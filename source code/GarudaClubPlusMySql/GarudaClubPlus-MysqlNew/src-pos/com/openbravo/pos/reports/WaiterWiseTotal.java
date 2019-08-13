/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WaiterWiseTotal.java
 *
 * Created on 21-Dec-2011, 10:25:22
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactory;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.reports.WaiterWiseTotalModel.waiterTotalSales;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class WaiterWiseTotal extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private SentenceList m_sentlocations;
    private ComboBoxValModel m_LocationsModel;
    private String location;
    private DataLogicSales dlSales;
    private AppView m_App;
    private WaiterWiseTotalModel wmodel;

    /** Creates new form WaiterWiseTotal */
    public WaiterWiseTotal() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTxtStartDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTxtEndDate = new javax.swing.JTextField();
        btnDateStart = new javax.swing.JButton();
        btnDateEnd = new javax.swing.JButton();
        m_jLocation = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setLayout(null);

        jButton1.setText("Report");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(70, 170, 100, 23);

        jLabel1.setText(AppLocal.getIntString("Label.StartDate")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 20, 120, 20);

        jTxtStartDate.setName("jTxtStartDate"); // NOI18N
        add(jTxtStartDate);
        jTxtStartDate.setBounds(140, 20, 200, 20);

        jLabel2.setText(AppLocal.getIntString("Label.EndDate")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 50, 120, 20);

        jTxtEndDate.setName("jTxtEndDate"); // NOI18N
        add(jTxtEndDate);
        jTxtEndDate.setBounds(140, 50, 200, 20);

        btnDateStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        btnDateStart.setName("btnDateStart"); // NOI18N
        btnDateStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateStartActionPerformed(evt);
            }
        });
        add(btnDateStart);
        btnDateStart.setBounds(350, 20, 49, 25);

        btnDateEnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        btnDateEnd.setName("btnDateEnd"); // NOI18N
        btnDateEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateEndActionPerformed(evt);
            }
        });
        add(btnDateEnd);
        btnDateEnd.setBounds(350, 50, 49, 25);

        m_jLocation.setName("m_jLocation"); // NOI18N
        m_jLocation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jLocationItemStateChanged(evt);
            }
        });
        m_jLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jLocationActionPerformed(evt);
            }
        });
        add(m_jLocation);
        m_jLocation.setBounds(140, 90, 200, 20);

        jLabel8.setText(AppLocal.getIntString("label.warehouse")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        add(jLabel8);
        jLabel8.setBounds(20, 90, 110, 20);

        jButton2.setText("Credit Bills");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(220, 170, 110, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDateStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateStartActionPerformed

        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTxtStartDate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTxtStartDate.setText(Formats.TIMESTAMP.formatValue(date));
        }
}//GEN-LAST:event_btnDateStartActionPerformed

    private void btnDateEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateEndActionPerformed

        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTxtEndDate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTxtEndDate.setText(Formats.TIMESTAMP.formatValue(date));
        }
}//GEN-LAST:event_btnDateEndActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (jTxtStartDate.getText().length() > 0 && jTxtEndDate.getText().length() > 0 && location != null) {
                generateReport(getDate(jTxtStartDate.getText()), getDate(jTxtEndDate.getText()), location);
            } else {
                JOptionPane.showMessageDialog(this, "select fromdate,todate and warehouse", "incomplte form", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void m_jLocationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jLocationItemStateChanged
        // TODO add your handling code here:
        try {
            if (m_jLocation.getSelectedIndex() != -1) {
                LocationInfo linfo = (LocationInfo) m_LocationsModel.getSelectedItem();
                location = linfo.getID();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_m_jLocationItemStateChanged

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    try {
        if (jTxtStartDate.getText().length() > 0 && jTxtEndDate.getText().length() > 0 && location != null) {
            generateReport1(getDate(jTxtStartDate.getText()), getDate(jTxtEndDate.getText()), location);
        } else {
            JOptionPane.showMessageDialog(this, "select fromdate,todate and warehouse", "incomplte form", JOptionPane.WARNING_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}//GEN-LAST:event_jButton2ActionPerformed

    private void m_jLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jLocationActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_m_jLocationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDateEnd;
    private javax.swing.JButton btnDateStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTxtEndDate;
    private javax.swing.JTextField jTxtStartDate;
    private javax.swing.JComboBox m_jLocation;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "WaiterWiseTotalSales";
    }

    public void activate() throws BasicException {
        jTxtStartDate.setText(null);
        jTxtEndDate.setText(null);
        m_sentlocations = dlSales.getLocationsList();
        m_LocationsModel = new ComboBoxValModel(m_sentlocations.list());
        m_jLocation.setModel(m_LocationsModel);

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_LocationsModel = new ComboBoxValModel();
        m_App = app;
    }

    public Object getBean() {
        return this;
    }

    private Date getDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        d.setTime(cal.getTimeInMillis());
        return d;
    }

    public void generateReport(Date from, Date to, String location) throws BasicException {
        wmodel = new WaiterWiseTotalModel();
        Object[] values = new Object[]{from, to, location, from, to, location, from, to, location, from, to, location};
        wmodel = wmodel.loadInstance(m_App, values);
        launch(wmodel.getWaiterlist());

    }

    public void generateReport1(Date from, Date to, String location) throws BasicException {
        wmodel = new WaiterWiseTotalModel();
        Object[] values = new Object[]{from, to, location, from, to, location};
        wmodel = wmodel.loadInstanceCredit(m_App, values);
        launch1(wmodel.getWaitercreditlist());

    }

    private void launch(List<waiterTotalSales> list) throws BasicException {
        Map reportparams = new HashMap();
        reportparams.put("companyName", m_App.getSession().getCompanyName());
        reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
        reportparams.put("startdate", getDate(jTxtStartDate.getText()));
        reportparams.put("enddate", getDate(jTxtEndDate.getText()));
        DataSourceProvider data1 = new DataSourceProvider(list);
        DataSourceWaiterTotal ds = new DataSourceWaiterTotal(list);
        data1.setDataSource(ds);
        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/waiterbillwisenew.jrxml", reportparams, false, data1, true, null);

    }

    private void launch1(List<WaiterWiseTotalModel.Creditbill> list) throws BasicException {
        Map reportparams = new HashMap();
        reportparams.put("companyName", m_App.getSession().getCompanyName());
        reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
        reportparams.put("startdate", getDate(jTxtStartDate.getText()));
        reportparams.put("enddate", getDate(jTxtEndDate.getText()));
        DataSourceProvider data1 = new DataSourceProvider(list);
        DataSourceCreditBills ds = new DataSourceCreditBills(list);
        data1.setDataSource(ds);
        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CreditBillWiseReport.jrxml", reportparams, false, data1, true, null);

    }
}
