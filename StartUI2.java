package HalGal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sun.tools.javac.Main;

import javax.imageio.*;
import java.io.*;


public class StartUI2 extends JFrame implements ActionListener, Runnable {

	Container cp;	//Container
	JButton startB;	//start Button
	JButton loginB;	//login Button
	JPanel loginP;	//login Panel
	JTextField ipInput;
	JTextField idInput;
	JLabel ipL;
	JLabel idL;
	ImageIcon startGif;
	//ImageIcon loginImg;
	
	public void run() {
			//try {
				//Thread.sleep(3600); 
				cp.remove(startB);
			//}catch(InterruptedException ie) {}
	}
	public void actionPerformed(ActionEvent e) {
		Object changeLoginP = e.getSource();
		//startB.setVisible(false);
		if(changeLoginP == startB) {
			cp.add(loginP);
			Thread buttonSleep = new Thread(new StartUI2());
			buttonSleep.start();
		}
	}
	/*
 	public void paintComponent(Graphics g) {
	Image loginImg = new ImageIcon("../img/hg.png").getImage();
	File f = new File("../img/hg.png");
	System.out.println(f.exists()?"Exists":"doesnt exists");
	g.drawImage(loginImg, 0, 0, this);
	}
	*/
	void screen() {
		startGif = new ImageIcon(getClass().getResource("../img/hg.gif")); // GIF 파일 위치
		setLayout(new CardLayout());
		ipL = new JLabel("IP   : ", SwingConstants.RIGHT);
		idL = new JLabel("ID   : ", SwingConstants.RIGHT);
		
		startB = new JButton(startGif);
		startB.setBounds(0, 0, 800, 450); // Game Start 버튼 위치 크기
		
		ipL.setLayout(null);
		ipL.setBounds(0, 0, 280, 780);
		idL.setLayout(null);
		idL.setBounds(0, 0, 280, 820);
		
		loginP = new JPanel();
		loginP.setBackground(new Color(255, 0, 0, 0));
		loginP.setLayout(null);
		loginP.setBounds(0, 0, 800, 450);
		loginP.add(ipL);
		loginP.add(idL);
		// IP 위치 & 크기
		ipInput = new JTextField();
		ipInput.setLayout(null);
		ipInput.setBounds(290, 380, 240, 20);
		loginP.add(ipInput);
		// ID 위치 & 크기
		idInput = new JTextField();
		idInput.setLayout(null);
		idInput.setBounds(290, 400, 240, 20);
		loginP.add(idInput);
		//LOGIN BUTTON 위치 & 크기
		loginB = new JButton("LOGIN");
		loginB.setLayout(null);
		loginB.setBounds(540, 380, 69, 40);  // 290
		loginP.add(loginB);
		
		//loginImg = new ImageIcon(getClass().getResource("hg.png"));
		//loginP = new JLabel(loginImg);
		
		//loginP.setPreferredSize(new Dimension(500, 30));	// 크기
		//ipPC.add(ipInput);
		//loginB.setPreferredSize(new Dimension(80, 50)); // 크기
		
		cp = getContentPane();
		
		cp.add(startB);
		startB.addActionListener(this);
		setUI();
	}
	void setUI() {
		setTitle("Halli Galli");
		setVisible(true);
		setSize(816, 488);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new StartUI2().screen();
	}

}

