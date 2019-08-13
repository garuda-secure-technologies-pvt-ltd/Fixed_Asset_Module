

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;

public class QT_Bill_UpdateMenuNew extends javax.swing.JDialog{

   
    private AppView app;
     private boolean flag;
    private int QT_Bill_Flag;

    
      
    public QT_Bill_UpdateMenuNew(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }

    
  
    
    public QT_Bill_UpdateMenuNew(java.awt.Dialog parent,  AppView app, boolean flag , int QT_Bill_Flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
        this.QT_Bill_Flag=QT_Bill_Flag;
    }
    
     public QT_Bill_UpdateMenuNew(java.awt.Frame parent,  AppView app, boolean flag , int QT_Bill_Flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
        this.QT_Bill_Flag=QT_Bill_Flag;
        
    }

    
    
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        countername_label1 = new javax.swing.JLabel();
        QT_Panel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        qtseries1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lqtno1 = new javax.swing.JTextField();
        Update_QT_Bill_Btn = new javax.swing.JButton();
        bill_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        billseries_text = new javax.swing.JTextField();
        lbillno_text = new javax.swing.JTextField();
        UpdateBill_Btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Kitchen_panel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kitchen_text1 = new javax.swing.JTextField();
        kitchen_text2 = new javax.swing.JTextField();
        KitchenQT_updateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Counter :");

        jLabel5.setForeground(new java.awt.Color(233, 6, 6));
        jLabel5.setText("Bill / QT Update Menu");

        countername_label1.setText("jLabel3");

        QT_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("QT as per QT table :");

        qtseries1.setEditable(false);
        qtseries1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel8.setText("QT as per Sequence Detail  :");

