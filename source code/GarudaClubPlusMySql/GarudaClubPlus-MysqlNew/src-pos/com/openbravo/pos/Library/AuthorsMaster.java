/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Library.Lib_AuthorTableModel.lib_Authorline;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Santhosh
 */
public class AuthorsMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    private Lib_AuthorTableModel amodel;
     private ComboBoxValModel lagmodel;
     private ComboBoxValModel catmodel;
      Date d=null;
    private String a_id;
    JFileChooser filechooser1=new javax.swing.JFileChooser();
    File file;
     String fname,path;
    private String deact_id;
    private String catName;
    private String lang1;
    
      
      /** Creates new form AuthorsMaster */
    public AuthorsMaster() {
        initComponents();
        String datelist[] = new String[]{null, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String monthlist[] = new String[]{null, "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        date.setModel(new javax.swing.DefaultComboBoxModel(datelist));
        month.setModel(new javax.swing.DefaultComboBoxModel(monthlist));
       
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        name_text = new javax.swing.JTextField();
        city_text = new javax.swing.JTextField();
        regn_text = new javax.swing.JTextField();
        cnty_text = new javax.swing.JTextField();
        phn_text = new javax.swing.JTextField();
        email_text = new javax.swing.JTextField();
        wrt_text = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        add_text = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        catog_box = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        lang_box = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        photo_text = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        notes_text = new javax.swing.JTextArea();
        save = new javax.swing.JButton();
        savechanges = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        date = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        year = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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

        jLabel3.setText("City");

        jLabel4.setText("Region");

        jLabel5.setText("Country");

        jLabel6.setText("Contact No");

        jLabel7.setText("D.O.B");

        jLabel8.setText("Email");

        jLabel9.setText("WriteUp");

        regn_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regn_textActionPerformed(evt);
            }
        });

        phn_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phn_textActionPerformed(evt);
            }
        });
        phn_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phn_textKeyTyped(evt);
            }
        });

        email_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_textActionPerformed(evt);
            }
        });

        add_text.setColumns(20);
        add_text.setRows(5);
        jScrollPane1.setViewportView(add_text);

        jLabel10.setText("Categories");

        catog_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Language");

        lang_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Notes");

        jLabel13.setText("Photo");

        photo_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                photo_textActionPerformed(evt);
            }
        });

        notes_text.setColumns(20);
        notes_text.setRows(5);
        jScrollPane4.setViewportView(notes_text);

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

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        date.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yearKeyTyped(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(jLabel2)
                                    .add(jLabel3))
                                .add(33, 33, 33)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .add(city_text)
                                    .add(name_text)))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel5)
                                    .add(jLabel7)
                                    .add(jLabel13)
                                    .add(jLabel12))
                                .add(32, 32, 32)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(catog_box, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(27, 27, 27)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel11)
                                            .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(cnty_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(30, 30, 30)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel6)
                                            .add(jLabel9)
                                            .add(jLabel4))
                                        .add(18, 18, 18)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(regn_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 284, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(phn_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 284, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(wrt_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 284, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(lang_box, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 284, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel10)
                                    .add(jLabel8))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(photo_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(date, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(month, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(year, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(email_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(save, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(savechanges)
                        .add(103, 103, 103))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(name_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(13, 13, 13)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(city_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(regn_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(cnty_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6)
                    .add(phn_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jLabel9)
                    .add(wrt_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(date, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(month, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(year, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(email_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(11, 11, 11)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(catog_box, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10)
                    .add(jLabel11)
                    .add(lang_box, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(photo_text, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel12)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 36, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(savechanges)
                    .add(jButton2)
                    .add(save))
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
        jScrollPane3.setViewportView(jTable1);

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
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                .add(edit)
                                .add(18, 18, 18)
                                .add(deactivate)
                                .add(132, 132, 132))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                .add(jCheckBox1)
                                .add(61, 61, 61))))))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {deactivate, edit}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jCheckBox1)
                .add(25, 25, 25)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 425, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(edit)
                    .add(deactivate))
                .addContainerGap())
        );

        jTabbedPane1.addTab("View", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 842, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 579, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        
        if(jTable1.getSelectedRow()!=-1){
            int pub = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
            if(pub == JOptionPane.YES_OPTION){
                if(jTable1.getSelectedRow()<amodel.getSize()){
                    int row = jTable1.getSelectedRow();
                    if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 16).toString())) {
                    lib_Authorline data = amodel.getList().get(row);
                    savechanges.setVisible(true);
                    save.setVisible(false);
                    a_id=data.getId();
                    String name1=data.getName();
                    String add=data.getAddress();
                    String email=data.getEmail();
                    String cntry=data.getCountry();
                    String city1=data.getCity();
                    String regon=data.getRegion();
                    String photo=data.getPhoto();
                    String phno1=data.getPhone();
                    String nots=data.getNotes();
        
                    String wrtup=data.getWriteup();
                    String catg=data.getCategories();
                    String lang=data.getLanguage();
                    Date dob=data.getDob();
                     
                        if(dob!=null && !dob.equals("")){
            
                           Date d= (Date)dob;
                             Calendar cal=GregorianCalendar.getInstance();
                             cal.setTimeInMillis(d.getTime());
                             date.setSelectedIndex(cal.get(Calendar.DATE));
                             month.setSelectedIndex(cal.get(Calendar.MONTH)+1);
                             year.setText(String.valueOf(cal.get(Calendar.YEAR)));
                            }else{
                             date.setSelectedIndex(-1);
                             month.setSelectedIndex(-1);
                             year.setText(null);
                             }
                        
                    name_text.setText(name1);
                    add_text.setText(add);
                    city_text.setText(city1);
                    regn_text.setText(regon);
                    cnty_text.setText(cntry);
                    phn_text.setText(phno1);       
                    for(int i=0; i<catog_box.getItemCount(); i++)
                            {
                              if(catog_box.getItemAt(i).toString().equals(catg))
                                {
                                    catog_box.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                    for(int i=0; i<lang_box.getItemCount(); i++)
                            {
                              if(lang_box.getItemAt(i).toString().equals(lang))
                                {
                                    lang_box.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                    email_text.setText(email);
                    wrt_text.setText(wrtup);
                    notes_text.setText(nots);
                    photo_text.setText(photo);
                    jTabbedPane1.setSelectedIndex(0);
                    }else {
                    JOptionPane.showMessageDialog(this, "selected Author is deactivated.cannot edit it");
                }
                }
            } 
        }
    }//GEN-LAST:event_editActionPerformed

    private void photo_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_photo_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_photo_textActionPerformed

    private void regn_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regn_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regn_textActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
      try{
        if(name_text.getText().length()>0 && catog_box.getSelectedIndex()!=-1 && lang_box.getSelectedIndex()!=-1){
             
              if(date.getSelectedIndex()>0 && month.getSelectedIndex()>0 && year.getText().length()>0)
             d= getDate(date.getSelectedItem().toString(),month.getSelectedIndex(),year.getText());
              
              int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                    , "SELECT COUNT(*) FROM LIB_AUTHOR WHERE NAME=? AND ACTIVE=TRUE"
                    ,SerializerWriteString.INSTANCE
                    ,SerializerReadInteger.INSTANCE).find(name_text.getText()).toString());  
                if(count==0){
                   if(cnty_text.getText().length()>0 && regn_text.getText().length()>0 && city_text.getText().length()>0 && wrt_text.getText().length()>0 && catog_box.getSelectedIndex()!=-1){
                    insertlib_author();
                } else{
                    String missingnos=" ";
                    if(cnty_text.getText().length()<=0)
                    missingnos +="CNTRY ";
                    if(regn_text.getText().length()<=0)
                    missingnos +="REGION ";
                    if(city_text.getText().length()<=0)
                    missingnos +="CITY ";
                    if(wrt_text.getText().length()<=0)
                    missingnos +="WriteUp ";
                    if(email_text.getText().length()<=0)
                    missingnos +="EMAIL ";
                    if(photo_text.getText().length()<=0)
                    missingnos +="PHOTO ";
                   
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        insertlib_author();
                    }
                   }
                    
                }else{
                JOptionPane.showMessageDialog(this, "Author with the name "+name_text.getText()+" already exist", null, JOptionPane.OK_OPTION);
            }
            }else{
            JOptionPane.showMessageDialog(this, "Please ensure that Author name and Category and language is not empty", null, JOptionPane.OK_OPTION);
        }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_saveActionPerformed

    private void email_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_textActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        
        javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int tabno=tabpane.getSelectedIndex();
       jCheckBox1.setSelected(false);
        if(tabno==1){
            try {
                amodel = Lib_AuthorTableModel.loadInstance(m_App,1);

                jTable1.setModel(amodel.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

                TableColumnModel cmodel=jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(200);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(60);
                cmodel.getColumn(4).setPreferredWidth(60);
                cmodel.getColumn(5).setPreferredWidth(60);
                cmodel.getColumn(6).setPreferredWidth(100);
                cmodel.getColumn(7).setPreferredWidth(100);
                cmodel.getColumn(8).setPreferredWidth(100);
                cmodel.getColumn(9).setPreferredWidth(100);
                cmodel.getColumn(10).setPreferredWidth(100);
                
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
       filechooser1.setName("filechooser");
       filechooser1.setDragEnabled(true);
       filechooser1.setFileFilter(filter);
       int returnVal = filechooser1.showOpenDialog(null);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
       file = filechooser1.getSelectedFile();
       fname=file.getPath();
       photo_text.setText(fname);
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void savechangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savechangesActionPerformed
        try{
         if( name_text.getText().length()>0){
             if(date.getSelectedIndex()>0 && month.getSelectedIndex()>0 && year.getText().length()>0)
             d= getDate(date.getSelectedItem().toString(),month.getSelectedIndex(),year.getText()); 
             if(cnty_text.getText().length()>0 && regn_text.getText().length()>0 && city_text.getText().length()>0 && wrt_text.getText().length()>0){
                    updatelib_author();
                } else{
                    String missingnos=" ";
                    if(cnty_text.getText().length()<=0)
                    missingnos +="CNTRY ";
                    if(regn_text.getText().length()<=0)
                    missingnos +="REGION ";
                    if(city_text.getText().length()<=0)
                    missingnos +="CITY ";
                    if(wrt_text.getText().length()<=0)
                    missingnos +="WriteUp ";
                    if(email_text.getText().length()<=0)
                    missingnos +="EMAIL ";
                    if(photo_text.getText().length()<=0)
                    missingnos +="PHOTO ";
                    if(JOptionPane.showConfirmDialog(this, missingnos+"missing.Do you want to continue", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        updatelib_author();
                    }
                   }
                    
                
            }else{
            JOptionPane.showMessageDialog(this, "Please ensure that categories and Author name is not empty", null, JOptionPane.OK_OPTION);
        }
             
        }catch(Exception e){
            e.printStackTrace();
        }   
        
    }//GEN-LAST:event_savechangesActionPerformed

    private void deactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivateActionPerformed
        
         if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION){
                  if(jTable1.getSelectedRow()<amodel.getSize()){
                     int row = jTable1.getSelectedRow();
                     if (Boolean.valueOf(jTable1.getModel().getValueAt(row, 16).toString())) {
                     lib_Authorline showdata = amodel.getList().get(row);
                     deact_id=showdata.getId();
                     deactlib_author();
                     }else {
                    JOptionPane.showMessageDialog(this, "selected Author is already deactivated.");
                }
                  }
             }
         }
        
    }//GEN-LAST:event_deactivateActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        
       try {
            if (jCheckBox1.isSelected() == true) {
                amodel = Lib_AuthorTableModel.loadInstance(m_App, 2);
                jTable1.setModel(amodel.getTableModel());
            } else if (jCheckBox1.isSelected() == false) {
                amodel = Lib_AuthorTableModel.loadInstance(m_App, 1);
                jTable1.setModel(amodel.getTableModel());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void phn_textKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phn_textKeyTyped
         char value = evt.getKeyChar();
    if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) || value==KeyEvent.VK_DELETE)) {
       evt.consume();
    } 
    }//GEN-LAST:event_phn_textKeyTyped

    private void yearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyTyped
        char value = evt.getKeyChar();
    if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) || value==KeyEvent.VK_DELETE)) {
       evt.consume();
    } 
    }//GEN-LAST:event_yearKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void phn_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phn_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phn_textActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea add_text;
    private javax.swing.JComboBox catog_box;
    private javax.swing.JTextField city_text;
    private javax.swing.JTextField cnty_text;
    private javax.swing.JComboBox date;
    private javax.swing.JButton deactivate;
    private javax.swing.JButton edit;
    private javax.swing.JTextField email_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox lang_box;
    private javax.swing.JComboBox month;
    private javax.swing.JTextField name_text;
    private javax.swing.JTextArea notes_text;
    private javax.swing.JTextField phn_text;
    private javax.swing.JTextField photo_text;
    private javax.swing.JTextField regn_text;
    private javax.swing.JButton save;
    private javax.swing.JButton savechanges;
    private javax.swing.JTextField wrt_text;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitle() {
       return "Library Author Management";
    }

    @Override
    public void activate() throws BasicException {
       
       catmodel=new ComboBoxValModel(getcatname());
       catog_box.setModel(catmodel);
       lagmodel=new ComboBoxValModel(getlangname());
       lang_box.setModel(lagmodel);
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
        
        name_text.setText(null);
        add_text.setText(null);
        city_text.setText(null);
        regn_text.setText(null);
        cnty_text.setText(null);
        savechanges.setVisible(false);
        phn_text.setText(null);
        year.setText(null);
        email_text.setText(null);
        wrt_text.setText(null);
        notes_text.setText(null);
        photo_text.setText(null);
        save.setVisible(true);
        catog_box.setSelectedIndex(-1);
        lang_box.setSelectedIndex(-1);
        date.setSelectedIndex(-1);
        month.setSelectedIndex(-1);
        jTabbedPane1.setSelectedIndex(0);
        jCheckBox1.setSelected(false);
    }
    private Date getDate(String date,int month,String year){
      
        Calendar cal=Calendar.getInstance();
       // cal.setTimeInMillis(d.getTime());
        cal.set(Integer.parseInt(year), month-1,Integer.parseInt(date));
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,00);
        Date d=new Date();
        d.setTime(cal.getTimeInMillis());
        return d;

    }
    public List getcatname() throws BasicException{
          List<Object> catg_list = new ArrayList<Object>();
           catg_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return catg_list;
      }
    
     public List getlangname() throws BasicException{
          List<Object> lang_list = new ArrayList<Object>();
           lang_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return lang_list;
      }
    
    public  String getcatId(){
          
        List<Object> cat_list1 = new ArrayList<Object>();
        try {
        cat_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(catog_box.getSelectedItem());
        catName =(String)cat_list1.get(0);
        
        } catch (BasicException ex) {
            Logger.getLogger(PublisherMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return catName;
      }
    public  String getlangId(){
           
           List<Object> lan_list1 = new ArrayList<Object>();
        try {
            if(lang_box.getSelectedIndex()!=-1){
            lan_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(lang_box.getSelectedItem());
            lang1 =(String)lan_list1.get(0);
            }else{
                lang1=" ";
            }
        
        } catch (BasicException ex) {
            Logger.getLogger(BookMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           return lang1;
      }
    private void insertlib_author() {
        
         try{
          if(name_text.getText().length()>0){
            
              String x=Formats.DATE.formatValue(d);
             Object[] param=new Object[]{UUID.randomUUID().toString(),name_text.getText(),getcatId(),wrt_text.getText(),getlangId(),city_text.getText(),regn_text.getText(),cnty_text.getText(),Formats.DATE.parseValue(x),email_text.getText(),phn_text.getText(),add_text.getText(),notes_text.getText(),photo_text.getText(),/*ADD CATEG & LANG COMBO,*/m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),true};
             new PreparedSentence(m_App.getSession()
             , "INSERT INTO lib_author (ID,NAME,Categories,WRITEUP,language,CITY,REGION,COUNTRY,DOB,EMAIL,PHONE,ADDRESS,NOTES,PHOTO,CREATEDBY,CRDATE,CREATEDHOST,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
             , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN})).exec(param);
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
    private void updatelib_author() {
        
         try{
          if(name_text.getText().length()>0){
            
             String x=Formats.DATE.formatValue(d);
             Object[] param=new Object[]{name_text.getText(),getcatId(),wrt_text.getText(),getlangId(),city_text.getText(),regn_text.getText(),cnty_text.getText(),Formats.DATE.parseValue(x),email_text.getText(),phn_text.getText(),add_text.getText(),notes_text.getText(),photo_text.getText(),/*ADD CATEG & LANG COMBO,*/m_App.getAppUserView().getUser().getName(),new Date(),m_App.getProperties().getHost(),true,a_id};
             new PreparedSentence(m_App.getSession()
             , "UPDATE lib_author SET NAME=?,Categories=?,WRITEUP=?,language=?,CITY=?,REGION=?,COUNTRY=?,DOB=?,EMAIL=?,PHONE=?,ADDRESS=?,NOTES=?,PHOTO=?,CREATEDBY=?,CRDATE=?,CREATEDHOST=?,ACTIVE=? WHERE ID=?"
             , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN,Datas.STRING})).exec(param);
             JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
          }
           amodel  = Lib_AuthorTableModel.loadInstance(m_App,1);
           jTable1.setModel(amodel.getTableModel());
          reset();
          
      }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
        
    }
    
    private void deactlib_author() {
           try{
            String newautId = UUID.randomUUID().toString();
           
             new PreparedSentence(m_App.getSession(), "UPDATE lib_author  SET ID=?, ACTIVE=0  , DEACTBY=? , DEACTDATE=? , DEACTHOST=?,DEACTREFERENCE=?  WHERE ID = ? AND DEACTBY IS NULL AND DEACTDATE IS NULL"
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING , Datas.STRING})).exec
                                                                            (new Object[]{ newautId,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost(),deact_id , deact_id  });
            amodel  = Lib_AuthorTableModel.loadInstance(m_App,1);
            jTable1.setModel(amodel.getTableModel()); 
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
