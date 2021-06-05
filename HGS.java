package HalGal;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class HGS extends JFrame{

   int port = 3000;
   LinkedList<OCM> user_list = new LinkedList<OCM>();  // ����,���濡 ������ Ŭ��� ��� �÷���
   OCM ocm;

   ServerSocket ss;
   Socket s;
   
   JTextArea ta = new JTextArea(); //�������� �������°�
   JScrollPane scroll = new JScrollPane(ta);
   
   
   HGS(){ //����â �����°�

      setSize(450, 500);
      ta.setEditable(false);
      add(scroll);
      setTitle("Halli~ Galli Server");
      ta.append("�Ҹ����� ���� ����! \n");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   void startServer() {
      try {
         ss = new ServerSocket(port);

         while(true) {
            s = ss.accept();
            ocm = new OCM(this);
            user_list.add(ocm);
            ocm.start();
         }
      }catch(IOException ie){}
   }
   public static void main(String[] args) {
      new HGS().startServer();
   }
   
   
   class OCM extends Thread{ //�������� Ŭ������°� �����ִ°� ���ִ� ����Ŭ����
      HGS hgs;
      Socket s;
      String userID;
      
      InputStream is;
      OutputStream os;
      DataInputStream dis;
      DataOutputStream dos;
      
      OCM(HGS hgs){
         this.hgs = hgs;
         this.s = hgs.s;
         try {
            is = s.getInputStream();
            os = s.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
         }catch(IOException ie) {}
      }
      
      public void run() { // Ŭ�� ������ ���°� �����ִ� ������
         listen(); 
      }
      void listen() {  //�������� ǥ�õɰ� 
         try {
            userID = dis.readUTF();
            broadcast(userID + "�� ���� (�����ο� : "+hgs.user_list.size()+"��) \n");
            hgs.ta.append(userID + "�� ���� (�����ο� : "+hgs.user_list.size()+"��) \n");
         }catch(IOException ie) {
            hgs.user_list.remove(this);
            broadcast(userID +"�� ���� (�����ο� : "+hgs.user_list.size()+ "��) \n");
            hgs.ta.append(userID +"�� ���� (�����ο� : "+hgs.user_list.size()+ "��) \n");
         }finally {
            closeall();
         }
      }
      
      void broadcast(String msg) {    //Ŭ���̾�Ʈ���� msg�����°�
         try {
            for(OCM ocm: hgs.user_list) {
               ocm.dos.writeUTF(msg);
               ocm.dos.flush();
            }
         }catch(IOException ie) {}
      }
      void closeall() {
         try {
            if(dis != null) dis.close();
            if(dos != null) dos.close();
            if(is != null) is.close();
            if(os != null) os.close();
            if(s != null) s.close();
         }catch(IOException ie){}
      }
   }
}