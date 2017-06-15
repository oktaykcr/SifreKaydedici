import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import com.sifrekaydedici.library.Library;

public class Menu extends Library implements ActionListener,MouseListener{
	static JPanel panel;
	JButton[] buttons;
	ImageIcon[] icon;
	JLabel[] label;
	static int menuInfo=0;// 0-menü 1-ekle 2- sil 3-ara 4-guncelle
	
	public void init() {
		panel=new JPanel();
		buttons=new JButton[5];//ekle sil ara guncelle profil resmi butonlari
		icon=new ImageIcon[10];//butonlar için icon
		label=new JLabel[1];
		imageIconInit(icon,0,"EkleButon.png");
		imageIconInit(icon,1,"SilButon.png");
		imageIconInit(icon,2,"AraButon.png");
		imageIconInit(icon,3,"GuncelleButon.png");
		imageIconInit(icon,4,"menu_new.png");
		imageIconInit(icon,5,"EkleButonAnim.png");
		imageIconInit(icon,6,"SilButonAnim.png");
		imageIconInit(icon,7,"AraButonAnim.png");
		imageIconInit(icon,8,"GuncelleButonAnim.png");
		
		//userPref klasorunun olup olmadigina bakiliyor
		File userPref=new File("userPref");
		if(!(userPref.exists())) userPref.mkdir();
		
		java.io.File profile_image=new java.io.File("userPref/"+JDBConnection.tempID+".png");
		System.out.println(profile_image.exists());
		if(profile_image.exists()){ 
			try{
				icon[9]=new javax.swing.ImageIcon("userPref/"+JDBConnection.tempID+".png");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} 
		else imageIconInit(icon,9,"profile_image.png");
		
		
		menuInfo=0;//þuan menüde baþladý
	}
	public Menu(){
		init();
		panel.setSize(new Dimension(752,504));
		panel.setLayout(null);
		panel.setOpaque(false);//transparent bg
		imageButtonEkle(panel,buttons,0,icon,0,this,83,144,207,115);//ekle
		imageButtonEkle(panel,buttons,1,icon,1,this,72,254,115,133);//sil
		imageButtonEkle(panel,buttons,2,icon,2,this,461,144,191,115);//ara
		imageButtonEkle(panel,buttons,3,icon,3,this,550,254,154,133);//güncelle
		imageLabelEkle(panel,label,0,icon,4,279,16,205,471);// menu
		addResizeImgButton(panel,buttons,4,icon,9,this,297,16,168,170);
		buttons[0].addMouseListener(this);
		buttons[1].addMouseListener(this);
		buttons[2].addMouseListener(this);
		buttons[3].addMouseListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){//ekle
			menuInfo=1;
			panel.setVisible(false);
			AnaPanel.ekle_visibility(true);
			AnaPanel.sil_visibility(false);
			AnaPanel.ara_visibility(false);
			AnaPanel.guncelle_visibility(false);
			AnaPanel.tableLabel[0].setVisible(false);
		}
		if(e.getSource().equals(buttons[1])){//sil
			menuInfo=2;
			panel.setVisible(false);
			AnaPanel.sil_visibility(true);
			AnaPanel.ekle_visibility(false);
			AnaPanel.ara_visibility(false);
			AnaPanel.guncelle_visibility(false);
			
			AnaPanel.tableLabel[0].setVisible(true);
			AnaPanel.tableLabel[0].setLocation(17,150);
			AnaPanel.tableLabel[0].setSize(new Dimension(715,350));
		}
		if(e.getSource().equals(buttons[2])){//ara
			menuInfo=3;
			panel.setVisible(false);
			AnaPanel.ekle_visibility(false);
			AnaPanel.sil_visibility(false);
			AnaPanel.ara_visibility(true);
			AnaPanel.guncelle_visibility(false);
			
			AnaPanel.tableLabel[0].setVisible(true);
			AnaPanel.tableLabel[0].setLocation(17, 150);
			AnaPanel.tableLabel[0].setSize(new Dimension(715,350));
		}
		if(e.getSource().equals(buttons[3])){//guncelle
			menuInfo=4;
			panel.setVisible(false);
			AnaPanel.ekle_visibility(false);
			AnaPanel.sil_visibility(false);
			AnaPanel.ara_visibility(false);
			AnaPanel.guncelle_visibility(true);
			
			AnaPanel.tableLabel[0].setVisible(true);
			AnaPanel.tableLabel[0].setLocation(25,108);
			AnaPanel.tableLabel[0].setSize(new Dimension(715,350));
		}
		
		if(e.getSource().equals(buttons[4])){//Profil Resmi butonu
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images","png");
			jfc.setFileFilter(filter);
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				File nFile=new File("userPref/"+JDBConnection.tempID+".png");
				try (InputStream is = new FileInputStream(selectedFile)) {
				      Files.copy(is, nFile.toPath());
				      panel.remove(buttons[4]);
				      try{
						icon[9]=new javax.swing.ImageIcon("userPref/"+JDBConnection.tempID+".png");
				      }catch(Exception ex){
						ex.printStackTrace();
					  }
				      addResizeImgButton(panel,buttons,4,icon,9,this,297,16,168,170);//
				      panel.repaint();
				}catch (IOException ex) {
				      System.err.println(ex);
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(buttons[0])){//ekle
			buttons[0].setIcon(icon[5]);
		}
		if(e.getSource().equals(buttons[1])){//sil
			buttons[1].setIcon(icon[6]);
		}
		if(e.getSource().equals(buttons[2])){//ara
			buttons[2].setIcon(icon[7]);
		}
		if(e.getSource().equals(buttons[3])){//guncelle
			buttons[3].setIcon(icon[8]);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(buttons[0])){//ekle
			buttons[0].setIcon(icon[0]);
		}
		if(e.getSource().equals(buttons[1])){//sil
			buttons[1].setIcon(icon[1]);
		}
		if(e.getSource().equals(buttons[2])){//ara
			buttons[2].setIcon(icon[2]);
		}
		if(e.getSource().equals(buttons[3])){//guncelle
			buttons[3].setIcon(icon[3]);
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