        lqtno1.setEditable(false);
        lqtno1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        Update_QT_Bill_Btn.setForeground(new java.awt.Color(12, 37, 244));
        Update_QT_Bill_Btn.setText("Update QT");
        Update_QT_Bill_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_QT_Bill_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QT_PanelLayout = new javax.swing.GroupLayout(QT_Panel);
        QT_Panel.setLayout(QT_PanelLayout);
        QT_PanelLayout.setHorizontalGroup(
            QT_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QT_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QT_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QT_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(qtseries1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QT_PanelLayout.createSequentialGroup()
                        .addGroup(QT_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(QT_PanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Update_QT_Bill_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(QT_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lqtno1)))
                        .addGap(59, 59, 59))))
        );
        QT_PanelLayout.setVerticalGroup(
            QT_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QT_PanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(QT_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtseries1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(QT_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lqtno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Update_QT_Bill_Btn)
                .addContainerGap())
        );

        qtseries1.setForeground(Color.RED);
        lqtno1.setForeground(Color.RED);

        bill_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Bill as per Bill table:");

        jLabel10.setText("Bill as per Sequence Detail : ");

        billseries_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        lbillno_text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        UpdateBill_Btn.setForeground(new java.awt.Color(7, 79, 253));
        UpdateBill_Btn.setText("Update Bill ");
        UpdateBill_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBill_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bill_panelLayout = new javax.swing.GroupLayout(bill_panel);
        bill_panel.setLayout(bill_panelLayout);
        bill_panelLayout.setHorizontalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bill_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(UpdateBill_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(91, 91, 91))
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbillno_text, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(billseries_text))))
                .addGap(49, 49, 49))
        );
        bill_panelLayout.setVerticalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billseries_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbillno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(UpdateBill_Btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        billseries_text.setEditable(false);
        billseries_text.setForeground(Color.RED);
        lbillno_text.setEditable(false);
        lbillno_text.setForeground(Color.RED);

        jButton2.setForeground(new java.awt.Color(252, 23, 23));
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(247, 20, 29));
        jLabel11.setText("Note: Be careful while using this menu. Only use when you are sure that ");

        jLabel12.setForeground(new java.awt.Color(243, 24, 48));
        jLabel12.setText("Bill/QT/ creation is not happening  due to 'Duplicate Entry'.");

        Kitchen_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setText("Kitchen QT No As per Table :");

        jLabel14.setText("Kitchen QT no As per Sequence :");

        kitchen_text1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        kitchen_text2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        KitchenQT_updateBtn.setForeground(new java.awt.Color(7, 79, 253));
        KitchenQT_updateBtn.setText("Update Kitchen Delivered QT");
        KitchenQT_updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KitchenQT_updateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Kitchen_panelLayout = new javax.swing.GroupLayout(Kitchen_panel);
        Kitchen_panel.setLayout(Kitchen_panelLayout);
        Kitchen_panelLayout.setHorizontalGroup(
            Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Kitchen_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Kitchen_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(KitchenQT_updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Kitchen_panelLayout.createSequentialGroup()
                        .addGroup(Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Kitchen_panelLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(43, 43, 43))
                            .addGroup(Kitchen_panelLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kitchen_text2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(kitchen_text1))))
                .addContainerGap())
        );
        Kitchen_panelLayout.setVerticalGroup(
            Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Kitchen_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kitchen_text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Kitchen_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kitchen_text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(KitchenQT_updateBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kitchen_text1.setEditable(false);
        kitchen_text1.setForeground(Color.RED);
        kitchen_text2.setEditable(false);
        kitchen_text2.setForeground(Color.RED);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(countername_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(143, 143, 143)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(QT_Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bill_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Kitchen_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(countername_label1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QT_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kitchen_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bill_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Update_QT_Bill_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_QT_Bill_BtnActionPerformed
      String createdby = null;
          try {
                  String DRoleID = app.getAppUserView().getUser().getRole();
       
                  Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.QTMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?  AND  ACTIVE=TRUE"  ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                  Object[] obj2 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.QTSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                  qcut=obj1[0].toString();
                  split2=qcut.split("(?<=\\G..)");
                    
                   for (int i = 0; i < split2.length; i++) {
                         System.out.println(split2[i]);
                    }
                   Object sp=split2[0].toString();
                          
                 
                   
                  //Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT MAX(ID) FROM QTICKET where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(); 
                   Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT ID FROM QTICKET where id like '"+obj2[0].toString()+""+sp+"%'ORDER BY CRDATE DESC LIMIT 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                
                  if (obj1 != null && obj2 != null) {
                          Double max = Double.parseDouble(obj1[0].toString());
                  /////////////////////////////////////////////////////////////////5thoct 
                          Double bser=   (Double) max;


                          obj9[0].toString();
                          String qcut1=obj9[0].toString();
                          String bring=obj2[0].toString();
                          String[] split3=qcut1.split(bring);

                          for (int i = 0; i < split3.length; i++) {
                                System.out.println(split3[i]);
                           }
                          String sp1=split3[1].toString();
                          String sp3=bser.toString();
                          Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          Double dObj=null;
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                                             
                                             
                                            dObj=dObj2-dObj3;
                                            System.out.println(+dObj+"");
                                            
                                            if(dObj==2){
                                                          int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                          if (res == JOptionPane.YES_OPTION) {
                                                                 getNextQTicketID(createdby);
                                                            ///  Bill.setEnabled(false);
                                                           }else{
                                                              Update_QT_Bill_Btn.setEnabled(true);
                                                            }

                                                }
                                              if(dObj==1){
                                                        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update QT ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                        if (res == JOptionPane.YES_OPTION) {

                                                              getNextQTicketID(createdby);
                                                          ///  Bill.setEnabled(false);
                                                        }else{
                                                            Update_QT_Bill_Btn.setEnabled(true);
                                                         }
                                              }



                                       if(dObj>2){
                                                     Update_QT_Bill_Btn.setEnabled(true);
                                                     System.out.println("dif>2"); 
                                                     JOptionPane.showMessageDialog(this, "Difference is greater than TWO.\n"
                                                             + "\t Cannot Update" 
                                                             + "\n\t Contact Administrator"
                                                             , "Error Message", JOptionPane.OK_OPTION); 
                                                 }








                       ///////////////////////////////////////////////////////////////5thoctend 



                                  }else if(dObj2<dObj3){
                                              dObj=dObj2-dObj3;
                                              System.out.println(+dObj+"");
                                              Update_QT_Bill_Btn.setEnabled(true);
                                              System.out.println("dif<2"); 
                                              JOptionPane.showMessageDialog(this, "Contact Admin.Difference is -1. CANNOT UPDATE", "Error Message", JOptionPane.OK_OPTION);
                                    }
                             }
                  
                  dispose();
            } catch (BasicException ex) {
                           Logger.getLogger(ResetQtBillReceipt.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_Update_QT_Bill_BtnActionPerformed

    private void UpdateBill_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBill_BtnActionPerformed
           String createdby=null;
        try {
                String DRoleID = app.getAppUserView().getUser().getRole();
                Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?  AND  ACTIVE=TRUE"  ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                Object[] obj2 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.BSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                bcut=obj1[0].toString();
                split2=bcut.split("(?<=\\G..)");
                    
                for (int i = 0; i < split2.length; i++) {
                     System.out.println(split2[i]);
                }
                Object sp=split2[0].toString();
                          
                 
                   
            //    Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
           Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT ID FROM bill where id like '"+obj2[0].toString()+""+sp+"%'ORDER BY CREATEDDATE DESC LIMIT 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
         
                
                if (obj1 != null && obj2 != null) {
                      Double max = Double.parseDouble(obj1[0].toString());
                           /////////////////////////////////////////////////////////////////5thoct 
                      Double bser=   (Double) max;
       
         
                      obj9[0].toString();
                      String qcut1=obj9[0].toString();
                      String bring=obj2[0].toString();
                      String[] split3=qcut1.split(bring);
                    
                      for (int i = 0; i < split3.length; i++) {
                           System.out.println(split3[i]);
                      }
                      String sp1=split3[1].toString();
                      String sp3=bser.toString();
                      Double dObj2 = Double.valueOf(sp1);
                      Double dObj3 = Double.valueOf(sp3);
                      Double dObj=null;
                      dObj2.compareTo(dObj3);
                      if(dObj2>dObj3){
                          dObj=dObj2-dObj3;
                          System.out.println(+dObj+"");
                          if(dObj==2){
                                int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("The Differene is more than 1\nAre you sure you want to update?"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (res == JOptionPane.YES_OPTION) {
            
                                 getNextBillID(createdby);
                                            ///  Bill.setEnabled(false);
                                }else{
                                    UpdateBill_Btn.setEnabled(true);
                                }
                           }  
                              
                             
                        if(dObj>2){
                                  UpdateBill_Btn.setEnabled(true);
                                  System.out.println("dif>2"); 
                                  JOptionPane.showMessageDialog(this, "Difference is greater than TWO.\n"
                                                                    + "\t Cannot Update" 
                                                                    + "\n\t Contact Administrator"
                                                                    , "Error Message", JOptionPane.OK_OPTION); 
                        }
                        
                         if(dObj==1){
                              
                            int res1 = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update Bill ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (res1 == JOptionPane.YES_OPTION) {
                                    getNextBillID(createdby);
                            }
                            else{
                                    UpdateBill_Btn.setEnabled(true);
                            }
        
                         }
                              
                      }  else if(dObj2<dObj3){
                               dObj=dObj2-dObj3;
                               System.out.println(+dObj+"");
                               UpdateBill_Btn.setEnabled(true);
                               System.out.println("dif<2"); 
                               JOptionPane.showMessageDialog(this, "Contact Admin.Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                          }
                          
                              
                      
        ///////////////////////////////////////////////////////////////5thoctend 
            
        
              }
                 
               dispose();
        } catch (BasicException ex) {
            Logger.getLogger(ResetQtBillReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_UpdateBill_BtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void KitchenQT_updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KitchenQT_updateBtnActionPerformed
        if(kitchen_text1.getText()!=null && kitchen_text1.getText().trim().length()>0){
            String kQt=kitchen_text1.getText().trim();
            String New_Kqt = kQt+"_A";
            try{
                
                new StaticSentence(app.getSession(), "UPDATE QTKITCHEN_ARV SET ID=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{New_Kqt,kQt});
                
                JOptionPane.showMessageDialog(this, " Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            catch(BasicException e){
                 Logger.getLogger(ResetQtBillReceipt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_KitchenQT_updateBtnActionPerformed

   public static QT_Bill_UpdateMenuNew getDialog(Component parent,  AppView app, boolean flag , int QT_Bill_Flag) throws BasicException {

        Window window = getWindow(parent);
        
        QT_Bill_UpdateMenuNew bill;
        
       

        if (window instanceof Frame) {
            bill = new QT_Bill_UpdateMenuNew((Frame) window , app, flag , QT_Bill_Flag);
        } else {
            bill = new QT_Bill_UpdateMenuNew((Dialog) window, app, flag , QT_Bill_Flag);
        }
       
        return bill;
        
        
    }
    
     public boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }
   
     
     
     
       
   protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    } 
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KitchenQT_updateBtn;
    private javax.swing.JPanel Kitchen_panel;
    private javax.swing.JPanel QT_Panel;
    private javax.swing.JButton UpdateBill_Btn;
    private javax.swing.JButton Update_QT_Bill_Btn;
    private javax.swing.JPanel bill_panel;
    private javax.swing.JTextField billseries_text;
    private javax.swing.JLabel countername_label1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField kitchen_text1;
    private javax.swing.JTextField kitchen_text2;
    private javax.swing.JTextField lbillno_text;
    private javax.swing.JTextField lqtno1;
    private javax.swing.JTextField qtseries1;
    // End of variables declaration//GEN-END:variables

 public void init() throws BasicException {
        initComponents();
        QT_Panel.setVisible(false);
        Update_QT_Bill_Btn.setEnabled(false);
        
        
        if(QT_Bill_Flag==1){
          
          QT_Panel.setVisible(true);
          Update_QT_Bill_Btn.setEnabled(false);
          String roleid = app.getAppUserView().getUser().getRole();
          String RoleName = getRoleNameById(roleid);
          countername_label1.setText(RoleName);
          
          CheckForQTUpdate();
          CheckBillUpdate();
          
          String kQt=getDuplicateKitchenTableQTs();
          if(kQt!=null && kQt.length()>0){
              kitchen_text1.setText(kQt);
              kitchen_text2.setText(kQt);
              KitchenQT_updateBtn.setEnabled(true);
          }
          else{
              kitchen_text1.setText(null);
              kitchen_text2.setText(null);
              KitchenQT_updateBtn.setEnabled(false);
          }
          
       }
      
        
      

    }


 // ********************  CHANGES DONE FOR KITCHEN QT UPDATES ********************************* //////
 
 public String getDuplicateKitchenTableQTs() throws  BasicException{
     String kQt = null;
     
     Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"select id from qtkitchen where id in (select id from qtkitchen_arv) limit 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
     if(obj9!=null){
         kQt = obj9[0].toString();
     }
     
     return kQt;
 }
 
 
 
     

   public String getRoleNameById(String RoleID) throws  BasicException{
      String RoleName=null; 
      Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT NAME   FROM ROLES  WHERE ID=?     ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(RoleID); 
      if(obj1!=null){
         RoleName= obj1[0].toString();
      } 
      else{
         RoleName=null; 
      }
      return RoleName;
   }

   
    private String qcut=null;
    public String split2[];
    private Double qtmax=0.0;
    public String qtseries5;
    private String qt=null; 
    private int sales=0;
    private int payment=0;
   
    
    
    
    
    
   public void CheckForQTUpdate(){
       
       
       qtmax=0.0;
    
       try{
             
    
            String DRoleID = app.getAppUserView().getUser().getRole();
            Object[] obj=(Object[])   new StaticSentence(app.getSession()
                        , "SELECT QTSERIES,QTMAX,BSERIES,BMAX,RSERIES,RMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE AND CATEGORY=? "
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).find(new Object[]{DRoleID,DRoleID});
       
        
            
            if(obj[0]!=null){
                
                 qcut=obj[1].toString();
                     split2=qcut.split("(?<=\\G..)");
                    
                       for (int i = 0; i < split2.length; i++) {
                       System.out.println(split2[i]);
                       }
                       Object sp=split2[0].toString();
                   
                
                       
                  Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT ID FROM qticket where id like '"+obj[0].toString()+""+sp+"%'ORDER BY CRDATE DESC LIMIT 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                  qtseries5=obj[0].toString();
                 
                  
                  if(obj9[0]!=null){
                      
                        qtseries5=obj9[0].toString();
                        System.out.println(qtseries5);
                        qt=obj[0].toString();  
                      
                      
                     
      
                                String s1=obj[0].toString();

                                          String s2=obj[1].toString();
                                          String split1[]=s2.split("\\.");

                                          for (int i = 0; i < split1.length; i++) {
                                             System.out.println(split1[i]);
                                               }
                                          String s5=s1+split1[0];

                                          String s3=s1+s2;
                                          String s4=obj9[0].toString(); 
                                          
                                          qtseries1.setText(s5);
                                          lqtno1.setText(s4);
                                          
                                          if(s5.equals(s4)){

                                         Update_QT_Bill_Btn.setEnabled(false);
                                      //  Bill.setEnabled(false);
                                          }else{
                                                 Update_QT_Bill_Btn.setEnabled(true);
                                            //     Bill.setEnabled(true);
                                                  /////////////////////////////////////////////////////////////////5thoct 
                                                Double qser=   (Double) obj[1];
                                                obj[1].toString();
                                                ////this is for seqdet
                                                qtseries5.concat(split2[0].toString());
                                                /////////end

                                                obj9[0].toString();
                                               String qcut1=obj9[0].toString();
                                              String bring=obj[0].toString();
                                              String[] split3=qcut1.split(bring);

                                             for (int i = 0; i < split3.length; i++) {
                                             System.out.println(split3[i]);
                                             }
                                               String sp1=split3[1].toString();
                                               String sp3=qcut;
                                               Double dObj2 = Double.valueOf(sp1);
                                                Double dObj3 = Double.valueOf(sp3);
                                                dObj2.compareTo(dObj3);
                                               
                                                
                                                
                                                            if( dObj2>dObj3){
                                                               Double dObj=dObj2-dObj3;
                                                                System.out.println(+dObj+"");

                                                                if(dObj==1){
                                                                    
                                                                    
                                                                    
                                                                      Update_QT_Bill_Btn.setEnabled(true);  
                                                                   }


                                                                else if(dObj==2){
                                                                    
                                                                  
                                                                    
                                                                    
                                                              Update_QT_Bill_Btn.setEnabled(true);
                                                              System.out.println("dif==2");
                                                          }      
                                                          else if(dObj>2){
                                                                      

                                                              
                                                                     Update_QT_Bill_Btn.setEnabled(true);
                                                                    System.out.println("dif>2"); 
                                                                 //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);

                                                                }





                                                            }else if(dObj2<dObj3){
                                                                 Double dObj=dObj2-dObj3;
                                                                System.out.println(+dObj+"");
                                                                Update_QT_Bill_Btn.setEnabled(false);
                                                                    System.out.println("dif>2"); 
                                                             //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                                                            }
                                                            else if( dObj2==dObj3){
                                                                 Double dObj=dObj2-dObj3;
                                                                System.out.println(+dObj+"");
                                                                Update_QT_Bill_Btn.setEnabled(false);
                                                            }

                              ///////////////////////////////////////////////////////////////5thoctend 
                                              }

                           

                      
                      
                      
                    
                      
                  }
                  
                  
                
                
            }
           
            
        
       }
       catch(BasicException E){
           
       }
       
       
       
   }
   
   
   
   
   
    private String getNextQTicketID(String createdby) throws BasicException {
        //shiv:sequencedetail:inserting id instead of names
 ////        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you wanna Update QT ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
 /////       if (res == JOptionPane.YES_OPTION) {
             try{
             
             String qtnum;
                     String DRoleID = app.getAppUserView().getUser().getRole();

                   
                     Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.QTMAX  FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?   AND  ACTIVE=TRUE  ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                     Object[] obj2 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.QTSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                     Object[] obj5= (Object[]) new StaticSentence(app.getSession(),"SELECT CONCAT(QTSERIES,QTMAX) AS QTICKET FROM SEQUENCEDETAIL WHERE QTSERIES=?",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj2[0].toString());
                     System.out.println(obj1[0]);
                     System.out.println(obj2[0]);
                     String qt1=obj2[0].toString()+obj1[0].toString();
                   
                  ///////////////////////////////////////SHIV:CREATED///////////////////////////////////////////////////////////////////////////////////////////////////////////   
                   Object[] obj4 = (Object[]) new StaticSentence(app.getSession(),"SELECT * FROM QTICKET Q,SEQUENCEDETAIL S WHERE  S.QTMAX="+(obj1[0].toString())+" AND Q.ID=?" ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj5.toString());        
                     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                  // Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT ID FROM QTICKET Q,SEQUENCEDETAIL S WHERE Q.ID = ? AND S.QTMAX=?",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name.toString());        
                   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                         qcut=obj1[0].toString();
                                  split2=qcut.split("(?<=\\G..)");

                                    for (int i = 0; i < split2.length; i++) {
                                    System.out.println(split2[i]);
                                    }
                                      Object sp=split2[0].toString();




        //      Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT MAX(ID) FROM qticket where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
  Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT ID FROM qticket where id like '"+obj2[0].toString()+""+sp+"%'ORDER BY CRDATE DESC LIMIT 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        

                  //   String S = obj2[0].toString();  
                   // Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM qticket where id like '"+obj2[0].toString()+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                              String qtseries1=obj9.toString();
                               System.out.println(qtseries1);
                           /////////////////////////////////////////////////////////////////////////////////////
                              String bmaga= obj9[0].toString();

                                 String split4[]=bmaga.split("(?<=\\G..)");

                                 for (int i = 0; i < split4.length; i++) {
                                    System.out.println(split4[i]);
                                 }




                           ////////////////////////////////////////////////////////////////////////////////////
                   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                 if(obj1 != null && obj2 != null) {
                                     Double max = Double.parseDouble(obj1[0].toString());

                              
                                     String mbcut1=obj9[0].toString();
                                     String bring1=obj2[0].toString();
                                     String[] split5=mbcut1.split(bring1);

                                    for (int i = 0; i < split5.length; i++) {
                                    System.out.println(split5[i]);
                                    }

                                      String sp4=split5[1].toString();

                                       Double dObj4 = Double.valueOf(sp4);
                                   //    Double max1 = Double.parseDouble(obj1[0].toString());
                                       if(dObj4>max){

                                                  Double dObj5=dObj4-max;

                                                  if(dObj5==2){
                                           
                                                      max++;
                                                      new StaticSentence(app.getSession(), "UPDATE SEQUENCEDETAIL SET QTMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY= ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING,Datas.STRING})).exec(new Object[]{max,DRoleID,DRoleID});

                                        
                                                 }

                                       }

               ////////////////////////////new end


                          max++;
                          qtnum = DRoleID + max.intValue();
                          new StaticSentence(app.getSession(), "UPDATE SEQUENCEDETAIL SET QTMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY= ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING,Datas.STRING})).exec(new Object[]{max,DRoleID,DRoleID});

                          String value=obj2[0].toString()+max.intValue();
                          lqtno1.setText(value);
                          JOptionPane.showMessageDialog(null, "Updated QT to: " + value);


                                                              /////////////////////////////////////////////////////////////////5thoct 
                          Double qser= (Double) max;

                      ////this is for seqdet

                      /////////end

                          obj9[0].toString();
                          String qcut1=obj9[0].toString();
                          String bring=obj2[0].toString();
                          String[] split3=qcut1.split(bring);

                          for (int i = 0; i < split3.length; i++) {
                               System.out.println(split3[i]);
                           }
                                      
                          String sp1=split3[1].toString();
                          String sp3=qser.toString();
                          Double dObj2 = Double.valueOf(sp1);
                          Double dObj3 = Double.valueOf(sp3);
                          dObj2.compareTo(dObj3);
                          if( dObj2>dObj3){
                                      Double dObj=dObj2-dObj3;
                                      System.out.println(+dObj+"");

                                     if(dObj==1){
                                             Update_QT_Bill_Btn.setEnabled(true);  
                                      }
                                      else if(dObj==2){
                                          Update_QT_Bill_Btn.setEnabled(true);
                                          System.out.println("dif==2");
                                      }      
                                     else if(dObj>2){
                                                 Update_QT_Bill_Btn.setEnabled(false);
                                                 System.out.println("dif>2"); 
                                      }

                             }else if(dObj2<dObj3){
                                            Double dObj=dObj2-dObj3;
                                            System.out.println(+dObj+"");
                                            Update_QT_Bill_Btn.setEnabled(false);
                                            System.out.println("dif>2"); 
                                       
                               }
                               else {
                                            Double dObj=dObj2-dObj3;
                                            System.out.println(+dObj+"");
                                            Update_QT_Bill_Btn.setEnabled(false);
                               }

                     ///////////////////////////////////////////////////////////////5thoctend 


                        return qtnum;

                     } else {
                        JOptionPane.showMessageDialog(null, "Please Specify the QT Series", "Cannot Create QT", JOptionPane.OK_OPTION);

                     }


                          }catch(Exception e){
                              JOptionPane.showMessageDialog(null, "Please Specify the QT Series", "Cannot Create QT", JOptionPane.OK_OPTION);
                               }




               ////      }///end of optionpane
               ////      else{
                ////           QT.setEnabled(true);
                 ////         }
                     return "";
             
             
    }
    
    
    
    
    
    
    
    
    
     private String bcut=null;
     public String bseries1;
     private String bill=null;
     private Double billmax=0.0;
     
    public void CheckBillUpdate() throws BasicException{
        
         String DRoleID = app.getAppUserView().getUser().getRole();
         Object[] obj=(Object[])   new StaticSentence(app.getSession()
                        , "SELECT QTSERIES,QTMAX,BSERIES,BMAX,RSERIES,RMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE AND CATEGORY=? "
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.DOUBLE})).find(new Object[]{DRoleID,DRoleID});
       
        
        
         if(obj[2]!=null){
                     
                 ///////////shiv:For Bill
               
               

              
                   
               bcut=obj[3].toString();
               split2=bcut.split("(?<=\\G..)");
                    
               for (int i = 0; i < split2.length; i++) {
                    System.out.println(split2[i]);
               }
               Object sp=split2[0].toString();
                          
               Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT ID FROM bill where id like '"+obj[2].toString()+""+sp+"%'ORDER BY CREATEDDATE DESC LIMIT 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
             
              // bseries1=obj[2].toString();
 
               if(obj9[0]!=null){
 
                   bseries1=obj9[0].toString();
                   System.out.println(bseries1);
                   //   double bsd = Double.valueOf(obj9[0].toString()).doubleValue();
                   bseries1.split(obj9[0].toString(),3);
                   billseries_text.setText(bseries1);
                   bill=obj[2].toString();
               
                   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                   if(sales==1 || payment==0){
      
                            String s1=obj[2].toString();
             
                            String s2=obj[3].toString();
                            String split1[]=s2.split("\\.");

                            for (int i = 0; i < split1.length; i++) {
                               System.out.println(split1[i]);
                                 }
                            String s5=s1+split1[0];

                            String s3=s1+s2;
                            String s4=obj9[0].toString(); 
                            if(s5.equals(s4)){
                     
                                // QT.setEnabled(false);
                                 UpdateBill_Btn.setEnabled(false);
                            }else{
                        
                      //     Bill.setEnabled(true);
                            /////////////////////////////////////////////////////////////////5thoct 
                            Double qser=   (Double) obj[3];
                            obj[3].toString();
         
         
                            obj9[0].toString();
                            String qcut1=obj9[0].toString();
                            String bring=obj[2].toString();
                            String[] split3=qcut1.split(bring);
                    
                            for (int i = 0; i < split3.length; i++) {
                                  System.out.println(split3[i]);
                            }
                            String sp1=split3[1].toString();
                            String sp3=bcut;
                            Double dObj2 = Double.valueOf(sp1);
                            Double dObj3 = Double.valueOf(sp3);
                            dObj2.compareTo(dObj3);
                            if( dObj2>dObj3){
                              Double dObj=dObj2-dObj3;
                              System.out.println(+dObj+"");
                              if(dObj==1){
                                UpdateBill_Btn.setEnabled(true);  
                              }
                              else if(dObj==2){
                                  UpdateBill_Btn.setEnabled(true);
                                  System.out.println("dif==2");
                              }      
                               else if(dObj>2){
                                    UpdateBill_Btn.setEnabled(true);
                                    System.out.println("dif>2"); 
                                 //    JOptionPane.showMessageDialog(this, "Difference is greater than TWO. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);

                              }
                              
                            
                              }else if(dObj2<dObj3){
                                  Double dObj=dObj2-dObj3;
                                  System.out.println(+dObj+"");
                                  UpdateBill_Btn.setEnabled(false);
                                  System.out.println("dif>2"); 
                                  //        JOptionPane.showMessageDialog(this, "Difference is -1. CANNOT UPDATE", "cannot", JOptionPane.OK_OPTION);
                              }
                              else if( dObj2==dObj3){
                                   Double dObj=dObj2-dObj3;
                                   System.out.println(+dObj+"");
                                   UpdateBill_Btn.setEnabled(false);
                              }
                      
        ///////////////////////////////////////////////////////////////5thoctend 
                        
                        
                        
                        
                        
                            }
                 
                         }
 
           
               ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
            
                        if(obj[3]!=null)
                            billmax=Double.parseDouble(obj[3].toString());
                        } else{
                          billseries_text.setText("No Bill");
                          UpdateBill_Btn.setEnabled(false);
                        }
               
                        }else billseries_text.setText("");
         
                         billmax=Double.parseDouble(obj[3].toString());
                         String sw=  obj[3].toString();
                         lbillno_text.setText(obj[2].toString()+billmax.intValue());
    }
    
    
    
    
    public String getNextBillID(String createdby) throws BasicException {
  
        String billnum;
       
           try{
                        String DRoleID = app.getAppUserView().getUser().getRole(); 

                        Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=?  AND  ACTIVE=TRUE"  ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);
                        Object[] obj2 = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.BSERIES FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(DRoleID);

                        System.out.println(obj1[0]);
                        System.out.println(obj2[0]);

                        bcut=obj1[0].toString();
                        split2=bcut.split("(?<=\\G..)");

                        for (int i = 0; i < split2.length; i++) {
                               System.out.println(split2[i]);
                        }
                        Object sp=split2[0].toString();

                   //     Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj2[0].toString()+""+sp+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        


  Object[] obj9 = (Object[]) new StaticSentence(app.getSession(),"SELECT ID FROM bill where id like '"+obj2[0].toString()+""+sp+"%'ORDER BY CREATEDDATE DESC LIMIT 1",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        



                        //  Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(),"SELECT MAX(ID) FROM bill where id like '"+obj2[0].toString()+"%'",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();        
                        String bseries1=obj9.toString();
                        System.out.println(bseries1);


                        if (obj1 != null && obj2 != null) { 
                            Double max = Double.parseDouble(obj1[0].toString());

               
                            String mqcut1=obj9[0].toString();
                            String bring1=obj2[0].toString();
                            String[] split4=mqcut1.split(bring1);

                            for (int i = 0; i < split4.length; i++) {
                                System.out.println(split4[i]);
                            }

                            String sp4=split4[1].toString();

                            Double dObj4 = Double.valueOf(sp4);
                             
                            if(dObj4>max){
                                     Double dObj5=dObj4-max;
                                     if(dObj5==2){
                                              max++;
                                              new StaticSentence(app.getSession(), "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY = ? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max,DRoleID,DRoleID});
                                             
                                      }

                             }

         
                            max++;
                            billnum = DRoleID + max.intValue();
                            new StaticSentence(app.getSession(), "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ? AND CATEGORY = ? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max,DRoleID,DRoleID});

                            String value=obj2[0].toString()+max.intValue();
                            lbillno_text.setText(value);

                            JOptionPane.showMessageDialog(null, "Updated Bill to: " + value);


                                 
                            Double bser=   (Double) max;


                            obj9[0].toString();
                            String qcut1=obj9[0].toString();
                            String bring=obj2[0].toString();
                            String[] split3=qcut1.split(bring);

                            for (int i = 0; i < split3.length; i++) {
                               System.out.println(split3[i]);
                            }
                            String sp1=split3[1].toString();
                            String sp3=bser.toString();
                            Double dObj2 = Double.valueOf(sp1);
                            Double dObj3 = Double.valueOf(sp3);
                            dObj2.compareTo(dObj3);
                            if( dObj2>dObj3){
                                     Double dObj=dObj2-dObj3;
                                     System.out.println(+dObj+"");

                                     if(dObj==1){
                                        UpdateBill_Btn.setEnabled(true);  
                                     }
                                     else if(dObj==2){
                                        UpdateBill_Btn.setEnabled(true);
                                        System.out.println("dif==2");
                                     }      
                                     else if(dObj>2){
                                          UpdateBill_Btn.setEnabled(false);
                                          System.out.println("dif>2"); 
                                      
                                      }





                                      }else if(dObj2<dObj3){
                                          Double dObj=dObj2-dObj3;
                                          System.out.println(+dObj+"");
                                          UpdateBill_Btn.setEnabled(false);
                                          System.out.println("dif>2"); 
                                 
                                      }
                                      else{
                                           Double dObj=dObj2-dObj3;
                                           System.out.println(+dObj+"");
                                           UpdateBill_Btn.setEnabled(false);
                                      }

                ///////////////////////////////////////////////////////////////5thoctend 





                            return billnum;

                            } 
                        else {
                                JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);

                       }

     
             }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
                  }
  
          return "";
          
        
    }
    
    
    
}
