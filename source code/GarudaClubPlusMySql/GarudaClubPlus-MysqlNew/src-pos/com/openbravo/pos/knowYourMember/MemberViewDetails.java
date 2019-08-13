
package com.openbravo.pos.knowYourMember;
import com.openbravo.pos.knowYourMember.MemberViewDetailsTableModel;
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
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;
//import com.openbravo.pos.sms.EmailMasterTableForCreateGroup;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.UUID;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.table.TableCellRenderer;
import java.util.Properties;
import java.io.ObjectOutputStream;
import java.text.DateFormat;


public class MemberViewDetails extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    
    
    private AppView m_App;
    public List<RecreationalActivitiesListClass> RecreationalActivityList = new ArrayList<RecreationalActivitiesListClass>();
    public List<ChildrenClass> ChildrenClassList = new ArrayList<ChildrenClass>();
    public List<OtherClubClass> OtherClubClassListAll_Class = new ArrayList<OtherClubClass>();
    public List<SD_Sports_Class> SD_Sports_ClassListAll = new ArrayList<SD_Sports_Class>();
    public List<SD_RActivity_Class> SD_RActivity_ClassListAll = new ArrayList<SD_RActivity_Class>();
    public List<SD_Facility_Class> SD_Facility_ClassListAll = new ArrayList<SD_Facility_Class>();
    public List<SD_Talent_Class> SD_Talent_ClassListAll = new ArrayList<SD_Talent_Class>();
    public List<SD_ClubActivity_Class> SD_ClubActivity_ClassListAll = new ArrayList<SD_ClubActivity_Class>();
    
    //public List<String> RecreationalActivityList1 = new ArrayList<String>();
    
    
    private List<String> CityList = new ArrayList<String>();
    private List<String> CountryList = new ArrayList<String>();
    private List<String> StateList = new ArrayList<String>();
    private List<String> MobileOsList = new ArrayList<String>();
    private List<String> ProfessionList = new ArrayList<String>();
    private List<String> ClubActivityList = new ArrayList<String>();
    private List<String> FacilityList = new ArrayList<String>();
    private List<String> IDProofTypeList = new ArrayList<String>();
    private List<String> MemberShipTypeList = new ArrayList<String>();
    private List<String> SelectedFacilityList = new ArrayList<String>();
    private List<String> SelectedActivityList = new ArrayList<String>();
    private List<String> ChildrenIdProofListAll = new ArrayList<String>();
    private List<String> ChildrenBloodGrpListAll = new ArrayList<String>();
    private List<String> OtherClubNameListAll = new ArrayList<String>(); 
    private List<String> SD_listAll = new ArrayList<String>();
    
    
    private ComboBoxValModel CityComboBoxValModel;
    private ComboBoxValModel OffCityComboBoxValModel;
    private ComboBoxValModel StateComboBoxValModel;
    private ComboBoxValModel OffStateComboBoxValModel;
    private ComboBoxValModel CountryComboBoxValModel;
    private ComboBoxValModel OffCountryComboBoxValModel;
    private ComboBoxValModel MobileOSComboBoxValModel;
    private ComboBoxValModel professionComboBoxValModel;
    private ComboBoxValModel ClubActivityComboBoxValModel;
    private ComboBoxValModel FacilityComboBoxValModel;
    private ComboBoxValModel IdProofComboBoxValModel;
    private ComboBoxValModel MemTypeComboBoxValModel;
    private ComboBoxValModel ChildrenIdProodListBoxValModel;
    private ComboBoxValModel ChildrenBloodGrpListBoxValModel;
    private ComboBoxValModel OtherClubCityListBoxValModel;
    private ComboBoxValModel OtherClubNameListBoxValModel;
    private ComboBoxValModel FatherBloodGroupBoxValModel;
    private ComboBoxValModel MotherBloodGroupBoxValModel;
    private ComboBoxValModel FatherIdProofBoxValModel;
    private ComboBoxValModel MotherIdProofBoxValModel;
    private ComboBoxValModel SD_List_ComboBoxValModel;
    private ComboBoxValModel SD_Ractivity_ComboBoxValModel;
    private ComboBoxValModel SD_FacilitySpouse_ComboBoxValModel;
    private ComboBoxValModel SD_FacilityName_ComboBoxValModel;
    private ComboBoxValModel SD_Talent_ComboBoxValModel;
    
    
    
    private MemberViewDetailsTableModel MemberFormTable_Model;
    private MemberViewDetailsTableModel MemberFormTable_Model2;
    private MemberViewDetailsTableModel MemberFormTable_Model3;
    
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    
    
    public MemberViewDetails() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        male_radio = new javax.swing.JRadioButton();
        female_radio = new javax.swing.JRadioButton();
        mno_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        MemberName_text = new javax.swing.JLabel();
        mname_text = new javax.swing.JLabel();
        FatherName_text = new javax.swing.JLabel();
        DOB_text = new javax.swing.JLabel();
        idDocUniqueNo_text = new javax.swing.JLabel();
        memberSince_text = new javax.swing.JLabel();
        SpouseName_text = new javax.swing.JLabel();
        DateOfMarriage_text = new javax.swing.JLabel();
        IDproofDoc_combo = new javax.swing.JLabel();
        TypeofMember_combo = new javax.swing.JLabel();
        as_Per_Customer_Table = new javax.swing.JLabel();
        memberSince_text1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        FatherPhoneNo_Text = new javax.swing.JLabel();
        FatherEmailId_Text = new javax.swing.JLabel();
        FatherUniqueNo_text = new javax.swing.JLabel();
        fatherDob_text = new javax.swing.JLabel();
        FatherBloodGroup_combo = new javax.swing.JLabel();
        FatherIdProof_combo = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        MotherName_text = new javax.swing.JLabel();
        MotherPhoneNo_text = new javax.swing.JLabel();
        MotherDOB_text = new javax.swing.JLabel();
        MotherEmailid_text = new javax.swing.JLabel();
        MotherUniqueNo_text = new javax.swing.JLabel();
        MotherBloodGroup_combo = new javax.swing.JLabel();
        MotherIdProof_combo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        ChildrenAdd_button = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        children_Jtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel32 = new javax.swing.JLabel();
        ChildRemove_Button = new javax.swing.JButton();
        ChildrenName_text = new javax.swing.JLabel();
        ChildrenPhone_text = new javax.swing.JLabel();
        ChildrenEmailId_text = new javax.swing.JLabel();
        ChildrenDOB_text = new javax.swing.JLabel();
        ChildrenUniqueNo_text = new javax.swing.JLabel();
        children_info_hidden_panel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        ChildrenMale_radio = new javax.swing.JRadioButton();
        ChildrenFemale_radio = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ChildrenBlood_Combo = new javax.swing.JLabel();
        ChildrenIDProof_combo = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        Rline1Res_text = new javax.swing.JLabel();
        RLine2Res_text = new javax.swing.JLabel();
        ResPost_text = new javax.swing.JLabel();
        ResPinNo_text = new javax.swing.JLabel();
        ResCity_combo = new javax.swing.JLabel();
        ResCountry_combo = new javax.swing.JLabel();
        ResState_Combo = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        offLine1_text = new javax.swing.JLabel();
        Off_line2_text = new javax.swing.JLabel();
        OffPost_Text = new javax.swing.JLabel();
        OffPinNo_text = new javax.swing.JLabel();
        offCity_combo = new javax.swing.JLabel();
        OffState_combo = new javax.swing.JLabel();
        Off_Country_combo = new javax.swing.JLabel();
        OffPost_Text1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        residence_Radio = new javax.swing.JRadioButton();
        Office_Radio = new javax.swing.JRadioButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        MobileNo_text = new javax.swing.JLabel();
        Residence_no_text = new javax.swing.JLabel();
        OfficeNo_text = new javax.swing.JLabel();
        Email_Text = new javax.swing.JLabel();
        Twitter_text = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        activities_combo = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Activities_jList = new javax.swing.JList();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        PlaySport_Text = new javax.swing.JLabel();
        SpecialTalent_text = new javax.swing.JLabel();
        MobilePhoneUse_Combo = new javax.swing.JLabel();
        Proffesion_Combo = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        OtherClubMember_JTable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jButton4 = new javax.swing.JButton();
        OtherClubMemno_text = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JLabel();
        other_clubs_hidden_panel = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        OtherClubName_Combo = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        OtherClubCity_combo = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        OtherClubMemberSince_text = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        FacilityOption_Combo = new javax.swing.JComboBox();
        facilityAdd_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Facility_Jlist = new javax.swing.JList();
        facilityRemove_button = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        RecreationalActivity_JTable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        Recractivity_remove_btn = new javax.swing.JButton();
        recreational_Sport_Text = new javax.swing.JLabel();
        recreational_level_text = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sports_SD_Combo = new javax.swing.JComboBox();
        jLabel75 = new javax.swing.JLabel();
        SD_Sports_AddButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        SD_Sports_Jtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        SD_Sports_Textfeild = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        SD_RActivity_Combo = new javax.swing.JComboBox();
        jLabel77 = new javax.swing.JLabel();
        SD_Ractivity_AddButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        SD_RActivity_Jtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel78 = new javax.swing.JLabel();
        SD_RActivity_Textfeild = new javax.swing.JLabel();
        SD_RActivityLevel_Textfeild = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        SD_FacilitySpouce_Combo = new javax.swing.JComboBox();
        SD_Facility_FullAddButton = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        SD_Facility_Jtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel81 = new javax.swing.JLabel();
        SD_facname_combo = new javax.swing.JComboBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        SD_facility_Jlist = new javax.swing.JList();
        SD_facilityAdd_button = new javax.swing.JButton();
        SD_facility_remove_button = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        SD_FacilityTable_Remove_button = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        SD_talent_Combo = new javax.swing.JComboBox();
        jLabel82 = new javax.swing.JLabel();
        SD_talent_Textfeild = new javax.swing.JTextField();
        SD_talent_AddButton = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        SD_talent_Jtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jPanel19 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        SD_ClubActSDName_Combo = new javax.swing.JComboBox();
        SD_Facility_FullAddButton1 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        SD_Facility_Jtable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel84 = new javax.swing.JLabel();
        SD_ClubAct_combo = new javax.swing.JComboBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        SD_ClubAct_Jlist = new javax.swing.JList();
        SD_facilityAdd_button1 = new javax.swing.JButton();
        SD_facility_remove_button1 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        SD_FacilityTable_Remove_button1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        prev_Data = new javax.swing.JButton();
        next_data = new javax.swing.JButton();

        jLabel1.setText("Name of the Member : ");

        jLabel2.setText("Father's Name : ");

        jLabel3.setText("Identity Proof Document Submitted : ");

        jLabel5.setText("Member No : ");

        jLabel6.setText("Id Document Unique No. : ");

        jLabel7.setText("Type Of Member : ");

        jLabel8.setText("Member Since :  ");

        jLabel9.setText("Name of Spouse : ");

        jLabel10.setText("Date of Marriage : ");

        jLabel11.setForeground(new java.awt.Color(204, 0, 51));
        jLabel11.setText("Eg. (dd/mm/yyyy)");

        male_radio.setText("Male");

        female_radio.setText("Female");

        mno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mno_textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mno_textKeyReleased(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(204, 0, 51));
        jLabel12.setText("Eg. (dd/mm/yyyy)");

        jLabel13.setText("Date Of Birth :  ");

        jLabel14.setForeground(new java.awt.Color(204, 0, 51));
        jLabel14.setText("Eg. (dd/mm/yyyy)");

        MemberName_text.setForeground(new java.awt.Color(214, 16, 16));
        MemberName_text.setText("MemberName");

        IDproofDoc_combo.setText("identityProofDocumentSubmitted");

        as_Per_Customer_Table.setText("(As Per Customer Table)");

        memberSince_text1.setText("jLabel86");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idDocUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TypeofMember_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(DateOfMarriage_text, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FatherName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(mno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(MemberName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(DOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addComponent(IDproofDoc_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(56, 56, 56)
                                .addComponent(memberSince_text1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel12)
                                        .addGap(119, 119, 119)
                                        .addComponent(male_radio))
                                    .addComponent(memberSince_text, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SpouseName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(female_radio)
                            .addComponent(as_Per_Customer_Table, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(mno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MemberName_text)
                    .addComponent(as_Per_Customer_Table))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mname_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(FatherName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(DOB_text, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(IDproofDoc_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(idDocUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(male_radio)
                        .addComponent(female_radio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TypeofMember_combo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12)
                            .addComponent(memberSince_text)
                            .addComponent(memberSince_text1))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(SpouseName_text))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(DateOfMarriage_text))
                .addGap(58, 58, 58))
        );

        jLabel11.setVisible(false);
        male_radio.setSelected(false);
        female_radio.setSelected(false);
        jLabel12.setVisible(false);
        jLabel14.setVisible(false);
        IDproofDoc_combo.setText(null);
        as_Per_Customer_Table.setText("");
        memberSince_text1.setText("");

        jTabbedPane1.addTab("Main Form", jPanel1);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Father Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel15.setText("Father's Name :  ");

        jLabel16.setText("Date of Birth : ");

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("Eg. (dd/mm/yyyy)");

        jLabel28.setText("Blood Group :-");

        jLabel29.setText("Phone No :- ");

        jLabel30.setText("Email Id :-");

        jLabel33.setText("Id Proof :- ");

        jLabel34.setText("Unique No :-");

        fatherDob_text.setText("jLabel85");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addComponent(FatherPhoneNo_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(FatherIdProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel30)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(fatherDob_text, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17))
                            .addComponent(FatherEmailId_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FatherUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(FatherBloodGroup_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jTextField1)
                    .addComponent(fatherDob_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(FatherBloodGroup_combo))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(FatherEmailId_Text))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FatherPhoneNo_Text, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel29)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(FatherUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FatherIdProof_combo))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel17.setVisible(false);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Mother's Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(157, 4, 4))); // NOI18N

        jLabel18.setText("Mother's Name : ");

        jLabel19.setText("Date of Birth : ");

        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("Eg. (dd/mm/yyyy)");

        jLabel35.setText("Blood Group :-");

        jLabel36.setText("Phone No :- ");

        jLabel61.setText("Id Proof :- ");

        jLabel70.setText("Email Id :-");

        jLabel71.setText("Unique No :-");

        MotherIdProof_combo.setText("jLabel85");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel35)
                    .addComponent(jLabel61)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MotherName_text, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(MotherPhoneNo_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MotherIdProof_combo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel70))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(MotherDOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20))
                                    .addComponent(MotherEmailid_text, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MotherUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(MotherBloodGroup_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(MotherName_text)
                    .addComponent(MotherDOB_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(MotherBloodGroup_combo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jLabel70)
                        .addComponent(MotherEmailid_text))
                    .addComponent(MotherPhoneNo_text, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel71)
                    .addComponent(MotherUniqueNo_text)
                    .addComponent(MotherIdProof_combo))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel20.setVisible(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Parents Info", jPanel2);

        jSeparator1.setForeground(new java.awt.Color(137, 24, 24));

        ChildrenAdd_button.setForeground(new java.awt.Color(198, 16, 16));
        ChildrenAdd_button.setText("Add");
        ChildrenAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChildrenAdd_buttonActionPerformed(evt);
            }
        });

        children_Jtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(children_Jtable);

        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("Children details ");

        ChildRemove_Button.setText("Remove");
        ChildRemove_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChildRemove_ButtonActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("Name : ");

        ChildrenMale_radio.setText("Male");

        ChildrenFemale_radio.setText("Female");

        jLabel22.setText("Date of Birth");

        jLabel24.setText("Phone No: ");

        jLabel25.setText("Email Id: ");

        jLabel23.setText("Blood Group : ");

        jLabel26.setText("ID Proof :");

        jLabel27.setText("Unique No : ");

        ChildrenBlood_Combo.setText("jLabel85");

        ChildrenIDProof_combo.setText("jLabel85");

        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("Eg. (dd/mm/yyyy)");

        javax.swing.GroupLayout children_info_hidden_panelLayout = new javax.swing.GroupLayout(children_info_hidden_panel);
        children_info_hidden_panel.setLayout(children_info_hidden_panelLayout);
        children_info_hidden_panelLayout.setHorizontalGroup(
            children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(children_info_hidden_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChildrenMale_radio)
                    .addComponent(ChildrenFemale_radio))
                .addGap(27, 27, 27)
                .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(children_info_hidden_panelLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ChildrenIDProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(children_info_hidden_panelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel26)
                        .addGap(50, 50, 50)
                        .addComponent(ChildrenBlood_Combo, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addGap(48, 48, 48))
        );
        children_info_hidden_panelLayout.setVerticalGroup(
            children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(children_info_hidden_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(children_info_hidden_panelLayout.createSequentialGroup()
                        .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChildrenMale_radio)
                                .addComponent(jLabel22)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26)
                                .addComponent(ChildrenBlood_Combo))
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChildrenFemale_radio)
                                .addComponent(jLabel24)
                                .addComponent(jLabel23))
                            .addGroup(children_info_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(ChildrenIDProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(ChildrenName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(ChildrenPhone_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(95, 95, 95))
                                    .addComponent(ChildrenEmailId_text, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))))
                        .addGap(212, 212, 212)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ChildrenUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ChildrenAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ChildrenDOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(354, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ChildRemove_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(children_info_hidden_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 105, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(children_info_hidden_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChildrenName_text)
                    .addComponent(ChildrenDOB_text))
                .addGap(53, 53, 53)
                .addComponent(ChildrenPhone_text)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChildrenAdd_button)
                    .addComponent(ChildrenEmailId_text)
                    .addComponent(ChildrenUniqueNo_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ChildRemove_Button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ChildrenAdd_button.setEnabled(false);
        ChildRemove_Button.setEnabled(false);
        children_info_hidden_panel.setVisible(false);

        jTabbedPane1.addTab("Children Info", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Residential Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel37.setText("Line 1 : ");

        jLabel38.setText("Line 2 : ");

        jLabel41.setText("Post : ");

        jLabel42.setText("City : ");

        jLabel43.setText("Country : ");

        jLabel44.setText("State : ");

        jLabel45.setText("Pin : ");

        jLabel85.setText("jLabel85");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ResCity_combo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ResCountry_combo, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                        .addGap(131, 131, 131)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel41))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ResPinNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResState_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel85))
                        .addGap(88, 88, 88))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(RLine2Res_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(Rline1Res_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(94, 94, 94)))
                        .addGap(57, 57, 57)
                        .addComponent(ResPost_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rline1Res_text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(ResPost_text)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(RLine2Res_text)
                    .addComponent(jLabel41)
                    .addComponent(jLabel85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel44)
                    .addComponent(ResCity_combo)
                    .addComponent(ResState_Combo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel45)
                    .addComponent(ResPinNo_text)
                    .addComponent(ResCountry_combo)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Office Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel39.setText("Line 1 : ");

        jLabel40.setText("Line 2 : ");

        jLabel46.setText("City : ");

        jLabel47.setText("Country : ");

        jLabel48.setText("State : ");

        jLabel49.setText("Pin : ");

        jLabel50.setText("Post : ");

        offLine1_text.setText("o_line1");

        OffPost_Text1.setText("jLabel86");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(offLine1_text, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(379, 379, 379)
                                .addComponent(jLabel50)
                                .addGap(18, 18, 18)
                                .addComponent(OffPost_Text1)
                                .addGap(30, 30, 30)))
                        .addComponent(OffPost_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(offCity_combo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Off_Country_combo, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                            .addComponent(Off_line2_text, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(128, 128, 128)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OffPinNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OffState_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(OffPost_Text)
                    .addComponent(offLine1_text, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(Off_line2_text)
                    .addComponent(jLabel50)
                    .addComponent(OffPost_Text1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel48)
                    .addComponent(offCity_combo, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(OffState_combo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel49)
                    .addComponent(OffPinNo_text)
                    .addComponent(Off_Country_combo)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)), "Contact Numbers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 12), new java.awt.Color(102, 0, 102))); // NOI18N

        jLabel51.setText("Mobile No : ");

        jLabel52.setText("Residence : ");

        jLabel53.setText("Office : ");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel54.setText("Prefered Address for communication :  ");

        residence_Radio.setText("Residential");

        Office_Radio.setText("Office");

        jLabel55.setText("Email Id : ");

        jLabel56.setText("Twitter handle (If any) :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MobileNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Residence_no_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel53))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Email_Text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel54))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(residence_Radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Office_Radio)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Twitter_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OfficeNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51)
                        .addComponent(jLabel52)
                        .addComponent(jLabel53)
                        .addComponent(Residence_no_text)
                        .addComponent(OfficeNo_text))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(MobileNo_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(residence_Radio)
                    .addComponent(Office_Radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(Email_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Twitter_text))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        residence_Radio.setSelected(false);
        Office_Radio.setSelected(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Address Detial", jPanel4);

        jLabel57.setText("Which Mobile Phone do you use ?  ");

        jLabel58.setText("What is your profession ? ");

        jLabel59.setText("Which area do you think you can contribute in activities of club ?  ");

        activities_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel60.setText("Do you play any Sport ? If so, Pls specify");

        jLabel63.setText("Any special talent that you have which you would like to know us ? ");

        Activities_jList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(Activities_jList);

        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Remove");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        SpecialTalent_text.setText("jLabel85");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(activities_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MobilePhoneUse_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(Proffesion_Combo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(16, 16, 16))))
                            .addComponent(jLabel59)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SpecialTalent_text, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(PlaySport_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(MobilePhoneUse_Combo))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(Proffesion_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel59)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton7))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(activities_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61)
                        .addComponent(jButton8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(PlaySport_Text))
                .addGap(50, 50, 50)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(SpecialTalent_text))
                .addGap(40, 40, 40))
        );

        jButton7.setEnabled(false);
        jButton8.setEnabled(false);

        jTabbedPane1.addTab("Other Info", jPanel8);

        jButton6.setText("Add Club Details");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(113, 19, 19));

        OtherClubMember_JTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(OtherClubMember_JTable);

        jButton4.setText("Remove Details");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel64.setText("Membership of other clubs : ");

        OtherClubName_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        OtherClubName_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtherClubName_ComboActionPerformed(evt);
            }
        });

        jLabel65.setText("Name of club : ");

        jLabel67.setText("Member No: ");

        jLabel68.setText("Type of member : ");

        jLabel66.setText("City : ");

        OtherClubCity_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel69.setText("Member since : ");

        OtherClubMemberSince_text.setText("member since");

        javax.swing.GroupLayout other_clubs_hidden_panelLayout = new javax.swing.GroupLayout(other_clubs_hidden_panel);
        other_clubs_hidden_panel.setLayout(other_clubs_hidden_panelLayout);
        other_clubs_hidden_panelLayout.setHorizontalGroup(
            other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, other_clubs_hidden_panelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel65)
                .addGap(167, 167, 167)
                .addComponent(OtherClubName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(other_clubs_hidden_panelLayout.createSequentialGroup()
                .addGroup(other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(other_clubs_hidden_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel67))
                    .addGroup(other_clubs_hidden_panelLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel66)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel69)
                        .addGap(72, 72, 72)
                        .addComponent(OtherClubCity_combo, 0, 253, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(OtherClubMemberSince_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(293, 293, 293))
        );
        other_clubs_hidden_panelLayout.setVerticalGroup(
            other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(other_clubs_hidden_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OtherClubName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel68))
                .addGap(18, 18, 18)
                .addGroup(other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(other_clubs_hidden_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(OtherClubCity_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(OtherClubMemberSince_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel69))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(OtherClubMemno_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(other_clubs_hidden_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(other_clubs_hidden_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OtherClubMemno_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(76, 76, 76))
        );

        jButton6.setEnabled(false);
        jButton4.setEnabled(false);

        jTabbedPane1.addTab("other Clubs", jPanel9);

        jLabel72.setText("Which of the optional facilities that you have opted for ?  ");

        FacilityOption_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        facilityAdd_button.setText("Add");
        facilityAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilityAdd_buttonActionPerformed(evt);
            }
        });

        Facility_Jlist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Facility_Jlist);

        facilityRemove_button.setText("Remove");
        facilityRemove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilityRemove_buttonActionPerformed(evt);
            }
        });

        jLabel62.setText("Have you represented your school/college/state/country/institution in any sports or recreational activities ? ");

        jLabel73.setText("Sports/Activities");

        jLabel74.setText("Level");

        jSeparator5.setBackground(new java.awt.Color(114, 18, 8));

        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        RecreationalActivity_JTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(RecreationalActivity_JTable);

        Recractivity_remove_btn.setText("Remove");
        Recractivity_remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Recractivity_remove_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel72))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel73)
                                .addGap(255, 255, 255)
                                .addComponent(jLabel74))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(recreational_Sport_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)
                                        .addComponent(recreational_level_text, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9)
                            .addComponent(Recractivity_remove_btn))
                        .addGap(0, 163, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(FacilityOption_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(facilityRemove_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(facilityAdd_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel72)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(FacilityOption_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(facilityAdd_button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(facilityRemove_button))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(recreational_Sport_Text)
                            .addComponent(recreational_level_text))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Recractivity_remove_btn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        FacilityOption_Combo.setVisible(false);
        facilityAdd_button.setEnabled(false);
        facilityRemove_button.setEnabled(false);
        jLabel73.setVisible(false);
        jLabel74.setVisible(false);
        jButton9.setEnabled(false);
        Recractivity_remove_btn.setEnabled(false);

        jTabbedPane1.addTab("Club Facilities", jPanel10);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sports  (Spouse / Dependants)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(150, 15, 15))); // NOI18N

        jLabel4.setText("Select Spouse / Dependant");

        sports_SD_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel75.setText("Sports Intreasted in : ");

        SD_Sports_AddButton.setText("Add");
        SD_Sports_AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Sports_AddButtonActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(204, 22, 22));

        SD_Sports_Jtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(SD_Sports_Jtable);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel75))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sports_SD_Combo, 0, 250, Short.MAX_VALUE)
                            .addComponent(SD_Sports_Textfeild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(140, 140, 140)
                        .addComponent(SD_Sports_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sports_SD_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel75)
                        .addComponent(SD_Sports_Textfeild))
                    .addComponent(SD_Sports_AddButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setVisible(false);
        sports_SD_Combo.setVisible(false);
        jLabel75.setVisible(false);
        SD_Sports_AddButton.setVisible(false);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Recreational Activities (Spouse / Dependants)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(150, 15, 15))); // NOI18N

        jLabel76.setText("Select Spouse / Dependant");

        SD_RActivity_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel77.setText("Sports/Activities Intreasted");

        SD_Ractivity_AddButton.setText("Add");
        SD_Ractivity_AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Ractivity_AddButtonActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(204, 22, 22));

        SD_RActivity_Jtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(SD_RActivity_Jtable);

        jLabel78.setText("Level");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SD_RActivity_Textfeild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SD_RActivity_Combo, 0, 250, Short.MAX_VALUE)
                            .addComponent(SD_RActivityLevel_Textfeild, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                        .addGap(141, 141, 141)
                        .addComponent(SD_Ractivity_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(SD_RActivity_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel77)
                    .addComponent(SD_RActivity_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(SD_Ractivity_AddButton)
                    .addComponent(SD_RActivityLevel_Textfeild))
                .addGap(3, 3, 3)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel76.setVisible(false);
        SD_RActivity_Combo.setVisible(false);
        jLabel77.setVisible(false);
        SD_Ractivity_AddButton.setVisible(false);
        jLabel78.setVisible(false);
        SD_RActivity_Textfeild.setVisible(false);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Optional Facilities  (Spouse / Dependants)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(150, 15, 15))); // NOI18N

        jLabel79.setText("Select Spouse / Dependant");

        SD_FacilitySpouce_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        SD_Facility_FullAddButton.setText("Add");
        SD_Facility_FullAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Facility_FullAddButtonActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(204, 22, 22));

        SD_Facility_Jtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(SD_Facility_Jtable);

        jLabel81.setText("Select Facility :  ");

        SD_facname_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        SD_facility_Jlist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(SD_facility_Jlist);
        SD_facility_Jlist.setVisible(false);

        SD_facilityAdd_button.setText("Add");
        SD_facilityAdd_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_facilityAdd_buttonActionPerformed(evt);
            }
        });

        SD_facility_remove_button.setText("Remove");
        SD_facility_remove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_facility_remove_buttonActionPerformed(evt);
            }
        });

        jSeparator7.setForeground(new java.awt.Color(204, 22, 22));

        SD_FacilityTable_Remove_button.setText("Remove");
        SD_FacilityTable_Remove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_FacilityTable_Remove_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel81))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SD_FacilitySpouce_Combo, 0, 250, Short.MAX_VALUE)
                                    .addComponent(SD_facname_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(SD_facilityAdd_button))
                            .addComponent(SD_facility_remove_button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SD_Facility_FullAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jSeparator6)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(SD_FacilityTable_Remove_button)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(SD_FacilitySpouce_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel81)
                                    .addComponent(SD_facname_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SD_facilityAdd_button))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SD_facility_remove_button))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(SD_Facility_FullAddButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(SD_FacilityTable_Remove_button))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel79.setVisible(false);
        SD_FacilitySpouce_Combo.setVisible(false);
        SD_Facility_FullAddButton.setVisible(false);
        jLabel81.setVisible(false);
        SD_facname_combo.setVisible(false);
        SD_facilityAdd_button.setVisible(false);
        SD_facility_remove_button.setVisible(false);
        SD_FacilityTable_Remove_button.setVisible(false);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Special Talents (Spouse / Dependants)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(150, 15, 15))); // NOI18N

        jLabel80.setText("Select Spouse / Dependant");

        SD_talent_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel82.setText("Sports/Activities Intreasted");

        SD_talent_AddButton.setText("Add");
        SD_talent_AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_talent_AddButtonActionPerformed(evt);
            }
        });
        SD_talent_AddButton.setVisible(false);

        jSeparator8.setForeground(new java.awt.Color(204, 22, 22));

        SD_talent_Jtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(SD_talent_Jtable);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator8)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(jLabel82))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SD_talent_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(SD_talent_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(SD_talent_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(SD_talent_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel82)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SD_talent_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SD_talent_AddButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel80.setVisible(false);
        SD_talent_Combo.setVisible(false);
        jLabel82.setVisible(false);
        SD_talent_Textfeild.setVisible(false);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Club Activities  (Spouse / Dependants)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(150, 15, 15))); // NOI18N

        jLabel83.setText("Select Spouse / Dependant");

        SD_ClubActSDName_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        SD_Facility_FullAddButton1.setText("Add");
        SD_Facility_FullAddButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Facility_FullAddButton1ActionPerformed(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(204, 22, 22));

        SD_Facility_Jtable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(SD_Facility_Jtable1);

        jLabel84.setText("Select Facility :  ");

        SD_ClubAct_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        SD_ClubAct_Jlist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane13.setViewportView(SD_ClubAct_Jlist);
        SD_ClubAct_Jlist.setVisible(false);

        SD_facilityAdd_button1.setText("Add");
        SD_facilityAdd_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_facilityAdd_button1ActionPerformed(evt);
            }
        });

        SD_facility_remove_button1.setText("Remove");
        SD_facility_remove_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_facility_remove_button1ActionPerformed(evt);
            }
        });

        jSeparator10.setForeground(new java.awt.Color(204, 22, 22));

        SD_FacilityTable_Remove_button1.setText("Remove");
        SD_FacilityTable_Remove_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_FacilityTable_Remove_button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83)
                                    .addComponent(jLabel84))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SD_ClubActSDName_Combo, 0, 250, Short.MAX_VALUE)
                                    .addComponent(SD_ClubAct_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(SD_facilityAdd_button1))
                            .addComponent(SD_facility_remove_button1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SD_Facility_FullAddButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jSeparator9)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(SD_FacilityTable_Remove_button1)
                                .addContainerGap(27, Short.MAX_VALUE))))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(SD_ClubActSDName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel84)
                                    .addComponent(SD_ClubAct_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SD_facilityAdd_button1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SD_facility_remove_button1))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(SD_Facility_FullAddButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(SD_FacilityTable_Remove_button1))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel83.setVisible(false);
        SD_ClubActSDName_Combo.setVisible(false);
        SD_Facility_FullAddButton1.setVisible(false);
        jLabel84.setVisible(false);
        SD_ClubAct_combo.setVisible(false);
        SD_facilityAdd_button1.setVisible(false);
        SD_facility_remove_button1.setVisible(false);
        SD_FacilityTable_Remove_button1.setVisible(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel12);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Spouse / Dependants", jPanel11);

        jButton2.setText("Preview");

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton10.setText("Add to Master");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        prev_Data.setText("Pervious");
        prev_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev_DataActionPerformed(evt);
            }
        });

        next_data.setText("Next");
        next_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_dataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(37, 37, 37)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton2)
                        .addGap(31, 31, 31)
                        .addComponent(prev_Data)
                        .addGap(35, 35, 35)
                        .addComponent(next_data)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton10)
                    .addComponent(prev_Data)
                    .addComponent(next_data))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setVisible(false);
        jButton10.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    String MemberNo;
    String MemberName;
    String MemberShipType;
    
    String IDProofDoc;
    Date MemberDOB;
    Date MemberSince;
    String DocumentUniqueNo;
    String Gender;
    String SpouceName;
    Date MarriageDate;
    
    String FatherName;
    String MotherName;
    Date FatherDOB;
    Date MotherDOB;
    String FatherBloodGroup;
    String FatherPhoneNo;
    String FatherEmailID;
    String FatherIDProof;
    String FatherUniqueNo;
    String MotherBloodGroup;
    String MotherPhoneNo;
    String MotherEmailid;
    String MotherIdProof;
    String MotherUniqueNo;
    
    String ResidentialAddress;
    String OfficeAddress;
    
    String MobileNo;
    String ResidenceNo;
    String OfficeNo;
    String CommunicationAddress;
    String EmailId;
    String Twitter;
    
    String MobilePhoneUse;
    String Proffesion;
    String ClubActivity;
    String PlaySport;
    String SpecialTalent;
    
    String FacilitiesOpted;
    String RecreationalActivityStr;
    
    String ChildrenDetailedStr;
    String OtherClubInfoStr;
    String DeactivatedRef;
    String SD_Sports_FullStr;
    String SD_RActivity_FullStr;
    Properties SD_Ractivity_Properties = new Properties();
    Object oj;
    String SD_Facility_FullStr;
    Object SD_Facility_Object;
    String SD_Talent_FullStr;
    Object SD_Talent_Object;
    
    String kymmember_id;
    String kymmember_arv_id;
    String kymmember_deacRef;
    String kymmember_arv_deacRef;
    
    String customer_id;
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      try{
          prev_Data.setEnabled(false);
          next_data.setEnabled(false);
        mno_text.setText("");
        as_Per_Customer_Table.setVisible(false);
        MemberName_text.setText("");
         reset();
      }catch(Exception e)
      {
          e.printStackTrace();
      }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       KymMasterDialog KymMasterDialogList;
        try {
            KymMasterDialogList = KymMasterDialog.getDialog(this, m_App,true);
            KymMasterDialogList.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
          //String Srno = recreationalAct_slno_text.getText();
          String Acitvity = recreational_Sport_Text.getText();
          String Level1 = recreational_level_text.getText();
          
          RecreationalActivitiesListClass rs = new RecreationalActivitiesListClass();
          rs.setActivityLevel(Level1);
          rs.setSlNo((RecreationalActivityList.size()+1)+"");
          rs.setActivityName(Acitvity);
          
          RecreationalActivityList.add(rs);
          try{
            MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
            RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
            
            RecreationalActivity_JTable.setVisible(true);
            recreational_Sport_Text.setText(null);
            recreational_level_text.setText(null);
            
          }
          catch(BasicException e){
              e.printStackTrace();
          }
          
          
    }//GEN-LAST:event_jButton9ActionPerformed

    private void Recractivity_remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Recractivity_remove_btnActionPerformed
        if(RecreationalActivity_JTable.getSelectedRow()!=-1){
            if(RecreationalActivity_JTable.getSelectedRow()< RecreationalActivityList.size()){
                
                int row = RecreationalActivity_JTable.getSelectedRow();
                RecreationalActivityList.remove(row);
                
                try{
                    MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                    RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                }
                catch(BasicException e){
                    e.printStackTrace();
                }
                
            }
        }
        
    }//GEN-LAST:event_Recractivity_remove_btnActionPerformed

    private void facilityAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityAdd_buttonActionPerformed
        if(FacilityOption_Combo.getSelectedIndex()!=-1){
            String selectedFac = FacilityOption_Combo.getSelectedItem().toString();
            int flag=0;
            for(int i=0;i<SelectedFacilityList.size();i++){
                String t = SelectedFacilityList.get(i).toString();
                if(t.equals(selectedFac)){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                SelectedFacilityList.add(selectedFac);
                Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
            }
            else{
                JOptionPane.showMessageDialog(this, "Name Already Exsist. Please select another name.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_facilityAdd_buttonActionPerformed

    private void facilityRemove_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facilityRemove_buttonActionPerformed
        int row = Facility_Jlist.getSelectedIndex();
        if(row>=0){
            SelectedFacilityList.remove(row);
            Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
        }
    }//GEN-LAST:event_facilityRemove_buttonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(activities_combo.getSelectedIndex()!=-1){
            String Act = activities_combo.getSelectedItem().toString();
            int flag=0;
            for(int i=0;i<SelectedActivityList.size();i++){
                String t = SelectedActivityList.get(i).toString();
                if(t.equals(Act)){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                SelectedActivityList.add(Act);
                Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
            }
            else{
                JOptionPane.showMessageDialog(this, "Name Already Exsist. Please select another name.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int row = Activities_jList.getSelectedIndex();
        if(row>=0){
            SelectedActivityList.remove(row);
            Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void ChildrenAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChildrenAdd_buttonActionPerformed
//        if(ChildrenName_text.getText()!=null && ChildrenName_text.getText().trim().length()>0){
//           if(ChildrenDOB_text.getText()!=null && ChildrenDOB_text.getText().trim().length()>0){ 
//              if(ChildrenIDProof_combo.getSelectedIndex()!=-1){
//                 
//             try{    
//                  String Name = ChildrenName_text.getText().trim();
//                 String Cgender="";
//                 if(ChildrenMale_radio.isSelected()){
//                     Cgender="Male";
//                 }
//                 else{
//                     Cgender="Female";
//                 }
//                 
//                 String BloodGrp="";
//                // if(ChildrenBlood_Combo.getSelectedIndex()!=-1){
//                    // BloodGrp=ChildrenBlood_Combo.getSelectedItem().toString();
//                // } 
//                 String idProof="";
//                 if(ChildrenIDProof_combo.getSelectedIndex()!=-1){
//                     idProof=ChildrenIDProof_combo.getSelectedItem().toString();
//                 }
//                 String PhoneNo=ChildrenPhone_text.getText().trim();
//                 String EmailId=ChildrenEmailId_text.getText().trim();
//                 String Uniqueno=ChildrenUniqueNo_text.getText().trim();
//                 Date ChildDate=new Date(); 
//                 
//                 
//                 
//                     ChildDate=(Date) formatter.parse(ChildrenDOB_text.getText());
//                     
//                     ChildrenClass cl = new ChildrenClass();
//                     cl.setName(Name);
//                     cl.setGender(Cgender);
//                     cl.setBloodGrp(BloodGrp);
//                     cl.setIDProof(idProof);
//                     cl.setPhoneNo(PhoneNo);
//                     cl.setEmailID(EmailId);
//                     cl.setUniqueNo(Uniqueno);
//                     cl.setDateOfBirth(ChildDate);
//                     
//                     ChildrenClassList.add(cl);
//                     
//                     MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
//                     children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
//                     
//                     children_Jtable.setVisible(true);
//                     
//                     JOptionPane.showMessageDialog(this, "Children detail added successfully. ", "Success", JOptionPane.INFORMATION_MESSAGE);
//                     
//                     
//                     ChildrenName_text.setText(null);
//                     ChildrenDOB_text.setText(null);
//                     ChildrenPhone_text.setText(null);
//                     ChildrenEmailId_text.setText(null);     
//                     ChildrenUniqueNo_text.setText(null);        
//                    // ChildrenBlood_Combo.setSelectedIndex(-1);
//                    // ChildrenIDProof_combo.setSelectedIndex(-1);
//                     ChildrenMale_radio.setSelected(true);
//                             
//                 }
//                 catch(ParseException ex){
//                            Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
//                            ex.printStackTrace();
//                            new MessageInf(ex).show(new JFrame()); 
//                 }
//                 catch(BasicException ex){
//                            Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
//                            ex.printStackTrace();
//                            new MessageInf(ex).show(new JFrame()); 
//                 }
//               }
//              else{
//                  JOptionPane.showMessageDialog(this, "Select ID Proof .", "Warning", JOptionPane.WARNING_MESSAGE);
//              }
//           }
//           else{
//               JOptionPane.showMessageDialog(this, "Date of Birth cannot be null", "Warning", JOptionPane.WARNING_MESSAGE);
//           }
//        }
//        else{
//            JOptionPane.showMessageDialog(this, "Name cannot be null", "Warning", JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_ChildrenAdd_buttonActionPerformed

    private void ChildRemove_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChildRemove_ButtonActionPerformed
       if(children_Jtable.getSelectedRow()!=-1){
            if(children_Jtable.getSelectedRow()< ChildrenClassList.size()){
                
                int row = children_Jtable.getSelectedRow();
                ChildrenClassList.remove(row);
                
                try{
                    MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                    children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                }
                catch(BasicException e){
                    e.printStackTrace();
                }
                
            }
        }
    }//GEN-LAST:event_ChildRemove_ButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if(OtherClubName_Combo.getSelectedIndex()!=-1){
          if(OtherClubMemno_text.getText()!=null && OtherClubMemno_text.getText().trim().length()>0){
            if(OtherClubCity_combo.getSelectedIndex()!=-1){  
              try{  
                String Clubname=OtherClubName_Combo.getSelectedItem().toString();
                String OtherClubMemno=OtherClubMemno_text.getText().trim();
                String OtherClubMemtype=jTextField34.getText().trim();
                String OtherCity=OtherClubCity_combo.getSelectedItem().toString();
                Date OtherClubMemberSince=null;
                if(OtherClubMemberSince_text.getText()!=null && OtherClubMemberSince_text.getText().trim().length()>0){
                    OtherClubMemberSince=(Date) formatter.parse(OtherClubMemberSince_text.getText());
                }
                
                OtherClubClass oc =new OtherClubClass();
                oc.setClubName(Clubname);
                oc.setMemberno(OtherClubMemno);
                oc.setTypeofMember(OtherClubMemtype);
                oc.setCity(OtherCity);
                oc.setMemberSince(OtherClubMemberSince);
                
                OtherClubClassListAll_Class.add(oc);
                
                MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                OtherClubMember_JTable.setVisible(true);
                
                OtherClubName_Combo.setSelectedIndex(-1);
                OtherClubMemno_text.setText(null);
                jTextField34.setText(null);
                OtherClubCity_combo.setSelectedIndex(-1);
                OtherClubMemberSince_text.setText(null);
              }
              catch(ParseException ex){
                  Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                  ex.printStackTrace();
                  new MessageInf(ex).show(new JFrame()); 
              }
              catch(BasicException ex){
                  Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                  ex.printStackTrace();
                  new MessageInf(ex).show(new JFrame()); 
              }
            }
            else{
                JOptionPane.showMessageDialog(this, "Select City first", "Warning", JOptionPane.WARNING_MESSAGE);
            }
          }
          else{
              JOptionPane.showMessageDialog(this, "Enter Membership No. first", "Warning", JOptionPane.WARNING_MESSAGE);
          }
       }
       else{
           JOptionPane.showMessageDialog(this, "Select Club Name first", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if(OtherClubMember_JTable.getSelectedRow()!=-1){
            if(OtherClubMember_JTable.getSelectedRow()< OtherClubClassListAll_Class.size()){
                
                int row = OtherClubMember_JTable.getSelectedRow();
                OtherClubClassListAll_Class.remove(row);
                
                try{
                    MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                    OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                }
                catch(BasicException e){
                    e.printStackTrace();
                }
                
            }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void mno_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mno_textKeyPressed
       
    }//GEN-LAST:event_mno_textKeyPressed

    private void mno_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mno_textKeyReleased
         if(mno_text.getText()!=null && mno_text.getText().trim().length()>0){
            String memno=mno_text.getText().trim();
            try{
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM CUSTOMERS where SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj!=null){
                   jButton3.setEnabled(true);
                    prev_Data.setEnabled(true);
                    next_data.setEnabled(false);
                    String memberOrgname = obj[0].toString();
                    
                    MemberName_text.setText(memberOrgname);
                    MemberName_text.setVisible(true);
                    MemberName_text.setForeground(Color.BLUE);
                    as_Per_Customer_Table.setVisible(true);
                     as_Per_Customer_Table.setText("(As Per Customer Table)");
                     as_Per_Customer_Table.setForeground(Color.darkGray);
                  //  Save_button.setEnabled(true);
                    
                    // ************** CHECK IN KNOW YOUR MEMBER LIST ******************************************************* 
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM KYMMEMBER where MEMBERNO=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                    if(obj1!=null){
                        kymmember_id=obj1[0].toString();
                        getAlreadyLoadedMemberInfo(kymmember_id);
                    }
                    else{
                        reset();
                    }
                    if(obj1!=null)
                    {
                    Object kymmember_deacRef_obj[] = (Object[]) new StaticSentence(m_App.getSession(), "SELECT deacRef FROM KYMMEMBER where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj1[0].toString());
                    
                    if(kymmember_deacRef_obj[0]!=null)
                   {
                       kymmember_deacRef=(String)kymmember_deacRef_obj[0].toString();
                    kymmember_arv_id=kymmember_deacRef;
                   }
                    }
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, true);
                    jTabbedPane1.setEnabledAt(3, true);
                    jTabbedPane1.setEnabledAt(4, true);
                    jTabbedPane1.setEnabledAt(5, true);
                    jTabbedPane1.setEnabledAt(6, true);
                    jTabbedPane1.setEnabledAt(7, true);
                    
                }
                else{
                    prev_Data.setEnabled(false);
                    next_data.setEnabled(false);
                    MemberName_text.setText("No match found.");
                    MemberName_text.setVisible(true);
                    MemberName_text.setForeground(Color.RED);
                    as_Per_Customer_Table.setText("");
                    as_Per_Customer_Table.setForeground(Color.red);
                   
                   // Save_button.setEnabled(false);
                    reset();
                    
                    jTabbedPane1.setEnabledAt(1, false);
                    jTabbedPane1.setEnabledAt(2, false);
                    jTabbedPane1.setEnabledAt(3, false);
                    jTabbedPane1.setEnabledAt(4, false);
                    jTabbedPane1.setEnabledAt(5, false);
                    jTabbedPane1.setEnabledAt(6, false);
                    jTabbedPane1.setEnabledAt(7, false);
                    TypeofMember_combo.setText(null);
                    IDproofDoc_combo.setText(null);
                    male_radio.setVisible(false);
                    female_radio.setVisible(false);
                    SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
                }
                
            }
            catch(BasicException e){
                
            }
        }
        else{
            MemberName_text.setVisible(false);
            try{
            reset();
            prev_Data.setEnabled(false);
            next_data.setEnabled(false);
            jButton3.setEnabled(false);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_mno_textKeyReleased

    private void SD_Sports_AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Sports_AddButtonActionPerformed
         if(sports_SD_Combo.getSelectedIndex()!=-1){
             if(SD_Sports_Textfeild.getText()!=null && SD_Sports_Textfeild.getText().trim().length()>0 ){
                 String SD_name = sports_SD_Combo.getSelectedItem().toString();
                 String SD_sport= SD_Sports_Textfeild.getText().trim();
                 
                 SD_Sports_Class sc = new SD_Sports_Class();
                 sc.setSD_Name(SD_name);
                 sc.setSD_Sports(SD_sport);
                 SD_Sports_ClassListAll.add(sc);
                 
                 try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                    SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));
                    SD_Sports_Jtable.setVisible(true);
                    SD_Sports_Textfeild.setText(null);
                    sports_SD_Combo.setSelectedIndex(-1);

                  }
                  catch(BasicException e){
                      e.printStackTrace();
                  }
             }
             else{
                 JOptionPane.showMessageDialog(this, "Enter sports played by them.", "Warning", JOptionPane.WARNING_MESSAGE);
             }
         }
         else{
             JOptionPane.showMessageDialog(this, "Select Pouuse or dependant", "Warning", JOptionPane.WARNING_MESSAGE);
         }
    }//GEN-LAST:event_SD_Sports_AddButtonActionPerformed

    private void SD_Ractivity_AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Ractivity_AddButtonActionPerformed
       if(SD_RActivity_Combo.getSelectedIndex()!=-1){
           if(SD_RActivity_Textfeild.getText()!=null && SD_RActivity_Textfeild.getText().trim().length()>0){
               if(SD_RActivityLevel_Textfeild.getText()!=null && SD_RActivityLevel_Textfeild.getText().trim().length()>0){
                   String SD_Name = SD_RActivity_Combo.getSelectedItem().toString();
                   String SD_Activityname = SD_RActivity_Textfeild.getText().trim();
                   String SD_level = SD_RActivityLevel_Textfeild.getText().trim();
                   SD_RActivity_Class sd = new SD_RActivity_Class();
                   sd.setSD_Name(SD_Name);
                   sd.setSD_Sports(SD_Activityname);
                   sd.setSD_level(SD_level);
                   SD_RActivity_ClassListAll.add(sd);
                   
                   try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                    SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                    SD_RActivity_Jtable.setVisible(true);
                    SD_RActivityLevel_Textfeild.setText(null);
                    SD_RActivity_Textfeild.setText(null);
                    SD_RActivity_Combo.setSelectedIndex(-1);

                  }
                  catch(BasicException e){
                      e.printStackTrace();
                  }
               
               
               }
               else{
                   JOptionPane.showMessageDialog(this, "Enter Level for sports / Activity name ", "Warning", JOptionPane.WARNING_MESSAGE);
               }
           }
           else{
                JOptionPane.showMessageDialog(this, "Enter sports / Activity name ", "Warning", JOptionPane.WARNING_MESSAGE);
           }
       }
       else{
           JOptionPane.showMessageDialog(this, "Select Pouuse or dependant", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_SD_Ractivity_AddButtonActionPerformed

    private void SD_Facility_FullAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Facility_FullAddButtonActionPerformed
       if(SelectedSD_FacilityList.size()>0){
           String SpouceName = SD_FacilitySpouce_Combo.getSelectedItem().toString();
           String facility = "";
           for(int i=0;i<SelectedSD_FacilityList.size();i++){
               String x=SelectedSD_FacilityList.get(i).toString();
               facility=facility+x+",";
           }
           
           SD_Facility_Class sf = new SD_Facility_Class();
           sf.setSD_Name(SpouceName);
           sf.setSD_Facility(facility);
           SD_Facility_ClassListAll.add(sf);
           
           try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                    SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                    SD_Facility_Jtable.setVisible(true);
                    SelectedSD_FacilityList=new ArrayList<String>();
                    SD_facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedSD_FacilityList));
                    SD_FacilitySpouce_Combo.setSelectedIndex(-1);
                    SD_facname_combo.setSelectedIndex(-1);
           }
           catch(BasicException e){
               e.printStackTrace();
           }
           
       }
       
    }//GEN-LAST:event_SD_Facility_FullAddButtonActionPerformed
    List<String>SelectedSD_FacilityList = new ArrayList<String>();
    private void SD_facilityAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_facilityAdd_buttonActionPerformed
        if(SD_FacilitySpouce_Combo.getSelectedIndex()!=-1){
           if(SD_facname_combo.getSelectedIndex()!=-1){
               String selFac = SD_facname_combo.getSelectedItem().toString();
               
               int flag=0;
               for(int i=0;i<SelectedSD_FacilityList.size();i++){
                   String x= SelectedSD_FacilityList.get(i).toString();
                   if(x.equals(selFac)){
                       flag=1;
                       break;
                   }
               }
               if(flag==0){
                    SelectedSD_FacilityList.add(selFac);
                    SD_facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedSD_FacilityList));
               }
               else{
                   JOptionPane.showMessageDialog(this, "Facility already added. Select another facility", "Warning", JOptionPane.WARNING_MESSAGE); 
               }
               
               
               
               
               
           } 
            
        }
    }//GEN-LAST:event_SD_facilityAdd_buttonActionPerformed

    private void SD_facility_remove_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_facility_remove_buttonActionPerformed
       if(SD_facility_Jlist.getSelectedIndex()!=-1){
           int row = SD_facility_Jlist.getSelectedIndex();
           SelectedSD_FacilityList.remove(row);
           SD_facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedSD_FacilityList));
           
       }
    }//GEN-LAST:event_SD_facility_remove_buttonActionPerformed

    private void SD_FacilityTable_Remove_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_FacilityTable_Remove_buttonActionPerformed
        if(SD_Facility_Jtable.getSelectedRow()!=-1){
            int row = SD_Facility_Jtable.getSelectedRow();
            SD_Facility_ClassListAll.remove(row);
            
            try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                    SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                    SD_Facility_Jtable.setVisible(true);
           }
           catch(BasicException e){
               e.printStackTrace();
           }
            
        }
    }//GEN-LAST:event_SD_FacilityTable_Remove_buttonActionPerformed

    private void SD_talent_AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_talent_AddButtonActionPerformed
       if(SD_talent_Combo.getSelectedIndex()!=-1){
           if(SD_talent_Textfeild.getText()!=null && SD_talent_Textfeild.getText().trim().length()>0){
             String Spousename = SD_talent_Combo.getSelectedItem().toString();
             String Talent = SD_talent_Textfeild.getText().trim();
               
             SD_Talent_Class st = new SD_Talent_Class();
             st.setSD_Name(Spousename);
             st.setSD_Talent(Talent);
             SD_Talent_ClassListAll.add(st);
             
             try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                    SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                    SD_talent_Jtable.setVisible(true);
                    SD_talent_Combo.setSelectedIndex(-1);
                    SD_talent_Textfeild.setText(null);
                    
                    
                  }
                  catch(BasicException e){
                      e.printStackTrace();
                  }
               
           }
           else{
               JOptionPane.showMessageDialog(this, "Please enter talent", "Warning", JOptionPane.WARNING_MESSAGE); 
           }
        }
       else{
           JOptionPane.showMessageDialog(this, "Select Spouse/ dependant.", "Warning", JOptionPane.WARNING_MESSAGE); 
       }
    }//GEN-LAST:event_SD_talent_AddButtonActionPerformed

    private void SD_Facility_FullAddButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Facility_FullAddButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SD_Facility_FullAddButton1ActionPerformed

    private void SD_facilityAdd_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_facilityAdd_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SD_facilityAdd_button1ActionPerformed

    private void SD_facility_remove_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_facility_remove_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SD_facility_remove_button1ActionPerformed

    private void SD_FacilityTable_Remove_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_FacilityTable_Remove_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SD_FacilityTable_Remove_button1ActionPerformed

    private void OtherClubName_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtherClubName_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OtherClubName_ComboActionPerformed

    private void prev_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prev_DataActionPerformed
                next_data.setEnabled(true);
                        
           
            try{
                
                
                            SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();                                                                                                                                                              
                    
                    // ************** CHECK IN KNOW YOUR MEMBER LIST ******************************************************* 
                   
                        if(kymmember_arv_id!=null&&kymmember_arv_id.trim().length()>0)
                        {
                            prev_Data.setEnabled(true);
                        getAlreadyLoadedMemberInfo1(kymmember_arv_id);
                        }
                        else
                        {
                            prev_Data.setEnabled(false);
                        }
                    
                    
                   
                 Object[] kymmember_arv=(Object[]) new StaticSentence(m_App.getSession(), "SELECT deacRef FROM KYMMEMBER_ARV where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(kymmember_arv_id);
       
                 if(kymmember_arv!=null)
                     {
                     if(kymmember_arv[0]!=null)
         {
          kymmember_arv_deacRef = kymmember_arv[0].toString();
          kymmember_arv_id=kymmember_arv_deacRef;        
         }
         else
         {
             
             prev_Data.setEnabled(false);
         }
                
                     } else
                 {
                     prev_Data.setEnabled(false);
                 }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
                    
    }//GEN-LAST:event_prev_DataActionPerformed

    private void next_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_dataActionPerformed
        // TODO add your handling code here:
                                                 
prev_Data.setEnabled(true);
           
            try{
                 
         
           SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();
                                                                                                                                                                  
                    
                    // ************** CHECK IN KNOW YOUR MEMBER LIST ******************************************************* 
                   
                        if(kymmember_arv_deacRef!=null && kymmember_arv_deacRef.trim().length()>0)
                        {
                            prev_Data.setEnabled(true);
                        getAlreadyLoadedMemberInfo2(kymmember_arv_deacRef);
                        }
                        else
                        {
                            if(kymmember_deacRef!=null)
                            {
                            Object[] kymmember=(Object[]) new StaticSentence(m_App.getSession(), "SELECT id FROM KYMMEMBER where deacRef=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(kymmember_deacRef);
                            if(kymmember!=null)
                            {
                            getAlreadyLoadedMemberInfo(kymmember[0].toString());
                            }
                            }
                            next_data.setEnabled(false);
                        }
                    
                    Object[] kymmember_arv=(Object[]) new StaticSentence(m_App.getSession(), "SELECT id FROM KYMMEMBER_ARV where deacRef=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(kymmember_arv_deacRef);
                 if(kymmember_arv!=null){
           if(kymmember_arv[0]!=null)
         {
          kymmember_arv_id = kymmember_arv[0].toString();
          kymmember_arv_deacRef=kymmember_arv_id;
         // kymmember_arv_id=kymmember_deacRef;
         }
       }
                    
                
                
               
                
            }catch(Exception e){
                e.printStackTrace();
            }
    }//GEN-LAST:event_next_dataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Activities_jList;
    private javax.swing.JButton ChildRemove_Button;
    private javax.swing.JButton ChildrenAdd_button;
    private javax.swing.JLabel ChildrenBlood_Combo;
    private javax.swing.JLabel ChildrenDOB_text;
    private javax.swing.JLabel ChildrenEmailId_text;
    private javax.swing.JRadioButton ChildrenFemale_radio;
    private javax.swing.JLabel ChildrenIDProof_combo;
    private javax.swing.JRadioButton ChildrenMale_radio;
    private javax.swing.JLabel ChildrenName_text;
    private javax.swing.JLabel ChildrenPhone_text;
    private javax.swing.JLabel ChildrenUniqueNo_text;
    private javax.swing.JLabel DOB_text;
    private javax.swing.JLabel DateOfMarriage_text;
    private javax.swing.JLabel Email_Text;
    private javax.swing.JComboBox FacilityOption_Combo;
    private javax.swing.JList Facility_Jlist;
    private javax.swing.JLabel FatherBloodGroup_combo;
    private javax.swing.JLabel FatherEmailId_Text;
    private javax.swing.JLabel FatherIdProof_combo;
    private javax.swing.JLabel FatherName_text;
    private javax.swing.JLabel FatherPhoneNo_Text;
    private javax.swing.JLabel FatherUniqueNo_text;
    private javax.swing.JLabel IDproofDoc_combo;
    private javax.swing.JLabel MemberName_text;
    private javax.swing.JLabel MobileNo_text;
    private javax.swing.JLabel MobilePhoneUse_Combo;
    private javax.swing.JLabel MotherBloodGroup_combo;
    private javax.swing.JLabel MotherDOB_text;
    private javax.swing.JLabel MotherEmailid_text;
    private javax.swing.JLabel MotherIdProof_combo;
    private javax.swing.JLabel MotherName_text;
    private javax.swing.JLabel MotherPhoneNo_text;
    private javax.swing.JLabel MotherUniqueNo_text;
    private javax.swing.JLabel OffPinNo_text;
    private javax.swing.JLabel OffPost_Text;
    private javax.swing.JLabel OffPost_Text1;
    private javax.swing.JLabel OffState_combo;
    private javax.swing.JLabel Off_Country_combo;
    private javax.swing.JLabel Off_line2_text;
    private javax.swing.JLabel OfficeNo_text;
    private javax.swing.JRadioButton Office_Radio;
    private javax.swing.JComboBox OtherClubCity_combo;
    private javax.swing.JLabel OtherClubMemberSince_text;
    private javax.swing.JTable OtherClubMember_JTable;
    private javax.swing.JLabel OtherClubMemno_text;
    private javax.swing.JComboBox OtherClubName_Combo;
    private javax.swing.JLabel PlaySport_Text;
    private javax.swing.JLabel Proffesion_Combo;
    private javax.swing.JLabel RLine2Res_text;
    private javax.swing.JButton Recractivity_remove_btn;
    private javax.swing.JTable RecreationalActivity_JTable;
    private javax.swing.JLabel ResCity_combo;
    private javax.swing.JLabel ResCountry_combo;
    private javax.swing.JLabel ResPinNo_text;
    private javax.swing.JLabel ResPost_text;
    private javax.swing.JLabel ResState_Combo;
    private javax.swing.JLabel Residence_no_text;
    private javax.swing.JLabel Rline1Res_text;
    private javax.swing.JComboBox SD_ClubActSDName_Combo;
    private javax.swing.JList SD_ClubAct_Jlist;
    private javax.swing.JComboBox SD_ClubAct_combo;
    private javax.swing.JComboBox SD_FacilitySpouce_Combo;
    private javax.swing.JButton SD_FacilityTable_Remove_button;
    private javax.swing.JButton SD_FacilityTable_Remove_button1;
    private javax.swing.JButton SD_Facility_FullAddButton;
    private javax.swing.JButton SD_Facility_FullAddButton1;
    private javax.swing.JTable SD_Facility_Jtable;
    private javax.swing.JTable SD_Facility_Jtable1;
    private javax.swing.JLabel SD_RActivityLevel_Textfeild;
    private javax.swing.JComboBox SD_RActivity_Combo;
    private javax.swing.JTable SD_RActivity_Jtable;
    private javax.swing.JLabel SD_RActivity_Textfeild;
    private javax.swing.JButton SD_Ractivity_AddButton;
    private javax.swing.JButton SD_Sports_AddButton;
    private javax.swing.JTable SD_Sports_Jtable;
    private javax.swing.JLabel SD_Sports_Textfeild;
    private javax.swing.JButton SD_facilityAdd_button;
    private javax.swing.JButton SD_facilityAdd_button1;
    private javax.swing.JList SD_facility_Jlist;
    private javax.swing.JButton SD_facility_remove_button;
    private javax.swing.JButton SD_facility_remove_button1;
    private javax.swing.JComboBox SD_facname_combo;
    private javax.swing.JButton SD_talent_AddButton;
    private javax.swing.JComboBox SD_talent_Combo;
    private javax.swing.JTable SD_talent_Jtable;
    private javax.swing.JTextField SD_talent_Textfeild;
    private javax.swing.JLabel SpecialTalent_text;
    private javax.swing.JLabel SpouseName_text;
    private javax.swing.JLabel Twitter_text;
    private javax.swing.JLabel TypeofMember_combo;
    private javax.swing.JComboBox activities_combo;
    private javax.swing.JLabel as_Per_Customer_Table;
    private javax.swing.JTable children_Jtable;
    private javax.swing.JPanel children_info_hidden_panel;
    private javax.swing.JButton facilityAdd_button;
    private javax.swing.JButton facilityRemove_button;
    private javax.swing.JLabel fatherDob_text;
    private javax.swing.JRadioButton female_radio;
    private javax.swing.JLabel idDocUniqueNo_text;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jTextField1;
    private javax.swing.JLabel jTextField34;
    private javax.swing.JRadioButton male_radio;
    private javax.swing.JLabel memberSince_text;
    private javax.swing.JLabel memberSince_text1;
    private javax.swing.JLabel mname_text;
    private javax.swing.JTextField mno_text;
    private javax.swing.JButton next_data;
    private javax.swing.JLabel offCity_combo;
    private javax.swing.JLabel offLine1_text;
    private javax.swing.JPanel other_clubs_hidden_panel;
    private javax.swing.JButton prev_Data;
    private javax.swing.JLabel recreational_Sport_Text;
    private javax.swing.JLabel recreational_level_text;
    private javax.swing.JRadioButton residence_Radio;
    private javax.swing.JComboBox sports_SD_Combo;
    // End of variables declaration//GEN-END:variables

 public String getTitle() {
   return "Know Your Member Form";  
    }

    @Override
    public void activate() throws BasicException {
       try{
       mno_text.setText(""); 
      ButtonGrp();
      loaddata();
      LoadAllComboBox();
       reset();
    }catch(Exception ex)
    {
        ex.printStackTrace();
    }
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


    public void loaddata() throws BasicException{
       
        
        MemberShipTypeList=new ArrayList<String>();
        MemberShipTypeList=getMemberTypeListAll();
        MemTypeComboBoxValModel=new ComboBoxValModel(MemberShipTypeList);
//        TypeofMember_combo.setModel(MemTypeComboBoxValModel);
//        TypeofMember_combo.setSelectedIndex(-1);
        
        if(RecreationalActivityList.size()>0){
            RecreationalActivity_JTable.setVisible(true);
        }
        else{
            RecreationalActivity_JTable.setVisible(false);
        }
        
        ChildrenMale_radio.setSelected(true);
        children_Jtable.setVisible(false);
        OtherClubMember_JTable.setVisible(false);
        
        MemberName_text.setVisible(false);
       // Save_button.setEnabled(false);
        male_radio.setSelected(true);
        residence_Radio.setSelected(true);
        SD_Sports_Jtable.setVisible(false);
        SD_Facility_Jtable.setVisible(false);
        
        
        System.out.println("Time zone : "+formatter.getTimeZone().getDisplayName());
    }
    
    
    public void ButtonGrp(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(male_radio);
        bg.add(female_radio);
        
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(residence_Radio);
        bg1.add(Office_Radio);
        
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(ChildrenMale_radio);
        bg2.add(ChildrenFemale_radio);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(5, false);
        jTabbedPane1.setEnabledAt(6, false);
        jTabbedPane1.setEnabledAt(7, false);
        
        jTabbedPane1.setForegroundAt(0, Color.red);
        jTabbedPane1.setForegroundAt(1, Color.blue);
        jTabbedPane1.setForegroundAt(2, Color.red);
        jTabbedPane1.setForegroundAt(3, Color.blue);
        jTabbedPane1.setForegroundAt(4, Color.red);
        jTabbedPane1.setForegroundAt(5, Color.blue);
        jTabbedPane1.setForegroundAt(6, Color.red);
        jTabbedPane1.setForegroundAt(7, Color.blue);
     }


    public void reset(){
          kymmember_id="";
          kymmember_arv_id="";
          kymmember_deacRef="";
          kymmember_arv_deacRef="";
          
         TypeofMember_combo.setText(null);
         IDproofDoc_combo.setText(null);
        female_radio.setVisible(false);
        male_radio.setVisible(false);
        //MemberName_text.setVisible(false);
      as_Per_Customer_Table.setVisible(false);
//      as_Per_Customer_Table.setText("NO DATA FOUND");
//      as_Per_Customer_Table.setForeground(Color.red);
        //mno_text.setText(null);
        mname_text.setText(null);
        FatherName_text.setText(null);
        DOB_text.setText(null);
      //  IDproofDoc_combo.setSelectedIndex(-1);
        idDocUniqueNo_text.setText(null);
       // TypeofMember_combo.setSelectedIndex(-1);
        memberSince_text1.setText(null);
        SpouseName_text.setText(null);
        DateOfMarriage_text.setText(null);
        male_radio.setSelected(true);
        
        jTextField1.setText(null);
        fatherDob_text.setText(null);
        MotherName_text.setText(null);
        MotherDOB_text.setText(null);
        
        Rline1Res_text.setText(null);
        RLine2Res_text.setText(null);
        ResPost_text.setText(null);
       // ResCity_combo.setSelectedIndex(-1);
        //ResCountry_combo.setSelectedIndex(-1);
      //  ResState_Combo.setSelectedIndex(-1);
        ResPinNo_text.setText(null);
        offLine1_text.setText(null);
        Off_line2_text.setText(null);
        OffPost_Text.setText(null);
        OffPinNo_text.setText(null);
       // offCity_combo.setSelectedIndex(-1);
       // Off_Country_combo.setSelectedIndex(-1);
        //OffState_combo.setSelectedIndex(-1);
        
        MobileNo_text.setText(null);
        Residence_no_text.setText(null);
        OfficeNo_text.setText(null);
        Email_Text.setText(null);
        Twitter_text.setText(null);
        residence_Radio.setSelected(true);
       // MobilePhoneUse_Combo.setSelectedIndex(-1);
       // Proffesion_Combo.setSelectedIndex(-1);
        activities_combo.setSelectedIndex(-1);
        FacilityOption_Combo.setSelectedIndex(-1);
        PlaySport_Text.setText(null);
        SpecialTalent_text.setText(null);
        recreational_Sport_Text.setText(null);
        recreational_level_text.setText(null);
        SelectedActivityList=new ArrayList<String>();
        Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
        SelectedFacilityList=new ArrayList<String>();
        Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
        
        RecreationalActivityList = new ArrayList<RecreationalActivitiesListClass>();
        RecreationalActivity_JTable.setVisible(false);
        
        children_Jtable.setVisible(false);
        
       // FatherBloodGroup_combo.setSelectedIndex(-1);
        FatherPhoneNo_Text.setText(null);
       // FatherIdProof_combo.setSelectedIndex(-1);
        FatherEmailId_Text.setText(null);
        FatherUniqueNo_text.setText(null);
       // MotherBloodGroup_combo.setSelectedIndex(-1);
        MotherPhoneNo_text.setText(null);
       // MotherIdProof_combo.setSelectedIndex(-1);
        MotherEmailid_text.setText(null);
        MotherUniqueNo_text.setText(null);
        
        OtherClubName_Combo.setSelectedIndex(-1);
        OtherClubMemno_text.setText(null);
        jTextField34.setText(null);
        OtherClubCity_combo.setSelectedIndex(-1);
        OtherClubMemberSince_text.setText(null);
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(5, false);
        jTabbedPane1.setEnabledAt(6, false);
        jTabbedPane1.setEnabledAt(7, false);
        jTabbedPane1.setSelectedIndex(0);
        
        SD_Sports_ClassListAll = new ArrayList<SD_Sports_Class>();
        SD_Sports_Jtable.setVisible(false);
        
        SD_RActivityLevel_Textfeild.setText(null);
        SD_RActivity_Textfeild.setText(null);
        SD_RActivity_Combo.setSelectedIndex(-1);
        SD_RActivity_Jtable.setVisible(false);
        SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
        
        SD_talent_Jtable.setVisible(false);
        SD_talent_Combo.setSelectedIndex(-1);
        SD_talent_Textfeild.setText(null);
        SD_Talent_ClassListAll=new ArrayList<SD_Talent_Class>();
        
        
        
    }
    
    
        
    private class ItemsListModel extends AbstractListModel {
        private java.util.List items;
        public ItemsListModel(java.util.List items) {
            this.items = items;
        }
        @Override
        public int getSize() {
            return items.size();
        }
        @Override
        public Object getElementAt(int i) {
            return items.get(i);
        }
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
                      ChildrenBloodGrpListAll.add(Xarr[i].toString());
               }
              return ChildrenBloodGrpListAll;
          }
          else{
              return ChildrenBloodGrpListAll;
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
                      OtherClubNameListAll.add(Xarr[i].toString());
               }
              return OtherClubNameListAll;
          }
          else{
              return OtherClubNameListAll;
          }
      }
    
    public List getMemberTypeListAll() throws  BasicException{
       List<Object> Temp = new ArrayList();
        
   
        Temp =  new StaticSentence(m_App.getSession(), " select Name from memtype where active=1 order by name ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).list();
                    
        return Temp;              
          
    }
    
    
// ********************************************************** END OF LOOP ***********************************************************************     
     

    public void LoadAllComboBox() throws BasicException{
        CityList = new ArrayList<String>();
        CityList=GetCityList(m_App);
        CityComboBoxValModel = new ComboBoxValModel(CityList);
       // ResCity_combo.setModel(CityComboBoxValModel);
        OffCityComboBoxValModel = new ComboBoxValModel(CityList);
        //offCity_combo.setModel(OffCityComboBoxValModel);
        //city_combo.setSelectedIndex(-1);
        OtherClubCityListBoxValModel=new ComboBoxValModel(CityList);
        OtherClubCity_combo.setModel(OtherClubCityListBoxValModel);
        
        CountryList = new ArrayList<String>();
        CountryList=GetCountryList(m_App);
        CountryComboBoxValModel = new ComboBoxValModel(CountryList);
       // ResCountry_combo.setModel(CountryComboBoxValModel);
        OffCountryComboBoxValModel = new ComboBoxValModel(CountryList);
        //Off_Country_combo.setModel(OffCountryComboBoxValModel);
        //Country_Combo.setSelectedIndex(-1);
        
        StateList = new ArrayList<String>();
        StateList=GetStateList(m_App);
        StateComboBoxValModel = new ComboBoxValModel(StateList);
       // ResState_Combo.setModel(StateComboBoxValModel);
        OffStateComboBoxValModel = new ComboBoxValModel(StateList);
       // OffState_combo.setModel(OffStateComboBoxValModel);
        //State_Combo.setSelectedIndex(-1);
        
        MobileOsList = new ArrayList<String>();
        MobileOsList=GetMobileOSList(m_App);
        MobileOSComboBoxValModel = new ComboBoxValModel(MobileOsList);
        //MobilePhoneUse_Combo.setModel(MobileOSComboBoxValModel);
        //MobileOS_Combo.setSelectedIndex(-1);
        
        ProfessionList = new ArrayList<String>();
        ProfessionList=GetProfessionList(m_App);
        professionComboBoxValModel = new ComboBoxValModel(ProfessionList);
       // Proffesion_Combo.setModel(professionComboBoxValModel);
        //Profession_combo.setSelectedIndex(-1);
        
        ClubActivityList = new ArrayList<String>();
        ClubActivityList=GetClubActivityList(m_App);
        ClubActivityComboBoxValModel = new ComboBoxValModel(ClubActivityList);
        activities_combo.setModel(ClubActivityComboBoxValModel);
        //ClubActivity_Combo.setSelectedIndex(-1);
        
        FacilityList = new ArrayList<String>();
        FacilityList=GetFacilityList(m_App);
        FacilityComboBoxValModel = new ComboBoxValModel(FacilityList);
        FacilityOption_Combo.setModel(FacilityComboBoxValModel);
        
        SD_FacilityName_ComboBoxValModel = new ComboBoxValModel(FacilityList);
        SD_facname_combo.setModel(SD_FacilityName_ComboBoxValModel);
        //Facility_combo.setSelectedIndex(-1);
        
        IDProofTypeList = new ArrayList<String>();
        IDProofTypeList=GetIDProofList(m_App);
        IdProofComboBoxValModel = new ComboBoxValModel(IDProofTypeList);
       // IDproofDoc_combo.setModel(IdProofComboBoxValModel);
        ChildrenIdProodListBoxValModel=new ComboBoxValModel(IDProofTypeList);
       // ChildrenIDProof_combo.setModel(ChildrenIdProodListBoxValModel);
       // ChildrenIDProof_combo.setSelectedIndex(-1);
         FatherIdProofBoxValModel=new ComboBoxValModel(IDProofTypeList);
       // FatherIdProof_combo.setModel(FatherIdProofBoxValModel);
        MotherIdProofBoxValModel=new ComboBoxValModel(IDProofTypeList);
        //MotherIdProof_combo.setModel(MotherIdProofBoxValModel);
        
        //IDProof_combo.setSelectedIndex(-1);
        
        ChildrenBloodGrpListAll=new ArrayList<String>();
        ChildrenBloodGrpListAll=GetBloodGroupList(m_App);
        ChildrenBloodGrpListBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
       // ChildrenBlood_Combo.setModel(ChildrenBloodGrpListBoxValModel);
        
        FatherBloodGroupBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
        //FatherBloodGroup_combo.setModel(FatherBloodGroupBoxValModel);
        MotherBloodGroupBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
       // MotherBloodGroup_combo.setModel(MotherBloodGroupBoxValModel);
        //ChildrenBlood_Combo.setSelectedIndex(-1);
        
        
        OtherClubNameListAll=new ArrayList<String>();
        OtherClubNameListAll=GetOtherClubNameList(m_App);
        OtherClubNameListBoxValModel=new ComboBoxValModel(OtherClubNameListAll);
        OtherClubName_Combo.setModel(OtherClubNameListBoxValModel);
        //ChildrenBlood_Combo.setSelectedIndex(-1);
        
    }
        
 // ********************************************************************* NEW CLASSESSS ************************************************   
    
   public class RecreationalActivitiesListClass{
        private String ActivitySlNo;
        private String ActivityName;
        private String ActivityLevel;
        
        public String GetSlNo(){
            return ActivitySlNo;
        }
        public void setSlNo(String ActivitySlNo){
            this.ActivitySlNo=ActivitySlNo;
        }
        
        public String GetActivityName(){
            return ActivityName;
        }
        public void setActivityName(String ActivityName){
            this.ActivityName=ActivityName;
        }
        public String GetActivityLevel(){
            return ActivityLevel;
        }
        public void setActivityLevel(String ActivityLevel){
            this.ActivityLevel=ActivityLevel;
        }
    }     
    public class ChildrenClass{
        private String Name;
        private String Gender;
        private Date DateOfBirth;
        private String BloodGrp;
        private String PhoneNo;
        private String EmailID;
        private String IDProof;
        private String UniqueNo;
        
        
        public String GetName(){
            return Name;
        }
        public void setName(String Name){
            this.Name=Name;
        }
        
        public String GetGender(){
            return Gender;
        }
        public void setGender(String Gender){
            this.Gender=Gender;
        }
        public String GetDateOfBirth(){
            if(DateOfBirth!=null)
            {
            String x= formatter.format(DateOfBirth);
            if(x!=null)
            {
            return x;
            }
            }
            return "";
        }
        public void setDateOfBirth(Date DateOfBirth){
            this.DateOfBirth=DateOfBirth;
        }
        
        public String GetBloodGrp(){
            return BloodGrp;
        }
        public void setBloodGrp(String BloodGrp){
            this.BloodGrp=BloodGrp;
        }
        public String GetPhoneNo(){
            return PhoneNo;
        }
        public void setPhoneNo(String PhoneNo){
            this.PhoneNo=PhoneNo;
        }
        public String GetEmailID(){
            return EmailID;
        }
        public void setEmailID(String EmailID){
            this.EmailID=EmailID;
        }
        public String GetIDProof(){
            return IDProof;
        }
        public void setIDProof(String IDProof){
            this.IDProof=IDProof;
        }
        public String GetUniqueNo(){
            return UniqueNo;
        }
        public void setUniqueNo(String UniqueNo){
            this.UniqueNo=UniqueNo;
        }
        
    }     

    public class OtherClubClass{
        private String ClubName;
        private String Memberno;
        private String TypeofMember;
        private String City;
        private Date MemberSince;
        
        
        public String GetClubName(){
            return ClubName;
        }
        public void setClubName(String ClubName){
            this.ClubName=ClubName;
        }
        
        public String GetMemberno(){
            return Memberno;
        }
        public void setMemberno(String Memberno){
            this.Memberno=Memberno;
        }
        public String GetTypeofMember(){
            return TypeofMember;
        }
        public void setTypeofMember(String TypeofMember){
            this.TypeofMember=TypeofMember;
        }
        public String GetCity(){
            return City;
        }
        public void setCity(String City){
            this.City=City;
        }
        public String GetMemberSince(){
            if(MemberSince!=null)
            {
            String x= formatter.format(MemberSince);
            return x;
            }
            return "";
        }
        public void setMemberSince(Date MemberSince){
            this.MemberSince=MemberSince;
        }
        
    }     
    // FOR SPOUSE AND DEPENDANTS 
    
    public class SD_Sports_Class{
        private String SD_Name;
        private String SD_Sports;
        
        
        
        public String GetSD_Name(){
            return SD_Name;
        }
        public void setSD_Name(String SD_Name){
            this.SD_Name=SD_Name;
        }
        public String GetSD_Sports(){
            return SD_Sports;
        }
        public void setSD_Sports(String SD_Sports){
            this.SD_Sports=SD_Sports;
        }
    }     
    public class SD_RActivity_Class{
        private String SD_Name;
        private String SD_Sports;
        private String SD_level;
        
        
        public String GetSD_Name(){
            return SD_Name;
        }
        public void setSD_Name(String SD_Name){
            this.SD_Name=SD_Name;
        }
        public String GetSD_Sports(){
            return SD_Sports;
        }
        public void setSD_Sports(String SD_Sports){
            this.SD_Sports=SD_Sports;
        }
        public String GetSD_level(){
            return SD_level;
        }
        public void setSD_level(String SD_level){
            this.SD_level=SD_level;
        }
    }  
    public class SD_Facility_Class{
        private String SD_Name;
        private String SD_Facility;
        
        public String GetSD_Name(){
            return SD_Name;
        }
        public void setSD_Name(String SD_Name){
            this.SD_Name=SD_Name;
        }
        public String GetSD_Facility(){
            return SD_Facility;
        }
        public void setSD_Facility(String SD_Facility){
            this.SD_Facility=SD_Facility;
        }
    }  
    public class SD_Talent_Class{
        private String SD_Name;
        private String SD_Talent;
        
        public String GetSD_Name(){
            return SD_Name;
        }
        public void setSD_Name(String SD_Name){
            this.SD_Name=SD_Name;
        }
        public String GetSD_Talent(){
            return SD_Talent;
        }
        public void setSD_Talent(String SD_Talent){
            this.SD_Talent=SD_Talent;
        }
    }  
    
    
    // *******************************************************************  GET ALREADY LOADED INFO *************************************************
    
    public class SD_ClubActivity_Class{
        private String SD_Name;
        private String SD_Activity;
        
        public String GetSD_Name(){
            return SD_Name;
        }
        public void setSD_Name(String SD_Name){
            this.SD_Name=SD_Name;
        }
        public String GetSD_Activity(){
            return SD_Activity;
        }
        public void setSD_Activity(String SD_Activity){
            this.SD_Activity=SD_Activity;
        }
    } 
    
    public void getAlreadyLoadedMemberInfo(String Id) {
      try{   
       
          SD_listAll = new ArrayList<String>();
          
          // ***********************  MAIN PAGE INFO **********************************************************
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MEMBERNAME,DATEOFBIRTH,IDPROOF,IDUNIQUENO,MEMBERTYPE,MEMBERSINCE,SPOUCENAME,DATEOFMARRIAGE,GENDER FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.STRING,Datas.STRING , Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP , Datas.STRING })).find(Id); 
          
          
          if(obj1!=null){
              if(obj1[0]!=null){
                  mname_text.setText(obj1[0].toString());
              }
              
              if(obj1[1]!=null){
                  Date dob = (Date) obj1[1];
                  String X = formatter.format(dob);
                  DOB_text.setText(X);
              }                                        
             if(obj1[2]!=null){
                 String idProofDoc=obj1[2].toString();
                 if(idProofDoc!=null && idProofDoc.trim().length()>0)
                 {
                  IDproofDoc_combo.setText(idProofDoc);
//                  for(int i=0;i<IDProofTypeList.size();i++){
//                      String x=IDProofTypeList.get(i).toString();
//                      if(x.equals(idproof)){
//                          IDproofDoc_combo.setSelectedIndex(i);
//                          break;
//                      }
//                  }
                 }
                 else
                 {
                     IDproofDoc_combo.setText(null);
                 }
              }
             else
             {
                 IDproofDoc_combo.setText(null);
             }
              
              if(obj1[3]!=null){
                  idDocUniqueNo_text.setText(obj1[3].toString());
              }
              
              if(obj1[4]!=null){
                  String memtype=obj1[4].toString();
                  TypeofMember_combo.setText(memtype);
//                  for(int i=0;i<MemberShipTypeList.size();i++){
//                      String x=MemberShipTypeList.get(i).toString();
//                      if(x.equals(memtype)){
//                          TypeofMember_combo.setSelectedIndex(i);
//                          break;
//                      }
//                  }
              }
//              else{
//                  TypeofMember_combo.setSelectedIndex(-1);
//              }
              
              
              if(obj1[5]!=null){
                  memberSince_text.setVisible(false);
                  Date memsince = (Date) obj1[5];
                  String X = formatter.format(memsince);
                  
                  memberSince_text1.setText(X);
              }
              else
              {
                  memberSince_text1.setText("");
              }
              if(obj1[6]!=null){
                  String spouse_name=obj1[6].toString();
                  if(spouse_name.trim().length()>0)
                  {
                  SpouseName_text.setText(spouse_name);
                  SD_listAll.add(obj1[6].toString());
                  }
                  else{
                      SpouseName_text.setText("");
                  }
              }
              else
              {
                  SpouseName_text.setText("");
              }
              if(obj1[7]!=null){
                  Date dom = (Date) obj1[7];
                  String X = formatter.format(dom);
                  if(X!=null && X.trim().length()>0)
                  {
                  DateOfMarriage_text.setText(X);
                  }
                  else
                  {
                      DateOfMarriage_text.setText("");
                  }
              }
                else
              {
                  DateOfMarriage_text.setText("");
              }
              if(obj1[8]!=null){
                 
                 String g=obj1[8].toString().toLowerCase();
                 if(g.equals("male")){
                     male_radio.setVisible(true);
                     male_radio.setSelected(true);
                     female_radio.setVisible(false);
                 }
                 else{
                     female_radio.setVisible(true);
                     female_radio.setSelected(true);
                     male_radio.setVisible(false);
                 }
              }
              
              // ***********************  MAIN PAGE INFO ENDS********************************************************** 
              
              
              
              // ***********************  PARENTS INFO  **********************************************************
              
              Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT FATHERNAME,FATHERDOB,MOTHERNAME,MOTHERDOB,FATHERBG,FATHERPHONENO,FATHEREMAILID,FATHERIDPROOF,FATHERUNIQUENO,MOTHERBG,MOTHERPHONENO,MOTHEREMAILID,MOTHERIDPROOF,MOTHERUNIQUENO FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(
                   new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.TIMESTAMP,Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING })).find(Id); 
          
                if(obj2!=null){

                    if(obj2[0]!=null){
                        String fn = obj2[0].toString();
                        FatherName_text.setText(fn);
                        jTextField1.setText(fn);
                    }
                    if(obj2[1]!=null){
                         Date dom = (Date) obj2[1];
                          String X = formatter.format(dom);
                          if(X!=null && X.trim().length()>0)
                          {
                          fatherDob_text.setText(X);
                          }
                          else
                          {
                              fatherDob_text.setText("");
                          }
                    }
                    else
                    {
                        fatherDob_text.setText("");
                    }
                    if(obj2[2]!=null){
                        String fn = obj2[2].toString();
                        if(fn!=null&& fn.trim().length()>0)
                        {
                        MotherName_text.setText(fn);
                        }
                        else
                        {
                            MotherName_text.setText("");
                        }
                    }
                    else
                    {
                        MotherName_text.setText("");
                    }
                    if(obj2[3]!=null){
                         Date dom = (Date) obj2[3];
                          String X = formatter.format(dom);
                          if(X!=null&&X.trim().length()>0)
                          {
                          MotherDOB_text.setText(X);
                          }
                          else
                              {
                                  MotherDOB_text.setText("");
                              }
                    }
                    else{
                    MotherDOB_text.setText("");
                    }
                    if(obj2[4]!=null){
                        String Fatherbloodgrp= obj2[4].toString();
//                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
//                            String x= ChildrenBloodGrpListAll.get(i).toString();
//                            if(x.equals(Fatherbloodgrp)){
//                                FatherBloodGroup_combo.setSelectedIndex(i);
//                                break;
//                            }
                     // }
                        if(Fatherbloodgrp!=null && Fatherbloodgrp.trim().length()>0)
                        {
                         FatherBloodGroup_combo.setText(Fatherbloodgrp);
                        }
                        else 
                        {
                            FatherBloodGroup_combo.setText("");
                        }
                    }
                    else 
                    {
                        FatherBloodGroup_combo.setText("");
                    }

                    if(obj2[5]!=null){
                        String fn = obj2[5].toString();
                        if(fn.trim().length()>0)
                        {
                        FatherPhoneNo_Text.setText(fn);
                        }
                         else
                    {
                        FatherPhoneNo_Text.setText("");
                    }
                    }
                    else
                    {
                        FatherPhoneNo_Text.setText("");
                    }
                   
                    if(obj2[6]!=null){
                        String fn = obj2[6].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        FatherEmailId_Text.setText(fn);
                        }
                        else
                        {
                            FatherEmailId_Text.setText("");
                        }
                    }
                    else
                    {
                        FatherEmailId_Text.setText("");
                    }
                    if(obj2[7]!=null){
                        String FatherIdProof= obj2[7].toString();
//                        for(int i=0;i<IDProofTypeList.size();i++){
//                            String x= IDProofTypeList.get(i).toString();
//                            if(x.equals(FatherIdProof)){
//                                FatherIdProof_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                        if(FatherIdProof!=null&&FatherIdProof.trim().length()>0)
                        {
                        FatherIdProof_combo.setText(FatherIdProof);
                        }
                        else
                        {
                            FatherIdProof_combo.setText("");
                        }
                    }
                    else
                    {
                        FatherIdProof_combo.setText("");
                    }

                    if(obj2[8]!=null){
                        String fn = obj2[8].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        FatherUniqueNo_text.setText(fn);
                        }
                        else
                        {
                            FatherUniqueNo_text.setText("");
                        }
                    }
                    else
                    {
                        FatherUniqueNo_text.setText("");
                    }

                    if(obj2[9]!=null){
                        String Motherbloodgrp= obj2[9].toString();
//                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
//                            String x= ChildrenBloodGrpListAll.get(i).toString();
//                            if(x.equals(Motherbloodgrp)){
//                                MotherBloodGroup_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                        if(Motherbloodgrp!=null&&Motherbloodgrp.trim().length()>0)
                        {
                        MotherBloodGroup_combo.setText(Motherbloodgrp);
                        }
                        else
                        {
                            MotherBloodGroup_combo.setText("");
                        }
                    }
                    else
                    {
                        MotherBloodGroup_combo.setText("");
                    }

                    if(obj2[10]!=null){
                        String fn = obj2[10].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherPhoneNo_text.setText(fn);
                        }
                        else
                        {
                            MotherPhoneNo_text.setText("");
                        }
                    }
                    else
                    {
                        MotherPhoneNo_text.setText("");
                    }
                    if(obj2[11]!=null){
                        String fn = obj2[11].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherEmailid_text.setText(fn);
                        }
                        else
                        {
                            MotherEmailid_text.setText("");
                        }
                    }
                    else
                    {
                        MotherEmailid_text.setText("");
                    }
                        if(obj2[12]!=null){
                        String MotherIdProof= obj2[12].toString();
//                        for(int i=0;i<IDProofTypeList.size();i++){
//                            String x= IDProofTypeList.get(i).toString();
//                            if(x.equals(MotherIdProof)){
//                                MotherIdProof_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                       if(MotherIdProof!=null&&MotherIdProof.trim().length()>0)
                       {
                        MotherIdProof_combo.setText(MotherIdProof);
                       }
                       else
                       {
                           MotherIdProof_combo.setText("");
                       }
                       }
                        else
                       {
                           MotherIdProof_combo.setText("");
                       }
                      

                    if(obj2[13]!=null){
                        String fn = obj2[13].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherUniqueNo_text.setText(fn);
                        }
                        else
                        {
                            MotherUniqueNo_text.setText("");
                        }
                    }
                    else
                    {
                        MotherUniqueNo_text.setText("");
                    }



                }
                else
                {
                    fatherDob_text.setText("");
                    MotherName_text.setText("");
                    MotherDOB_text.setText("");
                    FatherBloodGroup_combo.setText("");
                    FatherPhoneNo_Text.setText("");
                    FatherEmailId_Text.setText("");
                    FatherIdProof_combo.setText("");
                    FatherUniqueNo_text.setText("");
                    MotherBloodGroup_combo.setText("");
                    MotherPhoneNo_text.setText("");
                    MotherEmailid_text.setText("");
                    MotherIdProof_combo.setText("");
                    MotherUniqueNo_text.setText("");
                }
               // ***********************  PARENTS INFO  ENDS **********************************************************
                
                
                
                
              // ***********************  CHILDREN INFO    **********************************************************  // 
               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT DEPENDENTDETAILS FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id); 
            
               ChildrenClassList = new ArrayList<ChildrenClass>();
               
               if(obj3!=null){
                   
                   if(obj3[0]!=null){
                        String DependentDetailstr=obj3[0].toString();
                        String Classlevelstr[] = DependentDetailstr.split("#");
                        for(int i=0;i<Classlevelstr.length;i++){
                            String ChildLevel=Classlevelstr[i].toString();
                            String ChildArr[]=ChildLevel.split("%");
                            ChildrenClass cb = new ChildrenClass();
                            
                            if(ChildArr.length>0){
                                String name=ChildArr[0].toString();
                                String Gender=ChildArr[1].toString();
                                String BloodGroup=ChildArr[2].toString();
                                String IdProof=ChildArr[3].toString();
                                String PhoneNo=ChildArr[4].toString();
                                String EmailId=ChildArr[5].toString();
                                String UniqueNo=ChildArr[6].toString();
                                String Cdob=ChildArr[7].toString(); 
                                ChildrenName_text.setVisible(false);
                                ChildrenBlood_Combo.setVisible(false);
                                ChildrenIDProof_combo.setVisible(false);
                                ChildrenPhone_text.setVisible(false);
                                ChildrenEmailId_text.setVisible(false);
                                ChildrenUniqueNo_text.setVisible(false);
                                ChildrenDOB_text.setVisible(false);                                                                
                                 String date=null;
                                 Date Dob=null;
                                try
                                {
                                Dob=(Date)formatter.parse(Cdob);
                                date=formatter.format(Dob);
                                }catch(ParseException ex)
                                        {
                                            ex.printStackTrace();
                                        }
                                catch(Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                if(name!=null && name.trim().length()>0)
                                {
                                    ChildrenName_text.setText(name);
                                }
                                else 
                                {
                                    ChildrenName_text.setText("");
                                }
                                if(Gender!=null && Gender.trim().length()>0)
                                {
                                    ChildrenFemale_radio.setVisible(true);
                                    ChildrenMale_radio.setVisible(true);
                                    if (Gender.equals("Female")) {
                                        ChildrenFemale_radio.setSelected(true);
                                        ChildrenMale_radio.setVisible(false);
                                    }
                                   else
                                    if(Gender.equals("Male"))
                                    {
                                        ChildrenMale_radio.setSelected(true);
                                        ChildrenFemale_radio.setVisible(false);
                                    }
                                   
                                }
                                else
                                {
                                    ChildrenFemale_radio.setVisible(false);
                                    ChildrenMale_radio.setVisible(false);
                                    ChildrenMale_radio.setText("");
                                }
                                if(BloodGroup!=null && BloodGroup.trim().length()>0)
                                {
                                    
                                    ChildrenBlood_Combo.setText(BloodGroup);
                                }
                                else
                                {
                                    ChildrenBlood_Combo.setText("");
                                }
                                if(IdProof!=null && IdProof.trim().length()>0)
                                {
                                    ChildrenIDProof_combo.setText(IdProof);
                                }
                                else
                                {
                                    ChildrenIDProof_combo.setText("");
                                }
                                if(PhoneNo!=null && PhoneNo.trim().length()>0)
                                {
                                    ChildrenPhone_text.setText(PhoneNo);
                                }
                                else
                                {
                                    ChildrenPhone_text.setText("");
                                }
                                if(EmailId!=null && EmailId.trim().length()>0)
                                {
                                    ChildrenEmailId_text.setText(EmailId);
                                }
                                else
                                {
                                    ChildrenEmailId_text.setText("");
                                }
                                if(UniqueNo!=null && UniqueNo.trim().length()>0)
                                {
                                    ChildrenUniqueNo_text.setText(UniqueNo);
                                }
                                else
                                {
                                    ChildrenUniqueNo_text.setText("");
                                }
                                if(date!=null && date.trim().length()>0)
                                {
                                    ChildrenDOB_text.setText(date);
                                }
                                else
                                {
                                    ChildrenDOB_text.setText("");
                                }
                                cb.setName(name);
                                cb.setGender(Gender);
                                cb.setBloodGrp(BloodGroup);
                                cb.setIDProof(IdProof);
                                cb.setPhoneNo(PhoneNo);
                                cb.setEmailID(EmailId);
                                cb.setUniqueNo(UniqueNo);
                                cb.setDateOfBirth(Dob);
                                
                                SD_listAll.add(name);
                                ChildrenClassList.add(cb);
                            }
                            else
                            {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText("");
                            }
                        }                                                                                            
                        MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                        
                   }
                   else
                   {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText(""); 
                   }
                }
              else
               {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText("");
               }
               
               // *********************************** CHILDREN DETAILS ENDS HERE ***************************************************
               
               
               
               // *********************************** ADDRESS DETAILS STARTS HERE ***************************************************
              
               
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               if(obj4!=null){
                   ResPost_text.setVisible(false);
                   if(obj4[0]!=null){
                       String ResStrFull=obj4[0].toString();
                       String resArr[] = ResStrFull.split("#");
                       if(resArr.length>0){
                           String Rline1=resArr[0].toString();
                           String Rline2=resArr[1].toString();
                           String Rpost=resArr[2].toString();
                           String Rcity=resArr[3].toString();
                           String RState=resArr[4].toString();
                           String RCountry=resArr[5].toString();
                           String PinNo=resArr[6].toString();
                           if((Rline1!=null && Rline1.trim().length()>0) || (Rline2!=null && Rline2.trim().length()>0))
                           {
                           Rline1Res_text.setText(Rline1);
                           RLine2Res_text.setText(Rline2);
                           }
                           else
                           {
                               Rline1Res_text.setText("");
                               RLine2Res_text.setText("");
                           }
                           if(Rpost!=null && Rpost.trim().length()>0)
                           {
                           jLabel85.setText(Rpost);
                           }
                           else
                           {
                               ResPost_text.setText("");
                           }
                           if(PinNo!=null && PinNo.trim().length()>0)
                           {
                           ResPinNo_text.setText(PinNo);
                           }
                           else
                           {
                               ResPinNo_text.setText("");
                           }
                           if(Rcity!=null && Rcity.trim().length()>0)
                           {
                           ResCity_combo.setText(Rcity);
                           }
                           else
                           {
                               ResCity_combo.setText("");
                           }
                           
                           if(RState!=null && RState.trim().length()>0)
                           {
                           ResState_Combo.setText(RState);
                           }
                           else
                           {
                               ResState_Combo.setText("");
                           }
                  
//                           for(int i=0;i<StateList.size();i++){
//                               String c = StateList.get(i).toString();
//                               if(c.equals(RState)){
//                                   ResState_Combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   ResState_Combo.setSelectedIndex(-1);
//                               }
//                           }
//                           for(int i=0;i<CountryList.size();i++){
//                               String c = CountryList.get(i).toString();
//                               if(c.equals(RCountry)){
//                                   ResCountry_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   ResCountry_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(RCountry!=null && RCountry.trim().length()>0)
                           {
                           ResCountry_combo.setText(RCountry);
                           }
                           else
                           {
                               ResCountry_combo.setText("");
                           }
                           
                           
                       }
                       else
                       {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");
                       }
                   }
                   else
                   {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");  
                   }
                   
                   if(obj4[1]!=null){
                       String ResStrFull=obj4[1].toString();
                       String OffArr[] = ResStrFull.split("#");
                       if(OffArr.length>0){
                           OffPost_Text.setVisible(false);
                           String Oline1=OffArr[0].toString();
                           String Oline2=OffArr[1].toString();
                           String Opost=OffArr[2].toString();
                           String Ocity=OffArr[3].toString();
                           String OState=OffArr[4].toString();
                           String OCountry=OffArr[5].toString();
                           String OPinNo=OffArr[6].toString();
                           if((Oline1!=null && Oline1.trim().length()>0) || (Oline2!=null && Oline2.trim().length()>0))
                           {
                           offLine1_text.setText(Oline1);
                           Off_line2_text.setText(Oline2);
                           }
                           else
                           {
                               offLine1_text.setText("");
                               Off_line2_text.setText("");
                           }
                           if(Opost!=null && Opost.trim().length()>0)
                           {
                           OffPost_Text1.setText(Opost);
                           }
                           else
                           {
                               OffPost_Text1.setText("");
                           }
                           if(OPinNo!=null && OPinNo.trim().length()>0)
                           {
                           OffPinNo_text.setText(OPinNo);
                           }
                           else
                           {
                               OffPinNo_text.setText("");
                           }
                           if(Ocity!=null & Ocity.trim().length()>0)
                           {
                           offCity_combo.setText(Ocity);
                           }
                           else
                           {
                               offCity_combo.setText("");
                           }
//                           for(int i=0;i<CityList.size();i++){
//                               String c = CityList.get(i).toString();
//                               if(c.equals(Ocity)){
//                                   offCity_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   offCity_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(OState!=null && OState.trim().length()>0)
                           {
                           OffState_combo.setText(OState);
                           }
                           else
                           {
                               OffState_combo.setText("");
                           }
//                           for(int i=0;i<StateList.size();i++){
//                               String c = StateList.get(i).toString();
//                               if(c.equals(OState)){
//                                   OffState_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   OffState_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(OCountry!=null && OCountry.trim().length()>0)
                           {
                           Off_Country_combo.setText(OCountry);
                           }
                           else
                           {
                               Off_Country_combo.setText("");
                           }
//                           for(int i=0;i<CountryList.size();i++){
//                               String c = CountryList.get(i).toString();
//                               if(c.equals(OCountry)){
//                                   Off_Country_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   Off_Country_combo.setSelectedIndex(-1);
//                               }
//                           }
                           
                           
                       }
                       else
                       {
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");
                       }
                   }
                   else
                   {
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");   
                   }
                   
                   if(obj4[2]!=null){
                       String m = obj4[2].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       MobileNo_text.setText(m);
                       }
                       else
                       {
                           MobileNo_text.setText("");
                       }
                   }
                   else
                   {
                       MobileNo_text.setText("");
                   }
                   if(obj4[3]!=null){
                       String m = obj4[3].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Residence_no_text.setText(m);
                       }
                       else
                       {
                           Residence_no_text.setText("");
                       }
                   }
                   else
                   {
                       Residence_no_text.setText("");
                   }
                   if(obj4[4]!=null){
                       String m = obj4[4].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       OfficeNo_text.setText(m);
                       }
                       else
                       {
                           OfficeNo_text.setText("");
                       }
                   }
                   else
                   {
                       OfficeNo_text.setText("");
                   }
                   if(obj4[5]!=null){
                       Office_Radio.setVisible(true);
                       residence_Radio.setVisible(true);
                       String m = obj4[5].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       if(m.equals("Residence")){
                           residence_Radio.setSelected(true);
                           Office_Radio.setVisible(false);
                       }
                       else{
                           Office_Radio.setSelected(true);
                           residence_Radio.setVisible(false);
                       }
                       }
                       else
                       {
                           residence_Radio.setText("");
                       }
                   }
                   else 
                   {
                       residence_Radio.setText("");
                   }
                   if(obj4[6]!=null){
                       String m = obj4[6].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Email_Text.setText(m);
                       }
                       else
                       {
                           Email_Text.setText("");
                       }
                   }
                   else
                   {
                       Email_Text.setText("");
                   }
                   if(obj4[7]!=null){
                       String m = obj4[7].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Twitter_text.setText(m);
                       }
                       else
                       {
                           Twitter_text.setText("");
                       }
                   }
                   else
                   {
                       Twitter_text.setText("");
                   }
               }
               else
               {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");  
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");  
                           MobileNo_text.setText("");
                           Residence_no_text.setText("");
                           OfficeNo_text.setText("");
                           Email_Text.setText("");
                           Twitter_text.setText("");
                           residence_Radio.setText("");
               }
               
             // ************************************OTHER INFO PANEL ***********************************************************************************************  
              
               Object[] obj5 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MOBILETYPE,PROFFESION,CLUBACTIVITY,PLAYSPORTS,SpecialTalent,FACILITIES,RecreationalActivities FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
                SelectedActivityList = new ArrayList<String>();
     SelectedFacilityList = new ArrayList<String>();
     RecreationalActivityList = new ArrayList<RecreationalActivitiesListClass>();
               
               if(obj5!=null){
                   if(obj5[0]!=null){
                       String Mobtype=obj5[0].toString();
                       activities_combo.setVisible(false);
                       if(Mobtype!=null && Mobtype.trim().length()>0)
                       {
                       MobilePhoneUse_Combo.setText(Mobtype);
                       }
                       else
                       {
                           MobilePhoneUse_Combo.setText("");
                       }
//                       for(int i=0;i<MobileOsList.size();i++){
//                           String x=MobileOsList.get(i).toString();
//                           if(x.equals(Mobtype)){
//                               MobilePhoneUse_Combo.setSelectedIndex(i);
//                               break;
//                           }
//                           else{
//                               MobilePhoneUse_Combo.setSelectedIndex(-1);
//                           }
//                       }
                   }
                   else
                   {
                       MobilePhoneUse_Combo.setText("");
                   }

//                   else{
//                       MobilePhoneUse_Combo.setSelectedIndex(-1);
//                   }
                   if(obj5[1]!=null){
                       String Profession=obj5[1].toString();
                       if(Profession!=null && Profession.trim().length()>0)
                       {
                      Proffesion_Combo.setText(Profession);
                       }
                       else
                       {
                           Proffesion_Combo.setText("");
                       }
//                       for(int i=0;i<ProfessionList.size();i++){
//                           String x=ProfessionList.get(i).toString();
//                           if(x.equals(Mobtype)){
//                               Proffesion_Combo.setSelectedIndex(i);
//                               break;
//                           }
//                           else{
//                               Proffesion_Combo.setSelectedIndex(-1);
//                           }
//                       }
                   }
                   else
                   {
                       Proffesion_Combo.setText("");
                   }
//                   else{
//                       Proffesion_Combo.setSelectedIndex(-1);
//                   }
                   if(obj5[2]!=null){
                       String Act = obj5[2].toString();
                       String ActArr[]=Act.split("#");
                       for(int i=0;i<ActArr.length;i++){
                           String t = ActArr[i].toString();
                           SelectedActivityList.add(t);
                       }
                       Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
                   }
                   if(obj5[3]!=null){
                       String t=obj5[3].toString();
                       if(t!=null && t.trim().length()>0)
                       {
                       PlaySport_Text.setText(t);
                       }
                       else
                       {
                           PlaySport_Text.setText("");
                       }
                   }
                   else
                   {
                       PlaySport_Text.setText("");
                   }
                   if(obj5[4]!=null){
                       String t=obj5[4].toString();
                       if(t!=null && t.trim().length()>0)
                       {
                       SpecialTalent_text.setText(t);
                       }
                       else
                       {
                           SpecialTalent_text.setText("");
                       }
                   }
                   else
                   {
                       SpecialTalent_text.setText("");
                   }
                   if(obj5[5]!=null){
                       String Facility = obj5[5].toString();
                       String FacArr []=Facility.split("#");
                       for(int i=0;i<FacArr.length;i++){
                           String t=FacArr[i].toString();
                           SelectedFacilityList.add(t);
                       }
                       Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                   }
                   if(obj5[6]!=null){
                       String RaStr =obj5[6].toString();
                       String Recrea_Arr[] = RaStr.split("#");
                       for(int i=0;i<Recrea_Arr.length;i++){
                           String x =Recrea_Arr[i].toString();
                           String ReacAct[] = x.split("%");
                           RecreationalActivitiesListClass rclass= new RecreationalActivitiesListClass();
                           if(ReacAct.length>0){
                               String slno=ReacAct[0].toString();
                               String activity=ReacAct[1].toString();
                               String Level=ReacAct[2].toString();
                               rclass.setSlNo(slno);
                               rclass.setActivityName(activity);
                               rclass.setActivityLevel(Level);
                               RecreationalActivityList.add(rclass);
                           }
                       }
                       MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true);
                   }
               }
               else
               {
                MobilePhoneUse_Combo.setText(""); 
                Proffesion_Combo.setText("");
                PlaySport_Text.setText("");
                SpecialTalent_text.setText("");
               }
              // ******************************** ENDD ******************************************************************************  
               
               
              // ************************************ OTHER CLUB INFO ******************************************************************* 
               
               
               Object[] obj6 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT OTHERCLUB FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING })).find(Id); 
               
               if(obj6!=null){
                   OtherClubClassListAll_Class=new ArrayList<OtherClubClass>();
                   
                   if(obj6[0]!=null){
                       
                       String otherclubfull = obj6[0].toString();
                       other_clubs_hidden_panel.setVisible(false);
                       memberSince_text.setVisible(false);
                       OtherClubMemno_text.setVisible(false);
                       jTextField34.setVisible(false);
                       String Str_Arr[]=otherclubfull.split("#");
                       for(int i=0;i<Str_Arr.length;i++){
                           String clubInfo = Str_Arr[i].toString();
                           String Clubinfo_arr[] = clubInfo.split("%");
                           if(Clubinfo_arr.length>0){
                               String Clubname=Clubinfo_arr[0].toString();
                               
                               String memno=Clubinfo_arr[1].toString();
                               
                               
                               String TypeofMember=Clubinfo_arr[2].toString();
                               
                               
                               String City=Clubinfo_arr[3].toString();
                               String MemberSince=Clubinfo_arr[4].toString();
                               Date memSinceDate = null;
                               if(MemberSince!=null && MemberSince.trim().length()>0){
                                   memSinceDate = formatter.parse(MemberSince);
                               }
                               OtherClubClass clb=new OtherClubClass();
                               clb.setClubName(Clubname);
                               clb.setMemberno(memno);
                               clb.setTypeofMember(TypeofMember);
                               clb.setCity(City);
                               clb.setMemberSince(memSinceDate);
                               OtherClubClassListAll_Class.add(clb);
                               
                           }
                           
                       }
                        MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true);
                   }
                           
                   
               }
                           
               
               Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT SD_SPORTS_DETAIL   FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id);
               SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();
               if(obj7!=null){
                   if(obj7[0]!=null){
                       String SD_Sportfull = obj7[0].toString();
                       if(SD_Sportfull.trim().length()>0){
                       String Str_Arr[]=SD_Sportfull.split("#");
                       for(int i=0;i<Str_Arr.length;i++){
                           String SD_SportsInfo[] = Str_Arr[i].split("%");
                           if(SD_SportsInfo.length>0){
                               String dname = SD_SportsInfo[0].toString();
                               String Sname = SD_SportsInfo[1].toString();
                               SD_Sports_Class cs = new SD_Sports_Class();
                        
                               cs.setSD_Name(dname);
                               cs.setSD_Sports(Sname);
                               
                               SD_Sports_ClassListAll.add(cs);
                               
                           }
                       }
                     MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
                  
                   }
                     
                   }
                   
               }
               SD_ClubActivity_ClassListAll=new ArrayList<SD_ClubActivity_Class>();
                SD_RActivity_ClassListAll = new ArrayList<SD_RActivity_Class>();
                SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
                SD_Talent_ClassListAll=new ArrayList<SD_Talent_Class>();
               Object[] obj8 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT  SD_RACTIVITY,SD_FACILITY ,SD_TALENT,SD_CLUBACTIVITY FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{ Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE,Datas.SERIALIZABLE})).find(Id);
               
                if(obj8!=null){ 
                    if(obj8[0]!=null){
                        String x = obj8[0].toString();
                        if(x.trim().length()>0)
                        {
                        String SD_RacArr [] = x.split("#");
                        for(int i=0;i<SD_RacArr.length;i++){
                            String x1 = SD_RacArr[i].toString();
                            String SD_Arr[]=x1.split("%");
                            String SD_name = SD_Arr[0].toString();
                            String SD_Sports=SD_Arr[1].toString();
                            String SD_Level=SD_Arr[2].toString();
                            SD_RActivity_Class sd=new SD_RActivity_Class();
                            sd.setSD_Name(SD_name);
                            sd.setSD_Sports(SD_Sports);
                            sd.setSD_level(SD_Level);
                            SD_RActivity_ClassListAll.add(sd);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true);
                        }   
                        }

                         
                    }
                    
                    if(obj8[1]!=null){
                        String x = obj8[1].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            
                            String x1=StrArr[i].toString();
                            if(x1.trim().length()>0)
                            {
                            String SD_facArr[] = x1.split("%");
                            String SD_Name = SD_facArr[0].toString();
                            String Facility = SD_facArr[1].toString();
                            SD_Facility_Class sf = new SD_Facility_Class();
                            sf.setSD_Name(SD_Name);
                            sf.setSD_Facility(Facility);
                            SD_Facility_ClassListAll.add(sf);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                        }
                        }
                        
                        
                    }
                    
                    if(obj8[2]!=null){
                        String x = obj8[2].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            String x1=StrArr[i].toString();
                            if(x1.trim().length()>0)
                            {
                            String SD_TalArr[] = x1.split("%");
                            String SD_Name = SD_TalArr[0].toString();
                            String talent = SD_TalArr[1].toString();
                            SD_Talent_Class sf = new SD_Talent_Class();
                            sf.setSD_Name(SD_Name);
                            sf.setSD_Talent(talent);
                            SD_Talent_ClassListAll.add(sf);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);
                        }
                        }
                        
                        
                    }
                      if(obj8[3]!=null){
                        
                          String x = obj8[3].toString();
                          String StrArr[] = x.split("#");
                          for(int i=0;i<StrArr.length;i++){
                              String x1=StrArr[i].toString();
                              String SD_ActivityArr[] = x1.split("%");
                              if(SD_ActivityArr.length>1){
                                String SD_Name = SD_ActivityArr[0].toString();
                                String SD_Activity = SD_ActivityArr[1].toString();
                                MemberViewDetails.SD_ClubActivity_Class sd = new MemberViewDetails.SD_ClubActivity_Class();
                                sd.setSD_Name(SD_Name);
                                sd.setSD_Activity(SD_Activity);
                                SD_ClubActivity_ClassListAll.add(sd);
                                MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);
                              }
                          }  
                            
                        
                    }
                    
                  }
                   
                   
                   
               
               // ***************** RECREATIONL ACTIVITES FOR SPOUSE/DEPENDENTS *****************************************
               
               
               
               
               
                SD_List_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                sports_SD_Combo.setModel(SD_List_ComboBoxValModel);
                SD_Ractivity_ComboBoxValModel = new ComboBoxValModel(SD_listAll);
                SD_RActivity_Combo.setModel(SD_Ractivity_ComboBoxValModel);
                SD_FacilitySpouse_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_FacilitySpouce_Combo.setModel(SD_FacilitySpouse_ComboBoxValModel);
                SD_Talent_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_talent_Combo.setModel(SD_Talent_ComboBoxValModel);
                
                
          }
        
         else
          {
              IDproofDoc_combo.setVisible(false);
             TypeofMember_combo.setVisible(false);
          }
          
       
       
        
      }
      catch(BasicException ex){
          Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
      }
      catch(ParseException ex){
          Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
      }
      next_data.setEnabled(false);
    }
    
    
    public void getAlreadyLoadedMemberInfo1(String Id) {
      try{   
       
          SD_listAll = new ArrayList<String>();
          
          // ***********************  MAIN PAGE INFO **********************************************************
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MEMBERNAME,DATEOFBIRTH,IDPROOF,IDUNIQUENO,MEMBERTYPE,MEMBERSINCE,SPOUCENAME,DATEOFMARRIAGE,GENDER FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.STRING,Datas.STRING , Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP , Datas.STRING })).find(Id); 
         
        
         
         //kymmember_arv_id="db9cd16e-14c6-46de-b165-2faed7ef17a9";
          if(obj1!=null){
             // prev_Data.setEnabled(true);
              if(obj1[0]!=null){
                  mname_text.setText(obj1[0].toString());
              }
              
              if(obj1[1]!=null){
                  Date dob = (Date) obj1[1];
                  String X = formatter.format(dob);
                  DOB_text.setText(X);
              }                                        
             if(obj1[2]!=null){
                 String idProofDoc=obj1[2].toString();
                 if(idProofDoc!=null && idProofDoc.trim().length()>0)
                 {
                  IDproofDoc_combo.setText(idProofDoc);
//                  for(int i=0;i<IDProofTypeList.size();i++){
//                      String x=IDProofTypeList.get(i).toString();
//                      if(x.equals(idproof)){
//                          IDproofDoc_combo.setSelectedIndex(i);
//                          break;
//                      }
//                  }
                 }
                 else
                 {
                     IDproofDoc_combo.setText(null);
                 }
              }
             else
             {
                 IDproofDoc_combo.setText(null);
             }
              
              if(obj1[3]!=null){
                  idDocUniqueNo_text.setText(obj1[3].toString());
              }
              
              if(obj1[4]!=null){
                  String memtype=obj1[4].toString();
                  TypeofMember_combo.setText(memtype);
//                  for(int i=0;i<MemberShipTypeList.size();i++){
//                      String x=MemberShipTypeList.get(i).toString();
//                      if(x.equals(memtype)){
//                          TypeofMember_combo.setSelectedIndex(i);
//                          break;
//                      }
//                  }
              }
//              else{
//                  TypeofMember_combo.setSelectedIndex(-1);
//              }
              
              
              if(obj1[5]!=null){
                  memberSince_text.setVisible(false);
                  Date memsince = (Date) obj1[5];
                  String X = formatter.format(memsince);
                  
                  memberSince_text1.setText(X);
              }
              else
              {
                  memberSince_text1.setText("");
              }
              if(obj1[6]!=null){
                  String spouse_name=obj1[6].toString();
                  if(spouse_name.trim().length()>0)
                  {
                  SpouseName_text.setText(spouse_name);
                  SD_listAll.add(obj1[6].toString());
                  }
                  else{
                      SpouseName_text.setText("");
                  }
              }
              else
              {
                  SpouseName_text.setText("");
              }
              if(obj1[7]!=null){
                  Date dom = (Date) obj1[7];
                  String X = formatter.format(dom);
                  if(X!=null && X.trim().length()>0)
                  {
                  DateOfMarriage_text.setText(X);
                  }
                  else
                  {
                      DateOfMarriage_text.setText("");
                  }
              }
                else
              {
                  DateOfMarriage_text.setText("");
              }
              if(obj1[8]!=null){
                 
                 String g=obj1[8].toString().toLowerCase();
                 if(g.equals("male")){
                     male_radio.setVisible(true);
                     male_radio.setSelected(true);
                     female_radio.setVisible(false);
                 }
                 else{
                     female_radio.setVisible(true);
                     female_radio.setSelected(true);
                     male_radio.setVisible(false);
                 }
              }
              
              // ***********************  MAIN PAGE INFO ENDS********************************************************** 
              
              
              
              // ***********************  PARENTS INFO  **********************************************************
              
              Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT FATHERNAME,FATHERDOB,MOTHERNAME,MOTHERDOB,FATHERBG,FATHERPHONENO,FATHEREMAILID,FATHERIDPROOF,FATHERUNIQUENO,MOTHERBG,MOTHERPHONENO,MOTHEREMAILID,MOTHERIDPROOF,MOTHERUNIQUENO FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(
                   new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.TIMESTAMP,Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING })).find(Id); 
          
                if(obj2!=null){

                    if(obj2[0]!=null){
                        String fn = obj2[0].toString();
                        FatherName_text.setText(fn);
                        jTextField1.setText(fn);
                    }
                    if(obj2[1]!=null){
                         Date dom = (Date) obj2[1];
                          String X = formatter.format(dom);
                          if(X!=null && X.trim().length()>0)
                          {
                          fatherDob_text.setText(X);
                          }
                          else
                          {
                              fatherDob_text.setText("");
                          }
                    }
                    else
                    {
                        fatherDob_text.setText("");
                    }
                    if(obj2[2]!=null){
                        String fn = obj2[2].toString();
                        if(fn!=null&& fn.trim().length()>0)
                        {
                        MotherName_text.setText(fn);
                        }
                        else
                        {
                            MotherName_text.setText("");
                        }
                    }
                    else
                    {
                        MotherName_text.setText("");
                    }
                    if(obj2[3]!=null){
                         Date dom = (Date) obj2[3];
                          String X = formatter.format(dom);
                          if(X!=null&&X.trim().length()>0)
                          {
                          MotherDOB_text.setText(X);
                          }
                          else
                              {
                                  MotherDOB_text.setText("");
                              }
                    }
                    else{
                    MotherDOB_text.setText("");
                    }
                    if(obj2[4]!=null){
                        String Fatherbloodgrp= obj2[4].toString();
//                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
//                            String x= ChildrenBloodGrpListAll.get(i).toString();
//                            if(x.equals(Fatherbloodgrp)){
//                                FatherBloodGroup_combo.setSelectedIndex(i);
//                                break;
//                            }
                     // }
                        if(Fatherbloodgrp!=null && Fatherbloodgrp.trim().length()>0)
                        {
                         FatherBloodGroup_combo.setText(Fatherbloodgrp);
                        }
                        else 
                        {
                            FatherBloodGroup_combo.setText("");
                        }
                    }
                    else 
                    {
                        FatherBloodGroup_combo.setText("");
                    }

                    if(obj2[5]!=null){
                        String fn = obj2[5].toString();
                        if(fn.trim().length()>0)
                        {
                        FatherPhoneNo_Text.setText(fn);
                        }
                         else
                    {
                        FatherPhoneNo_Text.setText("");
                    }
                    }
                    else
                    {
                        FatherPhoneNo_Text.setText("");
                    }
                   
                    if(obj2[6]!=null){
                        String fn = obj2[6].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        FatherEmailId_Text.setText(fn);
                        }
                        else
                        {
                            FatherEmailId_Text.setText("");
                        }
                    }
                    else
                    {
                        FatherEmailId_Text.setText("");
                    }
                    if(obj2[7]!=null){
                        String FatherIdProof= obj2[7].toString();
//                        for(int i=0;i<IDProofTypeList.size();i++){
//                            String x= IDProofTypeList.get(i).toString();
//                            if(x.equals(FatherIdProof)){
//                                FatherIdProof_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                        if(FatherIdProof!=null&&FatherIdProof.trim().length()>0)
                        {
                        FatherIdProof_combo.setText(FatherIdProof);
                        }
                        else
                        {
                            FatherIdProof_combo.setText("");
                        }
                    }
                    else
                    {
                        FatherIdProof_combo.setText("");
                    }

                    if(obj2[8]!=null){
                        String fn = obj2[8].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        FatherUniqueNo_text.setText(fn);
                        }
                        else
                        {
                            FatherUniqueNo_text.setText("");
                        }
                    }
                    else
                    {
                        FatherUniqueNo_text.setText("");
                    }

                    if(obj2[9]!=null){
                        String Motherbloodgrp= obj2[9].toString();
//                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
//                            String x= ChildrenBloodGrpListAll.get(i).toString();
//                            if(x.equals(Motherbloodgrp)){
//                                MotherBloodGroup_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                        if(Motherbloodgrp!=null&&Motherbloodgrp.trim().length()>0)
                        {
                        MotherBloodGroup_combo.setText(Motherbloodgrp);
                        }
                        else
                        {
                            MotherBloodGroup_combo.setText("");
                        }
                    }
                    else
                    {
                        MotherBloodGroup_combo.setText("");
                    }

                    if(obj2[10]!=null){
                        String fn = obj2[10].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherPhoneNo_text.setText(fn);
                        }
                        else
                        {
                            MotherPhoneNo_text.setText("");
                        }
                    }
                    else
                    {
                        MotherPhoneNo_text.setText("");
                    }
                    if(obj2[11]!=null){
                        String fn = obj2[11].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherEmailid_text.setText(fn);
                        }
                        else
                        {
                            MotherEmailid_text.setText("");
                        }
                    }
                    else
                    {
                        MotherEmailid_text.setText("");
                    }
                        if(obj2[12]!=null){
                        String MotherIdProof= obj2[12].toString();
//                        for(int i=0;i<IDProofTypeList.size();i++){
//                            String x= IDProofTypeList.get(i).toString();
//                            if(x.equals(MotherIdProof)){
//                                MotherIdProof_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                       if(MotherIdProof!=null&&MotherIdProof.trim().length()>0)
                       {
                        MotherIdProof_combo.setText(MotherIdProof);
                       }
                       else
                       {
                           MotherIdProof_combo.setText("");
                       }
                       }
                        else
                       {
                           MotherIdProof_combo.setText("");
                       }
                      

                    if(obj2[13]!=null){
                        String fn = obj2[13].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherUniqueNo_text.setText(fn);
                        }
                        else
                        {
                            MotherUniqueNo_text.setText("");
                        }
                    }
                    else
                    {
                        MotherUniqueNo_text.setText("");
                    }



                }
                else
                {
                    fatherDob_text.setText("");
                    MotherName_text.setText("");
                    MotherDOB_text.setText("");
                    FatherBloodGroup_combo.setText("");
                    FatherPhoneNo_Text.setText("");
                    FatherEmailId_Text.setText("");
                    FatherIdProof_combo.setText("");
                    FatherUniqueNo_text.setText("");
                    MotherBloodGroup_combo.setText("");
                    MotherPhoneNo_text.setText("");
                    MotherEmailid_text.setText("");
                    MotherIdProof_combo.setText("");
                    MotherUniqueNo_text.setText("");
                }
               // ***********************  PARENTS INFO  ENDS **********************************************************
                
                
                
                
              // ***********************  CHILDREN INFO    **********************************************************  // 
               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT DEPENDENTDETAILS FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id); 
            
               ChildrenClassList = new ArrayList<ChildrenClass>();
               
               if(obj3!=null){
                   String name="";
                   String Gender="";
                   String BloodGroup="";
                   String IdProof="";
                   String PhoneNo="";
                   String EmailId="";
                     String UniqueNo="";
                     String Cdob="";
                   if(obj3[0]!=null){
                        String DependentDetailstr=obj3[0].toString();
                        String Classlevelstr[] = DependentDetailstr.split("#");
                        for(int i=0;i<Classlevelstr.length;i++){
                            String ChildLevel=Classlevelstr[i].toString();
                            String ChildArr[]=ChildLevel.split("%");
                            ChildrenClass cb = new ChildrenClass();
                            
                            if(ChildArr.length>0){
                                for(int j=0;j<ChildArr.length;j++)
                                {
                                    if(j==0)
                                    {
                                name=ChildArr[0].toString();
                                    }
                                    if(j==1)
                                    {
                                 Gender=ChildArr[1].toString();
                                    }
                                    if(j==2)
                                    {
                                 BloodGroup=ChildArr[2].toString();
                                    }
                                    if(j==3)
                                    {
                               IdProof=ChildArr[3].toString();
                                    }
                                    if(j==4)
                                    {
                                PhoneNo=ChildArr[4].toString();
                                    }
                                    if(j==5)
                                    {
                                EmailId=ChildArr[5].toString();
                                    }
                                    if(j==6)
                                    {
                             UniqueNo=ChildArr[6].toString();
                                    }
                                    if(j==7)
                                    {
                                 Cdob=ChildArr[7].toString(); 
                                    }
                                }
                                ChildrenName_text.setVisible(false);
                                ChildrenBlood_Combo.setVisible(false);
                                ChildrenIDProof_combo.setVisible(false);
                                ChildrenPhone_text.setVisible(false);
                                ChildrenEmailId_text.setVisible(false);
                                ChildrenUniqueNo_text.setVisible(false);
                                ChildrenDOB_text.setVisible(false);                                                                
                                 String date=null;
                                 Date Dob=null;
                                try
                                {
                                    if(Cdob!=null && Cdob.trim().length()>0)
                                    {
                                Dob=(Date)formatter.parse(Cdob);
                                date=formatter.format(Dob);
                                    }
                                }catch(ParseException ex)
                                        {
                                            ex.printStackTrace();
                                        }
                                catch(Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                if(name!=null && name.trim().length()>0)
                                {
                                    ChildrenName_text.setText(name);
                                }
                                else 
                                {
                                    ChildrenName_text.setText("");
                                }
                                if(Gender!=null && Gender.trim().length()>0)
                                {
                                    ChildrenFemale_radio.setVisible(true);
                                    ChildrenMale_radio.setVisible(true);
                                    if (Gender.equals("Female")) {
                                        ChildrenFemale_radio.setSelected(true);
                                        ChildrenMale_radio.setVisible(false);
                                    }
                                   else
                                    if(Gender.equals("Male"))
                                    {
                                        ChildrenMale_radio.setSelected(true);
                                        ChildrenFemale_radio.setVisible(false);
                                    }
                                   
                                }
                                else
                                {
                                    ChildrenFemale_radio.setVisible(false);
                                    ChildrenMale_radio.setVisible(false);
                                    ChildrenMale_radio.setText("");
                                }
                                if(BloodGroup!=null && BloodGroup.trim().length()>0)
                                {
                                    
                                    ChildrenBlood_Combo.setText(BloodGroup);
                                }
                                else
                                {
                                    ChildrenBlood_Combo.setText("");
                                }
                                if(IdProof!=null && IdProof.trim().length()>0)
                                {
                                    ChildrenIDProof_combo.setText(IdProof);
                                }
                                else
                                {
                                    ChildrenIDProof_combo.setText("");
                                }
                                if(PhoneNo!=null && PhoneNo.trim().length()>0)
                                {
                                    ChildrenPhone_text.setText(PhoneNo);
                                }
                                else
                                {
                                    ChildrenPhone_text.setText("");
                                }
                                if(EmailId!=null && EmailId.trim().length()>0)
                                {
                                    ChildrenEmailId_text.setText(EmailId);
                                }
                                else
                                {
                                    ChildrenEmailId_text.setText("");
                                }
                                if(UniqueNo!=null && UniqueNo.trim().length()>0)
                                {
                                    ChildrenUniqueNo_text.setText(UniqueNo);
                                }
                                else
                                {
                                    ChildrenUniqueNo_text.setText("");
                                }
                                if(date!=null && date.trim().length()>0)
                                {
                                    ChildrenDOB_text.setText(date);
                                }
                                else
                                {
                                    ChildrenDOB_text.setText("");
                                }
                                cb.setName(name);
                                cb.setGender(Gender);
                                cb.setBloodGrp(BloodGroup);
                                cb.setIDProof(IdProof);
                                cb.setPhoneNo(PhoneNo);
                                cb.setEmailID(EmailId);
                                cb.setUniqueNo(UniqueNo);
                                cb.setDateOfBirth(Dob);
                                
                                SD_listAll.add(name);
                                ChildrenClassList.add(cb);
                            
                            }
                            else
                            {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText("");
                            }
                        }                                                                                            
                        MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                        
                   }
                   else
                   {
                       MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText(""); 
                   }
                }
              else
               {
                   MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText("");
               }
               
               // *********************************** CHILDREN DETAILS ENDS HERE ***************************************************
               
               
               
               // *********************************** ADDRESS DETAILS STARTS HERE ***************************************************
              
               
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               if(obj4!=null){
                   ResPost_text.setVisible(false);
                   if(obj4[0]!=null){
                       String ResStrFull=obj4[0].toString();
                       String resArr[] = ResStrFull.split("#");
                       if(resArr.length>0){
                           String Rline1=resArr[0].toString();
                           String Rline2=resArr[1].toString();
                           String Rpost=resArr[2].toString();
                           String Rcity=resArr[3].toString();
                           String RState=resArr[4].toString();
                           String RCountry=resArr[5].toString();
                           String PinNo=resArr[6].toString();
                           if((Rline1!=null && Rline1.trim().length()>0) || (Rline2!=null && Rline2.trim().length()>0))
                           {
                           Rline1Res_text.setText(Rline1);
                           RLine2Res_text.setText(Rline2);
                           }
                           else
                           {
                               Rline1Res_text.setText("");
                               RLine2Res_text.setText("");
                           }
                           if(Rpost!=null && Rpost.trim().length()>0)
                           {
                           jLabel85.setText(Rpost);
                           }
                           else
                           {
                               ResPost_text.setText("");
                           }
                           if(PinNo!=null && PinNo.trim().length()>0)
                           {
                           ResPinNo_text.setText(PinNo);
                           }
                           else
                           {
                               ResPinNo_text.setText("");
                           }
                           if(Rcity!=null && Rcity.trim().length()>0)
                           {
                           ResCity_combo.setText(Rcity);
                           }
                           else
                           {
                               ResCity_combo.setText("");
                           }
                           
                           if(RState!=null && RState.trim().length()>0)
                           {
                           ResState_Combo.setText(RState);
                           }
                           else
                           {
                               ResState_Combo.setText("");
                           }
                  
//                           for(int i=0;i<StateList.size();i++){
//                               String c = StateList.get(i).toString();
//                               if(c.equals(RState)){
//                                   ResState_Combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   ResState_Combo.setSelectedIndex(-1);
//                               }
//                           }
//                           for(int i=0;i<CountryList.size();i++){
//                               String c = CountryList.get(i).toString();
//                               if(c.equals(RCountry)){
//                                   ResCountry_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   ResCountry_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(RCountry!=null && RCountry.trim().length()>0)
                           {
                           ResCountry_combo.setText(RCountry);
                           }
                           else
                           {
                               ResCountry_combo.setText("");
                           }
                           
                           
                       }
                       else
                       {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");
                       }
                   }
                   else
                   {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");  
                   }
                   
                   if(obj4[1]!=null){
                       String ResStrFull=obj4[1].toString();
                       String OffArr[] = ResStrFull.split("#");
                       if(OffArr.length>0){
                           OffPost_Text.setVisible(false);
                           String Oline1=OffArr[0].toString();
                           String Oline2=OffArr[1].toString();
                           String Opost=OffArr[2].toString();
                           String Ocity=OffArr[3].toString();
                           String OState=OffArr[4].toString();
                           String OCountry=OffArr[5].toString();
                           String OPinNo=OffArr[6].toString();
                           if((Oline1!=null && Oline1.trim().length()>0) || (Oline2!=null && Oline2.trim().length()>0))
                           {
                           offLine1_text.setText(Oline1);
                           Off_line2_text.setText(Oline2);
                           }
                           else
                           {
                               offLine1_text.setText("");
                               Off_line2_text.setText("");
                           }
                           if(Opost!=null && Opost.trim().length()>0)
                           {
                           OffPost_Text1.setText(Opost);
                           }
                           else
                           {
                               OffPost_Text1.setText("");
                           }
                           if(OPinNo!=null && OPinNo.trim().length()>0)
                           {
                           OffPinNo_text.setText(OPinNo);
                           }
                           else
                           {
                               OffPinNo_text.setText("");
                           }
                           if(Ocity!=null & Ocity.trim().length()>0)
                           {
                           offCity_combo.setText(Ocity);
                           }
                           else
                           {
                               offCity_combo.setText("");
                           }
//                           for(int i=0;i<CityList.size();i++){
//                               String c = CityList.get(i).toString();
//                               if(c.equals(Ocity)){
//                                   offCity_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   offCity_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(OState!=null && OState.trim().length()>0)
                           {
                           OffState_combo.setText(OState);
                           }
                           else
                           {
                               OffState_combo.setText("");
                           }
//                           for(int i=0;i<StateList.size();i++){
//                               String c = StateList.get(i).toString();
//                               if(c.equals(OState)){
//                                   OffState_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   OffState_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(OCountry!=null && OCountry.trim().length()>0)
                           {
                           Off_Country_combo.setText(OCountry);
                           }
                           else
                           {
                               Off_Country_combo.setText("");
                           }
//                           for(int i=0;i<CountryList.size();i++){
//                               String c = CountryList.get(i).toString();
//                               if(c.equals(OCountry)){
//                                   Off_Country_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   Off_Country_combo.setSelectedIndex(-1);
//                               }
//                           }
                           
                           
                       }
                       else
                       {
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");
                       }
                   }
                   else
                   {
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");   
                   }
                   
                   if(obj4[2]!=null){
                       String m = obj4[2].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       MobileNo_text.setText(m);
                       }
                       else
                       {
                           MobileNo_text.setText("");
                       }
                   }
                   else
                   {
                       MobileNo_text.setText("");
                   }
                   if(obj4[3]!=null){
                       String m = obj4[3].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Residence_no_text.setText(m);
                       }
                       else
                       {
                           Residence_no_text.setText("");
                       }
                   }
                   else
                   {
                       Residence_no_text.setText("");
                   }
                   if(obj4[4]!=null){
                       String m = obj4[4].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       OfficeNo_text.setText(m);
                       }
                       else
                       {
                           OfficeNo_text.setText("");
                       }
                   }
                   else
                   {
                       OfficeNo_text.setText("");
                   }
                   if(obj4[5]!=null){
                       Office_Radio.setVisible(true);
                       residence_Radio.setVisible(true);
                       String m = obj4[5].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       if(m.equals("Residence")){
                           residence_Radio.setSelected(true);
                           Office_Radio.setVisible(false);
                       }
                       else{
                           Office_Radio.setSelected(true);
                           residence_Radio.setVisible(false);
                       }
                       }
                       else
                       {
                           residence_Radio.setText("");
                       }
                   }
                   else 
                   {
                       residence_Radio.setText("");
                   }
                   if(obj4[6]!=null){
                       String m = obj4[6].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Email_Text.setText(m);
                       }
                       else
                       {
                           Email_Text.setText("");
                       }
                   }
                   else
                   {
                       Email_Text.setText("");
                   }
                   if(obj4[7]!=null){
                       String m = obj4[7].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Twitter_text.setText(m);
                       }
                       else
                       {
                           Twitter_text.setText("");
                       }
                   }
                   else
                   {
                       Twitter_text.setText("");
                   }
               }
               else
               {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");  
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");  
                           MobileNo_text.setText("");
                           Residence_no_text.setText("");
                           OfficeNo_text.setText("");
                           Email_Text.setText("");
                           Twitter_text.setText("");
                           residence_Radio.setText("");
               }
                
    
     
             // ************************************OTHER INFO PANEL ***********************************************************************************************  
               Object[] obj5 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MOBILETYPE,PROFFESION,CLUBACTIVITY,PLAYSPORTS,SpecialTalent,FACILITIES,RecreationalActivities FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
                SelectedActivityList = new ArrayList<String>();
     SelectedFacilityList = new ArrayList<String>();
     RecreationalActivityList = new ArrayList<RecreationalActivitiesListClass>();
               if(obj5!=null){
                   if(obj5[0]!=null){
                       String Mobtype=obj5[0].toString();
                       activities_combo.setVisible(false);
                       if(Mobtype!=null && Mobtype.trim().length()>0)
                       {
                       MobilePhoneUse_Combo.setText(Mobtype);
                       }
                       else
                       {
                           MobilePhoneUse_Combo.setText("");
                       }
//                       for(int i=0;i<MobileOsList.size();i++){
//                           String x=MobileOsList.get(i).toString();
//                           if(x.equals(Mobtype)){
//                               MobilePhoneUse_Combo.setSelectedIndex(i);
//                               break;
//                           }
//                           else{
//                               MobilePhoneUse_Combo.setSelectedIndex(-1);
//                           }
//                       }
                   }
                   else
                   {
                       MobilePhoneUse_Combo.setText("");
                   }

//                   else{
//                       MobilePhoneUse_Combo.setSelectedIndex(-1);
//                   }
                   if(obj5[1]!=null){
                       String Profession=obj5[1].toString();
                       if(Profession!=null && Profession.trim().length()>0)
                       {
                      Proffesion_Combo.setText(Profession);
                       }
                       else
                       {
                           Proffesion_Combo.setText("");
                       }
//                       for(int i=0;i<ProfessionList.size();i++){
//                           String x=ProfessionList.get(i).toString();
//                           if(x.equals(Mobtype)){
//                               Proffesion_Combo.setSelectedIndex(i);
//                               break;
//                           }
//                           else{
//                               Proffesion_Combo.setSelectedIndex(-1);
//                           }
//                       }
                   }
                   else
                   {
                       Proffesion_Combo.setText("");
                   }
//                   else{
//                       Proffesion_Combo.setSelectedIndex(-1);
//                   }
                   if(obj5[2]!=null){
                       String Act = obj5[2].toString();
                       String ActArr[]=Act.split("#");
                       for(int i=0;i<ActArr.length;i++){
                           String t = ActArr[i].toString();
                           SelectedActivityList.add(t);
                       }
                       Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
                   }
                   else
                   {
                      Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList)); 
                   }
                   if(obj5[3]!=null){
                       String t=obj5[3].toString();
                       if(t!=null && t.trim().length()>0)
                       {
                       PlaySport_Text.setText(t);
                       }
                       else
                       {
                           PlaySport_Text.setText("");
                       }
                   }
                   else
                   {
                       PlaySport_Text.setText("");
                   }
                   if(obj5[4]!=null){
                       String t=obj5[4].toString();
                       if(t!=null && t.trim().length()>0)
                       {
                       SpecialTalent_text.setText(t);
                       }
                       else
                       {
                           SpecialTalent_text.setText("");
                       }
                   }
                   else
                   {
                       SpecialTalent_text.setText("");
                   }
                   if(obj5[5]!=null){
                       String Facility = obj5[5].toString();
                       String FacArr []=Facility.split("#");
                       for(int i=0;i<FacArr.length;i++){
                           String t=FacArr[i].toString();
                           SelectedFacilityList.add(t);
                       }
                       Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                   }
                   else
                   {
                       Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                   }
                   if(obj5[6]!=null){
                       String slno="";
                       String activity="";
                       String Level="";
                       String RaStr =obj5[6].toString();
                       String Recrea_Arr[] = RaStr.split("#");
                       for(int i=0;i<Recrea_Arr.length;i++){
                           String x =Recrea_Arr[i].toString();
                           String ReacAct[] = x.split("%");
                           RecreationalActivitiesListClass rclass= new RecreationalActivitiesListClass();
                           if(ReacAct.length>0){
                              for(int j=0;j<ReacAct.length;j++)
                              {
                                  if(j==0)
                                  {
                               slno=ReacAct[0].toString();
                                  }
                                  if(j==1)
                                  {
                               activity=ReacAct[1].toString();
                                  }
                                  if(j==2)
                                  {
                               Level=ReacAct[2].toString();
                                  }
                              }
                               rclass.setSlNo(slno);
                               rclass.setActivityName(activity);
                               rclass.setActivityLevel(Level);
                               RecreationalActivityList.add(rclass);
                              
                           }
                       }
                       MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true);
                   }
                   else
                   {
                       MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true);
                   }
               }
               else
               {
                    Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList)); 
                    Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                   MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true);
                MobilePhoneUse_Combo.setText(""); 
                Proffesion_Combo.setText("");
                PlaySport_Text.setText("");
                SpecialTalent_text.setText("");
               }
              // ******************************** ENDD ******************************************************************************  
               
               
              // ************************************ OTHER CLUB INFO ******************************************************************* 
               
               OtherClubClassListAll_Class=new ArrayList<OtherClubClass>();
               Object[] obj6 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT OTHERCLUB FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING })).find(Id); 
               
               if(obj6!=null){
                   
                   
                   if(obj6[0]!=null){
                       String Clubname="";
                        String memno="";
                        String TypeofMember="";
                          String City="";
                             String MemberSince="";
                       String otherclubfull = obj6[0].toString();
                       other_clubs_hidden_panel.setVisible(false);
                       memberSince_text.setVisible(false);
                       OtherClubMemno_text.setVisible(false);
                       jTextField34.setVisible(false);
                       String Str_Arr[]=otherclubfull.split("#");
                       for(int i=0;i<Str_Arr.length;i++){
                           String clubInfo = Str_Arr[i].toString();
                           String Clubinfo_arr[] = clubInfo.split("%");
                           if(Clubinfo_arr.length>0){
                               for(int j=0;j<Clubinfo_arr.length;j++)
                               {
                                   if(j==0)
                                   {
                               Clubname=Clubinfo_arr[0].toString();
                                   }
                                   if(j==1)
                                   {
                               memno=Clubinfo_arr[1].toString();
                                   }
                               if(j==2)
                               {
                               TypeofMember=Clubinfo_arr[2].toString();
                               }
                               if(j==3)
                               {
                               City=Clubinfo_arr[3].toString();
                               }
                               if(j==4)
                               {
                              MemberSince=Clubinfo_arr[4].toString();
                               }
                               }
                               Date memSinceDate = null;
                               if(MemberSince!=null && MemberSince.trim().length()>0){
                                   memSinceDate = formatter.parse(MemberSince);
                               }
                               OtherClubClass clb=new OtherClubClass();
                               clb.setClubName(Clubname);
                               clb.setMemberno(memno);
                               clb.setTypeofMember(TypeofMember);
                               clb.setCity(City);
                               clb.setMemberSince(memSinceDate);
                               OtherClubClassListAll_Class.add(clb);
                               
                           }
                           
                       }
                        MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true);
                   }
                      else
                   {
                       MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true);
                   }
                   
               }
               else
               {
                  MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true);  
               }
                           
               
               Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT SD_SPORTS_DETAIL   FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id);
               SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();
               if(obj7!=null){
                   
                   if(obj7[0]!=null){
                       String SD_Sportfull = obj7[0].toString();
                       if(SD_Sportfull.trim().length()>0){
                       String Str_Arr[]=SD_Sportfull.split("#");
                       for(int i=0;i<Str_Arr.length;i++){
                           String SD_SportsInfo[] = Str_Arr[i].split("%");
                           if(SD_SportsInfo.length>0){
                               
                               
                                  
                                  
                                String dname = SD_SportsInfo[0].toString();
                                  
                                   
                                   
                                String Sname = SD_SportsInfo[1].toString();
                                   
                               
                               SD_Sports_Class cs = new SD_Sports_Class();
                        
                               cs.setSD_Name(dname);
                               cs.setSD_Sports(Sname);
                               
                               SD_Sports_ClassListAll.add(cs);
                               
                           }
                       }
                     MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
                  
                   }
                     
                   }
                   else
                   {
                        MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
                   }
                   
               }
               else
               {
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
               }
               
               
               SD_ClubActivity_ClassListAll=new ArrayList<SD_ClubActivity_Class>();
                 SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
                 SD_RActivity_ClassListAll=new ArrayList<SD_RActivity_Class>();
                  SD_Talent_ClassListAll=new ArrayList<SD_Talent_Class>();
               Object[] obj8 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT  SD_RACTIVITY,SD_FACILITY ,SD_TALENT,SD_CLUBACTIVITY FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{ Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE,Datas.SERIALIZABLE})).find(Id);
              
                if(obj8!=null){ 
                     String SD_name="";
                     String SD_Sports="";
                      String SD_Level="";
                    if(obj8[0]!=null){
                        String x = obj8[0].toString();
                        if(x.trim().length()>0)
                        {
                        String SD_RacArr [] = x.split("#");
                        for(int i=0;i<SD_RacArr.length;i++){
                            String x1 = SD_RacArr[i].toString();
                            String SD_Arr[]=x1.split("%");
                            for(int j=0;j<SD_Arr.length;j++)
                            {
                                if(j==0)
                                {
                             SD_name = SD_Arr[0].toString();
                                }
                                if(j==1)
                                {
                             SD_Sports=SD_Arr[1].toString();
                                }
                                if(j==2)
                                {
                             SD_Level=SD_Arr[2].toString();
                                }
                            }
                            SD_RActivity_Class sd=new SD_RActivity_Class();
                            sd.setSD_Name(SD_name);
                            sd.setSD_Sports(SD_Sports);
                            sd.setSD_level(SD_Level);
                            SD_RActivity_ClassListAll.add(sd);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true);
                        }   
                        }
                         else
                        {
                           MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true);  
                        }
                         
                    }
                    else
                    {
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true);  
                    }
                    
                    if(obj8[1]!=null){
                        String SD_Name="";
                        String Facility ="";
                        String x = obj8[1].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            
                            String x1=StrArr[i].toString();
                            if(x1.trim().length()>0)
                            {
                            String SD_facArr[] = x1.split("%");
                            for(int j=0;j<SD_facArr.length;j++)
                            {
                                if(j==0)
                                {
                            SD_Name = SD_facArr[0].toString();
                                }
                                if(j==1)
                                {
                             Facility = SD_facArr[1].toString();
                                }
                            }
                            SD_Facility_Class sf = new SD_Facility_Class();
                            sf.setSD_Name(SD_Name);
                            sf.setSD_Facility(Facility);
                            SD_Facility_ClassListAll.add(sf);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                        }
                            else
                            {
                                MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                            }
                        }
                        
                        
                    }
                    else
                    {
                       MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true); 
                    }
                    if(obj8[2]!=null){
                        String SD_Name="";
                        String talent="";
                        String x = obj8[2].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            String x1=StrArr[i].toString();
                            if(x1.trim().length()>0)
                            {
                            String SD_TalArr[] = x1.split("%");
                            for(int j=0;j<SD_TalArr.length;j++)
                            {
                                if(j==0)
                                {
                             SD_Name = SD_TalArr[0].toString();
                                }
                                if(j==1)
                                {
                             talent = SD_TalArr[1].toString();
                                }
                            }
                            SD_Talent_Class sf = new SD_Talent_Class();
                            sf.setSD_Name(SD_Name);
                            sf.setSD_Talent(talent);
                            SD_Talent_ClassListAll.add(sf);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);
                        }
                            else
                            {
                                MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true); 
                            }
                        }
                        
                        
                    }
                    else
                    {
                         MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);
                    }
                    if(obj8[3]!=null){
                        String SD_Name="";
                        String SD_Activity="";
                        if(!obj8[3].equals("")){
                          String x = obj8[3].toString();
                          String StrArr[] = x.split("#");
                          for(int i=0;i<StrArr.length;i++){
                              String x1=StrArr[i].toString();
                              String SD_ActivityArr[] = x1.split("%");
                              if(SD_ActivityArr.length>1){
                                  for(int j=0;j<SD_ActivityArr.length;j++)
                                  {
                                      if(j==0)
                                      {
                                 SD_Name = SD_ActivityArr[0].toString();
                                      }
                                      if(j==1)
                                      {
                                 SD_Activity = SD_ActivityArr[1].toString();
                                      }
                                  }
                                MemberViewDetails.SD_ClubActivity_Class sd = new MemberViewDetails.SD_ClubActivity_Class();
                                sd.setSD_Name(SD_Name);
                                sd.setSD_Activity(SD_Activity);
                                SD_ClubActivity_ClassListAll.add(sd);
                                MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);
                              }
                              else
                              {
                                 MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true); 
                              }
                          }  
                            
                        }
                        else
                        {
                           MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);
                        }
                    }
                    else
                    {
                        MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);
                    }
                  }
                else
                {
                   MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true); 
                   MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                   MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);
                   MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);         
                }
                   
                   
                   
               
               // ***************** RECREATIONL ACTIVITES FOR SPOUSE/DEPENDENTS *****************************************
               
               
               
               
               
                SD_List_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                sports_SD_Combo.setModel(SD_List_ComboBoxValModel);
                SD_Ractivity_ComboBoxValModel = new ComboBoxValModel(SD_listAll);
                SD_RActivity_Combo.setModel(SD_Ractivity_ComboBoxValModel);
                SD_FacilitySpouse_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_FacilitySpouce_Combo.setModel(SD_FacilitySpouse_ComboBoxValModel);
                SD_Talent_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_talent_Combo.setModel(SD_Talent_ComboBoxValModel);
                
                
          }
          
       
        
      }
      catch(BasicException ex){
          Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
      }
      catch(ParseException ex){
          Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
      }
    }
     public void getAlreadyLoadedMemberInfo2(String Id) {
     prev_Data.setEnabled(true);
     try{   
       
          SD_listAll = new ArrayList<String>();
          
          // ***********************  MAIN PAGE INFO **********************************************************
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MEMBERNAME,DATEOFBIRTH,IDPROOF,IDUNIQUENO,MEMBERTYPE,MEMBERSINCE,SPOUCENAME,DATEOFMARRIAGE,GENDER FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.STRING,Datas.STRING , Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP , Datas.STRING })).find(Id); 
         
        
         
         //kymmember_arv_id="db9cd16e-14c6-46de-b165-2faed7ef17a9";
          if(obj1!=null){
             // prev_Data.setEnabled(true);
              if(obj1[0]!=null){
                  mname_text.setText(obj1[0].toString());
              }
              
              if(obj1[1]!=null){
                  Date dob = (Date) obj1[1];
                  String X = formatter.format(dob);
                  DOB_text.setText(X);
              }                                        
             if(obj1[2]!=null){
                 String idProofDoc=obj1[2].toString();
                 if(idProofDoc!=null && idProofDoc.trim().length()>0)
                 {
                  IDproofDoc_combo.setText(idProofDoc);
//                  for(int i=0;i<IDProofTypeList.size();i++){
//                      String x=IDProofTypeList.get(i).toString();
//                      if(x.equals(idproof)){
//                          IDproofDoc_combo.setSelectedIndex(i);
//                          break;
//                      }
//                  }
                 }
                 else
                 {
                     IDproofDoc_combo.setText(null);
                 }
              }
             else
             {
                 IDproofDoc_combo.setText(null);
             }
              
              if(obj1[3]!=null){
                  idDocUniqueNo_text.setText(obj1[3].toString());
              }
              
              if(obj1[4]!=null){
                  String memtype=obj1[4].toString();
                  TypeofMember_combo.setText(memtype);
//                  for(int i=0;i<MemberShipTypeList.size();i++){
//                      String x=MemberShipTypeList.get(i).toString();
//                      if(x.equals(memtype)){
//                          TypeofMember_combo.setSelectedIndex(i);
//                          break;
//                      }
//                  }
              }
//              else{
//                  TypeofMember_combo.setSelectedIndex(-1);
//              }
              
              
              if(obj1[5]!=null){
                  memberSince_text.setVisible(false);
                  Date memsince = (Date) obj1[5];
                  String X = formatter.format(memsince);
                  
                  memberSince_text1.setText(X);
              }
              else
              {
                  memberSince_text1.setText("");
              }
              if(obj1[6]!=null){
                  String spouse_name=obj1[6].toString();
                  if(spouse_name.trim().length()>0)
                  {
                  SpouseName_text.setText(spouse_name);
                  SD_listAll.add(obj1[6].toString());
                  }
                  else{
                      SpouseName_text.setText("");
                  }
              }
              else
              {
                  SpouseName_text.setText("");
              }
              if(obj1[7]!=null){
                  Date dom = (Date) obj1[7];
                  String X = formatter.format(dom);
                  if(X!=null && X.trim().length()>0)
                  {
                  DateOfMarriage_text.setText(X);
                  }
                  else
                  {
                      DateOfMarriage_text.setText("");
                  }
              }
                else
              {
                  DateOfMarriage_text.setText("");
              }
              if(obj1[8]!=null){
                 
                 String g=obj1[8].toString().toLowerCase();
                 if(g.equals("male")){
                     male_radio.setVisible(true);
                     male_radio.setSelected(true);
                     female_radio.setVisible(false);
                 }
                 else{
                     female_radio.setVisible(true);
                     female_radio.setSelected(true);
                     male_radio.setVisible(false);
                 }
              }
              
              // ***********************  MAIN PAGE INFO ENDS********************************************************** 
              
              
              
              // ***********************  PARENTS INFO  **********************************************************
              
              Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT FATHERNAME,FATHERDOB,MOTHERNAME,MOTHERDOB,FATHERBG,FATHERPHONENO,FATHEREMAILID,FATHERIDPROOF,FATHERUNIQUENO,MOTHERBG,MOTHERPHONENO,MOTHEREMAILID,MOTHERIDPROOF,MOTHERUNIQUENO FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(
                   new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.TIMESTAMP,Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING })).find(Id); 
          
                if(obj2!=null){

                    if(obj2[0]!=null){
                        String fn = obj2[0].toString();
                        FatherName_text.setText(fn);
                        jTextField1.setText(fn);
                    }
                    if(obj2[1]!=null){
                         Date dom = (Date) obj2[1];
                          String X = formatter.format(dom);
                          if(X!=null && X.trim().length()>0)
                          {
                          fatherDob_text.setText(X);
                          }
                          else
                          {
                              fatherDob_text.setText("");
                          }
                    }
                    else
                    {
                        fatherDob_text.setText("");
                    }
                    if(obj2[2]!=null){
                        String fn = obj2[2].toString();
                        if(fn!=null&& fn.trim().length()>0)
                        {
                        MotherName_text.setText(fn);
                        }
                        else
                        {
                            MotherName_text.setText("");
                        }
                    }
                    else
                    {
                        MotherName_text.setText("");
                    }
                    if(obj2[3]!=null){
                         Date dom = (Date) obj2[3];
                          String X = formatter.format(dom);
                          if(X!=null&&X.trim().length()>0)
                          {
                          MotherDOB_text.setText(X);
                          }
                          else
                              {
                                  MotherDOB_text.setText("");
                              }
                    }
                    else{
                    MotherDOB_text.setText("");
                    }
                    if(obj2[4]!=null){
                        String Fatherbloodgrp= obj2[4].toString();
//                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
//                            String x= ChildrenBloodGrpListAll.get(i).toString();
//                            if(x.equals(Fatherbloodgrp)){
//                                FatherBloodGroup_combo.setSelectedIndex(i);
//                                break;
//                            }
                     // }
                        if(Fatherbloodgrp!=null && Fatherbloodgrp.trim().length()>0)
                        {
                         FatherBloodGroup_combo.setText(Fatherbloodgrp);
                        }
                        else 
                        {
                            FatherBloodGroup_combo.setText("");
                        }
                    }
                    else 
                    {
                        FatherBloodGroup_combo.setText("");
                    }

                    if(obj2[5]!=null){
                        String fn = obj2[5].toString();
                        if(fn.trim().length()>0)
                        {
                        FatherPhoneNo_Text.setText(fn);
                        }
                         else
                    {
                        FatherPhoneNo_Text.setText("");
                    }
                    }
                    else
                    {
                        FatherPhoneNo_Text.setText("");
                    }
                   
                    if(obj2[6]!=null){
                        String fn = obj2[6].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        FatherEmailId_Text.setText(fn);
                        }
                        else
                        {
                            FatherEmailId_Text.setText("");
                        }
                    }
                    else
                    {
                        FatherEmailId_Text.setText("");
                    }
                    if(obj2[7]!=null){
                        String FatherIdProof= obj2[7].toString();
//                        for(int i=0;i<IDProofTypeList.size();i++){
//                            String x= IDProofTypeList.get(i).toString();
//                            if(x.equals(FatherIdProof)){
//                                FatherIdProof_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                        if(FatherIdProof!=null&&FatherIdProof.trim().length()>0)
                        {
                        FatherIdProof_combo.setText(FatherIdProof);
                        }
                        else
                        {
                            FatherIdProof_combo.setText("");
                        }
                    }
                    else
                    {
                        FatherIdProof_combo.setText("");
                    }

                    if(obj2[8]!=null){
                        String fn = obj2[8].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        FatherUniqueNo_text.setText(fn);
                        }
                        else
                        {
                            FatherUniqueNo_text.setText("");
                        }
                    }
                    else
                    {
                        FatherUniqueNo_text.setText("");
                    }

                    if(obj2[9]!=null){
                        String Motherbloodgrp= obj2[9].toString();
//                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
//                            String x= ChildrenBloodGrpListAll.get(i).toString();
//                            if(x.equals(Motherbloodgrp)){
//                                MotherBloodGroup_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                        if(Motherbloodgrp!=null&&Motherbloodgrp.trim().length()>0)
                        {
                        MotherBloodGroup_combo.setText(Motherbloodgrp);
                        }
                        else
                        {
                            MotherBloodGroup_combo.setText("");
                        }
                    }
                    else
                    {
                        MotherBloodGroup_combo.setText("");
                    }

                    if(obj2[10]!=null){
                        String fn = obj2[10].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherPhoneNo_text.setText(fn);
                        }
                        else
                        {
                            MotherPhoneNo_text.setText("");
                        }
                    }
                    else
                    {
                        MotherPhoneNo_text.setText("");
                    }
                    if(obj2[11]!=null){
                        String fn = obj2[11].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherEmailid_text.setText(fn);
                        }
                        else
                        {
                            MotherEmailid_text.setText("");
                        }
                    }
                    else
                    {
                        MotherEmailid_text.setText("");
                    }
                        if(obj2[12]!=null){
                        String MotherIdProof= obj2[12].toString();
//                        for(int i=0;i<IDProofTypeList.size();i++){
//                            String x= IDProofTypeList.get(i).toString();
//                            if(x.equals(MotherIdProof)){
//                                MotherIdProof_combo.setSelectedIndex(i);
//                                break;
//                            }
//                        }
                       if(MotherIdProof!=null&&MotherIdProof.trim().length()>0)
                       {
                        MotherIdProof_combo.setText(MotherIdProof);
                       }
                       else
                       {
                           MotherIdProof_combo.setText("");
                       }
                       }
                        else
                       {
                           MotherIdProof_combo.setText("");
                       }
                      

                    if(obj2[13]!=null){
                        String fn = obj2[13].toString();
                        if(fn!=null&&fn.trim().length()>0)
                        {
                        MotherUniqueNo_text.setText(fn);
                        }
                        else
                        {
                            MotherUniqueNo_text.setText("");
                        }
                    }
                    else
                    {
                        MotherUniqueNo_text.setText("");
                    }



                }
                else
                {
                    fatherDob_text.setText("");
                    MotherName_text.setText("");
                    MotherDOB_text.setText("");
                    FatherBloodGroup_combo.setText("");
                    FatherPhoneNo_Text.setText("");
                    FatherEmailId_Text.setText("");
                    FatherIdProof_combo.setText("");
                    FatherUniqueNo_text.setText("");
                    MotherBloodGroup_combo.setText("");
                    MotherPhoneNo_text.setText("");
                    MotherEmailid_text.setText("");
                    MotherIdProof_combo.setText("");
                    MotherUniqueNo_text.setText("");
                }
               // ***********************  PARENTS INFO  ENDS **********************************************************
                
                
                
                
              // ***********************  CHILDREN INFO    **********************************************************  // 
               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT DEPENDENTDETAILS FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id); 
            
               ChildrenClassList = new ArrayList<ChildrenClass>();
               
               if(obj3!=null){
                   String name="";
                   String Gender="";
                   String BloodGroup="";
                   String IdProof="";
                   String PhoneNo="";
                   String EmailId="";
                   String UniqueNo="";
                   String Cdob="";
                   if(obj3[0]!=null){
                        String DependentDetailstr=obj3[0].toString();
                        String Classlevelstr[] = DependentDetailstr.split("#");
                        for(int i=0;i<Classlevelstr.length;i++){
                            String ChildLevel=Classlevelstr[i].toString();
                            String ChildArr[]=ChildLevel.split("%");
                            ChildrenClass cb = new ChildrenClass();
                            
                            if(ChildArr.length>0){
                                for(int j=0;j<ChildArr.length;j++)
                                {
                                    if(j==0)
                                    {
                                name=ChildArr[0].toString();
                                    }
                                    if(j==1)
                                    {
                                Gender=ChildArr[1].toString();
                                    }
                                    if(j==2)
                                    {
                                BloodGroup=ChildArr[2].toString();
                                    }
                                    if(j==3)
                                    {
                                IdProof=ChildArr[3].toString();
                                    }
                                    if(j==4)
                                    {
                                PhoneNo=ChildArr[4].toString();
                                    }
                                    if(j==5)
                                    {
                                EmailId=ChildArr[5].toString();
                                    }
                                    if(j==6)
                                    {
                                UniqueNo=ChildArr[6].toString();
                                    }
                                    if(j==7)
                                    {
                                Cdob=ChildArr[7].toString(); 
                                    }
                                    }
                                ChildrenName_text.setVisible(false);
                                ChildrenBlood_Combo.setVisible(false);
                                ChildrenIDProof_combo.setVisible(false);
                                ChildrenPhone_text.setVisible(false);
                                ChildrenEmailId_text.setVisible(false);
                                ChildrenUniqueNo_text.setVisible(false);
                                ChildrenDOB_text.setVisible(false);                                                                
                                 String date=null;
                                 Date Dob=null;
                                try
                                {
                                 if(Cdob!=null && Cdob.trim().length()>0)
                                 {
                                Dob=(Date)formatter.parse(Cdob);
                                date=formatter.format(Dob);
                                 }
                                }catch(ParseException ex)
                                        {
                                            ex.printStackTrace();
                                        }
                                catch(Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                if(name!=null && name.trim().length()>0)
                                {
                                    ChildrenName_text.setText(name);
                                }
                                else 
                                {
                                    ChildrenName_text.setText("");
                                }
                                if(Gender!=null && Gender.trim().length()>0)
                                {
                                    ChildrenFemale_radio.setVisible(true);
                                    ChildrenMale_radio.setVisible(true);
                                    if (Gender.equals("Female")) {
                                        ChildrenFemale_radio.setSelected(true);
                                        ChildrenMale_radio.setVisible(false);
                                    }
                                   else
                                    if(Gender.equals("Male"))
                                    {
                                        ChildrenMale_radio.setSelected(true);
                                        ChildrenFemale_radio.setVisible(false);
                                    }
                                   
                                }
                                else
                                {
                                    ChildrenFemale_radio.setVisible(false);
                                    ChildrenMale_radio.setVisible(false);
                                    ChildrenMale_radio.setText("");
                                }
                                if(BloodGroup!=null && BloodGroup.trim().length()>0)
                                {
                                    
                                    ChildrenBlood_Combo.setText(BloodGroup);
                                }
                                else
                                {
                                    ChildrenBlood_Combo.setText("");
                                }
                                if(IdProof!=null && IdProof.trim().length()>0)
                                {
                                    ChildrenIDProof_combo.setText(IdProof);
                                }
                                else
                                {
                                    ChildrenIDProof_combo.setText("");
                                }
                                if(PhoneNo!=null && PhoneNo.trim().length()>0)
                                {
                                    ChildrenPhone_text.setText(PhoneNo);
                                }
                                else
                                {
                                    ChildrenPhone_text.setText("");
                                }
                                if(EmailId!=null && EmailId.trim().length()>0)
                                {
                                    ChildrenEmailId_text.setText(EmailId);
                                }
                                else
                                {
                                    ChildrenEmailId_text.setText("");
                                }
                                if(UniqueNo!=null && UniqueNo.trim().length()>0)
                                {
                                    ChildrenUniqueNo_text.setText(UniqueNo);
                                }
                                else
                                {
                                    ChildrenUniqueNo_text.setText("");
                                }
                                if(date!=null && date.trim().length()>0)
                                {
                                    ChildrenDOB_text.setText(date);
                                }
                                else
                                {
                                    ChildrenDOB_text.setText("");
                                }
                                cb.setName(name);
                                cb.setGender(Gender);
                                cb.setBloodGrp(BloodGroup);
                                cb.setIDProof(IdProof);
                                cb.setPhoneNo(PhoneNo);
                                cb.setEmailID(EmailId);
                                cb.setUniqueNo(UniqueNo);
                                cb.setDateOfBirth(Dob);
                                
                                SD_listAll.add(name);
                                ChildrenClassList.add(cb);
                            }
                            else
                            {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText("");
                            }
                        }                                                                                            
                        MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                        
                   }
                   else
                   {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText(""); 
                   }
                }
              else
               {
                             ChildrenName_text.setText("");
                             ChildrenBlood_Combo.setText("");
                             ChildrenFemale_radio.setVisible(false);
                             ChildrenMale_radio.setVisible(false);
                             ChildrenMale_radio.setText("");
                             ChildrenIDProof_combo.setText("");
                             ChildrenPhone_text.setText("");
                             ChildrenEmailId_text.setText("");
                             ChildrenUniqueNo_text.setText("");
                             ChildrenDOB_text.setText("");
               }
               
               // *********************************** CHILDREN DETAILS ENDS HERE ***************************************************
               
               
               
               // *********************************** ADDRESS DETAILS STARTS HERE ***************************************************
              
               
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               if(obj4!=null){
                   ResPost_text.setVisible(false);
                   if(obj4[0]!=null){
                       String ResStrFull=obj4[0].toString();
                       String resArr[] = ResStrFull.split("#");
                       if(resArr.length>0){
                           String Rline1=resArr[0].toString();
                           String Rline2=resArr[1].toString();
                           String Rpost=resArr[2].toString();
                           String Rcity=resArr[3].toString();
                           String RState=resArr[4].toString();
                           String RCountry=resArr[5].toString();
                           String PinNo=resArr[6].toString();
                           if((Rline1!=null && Rline1.trim().length()>0) || (Rline2!=null && Rline2.trim().length()>0))
                           {
                           Rline1Res_text.setText(Rline1);
                           RLine2Res_text.setText(Rline2);
                           }
                           else
                           {
                               Rline1Res_text.setText("");
                               RLine2Res_text.setText("");
                           }
                           if(Rpost!=null && Rpost.trim().length()>0)
                           {
                           jLabel85.setText(Rpost);
                           }
                           else
                           {
                               ResPost_text.setText("");
                           }
                           if(PinNo!=null && PinNo.trim().length()>0)
                           {
                           ResPinNo_text.setText(PinNo);
                           }
                           else
                           {
                               ResPinNo_text.setText("");
                           }
                           if(Rcity!=null && Rcity.trim().length()>0)
                           {
                           ResCity_combo.setText(Rcity);
                           }
                           else
                           {
                               ResCity_combo.setText("");
                           }
                           
                           if(RState!=null && RState.trim().length()>0)
                           {
                           ResState_Combo.setText(RState);
                           }
                           else
                           {
                               ResState_Combo.setText("");
                           }
                  
//                           for(int i=0;i<StateList.size();i++){
//                               String c = StateList.get(i).toString();
//                               if(c.equals(RState)){
//                                   ResState_Combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   ResState_Combo.setSelectedIndex(-1);
//                               }
//                           }
//                           for(int i=0;i<CountryList.size();i++){
//                               String c = CountryList.get(i).toString();
//                               if(c.equals(RCountry)){
//                                   ResCountry_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   ResCountry_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(RCountry!=null && RCountry.trim().length()>0)
                           {
                           ResCountry_combo.setText(RCountry);
                           }
                           else
                           {
                               ResCountry_combo.setText("");
                           }
                           
                           
                       }
                       else
                       {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");
                       }
                   }
                   else
                   {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");  
                   }
                   
                   if(obj4[1]!=null){
                       String ResStrFull=obj4[1].toString();
                       String OffArr[] = ResStrFull.split("#");
                       if(OffArr.length>0){
                           OffPost_Text.setVisible(false);
                           String Oline1=OffArr[0].toString();
                           String Oline2=OffArr[1].toString();
                           String Opost=OffArr[2].toString();
                           String Ocity=OffArr[3].toString();
                           String OState=OffArr[4].toString();
                           String OCountry=OffArr[5].toString();
                           String OPinNo=OffArr[6].toString();
                           if((Oline1!=null && Oline1.trim().length()>0) || (Oline2!=null && Oline2.trim().length()>0))
                           {
                           offLine1_text.setText(Oline1);
                           Off_line2_text.setText(Oline2);
                           }
                           else
                           {
                               offLine1_text.setText("");
                               Off_line2_text.setText("");
                           }
                           if(Opost!=null && Opost.trim().length()>0)
                           {
                           OffPost_Text1.setText(Opost);
                           }
                           else
                           {
                               OffPost_Text1.setText("");
                           }
                           if(OPinNo!=null && OPinNo.trim().length()>0)
                           {
                           OffPinNo_text.setText(OPinNo);
                           }
                           else
                           {
                               OffPinNo_text.setText("");
                           }
                           if(Ocity!=null & Ocity.trim().length()>0)
                           {
                           offCity_combo.setText(Ocity);
                           }
                           else
                           {
                               offCity_combo.setText("");
                           }
//                           for(int i=0;i<CityList.size();i++){
//                               String c = CityList.get(i).toString();
//                               if(c.equals(Ocity)){
//                                   offCity_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   offCity_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(OState!=null && OState.trim().length()>0)
                           {
                           OffState_combo.setText(OState);
                           }
                           else
                           {
                               OffState_combo.setText("");
                           }
//                           for(int i=0;i<StateList.size();i++){
//                               String c = StateList.get(i).toString();
//                               if(c.equals(OState)){
//                                   OffState_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   OffState_combo.setSelectedIndex(-1);
//                               }
//                           }
                           if(OCountry!=null && OCountry.trim().length()>0)
                           {
                           Off_Country_combo.setText(OCountry);
                           }
                           else
                           {
                               Off_Country_combo.setText("");
                           }
//                           for(int i=0;i<CountryList.size();i++){
//                               String c = CountryList.get(i).toString();
//                               if(c.equals(OCountry)){
//                                   Off_Country_combo.setSelectedIndex(i);
//                                   break;
//                               }
//                               else{
//                                   Off_Country_combo.setSelectedIndex(-1);
//                               }
//                           }
                           
                           
                       }
                       else
                       {
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");
                       }
                   }
                   else
                   {
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");   
                   }
                   
                   if(obj4[2]!=null){
                       String m = obj4[2].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       MobileNo_text.setText(m);
                       }
                       else
                       {
                           MobileNo_text.setText("");
                       }
                   }
                   else
                   {
                       MobileNo_text.setText("");
                   }
                   if(obj4[3]!=null){
                       String m = obj4[3].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Residence_no_text.setText(m);
                       }
                       else
                       {
                           Residence_no_text.setText("");
                       }
                   }
                   else
                   {
                       Residence_no_text.setText("");
                   }
                   if(obj4[4]!=null){
                       String m = obj4[4].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       OfficeNo_text.setText(m);
                       }
                       else
                       {
                           OfficeNo_text.setText("");
                       }
                   }
                   else
                   {
                       OfficeNo_text.setText("");
                   }
                   if(obj4[5]!=null){
                       Office_Radio.setVisible(true);
                       residence_Radio.setVisible(true);
                       String m = obj4[5].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       if(m.equals("Residence")){
                           residence_Radio.setSelected(true);
                           Office_Radio.setVisible(false);
                       }
                       else{
                           Office_Radio.setSelected(true);
                           residence_Radio.setVisible(false);
                       }
                       }
                       else
                       {
                           residence_Radio.setText("");
                       }
                   }
                   else 
                   {
                       residence_Radio.setText("");
                   }
                   if(obj4[6]!=null){
                       String m = obj4[6].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Email_Text.setText(m);
                       }
                       else
                       {
                           Email_Text.setText("");
                       }
                   }
                   else
                   {
                       Email_Text.setText("");
                   }
                   if(obj4[7]!=null){
                       String m = obj4[7].toString();
                       if(m!=null && m.trim().length()>0)
                       {
                       Twitter_text.setText(m);
                       }
                       else
                       {
                           Twitter_text.setText("");
                       }
                   }
                   else
                   {
                       Twitter_text.setText("");
                   }
               }
               else
               {
                           Rline1Res_text.setText("");
                           RLine2Res_text.setText("");
                           jLabel85.setText("");
                           ResPinNo_text.setText("");
                           ResCity_combo.setText("");
                           ResState_Combo.setText("");
                           ResCountry_combo.setText("");  
                           offLine1_text.setText("");
                           Off_line2_text.setText("");
                           OffPost_Text1.setText("");
                           OffPinNo_text.setText("");
                           offCity_combo.setText("");
                           OffState_combo.setText("");
                           Off_Country_combo.setText("");  
                           MobileNo_text.setText("");
                           Residence_no_text.setText("");
                           OfficeNo_text.setText("");
                           Email_Text.setText("");
                           Twitter_text.setText("");
                           residence_Radio.setText("");
               }
               
                
             // ************************************OTHER INFO PANEL ***********************************************************************************************  
               Object[] obj5 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MOBILETYPE,PROFFESION,CLUBACTIVITY,PLAYSPORTS,SpecialTalent,FACILITIES,RecreationalActivities FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
                  
               SelectedActivityList = new ArrayList<String>();
     SelectedFacilityList = new ArrayList<String>();
     RecreationalActivityList = new ArrayList<RecreationalActivitiesListClass>();
               if(obj5!=null){
                   if(obj5[0]!=null){
                       String Mobtype=obj5[0].toString();
                       activities_combo.setVisible(false);
                       if(Mobtype!=null && Mobtype.trim().length()>0)
                       {
                       MobilePhoneUse_Combo.setText(Mobtype);
                       }
                       else
                       {
                           MobilePhoneUse_Combo.setText("");
                       }
//                       for(int i=0;i<MobileOsList.size();i++){
//                           String x=MobileOsList.get(i).toString();
//                           if(x.equals(Mobtype)){
//                               MobilePhoneUse_Combo.setSelectedIndex(i);
//                               break;
//                           }
//                           else{
//                               MobilePhoneUse_Combo.setSelectedIndex(-1);
//                           }
//                       }
                   }
                   else
                   {
                       MobilePhoneUse_Combo.setText("");
                   }

//                   else{
//                       MobilePhoneUse_Combo.setSelectedIndex(-1);
//                   }
                   if(obj5[1]!=null){
                       String Profession=obj5[1].toString();
                       if(Profession!=null && Profession.trim().length()>0)
                       {
                      Proffesion_Combo.setText(Profession);
                       }
                       else
                       {
                           Proffesion_Combo.setText("");
                       }
//                       for(int i=0;i<ProfessionList.size();i++){
//                           String x=ProfessionList.get(i).toString();
//                           if(x.equals(Mobtype)){
//                               Proffesion_Combo.setSelectedIndex(i);
//                               break;
//                           }
//                           else{
//                               Proffesion_Combo.setSelectedIndex(-1);
//                           }
//                       }
                   }
                   else
                   {
                       Proffesion_Combo.setText("");
                   }
//                   else{
//                       Proffesion_Combo.setSelectedIndex(-1);
//                   }
                   if(obj5[2]!=null){
                       String Act = obj5[2].toString();
                       String ActArr[]=Act.split("#");
                       for(int i=0;i<ActArr.length;i++){
                           String t = ActArr[i].toString();
                           SelectedActivityList.add(t);
                       }
                       Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
                   }
                   else
                   {
                      Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList)); 
                   }
                   if(obj5[3]!=null){
                       String t=obj5[3].toString();
                       if(t!=null && t.trim().length()>0)
                       {
                       PlaySport_Text.setText(t);
                       }
                       else
                       {
                           PlaySport_Text.setText("");
                       }
                   }
                   else
                   {
                       PlaySport_Text.setText("");
                   }
                   if(obj5[4]!=null){
                       String t=obj5[4].toString();
                       if(t!=null && t.trim().length()>0)
                       {
                       SpecialTalent_text.setText(t);
                       }
                       else
                       {
                           SpecialTalent_text.setText("");
                       }
                   }
                   else
                   {
                       SpecialTalent_text.setText("");
                   }
                   if(obj5[5]!=null){
                       String Facility = obj5[5].toString();
                       String FacArr []=Facility.split("#");
                       for(int i=0;i<FacArr.length;i++){
                           String t=FacArr[i].toString();
                           SelectedFacilityList.add(t);
                       }
                       Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                   }
                   else
                   {
                       Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                   }
                   if(obj5[6]!=null){
                       String slno="";
                       String activity="";
                       String Level="";
                       String RaStr =obj5[6].toString();
                       String Recrea_Arr[] = RaStr.split("#");
                       for(int i=0;i<Recrea_Arr.length;i++){
                           String x =Recrea_Arr[i].toString();
                           String ReacAct[] = x.split("%");
                           RecreationalActivitiesListClass rclass= new RecreationalActivitiesListClass();
                           if(ReacAct.length>0){
                               for(int j=0;j<ReacAct.length;j++)
                               {
                                   if(j==0)
                                   {
                               slno=ReacAct[0].toString();
                                   }
                                   if(j==1)
                                   {
                               activity=ReacAct[1].toString();
                                   }if(j==2)
                                   {
                               Level=ReacAct[2].toString();
                                   }
                               }
                               rclass.setSlNo(slno);
                               rclass.setActivityName(activity);
                               rclass.setActivityLevel(Level);
                               RecreationalActivityList.add(rclass);
                           }
                       }
                       MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true);
                   }
                   else
                   {
                      MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true); 
                   }
               }
               else
               {
                   Facility_Jlist.setModel(new MemberViewDetails.ItemsListModel(SelectedFacilityList));
                    Activities_jList.setModel(new MemberViewDetails.ItemsListModel(SelectedActivityList));
                     MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                       RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                       RecreationalActivity_JTable.setVisible(true);
                MobilePhoneUse_Combo.setText(""); 
                Proffesion_Combo.setText("");
                PlaySport_Text.setText("");
                SpecialTalent_text.setText("");
               }
              // ******************************** ENDD ******************************************************************************  
               
               
              // ************************************ OTHER CLUB INFO ******************************************************************* 
               
               
               Object[] obj6 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT OTHERCLUB FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING })).find(Id); 
               
               if(obj6!=null){
                   String Clubname="";
                   String memno="";
                   String TypeofMember="";
                   String City="";
                    String MemberSince="";
                   OtherClubClassListAll_Class=new ArrayList<OtherClubClass>();
                   
                   if(obj6[0]!=null){
                       
                       String otherclubfull = obj6[0].toString();
                       other_clubs_hidden_panel.setVisible(false);
                       memberSince_text.setVisible(false);
                       OtherClubMemno_text.setVisible(false);
                       jTextField34.setVisible(false);
                       String Str_Arr[]=otherclubfull.split("#");
                       for(int i=0;i<Str_Arr.length;i++){
                           String clubInfo = Str_Arr[i].toString();
                           String Clubinfo_arr[] = clubInfo.split("%");
                           if(Clubinfo_arr.length>0){
                               for(int j=0;j<Clubinfo_arr.length;j++)
                               {
                               if(j==0)
                               {
                               Clubname=Clubinfo_arr[0].toString();
                               }
                               if(j==1)
                               {
                              memno=Clubinfo_arr[1].toString();
                               }
                               if(j==2)
                               {
                               TypeofMember=Clubinfo_arr[2].toString();
                               }
                               if(j==3)
                               {
                               
                               City=Clubinfo_arr[3].toString();
                               }
                               if(j==4)
                               {
                               MemberSince=Clubinfo_arr[4].toString();
                               }
                               }
                               Date memSinceDate = null;
                               if(MemberSince!=null && MemberSince.trim().length()>0){
                                   memSinceDate = formatter.parse(MemberSince);
                               }
                               OtherClubClass clb=new OtherClubClass();
                               clb.setClubName(Clubname);
                               clb.setMemberno(memno);
                               clb.setTypeofMember(TypeofMember);
                               clb.setCity(City);
                               clb.setMemberSince(memSinceDate);
                               OtherClubClassListAll_Class.add(clb);
                               
                           }
                           
                       }
                        MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true);
                   }
                     else
                   {
                      MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true); 
                   }
                   
               }
                 else
               {
                   MemberFormTable_Model3=MemberFormTable_Model3.LoadOtherClubClassInfo(m_App,OtherClubClassListAll_Class);
                        OtherClubMember_JTable.setModel(MemberFormTable_Model3.getTableModelforOtherClubList(OtherClubClassListAll_Class));
                        OtherClubMember_JTable.setVisible(true);
               }
               
               Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT SD_SPORTS_DETAIL   FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id);
               SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();
               if(obj7!=null){
                   if(obj7[0]!=null){
                       String SD_Sportfull = obj7[0].toString();
                       if(SD_Sportfull.trim().length()>0){
                       String Str_Arr[]=SD_Sportfull.split("#");
                       for(int i=0;i<Str_Arr.length;i++){
                           String SD_SportsInfo[] = Str_Arr[i].split("%");
                           if(SD_SportsInfo.length>0){
                               String dname = SD_SportsInfo[0].toString();
                               String Sname = SD_SportsInfo[1].toString();
                               SD_Sports_Class cs = new SD_Sports_Class();
                        
                               cs.setSD_Name(dname);
                               cs.setSD_Sports(Sname);
                               
                               SD_Sports_ClassListAll.add(cs);
                               
                           }
                       }
                     MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
                  
                   }
                     
                   }
                   else
                   {
                        MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
                   }
                   
               }
               else
               {
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                     SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));  
                     SD_Sports_Jtable.setVisible(true);
               }
               SD_ClubActivity_ClassListAll=new ArrayList<SD_ClubActivity_Class>();
                 SD_RActivity_ClassListAll = new ArrayList<SD_RActivity_Class>();
               SD_Talent_ClassListAll = new ArrayList<SD_Talent_Class>();
               SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
               Object[] obj8 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT  SD_RACTIVITY,SD_FACILITY ,SD_TALENT,SD_CLUBACTIVITY FROM KYMMEMBER_ARV where deacRef=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{ Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE,Datas.SERIALIZABLE})).find(Id);
              
                if(obj8!=null){ 
                    String SD_name="";
                    String SD_Sports="";
                    String SD_Level="";
                    SD_RActivity_ClassListAll=new ArrayList<SD_RActivity_Class>();
                    if(obj8[0]!=null){
                        String x = obj8[0].toString();
                        if(x.trim().length()>0)
                        {
                        String SD_RacArr [] = x.split("#");
                        for(int i=0;i<SD_RacArr.length;i++){
                            String x1 = SD_RacArr[i].toString();
                            String SD_Arr[]=x1.split("%");
                            for(int j=0;j<SD_Arr.length;j++)
                            {
                                if(j==0)
                                {
                             SD_name = SD_Arr[0].toString();
                                }
                                if(j==1)
                                {
                             SD_Sports=SD_Arr[1].toString();
                                }
                                if(j==2)
                                {
                             SD_Level=SD_Arr[2].toString();
                                }
                            }
                            SD_RActivity_Class sd=new SD_RActivity_Class();
                            sd.setSD_Name(SD_name);
                            sd.setSD_Sports(SD_Sports);
                            sd.setSD_level(SD_Level);
                            SD_RActivity_ClassListAll.add(sd);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true);
                        }   
                        }

                         else
                        {
                           MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true); 
                        }
                    }
                    else
                    {
                        MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true); 
                    }
                    
                    if(obj8[1]!=null){
                        String SD_Name="";
                        String Facility="";
                        
                        String x = obj8[1].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            
                            String x1=StrArr[i].toString();
                            if(x1.trim().length()>0)
                            {
                            String SD_facArr[] = x1.split("%");
                            for(int j=0;j<SD_facArr.length;j++)
                            {
                                if(j==0)
                                {
                            SD_Name = SD_facArr[0].toString();
                                }
                                if(j==1)
                                {
                            Facility = SD_facArr[1].toString();
                                }
                            }
                            SD_Facility_Class sf = new SD_Facility_Class();
                            sf.setSD_Name(SD_Name);
                            sf.setSD_Facility(Facility);
                            SD_Facility_ClassListAll.add(sf);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                        }
                            else
                            {
                               MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);  
                            }
                        }
                        
                        
                    }
                    else
                    {
                        MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                    }
                     SD_Talent_ClassListAll=new ArrayList<SD_Talent_Class>();
                    if(obj8[2]!=null){
                        String SD_Name="";
                        String talent="";
                        String x = obj8[2].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            String x1=StrArr[i].toString();
                            if(x1.trim().length()>0)
                            {
                            String SD_TalArr[] = x1.split("%");
                            for(int j=0;j<SD_TalArr.length;j++)
                            {
                                if(j==0)
                                {
                            SD_Name = SD_TalArr[0].toString();
                                }
                                if(j==1)
                                {
                             talent = SD_TalArr[1].toString();
                                }
                            }
                            SD_Talent_Class sf = new SD_Talent_Class();
                            sf.setSD_Name(SD_Name);
                            sf.setSD_Talent(talent);
                            SD_Talent_ClassListAll.add(sf);
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);
                        }
                            else
                            {
                              MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);  
                            }
                        }
                        
                        
                    }
                    else
                    {
                        MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true);
                    }
                    SD_ClubActivity_ClassListAll=new ArrayList<SD_ClubActivity_Class>();
                    if(obj8[3]!=null){
                       String SD_Name="";
                       String SD_Activity="";
                        if(!obj8[3].equals("")){
                          String x = obj8[3].toString();
                          String StrArr[] = x.split("#");
                          for(int i=0;i<StrArr.length;i++){
                              String x1=StrArr[i].toString();
                              String SD_ActivityArr[] = x1.split("%");
                              if(SD_ActivityArr.length>0){
                                 for(int j=0;j<SD_ActivityArr.length;j++)
                                 {
                                     if(j==0)
                                     {
                                SD_Name = SD_ActivityArr[0].toString();
                                     }
                                     if(j==1)
                                     {
                                SD_Activity = SD_ActivityArr[1].toString();
                                     }
                                 }
                                SD_ClubActivity_Class sd = new SD_ClubActivity_Class();
                                sd.setSD_Name(SD_Name);
                                sd.setSD_Activity(SD_Activity);
                                SD_ClubActivity_ClassListAll.add(sd);
                                MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);
                              }
                          }  
                            
                        }
                        
                        else
                        {
                            MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                               SD_Facility_Jtable1.setVisible(true);
                        }
                    }
                    else
                    {
                         MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                          SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                          SD_Facility_Jtable1.setVisible(true);
                    }
                  }
                   else
                {
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                          SD_Facility_Jtable1.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                          SD_Facility_Jtable1.setVisible(true);
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                            SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                            SD_talent_Jtable.setVisible(true); 
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Facility_InfoAll(m_App,SD_Facility_ClassListAll);
                            SD_Facility_Jtable.setModel(MemberFormTable_Model.getSD_Facility_TableModel(SD_Facility_ClassListAll));
                            SD_Facility_Jtable.setVisible(true);
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                            SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                            SD_RActivity_Jtable.setVisible(true);       
                }
                   
                   
               
               // ***************** RECREATIONL ACTIVITES FOR SPOUSE/DEPENDENTS *****************************************
               
               
               
               
               
                SD_List_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                sports_SD_Combo.setModel(SD_List_ComboBoxValModel);
                SD_Ractivity_ComboBoxValModel = new ComboBoxValModel(SD_listAll);
                SD_RActivity_Combo.setModel(SD_Ractivity_ComboBoxValModel);
                SD_FacilitySpouse_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_FacilitySpouce_Combo.setModel(SD_FacilitySpouse_ComboBoxValModel);
                SD_Talent_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_talent_Combo.setModel(SD_Talent_ComboBoxValModel);
                
                
          }
          
       else
          {
               Object kymmember_deacRef_obj[] = (Object[]) new StaticSentence(m_App.getSession(), "SELECT id FROM KYMMEMBER where deacRef=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(kymmember_arv_deacRef);
              getAlreadyLoadedMemberInfo(kymmember_deacRef_obj[0].toString());
              next_data.setEnabled(false);
          }
        
      }
      catch(BasicException ex){
          Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
      }
      catch(ParseException ex){
          Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
      }
    }
   
}
