 
package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.BookedRoomStatusTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
    
 
public class DocumentReceiptMenu extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {
    private AppView m_App;
    
    private static int NO_OF_SCAN_DOCUMENTS = 0;
    private static int NO_OF_PHOTOS = 0;
    private static int NO_OF_PHOTOS_SON = 0;
    
    private List<String> MemberShipTypeList = new ArrayList<String>();
    private ComboBoxValModel MemTypeComboBoxValModel;
    private List<String> IDProofTypeList = new ArrayList<String>();
    private List<String> IDProofTypeListSpouse = new ArrayList<String>();
    private List<String> IDProofTypeListChildren = new ArrayList<String>();
    private ComboBoxValModel IdProofComboBoxValModel;
    private ComboBoxValModel IdProofComboBoxValModelFather;
    private ComboBoxValModel IdProofComboBoxValModelMpther;
    private ComboBoxValModel IdProofComboBoxValModelSpouse;
    
    
    private ComboBoxValModel IdProofComboBoxValModelSon1;
    private ComboBoxValModel IdProofComboBoxValModelSon2;
    private ComboBoxValModel IdProofComboBoxValModelSon3;
    
    private ComboBoxValModel IdProofComboBoxValModelDaughter1;
    private ComboBoxValModel IdProofComboBoxValModelDaughter2;
    private ComboBoxValModel IdProofComboBoxValModelDaughter3;
    
    
    
    private DocumentReceiptTableModel documentReceipt_Table_Model;
    private List<DocumentReceiptTableModel.DocumentReceiptListInfo> DocumentDataList = new ArrayList<DocumentReceiptTableModel.DocumentReceiptListInfo>();
    
    
    
    int MEMBER_ID_COMBO_INDEX =-1;
    int FATHER_ID_COMBO_INDEX =-1;
    int MOTHER_ID_COMBO_INDEX =-1;
    int SPOUSE_ID_COMBO_INDEX =-1;
    int S1_ID_COMBO_INDEX =-1;
    int S2_ID_COMBO_INDEX =-1;
    int S3_ID_COMBO_INDEX =-1;
    int D1_ID_COMBO_INDEX =-1;
    int D2_ID_COMBO_INDEX =-1;
    int D3_ID_COMBO_INDEX =-1;
    
    
    
    
    
