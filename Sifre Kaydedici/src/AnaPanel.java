
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sifrekaydedici.library.Library;

public class AnaPanel extends Library implements ActionListener {
	Font font=new Font("Arial",Font.BOLD,12);
	static JPanel panel;
	ImageIcon[] icon;
	static JButton[] buttons;
	static JLabel[] imgLabel;
	//JTABLE PART
	static JTable table;
	static DefaultTableModel model;
	static TableRowSorter<TableModel> sorter;
	JScrollPane sp;
	//---------------------------------------------
	//EKLE
	static JTextField[] ekle_fields;
	//-------------------------------
	//SIL
	static JLabel[] tableLabel;//sil ve ara için tablo label a aktarýlýyor
	static JTextField[] sil_field;
	//-------------------------------
	//ARA
	static JTextField[] ara_field;
	//-------------------------------
	//GUNCELLE
	JDialog dialog;//tabloya týklandýðýnda dialog penceresi çýkacak
	JPanel dPanel;//dialog içindeki panel
	static JTextField[] guncelle_field;
	static JButton[] exitButton;//dialog panel exitButton
	//-------------------------------
	
	public void init(){
		panel=new JPanel();
		//JTable part
		model=new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table=new JTable(model);
		model.addColumn("*");
		model.addColumn("Sifre Adý");
		model.addColumn("ID");
		model.addColumn("PW");
		model.addColumn("EMAIL");
		model.addColumn("Discription");
		//JTable Align
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<6;i++)
		table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		
		//--------------------
//		JDialog part
		dialog=new JDialog();
		dialog.setUndecorated(true);
		setFrameMotion(dialog,true);
		dialog.setSize(new Dimension(300,400));
		dPanel=new JPanel();
		dialog.setTitle("Güncelle");
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(panel);
		dialog.add(dPanel);
		dPanel.setLayout(null);
		exitButton=new JButton[1];
//		----------------------------------------
		tableLabel=new JLabel[1];
		icon=new ImageIcon[12];
		buttons=new JButton[5];
		imgLabel=new JLabel[6];
		
		ekle_fields=new JTextField[5];
		sil_field=new JTextField[1];
		ara_field=new JTextField[1];
		guncelle_field=new JTextField[5];
		
