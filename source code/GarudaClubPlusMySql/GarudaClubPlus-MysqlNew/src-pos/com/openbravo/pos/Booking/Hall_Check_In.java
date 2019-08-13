
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.AdvanceAdjustTableModel.MyAbstractTableModel2;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;
import com.openbravo.pos.Booking.HallService.MyAbstractTableModel;
import java.util.Calendar;

public class Hall_Check_In extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    java.util.List<Object> Mem_list = new ArrayList<Object>();
    java.util.List<Object> Guest_list = new ArrayList<Object>();
    private ComboBoxValModel Memlist_model ;
    private ComboBoxValModel GuestList_model;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private AdvanceRecieveTableModel AdvanceRecv;
    private CheckInTableModel CheckInTable_Model;
    private List<CheckInTableModel.Hall_AdvInfo> HallDetailList;
    private HallCheckInTable Hall_CheckIn_Detail ; 
    private HallBillModel hallbill_Model;
    private List<HallBillModel.HallBillInfo> hall_Bill_list;
    private DataLogicSales m_dlSales;
    private DataLogicFacilities dmang;
    private HallService HS;
    private MyAbstractTableModel HallServiceTableModel;
    private List<HallService.BillInfo> BillInfoList;
    private AdvanceAdjustTableModel AdvanceAdjustTable_model;
    private List<AdvanceAdjustTableModel.AdvanceInfo> AdvanceInfoList;
    private MyAbstractTableModel2 AdvanceListTableModel; 
    //added By pratima
    ArrayList Recpt_list = new ArrayList<String>();
    private ComboBoxValModel Recptlist_model ;
    //end By pratima
   private HallCheckInTable GR_CheckIn_Detail ; 
    //private GuestRoomCheckInTable GR_CheckIn_Detail ; 
    String strictApprove1;
     private static DataLogicSales dlSales;
    
    public Hall_Check_In() {
        initComponents();
        //added By pratima
        RnoComboBox.setVisible(false);
        jLabelRno.setVisible(false);
        //end By pratima
        main_panel.setVisible(true);
        Gname_Combo.setSelectedIndex(-1);
        slot_panel.setVisible(false);
        tariff_panel.setVisible(false);
        hallname_label.setVisible(false);
        
         
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        chk_out_btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        tariff_panel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        AdvRec_label = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        totC_label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        due_label = new javax.swing.JLabel();
        hall_name_label = new javax.swing.JLabel();
        mem = new javax.swing.JRadioButton();
        nonmem = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        Gname_Combo = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        BillName = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        id_detail = new javax.swing.JTextField();
        advance_id_label = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        slot_panel = new javax.swing.JPanel();
        memNo = new javax.swing.JLabel();
        MemNo_label = new javax.swing.JLabel();
        memNo2 = new javax.swing.JLabel();
        slot_label = new javax.swing.JLabel();
        memNo3 = new javax.swing.JLabel();
        booked_date_label = new javax.swing.JLabel();
        tax1_label = new javax.swing.JLabel();
        tax2_label = new javax.swing.JLabel();
        tax3_label = new javax.swing.JLabel();
        member_ID_label = new javax.swing.JLabel();
        hall_id_label = new javax.swing.JLabel();
        hallname_label = new javax.swing.JLabel();
        booking_id_label = new javax.swing.JLabel();
        RnoComboBox = new javax.swing.JComboBox<>();
        jLabelRno = new javax.swing.JLabel();
        bill_panel = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mno_label = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        guest_name_label = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        adjustBill_button = new javax.swing.JButton();
        createbill_buttn = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        Hall_N_Label = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        mem_name_label = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        Biiling_name_label = new javax.swing.JLabel();
        bill_id_label = new javax.swing.JLabel();
        link_name_label = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Date_label = new javax.swing.JLabel();
        advance_Recv_label = new javax.swing.JLabel();
        basic2_label = new javax.swing.JLabel();
        basic1_label = new javax.swing.JLabel();
        slot_flag_label = new javax.swing.JLabel();
        tax1_rate_label = new javax.swing.JLabel();
        tax2_rate_label = new javax.swing.JLabel();
        tax3_rate_label = new javax.swing.JLabel();
        checkin_id_label = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        booking_seq_no_label = new javax.swing.JLabel();
        tax1_id_label = new javax.swing.JLabel();
        tax2_id_label = new javax.swing.JLabel();
        tax3_id_label = new javax.swing.JLabel();
        hall_id_label2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dueamnt_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel451 = new javax.swing.JLabel();
        slot_time_label = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        charges = new javax.swing.JLabel();
        slot_book_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tax1_lebel = new javax.swing.JLabel();
        tax2_Label = new javax.swing.JLabel();
        tax3_Label = new javax.swing.JLabel();
        Tax_rate1_label = new javax.swing.JLabel();
        Tax_rate2_label = new javax.swing.JLabel();
        Tax_rate3_label = new javax.swing.JLabel();
        tax1_amt_label = new javax.swing.JLabel();
        tax2_amt_label = new javax.swing.JLabel();
        tax3_amt_label = new javax.swing.JLabel();
        Tax_Basic_Label1 = new javax.swing.JLabel();
        Tax_Basic_Label2 = new javax.swing.JLabel();
        Tax_Basic_Label3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Discount_Amt_label = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Discount_Amt_label_Total = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        hallservicetable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jScrollPane4 = new javax.swing.JScrollPane();
        advancedetailtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        hall_serv_chrg_label = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        tot_chrge = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        Tot_Bal_amt_text = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        refund_bal_amt_text = new javax.swing.JTextField();
        totalAmtAdjusted = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        Adjust_billedAmt_check = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        booking_id_Label = new javax.swing.JLabel();
        TotAdvRecv_Lable = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        billed_label = new javax.swing.JLabel();
        billno_label = new javax.swing.JLabel();
        tot_label = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        unadjustTotal_text = new javax.swing.JTextField();
        generateFinalReport_btn = new javax.swing.JButton();
        createbill_buttn1 = new javax.swing.JButton();
        AddDiscount_btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

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

        chk_out_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chk_out_btn.setForeground(new java.awt.Color(102, 0, 0));
        chk_out_btn.setText("Check  Out");
        chk_out_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_out_btnActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setText("Bill Re- Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 102));
        jButton3.setText("Bill Statement RePrint");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(chk_out_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_out_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Checked_In List", jPanel1);

        save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save.setForeground(new java.awt.Color(0, 102, 51));
        save.setText("Submit");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cancel.setForeground(new java.awt.Color(153, 0, 51));
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        tariff_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Advance Recieved :");

        AdvRec_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AdvRec_label.setText("Unknown");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Total Charge : ");

        totC_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totC_label.setText("Unknown");

        jLabel2.setText("-----------------------------------------------------------");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Due Amount :");

        due_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        due_label.setText("Unknown");

        javax.swing.GroupLayout tariff_panelLayout = new javax.swing.GroupLayout(tariff_panel);
        tariff_panel.setLayout(tariff_panelLayout);
        tariff_panelLayout.setHorizontalGroup(
            tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_panelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
            .addGroup(tariff_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(tariff_panelLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(51, 51, 51)
                        .addComponent(due_label))
                    .addGroup(tariff_panelLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totC_label)
                            .addComponent(AdvRec_label))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        tariff_panelLayout.setVerticalGroup(
            tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(totC_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(AdvRec_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(due_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AdvRec_label.setForeground(Color.RED);
        totC_label.setForeground(Color.RED);
        due_label.setForeground(Color.RED);

        hall_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hall_name_label.setText("Hall Name :");

        mem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem.setText("Member");
        mem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memItemStateChanged(evt);
            }
        });

        nonmem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nonmem.setText("Guest");
        nonmem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nonmemItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Select Name : ");

        Gname_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        Gname_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Gname_ComboItemStateChanged(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Billing Name :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("ID Card Detail : ");

        id_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_detailActionPerformed(evt);
            }
        });

        advance_id_label.setText("jLabel5");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 204));
        jLabel46.setText("Hall Check In Menu");

        slot_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        memNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        memNo.setText("Member No :");

        MemNo_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MemNo_label.setText("Mem No");

        memNo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        memNo2.setText("Slot Booked :");

        slot_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        slot_label.setText("jLabel4");

        memNo3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        memNo3.setText("Booking Date :");

        booked_date_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        booked_date_label.setText("Booking Date");

        javax.swing.GroupLayout slot_panelLayout = new javax.swing.GroupLayout(slot_panel);
        slot_panel.setLayout(slot_panelLayout);
        slot_panelLayout.setHorizontalGroup(
            slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(memNo)
                    .addComponent(memNo2)
                    .addComponent(memNo3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(booked_date_label)
                    .addComponent(MemNo_label)
                    .addComponent(slot_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        slot_panelLayout.setVerticalGroup(
            slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memNo)
                    .addComponent(MemNo_label))
                .addGap(18, 18, 18)
                .addGroup(slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memNo3)
                    .addComponent(booked_date_label))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(slot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memNo2)
                    .addComponent(slot_label))
                .addGap(24, 24, 24))
        );

        MemNo_label.setForeground(Color.RED);
        slot_label.setForeground(Color.BLUE);
        MemNo_label.setForeground(Color.RED);

        tax1_label.setText("jLabel1");

        tax2_label.setText("jLabel3");

        tax3_label.setText("jLabel4");

        member_ID_label.setText("jLabel5");

        hall_id_label.setText("jLabel1");

        hallname_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hallname_label.setText("jLabel20");

        booking_id_label.setText("bookingId");

        RnoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        RnoComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RnoComboBoxItemStateChanged(evt);
            }
        });

        jLabelRno.setText("jLabelRno");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(268, Short.MAX_VALUE)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
                                .addComponent(mem)
                                .addGap(18, 18, 18)
                                .addComponent(nonmem, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tax3_label)
                                    .addComponent(member_ID_label)
                                    .addComponent(hall_id_label)
                                    .addComponent(tax2_label)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel27)
                                            .addComponent(hall_name_label)
                                            .addComponent(tax1_label)
                                            .addComponent(advance_id_label))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BillName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hallname_label, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(slot_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tariff_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabelRno)
                                                .addGap(21, 21, 21)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Gname_Combo, 0, 175, Short.MAX_VALUE)
                                            .addComponent(RnoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(147, 147, 147)))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(booking_id_label)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mem)
                    .addComponent(nonmem))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hall_name_label)
                            .addComponent(hallname_label))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(BillName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(id_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(advance_id_label)
                        .addGap(18, 18, 18)
                        .addComponent(tax1_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tax2_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tax3_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(member_ID_label)
                        .addGap(18, 18, 18)
                        .addComponent(hall_id_label)
                        .addGap(14, 14, 14)
                        .addComponent(booking_id_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(Gname_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RnoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(slot_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tariff_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(Cancel))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        advance_id_label.setVisible(false);
        advance_id_label.setForeground(Color.BLUE);
        tax1_label.setVisible(false);
        tax2_label.setVisible(false);
        tax3_label.setVisible(false);
        member_ID_label.setVisible(false);
        hall_id_label.setVisible(false);
        hallname_label.setForeground(Color.BLUE);
        booking_id_label.setVisible(false);

        jTabbedPane1.addTab("Check In", jPanel4);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setText("Bill Menu");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Member No : ");

        mno_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mno_label.setText("jLabel11");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Guest Name :");

        guest_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guest_name_label.setText("GName");

        cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(153, 0, 0));
        cancel.setText("Back");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        adjustBill_button.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        adjustBill_button.setForeground(new java.awt.Color(153, 0, 51));
        adjustBill_button.setText("Adjust Bill");
        adjustBill_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjustBill_buttonActionPerformed(evt);
            }
        });

        createbill_buttn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        createbill_buttn.setForeground(new java.awt.Color(51, 51, 255));
        createbill_buttn.setText("Create Bill");
        createbill_buttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createbill_buttnActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Hall Name :");

        Hall_N_Label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Hall_N_Label.setText("jLabel41");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Member Name : ");

        mem_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem_name_label.setText("jLabel11");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Billing Name :");

        Biiling_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Biiling_name_label.setText("Bill Name");

        bill_id_label.setText("jLabel48");

        link_name_label.setText("jLabel48");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Date :");

        Date_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Date_label.setText("Date2");

        advance_Recv_label.setText("jLabel1");

        basic2_label.setText("basic2");

        basic1_label.setText("basic1");

        slot_flag_label.setText("slot_flag");

        tax1_rate_label.setText("jLabel1");

        tax2_rate_label.setText("jLabel3");

        tax3_rate_label.setText("jLabel4");

        checkin_id_label.setText("check_in_id");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Booking No: ");

        booking_seq_no_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        booking_seq_no_label.setText("Booking_id");

        tax1_id_label.setText("tax1_id");

        tax2_id_label.setText("tax2_id");

        tax3_id_label.setText("tax3_id");

        hall_id_label2.setText("hallId");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 0, 0));
        jLabel16.setText("Pending Bills ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Total :");

        dueamnt_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dueamnt_label.setText("Final Amt");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Details");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Rate");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Amount");

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Taxes");

        jLabel451.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel451.setText("Slot Booked : ");

        slot_time_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        slot_time_label.setText("Date1");

        rate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rate.setText("Rate");

        charges.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        charges.setText("Amt1");

        slot_book_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        slot_book_label.setText("Date1");

        jLabel1.setText("(1)");

        jLabel3.setText("(2)");

        jLabel4.setText("(3)");

        tax1_lebel.setText("Tax1");

        tax2_Label.setText("Tax2");

        tax3_Label.setText("Tax3");

        Tax_rate1_label.setText("Tax_rate1");

        Tax_rate2_label.setText("Tax_rate2");

        Tax_rate3_label.setText("Tax_rate3");

        tax1_amt_label.setText("T_AMT1");

        tax2_amt_label.setText("T_AMT2");

        tax3_amt_label.setText("T_AMT3");

        Tax_Basic_Label1.setText("(B)");

        Tax_Basic_Label2.setText("(B)");

        Tax_Basic_Label3.setText("(B)");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Discount :-");

        Discount_Amt_label.setText("T_AMT3");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Total : ");

        Discount_Amt_label_Total.setText("T_AMT3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel451)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(slot_book_label))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(slot_time_label))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax2_Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Tax_Basic_Label2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax1_lebel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Tax_Basic_Label1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax3_Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Tax_Basic_Label3))))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tax_rate1_label)
                            .addComponent(rate)
                            .addComponent(Tax_rate2_label)
                            .addComponent(Tax_rate3_label))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tax3_amt_label)
                                    .addComponent(charges)
                                    .addComponent(tax1_amt_label)
                                    .addComponent(tax2_amt_label)
                                    .addComponent(Discount_Amt_label)
                                    .addComponent(Discount_Amt_label_Total))))
                        .addGap(22, 22, 22))
                    .addComponent(jSeparator11)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(rate)
                        .addGap(100, 100, 100)
                        .addComponent(Tax_rate1_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tax_rate2_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tax_rate3_label))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel451)
                            .addComponent(slot_book_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slot_time_label)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel12)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel17)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1)
                            .addComponent(tax1_lebel)
                            .addComponent(Tax_Basic_Label1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tax2_Label)
                            .addComponent(Tax_Basic_Label2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tax3_Label)
                            .addComponent(Tax_Basic_Label3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(charges)
                        .addGap(27, 27, 27)
                        .addComponent(Discount_Amt_label)
                        .addGap(18, 18, 18)
                        .addComponent(Discount_Amt_label_Total)
                        .addGap(28, 28, 28)
                        .addComponent(tax1_amt_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tax2_amt_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tax3_amt_label)))
                .addGap(19, 19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3))
                .addContainerGap())
        );

        slot_time_label.setForeground(Color.BLUE);
        charges.setForeground(Color.BLUE);
        slot_book_label.setForeground(Color.BLUE);
        tax1_amt_label.setForeground(Color.BLUE);
        tax2_amt_label.setForeground(Color.BLUE);
        tax3_amt_label.setForeground(Color.BLUE);
        Discount_Amt_label.setForeground(Color.BLUE);
        Discount_Amt_label_Total.setForeground(Color.BLUE);

        jScrollPane2.setViewportView(jPanel2);

        hallservicetable.setModel(new javax.swing.table.DefaultTableModel(
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
        hallservicetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hallservicetableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(hallservicetable);

        advancedetailtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(advancedetailtable);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 0, 0));
        jLabel21.setText("Advance Detials");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Total :");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Total Billed Amt : ");

        tot_chrge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tot_chrge.setForeground(new java.awt.Color(0, 0, 204));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Total Bal Amt :");

        Tot_Bal_amt_text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Tot_Bal_amt_text.setForeground(new java.awt.Color(0, 51, 153));

        jCheckBox1.setText("Refund Balance Amount : ");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        totalAmtAdjusted.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totalAmtAdjusted.setForeground(new java.awt.Color(153, 0, 0));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 153));
        jLabel54.setText("Total :");

        Adjust_billedAmt_check.setText("Adjust");
        Adjust_billedAmt_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Adjust_billedAmt_checkItemStateChanged(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(102, 0, 0));

        jSeparator6.setForeground(new java.awt.Color(102, 0, 51));

        booking_id_Label.setText("jLabel5");

        TotAdvRecv_Lable.setText("TotAdvRecv_Lable");

        jPanel6.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel6AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton12.setText("Enter");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        billed_label.setText("billed");

        billno_label.setText("billno");

        jLabel18.setText("Total Unadjusted Amount : ");

        unadjustTotal_text.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        generateFinalReport_btn.setText("Generate Final Bill Statement");
        generateFinalReport_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateFinalReport_btnActionPerformed(evt);
            }
        });

        createbill_buttn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        createbill_buttn1.setForeground(new java.awt.Color(51, 51, 255));
        createbill_buttn1.setText("Check Bill");
        createbill_buttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createbill_buttn1ActionPerformed(evt);
            }
        });

        AddDiscount_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        AddDiscount_btn.setForeground(new java.awt.Color(204, 0, 0));
        AddDiscount_btn.setText("Give  Discount");
        AddDiscount_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDiscount_btnActionPerformed(evt);
            }
        });

        jLabel5.setText("jLabel5");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bill_panelLayout = new javax.swing.GroupLayout(bill_panel);
        bill_panel.setLayout(bill_panelLayout);
        bill_panelLayout.setHorizontalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(basic2_label)
                    .addComponent(basic1_label)
                    .addComponent(slot_flag_label)
                    .addComponent(checkin_id_label)
                    .addComponent(tax1_id_label)
                    .addComponent(tax2_id_label)
                    .addComponent(tax3_id_label))
                .addGap(36, 36, 36)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(mno_label)
                                        .addGap(99, 99, 99)
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Hall_N_Label))
                                    .addComponent(Biiling_name_label))
                                .addGap(69, 69, 69)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(booking_seq_no_label))
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(49, 49, 49)
                                        .addComponent(Date_label)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(guest_name_label)))
                                .addContainerGap(82, Short.MAX_VALUE))
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(mem_name_label)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addGap(229, 229, 229)
                                        .addComponent(tax3_rate_label))
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(generateFinalReport_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(106, 106, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(totalAmtAdjusted, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator6)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unadjustTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(hall_serv_chrg_label, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bill_panelLayout.createSequentialGroup()
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bill_panelLayout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund_bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bill_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Tot_Bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(167, 167, 167)
                                        .addComponent(jLabel54))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bill_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tot_chrge, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Adjust_billedAmt_check)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tot_label, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(adjustBill_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createbill_buttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancel)
                            .addComponent(createbill_buttn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddDiscount_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dueamnt_label)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(billed_label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(bill_panelLayout.createSequentialGroup()
                                                .addComponent(tax1_rate_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(hall_id_label2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(advance_Recv_label))
                                            .addGroup(bill_panelLayout.createSequentialGroup()
                                                .addComponent(billno_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(booking_id_Label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tax2_rate_label))
                                            .addGroup(bill_panelLayout.createSequentialGroup()
                                                .addComponent(link_name_label)
                                                .addGap(18, 18, 18)
                                                .addComponent(bill_id_label))))))
                            .addComponent(TotAdvRecv_Lable)))))
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bill_panelLayout.setVerticalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(mno_label)
                            .addComponent(jLabel14)
                            .addComponent(guest_name_label)
                            .addComponent(jLabel41)
                            .addComponent(Hall_N_Label)
                            .addComponent(TotAdvRecv_Lable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(mem_name_label)
                            .addComponent(jLabel13)
                            .addComponent(Date_label)
                            .addComponent(jLabel5)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(Biiling_name_label)
                            .addComponent(jLabel20)
                            .addComponent(booking_seq_no_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(AddDiscount_btn)
                                        .addGap(21, 21, 21)
                                        .addComponent(createbill_buttn1)
                                        .addGap(25, 25, 25)
                                        .addComponent(createbill_buttn)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel53)
                                        .addComponent(tot_chrge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Adjust_billedAmt_check)
                                        .addComponent(jLabel23)
                                        .addComponent(tot_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(tax3_id_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addComponent(tax2_id_label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax1_id_label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkin_id_label))))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(billed_label)
                                .addComponent(jLabel18)
                                .addComponent(unadjustTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hall_serv_chrg_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(adjustBill_button)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(billno_label)
                            .addComponent(booking_id_Label)
                            .addComponent(tax2_rate_label))
                        .addGap(18, 18, 18)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tax1_rate_label)
                            .addComponent(hall_id_label2)
                            .addComponent(advance_Recv_label))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(link_name_label)
                            .addComponent(bill_id_label))
                        .addGap(7, 7, 7)
                        .addComponent(tax3_rate_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateFinalReport_btn)
                        .addGap(44, 44, 44))
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(basic1_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(basic2_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(slot_flag_label)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalAmtAdjusted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel54)
                                .addComponent(cancel))
                            .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel48)
                                .addComponent(Tot_Bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(dueamnt_label))
                    .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox1)
                        .addComponent(refund_bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel451.setForeground(Color.BLUE);
        mno_label.setForeground(Color.BLUE);
        guest_name_label.setForeground(Color.BLUE);
        Hall_N_Label.setForeground(Color.BLUE);
        mem_name_label.setForeground(Color.BLUE);
        Biiling_name_label.setForeground(Color.BLUE);
        bill_id_label.setVisible(false);
        link_name_label.setVisible(false);
        Date_label.setForeground(Color.BLUE);
        advance_Recv_label.setVisible(false);
        basic2_label.setVisible(false);
        basic1_label.setVisible(false);
        slot_flag_label.setVisible(false);
        //tax1_rate_label.setVisible(false);
        tax1_rate_label.setVisible(false);
        //tax2_rate_label.setVisible(false);
        tax2_rate_label.setVisible(false);
        //tax3_rate_label.setVisible(false);
        tax3_rate_label.setVisible(false);
        checkin_id_label.setVisible(false);
        Date_label.setForeground(Color.BLUE);
        tax1_id_label.setVisible(false);
        tax2_id_label.setVisible(false);
        tax3_id_label.setVisible(false);
        hall_id_label2.setVisible(false);
        dueamnt_label.setVisible(false);
        hall_serv_chrg_label.setEditable(false);
        hall_serv_chrg_label.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tot_chrge.setEditable(false);
        tot_chrge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Tot_Bal_amt_text.setEditable(false);
        Tot_Bal_amt_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        refund_bal_amt_text.setEditable(false);
        refund_bal_amt_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalAmtAdjusted.setEditable(false);
        totalAmtAdjusted.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Adjust_billedAmt_check.setVisible(false);
        booking_id_Label.setVisible(false);
        TotAdvRecv_Lable.setVisible(false);
        billed_label.setVisible(false);
        billno_label.setVisible(false);
        tot_label.setEditable(false);
        tot_label.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        unadjustTotal_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        unadjustTotal_text.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bill_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    String MemberName;
    String MemberNo;
    String GuestName;
    String SlotTimings;
    String Slot_Booked;
    String BillingName;
    String Id_Card_Details;
    String HallName;
    String HallId;
    String advanceId;
    Double Charges;
    Double TotalAmount;
    Double Tax_total;
    Double Advance_Recv;
    Double BalAMT;
    String BookingID;
    String Tax1;
    String Tax2;
    String Tax3;
    String Member_ID;
    int MemberFlag;
    int Active;
    String Reciept_No;
    Date Booking_Date;
    String Booking_Date_Str;
    String Hall_Name_ID;
    Double Tax1_rate;
    Double Tax2_rate;
    Double Tax3_rate;
    Double Hall_Serv_Chrg;
    String Hall_serv_ID;
    int Basic1;
    int Basic2;
    int Slot_Flag;
    String Bill_ID;
    String Customer;
    int Paid;
    String Narrations;
    String Check_in_ID;
    String Booking_seq_no;
    
    String Customer_ID;
    String TRANSREF;
    String NARRATION;
    Double Tax1_AMT;
    Double Tax2_AMT;
    Double Tax3_AMT;
    
    Double DueAmt;
    String Rfd_Voucher_No;
    
    String Tax1_Accnt;
    String Tax2_Accnt;
    String Tax3_accnt;
    List<Object> Tax_Accnt_List;
    List<Double> Tax_Amt_List;
    String TID;
    String Advance_Acct_ID ;
    String Revenue_Acct_ID;
    String UserAccnt;
    String booking_id;
    String Gst;
    Double MinCheckInAmt = 0.00;
    
     List<Object> AdvRecptAdjusted = new ArrayList<Object>();  
     List<Double> BalAmtAdjusted = new ArrayList<Double>();
     List<Double> AdjAmtList = new ArrayList<Double>();
     String AdvanceRecieptNo=null;         
     
     Double Discount_Amt;
     int DISC_FLAG;
     
     
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(Gname_Combo.getSelectedIndex()!=-1){
            if(id_detail.getText()!=null && id_detail.getText().trim().length()>0){
           
                Date d = new Date();
                String x1 = Formats.DATE.formatValue(d);
                try {
                    d = (Date) Formats.TIMESTAMP.parseValue(x1);
                } catch (BasicException ex) {
                    Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                System.out.println("todays"+d);
                
                
                try {                       
                          Booking_Date = (Date) Formats.TIMESTAMP.parseValue(booked_date_label.getText());
                   } catch (BasicException ex) {
                                 Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                 }
                System.out.println("booked:  "+Booking_Date);
                if(Booking_Date.equals(d))
                
                {
                
                if(MinCheckInAmt<=Advance_Recv)  {
                
                
                
               int x = JOptionPane.showConfirmDialog(main_panel, "Do You Want to Check In..?? " , "Check in Menu " , JOptionPane.YES_NO_OPTION);
                 if( x  == JOptionPane.YES_OPTION){
        
                       Member_ID =  member_ID_label.getText();
                       advanceId = advance_id_label.getText();
                       
                       MemberNo = MemNo_label.getText();
                       SlotTimings = slot_label.getText();
                       HallName = hallname_label.getText();
                       TotalAmount = Double.parseDouble(totC_label.getText());
                       Advance_Recv = Double.parseDouble(AdvRec_label.getText());
                       Tax1 = tax1_label.getText();
                       Tax2 = tax2_label.getText();
                       Tax3 = tax3_label.getText();
                       BookingID = booking_id_label.getText();
                       
                       try {                       
                          Booking_Date = (Date) Formats.TIMESTAMP.parseValue(booked_date_label.getText());
                          } catch (BasicException ex) {
                                 Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                       Booking_Date_Str = booked_date_label.getText();
                       if(BillName.getText()!=null && BillName.getText().trim().length()>0){
                           BillingName = BillName.getText();
                       }else{
                           if(MemberFlag ==1){
                               BillingName = MemberName;
                           }
                           else{
                               BillingName = GuestName;
                           }
                       }
                       
                       Id_Card_Details = id_detail.getText();
                       
                       Active=1;
                       Hall_Name_ID = hall_id_label.getText();
                       
                       
                    Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {
                       
                     
                         
                           
                           int copy_hall_to_hall_cancel_request  =  new PreparedSentence(m_App.getSession(), "INSERT INTO hall_check_in (ID , HALLNAME , MEMBERNAME , MEMBERNO , GUESTNAME ,SLOTBOOKED , ID_CARD , BILL_NAME , ADVNCE_RECV , TOT_AMT  , RECIEPT_NO ,  ADVNCE_ID , CRBY , CRDATE , CRHOST , TAX1 , TAX2 , TAX3 , ACTIVE , BOOKED_DATE , BOOKING_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING ,Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE ,  Datas.STRING , Datas.STRING ,  Datas.STRING ,Datas.TIMESTAMP ,  Datas.STRING  , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.INT , Datas.TIMESTAMP , Datas.STRING})).exec
                                                                       (new Object[]{UUID.randomUUID().toString() , Hall_Name_ID , MemberName , MemberNo , GuestName , SlotTimings , Id_Card_Details , BillingName , Advance_Recv , TotalAmount , Reciept_No , advanceId ,   m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Tax1 , Tax2  , Tax3 , Active , Booking_Date, BookingID}); 

                           
                         
                          
                           
                         
                                int Adv_pmnt_table  =  new PreparedSentence(m_App.getSession(), "UPDATE hall_advance_payment  SET CHK_IN_FLAG=1  WHERE ID =  ?"
                                                          , new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec
                                                           (new Object[]{  advanceId  });

                                
                                int booking_table  =  new PreparedSentence(m_App.getSession(), "UPDATE hall_booked_details  SET CHK_IN_FLAG=1  WHERE ID =  ?"
                                                          , new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec
                                                           (new Object[]{  BookingID  });

                         
                            return null;
                          }
                         };
                   
               
               
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Check In Done Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    reset();
                                    Gname_Combo.setSelectedIndex(-1);
                                      loaddata();

                                } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                                }

                       
            
            
            
                       }
                 
                 
                 
              }
                
              else{
                  
               JOptionPane.showMessageDialog(this, (" No Sufficient Balance. \n  Minimum Amount for Check_In is : "+decimalFormat.format(MinCheckInAmt)+ ". " ) , "Error Message", JOptionPane.ERROR_MESSAGE);
                  
                  
              }
                
                
                
                
                }
                else{
                    
                    
                     JOptionPane.showMessageDialog(this, "You Cannot CheckedIn as the checkIn date is different..!   \n \n In case if u want to book for different date Please book again..!!  ", " Warning ", JOptionPane.WARNING_MESSAGE);
                    
                    
                }
                
                
                
                 
                 
            }
            else{
                JOptionPane.showMessageDialog(this, "Enter Id Card Details..!! ", " Error ", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else{
              JOptionPane.showMessageDialog(this, "Select Name First ..!! ", " Error ", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        
        reset();
        Gname_Combo.setSelectedIndex(-1);
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_CancelActionPerformed

    private void memItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memItemStateChanged
        try {
            loaddata();
            reset();//by pratima
        } catch (BasicException ex) {
            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_memItemStateChanged

    private void nonmemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nonmemItemStateChanged
      try {
            loaddata();
            reset();
        } catch (BasicException ex) {
            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nonmemItemStateChanged

    int flag;
    
    
    
    
    private void Gname_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Gname_ComboItemStateChanged
        //added by pratima 
        reset();
       
        RnoComboBox.setVisible(false);
          
        if(Gname_Combo.getSelectedIndex()==-1){
             RnoComboBox.setVisible(false);
              
         }
        //ended by pratima
        if(Gname_Combo.getSelectedIndex()!=-1){
            //added by pratima
           
            //ended by pratima
              if(mem.isSelected()){
                
                String Name  = Gname_Combo.getSelectedItem().toString();
                flag = 1;
               
                try {
                    CheckInTable_Model = CheckInTableModel.LoadHallCheckInDetail(m_App, Name , flag);
                } catch (BasicException ex) {
                    Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                String Name  = Gname_Combo.getSelectedItem().toString(); 
                flag = 2;
                try {
                    CheckInTable_Model = CheckInTableModel.LoadHallCheckInDetail(m_App, Name , flag);
                } catch (BasicException ex) {
                    Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            HallDetailList =  (List<CheckInTableModel.Hall_AdvInfo>) CheckInTable_Model.getHallPath();
            
             //by pratima :code to display all reciept at same member name 
            
             
             for(int i=0;i<HallDetailList.size();i++)
             {
                Recpt_list.add(HallDetailList.get(i).getRECIEPT_NO()) ;
             }
             Recptlist_model= new ComboBoxValModel(Recpt_list);
              
             RnoComboBox.setModel(Recptlist_model);
             RnoComboBox.setSelectedIndex(-1);
              
             if( HallDetailList.size()>1){
                  
                 RnoComboBox.setVisible(true);
                 jLabelRno.setText("RecieptNo.");
                 jLabelRno.setVisible(true);
                  
             }
             else{
                  
                 RnoComboBox.setVisible(false);
                 jLabelRno.setVisible(false);
              //ended By Pratima
            CheckInTableModel.Hall_AdvInfo editData = (CheckInTableModel.Hall_AdvInfo) ((CheckInTableModel.Hall_AdvInfo)HallDetailList.get(0)); 
            
            MemNo_label.setText(editData.getMEMBER_NO());
            slot_label.setText(editData.getSLOT_TIMMINGS());
            hallname_label.setText(editData.getHALLNAME());
            totC_label.setText(decimalFormat.format(editData.getTOTAL_AMOUNT()));
            AdvRec_label.setText(decimalFormat.format(editData.getADVANCE_RECV()));
            Advance_Recv = editData.getADVANCE_RECV();
            BookingID = editData.getBOOKING_ID();
            booking_id_label.setText(BookingID);
            
            Double Tot_amt = editData.getTOTAL_AMOUNT();
            Double Advnce_Recv = editData.getADVANCE_RECV();
            MemberFlag = editData.getMEMBER_FLAG();
            Double Due_amt=0.00;
            if(Advnce_Recv>=Tot_amt){
                Due_amt = (Advnce_Recv-Tot_amt);
                jLabel33.setText("Total Amount Refundable :");
            }
            else{
                Due_amt = (Tot_amt-Advnce_Recv);
                jLabel33.setText("Total Amount Payable :");
            }
          
            hall_id_label.setText(editData.getHallName_ID());
            due_label.setText(decimalFormat.format(Due_amt));
            
           
            
            try {
                Booking_Date = (Date) Formats.DATE.parseValue(editData.getCHECK_IN_DATE());
            } catch (BasicException ex) {
                Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String Temp_D = Formats.DATE.formatValue(Booking_Date);
            booked_date_label.setText(Temp_D);
            
            advance_id_label.setText(editData.getADVANCE_ID());
            member_ID_label.setText(editData.getMEMBER_ID());
            tax1_label.setText(editData.getTAX1());
            tax2_label.setText(editData.getTAX2());
            tax3_label.setText(editData.getTAX3());
            GuestName = editData.getGuestName();
            MemberName = editData.getMEMBERNAME();
            Reciept_No = editData.getRECIEPT_NO();
            slot_panel.setVisible(true);
            tariff_panel.setVisible(true); 
            hallname_label.setVisible(true);
            
            
            String Advance_Perc = editData.getADVANCE_PERC();
            String Temp_array[] = Advance_Perc.split("-");
            Double Bkng_perc = Double.parseDouble(Temp_array[0]);
            Double Check_in_perc = Double.parseDouble(Temp_array[1]);
            Double Tot_Perc = Bkng_perc + Check_in_perc;
            
            
             MinCheckInAmt = 0.00;
             MinCheckInAmt = ((Tot_Perc*Tot_amt)/100);
            
            System.out.println(MinCheckInAmt);
             }
        }
       
        else{
            slot_panel.setVisible(false);
            tariff_panel.setVisible(false);
            hallname_label.setVisible(false);
             
            RnoComboBox.setVisible(false);
            jLabelRno.setVisible(false);
        }

        
    }//GEN-LAST:event_Gname_ComboItemStateChanged

    private void chk_out_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_out_btnActionPerformed
                       
                 
        if(jTable1.getSelectedRow()!=-1){
              if(jTable1.getSelectedRow()<Hall_CheckIn_Detail.getSize()){
                  try {
                      int row = jTable1.getSelectedRow();
                      
                      HallCheckInTable.Hall_CheckIn_Info showdata = Hall_CheckIn_Detail.getHallList().get(row);
                      
                      mno_label.setText(showdata.getMEMBERNO());
                      mem_name_label.setText(showdata.getMEMBERNAME());
                      Biiling_name_label.setText(showdata.getBILL_NAME());
                      guest_name_label.setText(showdata.getGUEST_N());
                      Date_label.setText(Formats.DATE.formatValue(showdata.getCHECK_IN_DATE()));
                      slot_time_label.setText(showdata.getSLOT_BOOKED());
                      //  strictApprove1 = showdata.getGst();
//                strictApprove1=GR_CheckIn_Detail.getCustomerID1(m_App, showdata.getGst());
//                  System.out.println("gstnumberis::::::::"+strictApprove1);
//                jTextField1.setText(strictApprove1);
//  jTextField1.setText(showdata.getGst());
refund_bal_amt_text.setText("0.00");

rate.setText(decimalFormat.format(showdata.getCHARGES()));
Hall_N_Label.setText(showdata.getHALLNAME());
//   advnce_recv_Text.setText(decimalFormat.format(showdata.getADV_RECV()));
TotAdvRecv_Lable.setText(decimalFormat.format(showdata.getADV_RECV()));
billed_label.setText(""+showdata.getBILLED());
billno_label.setText(showdata.getBILLNO());

HallName = showdata.getHALLNAME();
Member_ID = showdata.getCUSTOMERID();



charges.setText(decimalFormat.format(showdata.getCHARGES()));
Discount_Amt_label.setText(decimalFormat.format(showdata.getDiscount()));

booking_seq_no_label.setText(showdata.getBOOKING_SEQ_NO());

booking_id_Label.setText(showdata.getBooking_ID());
Reciept_No = showdata.getRECIEPT_NO();
booking_id = booking_id_Label.getText();

Basic1 = showdata.getBASIC1();
Basic2 = showdata.getBASIC2();

DISC_FLAG = showdata.getDISC_FLAG();

Double TaxRate1;
Double TaxRate2;
Double TaxRate3;

if(showdata.getTAX1_RATE()!=null){
    
    TaxRate1 = showdata.getTAX1_RATE();
    tax1_rate_label.setText(""+showdata.getTAX1_RATE());
    
}
else{
    
    TaxRate1 = 0.00;
    tax1_rate_label.setText(""+TaxRate1);
}


if(showdata.getTAX2_RATE()!=null){
    
    TaxRate2 = showdata.getTAX2_RATE();
    tax2_rate_label.setText(""+showdata.getTAX2_RATE());
}
else{
    TaxRate2 = 0.00;
    tax2_rate_label.setText(""+TaxRate2);
    
}


if(showdata.getTAX3_RATE()!=null){
    
    TaxRate3 = showdata.getTAX3_RATE();
    tax3_rate_label.setText(""+showdata.getTAX3_RATE());
    
}
else{
    
    TaxRate3 = 0.00;
    tax3_rate_label.setText(""+TaxRate3);
    tax3_id_label.setText("");
}

Double Discount = Double.parseDouble(Discount_Amt_label.getText());
Double Tot_Charge = Double.parseDouble(charges.getText());
Tot_Charge = Tot_Charge - Discount;
Double Grand_Total = Double.parseDouble(charges.getText());
Grand_Total = Grand_Total - Discount;

Discount_Amt_label_Total.setText(decimalFormat.format(Tot_Charge));


// FOR DISPLAYING AND CALCULATION OF TAX
if(showdata.getTAX1_id()!=null  && showdata.getTAX1_id().length()>0){
    try {
        TaxCategoryInfo tax1= m_dlSales.getTaxCategoryByid(showdata.getTAX1_id());
        
        
        tax1_lebel.setText(tax1.getName());
        Tax_rate1_label.setText((decimalFormat.format(TaxRate1*100))+" %");
        tax1_amt_label.setText(decimalFormat.format(TaxRate1*Tot_Charge));
        Double tax1_r = TaxRate1*Tot_Charge;
        tax1_id_label.setText(tax1.getAccount());
        Tax_Basic_Label1.setText("(B)");
        Grand_Total = Grand_Total + tax1_r;
    } catch (BasicException ex) {
        Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
else{
    Tax_Basic_Label1.setText("");
    tax1_lebel.setText("----");
    Tax_rate1_label.setText((decimalFormat.format(0.00*100))+" %");
    tax1_amt_label.setText(decimalFormat.format(0.00*Tot_Charge));
}




if(showdata.getTAX2_id()!=null && showdata.getTAX2_id().length()>0){
    try {
        
        if(Basic1==1){
            TaxCategoryInfo tax2= m_dlSales.getTaxCategoryByid(showdata.getTAX2_id());
            tax2_Label.setText(tax2.getName());
            Tax_rate2_label.setText((decimalFormat.format(TaxRate2*100))+" %");
            tax2_amt_label.setText(decimalFormat.format(TaxRate2*Tot_Charge));
            Double tax2_r = TaxRate2*Tot_Charge;
            tax2_id_label.setText(tax2.getAccount());
            Tax_Basic_Label2.setText("(B)");
            Grand_Total = Grand_Total + tax2_r;
        }
        else{
            TaxCategoryInfo tax2= m_dlSales.getTaxCategoryByid(showdata.getTAX2_id());
            tax2_Label.setText(tax2.getName());
            Tax_rate2_label.setText((decimalFormat.format(TaxRate2*100))+" %");
            tax2_amt_label.setText(decimalFormat.format(TaxRate2*Grand_Total));
            Double tax2_r = TaxRate2*Grand_Total;
            tax2_id_label.setText(tax2.getAccount());
            Tax_Basic_Label2.setText("(C)");
            Grand_Total = Grand_Total + tax2_r;
        }
        
        
    } catch (BasicException ex) {
        Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
else{
    
    
    Tax_Basic_Label2.setText("");
    tax2_Label.setText("----");
    Tax_rate2_label.setText((decimalFormat.format(0.00*100))+" %");
    tax2_amt_label.setText(decimalFormat.format(0.00*Tot_Charge));
    tax2_id_label.setText("");
    
}




if(showdata.getTAX3_id()!=null && showdata.getTAX3_id().length()>0){
    try {
        
        if(Basic2==1){
            
            TaxCategoryInfo tax3= m_dlSales.getTaxCategoryByid(showdata.getTAX3_id());
            tax3_Label.setText(tax3.getName());
            Tax_rate3_label.setText((decimalFormat.format(TaxRate3*100))+" %");
            tax3_amt_label.setText(decimalFormat.format(TaxRate3*Tot_Charge));
            Double tax3_r = TaxRate3*Tot_Charge;
            tax3_id_label.setText(tax3.getAccount());
            Tax_Basic_Label3.setText("(B)");
            Grand_Total = Grand_Total + tax3_r;
            
        }
        else{
            
            TaxCategoryInfo tax3= m_dlSales.getTaxCategoryByid(showdata.getTAX3_id());
            tax3_Label.setText(tax3.getName());
            Tax_rate3_label.setText((decimalFormat.format(TaxRate3*100))+" %");
            tax3_amt_label.setText(decimalFormat.format(TaxRate3*Grand_Total));
            Double tax3_r = TaxRate3*Grand_Total;
            tax3_id_label.setText(tax3.getAccount());
            Tax_Basic_Label3.setText("(C)");
            Grand_Total = Grand_Total + tax3_r;
        }
        
    } catch (BasicException ex) {
        Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
else{
    
    Tax_Basic_Label3.setText("");
    tax3_Label.setText("----");
    Tax_rate3_label.setText((decimalFormat.format(0.00*100))+" %");
    tax3_amt_label.setText(decimalFormat.format(0.00*Tot_Charge));
    tax3_id_label.setText("");
    
}


hall_id_label2.setText(showdata.getHall_ID());
///////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////

// tax_rate.setText(""+(showdata.getTAX1_RATE()+showdata.getTAX2_RATE()+showdata.getTAX3_RATE()));

// ---------------------------------------------------------------------------------------------------------------
totalAmtAdjusted.setText("0.00");
Adjust_billedAmt_check.setSelected(false);
totalAmtAdjusted.setText("0.00");
jCheckBox1.setSelected(false);


String Cust_n = HS.getCust_link_name(m_App, "TEMP");

if(Cust_n!="null"){
    Check_in_ID=showdata.getCHECK_IN_Id();
    try{
        //  HS = HallService.load_Curr_Bills(m_App, "temp" ,Member_ID ,HallName  );//commented by pratima
        
        HS = HallService.load_Curr_Bills(m_App, "temp" ,Member_ID ,HallName,Check_in_ID );//added by pratima
    }
    catch (BasicException ex) {
        Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
    }
    BillInfoList = (List<HallService.BillInfo>) HS.getBillInfo_Path();
    if(BillInfoList==null){
        BillInfoList = new ArrayList<HallService.BillInfo>();
    }
    
    ShowHallServiceTableModel(HS);
    
}


// ------------------------------------------------------------------------------- METHOD TO SHOW ADVANCE DETAILS

if(Cust_n!="null"){
    
    try{
        AdvanceAdjustTable_model = AdvanceAdjustTableModel.load_AdvanceList_Hall(m_App , booking_id);
    }
    catch (BasicException ex) {
        Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
    }
    AdvanceInfoList = (List<AdvanceAdjustTableModel.AdvanceInfo>) AdvanceAdjustTable_model.getAdvanceList();
    if(AdvanceInfoList==null){
        AdvanceInfoList = new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
    }
    
    ShowAdvanceInfo(AdvanceAdjustTable_model);
    
}

tot_label.setText(decimalFormat.format(Grand_Total));
tot_chrge.setText(decimalFormat.format(Grand_Total));



hall_serv_chrg_label.setText("0.00");
basic1_label.setText(""+showdata.getBASIC1());
basic2_label.setText(""+showdata.getBASIC2());
slot_flag_label.setText(""+showdata.getSLOT_FLAG());

advance_Recv_label.setText(showdata.getADVANCE_ID());
Id_Card_Details = showdata.getID_CARD();

checkin_id_label.setText(showdata.getCHECK_IN_Id());

Tot_Bal_amt_text.setText(decimalFormat.format(showdata.getBal_AMt()));

//               strictApprove1=GR_CheckIn_Detail.getCustomerID1(m_App, showdata.getGst());
//               jTextField1.setText(strictApprove1);              
//                 strictApprove1=showdata.getGst();
String id=null;
Object obj = new StaticSentence(m_App.getSession(), "SELECT value FROM generaltable WHERE name=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find("Statutory Details");
if(obj!=null){
    id = obj.toString();
    // String id= showdata.getGst();
    System.out.println("gstnumber id::::::"+id);
    //strictApprove1=   dlSales.getPeopleListByName7(id);
    //System.out.println("gstnumber::::::"+strictApprove1);
    jTextField1.setText(id);
}
int sf = showdata.getSLOT_FLAG();
//                  strictApprove1=GR_CheckIn_Detail.getCustomerID1(m_App, showdata.getGst());
//                  System.out.println("gstnumberis::::::::"+strictApprove1);
//                jTextField1.setText(strictApprove1);
if(sf==1){
    slot_book_label.setText("( Hourly Booked )");
}
else if(sf==2){
    slot_book_label.setText("( Half Day )");
}
else{
    slot_book_label.setText("( Full Day )");
}



if(showdata.getGUEST_N()!=null ){
    guest_name_label.setText(showdata.getGUEST_N());
    guest_name_label.setVisible(true);
    jLabel14.setVisible(true);
}
else{
    guest_name_label.setVisible(false);
    jLabel14.setVisible(false);
}




Double tot_Amt =showdata.getTOT_AMT();
Double Advance_Recv = showdata.getADV_RECV();
//                 strictApprove1 = showdata.getGst();
//                  jTextField1.setText(strictApprove1);
if(tot_Amt>=showdata.getADV_RECV()){
    Double AmountPayable = (tot_Amt)-(Advance_Recv);
    dueamnt_label.setText(decimalFormat.format(AmountPayable));
    dueamnt_label.setForeground(Color.red);
    
}
else{
    Double AmountRefundable = (Advance_Recv-tot_Amt);
    dueamnt_label.setText(decimalFormat.format(AmountRefundable));
    dueamnt_label.setForeground(Color.GREEN);
    
    
}

if(showdata.getBILLED()==0){
    createbill_buttn.setEnabled(true);
    AddDiscount_btn.setEnabled(true);
    adjustBill_button.setEnabled(false);
    generateFinalReport_btn.setEnabled(false);
}
else if(showdata.getBILLED()==1){
    createbill_buttn.setEnabled(false);
    AddDiscount_btn.setEnabled(false);
    adjustBill_button.setEnabled(true);
    generateFinalReport_btn.setEnabled(false);
}
else{
    createbill_buttn.setEnabled(false);
    AddDiscount_btn.setEnabled(false);
    adjustBill_button.setEnabled(false);
    generateFinalReport_btn.setEnabled(true);
    
}



bill_panel.setVisible(true);
main_panel.setVisible(false);
setAdjustedValue(Booking_seq_no); 
                  } catch (BasicException ex) {
                      Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              
              
          }
          
    }//GEN-LAST:event_chk_out_btnActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        main_panel.setVisible(true);
        bill_panel.setVisible(false);
    }//GEN-LAST:event_cancelActionPerformed
   List<Object> billList;
  
   Double AdjustAmt=0.00;
    Double AdjustAmt_HALL=0.00;
    String Adjust_reference="";
    List<Object> HALL_billList;
    String Adjust_reference_HALL="";
    
    
    private void adjustBill_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjustBill_buttonActionPerformed
         if(Double.parseDouble(hall_serv_chrg_label.getText())>0){
             
             Double RScharge = Double.parseDouble(hall_serv_chrg_label.getText());
             Double AdvanceAdjust = Double.parseDouble(totalAmtAdjusted.getText());
             AdjustAmt_HALL=0.00;
             AdjustAmt=0.00;
             Adjust_reference="";
             Adjust_reference_HALL="";
             
             if(RScharge<=AdvanceAdjust){
             
             
             int Row_Count = HS.getTableModel2().getRowCount();
             billList = new ArrayList<Object>();
             HALL_billList = new ArrayList();
             Booking_seq_no = booking_seq_no_label.getText();
             Double AdvanceAmt;
             Double BalAmt;
             int RoomBill=0;
             
       // FOR ROOM SERVICE BILL NO  LIST ----------------------------------------------------------------------------
             
             
             for(int i=0;i<Row_Count;i++){
                 int row = i;
               //  System.out.print(GRS.getTableModel2().getValueAt(row, 4).toString());
                 
              //   Boolean ticked  = Boolean.valueOf(HS.getTableModel2().getValueAt(row, 4).toString());
               Double AmountTemp =  Double.valueOf(HS.getTableModel2().getValueAt(row, 5).toString());    
                 
                 
                 if(AmountTemp>0){
                    String temp =  String.valueOf(HS.getTableModel2().getValueAt(row, 2).toString());
                    if(temp.equals("HALL")){
                        RoomBill = 1;
                        String BillNo =  String.valueOf(HS.getTableModel2().getValueAt(row, 1).toString());
                        Double d =  Double.valueOf(HS.getTableModel2().getValueAt(row, 5).toString());
                        Adjust_reference_HALL = Adjust_reference_HALL +BillNo+"#"+decimalFormat.format(d)+"-";
                        HALL_billList.add(BillNo);
                        AdjustAmt_HALL=AdjustAmt_HALL+d;  
                        
                        
                    }
                    else{
                        String BillNo =  String.valueOf(HS.getTableModel2().getValueAt(row, 1).toString());
                        Double d =  Double.valueOf(HS.getTableModel2().getValueAt(row, 5).toString());
                        Adjust_reference = Adjust_reference +BillNo+"#"+decimalFormat.format(d)+"-";
                        billList.add(BillNo);
                        AdjustAmt=AdjustAmt+d;  
                        
                        
                    }
                    
               }
             } 
              
             // FOR ADVANCE RECIEPT.----------------------------------------------------------------------------------
             
             int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
              for(int j=0;j<rowCount2;j++){
                 int row1 = j; 
                 Boolean ticked1  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 3).toString()); 
                 if(ticked1){
                    AdvanceRecieptNo = String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 1).toString());
                 }
              }   
              
              
              // ACCOUNTING ENTRIES ----------------------------------------------------------------------------------
              
              
              if(billList.size()>0 && AdvanceRecieptNo!=null && AdvanceRecieptNo.trim().length()>0){
                  
                  
                  
                  
                  
              }
              
              
              
            // ENTRIES FOR ADJUSTEMENT OF ROOM BILLS....-------------------------------------------------------------------------------------------------  
              
             if(HALL_billList.size()>0 && Adjust_reference_HALL.trim().length()>0 && Adjust_reference_HALL!=null )  {
                 
                 
                  Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {
                       
                           for(int i=0;i<HALL_billList.size();i++){
                              String BillNo =   HALL_billList.get(i).toString();
                             
                              
                              Boolean ticked2  = Boolean.valueOf(HS.getTableModel2().getValueAt(i, 4).toString());
                              if(ticked2){
                                  
                                   int x = new StaticSentence(m_App.getSession(), "UPDATE hall_bill SET paid=1 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {BillNo});
                            
                                   int checkin_update = new StaticSentence(m_App.getSession(), "UPDATE hall_check_in SET BILLED=2 WHERE BILLNO=?"
                                                                        , new SerializerWriteBasic(new Datas[] { Datas.STRING })).exec(new Object[] {BillNo });
                            
                                   
                                  
                              }
                              
                             
                              int x1 = new StaticSentence(m_App.getSession(), "UPDATE hall_bill SET RECIEPT=? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {AdvanceRecieptNo , BillNo});
                              
                              int UpdateBalAmount = new StaticSentence(m_App.getSession(), "UPDATE hall_bill SET BALANCEAMOUNT=BALANCEAMOUNT-? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_HALL,BillNo});
                            
                           
                           
                           }
                           
                             
                          
                           
                           
//                            int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_HALL , AdvanceRecieptNo });
//                            
//                           
//                            int Update_Advce_GuestRoom2 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET adjust_ref= ?  WHERE RECIEPT_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference_HALL , AdvanceRecieptNo });
//                            
//                           
//                            
//                            
//                            int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_HALL , AdvanceRecieptNo });
//                            
//                            
//                            
//                           int GuestRoom_AdvancePay = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET BAL_AMT=BAL_AMT-? WHERE BOOKING_SEQ_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_HALL , Booking_seq_no });
//                            
//                            
//                            
//                             
//                          // ADJUSTMENT IN ACCOUNTS ----------------------------------------------------------------------------------------------------------------
//                           
//                           
//                            int UPDATE_aCCOUNT1 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET balanceamount=balanceamount-?  WHERE TRANSNO=? AND TRANSTYPE='C'  "
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_HALL , AdvanceRecieptNo });
//                            
//                           
//                            int UPDATE_aCCOUNT2 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET PAYMENTREF= CONCAT(COALESCE(PAYMENTREF , ?))  WHERE TRANSNO=? AND TRANSTYPE='C'"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference_HALL , AdvanceRecieptNo });
//                            
                          
//added by pratima :above commented queries replaced to adjust multiple receipts at same bill
                            int GuestRoom_AdvancePay = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET BAL_AMT=BAL_AMT-? WHERE BOOKING_SEQ_NO=?"
                                 , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_HALL , Booking_seq_no });
              int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
              for(int j=0;j<rowCount2;j++){
                 int row11 = j; 
                 Boolean ticked11  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row11, 3).toString()); 
                 if(ticked11){
                     Double tempAmt=Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row11, 4).toString());
                     String tempReciept=String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row11, 1).toString());
                                        
                           int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {tempAmt , tempReciept });
                            
                           
                            int Update_Advce_GuestRoom2 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET adjust_ref= ?  WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference_HALL , tempReciept });
                            
                           
                            
                            
                            int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {tempAmt , tempReciept });
                            
                             
                          // ADJUSTMENT IN ACCOUNTS ----------------------------------------------------------------------------------------------------------------
                           
                           
                            int UPDATE_aCCOUNT1 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET balanceamount=balanceamount-?  WHERE TRANSNO=? AND TRANSTYPE='C'  "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {tempAmt , tempReciept });
                            
                           
                            int UPDATE_aCCOUNT2 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET PAYMENTREF= CONCAT(COALESCE(PAYMENTREF , ?)),ADJUSTED=?  WHERE TRANSNO=? AND TRANSTYPE='C'"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING ,Datas.INT, Datas.STRING })).exec(new Object[] {Adjust_reference_HALL ,1, tempReciept });
                                  
                 }}//for & if anded by pratima
                           
                           
                            
                            
                            return null;
                          }
                         };
                   
                  try {
                       t.execute();
                       JOptionPane.showMessageDialog(this, "Adjusted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                        
                      //  generateFinalReport_btn.setEnabled(true);
                        totalAmtAdjusted.setText("0.00");
                        LoaddataForTables();
                        
                        Double z1 = Double.parseDouble(Tot_Bal_amt_text.getText());
                        Tot_Bal_amt_text.setText(decimalFormat.format(z1-AdjustAmt_HALL));
                        loaddata();   
                         setAdjustedValue(Booking_seq_no)  ; 
                                    
                   } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                    } 
                 
                 
             }  
             else{
                 
             }
                 
            
             
            
             
             }
             else{
                  JOptionPane.showMessageDialog(this, "Bill Amount Exceeds the Advance amount ", "Warning", JOptionPane.WARNING_MESSAGE);
             }
             
             
             
         }
         else{
             
             JOptionPane.showMessageDialog(this, "Select Room Service bill to adjust", "Error", JOptionPane.ERROR_MESSAGE);
         }  
            
        
        
    }//GEN-LAST:event_adjustBill_buttonActionPerformed

    private void createbill_buttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createbill_buttnActionPerformed
      
     if(DISC_FLAG==1){  
       
       Double tot_Bill_temp  = Double.parseDouble(tot_label.getText());
       Double Tot_bal_amt_temp = Double.parseDouble(Tot_Bal_amt_text.getText());  
         
       if(tot_Bill_temp <= Tot_bal_amt_temp)  
         
       { 
       
       
       
        int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to Check Out ?? " , "Billing Menu " , JOptionPane.YES_NO_OPTION);
        if(bill == JOptionPane.YES_OPTION)
        {   
       
           
           
        
          Generate_Hall_Bill2();
          
         main_panel.setVisible(true);
         bill_panel.setVisible(false);
         
        
       }
        
        
        
        
        
        }
       
        else{
          JOptionPane.showMessageDialog(this, " No sufficient balance to check out..!!  \n \n Please pay remaining amount to Check-out ", " Warning ", JOptionPane.WARNING_MESSAGE);
        }
     
       
       }
       
       
        else{
            JOptionPane.showMessageDialog(this, " Please wait for discount request to be approved..!!  ", " Warning ", JOptionPane.WARNING_MESSAGE);
        }
     
     
     
    
     
     
     
     
        
    }//GEN-LAST:event_createbill_buttnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     String Bill =  JOptionPane.showInputDialog(jPanel1, "Please Enter Bill no. to print bill. " , "Bill Re-print" , JOptionPane.YES_NO_OPTION);
     String Billno = Bill.toUpperCase();
     if(Billno.length()>0 && Billno!=null){
          
         String avail = CheckInTable_Model.getHallBillID(m_App, Billno);
         if(avail.equals(Billno)){
              Launch_Bill(Billno); 
         }
         else{
            JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
         }
     }      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
//        if(jCheckBox1.isSelected()){
//
//            Double Refund_amt = Double.parseDouble(Tot_Bal_amt_text.getText())  ;
//            refund_bal_amt_text.setText(Refund_amt+"");
//            Tot_Bal_amt_text.setText("0.00");
//
//        }
//        else{
//
//            Double Old_Bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
//            Tot_Bal_amt_text.setText(decimalFormat.format(Old_Bal_amt+Double.parseDouble(refund_bal_amt_text.getText())));
//
//            refund_bal_amt_text.setText("0.00");
//        }
//added by pratima
 if(jCheckBox1.isSelected()){
    if(Adjust_billedAmt_check.isSelected()){
                    Double Refund_amt = Double.parseDouble(Tot_Bal_amt_text.getText())  ;
            refund_bal_amt_text.setText(Refund_amt+"");
            Tot_Bal_amt_text.setText("0.00");
     } else{ JOptionPane.showMessageDialog(this, "Please first create bill and adjust bill. ","Error", JOptionPane.OK_OPTION);
    }
 } else{

            Double Old_Bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
            Tot_Bal_amt_text.setText(decimalFormat.format(Old_Bal_amt+Double.parseDouble(refund_bal_amt_text.getText())));

            refund_bal_amt_text.setText("0.00");
        }
 //ended by pratima
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void Adjust_billedAmt_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Adjust_billedAmt_checkItemStateChanged
        if(Adjust_billedAmt_check.isSelected()){
            Double BiiledAmt = Double.parseDouble((tot_chrge.getText()));
            Double Total = Double.parseDouble((totalAmtAdjusted.getText()));

            Total = Total + BiiledAmt;
            tot_chrge.setText("0.00");
            totalAmtAdjusted.setText(decimalFormat.format(Total));

        }
        else{

            Double BiiledAmt = Double.parseDouble((tot_label.getText()));
            Double Total = Double.parseDouble((totalAmtAdjusted.getText()));

            Total = Total - BiiledAmt;

            tot_chrge.setText(decimalFormat.format(BiiledAmt));
            totalAmtAdjusted.setText(decimalFormat.format(Total));

        }

    }//GEN-LAST:event_Adjust_billedAmt_checkItemStateChanged

    
    private MyAbstractTableModel HallServTableModel;
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DecimalFormat dFormat = new DecimalFormat("#.##");
        try {
            int row = hallservicetable.getSelectedRow();

            if (row < 0 ) {
                JOptionPane.showMessageDialog(this, "Please select only one table", "Cannot insert", JOptionPane.OK_OPTION);
            }
            if (row >= 0) {
                
                
                Boolean ticked2  = Boolean.valueOf(HS.getTableModel2().getValueAt(row, 4).toString());
                if(!ticked2){
                

                int column = hallservicetable.getSelectedColumn();
                //  if(column==1){
                    try {
                        if (jTextField3.getText().length() > 0) {
                            boolean bool = Boolean.valueOf(HS.getTableModel2().getValueAt(row, 5).toString());
                            if (!bool) {
                                if (column == 5) {   //Arun
                                    Double Old_adjst_amt = Double.valueOf(HS.getTableModel2().getValueAt(row, 5).toString());
                                    Double OrgAmt = Double.valueOf(HS.getTableModel2().getValueAt(row, 3).toString());

                                    Double New_Adjst_amt = (Double.valueOf(jTextField3.getText()).doubleValue());

                                    Double Amount = New_Adjst_amt-Old_adjst_amt;

                                    if(New_Adjst_amt<OrgAmt){

                                        Double TotRoomServ_Charge = Double.parseDouble(hall_serv_chrg_label.getText());
                                        Double Total_AdjstAmt =  Double.parseDouble(totalAmtAdjusted.getText());

                                        // roomCharges.setText(decimalFormat.format(TotRoomServ_Charge - Amount));
                                        // totalAmtAdjusted.setText(decimalFormat.format(Total_AdjstAmt - Amount));

                                        HallServTableModel = HS.getTableModel2();
                                        //unadjustTotal_text.setText("0.00");
                                        HallServTableModel.settext(hall_serv_chrg_label ,totalAmtAdjusted,unadjustTotal_text );

                                        HallServTableModel.setValueAt(New_Adjst_amt, row, 5);
                                        //GRS.getTableModel2().setValueAt(rate, row, 2);
                                        hallservicetable.setModel(HallServTableModel);
                                        jTextField3.setText(null);
                                        //ShowBillDetails(HS);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(this, "Value Should be less than Org. Amt", "Error", JOptionPane.WARNING_MESSAGE);

                                    }
                                }
                            }

                        }

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid Number.Quantity Cannot be a decimal value", "Error", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Please Select a product", "Error", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                    }
                    
                    
                }
                else{
                      JOptionPane.showMessageDialog(this, "Amount fully selected. Cannot do partial payment", "Error", JOptionPane.WARNING_MESSAGE);
                }
                    
                    
                    
                
            }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Invalid Number", "Error", JOptionPane.OK_OPTION);
                e1.printStackTrace();
            }

               int x = jTable1.getSelectedRow();
            int y = jTable1.getSelectedColumn();

            if(y!=6){
                jTable1.setRowSelectionInterval(x-1, x-1);
                jTable1.setColumnSelectionInterval(y+1, y+1);

                jTable1.changeSelection(x-1, y+1 ,true, true);
            }
            else{
                jTable1.setRowSelectionInterval(x+1, x+1);
                jTable1.setColumnSelectionInterval(0, 0);
            }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jPanel6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6AncestorAdded

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      String Bill =  JOptionPane.showInputDialog(jPanel1, "Please Enter Bill no. to print bill. " , "Bill Re-print" , JOptionPane.YES_NO_OPTION);
     String Billno = Bill.toUpperCase();
     if(Billno.length()>0 && Billno!=null){
          
         String avail = CheckInTable_Model.getHallBillID(m_App, Billno);
         if(avail.equals(Billno)){
              Launch_Bill_Statement(Billno); 
         }
         else{
            JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
         }
     }      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void generateFinalReport_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateFinalReport_btnActionPerformed
       if( Adjust_billedAmt_check.isSelected()){
            Double NewUnadjustedAmount=0.00;
   int  Row_Count1 = HS.getTableModel2().getRowCount();    
   for(int i=0;i<Row_Count1;i++){
     int row = i;
     String temp =  String.valueOf(HS.getTableModel2().getValueAt(row, 2).toString());
     if(temp.equals("HALL")){
        Double AmountTemp =  Double.valueOf(HS.getTableModel2().getValueAt(row, 6).toString());   
        if(AmountTemp>0){  
            NewUnadjustedAmount=NewUnadjustedAmount+AmountTemp;
            
        } 
         
         
     }
     
   }
   
           
        int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to Generate Final Bill Statement ?? " , "Bill menu" , JOptionPane.YES_NO_OPTION);
       if(bill == JOptionPane.YES_OPTION)
        {   
           Double UnAdjusted_Amt = Double.parseDouble(totalAmtAdjusted.getText());
           Double TotBilledAmt = Double.parseDouble(tot_chrge.getText());
          // if(UnAdjusted_Amt<=0  ){
              if(NewUnadjustedAmount<=0  ){
          try {
                   GERERATE_FINAL_STATEMENT();
               } catch (BasicException ex) {
                   Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
               }
                      
                
                  main_panel.setVisible(true);
                  bill_panel.setVisible(false);


               try {
                      loaddata();
                      reset();
                  } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }
           }
           
           
           
           else{
               JOptionPane.showMessageDialog(this, "Still Unadjusted Amt..! \n Please Clear Balance..!!", "Error", JOptionPane.ERROR_MESSAGE);
           }
        
       }  
       }else{JOptionPane.showMessageDialog(this, "Please first adjust the bill .", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_generateFinalReport_btnActionPerformed

    private void createbill_buttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createbill_buttn1ActionPerformed
        Print_Bill_Proforma();
    }//GEN-LAST:event_createbill_buttn1ActionPerformed

    private void AddDiscount_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDiscount_btnActionPerformed
        int Perc_flag = 0;
        Double Discount_Amt = 0.00;
        
        Discount_MasterDialog dbillList = Discount_MasterDialog.getDialog(this, m_App,true);
        
        try {
            dbillList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Perc_flag = dbillList.getPerc_Flag();
        
        int Flag_x = dbillList.getFlag_X();
        if(Perc_flag!=6){
         
           if(Perc_flag==1){
               
               
               
               Double Perc = dbillList.getPerc_Amt();
               Double charge = Double.parseDouble(charges.getText());
               Discount_Amt = ((Perc*charge)/100);
               
               
               
           } 
           if(Perc_flag==0){
               
                Discount_Amt = dbillList.getFixed_amt();
               
               
               
               
           }
            
            
           if(Flag_x==1){
               
           
            
           int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to give discount of  "+Discount_Amt+"/- on billed amount ?? " , "Discount Menu" , JOptionPane.YES_NO_OPTION);
           if(bill == JOptionPane.YES_OPTION) 
            
           {
               
               Double old_discount = Double.parseDouble(Discount_Amt_label.getText());
               Discount_Amt_label.setText(decimalFormat.format(Discount_Amt));
               
               Double tot_charge = Double.parseDouble(tot_chrge.getText());
               Double tot = Double.parseDouble(tot_label.getText());
               
               Double new_tot_charge = ((tot_charge + old_discount)-Discount_Amt);
               Double new_tot = ((tot + old_discount)-Discount_Amt);
               
               tot_chrge.setText(decimalFormat.format(new_tot_charge));
               tot_label.setText(decimalFormat.format(new_tot));
               
               String id = checkin_id_label.getText();
               
               
               try {
                   
                   
              new PreparedSentence(m_App.getSession()
                      , "UPDATE hall_check_in SET DISCOUNT=? , DISC_FLAG=0  WHERE ID=?"
                      , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE ,Datas.STRING })).exec(
                      new Object[]{Discount_Amt , id });
               
               
               JOptionPane.showMessageDialog(this, "Discount Requested for approval. Please  Wait for approval", "Success", JOptionPane.INFORMATION_MESSAGE); 
                loaddata();
                bill_panel.setVisible(false);
                main_panel.setVisible(true);
              
               } 
               
               catch (BasicException ex) {
                   Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                   new MessageInf(ex).show(new JFrame());
                   
                   
               }
            
            
            
           } 
            
            
           }     
            
            
        }
        else{
            
           JOptionPane.showMessageDialog(this, " Please set discount in masters", "Error", JOptionPane.WARNING_MESSAGE);  
            
        }
    }//GEN-LAST:event_AddDiscount_btnActionPerformed

    private void id_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_detailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_detailActionPerformed

    private void hallservicetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hallservicetableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_hallservicetableMouseClicked
    //added By pratima
    private void RnoComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RnoComboBoxItemStateChanged
        // TODO add your handling code here:
        if(RnoComboBox.getSelectedIndex()!=-1){
            
             String RecieptSelected=RnoComboBox.getSelectedItem().toString();
             int row=0;
                for(int i=0;i<HallDetailList.size();i++){
                  if(HallDetailList.get(i).getRECIEPT_NO().equals(RecieptSelected))
                  row= i; 
                }
            CheckInTableModel.Hall_AdvInfo editData = (CheckInTableModel.Hall_AdvInfo) ((CheckInTableModel.Hall_AdvInfo)HallDetailList.get(row));
            
            MemNo_label.setText(editData.getMEMBER_NO());
            slot_label.setText(editData.getSLOT_TIMMINGS());
            hallname_label.setText(editData.getHALLNAME());
            totC_label.setText(decimalFormat.format(editData.getTOTAL_AMOUNT()));
            AdvRec_label.setText(decimalFormat.format(editData.getADVANCE_RECV()));
            Advance_Recv = editData.getADVANCE_RECV();
            BookingID = editData.getBOOKING_ID();
            booking_id_label.setText(BookingID);
            
            Double Tot_amt = editData.getTOTAL_AMOUNT();
            Double Advnce_Recv = editData.getADVANCE_RECV();
            MemberFlag = editData.getMEMBER_FLAG();
            Double Due_amt=0.00;
            if(Advnce_Recv>=Tot_amt){
                Due_amt = (Advnce_Recv-Tot_amt);
                jLabel33.setText("Total Amount Refundable :");
            }
            else{
                Due_amt = (Tot_amt-Advnce_Recv);
                jLabel33.setText("Total Amount Payable :");
            }
          
            hall_id_label.setText(editData.getHallName_ID());
            due_label.setText(decimalFormat.format(Due_amt));
            
           
            
            try {
                Booking_Date = (Date) Formats.DATE.parseValue(editData.getCHECK_IN_DATE());
            } catch (BasicException ex) {
                Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String Temp_D = Formats.DATE.formatValue(Booking_Date);
            booked_date_label.setText(Temp_D);
            
            advance_id_label.setText(editData.getADVANCE_ID());
            member_ID_label.setText(editData.getMEMBER_ID());
            tax1_label.setText(editData.getTAX1());
            tax2_label.setText(editData.getTAX2());
            tax3_label.setText(editData.getTAX3());
            GuestName = editData.getGuestName();
            MemberName = editData.getMEMBERNAME();
            Reciept_No = editData.getRECIEPT_NO();
            slot_panel.setVisible(true);
            tariff_panel.setVisible(true); 
            hallname_label.setVisible(true);
            
            
            String Advance_Perc = editData.getADVANCE_PERC();
            String Temp_array[] = Advance_Perc.split("-");
            Double Bkng_perc = Double.parseDouble(Temp_array[0]);
            Double Check_in_perc = Double.parseDouble(Temp_array[1]);
            Double Tot_Perc = Bkng_perc + Check_in_perc;
            
            
             MinCheckInAmt = 0.00;
             MinCheckInAmt = ((Tot_Perc*Tot_amt)/100);
            
            System.out.println(MinCheckInAmt);
        } 
    }//GEN-LAST:event_RnoComboBoxItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    //ended By pratima
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddDiscount_btn;
    private javax.swing.JCheckBox Adjust_billedAmt_check;
    private javax.swing.JLabel AdvRec_label;
    private javax.swing.JLabel Biiling_name_label;
    private javax.swing.JTextField BillName;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel Date_label;
    private javax.swing.JLabel Discount_Amt_label;
    private javax.swing.JLabel Discount_Amt_label_Total;
    private javax.swing.JComboBox Gname_Combo;
    private javax.swing.JLabel Hall_N_Label;
    private javax.swing.JLabel MemNo_label;
    private javax.swing.JComboBox<String> RnoComboBox;
    private javax.swing.JLabel Tax_Basic_Label1;
    private javax.swing.JLabel Tax_Basic_Label2;
    private javax.swing.JLabel Tax_Basic_Label3;
    private javax.swing.JLabel Tax_rate1_label;
    private javax.swing.JLabel Tax_rate2_label;
    private javax.swing.JLabel Tax_rate3_label;
    private javax.swing.JLabel TotAdvRecv_Lable;
    private javax.swing.JTextField Tot_Bal_amt_text;
    private javax.swing.JButton adjustBill_button;
    private javax.swing.JLabel advance_Recv_label;
    private javax.swing.JLabel advance_id_label;
    private javax.swing.JTable advancedetailtable;
    private javax.swing.JLabel basic1_label;
    private javax.swing.JLabel basic2_label;
    private javax.swing.JLabel bill_id_label;
    private javax.swing.JPanel bill_panel;
    private javax.swing.JLabel billed_label;
    private javax.swing.JLabel billno_label;
    private javax.swing.JLabel booked_date_label;
    private javax.swing.JLabel booking_id_Label;
    private javax.swing.JLabel booking_id_label;
    private javax.swing.JLabel booking_seq_no_label;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel charges;
    private javax.swing.JLabel checkin_id_label;
    private javax.swing.JButton chk_out_btn;
    private javax.swing.JButton createbill_buttn;
    private javax.swing.JButton createbill_buttn1;
    private javax.swing.JLabel due_label;
    private javax.swing.JLabel dueamnt_label;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton generateFinalReport_btn;
    private javax.swing.JLabel guest_name_label;
    private javax.swing.JLabel hall_id_label;
    private javax.swing.JLabel hall_id_label2;
    private javax.swing.JLabel hall_name_label;
    private javax.swing.JTextField hall_serv_chrg_label;
    private javax.swing.JLabel hallname_label;
    private javax.swing.JTable hallservicetable;
    private javax.swing.JTextField id_detail;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel451;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelRno;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel link_name_label;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton mem;
    private javax.swing.JLabel memNo;
    private javax.swing.JLabel memNo2;
    private javax.swing.JLabel memNo3;
    private javax.swing.JLabel mem_name_label;
    private javax.swing.JLabel member_ID_label;
    private javax.swing.JLabel mno_label;
    private javax.swing.JRadioButton nonmem;
    private javax.swing.JLabel rate;
    private javax.swing.JTextField refund_bal_amt_text;
    private javax.swing.JButton save;
    private javax.swing.JLabel slot_book_label;
    private javax.swing.JLabel slot_flag_label;
    private javax.swing.JLabel slot_label;
    private javax.swing.JPanel slot_panel;
    private javax.swing.JLabel slot_time_label;
    private javax.swing.JPanel tariff_panel;
    private javax.swing.JLabel tax1_amt_label;
    private javax.swing.JLabel tax1_id_label;
    private javax.swing.JLabel tax1_label;
    private javax.swing.JLabel tax1_lebel;
    private javax.swing.JLabel tax1_rate_label;
    private javax.swing.JLabel tax2_Label;
    private javax.swing.JLabel tax2_amt_label;
    private javax.swing.JLabel tax2_id_label;
    private javax.swing.JLabel tax2_label;
    private javax.swing.JLabel tax2_rate_label;
    private javax.swing.JLabel tax3_Label;
    private javax.swing.JLabel tax3_amt_label;
    private javax.swing.JLabel tax3_id_label;
    private javax.swing.JLabel tax3_label;
    private javax.swing.JLabel tax3_rate_label;
    private javax.swing.JLabel totC_label;
    private javax.swing.JTextField tot_chrge;
    private javax.swing.JTextField tot_label;
    private javax.swing.JTextField totalAmtAdjusted;
    private javax.swing.JTextField unadjustTotal_text;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "";
    }

    public void activate() throws BasicException {
        main_panel.setVisible(true);
        bill_panel.setVisible(false);
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
        AdvanceRecv = (AdvanceRecieveTableModel) app.getBean("com.openbravo.pos.Booking.AdvanceRecieveTableModel");
        CheckInTable_Model = (CheckInTableModel) app.getBean("com.openbravo.pos.Booking.CheckInTableModel");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         
        HS = (HallService) app.getBean("com.openbravo.pos.Booking.HallService");
        buttonGoup();
    }

    public Object getBean() {
       return this;
    }
    
    
     public void loaddata() throws BasicException{
         
       jLabel5.setVisible(false);
        jTextField1.setVisible(false);
        if(mem.isSelected()){
          Mem_list = new ArrayList<Object>();
          Mem_list =  AdvanceRecv.getMemListFromHall(m_App);
          for(int i=0;i < Mem_list.size();i++){
              System.out.println("Member name is "+Mem_list.get(i));
          }
          Memlist_model = new ComboBoxValModel(Mem_list);
          Gname_Combo.setModel(Memlist_model);
          
          } 
        if(nonmem.isSelected()){
          Guest_list = new ArrayList<Object>();
          Guest_list =AdvanceRecv.getGuestListFromHall(m_App);
          GuestList_model = new ComboBoxValModel(Guest_list);
          Gname_Combo.setModel(GuestList_model);
        }
       
          Hall_CheckIn_Detail = HallCheckInTable.loadCheckIn_Data(m_App);
          showPanelInfo(Hall_CheckIn_Detail );
         
     }
     
      private void buttonGoup() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(mem);
        bg1.add(nonmem);
      }
    
      
      public void reset(){
          slot_panel.setVisible(false);
          tariff_panel.setVisible(false);
          hallname_label.setVisible(false);
          id_detail.setText(null);
          BillName.setText(null);
          //added by pratima
          jLabelRno.setVisible(false);
          RnoComboBox.setVisible(false);
          //ended by pratima
          Recpt_list = new ArrayList<String>();
          Adjust_billedAmt_check.setSelected(false);
      }
    
      public void showPanelInfo(HallCheckInTable hall_check_in_detail){
            jTable1.setModel(hall_check_in_detail.getTableModel());
          
      }
      
      
      
        //GET BILL SEQUENCE ID  
        private String getNextBillID() throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String createBy =  m_App.getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.BSERIES,SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE  AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createBy});
        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            billnum = obj[0].toString() + max.intValue();
            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createBy});
            return billnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
            return "";
        }
        }
        
        
        // FOR GENERATING BILL 
        public void Generate_Hall_Bill(){
                            
                        
                        Tax_Accnt_List = new ArrayList<Object>();
                        Tax_Amt_List = new ArrayList<Double>();
                        MemberNo = mno_label.getText();
                        MemberName = mem_name_label.getText();
                        BillingName = Biiling_name_label.getText();
                        HallId = hall_id_label2.getText();
                        Booking_Date_Str = booked_date_label.getText();
                        if(guest_name_label.getText()!=null && guest_name_label.getText().trim().length()>0){
                            GuestName = guest_name_label.getText();
                        }
                        else{
                            GuestName=null;
                        }
                        try {
                            Booking_Date = (Date) Formats.TIMESTAMP.parseValue(Date_label.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SlotTimings = slot_time_label.getText();
                        Slot_Booked = slot_book_label.getText();
                        Charges = Double.parseDouble(charges.getText());
                        System.out.println(tax2_rate_label.getText());
                        
                        if(tax1_rate_label.getText()!="null" ){
                              Tax1_rate = Double.parseDouble(tax1_rate_label.getText());
                        }
                        else{
                             Tax1_rate = 0.00;
                        }
                        if(tax2_rate_label.getText()!="null" ){
                              Tax2_rate = Double.parseDouble(tax2_rate_label.getText());
                        }
                        else{
                             Tax2_rate = 0.00;
                        }
                        if(tax3_rate_label.getText()!="null" ){
                              Tax3_rate = Double.parseDouble(tax3_rate_label.getText());
                        }
                        else{
                             Tax3_rate = 0.00;
                        }
                        
                        Hall_Serv_Chrg = Double.parseDouble(hall_serv_chrg_label.getText());
                        TotalAmount = Double.parseDouble(tot_chrge.getText());
                        Basic1 = Integer.parseInt(basic1_label.getText());
                        Basic2 = Integer.parseInt(basic2_label.getText());
                        Slot_Flag = Integer.parseInt(slot_flag_label.getText());
                        advanceId = advance_Recv_label.getText();
                        Advance_Recv = Double.parseDouble(TotAdvRecv_Lable.getText());
                        
                        Tax1_AMT = Double.parseDouble(tax1_amt_label.getText());
                        Tax2_AMT = Double.parseDouble(tax2_amt_label.getText());
                        Tax3_AMT = Double.parseDouble(tax3_amt_label.getText());
                        
                        Tax_total = (Tax1_AMT + Tax2_AMT + Tax3_AMT);
                        Customer = Hall_CheckIn_Detail.getCustomerID(m_App, MemberNo);
                        Customer_ID = Hall_CheckIn_Detail.getCustomerID(m_App, MemberNo);
                        Booking_seq_no = booking_seq_no_label.getText();
                      //  HallId = hall_id_label.getText();
                        
                        Hall_Serv_Chrg = 0.00;
                        Hall_serv_ID = null;
                        Paid = 0;
                        
                        // TAX ACCOUNTS .... #AAKASH
                        
                         if(tax1_id_label.getText()!=null && tax1_id_label.getText().trim().length()>0){
                                Tax1_Accnt = tax1_id_label.getText();
                                Tax_Accnt_List.add(Tax1_Accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax1_amt_label.getText()));
                            }
                            else{
                                Tax1_Accnt = null;
                            }
                            if(tax2_id_label.getText()!=null && tax2_id_label.getText().trim().length()>0){
                                Tax2_Accnt = tax2_id_label.getText();
                                Tax_Accnt_List.add(Tax2_Accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax2_amt_label.getText()));
                            }
                            else{
                               Tax2_Accnt = null;
                            }
                            if(tax3_id_label.getText()!=null && tax3_id_label.getText().trim().length()>0){
                                Tax3_accnt= tax3_id_label.getText();
                                Tax_Accnt_List.add(Tax3_accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax3_amt_label.getText()));
                            }
                            else{
                                Tax3_accnt = null;
                            }
                        
                        
                        
                        
                        if(GuestName!=null){
                            Customer = Customer + "#" +GuestName;
                        }
                        
                        Narrations=null;
                        Check_in_ID = checkin_id_label.getText();
                        HallName = Hall_N_Label.getText();
                        NARRATION = "Recieved Bill Against Hall Booking_ID : "+Booking_seq_no+ "." ;
                        
                        try {
                            Bill_ID = getNextBillID();
                        } catch (BasicException ex) {
                            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        TRANSREF = CheckInTable_Model.getTransRef(m_App);
                        Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Hall(m_App, HallId);
                        Revenue_Acct_ID = CheckInTable_Model.getReve_Acct_Hall(m_App, HallId);
                        DueAmt = Double.parseDouble(dueamnt_label.getText());
                        BalAMT = Double.parseDouble(Tot_Bal_amt_text.getText());
                       
                        UserAccnt =  m_App.getAppUserView().getUser().getcashaccount();
                        TID = UUID.randomUUID().toString();  
                        
                        
                        
                        // ------------------------------------------------------------------------------ LOAD ADVANCE DETIALS
                        
                            AdvRecptAdjusted = new ArrayList<Object>();  
                            BalAmtAdjusted = new ArrayList<Double>();
                            AdjAmtList = new ArrayList<Double>();

                           Double BalAmt = 0.00;
                           Double AdjustedAmt = 0.00;  


                            int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
                             for(int j=0;j<rowCount2;j++){
                                int row1 = j; 
                                Boolean ticked1  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 3).toString()); 
                                if(ticked1){
                                   AdvanceRecieptNo = String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 1).toString());
                                   BalAmt = Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 5).toString());
                                   AdjustedAmt =  Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 4).toString());

                                   AdvRecptAdjusted.add(AdvanceRecieptNo);
                                   BalAmtAdjusted.add(BalAmt);
                                   AdjAmtList.add(AdjustedAmt);
                                }

                             }   
              
                        
                       
                             
                        
                        
                        
                         Transaction t = new Transaction(m_App.getSession()) {
             
                        
                             
                        @Override
                        protected Object transact() throws BasicException {

                        Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
                                    
                                    
                                    
                                    
                                    
                            int insert_Bill  =  new PreparedSentence(m_App.getSession(), "INSERT INTO hall_bill (ID , CUSTOMER , HALL_NAME , SLOT_TIME , CHECKIN_DATE ,RATE , TAX_TOTAL , HALL_SERV_ID , HALL_SERV_AMT , PAID  , RECIEPT ,  ADVANCE_RECV , CHECK_IN_ID , TAX1 , TAX2 , TAX3 , BILLNAME , ID_DETAIL , CRBY , CRDATE , CRHOST , SLOT_BOOKED , NARRATIONS , BOOKING_SEQ_NO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.TIMESTAMP , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE  , Datas.INT , Datas.STRING , Datas.DOUBLE ,  Datas.STRING  , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP, Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING})).exec
                                                                       (new Object[]{Bill_ID , Customer , HallName , SlotTimings ,Booking_Date , Charges ,  Tax_total , Hall_serv_ID , Hall_Serv_Chrg , Paid , Reciept_No , Advance_Recv , Check_in_ID , Tax1_rate , Tax2_rate , Tax3_rate , BillingName , Id_Card_Details ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Slot_Booked , Narrations , Booking_seq_no }); 

                             
                            int x = new StaticSentence(m_App.getSession(), "UPDATE hall_check_in SET ACTIVE=0 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {Check_in_ID});

                            
                            int x1 = new StaticSentence(m_App.getSession(), "UPDATE hall_booked_details SET CHK_IN_FLAG=2 WHERE ID=(SELECT C.BOOKING_ID FROM hall_check_in C WHERE C.ID=?)"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {Check_in_ID});

                            int x2 = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET CHK_IN_FLAG=2  WHERE BOOKING_SEQ_NO = ? "
                                                                        , new SerializerWriteBasic(new Datas[] { Datas.STRING })).exec(new Object[] {  Booking_seq_no});

                            int x3 = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET  BAL_AMT=? WHERE BOOKING_SEQ_NO = ? "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {BalAMT ,  Booking_seq_no});
                             
                             
                            
                            
                            for(int i=0;i<AdvRecptAdjusted.size();i++)
                            {
                                
                                String Recieptno = AdvRecptAdjusted.get(i).toString();
                                Double Adjamt = AdjAmtList.get(i);
                               
                                
                                int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
                                                                           , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {Adjamt , Recieptno });


                                int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_hall SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
                                                                           , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {Adjamt , Recieptno });

                            
                                
                            }
                            
                            
                            
                            
                             int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID ,(Advance_Recv-BalAMT) , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                               TRANSREF = MemberName+","+MemberNo+", Hall rent :"+ Charges+" /-. Advance paid ="+Advance_Recv+"/-."  ;

                              
                              int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{(Advance_Recv-BalAMT) ,Advance_Acct_ID ,AjPerDate});


                              int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{(Advance_Recv-BalAMT),Advance_Acct_ID});


                               
                              
                               
                               
                               int  INSERT_INTO_ACCOUNT2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID ,Charges , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Revenue_Acct_ID , TID , new Date() , "C" , 1    });                                                                                                


                               
                                int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Charges ,Revenue_Acct_ID ,AjPerDate});   

                                    
                                 int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Charges,Revenue_Acct_ID});

                            
                               
                               
                               
                              // FOR  TAX ACCOUNTING ENTRIES.... 
                               for(int i=0 ; i< Tax_Accnt_List.size() ; i++){
                                  String TaxacntId = Tax_Accnt_List.get(i).toString();
                                  Double taxamt = Tax_Amt_List.get(i);
                                  TRANSREF = "tax collected for Hall";
                                  NARRATION = "tax amt collected for Hall "+hall_name_label.getText();

                                  int  INSERT_INTO_ACCOUNT3  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                      ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID , taxamt , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , TaxacntId , TID , new Date() , "C" , 1    });                                                                                                

                                  
                                  int Update_AJPeriodTotals3 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{taxamt ,TaxacntId ,AjPerDate});   


                                  int Update_TrailBalance3 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{taxamt,TaxacntId});


                                  

                              } 


                                      // INSERT REFUND VOUCHER  
                               
                               
                              Double RefundAmt = Double.parseDouble(refund_bal_amt_text.getText());
                        
                                if(RefundAmt>0){    
                                
                                  InsertRefund_VoucherEntry(Bill_ID);
                             
                                 }
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Bill Generated Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    bill_id_label.setText(Bill_ID);
                                   
                                    Launch_Bill(Bill_ID);
                                    Launch_Bill_Statement(Bill_ID);
                                    //  SEND SMS .....
                                    String MobNo = dmang.getcustMobileNoByCustID(Member_ID);
                                    String Msg = "Dear Member,\nYou have charged Rs."+TotalAmount+"/- for booking of "+HallName+" Hall on "+Booking_Date_Str+". Advnce Recvd Rs."+Advance_Recv+"/- .";
                                    System.out.println("Cancel msg = "+Msg.length());
                                    if(MobNo!=null && MobNo.trim().length()>0){
                                          dmang.InsertActiveMsgTable(Msg, Member_ID , MobNo, 2);
                                     }
                                    
                                    
                                    reset();
                                    loaddata();
                                    UpdateRefundVoucher();
                                    Gname_Combo.setSelectedIndex(-1);
                                    jTabbedPane1.setSelectedIndex(0);
                                    
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                                }  
                        
             }
        
        
        
        // CREATE HALL BILL ALTERNATE METHOD---------------------------------------------------------------------------------
        
          public void Generate_Hall_Bill2(){
                            
                        
                        Tax_Accnt_List = new ArrayList<Object>();
                        Tax_Amt_List = new ArrayList<Double>();
                        MemberNo = mno_label.getText();
                        MemberName = mem_name_label.getText();
                        BillingName = Biiling_name_label.getText();
                        HallId = hall_id_label2.getText();
                        Booking_Date_Str = booked_date_label.getText();
                        if(guest_name_label.getText()!=null && guest_name_label.getText().trim().length()>0){
                            GuestName = guest_name_label.getText();
                        }
                        else{
                            GuestName=null;
                        }
                        try {
                            Booking_Date = (Date) Formats.TIMESTAMP.parseValue(Date_label.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SlotTimings = slot_time_label.getText();
                        Slot_Booked = slot_book_label.getText();
                        Charges = Double.parseDouble(charges.getText());
                        System.out.println(tax2_rate_label.getText());
                        
                        if(tax1_rate_label.getText()!="null" ){
                              Tax1_rate = Double.parseDouble(tax1_rate_label.getText());
                        }
                        else{
                             Tax1_rate = 0.00;
                        }
                        if(tax2_rate_label.getText()!="null" ){
                              Tax2_rate = Double.parseDouble(tax2_rate_label.getText());
                        }
                        else{
                             Tax2_rate = 0.00;
                        }
                        if(tax3_rate_label.getText()!="null" ){
                              Tax3_rate = Double.parseDouble(tax3_rate_label.getText());
                        }
                        else{
                             Tax3_rate = 0.00;
                        }
                    
                        Hall_Serv_Chrg = Double.parseDouble(hall_serv_chrg_label.getText());
                        TotalAmount = Double.parseDouble(tot_chrge.getText());
                        Basic1 = Integer.parseInt(basic1_label.getText());
                        Basic2 = Integer.parseInt(basic2_label.getText());
                        Slot_Flag = Integer.parseInt(slot_flag_label.getText());
                        advanceId = advance_Recv_label.getText();
                        Advance_Recv = Double.parseDouble(TotAdvRecv_Lable.getText());
                        
                        Tax1_AMT = Double.parseDouble(tax1_amt_label.getText());
                        Tax2_AMT = Double.parseDouble(tax2_amt_label.getText());
                        Tax3_AMT = Double.parseDouble(tax3_amt_label.getText());
                        
                        Tax_total = (Tax1_AMT + Tax2_AMT + Tax3_AMT);
                        Customer = Hall_CheckIn_Detail.getCustomerID(m_App, MemberNo);
                        Customer_ID = Hall_CheckIn_Detail.getCustomerID(m_App, MemberNo);
                        Booking_seq_no = booking_seq_no_label.getText();
                       // HallId = hall_id_label.getText();
                        
                        Hall_Serv_Chrg = 0.00;
                        Hall_serv_ID = null;
                        Paid = 0;
                        Discount_Amt = Double.parseDouble(Discount_Amt_label.getText());
                        
                        
                        // TAX ACCOUNTS .... #AAKASH
                        
                         if(tax1_id_label.getText()!=null && tax1_id_label.getText().trim().length()>0){
                                Tax1_Accnt = tax1_id_label.getText();
                                Tax_Accnt_List.add(Tax1_Accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax1_amt_label.getText()));
                            }
                            else{
                                Tax1_Accnt = null;
                            }
                            if(tax2_id_label.getText()!=null && tax2_id_label.getText().trim().length()>0){
                                Tax2_Accnt = tax2_id_label.getText();
                                Tax_Accnt_List.add(Tax2_Accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax2_amt_label.getText()));
                            }
                            else{
                               Tax2_Accnt = null;
                            }
                            if(tax3_id_label.getText()!=null && tax3_id_label.getText().trim().length()>0){
                                Tax3_accnt= tax3_id_label.getText();
                                Tax_Accnt_List.add(Tax3_accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax3_amt_label.getText()));
                            }
                            else{
                                Tax3_accnt = null;
                            }
                        
                        
                        
                        
                        if(GuestName!=null){
                            Customer = Customer + "#" +GuestName;
                        }
                        
                        Narrations=null;
                        Check_in_ID = checkin_id_label.getText();
                        HallName = Hall_N_Label.getText();
                        NARRATION = "Recieved Bill Against Hall Booking_ID : "+Booking_seq_no+ "." ;
                        
                       
                        
                        TRANSREF = CheckInTable_Model.getTransRef(m_App);
                        Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Hall(m_App, HallId);
                        Revenue_Acct_ID = CheckInTable_Model.getReve_Acct_Hall(m_App, HallId);
                        DueAmt = Double.parseDouble(dueamnt_label.getText());
                        BalAMT = Double.parseDouble(Tot_Bal_amt_text.getText());
                       
                        UserAccnt =  m_App.getAppUserView().getUser().getcashaccount();
                        TID = UUID.randomUUID().toString();  
                        
                        
                        
                        // ------------------------------------------------------------------------------ LOAD ADVANCE DETIALS
                        
                            AdvRecptAdjusted = new ArrayList<Object>();  
                            BalAmtAdjusted = new ArrayList<Double>();
                            AdjAmtList = new ArrayList<Double>();

                           Double BalAmt = 0.00;
                           Double AdjustedAmt = 0.00;  


                            int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
                             for(int j=0;j<rowCount2;j++){
                                int row1 = j; 
                                Boolean ticked1  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 3).toString()); 
                                if(ticked1){
                                   AdvanceRecieptNo = String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 1).toString());
                                   BalAmt = Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 5).toString());
                                   AdjustedAmt =  Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 4).toString());

                                   AdvRecptAdjusted.add(AdvanceRecieptNo);
                                   BalAmtAdjusted.add(BalAmt);
                                   AdjAmtList.add(AdjustedAmt);
                                }

                             }   
              
                        try {
                            Bill_ID = getNextBillID();
                        } catch (BasicException ex) {
                            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                             
                       if(Bill_ID!=null && Bill_ID.trim().length()>0 && Bill_ID!=""){  
                        
                        
                         Transaction t = new Transaction(m_App.getSession()) {
             
                        
                             
                        @Override
                        protected Object transact() throws BasicException {

                        Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
                                    
                                    
                                    
                                    
                                    
                            int insert_Bill  =  new PreparedSentence(m_App.getSession(), "INSERT INTO hall_bill (ID , CUSTOMER , HALL_NAME , SLOT_TIME , CHECKIN_DATE ,RATE , TAX_TOTAL , HALL_SERV_ID , HALL_SERV_AMT , PAID  , RECIEPT ,  ADVANCE_RECV , CHECK_IN_ID , TAX1 , TAX2 , TAX3 , BILLNAME , ID_DETAIL , CRBY , CRDATE , CRHOST , SLOT_BOOKED , NARRATIONS , BOOKING_SEQ_NO , DISCOUNT , TAX1_AMT , TAX2_AMT , TAX3_AMT , BALANCEAMOUNT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.TIMESTAMP , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE  , Datas.INT , Datas.STRING , Datas.DOUBLE ,  Datas.STRING  , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP, Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE ,  Datas.DOUBLE})).exec
                                                                       (new Object[]{Bill_ID , Customer , HallName , SlotTimings ,Booking_Date , Charges ,  Tax_total , Hall_serv_ID , Hall_Serv_Chrg , Paid , Reciept_No , Advance_Recv , Check_in_ID , Tax1_rate , Tax2_rate , Tax3_rate , BillingName , Id_Card_Details ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Slot_Booked , Narrations , Booking_seq_no , Discount_Amt , Tax1_AMT , Tax2_AMT , Tax3_AMT , ((Charges+Tax_total)-Discount_Amt)}); 

               
                           
                            int x = new StaticSentence(m_App.getSession(), "UPDATE hall_check_in SET BILLED=1 WHERE ID=?"
                                                                      , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {Check_in_ID});
                             
                            
                         
                         
                           int x1 = new StaticSentence(m_App.getSession(), "UPDATE hall_check_in SET BILLNO=? WHERE ID=?"
                                                                      , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] { Bill_ID , Check_in_ID});
                             
                               
                               
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Bill Generated Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    bill_id_label.setText(Bill_ID);
                                   
                                     Launch_Bill(Bill_ID);
                                   // Launch_Bill_Statement(Bill_ID);
                                    //  SEND SMS .....
                                  //  String MobNo = dmang.getcustMobileNoByCustID(Member_ID);
                                  //  String Msg = "Dear Member,\nYou have charged Rs."+TotalAmount+"/- for booking of "+HallName+" Hall on "+Booking_Date_Str+". Advnce Recvd Rs."+Advance_Recv+"/- .";
                                  //  System.out.println("Cancel msg = "+Msg.length());
                                  //  if(MobNo!=null && MobNo.trim().length()>0){
                                  //        dmang.InsertActiveMsgTable(Msg, Member_ID , MobNo, 2);
                                  //   }
                                    
                                    
                                    reset();
                                    loaddata();
                                 //   UpdateRefundVoucher();
                                    Gname_Combo.setSelectedIndex(-1);
                                   jTabbedPane1.setSelectedIndex(0);
                                    
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                                }  
                       }
                        
             }
        
       // GENERATE FINAL BILL STATEMENT --------------------------------------------------------------------------------------------
          
            public void GERERATE_FINAL_STATEMENT() throws BasicException{
                            
                        
                        Tax_Accnt_List = new ArrayList<Object>();
                        Tax_Amt_List = new ArrayList<Double>();
                        MemberNo = mno_label.getText();
                        MemberName = mem_name_label.getText();
                        BillingName = Biiling_name_label.getText();
                        HallId = hall_id_label2.getText();
                        Booking_Date_Str = booked_date_label.getText();
                        if(guest_name_label.getText()!=null && guest_name_label.getText().trim().length()>0){
                            GuestName = guest_name_label.getText();
                        }
                        
                        else{
                            GuestName=null;
                        }
                        try {
                            Booking_Date = (Date) Formats.TIMESTAMP.parseValue(Date_label.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SlotTimings = slot_time_label.getText();
                        Slot_Booked = slot_book_label.getText();
                        Charges = Double.parseDouble(charges.getText());
                        Discount_Amt = Double.parseDouble(Discount_Amt_label.getText());
                        System.out.println(tax2_rate_label.getText());
                        
                        if(tax1_rate_label.getText()!="null" ){
                              Tax1_rate = Double.parseDouble(tax1_rate_label.getText());
                        }
                        else{
                             Tax1_rate = 0.00;
                        }
                        if(tax2_rate_label.getText()!="null" ){
                              Tax2_rate = Double.parseDouble(tax2_rate_label.getText());
                        }
                        else{
                             Tax2_rate = 0.00;
                        }
                        if(tax3_rate_label.getText()!="null" ){
                              Tax3_rate = Double.parseDouble(tax3_rate_label.getText());
                        }
                        else{
                             Tax3_rate = 0.00;
                        }
                    
                        Hall_Serv_Chrg = Double.parseDouble(hall_serv_chrg_label.getText());
                        TotalAmount = Double.parseDouble(tot_chrge.getText());
                        Basic1 = Integer.parseInt(basic1_label.getText());
                        Basic2 = Integer.parseInt(basic2_label.getText());
                        Slot_Flag = Integer.parseInt(slot_flag_label.getText());
                        advanceId = advance_Recv_label.getText();
                        Advance_Recv = Double.parseDouble(TotAdvRecv_Lable.getText());
                        
                        Tax1_AMT = Double.parseDouble(tax1_amt_label.getText());
                        Tax2_AMT = Double.parseDouble(tax2_amt_label.getText());
                        Tax3_AMT = Double.parseDouble(tax3_amt_label.getText());
                        
                        Tax_total = (Tax1_AMT + Tax2_AMT + Tax3_AMT);
                        Customer = Hall_CheckIn_Detail.getCustomerID(m_App, MemberNo);
                        Customer_ID = Hall_CheckIn_Detail.getCustomerID(m_App, MemberNo);
                        Booking_seq_no = booking_seq_no_label.getText();
                       // HallId = hall_id_label.getText();
                        
                        Hall_Serv_Chrg = 0.00;
                        Hall_serv_ID = null;
                        Paid = 0;
                        Bill_ID = bill_id_label.getText();
                        // TAX ACCOUNTS .... #AAKASH
                        
                         if(tax1_id_label.getText()!=null && tax1_id_label.getText().trim().length()>0){
                                Tax1_Accnt = tax1_id_label.getText();
                                Tax_Accnt_List.add(Tax1_Accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax1_amt_label.getText()));
                            }
                            else{
                                Tax1_Accnt = null;
                            }
                            if(tax2_id_label.getText()!=null && tax2_id_label.getText().trim().length()>0){
                                Tax2_Accnt = tax2_id_label.getText();
                                Tax_Accnt_List.add(Tax2_Accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax2_amt_label.getText()));
                            }
                            else{
                               Tax2_Accnt = null;
                            }
                            if(tax3_id_label.getText()!=null && tax3_id_label.getText().trim().length()>0){
                                Tax3_accnt= tax3_id_label.getText();
                                Tax_Accnt_List.add(Tax3_accnt);
                                Tax_Amt_List.add(Double.parseDouble(tax3_amt_label.getText()));
                            }
                            else{
                                Tax3_accnt = null;
                            }
                        
                        
                        
                        
                        if(GuestName!=null){
                            Customer = Customer + "#" +GuestName;
                        }
                        
                        Narrations=null;
                        Check_in_ID = checkin_id_label.getText();
                        HallName = Hall_N_Label.getText();
                        NARRATION = "Recieved Bill Against Hall Booking_ID : "+Booking_seq_no+ "." ;
                        
                      //  try {
                      //      Bill_ID = getNextBillID();
                      //  } catch (BasicException ex) {
                      //      Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                      //  }
                        
                        TRANSREF = CheckInTable_Model.getTransRef(m_App);
                        Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Hall(m_App, HallId);
                        Revenue_Acct_ID = CheckInTable_Model.getReve_Acct_Hall(m_App, HallId);
                        DueAmt = Double.parseDouble(dueamnt_label.getText());
                        BalAMT = Double.parseDouble(Tot_Bal_amt_text.getText());
                       
                        UserAccnt =  m_App.getAppUserView().getUser().getcashaccount();
                        TID = UUID.randomUUID().toString();  
                        
                        
                        
                        // ------------------------------------------------------------------------------ LOAD ADVANCE DETIALS
                        
                            AdvRecptAdjusted = new ArrayList<Object>();  
                            BalAmtAdjusted = new ArrayList<Double>();
                            AdjAmtList = new ArrayList<Double>();

                           Double BalAmt = 0.00;
                           Double AdjustedAmt = 0.00;  


                            int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
                             for(int j=0;j<rowCount2;j++){
                                int row1 = j; 
                                Boolean ticked1  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 3).toString()); 
                                if(ticked1){
                                   AdvanceRecieptNo = String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 1).toString());
                                   BalAmt = Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 5).toString());
                                   AdjustedAmt =  Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 4).toString());

                                   AdvRecptAdjusted.add(AdvanceRecieptNo);
                                   BalAmtAdjusted.add(BalAmt);
                                   AdjAmtList.add(AdjustedAmt);
                                }

                             }   
              
                        
                       
                             
                         Rfd_Voucher_No = getNextVoucherNo();
                          
                         if(Rfd_Voucher_No!=null && Rfd_Voucher_No.trim().length()>0 && Rfd_Voucher_No!="")
                         {
                        
                         Transaction t = new Transaction(m_App.getSession()) {
             
                        
                             
                        @Override
                        protected Object transact() throws BasicException {

                        Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
                                    
                                    
                                    
                                    
                                    
                          //  int insert_Bill  =  new PreparedSentence(m_App.getSession(), "INSERT INTO hall_bill (ID , CUSTOMER , HALL_NAME , SLOT_TIME , CHECKIN_DATE ,RATE , TAX_TOTAL , HALL_SERV_ID , HALL_SERV_AMT , PAID  , RECIEPT ,  ADVANCE_RECV , CHECK_IN_ID , TAX1 , TAX2 , TAX3 , BILLNAME , ID_DETAIL , CRBY , CRDATE , CRHOST , SLOT_BOOKED , NARRATIONS , BOOKING_SEQ_NO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                          //            , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.TIMESTAMP , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE  , Datas.INT , Datas.STRING , Datas.DOUBLE ,  Datas.STRING  , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP, Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING})).exec
                                                            //           (new Object[]{Bill_ID , Customer , HallName , SlotTimings ,Booking_Date , Charges ,  Tax_total , Hall_serv_ID , Hall_Serv_Chrg , Paid , Reciept_No , Advance_Recv , Check_in_ID , Tax1_rate , Tax2_rate , Tax3_rate , BillingName , Id_Card_Details ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Slot_Booked , Narrations , Booking_seq_no }); 

                             
                            int x = new StaticSentence(m_App.getSession(), "UPDATE hall_check_in SET ACTIVE=0 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {Check_in_ID});

                            
                            int x1 = new StaticSentence(m_App.getSession(), "UPDATE hall_booked_details SET CHK_IN_FLAG=2  WHERE ID=(SELECT C.BOOKING_ID FROM hall_check_in C WHERE C.ID=?)"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {Check_in_ID});

                            int x2 = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET CHK_IN_FLAG=2  WHERE BOOKING_SEQ_NO = ? "
                                                                        , new SerializerWriteBasic(new Datas[] { Datas.STRING })).exec(new Object[] {  Booking_seq_no});

                            int x3 = new StaticSentence(m_App.getSession(), "UPDATE hall_advance_payment SET  BAL_AMT=? WHERE BOOKING_SEQ_NO = ? "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {BalAMT ,  Booking_seq_no});
                             
                             
                            
                        
                            
                              TRANSREF = "Party Hall Booking"  ;
                            
                            
                             int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID ,(Advance_Recv-BalAMT) , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                              // TRANSREF = MemberName+","+MemberNo+", Hall rent :"+ (Charges-Discount_Amt)+" /-. Advance paid ="+Advance_Recv+"/-."  ;

                              
                              int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{(Advance_Recv-BalAMT) ,Advance_Acct_ID ,AjPerDate});


                              int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{(Advance_Recv-BalAMT),Advance_Acct_ID});


                               
                              
                               
                               
                               int  INSERT_INTO_ACCOUNT2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID ,(Charges-Discount_Amt) , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Revenue_Acct_ID , TID , new Date() , "C" , 1    });                                                                                                


                               
                                int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Charges-Discount_Amt ,Revenue_Acct_ID ,AjPerDate});   

                                    
                                 int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Charges-Discount_Amt,Revenue_Acct_ID});

                            
                               
                               
                               
                              // FOR  TAX ACCOUNTING ENTRIES.... 
                               for(int i=0 ; i< Tax_Accnt_List.size() ; i++){
                                  String TaxacntId = Tax_Accnt_List.get(i).toString();
                                  Double taxamt = Tax_Amt_List.get(i);
                                  TRANSREF = "tax collected for Hall";
                                  NARRATION = "tax amt collected for Hall "+hall_name_label.getText();

                                  int  INSERT_INTO_ACCOUNT3  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                      ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID , taxamt , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , TaxacntId , TID , new Date() , "C" , 1    });                                                                                                

                                  
                                  int Update_AJPeriodTotals3 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{taxamt ,TaxacntId ,AjPerDate});   


                                  int Update_TrailBalance3 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{taxamt,TaxacntId});


                                  

                              } 


                                      // INSERT REFUND VOUCHER  
                               
                               
                              Double RefundAmt = Double.parseDouble(refund_bal_amt_text.getText());
                        
                                if(RefundAmt>0){    
                                
                                  InsertRefund_VoucherEntry(Bill_ID);
                             
                                 }
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Bill Statement Generated Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    bill_id_label.setText(Bill_ID);
                                   
                                 //  Launch_Bill(Bill_ID);
                                    Launch_Bill_Statement(Bill_ID);
                                    //  SEND SMS .....
                                    String MobNo = dmang.getcustMobileNoByCustID(Member_ID);
                                    String Msg = "Dear Member,\nYou have charged Rs."+TotalAmount+"/- for booking of "+HallName+" Hall on "+Booking_Date_Str+". Advnce Recvd Rs."+Advance_Recv+"/- .";
                                    System.out.println("Cancel msg = "+Msg.length());
                                    if(MobNo!=null && MobNo.trim().length()>0){
                                          dmang.InsertActiveMsgTable(Msg, Member_ID , MobNo, 2);
                                     }
                                    
                                    
                                    reset();
                                    loaddata();
                                    UpdateRefundVoucher();
                                    Gname_Combo.setSelectedIndex(-1);
                                    jTabbedPane1.setSelectedIndex(0);
                                    
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                                } 
                            
                            
                         }
             }
        
        
        
        
        
        
        
        // FOR JASPER REPORT OF BILL 
        public void  Launch_Bill(String Billid){
            
         Date edate = new Date();
          
         Map reportparam = new HashMap();
        
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForHallCheckIn ds= null;
         
       
         String linkname = link_name_label.getText();
         
         try {
                hallbill_Model = HallBillModel.LoadHallCheckInDetail(m_App, Billid);
             } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             hall_Bill_list  =  (List<HallBillModel.HallBillInfo>) hallbill_Model.getHallInfoList();
           
         ds = new DataSourceForHallCheckIn(hall_Bill_list);
         data1.setDataSource(ds);
         
         
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("enddate", edate);
         
         
         
         JasperPrint jp = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/HallBill.jrxml", reportparam, false, data1, true, "HallBill");
           
      
        }
          // FOR JASPER REPORT OF BILL 
        public void  Launch_Bill_Statement(String Billid){
            
         Date edate = new Date();
          
         Map reportparam = new HashMap();
         
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForHallCheckIn ds= null;
         
       
         String linkname = link_name_label.getText();
         hall_Bill_list = new ArrayList();
         try {
                hallbill_Model = HallBillModel.LoadHallCheckInDetail(m_App, Billid);
             } catch (BasicException ex) {
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             hall_Bill_list  =  (List<HallBillModel.HallBillInfo>) hallbill_Model.getHallInfoList();
           
         ds = new DataSourceForHallCheckIn(hall_Bill_list);
         data1.setDataSource(ds);
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("enddate", edate);
       
         JasperPrint jp1 = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/HallBillStatment.jrxml", reportparam, false, data1, true, "HallBill");   
            
        }
        
        
        
        public void InsertRefund_VoucherEntry(String BILL_ID) throws BasicException{
            
                        Date AjPerDate= new Date();
                        Calendar cal=Calendar.getInstance();
                        cal.setTimeInMillis(AjPerDate.getTime());
                        cal.set(Calendar.HOUR_OF_DAY, 00);
                        cal.set(Calendar.MINUTE, 00);
                        cal.set(Calendar.SECOND, 00);
                        cal.set(Calendar.MILLISECOND, 00);
                        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        AjPerDate.setTime(cal.getTimeInMillis());
                                    
            
            
              Double RefundAmt = Double.parseDouble(refund_bal_amt_text.getText());
              
              int Refund_Voucher =  new PreparedSentence(m_App.getSession(), "INSERT INTO room_hall_refund_voucher (ID , RV_NO , CUST_ID , MEM_NO , BOOKING_SEQ_NO ,BILLED_AMT , ADVNCE_AMT , REFUND_AMT , CHK_IN_ID , REFUND_BY  , REFUND_HOST ,  REFUND_DATE  , ROOMTYPE ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING})).exec
                                  (new Object[]{UUID.randomUUID().toString() ,Rfd_Voucher_No ,  Customer , MemberNo , Booking_seq_no ,TotalAmount , Advance_Recv ,RefundAmt , Check_in_ID , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() , new Date() ,  HallName }); 
              
              
              TRANSREF = MemberNo + " , Refund Voucher No : "+Rfd_Voucher_No+" # amt :"+(Advance_Recv-TotalAmount)+" /-  created againts booking no: "+Bill_ID ;
              NARRATION = "Refund of "+(Advance_Recv-TotalAmount)+ " /- to member : "+MemberNo+" . Voucher no "+Rfd_Voucher_No;
              
              int  INSERT_INTO_ACCOUNT5  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Bill_ID ,RefundAmt , Booking_Date , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccnt , TID , new Date() , "C" , 1    });                                                                                                

              
              
                
              int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{RefundAmt ,UserAccnt ,AjPerDate});   

                                    
              int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{RefundAmt,UserAccnt});

              
              
              
              
              
              
              generateRefundVoucher();
              
              
        }
       
      public void generateRefundVoucher( )
        {
             //List<SmsConnection.DelRepBean> list = new ArrayList<SmsConnection.DelRepBean>();
            Map reportparams = new HashMap();
            List<Object> list = new ArrayList<Object>();
              //list= new SmsConnection(m_app).getDelperlist();
             DataSourceProvider data1 = new DataSourceProvider(list);
            // DataSourceForSMSDelReport dsfc = new DataSourceForSMSDelReport(list);
            // data1.setDataSource(dsfc);
             Double RefundAmt = Double.parseDouble(refund_bal_amt_text.getText());
             String Message=null;
             
             Message = "Dear Member, \n        Refund of Rs. "+RefundAmt +" /-  after adjustment of  all advances recieved for Hall / Room Booking.  \n (Booking ID : "+Booking_seq_no+ ") Booked By "+BillingName+ ".  \n " + "Total Advance Recieved : "+TotalAmount +".";
             reportparams.put("V_NO",Rfd_Voucher_No );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             reportparams.put("MEM_NAME", MemberName);
             reportparams.put("M_NO", MemberNo);
           //  reportparams.put("MAIN_TEXT", Message);
             reportparams.put("CREATEBY",  m_App.getAppUserView().getUser().getName());
             reportparams.put("CRHOST" , m_App.getProperties().getHost());
             reportparams.put("MEMSIGN", BillingName);
             
              reportparams.put("HALLNAME", HallName);
             reportparams.put("bookingseqno", Booking_seq_no);
             reportparams.put("bookingdateinfo", Booking_Date+" Slot : ( "+SlotTimings+") .");
             reportparams.put("advanceamt", decimalFormat.format(Advance_Recv));
             reportparams.put("totalbillamount", decimalFormat.format(TotalAmount));
            
             reportparams.put("cancellationperc", "0.00%");
             reportparams.put("fixedCharge", "0.00");
             reportparams.put("cancellationCharge", "0.00");
             reportparams.put("refundamt", decimalFormat.format(RefundAmt));
             
             
             
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/Refund_voucher.jrxml", reportparams, false, data1, true, null); 


        }
        
        
        
        
       private String getNextVoucherNo() throws BasicException{
       //akash:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname= "REFUND VOUCHER";
     Object[] obj=(Object[])new  StaticSentence(m_App.getSession()
            , "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=? AND ACTIVE=TRUE"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING})
            ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.DOUBLE})).find(new Object[]{uname,uname});
     if(obj!=null){
         Double max=Double.parseDouble(obj[1].toString());
         max++;
         billnum=obj[0].toString()+ max.intValue();
         return billnum;   
        }
     else{
         JOptionPane.showMessageDialog(null, "Please Specify the Refund Voucher Series", "Cannot Create Refund", JOptionPane.WARNING_MESSAGE);
         return "";
        }
       
     }
        
      
       
       public void UpdateRefundVoucher() throws BasicException{ 
         
        String billnum;
        String uname="REFUND VOUCHER";
    
           int x = new StaticSentence(m_App.getSession()
                       // , "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME =(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) "
                  , "UPDATE SEQUENCEDETAIL SET RMAX=RMAX+1 WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=?"

                  , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING }))
                        .exec(new Object[] {uname,uname});
         
    }  
       
       
    // -------------------------------------------------------------------------------------------------------------------------------------   
       
       public void ShowHallServiceTableModel(HallService HS){
           
         
          //roomCharges.setText("0.00");
          HallServiceTableModel = HS.getTableModel2();
          
          HallServiceTableModel.settext(hall_serv_chrg_label ,totalAmtAdjusted , unadjustTotal_text  );
          hallservicetable.setModel(HallServiceTableModel);
          
           
       }
       
        
       public void ShowAdvanceInfo(AdvanceAdjustTableModel AdvanceAdjustTable_Model){
        
        hall_serv_chrg_label.setText("0.00");
        AdvanceListTableModel = AdvanceAdjustTable_Model.getTableModel2();
        //Tot_Bal_amt_text.setText("0.00");
       
        
        AdvanceListTableModel.settext(totalAmtAdjusted , Tot_Bal_amt_text , hall_serv_chrg_label);
        advancedetailtable.setModel(AdvanceListTableModel);
        
    }    
       
       private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            jTextField3.setText(null);
        } else if (cTrans == '+' || cTrans == '-') {
        } else if (cTrans == ' ' || cTrans == '=') {
        } else {

            jTextField3.setText(jTextField3.getText() + cTrans);
        }
    } 
       
       
    public void LoaddataForTables(){
        
         String Cust_n = HS.getCust_link_name(m_App, "TEMP");
                 
                if(Cust_n!="null"){
               
                    try{
                           //  HS = HallService.load_Curr_Bills(m_App, "temp" ,Member_ID ,HallName,  );//commented by pratima
                           HS = HallService.load_Curr_Bills(m_App, "temp" ,Member_ID ,HallName,Check_in_ID);
                         }
                         catch (BasicException ex) {
                            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        BillInfoList = (List<HallService.BillInfo>) HS.getBillInfo_Path();
                        if(BillInfoList==null){
                             BillInfoList = new ArrayList<HallService.BillInfo>();
                        }
                        if(BillInfoList.size()>0){
                            unadjustTotal_text.setText(Formats.DOUBLE.formatValue(BillInfoList.get(0).getBalAmt()));
                        }
                        else{
                            unadjustTotal_text.setText("0.00");
                        }
                       ShowHallServiceTableModel(HS);
                   
                   }
                
                 
             // ADVANCE RECIEPT TABLE MODEL----------------------------------------------------------------------------------------------   
                
                try{
                             AdvanceAdjustTable_model = AdvanceAdjustTableModel.load_AdvanceList_Hall(m_App , booking_id);
                         }
                         catch (BasicException ex) {
                            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        AdvanceInfoList = (List<AdvanceAdjustTableModel.AdvanceInfo>) AdvanceAdjustTable_model.getAdvanceList();
                        if(AdvanceInfoList==null){
                             AdvanceInfoList = new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
                        }
              
                        ShowAdvanceInfo(AdvanceAdjustTable_model); 
         
    }   
       
    
    
    
    public void Print_Bill_Proforma(){
        
        
         Map reportparams = new HashMap();
            List<Object> list = new ArrayList<Object>();
             
             DataSourceProvider data1 = new DataSourceProvider(list);
           
            
             
            
            
             
          
             reportparams.put("BILLNAME","Hall Bill Proforma" );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
              reportparams.put("GSTNumber", jTextField1.getText());
             reportparams.put("MEM_NAME", mem_name_label.getText());
             reportparams.put("SKEY", mno_label.getText());
            // reportparams.put("MAIN_TEXT", Message);
             reportparams.put("HALL_NAME", Hall_N_Label.getText());
             reportparams.put("BOOKING_SEQ_NO" , booking_seq_no_label.getText());
             reportparams.put("PARENTID", "N/A");
             
             reportparams.put("MEMBER", mem_name_label.getText());
             reportparams.put("BOOKING_DATE", Date_label.getText());
             reportparams.put("SLOT_TIME", slot_book_label.getText());
             
            
             reportparams.put("RATE",  rate.getText());
             reportparams.put("RTAX1", Tax_rate1_label.getText());
             reportparams.put("RTAX2", Tax_rate2_label.getText());
             reportparams.put("RTAX3", Tax_rate3_label.getText());
             
             reportparams.put("DISCAMT", Discount_Amt_label.getText());
             reportparams.put("AMT1", charges.getText());
             reportparams.put("TAX1", tax1_amt_label.getText());
             reportparams.put("TAX2", tax2_amt_label.getText());
             reportparams.put("TAX3", tax3_amt_label.getText());
             
             reportparams.put("N_TAX1", (tax1_lebel.getText() + "  "+ Tax_Basic_Label1.getText()));
             reportparams.put("N_TAX2", (tax2_Label.getText() + "  "+ Tax_Basic_Label2.getText()));
             reportparams.put("N_TAX3", (tax3_Label.getText() + "  "+ Tax_Basic_Label3.getText()));
             
             reportparams.put("DISCAMT", Discount_Amt_label.getText());
             reportparams.put("DISCAMT_TOT", Discount_Amt_label_Total.getText());
             
             reportparams.put("GTOTAL", tot_label.getText());
             reportparams.put("WAITER", m_App.getAppUserView().getUser().getName());
            
             
             
             
             
             
             
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/HallBill_checknew.jrxml", reportparams, false, data1, true, null); 

  
        
        
        
        
        
        
    }
    
     public void setAdjustedValue(String Booking_seq_no){
    int row = jTable1.getSelectedRow();
               HallCheckInTable.Hall_CheckIn_Info showdata1 = Hall_CheckIn_Detail.getHallList().get(row);  
     if(showdata1.getBILLED()==2){
               try{
     Object[] objPaid= (Object[])new StaticSentence(m_App.getSession(), "select paid from hall_bill where  booking_seq_no=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.INT })).find(new Object[]{booking_seq_no_label.getText().toString()});
     System.out.println("paid"+(objPaid[0]).toString()) ;
     int paid=Integer.parseInt((objPaid[0]).toString());
      if(paid==1){
      Adjust_billedAmt_check.setSelected(true);
      }
      }catch(BasicException e){e.printStackTrace();
      }
    }
    
     }
       
}
