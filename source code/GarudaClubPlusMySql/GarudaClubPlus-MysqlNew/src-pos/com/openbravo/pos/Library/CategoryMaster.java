/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.pos.Library.Lib_CategoryTableModel.lib_Categoryline;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.clubmang.FacilityApprovalRitesModel;
import com.openbravo.pos.clubmang.MemCat;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Santhosh
 */
public class CategoryMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private DataLogicFacilities dlfac;
    private ComboBoxValModel fModel;
     
      private ComboBoxValModel p1Model,a1Model,a2Model,a3Model;
     private ComboBoxValModel p2Model;  
    private DataLogicFacilities dlaac;
    Lib_CategoryTableModel.lib_Categoryline cat1;
   private String Name;
   Lib_CategoryTableModel amodel;
    private String c_id;
    private String deact_id;
    private String prName;
    private AllUsersListModel aumodel;
    private AllUsersListModel cfmodel;
    private List list=new ArrayList();
    private List l1= new ArrayList();
    String obj[];
    /** Creates new form CategoryMaster */
    public CategoryMaster() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        name_txt = new javax.swing.JTextField();
        dis_txt = new javax.swing.JTextField();
        primprt_comb = new javax.swing.JComboBox();
        prnt_comb = new javax.swing.JComboBox();
        revhd_comb = new javax.swing.JComboBox();
        disphd_comb = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        custcuracc_comb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        fclty_comb = new javax.swing.JComboBox();
        save = new javax.swing.JButton();
        savechanges = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        add_txt = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        deactivate = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Address");

        jLabel3.setText("Display Name");

        jLabel4.setText("Primary Parent");

        jLabel5.setText("Parent");

        jLabel6.setText("Revenue Head");

        jLabel7.setText("Disposal Head");

        primprt_comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        primprt_comb.setSelectedIndex(-1);

        prnt_comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        prnt_comb.setSelectedIndex(-1);
        prnt_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prnt_combActionPerformed(evt);
            }
        });

        revhd_comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        revhd_comb.setSelectedIndex(-1);
        revhd_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revhd_combActionPerformed(evt);
            }
        });

        disphd_comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        disphd_comb.setSelectedIndex(-1);
        disphd_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disphd_combActionPerformed(evt);
            }
        });

        jLabel8.setText("CustomerCurrAcc");

        custcuracc_comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        custcuracc_comb.setSelectedIndex(-1);
        custcuracc_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custcuracc_combActionPerformed(evt);
            }
        });

        jLabel9.setText("Facility");

        fclty_comb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fclty_comb.setSelectedIndex(-1);
        fclty_comb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fclty_combActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        savechanges.setText("SaveChanges");
        savechanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savechangesActionPerformed(evt);
            }
        });

        add_txt.setColumns(20);
        add_txt.setRows(5);
        jScrollPane1.setViewportView(add_txt);

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Category Applicable to Member type:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("All");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("MembershipType");
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jList2);

        jButton3.setText("remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(save, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(savechanges)
                        .add(56, 56, 56))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel8)
                                    .add(jLabel4)
                                    .add(jLabel5)
                                    .add(jLabel3)
                                    .add(jLabel2)
                                    .add(jLabel1))
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(24, 24, 24)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(primprt_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, custcuracc_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(prnt_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(dis_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(name_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(fclty_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(jLabel9))
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(27, 27, 27)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(jLabel6)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(4, 4, 4)))
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(revhd_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(disphd_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel10)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jRadioButton1)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jRadioButton2))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(79, 79, 79)
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jButton2)
                                    .add(jButton3))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(52, 52, 52)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(name_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10)
                    .add(jRadioButton1)
                    .add(jRadioButton2))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(dis_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel3)))
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton3))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .add(15, 15, 15)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(prnt_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6)
                    .add(revhd_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(disphd_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(primprt_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(custcuracc_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(fclty_comb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(128, 128, 128)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(savechanges, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton1)
                    .add(save))
                .add(46, 46, 46))
        );

        jTabbedPane1.addTab("CreateNew", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTable1);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        deactivate.setText("Deactivate");
        deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivateActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Show All");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap(607, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(edit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deactivate)
                        .add(115, 115, 115))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox1)
                        .add(46, 46, 46))))
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {deactivate, edit}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 499, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(edit)
                    .add(deactivate))
                .addContainerGap())
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .add(53, 53, 53)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 434, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(89, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("View", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 613, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void prnt_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prnt_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prnt_combActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        
        try{
        if( name_txt.getText().length()>0 ){
            if(revhd_comb.getSelectedIndex()!=-1&& disphd_comb.getSelectedIndex()!=-1){
            if(custcuracc_comb.getSelectedIndex()!=-1&& fclty_comb.getSelectedIndex()!=-1){
            int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM lib_categories WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name_txt.getText()).toString());  
                if(count==0){
                   if(add_txt.getText().length()>0 && primprt_comb.getSelectedItem()!=null && prnt_comb.getSelectedItem()!=null){
                    if(jRadioButton1.isSelected()){
                            String memtype="All";
                            insertlib_category(memtype);
                        }else{
                            String memtyp="";
                            for(int i=0;i<l1.size();i++){
                             memtyp=memtyp+l1.get(i)+"#";
                            }
                            insertlib_category(memtyp);
                        }
                } else{
                    String missingnos=" ";
                    if(add_txt.getText().length()<=0)
                    missingnos +="Address ";
                    if(dis_txt.getText().length()<=0)
                    missingnos +="DispName ";
                    if(primprt_comb.getSelectedItem()==null)
                    missingnos +="primaryParent ";
                    if(prnt_comb.getSelectedItem()==null)
                    missingnos +="Parent ";
                   
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        if(jRadioButton1.isSelected()){
                            String memtype="All";
                            insertlib_category(memtype);
                        }else{
                            String memtyp = "";
                            for(int i=0;i<l1.size();i++){
                             memtyp=memtyp+l1.get(i)+"#";
                            }
                            insertlib_category(memtyp);
                        }
                        
                    }
                   }
                    
                }else{
                JOptionPane.showMessageDialog(this, "Category with the name "+name_txt.getText()+" already exist", null, JOptionPane.OK_OPTION);
            }
            }else{
              JOptionPane.showMessageDialog(this, "Please ensure that CurrentCstAcc and Facility is not empty", null, JOptionPane.OK_OPTION);  
            }
        }else{
              JOptionPane.showMessageDialog(this, "Please ensure that Revenue and disposal is not empty", null, JOptionPane.OK_OPTION);  
            }
            }else{
            JOptionPane.showMessageDialog(this, "Please ensure that categories is not empty", null, JOptionPane.OK_OPTION);
        }
            
            
            
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_saveActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
       
         javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int tabno=tabpane.getSelectedIndex();
        jCheckBox1.setSelected(false);
        if(tabno==1){
            try {
                amodel = Lib_CategoryTableModel.loadInstance(m_App,1);

                jTable1.setModel(amodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                
                TableColumnModel cmodel=jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(100);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(100);
                cmodel.getColumn(6).setPreferredWidth(100);
                cmodel.getColumn(7).setPreferredWidth(100);
                cmodel.getColumn(8).setPreferredWidth(100);
                
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void savechangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savechangesActionPerformed
       
        try{
        if( name_txt.getText().length()>0 ){
            if(revhd_comb.getSelectedIndex()!=-1&& disphd_comb.getSelectedIndex()!=-1){
            if(custcuracc_comb.getSelectedIndex()!=-1&& fclty_comb.getSelectedIndex()!=-1){
           
                   if(add_txt.getText().length()>0 && primprt_comb.getSelectedItem()!=null && prnt_comb.getSelectedItem()!=null){
                    if(jRadioButton1.isSelected()){
                            String memtype="All";
                             updatelib_category(memtype);
                        }else{
                            String memtyp="";
                            for(int i=0;i<l1.size();i++){
                             memtyp=memtyp+l1.get(i)+"#";
                            }
                             updatelib_category(memtyp);
                        }
                       
                } else{
                    String missingnos=" ";
                    if(add_txt.getText().length()<=0)
                    missingnos +="Address ";
                    if(dis_txt.getText().length()<=0)
                    missingnos +="DispName ";
                    if(primprt_comb.getSelectedItem()==null)
                    missingnos +="primaryParent ";
                    if(prnt_comb.getSelectedItem()==null)
                    missingnos +="Parent ";
                    
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        if(jRadioButton1.isSelected()){
                            String memtype="All";
                             updatelib_category(memtype);
                        }else{
                            String memtyp="";
                            for(int i=0;i<l1.size();i++){
                             memtyp=memtyp+l1.get(i)+"#";
                            }
                             updatelib_category(memtyp);
                        }
                    }
                   }
               
           }else{
              JOptionPane.showMessageDialog(this, "Please ensure that CurrentCstAcc and Facility is not empty", null, JOptionPane.OK_OPTION);  
            }
        }else{
              JOptionPane.showMessageDialog(this, "Please ensure that Revenue and disposal is not empty", null, JOptionPane.OK_OPTION);  
            }
            }else{
            JOptionPane.showMessageDialog(this, "Please ensure that categories is not empty", null, JOptionPane.OK_OPTION);
        }
            
            
            
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }//GEN-LAST:event_savechangesActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        
        if(jTable1.getSelectedRow()!=-1){
            int pub = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(pub == JOptionPane.YES_OPTION){
                if(jTable1.getSelectedRow()<amodel.getSize()){
                    int row = jTable1.getSelectedRow();
                    if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 13).toString())) {
                    lib_Categoryline data = amodel.getList().get(row);
                    savechanges.setVisible(true);
                    save.setVisible(false);
                    c_id=data.getId();
                    String name1=data.getName();
                    String add=data.getAddress();
                    String parent=data.getParent();
                    String priprnt=data.getPrimaryparent();
                    String revn=data.getRevenue();
                    String dispo=data.getDisposal();
                    String dispname=data.getDispname();
                    String curcusac=data.getCurcusacc();
                    String fac=data.getFacility();
                    String memtyp=data.getMemtypes();
                    
                    
                     List<Object> p1_list = new ArrayList<Object>();
                     List<Object> p2_list = new ArrayList<Object>();
                     //List<Object> p3_list = new ArrayList<Object>();
                     //List<Object> p4_list = new ArrayList<Object>();
                     //List<Object> p5_list = new ArrayList<Object>();
                     List<Object> p6_list = new ArrayList<Object>();
      try {
            
            p1_list = (List<Object>)new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(parent);
            p2_list=(List<Object>)new StaticSentence(m_App.getSession(), "SELECT a.NAME FROM accountmaster a WHERE a.ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(revn);
           //p3_list=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT a.NAME FROM accountmaster a WHERE a.ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(dispo);
            //p4_list=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT a.NAME FROM accountmaster a WHERE a.ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(curcusac);
           //p5_list=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT f.name FROM facility f WHERE f.id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(fac);
            p6_list=(List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(priprnt);
             } catch (BasicException ex) {
            Logger.getLogger(CategoryMaster.class.getName()).log(Level.SEVERE, null, ex);
        }      
                    
                    
                    name_txt.setText(name1);
                    add_txt.setText(add);
                    dis_txt.setText(dispname);
                    
                    if(name1!=null && !name1.equals("") ){
                      for(int i=0; i<revhd_comb.getItemCount(); i++)
                            {
                              if(revhd_comb.getItemAt(i).toString().equals(revn))
                                {
                                    revhd_comb.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<disphd_comb.getItemCount(); i++)
                            {
                              if(disphd_comb.getItemAt(i).toString().equals(dispo))
                                {
                                    disphd_comb.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<custcuracc_comb.getItemCount(); i++)
                            {
                              if(custcuracc_comb.getItemAt(i).toString().equals(curcusac))
                                {
                                    custcuracc_comb.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<fclty_comb.getItemCount(); i++)
                            {
                              if(fclty_comb.getItemAt(i).toString().equals(fac))
                                {
                                    fclty_comb.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                   
                    }
                            if(p1_list.size()!=0){
                                p1Model.setSelectedItem(p1_list.get(0));
                            }else{
                                prnt_comb.setSelectedIndex(-1);
                                }
                            if(p6_list.size()!=0){
                                p2Model.setSelectedItem(p6_list.get(0));
                            }else{
                                primprt_comb.setSelectedIndex(-1);
                            }
                            if(!memtyp.equals("All")){
                                obj=memtyp.split("#");
                                jRadioButton2.setSelected(true);
                                
                               // List obj1=new ArrayList();
                                for(int i=0;i<obj.length;i++){
                                    try {
                                        if(!l1.contains(obj[i])){
                                        l1.add(obj[i]);
                                        
                                        List obj2=(List)new StaticSentence(m_App.getSession(), "SELECT name FROM memtype  WHERE id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(obj[i]);
                                    list.add(obj2.get(0).toString());
                                        }
                                    } catch (BasicException ex) {
                                        Logger.getLogger(CategoryMaster.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                cfmodel=new AllUsersListModel(list);
                                jList2.setModel(cfmodel);
                            }else{
                                jRadioButton1.setSelected(true);
                            }
                                jTabbedPane1.setSelectedIndex(0);
                                }else {
                    JOptionPane.showMessageDialog(this, "selected Category is deactivated.cannot edit it");
                }
                }
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void revhd_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revhd_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revhd_combActionPerformed

    private void disphd_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disphd_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disphd_combActionPerformed

    private void custcuracc_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custcuracc_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custcuracc_combActionPerformed

    private void fclty_combActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fclty_combActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fclty_combActionPerformed

    private void deactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateActionPerformed
       
       if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION){
                  if(jTable1.getSelectedRow()<amodel.getSize()){
                     int row = jTable1.getSelectedRow(); 
                     if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 13).toString())) {
                     lib_Categoryline showdata = amodel.getList().get(row);
                     deact_id=showdata.getId();
                     deactcat_author();
                     }else {
                    JOptionPane.showMessageDialog(this, "selected Category is already deactivated.");
                }
                  }
             }
         } 
        
    }//GEN-LAST:event_deactivateActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        try {
            if (jCheckBox1.isSelected() == true) {
                amodel = Lib_CategoryTableModel.loadInstance(m_App, 2);
                jTable1.setModel(amodel.getTableModel());
            } else if (jCheckBox1.isSelected() == false) {
                amodel = Lib_CategoryTableModel.loadInstance(m_App, 1);
                jTable1.setModel(amodel.getTableModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int row=jList1.getSelectedIndex();
            if(row>=0){
            
                MemCat Memtyp=new MemCat();
               Memtyp =(MemCat) aumodel.getElementAt(row);
                String memtypid=Memtyp.getID();
                //String memname=Memtyp.getMemberCategory();
                if(!l1.contains(memtypid)){ 
                l1.add(memtypid);
                }
                 //for(int i=0;i<l1.size();i++){
               //List lst=(List)new StaticSentence(m_App.getSession(), "SELECT name FROM memtype  WHERE id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memtypid);
                 //}
                if(!list.contains(Memtyp.getMemberCategory())){
                list.add(Memtyp.getMemberCategory());
                }
                cfmodel=new AllUsersListModel(list);
                jList2.setModel(cfmodel);
                jList1.clearSelection();
                
            
            }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        if(jRadioButton2.isSelected()){
            jScrollPane2.setVisible(true);
            jList1.setVisible(true);
            jScrollPane3.setVisible(true);
            jList2.setVisible(true);
            jButton2.setVisible(true);
             jButton3.setVisible(true);
        }else{
            jScrollPane2.setVisible(false);
            jList1.setVisible(false);
            jScrollPane3.setVisible(false);
            jList2.setVisible(false);
            jButton2.setVisible(false);
             jButton3.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        int row=jList2.getSelectedIndex();
            if(row>=0){
            
               // MemCat Memtyp=new MemCat();
               //Memtyp =(MemCat) cfmodel.getElementAt(row);
                //String memtypid=Memtyp.getID();
                 l1.remove(row);
                 //for(int i=0;i<l1.size();i++){
               //List lst=(List)new StaticSentence(m_App.getSession(), "SELECT name FROM memtype  WHERE id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(memtypid);
                 //}
               
                list.remove(row);
                cfmodel=new AllUsersListModel(list);
                jList2.setModel(cfmodel);
                jList2.clearSelection();
             
            
            }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea add_txt;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox custcuracc_comb;
    private javax.swing.JButton deactivate;
    private javax.swing.JTextField dis_txt;
    private javax.swing.JComboBox disphd_comb;
    private javax.swing.JButton edit;
    private javax.swing.JComboBox fclty_comb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name_txt;
    private javax.swing.JComboBox primprt_comb;
    private javax.swing.JComboBox prnt_comb;
    private javax.swing.JComboBox revhd_comb;
    private javax.swing.JButton save;
    private javax.swing.JButton savechanges;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
        return "Library Category Management";
    }

    @Override
    public void activate() throws BasicException {
      
       List<Facility> flist=dlfac.getFacility();
      
       fModel=new ComboBoxValModel(flist);
       fclty_comb.setModel(fModel);
       a1Model=new ComboBoxValModel(dlaac.getaccounts());
       revhd_comb.setModel(a1Model);
       a2Model=new ComboBoxValModel(dlfac.getaccounts());
       disphd_comb.setModel(a2Model);
       a3Model=new ComboBoxValModel(dlfac.getaccounts());
       custcuracc_comb.setModel(a3Model);
       p1Model=new ComboBoxValModel(getname());
       prnt_comb.setModel(p1Model);
       p2Model=new ComboBoxValModel(getname1());
       primprt_comb.setModel(p2Model);
       reset();
      List mlist = dlfac.getMemberCategory();
      aumodel=new AllUsersListModel(mlist);
        jList1.setModel(aumodel);
        List l= new ArrayList();
        cfmodel=new AllUsersListModel(l);
        jList2.setModel(cfmodel);
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
        dlfac=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlaac=(DataLogicFacilities) app.getBean("com.openbravo.pos.Library.LibDataLogicFacilitiesCreate");
       
    }

    @Override
    public Object getBean() {
       return this;
    }
    
     public void reset(){
         
         name_txt.setText(null);
         add_txt.setText(null);
         dis_txt.setText(null);
         primprt_comb.setSelectedIndex(-1);
         prnt_comb.setSelectedIndex(-1);
         revhd_comb.setSelectedIndex(-1);
         disphd_comb.setSelectedIndex(-1);
         custcuracc_comb.setSelectedIndex(-1);
         fclty_comb.setSelectedIndex(-1);
         save.setVisible(true);
         savechanges.setVisible(false);
         jTabbedPane1.setSelectedIndex(0);
         jCheckBox1.setSelected(false);
         jRadioButton1.setSelected(true);
         if(jRadioButton1.isSelected()){
         jScrollPane2.setVisible(false);
            jList1.setVisible(false);
            jScrollPane3.setVisible(false);
            jList2.setVisible(false);
            jButton2.setVisible(false);
             jButton3.setVisible(false);
         }
     }
     public List getname() throws BasicException{
          List<Object> catg_list = new ArrayList<Object>();
           catg_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return catg_list;
      }
      public  String getpId(){
           List<Object> prnt_list1 = new ArrayList<Object>();
        try {
            if(prnt_comb.getSelectedIndex()!=-1){
            prnt_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(prnt_comb.getSelectedItem());
        Name =(String)prnt_list1.get(0);
            }else{
                Name=" ";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(CategoryMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return Name;
      }
      
       public List getname1() throws BasicException{
          List<Object> catg_list = new ArrayList<Object>();
           catg_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE  c.parentid in(select c.id FROM lib_categories c where active =1) and active =1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return catg_list;
      }
      public  String getp1Id(){
           
             List<Object> prprnt_list1 = new ArrayList<Object>();
        try {
            if(prnt_comb.getSelectedIndex()!=-1){
            prprnt_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.id FROM lib_categories c WHERE  c.parentid in(select c.id FROM lib_categories c where active =1) and active =1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(prnt_comb.getSelectedItem());
        prName =(String)prprnt_list1.get(0);
            }else{
                prName=" ";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(CategoryMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return prName;
      }
      private void insertlib_category(String memtype) {
        
         try{
          if(name_txt.getText().length()>0){
               AccountMaster acc=(AccountMaster)revhd_comb.getSelectedItem();
               AccountMaster acc1=(AccountMaster)disphd_comb.getSelectedItem();
               AccountMaster acc2=(AccountMaster)custcuracc_comb.getSelectedItem();
               Facility fcc=(Facility)fclty_comb.getSelectedItem();
           
              Object[] param=new Object[]{UUID.randomUUID().toString(),name_txt.getText(),getpId(),add_txt.getText(),dis_txt.getText(),getp1Id(),acc.getId(),acc1.getId(),acc2.getId(),fcc.getid(),/*ADD CATEG & LANG COMBO,*/m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),true,memtype};
             new PreparedSentence(m_App.getSession()
                , "INSERT INTO lib_categories (ID,NAME,parentid,Address,DispName,Primaryparent,RevenueHead,DisposalHead,CustCurrAcct,Facility,CREATEDBY,CRDATE,CREATEDHOST,ACTIVE,memtypes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.STRING})).exec(param);
          JOptionPane.showMessageDialog(this, "Created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           }

          
          reset(); 
              
         }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
      }
      
      
       private void updatelib_category(String memtype) {
           
           try{
          if(name_txt.getText().length()>0){
              AccountMaster acc=(AccountMaster)revhd_comb.getSelectedItem();
              AccountMaster acc1=(AccountMaster)disphd_comb.getSelectedItem();
              AccountMaster acc2=(AccountMaster)custcuracc_comb.getSelectedItem();
              Facility fcc=(Facility)fclty_comb.getSelectedItem();
           
              Object[] param=new Object[]{name_txt.getText(),getpId(),add_txt.getText(),dis_txt.getText(),getp1Id(),acc.getId(),acc1.getId(),acc2.getId(),fcc.getid(),m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),true,memtype,c_id};
             new PreparedSentence(m_App.getSession()
                , "UPDATE lib_categories SET NAME=?,parentid=?,Address=?,DispName=?,Primaryparent=?,RevenueHead=?,DisposalHead=?,CustCurrAcct=?,Facility=?,CREATEDBY=?,CRDATE=?,CREATEDHOST=?,ACTIVE=?,memtypes=? WHERE ID=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING})).exec(param);
          JOptionPane.showMessageDialog(this, "Udated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           }

            amodel  = Lib_CategoryTableModel.loadInstance(m_App,1);
            jTable1.setModel(amodel.getTableModel()); 
          reset(); 
              
      }catch(NullPointerException e){
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
           
           
       }
       
       private void deactcat_author() {
           try{
           String newcatgId = UUID.randomUUID().toString();
           
            new PreparedSentence(m_App.getSession(), "UPDATE lib_categories  SET ID=?, ACTIVE=0  , DEACTBY=? , DEACTDATE=? , DEACTHOST=?,DEACTREFERENCE=? WHERE ID = ? AND DEACTBY IS NULL AND DEACTDATE IS NULL"
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.STRING  , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{ newcatgId, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),deact_id , deact_id  });
            amodel  = Lib_CategoryTableModel.loadInstance(m_App,1);
            jTable1.setModel(amodel.getTableModel()); 
       
            reset();
          JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
            p1Model=new ComboBoxValModel(getname());
            prnt_comb.setModel(p1Model);
            p2Model=new ComboBoxValModel(getname1());
            primprt_comb.setModel(p2Model);
           
           
       }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
    }
       
        public void load(){
            
        }
       
      private class AllUsersListModel extends AbstractListModel {
        private java.util.List<FacilityApprovalRitesModel.AllUsersLine> ulist;
        public AllUsersListModel(java.util.List<FacilityApprovalRitesModel.AllUsersLine> ulist) {
            this.ulist = ulist;
        }
        public int getSize() {
            return ulist.size();
        }
        public Object getElementAt(int i) {
            return ulist.get(i);
        }
        public void remove(int i){
             ulist.remove(i);
        }

    }
}
