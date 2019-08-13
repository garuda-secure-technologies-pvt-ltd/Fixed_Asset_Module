
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.beans.JFlowPanel;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.Booking.FloorEditor.Guest_FloorTableInfo;
import com.openbravo.pos.Booking.GuestRoomTableModel.GuestRoomTableInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.inventory.TaxCategoryInfo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;



public class GuestRoomBookingMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp  {

    private AppView m_App;
    private BufferedImage Image1;
    private BufferedImage Image2;
    private BufferedImage Image3;
    private List staxlist;
    private DataLogicSales m_dlSales;
    private ComboBoxValModel  staxmodel;
    private ComboBoxValModel  stax2;
    private ComboBoxValModel  stax3;
    private FloorEditor GF;
    private List<FloorEditor.Guest_FloorTableInfo> data ;
    private List<GuestRoomTableModel.GuestRoomTableInfo> data1 ;
    private HashSet<GuestRoomTableModel.GuestRoomTableInfo> finaldata1;
    private ComboBoxValModel FloorListModel ;
    private ComboBoxValModel RoomTypeListModel;
    
    private GuestRoomTableModel GuestRoomTable;
    private Guest_FloorTableInfo  G_fti;
    public BufferedImage bufferedImage = null;
    private List Room_Nos_list = new ArrayList();
    private List<Object> roomNo_List_Cust = new ArrayList<Object>();
    private Room_Nos_Model RoomNoListModel;
    private ComboBoxValModel RoomNo_link_listModel ;
    private ComboBoxValModel RoomNo_link_Cust_listModel ;
    private GuestRoomLinkTableModel GR_Linked_tableModel;
    private List<Object> Linked_Room_Nos_list = new ArrayList<Object>();
    private List<Object> Linked_Cust_Name_List = new ArrayList<Object>();
    String Advance_Acct_ID;
    String Revenue_Acct_ID;
    String Cancel_Acct_ID;
    private DataLogicFacilities dlfac;
    
     List<AccountMasterExt> acclist=new ArrayList<AccountMasterExt>();
     List<AccountMasterExt> sacclist=new ArrayList<AccountMasterExt>();
    
