package HalGal;

import java.io.*;
import java.net.*;

public class HGC extends Thread{    //클라에서 실행할 메소드 
   Socket s;
   int port = 3000;
   OutputStream os;
   InputStream is;
   DataInputStream dis;
   DataOutputStream dos;
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   String userId;
   String ip;
   
   StartUI ui;	// 스타트 창
   
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
         
         start(); //클라가 메세지받는거
         sendMsg(); 
         
      }catch(UnknownHostException uhe) {
         System.out.println("서버 접속 불가");
         connect();
      }catch(IOException ie) {
         connect();
      }
   }
   
   void inIP() { //ip입력받기
      try {
         ip = br.readLine();
         dos.writeUTF(ip);
         dos.flush();
      }catch(IOException ie) {
         ie.printStackTrace();
      }
   }
   void inID() { //유저 ID입력받는거    
      try {
         userId = br.readLine();
         dos.writeUTF(userId);
         dos.flush();
      }catch(IOException ie) {
         ie.printStackTrace();
      }
   }

   void sendMsg() { //메세지 보내기
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
   
   
   public void run() {  //메세지 받기 
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