
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.beans.JFlowPanel;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;
 import java.text.*;

public class BookGuestRoom extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private ComboBoxValModel RoomTypeListModel ;
    private ComboBoxValModel RoomTypeListModel_avail;
     private ComboBoxValModel RoomNameListModel ;
   
    
    private List<Object> roomType_list ;
    private List<Object> roomName_list ;
    private GuestRoomTableModel RoomTableModel;
    private BookGuestRoomTableModel BookGuestRoom;
    private BookGuestRoomTableModel BookGuestRoom_Temp;
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private DataLogicFacilities dmang;
    private BookedRoomStatusTableModel Booked_room_status; 
    private BookedRoomStatusTableModel Rejected_Room_Status;
    private BookedRoomStatusTableModel Payment_Status;
    private Billpage bill;
    private CustomerInfoExt customer;
    private DataLogicSales dlSales = null;
    private CheckInTableModel CheckInTable_Model;
    private List<BookGuestRoomTableModel.GuestRoomTableInfo> Room_Details;
    private List<BookGuestRoomTableModel.GuestRoomTableInfo> Room_Details_temp;
    private TicketParser m_TTP;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private Double Total_Charge = 0.00;
    private Double Total_Charge_mem = 0.00;
    private Double Total_Charge_Guest = 0.00;
    //added by pratima
    private  BillTableModel BillModel=new BillTableModel(m_App);
     private double Final_Amount;
     private double Old_Final_Amount;
     private int Old_NO_OF_DAYS;
     private double Old_ADVANCE_RECV;
     private double New_ADVANCE_RECV;
     private String Old_Id;
     private BookedRoomStatusTableModel.Room_StatusInfo showdata;
     private int extendDateFlag=0;
     Date Old_Check_out_DATE = new Date();
    
     //ended by pratima
    public BookGuestRoom() {
        initComponents();
        main_panel.setVisible(true);
        tariff_details.setVisible(false);
        image_panel.setVisible(false);
        mem_radio.setSelected(true);
        submit_panel.setVisible(false);
        jInternalFrame1.setVisible(false);
        availibility_panel.setVisible(false);
        view_details.setEnabled(false);
        cancel_request.setEnabled(false);
        Check_out_label.setVisible(false);
        check_in_label.setVisible(false);
        message.setVisible(false);
        request_panel.setVisible(false);
        cancel_btn2.setVisible(false);
        make_pay_btn.setEnabled(false);
        
        
         
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        view_details = new javax.swing.JButton();
        cancel_request = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        make_pay_btn = new javax.swing.JButton();
        pending_label = new javax.swing.JLabel();
        ChangeBkngDate_Button = new javax.swing.JButton();
        ExtChkoutDt_Button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roomTyppe_combo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        Tot_room_avail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Booked_no_of_rooms_text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        Check_IN = new javax.swing.JTextField();
        mem_radio = new javax.swing.JRadioButton();
        n_mem_radio = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        bookedNo_of_days = new javax.swing.JTextField();
        A_date = new javax.swing.JTextField();
        room_id_label = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        Check_OUT = new javax.swing.JTextField();
        check_in_label = new javax.swing.JLabel();
        Check_out_label = new javax.swing.JLabel();
        max_Days_label = new javax.swing.JLabel();
        payment_date_label = new javax.swing.JLabel();
        role_label = new javax.swing.JLabel();
        tax1_Id_label = new javax.swing.JLabel();
        tax2_Id_label = new javax.swing.JLabel();
        tax3_Id_label = new javax.swing.JLabel();
        image_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        image_label1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        image_label3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        image_label2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        submit_panel = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        mName = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        memberNo = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        NName = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        contactNo = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Adress = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        roomtype = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        capacity = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        tariff = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        bookingDate = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        tax_1 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        tax_2 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        tax_3 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        booked_no_of_days = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        no_of_rooms_bk = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        cancel_note = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        tax1_rate = new javax.swing.JLabel();
        tax2_rate = new javax.swing.JLabel();
        tax3_rate = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        total_charges_label = new javax.swing.JLabel();
        basic_tax_label1 = new javax.swing.JLabel();
        basic_tax_label2 = new javax.swing.JLabel();
        basicCharge_multiply_label = new javax.swing.JLabel();
        basic_tax_label3 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        message1 = new javax.swing.JLabel();
        cancel_btn2 = new javax.swing.JButton();
        mem_label = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        non_mem_detail_table = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        non_member_name = new javax.swing.JTextField();
        non_M_ContactNo = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        non_M_Address = new javax.swing.JTextArea();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        memno_text = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        mname_text = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        member_label = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        cardno_text = new javax.swing.JPasswordField();
        jTextField3 = new javax.swing.JTextField();
        availibility_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        roomTyppe_combo1 = new javax.swing.JComboBox();
        jButton17 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jButton18 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        room_booking_no = new javax.swing.JTextField();
        temp_T = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        request_panel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
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
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        BookingDateChange_Panel = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        chengedCheckin_Text = new javax.swing.JTextField();
        ChangedCheckout_Text = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        roomtype_label = new javax.swing.JLabel();
        noofrooms_label = new javax.swing.JLabel();
        noofdays_label = new javax.swing.JLabel();
        ChangedCheckIn_Button = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        Cancel3_button = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        tariff_details = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        luxuryTax = new javax.swing.JTextField();
        tax2 = new javax.swing.JTextField();
        tax3 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tariff1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        tax_basic_label3 = new javax.swing.JLabel();
        tax_basic_label4 = new javax.swing.JLabel();
        tax1_rate_label = new javax.swing.JTextField();
        tax2_rate_label = new javax.swing.JTextField();
        tax3_rate_label = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        total_label = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        N_luxuryTax = new javax.swing.JTextField();
        N_tax2 = new javax.swing.JTextField();
        N_tax3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tariff2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        tax_basic_label1 = new javax.swing.JLabel();
        tax_basic_label2 = new javax.swing.JLabel();
        tax1_rate_label1 = new javax.swing.JTextField();
        tax2_rate_label1 = new javax.swing.JTextField();
        tax3_rate_label1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        total_label1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        roomtype_label1 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        noofrooms_label1 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        ChangedCheckout1_Text = new javax.swing.JTextField();
        chengedCheckin1_Text = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        ext_bookedNo_of_days = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Room Type", "No of Rooms Booked", "Booked By", "Booking Date", "Booking Status", "Booked No of Days", "Request Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        view_details.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        view_details.setForeground(new java.awt.Color(0, 0, 255));
        view_details.setText("View Details");
        view_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_detailsActionPerformed(evt);
            }
        });

        cancel_request.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancel_request.setForeground(new java.awt.Color(255, 0, 51));
        cancel_request.setText("Cancel Request");
        cancel_request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_requestActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(153, 0, 153));
        jButton19.setText("Check Availibility ");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 0, 51));
        jButton23.setText("View Cancelled Requests");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        make_pay_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        make_pay_btn.setForeground(new java.awt.Color(51, 51, 255));
        make_pay_btn.setText("Make Payment");
        make_pay_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                make_pay_btnActionPerformed(evt);
            }
        });

        pending_label.setText("(* Pending Request )");

        ChangeBkngDate_Button.setText("Change Booking Date");
        ChangeBkngDate_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeBkngDate_ButtonActionPerformed(evt);
            }
        });

        ExtChkoutDt_Button.setText("Extend Check Out Date");
        ExtChkoutDt_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtChkoutDt_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pending_label)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(view_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(120, 120, 120)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ExtChkoutDt_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(ChangeBkngDate_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancel_request, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(make_pay_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pending_label)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view_details, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel_request, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(make_pay_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeBkngDate_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(ExtChkoutDt_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        view_details.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/viewmag.png"))); // NOI18N
        cancel_request.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/request.png"))); // NOI18N
        make_pay_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash.png"))); // NOI18N
        pending_label.setForeground(Color.RED);

        jTabbedPane1.addTab("Room Booking Details", jPanel2);

        jLabel1.setText("Room Type");

        roomTyppe_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        roomTyppe_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                roomTyppe_comboItemStateChanged(evt);
            }
        });

        jLabel3.setText("No. Of Rooms Available");

        jLabel4.setText("Book  No. of Rooms ");

        Booked_no_of_rooms_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Booked_no_of_rooms_textKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 0, 0));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Block Room");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Images");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("View Tariff & Tax details");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Enter Booking Details");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Check Availibility");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("Check IN");
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        mem_radio.setText("Member");
        mem_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mem_radioItemStateChanged(evt);
            }
        });

        n_mem_radio.setText("Non Member ");
        n_mem_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                n_mem_radioItemStateChanged(evt);
            }
        });

        jLabel9.setText("Book for No. of Days ");

        bookedNo_of_days.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bookedNo_of_daysKeyReleased(evt);
            }
        });

        room_id_label.setText("ID");

        jButton15.setText("Check OUT");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        check_in_label.setText("Check_IN");

        Check_out_label.setText("Check_out");

        max_Days_label.setText("Max_days");

        payment_date_label.setText("jLabel10");

        role_label.setText("Role");

        tax1_Id_label.setText("tax1Id");

        tax2_Id_label.setText("tax2Id");

        tax3_Id_label.setText("tax3Id");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(max_Days_label)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(room_id_label)
                                .addGap(11, 11, 11)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 487, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(mem_radio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(n_mem_radio))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(61, 61, 61))))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Tot_room_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookedNo_of_days, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(Booked_no_of_rooms_text, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(roomTyppe_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(role_label, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Check_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Check_IN, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check_in_label)
                                    .addComponent(Check_out_label))
                                .addGap(52, 52, 52)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payment_date_label)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tax1_Id_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax2_Id_label))
                            .addComponent(tax3_Id_label))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roomTyppe_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Check_IN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(check_in_label))
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Booked_no_of_rooms_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Tot_room_avail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(bookedNo_of_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Check_out_label)
                            .addComponent(Check_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15))
                        .addGap(1, 1, 1)
                        .addComponent(role_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(A_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mem_radio)
                            .addComponent(n_mem_radio))))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(room_id_label)))
                .addGap(18, 18, 18)
                .addComponent(max_Days_label)
                .addGap(18, 18, 18)
                .addComponent(payment_date_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tax1_Id_label)
                    .addComponent(tax2_Id_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tax3_Id_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        Tot_room_avail.setEditable(false);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/view2.png"))); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        Check_IN.setEditable(false);
        bookedNo_of_days.setEditable(false);
        A_date.setVisible(false);
        room_id_label.setVisible(false);
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        Check_OUT.setEditable(false);
        check_in_label.setForeground(Color.RED);
        Check_out_label.setForeground(Color.RED);
        max_Days_label.setVisible(false);
        payment_date_label.setVisible(false);
        role_label.setVisible(false);
        tax1_Id_label.setVisible(false);
        tax2_Id_label.setVisible(false);
        tax3_Id_label.setVisible(false);

        jTabbedPane1.addTab("Book Room", jPanel3);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(358, Short.MAX_VALUE))
        );

        image_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Room Images");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Image 1");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Image 2");

        image_label1.setMaximumSize(new java.awt.Dimension(370, 195));
        image_label1.setMinimumSize(new java.awt.Dimension(370, 195));
        image_label1.setPreferredSize(new java.awt.Dimension(370, 195));
        jScrollPane2.setViewportView(image_label1);
        jScrollPane2.setSize(370,195);
        image_label1.setSize(370, 195);

        image_label3.setMaximumSize(new java.awt.Dimension(370, 195));
        image_label3.setMinimumSize(new java.awt.Dimension(370, 195));
        image_label3.setPreferredSize(new java.awt.Dimension(370, 195));
        jScrollPane3.setViewportView(image_label3);
        jScrollPane3.setSize(370,195);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Image 3");

        image_label2.setMaximumSize(new java.awt.Dimension(370, 195));
        image_label2.setMinimumSize(new java.awt.Dimension(370, 195));
        image_label2.setPreferredSize(new java.awt.Dimension(370, 195));
        jScrollPane4.setViewportView(image_label2);
        jScrollPane4.setSize(370,195);
        image_label2.setSize(370, 195);

        jButton10.setText("Cancel");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout image_panelLayout = new javax.swing.GroupLayout(image_panel);
        image_panel.setLayout(image_panelLayout);
        image_panelLayout.setHorizontalGroup(
            image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_panelLayout.createSequentialGroup()
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, image_panelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, image_panelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(153, 153, 153)
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(jLabel11)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        image_panelLayout.setVerticalGroup(
            image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, image_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, image_panelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(249, 249, 249))
                        .addGroup(image_panelLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(61, 61, 61)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, image_panelLayout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );

        jScrollPane2.setSize(370,222);
        jScrollPane3.setSize(370,222);
        jScrollPane4.setSize(370,222);
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 51, 51));
        jLabel59.setText("Booking Detail");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Member Name  : ");

        mName.setText("N/A");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Member No. :");

        memberNo.setText("N/A");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Address : ");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("Guest's Name : ");

        NName.setText("N/A");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Contact No. : ");

        contactNo.setText("N/A");

        Adress.setColumns(20);
        Adress.setRows(5);
        jScrollPane9.setViewportView(Adress);
        Adress.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mName))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberNo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NName))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGap(18, 18, 18)
                        .addComponent(contactNo))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(200, 200, 200))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(mName)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NName))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(memberNo)
                    .addComponent(jLabel63)
                    .addComponent(contactNo))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Room Type :");

        roomtype.setText("N/A");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Max. Capacity : ");

        capacity.setText("N/A");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Basic Tariff : ");

        tariff.setText("N/A");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Booking Date : ");

        bookingDate.setText("N/A");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setText("Taxes : ");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("(1)");

        tax_1.setText("N/A");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setText("(2)");

        tax_2.setText("N/A");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("(3)");

        tax_3.setText("N/A");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText("Booked For No. of Days: ");

        booked_no_of_days.setText("N/A");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("No. Of Rooms Booked : ");

        no_of_rooms_bk.setText("N/A");

        cancel_note.setColumns(20);
        cancel_note.setRows(5);
        jScrollPane8.setViewportView(cancel_note);
        cancel_note.setEditable(false);
        cancel_note.setForeground(Color.RED);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Note :");

        tax1_rate.setText("tax1");

        tax2_rate.setText("tax2");

        tax3_rate.setText("tax3");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("Total : ");

        total_charges_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        total_charges_label.setText("total_charge");

        basic_tax_label1.setText("(B)");

        basic_tax_label2.setText("(B)");

        basicCharge_multiply_label.setText("X room X days");

        basic_tax_label3.setText("(B)");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tariff))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(total_charges_label))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(capacity))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roomtype))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel73)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax_3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(basic_tax_label2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tax3_rate))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel72)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax_2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(basic_tax_label1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tax2_rate))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel71)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tax_1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(basic_tax_label3)
                                        .addGap(73, 73, 73)
                                        .addComponent(tax1_rate)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(basicCharge_multiply_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75)
                            .addComponent(jLabel68))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bookingDate)
                            .addComponent(booked_no_of_days)
                            .addComponent(no_of_rooms_bk)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(62, 62, 62)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomtype)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capacity)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(booked_no_of_days))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tariff)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no_of_rooms_bk)
                    .addComponent(basicCharge_multiply_label))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tax_1)
                            .addComponent(tax1_rate)
                            .addComponent(jLabel22)
                            .addComponent(basic_tax_label3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tax_2)
                            .addComponent(tax2_rate)
                            .addComponent(basic_tax_label1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tax_3)
                            .addComponent(tax3_rate)
                            .addComponent(basic_tax_label2))
                        .addGap(18, 21, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(total_charges_label, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 51, 51));
        jLabel69.setText("Hall Details");

        message.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        message.setText("jLabel79");

        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });

        message1.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        message1.setText("jLabel79");

        cancel_btn2.setText("Cancel");
        cancel_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btn2ActionPerformed(evt);
            }
        });

        mem_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem_label.setText("( Booked For :- )");

        javax.swing.GroupLayout submit_panelLayout = new javax.swing.GroupLayout(submit_panel);
        submit_panel.setLayout(submit_panelLayout);
        submit_panelLayout.setHorizontalGroup(
            submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(submit_panelLayout.createSequentialGroup()
                .addGroup(submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(submit_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel69))
                    .addGroup(submit_panelLayout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mem_label)))
                .addContainerGap())
            .addGroup(submit_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(submit_panelLayout.createSequentialGroup()
                        .addComponent(message1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(205, 205, 205))
                    .addGroup(submit_panelLayout.createSequentialGroup()
                        .addGroup(submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submit_panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cancel_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(submit_panelLayout.createSequentialGroup()
                                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(submit_panelLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        submit_panelLayout.setVerticalGroup(
            submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(submit_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(mem_label))
                .addGroup(submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(submit_panelLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel69)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submit_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(message1)
                .addGap(12, 12, 12)
                .addGroup(submit_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_btn)
                    .addComponent(jButton6)
                    .addComponent(cancel_btn2))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jPanel6.setSize(690, 130);
        jPanel7.setSize(690, 220);
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        message.setForeground(Color.RED);
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        cancel_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        message1.setForeground(Color.RED);
        cancel_btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        mem_label.setForeground(Color.BLUE);

        jInternalFrame1.setVisible(true);

        jLabel53.setText("Guest Name:");

        jLabel54.setText("Contact No ");

        jLabel55.setText("Address");

        non_member_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                non_member_nameActionPerformed(evt);
            }
        });

        non_M_ContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                non_M_ContactNoKeyReleased(evt);
            }
        });

        non_M_Address.setColumns(20);
        non_M_Address.setRows(5);
        jScrollPane6.setViewportView(non_M_Address);

        jLabel56.setText("Referenced To :- ");

        javax.swing.GroupLayout non_mem_detail_tableLayout = new javax.swing.GroupLayout(non_mem_detail_table);
        non_mem_detail_table.setLayout(non_mem_detail_tableLayout);
        non_mem_detail_tableLayout.setHorizontalGroup(
            non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(non_mem_detail_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(non_mem_detail_tableLayout.createSequentialGroup()
                        .addGroup(non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addGap(36, 36, 36)
                        .addGroup(non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(non_M_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(non_member_name, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel56))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        non_mem_detail_tableLayout.setVerticalGroup(
            non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(non_mem_detail_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(non_member_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(non_M_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(non_mem_detail_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel56))
        );

        jLabel57.setText("Member No.");

        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        jLabel58.setText("Member Name");

        mname_text.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                mname_textComponentAdded(evt);
            }
        });

        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 255));
        jButton11.setText("Save");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 0, 0));
        jButton12.setText("Cancel");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        member_label.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        member_label.setForeground(new java.awt.Color(0, 0, 255));
        member_label.setText("Booking Details");

        jLabel25.setText("Card No: ");

        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        cardno_text.setText("jPasswordField1");
        cardno_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardno_textActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(64, 64, 64))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton16))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                        .addComponent(cardno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(non_mem_detail_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(member_label, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(member_label)
                .addGap(18, 18, 18)
                .addComponent(non_mem_detail_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(cardno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(392, Short.MAX_VALUE))
        );

        mname_text.setEditable(false);
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        member_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig32.png")));
        member_label.setForeground(Color.RED);
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png")));
        cardno_text.setVisible(true);
        cardno_text.setDisabledTextColor(Color.WHITE);
        jTextField3.setEditable(false);

        availibility_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Room Type");

        roomTyppe_combo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        roomTyppe_combo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                roomTyppe_combo1ItemStateChanged(evt);
            }
        });

        jButton17.setForeground(new java.awt.Color(102, 0, 102));
        jButton17.setText("Select Booking Date");
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(153, 0, 0));
        jButton18.setText("Block Room");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(204, 0, 51));
        jButton14.setText("Cancel");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel15.setText("Available");

        jLabel16.setText("Booked");

        jLabel17.setText("Blocked");

        jLabel18.setText("* Select Booking Date");

        jLabel19.setText("Book no. of Rooms");

        room_booking_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                room_booking_noKeyReleased(evt);
            }
        });

        jLabel20.setText("No. of Rooms Available");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 255));
        jLabel21.setText("Availibility Menu ");

        jLabel10.setText("* Enter Rooms");

        javax.swing.GroupLayout availibility_panelLayout = new javax.swing.GroupLayout(availibility_panel);
        availibility_panel.setLayout(availibility_panelLayout);
        availibility_panelLayout.setHorizontalGroup(
            availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addComponent(roomTyppe_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(availibility_panelLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)))
                            .addGroup(availibility_panelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel20)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(temp_T, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addComponent(room_booking_no))))
                        .addContainerGap(24, Short.MAX_VALUE))))
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        availibility_panelLayout.setVerticalGroup(
            availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roomTyppe_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel18)
                        .addGap(29, 29, 29)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(temp_T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(room_booking_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(67, 67, 67)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(26, 26, 26)
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTextField1.setEditable(false);
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton20.setBackground(Color.GREEN);
        jButton21.setBackground(Color.RED);
        jButton22.setBackground(Color.ORANGE);
        jLabel18.setForeground(Color.RED);
        jLabel18.setVisible(false);
        temp_T.setEditable(false);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/location.png"))); // NOI18N
        jLabel10.setForeground(Color.RED);
        jLabel10.setVisible(false);

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
        jScrollPane7.setViewportView(jTable2);

        jButton24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton24.setForeground(new java.awt.Color(51, 0, 255));
        jButton24.setText("View Details");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton25.setForeground(new java.awt.Color(153, 0, 0));
        jButton25.setText("Go Back to Main Menu");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/view2.png"))); // NOI18N
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/gohome.png"))); // NOI18N

        jTabbedPane2.addTab("Cancelled requests", jPanel4);

        javax.swing.GroupLayout request_panelLayout = new javax.swing.GroupLayout(request_panel);
        request_panel.setLayout(request_panelLayout);
        request_panelLayout.setHorizontalGroup(
            request_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        request_panelLayout.setVerticalGroup(
            request_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(request_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BookingDateChange_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel39.setText("Check In Date : ");

        jLabel40.setText("Check Out Date : ");

        jLabel41.setText("Room Type : ");

        jLabel42.setText("No of rooms : ");

        jLabel43.setText("No of Days Booked : ");

        roomtype_label.setText("jLabel44");

        noofrooms_label.setText("jLabel45");

        noofdays_label.setText("jLabel46");

        ChangedCheckIn_Button.setText("Change");
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        ChangedCheckIn_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangedCheckIn_ButtonActionPerformed(evt);
            }
        });

        jButton27.setText("Check OUT");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton26.setText("Block Room ");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        Cancel3_button.setText("Cancel");
        Cancel3_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel3_buttonActionPerformed(evt);
            }
        });

        jLabel44.setText("Booked By : ");

        jLabel45.setText("jLabel45");

        javax.swing.GroupLayout BookingDateChange_PanelLayout = new javax.swing.GroupLayout(BookingDateChange_Panel);
        BookingDateChange_Panel.setLayout(BookingDateChange_PanelLayout);
        BookingDateChange_PanelLayout.setHorizontalGroup(
            BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookingDateChange_PanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(Cancel3_button, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
            .addGroup(BookingDateChange_PanelLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addGap(20, 20, 20)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookingDateChange_PanelLayout.createSequentialGroup()
                        .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chengedCheckin_Text, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(ChangedCheckout_Text))
                        .addGap(26, 26, 26)
                        .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChangedCheckIn_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(noofrooms_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addComponent(roomtype_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(noofdays_label, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        BookingDateChange_PanelLayout.setVerticalGroup(
            BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookingDateChange_PanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(roomtype_label))
                .addGap(18, 18, 18)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(16, 16, 16)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(noofrooms_label))
                .addGap(28, 28, 28)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(noofdays_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(chengedCheckin_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangedCheckIn_Button))
                .addGap(14, 14, 14)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(ChangedCheckout_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27))
                .addGap(18, 18, 18)
                .addGroup(BookingDateChange_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel3_button))
                .addContainerGap())
        );

        chengedCheckin_Text.setEditable(false);
        ChangedCheckout_Text.setEditable(false);
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton27.setVisible(false);

        tariff_details.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setText("Tariff Details ");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 0, 51));
        jLabel33.setText("Members");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Taxes");

        jLabel7.setText("Tax 1");

        jLabel26.setText("Tax 2");

        jLabel30.setText("Tax 3");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Basic Tariff");

        jLabel5.setText("Rs./Per Day");

        jLabel35.setText("(B)");

        tax_basic_label3.setText("(B)");

        tax_basic_label4.setText("(B)");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Total Amount :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel30)
                    .addComponent(jLabel7))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(tax3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_basic_label4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(tax2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tax_basic_label3))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35)))
                        .addContainerGap(347, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(127, 127, 127))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tax1_rate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tariff1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(tax2_rate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax3_rate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tariff1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(tax1_rate_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(tax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_basic_label3)
                    .addComponent(tax2_rate_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tax_basic_label4)
                        .addComponent(tax3_rate_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(total_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jLabel28.setForeground(Color.blue);
        luxuryTax.setEditable(false);
        tax2.setEditable(false);
        tax3.setEditable(false);
        jLabel23.setForeground(Color.blue);
        tariff1.setEditable(false);
        tariff1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax1_rate_label.setEditable(false);
        tax1_rate_label.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax2_rate_label.setEditable(false);
        tax2_rate_label.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax3_rate_label.setEditable(false);
        tax3_rate_label.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jLabel23.setForeground(Color.blue);
        total_label.setEditable(false);
        total_label.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(204, 0, 51));
        jLabel32.setText("Non- Members");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Taxes");

        jLabel8.setText("Tax 1");

        jLabel27.setText("Tax 2");

        jLabel31.setText("Tax 3");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Basic Tariff");

        jLabel6.setText("Rs./Per Day");

        jLabel38.setText("(B)");

        tax_basic_label1.setText("(B)");

        tax_basic_label2.setText("(B)");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Total Amount :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel31))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(N_tax2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(N_tax3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29)
                                .addGap(101, 101, 101))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(N_luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(tax_basic_label1)
                            .addComponent(tax_basic_label2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(124, 124, 124))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel37)
                        .addGap(37, 37, 37)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax1_rate_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tariff2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(tax2_rate_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax3_rate_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tariff2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(N_luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tax1_rate_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(N_tax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_basic_label1)
                    .addComponent(tax2_rate_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(N_tax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tax_basic_label2)
                    .addComponent(tax3_rate_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(total_label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jLabel29.setForeground(Color.blue);
        N_luxuryTax.setEditable(false);
        N_tax2.setEditable(false);
        N_tax3.setEditable(false);
        jLabel24.setForeground(Color.BLUE);
        tariff2.setEditable(false);
        tariff2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax1_rate_label1.setEditable(false);
        tax1_rate_label1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax2_rate_label1.setEditable(false);
        tax2_rate_label1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax3_rate_label1.setEditable(false);
        tax3_rate_label1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jLabel24.setForeground(Color.BLUE);
        total_label1.setEditable(false);
        total_label1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton8.setText("Close");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tariff_detailsLayout = new javax.swing.GroupLayout(tariff_details);
        tariff_details.setLayout(tariff_detailsLayout);
        tariff_detailsLayout.setHorizontalGroup(
            tariff_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_detailsLayout.createSequentialGroup()
                .addGroup(tariff_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tariff_detailsLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(tariff_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tariff_detailsLayout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tariff_detailsLayout.setVerticalGroup(
            tariff_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/exit.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel46.setText("Room Type:");

        jLabel47.setText("Booked By:");

        jLabel48.setText("Number of days wants to extend:");

        jLabel49.setText("Number of rooms:");

        roomtype_label1.setText("jLabel50");

        jLabel51.setText("jLabel51");

        noofrooms_label1.setText("jLabel52");

        jLabel76.setText("Check-In Date:");

        jLabel77.setText("Extended Check-Out Date:");

        jButton28.setText("Block Room");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText("Change");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        ext_bookedNo_of_days.setText("jLabel50");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roomtype_label1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel51))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(noofrooms_label1)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ChangedCheckout1_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton29)
                                .addGap(385, 385, 385))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chengedCheckin1_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ext_bookedNo_of_days, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(roomtype_label1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel51))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(noofrooms_label1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(ext_bookedNo_of_days))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(chengedCheckin1_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(ChangedCheckout1_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29))
                .addGap(18, 18, 18)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tariff_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(request_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(availibility_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(BookingDateChange_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tariff_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submit_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(availibility_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(request_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookingDateChange_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );

        jInternalFrame1.setSize(640, 530);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if(roomTyppe_combo.getSelectedIndex()!=-1){
        main_panel.setVisible(false);
        tariff_details.setVisible(true);
       }
       else{
            JOptionPane.showMessageDialog(this, " Select Room Type First ", " room Type", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        main_panel.setVisible(true);
        image_panel.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(roomTyppe_combo.getSelectedIndex()!=-1){
        image_panel.setVisible(true);
        main_panel.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(this, " Select Room Type First ", " room Type", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void non_member_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_non_member_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_non_member_nameActionPerformed

    private void non_M_ContactNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_non_M_ContactNoKeyReleased
        char c = evt.getKeyChar();

        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {
            if(!Character.isDigit(c))
            {
                JOptionPane.showMessageDialog(non_M_ContactNo, "Please enter only numbers..");

                non_M_ContactNo.setText(null);
            }
        }
    }//GEN-LAST:event_non_M_ContactNoKeyReleased

    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            try {
                Object[] obj = dmang.getMamberbySkey(memno_text.getText().toUpperCase());

                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                    memno_text.setText(null);
                } else {
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno_text.getText().toUpperCase());
                    customerInfo.setMobile(String.valueOf(obj[3]));
                    customerInfo.setAccno(obj[4].toString());
                    System.out.println(customerInfo.getAccno());
                    mname_text.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mname_text.setText(null);
            customerInfo = null;

        }
       
    }//GEN-LAST:event_memno_textKeyPressed

    private void mname_textComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_mname_textComponentAdded
        
    }//GEN-LAST:event_mname_textComponentAdded

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
          JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname_text.setText(customerInfo.toString());
                memno_text.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
     
    }//GEN-LAST:event_jButton16ActionPerformed

    private void roomTyppe_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomTyppe_comboItemStateChanged
       if(roomTyppe_combo.getSelectedIndex()!=-1){
           
           Booked_no_of_rooms_text.setText(null);
           bookedNo_of_days.setText(null);
           Check_IN.setText(null);
           Check_OUT.setText(null);
           
           
           
           
           String roomType = roomTyppe_combo.getSelectedItem().toString();
           
           try{
               BookGuestRoom = BookGuestRoomTableModel.loadInstanceGuestInfo(m_App, roomType);
              
           } 
           catch (BasicException ex) {
            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
            Room_Details =  (List<BookGuestRoomTableModel.GuestRoomTableInfo>) BookGuestRoom.getGuestRoomPath();
              
           BookGuestRoomTableModel.GuestRoomTableInfo editData = (BookGuestRoomTableModel.GuestRoomTableInfo) ((BookGuestRoomTableModel.GuestRoomTableInfo)Room_Details.get(0));
           
           Tot_room_avail.setText(""+editData.getRooms_available());
           
           tariff1.setText(decimalFormat.format(editData.getMem_tariff()));
           tariff2.setText(decimalFormat.format(editData.getNon_mem_tariff()));
           
           luxuryTax.setText(editData.getLuxuryTax());
           tax2.setText(editData.getTax_2());
           tax3.setText(editData.getTax_3());
           
           tax1_Id_label.setText(editData.getTax1_ID());
           tax2_Id_label.setText(editData.getTax2_ID());
           tax3_Id_label.setText(editData.getTax3_ID());
           
           BASIC1 = editData.getBasic1();
           BASIC2 = editData.getBasic2();
           
           Total_Charge_mem=0.00;
           Total_Charge_Guest=0.00;
           
           Double Memtariff = editData.getMem_tariff();
           Double Non_memTariff = editData.getNon_mem_tariff();
           
           if(editData.getTax1_Rate()!=null ){
               tax1_rate.setText(""+editData.getTax1_Rate());
               
               Double Tax1_rate = (Memtariff * editData.getTax1_Rate());
               tax1_rate_label.setText(decimalFormat.format(Tax1_rate));
               Total_Charge_mem = Memtariff + Tax1_rate; 
               
               Double Tax1_rate1 = (Non_memTariff * editData.getTax1_Rate());
               tax1_rate_label1.setText(decimalFormat.format(Tax1_rate1));
               Total_Charge_Guest = Non_memTariff + Tax1_rate1; 
               
            }
            else{
                tax1_rate.setText("");
                tax1_rate_label.setText("");
                tax1_rate_label1.setText("");
                
            }
            
            
            if(editData.getTax2_Rate()!=null ){
                 tax2_rate.setText(""+editData.getTax2_Rate());
                 if(editData.getBasic1()==1){
                     Double Tax2_rate = (Memtariff * editData.getTax2_Rate());
                     tax2_rate_label.setText(decimalFormat.format(Tax2_rate));
                     Total_Charge_mem = Total_Charge_mem  + Tax2_rate; 
                     tax_basic_label3.setText("(B)");
                     tax_basic_label1.setText("(B)");
                     
                     Double Tax2_rate1 = (Non_memTariff * editData.getTax2_Rate());
                     tax2_rate_label1.setText(decimalFormat.format(Tax2_rate1));
                     Total_Charge_Guest = Total_Charge_Guest  + Tax2_rate1; 
                     
                     
                     
                 }
                 else{
                      Double Tax2_rate = (Total_Charge_mem * editData.getTax2_Rate());
                     tax2_rate_label.setText(decimalFormat.format(Tax2_rate));
                     Total_Charge_mem = Total_Charge_mem  + Tax2_rate; 
                     tax_basic_label3.setText("(C)");
                     tax_basic_label1.setText("(C)");
                     
                     Double Tax2_rate1 = (Total_Charge_Guest * editData.getTax2_Rate());
                     tax2_rate_label1.setText(decimalFormat.format(Tax2_rate1));
                     Total_Charge_Guest = Total_Charge_Guest  + Tax2_rate1; 
                     
                     
                 }
                 
            }
            else{
                 tax2_rate.setText("");
                 tax_basic_label3.setText("");
                 tax_basic_label1.setText("");
                 tax2_rate_label.setText("");
                 tax2_rate_label1.setText("");
            }
            
            
            if(editData.getTax3_Rate()!=null){
                 tax3_rate.setText(""+editData.getTax3_Rate());
                  if(editData.getBasic2()==1){
                     Double Tax3_rate = (Memtariff * editData.getTax3_Rate());
                     tax3_rate_label.setText(decimalFormat.format(Tax3_rate));
                     Total_Charge_mem = Total_Charge_mem  + Tax3_rate; 
                     tax_basic_label4.setText("(B)");
                     tax_basic_label2.setText("(B)");
                     
                     
                     Double Tax3_rate1 = (Non_memTariff * editData.getTax3_Rate());
                     tax3_rate_label1.setText(decimalFormat.format(Tax3_rate1));
                     Total_Charge_Guest = Total_Charge_Guest  + Tax3_rate1; 
                     
                     
                 }
                 else{
                      Double Tax3_rate = (Total_Charge_mem * editData.getTax3_Rate());
                     tax3_rate_label.setText(decimalFormat.format(Tax3_rate));
                     Total_Charge_mem = Total_Charge_mem  + Tax3_rate; 
                     tax_basic_label4.setText("(C)");
                     tax_basic_label2.setText("(C)");
                     
                     Double Tax3_rate1 = (Total_Charge_Guest * editData.getTax3_Rate());
                     tax3_rate_label1.setText(decimalFormat.format(Tax3_rate1));
                     Total_Charge_Guest = Total_Charge_Guest  + Tax3_rate1; 
                     
                     
                 }
                 
            }
            else{
                tax3_rate.setText("");
                 tax_basic_label3.setText("");
                 tax3_rate_label.setText("");
                 tax_basic_label2.setText("");
                  tax_basic_label4.setText("");
                  tax3_rate_label1.setText("");
            }
            
            
            total_label1.setText(decimalFormat.format(Total_Charge_Guest));
            total_label.setText(decimalFormat.format(Total_Charge_mem));
            
           
           MAX_CAPACITY = editData.getMAX_CAPACITY();
           capacity.setText(""+editData.getMAX_CAPACITY());
           room_id_label.setText(editData.getId());
           
           N_luxuryTax.setText(editData.getLuxuryTax());
           N_tax2.setText(editData.getTax_2());
           N_tax3.setText(editData.getTax_3());
           
           check_in_label.setText(Formats.TIME.formatValue(editData.getCHECK_IN_TIME()));
           Check_out_label.setText(Formats.TIME.formatValue(editData.getCHECK_OUT_TIME()));
           
           time_Hours = editData.getCHECK_IN_TIME().getHours();
           System.out.println("hours :"+ time_Hours);
          // check_in_label.setVisible(true);
          // Check_out_label.setVisible(true);
           
           
           
           max_Days_label.setText(""+editData.getMAX_DAYS());
           
           BASIC1 = editData.getBasic1();
           CASCADE1 = editData.getCascade1();
           BASIC2  = editData.getBasic2();
           CASCADE2 = editData.getCascade2();
           
           Payment_Days = editData.getPAYMENT_DAYS();
                  Date Currdate = new Date();
                  Calendar c = Calendar.getInstance();
                  c.setTimeInMillis(new Date().getTime());
                  c.add(Calendar.DATE, Payment_Days);
                  Last_payment_date = c.getTime();
                  String Final_date = Formats.DATE.formatValue(Last_payment_date);
                  payment_date_label.setText(Final_date);
           
           
           A_date.setText(""+editData.getadvance_booking_dura());
           if(editData.getIMAGE1()!=null){
               BufferedImage b1 = editData.getIMAGE1();
               Icon ic1 = new ImageIcon(b1);
               image_label1.setIcon(ic1);
           }
           else{
               image_label1.setText("Image Not Available..!");
           }
           
           if(editData.getIMAGE2()!=null){
               BufferedImage b2 = editData.getIMAGE2();
               Icon ic2 = new ImageIcon(b2);
               image_label2.setIcon(ic2);
           }
           else{
               image_label2.setText("Image Not Available..!");
           }
           
           if(editData.getIMAGE3()!=null){
               BufferedImage b3 = editData.getIMAGE3();
               Icon ic3 = new ImageIcon(b3);
               image_label3.setIcon(ic3);
           }
           else{
               image_label3.setText("Image Not Available..!");
           }
           
           
           
       }
    }//GEN-LAST:event_roomTyppe_comboItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       tariff_details.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      reset();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
            
            availibility_panel.setVisible(true);
            main_panel.setVisible(false);
            
            if(Check_IN.getText()!=null){
              jTextField1.setText(Check_IN.getText());
            }
          
          
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       if(roomTyppe_combo.getSelectedIndex()!=-1){
      
           jInternalFrame1.setVisible(true);
           jButton16.setVisible(true);
           main_panel.setVisible(false);
           
           cardno_text.requestFocus();
           
           
           
        }
        else{
            JOptionPane.showMessageDialog(this, " Select Room Type First ", " room Type", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      
       if(mem_radio.isSelected()){ 
        if(memno_text.getText()!=null && memno_text.getText().trim().length()>0){
           if(mname_text.getText()!=null && mname_text.getText().trim().length()>0){
            
               main_panel.setVisible(true);
               jInternalFrame1.setVisible(false);
               
           }
           else{
               JOptionPane.showMessageDialog(this, " Member Name  Should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
           }
       } 
       else{
           JOptionPane.showMessageDialog(this, " Member No. Should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
       }
       }
       
       
       
       
       
       else{
           
           if(non_member_name.getText()!=null && non_member_name.getText().trim().length()>0){ 
               if(non_M_ContactNo.getText()!=null && non_M_ContactNo.getText().trim().length()>0){
                   if(non_M_Address.getText()!=null && non_M_Address.getText().trim().length()>0){
                 if(memno_text.getText()!=null && memno_text.getText().trim().length()>0){
                    if(mname_text.getText()!=null && mname_text.getText().trim().length()>0){
                     
             
                        main_panel.setVisible(true);
                        jInternalFrame1.setVisible(false);

                        
                        
                      }
                        else{
                            JOptionPane.showMessageDialog(this, " Member Name  Should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
                                 }
                             } 
                     else{
                    JOptionPane.showMessageDialog(this, " Member No. Should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
                 }
               }
                else{
                    JOptionPane.showMessageDialog(this, " Non Member Address should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
                }
               }
               
            else{
                   JOptionPane.showMessageDialog(this, " Non Member contact number should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
               JOptionPane.showMessageDialog(this, " Non Member name  should not be empty..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
           }
           
           
           
       }
    }//GEN-LAST:event_jButton11ActionPerformed

    
    String ROOM_TYPE;
    String BOOKING_DATE;
    String MEMBER_NAME;
    String MEMBER_NO;
    String NON_MEM_NAME;
    String NON_MEM_CONTACT;
    String NON_MEM_ADDR;
    int MEMBER_FLAG = 0;
    int NON_MEM_FLAG = 0;
    int STATUS;
    int NO_OF_ROOMS_BOOKED;
    int NO_OF_DAYS;
    int BASIC1;
    int CASCADE1;
    int BASIC2;
    int CASCADE2;
    
    String LUXURYTAX;
    String TAX2;
    String TAX3;
    
    String TAX1_ID;
    String TAX2_ID;
    String TAX3_ID;
    
    
    Double CHARGES;
    int FLAG;
    int TOTAL_ROOMS_AVAIL;
    Date BOOKING_DATE_EX;
    
    String Room_TYPE_ID;
    String Member_ID;
    int MAX_CAPACITY;
    Date Last_payment_date = new Date();
    int Payment_flag;
    int Payment_Days;
   
    String Booking_Seq_No;
    int Rooms_booked;
    int no_of_days_bkd;
    Double Advance_Recv = 0.00;
    String BookingID ;
    String roomType_ID;
    String RoomType_N;
    String TRANSREF;
    String NARRATION;
   String BILLING_NAME;
   Date CHECKOUT_DATE_EX;//added by pratima
   long time_Hours;
   
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(roomTyppe_combo.getSelectedIndex()!=-1){
           if(Booked_no_of_rooms_text.getText()!=null && Booked_no_of_rooms_text.getText().trim().length()>0){ 
               if(bookedNo_of_days.getText()!=null && bookedNo_of_days.getText().trim().length()>0){
                 if(memno_text.getText()!=null && memno_text.getText().trim().length()>0){
                     if(Check_IN.getText()!=null && Check_IN.getText().trim().length()>0){   
                         if(Check_OUT.getText()!=null && Check_OUT.getText().trim().length()>0){
                         
                         
                       MEMBER_NAME =   mname_text.getText();
                       mName.setText(MEMBER_NAME);
                       
                       MEMBER_NO = memno_text.getText();
                       memberNo.setText(memno_text.getText());
                      
                       Member_ID = customerInfo.getId();
                       
                       ROOM_TYPE = roomTyppe_combo.getSelectedItem().toString();
                       roomtype.setText(roomTyppe_combo.getSelectedItem().toString());
                       
                       BOOKING_DATE = Check_IN.getText();
                       bookingDate.setText(Check_IN.getText());
                       
                       NO_OF_DAYS = Integer.parseInt(""+bookedNo_of_days.getText());
                       booked_no_of_days.setText(bookedNo_of_days.getText());
                       
                       LUXURYTAX = luxuryTax.getText();
                       tax_1.setText(luxuryTax.getText());
                       TAX1_ID = tax1_Id_label.getText();
                       
                       
                       Room_TYPE_ID = room_id_label.getText();
                             
                      
                               
                       TAX2 = tax2.getText();
                       tax_2.setText(tax2.getText());
                       TAX2_ID = tax2_Id_label.getText();
                       
                       
                       TAX3 = tax3.getText();
                       tax_3.setText(tax3.getText());
                       TAX3_ID = tax3_Id_label.getText();
                       
                       
                       NO_OF_ROOMS_BOOKED = Integer.parseInt(Booked_no_of_rooms_text.getText());
                       no_of_rooms_bk.setText(""+NO_OF_ROOMS_BOOKED);
                               
                       TOTAL_ROOMS_AVAIL = Integer.parseInt(Tot_room_avail.getText());
                        
                       
                       try {
                            Last_payment_date = (Date) Formats.DATE.parseValue(payment_date_label.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                       if(BASIC2 == 1){
                          
                       }
                       else{
                           
                       }
                       
                     
                        
                        Payment_flag = 0;
                       
                       if(mem_radio.isSelected()){
                           tariff.setText((tariff1.getText()));
                           CHARGES = Double.parseDouble(tariff1.getText());
                           MEMBER_FLAG = 1;
                           NON_MEM_FLAG = 0;
                           NON_MEM_NAME = "N/A";
                           NON_MEM_ADDR = "N/A";
                           NON_MEM_CONTACT = customerInfo.getMobile();
                           contactNo.setText(customerInfo.getMobile());
                           mem_label.setText("( Booked For :- Member )");
                           mem_label.setForeground(Color.BLUE);
              
                           
                       }
                       else{
                            NON_MEM_NAME = non_member_name.getText();
                            NName.setText(non_member_name.getText());
                            
                            NON_MEM_ADDR = non_M_Address.getText();
                            Adress.setText(non_M_Address.getText());
                            
                            NON_MEM_CONTACT = non_M_ContactNo.getText();
                            contactNo.setText(non_M_ContactNo.getText());
                            
                            tariff.setText((tariff2.getText()));
                            CHARGES = Double.parseDouble(tariff2.getText());
                            NON_MEM_FLAG = 1;
                            MEMBER_FLAG = 0;
                            mem_label.setText("( Booked For :- Non-Member )");
                            mem_label.setForeground(Color.BLUE);
              
                            
                            
                       }
                     
                       
                      
                        try {
                             BOOKING_DATE_EX  = (Date) Formats.TIMESTAMP.parseValue(BOOKING_DATE);
                         } catch (BasicException ex) {
                             Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                         }
                       
                        
                        
                        Calendar cal = Calendar.getInstance(); // creates calendar
                        cal.setTime(BOOKING_DATE_EX); // sets calendar time/date
                        cal.add(Calendar.HOUR_OF_DAY, (int)time_Hours); // adds one hour
                        cal.getTime();
                        
                        BOOKING_DATE_EX = cal.getTime();
                        System.out.println(BOOKING_DATE_EX);
                             
                             
                             
                        basicCharge_multiply_label.setText("X "+NO_OF_DAYS+" X "+NO_OF_ROOMS_BOOKED);
                         
                        Total_Charge = 0.00;
                        Double Basic_Charge = (CHARGES * NO_OF_DAYS *NO_OF_ROOMS_BOOKED ) ;
                        Total_Charge = Total_Charge + Basic_Charge;

                        if(tax1_rate.getText()!=null && tax1_rate.getText().trim().length()>0){
                            Double Tax1 = Double.parseDouble(tax1_rate.getText());
                            tax1_rate.setText(decimalFormat.format(Basic_Charge*Tax1));
                            Total_Charge = Total_Charge + (Basic_Charge*Tax1);

                        }
                        else{


                        }

                        if(tax2_rate.getText()!=null && tax2_rate.getText().trim().length()>0){
                           if(BASIC1==1){
                                Double Tax2 = Double.parseDouble(tax2_rate.getText());
                                tax2_rate.setText(decimalFormat.format(Basic_Charge*Tax2));
                                Total_Charge = Total_Charge + (Basic_Charge*Tax2);
                                basic_tax_label1.setText("(B)");
                           }
                           else{
                              Double Tax2 = Double.parseDouble(tax2_rate.getText());
                              tax2_rate.setText(decimalFormat.format(Total_Charge*Tax2));
                              Total_Charge = Total_Charge + (Total_Charge*Tax2);
                              basic_tax_label1.setText("(C)");
                           }
                        }
                        else{

                             basic_tax_label1.setText("");
                        }

                        
                        
                        if(tax3_rate.getText()!=null && tax3_rate.getText().trim().length()>0){
                            
                            if(BASIC2==1){
                                Double Tax3 = Double.parseDouble(tax3_rate.getText());
                                tax3_rate.setText(decimalFormat.format(Basic_Charge*Tax3));
                                Total_Charge = Total_Charge + (Basic_Charge*Tax3);
                                basic_tax_label2.setText("(B)");
                            }
                            else{
                                Double Tax3 = Double.parseDouble(tax3_rate.getText());
                                tax3_rate.setText(decimalFormat.format(Total_Charge*Tax3));
                                Total_Charge = Total_Charge + (Total_Charge*Tax3);
                                basic_tax_label2.setText("(C)");
                            }
                        }
                        else{
                                basic_tax_label2.setText("");  
                        }

                        total_charges_label.setText(decimalFormat.format(Total_Charge));
                        
                        
                        
                        
                       FLAG = Booked_room_status.getFlag(m_App);
                       STATUS = 3;
                       submit_panel.setVisible(true);
                       main_panel.setVisible(false);
                       message.setVisible(false);
                       message1.setVisible(false);
                       cancel_note.setText(null);
                     }
                         else{
                             JOptionPane.showMessageDialog(this, " Enter Check-OUT date..! ", " room Type", JOptionPane.ERROR_MESSAGE);
                         }
                     }
                     else{
                          JOptionPane.showMessageDialog(this, " Enter Check-IN date..! ", " room Type", JOptionPane.ERROR_MESSAGE);
                     }
                 }
                 else{
                     JOptionPane.showMessageDialog(this, " Member's details should not be empty ..! ", " room Type", JOptionPane.ERROR_MESSAGE);
                 }
               }
               else{
                   JOptionPane.showMessageDialog(this, " Enter No. of Days ..! ", " room Type", JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
               JOptionPane.showMessageDialog(this, " Enter No. of Rooms..! ", " room Type", JOptionPane.ERROR_MESSAGE);
           }
        }
        else{
            JOptionPane.showMessageDialog(this, " Select Room Type..!  ", " room Type", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int submit_hall = JOptionPane.showConfirmDialog(submit_panel, "Do You Want to block Room .. ?? " , "Blocking Confirmation" , JOptionPane.YES_NO_OPTION);
        if(submit_hall == JOptionPane.YES_OPTION){
            
           try {
                  Booking_Seq_No =  getNextGuestRoom_Sequence();
            } catch (BasicException ex) {
                  Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
             }    
            
           if(Booking_Seq_No!=null && Booking_Seq_No.trim().length()>0 && Booking_Seq_No!="") 
           {
            Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                                                              
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                            
                            
                       int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_booked_details (ID ,BOOKING_SEQ_NO , ROOM_TYPE , BOOKING_DATE , MEMBERNAME , MEMBER_FLAG ,NON_MEMBER_FLAG , STATUS , FLAG , ROOM_NOS , CRBY  , CRDATE ,  CRHOST , BOOKING_DATE_EX , BOOKING_DAYS , MEMBER_NO , NON_MEM_NAME , NON_MEM_CNTCT , NON_MEM_ADDR , CHARGES , LUXURYTAX , TAX2 , TAX3 , BASIC1 , CASCADE1 , MAX_CAPACITY , BASIC2 , CASCADE2 , LAST_PAYMENT_DATE , PAYMENT_FLAG , ROLE ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.INT ,Datas.INT ,Datas.INT  ,Datas.INT   , Datas.INT , Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP , Datas.INT , Datas.STRING , Datas.STRING ,  Datas.STRING , Datas.STRING  , Datas.DOUBLE , Datas.STRING ,Datas.STRING , Datas.STRING , Datas.INT , Datas.INT , Datas.INT , Datas.INT , Datas.INT ,Datas.TIMESTAMP , Datas.INT , Datas.STRING  })                         
                        ).exec(new Object[]{UUID.randomUUID().toString(),Booking_Seq_No , Room_TYPE_ID ,BOOKING_DATE ,Member_ID ,MEMBER_FLAG ,NON_MEM_FLAG , STATUS  , FLAG ,NO_OF_ROOMS_BOOKED ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , BOOKING_DATE_EX , NO_OF_DAYS , MEMBER_NO, NON_MEM_NAME , NON_MEM_CONTACT , NON_MEM_ADDR , CHARGES ,  TAX1_ID ,TAX2_ID , TAX3_ID , BASIC1 , CASCADE1 , MAX_CAPACITY , BASIC2 , CASCADE2 ,Last_payment_date , Payment_flag , m_App.getAppUserView().getUser().getRole()  });                                                                                                
                         
                         
                            load_availibility(BOOKING_DATE_EX , Room_TYPE_ID , NO_OF_ROOMS_BOOKED ,TOTAL_ROOMS_AVAIL , NO_OF_DAYS);
                            Update_GuestRoomSeq();
                       
                       
                       
                          return null;                                      
                            }                            
                        };                 
                          
                        try {                 
                            t.execute();          
                            
                            
                            
                            JOptionPane.showMessageDialog(this, "Room Blocked Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reset();
                            loaddata();
                            
                        }
                     catch (BasicException ex) {                    
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                            
                            }
                        
              }
           
             }
        else{
               submit_panel.setVisible(false);
               main_panel.setVisible(true);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void mem_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mem_radioItemStateChanged
        if(mem_radio.isSelected()){
            non_mem_detail_table.setVisible(false);
            member_label.setText("Member Details");
        }
    }//GEN-LAST:event_mem_radioItemStateChanged

    private void n_mem_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_n_mem_radioItemStateChanged
       if(n_mem_radio.isSelected()){
            non_mem_detail_table.setVisible(true);
            member_label.setText("Non member Details");
       }
    }//GEN-LAST:event_n_mem_radioItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
      jInternalFrame1.setVisible(false);
      main_panel.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void bookedNo_of_daysKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bookedNo_of_daysKeyReleased
         char c = evt.getKeyChar();
    
         Check_IN.setText(null);
         
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(bookedNo_of_days, "Please enter only numbers..");
    
            bookedNo_of_days.setText(null);
         
    }
    }
    }//GEN-LAST:event_bookedNo_of_daysKeyReleased

    private void Booked_no_of_rooms_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Booked_no_of_rooms_textKeyReleased
    char c = evt.getKeyChar();
    Check_IN.setText(null);
    bookedNo_of_days.setText(null);
    int total_rooms = Integer.parseInt(Tot_room_avail.getText());
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(Booked_no_of_rooms_text, "Please enter only numbers..");
    
            Booked_no_of_rooms_text.setText(null);
     
    }
    else
    {
        int x = Integer.parseInt(Booked_no_of_rooms_text.getText());
        if(x>total_rooms){
            JOptionPane.showMessageDialog(Booked_no_of_rooms_text, "Only "+total_rooms+" are Available for booking..!" );
            Booked_no_of_rooms_text.setText(null);
            
        }
    }
           
    }
    
    }//GEN-LAST:event_Booked_no_of_rooms_textKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       if(roomTyppe_combo.getSelectedIndex()!=-1){
           if(Booked_no_of_rooms_text.getText()!=null && Booked_no_of_rooms_text.getText().trim().length()>0){
              
                   
                   
                   int Advance_book_dura = 3;
                    if(A_date.getText()!=null){
                       Advance_book_dura = Integer.parseInt(A_date.getText());
                   }

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
                        date = (Date) Formats.DATE.parseValue(Check_IN.getText());
                    } catch (BasicException e) {
                        date = null;
                    }
                      try{
                    date = JCalendarDialog.showCalendar(this, date);
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
                                
                                
                                
                              //  for(int i=1;i<=Days ; i++){
                               //      Calendar c = Calendar.getInstance(); 
                               //      c.setTime(d);
                               //      c.add(Calendar.DATE, i-1);
                                //     d = c.getTime();
                                     
                                    // call for booked rooms 
                                 //   Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,room_type_ID , d);
                                   
                           Check_IN.setText(Formats.DATE.formatValue(date));
                           check_in_label.setVisible(true);
                           Check_OUT.setText(null);
                                    
                          //   }
                        }

                    }
          }
        catch(Exception e1){
              e1.printStackTrace();
          }
          
           }
           else{
               JOptionPane.showMessageDialog(this, " Select No. Of Rooms to be booked..!   ", " hall", JOptionPane.ERROR_MESSAGE);
           }
       }
     
     else{
         JOptionPane.showMessageDialog(this, " Select Room Type   ", " hall", JOptionPane.ERROR_MESSAGE);
         
     }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       if(roomTyppe_combo1.getSelectedIndex()!=-1){
        int Advance_book_dura = 3;
              String Room_type =  roomTyppe_combo1.getSelectedItem().toString();
              Advance_book_dura = Booked_room_status.getAdvance_bookingDate(m_App, Room_type);
                 

                  Date date=new Date();
                  Calendar call = Calendar.getInstance();
                  call.setTimeInMillis(new Date().getTime());
                  call.add(Calendar.MONTH, Advance_book_dura);
                  Date afterDate = call.getTime();
                  
                    Calendar c1 = Calendar.getInstance();
                    c1.setTimeInMillis(new Date().getTime());
                    c1.add(Calendar.DATE,-1);
                    Date Curr_date = c1.getTime();
                  
                    try {
                        date = (Date) Formats.DATE.parseValue(jTextField1.getText());
                    } catch (BasicException e) {
                        date = null;
                    }
                      try{
                    date = JCalendarDialog.showCalendar(this, date);
                    if (date != null) {
                        if(date.before(Curr_date))
                        {
                            JOptionPane.showMessageDialog(this, "Date Selected is not available... Please Select again..!!");

                        }
                        else if(date.after(afterDate))
                        {
                           JOptionPane.showMessageDialog(this, "Booking is available upto "+Advance_book_dura+" Monts only..!!! ");
                        }

                        else{
                                
                              jTextField1.setText(Formats.DATE.formatValue(date));
                              jLabel18.setVisible(false);
                              if(roomTyppe_combo1.getSelectedIndex()!=-1){
                                  String room_type = roomTyppe_combo1.getSelectedItem().toString();
                                  int total_rooms = Booked_room_status.getTotal_Rooms(m_App, room_type);
                                  showAvailibility(total_rooms);
                              }
                        }

                    }
          }
        catch(Exception e1){
              e1.printStackTrace();
          }
       }
       else{
           JOptionPane.showMessageDialog(this, " Select Room Type First..!!  ", " hall", JOptionPane.ERROR_MESSAGE);
       }
    
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
     
      if(roomTyppe_combo1.getSelectedIndex()!=-1){  
          if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0){
            if(room_booking_no.getText()!=null && room_booking_no.getText().trim().length()>0){
             
                
            String roomType = roomTyppe_combo1.getSelectedItem().toString();
            
            for(int i=0; i<roomTyppe_combo.getItemCount() ;i++){
                String X = roomTyppe_combo.getItemAt(i).toString();
                
                if(roomType.equals(X)){
                    roomTyppe_combo.setSelectedIndex(i);
                }
                
            }
             
            Booked_no_of_rooms_text.setText(room_booking_no.getText());
            Check_IN.setText(jTextField1.getText());
            jTabbedPane1.setSelectedIndex(1);
            availibility_panel.setVisible(false);
            main_panel.setVisible(true);
            check_in_label.setVisible(true);
            
            
            
          }
            else{
                 JOptionPane.showMessageDialog(this, " Enter no Of Rooms for Booking..!!  ", " room", JOptionPane.ERROR_MESSAGE);
                 jLabel10.setVisible(true);
            }   
           
          }
          else{
              JOptionPane.showMessageDialog(this, " Select Booking Date First..!!  ", " room", JOptionPane.ERROR_MESSAGE);
          }
      }
      else{
          JOptionPane.showMessageDialog(this, " Select Room Type First..!!  ", " room", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void roomTyppe_combo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_roomTyppe_combo1ItemStateChanged
        if(roomTyppe_combo1.getSelectedIndex()!=-1){ 
        
        String Room_Type = roomTyppe_combo1.getSelectedItem().toString();
        
         int total_Rooms = Booked_room_status.getTotal_Rooms(m_App, Room_Type);
         room_booking_no.setText(null);
            
        if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0){
             
            showAvailibility(total_Rooms);
              
        }
        else{
            jLabel18.setVisible(true);
            
        }
          }
          
    }//GEN-LAST:event_roomTyppe_combo1ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(jTable1.getSelectedColumn()!=-1){
          view_details.setEnabled(true);
          cancel_request.setEnabled(true);
          make_pay_btn.setEnabled(true);
       }
       else{
          view_details.setEnabled(false);
          cancel_request.setEnabled(false);
          make_pay_btn.setEnabled(false);
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void view_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_detailsActionPerformed
        if(jTable1.getSelectedRow()!=-1){
            if(jTable1.getSelectedRow()<Booked_room_status.getRoomSize()){
            int row = jTable1.getSelectedRow();
            BookedRoomStatusTableModel.Room_StatusInfo showdata = Booked_room_status.getGuestRoomList().get(row);
            
            cancel_note.setText(null);
            cancel_btn.setVisible(true);
            cancel_btn2.setVisible(false);
            mName.setText(showdata.getMemberName());
            memberNo.setText(showdata.getMem_No());
            NName.setText(showdata.getNON_MEM_NAME());
            contactNo.setText(showdata.getNON_MEM_CONTCT());
            if(showdata.getMem_flag()==1){
                 Adress.setText(showdata.getCustAddress());
            }
            else{
                 Adress.setText(showdata.getNON_MEM_ADDR());
            }
            
            
           
            
            capacity.setText(""+showdata.getMAX_CAPACITY());
            
            roomtype.setText(showdata.getROOM_TYPE());
            bookingDate.setText(showdata.getBOOKING_DATE());
            no_of_rooms_bk.setText(""+showdata.getNO_OF_ROOMS_BOOKED());
            tariff.setText(decimalFormat.format(showdata.getCHARGES()));
            tax_1.setText(showdata.getLUXURYTAX());
            tax_2.setText(showdata.getTAX2());
            tax_3.setText(showdata.getTAX3());
            message.setVisible(true);
            Double Basic_charge = (showdata.getCHARGES());
            
            
            Date payment_date = showdata.getLAST_PAYMENT_DATE();
            Date Currdate = new Date();
            int No_of_days_left = (int) (payment_date.getTime() - Currdate.getTime())/(1000 * 60 * 60 * 24);
        
            Total_Charge = 0.00;
            Basic_charge = (Basic_charge * showdata.getNO_OF_DAYS() * showdata.getNO_OF_ROOMS_BOOKED());
            basicCharge_multiply_label.setText("X "+showdata.getNO_OF_DAYS()+" X "+showdata.getNO_OF_ROOMS_BOOKED());
            
            Total_Charge = Total_Charge + Basic_charge;
             
            
             BASIC1 = showdata.getBASIC1();
             BASIC2 = showdata.getBASIC2();
               
               if(showdata.getTax1_Rate()!=null ) {
                 Double Tax1 = showdata.getTax1_Rate()*Basic_charge;
                 tax1_rate.setText(decimalFormat.format(showdata.getTax1_Rate()*Basic_charge));
                 Total_Charge = Total_Charge + Tax1;
               }
               else{
                   tax1_rate.setText("");
               }
              if(showdata.getTax2_Rate()!=null ) {
                  if(BASIC1==1){
                    Double Tax2 = showdata.getTax2_Rate()*Basic_charge;
                    tax2_rate.setText(decimalFormat.format(showdata.getTax2_Rate()*Basic_charge));
                    Total_Charge = Total_Charge + Tax2;
                    basic_tax_label1.setText("(B)");
                  }
                  else{
                    Double Tax2 = showdata.getTax2_Rate()*Total_Charge;
                    tax2_rate.setText(decimalFormat.format(showdata.getTax2_Rate()*Total_Charge));
                    Total_Charge = Total_Charge + Tax2;
                    basic_tax_label1.setText("(C)");
                  }
               }
              else{
                 tax2_rate.setText("");
                  basic_tax_label1.setText("");
              }
              
               if(showdata.getTax3_Rate()!=null ) {
                   
                   if(BASIC2==1){
                        Double Tax3 = showdata.getTax3_Rate()*Basic_charge;
                        tax3_rate.setText(decimalFormat.format(showdata.getTax3_Rate()*Basic_charge));
                        Total_Charge = Total_Charge + Tax3;
                        basic_tax_label2.setText("(B)");
                   }
                   else{
                        Double Tax3 = showdata.getTax3_Rate()*Total_Charge;
                        tax3_rate.setText(decimalFormat.format(showdata.getTax3_Rate()*Total_Charge));
                        Total_Charge = Total_Charge + Tax3;
                        basic_tax_label2.setText("(C)");  
                   }
                   
               }
               else{
                   tax3_rate.setText("");
                   basic_tax_label2.setText("");  
               }
               
               
               total_charges_label.setText(decimalFormat.format(Total_Charge));
            
            
            
            
            
            String Final_date = Formats.DATE.formatValue(payment_date);
            
            if(showdata.getFlag()==1){
                if(showdata.getPAYMENT_FLAG()==1 && showdata.getStatus()==2){
                    message.setText("Advance Payment Done..!");
                    message1.setVisible(false);
                    
                } 
                else{
                        message.setText("* Request Approved  , Please Collect Amount From Member..!");
                        if(No_of_days_left==1){
                              message1.setText("* Last Date For Payment : "+Final_date+ ".   Today Last Day..!! ");
                        }
                        else{
                            message1.setText("* Last Date For Payment : "+Final_date+ ".     Days Left "+(No_of_days_left+1)+".");
                        }
                        message1.setVisible(true);
                }
            }
            
            if(showdata.getFlag()==0){
                message.setText("* Request pending , Please wait for approval...!!");
                message.setVisible(true);
                message1.setVisible(false);
             }
            
            
         
            
            if(showdata.getFlag()==2){
                
                String id = showdata.getId();
                String Cancel_reason = Booked_room_status.getCancel_reason(m_App, id);
                cancel_note.setText(Cancel_reason);
                  
                message.setText("Request has not Approved ..! ");
                message.setForeground(Color.red);
            }
            
            booked_no_of_days.setText(""+showdata.getNO_OF_DAYS());
            
            if(showdata.getMem_flag()==1){
               mem_label.setText("( Booked For :- Member )");
              
            }
            else{
                mem_label.setText("( Booked For :- Non-Member )");
               
            }
            
            submit_panel.setVisible(true);
            main_panel.setVisible(false);
            jButton6.setVisible(false);
            }
        }
    }//GEN-LAST:event_view_detailsActionPerformed

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        main_panel.setVisible(true);
        submit_panel.setVisible(false);
        jButton6.setVisible(true);
        
        int x = roomTyppe_combo.getSelectedIndex();
        roomTyppe_combo.setSelectedIndex(-1);
        roomTyppe_combo.setSelectedIndex(x);
        
        
    }//GEN-LAST:event_cancel_btnActionPerformed

    int getBooked_rooms=0;  
    int no_of_rooms_booked_by_mem;
    Date Booked_date_EX_TEMP = new Date();
    Date Booked_date_EX = new Date();
    Double Refund_Amt = 0.00;
    Double Cancel_Charge = 0.00;  
    Double TotalAmt;
    String Guest_N;
    String book_date;
    String Rfd_Voucher_No;
    
    String Cancel_Accnt_ID;
    String UserAccnt;
    String Advance_Acct_ID;
    String TID;
    String CancelReason;
    Double Cancel_Chrg_Perc = 0.00;
    Double Fix_Charge = 0.00;
    
    
    
    private void cancel_requestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_requestActionPerformed
       if(jTable1.getSelectedRow()!=-1){
         int cnl_req = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Cancel Request .. ?? ","Booking Cancellation", JOptionPane.YES_NO_OPTION);
           if(cnl_req == JOptionPane.YES_OPTION){
               
            int row = jTable1.getSelectedRow();
            final BookedRoomStatusTableModel.Room_StatusInfo showdata = Booked_room_status.getGuestRoomList().get(row);
               
            String Role = showdata.getROLE();
            String CurrRole =  m_App.getAppUserView().getUser().getRole();
            
            if(Role.equals(CurrRole)){
              if(showdata.getPAYMENT_FLAG()!=1 && showdata.getStatus()!=2){
            
            
            Transaction t = new Transaction(m_App.getSession()) {
                
                
            String BookingID = showdata.getId();
            String roomType_ID  = showdata.getROOMTYPE_ID();
            String book_date = showdata.getBOOKING_DATE();
            Date Booked_date_EX = showdata.getBOOKED_DATE_EX();
            
            String MEMBER_ID = showdata.getMEMBER_ID();
            Double Cancel_Chrg_Perc = 0.00;
            Double Fix_Charge = 0.00;
            
            
            int no_of_rooms_booked_by_mem = showdata.getNO_OF_ROOMS_BOOKED();
            int no_of_days = showdata.getNO_OF_DAYS();
            
           
                @Override
                protected Object transact() throws BasicException {
                                    try { 
                                   int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET STATUS=1 , REQ_CAN_BY=? , REQ_CAN_DATE=? , REQ_CAN_HOST=?  WHERE ID = ?"
                                                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  })).exec
                                                                       (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() ,BookingID });




                                   for(int i=1;i<=no_of_days ; i++ ){

                                    Calendar c = Calendar.getInstance(); 
                                    c.setTime(Booked_date_EX);
                                    c.add(Calendar.DATE, i-1);
                                    c.set(Calendar.MILLISECOND, 0);
                                    c.set(Calendar.SECOND, 0);
                                    c.set(Calendar.MINUTE, 0);
                                    c.set(Calendar.HOUR, 0);
                                    Booked_date_EX_TEMP = c.getTime();   
                                    
                                   getBooked_rooms = Booked_room_status.getRoom_Booked(m_App ,roomType_ID , Booked_date_EX_TEMP);

                                   if(getBooked_rooms >= no_of_rooms_booked_by_mem){ 

                                       int update_room_availibility = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=?  WHERE BOOKED_DATES=? AND ROOM_TYPE=?"
                                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                       (new Object[]{ (getBooked_rooms - no_of_rooms_booked_by_mem ) , Booked_date_EX_TEMP , roomType_ID  });

                                        }
                                   }


                                   // COPY TO GUEST ROOM CANCELLED DETAIS TABLE
                                   int copy_hall_to_room_cancel_request  =  new PreparedSentence(m_App.getSession(), "INSERT INTO guestroom_cancelled_details SELECT * FROM guestroom_booked_details   WHERE ID = ? "
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                       (new Object[]{BookingID}); 


                                   //DELETE FROM GUEST ROOM BOOKED DETAILS 
                                   int delete_From_room_booked_details  =  new PreparedSentence(m_App.getSession(), "DELETE FROM  guestroom_booked_details  WHERE ID = ?  "
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                       (new Object[]{BookingID}); 

                                  


                               } catch (BasicException ex) {
                                    ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                   Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                               }
                    return null;
                }
            };
                try {
                    t.execute();
                    
                     loaddata();

                     JOptionPane.showMessageDialog(this, "Request Canceled..! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (BasicException ex) {
                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                    new MessageInf(ex).show(new JFrame());
                }
            
               
            }
              
            else if(showdata.getPAYMENT_FLAG()==1 ){ 
                  int cnl_req2 = JOptionPane.showConfirmDialog(jPanel2, "Advance Payment already done. Do you Want to Cancel Booking ??" , "Booking Cancellation" , JOptionPane.YES_NO_OPTION);
                    if(cnl_req2 == JOptionPane.YES_OPTION){  
                        
                        
                        BookingID = showdata.getId();
                        roomType_ID  = showdata.getROOMTYPE_ID();
                        RoomType_N = showdata.getROOM_TYPE();
                        no_of_days_bkd = showdata.getNO_OF_DAYS();
                        Rooms_booked = showdata.getNO_OF_ROOMS_BOOKED();
                        book_date = showdata.getBOOKING_DATE();
                        Booked_date_EX = showdata.getBOOKED_DATE_EX();
                        Date Curr_Date = new Date();
                        Member_ID = showdata.getMEMBER_ID();
                        
                        
                        int Final_days=0;
                        Object []Charges = null;
                       
                        MEMBER_NAME = showdata.getMemberName();
                        MEMBER_NO = showdata.getMem_No();
                        Guest_N = showdata.getNON_MEM_NAME();
                        Member_ID = showdata.getMEMBER_ID();
                        Booking_Seq_No = showdata.getBOOKING_SEQ_NO();
                        Cancel_Accnt_ID = CheckInTable_Model.getReve_Acct_Room(m_App, roomType_ID);
                        UserAccnt =  m_App.getAppUserView().getUser().getcashaccount();
                        Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Room(m_App, roomType_ID);
                        
                        List<Object> Days_list = new ArrayList<Object>();
                        
                        int Check_in_Status =  Booked_room_status.getRoomCheckInStatus(m_App, BookingID);
                        
                        if(Check_in_Status==0){
                        
                        Advance_Recv = Booked_room_status.getAdvance_Paid(m_App, BookingID);
                            
                          
                          try {
                               Booked_date_EX = (Date) Formats.TIMESTAMP.parseValue(book_date);
                             
                           } catch (BasicException ex) {
                               Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        
                           long  no_of_day = (long) ( Booked_date_EX.getTime()-Curr_Date.getTime())/(1000 * 60 * 60 * 24);    
                           Days_list = Booked_room_status.getRoomCancellationOffer(m_App, roomType_ID); 
                           
                          for(int i=0;i<Days_list.size();i++){
                                int Min_day = Integer.parseInt(Days_list.get(i).toString());
                                if(no_of_day<Min_day){
                                    Final_days = Min_day;
                                    break;
                                }
                           }
                      
                          Charges =  Payment_Status.getCan_ChargesFixed_Rates(m_App, roomType_ID, Final_days); 
                           if(Charges!=null){  
                            
                            Cancel_Chrg_Perc = Double.parseDouble(Charges[0].toString());
                            Fix_Charge = Double.parseDouble(Charges[1].toString());
                            
                           // TotalAmt = Booked_room_status.getTotalAmount(m_App, BookingID);
                            TotalAmt = showdata.getCHARGES();
                            
                            Double Temp_amt = ((TotalAmt*Cancel_Chrg_Perc)/100);
                            Cancel_Charge = Temp_amt+Fix_Charge;   
                             
                            
                            int cnl_req3 = JOptionPane.showConfirmDialog(jPanel2, " Your Cancellation Charge will be : "+decimalFormat.format(Cancel_Charge)+ "/- . Do you still want to continue ?? " , "Booking Cancellation" , JOptionPane.YES_NO_OPTION);
                                if(cnl_req3 == JOptionPane.YES_OPTION){
                                    
                                    
                                  Refund_Amt = Advance_Recv - Cancel_Charge; 
                                  
                                  if(Refund_Amt>=0){
                                  
                                  TID=UUID.randomUUID().toString();
                                  CancelReason = "Room Cancelled after advance payment deducting cancellation charge of Rs."+decimalFormat.format(Cancel_Charge)+" /- .";     
                                  try {
                                          Rfd_Voucher_No = getNextVoucherNo();  
                                      } catch (BasicException ex) {
                                          Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                
                                  if(Rfd_Voucher_No!=null && Rfd_Voucher_No.trim().length()>0 && Rfd_Voucher_No!="")
                                  {
                                      
                                  Transaction t = new Transaction(m_App.getSession()) {    
                                    
                                   Double Amt_Adjusted = Cancel_Charge;
                                   Double Bal_amt = 0.00;
                                   String Advnce_Adjst_Ref = "";
                                   String Refund_Ref = "";
                                   
                                     
                                      
                                  @Override
                                  protected Object transact() throws BasicException {
                                    try { 
                                        
                                        
                                      int Update_Advance_Against_Hall  =  new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom  SET ADVNCE_ADJUST = ? , ADJUST_REF = ? ,BAL_AMT=? , REFUND_AMT =? , REFUND_REF=? , REFUND_BY=? , REFUND_DATE=? , REFUND_HOST=? , ACTIVE=0 WHERE BOOKING_ID =  ?"
                                                           , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  })).exec
                                                            (new Object[]{Amt_Adjusted ,Advnce_Adjst_Ref ,  Bal_amt , Refund_Amt , Refund_Ref ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , BookingID });
                                             
                                        
                                        
                                      int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET STATUS=1 , REQ_CAN_BY=? , REQ_CAN_DATE=? , REQ_CAN_HOST=? , CANCEL_REASON=? WHERE ID = ?"
                                                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.STRING  })).exec
                                                                       (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , CancelReason ,BookingID });




                                     for(int i=1;i<=no_of_days_bkd ; i++ ){

                                    Calendar c = Calendar.getInstance(); 
                                    c.setTime(Booked_date_EX);
                                    c.add(Calendar.DATE, i-1);
                                    Booked_date_EX_TEMP = c.getTime();   
                                    no_of_rooms_booked_by_mem = showdata.getNO_OF_ROOMS_BOOKED();
                                    getBooked_rooms = Booked_room_status.getRoom_Booked(m_App ,roomType_ID , Booked_date_EX_TEMP);

                                    if(getBooked_rooms >= no_of_rooms_booked_by_mem){ 

                                       int update_room_availibility = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=?  WHERE BOOKED_DATES=? AND ROOM_TYPE=?"
                                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                       (new Object[]{ (getBooked_rooms - no_of_rooms_booked_by_mem ) , Booked_date_EX_TEMP , roomType_ID  });

                                        }
                                   }


                                  
                                     /*  //DELETE ADVANCE PAYMENT DETAIL FROM GUEST ROOM BOOKED DETAILS 
                                     int delete_From_Room_Advnce_Detail  =  new PreparedSentence(m_App.getSession(), "DELETE FROM  guestroom_advance_payment  WHERE BOOKING_ID = ?  "
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                       (new Object[]{BookingID}); 

*                                   */
                                   
                                   
                                 
                                   // ACCOUNTING AND VOUCHER ENTRIES ..... 
                                 
                                   
                                   
                                  int Refund_Voucher =  new PreparedSentence(m_App.getSession(), "INSERT INTO room_hall_refund_voucher (ID , RV_NO , CUST_ID , MEM_NO , BOOKING_SEQ_NO ,BILLED_AMT , ADVNCE_AMT , REFUND_AMT , CHK_IN_ID , REFUND_BY  , REFUND_HOST ,  REFUND_DATE , CANCELLED , ROOMTYPE ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                 , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.INT , Datas.STRING})).exec
                                                                (new Object[]{UUID.randomUUID().toString() ,Rfd_Voucher_No , Member_ID , MEMBER_NO , Booking_Seq_No ,TotalAmt , Advance_Recv ,Refund_Amt  , BookingID , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() , new Date() , 1 , RoomType_N }); 

                                   
                                   
                                    // ACCOUNTING ENTRIES..... 
                                   
                                   TRANSREF = MEMBER_NO + " , Refund Voucher No : "+Rfd_Voucher_No+" # amt :"+Refund_Amt+" /- for Room cancellation created againts booking no: "+Booking_Seq_No ;     
                                   NARRATION = "Refund of "+Refund_Amt+ " /- to member : "+MEMBER_NO+" . Voucher no "+Rfd_Voucher_No+ " , for Room cancellation";
                                             
                                             
                                   int  INSERT_INTO_ACCOUNT5  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_Seq_No ,Refund_Amt , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccnt , TID , new Date() , "C" , 1    });                                                                                                

                                             
                                   int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                                            ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_Seq_No ,Advance_Recv , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                                             
                                             
                                             
                                             
                                   int  INSERT_INTO_ACCOUNT2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_Seq_No ,Cancel_Charge , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Cancel_Accnt_ID , TID , new Date() , "C" , 1    });                                                                                                

                                             
                                   
                                   
                                   
                                   
                                   // INSERT INTO CANCELLED DETAILS ...
                                    // COPY TO GUEST ROOM CANCELLED DETAIS TABLE
                                   int copy_cancel_request  =  new PreparedSentence(m_App.getSession(), "INSERT INTO guestroom_cancelled_details SELECT * FROM guestroom_booked_details   WHERE ID = ? "
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                       (new Object[]{BookingID}); 


                                   //DELETE FROM GUEST ROOM BOOKED DETAILS 
                                   int delete_From_Room_booked_details  =  new PreparedSentence(m_App.getSession(), "DELETE FROM  guestroom_booked_details  WHERE ID = ?  "
                                                                      , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                       (new Object[]{BookingID}); 

                                   
                                   

                                    } catch (BasicException ex) {
                                         ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                        Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                             return null;
                                         }
                                     };
                                         try {
                                             t.execute();
                                             PrintRefundReciept( MEMBER_NAME, MEMBER_NO , Guest_N ,  RoomType_N, Rooms_booked, no_of_days_bkd, book_date,  Cancel_Charge , TotalAmt,  Advance_Recv , Refund_Amt);
                                              loaddata();
                                             // generateRefundVoucher();
                                              UpdateRefundVoucher();
                                             

                                              String MobNo = dmang.getcustMobileNoByCustID(Member_ID);
                                              String Msg = "Dear Member,Cancellation of "+showdata.getROOM_TYPE()+" Room has charged Rs."+decimalFormat.format(Cancel_Charge)+"/- from advnce recvd of Rs."+decimalFormat.format(Advance_Recv)+"/-";
                                              System.out.println("Cancel msg = "+Msg.length());
                                              if(MobNo!=null && MobNo.trim().length()>0){
                                                    dmang.InsertActiveMsgTable(Msg, Member_ID , MobNo, 2);
                                              }
                                              
                                              JOptionPane.showMessageDialog(this, "Request Canceled..! ", "Success", JOptionPane.INFORMATION_MESSAGE);
                                              
                                         } catch (BasicException ex) {
                                             Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                             new MessageInf(ex).show(new JFrame());
                                         }
                                         
                                  } 
                                         
                                         
                                         

                                  }
                                  else{
                                        JOptionPane.showMessageDialog(this, "Advance Amount Recieved is Less than cancellation charge. \n Please pay remaining amount for cancellation..!!  ", " Date", JOptionPane.ERROR_MESSAGE);    
                                  }
                                }
                           }
                           else{
                                     JOptionPane.showMessageDialog(this, "Cancellation Rate Is not Defined for "+RoomType_N+"."+" Define Cancellation Rate first..!! ", " Date", JOptionPane.ERROR_MESSAGE);    
                           }

                        }
                        else{
                             JOptionPane.showMessageDialog(this, "Already Checked In. Cannot Cancel..!! ", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } 
              }
              
              else{
                  JOptionPane.showMessageDialog(this, "Hall is Booked.!   ", " hall", JOptionPane.ERROR_MESSAGE);
              }
            }
            else{
                 JOptionPane.showMessageDialog(this, "Permission Denied..!   ", " hall", JOptionPane.ERROR_MESSAGE);
            }
           }
       }
       
    }//GEN-LAST:event_cancel_requestActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       availibility_panel.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if(roomTyppe_combo.getSelectedIndex()!=-1){
            if(Check_IN.getText()!=null && Check_IN.getText().trim().length()>0){
                Date Check_in_DATE = new Date();
                Date Check_out_DATE = new Date();
               
               
                int Max_Days = Integer.parseInt(max_Days_label.getText());
                
                try {
                    Check_in_DATE = (Date) Formats.DATE.parseValue(Check_IN.getText());
                    //Check_out_DATE = (Date) Formats.DATE.parseValue(Check_OUT.getText());//commented by pratima: was creating nullpointer exception bcz till now checkout date is not filled 
                } catch (BasicException ex) {
                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                Calendar call = Calendar.getInstance();
                call.setTimeInMillis(Check_in_DATE.getTime());
                call.add(Calendar.DATE,Max_Days );
                Date afterDate = call.getTime();
                
               
                try{
                  
                   Check_out_DATE = JCalendarDialog.showCalendar(this, Check_in_DATE);
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
                               String Room_ID = room_id_label.getText();
                               Date d = Check_in_DATE;
                               int Booked_rooms;
                               int rooms_available;
                               int no_of_rooms_to_be_booked = Integer.parseInt(Booked_no_of_rooms_text.getText());
                               int total_rooms = Integer.parseInt(Tot_room_avail.getText());
                               
                               for(int i=1;i<=no_of_days;i++){
                                    Calendar c = Calendar.getInstance(); 
                                    c.setTime(d);
                                    c.add(Calendar.DATE, i-1);
                                    d = c.getTime();
                                    
                                    Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_ID , d);
                                    rooms_available = total_rooms - Booked_rooms;
                                    if(rooms_available>=no_of_rooms_to_be_booked){
                                       Check_OUT.setText(Formats.DATE.formatValue(Check_out_DATE));
                                       Check_out_label.setVisible(true); 
                                       bookedNo_of_days.setText(""+no_of_days);
                                    }
                                    else{
                                        
                                        JOptionPane.showMessageDialog(this, " Sorry, Room is already booked on "+Formats.DATE.formatValue(d)+".!! "
                                                + " Only "+ rooms_available +" Rooms are available..!", " hall", JOptionPane.ERROR_MESSAGE);
                                        Check_IN.setText(null);
                                        Check_OUT.setText(null);
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
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       availibility_panel.setVisible(true);
            main_panel.setVisible(false);
            
            if(Check_IN.getText()!=null){
              jTextField1.setText(Check_IN.getText());
            }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void room_booking_noKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_room_booking_noKeyReleased
    if(temp_T.getText()!=null && temp_T.getText().trim().length()>0){
    char c = evt.getKeyChar();
    int Vacant_Rooms=0 ;
    if(temp_T.getText()!=null){
        Vacant_Rooms = Integer.parseInt(temp_T.getText());
    }
    
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(room_booking_no, "Please enter only numbers..");
    
            room_booking_no.setText(null);
     
    }
    else
    {
        int x = Integer.parseInt(room_booking_no.getText());
        if(x>Vacant_Rooms){
            JOptionPane.showMessageDialog(room_booking_no, "Only "+Vacant_Rooms+" are Available for booking..!" );
            room_booking_no.setText(null);
            
        }
      }
           
     
    }
    }
      else{
           JOptionPane.showMessageDialog(room_booking_no, "Select Room Type and Date first..!");
           room_booking_no.setText(null);
      }
    
   
    }//GEN-LAST:event_room_booking_noKeyReleased

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
       request_panel.setVisible(true);
       main_panel.setVisible(false);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
       main_panel.setVisible(true);
       request_panel.setVisible(false);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if(jTable2.getSelectedRow()!=-1){
            
            int row = jTable2.getSelectedRow();
            BookedRoomStatusTableModel.Rejected_room_info showdata = Rejected_Room_Status.getRejectedGuestRoomList().get(row);
            
            mName.setText(showdata.getMemberName());
            
            if(showdata.getMem_flag()==1){
                
                NName.setText("N/A");
                Adress.setText("N/A");
                
                
            }
            else{
                NName.setText(showdata.getNON_MEM_NAME());
                Adress.setText(showdata.getNON_MEM_CONTCT());
                contactNo.setText(showdata.getNON_MEM_CONTCT());
                
            }
            
            
            roomtype.setText(showdata.getROOM_TYPE());
            capacity.setText(""+showdata.getMAX_CAPACITY());
            tariff.setText(decimalFormat.format(showdata.getCHARGES()));
            tax_1.setText(showdata.getLUXURYTAX());
            tax_2.setText(showdata.getTAX2());
            tax_3.setText(showdata.getTAX3());
            bookingDate.setText(showdata.getBOOKING_DATE());
            booked_no_of_days.setText(""+showdata.getNO_OF_DAYS());
            no_of_rooms_bk.setText(""+showdata.getNO_OF_ROOMS_BOOKED());
            cancel_note.setText(showdata.getCANCEL_REASON());
            
            String reject_date = Formats.DATE.formatValue(showdata.getREQ_APPROV_DATE());
            String Last_Pay_Date = Formats.DATE.formatValue(showdata.getLAST_PAYMENT_DATE());
            String name = showdata.getREQ_APPROV_BY();
            message1.setVisible(false);
            if(showdata.getFlag()==2){
                message.setText("Request not Approved by :-  " +name+ " on "+reject_date+"." );
                message.setVisible(true);
            }
            
            if(showdata.getPAYMENT_FLAG()==2){
                message1.setVisible(true);
                message1.setText("Payment Not Recieved in Time. Last date for payment was :"+Last_Pay_Date);
            }
            
            if(showdata.getMem_flag()==1){
               mem_label.setText("( Booked For :- Member )");
            }
            else{
                mem_label.setText("( Booked For :- Non-Member )");
            }
            
            cancel_btn2.setVisible(true);
            cancel_btn.setVisible(false);
            submit_panel.setVisible(true);
            request_panel.setVisible(false);
            jButton6.setVisible(false);
            
            }
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void cancel_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btn2ActionPerformed
       request_panel.setVisible(true);
       submit_panel.setVisible(false);
       cancel_btn2.setVisible(false);
       cancel_btn.setVisible(true);
    }//GEN-LAST:event_cancel_btn2ActionPerformed

    private void make_pay_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_make_pay_btnActionPerformed
        if(jTable1.getSelectedRow()!=-1){
        
            if(jTable1.getSelectedRow()<Booked_room_status.getRoomSize()){
            int row = jTable1.getSelectedRow();
            BookedRoomStatusTableModel.Room_StatusInfo showdata = Booked_room_status.getGuestRoomList().get(row);
            
            if(showdata.getFlag()==1){
               if(showdata.getBDateChangeFlag()!=2){
                    if(showdata.getPAYMENT_FLAG()!=1 && showdata.getStatus()!=2){  

                              this.setFocusable(false);
                              Billpage bp =   new Billpage(showdata , 0);
                        try {
                             
                            bp.showDialog();
                           
                        } catch (BasicException ex) {
                            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                        }



                    } 
                    else{
                       String bookingId = showdata.getId();

                       int x = JOptionPane.showConfirmDialog(this, " Payment already done..!  \n  Do you want to make another payment..?? ", " Payment ", JOptionPane.YES_NO_OPTION);
                       if(x==JOptionPane.YES_OPTION) {


                           int chk_in_flag = showdata.getCHK_IN_FLAG();

                           if(chk_in_flag==1){
                                  int x1 = JOptionPane.showConfirmDialog(this, " Member had Already CheckedIn..!  \n \n  Do you want to make more payment..?? ", " Payment ", JOptionPane.YES_NO_OPTION);
                                   if(x1==JOptionPane.YES_OPTION) {
                                              this.setFocusable(false);
                                              Billpage bp =   new Billpage(showdata , 1);
                                      try {
                                          bp.showDialog();
                                      } catch (BasicException ex) {
                                          Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                      }


                                   }
                           }
                           else{

                                  this.setFocusable(false);
                                  Billpage bp =   new Billpage(showdata , 1);
                               try {
                                   bp.showDialog();
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                               }

                       }

                  }
                 }
                    
                    
                    
               }
               else{
                   JOptionPane.showMessageDialog(this, " Requested to change booking date. kindly wait for approval ", " Payament Warning", JOptionPane.ERROR_MESSAGE);
               }
                    
         }
            else{
                 JOptionPane.showMessageDialog(this, " Request is Still Pending. Wait for approval.", " Warning", JOptionPane.ERROR_MESSAGE);
            }
           }
        }
        else{
            make_pay_btn.setEnabled(false);
        }
        
    }//GEN-LAST:event_make_pay_btnActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
            cardno_text.requestFocus();
            cardno_text.setText(null);
            memno_text.setText(null);
            mname_text.setText(null);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void cardno_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardno_textActionPerformed
         String custoid;
        String cust = cardno_text.getText();
        if(cust!=null && cust.length()>0)
        {
        try {
           
              
            Object[] obj=null;  
              if(CardAccessFlag==3){
                      obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=? AND C.VISIBLE=TRUE  UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=? AND C.VISIBLE=TRUE ",
                                  new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{cust, cust});
                  System.out.println("Member+Spouse+Dependant");
               }  
             if(CardAccessFlag==2){
                     obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=? AND M.DTYPE='Spouse' AND C.VISIBLE=TRUE  UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=? AND C.VISIBLE=TRUE",
                                  new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{cust, cust});
                     System.out.println("Member+Spouse");
                }
                if(CardAccessFlag==1){
                     obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=? AND C.VISIBLE=TRUE",
                                  new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{ cust});

                     System.out.println("Member Only");

                }
            
            
            
            
            if (obj == null) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                cardno_text.setText(null);
                cardno_text.requestFocus();
            } else {
                custoid = obj[0].toString();
                customerInfo = dlSales.loadCustomerExt(custoid);
                memno_text.setText(obj[1].toString());
                mname_text.setText(obj[2].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        }
        //akshatha:to read a card from card reader without port num
        //cardno_text.transferFocus();
    }//GEN-LAST:event_cardno_textActionPerformed

     BookedRoomStatusTableModel.Room_StatusInfo showdataChanged;
    
    private void ChangeBkngDate_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeBkngDate_ButtonActionPerformed
       if(jTable1.getSelectedRow()!=-1){
           
           int row = jTable1.getSelectedRow();
           showdataChanged = Booked_room_status.getGuestRoomList().get(row);
           
           if(showdataChanged.getFlag()==1){
                if(showdataChanged.getCHK_IN_FLAG()==0){

                     String RoomType = showdataChanged.getROOM_TYPE();
                     for(int i=0;i<roomType_list.size();i++){
                         String r=roomType_list.get(i).toString();
                         if(r.equals(RoomType)){
                             roomTyppe_combo.setSelectedIndex(i);
                             break;
                         }
                     }
                     int NoofroomsBooked= showdataChanged.getNO_OF_ROOMS_BOOKED();
                     int NoofDays = showdataChanged.getNO_OF_DAYS();
                     Date Bookeddate= showdataChanged.getBOOKED_DATE_EX();

                     jLabel45.setText(showdataChanged.getMem_No()+" - "+showdataChanged.getMemberName());


                     roomtype_label.setText(RoomType);
                     noofrooms_label.setText(NoofroomsBooked+"");
                     noofdays_label.setText(NoofDays+"");
                     chengedCheckin_Text.setText(Formats.DATE.formatValue(Bookeddate));
                     Date CheckOutDate  = null; 
                     Calendar cx = Calendar.getInstance();
                     cx.setTime((Bookeddate));
                     cx.add(Calendar.DATE, showdataChanged.getNO_OF_DAYS());  // number of days to add
                     CheckOutDate = cx.getTime(); 
                     ChangedCheckout_Text.setText(Formats.DATE.formatValue(CheckOutDate));

                     BookingDateChange_Panel.setVisible(true);
                     main_panel.setVisible(false);

                }
                else{
                    JOptionPane.showMessageDialog(this, "Check-in already done . cannot change date.");
                }
           }
           else{
                JOptionPane.showMessageDialog(this, "Request still pending. . cannot change date.");
           }
           
       }
    }//GEN-LAST:event_ChangeBkngDate_ButtonActionPerformed

    private void ChangedCheckIn_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangedCheckIn_ButtonActionPerformed
       int Advance_book_dura = 3;
                    if(A_date.getText()!=null){
                       Advance_book_dura = Integer.parseInt(A_date.getText());
                   }

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
                        date = (Date) Formats.DATE.parseValue(chengedCheckin_Text.getText());
                    } catch (BasicException e) {
                        date = null;
                    }
                      try{
                    date = JCalendarDialog.showCalendar(this, date);
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
                                
                                
                                
                              //  for(int i=1;i<=Days ; i++){
                               //      Calendar c = Calendar.getInstance(); 
                               //      c.setTime(d);
                               //      c.add(Calendar.DATE, i-1);
                                //     d = c.getTime();
                                     
                                    // call for booked rooms 
                                 //   Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,room_type_ID , d);
                                   
                           chengedCheckin_Text.setText(Formats.DATE.formatValue(date));
                           Date CheckOutDate  = null; 
                           
                           String Room_ID = room_id_label.getText();
                               Date d = date;
                               int Booked_rooms;
                               int rooms_available;
                               int no_of_rooms_to_be_booked = showdataChanged.getNO_OF_ROOMS_BOOKED();
                               int total_rooms = Integer.parseInt(Tot_room_avail.getText());
                               
                               for(int i=1;i<=showdataChanged.getNO_OF_DAYS();i++){
                                    Calendar cy = Calendar.getInstance(); 
                                    cy.setTime(d);
                                    cy.add(Calendar.DATE, i-1);
                                    d = cy.getTime();
                                    
                                    Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_ID , d);
                                    rooms_available = total_rooms - Booked_rooms;
                                    if(rooms_available>=no_of_rooms_to_be_booked){
                                        
                                        
                                    
                                        Calendar cx = Calendar.getInstance();
                                        cx.setTime((date));
                                        cx.add(Calendar.DATE, showdataChanged.getNO_OF_DAYS());  // number of days to add
                                        CheckOutDate = cx.getTime(); 
                            
                            
                                        ChangedCheckout_Text.setText(Formats.DATE.formatValue(CheckOutDate));
                                    }
                                    else{
                                        
                                        JOptionPane.showMessageDialog(this, " Sorry, Room is already booked on "+Formats.DATE.formatValue(d)+".!! "
                                                + " Only "+ rooms_available +" Rooms are available..!", "Guest Rooms", JOptionPane.ERROR_MESSAGE);
                                       
                                        ChangedCheckout_Text.setText(null);
                                        chengedCheckin_Text.setText(null);
                                        break;
                                    }
                           
                           
                           
                                    
                          //   }
                        }

                    }
          }
                      }
        catch(Exception e1){
              e1.printStackTrace();
          }
    


    }//GEN-LAST:event_ChangedCheckIn_ButtonActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
       Date Check_in_DATE = new Date();
                Date Check_out_DATE = new Date();
                int Max_Days = Integer.parseInt(max_Days_label.getText());
                
                try {
                    Check_in_DATE = (Date) Formats.DATE.parseValue(chengedCheckin_Text.getText());
                    Check_out_DATE = (Date) Formats.DATE.parseValue(ChangedCheckout_Text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Calendar call = Calendar.getInstance();
                call.setTimeInMillis(Check_in_DATE.getTime());
                call.add(Calendar.DATE,Max_Days );
                Date afterDate = call.getTime();
                
                
                try{
                    Check_out_DATE = JCalendarDialog.showCalendar(this, Check_in_DATE);
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
                               String Room_ID = room_id_label.getText();
                               Date d = Check_in_DATE;
                               int Booked_rooms;
                               int rooms_available;
                               int no_of_rooms_to_be_booked = Integer.parseInt(Booked_no_of_rooms_text.getText());
                               int total_rooms = Integer.parseInt(Tot_room_avail.getText());
                               
                               for(int i=1;i<=no_of_days;i++){
                                    Calendar c = Calendar.getInstance(); 
                                    c.setTime(d);
                                    c.add(Calendar.DATE, i-1);
                                    d = c.getTime();
                                    
                                    Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_ID , d);
                                    rooms_available = total_rooms - Booked_rooms;
                                    if(rooms_available>=no_of_rooms_to_be_booked){
                                       ChangedCheckout_Text.setText(Formats.DATE.formatValue(Check_out_DATE));
                                       
                                       bookedNo_of_days.setText(""+no_of_days);
                                    }
                                    else{
                                        
                                        JOptionPane.showMessageDialog(this, " Sorry, Room is already booked on "+Formats.DATE.formatValue(d)+".!! "
                                                + " Only "+ rooms_available +" Rooms are available..!", "Guest Rooms", JOptionPane.ERROR_MESSAGE);
                                       
                                        ChangedCheckout_Text.setText(null);
                                        break;
                                    }
                                    
                               }
                               
                              
                        }

                    }
          }
        catch(Exception e1){
              e1.printStackTrace();
          }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void Cancel3_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel3_buttonActionPerformed
       BookingDateChange_Panel.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_Cancel3_buttonActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        if(chengedCheckin_Text.getText()!=null && chengedCheckin_Text.getText().trim().length()>0){
            if(ChangedCheckout_Text.getText()!=null && ChangedCheckout_Text.getText().trim().length()>0){
                
                    int submit_hall = JOptionPane.showConfirmDialog(submit_panel, "Do You Want to block Room .. ?? " , "Blocking Confirmation" , JOptionPane.YES_NO_OPTION);
                    if(submit_hall == JOptionPane.YES_OPTION){
                       
                       TAX1_ID = tax1_Id_label.getText();
                       TAX2_ID = tax2_Id_label.getText(); 
                       TAX3_ID = tax3_Id_label.getText();
                       
                       TOTAL_ROOMS_AVAIL = Integer.parseInt(Tot_room_avail.getText());
                       
                       
                       
                       BOOKING_DATE=chengedCheckin_Text.getText();
                       try {
                             BOOKING_DATE_EX  = (Date) Formats.TIMESTAMP.parseValue(BOOKING_DATE);
                         } catch (BasicException ex) {
                             Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                         }
                       
                        
                        
                        Calendar cal = Calendar.getInstance(); // creates calendar
                        cal.setTime(BOOKING_DATE_EX); // sets calendar time/date
                        cal.add(Calendar.HOUR_OF_DAY, (int)time_Hours); // adds one hour
                        cal.getTime();
                        
                        BOOKING_DATE_EX = cal.getTime();
                        STATUS = 3;
                        FLAG = Booked_room_status.getFlag(m_App);
                        
                        
                        
                       try {
                              Booking_Seq_No =  getNextGuestRoom_Sequence();
                        } catch (BasicException ex) {
                              Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                         }    

                       if(Booking_Seq_No!=null && Booking_Seq_No.trim().length()>0 && Booking_Seq_No!="") 
                       {
                        Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                                 String CurrentBookingID=UUID.randomUUID().toString();
                            
                                    @Override      
                                    protected Object transact() throws BasicException {   

                                       

                                   int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_booked_details (ID ,BOOKING_SEQ_NO , ROOM_TYPE , BOOKING_DATE , MEMBERNAME , MEMBER_FLAG ,NON_MEMBER_FLAG , STATUS , FLAG , ROOM_NOS , CRBY  , CRDATE ,  CRHOST , BOOKING_DATE_EX , BOOKING_DAYS , MEMBER_NO , NON_MEM_NAME , NON_MEM_CNTCT , NON_MEM_ADDR , CHARGES , LUXURYTAX , TAX2 , TAX3 , BASIC1 , CASCADE1 , MAX_CAPACITY , BASIC2 , CASCADE2 , LAST_PAYMENT_DATE , PAYMENT_FLAG , ROLE , REF ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.INT ,Datas.INT ,Datas.INT  ,Datas.INT   , Datas.INT , Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP , Datas.INT , Datas.STRING , Datas.STRING ,  Datas.STRING , Datas.STRING  , Datas.DOUBLE , Datas.STRING ,Datas.STRING , Datas.STRING , Datas.INT , Datas.INT , Datas.INT , Datas.INT , Datas.INT ,Datas.TIMESTAMP , Datas.INT , Datas.STRING ,Datas.STRING  })                         
                                    ).exec(new Object[]{CurrentBookingID,Booking_Seq_No , showdataChanged.getROOMTYPE_ID() ,BOOKING_DATE ,showdataChanged.getMEMBER_ID() ,showdataChanged.getMem_flag() ,showdataChanged.getNon_Mem_flag() , STATUS  , FLAG ,showdataChanged.getNO_OF_ROOMS_BOOKED() ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , BOOKING_DATE_EX , showdataChanged.getNO_OF_DAYS() , showdataChanged.getMem_No(), showdataChanged.getNON_MEM_NAME() , showdataChanged.getNON_MEM_CONTCT() , showdataChanged.getNON_MEM_ADDR() , showdataChanged.getCHARGES() , TAX1_ID ,TAX2_ID , TAX3_ID , BASIC1 , CASCADE1 , MAX_CAPACITY , BASIC2 , CASCADE2 ,Last_payment_date , showdataChanged.getPAYMENT_FLAG() , m_App.getAppUserView().getUser().getRole() ,showdataChanged.getId()  });                                                                                                


                                        load_availibility(BOOKING_DATE_EX , showdataChanged.getROOMTYPE_ID() , showdataChanged.getNO_OF_ROOMS_BOOKED() ,TOTAL_ROOMS_AVAIL , showdataChanged.getNO_OF_DAYS());
                                        Update_GuestRoomSeq();


                                        
                                     //  int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET STATUS=1 , REQ_CAN_BY=? , REQ_CAN_DATE=? , REQ_CAN_HOST=?  WHERE ID = ?"
                                                       //               , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  })).exec
                                                         //              (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() ,showdataChanged.getId() });
 
                                        
                                       
                                       
                                       // GUEST ROOM AVAILBILITY 
                                     /*  
                                       for(int i=1;i<=showdataChanged.getNO_OF_DAYS() ; i++ ){

                                                Calendar c = Calendar.getInstance(); 
                                                c.setTime(showdataChanged.getBOOKED_DATE_EX());
                                                c.add(Calendar.DATE, i-1);
                                                c.set(Calendar.MILLISECOND, 0);
                                                c.set(Calendar.SECOND, 0);
                                                c.set(Calendar.MINUTE, 0);
                                                c.set(Calendar.HOUR, 0);
                                                Booked_date_EX_TEMP = c.getTime();   

                                               getBooked_rooms = Booked_room_status.getRoom_Booked(m_App ,showdataChanged.getROOMTYPE_ID() , Booked_date_EX_TEMP);

                                               if(getBooked_rooms >= showdataChanged.getNO_OF_ROOMS_BOOKED()){ 

                                                   int update_room_availibility = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=?  WHERE BOOKED_DATES=? AND ROOM_TYPE=?"
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                                   (new Object[]{ (getBooked_rooms - showdataChanged.getNO_OF_ROOMS_BOOKED() ) , Booked_date_EX_TEMP , showdataChanged.getROOMTYPE_ID()  });

                                               }
                                       }
                                       
                                       */
                                       
                                        
                                         int UpdateoldBooking = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET BDATECHANGEFLAG=?  WHERE  ID=?  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.STRING  })).exec
                                                                                   (new Object[]{ 2 ,showdataChanged.getId() });
                                           
                                        
                                       
                                        ////////////////////////////////////////////////////////////////////////////////////////////////////////
                                       
                                       if(showdataChanged.getPAYMENT_FLAG()==1){
                                           
                                           // PUT THE CODE WHEN REQUEST IS APPROVED
                                           
                                          // int UpdateAdvanceDetails = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_advance_payment  SET BOOKING_ID=? , BOOKING_SEQ_NO=? , CHECK_IN_DATE=?   WHERE  BOOKING_ID=?  "
                                              //                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING  })).exec
                                              //                                     (new Object[]{ CurrentBookingID , Booking_Seq_No , BOOKING_DATE_EX, showdataChanged.getId() });
                                       
                                       
                                         //  int UpdateAdvanceDetails2 = new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom  SET BOOKING_ID=?  WHERE  BOOKING_ID=?  "
                                                   //                               , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING  })).exec
                                                   //                                (new Object[]{ CurrentBookingID ,showdataChanged.getId() });
                                           
                                           
                                       }
                                        

                                       
                                       
                                       
                                      return null;                                      
                                        }                            
                                    };                 

                                    try {                 
                                        t.execute();          



                                        JOptionPane.showMessageDialog(this, "Room Blocked Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        reset();
                                        loaddata();

                                    }
                                 catch (BasicException ex) {                    
                                            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                        ex.printStackTrace();
                                                       new MessageInf(ex).show(new JFrame());

                                        }

                          }

                         }
                
                
                
                
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Date field should not be empty");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Date field should not be empty");
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    //added by pratima
    private void ExtChkoutDt_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtChkoutDt_ButtonActionPerformed
    if(jTable1.getSelectedRow()!=-1){
        int row = jTable1.getSelectedRow();
        showdataChanged = Booked_room_status.getGuestRoomList().get(row);
       if(showdataChanged.getCHK_IN_FLAG()==1){
            String RoomType = showdataChanged.getROOM_TYPE();
            for(int i=0;i<roomType_list.size();i++){
                String r=roomType_list.get(i).toString();
                if(r.equals(RoomType)){
                    roomTyppe_combo.setSelectedIndex(i);
                    break;
                }
            }
           
            int NoofroomsBooked= showdataChanged.getNO_OF_ROOMS_BOOKED();
            Date Bookeddate= showdataChanged.getBOOKED_DATE_EX();
            jLabel51.setText(showdataChanged.getMem_No()+" - "+showdataChanged.getMemberName());
            Date CheckOutDate  = null; 
                     Calendar cx = Calendar.getInstance();
                     cx.setTime((Bookeddate));
                     cx.add(Calendar.DATE, showdataChanged.getNO_OF_DAYS());  // number of days to add
                     CheckOutDate = cx.getTime(); 
                     ChangedCheckout1_Text.setText(Formats.DATE.formatValue(CheckOutDate));

            roomtype_label1.setText(RoomType);
            noofrooms_label1.setText(NoofroomsBooked+"");
            chengedCheckin1_Text.setText(Formats.DATE.formatValue(Bookeddate));
        jPanel1.setVisible(true);
        ext_bookedNo_of_days.setVisible(false);
        main_panel.setVisible(false);    
       }
             else{ 
            JOptionPane.showMessageDialog(this, "You can extend checkout date after checkin.");
              }
    }
    
    }//GEN-LAST:event_ExtChkoutDt_ButtonActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
                      
                Date Check_in_DATE = new Date();
                Date Check_out_DATE = new Date();
                
                int Max_Days = Integer.parseInt(max_Days_label.getText());
                
                try {
                    Check_in_DATE = (Date) Formats.DATE.parseValue(chengedCheckin1_Text.getText());
                    Old_Check_out_DATE = (Date) Formats.DATE.parseValue(ChangedCheckout1_Text.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Calendar call = Calendar.getInstance();
                call.setTimeInMillis(Check_in_DATE.getTime());
                call.add(Calendar.DATE,Max_Days );
                Date afterDate = call.getTime();
                 Date Currdate = new Date();
                  
                 Calendar c1 = Calendar.getInstance();
                  c1.setTimeInMillis(new Date().getTime());
                  c1.add(Calendar.DATE, -1);
                  Currdate = c1.getTime();
                
                try{
                   Check_out_DATE = JCalendarDialog.showCalendar(this, Currdate);
                  
                    if (Check_out_DATE != null) {
                        if(Check_out_DATE.before(Check_in_DATE))
                        {
                            JOptionPane.showMessageDialog(this, "Date Selected Should be after Check IN date..!!");

                        }
                        else if(Check_out_DATE.after(afterDate))
                        {
                           JOptionPane.showMessageDialog(this, "Booking is available upto "+Max_Days+" Days only..!!! ");
                        }
                         else if(Check_out_DATE.equals(Old_Check_out_DATE))
                        {
                           JOptionPane.showMessageDialog(this, "Extended checkout date should be aftter Chekout date ");
                        }
                        else{
                               int no_of_days = (int) (Check_out_DATE.getTime() - Old_Check_out_DATE.getTime())/(1000 * 60 * 60 * 24);
                                 
                               String Room_ID = room_id_label.getText();
                               Date d = Old_Check_out_DATE;
                               int Booked_rooms;
                               int rooms_available;
                               
                               int no_of_rooms_to_be_booked = Integer.parseInt(noofrooms_label1.getText());
                               
                               int total_rooms = Integer.parseInt(Tot_room_avail.getText());
                               
                               for(int i=1;i<=no_of_days;i++){
                                    Calendar c = Calendar.getInstance(); 
                                    c.setTime(d);
                                    c.add(Calendar.DATE, i-1);
                                    d = c.getTime();
                                    
                                    Booked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_ID , d);
                                    rooms_available = total_rooms - Booked_rooms;
                                    if(rooms_available>=no_of_rooms_to_be_booked){
                                       ChangedCheckout1_Text.setText(Formats.DATE.formatValue(Check_out_DATE));
                                       ext_bookedNo_of_days.setText(""+no_of_days);
                                     
                                    }
                                    else{
                                        
                                        JOptionPane.showMessageDialog(this, " Sorry, Room is already booked on "+Formats.DATE.formatValue(d)+".!! "
                                                + " Only "+ rooms_available +" Rooms are available..!", "Guest Rooms", JOptionPane.ERROR_MESSAGE);
                                       
                                        ChangedCheckout1_Text.setText(null);
                                         ext_bookedNo_of_days.setText(null);
                                        break;
                                    }
                                    
                               }
                               
                              
                        }

                    }
          }
        catch(Exception e1){
              e1.printStackTrace();
          }
        
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        
                              
        extendDateFlag=0;
         BillModel=new  BillTableModel(m_App);
        int row = jTable1.getSelectedRow();
        showdataChanged = Booked_room_status.getGuestRoomList().get(row);
        Old_NO_OF_DAYS=showdataChanged.getNO_OF_DAYS();
        Old_Id=showdataChanged.getId();
        showdata = Booked_room_status.getGuestRoomList().get(row);
         BillModel.setRsi(showdata);
        Old_Final_Amount= BillModel.getTotAmt_Room(showdata);
      
        if(chengedCheckin1_Text.getText()!=null && chengedCheckin1_Text.getText().trim().length()>0){
      if(ChangedCheckout1_Text.getText()!=null && ChangedCheckout1_Text.getText().trim().length()>0){
                
                    int submit_hall = JOptionPane.showConfirmDialog(submit_panel, "Do You Want to block Room .. ?? " , "Blocking Confirmation" , JOptionPane.YES_NO_OPTION);
                    if(submit_hall == JOptionPane.YES_OPTION){
                       
                        TAX1_ID = tax1_Id_label.getText();
                       TAX2_ID = tax2_Id_label.getText(); 
                       TAX3_ID = tax3_Id_label.getText();
                         
                       
                       TOTAL_ROOMS_AVAIL = Integer.parseInt(Tot_room_avail.getText());
                        BOOKING_DATE=chengedCheckin1_Text.getText();
                            try {
                             BOOKING_DATE_EX = (Date)Formats.TIMESTAMP.parseValue(BOOKING_DATE);
                             
                                }catch (BasicException ex) {
                             Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                         BOOKING_DATE=ChangedCheckout1_Text.getText();
                      try {
                            CHECKOUT_DATE_EX  = (Date)Formats.TIMESTAMP.parseValue(BOOKING_DATE);
                            } catch (BasicException ex) {
                             Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                         }
                   
                      
                      
                     try {
                              Booking_Seq_No =  getNextGuestRoom_Sequence();
                        } catch (BasicException ex) {
                              Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                         }    

                       if(Booking_Seq_No!=null && Booking_Seq_No.trim().length()>0 && Booking_Seq_No!="") 
                       {
                        Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                          @Override      
                                    protected Object transact() throws BasicException {   
                                   
                                       
                                       //code to change total days in book details table
                                       
                                      
                                        NO_OF_DAYS =(int)((CHECKOUT_DATE_EX.getTime() - BOOKING_DATE_EX.getTime()) / 1000 / 60 / 60 / 24);
                                    
                                          int UpdateoldBooking = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET CDATECHANGEFLAG=?,BOOKING_DAYS=? WHERE  ID=?  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.INT , Datas.STRING  })).exec
                                                                                   (new Object[]{ 1 ,NO_OF_DAYS, showdataChanged.getId() });
                                          
                                        
                                          /////////////////////////////////////
                                      
                                       if(showdataChanged.getPAYMENT_FLAG()==1){
                                           BillModel=new BillTableModel(m_App);
                                           int row = jTable1.getSelectedRow();
                                           showdata = Booked_room_status.getGuestRoomList().get(row);//line to bring booking details with extended no of days from bookguestroom page
                                           showdata.setNO_OF_DAYS(NO_OF_DAYS);
                                           BillModel.setRsi(showdata);
                                           
                                           Final_Amount= BillModel.getTotAmt_Room(showdata);
                                            Calendar c11 = Calendar.getInstance();
                                            c11.setTimeInMillis(CHECKOUT_DATE_EX.getTime());
                                            c11.add(Calendar.HOUR_OF_DAY, 9);
                                            CHECKOUT_DATE_EX = c11.getTime();
                                           
                                           //query to add above fetched details to database
                                           int UpdateAdvanceDetails = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_advance_payment  SET TOTAL_AMOUNT=?  WHERE  BOOKING_SEQ_NO=?  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE  , Datas.STRING  })).exec
                                                                                (new Object[]{Final_Amount, showdataChanged.getBOOKING_SEQ_NO() });
                                               
                                    Object[]  obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ADVANCE_RECV FROM guestroom_advance_payment WHERE  BOOKING_SEQ_NO=?",
                                     new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{showdataChanged.getBOOKING_SEQ_NO()});
                                    Old_ADVANCE_RECV=Double.parseDouble(obj[0].toString());
                                      int UpdateoldCheckout  = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET DAYS=?,E_CHK_OUT=?,TOT_AMT=?  WHERE  advnce_recv_id in(SELECT ID from guestroom_advance_payment WHERE BOOKING_ID=?)  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.TIMESTAMP ,Datas.DOUBLE, Datas.STRING  })).exec
                                                                                   (new Object[]{NO_OF_DAYS, CHECKOUT_DATE_EX, Final_Amount, showdataChanged.getId() });
                                    
                                       }
                                          
                   //  int UpdateAdvanceDetails2 = new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_guestroom  SET BOOKING_ID=?  WHERE  BOOKING_ID=?  "
                                                   //                               , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING  })).exec
                                                   //                                (new Object[]{ CurrentBookingID ,showdataChanged.getId() });
                                           
                                           /////////////////////////////////////
                                        //code to load availability for extra days
                                       load_availibility(Old_Check_out_DATE, showdataChanged.getROOMTYPE_ID() , showdataChanged.getNO_OF_ROOMS_BOOKED() ,TOTAL_ROOMS_AVAIL , NO_OF_DAYS- Old_NO_OF_DAYS);
                                        Update_GuestRoomSeq();
                                       /////////////////////////////////////
                                       
                                          
                                          return null;                                      
                                        }                            
                                    };                 

                                    try {                 
                                        t.execute();          
                                
                                     if(Old_ADVANCE_RECV<Final_Amount){
                                       int submit_hall1 = JOptionPane.showConfirmDialog(submit_panel, "insufficient amount to block room. Do you want to do payment ?? " , "Blocking Confirmation" , JOptionPane.YES_NO_OPTION);
                                        if(submit_hall1 == JOptionPane.YES_OPTION){
                                            setExtendDateFlag();
                                             this.setFocusable(false);
                                              Billpage bp =   new Billpage(showdata , 1);
                                              
                                         try {
                                          bp.showDialog();
                                          } catch (BasicException ex) {
                                          Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                                         }
//                                        
                                        }
                                         if((submit_hall1 == JOptionPane.NO_OPTION)|| (submit_hall1 == JOptionPane.CLOSED_OPTION)){
                                             
                                          cancleCheckoutDateExtend();
                                        }
                                 }else{
                                         JOptionPane.showMessageDialog(this, "Room Blocked Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                     }
                                    reset();
                                    loaddata();    
                                    }

                                      catch (BasicException ex) {                    
                                            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                        ex.printStackTrace();
                                                       new MessageInf(ex).show(new JFrame());

                                            }
                          /*         try{
                                       
                                    if(  bill.getFinalAmtOfBookingId_Room(showdataChanged.getId())< bill.getAdvancePaidAmtByBookingId_Room(showdataChanged.getId())   )
                                 { 
                                     JOptionPane.showMessageDialog(this, "Insufficient amount to block the room. Please do the payment.");
                                     reset();
                                     loaddata();
                                 }else{
                                        load_availibility(BOOKING_DATE_EX , showdataChanged.getROOMTYPE_ID() , showdataChanged.getNO_OF_ROOMS_BOOKED() ,TOTAL_ROOMS_AVAIL , showdataChanged.getNO_OF_DAYS());
                                        Update_GuestRoomSeq();
                                         JOptionPane.showMessageDialog(this, "Room Blocked Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        reset();
                                        loaddata();    
                                    }
                                   }catch (BasicException ex) {                    
                                            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                        ex.printStackTrace();
                                                       new MessageInf(ex).show(new JFrame());

                                            }*/
                       } 
                    }   if((submit_hall == JOptionPane.NO_OPTION)|| (submit_hall == JOptionPane.CLOSED_OPTION)){
                     ChangedCheckout1_Text.setText(Formats.DATE.formatValue(Old_Check_out_DATE));
                    }              
      }
            else{
                JOptionPane.showMessageDialog(this, "Date field should not be empty");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Date field should not be empty");
        }
      
    }//GEN-LAST:event_jButton28ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A_date;
    private javax.swing.JTextArea Adress;
    private javax.swing.JTextField Booked_no_of_rooms_text;
    private javax.swing.JPanel BookingDateChange_Panel;
    private javax.swing.JButton Cancel3_button;
    private javax.swing.JButton ChangeBkngDate_Button;
    private javax.swing.JButton ChangedCheckIn_Button;
    private javax.swing.JTextField ChangedCheckout1_Text;
    private javax.swing.JTextField ChangedCheckout_Text;
    private javax.swing.JTextField Check_IN;
    private javax.swing.JTextField Check_OUT;
    private javax.swing.JLabel Check_out_label;
    private javax.swing.JButton ExtChkoutDt_Button;
    private javax.swing.JLabel NName;
    private javax.swing.JTextField N_luxuryTax;
    private javax.swing.JTextField N_tax2;
    private javax.swing.JTextField N_tax3;
    private javax.swing.JTextField Tot_room_avail;
    private javax.swing.JPanel availibility_panel;
    private javax.swing.JLabel basicCharge_multiply_label;
    private javax.swing.JLabel basic_tax_label1;
    private javax.swing.JLabel basic_tax_label2;
    private javax.swing.JLabel basic_tax_label3;
    private javax.swing.JTextField bookedNo_of_days;
    private javax.swing.JLabel booked_no_of_days;
    private javax.swing.JLabel bookingDate;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JButton cancel_btn2;
    private javax.swing.JTextArea cancel_note;
    private javax.swing.JButton cancel_request;
    private javax.swing.JLabel capacity;
    private javax.swing.JPasswordField cardno_text;
    private javax.swing.JLabel check_in_label;
    private javax.swing.JTextField chengedCheckin1_Text;
    private javax.swing.JTextField chengedCheckin_Text;
    private javax.swing.JLabel contactNo;
    private javax.swing.JLabel ext_bookedNo_of_days;
    private javax.swing.JLabel image_label1;
    private javax.swing.JLabel image_label2;
    private javax.swing.JLabel image_label3;
    private javax.swing.JPanel image_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JInternalFrame jInternalFrame1;
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
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField luxuryTax;
    private javax.swing.JLabel mName;
    private javax.swing.JPanel main_panel;
    private javax.swing.JButton make_pay_btn;
    private javax.swing.JLabel max_Days_label;
    private javax.swing.JLabel mem_label;
    private javax.swing.JRadioButton mem_radio;
    private javax.swing.JLabel memberNo;
    private javax.swing.JLabel member_label;
    private javax.swing.JTextField memno_text;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message1;
    private javax.swing.JTextField mname_text;
    private javax.swing.JRadioButton n_mem_radio;
    private javax.swing.JLabel no_of_rooms_bk;
    private javax.swing.JTextArea non_M_Address;
    private javax.swing.JTextField non_M_ContactNo;
    private javax.swing.JPanel non_mem_detail_table;
    private javax.swing.JTextField non_member_name;
    private javax.swing.JLabel noofdays_label;
    private javax.swing.JLabel noofrooms_label;
    private javax.swing.JLabel noofrooms_label1;
    private javax.swing.JLabel payment_date_label;
    private javax.swing.JLabel pending_label;
    private javax.swing.JPanel request_panel;
    private javax.swing.JLabel role_label;
    private javax.swing.JComboBox roomTyppe_combo;
    private javax.swing.JComboBox roomTyppe_combo1;
    private javax.swing.JTextField room_booking_no;
    private javax.swing.JLabel room_id_label;
    private javax.swing.JLabel roomtype;
    private javax.swing.JLabel roomtype_label;
    private javax.swing.JLabel roomtype_label1;
    private javax.swing.JPanel submit_panel;
    private javax.swing.JLabel tariff;
    private javax.swing.JTextField tariff1;
    private javax.swing.JTextField tariff2;
    private javax.swing.JPanel tariff_details;
    private javax.swing.JLabel tax1_Id_label;
    private javax.swing.JLabel tax1_rate;
    private javax.swing.JTextField tax1_rate_label;
    private javax.swing.JTextField tax1_rate_label1;
    private javax.swing.JTextField tax2;
    private javax.swing.JLabel tax2_Id_label;
    private javax.swing.JLabel tax2_rate;
    private javax.swing.JTextField tax2_rate_label;
    private javax.swing.JTextField tax2_rate_label1;
    private javax.swing.JTextField tax3;
    private javax.swing.JLabel tax3_Id_label;
    private javax.swing.JLabel tax3_rate;
    private javax.swing.JTextField tax3_rate_label;
    private javax.swing.JTextField tax3_rate_label1;
    private javax.swing.JLabel tax_1;
    private javax.swing.JLabel tax_2;
    private javax.swing.JLabel tax_3;
    private javax.swing.JLabel tax_basic_label1;
    private javax.swing.JLabel tax_basic_label2;
    private javax.swing.JLabel tax_basic_label3;
    private javax.swing.JLabel tax_basic_label4;
    private javax.swing.JTextField temp_T;
    private javax.swing.JLabel total_charges_label;
    private javax.swing.JTextField total_label;
    private javax.swing.JTextField total_label1;
    private javax.swing.JButton view_details;
    // End of variables declaration//GEN-END:variables

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
       memberSelect();
       reset();
       
       RoomTableModel = (GuestRoomTableModel) app.getBean("com.openbravo.pos.Booking.GuestRoomTableModel");
       dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
       Payment_Status = (BookedRoomStatusTableModel) app.getBean("com.openbravo.pos.Booking.BookedRoomStatusTableModel");
       dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       CheckInTable_Model = (CheckInTableModel) app.getBean("com.openbravo.pos.Booking.CheckInTableModel");
       
    }

    public Object getBean() {
         return this;
    }
    
    public void reset(){
       main_panel.setVisible(true);
       Check_IN.setText(null);
       Tot_room_avail.setText(null);
       Booked_no_of_rooms_text.setText(null);
       jTabbedPane1.setSelectedIndex(0);
       image_panel.setVisible(false);
       roomTyppe_combo.setSelectedIndex(-1);
       submit_panel.setVisible(false);
       jInternalFrame1.setVisible(false);
       bookedNo_of_days.setText(null);
       availibility_panel.setVisible(false);
       memno_text.setText(null);
       mname_text.setText(null);
       non_member_name.setText(null);
       non_M_ContactNo.setText(null);
       non_M_Address.setText(null);
       check_in_label.setVisible(false);
       Check_out_label.setVisible(false);
       Check_OUT.setText(null);
       message.setVisible(false);
       make_pay_btn.setEnabled(false);
       request_panel.setVisible(false);
       cardno_text.setText(null);
       tax1_Id_label.setText(null);
       tax2_Id_label.setText(null);
       tax3_Id_label.setText(null);
       tariff_details.setVisible(false);
       image_panel.setVisible(false);
       submit_panel.setVisible(false);
       jInternalFrame1.setVisible(false);
       availibility_panel.setVisible(false);
       request_panel.setVisible(false);
       BookingDateChange_Panel.setVisible(false);           
    }
    
    int CardAccessFlag=0;
    
    public void loaddata() throws BasicException{
        
        roomType_list = new ArrayList<Object>();
        roomType_list = RoomTableModel.RoomType_NamesList_Active(m_App);
        RoomTypeListModel = new ComboBoxValModel(roomType_list);
        RoomTypeListModel_avail = new ComboBoxValModel(roomType_list);
        roomTyppe_combo.setModel(RoomTypeListModel);
        
        roomTyppe_combo1.setModel(RoomTypeListModel_avail);
        try {
            Payment_Option();  // if payment not done in time.... request will be cancelled..!!
        } catch (ParseException ex) {
            Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        Booked_room_status = BookedRoomStatusTableModel.loadInstanceBooked_Room_Status(m_App);
        showPanelInfo(Booked_room_status);
        
        int pending_list = Booked_room_status.getPending_ReqList(m_App);
         if(pending_list>0){
              pending_label.setVisible(true);
               pending_label.setText("( * "+pending_list+" Request is pending. )");
         }
         else{
              pending_label.setVisible(false);
         }
        
        Rejected_Room_Status = BookedRoomStatusTableModel.loadInstance_Cancel_rooms(m_App);
        show_Rejected_rooms(Rejected_Room_Status);
        
        
        Delete_From_Guest_Availibility();
        
        
        
        
        
// CODE ADDED FOR CATD ACCESSSSS  
         
                    Object[] obj15 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Guest Room Card Access Flag'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                    if(obj15!=null){
                         Boolean v15 = (Boolean)obj15[0];
                         if(v15){
                            memno_text.setEditable(false);
                            jButton16.setVisible(false);
                         }
                         else{
                             memno_text.setEditable(true);
                             jButton16.setVisible(true);
                         }
                         
                        
                     }
                     else{
                           memno_text.setEditable(false);
                           jButton16.setVisible(false);
                     }        
        
        
        
          Object[] obj17 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Card Access Type GuestRooms and PartyHall'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                     if(obj17!=null){
                            String  v17 = (String)obj17[0];
                            if(v17.equals("1")){
                                CardAccessFlag=1;
                            }
                            else if(v17.equals("2")){
                                CardAccessFlag=2;
                            }
                            else{
                               CardAccessFlag=3;
                            }
                         
                     }
                     else{
                         CardAccessFlag=1;
                     }
                     
                    
                    
                    
                    
                    
        
        
        
        
    }
   private void memberSelect() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(mem_radio);
        bg1.add(n_mem_radio);
   }
   
   
   public void showPanelInfo(BookedRoomStatusTableModel Booked_room_status){
       
        jTable1.setModel(Booked_room_status.getTableModel());
         
    }
    public void show_Rejected_rooms(BookedRoomStatusTableModel Rejected_Room_Status){
       
        jTable2.setModel(Rejected_Room_Status.getTableModel2());
         
    }
   Date BOOKING_DATE_EX_T ;
   public void load_availibility(Date BOOKINGDATE_EX , String ROOMTYPE_ID , int NO_OF_ROOMS_Booked ,int TOTAL_rooms_avail , int no_of_days) throws BasicException{
       
      BOOKING_DATE_EX = BOOKINGDATE_EX;
      Room_TYPE_ID = ROOMTYPE_ID;
      NO_OF_ROOMS_BOOKED = NO_OF_ROOMS_Booked;
      TOTAL_ROOMS_AVAIL = TOTAL_rooms_avail;
      NO_OF_DAYS = no_of_days;
      int  getBooked_rooms;
      
      String temp = Formats.DATE.formatValue(BOOKING_DATE_EX);
       BOOKING_DATE_EX_T = (Date) Formats.TIMESTAMP.parseValue(temp);
       
       
       for(int i=1;i<=NO_OF_DAYS;i++)
       {
           
           
           
           Calendar c = Calendar.getInstance(); 
           c.setTime(BOOKING_DATE_EX_T);
           c.add(Calendar.DATE, i-1);
           BOOKING_DATE_EX_T = c.getTime();
           
           getBooked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_TYPE_ID , BOOKING_DATE_EX_T);
           if(getBooked_rooms!=-1){
                int update_Availibilty =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=? WHERE ROOM_TYPE =? AND BOOKED_DATES=?"
                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.STRING , Datas.TIMESTAMP  })).exec
                                                       (new Object[]{(NO_OF_ROOMS_BOOKED+getBooked_rooms) ,Room_TYPE_ID , BOOKING_DATE_EX_T  });
              
           }
           else{
                Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                               @Override      
                               protected Object transact() throws BasicException {   



                              int   insert_Availibility =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_availibility (ID , ROOM_TYPE , TOTAL_ROOMS , BOOKED_ROOMS , BOOKED_DATES ) VALUES (?,?,?,?,?)"                           
                               , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING  , Datas.INT ,Datas.INT ,Datas.TIMESTAMP })                         
                               ).exec(new Object[]{UUID.randomUUID().toString() , Room_TYPE_ID , TOTAL_ROOMS_AVAIL ,NO_OF_ROOMS_BOOKED,  BOOKING_DATE_EX_T });                                                                                                


                                 return null;                                      
                                   }                            
                               };                 

                               try {                 
                                   t.execute();          

                               }
                            catch (BasicException ex) {   
                                 ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                       Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                               }

                        
                        
       }
       }
       
       
   }
   
   public void show_VacantRooms(int vacant_rooms , String room_temp , int Booked_Rooms){
       
        jScrollPane5.getViewport().setView(null);
              JFlowPanel jPeople = new JFlowPanel();
              jPeople.applyComponentOrientation(getComponentOrientation()); 
              
               for(int i=1 ; i<= vacant_rooms ; i++){ 
                    JButton btn = new JButton(room_temp);
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(100, 50));
                    btn.setPreferredSize(new Dimension(100, 50));
                    btn.setMinimumSize(new Dimension(100, 50));
                    btn.setBackground(Color.GREEN);
                    btn.setBorderPainted(true);
                    
                    jPeople.add(btn);
                    
                }
               
               
               for(int i=1 ; i<= Booked_Rooms ; i++){ 
                    JButton btn = new JButton(room_temp);
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(100, 50));
                    btn.setPreferredSize(new Dimension(100, 50));
                    btn.setMinimumSize(new Dimension(100, 50));
                    btn.setBackground(Color.RED);
                    btn.setBorderPainted(true);
                    
                    jPeople.add(btn);
                    
                }
               
               
               
               
              
            
              jScrollPane5.getViewport().setView(jPeople);
       
       
       
   }
   public void showAvailibility(int total_rooms){
          int booked_rooms = 0;
            Date d = new Date();
            int vacant_rooms;
            
            String Room_type = roomTyppe_combo1.getSelectedItem().toString();
            String Room_id = Booked_room_status.getRoom_id(m_App, Room_type);
                try {
                    d = (Date) Formats.DATE.parseValue(jTextField1.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            booked_rooms = Booked_room_status.getRoom_Booked(m_App ,Room_id , d);
            if(booked_rooms==-1){
                booked_rooms = 0;
            }    
            vacant_rooms = total_rooms - booked_rooms;
            temp_T.setText(""+vacant_rooms);
            show_VacantRooms(vacant_rooms , Room_type , booked_rooms);
            
       
       
   }
   
   
   public void Delete_From_Guest_Availibility() throws BasicException{
       
        Object num1 =  new PreparedSentence(m_App.getSession(), "DELETE FROM guestroom_availibility WHERE BOOKED_ROOMS=0",   SerializerWriteString.INSTANCE,  SerializerReadInteger.INSTANCE).exec();

      }
   
   
   
  
      public void Payment_Option() throws BasicException, ParseException, ParseException{
          
           Date date=new Date();
           Calendar c = Calendar.getInstance();
           c.setTimeInMillis(date.getTime());
           c.add(Calendar.DATE, -1);
           date = c.getTime();
           
            List<Object> Payment_list = new ArrayList<Object>();
            
            Payment_list = Payment_Status.Payment_notRecieve(m_App ,date);
            
            int list_size = Payment_list.size();
            if(list_size>0){
                
                for(int i=0;i<list_size ; i++){
                    Date Booked_Date = new Date();
                    int Booked_Rooms = 0;
                    int No_Of_days =0;
                    String Room_Type_ID;
                    
                    String Booking_id = Payment_list.get(i).toString();
                    
                    // Payment Flag : (0: Payment Not Recieved[0] . 1:- Payment Recieved . 2:- payment not pdone in time....)
                    
                     int Update_Status  =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET STATUS=1 , PAYMENT_FLAG=2  WHERE ID =  ?"
                                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec
                                                       (new Object[]{  Booking_id  });
                    try {
                        Booked_Date =  Payment_Status.getBooking_date(m_App, Booking_id);
                    } catch (ParseException ex) {
                        Logger.getLogger(BookGuestRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Booked_Rooms =  Payment_Status.get_Rooms_Booked(m_App, Booking_id);
                    No_Of_days =  Payment_Status.get_No_Of_Days(m_App, Booking_id);
                    Room_Type_ID = Payment_Status.getRom_Type_ID(m_App, Booking_id);
                    
                   Update_availibility_for_Payment( Booked_Date , Booked_Rooms , No_Of_days , Room_Type_ID);
                    
                }
                
            }
          
          
      }
   
     public void Update_availibility_for_Payment(Date Booked_Date , int Booked_Rooms , int No_Of_days , String Room_Type_ID) throws BasicException{
         
         for(int i=0;i<No_Of_days;i++){
                  Date Todate = Booked_Date;
                  Calendar c = Calendar.getInstance();
                  c.setTimeInMillis(Todate.getTime());
                  c.add(Calendar.DATE, i);
                  Todate = c.getTime();  
                  
                  
                  int Update_Status  =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=(BOOKED_ROOMS-?)  WHERE BOOKED_DATES=? AND ROOM_TYPE=?  "
                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP ,Datas.STRING  })).exec
                                                       (new Object[]{  Booked_Rooms , Todate ,  Room_Type_ID });
              
                  
             
         }
            
     }
     
     
      
     
     
     
     //PRINT REFUND RECIEPT
     public void PrintRefundReciept(String M_Name,String M_No,String G_Name,String RmType,int rooms,int days,String BK_date, Double Cn_chrg,Double Tot_Amt, Double Advnce_recv ,Double Rfnd_amt){
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
         

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            
            script.put("maintitle", "Booking Cancellation Slip");
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", M_Name);
            script.put("cno", M_No);
            script.put("date", Formats.TIMESTAMP.formatValue(new Date())); 
            
            
           script.put("Reciept_title", "Refund Voucher Id : ");
           script.put("receipt", Rfd_Voucher_No);
           
            
            
            script.put("label_1", "Room Type :");
            script.put("text1", RmType);
            
            script.put("label_2", "Booking Date :");
            script.put("text2",BK_date);
            
            script.put("label_3", "Total Amount : ");
            script.put("text3", decimalFormat.format(TotalAmt));
            
          
            script.put("label_4", "Cancellation Charge : ");
            script.put("text4", (decimalFormat.format((Cancel_Chrg_Perc*TotalAmt)/100)) + "  ("+  Cancel_Chrg_Perc +" %)" );
            
            script.put("label_5", "Fixed Cancel Chrg:");
            script.put("text5", Fix_Charge);
            
            script.put("Gname", Guest_N);
            
            script.put("label_6", "Advance Paid : ");
            script.put("text6", decimalFormat.format(Advance_Recv));
            
            
            script.put("label_7", "Total Cancellation Charge :");
            script.put("text7", decimalFormat.format(Cancel_Charge)); 
            
            script.put("label_8", "Refund Amount : ");
            script.put("text8", decimalFormat.format(Rfnd_amt));
            
            
            
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
    }
     
     
     
      // METHOD FOR PRINTING BLOCKING DETAILS :...
    public void PrintBlockingDetails(){
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            
            script.put("maintitle", "Hall Blocking Details");
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", mName.getText());
            script.put("cno", memberNo.getText());
            script.put("date", Formats.TIMESTAMP.formatValue(new Date())); 
            script.put("Gname", NName.getText());
            
            script.put("receipt", "          ");
           
            
            script.put("label_1", "Check In Date :");
            script.put("text1", roomtype.getText());
            
            script.put("label_2", "Booking Date :");
            script.put("text2",bookingDate.getText());
            
            script.put("label_3", "No of Rooms Booked : ");
            script.put("text3", no_of_rooms_bk.getText()); 
          
            
            
            script.put("label_4", "No of Booking Days :");
            script.put("text4", booked_no_of_days.getText());
           
            
            
            script.put("label_5", "Tariff : ");
            script.put("text5", tariff.getText()+"/-");
            
          
           
            
            script.put("label_6", "Tariff :  ");
            script.put("text6", tariff.getText());
            
            
            script.put("label_7", "Advance Paid : ");
            script.put("text7", "0.00");
            
            script.put("label_8", "Refund Amount : ");
            script.put("text8", Refund_Amt);
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (Exception e) {
            }
        }   
     
    
     private String getNextGuestRoom_Sequence() throws BasicException{
       //akash:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname="GUESTROOM_ID";
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
         JOptionPane.showMessageDialog(null, "Please Specify series for Guest Rooms in general Table..!! ", "Cannot Create Refund", JOptionPane.WARNING_MESSAGE);
         return "";
     }
       
     }
     
     
     
       public void Update_GuestRoomSeq() throws BasicException{ 
         
        String billnum;
        String uname="GUESTROOM_ID";
    
           int x = new StaticSentence(m_App.getSession()
                       // , "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME =(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) "
                  , "UPDATE SEQUENCEDETAIL SET RMAX=RMAX+1 WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=?"

                  , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING }))
                        .exec(new Object[] {uname,uname});
         
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
       
       
     
     // REFUND VOUCHER FOR CANCELLED HALL/ROOMS
     
       public void generateRefundVoucher()
        {
             //List<SmsConnection.DelRepBean> list = new ArrayList<SmsConnection.DelRepBean>();
            Map reportparams = new HashMap();
            List<Object> list = new ArrayList<Object>();
              //list= new SmsConnection(m_app).getDelperlist();
             DataSourceProvider data1 = new DataSourceProvider(list);
            // DataSourceForSMSDelReport dsfc = new DataSourceForSMSDelReport(list);
            // data1.setDataSource(dsfc);
             
             String Message=null;
             
             Message = "Dear Member, \n        Refund of Rs. "+Refund_Amt +" /-  after cancellation of Romm. \n (Advance Recieved : "+Advance_Recv+"/- , Cancellation Charge :" +Cancel_Charge +"/- ).  \n (Booking ID : "+Booking_Seq_No+ ") Booked By "+MEMBER_NAME+ ". "  ;
             reportparams.put("V_NO",Rfd_Voucher_No );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             reportparams.put("MEM_NAME", MEMBER_NAME);
             reportparams.put("M_NO", MEMBER_NO);
             reportparams.put("MAIN_TEXT", Message);
             reportparams.put("CREATEBY",  m_App.getAppUserView().getUser().getName());
             reportparams.put("CRHOST" , m_App.getProperties().getHost());
             reportparams.put("MEMSIGN", MEMBER_NAME);
            
             
             reportparams.put("HALLNAME", RoomType_N);
             reportparams.put("bookingseqno", Booking_Seq_No);
             reportparams.put("bookingdateinfo", (book_date+" . ( No of Days :- "+no_of_days_bkd+"  , No of Rooms :"+Rooms_booked+" Rooms. )"));
             reportparams.put("advanceamt", decimalFormat.format(Advance_Recv));
             reportparams.put("totalbillamount", decimalFormat.format(TotalAmt));
            
             reportparams.put("cancellationperc", (decimalFormat.format(Cancel_Chrg_Perc))+"%");
             reportparams.put("fixedCharge", decimalFormat.format(Fix_Charge));
             reportparams.put("cancellationCharge", decimalFormat.format(Cancel_Charge));
             reportparams.put("refundamt", decimalFormat.format(Refund_Amt));
            
             
            JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/Refund_voucher.jrxml", reportparams, false, data1, true, null); 

            
        }
     
       
       // UPDATE REFUND VOUCHER.... #AAKASH
       public void UpdateRefundVoucher() throws BasicException{ 
         
        String billnum;
        String uname="REFUND VOUCHER";
    
           int x = new StaticSentence(m_App.getSession()
                       // , "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME =(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) "
                  , "UPDATE SEQUENCEDETAIL SET RMAX=RMAX+1 WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=?"

                  , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING }))
                        .exec(new Object[] {uname,uname});
         
        }    
      //Cancle Checkout date Extend : pratima 
       public void cancleCheckoutDateExtend(){
         
           if(showdataChanged.getId().equals(Old_Id)){
                   Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                          @Override      
                                    protected Object transact() throws BasicException {   
                                   
           int UpdateoldBooking = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_booked_details  SET CDATECHANGEFLAG=?,BOOKING_DAYS=? WHERE  ID=?  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.INT , Datas.STRING  })).exec
                                                                                   (new Object[]{ 0 ,Old_NO_OF_DAYS, showdataChanged.getId() });
                                      
                                                                                       
                                       
                                         
                    /////////////////////////////////////
                                        //code to load availability after request of  extending days is cancled ie. payment is not done
                                        
                                         NO_OF_DAYS =(int)((CHECKOUT_DATE_EX.getTime() - Old_Check_out_DATE.getTime()) / 1000 / 60 / 60 / 24);
                                         no_of_rooms_booked_by_mem = showdata.getNO_OF_ROOMS_BOOKED();
                                    for(int i=1;i<=NO_OF_DAYS ; i++ ){
                                        Calendar c = Calendar.getInstance(); 
                                        c.setTime(Old_Check_out_DATE);
                                        c.add(Calendar.DATE, i-1);
                                        Old_Check_out_DATE=c.getTime();
                                        getBooked_rooms = Booked_room_status.getRoom_Booked(m_App ,roomType_ID , Old_Check_out_DATE);

                                    if(getBooked_rooms >= no_of_rooms_booked_by_mem){ 
                                        
                                       int update_room_availibility = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=?  WHERE BOOKED_DATES=? AND ROOM_TYPE=?"
                                                                      , new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.TIMESTAMP , Datas.STRING  })).exec
                                                                       (new Object[]{ (getBooked_rooms - no_of_rooms_booked_by_mem ) , Old_Check_out_DATE , roomType_ID  });
                                    }
                                        }
                                       /////////////////////////////////////
                                       if(showdataChanged.getPAYMENT_FLAG()==1){
                                           BillModel.setRsi(showdata);
                                           Final_Amount= BillModel.getTotAmt_Room(showdata);
                                           showdata.setNO_OF_DAYS(Old_NO_OF_DAYS);
                                           int UpdateAdvanceDetails = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_advance_payment  SET TOTAL_AMOUNT=?  WHERE  BOOKING_SEQ_NO=?  "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE  , Datas.STRING  })).exec
                                                                                (new Object[]{Old_Final_Amount, showdataChanged.getBOOKING_SEQ_NO() });
                                            int UpdateoldCheckout  = new PreparedSentence(m_App.getSession(), "UPDATE guestroom_checkin  SET DAYS=?,E_CHK_OUT=?,TOT_AMT=?,ADV_RECV=?   WHERE  advnce_recv_id in(SELECT ID from guestroom_advance_payment WHERE BOOKING_ID=? ) "
                                                                                  , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.TIMESTAMP ,Datas.DOUBLE,Datas.DOUBLE, Datas.STRING  })).exec
                                                                                   (new Object[]{NO_OF_DAYS,Old_Check_out_DATE,Old_Final_Amount,Old_ADVANCE_RECV, showdataChanged.getId() });
                                       }
                                           return null;                                      
                                        }
                                    
                                    };                 

                                    try {                 
                                        t.execute();    }

                                      catch (BasicException ex) {                    
                                            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                        ex.printStackTrace();
                                                       new MessageInf(ex).show(new JFrame());

                                            }   
                                    jTable1.setModel(Booked_room_status.getTableModel());
       }
       }
       
       public BookedRoomStatusTableModel.Room_StatusInfo getRsi(){
       int row = jTable1.getSelectedRow();
        BookedRoomStatusTableModel.Room_StatusInfo showdata = Booked_room_status.getGuestRoomList().get(row);
   return showdata;
   }
   
       public void setExtendDateFlag(){
   extendDateFlag=1;
   }
  
       public int getExtendDateFlag(){
  return extendDateFlag;
   }
   
    
}