    private ComboBoxValModel  Adv_acount_Model;
    private ComboBoxValModel  reve_accnt_Model;
    private ComboBoxValModel  Cancel_accnt_model;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    public GuestRoomBookingMaster() {
        initComponents();
        
        main_panel.setVisible(true);
        image_panel.setVisible(false);
        
        
        
        jLabel12.setVisible(false);
        tax2.setVisible(false);
        jLabel13.setVisible(false);
        tax3.setVisible(false);
        basic.setVisible(false);
        cascade.setVisible(false);
        basic.setSelected(true);
        selectMoreTax.setVisible(false);
        selectMoreTax1.setVisible(false);
        m_tariff.setVisible(false);
        n_tariff.setVisible(false);
        jLabel32.setVisible(false);
        jLabel44.setVisible(false);
        updateChanges.setVisible(false);
        room_no_panel.setVisible(false);
        
           availibility_panel.setVisible(false);
           typeOfRoom.setVisible(true);
           jLabel16.setVisible(true);
       
           basic2.setVisible(false);
           cascade2.setVisible(false);
       
       
           edit.setEnabled(false);
           delete.setEnabled(false);
           block_button.setEnabled(false);
           block_panel.setVisible(false);
           payment_frame.setVisible(false);
           full_radio_btn.setSelected(true);
           basic2.setSelected(true);
          account_panel.setVisible(false);
           
           
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        main_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        showAll = new javax.swing.JCheckBox();
        block_button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        roomNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        facilities = new javax.swing.JTextArea();
        save = new javax.swing.JButton();
        updateChanges = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        luxuryTax = new javax.swing.JComboBox();
        selectMoreTax = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        tax2 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        tax3 = new javax.swing.JComboBox();
        selectMoreTax1 = new javax.swing.JCheckBox();
        basic = new javax.swing.JRadioButton();
        cascade = new javax.swing.JRadioButton();
        member = new javax.swing.JCheckBox();
        non_member = new javax.swing.JCheckBox();
        id = new javax.swing.JLabel();
        m_tariff = new javax.swing.JTextField();
        n_tariff = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        typeOfRoom = new javax.swing.JTextField();
        m_jimageIcon = new com.openbravo.data.gui.JImageEditor();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        advnceBooking_dura = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        max_Capacity = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        check_IN = new javax.swing.JTextField();
        check_OUT = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        max_days = new javax.swing.JTextField();
        basic2 = new javax.swing.JRadioButton();
        cascade2 = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        payment_Text = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        set_acc_btn = new javax.swing.JButton();
        image_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        imgCancel = new javax.swing.JButton();
        imgSave = new javax.swing.JButton();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        m_jImage1 = new com.openbravo.data.gui.JImageEditor();
        m_jImage2 = new com.openbravo.data.gui.JImageEditor();
        jLabel15 = new javax.swing.JLabel();
        availibility_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        available_text = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        totalRoomsText = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        availroomText = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        save_avail = new javax.swing.JButton();
        cancel_avail = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        block_panel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        BLOCK_FROM_TEXT = new javax.swing.JTextField();
        BLOCK_UPTO_TEXT = new javax.swing.JTextField();
        room_label = new javax.swing.JLabel();
        capacity_label = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        total_room_label = new javax.swing.JLabel();
        ID_label = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        from_date_label = new javax.swing.JLabel();
        to_date_label = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        Submit = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        blocked_rooms_label = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        block_rooms_text = new javax.swing.JTextField();
        payment_frame = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        full_radio_btn = new javax.swing.JRadioButton();
        partial_radio_btn = new javax.swing.JRadioButton();
        partial_variation_panel = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        Adv_Perc = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        Chk_in_perc = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        chk_out_perc = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        room_no_panel = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        no_of_rooms = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        Room_No = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        Remove = new javax.swing.JButton();
        save3 = new javax.swing.JButton();
        cancel3 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        account_panel = new javax.swing.JPanel();
        Adv_acount_combo = new javax.swing.JComboBox();
        jLabel50 = new javax.swing.JLabel();
        reve_accnt_combo = new javax.swing.JComboBox();
        jLabel51 = new javax.swing.JLabel();
        sace_acc = new javax.swing.JButton();
        cancel_acc = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        cancel_acct_combo = new javax.swing.JComboBox();

        jFormattedTextField1.setText("jFormattedTextField1");
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

        main_panel.setName("main_panel"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

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
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Room Name", "Room Type", "No. of Rooms", "Mem Tariff", "Non_mem Tariff", "Luxury tax", "Tax 2", "tax 3", "Facilities", "Basic/Cascade", "Advc Bkng Dura", "Availibility", "Rooms Available", "Check In Time", "Check Out Time", "Max_Days"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        edit.setText("Edit");
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
        edit.setName("edit"); // NOI18N
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        delete.setText("Deactivate");
        delete.setName("delete"); // NOI18N
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        showAll.setText("Show All");
        showAll.setName("showAll"); // NOI18N
        showAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showAllItemStateChanged(evt);
            }
        });

        block_button.setText("Block");
        block_button.setName("block_button"); // NOI18N
        block_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                block_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(block_button, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showAll)
                .addGap(43, 43, 43))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(showAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(block_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(269, 269, 269))
        );

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/exit.png"))); // NOI18N
        block_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/block.png"))); // NOI18N

        jTabbedPane1.addTab("GuestRoom Details", jPanel3);

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setText("No. of Rooms");
        jLabel2.setName("jLabel2"); // NOI18N

        roomNo.setName("roomNo"); // NOI18N
        roomNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roomNoKeyReleased(evt);
            }
        });

        jLabel3.setText("Room Tariffs");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel6.setText("Images");
        jLabel6.setName("jLabel6"); // NOI18N

        jButton1.setText("Upload Images");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/upload.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.setName("cancel"); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel7.setText("Facilities");
        jLabel7.setName("jLabel7"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        facilities.setColumns(20);
        facilities.setRows(5);
        facilities.setName("facilities"); // NOI18N
        jScrollPane2.setViewportView(facilities);

        save.setText("Save");
        save.setName("save"); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        updateChanges.setText("saveChanges");
        updateChanges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        updateChanges.setName("updateChanges"); // NOI18N
        updateChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateChangesActionPerformed(evt);
            }
        });

        jLabel11.setText("Tax 1");
        jLabel11.setName("jLabel11"); // NOI18N

        luxuryTax.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        luxuryTax.setName("luxuryTax"); // NOI18N
        luxuryTax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                luxuryTaxItemStateChanged(evt);
            }
        });
        luxuryTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luxuryTaxActionPerformed(evt);
            }
        });

        selectMoreTax.setText("Select more Tax");
        selectMoreTax.setName("selectMoreTax"); // NOI18N
        selectMoreTax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectMoreTaxItemStateChanged(evt);
            }
        });
        selectMoreTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMoreTaxActionPerformed(evt);
            }
        });

        jLabel12.setText("Tax 2");
        jLabel12.setName("jLabel12"); // NOI18N

        tax2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        tax2.setName("tax2"); // NOI18N
        tax2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tax2ItemStateChanged(evt);
            }
        });
        tax2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tax2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Tax 3");
        jLabel13.setName("jLabel13"); // NOI18N

        tax3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        tax3.setName("tax3"); // NOI18N
        tax3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tax3ItemStateChanged(evt);
            }
        });
        tax3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tax3ActionPerformed(evt);
            }
        });

        selectMoreTax1.setText("Select more Tax");
        selectMoreTax1.setName("selectMoreTax1"); // NOI18N
        selectMoreTax1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectMoreTax1ItemStateChanged(evt);
            }
        });
        selectMoreTax1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMoreTax1ActionPerformed(evt);
            }
        });

        basic.setText("Basic");
        basic.setName("basic"); // NOI18N

        cascade.setText("Cascade");
        cascade.setName("cascade"); // NOI18N

        member.setText("Member");
        member.setName("member"); // NOI18N
        member.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memberItemStateChanged(evt);
            }
        });
        member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberActionPerformed(evt);
            }
        });

        non_member.setText("Non Member ");
        non_member.setName("non_member"); // NOI18N
        non_member.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                non_memberItemStateChanged(evt);
            }
        });
        non_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                non_memberActionPerformed(evt);
            }
        });

        id.setText("id");
        id.setName("id"); // NOI18N

        m_tariff.setName("m_tariff"); // NOI18N
        m_tariff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                m_tariffKeyReleased(evt);
            }
        });

        n_tariff.setName("n_tariff"); // NOI18N
        n_tariff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                n_tariffKeyReleased(evt);
            }
        });

        jLabel16.setText("Type of Room");
        jLabel16.setName("jLabel16"); // NOI18N

        typeOfRoom.setName("typeOfRoom"); // NOI18N

        m_jimageIcon.setName("m_jimageIcon"); // NOI18N
        m_jimageIcon.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                m_jimageIconAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        add(m_jImage);
        m_jImage.setBounds(130, 300, 240, 180);

        jLabel14.setText("Select Icon");
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel17.setText("Advance Booking Duration");
        jLabel17.setName("jLabel17"); // NOI18N

        advnceBooking_dura.setName("advnceBooking_dura"); // NOI18N
        advnceBooking_dura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                advnceBooking_duraKeyReleased(evt);
            }
        });

        jButton3.setText("Change Availibility");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel21.setText("Max . Capacity");
        jLabel21.setName("jLabel21"); // NOI18N

        max_Capacity.setName("max_Capacity"); // NOI18N
        max_Capacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                max_CapacityKeyReleased(evt);
            }
        });

        jButton4.setText("Check IN");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Check OUT");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        check_IN.setName("check_IN"); // NOI18N

        check_OUT.setName("check_OUT"); // NOI18N

        jLabel24.setText("Max. no. of days to be booked");
        jLabel24.setName("jLabel24"); // NOI18N

        max_days.setName("max_days"); // NOI18N
        max_days.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                max_daysKeyReleased(evt);
            }
        });

        basic2.setText("Basic");
        basic2.setName("basic2"); // NOI18N

        cascade2.setText("Cascade");
        cascade2.setName("cascade2"); // NOI18N

        jLabel25.setText("Advanced Payment Duration");
        jLabel25.setName("jLabel25"); // NOI18N

        payment_Text.setName("payment_Text"); // NOI18N
        payment_Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                payment_TextKeyReleased(evt);
            }
        });

        jLabel26.setText("Months");
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel27.setText("(* Days within Booking)");
        jLabel27.setName("jLabel27"); // NOI18N

        jButton9.setText("Advance Payment Option");
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel32.setText("(Rs.)");
        jLabel32.setName("jLabel32"); // NOI18N

        jLabel44.setText("(Rs.)");
        jLabel44.setName("jLabel44"); // NOI18N

        jButton10.setText("Enter Room Nos.");
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        set_acc_btn.setText("Set Account");
        set_acc_btn.setName("set_acc_btn"); // NOI18N
        set_acc_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_acc_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(typeOfRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel17))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(roomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton10)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(non_member)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel44))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(member)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel32)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m_tariff, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(n_tariff, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(max_days, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(advnceBooking_dura, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26))
                            .addComponent(m_jimageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(payment_Text, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(max_Capacity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 485, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(set_acc_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addComponent(jLabel11))
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(id)
                                                        .addComponent(jLabel13)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tax2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tax3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(selectMoreTax)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(basic2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cascade2))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(basic)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cascade)
                                                .addGap(18, 18, 18)
                                                .addComponent(selectMoreTax1))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(check_IN, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(check_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 289, Short.MAX_VALUE)))))
                .addGap(114, 114, 114))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(typeOfRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(roomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(advnceBooking_dura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26))
                            .addComponent(jLabel17))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(member)
                            .addComponent(m_tariff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(non_member)
                            .addComponent(n_tariff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(max_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(selectMoreTax))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(selectMoreTax1)
                            .addComponent(basic)
                            .addComponent(cascade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(basic2)
                            .addComponent(cascade2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(max_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(payment_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(m_jimageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jLabel6)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_IN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(set_acc_btn)
                            .addComponent(jButton9))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        id.setVisible(false);
        jLabel16.setVisible(false);
        typeOfRoom.setVisible(false);
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit2.png"))); // NOI18N
        check_IN.setEditable(false);
        check_OUT.setEditable(false);
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash2.png"))); // NOI18N

        jTabbedPane1.addTab("Create New ", jPanel2);

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
                .addContainerGap())
        );

        image_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        image_panel.setName("image_panel"); // NOI18N

        jLabel8.setText("Image 1");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText("Image 2");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText("Image 3");
        jLabel10.setName("jLabel10"); // NOI18N

        imgCancel.setText("Cancel");
        imgCancel.setName("imgCancel"); // NOI18N
        imgCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        imgCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgCancelActionPerformed(evt);
            }
        });

        imgSave.setText("Save");
        imgSave.setName("imgSave"); // NOI18N
        imgSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        imgSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgSaveActionPerformed(evt);
            }
        });

        m_jImage.setMaximumSize(new java.awt.Dimension(370, 195));
        m_jImage.setMinimumSize(new java.awt.Dimension(370, 195));
        m_jImage.setName("m_jImage"); // NOI18N
        m_jImage.setPreferredSize(new java.awt.Dimension(370, 195));
        m_jImage.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                m_jImageAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        add(m_jImage);
        m_jImage.setBounds(130, 300, 240, 180);

        m_jImage1.setMaximumSize(new java.awt.Dimension(370, 195));
        m_jImage1.setMinimumSize(new java.awt.Dimension(370, 195));
        m_jImage1.setName("m_jImage1"); // NOI18N
        m_jImage1.setPreferredSize(new java.awt.Dimension(370, 195));
        m_jImage1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                m_jImage1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        add(m_jImage);
        m_jImage.setBounds(130, 300, 240, 180);

        m_jImage2.setMaximumSize(new java.awt.Dimension(370, 195));
        m_jImage2.setMinimumSize(new java.awt.Dimension(370, 195));
        m_jImage2.setName("m_jImage2"); // NOI18N
        m_jImage2.setPreferredSize(new java.awt.Dimension(370, 195));
        m_jImage2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                m_jImage2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        add(m_jImage);
        m_jImage.setBounds(130, 300, 240, 180);

        jLabel15.setText("Image Should not be more than 64 kb");
        jLabel15.setName("jLabel15"); // NOI18N

        javax.swing.GroupLayout image_panelLayout = new javax.swing.GroupLayout(image_panel);
        image_panel.setLayout(image_panelLayout);
        image_panelLayout.setHorizontalGroup(
            image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_panelLayout.createSequentialGroup()
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addComponent(imgCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(imgSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(m_jImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, Short.MAX_VALUE)
                                    .addComponent(m_jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m_jImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        image_panelLayout.setVerticalGroup(
            image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(image_panelLayout.createSequentialGroup()
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(206, 206, 206))
                            .addGroup(image_panelLayout.createSequentialGroup()
                                .addComponent(m_jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(67, 67, 67)))
                        .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(m_jImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(m_jImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(image_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        availibility_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        availibility_panel.setName("availibility_panel"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Availibility");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel4.setText("Availibility");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Type of Room ");
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        available_text.setName("available_text"); // NOI18N
        available_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                available_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                available_textKeyReleased(evt);
            }
        });

        jLabel18.setText("%");
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText("Total No. of Rooms ");
        jLabel19.setName("jLabel19"); // NOI18N

        totalRoomsText.setName("totalRoomsText"); // NOI18N

        jLabel20.setText("Available Rooms");
        jLabel20.setName("jLabel20"); // NOI18N

        availroomText.setName("availroomText"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        save_avail.setText("Save");
        save_avail.setName("save_avail"); // NOI18N
        save_avail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_availActionPerformed(evt);
            }
        });

        cancel_avail.setText("Cancel");
        cancel_avail.setName("cancel_avail"); // NOI18N
        cancel_avail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_availActionPerformed(evt);
            }
        });

        jButton2.setText("Check Availibility ");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout availibility_panelLayout = new javax.swing.GroupLayout(availibility_panel);
        availibility_panel.setLayout(availibility_panelLayout);
        availibility_panelLayout.setHorizontalGroup(
            availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(availibility_panelLayout.createSequentialGroup()
                                .addComponent(available_text, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(availroomText, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalRoomsText, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118))
                    .addGroup(availibility_panelLayout.createSequentialGroup()
                        .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(availibility_panelLayout.createSequentialGroup()
                                .addComponent(cancel_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(346, 346, 346)
                                .addComponent(save_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 100, Short.MAX_VALUE))))
        );
        availibility_panelLayout.setVerticalGroup(
            availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availibility_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(totalRoomsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(available_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(availroomText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(availibility_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jTextField1.setEditable(false);
        totalRoomsText.setEditable(false);
        availroomText.setEditable(false);
        save_avail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        cancel_avail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/Check.png"))); // NOI18N

        block_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        block_panel.setName("block_panel"); // NOI18N

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel28.setText("Block Hall");
        jLabel28.setName("jLabel28"); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Room Type: ");
        jLabel29.setName("jLabel29"); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Capacity : ");
        jLabel30.setName("jLabel30"); // NOI18N

        jButton7.setText("Block From");
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton12.setText("Block  Up To");
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        BLOCK_FROM_TEXT.setName("BLOCK_FROM_TEXT"); // NOI18N

        BLOCK_UPTO_TEXT.setName("BLOCK_UPTO_TEXT"); // NOI18N

        room_label.setText("name");
        room_label.setName("room_label"); // NOI18N

        capacity_label.setText("capacity");
        capacity_label.setName("capacity_label"); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Total No Of Rooms : ");
        jLabel31.setName("jLabel31"); // NOI18N

        total_room_label.setText("No. ");
        total_room_label.setName("total_room_label"); // NOI18N

        ID_label.setText("ID");
        ID_label.setName("ID_label"); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Hall is blocked   from  : ");
        jLabel33.setName("jLabel33"); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("to : ");
        jLabel34.setName("jLabel34"); // NOI18N

        from_date_label.setText("Date");
        from_date_label.setName("from_date_label"); // NOI18N

        to_date_label.setText("Date");
        to_date_label.setName("to_date_label"); // NOI18N

        jButton6.setText("Cancel");
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        Submit.setText("Submit");
        Submit.setName("Submit"); // NOI18N
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("No. of rooms Blocked : ");
        jLabel35.setName("jLabel35"); // NOI18N

        blocked_rooms_label.setText("rooms");
        blocked_rooms_label.setName("blocked_rooms_label"); // NOI18N

        jButton8.setText("Un Block");
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel36.setText("Block No. of Rooms : ");
        jLabel36.setName("jLabel36"); // NOI18N

        block_rooms_text.setName("block_rooms_text"); // NOI18N
        block_rooms_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                block_rooms_textKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout block_panelLayout = new javax.swing.GroupLayout(block_panel);
        block_panel.setLayout(block_panelLayout);
        block_panelLayout.setHorizontalGroup(
            block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BLOCK_FROM_TEXT, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(BLOCK_UPTO_TEXT)))
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blocked_rooms_label))
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(from_date_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(to_date_label)))
                .addGap(236, 236, 236))
            .addGroup(block_panelLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, block_panelLayout.createSequentialGroup()
                                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(block_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(room_label))
                                    .addGroup(block_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(capacity_label)))
                                .addGap(121, 121, 121)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total_room_label)
                                .addGap(122, 122, 122)
                                .addComponent(jLabel36))
                            .addComponent(ID_label, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(block_rooms_text, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addGap(133, 133, 133))
        );
        block_panelLayout.setVerticalGroup(
            block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(block_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(28, 28, 28)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(room_label)
                    .addComponent(jLabel31)
                    .addComponent(total_room_label)
                    .addComponent(jLabel36)
                    .addComponent(block_rooms_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(capacity_label))
                .addGap(30, 30, 30)
                .addComponent(ID_label)
                .addGap(55, 55, 55)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(from_date_label)
                    .addComponent(jLabel34)
                    .addComponent(to_date_label))
                .addGap(13, 13, 13)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(blocked_rooms_label))
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(BLOCK_FROM_TEXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12)
                            .addComponent(BLOCK_UPTO_TEXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 124, Short.MAX_VALUE))
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8))
                        .addGap(30, 30, 30))))
        );

        jLabel28.setForeground(Color.RED);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/block.png"))); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        BLOCK_FROM_TEXT.setEditable(false);
        BLOCK_UPTO_TEXT.setEditable(false);
        ID_label.setVisible(false);
        from_date_label.setForeground(Color.RED);
        to_date_label.setForeground(Color.RED);
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        Submit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        blocked_rooms_label.setForeground(Color.RED);
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/unblock.png"))); // NOI18N

        payment_frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        payment_frame.setName("payment_frame"); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel37.setText("Advance Payment Options");
        jLabel37.setName("jLabel37"); // NOI18N

        full_radio_btn.setText("Pay full advance amount");
        full_radio_btn.setName("full_radio_btn"); // NOI18N
        full_radio_btn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                full_radio_btnItemStateChanged(evt);
            }
        });

        partial_radio_btn.setText("Pay partially advance amount");
        partial_radio_btn.setName("partial_radio_btn"); // NOI18N
        partial_radio_btn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                partial_radio_btnItemStateChanged(evt);
            }
        });

        partial_variation_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        partial_variation_panel.setName("partial_variation_panel"); // NOI18N

        jLabel38.setText("Initial Payment during booking (%) ");
        jLabel38.setName("jLabel38"); // NOI18N

        Adv_Perc.setName("Adv_Perc"); // NOI18N
        Adv_Perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Adv_PercKeyReleased(evt);
            }
        });

        jLabel39.setText(" Payment during Check-In (%) ");
        jLabel39.setName("jLabel39"); // NOI18N

        Chk_in_perc.setName("Chk_in_perc"); // NOI18N
        Chk_in_perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Chk_in_percKeyReleased(evt);
            }
        });

        jLabel40.setText("Payment during Check-Out (%)");
        jLabel40.setName("jLabel40"); // NOI18N

        chk_out_perc.setName("chk_out_perc"); // NOI18N
        chk_out_perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chk_out_percKeyReleased(evt);
            }
        });

        jLabel41.setText("(0-100%)");
        jLabel41.setName("jLabel41"); // NOI18N

        jLabel42.setText("(0-100%)");
        jLabel42.setName("jLabel42"); // NOI18N

        jLabel43.setText("(0-100%)");
        jLabel43.setName("jLabel43"); // NOI18N

        javax.swing.GroupLayout partial_variation_panelLayout = new javax.swing.GroupLayout(partial_variation_panel);
        partial_variation_panel.setLayout(partial_variation_panelLayout);
        partial_variation_panelLayout.setHorizontalGroup(
            partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partial_variation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(partial_variation_panelLayout.createSequentialGroup()
                        .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel38))
                        .addGap(18, 18, 18)
                        .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Adv_Perc, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(Chk_in_perc)))
                    .addGroup(partial_variation_panelLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(chk_out_perc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        partial_variation_panelLayout.setVerticalGroup(
            partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partial_variation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(Adv_Perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(Chk_in_perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(chk_out_perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton29.setText("Save");
        jButton29.setName("jButton29"); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton28.setText("Cancel");
        jButton28.setName("jButton28"); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout payment_frameLayout = new javax.swing.GroupLayout(payment_frame);
        payment_frame.setLayout(payment_frameLayout);
        payment_frameLayout.setHorizontalGroup(
            payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payment_frameLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(payment_frameLayout.createSequentialGroup()
                .addGroup(payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(payment_frameLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(partial_variation_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(payment_frameLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partial_radio_btn)
                            .addComponent(full_radio_btn)))
                    .addGroup(payment_frameLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel37)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        payment_frameLayout.setVerticalGroup(
            payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payment_frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(full_radio_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(partial_radio_btn)
                .addGap(18, 18, 18)
                .addComponent(partial_variation_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton29)
                    .addComponent(jButton28))
                .addContainerGap())
        );

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        room_no_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        room_no_panel.setName("room_no_panel"); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setText("Room Nos. Option");
        jLabel45.setName("jLabel45"); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("No. Of Rooms : ");
        jLabel22.setName("jLabel22"); // NOI18N

        no_of_rooms.setText("jLabel23");
        no_of_rooms.setName("no_of_rooms"); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Enter Room No: ");
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("List Of Room nos. Entered :");
        jLabel46.setName("jLabel46"); // NOI18N

        Room_No.setName("Room_No"); // NOI18N
        Room_No.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Room_NoKeyPressed(evt);
            }
        });

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("jList1"); // NOI18N
        jScrollPane4.setViewportView(jList1);

        Remove.setText("Remove");
        Remove.setName("Remove"); // NOI18N
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });

        save3.setText("Save");
        save3.setName("save3"); // NOI18N
        save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save3ActionPerformed(evt);
            }
        });

        cancel3.setText("Cancel");
        cancel3.setName("cancel3"); // NOI18N
        cancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel3ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N

        javax.swing.GroupLayout room_no_panelLayout = new javax.swing.GroupLayout(room_no_panel);
        room_no_panel.setLayout(room_no_panelLayout);
        room_no_panelLayout.setHorizontalGroup(
            room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room_no_panelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(room_no_panelLayout.createSequentialGroup()
                        .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Room_No, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(room_no_panelLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(no_of_rooms)))
                    .addComponent(jLabel47))
                .addContainerGap(234, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, room_no_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addGap(298, 298, 298))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, room_no_panelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(cancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        room_no_panelLayout.setVerticalGroup(
            room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room_no_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(21, 21, 21)
                .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Remove)
                    .addGroup(room_no_panelLayout.createSequentialGroup()
                        .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(no_of_rooms))
                        .addGap(18, 18, 18)
                        .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(Room_No, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(room_no_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save3)
                    .addComponent(cancel3))
                .addGap(221, 221, 221))
        );

        jLabel45.setForeground(Color.BLUE);
        no_of_rooms.setForeground(Color.RED);

        account_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        account_panel.setName("account_panel"); // NOI18N

        Adv_acount_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        Adv_acount_combo.setName("Adv_acount_combo"); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Advance Account ");
        jLabel50.setName("jLabel50"); // NOI18N

        reve_accnt_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        reve_accnt_combo.setName("reve_accnt_combo"); // NOI18N

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Revenue Account");
        jLabel51.setName("jLabel51"); // NOI18N

        sace_acc.setText("Save");
        sace_acc.setName("sace_acc"); // NOI18N
        sace_acc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sace_accActionPerformed(evt);
            }
        });

        cancel_acc.setText("Cancel ");
        cancel_acc.setName("cancel_acc"); // NOI18N
        cancel_acc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_accActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel49.setText("Account Menu");
        jLabel49.setName("jLabel49"); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Cancellation Account");
        jLabel52.setName("jLabel52"); // NOI18N

        cancel_acct_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        cancel_acct_combo.setName("cancel_acct_combo"); // NOI18N

        javax.swing.GroupLayout account_panelLayout = new javax.swing.GroupLayout(account_panel);
        account_panel.setLayout(account_panelLayout);
        account_panelLayout.setHorizontalGroup(
            account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, account_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(account_panelLayout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(56, 56, 56)
                        .addComponent(Adv_acount_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54))
                    .addGroup(account_panelLayout.createSequentialGroup()
                        .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addGroup(account_panelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(cancel_acc)))
                        .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, account_panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sace_acc)
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, account_panelLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cancel_acct_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reve_accnt_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(54, 54, 54))))))
            .addGroup(account_panelLayout.createSequentialGroup()
                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(account_panelLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel49))
                    .addGroup(account_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel52)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        account_panelLayout.setVerticalGroup(
            account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(account_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(48, 48, 48)
                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Adv_acount_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(32, 32, 32)
                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reve_accnt_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addGap(26, 26, 26)
                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(cancel_acct_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(account_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sace_acc)
                    .addComponent(cancel_acc))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel45.setForeground(Color.BLUE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(payment_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(availibility_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(block_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_no_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(account_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(availibility_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(block_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payment_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(room_no_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(account_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void m_jImageAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_m_jImageAncestorAdded

    }//GEN-LAST:event_m_jImageAncestorAdded

    private void m_jImage1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_m_jImage1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jImage1AncestorAdded

    private void m_jImage2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_m_jImage2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jImage2AncestorAdded

    private void imgSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgSaveActionPerformed
       
        
        count++;
        if(m_jImage.getImage()!=null || m_jImage1.getImage()!=null || m_jImage2.getImage()!=null){
       
        Image1 = m_jImage.getImage();
        Image2 = m_jImage1.getImage();
        Image3 = m_jImage2.getImage();
        image_panel.setVisible(false);
        main_panel.setVisible(true);
       
       }
        else{
            JOptionPane.showMessageDialog(this, "Select Room  image..!!", " tariff", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_imgSaveActionPerformed

    private void imgCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgCancelActionPerformed
        m_jImage.setImage(null);
        m_jImage1.setImage(null);
        m_jImage2.setImage(null);
        image_panel.setVisible(false);
        main_panel.setVisible(true);
    }//GEN-LAST:event_imgCancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       main_panel.setVisible(false);
      
       image_panel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void selectMoreTaxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectMoreTaxItemStateChanged
       if(selectMoreTax.isSelected()==true){
           jLabel12.setVisible(true);
           tax2.setVisible(true);
           
           
           
       }
       else{
           jLabel12.setVisible(false);
           tax2.setVisible(false);
           basic.setVisible(false);
           cascade.setVisible(false);
           selectMoreTax1.setVisible(false);
           jLabel13.setVisible(false);
           tax3.setVisible(false);
           tax2.setSelectedIndex(-1);
           tax3.setSelectedIndex(-1);
       }
    }//GEN-LAST:event_selectMoreTaxItemStateChanged

    private void selectMoreTax1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectMoreTax1ItemStateChanged
        if(selectMoreTax1.isSelected()==true){
            jLabel13.setVisible(true);
            tax3.setVisible(true);
           
            
            
        }
        else{
            jLabel13.setVisible(false);
            tax3.setVisible(false);
            tax3.setSelectedIndex(-1);
            basic2.setVisible(false);
            cascade2.setVisible(false);
        }
    }//GEN-LAST:event_selectMoreTax1ItemStateChanged

    private void selectMoreTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMoreTaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMoreTaxActionPerformed

    private void luxuryTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luxuryTaxActionPerformed
       
    }//GEN-LAST:event_luxuryTaxActionPerformed

    private void tax2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tax2ActionPerformed
      
    }//GEN-LAST:event_tax2ActionPerformed

    private void memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberActionPerformed
      
    }//GEN-LAST:event_memberActionPerformed

    private void non_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_non_memberActionPerformed
        
    }//GEN-LAST:event_non_memberActionPerformed

    String ID = null;
   
    int  Total_Rooms = 0;
    Double mem_tarif = 0.0;
    Double non_mem_tariff = 0.0;
    String facility = null;
    int basicTax1 = 0;
    int cascadeTax1 = 0;
    int basicTax2 = 0;
    int cascadeTax2 = 0;
    String Luxury_tax_Id = null;
    String Tax2_id = null;
    String Tax3_id = null;
    int insert_data = 0;
    int insert_image = 0;
    String Floor= null;
    String ID_update = null;
    int count = 0;
    int floor_X = 0;
    int floor_Y = 0;
    int active= 1;
    String TypeOfRoom = null;
    int max_capacity = 0;
    BufferedImage room_icon = null;
    int ADVANCE_BOOKING_DURA = 3;
   
    double Availibility_perc = 100.00 ; 
    int rooms_available = 0;
    
    Date Check_IN_Time = null;
    Date Check_Out_time = null;
    int Max_days;
    int Payment_Days;
    int block_Flag =0;
    int block_rooms = 0;
    String Advance_perc;
    String All_RNo;
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
       
      if(typeOfRoom.getText()!=null && typeOfRoom.getText().trim().length()>0 ){
            if(roomNo.getText()!=null && roomNo.getText().trim().length()>0){
             
                if(m_tariff.getText()!=null && m_tariff.getText().trim().length()>0){
                    if(n_tariff.getText()!=null && n_tariff.getText().trim().length()>0){
                        if(advnceBooking_dura.getText()!=null && advnceBooking_dura.getText().trim().length()>0){
                            if(luxuryTax.getSelectedIndex()!=-1){
                                if(max_Capacity.getText()!=null && max_Capacity.getText().trim().length()>0){
                                       if(check_IN.getText()!=null && check_IN.getText().trim().length()>0 && check_OUT.getText()!=null && check_OUT.getText().trim().length()>0){
                                           if(max_days.getText()!=null && max_days.getText().trim().length()>0  &&  payment_Text.getText().trim().length()>0  ){
                                                if(All_RNo !=null ){
                                           
                                                 if(Adv_acount_combo.getSelectedIndex()!=-1 && Advance_Acct_ID!=null){   
                                                    if(reve_accnt_combo.getSelectedIndex()!=-1 && Revenue_Acct_ID!=null){
                                                           if(cancel_acct_combo.getSelectedIndex()!=-1 && Cancel_Acct_ID!=null){ 
                                           
                                           
                                                
                                                               
                                                               
                                                Total_Rooms = Integer.parseInt(roomNo.getText());
                                                mem_tarif = Double.parseDouble(m_tariff.getText());
                                                non_mem_tariff = Double.parseDouble(n_tariff.getText());
                                                facility = facilities.getText();
                                                ADVANCE_BOOKING_DURA = Integer.parseInt(advnceBooking_dura.getText());

                                                Payment_Days = Integer.parseInt(payment_Text.getText());

                                                try {
                                                    Check_IN_Time = (Date) Formats.TIME.parseValue(check_IN.getText());
                                                    Check_Out_time = (Date) Formats.TIME.parseValue(check_OUT.getText());
                                                } catch (BasicException ex) {
                                                    Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                                Max_days = Integer.parseInt(max_days.getText());

                                                block_Flag = 0;
                                                block_rooms = 0;

                                                max_capacity = Integer.parseInt(max_Capacity.getText());
                                                if(m_jimageIcon!=null){
                                                    room_icon = m_jimageIcon.getImage();
                                                }
                                                if(basic.isSelected()==true){ 
                                                    basicTax1=1;
                                                    cascadeTax1=0;
                                                }
                                                 else{ 
                                                    cascadeTax1=1;
                                                    basicTax1=0;
                                                }        

                                                if(basic2.isSelected()){
                                                    basicTax2 = 1;
                                                    cascadeTax2 = 0;
                                                }
                                                else{
                                                    cascadeTax2 = 1;
                                                    basicTax2 = 0;
                                                }

                                                if (luxuryTax.getSelectedItem() != null) {
                                                    TaxCategoryInfo luxurytax  = (TaxCategoryInfo) luxuryTax.getSelectedItem(); 
                                                    Luxury_tax_Id = luxurytax.getID();

                                                }   

                                                if(tax2.getSelectedItem()!= null && tax2.getSelectedIndex()!=-1){

                                                   TaxCategoryInfo tci2  = (TaxCategoryInfo) tax2.getSelectedItem();                         
                                                    Tax2_id = tci2.getID(); 
                                                }
                                                else{
                                                    Tax2_id = null;
                                                }        


                                                if (tax3.getSelectedItem() != null && tax3.getSelectedIndex()!=-1)                
                                                {                          
                                                    TaxCategoryInfo tci3  = (TaxCategoryInfo) tax3.getSelectedItem();
                                                    Tax3_id = tci3.getID(); 

                                                }
                                                else{
                                                    Tax3_id=null;
                                                }


                                              TypeOfRoom = typeOfRoom.getText();

                                              if(available_text.getText()!=null){ 
                                              available_text.setText(""+Availibility_perc);
                                              Availibility_perc = Double.parseDouble(available_text.getText());
                                              }
                                              else{
                                                  Availibility_perc = 100.00;
                                              }


                                              if(availroomText.getText()!=null){
                                              rooms_available = (int) ((Total_Rooms * Availibility_perc)/100);
                                              availroomText  .setText(""+rooms_available);
                                              rooms_available = Integer.parseInt(availroomText.getText());
                                              }
                                              else{
                                                  rooms_available = (int) ((Total_Rooms * Availibility_perc)/100);
                                              }


                                              if(full_radio_btn.isSelected()){
                                                 Advance_perc = "100-0-0"; 
                                              }



                                              ID = UUID.randomUUID().toString(); 

                                              id.setText(ID);




                                                 Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                                                @Override      
                                                protected Object transact() throws BasicException {   



                                                insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_master (ID, TOTAL_ROOMS, MEM_TARIFF, N_MEM_TARIFF, LUXURYTAX, TAX2, TAX3, FACILITY, CRBY, CRDATE, CRHOST, BASIC1, CASCADE1, ACTIVE, ROOM_ICON, ROOMTYPE, AVAILIBILITY, ADVNCE_BOOK_DURA, ROOMS_AVAILABLE , MAX_CAPACITY , CHECK_IN , CHECK_OUT , MAX_DAYS,BASIC2,CASCADE2, PAYMENT_DAYS , BLOCK_FLAG , BLOCK_ROOMS , ADVANCE_PERC , ROOM_NOS , ADVNCE_ACCT , REVENUE_ACCT , CANCEL_ACCT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.INT, Datas.DOUBLE ,Datas.DOUBLE, Datas.STRING,Datas.STRING , Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING , Datas.INT ,Datas.INT , Datas.INT  , Datas.IMAGE , Datas.STRING , Datas.DOUBLE , Datas.INT , Datas.INT , Datas.INT , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.INT ,Datas.INT , Datas.INT , Datas.INT , Datas.INT , Datas.INT , Datas.STRING , Datas.STRING ,  Datas.STRING , Datas.STRING , Datas.STRING})                         
                                                ).exec(new Object[]{ID ,Total_Rooms ,mem_tarif ,non_mem_tariff ,Luxury_tax_Id , Tax2_id , Tax3_id ,facility , m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , basicTax1 ,cascadeTax1 , active , room_icon , TypeOfRoom , Availibility_perc , ADVANCE_BOOKING_DURA , rooms_available , max_capacity , Check_IN_Time , Check_Out_time , Max_days , basicTax2 , cascadeTax2 , Payment_Days , block_Flag , block_rooms , Advance_perc , All_RNo , Advance_Acct_ID , Revenue_Acct_ID , Cancel_Acct_ID});                                                                                                





                                               insert_image =  new PreparedSentence(m_App.getSession() , "INSERT INTO guestroom_images (ID , IMAGE1  , IMAGE2 , IMAGE3  ) VALUES (?,?,?,?)"                      
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.IMAGE ,Datas.IMAGE, Datas.IMAGE }) ).exec(new Object[]{ID , Image1 , Image2 , Image3 });        



                                                    return null;                                      
                                                    }                            
                                                };                 



                                                try {                 
                                                    t.execute();          


                                                    loaddata();
                                                    insert_data = 0;
                                                    insert_image =0;
                                                    JOptionPane.showMessageDialog(this, "Data Saved  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);                  

                                                     reset();  


                                                } 



                                                catch (BasicException ex) {   
                                                     ex.printStackTrace();
                                                            new MessageInf(ex).show(new JFrame());   
                                                        Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                                                    if(insert_data!=1){
                                                      ex.printStackTrace();
                                                            new MessageInf(ex).show(new JFrame());           
                                                    } 
                                                    else{
                                                       JOptionPane.showMessageDialog(this, " Images exceeds the limit , more than 64 KB. Try again ", " Images  not saved", JOptionPane.ERROR_MESSAGE);                  

                                                    }  
                                                    insert_data = 0;
                                                    insert_image =0;
                                                } 

                                                
                                                
                                                
                                                
                                                
                                                
                                                           }
                                                           else{
                                                               JOptionPane.showMessageDialog(this, " Select Cancellation Account.. !!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                                           }
                           
                        
                                                   }
                                                 else{
                                                       JOptionPane.showMessageDialog(this, " Select Revenue Account.. !!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                                 }
                                                
                                                 }
                                                 else{
                                                      JOptionPane.showMessageDialog(this, " Select Advance Account.. !!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                                 }
                        
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(this, " Give Room Nos.  !!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                                }
                          
                                       }
                                           else{
                                               if(payment_Text.getText().trim().length()==0){
                                                   JOptionPane.showMessageDialog(this, " Enter  Advance Payment duration (Days). ", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                               }
                                               else{
                                                   JOptionPane.showMessageDialog(this, "Enter Max. No. of Days To be Booked ..!!", " Incomplete form", JOptionPane.WARNING_MESSAGE);  
                                               }
                                               
                                              
                                           }
                                       }
                                       else{
                                           JOptionPane.showMessageDialog(this, "Enter Check In and Check Out Timings ..!!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                       }
                       
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "Enter Max. Capacity ..!!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                                }
                       }
                       
                      else{
                             JOptionPane.showMessageDialog(this, "Select  Tax 1 ..!!", " Incomplete form", JOptionPane.WARNING_MESSAGE); 
                          }
                      }
                     
                      else{
                        JOptionPane.showMessageDialog(this, "please enter Advance Booking Duration (Months) ..!!", " Incomplete form", JOptionPane.ERROR_MESSAGE);
                    }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Non-Member tariff should not be empty..!!", " Incomplete form", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Member tariff should not be empty..!!", " Incomplete form", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Enter Room No..!!", " Incomplete form", JOptionPane.ERROR_MESSAGE);
            }
      }
      else{
          JOptionPane.showMessageDialog(this, "Enter Room Type..!!", " Incomplete form", JOptionPane.ERROR_MESSAGE);
      }
       
        
        
        
    }//GEN-LAST:event_saveActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
       reset();
       jTabbedPane1.setSelectedIndex(0);
       
       
    }//GEN-LAST:event_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(jTable1.getSelectedColumn()!=-1){
           edit.setEnabled(true);
           delete.setEnabled(true);
           int row = jTable1.getSelectedRow();
           block_button.setEnabled(true);
           
          
       }
       else{
           edit.setEnabled(false);
           delete.setEnabled(false);
           block_button.setEnabled(false);
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
       
        if(jTable1.getSelectedRow()!=-1){
             if(jTable1.getSelectedRow()<GuestRoomTable.getGuestRoomSize()){
                 int row = jTable1.getSelectedRow();
                 GuestRoomTableInfo EditData = GuestRoomTable.getGuestRoomPath().get(row);
                 
                 save.setVisible(false);
                 updateChanges.setVisible(true); 
                 
                 roomNo.setText(""+EditData.getRoom_no());
                 m_tariff.setText(decimalFormat.format(EditData.getMem_tariff()));
                 n_tariff.setText(decimalFormat.format(EditData.getNon_mem_tariff()));
                 facilities.setText(EditData.getFacilities());
                 jTabbedPane1.setSelectedIndex(1);
                 
                 
                 available_text.setText(""+EditData.getAvailibility_perc());
                 Availibility_perc = EditData.getAvailibility_perc();
                 availroomText.setText(""+EditData.getRooms_available());
                 totalRoomsText.setText(""+EditData.getRoom_no());
                 max_days.setText(""+EditData.getMAX_DAYS());
                 
                 typeOfRoom.setText(EditData.getRoomType());
                 m_jimageIcon.setImage(EditData.getIcon());
                
                 advnceBooking_dura.setText(""+EditData.getadvance_booking_dura());
                 Image1 = EditData.getIMAGE1();
                 Image2 = EditData.getIMAGE2();
                 Image3 = EditData.getIMAGE3();
                
                 max_Capacity.setText(""+EditData.getMAX_CAPACITY());
                 check_IN.setText(Formats.TIME.formatValue(EditData.getCHECK_IN_TIME()));
                 check_OUT.setText(Formats.TIME.formatValue(EditData.getCHECK_OUT_TIME()));
                 
                 payment_Text.setText(""+EditData.getPAYMENT_DAYS());
                 
                 member.setSelected(true);
                 non_member.setSelected(true);
                 m_tariff.setVisible(true);
                 n_tariff.setVisible(true);
                 jLabel32.setVisible(true);
                 jLabel44.setVisible(true);
                 selectMoreTax.setSelected(true);
                 selectMoreTax1.setSelected(true);
                 tax2.setVisible(true);
                 tax3.setVisible(true);
                 selectMoreTax.setVisible(true);
                 selectMoreTax1.setVisible(true);
                 basic.setVisible(true);
                 cascade.setVisible(true);
                 jLabel16.setVisible(true);
                 typeOfRoom.setVisible(true);
             
              
                m_jImage.setImage(Image1);
                m_jImage1.setImage(Image2);
                m_jImage2.setImage(Image3);
                
                
                
                 int x = EditData.getBasic1();
                 if(x==1){
                     basic.setSelected(true);
                 }
                 else{
                     cascade.setSelected(true);
                 }
                 
                 int y = EditData.getBasic2();
                 if(y==1){
                     basic2.setSelected(true);
                 }
                 else{
                     cascade2.setSelected(true);
                 }
                 
               
                 BufferedImage bi = EditData.getIcon();
                 if(bi!=null){
                 ImageIcon IC = new ImageIcon(bi);
                
               
             
                 }
                 
                
                 
                 String luxurytax = EditData.getLuxuryTax();
                 for(int i = 1 ; i < staxmodel.getSize(); i++ )
                 {
                     TaxCategoryInfo temp  = (TaxCategoryInfo) staxmodel.getElementAt(i);
                     
                     if(temp.getName().equals(luxurytax)){
                         luxuryTax.setSelectedIndex(i);
                     }
                 }
                 
                 
                 if(EditData.getTax_2()!=null){
                 String tax2Temp = EditData.getTax_2();
                 for(int i = 1 ; i < stax2.getSize(); i++ )
                 {
                     TaxCategoryInfo temp  = (TaxCategoryInfo) stax2.getElementAt(i);
                     
                     if(temp.getName().equals(tax2Temp)){
                         tax2.setSelectedIndex(i);
                     }
                 }
                 }
                 else{
                     selectMoreTax.setSelected(false);
                 }
                 
                 
                 
                 
                 if(EditData.getTax_3()!=null){
                 String tax3Temp = EditData.getTax_3();
                 for(int i = 1 ; i < stax3.getSize(); i++ )
                 {
                     TaxCategoryInfo temp  = (TaxCategoryInfo) stax3.getElementAt(i);
                     
                     if(temp.getName().equals(tax3Temp)){
                         tax3.setSelectedIndex(i);
                     }
                 }
                 }
                 else{
                     selectMoreTax1.setSelected(false);
                     tax3.setSelectedIndex(-1);
                 }
                 
                 
                   String ADVNCE_ACCT = EditData.getADVNCE_ACCT();
                 if(ADVNCE_ACCT!=null && ADVNCE_ACCT.length()>0){
                      for(int i=0;i<acclist.size();i++){
                          String x1 = acclist.get(i).toString();
                          if(x1.equals(ADVNCE_ACCT)){
                              Adv_acount_combo.setSelectedIndex(i);
                          }
                         }
                 }
                 else{
                     Adv_acount_combo.setSelectedIndex(-1);
                 }
                
                String REVENUE_ACCT = EditData.getREVENUE_ACCT();
                 if(REVENUE_ACCT!=null && REVENUE_ACCT.length()>0){
                      for(int i=0;i<sacclist.size();i++){
                          String x2 = sacclist.get(i).toString();
                          if(x2.equals(REVENUE_ACCT)){
                              reve_accnt_combo.setSelectedIndex(i);
                          }
                         }
                 }
                 else{
                      reve_accnt_combo.setSelectedIndex(-1);
                  }
                 
                 String CANCEL_ACCT = EditData.getCANCEL_ACCT();
                 if(CANCEL_ACCT!=null && CANCEL_ACCT.length()>0){
                      for(int i=0;i<sacclist.size();i++){
                          String x2 = sacclist.get(i).toString();
                          if(x2.equals(CANCEL_ACCT)){
                              cancel_acct_combo.setSelectedIndex(i);
                          }
                         }
                 }
                 else{
                      cancel_acct_combo.setSelectedIndex(-1);
                  }
                 
                 
                 String Z =  EditData.getADVANCE_PERC();
                 if(Z!=null){
                        String[] t = Z.split("-");
                        Adv_Perc.setText(t[0]);
                        Chk_in_perc.setText(t[1]);
                        chk_out_perc.setText(t[2]);
                        partial_radio_btn.setSelected(true);
                        
                        int z1 = Integer.parseInt(t[0]);
                        if(z1==100){
                            full_radio_btn.setSelected(true);
                        }
                        
                        
                 }
                 
                 
                 
                 
                 
                 
                 
                 id.setText(EditData.getId());
                 ID_update = id.getText();
                 
                 All_RNo = EditData.getRoom_Nos();
                 
             }
        }
        
    }//GEN-LAST:event_editActionPerformed

    private void updateChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateChangesActionPerformed
     
           if(roomNo.getText()!=null && roomNo.getText().trim().length()>0){
               if(m_tariff.getText()!=null && m_tariff.getText().trim().length()>0){
                    if(n_tariff.getText()!=null && n_tariff.getText().trim().length()>0){
                      if(advnceBooking_dura.getText()!=null && advnceBooking_dura.getText().trim().length()>0){
                            if(max_Capacity.getText()!=null && max_Capacity.getText().trim().length()>0){
                                if(max_days.getText()!=null && max_days.getText().trim().length()>0){
                                   
                    
                                    
                 try {
                      
                       
                        Total_Rooms = Integer.parseInt(roomNo.getText());
                        mem_tarif = Double.parseDouble(m_tariff.getText());
                        non_mem_tariff = Double.parseDouble(n_tariff.getText());
                        facility = facilities.getText();
                        ADVANCE_BOOKING_DURA = Integer.parseInt(advnceBooking_dura.getText());
                        Max_days = Integer.parseInt(max_days.getText());
                        max_capacity = Integer.parseInt(max_Capacity.getText());
                      
                        TypeOfRoom = typeOfRoom.getText();
                        
                       try {
                            Check_IN_Time = (Date) Formats.TIME.parseValue(check_IN.getText());
                            Check_Out_time = (Date) Formats.TIME.parseValue(check_OUT.getText());
                        } catch (BasicException ex) {
                            Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                       
                        room_icon = m_jimageIcon.getImage();
                        if(basic.isSelected()==true){ 
                            basicTax1=1;
                            cascadeTax1=0;
                        }
                         else{ 
                            basicTax1=0;
                            cascadeTax1=1;
                        }        
                         if(basic2.isSelected()){
                             basicTax2 = 1;
                             cascadeTax2=0;
                         }
                         else{
                             basicTax2 = 0;
                             cascadeTax2 = 1;
                         }
                                
                        if (luxuryTax.getSelectedItem() != null) {
                            TaxCategoryInfo luxurytax  = (TaxCategoryInfo) luxuryTax.getSelectedItem(); 
                            Luxury_tax_Id = luxurytax.getID();

                        }   
                                
                        if(tax2.getSelectedItem()!= null && tax2.getSelectedIndex()!=-1){
                            
                           TaxCategoryInfo tci2  = (TaxCategoryInfo) tax2.getSelectedItem();                         
                            Tax2_id = tci2.getID(); 
                        }
                        else{
                            Tax2_id = null;
                        }        
                                
                         
                        if (tax3.getSelectedItem() != null && tax3.getSelectedIndex()!=-1)                
                        {                          
                            TaxCategoryInfo tci3  = (TaxCategoryInfo) tax3.getSelectedItem();
                            Tax3_id = tci3.getID(); 
                        
                        }
                        else{
                            Tax3_id=null;
                        }
                        
                        
                      if(available_text.getText()!=null){ 
                      Availibility_perc = Double.parseDouble(available_text.getText());
                      }
                      else{
                          Availibility_perc = 100;
                      }
                        
                      
                      if(availroomText.getText()!=null){
                      rooms_available = Integer.parseInt(availroomText.getText());
                      }
                      else{
                          rooms_available = (int) ((Total_Rooms * Availibility_perc)/100);
                      }
                     
                      
                      if(full_radio_btn.isSelected()){
                           Advance_perc = "100-0-0"; 
                      }
                      else{
                         int z1 = Integer.parseInt(Adv_Perc.getText()); 
                         int z2 = Integer.parseInt(Chk_in_perc.getText()); 
                         int z3 = Integer.parseInt(chk_out_perc.getText());
                         
                         
                         Advance_perc = z1+"-"+z2+"-"+z3;
                         
                         
                      }
                      
                       String Adv_acc_name = Adv_acount_combo.getSelectedItem().toString();
                       String Rev_Acc_name = reve_accnt_combo.getSelectedItem().toString();
                       String Cancel_Acc_Name = cancel_acct_combo.getSelectedItem().toString();
                             try {
                                 Advance_Acct_ID = dlfac.getaccountidByName(Adv_acc_name);
                                 Revenue_Acct_ID = dlfac.getaccountidByName(Rev_Acc_name);
                                 Cancel_Acct_ID = dlfac.getaccountidByName(Rev_Acc_name);
                             } catch (BasicException ex) {
                                 Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        
                      ID_update = id.getText();
                        
                     Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                                                              
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                            
                       
                      
                      
                              int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_master  SET ACTIVE=true , TOTAL_ROOMS=? , MEM_TARIFF=? ,N_MEM_TARIFF=? , LUXURYTAX=? , TAX2=? , TAX3=? , BASIC1=? , CASCADE1=? , FACILITY=? , CRBY=? ,  CRDATE=? , CRHOST=?  , ROOM_ICON=? , ROOMTYPE=? , ADVNCE_BOOK_DURA=? , AVAILIBILITY=? , ROOMS_AVAILABLE=? , MAX_CAPACITY=? , CHECK_IN=? , CHECK_OUT=? , MAX_DAYS=? , BASIC2=? , CASCADE2=?  , PAYMENT_DAYS=? , ADVANCE_PERC=? , ROOM_NOS=? , ADVNCE_ACCT=?,  REVENUE_ACCT=? , CANCEL_ACCT=? WHERE ID =? "
                                                   , new SerializerWriteBasic(new Datas[]{ Datas.INT,Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT ,Datas.INT , Datas.STRING , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.IMAGE , Datas.STRING , Datas.INT ,Datas.DOUBLE , Datas.INT ,Datas.INT ,Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.INT , Datas.INT , Datas.INT ,Datas.INT , Datas.STRING ,    Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING , Datas.STRING })).exec
                                                    (new Object[]{Total_Rooms , mem_tarif , non_mem_tariff ,  Luxury_tax_Id , Tax2_id , Tax3_id  , basicTax1 , cascadeTax1 , facility ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , room_icon ,TypeOfRoom , ADVANCE_BOOKING_DURA ,Availibility_perc , rooms_available , max_capacity ,Check_IN_Time , Check_Out_time , Max_days ,basicTax2 , cascadeTax2 ,Payment_Days ,Advance_perc , All_RNo ,   Advance_Acct_ID , Revenue_Acct_ID , Cancel_Acct_ID ,  ID_update });
                                
                         
                        if(count==1){
                            
                              int update_Guest_images =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_images  SET IMAGE1=? , IMAGE2=?, IMAGE3=?  WHERE ID =? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.IMAGE ,Datas.IMAGE , Datas.IMAGE , Datas.STRING   })).exec
                                                    (new Object[]{ Image1 , Image2 , Image3 , ID_update });
                                
                            
                        }
                              
                       
                        
                        
                      return null;                                      
                            }                            
                        };                 
                          
                              try {                 
                                t.execute();          
                                JOptionPane.showMessageDialog(this, "Data Updated  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);      
                                loaddata(); 
                                 reset();
                                 jTabbedPane1.setSelectedIndex(0);
                                Reset_Mem_Master(ID_update);
                        }
                       catch (BasicException ex) {   
                              ex.printStackTrace();
                                                            new MessageInf(ex).show(new JFrame());        
                              Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);             
                       }  
                        
                        
                        
                        
                       }
                         catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(this, " Number Format exception", " Exception", JOptionPane.WARNING_MESSAGE);               
                        }
                       
                   
                            }
                                else{
                                    JOptionPane.showMessageDialog(this, " Enter Max no of days..!", " Max_Days", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else{
                                 JOptionPane.showMessageDialog(this, " Enter Max Capacity..!!", " ADVNCE_BOOKING", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                            else{
                                JOptionPane.showMessageDialog(this, " Enter Advance Booking Duration..!!", " ADVNCE_BOOKING", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                       
                    else{
                        JOptionPane.showMessageDialog(this, "Non-Member tariff should not be empty..!!", " n_tariff", JOptionPane.WARNING_MESSAGE);
                    }
               }
               else{
                   JOptionPane.showMessageDialog(this, "Member tariff should not be empty..!!", " tariff", JOptionPane.WARNING_MESSAGE);
               }
           }
           else{
               JOptionPane.showMessageDialog(this, "Enter No of Rooms....!!", " Room No", JOptionPane.WARNING_MESSAGE);
           }
       
     
        
        
        
        
        
        
        
    }//GEN-LAST:event_updateChangesActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
      
            if(jTable1.getSelectedRow()!=-1){
                if(jTable1.getSelectedRow()<GuestRoomTable.getGuestRoomSize()){
                
                int delete_room = JOptionPane.showConfirmDialog(jPanel3, "Do you want to Deactivate Room ?? "  , "Deactivation" , JOptionPane.YES_NO_OPTION);
                     if(delete_room == JOptionPane.YES_OPTION)
                        {
            
                 
                 int row = jTable1.getSelectedRow();
                 GuestRoomTableInfo DeleteData = GuestRoomTable.getGuestRoomPath().get(row);
                 
                 id.setText(DeleteData.getId());
                 String del_ID = id.getText();
                try {
                    int num =  new PreparedSentence(m_App.getSession(), "UPDATE  guestroom_master set ACTIVE=false , DEACBY=? , DEACDATE=? , DEACHOST=? where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost() , del_ID});
                    //int num1 =  new PreparedSentence(m_App.getSession(), "delete from guestroom_images where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{del_ID});
                    
                    
                    JOptionPane.showMessageDialog(this, "Guest Room  Deactivated   Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);                  
                    loaddata();
                
                } catch (BasicException ex) {
                    Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 
             }
       }
            
            
            
        }
        
        
    }//GEN-LAST:event_deleteActionPerformed

    

    
    
    
    BufferedImage Final_icon ;
    private void showAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showAllItemStateChanged
        try {
            loaddata();
        } catch (BasicException ex) {
            Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showAllItemStateChanged

    private void selectMoreTax1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMoreTax1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMoreTax1ActionPerformed

    private void memberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberItemStateChanged
if(member.isSelected()){
          m_tariff.setVisible(true);
          jLabel32.setVisible(true);
       }
      else{
          m_tariff.setVisible(false);
          jLabel32.setVisible(false);
          m_tariff.setText(null);
      }      
    }//GEN-LAST:event_memberItemStateChanged

    private void non_memberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_non_memberItemStateChanged
if(non_member.isSelected()){
            n_tariff.setVisible(true);
            jLabel44.setVisible(true);
       
        }
        else{
            n_tariff.setVisible(false);
            n_tariff.setText(null);
            jLabel44.setVisible(false);
        }     
    }//GEN-LAST:event_non_memberItemStateChanged

    private void m_jimageIconAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_m_jimageIconAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jimageIconAncestorAdded

    private void roomNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roomNoKeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
    if(!Character.isDigit(c))
    {
        JOptionPane.showMessageDialog(roomNo, "Please enter only numbers..");
            
            roomNo.setText(null);
    }
    }
    }//GEN-LAST:event_roomNoKeyReleased

    private void m_tariffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_tariffKeyReleased
        char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(m_tariff, "Please enter only numbers..");
    
            m_tariff.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_m_tariffKeyReleased

    private void n_tariffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_n_tariffKeyReleased
        char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(n_tariff, "Please enter only numbers..");
    
            n_tariff.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_n_tariffKeyReleased

    private void advnceBooking_duraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advnceBooking_duraKeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(advnceBooking_dura, "Please enter only numbers..");
    
            advnceBooking_dura.setText(null);
         
    }
    }
    }//GEN-LAST:event_advnceBooking_duraKeyReleased

    private void available_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_available_textKeyReleased
        char c = evt.getKeyChar();

        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {   
                if(c!='.')
                {
                    
                JOptionPane.showMessageDialog(available_text, "Please enter only numbers..");

                available_text.setText(null);

            }
        }
        }
    }//GEN-LAST:event_available_textKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      if(typeOfRoom.getText()!=null && typeOfRoom.getText().trim().length()>0){
          if(roomNo.getText()!=null && roomNo.getText().trim().length()>0){
                main_panel.setVisible(false);
                availibility_panel.setVisible(true);
                
                jTextField1.setText(typeOfRoom.getText());
                int total_rooms = Integer.parseInt(roomNo.getText());
                double availibility_temp = Availibility_perc;
                totalRoomsText.setText(""+total_rooms);
                
                available_text.setText(""+Availibility_perc);
                
                int rooms_avail_t = (int) ((total_rooms*availibility_temp)/100);
                
                availroomText.setText(""+rooms_avail_t);
                Icon ic = new ImageIcon();
                if(m_jimageIcon.getImage()!=null){
                    BufferedImage bi  = m_jimageIcon.getImage();
                    ic = new ImageIcon(bi);
                }
                else{
                    ic = new ImageIcon(getClass().getResource("/com/openbravo/images/home.png"));
                   
               }
                
                showAvailibilityIcons(rooms_avail_t ,ic ,total_rooms);
                
                
                
                
                
          }
          else{
              JOptionPane.showMessageDialog(this, "Enter Room No..!!", " Room No", JOptionPane.ERROR_MESSAGE);
          }
     }
      else{
          JOptionPane.showMessageDialog(this, "Enter Room Type..!!", " Room No", JOptionPane.ERROR_MESSAGE);
      }
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(available_text.getText()!=null && available_text.getText().trim().length()>0){
            
               int total_rooms = Integer.parseInt(roomNo.getText());
               Double availibility_temp = Double.parseDouble(available_text.getText());
               
               totalRoomsText.setText(""+total_rooms);
              
               int rooms_avail_t = (int) ((total_rooms*availibility_temp)/100);
                
               availroomText.setText(""+rooms_avail_t);
               Icon ic = null;
               if(m_jimageIcon.getImage()!=null){
                    BufferedImage bi  = m_jimageIcon.getImage();
                    ic = new ImageIcon(bi);
               }
               else{
                    ic = new ImageIcon(getClass().getResource("/com/openbravo/images/home.png"));
                   
               }
                
               showAvailibilityIcons(rooms_avail_t ,ic ,total_rooms);
            
            
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Availibility percent should not be empty..!!", " Room No", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void save_availActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_availActionPerformed
        
         if(available_text.getText()!=null && available_text.getText().trim().length()>0){
            Availibility_perc = Double.parseDouble(available_text.getText());
            
            availibility_panel.setVisible(false);
            main_panel.setVisible(true);
        
              
        }
        else{
            JOptionPane.showMessageDialog(this, "Availibility percent should not be empty..!!", " Room No", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_save_availActionPerformed

    private void cancel_availActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_availActionPerformed
       main_panel.setVisible(true);
       availibility_panel.setVisible(false);
       
    }//GEN-LAST:event_cancel_availActionPerformed

    private void max_CapacityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_max_CapacityKeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(max_Capacity, "Please enter only numbers..");
    
            max_Capacity.setText(null);
         
    }
    }
    }//GEN-LAST:event_max_CapacityKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         Date date1=new Date();
       try {
            date1 = (Date) Formats.TIME.parseValue(check_IN.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, date1);
        if (date1 != null) {
           
                check_IN.setText(Formats.TIME.formatValue(date1));
           
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       Date date1=new Date();
       try {
            date1 = (Date) Formats.TIME.parseValue(check_IN.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, date1);
        if (date1 != null) {
           
                check_OUT.setText(Formats.TIME.formatValue(date1));
           
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void max_daysKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_max_daysKeyReleased
        char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
    if(!Character.isDigit(c))
    {
        JOptionPane.showMessageDialog(max_days, "Please enter only numbers..");
            
            max_days.setText(null);
    }
    }
    }//GEN-LAST:event_max_daysKeyReleased

    private void tax3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tax3ActionPerformed
      
    }//GEN-LAST:event_tax3ActionPerformed

    private void payment_TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payment_TextKeyReleased
        char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(payment_Text, "Please enter only numbers..");
    
            payment_Text.setText(null);
         
    }
    }
    }//GEN-LAST:event_payment_TextKeyReleased

    private void block_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_block_buttonActionPerformed
        if(jTable1.getSelectedRow()!=-1){
             if(jTable1.getSelectedRow()<GuestRoomTable.getGuestRoomSize()){
                 int row = jTable1.getSelectedRow();
                 GuestRoomTableInfo EditData = GuestRoomTable.getGuestRoomPath().get(row);
                  
                 room_label.setText(EditData.getRoomType());
                 capacity_label.setText(""+EditData.getMAX_CAPACITY());
                 total_room_label.setText(""+EditData.getRooms_available());
                 ID_label.setText(EditData.getId());
                 block_rooms_text.setText(null);
                 BLOCK_FROM_TEXT.setText(null);
                 BLOCK_UPTO_TEXT.setText(null);
                 
                 if(EditData.getBLOCK_FLAG()==1){
                     jLabel33.setVisible(true);
                     jLabel35.setVisible(true);
                     from_date_label.setVisible(true);
                     jLabel34.setVisible(true);
                     to_date_label.setVisible(true);
                     blocked_rooms_label.setVisible(true);
                     String ID_T =  ID_label.getText();
                     Date From_Date = GuestRoomTable.getBlock_From_Date(ID_T , m_App);
                     Date To_Date =GuestRoomTable.getBlock_To_Date(ID_T , m_App);
                     from_date_label.setText(Formats.DATE.formatValue(From_Date));
                     to_date_label.setText(Formats.DATE.formatValue(To_Date));
                     blocked_rooms_label.setText(""+EditData.getBLOCK_ROOMS());
                     Submit.setVisible(false);
                     jButton8.setVisible(true);
                     
                 }
                 else{
                      jLabel33.setVisible(false);
                      jLabel35.setVisible(false);
                      from_date_label.setVisible(false);
                      jLabel34.setVisible(false);
                      to_date_label.setVisible(false);
                      blocked_rooms_label.setVisible(false);
                      Submit.setVisible(true);
                      jButton8.setVisible(false);
                 }
            
            
                 block_panel.setVisible(true);
                 main_panel.setVisible(false);
                 
             }
        }
        else{
          block_button.setEnabled(false);
        }
    }//GEN-LAST:event_block_buttonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       if(block_rooms_text.getText()!=null && block_rooms_text.getText().trim().length()>0){
        Date date=new Date();
                  Date Currdate = new Date();
                  Calendar c = Calendar.getInstance();
                  c.setTimeInMillis(new Date().getTime());
                  c.add(Calendar.DATE, -1);
                  Currdate = c.getTime();
                  
        
        
        try {
            date = (Date) Formats.DATE.parseValue(BLOCK_FROM_TEXT.getText());
        } catch (BasicException e) {
            date = null;
        }
        try{
            date = JCalendarDialog.showCalendar(this, date);
            if (date != null) {
                if(date.after(Currdate))
                {

                    BLOCK_FROM_TEXT.setText(Formats.DATE.formatValue(date));
                    BLOCK_UPTO_TEXT.setText(null);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, " Select Date After Current Date.!!! ");
                }

            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
       }
       else{
            JOptionPane.showMessageDialog(this, " Select No. of Rooms to be blocked !!! ");
       }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(BLOCK_FROM_TEXT.getText()!=null && BLOCK_FROM_TEXT.getText().trim().length()>0){
            List<Date> Booking_Dates = new ArrayList<Date>();
            int Rooms_to_be_block = Integer.parseInt(block_rooms_text.getText());
            int total_rooms = Integer.parseInt(total_room_label.getText());
            String ID = ID_label.getText();
            Date From_Date=new Date();
            Date To_Date = new Date();
            
            
            
            try {
                From_Date = (Date) Formats.DATE.parseValue(BLOCK_FROM_TEXT.getText());
            }
            catch (BasicException e) {
                From_Date = new Date();
            }

            try{
                To_Date = JCalendarDialog.showCalendar(this, From_Date);
                if (To_Date != null) {
                    if(To_Date.after(From_Date) || To_Date.equals(From_Date))
                    {

                      
                            Booking_Dates =  getBooked_Rooms_For_Blocking(From_Date, To_Date);
                            if(Booking_Dates.size()!=0){
                                for(int i=0;i<Booking_Dates.size();i++){
                                            Date bk_date = Booking_Dates.get(i);
                                            int Booked_Rooms = GuestRoomTable.getRooms_Available(bk_date, ID, m_App);
                                            if(Booked_Rooms!=0){
                                               if(Rooms_to_be_block<=(total_rooms - Booked_Rooms)){ 
                                                
                                                  BLOCK_UPTO_TEXT.setText(Formats.DATE.formatValue(To_Date));
                                                   
                                               }
                                               else{
                                                   
                                                    JOptionPane.showMessageDialog(this, " Sorry "+Booked_Rooms+" Rooms are already blocked on :"+Formats.DATE.formatValue(bk_date));
                                                    break;
                                               }
                                            }        
                                                    
                                    }
                            }
                            else{
                                 BLOCK_UPTO_TEXT.setText(Formats.DATE.formatValue(To_Date));
                            }
                      

                         
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, " Select Date After Current Date.!!! ");
                    }

                }
            }catch(Exception e1){
                e1.printStackTrace();
            }

        }
        else{
            JOptionPane.showMessageDialog(this, " Select From Date First.!!! ");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       main_panel.setVisible(true);
       block_panel.setVisible(false);
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void block_rooms_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_block_rooms_textKeyReleased
        char c = evt.getKeyChar();
      int i = Integer.parseInt(total_room_label.getText());
      
       BLOCK_FROM_TEXT.setText(null);
       BLOCK_UPTO_TEXT.setText(null);
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(block_rooms_text, "Please enter only numbers..");
        
            block_rooms_text.setText(null);
         
    }
     else{
       int y = Integer.parseInt(block_rooms_text.getText());
       if(y>i){
           JOptionPane.showMessageDialog(block_rooms_text, " Block Rooms Should not be greater than Total rooms..!");
            block_rooms_text.setText(null);
          
       }
       
    }
    
    
    } 
   
    
    
    
    
    }//GEN-LAST:event_block_rooms_textKeyReleased

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
       if(block_rooms_text.getText()!=null && block_rooms_text.getText().trim().length()>0){
          if(BLOCK_FROM_TEXT.getText()!=null && BLOCK_FROM_TEXT.getText().trim().length()>0){ 
            if(BLOCK_UPTO_TEXT.getText()!=null && BLOCK_UPTO_TEXT.getText().trim().length()>0){
              
               String ID  = ID_label.getText();
               int Block_rooms = Integer.parseInt(block_rooms_text.getText());
               int total_rooms = Integer.parseInt(total_room_label.getText());
              Date Block_from = new Date();
              Date Block_Upto = new Date();
              int getBooked_rooms = 0;  
              try {
                    Block_from = (Date) Formats.DATE.parseValue(BLOCK_FROM_TEXT.getText());
                    Block_Upto = (Date) Formats.DATE.parseValue(BLOCK_UPTO_TEXT.getText());
                } catch (BasicException ex) {
                    Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
             }
              
              
              
              
                try {
                    
                 LoadBlockRooms_To_Availiblity( Block_from ,  Block_Upto ,Block_rooms , total_rooms , ID);   
                    
                 int num =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_master SET BLOCK_FLAG=1 , BLOCK_ROOMS=? ,   BLOCK_BY=? , BLOCK_DATE=?, BLOCK_HOST=? , BLOCK_FROM=? , BLOCK_UPTO=? where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.STRING ,Datas.TIMESTAMP , Datas.STRING ,Datas.TIMESTAMP , Datas.TIMESTAMP ,  Datas.STRING , })).exec(new Object[]{Block_rooms , m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost() ,Block_from , Block_Upto , ID });
                
                  JOptionPane.showMessageDialog(this, "Rooms Blocked Succesfully..!", "Success", JOptionPane.INFORMATION_MESSAGE); 
                 loaddata();
                 block_panel.setVisible(false);
                 main_panel.setVisible(true);
                    
                 
                } catch (BasicException ex) {
                    Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(block_rooms_text, "Error in saving data ..!! .");
                    
                }
            
              
                
             
               
               
                      
            }
            else{
                JOptionPane.showMessageDialog(block_rooms_text, "Enter Blocking 'Upto' Date ..!! .");
            }
          }
          else{
              JOptionPane.showMessageDialog(block_rooms_text, "Enter Blocking 'From' Date ..!! .");
          }
       }
       else{
            JOptionPane.showMessageDialog(block_rooms_text, "Please Enter No. of blocking rooms..!! .");
       }
    }//GEN-LAST:event_SubmitActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int delete_room = JOptionPane.showConfirmDialog(block_panel, "Do you want to Unblock  Guest Room ?? " , "Guest Room " , JOptionPane.YES_NO_OPTION);
                     if(delete_room == JOptionPane.YES_OPTION)
                        {
                            
                             String ID_T = ID_label.getText();
                             Date From_Date = new Date();
                             Date Upto_Date = new Date();
                             int Blocked_Rooms = Integer.parseInt(blocked_rooms_label.getText());
                             
                             
                            try {
                                From_Date = (Date) Formats.DATE.parseValue(from_date_label.getText());
                                Upto_Date = (Date) Formats.DATE.parseValue(to_date_label.getText());
                                
                            } catch (BasicException ex) {
                                Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       
                            try {
                                
                                
                            int num =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_master SET BLOCK_FLAG=0 ,BLOCK_ROOMS=0 ,  UNBLOCK_BY=? , UNBLOCK_DATE=?, UNBLOCK_HOST=?  where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP , Datas.STRING ,  Datas.STRING  })).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost() , ID_T });
                            
                            
                            update_Availiblility_unblock(From_Date , Upto_Date , ID_T ,  Blocked_Rooms) ;
                            
                            loaddata();
                            block_panel.setVisible(false);
                            main_panel.setVisible(true);
                            
                            } catch (BasicException ex) {
                            Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                       
                            }
            
    
                            
                            
                            
                     
                            
                            
                            
                            
                            
                            
                            
                        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void full_radio_btnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_full_radio_btnItemStateChanged
        if(full_radio_btn.isSelected()){
            partial_variation_panel.setVisible(false);
            
        }
        else{
            partial_variation_panel.setVisible(true);
        }
    }//GEN-LAST:event_full_radio_btnItemStateChanged

    private void partial_radio_btnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_partial_radio_btnItemStateChanged
        if(partial_radio_btn.isSelected()){
            partial_variation_panel.setVisible(true);
        }
        else{
            partial_variation_panel.setVisible(false);
        }
    }//GEN-LAST:event_partial_radio_btnItemStateChanged

    private void Adv_PercKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Adv_PercKeyReleased
        char c = evt.getKeyChar();

        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {
                JOptionPane.showMessageDialog(Adv_Perc, "Please enter only numbers..");

                Adv_Perc.setText(null);

            }

        }
    }//GEN-LAST:event_Adv_PercKeyReleased

    private void Chk_in_percKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Chk_in_percKeyReleased
        char c = evt.getKeyChar();

        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {
                JOptionPane.showMessageDialog(Chk_in_perc, "Please enter only numbers..");

                Chk_in_perc.setText(null);

            }
        }
    }//GEN-LAST:event_Chk_in_percKeyReleased

    private void chk_out_percKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chk_out_percKeyReleased
        char c = evt.getKeyChar();

        if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
        {

            if(!Character.isDigit(c))
            {
                JOptionPane.showMessageDialog(chk_out_perc, "Please enter only numbers..");

                chk_out_perc.setText(null);

            }
        }
    }//GEN-LAST:event_chk_out_percKeyReleased

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        if(full_radio_btn.isSelected()){

            Advance_perc = "100-0-0";
            payment_frame.setVisible(false);
            main_panel.setVisible(true);

        }
        else{
            if(Adv_Perc.getText()!=null && Adv_Perc.getText().trim().length()>0){
                if(Chk_in_perc.getText()!=null && Chk_in_perc.getText().trim().length()>0){

                    String x = Adv_Perc.getText();
                    int X = Integer.parseInt(x);

                    String y = Chk_in_perc.getText();
                    int Y = Integer.parseInt(y);

                    int Z = 0;
                    if(chk_out_perc.getText()!=null && chk_out_perc.getText().trim().length()>0){

                        String z = chk_out_perc.getText();
                        Z = Integer.parseInt(z);

                    }

                    if((X+Y+Z)==100){
                        Advance_perc = X+"-"+Y+"-"+Z;
                        payment_frame.setVisible(false);
                        main_panel.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, " Summation should be 100 only..!! ", " Images  not saved", JOptionPane.ERROR_MESSAGE);
                        Chk_in_perc.setText(null);
                        chk_out_perc.setText(null);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(chk_out_perc, "Enter Check-in Payment Percentage..! ");
                }
            }
            else{
                JOptionPane.showMessageDialog(chk_out_perc, "Enter Advance Payment Percentage..! ");
            }
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        payment_frame.setVisible(false);
        main_panel.setVisible(true);
        full_radio_btn.setSelected(true);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        payment_frame.setVisible(true);
        main_panel.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tax2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tax2ItemStateChanged
      if(tax2.getSelectedIndex()!=-1){
          selectMoreTax1.setVisible(true);
          basic.setVisible(true);
          cascade.setVisible(true);
          
          serviceTax2();
      }
      else{
          selectMoreTax1.setVisible(false);
          basic.setVisible(false);
          cascade.setVisible(false);
          jLabel13.setVisible(false);
          tax3.setVisible(false);
          basic2.setVisible(false);
          cascade2.setVisible(false);
          tax3.setSelectedIndex(-1);
          selectMoreTax1.setSelected(false);
      }
      
    }//GEN-LAST:event_tax2ItemStateChanged

    private void luxuryTaxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_luxuryTaxItemStateChanged
       if(luxuryTax.getSelectedIndex()!=-1){
            selectMoreTax.setVisible(true);
            serviceTax();
        }
        else{
           
            selectMoreTax.setVisible(false);
            jLabel12.setVisible(false);
            tax2.setVisible(false);
            selectMoreTax1.setVisible(false);
            tax3.setVisible(false);
            jLabel13.setVisible(false);
            basic.setVisible(false);
            cascade.setVisible(false);
            selectMoreTax.setSelected(false);
            selectMoreTax1.setSelected(false);
        }
    }//GEN-LAST:event_luxuryTaxItemStateChanged

    private void tax3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tax3ItemStateChanged
        if(tax3.getSelectedIndex()!=-1){
           basic2.setVisible(true);
          cascade2.setVisible(true);
       }
       else{
           basic2.setVisible(false);
          cascade2.setVisible(false);
       }
    }//GEN-LAST:event_tax3ItemStateChanged

    private void available_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_available_textKeyPressed
       
    }//GEN-LAST:event_available_textKeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
     if(roomNo.getText()!=null && roomNo.getText().trim().length()>0){  
        Room_Nos_list = new ArrayList();
        RoomNoListModel = new Room_Nos_Model(null);
       if(All_RNo !=null){
           
             String []t1 = All_RNo.split("#");
             for(int i=0;i<t1.length;i++){
                 Room_Nos_list.add(t1[i]);
              }
             RoomNoListModel = new Room_Nos_Model(Room_Nos_list);
             jList1.setModel(RoomNoListModel);
             room_no_panel.setVisible(true);
             main_panel.setVisible(false);
             no_of_rooms.setText(roomNo.getText()); 
             
           }
         
         
        else{
          room_no_panel.setVisible(true);
          main_panel.setVisible(false);
          no_of_rooms.setText(roomNo.getText()); 
        }
        
     }
     else{
        JOptionPane.showMessageDialog(this, "Enter No Of Rooms First..!!", " n_tariff", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void Room_NoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Room_NoKeyPressed
        int count = 0;
        
     
        
        
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            if (Room_No.getText().trim().length() > 0) { 
                if(Room_Nos_list.size() < Integer.parseInt(no_of_rooms.getText())){
                   
                        List<Object> RoomNoList = new ArrayList<Object>();
                        try {
                            RoomNoList = GR_Linked_tableModel.getRoomNo_List(m_App);
                        } catch (BasicException ex) {
                            Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String roomNos[] ;
                        List<String> Temp_Nos = new ArrayList<String>();

                        for(int j=0;j<RoomNoList.size();j++){
                            String x = RoomNoList.get(j).toString();
                            String []temp_array = x.split("#");
                            for(int j1=0;j1<temp_array.length ; j1++){
                              Temp_Nos.add(temp_array[j1]);  
                            }


                        }
                    
                       
                      
                    
                    
                        if(Room_Nos_list.size()>-1){
                                for(int i=0;i<Room_Nos_list.size();i++){
                                  String x = Room_Nos_list.get(i).toString();
                                  if(x.equals(Room_No.getText().trim())){
                                     count++;
                                     break;
                                  }
                                }
                                
                                
                                if(Temp_Nos.size()>0){
                                    for(int j2=0;j2<Temp_Nos.size();j2++){
                                     String x1 = Temp_Nos.get(j2).toString();
                                     if(x1.equals(Room_No.getText().trim())){
                                        count++; 
                                        break;
                                     }

                                    } 
                                }
                                
                                
                                if(count==0){
                                    Room_Nos_list.add(Room_No.getText().trim());
                                    RoomNoListModel = new Room_Nos_Model(Room_Nos_list);
                                    jList1.setModel(RoomNoListModel);
                                    Room_No.setText(null);
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "Room No already In use..!!", " Error Message ", JOptionPane.ERROR_MESSAGE);
                                    Room_No.setText(null);
                                }
                                
                          }
                        
                          
                        
                        
                    
                        else{
                            Room_Nos_list.add(Room_No.getText().trim());
                            RoomNoListModel = new Room_Nos_Model(Room_Nos_list);
                            jList1.setModel(RoomNoListModel);
                            Room_No.setText(null);
                        }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Guest list exceeds the number of guest specified", "Exceeds the specified number", JOptionPane.OK_OPTION);
                    Room_No.setText(null);
                }
            }
        }
    }//GEN-LAST:event_Room_NoKeyPressed

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        int row = jList1.getSelectedIndex();
        if (row >= 0) {
          Room_Nos_list.remove(row);
          RoomNoListModel = new Room_Nos_Model(Room_Nos_list);
          jList1.setModel(RoomNoListModel);  
            
        }
    }//GEN-LAST:event_RemoveActionPerformed

    private void save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save3ActionPerformed
       main_panel.setVisible(true);
       room_no_panel.setVisible(false);
       All_RNo = null;
       String Temp;
       int s = RoomNoListModel.getSize();
       for(int i=0;i<s;i++){
           Temp = RoomNoListModel.getElementAt(i).toString();
           if(All_RNo==null){
               All_RNo = Temp;
           }else{
               All_RNo = All_RNo + "#" +Temp;
           }
           
       }
       
       
    }//GEN-LAST:event_save3ActionPerformed

    private void cancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel3ActionPerformed
        main_panel.setVisible(true);
       room_no_panel.setVisible(false);
       
    }//GEN-LAST:event_cancel3ActionPerformed

    private void set_acc_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_acc_btnActionPerformed
       main_panel.setVisible(false);
       account_panel.setVisible(true);
       
    }//GEN-LAST:event_set_acc_btnActionPerformed

    private void sace_accActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sace_accActionPerformed
       if(Adv_acount_combo.getSelectedIndex()!=-1){
           if(reve_accnt_combo.getSelectedIndex()!=-1){
               if(cancel_acct_combo.getSelectedIndex()!=-1){
           
                 String Adv_acc_name = Adv_acount_combo.getSelectedItem().toString();
                 String Rev_Acc_name = reve_accnt_combo.getSelectedItem().toString();
                 String Cancel_Acc_Name = cancel_acct_combo.getSelectedItem().toString();
                 
               try {
                   Advance_Acct_ID = dlfac.getaccountidByName(Adv_acc_name);
                   Revenue_Acct_ID = dlfac.getaccountidByName(Rev_Acc_name);
                   Cancel_Acct_ID =  dlfac.getaccountidByName(Cancel_Acc_Name);
               } catch (BasicException ex) {
                   Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
               }
                 
               
               
               main_panel.setVisible(true);
               account_panel.setVisible(false);
           
               
               
               }
               else{
                     JOptionPane.showMessageDialog(this, " Select Cancellation Account..!!", " Incomplete Form", JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
                 JOptionPane.showMessageDialog(this, " Select Revenue Account..!!", " Incomplete Form", JOptionPane.ERROR_MESSAGE);
           }
       }
       else{
               JOptionPane.showMessageDialog(this, " Select Advance Account ..!!", " Incomplete Form", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_sace_accActionPerformed

    private void cancel_accActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_accActionPerformed
        Advance_Acct_ID = null;
        Revenue_Acct_ID = null;
        Adv_acount_combo.setSelectedIndex(-1);
        reve_accnt_combo.setSelectedIndex(-1);
        cancel_acct_combo.setSelectedIndex(-1);
        main_panel.setVisible(true);
        account_panel.setVisible(false);
        
    }//GEN-LAST:event_cancel_accActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
       if(jTabbedPane1.getSelectedIndex()==0){
            reset();
       }
    }//GEN-LAST:event_jTabbedPane1StateChanged

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adv_Perc;
    private javax.swing.JComboBox Adv_acount_combo;
    private javax.swing.JTextField BLOCK_FROM_TEXT;
    private javax.swing.JTextField BLOCK_UPTO_TEXT;
    private javax.swing.JTextField Chk_in_perc;
    private javax.swing.JLabel ID_label;
    private javax.swing.JButton Remove;
    private javax.swing.JTextField Room_No;
    private javax.swing.JButton Submit;
    private javax.swing.JPanel account_panel;
    private javax.swing.JTextField advnceBooking_dura;
    private javax.swing.JTextField available_text;
    private javax.swing.JPanel availibility_panel;
    private javax.swing.JTextField availroomText;
    private javax.swing.JRadioButton basic;
    private javax.swing.JRadioButton basic2;
    private javax.swing.JButton block_button;
    private javax.swing.JPanel block_panel;
    private javax.swing.JTextField block_rooms_text;
    private javax.swing.JLabel blocked_rooms_label;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cancel3;
    private javax.swing.JButton cancel_acc;
    private javax.swing.JComboBox cancel_acct_combo;
    private javax.swing.JButton cancel_avail;
    private javax.swing.JLabel capacity_label;
    private javax.swing.JRadioButton cascade;
    private javax.swing.JRadioButton cascade2;
    private javax.swing.JTextField check_IN;
    private javax.swing.JTextField check_OUT;
    private javax.swing.JTextField chk_out_perc;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JTextArea facilities;
    private javax.swing.JLabel from_date_label;
    private javax.swing.JRadioButton full_radio_btn;
    private javax.swing.JLabel id;
    private javax.swing.JPanel image_panel;
    private javax.swing.JButton imgCancel;
    private javax.swing.JButton imgSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox luxuryTax;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private com.openbravo.data.gui.JImageEditor m_jImage1;
    private com.openbravo.data.gui.JImageEditor m_jImage2;
    private com.openbravo.data.gui.JImageEditor m_jimageIcon;
    private javax.swing.JTextField m_tariff;
    private javax.swing.JPanel main_panel;
    private javax.swing.JTextField max_Capacity;
    private javax.swing.JTextField max_days;
    private javax.swing.JCheckBox member;
    private javax.swing.JTextField n_tariff;
    private javax.swing.JLabel no_of_rooms;
    private javax.swing.JCheckBox non_member;
    private javax.swing.JRadioButton partial_radio_btn;
    private javax.swing.JPanel partial_variation_panel;
    private javax.swing.JTextField payment_Text;
    private javax.swing.JPanel payment_frame;
    private javax.swing.JComboBox reve_accnt_combo;
    private javax.swing.JTextField roomNo;
    private javax.swing.JLabel room_label;
    private javax.swing.JPanel room_no_panel;
    private javax.swing.JButton sace_acc;
    private javax.swing.JButton save;
    private javax.swing.JButton save3;
    private javax.swing.JButton save_avail;
    private javax.swing.JCheckBox selectMoreTax;
    private javax.swing.JCheckBox selectMoreTax1;
    private javax.swing.JButton set_acc_btn;
    private javax.swing.JCheckBox showAll;
    private javax.swing.JComboBox tax2;
    private javax.swing.JComboBox tax3;
    private javax.swing.JLabel to_date_label;
    private javax.swing.JTextField totalRoomsText;
    private javax.swing.JLabel total_room_label;
    private javax.swing.JTextField typeOfRoom;
    private javax.swing.JButton updateChanges;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "";
    }

    public void activate() throws BasicException {
        main_panel.setVisible(true);
        reset();
        
        jTabbedPane1.setSelectedIndex(0);
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
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        GR_Linked_tableModel = (GuestRoomLinkTableModel) m_App.getBean("com.openbravo.pos.Booking.GuestRoomLinkTableModel");
        dlfac=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate") ;
        
         buttonGoup();
        
    }

    public Object getBean() {
          return this;
    }
    
    
    public void loaddata() throws BasicException {
        
        
        try {
            
            GF = FloorEditor.loadInstanceGuest_FloorInfo(m_App);
            GuestRoomTable = GuestRoomTableModel.loadInstanceGuestInfo(m_App);
       
        } catch (BasicException ex) {
            Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         
       data1 = (List<GuestRoomTableModel.GuestRoomTableInfo>) GuestRoomTable.getGuestRoomPath();   
      
    
      
    
      
      
        
       staxlist = m_dlSales.getTaxCategoriesList().list();
       staxlist.add(0, null);
       staxmodel = new ComboBoxValModel(staxlist);
       luxuryTax.setModel(staxmodel);
            
            
            //load Guest room  table
            
            if(showAll.isSelected()==true){
            GuestRoomTable = GuestRoomTableModel.loadInstanceGuestInfo_showAll(m_App);
            showPanelInfo(GuestRoomTable);
            }
            else{
            GuestRoomTable = GuestRoomTableModel.loadInstanceGuestInfo(m_App);
            showPanelInfo(GuestRoomTable);
            }
            
         acclist=dlfac.getaccounts();
         sacclist=new ArrayList<AccountMasterExt>();
        sacclist.addAll(acclist);
        Adv_acount_Model=new ComboBoxValModel(acclist);
        Adv_acount_combo.setModel(Adv_acount_Model);
        reve_accnt_Model=new ComboBoxValModel(sacclist);
        reve_accnt_combo.setModel(reve_accnt_Model);
        Cancel_accnt_model = new ComboBoxValModel(sacclist);
        cancel_acct_combo.setModel(Cancel_accnt_model);
            
            
    }
    
    
    
    public void reset(){
        
        
        
     
          roomNo.setText(null);
          facilities.setText(null);
          selectMoreTax.setVisible(false);
          selectMoreTax1.setVisible(false);
          selectMoreTax.setSelected(false);
          selectMoreTax1.setSelected(false);
          luxuryTax.setSelectedIndex(-1);
          tax2.setSelectedIndex(-1);
          tax3.setSelectedIndex(-1);
          max_days.setText(null);
        
          m_tariff.setVisible(false);
          n_tariff.setVisible(false);
          jLabel32.setVisible(false);
          jLabel44.setVisible(false);
          m_tariff.setText(null);
          n_tariff.setText(null);
          updateChanges.setVisible(false);
          member.setSelected(false);
          non_member.setSelected(false);
          save.setVisible(true);
          count=0;
          floor_X=0;
           floor_Y = 0;
      
          m_jImage.setImage(null);
          m_jImage1.setImage(null);
          m_jImage2.setImage(null);
          typeOfRoom.setText(null);
          m_jimageIcon.setImage(null);
       
          available_text.setText(null);
          totalRoomsText.setText(null);
          availroomText.setText(null);
          jTextField1.setText(null);
        
          max_Capacity.setText(null);
 
          advnceBooking_dura.setText(null);
        
          availibility_panel.setVisible(false);
          jTabbedPane1.setSelectedIndex(0);
      
          typeOfRoom.setVisible(true);
          jLabel16.setVisible(true);
        
           check_IN.setText(null);
           check_OUT.setText(null);
       
           Availibility_perc = 100.00 ;
        
           basic2.setVisible(false);
           cascade2.setVisible(false);
        
           edit.setEnabled(false);
           delete.setEnabled(false);
           block_button.setEnabled(false);
           block_panel.setVisible(false);
           payment_frame.setVisible(false);
           Adv_Perc.setText(null);
           Chk_in_perc.setText(null);
           chk_out_perc.setText(null);
           full_radio_btn.setSelected(true);
           payment_Text.setText(null);
           All_RNo=null;
           Room_Nos_list= new ArrayList();
           RoomNoListModel = new Room_Nos_Model(Room_Nos_list);
           jList1.setModel(RoomNoListModel);
          roomNo_List_Cust = new ArrayList<Object>();
           RoomNo_link_Cust_listModel = new ComboBoxValModel();
           
           
           
        Advance_Acct_ID = null;
        Revenue_Acct_ID = null;
        Adv_acount_combo.setSelectedIndex(-1);
        reve_accnt_combo.setSelectedIndex(-1);
        cancel_acct_combo.setSelectedIndex(-1);
        room_no_panel.setVisible(false);
        
        account_panel.setVisible(false);
        payment_frame.setVisible(false);
        block_panel.setVisible(false);
        availibility_panel.setVisible(false);
        image_panel.setVisible(false);
        
        
        
    }
    
    private void buttonGoup() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(basic);
        bg1.add(cascade);
       ButtonGroup bg2 = new ButtonGroup();
        bg2.add(basic2);
        bg2.add(cascade2);
       ButtonGroup bg3 = new ButtonGroup();
        bg3.add(full_radio_btn);
        bg3.add(partial_radio_btn);
      
    }
    
    public void serviceTax()
    {
        Collection<TaxCategoryInfo> taxtwo = new ArrayList<TaxCategoryInfo>();
        taxtwo = staxlist;
        List taxListTwo = new ArrayList();
        taxListTwo.addAll(taxtwo);
        if(luxuryTax.getSelectedIndex()!=-1)
                {
                    TaxCategoryInfo tci = (TaxCategoryInfo) luxuryTax.getSelectedItem();
                    taxListTwo.remove(tci);
                    stax2 = new ComboBoxValModel(taxListTwo);
                    tax2.setModel(stax2);
                }
    }
    
    public void serviceTax2()
      {
         Collection<TaxCategoryInfo> taxthree = new ArrayList<TaxCategoryInfo>(); 
          taxthree = staxlist ; 
          List taxListThree = new ArrayList();
          taxListThree.addAll(taxthree);
          if(luxuryTax.getSelectedIndex()!=-1 && tax2.getSelectedIndex()!=-1){
              TaxCategoryInfo tci1 = (TaxCategoryInfo) luxuryTax.getSelectedItem();
              TaxCategoryInfo tci2 = (TaxCategoryInfo) tax2.getSelectedItem();
              taxListThree.remove(tci1);
              taxListThree.remove(tci2);
              stax3 = new ComboBoxValModel(taxListThree);
              tax3.setModel(stax3);
              
          }
          
      }  
    
    public void showPanelInfo(GuestRoomTableModel GuestRoomTable){
        jTable1.setModel(GuestRoomTable.getTableModel());
         
    }
    
    
    
    public void showAvailibilityIcons(int room_available , Icon ic , int totalRooms){
        
         jScrollPane3.getViewport().setView(null);
                   JFlowPanel jPeople = new JFlowPanel();
                   jPeople.applyComponentOrientation(getComponentOrientation());
                   
                   for(int i=0 ; i< room_available ; i++){
                    
                     
                    JButton btn = new JButton(ic);
                   
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
                   for(int y=0 ; y<(totalRooms-room_available); y++){
                       
                    JButton btn = new JButton(ic);
                   
                    btn.applyComponentOrientation(getComponentOrientation());
                    btn.setFocusPainted(false);
                    btn.setFocusable(false);
                    btn.setRequestFocusEnabled(false);
                    btn.setHorizontalAlignment(SwingConstants.LEADING);
                    btn.setMaximumSize(new Dimension(100, 100));
                    btn.setPreferredSize(new Dimension(100, 100));
                    btn.setMinimumSize(new Dimension(100, 100));
                    btn.setBackground(Color.red);
                    jPeople.add(btn);
                       
                   }
                    jScrollPane3.getViewport().setView(jPeople);
               
         }
    
    
    
    
       public List getBooked_Rooms_For_Blocking(Date FromDate , Date ToDate ){
           
           String Roomtype_Id = ID_label.getText();
           List<Object> BookedDates = new ArrayList<Object>();
            List<Object> BookedDates_Temp = new ArrayList<Object>();
           Object[] o = null;
            BookedDates_Temp = GuestRoomTable.getRoom_Booked_Date_List(Roomtype_Id, m_App);
            
            int No_of_days  = (int) (ToDate.getTime() - FromDate.getTime())/(1000 * 60 * 60 * 24);
            No_of_days++;
           
            for(int i=0;i<No_of_days;i++){
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(FromDate.getTime());
                c.add(Calendar.DATE, i);
                Date NextDay = c.getTime();
                
                for(int j=0;j<BookedDates_Temp.size();j++){
                    Date tmp = new Date();
                    Date Booked_date = new Date();
                    o =  (Object[]) BookedDates_Temp.get(j);          
                    tmp =  (Date)o[0];
                    Booked_date = new java.util.Date(tmp.getTime());
                    if(Booked_date.equals(NextDay)){
                        
                       BookedDates.add(Booked_date);
                        
                    }
                    
                  
                
                
                }
            }
            
            return BookedDates;
            
       }
       
       
       public void LoadBlockRooms_To_Availiblity(Date Block_from , Date Block_Upto ,int  Block_rooms , int total_rooms ,String  ID){
           
           int getBooked_rooms=0;
              
                   
                   
                   
         int No_of_days  = (int) (Block_Upto.getTime() - Block_from.getTime())/(1000 * 60 * 60 * 24);
               No_of_days++;  
                      
               for(int i=0;i<No_of_days;i++){
                   Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(Block_from.getTime());
                    c.add(Calendar.DATE, i);
                    Date NextDay = c.getTime();
                   
                     getBooked_rooms = GuestRoomTable.getRoom_Booked(m_App ,ID , NextDay);
                    
                     
                     if(getBooked_rooms!=-1){
                         
                       // update if booking date exist in availibility master...  
                       try {
                           int update_Availibilty =  new PreparedSentence(m_App.getSession(), "UPDATE guestroom_availibility  SET BOOKED_ROOMS=? WHERE ROOM_TYPE =? AND BOOKED_DATES=?"
                                                                 , new SerializerWriteBasic(new Datas[]{Datas.INT ,Datas.STRING , Datas.TIMESTAMP  })).exec
                                                                  (new Object[]{(Block_rooms+getBooked_rooms) ,ID , NextDay  });
                       } catch (BasicException ex) {
                           Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                       }

                       
                       
                       
                    }
                    else{
                         final Date Next_date = NextDay;
                         final int Total_rooms_temp = total_rooms;
                         final String ID_temp = ID;
                         final int Block_rooms_T = Block_rooms;
                         
                         // insert if booking date doesnt exits...
                         
                         Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                                        @Override      
                                        protected Object transact() throws BasicException {   



                                       int   insert_Availibility =  new PreparedSentence(m_App.getSession()  , "INSERT INTO guestroom_availibility (ID , ROOM_TYPE , TOTAL_ROOMS , BOOKED_ROOMS , BOOKED_DATES ) VALUES (?,?,?,?,?)"                           
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING  , Datas.INT ,Datas.INT ,Datas.TIMESTAMP })                         
                                        ).exec(new Object[]{UUID.randomUUID().toString() , ID_temp , Total_rooms_temp , Block_rooms_T ,  Next_date });                                                                                                


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
       
       
       
       public void update_Availiblility_unblock(Date From_Date , Date Upto_Date , String ID_T , int Blocked_Rooms){
           
           
               int No_of_days  = (int) (Upto_Date.getTime() - From_Date.getTime())/(1000 * 60 * 60 * 24);
                              No_of_days++;
           
                            for(int i=0;i<No_of_days;i++){
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(From_Date.getTime());
                                c.add(Calendar.DATE, i);
                                Date NextDay = c.getTime(); 
                            
                                
                                int Booked_Rooms = GuestRoomTable.getRooms_Available(NextDay, ID_T , m_App);
                                 try {
                                     int update_Availibility_Table = new PreparedSentence(m_App.getSession(), "UPDATE  guestroom_availibility set BOOKED_ROOMS=(BOOKED_ROOMS-?)  where ROOM_TYPE = ? AND BOOKED_DATES=? ", new SerializerWriteBasic(new Datas[]{Datas.INT , Datas.STRING , Datas.TIMESTAMP  })).exec(new Object[]{ Blocked_Rooms, ID_T ,NextDay });
                                
                                 } catch (BasicException ex) {
                                     Logger.getLogger(GuestRoomBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                  
                                
                         }
           
           
       }
        public class Room_Nos_Model extends AbstractListModel {

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
        
        
        public void Reset_Mem_Master(String RoomType) throws BasicException{
            
             int Delete_room_links =  new PreparedSentence(m_App.getSession(), "  DELETE FROM guestroom_link WHERE ROOMTYPE = ? "
                                                   , new SerializerWriteBasic(new Datas[]{ Datas.STRING })).exec(new Object[]{RoomType });
                                
            
            
        }
       
}
