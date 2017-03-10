import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.sifrekaydedici.library.Library;

public class Login extends Library implements ActionListener,MouseListener {
	static JPanel panel;
	JButton[] buttons;
	public ImageIcon[] icons;
	JLabel[] labels;
	JTextField[] login_fields;
	//JDIALOG PART
	JDialog dialog;
	JPanel dialogPanel;
	JTextField[] dialog_fields;
	
	public void init(){
		panel=new JPanel();
		login_fields=new JTextField[2];
		//DIALOG PART
		dialog=new JDialog();
		dialogPanel=new JPanel();
		dialog.setSize(new Dimension(300,400));
		dialog.setTitle("Kayýt Ol");
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(panel);
		dialog.add(dialogPanel);
		dialog.setVisible(false);
		dialogPanel.setLayout(null);
		dialog_fields=new JTextField[3];
		//---------------------------
		buttons=new JButton[3];
		icons=new ImageIcon[6];//bg ve button için 3 tane
		labels=new JLabel[2];
		imageIconInit(icons,0,"Login_Panel.png");//login bg
		imageIconInit(icons,1,"LoginButton.png");//login button resim
		imageIconInit(icons,2,"RegisterButton.png");//register button resim
		imageIconInit(icons,3,"LoginButtonAnim.png");//login button anim resim
		imageIconInit(icons,4,"RegisterButtonAnim.png");//register button anim resim
		imageIconInit(icons,5,"KayitDialog.png");//kayit dialog bg resim
	}
	
	public Login(){
		init();
		panel.setSize(new Dimension(752,504));
		panel.setLayout(null);
		panel.setOpaque(false);//bg transparent
		panel.setVisible(true);
		//LOGIN PANEL KONUMLANDIRMA
		textFieldEkle(panel,login_fields,0,210,157,349,54);//login bölümü kullanýcý adý tf
		textFieldEkle(panel,login_fields,1,210,287,349,54);//login bölümü sifre tf
		imageLabelEkle(panel,labels,0,icons,0,203,22,381,344);//login bg konumlandýrma
		imageButtonEkle(panel,buttons,0,icons,1,this,313,386,147,57);//login button
		imageButtonEkle(panel, buttons,1,icons,2,this,604,442,147,57);//regist button
		//DIALOG KONUMLANDIRMA
		textFieldEkle(dialogPanel,dialog_fields,2,44,83,201,29);//dialog kullanýcý ismi
		textFieldEkle(dialogPanel,dialog_fields,0,44,165,201,29);//dialog kullanýcý adý tf
		textFieldEkle(dialogPanel,dialog_fields,1,44,242,201,29);//dialog sifre tf
		imageButtonEkle(dialogPanel,buttons,2,icons,2,this,80,290,147,57);//dialog kayit button
		imageLabelEkle(dialogPanel,labels,1,icons,5,0,0,284,359);//dialog bg konumlandýrma
		
		buttons[0].addMouseListener(this);
		buttons[1].addMouseListener(this);
		buttons[2].addMouseListener(this);
		
		dialog.addWindowListener(new WindowListener() {
			
			
			
			@Override
			public void windowClosing(WindowEvent e) {//dialog kapandýðýnda kayýt ol butonu yeniden görünür oluyor
				// TODO Auto-generated method stub
				buttons[1].setVisible(true);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){//login butonu
			if(!(login_fields[0].getText().isEmpty()) && !(login_fields[1].getText().isEmpty())){
				Profile.createProfilesTable();
				if(JDBConnection.findAccount(login_fields[0].getText(),login_fields[1].getText())){
					panel.setVisible(false);
					Menu.panel.setVisible(true);
					String sql="CREATE TABLE IF NOT EXISTS "+JDBConnection.tempTable 
							+"(sifre_pk    INTEGER       PRIMARY KEY AUTOINCREMENT,"
							+"sifre_name  VARCHAR (20),"
							+"sifre_id    VARCHAR (20),"
							+"sifre_pw    VARCHAR (20),"
							+"sifre_email VARCHAR (50),"
							+"sifre_dis   VARCHAR (100))";
					JDBConnection.CreateTable(sql);
    				JDBConnection.tabloyaAktar();
				}
				else{
					JOptionPane.showMessageDialog(panel, "Lütfen id ve þifrenizi doðru bir þekilde giriniz!");
				}
			}
			else{
				JOptionPane.showMessageDialog(panel,"Lutfen id ve þifre bölümünü doldurunuz!");
			}
			
		}
		if(e.getSource().equals(buttons[1])){//login panelindeki register
			buttons[1].setVisible(false);
			dialog.setLocationRelativeTo(panel);
			dialog.setLocation(dialog.getX()+575,dialog.getY()-140);
			dialog.setVisible(true);
		}
		if(e.getSource().equals(buttons[2])){//dialog panelindeki kayýt butonu
			if(!(dialog_fields[0].getText().isEmpty()) && !(dialog_fields[1].getText().isEmpty()) && !(dialog_fields[2].getText().isEmpty()) ){
				Profile.createProfilesTable();//profile tablosu yoksa oluþturuyor.
				new Profile(dialog_fields[2].getText(),dialog_fields[0].getText(),dialog_fields[1].getText());
				for(int i=0;i<3;i++)//Reset dialog fields
				dialog_fields[i].setText("");
				dialog.setVisible(false);
				buttons[1].setVisible(true);		
			}
			else{
				JOptionPane.showMessageDialog(dialogPanel,"Lutfen bütün alanlarý doldurunuz!");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buttons[0])){//login button
			buttons[0].setIcon(icons[3]);
		}
		if(e.getSource().equals(buttons[1])){//register button
			buttons[1].setIcon(icons[4]);
		}
		if(e.getSource().equals(buttons[2])){//dialog register button
			buttons[2].setIcon(icons[4]);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buttons[0])){//login button anim
			buttons[0].setIcon(icons[1]);
		}
		if(e.getSource().equals(buttons[1])){//register button anim
			buttons[1].setIcon(icons[2]);
		}
		if(e.getSource().equals(buttons[2])){//dialog register button anim
			buttons[2].setIcon(icons[2]);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
