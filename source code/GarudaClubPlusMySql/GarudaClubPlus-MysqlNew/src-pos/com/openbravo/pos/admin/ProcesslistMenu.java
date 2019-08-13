/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.admin;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;
import com.openbravo.pos.sms.EmailMasterTableModel;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import java.util.Date;
import java.util.List;


/**
 *
 * @author dev3
 */
public class ProcesslistMenu extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    
    private AppView m_App;
    private ProcesslistTableModel Processlist_Table_Model;
    private ProcesslistTableModel Processlist_Table_Model2;
    public List<String> ActiveIpList = new ArrayList<String>();
    
    
    public ProcesslistMenu() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jButton1 = new javax.swing.JButton();
        Refresh_Button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        flushHost_button = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ipName_text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        desc_text = new javax.swing.JTextField();
        Add_ipaddress_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        deactivate_button = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(168, 22, 22));
        jLabel1.setText("Current Process List ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Kill Thread");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Refresh_Button.setText("Refresh List ");
        Refresh_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh_ButtonActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(228, 23, 23));
        jLabel2.setText("* Note :  Refresh the list before killing any thread.");

        flushHost_button.setForeground(new java.awt.Color(59, 69, 186));
        flushHost_button.setText("Flush Hosts");
        flushHost_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flushHost_buttonActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(228, 23, 23));
        jLabel6.setText("* Note :  Don't press \"Flush Hosts\" button unless it has been told .");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(Refresh_Button))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(flushHost_button, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Refresh_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(flushHost_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Process List", jPanel1);

        jLabel3.setText("IP Address Settings ");

        jLabel4.setText("IP Address/ Host Name :  ");

        jLabel5.setText("Description :  ");

        Add_ipaddress_button.setText("Add");
        Add_ipaddress_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_ipaddress_buttonActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setRowHeight(24);
        jScrollPane2.setViewportView(jTable2);

        deactivate_button.setText("Deactivate ");
        deactivate_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivate_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ipName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Add_ipaddress_button, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(deactivate_button)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ipName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(desc_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add_ipaddress_button))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deactivate_button)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add IP Adresss", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Refresh_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh_ButtonActionPerformed
        try{
           loaddata();
           
       } 
       catch(Exception ex){
           ex.printStackTrace();
       }
    }//GEN-LAST:event_Refresh_ButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
           int row=jTable1.getSelectedRow();
           String ProcessID = String.valueOf(Processlist_Table_Model.getTableModel().getValueAt(row, 0).toString());
           String HostNameGiven = String.valueOf(Processlist_Table_Model.getTableModel().getValueAt(row, 8).toString());
           String TimeofSeletedThread = String.valueOf(Processlist_Table_Model.getTableModel().getValueAt(row, 6).toString());
           
           int intTimeSeleted = Integer.parseInt(TimeofSeletedThread);
           
           
           if(ProcessID!=null){
               try {
                      int timeFlag = getMaxTimeOfsameHost(HostNameGiven,intTimeSeleted);
                      if(timeFlag==0){
                          
                        if(HostMatchingCount>1)  {
                        int killtherad =  new PreparedSentence(m_App.getSession(), "kill ? "
                                                                             , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                              (new Object[]{ ProcessID   });

                        JOptionPane.showMessageDialog(this, "Process Killed", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Host having single thread cannot be cancellled.", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                      
                      }
                      else{
                          JOptionPane.showMessageDialog(this, "Select Thread with maximum time. ", "Warning", JOptionPane.WARNING_MESSAGE);
                      }
                      
                      loaddata();
                      
                      
                      
               } catch (BasicException ex) {
                      Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
               
               
               
               
           }
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Add_ipaddress_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_ipaddress_buttonActionPerformed
       if(ipName_text.getText()!=null && ipName_text.getText().trim().length()>0){
           if(desc_text.getText()!=null && desc_text.getText().trim().length()>0){
                String ipaddress = ipName_text.getText().trim();
                String Description = desc_text.getText().trim();
           
                try{
                    
                    
                    
                    int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO iplist (ID ,ipaddress , description , ACTIVE , CRBY , CRDATE , CRHOST  ) VALUES (?,?,?,?,?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT , Datas.STRING,Datas.TIMESTAMP,Datas.STRING})                         
                                  ).exec(new Object[]{UUID.randomUUID().toString(), ipaddress, Description,1, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost()  });                                                                                                

                    
                    JOptionPane.showMessageDialog(this, "Successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    ipName_text.setText(null);
                    desc_text.setText(null);
                    loaddata();
                }
                catch(BasicException e){
                    e.printStackTrace();
                }
           
           
           }
           else{
               JOptionPane.showMessageDialog(this, "Enter Description ", "Warning", JOptionPane.INFORMATION_MESSAGE);
           }
       }
       else{
           JOptionPane.showMessageDialog(this, "Enter IP address / Host Name ", "Warning", JOptionPane.INFORMATION_MESSAGE);
       }
    }//GEN-LAST:event_Add_ipaddress_buttonActionPerformed

    private void deactivate_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_buttonActionPerformed
       if(jTable2.getSelectedRow()!=-1){
           int row=jTable2.getSelectedRow();
           
           String IpAddreddID = String.valueOf(Processlist_Table_Model2.getTableModel2().getValueAt(row, 3).toString());
           
           try {
                      int deactivate =  new PreparedSentence(m_App.getSession(), "update iplist set active=0 , deacby=?,deacdate=? , deachost=? where id=? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.TIMESTAMP,Datas.STRING,Datas.STRING })).exec
                                                                            (new Object[]{ m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),IpAddreddID   });
                  
               
                       
                      loaddata();
                      
                      JOptionPane.showMessageDialog(this, "Successfully Deactivated", "Success", JOptionPane.INFORMATION_MESSAGE);
                      
               } catch (BasicException ex) {
                      Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
           
           
           
       }
    }//GEN-LAST:event_deactivate_buttonActionPerformed

    private void flushHost_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flushHost_buttonActionPerformed
         try{
              
             int killtherad =  new PreparedSentence(m_App.getSession(), "flush hosts "
                          , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                          ();
             
             
              JOptionPane.showMessageDialog(this, "Successfully done", "Success", JOptionPane.INFORMATION_MESSAGE);    
             
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }//GEN-LAST:event_flushHost_buttonActionPerformed

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_ipaddress_button;
    private javax.swing.JButton Refresh_Button;
    private javax.swing.JButton deactivate_button;
    private javax.swing.JTextField desc_text;
    private javax.swing.JButton flushHost_button;
    private javax.swing.JTextField ipName_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "Mysql Processlist Menu";
    }

    public void activate() throws BasicException {
       
        //reset();
        
        Boolean flag=m_App.getAppUserView().getUser().hasPermission("IP address adding permission");
        if(flag){
            jTabbedPane1.setEnabledAt(1, true);
        }
        else{
            jTabbedPane1.setEnabledAt(1, false);
        }
        
        Boolean flag1=m_App.getAppUserView().getUser().hasPermission("Flush Hosts permission");
        if(flag1){
            flushHost_button.setEnabled(true);
        }
        else{
            flushHost_button.setEnabled(false);
        }
        
        
        
        
        loaddata();
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
    }

    public Object getBean() {
        return this;
    }
    
    
    public void loaddata() throws BasicException{
       try{
           
           
           
           
           
           ActiveIpList=new ArrayList<String>();
           ActiveIpList=getActiveIpAddressList();
           
           Processlist_Table_Model=Processlist_Table_Model.LoadProcessListAll(m_App,ActiveIpList);
           jTable1.setModel(Processlist_Table_Model.getTableModel());
           
           Processlist_Table_Model2=Processlist_Table_Model2.LoadIpAddressDetails(m_App);
           jTable2.setModel(Processlist_Table_Model2.getTableModel2());
           
           
           
           
           
       } 
       catch(Exception ex){
           ex.printStackTrace();
       }
       
    }

    
     public List getActiveIpAddressList() throws  BasicException{
       List<Object> Temp = new ArrayList();
        Temp =  new StaticSentence(m_App.getSession(), "SELECT ipaddress from iplist where active=1", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
        return Temp;              
    }
    int HostMatchingCount=0;
    public int getMaxTimeOfsameHost(String Name , int timeofseletedThread) throws BasicException{
        int flag=0;
        HostMatchingCount=0;
        for(int i=0;i<Processlist_Table_Model.getSize();i++){
           String HostNameGiven = String.valueOf(Processlist_Table_Model.getTableModel().getValueAt(i, 8).toString());
           if(Name.equals(HostNameGiven)){
               HostMatchingCount++;
               String temptime = String.valueOf(Processlist_Table_Model.getTableModel().getValueAt(i, 6).toString());
               int intTime= Integer.parseInt(temptime);
               if(timeofseletedThread<intTime){
                   flag=1;
                   break;
               }
               else{
                   
               }
               
           }
       }
        
        return flag;
        
    }

     
     
}
