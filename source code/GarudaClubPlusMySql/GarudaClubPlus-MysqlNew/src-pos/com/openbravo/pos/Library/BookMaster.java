/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Booking.BookHall;
import com.openbravo.pos.Library.Lib_BookTableModel.lib_Bookline;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Santhosh
 */
public class BookMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
     private ComboBoxValModel autModel,pubModel,langModel,catModel;
     Lib_BookTableModel bmodel;
    private String c_id;
    private String deact_id;
    private String Author;
    private String catgr;
    private String publ;
    private String lang;
    private ComboBoxValModel issueModel;
    private ComboBoxValModel mediaModel;
    private String medi1;
    private String medi;
    private ComboBoxValModel vendoModel;
    private String vendo;
    

    /** Creates new form BookMaster */
    public BookMaster() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        namebook_txt = new javax.swing.JTextField();
        ref_txt = new javax.swing.JTextField();
        edition_txt = new javax.swing.JTextField();
        alloc_txt = new javax.swing.JTextField();
        copies_txt = new javax.swing.JTextField();
        sms_txt = new javax.swing.JTextField();
        aut_com = new javax.swing.JComboBox();
        pub_com = new javax.swing.JComboBox();
        lang_com = new javax.swing.JComboBox();
        cat_com = new javax.swing.JComboBox();
        save = new javax.swing.JButton();
        savechanges = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        keywd_txt = new javax.swing.JTextArea();
        issue_com = new javax.swing.JComboBox();
        media_com = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        vendonr = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        yes = new javax.swing.JRadioButton();
        no = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        edit = new javax.swing.JButton();
        deactv = new javax.swing.JButton();
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

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel14.setText("Name");

        jLabel15.setText("Reff-No");

        jLabel16.setText("Author");

        jLabel17.setText("Publisher");

        jLabel18.setText("Language");

        jLabel19.setText("Category");

        jLabel20.setText("Edition");

        jLabel21.setText("KeyWords");

        jLabel22.setText("Media");

        jLabel23.setText("Allocated Nos.");

        jLabel24.setText("Nos. of Copies");

        jLabel25.setText("Issue Rules");

        jLabel26.setText("SMS Short Form");

        namebook_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namebook_txtActionPerformed(evt);
            }
        });

        ref_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ref_txtActionPerformed(evt);
            }
        });

        alloc_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                alloc_txtKeyTyped(evt);
            }
        });

        copies_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                copies_txtKeyTyped(evt);
            }
        });

        aut_com.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pub_com.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pub_com.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pub_comActionPerformed(evt);
            }
        });

        lang_com.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cat_com.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        savechanges.setText("Savachanges");
        savechanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savechangesActionPerformed(evt);
            }
        });

        keywd_txt.setColumns(20);
        keywd_txt.setRows(5);
        jScrollPane1.setViewportView(keywd_txt);

        issue_com.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        media_com.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Vendor/Donor");

        vendonr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Book Available");

        buttonGroup2.add(yes);
        yes.setText("Yes");
        yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesActionPerformed(evt);
            }
        });

        buttonGroup2.add(no);
        no.setText("No");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel22)
                            .add(jLabel24)
                            .add(jLabel26)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel15)
                                    .add(jLabel16)
                                    .add(jPanel3Layout.createSequentialGroup()
                                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel19)
                                            .add(jLabel18)
                                            .add(jLabel14)
                                            .add(jLabel23))
                                        .add(23, 23, 23)
                                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(copies_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jPanel3Layout.createSequentialGroup()
                                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, media_com, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, sms_txt)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, alloc_txt)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, aut_com, 0, 286, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, ref_txt)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, namebook_txt)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, pub_com, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, lang_com, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, cat_com, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(org.jdesktop.layout.GroupLayout.LEADING, edition_txt))
                                                .add(46, 46, 46)
                                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(jLabel21)
                                                    .add(jLabel25)
                                                    .add(jLabel1)))
                                            .add(jPanel3Layout.createSequentialGroup()
                                                .add(yes)
                                                .add(27, 27, 27)
                                                .add(no))))
                                    .add(jLabel17)
                                    .add(jLabel20))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                                    .add(issue_com, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(vendonr, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(43, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(save, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(savechanges)
                        .add(66, 66, 66))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel2)
                        .add(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel14)
                            .add(namebook_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel21))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel15)
                            .add(ref_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(11, 11, 11)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel16)
                            .add(aut_com, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel17)
                    .add(pub_com, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel25)
                    .add(issue_com, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(lang_com, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(vendonr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel19)
                    .add(cat_com, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel20)
                    .add(edition_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(media_com, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel23)
                    .add(alloc_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(copies_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel26)
                    .add(sms_txt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(yes)
                    .add(no))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 127, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(savechanges)
                    .add(jButton1)
                    .add(save))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CreateNew", jPanel1);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        deactv.setText("Deactivate");
        deactv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactvActionPerformed(evt);
            }
        });

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

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap(586, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(edit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deactv)
                        .add(143, 143, 143))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jCheckBox1)
                        .add(35, 35, 35))))
            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel4Layout.createSequentialGroup()
                    .add(22, 22, 22)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                    .add(23, 23, 23)))
        );

        jPanel4Layout.linkSize(new java.awt.Component[] {deactv, edit}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 555, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(edit)
                    .add(deactv))
                .addContainerGap())
            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel4Layout.createSequentialGroup()
                    .add(55, 55, 55)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 447, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(130, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("View", jPanel4);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        
        javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int tabno=tabpane.getSelectedIndex();
        jCheckBox1.setSelected(false);
        if(tabno==1){
            try {
                bmodel = Lib_BookTableModel.loadInstance(m_App,1);

                jTable1.setModel(bmodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
               
                TableColumnModel cmodel=jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(200);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(100);
                cmodel.getColumn(6).setPreferredWidth(100);
                cmodel.getColumn(7).setPreferredWidth(100);
                
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
            
          }
        
       
    
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
       
        
        if(jTable1.getSelectedRow()!=-1){
            int pub = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(pub == JOptionPane.YES_OPTION){
                if(jTable1.getSelectedRow()<bmodel.getSize()){
                    int row = jTable1.getSelectedRow();
                    if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 13).toString())) {
                   lib_Bookline data = bmodel.getList().get(row);
                    savechanges.setVisible(true);
                    save.setVisible(false);
                    c_id=data.getId();
                    String name1=data.getName();
                    String ref=data.getRefno();
                    String auth=data.getAuthor();
                    String publ=data.getPublisher();
                    String lang=data.getLanguage();
                    String catg=data.getCategory();
                    String editn=data.getEdition();
                    String alloc=data.getAllocatedno();
                    String keywd=data.getKeywords();
                    String media=data.getMedia();
                    String issuerules1=data.getIssuerules();
                    String sms=data.getSms();
                    String copies=data.getNocopies();
                    String vflg=data.getVendfg();
                    int flg=data.getAvlfg();
                    
                     namebook_txt.setText(name1);
                     ref_txt.setText(ref);
                     edition_txt.setText(editn);
                     keywd_txt.setText(keywd);
                    // media_txt.setText(media);
                     alloc_txt.setText(alloc);
                     copies_txt.setText(copies);
                     //Issue_txt.setText(issuerules1);
                     sms_txt.setText(sms);
                     if(name1!=null && !name1.equals("") ){
                         
                         for(int i=0; i<aut_com.getItemCount(); i++)
                            {
                              if(aut_com.getItemAt(i).toString().equals(auth))
                                {
                                    aut_com.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<pub_com.getItemCount(); i++)
                            {
                              if(pub_com.getItemAt(i).toString().equals(publ))
                                {
                                    pub_com.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<lang_com.getItemCount(); i++)
                            {
                              if(lang_com.getItemAt(i).toString().equals(lang))
                                {
                                    lang_com.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<cat_com.getItemCount(); i++)
                            {
                              if(cat_com.getItemAt(i).toString().equals(catg))
                                {
                                    cat_com.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<issue_com.getItemCount(); i++)
                            {
                              if(issue_com.getItemAt(i).toString().equals(issuerules1))
                                {
                                    issue_com.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      List<Object> medi_list = new ArrayList<Object>();
                       try {
                           medi_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_media m WHERE m.id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(media);
                       } catch (BasicException ex) {
                           Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
                       }
                      for(int i=0; i<media_com.getItemCount(); i++)
                            {
                                if(medi_list.size()!=0){
                              if(media_com.getItemAt(i).toString().equals(medi_list.get(0).toString()))
                                {
                                    media_com.setSelectedIndex(i);
                                    break;
                                }
                                }else{
                                  media_com.setSelectedIndex(-1);
                                    break;
                              }
                            }
                       List<Object> vend_list = new ArrayList<Object>();
                       try {
                           vend_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT v.name FROM lib_vendor v WHERE v.id=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(vflg);
                       } catch (BasicException ex) {
                           Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
                       }
                      
                      for(int i=0; i<vendonr.getItemCount(); i++)
                            {
                                if(vend_list.size()!=0){
                              if(vendonr.getItemAt(i).toString().equals(vend_list.get(0).toString()))
                                {
                                    vendonr.setSelectedIndex(i);
                                    break;
                                }
                              }else{
                                  vendonr.setSelectedIndex(-1);
                                    break;
                              }
                            }
                      if(flg==1){
                          yes.setSelected(true);
                      }
                      else{
                          no.setSelected(true);
                      }
                     }
                    
                      jTabbedPane1.setSelectedIndex(0);
                }else {
                    JOptionPane.showMessageDialog(this, "selected Book is deactivated.cannot edit it");
                }
                }      
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void deactvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactvActionPerformed
       
        if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION){
                  if(jTable1.getSelectedRow() < bmodel.getSize()){
                     int row = jTable1.getSelectedRow(); 
                     if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 13).toString())) {
                     lib_Bookline showdata = bmodel.getList().get(row);
                     deact_id=showdata.getId();
                     deactcat_book();
                     }else {
                    JOptionPane.showMessageDialog(this, "selected Book is already deactivated.");
                        }
                  }
             }
         } 
        
    }//GEN-LAST:event_deactvActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       
        try {
            if (jCheckBox1.isSelected() == true) {
                bmodel = Lib_BookTableModel.loadInstance(m_App, 2);
                jTable1.setModel(bmodel.getTableModel());
            } else if(jCheckBox1.isSelected() == false) {
                bmodel = Lib_BookTableModel.loadInstance(m_App, 1);
                jTable1.setModel(bmodel.getTableModel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void savechangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savechangesActionPerformed

        try{
            if( namebook_txt.getText().length()>0 ){

                    String missingnos=" ";
                    if(ref_txt.getText().length()<=0)
                    missingnos +="RefNo ";
                    if(edition_txt.getText().length()<=0)
                    missingnos +="Edition ";
                    if(keywd_txt.getText().length()<=0)
                    missingnos +="Keywords ";
                    if(alloc_txt.getText().length()<=0)
                    missingnos +="AlloatedNor ";
                    if(copies_txt.getText().length()<=0)
                    missingnos +="Nor of Copies ";
                    if(sms_txt.getText().length()<=0)
                    missingnos +="SMS ";

                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        if(yes.isSelected())
                                    updatelib_book(1);
                               else
                                 updatelib_book(0);  
                     }
                    // }

            }else{
                JOptionPane.showMessageDialog(this, "Please ensure that Book Name is not empty", null, JOptionPane.OK_OPTION);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_savechangesActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        try{
            if( namebook_txt.getText().length()>0 ){
                if(aut_com.getSelectedIndex()!=-1 && pub_com.getSelectedIndex()!=-1 && cat_com.getSelectedIndex()!=-1 && lang_com.getSelectedIndex()!=-1 && copies_txt.getText().length()>0 ){
                    if( issue_com.getSelectedIndex()!=-1 ){
                        int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM lib_bookmaster WHERE NAME=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(namebook_txt.getText()).toString());
                    if(count==0){
                            String missingnos=" ";
                            if(ref_txt.getText().length()<=0)
                            missingnos +="RefNo ";
                            if(edition_txt.getText().length()<=0)
                            missingnos +="Edition ";
                            if(keywd_txt.getText().length()<=0)
                            missingnos +="Keywords ";
                            if(alloc_txt.getText().length()<=0)
                            missingnos +="AlloatedNor ";
                           // if(copies_txt.getText().length()<=0)
                            //missingnos +="Nor of Copies ";
                            if(sms_txt.getText().length()<=0)
                            missingnos +="SMS ";

                            if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                               if(yes.isSelected())
                                    insertlib_Book(1);
                               else
                                 insertlib_Book(0);  
                             }
                    }else{
                        JOptionPane.showMessageDialog(this, "Book with the name "+namebook_txt.getText()+" already exist", null, JOptionPane.OK_OPTION);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Please ensure that IssueRules is not empty", null, JOptionPane.OK_OPTION);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Please ensure that Author and publisher and category and language and nor. of copies is not empty", null, JOptionPane.OK_OPTION);
            }

        }else{
            JOptionPane.showMessageDialog(this, "Please ensure that Book Name is not empty", null, JOptionPane.OK_OPTION);
        }

        }catch(Exception e){
            e.printStackTrace();
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, e);
                       new MessageInf(e).show(new JFrame());
            
        }

    }//GEN-LAST:event_saveActionPerformed

    private void pub_comActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pub_comActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pub_comActionPerformed

    private void copies_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_copies_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) ||value==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_copies_txtKeyTyped

    private void alloc_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alloc_txtKeyTyped
        char value1 = evt.getKeyChar();
        if (!(Character.isDigit(value1) ||(value1==KeyEvent.VK_BACK_SPACE) ||value1==KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_alloc_txtKeyTyped

    private void ref_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ref_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ref_txtActionPerformed

    private void yesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yesActionPerformed

    private void namebook_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namebook_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namebook_txtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alloc_txt;
    private javax.swing.JComboBox aut_com;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cat_com;
    private javax.swing.JTextField copies_txt;
    private javax.swing.JButton deactv;
    private javax.swing.JButton edit;
    private javax.swing.JTextField edition_txt;
    private javax.swing.JComboBox issue_com;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea keywd_txt;
    private javax.swing.JComboBox lang_com;
    private javax.swing.JComboBox media_com;
    private javax.swing.JTextField namebook_txt;
    private javax.swing.JRadioButton no;
    private javax.swing.JComboBox pub_com;
    private javax.swing.JTextField ref_txt;
    private javax.swing.JButton save;
    private javax.swing.JButton savechanges;
    private javax.swing.JTextField sms_txt;
    private javax.swing.JComboBox vendonr;
    private javax.swing.JRadioButton yes;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
         return "Library Books Management";
    }

    @Override
    public void activate() throws BasicException {
        
       autModel=new ComboBoxValModel(getautname());
       aut_com.setModel(autModel);
       pubModel=new ComboBoxValModel(getpubname());
       pub_com.setModel(pubModel);
       catModel=new ComboBoxValModel(getcatname());
       cat_com.setModel(catModel);
       langModel=new ComboBoxValModel(getlangname());
       lang_com.setModel(langModel);
       issueModel=new ComboBoxValModel(getissuename());
       issue_com.setModel(issueModel);
       mediaModel=new ComboBoxValModel(getmedianame());
       media_com.setModel(mediaModel);
       vendoModel=new ComboBoxValModel(getven_donname());
       vendonr.setModel(vendoModel);
       
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
    }

    @Override
    public Object getBean() {
         return this;
    }
     public void reset(){
         
         namebook_txt.setText(null);
         ref_txt.setText(null);
         edition_txt.setText(null);
         keywd_txt.setText(null);
         alloc_txt.setText(null);
         copies_txt.setText(null);
         sms_txt.setText(null);
         aut_com.setSelectedIndex(-1);
         issue_com.setSelectedIndex(-1);
         pub_com.setSelectedIndex(-1);
         lang_com.setSelectedIndex(-1);
         media_com.setSelectedIndex(-1);
         cat_com.setSelectedIndex(-1);
         save.setVisible(true);
         savechanges.setVisible(false);
         jTabbedPane1.setSelectedIndex(0);
         jCheckBox1.setSelected(false);
         vendonr.setSelectedIndex(-1);
         yes.setSelected(true);
         
     }
      public List getcatname() throws BasicException{
          List<Object> catg_list = new ArrayList<Object>();
           catg_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return catg_list;
      }
      public List getautname() throws BasicException{
          List<Object> aut_list = new ArrayList<Object>();
           aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_author c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return aut_list;
      }
      public List getpubname() throws BasicException{
          List<Object> pub_list = new ArrayList<Object>();
           pub_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_publisher c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return pub_list;
      }
      public List getlangname() throws BasicException{
          List<Object> lang_list = new ArrayList<Object>();
           lang_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return lang_list;
      }
      
      public List getissuename() throws BasicException{
          List<Object> issue_list = new ArrayList<Object>();
           issue_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.name FROM lib_issuerules i WHERE i.active=1 ORDER by i.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return issue_list;
      }
      
      public List getmedianame() throws BasicException{
          List<Object> media_list = new ArrayList<Object>();
           media_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_media m WHERE m.active=1 ORDER by m.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return media_list;
      }
      
      public List getven_donname() throws BasicException{
          List<Object> vend_list = new ArrayList<Object>();
           vend_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_vendor m WHERE m.active=1 ORDER by m.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return vend_list;
      }
     
      public  String getautId(){
           
           List<Object> auth_list1 = new ArrayList<Object>();
        try {
            if(aut_com.getSelectedIndex()!=-1){
            auth_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT a.id FROM lib_author a  WHERE a.name=? and a.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(aut_com.getSelectedItem());
        Author =(String)auth_list1.get(0);
            }else{
                Author="null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return Author;
      }
      public  String getcatId(){
           
           List<Object> cat_list1 = new ArrayList<Object>();
        try {
            if(cat_com.getSelectedIndex()!=-1){
            cat_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.id FROM lib_categories c  WHERE c.name=? and c.ACTIVE=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(cat_com.getSelectedItem());
        catgr =(String)cat_list1.get(0);
            }else{
                catgr="null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return catgr;
      }
      
      public  String getpubId(){
           
           List<Object> pub_list1 = new ArrayList<Object>();
        try {
            if(pub_com.getSelectedIndex()!=-1){
            pub_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.id FROM lib_publisher c  WHERE c.name=? and c.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(pub_com.getSelectedItem());
            publ =(String)pub_list1.get(0);
            }else{
                publ="null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return publ;
      }
      
      public  String getlangId(){
           
           List<Object> lan_list1 = new ArrayList<Object>();
        try {
            if(lang_com.getSelectedIndex()!=-1){
            lan_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(lang_com.getSelectedItem());
            lang =(String)lan_list1.get(0);
            }else{
                lang="null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return lang;
      }
      public  String getissueId(){
           
           List<Object> iss_list1 = new ArrayList<Object>();
        try {
            if(issue_com.getSelectedIndex()!=-1){
            iss_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_issuerules i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(issue_com.getSelectedItem());
            medi1 =(String)iss_list1.get(0);
            }else{
                medi1="null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return medi1;
      }
      public  String getmediaId(){
           
           List<Object> iss_list1 = new ArrayList<Object>();
        try {
            if(media_com.getSelectedIndex()!=-1){
            iss_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_media i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(media_com.getSelectedItem());
            medi =(String)iss_list1.get(0);
            }else{
                medi="null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return medi;
      }
      
      public  String getven_doId(){
           
           List<Object> iss_list1 = new ArrayList<Object>();
        try {
            if(vendonr.getSelectedIndex()!=-1){
            iss_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_vendor i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(vendonr.getSelectedItem());
            vendo =(String)iss_list1.get(0);
            }else{
                vendo=" null";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return vendo;
      }
      
      private void insertlib_Book(int flag) {
        
         try{
          if(namebook_txt.getText().length()>0){
              
             Object[] param=new Object[]{UUID.randomUUID().toString(),ref_txt.getText(),namebook_txt.getText(),getautId(),getpubId(),getlangId(),sms_txt.getText(),getcatId(),edition_txt.getText(),keywd_txt.getText(),getmediaId(),getissueId(),alloc_txt.getText(),copies_txt.getText(),true,m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),getven_doId(),flag};
             new PreparedSentence(m_App.getSession()
                , "INSERT INTO lib_bookmaster (ID,RefNo,NAME,Author,Publisher,language,sms,Category,Edition,Keywords,Media,IssueRules,AllocatedNor,copies,ACTIVE,CREATEDBY,CREATEDATE,CREATEDHOST,Vend_doid,available_flag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.INT})).exec(param);
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
      
       private void updatelib_book(int flag) {
           
           try{
          if(namebook_txt.getText().length()>0){
           
             Object[] param=new Object[]{ref_txt.getText(),namebook_txt.getText(),getautId(),getpubId(),getlangId(),sms_txt.getText(),getcatId(),edition_txt.getText(),keywd_txt.getText(),getmediaId(),getissueId(),alloc_txt.getText(),copies_txt.getText(),true,m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),getven_doId(),flag,c_id};
             new PreparedSentence(m_App.getSession()
                , "UPDATE lib_bookmaster SET RefNo=?,NAME=?,Author=?,Publisher=?,language=?,sms=?,Category=?,Edition=?,Keywords=?,Media=?,IssueRules=?,AllocatedNor=?,copies=?,ACTIVE=?,CREATEDBY=?,CREATEDATE=?,CREATEDHOST=?,Vend_doid=?,available_flag=? WHERE ID=? "
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING})).exec(param);
            JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
           }

           bmodel  = Lib_BookTableModel.loadInstance(m_App,1);
           jTable1.setModel(bmodel.getTableModel()); 
           reset(); 
              
         }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
           
           
       }
       
       private void deactcat_book() {
           try{
           String newbookId = UUID.randomUUID().toString();
           
             new PreparedSentence(m_App.getSession(), "UPDATE lib_bookmaster  SET ID=?, ACTIVE=0  , DEACBY=? , DEACDATE=? , DEACHOST=?,DEACTREFERENCE=? WHERE ID = ? AND DEACBY IS NULL AND DEACDATE IS NULL"
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING , Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{ newbookId, m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),deact_id , deact_id  });
       bmodel  = Lib_BookTableModel.loadInstance(m_App,1);
       jTable1.setModel(bmodel.getTableModel()); 
       
       reset();
       JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
       autModel=new ComboBoxValModel(getautname());
       aut_com.setModel(autModel);
       pubModel=new ComboBoxValModel(getpubname());
       pub_com.setModel(pubModel);
       catModel=new ComboBoxValModel(getcatname());
       cat_com.setModel(catModel);
       issueModel=new ComboBoxValModel(getissuename());
       issue_com.setModel(issueModel);
       mediaModel=new ComboBoxValModel(getmedianame());
       media_com.setModel(mediaModel);
           
           
       }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
    }

}
