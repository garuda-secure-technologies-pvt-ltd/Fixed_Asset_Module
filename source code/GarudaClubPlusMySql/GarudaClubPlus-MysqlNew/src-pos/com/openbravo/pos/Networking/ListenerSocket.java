/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Networking;

//import desktopapplication1.DesktopApplication1View;
import com.openbravo.pos.forms.JRootApp;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author swathi
 */
public class ListenerSocket implements Runnable{
   // private MsgDialog messagedialog;
    private Socket s;
    private DataInputStream buf;
    private DataOutputStream out;
    private int port=0;
    private String user;
    public ListenerSocket(String value){
      user=value;
    }
    public void CreateServerSocket() throws IOException {
        ServerSocket ssocket=null;
        int value=4444;
        while(ssocket==null){
            try{
            ssocket=new ServerSocket(value);
            }catch(Exception e){
                value++;
              e.printStackTrace();
            }
        }
        port=value;
       //System.out.println("Server socket created");

       while(true && JRootApp.sSocketActive){
         s=ssocket.accept();
         if(JRootApp.sSocketActive){
           buf=new DataInputStream(s.getInputStream());
           out=new DataOutputStream(s.getOutputStream());
           String client="";
           boolean flag=false;
           try{
              client =buf.readUTF();
              SocketInfo sinfo=JRootApp.socketList.get(client);
              if(sinfo !=null){
                 sinfo.setSocket(s);
                 JRootApp.socketList.remove(client);
                 JRootApp.socketList.put(client,sinfo);
                 flag=true;
              }
           }catch(Exception e){
           }
           SocketInfo sinfo=new SocketInfo();
           sinfo.setSocket(s);
           NewWindow obj=new NewWindow(s,buf,out,user,client,flag);
           Thread t=new Thread(obj);
           t.start();
           sinfo.setMsgDialog(obj.getdialog());
           JRootApp.socketList.put(client, sinfo);
        }
        // ProcessRequest pr=new ProcessRequest(socket, breader, out);
       //  MsgDialog messagedialog=MsgDialog.getDialog(new JFrame(),socket,br,out,"UNKNOWN");
        // Thread t=new Thread(messagedialog);
        // t.start();
       }
       
       for(int i=0;i<1000000000;i++){
           
       }
       
        ssocket.close();
    }
    public int getPort(){
      return port;
    }
    public void run() {
        try {
            CreateServerSocket();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
  
}
