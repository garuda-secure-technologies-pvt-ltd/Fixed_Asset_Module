

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.pos.Booking.CheckInTableModel;
import com.openbravo.pos.Booking.GuestRoomBillModel;
import com.openbravo.pos.Booking.GuestRoomTableModel;
import com.openbravo.pos.Booking.hallTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;
import java.util.Map;
import com.openbravo.format.Formats;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.Booking.AdvanceRecvReportModel;
import com.openbravo.pos.Booking.DataSourceForAdvancePaymentReportRoom;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.reports.DataSourceForBotReports;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.JFrame;

public class BillandQtReports extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

   private AppView m_App;
    
   private List<BillandQtReportsTableModel.BOTDetails> BOT_Detials;
   private List<BillandQtReportsTableModel2.BillDetails> BIll_Detials;
   public BillandQtReportsTableModel BillandQtReports_Table_Model;
   public BillandQtReportsTableModel2 BillandQtReports_Table_Model2;
   DecimalFormat decimalFormat = new DecimalFormat("#0.00");
   
   private waitDialog w;
   public String BillOrderby;
   
    public BillandQtReports() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        jLabel2 = new javax.swing.JLabel();
        qt_nos_radio = new javax.swing.JRadioButton();
        date_radio = new javax.swing.JRadioButton();
        crby_radio = new javax.swing.JRadioButton();
        productName_radio = new javax.swing.JRadioButton();
        pendingQt_radio = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        month_billradio = new javax.swing.JRadioButton();
        period_radiobill = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        todate_textbill = new javax.swing.JTextField();
        fromdate_textbill = new javax.swing.JTextField();
        Month_calbill = new javax.swing.JButton();
        todate_calbill = new javax.swing.JButton();
        fromdate_calbill = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Bill_Execute = new javax.swing.JButton();
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
        jLabel4 = new javax.swing.JLabel();
        billamount_text = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        taxtotal_text = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        GrandTotal_text = new javax.swing.JTextField();
        billid_radio = new javax.swing.JRadioButton();
        createdDate_radio = new javax.swing.JRadioButton();
        warehouse_Radio = new javax.swing.JRadioButton();
        pendingBill_radio = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Miriam Fixed", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Bill / QT reports");

        month_radio.setText("Month ");
        month_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_radioItemStateChanged(evt);
            }
        });

        period_radio.setText("Period");

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
        jTable1.setRowHeight(21);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Execute");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Order by: ");

        qt_nos_radio.setText("QT Nos.");
        qt_nos_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                qt_nos_radioItemStateChanged(evt);
            }
        });

        date_radio.setText("Date");
        date_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                date_radioItemStateChanged(evt);
            }
        });

        crby_radio.setText("Created by");
        crby_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                crby_radioItemStateChanged(evt);
            }
        });

        productName_radio.setText("Product name");
        productName_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productName_radioItemStateChanged(evt);
            }
        });

        pendingQt_radio.setText("Pending QTs.");
        pendingQt_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pendingQt_radioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(month_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(period_radio))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(qt_nos_radio)
                                                .addGap(18, 18, 18)
                                                .addComponent(date_radio)
                                                .addGap(19, 19, 19)
                                                .addComponent(crby_radio)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(Month_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fromdate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(todate_cal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(productName_radio)
                                                .addGap(18, 18, 18)
                                                .addComponent(pendingQt_radio)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month_radio)
                    .addComponent(period_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_cal)
                    .addComponent(fromdate_cal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(todate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(todate_cal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jLabel2)
                            .addComponent(qt_nos_radio)
                            .addComponent(date_radio)
                            .addComponent(crby_radio)
                            .addComponent(productName_radio)
                            .addComponent(pendingQt_radio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        fromdate_text.setEditable(false);
        todate_text.setEditable(false);

        jTabbedPane1.addTab("QT Reports ", jPanel2);

        month_billradio.setText("Month ");
        month_billradio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_billradioItemStateChanged(evt);
            }
        });

        period_radiobill.setText("Period");
        period_radiobill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                period_radiobillActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel7.setText("From :");

        jLabel8.setFont(new java.awt.Font("Miriam Fixed", 1, 12)); // NOI18N
        jLabel8.setText("To:");

        fromdate_textbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_textbillActionPerformed(evt);
            }
        });

        Month_calbill.setText("Month");
        Month_calbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Month_calbillActionPerformed(evt);
            }
        });

        todate_calbill.setText("To Date");
        todate_calbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_calbillActionPerformed(evt);
            }
        });

        fromdate_calbill.setText("From Date");
        fromdate_calbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_calbillActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Miriam Fixed", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Bill Reports");

        jButton3.setText("Generate Report ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Bill_Execute.setText("Execute");
        Bill_Execute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bill_ExecuteActionPerformed(evt);
            }
        });

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jTable2.setRowHeight(21);
        jScrollPane2.setViewportView(jTable2);

        jLabel4.setText("Total  Amount : ");

        jLabel9.setText("Tax Total : ");

        jLabel10.setText("Grand Total :  ");

        billid_radio.setText("Bill Id");
        billid_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                billid_radioItemStateChanged(evt);
            }
        });

        createdDate_radio.setText("Created Date");
        createdDate_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                createdDate_radioItemStateChanged(evt);
            }
        });

        warehouse_Radio.setText("Warehouse");
        warehouse_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                warehouse_RadioItemStateChanged(evt);
            }
        });

        pendingBill_radio.setText("Show Pending Bills");

        jLabel11.setText("Order by :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(month_billradio, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(period_radiobill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(331, 331, 331))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(billid_radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(createdDate_radio)
                                        .addGap(34, 34, 34)
                                        .addComponent(warehouse_Radio)
                                        .addGap(31, 31, 31)
                                        .addComponent(pendingBill_radio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Bill_Execute))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(183, 183, 183)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(billamount_text, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(taxtotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3)))
                                .addGap(155, 155, 155))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fromdate_textbill, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(todate_textbill, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(Month_calbill, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fromdate_calbill, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(todate_calbill, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(614, Short.MAX_VALUE))))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(GrandTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(month_billradio)
                            .addComponent(period_radiobill))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fromdate_textbill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_calbill)
                    .addComponent(fromdate_calbill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(todate_textbill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todate_calbill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bill_Execute)
                    .addComponent(billid_radio)
                    .addComponent(createdDate_radio)
                    .addComponent(warehouse_Radio)
                    .addComponent(pendingBill_radio)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel4)
                    .addComponent(billamount_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(taxtotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(GrandTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        todate_textbill.setEditable(false);
        fromdate_textbill.setEditable(false);
        billamount_text.setEditable(false);
        taxtotal_text.setEditable(false);
        GrandTotal_text.setEditable(false);

        jTabbedPane1.addTab("Bill Reports", jPanel3);

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                
                
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                
                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();
                if(qt_nos_radio.isSelected()){
                
                    try{
                        BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTs(m_App, FrmDate, ToDate);
                        BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                    }
                    catch(BasicException ex){
                        ex.printStackTrace();
                            new MessageInf(ex).show(this);
                              Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                /////////////////////////////////////////////////////////////////////////////////////
                
                if(date_radio.isSelected()){
                    try{
                        BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyCRdate(m_App, FrmDate, ToDate);
                        BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                    }
                    catch(BasicException ex){
                        ex.printStackTrace();
                            new MessageInf(ex).show(this);
                              Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /////////////////////////////////////////////////////////////////////////////////////////
                if(crby_radio.isSelected()){
                    try{
                        BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyCrby(m_App, FrmDate, ToDate);
                        BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                    }
                    catch(BasicException ex){
                        ex.printStackTrace();
                            new MessageInf(ex).show(this);
                              Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //////////////////////////////////////////////////////////////////////////////////////////////
                if(productName_radio.isSelected()){
                    try{
                        BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyProduct(m_App, FrmDate, ToDate);
                        BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                    }
                    catch(BasicException ex){
                        ex.printStackTrace();
                            new MessageInf(ex).show(this);
                              Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //////////////////////////////////////////////////////////////////////////////////////////////
                if(pendingQt_radio.isSelected()){
                    try{
                        BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyPendingQts(m_App, FrmDate, ToDate);
                        BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                    }
                    catch(BasicException ex){
                        ex.printStackTrace();
                            new MessageInf(ex).show(this);
                              Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                ////////////////////////////////////////////////////////////////////////////////////////////
                
                //BOT_Detials = (BillandQtReportsTableModel.BOTDetails) BillandQtReports_Table_Model.getBOTList();
                
                DataSourceProvider data1 = new DataSourceProvider(BOT_Detials);
                DataSourceForBotReports dsfc = new DataSourceForBotReports(BOT_Detials);
                data1.setDataSource(dsfc);
                Map reportparams = new HashMap();
                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                String RPH =  "From  : "+fromdate_text.getText()+ "  To: "+todate_text.getText();
                reportparams.put("ReportHeader",RPH);
                reportparams.put("CreatedDate",Formats.TIMESTAMP.formatValue(CurrDate));
                reportparams.put("TITLE","QT Detailed Report");
                
                         
                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/BOTReport.jrxml", reportparams, false, data1, true, null); 
                         
                
       
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
       }
       else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
       }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
            if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){
                
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                
                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();
                
                try{
                    BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTs(m_App, FrmDate, ToDate);
                    BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                    jTable1.setModel(BillandQtReports_Table_Model.getTableModel());
                    jTable1.setVisible(true);
                }
                catch(BasicException ex){
                    ex.printStackTrace();
                        new MessageInf(ex).show(this);
                          Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void qt_nos_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_qt_nos_radioItemStateChanged
         if(qt_nos_radio.isSelected()){
             if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
                    if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                        Date CurrDate = new Date();

                        try {
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();
                        
                        try{
                            BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTs(m_App, FrmDate, ToDate);
                            BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                            jTable1.setModel(BillandQtReports_Table_Model.getTableModel());
                            jTable1.setVisible(true);
                        }
                        catch(BasicException ex){
                            ex.printStackTrace();
                                new MessageInf(ex).show(this);
                                  Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }





                    }
                    else{
                        
                    }
                }
                else{
                   
                }
             
         }
         else{
             
         }
    }//GEN-LAST:event_qt_nos_radioItemStateChanged

    private void date_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_date_radioItemStateChanged
       if(date_radio.isSelected()){
             if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
                    if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                        Date CurrDate = new Date();

                        try {
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();
                            
                        try{
                            BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyCRdate(m_App, FrmDate, ToDate);
                            BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                            jTable1.setModel(BillandQtReports_Table_Model.getTableModel());
                            jTable1.setVisible(true);
                        }
                        catch(BasicException ex){
                            ex.printStackTrace();
                                new MessageInf(ex).show(this);
                                  Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }





                    }
                    else{
                        
                    }
                }
                else{
                   
                }
                
         }
         else{
             
         }
    }//GEN-LAST:event_date_radioItemStateChanged

    private void crby_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_crby_radioItemStateChanged
         if(crby_radio.isSelected()){
             if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
                    if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                        Date CurrDate = new Date();

                        try {
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();

                        try{
                            BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyCrby(m_App, FrmDate, ToDate);
                            BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                            jTable1.setModel(BillandQtReports_Table_Model.getTableModel());
                            jTable1.setVisible(true);
                        }
                        catch(BasicException ex){
                            ex.printStackTrace();
                                new MessageInf(ex).show(this);
                                  Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }





                    }
                    else{
                        
                    }
                }
                else{
                   
                }
             
         }
         else{
             
         }
    }//GEN-LAST:event_crby_radioItemStateChanged

    private void productName_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productName_radioItemStateChanged
         if(productName_radio.isSelected()){
             if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
                    if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                        Date CurrDate = new Date();

                        try {
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();

                        try{
                            BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyProduct(m_App, FrmDate, ToDate);
                            BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                            jTable1.setModel(BillandQtReports_Table_Model.getTableModel());
                            jTable1.setVisible(true);
                        }
                        catch(BasicException ex){
                            ex.printStackTrace();
                                new MessageInf(ex).show(this);
                                  Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }





                    }
                    else{
                        
                    }
                }
                else{
                   
                }
             
         }
         else{
             
         }
    }//GEN-LAST:event_productName_radioItemStateChanged

    private void pendingQt_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pendingQt_radioItemStateChanged
        if(pendingQt_radio.isSelected()){
             if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
                    if(todate_text.getText()!=null && todate_text.getText().trim().length()>0){

                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                        Date CurrDate = new Date();

                        try {
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_text.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();

                        try{
                            BillandQtReports_Table_Model = BillandQtReportsTableModel.LoadBillQTsOrderbyPendingQts(m_App, FrmDate, ToDate);
                            BOT_Detials  =  (List<BillandQtReportsTableModel.BOTDetails>) BillandQtReports_Table_Model.getBOTList();
                            jTable1.setModel(BillandQtReports_Table_Model.getTableModel());
                            jTable1.setVisible(true);
                        }
                        catch(BasicException ex){
                            ex.printStackTrace();
                                new MessageInf(ex).show(this);
                                  Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                        }





                    }
                    else{
                        
                    }
                }
                else{
                   
                }
             
         }
         else{
             
         }
    }//GEN-LAST:event_pendingQt_radioItemStateChanged

    private void month_billradioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_billradioItemStateChanged
        if(month_billradio.isSelected()){
            todate_calbill.setVisible(false);
            fromdate_calbill.setVisible(false);
            Month_calbill.setVisible(true);
            fromdate_textbill.setText(null);
            todate_textbill.setText(null);
           
        }
        else{
            todate_calbill.setVisible(true);
            fromdate_calbill.setVisible(true);
            Month_calbill.setVisible(false);
            fromdate_textbill.setText(null);
            todate_textbill.setText(null);
        }
    }//GEN-LAST:event_month_billradioItemStateChanged

    private void Month_calbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Month_calbillActionPerformed
       Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_textbill.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            fromdate_textbill.setText(Formats.TIMESTAMP.formatValue(date));
           
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(date.getTime());
            cal1.add(Calendar.MONTH, 1);

            cal1.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal1.getTimeInMillis());
            todate_textbill.setText(Formats.TIMESTAMP.formatValue(date));
           
           

        }
    }//GEN-LAST:event_Month_calbillActionPerformed

    private void todate_calbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_calbillActionPerformed
       Date date;
        if(fromdate_textbill.getText()!=null && fromdate_textbill.getText().trim().length()>0){

            try {
                date = (Date) Formats.TIMESTAMP.parseValue(fromdate_textbill.getText());
            } catch (BasicException ex) {
                date = null;
            }
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {

                todate_textbill.setText(Formats.TIMESTAMP.formatValue(date));
               
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_todate_calbillActionPerformed

    private void fromdate_calbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_calbillActionPerformed
      Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_textbill.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            fromdate_textbill.setText(Formats.TIMESTAMP.formatValue(date));
            todate_textbill.setText(null);
        }
    }//GEN-LAST:event_fromdate_calbillActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void period_radiobillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_period_radiobillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_period_radiobillActionPerformed

    private void fromdate_textbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_textbillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromdate_textbillActionPerformed

    private void Bill_ExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bill_ExecuteActionPerformed
     w=new waitDialog(new JFrame(), true);
         int h=w.getSize().height;
         int w1=w.getSize().width;
         Toolkit toolkit = Toolkit.getDefaultToolkit();
	 Dimension scrnsize = toolkit.getScreenSize();
         w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
            
          if(BillOrderby==null || BillOrderby.length()==0){
              BillOrderby="billid";
          } 
         
         
          Thread t=new Thread(
				new Runnable()
				{
					public void run()
					{
                                           ExecuteDetailsForBill(BillOrderby);
					}
				}
			);
            t.start();
            w.showDialog("Please wait, getting details.");
         
    }//GEN-LAST:event_Bill_ExecuteActionPerformed

    private void billid_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_billid_radioItemStateChanged
         if(billid_radio.isSelected()){
           BillOrderby = "billid";
           if(fromdate_textbill.getText()!=null && fromdate_textbill.getText().trim().length()>0){
            if(todate_textbill.getText()!=null && todate_textbill.getText().trim().length()>0){
                    w=new waitDialog(new JFrame(), true);
                    int h=w.getSize().height;
                    int w1=w.getSize().width;
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Dimension scrnsize = toolkit.getScreenSize();
                    w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
                        Thread t=new Thread(
                                           new Runnable()
                                           {
                                                   public void run()
                                                   {
                                                      ExecuteDetailsForBill(BillOrderby);
                                                   }
                                           }
                                   );
                       t.start();
                       w.showDialog("Please wait, getting details.");
                
                
                
                
             
            }
           }
             
         }
         
    }//GEN-LAST:event_billid_radioItemStateChanged

    private void createdDate_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_createdDate_radioItemStateChanged
         if(createdDate_radio.isSelected()){
           BillOrderby = "createddate";
           if(fromdate_textbill.getText()!=null && fromdate_textbill.getText().trim().length()>0){
            if(todate_textbill.getText()!=null && todate_textbill.getText().trim().length()>0){
                    w=new waitDialog(new JFrame(), true);
                    int h=w.getSize().height;
                    int w1=w.getSize().width;
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Dimension scrnsize = toolkit.getScreenSize();
                    w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
                        Thread t=new Thread(
                                           new Runnable()
                                           {
                                                   public void run()
                                                   {
                                                      ExecuteDetailsForBill(BillOrderby);
                                                   }
                                           }
                                   );
                       t.start();
                       w.showDialog("Please wait, getting details.");
                
                
                
                
             
            }
           }
             
         }
    }//GEN-LAST:event_createdDate_radioItemStateChanged

    private void warehouse_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_warehouse_RadioItemStateChanged
       if(warehouse_Radio.isSelected()){
           BillOrderby = "warehousename";
           if(fromdate_textbill.getText()!=null && fromdate_textbill.getText().trim().length()>0){
            if(todate_textbill.getText()!=null && todate_textbill.getText().trim().length()>0){
                    w=new waitDialog(new JFrame(), true);
                    int h=w.getSize().height;
                    int w1=w.getSize().width;
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Dimension scrnsize = toolkit.getScreenSize();
                    w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
                        Thread t=new Thread(
                                           new Runnable()
                                           {
                                                   public void run()
                                                   {
                                                      ExecuteDetailsForBill(BillOrderby);
                                                   }
                                           }
                                   );
                       t.start();
                       w.showDialog("Please wait, getting details.");
                
                
                
                
             
            }
           }
             
         }
    }//GEN-LAST:event_warehouse_RadioItemStateChanged

    
    
    
    
    
    
    
    
    public void ExecuteDetailsForBill(String Orderby ){
        if(fromdate_textbill.getText()!=null && fromdate_textbill.getText().trim().length()>0){
            if(todate_textbill.getText()!=null && todate_textbill.getText().trim().length()>0){
             
                
                Date FrmDate = new Date() ;
                Date ToDate = new Date();
                Date CurrDate = new Date();
                
                try {
                    FrmDate =  (Date) Formats.TIMESTAMP.parseValue(fromdate_textbill.getText());
                    ToDate =  (Date) Formats.TIMESTAMP.parseValue(todate_textbill.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTable2.setVisible(true);
                
                BOT_Detials =  new ArrayList<BillandQtReportsTableModel.BOTDetails>();
                
                try{
                    BillandQtReports_Table_Model2 = BillandQtReportsTableModel2.LoadBillQTs(m_App, FrmDate, ToDate , Orderby);
                    BIll_Detials  =  (List<BillandQtReportsTableModel2.BillDetails>) BillandQtReports_Table_Model2.getBOTList();
                    jTable2.setModel(BillandQtReports_Table_Model2.getTableModel());
                    
                    
                    
                    
                    Double TotalAmt = 0.00;
                    Double TotalTax = 0.00;
                    
                    int  Row_Count1 = BillandQtReports_Table_Model2.getTableModel().getRowCount();    
                        for(int i=0;i<Row_Count1;i++){
                          int row = i;
                          Double Amount =  Double.valueOf(BillandQtReports_Table_Model2.getTableModel().getValueAt(row, 2).toString());
                          Double TaxAmt =  Double.valueOf(BillandQtReports_Table_Model2.getTableModel().getValueAt(row, 3).toString());
                          TotalAmt =TotalAmt+Amount;  
                          TotalTax = TotalTax+TaxAmt;
                          
                        }
                        
                     billamount_text.setText(decimalFormat.format(TotalAmt));
                     taxtotal_text.setText(decimalFormat.format(TotalTax));
                     GrandTotal_text.setText(decimalFormat.format(TotalAmt+TotalTax));
                    w.hideDialog();
                    jTable2.setVisible(true);
                    
                }
                catch(BasicException ex){
                    ex.printStackTrace();
                        new MessageInf(ex).show(this);
                        w.hideDialog(); 
                          Logger.getLogger(BillandQtReports.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
            else{
                w.hideDialog(); 
                JOptionPane.showMessageDialog(this, "Please enter from date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
           }
     }
     else{
         w.hideDialog(); 
         JOptionPane.showMessageDialog(this, "Please enter To date first..!!", "incomplete Form", JOptionPane.WARNING_MESSAGE);
     }
    }
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bill_Execute;
    private javax.swing.JTextField GrandTotal_text;
    private javax.swing.JButton Month_cal;
    private javax.swing.JButton Month_calbill;
    private javax.swing.JTextField billamount_text;
    private javax.swing.JRadioButton billid_radio;
    private javax.swing.JRadioButton crby_radio;
    private javax.swing.JRadioButton createdDate_radio;
    private javax.swing.JRadioButton date_radio;
    private javax.swing.JButton fromdate_cal;
    private javax.swing.JButton fromdate_calbill;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JTextField fromdate_textbill;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton month_billradio;
    private javax.swing.JRadioButton month_radio;
    private javax.swing.JRadioButton pendingBill_radio;
    private javax.swing.JRadioButton pendingQt_radio;
    private javax.swing.JRadioButton period_radio;
    private javax.swing.JRadioButton period_radiobill;
    private javax.swing.JRadioButton productName_radio;
    private javax.swing.JRadioButton qt_nos_radio;
    private javax.swing.JTextField taxtotal_text;
    private javax.swing.JButton todate_cal;
    private javax.swing.JButton todate_calbill;
    private javax.swing.JTextField todate_text;
    private javax.swing.JTextField todate_textbill;
    private javax.swing.JRadioButton warehouse_Radio;
    // End of variables declaration//GEN-END:variables

 public void reset(){
       
     }

    public String getTitle() {
         return "";
    }

    public void activate() throws BasicException {
        reset();
        loaddata();
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
        
        groupButton();
        jTable1.setVisible(false);
        jTable2.setVisible(false);
        month_radio.setSelected(true);
        qt_nos_radio.setSelected(true);
        month_billradio.setSelected(true);
        billid_radio.setSelected(true);
        
    }
    
    
    
     private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(month_radio);
        bg1.add(period_radio);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(qt_nos_radio);
        bg2.add(date_radio);
        bg2.add(crby_radio);
        bg2.add(productName_radio);
        bg2.add(pendingQt_radio);
        
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(month_billradio);
        bg3.add(period_radiobill);
        
        
        ButtonGroup bg4 = new ButtonGroup();
        bg4.add(billid_radio);
        bg4.add(createdDate_radio);
        bg4.add(warehouse_Radio);
        bg4.add(pendingBill_radio);
         
        
        
    }
     


}
