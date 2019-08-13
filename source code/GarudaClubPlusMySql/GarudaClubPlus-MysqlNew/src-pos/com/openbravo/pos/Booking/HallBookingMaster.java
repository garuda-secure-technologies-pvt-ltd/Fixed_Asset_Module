/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HallBookingMaster.java
 *
 * Created on Apr 2, 2013, 10:25:55 AM
 */
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.beans.JTimePanel;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.Booking.FloorEditor.FloorTableInfo;
import com.openbravo.pos.catalog.WideComboBox;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import com.openbravo.pos.forms.DataLogicSales;

import com.openbravo.pos.Booking.hallTableModel.HallTableInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.inventory.TaxCategoryInfo;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class HallBookingMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private FloorEditor FE;
    private List<FloorEditor.FloorTableInfo> data ;
    private ComboBoxValModel FloorListModel ;
    private ComboBoxValModel  staxmodel;
    private ComboBoxValModel  stax2;
    private ComboBoxValModel  stax3;
    private BufferedImage Image1;
    private BufferedImage Image2;
    private BufferedImage Image3;
    private FloorTableInfo  fti;
    private BufferedImage bufferedImage = null;
    private BufferedImage m_Img = null;
    private DataLogicSales m_dlSales;
    
    private hallTableModel hallTable;
    private List staxlist;
    private List staxlist2;
    private List staxlist3;
    
    private List<String> M_hourly_Slots = new ArrayList<String>();
    private List<String> M_halfDay_Slots = new ArrayList<String>();
    private List<String> M_fullDay_Slots = new ArrayList<String>();
  
     private ComboBoxValModel  M_hourly_Slots_C;
     private ComboBoxValModel  M_halfDay_Slots_C;
     private ComboBoxValModel  M_fullDay_Slots_C;
     private ComboBoxValModel  Adv_acount_Model;
     private ComboBoxValModel  reve_accnt_Model;
     private ComboBoxValModel  Cancel_accnt_model;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     
    List<AccountMasterExt> acclist= new ArrayList<AccountMasterExt>();
    List<AccountMasterExt> sacclist=new ArrayList<AccountMasterExt>(); 
     private DataLogicFacilities dlfac;
     
    /** Creates new form HallBookingMaster */
    public HallBookingMaster() {
        initComponents();
      
       CreateHall_panel.setVisible(true);
        
       // for members tariff
       
       timing_label.setVisible(true);
       forNonMembers.setVisible(true);
    
     charges_panel.setVisible(false);
       timing_panel.setVisible(false);
       From.setVisible(true);
       rate1.setVisible(true);
        jTextField3.setVisible(true);     
        jButton3.setVisible(true);      
        rate2.setVisible(true);      
         jTextField4.setVisible(true);     
        jButton5.setVisible(true);      
        rate3.setVisible(true); 
        jTextField5.setVisible(true);       
         jPanel1.setVisible(true);        
         jPanel4.setVisible(true); 
         jPanel7.setVisible(true);         
          timing_panel.setVisible(false);
          charges_panel.setVisible(false);
        basic2.setVisible(false);
        cascade2.setVisible(false);    
       // for non Members Tariff        
 
       forNonMembers.setVisible(true);
     
       rate4.setVisible(true);        
       jTextField11.setVisible(true);       
            
         rate5.setVisible(true);      
        jTextField9.setVisible(true);
             
         rate6.setVisible(true);        
        jTextField10.setVisible(true);        
        
                 
                 
        ImagePanel.setVisible(false);
        
        edit.setEnabled(false);
        delete.setEnabled(false);
        saveChanges.setVisible(false);
        jLabel16.setVisible(false);
        Tax2.setVisible(false);
        jLabel19.setVisible(false);
        Tax3.setVisible(false);
        select_more_tax.setVisible(false);
        select_more_tax1.setVisible(false);
        jTabbedPane1.setSelectedIndex(0);
        location_panel.setVisible(false);
        basic2.setSelected(true);
        basic1.setVisible(false);
        cascade1.setVisible(false);
        basic1.setSelected(true);
        block_panel.setVisible(false);
        jButton6.setEnabled(false);
        payment_frame.setVisible(false);
        full_radio_btn.setSelected(true);
        
        
    }
     


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jFrame5 = new javax.swing.JFrame();
        jFrame6 = new javax.swing.JFrame();
        jFrame7 = new javax.swing.JFrame();
        jFrame8 = new javax.swing.JFrame();
        CreateHall_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        table_scrolPnne = new javax.swing.JScrollPane();
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
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        showAll = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        max_Capacity = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        FloorCombo = new javax.swing.JComboBox();
        jButton11 = new javax.swing.JButton();
        luxuryTax = new WideComboBox();
        select_more_tax = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        Facility = new javax.swing.JTextArea();
        id = new javax.swing.JLabel();
        select_more_tax1 = new javax.swing.JCheckBox();
        Tax2 = new WideComboBox();
        jLabel16 = new javax.swing.JLabel();
        Tax3 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        cascade1 = new javax.swing.JRadioButton();
        basic1 = new javax.swing.JRadioButton();
        save = new javax.swing.JButton();
        saveChanges = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        advnceBooking_dura = new javax.swing.JTextField();
        basic2 = new javax.swing.JRadioButton();
        cascade2 = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        payment_text = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        Adv_acount_combo = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        reve_accnt_combo = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        cancellation_accnt_combo = new javax.swing.JComboBox();
        ImagePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        m_jImage1 = new com.openbravo.data.gui.JImageEditor();
        m_jImage2 = new com.openbravo.data.gui.JImageEditor();
        charges_panel = new javax.swing.JPanel();
        forNonMembers = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        rate3 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rate2 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rate1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        rate6 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        rate4 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        rate5 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        forNonMembers1 = new javax.swing.JLabel();
        timing_panel = new javax.swing.JPanel();
        timing_label = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        From = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        M_hourl_TimeSlot_Combo = new javax.swing.JComboBox();
        delete_M_hourly = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        M_halfDay_TimeSlot_Combo = new javax.swing.JComboBox();
        delete_M_half = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        M_fullDay_TimeSlot_Combo = new javax.swing.JComboBox();
        delete_M_full = new javax.swing.JButton();
        location_panel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BackImageLabel = new javax.swing.JLabel();
        hall_ic1 = new javax.swing.JButton();
        hall_ic2 = new javax.swing.JButton();
        hall_ic3 = new javax.swing.JButton();
        hall_ic4 = new javax.swing.JButton();
        temp_icon = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        X1 = new javax.swing.JTextField();
        Y1 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        block_panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        hall_label = new javax.swing.JLabel();
        capacity_label = new javax.swing.JLabel();
        floor_label = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        message = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        hall_id = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        block_from_label = new javax.swing.JLabel();
        block_upto_label = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        payment_frame = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        full_radio_btn = new javax.swing.JRadioButton();
        partial_radio_btn = new javax.swing.JRadioButton();
        partial_variation_panel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        Adv_Perc = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        Chk_in_perc = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        chk_out_perc = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();

        jFrame1.setName("jFrame1"); // NOI18N

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame2.setName("jFrame2"); // NOI18N

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame3.setName("jFrame3"); // NOI18N

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame4.setName("jFrame4"); // NOI18N

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame5.setName("jFrame5"); // NOI18N

        javax.swing.GroupLayout jFrame5Layout = new javax.swing.GroupLayout(jFrame5.getContentPane());
        jFrame5.getContentPane().setLayout(jFrame5Layout);
        jFrame5Layout.setHorizontalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame5Layout.setVerticalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame6.setName("jFrame6"); // NOI18N

        javax.swing.GroupLayout jFrame6Layout = new javax.swing.GroupLayout(jFrame6.getContentPane());
        jFrame6.getContentPane().setLayout(jFrame6Layout);
        jFrame6Layout.setHorizontalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame6Layout.setVerticalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame7.setName("jFrame7"); // NOI18N

        javax.swing.GroupLayout jFrame7Layout = new javax.swing.GroupLayout(jFrame7.getContentPane());
        jFrame7.getContentPane().setLayout(jFrame7Layout);
        jFrame7Layout.setHorizontalGroup(
            jFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame7Layout.setVerticalGroup(
            jFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jFrame8.setName("jFrame8"); // NOI18N

        javax.swing.GroupLayout jFrame8Layout = new javax.swing.GroupLayout(jFrame8.getContentPane());
        jFrame8.getContentPane().setLayout(jFrame8Layout);
        jFrame8Layout.setHorizontalGroup(
            jFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame8Layout.setVerticalGroup(
            jFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        CreateHall_panel.setName("CreateHall_panel"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel5.setName("jPanel5"); // NOI18N

        table_scrolPnne.setAutoscrolls(true);
        table_scrolPnne.setName("table_scrolPnne"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Max. Capacity", "Mem Rs./Hour", "Mem Rs./Half Slot", "Mem Rs./Full Slot", "LuxuryTax", "Tax 2", "Tax 3", "Basic/Cascade", "Facility"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        table_scrolPnne.setViewportView(jTable1);

        edit.setText("Edit");
        edit.setName("edit"); // NOI18N
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
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

        jButton6.setText("Block");
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showAll)
                .addGap(57, 57, 57))
            .addComponent(table_scrolPnne, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showAll)
                .addGap(11, 11, 11)
                .addComponent(table_scrolPnne, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(197, 197, 197))
        );

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/deactivate.png"))); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/block.png"))); // NOI18N

        jTabbedPane1.addTab("Hall Details", jPanel5);

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText("Name");
        jLabel1.setName("jLabel1"); // NOI18N

        name.setName("name"); // NOI18N

        jLabel2.setText("Max. Capacity");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Facilities");
        jLabel3.setName("jLabel3"); // NOI18N

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(153, 0, 0));
        jButton4.setText("Cancel");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        max_Capacity.setName("max_Capacity"); // NOI18N
        max_Capacity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                max_CapacityKeyReleased(evt);
            }
        });

        jButton8.setText("Upload Images");
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/upload.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel5.setText("Images");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel15.setText("Tax 1");
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel17.setText("Set location In LayOut");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText("Floor");
        jLabel18.setName("jLabel18"); // NOI18N

        FloorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Item 2", "Item 3", "Item 4" }));
        FloorCombo.setName("FloorCombo"); // NOI18N
        FloorCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FloorComboItemStateChanged(evt);
            }
        });

        jButton11.setText("Set Location");
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

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

        select_more_tax.setText("Select more Tax ");
        select_more_tax.setName("select_more_tax"); // NOI18N
        select_more_tax.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_more_taxItemStateChanged(evt);
            }
        });
        select_more_tax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_more_taxActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        Facility.setColumns(20);
        Facility.setRows(5);
        Facility.setName("Facility"); // NOI18N
        jScrollPane2.setViewportView(Facility);

        id.setText("id");
        id.setName("id"); // NOI18N

        select_more_tax1.setText("Select more tax");
        select_more_tax1.setName("select_more_tax1"); // NOI18N
        select_more_tax1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_more_tax1ItemStateChanged(evt);
            }
        });
        select_more_tax1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_more_tax1ActionPerformed(evt);
            }
        });

        Tax2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        Tax2.setName("Tax2"); // NOI18N
        Tax2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tax2MouseClicked(evt);
            }
        });
        Tax2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Tax2ItemStateChanged(evt);
            }
        });
        Tax2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tax2ActionPerformed(evt);
            }
        });

        jLabel16.setText("Tax 2");
        jLabel16.setName("jLabel16"); // NOI18N

        Tax3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        Tax3.setName("Tax3"); // NOI18N
        Tax3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Tax3ItemStateChanged(evt);
            }
        });
        Tax3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tax3ActionPerformed(evt);
            }
        });

        jLabel19.setText("Tax 3");
        jLabel19.setName("jLabel19"); // NOI18N

        cascade1.setText("Cascade");
        cascade1.setName("cascade1"); // NOI18N
        cascade1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cascade1MouseClicked(evt);
            }
        });
        cascade1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cascade1ItemStateChanged(evt);
            }
        });
        cascade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cascade1ActionPerformed(evt);
            }
        });

        basic1.setText("Basic");
        basic1.setName("basic1"); // NOI18N
        basic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                basic1MouseClicked(evt);
            }
        });
        basic1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                basic1ItemStateChanged(evt);
            }
        });
        basic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basic1ActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save.setForeground(new java.awt.Color(0, 102, 51));
        save.setText("Save");
        save.setName("save"); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        saveChanges.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveChanges.setForeground(new java.awt.Color(0, 102, 51));
        saveChanges.setText("Save changes");
        saveChanges.setName("saveChanges"); // NOI18N
        saveChanges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        saveChanges.setPreferredSize(new java.awt.Dimension(120, 23));
        saveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChangesActionPerformed(evt);
            }
        });

        jButton13.setText("Hall Tariff ");
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/rupee.png"))); // NOI18N
        jButton13.setName("jButton13"); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Timing Slots");
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/time.png"))); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel29.setText("Advance Booking Duration ");
        jLabel29.setName("jLabel29"); // NOI18N

        advnceBooking_dura.setName("advnceBooking_dura"); // NOI18N
        advnceBooking_dura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                advnceBooking_duraKeyReleased(evt);
            }
        });

        basic2.setText("Basic");
        basic2.setName("basic2"); // NOI18N

        cascade2.setText("Cascade");
        cascade2.setName("cascade2"); // NOI18N

        jLabel30.setText("Months");
        jLabel30.setName("jLabel30"); // NOI18N

        jLabel32.setText("Advance Payment Duration");
        jLabel32.setName("jLabel32"); // NOI18N

        payment_text.setName("payment_text"); // NOI18N
        payment_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                payment_textKeyReleased(evt);
            }
        });

        jLabel33.setText("(* Days within Booking)");
        jLabel33.setName("jLabel33"); // NOI18N

        jButton30.setText("Advance Payment Option");
        jButton30.setName("jButton30"); // NOI18N
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jLabel42.setText("Advance Account");
        jLabel42.setName("jLabel42"); // NOI18N

        Adv_acount_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        Adv_acount_combo.setName("Adv_acount_combo"); // NOI18N

        jLabel43.setText("Revenue Account ");
        jLabel43.setName("jLabel43"); // NOI18N

        reve_accnt_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        reve_accnt_combo.setName("reve_accnt_combo"); // NOI18N

        jLabel44.setText("Cancellation Account ");
        jLabel44.setName("jLabel44"); // NOI18N

        cancellation_accnt_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        cancellation_accnt_combo.setName("cancellation_accnt_combo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(Tax2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(basic1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(cascade1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(select_more_tax1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(Tax3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(basic2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(cascade2)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 4, Short.MAX_VALUE)))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(select_more_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(96, 96, 96)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel44)
                                            .addComponent(jLabel43)
                                            .addComponent(jLabel42))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(Adv_acount_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel17))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(reve_accnt_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cancellation_accnt_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FloorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(payment_text, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(advnceBooking_dura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(jLabel33)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(max_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(FloorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(max_Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(advnceBooking_dura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(payment_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33))
                    .addComponent(jButton30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(luxuryTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_more_tax)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_more_tax1)
                            .addComponent(jLabel16)
                            .addComponent(basic1)
                            .addComponent(cascade1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(basic2)
                            .addComponent(cascade2)
                            .addComponent(id))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(Adv_acount_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jButton11)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel43)
                    .addComponent(reve_accnt_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(cancellation_accnt_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/location.png"))); // NOI18N
        id.setVisible(false);
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash2.png"))); // NOI18N

        jTabbedPane1.addTab("Create New ", jPanel2);

        javax.swing.GroupLayout CreateHall_panelLayout = new javax.swing.GroupLayout(CreateHall_panel);
        CreateHall_panel.setLayout(CreateHall_panelLayout);
        CreateHall_panelLayout.setHorizontalGroup(
            CreateHall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateHall_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CreateHall_panelLayout.setVerticalGroup(
            CreateHall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateHall_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        ImagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ImagePanel.setName("ImagePanel"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Image 1");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Image 2");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Image 3");
        jLabel14.setName("jLabel14"); // NOI18N

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 102, 51));
        jButton9.setText("Save");
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(204, 0, 0));
        jButton10.setText("Cancel");
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("Select Image less than 64 KB");
        jLabel20.setName("jLabel20"); // NOI18N

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

        add(m_jImage);
        m_jImage.setBounds(130, 300, 240, 180);

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ImagePanelLayout.createSequentialGroup()
                .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ImagePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addGroup(ImagePanelLayout.createSequentialGroup()
                                .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ImagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(m_jImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(ImagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(m_jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ImagePanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(m_jImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(m_jImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(m_jImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N

        charges_panel.setName("charges_panel"); // NOI18N

        forNonMembers.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        forNonMembers.setForeground(new java.awt.Color(102, 0, 0));
        forNonMembers.setText("For Non Members");
        forNonMembers.setName("forNonMembers"); // NOI18N

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(204, 0, 51));
        jButton18.setText("Cancel");
        jButton18.setName("jButton18"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 102, 0));
        jButton19.setText("Save");
        jButton19.setName("jButton19"); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setName("jPanel9"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Full Day");
        jLabel12.setName("jLabel12"); // NOI18N

        rate3.setText("Rate (Rs.)");
        rate3.setName("rate3"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Half Day");
        jLabel9.setName("jLabel9"); // NOI18N

        rate2.setText("Rate (Rs.)");
        rate2.setName("rate2"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Hourly");
        jLabel8.setName("jLabel8"); // NOI18N

        rate1.setText("Rate (Rs.)");
        rate1.setName("rate1"); // NOI18N

        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rate1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(rate3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField5))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(rate2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(rate1))
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rate2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rate3)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jLabel12.setForeground(Color.blue);
        jLabel9.setForeground(Color.blue);
        jLabel8.setForeground(Color.blue);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setToolTipText("null\n");
        jPanel11.setName("jPanel11"); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Full Day");
        jLabel28.setName("jLabel28"); // NOI18N

        rate6.setText("Rate (Rs.)");
        rate6.setName("rate6"); // NOI18N

        jTextField11.setName("jTextField11"); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Half Day");
        jLabel26.setName("jLabel26"); // NOI18N

        rate4.setText("Rate (Rs.)");
        rate4.setName("rate4"); // NOI18N

        jTextField10.setName("jTextField10"); // NOI18N
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Hourly ");
        jLabel23.setName("jLabel23"); // NOI18N

        rate5.setText("Rate (Rs.)");
        rate5.setName("rate5"); // NOI18N

        jTextField9.setName("jTextField9"); // NOI18N
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel26)
                    .addComponent(jLabel23))
                .addGap(49, 49, 49)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rate5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rate6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rate4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rate5)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(rate4)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rate6))
                .addGap(38, 38, 38))
        );

        jLabel28.setForeground(Color.blue);
        jLabel26.setForeground(Color.blue);
        jLabel23.setForeground(Color.blue);

        forNonMembers1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        forNonMembers1.setForeground(new java.awt.Color(102, 0, 0));
        forNonMembers1.setText("For Members");
        forNonMembers1.setName("forNonMembers1"); // NOI18N

        javax.swing.GroupLayout charges_panelLayout = new javax.swing.GroupLayout(charges_panel);
        charges_panel.setLayout(charges_panelLayout);
        charges_panelLayout.setHorizontalGroup(
            charges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(charges_panelLayout.createSequentialGroup()
                .addGroup(charges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(charges_panelLayout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addGroup(charges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(charges_panelLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(charges_panelLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(forNonMembers))
                    .addGroup(charges_panelLayout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(forNonMembers1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        charges_panelLayout.setVerticalGroup(
            charges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(charges_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(forNonMembers1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(forNonMembers)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(charges_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        forNonMembers.setForeground(Color.RED);
        forNonMembers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig32.png"))); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        forNonMembers1.setForeground(Color.RED);
        forNonMembers1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kdmconfig32.png"))); // NOI18N

        timing_panel.setName("timing_panel"); // NOI18N

        timing_label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        timing_label.setForeground(new java.awt.Color(0, 0, 204));
        timing_label.setText("Timing Slots");
        timing_label.setName("timing_label"); // NOI18N

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(153, 0, 0));
        jButton16.setText("Cancel");
        jButton16.setName("jButton16"); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 102, 51));
        jButton17.setText("Save");
        jButton17.setName("jButton17"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setName("jPanel1"); // NOI18N

        From.setText("From");
        From.setName("From"); // NOI18N
        From.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Timing Slots");
        jLabel7.setName("jLabel7"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jButton2.setText("To");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.setName("jTextField2"); // NOI18N

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton25.setName("jButton25"); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        M_hourl_TimeSlot_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        M_hourl_TimeSlot_Combo.setName("M_hourl_TimeSlot_Combo"); // NOI18N

        delete_M_hourly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editdelete.png"))); // NOI18N
        delete_M_hourly.setFocusPainted(false);
        delete_M_hourly.setFocusable(false);
        delete_M_hourly.setMargin(new java.awt.Insets(2, 8, 2, 8));
        delete_M_hourly.setName("delete_M_hourly"); // NOI18N
        delete_M_hourly.setRequestFocusEnabled(false);
        delete_M_hourly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_M_hourlyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel7)
                .addGap(139, 139, 139)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(From, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(M_hourl_TimeSlot_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(delete_M_hourly)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete_M_hourly)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(M_hourl_TimeSlot_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(From, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel7.setForeground(Color.BLUE);
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setName("jPanel4"); // NOI18N

        jButton3.setText("From");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Half Day Timing Slots");
        jLabel10.setName("jLabel10"); // NOI18N

        jButton20.setText("To");
        jButton20.setName("jButton20"); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jTextField6.setName("jTextField6"); // NOI18N

        jTextField7.setName("jTextField7"); // NOI18N

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton26.setName("jButton26"); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        M_halfDay_TimeSlot_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        M_halfDay_TimeSlot_Combo.setName("M_halfDay_TimeSlot_Combo"); // NOI18N
        M_halfDay_TimeSlot_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_halfDay_TimeSlot_ComboActionPerformed(evt);
            }
        });

        delete_M_half.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editdelete.png"))); // NOI18N
        delete_M_half.setFocusPainted(false);
        delete_M_half.setFocusable(false);
        delete_M_half.setMargin(new java.awt.Insets(2, 8, 2, 8));
        delete_M_half.setName("delete_M_half"); // NOI18N
        delete_M_half.setRequestFocusEnabled(false);
        delete_M_half.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_M_halfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel10)
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(M_halfDay_TimeSlot_Combo, 0, 197, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(delete_M_half)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(M_halfDay_TimeSlot_Combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete_M_half, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jLabel10.setForeground(Color.BLUE);
        jTextField6.setEditable(false);
        jTextField7.setEditable(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setName("jPanel7"); // NOI18N

        jButton5.setText("From");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton21.setText("To");
        jButton21.setName("jButton21"); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jTextField8.setName("jTextField8"); // NOI18N

        jTextField12.setName("jTextField12"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Full Day Timing Slots");
        jLabel13.setName("jLabel13"); // NOI18N

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        jButton27.setName("jButton27"); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        M_fullDay_TimeSlot_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        M_fullDay_TimeSlot_Combo.setName("M_fullDay_TimeSlot_Combo"); // NOI18N

        delete_M_full.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editdelete.png"))); // NOI18N
        delete_M_full.setFocusPainted(false);
        delete_M_full.setFocusable(false);
        delete_M_full.setMargin(new java.awt.Insets(2, 8, 2, 8));
        delete_M_full.setName("delete_M_full"); // NOI18N
        delete_M_full.setRequestFocusEnabled(false);
        delete_M_full.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_M_fullActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel13)
                .addGap(77, 77, 77)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(jTextField8))
                .addGap(42, 42, 42)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(M_fullDay_TimeSlot_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(delete_M_full)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(delete_M_full)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(M_fullDay_TimeSlot_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTextField8.setEditable(false);
        jTextField12.setEditable(false);
        jLabel13.setForeground(Color.BLUE);

        javax.swing.GroupLayout timing_panelLayout = new javax.swing.GroupLayout(timing_panel);
        timing_panel.setLayout(timing_panelLayout);
        timing_panelLayout.setHorizontalGroup(
            timing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timing_panelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(timing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(timing_panelLayout.createSequentialGroup()
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 49, Short.MAX_VALUE))
            .addGroup(timing_panelLayout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(timing_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        timing_panelLayout.setVerticalGroup(
            timing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timing_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(timing_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(timing_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timing_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        timing_label.setForeground(Color.red);
        timing_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/time.png"))); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N

        location_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        location_panel.setName("location_panel"); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 153));
        jLabel31.setText("Location ");
        jLabel31.setName("jLabel31"); // NOI18N

        jLabel21.setText("Select Icon");
        jLabel21.setName("jLabel21"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        BackImageLabel.setName("BackImageLabel"); // NOI18N
        BackImageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackImageLabelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(BackImageLabel);

        hall_ic1.setName("hall_ic1"); // NOI18N
        hall_ic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hall_ic1MouseClicked(evt);
            }
        });
        hall_ic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic1ActionPerformed(evt);
            }
        });

        hall_ic2.setName("hall_ic2"); // NOI18N
        hall_ic2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hall_ic2MouseClicked(evt);
            }
        });
        hall_ic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic2ActionPerformed(evt);
            }
        });

        hall_ic3.setName("hall_ic3"); // NOI18N
        hall_ic3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hall_ic3MouseClicked(evt);
            }
        });
        hall_ic3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic3ActionPerformed(evt);
            }
        });

        hall_ic4.setName("hall_ic4"); // NOI18N
        hall_ic4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hall_ic4MouseClicked(evt);
            }
        });
        hall_ic4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic4ActionPerformed(evt);
            }
        });

        temp_icon.setName("temp_icon"); // NOI18N
        temp_icon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temp_iconActionPerformed(evt);
            }
        });

        jPanel6.setName("jPanel6"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("Cancel");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        X1.setName("X1"); // NOI18N

        Y1.setName("Y1"); // NOI18N

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 102, 0));
        jButton15.setText("Save");
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout location_panelLayout = new javax.swing.GroupLayout(location_panel);
        location_panel.setLayout(location_panelLayout);
        location_panelLayout.setHorizontalGroup(
            location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, location_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, location_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(386, 386, 386))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, location_panelLayout.createSequentialGroup()
                        .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(location_panelLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(X1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(location_panelLayout.createSequentialGroup()
                                .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(location_panelLayout.createSequentialGroup()
                                        .addComponent(hall_ic1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(hall_ic2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(location_panelLayout.createSequentialGroup()
                                        .addComponent(hall_ic3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(hall_ic4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(location_panelLayout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(temp_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(171, 171, 171))))
            .addGroup(location_panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        location_panelLayout.setVerticalGroup(
            location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(location_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(location_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(location_panelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hall_ic2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hall_ic1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hall_ic3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hall_ic4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(temp_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(location_panelLayout.createSequentialGroup()
                        .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(location_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(X1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Y1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/location.png"))); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        X1.setVisible(false);
        Y1.setVisible(false);
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N

        block_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        block_panel.setName("block_panel"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Block Hall");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Hall Name :");
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Capacity : ");
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Floor : ");
        jLabel25.setName("jLabel25"); // NOI18N

        hall_label.setText("HallName");
        hall_label.setName("hall_label"); // NOI18N

        capacity_label.setText("jLabel27");
        capacity_label.setName("capacity_label"); // NOI18N

        floor_label.setText("jLabel27");
        floor_label.setName("floor_label"); // NOI18N

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

        jTextField13.setName("jTextField13"); // NOI18N

        jTextField14.setName("jTextField14"); // NOI18N

        message.setText("message");
        message.setName("message"); // NOI18N

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton22.setForeground(new java.awt.Color(204, 0, 0));
        jButton22.setText("Cancel");
        jButton22.setName("jButton22"); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(0, 153, 51));
        jButton23.setText("Submit");
        jButton23.setName("jButton23"); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        hall_id.setText("ID");
        hall_id.setName("hall_id"); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Hall is blocked   from  : ");
        jLabel27.setName("jLabel27"); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("to : ");
        jLabel34.setName("jLabel34"); // NOI18N

        block_from_label.setText("Date1");
        block_from_label.setName("block_from_label"); // NOI18N

        block_upto_label.setText("Date2 ");
        block_upto_label.setName("block_upto_label"); // NOI18N

        jButton24.setText("Un Block");
        jButton24.setName("jButton24"); // NOI18N
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout block_panelLayout = new javax.swing.GroupLayout(block_panel);
        block_panel.setLayout(block_panelLayout);
        block_panelLayout.setHorizontalGroup(
            block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(block_panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hall_id)
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(floor_label))
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hall_label))
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capacity_label)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block_panelLayout.createSequentialGroup()
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block_panelLayout.createSequentialGroup()
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, block_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
            .addGroup(block_panelLayout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(message)
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jTextField14)))
                    .addGroup(block_panelLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(block_from_label)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(block_upto_label)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        block_panelLayout.setVerticalGroup(
            block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(block_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(hall_label)
                    .addComponent(jButton24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(capacity_label))
                .addGap(18, 18, 18)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(floor_label))
                .addGap(14, 14, 14)
                .addComponent(hall_id)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(block_from_label)
                    .addComponent(jLabel34)
                    .addComponent(block_upto_label))
                .addGap(28, 28, 28)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(block_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22)
                    .addComponent(jButton23))
                .addGap(29, 29, 29))
        );

        jLabel4.setForeground(Color.RED);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/block.png"))); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jTextField13.setEditable(false);
        jTextField14.setEditable(false);
        message.setVisible(false);
        message.setForeground(Color.RED);
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel2.png"))); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        hall_id.setVisible(false);
        block_from_label.setForeground(Color.RED);
        block_upto_label.setForeground(Color.RED);
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/unblock.png"))); // NOI18N

        payment_frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        payment_frame.setName("payment_frame"); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 255));
        jLabel35.setText("Advance Payment Options");
        jLabel35.setName("jLabel35"); // NOI18N

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

        jLabel36.setText("Initial Payment during booking (%) ");
        jLabel36.setName("jLabel36"); // NOI18N

        Adv_Perc.setName("Adv_Perc"); // NOI18N
        Adv_Perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Adv_PercKeyReleased(evt);
            }
        });

        jLabel37.setText(" Payment during Check-In (%) ");
        jLabel37.setName("jLabel37"); // NOI18N

        Chk_in_perc.setName("Chk_in_perc"); // NOI18N
        Chk_in_perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Chk_in_percKeyReleased(evt);
            }
        });

        jLabel38.setText("Payment during Check-Out (%)");
        jLabel38.setName("jLabel38"); // NOI18N

        chk_out_perc.setName("chk_out_perc"); // NOI18N
        chk_out_perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chk_out_percKeyReleased(evt);
            }
        });

        jLabel39.setText("(0-100%)");
        jLabel39.setName("jLabel39"); // NOI18N

        jLabel40.setText("(0-100%)");
        jLabel40.setName("jLabel40"); // NOI18N

        jLabel41.setText("(0-100%)");
        jLabel41.setName("jLabel41"); // NOI18N

        javax.swing.GroupLayout partial_variation_panelLayout = new javax.swing.GroupLayout(partial_variation_panel);
        partial_variation_panel.setLayout(partial_variation_panelLayout);
        partial_variation_panelLayout.setHorizontalGroup(
            partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partial_variation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(partial_variation_panelLayout.createSequentialGroup()
                        .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Adv_Perc, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(Chk_in_perc)))
                    .addGroup(partial_variation_panelLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(chk_out_perc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        partial_variation_panelLayout.setVerticalGroup(
            partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(partial_variation_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(Adv_Perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(Chk_in_perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(18, 18, 18)
                .addGroup(partial_variation_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(chk_out_perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton29.setForeground(new java.awt.Color(0, 102, 51));
        jButton29.setText("Save");
        jButton29.setName("jButton29"); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 0, 0));
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
                        .addGroup(payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partial_variation_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)))
                    .addGroup(payment_frameLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partial_radio_btn)
                            .addComponent(full_radio_btn))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        payment_frameLayout.setVerticalGroup(
            payment_frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payment_frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateHall_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(charges_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timing_panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(block_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payment_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(location_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CreateHall_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timing_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(charges_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(location_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(block_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payment_frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        m_jImage.setImage(null);
        m_jImage1.setImage(null);
        m_jImage2.setImage(null);
         CreateHall_panel.setVisible(true);
        ImagePanel.setVisible(false);
       
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       count++;
        Dimension image1Size = m_jImage.getSize();
        Dimension image2Size = m_jImage1.getSize();
        Dimension image3Size = m_jImage2.getSize();
       
       if(m_jImage.getImage()!=null || m_jImage1.getImage()!=null || m_jImage2.getImage()!=null){
       
        
        Image1 = m_jImage.getImage();
        Image2 = m_jImage1.getImage();
        Image3 = m_jImage2.getImage();
        
        
                
    }
        
        
        CreateHall_panel.setVisible(true);
        ImagePanel.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    
    String Hall_Name = null;
    Double Max_Capacity = 0.0;
    Double M_hourly = 0.0;
    Double M_half = 0.0;
    Double M_Full = 0.0;
    Double N_hourly = 0.0;
    Double N_half = 0.0;
    Double N_full = 0.0;
    
    String Facilty = null;
    int basic = 0;
    int cascade = 0;
    int Basic2 = 0;
    int Cascade2 = 0;
    
    String Floor= null;
    String ID =  null;  
    String Luxury_tax_Id = null;
    String Tax2_id = null;
    String Tax3_id = null;
    int insert_data = 0;
    int insert_image = 0;
    int count = 0;
    int floor_X = 0;
    int floor_Y = 0;
   int active = 1;
    String hourly_slot1;
    String halfDay_slot1;
    String fullDay_slot1;
    String hourly_slot2;
    String halfDay_slot2;
    String fullDay_slot2;
    int advance_booking_duration = 0;
    int payment;
    int block_flag=0;
    
    Date Block_From; 
    Date Block_upto;
    String Advance_perc;
    
    String Advance_Acct_ID;
    String Revenue_Acct_ID;
    String Cancel_Acct_ID;
    
private void basic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basic1ActionPerformed

}//GEN-LAST:event_basic1ActionPerformed

private void cascade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cascade1ActionPerformed

}//GEN-LAST:event_cascade1ActionPerformed

private void Tax2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Tax2ItemStateChanged
   if(Tax2.getSelectedIndex()!=-1){
           select_more_tax1.setVisible(true);
           
           Tax3.setSelectedIndex(-1);
           serviceTax2();
           basic1.setVisible(true);
           cascade1.setVisible(true);
           
       }
       else{
           select_more_tax1.setVisible(false);
           jLabel19.setVisible(false);
           Tax3.setVisible(false);
           basic1.setVisible(false);
           cascade1.setVisible(false);
           cascade2.setVisible(false);
           basic2.setVisible(false);
       }
        }//GEN-LAST:event_Tax2ItemStateChanged

private void Tax2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tax2MouseClicked

      }//GEN-LAST:event_Tax2MouseClicked

ImageIcon hall_icon1;
ImageIcon hall_icon2;
ImageIcon hall_icon3;
ImageIcon hall_icon4;




private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    ImagePanel.setVisible(true);
    CreateHall_panel.setVisible(false);
    
    
}//GEN-LAST:event_jButton8ActionPerformed
   

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    CreateHall_panel.setVisible(true);
    //HallBooking_panel.setVisible(true);
    jTabbedPane1.setSelectedIndex(0);
            reset();}//GEN-LAST:event_jButton4ActionPerformed

private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
 
if(name.getText()!=null && name.getText().trim().length()>=1){             
    
    if(max_Capacity.getText()!=null && max_Capacity.getText().trim().length()>1 ){                                
        
        if(jTextField3.getText().trim().length()>=1 && jTextField4.getText().trim().length()>=1 && jTextField5.getText().trim().length()>=1 && jTextField9.getText().trim().length()>=1 && jTextField10.getText().trim().length()>=1 && jTextField11.getText().trim().length()>=1){
        
            if(basic1.isSelected()==false || cascade1.isSelected()==false){                   
            
            if(FloorCombo.getSelectedIndex()!=-1){                                       
                if(advnceBooking_dura.getText() !=null && advnceBooking_dura.getText().trim().length()>0){
                if(luxuryTax.getSelectedIndex() !=-1 ){                        
                     if(M_hourl_TimeSlot_Combo.getItemCount()>0  ||  M_halfDay_TimeSlot_Combo.getItemCount()>0 ||   M_fullDay_TimeSlot_Combo.getItemCount()>0){
                      if(payment_text.getText()!=null && payment_text.getText().trim().length()>0){
                          if(Adv_acount_combo.getSelectedIndex()!=-1){   
                             if(reve_accnt_combo.getSelectedIndex()!=-1){   
                               if(cancellation_accnt_combo.getSelectedIndex()!=-1){
                                 
                                 
                     try {
                        
                        Hall_Name = name.getText();
                        
                        Max_Capacity=Double.parseDouble(max_Capacity.getText());
                        M_hourly=Double.parseDouble(jTextField3.getText()); 
                        M_half = Double.parseDouble(jTextField4.getText());
                        M_Full = Double.parseDouble(jTextField5.getText());
                        N_hourly = Double.parseDouble(jTextField9.getText());
                        N_half = Double.parseDouble(jTextField10.getText());
                        N_full = Double.parseDouble(jTextField11.getText());
                        advance_booking_duration = Integer.parseInt(advnceBooking_dura.getText());
                        //Luxury_Tax = Double.parseDouble(luxuryTax.getText()); 
                        //AnyOther_Tax = Double.parseDouble(AnyOtherTax.getText());
                        Facilty = Facility.getText();
                        payment = Integer.parseInt(payment_text.getText());
                        
                        block_flag=0;
                        
                        if(basic1.isSelected()==true){ 
                            basic=1;
                            cascade=0;
                        }
                         else{ 
                            cascade=1;
                            basic=0;
                        }
                        
                        if(basic2.isSelected()){
                            Basic2 = 1;
                            Cascade2 = 0;
                        }
                        else{
                            Cascade2 = 1;
                            Basic2 = 0;
                        }
                        
                        
                        if (luxuryTax.getSelectedItem() != null) {
                            TaxCategoryInfo luxurytax  = (TaxCategoryInfo) luxuryTax.getSelectedItem(); 
                            Luxury_tax_Id = luxurytax.getID();

                        }   
                        
                        if(Tax2.getSelectedItem()!= null && Tax2.getSelectedIndex()!=-1){
                            
                           TaxCategoryInfo tci2  = (TaxCategoryInfo) Tax2.getSelectedItem();                         
                            Tax2_id = tci2.getID(); 
                        }
                        else{
                            Tax2_id = null;
                        }
                        if (Tax3.getSelectedItem() != null && Tax3.getSelectedIndex()!=-1)                
                        {                          
                          TaxCategoryInfo tci3  = (TaxCategoryInfo) Tax3.getSelectedItem();
                            Tax3_id = tci3.getID(); 
                        
                        }
                        else{
                            Tax3_id=null;
                        }
                        
                        
                        if(M_hourl_TimeSlot_Combo.getItemCount()>0){
                            StringBuffer stbr = new StringBuffer();
                            String xyz;
                            for(int i=0; i<M_hourl_TimeSlot_Combo.getItemCount() ; i++ ){
                                xyz = M_hourl_TimeSlot_Combo.getItemAt(i).toString();
                               stbr.append(xyz+";");
                            
                            }
                            hourly_slot1 = stbr.toString();
                        }
                         if(M_halfDay_TimeSlot_Combo.getItemCount()>0){
                            StringBuffer stbr = new StringBuffer();
                            String xyz;
                            for(int i=0; i<M_halfDay_TimeSlot_Combo.getItemCount() ; i++ ){
                                xyz = M_halfDay_TimeSlot_Combo.getItemAt(i).toString();
                               stbr.append(xyz+";");
                            
                            }
                            halfDay_slot1 = stbr.toString();
                        }
                         if(M_fullDay_TimeSlot_Combo.getItemCount()>0){
                            StringBuffer stbr = new StringBuffer();
                            String xyz;
                            for(int i=0; i<M_fullDay_TimeSlot_Combo.getItemCount() ; i++ ){
                                xyz = M_fullDay_TimeSlot_Combo.getItemAt(i).toString();
                               stbr.append(xyz+";");
                            
                            }
                            fullDay_slot1 = stbr.toString();
                        }
                        
                      
                        
                        Floor = (String) FloorCombo.getSelectedItem().toString();   
                        ID = UUID.randomUUID().toString(); 
                        id.setText(ID);
                        
                        if(floor_X == 0 && floor_Y == 0 ){
                            
                        }
                        
                        if(full_radio_btn.isSelected()){
                         Advance_perc = "100-0-0"; 
                        }
                        
                        String Adv_acc_name = Adv_acount_combo.getSelectedItem().toString();
                        String Rev_Acc_name = reve_accnt_combo.getSelectedItem().toString();
                        String Cancel_Acc_Name = cancellation_accnt_combo.getSelectedItem().toString();
                        
                        Advance_Acct_ID = dlfac.getaccountidByName(Adv_acc_name);
                        Revenue_Acct_ID = dlfac.getaccountidByName(Rev_Acc_name);
                        Cancel_Acct_ID = dlfac.getaccountidByName(Cancel_Acc_Name);
                                
                        Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                          private Component MainButtons;                                          
                        @Override      
                        protected Object transact() throws BasicException {   
                         
                            
                            
                         insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO hall_master (ID ,FLOOR , NAME ,MAX_CAPACITY, M_HOURS, M_HALF, M_FULL , N_HOURS , N_HALF, N_FULL, LUXURYTAX ,TAX2 , TAX3 , BASIC1 , CASCADE1 , FACILITLIES , CRBY , CRDATE , CRHOST , FLOOR_X , FLOOR_Y , ACTIVE , HALL_ICON , BOOKING_DURA, BASIC2 , CASCADE2 , PAYMENT_DAYS ,  M_HOURLY_SLOTS  , M_HALFDAY_SLOT , M_FULLDAY_SLOT , BLOCK_FLAG , ADVANCE_PERC , ADVNCE_ACCT , REVENUE_ACCT , CANCEL_ACCT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.STRING, Datas.DOUBLE ,Datas.DOUBLE, Datas.DOUBLE,Datas.DOUBLE , Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE ,Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.INT , Datas.INT ,  Datas.STRING,Datas.STRING ,Datas.TIMESTAMP ,Datas.STRING , Datas.INT , Datas.INT , Datas.INT , Datas.IMAGE , Datas.INT , Datas.INT , Datas.INT , Datas.INT ,Datas.STRING ,Datas.STRING ,Datas.STRING , Datas.INT , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING})                         
                        ).exec(new Object[]{ID, Floor ,Hall_Name ,Max_Capacity ,M_hourly ,M_half , M_Full , N_hourly ,N_half ,N_full , Luxury_tax_Id ,Tax2_id , Tax3_id , basic , cascade ,  Facilty ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , floor_X , floor_Y , active , Final_icon , advance_booking_duration , Basic2 , Cascade2 , payment , hourly_slot1 , halfDay_slot1 ,fullDay_slot1 , block_flag , Advance_perc , Advance_Acct_ID , Revenue_Acct_ID ,Cancel_Acct_ID });                                                                                                
                         
                         
                        
                         
                        
                       int insert_image =  new PreparedSentence(m_App.getSession() , "INSERT INTO hall_images (ID , IMAGE1  , IMAGE2 , IMAGE3  ) VALUES (?,?,?,?)"                      
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
                            jTabbedPane1.setSelectedIndex(0);
                            jPanel2.setVisible(false);                    
                           // HallBooking_panel.setVisible(true);            
                            CreateHall_panel.setVisible(true);  
                        
                        
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
                    
                     catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(this, " Enter only numeric value for Amount and Max_Capacity..!!", " Tax", JOptionPane.ERROR_MESSAGE);               
                           }     catch (BasicException ex) {
                                     Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                       
                     
                     
                     
                     
                     
                               }
                               else{
                                   JOptionPane.showMessageDialog(this, " Select Cancellation  Account..!!", " Cancellation  Account..!!", JOptionPane.ERROR_MESSAGE);
                               }
                     
                             }
                             else{
                                  JOptionPane.showMessageDialog(this, " Select Revenue Account..!!", " Revenue Account..!!", JOptionPane.ERROR_MESSAGE);
                             }
                       }
                          else{
                               JOptionPane.showMessageDialog(this, " Select Advance Account ..!!", " Advance Account ..!!", JOptionPane.ERROR_MESSAGE);
                          }
                      
                      }
                      else{
                                JOptionPane.showMessageDialog(this, " Enter Advance Payment Duration..! ", " Tax", JOptionPane.ERROR_MESSAGE);
                            }
                        
                     }
                     else{
                         JOptionPane.showMessageDialog(this, " Select timing Slots for Members ..! ", " Tax", JOptionPane.ERROR_MESSAGE);
                     }
                
                }
                
                   else{                 
                        JOptionPane.showMessageDialog(this, " SELECT  TAX 1 ..! ", " Tax", JOptionPane.ERROR_MESSAGE);     
                        }                              
                        } 
            else{
                 JOptionPane.showMessageDialog(this, " Enter Advanced Booking Duration ..! ", " Tax", JOptionPane.ERROR_MESSAGE); 
            }
            }
                else{             
                JOptionPane.showMessageDialog(this, " SELECT FLOOR ..! ", " Tax", JOptionPane.ERROR_MESSAGE);    
                }          
                } 
                 
                else{             
                JOptionPane.showMessageDialog(this, " Select Any One 'Basic OR Cascade' ", " Tax", JOptionPane.ERROR_MESSAGE);           
                }                
                } 
                else{
                JOptionPane.showMessageDialog(this, " Amount field should not be empty..!!! ", " Amount", JOptionPane.ERROR_MESSAGE);
                    }
                }
    
                else{           
                JOptionPane.showMessageDialog(this, " Enter Max. Capacity   ", " Max Cap", JOptionPane.ERROR_MESSAGE);   
                }      
                }   

                else{               
                JOptionPane.showMessageDialog(this, " Enter Hall  Name  ", " Hall Name", JOptionPane.ERROR_MESSAGE);   
                }      
                                   

                                   

                                   
                                   

                                   
                              


}//GEN-LAST:event_saveActionPerformed

    private void basic1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_basic1ItemStateChanged
     /* if(jRadioButton1.isSelected()==true){
          jRadioButton2.setSelected(false);
      }
      else{
          jRadioButton2.setSelected(true);
      }*/
    }//GEN-LAST:event_basic1ItemStateChanged

    private void cascade1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cascade1ItemStateChanged
/*       if(jRadioButton2.isSelected()==true){
          jRadioButton1.setSelected(false);
      }
      else{
          jRadioButton1.setSelected(true);
      }*/
    }//GEN-LAST:event_cascade1ItemStateChanged

    private void basic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_basic1MouseClicked
       
    }//GEN-LAST:event_basic1MouseClicked

    private void cascade1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cascade1MouseClicked
      
    }//GEN-LAST:event_cascade1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(jTable1.getSelectedColumn()!=-1){
           edit.setEnabled(true);
           delete.setEnabled(true);
            jButton6.setEnabled(true);
           int row = jTable1.getSelectedRow();
          
       }
       else{
           edit.setEnabled(false);
           delete.setEnabled(false);
            jButton6.setEnabled(false);
       }
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
      
        if(jTable1.getSelectedRow()!=-1){
             if(jTable1.getSelectedRow()<hallTable.getHallSize()){
                 
                 int row = jTable1.getSelectedRow();
                 HallTableInfo EditData = hallTable.getHallPath().get(row);
                 
                
                 name.setText(EditData.getName());
             
                 max_Capacity.setText(""+EditData.getMax_Cap());
                 jTextField3.setText(decimalFormat.format(EditData.getMem_hourly_charge()));
                 jTextField4.setText(decimalFormat.format(EditData.getMem_halfDay_charge()));
                 jTextField5.setText(decimalFormat.format(EditData.getMem_fullDay_charge()));
                 jTextField9.setText(decimalFormat.format(EditData.getNon_Mem_hourly_charge()));
                 jTextField10.setText(decimalFormat.format(EditData.getNon_Mem_halfDay_charge()));
                 jTextField11.setText(decimalFormat.format(EditData.getNon_Mem_fullDay_charge()));
                 Facility.setText(EditData.getFacilities());
                 advnceBooking_dura.setText(""+EditData.getAdvanceBookingDuration());
                 payment_text.setText(""+EditData.getPAYMENT_DAYS());
                 
                 
                 M_hourly_Slots  = new ArrayList<String>();
                 String mem_hour_time = EditData.getM_HOURLY_SLOTS();
                 if(mem_hour_time!=null){
                 String[] items = mem_hour_time.split(";");
                 Collection<String> collection1 = new ArrayList<String>();
                  for(int i=0;i<items.length;i++){
                      collection1.add(items[i]);
                  }
                  M_hourly_Slots.addAll(collection1);
                  M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots);
                  M_hourl_TimeSlot_Combo.setModel(M_hourly_Slots_C);
                 }
                  
                 
                 M_halfDay_Slots = new ArrayList<String>();
                 String mem_half_time = EditData.getM_HALFDAY_SLOT();
                 if(mem_half_time!=null){
                 String[] items2 = mem_half_time.split(";");
                 Collection<String> collection2 = new ArrayList<String>();
                  for(int i=0;i<items2.length;i++){
                      collection2.add(items2[i]);
                  }
                  M_halfDay_Slots.addAll(collection2);
                  M_halfDay_Slots_C = new ComboBoxValModel(M_halfDay_Slots);
                  M_halfDay_TimeSlot_Combo.setModel(M_halfDay_Slots_C);
                 }
                 
                 
                 M_fullDay_Slots = new ArrayList<String>();
                 String mem_full_time = EditData.getM_FULLDAY_SLOT();
                 if(mem_full_time!=null){
                 String[] items3 = mem_full_time.split(";");
                 Collection<String> collection3 = new ArrayList<String>();
                  for(int i=0;i<items3.length;i++){
                      collection3.add(items3[i]);
                  }
                 M_fullDay_Slots.addAll(collection3);
                 M_fullDay_Slots_C = new ComboBoxValModel(M_fullDay_Slots);
                 M_fullDay_TimeSlot_Combo.setModel(M_fullDay_Slots_C);
                 }
                 
                 
                 
                 
               
                 
                 int temp1 = EditData.getFloor_X();
                 X1.setText(""+temp1);
                 int temp2 = EditData.getFloor_Y();
                 Y1.setText(""+temp2);
                  BufferedImage tempBI = null;
                  tempBI = EditData.getIcon();
                 if(tempBI!=null){
                  ImageIcon  ic = new ImageIcon(tempBI);
                 
                 temp_icon.setIcon(ic);
                 comp = temp_icon;
                 BackImageLabel.add(comp);
                 comp.setLocation(temp1, temp2);
                 }
                 
                 jTabbedPane1.setSelectedIndex(1);
                   saveChanges.setVisible(true);
                 save.setVisible(false);
                 
                 
                 // For Members Tariff
                    timing_label.setVisible(true);
                   
                    From.setVisible(true);
                    jTextField3.setVisible(true);  
                  
                    
                 // For Non Members
                    forNonMembers.setVisible(true);
                   
                    rate4.setVisible(true);        
                    jTextField11.setVisible(true);       
                            
                  //Set timing Slots   
                  String TempId = EditData.getId();  
                  id.setText(EditData.getId());
                  
                  BufferedImage IMAGE1 = EditData.getIMAGE1();
                  if(IMAGE1 !=null){
                     m_jImage.setImage(IMAGE1);
                  }
                   BufferedImage IMAGE2 = EditData.getIMAGE2();
                  if(IMAGE2 !=null){
                     m_jImage1.setImage(IMAGE2);
                  }
                  
                   BufferedImage IMAGE3 = EditData.getIMAGE3();
                  if(IMAGE3 !=null){
                     m_jImage2.setImage(IMAGE3);
                  }
                  
                 //set combobox values
                 String luxurytax = EditData.getLuxuryTax();
                 for(int i = 1 ; i < staxmodel.getSize(); i++ )
                 {
                     TaxCategoryInfo temp  = (TaxCategoryInfo) staxmodel.getElementAt(i);
                     
                     if(temp.getName().equals(luxurytax)){
                         luxuryTax.setSelectedIndex(i);
                     }
                 }
                 
                 
           
                
                 String tax2 = EditData.getTax_2();
                 if(tax2!=null){
                    for(int i = 1 ; i < stax2.getSize(); i++ )
                    {
                        TaxCategoryInfo temp  = (TaxCategoryInfo) stax2.getElementAt(i);

                        if(temp.getName().equals(tax2)){
                            Tax2.setSelectedIndex(i);
                        }
                    }
                 }
                 
                 String tax3 = EditData.getTax_3();
                 if(tax3!=null){
                        for(int i = 1 ; i < stax3.getSize(); i++ )
                        {
                            TaxCategoryInfo temp  = (TaxCategoryInfo) stax3.getElementAt(i);

                            if(temp.getName().equals(tax3)){
                                Tax3.setSelectedIndex(i);
                            }
                        }
                       
                 }
                
                 
                 
                 String flr = EditData.getFloor();
                 for(int i=0; i< FloorListModel.getSize() ; i++){
                    FloorTableInfo  temp =  (FloorTableInfo) FloorListModel.getElementAt(i);
                   
                        if(temp.getName().equals(flr))
                        {
                            FloorCombo.setSelectedIndex(i);
                        }
                 }
                 
                 
                 String ADVNCE_ACCT = EditData.getADVNCE_ACCT();
                 if(ADVNCE_ACCT!=null && ADVNCE_ACCT.length()>0){
                      for(int i=0;i<acclist.size();i++){
                          String x = acclist.get(i).toString();
                          if(x.equals(ADVNCE_ACCT)){
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
                          String x = sacclist.get(i).toString();
                          if(x.equals(REVENUE_ACCT)){
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
                          String x = sacclist.get(i).toString();
                          if(x.equals(CANCEL_ACCT)){
                              cancellation_accnt_combo.setSelectedIndex(i);
                          }
                         }
                 }
                 else{
                      cancellation_accnt_combo.setSelectedIndex(-1);
                  }
                 
                 
                 
                 if(EditData.getBasic()==1){
                     basic1.setSelected(true);
                 }
                 else{
                     cascade1.setSelected(true);
                 }
                
                 
                 if(EditData.getBasic2()==1){
                     basic2.setSelected(true);
                 }
                 else{
                     cascade2.setSelected(true);
                 }
                 
                  select_more_tax.setSelected(true);
                  select_more_tax1.setSelected(true);
                  jLabel19.setVisible(true);
                  Tax3.setVisible(true);
                 
                 
                 // OPERATION FOR ADVANCE PERCENTAGE
                 
                 String Z =  EditData.getADVANCE_PERC();
                
                 String[] t = Z.split("-");
                 Adv_Perc.setText(t[0]);
                 Chk_in_perc.setText(t[1]);
                 chk_out_perc.setText(t[2]);
                 
                 
                 if(Double.parseDouble(t[0])==100){
                    full_radio_btn.setSelected(true); 
                    partial_variation_panel.setVisible(false);
                 }
                 else{
                    partial_radio_btn.setSelected(true);  
                 }
                 
                 
             }
            
            
        }
        
        
        
    }//GEN-LAST:event_editActionPerformed

    private void saveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChangesActionPerformed
     if(name.getText()!=null && name.getText().length()>1){             
    
    if(max_Capacity.getText()!=null && max_Capacity.getText().length()>1){                                
        
        if(basic1.isSelected()==false || cascade1.isSelected()==false){                   
            
            if(FloorCombo.getSelectedIndex()!=-1){                                       
                
                if(luxuryTax.getSelectedIndex() !=-1 ){                        
                   if(advnceBooking_dura.getText() != null && advnceBooking_dura.getText().trim().length() > 0){
                         if(jTextField3.getText().trim().length()>=1 && jTextField4.getText().trim().length()>=1 && jTextField5.getText().trim().length()>=1 && jTextField9.getText().trim().length()>=1 && jTextField10.getText().trim().length()>=1 && jTextField11.getText().trim().length()>=1){
                          if(Adv_acount_combo.getSelectedIndex()!=-1){   
                             if(reve_accnt_combo.getSelectedIndex()!=-1){   
                                 if(cancellation_accnt_combo.getSelectedIndex()!=-1){   
                             
                             
                        Hall_Name = name.getText();
                        Max_Capacity=Double.parseDouble(max_Capacity.getText());
                        M_hourly=Double.parseDouble(jTextField3.getText()); 
                        M_half = Double.parseDouble(jTextField4.getText());
                        M_Full = Double.parseDouble(jTextField5.getText());
                        N_hourly = Double.parseDouble(jTextField9.getText());
                        N_half = Double.parseDouble(jTextField10.getText());
                        N_full = Double.parseDouble(jTextField11.getText());
                        advance_booking_duration = Integer.parseInt(advnceBooking_dura.getText());
                        //Luxury_Tax = Double.parseDouble(luxuryTax.getText()); 
                        //AnyOther_Tax = Double.parseDouble(AnyOtherTax.getText());
                        Facilty = Facility.getText();
                        
                        payment = Integer.parseInt(payment_text.getText());
                        
                        floor_X = Integer.parseInt(X1.getText());
                        floor_Y = Integer.parseInt(Y1.getText());
                        
                        if(basic1.isSelected()==true){ 
                            basic=1;
                            cascade=0;
                        }
                         else{ 
                            basic=0;
                            cascade=1;
                        }
                         if(basic2.isSelected()){
                            Basic2 = 1;
                            Cascade2 = 0;
                        }
                        else{
                            Cascade2 = 1;
                            Basic2 = 0;
                        }
                        
                        
                        
                        if (luxuryTax.getSelectedItem() != null ) {
                            TaxCategoryInfo luxurytax  = (TaxCategoryInfo) luxuryTax.getSelectedItem(); 
                            Luxury_tax_Id = luxurytax.getID();

                        }
                        
                        if(Tax2.getSelectedItem()!= null && Tax2.getSelectedIndex()!=-1){
                            
                           TaxCategoryInfo tax2  = (TaxCategoryInfo) Tax2.getSelectedItem();                         
                            Tax2_id = tax2.getID(); 
                        }
                        else{
                            Tax2_id = null;
                        }
                        
                        if (Tax3.getSelectedItem() != null && Tax3.getSelectedIndex()!=-1)                
                        {                          
                            TaxCategoryInfo tax3  = (TaxCategoryInfo) Tax3.getSelectedItem();
                            Tax3_id = tax3.getID(); 
                        
                        }
                        else{
                            Tax3_id=null;
                        }
                        Floor = (String) FloorCombo.getSelectedItem().toString();   
                       
                        ID = id.getText();
                        
                        
                        // Timings Slots
                        
                        if(M_hourl_TimeSlot_Combo.getItemCount()>0){
                            StringBuffer stbr = new StringBuffer();
                            String xyz;
                            for(int i=0; i<M_hourl_TimeSlot_Combo.getItemCount() ; i++ ){
                                xyz = M_hourl_TimeSlot_Combo.getItemAt(i).toString();
                               stbr.append(xyz+";");
                            
                            }
                            hourly_slot1 = stbr.toString();
                        }
                         if(M_halfDay_TimeSlot_Combo.getItemCount()>0){
                            StringBuffer stbr = new StringBuffer();
                            String xyz;
                            for(int i=0; i<M_halfDay_TimeSlot_Combo.getItemCount() ; i++ ){
                                xyz = M_halfDay_TimeSlot_Combo.getItemAt(i).toString();
                               stbr.append(xyz+";");
                            
                            }
                            halfDay_slot1 = stbr.toString();
                        }
                         if(M_fullDay_TimeSlot_Combo.getItemCount()>0){
                            StringBuffer stbr = new StringBuffer();
                            String xyz;
                            for(int i=0; i<M_fullDay_TimeSlot_Combo.getItemCount() ; i++ ){
                                xyz = M_fullDay_TimeSlot_Combo.getItemAt(i).toString();
                               stbr.append(xyz+";");
                            
                            }
                            fullDay_slot1 = stbr.toString();
                        }
                        
                        if(full_radio_btn.isSelected()){
                         Advance_perc = "100-0-0"; 
                        }
                        else{
                            String x1 = Adv_Perc.getText();
                            String x2 = Chk_in_perc.getText();
                            String x3 = chk_out_perc.getText();
                            
                            Advance_perc = x1+"-"+x2+"-"+x3;
                        }
                         
                       String Adv_acc_name = Adv_acount_combo.getSelectedItem().toString();
                       String Rev_Acc_name = reve_accnt_combo.getSelectedItem().toString();
                       String Cancel_Acc_Name = cancellation_accnt_combo.getSelectedItem().toString();
                             
                       try {
                                 Advance_Acct_ID = dlfac.getaccountidByName(Adv_acc_name);
                                 Revenue_Acct_ID = dlfac.getaccountidByName(Rev_Acc_name);
                                 Cancel_Acct_ID = dlfac.getaccountidByName(Cancel_Acc_Name);
                                 
                             } catch (BasicException ex) {
                                 Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                             }
                       
                                      
                
                   try {
                              int update_hall_master =  new PreparedSentence(m_App.getSession(), "UPDATE hall_master  SET FLOOR=? , NAME=?,MAX_CAPACITY=? ,  M_HOURS=?, M_HALF=? ,M_FULL=?,N_HOURS=? ,N_HALF=? , N_FULL=? , BASIC1=? , CASCADE1=? , FACILITLIES=? , CRBY=? ,  CRDATE=? , CRHOST=? , LUXURYTAX=? , TAX2=? , TAX3=? , ACTIVE=? , HALL_ICON=? , FLOOR_X=? , FLOOR_Y=?  , BOOKING_DURA=? , BASIC2=? , CASCADE2=? , PAYMENT_DAYS=? , M_HOURLY_SLOTS=? , M_HALFDAY_SLOT=?, M_FULLDAY_SLOT=? , ADVANCE_PERC=? , ADVNCE_ACCT=?,  REVENUE_ACCT=?  , CANCEL_ACCT=? WHERE ID = ? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.DOUBLE , Datas.DOUBLE ,Datas.DOUBLE , Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE ,Datas.DOUBLE ,   Datas.INT ,Datas.INT , Datas.STRING , Datas.STRING , Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING , Datas.STRING , Datas.STRING , Datas.INT , Datas.IMAGE , Datas.INT , Datas.INT , Datas.INT ,Datas.INT , Datas.INT ,Datas.INT ,Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING ,Datas.STRING,  Datas.STRING })).exec
                                                    (new Object[]{Floor , Hall_Name , Max_Capacity , M_hourly , M_half , M_Full ,N_hourly , N_half , N_full , basic , cascade , Facilty ,  m_App.getAppUserView().getUser().getName() ,new Date() ,m_App.getProperties().getHost() , Luxury_tax_Id , Tax2_id , Tax3_id , active , Final_icon , floor_X , floor_Y , advance_booking_duration ,Basic2 , Cascade2 ,payment ,hourly_slot1 , halfDay_slot1 , fullDay_slot1  , Advance_perc , Advance_Acct_ID , Revenue_Acct_ID , Cancel_Acct_ID,   ID });
                                
                         
                        
                          if(count==1){
                              
                             int update_Guest_images =  new PreparedSentence(m_App.getSession(), "UPDATE hall_images  SET IMAGE1=? , IMAGE2=?, IMAGE3=?  WHERE ID =? "
                                                   , new SerializerWriteBasic(new Datas[]{Datas.IMAGE ,Datas.IMAGE , Datas.IMAGE , Datas.STRING   })).exec
                                                    (new Object[]{ Image1 , Image2 , Image3 , ID }); 
                              
                          }
                          
                          
                          
                              
                              
                         reset();      
                         loaddata();    
                         JOptionPane.showMessageDialog(this, "Data Updated  Succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);                      
                             
                         jTabbedPane1.setSelectedIndex(0);     
                              
                         } catch (BasicException ex) {
                             ex.printStackTrace();
                                           new MessageInf(ex).show(new JFrame());        
                             Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                             
                        }
                        
                        
                            jPanel2.setVisible(false);                    
                           // HallBooking_panel.setVisible(true);            
                            CreateHall_panel.setVisible(true);                
                                
                           
                            
                            
                            
                                 }
                                 else{
                                      JOptionPane.showMessageDialog(this, " Select Cancellation  Account..!!", " Cancellation  Account..!!", JOptionPane.ERROR_MESSAGE);
                                 }
                            
                        }
                             else{
                                  JOptionPane.showMessageDialog(this, " Select Revenue Account..!!", " Revenue Account..!!", JOptionPane.ERROR_MESSAGE);
                             }
                       }
                          else{
                               JOptionPane.showMessageDialog(this, " Select Advance Account ..!!", " Advance Account ..!!", JOptionPane.ERROR_MESSAGE);
                    }     
                            
                            
                            
                   }
                   else{
                       JOptionPane.showMessageDialog(this, " Amount field Should not be empty ..! ", " Tax", JOptionPane.ERROR_MESSAGE); 
                   }
                }
                   else{
                       JOptionPane.showMessageDialog(this, " Enter Advance Booking Duration...! ", " Tax", JOptionPane.ERROR_MESSAGE);  
                   }
                }
                   
                   else{                 
                        JOptionPane.showMessageDialog(this, " SELECT  TAX 1 ..! ", " Tax", JOptionPane.ERROR_MESSAGE);     
                        }                              
                } 
            
                else{             
                JOptionPane.showMessageDialog(this, " SELECT FLOOR ..! ", " Tax", JOptionPane.ERROR_MESSAGE);    
                }          
                }  
        
                else{             
                JOptionPane.showMessageDialog(this, " Select Any One 'Basic OR Cascade' ", " Tax", JOptionPane.ERROR_MESSAGE);           
                }                
                }  
    
                else{           
                JOptionPane.showMessageDialog(this, " Enter Max. Capacity   ", " Max Cap", JOptionPane.ERROR_MESSAGE);   
                }      
                }   

                else{               
                JOptionPane.showMessageDialog(this, " Enter Hall  Name  ", " Hall Name", JOptionPane.ERROR_MESSAGE);   
                }      
                                   

                  
        
        
        
        
        
        
    }//GEN-LAST:event_saveChangesActionPerformed

    private void luxuryTaxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_luxuryTaxItemStateChanged
        if(luxuryTax.getSelectedIndex()==-1){
             jLabel16.setVisible(false);
             Tax2.setVisible(false);
             select_more_tax1.setVisible(false);
             Tax2.setSelectedIndex(-1);
             Tax3.setVisible(false);
             Tax3.setSelectedIndex(-1);
             jLabel19.setVisible(false);       
             basic1.setVisible(false);
             cascade1.setVisible(false);       
              select_more_tax.setSelected(false);
              select_more_tax1.setSelected(false);
              select_more_tax.setVisible(false);
            
             }
        else{
           
            select_more_tax.setVisible(true);
            select_more_tax.setSelected(false);
            
        }
    }//GEN-LAST:event_luxuryTaxItemStateChanged

    private void select_more_tax1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_more_tax1ItemStateChanged
        if(select_more_tax.isSelected()){
            jLabel19.setVisible(true);
            Tax3.setVisible(true);
        }
        else{
            jLabel19.setVisible(false);
            Tax3.setVisible(false);
            Tax3.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_select_more_tax1ItemStateChanged

    private void luxuryTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luxuryTaxActionPerformed
        if(luxuryTax.getSelectedIndex()!=-1){
            select_more_tax.setVisible(true);
            basic1.setSelected(true);
            cascade1.setSelected(false);
            serviceTax();
            
        }
    }//GEN-LAST:event_luxuryTaxActionPerformed

    private void Tax2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tax2ActionPerformed
    
    }//GEN-LAST:event_Tax2ActionPerformed

    private void Tax3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tax3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tax3ActionPerformed

    private void select_more_tax1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_more_tax1ActionPerformed
     
    }//GEN-LAST:event_select_more_tax1ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       if(jTable1.getSelectedRow()!=-1){
             if(jTable1.getSelectedRow()<hallTable.getHallSize()){
                 int delete_room = JOptionPane.showConfirmDialog(jPanel5, "Do you want to Deactivate hall?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
                     if(delete_room == JOptionPane.YES_OPTION)
                        {
                             int row = jTable1.getSelectedRow();
                             HallTableInfo DeleteData = hallTable.getHallPath().get(row);

                             id.setText(DeleteData.getId());
                             String del_ID = id.getText();
                            try {
                                int num =  new PreparedSentence(m_App.getSession(), "UPDATE hall_master SET ACTIVE=false , DEACBY=? , DEACDATE=?,DEACHOST=? where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP , Datas.STRING , Datas.STRING , })).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost() , del_ID});
                                //int num1 =  new PreparedSentence(m_App.getSession(), "delete from booking_hall_images where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{del_ID});


                                JOptionPane.showMessageDialog(this, "Hall Deactivates  Succesfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);                  
                                loaddata();

                            } catch (BasicException ex) {
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                 
             }
       }
       
    }//GEN-LAST:event_deleteActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
                   
        if(FloorCombo.getSelectedIndex()!=-1)
        {
            
            location_panel.setVisible(true);
            CreateHall_panel.setVisible(false);
            
        }
        else{
            
            BackImageLabel.setIcon(null);
            if(BackImageLabel.getComponentCount()>0){
                BackImageLabel.remove(comp);
            }
            
        JOptionPane.showMessageDialog(this, " Select Floor First ..! ", " Tax", JOptionPane.ERROR_MESSAGE); 
            
        }
                    
                   
    }//GEN-LAST:event_jButton11ActionPerformed

    private void BackImageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackImageLabelMouseClicked
      int x =  evt.getX();
       int y = evt.getY();
      X1.setText(""+x);
      Y1.setText(""+y);
      
      comp.setLocation(x,y);
     // comp.setBackground(Color.red);
    }//GEN-LAST:event_BackImageLabelMouseClicked
Component comp = null;

BufferedImage Final_icon ;
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
      floor_X = Integer.parseInt(X1.getText());
      floor_Y = Integer.parseInt(Y1.getText());
      
      ImageIcon ic = (ImageIcon) temp_icon.getIcon();
      Final_icon = (BufferedImage) ic.getImage();
      location_panel.setVisible(false);
      CreateHall_panel.setVisible(true);
       
       
    }//GEN-LAST:event_jButton15ActionPerformed

    private void showAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showAllItemStateChanged
        try {
            loaddata();
        } catch (BasicException ex) {
            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showAllItemStateChanged

    private void hall_ic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hall_ic1MouseClicked
      
    }//GEN-LAST:event_hall_ic1MouseClicked

    private void hall_ic2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hall_ic2MouseClicked
    
      
    }//GEN-LAST:event_hall_ic2MouseClicked

    private void hall_ic3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hall_ic3MouseClicked
    
       
    }//GEN-LAST:event_hall_ic3MouseClicked

    private void hall_ic4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hall_ic4MouseClicked
   
      
    }//GEN-LAST:event_hall_ic4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
        if(BackImageLabel.getComponentCount()>=1){
        
           
        BackImageLabel.remove(comp);
       
        hall_ic1.setVisible(true);
        jPanel6.add(temp_icon);
        }
       
       
         hall_ic1.setEnabled(true);
         hall_ic2.setEnabled(true); 
         hall_ic3.setEnabled(true);
         hall_ic4.setEnabled(true);
         
        CreateHall_panel.setVisible(true);        
        location_panel.setVisible(false);    
    
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void hall_ic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic1ActionPerformed
        
        if(BackImageLabel.getComponentCount()==1){
            ImageIcon ic = (ImageIcon) hall_ic1.getIcon();
            temp_icon.setIcon(ic);
             comp = temp_icon;
        }
        else{
        ImageIcon ic = (ImageIcon) hall_ic1.getIcon();
        temp_icon.setIcon(ic);
      
        comp = temp_icon;
         BackImageLabel.add(comp);
        }
      
      
    }//GEN-LAST:event_hall_ic1ActionPerformed

    private void hall_ic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic2ActionPerformed
       
       if(BackImageLabel.getComponentCount()==1){
             ImageIcon ic = (ImageIcon) hall_ic2.getIcon();
            temp_icon.setIcon(ic);
             comp = temp_icon;
             
        } 
       else{
        ImageIcon ic = (ImageIcon) hall_ic2.getIcon();
        temp_icon.setIcon(ic);
        comp = temp_icon;
        
           BackImageLabel.add(comp);
       }
    }//GEN-LAST:event_hall_ic2ActionPerformed

    private void hall_ic3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic3ActionPerformed
       
        if(BackImageLabel.getComponentCount()==1){
             ImageIcon ic = (ImageIcon) hall_ic3.getIcon();
        temp_icon.setIcon(ic);
        comp = temp_icon;
          
        }
        else{
         ImageIcon ic = (ImageIcon) hall_ic3.getIcon();
        temp_icon.setIcon(ic);
        comp = temp_icon;
      
       BackImageLabel.add(comp);
        }
    }//GEN-LAST:event_hall_ic3ActionPerformed

    private void hall_ic4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic4ActionPerformed
        if(BackImageLabel.getComponentCount()==1){
            
           ImageIcon ic = (ImageIcon) hall_ic4.getIcon();
        temp_icon.setIcon(ic);
        comp = temp_icon;
        }
        else{
         ImageIcon ic = (ImageIcon) hall_ic4.getIcon();
        temp_icon.setIcon(ic);
        comp = temp_icon;
      
        BackImageLabel.add(comp);
        }
    }//GEN-LAST:event_hall_ic4ActionPerformed

    private void temp_iconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temp_iconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_temp_iconActionPerformed

    private void FloorComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FloorComboItemStateChanged
        if(FloorCombo.getSelectedIndex()!=-1){
        fti = (FloorTableInfo) FloorCombo.getSelectedItem();
        if(fti!=null){
            bufferedImage = fti.getImg();
            ImageIcon ic  = new ImageIcon(bufferedImage);
            BackImageLabel.setIcon(ic);
           
            BufferedImage ic1 = fti.getIcon1();
            BufferedImage ic2 = fti.getIcon2();
            BufferedImage ic3 = fti.getIcon3();
            BufferedImage ic4 = fti.getIcon4();
            
            
            if(ic1!=null){
                  hall_icon1 = new ImageIcon(ic1);
            }
            else{
                 hall_icon1 = new ImageIcon();
            }
            
            
           
            if(ic2!=null){
                 hall_icon2 = new ImageIcon(ic2);
            }
            else{
                 hall_icon2 = new ImageIcon();
            }
           if(ic3!=null){
                 hall_icon3 = new ImageIcon(ic3);
            }
            else{
                hall_icon3 = new ImageIcon();
            }
           
            if(ic4!=null){
                hall_icon4 = new ImageIcon(ic4);
            }
            else{
                hall_icon4 = new ImageIcon();
            }
           
           
           
           
            
            
            hall_ic1.setIcon(hall_icon1);
            hall_ic2.setIcon(hall_icon2);
            hall_ic3.setIcon(hall_icon3);
            hall_ic4.setIcon(hall_icon4);
            
        }  
        
    }      
    }//GEN-LAST:event_FloorComboItemStateChanged

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(jTextField3, "Please enter only numbers..");
    
            jTextField3.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(jTextField4, "Please enter only numbers..");
    
            jTextField4.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(jTextField5, "Please enter only numbers..");
    
            jTextField5.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(jTextField9, "Please enter only numbers..");
    
            jTextField9.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
      char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(jTextField10, "Please enter only numbers..");
    
            jTextField10.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {   if(c!='.'){
        JOptionPane.showMessageDialog(jTextField11, "Please enter only numbers..");
    
            jTextField11.setText(null);
    
             }
    }
    }
    }//GEN-LAST:event_jTextField11KeyReleased

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

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       charges_panel.setVisible(true);
       forNonMembers1.setVisible(true);
       forNonMembers.setVisible(true);
       CreateHall_panel.setVisible(false);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
      
      //  if(M_hourl_TimeSlot_Combo.getItemCount()>0 ){
         //        if(M_halfDay_TimeSlot_Combo.getItemCount()>0){
            //         if( M_fullDay_TimeSlot_Combo.getItemCount()>0){
                     
                 
                    timing_panel.setVisible(false);

                    CreateHall_panel.setVisible(true);
       
       
       
       
       
       
             //    }
              //       else{
              //           JOptionPane.showMessageDialog(this, "Enter Full Day  Timing Slots ..!!! ", " Amount", JOptionPane.ERROR_MESSAGE);
            //         }
              //   }
             //    else{
            //           JOptionPane.showMessageDialog(this, "Enter Half Day  Timing Slots ..!!! ", " Amount", JOptionPane.ERROR_MESSAGE);     
             //    }
            //   }
            //   else{
            //        JOptionPane.showMessageDialog(this, "Enter Hourly Timing Slots ..!!! ", " Amount", JOptionPane.ERROR_MESSAGE);
           //    }
            
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
      timing_label.setVisible(true);
        timing_panel.setVisible(true);
       CreateHall_panel.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
      timing_panel.setVisible(false);
      CreateHall_panel.setVisible(true);
               
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
     if(jTextField3.getText().trim().length()>=1 && jTextField3.getText()!=null){
       if(jTextField4.getText().trim().length()>=1 && jTextField4.getText()!=null){  
         if(jTextField5.getText().trim().length()>=1 && jTextField5.getText()!=null){   
           if(jTextField9.getText().trim().length()>=1 && jTextField9.getText()!=null){
            if(jTextField10.getText().trim().length()>=1 && jTextField10.getText()!=null){
              if(jTextField11.getText().trim().length()>=1 && jTextField11.getText()!=null){
              
               
                  
        
        
                        charges_panel.setVisible(false);
                        CreateHall_panel.setVisible(true);

                        
                        
                  
             }
             else{
                 JOptionPane.showMessageDialog(this, "  Non Member Full Day Rate should not be empty..!!! ", " Amount", JOptionPane.ERROR_MESSAGE); 
             }
         }
         else{
                  JOptionPane.showMessageDialog(this, " Non Member  Half day Rate should not be empty..!!! ", " Amount", JOptionPane.ERROR_MESSAGE); 
         }
      }
      else{
         JOptionPane.showMessageDialog(this, " Non Member Hourly Rate should not be empty..!!! ", " Amount", JOptionPane.ERROR_MESSAGE); 
       }
      }
         else{
              JOptionPane.showMessageDialog(this, " Member Full Day Rate should not be empty..!!! ", " Amount", JOptionPane.ERROR_MESSAGE); 
         }
       }
       else{
           JOptionPane.showMessageDialog(this, " Member Half day Rate should not be empty..!!!", " Amount", JOptionPane.ERROR_MESSAGE);
       }
     }
       else{
          JOptionPane.showMessageDialog(this, " Member Hourly Rate should not be empty..!!! ", " Amount", JOptionPane.ERROR_MESSAGE); 
     }
        
        
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        charges_panel.setVisible(false);
       CreateHall_panel.setVisible(true);
               
    }//GEN-LAST:event_jButton18ActionPerformed

    private void FromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromActionPerformed
      Date date1=new Date();
      Date Prev_From_date = null;
      Date prev_To_date = null;
        try {
            prev_To_date = sdf.parse("1970-01-01 00:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
      
            if(M_hourly_Slots.size()!=0){
                for(int i=0;i<M_hourly_Slots.size();i++){
                    String slot = M_hourly_Slots.get(i).toString();
                    String[] items = slot.split("-");
                    String to = items[1];
                    try {
                        prev_To_date = (Date) Formats.TIME.parseValue(to);
                    } catch (BasicException ex) {
                        Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
      
      
      try {
            date1 = (Date) Formats.TIME.parseValue(jTextField1.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, prev_To_date);
        Date date_temp = sdf.parse("1970-01-01 23:59:59");
        if(date1.before(date_temp))
        {
        if (date1 != null) {
            if(prev_To_date !=null){
                if(date1.after(prev_To_date) || date1.equals(prev_To_date) ){

                    jTextField1.setText(Formats.TIME.formatValue(date1));
                    jTextField2.setText(null);
                }
                else{
                     JOptionPane.showMessageDialog(this, " Select 'From' time after previous  slot..!", " To ", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                    jTextField1.setText(Formats.TIME.formatValue(date1));
                    jTextField2.setText(null);
            }
        }
        
        }
        else{
             JOptionPane.showMessageDialog(this, "Please Dont change date", " To ", JOptionPane.ERROR_MESSAGE);
        }
        
        
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_FromActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Date date1=new Date();
       Date From_Date = null;
       
       try {
            date1 = (Date) Formats.TIME.parseValue(jTextField1.getText());
            From_Date = (Date) Formats.TIME.parseValue(jTextField1.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, date1);
        if (date1 != null) {
            if(From_Date!=null){    
            if(From_Date.before(date1)){
                    jTextField2.setText(Formats.TIME.formatValue(date1));
                }
                else{
                    JOptionPane.showMessageDialog(this, " 'To' time Should be after 'From' Time..!!  Please , Select Again..!", " To ", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
            else{
                 JOptionPane.showMessageDialog(this, " Select 'From' Time First..!", " To ", JOptionPane.ERROR_MESSAGE);
            }
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       Date date1=new Date();
        Date prev_To_date = null;
        try {
            prev_To_date = sdf.parse("1970-01-01 00:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         if(M_halfDay_Slots.size()!=0){
                for(int i=0;i<M_halfDay_Slots.size();i++){
                    String slot = M_halfDay_Slots.get(i).toString();
                    String[] items = slot.split("-");
                    String to = items[1];
                    try {
                        prev_To_date = (Date) Formats.TIME.parseValue(to);
                    } catch (BasicException ex) {
                        Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
       
       try {
            date1 = (Date) Formats.TIME.parseValue(jTextField6.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, prev_To_date);
        Date date_temp = sdf.parse("1970-01-01 23:59:59");
        if(date1.before(date_temp))
        {
        
        
        if (date1 != null) {
            if(prev_To_date !=null){
                 if(date1.after(prev_To_date) || date1.equals(prev_To_date) ){
                        
                    jTextField6.setText(Formats.TIME.formatValue(date1));
                    jTextField7.setText(null);
                }
                else{
                     JOptionPane.showMessageDialog(this, " Select 'From' time after previous  slot..!", " To ", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                
                jTextField6.setText(Formats.TIME.formatValue(date1));
                jTextField7.setText(null);
                
                
            }
        }
        
        }
        else{
              JOptionPane.showMessageDialog(this, "Please Dont change date", " To ", JOptionPane.ERROR_MESSAGE);  
          }
        
        
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
         Date date1=new Date();
         Date From_Date = null;
       try {
            date1 = (Date) Formats.TIME.parseValue(jTextField6.getText());
            From_Date = (Date) Formats.TIME.parseValue(jTextField6.getText());
       } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, date1);
        if (date1 != null) {
           if(From_Date!=null){
               if(From_Date.before(date1)){
                   
                   
               jTextField7.setText(Formats.TIME.formatValue(date1));
               
               
               
               }
               else{
                   JOptionPane.showMessageDialog(this, " 'To' time Should be after 'From' Time..!!  Please , Select Again..!", " To ", JOptionPane.ERROR_MESSAGE);
                   
               
               }
            }
           else{
                JOptionPane.showMessageDialog(this, " Select 'From' Time First..!", " To ", JOptionPane.ERROR_MESSAGE);
           }
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Date date1=new Date();
        Date prev_To_date = null;
        try {
            prev_To_date = sdf.parse("1970-01-01 00:00:00");
        } catch (ParseException ex) {
            Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(M_fullDay_Slots.size()!=0){
                for(int i=0;i<M_fullDay_Slots.size();i++){
                    String slot = M_fullDay_Slots.get(i).toString();
                    String[] items = slot.split("-");
                    String to = items[1];
                    try {
                        prev_To_date = (Date) Formats.TIME.parseValue(to);
                    } catch (BasicException ex) {
                        Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        
        try {
            date1 = (Date) Formats.TIME.parseValue(jTextField8.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, prev_To_date);
        if (date1 != null) {
           
                jTextField8.setText(Formats.TIME.formatValue(date1));
                jTextField12.setText(null);
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
       Date date1=new Date();
       Date From_Date = null;
       try {
            date1 = (Date) Formats.TIME.parseValue(jTextField8.getText());
            From_Date = (Date) Formats.TIME.parseValue(jTextField8.getText());
        } 
       catch (BasicException e) {
            date1 = null;
        }
          try{
        date1 = JCalendarDialog.showCalendarTimeHours(this, date1);
        if (date1 != null) {
           if(From_Date != null){
               if(From_Date.before(date1)){
                    jTextField12.setText(Formats.TIME.formatValue(date1));
                    
                    
                    
               }
               else{
                    JOptionPane.showMessageDialog(this, " 'To' time Should be after 'From' Time..!!  Please , Select Again..!", " To ", JOptionPane.ERROR_MESSAGE);
               }
           }
           else{
             JOptionPane.showMessageDialog(this, " Select 'From' Time First..!", " To ", JOptionPane.ERROR_MESSAGE);
        }
        }
          }catch(Exception e1){
              e1.printStackTrace();
          } 
    }//GEN-LAST:event_jButton21ActionPerformed
  public javax.swing.ListModel listSlot;
    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
      if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0 ){
          if(jTextField2.getText()!=null && jTextField2.getText().trim().length()>0 ){
          
               Date From_temp  = null;
               Date To_Temp = null;
               String from = jTextField1.getText();
               String to = jTextField2.getText();
                    try {          
                        From_temp = (Date) Formats.TIME.parseValue(jTextField1.getText());
                        To_Temp = (Date) Formats.TIME.parseValue(jTextField2.getText());
                        
                        
                    } catch (BasicException ex) {
                        Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
               if(From_temp.before(To_Temp) && From_temp!=null && To_Temp!=null){
               String slot = from+"-"+to;
               String data = slot;
               Collection<String> timeslots  = new ArrayList<String>();
               timeslots.add(data);
               M_hourly_Slots.addAll(timeslots);
             
             
              M_hourly_Slots_C = new ComboBoxValModel(M_hourly_Slots);
              M_hourl_TimeSlot_Combo.setModel(M_hourly_Slots_C);
              jTextField1.setText(null);
              jTextField2.setText(null);  
                        
              
              
              } 
              else{
                  JOptionPane.showMessageDialog(this, " 'To' time Should be after 'From' Time..!!  Please , Select Again..!", " To ", JOptionPane.ERROR_MESSAGE);
                 
                  jTextField2.setText(null);
                  
              }
               
           
              
          }
          else{
              JOptionPane.showMessageDialog(this, " Select End  Time ", " To ", JOptionPane.ERROR_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, " Select Starting Time   ", " From", JOptionPane.ERROR_MESSAGE);  
      }
  
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        if(jTextField6.getText()!=null && jTextField6.getText().trim().length()>0 ){
          if(jTextField7.getText()!=null && jTextField7.getText().trim().length()>0 ){
          
              String from = jTextField6.getText();
              String to = jTextField7.getText();
              String slot = from+"-"+to;
              String data = slot;
              Collection<String> timeslots  = new ArrayList<String>();
              timeslots.add(data);
              
              M_halfDay_Slots.addAll(timeslots);
            
              
              M_halfDay_Slots_C = new ComboBoxValModel(M_halfDay_Slots);
              M_halfDay_TimeSlot_Combo.setModel(M_halfDay_Slots_C);
              jTextField6.setText(null);
              jTextField7.setText(null);
        }
          else{
              JOptionPane.showMessageDialog(this, " Select End  Time ", " To ", JOptionPane.ERROR_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, " Select Starting Time   ", " From", JOptionPane.ERROR_MESSAGE);  
      }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
      if(jTextField8.getText()!=null && jTextField8.getText().trim().length()>0 ){
          if(jTextField12.getText()!=null && jTextField12.getText().trim().length()>0 ){
          
              String from = jTextField8.getText();
              String to = jTextField12.getText();
              String slot = from+"-"+to;
              String data = slot;
              Collection<String> timeslots  = new ArrayList<String>();
              timeslots.add(data);
              
              M_fullDay_Slots.addAll(timeslots);
            
              
              M_fullDay_Slots_C = new ComboBoxValModel(M_fullDay_Slots);
              M_fullDay_TimeSlot_Combo.setModel(M_fullDay_Slots_C);
              jTextField8.setText(null);
              jTextField12.setText(null);
        }
          else{
              JOptionPane.showMessageDialog(this, " Select End  Time ", " To ", JOptionPane.ERROR_MESSAGE);
           }
      }
      else{
           JOptionPane.showMessageDialog(this, " Select Starting Time   ", " From", JOptionPane.ERROR_MESSAGE);  
      }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void delete_M_hourlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_M_hourlyActionPerformed
     
        if(M_hourl_TimeSlot_Combo.getSelectedIndex()!=-1){
            
          int delete_room = JOptionPane.showConfirmDialog(location_panel, "Do you want to Delete Time Slot ??  ");  
            if(delete_room == JOptionPane.YES_OPTION)
            { 
                    String xyz = M_hourl_TimeSlot_Combo.getSelectedItem().toString();
                      for(int i=0;i<M_hourl_TimeSlot_Combo.getItemCount() ; i++){
                          if(xyz.equals(M_hourly_Slots.get(i))){

                              M_hourly_Slots.remove(i);
                          }
                        }
                     M_hourl_TimeSlot_Combo.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(this, "Time Slot Deleted Successfully .. ! ", "Success", JOptionPane.INFORMATION_MESSAGE);   
            }
          
      }
      
    }//GEN-LAST:event_delete_M_hourlyActionPerformed

    private void delete_M_halfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_M_halfActionPerformed
        if(M_halfDay_TimeSlot_Combo.getSelectedIndex()!=-1){
            
          int delete_room = JOptionPane.showConfirmDialog(location_panel, "Do you want to Delete Time Slot ??  ");  
            if(delete_room == JOptionPane.YES_OPTION)
            { 
                    String xyz = M_halfDay_TimeSlot_Combo.getSelectedItem().toString();
                      for(int i=0;i<M_halfDay_TimeSlot_Combo.getItemCount() ; i++){
                          if(xyz.equals(M_halfDay_Slots.get(i))){

                              M_halfDay_Slots.remove(i);
                          }
                        }
                     M_halfDay_TimeSlot_Combo.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(this, "Time Slot Deleted Successfully .. ! ", "Success", JOptionPane.INFORMATION_MESSAGE);   
            }
          
      }
    }//GEN-LAST:event_delete_M_halfActionPerformed

    private void delete_M_fullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_M_fullActionPerformed
       if(M_fullDay_TimeSlot_Combo.getSelectedIndex()!=-1){
            
          int delete_room = JOptionPane.showConfirmDialog(location_panel, "Do you want to Delete Time Slot ??  ");  
            if(delete_room == JOptionPane.YES_OPTION)
            { 
                    String xyz = M_fullDay_TimeSlot_Combo.getSelectedItem().toString();
                      for(int i=0;i<M_fullDay_TimeSlot_Combo.getItemCount() ; i++){
                          if(xyz.equals(M_fullDay_Slots.get(i))){

                              M_fullDay_Slots.remove(i);
                          }
                        }
                     M_fullDay_TimeSlot_Combo.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(this, "Time Slot Deleted Successfully .. ! ", "Success", JOptionPane.INFORMATION_MESSAGE);   
            }
          
      }
    }//GEN-LAST:event_delete_M_fullActionPerformed

    private void select_more_taxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_more_taxActionPerformed
        
    }//GEN-LAST:event_select_more_taxActionPerformed

    private void select_more_taxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_more_taxItemStateChanged

        if(select_more_tax.isSelected())
        {
            jLabel16.setVisible(true);
            Tax2.setVisible(true);
            

        }
        else{
            jLabel16.setVisible(false);
            Tax2.setVisible(false);
            Tax2.setSelectedIndex(-1);
            jLabel19.setVisible(false);
            Tax3.setVisible(false);
            Tax3.setSelectedIndex(-1);
            select_more_tax1.setSelected(false);
        }

    }//GEN-LAST:event_select_more_taxItemStateChanged

    private void M_halfDay_TimeSlot_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_halfDay_TimeSlot_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_M_halfDay_TimeSlot_ComboActionPerformed

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

    private void Tax3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Tax3ItemStateChanged
      if(Tax3.getSelectedIndex()!=-1){
          
          basic2.setVisible(true);
          cascade2.setVisible(true);
           
      }
      else{
          basic2.setVisible(false);
          cascade2.setVisible(false);
      }
    }//GEN-LAST:event_Tax3ItemStateChanged

    private void payment_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payment_textKeyReleased
         char c = evt.getKeyChar();
    
    if(c!=KeyEvent.VK_BACK_SPACE && c!=KeyEvent.VK_TAB && c!=KeyEvent.VK_ESCAPE && c!=KeyEvent.VK_ENTER && c!=KeyEvent.VK_CONTROL && c!=KeyEvent.VK_SHIFT )
    {
   
    if(!Character.isDigit(c))
    {  
        JOptionPane.showMessageDialog(payment_text, "Please enter only numbers..");
    
            payment_text.setText(null);
         
    }
    }
    }//GEN-LAST:event_payment_textKeyReleased

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        if(jTextField13.getText()!=null && jTextField13.getText().trim().length()>0){
            if(jTextField14.getText()!=null && jTextField14.getText().trim().length()>0){
            int delete_room = JOptionPane.showConfirmDialog(jPanel5, "Do you want to Block hall for specific duration?? " , "Hall Blocking Panel " , JOptionPane.YES_NO_OPTION);


            if(delete_room == JOptionPane.YES_OPTION)
                        {
                              String ID = hall_id.getText();
                              Date FROM_DATE = new Date();
                              Date TO_DATE = new Date();       
                                try {
                                    FROM_DATE = (Date) Formats.DATE.parseValue(jTextField13.getText());
                                    TO_DATE = (Date) Formats.DATE.parseValue(jTextField14.getText());
                                
                                } catch (BasicException ex) {
                                    Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                                }
                             
                            
                               try {
                                int num =  new PreparedSentence(m_App.getSession(), "UPDATE hall_master SET BLOCK_FLAG=1 ,  BLOCK_BY=? , BLOCK_DATE=?, BLOCK_HOST=? , BLOCK_FROM=? , BLOCK_UPTO=? where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP , Datas.STRING ,Datas.TIMESTAMP , Datas.TIMESTAMP ,  Datas.STRING , })).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost() ,FROM_DATE , TO_DATE , ID });
            


                                JOptionPane.showMessageDialog(this, "Hall Blocked Succesfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);                  
                                loaddata();
                                
                                block_panel.setVisible(false);
                                CreateHall_panel.setVisible(true);

                            } catch (BasicException ex) {
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                            }    
                            
                            
                        }
        }
            else{
                 JOptionPane.showMessageDialog(this, " Enter 'To Date' for blocking hall .!!! ");
            }
        }
           
        else{
             JOptionPane.showMessageDialog(this, " Enter 'From Date' for blocking hall .!!! ");
        }
                    
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         Date date=new Date();
        try {
            date = (Date) Formats.DATE.parseValue(jTextField13.getText());
        } catch (BasicException e) {
            date = null;
        }
          try{
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            if(date.after(new Date()))
            {
             
                  jTextField13.setText(Formats.DATE.formatValue(date));
                  jTextField14.setText(null);
            }
            else
            {
                JOptionPane.showMessageDialog(this, " Select Date After Current Date.!!! ");
            }
            
        }
          }catch(Exception e1){
              e1.printStackTrace();
          }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
      if(jTextField13.getText()!=null && jTextField13.getText().trim().length()>0){
         List<Object> Booking_Dates = new ArrayList<Object>();
         
         Date From_Date=new Date();
         Date To_Date = new Date();
        try {
            From_Date = (Date) Formats.DATE.parseValue(jTextField13.getText());
        } 
        catch (BasicException e) {
            From_Date = new Date();
        }
          
        try{
           To_Date = JCalendarDialog.showCalendar(this, From_Date);
        if (To_Date != null) {
            if(To_Date.after(From_Date) || To_Date.equals(From_Date))
            {
                  
                  Booking_Dates = Check_booking_Dates(From_Date , To_Date);
                  if(Booking_Dates.size()<1){ 
                      jTextField14.setText(Formats.DATE.formatValue(To_Date));
                  }
                  else{
                     
                         JOptionPane.showMessageDialog(this, " Sorry Hall Is Booked/Blocked on the below dates   " + Booking_Dates.toString() );
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

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
       block_panel.setVisible(false);
       CreateHall_panel.setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if(jTable1.getSelectedRow()!=-1){
            if(jTable1.getSelectedRow()<hallTable.getHallSize()){
                int row = jTable1.getSelectedRow();
                HallTableInfo block_hall = hallTable.getHallPath().get(row);
           
                hall_label.setText(block_hall.getName());
                capacity_label.setText(""+block_hall.getMax_Cap());
                floor_label.setText(block_hall.getFloor());
                hall_id.setText(block_hall.getId());
                if(block_hall.getBLOCK_FLAG()==1){
                     String Hall_Name = hall_label.getText();
                     
                     Date From_Date = hallTable.getBlock_From_Date(Hall_Name, m_App);
                     Date To_Date =hallTable.getBlock_To_Date(Hall_Name, m_App);
                     
                     block_from_label.setText(Formats.DATE.formatValue(From_Date));
                     block_upto_label.setText(Formats.DATE.formatValue(To_Date));
                     
                     jLabel27.setVisible(true);
                     jLabel34.setVisible(true);
                     block_from_label.setVisible(true);
                     block_upto_label.setVisible(true);
                     jButton24.setVisible(true);
                     
                    }
                    else{
                         jLabel27.setVisible(false);
                         jLabel34.setVisible(false);
                         block_from_label.setVisible(false);
                         block_upto_label.setVisible(false);
                         jButton24.setVisible(false);
                         
                     }
                
                
                
                jTextField13.setText(null);
                jTextField14.setText(null);
                block_panel.setVisible(true);
                CreateHall_panel.setVisible(false);
                
            }
       }
       else{
           
       }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
       int delete_room = JOptionPane.showConfirmDialog(jPanel5, "Do you want to Unblock  hall?? " , "Hall " , JOptionPane.YES_NO_OPTION);
                     if(delete_room == JOptionPane.YES_OPTION)
                        {
                            String ID = hall_id.getText();
                            
                             try {
                                int num =  new PreparedSentence(m_App.getSession(), "UPDATE hall_master SET BLOCK_FLAG=0 ,  UNBLOCK_BY=? , UNBLOCK_DATE=?, UNBLOCK_HOST=?  where ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP , Datas.STRING ,  Datas.STRING  })).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost() , ID });
            


                                JOptionPane.showMessageDialog(this, "Hall Un Blocked Succesfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);                  
                               loaddata();
                                block_panel.setVisible(false);
                                CreateHall_panel.setVisible(true);
                               
                           } catch (BasicException ex) {
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                            
                            
                            
                            
                        }
    }//GEN-LAST:event_jButton24ActionPerformed

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

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
           
            payment_frame.setVisible(true);
            CreateHall_panel.setVisible(false);
           
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
       payment_frame.setVisible(false);
       CreateHall_panel.setVisible(true);
       full_radio_btn.setSelected(true);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
       
      if(full_radio_btn.isSelected()){
          
          Advance_perc = "100-0-0";
          payment_frame.setVisible(false);
          CreateHall_panel.setVisible(true);
          
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
                   CreateHall_panel.setVisible(true);
                }
                else{
                   JOptionPane.showMessageDialog(this, " Summation should be 100 only..!! ", " Images  not saved", JOptionPane.ERROR_MESSAGE); 
                   Chk_in_perc.setText(null);
                   chk_out_perc.setText(null);
                }
                
                
                
            }
            else{
                JOptionPane.showMessageDialog(chk_out_perc, "Enter Check-in Payment Percentage..! " , "Warning" ,JOptionPane.WARNING_MESSAGE );
            }
         }
         else{
              JOptionPane.showMessageDialog(chk_out_perc, "Enter Advance Payment Percentage..! " , "Warning" ,JOptionPane.WARNING_MESSAGE );
         }
      }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
       if(jTabbedPane1.getSelectedIndex()==0){
           reset();
       }
    }//GEN-LAST:event_jTabbedPane1StateChanged
         
        
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
                    Tax2.setModel(stax2);
                }
    }
    
    
      public void serviceTax2()
      {
         Collection<TaxCategoryInfo> taxthree = new ArrayList<TaxCategoryInfo>(); 
          taxthree = staxlist ; 
          List taxListThree = new ArrayList();
          taxListThree.addAll(taxthree);
          if(luxuryTax.getSelectedIndex()!=-1 && Tax2.getSelectedIndex()!=-1){
              TaxCategoryInfo tci1 = (TaxCategoryInfo) luxuryTax.getSelectedItem();
              TaxCategoryInfo tci2 = (TaxCategoryInfo) Tax2.getSelectedItem();
              taxListThree.remove(tci1);
              taxListThree.remove(tci2);
              stax3 = new ComboBoxValModel(taxListThree);
              Tax3.setModel(stax3);
              
          }
          
      }  
        
        
      
     public void loaddata() throws BasicException
    {
        try {
            FE = FloorEditor.loadInstanceFloorInfo(m_App);
        } catch (BasicException ex) {
            Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        data = (List<FloorTableInfo>) FE.getFloorInfo();
        if(data==null){
             data = new ArrayList<FloorTableInfo>();
        }
     
        
            
            
            // for tax combobox
            staxlist = m_dlSales.getTaxCategoriesList().list();
            staxlist.add(0, null);
            staxmodel = new ComboBoxValModel(staxlist);
            luxuryTax.setModel(staxmodel);
            
            
             FloorListModel   = new ComboBoxValModel(data);
             FloorCombo.setModel(FloorListModel);
            basic1.setSelected(true);
     
            //load hall table details
            
            if(showAll.isSelected()==true){
            
                hallTable = hallTableModel.loadInstanceHallInfo_ShowAll(m_App);
                showPanelInfo(hallTable);
            
            }
            else{
                hallTable = hallTableModel.loadInstanceHallInfo(m_App);
                showPanelInfo(hallTable);
                
            }
            
            
            // for time Slots
            
   
        acclist=dlfac.getaccounts();
        sacclist=new ArrayList<AccountMasterExt>();
        sacclist.addAll(acclist);
        Adv_acount_Model=new ComboBoxValModel(acclist);
        Adv_acount_combo.setModel(Adv_acount_Model);
        reve_accnt_Model=new ComboBoxValModel(sacclist);
        reve_accnt_combo.setModel(reve_accnt_Model);
        Cancel_accnt_model = new ComboBoxValModel(sacclist);
        cancellation_accnt_combo.setModel(Cancel_accnt_model);
            
            
     
     
        
    }
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adv_Perc;
    private javax.swing.JComboBox Adv_acount_combo;
    private javax.swing.JLabel BackImageLabel;
    private javax.swing.JTextField Chk_in_perc;
    private javax.swing.JPanel CreateHall_panel;
    private javax.swing.JTextArea Facility;
    private javax.swing.JComboBox FloorCombo;
    private javax.swing.JButton From;
    private javax.swing.JPanel ImagePanel;
    private javax.swing.JComboBox M_fullDay_TimeSlot_Combo;
    private javax.swing.JComboBox M_halfDay_TimeSlot_Combo;
    private javax.swing.JComboBox M_hourl_TimeSlot_Combo;
    private javax.swing.JComboBox Tax2;
    private javax.swing.JComboBox Tax3;
    private javax.swing.JTextField X1;
    private javax.swing.JTextField Y1;
    private javax.swing.JTextField advnceBooking_dura;
    private javax.swing.JRadioButton basic1;
    private javax.swing.JRadioButton basic2;
    private javax.swing.JLabel block_from_label;
    private javax.swing.JPanel block_panel;
    private javax.swing.JLabel block_upto_label;
    private javax.swing.JComboBox cancellation_accnt_combo;
    private javax.swing.JLabel capacity_label;
    private javax.swing.JRadioButton cascade1;
    private javax.swing.JRadioButton cascade2;
    private javax.swing.JPanel charges_panel;
    private javax.swing.JTextField chk_out_perc;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete_M_full;
    private javax.swing.JButton delete_M_half;
    private javax.swing.JButton delete_M_hourly;
    private javax.swing.JButton edit;
    private javax.swing.JLabel floor_label;
    private javax.swing.JLabel forNonMembers;
    private javax.swing.JLabel forNonMembers1;
    private javax.swing.JRadioButton full_radio_btn;
    private javax.swing.JButton hall_ic1;
    private javax.swing.JButton hall_ic2;
    private javax.swing.JButton hall_ic3;
    private javax.swing.JButton hall_ic4;
    private javax.swing.JLabel hall_id;
    private javax.swing.JLabel hall_label;
    private javax.swing.JLabel id;
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JFrame jFrame5;
    private javax.swing.JFrame jFrame6;
    private javax.swing.JFrame jFrame7;
    private javax.swing.JFrame jFrame8;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel location_panel;
    private javax.swing.JComboBox luxuryTax;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private com.openbravo.data.gui.JImageEditor m_jImage1;
    private com.openbravo.data.gui.JImageEditor m_jImage2;
    private javax.swing.JTextField max_Capacity;
    private javax.swing.JLabel message;
    private javax.swing.JTextField name;
    private javax.swing.JRadioButton partial_radio_btn;
    private javax.swing.JPanel partial_variation_panel;
    private javax.swing.JPanel payment_frame;
    private javax.swing.JTextField payment_text;
    private javax.swing.JLabel rate1;
    private javax.swing.JLabel rate2;
    private javax.swing.JLabel rate3;
    private javax.swing.JLabel rate4;
    private javax.swing.JLabel rate5;
    private javax.swing.JLabel rate6;
    private javax.swing.JComboBox reve_accnt_combo;
    private javax.swing.JButton save;
    private javax.swing.JButton saveChanges;
    private javax.swing.JCheckBox select_more_tax;
    private javax.swing.JCheckBox select_more_tax1;
    private javax.swing.JCheckBox showAll;
    private javax.swing.JScrollPane table_scrolPnne;
    private javax.swing.JButton temp_icon;
    private javax.swing.JLabel timing_label;
    private javax.swing.JPanel timing_panel;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "";
    }

    public void activate() throws BasicException {
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
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate") ;
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
       
       buttonGoup();
    }

    public Object getBean() {
       return this;
    }
 
    
    
    public void reset()
    {
        name.setText(null);
                   max_Capacity.setText(null);
                    jTextField3.setText(null);
                    jTextField4.setText(null);
                    jTextField5.setText(null);
                    jTextField9.setText(null);
                    jTextField10.setText(null);
                    jTextField9.setText(null);
                    jTextField10.setText(null);
                    jTextField11.setText(null);
                    jTextField1.setText(null);
                    jTextField2.setText(null);
                    jTextField6.setText(null);
                    jTextField7.setText(null);
                    jTextField8.setText(null);
                    jTextField12.setText(null); 
                    advnceBooking_dura.setText(null);
                    jTextField11.setText(null);
                    Facility.setText(null);
                    jFrame1.setVisible(false);
                    edit.setEnabled(false);
                    delete.setEnabled(false);
                    saveChanges.setVisible(false);
                    save.setVisible(true);
                    timing_label.setVisible(false);
                    forNonMembers.setVisible(false);
                    forNonMembers.setVisible(false);
                    select_more_tax.setSelected(false);
                    FloorCombo.setSelectedIndex(-1);
                    luxuryTax.setSelectedIndex(-1);
                    Tax2.setSelectedIndex(-1);
                    Tax3.setSelectedIndex(-1);
                    jLabel16.setVisible(false);
                    Tax2.setVisible(false);
                    jLabel19.setVisible(false);
                    Tax3.setVisible(false);
                    select_more_tax.setVisible(false);
                    select_more_tax1.setVisible(false);
                    count = 0;
                    showAll.setSelected(false);
                    
                     m_jImage.setImage(null);
                     m_jImage1.setImage(null);
                     m_jImage2.setImage(null);
                     
                     payment_text.setText(null);
                     M_hourly_Slots = new ArrayList<String>();
                     M_halfDay_Slots = new ArrayList<String>();
                     M_fullDay_Slots = new ArrayList<String>();
                   
                     basic2.setVisible(false);
                     cascade2.setVisible(false);
                     basic1.setVisible(false);
                     cascade1.setVisible(false);
                     CreateHall_panel.setVisible(true);
                   
                     jTabbedPane1.setSelectedIndex(0);
              
                     M_hourly_Slots_C = new ComboBoxValModel();
                     M_hourl_TimeSlot_Combo.setModel(M_hourly_Slots_C);
                     M_halfDay_Slots_C = new ComboBoxValModel();
                     M_halfDay_TimeSlot_Combo.setModel(M_halfDay_Slots_C);
                   
                     M_fullDay_Slots_C = new ComboBoxValModel();
                     M_fullDay_TimeSlot_Combo.setModel(M_fullDay_Slots_C);
                    
               
                     block_panel.setVisible(false);
                
                     payment_frame.setVisible(false);
                     full_radio_btn.setSelected(true);
                     Adv_Perc.setText(null);
                     Chk_in_perc.setText(null);
                     chk_out_perc.setText(null);
                    
                    Adv_acount_combo.setSelectedIndex(-1);
                    reve_accnt_combo.setSelectedIndex(-1);
                    cancellation_accnt_combo.setSelectedIndex(-1);
                    ImagePanel.setVisible(false);
                    timing_panel.setVisible(false);
                    charges_panel.setVisible(false);
                    location_panel.setVisible(false);
                    block_panel.setVisible(false);
                    payment_frame.setVisible(false);
    }
    

    
    private void buttonGoup() {
       ButtonGroup bg1 = new ButtonGroup();
        bg1.add(basic1);
        bg1.add(cascade1);
       ButtonGroup bg2 = new ButtonGroup();
        bg2.add(basic2);
        bg2.add(cascade2);
      ButtonGroup bg3 = new ButtonGroup();
        bg3.add(full_radio_btn);
        bg3.add(partial_radio_btn);
        
    }
    
    public void showPanelInfo(hallTableModel hallTable){
        jTable1.setModel(hallTable.getTableModel());
        
      //  jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
       
    setsize();
        
    }
    
    
     public void setsize(){
        
         jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
         jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(150);
       
    }
    
    
    private void privateSetEnabled(boolean enabled) {
       
    }
    
    public List Check_booking_Dates(Date FromDate , Date ToDate){
        
        String Hall_Name = hall_label.getText();
        String Hall_ID = hall_id.getText();
        List<Object> BookedDates = new ArrayList<Object>();
        List<Object> ClashingDates  = new ArrayList<Object>();
        
        
         int No_of_days  = (int) (ToDate.getTime() - FromDate.getTime())/(1000 * 60 * 60 * 24);
         No_of_days++;
         
         BookedDates = hallTable.CheckAvailibility(Hall_ID, m_App);
         Collection t = BookedDates;
         HashSet<Object> h = new HashSet<Object>();
         h.addAll(t); 
         BookedDates.clear();
         BookedDates.addAll(h);
          
          
         for(int i=0;i<No_of_days ; i++){
             
             
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(FromDate.getTime());
            c.add(Calendar.DATE, i);
            Date NextDay = c.getTime();
             
                    for(int j=0; j<BookedDates.size() ; j++){
                      Date Booked_Date = new Date();
                     
                      try {
                            
                               Booked_Date = (Date) Formats.DATE.parseValue(BookedDates.get(j).toString());
                            } catch (BasicException ex) {
                                Logger.getLogger(HallBookingMaster.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        if(Booked_Date.equals(NextDay)){

                            ClashingDates.add(BookedDates.get(j));
                        }

                    }
            
         }
         
        return ClashingDates;
    }
    
    
    
    
}

   
   
