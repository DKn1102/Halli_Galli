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
   
   EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED); //�׵θ�
   LineBorder lb = new LineBorder(Color.YELLOW,3);

   JButton enterButton = new JButton("����");
   JButton makeRoomButton = new JButton("�游���");
   JButton helpButton = new JButton("����");
   JButton exitButton = new JButton("����");
   JButton ManagerButton = new JButton("������");
   
   
   WaitingRoom(){
      
      
     // setLayout(new GridLayout());
   //   URL url = getClass().getClassLoader().getResource("bord2.jpg");
    //  ImageIcon background = new ImageIcon(url);
      
      JPanel background1 = new JPanel() {
         public void paintComponent(Graphics g) {
       //     g.drawImage(background.getImage(), 0, 0, null);
            setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ�
            super.paintComponent(g);
            this.repaint();
         }
      };
      add(background1);
      background1.setLayout(null);

      
      setTitle("���� ���� ���� !");  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container cp =getContentPane();
      
      JPanel bP1 = new JPanel(); //�游���,����,������,���� ��ư
      bP1.setLayout(new GridLayout(2,2));
      makeRoomButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      helpButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      exitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      ManagerButton.setFont(new Font("Dialog", Font.PLAIN, 20));

      bP1.add(makeRoomButton);
      bP1.add(ManagerButton);
      bP1.add(helpButton);
      bP1.add(exitButton);
    
            
      bP1.setBounds(750, 400 , 300 , 180); //������ġ,������ġ,���α���,���α���
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
      JButton up = new JButton("��");
      
      up.setBorderPainted(false); //��ư �ܰ��� ������
      up.setContentAreaFilled(false); //���뿵�� ä��� ����
      JButton down = new JButton("��");
      
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
      waitChatArea.setText("�弳���� ���ϸ� ���� �ų� �����ֻ� \n");
      waitChatArea.setFont(new Font("Dialog", Font.PLAIN, 20));
      waitSp = new JScrollPane(waitChatArea);
      waitSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      // �ʿ信���ؼ� ������ �������� ��ũ�� �ٰ� �����
      waitSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      // ���� ��ũ���� �ȸ����

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