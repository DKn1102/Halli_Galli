package HalGal;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class HGS extends JFrame{

   int port = 3000;
   LinkedList<OCM> user_list = new LinkedList<OCM>();  // 서버,대기방에 들어오는 클라들 담는 컬렉션
   OCM ocm;

   ServerSocket ss;
   Socket s;
   
   JTextArea ta = new JTextArea(); //서버에서 말나오는거
   JScrollPane scroll = new JScrollPane(ta);
   
   
   HGS(){ //서버창 나오는거

      setSize(450, 500);
      ta.setEditable(false);
      add(scroll);
      setTitle("Halli~ Galli Server");
      ta.append("할리갈리 서버 오픈! \n");
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
   
   
   class OCM extends Thread{ //서버에서 클라들어오는거 말해주는거 해주는 내부클래스
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
      
      public void run() { // 클라 나가고 들어가는거 말해주는 쓰레드
         listen(); 
      }
      void listen() {  //서버에서 표시될거 
         try {
            userID = dis.readUTF();
            broadcast(userID + "님 입장 (서버인원 : "+hgs.user_list.size()+"명) \n");
            hgs.ta.append(userID + "님 입장 (서버인원 : "+hgs.user_list.size()+"명) \n");
         }catch(IOException ie) {
            hgs.user_list.remove(this);
            broadcast(userID +"님 퇴장 (서버인원 : "+hgs.user_list.size()+ "명) \n");
            hgs.ta.append(userID +"님 퇴장 (서버인원 : "+hgs.user_list.size()+ "명) \n");
         }finally {
            closeall();
         }
      }
      
      void broadcast(String msg) {    //클라이언트에게 msg보내는거
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