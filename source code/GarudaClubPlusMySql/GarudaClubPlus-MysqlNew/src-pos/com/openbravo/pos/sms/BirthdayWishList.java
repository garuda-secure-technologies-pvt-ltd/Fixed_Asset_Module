/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.table.TableCellRenderer;
import com.openbravo.pos.sms.BirthdayWishListTableModel.MyAbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.UUID;
import javax.swing.JFrame;
import java.util.Date;



public class BirthdayWishList extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private BirthdayWishListTableModel BirthdayWishes_Table_Model;
    private BirthdayWishListTableModel BirthdayWishes_Table_Model_Dept;
    private MyAbstractTableModel tablemodel;
    
    
    
    
    
    public BirthdayWishList() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        memdept_option = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        jTabbedPane1.setForeground(new java.awt.Color(204, 0, 0));

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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Send ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("List of Members Having Birthday Today");

        jCheckBox1.setText("Select All");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        memdept_option.setText("Show Member Dependents ");
        memdept_option.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memdept_optionItemStateChanged(evt);
            }
        });

        jButton2.setText("Check For Birthday wishes sent ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(memdept_option)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(memdept_option))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List of Members ", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
     
        if(memdept_option.isSelected()){
            
            
            
            
            
            
            
            
            
        } 
        else{
            
             tablemodel = BirthdayWishes_Table_Model.getTableModel2();
            
        
           int rowcnt=tablemodel.getRowCount();
           System.out.println(rowcnt); 
            if(jCheckBox1.isSelected()){

                for(int i=0; i<rowcnt; i++){

                    tablemodel.setValueAt(true, i, 4);
                }
            }else if(jCheckBox1.isSelected()==false){
                for(int i=0; i<rowcnt; i++){

                    tablemodel.setValueAt(false, i, 4);
                }

            }
        
           
            
            
            
            
        }
        
        
      
          
          
      
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void memdept_optionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memdept_optionItemStateChanged
      if(memdept_option.isSelected()){
          try {
              BirthdayWishes_Table_Model_Dept = BirthdayWishListTableModel.loadBirthdayWishTodayDept(m_App);
          } catch (BasicException ex) {
              Logger.getLogger(BirthdayWishList.class.getName()).log(Level.SEVERE, null, ex);
          }
          jTable1.setModel(BirthdayWishes_Table_Model_Dept.getTableModel3()); 
          
          
          
          
      }
      else{
          
          
          try {
              BirthdayWishes_Table_Model = BirthdayWishListTableModel.loadBirthdayWishToday(m_App);
          } catch (BasicException ex) {
              Logger.getLogger(BirthdayWishList.class.getName()).log(Level.SEVERE, null, ex);
          }
           jTable1.setModel(BirthdayWishes_Table_Model.getTableModel2()); 
          
          
      }
    }//GEN-LAST:event_memdept_optionItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          int Row_Count = BirthdayWishes_Table_Model.getTableModel2().getRowCount();
          List<String> MemNoList = new ArrayList<String>();                     // Tejaswini
            
            
           for(int i=0;i<Row_Count;i++){
                 int row = i;
               //  System.out.print(GRS.getTableModel2().getValueAt(row, 4).toString());
                 
                 Boolean ticked  = Boolean.valueOf(BirthdayWishes_Table_Model.getTableModel2().getValueAt(row, 4).toString());
                 if(ticked){
                    String MemNo =  String.valueOf(BirthdayWishes_Table_Model.getTableModel2().getValueAt(row, 1).toString());
                    MemNoList.add(MemNo);
                    
                   
                    
               }
             }   
            
         Object[] Birthdy_Message = null;  
           
         try{ 
           
         Birthdy_Message = (Object[]) new StaticSentence(m_App.getSession()
                , "SELECT PREFIX , MESSAGE1 , MEMFLAG , AUTOFLAG , MEMNAME FROM sms_greetings WHERE ACTIVE=1 AND MEMFLAG=1  "
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.INT ,  Datas.INT ,  Datas.INT })).find();
        
         }
         catch(BasicException e){
              e.printStackTrace();
         }
         
         
         if(Birthdy_Message!=null){
         
         
         
         String Prefix = Birthdy_Message[0].toString();
         String MESSAGE1 = Birthdy_Message[1].toString();
         int AUTOFLAG = Integer.parseInt(Birthdy_Message[3].toString());
         int MEMNAME = Integer.parseInt(Birthdy_Message[4].toString());
           
           
         String Message =  Prefix +  MESSAGE1;
           
           
           
           for(int i=0; i<MemNoList.size() ; i++ ){
               
               String Memno = MemNoList.get(i).toString();
               String Mobile = null;
               
                try{ 
           
               
                    Object[] getMobile =  (Object[]) new StaticSentence(m_App.getSession()
                                                // , "SELECT MOBILE FROM CUSTOMERS WHERE SEARCHKEY= ?  AND LENGTH(MOBILE)=10 || LENGTH(MOBILE)=13 "
                                                , "SELECT MOBILE FROM CUSTOMERS WHERE SEARCHKEY= ?  AND ( LENGTH(MOBILE)=10 || LENGTH(MOBILE)=13  )"
                                                 , new SerializerWriteBasic( new Datas[]{Datas.STRING})
                                                 ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{Memno});
                        
                    
                    if(getMobile != null){ 
                           Mobile = getMobile[0].toString();
                        }
                         else{
                           Mobile = null;
                        }
                                            
                  }
                 catch(BasicException e){
                      e.printStackTrace();
                 }
               
                
              if(Mobile!=null){
                  
                  
                  try{
                  
                 
                    int   insert_data2 =  new PreparedSentence(m_App.getSession()  , "insert into sentbirthdaywish (ID , MEMNO , DATEOFSENT , CRBY , MESSAGE , MOBILE , FLAG) VALUES(?,?,?,?,?,?,?) \n"
                                                                                ,  new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING, Datas.STRING , Datas.STRING , Datas.INT})                         
                                                                                ).exec( new Object[]{ UUID.randomUUID().toString() , Memno  , new Date() ,   m_App.getAppUserView().getUser().getName()  , Message , Mobile , 1  });                 
                      
                      
                      
                      
                      
                  int   insert_data =  new PreparedSentence(m_App.getSession()  , "insert into activemsgtable (ID , MESSAGE , SENDTO , CNT , PRIORITY , FLAG) VALUES(?,?,?,?,?,?) \n"
                                                                                ,  new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT, Datas.INT  , Datas.INT})                         
                                                                                ).exec( new Object[]{ UUID.randomUUID().toString() , Message  ,  Mobile , 1 , 2 , 0   });                                                                                                
 
                  
                  
                  int Update =  new PreparedSentence(m_App.getSession(), "  UPDATE   sms_birthdy_currlist SET ACTIVE=0 WHERE  MEMNO=? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING })).exec
                                                                            (new Object[]{ Memno });
                  
                  
                  }
                  catch(BasicException EX){
                      EX.printStackTrace();
                      new MessageInf(EX).show(new JFrame());
                  }
                  
                  
                  
              }
                
           }
           
           
           try{
           
             loaddata();
           }
           catch(Exception e){
               e.printStackTrace();
               new MessageInf(e).show(new JFrame());
           }
           
         }
         else{
             
              JOptionPane.showMessageDialog(this, "No message type defined..!!  \n \n  Define Message first in master", "Warning", JOptionPane.WARNING_MESSAGE);
         }
           
            
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         SentSMSList memList;
        try {
            memList = SentSMSList.getDialog(this, m_App,true);
            memList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(BirthdayWishList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox memdept_option;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
      return "Birthday Wish List";
    }

    public void activate() throws BasicException {
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
        
        memdept_option.setSelected(false);
        BirthdayWishes_Table_Model = BirthdayWishListTableModel.loadBirthdayWishToday(m_App);
        jTable1.setModel(BirthdayWishes_Table_Model.getTableModel2()); 
        
    }
    
    
    
}
