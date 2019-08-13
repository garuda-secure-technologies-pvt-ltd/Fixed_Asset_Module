

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ReceiptToReceiptArvMenu extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    private AppView m_App;
   
   
    public ReceiptToReceiptArvMenu() {
        initComponents();
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        receipt_rows_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        receipt_arv_label = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(192, 10, 10));
        jLabel1.setText("Receipt To Receipt_arv Menu");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(18, 56, 230));
        jLabel2.setText("Receipt entries before selected date will be transfered to Receipt_arv. ");

        jLabel3.setText("Select Date : ");

        jButton1.setText("Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(220, 39, 39));
        jButton2.setText("Start Transfer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Total No. of rows (Receipt) upto selected date :  ");

        receipt_rows_label.setForeground(new java.awt.Color(18, 35, 239));
        receipt_rows_label.setText("jLabel5");

        jLabel5.setText("Total No. of rows in Receipt_arv table :  ");

        receipt_arv_label.setForeground(new java.awt.Color(22, 57, 214));
        receipt_arv_label.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(receipt_rows_label))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(receipt_arv_label)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(receipt_rows_label))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(receipt_arv_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        fromdate_text.setEditable(false);

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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

           Date CurrDate = new Date(); 
           if(CurrDate.after(date) ) {
            
            
            /// ********************************************************************************
                    fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
                    try{

                     Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT count(*) from receipts where datenew < ? and closecashseq is not null ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP }) , new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{ date });
                     if(obj2!=null){
                         int ReceiptCountBeforeDate =  Integer.parseInt(obj2[0].toString());
                         receipt_rows_label.setText(ReceiptCountBeforeDate+"");
                     }

                    
                     Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT count(*) from receipts_arv", SerializerWriteString.INSTANCE  , new SerializerReadBasic(new Datas[]{Datas.INT})).find();
                     if(obj3!=null){
                         int Receipt_arvCount = Integer.parseInt(obj3[0].toString());
                         receipt_arv_label.setText(Receipt_arvCount+"");
                     }

                    }
                    catch(BasicException e){
                        e.printStackTrace();
                         Logger.getLogger(ReceiptToReceiptArvMenu.class.getName()).log(Level.SEVERE, null, e);             
                             e.printStackTrace();
                             new MessageInf(e).show(new JFrame());
                    }
                    // ************************************************************************************************
           }
           else{
                JOptionPane.showMessageDialog(this, "Selected date should be before current date..! ");
                reset();
           }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
           

        try 
        {
           if (JOptionPane.showConfirmDialog(null,  " Are you sure you want to transfer "+receipt_rows_label.getText()+"receipts to Archive table ??", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            
            
                
                 Transaction t = new Transaction(m_App.getSession()) {

                      Date date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                            @Override
                            protected Object transact() throws BasicException {
                
                                
                                 new PreparedSentence(m_App.getSession(), " drop table if exists receipts_bk   ",new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                                 new PreparedSentence(m_App.getSession(), " drop table if exists payments_bk   ",new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                                 new PreparedSentence(m_App.getSession(), " CREATE TABLE RECEIPTS_BK AS (SELECT * FROM RECEIPTS WHERE 1=1)   ",new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                                 new PreparedSentence(m_App.getSession(), " CREATE TABLE PAYMENTS_BK AS (SELECT * FROM PAYMENTS WHERE 1=1)   ",new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                                
                                 new PreparedSentence(m_App.getSession(), " INSERT INTO RECEIPTS_ARV SELECT * FROM RECEIPTS WHERE DATENEW < ? AND CLOSECASHSEQ IS NOT NULL  ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[] {date});
                                 new PreparedSentence(m_App.getSession(), " INSERT INTO PAYMENTS SELECT * FROM PAYMENTS_ARV  WHERE RECEIPT IN (SELECT ID FROM RECEIPTS WHERE DATENEW < ? AND CLOSECASHSEQ IS NOT NULL)  ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[] {date});
                                
                                 new PreparedSentence(m_App.getSession(), " DELETE FROM PAYMENTS WHERE RECEIPT IN ( SELECT ID FROM RECEIPTS WHERE DATENEW < ? AND CLOSECASHSEQ IS NOT NULL ) ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[] {date});
                                 new PreparedSentence(m_App.getSession(), " DELETE FROM RECEIPTS WHERE DATENEW < ? AND CLOSECASHSEQ IS NOT NULL  ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[] {date});
                                  
                                 
                                 
                                return null;
                            }
                        };
                        t.execute();
                        
                        JOptionPane.showMessageDialog(this, "Successfully completed !");
            
            }
        
            
            
        
        } catch (BasicException ex) {
            //date = null;
            ex.printStackTrace();
            Logger.getLogger(ReceiptToReceiptArvMenu.class.getName()).log(Level.SEVERE, null, ex);             
                     ex.printStackTrace();
                     new MessageInf(ex).show(new JFrame());
        }
        }
        else{
             JOptionPane.showMessageDialog(this, "Select date first..! ");
             reset();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel receipt_arv_label;
    private javax.swing.JLabel receipt_rows_label;
    // End of variables declaration//GEN-END:variables


    public void reset(){
       receipt_rows_label.setText("");
       receipt_arv_label.setText("");
       fromdate_text.setText("");
     }

    public String getTitle() {
         return "";
    }

    public void activate() throws BasicException {
        reset();
        loaddata();
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
      
    }

    public Object getBean() {
       return this;
    }
    

    public void loaddata() throws BasicException{
        
       receipt_rows_label.setText("");
       receipt_arv_label.setText("");
       fromdate_text.setText("");
    }

   
}
