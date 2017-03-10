package com.sifrekaydedici.library;

import java.awt.TextArea;

public class Library {
	
	public void frameAc(javax.swing.JFrame frame,String name,int width,int height,boolean visibility){
		//sadece JFrame açma
		frame.setTitle("Ana Panel");
		frame.setSize(width,height);
		frame.setVisible(visibility);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}
	public void frameAc(javax.swing.JFrame frame,javax.swing.JPanel panel,java.awt.LayoutManager layout,String name,int width,int height,boolean visibility){
		//panelli frame.panel layout giriþi var
		frame.setTitle(name);
		frame.setSize(width,height);
		frame.setVisible(visibility);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		panel=new javax.swing.JPanel();
		frame.add(panel);
		panel.setLayout(layout);
	}
	public void undecoratedFrame(javax.swing.JFrame frame,int width,int height){
		frame.setSize(width,height);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	public void panelEkle(javax.swing.JFrame frame,javax.swing.JPanel panel,java.awt.LayoutManager layout,boolean visibility){
		//frame e panel ekleme.Panel layout giriþi var
		frame.add(panel);
		panel.setVisible(visibility);
		panel.setLayout(layout);
	}
	public void windowAc(javax.swing.JWindow window,int width,int height,boolean visibility){
		//Jwindow açma fonk
		window.setSize(width,height);
		window.setVisible(visibility);
		window.setLocationRelativeTo(null);
	}
	public void fullscreenWindow(javax.swing.JWindow window,boolean fullscreenInfo){
		//JWindow fullscreen yapma
		//true ise full
		//false ise eski hali
		java.awt.Toolkit tk=java.awt.Toolkit.getDefaultToolkit();
		int windowX=(int)tk.getScreenSize().getWidth();
		int windowY=(int)tk.getScreenSize().getHeight();
		if(fullscreenInfo){
			window.setSize(windowX,windowY);
			window.setLocation(0,0);
		}
		else{
			window.setSize(640,480);
			window.setLocation(windowX/2-(window.getWidth()/2),windowY/2-(window.getHeight()/2));
		}
	}
	public void fullscreenFrame(javax.swing.JFrame window,boolean fullscreenInfo){
		//undecorated JFrame fullscreen yapma
		//true ise full
		//false ise eski hali
		java.awt.Toolkit tk=java.awt.Toolkit.getDefaultToolkit();
		int windowX=(int)tk.getScreenSize().getWidth();
		int windowY=(int)tk.getScreenSize().getHeight();
		if(fullscreenInfo){
			window.setSize(windowX,windowY);
			window.setLocation(0,0);
		}
		else{
			window.setSize(640,480);
			window.setLocation(windowX/2-(window.getWidth()/2),windowY/2-(window.getHeight()/2));
		}
	}
	public void setWindowMotion(javax.swing.JWindow window,boolean drag){
		//JWindow hareket ettirme
		if(drag==true){
			java.awt.event.MouseAdapter ma=new java.awt.event.MouseAdapter(){
				int lastX, lastY;
		        @Override
		        public void mousePressed(java.awt.event.MouseEvent e) {
		            lastX = e.getXOnScreen();
		            lastY = e.getYOnScreen();
		        }
		        @Override
		        public void mouseDragged(java.awt.event.MouseEvent e) {
		            int x = e.getXOnScreen();
		            int y = e.getYOnScreen();
		            // Move frame by the mouse delta
		            window.setLocation(window.getLocationOnScreen().x + x - lastX,
		                    window.getLocationOnScreen().y + y - lastY);
		            lastX = x;
		            lastY = y;
		        }
		    };
		    window.addMouseListener(ma);
		    window.addMouseMotionListener(ma);
			}
		}
	public void setFrameMotion(javax.swing.JFrame window,boolean drag){
		// undecorated JFrame hareket ettirme
		if(drag==true){
			java.awt.event.MouseAdapter ma=new java.awt.event.MouseAdapter(){
				int lastX, lastY;
		        @Override
		        public void mousePressed(java.awt.event.MouseEvent e) {
		            lastX = e.getXOnScreen();
		            lastY = e.getYOnScreen();
		        }
		        @Override
		        public void mouseDragged(java.awt.event.MouseEvent e) {
		            int x = e.getXOnScreen();
		            int y = e.getYOnScreen();
		            // Move frame by the mouse delta
		            window.setLocation(window.getLocationOnScreen().x + x - lastX,
		                    window.getLocationOnScreen().y + y - lastY);
		            lastX = x;
		            lastY = y;
		        }
		    };
		    window.addMouseListener(ma);
		    window.addMouseMotionListener(ma);
			}
		}
		
	public void buttonEkle(javax.swing.JPanel panel,javax.swing.JButton button,String name,java.awt.event.ActionListener al,int x,int y,int width,int height ){
		//arraysiz buton text
		button=new javax.swing.JButton(name);
		panel.add(button);
		button.setBounds(x,y,width,height);
		button.addActionListener(al);
		panel.revalidate();
	}
	public void buttonEkle(javax.swing.JPanel panel,javax.swing.JButton[] button,int index,String name,java.awt.event.ActionListener al,int x,int y,int width,int height ){
		//array buton ekleme text
		button[index]=new javax.swing.JButton(name);
		panel.add(button[index]);
		button[index].setBounds(x,y,width,height);
		button[index].addActionListener(al);
		panel.revalidate();
	}
	public void textFieldEkle(javax.swing.JPanel panel,javax.swing.JTextField[] fields,int index,int x,int y,int width,int height ){
		//null layoutlu panele JTextField ekliyor ,text fieldlar saydam
		fields[index]=new javax.swing.JTextField();
		panel.add(fields[index]);
		fields[index].setBounds(x, y, width, height);
		fields[index].setOpaque(false);
		fields[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fields[index].setForeground(java.awt.Color.black);
		fields[index].setCaretColor(java.awt.Color.black);
		fields[index].setHorizontalAlignment(javax.swing.JTextField.CENTER);//caret i ortaya alma
		panel.revalidate();
	}
	public void textFieldEkle(javax.swing.JDialog dialog,javax.swing.JTextField[] fields,int index,int x,int y,int width,int height ){
		//null layoutlu diyalog JTextField ekliyor ,text fieldlar saydam
		fields[index]=new javax.swing.JTextField();
		dialog.add(fields[index]);
		fields[index].setBounds(x, y, width, height);
		fields[index].setOpaque(false);
		fields[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fields[index].setForeground(java.awt.Color.black);
		fields[index].setCaretColor(java.awt.Color.black);
		fields[index].setHorizontalAlignment(javax.swing.JTextField.CENTER);//caret i ortaya alma
		dialog.revalidate();
	}
	public void textAreaEkle(javax.swing.JPanel panel,TextArea ta,int x,int y,int width,int height){ 
		//null layout dýþýnda çalýþýr panele JTextArea ekliyor JScrollPane de dahil her ikisi de transparent 
		ta=new TextArea("",3,100,TextArea.SCROLLBARS_VERTICAL_ONLY);
		panel.add(ta);
		ta.setBounds(x, y, width, height);
		panel.revalidate();
		//ta.setOpaque(false);
		//ta.setCaretColor(java.awt.Color.white);
		ta.setForeground(java.awt.Color.white);
		//ta.setLineWrap(true);
		//sp=new javax.swing.JScrollPane(ta,javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//panel.add(sp);
		//sp.setBounds(ta.getX(),ta.getY(),ta.getWidth(),ta.getHeight());
		//scrollpane transparent yapma
		//sp.setOpaque(false);
		//sp.getViewport().setOpaque(false);
		//sp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	}
	public void labelEkle(javax.swing.JPanel panel,javax.swing.JLabel[] label,int index,int x,int y,int width,int height){
		//null layoutlu panele label ekliyor
		label[index]=new javax.swing.JLabel();
		panel.add(label[index]);
		label[index].setBounds(x, y, width, height);
		panel.revalidate();
	}
	public void textLabelEkle(javax.swing.JPanel panel,javax.swing.JLabel[] label,int index,String text,int x,int y,int width,int height){
		//null layoutlu panele herhangi girilen ifadeyi label a ekliyor
		label[index]=new javax.swing.JLabel();
		panel.add(label[index]);
		label[index].setText(text);
		label[index].setBounds(x, y, width, height);
		panel.revalidate();
	}
	public void imageLabelEkle(javax.swing.JPanel panel,javax.swing.JLabel[] label,int index,javax.swing.ImageIcon[] icon,int iconindex,int x,int y,int width,int height){
		//null layoutlu panele istenilen iconu label a yereþtirerek ekrana basýyor
		label[index]=new javax.swing.JLabel();
		panel.add(label[index]);
		label[index].setIcon(icon[iconindex]);
		label[index].setBounds(x, y, width, height);
		panel.revalidate();
	}
	public void imageLabelEkle(javax.swing.JDialog dialog,javax.swing.JLabel[] label,int index,javax.swing.ImageIcon[] icon,int iconindex,int x,int y,int width,int height){
		//null layoutlu panele istenilen iconu label a yereþtirerek ekrana basýyor
		label[index]=new javax.swing.JLabel();
		dialog.add(label[index]);
		label[index].setIcon(icon[iconindex]);
		label[index].setBounds(x, y, width, height);
		dialog.revalidate();
	}
	public void imageLabelEkle(javax.swing.JLayeredPane lPane,javax.swing.JLabel[] label,int index,javax.swing.ImageIcon[] icon,int iconindex,int x,int y,int width,int height){
		//JLayeredPane e istenilen iconu label a yereþtirerek ekrana basýyor
		label[index]=new javax.swing.JLabel();
		lPane.add(label[index]);
		label[index].setIcon(icon[iconindex]);
		label[index].setBounds(x, y, width, height);
		lPane.revalidate();
	}
	public void imageIconInit(javax.swing.ImageIcon[] icon,int index,String file_name){
		//array imageIcon initialize etme
		//kaynak dosyalar class dosyasýnýn içinde
		try{
			icon[index]=new javax.swing.ImageIcon(getClass().getClassLoader().getResource("Resources/"+file_name));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void  imageButtonEkle(javax.swing.JPanel panel,javax.swing.JButton[] button,int index,javax.swing.ImageIcon[] icon,int iconIndex,java.awt.event.ActionListener al,int x,int y,int width,int height ){
		// PANEL ICIN --array butona array icon ekleme text siz (buton özelliði yok tamamen resim hali ve actionListener da çalýþýyor)
		button[index]=new javax.swing.JButton(icon[iconIndex]);
		panel.add(button[index]);
		button[index].setBounds(x, y, width, height);
		button[index].addActionListener(al);
		button[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		button[index].setContentAreaFilled(false);
		button[index].setOpaque(false);
		panel.revalidate();
	}
	public void  imageButtonEkle(javax.swing.JLayeredPane lPane,javax.swing.JButton[] button,int index,javax.swing.ImageIcon[] icon,int iconIndex,java.awt.event.ActionListener al,int x,int y,int width,int height ){
		// JLAYEREDPANE ICIN --array butona array icon ekleme text siz (buton özelliði yok tamamen resim hali ve actionListener da çalýþýyor)
		button[index]=new javax.swing.JButton(icon[iconIndex]);
		lPane.add(button[index]);
		button[index].setBounds(x, y, width, height);
		button[index].addActionListener(al);
		button[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		button[index].setContentAreaFilled(false);
		button[index].setOpaque(false);
		lPane.revalidate();
	}
	/*	ekraný fullscreen yapma
	 * 
	 * if(fullscreen==false){
				int x=30;
				setFrameMotion(frame,false);
				fullscreenFrame(frame,true);
				for(int i=0;i<3;i++){
					buttons[i].setLocation((panel.getX()+frame.getWidth())-x,panel.getY());
					x+=30;
				}
				fullscreen=true;
			}
			else if(fullscreen==true){
				int x=30;
				setFrameMotion(frame,true);
				fullscreenFrame(frame,false);
				for(int i=0;i<3;i++){
					buttons[i].setLocation((panel.getX()+frame.getWidth())-x,panel.getY());
					x+=30;
				}
				fullscreen=false;
			}
	 * */
}
