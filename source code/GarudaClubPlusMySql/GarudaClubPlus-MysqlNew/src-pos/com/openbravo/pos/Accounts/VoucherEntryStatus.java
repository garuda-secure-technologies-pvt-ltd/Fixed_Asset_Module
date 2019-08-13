

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class VoucherEntryStatus extends javax.swing.JPanel  implements JPanelView,BeanFactoryApp{

    
    private AppView m_App;
    VoucherEntryStatusTablemodel VoucherEntryStatus_Table_model;
    VoucherEntryConfirmationTableModel VoucherEntryConfirmation_Table_Model2;
    
    public VoucherEntryStatus() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jScrollPane3 = new javax.swing.JScrollPane();
        narration_text = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        All_radio = new javax.swing.JRadioButton();
        receipt_radio = new javax.swing.JRadioButton();
        payment_radio = new javax.swing.JRadioButton();
        journal_radio = new javax.swing.JRadioButton();
        Contra_radio = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(124, 8, 8));
        jLabel1.setText("Voucher Limit Status ");

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

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        narration_text.setColumns(20);
        narration_text.setRows(5);
        jScrollPane3.setViewportView(narration_text);
        narration_text.setEditable(false);

        jLabel2.setText("Narration");

        jLabel3.setText("Filter By :");

        All_radio.setText("All");
        All_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                All_radioItemStateChanged(evt);
            }
        });

        receipt_radio.setText("Receipt Radio");
        receipt_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                receipt_radioItemStateChanged(evt);
            }
        });

        payment_radio.setText("Payment");
        payment_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                payment_radioItemStateChanged(evt);
            }
        });

        journal_radio.setText("Journal");
        journal_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                journal_radioItemStateChanged(evt);
            }
        });

        Contra_radio.setText("Contra");
        Contra_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Contra_radioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3)
                            .addGap(27, 27, 27)
                            .addComponent(All_radio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(receipt_radio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(payment_radio)
                            .addGap(18, 18, 18)
                            .addComponent(journal_radio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Contra_radio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(All_radio)
                            .addComponent(receipt_radio)
                            .addComponent(payment_radio)
                            .addComponent(journal_radio)
                            .addComponent(Contra_radio))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png")));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      jTable2.setVisible(false);
      All_radio.setSelected(true);
      narration_text.setText(null);
      try{  
        String user=m_App.getAppUserView().getUser().getName();
        VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingVouchers(m_App,user);
        jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
      }
      catch(BasicException e){
          
      }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTable1.getSelectedRow()!=-1){ 
             
              int Row_Count = jTable1.getSelectedRow();
              String Tid  = String.valueOf(VoucherEntryStatus_Table_model.getTableModel().getValueAt(Row_Count, 7).toString());  
             
              
              try{
                    new PreparedSentence(m_App.getSession(), "UPDATE voucherentry SET userconf=1  where tid=?", new SerializerWriteBasic(new Datas[]{Datas.STRING }), null).exec(new Object[]{Tid });
                    
                    
                 loaddata();
                    
              }
              catch(BasicException e){
                      Logger.getLogger(VoucherEntryStatus.class.getName()).log(Level.SEVERE, null, e);
              }
              
              
              
              
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTable2.setVisible(false);
         if(jTable1.getSelectedRow()!=-1){ 
             
              int Row_Count = jTable1.getSelectedRow();
              String Tid  = String.valueOf(VoucherEntryStatus_Table_model.getTableModel().getValueAt(Row_Count, 7).toString());  
              String Narration  = String.valueOf(VoucherEntryStatus_Table_model.getTableModel().getValueAt(Row_Count, 8).toString()); 
             try {
                      VoucherEntryConfirmation_Table_Model2  = VoucherEntryConfirmationTableModel.LoadAccountDetailsByTid(m_App, Tid);
                }
                catch (BasicException ex) {
                     Logger.getLogger(VoucherEntryConfirmation.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTable2.setVisible(true);
                jTable2.setModel(VoucherEntryConfirmation_Table_Model2.getTableModel2());
                narration_text.setText(Narration);
             
         }
    }//GEN-LAST:event_jTable1MouseClicked

    private void All_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_All_radioItemStateChanged
        if(All_radio.isSelected()){
           try{ 
                String user=m_App.getAppUserView().getUser().getName();   
                VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingVouchers(m_App,user);
                jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
           }
           catch(BasicException e) {
               e.printStackTrace();
           }
        }
    }//GEN-LAST:event_All_radioItemStateChanged

    private void receipt_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_receipt_radioItemStateChanged
        if(receipt_radio.isSelected()){
            try{ 
                String Type="Receipt";
                String user=m_App.getAppUserView().getUser().getName();   
                VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingReceiptVouchers(m_App,user,Type);
                jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
           }
           catch(BasicException e) {
               e.printStackTrace();
           }
        }
    }//GEN-LAST:event_receipt_radioItemStateChanged

    private void payment_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_payment_radioItemStateChanged
        if(payment_radio.isSelected()){
            try{ 
                String Type="Payments";
                String user=m_App.getAppUserView().getUser().getName();   
                VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingReceiptVouchers(m_App,user,Type);
                jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
           }
           catch(BasicException e) {
               e.printStackTrace();
           }
        }
    }//GEN-LAST:event_payment_radioItemStateChanged

    private void journal_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_journal_radioItemStateChanged
        if(journal_radio.isSelected()){
            try{ 
                String Type="Journal";
                String user=m_App.getAppUserView().getUser().getName();   
                VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingReceiptVouchers(m_App,user,Type);
                jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
           }
           catch(BasicException e) {
               e.printStackTrace();
           }
        }
    }//GEN-LAST:event_journal_radioItemStateChanged

    private void Contra_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Contra_radioItemStateChanged
        if(Contra_radio.isSelected()){
            try{ 
                String Type="Contra";
                String user=m_App.getAppUserView().getUser().getName();   
                VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingReceiptVouchers(m_App,user,Type);
                jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
           }
           catch(BasicException e) {
               e.printStackTrace();
           }
        }
    }//GEN-LAST:event_Contra_radioItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton All_radio;
    private javax.swing.JRadioButton Contra_radio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
       return "Voucher Limit Status";
    }

    public void activate() throws BasicException{
        
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
        
        ButtonGrp();
        String user=m_App.getAppUserView().getUser().getName();
        VoucherEntryStatus_Table_model  = VoucherEntryStatusTablemodel.LoadPendingVouchers(m_App,user);
        jTable1.setModel(VoucherEntryStatus_Table_model.getTableModel()); 
        
        jTable2.setVisible(false);
        narration_text.setText(null);
        All_radio.setSelected(true);
      
       
    }

 public void ButtonGrp(){
     ButtonGroup bg = new ButtonGroup();
     bg.add(All_radio);
     bg.add(receipt_radio);
     bg.add(payment_radio);
     bg.add(journal_radio);
     bg.add(Contra_radio);
 }


}
