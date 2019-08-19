
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;

import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;


public class PurchaseJournalReportNew extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    public List<String> WarehouseList = new ArrayList<String>();
    public ComboBoxValModel WarehouseCombo;
    
     public PurchaseJournalReportNewTableModel PurchaseJournalReportNew_Table_Model;
     private List<PurchaseJournalReportNewTableModel.PurchaseInfo> PurchaseDetail_list;
    
     public PJ_taxwiseReportTableModel PJ_taxwiseReport_Table_Model;
     private List<PJ_taxwiseReportTableModel.PurchaseInfo> PJ_taxwiseList;
     
     public List<String> AllTax_List = new ArrayList<String>();
     public List<String> SelectedTax_List = new ArrayList<String>();
     
     
    public PurchaseJournalReportNew() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        month_radio = new javax.swing.JRadioButton();
        period_radio = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        Month_cal = new javax.swing.JButton();
        todate_cal = new javax.swing.JButton();
        fromdate_cal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        all_warehouseRadio = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        filter_warehouseRadio = new javax.swing.JRadioButton();
        warehouse_panel = new javax.swing.JPanel();
        warehouse_combo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        Taxwise_btn = new javax.swing.JButton();
        tax_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allTax_Jlist = new javax.swing.JList();
        add_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        SelectedTax_JList = new javax.swing.JList();
        remove_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("Purchase - Tax Details");

        month_radio.setText("Month ");
        month_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_radioItemStateChanged(evt);
            }
        });

        period_radio.setText("Period");
        period_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_radioItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel5.setText("From :");

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("To:");

        Month_cal.setText("Month");
        Month_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calActionPerformed(evt);
            }
        });

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

        jButton1.setText("Generate Report ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        all_warehouseRadio.setText("All ");
        all_warehouseRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                all_warehouseRadioItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Miriam Fixed", 1, 14)); // NOI18N
        jLabel8.setText("Warehouse : ");

        filter_warehouseRadio.setText("Filter");
        filter_warehouseRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filter_warehouseRadioItemStateChanged(evt);
            }
        });

        warehouse_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel7.setFont(new java.awt.Font("Miriam Fixed", 1, 14)); // NOI18N
        jLabel7.setText("Select Warehouse : ");

        javax.swing.GroupLayout warehouse_panelLayout = new javax.swing.GroupLayout(warehouse_panel);
        warehouse_panel.setLayout(warehouse_panelLayout);
        warehouse_panelLayout.setHorizontalGroup(
            warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, warehouse_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        warehouse_panelLayout.setVerticalGroup(
            warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        Taxwise_btn.setText("Generate Report");
        Taxwise_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Taxwise_btnActionPerformed(evt);
            }
        });

        allTax_Jlist.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null));
        allTax_Jlist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(allTax_Jlist);

        add_btn.setText("Add");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        SelectedTax_JList.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), null)));
        SelectedTax_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(SelectedTax_JList);

        remove_btn.setText("Remove");
        remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tax_panelLayout = new javax.swing.GroupLayout(tax_panel);
        tax_panel.setLayout(tax_panelLayout);
        tax_panelLayout.setHorizontalGroup(
            tax_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tax_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tax_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        tax_panelLayout.setVerticalGroup(
            tax_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tax_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tax_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tax_panelLayout.createSequentialGroup()
                        .addComponent(add_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remove_btn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Select Tax to be displayed in report . (Maximum 5 Tax can be selected) ");

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("** Note :  Unselected Tax will be displayed as 'Other Tax' In report.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(all_warehouseRadio)
                                .addGap(18, 18, 18)
                                .addComponent(filter_warehouseRadio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(month_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(period_radio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(23, 23, 23)
                                        .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(47, 47, 47)
                                        .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(warehouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton2)
                                    .addGap(161, 161, 161)
                                    .addComponent(jButton1)
                                    .addGap(104, 104, 104)
                                    .addComponent(Taxwise_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(tax_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(jLabel2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(period_radio)
                    .addComponent(month_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(todate_cal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(all_warehouseRadio)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(filter_warehouseRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warehouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(tax_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(Taxwise_btn))
                .addGap(25, 25, 25))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);
        jButton1.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void month_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_radioItemStateChanged
        if(month_radio.isSelected()){
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);

            fromdate_text.setText(null);
            todate_text.setText(null);
           
        }
        else{
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
    }//GEN-LAST:event_month_radioItemStateChanged

    private void period_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_radioItemStateChanged
        if(period_radio.isSelected()){
            todate_cal.setVisible(true);
            fromdate_cal.setVisible(true);
            Month_cal.setVisible(false);
            fromdate_text.setText(null);
          
            todate_text.setText(null);
        }
        else{
            todate_cal.setVisible(false);
            fromdate_cal.setVisible(false);
            Month_cal.setVisible(true);
            fromdate_text.setText(null);
           
            todate_text.setText(null);
        }
    }//GEN-LAST:event_period_radioItemStateChanged

    private void Month_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_calActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
         

            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.add(Calendar.MONTH, 1);

            cal1.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate_text.setText(Formats.TIMESTAMP.formatValue(date));
           
        }
    }//GEN-LAST:event_Month_calActionPerformed

    private void todate_calActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calActionPerformed
        Date date;
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
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
     reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                String crr_date_str = null;

                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                }
                
                
                if(all_warehouseRadio.isSelected()){
                    
                    PurchaseDetail_list = new ArrayList<PurchaseJournalReportNewTableModel.PurchaseInfo>();
                    try {
                        PurchaseJournalReportNew_Table_Model  = PurchaseJournalReportNewTableModel.LoadPurchaseDetailsAll(m_App, FrmDate , ToDate  );
                     } 

                     catch (BasicException ex) {
                     }
                         PurchaseDetail_list  =  (List<PurchaseJournalReportNewTableModel.PurchaseInfo>) PurchaseJournalReportNew_Table_Model.getList();
                         
                    
                   //  jTable1.setModel(PurchaseJournalReportNew_Table_Model.getTableModel());
                    
                   // DataSourceProvider data1 = new DataSourceProvider(PurchaseDetail_list);
                  //  DataSourceForPurchaseReportNew dsfc = new DataSourceForPurchaseReportNew(PurchaseDetail_list);
                  //  data1.setDataSource(dsfc);
                  //  Map reportparams = new HashMap();
                  //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                  //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                  //  String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                   // reportparams.put("ReportHeader",RPH);
                   // reportparams.put("date",new Date());
                   // reportparams.put("TITLE"," Purchase Journal Report ");
                        
                   // JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/PurchaseReportNew1.jrxml", reportparams, false, data1, true, null); 
                         
                    
                    
                    
                }
                else{
                    String selectedWarehouse= warehouse_combo.getSelectedItem().toString();
                    String WarehouseId = null;
                    try {
                        WarehouseId = getWarehouseIDbyName(selectedWarehouse);
                    } catch (BasicException ex) {
                        Logger.getLogger(PurchaseJournalReportNew.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }
                
                
             }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void all_warehouseRadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_all_warehouseRadioItemStateChanged
        if(all_warehouseRadio.isSelected()){
            warehouse_panel.setVisible(false);
        }
        else{
            warehouse_panel.setVisible(true);
        }
    }//GEN-LAST:event_all_warehouseRadioItemStateChanged

    private void filter_warehouseRadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filter_warehouseRadioItemStateChanged
       if(filter_warehouseRadio.isSelected()){
            warehouse_panel.setVisible(true);
        }
        else{
            warehouse_panel.setVisible(false);
        }
    }//GEN-LAST:event_filter_warehouseRadioItemStateChanged

    private void Taxwise_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Taxwise_btnActionPerformed
         if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                
               if(SelectedTax_List.size()==5){  
                
                System.out.println("Tax 1 : "+SelectedTax_List.get(0).toString());
                System.out.println("Tax 2 : "+SelectedTax_List.get(1).toString());   
                System.out.println("Tax 3 : "+SelectedTax_List.get(2).toString());
                System.out.println("Tax 4 : "+SelectedTax_List.get(3).toString());
                System.out.println("Tax 5 : "+SelectedTax_List.get(4).toString());
                
                Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                     List<String> InvoiceIdList = new ArrayList<String>();
                     Date FrmDate = new Date() ;
                     Date ToDate = new Date();
                     Date CurrDate = new Date();
                     String crr_date_str = null;

                    @Override      
                             
                     protected Object transact() throws BasicException {   
                            
                         FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                         ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                         InvoiceIdList = getInvoceList(FrmDate, ToDate);
                         
                         
                         ////////////////////////////////////////////////////////////////////////  
                         int update=new PreparedSentence(m_App.getSession()
                            , "DELETE FROM PJ_REPORT "
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();

                
                         for(int i=0;i<InvoiceIdList.size();i++){
                          
                            String invoiceID =  InvoiceIdList.get(i).toString();
                            String InvoiceNo = getInvoiceNoByID(invoiceID);
                            String VendorName = getVendorNameByPJID(invoiceID);
                            String InvoiceDate =  getInvoiceDateByPJID(invoiceID);
                            String Tinno = getTinNoByPJID(invoiceID);
                            
                            
                            int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO pj_report (ID , invoiceno ,vendor,Date,tinno) VALUES (?,?,?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.STRING ,Datas.STRING,Datas.STRING})                         
                                  ).exec(new Object[]{ invoiceID ,InvoiceNo ,VendorName ,InvoiceDate,Tinno});                                                                                                

                          }
                         
                         List<String> InsertedPJ_ID_List = new ArrayList<String>(); 
                         InsertedPJ_ID_List=getInsertedPJ_IDList();
                         
                         Double OrgAmount;
                         Double OrgTaxAmount;
                         Double TaxPerc;        
                         for(int i=0;i<InsertedPJ_ID_List.size();i++){
                             String TempPJ_ID =  InsertedPJ_ID_List.get(i).toString();
                             TaxPerc=Double.parseDouble(SelectedTax_List.get(0).toString());
                             
                             OrgAmount = getAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             OrgTaxAmount = getTaxAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             
                             int Update_Tax1 =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET amt1=? , tax1=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ OrgAmount,OrgTaxAmount ,TempPJ_ID });
                             
                             
                             TaxPerc=Double.parseDouble(SelectedTax_List.get(1).toString());;
                             OrgAmount = getAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             OrgTaxAmount = getTaxAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             
                             int Update_Tax2 =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET amt2=? , tax2=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ OrgAmount,OrgTaxAmount ,TempPJ_ID });
                             
                             
                             TaxPerc=Double.parseDouble(SelectedTax_List.get(2).toString());;
                             OrgAmount = getAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             OrgTaxAmount = getTaxAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             
                             int Update_Tax3 =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET amt3=? , tax3=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ OrgAmount,OrgTaxAmount ,TempPJ_ID });
                             
                             
                             TaxPerc=Double.parseDouble(SelectedTax_List.get(3).toString());;
                             OrgAmount = getAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             OrgTaxAmount = getTaxAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             
                             int Update_Tax4 =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET amt4=? , tax4=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ OrgAmount,OrgTaxAmount ,TempPJ_ID });
                             
                             
                             
                             TaxPerc=Double.parseDouble(SelectedTax_List.get(4).toString());;
                             OrgAmount = getAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             OrgTaxAmount = getTaxAmountByTaxPerc(TempPJ_ID, TaxPerc);
                             
                             int Update_Tax5 =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET amt5=? , tax5=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ OrgAmount,OrgTaxAmount ,TempPJ_ID });
                             
                             
                             
                             
                             Double OtherAmount = getOtherAmount(TempPJ_ID);
                             Double OtherTaxAmount = getOtherTaxAmount(TempPJ_ID);
                             
                             
                             int Update_OtherAmount =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET otheramt=? , othertax=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ OtherAmount,OtherTaxAmount ,TempPJ_ID });
                             
                             
                             
                             Double AdditionalChrg = getAdditionalChargeByPJ_ID(TempPJ_ID);
                             
                             int Update_AddChrg =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET addchrg=?   WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ AdditionalChrg ,TempPJ_ID });
                             
                             
                             
                             
                             
                             
                             Double GrandTotal = getGrandTotalByPJ_ID(TempPJ_ID);
                             
                             GrandTotal=GrandTotal+AdditionalChrg;
                              int Update_GrandTotal =  new PreparedSentence(m_App.getSession(), "UPDATE pj_report  SET TOTAL=?   WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING   })).exec
                                                                            (new Object[]{ GrandTotal ,TempPJ_ID });
                             
                             
                             
                         }
                         
                         
                                 
                         
                         return null;                                      
                                     
                     }                            
                                 
                 };                 

                                 
                 try {                 
                     t.execute();          
                }
                 catch (BasicException ex) {                    
                     Logger.getLogger(PurchaseJournalReportNew.class.getName()).log(Level.SEVERE, null, ex);             
                     ex.printStackTrace();
                     new MessageInf(ex).show(new JFrame());
                 } 
                 
                 
                 
                  PJ_taxwiseList = new ArrayList<PJ_taxwiseReportTableModel.PurchaseInfo>();
                    try {
                        PJ_taxwiseReport_Table_Model  = PJ_taxwiseReportTableModel.LoadPurchaseDetailsAll(m_App );
                     } 

                     catch (BasicException ex) {
                     }
                         PJ_taxwiseList  =  (List<PJ_taxwiseReportTableModel.PurchaseInfo>) PJ_taxwiseReport_Table_Model.getList();
                         
                    DataSourceProvider data1 = new DataSourceProvider(PJ_taxwiseList);
                    DataSourceForPJ_TaxwiseReport dsfc = new DataSourceForPJ_TaxwiseReport(PJ_taxwiseList);
                    data1.setDataSource(dsfc);
                    Map reportparams = new HashMap();
                    reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                    reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                    String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                    reportparams.put("ReportHeader",RPH);
                    reportparams.put("date",new Date());
                    reportparams.put("HTAX1", SelectedTax_List.get(0).toString()+" %");
                    reportparams.put("HTAX2",SelectedTax_List.get(1).toString()+" %");
                    reportparams.put("HTAX3",SelectedTax_List.get(2).toString()+" %");
                    reportparams.put("HTAX4",SelectedTax_List.get(3).toString()+" %");
                    reportparams.put("HTAX5",SelectedTax_List.get(4).toString()+" %");
                    reportparams.put("HOTHERS","Other Tax");
                    reportparams.put("ASONDATE",("As On - " +Formats.TIMESTAMP.formatValue(new Date())));
                    
                    
                    reportparams.put("TITLE"," Purchase-Tax Report");
                        
                    
                    
                    
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/PJ_TaxwiseReport.jrxml", reportparams, false, data1, true, null); 
                         
                 
                 
               }
               else{
                    JOptionPane.showMessageDialog(this, "Atleast 5 Tax should be selected..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
               }
                
            }
            
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Taxwise_btnActionPerformed

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
          String selectedTax = allTax_Jlist.getSelectedValue().toString();
          SelectedTax_List.add(selectedTax);
          SelectedTax_JList.setModel(new PurchaseJournalReportNew.ItemsListModel(SelectedTax_List));
          AllTax_List.remove(selectedTax);
          allTax_Jlist.setModel(new PurchaseJournalReportNew.ItemsListModel(AllTax_List));
          
    }//GEN-LAST:event_add_btnActionPerformed

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_btnActionPerformed
          String selectedTax = SelectedTax_JList.getSelectedValue().toString();
          AllTax_List.add(selectedTax);
          allTax_Jlist.setModel(new PurchaseJournalReportNew.ItemsListModel(AllTax_List));
          SelectedTax_List.remove(selectedTax);
          SelectedTax_JList.setModel(new PurchaseJournalReportNew.ItemsListModel(SelectedTax_List));
    }//GEN-LAST:event_remove_btnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Month_cal;
    private javax.swing.JList SelectedTax_JList;
    private javax.swing.JButton Taxwise_btn;
    private javax.swing.JButton add_btn;
    private javax.swing.JList allTax_Jlist;
    private javax.swing.JRadioButton all_warehouseRadio;
    private javax.swing.JRadioButton filter_warehouseRadio;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JButton remove_btn;
    private javax.swing.JPanel tax_panel;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    private javax.swing.JComboBox warehouse_combo;
    private javax.swing.JPanel warehouse_panel;
    // End of variables declaration//GEN-END:variables

 public String getTitle() {
         return "";
    }

    public void activate() throws BasicException {
       
       SelectedTax_List = new ArrayList();
       SelectedTax_JList.setModel(new PurchaseJournalReportNew.ItemsListModel(SelectedTax_List));
       reset();
       loaddata();
       groupButton();
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
        
        WarehouseList = new ArrayList<String>();
        WarehouseList=getWarehouseList();
        WarehouseCombo = new ComboBoxValModel(WarehouseList);
        warehouse_combo.setModel(WarehouseCombo);
        
        
        AllTax_List = new ArrayList<String>();
        AllTax_List = getTaxList();
        
        allTax_Jlist.setModel(new PurchaseJournalReportNew.ItemsListModel(AllTax_List));
        
        
    }
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(all_warehouseRadio);
        bg2.add(filter_warehouseRadio);
        
    }

     
     public void reset(){
         
        all_warehouseRadio.setSelected(true);  
        fromdate_text.setText(null);
        todate_text.setText(null);
        month_radio.setSelected(true);
        warehouse_combo.setSelectedIndex(-1);
        
        
        
     }


     
      public List getWarehouseList() throws  BasicException{
       List<Object> Temp = new ArrayList();
       Temp =  new StaticSentence(m_App.getSession(), " SELECT NAME FROM LOCATIONS ORDER BY NAME ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
        return Temp;              
      }
     
     
      public String getWarehouseIDbyName(String Name) throws BasicException{
       Object o = null;
       String t = null;
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS  WHERE NAME=? ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
        if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
        
    } 
      
      
      public List getInvoceList(Date FrmDate , Date ToDate ) throws  BasicException{
       List<Object> Temp = new ArrayList();
       Temp =  new StaticSentence(m_App.getSession(), "SELECT M.ID FROM PURCHASEJOURNALMAIN M WHERE M.CRDATE>=? AND M.CRDATE<=? ORDER BY M.CRDATE ", 
                              new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }),
                             SerializerReadString.INSTANCE).list(new Object[]{  FrmDate  , ToDate });
        return Temp;              
      }
      
      
       public String getInvoiceNoByID(String ID) throws BasicException{
            Object o = null;
            String t = null;
             o =  new StaticSentence(m_App.getSession(), "SELECT INVOICENO FROM PURCHASEJOURNALMAIN  WHERE ID=? ", 
                                  SerializerWriteString.INSTANCE,
                                  SerializerReadString.INSTANCE).find(ID);
             if(o!=null){
                 t = o.toString();
                 return t;
             }
             else{
                 return t;
             }
        
        } 
       
       public String getVendorNameByPJID(String ID) throws BasicException{
            Object o = null;
            String t = null;
             o =  new StaticSentence(m_App.getSession(), "SELECT v.name FROM PURCHASEJOURNALMAIN p , vendor v WHERE p.ID=? and p.vendor=v.id", 
                                  SerializerWriteString.INSTANCE,
                                  SerializerReadString.INSTANCE).find(ID);
             if(o!=null){
                 t = o.toString();
                 return t;
             }
             else{
                 return t;
             }
        
        } 
       
       public String getTinNoByPJID(String ID) throws BasicException{
            Object o = null;
            String t = null;
             o =  new StaticSentence(m_App.getSession(), "select v.tinno  from vendor v , purchasejournalmain p  \n" +
"where                              p.id= ? and p.vendor=v.id ", 
                                  SerializerWriteString.INSTANCE,
                                  SerializerReadString.INSTANCE).find(ID);
             if(o!=null){
                 t = o.toString();
                 return t;
             }
             else{
                 return t;
             }
        
        } 
       
       public String getInvoiceDateByPJID(String ID) throws BasicException{
            
            String t = null;
            Date d = null;
            Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT crdate from purchasejournalmain where id=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(ID);
             if(obj2!=null){
                 d =(Date) obj2[0];
                 t= Formats.DATE.formatValue(d);
                 return t;
             }
             else{
                 return t;
             }
        
        } 
       
       
       public List getInsertedPJ_IDList() throws  BasicException{
       List<Object> Temp = new ArrayList();
       Temp =  new StaticSentence(m_App.getSession(), "SELECT ID FROM PJ_REPORT ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
        return Temp;              
      }
       
       
       public List getTaxList() throws  BasicException{
       List<Object> Temp = new ArrayList();
       Temp =  new StaticSentence(m_App.getSession(), "SELECT tax FROM pj_taxlist order by tax", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
        return Temp;              
      }
       
       
      public Double getAmountByTaxPerc(String PJID , Double TaxPerc ) throws BasicException{
        
          Object o = null;
            String t = null;
            Double D = 0.00;
//            o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
//                                                            "from purchasejournalmain m , purchasejournal p\n" +
//                                                            "where p.parent=m.id and ifnull(round(((p.taxtotal*100)/p.total),2),0.00)=  ?  \n" +
//                                                            " and m.id=?  ", 
//                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING  }),
//                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc , PJID });
//pratima:to add tax2 and tax3
if(TaxPerc!=0){
  o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax1*100)/p.total),2),0.00)=  ?  \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc , PJID });
  if(new Double(o.toString())==0){ 
      o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where ifnull(round(((p.tax2*100)/p.total),2),0.00)=  ?  or ifnull(round(((p.tax2*100)/(p.total+p.tax1)),2),0.00)=  ? and p.parent=m.id \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc ,TaxPerc, PJID });
  }
   
 if(new Double(o.toString())==0){ 
      o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where ifnull(round(((p.tax3*100)/p.total),2),0.00)=  ? or ifnull(round(((p.tax3*100)/(p.total+p.tax1+tax2)),2),0.00)=  ?  and p.parent=m.id \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc ,TaxPerc, PJID });
  }
 }
