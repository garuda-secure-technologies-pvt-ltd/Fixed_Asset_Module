/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SmsSender.java
 *
 * Created on Aug 12, 2009, 6:58:10 PM
 */
package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
//import com.openbravo.data.loader.Datas;
//import com.openbravo.data.loader.SerializerReadBasic;
//import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.FixedAssetRegistration.AmcnNonamcDialog;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.Facility;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
//import com.sun.media.sound.Toolkit;
import com.openbravo.pos.util.AltEncrypter;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author swathi
 */
public class SmsSender extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /**
     * Creates new form SmsSender
     */
    private AppView m_App;
    private ComboBoxValModel cmodel;
    private DataLogicFacilities dlfac;
    private String prefix;
    private ComboBoxValModel groupModel;
    private ComboBoxValModel Memtype_Model;
    int i = 1;
    int k = 1;
    int j = 1;
    int l = 1;
    int m = 1;
    int n = 1;
    int v = 1;
    int p = 1;
    int count = 0;
    int len;

    public SmsSender() {
        initComponents();

        memtype_panel.setVisible(false);
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jTextField2.setDocument(new MyPlainDocument(12));
        jTextField2.setDocument(new MyPlainDocument(17));
        noofmsglabel.setText("/1");
        // jTextArea1.setDocument(new MyPlainDocument(160,jLabel3));
        // jTextArea1.getDocument().addDocumentListener(new TextEdited(jLabel3,125,jTextArea1));
        // jTextField2.getDocument().addDocumentListener(new TextEdited(null,12,jTextField2));
        // jTextField3.getDocument().addDocumentListener(new TextEdited(null,17,jTextField3));
    }

    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        Facility f = new Facility();
        f.setID("ALL");
        f.setName("ALL");
        List<Facility> faclist = dlfac.getFacility();
        faclist.add(0, f);
        cmodel = new ComboBoxValModel(faclist);
        jComboBox1.setModel(cmodel);

        // METHOD FOR GETING GROUP LIST ....................................................................#AAKASH
        List<Object> GroupName = new ArrayList<Object>();
        GroupName = getGroupName();

        groupModel = new ComboBoxValModel(GroupName);
        Groupname_combo.setModel(groupModel);

        // METHOD FOR GETING MEMBER TYPE LIST LIST ....................................................................#AAKASH
        List<Object> Memtype = new ArrayList<Object>();
        Memtype = getMemtypeName();

        Memtype_Model = new ComboBoxValModel(Memtype);
        memtype_combo.setModel(Memtype_Model);

        DearMember.setSelected(true);
        facilityWise_Radio.setSelected(true);
        jTextField1.setEditable(false);
        jLabel3.setText("0");
        jLabel8.setVisible(false);
        jTextField3.setVisible(false);

        buttongrp();

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public Object getBean() {
        return this;
    }

    private class MyPlainDocument extends PlainDocument {

        private JLabel label;
        private int max;

        // private JTextArea textarea;
        // private JTextField textfield;
        public MyPlainDocument(int max) {
            //this.label=label;
            this.max = max;
            // this.textarea=null;
        }

        public MyPlainDocument(int max, JLabel label) {
            this.label = label;
            this.max = max;
            // this.textarea=null;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet a) throws
                BadLocationException {
            if (label != null) {
                // max=max+17-prefix.length();
                //  jLabel2.setText("/"+max);
                int len = getLength() + str.length();
                int oldlen = getLength();
                if (!((len) > max)) {
                    super.insertString(offset, str, a);
                    label.setText(String.valueOf(len));
                } else {
                    if (oldlen < max) {
                        String str1 = str.substring(0, max - oldlen);
                        super.insertString(offset, str1, a);
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                //label.setText(String.valueOf(max));
            } else {
                int oldlen = getLength();
                if (!((getLength() + str.length()) > max)) {
                    super.insertString(offset, str, a);
                } else {
                    if (oldlen < max) {
                        String str1 = str.substring(0, max - oldlen);
                        super.insertString(offset, str1, a);
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        facilityWise_Radio = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        DearSir = new javax.swing.JRadioButton();
        DearMam = new javax.swing.JRadioButton();
        DearSirMam = new javax.swing.JRadioButton();
        DearName = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        OtherPrefix = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        NoPrefix = new javax.swing.JRadioButton();
        DearMember = new javax.swing.JRadioButton();
        grouplist_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Groupname_combo = new javax.swing.JComboBox();
        groupWise_Radio = new javax.swing.JRadioButton();
        memtype_panel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        memtype_combo = new javax.swing.JComboBox();
        memtype_radio = new javax.swing.JRadioButton();
        noofmsglabel = new javax.swing.JLabel();
        activemember_CheckBox = new javax.swing.JCheckBox();

        jLabel1.setText("Message :");
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);

        jButton1.setText("Send to all");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("/125");
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabel2KeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("0");
        jLabel3.setName("jLabel3"); // NOI18N

        buttonGroup1.add(facilityWise_Radio);
        facilityWise_Radio.setText("Facility Wise ");
        facilityWise_Radio.setName("facilityWise_Radio"); // NOI18N
        facilityWise_Radio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                facilityWise_RadioStateChanged(evt);
            }
        });
        facilityWise_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                facilityWise_RadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Other List");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel4.setText("Import Data From Excel File");
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N

        jButton2.setText("Browse");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Sender Name");
        jLabel6.setName("jLabel6"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setName("jPanel2"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel5.setText("Facility");
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel4.setName("jPanel4"); // NOI18N

        buttonGroup2.add(DearSir);
        DearSir.setText("Dear Sir");
        DearSir.setName("DearSir"); // NOI18N
        DearSir.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DearSirStateChanged(evt);
            }
        });
        DearSir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DearSirItemStateChanged(evt);
            }
        });

        buttonGroup2.add(DearMam);
        DearMam.setText("Dear Mam");
        DearMam.setName("DearMam"); // NOI18N
        DearMam.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DearMamStateChanged(evt);
            }
        });
        DearMam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DearMamItemStateChanged(evt);
            }
        });

        buttonGroup2.add(DearSirMam);
        DearSirMam.setText("Dear Sir/Mam");
        DearSirMam.setName("DearSirMam"); // NOI18N
        DearSirMam.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DearSirMamStateChanged(evt);
            }
        });
        DearSirMam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DearSirMamItemStateChanged(evt);
            }
        });

        buttonGroup2.add(DearName);
        DearName.setText("Dear $Name$");
        DearName.setName("DearName"); // NOI18N
        DearName.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DearNameStateChanged(evt);
            }
        });
        DearName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DearNameItemStateChanged(evt);
            }
        });

        jLabel7.setText("Select a Prefix :");
        jLabel7.setName("jLabel7"); // NOI18N

        buttonGroup2.add(OtherPrefix);
        OtherPrefix.setText("Other Prefix");
        OtherPrefix.setName("OtherPrefix"); // NOI18N
        OtherPrefix.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OtherPrefixItemStateChanged(evt);
            }
        });

        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel8.setText("Prefix");
        jLabel8.setName("jLabel8"); // NOI18N

        buttonGroup2.add(NoPrefix);
        NoPrefix.setText("No Prefix");
        NoPrefix.setName("NoPrefix"); // NOI18N
        NoPrefix.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                NoPrefixStateChanged(evt);
            }
        });
        NoPrefix.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NoPrefixItemStateChanged(evt);
            }
        });

        buttonGroup2.add(DearMember);
        DearMember.setText("Dear Member");
        DearMember.setName("DearMember"); // NOI18N
        DearMember.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DearMemberStateChanged(evt);
            }
        });
        DearMember.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DearMemberItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DearMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DearSirMam)
                                    .addComponent(DearName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 22, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NoPrefix)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DearSir, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addComponent(DearMam)))))
                .addGap(16, 16, 16))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(OtherPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DearSirMam)
                    .addComponent(DearMam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DearSir)
                    .addComponent(DearName))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DearMember)
                    .addComponent(NoPrefix))
                .addGap(9, 9, 9)
                .addComponent(OtherPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        grouplist_panel.setName("grouplist_panel"); // NOI18N

        jLabel9.setText("Group Name :");
        jLabel9.setName("jLabel9"); // NOI18N

        Groupname_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        Groupname_combo.setName("Groupname_combo"); // NOI18N
        Groupname_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Groupname_comboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout grouplist_panelLayout = new javax.swing.GroupLayout(grouplist_panel);
        grouplist_panel.setLayout(grouplist_panelLayout);
        grouplist_panelLayout.setHorizontalGroup(
            grouplist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grouplist_panelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(Groupname_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        grouplist_panelLayout.setVerticalGroup(
            grouplist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grouplist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grouplist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Groupname_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        groupWise_Radio.setText("Group Wise ");
        groupWise_Radio.setName("groupWise_Radio"); // NOI18N
        groupWise_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupWise_RadioItemStateChanged(evt);
            }
        });

        memtype_panel.setName("memtype_panel"); // NOI18N

        jLabel10.setText("Member Type :");
        jLabel10.setName("jLabel10"); // NOI18N

        memtype_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        memtype_combo.setName("memtype_combo"); // NOI18N

        javax.swing.GroupLayout memtype_panelLayout = new javax.swing.GroupLayout(memtype_panel);
        memtype_panel.setLayout(memtype_panelLayout);
        memtype_panelLayout.setHorizontalGroup(
            memtype_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memtype_panelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(memtype_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        memtype_panelLayout.setVerticalGroup(
            memtype_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memtype_panelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(memtype_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memtype_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        memtype_radio.setText("Member type wise ");
        memtype_radio.setName("memtype_radio"); // NOI18N
        memtype_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memtype_radioItemStateChanged(evt);
            }
        });

        noofmsglabel.setText("jLabel11");
        noofmsglabel.setName("noofmsglabel"); // NOI18N

        activemember_CheckBox.setText("Send to inactive members also.");
        activemember_CheckBox.setName("activemember_CheckBox"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2)
                        .addGap(34, 34, 34))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(noofmsglabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(146, 146, 146)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grouplist_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(memtype_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(facilityWise_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupWise_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memtype_radio)
                        .addGap(45, 45, 45)
                        .addComponent(activemember_CheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(noofmsglabel)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(facilityWise_Radio)
                            .addComponent(groupWise_Radio)
                            .addComponent(memtype_radio)
                            .addComponent(activemember_CheckBox)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grouplist_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memtype_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(5, 5, 5)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jRadioButton2.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(activemember_CheckBox.isSelected()){
           
                int cnl_req = JOptionPane.showConfirmDialog(jPanel2, "Do you want to send SMS to all (active and inactive) members ? " ,"SMS confirmation",JOptionPane.YES_NO_OPTION );
                if(cnl_req == JOptionPane.YES_OPTION){


                    try {
                        String sendtype = null;
                        String mobiledata = null;
                        boolean withname = false;

                        String suffuix = jTextField2.getText();
                        String msg = "";
                        if (NoPrefix.isSelected()) {
                            msg = jTextArea1.getText();
                        } else {
                            msg = prefix + "\r" + jTextArea1.getText();
                        }

                        System.out.println(msg.length());
                        //String prefix;

                        // FECILITY WISE SENDING SMS SELECTED...................................................................................................................................
                        if (facilityWise_Radio.isSelected()) {
                            if (DearName.isSelected()) {
                                withname = true;
                                List<Object[]> mobilelist = new ArrayList();
                                if (jComboBox1.getSelectedItem() != null && jComboBox1.getSelectedItem().toString().equals("ALL")) {
                                    sendtype = "ALL";
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE,C.NAME FROM CUSTOMERS C WHERE C.MOBILE IS NOT NULL AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)", null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list();
                                } else {
                                    Facility f = (Facility) jComboBox1.getSelectedItem();
                                    sendtype = f.getName();
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE,C.NAME FROM MEMFACILITYUSAGE M JOIN CUSTOMERS C ON M.MEMNO=C.ID AND C.MOBILE IS NOT NULL AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  WHERE M.ACTIVE=TRUE AND M.FACILITYTYPE=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(f.getid());
                                }
                                for (Object[] a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a[1] + " # " + a[0];
                                    } else {
                                        mobiledata += " : " + a[1] + " # " + a[0];
                                    }
                                    /*if(mobiledata==null)
                                     mobiledata=String.valueOf(a[0]);
                                     else
                                     mobiledata+=" : "+a[0];*/
                                }
                            } else {
                                List<String> mobilelist = new ArrayList();
                                if (jComboBox1.getSelectedItem() != null && jComboBox1.getSelectedItem().toString().equals("ALL")) {
                                    sendtype = "ALL";
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE FROM CUSTOMERS C WHERE C.MOBILE IS NOT NULL AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)", null, SerializerReadString.INSTANCE).list();
                                } else {
                                    Facility f = (Facility) jComboBox1.getSelectedItem();
                                    sendtype = f.getName();
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE FROM MEMFACILITYUSAGE M JOIN CUSTOMERS C ON M.MEMNO=C.ID AND C.MOBILE IS NOT NULL AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  WHERE M.ACTIVE=TRUE AND M.FACILITYTYPE=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(f.getid());
                                }
                                for (String a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a;
                                    } else {
                                        mobiledata += " : " + a;
                                    }
                                }
                            }
                        } //  GROUP WISE SENDING MESSAGE SELECTED .----------------------------------------------------------------------------------EDITED BY #AAKASG
                        else if (groupWise_Radio.isSelected()) {

                            if (prefix.contains("$Name$")) {
                                withname = true;
                                List<Object[]> mobilelist = new ArrayList();
                                if (Groupname_combo.getSelectedIndex() != -1) {
                                    String GrpName = Groupname_combo.getSelectedItem().toString();
                                    String GrpID = getGroupIDbyName(GrpName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE , C.NAME FROM CUSTOMERS C , sms_grp_mem M  WHERE C.ID = M.MEMBERNAME AND M.GROUPNAME=?  AND   (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(GrpID);
                                }
                                for (Object[] a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a[1] + " # " + a[0];
                                    } else {
                                        mobiledata += " : " + a[1] + " # " + a[0];
                                    }

                                }
                            } else {
                                List<String> mobilelist = new ArrayList();
                                if (Groupname_combo.getSelectedIndex() != -1) {
                                    sendtype = "ALL";
                                    String GrpName = Groupname_combo.getSelectedItem().toString();
                                    String GrpID = getGroupIDbyName(GrpName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE FROM CUSTOMERS C , sms_grp_mem M  WHERE C.ID = M.MEMBERNAME AND M.GROUPNAME=?      AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(GrpID);
                                }
                                for (String a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a;
                                    } else {
                                        mobiledata += " : " + a;
                                    }
                                }
                            }

                        } //  MEMTYPE  WISE SENDING MESSAGE SELECTED .----------------------------------------------------------------------------------EDITED BY #AAKASG
                        else if (memtype_radio.isSelected()) {

                            if (prefix.contains("$Name$")) {
                                withname = true;
                                List<Object[]> mobilelist = new ArrayList();
                                if (memtype_combo.getSelectedIndex() != -1) {
                                    String MemTypeName = memtype_combo.getSelectedItem().toString();
                                    String MemTypeID = getMemTypeIDByName(MemTypeName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE , C.NAME FROM CUSTOMERS C  WHERE C.MEMTYPE=?  AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(MemTypeID);
                                }
                                for (Object[] a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a[1] + " # " + a[0];
                                    } else {
                                        mobiledata += " : " + a[1] + " # " + a[0];
                                    }

                                }
                            } else {
                                List<String> mobilelist = new ArrayList();
                                if (memtype_combo.getSelectedIndex() != -1) {
                                    sendtype = "ALL";
                                    String MemTypeName = memtype_combo.getSelectedItem().toString();
                                    String MemTypeID = getMemTypeIDByName(MemTypeName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE  FROM CUSTOMERS C  WHERE C.MEMTYPE=?  AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(MemTypeID);
                                }
                                for (String a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a;
                                    } else {
                                        mobiledata += " : " + a;
                                    }
                                }
                            }

                        }

                        // COMMON METHOD TO SEND MESSAGE TO ACTIVE MESSAGE TABLE.............................................................................................................................
                        if (mobiledata != null) {
                            String id = UUID.randomUUID().toString();
                            if (sendtype.equals("others")) /*
                             new PreparedSentence(m_App.getSession(), "INSERT INTO MANUALMSG(ID,MESSAGE,SENTDATE,TYPE_,SENTTO,SENTBY) VALUES (?,?,?,?,?,?) "
                             ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING})
                             ).exec(new Object[]{id,msg,new Date(),sendtype,mobiledata,m_App.getAppUserView().getUser().getId()});

                             */ {
                                System.out.println();
                            } else {
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MANUALMSG(ID,MESSAGE,SENTDATE,TYPE_) VALUES (?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})
                                ).exec(new Object[]{id, msg, new Date(), sendtype});
                            }

                            if (DearName.isSelected()) {
                                String phoneNO[] = mobiledata.split(":");
                                for (String phno : phoneNO) {
                                    String name_num[] = phno.split("#");
                                    String name = name_num[0];
                                    String ph_no = name_num[1];
                                    msg = prefix + " " + name + "\r" + jTextArea1.getText();
                                    String id1 = UUID.randomUUID().toString();

                                    new PreparedSentence(m_App.getSession(), "INSERT INTO activemsgtable(ID,Message,SENDTO,WITHNAME,PRIORITY,CNT) VALUES (?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.INT, Datas.INT})
                                    ).exec(new Object[]{id1, msg, ph_no.trim(), withname, 0, 0});

                                }

                            } else {

                                String phoneNO[] = mobiledata.split(":");

                                for (int i = 0; i < phoneNO.length; i++) {
                                    String id1 = UUID.randomUUID().toString();
                                    String number = phoneNO[i];
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO activemsgtable(ID,Message,SENDTO,WITHNAME,PRIORITY,CNT) VALUES (?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.INT, Datas.INT})
                                    ).exec(new Object[]{id1, msg, number, withname, 0, 0});
                                }
                            }

                            jTextArea1.setText(null);

                            JOptionPane.showMessageDialog(this, " Message Sent Successfully..!! ", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                            DearMember.setSelected(true);
                            prefix = "Dear Member,";
                            // int size = 160 - prefix.length() - 1;
                            jLabel2.setText("/" + 147);
                            jLabel3.setText("0");
                            facilityWise_Radio.setSelected(true);

                        } else {

                            JOptionPane.showMessageDialog(this, " No Mobile Record Found.... !! ", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }







                }
        
        }
        
        else{
            // if inactive members is not selected 
            
            
            
            
            
            int cnl_req = JOptionPane.showConfirmDialog(jPanel2, "Do you want to send SMS to all active members ? " ,"SMS confirmation",JOptionPane.YES_NO_OPTION );
                if(cnl_req == JOptionPane.YES_OPTION){


                    try {
                        String sendtype = null;
                        String mobiledata = null;
                        boolean withname = false;

                        String suffuix = jTextField2.getText();
                        String msg = "";
                        if (NoPrefix.isSelected()) {
                            msg = jTextArea1.getText();
                        } else {
                            msg = prefix + "\r" + jTextArea1.getText();
                        }

                        System.out.println(msg.length());
                        //String prefix;

                        // FECILITY WISE SENDING SMS SELECTED...................................................................................................................................
                        if (facilityWise_Radio.isSelected()) {
                            if (DearName.isSelected()) {
                                withname = true;
                                List<Object[]> mobilelist = new ArrayList();
                                if (jComboBox1.getSelectedItem() != null && jComboBox1.getSelectedItem().toString().equals("ALL")) {
                                    sendtype = "ALL";
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE,C.NAME FROM CUSTOMERS C WHERE C.MOBILE IS NOT NULL and C.VISIBLE=true AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)", null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list();
                                } else {
                                    Facility f = (Facility) jComboBox1.getSelectedItem();
                                    sendtype = f.getName();
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE,C.NAME FROM MEMFACILITYUSAGE M JOIN CUSTOMERS C ON M.MEMNO=C.ID and C.VISIBLE=true AND C.MOBILE IS NOT NULL AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  WHERE M.ACTIVE=TRUE AND M.FACILITYTYPE=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(f.getid());
                                }
                                for (Object[] a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a[1] + " # " + a[0];
                                    } else {
                                        mobiledata += " : " + a[1] + " # " + a[0];
                                    }
                                    /*if(mobiledata==null)
                                     mobiledata=String.valueOf(a[0]);
                                     else
                                     mobiledata+=" : "+a[0];*/
                                }
                            } else {
                                List<String> mobilelist = new ArrayList();
                                if (jComboBox1.getSelectedItem() != null && jComboBox1.getSelectedItem().toString().equals("ALL")) {
                                    sendtype = "ALL";
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE FROM CUSTOMERS C WHERE C.MOBILE IS NOT NULL and C.VISIBLE=true AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)", null, SerializerReadString.INSTANCE).list();
                                } else {
                                    Facility f = (Facility) jComboBox1.getSelectedItem();
                                    sendtype = f.getName();
                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE FROM MEMFACILITYUSAGE M JOIN CUSTOMERS C ON M.MEMNO=C.ID AND C.MOBILE IS NOT NULL  and C.VISIBLE=true AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  WHERE M.ACTIVE=TRUE AND M.FACILITYTYPE=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(f.getid());
                                }
                                for (String a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a;
                                    } else {
                                        mobiledata += " : " + a;
                                    }
                                }
                            }
                        } //  GROUP WISE SENDING MESSAGE SELECTED .----------------------------------------------------------------------------------EDITED BY #AAKASG
                        else if (groupWise_Radio.isSelected()) {

                            if (prefix.contains("$Name$")) {
                                withname = true;
                                List<Object[]> mobilelist = new ArrayList();
                                if (Groupname_combo.getSelectedIndex() != -1) {
                                    String GrpName = Groupname_combo.getSelectedItem().toString();
                                    String GrpID = getGroupIDbyName(GrpName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE , C.NAME FROM CUSTOMERS C , sms_grp_mem M  WHERE C.ID = M.MEMBERNAME AND M.GROUPNAME=?  AND C.VISIBLE=TRUE AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(GrpID);
                                }
                                for (Object[] a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a[1] + " # " + a[0];
                                    } else {
                                        mobiledata += " : " + a[1] + " # " + a[0];
                                    }

                                }
                            } else {
                                List<String> mobilelist = new ArrayList();
                                if (Groupname_combo.getSelectedIndex() != -1) {
                                    sendtype = "ALL";
                                    String GrpName = Groupname_combo.getSelectedItem().toString();
                                    String GrpID = getGroupIDbyName(GrpName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE FROM CUSTOMERS C , sms_grp_mem M  WHERE C.ID = M.MEMBERNAME AND M.GROUPNAME=?  AND C.VISIBLE=TRUE   AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(GrpID);
                                }
                                for (String a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a;
                                    } else {
                                        mobiledata += " : " + a;
                                    }
                                }
                            }

                        } //  MEMTYPE  WISE SENDING MESSAGE SELECTED .----------------------------------------------------------------------------------EDITED BY #AAKASG
                        else if (memtype_radio.isSelected()) {

                            if (prefix.contains("$Name$")) {
                                withname = true;
                                List<Object[]> mobilelist = new ArrayList();
                                if (memtype_combo.getSelectedIndex() != -1) {
                                    String MemTypeName = memtype_combo.getSelectedItem().toString();
                                    String MemTypeID = getMemTypeIDByName(MemTypeName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE , C.NAME FROM CUSTOMERS C  WHERE C.MEMTYPE=? and C.VISIBLE=true  AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(MemTypeID);
                                }
                                for (Object[] a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a[1] + " # " + a[0];
                                    } else {
                                        mobiledata += " : " + a[1] + " # " + a[0];
                                    }

                                }
                            } else {
                                List<String> mobilelist = new ArrayList();
                                if (memtype_combo.getSelectedIndex() != -1) {
                                    sendtype = "ALL";
                                    String MemTypeName = memtype_combo.getSelectedItem().toString();
                                    String MemTypeID = getMemTypeIDByName(MemTypeName);

                                    mobilelist = new PreparedSentence(m_App.getSession(), "SELECT C.MOBILE  FROM CUSTOMERS C  WHERE C.MEMTYPE=? and C.VISIBLE=true AND (LENGTH(C.MOBILE)=10 OR LENGTH(C.MOBILE)=13)  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(MemTypeID);
                                }
                                for (String a : mobilelist) {
                                    if (mobiledata == null) {
                                        mobiledata = a;
                                    } else {
                                        mobiledata += " : " + a;
                                    }
                                }
                            }

                        }

                        // COMMON METHOD TO SEND MESSAGE TO ACTIVE MESSAGE TABLE.............................................................................................................................
                        if (mobiledata != null) {
                            String id = UUID.randomUUID().toString();
                            if (sendtype.equals("others")) /*
                             new PreparedSentence(m_App.getSession(), "INSERT INTO MANUALMSG(ID,MESSAGE,SENTDATE,TYPE_,SENTTO,SENTBY) VALUES (?,?,?,?,?,?) "
                             ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING})
                             ).exec(new Object[]{id,msg,new Date(),sendtype,mobiledata,m_App.getAppUserView().getUser().getId()});

                             */ {
                                System.out.println();
                            } else {
                                new PreparedSentence(m_App.getSession(), "INSERT INTO MANUALMSG(ID,MESSAGE,SENTDATE,TYPE_) VALUES (?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})
                                ).exec(new Object[]{id, msg, new Date(), sendtype});
                            }

                            if (DearName.isSelected()) {
                                String phoneNO[] = mobiledata.split(":");
                                for (String phno : phoneNO) {
                                    String name_num[] = phno.split("#");
                                    String name = name_num[0];
                                    String ph_no = name_num[1];
                                    msg = prefix + " " + name + "\r" + jTextArea1.getText();
                                    String id1 = UUID.randomUUID().toString();

                                    new PreparedSentence(m_App.getSession(), "INSERT INTO activemsgtable(ID,Message,SENDTO,WITHNAME,PRIORITY,CNT) VALUES (?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.INT, Datas.INT})
                                    ).exec(new Object[]{id1, msg, ph_no.trim(), withname, 0, 0});

                                }

                            } else {

                                String phoneNO[] = mobiledata.split(":");

                                for (int i = 0; i < phoneNO.length; i++) {
                                    String id1 = UUID.randomUUID().toString();
                                    String number = phoneNO[i];
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO activemsgtable(ID,Message,SENDTO,WITHNAME,PRIORITY,CNT) VALUES (?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.INT, Datas.INT})
                                    ).exec(new Object[]{id1, msg, number, withname, 0, 0});
                                }
                            }

                            jTextArea1.setText(null);

                            JOptionPane.showMessageDialog(this, " Message Sent Successfully..!! ", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                            DearMember.setSelected(true);
                            prefix = "Dear Member,";
                            // int size = 160 - prefix.length() - 1;
                            jLabel2.setText("/" + 147);
                            jLabel3.setText("0");
                            facilityWise_Radio.setSelected(true);

                        } else {

                            JOptionPane.showMessageDialog(this, " No Mobile Record Found.... !! ", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

            
                }
            
            
        }
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void sendmessage(String id, String msg, String phoneNO[]) {
        String postData = "";
        String retval = "";
        String delrep = "";
        List<Object[]> urlbean = null;
        //List<UrlBean> urlbean=null;
        //UrlBean urlBean=null;
        Object[] obj = null;
        Object[] obj1 = null;
        try {
            urlbean = geturl();
        } catch (BasicException ex) {
            System.out.println(ex);
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Object[] url1 : urlbean) {
            //urlBean=(UrlBean)url;
            obj = url1;
        }
        //for (UrlBean url : urlbean) {
        // urlBean=(UrlBean)url;
        //obj=url;
        // }

        // for(int i=0;i<phoneNO.length;i++){
        URL url = null;
        String User = obj[2].toString();
        String passwd = obj[3].toString();
        // System.out.println(passwd);
        String decryptpass = new AltEncrypter("key").decrypt(passwd);
       // System.out.println(decryptpass);
        // String User="kapdemo";
        // String passwd="demo123";

        //get msg and phno
        // List<Object[]>msgobj=getmsg();
        // for (Object[] msglist : msgobj) {
        //urlBean=(UrlBean)url;
        //obj1=msglist;
        System.out.println("*********");
        String mobilenumber = "9663876346";
        //String mobilenumber =phoneNO[i]; 
        String message = msg;
        String sid = obj[5].toString();
        String decodedString;
        // String urlpath="http://trans.kapsystem.com/web2sms.php?";

        String urlpath = obj[1].toString();
        //String urlpath="http://instant.kapsystem.com/sms/user/urlsms_temp.php?";
        // String delreportpath="http://203.129.203.254/sms/user/responce.php";
        //postData += "username=" + User+ "&pass=" + decryptpass + "&to=" + mobilenumber +"&sender=" + sid + "&message=" + message;
        //postData +="username=" + User+ "&pass=" + decryptpass+ "&senderid=" + sid  +"&mtype=txt&tempid=174&f1=xxxxx&f2=xxxxx&f3=xxxxx&dest_mobileno=" +mobilenumber +"&response=Y" ;
        //postData += "username=" + User + "&pass=" + decryptpass + "&senderid=" + sid + "&dest_mobileno=" + mobilenumber + "&tempid=15211&response=Y";

        postData += "username=" + User + "&pass=" + decryptpass + "&senderid=" + sid + "&message=" + message + "&dest_mobileno=" + mobilenumber + "&response=Y";
        try {
            url = new URL(urlpath);
          //  HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            //  urlconnection.setRequestMethod("POST");
            //    urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            //   urlconnection.setDoOutput(true);
            //    OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
            //    out.write(postData);
            //   out.close();
            // BufferedReader in = new BufferedReader( new 
            // InputStreamReader(urlconnection.getInputStream()));

            // while ((decodedString = in.readLine()) != null) {
            // retval= decodedString;
            //  }
            // in.close();
            System.out.println(retval);
            if (!retval.contains("ES1003 Insufficient Balance/Account Expired") && !retval.contains("message is blank") && !retval.contains("invalid username and password") && !retval.contains("you have exceeded your sms limit") && !retval.contains("invalid senderID") && !retval.contains("template id is invalid") && !retval.contains("message not found") && !retval.contains("username not found..!") && !retval.contains("invalid username and password") && !retval.contains("007")) {
                // updateToSentMsgTable(id, retval, msg, mobilenumber);
                //deletefromactivemsg(obj1[0].toString());    
            } else {
                //JOptionPane.showMessageDialog(this, "ES1003 Insufficient Balance/Account Expired");
            }

        } catch (MalformedURLException ex) {
            System.out.println("malformedURLException");
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        // }
    }

    public List<Object[]> geturl() throws BasicException {
        List<Object[]> obj = (List<Object[]>) new PreparedSentence(m_App.getSession(), "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE,S.SENDERID,S.URLREF FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE AND S.URLREF='SEND_URL'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING})).list();

        // List<UrlBean> url=(List<UrlBean>)new StaticSentence(m_App.getSession(), "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadClass(UrlBean.class)).list();
        // System.out.println(url.size());
        // return url;
        return obj;
    }

    public void updateToSentMsgTable(String id, String details, String msg, String phoneno) {
        try {
            new PreparedSentence(m_App.getSession(), "INSERT INTO sentmsgtable(ID, MESSAGE, SENTTO, SENTDETAILS) VALUES (?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})
            ).exec(new Object[]{id, msg, phoneno, details});
        } catch (BasicException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletefromactivemsg(String msgid) {
        try {
            new StaticSentence(m_App.getSession(), "DELETE FROM activemsgtable WHERE ID = ?", SerializerWriteString.INSTANCE).exec(msgid);
        } catch (BasicException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(null);
        FileFilter ffilter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                // if(f.get)
                String path = f.getAbsolutePath();
                if (path.toUpperCase().endsWith(".XLS")) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                return "Excel Files";
            }
        };
        file.setFileFilter(ffilter);
        if (file.showOpenDialog(new JFrame()) == JFileChooser.OPEN_DIALOG) {
            jTextField1.setText(file.getSelectedFile().getAbsolutePath());

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void facilityWise_RadioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_facilityWise_RadioStateChanged


    }//GEN-LAST:event_facilityWise_RadioStateChanged

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        if (jRadioButton2.isSelected() == true) {
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void DearSirMamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DearSirMamStateChanged
        // if(jRadioButton5.isSelected()==true)
        // {
        //    prefix="Dear Sir/Mam";
        //}
        // int size= 160-prefix.length();
        // jLabel2.setText("/"+size);

    }//GEN-LAST:event_DearSirMamStateChanged

    private void DearMamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DearMamStateChanged
        if (DearMam.isSelected() == true) {
            prefix = "Dear Mam";
        }
        //  int size= 160-prefix.length();
        //   jLabel2.setText("/"+size);
    }//GEN-LAST:event_DearMamStateChanged

    private void DearNameStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DearNameStateChanged
        if (DearName.isSelected() == true) {
            prefix = "Dear";
        }
        // int size= 160-prefix.length();
        // jLabel2.setText("/125");
    }//GEN-LAST:event_DearNameStateChanged

    private void DearSirStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DearSirStateChanged
        if (DearSir.isSelected() == true) {
            prefix = "Dear Sir";
        }
        // int size= 160-prefix.length();
        // jLabel2.setText("/"+size);
    }//GEN-LAST:event_DearSirStateChanged

    private void DearMemberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DearMemberStateChanged
        if (DearMember.isSelected() == true) {
            prefix = "Dear Member";
        }
        // int size= 160-prefix.length();
        // jLabel2.setText("/"+size);
    }//GEN-LAST:event_DearMemberStateChanged

    private void NoPrefixStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_NoPrefixStateChanged
        if (NoPrefix.isSelected() == true) {
            p = 1;
            prefix = "";
            jLabel2.setText("/160");
            jLabel3.setText("0");
            noofmsglabel.setText("1/");
        } else {
            if (OtherPrefix.isSelected() == true) {
                prefix = jTextField3.getText();
            }

            int size = 160 - prefix.length();
            if (DearName.isSelected() == true) {
                jLabel2.setText("/125");
            } else {
                jLabel2.setText("/" + size);
            }
        }
    }//GEN-LAST:event_NoPrefixStateChanged

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        /* if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
         int size = 160 - jTextField3.getText().length() - 1;
         jLabel2.setText("/" + size);
         jTextArea1.setDocument(new MyPlainDocument(764));
         }*/

    }//GEN-LAST:event_jTextField3KeyPressed

    private void DearSirMamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DearSirMamItemStateChanged
        if (DearSirMam.isSelected() == true) {
            k = 1;
            prefix = "Dear Sir/Mam,";
        }
        int size = 160 - prefix.length() - 1;
        noofmsglabel.setText("1/");
        jLabel3.setText("0");
        jLabel2.setText("/" + size);
        jTextArea1.setDocument(new MyPlainDocument(465));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_DearSirMamItemStateChanged

    private void DearMamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DearMamItemStateChanged
        if (DearMam.isSelected() == true) {
            prefix = "Dear Mam,";
            m = 1;
        }
        int size = 160 - prefix.length() - 1;
        noofmsglabel.setText("1/");
        jLabel3.setText("0");
        jLabel2.setText("/" + size);
        jTextArea1.setDocument(new MyPlainDocument(469));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_DearMamItemStateChanged

    private void DearNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DearNameItemStateChanged
        if (DearName.isSelected() == true) {
            i = 1;
        }
        prefix = "Dear";
        int size = 160 - prefix.length();
        jLabel2.setText("/124");
        noofmsglabel.setText("1/");
        jLabel3.setText("0");
        jTextArea1.setDocument(new MyPlainDocument(443));
        jTextArea1.setEditable(true);

    }//GEN-LAST:event_DearNameItemStateChanged

    private void DearSirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DearSirItemStateChanged
        if (DearSir.isSelected() == true) {
            prefix = "Dear Sir,";
            n = 1;
        }
        int size = 160 - prefix.length() - 1;
        jLabel2.setText("/" + size);
        noofmsglabel.setText("1/");
        jLabel3.setText("0");
        jTextArea1.setDocument(new MyPlainDocument(469));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_DearSirItemStateChanged

    private void DearMemberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DearMemberItemStateChanged
        if (DearMember.isSelected() == true) {
            prefix = "Dear Member,";
            j = 1;
        }
        int size = 160 - prefix.length() - 1;
        jLabel2.setText("/" + size);
        noofmsglabel.setText("1/");
        jLabel3.setText("0");
        jTextArea1.setDocument(new MyPlainDocument(466));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_DearMemberItemStateChanged

    private void NoPrefixItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NoPrefixItemStateChanged
        if (NoPrefix.isSelected() == true) {
            p = 1;
            prefix = "";
            jLabel2.setText("/160");
            noofmsglabel.setText("1/");
            jLabel3.setText("0");
            int size = 160;
            jTextArea1.setDocument(new MyPlainDocument(479));
            jTextArea1.setEditable(true);
        }
    }//GEN-LAST:event_NoPrefixItemStateChanged

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked

        /*        if(jRadioButton6.isSelected()==true){
          
          
         int size=160-jTextField3.getText().length()-1;
         jLabel2.setText("/"+size);
         jTextArea1.setDocument(new MyPlainDocument(Integer.MAX_VALUE,jLabel3));
         prefix=jTextField3.getText();
         if(jLabel3.getText().equals("159")){
         k=i++;
         noofmsglabel.setText("/"+i);
         }else{
         noofmsglabel.setText("0");
         }
         /* int size=160-jTextField3.getText().length()-1;
         if(size==160){
         jLabel2.setText("/"+size);
         jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
         prefix=jTextField3.getText();
         }*/
        /*  }
         jTextArea1.setEditable(true);*/
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void Groupname_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Groupname_comboItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Groupname_comboItemStateChanged

    private void groupWise_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupWise_RadioItemStateChanged
        if (groupWise_Radio.isSelected()) {
            jPanel2.setVisible(false);
            jPanel1.setVisible(false);
            Groupname_combo.setSelectedIndex(-1);
            grouplist_panel.setVisible(true);

        } else {
            jPanel2.setVisible(true);
            Groupname_combo.setSelectedIndex(-1);
            grouplist_panel.setVisible(false);
        }
    }//GEN-LAST:event_groupWise_RadioItemStateChanged

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
//        boolean ctrlPressed = false;
        //   boolean cPressed = false;
        if (!evt.getKeyText(evt.getKeyCode()).equals("Backspace")) {

            String message = jTextArea1.getText();
            Pattern pattern = Pattern.compile("\\&");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {

                JOptionPane.showMessageDialog(this, "Please do not use special character-'&' ");

            }

        }
        /*  switch(evt.getKeyCode()) {
         case KeyEvent.VK_C:
         cPressed=true;
         len=jTextArea1.getText().length()-1;
               
         case KeyEvent.VK_CONTROL:
         ctrlPressed=true;
         len=jTextArea1.getText().length()-1;
         }

         if(ctrlPressed && cPressed) {
         len=jTextArea1.getText().length()-1;  
         }
         */
       if (((evt.getKeyCode() == KeyEvent.VK_C) || (evt.getKeyCode() == KeyEvent.VK_V) || (evt.getKeyCode() == KeyEvent.VK_A) || (evt.getKeyCode() == KeyEvent.VK_X) || (evt.getKeyCode() == KeyEvent.VK_CUT)) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            len = jTextArea1.getText().length() + 1;
        } else {

            len = 0;
        }
        
        
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void facilityWise_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_facilityWise_RadioItemStateChanged
        // TODO add your handling code here:
        if (facilityWise_Radio.isSelected()) {
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
            Groupname_combo.setSelectedIndex(-1);
            grouplist_panel.setVisible(false);

        } else {
            jPanel2.setVisible(false);
            Groupname_combo.setSelectedIndex(-1);
            grouplist_panel.setVisible(true);
        }


    }//GEN-LAST:event_facilityWise_RadioItemStateChanged

    private void memtype_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memtype_radioItemStateChanged
        if (memtype_radio.isSelected()) {
            memtype_panel.setVisible(true);
            memtype_combo.setSelectedIndex(-1);
            grouplist_panel.setVisible(false);
            jPanel2.setVisible(false);
        } else {
            memtype_panel.setVisible(false);
            memtype_combo.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_memtype_radioItemStateChanged

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        try {
            if (DearName.isSelected()) {
                int ky = 0;
                int numOfCharactersInt;
                String numOfCharactersString = null;
                prefix = "Dear";
                int size = 160 - prefix.length();

                if (evt.getSource() == jTextArea1) {
                    String tyd = jTextArea1.getText();//whatever the user types
                    numOfCharactersInt = tyd.length() + 1;//length of the string
                    char value = evt.getKeyChar();
                    if (len != 0) {
                        numOfCharactersInt = numOfCharactersInt - 1;

                    }

                    if (numOfCharactersInt > 124) {
                        int rno = numOfCharactersInt - 124;

                        if (numOfCharactersInt > 283) {
                            int no = numOfCharactersInt - 124;
                            if ((no % 160) == 0) {
                                ky = 160;
                            } else {

                                ky = (rno % 160);
                            }
                        } else {

                            ky = (rno % 160);
                        }
                    } else if (numOfCharactersInt == 124) {
                        ky = 124;

                    } else if (numOfCharactersInt < 124) {
                        ky = numOfCharactersInt;
                    }

                    if ((numOfCharactersInt / 124) == 1 && numOfCharactersInt < 284 && ((numOfCharactersInt % 124) > 0)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (2) + "/");
                    }

                    if ((((numOfCharactersInt - 124) / 160) == 1) && (numOfCharactersInt > 125) && (((numOfCharactersInt - 124) % 160) > 0)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (3) + "/");

                    }
                    if ((((numOfCharactersInt - 124) / 160) == 2) && (numOfCharactersInt > 125) && (((numOfCharactersInt - 124) % 160) > 0)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (4) + "/");

                    }
                    if ((((numOfCharactersInt - 124) / 160) == 3) && (numOfCharactersInt > 125) && (((numOfCharactersInt - 124) % 160) > 0)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (5) + "/");

                    }
                    if ((((numOfCharactersInt - 124) / 160) == 4) && (numOfCharactersInt > 125) && (((numOfCharactersInt - 124) % 160) > 0)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (6) + "/");

                    }

                   // char value = evt.getKeyChar();
                    if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - 124) / 160) == 0) && (tyd.length() > 125)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (2) + "/");
                    }
                    if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < 125) {

                        jLabel2.setText("/124");
                        noofmsglabel.setText("" + (1) + "/");
                    }

                    if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - 124) / 160) == 1) && (tyd.length() > 125)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (3) + "/");

                    }
                    if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - 124) / 160) == 2) && (tyd.length() > 125)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (4) + "/");

                    }
                    if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - 124) / 160) == 3) && (tyd.length() > 125)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (5) + "/");

                    }
                    if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - 124) / 160) == 4) && (tyd.length() > 125)) {

                        jLabel2.setText("/160");
                        noofmsglabel.setText("" + (6) + "/");

                    }
                    if ((tyd.length() > 124) && (value == KeyEvent.VK_BACK_SPACE)) {
                        int rno = tyd.length() - 124;

                        if (tyd.length() > 282) {
                            int no = tyd.length() - 124;
                            if ((no % 160) == 0) {
                                ky = 160;
                            } else {

                                ky = (rno % 160);
                            }
                        } else {

                            ky = (rno % 160);
                        }
                    } else if ((tyd.length() == 124) && (value == KeyEvent.VK_BACK_SPACE)) {
                        ky = 124;

                    } else if ((tyd.length() < 124) && (value == KeyEvent.VK_BACK_SPACE)) {
                        ky = tyd.length();
                    }

                    numOfCharactersString = Integer.toString(ky);
                    jLabel3.setText(numOfCharactersString);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

            Logger.getLogger(SmsSender.class.getName()).log(Level.SEVERE, null, e);

        }
        if (DearSirMam.isSelected()) {
            int ky = 0;
            int numOfCharactersInt;
            String numOfCharactersString = null;
            prefix = "Dear Sir/Mam,";
            int size = 160 - prefix.length() - 1;
            if (evt.getSource() == jTextArea1) {
                String tyd = jTextArea1.getText();//whatever the user types
                numOfCharactersInt = tyd.length() + 1;//length of the string
                int kl = tyd.length();
                if (len != 0) {
                    numOfCharactersInt = numOfCharactersInt - 1;

                }
                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;

                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }

                if ((numOfCharactersInt / size) == 1 && numOfCharactersInt < (size + 160) && ((numOfCharactersInt % size) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }

                if ((((numOfCharactersInt - size) / 160) == 1) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 2) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 3) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 4) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }

                char value = evt.getKeyChar();

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 0) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }
                if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < (size + 1)) {

                    jLabel2.setText("/" + size);
                    noofmsglabel.setText("" + (1) + "/");
                }

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 1) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 2) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 3) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 4) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }
                if ((tyd.length() > (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    int rno = tyd.length() - (size);

                    if (tyd.length() > (size + 160 - 2)) {
                        int no = tyd.length() - (size);
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if ((tyd.length() == (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = (size);

                } else if ((tyd.length() < (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = tyd.length();
                }

                
                numOfCharactersString = Integer.toString(ky);//converts the int to string
                jLabel3.setText(numOfCharactersString);//sets the textfield to the number of characters entered
            }
        }
        if (DearMember.isSelected()) {
            int ky = 0;
            int numOfCharactersInt;
            String numOfCharactersString = null;
            prefix = "Dear Member,";
            int size = 160 - prefix.length() - 1;
            if (evt.getSource() == jTextArea1) {
                String tyd = jTextArea1.getText();//whatever the user types
                numOfCharactersInt = tyd.length() + 1;//length of the string
                if (len != 0) {
                    numOfCharactersInt = numOfCharactersInt - 1;

                }
                /*  if ((numOfCharactersInt % size) == 0) {
                 noofmsglabel.setText("" + (j++) + "/");

                 }*/
                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;

                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }

                if ((numOfCharactersInt / size) == 1 && numOfCharactersInt < (size + 160) && ((numOfCharactersInt % size) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }

                if ((((numOfCharactersInt - size) / 160) == 1) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 2) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 3) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 4) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }

                char value = evt.getKeyChar();

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 0) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }
                if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < (size + 1)) {

                    jLabel2.setText("/" + size);
                    noofmsglabel.setText("" + (1) + "/");
                }

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 1) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 2) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 3) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 4) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }
                if ((tyd.length() > (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    int rno = tyd.length() - (size);

                    if (tyd.length() > (size + 160 - 2)) {
                        int no = tyd.length() - (size);
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if ((tyd.length() == (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = (size);

                } else if ((tyd.length() < (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = tyd.length();
                }

                
                numOfCharactersString = Integer.toString(ky);//converts the int to string
                jLabel3.setText(numOfCharactersString);//sets the textfield to the number of characters entered
            }
        }
        if (OtherPrefix.isSelected()) {
            int ky = 0;
            int numOfCharactersInt;
            String numOfCharactersString = null;
            prefix = jTextField3.getText();
            int size = 160 - prefix.length();

            if (evt.getSource() == jTextArea1) {
                String tyd = jTextArea1.getText();//whatever the user types
                numOfCharactersInt = tyd.length() + 1;//length of the string
                if (len != 0) {
                    numOfCharactersInt = numOfCharactersInt - 1;

                }
                if (tyd.length() <= size) {
                    jLabel2.setText("/" + size);

                } else {
                    jLabel2.setText("/160");

                }

                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;

                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }

                if ((numOfCharactersInt / size) == 1 && numOfCharactersInt < (size + 160) && ((numOfCharactersInt % size) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }

                if ((((numOfCharactersInt - size) / 160) == 1) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 2) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 3) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 4) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }

                char value = evt.getKeyChar();

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 0) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }
                if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < (size + 1)) {

                    jLabel2.setText("/" + size);
                    noofmsglabel.setText("" + (1) + "/");
                }

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 1) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 2) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 3) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 4) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }
                if ((tyd.length() > (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    int rno = tyd.length() - (size);

                    if (tyd.length() > (size + 160 - 2)) {
                        int no = tyd.length() - (size);
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if ((tyd.length() == (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = (size);

                } else if ((tyd.length() < (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = tyd.length();
                }

                numOfCharactersString = Integer.toString(ky);//converts the int to string
                jLabel3.setText(numOfCharactersString);//sets the textfield to the number of characters entered
            }
        }
        if (DearMam.isSelected()) {
            int ky = 0;
            int numOfCharactersInt;
            String numOfCharactersString = null;
            prefix = "Dear Mam,";
            int size = 160 - prefix.length() - 1;
            if (evt.getSource() == jTextArea1) {
                String tyd = jTextArea1.getText();//whatever the user types
                numOfCharactersInt = tyd.length() + 1;//length of the string
                if (len != 0) {
                    numOfCharactersInt = numOfCharactersInt - 1;

                }
                /* if ((numOfCharactersInt % size) == 0) {
                 noofmsglabel.setText("" + (m++) + "/");

                 }*/
                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;

                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }
                if ((numOfCharactersInt / size) == 1 && numOfCharactersInt < (size + 160) && ((numOfCharactersInt % size) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }

                if ((((numOfCharactersInt - size) / 160) == 1) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 2) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 3) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 4) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }

                char value = evt.getKeyChar();

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 0) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }
                if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < (size + 1)) {

                    jLabel2.setText("/" + size);
                    noofmsglabel.setText("" + (1) + "/");
                }

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 1) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 2) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 3) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 4) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }
                if ((tyd.length() > (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    int rno = tyd.length() - (size);

                    if (tyd.length() > (size + 160 - 2)) {
                        int no = tyd.length() - (size);
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if ((tyd.length() == (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = (size);

                } else if ((tyd.length() < (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = tyd.length();
                }
               

                numOfCharactersString = Integer.toString(ky);//converts the int to string
                jLabel3.setText(numOfCharactersString);//sets the textfield to the number of characters entered
            }
        }
        if (DearSir.isSelected()) {
            int ky = 0;
            int numOfCharactersInt;
            String numOfCharactersString = null;
            prefix = "Dear Sir,";
            int size = 160 - prefix.length() - 1;
            if (evt.getSource() == jTextArea1) {
                String tyd = jTextArea1.getText();//whatever the user types
                numOfCharactersInt = tyd.length() + 1;//length of the string
                if (len != 0) {
                    numOfCharactersInt = numOfCharactersInt - 1;

                }
                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;

                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }

                if ((numOfCharactersInt / size) == 1 && numOfCharactersInt < (size + 160) && ((numOfCharactersInt % size) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }

                if ((((numOfCharactersInt - size) / 160) == 1) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 2) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 3) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 4) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }

                char value = evt.getKeyChar();

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 0) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }
                if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < (size + 1)) {

                    jLabel2.setText("/" + size);
                    noofmsglabel.setText("" + (1) + "/");
                }

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 1) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 2) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 3) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 4) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }
                if ((tyd.length() > (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    int rno = tyd.length() - (size);

                    if (tyd.length() > (size + 160 - 2)) {
                        int no = tyd.length() - (size);
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if ((tyd.length() == (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = (size);

                } else if ((tyd.length() < (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = tyd.length();
                }

                
                numOfCharactersString = Integer.toString(ky);//converts the int to string
                jLabel3.setText(numOfCharactersString);//sets the textfield to the number of characters entered
            }
        }
        if (NoPrefix.isSelected()) {
            int ky = 0;
            int numOfCharactersInt;
            String numOfCharactersString = null;
            prefix = "";
            int size = 160 - prefix.length();
            if (evt.getSource() == jTextArea1) {
                String tyd = jTextArea1.getText();//whatever the user types
                numOfCharactersInt = tyd.length() + 1;//length of the string
                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;
                    if (len != 0) {
                        numOfCharactersInt = numOfCharactersInt - 1;

                    }
                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }

                if (numOfCharactersInt > size) {
                    int rno = numOfCharactersInt - size;

                    if (numOfCharactersInt >= (size + 160)) {
                        int no = numOfCharactersInt - size;
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if (numOfCharactersInt == size) {
                    ky = size;

                } else if (numOfCharactersInt < size) {
                    ky = numOfCharactersInt;
                }

                if ((numOfCharactersInt / size) == 1 && numOfCharactersInt < (size + 160) && ((numOfCharactersInt % size) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }

                if ((((numOfCharactersInt - size) / 160) == 1) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 2) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 3) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((((numOfCharactersInt - size) / 160) == 4) && (numOfCharactersInt > (size + 1)) && (((numOfCharactersInt - size) % 160) > 0)) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }

                char value = evt.getKeyChar();

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 0) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (2) + "/");
                }
                if ((value == KeyEvent.VK_BACK_SPACE) && tyd.length() < (size + 1)) {

                    jLabel2.setText("/" + size);
                    noofmsglabel.setText("" + (1) + "/");
                }

                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 1) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (3) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 2) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (4) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 3) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (5) + "/");

                }
                if ((value == KeyEvent.VK_BACK_SPACE) && (((tyd.length() - size) / 160) == 4) && (tyd.length() > (size + 1))) {

                    jLabel2.setText("/160");
                    noofmsglabel.setText("" + (6) + "/");

                }
                if ((tyd.length() > (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    int rno = tyd.length() - (size);

                    if (tyd.length() > (size + 160 - 2)) {
                        int no = tyd.length() - (size);
                        if ((no % 160) == 0) {
                            ky = 160;
                        } else {

                            ky = (rno % 160);
                        }
                    } else {

                        ky = (rno % 160);
                    }
                } else if ((tyd.length() == (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = (size);

                } else if ((tyd.length() < (size)) && (value == KeyEvent.VK_BACK_SPACE)) {
                    ky = tyd.length();
                }

               
                numOfCharactersString = Integer.toString(ky);//converts the int to string
                jLabel3.setText(numOfCharactersString);//sets the textfield to the number of characters entered
            }
        }
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jLabel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2KeyTyped

    private void OtherPrefixItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OtherPrefixItemStateChanged
        if (OtherPrefix.isSelected() == true) {
            l = 1;
            jTextArea1.setEditable(true);
            jTextArea1.setText("");
            jLabel8.setVisible(true);
            jTextField3.setVisible(true);
            noofmsglabel.setText("1/");
            jLabel3.setText("0");
            // jTextField3.requestFocus(true);
            if (jTextField3.getText() == null || jTextField3.getText().length() <= 0) {
                jLabel2.setText("/160");
                prefix = jTextField3.getText();

            } else {
                prefix = jTextField3.getText();
                int size = 160 - prefix.length() - 1;

                jLabel2.setText("/" + size);
                //  int size=160-jTextField3.getText().length();
                //  jLabel2.setText("/"+size);
            }
        } else {
            jLabel8.setVisible(false);
            jTextField3.setVisible(false);
            jTextField3.setText(null);
        }
        jTextArea1.setDocument(new MyPlainDocument(479));
    }//GEN-LAST:event_OtherPrefixItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton DearMam;
    private javax.swing.JRadioButton DearMember;
    private javax.swing.JRadioButton DearName;
    private javax.swing.JRadioButton DearSir;
    private javax.swing.JRadioButton DearSirMam;
    private javax.swing.JComboBox Groupname_combo;
    private javax.swing.JRadioButton NoPrefix;
    private javax.swing.JRadioButton OtherPrefix;
    private javax.swing.JCheckBox activemember_CheckBox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton facilityWise_Radio;
    private javax.swing.JRadioButton groupWise_Radio;
    private javax.swing.JPanel grouplist_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JComboBox memtype_combo;
    private javax.swing.JPanel memtype_panel;
    private javax.swing.JRadioButton memtype_radio;
    private javax.swing.JLabel noofmsglabel;
    // End of variables declaration//GEN-END:variables

    public void buttongrp() {
        ButtonGroup btn = new ButtonGroup();
        btn.add(facilityWise_Radio);
        btn.add(groupWise_Radio);
        btn.add(memtype_radio);

    }

    public List getGroupName() throws BasicException {
        List<Object> GrpNameList = new ArrayList<Object>();

        GrpNameList = new PreparedSentence(m_App.getSession(), "SELECT GROUPNAME FROM sms_group_list WHERE ACTIVE=1 ORDER BY GROUPNAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return GrpNameList;

    }

    public List getMemtypeName() throws BasicException {
        List<Object> GrpNameList = new ArrayList<Object>();

        GrpNameList = new PreparedSentence(m_App.getSession(), "SELECT NAME   FROM memtype WHERE ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return GrpNameList;

    }

    public String getGroupIDbyName(String Grpname) throws BasicException {

        String GroupID = null;
        Object GrpId = null;

        GrpId = new PreparedSentence(m_App.getSession(), "SELECT ID FROM sms_group_list WHERE GROUPNAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Grpname);
        if (GrpId != null) {
            GroupID = GrpId.toString();
        }

        return GroupID;

    }

    public String getMemTypeIDByName(String memtypeName) throws BasicException {

        String GroupID = null;
        Object GrpId = null;

        GrpId = new PreparedSentence(m_App.getSession(), "SELECT ID  FROM memtype WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(memtypeName);
        if (GrpId != null) {
            GroupID = GrpId.toString();
        }

        return GroupID;

    }

}
