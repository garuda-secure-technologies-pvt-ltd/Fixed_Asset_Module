/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
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
import com.openbravo.pos.sms.SmsSendernew;
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
 * @author garuda
 */
public class JPanelFacilityBilling extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {
    private AppView m_app;
    List<Object> lst1=new ArrayList<Object>();
    List<Object> lst2=new ArrayList<Object>();
    List<Object> lst3=new ArrayList<Object>();
    List<Object> lst4=new ArrayList<Object>();
    List<Object> lst5=new ArrayList<Object>();
    List<Object> lst6=new ArrayList<Object>();
   private ComboBoxValModel FacilityListModel ;
   private ComboBoxValModel OrderbyModel1,OrderbyModel2,OrderbyModel3,OrderbyModel4,OrderbyModel5 ;
    private ArrayList<OrderdFacilityBillReport.FacilityBillInfo> facility_list;
    OrderdFacilityBillReport  OrderFacility_model;
    //private ComboBoxValModel ;
  // private ComboBoxValModel OrderbyModel2 ;
  // private ComboBoxValModel OrderbyModel3 ;
    public JPanelFacilityBilling() {
        initComponents();
    
         lst2.add(0,"Bill Date");
        lst2.add(1,"Clear Date");
        lst2.add(2,"Member No");
        lst5.add(0,"Bill Date");
        lst5.add(1,"Member No");
         
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        facility_combo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        period = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        monthly = new javax.swing.JRadioButton();
        frm_dt = new javax.swing.JTextField();
        to_dt = new javax.swing.JTextField();
        to = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        paid_radio = new javax.swing.JRadioButton();
        clear_till_dt = new javax.swing.JLabel();
        monthly1 = new javax.swing.JRadioButton();
        clear_during = new javax.swing.JLabel();
        period1 = new javax.swing.JRadioButton();
        frm_dt1 = new javax.swing.JTextField();
        to1 = new javax.swing.JLabel();
        to_dt1 = new javax.swing.JTextField();
        mon_btn = new javax.swing.JButton();
        frm_btn = new javax.swing.JButton();
        to_btn = new javax.swing.JButton();
        frm_btn1 = new javax.swing.JButton();
        to_btn1 = new javax.swing.JButton();
        mon_btn1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bill_til_rdo = new javax.swing.JRadioButton();
        bill_during = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        clear_til_rdo = new javax.swing.JRadioButton();
        clr_during_rdo = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        orderby1 = new javax.swing.JComboBox();
        orderby2 = new javax.swing.JComboBox();
        orderby3 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        bill_nt_clr_combo = new javax.swing.JComboBox();
        bill_nt_clr_combo1 = new javax.swing.JComboBox();

        jLabel1.setText("Facility");

        facility_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Billed till Date");

        buttonGroup1.add(period);
        period.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                periodItemStateChanged(evt);
            }
        });
        period.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodActionPerformed(evt);
            }
        });

        jLabel3.setText("Billed Duration");

        buttonGroup1.add(monthly);
        monthly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthlyItemStateChanged(evt);
            }
        });

        frm_dt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frm_dtActionPerformed(evt);
            }
        });

        to_dt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_dtActionPerformed(evt);
            }
        });

        to.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        to.setText("To");

        jLabel5.setText("Bill Cleared");

        paid_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paid_radioItemStateChanged(evt);
            }
        });
        paid_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paid_radioActionPerformed(evt);
            }
        });

        clear_till_dt.setText("Cleared till Date");

        buttonGroup2.add(monthly1);
        monthly1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthly1ItemStateChanged(evt);
            }
        });
        monthly1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthly1ActionPerformed(evt);
            }
        });

        clear_during.setText("Cleared Duration");

        buttonGroup2.add(period1);
        period1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period1ItemStateChanged(evt);
            }
        });

        frm_dt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frm_dt1ActionPerformed(evt);
            }
        });

        to1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        to1.setText("To");

        to_dt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_dt1ActionPerformed(evt);
            }
        });

        mon_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mon_btn.setText("Month");
        mon_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mon_btnActionPerformed(evt);
            }
        });

        frm_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        frm_btn.setText("From");
        frm_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frm_btnActionPerformed(evt);
            }
        });

        to_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        to_btn.setText("To");
        to_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_btnActionPerformed(evt);
            }
        });

        frm_btn1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        frm_btn1.setText("From");
        frm_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frm_btn1ActionPerformed(evt);
            }
        });

        to_btn1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        to_btn1.setText("To");
        to_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_btn1ActionPerformed(evt);
            }
        });

        mon_btn1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mon_btn1.setText("Month");
        mon_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mon_btn1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("From");

        buttonGroup3.add(bill_til_rdo);
        bill_til_rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bill_til_rdoItemStateChanged(evt);
            }
        });

        buttonGroup3.add(bill_during);
        bill_during.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bill_duringItemStateChanged(evt);
            }
        });
        bill_during.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bill_duringActionPerformed(evt);
            }
        });

        jLabel8.setText("Monthly");

        jLabel9.setText("Period");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("From");

        buttonGroup4.add(clear_til_rdo);
        clear_til_rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clear_til_rdoItemStateChanged(evt);
            }
        });
        clear_til_rdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_til_rdoActionPerformed(evt);
            }
        });

        buttonGroup4.add(clr_during_rdo);
        clr_during_rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                clr_during_rdoItemStateChanged(evt);
            }
        });
        clr_during_rdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clr_during_rdoActionPerformed(evt);
            }
        });

        jLabel11.setText("Monthly");

        jLabel12.setText("Period");

        orderby1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderby1ItemStateChanged(evt);
            }
        });
        orderby1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderby1ActionPerformed(evt);
            }
        });

        orderby2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        orderby2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderby2ItemStateChanged(evt);
            }
        });
        orderby2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderby2ActionPerformed(evt);
            }
        });

        orderby3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        orderby3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderby3ItemStateChanged(evt);
            }
        });
        orderby3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderby3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ORDER BY");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 255));
        jButton1.setText("Generate Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bill_nt_clr_combo.setSelectedIndex(-1);
        bill_nt_clr_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bill_nt_clr_comboItemStateChanged(evt);
            }
        });

        bill_nt_clr_combo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paid_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clear_till_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clear_til_rdo)
                                .addGap(34, 34, 34)
                                .addComponent(clear_during, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clr_during_rdo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(orderby2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bill_nt_clr_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(orderby1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bill_nt_clr_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(orderby3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bill_til_rdo)
                            .addComponent(facility_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(monthly)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bill_during))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(frm_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(to_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(monthly1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(period1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(to1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(to_dt1)
                                    .addComponent(frm_dt1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(period)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(to_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(frm_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mon_btn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(frm_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mon_btn1))
                            .addComponent(to_btn))))
                .addContainerGap(351, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {frm_btn, mon_btn, to_btn});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(facility_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bill_til_rdo)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bill_during)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(frm_btn)
                        .addComponent(frm_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mon_btn)
                        .addComponent(jLabel8))
                    .addComponent(monthly, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(to_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(to_btn)
                        .addComponent(jLabel9))
                    .addComponent(period))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paid_radio))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clear_til_rdo)
                    .addComponent(clear_till_dt, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear_during, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clr_during_rdo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthly1)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frm_dt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mon_btn1)
                        .addComponent(frm_btn1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(to_dt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(to_btn1)
                            .addComponent(to1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(period1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderby1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bill_nt_clr_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderby2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bill_nt_clr_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderby3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void paid_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paid_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_radioActionPerformed

    private void periodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodItemStateChanged

       if(period.isSelected()){
         mon_btn.setVisible(false);

            frm_dt.setText(null);
             to_dt.setText(null);
            monthly.setVisible(true);
             period.setVisible(true);
             frm_btn.setVisible(true);
             to_btn.setVisible(true);
             frm_dt.setVisible(true);
             to_dt.setVisible(true);
             jLabel4.setVisible(true);
             to.setVisible(true);
             jLabel8.setVisible(true);
             jLabel9.setVisible(true);

       }



    }//GEN-LAST:event_periodItemStateChanged

    private void monthly1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthly1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthly1ActionPerformed

    private void frm_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frm_btnActionPerformed
      Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frm_dt.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            frm_dt.setText(Formats.TIMESTAMP.formatValue(date));
            to_dt.setText(null);

        }



    }//GEN-LAST:event_frm_btnActionPerformed

    private void frm_dt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frm_dt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frm_dt1ActionPerformed

    private void monthly1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthly1ItemStateChanged

       if(monthly1.isSelected()){
         mon_btn1.setVisible(true);

            frm_dt1.setText(null);
             to_dt1.setText(null);
            monthly1.setVisible(true);
             period1.setVisible(true);
             frm_btn1.setVisible(false);
             to_btn1.setVisible(false);
             frm_dt1.setVisible(true);
             to_dt1.setVisible(true);
             jLabel12.setVisible(true);
             to1.setVisible(true);
             jLabel11.setVisible(true);
             jLabel10.setVisible(true);

       }

    }//GEN-LAST:event_monthly1ItemStateChanged

    private void period1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period1ItemStateChanged

         if(period1.isSelected()){
         mon_btn1.setVisible(false);

            frm_dt1.setText(null);
             to_dt1.setText(null);
            monthly1.setVisible(true);
             period1.setVisible(true);
             frm_btn1.setVisible(true);
             to_btn1.setVisible(true);
             frm_dt1.setVisible(true);
             to_dt1.setVisible(true);
             jLabel12.setVisible(true);
             to1.setVisible(true);
             jLabel10.setVisible(true);
             jLabel11.setVisible(true);

       }


    }//GEN-LAST:event_period1ItemStateChanged

    private void mon_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mon_btnActionPerformed

         Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frm_dt.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            frm_dt.setText(Formats.TIMESTAMP.formatValue(date));

            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.MONTH, 1);

            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());

            to_dt.setText(Formats.TIMESTAMP.formatValue(date));

        }

    }//GEN-LAST:event_mon_btnActionPerformed

    private void frm_dtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frm_dtActionPerformed

    }//GEN-LAST:event_frm_dtActionPerformed

    private void to_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_btnActionPerformed

         Date date;
        if(frm_dt.getText()!=null && frm_dt.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(frm_dt.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                to_dt.setText(Formats.TIMESTAMP.formatValue(date));

            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_to_btnActionPerformed

    private void frm_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frm_btn1ActionPerformed

         Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frm_dt1.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            frm_dt1.setText(Formats.TIMESTAMP.formatValue(date));
            to_dt1.setText(null);

        }

    }//GEN-LAST:event_frm_btn1ActionPerformed

    private void to_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_btn1ActionPerformed

        Date date;
        if(frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(frm_dt1.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                to_dt1.setText(Formats.TIMESTAMP.formatValue(date));

            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_to_btn1ActionPerformed

    private void periodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_periodActionPerformed

    private void to_dtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_dtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_to_dtActionPerformed

    private void bill_duringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bill_duringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bill_duringActionPerformed

    private void clear_til_rdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_til_rdoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clear_til_rdoActionPerformed

    private void clr_during_rdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clr_during_rdoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clr_during_rdoActionPerformed

    private void bill_til_rdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bill_til_rdoItemStateChanged

       
        if(bill_til_rdo.isSelected())
         {
             paid_radio.setSelected(false);
             monthly.setVisible(false);
             period.setVisible(false);
             frm_btn.setVisible(false);
             to_btn.setVisible(false);
             mon_btn.setVisible(false);
             frm_dt.setVisible(false);
             to_dt.setVisible(false);
             jLabel4.setVisible(false);
             to.setVisible(false);
             jLabel8.setVisible(false);
             jLabel9.setVisible(false);
             orderby1.setVisible(false);
             
             orderby2.setVisible(false);
             orderby3.setVisible(false);
             bill_nt_clr_combo.setVisible(true);
             bill_nt_clr_combo.setSelectedIndex(-1);
             bill_nt_clr_combo.setName(null);
             bill_nt_clr_combo1.setVisible(false);
             
           Date date1=new Date();
         }

    }//GEN-LAST:event_bill_til_rdoItemStateChanged

    private void bill_duringItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bill_duringItemStateChanged

             
        
        if(bill_during.isSelected()){
            paid_radio.setSelected(false);
            monthly.setVisible(true);
             period.setVisible(true);
             frm_btn.setVisible(false);
             to_btn.setVisible(false);
             mon_btn.setVisible(true);
             frm_dt.setVisible(true);
             frm_dt.setText(null);
             to_dt.setText(null);
             to_dt.setVisible(true);
             jLabel4.setVisible(true);
             to.setVisible(true);
             jLabel8.setVisible(true);
             jLabel9.setVisible(true);
              monthly.setSelected(true);
              orderby1.setVisible(false);
             
           orderby2.setVisible(false);
           orderby3.setVisible(false);
             bill_nt_clr_combo.setVisible(true);
            bill_nt_clr_combo.setSelectedIndex(-1);
             bill_nt_clr_combo.setName(null);
             bill_nt_clr_combo1.setVisible(false);
            
                }

    }//GEN-LAST:event_bill_duringItemStateChanged

    private void clear_til_rdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clear_til_rdoItemStateChanged

        if(clear_til_rdo.isSelected())
         {
             monthly1.setVisible(false);
             period1.setVisible(false);
             frm_btn1.setVisible(false);
             to_btn1.setVisible(false);
             mon_btn1.setVisible(false);
             frm_dt1.setVisible(false);
             to_dt1.setVisible(false);
             jLabel11.setVisible(false);
             to1.setVisible(false);
             jLabel12.setVisible(false);
             jLabel10.setVisible(false);
             orderby1.setVisible(true);
             orderby1.setName(null);
             orderby2.setVisible(false);
             orderby3.setVisible(false);
             orderby1.setSelectedIndex(-1);
             bill_nt_clr_combo.setVisible(false);
             bill_nt_clr_combo1.setVisible(false);
           Date date1=new Date();
         }


    }//GEN-LAST:event_clear_til_rdoItemStateChanged

    private void clr_during_rdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_clr_during_rdoItemStateChanged


         if(clr_during_rdo.isSelected()){
         monthly1.setVisible(true);
             period1.setVisible(true);
             frm_btn1.setVisible(false);
             to_btn1.setVisible(false);
             mon_btn1.setVisible(true);
             frm_dt1.setVisible(true);
             frm_dt1.setText(null);
             to_dt1.setText(null);
             to_dt1.setVisible(true);
             jLabel11.setVisible(true);
             to1.setVisible(true);
             jLabel12.setVisible(true);
             jLabel10.setVisible(true);
              monthly1.setSelected(true);
              orderby1.setVisible(true);
             orderby1.setName(null);
             orderby2.setVisible(false);
             orderby3.setVisible(false);
             orderby1.setSelectedIndex(-1);
             bill_nt_clr_combo.setVisible(false);
             bill_nt_clr_combo1.setVisible(false);
                }

    }//GEN-LAST:event_clr_during_rdoItemStateChanged

    private void mon_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mon_btn1ActionPerformed

         Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frm_dt1.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            frm_dt1.setText(Formats.TIMESTAMP.formatValue(date));

            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.MONTH, 1);

            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());

            to_dt1.setText(Formats.TIMESTAMP.formatValue(date));


        }

    }//GEN-LAST:event_mon_btn1ActionPerformed

    private void to_dt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_dt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_to_dt1ActionPerformed

    private void orderby1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderby1ItemStateChanged
        
      orderby2.setVisible(false);
       orderby3.setVisible(false);
      
       lst3.clear();
      
     if(orderby1.getSelectedIndex()!=-1){  
       
       
       if(orderby1.getSelectedItem().equals(lst2.get(0)))
      {
                orderby2.setVisible(true);
               lst3.add(0, lst2.get(1));
               lst3.add(1, lst2.get(2));
            OrderbyModel2 = new ComboBoxValModel(lst3);
           orderby2.setModel(OrderbyModel2);
           orderby2.setSelectedIndex(-1);
         

      }else if(orderby1.getSelectedItem().equals(lst2.get(1))){
            orderby2.setVisible(true);
                lst3.add(0, lst2.get(0));
               lst3.add(1, lst2.get(2));
              OrderbyModel2 = new ComboBoxValModel(lst3);
           orderby2.setModel(OrderbyModel2);
           orderby2.setSelectedIndex(-1);
         

      }else{
         orderby2.setVisible(true);
          lst3.add(0, lst2.get(0));
               lst3.add(1, lst2.get(1));
              OrderbyModel2 = new ComboBoxValModel(lst3);
           orderby2.setModel(OrderbyModel2);
           orderby2.setSelectedIndex(-1);
        
           
      }
     }

    }//GEN-LAST:event_orderby1ItemStateChanged

    private void orderby2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderby2ItemStateChanged
        
        orderby3.setVisible(false);
       // orderby3.setSelectedIndex(-1);
        lst4.clear();
        
     if(orderby2.getSelectedIndex()!=-1){   
        
        
         if(orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedItem().equals(lst3.get(0)))
      {
                    orderby3.setVisible(true);
               lst4.add(0, lst3.get(1));
               //lst3.add(1, lst2.get(2));
            OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
           orderby3.setSelectedIndex(0);
      
      }else if(orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedItem().equals(lst3.get(1))){
               // lst3.add(0, lst2.get(0));
          orderby3.setVisible(true);
               lst4.add(0, lst3.get(0));
              OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
           orderby3.setSelectedIndex(0);
   

      }else if(orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedItem().equals(lst3.get(0))){
          orderby3.setVisible(true);
          lst4.add(0, lst3.get(1));
               //lst3.add(1, lst2.get(1));
              OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
           orderby3.setSelectedIndex(0);
     
      }else if(orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedItem().equals(lst3.get(1))){
          orderby3.setVisible(true);
          lst4.add(0, lst3.get(0));
               //lst3.add(1, lst2.get(1));
              OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
           orderby3.setSelectedIndex(0);
     
      }
         else if(orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedItem().equals(lst3.get(0))){
          
             orderby3.setVisible(true);
             lst4.add(0, lst3.get(1));
               //lst3.add(1, lst2.get(1));
              OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
          orderby3.setSelectedIndex(0);
         
      }else {
             
             orderby3.setVisible(true);
             lst4.add(0, lst3.get(0));
             OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
           orderby3.setSelectedIndex(0);
          

         }

     }

    }//GEN-LAST:event_orderby2ItemStateChanged

    private void orderby2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderby2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderby2ActionPerformed

    private void orderby3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderby3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderby3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       
                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                        try {
                             FrmDate =  (Date) Formats.TIMESTAMP.parseValue(frm_dt.getText());
                             ToDate =  (Date) Formats.TIMESTAMP.parseValue(to_dt.getText());
                        } catch (BasicException ex) {
                        }
                         Date FrmDate1 = new Date() ;
                        Date ToDate1 = new Date();
                        try {
                             FrmDate1 =  (Date) Formats.TIMESTAMP.parseValue(frm_dt1.getText());
                             ToDate1 =  (Date) Formats.TIMESTAMP.parseValue(to_dt1.getText());
                        } catch (BasicException ex) {
                        }

       
        if(facility_combo.getSelectedIndex()!=-1 && !facility_combo.getSelectedItem().equals("All"))
       {
        if(bill_til_rdo.isSelected() )
        {
            if(paid_radio.isSelected())//bill till payed
            {
              if(clear_til_rdo.isSelected()) { 
            if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1  &&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_B_R_M(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);
                

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml",reportparams,false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_B_M_R(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_R_B_M(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_R_M_B(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_M_B_R(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_M_R_B(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }

            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }//if end
              else if(clr_during_rdo.isSelected() && monthly1.isSelected()  && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                  //else of clear during
                  if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_B_R_M1(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_B_M_R1(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_R_B_M1(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_R_M_B1(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_M_B_R1(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_M_R_B1(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
                  
                  
                  
              }//else if end clear monyhly
              else if(clr_during_rdo.isSelected() && period1.isSelected()  && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                  //else clear period
                  if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_B_R_M11(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_B_M_R11(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_R_B_M11(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_R_M_B11(m_app,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_M_B_R11(m_app,FrmDate1, ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Clear_Payed_M_R_B11(m_app,FrmDate1, ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
                  
              }//else end for clear during
              else{
                          JOptionPane.showMessageDialog(this, "Please Select Duration..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
              
              
            } //bill cleared not selected
            else{
                if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_B_R_M(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_B_M_R(m_app,date,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
        }

        //------------------------------bill_during monthly paid and not paid (1388-1720)---------------------------
        else if( bill_during.isSelected() && monthly.isSelected() && to_dt.getText()!=null && to_dt.getText().trim().length()>0 && frm_dt.getText()!=null && frm_dt.getText().trim().length()>0)
        {
          
            if(paid_radio.isSelected())//bill till payed
            {
              if(clear_til_rdo.isSelected()){
            if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_B_R_M1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_B_M_R1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);
                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_R_B_M1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_R_M_B1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_M_B_R1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_M_R_B1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
              }else if(clr_during_rdo.isSelected()&& monthly1.isSelected()&&to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                
                //code here
                if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
             
            }//continue here...
        else if(clr_during_rdo.isSelected()&& period1.isSelected()&& to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                
                 if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear1_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear1_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear1_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
              reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear1_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear1_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Clear1_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
               
            }
         else{
                          JOptionPane.showMessageDialog(this, "Please Select Duration..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            
            } //paid end here 
            else{

                if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_B_R_M1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_B_M_R1(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
 
        }
        
        //------////////-----------------bill_during - period -paid and not paid (1722-2049)   --------//////////--------------
        else if( bill_during.isSelected() && period.isSelected() && to_dt.getText()!=null && to_dt.getText().trim().length()>0 && frm_dt.getText()!=null && frm_dt.getText().trim().length()>0)
        {
            
            if(paid_radio.isSelected()  )//bill till payed
            {
               if(clear_til_rdo.isSelected()){
            if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_B_R_M11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_B_M_R11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_R_B_M11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&&orderby1.getSelectedIndex()!=-1&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_R_M_B11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_M_B_R11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_Payed_M_R_B11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
            else if( clr_during_rdo.isSelected()&&monthly1.isSelected() && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0 ){
                
                if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
              }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
              
            }
            else if (clr_during_rdo.isSelected()&&period1.isSelected() && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                
                if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear1_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear1_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear1_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear1_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear1_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate1_Bill_Clear1_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
             
            }
     
            } //paid end for bill during period and clear during monthly,period
            else{
                if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_B_R_M11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.Tilldate_Bill_B_M_R11(m_app,FrmDate , ToDate,facilitytype);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
        
        }
        else{
                          JOptionPane.showMessageDialog(this, "Please Select Duration..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
        
       }
             //FACILITY FIELD ------------- All--------------------STARTS HERE////////////////////////////////////////////////// 
        //all should copy here
        else if(facility_combo.getSelectedIndex()!=-1 && facility_combo.getSelectedItem().equals("All"))
        {
            if(bill_til_rdo.isSelected() )
        {
            if(paid_radio.isSelected())//bill till payed
            {
              if(clear_til_rdo.isSelected()) { 
            if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1  &&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_B_R_M(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml",reportparams,false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_B_M_R(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_R_B_M(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_R_M_B(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_M_B_R(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1 && orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_M_R_B(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }

            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }//if end
              else if(clr_during_rdo.isSelected() && monthly1.isSelected()  && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                  //else of clear during
                  if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_B_R_M1(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_B_M_R1(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_R_B_M1(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_R_M_B1(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_M_B_R1(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_M_R_B1(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
                  
                  
                  
              }//else if end clear monyhly
              else if(clr_during_rdo.isSelected() && period1.isSelected()  && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                  //else clear period
                  if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_B_R_M11(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_B_M_R11(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
               reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_R_B_M11(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                  reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_R_M_B11(m_app,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_M_B_R11(m_app,FrmDate1, ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Clear_Payed_M_R_B11(m_app,FrmDate1, ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
                  
              }//else end for clear during
              else{
                          JOptionPane.showMessageDialog(this, "Please Select Duration..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
              
              
            } //bill cleared not selected
            else{
                if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_B_R_M(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  Date date=new Date();
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_B_M_R(m_app,date);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
        }

        //------------------------------bill_during monthly paid and not paid (1388-1720)---------------------------
        else if( bill_during.isSelected() && monthly.isSelected() && to_dt.getText()!=null && to_dt.getText().trim().length()>0 && frm_dt.getText()!=null && frm_dt.getText().trim().length()>0)
        {
          
            if(paid_radio.isSelected())//bill till payed
            {
              if(clear_til_rdo.isSelected()){
            if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_B_R_M1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_B_M_R1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_R_B_M1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_R_M_B1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_M_B_R1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_M_R_B1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
              }else if(clr_during_rdo.isSelected()&& monthly1.isSelected()&&to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                
                //code here
                if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
             
            }//continue here...
        else if(clr_during_rdo.isSelected()&& period1.isSelected()&& to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                
                 if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear1_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear1_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear1_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear1_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear1_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Clear1_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
               
            }
         else{
                          JOptionPane.showMessageDialog(this, "Please Select Duration..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            
            } //paid end here 
            else{

                if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_B_R_M1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_B_M_R1(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                  reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
 
        }
        
        //------////////-----------------bill_during - period -paid and not paid (1722-2049)   --------//////////--------------
        else if( bill_during.isSelected() && period.isSelected() && to_dt.getText()!=null && to_dt.getText().trim().length()>0 && frm_dt.getText()!=null && frm_dt.getText().trim().length()>0)
        {
            
            if(paid_radio.isSelected()  )//bill till payed
            {
               if(clear_til_rdo.isSelected()){
            if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_B_R_M11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                  reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_B_M_R11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&&orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_R_B_M11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&&orderby1.getSelectedIndex()!=-1&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_R_M_B11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_M_B_R11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                  reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_Payed_M_R_B11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
            else if( clr_during_rdo.isSelected()&&monthly1.isSelected() && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0 ){
                
                if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
              }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
              
            }
            else if (clr_during_rdo.isSelected()&&period1.isSelected() && to_dt1.getText()!=null && to_dt1.getText().trim().length()>0 && frm_dt1.getText()!=null && frm_dt1.getText().trim().length()>0){
                
                if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear1_B_R_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(0))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear1_B_M_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear1_R_B_M1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(1))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear1_R_M_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);
                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if(orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&&orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(0))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                  
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear1_M_B_R1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else if   (orderby1.getSelectedIndex()!=-1&& orderby1.getSelectedItem().equals(lst2.get(2))&& orderby2.getSelectedIndex()!=-1&&orderby2.getSelectedItem().equals(lst3.get(1))&&orderby3.getSelectedItem().equals(lst4.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate1_Bill_Clear1_M_R_B1(m_app,FrmDate , ToDate,FrmDate1 , ToDate1);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
             
            }
     
            } //paid end for bill during period and clear during monthly,period
            else{
                if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
            {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_B_R_M11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                 reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);

            }else if(bill_nt_clr_combo.getSelectedIndex()!=-1&&bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))&&bill_nt_clr_combo1.getSelectedItem().equals(lst6.get(0)))
              {
                String facilitytype = facility_combo.getSelectedItem().toString();
                 
              facility_list = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();

               try {
                               OrderFacility_model  = OrderdFacilityBillReport.All_Tilldate_Bill_B_M_R11(m_app,FrmDate , ToDate);
                              }

                             catch (BasicException ex) {
                                 Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
                            }

               facility_list=(ArrayList<OrderdFacilityBillReport.FacilityBillInfo>) OrderFacility_model.getfacilityBillList();

                DataSourceProvider data1 = new DataSourceProvider(facility_list);
                DataSourceForFacilityBilling dsfc = new  DataSourceForFacilityBilling(facility_list);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                  reportparams.put("CLUBNAME", m_app.getSession().getCompanyName());
                reportparams.put("date",new Date());
                String z = facilitytype + "  ( From : "+frm_dt.getText() + "  To : "+to_dt.getText()+" )";
                reportparams.put("FacilityName",z);

                 JasperPrint jp = JasperReportNew.runReport(m_app, "./reports/com/openbravo/reports/FacilityBillingPymtPosition_Report.jrxml", reportparams, false, data1, true, null);
            }
            else{
                          JOptionPane.showMessageDialog(this, "Please Select OrderBy..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            }
        
        }
        else{
                          JOptionPane.showMessageDialog(this, "Please Select Duration..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }
            
            
        }
        else{
                          JOptionPane.showMessageDialog(this, "Please Select First Field..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
                      }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void orderby1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderby1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderby1ActionPerformed

    private void monthlyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthlyItemStateChanged
        if(monthly.isSelected()){
            mon_btn.setVisible(true);

            monthly.setVisible(true);
            period.setVisible(true);
            frm_btn.setVisible(false);
            to_btn.setVisible(false);
            frm_dt.setVisible(true);
            frm_dt.setText(null);
            to_dt.setText(null);
            to_dt.setVisible(true);
            jLabel4.setVisible(true);
            to.setVisible(true);
            jLabel8.setVisible(true);
            jLabel9.setVisible(true);

        }

    }//GEN-LAST:event_monthlyItemStateChanged

    private void paid_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paid_radioItemStateChanged
        
     
        if(paid_radio.isSelected()==false){
            clear_till_dt.setVisible(false);
             clear_til_rdo.setVisible(false);
            clear_during.setVisible(false);
             clr_during_rdo.setVisible(false);
             monthly1.setVisible(false);
             period1.setVisible(false);
             frm_btn1.setVisible(false);
             to_btn1.setVisible(false);
             mon_btn1.setVisible(false);
             frm_dt1.setVisible(false);
             to_dt1.setVisible(false);
             jLabel11.setVisible(false);
             to1.setVisible(false);
             jLabel12.setVisible(false);
             jLabel10.setVisible(false);
             orderby1.setVisible(false);
             orderby1.setName(null);
             orderby2.setVisible(false);
             orderby3.setVisible(false);
             //orderby1.setSelectedIndex(-1);
             bill_nt_clr_combo.setVisible(true);
             bill_nt_clr_combo1.setVisible(false);
             bill_nt_clr_combo.setSelectedIndex(-1);
            
        }
        else{
            clear_till_dt.setVisible(true);
             clear_til_rdo.setVisible(true);
             clear_til_rdo.setSelected(true);
            clear_during.setVisible(true);
             clr_during_rdo.setVisible(true); 
             
             orderby1.setVisible(true);
             orderby1.setName(null);
             orderby2.setVisible(false);
             orderby3.setVisible(false);
             orderby1.setSelectedIndex(-1);
             bill_nt_clr_combo.setVisible(false);
             bill_nt_clr_combo1.setVisible(false);
        }
       
    }//GEN-LAST:event_paid_radioItemStateChanged

    private void orderby3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderby3ItemStateChanged
      
    }//GEN-LAST:event_orderby3ItemStateChanged

    private void bill_nt_clr_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bill_nt_clr_comboItemStateChanged
       
        
         bill_nt_clr_combo1.setVisible(false);
        //bill_nt_clr_combo.setSelectedIndex(0);
         lst6.clear();
         
      if(bill_nt_clr_combo.getSelectedIndex()!=-1){   
         
         if(bill_nt_clr_combo.getSelectedItem().equals(lst5.get(0)))
      {
                bill_nt_clr_combo1.setVisible(true);
               lst6.add(0, lst5.get(1));
               //lst3.add(1, lst2.get(2));
            OrderbyModel5 = new ComboBoxValModel(lst6);
           bill_nt_clr_combo1.setModel(OrderbyModel5);
           bill_nt_clr_combo1.setSelectedIndex(0);
          // lst3.clear();

      }else if(bill_nt_clr_combo.getSelectedItem().equals(lst5.get(1))){
            bill_nt_clr_combo1.setVisible(true);
                lst6.add(0, lst5.get(0));
               //lst3.add(1, lst2.get(2));
              OrderbyModel5 = new ComboBoxValModel(lst6);
           bill_nt_clr_combo1.setModel(OrderbyModel5);
           bill_nt_clr_combo1.setSelectedIndex(0);
         }
      
    
      }
      
    }//GEN-LAST:event_bill_nt_clr_comboItemStateChanged



      public void grouplst(){



          try {

                      lst1 =  new StaticSentence(m_app.getSession(), "SELECT NAME FROM facility WHERE ACTIVE=TRUE ORDER BY NAME ",
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();

                    } catch (BasicException ex) {
                        Logger.getLogger(JPanelFacilityBilling.class.getName()).log(Level.SEVERE, null, ex);
                    }


          }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bill_during;
    private javax.swing.JComboBox bill_nt_clr_combo;
    private javax.swing.JComboBox bill_nt_clr_combo1;
    private javax.swing.JRadioButton bill_til_rdo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JLabel clear_during;
    private javax.swing.JRadioButton clear_til_rdo;
    private javax.swing.JLabel clear_till_dt;
    private javax.swing.JRadioButton clr_during_rdo;
    private javax.swing.JComboBox facility_combo;
    private javax.swing.JButton frm_btn;
    private javax.swing.JButton frm_btn1;
    private javax.swing.JTextField frm_dt;
    private javax.swing.JTextField frm_dt1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mon_btn;
    private javax.swing.JButton mon_btn1;
    private javax.swing.JRadioButton monthly;
    private javax.swing.JRadioButton monthly1;
    private javax.swing.JComboBox orderby1;
    private javax.swing.JComboBox orderby2;
    private javax.swing.JComboBox orderby3;
    private javax.swing.JRadioButton paid_radio;
    private javax.swing.JRadioButton period;
    private javax.swing.JRadioButton period1;
    private javax.swing.JLabel to;
    private javax.swing.JLabel to1;
    private javax.swing.JButton to_btn;
    private javax.swing.JButton to_btn1;
    private javax.swing.JTextField to_dt;
    private javax.swing.JTextField to_dt1;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
       return "Facility Billing and Payment Position";
    }

    @Override
    public void activate() throws BasicException {
         grouplst();

        lst1.add(0,"All");
        FacilityListModel  = new ComboBoxValModel(lst1);

           facility_combo.setModel(FacilityListModel);
           facility_combo.setSelectedIndex(-1);
          // clear_til_rdo.setSelected(true);
           bill_til_rdo.setSelected(true);
           paid_radio.setSelected(false);
         // bill_nt_clr_combo.setSelectedIndex(-1);
        
           OrderbyModel1  = new ComboBoxValModel(lst2);
           orderby1.setModel(OrderbyModel1);
           orderby1.setSelectedIndex(-1);
           orderby1.setVisible(false);
           
           OrderbyModel2 = new ComboBoxValModel(lst3);
           orderby2.setModel(OrderbyModel2);
           orderby2.setSelectedIndex(-1);
           orderby2.setVisible(false);
        
           OrderbyModel3 = new ComboBoxValModel(lst4);
           orderby3.setModel(OrderbyModel3);
           orderby3.setSelectedIndex(-1);
           orderby3.setVisible(false);
           
           OrderbyModel4 = new ComboBoxValModel(lst5);
           bill_nt_clr_combo.setModel(OrderbyModel4);
           bill_nt_clr_combo.setSelectedIndex(-1);
           bill_nt_clr_combo.setVisible(true);
           
           OrderbyModel5 = new ComboBoxValModel(lst6);
           bill_nt_clr_combo1.setModel(OrderbyModel5);
           bill_nt_clr_combo1.setSelectedIndex(-1);
           bill_nt_clr_combo1.setVisible(false);
     


        frm_dt.setText(null);
        frm_dt1.setText(null);
        to_dt.setText(null);
        to_dt1.setText(null);
        
        if(paid_radio.isSelected()==false){
            clear_till_dt.setVisible(false);
             clear_til_rdo.setVisible(false);
            clear_during.setVisible(false);
             clr_during_rdo.setVisible(false);
             monthly1.setVisible(false);
             period1.setVisible(false);
             frm_btn1.setVisible(false);
             to_btn1.setVisible(false);
             mon_btn1.setVisible(false);
             frm_dt1.setVisible(false);
             to_dt1.setVisible(false);
             jLabel11.setVisible(false);
             to1.setVisible(false);
             jLabel12.setVisible(false);
             jLabel10.setVisible(false);
             /*orderby1.setVisible(false);
             orderby1.setName(null);
             orderby2.setVisible(false);
             orderby3.setVisible(false);
             //orderby1.setSelectedIndex(-1);
             bill_nt_clr_combo.setVisible(true);
             bill_nt_clr_combo1.setVisible(false);
             bill_nt_clr_combo.setSelectedIndex(-1);*/
            
        }

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
        this.m_app=app;
    }

    @Override
    public Object getBean() {
           return this;
    }
}
