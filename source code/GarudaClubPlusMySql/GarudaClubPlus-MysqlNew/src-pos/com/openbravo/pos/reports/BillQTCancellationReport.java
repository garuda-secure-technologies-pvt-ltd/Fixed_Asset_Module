

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author dev1
 */
public class BillQTCancellationReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

  
    private AppView m_App;
    List<Object> lst= new ArrayList<Object>();
    private ComboBoxValModel wareCombo;
    private List<BillQTCancellationModel.GetSetMethod1> qtRev_List_all;
    private BillQTCancellationModel qtdisc_List_Report;
    private List<BillQTCancellationModel.GetSetMethod2> qtRev_List_all2;
    private BillQTCancellationModel qtdisc_List_Report2;
    private Date fromDate;
    private Date toDat;
   
    
    public BillQTCancellationReport() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        bill_rdo = new javax.swing.JRadioButton();
        qt_rdo = new javax.swing.JRadioButton();
        ware = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        month_Rdo = new javax.swing.JRadioButton();
        period_Rdo = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        frmDate = new javax.swing.JTextField();
        toDate = new javax.swing.JTextField();
        DateSelect = new javax.swing.JButton();
        DateSelect1 = new javax.swing.JButton();
        DateSelect2 = new javax.swing.JButton();

        buttonGroup1.add(bill_rdo);
        bill_rdo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        bill_rdo.setText("Bill ");
        bill_rdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bill_rdoActionPerformed(evt);
            }
        });

        buttonGroup1.add(qt_rdo);
        qt_rdo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        qt_rdo.setText("QT");

        ware.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(133, 8, 8));
        jLabel1.setText("Select Warehouse:");

        buttonGroup2.add(month_Rdo);
        month_Rdo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        month_Rdo.setText("Month");
        month_Rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_RdoItemStateChanged(evt);
            }
        });

        buttonGroup2.add(period_Rdo);
        period_Rdo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        period_Rdo.setText("Period");
        period_Rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_RdoItemStateChanged(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(133, 8, 8));
        jButton1.setText("Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(133, 8, 8));
        jLabel2.setText("Select  Duration:");

        DateSelect.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        DateSelect.setText("fromDate");
        DateSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSelectActionPerformed(evt);
            }
        });

        DateSelect1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        DateSelect1.setText("ToDate");
        DateSelect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSelect1ActionPerformed(evt);
            }
        });

        DateSelect2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        DateSelect2.setText("Month");
        DateSelect2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSelect2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(toDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(frmDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(DateSelect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(DateSelect2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(DateSelect1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(frmDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(DateSelect)
                    .add(DateSelect2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(toDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(DateSelect1))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(36, 36, 36)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(month_Rdo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(period_Rdo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(206, 206, 206)
                        .add(bill_rdo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(qt_rdo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(27, 27, 27)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ware, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(215, 215, 215)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(122, 122, 122)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(32, 32, 32)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(month_Rdo)
                    .add(period_Rdo))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(qt_rdo)
                    .add(bill_rdo))
                .add(22, 22, 22)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ware, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(58, 58, 58)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DateSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelectActionPerformed

        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frmDate.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                frmDate.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_DateSelectActionPerformed

    private void DateSelect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect1ActionPerformed
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(toDate.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis((date).getTime());
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                toDate.setText(Formats.TIMESTAMP.formatValue(cal.getTime()));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_DateSelect1ActionPerformed

    private void DateSelect2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect2ActionPerformed
        
          Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frmDate.getText());
            //System.out.println(date);
        } catch (BasicException ex) {
            
             ex.printStackTrace();
             new MessageInf(ex).show(this);
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            frmDate.setText(Formats.TIMESTAMP.formatValue(date));
            frmDate.setText(Formats.TIMESTAMP.formatValue(date));
            
            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.MONTH, 1);
            
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            
            toDate.setText(Formats.TIMESTAMP.formatValue(date));
             toDate.setText(Formats.TIMESTAMP.formatValue(date));

           /* ReportHeader = "Advance Payment Report for All Members ";
            header_text.setText(ReportHeader);
            report_btn.setEnabled(true);
            gen_report_room.setEnabled(true);*/

        }
              
    }//GEN-LAST:event_DateSelect2ActionPerformed

    private void period_RdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_RdoItemStateChanged
        DateSelect.setVisible(true);
         DateSelect1.setVisible(true);
         DateSelect2.setVisible(false);
         frmDate.setText("");
         toDate.setText("");
        
    }//GEN-LAST:event_period_RdoItemStateChanged

    private void month_RdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_RdoItemStateChanged
        DateSelect2.setVisible(true);
        DateSelect.setVisible(false);
         DateSelect1.setVisible(false);
        frmDate.setText("");
        toDate.setText("");
    }//GEN-LAST:event_month_RdoItemStateChanged

    private void bill_rdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bill_rdoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bill_rdoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            fromDate = (Date) Formats.TIMESTAMP.parseValue(frmDate.getText());
        } catch (BasicException ex) {
            Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toDat = (Date) Formats.TIMESTAMP.parseValue(toDate.getText());
        } catch (BasicException ex) {
            Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date=new Date();
       if(month_Rdo.isSelected() && qt_rdo.isSelected())
         { 
             if(frmDate.getText()!= null && frmDate.getText().toString().length()>0 ){
                 if(toDate.getText()!=null && toDate.getText().toString().length()>0){
        if( ware.getSelectedIndex()!=-1 && ware.getSelectedItem().toString().equals("All"))
        {
             qtRev_List_all = new ArrayList<BillQTCancellationModel.GetSetMethod1>();
             
             try {
                               qtdisc_List_Report = BillQTCancellationModel.LoadAllqtdisc(m_App,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all  = (List<BillQTCancellationModel.GetSetMethod1>)  qtdisc_List_Report.getqtdiscList();
             DataSourceProvider data1 = new DataSourceProvider(qtRev_List_all);
                        DataSourceForQTCancellation dsfc = new DataSourceForQTCancellation(qtRev_List_all);
                        data1.setDataSource(dsfc);
             
           Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName()); 
             reportparams.put("WarehouseName", ware.getSelectedItem().toString());
             reportparams.put("Date", date);
             reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillCancellationReport1.jrxml", reportparams, false, data1, true, null);
             
             
        }else if( ware.getSelectedIndex()!=-1 && !ware.getSelectedItem().toString().equals("All"))
        {
              String memty=ware.getSelectedItem().toString();
            qtRev_List_all = new ArrayList<BillQTCancellationModel.GetSetMethod1>();
             
             try {
                               qtdisc_List_Report = BillQTCancellationModel.Loadqtdisc(m_App,memty,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all  = (List<BillQTCancellationModel.GetSetMethod1>)  qtdisc_List_Report.getqtdiscList();
             DataSourceProvider data1 = new DataSourceProvider(qtRev_List_all);
                        DataSourceForQTCancellation dsfc = new DataSourceForQTCancellation(qtRev_List_all);
                        data1.setDataSource(dsfc);
            
           Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());  
            reportparams.put("WarehouseName", ware.getSelectedItem().toString());
            reportparams.put("Date", date);
            reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillCancellationReport1.jrxml", reportparams, false, data1, true, null);
        }else{
             JOptionPane.showMessageDialog(this, "Select Warehouse First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }
        
         }else{
             JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }
         }else{
             JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }                 
           
       }
      else if(period_Rdo.isSelected() && qt_rdo.isSelected())
         {
              if(frmDate.getText().toString()!=null &&  frmDate.getText().toString().length()>0 ){
                  
                if  (toDate.getText().toString()!=null && toDate.getText().toString().length()>0 ){
           if( ware.getSelectedIndex()!=-1 && ware.getSelectedItem().toString().equals("All"))
           {
            //String frm_date= frmDate.getText();
           // String to_date= toDate.getText();
             qtRev_List_all = new ArrayList<BillQTCancellationModel.GetSetMethod1>();
             
             try {
                               qtdisc_List_Report = BillQTCancellationModel.LoadAllqtdiscPeriod(m_App,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all  = (List<BillQTCancellationModel.GetSetMethod1>)  qtdisc_List_Report.getqtdiscList();
             DataSourceProvider data1 = new DataSourceProvider(qtRev_List_all);
                        DataSourceForQTCancellation dsfc = new DataSourceForQTCancellation(qtRev_List_all);
                        data1.setDataSource(dsfc);
             
           Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());  
             reportparams.put("WarehouseName", ware.getSelectedItem().toString());
             reportparams.put("Date", date);
             reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillCancellationReport1.jrxml", reportparams, false, data1, true, null);
              
               
           }else if( ware.getSelectedIndex()!=-1 && !ware.getSelectedItem().toString().equals("All"))
           {
               String memty=ware.getSelectedItem().toString();
            qtRev_List_all = new ArrayList<BillQTCancellationModel.GetSetMethod1>();
             
             try {
                               qtdisc_List_Report = BillQTCancellationModel.LoadqtdiscPeriod(m_App,memty,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all  = (List<BillQTCancellationModel.GetSetMethod1>)  qtdisc_List_Report.getqtdiscList();
             DataSourceProvider data1 = new DataSourceProvider(qtRev_List_all);
                        DataSourceForQTCancellation dsfc = new DataSourceForQTCancellation(qtRev_List_all);
                        data1.setDataSource(dsfc);
            
           Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());  
            reportparams.put("WarehouseName", ware.getSelectedItem().toString());
            reportparams.put("Date", date);
            reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillCancellationReport1.jrxml", reportparams, false, data1, true, null);
           }else{
             JOptionPane.showMessageDialog(this, "Select Warehouse First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }
              }else{  
          JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
         }
         }else{  
          JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
         }                         
                              
         }
      //---------------------------bill cancellation--------------------------------------------------
        if(month_Rdo.isSelected() && bill_rdo.isSelected())
         {
          if(frmDate.getText()!= null && frmDate.getText().toString().length()>0 ){
              if( toDate.getText()!=null && toDate.getText().toString().length()>0)
          {
        if( ware.getSelectedIndex()!=-1 && ware.getSelectedItem().toString().equals("All"))
        {
             qtRev_List_all2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();
             
             try {
                               qtdisc_List_Report2 = BillQTCancellationModel.LoadAllbill_rev(m_App,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all2  = (List<BillQTCancellationModel.GetSetMethod2>)  qtdisc_List_Report2.getbill_revList2();
             DataSourceProvider data2 = new DataSourceProvider(qtRev_List_all2);
             DataSourceForBillCancellation dsfc1 = new DataSourceForBillCancellation(qtRev_List_all2);
             data2.setDataSource(dsfc1);
             
           Map reportparams = new HashMap();
           reportparams.put("companyName", m_App.getSession().getCompanyName());  
           reportparams.put("WarehouseName",ware.getSelectedItem().toString()); 
           reportparams.put("Date", date);
           reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillQTCancellationReport1.jrxml", reportparams, false, data2, true, null);
        }
        else if( ware.getSelectedIndex()!=-1 && !ware.getSelectedItem().toString().equals("All"))
        {    String memty=ware.getSelectedItem().toString();
             qtRev_List_all2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();
             
             try {
                               qtdisc_List_Report2 = BillQTCancellationModel.Loadbill_rev(m_App,memty,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all2  = (List<BillQTCancellationModel.GetSetMethod2>)  qtdisc_List_Report2.getbill_revList2();
             DataSourceProvider data2 = new DataSourceProvider(qtRev_List_all2);
             DataSourceForBillCancellation dsfc1 = new DataSourceForBillCancellation(qtRev_List_all2);
             data2.setDataSource(dsfc1);
             
           Map reportparams = new HashMap();
           reportparams.put("companyName", m_App.getSession().getCompanyName());  
           reportparams.put("WarehouseName",ware.getSelectedItem().toString());
           reportparams.put("Date", date);
           reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillQTCancellationReport1.jrxml", reportparams, false, data2, true, null);
        }else{
             JOptionPane.showMessageDialog(this, "Select Warehouse First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }
         
         }else{  
        JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
         }
         }else{  
        JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
         }                        
    }
      else if(bill_rdo.isSelected() && period_Rdo.isSelected())
           {
               if(frmDate.getText()!= null && frmDate.getText().toString().length()>0 ){
                   if( toDate.getText()!=null && toDate.getText().toString().length()>0)
               {
           if( ware.getSelectedIndex()!=-1 && ware.getSelectedItem().toString().equals("All"))
           {
               qtRev_List_all2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();
             
             try {
                               qtdisc_List_Report2 = BillQTCancellationModel.LoadAllbill_discPeriod(m_App,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all2  = (List<BillQTCancellationModel.GetSetMethod2>)  qtdisc_List_Report2.getbill_revList2();
             DataSourceProvider data1 = new DataSourceProvider(qtRev_List_all2);
                        DataSourceForBillCancellation dsfc = new DataSourceForBillCancellation(qtRev_List_all2);
                        data1.setDataSource(dsfc);
             
           Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName()); 
             reportparams.put("WarehouseName", ware.getSelectedItem().toString());
             reportparams.put("Date", date);
             reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillQTCancellationReport1.jrxml", reportparams, false, data1, true, null);
              
               
           }else if( ware.getSelectedIndex()!=-1 && !ware.getSelectedItem().toString().equals("All"))
           {
               String memty=ware.getSelectedItem().toString();
            qtRev_List_all2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();
             
             try {
                               qtdisc_List_Report2 = BillQTCancellationModel.Loadbill_revPeriod(m_App,memty,fromDate,toDat);
                              } 

                             catch (BasicException ex) {
                                 Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                            }
              qtRev_List_all2 = (List<BillQTCancellationModel.GetSetMethod2>)  qtdisc_List_Report2.getbill_revList2();
             DataSourceProvider data1 = new DataSourceProvider(qtRev_List_all);
                        DataSourceForBillCancellation dsfc = new DataSourceForBillCancellation(qtRev_List_all2);
                        data1.setDataSource(dsfc);
            
           Map reportparams = new HashMap();
            reportparams.put("companyName", m_App.getSession().getCompanyName());  
            reportparams.put("WarehouseName", ware.getSelectedItem().toString());
            reportparams.put("Date", date);
            reportparams.put("fromDate", fromDate);
             reportparams.put("toDat", toDat);
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BillQTCancellationReport1.jrxml", reportparams, false, data1, true, null);
           }else{
             JOptionPane.showMessageDialog(this, "Select Warehouse First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }
         }else{  
        JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
         }
           }else{  
        JOptionPane.showMessageDialog(this, "Select Date First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
         }                       
    }
         // frmDate.setText(null);
        //  toDate.setText(null);  
    }//GEN-LAST:event_jButton1ActionPerformed


    public void loadCombo()
    {
        try {
            lst= new StaticSentence(m_App.getSession(), "SELECT Name FROM locations WHERE stock=TRUE order by name",
                    SerializerWriteString.INSTANCE,
                    SerializerReadString.INSTANCE).list();
        } catch (BasicException ex) {
            Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DateSelect;
    private javax.swing.JButton DateSelect1;
    private javax.swing.JButton DateSelect2;
    private javax.swing.JRadioButton bill_rdo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField frmDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton month_Rdo;
    private javax.swing.JRadioButton period_Rdo;
    private javax.swing.JRadioButton qt_rdo;
    private javax.swing.JTextField toDate;
    private javax.swing.JComboBox ware;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
   return "Bill/QT Cancellation Report";  
    }

    @Override
    public void activate() throws BasicException {
        
        month_Rdo.setSelected(true);
        bill_rdo.setSelected(true);
        DateSelect2.setVisible(true);
        DateSelect.setVisible(false);
         DateSelect1.setVisible(false);
         loadCombo();
         lst.add(0, "All");
         wareCombo=new ComboBoxValModel(lst);
         ware.setModel(wareCombo);
         ware.setSelectedIndex(-1);
    }

    @Override
    public boolean deactivate() {

     return true;
    
        }    
    @Override
    public JComponent getComponent() {

    return this;
    
    }

    @Override
    public void init(AppView app) throws BeanFactoryException {
      m_App=app;
   
    }

    @Override
    public Object getBean() {
  
    return this;
    }
}



