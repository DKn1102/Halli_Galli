package HalGal;

import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.*;

public class WaitingRoom extends JFrame {
   
   Container cp;
   
   EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED); //테두리
   LineBorder lb = new LineBorder(Color.YELLOW,3);

   JButton enterButton = new JButton("입장");
   JButton makeRoomButton = new JButton("방만들기");
   JButton helpButton = new JButton("도움말");
   JButton exitButton = new JButton("종료");
   JButton ManagerButton = new JButton("관리자");
   
   
   WaitingRoom(){
      
      
     // setLayout(new GridLayout());
   //   URL url = getClass().getClassLoader().getResource("bord2.jpg");
    //  ImageIcon background = new ImageIcon(url);
      
      JPanel background1 = new JPanel() {
         public void paintComponent(Graphics g) {
       //     g.drawImage(background.getImage(), 0, 0, null);
            setOpaque(false); // 그림을 표시하게 설정,투명하게
            super.paintComponent(g);
            this.repaint();
         }
      };
      add(background1);
      background1.setLayout(null);

      
      setTitle("사이 좋은 대기실 !");  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container cp =getContentPane();
      
      JPanel bP1 = new JPanel(); //방만들기,종료,관리자,도움말 버튼
      bP1.setLayout(new GridLayout(2,2));
      makeRoomButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      helpButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      exitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      ManagerButton.setFont(new Font("Dialog", Font.PLAIN, 20));

      bP1.add(makeRoomButton);
      bP1.add(ManagerButton);
      bP1.add(helpButton);
      bP1.add(exitButton);
    
            
      bP1.setBounds(750, 400 , 300 , 180); //가로위치,세로위치,가로길이,세로길이
      background1.add(bP1);

      JPanel bP2 = new JPanel();
      bP2.setLayout(new GridLayout(1,1));
      enterButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      bP2.add(enterButton);
      
      bP2.setBounds(550, 400 , 195, 180);
      background1.add(bP2);
      
      
      
      JPanel roomPanel;
     
      JTextArea waitChatArea;
      
      
      JScrollPane waitSp;
      
   

       
      JPanel total = new JPanel();
      total.setLayout(new BorderLayout());

      roomPanel = new JPanel();
      roomPanel.setLayout(new GridLayout(2, 2));
      roomPanel.setBorder(new LineBorder(Color.BLACK, 1));

      JPanel movePanel = new JPanel();
      movePanel.setLayout(new GridLayout(2, 2));
      JButton up = new JButton("∧");
      
      up.setBorderPainted(false); //버튼 외곽선 없애줌
      up.setContentAreaFilled(false); //내용영역 채우기 안함
      JButton down = new JButton("∨");
      
      down.setBorderPainted(false);
      down.setContentAreaFilled(false);
      movePanel.add(up);
      movePanel.add(down);

      total.add(roomPanel, "Center");
      total.add(movePanel, "East");

      total.setBounds(30, 30, 430, 250);
      background1.add(total);

      ///
      JPanel chatJP = new JPanel();
      chatJP.setLayout(new BorderLayout());
      waitChatArea = new JTextArea(1, 1);
      waitChatArea.setEditable(false);
      waitChatArea.setText("욕설금지 욕하면 강퇴 매너 지켜주삼 \n");
      waitChatArea.setFont(new Font("Dialog", Font.PLAIN, 20));
      waitSp = new JScrollPane(waitChatArea);
      waitSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      // 필요에의해서 내용이 많아지면 스크롤 바가 생긴다
      waitSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      // 가로 스크롤은 안만든다

      chatJP.add(waitSp, "Center");
      JTextField chatInput = new JTextField("");
      waitChatArea.setFont(new Font("Dialog", Font.PLAIN, 13));
      
      chatJP.setBounds(550, 30, 500, 350);
      background1.add(chatJP);
      
      chatJP.add(chatInput, "South");
      
       JPanel userPanel = new JPanel();
       userPanel.setLayout(new BorderLayout());
       JLabel userHeader = new JLabel("     no    |    name");
       JTextArea userArea = new JTextArea();
       JScrollPane userSP = new JScrollPane(userArea);
       userPanel.add(userHeader, "North");
       userPanel.add(userSP, "Center");
       userPanel.setBounds(30, 350, 385, 250);
       background1.add(userPanel);
      
      
      setResizable(false);
       setSize(1100,700);
       setVisible(true);
         
      
   
}

     

   public static void main(String[]args) {
            new WaitingRoom();
   }
   
   
}