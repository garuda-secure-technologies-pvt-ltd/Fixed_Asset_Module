/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author user
 */
public class KioskGuestEntryReport extends javax.swing.JPanel implements JPanelView,BeanFactoryApp  {

    /**
     * Creates new form KioskGuestEntryReport
     */
    
     private AppView m_App;
     private KioskGuestEntryReportTableModel kgertm;
     private Date fromDate;
        private Date toDat;
        
     
    public KioskGuestEntryReport() {
        initComponents();
    }

    public void activate() throws BasicException {
        
    }
    
    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        frmDate = new javax.swing.JTextField();
        toDate = new javax.swing.JTextField();
        DateSelect = new javax.swing.JButton();
        DateSelect1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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
        Print = new javax.swing.JButton();

        DateSelect.setText("Date");
        DateSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSelectActionPerformed(evt);
            }
        });

        DateSelect1.setText("Date");
        DateSelect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSelect1ActionPerformed(evt);
            }
        });

        jButton1.setText("Execute");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        Print.setText("Print");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DateSelect1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(frmDate)
                            .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(frmDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateSelect))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateSelect1)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Print)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(frmDate.getText()!=null && frmDate.getText().length()>2 && toDate.getText()!=null && toDate.getText().length()>2 )
        {
            try
            {
                fromDate = (Date) Formats.TIMESTAMP.parseValue(frmDate.getText());
                toDat = (Date) Formats.TIMESTAMP.parseValue(toDate.getText());
                kgertm = KioskGuestEntryReportTableModel.getReport(fromDate, toDat, m_App);
                jTable1.setModel(kgertm.getTableModel());
                
                
                
                
            }catch(BasicException  e)
            {
                
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select Date", "Select Date", JOptionPane.ERROR_MESSAGE);
        }
         
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
 DataSourceProvider data1 = new DataSourceProvider(kgertm.getList());
 SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");
 DataSourceForGEMemberKiosk data = new DataSourceForGEMemberKiosk(kgertm.getList());
 data1.setDataSource(data);
 
 
                Map reportparams = new HashMap();
                reportparams.put("companyName", m_App.getSession().getCompanyName());
                reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
                reportparams.put("fromDate", sdfFrom.format(fromDate));
                reportparams.put("toDate", sdfFrom.format(toDat));
        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/MemberKiosk.jrxml", reportparams, false, data1, true, null);
        
    }//GEN-LAST:event_PrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DateSelect;
    private javax.swing.JButton DateSelect1;
    private javax.swing.JButton Print;
    private javax.swing.JTextField frmDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField toDate;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Kiosk Report"; //To change body of generated methods, choose Tools | Templates.
    }

    

    public boolean deactivate() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    public JComponent getComponent() {
        return this;//To change body of generated methods, choose Tools | Templates.
    }

    

    public Object getBean() {
       return this; //To change body of generated methods, choose Tools | Templates.
    }
}
