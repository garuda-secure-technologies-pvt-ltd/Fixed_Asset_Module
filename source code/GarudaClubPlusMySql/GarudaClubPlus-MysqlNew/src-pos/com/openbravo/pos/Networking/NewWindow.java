/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Networking;

import com.openbravo.pos.forms.JRootApp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author swathi
 */
public class NewWindow implements Runnable{
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private String user;
    private MsgDialog dialog;
    private String client;
    private boolean exist;
   public NewWindow(Socket s,DataInputStream in,DataOutputStream out,String user,String client,boolean exist){
     this.s=s;
     this.in=in;
     this.out=out;
     this.user=user;
     this.client=client;
     this.exist=exist;
   }
    public void run() {
        int flag=0;
        if(exist==false)
        dialog=new MsgDialog(new JFrame(),false, s, in, out, user,client);
        else{
            SocketInfo sinfo=JRootApp.socketList.get(client);
            dialog=sinfo.getMsgDialog();
            if(sinfo.getDialogStatus())
                flag=1;
        }
        if(flag==0){
         dialog.setTitle("Chatting with "+client);
         dialog.showDialog(exist);
        }
    }
    public MsgDialog getdialog(){
       return dialog;
    }

}
