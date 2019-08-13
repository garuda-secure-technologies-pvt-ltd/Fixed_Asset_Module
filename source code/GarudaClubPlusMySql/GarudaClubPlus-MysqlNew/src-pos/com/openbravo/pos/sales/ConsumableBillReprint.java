
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;


import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.InventoryLine;
import com.openbravo.pos.inventory.JPanelInventory1;
import com.openbravo.pos.panels.ConsumableSalesModel;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import net.sf.jasperreports.engine.JasperPrint;
import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.pos.Booking.BookingSituationReport;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;


public class ConsumableBillReprint extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    private AppView m_App;
    private JPanelInventory1 m_invlines;
    private TicketParser m_TTP;
    private List<ConsumableBillReprintTableModel.BillInfo> BillInfoList;
    private ConsumableBillReprintTableModel ConsumableBillReprint_Table_Model;
    private List<ConsumableBillReprintTableModel.CloseDayInfo> CloseDayList;
    private List<ConsumableBillReprintTableModel.VoucherListInfo> VoucherList;
    private List<ConsumableBillReprintTableModel.ProductListInfo> ProductList; 
    
    private ConsumableBillReprintTableModel ConsumableCloseDay_Table_Model;
    private ConsumableBillReprintTableModel VoucherListTable_Model;
    private ConsumableBillReprintTableModel ProductListTableModel;
    
    public ConsumableBillReprint() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        todate_cal = new javax.swing.JButton();
        todate_text = new javax.swing.JTextField();
        fromdate_cal = new javax.swing.JButton();
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ClosedayReportPrint_btn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel2.setText("Voucher No :");

        jButton1.setText("Re-Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(752, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addContainerGap(346, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Voucher Report ", jPanel1);

        jLabel5.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel5.setText("From :");

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("To:");

        todate_cal.setText("To Date");
        todate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calActionPerformed(evt);
            }
        });

        fromdate_cal.setText("From Date");
        fromdate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(154, 7, 7));
        jLabel1.setText("Close Day Reports ");

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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Detials");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jButton4.setText("View Details");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(20, 42, 111));
        jLabel3.setText("Voucher wise ");

        jLabel4.setForeground(new java.awt.Color(26, 25, 168));
        jLabel4.setText("Product Wise ");

        ClosedayReportPrint_btn.setText("Print");
        ClosedayReportPrint_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosedayReportPrint_btnActionPerformed(evt);
            }
        });

        jButton6.setText("Print");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromdate_text, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(todate_text))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jButton5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ClosedayReportPrint_btn)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromdate_cal))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(todate_cal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(ClosedayReportPrint_btn))
                        .addGap(39, 39, 39))))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);
        jButton4.setVisible(false);
        ClosedayReportPrint_btn.setVisible(false);

        jTabbedPane1.addTab("Close Day Reports", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0){
           
           String BillNo = jTextField1.getText().trim().toString();
          
          
           
           
           
           
           BillInfoList = new ArrayList<ConsumableBillReprintTableModel.BillInfo>();

           try {
                 ConsumableBillReprint_Table_Model  = ConsumableBillReprintTableModel.loadEmailInfo(m_App, BillNo );
           }

           catch (BasicException ex) {
                Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
           }
           BillInfoList  =  (List<ConsumableBillReprintTableModel.BillInfo>) ConsumableBillReprint_Table_Model.getBillList();

           DataSourceProvider data1 = new DataSourceProvider(BillInfoList);
           DatasourceForConsumeBillReprint dsfc = new DatasourceForConsumeBillReprint(BillInfoList);
           data1.setDataSource(dsfc);
           Map reportparams = new HashMap();
           reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
           reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
           
           reportparams.put("TITLE","Consumable Material Issued Voucher");
           

           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ConsumableBill.jrxml", reportparams, false, data1, true, null);

           
           
           
           
           
           
           
       }
       else{
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void todate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calActionPerformed
        Date date;
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                todate_text.setText(Formats.TIMESTAMP.formatValue(date));
               
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_todate_calActionPerformed

    private void fromdate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
            todate_text.setText(null);
            
        }
    }//GEN-LAST:event_fromdate_calActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
           int SequenceNo  = Integer.valueOf(ConsumableCloseDay_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  
        
           String s1 = (ConsumableCloseDay_Table_Model.getTableModel().getValueAt(Row_Count, 1).toString());
           String s2 = (ConsumableCloseDay_Table_Model.getTableModel().getValueAt(Row_Count, 2).toString());
           
           
           String x = "%: "+SequenceNo;
           
                ProductList = new ArrayList<ConsumableBillReprintTableModel.ProductListInfo>();

                try {
                      ProductListTableModel  = ConsumableBillReprintTableModel.LoadProdData(m_App, x);
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ProductList  =  (List<ConsumableBillReprintTableModel.ProductListInfo>) ProductListTableModel.getProdList();
                
                
                
                String MainHeader = "Close Sale Seq : "+SequenceNo+" . (From "+s1+ "  to "+s2+" .)";
                DataSourceProvider data1 = new DataSourceProvider(ProductList);
                DataSourceforConsumableProductList dsfc = new DataSourceforConsumableProductList(ProductList);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());

                reportparams.put("TITLE","Consumable voucher wise close day Report. Seq no :" +SequenceNo);
                reportparams.put("MainHeader",MainHeader);
                 
                String CreatedDate = "As on "+(Formats.TIMESTAMP.formatValue(new Date()));
                reportparams.put("CreatedDate",CreatedDate);

                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ConsumableCloseDayReport.jrxml", reportparams, false, data1, true, null);

         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
           if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
          
               jTable2.setVisible(false);
               
               Date FrmDate = new Date() ;
               Date ToDate = new Date();
               
               try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
               
          
                CloseDayList = new ArrayList<ConsumableBillReprintTableModel.CloseDayInfo>();

                try {
                      ConsumableCloseDay_Table_Model  = ConsumableBillReprintTableModel.LoadCloseDayDetails(m_App, FrmDate , ToDate );
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                CloseDayList  =  (List<ConsumableBillReprintTableModel.CloseDayInfo>) ConsumableCloseDay_Table_Model.getCloseDayList();
               
                jTable1.setVisible(true);
                jTable1.setModel(ConsumableCloseDay_Table_Model.getTableModel());
                ClosedayReportPrint_btn.setVisible(true);
          
           }
           else{
               JOptionPane.showMessageDialog(this, "Please enter To date ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
      }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
           int SequenceNo  = Integer.valueOf(ConsumableCloseDay_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  
        
           String x = "%: "+SequenceNo;
           
                VoucherList = new ArrayList<ConsumableBillReprintTableModel.VoucherListInfo>();

                try {
                      VoucherListTable_Model  = ConsumableBillReprintTableModel.LoadVoucherInfoList(m_App, x);
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                VoucherList  =  (List<ConsumableBillReprintTableModel.VoucherListInfo>) VoucherListTable_Model.getVoucherList();
               
                
                ProductList = new ArrayList<ConsumableBillReprintTableModel.ProductListInfo>();

                try {
                      ProductListTableModel  = ConsumableBillReprintTableModel.LoadProdData(m_App, x);
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                ProductList  =  (List<ConsumableBillReprintTableModel.ProductListInfo>) ProductListTableModel.getProdList();
               
                
                
                
                
                jTable2.setVisible(true);
                jTable2.setModel(VoucherListTable_Model.getVoucherTableModel());
               
                
                jTable3.setModel(ProductListTableModel.getProductTableModel());
                jTable3.setVisible(true);
           
           
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         jTable1.setVisible(false);
         jTable2.setVisible(false);
         jTable3.setVisible(false);
         
         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         jTable2.setVisible(false);
         jTable3.setVisible(false);
         
         
          if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
           int SequenceNo  = Integer.valueOf(ConsumableCloseDay_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  
        
           String x = "%: "+SequenceNo;
           
                VoucherList = new ArrayList<ConsumableBillReprintTableModel.VoucherListInfo>();

                try {
                      VoucherListTable_Model  = ConsumableBillReprintTableModel.LoadVoucherInfoList(m_App, x);
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                VoucherList  =  (List<ConsumableBillReprintTableModel.VoucherListInfo>) VoucherListTable_Model.getVoucherList();
               
                
                ProductList = new ArrayList<ConsumableBillReprintTableModel.ProductListInfo>();

                try {
                      ProductListTableModel  = ConsumableBillReprintTableModel.LoadProdData(m_App, x);
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                ProductList  =  (List<ConsumableBillReprintTableModel.ProductListInfo>) ProductListTableModel.getProdList();
               
                
                
                
                
                jTable2.setVisible(true);
                jTable2.setModel(VoucherListTable_Model.getVoucherTableModel());
               
                
                jTable3.setModel(ProductListTableModel.getProductTableModel());
                jTable3.setVisible(true);
           
           
        }
         
         
         
    }//GEN-LAST:event_jTable1MouseClicked

    private void ClosedayReportPrint_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosedayReportPrint_btnActionPerformed
     if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
           if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
          
              
               
               Date FrmDate = new Date() ;
               Date ToDate = new Date();
               
               try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
               
          
                CloseDayList = new ArrayList<ConsumableBillReprintTableModel.CloseDayInfo>();

                try {
                      ConsumableCloseDay_Table_Model  = ConsumableBillReprintTableModel.LoadCloseDayDetails(m_App, FrmDate , ToDate );
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                CloseDayList  =  (List<ConsumableBillReprintTableModel.CloseDayInfo>) ConsumableCloseDay_Table_Model.getCloseDayList();
               
                String MainHeader = "From "+fromdate_text.getText()+ "  to "+todate_text.getText()+" .";
                DataSourceProvider data1 = new DataSourceProvider(CloseDayList);
                DataSourceProviderCloseDay dsfc = new DataSourceProviderCloseDay(CloseDayList);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());

                reportparams.put("TITLE","Consumable Close Day Report");
                reportparams.put("MainHeader",MainHeader);
                 
                String CreatedDate = "As on "+(Formats.TIMESTAMP.formatValue(new Date()));
                reportparams.put("CreatedDate",CreatedDate);

                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ConsumableCloseDayReport.jrxml", reportparams, false, data1, true, null);

                
                
          
           }
           else{
               JOptionPane.showMessageDialog(this, "Please enter To date ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
      }
    }//GEN-LAST:event_ClosedayReportPrint_btnActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(jTable1.getSelectedRow()!=-1){  
        
       
           int Row_Count = jTable1.getSelectedRow();
           int SequenceNo  = Integer.valueOf(ConsumableCloseDay_Table_Model.getTableModel().getValueAt(Row_Count, 0).toString());  
        
           String x = "%: "+SequenceNo;
           
                VoucherList = new ArrayList<ConsumableBillReprintTableModel.VoucherListInfo>();

                try {
                      VoucherListTable_Model  = ConsumableBillReprintTableModel.LoadVoucherInfoList(m_App, x);
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                VoucherList  =  (List<ConsumableBillReprintTableModel.VoucherListInfo>) VoucherListTable_Model.getVoucherList();
               
                
                 String MainHeader = "From "+fromdate_text.getText()+ "  to "+todate_text.getText()+" .";
                DataSourceProvider data1 = new DataSourceProvider(VoucherList);
                DataSourceforConsumableVoucherList dsfc = new DataSourceforConsumableVoucherList(VoucherList);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());

                reportparams.put("TITLE","Consumable voucher wise close day Report. Seq no :" +SequenceNo);
                reportparams.put("MainHeader",MainHeader);
                 
                String CreatedDate = "As on "+(Formats.TIMESTAMP.formatValue(new Date()));
                reportparams.put("CreatedDate",CreatedDate);

                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ConsumableCloseDayReport.jrxml", reportparams, false, data1, true, null);

                
                
                
                
                
                
                
                
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClosedayReportPrint_btn;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    // End of variables declaration//GEN-END:variables


 public String getTitle() {
       return "Billed Reports";
    }

    public void activate() throws BasicException {
       
       jTable1.setVisible(false);
       jTable2.setVisible(false);
       jTable3.setVisible(false);
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
        
       
      
        
    }
    
    public void reset(){
         jTable1.setVisible(false);
         jTable2.setVisible(false);
         jTable3.setVisible(false);
    }
    
    
    
   


     
     
     
     
     
     
  
    
    
    
 
     
   
    
     
     
     

}
