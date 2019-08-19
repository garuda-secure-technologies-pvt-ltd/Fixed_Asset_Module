/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.admin;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.pos.forms.CardSwipeNotifier;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.forms.WaiterInfoForCardAccess;
import com.openbravo.pos.mant.WaiterInfo;
import gnu.io.CommPortIdentifier;
import gnu.io.CommPortOwnershipListener;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
//import javax.comm.*;
import gnu.io.UnsupportedCommOperationException;
import java.io.*;
import java.awt.TextArea;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author swathi
 */
public class SerialConnection implements SerialPortEventListener,
        CommPortOwnershipListener {
//    private SerialDemo parent;

    /*    private TextArea messageAreaOut;
    private TextArea messageAreaIn;
     */
    private SerialParameters parameters;
    private OutputStream os;
    private InputStream is;
    private KeyHandler keyHandler;
    private CommPortIdentifier portId;
    private SerialPort sPort;
    private boolean open;
    private static String receptionString = "";
    private JTextField text;
    JTextField fac;
    JTextField cardno;
    private CardSwipeNotifier cardSwipeNotifier = null;
    private WaiterInfo waiterinfo;
    private DataLogicSales dlsales;
    private boolean cardAccessOnly;
    private Session session;
    private WaiterInfoForCardAccess winfo;
    // private ReadCard rcard;

    public CardSwipeNotifier getCardSwipeNotifier() {
        return cardSwipeNotifier;
    }

    public void setCardSwipeNotifier(CardSwipeNotifier cardSwipeNotifier) {
        this.cardSwipeNotifier = cardSwipeNotifier;
    }

    public String getIncommingString() {
        byte[] bVal = receptionString.getBytes();
        receptionString = "";
        return new String(bVal);
    }

    public void setIncommingString(String value) {
        receptionString = value;

    }
    // public void setCardRederPanelObject(ReadCard obj){
    //  rcard=obj;
    //  }

    /**
     * Creates a SerialConnection object and initilizes variables passed in as
     * params.
     *
     * @param parent A SerialDemo object.
     * @param parameters A SerialParameters object.
     * @param messageAreaOut The TextArea that messages that are to be sent out
     * of the serial port are entered into.
     * @param messageAreaIn The TextArea that messages comming into the serial
     * port are displayed on.
     */
    public SerialConnection(SerialParameters parameters, JTextField text, JTextField fac, JTextField cardno, boolean cardAccessOnly) {
        this.parameters = parameters;
        this.text = text;
        this.fac = fac;
        this.cardno = cardno;
        open = false;
        this.cardAccessOnly = cardAccessOnly;
    }

    /**
     * Attempts to open a serial connection and streams using the parameters in
     * the SerialParameters object. If it is unsuccesfull at any step it returns
     * the port to a closed state, throws a
     * <code>SerialConnectionException</code>, and returns.
     *
     * Gives a timeout of 30 seconds on the portOpen to allow other applications
     * to reliquish the port if have it open and no longer need it.
     */
    public void openConnection() throws SerialConnectionException, PortInUseException, UnsupportedCommOperationException {

        
//        try {
//            
//            portId = CommPortIdentifier.getPortIdentifier(parameters.getPortName());
//            
//
//        } catch (NoSuchPortException e) {
//           
//            e.printStackTrace();
//            
//        } catch (Exception e) {
//            
//            e.printStackTrace();
//        }
//     
//        try {
//            // sPort.close();
//            sPort = (SerialPort) portId.open("RFID", 30000);
//
//
//        } catch (PortInUseException e) {
//
//            throw new SerialConnectionException(e.getMessage());
//        }
//        
//        try {
//            setConnectionParameters();
//        } catch (SerialConnectionException e) {
//            sPort.close();
//            throw e;
//        }
//        
//        try {
//            os = sPort.getOutputStream();
//            is = sPort.getInputStream();
//
//        } catch (IOException e) {
//            sPort.close();
//            e.printStackTrace();
//            throw new SerialConnectionException("Error opening i/o streams");
//        }
//
//        try {
//            sPort.addEventListener(this);
//        } catch (TooManyListenersException e) {
//            sPort.close();
//            throw new SerialConnectionException("too many listeners added");
//        }
//        sPort.notifyOnDataAvailable(true);
//
//       
//        sPort.notifyOnBreakInterrupt(true);
//
//        sPort.notifyOnCarrierDetect(true);
//        sPort.notifyOnCTS(true);
//        sPort.notifyOnDSR(true);
//        sPort.notifyOnFramingError(true);
//        sPort.notifyOnOverrunError(true);
//        sPort.notifyOnParityError(true);
//        sPort.notifyOnRingIndicator(true);
//        sPort.sendBreak(10000);
//        try {
//            sPort.enableReceiveTimeout(30);
//        } catch (UnsupportedCommOperationException e) {
//        }
//        portId.addPortOwnershipListener(this);
//
//        open = true;
    }

   
    public void setConnectionParameters() throws SerialConnectionException {

        // Save state of parameters before trying a set.
        int oldBaudRate = sPort.getBaudRate();
        int oldDatabits = sPort.getDataBits();
        int oldStopbits = sPort.getStopBits();
        int oldParity = sPort.getParity();
        int oldFlowControl = sPort.getFlowControlMode();

        
        try {
            sPort.setSerialPortParams(parameters.getBaudRate(),
                    parameters.getDatabits(),
                    parameters.getStopbits(),
                    parameters.getParity());
        } catch (UnsupportedCommOperationException e) {
            parameters.setBaudRate(oldBaudRate);
            parameters.setDatabits(oldDatabits);
            parameters.setStopbits(oldStopbits);
            parameters.setParity(oldParity);
            throw new SerialConnectionException("Unsupported parameter");
        }

        try {
            sPort.setFlowControlMode(parameters.getFlowControlIn() | parameters.getFlowControlOut());
        } catch (UnsupportedCommOperationException e) {
            throw new SerialConnectionException("Unsupported flow control");
        }
    }

    
    public void closeConnection() {
        // If port is alread closed just return.
        if (!open) {
            return;
        }

     
        if (sPort != null) {
            try {
                // close the i/o streams.
                os.close();
                is.close();
            } catch (IOException e) {
                System.err.println(e);
            }

            sPort.close();

            portId.removePortOwnershipListener(this);
        }

        open = false;
    }

    /**
     * Send a one second break signal.
     */
    public void sendBreak() {
        sPort.sendBreak(1000);
    }

    /**
     * Reports the open status of the port.
     *
     * @return true if port is open, false if port is closed.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Handles SerialPortEvents. The two types of SerialPortEvents that this
     * program is registered to listen for are DATA_AVAILABLE and BI. During
     * DATA_AVAILABLE the port buffer is read until it is drained, when no more
     * data is availble and 30ms has passed the method returns. When a BI event
     * occurs the words BREAK RECEIVED are written to the messageAreaIn.
     */
    public static byte reverseBits(byte in) {
        byte out = 0;
        for (int ii = 0; ii < 8; ii++) {
            //System.out.print((byte)(in << 1));
            byte bit = (byte) (in & 1);
            out = (byte) ((out << 1) | bit);
            in = (byte) (in >> 1);
        }
        return out;
    }

    public void serialEvent(SerialPortEvent e) {
        StringBuffer inputBuffer = new StringBuffer();
        int newData = 0;

        // Determine type of event.
        // System.out.println(e.getEventType() + " : " + e.getNewValue() + " : " + e.getOldValue() );
        switch (e.getEventType()) {

            // Read data until -1 is returned. If \r is received substitute
            // \n for correct newline handling.
            case SerialPortEvent.DATA_AVAILABLE:
                System.out.println("Data");
                //   String a=null,b="",c="";
                /*   try {
                while(is.available() >0) {
                // Read the RFID data and store in the byte array
                is.read(rbuffer);
                // System.out.println("Number of Bytes read: " + numbyte);
                }
                } catch (IOException e1) {}
                int length = rbuffer[1];

                for( int m = 1; m <11; m--)
                System.out.print(Integer.toHexString(rbuffer[m] & 255));
                 */
                // byte[] readBuffer = new byte[20];
                /*   try {
                while(is.available() >0) {
                is.read(rbuffer);
                }
                }catch(Exception e1){
                }*/

                try {
                    int i = 0, j = 0;
                    byte[] b = new byte[30];
                    // is.read(b);
                    //String.
                    while (newData != -1) {
                        //  b=new byte[30];
                        // is.read(b);

                        newData = is.read();
                        char data = (char) newData;
                        // inputBuffer.append(data);
//                 if(newData!=-1){
//                 if(newData!=2 && newData!=3 && newData!=10 && newData!=13){
                        inputBuffer.append(data);
//                 }
//                 }

                        //  j++;
                        i++;
                    }
                    //Arun
                    inputBuffer.deleteCharAt(0);
                    int val = inputBuffer.charAt(11);
                    inputBuffer.delete(val, inputBuffer.length());

                } catch (IOException ex) {
                    Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
                receptionString = new String(inputBuffer);
                System.out.println(receptionString);
                //praveen:waiter kiosk
                try {
                    boolean flag = cardAccessOnly;
                    if (flag && cardSwipeNotifier != null) {
                        if (LookupUtilityImpl.getInstance(null).getAppView().getAppUserView() == null) {

                            waiterinfo = (WaiterInfo) new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "SELECT W.ID, W.NAME,W.COUNTER,P.NAME FROM WAITER W,PEOPLE P WHERE P.ROLE=W.COUNTER AND W.CARDNO = ? AND P.VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(receptionString);
                            if (waiterinfo != null) {
                                cardSwipeNotifier.cardswiped(waiterinfo);
                            } else {
                                cardSwipeNotifier.cardswiped(receptionString);
                            }
                        } else {
                            cardSwipeNotifier.cardswiped(receptionString);
                        }
                    } else if (cardSwipeNotifier != null) {
                        cardSwipeNotifier.cardswiped(receptionString);
                    }
                } catch (BasicException ex) {
                    ex.printStackTrace();
                }

                int j = Integer.parseInt(receptionString);
                String facno = String.valueOf((j - (j % (256 * 256))) / (256 * 256));
                fac.setText(facno);
                int k = Integer.parseInt(facno);
                String cardno1 = String.valueOf(j - k * 256 * 256);
                cardno.setText(cardno1);
                /*   String temp="";
                for(int i=0;i<receptionString.length();i++){
                temp+=j%2;
                j=j/2;
                }
                int maxBytes = 3;
                //  do {
                try {
                // System.out.print("Type the number to parse: ");
                int number = Integer.parseInt(receptionString);
                int Bit;
                String result = "";
                for (int i = maxBytes*8; i >= 0; i--) {
                Bit = 1 << i;
                if (number >= Bit) {
                result += 1;
                number -= Bit;
                }
                else {
                result += 0;
                }
                }
                System.out.println(result);
                }
                catch (NumberFormatException e1) {
                // System.exit(0);
                }*/

                //  }
                //  while (true);
                text.setText(receptionString);
                //this.getIncommingString();
                //rcard.execute(receptionString);
                //  System.out.println("Data");
                break;

            case SerialPortEvent.BI:

                try {
                    int p = 0, q = 0;
                    byte[] b = new byte[30];
                    // is.read(b);
                    //String.
                    while (newData != -1) {
                        //  b=new byte[30];
                        // is.read(b);

                        newData = is.read();
                        char data = (char) newData;
                        // inputBuffer.append(data);
                        //  if(newData!=-1){
                        //  if(newData!=2 && newData!=3 && newData!=10 && newData!=13){
                        inputBuffer.append(data);
                        //   }
                        //   }

                        //  j++;
                        p++;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
                receptionString = new String(inputBuffer);
                System.out.println(receptionString);
                System.out.println("BI");
                break;
            case SerialPortEvent.CD:
                System.out.println("CD");
                break;

            case SerialPortEvent.CTS:
                System.out.println("CTS");
                break;

            case SerialPortEvent.DSR:
                System.out.println("DSR");
                break;

            case SerialPortEvent.FE:
                System.out.println("FE");
                break;

            case SerialPortEvent.OE:
                System.out.println("OE");
                break;

            case SerialPortEvent.PE:
                System.out.println("PE");
                break;
            case SerialPortEvent.RI:
                System.out.println("RI");
                break;
        }

    }

    /**
     * Handles ownership events. If a PORT_OWNERSHIP_REQUESTED event is received
     * a dialog box is created asking the user if they are willing to give up
     * the port. No action is taken on other types of ownership events.
     */
    public void ownershipChange(int type) {
        /*
        if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
        PortRequestedDialog prd = new PortRequestedDialog(parent);
        }
         */
    }

    /**
     * A class to handle <code>KeyEvent</code>s generated by the messageAreaOut.
     * When a <code>KeyEvent</code> occurs the <code>char</code> that is
     * generated by the event is read, converted to an <code>int</code> and
     * writen to the <code>OutputStream</code> for the port.
     */
    class KeyHandler extends KeyAdapter {

        OutputStream os;

        /**
         * Creates the KeyHandler.
         *
         * @param os The OutputStream for the port.
         */
        public KeyHandler(OutputStream os) {
            super();
            this.os = os;
        }

        /**
         * Handles the KeyEvent. Gets the <code>char</char> generated by the
         * <code>KeyEvent</code>, converts it to an <code>int</code>, writes it
         * to the <code>
         * OutputStream</code> for the port.
         */
        public void keyTyped(KeyEvent evt) {
            char newCharacter = evt.getKeyChar();
            if ((int) newCharacter == 10) {
                newCharacter = '\r';
            }
            System.out.println((int) newCharacter);
            try {
                os.write((int) newCharacter);
            } catch (IOException e) {
                System.err.println("OutputStream write error: " + e);
            }
        }
    }

    public void send(String message) {
        byte[] theBytes = (message + "\n").getBytes();
        for (int i = 0; i < theBytes.length; i++) {

            char newCharacter = (char) theBytes[i];
            if ((int) newCharacter == 10) {
                newCharacter = '\r';
            }

            try {
                os.write((int) newCharacter);
            } catch (IOException e) {
                System.err.println("OutputStream write error: " + e);
            }

        }
        //System.out.println (">'" +message +"' sent");

    }
}
