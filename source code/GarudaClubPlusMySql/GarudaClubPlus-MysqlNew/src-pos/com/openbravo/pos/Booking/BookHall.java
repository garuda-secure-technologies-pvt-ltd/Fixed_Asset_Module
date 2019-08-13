
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.beans.JFlowPanel;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.BookHallTableModel.HallAvailibilityInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.Booking.BookedHallStatusTableModel.HallStatusInfo;
import com.openbravo.pos.Booking.BookedHallStatusTableModel.Cancel_requestInfo;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServlet;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JasperPrint;

public class BookHall extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private hallTableModel Hall;
    private BookHallTableModel hallBooking;
    private BookedHallStatusTableModel BookHall_Status ; 
    private BookedHallStatusTableModel Payment_Status;
    private BookedHallStatusTableModel Canceled_request;
    private CheckInTableModel CheckInTable_Model;
    
    private ComboBoxValModel HallListModel ;
    private List<hallTableModel.HallTableInfo> halllist ;
    private List<BookHallTableModel.HallAvailibilityInfo> hallDetails;
    private List<String> M_hourly_Slots = new ArrayList<String>();
    private List<String> M_halfDay_Slots = new ArrayList<String>();
    private List<String> M_fullDay_Slots = new ArrayList<String>();
   
    private List<String> Avail_hall_List_for_book = new ArrayList<String>();
    private   Color myOrange = new Color(255,165,0);
  
    
    
     private ComboBoxValModel  M_hourly_Slots_C;
     private ComboBoxValModel  M_halfDay_Slots_C;
     private ComboBoxValModel  M_fullDay_Slots_C;
     private CustomerInfoExt customer;
     private ComboBoxValModel  Available_hall_list_model;
    
     private DataLogicFacilities dmang;
     private CustomerInfo customerInfo;
     private DataLogicCustomers dlCustomers;
     private TicketParser m_TTP;
     private DataLogicSales m_dlSales = null;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     private Double Total_Charge = 0.00;
     
     
     
    public BookHall() {
        initComponents();
       
        main_panel.setVisible(true);
        jTabbedPane1.setSelectedIndex(0);
       
      
        image_panel.setVisible(false);
        layout_panel.setVisible(false);
        //member_panel.setVisible(false);
       // non_member_panel.setVisible(false);
        tariff_panel.setVisible(false);
        timingFrame.setVisible(false);
        // non member combo box
           
           Avail_Date_frame.setVisible(false);
           membersDetails_frame.setVisible(false); 
            
        //member combo box 
            jLabel16.setVisible(false);
           hourly_combo.setVisible(false);
           jLabel17.setVisible(false);
           halfDay_combo.setVisible(false);
           jLabel18.setVisible(false);
           fullDay_combo.setVisible(false);
           
         
           hallDetails_panel.setVisible(false);
           Avail_Hall_frame.setVisible(false);
           jButton1.setEnabled(false);
            message.setVisible(false);
            jButton33.setEnabled(false);
            message1.setVisible(false);
            cancel_panel.setVisible(false);
            request_cln_btn.setVisible(false);
            jButton35.setEnabled(false);
              
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
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        pending_label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hallCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        max_Capacity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        floor = new javax.swing.JTextField();
        ID = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        book_hall_btn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        date_textfield = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        member = new javax.swing.JRadioButton();
        non_member = new javax.swing.JRadioButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        slot_TF = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        advanceBookingDura = new javax.swing.JTextField();
        hall_ID = new javax.swing.JLabel();
        block_flag_label = new javax.swing.JLabel();
        block_from_text = new javax.swing.JTextField();
        block_upto_text = new javax.swing.JTextField();
        tax1_ID_label = new javax.swing.JLabel();
        tax2_id_label = new javax.swing.JLabel();
        tax3_id_label = new javax.swing.JLabel();
        layout_panel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        iconLabel = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        btnIcon = new javax.swing.JButton();
        tariff_panel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        member_panel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        luxuryTax = new javax.swing.JTextField();
        tax2 = new javax.swing.JTextField();
        tax3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        hourly = new javax.swing.JTextField();
        halfDay = new javax.swing.JTextField();
        fullDay = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        temp_combo1 = new javax.swing.JComboBox();
        temp_combo2 = new javax.swing.JComboBox();
        temp_combo3 = new javax.swing.JComboBox();
        non_member_panel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        hourly1 = new javax.swing.JTextField();
        halfDay1 = new javax.swing.JTextField();
        fullDay1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        N_luxuryTax = new javax.swing.JTextField();
        N_tax2 = new javax.swing.JTextField();
        N_tax3 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        temp_combo4 = new javax.swing.JComboBox();
        temp_combo5 = new javax.swing.JComboBox();
        temp_combo6 = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        image_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        scroll1 = new javax.swing.JScrollPane();
        image_label1 = new javax.swing.JLabel();
        scroll2 = new javax.swing.JScrollPane();
        image_label2 = new javax.swing.JLabel();
        scroll3 = new javax.swing.JScrollPane();
        image_label3 = new javax.swing.JLabel();
        Avail_Date_frame = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        Avail_Hall_frame = new javax.swing.JPanel();
        hallListConbo2 = new javax.swing.JComboBox();
        jLabel70 = new javax.swing.JLabel();
        Month_year = new javax.swing.JLabel();
        Month_label = new javax.swing.JLabel();
        year_Label = new javax.swing.JLabel();
        uniqueNo = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jButton21 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        advanceBookedDura = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        hallDetails_panel = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        mName = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        memberNo = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        NName = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        contactNo = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Adress = new javax.swing.JTextArea();
        jLabel57 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        hallName = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        capacity = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        tariff = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        bookingDate = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        bookingSlot = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        tax_1 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        tax_2 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        tax_3 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        payment_label = new javax.swing.JLabel();
        slot_label = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        note_label = new javax.swing.JTextArea();
        booking_status_label = new javax.swing.JLabel();
        tax1_rate = new javax.swing.JLabel();
        tax2_rate = new javax.swing.JLabel();
        tax3_rate = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        total_charges_label = new javax.swing.JLabel();
        basic_tax_label1 = new javax.swing.JLabel();
        basic_tax_label2 = new javax.swing.JLabel();
        basic_tax_label3 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        Submit = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        message = new javax.swing.JLabel();
        message1 = new javax.swing.JLabel();
        request_cln_btn = new javax.swing.JButton();
        mem_label = new javax.swing.JLabel();
        print_btn = new javax.swing.JButton();
        cancel_panel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
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
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        timingFrame = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        member_timing_main_panel = new javax.swing.JPanel();
        hour_radio = new javax.swing.JRadioButton();
        halfDay_radio = new javax.swing.JRadioButton();
        fullDay_radio = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        hourly_combo = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        halfDay_combo = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        fullDay_combo = new javax.swing.JComboBox();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        membersDetails_frame = new javax.swing.JPanel();
        member_label = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        mname_text = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        memno_text = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        non_mem_details_panel = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        non_member_name = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        non_M_ContactNo = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        non_M_Address = new javax.swing.JTextArea();
        jLabel51 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        cardno_text = new javax.swing.JPasswordField();
        jTextField3 = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hall Name", "Floor", "Max Capacity", "Booking Status", "Booked  By", "Booked  Date", "Slot Booked", "Request Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("View Bookingl Details");
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/viewmag.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 51));
        jButton4.setText("Check Availibility  By Date");
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(0, 51, 51));
        jButton20.setText("Check Availibility by Hall Name");
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton33.setForeground(new java.awt.Color(102, 0, 51));
        jButton33.setText("Cancel Request");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 0, 0));
        jButton34.setText("View Cancel Requests");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton37.setForeground(new java.awt.Color(0, 0, 102));
        jButton37.setText("Make Payment");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        pending_label.setText("(* Pending Request)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addGap(90, 90, 90)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 139, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pending_label)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pending_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/request.png"))); // NOI18N
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash.png"))); // NOI18N
        pending_label.setForeground(Color.RED);

        jTabbedPane1.addTab("View Details", jPanel2);

        jLabel1.setText("Select Hall");

        hallCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        hallCombo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                hallComboComponentHidden(evt);
            }
        });
        hallCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hallComboItemStateChanged(evt);
            }
        });

        jLabel2.setText("Max. Capacity");

        jLabel3.setText("Floor ");

        ID.setText("ID");

        jButton2.setText("View Location In Layout");
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/location.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Images");
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/view2.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Status ");

        book_hall_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        book_hall_btn.setText("Block Hall");
        book_hall_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        book_hall_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_hall_btnActionPerformed(evt);
            }
        });

        jButton6.setText("Select Blocking  Date");
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setForeground(new java.awt.Color(255, 0, 51));
        jButton7.setText("Check Availibility");
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("View Tariiff & Tax details");
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        member.setText("Member");
        member.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memberItemStateChanged(evt);
            }
        });

        non_member.setText("Non Member");
        non_member.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                non_memberItemStateChanged(evt);
            }
        });

        jButton12.setText("Select  Slot");
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/appointment.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton13.setText("Enter Booking Details");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(204, 0, 51));
        cancel.setText("Cancel");
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        hall_ID.setText("HALL_ID");

        block_flag_label.setText("Block_Flag");

        tax1_ID_label.setText("jLabel30");

        tax2_id_label.setText("jLabel31");

        tax3_id_label.setText("jLabel36");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(max_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(member)
                        .addGap(18, 18, 18)
                        .addComponent(non_member))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(hallCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(book_hall_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(tax1_ID_label)
                                        .addGap(18, 18, 18)
                                        .addComponent(tax3_id_label))
                                    .addComponent(tax2_id_label)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(floor, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(advanceBookingDura, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(block_upto_text)
                                            .addComponent(block_from_text))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(slot_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(date_textfield)))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ID)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(block_flag_label)
                            .addComponent(hall_ID))))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hallCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(floor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(max_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slot_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(member)
                    .addComponent(non_member)
                    .addComponent(advanceBookingDura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ID)
                            .addComponent(hall_ID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(block_flag_label))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(block_from_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(block_upto_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tax1_ID_label)
                            .addComponent(tax3_id_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tax2_id_label)))
                .addGap(62, 62, 62)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(book_hall_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        max_Capacity.setEditable(false);
        floor.setEditable(false);
        ID.setVisible(false);
        status.setEditable(false);
        date_textfield.setEditable(false);
        slot_TF.setEditable(false);
        advanceBookingDura.setVisible(false);
        hall_ID.setVisible(false);
        block_flag_label.setVisible(false);
        block_from_text.setVisible(false);
        block_upto_text.setVisible(false);
        tax1_ID_label.setVisible(false);
        tax2_id_label.setVisible(false);
        tax3_id_label.setVisible(false);

        jTabbedPane1.addTab("Book Hall", jPanel3);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel15.setText("Hall Location ");

        jScrollPane2.setViewportView(iconLabel);

        close.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        close.setForeground(new java.awt.Color(204, 0, 51));
        close.setText("Close");
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout_panelLayout = new javax.swing.GroupLayout(layout_panel);
        layout_panel.setLayout(layout_panelLayout);
        layout_panelLayout.setHorizontalGroup(
            layout_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout_panelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout_panelLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(btnIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout_panelLayout.setVerticalGroup(
            layout_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/location.png"))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setText("Tariff Details ");

        member_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Tax 1");

        jLabel8.setText("Tax 2");

        jLabel9.setText("Tax 3");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Basic Tariff");

        jLabel4.setText("Rs. / Hour");

        jLabel5.setText("Rs. / Half Day");

        jLabel6.setText("Rs. / Full Day");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Taxes");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 0, 51));
        jLabel33.setText("Members");

        jLabel38.setText("Hourly ");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Timing Slots");

        jLabel39.setText("Half Day");

        jLabel41.setText("Full Day");

        temp_combo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        temp_combo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        temp_combo3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        javax.swing.GroupLayout member_panelLayout = new javax.swing.GroupLayout(member_panel);
        member_panel.setLayout(member_panelLayout);
        member_panelLayout.setHorizontalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(member_panelLayout.createSequentialGroup()
                                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(member_panelLayout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, member_panelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(halfDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(fullDay, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hourly)))
                            .addGroup(member_panelLayout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tax3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tax2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)))
                            .addGroup(member_panelLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel23)))
                        .addGap(46, 46, 46)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel39)
                            .addComponent(jLabel38))
                        .addGap(18, 18, 18)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40)
                            .addComponent(temp_combo1, 0, 182, Short.MAX_VALUE)
                            .addComponent(temp_combo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(temp_combo3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel33)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        member_panelLayout.setVerticalGroup(
            member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_panelLayout.createSequentialGroup()
                .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel33)
                        .addGap(8, 8, 8)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel23)
                            .addComponent(jLabel40))
                        .addGap(18, 18, 18)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38)
                                .addComponent(temp_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hourly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(tax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(halfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel39)
                                .addComponent(temp_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(fullDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(member_panelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(temp_combo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        luxuryTax.setEditable(false);
        tax2.setEditable(false);
        tax3.setEditable(false);
        jLabel23.setForeground(Color.blue);
        hourly.setEditable(false);
        halfDay.setEditable(false);
        fullDay.setEditable(false);
        jLabel28.setForeground(Color.blue);
        jLabel40.setForeground(Color.blue);

        non_member_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        non_member_panel.setPreferredSize(new java.awt.Dimension(1019, 211));

        jLabel19.setText("Rs. / Hour");

        jLabel20.setText("Rs. / Half Day");

        jLabel21.setText("Rs. / Full Day");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Basic Tariff");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Taxes");

        jLabel25.setText("Tax 1");

        jLabel26.setText("Tax 2");

        jLabel27.setText("Tax 3");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jLabel32.setForeground(new java.awt.Color(204, 0, 51));
        jLabel32.setText("Non- Members");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Timing Slots");

        jLabel43.setText("Hourly ");

        jLabel44.setText("Half Day");

        jLabel45.setText("Full Day");

        temp_combo4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        temp_combo5.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        temp_combo6.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout non_member_panelLayout = new javax.swing.GroupLayout(non_member_panel);
        non_member_panel.setLayout(non_member_panelLayout);
        non_member_panelLayout.setHorizontalGroup(
            non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(non_member_panelLayout.createSequentialGroup()
                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(non_member_panelLayout.createSequentialGroup()
                        .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(non_member_panelLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hourly1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(halfDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fullDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(non_member_panelLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel22)))
                        .addGap(38, 38, 38)
                        .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(N_tax3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(N_tax2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jLabel24)
                            .addComponent(N_luxuryTax))
                        .addGap(39, 39, 39)
                        .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel43)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42)
                            .addComponent(temp_combo4, 0, 188, Short.MAX_VALUE)
                            .addComponent(temp_combo5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(temp_combo6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(non_member_panelLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel32)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        non_member_panelLayout.setVerticalGroup(
            non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(non_member_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel22)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(hourly1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel43)
                    .addComponent(temp_combo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(N_luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(halfDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(N_tax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel44)
                    .addComponent(temp_combo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(non_member_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(fullDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(N_tax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(temp_combo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        hourly1.setEditable(false);
        halfDay1.setEditable(false);
        fullDay1.setEditable(false);
        jLabel22.setForeground(Color.blue);
        jLabel24.setForeground(Color.blue);
        N_luxuryTax.setEditable(false);
        N_tax2.setEditable(false);
        N_tax3.setEditable(false);
        jLabel42.setForeground(Color.blue);

        jButton9.setText("Close");
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/exit.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tariff_panelLayout = new javax.swing.GroupLayout(tariff_panel);
        tariff_panel.setLayout(tariff_panelLayout);
        tariff_panelLayout.setHorizontalGroup(
            tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_panelLayout.createSequentialGroup()
                .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tariff_panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(member_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(non_member_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)))
                    .addGroup(tariff_panelLayout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        tariff_panelLayout.setVerticalGroup(
            tariff_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tariff_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(non_member_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N

        image_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("Hall Images");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Image 1");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Image 2");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Image 3");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(153, 0, 0));
        jButton5.setText("Close");
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/exit.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        image_label1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        image_label1.setMaximumSize(new java.awt.Dimension(370, 222));
        image_label1.setMinimumSize(new java.awt.Dimension(370, 222));
        image_label1.setPreferredSize(new java.awt.Dimension(370, 222));
        scroll1.setViewportView(image_label1);
        scroll1.setSize(370,222);

        image_label2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        image_label2.setMaximumSize(new java.awt.Dimension(370, 222));
        image_label2.setMinimumSize(new java.awt.Dimension(370, 222));
        image_label2.setPreferredSize(new java.awt.Dimension(370, 222));
        scroll2.setViewportView(image_label2);
        scroll2.setSize(370,222);

        image_label3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        image_label3.setMaximumSize(new java.awt.Dimension(370, 222));
        image_label3.setMinimumSize(new java.awt.Dimension(370, 222));
        image_label3.setPreferredSize(new java.awt.Dimension(370, 222));
        scroll3.setViewportView(image_label3);
        scroll3.setSize(370,222);

        javax.swing.GroupLayout image_panelLayout = new javax.swing.GroupLayout(image_panel);
        image_panel.setLayout(image_panelLayout);
        image_panelLayout.setHorizontalGroup(
            image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, image_panelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, image_panelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        image_panelLayout.setVerticalGroup(
            image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_panelLayout.createSequentialGroup()
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, image_panelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(scroll3)))
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        Avail_Date_frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 153, 0));
        jButton19.setText("Block Now..!!");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel69.setText("Available Halls");

        jButton17.setText("Select Date For Booking");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 0, 51));
        jButton24.setText("Cancel");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel75.setText("Fully Booked");

        jLabel76.setText("Partially Booked");

        jLabel77.setText("Blocked");

        jLabel78.setText("Available");

        javax.swing.GroupLayout Avail_Date_frameLayout = new javax.swing.GroupLayout(Avail_Date_frame);
        Avail_Date_frame.setLayout(Avail_Date_frameLayout);
        Avail_Date_frameLayout.setHorizontalGroup(
            Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Avail_Date_frameLayout.createSequentialGroup()
                .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Avail_Date_frameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel69)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Avail_Date_frameLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Avail_Date_frameLayout.createSequentialGroup()
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(181, 181, 181)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Avail_Date_frameLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(52, 52, 52))
        );
        Avail_Date_frameLayout.setVerticalGroup(
            Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Avail_Date_frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Avail_Date_frameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(164, 164, 164))
                    .addGroup(Avail_Date_frameLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(Avail_Date_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))))
        );

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        jTextField1.setEditable(false);
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton29.setBackground(Color.RED);
        jButton30.setBackground(Color.YELLOW);
        jButton31.setBackground(myOrange);
        jButton32.setBackground(Color.GREEN);

        hallListConbo2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        hallListConbo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hallListConbo2ItemStateChanged(evt);
            }
        });

        jLabel70.setText("Select Hall ");

        Month_year.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Month_year.setText("MONTH");

        Month_label.setText("Month_label");

        year_Label.setText("Year_label");

        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton21.setText("Block Hall ");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 0, 102));
        jButton18.setText("Cancel");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel71.setText("Fully Booked");

        jLabel72.setText("Partially Booked");

        jLabel73.setText("Blocked");

        jLabel74.setText("Available");

        javax.swing.GroupLayout Avail_Hall_frameLayout = new javax.swing.GroupLayout(Avail_Hall_frame);
        Avail_Hall_frame.setLayout(Avail_Hall_frameLayout);
        Avail_Hall_frameLayout.setHorizontalGroup(
            Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(Month_year)
                        .addGap(301, 301, 301)
                        .addComponent(year_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                                    .addComponent(jLabel70)
                                    .addGap(33, 33, 33)
                                    .addComponent(hallListConbo2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(309, 309, 309)
                                    .addComponent(Month_label, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(advanceBookedDura, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uniqueNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        Avail_Hall_frameLayout.setVerticalGroup(
            Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(advanceBookedDura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Month_label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(year_Label)
                            .addComponent(Month_year)))
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hallListConbo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addComponent(uniqueNo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)
                                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(54, 54, 54)
                                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel71))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel72))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel73))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(Avail_Hall_frameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Avail_Hall_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Month_year.setForeground(Color.RED);
        Month_label.setVisible(false);
        year_Label.setVisible(false);
        uniqueNo.setVisible(false);
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        advanceBookedDura.setVisible(false);
        jButton25.setBackground(Color.RED);
        jButton26.setBackground(Color.YELLOW);
        jButton27.setBackground(myOrange);
        jTextField2.setEditable(false);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 51, 51));
        jLabel56.setText("Booking Detail");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Member Name  : ");

        mName.setText("N/A");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Member No. :");

        memberNo.setText("N/A");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Address : ");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("Non_Member's Name : ");

        NName.setText("N/A");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Contact No. : ");

        contactNo.setText("N/A");

        Adress.setColumns(20);
        Adress.setRows(5);
        jScrollPane8.setViewportView(Adress);
        Adress.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mName))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberNo)))
                .addGap(159, 159, 159)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactNo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NName))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(mName)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NName))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(memberNo)
                    .addComponent(jLabel55)
                    .addComponent(contactNo))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 51, 51));
        jLabel57.setText("Hall Details");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Name : ");

        hallName.setText("N/A");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Max. Capacity : ");

        capacity.setText("N/A");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Basic Tariff : ");

        tariff.setText("N/A");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Booking Date : ");

        bookingDate.setText("N/A");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Booking Slot : ");

        bookingSlot.setText("N/A");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Taxes : ");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("(1)");

        tax_1.setText("N/A");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("(2)");

        tax_2.setText("N/A");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("(3)");

        tax_3.setText("N/A");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("Last Payment Date :");

        payment_label.setText("N/A");

        slot_label.setText("(timings)");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Note :-");

        note_label.setColumns(20);
        note_label.setRows(5);
        jScrollPane7.setViewportView(note_label);
        note_label.setForeground(Color.RED);
        note_label.setEditable(false);

        booking_status_label.setText("jLabel30");

        tax1_rate.setText("tax1");

        tax2_rate.setText("tax1");

        tax3_rate.setText("tax1");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("Total : ");

        total_charges_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        total_charges_label.setText("total_charge");

        basic_tax_label1.setText("(B)");

        basic_tax_label2.setText("(B)");

        basic_tax_label3.setText("(B)");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(total_charges_label))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hallName))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capacity))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68)
                            .addComponent(jLabel66))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(tax_2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(basic_tax_label1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tax2_rate))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(tax_3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(basic_tax_label2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tax3_rate))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(tax_1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(basic_tax_label3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tax1_rate))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(141, 141, 141)
                        .addComponent(tariff)))
                .addGap(216, 216, 216)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel79)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(payment_label))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel63)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bookingSlot)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(slot_label))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bookingDate))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(booking_status_label, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hallName)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capacity)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingSlot)
                    .addComponent(slot_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tariff)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payment_label))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tax_1)
                                .addComponent(tax1_rate)
                                .addComponent(basic_tax_label3))
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tax_2)
                                .addComponent(basic_tax_label1))
                            .addComponent(tax2_rate)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(tax3_rate)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tax_3)
                        .addComponent(basic_tax_label2)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(booking_status_label)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_charges_label, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        booking_status_label.setVisible(false);

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 0, 51));
        jButton16.setText("Cancel");
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        Submit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Submit.setForeground(new java.awt.Color(0, 153, 0));
        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        message.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        message.setText("jLabel79");

        message1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        message1.setText("jLabel79");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        message.setForeground(Color.RED);
        message1.setForeground(Color.RED);

        request_cln_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        request_cln_btn.setForeground(new java.awt.Color(255, 0, 51));
        request_cln_btn.setText("Cancel");
        request_cln_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        request_cln_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                request_cln_btnActionPerformed(evt);
            }
        });

        mem_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mem_label.setText("( Booked For :- )");

        print_btn.setText("Print");
        print_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hallDetails_panelLayout = new javax.swing.GroupLayout(hallDetails_panel);
        hallDetails_panel.setLayout(hallDetails_panelLayout);
        hallDetails_panelLayout.setHorizontalGroup(
            hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hallDetails_panelLayout.createSequentialGroup()
                .addGroup(hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hallDetails_panelLayout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mem_label))
                    .addGroup(hallDetails_panelLayout.createSequentialGroup()
                        .addGroup(hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hallDetails_panelLayout.createSequentialGroup()
                                .addGap(326, 326, 326)
                                .addComponent(jLabel57))
                            .addGroup(hallDetails_panelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hallDetails_panelLayout.createSequentialGroup()
                                        .addComponent(print_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(request_cln_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        hallDetails_panelLayout.setVerticalGroup(
            hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hallDetails_panelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(mem_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hallDetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(request_cln_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_btn))
                .addContainerGap())
        );

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig.png"))); // NOI18N
        jPanel6.setSize(730, 130);
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/home.png"))); // NOI18N
        jPanel7.setSize(730, 220);
        jButton16.setVisible(false);
        Submit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton16.setVisible(false);
        mem_label.setForeground(Color.BLUE);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hall Name", "Floor", "Max Capacity", "Booking Status", "Booked By", "Booking Date", "Slot Booked", "Request Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable2);

        jButton35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 51, 51));
        jButton35.setText("View Details ");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jButton36.setForeground(new java.awt.Color(0, 153, 0));
        jButton36.setText("Go Back to Main Menu");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244)
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
            .addComponent(jScrollPane6)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/view2.png"))); // NOI18N
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/gohome.png"))); // NOI18N

        jTabbedPane2.addTab("Cancel Requests", jPanel9);

        javax.swing.GroupLayout cancel_panelLayout = new javax.swing.GroupLayout(cancel_panel);
        cancel_panel.setLayout(cancel_panelLayout);
        cancel_panelLayout.setHorizontalGroup(
            cancel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancel_panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cancel_panelLayout.setVerticalGroup(
            cancel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancel_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        timingFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 255));
        jLabel35.setText("Booking -Slot Selection");

        hour_radio.setText("Time slot");
        hour_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hour_radioItemStateChanged(evt);
            }
        });

        halfDay_radio.setText("Half Day");
        halfDay_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                halfDay_radioItemStateChanged(evt);
            }
        });

        fullDay_radio.setText("Full Day");
        fullDay_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fullDay_radioItemStateChanged(evt);
            }
        });

        jLabel16.setText("Time slot");

        hourly_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        hourly_combo.setPreferredSize(new java.awt.Dimension(170, 20));

        jLabel17.setText("Half Day Slot");

        halfDay_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        halfDay_combo.setPreferredSize(new java.awt.Dimension(170, 20));

        jLabel18.setText("Full Day Slot");

        fullDay_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        fullDay_combo.setPreferredSize(new java.awt.Dimension(170, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hourly_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(halfDay_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fullDay_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(hourly_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(halfDay_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(fullDay_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout member_timing_main_panelLayout = new javax.swing.GroupLayout(member_timing_main_panel);
        member_timing_main_panel.setLayout(member_timing_main_panelLayout);
        member_timing_main_panelLayout.setHorizontalGroup(
            member_timing_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_timing_main_panelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(member_timing_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(member_timing_main_panelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(hour_radio)
                        .addGap(27, 27, 27)
                        .addComponent(halfDay_radio)
                        .addGap(18, 18, 18)
                        .addComponent(fullDay_radio)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        member_timing_main_panelLayout.setVerticalGroup(
            member_timing_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(member_timing_main_panelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(member_timing_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hour_radio)
                    .addComponent(halfDay_radio)
                    .addComponent(fullDay_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton11.setText("Save");
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setText("Cancel");
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout timingFrameLayout = new javax.swing.GroupLayout(timingFrame);
        timingFrame.setLayout(timingFrameLayout);
        timingFrameLayout.setHorizontalGroup(
            timingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timingFrameLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timingFrameLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(timingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timingFrameLayout.createSequentialGroup()
                        .addComponent(member_timing_main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timingFrameLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        timingFrameLayout.setVerticalGroup(
            timingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timingFrameLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(member_timing_main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(timingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/time.png"))); // NOI18N

        membersDetails_frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        member_label.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 18)); // NOI18N
        member_label.setText("Booking Details");

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        mname_text.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                mname_textComponentAdded(evt);
            }
        });

        jLabel47.setText("Member Name :");

        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memno_textKeyPressed(evt);
            }
        });

        jLabel46.setText("Member No.");

        Save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Save.setForeground(new java.awt.Color(0, 153, 51));
        Save.setText("Save");
        Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 51, 51));
        jButton15.setText("Cancel");
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel49.setText("Guest Name :");

        non_member_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                non_member_nameActionPerformed(evt);
            }
        });

        jLabel50.setText("Contact No ");

        non_M_ContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                non_M_ContactNoKeyReleased(evt);
            }
        });

        jLabel52.setText("Address");

        non_M_Address.setColumns(20);
        non_M_Address.setRows(5);
        jScrollPane3.setViewportView(non_M_Address);

        jLabel51.setText("Referenced To :- ");

        javax.swing.GroupLayout non_mem_details_panelLayout = new javax.swing.GroupLayout(non_mem_details_panel);
        non_mem_details_panel.setLayout(non_mem_details_panelLayout);
        non_mem_details_panelLayout.setHorizontalGroup(
            non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(non_mem_details_panelLayout.createSequentialGroup()
                .addGroup(non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(non_mem_details_panelLayout.createSequentialGroup()
                        .addGroup(non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(non_mem_details_panelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel49))
                            .addGroup(non_mem_details_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel50))
                            .addGroup(non_mem_details_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel52)))
                        .addGap(42, 42, 42)
                        .addGroup(non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(non_member_name, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(non_M_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel51))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        non_mem_details_panelLayout.setVerticalGroup(
            non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(non_mem_details_panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(non_member_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(non_M_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(non_mem_details_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel51))
        );

        jLabel30.setText("Card No :");

        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        cardno_text.setText("jPasswordField1");
        cardno_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardno_textActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout membersDetails_frameLayout = new javax.swing.GroupLayout(membersDetails_frame);
        membersDetails_frame.setLayout(membersDetails_frameLayout);
        membersDetails_frameLayout.setHorizontalGroup(
            membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(membersDetails_frameLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membersDetails_frameLayout.createSequentialGroup()
                            .addComponent(non_mem_details_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(200, 200, 200))
                        .addGroup(membersDetails_frameLayout.createSequentialGroup()
                            .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(membersDetails_frameLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30))
                                    .addGap(18, 18, 18)
                                    .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(membersDetails_frameLayout.createSequentialGroup()
                                            .addGap(232, 232, 232)
                                            .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(membersDetails_frameLayout.createSequentialGroup()
                                            .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton14))
                                        .addGroup(membersDetails_frameLayout.createSequentialGroup()
                                            .addComponent(cardno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(2, 2, 2)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton38))))
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(108, 108, 108)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membersDetails_frameLayout.createSequentialGroup()
                        .addComponent(member_label, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185))))
        );
        membersDetails_frameLayout.setVerticalGroup(
            membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(membersDetails_frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(member_label)
                .addGap(18, 18, 18)
                .addComponent(non_mem_details_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(membersDetails_frameLayout.createSequentialGroup()
                        .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(membersDetails_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton14))
                .addGap(130, 130, 130))
        );

        member_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig32.png")));
        member_label.setForeground(Color.RED);
        mname_text.setEditable(false);
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png")));
        jTextField3.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Avail_Date_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(layout_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tariff_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cancel_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Avail_Hall_frame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(membersDetails_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(timingFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(hallDetails_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tariff_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layout_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timingFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(membersDetails_frame, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hallDetails_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Avail_Date_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Avail_Hall_frame, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    
         private  String M_HOURLY_SLOTS;
         private  String M_HALFDAY_SLOT;
         private  String M_FULLDAY_SLOT;
       
    
    private void hallComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hallComboItemStateChanged
       if(hallCombo.getSelectedIndex()!=-1){
           
           member_panel.setVisible(true);
           non_member_panel.setVisible(false);
           member.setSelected(true);
           non_member.setSelected(false);
           memno_text.setText(null);
           mname_text.setText(null);
           non_member_name.setText(null);
           non_M_ContactNo.setText(null);
           non_M_Address.setText(null);
           date_textfield.setText(null);
           slot_TF.setText(null);
           
           
           
           String hallname = hallCombo.getSelectedItem().toString();
           
           try{
               hallBooking = BookHallTableModel.loadInstanceHallInfo(m_App, hallname);
              
           } 
           catch (BasicException ex) {
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
          hallDetails =  (List<BookHallTableModel.HallAvailibilityInfo>) hallBooking.getHallPath();
           
          
        BookHallTableModel.HallAvailibilityInfo editData = (BookHallTableModel.HallAvailibilityInfo) ((HallAvailibilityInfo)hallDetails.get(0));
        
           int capacity = Integer.parseInt(editData.getMax_Cap()+"");
           max_Capacity.setText(""+capacity);
           floor.setText(editData.getFloor());
        
           hourly.setText(decimalFormat.format(editData.getMem_hourly_charge()));
           halfDay.setText(decimalFormat.format(editData.getMem_halfDay_charge()));
           fullDay.setText(decimalFormat.format(editData.getMem_fullDay_charge()));
        
             
            hourly1.setText(decimalFormat.format(editData.getNon_Mem_hourly_charge()));
            halfDay1.setText(decimalFormat.format(editData.getNon_Mem_halfDay_charge()));
            fullDay1.setText(decimalFormat.format(editData.getNon_Mem_fullDay_charge()));
          
            luxuryTax.setText(editData.getLuxuryTax());
            tax2.setText(editData.getTax_2());
            tax3.setText(editData.getTax_3());
            N_luxuryTax.setText(editData.getLuxuryTax());
            N_tax2.setText(editData.getTax_2());
            N_tax3.setText(editData.getTax_3());
            
            basicTax = editData.getBasic();
            basicTax2 = editData.getBasic2();
            
            
            if(N_luxuryTax.getText()!=null && N_luxuryTax.getText().trim().length()>0){
               tax1_rate.setText(""+editData.getTax1_Rate());
            }
            else{
                tax1_rate.setText("");
            }
            
            
            if(N_tax2.getText()!=null && N_tax2.getText().trim().length()>0){
                 tax2_rate.setText(""+editData.getTax2_Rate());
            }
            else{
                 tax2_rate.setText("");
            }
            
            
            if(N_tax3.getText()!=null && N_tax3.getText().trim().length()>0){
                 tax3_rate.setText(""+editData.getTax3_Rate());
            }
            else{
                tax3_rate.setText("");
            }
            
            
            
            advanceBookingDura.setText(""+editData.getAdvanceBookingDuration());
            
            tax1_ID_label.setText(editData.getTAX1_ID());
            tax2_id_label.setText(editData.getTAX2_ID());
            tax3_id_label.setText(editData.getTAX3_ID());
            
            
            basicTax = editData.getBasic();
            cascadeTax = editData.getCascade();
            
            basicTax2 = editData.getBasic2();
            cascadeTax2 = editData.getCascade2();
            
               Date Blocking_date = new Date();
               int Payment_days = editData.getPAYMENT_DAYS();
               Calendar c = Calendar.getInstance();
               c.setTimeInMillis(Blocking_date.getTime());
               c.add(Calendar.DATE, Payment_days);
               Date final_payment_date = c.getTime();
            
               Date Current_date = new Date();
               int No_of_days_left = (int) (final_payment_date.getTime() - Current_date.getTime())/(1000 * 60 * 60 * 24);
               String Final_date = Formats.DATE.formatValue(final_payment_date);
               payment_label.setText(Final_date);
            
        // Timings Slots combo box..
           
            if(M_hourly_Slots!=null || M_halfDay_Slots!=null || M_fullDay_Slots!=null ){
                M_hourly_Slots = new ArrayList<String>();
                M_halfDay_Slots = new ArrayList<String>();
                M_fullDay_Slots = new ArrayList<String>();
            }
            
         
            
            M_HOURLY_SLOTS = editData.getM_HOURLY_SLOTS();
            M_HALFDAY_SLOT = editData.getM_HALFDAY_SLOT();
            M_FULLDAY_SLOT = editData.getM_FULLDAY_SLOT();
         
           block_flag_label.setText(""+editData.getBLOCK_FLAG());
           Block_Flag = editData.getBLOCK_FLAG();
           
           if(editData.getBLOCK_FLAG()==1){
              String hall_name = hallCombo.getSelectedItem().toString();
                Date From_Date = Hall.getBlock_From_Date(hall_name, m_App);
                Date To_Date =Hall.getBlock_To_Date(hall_name, m_App);
               
               block_from_text.setText(Formats.DATE.formatValue(From_Date));
               block_upto_text.setText(Formats.DATE.formatValue(To_Date));
               
               
               try {
                   Block_From = (Date) Formats.DATE.parseValue(block_from_text.getText());
                   Block_Upto = (Date) Formats.DATE.parseValue(block_upto_text.getText());
               } catch (BasicException ex) {
                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
               }
              
           }
           else{
                 block_from_text.setText(null);
                 block_upto_text.setText(null);
           }
           
           hall_ID.setText(editData.getId());
            
            if(M_HOURLY_SLOTS!=null){
            String[] items1 = M_HOURLY_SLOTS.split(";");
            Collection<String> collection1 = new ArrayList<String>();
            for(int i=0;i<items1.length;i++){
                      collection1.add(items1[i]);
                  }
            M_hourly_Slots.addAll(collection1);
            M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots);
            hourly_combo.setModel(M_hourly_Slots_C);
            temp_combo1.setModel(M_hourly_Slots_C);
            temp_combo4.setModel(M_hourly_Slots_C);
            
            }
            
            
            if(M_HALFDAY_SLOT!=null){
            String[] items2 = M_HALFDAY_SLOT.split(";");
           Collection<String> collection2 = new ArrayList<String>();
           for(int i=0;i<items2.length;i++){
                      collection2.add(items2[i]);
                  }
           M_halfDay_Slots.addAll(collection2);
           M_halfDay_Slots_C = new ComboBoxValModel(M_halfDay_Slots);
           halfDay_combo.setModel(M_halfDay_Slots_C);
           temp_combo2.setModel(M_halfDay_Slots_C);
           temp_combo5.setModel(M_halfDay_Slots_C);
            }
            
            
           if(M_FULLDAY_SLOT!=null){
           String[] items3 = M_FULLDAY_SLOT.split(";");
           Collection<String> collection3 = new ArrayList<String>();
           for(int i=0;i<items3.length;i++){
                      collection3.add(items3[i]);
                  }
            M_fullDay_Slots.addAll(collection3);
            M_fullDay_Slots_C = new ComboBoxValModel(M_fullDay_Slots);
            fullDay_combo.setModel(M_fullDay_Slots_C);
            temp_combo3.setModel(M_fullDay_Slots_C);
            temp_combo6.setModel(M_fullDay_Slots_C);
            
           }
           
           
          
            
         
          
       // Image operatiion ... 
            
            
            BufferedImage img1 = editData.getIMAGE1();
            if(img1 != null){
            ImageIcon ic1 = new ImageIcon(img1);
            image_label1.setText(null);
            image_label1.setIcon(ic1);
            }
            else{
                image_label1.setIcon(null);
                image_label1.setText("Image Not Available..!!");
            }
            
            BufferedImage img2 = editData.getIMAGE2();
            if(img2 != null){
            ImageIcon ic2 = new ImageIcon(img2);
            image_label2.setText(null);
            image_label2.setIcon(ic2);
            }
            else{
                image_label2.setIcon(null);
                image_label2.setText("Image Not Available..!!");
            }
            
            BufferedImage img3 = editData.getIMAGE3();
            if(img3 != null){
            ImageIcon ic3 = new ImageIcon(img3);
            image_label3.setText(null);
            image_label3.setIcon(ic3);
            }
            else{
                image_label3.setIcon(null);
                image_label3.setText(" Image Not Available..!!");
            }
            
            
            BufferedImage floor = editData.getFloorImage();
           // if(floor !=null) {
                ImageIcon flr = new ImageIcon(floor);
              //  layout_label.setIcon(flr);
           // }
            
            int X = editData.getFloor_X();
            int Y = editData.getFloor_Y();
            
            BufferedImage icon = editData.getIcon();
            ImageIcon ic  = new ImageIcon();
            if(icon!=null){
               ic = new ImageIcon(icon);
              
            }
            showPosition(X,Y,ic,flr);
                
            
            
            
            
            
            
            
            
            
            
       }
    }//GEN-LAST:event_hallComboItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      if(hallCombo.getSelectedIndex()!=-1){
          image_panel.setVisible(true);
      
       main_panel.setVisible(false);
      }
      else{
          JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       image_panel.setVisible(false);
       main_panel.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void memberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberItemStateChanged
      //  member_label.setText("Member Details");
      
    }//GEN-LAST:event_memberItemStateChanged

    private void non_memberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_non_memberItemStateChanged
     
     // member_label.setText("Non member Details");
      
    }//GEN-LAST:event_non_memberItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      if(hallCombo.getSelectedIndex()!=-1)
      {
      layout_panel.setVisible(true);
      main_panel.setVisible(false);
      }
      else{
          JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
      }
           
    }//GEN-LAST:event_jButton2ActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
       layout_panel.setVisible(false);
      main_panel.setVisible(true);
    }//GEN-LAST:event_closeActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
   
     
    int Advance_book_dura = 3;
      if(advanceBookingDura.getText()!=null){
         Advance_book_dura = Integer.parseInt(advanceBookingDura.getText());
     }
        
     if(hallCombo.getSelectedIndex()!=-1)
     {
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
            date = (Date) Formats.DATE.parseValue(date_textfield.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            if(date.before(Curr_date))
            {
                JOptionPane.showMessageDialog(this, "Date Selected is not available... Please Select again..!!");
                date_textfield.setText(null);
                
             
            }
            else if(date.after(afterDate))
            {
               JOptionPane.showMessageDialog(this, "Booking is available upto "+Advance_book_dura+" Monts only..!!! ");
               date_textfield.setText(null);
            }
           
            else{
                 String temp = Formats.DATE.formatValue(date);
                 List<Object> tempList = new ArrayList<Object>();
                 tempList =  BookHall_Status.CheckAvailibility(temp , m_App);
                 String TempDATE =   Formats.DATE.formatValue(date);
                
                 List<Object> hourly_Booked_Slot_list = new ArrayList<Object>();
                 List<Object> half_day_Booked_slot = new ArrayList<Object>();
                 String hallname = hallCombo.getSelectedItem().toString();
                 
                 int slot_flag = BookHall_Status.getTiming_Slot_Flag(temp, hallname, m_App);
                 
                 
                 
                 if(slot_flag==1){
                   hourly_Booked_Slot_list =   BookHall_Status.getBooking_Timings(temp, hallname, m_App);
                   if(hourly_Booked_Slot_list.size() < M_hourly_Slots.size() ){
                      
                       
                            date_textfield.setText(Formats.DATE.formatValue(date));

                            status.setText("Available");

                            book_hall_btn.setBackground(Color.green);
                       
                       }
                  
                   else{
                        JOptionPane.showMessageDialog(this, "Hall is already booked..!!! ");
                         date_textfield.setText(null);
                   }
                   
                   
                 }
                 
                 
                else if(slot_flag==2){
                     half_day_Booked_slot =  BookHall_Status.getHalfDay_Booking_Timings(temp, hallname, m_App);
                       if(half_day_Booked_slot.size()< M_halfDay_Slots.size()){
                           
                           date_textfield.setText(Formats.DATE.formatValue(date));

                           status.setText("Available");

                           book_hall_btn.setBackground(Color.green);
                           
                       }
                       else{
                           
                            JOptionPane.showMessageDialog(this, "Hall is already booked..!!! ");
                            date_textfield.setText(null);
                           
                       }
                       
                   }
                
                
                
                 else{
                        int count = 0;
                        for(int i=0;i<tempList.size();i++){
                            String x = hallCombo.getSelectedItem().toString();
                            String y = tempList.get(i).toString();
                            if(x.equals(y)){
                                count++;
                               // JOptionPane.showMessageDialog(this, "Hall is already booked..!!! ");
                            }
                        }

                        if(count!=0){
                          JOptionPane.showMessageDialog(this, "Hall is already booked..!!! ");
                          date_textfield.setText(null);
                       }
                 
                
                        else{
                            
                            if(Block_Flag==1){
                                 int No_of_days  = (int) (Block_Upto.getTime() - Block_From.getTime())/(1000 * 60 * 60 * 24);
                                 No_of_days++;
                                 
                                 for(int i=0; i< No_of_days ; i++ ){
                                    Calendar c = Calendar.getInstance();
                                    c.setTimeInMillis(Block_From.getTime());
                                    c.add(Calendar.DATE, i);
                                    Date NextDay = c.getTime(); 
                                     
                                    if(date.equals(NextDay)){
                                         JOptionPane.showMessageDialog(this, "Hall is Blocked For Club Use.  Select Another Date  !!! ");
                                         date_textfield.setText(null);
                                         break; 
                                    }
                                    else{
                                        
                                    date_textfield.setText(Formats.DATE.formatValue(date));

                                    status.setText("Available");
                                    slot_TF.setText(null);
                                    book_hall_btn.setBackground(Color.green);
                                    }
                                 }
                               }
                            
                            
                            
                            else{
                                    date_textfield.setText(Formats.DATE.formatValue(date));

                                    status.setText("Available");
                                    slot_TF.setText(null);
                                    book_hall_btn.setBackground(Color.green);
                            }
                            
                            
                            
                            
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
         JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
         
     }
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void hour_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hour_radioItemStateChanged
       if(hour_radio.isSelected()){
           jLabel16.setVisible(true);
           hourly_combo.setVisible(true);
           jLabel17.setVisible(false);
           halfDay_combo.setVisible(false);
           jLabel18.setVisible(false);
           fullDay_combo.setVisible(false);        
                   
       }
       
    }//GEN-LAST:event_hour_radioItemStateChanged

    private void halfDay_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_halfDay_radioItemStateChanged
        jLabel16.setVisible(false);
           hourly_combo.setVisible(false);
           jLabel17.setVisible(true);
           halfDay_combo.setVisible(true);
           jLabel18.setVisible(false);
           fullDay_combo.setVisible(false); 
    }//GEN-LAST:event_halfDay_radioItemStateChanged

    private void fullDay_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fullDay_radioItemStateChanged
       jLabel16.setVisible(false);
           hourly_combo.setVisible(false);
           jLabel17.setVisible(false);
           halfDay_combo.setVisible(false);
           jLabel18.setVisible(true);
           fullDay_combo.setVisible(true); 
    }//GEN-LAST:event_fullDay_radioItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      if(hallCombo.getSelectedIndex()!=-1) 
      {
       tariff_panel.setVisible(true);
       main_panel.setVisible(false);
       non_member_panel.setVisible(true);
       member_panel.setVisible(true);
       
       
       
      }
      else{
          JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        tariff_panel.setVisible(false);
        main_panel.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
     if(hallCombo.getSelectedIndex()!=-1){
          Date Selected_Date = new Date();
          Date Current_Date = new Date();
         try {
             Selected_Date = (Date) Formats.DATE.parseValue(date_textfield.getText());
         } catch (BasicException ex) {
             Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
         
         if(date_textfield.getText()!=null && date_textfield.getText().trim().length()>0){
       
         
          
          
         String hallname = hallCombo.getSelectedItem().toString();
         String B_date = date_textfield.getText();
         int slot_flag =  BookHall_Status.getTiming_Slot_Flag(B_date, hallname, m_App);
         List<Object> Hourly_Booking_Slot_List = new ArrayList<Object>();
         List<Object> HalfDay_Booking_Slot_List = new ArrayList<Object>();
        
         if(slot_flag==1 ){
             Hourly_Booking_Slot_List = BookHall_Status.getBooking_Timings(B_date, hallname, m_App);
             HalfDay_Booking_Slot_List = BookHall_Status.getHalfDay_Booking_Timings(B_date, hallname, m_App);
             
             
             List<String> M_hourly_Slots_temp = new ArrayList<String>();
             List<String> M_HalfDay_Slot_temp = new ArrayList<String>();
           
             Collection  c1 = M_hourly_Slots;
             M_hourly_Slots_temp.addAll(c1);
             
             Collection c2 = M_halfDay_Slots;
             M_HalfDay_Slot_temp.addAll(c2);
             
             M_hourly_Slots_temp.removeAll(Hourly_Booking_Slot_List);
             M_HalfDay_Slot_temp.removeAll(HalfDay_Booking_Slot_List);
             
             
             
             
             Date[] Hourly_To_Time = new Date[Hourly_Booking_Slot_List.size()];
             Date[] Hourly_From_time = new Date[Hourly_Booking_Slot_List.size()];
             
             Date Hourly_Booked_To_Date = null;
             Date Hourly_Booked_From_Date = null;
               for(int i=0;i<Hourly_Booking_Slot_List.size();i++){
                   String slot = Hourly_Booking_Slot_List.get(i).toString();
                   String time[] = slot.split("-");
                        try {
                            Hourly_From_time[i] = (Date) Formats.TIME.parseValue(time[0]);
                            Hourly_To_Time[i] = (Date) Formats.TIME.parseValue(time[1]);
                        } catch (BasicException ex) {
                            Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
             
               if(Hourly_To_Time.length==1){
                   
                   Hourly_Booked_To_Date = Hourly_To_Time[0];
                   Hourly_Booked_From_Date = Hourly_From_time[0];
               } 
               else if(Hourly_To_Time.length==2){
                   if(Hourly_To_Time[0].after(Hourly_To_Time[1])){
                           Hourly_Booked_To_Date = Hourly_To_Time[0];
                       }
                       else{
                           Hourly_Booked_To_Date = Hourly_To_Time[1];
                       }
                        if(Hourly_From_time[0].before(Hourly_From_time[1])){

                            Hourly_Booked_From_Date = Hourly_From_time[0];
                        }
                        else{
                            Hourly_Booked_From_Date = Hourly_From_time[1];
                        }
                       
                   }
               else{
                   for(int i=1;i<Hourly_To_Time.length;i++){
                       if(Hourly_To_Time[i-1].after(Hourly_To_Time[i])){
                           Hourly_Booked_To_Date = Hourly_To_Time[i-1];
                       }
                       else{
                           Hourly_Booked_To_Date = Hourly_To_Time[i];
                       } 
                   }
                   for(int i=1;i<Hourly_From_time.length;i++){
                       if(Hourly_From_time[i-1].after(Hourly_From_time[i])){
                           Hourly_Booked_From_Date = Hourly_From_time[i-1];
                       }
                       else{
                           Hourly_Booked_From_Date = Hourly_From_time[i];
                       } 
                   }
                   
                   
               }
               
               
               
               int t=0;
               
               // check for halfday slot....
               
               while(t<M_HalfDay_Slot_temp.size()){
                   String slot = M_HalfDay_Slot_temp.get(t).toString();
                   
                   String[] From_T = slot.split("-");
                   Date from_Time = new Date();
                   Date to_Time = new Date();
                        try {
                            from_Time = (Date) Formats.TIME.parseValue(From_T[0]);
                            to_Time = (Date) Formats.TIME.parseValue(From_T[1]);
                        } catch (BasicException ex) {
                            Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   if(from_Time.before(Hourly_Booked_To_Date) ){
                       if(to_Time.after(Hourly_Booked_From_Date)){
                       
                            M_HalfDay_Slot_temp.remove(t);
                            t=0;
                       }
                       else{
                           t++;
                       }
                   }
                   else{
                       t++;
                   }
                   
               }
               
               
                Date HalfDay_To_Time = new Date();
                Date HalfDay_From_Time = new Date();
                
                if(HalfDay_Booking_Slot_List.size()==1){
                     
                     for(int i=0; i<HalfDay_Booking_Slot_List.size();i++ ){
                       String slot = HalfDay_Booking_Slot_List.get(i).toString();
                       String[] To = slot.split("-");
                       
                            try {
                                HalfDay_From_Time = (Date) Formats.TIME.parseValue(To[0]);
                                HalfDay_To_Time = (Date) Formats.TIME.parseValue(To[1]);
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                         
                     }
                     
                  int t1=0;
                  while(t1<M_hourly_Slots_temp.size()){
                     String slot = M_hourly_Slots_temp.get(t1).toString();
                     String[] From_T = slot.split("-");
                     Date from_Time = new Date();
                     Date to_time = new Date();
                            try {
                                to_time = (Date) Formats.TIME.parseValue(From_T[1]);
                                from_Time = (Date) Formats.TIME.parseValue(From_T[0]);
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                   if(from_Time.before(HalfDay_To_Time)){
                       if(to_time.after(HalfDay_From_Time)){
                            M_hourly_Slots_temp.remove(t1);
                            t1=0;
                       }
                       else{
                            t1++;
                       }
                   }
                   else{
                       t1++;
                   }
                     
                     
                   }
                 }
               
               
                
                // if the time for booking is same as current date............. 
                
                
                String  Str_curr_date =  Formats.DATE.formatValue(new Date());
                Date Current_Date2 = null;
                
                try {
                        Current_Date2 = (Date) Formats.DATE.parseValue(Str_curr_date);
                    } catch (BasicException ex) {
                        Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                
                  
                if(Selected_Date.equals(Current_Date2))
                {
               
            
                  Date[] Hourly_To_Time3 = new Date[M_hourly_Slots_temp.size()];
                  Date[] Hourly_From_time3 = new Date[M_hourly_Slots_temp.size()];
            
                    
                
                      for(int i=0;i<M_hourly_Slots_temp.size();i++){
                          String slot = M_hourly_Slots_temp.get(i).toString();
                          String time[] = slot.split("-");
                               try {
                                   Hourly_From_time3[i] = new Date();
                                   Hourly_From_time3[i] = (Date) Formats.TIME.parseValue(time[0]);
                                   System.out.println(Hourly_From_time3[i].getHours());
                                   System.out.println(Current_Date.getHours());
                                   if(Hourly_From_time3[i].getHours()<Current_Date.getHours()){
                                      M_hourly_Slots_temp.remove(i);
                                      i=0;
                                   }
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                               }
                       }
                 
                 
                  Date[] HalfDay_To_Time3 = new Date[M_HalfDay_Slot_temp.size()];
                  Date[] HalfDay_From_time3 = new Date[M_HalfDay_Slot_temp.size()];
            
                    
                  
                      
                    for(int i=0;i<M_HalfDay_Slot_temp.size();i++){
                          String slot = M_HalfDay_Slot_temp.get(i).toString();
                          String time[] = slot.split("-");
                               try {
                                   HalfDay_From_time3[i] = new Date();
                                   HalfDay_From_time3[i] = (Date) Formats.TIME.parseValue(time[0]);
                                   System.out.println(HalfDay_From_time3[i].getHours());
                                   System.out.println(Current_Date.getHours());
                                   if(HalfDay_From_time3[i].getHours()<Current_Date.getHours()){
                                      M_HalfDay_Slot_temp.remove(i);
                                      i=0;
                                   }
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                               }
                       }
                      
                    
                    
                    
                    
                    
                    
                 }
                
                
                
                
                
                
               
               
                  M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots_temp);
                  hourly_combo.setModel(M_hourly_Slots_C);
                  temp_combo1.setModel(M_hourly_Slots_C);
                  temp_combo4.setModel(M_hourly_Slots_C);
                  
                  M_halfDay_Slots_C = new ComboBoxValModel(M_HalfDay_Slot_temp);
                  halfDay_combo.setModel(M_halfDay_Slots_C);
                  temp_combo2.setModel(M_halfDay_Slots_C);
                  temp_combo5.setModel(M_halfDay_Slots_C);
                  
                  M_fullDay_Slots_C = new ComboBoxValModel();
                  fullDay_combo.setModel(M_fullDay_Slots_C);
                  temp_combo3.setModel(M_fullDay_Slots_C);
                  temp_combo6.setModel(M_fullDay_Slots_C);
                  
                  
             
                   
                 main_panel.setVisible(false);
                 timingFrame.setVisible(true);
                // if(M_HalfDay_Slot_temp.size()==0 && M_hourly_Slots_temp.size()==0){
                 //     slot_message.setText("* Sorry ! no slots are available. Select another date. ");
                // }
                // else if(M_hourly_Slots_temp.size()>0){
                 //     slot_message.setText("* Only Hourly slot is available. ");
               //  }
                // else{
                //     slot_message.setText("* Only Half Day slot is available. ");
               //  }
                 
                //  slot_message.setVisible(true);
                  member_timing_main_panel.setVisible(true);
                
          
           }
         
         
         
        else if(slot_flag==2){
            
            
            
             Hourly_Booking_Slot_List = BookHall_Status.getBooking_Timings(B_date, hallname, m_App);
             HalfDay_Booking_Slot_List = BookHall_Status.getHalfDay_Booking_Timings(B_date, hallname, m_App);
             
             
             List<String> M_hourly_Slots_temp = new ArrayList<String>();
             List<String> M_HalfDay_Slot_temp = new ArrayList<String>();
           
             Collection  c1 = M_hourly_Slots;
             M_hourly_Slots_temp.addAll(c1);
             
             Collection c2 = M_halfDay_Slots;
             M_HalfDay_Slot_temp.addAll(c2);
             
             M_hourly_Slots_temp.removeAll(Hourly_Booking_Slot_List);
             M_HalfDay_Slot_temp.removeAll(HalfDay_Booking_Slot_List);
             
             
             
              Date HalfDay_To_Time = new Date();
              Date HalfDay_From_Time = new Date();  
                
                if(HalfDay_Booking_Slot_List.size()==1){
                     
                     for(int i=0; i<HalfDay_Booking_Slot_List.size();i++ ){
                       String slot = HalfDay_Booking_Slot_List.get(i).toString();
                       String[] To = slot.split("-");
                    
                            try {
                                HalfDay_From_Time = (Date) Formats.TIME.parseValue(To[0]);
                                HalfDay_To_Time = (Date) Formats.TIME.parseValue(To[1]);
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                         
                     }
                     
                  int t1=0;
                  while(t1<M_hourly_Slots_temp.size()){
                     String slot = M_hourly_Slots_temp.get(t1).toString();
                     String[] From_T = slot.split("-");
                   
                     Date To_Time = new Date();
                     Date from_Time = new Date();
                            try {
                                To_Time = (Date) Formats.TIME.parseValue(From_T[1]);
                                from_Time = (Date) Formats.TIME.parseValue(From_T[0]);
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                   if(from_Time.before(HalfDay_To_Time)){
                       
                       if(To_Time.after(HalfDay_From_Time)){
                          M_hourly_Slots_temp.remove(t1);
                          t1=0;  
                       }
                       else{
                           t1++;
                       }
                   }
                   else{
                       t1++;
                   }
                     
                     
                   }
                 }
               
                 
                
                
                // FOR SAME DATE TO REMOVE SLOTS BEFORE CURRENT TIME========================================================================
                
                
                
                String  Str_curr_date =  Formats.DATE.formatValue(new Date());
                Date Current_Date2 = null;
                
                try {
                        Current_Date2 = (Date) Formats.DATE.parseValue(Str_curr_date);
                    } catch (BasicException ex) {
                        Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                
                  
                if(Selected_Date.equals(Current_Date2))
                {
               
            
                  Date[] Hourly_To_Time3 = new Date[M_hourly_Slots_temp.size()];
                  Date[] Hourly_From_time3 = new Date[M_hourly_Slots_temp.size()];
            
                    
                
                      for(int i=0;i<M_hourly_Slots_temp.size();i++){
                          String slot = M_hourly_Slots_temp.get(i).toString();
                          String time[] = slot.split("-");
                               try {
                                   Hourly_From_time3[i] = new Date();
                                   Hourly_From_time3[i] = (Date) Formats.TIME.parseValue(time[0]);
                                   System.out.println(Hourly_From_time3[i].getHours());
                                   System.out.println(Current_Date.getHours());
                                   if(Hourly_From_time3[i].getHours()<Current_Date.getHours()){
                                      M_hourly_Slots_temp.remove(i);
                                      i=0;
                                   }
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                               }
                       }
                 
                 
                  Date[] HalfDay_To_Time3 = new Date[M_HalfDay_Slot_temp.size()];
                  Date[] HalfDay_From_time3 = new Date[M_HalfDay_Slot_temp.size()];
            
                    
                  
                      
                    for(int i=0;i<M_HalfDay_Slot_temp.size();i++){
                          String slot = M_HalfDay_Slot_temp.get(i).toString();
                          String time[] = slot.split("-");
                               try {
                                   HalfDay_From_time3[i] = new Date();
                                   HalfDay_From_time3[i] = (Date) Formats.TIME.parseValue(time[0]);
                                   System.out.println(HalfDay_From_time3[i].getHours());
                                   System.out.println(Current_Date.getHours());
                                   if(HalfDay_From_time3[i].getHours()<Current_Date.getHours()){
                                      M_HalfDay_Slot_temp.remove(i);
                                      i=0;
                                   }
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                               }
                       }
                      
                    
                    
                    
                    
                    
                    
                 }
                
                
               
               
                  M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots_temp);
                  hourly_combo.setModel(M_hourly_Slots_C);
                  temp_combo1.setModel(M_hourly_Slots_C);
                  temp_combo4.setModel(M_hourly_Slots_C);
                  
                  M_halfDay_Slots_C = new ComboBoxValModel(M_HalfDay_Slot_temp);
                  halfDay_combo.setModel(M_halfDay_Slots_C);
                  temp_combo2.setModel(M_halfDay_Slots_C);
                  temp_combo5.setModel(M_halfDay_Slots_C);
                  
                  M_fullDay_Slots_C = new ComboBoxValModel();
                  fullDay_combo.setModel(M_fullDay_Slots_C);
                  temp_combo3.setModel(M_fullDay_Slots_C);
                  temp_combo6.setModel(M_fullDay_Slots_C);
                  
                  
             
                   
                 main_panel.setVisible(false);
                 timingFrame.setVisible(true);
              //   if(M_HalfDay_Slot_temp.size()==0 && M_hourly_Slots_temp.size()==0){
              //        slot_message.setText("* Sorry ! no slots are available. Select another date. ");
              //   }
              //   else if(M_hourly_Slots_temp.size()>0){
               //       slot_message.setText("* Only Hourly slot is available. ");
              //  }
               //  else{
              //       slot_message.setText("* Only Half Day slot is available. ");
              //   }
                 
               //   slot_message.setVisible(true);
                  member_timing_main_panel.setVisible(true);
             
                
        }
        else if(slot_flag==3){
            
               JOptionPane.showMessageDialog(this, " Hall Already Booked .. Please Select Another Date..!! ", " hall", JOptionPane.ERROR_MESSAGE);
               
                main_panel.setVisible(true);
            
          }
      
         else{
             
                String  Str_curr_date =  Formats.DATE.formatValue(new Date());
                Date Current_Date2 = null;
                
                try {
                        Current_Date2 = (Date) Formats.DATE.parseValue(Str_curr_date);
                    } catch (BasicException ex) {
                        Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                
                
                
                if(Selected_Date.equals(Current_Date2))
                {
                List<String> M_hourly_Slots_temp = new ArrayList<String>();
                List<String> M_HalfDay_Slot_temp = new ArrayList<String>();
            
                  Collection  c1 = M_hourly_Slots;
                  M_hourly_Slots_temp.addAll(c1);
             
                  Collection c2 = M_halfDay_Slots;
                  M_HalfDay_Slot_temp.addAll(c2);
            
                  Date[] Hourly_To_Time = new Date[M_hourly_Slots_temp.size()];
                  Date[] Hourly_From_time = new Date[M_hourly_Slots_temp.size()];
            
                    
                 Date Hourly_Booked_To_Date = null;
                    Date Hourly_Booked_From_Date = null;
                      for(int i=0;i<M_hourly_Slots_temp.size();i++){
                          String slot = M_hourly_Slots_temp.get(i).toString();
                          String time[] = slot.split("-");
                               try {
                                   Hourly_From_time[i] = new Date();
                                   Hourly_From_time[i] = (Date) Formats.TIME.parseValue(time[0]);
                                   System.out.println(Hourly_From_time[i].getHours());
                                   System.out.println(Current_Date.getHours());
                                   if(Hourly_From_time[i].getHours()<Current_Date.getHours()){
                                      M_hourly_Slots_temp.remove(i);
                                      i=0;
                                   }
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                               }
                       }
                 
                 
                  Date[] HalfDay_To_Time = new Date[M_HalfDay_Slot_temp.size()];
                  Date[] HalfDay_From_time = new Date[M_HalfDay_Slot_temp.size()];
            
                    
                    Date HalfDay_Booked_To_Date = null;
                    Date HalfDay_Booked_From_Date = null;
                      
                    for(int i=0;i<M_HalfDay_Slot_temp.size();i++){
                          String slot = M_HalfDay_Slot_temp.get(i).toString();
                          String time[] = slot.split("-");
                               try {
                                   HalfDay_From_time[i] = new Date();
                                   HalfDay_From_time[i] = (Date) Formats.TIME.parseValue(time[0]);
                                   System.out.println(HalfDay_From_time[i].getHours());
                                   System.out.println(Current_Date.getHours());
                                   if(HalfDay_From_time[i].getHours()<Current_Date.getHours()){
                                      M_HalfDay_Slot_temp.remove(i);
                                      i=0;
                                   }
                               } catch (BasicException ex) {
                                   Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                               }
                       }
                      
                      
                      
                       M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots_temp);
                  hourly_combo.setModel(M_hourly_Slots_C);
                  temp_combo1.setModel(M_hourly_Slots_C);
                  temp_combo4.setModel(M_hourly_Slots_C);
                  
                  M_halfDay_Slots_C = new ComboBoxValModel(M_HalfDay_Slot_temp);
                  halfDay_combo.setModel(M_halfDay_Slots_C);
                  temp_combo2.setModel(M_halfDay_Slots_C);
                  temp_combo5.setModel(M_halfDay_Slots_C);
                  
                  M_fullDay_Slots_C = new ComboBoxValModel();
                  fullDay_combo.setModel(M_fullDay_Slots_C);
                  temp_combo3.setModel(M_fullDay_Slots_C);
                  temp_combo6.setModel(M_fullDay_Slots_C);
                  
                  
             
                   
                        main_panel.setVisible(false);
                        timingFrame.setVisible(true);
                     //   if(M_HalfDay_Slot_temp.size()==0 && M_hourly_Slots_temp.size()==0){
                     //        slot_message.setText("* Sorry ! no slots are available. Select another date. ");
                     //   }
                     //   else if(M_hourly_Slots_temp.size()>0){
                      //       slot_message.setText("* Only Hourly slot is available. ");
                     //  }
                      //  else{
                     //       slot_message.setText("* Only Half Day slot is available. ");
                     //   }

                      //   slot_message.setVisible(true);
                         member_timing_main_panel.setVisible(true);

                      
                      
                      
                      
                      
                      
                      
                      
                  
                }
                else{
                    
                     M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots);
                        hourly_combo.setModel(M_hourly_Slots_C);
                        temp_combo1.setModel(M_hourly_Slots_C);
                        temp_combo4.setModel(M_hourly_Slots_C);

                        M_halfDay_Slots_C = new ComboBoxValModel(M_halfDay_Slots);
                        halfDay_combo.setModel(M_halfDay_Slots_C);
                        temp_combo2.setModel(M_halfDay_Slots_C);
                        temp_combo5.setModel(M_halfDay_Slots_C);
                        M_fullDay_Slots_C = new ComboBoxValModel(M_fullDay_Slots);
                        fullDay_combo.setModel(M_fullDay_Slots_C);
                        temp_combo3.setModel(M_fullDay_Slots_C);
                        temp_combo6.setModel(M_fullDay_Slots_C);



                         main_panel.setVisible(false);
                         timingFrame.setVisible(true);
                         member_timing_main_panel.setVisible(true);
                    
                    
                    
                }
            
                 
                
                 
         }
         
         
         
       
     }
         else{
             JOptionPane.showMessageDialog(this, " Select Booking Date  First ", " hall", JOptionPane.ERROR_MESSAGE);
         }
     }
     else{
        JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
     }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
      TIMING_SLOT = "";
       slot_TF.setText(null); 
      main_panel.setVisible(true);
       timingFrame.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
  
                     
                    if(hour_radio.isSelected() || halfDay_radio.isSelected() || fullDay_radio.isSelected()){
                          if(hour_radio.isSelected()){
                              if(hourly_combo.getSelectedIndex()!=-1){
                                TIMING_SLOT  =  hourly_combo.getSelectedItem().toString();
                                  slot_TF.setText(TIMING_SLOT);
                                  main_panel.setVisible(true);
                                  timingFrame.setVisible(false);
                                  
                              }
                              else{
                                    JOptionPane.showMessageDialog(this, " Select Timing Slots to booked ..!! ", " slots", JOptionPane.ERROR_MESSAGE);
                              }
                          }
                          if(halfDay_radio.isSelected()){
                              
                              if(halfDay_combo.getSelectedIndex()!=-1){
                                TIMING_SLOT  =  halfDay_combo.getSelectedItem().toString();
                                  slot_TF.setText(TIMING_SLOT);
                                   main_panel.setVisible(true);
                                  timingFrame.setVisible(false);
                                  
                              }
                              else{
                                    JOptionPane.showMessageDialog(this, " Select Timing Slots to booked ..!! ", " slots", JOptionPane.ERROR_MESSAGE);
                              }
                              
                              
                              
                          }
                          if(fullDay_radio.isSelected()){
                               if(fullDay_combo.getSelectedIndex()!=-1){
                                TIMING_SLOT  =  fullDay_combo.getSelectedItem().toString();
                                  slot_TF.setText(TIMING_SLOT);
                                   main_panel.setVisible(true);
                                  timingFrame.setVisible(false);
                                  
                              }
                              else{
                                    JOptionPane.showMessageDialog(this, " Select Timing Slots to booked ..!! ", " slots", JOptionPane.ERROR_MESSAGE);
                              }
                              
                          }
                          
                          
                          
                          
                    }
                    
                    
                      else{
                          JOptionPane.showMessageDialog(this, " Select any One (hourly , halfDay or for fullday )  ..!!   ", " slots", JOptionPane.ERROR_MESSAGE);
                      }   
                      
  
        
     
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
      if(hallCombo.getSelectedIndex()!=-1){ 
          jButton14.setVisible(true);//added by pratima
          cardno_text.requestFocus();
          
          if(member.isSelected()){
          non_mem_details_panel.setVisible(false);
          membersDetails_frame.setVisible(true);
          main_panel.setVisible(false);
          
      }
        if(non_member.isSelected()){  
          non_mem_details_panel.setVisible(true);
          membersDetails_frame.setVisible(true);
          main_panel.setVisible(false);
        }
      }
      else{
          JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void memno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyPressed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_mname_textComponentAdded

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                mname_text.setText(customerInfo.toString());
                memno_text.setText(customerInfo.getSearchkey());
                Contact = customerInfo.getMobile();
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
       if(member.isSelected()){
           if(memno_text.getText() !=null && mname_text.getText() !=null && memno_text.getText().trim().length()>0 && mname_text.getText().trim().length()>0){ 
           
               MemberName = mname_text.getText();
               MemberNo = memno_text.getText();
               membersDetails_frame.setVisible(false);
                       
               main_panel.setVisible(true);
               
           }
           else{
               JOptionPane.showMessageDialog(this, " Member's Details Should not be empty..!!  ", " hall", JOptionPane.ERROR_MESSAGE);
           }
       }
       if(non_member.isSelected()){
           
           if(non_member_name.getText()!=null && non_member_name.getText().trim().length()>0){
               if(non_M_ContactNo.getText()!=null && non_M_ContactNo.getText().trim().length()>0){
                   if(non_M_Address.getText()!=null && non_M_Address.getText().trim().length()>0){
                      if(memno_text.getText() !=null && mname_text.getText()!=null && memno_text.getText().trim().length()>0 && mname_text.getText().trim().length()>0){ 
                       Non_Memmer_Name = non_member_name.getText();
                       Contact = non_M_ContactNo.getText();
                       Non_member_Address = non_M_Address.getText();
                       MemberName = mname_text.getText();
                       MemberNo = memno_text.getText();
                       
                       
                       
                       membersDetails_frame.setVisible(false);
                       
                       main_panel.setVisible(true);
                      }
                      else{
                          
                           JOptionPane.showMessageDialog(this, " Member's Details Should not be empty..!!  ", " hall", JOptionPane.ERROR_MESSAGE);
                      }
                   }
                   else{
                       
                        JOptionPane.showMessageDialog(this, " Address should not be empty..!!  ", " hall", JOptionPane.ERROR_MESSAGE);
                   }
                   
               }
               else{
                    JOptionPane.showMessageDialog(this, " Contact No. should not be empty...!!  ", " hall", JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
                JOptionPane.showMessageDialog(this, " Name should not be empty..!!!  ", " hall", JOptionPane.ERROR_MESSAGE);
           }
           
       }
        
       
    }//GEN-LAST:event_SaveActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
      non_mem_details_panel.setVisible(false);
      non_member_name.setText(null);
      non_M_ContactNo.setText(null);
      
      membersDetails_frame.setVisible(false);
       main_panel.setVisible(true);
       non_member_name.setText(null);
       non_M_ContactNo.setText(null);
       non_M_Address.setText(null);
       memno_text.setText(null);
       mname_text.setText(null);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       if(hallCombo.getSelectedIndex()!=-1){
           if(date_textfield.getText()!=null){
               
             Avail_Hall_frame.setVisible(true);
             main_panel.setVisible(false);
               
           }
           else{
               JOptionPane.showMessageDialog(this, " Select Date First ", " hall", JOptionPane.ERROR_MESSAGE);
           }
       }
       else{
           JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton7ActionPerformed

     String TIMING_SLOT ;
     String Non_Memmer_Name;
     String Contact ; 
     String Non_member_Address;
     String MemberName;
     String MemberNo;
     Double Hall_Charge;
     String LUXURYTAX ; 
     String TAX2;
     String TAX3;
     String LUXURYTAX_ID ; 
     String TAX2_ID;
     String TAX3_ID;
     
     String BOOKINGDATE;
     
     int status_num;
     String HallName;
     String FLOOR ; 
     int MEMBER_FLAG = 0 ; 
     int NON_MEMBER_FLAG = 0;
     int basicTax;
     int cascadeTax;
     int basicTax2;
     int cascadeTax2;
     int MaxCapacity;
     int flag;
    Date dTemp;
    Date payment_date;
    String Member_ID;
    String HallName_ID;
    int Payment_flag; 
    int Booking_Slot_Flag = 0;
    
    int Block_Flag;
    Date Block_From ;
    Date Block_Upto ;
    String Role;
    String Booking_Seq_No;
    String Cancel_Accnt_ID;
    String Advance_Acct_ID;
    String UserAccnt;
    Double TotAmt;
    
    private void book_hall_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book_hall_btnActionPerformed
      if(hallCombo.getSelectedIndex()!=-1){
          if(date_textfield.getText()!=null && date_textfield.getText().trim().length()>0){
              if(member.isSelected() || non_member.isSelected()){
                  if(slot_TF.getText()!=null && slot_TF.getText().trim().length()>0){
                     if(memno_text.getText()!=null && memno_text.getText().trim().length()>0 && mname_text.getText()!=null && mname_text.getText().trim().length()>0){
                      // for tariff 
                      
                     
                      
                      
                      if(member.isSelected()){
                          mName.setText(MemberName);
                          Member_ID = customerInfo.getId();
                          
                          Non_Memmer_Name = "N/A";
                         // Non_Member_Contact = "N/A";
                          Non_member_Address = "N/A";
                          NName.setText(Non_Memmer_Name);
                          NName.setVisible(false);
                          Contact = customerInfo.getMobile();
                          contactNo.setText(""+Contact);
                          Adress.setText(Non_member_Address);
                          memberNo.setText(MemberNo);
                          MEMBER_FLAG = 1;
                          NON_MEMBER_FLAG = 0;
                          
                          if(hour_radio.isSelected()){
                           Hall_Charge = Double.parseDouble(hourly.getText());
                           Booking_Slot_Flag = 1;
                            slot_label.setText("(Hourly Booked)");
                           
                       } 
                          if(halfDay_radio.isSelected()){
                            Hall_Charge = Double.parseDouble(halfDay.getText());
                            Booking_Slot_Flag = 2;
                            slot_label.setText("(Half Day)");
                        }
                          if(fullDay_radio.isSelected()){
                            Booking_Slot_Flag = 3;
                            Hall_Charge = Double.parseDouble(fullDay.getText());
                            
                             slot_label.setText("(Full Day)");
                        }
                        
                        
                        mem_label.setText("( Booked For :- Member )");
                        mem_label.setForeground(Color.BLUE);
               
                          
                    }
                    if(non_member.isSelected()){
                       
                         mName.setText(MemberName);
                         Member_ID = customerInfo.getId();
                         NName.setText(Non_Memmer_Name);
                         NName.setVisible(true);
                         contactNo.setText(""+Contact);
                         Adress.setText(Non_member_Address);
                         memberNo.setText(MemberNo);
                         MEMBER_FLAG = 0;
                         NON_MEMBER_FLAG = 1;
                         
                            if(hour_radio.isSelected()){
                              Hall_Charge = Double.parseDouble(hourly1.getText());
                              Booking_Slot_Flag = 1;
                              slot_label.setText("(Hourly Booked)");
                           
                             } 
                          if(halfDay_radio.isSelected()){
                              Hall_Charge = Double.parseDouble(halfDay1.getText());
                              Booking_Slot_Flag = 2;
                              slot_label.setText("(Half Day)");
                            }
                          if(fullDay_radio.isSelected()){
                              Booking_Slot_Flag = 3;
                              Hall_Charge = Double.parseDouble(fullDay1.getText());
                              slot_label.setText("(Full Day)");
                             }
                         
                          
                         mem_label.setText("( Booked For :- Non-Member )");
                         mem_label.setForeground(Color.BLUE);
                         
                         
                         
                         
                   }  
                         
                   
                  LUXURYTAX = luxuryTax.getText();
                  
                  Total_Charge = 0.00;
                  Double Basic_Charge = Hall_Charge;
                  Total_Charge = Total_Charge + Basic_Charge;
                  
                  if(tax1_rate.getText()!=null && tax1_rate.getText().trim().length()>0){
                      Double Tax1 = Double.parseDouble(tax1_rate.getText());
                      tax1_rate.setText(decimalFormat.format(Basic_Charge*Tax1));
                      Total_Charge = Total_Charge + (Basic_Charge*Tax1);
                      
                  }
                  else{
                      
                      
                  }
                  
                  if(tax2_rate.getText()!=null && tax2_rate.getText().trim().length()>0){
                      if(basicTax==1){
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
                      if(basicTax2==1){
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
                 // tax1_rate.setText("");
                 // tax2_rate.setText("");
                 // tax3_rate.setText("");
                  
                 
                  if(tax1_ID_label.getText()!=null && tax1_ID_label.getText().trim().length()>0){
                       LUXURYTAX_ID = tax1_ID_label.getText();
                       
                  }
                  else{
                        LUXURYTAX_ID = "";
                  }
                  
                  
                 if(tax2_id_label.getText()!=null && tax2_id_label.getText().trim().length()>0){
                       TAX2_ID = tax2_id_label.getText();
                  }
                  else{
                        TAX2_ID = "";
                  }
                 
                 
                 if(tax3_id_label.getText()!=null && tax3_id_label.getText().trim().length()>0){
                       TAX3_ID = tax3_id_label.getText();
                  }
                  else{
                        TAX3_ID = "";
                  }
                 
                 
                  
                 
                 
                  TAX2 = tax2.getText();
                  TAX3 = tax3.getText();
                  TIMING_SLOT = slot_TF.getText();
                  BOOKINGDATE =  date_textfield.getText();
                  Payment_flag = 0;
                  
                  flag= BookHall_Status.getFlag(m_App) ;
                  
                         try {
                             dTemp = (Date) Formats.DATE.parseValue(BOOKINGDATE);
                             payment_date = (Date) Formats.DATE.parseValue(payment_label.getText());
                             
                         } catch (BasicException ex) {
                             Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                         }
                  
                  MaxCapacity = Integer.parseInt(max_Capacity.getText());
                  status_num = 3;
                  HallName = hallCombo.getSelectedItem().toString();
                  FLOOR = floor.getText();
                  
                  
                  hallName.setText(HallName);
                  bookingDate.setText(BOOKINGDATE);
                  bookingSlot.setText(TIMING_SLOT);
                  tariff.setText(decimalFormat.format(Hall_Charge));
                  tax_1.setText(LUXURYTAX);
                  tax_2.setText(TAX2);
                  tax_3.setText(TAX3);
                  capacity.setText(""+MaxCapacity);
                  
                  HallName_ID = hall_ID.getText();
                  
                  note_label.setVisible(false);
                  jLabel29.setVisible(false);
                  
                 String temp = BOOKINGDATE;
                 int count=0;
                 count = BookHall_Status.getHall_Booked_Status(temp, HallName, TIMING_SLOT, m_App);
                 if(count==1){
                     count=0;
                 }
                 
                        
                 if(count==0){
                     
                       if(Block_Flag==1){
                                 Date d = new Date();
                                 int No_of_days  = (int) (Block_Upto.getTime() - Block_From.getTime())/(1000 * 60 * 60 * 24);
                                 No_of_days++;
                                    try {
                                        d = (Date) Formats.DATE.parseValue(date_textfield.getText());
                                    } catch (BasicException ex) {
                                        Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                 
                                 for(int i=0; i< No_of_days ; i++ ){
                                    Calendar c = Calendar.getInstance();
                                    c.setTimeInMillis(Block_From.getTime());
                                    c.add(Calendar.DATE, i);
                                    Date NextDay = c.getTime(); 
                                     
                                    if(d.equals(NextDay)){
                                         JOptionPane.showMessageDialog(this, "Hall is Blocked For Club Use.  Select Another Date  !!! ");
                                         date_textfield.setText(null);
                                         slot_TF.setText(null);
                                         
                                         break; 
                                    }
                                    else{
                                            message.setVisible(false);
                                            message1.setVisible(false);
                                            jButton16.setVisible(true);
                                            hallDetails_panel.setVisible(true);
                                            main_panel.setVisible(false);
                                            Submit.setVisible(true);
                                  
                                    }
                                 }
                               }
                       else{
                                            message.setVisible(false);
                                            message1.setVisible(false);
                                            jButton16.setVisible(true);
                                            hallDetails_panel.setVisible(true);
                                            main_panel.setVisible(false);
                                            Submit.setVisible(true);
                       }
                     
                  
                 }
                 else{
                   JOptionPane.showMessageDialog(this, "Hall is already booked..!!! ");
                }
           }
                     else{
                         JOptionPane.showMessageDialog(this, " Member details should not be empty ..!!  ", " slot", JOptionPane.ERROR_MESSAGE);
                     }
                  }
                  else{
                      JOptionPane.showMessageDialog(this, " Select Timing Slot ..!!  ", " slot", JOptionPane.ERROR_MESSAGE);
                  }
              }
              else{
              
              JOptionPane.showMessageDialog(this, " Select Member or Non-Member ", " member", JOptionPane.ERROR_MESSAGE);
          }
          }
          else{
              JOptionPane.showMessageDialog(this, " Select Date  First ", " hall", JOptionPane.ERROR_MESSAGE);
          }
      }
      else{
          JOptionPane.showMessageDialog(this, " Select hall First ", " hall", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_book_hall_btnActionPerformed

    private void non_member_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_non_member_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_non_member_nameActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
       int submit_hall = JOptionPane.showConfirmDialog(jPanel3, "Do You Want to Block Hall .. ?? ", "Booking Menu" , JOptionPane.YES_NO_OPTION);
        if(submit_hall == JOptionPane.YES_OPTION){
            
            
             try {
                  Booking_Seq_No = getNextHall_Sequence();
              } catch (BasicException ex) {
                  Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
              }
                      
            if(Booking_Seq_No!=null && Booking_Seq_No.trim().length()>0 && Booking_Seq_No!="")
            {
            
             Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                                                              
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                            
                            
                       int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO hall_booked_details (ID ,BOOKING_SEQ_NO , MEMBERNAME , MEM_NO , STATUS , HALL_NAME ,FLOOR , MEMBER , NON_MEMBER , BOOKING_DATE , BOOKING_SLOT  , CHARGES ,  CRBY , CRDATE , CRHOST , BASIC , CASCADE1 , LUXURYTAX , TAX2 , TAX3 , NON_MEM_NAME , NON_MEM_CONTCT , NON_MEM_ADDR , MAXCAPACITY , BOOKING_DATE_EX ,  FLAG , BASIC2 , CASCADE2 , LAST_PAYMENT_DATE,PAYMENT_FLAG , SLOT_FLAG , ROLE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING , Datas.STRING, Datas.STRING , Datas.INT ,Datas.STRING, Datas.STRING,Datas.INT ,Datas.INT ,Datas.STRING  ,Datas.STRING   , Datas.DOUBLE , Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING ,Datas.INT , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT , Datas.TIMESTAMP , Datas.INT , Datas.INT , Datas.INT , Datas.TIMESTAMP,Datas.INT,Datas.INT , Datas.STRING})                         
                        ).exec(new Object[]{UUID.randomUUID().toString(),Booking_Seq_No , Member_ID ,MemberNo ,status_num ,HallName_ID ,FLOOR , MEMBER_FLAG , NON_MEMBER_FLAG ,BOOKINGDATE  ,  TIMING_SLOT ,Hall_Charge,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , basicTax , cascadeTax , LUXURYTAX_ID ,TAX2_ID , TAX3_ID , Non_Memmer_Name ,  Contact , Non_member_Address , MaxCapacity , dTemp , flag , basicTax2 , cascadeTax2 , payment_date , Payment_flag , Booking_Slot_Flag ,  m_App.getAppUserView().getUser().getRole()});                                                                                                
                         
                         
                        
                          
                                                                                         
                            return null;                                      
                            }                            
                        };                 
                          
                        try {                 
                            t.execute();          
                            
                            
                            
                            
                            JOptionPane.showMessageDialog(this, "Hall Booked   Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            main_panel.setVisible(true);
                            hallDetails_panel.setVisible(false);
                            Update_HallSeq();
                            loaddata();
                            reset();
                        }
                         catch (BasicException ex) {                    
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                            
                            }
            }
            
            
            
        }
        else{
            hallDetails_panel.setVisible(false);
            main_panel.setVisible(true);
        }
        
    }//GEN-LAST:event_SubmitActionPerformed

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

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
       reset();
    }//GEN-LAST:event_cancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
           if(jTable1.getSelectedRow()<BookHall_Status.getHallSize()){
               int row = jTable1.getSelectedRow();
               HallStatusInfo showdata = BookHall_Status.getHallList().get(row);
               
               Total_Charge = 0.00;
               mName.setText(showdata.getMemberName());
               memberNo.setText(showdata.getMem_No());
               Adress.setText(showdata.getNON_MEM_ADDR());
               NName.setText(showdata.getNON_MEM_NAME());
               contactNo.setText(""+showdata.getNON_MEM_CONTCT());
               hallName.setText(showdata.gethall_name());
               capacity.setText(""+showdata.getMAX_CAPACITY());
               tariff.setText(decimalFormat.format(showdata.getCHARGES()));
               Double Basic_charge = showdata.getCHARGES();
               tax_1.setText(showdata.getLUXURYTAX_N());
               tax_2.setText(showdata.getTAX2_N());
               tax_3.setText(showdata.getTAX3_N());
               Total_Charge = Total_Charge + Basic_charge;
               
               basicTax = showdata.getBASIC();
               basicTax2 = showdata.getBASIC2();
               
               
               if(showdata.getTax1_Rate()!=null ) {
                 Double Tax1 = showdata.getTax1_Rate()*Basic_charge;
                 tax1_rate.setText(decimalFormat.format(showdata.getTax1_Rate()*Basic_charge));
                 Total_Charge = Total_Charge + Tax1;
               }
               else{
                   tax1_rate.setText("");
               }
              if(showdata.getTax2_Rate()!=null ) {
                  
                  if(basicTax==1){
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
                   
                   if(basicTax2==1){
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
              
               
               bookingDate.setText(showdata.getBOOKING_DATE());
               bookingSlot.setText(showdata.getTIMING_SLOTS());
               note_label.setText(null);
               
               if(showdata.getMem_flag()==1 ){
                   Adress.setText(showdata.getCustAddress());
               }
               
               
               
               Date Blocking_date = showdata.getBLOCKED_DATE();
               int Payment_days = showdata.getPAYMENT_DAYS();
               Calendar c = Calendar.getInstance();
               c.setTimeInMillis(Blocking_date.getTime());
               c.add(Calendar.DATE, Payment_days);
               Date final_payment_date = c.getTime();
               
               Date Current_date = new Date();
               int No_of_days_left = (int) (final_payment_date.getTime() - Current_date.getTime())/(1000 * 60 * 60 * 24);
               
               String Final_date = Formats.DATE.formatValue(final_payment_date);
               
               payment_label.setText(Final_date);
               
               
               hallDetails_panel.setVisible(true);
               main_panel.setVisible(false);
               jButton16.setVisible(true);
               Submit.setVisible(false);
               
               
               jPanel5.setVisible(true);
                
               
               if(showdata.getSLOT_FLAG()==1){
                   slot_label.setText("(Hourly Booked)");
               }
               if(showdata.getSLOT_FLAG()==2){
                   slot_label.setText("(Half Day)");
               }
               if(showdata.getSLOT_FLAG()==3){
                   slot_label.setText("(Full Day)");
               }
               note_label.setText(null);
               jLabel29.setVisible(true);
               note_label.setVisible(false);
               
               if(showdata.getFlag()==0){
               
                 message.setText("* Sorry !  Request is pending , Wait for to approval..!!  ");
                 message.setForeground(Color.RED);
                 message.setVisible(true);
                 message1.setVisible(false);
               }
               if(showdata.getFlag()==1){
                   if(showdata.getPAYMENT_FLAG()==1 && showdata.getStatus()==2){
                       message.setText("Advanced payment recieved..!");
                       message.setVisible(true);
                        message1.setVisible(false);
                       
                   }
                   else{
                        message.setText("* congratulations !   Request has been processed , Please Collect  Amount ..!! ");
                        message1.setText("* Last Date For Payment : "+Final_date+ ".     Days Left "+(No_of_days_left+1)+".");
                        message.setForeground(Color.RED);
                        message1.setForeground(Color.RED);
                        message.setVisible(true);
                        message1.setVisible(true);
                   }
               }
               if(showdata.getFlag()==2){
                   
                   String id = showdata.getId();
                   String can_note = BookHall_Status.getCancellation_Reason(m_App, id);
                   note_label.setText(can_note);
                   note_label.setVisible(true);
                   message.setText("* Request not approved..!!");
                   message.setForeground(Color.RED);
                   message.setVisible(true);
                   message1.setVisible(false);
               }
               if(showdata.getMem_flag()==1){
                   mem_label.setText("( Booked For :- Member )");
                   mem_label.setForeground(Color.BLUE);
               }
               else{
                    mem_label.setText("( Booked For :- Non-Member )");
                    mem_label.setForeground(Color.BLUE);
               }
               
               booking_status_label.setText(""+showdata.getFlag());
               
               
           }
       }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
        
        hallDetails_panel.setVisible(false);
        main_panel.setVisible(true);
        Total_Charge=0.00;
        int x = hallCombo.getSelectedIndex();
        System.out.println(x);
        hallCombo.setSelectedIndex(-1);
        hallCombo.setSelectedIndex(x);
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
      Avail_hall_List_for_book = new ArrayList<String>();
        
        Date date=new Date();
      Calendar call = Calendar.getInstance();
      call.setTimeInMillis(new Date().getTime());
      call.add(Calendar.MONTH, 3);
      Date afterDate = call.getTime();
      
      Calendar c1 = Calendar.getInstance();
      c1.setTimeInMillis(new Date().getTime());
      c1.add(Calendar.DATE,-1);
      Date Curr_date = c1.getTime();
      
      
        try {
            date = (Date) Formats.DATE.parseValue(date_textfield.getText());
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
               JOptionPane.showMessageDialog(this, "Booking is available upto 3 Monts only..!!! ");
            }
        
            else{
                   String TempDATE =   Formats.DATE.formatValue(date);
                   jTextField1.setText(TempDATE);
                   List<Object> hallList = new ArrayList<Object>();
                   List<Object> BookedHallList = new ArrayList<Object>();
                   List<Object> Booked_slot_list = new ArrayList<Object>();
                   List<String> N_Hourly_Timings_List = new ArrayList<String>();
                   List<Object> Blocked_halls = new ArrayList<Object>();
                   
                   
                   
                   // checking for hall blocked or not...
                   Blocked_halls = getBlocked_Hall_List(date);
                  
                   
                   hallList = BookHall_Status.hallNamesList(m_App);
                   BookedHallList = BookHall_Status.CheckAvailibility(TempDATE, m_App);
                   
                   int i=0;
                   while(i<BookedHallList.size()){
                       String Booked_Hall = BookedHallList.get(i).toString();
                       int get_Slot_Booked_flag = BookHall_Status.getTiming_Slot_Flag(TempDATE, Booked_Hall, m_App);
                       
                       
                       if(get_Slot_Booked_flag==1){
                          
                           Booked_slot_list = BookHall_Status.getBooking_Timings(TempDATE, Booked_Hall, m_App);
                           N_Hourly_Timings_List = BookHall_Status.getMEM_Hourly_timing_slots(Booked_Hall, m_App);
                           
                          // if(Booked_slot_list.size()< N_Hourly_Timings_List.size()){
                               // BookedHallList.remove(i);
                         //  }
                          // else{
                              i++;
                         // }
                        }
                       else{
                           i++;
                       }
                       
                   }
                   
                
                  Collection<Object> c =  BookedHallList;
                  HashSet<Object> h = new HashSet<Object>();
                  h.addAll(c);
                 
                  BookedHallList.clear();
                  BookedHallList.addAll(h);
                   
                   showHallAvail_Btns(hallList,BookedHallList ,Blocked_halls );
                  
                   
                 
                
                }
            }
            
        }
          
        catch(Exception e1){
              e1.printStackTrace();
          }
     
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       Avail_Date_frame.setVisible(true);
       main_panel.setVisible(false);
       jScrollPane4.getViewport().setView(null);
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
      Avail_Hall_frame.setVisible(true);
      main_panel.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void hallListConbo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hallListConbo2ItemStateChanged
     if(hallListConbo2.getSelectedIndex()!=-1){
          String Current_hall_Name = hallListConbo2.getSelectedItem().toString();
         
     
         
          try{
               hallBooking = BookHallTableModel.loadInstanceHallInfo(m_App, Current_hall_Name);
              
           } 
           catch (BasicException ex) {
                ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         hallDetails =  (List<BookHallTableModel.HallAvailibilityInfo>) hallBooking.getHallPath();
           
          
        BookHallTableModel.HallAvailibilityInfo editData = (BookHallTableModel.HallAvailibilityInfo) ((HallAvailibilityInfo)hallDetails.get(0));
         
        advanceBookedDura.setText(""+editData.getAdvanceBookingDuration());
         
        
        try {
             showCalender(Current_hall_Name);
         } catch (BasicException ex) {
             Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        
         
         
         
         
     }
     else{
          jScrollPane5.getViewport().setView(null);
           
     }
     
     
    }//GEN-LAST:event_hallListConbo2ItemStateChanged

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
     int unique = Integer.parseInt(uniqueNo.getText());
     int AdvanceBDT = Integer.parseInt(advanceBookedDura.getText());
     String Current_hall_Name =  hallListConbo2.getSelectedItem().toString();
     
     if(unique <= (AdvanceBDT+1) && unique > 1){
         unique--;
         uniqueNo.setText(""+unique);
           jButton22.setEnabled(true);
           jButton23.setEnabled(true);
                   int GetYear = Integer.parseInt(year_Label.getText());
                   int GetMonth = Integer.parseInt(Month_label.getText())-1;
                   if(GetMonth==0){
                    GetYear = GetYear-1;
                    GetMonth = 12;
                }
      
                Calendar cal2 = Calendar.getInstance();
                
                cal2.add(Calendar.MONTH, +(unique-1));

                 int months = cal2.get(Calendar.MONTH)+1;
                 Month_label.setText(""+months);
                 String Currnt_month_name = getMonth(months);
                  int currnt_year = GetYear;
                   year_Label.setText(""+currnt_year);
                    Month_year.setText(Currnt_month_name+" "+currnt_year);

                 int no_of_days = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);    
                int currnt_day = cal2.getActualMinimum(Calendar.DAY_OF_MONTH);
                cal2.set(GetYear, months-1, currnt_day);
                int day_of_week = cal2.get(Calendar.DAY_OF_WEEK);


               if(unique==1){
                   currnt_day = cal2.get(Calendar.DAY_OF_MONTH);
                            try {
                                calender_display(day_of_week ,no_of_days , currnt_day , Current_hall_Name);
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
               }
               else{
                        try {
                            calender_display(day_of_week ,no_of_days , currnt_day , Current_hall_Name);
                        } catch (BasicException ex) {
                            Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                        }
               }
             if(unique==1){
                 jButton23.setEnabled(false);
             }
     } 
     
  
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
       int unique = Integer.parseInt(uniqueNo.getText());
       int AdvanceBDT = Integer.parseInt(advanceBookedDura.getText());
       String Current_hall_Name =  hallListConbo2.getSelectedItem().toString();
       
       if(unique <= AdvanceBDT){
                    unique++;
                    uniqueNo.setText(""+unique);
                     jButton22.setEnabled(true);
                     jButton23.setEnabled(true);
                   int GetYear = Integer.parseInt(year_Label.getText());
                   int GetMonth = Integer.parseInt(Month_label.getText())-1;
                   
                   if(GetMonth==0){
                    GetYear = GetYear-1;
                    GetMonth = 12;
                     }
      
                Calendar cal2 = Calendar.getInstance();
                cal2.add(Calendar.MONTH, +(unique-1));

                 int months = cal2.get(Calendar.MONTH)+1;
                 Month_label.setText(""+months);
                 String Currnt_month_name = getMonth(months);
                  int currnt_year = GetYear;
                   year_Label.setText(""+currnt_year);
                    Month_year.setText(Currnt_month_name+" "+currnt_year);

                 int no_of_days = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);    
                int currnt_day = cal2.getActualMinimum(Calendar.DAY_OF_MONTH);
                cal2.set(GetYear, months-1, currnt_day);
                int day_of_week = cal2.get(Calendar.DAY_OF_WEEK);
                    try {
                        calender_display(day_of_week ,no_of_days , currnt_day , Current_hall_Name );
                    } catch (BasicException ex) {
                        Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                    }
               if(unique > AdvanceBDT){
                   jButton22.setEnabled(false);
               }
       }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
      
        main_panel.setVisible(true);
        jTextField2.setText(null);
      hallListConbo2.setSelectedIndex(-1);
      
       Avail_Hall_frame.setVisible(false);
     
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
      
        
       main_panel.setVisible(true);
       Avail_Date_frame.setVisible(false);
       
       jButton19.setBackground(null);
       jTextField1.setText(null);
       jComboBox1.setSelectedIndex(-1);
       
       
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(jTable1.getSelectedColumn()!=-1){
           jButton1.setEnabled(true);
           jButton33.setEnabled(true);
           jButton37.setEnabled(true);
       }
       else{
           jButton1.setEnabled(false);
           jButton33.setEnabled(false);
           jButton37.setEnabled(false);
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       if(jTextField1.getText()!=null &&  jTextField1.getText().trim().length()>0){
           if(jComboBox1.getSelectedIndex()!=-1){
             
               String bDate = jTextField1.getText();
               String bHallName = jComboBox1.getSelectedItem().toString();
               
               for(int i=0;i< hallCombo.getItemCount();i++){
                   String x =  hallCombo.getItemAt(i).toString();
                   if(bHallName.equals(x)){
                       hallCombo.setSelectedIndex(i);
                       break;
                   }
                   
               }
              date_textfield.setText(bDate);
              status.setText("Available");
              main_panel.setVisible(true);
              jTabbedPane1.setSelectedIndex(1);
              Avail_Date_frame.setVisible(false);
           }
           else{
               JOptionPane.showMessageDialog(this, " Select hall ..!! ", " hall", JOptionPane.ERROR_MESSAGE);
           }
       }
       else{
           JOptionPane.showMessageDialog(this, " Select Booking Date First..!! ", " Date", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedIndex()!=-1){
            jButton19.setBackground(Color.GREEN);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
       if(hallListConbo2.getSelectedIndex()!=-1){
        main_panel.setVisible(true);
       Avail_Hall_frame.setVisible(false);
       jTabbedPane1.setSelectedIndex(1);
       }
       else{
           JOptionPane.showMessageDialog(this, " Select Hall to book..!! ", " Date", JOptionPane.ERROR_MESSAGE);
       }
            
    }//GEN-LAST:event_jButton21ActionPerformed

     Double Cancel_Charge=0.00;
     Double Refund_Amt = 0.00;
     String Booking_Id ;
     Double Advance_Recv=0.00 ;
     String TRANSREF;
     String NARRATION;
     String TID;
     String Rfd_Voucher_No;
     String  memberName_ID;
     String CancelReason;
     Double Cancel_Chrg_Perc = 0.00;
     Double Fix_Charge = 0.00;
     Double TotalAmt=0.00;
     
     String bookeddate;
     String Booked_slot;
     
    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        if(jTable1.getSelectedRow()!=-1){
         int cnl_req = JOptionPane.showConfirmDialog(jPanel2, "Do You Want to Cancel Request .. ?? " ,"Booking Cancellation",JOptionPane.YES_NO_OPTION );
           if(cnl_req == JOptionPane.YES_OPTION){
               
               
               
           if(jTable1.getSelectedRow()<BookHall_Status.getHallSize()){
               int row = jTable1.getSelectedRow();
                final HallStatusInfo showdata = BookHall_Status.getHallList().get(row);
               
               String Role = showdata.getROLE();                                                                        //Status 1 = Available;
               Booking_Id = showdata.getId();
               Non_Memmer_Name = showdata.getNON_MEM_NAME();
               HallName = showdata.gethall_name();
               TIMING_SLOT = showdata.getTIMING_SLOTS();
               
               MemberNo = showdata.getMem_No();
               BOOKINGDATE = showdata.getBOOKING_DATE();
               String CurrentRole = m_App.getAppUserView().getUser().getRole();
               TotAmt = showdata.getCHARGES();
               Booking_Seq_No = showdata.getBOOKING_SEQ_NO();
               MemberName = showdata.getMemberName();
               
               
               if(Role.equals(CurrentRole)){
                 if(showdata.getPAYMENT_FLAG()!=1 || showdata.getStatus()!=2){
                  
                     
                bookeddate = showdata.getBOOKING_DATE();
                Booked_slot = showdata.getTIMING_SLOTS();  
               Transaction t = new Transaction(m_App.getSession()) {
               String hallname_ID = showdata.getHall_ID();
               String memberName = showdata.getMEMBER_ID();
               
               
             
               @Override
               protected Object transact() throws BasicException {
                       
                       
                            try {
                               int update_hall_cancel_request =  new PreparedSentence(m_App.getSession(), "UPDATE hall_booked_details  SET STATUS=1 , FLAG=2 ,  REQ_CANCEL_BY=? , REQ_CANCEL_DATE=? , REQ_CANCEL_HOST=?   WHERE  ID =  ? "
                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  })).exec
                                                                   (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , Booking_Id });



                                int copy_hall_to_hall_cancel_request  =  new PreparedSentence(m_App.getSession(), "INSERT INTO hall_cancelled_details SELECT * FROM hall_booked_details    WHERE ID =  ?"
                                                                  , new SerializerWriteBasic(new Datas[]{ Datas.STRING })).exec
                                                                   (new Object[]{ Booking_Id }); 



                                int delete_From_hall_booked_details  =  new PreparedSentence(m_App.getSession(), "DELETE FROM  hall_booked_details  WHERE ID =  ? "
                                                                  , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                   (new Object[]{ Booking_Id }); 


                             

                           } catch (BasicException ex) {
                                ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                               Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       
                       
                       
                     return null;
                   }
               };
                   
               
               
               try {
                       t.execute();
                       JOptionPane.showMessageDialog(this, "Request Cancelled..! ", "Success", JOptionPane.INFORMATION_MESSAGE);  
                       loaddata();
                       
                   } catch (BasicException ex) {
                       Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                       new MessageInf(ex).show(new JFrame());
                   }
               
             
                                
                 }
                 else if(showdata.getPAYMENT_FLAG()==1 && showdata.getStatus()==2) {
                   
                  int cnl_req1 = JOptionPane.showConfirmDialog(jPanel2, "Advance Payment already done. Do you still want to continue..?? " , "Advance Payment" , JOptionPane.YES_NO_OPTION);
                   if(cnl_req1 == JOptionPane.YES_OPTION){   
                     
                    String hallname_ID = showdata.getHall_ID();
                    memberName_ID = showdata.getMEMBER_ID();
                     bookeddate = showdata.getBOOKING_DATE();
                     Booked_slot = showdata.getTIMING_SLOTS(); 
                    Booking_Id = showdata.getId();
                    Date Temp = new Date(); 
                    Date D ;
                    String Curr_D = Formats.DATE.formatValue(Temp);
                    List<Object> Days_list = new ArrayList<Object>();
                    int Final_days=0;
                    Object []Charges = null;
                    
                    
                    
                    int Check_in_Status = Payment_Status.getHallCheckInStatus(m_App, Booking_Id);
                    if(Check_in_Status == 0){
                        Advance_Recv = Payment_Status.getAdvance_Paid(m_App, Booking_Id);
                        Date bk_date = new Date();
                        Date Curr_date = new Date(); 
                        
                        try {
                            bk_date = (Date) Formats.TIMESTAMP.parseValue(bookeddate);
                            Curr_date = (Date) Formats.TIMESTAMP.parseValue(Curr_D);
                        } catch (BasicException ex) {
                            Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                      long  no_of_days = (long) ( bk_date.getTime()-Curr_date.getTime())/(1000 * 60 * 60 * 24);    
                      System.out.println(no_of_days);
                      
                      Days_list = Payment_Status.getHallCancellationOffer(m_App, hallname_ID);
                      for(int i=0;i<Days_list.size();i++){
                          int Min_day = Integer.parseInt(Days_list.get(i).toString());
                          
                          if(no_of_days<Min_day){
                              Final_days = Min_day;
                              break;
                          }
                      }
                      
                     Charges =  Payment_Status.getCan_ChargesFixed_Rates(m_App, hallname_ID, Final_days);
                     if(Charges!=null){
                         Cancel_Chrg_Perc = Double.parseDouble(Charges[0].toString());
                         Fix_Charge = Double.parseDouble(Charges[1].toString());
                         
                            //  TotalAmt = Payment_Status.getTotalAmount(m_App, Booking_Id);
                               TotalAmt = showdata.getCHARGES();
                               
                               
                                Double Temp_amt = ((TotalAmt*Cancel_Chrg_Perc)/100);
                                Cancel_Charge = Temp_amt+Fix_Charge;
                                
                                Cancel_Accnt_ID = CheckInTable_Model.getReve_Acct_Hall(m_App, hallname_ID);
                                UserAccnt =  m_App.getAppUserView().getUser().getcashaccount();
                                Advance_Acct_ID = CheckInTable_Model.getAdvance_Acct_Hall(m_App, hallname_ID);
                                
                                System.out.println(Cancel_Accnt_ID);
                                TID = UUID.randomUUID().toString();  
                                int cnl_req3 = JOptionPane.showConfirmDialog(jPanel2, " Your Cancellation Charge will be : "+decimalFormat.format(Cancel_Charge)+ "/- . Do you still want to continue ?? " , "Cancellation Request" , JOptionPane.YES_NO_OPTION);
                                if(cnl_req3 == JOptionPane.YES_OPTION){
                                    
                                   if(Cancel_Accnt_ID!=null && Cancel_Accnt_ID.trim().length()>0 && UserAccnt!=null && UserAccnt.trim().length()>0 && Advance_Acct_ID!=null && Advance_Acct_ID.trim().length()>0) {
                                                                        
                                   Refund_Amt = Advance_Recv - Cancel_Charge; 
                                   
                                   if(Refund_Amt>=0){
                                  try {
                                      Rfd_Voucher_No = getNextVoucherNo();     
                                   } catch (BasicException ex) {
                                           Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                    
                                 if(Rfd_Voucher_No!=null && Rfd_Voucher_No.trim().length()>0 && Rfd_Voucher_No!="")
                                 {
                                  
                                       
                                 Transaction t = new Transaction(m_App.getSession()) { 
                                   
                                       
                                       
                                   Double Amt_Adjusted = Cancel_Charge;
                                   Double Bal_amt = 0.00;
                                   String Advnce_Adjst_Ref = "";
                                   String Refund_Ref = "";
                                   String Cancel_Reason = "";
                                   
                                   
                                   
                                    @Override
                                    protected Object transact() throws BasicException {
                                        
                                    CancelReason = "Hall Cancelled after advance payment deducting cancellation charge of Rs."+decimalFormat.format(Cancel_Charge)+" /- .";     
                                   
                                    try {
                                     
                                                int Update_Advance_Against_Hall  =  new PreparedSentence(m_App.getSession(), "UPDATE advnce_agnst_hall  SET ADVNCE_ADJUST = ? , ADJUST_REF = ? ,BAL_AMT=? , REFUND_AMT =? , REFUND_REF=? , REFUND_BY=? , REFUND_DATE=? , REFUND_HOST=? , ACTIVE=0 WHERE BOOKING_ID =  ?"
                                                           , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE , Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  })).exec
                                                            (new Object[]{Amt_Adjusted ,Advnce_Adjst_Ref ,  Bal_amt , Refund_Amt , Refund_Ref ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , Booking_Id });
                                        
                                             
                                                int update_hall_cancel_request =  new PreparedSentence(m_App.getSession(), "UPDATE hall_booked_details  SET STATUS=1 , FLAG=2 , REQ_CANCEL_BY=? , REQ_CANCEL_DATE=? , REQ_CANCEL_HOST=? , CANCEL_REASON=? WHERE ID=? "
                                                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING , Datas.STRING})).exec
                                                                   (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , CancelReason, Booking_Id});



                                                int copy_hall_to_hall_cancel_request  =  new PreparedSentence(m_App.getSession(), "INSERT INTO hall_cancelled_details SELECT * FROM hall_booked_details   WHERE ID=? "
                                                                  , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                   (new Object[]{ Booking_Id }); 



                                                 int delete_From_hall_booked_details  =  new PreparedSentence(m_App.getSession(), "DELETE FROM  hall_booked_details  WHERE ID=? "
                                                                  , new SerializerWriteBasic(new Datas[]{ Datas.STRING  })).exec
                                                                   (new Object[]{ Booking_Id }); 

                                             
                                             // GENERATE REFUND VOUCHER..... #AAKASH
                                           
                                            
              
                                            int Refund_Voucher =  new PreparedSentence(m_App.getSession(), "INSERT INTO room_hall_refund_voucher (ID , RV_NO , CUST_ID , MEM_NO , BOOKING_SEQ_NO ,BILLED_AMT , ADVNCE_AMT , REFUND_AMT , CHK_IN_ID , REFUND_BY  , REFUND_HOST ,  REFUND_DATE , CANCELLED , ROOMTYPE ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                                                 , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING  ,  Datas.STRING , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING  , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.INT , Datas.STRING})).exec
                                                                (new Object[]{UUID.randomUUID().toString() ,Rfd_Voucher_No , memberName_ID , MemberNo , Booking_Seq_No ,TotAmt , Advance_Recv ,Refund_Amt  , Booking_Id , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() , new Date() , 1 , HallName }); 

                                                 
                                         
                                              // ACCOUNTING ENTRIES..... 
                                             TRANSREF = MemberNo + " , Refund Voucher No : "+Rfd_Voucher_No+" # amt :"+Refund_Amt+" /- for Hall cancellation created againts booking no: "+Booking_Seq_No ;     
                                             NARRATION = "Refund of "+Refund_Amt+ " /- to member : "+MemberNo+" . Voucher no "+Rfd_Voucher_No+ " , for hall cancellation";
                                             
                                             
                                             int  INSERT_INTO_ACCOUNT5  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_Seq_No ,Refund_Amt , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , UserAccnt , TID , new Date() , "C" , 1    });                                                                                                

                                             
                                             int   INSERT_INTO_ACCOUNT1  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                                            ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_Seq_No ,Advance_Recv , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Advance_Acct_ID , TID , new Date() , "D" , 1    });                                                                                                

                                             
                                             
                                             
                                             
                                             int  INSERT_INTO_ACCOUNT2  =  new PreparedSentence(m_App.getSession()  , "INSERT INTO accountjournal (ID ,date , TRANSREF , TRANSNO , AMOUNT , DUEDATE , BALANCEAMOUNT ,ADJUSTED , CREATEDBY , COUNTER , NARRATION , ACCOUNTID , TID , DATEOFENTRY , TRANSTYPE , ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                                                , new SerializerWriteBasic(new Datas[]{Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.DOUBLE ,Datas.TIMESTAMP , Datas.DOUBLE , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING , Datas.INT})                         
                                                                                ).exec(new Object[]{ UUID.randomUUID().toString()  , new Date() , TRANSREF , Booking_Seq_No ,Cancel_Charge , new Date() , 0.00 , 1 , m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost() ,NARRATION  , Cancel_Accnt_ID , TID , new Date() , "C" , 1    });                                                                                                

                                             
                                             
                                    
                                    
                                    } catch (BasicException ex) {
                                         ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());
                                            Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    
                       
                                            return null;
                                          }
                                      };

                                  

                                      try {
                                              t.execute();
                                              JOptionPane.showMessageDialog(this, " Hall Cancelled Successfully..! ", "Success", JOptionPane.INFORMATION_MESSAGE); 
                                             // generateRefundVoucher();
                                              PrintRefundReciept(Booking_Id , Cancel_Chrg_Perc );
                                              
                                              UpdateRefundVoucher();
                                              loaddata();
                                             
                                              String MobNo = dmang.getcustMobileNoByCustID(memberName_ID);
                                              String Msg = "Dear Member,Cancellation of "+showdata.gethall_name()+" has charged Rs."+Cancel_Charge+"/- from advnce recvd of Rs."+Advance_Recv+"/-";
                                              System.out.println("Cancel msg = "+Msg.length());
                                              if(MobNo!=null && MobNo.trim().length()>0){
                                                    dmang.InsertActiveMsgTable(Msg, memberName_ID , MobNo, 2);
                                              }
                                              
                                              

                                          } catch (BasicException ex) {
                                              Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                                              new MessageInf(ex).show(new JFrame());
                                          }
                                      
                                    }
                                      
                                      
                                 
                                   }
                                   else{
                                        JOptionPane.showMessageDialog(this, "Advance Amount Recieved is Less than cancellation charge. \n Please pay remaining amount for cancellation..!!  ", " Date", JOptionPane.ERROR_MESSAGE);    
                                     }
                                   }
                                   else{
                                        JOptionPane.showMessageDialog(this, " Account Cannot be null..! \n Select Account  ", " Date", JOptionPane.ERROR_MESSAGE);    
                                   }
                            
                       }
                     
                         
                         
                         
                     }
                     else{
                          Cancel_Chrg_Perc = 0.00;
                          Fix_Charge = 0.00;  
                          
                          JOptionPane.showMessageDialog(this, "Cancellation Rate Is not Defined for "+HallName+"."+" Define Cancellation Rate first..!! ", " Date", JOptionPane.ERROR_MESSAGE);    
                          
                          
                     }
                    
                          
                         
                          
                     
                    
                    
                     }    
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Member Had already Checked In. Cannot Cancel..!! ", " Date", JOptionPane.ERROR_MESSAGE);  
                        
                    }
                 }
                 else{
                     
                     
                     
                     
                    JOptionPane.showMessageDialog(this, "Cannot Cancel request !! ", " Date", JOptionPane.ERROR_MESSAGE);
               }
               
               }
               else{
                    JOptionPane.showMessageDialog(this, " Permission Denied.!! ", " Date", JOptionPane.ERROR_MESSAGE);
               }
               
           }
        }
     }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
         cancel_panel.setVisible(true);
         main_panel.setVisible(false);
                
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        cancel_panel.setVisible(false);
        main_panel.setVisible(true);
        
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        if(jTable2.getSelectedRow()!=-1){
           if(jTable2.getSelectedRow()<Canceled_request.getCancelRequestHallSize()){
               int row = jTable2.getSelectedRow();
               Cancel_requestInfo showCanceldata = Canceled_request.getHallCanceledList().get(row);
               
               mName.setText(showCanceldata.getMemberName());
               memberNo.setText(showCanceldata.getMem_No());
               Adress.setText(showCanceldata.getNON_MEM_ADDR());
               NName.setText(showCanceldata.getNON_MEM_NAME());
               contactNo.setText(""+showCanceldata.getNON_MEM_CONTCT());
               hallName.setText(showCanceldata.gethall_name());
               capacity.setText(""+showCanceldata.getMAX_CAPACITY());
               tariff.setText(decimalFormat.format(showCanceldata.getCHARGES()));
               tax_1.setText(showCanceldata.getLUXURYTAX());
               tax_2.setText(showCanceldata.getTAX2());
               tax_3.setText(showCanceldata.getTAX3());
               bookingDate.setText(showCanceldata.getBOOKING_DATE());
               bookingSlot.setText(showCanceldata.getTIMING_SLOTS());
               
               Date Blocking_date = showCanceldata.getBLOCKED_DATE();
               int Payment_days = showCanceldata.getPAYMENT_DAYS();
               Calendar c = Calendar.getInstance();
               c.setTimeInMillis(Blocking_date.getTime());
               c.add(Calendar.DATE, Payment_days);
               Date final_payment_date = c.getTime();
               
               Date Current_date = new Date();
               int No_of_days_left = (int) (final_payment_date.getTime() - Current_date.getTime())/(1000 * 60 * 60 * 24);
               
               String Final_date = Formats.DATE.formatValue(final_payment_date);
               
               payment_label.setText(Final_date);
               
             
               
               
               
              
               
               hallDetails_panel.setVisible(true);
               cancel_panel.setVisible(false);
               jButton16.setVisible(false);
               Submit.setVisible(false);
               request_cln_btn.setVisible(true);
               
               jPanel5.setVisible(true);
                jLabel29.setVisible(true);
                note_label.setVisible(true);
               String Last_Pay_Date = Formats.DATE.formatValue(showCanceldata.getLAST_PAYMENT_DATE());
               
               message.setVisible(false);
               message1.setVisible(false);
               Double Basic_charge = showCanceldata.getCHARGES();
               
               Total_Charge = 0.00;
               
               Total_Charge = Total_Charge + Basic_charge;
               basicTax = showCanceldata.getBASIC();
               basicTax2 = showCanceldata.getBASIC2();
               
                 if(showCanceldata.getTax1_Rate()!=null ) {
                 Double Tax1 = showCanceldata.getTax1_Rate()*Basic_charge;
                 tax1_rate.setText(decimalFormat.format(showCanceldata.getTax1_Rate()*Basic_charge));
                 Total_Charge = Total_Charge + Tax1;
                 }
                else{
                   tax1_rate.setText("");
                 }
                 
                 
               if(showCanceldata.getTax2_Rate()!=null ) {
                  if(basicTax==1){ 
                    Double Tax2 = showCanceldata.getTax2_Rate()*Basic_charge;
                    tax2_rate.setText(decimalFormat.format(showCanceldata.getTax2_Rate()*Basic_charge));
                    Total_Charge = Total_Charge + Tax2;
                    basic_tax_label1.setText("(B)");
                  }
                  else{
                    Double Tax2 = showCanceldata.getTax2_Rate()*Total_Charge;
                    tax2_rate.setText(decimalFormat.format(showCanceldata.getTax2_Rate()*Total_Charge));
                    Total_Charge = Total_Charge + Tax2;
                    basic_tax_label1.setText("(C)");
                  }
               }
              else{
                 tax2_rate.setText("");
                   basic_tax_label1.setText("");
              }
               
               
              
               if(showCanceldata.getTax3_Rate()!=null ) {
                   if(basicTax2==1){
                        Double Tax3 = showCanceldata.getTax3_Rate()*Basic_charge;
                        tax3_rate.setText(decimalFormat.format(showCanceldata.getTax3_Rate()*Basic_charge));
                        Total_Charge = Total_Charge + Tax3;
                        basic_tax_label2.setText("(B)");
                   }
                   else{
                        Double Tax3 = showCanceldata.getTax3_Rate()*Total_Charge;
                        tax3_rate.setText(decimalFormat.format(showCanceldata.getTax3_Rate()*Total_Charge));
                        Total_Charge = Total_Charge + Tax3;
                        basic_tax_label2.setText("(C)");
                   }
                   
               }
               else{
                   tax3_rate.setText("");
                   basic_tax_label2.setText("");
               }
               
               total_charges_label.setText(decimalFormat.format(Total_Charge));
               
               
               
               if(showCanceldata.getMem_flag()==1){
                   mem_label.setText("( Booked By:- Member )");
               }
               else{
                    mem_label.setText("( Booked By:- Non-Member )");
                 }
               
               if(showCanceldata.getPAYMENT_FLAG()==2){
                   message.setText("* Sorry !  Request had not approved. ");
                   message1.setText("* Payment not recieved in given time period. Last Day For Payment was : "+Last_Pay_Date);
                 
                   message.setVisible(true);
                   message1.setVisible(true); 
               }
              
               if(showCanceldata.getFlag()==2){
                   
                   String id = showCanceldata.getId();
                   String can_note = BookHall_Status.getCancellation_Reason(m_App, id);
                   note_label.setText(can_note);
                   message.setText("Request Cancel by :- "+showCanceldata.getREQ_CANCEL_BY()+ " on "+showCanceldata.getREQ_CANCEL_DATE()+" from "+showCanceldata.getREQ_CANCEL_HOST()+".");
                   note_label.setText(showCanceldata.getCancel_Note());
                   message.setVisible(true);
                   message1.setVisible(false);   
                   
                 }
               
                if(showCanceldata.getSLOT_FLAG()==1){
                   slot_label.setText("(Hourly Booked)");
                  }
                if(showCanceldata.getSLOT_FLAG()==2){
                   slot_label.setText("(Half Day)");
                  }
                if(showCanceldata.getSLOT_FLAG()==3){
                   slot_label.setText("(Full Day)");
                 }
      
           }
        }
        else{
            jButton35.setEnabled(false);
        }
        
    }//GEN-LAST:event_jButton35ActionPerformed

    private void request_cln_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_request_cln_btnActionPerformed
       request_cln_btn.setVisible(false);
       hallDetails_panel.setVisible(false);
       cancel_panel.setVisible(true);
       Total_Charge=0.00;
    }//GEN-LAST:event_request_cln_btnActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if(jTable2.getSelectedColumn()!=-1){
          jButton35.setEnabled(true);
       }
       else{
          jButton35.setEnabled(false);
       }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
          if(jTable1.getSelectedRow()!=-1){
           if(jTable1.getSelectedRow()<BookHall_Status.getHallSize()){
               int row = jTable1.getSelectedRow();
               HallStatusInfo showdata = BookHall_Status.getHallList().get(row);
               if(showdata.getFlag()==1){
                   if(showdata.getPAYMENT_FLAG()!=1 && showdata.getStatus()!=2){
                       
                       
                       
                        this.setFocusable(false);
                        Billpage bp = new Billpage(showdata , 0);
                       try {
                           bp.showDialog();
                       } catch (BasicException ex) {
                           Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                        
                       
                   }
                   else if(showdata.getPAYMENT_FLAG()==1 && showdata.getCHK_IN_FLAG()==0){
                       
                        int cnl_req = JOptionPane.showConfirmDialog(jPanel2, " Payment Already done..  \n Do you want to make another Payment ?? " ," Advance Payment..! ",JOptionPane.YES_NO_OPTION );
                         if(cnl_req == JOptionPane.YES_OPTION){
               
                       
                                this.setFocusable(false);
                                Billpage bp = new Billpage(showdata , 1);
                            try {
                                bp.showDialog();
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         }
                       
                   }
                   else if(showdata.getPAYMENT_FLAG()==1 && showdata.getCHK_IN_FLAG()==1){
                       
                        
                        int cnl_req = JOptionPane.showConfirmDialog(jPanel2, " Member had  already checkedIn. \n   \n Do you want to make another Payment ?? " ," Advance Payment..! ",JOptionPane.YES_NO_OPTION );
                         if(cnl_req == JOptionPane.YES_OPTION){
               
                       
                                this.setFocusable(false);
                                Billpage bp = new Billpage(showdata , 1);
                            try {
                                bp.showDialog();
                            } catch (BasicException ex) {
                                Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         }
                       
                       
                       
                   }
                   
                    else{
                        JOptionPane.showMessageDialog(this, "Payment already done..!", " Payment", JOptionPane.ERROR_MESSAGE);
                   }
                    
               }
               else{
                   
                    JOptionPane.showMessageDialog(this, " Request is Still Pending. Wait for approval.", " Request Approval", JOptionPane.ERROR_MESSAGE);
                  
              }
               
           }
       }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void hallComboComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_hallComboComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_hallComboComponentHidden

    private void print_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_btnActionPerformed
       PrintBlockingDetails();
    }//GEN-LAST:event_print_btnActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
            cardno_text.requestFocus();
            cardno_text.setText(null);
            memno_text.setText(null);
            mname_text.setText(null);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void cardno_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardno_textActionPerformed
    String custoid;
    String cust = cardno_text.getText().trim();
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
            customerInfo = m_dlSales.loadCustomerExt(custoid);
            
            memno_text.setText(obj[1].toString());
            mname_text.setText(obj[2].toString());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }    
//akshatha:to read a card from card reader without port num
   // cardno_text.transferFocus();
    }//GEN-LAST:event_cardno_textActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Adress;
    private javax.swing.JPanel Avail_Date_frame;
    private javax.swing.JPanel Avail_Hall_frame;
    private javax.swing.JLabel ID;
    private javax.swing.JLabel Month_label;
    private javax.swing.JLabel Month_year;
    private javax.swing.JLabel NName;
    private javax.swing.JTextField N_luxuryTax;
    private javax.swing.JTextField N_tax2;
    private javax.swing.JTextField N_tax3;
    private javax.swing.JButton Save;
    private javax.swing.JButton Submit;
    private javax.swing.JTextField advanceBookedDura;
    private javax.swing.JTextField advanceBookingDura;
    private javax.swing.JLabel basic_tax_label1;
    private javax.swing.JLabel basic_tax_label2;
    private javax.swing.JLabel basic_tax_label3;
    private javax.swing.JLabel block_flag_label;
    private javax.swing.JTextField block_from_text;
    private javax.swing.JTextField block_upto_text;
    private javax.swing.JButton book_hall_btn;
    private javax.swing.JLabel bookingDate;
    private javax.swing.JLabel bookingSlot;
    private javax.swing.JLabel booking_status_label;
    private javax.swing.JButton btnIcon;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel cancel_panel;
    private javax.swing.JLabel capacity;
    private javax.swing.JPasswordField cardno_text;
    private javax.swing.JButton close;
    private javax.swing.JLabel contactNo;
    private javax.swing.JTextField date_textfield;
    private javax.swing.JTextField floor;
    private javax.swing.JTextField fullDay;
    private javax.swing.JTextField fullDay1;
    private javax.swing.JComboBox fullDay_combo;
    private javax.swing.JRadioButton fullDay_radio;
    private javax.swing.JTextField halfDay;
    private javax.swing.JTextField halfDay1;
    private javax.swing.JComboBox halfDay_combo;
    private javax.swing.JRadioButton halfDay_radio;
    private javax.swing.JComboBox hallCombo;
    private javax.swing.JPanel hallDetails_panel;
    private javax.swing.JComboBox hallListConbo2;
    private javax.swing.JLabel hallName;
    private javax.swing.JLabel hall_ID;
    private javax.swing.JRadioButton hour_radio;
    private javax.swing.JTextField hourly;
    private javax.swing.JTextField hourly1;
    private javax.swing.JComboBox hourly_combo;
    private javax.swing.JLabel iconLabel;
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
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
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
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel layout_panel;
    private javax.swing.JTextField luxuryTax;
    private javax.swing.JLabel mName;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTextField max_Capacity;
    private javax.swing.JLabel mem_label;
    private javax.swing.JRadioButton member;
    private javax.swing.JLabel memberNo;
    private javax.swing.JLabel member_label;
    private javax.swing.JPanel member_panel;
    private javax.swing.JPanel member_timing_main_panel;
    private javax.swing.JPanel membersDetails_frame;
    private javax.swing.JTextField memno_text;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message1;
    private javax.swing.JTextField mname_text;
    private javax.swing.JTextArea non_M_Address;
    private javax.swing.JTextField non_M_ContactNo;
    private javax.swing.JPanel non_mem_details_panel;
    private javax.swing.JRadioButton non_member;
    private javax.swing.JTextField non_member_name;
    private javax.swing.JPanel non_member_panel;
    private javax.swing.JTextArea note_label;
    private javax.swing.JLabel payment_label;
    private javax.swing.JLabel pending_label;
    private javax.swing.JButton print_btn;
    private javax.swing.JButton request_cln_btn;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JTextField slot_TF;
    private javax.swing.JLabel slot_label;
    private javax.swing.JTextField status;
    private javax.swing.JLabel tariff;
    private javax.swing.JPanel tariff_panel;
    private javax.swing.JLabel tax1_ID_label;
    private javax.swing.JLabel tax1_rate;
    private javax.swing.JTextField tax2;
    private javax.swing.JLabel tax2_id_label;
    private javax.swing.JLabel tax2_rate;
    private javax.swing.JTextField tax3;
    private javax.swing.JLabel tax3_id_label;
    private javax.swing.JLabel tax3_rate;
    private javax.swing.JLabel tax_1;
    private javax.swing.JLabel tax_2;
    private javax.swing.JLabel tax_3;
    private javax.swing.JComboBox temp_combo1;
    private javax.swing.JComboBox temp_combo2;
    private javax.swing.JComboBox temp_combo3;
    private javax.swing.JComboBox temp_combo4;
    private javax.swing.JComboBox temp_combo5;
    private javax.swing.JComboBox temp_combo6;
    private javax.swing.JPanel timingFrame;
    private javax.swing.JLabel total_charges_label;
    private javax.swing.JLabel uniqueNo;
    private javax.swing.JLabel year_Label;
    // End of variables declaration//GEN-END:variables

    
    int CardAccessFlag=0;
    
    public void loaddata() throws BasicException{
            
        //hallBooking = BookHallTableModel.loadInstanceHallInfo(m_App);    
       // showHallAvailibility(hallBooking);    
       
        reset();  
       
         try{
             Hall = hallTableModel.loadInstanceHallInfo(m_App);
         }
         catch (BasicException ex) {
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        halllist = (List<hallTableModel.HallTableInfo>) Hall.getHallPath();
        if(halllist==null){
             halllist = new ArrayList<hallTableModel.HallTableInfo>();
        }
        
        
        HallListModel  = new ComboBoxValModel(halllist);
         hallCombo.setModel(HallListModel);    
         hallListConbo2.setModel(HallListModel);
         
        
          Payment_Option();
         
         Date curr_date;
         curr_date = new Date();
         Calendar c = Calendar.getInstance();
         c.setTimeInMillis(new Date().getTime());
         c.add(Calendar.DATE, -1);
         curr_date = c.getTime();
         
         BookHall_Status = BookedHallStatusTableModel.loadInstanceBooked_Hall_Status(m_App ,curr_date );
         Canceled_request =  BookedHallStatusTableModel.load_CancelRequest(m_App);
         
         int pending_list = BookHall_Status.getPending_ReqList(m_App);
         if(pending_list>0){
              pending_label.setVisible(true);
               pending_label.setText("( * "+pending_list+" Request is pending. )");
         }
         else{
              pending_label.setVisible(false);
         }
         showPanelInfo(BookHall_Status);
         ShowCancelHallPanel(Canceled_request);
         
         
         
         book_hall_btn.setBackground(Color.RED);
       
         
         
// CODE ADDED FOR CATD ACCESSSSS  
         
                    Object[] obj15 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Party Hall Card Access Flag'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                    if(obj15!=null){
                         Boolean v15 = (Boolean)obj15[0];
                         if(v15){
                            memno_text.setEditable(false);
                            jButton14.setVisible(false);
                         }
                         else{
                             memno_text.setEditable(true);
                             jButton14.setVisible(true);
                         }
                         
                        
                     }
                     else{
                           memno_text.setEditable(false);
                           jButton14.setVisible(false);
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
         dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
         Payment_Status = (BookedHallStatusTableModel) app.getBean("com.openbravo.pos.Booking.BookedHallStatusTableModel");
         m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         CheckInTable_Model = (CheckInTableModel) app.getBean("com.openbravo.pos.Booking.CheckInTableModel");
    }

    public Object getBean() {
       return this;
    }
    
    
     private void memberSelect() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(member);
        bg1.add(non_member);
     ButtonGroup bg2 = new ButtonGroup();
        bg2.add(hour_radio);
        bg2.add(halfDay_radio);
        bg2.add(fullDay_radio);
  
    }
     
     
     Component comp = null;
     
     public void showPosition(int X , int Y , Icon ic , Icon flr){
         iconLabel.setIcon(flr);
         btnIcon.setIcon(ic);
         iconLabel.add(btnIcon);
         btnIcon.setLocation(X, Y);
         
        
     }
     
     
     public void reset(){
         
         jTabbedPane1.setSelectedIndex(0);
         floor.setText(null);
         max_Capacity.setText(null);
         status.setText(null);
         date_textfield.setText(null);
         slot_TF.setText(null);
         hallCombo.setSelectedIndex(-1);
          Avail_Date_frame.setVisible(false);
          jButton1.setEnabled(false);
          jButton33.setEnabled(false);
          Avail_hall_List_for_book = new ArrayList<String>();
           message.setVisible(false);
          main_panel.setVisible(true);
          image_panel.setVisible(false);
          message1.setVisible(false);
          book_hall_btn.setBackground(Color.red);
          cancel_panel.setVisible(false);
          hallDetails_panel.setVisible(false);
        //  slot_message.setVisible(false);
          cardno_text.setText(null);
          Avail_Hall_frame.setVisible(false);
          
          timingFrame.setVisible(false);
          
          tax1_rate.setText(null);
          tax2_rate.setText(null);
          tax3_rate.setText(null);
          Total_Charge = 0.00;
          total_charges_label.setText("");
          
          tariff_panel.setVisible(false);
          image_panel.setVisible(false);
          layout_panel.setVisible(false);
          timingFrame.setVisible(false);
          membersDetails_frame.setVisible(false);
          hallDetails_panel.setVisible(false);
          Avail_Date_frame.setVisible(false);
            
          Avail_Hall_frame.setVisible(false);
          cancel_panel.setVisible(false);
            
     }
     
     
     
     public void showPanelInfo(BookedHallStatusTableModel bookedHall){
          jTable1.setModel(bookedHall.getTableModel());
       }
     
      public void ShowCancelHallPanel(BookedHallStatusTableModel bookedHall){
          jTable2.setModel(bookedHall.getCancelRequestTable());
       }
     
       public void showHallAvail_Btns(List<Object> hallList , List<Object> BookedHallList , List<Object> Blocked_halls ){
        
                   int hallListSize = hallList.size();
                   int BookedHallSize = BookedHallList.size();
                   List<Object> AvailHallList = new ArrayList<Object>();
                   Collection<Object> c = hallList;
                   AvailHallList.addAll(c);
                   AvailHallList.removeAll(BookedHallList);
                   AvailHallList.removeAll(Blocked_halls);
                 
                   Blocked_halls.removeAll(BookedHallList);
                   
                   jScrollPane4.getViewport().setView(null);
                    JFlowPanel jPeople = new JFlowPanel();
                   jPeople.applyComponentOrientation(getComponentOrientation());
                   
                   for(int i=0 ; i< AvailHallList.size() ; i++){
                    
                    String hallName =  AvailHallList.get(i).toString();
                    String bookDate = jTextField1.getText();
                    
                    JButton btn = new JButton(new HallAction(hallName));
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(100, 100));
                    btn.setPreferredSize(new Dimension(100, 100));
                    btn.setMinimumSize(new Dimension(100, 100));
                    btn.setBackground(Color.GREEN);
                    jPeople.add(btn);
                   
                   }
                   
                   
                     for(int i=0 ; i< Blocked_halls.size() ; i++){
                    
                    String hallName =  Blocked_halls.get(i).toString();
                    String bookDate = jTextField1.getText();
                    
                    JButton btn = new JButton(new HallAction(hallName));
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(100, 100));
                    btn.setPreferredSize(new Dimension(100, 100));
                    btn.setMinimumSize(new Dimension(100, 100));
                    btn.setBackground(myOrange);
                    jPeople.add(btn);
                   
                   }
                   
                   
                   
                   
                    for(int i=0 ; i< BookedHallList.size() ; i++){
                   
                    String BookedHall =  BookedHallList.get(i).toString();
                    String bookDate = jTextField1.getText();
                    
                    JButton btn = new JButton(new HallAction(BookedHall));
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(100, 100));
                    btn.setPreferredSize(new Dimension(100, 100));
                    btn.setMinimumSize(new Dimension(100, 100));
                    
                    int Status = BookHall_Status.check_Status(bookDate , BookedHall , m_App);
                    int PartialBkng = BookHall_Status.getSlotBookingState(bookDate, BookedHall, m_App);
                             btn.setBackground(myOrange);       
                                    if(Status == 2){
                                        List<Object> hourly_Booked_Slot_list = new ArrayList<Object>();
                                        List<Object> half_day_Booked_slot = new ArrayList<Object>();
                                        for(int z=0;z<hallCombo.getItemCount();z++){
                                            String t = hallCombo.getItemAt(z).toString();
                                            if(BookedHall.equals(t)){
                                                hallCombo.setSelectedIndex(z);
                                                break;
                                            }
                                        }
                                        
                                        hourly_Booked_Slot_list =   BookHall_Status.getBooking_Timings(bookDate, BookedHall , m_App);
                                         half_day_Booked_slot =  BookHall_Status.getHalfDay_Booking_Timings(bookDate, BookedHall, m_App);
                                         
                                         
                                        if(hourly_Booked_Slot_list.size() < M_hourly_Slots.size() && half_day_Booked_slot.size()< M_halfDay_Slots.size() ){
                                             btn.setBackground(Color.YELLOW);
                                        }
                                        else{
                                             btn.setBackground(Color.RED);
                                        }
                                            
                                            
                                            
                                            
                                            
                                      //  if(PartialBkng==1 || PartialBkng==2){
                                       //     btn.setBackground(Color.YELLOW);
                                       // } 
                                      //  else{
                                       //     btn.setBackground(Color.RED);
                                       // }
                                        
                                        
                                        
                                        
                                       
                                    }
                                    if(Status==3){
                                        btn.setBackground(myOrange);
                                       
                                    }
                                    if(Status==4){
                                        btn.setBackground(Color.YELLOW);
                                       
                                    }
                    
                    
                   
                    jPeople.add(btn);
                        
                    }
                   jScrollPane4.getViewport().setView(jPeople);
                   
                   
                   getAvailable_hall_list_for_booking(AvailHallList);
        }
    
       public void showCalender(String Current_hall_Name) throws BasicException {
           
         
           
           
           // Displaying calender ..!!! 
           
           Calendar call = Calendar.getInstance();
           call.setTimeInMillis(new Date().getTime());
              Date currentDate = call.getTime();
              int currnt_month = call.get(Calendar.MONTH)+1;
              Month_label.setText(""+currnt_month);
              String Currnt_month_name = getMonth(currnt_month);
              int currnt_year = call.get(Calendar.YEAR);
              year_Label.setText(""+currnt_year);
              int no_of_days = call.getActualMaximum(Calendar.DAY_OF_MONTH);
              int currnt_day = call.get(Calendar.DAY_OF_MONTH);
              int day_of_week = call.get(Calendar.DAY_OF_WEEK);
              
              uniqueNo.setText(""+1);
              jButton22.setEnabled(true);
              jButton23.setEnabled(false);
              Month_year.setText(Currnt_month_name+" "+currnt_year);
           
              calender_display(day_of_week , no_of_days ,  currnt_day , Current_hall_Name);
       }
       
       
       
       public void calender_display(int day_of_week , int no_of_days , int currnt_day  , String Current_hall_Name) throws BasicException{
             
           
           List  BookedDates =new ArrayList();
           
           int month = Integer.parseInt(Month_label.getText())-1;
           int year = Integer.parseInt(year_Label.getText());
           BookedDates = getBookedDates(month);
           int Booked_date_array[] = new int[BookedDates.size()];
           for(int i=0;i<BookedDates.size();i++){
               Booked_date_array[i] = Integer.parseInt(BookedDates.get(i).toString());
           }
           
              jScrollPane5.getViewport().setView(null);
              JFlowPanel jPeople = new JFlowPanel();
              jPeople.applyComponentOrientation(getComponentOrientation()); 
              
                for(int i=0;i<7;i++){
                 String WeekDay  = getWeekDays(i);
                   JButton btn = new JButton(WeekDay);
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(80, 50));
                    btn.setPreferredSize(new Dimension(80, 50));
                    btn.setMinimumSize(new Dimension(80, 50));
                    btn.setBackground(Color.darkGray);
                    btn.setForeground(Color.MAGENTA);
                    jPeople.add(btn);

                }
              int dayWeek = 1;
              while(dayWeek<=day_of_week-1){
                   JButton btn = new JButton("");
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(80, 50));
                    btn.setPreferredSize(new Dimension(80, 50));
                    btn.setMinimumSize(new Dimension(80, 50));
                    btn.setBackground(Color.WHITE);
                    btn.setBorder(null);
                    
                    
                    jPeople.add(btn);
                     dayWeek++;  
                }
              
              for(int i=currnt_day ; i<=no_of_days ; i++){
                    
                  
                    JButton btn = new JButton(new DateAction(""+i));
                    
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(80, 50));
                    btn.setPreferredSize(new Dimension(80, 50));
                    btn.setMinimumSize(new Dimension(80, 50));
                    
                    int Block_Flag = BookHall_Status.getBlockFlag(Current_hall_Name, m_App);
                    
                    for(int x=0;x<Booked_date_array.length;x++){
                    if( Booked_date_array[x] == i){
                    int CurrStatus = getCurrentStatus(i ,month , year );
                    
                    
                    int CurrPartialStatus = getBookingSlotStatus(i, month, year);
                    
                    
                                    if(CurrStatus == 2){
                                        
                                        
                                        
                                        List<Object> hourly_Booked_Slot_list = new ArrayList<Object>();
                                        List<Object> half_day_Booked_slot = new ArrayList<Object>();
                                        
                                        
                                        hourly_Booked_Slot_list = getBooking_Timings(i, month , year);
                                        half_day_Booked_slot =   getHalfDay_Booking_Timings(i, month , year);
                                         
                                         
                                        if(hourly_Booked_Slot_list.size() < M_hourly_Slots.size() && half_day_Booked_slot.size()< M_halfDay_Slots.size() ){
                                             btn.setBackground(Color.YELLOW);
                                        }
                                        else{
                                             btn.setBackground(Color.RED);
                                        }
                                       
                                         break;
                                     
                                         
                                         
                                    }
                                    if(CurrStatus == 3){
                                        btn.setBackground(myOrange);
                                         break;
                                    }
                                    if(CurrStatus== 4){
                                        btn.setBackground(Color.YELLOW);
                                         break;
                                    }
                    
                    
                    
                   // btn.setBackground(Color.red);
                         
                    }
                    else{
                      //  btn.setBackground(Color.LIGHT_GRAY);
                    }
                    }
                    
                    if(Block_Flag==1){
                        
                       int CurrStatus = getCurrentStatus1(i ,month , year ); 
                        
                       if(CurrStatus==3){
                           btn.setBackground(myOrange);   
                           
                       } 
                        
                    }
                    
                    
                   
                 jPeople.add(btn);
                   }
                  
                jScrollPane5.getViewport().setView(jPeople);
              
              
               }
      
       
     
       
       
       public String getMonth(int month){
        String monthString;
        switch (month) {
            case 1:  monthString = "January";       break;
            case 2:  monthString = "February";      break;
            case 3:  monthString = "March";         break;
            case 4:  monthString = "April";         break;
            case 5:  monthString = "May";           break;
            case 6:  monthString = "June";          break;
            case 7:  monthString = "July";          break;
            case 8:  monthString = "August";        break;
            case 9:  monthString = "September";     break;
            case 10: monthString = "October";       break;
            case 11: monthString = "November";      break;
            case 12: monthString = "December";      break;
            default: monthString = "Invalid month"; break;
        }
         return   monthString;
           
       }
     public String getWeekDays(int Days){
        String WeekDays;
        switch (Days) {
            case 0:  WeekDays = "Sun";       break;
            case 1:  WeekDays = "Mon";      break;
            case 2:  WeekDays = "Tues";         break;
            case 3:  WeekDays = "Wed";         break;
            case 4:  WeekDays = "Thur";           break;
            case 5:  WeekDays = "Fri";          break;
            case 6:  WeekDays = "Sat";          break;
           
            default: WeekDays = "Invalid month"; break;
        }
         return   WeekDays;
           
       }
     
     
     
     public List getBookedDates(int monthReq){
         
          String Current_hall_Name = hallListConbo2.getSelectedItem().toString();
         
         List<Object> BookeddatesByHall  = new ArrayList<Object>();
         BookeddatesByHall = BookHall_Status.DateList_by_HallName(m_App, Current_hall_Name);
         Calendar tempCal = Calendar.getInstance();
       //  Calendar Maincal = Calendar.getInstance();
         
         int size = BookeddatesByHall.size();
         String[] str = new String[size];
     //    Date[] Curr_Date = new Date[size];
         
          List  dateList =new ArrayList();
         
      
         
         
         String[] Tempstr = new String[size];
         Date[] TempDate = new Date[size];
        
     //    int count = 0;
         
         for(int y=0;y<size;y++){
            
            Tempstr[y] = BookeddatesByHall.get(y).toString();
            try{
                TempDate[y] = (Date) Formats.DATE.parseValue(Tempstr[y]);
            }
            catch (BasicException ex) {
                  Logger.getLogger(BookHall.class.getName()).log(Level.SEVERE, null, ex);
              }
            tempCal.setTime(TempDate[y]);
            int temp_mnth = tempCal.get(Calendar.MONTH);
            if(temp_mnth == monthReq){
                int  dateFound = tempCal.get(Calendar.DATE);
                dateList.add(0,dateFound);
            }
         }
         
          return  dateList;
            
            
     }
     
     public int getCurrentStatus(int day , int month , int year ){
         int currentStatus = 0;
        String hall = hallListConbo2.getSelectedItem().toString();
        Calendar c1 = Calendar.getInstance();
        c1.set(year, month, day);
        Date sDate = c1.getTime();
        String temp = Formats.DATE.formatValue(sDate); 
        currentStatus =  BookHall_Status.check_Status(temp , hall , m_App);
         
        if(currentStatus==0){
            int Block_Flag = BookHall_Status.getBlockFlag(hall, m_App);
            
            if(Block_Flag==1){
                
                Date From_Date = Hall.getBlock_From_Date(hall, m_App);
                Date To_Date =Hall.getBlock_To_Date(hall, m_App);
                
                int No_of_days  = (int) (To_Date.getTime() - From_Date.getTime())/(1000 * 60 * 60 * 24);
                No_of_days++;
                
                
                for(int j=0;j<No_of_days;j++){
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(From_Date.getTime());
                    c.add(Calendar.DATE, j);
                    Date NextDay = c.getTime();  
                    
                    if(NextDay.equals(sDate)){
                       currentStatus = 3;
                        
                        break;
                    }
                    
                }
                
                
                
            }
            
            
        }
        
        
        
        return currentStatus;
     }
     
     
     //  DUPLICATE OF CURRENT STATUS 1........................................................................................................
     
      public int getCurrentStatus1(int day , int month , int year ) throws BasicException{
         int currentStatus = 0;
        String hall = hallListConbo2.getSelectedItem().toString();
        Calendar c1 = Calendar.getInstance();
        c1.set(year, month, day);
        Date sDate = c1.getTime();
        String temp = Formats.DATE.formatValue(sDate); 
        sDate = (Date) Formats.DATE.parseValue(temp);
        
       // currentStatus =  BookHall_Status.check_Status(temp , hall , m_App);
         
       
            int Block_Flag = BookHall_Status.getBlockFlag(hall, m_App);
            
            if(Block_Flag==1){
                
                Date From_Date = Hall.getBlock_From_Date(hall, m_App);
                Date To_Date =Hall.getBlock_To_Date(hall, m_App);
                
                int No_of_days  = (int) (To_Date.getTime() - From_Date.getTime())/(1000 * 60 * 60 * 24);
                No_of_days++;
                
                
                for(int j=0;j<No_of_days;j++){
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(From_Date.getTime());
                    c.add(Calendar.DATE, j);
                    Date NextDay = c.getTime();  
                    
                    if(NextDay.equals(sDate)){
                       currentStatus = 3;
                        
                        break;
                    }
                    
                }
                
                
        }
        
        
        
        return currentStatus;
     }
     
     
     
     
     
     // GET BOOKING SLOT STATUS FOR PARTIALLY BOOKING-------------------------------------------------------------------------------------------
      public int getBookingSlotStatus(int day , int month , int year ){
         int currentStatus = 0;
        String hall = hallListConbo2.getSelectedItem().toString();
        Calendar c1 = Calendar.getInstance();
        c1.set(year, month, day);
        Date sDate = c1.getTime();
        String temp = Formats.DATE.formatValue(sDate); 
        currentStatus =  BookHall_Status.getSlotBookingState(temp , hall , m_App);
         return currentStatus;
     }
     // GET TIMINGS FOR BOOKING SLOT STATUS FOR PARTIALLY BOOKING-------------------------------------------------------------------------------------------
      public List getBooking_Timings(int day , int month , int year ){
        List<Object> tempList = new ArrayList<Object>();
          
         
         String hall = hallListConbo2.getSelectedItem().toString();
         Calendar c1 = Calendar.getInstance();
         c1.set(year, month, day);
         Date sDate = c1.getTime();
         String temp = Formats.DATE.formatValue(sDate); 
         tempList =  BookHall_Status.getBooking_Timings(temp , hall , m_App);
         return tempList;
         
      }
      
      
      // GET TIMINGS FOR BOOKING SLOT STATUS FOR PARTIALLY BOOKING-------------------------------------------------------------------------------------------
      public List getHalfDay_Booking_Timings(int day , int month , int year ){
        List<Object> tempList = new ArrayList<Object>();
          
         
         String hall = hallListConbo2.getSelectedItem().toString();
         Calendar c1 = Calendar.getInstance();
         c1.set(year, month, day);
         Date sDate = c1.getTime();
         String temp = Formats.DATE.formatValue(sDate); 
         tempList =  BookHall_Status.getHalfDay_Booking_Timings(temp , hall , m_App);
         return tempList;
         
      }
      
      
     public void getAvailable_hall_list_for_booking(List Avail_hall_List_for_book){
        
         Available_hall_list_model = new ComboBoxValModel(Avail_hall_List_for_book);
         jComboBox1.setModel(Available_hall_list_model);
         
    
    
     }
     
     private class DateAction extends AbstractAction {

         private String bValue = null;
       
        public DateAction(String name) {
            super(name);
            bValue = name;
        }

         
         
        public void actionPerformed(ActionEvent e) {
            
          
            int day = Integer.parseInt(bValue);
            int month = Integer.parseInt(Month_label.getText());
            int year = Integer.parseInt(year_Label.getText());
            
            Calendar cal5 = Calendar.getInstance();
            cal5.set(year, month-1, day);
            Date dbDate = cal5.getTime();
            String str_dbDate =   Formats.DATE.formatValue(dbDate);
            date_textfield.setText(str_dbDate);
            
           jTextField2.setText(str_dbDate);
             //To change body of generated methods, choose Tools | Templates.
        }
         
     }
      private class HallAction extends AbstractAction {

                    private String bValue = null;

                   public HallAction(String name) {
                       super(name);
                       bValue = name;
                   }



                   public void actionPerformed(ActionEvent e) {


                       int size = jComboBox1.getItemCount();
                       for(int i=0 ; i< size ; i++){
                           String x = jComboBox1.getItemAt(i).toString();
                           if(bValue.equals(x)){
                               jComboBox1.setSelectedIndex(i);
                           }

                       }

                   }
           }
      
      
      public void Payment_Option() throws BasicException{
          
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
                    String Booking_id = Payment_list.get(i).toString();
                    
                    // Payment Flag : (0: Payment Not Recieved . 1:- Payment Recieved . 2:- payment not pdone in time....)
                    
                     int Update_Status  =  new PreparedSentence(m_App.getSession(), "UPDATE hall_booked_details  SET STATUS=1 , PAYMENT_FLAG=2  WHERE ID =  ?"
                                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec
                                                       (new Object[]{  Booking_id  });
              
                     
                     
                    
                }
                
            }
          
          
      }
      
      
      public List getBlocked_Hall_List(Date Selected_date){
          List<Object> Blocked_Halls = new ArrayList<Object>();
          List<Object> Blocked_Halls_Temp = new ArrayList<Object>();
          Blocked_Halls = BookHall_Status.getBlocked_Hall_list(m_App);
         
          if(Blocked_Halls.size()!=0){
             for(int i=0;i<Blocked_Halls.size();i++){
                String HallName = Blocked_Halls.get(i).toString();
                Date From_Date = Hall.getBlock_From_Date(HallName, m_App);
                Date To_Date =Hall.getBlock_To_Date(HallName, m_App);
                
                int No_of_days  = (int) (To_Date.getTime() - From_Date.getTime())/(1000 * 60 * 60 * 24);
                No_of_days++;
                
                for(int j=0;j<No_of_days;j++){
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(From_Date.getTime());
                    c.add(Calendar.DATE, j);
                    Date NextDay = c.getTime();  
                    
                    if(NextDay.equals(Selected_date)){
                        Blocked_Halls_Temp.add(HallName);
                        
                        break;
                    }
                    
                }
                
              
             }
          }
          
          
          return Blocked_Halls_Temp;
      }
      
      
      
      public void PrintRefundReciept(String Booking_Id , Double Cancel_Chrg_Perc){
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            
           // Double TotalAmt = Payment_Status.getTotalAmount(m_App, Booking_Id);

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            
            script.put("maintitle", "Booking Cancellation Slip");
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", MemberName);
            script.put("cno", MemberNo);
            script.put("date", Formats.TIMESTAMP.formatValue(new Date())); 
            
            
            script.put("Reciept_title", "Refund Voucher Id : ");
            script.put("receipt", Rfd_Voucher_No);
            
            
            script.put("label_1", "Hall Name :");
            script.put("text1", HallName);
            
            script.put("label_2", "Booking Date :");
            script.put("text2",BOOKINGDATE);
            
            script.put("label_3", "Total Amount : ");
            script.put("text3", decimalFormat.format(TotalAmt));
            
           
            script.put("label_4", "Cancellation Charge : ");
            script.put("text4", (decimalFormat.format((Cancel_Chrg_Perc*TotalAmt)/100)) + "  ("+  Cancel_Chrg_Perc +" %)" );
            
            script.put("label_5", "Fixed Cancel Chrg:");
            script.put("text5", Fix_Charge);
          
            script.put("Gname", Non_Memmer_Name);
            
            script.put("label_6", "Advance Paid : ");
            script.put("text6", decimalFormat.format(Advance_Recv));
            
            script.put("label_7", "Total Cancellation Charge :");
            script.put("text7", decimalFormat.format(Cancel_Charge)); 
            
            
            
            script.put("label_8", "Refund Amount : ");
            script.put("text8", decimalFormat.format(Refund_Amt));
            
            
           
            
            
            
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
         
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
            
            
            
            script.put("receipt", " ");
            script.put("Recietp_title", "");
            
            script.put("label_1", "Hall Name :");
            script.put("text1", hallName.getText());
            
            script.put("label_2", "Booking Date :");
            script.put("text2",bookingDate.getText());
            
            script.put("label_3", "Booking Slot : ");
            script.put("text3", bookingSlot.getText()); 
          
            
            
            script.put("label_4", "Booking Status :");
            int x1 = Integer.parseInt(booking_status_label.getText());
            if(x1==0){
                script.put("text4", "Pending");
            }
            else if(x1==1){
                script.put("text4", "Approved"); 
            }
            else{
                if(x1==2){
                 script.put("text4", "Rejected");  
                }
            }
            
            
            script.put("label_5", "Last Payment Date :");
            script.put("text5", payment_label.getText());
            
          
           Double tariff5 = Double.parseDouble((tariff.getText()));
            
            script.put("label_6", "Tariff :  ");
            script.put("text6", decimalFormat.format(tariff5));
            
            
            script.put("label_7", "Advance Paid : ");
            script.put("text7", decimalFormat.format(Advance_Recv));
            
            script.put("label_8", "Refund Amount : ");
            script.put("text8", decimalFormat.format(Refund_Amt));
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString() );
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (Exception e) {
            }
        }   
      
    
    
    
    // GET SEQUENCE NO FOR HALL BOOKING....## AAKASH
       private String getNextHall_Sequence() throws BasicException{
       //akash:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname="HALL_ID";
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
         JOptionPane.showMessageDialog(null, "Please Specify series for Hall Booking in general Table..!! ", "Cannot Create Refund", JOptionPane.WARNING_MESSAGE);
         return "";
     }
       
     }
     
     
     
       public void Update_HallSeq() throws BasicException{ 
         
        String billnum;
        String uname="HALL_ID";
    
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
             
             Message = "Dear Member, \n        Refund of Rs. "+Refund_Amt +" /-  after cancellation of HALL. \n (Advance Recieved : "+Advance_Recv+"/- , Cancellation Charge :" +Cancel_Charge +"/-  ).  \n (Booking ID : "+Booking_Seq_No+ ") Booked By "+MemberName+ ". "  ;
             reportparams.put("V_NO",Rfd_Voucher_No );
             String newDate = Formats.TIMESTAMP.formatValue(new Date());
             reportparams.put("date", newDate );
             reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
             reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
             reportparams.put("MEM_NAME", MemberName);
             reportparams.put("M_NO", MemberNo);
            // reportparams.put("MAIN_TEXT", Message);
             reportparams.put("CREATEBY",  m_App.getAppUserView().getUser().getName());
             reportparams.put("CRHOST" , m_App.getProperties().getHost());
             reportparams.put("MEMSIGN", MemberName);
             reportparams.put("HALLNAME", HallName);
             reportparams.put("bookingseqno", Booking_Seq_No);
             reportparams.put("bookingdateinfo", bookeddate+" Slot : ( "+TIMING_SLOT+") .");
             reportparams.put("advanceamt", decimalFormat.format(Advance_Recv));
             reportparams.put("totalbillamount", decimalFormat.format(TotalAmt));
             
             reportparams.put("cancellationperc", (decimalFormat.format(Cancel_Chrg_Perc))+"%");
             reportparams.put("CancelCharge", decimalFormat.format(Cancel_Chrg_Perc*TotalAmt));
             
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
       
       
      
       
}
