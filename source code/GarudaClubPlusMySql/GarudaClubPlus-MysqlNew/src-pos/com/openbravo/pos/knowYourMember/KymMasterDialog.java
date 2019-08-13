

package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.sms.EmailMasterTableModel;
import com.openbravo.pos.sms.MemberEmailList;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

public class KymMasterDialog extends javax.swing.JDialog {

    private AppView app;
    private boolean flag;
    private List<String> CityList = new ArrayList<String>();
    private List<String> CountryList = new ArrayList<String>();
    private List<String> StateList = new ArrayList<String>();
    private List<String> MobileOsList = new ArrayList<String>();
    private List<String> ProfessionList = new ArrayList<String>();
    private List<String> ClubActivityList = new ArrayList<String>();
    private List<String> FacilityList = new ArrayList<String>();
    private List<String> IDProofTypeList = new ArrayList<String>();
    private List<String> BloodGroupList = new ArrayList<String>();
    private List<String> OtherClubList = new ArrayList<String>();
    
    
    private ComboBoxValModel CityComboBoxValModel;
    private ComboBoxValModel StateComboBoxValModel;
    private ComboBoxValModel CountryComboBoxValModel;
    private ComboBoxValModel MobileOSComboBoxValModel;
    private ComboBoxValModel professionComboBoxValModel;
    private ComboBoxValModel ClubActivityComboBoxValModel;
    private ComboBoxValModel FacilityComboBoxValModel;
    private ComboBoxValModel IdProofComboBoxValModel;
    private ComboBoxValModel BloodGroupComboBoxValModel;
    private ComboBoxValModel OtherClubComboBoxValModel;
    
    
    public KymMasterDialog() {
        initComponents();
    }

    
    public KymMasterDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }

    
  
    
    public KymMasterDialog(java.awt.Dialog parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
     public KymMasterDialog(java.awt.Frame parent,  AppView app, boolean flag) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
    }
    
    
     
     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Country_Text = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Country_Combo = new javax.swing.JComboBox();
        CountryAdd_button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        city_text1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        city_combo = new javax.swing.JComboBox();
        CityAdd_button = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        State_text = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        State_Combo = new javax.swing.JComboBox();
        StateAdd_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        IDProof_Text = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        IDProof_combo = new javax.swing.JComboBox();
        IDProof_Add_Button = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        MobileOS_Text = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        MobileOS_Combo = new javax.swing.JComboBox();
        MobileOSAdd_button = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ClubActivity_Text1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ClubActivity_Combo = new javax.swing.JComboBox();
        ClubActivityAdd_button = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Facility_Text2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Facility_combo = new javax.swing.JComboBox();
        FacilityAdd_button = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        Profession_Text2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Profession_combo = new javax.swing.JComboBox();
        ProfessionAdd_button = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        BloodGroup_Text = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        BloodGroupAdd_Combo = new javax.swing.JComboBox();
        BloodGroupAdd_button = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        OtherClubName_Text = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        OtherClubName_Combo = new javax.swing.JComboBox();
        OtherClubName_button = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Country Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel1.setText("Enter Country ");

        jLabel2.setText("List Of Country Added");

        Country_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        CountryAdd_button.setText("Add");
        CountryAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountryAdd_buttonActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(Country_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(CountryAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(Country_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Country_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountryAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Country_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "City Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel3.setText("Enter City ");

        jLabel4.setText("List Of City Added");

        city_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        CityAdd_button.setText("Add");
        CityAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CityAdd_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(city_text1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(CityAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(city_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(city_text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CityAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(city_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "State Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel7.setText("Enter State");

        jLabel8.setText("List Of State Added");

        State_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        StateAdd_button.setText("Add");
        StateAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StateAdd_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(State_text, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(StateAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(27, 27, 27)
                        .addComponent(State_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(State_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StateAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(State_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("City/Country/State", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ID Proof Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel5.setText("Enter ID Proof ");

        jLabel6.setText("List Of Id Proof Added");

        IDProof_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        IDProof_Add_Button.setText("Add");
        IDProof_Add_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDProof_Add_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28)
                        .addComponent(IDProof_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(IDProof_Add_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(IDProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(IDProof_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDProof_Add_Button))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(IDProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Mobile OS  Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel9.setText("Enter Mobile OS");

        jLabel10.setText("List Of Mobile OS Added");

        MobileOS_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        MobileOSAdd_button.setText("Add");
        MobileOSAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MobileOSAdd_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28)
                        .addComponent(MobileOS_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(MobileOSAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(27, 27, 27)
                        .addComponent(MobileOS_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(MobileOS_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MobileOSAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(MobileOS_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(204, 204, 204))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ID Proof / Mobile OS", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Club Activities Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel11.setText("Enter Club Activity ");

        jLabel12.setText("List Of Club Activity  Added");

        ClubActivity_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        ClubActivityAdd_button.setText("Add");
        ClubActivityAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClubActivityAdd_buttonActionPerformed(evt);
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
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addComponent(ClubActivity_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(ClubActivityAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(27, 27, 27)
                        .addComponent(ClubActivity_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ClubActivity_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClubActivityAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(ClubActivity_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Facilities Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel13.setText("Enter Facility master ");

        jLabel14.setText("List Of Facilities  Added");

        Facility_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        FacilityAdd_button.setText("Add");
        FacilityAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacilityAdd_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)
                        .addComponent(Facility_Text2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(FacilityAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(27, 27, 27)
                        .addComponent(Facility_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Facility_Text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FacilityAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Facility_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(204, 204, 204))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Club Activities / Facilities", jPanel8);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Profession Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel15.setText("Enter Profession ");

        jLabel16.setText("List Of Profession  Added");

        Profession_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        ProfessionAdd_button.setText("Add");
        ProfessionAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfessionAdd_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28)
                        .addComponent(Profession_Text2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(ProfessionAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(27, 27, 27)
                        .addComponent(Profession_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(Profession_Text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProfessionAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(Profession_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Blood Group Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel18.setText("Enter Blood Group");

        jLabel19.setText("List Of Blood group Added");

        BloodGroupAdd_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        BloodGroupAdd_button.setText("Add");
        BloodGroupAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BloodGroupAdd_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(28, 28, 28)
                        .addComponent(BloodGroup_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(BloodGroupAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(BloodGroupAdd_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(BloodGroup_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BloodGroupAdd_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(BloodGroupAdd_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(204, 204, 204))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Profession / Blood Group", jPanel11);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Other Club Names", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel20.setText("Enter Club Name");

        jLabel21.setText("List Of Clubs Added");

        OtherClubName_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        OtherClubName_button.setText("Add");
        OtherClubName_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherClubName_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(28, 28, 28)
                        .addComponent(OtherClubName_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(OtherClubName_button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(27, 27, 27)
                        .addComponent(OtherClubName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(OtherClubName_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OtherClubName_button))
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(OtherClubName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(204, 204, 204))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Other Club Info", jPanel14);

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(164, 13, 13));
        jLabel17.setText("Master ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CityAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CityAdd_buttonActionPerformed
        if(city_text1.getText()!=null && city_text1.getText().trim().length()>0 ){
            
            String Name = city_text1.getText().trim();
            
            int flag=0;
            for(int i=0;i<CityList.size();i++){
                String x = CityList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               CityList.add(Name);
               String Str="";
               for(int i=0;i<CityList.size();i++){
                   String x = CityList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="City";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    city_text1.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_CityAdd_buttonActionPerformed

    private void CountryAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CountryAdd_buttonActionPerformed
       if(Country_Text.getText()!=null && Country_Text.getText().trim().length()>0 ){
            
            String Name = Country_Text.getText().trim();
            
            int flag=0;
            for(int i=0;i<CountryList.size();i++){
                String x = CountryList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               CountryList.add(Name);
               String Str="";
               for(int i=0;i<CountryList.size();i++){
                   String x = CountryList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="COUNTRY";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    Country_Text.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_CountryAdd_buttonActionPerformed

    private void StateAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StateAdd_buttonActionPerformed
        if(State_text.getText()!=null && State_text.getText().trim().length()>0 ){
            
            String Name = State_text.getText().trim();
            
            int flag=0;
            for(int i=0;i<StateList.size();i++){
                String x = StateList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               StateList.add(Name);
               String Str="";
               for(int i=0;i<StateList.size();i++){
                   String x = StateList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="STATE";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    State_text.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_StateAdd_buttonActionPerformed

    private void IDProof_Add_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDProof_Add_ButtonActionPerformed
        if(IDProof_Text.getText()!=null && IDProof_Text.getText().trim().length()>0 ){
            
            String Name = IDProof_Text.getText().trim();
            
            int flag=0;
            for(int i=0;i<IDProofTypeList.size();i++){
                String x = IDProofTypeList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               IDProofTypeList.add(Name);
               String Str="";
               for(int i=0;i<IDProofTypeList.size();i++){
                   String x = IDProofTypeList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="ID Proof";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    IDProof_Text.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_IDProof_Add_ButtonActionPerformed

    private void MobileOSAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MobileOSAdd_buttonActionPerformed
        if(MobileOS_Text.getText()!=null && MobileOS_Text.getText().trim().length()>0 ){
            
            String Name = MobileOS_Text.getText().trim();
            
            int flag=0;
            for(int i=0;i<MobileOsList.size();i++){
                String x = MobileOsList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               MobileOsList.add(Name);
               String Str="";
               for(int i=0;i<MobileOsList.size();i++){
                   String x = MobileOsList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="MOBILE OS";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    MobileOS_Text.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MobileOSAdd_buttonActionPerformed

    private void ClubActivityAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClubActivityAdd_buttonActionPerformed
        if(ClubActivity_Text1.getText()!=null && ClubActivity_Text1.getText().trim().length()>0 ){
            
            String Name = ClubActivity_Text1.getText().trim();
            int flag=0;
            for(int i=0;i<ClubActivityList.size();i++){
                String x = ClubActivityList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               ClubActivityList.add(Name);
               String Str="";
               for(int i=0;i<ClubActivityList.size();i++){
                   String x = ClubActivityList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="Club Activity";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    ClubActivity_Text1.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ClubActivityAdd_buttonActionPerformed

    private void FacilityAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacilityAdd_buttonActionPerformed
       if(Facility_Text2.getText()!=null && Facility_Text2.getText().trim().length()>0 ){
            
            String Name = Facility_Text2.getText().trim();
            int flag=0;
            for(int i=0;i<FacilityList.size();i++){
                String x = FacilityList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               FacilityList.add(Name);
               String Str="";
               for(int i=0;i<FacilityList.size();i++){
                   String x = FacilityList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="Facility";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    Facility_Text2.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_FacilityAdd_buttonActionPerformed

    private void ProfessionAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfessionAdd_buttonActionPerformed
      if(Profession_Text2.getText()!=null && Profession_Text2.getText().trim().length()>0 ){
            
            String Name = Profession_Text2.getText().trim();
            int flag=0;
            for(int i=0;i<ProfessionList.size();i++){
                String x = ProfessionList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               ProfessionList.add(Name);
               String Str="";
               for(int i=0;i<ProfessionList.size();i++){
                   String x = ProfessionList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="Profession";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    Profession_Text2.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ProfessionAdd_buttonActionPerformed

    private void BloodGroupAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloodGroupAdd_buttonActionPerformed
      if(BloodGroup_Text.getText()!=null && BloodGroup_Text.getText().trim().length()>0 ){
            
            String Name = BloodGroup_Text.getText().trim();
            int flag=0;
            for(int i=0;i<BloodGroupList.size();i++){
                String x = BloodGroupList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               BloodGroupList.add(Name);
               String Str="";
               for(int i=0;i<BloodGroupList.size();i++){
                   String x = BloodGroupList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="Blood Group";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    BloodGroup_Text.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BloodGroupAdd_buttonActionPerformed

    private void OtherClubName_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherClubName_buttonActionPerformed
        if(OtherClubName_Text.getText()!=null && OtherClubName_Text.getText().trim().length()>0 ){
            
            String Name = OtherClubName_Text.getText().trim();
            int flag=0;
            for(int i=0;i<OtherClubList.size();i++){
                String x = OtherClubList.get(i).toString().toLowerCase();
                if(x.equals(Name.toLowerCase())){
                    flag=1;
                    break;
                }
            }
            
            if(flag==0){
               OtherClubList.add(Name);
               String Str="";
               for(int i=0;i<OtherClubList.size();i++){
                   String x = OtherClubList.get(i).toString();
                   Str=Str+x+"#";
               }
                
               
               try{ 
                    String TableName="Other Club";
                    if( new PreparedSentence(app.getSession()
                                 , "UPDATE kymmaster SET VALUE=? WHERE NAME=?"
                                 , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Str,TableName})<=0){

                                  new PreparedSentence(app.getSession()
                                   , "INSERT INTO kymmaster(ID,NAME,VALUE) VALUES(?,?,?)"
                                   , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),TableName, Str});


                    }

                    JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE); 
                    OtherClubName_Text.setText(null);
                    Loaddata();
              }
              catch(BasicException e){
                   JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE); 
                   e.printStackTrace();
              }
               
            }
            else{
                 JOptionPane.showMessageDialog(this, "Name Already exsist. Please enter another name. ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
           
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter Name  ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_OtherClubName_buttonActionPerformed

    public static KymMasterDialog getDialog(Component parent,  AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);
        
        KymMasterDialog bill;
        
        if (window instanceof Frame) {
            bill = new KymMasterDialog((Frame) window , app, flag);
        } else {
            bill = new KymMasterDialog((Dialog) window, app, flag);
        }
        return bill;
     }
    
     public boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }
   
     
     
     
       
   protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    } 
    
   
   public void init() throws BasicException {
        initComponents();
        Loaddata();
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox BloodGroupAdd_Combo;
    private javax.swing.JButton BloodGroupAdd_button;
    private javax.swing.JTextField BloodGroup_Text;
    private javax.swing.JButton CityAdd_button;
    private javax.swing.JButton ClubActivityAdd_button;
    private javax.swing.JComboBox ClubActivity_Combo;
    private javax.swing.JTextField ClubActivity_Text1;
    private javax.swing.JButton CountryAdd_button;
    private javax.swing.JComboBox Country_Combo;
    private javax.swing.JTextField Country_Text;
    private javax.swing.JButton FacilityAdd_button;
    private javax.swing.JTextField Facility_Text2;
    private javax.swing.JComboBox Facility_combo;
    private javax.swing.JButton IDProof_Add_Button;
    private javax.swing.JTextField IDProof_Text;
    private javax.swing.JComboBox IDProof_combo;
    private javax.swing.JButton MobileOSAdd_button;
    private javax.swing.JComboBox MobileOS_Combo;
    private javax.swing.JTextField MobileOS_Text;
    private javax.swing.JComboBox OtherClubName_Combo;
    private javax.swing.JTextField OtherClubName_Text;
    private javax.swing.JButton OtherClubName_button;
    private javax.swing.JButton ProfessionAdd_button;
    private javax.swing.JTextField Profession_Text2;
    private javax.swing.JComboBox Profession_combo;
    private javax.swing.JButton StateAdd_button;
    private javax.swing.JComboBox State_Combo;
    private javax.swing.JTextField State_text;
    private javax.swing.JComboBox city_combo;
    private javax.swing.JTextField city_text1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    public void Loaddata() throws BasicException{

        CityList = new ArrayList<String>();
        CityList=GetCityList(app);
        CityComboBoxValModel = new ComboBoxValModel(CityList);
        city_combo.setModel(CityComboBoxValModel);
        city_combo.setSelectedIndex(-1);
        
        CountryList = new ArrayList<String>();
        CountryList=GetCountryList(app);
        CountryComboBoxValModel = new ComboBoxValModel(CountryList);
        Country_Combo.setModel(CountryComboBoxValModel);
        Country_Combo.setSelectedIndex(-1);
        
        StateList = new ArrayList<String>();
        StateList=GetStateList(app);
        StateComboBoxValModel = new ComboBoxValModel(StateList);
        State_Combo.setModel(StateComboBoxValModel);
        State_Combo.setSelectedIndex(-1);
        
        MobileOsList = new ArrayList<String>();
        MobileOsList=GetMobileOSList(app);
        MobileOSComboBoxValModel = new ComboBoxValModel(MobileOsList);
        MobileOS_Combo.setModel(MobileOSComboBoxValModel);
        MobileOS_Combo.setSelectedIndex(-1);
        
        ProfessionList = new ArrayList<String>();
        ProfessionList=GetProfessionList(app);
        professionComboBoxValModel = new ComboBoxValModel(ProfessionList);
        Profession_combo.setModel(professionComboBoxValModel);
        Profession_combo.setSelectedIndex(-1);
        
        ClubActivityList = new ArrayList<String>();
        ClubActivityList=GetClubActivityList(app);
        ClubActivityComboBoxValModel = new ComboBoxValModel(ClubActivityList);
        ClubActivity_Combo.setModel(ClubActivityComboBoxValModel);
        ClubActivity_Combo.setSelectedIndex(-1);
        
        FacilityList = new ArrayList<String>();
        FacilityList=GetFacilityList(app);
        FacilityComboBoxValModel = new ComboBoxValModel(FacilityList);
        Facility_combo.setModel(FacilityComboBoxValModel);
        Facility_combo.setSelectedIndex(-1);
        
        IDProofTypeList = new ArrayList<String>();
        IDProofTypeList=GetIDProofList(app);
        IdProofComboBoxValModel = new ComboBoxValModel(IDProofTypeList);
        IDProof_combo.setModel(IdProofComboBoxValModel);
        IDProof_combo.setSelectedIndex(-1);
        
        
        BloodGroupList=new ArrayList<String>();
        BloodGroupList=GetBloodGroupList(app);
        BloodGroupComboBoxValModel=new ComboBoxValModel(BloodGroupList);
        BloodGroupAdd_Combo.setModel(BloodGroupComboBoxValModel);
        BloodGroupAdd_Combo.setSelectedIndex(-1);
        
        
        OtherClubList=new ArrayList<String>();
        OtherClubList=GetOtherClubNameList(app);
        OtherClubComboBoxValModel=new ComboBoxValModel(OtherClubList);
        OtherClubName_Combo.setModel(OtherClubComboBoxValModel);
        OtherClubName_Combo.setSelectedIndex(-1);
        
        
    }
    public void Reset(){

    }

  // ***************************************************************** GET LIST DETAILS FOR ALL ************************************************  
    public List<String> GetCityList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="CITY";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      CityList.add(Xarr[i].toString());
               }
              return CityList;
          }
          else{
              return CityList;
          }
      }
    public List<String> GetCountryList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="COUNTRY";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      CountryList.add(Xarr[i].toString());
               }
              return CountryList;
          }
          else{
              return CountryList;
          }
      }
    public List<String> GetStateList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="STATE";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      StateList.add(Xarr[i].toString());
               }
              return StateList;
          }
          else{
              return StateList;
          }
      }
     public List<String> GetMobileOSList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="MOBILE OS";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      MobileOsList.add(Xarr[i].toString());
               }
              return MobileOsList;
          }
          else{
              return MobileOsList;
          }
      }
     public List<String> GetProfessionList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="Profession";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      ProfessionList.add(Xarr[i].toString());
               }
              return ProfessionList;
          }
          else{
              return ProfessionList;
          }
      }
     
     public List<String> GetClubActivityList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="Club Activity";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      ClubActivityList.add(Xarr[i].toString());
               }
              return ClubActivityList;
          }
          else{
              return ClubActivityList;
          }
      }
    public List<String> GetFacilityList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="Facility";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      FacilityList.add(Xarr[i].toString());
               }
              return FacilityList;
          }
          else{
              return FacilityList;
          }
      }
    public List<String> GetIDProofList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="ID Proof";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
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
     public List<String> GetBloodGroupList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="Blood Group";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      BloodGroupList.add(Xarr[i].toString());
               }
              return BloodGroupList;
          }
          else{
              return BloodGroupList;
          }
      }
     public List<String> GetOtherClubNameList(AppView app ) throws BasicException{
          Object  o = null;
          String Name="Other Club";
          o  =  new StaticSentence(app.getSession(), "SELECT VALUE FROM KYMMASTER WHERE NAME=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(o!=null){
              String x = o.toString();  
              String Xarr[] = x.split("#");
              for(int i=0;i<Xarr.length;i++){
                      OtherClubList.add(Xarr[i].toString());
               }
              return OtherClubList;
          }
          else{
              return OtherClubList;
          }
      }
     
}



