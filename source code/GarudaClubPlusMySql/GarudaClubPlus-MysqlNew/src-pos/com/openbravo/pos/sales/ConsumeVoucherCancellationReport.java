

package com.openbravo.pos.sales;

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
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.sales.ConsumeVoucherCancellationReportTableModel.BillInfo;


public class ConsumeVoucherCancellationReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    
    private AppView m_App;
    private List<ConsumeVoucherCancellationReportTableModel.BillInfo> Cancel_voucherList;
    private ConsumeVoucherCancellationReportTableModel ConsumeVoucherCancellationReport_Table_Model;
    
    public ConsumeVoucherCancellationReport() {
        initComponents();
        jTable1.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        todate_text = new javax.swing.JTextField();
        fromdate_cal = new javax.swing.JButton();
        todate_cal = new javax.swing.JButton();
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

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(107, 1, 1));
        jLabel1.setText("Material Voucher Cancellation Report");

        jLabel5.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel5.setText("From :");

        jLabel6.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel6.setText("To:");

        fromdate_cal.setText("From Date");
        fromdate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calActionPerformed(evt);
            }
        });

        todate_cal.setText("To Date");
        todate_cal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calActionPerformed(evt);
            }
        });

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

        jButton1.setText("Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print");
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
                        .addGap(313, 313, 313)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromdate_text, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(todate_text))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromdate_cal, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(todate_cal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(428, 428, 428)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromdate_cal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(todate_cal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
           if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
               
               
               Date FrmDate = new Date() ;
               Date ToDate = new Date();
               
               try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(ConsumeVoucherCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
               Cancel_voucherList = new ArrayList<ConsumeVoucherCancellationReportTableModel.BillInfo>();

                try {
                      ConsumeVoucherCancellationReport_Table_Model  = ConsumeVoucherCancellationReportTableModel.LoadCancelVoucherList(m_App, FrmDate , ToDate );
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                Cancel_voucherList  =  (List<ConsumeVoucherCancellationReportTableModel.BillInfo>) ConsumeVoucherCancellationReport_Table_Model.getCancelVoucherList();
               
               
               
                jTable1.setVisible(true);
                jTable1.setModel(ConsumeVoucherCancellationReport_Table_Model.getTableModel());
               
               
               
               
           
            }
           else{
               JOptionPane.showMessageDialog(this, "Please enter To date ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
           if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
               
               
               Date FrmDate = new Date() ;
               Date ToDate = new Date();
               
               try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(ConsumeVoucherCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               Cancel_voucherList = new ArrayList<ConsumeVoucherCancellationReportTableModel.BillInfo>();

                try {
                      ConsumeVoucherCancellationReport_Table_Model  = ConsumeVoucherCancellationReportTableModel.LoadCancelVoucherList(m_App, FrmDate , ToDate );
                }

                catch (BasicException ex) {
                     Logger.getLogger(ConsumableBillReprint.class.getName()).log(Level.SEVERE, null, ex);
                }
                Cancel_voucherList  =  (List<ConsumeVoucherCancellationReportTableModel.BillInfo>) ConsumeVoucherCancellationReport_Table_Model.getCancelVoucherList();
               
               
                String Header = "From : "+fromdate_text.getText()+" To : "+todate_text.getText()+" .";
                String currdate = Formats.TIMESTAMP.formatValue(new Date());        
                String asonDate =  "As on "+currdate;       
                DataSourceProvider data1 = new DataSourceProvider(Cancel_voucherList);
                DataSourceForConsumeCancelledVoucher dsfc = new DataSourceForConsumeCancelledVoucher(Cancel_voucherList);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());

                reportparams.put("TITLE","Consumable Material Cancelled  Vouchers");
                reportparams.put("Header",Header);
                reportparams.put("AsOnDate",asonDate);

                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/ConsumableVoucherCancelled.jrxml", reportparams, false, data1, true, null);

               
               
               
                }
           else{
               JOptionPane.showMessageDialog(this, "Please enter To date ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
      }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    // End of variables declaration//GEN-END:variables



public String getTitle() {
       return "Material Voucher cancellation Report";
    }

    public void activate() throws BasicException {
       
       jTable1.setVisible(false);
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
        
    }
    









}
