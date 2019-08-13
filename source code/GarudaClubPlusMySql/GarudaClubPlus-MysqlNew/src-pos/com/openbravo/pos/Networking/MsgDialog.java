/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MsgDialog.java
 *
 * Created on Jul 29, 2009, 3:01:03 PM
 */

package com.openbravo.pos.Networking;

import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.pos.forms.JRootApp;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author swathi
 */
public class MsgDialog extends javax.swing.JDialog {

     /** Creates new form MsgDialog */
    private DefaultListModel listModel;
   // private boolean flag=true;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Map<String,Socket> userSocketMap=new HashMap();
    private String user;
    private MsgDialog dialogdup;
    private String client;
    public MsgDialog(java.awt.Frame parent, boolean modal,Socket s,DataInputStream buf,DataOutputStream out,String user,String client) {
        super(parent, modal);
        initComponents();
        listModel=new DefaultListModel();
        socket=s;
        in=buf;
        this.out=out;
        this.user=user;
        this.client=client;
    }
    public MsgDialog(java.awt.Dialog parent, boolean modal,Socket s,DataInputStream buf,DataOutputStream out,String user,String client) {
        super(parent, modal);
        initComponents();
        listModel=new DefaultListModel();
        socket=s;
        in=buf;
        this.out=out;
        this.user=user;
        this.client=client;
    }
    public void updateChat(String msgLine,String client){
        SocketInfo sinfo=JRootApp.socketList.get(client);
        boolean flag=sinfo.getDialogStatus();
        if(flag==false){
            MsgDialog d=sinfo.getMsgDialog();
            dialogdup=d;
            sinfo.setDialogStatus(true);
            JRootApp.socketList.remove(client);
            JRootApp.socketList.put(client,sinfo);
            //d.setVisible(true);
            /*d.message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                messageEntered(e);
            }
            });
            d.setVisible(true);*/
            jList1=d.jList1;
            message=d.message;
            listModel=d.listModel;
            listModel.addElement(msgLine);
            jList1.setModel(listModel);
            this.setVisible(true);
            java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                message.requestFocus();
            }
        });

           // d.setVisible(true);
        }else{
            listModel.addElement(msgLine);
            jList1.setModel(listModel);
        }
        /*this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    out.writeUTF("user has logged off");
                } catch (IOException ex) {
                    Logger.getLogger(MsgDialog.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
         try{
           in.close();
          }catch(Exception e1){
              e1.printStackTrace();
          }
          try{
           out.close();
          }catch(Exception e1){
              e1.printStackTrace();
          }
         try{
           socket.close();
          }catch(Exception e1){
              e1.printStackTrace();
          }
               // try {
                  //  Thread.currentThread().sleep(10000);
                    dispose();
               // } catch (InterruptedException ex) {
               //     ex.printStackTrace();
              //  }
                    }
                });*/
    }

    public void showDialog(boolean exist){
      //  flag=false;
        dialogdup=this;
        message.setColumns(10);
        if(exist==false){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
        //  flag=true;
          SocketInfo sinfo=JRootApp.socketList.get(client);
          sinfo.setDialogStatus(false);
          sinfo.setMsgDialog(dialogdup);
          JRootApp.socketList.remove(client);
          JRootApp.socketList.put(client,sinfo);

         /* try{
           in.close();
          }catch(Exception e1){
              e1.printStackTrace();
          }
          try{
           out.close();
          }catch(Exception e1){
              e1.printStackTrace();
          }
          try{
           socket.close();
          }catch(Exception e1){
              e1.printStackTrace();
          }*/
          dispose();
          }
          });
        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                messageEntered(e);
            }
        });
       // flag=false;

         Reader r=new Reader(socket, in,client);
        Thread t=new Thread(r);
        t.start();
        }

       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                message.requestFocus();
            }
        });
        setVisible(true);
    }
    private void messageEntered(KeyEvent e){
       if(e.getKeyChar()==KeyEvent.VK_ENTER){
            try {
                String value = message.getText();
                out.writeUTF(user+" : "+value);
                updateChat("me : " + value,client);
                message.setText(null);
            }
            catch (IOException ex) {
                ex.printStackTrace();
                JRootApp.socketList.remove(client);
                dispose();
                //Logger.getLogger(MsgDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }

    private class Reader implements Runnable{
        private Socket socket;
        private DataInputStream in;
        private String client;
        public Reader(Socket socket,DataInputStream r,String client) {
            this.socket=socket;
            this.in=r;
            this.client=client;
        }

        public void run() {
            try {
              while(true){
                String data=null;
                data=in.readUTF();
               // System.out.println(data);
                updateChat(data,client);
              }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        @Override
        public void finalize(){
         try{
          // in.close();
          }catch(Exception e){
          }


        }
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Chat detail");
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Message");
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        message.setColumns(20);
        message.setLineWrap(true);
        message.setRows(5);
        message.setName("message"); // NOI18N
        jScrollPane2.setViewportView(message);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea message;
    // End of variables declaration//GEN-END:variables

}
