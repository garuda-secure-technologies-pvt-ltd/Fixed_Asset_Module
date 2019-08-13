/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.AccountCreatorDialog;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.pos.Library.Lib_vendorTableModel.lib_Vendorline;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.VendorDetailTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Santhosh
 */
public class VendorMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
     private ComboBoxValModel accmodel;
      private DataLogicFacilities dlfac;
       private Lib_vendorTableModel vmodel;
      protected String id;
       private String deact_id;
        
    /**
     * Creates new form LibVendorManagement
     */
    public VendorMaster() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        ContactPerson = new javax.swing.JTextField();
        panno = new javax.swing.JTextField();
        tinno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        contactno = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        account = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        instruction = new javax.swing.JTextArea();
        save = new javax.swing.JButton();
        savechanges = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cstno = new javax.swing.JTextField();
        vendor = new javax.swing.JRadioButton();
        donor = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
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
        jCheckBox1 = new javax.swing.JCheckBox();
        edit = new javax.swing.JButton();
        deactive = new javax.swing.JButton();

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jLabel2.setText("Address");

        jLabel3.setText("Contact Person");

        jLabel4.setText("PAN No");

        jLabel5.setText("TIN No");

        jLabel6.setText("CST No");

        jLabel7.setText("Account");

        jLabel8.setText("Instruction");

        address.setColumns(20);
        address.setRows(5);
        jScrollPane1.setViewportView(address);

        jLabel1.setText("Name");

        jLabel9.setText("Contact No ");

        contactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contactnoKeyTyped(evt);
            }
        });

        jButton2.setText("Create New Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        instruction.setColumns(20);
        instruction.setRows(5);
        jScrollPane2.setViewportView(instruction);

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

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(vendor);
        vendor.setText("Vendor");

        buttonGroup1.add(donor);
        donor.setText("Donar");

        jLabel11.setText("Select the above Person is :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savechanges, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(panno, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(tinno, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                                    .addComponent(cstno))
                                                .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(vendor)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(donor)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(name)
                                    .addComponent(ContactPerson))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(376, 376, 376)
                                .addComponent(contactno, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addGap(49, 49, 49))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, save});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(contactno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tinno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cstno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendor)
                    .addComponent(donor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savechanges)
                    .addComponent(jButton3)
                    .addComponent(save))
                .addContainerGap())
        );

        jTabbedPane2.addTab("CreateNew", jPanel1);

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

        jCheckBox1.setText("Show All");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        deactive.setText("Deactivate");
        deactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(494, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deactive)
                        .addGap(69, 69, 69))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deactive, edit});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 516, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit)
                    .addComponent(deactive))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(95, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("ViewList", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void contactnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactnoKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) ||value==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_contactnoKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try{
            AccountCreatorDialog accdialog= AccountCreatorDialog.getDialog(this, m_App);
            accdialog.showDialog();
            accmodel=new ComboBoxValModel(dlfac.getaccounts());
            account.setModel(accmodel);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        try{
            if(account.getSelectedIndex()!=-1 && name.getText().length()>0){
                int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM LIB_VENDOR WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name.getText()).toString());
            if(count==0){
                if(panno.getText().length()>0 && tinno.getText().length()>0 && cstno.getText().length()>0){
                    if(vendor.isSelected()){
                                   insertlib_Vendor(1);
                                }else{
                                    insertlib_Vendor(2);
                                }
                }else{
                    String missingnos=" ";
                    if(panno.getText().length()<=0)
                    missingnos +="PAN NO ";
                    if(cstno.getText().length()<=0)
                    missingnos +="CST NO ";
                    if(tinno.getText().length()<=0)
                    missingnos +="TIN NO ";
                    //if(donor_txt.getText().length()<=0)
                    //missingnos +="DONOR ";
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        
                        if(vendor.isSelected()){
                                   insertlib_Vendor(1);
                                }else{
                                    insertlib_Vendor(2);
                                }
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "Vendor with the name "+name.getText()+" already exist", null, JOptionPane.OK_OPTION);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please ensure that account and vendor name is not empty", null, JOptionPane.OK_OPTION);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveActionPerformed

    private void savechangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savechangesActionPerformed
        try{
            if(account.getSelectedIndex()!=-1 && name.getText().length()>0){

                if(panno.getText().length()>0 && tinno.getText().length()>0 && cstno.getText().length()>0){
                    if(vendor.isSelected()){
                                    updatelib_Vendor(1);
                                }else{
                                    updatelib_Vendor(2);
                                }
                }else{
                    String missingnos=" ";
                    if(panno.getText().length()<=0)
                    missingnos +="PAN NO ";
                    if(cstno.getText().length()<=0)
                    missingnos +="CST NO ";
                    if(tinno.getText().length()<=0)
                    missingnos +="TIN NO ";
                    //if(donor_txt.getText().length()<=0)
                    //missingnos +="DONOR ";
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        
                        if(vendor.isSelected()){
                                    updatelib_Vendor(1);
                                }else{
                                    updatelib_Vendor(2);
                                }
                    }
                }

            }else{
                JOptionPane.showMessageDialog(this, "Please ensure that account and vendor name is not empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_savechangesActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        
        javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int tabno=tabpane.getSelectedIndex();
        jCheckBox1.setSelected(false);
        if(tabno==1){
            try {
                vmodel = Lib_vendorTableModel.loadInstance(m_App,1);

                jTable1.setModel(vmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
               
                TableColumnModel cmodel=jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(200);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(100);
                cmodel.getColumn(6).setPreferredWidth(100);
                
                
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
            
          }
        
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        
        try {
            if (jCheckBox1.isSelected() == true) {
                vmodel = Lib_vendorTableModel.loadInstance(m_App, 2);
                jTable1.setModel(vmodel.getTableModel());
            } else if (jCheckBox1.isSelected() == false) {
                vmodel = Lib_vendorTableModel.loadInstance(m_App, 1);
                jTable1.setModel(vmodel.getTableModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
       
        if(jTable1.getSelectedRow()!=-1){
            int pub = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(pub == JOptionPane.YES_OPTION){
                if(jTable1.getSelectedRow()<vmodel.getSize()){
                    int row = jTable1.getSelectedRow();
                    if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 11).toString())) {
                   lib_Vendorline data = vmodel.getList().get(row);
                    savechanges.setVisible(true);
                    save.setVisible(false);
                    id=data.getId();
                    String name1=data.getName();
                    String add=data.getAddress();
                    String contnor=data.getCnumber();
                    String contper=data.getCperson();
                    String panno1=data.getPanno();
                    String tinno1=data.getTinno();
                    String cstno1=data.getCstno();
                    String instrn=data.getInstruction();
                    String acct=data.getAccount();
                    int donr=data.getDonor1();
                    
                    name.setText(name1);
                    address.setText(add);
                    contactno.setText(contnor);
                    ContactPerson.setText(contper);
                    panno.setText(panno1);
                    tinno.setText(tinno1);
                    cstno.setText(cstno1);
                    instruction.setText(instrn);
                    //donor_txt.setText(donr);
                     if(name!=null && !name.equals("") ){
                         
                         for(int i=0; i<account.getItemCount(); i++)
                            {
                              if(account.getItemAt(i).toString().equals(acct))
                                {
                                    account.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                         if(donr==1)
                      {
                          vendor.setSelected(true);
                          donor.setSelected(false);
                      }else{
                          vendor.setSelected(false);
                          donor.setSelected(true);
                      }
                     }
                     jTabbedPane2.setSelectedIndex(0);
                }else {
                    JOptionPane.showMessageDialog(this, "selected Vendor is deactivated.cannot edit it");
                }
                }      
            }
        }
        
    }//GEN-LAST:event_editActionPerformed

    private void deactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactiveActionPerformed
        
        if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION){
                  if(jTable1.getSelectedRow() < vmodel.getSize()){
                     int row = jTable1.getSelectedRow(); 
                     if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 11).toString())) {
                     lib_Vendorline showdata = vmodel.getList().get(row);
                     deact_id=showdata.getId();
                     deactivatelib_Vendor();
                     }else {
                    JOptionPane.showMessageDialog(this, "selected Vendor is already deactivated.");
                        }
                  }
             }
         } 
        
    }//GEN-LAST:event_deactiveActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    loadData();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ContactPerson;
    private javax.swing.JComboBox account;
    private javax.swing.JTextArea address;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField contactno;
    private javax.swing.JTextField cstno;
    private javax.swing.JButton deactive;
    private javax.swing.JRadioButton donor;
    private javax.swing.JButton edit;
    private javax.swing.JTextArea instruction;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField panno;
    private javax.swing.JButton save;
    private javax.swing.JButton savechanges;
    private javax.swing.JTextField tinno;
    private javax.swing.JRadioButton vendor;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
       return "Library Vendor Management";
    }

    @Override
    public void activate() throws BasicException {
        accmodel=new ComboBoxValModel(dlfac.getaccounts());
        account.setModel(accmodel);
        loadData();
    }
     private void loadData() {
        
        name.setText(null);
        address.setText(null);
        contactno.setText(null);
        ContactPerson.setText(null);
        panno.setText(null);
        tinno.setText(null);
        cstno.setText(null);
        instruction.setText(null);
        account.setSelectedIndex(-1);
        savechanges.setVisible(false);
        save.setVisible(true);
        jTabbedPane2.setSelectedIndex(0);
        jCheckBox1.setSelected(false);
        vendor.setSelected(true);
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
    public String getvenid(){
        return id;
    }

    private void insertlib_Vendor(int flag) {
        
         try{
          if(name.getText().length()>0){
             AccountMaster acc=(AccountMaster)account.getSelectedItem();
             Object[] param=new Object[]{UUID.randomUUID().toString(),true,name.getText(),address.getText(),ContactPerson.getText(),contactno.getText(),panno.getText(),tinno.getText(),cstno.getText(),acc.getid(),instruction.getText(),m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),flag};
             new PreparedSentence(m_App.getSession()
                , "INSERT INTO LIB_VENDOR (ID,ACTIVE,NAME,ADDRESS,CONTACTPERSON,CONTACTNUM,PANNO,TINNO,CST,ACCOUNT,INSTRUCTION,CREATEDBY,CRDATE,CREATEDHOST,Vendflag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT})).exec(param);
          JOptionPane.showMessageDialog(this, "Created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
          }
          
          loadData();
          
      }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
        
        
    }
     private void updatelib_Vendor(int flag){
         try{
         if(name.getText().length()>0){
             AccountMaster acc=(AccountMaster)account.getSelectedItem();
             Object[] param1=new Object[]{true,name.getText(),address.getText(),ContactPerson.getText(),contactno.getText(),panno.getText(),tinno.getText(),cstno.getText(),acc.getid(),instruction.getText(),m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),flag,id};
             new PreparedSentence(m_App.getSession()
                , "UPDATE lib_vendor SET ACTIVE=?,NAME=?,ADDRESS=?,CONTACTPERSON=?,CONTACTNUM=?,PANNO=?,TINNO=?,CST=?,ACCOUNT=?,INSTRUCTION=?,CREATEDBY=?,CRDATE=?,CREATEDHOST=?,Vendflag=? WHERE ID=?"
                , new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.INT,Datas.STRING})).exec(param1);
          JOptionPane.showMessageDialog(this, "UPDATED Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
             
         }
       vmodel  = Lib_vendorTableModel.loadInstance(m_App,1);
       jTable1.setModel(vmodel.getTableModel()); 
       account.setSelectedIndex(-1);
          loadData();
             }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
         
         
     }
      private void deactivatelib_Vendor(){
       try{
           String newvendId = UUID.randomUUID().toString();
           
             new PreparedSentence(m_App.getSession(), "UPDATE lib_vendor  SET ID=?, ACTIVE=0  , DEACTBY=? , DEACTDATE=? , DEACHOST=?,DEACTREFERENCE=? WHERE ID = ? AND DEACTBY IS NULL AND DEACTDATE IS NULL "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING,Datas.STRING })).exec
                                                                            (new Object[]{ newvendId, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),deact_id , deact_id  });
            vmodel  = Lib_vendorTableModel.loadInstance(m_App,1);
       jTable1.setModel(vmodel.getTableModel()); 
       account.setSelectedIndex(-1); 
             loadData();
          JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           
           
           
       }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
         
      }
    
}
