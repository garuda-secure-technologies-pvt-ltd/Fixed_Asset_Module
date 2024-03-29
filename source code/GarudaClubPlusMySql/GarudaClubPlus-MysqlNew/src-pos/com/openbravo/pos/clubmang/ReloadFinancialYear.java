/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CalFinancialYear.java
 *
 * Created on 24-Sep-2011, 16:31:33
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.Accounts.FinancialYearDefiner;
import com.openbravo.pos.Accounts.OpenFinancialYear;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.Networking.LoggedInUsers;
import com.openbravo.pos.Networking.NewWindow;
import com.openbravo.pos.Networking.SocketInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.JRootApp;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ReloadFinancialYear extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /** Creates new form CalFinancialYear */

    private AppView m_App;
    private waitDialog w;
    private MyList lmodel;
    private Socket s;
    private DataInputStream buf;
    private DataOutputStream out;
    private String ipaddr;
    public ReloadFinancialYear() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jButton1.setText("jButton1");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Online User List"));
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jLabel3.setText("List of users who are online or have improperly closed the application");
        jLabel3.setName("jLabel3"); // NOI18N

        jButton4.setText("jButton4");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("jButton5");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(11, 11, 11)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jButton1)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       w=new waitDialog(new JFrame(), true);
            int h=w.getSize().height;
           int w1=w.getSize().width;
           Toolkit toolkit = Toolkit.getDefaultToolkit();
		   Dimension scrnsize = toolkit.getScreenSize();
           w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
        Thread t=new Thread(
				new Runnable()
				{
					public void run()
					{
                                           loadFinancialYear();
					}
				}
			);
            t.start();
            w.showDialog("Please wait Reloading Financial Year...");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(jList1.getModel().getSize()>0){
            int row=jList1.getSelectedIndex();
            if(row>=0){
                LoggedInUsers user=(LoggedInUsers)lmodel.get(row);
                try{
                    boolean flag=false;
                    if(JRootApp.socketList.containsKey(user.getName())){
                        SocketInfo sinfo=JRootApp.socketList.get(user.getName());
                        s=sinfo.getSocket();
                        flag=true;
                    }else{
                        try {
                            s = new Socket(user.getIPaddr(), Integer.parseInt(user.getSocketNo()));
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(ReloadFinancialYear.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ReloadFinancialYear.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    buf=new DataInputStream(s.getInputStream());
                    out=new DataOutputStream(s.getOutputStream());
                    String uname=m_App.getAppUserView().getUser().getName();
                    if(flag==false)
                        out.writeUTF(uname);
                    NewWindow obj=new NewWindow(s, buf, out,uname,user.getName(),flag);
                    Thread t=new Thread(obj);
                    t.start();
                    if(flag==false){
                        SocketInfo sinfo=new SocketInfo();
                        sinfo.setSocket(s);
                        sinfo.setMsgDialog(obj.getdialog());
                        JRootApp.socketList.put(user.getName(), sinfo);
                    }
                }catch(UnknownHostException e){
                    lmodel.remove(row);
                    jList1.setModel(lmodel);
                    jList1.repaint();
                    try {
                        new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET LOGINTIME=?,IPADDR=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.NULL,Datas.NULL,Datas.STRING})).exec(new Object[]{null,null,user.getid()});
                    } catch (BasicException ex) {
                        Logger.getLogger(FinancialYearDefiner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "The user might have improperly closed the application","The System is turned off",JOptionPane.INFORMATION_MESSAGE);
                }catch(ConnectException e){
                    lmodel.remove(row);
                    jList1.setModel(lmodel);
                    jList1.repaint();
                    try {
                        new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET LOGINTIME=?,IPADDR=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.NULL,Datas.NULL,Datas.STRING})).exec(new Object[]{null,null,user.getid()});
                    } catch (BasicException ex) {
                        Logger.getLogger(FinancialYearDefiner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //this.revalidate();
                    JOptionPane.showMessageDialog(this, "The user might have improperly closed the application",null,JOptionPane.INFORMATION_MESSAGE);
                }catch(IOException e){
                    JOptionPane.showMessageDialog(this, "Error...");
                    e.printStackTrace();
                }
            }
        }
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        refresh();
}//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Reloading Financial Year";
    }

    public void activate() throws BasicException {       
        List<LoggedInUsers> obj= new StaticSentence(m_App.getSession(),
                      "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL "
                      ,null,new SerializerReadClass(LoggedInUsers.class)).list();
        if(obj.size()<=1){
            jButton4.setEnabled(true);
             }
        if(obj.size()>0){
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            
         }
       else
             jButton1.setEnabled(true);
            jButton4.setEnabled(false);
         try{
            InetAddress iaddr=InetAddress.getLocalHost();
            ipaddr=iaddr.getHostAddress();
         }catch(Exception e){
            e.printStackTrace();
         }

    }

        private class MyList extends DefaultListModel{
        private List<LoggedInUsers> data;
        public MyList(List<LoggedInUsers> list){
            data=list;
        }
        @Override
        public Object getElementAt(int index) {
            LoggedInUsers obj=data.get(index);
            String value=null;
            if(obj!=null){
            if(ipaddr.equals(obj.getIPaddr()))
                value=obj.getName()+"           "+"-"+"Same Machine";
            else
                value=obj.getName()+"           "+"-"+obj.getHostName();
            }
	        return value;
        }
        @Override
        public int getSize(){
          return data.size();
        }

        @Override
        public Object get(int index) {
            return data.get(index);
        }

        @Override
        public Object remove(int index) {
            return data.remove(index);
        }

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {

        m_App=app;
        jButton1.setText("Reload Financial Year");
        jButton4.setText("Send Message");
        jButton5.setText("Refresh List");
    }

    public Object getBean() {
        return this;
    }

    private void refresh(){
        try{
            //sameer added online users list
           List<LoggedInUsers> obj= new StaticSentence(m_App.getSession(),
                      "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL "
                      ,null,new SerializerReadClass(LoggedInUsers.class)).list();
           if(obj.size()>0){
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            jButton4.setEnabled(true);
           }else
            jButton4.setEnabled(false);
           if(lmodel.getSize()<=1)
               jButton1.setEnabled(true);
           else
               jButton1.setEnabled(false);
        }catch(Exception e){
          e.printStackTrace();
        }
    }
    private void loadFinancialYear() {
        try{
        Transaction t=new Transaction(m_App.getSession()) {
                @Override
                protected Object transact() throws BasicException {
                 if(JOptionPane.showConfirmDialog(null, "Do you want to reload the financial year ", null,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                  refresh();
                        try {
                            OpenFinancialYear.open(m_App);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            Logger.getLogger(ReloadFinancialYear.class.getName()).log(Level.SEVERE, null, ex);
                        }

                       JOptionPane.showMessageDialog(null, "Financial Year Sucessfully reloaded", null, JOptionPane.INFORMATION_MESSAGE);
                 }
                 if(w!=null)
                    w.hideDialog();
                      return null;
                }
            };
           t.execute();
           
            } catch (Exception e) {
                if(w!=null)
                    w.hideDialog();
            e.printStackTrace();
        }
    }

}
