 
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;
import java.util.Date;
import com.openbravo.format.Formats;
import java.util.ArrayList;
import java.util.Calendar;
import com.openbravo.data.gui.MessageInf;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.BankDetailsTableMOdel;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
 
public class BankChargesCollectedReports extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    private AppView m_App;
    private List<BankChargesCollectedReportsTableMOdel.BankCardInfo> BankChargesCollectedList;
    private BankChargesCollectedReportsTableMOdel bankChargesCollectedReports_Table_MOdel;
    public String BankDetail_id;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
  
  
    public BankChargesCollectedReports() {
        initComponents();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        month_Rdo = new javax.swing.JRadioButton();
        period_Rdo = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        frmDate_text = new javax.swing.JTextField();
        toDate_Text = new javax.swing.JTextField();
        DateSelect = new javax.swing.JButton();
        DateSelect1 = new javax.swing.JButton();
        DateSelect2 = new javax.swing.JButton();
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
        ExecuteButton = new javax.swing.JButton();
        receiptno_radio = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        bankName_radio = new javax.swing.JRadioButton();
        accountname_radio = new javax.swing.JRadioButton();
        createddate_radio = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
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
        edit_button = new javax.swing.JButton();
        DeactivateButton = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        bank_perc_text = new javax.swing.JTextField();
        bankAccount_combo2 = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        bankname_text = new javax.swing.JTextField();
        save_button = new javax.swing.JButton();
        minimumcharges_text = new javax.swing.JTextField();
        reset_button = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        defaultBank_label = new javax.swing.JLabel();
        makeDefault_Button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        bank_charges_account = new javax.swing.JComboBox<>();
        bank_charges_Button = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(133, 8, 8));
        jLabel2.setText("Card Payment Collection Reports");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(133, 8, 8));
        jLabel3.setText("Select  Duration:");

        month_Rdo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        month_Rdo.setText("Month");
        month_Rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                month_RdoItemStateChanged(evt);
            }
        });

        period_Rdo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        period_Rdo.setText("Period");
        period_Rdo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_RdoItemStateChanged(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(toDate_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frmDate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(DateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateSelect2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DateSelect1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frmDate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateSelect)
                    .addComponent(DateSelect2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toDate_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateSelect1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        ExecuteButton.setText("Execute");
        ExecuteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteButtonActionPerformed(evt);
            }
        });

        receiptno_radio.setText("Receipt No ");

        jLabel1.setText("Order by :  ");

        bankName_radio.setText("Bank Name ");

        accountname_radio.setText("Account Name ");

        createddate_radio.setText("Created Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(358, 358, 358))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(month_Rdo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(period_Rdo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(createddate_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(receiptno_radio)
                                .addGap(18, 18, 18)
                                .addComponent(bankName_radio)
                                .addGap(18, 18, 18)
                                .addComponent(accountname_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ExecuteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month_Rdo)
                    .addComponent(period_Rdo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(receiptno_radio)
                    .addComponent(jLabel1)
                    .addComponent(bankName_radio)
                    .addComponent(accountname_radio)
                    .addComponent(ExecuteButton)
                    .addComponent(createddate_radio))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View List", jPanel1);

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel64.setText("Bank Details for Credit/Debit cards");

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
        jScrollPane5.setViewportView(jTable2);

        edit_button.setText("Edit");
        edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_buttonActionPerformed(evt);
            }
        });

        DeactivateButton.setText("Deactivate");
        DeactivateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeactivateButtonActionPerformed(evt);
            }
        });

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Create New", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, null, java.awt.Color.red));

        jLabel70.setText("Account :");

        jLabel65.setText("Bank Name : ");

        bankAccount_combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));

        jLabel67.setText("Eg. (2.00)");

        save_button.setText("Save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        reset_button.setText("Reset");
        reset_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_buttonActionPerformed(evt);
            }
        });

        jLabel66.setText("CC Perc. (%)");

        jLabel72.setText("Minimum Fixed Charges");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bankname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(bankAccount_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                            .addComponent(reset_button)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(save_button))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel67)
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(bank_perc_text, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(minimumcharges_text, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(bankname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bank_perc_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minimumcharges_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66)
                    .addComponent(jLabel72))
                .addGap(8, 8, 8)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankAccount_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_button)
                    .addComponent(reset_button))
                .addContainerGap())
        );

        jLabel4.setText("Default Bank selected :  ");

        defaultBank_label.setForeground(new java.awt.Color(0, 0, 255));
        defaultBank_label.setText("jLabel5");

        makeDefault_Button.setText("Make Default");
        makeDefault_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeDefault_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel64))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(defaultBank_label, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(makeDefault_Button))
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addComponent(DeactivateButton)
                                    .addGap(308, 308, 308)
                                    .addComponent(edit_button))
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(defaultBank_label)
                    .addComponent(makeDefault_Button))
                .addGap(18, 35, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit_button)
                    .addComponent(DeactivateButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(96, 96, 96))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Credit/Debit card Bank Account settings", jPanel3);

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel68.setText("Bank charges for credit/debit card payments");

        jLabel69.setText("Select Account : ");

        bank_charges_account.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {   }));

        bank_charges_Button.setText("Save");
        bank_charges_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bank_charges_ButtonActionPerformed(evt);
            }
        });

        jLabel71.setText("Note : ");

        jLabel5.setText("Addition bank charges collected will be reflected in the account selected.");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68)
                            .addComponent(bank_charges_account, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(416, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bank_charges_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addGap(36, 36, 36)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(bank_charges_account, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(bank_charges_Button)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bank Charges for Credit/Debit Cards", jPanel4);

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

    private void month_RdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_month_RdoItemStateChanged
        DateSelect2.setVisible(true);
        DateSelect.setVisible(false);
        DateSelect1.setVisible(false);
        frmDate_text.setText("");
        toDate_Text.setText("");
    }//GEN-LAST:event_month_RdoItemStateChanged

    private void period_RdoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_RdoItemStateChanged
        DateSelect.setVisible(true);
        DateSelect1.setVisible(true);
        DateSelect2.setVisible(false);
        frmDate_text.setText("");
        toDate_Text.setText("");

    }//GEN-LAST:event_period_RdoItemStateChanged

    private void DateSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelectActionPerformed

        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frmDate_text.getText());
        } catch (BasicException e) {
            date = null;
        }

        try{
            date = JCalendarDialog.showCalendarTimeHours(this, date);
            if (date != null) {
                frmDate_text.setText(Formats.TIMESTAMP.formatValue(date));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_DateSelectActionPerformed

    private void DateSelect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect1ActionPerformed
        Date date=new Date();
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(toDate_Text.getText());
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
                toDate_Text.setText(Formats.TIMESTAMP.formatValue(cal.getTime()));
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }//GEN-LAST:event_DateSelect1ActionPerformed

    private void DateSelect2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSelect2ActionPerformed

        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(frmDate_text.getText());
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
            frmDate_text.setText(Formats.TIMESTAMP.formatValue(date));
            frmDate_text.setText(Formats.TIMESTAMP.formatValue(date));

            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.MONTH, 1);

            cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());

            toDate_Text.setText(Formats.TIMESTAMP.formatValue(date));
            toDate_Text.setText(Formats.TIMESTAMP.formatValue(date));

            /* ReportHeader = "Advance Payment Report for All Members ";
            header_text.setText(ReportHeader);
            report_btn.setEnabled(true);
            gen_report_room.setEnabled(true);*/

        }

    }//GEN-LAST:event_DateSelect2ActionPerformed

    Date FromDate ; 
    Date ToDate;
    
    private void ExecuteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteButtonActionPerformed
        
        int OrderFlag = 0;
        
        if(createddate_radio.isSelected()){
            OrderFlag=1;
        }
        if(receiptno_radio.isSelected()){
            OrderFlag=2;
        }
        if(bankName_radio.isSelected()){
            OrderFlag=3;
        }
        if(accountname_radio.isSelected()){
            OrderFlag=4;
        }
        
        
        
        if(frmDate_text.getText()!=null && frmDate_text.getText().length()>0){
           if(toDate_Text.getText()!=null && toDate_Text.getText().length()>0){ 
               try {
                    FromDate = (Date) Formats.TIMESTAMP.parseValue(frmDate_text.getText());
                    ToDate = (Date) Formats.TIMESTAMP.parseValue(toDate_Text.getText());
                    
                    
                    BankChargesCollectedList = new ArrayList<BankChargesCollectedReportsTableMOdel.BankCardInfo>();
                    bankChargesCollectedReports_Table_MOdel = BankChargesCollectedReportsTableMOdel.loadBankCollectionDetails(m_App, FromDate, ToDate , OrderFlag);
                    
                    jTable1.setModel(bankChargesCollectedReports_Table_MOdel.getTableModel());  
                    jTable1.setVisible(true);
                    
                    
                } catch (BasicException ex) {
                        Logger.getLogger(BillQTCancellationReport.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
               
               
               
               
               
            
           }
           else{
                JOptionPane.showMessageDialog(this, "Select to date  First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
           }
        }
        else{
             JOptionPane.showMessageDialog(this, "Select from date  First ... !! " ,"Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ExecuteButtonActionPerformed

    
     private BankDetailsTableMOdel bankDetailsTable_MOdel;
      private List<BankDetailsTableMOdel.BankInfo> BankList = new ArrayList<BankDetailsTableMOdel.BankInfo>();
      
      private List<Object> BankCharges_AccountList2 = new ArrayList<Object>();
      private ComboBoxValModel BankCharge_AccountModel2 ;
    
    private void edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_buttonActionPerformed
        if(jTable2.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(bill == JOptionPane.YES_OPTION)
            {

                if(jTable2.getSelectedRow()<bankDetailsTable_MOdel.getSize()){
                    int row = jTable2.getSelectedRow();
                    BankDetailsTableMOdel.BankInfo showdata = bankDetailsTable_MOdel.getEmailgroupList().get(row);

                    BankDetail_id = showdata.getID();
                    String BANKNAME = showdata.getNAME();
                    Double PERC = showdata.getPERC();
                    bankname_text.setText(BANKNAME);
                    bankname_text.setEditable(false);
                    bank_perc_text.setText(decimalFormat.format(PERC));
                    minimumcharges_text.setText(decimalFormat.format(showdata.getFixedCharges()));

                    if(showdata.getAccountid()!=null){
                        String AccountidX = showdata.getAccountid();
                        for(int i=0;i<BankCharges_AccountList2.size();i++){
                            String x = BankCharges_AccountList2.get(i).toString();
                            if(x.equals(AccountidX)){
                                bankAccount_combo2.setSelectedItem(x);
                                break;
                            }
                        }

                    }

                }
            }
        }
    }//GEN-LAST:event_edit_buttonActionPerformed

    private void DeactivateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeactivateButtonActionPerformed
        if(jTable2.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to Deactivate details ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(bill == JOptionPane.YES_OPTION)
            {

                if(jTable2.getSelectedRow()<bankDetailsTable_MOdel.getSize()){
                    int row = jTable2.getSelectedRow();
                    BankDetailsTableMOdel.BankInfo showdata = bankDetailsTable_MOdel.getEmailgroupList().get(row);

                    String id = showdata.getID();
                    try{
                        new PreparedSentence(m_App.getSession()
                            , "UPDATE bank_details  SET active=0 , deacby=? , deacdate=?  WHERE id=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING})).exec(new Object[]{ m_App.getAppUserView().getUser().getName() , new Date(),id });

                        JOptionPane.showMessageDialog(this, "Deactivated successfully.");
                        LoadBankTable();

                    }
                    catch(BasicException e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }//GEN-LAST:event_DeactivateButtonActionPerformed

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        if(bankname_text.getText()!=null){
            if(bank_perc_text.getText()!=null && bankAccount_combo2.getSelectedIndex()!=-1){
                if(minimumcharges_text.getText()!=null && minimumcharges_text.getText().length()>0){

                    try{
                        String Accountname = bankAccount_combo2.getSelectedItem().toString();
                        String bankname = bankname_text.getText().trim();
                        Double Perc = Double.parseDouble(bank_perc_text.getText());
                        Double FixedCharges = Double.parseDouble(minimumcharges_text.getText());

                        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID from accountmaster where name =?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Accountname);
                        if(obj!=null){

                            String Accountid=obj[0].toString();
                            if(BankDetail_id!=null){

                                new PreparedSentence(m_App.getSession()
                                    , "UPDATE bank_details  SET active=0 , deacby=? , deacdate=?  WHERE id=?"
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING})).exec(new Object[]{ m_App.getAppUserView().getUser().getName() , new Date(),BankDetail_id });

                                new PreparedSentence(m_App.getSession()
                                    , "INSERT INTO bank_details (ID,name,perc,active,crby,crdate,deacref,accountid , fixedcharges) VALUES( ?,?,?,?,?,?,?,?,?)"
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING , Datas.DOUBLE }))
                                .exec(new Object[]{UUID.randomUUID().toString(),bankname,Perc,1, m_App.getAppUserView().getUser().getName() , new Date(), BankDetail_id ,Accountid , FixedCharges});

                            }
                            else{
                                new PreparedSentence(m_App.getSession()
                                    , "INSERT INTO bank_details (ID,name,perc,active,crby,crdate,accountid , fixedcharges) VALUES( ?,?,?,?,?,?,?,?)"
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.DOUBLE }))
                                .exec(new Object[]{UUID.randomUUID().toString(),bankname,Perc,1, m_App.getAppUserView().getUser().getName() , new Date() , Accountid , FixedCharges });

                            }

                            JOptionPane.showMessageDialog(this, "Successfully Updated");
                            bankname_text.setText(null);
                            bank_perc_text.setText(null);
                            LoadBankTable();
                        }
                        else{

                        }

                    }
                    catch(BasicException e){
                        e.printStackTrace();
                    }
                    catch(NumberFormatException e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Please enter correct percentage format");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(this, "Please enter fixed charges. if not applied, keep it 0.00");
                }

            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter correct values");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter bank name.");
        }
    }//GEN-LAST:event_save_buttonActionPerformed

    private void reset_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_buttonActionPerformed
        resetBankTableValues();
    }//GEN-LAST:event_reset_buttonActionPerformed

    private void bank_charges_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bank_charges_ButtonActionPerformed
        if(bank_charges_account.getSelectedIndex()!=-1){
            String selectedBankAccount= bank_charges_account.getSelectedItem().toString();
            String Namex = "Bank Charges Collected Account";
            try{
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID from accountmaster where name =?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedBankAccount);
                if(obj!=null){
                    if(obj[0]!=null){
                        String BankAcctID=obj[0].toString();
                        if(new PreparedSentence(m_App.getSession()
                            , "UPDATE generaltable   SET value=?   WHERE Name='Bank Charges Collected Account' "
                            , new SerializerWriteBasic(new Datas[]{  Datas.STRING})).exec(new Object[]{ BankAcctID })<=0){

                        new PreparedSentence(m_App.getSession()
                            , "INSERT INTO generaltable (ID,name,value ) VALUES( ?,?,? )"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.STRING }))
                        .exec(new Object[]{UUID.randomUUID().toString() , Namex ,BankAcctID  });
                        }
                    }

                }
            }
            catch(BasicException e){
                e.printStackTrace();
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "please select bank account first.");
        }
    }//GEN-LAST:event_bank_charges_ButtonActionPerformed

    private void makeDefault_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeDefault_ButtonActionPerformed
      if(jTable2.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel2, " Do you want to make selected bank as default ? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(bill == JOptionPane.YES_OPTION)
            {

                if(jTable2.getSelectedRow()<bankDetailsTable_MOdel.getSize()){
                    int row = jTable2.getSelectedRow();
                    BankDetailsTableMOdel.BankInfo showdata = bankDetailsTable_MOdel.getEmailgroupList().get(row);

                    String id = showdata.getID();
                    try{
                        int xyz=0;
                        new PreparedSentence(m_App.getSession()
                            , "UPDATE bank_details  SET defaultflag= ?  "
                            , new SerializerWriteBasic(new Datas[]{Datas.INT  })).exec(new Object[]{ xyz });
                        
                        new PreparedSentence(m_App.getSession()
                            , "UPDATE bank_details  SET defaultflag=1  WHERE id=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec(new Object[]{ id });

                        JOptionPane.showMessageDialog(this, "Default value successfully updated");
                        LoadBankTable();

                    }
                    catch(BasicException e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }//GEN-LAST:event_makeDefault_ButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DateSelect;
    private javax.swing.JButton DateSelect1;
    private javax.swing.JButton DateSelect2;
    private javax.swing.JButton DeactivateButton;
    private javax.swing.JButton ExecuteButton;
    private javax.swing.JRadioButton accountname_radio;
    private javax.swing.JComboBox<String> bankAccount_combo2;
    private javax.swing.JRadioButton bankName_radio;
    private javax.swing.JButton bank_charges_Button;
    private javax.swing.JComboBox<String> bank_charges_account;
    private javax.swing.JTextField bank_perc_text;
    private javax.swing.JTextField bankname_text;
    private javax.swing.JRadioButton createddate_radio;
    private javax.swing.JLabel defaultBank_label;
    private javax.swing.JButton edit_button;
    private javax.swing.JTextField frmDate_text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton makeDefault_Button;
    private javax.swing.JTextField minimumcharges_text;
    private javax.swing.JRadioButton month_Rdo;
    private javax.swing.JRadioButton period_Rdo;
    private javax.swing.JRadioButton receiptno_radio;
    private javax.swing.JButton reset_button;
    private javax.swing.JButton save_button;
    private javax.swing.JTextField toDate_Text;
    // End of variables declaration//GEN-END:variables

@Override
    public String getTitle() {
   return "Card Payment Collected Reports";  
    }

    @Override
    public void activate() throws BasicException {
        ButtonGroupx();
        month_Rdo.setSelected(true);
        DateSelect2.setVisible(true);
        DateSelect.setVisible(false);
        DateSelect1.setVisible(false);
        jTable1.setVisible(false);
        createddate_radio.setSelected(true);
        LoadBankTable();
        loadBankCharges();
        Boolean BankChargesPermissionFlag = m_App.getAppUserView().getUser().hasPermission("Bank Charges Permission");
        Boolean BankAccountPermission = m_App.getAppUserView().getUser().hasPermission("Bank Account Permission");
        if(BankChargesPermissionFlag){
            jTabbedPane1.setEnabledAt(2, true);
        }
        else{
            jTabbedPane1.setEnabledAt(2, false);
        }
        
        if(BankAccountPermission){
            jTabbedPane1.setEnabledAt(1, true);
        }
        else{
            jTabbedPane1.setEnabledAt(1, false);
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
      m_App=app;
   
    }

    @Override
    public Object getBean() {
  
    return this;
    }

    public void ButtonGroupx(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(month_Rdo);
        bg.add(period_Rdo);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(bankName_radio);
        bg2.add(accountname_radio);
        bg2.add(receiptno_radio);
        bg2.add(createddate_radio);
        
    }

      public void resetBankTableValues(){
          bankname_text.setText(null);
          bankname_text.setEditable(true);
          bank_perc_text.setText(null);
          bankAccount_combo2.setSelectedIndex(-1);
          BankDetail_id=null;
          minimumcharges_text.setText("0.00");
          LoadBankTable();
      }
      
      public void LoadBankTable(){
          try{
            BankList = new ArrayList<BankDetailsTableMOdel.BankInfo>();
            BankDetail_id=null;
            bankname_text.setEditable(true);
            BankCharges_AccountList2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name from accountmaster where parent='1.1.2' or parent='2.1.3'  ORDER BY name ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
            BankCharge_AccountModel2 = new ComboBoxValModel(BankCharges_AccountList2);
            bankAccount_combo2.setModel(BankCharge_AccountModel2);
            bankDetailsTable_MOdel = BankDetailsTableMOdel.loademailGroupNameList(m_App);
            BankList = bankDetailsTable_MOdel.getEmailgroupList();
            jTable2.setModel(bankDetailsTable_MOdel.getTableModel()); 
            String DefaultBankName = "No Default value selected.";
            for(int i=0;i<BankList.size();i++){
                int DefaultBank = BankList.get(i).getDefaultBank();
                
                if(DefaultBank==1){
                    DefaultBankName = BankList.get(i).getNAME();
                }
            }
            defaultBank_label.setText(DefaultBankName);
            
            
            minimumcharges_text.setText("0.00");
            
          }
          catch(BasicException e){
              e.printStackTrace();
          }
       }
      
      
      private List<Object> BankCharges_AccountList = new ArrayList<Object>();
      private ComboBoxValModel BankCharge_AccountModel ;
      
      public void loadBankCharges(){
        try{  
          //  BankCharges_AccountList  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name from accountmaster   ORDER BY name ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          //edited by pratima 
           BankCharges_AccountList  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name from accountmaster  where level_='S' ORDER BY name ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
           
          BankCharge_AccountModel = new ComboBoxValModel(BankCharges_AccountList);
            bank_charges_account.setModel(BankCharge_AccountModel);
        
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME= 'Bank Charges Collected Account'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
            if(obj!=null){
                String Accountid = obj[0].toString();
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT name FROM accountmaster where id=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Accountid);
                if(obj2!=null){
                   String accountname = obj2[0].toString();
                   for(int i=0;i<BankCharges_AccountList.size();i++){
                       String x = BankCharges_AccountList.get(i).toString();
                       if(x.equals(accountname)){
                           bank_charges_account.setSelectedItem(accountname);
                           break;
                       }
                   } 
                    
                }
            }
        }
        catch(BasicException e){
            e.printStackTrace();
        }
          
      }
}
