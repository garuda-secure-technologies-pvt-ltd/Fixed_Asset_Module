/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.sms.EmailMasterTableModel;
import com.openbravo.pos.sms.MemberEmailList;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;



/**
 *
 * @author USER
 */
public class DocumentReceiptListForm extends javax.swing.JDialog {
 
    
     private AppView app;
     private boolean flag;
     private DocumentReceiptTableModel DocumentReceipt_Table_Model;
     private List<DocumentReceiptTableModel.DocumentReceiptListInfo> DocumentAllList = new ArrayList<DocumentReceiptTableModel.DocumentReceiptListInfo>();
     private String SelectedMember;
     
     
     public DocumentReceiptListForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }
     
     
      public DocumentReceiptListForm(java.awt.Dialog parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
     public DocumentReceiptListForm(java.awt.Frame parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
     
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setText("Document Receipt Received List");

        jButton1.setText("Re-Print Receipt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Select Member ");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private TicketParser m_TTP;
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
          
           DocumentReceiptTableModel.DocumentReceiptListInfo DocBean = DocumentAllList.get(Row_Count);
           if(DocBean!=null){
               
               String Memno = DocBean.getMemberNo();
               System.out.println("Memno : "+Memno);
               
               String FormNo = DocBean.getFormNo();
               String ReceiptNo = DocBean.getReceiptNo();
               int No_of_Photos = DocBean.getNosPhoto();
               int No_of_Doc = DocBean.getNosDoc();
               
               
               
               
               
               
               String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.kymDocumentReceipt");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
         

            // qTicket.getCustomer().getSearchkey();
                    m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
                    ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                    script.put("createdby", m_App.getAppUserView().getUser().getName());
                    String x = m_App.getAppUserView().getUser().getRole();
                    script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                    script.put("host",  m_App.getProperties().getHost());



                    script.put("maintitle", "KYM Document Receipt");
                    script.put("Reciept_title", "Receipt No :");
                    script.put("receipt",  ReceiptNo );
                    script.put("date", Formats.TIMESTAMP.formatValue(DocBean.getCRDATE()));
                    script.put("cname", DocBean.getMemberName());
                    script.put("cno", DocBean.getMemberNo());

                    String temp = "Received KYM form no. : "+FormNo;
                    script.put("label_6", temp);
                    script.put("label_7", "Containing details : ");
                    script.put("label_8", "No of Photos : ");
                    script.put("label_9", "No of Documents : ");


                    script.put("text8", No_of_Photos);
                    script.put("text9", No_of_Doc);





                    script.put("eoe", StringUtils.encodeXML("E&OE"));
                   // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
                    m_TTP.printTicket(script.eval(sresource).toString());








                } catch (ScriptException e) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                    msg.show(this);
                } catch (TicketPrinterException e) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                    msg.show(this);
                } catch (Exception e) {
                }
               
               
               
               
               
               
               
               
           }
       
       
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
          
           DocumentReceiptTableModel.DocumentReceiptListInfo DocBean = DocumentAllList.get(Row_Count);
           if(DocBean!=null){
               
               SelectedMember = DocBean.getMemberNo();
               System.out.println("Memno : "+SelectedMember);
               dispose();
           }
           
       
       }
       else{
            JOptionPane.showMessageDialog(this, "Please select member first.  ", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_jButton2ActionPerformed
 
    
    
    
    
    
    
    
     public static DocumentReceiptListForm getDialog(Component parent,  AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);
        
        DocumentReceiptListForm bill;
        
       

        if (window instanceof Frame) {
            bill = new DocumentReceiptListForm((Frame) window , app, flag);
        } else {
            bill = new DocumentReceiptListForm((Dialog) window, app, flag);
        }
       
        return bill;
        
        
    }
    
     public String showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return SelectedMember;
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
    
   
   public void init() throws BasicException {
        initComponents();
        
           DocumentReceipt_Table_Model = DocumentReceiptTableModel.loadInstanceDocumentReportsAll(app) ;
           DocumentAllList = DocumentReceipt_Table_Model.getDocumentList();
           jTable1.setModel(DocumentReceipt_Table_Model.getTableModel2()); 
           System.out.println("DocumentAllList size : "+DocumentAllList.size());

    }

    
    
    
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