    public DocumentReceiptMenu() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        IdProffMember_combo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        IdProofMember_Check = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        Spouse_check = new javax.swing.JCheckBox();
        spouse_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        IdProffSpouse_combo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        IdProofSpouse_Check = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        Father_check = new javax.swing.JCheckBox();
        father_panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        IdProffFather_combo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        IdProofFather_Check = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        Mother_check = new javax.swing.JCheckBox();
        Children_panel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        son_nos_combo = new javax.swing.JComboBox<>();
        daughter_nos_combo = new javax.swing.JComboBox<>();
        son_main_panel = new javax.swing.JPanel();
        s1_panel = new javax.swing.JPanel();
        s1_idproff_combo = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        s1_photo_check = new javax.swing.JCheckBox();
        s2_panel = new javax.swing.JPanel();
        s2_idproff_combo = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        s2_photo_check = new javax.swing.JCheckBox();
        s3_panel = new javax.swing.JPanel();
        s3_idproff_combo2 = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        s3_photo_check = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        d1_panel = new javax.swing.JPanel();
        d1_idproff_combo = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        d1_photo_check = new javax.swing.JCheckBox();
        d2_panel = new javax.swing.JPanel();
        d2_idproff_combo = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        d2_photo_check = new javax.swing.JCheckBox();
        d3_panel = new javax.swing.JPanel();
        d3_idproff_combo = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        d3_photo_check = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        Children_check = new javax.swing.JCheckBox();
        Mother_panel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        IdProfMother_combo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        IdProofMother_Check = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        dateFormat_panel = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        srNo3_Yesradio = new javax.swing.JRadioButton();
        srNo3_NAradio = new javax.swing.JRadioButton();
        jLabel34 = new javax.swing.JLabel();
        srNo10_Yesradio = new javax.swing.JRadioButton();
        srNo10_NAradio = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        srNo13_Yesradio = new javax.swing.JRadioButton();
        srNo13_NAradio = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        srNo14_Yesradio = new javax.swing.JRadioButton();
        srNo14_NAradio = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        PhoneNo_Check = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        PhotoMatches_check = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        EmailId_check = new javax.swing.JCheckBox();
        memberType_combo = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        PhotoInt_Text = new javax.swing.JLabel();
        Photo_Nos_Match_Check = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        DocumentInt_text = new javax.swing.JLabel();
        Document_Nos_Match_Check = new javax.swing.JCheckBox();
        MemberName_text = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        MemberName_Check = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        FormNo_Text = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        MemberNo_Text = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        memberName_Lable = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        MemberType_Label = new javax.swing.JLabel();
        MemberVisible_label = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        GenerateReceipt_Button = new javax.swing.JButton();
        receiptNo_text = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        Reprint_Receipt_Button = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("2.  Member Name  : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Membership Type  : ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("3.  ID Proof  : ");

        IdProffMember_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        IdProffMember_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProffMember_comboItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Photo : ");

        IdProofMember_Check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProofMember_CheckItemStateChanged(evt);
            }
        });
        IdProofMember_Check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProofMember_CheckActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("4.  Spouse ");

        Spouse_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Spouse_checkItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("ID Proof  : ");

        IdProffSpouse_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        IdProffSpouse_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProffSpouse_comboItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Photo : ");

        IdProofSpouse_Check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProofSpouse_CheckItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout spouse_panelLayout = new javax.swing.GroupLayout(spouse_panel);
        spouse_panel.setLayout(spouse_panelLayout);
        spouse_panelLayout.setHorizontalGroup(
            spouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spouse_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(IdProffSpouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IdProofSpouse_Check)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        spouse_panelLayout.setVerticalGroup(
            spouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spouse_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(spouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IdProofSpouse_Check)
                    .addGroup(spouse_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(IdProffSpouse_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("5.  Father ");

        Father_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Father_checkItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("ID Proof  : ");

        IdProffFather_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        IdProffFather_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProffFather_comboItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Photo : ");

        IdProofFather_Check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProofFather_CheckItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout father_panelLayout = new javax.swing.GroupLayout(father_panel);
        father_panel.setLayout(father_panelLayout);
        father_panelLayout.setHorizontalGroup(
            father_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(father_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(IdProffFather_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IdProofFather_Check)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        father_panelLayout.setVerticalGroup(
            father_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(father_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(father_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IdProofFather_Check, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(father_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(IdProffFather_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("6. Mother ");

        Mother_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Mother_checkItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Son : ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Daughter : ");

        son_nos_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));
        son_nos_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                son_nos_comboItemStateChanged(evt);
            }
        });

        daughter_nos_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3"  }));
        daughter_nos_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                daughter_nos_comboItemStateChanged(evt);
            }
        });

        s1_idproff_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        s1_idproff_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                s1_idproff_comboItemStateChanged(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Photo : ");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Son 1: ");

        s1_photo_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                s1_photo_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout s1_panelLayout = new javax.swing.GroupLayout(s1_panel);
        s1_panel.setLayout(s1_panelLayout);
        s1_panelLayout.setHorizontalGroup(
            s1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(s1_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s1_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s1_photo_check)
                .addContainerGap())
        );
        s1_panelLayout.setVerticalGroup(
            s1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(s1_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(s1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s1_photo_check)
                    .addGroup(s1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(s1_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42)))
                .addGap(2, 2, 2))
        );

        s2_idproff_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        s2_idproff_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                s2_idproff_comboItemStateChanged(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Photo : ");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Son 2: ");

        s2_photo_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                s2_photo_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout s2_panelLayout = new javax.swing.GroupLayout(s2_panel);
        s2_panel.setLayout(s2_panelLayout);
        s2_panelLayout.setHorizontalGroup(
            s2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(s2_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s2_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s2_photo_check)
                .addContainerGap())
        );
        s2_panelLayout.setVerticalGroup(
            s2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(s2_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(s2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s2_photo_check)
                    .addGroup(s2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(s2_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)))
                .addGap(2, 2, 2))
        );

        s3_idproff_combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        s3_idproff_combo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                s3_idproff_combo2ItemStateChanged(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Photo : ");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Son 3: ");

        s3_photo_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                s3_photo_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout s3_panelLayout = new javax.swing.GroupLayout(s3_panel);
        s3_panel.setLayout(s3_panelLayout);
        s3_panelLayout.setHorizontalGroup(
            s3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(s3_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s3_idproff_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(s3_photo_check)
                .addContainerGap())
        );
        s3_panelLayout.setVerticalGroup(
            s3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(s3_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(s3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s3_photo_check)
                    .addGroup(s3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(s3_idproff_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45)))
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout son_main_panelLayout = new javax.swing.GroupLayout(son_main_panel);
        son_main_panel.setLayout(son_main_panelLayout);
        son_main_panelLayout.setHorizontalGroup(
            son_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(son_main_panelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(son_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s3_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s2_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s1_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        son_main_panelLayout.setVerticalGroup(
            son_main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(son_main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(s1_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s2_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s3_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        d1_idproff_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        d1_idproff_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                d1_idproff_comboItemStateChanged(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Photo : ");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("Daughter  1: ");

        d1_photo_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                d1_photo_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout d1_panelLayout = new javax.swing.GroupLayout(d1_panel);
        d1_panel.setLayout(d1_panelLayout);
        d1_panelLayout.setHorizontalGroup(
            d1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d1_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d1_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d1_photo_check)
                .addContainerGap())
        );
        d1_panelLayout.setVerticalGroup(
            d1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d1_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(d1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d1_photo_check)
                    .addGroup(d1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(d1_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47)))
                .addGap(2, 2, 2))
        );

        d2_idproff_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        d2_idproff_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                d2_idproff_comboItemStateChanged(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Photo : ");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Daughter  2: ");

        d2_photo_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                d2_photo_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout d2_panelLayout = new javax.swing.GroupLayout(d2_panel);
        d2_panel.setLayout(d2_panelLayout);
        d2_panelLayout.setHorizontalGroup(
            d2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d2_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d2_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d2_photo_check)
                .addContainerGap())
        );
        d2_panelLayout.setVerticalGroup(
            d2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d2_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(d2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d2_photo_check)
                    .addGroup(d2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(d2_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49)))
                .addGap(2, 2, 2))
        );

        d3_idproff_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        d3_idproff_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                d3_idproff_comboItemStateChanged(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("Photo : ");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("Daughter  3: ");

        d3_photo_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                d3_photo_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout d3_panelLayout = new javax.swing.GroupLayout(d3_panel);
        d3_panel.setLayout(d3_panelLayout);
        d3_panelLayout.setHorizontalGroup(
            d3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d3_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d3_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d3_photo_check)
                .addContainerGap())
        );
        d3_panelLayout.setVerticalGroup(
            d3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(d3_panelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(d3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d3_photo_check)
                    .addGroup(d3_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel52)
                        .addComponent(d3_idproff_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51)))
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d3_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d2_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(d1_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d2_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d3_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Children_panelLayout = new javax.swing.GroupLayout(Children_panel);
        Children_panel.setLayout(Children_panelLayout);
        Children_panelLayout.setHorizontalGroup(
            Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Children_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Children_panelLayout.createSequentialGroup()
                        .addComponent(son_nos_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(son_main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Children_panelLayout.createSequentialGroup()
                        .addComponent(daughter_nos_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        Children_panelLayout.setVerticalGroup(
            Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Children_panelLayout.createSequentialGroup()
                .addGroup(Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Children_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(son_nos_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(son_main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Children_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(daughter_nos_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("7.  Children below 25 years ");

        Children_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Children_checkItemStateChanged(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("ID Proof  : ");

        IdProfMother_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        IdProfMother_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProfMother_comboItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Photo : ");

        IdProofMother_Check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdProofMother_CheckItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout Mother_panelLayout = new javax.swing.GroupLayout(Mother_panel);
        Mother_panel.setLayout(Mother_panelLayout);
        Mother_panelLayout.setHorizontalGroup(
            Mother_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Mother_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(IdProfMother_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IdProofMother_Check)
                .addContainerGap(251, Short.MAX_VALUE))
        );
        Mother_panelLayout.setVerticalGroup(
            Mother_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Mother_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Mother_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(IdProfMother_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(IdProofMother_Check)))
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("8.  Dates in correct format ? ");

        dateFormat_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Sl No 3: (Date of Birth)");

        srNo3_Yesradio.setText("YES");

        srNo3_NAradio.setText("N/A");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("Sl No 10: (Date of Marriage)");

        srNo10_Yesradio.setText("YES");

        srNo10_NAradio.setText("N/A");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("Sl No 13: (Children Date of birth)");

        srNo13_Yesradio.setText("YES");

        srNo13_NAradio.setText("N/A");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Sl No 14: (Parents Date of birth)");

        srNo14_Yesradio.setText("YES");

        srNo14_NAradio.setText("N/A");

        javax.swing.GroupLayout dateFormat_panelLayout = new javax.swing.GroupLayout(dateFormat_panel);
        dateFormat_panel.setLayout(dateFormat_panelLayout);
        dateFormat_panelLayout.setHorizontalGroup(
            dateFormat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dateFormat_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dateFormat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dateFormat_panelLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(srNo3_Yesradio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(srNo3_NAradio)
                        .addGap(16, 16, 16))
                    .addGroup(dateFormat_panelLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srNo13_Yesradio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(srNo13_NAradio)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(dateFormat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dateFormat_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(srNo10_Yesradio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srNo10_NAradio))
                    .addGroup(dateFormat_panelLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srNo14_Yesradio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(srNo14_NAradio)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        dateFormat_panelLayout.setVerticalGroup(
            dateFormat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dateFormat_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dateFormat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(srNo3_Yesradio)
                    .addComponent(srNo3_NAradio)
                    .addComponent(jLabel34)
                    .addComponent(srNo10_Yesradio)
                    .addComponent(srNo10_NAradio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(dateFormat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(srNo13_Yesradio)
                    .addComponent(srNo13_NAradio)
                    .addComponent(jLabel36)
                    .addComponent(srNo14_Yesradio)
                    .addComponent(srNo14_NAradio))
                .addGap(13, 13, 13))
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("9. Sr. No of spouse/parents/depandants matches with photograph serial : ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("10 . Phone No in Sl. no: 13,14,19 are clear : ");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("11. Email ID / Facebook / Twitter are legible : ");

        memberType_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("No of Photos : ");

        PhotoInt_Text.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        PhotoInt_Text.setText("9");

        Photo_Nos_Match_Check.setText("Matches ");
        Photo_Nos_Match_Check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Photo_Nos_Match_CheckActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("No of Scan documents  : ");

        DocumentInt_text.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        DocumentInt_text.setText("0");

        Document_Nos_Match_Check.setText("Matches ");
        Document_Nos_Match_Check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Document_Nos_Match_CheckActionPerformed(evt);
            }
        });

        jLabel27.setText("(As per form) ");

        MemberName_Check.setText("Same/Similar as in data");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setText("**");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 0, 0));
        jLabel39.setText("**");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 0, 0));
        jLabel40.setText("**");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(Father_check))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Mother_check))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Children_check))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EmailId_check))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PhoneNo_Check))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PhotoMatches_check)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PhotoInt_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DocumentInt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Photo_Nos_Match_Check)
                                    .addComponent(Document_Nos_Match_Check)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(Children_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(father_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel38)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel39)
                                            .addGap(17, 17, 17)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Spouse_check)
                                            .addComponent(jLabel40))
                                        .addGap(39, 39, 39)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(IdProffMember_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(IdProofMember_Check)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(memberType_combo, 0, 249, Short.MAX_VALUE)
                                            .addComponent(MemberName_text))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(MemberName_Check))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(spouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(Mother_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(dateFormat_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(MemberName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(MemberName_Check)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(memberType_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IdProofMember_Check, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(IdProffMember_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel40)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(Spouse_check))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spouse_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Father_check)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(father_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Mother_check)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Mother_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(Children_check))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Children_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateFormat_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(PhotoMatches_check))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PhoneNo_Check)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmailId_check)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(PhotoInt_Text)
                    .addComponent(Photo_Nos_Match_Check))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(DocumentInt_text)
                    .addComponent(Document_Nos_Match_Check))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Form SI. No : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("1.  Member No : ");

        MemberNo_Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemberNo_TextActionPerformed(evt);
            }
        });
        MemberNo_Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MemberNo_TextKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Member Name : ");

        memberName_Lable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        memberName_Lable.setForeground(new java.awt.Color(0, 0, 255));
        memberName_Lable.setText("jLabel15");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Membership Type : ");

        MemberType_Label.setText("jLabel15");

        MemberVisible_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        MemberVisible_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MemberVisible_label.setText("jLabel15");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Member details as per data ");

        GenerateReceipt_Button.setText("Generate Receipt ");
        GenerateReceipt_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateReceipt_ButtonActionPerformed(evt);
            }
        });

        receiptNo_text.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        receiptNo_text.setForeground(new java.awt.Color(204, 0, 0));
        receiptNo_text.setText("jLabel37");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 0, 0));
        jLabel37.setText("**");

        Reprint_Receipt_Button.setText("Re-Print Receipt ");
        Reprint_Receipt_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reprint_Receipt_ButtonActionPerformed(evt);
            }
        });

        jButton1.setText("View List");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FormNo_Text)
                            .addComponent(MemberNo_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MemberVisible_label, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel14))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(memberName_Lable, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                            .addComponent(MemberType_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(receiptNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(Reprint_Receipt_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GenerateReceipt_Button)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FormNo_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(receiptNo_text)
                                .addComponent(jLabel37))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MemberNo_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MemberVisible_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(memberName_Lable))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(MemberType_Label))
                        .addGap(10, 10, 10)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GenerateReceipt_Button)
                    .addComponent(Reprint_Receipt_Button))
                .addGap(256, 256, 256))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MemberNo_TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberNo_TextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemberNo_TextActionPerformed

    private void MemberNo_TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MemberNo_TextKeyReleased
        if(MemberNo_Text.getText()!=null && MemberNo_Text.getText().trim().length()>0){
            String memno=MemberNo_Text.getText().trim();
            try{
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT c.NAME , m.name , c.visible  FROM CUSTOMERS c , memtype m  where c.SEARCHKEY=?  and c.memtype=m.id ", 
                                                                SerializerWriteString.INSTANCE, 
                                                            new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.INT })).find(memno);
                if(obj!=null){
                    String memberOrgname = obj[0].toString();
                    String Memtype = obj[1].toString();
                    memberName_Lable.setText(memberOrgname);
                    memberName_Lable.setVisible(true);
                    memberName_Lable.setForeground(Color.BLUE);
                    jScrollPane2.setVisible(true);
                    MemberType_Label.setVisible(true);
                    MemberType_Label.setText(Memtype);
                    MemberType_Label.setForeground(Color.BLUE);
                    MemberVisible_label.setVisible(true);
                    GenerateReceipt_Button.setVisible(true);
                    receiptNo_text.setVisible(false);
                    
                    if(obj[2]!=null){
                        int Visiblestatus = Integer.parseInt(obj[2].toString());
                        
                        if(Visiblestatus==1){
                            MemberVisible_label.setText("Member is visible.");
                            MemberVisible_label.setForeground(Color.BLACK);
                        }
                        else{
                            MemberVisible_label.setText("Member is Deactivated.");
                            MemberVisible_label.setForeground(Color.RED);
                        }
                    }
                    
                    
                    // for memtype 
                    
                    
                     for(int i=0;i<MemberShipTypeList.size();i++){
                         String x = MemberShipTypeList.get(i).toString();
                         if(x.equals(Memtype)){
                             memberType_combo.setSelectedIndex(i);
                             break;
                         }
                     }
                    
                    
                     
                     
                     
                     Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID  FROM kym_documentreceipt where memno=?  ", 
                                                                SerializerWriteString.INSTANCE, 
                                                            new SerializerReadBasic(new Datas[]{Datas.STRING   })).find(memno);
                
                     if(obj2!=null){
                         if(obj2[0]!=null){
                             
                             documentReceipt_Table_Model = DocumentReceiptTableModel.loadInstanceDocumentReports(m_App , memno);
                             DocumentDataList = documentReceipt_Table_Model.getDocumentList();
                             
                             GenerateReceipt_Button.setVisible(false);
                             receiptNo_text.setVisible(true);
                             Reprint_Receipt_Button.setVisible(true);
                             setData();
                             
                         }
                         
                     }
                     
                     
                     
                    
                }
                else{
                    jScrollPane2.setVisible(false);
                    memberName_Lable.setVisible(false);
                    MemberType_Label.setVisible(false);
                    MemberVisible_label.setVisible(false);
                    GenerateReceipt_Button.setVisible(false);
                    receiptNo_text.setVisible(false);
                    
                    Loaddata();
                }
                
            }
            catch(BasicException e){
                
            }
        }//End of outermost if()
        else{
            jScrollPane2.setVisible(false);
            memberName_Lable.setVisible(false);
            MemberType_Label.setVisible(false);
            MemberVisible_label.setVisible(false);
            GenerateReceipt_Button.setVisible(false);
            receiptNo_text.setVisible(false);
            
        }
    }//GEN-LAST:event_MemberNo_TextKeyReleased

    private void Photo_Nos_Match_CheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Photo_Nos_Match_CheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Photo_Nos_Match_CheckActionPerformed

    private void Document_Nos_Match_CheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Document_Nos_Match_CheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Document_Nos_Match_CheckActionPerformed

    private void IdProofMember_CheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProofMember_CheckItemStateChanged
         if(IdProofMember_Check.isSelected()){
             NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
         }
         else{
             NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
         }
          PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_IdProofMember_CheckItemStateChanged

    private void IdProofSpouse_CheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProofSpouse_CheckItemStateChanged
         if(IdProofSpouse_Check.isSelected()){
              NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
         }
         else{
             NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
         }
         PhotoInt_Text.setText(NO_OF_PHOTOS+"");
         
    }//GEN-LAST:event_IdProofSpouse_CheckItemStateChanged

    private void IdProofFather_CheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProofFather_CheckItemStateChanged
         if(IdProofFather_Check.isSelected()){
              NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
         }
         else{
             NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
         }
          PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_IdProofFather_CheckItemStateChanged

    private void IdProofMother_CheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProofMother_CheckItemStateChanged
       if(IdProofMother_Check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_IdProofMother_CheckItemStateChanged

    private void Spouse_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Spouse_checkItemStateChanged
        if(Spouse_check.isSelected()){
            spouse_panel.setVisible(true);
            
            jLabel34.setVisible(true);
            srNo10_Yesradio.setVisible(true);
            srNo10_NAradio.setVisible(true);
            if(IdProffSpouse_combo.getSelectedIndex()==(-1))
            {
                IdProofSpouse_Check.setEnabled(false); //Edited By: Shoeb
            }
            
        }
        else{
            spouse_panel.setVisible(false);
            IdProofSpouse_Check.setSelected(false);
            IdProffSpouse_combo.setSelectedIndex(-1);
            
            jLabel34.setVisible(false);
            srNo10_Yesradio.setVisible(false);
            srNo10_NAradio.setVisible(false);
            srNo10_Yesradio.setSelected(false);
            srNo10_NAradio.setSelected(true);
            
        }
    }//GEN-LAST:event_Spouse_checkItemStateChanged

    private void Father_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Father_checkItemStateChanged
       if(Father_check.isSelected()){
           father_panel.setVisible(true);
           if(IdProffFather_combo.getSelectedIndex()==(-1))
           {
               IdProofFather_Check.setEnabled(false);
           }
       }
       else{
            father_panel.setVisible(false);
            IdProofFather_Check.setSelected(false);
            
            IdProffFather_combo.setSelectedIndex(-1);
       }
    }//GEN-LAST:event_Father_checkItemStateChanged

    private void Mother_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Mother_checkItemStateChanged
        if(Mother_check.isSelected()){
             Mother_panel.setVisible(true);
             IdProofMother_Check.setEnabled(false);
        }
        else{
             Mother_panel.setVisible(false);
             IdProofMother_Check.setSelected(false);
             IdProfMother_combo.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_Mother_checkItemStateChanged

    private void Children_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Children_checkItemStateChanged
        if(Children_check.isSelected()){
            Children_panel.setVisible(true);
            
            
            
            jLabel35.setVisible(true);
            srNo13_Yesradio.setVisible(true);
            srNo13_NAradio.setVisible(true);
            
            
             
        }
        else{
            Children_panel.setVisible(false);
            
            
            jLabel35.setVisible(false);
            srNo13_Yesradio.setVisible(false);
            srNo13_NAradio.setVisible(false);
            srNo13_Yesradio.setSelected(false);
            srNo13_NAradio.setSelected(true);
            
            
            
        }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");

    }//GEN-LAST:event_Children_checkItemStateChanged

    
    String ReceiptNo;
    String FormNo  ;
    String MemberNo ;
    String MemberName  ;
    String MemberType  ;
    int MemberNameMatches = 0;
    String MemberIDProof;
    int MemberPhoto;
    
    String SpouseID;
    int SpousePhoto;
    
    String FatherId;
    int FatherPhoto;
    
    String MotherId;
    int MotherPhoto;
    String SonID;
    String DaughterId;
    
    int SonNos=0;
    int DaughterNos=0;
    
    int SPD_Matches=0;
    int PhoneNoMatches = 0;
    int EmailIdMatches =0;
    
    String SonPhoto="";
    String DaughterPhoto="";
    String DateFormat = "";
    
    int Date_3 = 0;
    int Date_10 = 0;
    int Date_13 = 0;
    int Date_14=0;
    
    String S1_IDProof;
    String S2_IDProof;
    String S3_IDProof;
    
    String D1_IDProof;
    String D2_IDProof;
    String D3_IDProof;
    
    
    int S1_Photo;
    int S2_Photo;
    int S3_Photo;
    
    int D1_Photo;
    int D2_Photo;
    int D3_Photo;
    
    
    private void GenerateReceipt_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateReceipt_ButtonActionPerformed
         if(FormNo_Text.getText()!=null && FormNo_Text.getText().length()>0){
           if(MemberNo_Text.getText()!=null && MemberNo_Text.getText().length()>0){
             if(MemberName_text.getText()!=null && MemberName_text.getText().length()>0){
                 FormNo = FormNo_Text.getText().trim();
                 MemberNo = MemberNo_Text.getText().trim().toUpperCase();
                 MemberName = MemberName_text.getText().trim();
                 MemberType = memberType_combo.getSelectedItem().toString();
                 
                 if(MemberName_Check.isSelected()){
                     MemberNameMatches = 1;
                 }
                 else{
                     MemberNameMatches = 0;
                 }
             
                 MemberIDProof = IdProffMember_combo.getSelectedItem().toString();
                 if(IdProofMember_Check.isSelected()){
                     MemberPhoto =1;
                 }
                 else{
                     MemberPhoto=0;
                 }
                 
                 
                 if(IdProffSpouse_combo.getSelectedIndex()!=-1){
                     SpouseID = IdProffSpouse_combo.getSelectedItem().toString();
                 }
                 else{
                     SpouseID="";
                 }
                 
                 if(IdProofSpouse_Check.isSelected()){
                     SpousePhoto =1;
                 }
                 else{
                     SpousePhoto=0;
                 }
                 
                 if(IdProffFather_combo.getSelectedIndex()!=-1){
                     FatherId = IdProffFather_combo.getSelectedItem().toString();
                 }
                 else{
                     FatherId="";
                 }
                 
                 if(IdProofFather_Check.isSelected()){
                     FatherPhoto =1;
                 }
                 else{
                     FatherPhoto=0;
                 }
                 
                 
                 if(IdProfMother_combo.getSelectedIndex()!=-1){
                     MotherId = IdProfMother_combo.getSelectedItem().toString();
                 }
                 else{
                     MotherId="";
                 }
                 
                 if(IdProofMother_Check.isSelected()){
                     MotherPhoto =1;
                 }
                 else{
                     MotherPhoto=0;
                 }
             
             
                 if(son_nos_combo.getSelectedIndex()!=-1){
                     SonNos = Integer.parseInt(son_nos_combo.getSelectedItem().toString());
                 } 
                 else{
                     SonNos=0;
                 }
                 
                 if(daughter_nos_combo.getSelectedIndex()!=-1){
                     DaughterNos = Integer.parseInt(daughter_nos_combo.getSelectedItem().toString());
                     
                 } 
                 else{
                     DaughterNos=0;
                 }
                 
             
                 if(PhotoMatches_check.isSelected()){
                     SPD_Matches = 1;
                 }
                 else{
                     SPD_Matches=0;
                 }
                 
                 if(PhoneNo_Check.isSelected()){
                     PhoneNoMatches = 1;
                 }
                 else{
                     PhoneNoMatches=0;
                 }
                 
                 if(EmailId_check.isSelected()){
                     EmailIdMatches = 1;
                 }
                 else{
                     EmailIdMatches=0;
                 }
                 
             
                 ReceiptNo="";
                 
                 
                 
                
                
                
                 
                 
                 if(srNo3_Yesradio.isSelected()){
                     Date_3 = 1;
                 }else{
                     Date_3 = 0;
                 }
                 if(srNo10_Yesradio.isSelected()){
                     Date_10 = 1;
                 }else{
                     Date_10 = 0;
                 }
                 if(srNo13_Yesradio.isSelected()){
                     Date_13 = 1;
                 }else{
                     Date_13 = 0;
                 }
                 if(srNo14_Yesradio.isSelected()){
                     Date_14 = 1;
                 }else{
                     Date_14 = 0;
                 }
                 
                 
                 
                 
                 
                 
                 if(s1_idproff_combo.getSelectedIndex()!=-1){
                     S1_IDProof = s1_idproff_combo.getSelectedItem().toString();
                 }else{
                     S1_IDProof=null;
                 }
                 if(s2_idproff_combo.getSelectedIndex()!=-1){
                     S2_IDProof = s2_idproff_combo.getSelectedItem().toString();
                 }else{
                     S2_IDProof=null;
                 }
                 if(s3_idproff_combo2.getSelectedIndex()!=-1){
                     S3_IDProof = s3_idproff_combo2.getSelectedItem().toString();
                 }else{
                     S3_IDProof=null;
                 }
                 
                 
                 if(d1_idproff_combo.getSelectedIndex()!=-1){
                     D1_IDProof = d1_idproff_combo.getSelectedItem().toString();
                 }else{
                     D1_IDProof=null;
                 }
                 if(d2_idproff_combo.getSelectedIndex()!=-1){
                      D2_IDProof = d2_idproff_combo.getSelectedItem().toString();
                 }else{
                      D2_IDProof=null;
                 }
                 if(d3_idproff_combo.getSelectedIndex()!=-1){
                      D3_IDProof = d3_idproff_combo.getSelectedItem().toString();
                 }else{
                      D3_IDProof=null;
                 }
                 
                 
                 if(s1_photo_check.isSelected()){
                     S1_Photo=1;
                 }
                 else{
                     S1_Photo=0;
                 }
                 if(s2_photo_check.isSelected()){
                     S2_Photo=1;
                 }
                 else{
                     S2_Photo=0;
                 }
                 
                 if(s3_photo_check.isSelected()){
                     S3_Photo=1;
                 }
                 else{
                      S3_Photo=0;
                 }
                 
                 
                 
                 if(d1_photo_check.isSelected()){
                      D1_Photo=1;
                 }
                 else{
                     D1_Photo=0;
                 }
                 if(d2_photo_check.isSelected()){
                     D2_Photo=1;
                 }
                 else{
                      D2_Photo=0;
                 }
                 if(d3_photo_check.isSelected()){
                      D3_Photo=1;
                 }
                 else{
                      D3_Photo=0;
                 }
                 
                 
                 
                 
                 
                 try {
                     
                     ReceiptNo = getNextBillID();
                     
                     if(ReceiptNo!=null && ReceiptNo.length()>0){
                     
                            int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO  kym_documentreceipt  \n" +
                                    "(ID,FORMNO,MEMNO,MEMBERNAME,RECEIPTNO,MEMTYPE,IDPROOF,PHOTO,SPOUSEID,SPOUSEPHOTO,FATHERID,FATHERPHOTO,\n" +
                                    "MOTHERID,MOTHERPHOTO,SONID,SONPHOTO,DAUGHTERID,DAUGHTERPHOTO,DATEFORMAT,SPDMATCHES,PHONEMATCHES,EMAILMATCHES,\n" +
                                    "CRDATE,  CRBY , SONNOS , DAUGHTERNOS , DOBFLAG , DOMFLAG , DOCFLAG , DOPFLAG  , s1id , s2id , s3id , d1id , d2id , d3id , S1PHOTO , S2PHOTO , S3PHOTO , D1PHOTO , D2PHOTO , D3PHOTO , NOS_PHOTOS , NOS_DOC )\n" +
                                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.STRING, Datas.STRING ,Datas.STRING  , Datas.STRING
                                            , Datas.INT , Datas.STRING , Datas.INT , Datas.STRING,Datas.INT , Datas.STRING , Datas.INT,Datas.STRING,Datas.STRING ,Datas.STRING ,
                                            Datas.STRING ,Datas.STRING,Datas.INT ,Datas.INT,Datas.INT,Datas.TIMESTAMP,Datas.STRING , Datas.INT , Datas.INT , Datas.INT , Datas.INT  , Datas.INT , Datas.INT , Datas.STRING,Datas.STRING ,Datas.STRING , Datas.STRING,Datas.STRING ,Datas.STRING , Datas.INT,Datas.INT ,Datas.INT ,  Datas.INT,Datas.INT ,Datas.INT , Datas.INT , Datas.INT   })
                            ).exec(new Object[]{UUID.randomUUID().toString(),FormNo , MemberNo ,MemberName , ReceiptNo , MemberType ,
                                MemberIDProof,MemberPhoto, SpouseID , SpousePhoto , FatherId , FatherPhoto , MotherId,MotherPhoto,                                                                                                
                                SonID,SonPhoto,DaughterId,DaughterPhoto,DateFormat,SPD_Matches,PhoneNoMatches,EmailIdMatches ,new Date() , m_App.getAppUserView().getUser().getName() , SonNos , DaughterNos , Date_3 , Date_10 , Date_13 , Date_14 , S1_IDProof , S2_IDProof , S3_IDProof , D1_IDProof , D2_IDProof , D3_IDProof , S1_Photo , S2_Photo , S3_Photo , D1_Photo , D2_Photo , D3_Photo , NO_OF_PHOTOS , NO_OF_SCAN_DOCUMENTS  });


                            int x = new StaticSentence(m_App.getSession()
                                    , "UPDATE kym_sequence SET maxno=maxno+1 WHERE ACTIVE=1  "
                                    , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING }))
                                      .exec(new Object[] {"sh","sh"});
                            
                            MemberNo_Text.setText(null);
                             PrintReceipt();
                            Loaddata();
                               
                             JOptionPane.showMessageDialog(this, "Document Data Inserted Successfully", "Success Message", JOptionPane.OK_OPTION);
                     
                     }     
                      
                 
                 } catch (BasicException ex) {
                     Logger.getLogger(DocumentReceiptMenu.class.getName()).log(Level.SEVERE, null, ex);
                 }

                 
                 
                 
                 
                 
                 
                 
             }
             else{
                 JOptionPane.showMessageDialog(this, "Please enter Member Name.  ", "Warning", JOptionPane.WARNING_MESSAGE);
             }
           }
           else{
               JOptionPane.showMessageDialog(this, "Please enter Membership No.  ", "Warning", JOptionPane.WARNING_MESSAGE);
           }
         }
         else{
             JOptionPane.showMessageDialog(this, "Please enter Form No.  ", "Warning", JOptionPane.WARNING_MESSAGE);
         }
    }//GEN-LAST:event_GenerateReceipt_ButtonActionPerformed

    private void son_nos_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_son_nos_comboItemStateChanged
        if(son_nos_combo.getSelectedIndex()!=-1){
            s1_photo_check.setSelected(false);
              s2_photo_check.setSelected(false);
                     s3_photo_check.setSelected(false);
            int nos = Integer.parseInt(son_nos_combo.getSelectedItem().toString());
            if(nos>0){
                int snos = Integer.parseInt(son_nos_combo.getSelectedItem().toString());
                if(snos==1){
                     s1_panel.setVisible(true);
                     s2_panel.setVisible(false);
                     s3_panel.setVisible(false);
                     
                     s2_photo_check.setSelected(false);
                     s3_photo_check.setSelected(false);
                             
                     s2_idproff_combo.setSelectedIndex(-1);
                     s3_idproff_combo2.setSelectedIndex(-1);
                             
                }
                if(snos==2){
                    s1_panel.setVisible(true);
                     s2_panel.setVisible(true);
                     s3_panel.setVisible(false);
                     s3_photo_check.setSelected(false);
                      s3_idproff_combo2.setSelectedIndex(-1);
                }
                if(snos==3){
                    s1_panel.setVisible(true);
                     s2_panel.setVisible(true);
                     s3_panel.setVisible(true);
                }
                
                 
                
                
                
                son_main_panel.setVisible(true);
            }
            else{
                son_main_panel.setVisible(false);
                s1_idproff_combo.setSelectedIndex(-1);
                s2_idproff_combo.setSelectedIndex(-1);
                     s3_idproff_combo2.setSelectedIndex(-1);
                
            }
            
            
        }
        else{
            son_main_panel.setVisible(false);
        }
    }//GEN-LAST:event_son_nos_comboItemStateChanged

    private void daughter_nos_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_daughter_nos_comboItemStateChanged
       if(daughter_nos_combo.getSelectedIndex()!=-1){
            int nos = Integer.parseInt(daughter_nos_combo.getSelectedItem().toString());
            if(nos>0){
                
                int snos = Integer.parseInt(daughter_nos_combo.getSelectedItem().toString());
                if(snos==1){
                     d1_panel.setVisible(true);
                     d2_panel.setVisible(false);
                     d3_panel.setVisible(false);
                     
                     d2_photo_check.setSelected(false);
                     d3_photo_check.setSelected(false);
                     
                     d2_idproff_combo.setSelectedIndex(-1);
                      d3_idproff_combo.setSelectedIndex(-1);
                             
                }
                if(snos==2){
                    d1_panel.setVisible(true);
                     d2_panel.setVisible(true);
                     d3_panel.setVisible(false);
                     d3_photo_check.setSelected(false);
                     d3_idproff_combo.setSelectedIndex(-1);
                }
                if(snos==3){
                    d1_panel.setVisible(true);
                     d2_panel.setVisible(true);
                     d3_panel.setVisible(true);
                     
                      
                }
                
                
                
                jPanel4.setVisible(true);
            }
            else{
                jPanel4.setVisible(false);
                d1_idproff_combo.setSelectedIndex(-1);
                d2_idproff_combo.setSelectedIndex(-1);
                      d3_idproff_combo.setSelectedIndex(-1);
                
                
            }
            
            
        }
        else{
            jPanel4.setVisible(false);
        }
    }//GEN-LAST:event_daughter_nos_comboItemStateChanged

    private void Reprint_Receipt_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reprint_Receipt_ButtonActionPerformed
        PrintReceipt();
    }//GEN-LAST:event_Reprint_Receipt_ButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DocumentReceiptListForm memList;
        try {
            memList = DocumentReceiptListForm.getDialog(this, m_App,true);
            memList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(DocumentReceiptListForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void s1_photo_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_s1_photo_checkItemStateChanged
       if(s1_photo_check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_s1_photo_checkItemStateChanged

    private void s2_photo_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_s2_photo_checkItemStateChanged
       if(s2_photo_check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_s2_photo_checkItemStateChanged

    private void s3_photo_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_s3_photo_checkItemStateChanged
     if(s3_photo_check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_s3_photo_checkItemStateChanged

    private void d1_photo_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_d1_photo_checkItemStateChanged
      if(d1_photo_check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_d1_photo_checkItemStateChanged

    private void d2_photo_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_d2_photo_checkItemStateChanged
       if(d2_photo_check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_d2_photo_checkItemStateChanged

    private void d3_photo_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_d3_photo_checkItemStateChanged
        if(d3_photo_check.isSelected()){
            NO_OF_PHOTOS =    NO_OF_PHOTOS+1;
       }
       else{
           NO_OF_PHOTOS =    NO_OF_PHOTOS-1;
       }
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
    }//GEN-LAST:event_d3_photo_checkItemStateChanged

    private void IdProffMember_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProffMember_comboItemStateChanged
       
        int d = MEMBER_ID_COMBO_INDEX;
        
       if(IdProffMember_combo.getSelectedIndex()!=-1){
           if(d==-1){
                NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
            } 
           IdProofMember_Check.setEnabled(true);
          
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
            IdProofMember_Check.setEnabled(false);
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
        MEMBER_ID_COMBO_INDEX = IdProffMember_combo.getSelectedIndex();
        
        
    }//GEN-LAST:event_IdProffMember_comboItemStateChanged

    private void IdProffSpouse_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProffSpouse_comboItemStateChanged
        
        int d = SPOUSE_ID_COMBO_INDEX;
        if(IdProffSpouse_combo.getSelectedIndex()!=-1){
           if(d==-1){
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
           }
           IdProofSpouse_Check.setEnabled(true); //Edited By: Shoeb
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
            IdProofSpouse_Check.setEnabled(false); //Edited By: Shoeb
        }
        
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
        SPOUSE_ID_COMBO_INDEX = IdProffSpouse_combo.getSelectedIndex();
        
    }//GEN-LAST:event_IdProffSpouse_comboItemStateChanged

    private void IdProffFather_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProffFather_comboItemStateChanged

        int d = FATHER_ID_COMBO_INDEX;
        if(IdProffFather_combo.getSelectedIndex()!=-1){
           if(d==-1){
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
           }
           IdProofFather_Check.setEnabled(true);
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
        FATHER_ID_COMBO_INDEX = IdProffFather_combo.getSelectedIndex();
        
    }//GEN-LAST:event_IdProffFather_comboItemStateChanged

    private void IdProfMother_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdProfMother_comboItemStateChanged
         
        int d = MOTHER_ID_COMBO_INDEX;
        if(IdProfMother_combo.getSelectedIndex()!=-1){
            if(d==-1){
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
            }
            IdProofMother_Check.setEnabled(true);
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
        MOTHER_ID_COMBO_INDEX = IdProfMother_combo.getSelectedIndex();
        
    }//GEN-LAST:event_IdProfMother_comboItemStateChanged

    private void s1_idproff_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_s1_idproff_comboItemStateChanged
         
        int d = S1_ID_COMBO_INDEX;
        if(s1_idproff_combo.getSelectedIndex()!=-1){
           if(d==-1){ 
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
           }
          
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
         S1_ID_COMBO_INDEX = s1_idproff_combo.getSelectedIndex();
        
        
    }//GEN-LAST:event_s1_idproff_comboItemStateChanged

    private void s2_idproff_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_s2_idproff_comboItemStateChanged
         
        int d = S2_ID_COMBO_INDEX;
        if(s2_idproff_combo.getSelectedIndex()!=-1){
           if(d==-1){ 
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
           }
          
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
         S2_ID_COMBO_INDEX = s2_idproff_combo.getSelectedIndex();
        
    }//GEN-LAST:event_s2_idproff_comboItemStateChanged

    private void s3_idproff_combo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_s3_idproff_combo2ItemStateChanged
       
        int d = S3_ID_COMBO_INDEX;
        if(s3_idproff_combo2.getSelectedIndex()!=-1){
          if(d==-1){  
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
          }
       
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
         S3_ID_COMBO_INDEX = s3_idproff_combo2.getSelectedIndex();
        
    }//GEN-LAST:event_s3_idproff_combo2ItemStateChanged

    private void d1_idproff_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_d1_idproff_comboItemStateChanged
        int d = D1_ID_COMBO_INDEX;  
        if(d1_idproff_combo.getSelectedIndex()!=-1){
          if(d==-1){  
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
          }
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
         D1_ID_COMBO_INDEX = d1_idproff_combo.getSelectedIndex();
    }//GEN-LAST:event_d1_idproff_comboItemStateChanged

    private void d2_idproff_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_d2_idproff_comboItemStateChanged
         
        int d = D2_ID_COMBO_INDEX;  
        if(d2_idproff_combo.getSelectedIndex()!=-1){
          if(d==-1){  
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
          }
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
         D2_ID_COMBO_INDEX = d2_idproff_combo.getSelectedIndex();
        
    }//GEN-LAST:event_d2_idproff_comboItemStateChanged

    private void d3_idproff_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_d3_idproff_comboItemStateChanged
         
         int d = D3_ID_COMBO_INDEX;  
        if(d3_idproff_combo.getSelectedIndex()!=-1){
          if(d==-1){  
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS+1;
            }
        }
        else{
            NO_OF_SCAN_DOCUMENTS = NO_OF_SCAN_DOCUMENTS-1;
        }
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
        D3_ID_COMBO_INDEX = d3_idproff_combo.getSelectedIndex();
        
    }//GEN-LAST:event_d3_idproff_comboItemStateChanged

    private void IdProofMember_CheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProofMember_CheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdProofMember_CheckActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Children_check;
    private javax.swing.JPanel Children_panel;
    private javax.swing.JLabel DocumentInt_text;
    private javax.swing.JCheckBox Document_Nos_Match_Check;
    private javax.swing.JCheckBox EmailId_check;
    private javax.swing.JCheckBox Father_check;
    private javax.swing.JTextField FormNo_Text;
    private javax.swing.JButton GenerateReceipt_Button;
    private javax.swing.JComboBox<String> IdProfMother_combo;
    private javax.swing.JComboBox<String> IdProffFather_combo;
    private javax.swing.JComboBox<String> IdProffMember_combo;
    private javax.swing.JComboBox<String> IdProffSpouse_combo;
    private javax.swing.JCheckBox IdProofFather_Check;
    private javax.swing.JCheckBox IdProofMember_Check;
    private javax.swing.JCheckBox IdProofMother_Check;
    private javax.swing.JCheckBox IdProofSpouse_Check;
    private javax.swing.JCheckBox MemberName_Check;
    private javax.swing.JTextField MemberName_text;
    private javax.swing.JTextField MemberNo_Text;
    private javax.swing.JLabel MemberType_Label;
    private javax.swing.JLabel MemberVisible_label;
    private javax.swing.JCheckBox Mother_check;
    private javax.swing.JPanel Mother_panel;
    private javax.swing.JCheckBox PhoneNo_Check;
    private javax.swing.JLabel PhotoInt_Text;
    private javax.swing.JCheckBox PhotoMatches_check;
    private javax.swing.JCheckBox Photo_Nos_Match_Check;
    private javax.swing.JButton Reprint_Receipt_Button;
    private javax.swing.JCheckBox Spouse_check;
    private javax.swing.JComboBox<String> d1_idproff_combo;
    private javax.swing.JPanel d1_panel;
    private javax.swing.JCheckBox d1_photo_check;
    private javax.swing.JComboBox<String> d2_idproff_combo;
    private javax.swing.JPanel d2_panel;
    private javax.swing.JCheckBox d2_photo_check;
    private javax.swing.JComboBox<String> d3_idproff_combo;
    private javax.swing.JPanel d3_panel;
    private javax.swing.JCheckBox d3_photo_check;
    private javax.swing.JPanel dateFormat_panel;
    private javax.swing.JComboBox<String> daughter_nos_combo;
    private javax.swing.JPanel father_panel;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel memberName_Lable;
    private javax.swing.JComboBox<String> memberType_combo;
    private javax.swing.JLabel receiptNo_text;
    private javax.swing.JComboBox<String> s1_idproff_combo;
    private javax.swing.JPanel s1_panel;
    private javax.swing.JCheckBox s1_photo_check;
    private javax.swing.JComboBox<String> s2_idproff_combo;
    private javax.swing.JPanel s2_panel;
    private javax.swing.JCheckBox s2_photo_check;
    private javax.swing.JComboBox<String> s3_idproff_combo2;
    private javax.swing.JPanel s3_panel;
    private javax.swing.JCheckBox s3_photo_check;
    private javax.swing.JPanel son_main_panel;
    private javax.swing.JComboBox<String> son_nos_combo;
    private javax.swing.JPanel spouse_panel;
    private javax.swing.JRadioButton srNo10_NAradio;
    private javax.swing.JRadioButton srNo10_Yesradio;
    private javax.swing.JRadioButton srNo13_NAradio;
    private javax.swing.JRadioButton srNo13_Yesradio;
    private javax.swing.JRadioButton srNo14_NAradio;
    private javax.swing.JRadioButton srNo14_Yesradio;
    private javax.swing.JRadioButton srNo3_NAradio;
    private javax.swing.JRadioButton srNo3_Yesradio;
    // End of variables declaration//GEN-END:variables


public String getTitle() {
   return "Document Receipt Menu";  
    }

    @Override
    public void activate() throws BasicException {
        
        Loaddata();
        /**
         * Edited By: Shoeb
         * on navigating from different pages back to Document Receipt Menu, Member No. Field was not getting cleared.
         */
         MemberNo_Text.setText(""); //edited : explaination above.  
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
        MemberNo_Text.setText(null);
    }

    @Override
    public Object getBean() {
  
    return this;
    }


    
    public void Loaddata() throws BasicException{
        
        memberName_Lable.setVisible(false);
        MemberType_Label.setVisible(false);
        
        MemberName_text.setText(null);
        FormNo_Text.setText(null);
        
        
       jScrollPane2.setVisible(false);
       MemberVisible_label.setVisible(false);
       NO_OF_PHOTOS=0;
        PhotoInt_Text.setText(NO_OF_PHOTOS+"");
        NO_OF_SCAN_DOCUMENTS=0;
        DocumentInt_text.setText(NO_OF_SCAN_DOCUMENTS+"");
        reset();
        
        Reprint_Receipt_Button.setVisible(false);
         MemberShipTypeList=new ArrayList<String>();
        MemberShipTypeList=getMemberTypeListAll();
        MemTypeComboBoxValModel=new ComboBoxValModel(MemberShipTypeList);
        memberType_combo.setModel(MemTypeComboBoxValModel);
        memberType_combo.setSelectedIndex(-1);
        
        IDProofTypeList = new ArrayList<String>();
        IDProofTypeList=GetIDProofList(m_App);
        IdProofComboBoxValModel = new ComboBoxValModel(IDProofTypeList);
        IdProffMember_combo.setModel(IdProofComboBoxValModel);
        
       IdProofComboBoxValModelFather = new ComboBoxValModel(IDProofTypeList);
        IdProffFather_combo.setModel(IdProofComboBoxValModelFather);    
        
        IdProofComboBoxValModelMpther = new ComboBoxValModel(IDProofTypeList);
        IdProfMother_combo.setModel(IdProofComboBoxValModelMpther);
        
        IDProofTypeListSpouse = new ArrayList<String>();
        IDProofTypeListSpouse=GetIDProofListForSpouse(m_App);
        IdProofComboBoxValModelSpouse = new ComboBoxValModel(IDProofTypeListSpouse);
        IdProffSpouse_combo.setModel(IdProofComboBoxValModelSpouse);
        ButtonGrp();
        
        srNo3_NAradio.setSelected(true);
        srNo10_NAradio.setSelected(true);
        srNo13_NAradio.setSelected(true);
        srNo14_NAradio.setSelected(true);
        receiptNo_text.setVisible(false);
        
            jLabel34.setVisible(false);
            srNo10_Yesradio.setVisible(false);
            srNo10_NAradio.setVisible(false);
            srNo10_Yesradio.setSelected(false);
            srNo10_NAradio.setSelected(true);
        
            jLabel35.setVisible(false);
            srNo13_Yesradio.setVisible(false);
            srNo13_NAradio.setVisible(false);
            srNo13_Yesradio.setSelected(false);
            srNo13_NAradio.setSelected(true);
            
            
            // for son and daughter 
            
            IDProofTypeListChildren = new ArrayList<String>();
            IDProofTypeListChildren=GetIDProofListForChildren(m_App);

            IdProofComboBoxValModelSon1 = new ComboBoxValModel(IDProofTypeListChildren);
            s1_idproff_combo.setModel(IdProofComboBoxValModelSon1);


            IdProofComboBoxValModelSon2 = new ComboBoxValModel(IDProofTypeListChildren);
            s2_idproff_combo.setModel(IdProofComboBoxValModelSon2);

            IdProofComboBoxValModelSon3 = new ComboBoxValModel(IDProofTypeListChildren);
            s3_idproff_combo2.setModel(IdProofComboBoxValModelSon3);

            IdProofComboBoxValModelDaughter1 = new ComboBoxValModel(IDProofTypeListChildren);
            d1_idproff_combo.setModel(IdProofComboBoxValModelDaughter1);

            IdProofComboBoxValModelDaughter2 = new ComboBoxValModel(IDProofTypeListChildren);
            d2_idproff_combo.setModel(IdProofComboBoxValModelDaughter2);

            IdProofComboBoxValModelDaughter3 = new ComboBoxValModel(IDProofTypeListChildren);
            d3_idproff_combo.setModel(IdProofComboBoxValModelDaughter3);
             MEMBER_ID_COMBO_INDEX =-1;
            FATHER_ID_COMBO_INDEX =-1;
            MOTHER_ID_COMBO_INDEX =-1;
            SPOUSE_ID_COMBO_INDEX =-1;
            S1_ID_COMBO_INDEX =-1;
            S2_ID_COMBO_INDEX =-1;
            S3_ID_COMBO_INDEX =-1;
            D1_ID_COMBO_INDEX =-1;
            D2_ID_COMBO_INDEX =-1;
            D3_ID_COMBO_INDEX =-1;
            
            
    }
    
    
    public void reset(){
        
         Spouse_check.setSelected(false);
         Father_check.setSelected(false);
         Mother_check.setSelected(false);
         Children_check.setSelected(false);
         /**
          * Edited by: Shoeb
          * Reason: The checkboxes where not getting unchecked.
          * Edit begins.
          */
         
         IdProofMember_Check.setSelected(false);
         IdProofMember_Check.setEnabled(false);
         s1_photo_check.setSelected(false);
         s2_photo_check.setSelected(false);
         s3_photo_check.setSelected(false);
         d1_photo_check.setSelected(false);
         d2_photo_check.setSelected(false);
         d3_photo_check.setSelected(false);
         PhotoMatches_check.setSelected(false);
         PhoneNo_Check.setSelected(false);
         EmailId_check.setSelected(false);
         Photo_Nos_Match_Check.setSelected(false);
         Document_Nos_Match_Check.setSelected(false);
         PhotoInt_Text.setText("0");
         DocumentInt_text.setText("0"); 
         
         memberType_combo.setSelectedIndex(-1);
         //Edit Ends
         
         spouse_panel.setVisible(false);
         father_panel.setVisible(false);
         Mother_panel.setVisible(false);
         Children_panel.setVisible(false);
         GenerateReceipt_Button.setVisible(false);
        
         son_nos_combo.setSelectedIndex(-1);
         daughter_nos_combo.setSelectedIndex(-1);
         receiptNo_text.setVisible(false);
    }
    
    
    
    public List getMemberTypeListAll() throws  BasicException{
       List<Object> Temp = new ArrayList();
        
   
        Temp =  new StaticSentence(m_App.getSession(), " select Name from memtype where active=1 order by name ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
                    
        return Temp;              
          
    }

    
    
    public List<String> GetIDProofList(AppView app ) throws BasicException{
        IDProofTypeList = new ArrayList<String>();
          Object  o = null;
          String Name="ID Proof";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  order by name  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      IDProofTypeList.add(Xarr[i].toString());
               }
              return IDProofTypeList;
          }
          else{
              return IDProofTypeList;
          }
      }
    
    public List<String> GetIDProofListForSpouse(AppView app ) throws BasicException{
            IDProofTypeListSpouse = new ArrayList<String>();
          Object  o = null;
          String Name="Spouse ID proof";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  order by name  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      IDProofTypeListSpouse.add(Xarr[i].toString());
               }
              return IDProofTypeListSpouse;
          }
          else{
              return IDProofTypeListSpouse;
          }
      }
    
    
    public List<String> GetIDProofListForChildren(AppView app ) throws BasicException{
            IDProofTypeListChildren = new ArrayList<String>();
          Object  o = null;
          String Name="Children ID Proof";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  order by name  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      IDProofTypeListChildren.add(Xarr[i].toString());
               }
              return IDProofTypeListChildren;
          }
          else{
              return IDProofTypeListChildren;
          }
      }
    
    
    
    
    
     public void ButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(srNo3_Yesradio);
        bg.add(srNo3_NAradio);
        
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(srNo10_Yesradio);
        bg1.add(srNo10_NAradio);
        
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(srNo13_Yesradio);
        bg3.add(srNo13_NAradio);
        
        ButtonGroup bg4 = new ButtonGroup();
        bg4.add(srNo14_Yesradio);
        bg4.add(srNo14_NAradio);
        
    
     }
     
     
     
     
     
     
     
     public void setData(){
         for(int i=0;i<DocumentDataList.size();i++){
             
                DocumentReceiptTableModel.DocumentReceiptListInfo DocRec  = DocumentDataList.get(i);
             
                ReceiptNo =  DocRec.getReceiptNo();
                receiptNo_text.setText("Receipt No : " +ReceiptNo);
                FormNo  = DocRec.getFormNo();
                FormNo_Text.setText(FormNo);
                MemberNo = DocRec.getMemberNo();
                MemberName  = DocRec.getMemberName();
                MemberName_text.setText(MemberName);
                MemberType  = DocRec.getMemberType();
                
                MemberIDProof = DocRec.getMemberIDProof();
                for(int i1=0;i1<IDProofTypeList.size();i1++){
                    String c = IDProofTypeList.get(i1).toString();
                    if(c.equals(MemberIDProof)){
                        IdProffMember_combo.setSelectedIndex(i1);
                        break;
                    }
                    
                }
                
                MemberPhoto = DocRec.getMemberPhoto();
                if(MemberPhoto==1){
                    IdProofMember_Check.setSelected(true);
                }
                
                SpouseID = DocRec.getSpouseID();
                if(SpouseID!=null && SpouseID.length()>0){
                   Spouse_check.setSelected(true);
                   for(int i1=0;i1<IDProofTypeListSpouse.size();i1++){
                    String c = IDProofTypeListSpouse.get(i1).toString();
                    if(c.equals(SpouseID)){
                        IdProffSpouse_combo.setSelectedIndex(i1);
                        break;
                    }
                    
                }
                   
                   
                }
                else{
                    Spouse_check.setSelected(false);
                }
                
                
                SpousePhoto = DocRec.getSpousePhoto();
                if(SpousePhoto==1){
                    IdProofSpouse_Check.setSelected(true);
                }
                else{
                     IdProofSpouse_Check.setSelected(false);
                }
                
                
                
                FatherId = DocRec.getFatherId();
                if(FatherId!=null && FatherId.length()>0){
                   Father_check.setSelected(true);
                   for(int i1=0;i1<IDProofTypeList.size();i1++){
                    String c = IDProofTypeList.get(i1).toString();
                    if(c.equals(FatherId)){
                        IdProffFather_combo.setSelectedIndex(i1);
                        break;
                    }
                    
                }
                   
                   
                }
                else{
                    Father_check.setSelected(false);
                }
                
                
                
                FatherPhoto = DocRec.getFatherPhoto();
                if(FatherPhoto==1){
                    IdProofFather_Check.setSelected(true);
                }
                else{
                     IdProofFather_Check.setSelected(false);
                }
                
                
                
                
                MotherId = DocRec.getMotherId();
                if(MotherId!=null && MotherId.length()>0){
                   Mother_check.setSelected(true);
                   for(int i1=0;i1<IDProofTypeList.size();i1++){
                    String c = IDProofTypeList.get(i1).toString();
                    if(c.equals(MotherId)){
                        IdProfMother_combo.setSelectedIndex(i1);
                        break;
                    }
                    
                }
                   
                   
                }
                else{
                    Mother_check.setSelected(false);
                }
                
                
                
                
                MotherPhoto = DocRec.getMotherPhoto();
                if(MotherPhoto==1){
                    IdProofMother_Check.setSelected(true);
                }
                else{
                     IdProofMother_Check.setSelected(false);
                }
                
                
                SonID = DocRec.getSonID();
                
                
                DaughterId = DocRec.getDaughterId();
               



                SonNos = DocRec.getSonNos();
                if(SonNos>0){
                     son_nos_combo.setSelectedItem(SonNos);
                }
                else{
                    son_nos_combo.setSelectedIndex(-1);
                }
                
                
                DaughterNos = DocRec.getDaughterNos();
                if(DaughterNos>0){
                     daughter_nos_combo.setSelectedItem(DaughterNos);
                }
                else{
                    daughter_nos_combo.setSelectedIndex(-1);
                }
                
                
                
                SPD_Matches = DocRec.getSPD_Matches();
                if(SPD_Matches==1){
                    PhotoMatches_check.setSelected(true);
                }
                else{
                    PhotoMatches_check.setSelected(false);
                }
                
                PhoneNoMatches = DocRec.getPhoneNoMatches();
                
                if(PhoneNoMatches==1){
                    PhoneNo_Check.setSelected(true);
                }
                else{
                    PhoneNo_Check.setSelected(false);
                }
                
                
                EmailIdMatches = DocRec.getEmailIdMatches();
                if(EmailIdMatches==1){
                    EmailId_check.setSelected(true);
                }
                else{
                    EmailId_check.setSelected(false);
                }
                
                
                SonPhoto= DocRec.getSonPhoto();
               
                
                DaughterPhoto = DocRec.getDaughterPhoto();
               
                
                DateFormat = DocRec.getDateFormat();

                Date_3 =  DocRec.getDate_3();
                if(Date_3==1){
                    srNo3_Yesradio.setSelected(true);
                }
                else{
                    srNo3_Yesradio.setSelected(false);
                }
                
                Date_10 = DocRec.getDate_10();
                if(Date_10==1){
                    srNo10_Yesradio.setSelected(true);
                }
                else{
                    srNo10_Yesradio.setSelected(false);
                }
                
                
                Date_13 = DocRec.getDate_13();
                if(Date_13==1){
                    srNo13_Yesradio.setSelected(true);
                }
                else{
                    srNo13_Yesradio.setSelected(false);
                }
                
                
                Date_14= DocRec.getDate_14();
                if(Date_14==1){
                    srNo14_Yesradio.setSelected(true);
                }
                else{
                    srNo14_Yesradio.setSelected(false);
                }
             
             
             
             
             
             
             
             
         }
         
         
         
         
         
     }
     
     
     
     
     
     private String getNextBillID() throws BasicException{
        
        String billnum;
        String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
     Object[] obj=(Object[])new  StaticSentence(m_App.getSession()
            , "SELECT series,maxno  FROM kym_sequence where active=1"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING})
            ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.DOUBLE})).find(new Object[]{uname,uname});
     if(obj!=null){
         Double max=Double.parseDouble(obj[1].toString());
         max++;
         billnum=obj[0].toString()+ max.intValue();
         return billnum;   
        }
     else{
          JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.WARNING_MESSAGE);
         return "";
     }
       
     }
     
     
     
      private TicketParser m_TTP;
     
      public void PrintReceipt( ){
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.kymDocumentReceipt");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
         

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            
            
            
            script.put("maintitle", "KYM Document Receipt");
            script.put("Reciept_title", "Receipt No :");
            script.put("receipt",  ReceiptNo );
            script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
            script.put("cname", MemberName);
            script.put("cno", MemberNo);
            
            String temp = "Received KYM form no. : "+FormNo;
            script.put("label_6", temp);
            script.put("label_7", "Containing details : ");
            script.put("label_8", "No of Photos : ");
            script.put("label_9", "No of Documents : ");
            
            
            script.put("text8", PhotoInt_Text.getText());
            script.put("text9", DocumentInt_text.getText());
            
            
            
            
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
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
     
     
     
     
     
     
}
