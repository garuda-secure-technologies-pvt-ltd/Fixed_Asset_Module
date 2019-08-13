/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FinancialYearDefiner.java
 *
 * Created on Jul 21, 2009, 6:53:43 PM
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Networking.LoggedInUsers;
import com.openbravo.pos.Networking.NewWindow;
import com.openbravo.pos.Networking.SocketInfo;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.MemTypeTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.JRootApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class FinancialYearDefiner extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form FinancialYearDefiner */
    private AppView m_App;
    private MyList lmodel;
    private Socket s;
    private DataInputStream buf;
    private DataOutputStream out;
    private String ipaddr;
    private waitDialog w;
    public FinancialYearDefiner() {
        initComponents();
    }
    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
         jButton3.setEnabled(true);
         LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
         Map<String,GeneralSettingInfo> info=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
         
        // if(sdate.getText().length()>0 && edate.getText().length()>0)
             jButton3.setEnabled(false);
        // else
          //   jButton3.setEnabled(true);
         List<LoggedInUsers> obj= new StaticSentence(m_App.getSession(),
                      "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL "
                      ,null,new SerializerReadClass(LoggedInUsers.class)).list();
         if(obj.size()>0){
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            jButton4.setEnabled(true);
         }else
            jButton4.setEnabled(false);
         try{
            InetAddress iaddr=InetAddress.getLocalHost();
            ipaddr=iaddr.getHostAddress();
         }catch(Exception e){
            e.printStackTrace();
         }
         sdate.setText(info.get("Datestart").getValue());
         edate.setText(info.get("Dateend").getValue());

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
         jButton3.setText("Save");
         jButton5.setText("Refresh List");
         jButton4.setText("Send Message");
    }

    public Object getBean() {
        return this;
    }
    private void refresh(){
        try{
           List<LoggedInUsers> obj= new StaticSentence(m_App.getSession(),
                      "SELECT NAME,IPADDR,ID FROM PEOPLE WHERE LOGINTIME IS NOT NULL "
                      ,null,new SerializerReadClass(LoggedInUsers.class)).list();
           if(obj.size()>0){
            lmodel=new MyList(obj);
            jList1.setModel(lmodel);
            jButton4.setEnabled(true);
           }else
            jButton4.setEnabled(false);
        }catch(Exception e){
          e.printStackTrace();
        }
    }
    private void loadFinancialYear() {
        try{
            
           // startDateFromGT = (Date) Formats.DATE.parseValue(settings.get("Datestart").getValue());
  // endDateFromGT = (Date) Formats.DATE.parseValue(settings.get("Dateend").getValue());
            CloseFinancialYear.setStartDateFromGT((Date) Formats.DATE.parseValue(sdate.getText()));
            CloseFinancialYear.setEndDateFromGT((Date) Formats.DATE.parseValue(edate.getText()));
        Transaction t=new Transaction(m_App.getSession()) {
                @Override
                protected Object transact() throws BasicException {
                 if(JOptionPane.showConfirmDialog(null, "Do you want to load the new financial year ", null,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                   refresh();
                   if(lmodel.getSize()<=1){
                     if(sdate.getText().length()>0 && edate.getText().length()>0){
                                try {
                                   
                                    new PreparedSentence(m_App.getSession(), "UPDATE garudaconame.COMPANY SET flag=FALSE WHERE URL=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{m_App.getSession().getURL()});
                                    new PreparedSentence(m_App.getSession(), "UPDATE APPLICATIONS SET ACTIVE=FALSE ").exec();
                                    if (new PreparedSentence(m_App.getSession(), "UPDATE GENERALTABLE SET VALUE=? WHERE NAME LIKE 'Datestart' ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{sdate.getText()}) <= 0) {
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO GENERALTABLE (ID,NAME,VALUE) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), "Datestart", sdate.getText()});
                                    }
                                    if (new PreparedSentence(m_App.getSession(), "UPDATE GENERALTABLE SET VALUE=? WHERE NAME LIKE 'Dateend' ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{edate.getText()}) <= 0) {
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO GENERALTABLE (ID,NAME,VALUE) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), "Dateend", edate.getText()});
                                    }
                                    OpenFinancialYear.open(m_App);
                                    LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
                                   new PreparedSentence(m_App.getSession(), "UPDATE APPLICATIONS SET ACTIVE=TRUE").exec();
                                   // new PreparedSentence(m_App.getSession(), "UPDATE garudaconame.COMPANY SET flag=true ").exec();
                                    activate();
                                   new PreparedSentence(m_App.getSession(), "UPDATE garudaconame.COMPANY SET flag=true WHERE URL=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{m_App.getSession().getURL()});
                                    
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(FinancialYearDefiner.class.getName()).log(Level.SEVERE, null, ex);
                                    try {
                                        new PreparedSentence(m_App.getSession(), "UPDATE garudaconame.COMPANY SET flag=true WHERE URL=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{m_App.getSession().getURL()});
                                         new PreparedSentence(m_App.getSession(), "UPDATE APPLICATIONS SET ACTIVE=TRUE").exec();
                                   
                                    } catch (SQLException ex1) {
                                        Logger.getLogger(FinancialYearDefiner.class.getName()).log(Level.SEVERE, null, ex1);
                                    }
                                }

                   }else{
                      JOptionPane.showMessageDialog(null, "Please select a valid start and end date for the financial year", "Sorry cannot change the financial year", JOptionPane.INFORMATION_MESSAGE);
                   }
                 }else{
                      JOptionPane.showMessageDialog(null, "Please request the online users to close the application", "Sorry cannot change the financial year", JOptionPane.INFORMATION_MESSAGE);
                }
                 JOptionPane.showMessageDialog(null, "New Financial Year Sucessfully loaded", null, JOptionPane.INFORMATION_MESSAGE);
                 }
                 if(w!=null)
                    w.hideDialog();
                      return null;
                }
            };
           t.execute();
  //sameer:commented dis msz at placed it at line no 205
           //JOptionPane.showMessageDialog(this, "New Financial Year Sucessfully loaded", null, JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                if(w!=null)
                    w.hideDialog();
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sdate = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        edate = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Financial Period"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Start Date");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("End Date");
        jLabel2.setName("jLabel2"); // NOI18N

        sdate.setEditable(false);
        sdate.setName("sdate"); // NOI18N
        sdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdateActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        edate.setEditable(false);
        edate.setName("edate"); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edate)
                    .addComponent(sdate, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(55, 55, 55)
                        .addComponent(jButton3)))
                .addGap(105, 105, 105))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(edate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addGap(10, 10, 10))
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
                        .addComponent(jButton4)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         Date date;
        try {
            date = (Date) Formats.DATE.parseValue(sdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            if(cal.get(Calendar.DATE)==cal.getActualMinimum(Calendar.DATE)){
              String temp=Formats.DATE.formatValue(date);
              sdate.setText(temp);
              if(edate.getText().length()>0 && lmodel.getSize()<=1)
                 jButton3.setEnabled(true);
            }else{
               JOptionPane.showMessageDialog(this, "Start date must be any months first day", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          Date date;
        try {
            date = (Date) Formats.DATE.parseValue(edate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
           Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            if(cal.get(Calendar.DATE)==cal.getActualMaximum(Calendar.DATE)){
              String temp=Formats.DATE.formatValue(date);
              edate.setText(temp);
              if(edate.getText().length()>0 && lmodel.getSize()<=1)
                 jButton3.setEnabled(true);
            }else{
               JOptionPane.showMessageDialog(this, "End date must be any months last day", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
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
                   w.showDialog("Please wait.Loading New Financial Year...");
              
              
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       refresh();
    }//GEN-LAST:event_jButton5ActionPerformed

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
                      s=new Socket(user.getIPaddr(),Integer.parseInt(user.getSocketNo()));

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

    private void sdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField edate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField sdate;
    // End of variables declaration//GEN-END:variables

public static class AJBean implements SerializableRead, IKeyed {
        private String id;
    private String memid;
    private Timestamp date;
    private String transref;
    private String transno;
    private double amount;
    private Timestamp duedate;
    private Timestamp cleardate;
    private double balanceamount;
    private boolean adjusted;
    private String createdby;
    private String counter;
    private String narration;
    private String accountid;
    private String tid;
    private Timestamp dateofentry;
    private String  transtype;
    private String  paymentref;
    private Timestamp deactdate;
    private String deactby;
    private boolean active;
    private String deactref;

        
    
    
        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
memid = dr.getString(2);
date= dr.getTimestamp(3);
transref = dr.getString(4);
transno = dr.getString(5);
amount = dr.getDouble(6);
duedate = dr.getTimestamp(7);
cleardate = dr.getTimestamp(8);
balanceamount = dr.getDouble(9);
adjusted = dr.getBoolean(10);
createdby = dr.getString(11);
counter = dr.getString(12);
narration = dr.getString(13);
accountid = dr.getString(14);
tid = dr.getString(15);
dateofentry = dr.getTimestamp(16);
transtype = dr.getString(17);
paymentref = dr.getString(18);
deactdate = dr.getTimestamp(19);
deactby = dr.getString(20);
active = dr.getBoolean(21);
deactref = dr.getString(22);
            
            //To change body of generated methods, choose Tools | Templates.
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemid() {
            return memid;
        }

        public void setMemid(String memid) {
            this.memid = memid;
        }

        public Timestamp getDate() {
            return date;
        }

        public void setDate(Timestamp date) {
            this.date = date;
        }

        public String getTransref() {
            return transref;
        }

        public void setTransref(String transref) {
            this.transref = transref;
        }

        public String getTransno() {
            return transno;
        }

        public void setTransno(String transno) {
            this.transno = transno;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Timestamp getDuedate() {
            return duedate;
        }

        public void setDuedate(Timestamp duedate) {
            this.duedate = duedate;
        }

        public Timestamp getCleardate() {
            return cleardate;
        }

        public void setCleardate(Timestamp cleardate) {
            this.cleardate = cleardate;
        }

        public double getBalanceamount() {
            return balanceamount;
        }

        public void setBalanceamount(double balanceamount) {
            this.balanceamount = balanceamount;
        }

        public boolean isAdjusted() {
            return adjusted;
        }

        public void setAdjusted(boolean adjusted) {
            this.adjusted = adjusted;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getCounter() {
            return counter;
        }

        public void setCounter(String counter) {
            this.counter = counter;
        }

        public String getNarration() {
            return narration;
        }

        public void setNarration(String narration) {
            this.narration = narration;
        }

        public String getAccountid() {
            return accountid;
        }

        public void setAccountid(String accountid) {
            this.accountid = accountid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public Timestamp getDateofentry() {
            return dateofentry;
        }

        public void setDateofentry(Timestamp dateofentry) {
            this.dateofentry = dateofentry;
        }

        public String getTranstype() {
            return transtype;
        }

        public void setTranstype(String transtype) {
            this.transtype = transtype;
        }

        public String getPaymentref() {
            return paymentref;
        }

        public void setPaymentref(String paymentref) {
            this.paymentref = paymentref;
        }

        public Timestamp getDeactdate() {
            return deactdate;
        }

        public void setDeactdate(Timestamp deactdate) {
            this.deactdate = deactdate;
        }

        public String getDeactby() {
            return deactby;
        }

        public void setDeactby(String deactby) {
            this.deactby = deactby;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getDeactref() {
            return deactref;
        }

        public void setDeactref(String deactref) {
            this.deactref = deactref;
        }
        
        

        public Object getKey() {
            return this; //To change body of generated methods, choose Tools | Templates.
        }
    
}
    
    

}

/**
 *
 * @author user
 */
