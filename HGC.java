package HalGal;

import java.io.*;
import java.net.*;

public class HGC extends Thread{    //Ŭ�󿡼� ������ �޼ҵ� 
   Socket s;
   int port = 3000;
   OutputStream os;
   InputStream is;
   DataInputStream dis;
   DataOutputStream dos;
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   String userId;
   String ip;
   
   StartUI ui;	// ��ŸƮ â
   
   HGC(){
      connect();
   }
   
   void connect() {
      try {
         s = new Socket(ip, port);
         is = s.getInputStream();
         os = s.getOutputStream();
         dis = new DataInputStream(is);
         dos = new DataOutputStream(os);
         inIP();
         inID();
         
         start(); //Ŭ�� �޼����޴°�
         sendMsg(); 
         
      }catch(UnknownHostException uhe) {
         System.out.println("���� ���� �Ұ�");
         connect();
      }catch(IOException ie) {
         connect();
      }
   }
   
   void inIP() { //ip�Է¹ޱ�
      try {
         ip = br.readLine();
         dos.writeUTF(ip);
         dos.flush();
      }catch(IOException ie) {
         ie.printStackTrace();
      }
   }
   void inID() { //���� ID�Է¹޴°�    
      try {
         userId = br.readLine();
         dos.writeUTF(userId);
         dos.flush();
      }catch(IOException ie) {
         ie.printStackTrace();
      }
   }

   void sendMsg() { //�޼��� ������
      try {
         while(true) {
            String msg = br.readLine();
            dos.writeUTF("["+userId+"] : "+msg);
            dos.flush();
         }
      }catch(IOException ie) {
      }finally {
         closeAll();
      }
   }
   void closeAll() {
      try {
         if(dis != null) dis.close();
         if(dos != null) dos.close();
         if(is != null) is.close();
         if(os != null) os.close();
         if(s != null) s.close();
      }catch(IOException ie) {}
   }
   
   
   public void run() {  //�޼��� �ޱ� 
      try {
         while(true) {   
            String msg = dis.readUTF();
            System.out.println(msg);
         }
      }catch(IOException ie) {
      }finally {
         closeAll();
      }
   }
   
   
   public static void main(String[] args) {
      new HGC();
   }

}