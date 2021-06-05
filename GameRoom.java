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

public class GameRoom extends JFrame {
   
   Container cp;
   JPanel bP1;
   
   
   Timer timer;
   TimerTask task;
   ImageIcon [][] cardImg;
  // ImageIcon cardBackImg;
   ImageIcon emptyImg;
   
   
   int playerID;
   String[] Name = new String[4];
   int roomNum;
   
   JTextArea userArea;
   JTextArea chatArea;
   JScrollPane sp;
   
   
   //JPanel = p1;
    JPanel[] cardPanel;
   JLabel[] pName; // �÷��̾� �̸�
   JLabel[] pCardNum; // ���� ī�� ����
   JLabel[] pCard = new JLabel[4]; // ImageIcon�� ������ label
   
   EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED); //�׵θ�
   LineBorder lb = new LineBorder(Color.YELLOW,3);
   
   JButton backButton;

   JButton readyButton = new JButton("�غ�");
   JButton exitButton = new JButton("������");
   
   GameRoom(){
      
      
     // setLayout(new GridLayout());
      URL url = getClass().getClassLoader().getResource("bord2.jpg");
      ImageIcon background = new ImageIcon(url);
      
      JPanel background1 = new JPanel() {
         public void paintComponent(Graphics g) {
            g.drawImage(background.getImage(), 0, 0, null);
            setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ�
            super.paintComponent(g);
            this.repaint();
         }
      };
      add(background1);
      background1.setLayout(null);

       cardImg = new ImageIcon[4][5];
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 5; j++) {
            url = getClass().getClassLoader().getResource(i + "" + j + ".png");
            cardImg[i][j] = new ImageIcon(url);
         }
      } 

      url = getClass().getClassLoader().getResource("CardBack.png");
     ImageIcon cardBackImg = new ImageIcon(url);


   //  background.add(cardBackImg);
     

