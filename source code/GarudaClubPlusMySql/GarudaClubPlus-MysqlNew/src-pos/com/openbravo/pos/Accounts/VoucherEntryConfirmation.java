
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;


import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.util.List;
import javax.swing.JFrame;
import java.util.Date;
import java.util.Calendar;

public class VoucherEntryConfirmation extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

     private AppView m_App;
     VoucherEntryConfirmationTableModel VoucherEntryConfirmation_Table_Model;
     VoucherEntryConfirmationTableModel VoucherEntryConfirmation_Table_Model2;
     
    public VoucherEntryConfirmation() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        all_radio = new javax.swing.JRadioButton();
        receipt_radio = new javax.swing.JRadioButton();
        payment_radio = new javax.swing.JRadioButton();
        journal_radio = new javax.swing.JRadioButton();
        contra_radio = new javax.swing.JRadioButton();
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        narration_text = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(114, 15, 15));
        jLabel1.setText("Voucher Limit Release");

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Allow ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reject");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(74, 20, 129));
        jLabel2.setText("Filter By  :  ");

        all_radio.setForeground(new java.awt.Color(14, 48, 227));
        all_radio.setText("All");
        all_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                all_radioItemStateChanged(evt);
            }
        });

        receipt_radio.setForeground(new java.awt.Color(18, 32, 200));
        receipt_radio.setText("Receipt");
        receipt_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                receipt_radioItemStateChanged(evt);
            }
        });

        payment_radio.setForeground(new java.awt.Color(26, 79, 211));
        payment_radio.setText("Payment");
        payment_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                payment_radioItemStateChanged(evt);
            }
        });

        journal_radio.setForeground(new java.awt.Color(26, 45, 217));
        journal_radio.setText("Journal");
        journal_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                journal_radioItemStateChanged(evt);
            }
        });

        contra_radio.setForeground(new java.awt.Color(16, 53, 202));
        contra_radio.setText("Contra");
        contra_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                contra_radioItemStateChanged(evt);
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
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("Narration :");

        narration_text.setColumns(20);
        narration_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        narration_text.setRows(5);
        jScrollPane3.setViewportView(narration_text);
        narration_text.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(all_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(receipt_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(payment_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(journal_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(contra_radio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(all_radio)
                    .addComponent(receipt_radio)
                    .addComponent(payment_radio)
                    .addComponent(journal_radio)
                    .addComponent(contra_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void all_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_all_radioItemStateChanged
       jTable2.setVisible(false);
       narration_text.setText(null);
        if(all_radio.isSelected()){
           try{
                VoucherEntryConfirmation_Table_Model  = VoucherEntryConfirmationTableModel.LoadPendingVouchers(m_App);
                jTable1.setModel(VoucherEntryConfirmation_Table_Model.getTableModel()); 
        
                SetTableDimensions();
           }
           catch(BasicException e){
               Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, e);
           }
       }
    }//GEN-LAST:event_all_radioItemStateChanged

    private void receipt_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_receipt_radioItemStateChanged
       jTable2.setVisible(false);
       narration_text.setText(null);
        if(receipt_radio.isSelected()){
           try{
                VoucherEntryConfirmation_Table_Model  = VoucherEntryConfirmationTableModel.LoadPendingVouchersOnlyType(m_App,"Receipt");
                jTable1.setModel(VoucherEntryConfirmation_Table_Model.getTableModel()); 
        
                SetTableDimensions();
           }
           catch(BasicException e){
               Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, e);
           }
       }
    }//GEN-LAST:event_receipt_radioItemStateChanged

    private void payment_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_payment_radioItemStateChanged
       jTable2.setVisible(false);
       narration_text.setText(null);
        if(payment_radio.isSelected()){
           try{
                VoucherEntryConfirmation_Table_Model  = VoucherEntryConfirmationTableModel.LoadPendingVouchersOnlyType(m_App , "Payments");
                jTable1.setModel(VoucherEntryConfirmation_Table_Model.getTableModel()); 
        
                SetTableDimensions();
           }
           catch(BasicException e){
               Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, e);
           }
       }
    }//GEN-LAST:event_payment_radioItemStateChanged

    private void journal_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_journal_radioItemStateChanged
      jTable2.setVisible(false);
      narration_text.setText(null);
        if(journal_radio.isSelected()){
          try{
                VoucherEntryConfirmation_Table_Model  = VoucherEntryConfirmationTableModel.LoadPendingVouchersOnlyType(m_App,"Journal");
                jTable1.setModel(VoucherEntryConfirmation_Table_Model.getTableModel()); 
        
                SetTableDimensions();
           }
           catch(BasicException e){
               Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, e);
           }
      }
    }//GEN-LAST:event_journal_radioItemStateChanged

    private void contra_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_contra_radioItemStateChanged
       jTable2.setVisible(false);
       narration_text.setText(null);
        if(contra_radio.isSelected()){
          try{
                VoucherEntryConfirmation_Table_Model  = VoucherEntryConfirmationTableModel.LoadPendingVouchersOnlyType(m_App,"Contra");
                jTable1.setModel(VoucherEntryConfirmation_Table_Model.getTableModel()); 
        
                SetTableDimensions();
           }
           catch(BasicException e){
               Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, e);
           }
      }
    }//GEN-LAST:event_contra_radioItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTable2.setVisible(false);
         if(jTable1.getSelectedRow()!=-1){ 
             
              int Row_Count = jTable1.getSelectedRow();
              String Tid  = String.valueOf(VoucherEntryConfirmation_Table_Model.getTableModel().getValueAt(Row_Count, 7).toString());  
              String Narration  = String.valueOf(VoucherEntryConfirmation_Table_Model.getTableModel().getValueAt(Row_Count, 8).toString()); 
             try {
                      VoucherEntryConfirmation_Table_Model2  = VoucherEntryConfirmationTableModel.LoadAccountDetailsByTid(m_App, Tid);
                }
                catch (BasicException ex) {
                     Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTable2.setVisible(true);
                jTable2.setModel(VoucherEntryConfirmation_Table_Model2.getTableModel2());
                narration_text.setText(Narration);
                SetTableDimensions2();
             
         }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jTable2.setVisible(false);
         if(jTable1.getSelectedRow()!=-1){ 
             
              int Row_Count = jTable1.getSelectedRow();
              String Tid  = String.valueOf(VoucherEntryConfirmation_Table_Model.getTableModel().getValueAt(Row_Count, 7).toString());  
             
              try{
                    new PreparedSentence(m_App.getSession(), "UPDATE voucherentry SET approved=1 , approvedby=? , approveddate=? , approvedhost=? where tid=?", new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP ,  Datas.STRING , Datas.STRING}), null).exec(new Object[]{   m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(), Tid });
                    
                    
                    
                    JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    if(all_radio.isSelected()){
                        receipt_radio.setSelected(true);
                        all_radio.setSelected(true);
                    }
                    if(receipt_radio.isSelected()){
                        all_radio.setSelected(true);
                        receipt_radio.setSelected(true);
                    }
                    if(payment_radio.isSelected()){
                        all_radio.setSelected(true);
                        payment_radio.setSelected(true);
                    }
                    if(journal_radio.isSelected()){
                        all_radio.setSelected(true);
                        journal_radio.setSelected(true);
                    }
                    if(contra_radio.isSelected()){
                        all_radio.setSelected(true);
                        contra_radio.setSelected(true);
                    }
              }
              catch(BasicException e){
                   Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, e);
              }
              
              
              
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    List<String> AccountJourIDList = new ArrayList<String>();
    String TidTemp;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTable1.getSelectedRow()!=-1){ 
             
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Reject the entry  ? " , "Rejection" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
            
              int Row_Count = jTable1.getSelectedRow();
              TidTemp  = String.valueOf(VoucherEntryConfirmation_Table_Model.getTableModel().getValueAt(Row_Count, 7).toString());  
           try{    
              
              Object[] obj23 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM ACCOUNTJOURNALDUP where TID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(TidTemp);
              if(obj23==null){
                  
                             
                  Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                             
                      @Override      
                             
                      protected Object transact() throws BasicException {     
                  
                                    
                          AccountJourIDList = new ArrayList<String>();
                          AccountJourIDList = getAccountIdListByTID(m_App, TidTemp);
                                 
                                    
                          for(int i=0;i<AccountJourIDList.size();i++){
                              String AJID = AccountJourIDList.get(i).toString();
                                Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ACCOUNTID, AMOUNT , TRANSTYPE , DATE  FROM ACCOUNTJOURNAL where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.DOUBLE , Datas.STRING , Datas.TIMESTAMP })).find(AJID);
                                if(obj24!=null){
                                    String Acctid=obj24[0].toString();
                                    Double Amount=Double.parseDouble(obj24[1].toString());
                                    String Transtype=obj24[2].toString();
                                    Date EntryDate = (Date) obj24[3];
                                    Date LastDateOfMonth = new Date();
                                    
                                    
                                    
                                    Calendar cal=Calendar.getInstance();
                                    cal.setTimeInMillis(EntryDate.getTime());
                                    cal.set(Calendar.HOUR_OF_DAY, 00);
                                    cal.set(Calendar.MINUTE, 00);
                                    cal.set(Calendar.SECOND, 00);
                                    cal.set(Calendar.MILLISECOND, 00);
                                    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                                    LastDateOfMonth.setTime(cal.getTimeInMillis());
                        
                                    
                                    System.out.println(LastDateOfMonth);
                                    if(Transtype.equals("C")){
                                         new PreparedSentence(m_App.getSession(), "UPDATE trailbalance SET curcredit=curcredit-?  where accountid=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING}), null).exec(new Object[]{ Amount ,Acctid  });
                                        new PreparedSentence(m_App.getSession(), "UPDATE ajperiodtotals SET curcredit=curcredit-?  where accountid=? and edate=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING , Datas.TIMESTAMP}), null).exec(new Object[]{ Amount ,Acctid ,LastDateOfMonth });
                                    }
                                    if(Transtype.equals("D")){
                                        new PreparedSentence(m_App.getSession(), "UPDATE trailbalance SET curdebit=curdebit-?  where accountid=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING}), null).exec(new Object[]{ Amount ,Acctid  });
                                        new PreparedSentence(m_App.getSession(), "UPDATE ajperiodtotals SET curdebit=curdebit-?  where accountid=? and edate=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING , Datas.TIMESTAMP }), null).exec(new Object[]{ Amount ,Acctid ,LastDateOfMonth });
                                    }
                                    
                                     new PreparedSentence(m_App.getSession(), "UPDATE accountjournal SET active=0  where id=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), null).exec(new Object[]{ AJID  });
                                    
                                    
                                     
                                }
      
                          }
                      
                           new PreparedSentence(m_App.getSession(), "UPDATE voucherentry SET approved=2 , rejectby=? , rejectdate=? , rejecthost=? where tid=?", new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP ,  Datas.STRING , Datas.STRING}), null).exec(new Object[]{   m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(), TidTemp });
                                     
                                               
                                  
                          return null;                                      
                                   
                      }                            
                                 
                  };                 

                                 
                  try {                 
                                     
                      t.execute();          
                      JOptionPane.showMessageDialog(this, "Rejected Successfully..!", "Rejection", JOptionPane.INFORMATION_MESSAGE);
                        if(all_radio.isSelected()){
                        receipt_radio.setSelected(true);
                        all_radio.setSelected(true);
                        }
                        if(receipt_radio.isSelected()){
                            all_radio.setSelected(true);
                            receipt_radio.setSelected(true);
                        }
                        if(payment_radio.isSelected()){
                            all_radio.setSelected(true);
                            payment_radio.setSelected(true);
                        }
                        if(journal_radio.isSelected()){
                            all_radio.setSelected(true);
                            journal_radio.setSelected(true);
                        }
                        if(contra_radio.isSelected()){
                            all_radio.setSelected(true);
                            contra_radio.setSelected(true);
                        }
                  
                  
                  }
                              
                  catch (BasicException ex) {                    
                                         
                      Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);             
                      ex.printStackTrace();
                      new MessageInf(ex).show(new JFrame());
                  }  
                      
                      
                      
                  }
                  else{
                       JOptionPane.showMessageDialog(this, "Cannot Reject.! \n Still not approved by user.", "Error", JOptionPane.INFORMATION_MESSAGE);
                  }
           }
           catch (BasicException ex) {                    
                                         Logger.getLogger(EmailMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                         ex.printStackTrace();
                                         new MessageInf(ex).show(new JFrame());

                              }  
                      
             
             }
              
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton all_radio;
    private javax.swing.JRadioButton contra_radio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton journal_radio;
    private javax.swing.JTextArea narration_text;
    private javax.swing.JRadioButton payment_radio;
    private javax.swing.JRadioButton receipt_radio;
    // End of variables declaration//GEN-END:variables

 public String getTitle() {
       return "Voucher Limit Release";
    }

    public void activate() throws BasicException{
        ButtnGrp();
        all_radio.setSelected(true);
        jTable2.setVisible(false);
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
        VoucherEntryConfirmation_Table_Model  = VoucherEntryConfirmationTableModel.LoadPendingVouchers(m_App);
        jTable1.setModel(VoucherEntryConfirmation_Table_Model.getTableModel()); 
        
        SetTableDimensions();
       
    }
    
    public void ButtnGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(all_radio);
        bg.add(receipt_radio);
        bg.add(payment_radio);
        bg.add(journal_radio);
        bg.add(contra_radio);
        
    }
    
    public void SetTableDimensions(){
         
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
        jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(46);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(170);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(105);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(105);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(170);
        
    }
     public void SetTableDimensions2(){
         
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
          
        jTable2.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
        jTable2.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
       
        
        
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(250);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
       
        
    }
    
     
      
     public List getAccountIdListByTID(AppView app , String TID) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM ACCOUNTJOURNAL  WHERE ACTIVE=1 AND TID=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(TID);
          
          return Mem_list;
      }
     
    
}