if(TaxPerc==0){
            o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.taxtotal*100)/p.total),2),0.00)=  ?  \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc , PJID });
}
             if(o!=null){
                 D = Double.parseDouble(o.toString());
                 return D;
             }
             else{
                 return D;
             }

        }  
      
      public Double getOtherAmount(String PJID  ) throws BasicException{
            Double t1 = Double.parseDouble(SelectedTax_List.get(0));
            Double t2 = Double.parseDouble(SelectedTax_List.get(1));
            Double t3 = Double.parseDouble(SelectedTax_List.get(2));
            Double t4 = Double.parseDouble(SelectedTax_List.get(3));
            Double t5 = Double.parseDouble(SelectedTax_List.get(4));
            
            Object o = null;
             Object o1 = null;
              Object o2 = null;
            String t = null;
            Double D = 0.00;
            
//             o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
//                                                            "from purchasejournalmain m , purchasejournal p\n" +
//                                                            "where p.parent=m.id and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) != ?  and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) != ? and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) != ? and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) != ? and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) != ?  \n" +
//                                                            " and m.id=?  ", 
//                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
//                                  SerializerReadString.INSTANCE).find(new Object[]{ t1 , t2,t3,t4,t5  , PJID });
// o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
//                                                            "from purchasejournalmain m , purchasejournal p\n" +
//                                                            "where p.parent=m.id and ifnull(round(((p.tax1*100)/p.total),2),0.00) != ?  and ifnull(round(((p.tax1*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) != ?  \n" +
//                                                           "and ifnull(round(((p.tax2*100)/p.total),2),0.00) != ?  and ifnull(round(((p.tax2*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) != ? \n"+
//                                                           "and ifnull(round(((p.tax3*100)/p.total),2),0.00) != ?  and ifnull(round(((p.tax3*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) != ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) != ? \n"+ 
//                                                           "and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ?  and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? \n"+
//                                                            "and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ?  and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? \n"+
//                                                                " and m.id=?  ", 
//                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
//                                  SerializerReadString.INSTANCE).find(new Object[]{ t1 , t2,t3,t4,t5  ,t1 , t2,t3,t4,t5  ,t1 , t2,t3,t4,t5  ,t1 , t2,t3,t4,t5  ,t1 , t2,t3,t4,t5  , PJID });
  o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ? \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , PJID });
           
              o1 =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ? \n" +
                                                           " and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ?  and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? \n"+
                                                             "and m.id=?  ",
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , t1,t2,t3,t4,t5 , PJID });
             
          
              o2 =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.total),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ? \n" +
                                                           " and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ?  and ifnull(round(((p.tax3*100)/(p.total+tax1++tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? \n"+
                                                             "and m.id=?  ",
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , t1,t2,t3,t4,t5 , PJID });
                    
            if(o!=null){
                if(o1!=null){
                    if(o2!=null){
                            D = Double.parseDouble(o.toString())+Double.parseDouble(o1.toString())+Double.parseDouble(o2.toString());
                    }else {
                            D = Double.parseDouble(o.toString())+Double.parseDouble(o1.toString());
                    }
                }else{
                if(o2!=null){
                     D = Double.parseDouble(o.toString())+Double.parseDouble(o2.toString());
                     }else {
                         D = Double.parseDouble(o.toString());
                     }
                }
                 return D;
             }
             else{
               if(o1!=null){
                            if(o2!=null){
                                D = Double.parseDouble(o1.toString())+Double.parseDouble(o2.toString());
                             }else
                            {D = Double.parseDouble(o1.toString());}
                    }else{
                    if(o2!=null){
                                D = Double.parseDouble(o2.toString());
                             }}
                 return D;
             }
        
        }  
      
      
       
      public Double getTaxAmountByTaxPerc(String PJID , Double TaxPerc ) throws BasicException{
            Object o = null;
            String t = null;
            Double D = 0.00;
//             o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.taxtotal),2),0.00)\n" +
//                                                            "from purchasejournalmain m , purchasejournal p\n" +
//                                                            "where p.parent=m.id and ifnull(round(((p.taxtotal*100)/p.total),2),0.00)=  ?  \n" +
//                                                            " and m.id=?  ", 
//                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING  }),
//                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc , PJID });
  o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.tax1),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax1*100)/p.total),2),0.00)=  ?  \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc , PJID });
  if(new Double(o.toString())==0){ 
  o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.tax2),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where ifnull(round(((p.tax2*100)/p.total),2),0.00)=  ?  or ifnull(round(((p.tax2*100)/(p.total+p.tax1)),2),0.00)=  ? and p.parent=m.id \n" +
                                                            " and m.id=?   ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc ,TaxPerc , PJID });
    }
    if(new Double(o.toString())==0){ 
    o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.tax3),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where ifnull(round(((p.tax3*100)/p.total),2),0.00)=  ? or ifnull(round(((p.tax3*100)/(p.total+p.tax1+tax2)),2),0.00)=  ?  and p.parent=m.id \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE , Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ TaxPerc ,TaxPerc , PJID });
    
     }
             if(o!=null){
                 D = Double.parseDouble(o.toString());
                 return D;
             }
             else{
                 return D;
             }
        
        }  
      
      
      public Double getOtherTaxAmount(String PJID   ) throws BasicException{
           
            Double t1 = Double.parseDouble(SelectedTax_List.get(0));
            Double t2 = Double.parseDouble(SelectedTax_List.get(1));
            Double t3 = Double.parseDouble(SelectedTax_List.get(2));
            Double t4 = Double.parseDouble(SelectedTax_List.get(3));
            Double t5 = Double.parseDouble(SelectedTax_List.get(4));
          
            Object o = null;
             Object o1 = null;
              Object o2 = null;
            String t = null;
            Double D = 0.00;
//             o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.taxtotal),2),0.00)\n" +
//                                                            "from purchasejournalmain m , purchasejournal p\n" +
//                                                            "where p.parent=m.id and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.taxtotal*100)/p.total),2),0.00) !=  ? \n" +
//                                                            " and m.id=?  ", 
//                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
//                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , PJID });
              o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.tax1),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax1*100)/p.total),2),0.00) !=  ? \n" +
                                                            " and m.id=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , PJID });
           
              o1 =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.tax2),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax2*100)/p.total),2),0.00) !=  ? \n" +
                                                           "and  ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ?  and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? and ifnull(round(((p.tax2*100)/(p.total+tax1)),2),0.00) != ? \n"+
                                                             "and m.id=?  ",
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , t1,t2,t3,t4,t5 , PJID });
             
           
              o2 =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(p.tax3),2),0.00)\n" +
                                                            "from purchasejournalmain m , purchasejournal p\n" +
                                                            "where p.parent=m.id and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ?  and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ? and ifnull(round(((p.tax3*100)/p.total),2),0.00) !=  ? \n" +
                                                           " and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ?  and ifnull(round(((p.tax3*100)/(p.total+tax1++tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? and ifnull(round(((p.tax3*100)/(p.total+tax1+tax2)),2),0.00) != ? \n"+
                                                             "and m.id=?  ",
                                  new SerializerWriteBasic(new Datas[]{ Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE ,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ t1,t2,t3,t4,t5 , t1,t2,t3,t4,t5 , PJID });
             
                  if(o!=null){
                if(o1!=null){
                    if(o2!=null){
                            D = Double.parseDouble(o.toString())+Double.parseDouble(o1.toString())+Double.parseDouble(o2.toString());
                    }else {
                            D = Double.parseDouble(o.toString())+Double.parseDouble(o1.toString());
                    }
                }else{
                if(o2!=null){
                     D = Double.parseDouble(o.toString())+Double.parseDouble(o2.toString());
                     }else {
                         D = Double.parseDouble(o.toString());
                     }
                }
                 return D;
             }
             else{
               if(o1!=null){
                            if(o2!=null){
                                D = Double.parseDouble(o1.toString())+Double.parseDouble(o2.toString());
                             }else
                            {D = Double.parseDouble(o1.toString());}
                    }else{
                    if(o2!=null){
                                D = Double.parseDouble(o2.toString());
                             }}
                 return D;
             }
        
        }  
      
      
      
      
      
       public Double getAdditionalChargeByPJ_ID(String PJID  ) throws BasicException{
            Object o = null;
            String t = null;
            Double D = 0.00;
            String Narration="Additional charges";
            String TransRef="Purchase Journal";
             o =  new StaticSentence(m_App.getSession(), "select   ifnull(round(sum(a.amount),2),0.00)\n" +
                                                            "from accountjournal a \n" +
                                                            "where a.tid=?   \n" +
                                                            " and transref=? and narration=?  ", 
                                  new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING ,  Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{ PJID ,TransRef , Narration });
             if(o!=null){
                 D = Double.parseDouble(o.toString());
                 return D;
             }
             else{
                 return D;
             }
        
        }  
       
       
       
       public Double getGrandTotalByPJ_ID(String PJID   ) throws BasicException{
            Object o = null;
            String t = null;
            Double D = 0.00;
             o =  new StaticSentence(m_App.getSession(), "select total from purchasejournalmain where id=? ", 
                                  new SerializerWriteBasic(new Datas[]{   Datas.STRING  }),
                                  SerializerReadString.INSTANCE).find(new Object[]{   PJID });
             if(o!=null){
                 D = Double.parseDouble(o.toString());
                 return D;
             }
             else{
                 return D;
             }
        
        }  
      
       
       
       private class ItemsListModel extends AbstractListModel {

        private java.util.List items;

        public ItemsListModel(java.util.List items) {
            this.items = items;
        }

        @Override
        public int getSize() {
            return items.size();
        }

        @Override
        public Object getElementAt(int i) {
            
            return items.get(i);
        }
     }
      
}
