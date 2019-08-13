/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.sms.MemberEmailList;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;
import com.openbravo.pos.clubmang.MemberStatementEmailTableModel.MyAbstractTableModel;
import com.openbravo.pos.clubmang.MemberStatementEmailTableModel.EmailInfo;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class MemberStatementEmail extends javax.swing.JDialog {
     
    
     private AppView app;
     private boolean flag;
     private MemberStatementEmailTableModel MemberStatementEmail_Table_Model;
     private MyAbstractTableModel tablemodel;
     private static String MemType;
     private static Date FmDate;
     private MemberStatement Member_Statement;
     
     
     
     
    public MemberStatementEmail(java.awt.Frame parent,  boolean modal ) {
        super(parent, modal );
        initComponents();
       
    }

    
  
    
    public MemberStatementEmail(java.awt.Dialog parent,  AppView app, boolean flag ) {
        super(parent, flag );
       
        this.app = app;
        this.flag = flag;
      
    }
    
     public MemberStatementEmail(java.awt.Frame parent,  AppView app, boolean flag ) {
        super(parent, flag );
       
        this.app = app;
        this.flag = flag;
      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
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
        select_all_checkbox = new javax.swing.JCheckBox();
        close_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(16, 23, 232));
        jLabel1.setText("List of Members to Email Member Statement ");

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

        jButton1.setText("Check Report & Send E-Mail  ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Send  E-mail to selected Members   ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        select_all_checkbox.setText("Select All ");
        select_all_checkbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_all_checkboxItemStateChanged(evt);
            }
        });

        close_button.setText("Close");
        close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_buttonActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(226, 16, 16));
        jLabel2.setText("* Note : -    Sending multiple mail can take more time. Please wait for few minutes.");

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(select_all_checkbox))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(close_button)
                        .addGap(26, 26, 26))
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(jLabel1))
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(select_all_checkbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(close_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void select_all_checkboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_all_checkboxItemStateChanged
       
           tablemodel = MemberStatementEmail_Table_Model.getTableModel2();
            
        
           int rowcnt=tablemodel.getRowCount();
           System.out.println(rowcnt); 
            if(select_all_checkbox.isSelected()){

                for(int i=0; i<rowcnt; i++){

                    tablemodel.setValueAt(true, i, 4);
                }
            }else if(select_all_checkbox.isSelected()==false){
                for(int i=0; i<rowcnt; i++){

                    tablemodel.setValueAt(false, i, 4);
                }

            }
        
    }//GEN-LAST:event_select_all_checkboxItemStateChanged

    private void close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_buttonActionPerformed
       dispose();
    }//GEN-LAST:event_close_buttonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           
        
            int Row_Count = MemberStatementEmail_Table_Model.getTableModel2().getRowCount();
            List<String> MemNoList = new ArrayList<String>();
            
          /*  
            
           if(jTable1.getSelectedRow()<MemberStatementEmail_Table_Model.getSize()){ 
            int row = jTable1.getSelectedRow();
            EmailInfo showdata = MemberStatementEmail_Table_Model.getEmailMemberList().get(row);
            
            String Memno = showdata.getSEARCHKEY();
            String CustID = showdata.getID();
            String AccountId = showdata.getAccount();
            String MemName = showdata.getNAME();
            
            try{
                Member_Statement.generateReportForEmail(CustID ,AccountId ,  Memno , 0);
            }
            catch(Exception e){
                e.printStackTrace();
            }
           
            }
           */
           
           
            for(int i=0;i<Row_Count;i++){
                 int row = i;
               //  System.out.print(GRS.getTableModel2().getValueAt(row, 4).toString());
                 
                 Boolean ticked  = Boolean.valueOf(MemberStatementEmail_Table_Model.getTableModel2().getValueAt(row, 4).toString());
                 if(ticked){
                    String MemNo =  String.valueOf(MemberStatementEmail_Table_Model.getTableModel2().getValueAt(row, 1).toString());
                    MemNoList.add(MemNo);
                    
                   
                    
               }
             } 
           
            
            
           try{
                Member_Statement.generateReportForEmail_Multiple( MemNoList);
            }
            catch(Exception e){
                e.printStackTrace();
            }
           
            
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
           if(jTable1.getSelectedRow()<MemberStatementEmail_Table_Model.getSize()){ 
            int row = jTable1.getSelectedRow();
            EmailInfo showdata = MemberStatementEmail_Table_Model.getEmailMemberList().get(row);
            
            String Memno = showdata.getSEARCHKEY();
            String CustID = showdata.getID();
            String AccountId = showdata.getAccount();
            String MemName = showdata.getNAME();
            
            try{
                Member_Statement.generateReportForEmail(CustID ,AccountId ,  Memno , 1);
            }
            catch(Exception e){
                e.printStackTrace();
            }
           
            
            
            
            
            
           }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   
     public void init() throws BasicException {
        initComponents();
        Member_Statement = (MemberStatement) app.getBean("com.openbravo.pos.clubmang.MemberStatement");
        
        
        
        main_panel.setVisible(true);
      
        if(flag==true){
        
            MemberStatementEmail_Table_Model  = MemberStatementEmailTableModel.LoadMemberListToSendEmail_VISIBLE(app);
            jTable1.setModel(MemberStatementEmail_Table_Model.getTableModel2()); 
        }
        else{
            
            MemberStatementEmail_Table_Model  = MemberStatementEmailTableModel.LoadMemberListToSendEmail_MemType(app , MemType);
            jTable1.setModel(MemberStatementEmail_Table_Model.getTableModel2());   
            
            
        }

    }
    
     
     
      public static MemberStatementEmail getDialog(Component parent,  AppView app, boolean flag , String Memtype ) throws BasicException {

        Window window = getWindow(parent);
        
        MemberStatementEmail bill;
        
         MemType = Memtype;

        if (window instanceof Frame) {
            bill = new MemberStatementEmail((Frame) window , app, flag );
        } else {
            bill = new MemberStatementEmail((Dialog) window, app, flag );
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
    private javax.swing.JButton close_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JCheckBox select_all_checkbox;
    // End of variables declaration//GEN-END:variables
}
