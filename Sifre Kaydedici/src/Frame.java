
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sifrekaydedici.library.Library;

public class Frame extends Library implements ActionListener,MouseListener{
	
	JFrame frame;
	static JPanel panel;
	static JLayeredPane lp;
	AnaPanel anaPanel;
	//Menu menuPanel;
	Login loginPanel;
	JButton[] buttons;//buttonlarýn tutulduðu array
	ImageIcon[] icon;// resimlerin tutulduðu array
	JLabel bgLabel[];//background  label içine imageIcon eklemek için
	
	public static void main(String[] args) {
		new JDBConnection();
		new Frame();
	}
	public void init(){
		
		lp=new JLayeredPane();
		panel=new JPanel();
		panel.setLayout(null);
		loginPanel=new Login();
		anaPanel=new AnaPanel();
		frame=new JFrame();
		//menuPanel=new Menu();
		
		buttons=new JButton[3];//system buttons exit minimize buttonlar için ve information
		icon=new ImageIcon[9]; //5 tane icon system butonlarý için
		imageIconInit(icon,0,"Exit.png");
		imageIconInit(icon,1,"Alt.png");
		imageIconInit(icon,2,"SifreKaydediciBG.png");
		imageIconInit(icon,3,"ExitAnim.png");
		imageIconInit(icon,4,"AltAnim.png");
		imageIconInit(icon,5,"SifreKaydediciGif.gif");
		imageIconInit(icon,6,"Information.png");
		imageIconInit(icon,7,"InformationAnim.png");
		imageIconInit(icon,8,"programiconPNG.png");//icon
		bgLabel=new JLabel[2];//imageLabelEkle bg için ve program baþlýl anim
	}
	public Frame(){
		init();
		undecoratedFrame(frame,840,632);
		frame.add(lp,null);
		frame.setIconImage(icon[8].getImage());
		lp.add(panel,new Integer(1));
		lp.add(Login.panel,new Integer(2));
		lp.add(AnaPanel.panel,new Integer(4));
		panel.setBounds(0,0,840,632);
		//panel.add(AnaPanel.panel);
		//panel.add(Menu.panel);
		//panel.add(Login.panel);
		AnaPanel.panel.setLocation(44,96);
		//Menu.panel.setLocation(44,96);
		Login.panel.setLocation(44,96);
		//Menu.panel.setVisible(false);
		
		setFrameMotion(frame,true);//frame drag sistemi
		imageButtonEkle(panel,buttons,2,icon,6,this,775,40,51,49);//information button
		imageButtonEkle(panel,buttons,0,icon,0,this,panel.getX()+768,panel.getY(),53,28);//exit butonu
		imageButtonEkle(panel,buttons,1,icon,1,this,panel.getX()+715,panel.getY(),53,28);// minimize butonu
		imageLabelEkle(panel,bgLabel,1,icon,5,280,10,282,30);//sife kaydedici labelý
		imageLabelEkle(panel,bgLabel,0,icon,2,0,0,840,632);//labelýn içine bg yerleþtirilip framede konumlandýrýlýyor
		
		
		buttons[0].addMouseListener(this);//butonlarýn mouse listenerda çalýþmasý için panele ekleyince layerdan dolayý çalýþmýyor
		buttons[1].addMouseListener(this);
		buttons[2].addMouseListener(this);//information
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){
			//exit window
			System.exit(0);
		}
		if(e.getSource().equals(buttons[1])){
			//minimize window
			frame.setState(JFrame.ICONIFIED);
		}
		if(e.getSource().equals(buttons[2])){
			JOptionPane.showMessageDialog(null,"11/10/16\nOktay Koçer tarafýndan yapýlmýþtýr.\nProgram tüm hesaplarýnýzý bilgisayarýnýzýn\nhafýzasýnda güvenli bir þekilde saklar.\nHerhangi net baðlantýsý yoktur.\nGITHUB:http://github.com/oktaykcr","Program Hakkýnda",JOptionPane.INFORMATION_MESSAGE,icon[7]);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(buttons[0])){
			//exit anim
			buttons[0].setIcon(icon[3]);
		}
		if(e.getSource().equals(buttons[1])){
			//minimize anim
			buttons[1].setIcon(icon[4]);
		}
		if(e.getSource().equals(buttons[2])){
			buttons[2].setIcon(icon[7]);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buttons[0])){
			//exit anim
			buttons[0].setIcon(icon[0]);
		}
		if(e.getSource().equals(buttons[1])){
			//minimize anim
			buttons[1].setIcon(icon[1]);
		}
		if(e.getSource().equals(buttons[2])){
			//minimize anim
			buttons[2].setIcon(icon[6]);
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
