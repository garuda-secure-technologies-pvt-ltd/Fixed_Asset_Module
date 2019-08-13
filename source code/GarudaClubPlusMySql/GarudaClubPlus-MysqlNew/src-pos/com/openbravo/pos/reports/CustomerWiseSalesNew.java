/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailSentTableModel;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import com.openbravo.format.Formats;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.pos.Booking.DataSourceForBilledReports_Halls;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.reports.CuetomerwiseSalesTableModel.CustSalesInfo;
import net.sf.jasperreports.engine.JasperPrint;

public class CustomerWiseSalesNew extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    
    private AppView m_App;
    private CustomerInfoExt customer;
    private DataLogicSales dlSales = null;
    private List<String> WarehouseList = new ArrayList<String>();
    private ComboBoxValModel WarehouseListModel ; 
    private CuetomerwiseSalesTableModel CuetomerwiseSales_Table_Model;
    private List<CuetomerwiseSalesTableModel.CustSalesInfo> CustomerWiseSaleList;
    
    
    public CustomerWiseSalesNew() {
        initComponents();
         All_warehouse_radio.setSelected(true);
         all_members_radio.setSelected(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jLabel2 = new javax.swing.JLabel();
        all_members_radio = new javax.swing.JRadioButton();
        individual_mem_Radio = new javax.swing.JRadioButton();
        member_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        memno_text = new javax.swing.JTextField();
        memname_text = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        reset_btn = new javax.swing.JButton();
        All_warehouse_radio = new javax.swing.JRadioButton();
        select_Warehouse_Radio = new javax.swing.JRadioButton();
        warehouse_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        warehouse_combo = new javax.swing.JComboBox();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(154, 5, 32));
        jLabel1.setText("Memberwise Sales");

        month_radio.setForeground(new java.awt.Color(11, 12, 252));
        month_radio.setText("Month ");
        month_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_radioItemStateChanged(evt);
            }
        });

        period_radio.setForeground(new java.awt.Color(31, 53, 249));
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

        jLabel2.setForeground(new java.awt.Color(26, 28, 254));
        jLabel2.setText("Members  : ");

        all_members_radio.setText("All");
        all_members_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                all_members_radioItemStateChanged(evt);
            }
        });

        individual_mem_Radio.setText("Individual");

        jLabel3.setText("Membership  No : ");

        jLabel4.setText("Member Name : ");

        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout member_panelLayout = new javax.swing.GroupLayout(member_panel);
        member_panel.setLayout(member_panelLayout);
        member_panelLayout.setHorizontalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(memname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        member_panelLayout.setVerticalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(memname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        memname_text.setEditable(false);

        jButton1.setForeground(new java.awt.Color(248, 28, 28));
        jButton1.setText("Generate Report ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(26, 28, 254));
        jLabel7.setText("Warehouse : ");

        reset_btn.setForeground(new java.awt.Color(239, 12, 12));
        reset_btn.setText("Reset");
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        All_warehouse_radio.setText("All ");
        All_warehouse_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                All_warehouse_radioItemStateChanged(evt);
            }
        });
        All_warehouse_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                All_warehouse_radioActionPerformed(evt);
            }
        });

        select_Warehouse_Radio.setText("Select Warehouse");

        jLabel8.setText("WareHouse : ");

        warehouse_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        warehouse_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehouse_comboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout warehouse_panelLayout = new javax.swing.GroupLayout(warehouse_panel);
        warehouse_panel.setLayout(warehouse_panelLayout);
        warehouse_panelLayout.setHorizontalGroup(
            warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        warehouse_panelLayout.setVerticalGroup(
            warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(warehouse_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(warehouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(warehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warehouse_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(all_members_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(individual_mem_Radio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(month_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(period_radio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fromdate_text, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(todate_text))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(All_warehouse_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(select_Warehouse_Radio)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addGap(324, 324, 324))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(period_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(todate_cal))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(all_members_radio)
                    .addComponent(individual_mem_Radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(All_warehouse_radio)
                    .addComponent(select_Warehouse_Radio))
                .addGap(30, 30, 30)
                .addComponent(warehouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(reset_btn))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);

        jTabbedPane1.addTab("Memberwise Sales Menu ", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void all_members_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_all_members_radioItemStateChanged
       if(all_members_radio.isSelected()){
           member_panel.setVisible(false);
           
           
       }
       else{
           
           member_panel.setVisible(true);
           
       }
    }//GEN-LAST:event_all_members_radioItemStateChanged

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
        reset();
    }//GEN-LAST:event_reset_btnActionPerformed

    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
         // TODO add your handling code here:
        // String cust=null;
        String custoid;

        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            String cust = memno_text.getText().trim();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());                      // #CHANGE BY AAKASH... ON 6TH DEC 2013
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                    memname_text.setText(customer.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_memno_textKeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                memno_text.requestFocus();
                memno_text.setText(null);
                memname_text.setText(null);
               

            }
        }); //akash:to read a card from card reader without port num
    }//GEN-LAST:event_jButton10ActionPerformed

    private void All_warehouse_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_All_warehouse_radioItemStateChanged
       if(All_warehouse_radio.isSelected()){
           
           warehouse_panel.setVisible(false);
           
       }
       else{
           
           warehouse_panel.setVisible(true);
           
       }
    }//GEN-LAST:event_All_warehouse_radioItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   if(fromdate_text.getText()!=null && todate_text.getText()!=null && fromdate_text.getText().trim().length()>0 && todate_text.getText().trim().length()>0){
  // select member   
       
       Date FmDate = null;
        Date ToDate = null;
        try {
                 FmDate = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                 ToDate = (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
        } catch (BasicException ex) {
                Logger.getLogger(CustomerWiseSalesNew.class.getName()).log(Level.SEVERE, null, ex);
            }

       
       
       
           if(all_members_radio.isSelected()){
              
                    if(All_warehouse_radio.isSelected()){


                        CustomerWiseSaleList = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
                        try{
                            
                            CuetomerwiseSales_Table_Model = CuetomerwiseSalesTableModel.LoadCustWiseSaleAll(m_App, FmDate, ToDate);
                         }
                        catch(BasicException ex){
                             Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        CustomerWiseSaleList = (List<CuetomerwiseSalesTableModel.CustSalesInfo>) CuetomerwiseSales_Table_Model.getCustomerWiseList();
                        
                         DataSourceProvider data1 = new DataSourceProvider(CustomerWiseSaleList);
                         DataSourceForCustomerWiseSaleNew dsfc = new DataSourceForCustomerWiseSaleNew(CustomerWiseSaleList);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                       //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                       //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                       //  String ReportHeader = "Customerwise Sales ";
                         String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("RHEADER",RPH);
                         reportparams.put("WAREHOUSE","All");
                       //  reportparams.put("date",new Date());
                         
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CustomerWiseSalesNew.jrxml", reportparams, false, data1, true, null); 
                         
                        



                    }
                   if(select_Warehouse_Radio.isSelected()){
                       if(warehouse_combo.getSelectedIndex()!=-1){

                           String WarehouseName = warehouse_combo.getSelectedItem().toString();
                           String WID = null;
                                try{
                                    WID=getWareHouseIdByName(m_App, WarehouseName);
                                }
                                catch(BasicException e){

                                }
                                
                                 
                           
                            CustomerWiseSaleList = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
                                try{

                                    CuetomerwiseSales_Table_Model = CuetomerwiseSalesTableModel.LoadCustWiseSaleAllwithLocation(m_App, FmDate, ToDate , WID);
                                 }
                                catch(BasicException ex){
                                     Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                CustomerWiseSaleList = (List<CuetomerwiseSalesTableModel.CustSalesInfo>) CuetomerwiseSales_Table_Model.getCustomerWiseList();

                                 DataSourceProvider data1 = new DataSourceProvider(CustomerWiseSaleList);
                                 DataSourceForCustomerWiseSaleNew dsfc = new DataSourceForCustomerWiseSaleNew(CustomerWiseSaleList);
                                 data1.setDataSource(dsfc);
                                 Map reportparams = new HashMap();
                               //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                               //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                 
                                  String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                                 reportparams.put("RHEADER",RPH);
                                 reportparams.put("WAREHOUSE",WarehouseName);
                               //  reportparams.put("date",new Date());


                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CustomerWiseSalesNew.jrxml", reportparams, false, data1, true, null); 


                           
                           
                           

                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Please select Warehouse ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
                    }
            } 
           
           
           
           
   // individual members..............        
           
           
           if(individual_mem_Radio.isSelected()){
               if(customer!=null){
               
               
                    if(All_warehouse_radio.isSelected()){

                        String CustomerId = customer.getId();
                        
                        CustomerWiseSaleList = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
                        try{
                            
                            CuetomerwiseSales_Table_Model = CuetomerwiseSalesTableModel.LoadCustWiseSaleOneCustwithAllLocation(m_App, FmDate, ToDate , CustomerId);
                         }
                        catch(BasicException ex){
                             Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        CustomerWiseSaleList = (List<CuetomerwiseSalesTableModel.CustSalesInfo>) CuetomerwiseSales_Table_Model.getCustomerWiseList();
                        
                         DataSourceProvider data1 = new DataSourceProvider(CustomerWiseSaleList);
                         DataSourceForCustomerWiseSaleNew dsfc = new DataSourceForCustomerWiseSaleNew(CustomerWiseSaleList);
                         data1.setDataSource(dsfc);
                         Map reportparams = new HashMap();
                       //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                       //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                        
                         String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                         reportparams.put("RHEADER",RPH);
                         reportparams.put("WAREHOUSE","All");
                       //  reportparams.put("date",new Date());
                         
                        
                        JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CustomerWiseSalesNew.jrxml", reportparams, false, data1, true, null); 
                        
                        
                        




                      }
                    if(select_Warehouse_Radio.isSelected()){
                        if(warehouse_combo.getSelectedIndex()!=-1){

                            String CustomerId = customer.getId();
                            String WarehouseName = warehouse_combo.getSelectedItem().toString();
                            String WID = null;
                                try{
                                    WID=getWareHouseIdByName(m_App, WarehouseName);
                                }
                                catch(BasicException e){

                                }
                                
                        CustomerWiseSaleList = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
                       try{

                           CuetomerwiseSales_Table_Model = CuetomerwiseSalesTableModel.LoadCustWiseSaleOneCustwithLocation(m_App, FmDate, ToDate , WID , CustomerId);
                        }
                       catch(BasicException ex){
                            Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       CustomerWiseSaleList = (List<CuetomerwiseSalesTableModel.CustSalesInfo>) CuetomerwiseSales_Table_Model.getCustomerWiseList();

                        DataSourceProvider data1 = new DataSourceProvider(CustomerWiseSaleList);
                        DataSourceForCustomerWiseSaleNew dsfc = new DataSourceForCustomerWiseSaleNew(CustomerWiseSaleList);
                        data1.setDataSource(dsfc);
                        Map reportparams = new HashMap();
                      //  reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                      //  reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                        
                        String RPH = "  From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                        reportparams.put("RHEADER",RPH);
                        reportparams.put("WAREHOUSE",WarehouseName);
                      //  reportparams.put("date",new Date());


                       JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/CustomerWiseSalesNew.jrxml", reportparams, false, data1, true, null); 



                                
                                
                                
                                
                                
                            
                            
                            
                            
                            


                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Please select Warehouse ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                        }
                      }
               }
               else{
                    JOptionPane.showMessageDialog(this, "Please select Member ..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
               }
           }
            
            
        }
        else{
             JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void All_warehouse_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_All_warehouse_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_All_warehouse_radioActionPerformed

    private void warehouse_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouse_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_warehouse_comboActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton All_warehouse_radio;
    private javax.swing.JButton Month_cal;
    private javax.swing.JRadioButton all_members_radio;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JRadioButton individual_mem_Radio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel member_panel;
    private javax.swing.JTextField memname_text;
    private javax.swing.JTextField memno_text;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JButton reset_btn;
    private javax.swing.JRadioButton select_Warehouse_Radio;
    private javax.swing.JButton todate_cal;
    private javax.swing.JTextField todate_text;
    private javax.swing.JComboBox warehouse_combo;
    private javax.swing.JPanel warehouse_panel;
    // End of variables declaration//GEN-END:variables


    public String getTitle() {
      return "Customerwise sales";
    }

    public void activate() throws BasicException {
        
       
        month_radio.setSelected(true);
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
        m_App=app;
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        
        
        
    }

    public Object getBean() {
        return this;
    }


 public void loaddata() throws BasicException{
       reset();
       
        WarehouseList =  getWareHouseList(m_App);
        WarehouseListModel =new ComboBoxValModel(WarehouseList);
        warehouse_combo.setModel(WarehouseListModel);
       
       
       
       
 }

 private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
       ButtonGroup bg2 = new ButtonGroup();
        bg2.add(all_members_radio);
        bg2.add(individual_mem_Radio);
       
        
       ButtonGroup bg3 = new ButtonGroup();
        bg3.add(All_warehouse_radio);
        bg3.add(select_Warehouse_Radio);
    }

    public void reset(){
        member_panel.setVisible(false);
        month_radio.setSelected(true);
        all_members_radio.setSelected(true);
        memno_text.setText(null);
        memname_text.setText(null);
        fromdate_text.setText(null);
        todate_text.setText(null);
        warehouse_combo.setSelectedIndex(-1);
        All_warehouse_radio.setSelected(true);
        warehouse_panel.setVisible(false);
    }
 
    
     public List getWareHouseList(AppView app ) throws BasicException{
          List<Object> WarehouseList = new ArrayList<Object>();
           WarehouseList  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM LOCATIONS  ORDER BY NAME ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return WarehouseList;
      }
    
     
     public String getWareHouseIdByName(AppView app , String ID) throws BasicException{
          Object o = null;
           o  =  new StaticSentence(app.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME =?   ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(ID);
          
          if(o!=null){
              return o.toString();
          }
          else{
              return null; 
          }
         
      }
     
}
