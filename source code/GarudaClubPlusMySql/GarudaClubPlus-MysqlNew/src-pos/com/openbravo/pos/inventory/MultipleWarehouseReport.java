/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MultipleWarehouseReport.java
 *
 * Created on 14-Dec-2011, 12:03:40
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.inventory.MultipleWarehouseModel.MultipleWarehouse;
import com.openbravo.pos.inventory.MultipleWarehouseModel.consolidate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class MultipleWarehouseReport extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private DataLogicFacilities dlfac;
    private DataLogicSales dlsales;
    private AppView m_App;
    private ComboBoxValModel consol;
    private MultipleWarehouseModel wmodel;

    int DayWiseFlag=1;
    
    /** Creates new form MultipleWarehouseReport */
    public MultipleWarehouseReport() {
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fromdate = new javax.swing.JTextField();
        toDate = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel1.setName("jPanel1"); // NOI18N

        jButton1.setText("Generate");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Reports");
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel3.setText("From Date");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("To Date");
        jLabel4.setName("jLabel4"); // NOI18N

        fromdate.setName("fromdate"); // NOI18N
        fromdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdateActionPerformed(evt);
            }
        });

        toDate.setName("toDate"); // NOI18N

        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(194, 194, 194))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromdate, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(toDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                    .addContainerGap(309, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fromdate)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel4)
                    .addContainerGap(73, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBox1.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "select any report type");
            } else {
                launchReport();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            fromdate.setText(Formats.TIMESTAMP.formatValue(date));

            MultipleWarehouse mwmodel = (MultipleWarehouse) consol.getSelectedItem();
            if (mwmodel.getReporttype().equals("103")) {
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date);
                cal1.set(Calendar.HOUR_OF_DAY, 23);
                cal1.set(Calendar.MINUTE, 59);
                cal1.set(Calendar.SECOND, 59);
                cal1.set(Calendar.MILLISECOND, 59);
                //cal1.set(Calendar.AM_PM, Calendar.PM);
                cal1.getTime();
                if(DayWiseFlag==1){
                    toDate.setText(Formats.TIMESTAMP.formatValue(cal1.getTime()));
                }
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(date.getTime());
                cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
                date.setTime(cal.getTimeInMillis());
                fromdate.setText(Formats.TIMESTAMP.formatValue(date));
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date);
                cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
                cal1.set(Calendar.HOUR_OF_DAY, 23);
                cal1.set(Calendar.MINUTE, 59);
                cal1.set(Calendar.SECOND, 59);
                cal1.set(Calendar.MILLISECOND, 59);
                //cal1.set(Calendar.AM_PM, Calendar.PM);
                cal1.getTime();
                
                if(DayWiseFlag==1){
                    toDate.setText(Formats.TIMESTAMP.formatValue(cal1.getTime()));
                }
                
                
            }
        }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(toDate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            toDate.setText(Formats.TIMESTAMP.formatValue(date));
        }
}//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        
         if(jComboBox1.getSelectedIndex()!=-1){
        
             fromdate.setText(null);
             toDate.setText(null);
             
            try {
                jButton2.setVisible(true);







                String TypeOfReport = jComboBox1.getSelectedItem().toString().trim();

                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), " SELECT REPORTWISE FROM consolidatereport WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(TypeOfReport);
                if(obj1!=null){
                    String ReportType = obj1[0].toString();
                    if(ReportType.equals("101")){
                        jButton3.setVisible(false);
                        DayWiseFlag=1;
                    }
                    if(ReportType.equals("102")){
                        jButton3.setVisible(true);
                        DayWiseFlag=0;
                    }
                    
                    
                    
                }





                } catch (Exception e) {
                   e.printStackTrace();
               }
         }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void fromdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fromdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField toDate;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Sale Report";
    }

    public void activate() throws BasicException {
        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dlsales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        consol = new ComboBoxValModel();
    }

    public Object getBean() {
        return this;
    }

    private void load() throws BasicException {
        toDate.setEnabled(false);
       // jButton3.setVisible(false);
        fromdate.setEnabled(false);
        jButton2.setVisible(false);
       // fromdate.setText(null);
        toDate.setText(null);
        List<MultipleWarehouse> list = dlsales.getConsolidateReports();
        consol = new ComboBoxValModel(list);
        jComboBox1.setModel(consol);
    }

    private Date getDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
//        cal.set(Calendar.HOUR_OF_DAY, 23);
//        cal.set(Calendar.MINUTE, 59);
//        cal.set(Calendar.SECOND, 59);
//        cal.set(Calendar.MILLISECOND, 59);
//        cal.set(Calendar.AM_PM, Calendar.PM);
        d.setTime(cal.getTimeInMillis());
        return d;



    }

    private void launchReport() throws BasicException {
        MultipleWarehouse mwmodel = (MultipleWarehouse) consol.getSelectedItem();
      //  if (mwmodel.getReportwise().equals("101")) {
           // if (mwmodel.getReporttype().equals("103")) {
                wmodel = new MultipleWarehouseModel();
                MultipleWarehouseModel m = wmodel.loadInstance(m_App, mwmodel.getLocations(), getDate(fromdate.getText()), getDate(toDate.getText()),mwmodel.getParent());
               wmodel = m.calculate(m_App,mwmodel.getLocations());
               
                String[] loc = null;
                Object[] obj = null;
                String WarehouseReport="( ";
                if (mwmodel.getLocations() != null) {
                    loc = mwmodel.getLocations().split("#");
                }
               
                for(int i=0;i<loc.length ; i++){
                    String WareHouseName="";
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), " SELECT NAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(loc[i].toString());
                    if(obj1!=null){
                        WareHouseName = obj1[0].toString();
                    }
                    if(i==loc.length-1){
                         WarehouseReport = WarehouseReport+WareHouseName+" )";
                    }
                    else{
                         WarehouseReport = WarehouseReport+WareHouseName+" , ";
                    }
                   
                }
               System.out.println(WarehouseReport);
               launch(wmodel.getCons() , WarehouseReport);
          //  } 
            
            
            //else {
           // }
            

      //  } 
        
       // else {
         //   if (mwmodel.getReporttype().equals("103")) {
        ///    } else {
         //   }
       // }

    }

    private void launch(List<consolidate> list , String WarehouseReport) throws BasicException {
        Map reportparams = new HashMap();
        String Header = "From : "+fromdate.getText()+"  To : "+toDate.getText()+".";
        
        reportparams.put("companyName", m_App.getSession().getCompanyName());
        reportparams.put("companyAddress", m_App.getSession().getCompanyAddress());
        reportparams.put("fdate", Header );
        reportparams.put("Warehouse", WarehouseReport );
        DataSourceProvider data1 = new DataSourceProvider(list);
        DataSourceForDialyConsolidateReport ds = new DataSourceForDialyConsolidateReport(list);
        data1.setDataSource(ds);
        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/consolidate.jrxml", reportparams, false, data1, true, null);

    }
}