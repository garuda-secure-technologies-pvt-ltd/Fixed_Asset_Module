/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Networking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class LoggedInUsers implements SerializableRead {
    private String name;
    private String ipaddr;
    private String socketno;
    private String hostname;
    private String id;

    public void readValues(DataRead dr) throws BasicException {
       name=dr.getString(1);
       String temp=dr.getString(2);
       String[] tarr=temp.split(" : ");
       if(tarr.length>1){
         ipaddr=tarr[0];
         socketno=tarr[1];
       }else{
         ipaddr=temp;
         socketno=null;
       }
       hostname=ipaddr;
       id=dr.getString(3);
      /*  try {
            InetAddress addr = InetAddress.getByName(ipaddr);
            hostname=addr.getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoggedInUsers.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    public String getid(){
      return id;
    }
    public String getName(){
      return name;
    }
    public String getIPaddr(){
      return ipaddr;
    }
    public String getSocketNo(){
       return socketno;
    }
    public void setName(String name){
      this.name=name;
    }
    public String getHostName(){
       return hostname;
    }
    public void setHostName(String hname){
      hostname=hname;
    }
    @Override
    public String toString(){
      return name;
    }
}
