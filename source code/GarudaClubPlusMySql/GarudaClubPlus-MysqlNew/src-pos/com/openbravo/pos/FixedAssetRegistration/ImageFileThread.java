/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
class ImageFileThread implements Runnable{
    
     public final static int FILE_SIZE = 6022386;
         String imagePath;
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        private final int senderPort = 8182;
        Socket connection;

    @Override
    public void run() {
        
        
         try {
             String hostAddress = "192.168.1.145";
             InetAddress address = null;
             
             address = InetAddress.getByName(hostAddress);
             connection = new Socket(address, senderPort);
             DataOutputStream DOS1 = new DataOutputStream(connection.getOutputStream());
             DOS1.writeUTF("quit");
             
             connection.close();
             // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         } catch (UnknownHostException ex) {
             Logger.getLogger(ImageFileThread.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(ImageFileThread.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