/*           url2 = getClass().getClassLoader().getResource("CardBack.png");
      cardBackImg = new ImageIcon(url);

           url3 = getClass().getClassLoader().getResource("CardBack.png");
      cardBackImg = new ImageIcon(url);

           url4 = getClass().getClassLoader().getResource("CardBack.png");
      cardBackImg = new ImageIcon(url);

     Background1.add(url);
*/




      emptyImg = new ImageIcon();

        JPanel totalCardPanel = new JPanel();   
      //totalCardPanel.setLayout(new GridLayout(2, 2));
      cardPanel = new JPanel[4];

      pName = new JLabel[4];
      pCardNum = new JLabel[4];
      pCard = new JLabel[4];
      
      for (int i = 0; i < 4; i++) {
         cardPanel[i] = new JPanel();
         cardPanel[i].setLayout(null);

         pName[i] = new JLabel("player" + i, 0);
         pName[i].setForeground(Color.WHITE);
         pCardNum[i] = new JLabel("14��", 0);
         pCardNum[i].setForeground(Color.WHITE);
         pCard[i] = new JLabel(cardBackImg);

         pName[i].setBounds(50, 10, 110, 30);
         cardPanel[i].add(pName[i]);
         pCardNum[i].setBounds(160, 10, 60, 30);
         cardPanel[i].add(pCardNum[i]);
         pCard[i].setBounds(10, 25, 240, 270);
         cardPanel[i].add(pCard[i]);
         cardPanel[i].setBorder(eb);
         
         totalCardPanel.add(cardPanel[i]);

         cardPanel[i].setOpaque(false);
      }

        background1.add(totalCardPanel);

      totalCardPanel.setBackground(new Color(0x55000000, true));

         JPanel gameJp = new JPanel();
         gameJp.setLayout(null);
         JLabel info = new JLabel("*���� �˸�*"); // ���� �� ���� ���� �˸�
         info.setFont(new Font("Dialog", Font.BOLD, 20));
         info.setForeground(Color.WHITE);
         gameJp.add(info);
         gameJp.setOpaque(false);

         info.setBounds(10, 10, 490, 20);
         background1.add(gameJp);
         gameJp.setBounds(30, 20, 480, 635);

      
      setTitle("�Ҹ����� ����!");  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container cp =getContentPane();
      
      JPanel bP1 = new JPanel();   //�غ�,�������ư
      bP1.setLayout(new GridLayout(1,2));
      readyButton.setFont(new Font("Dialog", Font.PLAIN, 20));
      //readyButton.setEnabled(false);
      

      exitButton.setFont(new Font("Dialog", Font.PLAIN, 20));

      bP1.add(readyButton);
      bP1.add(exitButton);
            
      bP1.setBounds(800, 400 , 300 , 40); //������ġ,������ġ,���α���,���α���
      background1.add(bP1);

     ImageIcon BellIcon = new ImageIcon("image/Bell.PNG");   //�� ��ư
      ImageIcon ClickBellIcon = new ImageIcon("image/ClickBell.PNG");
      
      JButton bell = new JButton(BellIcon);
      bell.setPressedIcon(ClickBellIcon);

      bell.setBorderPainted(false);
      bell.setContentAreaFilled(false);
      bell.setFocusPainted(false);

      
      background1.add(bell);
      
      bell.setBounds(360,350,100,100);

     ImageIcon CardBackIcon1 = new ImageIcon("image/CardBack.PNG"); //������ ī�� ��ư
     ImageIcon CardBackIcon2 = new ImageIcon("image/CardBack.PNG");
     ImageIcon CardBackIcon3 = new ImageIcon("image/CardBack.PNG");
     ImageIcon CardBackIcon4 = new ImageIcon("image/CardBack.PNG");

      
      JButton cardBack1 = new JButton(CardBackIcon1);
     JButton cardBack2 = new JButton(CardBackIcon2);
     JButton cardBack3 = new JButton(CardBackIcon3);
     JButton cardBack4 = new JButton(CardBackIcon4);

      cardBack1.setBorderPainted(false);  ////��ư �ܰ��� ������
     cardBack2.setBorderPainted(false);
     cardBack3.setBorderPainted(false);
     cardBack4.setBorderPainted(false);

      cardBack1.setContentAreaFilled(false); //��ư ���� ä��� x
     cardBack2.setContentAreaFilled(false);
     cardBack3.setContentAreaFilled(false);
     cardBack4.setContentAreaFilled(false);

      cardBack1.setFocusPainted(false);  //��ư ���� ������ �׵θ� ��� x
     cardBack2.setFocusPainted(false);
     cardBack3.setFocusPainted(false);
     cardBack4.setFocusPainted(false);


      background1.add(cardBack1);
     background1.add(cardBack2);
     background1.add(cardBack3);
     background1.add(cardBack4);


      cardBack1.setBounds(250,100,170,250);
     cardBack2.setBounds(630,100,170,250);
     cardBack3.setBounds(250,480,170,250);
     cardBack4.setBounds(630,480,170,250); 


      JLabel scoreName1 = new JLabel();   //������ �̸�
      JLabel scoreName2 = new JLabel();
      JLabel scoreName3 = new JLabel();
      JLabel scoreName4 = new JLabel();
      
      
      
      scoreName1.setBounds(830, 500, 50, 50);
      scoreName2.setBounds(970, 500, 50, 50);
      scoreName3.setBounds(830,600,50,50);
      scoreName4.setBounds(970,600,50,50);
      
      
      scoreName1.setText("p1����");
      scoreName2.setText("p2����");
      scoreName3.setText("p3����");
      scoreName4.setText("p4����");
      background1.add(scoreName1);
      background1.add(scoreName2);
      background1.add(scoreName3);
      background1.add(scoreName4);
      
      JPanel userJP = new JPanel();       //ä�� �г�
      userJP.setLayout(new BorderLayout());
      chatArea = new JTextArea(1, 1);
      chatArea.setEditable(false);
      chatArea.setFont(new Font("Dialog", Font.BOLD, 15));
      
      sp = new JScrollPane(chatArea);
      
       JScrollPane sp = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
     JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
      userJP.add(sp, "Center");
      JTextField chatInput = new JTextField("");
      
      chatArea.setFont(new Font("Dialog", Font.BOLD, 15));
      chatArea.setForeground(Color.BLACK);
      
      chatInput.setForeground(Color.WHITE);
      chatArea.setOpaque(false);
      chatInput.setOpaque(false);
      
      sp.setOpaque(false);
      sp.getViewport().setOpaque(false);
      
      userJP.setBounds(800, 50 , 310, 298);
      userJP.add(chatInput, "South");
     userJP.setBackground(new Color(0x55000000, true));
      
      background1.add(userJP);
      
      setResizable(false);
      setSize(1140,780);
      setVisible(true);

   }

   public static void main(String[]args) {
            new GameRoom();
   }
   
   
}