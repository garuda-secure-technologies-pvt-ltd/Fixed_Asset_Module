package com.openbravo.pos.pda.dao;


import com.openbravo.pos.pda.exceptions.SerialConnectionException;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import javax.swing.JTextField;

/**
 *
 * @author swathi
 */
public class CardReader {

    private SerialParameters defaultParameters = new SerialParameters("COM1", 9600, 0, 0, 8, 1, 0);
    private static SerialConnection mySerial = null;
    JTextField text;
    JTextField fac;
    JTextField cardno;
    private CardSwipeNotifier cardSwipeNotifier;
    private boolean cardAccessOnly;

    public CardReader(){
    }
    //public CardReader(String portname,JTextField text,JTextField fac,JTextField cardno) {
    public CardReader(String portname, boolean cardAccessOnly) {
        this.cardAccessOnly = cardAccessOnly;
        // defaultParameters= new SerialParameters (portname,110,0,0,5,1,0);
        //defaultParameters= new SerialParameters ("COM1",9600,0,0,8,1,0);
        defaultParameters = new SerialParameters(portname, 9600, 0, 0, 8, 1, 0);
    // this.text=text;
    //this.fac=fac;*
    //his.cardno=cardno;
    }

    public void ConfigurePort() throws UnsupportedCommOperationException, PortInUseException, SerialConnectionException {
        if (mySerial == null || !mySerial.isOpen()) {
            mySerial = new SerialConnection(defaultParameters, text, fac, cardno, cardAccessOnly);
            if (mySerial != null) {
                mySerial.openConnection();
            }
        }

    }

    public String getData() {
        return mySerial.getIncommingString();
    }
    public void setData(String data) {
         mySerial.setIncommingString(data);
    }

    public SerialConnection getSerialConnection() {
        return mySerial;
    }

    public void closeconnection() {
        mySerial.closeConnection();
    }

    public CardSwipeNotifier getCardSwipeNotifier() {
        return cardSwipeNotifier;
    }

    public void setCardSwipeNotifier(CardSwipeNotifier cardSwipeNotifier) {
        //if (mySerial == null || !mySerial.isOpen()) {
        mySerial.setCardSwipeNotifier(cardSwipeNotifier);
    // }
    }
}

