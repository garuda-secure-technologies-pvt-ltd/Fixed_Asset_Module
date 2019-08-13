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
import com.openbravo.pos.Library.Lib_PublisherTableModel.lib_Publisherline;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Santhosh
 */
public class PublisherMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    private Lib_PublisherTableModel pmodel;
    private ComboBoxValModel venmodel,lanmodel;
    private DataLogicFacilities dlfac;
    private String Name;
    private String p_id;
    private String deact_id;
    private String lan;

    /** Creates new form PublisherMaster */
    public PublisherMaster() {
        initComponents();
    }

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        addr1 = new javax.swing.JLabel();
        addr2 = new javax.swing.JLabel();
        cit = new javax.swing.JLabel();
        state = new javax.swing.JLabel();
        pin = new javax.swing.JLabel();
        phno = new javax.swing.JLabel();
        name_text = new javax.swing.JTextField();
        city_text = new javax.swing.JTextField();
        state_text = new javax.swing.JTextField();
        pin_text = new javax.swing.JTextField();
        phn_txt = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        SaveChanges = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        addr1_text = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        addr2_text = new javax.swing.JTextArea();
        Ctry_text = new javax.swing.JTextField();
        ctry = new javax.swing.JLabel();
        lang = new javax.swing.JLabel();
        lang_cmb = new javax.swing.JComboBox();
        vendor = new javax.swing.JLabel();
        vend_cmb = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
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
        Edit = new javax.swing.JButton();
        Deactivate = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        name.setText("Name");

        addr1.setText("Address1");

        addr2.setText("Address2");

        cit.setText("City");

        state.setText("State");

        pin.setText("PIN");

        phno.setText("Contact No");

        city_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                city_textActionPerformed(evt);
            }
        });

        state_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                state_textActionPerformed(evt);
            }
        });

        pin_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pin_textKeyTyped(evt);
            }
        });

        phn_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phn_txtKeyTyped(evt);
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        SaveChanges.setText("SaveChanges");
        SaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChangesActionPerformed(evt);
            }
        });

        addr1_text.setColumns(20);
        addr1_text.setRows(5);
        jScrollPane3.setViewportView(addr1_text);

        addr2_text.setColumns(20);
        addr2_text.setRows(5);
        jScrollPane4.setViewportView(addr2_text);

        ctry.setText("Country");

        lang.setText("Language");

        lang_cmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        lang_cmb.setSelectedIndex(-1);

        vendor.setText("Vendor/Donor");

        vend_cmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        vend_cmb.setSelectedIndex(-1);

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addr1)
                    .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pin)
                    .add(phno)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cit)
                            .add(state)
                            .add(ctry)
                            .add(lang)
                            .add(addr2)
                            .add(vendor))
                        .add(27, 27, 27)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(vend_cmb, 0, 235, Short.MAX_VALUE)
                            .add(lang_cmb, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(state_text)
                            .add(Ctry_text)
                            .add(city_text)
                            .add(pin_text)
                            .add(phn_txt)
                            .add(name_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .add(Save, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(SaveChanges, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(name_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(addr1)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(27, 27, 27)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(addr2)
                            .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(19, 19, 19)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Ctry_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(ctry))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cit)
                            .add(city_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(state_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(state))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(pin_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(pin))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(phno)
                            .add(phn_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lang)
                            .add(lang_cmb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(vendor)
                            .add(vend_cmb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 44, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(SaveChanges)
                            .add(jButton1)
                            .add(Save))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Create New", jPanel1);

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

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Deactivate.setText("Deactivate");
        Deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeactivateActionPerformed(evt);
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
                .addContainerGap(413, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(Edit)
                        .add(18, 18, 18)
                        .add(Deactivate)
                        .add(82, 82, 82))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox1)
                        .add(38, 38, 38))))
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {Deactivate, Edit}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 469, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Edit)
                    .add(Deactivate))
                .addContainerGap())
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .add(50, 50, 50)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 435, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE)))
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
            .add(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void city_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_city_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_city_textActionPerformed

    private void state_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_state_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_state_textActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        try{
            if(name_text.getText().length()>0 && vend_cmb.getSelectedIndex()!=-1 && lang_cmb.getSelectedIndex()!=-1){
              int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM lib_publisher WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name_text.getText()).toString());  
                if(count==0){
                   if(Ctry_text.getText().length()>0 && state_text.getText().length()>0 && city_text.getText().length()>0 && pin_text.getText().length()>0){
                    insertlib_publisher();
                } else{
                    String missingnos=" ";
                    if(Ctry_text.getText().length()<=0)
                    missingnos +="CNTRY ";
                    if(state_text.getText().length()<=0)
                    missingnos +="STATE";
                    if(city_text.getText().length()<=0)
                    missingnos +="CITY";
                    if(pin_text.getText().length()<=0)
                    missingnos +="PIN";
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        insertlib_publisher();
                    }
                   }
                    
                }else{
                JOptionPane.showMessageDialog(this, "publisher with the name "+name.getText()+" already exist", null, JOptionPane.OK_OPTION);
            }
            }else{
            JOptionPane.showMessageDialog(this, "Please ensure that vendor and Publisher name is not empty", null, JOptionPane.OK_OPTION);
        }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_SaveActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
      javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int tabno=tabpane.getSelectedIndex();
        jCheckBox1.setSelected(false);
        if(tabno==1){
            try {
                pmodel = Lib_PublisherTableModel.loadInstance(m_App,1);

                jTable1.setModel(pmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

                TableColumnModel cmodel=jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(200);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(60);
                cmodel.getColumn(4).setPreferredWidth(60);
                cmodel.getColumn(5).setPreferredWidth(60);
                cmodel.getColumn(6).setPreferredWidth(60);
                cmodel.getColumn(7).setPreferredWidth(100);
                cmodel.getColumn(8).setPreferredWidth(100);
                cmodel.getColumn(9).setPreferredWidth(100);
                cmodel.getColumn(10).setPreferredWidth(100);
               
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed

        if(jTable1.getSelectedRow()!=-1){
            int pub = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(pub == JOptionPane.YES_OPTION){
                if(jTable1.getSelectedRow()<pmodel.getSize()){
                    int row = jTable1.getSelectedRow();
                    if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 16).toString())) {
                    lib_Publisherline data = pmodel.getList().get(row);
                    SaveChanges.setVisible(true);
                    Save.setVisible(false);
                    p_id=data.getId();
                    String name1=data.getName();
                    String add1=data.getAddress();
                    String add2=data.getAddress2();
                    String cntry=data.getCountry();
                    String city1=data.getCity();
                    String state1=data.getState();
                    String pin1=data.getPostal();
                    String phno1=data.getPhone();
                     String vend=data.getVendor();
                    String lang=data.getLanguage();
                    
                    name_text.setText(name1);
                    addr1_text.setText(add1);
                    addr2_text.setText(add2);
                    Ctry_text.setText(cntry);
                    city_text.setText(city1);
                    for(int i=0; i<vend_cmb.getItemCount(); i++)
                            {
                              if(vend_cmb.getItemAt(i).toString().equals(vend))
                                {
                                    vend_cmb.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                    for(int i=0; i<lang_cmb.getItemCount(); i++)
                            {
                              if(lang_cmb.getItemAt(i).toString().equals(lang))
                                {
                                    lang_cmb.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                    state_text.setText(state1);
                    pin_text.setText(pin1);
                    phn_txt.setText(phno1);
                    jTabbedPane1.setSelectedIndex(0);
                    }else {
                    JOptionPane.showMessageDialog(this, "selected Publisher is deactivated.cannot edit it");
                }
                }
            }

        }

    }//GEN-LAST:event_EditActionPerformed

    private void SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesActionPerformed
       try{
            if(vend_cmb.getSelectedIndex()!=-1 && name_text.getText().length()>0 && lang_cmb.getSelectedIndex()!=-1){
              if(Ctry_text.getText().length()>0 && state_text.getText().length()>0 && city_text.getText().length()>0 && pin_text.getText().length()>0){
                    updatelib_publisher();
                } else{
                    String missingnos=" ";
                    if(Ctry_text.getText().length()<=0)
                    missingnos +="CNTRY ";
                    if(state_text.getText().length()<=0)
                    missingnos +="STATE";
                    if(city_text.getText().length()<=0)
                    missingnos +="CITY";
                    if(pin_text.getText().length()<=0)
                    missingnos +="PIN";
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        updatelib_publisher();
                    }
                   }
                
            }else{
            JOptionPane.showMessageDialog(this, "Please ensure that vendor and Publisher name is not empty", null, JOptionPane.OK_OPTION);
        }
        
       }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_SaveChangesActionPerformed

    private void DeactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeactivateActionPerformed
        
        if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION){
                  if(jTable1.getSelectedRow()<pmodel.getSize()){
                     int row = jTable1.getSelectedRow(); 
                     if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 16).toString())) {
                     lib_Publisherline showdata = pmodel.getList().get(row);
                     deact_id=showdata.getId();
                     deactlib_publisher();
                     }else {
                    JOptionPane.showMessageDialog(this, "selected Publisher is already deactivated.");
                }
             
                  }
             }
         }
        
    }//GEN-LAST:event_DeactivateActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        
        try {
            if (jCheckBox1.isSelected() == true) {
                pmodel = Lib_PublisherTableModel.loadInstance(m_App, 2);
                jTable1.setModel(pmodel.getTableModel());
            } else if (jCheckBox1.isSelected() == false) {
                pmodel = Lib_PublisherTableModel.loadInstance(m_App, 1);
                jTable1.setModel(pmodel.getTableModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void pin_textKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pin_textKeyTyped
         char value = evt.getKeyChar();
    if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) ||value==KeyEvent.VK_DELETE)) {
       evt.consume();
    } 
    }//GEN-LAST:event_pin_textKeyTyped

    private void phn_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phn_txtKeyTyped
         char value1 = evt.getKeyChar();
    if (!(Character.isDigit(value1) ||(value1==KeyEvent.VK_BACK_SPACE) ||value1==KeyEvent.VK_DELETE)) {
       evt.consume();
    } 
    }//GEN-LAST:event_phn_txtKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         reset();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Ctry_text;
    private javax.swing.JButton Deactivate;
    private javax.swing.JButton Edit;
    private javax.swing.JButton Save;
    private javax.swing.JButton SaveChanges;
    private javax.swing.JLabel addr1;
    private javax.swing.JTextArea addr1_text;
    private javax.swing.JLabel addr2;
    private javax.swing.JTextArea addr2_text;
    private javax.swing.JLabel cit;
    private javax.swing.JTextField city_text;
    private javax.swing.JLabel ctry;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lang;
    private javax.swing.JComboBox lang_cmb;
    private javax.swing.JLabel name;
    private javax.swing.JTextField name_text;
    private javax.swing.JTextField phn_txt;
    private javax.swing.JLabel phno;
    private javax.swing.JLabel pin;
    private javax.swing.JTextField pin_text;
    private javax.swing.JLabel state;
    private javax.swing.JTextField state_text;
    private javax.swing.JComboBox vend_cmb;
    private javax.swing.JLabel vendor;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
        return "Library Publisher Management";
    }

    @Override
    public void activate() throws BasicException {
      
         List<Object> vendtype = new ArrayList<Object>();
         vendtype=getname();
            venmodel = new ComboBoxValModel(vendtype);
            vend_cmb.setModel(venmodel);
             lanmodel = new ComboBoxValModel(getlangname());
            lang_cmb.setModel(lanmodel);
            
         // vend_cmb.setSelectedIndex(-1);
        reset();
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
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.Library.LibDataLogicFacilitiesCreate");
          
    }

    @Override
    public Object getBean() {
        return this;
    }
    
    public void reset(){
        
        name_text.setText(null);
        addr1_text.setText(null);
        addr2_text.setText(null);
        Ctry_text.setText(null);
        city_text.setText(null);
        SaveChanges.setVisible(false);
        state_text.setText(null);
        pin_text.setText(null);
        phn_txt.setText(null);
        Save.setVisible(true);
        lang_cmb.setSelectedIndex(-1);
        vend_cmb.setSelectedIndex(-1);
        jTabbedPane1.setSelectedIndex(0);
        jCheckBox1.setSelected(false);
    }
     private void insertlib_publisher() {
        
         try{
          if(name.getText().length()>0){
             VendorMaster acc=new VendorMaster();
             Object[] param=new Object[]{UUID.randomUUID().toString(),name_text.getText(),addr1_text.getText(),addr2_text.getText(),pin_text.getText(),city_text.getText(),state_text.getText(),Ctry_text.getText(),phn_txt.getText(),getlangId(),getVendId().toString(),m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),true};
             new PreparedSentence(m_App.getSession()
                , "INSERT INTO lib_publisher (ID,NAME,ADDRESS,ADDRESS2,POSTAL,CITY,STATE,COUNTRY,PHONE,language,VENDOR_ID,CREATEDBY,CRDATE,CREATEDHOST,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,/*Datas.STRING,*/Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN})).exec(param);
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
      public List getname() throws BasicException{
          List<Object> vend_list = new ArrayList<Object>();
           vend_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT name FROM lib_vendor  WHERE active=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return vend_list;
      }
      
      public List getlangname() throws BasicException{
          List<Object> lang_list = new ArrayList<Object>();
           lang_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return lang_list;
      }
      public  String getVendId(){
           List<Object> vend_list1 = new ArrayList<Object>();
        try {
            vend_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_vendor  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(vend_cmb.getSelectedItem());
        Name =(String)vend_list1.get(0);
        
        } catch (BasicException ex) {
            Logger.getLogger(PublisherMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return Name;
      }
      
      public  String getlangId(){
           
           List<Object> lan_list1 = new ArrayList<Object>();
        try {
            if(lang_cmb.getSelectedIndex()!=-1){
            lan_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(lang_cmb.getSelectedItem());
            lan =(String)lan_list1.get(0);
            }else{
                lan=" ";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return lan;
      }
      
      private void updatelib_publisher() {
          try{
          if(name.getText().length()>0){
             VendorMaster acc=new VendorMaster();
             Object[] param=new Object[]{name_text.getText(),addr1_text.getText(),addr2_text.getText(),pin_text.getText(),city_text.getText(),state_text.getText(),Ctry_text.getText(),phn_txt.getText(),getlangId(),getVendId(),m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),true,p_id};
             new PreparedSentence(m_App.getSession()
                , "UPDATE lib_publisher SET NAME=?,ADDRESS=?,ADDRESS2=?,POSTAL=?,CITY=?,STATE=?,COUNTRY=?,PHONE=?,language=?,VENDOR_ID=?,CREATEDBY=?,CRDATE=?,CREATEDHOST=?,ACTIVE=? WHERE ID=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,/*Datas.STRING,*/Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.STRING})).exec(param);
          JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
          }
          
           pmodel  = Lib_PublisherTableModel.loadInstance(m_App,1);
           jTable1.setModel(pmodel.getTableModel());
           vend_cmb.setSelectedIndex(-1);
          reset();
          
          
      }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      } 
          
      }
      
       private void deactlib_publisher() {
           try{
            String newpubId = UUID.randomUUID().toString();
           
             new PreparedSentence(m_App.getSession(), "UPDATE lib_publisher  SET ID=?, ACTIVE=0  , DEACTBY=? , DEACTDATE=? , DEACTHOST=?,DEACTREFERENCE=? WHERE ID = ? AND DEACTBY IS NULL AND DEACTDATE IS NULL"
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING,Datas.STRING })).exec
                                                                            (new Object[]{ newpubId, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),deact_id , deact_id  });
            pmodel  = Lib_PublisherTableModel.loadInstance(m_App,1);
            jTable1.setModel(pmodel.getTableModel()); 
            vend_cmb.setSelectedIndex(-1); 
            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           
           
           
       }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
           
       }

}
