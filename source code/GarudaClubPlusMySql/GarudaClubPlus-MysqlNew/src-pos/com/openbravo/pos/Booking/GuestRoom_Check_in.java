package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.AdvanceAdjustTableModel.MyAbstractTableModel2;

import com.openbravo.pos.Booking.GuestRoomCheckInTable.GuestRoom_CheckIn_Info;
import com.openbravo.pos.Booking.GuestRoomService.MyAbstractTableModel;
//import com.openbravo.pos.Booking.GuestRoomService.MyAbstractTableModel;

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
import java.awt.ComponentOrientation;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;



public class GuestRoom_Check_in extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private AdvanceRecieveTableModel AdvanceRecv;
    private CheckInTableModel CheckInTable_Model;
    private GuestRoomBillModel GRBillModel;
    java.util.List<Object> Mem_list = new ArrayList<Object>();
    java.util.List<Object> Guest_list = new ArrayList<Object>();
    private ComboBoxValModel Memlist_model ;
    private ComboBoxValModel GuestList_model;
    private List<CheckInTableModel.RoomAdvInfo> GuestRoomDetailList;
    private List<GuestRoomBillModel.RoomAdvInfo> GR_BillList;
    
    private List<CheckInTableModel.RoomTariffInfo> GuestRoomTariffDetailList;
    private List<Object> roomType_list ;
    private List<Object> roomNo_List;
    private ComboBoxValModel RoomTypeListModel;
    private ComboBoxValModel RoomTypeListModel2;
    private ComboBoxValModel RoomNoListModel ;
    private GuestRoomTableModel RoomTableModel; 
    private List<Object> Room_Nos_list = new ArrayList<Object>();
    private Room_Nos_Model RoomNoListModel2; 
    private Chk_out_Guest_model chk_out_guest_model;
    private Double Tot_Amount;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private GuestRoomCheckInTable GR_CheckIn_Detail ; 
    private GuestRoomService GRS;
    private GuestRoomService []GRS1;
    private List<GuestRoomService.BillInfo> BillInfoList;
    private List<GuestRoomService.BillInfo> BillInfoList_Table;
    private List<Object> Chk_out_Guest_list = new ArrayList<Object>();
    private DataLogicSales m_dlSales;
    private DataLogicFacilities dmang;  
    private List<AdvanceAdjustTableModel.AdvanceInfo> AdvanceInfoList;
    private AdvanceAdjustTableModel AdvanceAdjustTable_model;
    
    private MyAbstractTableModel RoomServTableModel;
    private MyAbstractTableModel2 AdvanceListTableModel; 
    
    private BookedRoomStatusTableModel Booked_room_status; 
     //added By pratima
    ArrayList Recpt_list = new ArrayList<String>();
    private ComboBoxValModel Recptlist_model ;
    private  int strict ;
    private  double strict1;
    double strictApprove;
    String strictApprove1;
       private static DataLogicSales dlSales;
     //end By pratima
    
    public GuestRoom_Check_in() {
        initComponents();
       
       
        amount_panel.setVisible(false);
        memNo.setVisible(false);
        MemNo_label.setVisible(false); 
        RType_combo2.setVisible(false);
        RT_Chng.setSelected(false);
        yes.setSelected(true);
        chk_panel.setVisible(false);
        tariff_panrl2.setVisible(false);
        memNo1.setVisible(false);
        jLabel3.setVisible(false);
        memNo2.setVisible(false);
        jLabel4.setVisible(false);
        bill_panel.setVisible(false);
        jTabbedPane1.setSelectedIndex(1);
        Bill_frame.setVisible(false);
        check_IN.setVisible(false);
        check_OUT.setVisible(false);
        chk_out_guest_panel.setVisible(false);
         jCheckBox1.setSelected(false);
      
         refund_bal_amt_text.setText("0.00");
        jCheckBox1.setSelected(false);
        billno_label.setVisible(false);
         //added By pratima
        RnoComboBox.setSelectedIndex(-1);   
        RNoLabel.setVisible(false);
        //end By pratima
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator6 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        main_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        tariff_panrl2 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        tot_tar2 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        AdvRec_label1 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        N_Room = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        totC_label1 = new javax.swing.JLabel();
        amount_panel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        AdvRec_label = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        totC_label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        due_label = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        chk_panel = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        e_checkin_text = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        e_checkout_date = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        RType_combo = new javax.swing.JComboBox();
        mem = new javax.swing.JRadioButton();
        nonmem = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        Gname_Combo = new javax.swing.JComboBox();
        memNo = new javax.swing.JLabel();
        MemNo_label = new javax.swing.JLabel();
        memNo1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        memNo2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        BillName = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        id_detail = new javax.swing.JTextField();
        RT_Chng = new javax.swing.JCheckBox();
        RType_combo2 = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        RNo_Combo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        enter = new javax.swing.JButton();
        mem_flag_label = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        yes = new javax.swing.JRadioButton();
        no = new javax.swing.JRadioButton();
        check_IN = new javax.swing.JLabel();
        check_OUT = new javax.swing.JLabel();
        booking_id_label = new javax.swing.JLabel();
        RnoComboBox = new javax.swing.JComboBox<>();
        RNoLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        jButton6 = new javax.swing.JButton();
        chk_out_guest_panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bill_panel = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mno = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        guest_name_label = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        Pay_btn = new javax.swing.JButton();
        createBill_Button = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        roomNo_Label1 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        mem_name_label = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        room_type_label = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        Biiling_name_label = new javax.swing.JLabel();
        bill_id_label = new javax.swing.JLabel();
        link_name_label = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        bkng_seq_no_label = new javax.swing.JLabel();
        tax1_id_label = new javax.swing.JLabel();
        tax2_id_label = new javax.swing.JLabel();
        tax3_id_label = new javax.swing.JLabel();
        tax_cnt_label = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        advnce_recv = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        dueamnt = new javax.swing.JLabel();
        bal_amount_label = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Roomservice_table = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel15 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date2 = new javax.swing.JLabel();
        days = new javax.swing.JLabel();
        rate = new javax.swing.JLabel();
        charges = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        tax1_lebel = new javax.swing.JLabel();
        tax2_Label = new javax.swing.JLabel();
        tax3_Label = new javax.swing.JLabel();
        Tax_rate1_label = new javax.swing.JLabel();
        Tax_rate2_label = new javax.swing.JLabel();
        Tax_rate3_label = new javax.swing.JLabel();
        tax1_amt_label = new javax.swing.JLabel();
        tax2_amt_label = new javax.swing.JLabel();
        tax3_amt_label = new javax.swing.JLabel();
        basic_tax_label1 = new javax.swing.JLabel();
        basic_tax_label2 = new javax.swing.JLabel();
        basic_tax_label3 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        Discount_Amt_label = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        Discount_Amt_label_Total = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        advance_recv_table = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jSeparator14 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        roomCharges = new javax.swing.JTextField();
        tot = new javax.swing.JTextField();
        totalAmtAdjusted = new javax.swing.JTextField();
        Tot_Bal_amt_text = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        refund_bal_amt_text = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        tot_chrge = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        Adjust_billedAmt_check = new javax.swing.JCheckBox();
        billno_label = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        unadjustTotal_text = new javax.swing.JTextField();
        generateFinalReport_btn = new javax.swing.JButton();
        Check_bill_Btn = new javax.swing.JButton();
        discount_Btn = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Bill_frame = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
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
        jLabel47 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        roomNo_Label = new javax.swing.JLabel();

        save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save.setForeground(new java.awt.Color(0, 51, 51));
        save.setText("Submit");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cancel.setForeground(new java.awt.Color(102, 0, 0));
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        tariff_panrl2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("New Tariff :");

        tot_tar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tot_tar2.setText("Unknown");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Advance Recieved :");

        AdvRec_label1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AdvRec_label1.setText("Unknown");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("New Room Type :");

        N_Room.setText("jLabel3");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Room Type :");

        jLabel21.setText("jLabel20");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Curr Tariff : ");

        totC_label1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totC_label1.setText("Unknown");

        javax.swing.GroupLayout tariff_panrl2Layout = new javax.swing.GroupLayout(tariff_panrl2);
        tariff_panrl2.setLayout(tariff_panrl2Layout);
        tariff_panrl2Layout.setHorizontalGroup(
            tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_panrl2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tariff_panrl2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(26, 26, 26)
                        .addComponent(AdvRec_label1))
                    .addGroup(tariff_panrl2Layout.createSequentialGroup()
                        .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(37, 37, 37)
                        .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totC_label1)
                            .addComponent(jLabel21)
                            .addComponent(N_Room)
                            .addComponent(tot_tar2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tariff_panrl2Layout.setVerticalGroup(
            tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_panrl2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(N_Room))
                .addGap(18, 18, 18)
                .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(totC_label1))
                .addGap(18, 18, 18)
                .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(tot_tar2))
                .addGap(18, 18, 18)
                .addGroup(tariff_panrl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(AdvRec_label1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tot_tar2.setForeground(Color.RED);
        AdvRec_label1.setForeground(Color.RED);
        N_Room.setForeground(Color.BLUE);
        jLabel21.setForeground(Color.BLUE);
        totC_label1.setForeground(Color.RED);

        amount_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Room Type :");

        jLabel20.setText("jLabel20");

        javax.swing.GroupLayout amount_panelLayout = new javax.swing.GroupLayout(amount_panel);
        amount_panel.setLayout(amount_panelLayout);
        amount_panelLayout.setHorizontalGroup(
            amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amount_panelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(amount_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(amount_panelLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(52, 52, 52)
                        .addComponent(due_label))
                    .addGroup(amount_panelLayout.createSequentialGroup()
                        .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(totC_label)
                            .addComponent(AdvRec_label))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        amount_panelLayout.setVerticalGroup(
            amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amount_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(totC_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(AdvRec_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amount_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(due_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AdvRec_label.setForeground(Color.RED);
        totC_label.setForeground(Color.RED);
        due_label.setForeground(Color.RED);
        jLabel20.setForeground(Color.BLUE);

        chk_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Check In Date :");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Expected Check Out :");

        javax.swing.GroupLayout chk_panelLayout = new javax.swing.GroupLayout(chk_panel);
        chk_panel.setLayout(chk_panelLayout);
        chk_panelLayout.setHorizontalGroup(
            chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chk_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25))
                .addGap(21, 21, 21)
                .addGroup(chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chk_panelLayout.createSequentialGroup()
                        .addComponent(e_checkin_text, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chk_panelLayout.createSequentialGroup()
                        .addComponent(e_checkout_date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        chk_panelLayout.setVerticalGroup(
            chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chk_panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(e_checkin_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(chk_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(e_checkout_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        e_checkin_text.setEditable(false);
        e_checkout_date.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Room Type :");

        RType_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        RType_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RType_comboItemStateChanged(evt);
            }
        });
        RType_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RType_comboActionPerformed(evt);
            }
        });

        mem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem.setText("Member");
        mem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memItemStateChanged(evt);
            }
        });
        mem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memActionPerformed(evt);
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
        Gname_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gname_ComboActionPerformed(evt);
            }
        });

        memNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        memNo.setText("Member No :");

        MemNo_label.setText("Mem No");

        memNo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        memNo1.setText("No Of Days :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("jLabel3");

        memNo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        memNo2.setText("No Of Rooms Booked :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Billing Name :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("ID Card Detail : ");

        RT_Chng.setText("Do You Want to Change Room Type ?");
        RT_Chng.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RT_ChngItemStateChanged(evt);
            }
        });

        RType_combo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        RType_combo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RType_combo2ItemStateChanged(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Room No :");

        RNo_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        enter.setText("Enter");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });

        mem_flag_label.setText("jLabel5");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 153));
        jLabel45.setText("Guest Room Check In Menu");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Effective Check In Date :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Effective Check Out Date :");

        jLabel1.setText("Do you want to continue with the same date ?");

        yes.setText("Yes");
        yes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yesItemStateChanged(evt);
            }
        });

        no.setText("No");
        no.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                noItemStateChanged(evt);
            }
        });

        check_IN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_IN.setText("jLabel42");

        check_OUT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_OUT.setText("jLabel42");

        booking_id_label.setText("booking_id");

        RnoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        RnoComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RnoComboBoxItemStateChanged(evt);
            }
        });
        RnoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RnoComboBoxActionPerformed(evt);
            }
        });

        RNoLabel.setText("Receipt No");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(RType_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(RType_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(RNo_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(20, 20, 20)))
                                                .addGap(38, 38, 38)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(RT_Chng, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mem_flag_label)
                                .addGap(63, 63, 63)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(memNo2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel27)
                                                .addGap(28, 28, 28))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel28)
                                                .addGap(12, 12, 12)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(id_detail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BillName)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(RNoLabel)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(memNo)
                                                .addComponent(memNo1)))
                                        .addGap(79, 79, 79)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(MemNo_label))))
                                .addGap(9, 9, 9))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel31))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check_OUT)
                                    .addComponent(check_IN))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(yes)
                                        .addGap(18, 18, 18)
                                        .addComponent(no)
                                        .addGap(11, 11, 11))
                                    .addComponent(chk_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(225, 225, 225))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(booking_id_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(amount_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tariff_panrl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 89, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 640, Short.MAX_VALUE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(mem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nonmem, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(212, 212, 212))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(RnoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Gname_Combo, 0, 175, Short.MAX_VALUE))
                                .addGap(81, 81, 81))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mem)
                    .addComponent(nonmem))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(memNo1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(RT_Chng)
                        .addGap(10, 10, 10)
                        .addComponent(RType_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Gname_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RType_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(RnoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RNoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(memNo)
                            .addComponent(MemNo_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memNo2)
                    .addComponent(jLabel4))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(RNo_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enter)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check_IN))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(check_OUT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(yes)
                            .addComponent(no))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chk_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(booking_id_label))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(BillName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(id_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mem_flag_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(amount_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tariff_panrl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save)
                    .addComponent(Cancel))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        RType_combo.setEditable(false);
        RType_combo.setEnabled(false);
        MemNo_label.setForeground(Color.RED);
        jLabel3.setForeground(Color.BLUE);
        jLabel4.setForeground(Color.BLUE);
        RT_Chng.setVisible(false);
        mem_flag_label.setVisible(false);
        jLabel45.setForeground(Color.BLUE);
        check_IN.setForeground(Color.RED);
        check_OUT.setForeground(Color.RED);
        booking_id_label.setVisible(false);

        jTabbedPane1.addTab("Check In", jPanel4);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("Room Service ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(153, 0, 0));
        jButton6.setText("Check Out ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        chk_out_guest_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setBackground(new java.awt.Color(255, 51, 102));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Guest to be check out today  :  ");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList2);

        javax.swing.GroupLayout chk_out_guest_panelLayout = new javax.swing.GroupLayout(chk_out_guest_panel);
        chk_out_guest_panel.setLayout(chk_out_guest_panelLayout);
        chk_out_guest_panelLayout.setHorizontalGroup(
            chk_out_guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chk_out_guest_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        chk_out_guest_panelLayout.setVerticalGroup(
            chk_out_guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chk_out_guest_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chk_out_guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setForeground(Color.RED);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 153));
        jButton3.setText("Bill RePrint");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 153));
        jButton7.setText("Bill Statement Reprint");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText("Extra Billing");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chk_out_guest_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addGap(6, 6, 6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(chk_out_guest_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/view2.png"))); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N

        jTabbedPane1.addTab("Checked  IN List", jPanel1);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setText("Bill Menu");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Member No : ");

        mno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mno.setText("jLabel11");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Guest Name :");

        guest_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guest_name_label.setText("GName");

        cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(153, 0, 51));
        cancel.setText("Back");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        Pay_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Pay_btn.setForeground(new java.awt.Color(153, 0, 0));
        Pay_btn.setText("Adjust Bill");
        Pay_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pay_btnActionPerformed(evt);
            }
        });

        createBill_Button.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        createBill_Button.setForeground(new java.awt.Color(0, 0, 153));
        createBill_Button.setText("Create Bill");
        createBill_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBill_ButtonActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Room No : ");

        roomNo_Label1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        roomNo_Label1.setText("jLabel41");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Member Name : ");

        mem_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem_name_label.setText("jLabel11");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Room Type : ");

        room_type_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        room_type_label.setText("jLabel41");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Billing Name :");

        Biiling_name_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Biiling_name_label.setText("Bill Name");

        bill_id_label.setText("jLabel48");

        link_name_label.setText("jLabel48");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Booking Id : ");

        bkng_seq_no_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bkng_seq_no_label.setText("booking_id");

        tax1_id_label.setText("tax1_id");

        tax2_id_label.setText("tax2_id");

        tax3_id_label.setText("tax3_id");

        tax_cnt_label.setText("tax_cnt");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("Pending Bills ");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Total ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setText("Advance Recieved");

        advnce_recv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        advnce_recv.setText("Amt6");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Total Bal Amt :");

        dueamnt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dueamnt.setForeground(new java.awt.Color(0, 0, 153));
        dueamnt.setText("Total Bal Amt :");

        bal_amount_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bal_amount_label.setText("Amt7");

        Roomservice_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(Roomservice_table);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Total ");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Room Bill ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Days");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Rate");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Amount");

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Taxes");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("From : ");

        date1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        date1.setText("Date1");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("To : ");

        date2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        date2.setText("Date2");

        days.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        days.setText("Days");

        rate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rate.setText("Rate");

        charges.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        charges.setText("Amt1");

        jLabel50.setText("(1)");

        jLabel51.setText("(2)");

        jLabel52.setText("(3)");

        tax1_lebel.setText("Tax1");

        tax2_Label.setText("Tax2");

        tax3_Label.setText("Tax3");

        Tax_rate1_label.setText("Tax_rate1");

        Tax_rate2_label.setText("Tax_rate2");

        Tax_rate3_label.setText("Tax_rate3");

        tax1_amt_label.setText("T_AMT1");

        tax2_amt_label.setText("T_AMT2");

        tax3_amt_label.setText("T_AMT3");

        basic_tax_label1.setText("(B)");

        basic_tax_label2.setText("(B)");

        basic_tax_label3.setText("(B)");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Discount :");

        Discount_Amt_label.setText("D_Amt");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Total :");

        Discount_Amt_label_Total.setText("D_Amt");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel51)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tax2_Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(basic_tax_label2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel50)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tax1_lebel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(basic_tax_label1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel52)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tax3_Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(basic_tax_label3))))
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(days)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rate)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Tax_rate2_label)
                                    .addComponent(Tax_rate1_label)
                                    .addComponent(Tax_rate3_label))
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tax2_amt_label)
                            .addComponent(tax1_amt_label)
                            .addComponent(tax3_amt_label)
                            .addComponent(Discount_Amt_label)
                            .addComponent(charges)
                            .addComponent(Discount_Amt_label_Total))
                        .addGap(42, 42, 42)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(date1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(date2))
                        .addGap(22, 22, 22)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel56)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel50)
                            .addComponent(tax1_lebel)
                            .addComponent(basic_tax_label1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(tax2_Label)
                            .addComponent(basic_tax_label2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(tax3_Label)
                            .addComponent(basic_tax_label3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rate)
                        .addGap(134, 134, 134)
                        .addComponent(Tax_rate1_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tax_rate2_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Tax_rate3_label))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(charges)
                        .addGap(54, 54, 54)
                        .addComponent(Discount_Amt_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Discount_Amt_label_Total)
                        .addGap(37, 37, 37)
                        .addComponent(tax1_amt_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tax2_amt_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tax3_amt_label))
                    .addComponent(days))
                .addContainerGap(42, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addComponent(jSeparator4)
            .addComponent(jSeparator3)
        );

        date1.setForeground(Color.BLUE);
        date2.setForeground(Color.BLUE);
        charges.setForeground(Color.BLUE);
        tax1_amt_label.setForeground(Color.BLUE);
        tax2_amt_label.setForeground(Color.BLUE);
        tax3_amt_label.setForeground(Color.BLUE);
        Discount_Amt_label.setForeground(Color.BLUE);
        Discount_Amt_label_Total.setForeground(Color.BLUE);

        jScrollPane6.setViewportView(jPanel2);

        jSeparator12.setForeground(new java.awt.Color(0, 0, 153));

        jSeparator13.setForeground(new java.awt.Color(0, 0, 153));

        advance_recv_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(advance_recv_table);

        jSeparator14.setForeground(new java.awt.Color(0, 51, 204));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 153));
        jLabel54.setText("Total :");

        roomCharges.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        roomCharges.setForeground(new java.awt.Color(153, 0, 0));
        roomCharges.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        roomCharges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomChargesActionPerformed(evt);
            }
        });

        tot.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tot.setForeground(new java.awt.Color(102, 0, 0));
        tot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        totalAmtAdjusted.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totalAmtAdjusted.setForeground(new java.awt.Color(153, 0, 0));

        Tot_Bal_amt_text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Tot_Bal_amt_text.setForeground(new java.awt.Color(102, 0, 0));

        jCheckBox1.setText("Refund Balance Amount : ");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(153, 0, 0));
        jLabel53.setText("Total Billed Amt : ");

        tot_chrge.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tot_chrge.setForeground(new java.awt.Color(153, 0, 0));

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
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        Adjust_billedAmt_check.setText("Adjust");
        Adjust_billedAmt_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Adjust_billedAmt_checkItemStateChanged(evt);
            }
        });

        billno_label.setText("billno");

        jLabel18.setText("Total Unadjusted Amount : ");

        unadjustTotal_text.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        generateFinalReport_btn.setText("Generate Final Bill Statement");
        generateFinalReport_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateFinalReport_btnActionPerformed(evt);
            }
        });

        Check_bill_Btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Check_bill_Btn.setForeground(new java.awt.Color(0, 0, 153));
        Check_bill_Btn.setText("Check Bill");
        Check_bill_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Check_bill_BtnActionPerformed(evt);
            }
        });

        discount_Btn.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        discount_Btn.setForeground(new java.awt.Color(153, 0, 51));
        discount_Btn.setText("Give Discount");
        discount_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discount_BtnActionPerformed(evt);
            }
        });

        jLabel57.setText("GstNo");

        jTextField1.setEditable(false);

        javax.swing.GroupLayout bill_panelLayout = new javax.swing.GroupLayout(bill_panel);
        bill_panel.setLayout(bill_panelLayout);
        bill_panelLayout.setHorizontalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tot_chrge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator12)
                                        .addComponent(jLabel19)
                                        .addGroup(bill_panelLayout.createSequentialGroup()
                                            .addComponent(jCheckBox1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(refund_bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(bill_panelLayout.createSequentialGroup()
                                            .addComponent(jLabel48)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Tot_Bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(198, 198, 198)
                                            .addComponent(jLabel54)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(totalAmtAdjusted, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel16)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(bill_panelLayout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(unadjustTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(roomCharges, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane5)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Adjust_billedAmt_check)
                                        .addGap(29, 29, 29)
                                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bill_id_label)
                                                .addComponent(tax_cnt_label)
                                                .addComponent(tax2_id_label)
                                                .addComponent(tax3_id_label)
                                                .addComponent(tax1_id_label)
                                                .addComponent(advnce_recv)
                                                .addComponent(bal_amount_label)
                                                .addComponent(billno_label))
                                            .addGroup(bill_panelLayout.createSequentialGroup()
                                                .addComponent(link_name_label)
                                                .addGap(4, 4, 4)))
                                        .addGap(103, 103, 103))
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(discount_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Check_bill_Btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(createBill_Button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Pay_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(generateFinalReport_btn)
                                            .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mem_name_label)
                            .addComponent(mno))
                        .addGap(99, 99, 99)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel41))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roomNo_Label1)
                                    .addComponent(room_type_label))
                                .addGap(109, 109, 109)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel14)))
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bkng_seq_no_label)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(guest_name_label)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(Biiling_name_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGap(457, 457, 457)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136)))
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bill_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dueamnt)
                .addGap(112, 112, 112))
        );
        bill_panelLayout.setVerticalGroup(
            bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bill_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(mno)
                    .addComponent(jLabel14)
                    .addComponent(guest_name_label)
                    .addComponent(jLabel41)
                    .addComponent(roomNo_Label1)
                    .addComponent(jLabel57)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(mem_name_label)
                    .addComponent(jLabel43)
                    .addComponent(room_type_label)
                    .addComponent(jLabel49)
                    .addComponent(bkng_seq_no_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(Biiling_name_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addComponent(discount_Btn)
                                .addGap(16, 16, 16)
                                .addComponent(Check_bill_Btn)
                                .addGap(39, 39, 39)
                                .addComponent(createBill_Button)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53)
                            .addComponent(tot_chrge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Pay_btn))
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(roomCharges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(advnce_recv)
                                    .addComponent(jLabel18)
                                    .addComponent(unadjustTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(tax1_id_label)
                                    .addComponent(Adjust_billedAmt_check))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(generateFinalReport_btn)
                                            .addGroup(bill_panelLayout.createSequentialGroup()
                                                .addComponent(bill_id_label)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tax_cnt_label)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax2_id_label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax3_id_label)))
                                .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(link_name_label))
                                    .addGroup(bill_panelLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel54)
                                            .addComponent(totalAmtAdjusted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel48)
                                            .addComponent(Tot_Bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cancel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bill_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jCheckBox1)
                                            .addComponent(refund_bal_amt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(bill_panelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(billno_label)
                                .addGap(18, 18, 18)
                                .addComponent(bal_amount_label)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addComponent(dueamnt))
                    .addGroup(bill_panelLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel45.setForeground(Color.BLUE);
        mno.setForeground(Color.BLUE);
        guest_name_label.setForeground(Color.BLUE);
        roomNo_Label1.setForeground(Color.BLUE);
        mem_name_label.setForeground(Color.BLUE);
        room_type_label.setForeground(Color.BLUE);
        Biiling_name_label.setForeground(Color.BLUE);
        bill_id_label.setVisible(false);
        link_name_label.setVisible(false);
        bkng_seq_no_label.setForeground(Color.BLUE);
        tax1_id_label.setVisible(false);
        tax2_id_label.setVisible(false);
        tax3_id_label.setVisible(false);
        tax_cnt_label.setVisible(false);
        advnce_recv.setForeground(Color.RED);
        advnce_recv.setVisible(false);
        dueamnt.setVisible(false);
        bal_amount_label.setForeground(Color.RED);
        bal_amount_label.setVisible(false);
        roomCharges.setEditable(false);
        tot.setEditable(false);
        totalAmtAdjusted.setEditable(false);
        Tot_Bal_amt_text.setEditable(false);
        refund_bal_amt_text.setEditable(false);
        tot_chrge.setEditable(false);
        Adjust_billedAmt_check.setVisible(false);
        unadjustTotal_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        unadjustTotal_text.setEditable(false);

        Bill_frame.setTitle("Room Service");
        Bill_frame.setVisible(true);

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
        jScrollPane3.setViewportView(jTable2);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 255));
        jLabel47.setText("Room Service");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 0, 0));
        jButton8.setText("Cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Room No : ");

        roomNo_Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        roomNo_Label.setText("jLabel41");

        javax.swing.GroupLayout Bill_frameLayout = new javax.swing.GroupLayout(Bill_frame.getContentPane());
        Bill_frame.getContentPane().setLayout(Bill_frameLayout);
        Bill_frameLayout.setHorizontalGroup(
            Bill_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bill_frameLayout.createSequentialGroup()
                .addGroup(Bill_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Bill_frameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Bill_frameLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Bill_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Bill_frameLayout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roomNo_Label))
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Bill_frameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        Bill_frameLayout.setVerticalGroup(
            Bill_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bill_frameLayout.createSequentialGroup()
                .addContainerGap(264, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addGroup(Bill_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(roomNo_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8))
        );

        jLabel45.setForeground(Color.BLUE);
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        roomNo_Label.setForeground(Color.BLUE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bill_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(bill_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bill_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(RType_combo.getSelectedIndex()!=-1){
         
              
                   String roomType = RType_combo.getSelectedItem().toString();
                   
                   
                  int Advance_book_dura =0;
                  Advance_book_dura = CheckInTable_Model.getAdvancePayDura(m_App, roomType);
                  System.out.println(Advance_book_dura) ;

                  Date Currdate = new Date();
                  Calendar c = Calendar.getInstance();
                  c.setTimeInMillis(new Date().getTime());
                  c.add(Calendar.DATE, -1);
                  Currdate = c.getTime();
                  
                  
                  Date date=new Date();
                  Calendar call = Calendar.getInstance();
                  call.setTimeInMillis(new Date().getTime());
                  call.add(Calendar.MONTH, Advance_book_dura);
                  Date afterDate = call.getTime();
                    try {
                        date = (Date) Formats.TIMESTAMP.parseValue(e_checkin_text.getText());
                    } catch (BasicException e) {
                        date = null;
                    }
                      try{
                    date = JCalendarDialog.showCalendarTimeHours(this, date);
                    if (date != null) {
                        if(date.before(Currdate))
                        {
                            JOptionPane.showMessageDialog(this, "Date Selected is not available... Please Select again..!!");

                        }
                        else if(date.after(afterDate))
                        {
                           JOptionPane.showMessageDialog(this, "Booking is available upto "+Advance_book_dura+" Monts only..!!! ");
                        }

                        else{
                                
                            
                                   
                           e_checkin_text.setText(Formats.TIMESTAMP.formatValue(date));
                           
                           e_checkout_date.setText(null);
                                    
                         
                        }

                    }
          }
        catch(Exception e1){
              e1.printStackTrace();
          }
          
       
          
       }
     
     else{
         JOptionPane.showMessageDialog(this, " Select Room Type   ", " hall", JOptionPane.ERROR_MESSAGE);
         
     }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(RType_combo.getSelectedIndex()!=-1){
            if(e_checkin_text.getText()!=null && e_checkin_text.getText().trim().length()>0){
                
                String Roomtype = RType_combo.getSelectedItem().toString();
                Date Check_in_DATE = new Date();
                Date Check_out_DATE = new Date();
                int Max_Days = CheckInTable_Model.getMaxDaysDuration(m_App, Roomtype);
                
                try {
                    Check_in_DATE = (Date) Formats.TIMESTAMP.parseValue(e_checkin_text.getText());
                    Check_out_DATE = (Date) Formats.TIMESTAMP.parseValue(e_checkout_date.getText());
                } catch (BasicException ex) {
                     ex.printStackTrace();
                     new MessageInf(ex).show(this);
                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Calendar call = Calendar.getInstance();
                call.setTimeInMillis(Check_in_DATE.getTime());
                call.add(Calendar.DATE,Max_Days );
                Date afterDate = call.getTime();
                
                
                try{
                    Check_out_DATE = JCalendarDialog.showCalendarTimeHours(this, Check_in_DATE);
                    if (Check_out_DATE != null) {
                        if(Check_out_DATE.before(Check_in_DATE))
                        {
                            JOptionPane.showMessageDialog(this, "Date Selected Should be after Check IN date..!!");

                        }
                        else if(Check_out_DATE.after(afterDate))
                        {
                           JOptionPane.showMessageDialog(this, "Booking is available upto "+Max_Days+" Days only..!!! ");
                        }

                        else{
                               int no_of_days = (int) (Check_out_DATE.getTime() - Check_in_DATE.getTime())/(1000 * 60 * 60 * 24);
                               String Room_ID = CheckInTable_Model.getRoomID(m_App, Roomtype);
                               Date d = Check_in_DATE;
                               int Booked_rooms;
                               int rooms_available;
                               int no_of_rooms_to_be_booked = 1;
                               int total_rooms = CheckInTable_Model.getTotalRoomsAvailable(m_App, Roomtype);
                               
                               for(int i=1;i<=no_of_days;i++){
                                    Calendar c = Calendar.getInstance(); 
                                    c.setTime(d);
                                    c.add(Calendar.DATE, i-1);
                                    d = c.getTime();
                                    
                                    Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_ID , d);
                                    rooms_available = total_rooms - Booked_rooms;
                                    if(rooms_available>=no_of_rooms_to_be_booked){
                                       e_checkout_date.setText(Formats.TIMESTAMP.formatValue(Check_out_DATE));
                                       
                                    
                                    }
                                    else{
                                        
                                        JOptionPane.showMessageDialog(this, " Sorry, Room is already booked on "+Formats.DATE.formatValue(d)+".!! "
                                                + " Only "+ rooms_available +" Rooms are available..!", " hall", JOptionPane.ERROR_MESSAGE);
                                        e_checkin_text.setText(null);
                                        e_checkout_date.setText(null);
                                        break;
                                    }
                                    
                               }
                               
                              
                        }

                    }
          }
        catch(Exception e1){
              e1.printStackTrace();
          }
                
                
                
                
                
                
                
            }
            else{
                JOptionPane.showMessageDialog(this, " Select Check In Date First..!!  ", " hall", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
         JOptionPane.showMessageDialog(this, " Select Room Type   ", " hall", JOptionPane.ERROR_MESSAGE);
         
     }
                                            
    }//GEN-LAST:event_jButton5ActionPerformed

    int No_of_rooms_Bkd;
    int No_of_days;
    int MemberFlag;
    String GuestName;
    String Mname;
    String Mno;
    String RoomNo="";
    String Billname;
    String IdCard;
    String Rtype;
    Date chk_In_date;
    Date Chk_out_date;
    Double AdvanceAmt;
    Double DueAmt;
    Date E_chk_in;
    Date E_chk_out;
    Double RoomSerChrg;
    String RECIEPT_NO;
    String Advnce_Recv_ID;
    int Active;
    String Adv_Pmt_ID;
    Double SingleRoom_Tariff;
    String Link_Name;
    String booking_id;
    String RoomType_ID;
    String Booking_seq_id;
    int Tax_count=0;
    int Approved=1;
    Double Min_CheckInAmt = 0.00;
    Double Total_AdvanceRecv = 0.00;
    Date  Bill_Check_in;
    Date Bill_Check_out;
    Double Check_In_TimeDiff;
    Double Check_Out_TimeDiff;
    Double Discount_Amt = 0.00;
    
    int DISC_FLAG;
    
    
    private void Gname_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Gname_ComboItemStateChanged
        int memFlag;
        RT_Chng.setSelected(false);
        
        if(Gname_Combo.getSelectedIndex()==-1){
             RnoComboBox.setVisible(false);
             RNoLabel.setVisible(false);
        }
            //ended by pratima
        
        
        if(Gname_Combo.getSelectedIndex()!=-1){
            
            if(mem.isSelected()){
                String Name  = Gname_Combo.getSelectedItem().toString();
                memFlag = 1;
               
                try {
                    CheckInTable_Model = CheckInTableModel.LoadGuestRoomCheckInDetail(m_App, Name , memFlag);
                } catch (BasicException ex) {
                     ex.printStackTrace();
                     new MessageInf(ex).show(this);
                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                String Name  = Gname_Combo.getSelectedItem().toString(); 
                memFlag = 2;
                try {
                    CheckInTable_Model = CheckInTableModel.LoadGuestRoomCheckInDetail(m_App, Name , memFlag);
                } catch (BasicException ex) {
                     ex.printStackTrace();
                     new MessageInf(ex).show(this);
                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
            
            GuestRoomDetailList =  (List<CheckInTableModel.RoomAdvInfo>) CheckInTable_Model.getGuestRoomPath();
             //by pratima :code to display all reciept at same member name 
             if( GuestRoomDetailList.size()>1){
                  RnoComboBox.setVisible(true);
                  RNoLabel.setVisible(true);
             }
             else{
            RnoComboBox.setVisible(false);
            RNoLabel.setVisible(false);
            //ended By Pratima
            CheckInTableModel.RoomAdvInfo editData = (CheckInTableModel.RoomAdvInfo) ((CheckInTableModel.RoomAdvInfo)GuestRoomDetailList.get(0));
            
            
            e_checkin_text.setText(editData.getCHECK_IN_DATE());
            check_IN.setText(editData.getCHECK_IN_DATE());
            check_OUT.setText(editData.getCHECK_OUT_DATE());
            e_checkout_date.setText(editData.getCHECK_OUT_DATE());
            check_IN.setVisible(true);
            check_OUT.setVisible(true);
            try {
                chk_In_date = (Date) Formats.TIMESTAMP.parseValue(check_IN.getText());
                Chk_out_date = (Date) Formats.TIMESTAMP.parseValue(check_OUT.getText());
                
                E_chk_in = (Date) Formats.TIMESTAMP.parseValue(e_checkin_text.getText());
                E_chk_out = (Date) Formats.TIMESTAMP.parseValue(e_checkout_date.getText());
                
            } catch (BasicException ex) {
                 ex.printStackTrace();
                     new MessageInf(ex).show(this);
                Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String Booking_ID = editData.getBOOKING_ID();
            booking_id_label.setText(Booking_ID);
            
            
            AdvRec_label.setText(decimalFormat.format(editData.getBAL_AMT()));
            AdvanceAmt = Double.parseDouble(AdvRec_label.getText());
            
            MemNo_label.setText(editData.getMEMBER_NO());
            Mno = MemNo_label.getText();
            Booking_seq_id = editData.getBOOKING_SEQ_NO();
            
            Rtype = editData.getROOMTYPE();
            Adv_Pmt_ID = editData.getId();
            totC_label.setText(decimalFormat.format(editData.getTOTAL_AMOUNT()));
            Tot_Amount = Double.parseDouble(totC_label.getText());
            
            totC_label1.setText(decimalFormat.format(editData.getTOTAL_AMOUNT()));
            
            due_label.setText(decimalFormat.format((editData.getTOTAL_AMOUNT())-(editData.getBAL_AMT())));
            DueAmt = Double.parseDouble(due_label.getText());
            
            RECIEPT_NO = editData.getRECIEPT_NO();
            
            Advnce_Recv_ID = editData.getId();
            
            No_of_rooms_Bkd = editData.getNO_OF_ROOMS();
            
            No_of_days = editData.getBOOKING_DAYS();
            
            
            MemberFlag = editData.getMEMBER_FLAG();
            jLabel20.setText(editData.getROOMTYPE());
            jLabel21.setText(editData.getROOMTYPE());
            AdvRec_label1.setText(decimalFormat.format(editData.getBAL_AMT()));
            
            if(DueAmt >=0){
               jLabel33.setText("Total Amount Payable :");
            }
            else{
                jLabel33.setText("Total Due Amount :");
            }
            
            
            if(editData.getMEMBER_FLAG()!=1){
               GuestName = editData.getGuestName();
               Mname = editData.getMEMBERNAME();
            }
            else{
                GuestName = null;
                Mname = editData.getMEMBERNAME();
            }
            
            for(int i=0;i<RType_combo.getItemCount();i++){
                String x = RType_combo.getItemAt(i).toString();
                if(x.equals(Rtype)){
                    RType_combo.setSelectedIndex(i);
                    break;
                }
            }
            
            jLabel3.setText(""+No_of_days);
            jLabel4.setText(""+No_of_rooms_Bkd);
            mem_flag_label.setText(""+MemberFlag);
            
            // CALCULATION FOR SINGLE ROOM TARIFF
            
            if(editData.getMEMBER_FLAG()==1){
                SingleRoom_Tariff = editData.getMEM_Tariff();
            }
            else{
                SingleRoom_Tariff = editData.getN_MEM_Tariff();
            }
            
            
            Min_CheckInAmt = 0.00;
            Total_AdvanceRecv = editData.getADVANCE_RECV();
            
            String ADVANCE_PERC = editData.getADVANCE_PERC();
            String Advance_Array[] = ADVANCE_PERC.split("-");
            Double bkng_perc =0.00;
            Double Check_in_perc = 0.00;
            
            bkng_perc = Double.parseDouble(Advance_Array[0]);
            Check_in_perc = Double.parseDouble(Advance_Array[1]);
            
            Double Total_Perc = bkng_perc+Check_in_perc;
          
            
            
            Min_CheckInAmt = ((Total_Perc*Tot_Amount)/100);
             
            System.out.println(Min_CheckInAmt);
            
            
            
            
            // PANELS TO KEEP INVISIBLE
           
            amount_panel.setVisible(true);
            memNo.setVisible(true);
            MemNo_label.setVisible(true);
            memNo1.setVisible(true);
            jLabel3.setVisible(true);
            memNo2.setVisible(true);
            jLabel4.setVisible(true);
            
             }
             //added by pratima
             Recpt_list=new ArrayList<String>();
             for(int i=0;i<GuestRoomDetailList.size();i++){
             Recpt_list.add(GuestRoomDetailList.get(i).getRECIEPT_NO()) ;
             }
             Recptlist_model= new ComboBoxValModel(Recpt_list);
             RnoComboBox.setModel(Recptlist_model);
              //ended By Pratima
        }  
      else{
          
        memNo.setVisible(false);
        MemNo_label.setVisible(false);  
        amount_panel.setVisible(false);
        
        memNo1.setVisible(false);
        jLabel3.setVisible(false);
        memNo2.setVisible(false);
        jLabel4.setVisible(false);
        reset();
        
      }
    }//GEN-LAST:event_Gname_ComboItemStateChanged

    private void memItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memItemStateChanged
       reset();//by pratima
        Gname_Combo.setSelectedIndex(-1);
         try {
                  loaddata();
                } catch (BasicException ex) {
                     ex.printStackTrace();
                     new MessageInf(ex).show(this);
                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
     
    }//GEN-LAST:event_memItemStateChanged

    private void nonmemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nonmemItemStateChanged
        try {
            loaddata();
            reset();
        } catch (BasicException ex) {
            Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
     Gname_Combo.setSelectedIndex(-1);
      
    }//GEN-LAST:event_nonmemItemStateChanged

    private void RType_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RType_comboItemStateChanged
       String RoomNo;
       roomNo_List = new ArrayList<Object>();
       
      
       if(RType_combo.getSelectedIndex()!=-1){
       /* 
       String RoomType =  RType_combo.getSelectedItem().toString();
       RoomNo = RoomTableModel.getRoomNos(m_App,RoomType);
       String []RoomNo_Arr = RoomNo.split("#");
       for(int i=0;i<RoomNo_Arr.length;i++){
           Object o = RoomNo_Arr[i];
           roomNo_List.add(o);
       }
       */
       String RoomType = RType_combo.getSelectedItem().toString();
       roomNo_List = RoomTableModel.getRoomNos_Cust(m_App , RoomType);
       RoomNoListModel = new ComboBoxValModel(roomNo_List);
       RNo_Combo.setModel(RoomNoListModel);
       
    
     }
     else{
       RNo_Combo.setSelectedIndex(-1);
     }
     RType_combo2.setSelectedIndex(-1);
    }//GEN-LAST:event_RType_comboItemStateChanged

    private void RT_ChngItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RT_ChngItemStateChanged
       if(RT_Chng.isSelected()){
           if(Gname_Combo.getSelectedIndex()!=-1)
          {
           
           RType_combo2.setVisible(true);
           }
          else{
              
               JOptionPane.showMessageDialog(this, "Select Member first..!! ", " Warning ", JOptionPane.WARNING_MESSAGE);
          }
            
           
           
           
       }
       else{
          
           
        
          RType_combo2.setSelectedIndex(-1);
          RNo_Combo.setSelectedIndex(-1);
          RType_combo2.setVisible(false);
          tariff_panrl2.setVisible(false);
         
            String RoomNo;
            roomNo_List = new ArrayList<Object>();
            if(RType_combo.getSelectedIndex()!=-1){
                /*
            String RoomType =  RType_combo.getSelectedItem().toString();
            RoomNo = RoomTableModel.getRoomNos(m_App,RoomType);
            String []RoomNo_Arr = RoomNo.split("#");
            for(int i=0;i<RoomNo_Arr.length;i++){
                Object o = RoomNo_Arr[i];
                roomNo_List.add(o);
             }
             */
            String roomtype = RType_combo.getSelectedItem().toString();
            roomNo_List = RoomTableModel.getRoomNos_Cust(m_App ,roomtype );
            
            RoomNoListModel = new ComboBoxValModel(roomNo_List);
            RNo_Combo.setModel(RoomNoListModel);

            amount_panel.setVisible(true);
            }
            else{
            RNo_Combo.setSelectedIndex(-1);
            amount_panel.setVisible(false);
            }
            RType_combo2.setSelectedIndex(-1);
           
            tariff_panrl2.setVisible(false);
            
         
            
            
            
       }
    }//GEN-LAST:event_RT_ChngItemStateChanged

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
       reset();
       Gname_Combo.setSelectedIndex(-1);
       jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_CancelActionPerformed

    private void yesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yesItemStateChanged
       if(yes.isSelected()){
            chk_panel.setVisible(false);
            e_checkin_text.setText(null);
            e_checkout_date.setText(null);
       }
       else{
            chk_panel.setVisible(true);
       }
    }//GEN-LAST:event_yesItemStateChanged

    private void noItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_noItemStateChanged
       if(no.isSelected()){
            chk_panel.setVisible(true);
            e_checkin_text.setText(null);
            e_checkout_date.setText(null);
       }
       else{
            chk_panel.setVisible(false);
       }
    }//GEN-LAST:event_noItemStateChanged

    private void RType_combo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RType_combo2ItemStateChanged
       String RoomNo;
       roomNo_List = new ArrayList<Object>();
       
       if(RType_combo2.getSelectedIndex()!=-1){
           String RoomType =  RType_combo2.getSelectedItem().toString();
           roomNo_List = RoomTableModel.getRoomNos_Cust(m_App , RoomType);
             RoomNoListModel = new ComboBoxValModel(roomNo_List);
             RNo_Combo.setModel(RoomNoListModel);

             Double Amt=0.0;
             try {
                   Amt = getGuestRoomCharges(RoomType);
                 } catch (BasicException ex) {
                     Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                 }
             tariff_panrl2.setVisible(true);
             amount_panel.setVisible(false);
             tot_tar2.setText(decimalFormat.format(Amt));
             N_Room.setText(RoomType);
             jLabel3.setText(""+No_of_days);
             jLabel4.setText(""+No_of_rooms_Bkd);
             mem_flag_label.setText(""+MemberFlag);
             AdvRec_label1.setText(AdvRec_label.getText());
                     
                     
             if(Room_Nos_list.size()>0){
              Room_Nos_list = new ArrayList<Object>();
              RoomNoListModel2 = new Room_Nos_Model(Room_Nos_list);
              jList1.setModel(RoomNoListModel2);   
             }
             
             
             
     }
     else{
       RNo_Combo.setSelectedIndex(-1);
       tariff_panrl2.setVisible(false);
         
     }
    }//GEN-LAST:event_RType_combo2ItemStateChanged

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
       int count =0;
        if(RNo_Combo.getSelectedIndex()!=-1){
          enter.setVisible(true);
          String X =  RNo_Combo.getSelectedItem().toString();
          
          String GuestRoomLink = CheckInTable_Model.getGuestRoomLinkName(m_App, X);
          
          
          if(Room_Nos_list.size()>0){
              if(Room_Nos_list.size() < 1){
                    for(int i=0;i<Room_Nos_list.size();i++){
                          String x = Room_Nos_list.get(i).toString();
                          if(x.equals(X)){
                             count++;
                             break;
                         }
                    }
                      if(count==0){
                            Room_Nos_list.add(X);
                            RoomNoListModel2 = new Room_Nos_Model(Room_Nos_list);
                            jList1.setModel(RoomNoListModel2); 
                            JOptionPane.showMessageDialog(this, " Guest Room Account linked with this room is "+GuestRoomLink+".  \n \n Incase if you want to make any changes in link go to Guestroom Link Master menu.", "Warning", JOptionPane.WARNING_MESSAGE);
                            
                      }
                    else{
                        JOptionPane.showMessageDialog(this, "Room No already Entered..!!", " Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    }
             }
             else{
                   JOptionPane.showMessageDialog(this, "Only one room can be selected..!!", "Rooms Booked ", JOptionPane.WARNING_MESSAGE);
              }
          }
          else{
                Room_Nos_list.add(X);
                RoomNoListModel2 = new Room_Nos_Model(Room_Nos_list);
                jList1.setModel(RoomNoListModel2);  
                JOptionPane.showMessageDialog(this, " Guest Room Account linked with this room is '"+GuestRoomLink+"'.  \n \n Incase if you want to make any changes  , reffer 'Guestroom Link Master' menu.", "Warning", JOptionPane.WARNING_MESSAGE);
          }
          
       }
       else{
          
       }
    }//GEN-LAST:event_enterActionPerformed
    
   
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
     if(Gname_Combo.getSelectedIndex()!=-1){
              if(Room_Nos_list.size()>0){
                if(id_detail.getText() != null && id_detail.getText().trim().length()>0){
                    
                      
                    if( Min_CheckInAmt <= Total_AdvanceRecv )
                    {
                    
                     
                     Check_In_TimeDiff =   GR_CheckIn_Detail.getCheck_In_TimeDiff(m_App);
                     Check_Out_TimeDiff = GR_CheckIn_Detail.getCheck_Out_TimeDiff(m_App);
                        
                     Date CurrDate = new Date();
                     try {
                            E_chk_in = (Date) Formats.TIMESTAMP.parseValue(e_checkin_text.getText());
                           
                        } catch (BasicException ex) {
                            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                     }
                        
                     long diff = chk_In_date.getTime() - CurrDate.getTime() ;
                     long diffHours = diff / (60 * 60 * 1000);   
                     System.out.println(diffHours);
                     
                     
                     
                     //if( (Check_In_TimeDiff>=diffHours  && Check_In_TimeDiff >= (diffHours*-1)) || no.isSelected())
                     if(  Check_In_TimeDiff>=diffHours  || no.isSelected())
                     {
                         int checkoutComparision = Chk_out_date.compareTo(new Date());
                         if(checkoutComparision>0){
                         

                            int x = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Check In..?? " , "Check In Menu " , JOptionPane.YES_NO_OPTION);
                               if( x  == JOptionPane.YES_OPTION){


                            if(BillName.getText()!=null && BillName.getText().trim().length()>0){
                                Billname = BillName.getText();
                            } 
                            else{
                                 if(MemberFlag==1){
                                     Billname = Mname;
                                 }
                                 else{
                                      Billname  = GuestName;
                                 }
                            }



                            if(id_detail.getText()!=null && id_detail.getText().trim().length()>0){
                                IdCard = id_detail.getText();
                            }
                            RoomSerChrg = 0.00;

                            if(Room_Nos_list.size()>0){
                                for(int i=0;i<Room_Nos_list.size();i++){
                                    RoomNo = RoomNo+Room_Nos_list.get(i);
                                        if(i+1<Room_Nos_list.size()){
                                           RoomNo = RoomNo+",";
                                        }
                                }

                            }
                            Active = 1;

                            Bill_Check_in = new Date();

                           // IF CHECK IN CHECK OUT TIME CHANGED........ ...................................................................................... 

                           if(no.isSelected()){


                            try {
                                    E_chk_in = (Date) Formats.TIMESTAMP.parseValue(e_checkin_text.getText());
                                    E_chk_out = (Date) Formats.TIMESTAMP.parseValue(e_checkout_date.getText());
                                } catch (BasicException ex) {
                                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }


                            Approved = 0;


                           }
                           else{
                                try {
                                    E_chk_in = (Date) Formats.TIMESTAMP.parseValue(check_IN.getText());
                                    E_chk_out = (Date) Formats.TIMESTAMP.parseValue(check_OUT.getText());
                                } catch (BasicException ex) {
                                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                           }








                           Link_Name = GR_CheckIn_Detail.getRoom_Link_Name(m_App, RoomNo);

                           if(Link_Name !=null) {

                            Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {

                                 for(int i=0;i<Room_Nos_list.size();i++){
                                   String Room_No_I = Room_Nos_list.get(i).toString();



                                   int copy_hall_to_hall_cancel_request  =  new PreparedSentence(m_App.getSession(), "INSERT INTO guestroom_checkin (ID , ROOMTYPE , ROOMNO , MEMNO , GUEST_N ,ROOMS , DAYS , CHK_IN , CHK_OUT , ADV_RECV  , TOT_AMT ,  E_CHK_IN , E_CHK_OUT , RM_SERV_CHRG , RECIEPT_NO , BILL_NAME , ID_CARD , CRBY , CRDATE , CRHOST , ACTIVE , MEMNAME ,ADVNCE_RECV_ID , LINK_NAME , APPROVED , BILL_CHK_IN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                              , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING ,Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT , Datas.INT , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.DOUBLE , Datas.DOUBLE , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.DOUBLE , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING ,Datas.TIMESTAMP ,  Datas.STRING  , Datas.INT  , Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT , Datas.TIMESTAMP})).exec
                                                                               (new Object[]{UUID.randomUUID().toString() , Rtype , Room_No_I , Mno , GuestName , No_of_rooms_Bkd , No_of_days , chk_In_date , Chk_out_date , AdvanceAmt , Tot_Amount , E_chk_in , E_chk_out ,RoomSerChrg ,RECIEPT_NO , Billname , IdCard ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active , Mname  , Advnce_Recv_ID , Link_Name , Approved , Bill_Check_in}); 

                                     }

                                    int count = GR_CheckIn_Detail.getCountFor_Rooms_Booked(m_App , Advnce_Recv_ID);

                                    if(count>=No_of_rooms_Bkd){ 
                                        int Adv_pmnt_table  =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_advance_payment  SET CHK_FLAG=1  WHERE ID =  ?"
                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec
                                                                   (new Object[]{  Adv_Pmt_ID  });

                                           String Bkng_id=booking_id_label.getText();

                                            int Adv_pmnt_table1  =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET CHK_IN_FLAG=1  WHERE MEMBERNAME = (SELECT ID FROM CUSTOMERS WHERE SEARCHKEY=?) AND ID=?"
                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.STRING })).exec
                                                                   (new Object[]{  Mno , Bkng_id  });


                                    }

                                    return null;
                                  }
                                 };



                                    try {
                                            t.execute();
                                            if(no.isSelected()){
                                                JOptionPane.showMessageDialog(this, "Check In done. Please Wait for approval..!! ", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                            }
                                            else{
                                                 JOptionPane.showMessageDialog(this, "Check In Done Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                            }



                                            reset();
                                            Gname_Combo.setSelectedIndex(-1);
                                            loaddata();

                                        } catch (BasicException ex) {
                                            Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                            new MessageInf(ex).show(new JFrame());
                                        }

                           }
                           else{
                               JOptionPane.showMessageDialog(this, "No Link Set.. Plaese set link to master !! ", "Room NO.", JOptionPane.ERROR_MESSAGE);
                           }

                       /*
                            bill_panel.setVisible(true);
                            main_panel.setVisible(false);

                             mno.setText(MemNo_label.getText());
                             if(MemberFlag!=1){
                                 jLabel14.setVisible(true);
                                 mno3.setVisible(true);
                                 mno3.setText(GuestName);
                             }
                             else{
                                 jLabel14.setVisible(false);
                                 mno3.setVisible(false);
                             }

                             mno12.setText(due_label.getText());
                             advnce_recv.setText(AdvRec_label.getText());
                             tot_chrge.setText(totC_label.getText());
                             date1.setText(e_checkin_text.getText());
                             date2.setText(e_checkout_date.getText());
                             roomCharges.setText("0.00");
                             tot.setText(totC_label.getText());
                             days.setText(jLabel3.getText());
                             getRoomNoBooked();

                             */

                            }
                       
                     
                               
                               
                     
                         }  
                         else{
                             JOptionPane.showMessageDialog(this, " Check-in and check-out time is out of range. Please change booking dates. ", " Warning Message", JOptionPane.WARNING_MESSAGE);
                         }  
                               
                    }
                    else{
                         
                         JOptionPane.showMessageDialog(this, " Check In time is Different than Booked Check In time. \n Please Select current CheckIn Time ..!! ", " Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                       
                       
                }
                else{
                    
                     JOptionPane.showMessageDialog(this, (" No Sufficient Balance. \n  Minimum Amount for Check_In is : "+decimalFormat.format(Min_CheckInAmt)+ ". " ) , "Error Message", JOptionPane.ERROR_MESSAGE);
                    
                 }
                       
                }
                
                else{
                     JOptionPane.showMessageDialog(this, "Enter ID Card details. !! ", "Room NO.", JOptionPane.ERROR_MESSAGE);
                   }
                 }
          
              else{
                    JOptionPane.showMessageDialog(this, "Select Room No. !! ", "Room NO.", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        
    }//GEN-LAST:event_saveActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
       main_panel.setVisible(true);
       bill_panel.setVisible(false);
       
       
       try {
            loaddata();
        } catch (BasicException ex) {
             ex.printStackTrace();
                     new MessageInf(ex).show(this);
            Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cancelActionPerformed

    private void createBill_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBill_ButtonActionPerformed
       //added by pratima
      
       
        
          if(strict==0){
     //ended by pratima
        
     if(DISC_FLAG==1){   
      
       Double tot_Bill_temp  = Double.parseDouble(tot.getText());
       Double Tot_bal_amt_temp = Double.parseDouble(Tot_Bal_amt_text.getText());
       
       String Roomno = roomNo_Label1.getText();
       Date Check_In_Date = null;
       try {
               Check_In_Date = (Date) Formats.TIMESTAMP.parseValue(date1.getText());
           } catch (BasicException ex) {
               Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
           }
     
       String GuestRoomLink = CheckInTable_Model.getGuestRoomLinkName(m_App, Roomno);
       int PendngQTs = CheckInTable_Model.getPendingQTs(m_App, GuestRoomLink , Check_In_Date);
       
       if(tot_Bill_temp <= Tot_bal_amt_temp)  
         
       {
         
        if(PendngQTs==0){
           
           
           
         
       int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to Check Out  ?? " , "Bill menu" , JOptionPane.YES_NO_OPTION);
       if(bill == JOptionPane.YES_OPTION)
        {   
           Double UnAdjusted_Amt = Double.parseDouble(totalAmtAdjusted.getText());
           Double TotBilledAmt = Double.parseDouble(tot_chrge.getText());
           
           Date Check_Out_Date = null;
           Date CurrDate = new Date();
           
           try {
               Check_Out_Date = (Date) Formats.TIMESTAMP.parseValue(date2.getText());
           } catch (BasicException ex) {
               Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
           }
           Check_Out_TimeDiff = GR_CheckIn_Detail.getCheck_Out_TimeDiff(m_App);
           
           
           long diff = Check_Out_Date.getTime() - CurrDate.getTime() ;
           long diffHours = diff / (60 * 60 * 1000); 
           
          // if( (Check_Out_TimeDiff>=diffHours  && Check_Out_TimeDiff >= (diffHours*-1)) )
           if( ( Check_Out_TimeDiff >= (diffHours)) )         
           {
           
               
               
               
               
           if(UnAdjusted_Amt<=0 && TotBilledAmt<=0 ){
           
                try {
                      Generate_GuestRoomBill2();
                        int row = jTable1.getSelectedRow();
                        GuestRoom_CheckIn_Info showdata = GR_CheckIn_Detail.getGuestRmList().get(row);
                       if( ((Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT())).before(Check_Out_Date)){
                          
                                        //added by pratima 
                                         new PreparedSentence(m_App.getSession()
                            , "UPDATE guestroom_checkin SET E_CHK_OUT=?  WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.STRING })).exec(
                                    new Object[]{Check_Out_Date , CheckIn_ID });
                //ended by pratima
                           }
                  } catch (BasicException ex) {
                       ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }




               try {
                      loaddata();
                      reset();
                      LoadDataforTables();
                  } catch (BasicException ex) {
                       ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }
           }
           
          
           
           
           
           
           
           
           else{
               JOptionPane.showMessageDialog(this, "Still Unadjusted Amt..! \n Please Clear Balance..!!", "Error", JOptionPane.ERROR_MESSAGE);
           }
           
           }
           
           
           
           
           
           else{
               
             int Approved_flag = GR_CheckIn_Detail.getCheckOutApproval(m_App, CheckIn_ID);
             
             if(Approved_flag==1)
               
             { 
               
               
            int bill1 = JOptionPane.showConfirmDialog(main_panel, " Check out time is different than expected check out..!! \n  \n Do you want to send request to change check-out time ???   " , "Warning" , JOptionPane.YES_NO_OPTION);
            if(bill1 == JOptionPane.YES_OPTION)    
               
            {
             
                
             try {
                    new PreparedSentence(m_App.getSession()
                            , "UPDATE guestroom_checkin SET X_CHK_OUT=? , APPROVED=2 WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.STRING })).exec(
                                    new Object[]{ CurrDate , CheckIn_ID });
                
                    
                    
                    
                    JOptionPane.showMessageDialog(this, " Request Sent Successfully. Please wait for approval.", "Success", JOptionPane.INFORMATION_MESSAGE);    
             
             } catch (BasicException ex) {
                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    new MessageInf(ex).show(new JFrame());
                }
                
             
             
                
                
                
            } 
            
            // IF THE CHECK OUT TIME IS NOT CHANGED
            
            else{
                
                
                    int bill_T = JOptionPane.showConfirmDialog(main_panel, " Do you want to continue billing with "+days.getText()+ "  days  ?? \n \n If yes click on yes, otherwise click No to go back" , "Warning" , JOptionPane.YES_NO_OPTION);
                    if(bill_T == JOptionPane.YES_OPTION)    
               
                
                    {
                        
                        int bill_T1 = JOptionPane.showConfirmDialog(main_panel, " Are you sure  you want to  create the bill ?? " , "Warning" , JOptionPane.YES_NO_OPTION);
                         if(bill_T1 == JOptionPane.YES_OPTION)    
               
                        
                         {
                
                                    if(UnAdjusted_Amt<=0 && TotBilledAmt<=0 ){

                                    try {
                                        //added by pratima 
                                         new PreparedSentence(m_App.getSession()
                            , "UPDATE guestroom_checkin SET E_CHK_OUT=?  WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.STRING })).exec(
                                    new Object[]{ CurrDate , CheckIn_ID });
                //ended by pratima
                                          Generate_GuestRoomBill2();

                                      } catch (BasicException ex) {
                                           ex.printStackTrace();
                                         new MessageInf(ex).show(this);
                                          Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                      }




                                   try {
                                          loaddata();
                                          reset();
                                          LoadDataforTables();
                                      } catch (BasicException ex) {
                                           ex.printStackTrace();
                                         new MessageInf(ex).show(this);
                                          Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                               
                                    }
                         }
           
                    }
                
                
            }
            
            
           }
             
           
             
          else if(Approved_flag==2){
                 
              JOptionPane.showMessageDialog(this, " Request already sent. Please wait for approval.", "Warning", JOptionPane.WARNING_MESSAGE);        
          }
          else if(Approved_flag==5){
               
                JOptionPane.showMessageDialog(this, " Your request to Check-Out early was not approved.  \n \n Check-out is allowed at mention Check-out time only.", "Warning", JOptionPane.WARNING_MESSAGE);        
           }  
            
            
            
               
           }
           
           
           
        
         }
       
       
        }
        else{
            
            JOptionPane.showMessageDialog(this, " Still pending QTs for the account linked with this Room.  \n \n Please clear the pending QTs incase if you want to CheckOut.  \n ", " Warning ", JOptionPane.WARNING_MESSAGE);
        }
       
       
       
       
       }
       else{
           
            JOptionPane.showMessageDialog(this, " No sufficient balance to check out..!!  \n \n Please pay remaining amount to Check-out ", " Warning ", JOptionPane.WARNING_MESSAGE);
           
       }
       
     }
     else{
         JOptionPane.showMessageDialog(this, " Please wait for discount request to be approved..!!  ", " Warning ", JOptionPane.WARNING_MESSAGE);
     }
    
          }
          //added by pratima
        if(strict==1){
           Date Check_Out_Date = null;
            try {
            int row = jTable1.getSelectedRow();
               GuestRoom_CheckIn_Info showdata = GR_CheckIn_Detail.getGuestRmList().get(row); 
               E_chk_out = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT());
               
               Check_Out_Date = (Date) Formats.TIMESTAMP.parseValue(date2.getText());
          
         
         int Approved_flag = GR_CheckIn_Detail.getCheckOutApproval(m_App, CheckIn_ID);
         
         if(Approved_flag==1)
         { 
             if(E_chk_out.compareTo(Check_Out_Date)<0){
            int bill1 = JOptionPane.showConfirmDialog(main_panel, " Check out time is different than expected check out..!! \n  \n Do you want to send request to change check-out time ???   " , "Warning" , JOptionPane.YES_NO_OPTION);
            if(bill1 == JOptionPane.YES_OPTION)    
           {
             try {
               new PreparedSentence(m_App.getSession()
               , "UPDATE guestroom_checkin SET X_CHK_OUT=E_CHK_OUT , APPROVED=2 WHERE ID=?"
               , new SerializerWriteBasic(new Datas[]{  Datas.STRING })).exec(
               new Object[]{ CheckIn_ID });
               JOptionPane.showMessageDialog(this, " Request Sent Successfully. Please wait for approval.", "Success", JOptionPane.INFORMATION_MESSAGE);    
              } catch (BasicException ex) {
              Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              ex.printStackTrace();
              new MessageInf(ex).show(new JFrame());
              }
           }}
         } else if(Approved_flag==2){
                 
              JOptionPane.showMessageDialog(this, " Request already sent. Please wait for approval.", "Warning", JOptionPane.WARNING_MESSAGE);        
          }
          else if(Approved_flag==5){
               
                JOptionPane.showMessageDialog(this, " Your request to Check-Out early was not approved.  \n \n Check-out is allowed at mention Check-out time only.", "Warning", JOptionPane.WARNING_MESSAGE);        
         }else if(Approved_flag==4){
             
               if(DISC_FLAG==1){   
                String Roomno = roomNo_Label1.getText();
                Date Check_In_Date = null;
                try {
                Check_In_Date = (Date) Formats.TIMESTAMP.parseValue(date1.getText());
                } catch (BasicException ex) {
                Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                }
                String GuestRoomLink = CheckInTable_Model.getGuestRoomLinkName(m_App, Roomno);
                int PendngQTs = CheckInTable_Model.getPendingQTs(m_App, GuestRoomLink , Check_In_Date);
                if(PendngQTs==0){
                     int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to Check Out  ?? " , "Bill menu" , JOptionPane.YES_NO_OPTION);
                     if(bill == JOptionPane.YES_OPTION)
                     {   
                      Double UnAdjusted_Amt = Double.parseDouble(totalAmtAdjusted.getText());
                      Double TotBilledAmt = Double.parseDouble(tot_chrge.getText());
               
                        if(UnAdjusted_Amt<=0 && TotBilledAmt<=0 ){
                              try {
                              Generate_GuestRoomBill2();
                              } catch (BasicException ex) {
                              ex.printStackTrace();
                              new MessageInf(ex).show(this);
                              Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              try {
                              loaddata();
                              reset();
                              LoadDataforTables();
                              } catch (BasicException ex) {
                              ex.printStackTrace();
                              new MessageInf(ex).show(this);
                              Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                              }
                        }else{
                        JOptionPane.showMessageDialog(this, "Still Unadjusted Amt..! \n Please Clear Balance..!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                     }else{}
                }else{
                JOptionPane.showMessageDialog(this, " Still pending QTs for the account linked with this Room.  \n \n Please clear the pending QTs incase if you want to CheckOut.  \n ", " Warning ", JOptionPane.WARNING_MESSAGE);
                }
                 
             }else{
              JOptionPane.showMessageDialog(this, " Please wait for discount request to be approved..!!  ", " Warning ", JOptionPane.WARNING_MESSAGE);
              }
         }  
       
         
} catch (BasicException ex) {
               Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
           }
         }
      
     //ended by pratima
     
     
     
    }//GEN-LAST:event_createBill_ButtonActionPerformed
    List<Object> billList;
    String AdvanceRecieptNo=null;
    Double AdjustAmt=0.00;
    Double AdjustAmt_Room=0.00;
    String Adjust_reference="";
    List<Object> GuestRoom_billList;
    String Adjust_reference_Room="";
    int Row_Count;
    
    
    private void Pay_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pay_btnActionPerformed
         if(Double.parseDouble(roomCharges.getText())>0){
             
             Double RScharge = Double.parseDouble(roomCharges.getText()); 
             Double AdvanceAdjust = Double.parseDouble(totalAmtAdjusted.getText());
             
             if(RScharge<=AdvanceAdjust){
             
             
            
             Row_Count = GRS.getTableModel2().getRowCount();
             billList = new ArrayList<Object>();
             Booking_seq_id = bkng_seq_no_label.getText();
             Double AdvanceAmt;
             Double BalAmt;
             int RoomBill=0;
             AdjustAmt=0.00;
             GuestRoom_billList = new ArrayList();
             AdjustAmt_Room = 0.00;
             Adjust_reference_Room = "";

       // FOR ROOM SERVICE BILL NO  LIST ----------------------------------------------------------------------------
             
             
             for(int i=0;i<Row_Count;i++){
                 int row = i;
               //  System.out.print(GRS.getTableModel2().getValueAt(row, 4).toString());
                 
                // Boolean ticked  = Boolean.valueOf(GRS.getTableModel2().getValueAt(row, 4).toString());
                // if(ticked){
                 Double AmountTemp =  Double.valueOf(GRS.getTableModel2().getValueAt(row, 5).toString());   
                 if(AmountTemp>0){    
                     
                    String temp =  String.valueOf(GRS.getTableModel2().getValueAt(row, 2).toString());
                    if(temp.equals("Guest Room")){
                        RoomBill = 1;
                        String BillNo =  String.valueOf(GRS.getTableModel2().getValueAt(row, 1).toString());
                        Double d =  Double.valueOf(GRS.getTableModel2().getValueAt(row, 5).toString());
                        Adjust_reference_Room = Adjust_reference_Room +BillNo+"#"+d+"-";
                        GuestRoom_billList.add(BillNo);
                        AdjustAmt_Room=AdjustAmt_Room+d;  
                        
                        
                    }
                    else{
                        String BillNo =  String.valueOf(GRS.getTableModel2().getValueAt(row, 1).toString());
                        Double d =  Double.valueOf(GRS.getTableModel2().getValueAt(row, 5).toString());
                        Adjust_reference = Adjust_reference +BillNo+"#"+d+"-";
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
                  
                  
                  Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {
                       
                           for(int i=0;i<billList.size();i++){
                              String BillNo =   billList.get(i).toString();
                             
                              int x = new StaticSentence(m_App.getSession(), "UPDATE bill SET paid=1 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {BillNo});
                            
                              int x1 = new StaticSentence(m_App.getSession(), "UPDATE bill SET RECEIPT=? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {AdvanceRecieptNo , BillNo});
                            
                           
                             }
                           
                             
                            int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt , AdvanceRecieptNo });
                            
                           
                            int Update_Advce_GuestRoom2 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET adjust_ref= ?  WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference , AdvanceRecieptNo });
                            
                           
                            
                            
                            int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt , AdvanceRecieptNo });
                            
                            
                            
                           int GuestRoom_AdvancePay = new StaticSentence(m_App.getSession(), "UPDATE guestroom_advance_payment SET BAL_AMT=BAL_AMT-? WHERE BOOKING_SEQ_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt , Booking_seq_id });
                            
                            
                        
                           
                           // ADJUSTMENT IN ACCOUNTS ----------------------------------------------------------------------------------------------------------------
                           
                           
                            int UPDATE_aCCOUNT1 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET balanceamount=balanceamount-?  WHERE TRANSNO=?  AND TRANSTYPE='C' "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt , AdvanceRecieptNo });
                            
                           
                            int UPDATE_aCCOUNT2 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET PAYMENTREF = CONCAT(COALESCE(PAYMENTREF , ?))  WHERE TRANSNO=?  AND TRANSTYPE='C'  "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference , AdvanceRecieptNo });
                            
                           
                           
                            
                            
                            return null;
                          }
                         };
                   
                  try {
                       t.execute();
                       JOptionPane.showMessageDialog(this, "Adjusted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                        
                        Double z2 = Double.parseDouble(Tot_Bal_amt_text.getText());
                        Tot_Bal_amt_text.setText(decimalFormat.format(z2-AdjustAmt));
                        LoadDataforTables();
                        loaddata();
                        
                          
                            
                                    
                   } catch (BasicException ex) {
                       
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                    } 
                  
                  
                  
              }
              
              
              
            // ENTRIES FOR ADJUSTEMENT OF ROOM BILLS....-------------------------------------------------------------------------------------------------  
              
             if(GuestRoom_billList.size()>0 && Adjust_reference_Room.trim().length()>0 && Adjust_reference_Room!=null )  {
                 
                 
                  Transaction t = new Transaction(m_App.getSession()) {
             
                    @Override
                    protected Object transact() throws BasicException {
                       
                           for(int i=0;i<GuestRoom_billList.size();i++){
                              String BillNo =   GuestRoom_billList.get(i).toString();
                             
                              
                              int xNew = new StaticSentence(m_App.getSession(), "UPDATE guestroom_bill SET AMT_PAID=AMT_PAID+?  WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE  , Datas.STRING })).exec(new Object[] { AdjustAmt_Room , BillNo});   
                              
//                              int xNew1 = new StaticSentence(m_App.getSession(), "UPDATE guestroom_bill SET BAL_AMT=BAL_AMT-?  WHERE ID=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE  , Datas.STRING })).exec(new Object[] { AdjustAmt_Room , BillNo});   
//                              
                     int xNew1 = new StaticSentence(m_App.getSession(), "UPDATE guestroom_bill SET BAL_AMT=BAL_AMT-?  WHERE BOOKING_SEQ_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE  , Datas.STRING })).exec(new Object[] { AdjustAmt_Room , Booking_seq_id});   
                                    
                              int checkin_update = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET BILLED=1 WHERE BILLNO=?"
                                                                        , new SerializerWriteBasic(new Datas[] { Datas.STRING })).exec(new Object[] {BillNo });
                            
                              
                              
                              
                              
                                for(int h=0;h<Row_Count;h++){
                                 int row = h;

                                 Boolean ticked2  = Boolean.valueOf(GRS.getTableModel2().getValueAt(row, 4).toString());
                                 if(ticked2){


                                     int x = new StaticSentence(m_App.getSession(), "UPDATE guestroom_bill SET paid=1 WHERE ID=?"
                                                                         , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {BillNo});
                                     
                                     
                                     int checkin_update3 = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET BILLED=2 WHERE BILLNO=?"
                                                                        , new SerializerWriteBasic(new Datas[] { Datas.STRING })).exec(new Object[] {Booking_seq_id });
                            
                                     
                                 }
                                 
                                }
                               
                              
                              int x1 = new StaticSentence(m_App.getSession(), "UPDATE guestroom_bill SET RECIEPT=? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {AdvanceRecieptNo , BillNo});
                            
                            }
//                            int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_Room , AdvanceRecieptNo });
//                            
//                           
//                            int Update_Advce_GuestRoom2 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET adjust_ref= ?  WHERE RECIEPT_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference_Room , AdvanceRecieptNo });
//                            
//                           
//                            
//                            
//                            int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_Room , AdvanceRecieptNo });
//                            
//                            
//                            
//                           int GuestRoom_AdvancePay = new StaticSentence(m_App.getSession(), "UPDATE guestroom_advance_payment SET BAL_AMT=BAL_AMT-? WHERE BOOKING_SEQ_NO=?"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_Room , Booking_seq_id });
//                            
//                            
//                            
//                           
//                               
//                           
//                           
//                           
//                            
//                            
//                               // ADJUSTMENT IN ACCOUNTS ----------------------------------------------------------------------------------------------------------------
//                           
//                           
//                            int UPDATE_aCCOUNT1 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET balanceamount=balanceamount-?  WHERE TRANSNO=?  AND TRANSTYPE='C'"
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_Room , AdvanceRecieptNo });
//                            
//                           
//                            int UPDATE_aCCOUNT2 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET PAYMENTREF= CONCAT(COALESCE(PAYMENTREF , ?))  WHERE TRANSNO=?  AND TRANSTYPE='C' "
//                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference_Room , AdvanceRecieptNo });
//    above lines commented by pratima                        
               
                             
//added by pratima: to adjust multiple receipts
               int GuestRoom_AdvancePay = new StaticSentence(m_App.getSession(), "UPDATE guestroom_advance_payment SET BAL_AMT=BAL_AMT-? WHERE BOOKING_SEQ_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {AdjustAmt_Room , Booking_seq_id });
               int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
              for(int j=0;j<rowCount2;j++){
                 int row11 = j; 
                 Boolean ticked11  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row11, 3).toString()); 
                 if(ticked11){
                     Double tempAmt=Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row11, 4).toString());
                     String tempReciept=String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row11, 1).toString());
                            int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {tempAmt ,tempReciept });
                            
                           
                            int Update_Advce_GuestRoom2 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET adjust_ref= ?  WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] {Adjust_reference_Room ,tempReciept });
                            
                           
                            
                            
                            int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {tempAmt ,tempReciept });
                            
                            
                            
                           
                            
                            
                            
                           
                               
                           
                           
                           
                            
                            
                               // ADJUSTMENT IN ACCOUNTS ----------------------------------------------------------------------------------------------------------------
                           
                           
                            int UPDATE_aCCOUNT1 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET balanceamount=balanceamount-?  WHERE TRANSNO=?  AND TRANSTYPE='C'"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {tempAmt , tempReciept });
                            
                           
                            int UPDATE_aCCOUNT2 = new StaticSentence(m_App.getSession(), "UPDATE accountjournal SET PAYMENTREF= CONCAT(COALESCE(PAYMENTREF , ?)),ADJUSTED=?  WHERE TRANSNO=?  AND TRANSTYPE='C' "
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING ,Datas.INT, Datas.STRING })).exec(new Object[] {Adjust_reference_Room ,1,tempReciept });
                            
                 }}//for and if ended by pratima
               
                           
                            
                            
                            return null;
                          }
                         };
                   
                  try {
                       t.execute();
                       JOptionPane.showMessageDialog(this, "Adjusted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                        
                       // generateFinalReport_btn.setEnabled(true);
                        LoadDataforTables();
                        Double z1 = Double.parseDouble(Tot_Bal_amt_text.getText());
                        Tot_Bal_amt_text.setText(decimalFormat.format(z1-AdjustAmt_Room));
                        
                        setAdjustedValue(bkng_seq_no_label.getText());
                       
                            
                                    
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
            
            
    }//GEN-LAST:event_Pay_btnActionPerformed

    String CheckIn_ID;
    String CreateBy;
    Date CreateDate;
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
              if(jTable1.getSelectedRow()<GR_CheckIn_Detail.getSize()){
               int row = jTable1.getSelectedRow();
               GuestRoom_CheckIn_Info showdata = GR_CheckIn_Detail.getGuestRmList().get(row);
                
               CheckIn_ID = showdata.getId();
               RoomNo = showdata.getROOMNO();
               No_of_rooms_Bkd = showdata.getROOMS();
               Mname = showdata.getMemName();
               Mno = showdata.getMEMNO();
               GuestName = showdata.getGUEST_N();
               No_of_days = showdata.getDAYS();
                  try {
                      chk_In_date = (Date) Formats.TIMESTAMP.parseValue(showdata.getCHK_IN()) ;
                      Chk_out_date = (Date) Formats.TIMESTAMP.parseValue(showdata.getCHK_OUT()) ;
                      E_chk_in = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_IN()) ;
                      E_chk_out = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT()) ;
                      
                      
                  } catch (BasicException ex) {
                       ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
               //AdvanceAmt = showdata.getADV_RECV();
               AdvanceAmt = showdata.getBAL_AMT();
               Tot_Amount = showdata.getTOT_AMT();
               RoomSerChrg = showdata.getRM_SERV_CHRG();
               RECIEPT_NO = showdata.getRECIEPT_NO();
               Billname = showdata.getBILL_NAME();
               IdCard = showdata.getID_CARD();
               CreateBy = showdata.getCRBY();
               CreateDate = showdata.getCRDATE();
               
               roomNo_Label.setText(RoomNo);
               Rtype = showdata.getROOMTYPE();
               Customer = GR_CheckIn_Detail.getCustomerID(m_App, Mno);
               String Cust_n = GRS.getCust_link_name(m_App, RoomNo);
               
              if(Cust_n!="null"){
               
             try{
                             GRS = GuestRoomService.load_Curr_Bills(m_App, Cust_n ,Customer , RoomNo , E_chk_in);
              }
              catch (BasicException ex) {
                   ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                 }
                        BillInfoList = (List<GuestRoomService.BillInfo>) GRS.getBillInfo_Path();
                        if(BillInfoList==null){
                             BillInfoList = new ArrayList<GuestRoomService.BillInfo>();
                        }
              
                ShowBillDetails(GRS);
                
                Bill_frame.setVisible(true);
                main_panel.setVisible(false);
              }
              else{
                   JOptionPane.showMessageDialog(this, " Room not linked with Member Master", "Error", JOptionPane.ERROR_MESSAGE);
              }
               
             }
              
            
        }
        else{
             JOptionPane.showMessageDialog(this, " Select Any One ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       Bill_frame.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      setCheckoutStrict();
         if(jTable1.getSelectedRow()!=-1){
              if(jTable1.getSelectedRow()<GR_CheckIn_Detail.getSize()){
               int row = jTable1.getSelectedRow();
               GuestRoom_CheckIn_Info showdata = GR_CheckIn_Detail.getGuestRmList().get(row);
              //   jTextField1.setText(showdata.getGst());
                   main_panel.setVisible(false);
                   bill_panel.setVisible(true);
                   Adjust_billedAmt_check.setSelected(false);
                   refund_bal_amt_text.setText("0.00");
                   jCheckBox1.setSelected(false);
                   link_name_label.setText(showdata.getLINK_NAME());
                  //   jTextField1.setText(showdata.getGst());
                   mno.setText(showdata.getMEMNO());
                   if(showdata.getGUEST_N()!=null && showdata.getGUEST_N().trim().length()>0){
                       jLabel14.setVisible(true);
                       guest_name_label.setVisible(true);
                       guest_name_label.setText(showdata.getGUEST_N());
                   }
                   else{
                       jLabel14.setVisible(false);
                       guest_name_label.setVisible(false);
                       guest_name_label.setText("");
                   }
                   
                  Date check_out_d = null;
                  Date CurrTime = new Date();
                  
                   Check_In_TimeDiff =   GR_CheckIn_Detail.getCheck_In_TimeDiff(m_App);
                   Check_Out_TimeDiff = GR_CheckIn_Detail.getCheck_Out_TimeDiff(m_App);
                   
                   
                   DISC_FLAG = showdata.getDISC_FLAG();
                   
                   
                 try {
                            E_chk_in = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_IN());
                            E_chk_out = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT());
                      } catch (BasicException ex) {
                            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                  
                  try {
                      check_out_d = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT());
                  } catch (BasicException ex) {
                       ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  
                  long diff = CurrTime.getTime() - check_out_d.getTime() ;
                  long diffHours = diff / (60 * 60 * 1000);   
                  System.out.println("Houre :  "+diffHours);  
                  
                  if(diffHours > Check_Out_TimeDiff )
                  {
                     // int Day = (int) Math.ceil((diffHours/24)+0.5);
                      int Day = (int) Math.ceil((diffHours/24)+0.1);
                      System.out.println("Day  "+Day);
                      
                      Calendar c = Calendar.getInstance();
                        c.setTime(E_chk_out); // Now use today date.
                        c.add(Calendar.DATE, Day); // Adding 5 days
                        E_chk_out = c.getTime();
                      
                      
                  }
                  
                  
                   Date to_date = new Date();
                   
                   if(check_out_d.before(to_date)){
                       System.out.println(check_out_d);
                       System.out.println(to_date);
                   }
                   
                  
                   if(showdata.getBILLED()==1){
                    try {
                           
                            E_chk_out = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT());
                      } catch (BasicException ex) {
                            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     
                   }
                   //added by pratima
                   strictApprove=GR_CheckIn_Detail.getCheckOutApproval(m_App, showdata.getId());
                    if(strict==1){
                          
                        if(strictApprove==4){
                        try {
                           
                            E_chk_out = (Date) Formats.TIMESTAMP.parseValue(showdata.getE_CHK_OUT());
                      } catch (BasicException ex) {
                            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        }
                    }
                   //ended by pratima 
                   //////////////////////////////////////////////////////////
                   //Added By Ganesh
                      strictApprove1=GR_CheckIn_Detail.getCheckOutApproval1(m_App, showdata.getGst());
                      jTextField1.setText(strictApprove1);
                   //Ended By Ganesh
                   ////////////////////////////////////////////////////////////
                   
                   date1.setText((showdata.getE_CHK_IN()));
                   date2.setText((Formats.TIMESTAMP.formatValue(E_chk_out)));
                   
                   
                       
                   
                   long no_of_days = (long) (E_chk_out.getTime() - E_chk_in.getTime())/(1000 * 60 * 60 * 24);
                   //added by pratima
                   long hour = (long) (E_chk_out.getTime() - E_chk_in.getTime())/(1000 * 60 * 60 );
                   if((hour%24)>Check_Out_TimeDiff)
                        no_of_days= no_of_days+1;
                   //ended by pratima
                   
                   
                   System.out.println(m_App.getAppUserView().getUser().getName());
                   if(no_of_days==0){
                       no_of_days=1;
                   }
                   
                   
                   days.setText(""+no_of_days);
                   
                   charges.setText(""+showdata.getTOT_AMT());
                 //  advnce_recv.setText(""+showdata.getBAL_AMT());
                   advnce_recv.setText(""+showdata.getADV_RECV());
                   Mem_No = mno.getText();
                  Customer = GR_CheckIn_Detail.getCustomerID(m_App, Mem_No);
                   mem_name_label.setText(showdata.getMemName());
                   room_type_label.setText(showdata.getROOMTYPE());
                   
                   Booking_seq_id = showdata.getBOOKING_SEQ_NO();
                   booking_id = showdata.getBOOKING_ID();
                   booking_id_label.setText(booking_id);
                   RoomType_ID = showdata.getROOMTYPE_ID();
                   bkng_seq_no_label.setText(Booking_seq_id);
                   
                   CheckIn_ID = showdata.getId();
                   Advnce_Recv_ID = showdata.getADVNCE_RECV_ID();
                   RoomNo = showdata.getROOMNO();
                   String Cust_n = GRS.getCust_link_name(m_App, RoomNo);
                   
                   if(Cust_n!="null"){
               
                    try{
                             GRS = GuestRoomService.load_Curr_Bills(m_App, Cust_n , Customer ,RoomNo , E_chk_in);
                         }
                         catch (BasicException ex) {
                              ex.printStackTrace();
                              new MessageInf(ex).show(this);
                            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        BillInfoList = (List<GuestRoomService.BillInfo>) GRS.getBillInfo_Path();
                        if(BillInfoList==null){
                             BillInfoList = new ArrayList<GuestRoomService.BillInfo>();
                        }
              
                        ShowBillDetails(GRS);
                   
                   }
                   
                   Double RoomCharge = getRoomServiceDetails(Cust_n);
                 // roomCharges.setText(""+RoomCharge);
               
                   
                  // try {
                    ///  int update_RoomServiceCharges =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET RM_SERV_CHRG=? WHERE ID=? "
                     //                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING})).exec
                     //                                             (new Object[]{ RoomCharge , CheckIn_ID  });
                    //} catch (BasicException ex) {
                   //   Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  // }

                  
                  // method to show advance recieve in table model --------------------------------------------------------------- # aakash
                   
                   
                   if(Cust_n!="null"){
               
                    try{
                             AdvanceAdjustTable_model = AdvanceAdjustTableModel.load_Curr_Bills(m_App , booking_id);
                         }
                         catch (BasicException ex) {
                              ex.printStackTrace();
                              new MessageInf(ex).show(this);
                            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        AdvanceInfoList = (List<AdvanceAdjustTableModel.AdvanceInfo>) AdvanceAdjustTable_model.getAdvanceList();
                        if(AdvanceInfoList==null){
                             AdvanceInfoList = new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
                        }
              
                        ShowAdvanceInfo(AdvanceAdjustTable_model);
                   
                   }
                   
                   
                   
                   roomNo_Label1.setText(showdata.getROOMNO());
                   
                   String Name = showdata.getMemName();
                  
                   
                   try {
                      CheckInTable_Model = CheckInTableModel.LoadGuestRoomCheckInDetail2(m_App, booking_id);
                    } catch (BasicException ex) {
                         ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GuestRoomDetailList =  (List<CheckInTableModel.RoomAdvInfo>) CheckInTable_Model.getGuestRoomPath();
                    CheckInTableModel.RoomAdvInfo showdata2 = (CheckInTableModel.RoomAdvInfo) ((CheckInTableModel.RoomAdvInfo)GuestRoomDetailList.get(0));
                   
                    
                   
                   
                    
                    if(showdata2.getMEMBER_FLAG()==1){
                        rate.setText(decimalFormat.format(showdata2.getMEM_Tariff()));
                        charges.setText(decimalFormat.format(showdata2.getMEM_Tariff()*no_of_days));
                    }
                    else{
                         rate.setText(decimalFormat.format(showdata2.getN_MEM_Tariff()));
                         charges.setText(decimalFormat.format(showdata2.getN_MEM_Tariff()*no_of_days));
                    }
                   
                    Billname = showdata.getBILL_NAME();
                    Biiling_name_label.setText(Billname);
                    
                  
                   
                 Double TaxRate1;
                 Double TaxRate2;
                 Double TaxRate3;     
                 
                 Basic1 = showdata2.getBASIC1();
                 Basic2 = showdata2.getBASIC2();
              
               
                    
                 Double Amt = Double.parseDouble(charges.getText());
                 Amt = Amt-Discount_Amt;
                 Double Grand_Total = Double.parseDouble(charges.getText());
                 Grand_Total = Grand_Total-Discount_Amt;
                 
                 
                 Discount_Amt = showdata.getDiscount();
                 Discount_Amt_label.setText(decimalFormat.format(Discount_Amt)); 
                 
                 Discount_Amt_label_Total.setText(decimalFormat.format(Amt));   
                    
                    
                    
                    
                 Tot_Bal_amt_text.setText(decimalFormat.format(showdata.getBAL_AMT()));
                    
                    
                      // FOR DISPLAYING AND CALCULATION OF TAX 
                   
                   
                   
                   
                 if(showdata2.getTAX1_NAME()!=null){
                     try {
                         TaxRate1 = showdata2.getTAX1();
                         TaxCategoryInfo tax1= m_dlSales.getTaxCategoryByid(showdata2.getTAX1_NAME());
                            tax1_lebel.setText(tax1.getName());
                            Tax_rate1_label.setText((decimalFormat.format(TaxRate1*100))+" %");
                            tax1_amt_label.setText(decimalFormat.format(TaxRate1*Amt));
                            Double Tax1_r = TaxRate1*Amt;
                            Tax_count++;
                            tax1_id_label.setText(tax1.getAccount());
                            basic_tax_label1.setText("(B)");
                            Grand_Total = Grand_Total + Tax1_r;
                            
                     } catch (BasicException ex) {
                          ex.printStackTrace();
                           new MessageInf(ex).show(this);
                         Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                 }
                 else{
                     basic_tax_label1.setText("");
                     TaxRate1 = 0.00;
                     tax1_lebel.setText("----");
                     Tax_rate1_label.setText((decimalFormat.format(0.00*100))+" %");
                     tax1_amt_label.setText(decimalFormat.format(0.00*Amt));
                 }
                  
                 if(showdata2.getTAX2_NAME()!=null){
                     try {
                          if(Basic1==1){
                                TaxRate2 = showdata2.getTAX2();
                                TaxCategoryInfo tax2= m_dlSales.getTaxCategoryByid(showdata2.getTAX2_NAME());
                                Tax_count++;
                                tax2_Label.setText(tax2.getName());
                                Tax_rate2_label.setText((decimalFormat.format(TaxRate2*100))+" %");
                                tax2_amt_label.setText(decimalFormat.format(TaxRate2*Amt));
                                Double Tax2_r = TaxRate2*Amt;
                                tax2_id_label.setText(tax2.getAccount());
                                basic_tax_label2.setText("(B)");
                                Grand_Total = Grand_Total + Tax2_r;
                          }
                          else{
                                
                                TaxRate2 = showdata2.getTAX2();
                                TaxCategoryInfo tax2= m_dlSales.getTaxCategoryByid(showdata2.getTAX2_NAME());
                                Tax_count++;
                                tax2_Label.setText(tax2.getName());
                                Tax_rate2_label.setText((decimalFormat.format(TaxRate2*100))+" %");
                                tax2_amt_label.setText(decimalFormat.format(TaxRate2*Grand_Total));
                                Double Tax2_r = TaxRate2*Grand_Total;
                                tax2_id_label.setText(tax2.getAccount());
                                basic_tax_label2.setText("(C)");
                                Grand_Total = Grand_Total + Tax2_r;
                                
                          }
                          
                     } catch (BasicException ex) {
                          ex.printStackTrace();
                     new MessageInf(ex).show(this);
                         Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                 }
                 else{
                     TaxRate2 = 0.00;
                     tax2_Label.setText("----");
                     Tax_rate2_label.setText((decimalFormat.format(0.00*100))+" %");
                     tax2_amt_label.setText(decimalFormat.format(0.00*Amt));
                     basic_tax_label2.setText("");
                     tax2_id_label.setText("");
                 }
                 
                 
                 
                 
               if(showdata2.getTAX3_NAME()!=null){
                     try {
                         
                         if(Basic2==1){
                                TaxRate3 = showdata2.getTAX3();
                                TaxCategoryInfo tax3= m_dlSales.getTaxCategoryByid(showdata2.getTAX3_NAME());
                                tax3_Label.setText(tax3.getName());
                                Tax_rate3_label.setText((decimalFormat.format(TaxRate3*100))+" %");
                                tax3_amt_label.setText(decimalFormat.format(TaxRate3*Amt));
                                Double Tax3_r = TaxRate3*Amt;
                                Tax_count++;
                                tax3_id_label.setText(tax3.getAccount());
                                basic_tax_label3.setText("(B)");
                                Grand_Total = Grand_Total + Tax3_r;
                         }
                         else{
                                TaxRate3 = showdata2.getTAX3();
                                TaxCategoryInfo tax3= m_dlSales.getTaxCategoryByid(showdata2.getTAX3_NAME());
                                tax3_Label.setText(tax3.getName());
                                Tax_rate3_label.setText((decimalFormat.format(TaxRate3*100))+" %");
                                tax3_amt_label.setText(decimalFormat.format(TaxRate3*Grand_Total));
                                Double Tax3_r = TaxRate3*Grand_Total;
                                Tax_count++;
                                tax3_id_label.setText(tax3.getAccount());
                                basic_tax_label3.setText("(C)");
                                Grand_Total = Grand_Total + Tax3_r;
                         }
                         
                         
                     } catch (BasicException ex) {
                          ex.printStackTrace();
                     new MessageInf(ex).show(this);
                         Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                 }
                 else{
                     TaxRate3 = 0.00;
                     tax3_Label.setText("----");
                     Tax_rate3_label.setText((decimalFormat.format(0.00*100))+" %");
                     tax3_amt_label.setText(decimalFormat.format(0.00*Amt));
                     basic_tax_label3.setText("");
                     tax3_id_label.setText("");
                 }
               
               
               
                    
                   
                   Double Tax_amt = (Double.parseDouble(tax1_amt_label.getText()) + Double.parseDouble(tax2_amt_label.getText()) + Double.parseDouble(tax3_amt_label.getText())  );
                   
                   
                 
                   
                   
                   tot.setText(decimalFormat.format((Amt+Tax_amt)));
                    //totalAmtAdjusted.setText(decimalFormat.format(Amt+Tax_amt));
                   totalAmtAdjusted.setText("0.00");
                   Double Tot_amount = Double.parseDouble(tot.getText());
                   bal_amount_label.setText("0.00");
                   // tot_chrge.setText(decimalFormat.format(Tot_amount+RoomCharge)); 
                    
                    if(showdata.getBILLED()==0){
                        createBill_Button.setEnabled(true);
                        tot_chrge.setText("0.00");
                         discount_Btn.setEnabled(true);
                         billno_label.setText(showdata.getBILLNO());
                         generateFinalReport_btn.setEnabled(false);
                    }
                    
                    else if(showdata.getBILLED()==1){
                         tot_chrge.setText(decimalFormat.format(Tot_amount));
                         createBill_Button.setEnabled(false);
                         discount_Btn.setEnabled(false);
                         billno_label.setText(showdata.getBILLNO());
                         generateFinalReport_btn.setEnabled(true);
                    }
                    else{
                        
                         tot_chrge.setText(decimalFormat.format(Tot_amount));
                         createBill_Button.setEnabled(false);
                          discount_Btn.setEnabled(false);
                         billno_label.setText(showdata.getBILLNO());
                         generateFinalReport_btn.setEnabled(true);
                    }
                   
                    
                    Double AmountPayable = (Tot_amount+RoomCharge)-(showdata.getBAL_AMT());
                    //Double AmountPayable = (Tot_amount)-(showdata.getBAL_AMT());
                    if(AmountPayable>=0){
                        
                        dueamnt.setText(decimalFormat.format(AmountPayable));
                        dueamnt.setForeground(Color.red);
                        Amt_Paid = AmountPayable;
                    }
                    else{
                        dueamnt.setText(decimalFormat.format(AmountPayable));
                        dueamnt.setForeground(Color.GREEN);
                        Amt_Paid = AmountPayable;
                    }
                    
                    
                  
                    // TAX ID
                    tax_cnt_label.setText(Tax_count+"");
                    
                    
                    
                    
                    //TAX VALUES
                    Tax1_N = showdata2.getTAX1().toString();
                    Tax2_N = showdata2.getTAX2().toString();
                    Tax3_N = showdata2.getTAX3().toString();
                    
                    Id_Details = showdata.getID_CARD();
                    
                    no_of_romms = showdata2.getNO_OF_ROOMS();
                    
                    setAdjustedValue(Booking_seq_id);
              }
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     String bill =  JOptionPane.showInputDialog(jPanel1, "Please Enter Bill no. to print bill. " , "Bill Re-print" , JOptionPane.YES_NO_OPTION);
     String Billno = bill.toUpperCase();
     if(Billno.length()>0 && Billno!=null){
          
         String avail = CheckInTable_Model.getRoomBillID(m_App, Billno);
         if(avail.equals(Billno)){
              Launch_Bill(Billno); 
         }
         else{
            JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
         }
               
        
     }
       
         
   
     
     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
/* if(jCheckBox1.isSelected()){     
 Double Refund_amt = Double.parseDouble(Tot_Bal_amt_text.getText())  ;
        refund_bal_amt_text.setText(decimalFormat.format(Refund_amt));
        Tot_Bal_amt_text.setText("0.00");
 } else{
          
         
         Double Old_Bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
         Tot_Bal_amt_text.setText(decimalFormat.format(Old_Bal_amt+Double.parseDouble(refund_bal_amt_text.getText())));
         
         refund_bal_amt_text.setText("0.00");  
      }*/
//added by pratima
   try{
//       String Booking_seq_id=bkng_seq_no_label.getText();
//   List<String> allVouchers=new ArrayList<String>();
//   allVouchers=(List<String>)new StaticSentence(m_App.getSession(), "SELECT booking_seq_no FROM room_hall_refund_voucher",null, SerializerReadString.INSTANCE).list();
// 
//   if(! allVouchers.contains(Booking_seq_id)){
//   
   if(jCheckBox1.isSelected()){      

     Object[] objBookedRooms= (Object[])new StaticSentence(m_App.getSession(), "select room_nos from guestroom_booked_details where  booking_seq_no =?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.STRING })).find(new Object[]{Booking_seq_id});
       Object[] objBilledRooms= (Object[])new StaticSentence(m_App.getSession(), "SELECT count(*) FROM guestroom_bill  where booking_seq_no =? and paid=?" , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.INT   }), new SerializerReadBasic(new Datas[]{ Datas.STRING})).find(new Object[]{Booking_seq_id,1}); 
      Object[] objCheckedinRooms= (Object[])new StaticSentence(m_App.getSession(), "SELECT count(*) FROM guestroom_checkin where  advnce_recv_id =(select id from guestroom_advance_payment where booking_seq_no=?)", new SerializerWriteBasic(new Datas[]{ Datas.STRING   }), new SerializerReadBasic(new Datas[]{ Datas.STRING })).find(new Object[]{Booking_seq_id});
      int bookedRooms=Integer.parseInt((objBookedRooms[0]).toString());
      int billedRooms=Integer.parseInt((objBilledRooms[0]).toString());
      int checkedinRooms=Integer.parseInt((objCheckedinRooms[0]).toString());
      System.out.print("bookedRooms"+bookedRooms+" billedRooms"+billedRooms+" checkedinRooms"+checkedinRooms);
       if(bookedRooms==checkedinRooms){
      if(bookedRooms==billedRooms){
      
        
          
        Double Refund_amt = Double.parseDouble(Tot_Bal_amt_text.getText())  ;
        refund_bal_amt_text.setText(decimalFormat.format(Refund_amt));
        Tot_Bal_amt_text.setText("0.00");
        
        }else {
         
          JOptionPane.showMessageDialog(this, "Please create bill and adjust bill for the all rooms on this booking ID","Error", JOptionPane.OK_OPTION);
          }
      }else{int x = JOptionPane.showConfirmDialog(jPanel2, "All rooms were not checkedin on this booking Id!! \n Amount for unchecked room will be included in refund amount! \n Are you sure you want to create refund voucher?? " , "Check In Menu " , JOptionPane.YES_NO_OPTION);
                               if( x  == JOptionPane.YES_OPTION){ Double Refund_amt = Double.parseDouble(Tot_Bal_amt_text.getText())  ;
        refund_bal_amt_text.setText(decimalFormat.format(Refund_amt));
        Tot_Bal_amt_text.setText("0.00");}}
         
      }
      else{
          
         
         Double Old_Bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
         Tot_Bal_amt_text.setText(decimalFormat.format(Old_Bal_amt+Double.parseDouble(refund_bal_amt_text.getText())));
         
         refund_bal_amt_text.setText("0.00");  
      }
  //}else{ JOptionPane.showMessageDialog(this, "Voucher is already created on this Booking No.","Error", JOptionPane.OK_OPTION);}
       }catch(BasicException e){e.printStackTrace();}
   
//endeded by pratima
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jNumberKeys3KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys3KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys3KeyPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jPanel6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6AncestorAdded

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DecimalFormat dFormat = new DecimalFormat("#.##");
        try {
            int row = Roomservice_table.getSelectedRow();

            if (row < 0 ) {
                JOptionPane.showMessageDialog(this, "Please select only one table", "Cannot insert", JOptionPane.OK_OPTION);
            }
            if (row >= 0) {

                int column = Roomservice_table.getSelectedColumn();
                //  if(column==1){
                    try {
                        if (jTextField3.getText().length() > 0) {
                            boolean bool = Boolean.valueOf(GRS.getTableModel2().getValueAt(row, 4).toString());
                            if (!bool) {
                                if (column == 5) {   //Arun
                                    Double Old_adjst_amt = Double.valueOf(GRS.getTableModel2().getValueAt(row, 5).toString());
                                    Double OrgAmt = Double.valueOf(GRS.getTableModel2().getValueAt(row, 3).toString());

                                    Double New_Adjst_amt = (Double.valueOf(jTextField3.getText()).doubleValue());

                                    Double Amount = New_Adjst_amt-Old_adjst_amt;

                                    if(New_Adjst_amt<OrgAmt){

                                        Double TotRoomServ_Charge = Double.parseDouble(roomCharges.getText());
                                        Double Total_AdjstAmt =  Double.parseDouble(totalAmtAdjusted.getText());

                                        // roomCharges.setText(decimalFormat.format(TotRoomServ_Charge - Amount));
                                        // totalAmtAdjusted.setText(decimalFormat.format(Total_AdjstAmt - Amount));
                                        
                                        Double newRmChrg = TotRoomServ_Charge + New_Adjst_amt;
                                        
                                        
                                        RoomServTableModel = GRS.getTableModel2();
                                        RoomServTableModel.settext(roomCharges ,totalAmtAdjusted ,unadjustTotal_text );

                                        RoomServTableModel.setValueAt(New_Adjst_amt, row, 5);
                                        //GRS.getTableModel2().setValueAt(rate, row, 2);
                                        Roomservice_table.setModel(RoomServTableModel);
                                        jTextField3.setText(null);
                                        ShowBillDetails(GRS);
                                        roomCharges.setText(decimalFormat.format(newRmChrg));
                                        
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
                        JOptionPane.showMessageDialog(this, "Please Select row", "Error", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                    }
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Invalid Number", "Error", JOptionPane.OK_OPTION);
                e1.printStackTrace();
            }

            /*   int x = jTable1.getSelectedRow();
            int y = jTable1.getSelectedColumn();

            if(y!=6){
                jTable1.setRowSelectionInterval(x-1, x-1);
                jTable1.setColumnSelectionInterval(y+1, y+1);

                jTable1.changeSelection(x-1, y+1 ,true, true);
            }
            else{
                jTable1.setRowSelectionInterval(x+1, x+1);
                jTable1.setColumnSelectionInterval(0, 0);
            }*/
    }//GEN-LAST:event_jButton12ActionPerformed

    private void Adjust_billedAmt_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Adjust_billedAmt_checkItemStateChanged
      if(Adjust_billedAmt_check.isSelected()){
          Double BiiledAmt = Double.parseDouble((tot_chrge.getText()));
          Double Total = Double.parseDouble((totalAmtAdjusted.getText()));
          
          Total = Total + BiiledAmt;
          //tot_chrge.setText("0.00");
          totalAmtAdjusted.setText(decimalFormat.format(Total));
          
        }
      else{
          
          Double BiiledAmt = Double.parseDouble((totalAmtAdjusted.getText()));
          Double Total = Double.parseDouble((totalAmtAdjusted.getText()));
          
          Total = Total - BiiledAmt;
          
          tot_chrge.setText(decimalFormat.format(BiiledAmt));
          totalAmtAdjusted.setText(decimalFormat.format(Total));
          
      }
      
    }//GEN-LAST:event_Adjust_billedAmt_checkItemStateChanged

    private void roomChargesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomChargesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomChargesActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String bill =  JOptionPane.showInputDialog(jPanel1, "Please Enter Bill no. to print bill. " , "Bill Re-print" , JOptionPane.YES_NO_OPTION);
     String Billno = bill.toUpperCase();
     if(Billno.length()>0 && Billno!=null){
          
         String avail = CheckInTable_Model.getRoomBillID(m_App, Billno);
         if(avail.equals(Billno)){
              Launch_Bill_Statement(Billno); 
         }
         else{
            JOptionPane.showMessageDialog(this, "Enter correct bill no..!!  ", " Error ", JOptionPane.ERROR_MESSAGE);
         }
               
        
     }
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void generateFinalReport_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateFinalReport_btnActionPerformed
   if(Adjust_billedAmt_check.isSelected()){//only if check added by pratima
        Double NewUnadjustedAmount=0.00;
   int  Row_Count1 = GRS.getTableModel2().getRowCount();    
   for(int i=0;i<Row_Count1;i++){
     int row = i;
     String temp =  String.valueOf(GRS.getTableModel2().getValueAt(row, 2).toString());
     if(temp.equals("Guest Room")){
        Double AmountTemp =  Double.valueOf(GRS.getTableModel2().getValueAt(row, 6).toString());   
        if(AmountTemp>0){  
            NewUnadjustedAmount=NewUnadjustedAmount+AmountTemp;
            
        } 
         
         
     }
     
   }
   
   
   
   Double UnAdjusted_Amt = Double.parseDouble(totalAmtAdjusted.getText());
   Double TotBilledAmt = Double.parseDouble(tot_chrge.getText());
  // if(UnAdjusted_Amt<=0  ){
    if(NewUnadjustedAmount<=0.00  ){    
        
        int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to Generate Final Bill Statement ?? " , "Bill menu" , JOptionPane.YES_NO_OPTION);
      
        if(bill == JOptionPane.YES_OPTION)
        {   
           
           
                try {
                     GERERATE_FINAL_STATEMENT();
                      
                  } catch (BasicException ex) {
                       ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }




               try {
                      loaddata();
                      reset();
                  } catch (BasicException ex) {
                       ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                  }
          
        
       }  
            
        }
           
           
           
    else{
        JOptionPane.showMessageDialog(this, "Still Unadjusted Amt..! \n Please Clear Balance..!!", "Error", JOptionPane.ERROR_MESSAGE);
    }     
   }else{JOptionPane.showMessageDialog(this, "Please First adjust bill..!!", "Error", JOptionPane.ERROR_MESSAGE);}//added by pratima
    }//GEN-LAST:event_generateFinalReport_btnActionPerformed

    private void Check_bill_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Check_bill_BtnActionPerformed
        Load_Check_Bill_Report();
    }//GEN-LAST:event_Check_bill_BtnActionPerformed

    private void discount_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discount_BtnActionPerformed
        
        int Perc_flag = 0;
        Double Discount_Amt = 0.00;
         
        Discount_MasterDialog dbillList = Discount_MasterDialog.getDialog(this, m_App,true);
        try {
            dbillList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Perc_flag = dbillList.getPerc_Flag();
        
        
        Double charge = Double.parseDouble(charges.getText());
        int Flag_x = dbillList.getFlag_X();
        if(Perc_flag!=6){
         
           if(Perc_flag==1){
               Double Perc =  dbillList.getPerc_Amt();
               Discount_Amt = ((Perc*charge)/100);
               
               
               
           } 
           if(Perc_flag==0){
               
                Discount_Amt = dbillList.getFixed_amt();
               
               
               
               
           }
            
           
           if(Flag_x==1){  
            
           int bill = JOptionPane.showConfirmDialog(main_panel, " Do you want to give discount of  "+Discount_Amt+"/- on billed amount ?? " , "Discount Menu" , JOptionPane.YES_NO_OPTION);
           if(bill == JOptionPane.YES_OPTION) 
            
           {
               
              if(charge>=Discount_Amt) {
               
               Double old_discount = Double.parseDouble(Discount_Amt_label.getText());
               Discount_Amt_label.setText(decimalFormat.format(Discount_Amt));
               
               Double tot_charge = Double.parseDouble(tot_chrge.getText());
               Double tot1 = Double.parseDouble(tot.getText());
               
               Double new_tot_charge = ((tot_charge + old_discount)-Discount_Amt);
               Double new_tot = ((tot1 + old_discount)-Discount_Amt);
               
               //tot_chrge.setText(decimalFormat.format(new_tot_charge));
               tot.setText(decimalFormat.format(new_tot));
               
               String id = CheckIn_ID;
               
               
               try {
                   
                   
              new PreparedSentence(m_App.getSession()
                      , "UPDATE guestroom_checkin SET DISCOUNT=? , DISC_FLAG=0 WHERE ID=?"
                      , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE ,Datas.STRING })).exec(
                      new Object[]{Discount_Amt , id });
               
               
               JOptionPane.showMessageDialog(this, "Discount Requested for approval. Please  Wait for approval", "Success", JOptionPane.INFORMATION_MESSAGE); 
              
                   loaddata();
              
               } 
               
               catch (BasicException ex) {
                   Logger.getLogger(Hall_Check_In.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                   new MessageInf(ex).show(new JFrame());
                   
                   
               }
            
               
               
              }
              else{
                  
                   JOptionPane.showMessageDialog(this, " Billing Amount is less than discount.", "Warning", JOptionPane.WARNING_MESSAGE);  
                  
              }
               
           
            
            
            
            
            
            
           } 
            
            
           }
          
            
            
        }
        else{
            
           JOptionPane.showMessageDialog(this, " Please set discount in masters", "Error", JOptionPane.WARNING_MESSAGE);  
            
        }
        
        
    }//GEN-LAST:event_discount_BtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GuestRoomExtraBilling memList;
        try {
            memList = GuestRoomExtraBilling.getDialog(this, m_App,true);
            memList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(GuestRoomExtraBilling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
//added by pratima
    private void RnoComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RnoComboBoxItemStateChanged
        // TODO add your handling code here:
         if(RnoComboBox.getSelectedIndex()!=-1){
            
             String RecieptSelected=RnoComboBox.getSelectedItem().toString();
             int row=0;
                for(int i=0;i<GuestRoomDetailList.size();i++){
                  if(GuestRoomDetailList.get(i).getRECIEPT_NO().equals(RecieptSelected))
                  row= i; 
                }
                CheckInTableModel.RoomAdvInfo editData = (CheckInTableModel.RoomAdvInfo) ((CheckInTableModel.RoomAdvInfo)GuestRoomDetailList.get(row));
            
            
            e_checkin_text.setText(editData.getCHECK_IN_DATE());
            check_IN.setText(editData.getCHECK_IN_DATE());
            check_OUT.setText(editData.getCHECK_OUT_DATE());
            e_checkout_date.setText(editData.getCHECK_OUT_DATE());
            check_IN.setVisible(true);
            check_OUT.setVisible(true);
            try {
                chk_In_date = (Date) Formats.TIMESTAMP.parseValue(check_IN.getText());
                Chk_out_date = (Date) Formats.TIMESTAMP.parseValue(check_OUT.getText());
                
                E_chk_in = (Date) Formats.TIMESTAMP.parseValue(e_checkin_text.getText());
                E_chk_out = (Date) Formats.TIMESTAMP.parseValue(e_checkout_date.getText());
                
            } catch (BasicException ex) {
                 ex.printStackTrace();
                     new MessageInf(ex).show(this);
                Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String Booking_ID = editData.getBOOKING_ID();
            booking_id_label.setText(Booking_ID);
            
            
            AdvRec_label.setText(decimalFormat.format(editData.getBAL_AMT()));
            AdvanceAmt = Double.parseDouble(AdvRec_label.getText());
            
            MemNo_label.setText(editData.getMEMBER_NO());
            Mno = MemNo_label.getText();
            Booking_seq_id = editData.getBOOKING_SEQ_NO();
            
            Rtype = editData.getROOMTYPE();
            Adv_Pmt_ID = editData.getId();
            totC_label.setText(decimalFormat.format(editData.getTOTAL_AMOUNT()));
            Tot_Amount = Double.parseDouble(totC_label.getText());
            
            totC_label1.setText(decimalFormat.format(editData.getTOTAL_AMOUNT()));
            
            due_label.setText(decimalFormat.format((editData.getTOTAL_AMOUNT())-(editData.getBAL_AMT())));
            DueAmt = Double.parseDouble(due_label.getText());
            
            RECIEPT_NO = editData.getRECIEPT_NO();
            
            Advnce_Recv_ID = editData.getId();
            
            No_of_rooms_Bkd = editData.getNO_OF_ROOMS();
            
            No_of_days = editData.getBOOKING_DAYS();
            
            
            MemberFlag = editData.getMEMBER_FLAG();
            jLabel20.setText(editData.getROOMTYPE());
            jLabel21.setText(editData.getROOMTYPE());
            AdvRec_label1.setText(decimalFormat.format(editData.getBAL_AMT()));
            
            if(DueAmt >=0){
               jLabel33.setText("Total Amount Payable :");
            }
            else{
                jLabel33.setText("Total Due Amount :");
            }
            
            
            if(editData.getMEMBER_FLAG()!=1){
               GuestName = editData.getGuestName();
               Mname = editData.getMEMBERNAME();
            }
            else{
                GuestName = null;
                Mname = editData.getMEMBERNAME();
            }
            
            for(int i=0;i<RType_combo.getItemCount();i++){
                String x = RType_combo.getItemAt(i).toString();
                if(x.equals(Rtype)){
                    RType_combo.setSelectedIndex(i);
                    break;
                }
            }
            
            jLabel3.setText(""+No_of_days);
            jLabel4.setText(""+No_of_rooms_Bkd);
            mem_flag_label.setText(""+MemberFlag);
            
            // CALCULATION FOR SINGLE ROOM TARIFF
            
            if(editData.getMEMBER_FLAG()==1){
                SingleRoom_Tariff = editData.getMEM_Tariff();
            }
            else{
                SingleRoom_Tariff = editData.getN_MEM_Tariff();
            }
            
            
            Min_CheckInAmt = 0.00;
            Total_AdvanceRecv = editData.getADVANCE_RECV();
            
            String ADVANCE_PERC = editData.getADVANCE_PERC();
            String Advance_Array[] = ADVANCE_PERC.split("-");
            Double bkng_perc =0.00;
            Double Check_in_perc = 0.00;
            
            bkng_perc = Double.parseDouble(Advance_Array[0]);
            Check_in_perc = Double.parseDouble(Advance_Array[1]);
            
            Double Total_Perc = bkng_perc+Check_in_perc;
          
            
            
            Min_CheckInAmt = ((Total_Perc*Tot_Amount)/100);
             
            System.out.println(Min_CheckInAmt);
            
            
            
            
            // PANELS TO KEEP INVISIBLE
           
            amount_panel.setVisible(true);
            memNo.setVisible(true);
            MemNo_label.setVisible(true);
            memNo1.setVisible(true);
            jLabel3.setVisible(true);
            memNo2.setVisible(true);
            jLabel4.setVisible(true);
         }  
             
    }//GEN-LAST:event_RnoComboBoxItemStateChanged

    private void Gname_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gname_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Gname_ComboActionPerformed

    private void memActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memActionPerformed

    private void RnoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RnoComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RnoComboBoxActionPerformed

    private void RType_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RType_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RType_comboActionPerformed
//ended by pratima
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Adjust_billedAmt_check;
    private javax.swing.JLabel AdvRec_label;
    private javax.swing.JLabel AdvRec_label1;
    private javax.swing.JLabel Biiling_name_label;
    private javax.swing.JTextField BillName;
    private javax.swing.JInternalFrame Bill_frame;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Check_bill_Btn;
    private javax.swing.JLabel Discount_Amt_label;
    private javax.swing.JLabel Discount_Amt_label_Total;
    private javax.swing.JComboBox Gname_Combo;
    private javax.swing.JLabel MemNo_label;
    private javax.swing.JLabel N_Room;
    private javax.swing.JButton Pay_btn;
    private javax.swing.JLabel RNoLabel;
    private javax.swing.JComboBox RNo_Combo;
    private javax.swing.JCheckBox RT_Chng;
    private javax.swing.JComboBox RType_combo;
    private javax.swing.JComboBox RType_combo2;
    private javax.swing.JComboBox<String> RnoComboBox;
    private javax.swing.JTable Roomservice_table;
    private javax.swing.JLabel Tax_rate1_label;
    private javax.swing.JLabel Tax_rate2_label;
    private javax.swing.JLabel Tax_rate3_label;
    private javax.swing.JTextField Tot_Bal_amt_text;
    private javax.swing.JTable advance_recv_table;
    private javax.swing.JLabel advnce_recv;
    private javax.swing.JPanel amount_panel;
    private javax.swing.JLabel bal_amount_label;
    private javax.swing.JLabel basic_tax_label1;
    private javax.swing.JLabel basic_tax_label2;
    private javax.swing.JLabel basic_tax_label3;
    private javax.swing.JLabel bill_id_label;
    private javax.swing.JPanel bill_panel;
    private javax.swing.JLabel billno_label;
    private javax.swing.JLabel bkng_seq_no_label;
    private javax.swing.JLabel booking_id_label;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel charges;
    private javax.swing.JLabel check_IN;
    private javax.swing.JLabel check_OUT;
    private javax.swing.JPanel chk_out_guest_panel;
    private javax.swing.JPanel chk_panel;
    private javax.swing.JButton createBill_Button;
    private javax.swing.JLabel date1;
    private javax.swing.JLabel date2;
    private javax.swing.JLabel days;
    private javax.swing.JButton discount_Btn;
    private javax.swing.JLabel due_label;
    private javax.swing.JLabel dueamnt;
    private javax.swing.JTextField e_checkin_text;
    private javax.swing.JTextField e_checkout_date;
    private javax.swing.JButton enter;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton generateFinalReport_btn;
    private javax.swing.JLabel guest_name_label;
    private javax.swing.JTextField id_detail;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel link_name_label;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton mem;
    private javax.swing.JLabel memNo;
    private javax.swing.JLabel memNo1;
    private javax.swing.JLabel memNo2;
    private javax.swing.JLabel mem_flag_label;
    private javax.swing.JLabel mem_name_label;
    private javax.swing.JLabel mno;
    private javax.swing.JRadioButton no;
    private javax.swing.JRadioButton nonmem;
    private javax.swing.JLabel rate;
    private javax.swing.JTextField refund_bal_amt_text;
    private javax.swing.JTextField roomCharges;
    private javax.swing.JLabel roomNo_Label;
    private javax.swing.JLabel roomNo_Label1;
    private javax.swing.JLabel room_type_label;
    private javax.swing.JButton save;
    private javax.swing.JPanel tariff_panrl2;
    private javax.swing.JLabel tax1_amt_label;
    private javax.swing.JLabel tax1_id_label;
    private javax.swing.JLabel tax1_lebel;
    private javax.swing.JLabel tax2_Label;
    private javax.swing.JLabel tax2_amt_label;
    private javax.swing.JLabel tax2_id_label;
    private javax.swing.JLabel tax3_Label;
    private javax.swing.JLabel tax3_amt_label;
    private javax.swing.JLabel tax3_id_label;
    private javax.swing.JLabel tax_cnt_label;
    private javax.swing.JTextField tot;
    private javax.swing.JLabel totC_label;
    private javax.swing.JLabel totC_label1;
    private javax.swing.JTextField tot_chrge;
    private javax.swing.JLabel tot_tar2;
    private javax.swing.JTextField totalAmtAdjusted;
    private javax.swing.JTextField unadjustTotal_text;
    private javax.swing.JRadioButton yes;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
        main_panel.setVisible(true);
        bill_panel.setVisible(false);
        reset();
        Gname_Combo.setSelectedIndex(-1);
        jTabbedPane1.setSelectedIndex(1);
        
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
       RoomTableModel = (GuestRoomTableModel) app.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
       GRS = (GuestRoomService) app.getBean("com.openbravo.pos.Booking.GuestRoomService");
       GRBillModel = (GuestRoomBillModel) app.getBean("com.openbravo.pos.Booking.GuestRoomBillModel");
       m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       buttonGoup();
       Roomservice_table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       Booked_room_status = (BookedRoomStatusTableModel) app.getBean("com.openbravo.pos.Booking.BookedRoomStatusTableModel");
       
    }

    public Object getBean() {
        return this;
    }
     private void buttonGoup() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(mem);
        bg1.add(nonmem);
        
       ButtonGroup bg2 = new ButtonGroup();
       bg2.add(yes);
       bg2.add(no);
     }
     
     public void loaddata() throws BasicException{
         jLabel57.setVisible(false);
         jTextField1.setVisible(false);
         bill_panel.setVisible(false);
         main_panel.setVisible(true);
    //   jTextField1.setText(decimalFormat.format(strict1));
        GR_CheckIn_Detail = GuestRoomCheckInTable.loadCheckIn_Data(m_App);
        showPanelInfo(GR_CheckIn_Detail );
        
        Chk_out_Guest_list = GR_CheckIn_Detail.getCheck_out_guestList(m_App);
        
        if(Chk_out_Guest_list.size()>0){
          chk_out_guest_panel.setVisible(true);
          chk_out_guest_model = new Chk_out_Guest_model(Chk_out_Guest_list);
          jList2.setModel(chk_out_guest_model);
        }
        else{
           chk_out_guest_panel.setVisible(false);
        }
        
        if(mem.isSelected()){
          Mem_list = new ArrayList<Object>();
          Mem_list =  AdvanceRecv.getMemList_GL(m_App);
          Memlist_model = new ComboBoxValModel(Mem_list);
          Gname_Combo.setModel(Memlist_model);
          
        } 
        if(nonmem.isSelected()){
          Guest_list = new ArrayList<Object>();
          Guest_list =AdvanceRecv.getGuetList_GL(m_App);
          GuestList_model = new ComboBoxValModel(Guest_list);
          Gname_Combo.setModel(GuestList_model);
         
          
        }
       
       roomType_list = new ArrayList<Object>();
       roomType_list = RoomTableModel.RoomType_NamesList_Active(m_App);
       RoomTypeListModel = new ComboBoxValModel(roomType_list);
       RType_combo.setModel(RoomTypeListModel); 
       RoomTypeListModel2 = new ComboBoxValModel(roomType_list);
       RType_combo2.setModel(RoomTypeListModel2); 
         
          
       
       
     }
     
     
     public void reset(){
         
         RType_combo.setSelectedIndex(-1);
         RType_combo2.setSelectedIndex(-1);
         RNo_Combo.setSelectedIndex(-1);
         id_detail.setText(null);
         e_checkin_text.setText(null);
         e_checkout_date.setText(null);
         
         BillName.setText(null);
        
         yes.setSelected(true);
         RT_Chng.setSelected(false);
         id_detail.setText(null);
         BillName.setText(null);
         chk_panel.setVisible(false);
         check_IN.setText(null);
         check_OUT.setText(null);
         Room_Nos_list = new ArrayList<Object>();
         RoomNoListModel2 = new Room_Nos_Model(Room_Nos_list);
         jList1.setModel(RoomNoListModel2);  
         check_IN.setVisible(false);
         check_OUT.setVisible(false);
         tariff_panrl2.setVisible(false);
         RoomNo="";
        
         refund_bal_amt_text.setText("0.00");
         Recpt_list=new ArrayList<String>();
         
     }
     
     
     
     public Double getGuestRoomCharges(String RoomType) throws BasicException{
         CheckInTable_Model = CheckInTableModel.LoadGuestRoomTariff(m_App, RoomType);
         GuestRoomTariffDetailList  =  (List<CheckInTableModel.RoomTariffInfo>) CheckInTable_Model.getGuestRoomTariff();
         CheckInTableModel.RoomTariffInfo Tariff_D = (CheckInTableModel.RoomTariffInfo) ((CheckInTableModel.RoomTariffInfo)GuestRoomTariffDetailList.get(0));
         Double rate ;
         if(MemberFlag==1){
           rate = Tariff_D.getMEM_Tariff();  
         } 
         else{
           rate = Tariff_D.getN_MEM_Tariff(); 
         }
         
         int No_of_Days = No_of_days;
         int no_of_Booked_rooms = No_of_rooms_Bkd;
        
         Double Temp_1 = rate*No_of_Days*no_of_Booked_rooms;                             // TEMP_1 (WITHOUT ANY TAX CONSIST OF BASIC TARIFF )
        
         Double Tax1_Rate = Tariff_D.getTAX1();
         Double Tax2_Rate = Tariff_D.getTAX2();
         Double Tax3_Rate = Tariff_D.getTAX3();
        
         Double Temp_2 = Temp_1 + ((Temp_1*Tax1_Rate));                              // TEMP_2 WITH BASIC TAX1 CALCULATED
       
                
         int Tax2_Basic = Tariff_D.getBASIC1();
         int Tax2_Cascade =Tariff_D.getCASCADE1();
         int Tax3_Basic = Tariff_D.getBASIC1();
         int Tax3_Cascade = Tariff_D.getCASCADE2();
        
         Double Temp_3;
         
         if(Tax2_Basic==1){
            Temp_3 = ((Temp_1*Tax2_Rate));                                          // TEMP_3 WITH BASIC  OR CASCADE TAX 2     
            Temp_3 = Temp_2 + Temp_3;
         }
         else{
            Temp_3 = ((Temp_2*Tax2_Rate));
            Temp_3 = Temp_2 + Temp_3;
            
         }
        
         Double Temp_4;
        
         if(Tax3_Basic==1){
            Temp_4 = ((Temp_1*Tax3_Rate));                                          // TEMP_4 WITH BASIC  OR CASCADE TAX 3   
            Temp_4 = Temp_3 + Temp_4;
         }
         else{
            Temp_4 = ((Temp_3*Tax3_Rate));
            Temp_4 = Temp_3 + Temp_4;
         }
        
         Tot_Amount = Temp_4; 
         return Tot_Amount;
         
         
     }
     
     
       public static  String x=""; 
       public  void getRoomNoBooked(){
           if(Room_Nos_list.size()>0){
               for(int i=0;i<Room_Nos_list.size();i++){
                    x = x+Room_Nos_list.get(i).toString()+"#";
               }
           }
          
       }
      public static String getRoomNo(){
         
          String room = x;
          return room;
      }
     
     
     //     METHOD FOR LIST1
       private class Room_Nos_Model extends AbstractListModel {
            private java.util.List RoomNo;
                public Room_Nos_Model(java.util.List RNo) {
                    this.RoomNo = RNo;
                }
                public int getSize() {
                    return RoomNo.size();
                }
                public Object getElementAt(int i) {
                    return RoomNo.get(i);
                }
                public void remove(int i) {
                    RoomNo.remove(i);
                }
       }
      // METHOD FOR LIST 2
      private class Chk_out_Guest_model extends AbstractListModel {
            private java.util.List Name;
                public Chk_out_Guest_model(java.util.List Name) {
                    this.Name = Name;
                }
                public int getSize() {
                    return Name.size();
                }
                public Object getElementAt(int i) {
                    return Name.get(i);
                }
                public void remove(int i) {
                    Name.remove(i);
                }
       }
       
      
      
       public void showPanelInfo(GuestRoomCheckInTable GR_Check_In){
          jTable1.setModel(GR_Check_In.getTableModel());
       }
       
       
       public Double getRoomServiceDetails(String GuestRmNO){
                Double RoomBill = null;
                RoomBill = GRS.getTotalBillAmt(m_App, GuestRmNO);
                System.out.println(RoomBill);
                return RoomBill;   
       }
       
       
       
        public void ShowBillDetails(GuestRoomService GR_Service_detail){
          
          jTable2.setModel(GR_Service_detail.getTableModel());
          roomCharges.setText("0.00");
          RoomServTableModel = GR_Service_detail.getTableModel2();
          unadjustTotal_text.setText("0.00");
          totalAmtAdjusted.setText("0.00");
          RoomServTableModel.settext(roomCharges ,totalAmtAdjusted , unadjustTotal_text);
          Roomservice_table.setModel(RoomServTableModel);
          
          int rowcnt = RoomServTableModel.getRowCount();
          Double unadjstamt_total = 0.00;
           for(int i=0;i<rowcnt;i++){
                 int row = i;
               //  System.out.print(GRS.getTableModel2().getValueAt(row, 4).toString());
                 
                 Double unadjstamt  = Double.valueOf(RoomServTableModel.getValueAt(row, 3).toString());
                 unadjstamt_total = unadjstamt_total +unadjstamt;
                 
             } 
         unadjustTotal_text.setText(decimalFormat.format(unadjstamt_total));
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
     
         
        // UPDATE BILL SEQUENCE 
         public void UpdateRSeries() throws BasicException{ 
         
           String billnum;
           String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
    
           int x = new StaticSentence(m_App.getSession()
                       // , "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME =(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) "
                  , "UPDATE SEQUENCEDETAIL SET BMAX=BMAX+1 WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=?"

                  , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING }))
                        .exec(new Object[] {uname,uname});
         
          }
         
              String BillID;
              String Mem_No;
              String MemberName;
              String Customer;
              Date Chk_in;
              String Chk_in_date_string;
              Date Chk_Out;
              String Chk_Out_date_string;
              Double Rate;
              Double Tax_total;
              String Rm_Serv_ID = "";
              Double Rm_Serv_Amt=0.00;
              Double Advnce_Recv;
              int paid;
              String Reciept;
              String Room_No;
              String narrations;
              Double bal_amt;
              String Tax1_N;
              String Tax2_N;
              String Tax3_N;
              String BillingName;
              String Id_Details;
              int no_of_romms;
              String RoomType;
              String Customer_ID;
              Double tot_charge;
              // accounts
              int Basic1;
              int Basic2;
              
              
              String TRANSREF;
              String NARRATION;
              String Advance_Acct_ID;
              String Revenue_Acct_ID;
              String TRANSTYPE;
              String Rfd_Voucher_No;
              Double Amt_Paid;
              String UserAccount;
              
              String Tax1_ID;
              String Tax2_id;
              String Tax3_id;
              
              String Tax1_Accnt;
              String Tax2_Accnt;
              String Tax3_accnt;
              List<Object> Tax_Accnt_List;
              List<Double> Tax_Amt_List;
              String TID;
              List<Object> AdvanceReciept_list;
             
              
              List<Object> AdvRecptAdjusted = new ArrayList<Object>();  
              List<Double> BalAmtAdjusted = new ArrayList<Double>();
              List<Double> AdjAmtList = new ArrayList<Double>();
              
              Date Billed_CheckIn_Date;
              Date Billed_CheckOut_Date;
              
              
 public void Generate_GuestRoomBill() throws BasicException{
              
              Tax_Accnt_List = new ArrayList<Object>();
              Tax_Amt_List = new ArrayList<Double>();
              Mem_No = mno.getText();
              Customer = GR_CheckIn_Detail.getCustomerID(m_App, Mem_No);
              
              if(MemberFlag!=1){
                   GuestName = Biiling_name_label.getText();
              }
              else{
                  GuestName = null;
              }
             
              Chk_in = (Date) Formats.DATE.parseValue(date1.getText());
              Chk_Out = (Date) Formats.DATE.parseValue(date2.getText());
              
              Rate = Double.parseDouble(charges.getText());
              Tax_total = Double.parseDouble(tax1_amt_label.getText()) + Double.parseDouble(tax2_amt_label.getText()) + Double.parseDouble(tax3_amt_label.getText()) ;
              Customer_ID = CheckInTable_Model.getCustIDby_SearchKey(m_App, Mem_No);
              MemberName = mem_name_label.getText();
              
              // TAX ACCOUNTS 
              Tax_count = Integer.parseInt(tax_cnt_label.getText());
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
             // System.out.println("tax count = "+Tax_count);
              
              
              
              Advnce_Recv = Double.parseDouble(advnce_recv.getText());
              BillingName = Biiling_name_label.getText();
              Booking_seq_id = bkng_seq_no_label.getText();
              
           //   BillID = getNextBillID();
              booking_id = booking_id_label.getText();
              Room_No = roomNo_Label1.getText();
              No_of_days = Integer.parseInt(days.getText());
              RoomType = room_type_label.getText();
              
              tot_charge = Double.parseDouble(tot.getText());
             // bal_amt = (Advnce_Recv-tot_charge);
              bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
              String link_N = link_name_label.getText();
              
              TRANSREF = CheckInTable_Model.getTransRef(m_App);
              NARRATION = "Recieved Bill Against Room Booking_ID : "+Booking_seq_id+ "." ;
              
              Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Room(m_App, RoomType_ID);
              Revenue_Acct_ID = CheckInTable_Model.getReve_Acct_Room(m_App, RoomType_ID);
              TRANSTYPE = "C"; 
              
              
              
             
            
              UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            
              
              // FOR ADVANCE RECIEPTS ------------------------------------------------------------------------------------
              
            //  int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
            //  for(int j=0;j<rowCount2;j++){
             //    int row1 = j; 
              //   String reciept    = String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 1).toString());
              //   AdvanceReciept_list.add(reciept);
              //  }
              
              
              AdvanceReciept_list = GRS.getAdvanceRecieved_RecieptList(m_App, booking_id);
              
              
              // LOAD ALREADY ADJUSTED BILLS ------------------------------------------------------------------------------- 
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
              
              
              
              if(AdvanceReciept_list.size()>0 ){
              try{
                       GRS = GuestRoomService.Load_Adjusted_Bills(m_App, AdvanceReciept_list , link_N);
                   }
                    catch (BasicException ex) {
                         ex.printStackTrace();
                         new MessageInf(ex).show(this);
                          Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        BillInfoList = (List<GuestRoomService.BillInfo>) GRS.getBillInfo_Path();
                        if(BillInfoList==null){
                             BillInfoList = new ArrayList<GuestRoomService.BillInfo>();
                 }
              
              }        
               
            
               for(int i=0;i<BillInfoList.size();i++){          
                        GuestRoomService.BillInfo getData = GRS.getBillInfo_Path().get(i);
                        Rm_Serv_ID = Rm_Serv_ID + getData.getID()+"#"+getData.getPLACE()+"#"+getData.getAMOUNT()+"#";
               }
              if(BillInfoList.size()<=0){
                  Rm_Serv_ID = null;
              }
              
              Rm_Serv_Amt =  GRS.getTotalRmServAmt(m_App, AdvanceReciept_list, link_N);
              
             // -------------------------------------------------------------------------------------------------------------- 
              
             TID = UUID.randomUUID().toString();  
             Amt_Paid = Double.parseDouble(dueamnt.getText());
              
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
                        
                        
                        
                        
                            int insert_Bill  =  new PreparedSentence(m_App.getSession(), "INSERT INTO guestroom_bill (ID , CUSTOMER , ROOM_NO , CHK_IN , CHK_OUT ,RATE , TAX_TOTAL , RM_SERV_ID , RM_SERV_AMT , ADVNCE_RECV  , PAID ,  RECIEPT , CRBY , CRDATE , CRHOST , NARRATIONS , PARENTID , TAX1 , TAX2 , TAX3 , BILLNAME , ID_DETAIL , No_OF_ROOMS , NO_OF_DAYS , ROOMTYPE , BOOKING_SEQ_NO , BAL_AMT , AMT_PAID , GUESTNAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING, Datas.INT , Datas.INT , Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING})).exec
                                                                       (new Object[]{BillID ,Customer ,Room_No ,Chk_in,Chk_Out,Rate,Tax_total ,Rm_Serv_ID,Rm_Serv_Amt,Advnce_Recv ,paid ,Reciept, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , narrations , CheckIn_ID , Tax1_N , Tax2_N , Tax3_N , BillingName , Id_Details , no_of_romms , No_of_days ,RoomType , Booking_seq_id , bal_amt , Amt_Paid , GuestName}); 

                             
                            int x = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET ACTIVE=0 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {CheckIn_ID});
                            

                            int Z = new StaticSentence(m_App.getSession(), "UPDATE guestroom_booked_details SET CHK_IN_FLAG=2 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {booking_id});
 
                            
                            
                            int y = new StaticSentence(m_App.getSession(), "UPDATE guestroom_advance_payment SET BAL_AMT=? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {bal_amt , Advnce_Recv_ID});

                          
                            
                           for(int i=0;i<AdvRecptAdjusted.size();i++){
                            
                            String Recieptno = AdvRecptAdjusted.get(i).toString();
                            Double BalAmt = BalAmtAdjusted.get(i);
                            Double Adjamt = AdjAmtList.get(i);
                               
                            int Update_Advce_GuestRoom3 = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET BAL_AMT=BAL_AMT-? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {Adjamt , Recieptno });
                            
                            
                             int Update_Advce_GuestRoom = new StaticSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET advnce_adjust=advnce_adjust+? WHERE RECIEPT_NO=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {Adjamt , Recieptno });
                            
                            
                            
                           }
                           
                           
                            
                       
                           
                         // ACCOUNTING ENTRIES.................................................................................................................#AAKASH  
                           
                           
                         int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                        ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , BillID ,(Advnce_Recv-bal_amt) , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                         TRANSREF = MemberName+","+Mem_No+", Guest Room rent :"+ Rate+" /-. Advance paid ="+Advnce_Recv+"/-."  ;
                        
                         int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{(Advnce_Recv-bal_amt) ,Advance_Acct_ID ,AjPerDate});


                         int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{(Advnce_Recv-bal_amt),Advance_Acct_ID});


                         
                         
                         int  INSERT_INTO_ACCOUNT2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                        ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , BillID ,Rate , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Revenue_Acct_ID , TID , new Date() , "C" , 1    });                                                                                                

                        
                         
                         
                         int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Rate ,Revenue_Acct_ID ,AjPerDate});   

                                    
                          int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Rate,Revenue_Acct_ID});

                            
                         
                         
                         
                         
                         
                        // FOR  TAX ACCOUNTING ENTRIES.... 
                         for(int i=0 ; i< Tax_Accnt_List.size() ; i++){
                            String TaxacntId = Tax_Accnt_List.get(i).toString();
                            Double taxamt = Tax_Amt_List.get(i);
                            TRANSREF = "tax collected for rooms";
                            NARRATION = "tax amt collected for room "+room_type_label.getText();
                            
                            int  INSERT_INTO_ACCOUNT3  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , BillID , taxamt , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , TaxacntId , TID , new Date() , "C" , 1    });                                                                                                

                            
                            
                            int Update_AJPeriodTotals3 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{taxamt ,TaxacntId ,AjPerDate});   

                                    
                            int Update_TrailBalance3 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{taxamt,TaxacntId});

                            
                            
                            
                            
                            
                            
                        } 
                         
                         
                        Double RefundAmt = Double.parseDouble(refund_bal_amt_text.getText());
                        
                         if(RefundAmt>0){    
                                
                                 InsertRefund_VoucherEntry(BillID);
                             
                             }
                           
                                
                                
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Bill Generated Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    bill_id_label.setText(BillID);
                                    Launch_Bill(BillID);
                                    UpdateRefundVoucher();
                                    Launch_Bill_Statement(BillID);
                                    //  SEND SMS .....
                                    String MobNo = dmang.getcustMobileNoByCustID(Customer_ID);
                                    String Msg = "Dear Member,\nYou have charged Rs."+tot_charge+"/- for booking of "+RoomType+" Room From "+Chk_in_date_string+" to "+Chk_Out_date_string+". Advnce Recvd Rs."+AdvanceAmt+"/- .";
                                    System.out.println("Cancel msg = "+Msg.length());
                                    if(MobNo!=null && MobNo.trim().length()>0){
                                          dmang.InsertActiveMsgTable(Msg, Customer_ID , MobNo, 2);
                                     }
                                    
                                    
                            } catch (BasicException ex) {
                                 ex.printStackTrace();
                                 new MessageInf(ex).show(this);
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                                }

         }
         
   // generate guestroom bill --------------------------------------------------------------------------------      
 public void Generate_GuestRoomBill2() throws BasicException{
     
     
      Mem_No = mno.getText();
      Customer = GR_CheckIn_Detail.getCustomerID(m_App, Mem_No);
              
      if(MemberFlag!=1){
           GuestName = Biiling_name_label.getText();
       }
       else{
             GuestName = null;
       }
      
      Advnce_Recv = Double.parseDouble(advnce_recv.getText());
      Room_No = roomNo_Label1.getText();
      Chk_in = (Date) Formats.TIMESTAMP.parseValue(date1.getText());
      Chk_Out = (Date) Formats.TIMESTAMP.parseValue(date2.getText());
      Billed_CheckIn_Date = (Date) Formats.TIMESTAMP.parseValue(date1.getText());
      Billed_CheckOut_Date = new Date();
              
              
      Rate = Double.parseDouble(charges.getText());
      Tax_total = Double.parseDouble(tax1_amt_label.getText()) + Double.parseDouble(tax2_amt_label.getText()) + Double.parseDouble(tax3_amt_label.getText()) ;
      Customer_ID = CheckInTable_Model.getCustIDby_SearchKey(m_App, Mem_No);
      MemberName = mem_name_label.getText();
      paid = 0;
      BillingName = Biiling_name_label.getText();
      Room_No = roomNo_Label1.getText();
      No_of_days = Integer.parseInt(days.getText());
      RoomType = room_type_label.getText();
      Booking_seq_id = bkng_seq_no_label.getText();
      bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
      
      Double DisAmt = Double.parseDouble(Discount_Amt_label.getText());
      Discount_Amt = (DisAmt);
      
      
      
     
             
      Transaction t = new Transaction(m_App.getSession()) {
             
      Double Tax1_Amt = Double.parseDouble(tax1_amt_label.getText());
      Double Tax2_Amt = Double.parseDouble(tax2_amt_label.getText());
      Double Tax3_Amt = Double.parseDouble(tax3_amt_label.getText());   
          
      String z = Formats.DATE.formatValue(new Date());
      Date Avail_Date = (Date) Formats.DATE.parseValue(z);
     // System.out.println("availibility date : "+Avail_Date);     
      
      
      
                    @Override
                    protected Object transact() throws BasicException {
                     
                          BillID = getNextBillID();
                        
                          if(BillID!=null && BillID.trim().length()>0 && BillID!=""){
                        
                        
                            int insert_Bill  =  new PreparedSentence(m_App.getSession(), "INSERT INTO guestroom_bill (ID , CUSTOMER , ROOM_NO , CHK_IN , CHK_OUT ,RATE , TAX_TOTAL , RM_SERV_ID , RM_SERV_AMT , ADVNCE_RECV  , PAID ,  RECIEPT , CRBY , CRDATE , CRHOST , NARRATIONS , PARENTID , TAX1 , TAX2 , TAX3 , BILLNAME , ID_DETAIL , No_OF_ROOMS , NO_OF_DAYS , ROOMTYPE , BOOKING_SEQ_NO , BAL_AMT  , GUESTNAME , BILL_CHK_IN , BILL_CHK_OUT , DISCOUNT , TAX1_AMT , TAX2_AMT , TAX3_AMT,AMT_PAID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING, Datas.INT , Datas.INT , Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.DOUBLE ,  Datas.DOUBLE ,  Datas.DOUBLE ,  Datas.DOUBLE , Datas.DOUBLE})).exec
                                                                       (new Object[]{BillID ,Customer ,Room_No ,Chk_in,Chk_Out,Rate,Tax_total ,Rm_Serv_ID,Rm_Serv_Amt,Advnce_Recv ,paid ,Reciept, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , narrations , CheckIn_ID , Tax1_N , Tax2_N , Tax3_N , BillingName , Id_Details , no_of_romms , No_of_days ,RoomType , Booking_seq_id , bal_amt  , GuestName , Billed_CheckIn_Date , Billed_CheckOut_Date , Discount_Amt , Tax1_Amt , Tax2_Amt , Tax3_Amt , 0.00}); 

                             
                           // int x = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET ACTIVE=0 WHERE ID=?"
                                                                //        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {CheckIn_ID});
                            

                          //  int Z = new StaticSentence(m_App.getSession(), "UPDATE guestroom_booked_details SET CHK_IN_FLAG=2 WHERE ID=?"
                                               //                         , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {booking_id});
 
                            
                            
                           int x = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET BILLED=1 WHERE ID=?"
                                                                      , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {CheckIn_ID});
                             
                            
                         
                         
                           int x1 = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET BILLNO=? WHERE ID=?"
                                                                      , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.STRING })).exec(new Object[] { BillID , CheckIn_ID});
                             
                       
                           
                           
                          int guestroom_availibility = new StaticSentence(m_App.getSession(), "UPDATE guestroom_availibility SET BOOKED_ROOMS=BOOKED_ROOMS-1  WHERE ROOM_TYPE=? AND BOOKED_DATES=?  "
                                                                      , new SerializerWriteBasic(new Datas[] {Datas.STRING , Datas.TIMESTAMP })).exec(new Object[] { RoomType_ID , Avail_Date});
                             
                       
                          }
                                
                                
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Bill Generated Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    bill_id_label.setText(BillID);
                                    Launch_Bill(BillID);
                                    
                                   // Launch_Bill(BillID);
                                  //  UpdateRefundVoucher();
                                  //  Launch_Bill_Statement(BillID);
                                    //  SEND SMS .....
                                  //  String MobNo = dmang.getcustMobileNoByCustID(Customer_ID);
                                  //  String Msg = "Dear Member,\nYou have charged Rs."+tot_charge+"/- for booking of "+RoomType+" Room From "+Chk_in_date_string+" to "+Chk_Out_date_string+". Advnce Recvd Rs."+AdvanceAmt+"/- .";
                                 //   System.out.println("Cancel msg = "+Msg.length());
                                //    if(MobNo!=null && MobNo.trim().length()>0){
                                 //         dmang.InsertActiveMsgTable(Msg, Customer_ID , MobNo, 2);
                                  //   }
                                   
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                           }
     
      
      
     
 
     
     
 }
    // GENERATE FINAL BILL STATEMENT ---------------------------------------------------------------------------------------------
 
 public void GERERATE_FINAL_STATEMENT() throws BasicException{
              
              Tax_Accnt_List = new ArrayList<Object>();
              Tax_Amt_List = new ArrayList<Double>();
              Mem_No = mno.getText();
              Customer = GR_CheckIn_Detail.getCustomerID(m_App, Mem_No);
              
              if(MemberFlag!=1){
                   GuestName = Biiling_name_label.getText();
              }
              else{
                  GuestName = null;
              }
             
              Chk_in = (Date) Formats.TIMESTAMP.parseValue(date1.getText());
              Chk_Out = (Date) Formats.TIMESTAMP.parseValue(date2.getText());
              
              Chk_in_date_string = date1.getText();
              Chk_Out_date_string = date2.getText();
              
              Rate = Double.parseDouble(charges.getText());
              Tax_total = Double.parseDouble(tax1_amt_label.getText()) + Double.parseDouble(tax2_amt_label.getText()) + Double.parseDouble(tax3_amt_label.getText()) ;
              Customer_ID = CheckInTable_Model.getCustIDby_SearchKey(m_App, Mem_No);
              MemberName = mem_name_label.getText();
              Discount_Amt = Double.parseDouble(Discount_Amt_label.getText());
              
              // TAX ACCOUNTS 
              Tax_count = Integer.parseInt(tax_cnt_label.getText());
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
             // System.out.println("tax count = "+Tax_count);
              
              
              
              Advnce_Recv = Double.parseDouble(advnce_recv.getText());
              BillingName = Biiling_name_label.getText();
              Booking_seq_id = bkng_seq_no_label.getText();
              
           //   BillID = getNextBillID();
              booking_id = booking_id_label.getText();
              Room_No = roomNo_Label1.getText();
              No_of_days = Integer.parseInt(days.getText());
              RoomType = room_type_label.getText();
              
             
              
              
              tot_charge = Double.parseDouble(tot.getText());
             // bal_amt = (Advnce_Recv-tot_charge);
              bal_amt = Double.parseDouble(Tot_Bal_amt_text.getText());
              String link_N = link_name_label.getText();
              
              TRANSREF = CheckInTable_Model.getTransRef(m_App);
              NARRATION = "Recieved Bill Against Room Booking_ID : "+Booking_seq_id+ "." ;
              
              Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Room(m_App, RoomType_ID);
              Revenue_Acct_ID = CheckInTable_Model.getReve_Acct_Room(m_App, RoomType_ID);
              TRANSTYPE = "C"; 
              
              
              BillID = billno_label.getText();
             
            
              UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                            
              
          
              
              
              AdvanceReciept_list = GRS.getAdvanceRecieved_RecieptList(m_App, booking_id);
              
              
              // LOAD ALREADY ADJUSTED BILLS ------------------------------------------------------------------------------- 
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
              
              
              
              if(AdvanceReciept_list.size()>0 ){
              try{
                       GRS = GuestRoomService.Load_Adjusted_Bills(m_App, AdvanceReciept_list , link_N);
                   }
                    catch (BasicException ex) {
                         ex.printStackTrace();
                     new MessageInf(ex).show(this);
                          Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        BillInfoList = (List<GuestRoomService.BillInfo>) GRS.getBillInfo_Path();
                        if(BillInfoList==null){
                             BillInfoList = new ArrayList<GuestRoomService.BillInfo>();
                 }
              
              }        
               
            
               for(int i=0;i<BillInfoList.size();i++){          
                        GuestRoomService.BillInfo getData = GRS.getBillInfo_Path().get(i);
                        Rm_Serv_ID = Rm_Serv_ID + getData.getID()+"#"+getData.getPLACE()+"#"+getData.getAMOUNT()+"#";
               }
              if(BillInfoList.size()<=0){
                  Rm_Serv_ID = null;
              }
              
              Rm_Serv_Amt =  GRS.getTotalRmServAmt(m_App, AdvanceReciept_list, link_N);
              
             // -------------------------------------------------------------------------------------------------------------- 
              
             TID = UUID.randomUUID().toString();  
             Amt_Paid = Double.parseDouble(dueamnt.getText());
              
             
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
                        
                        
                        
                        
                      
                             
                            int x = new StaticSentence(m_App.getSession(), "UPDATE guestroom_checkin SET ACTIVE=0 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {CheckIn_ID});
                            

                            int Z = new StaticSentence(m_App.getSession(), "UPDATE guestroom_booked_details SET CHK_IN_FLAG=2 WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.STRING })).exec(new Object[] {booking_id});
 
                            
                            
                            int y = new StaticSentence(m_App.getSession(), "UPDATE guestroom_advance_payment SET BAL_AMT=? WHERE ID=?"
                                                                        , new SerializerWriteBasic(new Datas[] {Datas.DOUBLE , Datas.STRING })).exec(new Object[] {bal_amt , Advnce_Recv_ID});

                          
                            
                          
                           
                           
                            
                       
                           
                         // ACCOUNTING ENTRIES.................................................................................................................#AAKASH  
                         
                        TRANSREF = "Guest Room  Booking"  ;   
                            
                            
                           
                        
                         
                         
                         int  INSERT_INTO_ACCOUNT2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                        ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , BillID ,(Rate-Discount_Amt) , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Revenue_Acct_ID , TID , new Date() , "C" , 1    });                                                                                                

                        
                         
                         
                         int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Rate ,Revenue_Acct_ID ,AjPerDate});   

                                    
                          int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Rate,Revenue_Acct_ID});

                            
                         
                         
                         
                         
                         Double TotalTax = 0.00;
                        // FOR  TAX ACCOUNTING ENTRIES.... 
                         for(int i=0 ; i< Tax_Accnt_List.size() ; i++){
                            String TaxacntId = Tax_Accnt_List.get(i).toString();
                            Double taxamt = Tax_Amt_List.get(i);
                            TRANSREF = "tax collected for rooms";
                            NARRATION = "tax amt collected for room "+room_type_label.getText();
                            TotalTax=TotalTax+taxamt;
                            int  INSERT_INTO_ACCOUNT3  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , BillID , taxamt , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , TaxacntId , TID , new Date() , "C" , 1    });                                                                                                

                            
                            
                            int Update_AJPeriodTotals3 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{taxamt ,TaxacntId ,AjPerDate});   

                                    
                            int Update_TrailBalance3 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{taxamt,TaxacntId});

                        } 
                          TRANSREF = "Guest Room  Booking"  ; 
                         NARRATION = "Recieved Bill Against Room Booking_ID : "+Booking_seq_id+ "." ;
                         
                         
                         int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                        ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , BillID ,(Rate-Discount_Amt)+TotalTax , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                         //TRANSREF = MemberName+","+Mem_No+", Guest Room rent :"+ (Rate-Discount_Amt)+" /-. Advance paid ="+Advnce_Recv+"/-."  ;
                        
                         int Update_AJPeriodTotals_2  =  new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=? AND EDATE=? "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{(Rate-Discount_Amt)+TotalTax ,Advance_Acct_ID ,AjPerDate});


                         int Update_TrailBalance2 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT+?) where ACCOUNTID=?  "
                                                            , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{(Rate-Discount_Amt)+TotalTax,Advance_Acct_ID});

 
                         
                         
                         
                         
                        Double RefundAmt = Double.parseDouble(refund_bal_amt_text.getText());
                        
                         if(RefundAmt>0){    
                                
                                 InsertRefund_VoucherEntry(BillID);
                             
                             }
                           
                                
                                
                            
                             return null;
                          }
                         };
                   
                            try {
                                    t.execute();
                                    JOptionPane.showMessageDialog(this, "Bill Generated Sucsessfully..!!", "Success", JOptionPane.INFORMATION_MESSAGE);  
                                    bill_id_label.setText(BillID);
                                   // Launch_Bill(BillID);
                                    UpdateRefundVoucher();
                                    Launch_Bill_Statement(BillID);
                                    //  SEND SMS .....
                                    String MobNo = dmang.getcustMobileNoByCustID(Customer_ID);
                                    String Msg = "Dear Member,\nYou have charged Rs."+tot_charge+"/- for booking of "+RoomType+" Room From "+Chk_in_date_string+" to "+Chk_Out_date_string+". Advnce Recvd Rs."+AdvanceAmt+"/- .";
                                    System.out.println("Cancel msg = "+Msg.length());
                                    if(MobNo!=null && MobNo.trim().length()>0){
                                          dmang.InsertActiveMsgTable(Msg, Customer_ID , MobNo, 2);
                                     }
                                    
                                    
                            } catch (BasicException ex) {
                                    Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
                                    new MessageInf(ex).show(new JFrame());
                                }
                            
              }

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
            
                        
                        
            
              Double RA =Double.parseDouble(refund_bal_amt_text.getText());
              Double Refund_Amt = (RA);
              
              int Refund_Voucher =  new PreparedSentence(m_App.getSession(), "INSERT INTO room_hall_refund_voucher (ID , RV_NO , CUST_ID , MEM_NO , BOOKING_SEQ_NO ,BILLED_AMT , ADVNCE_AMT , REFUND_AMT , CHK_IN_ID , REFUND_BY  , REFUND_HOST ,  REFUND_DATE , ROOMTYPE ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING })).exec
                                  (new Object[]{UUID.randomUUID().toString() ,Rfd_Voucher_No ,  Customer , Mem_No , Booking_seq_id ,tot_charge , Advnce_Recv ,Refund_Amt , CheckIn_ID , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() , new Date()  , RoomType }); 

              
              TRANSREF = Mem_No + " , Refund Voucher No : "+Rfd_Voucher_No+" # amt :"+Refund_Amt+" /-  created againts booking no: "+Booking_seq_id ;
              NARRATION = "Refund of "+Refund_Amt+ " /- to member : "+Mem_No+" . Voucher no "+Rfd_Voucher_No;
            
            //added by pratima
              int rowCount2 =   AdvanceAdjustTable_model.getTableModel2().getRowCount();
               for(int j=0;j<rowCount2;j++){
                 int row1 = j; 
                 Boolean ticked1  = Boolean.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 3).toString()); 
                 if(ticked1){
                    Double  Refund_AmtTemp=Double.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 5).toString()); 
                    String x   = String.valueOf(AdvanceAdjustTable_model.getTableModel2().getValueAt(row1, 1).toString());
                    if(Refund_AmtTemp>0.00){
                      String paymenttype = (String)new StaticSentence(m_App.getSession(),"SELECT payment FROM payments where receipt=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(x);
                   if(paymenttype.equals("cheque")){
                   UserAccount =  m_App.getAppUserView().getUser().getchequeaccount();
                    }
                    else UserAccount =  m_App.getAppUserView().getUser().getcashaccount();
                    
                 
                    //ended by pratima
             // for(int i=0;i<AdvanceReciept_list.size();i++){
              
            //  String x = AdvanceReciept_list.get(0).toString();
              
              int updateadvnce_agnst_guestroom   = new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom SET ADVNCE_ADJUST=(ADVNCE_ADJUST+?) , BAL_AMT=(BAL_AMT-?)  where RECIEPT_NO=?  "
                                                , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.DOUBLE , Datas.STRING})).exec(new Object[]{Refund_AmtTemp, Refund_AmtTemp , x });
                   
              int Update_AJPeriodTotals1 =   new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=? AND EDATE=? "
                                 , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{Refund_AmtTemp ,UserAccount ,AjPerDate});   

                                    
              int Update_TrailBalance1 = new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT+?) where ACCOUNTID=?  "
                                    , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{Refund_AmtTemp,UserAccount});
                
                    }
                 }
              }
              
               int  INSERT_INTO_ACCOUNT5  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_seq_id ,Refund_Amt , Chk_Out , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccount , TID , new Date() , "C" , 1    });                                                                                                

              
              
             
              generateRefundVoucher();
              
            
        } 
         
         
         
            
      public void Launch_Bill(String Billid){
         Date edate = new Date();
         String newDate = Formats.TIMESTAMP.formatValue(new Date());
         Map reportparam = new HashMap();
         reportparam.put("enddate", edate);
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForGuestRoomCheckIN ds= null;
         
         String RmNo = roomNo_Label1.getText();
        
         String linkname = link_name_label.getText();
         
         
             try {
                GRBillModel = GuestRoomBillModel.LoadGuestRoomCheckInDetail(m_App, linkname , Billid);
             } catch (BasicException ex) {
                  ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             GR_BillList  =  (List<GuestRoomBillModel.RoomAdvInfo>) GRBillModel.getGuestRoomPath();
           
         ds = new DataSourceForGuestRoomCheckIN(GR_BillList);
         data1.setDataSource(ds);
         
        
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("date",newDate);
         
         
         
        JasperPrint jp = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/GuestRm.jrxml", reportparam, false, data1, true, "GuestRoomBill");
    
      } 
      
       public void Launch_Bill_Statement(String Billid){
         Date edate = new Date();
         String newDate = Formats.TIMESTAMP.formatValue(new Date());
         Map reportparam = new HashMap();
         reportparam.put("enddate", edate);
        
         DataSourceProvider data1 = new DataSourceProvider();
         DataSourceForGuestRoomCheckIN ds= null;
         
         String RmNo = roomNo_Label1.getText();
        
         String linkname = link_name_label.getText();
         
         
             try {
                GRBillModel = GuestRoomBillModel.LoadGuestRoomCheckInDetail(m_App, linkname , Billid);
             } catch (BasicException ex) {
                  ex.printStackTrace();
                     new MessageInf(ex).show(this);
                      Logger.getLogger(GuestRoom_Check_in.class.getName()).log(Level.SEVERE, null, ex);
              }
             GR_BillList  =  (List<GuestRoomBillModel.RoomAdvInfo>) GRBillModel.getGuestRoomPath();
           
         ds = new DataSourceForGuestRoomCheckIN(GR_BillList);
         data1.setDataSource(ds);
         
        
         reportparam.put("CLUBNAME", m_App.getSession().getCompanyName());
         reportparam.put("ADDR", m_App.getSession().getCompanyAddress());
         reportparam.put("date",newDate);
         
         
         
     
         JasperPrint jp1 = JasperReportNew.runReport(m_App , "./reports/com/openbravo/reports/GuestRmStatement.jrxml", reportparam, false, data1, true, "GuestRoomBill");  
      } 
      
      
      // GENERATE JASPER FOR VOUCHER 
       public void generateRefundVoucher()
        {
         
            Map reportparams = new HashMap();
            List<Object> list = new ArrayList<Object>();
             
             DataSourceProvider data1 = new DataSourceProvider(list);
           
             String Message=null;
             
             String RoomType = room_type_label.getText();
             Double refundAmt = Double.parseDouble(refund_bal_amt_text.getText()) ;
             
           //  Message = "Dear Member, \n        Refund of Rs. "+refundAmt +" /-  after adjustment of  all advances recieved for Hall / Room Booking.  \n (Booking ID : "+Booking_seq_id+ ") Booked By "+BillingName+ ". "  ;
             reportparams.put("V_NO",Rfd_Voucher_No );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             reportparams.put("MEM_NAME", MemberName);
             reportparams.put("M_NO", Mem_No);
            // reportparams.put("MAIN_TEXT", Message);
             reportparams.put("CREATEBY",  m_App.getAppUserView().getUser().getName());
             reportparams.put("CRHOST" , m_App.getProperties().getHost());
             reportparams.put("MEMSIGN", BillingName);
             
             reportparams.put("HALLNAME", RoomType);
             reportparams.put("bookingseqno", Booking_seq_id);
             reportparams.put("bookingdateinfo", (Chk_in+" , Ckeck-Out : "+Chk_Out+" ."  ));
             reportparams.put("advanceamt", decimalFormat.format(Advnce_Recv));
             reportparams.put("totalbillamount", decimalFormat.format(tot_charge));
            
             reportparams.put("cancellationperc", "0.00%");
             reportparams.put("fixedCharge", "0.00");
             reportparams.put("cancellationCharge", "0.00");
             reportparams.put("refundamt", decimalFormat.format(refundAmt));
             
             
             
             
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
          JOptionPane.showMessageDialog(null, "Please Specify the Refund Voucher Series in General Table", "Cannot Create Refund", JOptionPane.WARNING_MESSAGE);
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
       
       
       
    /// TO DISPLAY ADVANCE RECIEVED LIST IN TABLE .. ---------------------------------------------------------------- #AAKASH      
       
    public void ShowAdvanceInfo(AdvanceAdjustTableModel AdvanceAdjustTable_Model){
        
        roomCharges.setText("0.00");
        AdvanceListTableModel = AdvanceAdjustTable_Model.getTableModel2();
        //Tot_Bal_amt_text.setText("0.00");
       
        
        AdvanceListTableModel.settext(totalAmtAdjusted , Tot_Bal_amt_text , roomCharges);
        advance_recv_table.setModel(AdvanceListTableModel);
        
    }   
       
       
      //Akash:end
    private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            jTextField3.setText(null);
        } else if (cTrans == '+' || cTrans == '-') {
        } else if (cTrans == ' ' || cTrans == '=') {
        } else {

            jTextField3.setText(jTextField3.getText() + cTrans);
        }
    }
   
       
    
    
    //  LOAD DATA FOR ROOM SERVICE TABLE AND ADVANCE RECV TABLE --------------------------------------------------------------------------
    
    public void LoadDataforTables() throws BasicException{
          
        String Cust_n = GRS.getCust_link_name(m_App, RoomNo);
        Mem_No = mno.getText();
        Date CheckInDate = (Date) Formats.TIMESTAMP.parseValue(date1.getText());
        Customer = GR_CheckIn_Detail.getCustomerID(m_App, Mem_No);
        if(Cust_n!="null"){

                             try{
                                      GRS = GuestRoomService.load_Curr_Bills(m_App, Cust_n , Customer , RoomNo , CheckInDate);
                                  }
                                  catch (BasicException ex) {
                                       ex.printStackTrace();
                                          new MessageInf(ex).show(this);
                                     Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                 BillInfoList = (List<GuestRoomService.BillInfo>) GRS.getBillInfo_Path();
                                 if(BillInfoList==null){
                                      BillInfoList = new ArrayList<GuestRoomService.BillInfo>();
                                 }

                                 ShowBillDetails(GRS);
                                 totalAmtAdjusted.setText("0.00");
                            }
                            
             if(Cust_n!="null"){
               
                    try{
                             AdvanceAdjustTable_model = AdvanceAdjustTableModel.load_Curr_Bills(m_App , booking_id);
                         }
                         catch (BasicException ex) {
                              ex.printStackTrace();
                                  new MessageInf(ex).show(this);
                            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        AdvanceInfoList = (List<AdvanceAdjustTableModel.AdvanceInfo>) AdvanceAdjustTable_model.getAdvanceList();
                        if(AdvanceInfoList==null){
                             AdvanceInfoList = new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
                        }
              
                        ShowAdvanceInfo(AdvanceAdjustTable_model);
                   
                   }
    }
    
    
    
    
    // CHECK BILL REPORT FOR GUEST ROOM BILLLS 
    
    
    public void Load_Check_Bill_Report(){
        
        
      
            Map reportparams = new HashMap();
            List<Object> list = new ArrayList<Object>();
             
            DataSourceProvider data1 = new DataSourceProvider(list);
           
             reportparams.put("BILLNAME","GR Bill Proforma" );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             //Added By Ganesh
             reportparams.put("GstNumber",jTextField1.getText());
             //Ended By Ganesh
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             reportparams.put("MEM_NAME", MemberName);
             reportparams.put("SKEY", mno.getText());
            // reportparams.put("MAIN_TEXT", Message);
             reportparams.put("TABLE", room_type_label.getText());
             reportparams.put("BOOKING_SEQ_NO" , bkng_seq_no_label.getText());
             reportparams.put("PARENTID", "N/A");
             
             reportparams.put("MEMBER", mem_name_label.getText());
             reportparams.put("RoomNo", roomNo_Label1.getText());
             reportparams.put("FDATE", date1.getText());
             reportparams.put("TDATE",date2.getText());
             reportparams.put("DAYS", days.getText());
            
             reportparams.put("RATE",  rate.getText());
             reportparams.put("RTAX1", Tax_rate1_label.getText());
             reportparams.put("RTAX2", Tax_rate2_label.getText());
             reportparams.put("RTAX3", Tax_rate3_label.getText());
             
             
             reportparams.put("AMT1", charges.getText());
             reportparams.put("TAX1", tax1_amt_label.getText());
             reportparams.put("TAX2", tax2_amt_label.getText());
             reportparams.put("TAX3", tax3_amt_label.getText());
             
             reportparams.put("N_TAX1", tax1_lebel.getText() + "  "+ basic_tax_label1.getText());
             reportparams.put("N_TAX2", tax2_Label.getText() + "  "+ basic_tax_label2.getText());
             reportparams.put("N_TAX3", tax3_Label.getText() + "  "+ basic_tax_label3.getText());
           
             reportparams.put("DISCAMT", Discount_Amt_label.getText());
             reportparams.put("DISCAMT_TOT", Discount_Amt_label_Total.getText());
             
             reportparams.put("GTOTAL", tot.getText());
             reportparams.put("WAITER", m_App.getAppUserView().getUser().getName());
            // String id=null;
           
             
           JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/GuestRm_checknew.jrxml", reportparams, false, data1, true, null); 

    }
    //added by pratima
    public void setAdjustedValue(String Booking_seq_no){
    int row = jTable1.getSelectedRow();
               GuestRoom_CheckIn_Info showdata1 = GR_CheckIn_Detail.getGuestRmList().get(row);
             if(  showdata1.getBILLED()==1){
      try{
     Object[] objPaid= (Object[])new StaticSentence(m_App.getSession(), "select paid from guestroom_bill where  id=(select billno from  guestroom_checkin where id=?)", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.INT })).find(new Object[]{CheckIn_ID});
      int paid=Integer.parseInt((objPaid[0]).toString());
      if(paid==1){
      Adjust_billedAmt_check.setSelected(true);
      }
      }catch(BasicException e){e.printStackTrace();
      }
    }
    }
    
   public void setCheckoutStrict(){
     
                   try{
          Object[] objStrict = (Object[])new StaticSentence(m_App.getSession(),"SELECT VALUE FROM GENERALTABLE Where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find("Guest Room Checkout Strict");
        if(objStrict!=null){
          strict= Integer.parseInt(objStrict[0].toString());
          System.out.println("strict "+strict);
      
        } }
        catch(BasicException ex){
            
        }
   } 
    //ended by pratima
    
}
