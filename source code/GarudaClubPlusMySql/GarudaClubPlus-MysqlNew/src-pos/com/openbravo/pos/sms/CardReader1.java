/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sms;

import com.openbravo.pos.clubmang.ReadCard;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

/**
 *
 * @author swathi
 */
public class CardReader1 {
  private SerialParameters defaultParameters= new SerialParameters ("COM2",9600,0,0,8,1,0);
  SerialConnection mySerial =null;
  private double variant;
    
    public CardReader1(String portname,double value) {
        defaultParameters= new SerialParameters (portname,9600,0,0,8,1,1);
        variant=value;
    }
    public void ConfigurePort() throws UnsupportedCommOperationException, PortInUseException,  SerialConnectionException{
        mySerial =new SerialConnection (defaultParameters);
        mySerial.openConnection();
    }
    public void setCardRederPanelObject(ReadCard obj){
        mySerial.setCardRederPanelObject(obj);
    }
    public String getData(){
       return mySerial.getIncommingString();
    }
    public double getVariance(){
       return variant;
    }
   public void closeconnection(){
      mySerial.closeConnection();
   }


}
