
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;


public class QTDetails extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private List<QTDetailsReportTableModel.QTInfo> QtInfo_list;
    private QTDetailsReportTableModel QTDetailsReport_Table_Model;
    List<String> WarehouseList = new ArrayList<String>();
    private ComboBoxValModel WarehouseListModel;
    
    public QTDetails() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        bill_panel = new javax.swing.JPanel();
        billno_radio = new javax.swing.JRadioButton();
        billSeries_radio = new javax.swing.JRadioButton();
        billDate_radio = new javax.swing.JRadioButton();
        billno_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Billno_text = new javax.swing.JTextField();
        billSeries_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        BillNoFrom_Text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BillNoUpto_Text = new javax.swing.JTextField();
        billDate_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        BillDateFrom_text = new javax.swing.JTextField();
        billFromDate_button = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        BillDateUpto_text = new javax.swing.JTextField();
        BillToDate_Button = new javax.swing.JButton();
        warehouse_Combo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        Bill_generateReport_Button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        QTdate_radio = new javax.swing.JRadioButton();
        QtSeries_radio = new javax.swing.JRadioButton();
        qtdate_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        qtFromdate_text = new javax.swing.JTextField();
        QtFromDate_button = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        QtToDate_Text = new javax.swing.JTextField();
        QtToDate_Button = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Qtwarehouse_combo = new javax.swing.JComboBox();
        Qt_GenerateReport_button = new javax.swing.JButton();
        QtSeries_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        qtNoFrom_text = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        QtNoUpto_text = new javax.swing.JTextField();

        jPanel1.setForeground(new java.awt.Color(204, 0, 0));

        bill_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Against Bill", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("AngsanaUPC", 1, 36), new java.awt.Color(0, 0, 204))); // NOI18N
        bill_panel.setForeground(new java.awt.Color(204, 0, 0));

        billno_radio.setText("Bill No. wise");
        billno_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                billno_radioItemStateChanged(evt);
            }
        });

        billSeries_radio.setText("Bill Series wise");
        billSeries_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                billSeries_radioItemStateChanged(evt);
            }
        });

        billDate_radio.setText("Billed Date Wise");
        billDate_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                billDate_radioItemStateChanged(evt);
            }
        });

        jLabel2.setText("Bill No : ");

        javax.swing.GroupLayout billno_panelLayout = new javax.swing.GroupLayout(billno_panel);
        billno_panel.setLayout(billno_panelLayout);
        billno_panelLayout.setHorizontalGroup(
            billno_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billno_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Billno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        billno_panelLayout.setVerticalGroup(
            billno_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, billno_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(billno_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Billno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setText("Bill Series From  :  ");

        jLabel4.setText("To : ");

        javax.swing.GroupLayout billSeries_panelLayout = new javax.swing.GroupLayout(billSeries_panel);
        billSeries_panel.setLayout(billSeries_panelLayout);
        billSeries_panelLayout.setHorizontalGroup(
            billSeries_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billSeries_panelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BillNoFrom_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(BillNoUpto_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );
        billSeries_panelLayout.setVerticalGroup(
            billSeries_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billSeries_panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(billSeries_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(BillNoFrom_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(BillNoUpto_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel5.setText("Bill Created From : ");

        billFromDate_button.setText("From Date");
        billFromDate_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billFromDate_buttonActionPerformed(evt);
            }
        });

        jLabel6.setText("To :");

        BillToDate_Button.setText("To Date");
        BillToDate_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillToDate_ButtonActionPerformed(evt);
            }
        });

        warehouse_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel10.setText("Warehouse : ");

        javax.swing.GroupLayout billDate_panelLayout = new javax.swing.GroupLayout(billDate_panel);
        billDate_panel.setLayout(billDate_panelLayout);
        billDate_panelLayout.setHorizontalGroup(
            billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDate_panelLayout.createSequentialGroup()
                .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(billDate_panelLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel6))
                    .addGroup(billDate_panelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(billDate_panelLayout.createSequentialGroup()
                        .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BillDateFrom_text, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(BillDateUpto_text))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(billFromDate_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BillToDate_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(warehouse_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        billDate_panelLayout.setVerticalGroup(
            billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDate_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warehouse_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(BillDateFrom_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billFromDate_button))
                .addGap(20, 20, 20)
                .addGroup(billDate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BillDateUpto_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(BillToDate_Button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BillDateFrom_text.setEditable(false);
        BillDateUpto_text.setEditable(false);

        Bill_generateReport_Button.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Bill_generateReport_Button.setForeground(new java.awt.Color(153, 0, 0));
        Bill_generateReport_Button.setText("Generate Report ");
        Bill_generateReport_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bill_generateReport_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bill_panelLayout = new javax.swing.GroupLayout(bill_panel);
        bill_panel.setLayout(bill_panelLayout);
        bill_panelLayout.setHorizontalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Bill_generateReport_Button)
                    .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(bill_panelLayout.createSequentialGroup()
                            .addComponent(billno_radio)
                            .addGap(83, 83, 83)
                            .addComponent(billSeries_radio)
                            .addGap(73, 73, 73)
                            .addComponent(billDate_radio))
                        .addComponent(billSeries_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(billDate_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(billno_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        bill_panelLayout.setVerticalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(billno_radio)
                    .addComponent(billSeries_radio)
                    .addComponent(billDate_radio))
                .addGap(31, 31, 31)
                .addComponent(billno_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(billSeries_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(billDate_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bill_generateReport_Button)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bill_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bill_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Against Bill ", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Against  QT", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Aharoni", 1, 24), new java.awt.Color(0, 0, 255))); // NOI18N

        QTdate_radio.setText("QT Date Range wise");
        QTdate_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                QTdate_radioItemStateChanged(evt);
            }
        });

        QtSeries_radio.setText("Qt No. Series Wise");
        QtSeries_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                QtSeries_radioItemStateChanged(evt);
            }
        });

        jLabel1.setText("Qt Created From :  ");

        QtFromDate_button.setText("From Date");
        QtFromDate_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QtFromDate_buttonActionPerformed(evt);
            }
        });

        jLabel7.setText("To : ");

        QtToDate_Button.setText("To Date");
        QtToDate_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QtToDate_ButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("Warehouse : ");

        Qtwarehouse_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout qtdate_panelLayout = new javax.swing.GroupLayout(qtdate_panel);
        qtdate_panel.setLayout(qtdate_panelLayout);
        qtdate_panelLayout.setHorizontalGroup(
            qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qtdate_panelLayout.createSequentialGroup()
                .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qtdate_panelLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel7))
                    .addGroup(qtdate_panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qtdate_panelLayout.createSequentialGroup()
                        .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(qtFromdate_text)
                            .addComponent(QtToDate_Text, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(QtFromDate_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(QtToDate_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Qtwarehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        qtdate_panelLayout.setVerticalGroup(
            qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qtdate_panelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Qtwarehouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(qtFromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QtFromDate_button))
                .addGap(18, 18, 18)
                .addGroup(qtdate_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(QtToDate_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QtToDate_Button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        qtFromdate_text.setEditable(false);
        QtToDate_Text.setEditable(false);

        Qt_GenerateReport_button.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Qt_GenerateReport_button.setForeground(new java.awt.Color(153, 0, 0));
        Qt_GenerateReport_button.setText("Generate Report ");
        Qt_GenerateReport_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Qt_GenerateReport_buttonActionPerformed(evt);
            }
        });

        jLabel8.setText("QT No. From :  ");

        jLabel9.setText("To : ");

        javax.swing.GroupLayout QtSeries_panelLayout = new javax.swing.GroupLayout(QtSeries_panel);
        QtSeries_panel.setLayout(QtSeries_panelLayout);
        QtSeries_panelLayout.setHorizontalGroup(
            QtSeries_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QtSeries_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(qtNoFrom_text, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QtNoUpto_text, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        QtSeries_panelLayout.setVerticalGroup(
            QtSeries_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QtSeries_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QtSeries_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(qtNoFrom_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(QtNoUpto_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(qtdate_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(QTdate_radio)
                        .addGap(87, 87, 87)
                        .addComponent(QtSeries_radio))
                    .addComponent(QtSeries_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Qt_GenerateReport_button)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QTdate_radio)
                    .addComponent(QtSeries_radio))
                .addGap(18, 18, 18)
                .addComponent(qtdate_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(QtSeries_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(Qt_GenerateReport_button)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Against QT ", jPanel3);

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
                .addGap(22, 22, 22)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void billno_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_billno_radioItemStateChanged
        if(billno_radio.isSelected()){
            billSeries_panel.setVisible(false);
            billno_panel.setVisible(true);
            billDate_panel.setVisible(false);
        }
       
    }//GEN-LAST:event_billno_radioItemStateChanged

    private void billSeries_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_billSeries_radioItemStateChanged
       if(billSeries_radio.isSelected()){
            billSeries_panel.setVisible(true);
            billno_panel.setVisible(false);
            billDate_panel.setVisible(false);
        }
       
    }//GEN-LAST:event_billSeries_radioItemStateChanged

    private void billDate_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_billDate_radioItemStateChanged
        if(billDate_radio.isSelected()){
            billSeries_panel.setVisible(false);
            billno_panel.setVisible(false);
            billDate_panel.setVisible(true);
        }
    }//GEN-LAST:event_billDate_radioItemStateChanged

    private void QTdate_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_QTdate_radioItemStateChanged
       if(QTdate_radio.isSelected()){
           qtdate_panel.setVisible(true);
           QtSeries_panel.setVisible(false);
       }
    }//GEN-LAST:event_QTdate_radioItemStateChanged

    private void QtSeries_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_QtSeries_radioItemStateChanged
       if(QtSeries_radio.isSelected()){
           qtdate_panel.setVisible(false);
           QtSeries_panel.setVisible(true);
       }
    }//GEN-LAST:event_QtSeries_radioItemStateChanged

    private void billFromDate_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billFromDate_buttonActionPerformed
       Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(BillDateFrom_text.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                BillDateFrom_text.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_billFromDate_buttonActionPerformed

    private void BillToDate_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillToDate_ButtonActionPerformed
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(BillDateUpto_text.getText());
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
                BillDateUpto_text.setText(Formats.TIMESTAMP.formatValue(cal.getTime()));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_BillToDate_ButtonActionPerformed

    private void QtToDate_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QtToDate_ButtonActionPerformed
       Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(QtToDate_Text.getText());
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
                QtToDate_Text.setText(Formats.TIMESTAMP.formatValue(cal.getTime()));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_QtToDate_ButtonActionPerformed

    private void QtFromDate_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QtFromDate_buttonActionPerformed
         Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(qtFromdate_text.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                qtFromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_QtFromDate_buttonActionPerformed

    private void Bill_generateReport_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bill_generateReport_ButtonActionPerformed
        if(billno_radio.isSelected()){
            if(Billno_text.getText()!=null && Billno_text.getText().trim().length()>0){
                String BillNo=Billno_text.getText();
                int BillFlag=0;
                try {
                     BillFlag=CheckForBillNo(BillNo);
                } catch (BasicException ex) {
                    Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String Warehouse=null;
                if(BillFlag==1){
                    try {
                        Warehouse=GetWarehouseFromBillNo(BillNo);
                    } catch (BasicException ex) {
                        Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    QtInfo_list=new ArrayList<QTDetailsReportTableModel.QTInfo>();
                    try {
                               QTDetailsReport_Table_Model  = QTDetailsReportTableModel.LoadQTInfo(m_App, BillNo);
                     } 

                   catch (BasicException ex) {
                       Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                   }
                         
                    QtInfo_list  =  (List<QTDetailsReportTableModel.QTInfo>) QTDetailsReport_Table_Model.getQtList();
                         
                    DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                    DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                    data1.setDataSource(dsfc);
                    Map reportparams = new HashMap();
                    reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                    reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                    String h = "( QT details for Bill no : "+BillNo+" )";
                    reportparams.put("HEADING",h);
                    reportparams.put("WAREHOUSE",Warehouse);
                    reportparams.put("date",new Date());
                    reportparams.put("TITLE"," Rooms Billed Details");
                        
                    
                    JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/QtDetailReport.jrxml", reportparams, false, data1, true, null); 
                         
                    
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, "Please enter valid bill no. " ,"Error", JOptionPane.ERROR_MESSAGE);
                    Billno_text.setText(null);
                }
                 
            }
            else{
                 JOptionPane.showMessageDialog(this, "Enter Bill No. " ,"Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
        
        
        if(billSeries_radio.isSelected()){
            if(BillNoFrom_Text.getText()!=null && BillNoFrom_Text.getText().trim().length()>0){
               if(BillNoUpto_Text.getText()!=null && BillNoUpto_Text.getText().trim().length()>0){ 
                   
                   String BillNoFrom = BillNoFrom_Text.getText().trim();
                   String BillNoUpto = BillNoUpto_Text.getText().trim();
                   
                   int billFlagFrom=0;
                   int BillFlagUpto=0;
                   
                   try {
                       billFlagFrom=CheckForBillNo(BillNoFrom);
                       BillFlagUpto=CheckForBillNo(BillNoUpto);
                    } catch (BasicException ex) {
                      Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   if(billFlagFrom==1 && BillFlagUpto==1){
                      int WarehouseFlag=0;
                       try {
                           WarehouseFlag=CheckForWarehouse(BillNoFrom, BillNoUpto);
                       } catch (BasicException ex) {
                           Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       if(WarehouseFlag==1){
                           String Warehouse=null;
                           String WarehouseId=null;
                           Date FirstBillDate = null;
                           Date SecondBillDate=null;
                          try {
                              Warehouse=GetWarehouseFromBillNo(BillNoFrom);
                              FirstBillDate=getBillDateByBillNo(BillNoFrom);
                              SecondBillDate=getBillDateByBillNo(BillNoUpto);
                              WarehouseId=getWarehouseIdFromBillno(BillNoUpto);
                              
                          } catch (BasicException ex) {
                              Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                          }
                           
                           
                       
                                QtInfo_list=new ArrayList<QTDetailsReportTableModel.QTInfo>();
                                try {
                                           QTDetailsReport_Table_Model  = QTDetailsReportTableModel.LoadQTInfoForBillSeries(m_App, FirstBillDate,SecondBillDate , WarehouseId);
                                 } 

                                catch (BasicException ex) {
                                   Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         
                                QtInfo_list  =  (List<QTDetailsReportTableModel.QTInfo>) QTDetailsReport_Table_Model.getQtList();
                         
                                DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                                DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                                data1.setDataSource(dsfc);
                                Map reportparams = new HashMap();
                                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                String h = "( QT details for Bill No From : "+BillNoFrom+" to  : "+ BillNoUpto+ "  )";
                                reportparams.put("HEADING",h);
                                reportparams.put("WAREHOUSE",Warehouse);
                                reportparams.put("date",new Date());
                                reportparams.put("TITLE"," Rooms Billed Details");

                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/QtDetailReport.jrxml", reportparams, false, data1, true, null); 
                           
                           
                           
                           
                           
                           
                           
                       
                       
                       }
                       else{
                           JOptionPane.showMessageDialog(this, " Entered bills are not from same warehouse.  \n  Please enter bill within same warehouse. " ,"Error", JOptionPane.ERROR_MESSAGE);
                       }
                   
                   
                   }
                   else{
                        JOptionPane.showMessageDialog(this, "Not valid bill nos. Please enter correct bill No. " ,"Error", JOptionPane.ERROR_MESSAGE);
                   }
                 
               }
               else{
                    JOptionPane.showMessageDialog(this, "Enter Bill No.  " ,"Error", JOptionPane.ERROR_MESSAGE);
               }
            }
            else{
                 JOptionPane.showMessageDialog(this, "Enter Bill No. " ,"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
        
        
        if(billDate_radio.isSelected()){
          if(BillDateFrom_text.getText()!=null && BillDateFrom_text.getText().trim().length()>0){
               if(BillDateUpto_text.getText()!=null && BillDateUpto_text.getText().trim().length()>0){ 
                  if(warehouse_Combo.getSelectedIndex()!=-1){
                      
                      String WarehouseName = warehouse_Combo.getSelectedItem().toString();
                      String WarehouseId=null;
                
                        Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                       

                        try {
                            WarehouseId=getWarehouseIdFromName(WarehouseName);
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(BillDateFrom_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(BillDateUpto_text.getText());
                        } catch (BasicException ex) {
                        }
                   
                   
                                QtInfo_list=new ArrayList<QTDetailsReportTableModel.QTInfo>();
                                try {
                                           QTDetailsReport_Table_Model  = QTDetailsReportTableModel.LoadQTInfoForBillSeries(m_App, FrmDate,ToDate , WarehouseId);
                                 } 

                                catch (BasicException ex) {
                                   Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         
                                QtInfo_list  =  (List<QTDetailsReportTableModel.QTInfo>) QTDetailsReport_Table_Model.getQtList();
                         
                                DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                                DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                                data1.setDataSource(dsfc);
                                Map reportparams = new HashMap();
                                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                String h = "( QT details for Bills  From : "+BillDateFrom_text.getText()+" to  : "+ BillDateUpto_text.getText()+ "  )";
                                reportparams.put("HEADING",h);
                                reportparams.put("WAREHOUSE",WarehouseName);
                                reportparams.put("date",new Date());
                                reportparams.put("TITLE"," Rooms Billed Details");

                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/QtDetailReport.jrxml", reportparams, false, data1, true, null); 
                           
                   
                   
                   
                   
                  
                  
                  }
                  
                  else{
                   JOptionPane.showMessageDialog(this, "Select Warehouse first..! " ,"Error", JOptionPane.ERROR_MESSAGE);
               }
               }
               else{
                    JOptionPane.showMessageDialog(this, "Enter To Date ..  " ,"Error", JOptionPane.ERROR_MESSAGE);
               }
            }
            else{
                 JOptionPane.showMessageDialog(this, "Enter From Date. " ,"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Bill_generateReport_ButtonActionPerformed

    private void Qt_GenerateReport_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Qt_GenerateReport_buttonActionPerformed
        if(QTdate_radio.isSelected()){
            if(qtFromdate_text.getText()!=null && qtFromdate_text.getText().trim().length()>0){
               if(QtToDate_Text.getText()!=null && QtToDate_Text.getText().trim().length()>0){ 
                 if(Qtwarehouse_combo.getSelectedIndex()!=-1){
                   
                     String WarehouseName=Qtwarehouse_combo.getSelectedItem().toString();
                     String WarehouseId= null;
                     
                     Date FrmDate = new Date() ;
                        Date ToDate = new Date();
                       

                        try {
                            WarehouseId=getWarehouseIdFromName(WarehouseName);
                            FrmDate =  (Date) Formats.TIMESTAMP.parseValue(qtFromdate_text.getText());
                            ToDate =  (Date) Formats.TIMESTAMP.parseValue(QtToDate_Text.getText());
                        } catch (BasicException ex) {
                        }
                
                
                         QtInfo_list=new ArrayList<QTDetailsReportTableModel.QTInfo>();
                                try {
                                           QTDetailsReport_Table_Model  = QTDetailsReportTableModel.LoadQTInfoForBillSeries(m_App, FrmDate,ToDate , WarehouseId);
                                 } 

                                catch (BasicException ex) {
                                   Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         
                                QtInfo_list  =  (List<QTDetailsReportTableModel.QTInfo>) QTDetailsReport_Table_Model.getQtList();
                         
                                DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                                DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                                data1.setDataSource(dsfc);
                                Map reportparams = new HashMap();
                                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                String h = "( QT details for QTs  From : "+qtFromdate_text.getText()+" to  : "+ QtToDate_Text.getText()+ "  )";
                                reportparams.put("HEADING",h);
                                reportparams.put("WAREHOUSE",WarehouseName);
                                reportparams.put("date",new Date());
                                

                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/QtDetailReport.jrxml", reportparams, false, data1, true, null); 
                           
                   
                    
                   
                 }
                 else{
                     JOptionPane.showMessageDialog(this, "Select Warehouse first. " ,"Error", JOptionPane.ERROR_MESSAGE);
                 }
               }
               else{
                    JOptionPane.showMessageDialog(this, "Enter To Date ..  " ,"Error", JOptionPane.ERROR_MESSAGE);
               }
            }
            else{
                 JOptionPane.showMessageDialog(this, "Enter From Date. " ,"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
        if(QtSeries_radio.isSelected()){
           if(qtNoFrom_text.getText()!=null && qtNoFrom_text.getText().trim().length()>0){
               if(QtNoUpto_text.getText()!=null && QtNoUpto_text.getText().trim().length()>0){ 
                
                    String QtNoFrom =   qtNoFrom_text.getText();
                    String QtNoUpto = QtNoUpto_text.getText();
                    int QtNoFromFlag=0;
                    int QtNoUptoFlag=0;
                    int QtWarehouseFlag=0;
                   try {
                       QtNoFromFlag=CheckForQTNo(QtNoFrom);
                       QtNoUptoFlag=CheckForQTNo(QtNoUpto);
                       QtWarehouseFlag=CheckForQTWarehouse(QtNoFrom, QtNoUpto);
                   } catch (BasicException ex) {
                       Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   Date FromQtDate=null;
                   Date UptoQtDate=null;
                   String WarehouseID=null;
                   String WarehouseName=null;
                   
                   
                   if(QtNoFromFlag==1 && QtNoUptoFlag==1){
                
                       if(QtWarehouseFlag==1){
                           try {
                               FromQtDate=getQtDateByQtno(QtNoFrom);
                               UptoQtDate=getQtDateByQtno(QtNoUpto);
                               WarehouseID=getWarehouseIdFromQTNo(QtNoUpto);
                               WarehouseName=getWarehouseNameFromID(WarehouseID);
                               
                           } catch (BasicException ex) {
                               Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                           }
                          
                           
                           QtInfo_list=new ArrayList<QTDetailsReportTableModel.QTInfo>();
                                try {
                                           QTDetailsReport_Table_Model  = QTDetailsReportTableModel.LoadQTInfoForQtRange(m_App, FromQtDate,UptoQtDate , WarehouseID);
                                 } 

                                catch (BasicException ex) {
                                   Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         
                                QtInfo_list  =  (List<QTDetailsReportTableModel.QTInfo>) QTDetailsReport_Table_Model.getQtList();
                         
                                DataSourceProvider data1 = new DataSourceProvider(QtInfo_list);
                                DataSourceForQtDetailReport dsfc = new DataSourceForQtDetailReport(QtInfo_list);
                                data1.setDataSource(dsfc);
                                Map reportparams = new HashMap();
                                reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
                                reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
                                String h = "( QT details for QT No From : "+QtNoFrom+" to  : "+ UptoQtDate+ "  )";
                                reportparams.put("HEADING",h);
                                reportparams.put("WAREHOUSE",WarehouseName);
                                reportparams.put("date",new Date());
                                reportparams.put("TITLE"," Rooms Billed Details");

                                JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/QtDetailReport.jrxml", reportparams, false, data1, true, null); 
                           
                           
                           
                           
                       
                       
                       
                       
                    
                       }
                       else{
                           JOptionPane.showMessageDialog(this, " Select QTs from same warehouse. \n Selected Qts are from different warehouse. " ,"Error", JOptionPane.ERROR_MESSAGE);
                       }
                   }
                   else{
                        JOptionPane.showMessageDialog(this, " Enter Correct QT no. " ,"Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
               else{
                    JOptionPane.showMessageDialog(this, "Enter Qt No.  " ,"Error", JOptionPane.ERROR_MESSAGE);
               }
            }
            else{
                 JOptionPane.showMessageDialog(this, "Enter  Qt No.  " ,"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Qt_GenerateReport_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BillDateFrom_text;
    private javax.swing.JTextField BillDateUpto_text;
    private javax.swing.JTextField BillNoFrom_Text;
    private javax.swing.JTextField BillNoUpto_Text;
    private javax.swing.JButton BillToDate_Button;
    private javax.swing.JButton Bill_generateReport_Button;
    private javax.swing.JTextField Billno_text;
    private javax.swing.JRadioButton QTdate_radio;
    private javax.swing.JButton QtFromDate_button;
    private javax.swing.JTextField QtNoUpto_text;
    private javax.swing.JPanel QtSeries_panel;
    private javax.swing.JRadioButton QtSeries_radio;
    private javax.swing.JButton QtToDate_Button;
    private javax.swing.JTextField QtToDate_Text;
    private javax.swing.JButton Qt_GenerateReport_button;
    private javax.swing.JComboBox Qtwarehouse_combo;
    private javax.swing.JPanel billDate_panel;
    private javax.swing.JRadioButton billDate_radio;
    private javax.swing.JButton billFromDate_button;
    private javax.swing.JPanel billSeries_panel;
    private javax.swing.JRadioButton billSeries_radio;
    private javax.swing.JPanel bill_panel;
    private javax.swing.JPanel billno_panel;
    private javax.swing.JRadioButton billno_radio;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField qtFromdate_text;
    private javax.swing.JTextField qtNoFrom_text;
    private javax.swing.JPanel qtdate_panel;
    private javax.swing.JComboBox warehouse_Combo;
    // End of variables declaration//GEN-END:variables


 public String getTitle() {
   return "QT Report";  
    }

    @Override
    public void activate() throws BasicException {
        
      ButtonGrp();
      loaddata();
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


    public void loaddata(){
        billno_radio.setSelected(true);
        QTdate_radio.setSelected(true);
        
        WarehouseList = GetWarehouseList(m_App);
        WarehouseListModel=new ComboBoxValModel(WarehouseList);
        warehouse_Combo.setModel(WarehouseListModel);
        Qtwarehouse_combo.setModel(WarehouseListModel);
        
    }
    
    
    public void ButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(billDate_radio);
        bg.add(billSeries_radio);
        bg.add(billno_radio);
        
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(QTdate_radio);
        bg1.add(QtSeries_radio);
     }
    
    public int CheckForBillNo(String BillNo) throws BasicException{
        int flag=0;
        
        Object o = null;
       
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM BILL WHERE ID=?   \n" +
                                                     "UNION\n" +
                                                     "SELECT ID FROM BILL_ARV WHERE ID=?  ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ BillNo , BillNo});
        if(o!=null && o!=""){
            flag=1;
        }
        else{
            flag=0;
        }
        
        return flag;
    }
    
    
    public String GetWarehouseFromBillNo(String BillNo) throws BasicException{
        String  Warehouse=null;
        
        Object o = null;
       
        
       
        o =  new StaticSentence(m_App.getSession(), "select l.name\n" +
                                                    "from bill b , locations l\n" +
                                                    "where b.id=? and b.warehouse=l.id\n" +
                                                    "union\n" +
                                                    "select l.name\n" +
                                                    "from bill_arv b , locations l\n" +
                                                    "where b.id=? and b.warehouse=l.id ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ BillNo , BillNo});
        if(o!=null && o!=""){
            Warehouse=o.toString();
        }
        else{
            Warehouse="";
        }
        
        return Warehouse;
    }
    
    
     public int CheckForWarehouse(String BillNo1 , String BillNo2) throws BasicException{
        int flag=0;
        Object o1 = null;
        Object o2 = null;
        String s1=null;
        String s2=null;
        
        o1 =  new StaticSentence(m_App.getSession(), "SELECT WAREHOUSE FROM BILL WHERE ID=?   \n" +
                                                     "UNION\n" +
                                                     "SELECT WAREHOUSE FROM BILL_ARV WHERE ID=?  ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ BillNo1 , BillNo1});
        
        o2 =  new StaticSentence(m_App.getSession(), "SELECT WAREHOUSE FROM BILL WHERE ID=?   \n" +
                                                     "UNION\n" +
                                                     "SELECT WAREHOUSE FROM BILL_ARV WHERE ID=?  ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ BillNo2 , BillNo2 });
        
        if(o1!=null){
            s1=o1.toString();
        }
        if(o2!=null){
            s2=o2.toString();
        }
        
        if(s1.equals(s2)){
            flag=1;
        }
        else{
            flag=0;
        }
        
        return flag;
    }
    
    
     
      public Date getBillDateByBillNo(String BillNo) throws BasicException{
        Date  Warehouse=null;
        Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "select  b.createddate \n" +
                                                    "from bill b  \n" +
                                                    "where b.id=?  \n" +
                                                    "union\n" +
                                                    "select b.createddate \n" +
                                                    "from bill_arv b  \n" +
                                                    "where b.id=?   ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP })).find(new Object[]{ BillNo , BillNo});
        if(obj1!=null ){
            Warehouse=(Date) (obj1[0]);
        }
        
        
        return Warehouse;
    }
     
    
    public String getWarehouseIdFromBillno(String BillNo) throws BasicException{
        String  Warehouse=null;
        Object o = null;
        o =  new StaticSentence(m_App.getSession(), "select b.warehouse\n" +
                                                    "from bill b  \n" +
                                                    "where b.id=?  \n" +
                                                    "union\n" +
                                                    "select b.warehouse \n" +
                                                    "from bill_arv b  \n" +
                                                    "where b.id=?   ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ BillNo , BillNo});
        if(o!=null && o!=""){
            Warehouse=o.toString();
        }
        else{
            Warehouse="";
        }
        
        return Warehouse;
    }  
      
    
    public List GetWarehouseList (AppView app){
         List<Object> warehouse_List = new ArrayList<Object>();
         try {
            warehouse_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT name from locations order by name ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(QTDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
           return warehouse_List;
       }  
    
    public String getWarehouseIdFromName(String WarehouseName) throws BasicException{
        String  Warehouse=null;
        Object o = null;
        o =  new StaticSentence(m_App.getSession(), "  select id from locations where name=?  ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE  ).find(WarehouseName);
        if(o!=null && o!=""){
            Warehouse=o.toString();
        }
        else{
            Warehouse="";
        }
        
        return Warehouse;
    }
     
    
    public int CheckForQTWarehouse(String QTNo1 , String QTNo2) throws BasicException{
        int flag=0;
        Object o1 = null;
        Object o2 = null;
        String s1=null;
        String s2=null;
        
        o1 =  new StaticSentence(m_App.getSession(), "SELECT WAREHOUSE FROM qticket WHERE ID=?   \n" +
                                                     "UNION\n" +
                                                     "SELECT WAREHOUSE FROM qticket_arv WHERE ID=?  ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ QTNo1 , QTNo2});
        
        o2 =  new StaticSentence(m_App.getSession(), "SELECT WAREHOUSE FROM qticket  WHERE ID=?   \n" +
                                                     "UNION\n" +
                                                     "SELECT WAREHOUSE FROM qticket_arv WHERE ID=?  ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ QTNo1 , QTNo2 });
        
        if(o1!=null){
            s1=o1.toString();
        }
        if(o2!=null){
            s2=o2.toString();
        }
        
        if(s1.equals(s2)){
            flag=1;
        }
        else{
            flag=0;
        }
        
        return flag;
    }
    
    
    public int CheckForQTNo(String QtNo) throws BasicException{
        int flag=0;
        
        Object o = null;
       
        
       
        o =  new StaticSentence(m_App.getSession(), "SELECT ID FROM QTICKET WHERE ID=?   \n" +
                                                     "UNION\n" +
                                                     "SELECT ID FROM QTICKET_ARV WHERE ID=?  ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ QtNo , QtNo});
        if(o!=null && o!=""){
            flag=1;
        }
        else{
            flag=0;
        }
        
        return flag;
    }
    
    
    public Date getQtDateByQtno(String QTNO) throws BasicException{
        Date  Warehouse=null;
        Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "select  b.crdate \n" +
                                                    "from qticket b  \n" +
                                                    "where b.id=?  \n" +
                                                    "union\n" +
                                                    "select b.crdate \n" +
                                                    "from qticket_arv b  \n" +
                                                    "where b.id=?   ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP })).find(new Object[]{ QTNO , QTNO});
        if(obj1!=null ){
            Warehouse=(Date) (obj1[0]);
        }
        
        
        return Warehouse;
    }
    
    
     public String getWarehouseIdFromQTNo(String QTNo) throws BasicException{
        String  Warehouse=null;
        Object o = null;
        o =  new StaticSentence(m_App.getSession(), "select b.warehouse\n" +
                                                    "from qticket b  \n" +
                                                    "where b.id=?  \n" +
                                                    "union\n" +
                                                    "select b.warehouse \n" +
                                                    "from qticket_arv b  \n" +
                                                    "where b.id=?   ", 
                             new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }),
                             SerializerReadString.INSTANCE  ).find(new Object[]{ QTNo , QTNo});
        if(o!=null && o!=""){
            Warehouse=o.toString();
        }
        else{
            Warehouse="";
        }
        
        return Warehouse;
    }  
    
     
     public String getWarehouseNameFromID(String WarehouseID) throws BasicException{
        String  Warehouse=null;
        Object o = null;
        o =  new StaticSentence(m_App.getSession(), "  select name from locations where id=?  ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE  ).find(WarehouseID);
        if(o!=null && o!=""){
            Warehouse=o.toString();
        }
        else{
            Warehouse="";
        }
        
        return Warehouse;
    }
     
     
     
}
