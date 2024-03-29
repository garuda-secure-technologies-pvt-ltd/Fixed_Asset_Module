/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.LocalRes;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.FixedAssetRegistration.MaintenanceTableModel.MaintenanceInfo;
import com.openbravo.pos.FixedAssetRegistration.FixedAssetTableModel.FixedAssetInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import com.openbravo.pos.Accounts.AccountTable;
import com.openbravo.pos.Accounts.waitDialog;
import com.openbravo.pos.util.ComboBoxItem;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.ComboBoxModel;


/**
 *
 * @author dev3
 */
public class FixedAssetLocation extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private AppConfig config;
    private DirtyManager dirty = new DirtyManager();
    private static DataLogicFacilities dmang;
    private int flagkey;
    private String deac_id;
    private FALocationsTableModel fxd_table;
    private List<String> namelist = new ArrayList<String>();
    private List<LocationBean> buildingList = new ArrayList<LocationBean>();
    private List<LocationBean> blockList = new ArrayList<LocationBean>();
    private List<LocationBean> blockListofBuilding = new ArrayList<LocationBean>();
    private List<LocationBean> floorListofBlock = new ArrayList<LocationBean>();
    private List<LocationBean> floorList = new ArrayList<LocationBean>();
    //private List<String> buildingListNames = new ArrayList<String>();
    //private List<String> blockListNames = new ArrayList<String>();
    //private List<String> blockListofBuildingNames = new ArrayList<String>();
    //private List<String> floorListofBlockNames = new ArrayList<String>();
    //private List<String> floorListNames = new ArrayList<String>();
    private ComboBoxValModel buildingModel;
    private ComboBoxValModel buildingModel_Location;
    private ComboBoxValModel buildingModel_Blocks;
    private ComboBoxValModel buildingModel_Floors;
    private ComboBoxValModel blockModel;
    private ComboBoxValModel floorModel;
    
     private final ComboBoxItem[] floorListComboItems = new ComboBoxItem[100];
     
     
    private waitDialog w;
    /**
     * Creates new form FixedAsset2
     */
    public FixedAssetLocation() {
        initComponents();
        initDialog();
        savebutt.setVisible(true);

    }
    
    public void initDialog()
    {
        w = new waitDialog(new JFrame(), true);
        int h = w.getSize().height;
        int w1 = w.getSize().width;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        w.setLocation(scrnsize.width / 2 - w1, scrnsize.height / 2 - h);   
    }

    public boolean hasChanged() {
        return dirty.isDirty();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        msgdlg2 = new javax.swing.JLabel();
        msgdlg4 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox_building_locations = new javax.swing.JComboBox();
        jComboBox_Block_locations = new javax.swing.JComboBox();
        jComboBox_floor_locations = new javax.swing.JComboBox();
        savebutt = new javax.swing.JButton();
        savecha_but = new javax.swing.JButton();
        reset_but = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        edit_but = new javax.swing.JButton();
        removeButt = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox_Building = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField_building = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox_blocks = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jComboBox_building_Blocks = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox_floor = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jComboBox_block_floors = new javax.swing.JComboBox();
        jComboBox_building_floors = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);

        jLabel1.setText(" Select Building");

        jLabel2.setText("Name");

        jLabel3.setText("Select Block");

        jLabel4.setText("Select Floor");

        msgdlg2.setForeground(java.awt.Color.red);
        msgdlg2.setText("Capitalisation Date Should be (Greater than)  Installation Date,Commission date and  (Greater than or Equal to) Put To Use Date");

        msgdlg4.setForeground(java.awt.Color.red);
        msgdlg4.setText("Installation Date Should be Greater than or Equal to Purchase Date");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox_building_locations.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_building_locations.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_building_locationsItemStateChanged(evt);
            }
        });
        jComboBox_building_locations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_building_locationsActionPerformed(evt);
            }
        });

        jComboBox_Block_locations.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_Block_locations.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_Block_locationsItemStateChanged(evt);
            }
        });
        jComboBox_Block_locations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Block_locationsActionPerformed(evt);
            }
        });

        jComboBox_floor_locations.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_floor_locations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_floor_locationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(msgdlg2, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(msgdlg4, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(814, 814, 814)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(153, 153, 153)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameText)
                                    .addComponent(jComboBox_building_locations, 0, 257, Short.MAX_VALUE)
                                    .addComponent(jComboBox_Block_locations, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jComboBox_floor_locations, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_building_locations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox_Block_locations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jComboBox_floor_locations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(773, 773, 773)
                .addComponent(msgdlg2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msgdlg4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(720, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        savebutt.setText("Save");
        savebutt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttActionPerformed(evt);
            }
        });

        savecha_but.setText("Save Changes");
        savecha_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savecha_butActionPerformed(evt);
            }
        });

        reset_but.setText("Reset");
        reset_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_butActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(reset_but, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(221, 221, 221)
                        .addComponent(savebutt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(savecha_but)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_but)
                    .addComponent(savebutt)
                    .addComponent(savecha_but))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create New Location", jPanel1);

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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        edit_but.setText("Edit");
        edit_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_butActionPerformed(evt);
            }
        });

        removeButt.setText("Remove");
        removeButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(462, Short.MAX_VALUE)
                .addComponent(edit_but, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(removeButt)
                .addGap(259, 259, 259))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(425, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit_but)
                    .addComponent(removeButt))
                .addGap(46, 46, 46))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 114, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("View List", jPanel5);

        jLabel5.setText("Building Name:");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox_Building.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel6.setText("Existing Building  Names");

        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_Building, 0, 219, Short.MAX_VALUE)
                    .addComponent(jTextField_building))
                .addGap(128, 128, 128)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_building, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Building, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton4))
                .addContainerGap(330, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create New Building", jPanel4);

        jLabel9.setText("Block Name :");

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox_blocks.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_blocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_blocksActionPerformed(evt);
            }
        });

        jLabel10.setText("Existing Block Names:");

        jButton6.setText("Remove");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jComboBox_building_Blocks.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_building_Blocks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_building_BlocksItemStateChanged(evt);
            }
        });
        jComboBox_building_Blocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_building_BlocksActionPerformed(evt);
            }
        });

        jLabel11.setText("Select Building");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_building_Blocks, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jComboBox_blocks, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_building_Blocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jButton3))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_blocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton6))
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create New Block", jPanel8);

        jLabel7.setText("Floor Name:");

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Existing Floor Names :");

        jComboBox_floor.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_floor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_floorActionPerformed(evt);
            }
        });

        jButton5.setText("Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox_block_floors.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_block_floors.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_block_floorsItemStateChanged(evt);
            }
        });

        jComboBox_building_floors.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBox_building_floors.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_building_floorsItemStateChanged(evt);
            }
        });
        jComboBox_building_floors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_building_floorsActionPerformed(evt);
            }
        });

        jLabel12.setText("Select Block");

        jLabel13.setText("Select Building");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_building_floors, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_block_floors, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(jComboBox_floor, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addGap(297, 297, 297))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_building_floors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox_block_floors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_floor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(304, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create New Floor", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void edit_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_butActionPerformed

        if (jTable1.getSelectedRow() != -1) {
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Location ?? ", "Editing Menu", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {
                
                if (jTable1.getSelectedRow() < fxd_table.getSize()) {

                    Thread t = new Thread(new Runnable() {

                        public void run() {
                            try {

                                int row = jTable1.getSelectedRow();
                                int rowcount = jTable1.getRowCount();
                                FALocationsTableModel.FALocationsInfo showdata = fxd_table.getList().get(row);
                                //jPanel1.setVisible(true);
                                //savebutt.setVisible(false);
                                //savecha_but.setVisible(true);
                                nameText.setText(showdata.getName());

                               for (LocationBean bean : buildingList) {
                                    if(bean.getId().equals(showdata.getBuilding())){
                                        jComboBox_building_locations.setSelectedItem(bean);
                                        break;
                                    }
                                }
                               
                                blockListofBuilding = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=? AND ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(showdata.getBuilding());
                                jComboBox_Block_locations.setModel(new ComboBoxValModel(blockListofBuilding));
                                
                                for(LocationBean bean : blockListofBuilding) {
                                    if(bean.getId().equals(showdata.getBlock())){
                                        jComboBox_Block_locations.setSelectedItem(bean);
                                        break;
                                    }
                                }

                                floorListofBlock = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1  AND BLOCK=?   ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(showdata.getBlock());
                                jComboBox_floor_locations.setModel(new ComboBoxValModel(floorListofBlock));
                                for (LocationBean bean : floorListofBlock) {
                                    if (bean.getId().equals(showdata.getFloor())) {
                                        jComboBox_floor_locations.setSelectedItem(bean);
                                        break;
                                    }
                                }
                                savecha_but.setVisible(true);
                                savebutt.setVisible(false);
                                jTabbedPane1.setSelectedIndex(0);
                                jPanel1.setVisible(true);
                                w.hideDialog();

                            } catch (BasicException ex) {
                                w.hideDialog();
                                Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                            }catch (Exception ex) {
                                w.hideDialog();
                                Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    try {
                        t.start();
                        if (!w.isShowing()) {
                            w.showDialog("Please wait. Collecting data.");
                        }

                    } catch (Exception ex) {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

                    }

                }
            }
        }

    }//GEN-LAST:event_edit_butActionPerformed

    private void savecha_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savecha_butActionPerformed
        if (jTable1.getSelectedRow() != -1) {

            try {
                if (nameText.getText() != null && nameText.getText().trim().length() > 0) {
                    
                    Thread t = new Thread(new Runnable() {

                        public void run() {
                            try {

                                Transaction t = new Transaction(m_App.getSession()) {
                                    @Override
                                    protected Object transact() throws BasicException {
                                        int row = jTable1.getSelectedRow();
                                        FALocationsTableModel.FALocationsInfo showdata = fxd_table.getList().get(row);
                                        String id = showdata.getId();
                                        String name = nameText.getText();
                                        String floorId = floorList.get(jComboBox_floor_locations.getSelectedIndex()).getId();
                                        String buildingId = buildingList.get(jComboBox_building_locations.getSelectedIndex()).getId();
                                        String blockId = blockList.get(jComboBox_Block_locations.getSelectedIndex()).getId();
                                        Object[] param = new Object[]{name, floorId, buildingId, blockId, id};
                                        new PreparedSentence(m_App.getSession(), "UPDATE FA_LOCATIONS SET NAME=?,FLOOR=?,BUILDING=?,BLOCK=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(param);
                                        flagkey = 1;
                                        return null;
                                    }
                                };
                                t.execute();
                                if (flagkey == 1) {
                                    w.hideDialog();
                                    JOptionPane.showMessageDialog(FixedAssetLocation.this, "Location Editted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    loaddata();

                                    jTabbedPane1.setSelectedIndex(1);
                                    jPanel7.setVisible(true);
                                }
                                w.hideDialog();

                            } catch (BasicException ex) {
                                w.hideDialog();
                                Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    try {
                        t.start();
                        if (!w.isShowing()) {
                            w.showDialog("Please wait. Saving data");
                        }

                    } catch (Exception ex) {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure that Name is not empty", null, JOptionPane.OK_OPTION);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_savecha_butActionPerformed

    private void savebuttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttActionPerformed

        try {
            if (nameText.getText() != null && nameText.getText().trim().length() > 0) {
                if (jComboBox_building_locations.getSelectedIndex() != -1) {
                    if (jComboBox_Block_locations.getSelectedIndex() != -1) {
                        if (jComboBox_floor_locations.getSelectedIndex() != -1) {
                            
                            Thread t = new Thread(new Runnable() {

                                public void run() {
                                    try {
                                        Transaction t = new Transaction(m_App.getSession()) {
                                            @Override
                                            protected Object transact() throws BasicException {
                                                String id = UUID.randomUUID().toString();
                                                String name = nameText.getText();
                                                
                                                LocationBean floorBean = (LocationBean) jComboBox_floor_locations.getModel().getSelectedItem();
                                                String floorId = floorBean.getId();
                                                
                                                LocationBean buildingBean = (LocationBean) jComboBox_building_locations.getModel().getSelectedItem();
                                                String buildingId = buildingBean.getId();
                                                
                                                LocationBean blockBean = (LocationBean) jComboBox_Block_locations.getModel().getSelectedItem();
                                                String blockId = blockBean.getId();

                                                Object[] param = new Object[]{id, "lo", name, floorId, buildingId, blockId};
                                                new PreparedSentence(m_App.getSession(), "INSERT INTO FA_LOCATIONS(ID,TYPE,NAME,FLOOR,BUILDING,BLOCK) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(param);
                                                flagkey = 1;
                                                return null;
                                            }
                                        };
                                        t.execute();
                                        if (flagkey == 1) {
                                            w.hideDialog();
                                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "Location Added Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                            loaddata();
                                        } else {
                                             w.hideDialog();
                                        }

                                        w.hideDialog();

                                    } catch (BasicException ex) {
                                        w.hideDialog();
                                        Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                            try {
                                t.start();
                                if (!w.isShowing()) {
                                    w.showDialog("Please wait. Saving data");
                                }

                            } catch (Exception ex) {
                                w.hideDialog();
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

                            }
                            
                            
                            
                            
                            
                            
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that Floor is selected", null, JOptionPane.OK_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Block is selected", null, JOptionPane.OK_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure that Building is selected", null, JOptionPane.OK_OPTION);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please ensure that Name is not empty", null, JOptionPane.OK_OPTION);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }//GEN-LAST:event_savebuttActionPerformed

    private void reset_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_butActionPerformed
        reset();
    }//GEN-LAST:event_reset_butActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
           if(jTabbedPane1.getSelectedIndex() != 0) {
               jComboBox_building_locations.setSelectedIndex(-1);
               nameText.setText("");
               jComboBox_Block_locations.setSelectedIndex(-1);
               jComboBox_floor_locations.setSelectedIndex(-1);
               savecha_but.setVisible(false);
               savebutt.setVisible(true);
               reset_but.setVisible(true);
            }

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void removeButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() != -1) {
            List<String> tempList = new ArrayList<String>();
            try {
                int row1 = jTable1.getSelectedRow();

                FALocationsTableModel.FALocationsInfo showdata1 = fxd_table.getList().get(row1);

                tempList = new ArrayList<String>(new StaticSentence(m_App.getSession(), "SELECT LOCATION FROM  FA_PHYSICALVERIFICATION ", null, SerializerReadString.INSTANCE).list());

                if (!tempList.contains(showdata1.getId())) {

                    try {
                        Transaction t = new Transaction(m_App.getSession()) {
                            @Override
                            protected Object transact() throws BasicException {
                                int row = jTable1.getSelectedRow();

                                FALocationsTableModel.FALocationsInfo showdata = fxd_table.getList().get(row);
                                String id = showdata.getId();

                                Object[] param = new Object[]{id};
                                new PreparedSentence(m_App.getSession(), "DELETE FROM FA_LOCATIONS WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(param);

                                flagkey = 1;
                                return null;

                            }
                        };
                        t.execute();
                        if (flagkey == 1) {
                            flagkey = 0;
                            JOptionPane.showMessageDialog(this, "Location Deleted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            loaddata();
                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Can not delete ....! This Location is assigned to Asset..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_removeButtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // # ADDED BY AKASH

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {

                    String buildingId = UUID.randomUUID().toString();
                    String buildingName = jTextField_building.getText().trim();

                    Object blockListofBuilding = new PreparedSentence(m_App.getSession(),
                            "SELECT ID FROM  FA_LOCATIONS WHERE TYPE='bl' AND ACTIVE=1 AND NAME=?",
                            SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(buildingName);
                    if (blockListofBuilding == null) {
                        int row_inserted = new PreparedSentence(m_App.getSession(),
                                "INSERT INTO FA_LOCATIONS (ID,TYPE,NAME) VALUES (?,?,?)",
                                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}))
                                .exec(new Object[]{buildingId, "bl", buildingName});

                        if (row_inserted > 0) {
                            locationTables();
                            jComboBox_Building.setModel(new ComboBoxValModel(buildingList));
                            jComboBox_building_locations.setModel(new ComboBoxValModel(buildingList));
                            
                            jComboBox_building_floors.setModel(new ComboBoxValModel(buildingList));
                            jComboBox_building_Blocks.setModel(new ComboBoxValModel(buildingList));
                            
                            jTextField_building.setText(null);
                            w.hideDialog();
                            loaddata();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "Added Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            w.hideDialog();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "Saving Data failed. Please tru again", "Processed Failed", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, "Duplicate entry. Please try with different name", "Duplicate entry", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (BasicException ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        try {
            t.start();
            if (!w.isShowing()) {
                w.showDialog("Please wait. Saving data");
            }

        } catch (Exception ex) {
            w.hideDialog();
            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    String blockId = UUID.randomUUID().toString();
                    String blockName = jTextField3.getText().trim();
                    if (jComboBox_building_Blocks.getSelectedIndex() != -1) {
                        String buildingId = buildingList.get(jComboBox_building_Blocks.getSelectedIndex()).getId();

                        int row_insertted = new PreparedSentence(m_App.getSession(), "INSERT INTO FA_LOCATIONS(ID,TYPE,NAME,BUILDING) VALUES (?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{blockId, "bk", blockName, buildingId});
                        if (row_insertted > 0) {
                            
                            locationTables();
                            jComboBox_blocks.setModel(new ComboBoxValModel(blockList));
                            jComboBox_Block_locations.setModel(new ComboBoxValModel(blockList));
                            jTextField3.setText(null);
                            jComboBox_building_Blocks.setSelectedIndex(-1);
                            w.hideDialog();
                            loaddata();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "Added Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            w.hideDialog();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "Data not saved. Please try again", "Exception", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, "Please ensure that Building is selected", null, JOptionPane.OK_OPTION);
                    }
                    w.hideDialog();

                } catch (BasicException ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }catch (Exception ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        try {
            t.start();
            if (!w.isShowing()) {
                w.showDialog("Please wait. Saving data");
            }

        } catch (Exception ex) {
            w.hideDialog();
            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

        }

        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //  ## EDITED BY AKASH THREAD ADDED
            Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    String floorId = UUID.randomUUID().toString();
                    String floorName = jTextField2.getText();
                    String buildingId = buildingList.get(jComboBox_building_floors.getSelectedIndex()).getId();
                    String blockId = blockListofBuilding.get(jComboBox_block_floors.getSelectedIndex()).getId();
                    int row_inserted = new PreparedSentence(m_App.getSession(), "INSERT INTO FA_LOCATIONS(ID,TYPE,NAME,BUILDING,BLOCK) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{floorId, "fl", floorName, buildingId, blockId});
                    
                    if (row_inserted > 0) {
                        locationTables();
                        jComboBox_floor.setModel(new ComboBoxValModel(floorList));
                        jComboBox_floor_locations.setModel(new ComboBoxValModel(floorList));
                        jTextField2.setText(null);
                        jComboBox_building_floors.setSelectedIndex(-1);
                        jComboBox_block_floors.setSelectedIndex(-1);
                        w.hideDialog();
                        loaddata();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, "Added Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, "Error while saving data", "Exception", JOptionPane.ERROR_MESSAGE);
                    }

                    w.hideDialog();

                } catch (BasicException ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        try {
            t.start();
            if (!w.isShowing()) {
                w.showDialog("Please wait. Saving data");
            }

        } catch (Exception ex) {
            w.hideDialog();
            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {

                    int count = 0;
                    for (int x = 0; x < fxd_table.getSize(); x++) {
                        if (fxd_table.getList().get(x).getBuilding().equals(buildingList.get(jComboBox_Building.getSelectedIndex()).getId())) {
                            count++;
                            break;
                        }
                    }
                    if (count == 0) {
                        blockList = new ArrayList<LocationBean>(new StaticSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND BUILDING=? AND ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(buildingList.get(jComboBox_Building.getSelectedIndex()).getId()));
                        if (blockList.size() > 0) {
                            w.hideDialog();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "Please first remove blocks under this Building has..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            int row1 = new PreparedSentence(m_App.getSession(), "DELETE FROM  FA_LOCATIONS WHERE ID=?  ",
                                    new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{buildingList.get(jComboBox_Building.getSelectedIndex()).getId()});

                            if (row1 > 0) {
                                
                                locationTables();
                                jComboBox_Building.setModel(new ComboBoxValModel(buildingList));
                                jComboBox_building_locations.setModel(new ComboBoxValModel(buildingList));
                                jComboBox_building_locations.setSelectedIndex(-1);
                                
                                jComboBox_building_Blocks.setModel(new ComboBoxValModel(buildingList));
                                jComboBox_building_Blocks.setSelectedIndex(-1);
                                
                                jComboBox_building_floors.setModel(new ComboBoxValModel(buildingList));
                                jComboBox_building_floors.setSelectedIndex(-1);
                                
                                w.hideDialog();
                                loaddata();
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, "Removed Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else {
                                w.hideDialog();
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, "Data not removed. Please try again", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } else {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, "This Building is part of location. First remove the location ..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }

                    w.hideDialog();

                } catch (BasicException ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        try {
            t.start();
            if (!w.isShowing()) {
                w.showDialog("Processing request. Please wait..");
            }

        } catch (Exception ex) {
            w.hideDialog();
            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // ## EDITED BY AKASH

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    int count = 0;
                    if (jComboBox_building_Blocks.getSelectedIndex() != -1) {

                        for (int x = 0; x < fxd_table.getSize(); x++) {
                            if (fxd_table.getList().get(x).getBlock().equals(blockListofBuilding.get(jComboBox_blocks.getSelectedIndex()).getId())) {
                                count++;
                                break;
                            }
                        }
                        if (count == 0) {
                            floorList = new ArrayList<LocationBean>(new StaticSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='FL' AND BLOCK=? AND ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(blockList.get(jComboBox_blocks.getSelectedIndex()).getId()));
                            if (floorList.size() > 0) {
                                w.hideDialog();
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, "Please first remove floors under this Building ..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                int row1 = new PreparedSentence(m_App.getSession(), "DELETE FROM  FA_LOCATIONS WHERE ID=?  ",
                                        new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{blockList.get(jComboBox_blocks.getSelectedIndex()).getId()});

                                if (row1 > 0) {
                                    locationTables();
                                    jComboBox_Block_locations.setModel(new ComboBoxValModel(blockList));
                                    jComboBox_blocks.setModel(new ComboBoxValModel(blockList));
                                    jTextField3.setText(null);
                                    jComboBox_building_Blocks.setSelectedIndex(-1);
                                    jComboBox_blocks.setSelectedIndex(-1);
                                    w.hideDialog();
                                    loaddata();
                                    JOptionPane.showMessageDialog(FixedAssetLocation.this, "Removed Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    w.hideDialog();
                                    JOptionPane.showMessageDialog(FixedAssetLocation.this, "Some issue while removing data", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            w.hideDialog();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, "This Block is part of location. First remove the location ..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, "Select Building first.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    w.hideDialog();

                } catch (BasicException ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        try {
            t.start();
            if (!w.isShowing()) {
                w.showDialog("Please wait. Processing request.");
            }

        } catch (Exception ex) {
            w.hideDialog();
            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        if (jComboBox_building_floors.getSelectedIndex() != -1) {
            if (jComboBox_block_floors.getSelectedIndex() != -1) {

                Thread t = new Thread(new Runnable() {

                    public void run() {
                        try {
                            int count = 0;
                            for (int x = 0; x < fxd_table.getSize(); x++) {

                                if (fxd_table.getList().get(x).getFloor().equals(floorListofBlock.get(jComboBox_floor.getSelectedIndex()).getId())) {
                                    count++;
                                    break;
                                }
                            }
                            if (count == 0) {
                                int row1 = new PreparedSentence(m_App.getSession(), "DELETE FROM  FA_LOCATIONS WHERE ID=?  ",
                                        new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{floorList.get(jComboBox_floor.getSelectedIndex()).getId()});

                                if (row1 > 0) {
                                    locationTables();
                                    jComboBox_floor.setModel(new ComboBoxValModel(floorList));
                                    jComboBox_floor_locations.setModel(new ComboBoxValModel(floorList));
                                    jTextField2.setText(null);
                                    jComboBox_building_floors.setSelectedIndex(-1);
                                    jComboBox_block_floors.setSelectedIndex(-1);
                                    w.hideDialog();
                                    loaddata();
                                    JOptionPane.showMessageDialog(FixedAssetLocation.this, "Removed Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    
                                } else {
                                    w.hideDialog();
                                    JOptionPane.showMessageDialog(FixedAssetLocation.this, "Error while processing request", "Error", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                w.hideDialog();
                                JOptionPane.showMessageDialog(FixedAssetLocation.this, "This floor is part of location. First remove the location ..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            w.hideDialog();

                        } catch (BasicException ex) {
                            w.hideDialog();
                            Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            w.hideDialog();
                            Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                try {
                    t.start();
                    if (!w.isShowing()) {
                        w.showDialog("Please wait. Saving data");
                    }

                } catch (Exception ex) {
                    w.hideDialog();
                    JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select Block first.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select Building first.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox_building_floorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_building_floorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_building_floorsActionPerformed

    private void jComboBox_building_floorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_building_floorsItemStateChanged

        if (jComboBox_building_floors.getSelectedIndex() != -1) {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {

                        String buildingId = buildingList.get(jComboBox_building_floors.getSelectedIndex()).getId();
                        blockListofBuilding = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=?  ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(buildingId);

                        //blockListofBuilding = new PreparedSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=? ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(buildingId);
                        jComboBox_block_floors.setModel(new ComboBoxValModel(blockListofBuilding));

                        w.hideDialog();
                    } catch (BasicException ex) {
                        w.hideDialog();
                        Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        w.hideDialog();
                        Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            try {
                t.start();
                if (!w.isShowing()) {
                    w.showDialog("Please wait. Collecting data");
                }

            } catch (Exception ex) {
                w.hideDialog();
                Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                msg.show(null);
            }
        }

    }//GEN-LAST:event_jComboBox_building_floorsItemStateChanged

    private void jComboBox_building_locationsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_building_locationsItemStateChanged
        // TODO add your handling code here:
        if (jComboBox_building_locations.getSelectedIndex() != -1) {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {

                        String buildingId = buildingList.get(jComboBox_building_locations.getSelectedIndex()).getId();
                        blockListofBuilding = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=? AND ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(buildingId);
                        //blockListofBuildingNames = new PreparedSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=? AND ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(buildingId);

                        jComboBox_Block_locations.setModel(new ComboBoxValModel(blockListofBuilding));

                        w.hideDialog();
                    } catch (BasicException ex) {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        w.hideDialog();
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            try {
                t.start();
                if (!w.isShowing()) {
                    w.showDialog("Please wait. Collecting data");
                }

            } catch (Exception ex) {
                w.hideDialog();
                Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                msg.show(null);
            }
        }


    }//GEN-LAST:event_jComboBox_building_locationsItemStateChanged

    private void jComboBox_Block_locationsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_Block_locationsItemStateChanged
        // TODO add your handling code here:
        if (jComboBox_building_locations.getSelectedIndex() != -1) {
            if (jComboBox_Block_locations.getSelectedIndex() != -1) {
                Thread t = new Thread(new Runnable() {

                    public void run() {
                        try {
                                    
                            String blockObject =  blockListofBuilding.get(jComboBox_Block_locations.getSelectedIndex()).getId();
                            
                            //Object blockObject = new PreparedSentence(m_App.getSession(), "SELECT ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND NAME=?  ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(jComboBox_Block_locations.getSelectedItem().toString());
                            if(floorListofBlock != null) {
                                floorListofBlock = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1  AND BLOCK=?   ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(blockObject.toString());
                                //floorListofBlockNames = new PreparedSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1 AND BLOCK=?   ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(blockObject.toString());

                                jComboBox_floor_locations.setModel(new ComboBoxValModel(floorListofBlock));
                            }
                            w.hideDialog();
                        } catch (BasicException ex) {
                            w.hideDialog();
                            Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            w.hideDialog();
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                try {
                    t.start();
                    if (!w.isShowing()) {
                        w.showDialog("Please wait. Collecting data");
                    }

                } catch (Exception ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                    msg.show(null);
                }
            }
        }
    }//GEN-LAST:event_jComboBox_Block_locationsItemStateChanged

    private void jComboBox_Block_locationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Block_locationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Block_locationsActionPerformed

    private void jComboBox_floorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_floorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_floorActionPerformed

    private void jComboBox_floor_locationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_floor_locationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_floor_locationsActionPerformed

    private void jComboBox_building_locationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_building_locationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_building_locationsActionPerformed

    private void jComboBox_building_BlocksItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_building_BlocksItemStateChanged
        // TODO add your handling code here:
        if (jComboBox_building_Blocks.getSelectedIndex() != -1) {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {

                        String buildingId = buildingList.get(jComboBox_building_Blocks.getSelectedIndex()).getId();
                        blockListofBuilding = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=? ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(buildingId);
                       // blockListofBuildingNames = new PreparedSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 AND BUILDING=? ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(buildingId);

                        jComboBox_blocks.setModel(new ComboBoxValModel(blockListofBuilding));

                        w.hideDialog();
                    } catch (BasicException ex) {
                        w.hideDialog();
                        Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        w.hideDialog();
                        Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            try {
                t.start();
                if (!w.isShowing()) {
                    w.showDialog("Please wait. Collecting data");
                }

            } catch (Exception ex) {
                w.hideDialog();
                Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                msg.show(null);
            }
        }


    }//GEN-LAST:event_jComboBox_building_BlocksItemStateChanged

    private void jComboBox_blocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_blocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_blocksActionPerformed

    private void jComboBox_building_BlocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_building_BlocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_building_BlocksActionPerformed

    private void jComboBox_block_floorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_block_floorsItemStateChanged
        // TODO add your handling code here:
        if (jComboBox_building_floors.getSelectedIndex() != -1) {
            if (jComboBox_block_floors.getSelectedIndex() != -1) {
                Thread t = new Thread(new Runnable() {

                    public void run() {
                        try {

                            String blockId = blockListofBuilding.get(jComboBox_block_floors.getSelectedIndex()).getId();

                            floorListofBlock = new PreparedSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1  AND BLOCK=?  ORDER BY NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(LocationBean.class)).list(blockId);
                           // floorListofBlockNames = new PreparedSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1 AND BLOCK=?  ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(blockId);

                            jComboBox_floor.setModel(new ComboBoxValModel(floorListofBlock));

                            w.hideDialog();
                        } catch (BasicException ex) {
                            w.hideDialog();
                            Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            w.hideDialog();
                            Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(FixedAssetLocation.this, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                try {
                    t.start();
                    if (!w.isShowing()) {
                        w.showDialog("Please wait. Collecting data");
                    }

                } catch (Exception ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                    msg.show(null);
                }
            }
        }


    }//GEN-LAST:event_jComboBox_block_floorsItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit_but;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox jComboBox_Block_locations;
    private javax.swing.JComboBox jComboBox_Building;
    private javax.swing.JComboBox jComboBox_block_floors;
    private javax.swing.JComboBox jComboBox_blocks;
    private javax.swing.JComboBox jComboBox_building_Blocks;
    private javax.swing.JComboBox jComboBox_building_floors;
    private javax.swing.JComboBox jComboBox_building_locations;
    private javax.swing.JComboBox jComboBox_floor;
    private javax.swing.JComboBox jComboBox_floor_locations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField_building;
    private javax.swing.JLabel msgdlg2;
    private javax.swing.JLabel msgdlg4;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton removeButt;
    private javax.swing.JButton reset_but;
    private javax.swing.JButton savebutt;
    private javax.swing.JButton savecha_but;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
        return "Fixed Asset Location";
    }

    @Override
    public void activate() throws BasicException {

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
        m_App = app;
        dmang = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

    }

    public void init() throws BasicException {
        initComponents();

    }

    @Override
    public Object getBean() {
        return this;
    }

    public void reset() {
        nameText.setText(null);
        jComboBox_floor_locations.setSelectedItem(-1);
        jComboBox_building_locations.setSelectedItem(-1);
        jComboBox_Block_locations.setSelectedItem(-1);
        savebutt.setVisible(true);
        savecha_but.setVisible(false);

    }

    public void loadProperties(AppConfig config) {

        //  linktxt.setText(config.getProperty("folder"));
        dirty.setDirty(false);
    }

    public void saveProperties(AppConfig config) {

        //config.setProperty("FOLDER", linktxt.getText());
        dirty.setDirty(false);
    }

    public void loaddata() throws BasicException {
        
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {

                    reset();
                    flagkey = 0;
                    fxd_table = FALocationsTableModel.GetFALocationsTableModel(m_App);
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    jTable1.setModel(fxd_table.getTableModel());

                    savebutt.setVisible(true);
                    savecha_but.setVisible(false);
                    edit_but.setVisible(true);
                    locationTables();
                    buildingModel = new ComboBoxValModel(buildingList);
                    buildingModel_Location = new ComboBoxValModel(buildingList);
                     
                    blockModel = new ComboBoxValModel(blockList);
                    floorModel = new ComboBoxValModel(floorList);
                    jComboBox_building_locations.setModel(buildingModel_Location);
                    jComboBox_building_Blocks.setModel(new ComboBoxValModel(buildingList));
                    jComboBox_building_floors.setModel(new ComboBoxValModel(buildingList));
                    jComboBox_Building.setModel(buildingModel);
                    ComboBoxValModel blankModel = new ComboBoxValModel(new ArrayList<LocationBean>());
                    jComboBox_blocks.setModel(blankModel);
                    jComboBox_floor.setModel(blankModel);
                    jComboBox_Block_locations.setModel(blankModel);
                    jComboBox_floor_locations.setModel(blankModel);
                    jComboBox_block_floors.setModel(blankModel);

                    w.hideDialog();
                    
                } catch (BasicException ex) {
                    w.hideDialog();
                    Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
                    MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
                    msg.show(null);
                }
            }
        });
        try {
            t.start();
            if (!w.isShowing()) {
                w.showDialog("Please wait. Collecting data");
            }

        } catch (Exception ex) {
            w.hideDialog();
            Logger.getLogger(FixedAssetLocation.class.getName()).log(Level.SEVERE, null, ex);
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), ex);
            msg.show(null);
        }

        
        

    }

    public void locationTables() {
        try {

            buildingList = new ArrayList<LocationBean>(new StaticSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BL' AND ACTIVE=1 ORDER BY NAME", null, new SerializerReadClass(LocationBean.class)).list());
            blockList = new ArrayList<LocationBean>(new StaticSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 ORDER BY NAME", null, new SerializerReadClass(LocationBean.class)).list());
            floorList = new ArrayList<LocationBean>(new StaticSentence(m_App.getSession(), "SELECT NAME,ID FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1 ORDER BY NAME", null, new SerializerReadClass(LocationBean.class)).list());
            //buildingListNames = new ArrayList<String>(new StaticSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='BL' AND ACTIVE=1 ORDER BY NAME", null, SerializerReadString.INSTANCE).list());
            //blockListNames = new ArrayList<String>(new StaticSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='BK' AND ACTIVE=1 ORDER BY NAME", null, SerializerReadString.INSTANCE).list());
            //floorListNames = new ArrayList<String>(new StaticSentence(m_App.getSession(), "SELECT NAME FROM  FA_LOCATIONS WHERE TYPE='FL' AND ACTIVE=1 ORDER BY NAME", null, SerializerReadString.INSTANCE).list());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static class LocationBean implements SerializableRead {

        private String id;
        private String name;

        @Override
        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(2);
            name = dr.getString(1);

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
         @Override
        public String toString() {
            return name; 
        }
        

    }
}