		imageIconInit(icon,0,"Geri.png");
		imageIconInit(icon,1,"Ekle_icerik.png");
		imageIconInit(icon,2,"Sil_icerik.png");
		imageIconInit(icon,3,"Ara_icerik.png");
		imageIconInit(icon,4,"Guncelle_icerik.png");
		imageIconInit(icon,5,"EkleButon_icerik.png");
		imageIconInit(icon,6,"SilButon_icerik.png");
		imageIconInit(icon,7,"AraButon_icerik.png");
		imageIconInit(icon,8,"GuncelleButon_icerik.png");
		imageIconInit(icon,9,"guncelle_dialog.png");
		imageIconInit(icon,10,"Exit.png");
		imageIconInit(icon,11,"ExitAnim.png");
	}
	public AnaPanel(){
		init();
		panel.setSize(new Dimension(752,504));
		panel.setLayout(null);
		panel.setOpaque(false);

		
		//Ekle PART
		imageButtonEkle(panel,buttons,0,icon,5,this,652,19,72,72);//ekle icerik buton
		textFieldEkle(panel,ekle_fields,4,198,441,358,34);ekle_fields[4].addActionListener(this);//açýklama
		textFieldEkle(panel,ekle_fields,3,239,359,274,34);ekle_fields[3].addActionListener(this);//email tf
		textFieldEkle(panel,ekle_fields,2,291,277,173,34);ekle_fields[2].addActionListener(this);//þifre tf
		textFieldEkle(panel,ekle_fields,1,291,195,173,34);ekle_fields[1].addActionListener(this);//id tf
		textFieldEkle(panel,ekle_fields,0,269,113,220,34);ekle_fields[0].addActionListener(this);//þifre adý tf
		imageLabelEkle(panel,imgLabel,1,icon,1,198,30,358,445);//ekle_icerik bg
		//----------------------------------------------------------------------
		
		
		//JTABLE PART
		labelEkle(panel,tableLabel,0,17,160,715,340);
		tableLabel[0].setLayout(new FlowLayout());
		tableLabel[0].add(table.getTableHeader(),BorderLayout.PAGE_START);
		tableLabel[0].add(table,BorderLayout.CENTER);
		table.setPreferredScrollableViewportSize(new Dimension(715,340));
		table.setRowHeight(50);
		sp=new JScrollPane(table);
		tableLabel[0].add(sp);
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		table.setBackground(new Color(45,45,45));
		table.getTableHeader().setBackground(new Color(45,45,45));
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.getTableHeader().setForeground(Color.green);
		table.setForeground(Color.green);
		
		//Table Sorter Initialize
		sorter=new TableRowSorter<TableModel>(model);//tablo filter için 
		table.setRowSorter(sorter);//filter tabloya ekleniyor
		table.addMouseListener(new MouseAdapter() {
			//tablodaki verilere tiklandiginda
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(Menu.menuInfo==2){//sil bölümðne geçince tabloya týklandýðýnda silTablo metotdu çalýþacak
					silTabloTextField();
				}
				if(Menu.menuInfo==4){//guncelle tablosu için
					dialog.setLocationRelativeTo(panel);
					dialog.setLocation(dialog.getX()+575,dialog.getY()-140);
					dialog.setVisible(true);
					guncelleTablePart();
				}	
		     }
		});
		//-------------------------------------------------------------------
		
		//SIL PART
		textFieldEkle(panel,sil_field,0,269,113,220,34);
		imageButtonEkle(panel,buttons,1,icon,6,this,652,19,72,72);
		imageLabelEkle(panel,imgLabel,2,icon,2,269,30,220,117);//sil_icerik bg
		//----------------------------------------------------------------------
		
		//ARA PART
		textFieldEkle(panel,ara_field,0,268,113,220,34);
		ara_field[0].getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateFilter();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateFilter();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateFilter();
			}
		});
		imageButtonEkle(panel,buttons,2,icon,7,this,652,20,72,72);
		imageLabelEkle(panel,imgLabel,3,icon,3,268,30,220,117);//ara icerik bg
		//----------------------------------------------------------------------
		
		//GUNCELLE PART
		//Guncelle dialog
		textFieldEkle(dPanel,guncelle_field,0,70,42,167,30);//sifre adý
		textFieldEkle(dPanel,guncelle_field,1,85,121,131,30);//id
		textFieldEkle(dPanel,guncelle_field,2,85,200,131,30);//sifre
		textFieldEkle(dPanel,guncelle_field,3,45,278,208,30);//email
		textFieldEkle(dPanel,guncelle_field,4,14,357,272,30);//acýklama
		imageButtonEkle(dPanel,exitButton,0,icon,10,this,242,3,53,28);// dialog panel exit butonu
		imageLabelEkle(dPanel,imgLabel,5,icon,9,0,0,300,400);//Dialog panel bg
		exitButton[0].addMouseListener(new MouseAdapter() {
@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				exitButton[0].setIcon(icon[11]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				exitButton[0].setIcon(icon[10]);
			}
		});
		//---------------------------------------------------------------------
		imageButtonEkle(panel,buttons,3,icon,8,this,652,20,72,72);
		imageLabelEkle(panel,imgLabel,4,icon,4,263,27,231,25);//guncelle icerik bg
		//----------------------------------------------------------------------
		
		
		//GERI butonu
		imageButtonEkle(panel,buttons,4,icon,0,this,27,19,72,72);

		ekle_visibility(false);
		sil_visibility(false);
		ara_visibility(false);
		guncelle_visibility(false);
		buttons[4].setVisible(false);//ilk baþta geri butonu görünmemesi için
		tableLabel[0].setVisible(false);//tablonun ilk görünümü false
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){//ekle icerik button
			ekleMethod();
		}
		if(e.getSource().equals(buttons[1])){//sil icerik button
			String query="DELETE FROM "+JDBConnection.tempTable+" WHERE"+"(sifre_pk)="+sil_field[0].getText();
			if(!(sil_field[0].getText().isEmpty())){
				JDBConnection.executeSQLQuery(query,"Silme ");
				sil_field[0].setText("");
			}
			else{
				JOptionPane.showMessageDialog(null,"Lutfen Tablodan sileceðiniz sifreyi seçiniz veya id numarasýný giriniz!");
			}
			
		
		}
		if(e.getSource().equals(buttons[2])){//ara icerik buton
			updateFilter();
		}
		if(e.getSource().equals(buttons[3])){//guncelle icerik buton
			String query="UPDATE "+JDBConnection.tempTable+" SET sifre_name='"+guncelle_field[0].getText()+"',"+"sifre_id='"+guncelle_field[1].getText()+
					"',"+"sifre_pw='"+guncelle_field[2].getText()+"',"+"sifre_email='"+guncelle_field[3].getText()+"',"+"sifre_dis='"+
					guncelle_field[4].getText()+"' WHERE sifre_pk="+model.getValueAt(table.getSelectedRow(),0);
			if(!(guncelle_field[0].getText().isEmpty()) && !(guncelle_field[1].getText().isEmpty()) &&
					!(guncelle_field[2].getText().isEmpty()) && !(guncelle_field[3].getText().isEmpty()) &&
					!(guncelle_field[4].getText().isEmpty())){
				JDBConnection.executeSQLQuery(query,"Güncelleme ");
			}else{
				JOptionPane.showMessageDialog(panel,"Lütfen bütün alanlarý doldurunuz!");
			}
		}
		if(e.getSource().equals(buttons[4])){//geri butonu
			if(Menu.menuInfo==3){
				sorter.setRowFilter(null);
				ara_field[0].setText("");
			}
			Menu.menuInfo=0;
			ekle_visibility(false);
			sil_visibility(false);
			ara_visibility(false);
			guncelle_visibility(false);
			Menu.panel.setVisible(true);
			buttons[4].setVisible(false);
			tableLabel[0].setVisible(false);
			dialog.setVisible(false);
		}
		
		if(Menu.menuInfo==1){
			//ekle_fields actions
			if(e.getSource().equals(ekle_fields[0])){
				ekle_fields[1].requestFocus();
			}
			if(e.getSource().equals(ekle_fields[1])){
				ekle_fields[2].requestFocus();
			}
			if(e.getSource().equals(ekle_fields[2])){
				ekle_fields[3].requestFocus();
			}
			if(e.getSource().equals(ekle_fields[3])){
				ekle_fields[4].requestFocus();
			}
			if(e.getSource().equals(ekle_fields[4])){
				ekleMethod();
			}
		}
	}
	
	private void updateFilter(){
		String text=ara_field[0].getText();
		if(text.length()==0){
			sorter.setRowFilter(null);
		}
		else{
			sorter.setRowFilter(RowFilter.regexFilter(text,1));// sutun 1 e göre filtreleme yapýyor
		}
	}
	
	private void ekleMethod(){
		String query="insert into "+JDBConnection.tempTable+"(sifre_name,sifre_id,sifre_pw,sifre_email,sifre_dis)"+"values('"+ekle_fields[0].getText()
				+"','"+ekle_fields[1].getText()+"','"+ekle_fields[2].getText()+"','"+ekle_fields[3].getText()+"','"+ekle_fields[4].getText()+"')";
		if(!(ekle_fields[0].getText().isEmpty()) && !(ekle_fields[1].getText().isEmpty()) &&
				!(ekle_fields[2].getText().isEmpty()) && !(ekle_fields[3].getText().isEmpty()) &&
				!(ekle_fields[4].getText().isEmpty())){
			JDBConnection.executeSQLQuery(query,"Ekleme ");
			for(int i=0;i<5;i++) ekle_fields[i].setText("");//reset ekle fields
		}
		else{
			JOptionPane.showMessageDialog(panel,"Lütfen bütün alanlarý doldurunuz!");
		}
	}
	
	public void silTabloTextField(){
		//tablodaki row a týklandýðýnda sifre ara textField ta sifre adýnýn,
		//gözükmesi
		int i=table.getSelectedRow();
		javax.swing.table.TableModel model=table.getModel();
		sil_field[0].setText(model.getValueAt(i,0).toString());
	}
	public void araTableTextField(){
		//tablodaki row a týklandýðýnda sifre ara textField ta sifre adýnýn,
		//gözükmesi
		int i=table.getSelectedRow();
		javax.swing.table.TableModel model=table.getModel();
		ara_field[0].setText(model.getValueAt(i,1).toString());
	}
	public void guncelleTablePart(){
		int i=table.getSelectedRow();
		javax.swing.table.TableModel model=table.getModel();
		guncelle_field[0].setText(model.getValueAt(i,1).toString());
		guncelle_field[1].setText(model.getValueAt(i,2).toString());
		guncelle_field[2].setText(model.getValueAt(i,3).toString());
		guncelle_field[3].setText(model.getValueAt(i,4).toString());
		guncelle_field[4].setText(model.getValueAt(i,5).toString());
	}

	public static  void ekle_visibility(boolean visi){
		if(visi==false){
			imgLabel[1].setVisible(false);//ekle_icerik baþlangýç için visibility false
			for(int i=0;i<5;i++){
				ekle_fields[i].setVisible(false);
			}
			buttons[0].setVisible(false);
			
		}
		else{
			buttons[4].setVisible(true);
			Menu.panel.setVisible(false);
			imgLabel[1].setVisible(true);//ekle_icerik baþlangýç için visibility false
			for(int i=0;i<5;i++){
				ekle_fields[i].setVisible(true);
			}
			buttons[0].setVisible(true);
			
		}
		
	}
	public static void sil_visibility(boolean visi){
		if(visi==false){
			imgLabel[2].setVisible(false);
			buttons[1].setVisible(false);
			sil_field[0].setVisible(false);
		}
		else{
			buttons[4].setVisible(true);
			Menu.panel.setVisible(false);
			imgLabel[2].setVisible(true);
			buttons[1].setVisible(true);
			sil_field[0].setVisible(true);
		}
	}
	public static void ara_visibility(boolean visi){
		if(visi==false){
			imgLabel[3].setVisible(false);
			buttons[2].setVisible(false);
			ara_field[0].setVisible(false);
		}
		else{
			buttons[4].setVisible(true);
			Menu.panel.setVisible(false);
			imgLabel[3].setVisible(true);
			buttons[2].setVisible(true);
			ara_field[0].setVisible(true);
		}
	}
	public static void guncelle_visibility(boolean visi){
		if(visi==false){
			imgLabel[4].setVisible(false);
			buttons[3].setVisible(false);
		}
		else{
			buttons[4].setVisible(true);
			Menu.panel.setVisible(false);
			imgLabel[4].setVisible(true);
			buttons[3].setVisible(true);
		}
	}
}
