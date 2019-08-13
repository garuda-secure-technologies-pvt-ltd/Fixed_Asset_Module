/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Networking;

import java.net.Socket;

/**
 *
 * @author swathi
 */
public class SocketInfo {
    private Socket s;
    private MsgDialog msg;
    private boolean dialogvisible;
    public SocketInfo(){
       s=null;
       msg=null;
       dialogvisible=true;
    }
    public Socket getSocket(){
      return s;
    }
    public void setSocket(Socket s){
        this.s=s;
    }
    public MsgDialog getMsgDialog(){
      return msg;
    }
    public void setMsgDialog(MsgDialog dialog){
      msg=dialog;
    }
    public void setDialogStatus(boolean status){
      dialogvisible=status;
    }
    public boolean getDialogStatus(){
      return dialogvisible;
    }
}
