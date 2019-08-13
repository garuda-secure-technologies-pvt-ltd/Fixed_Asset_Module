
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
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.sms.EmailMaster;
import com.openbravo.pos.sms.EmailMasterTableForCreateGroup;
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
//added by pratima
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//ended
public class MemberForm extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    
    
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
    private List<String> IDProofTypeListSpouse = new ArrayList<String>();
    private List<String> IDProofTypeListChildren = new ArrayList<String>();
    
    private List<String> MemberShipTypeList = new ArrayList<String>();
    private List<String> SelectedFacilityList = new ArrayList<String>();
    private List<String> SelectedActivityList = new ArrayList<String>();
    private List<String> ChildrenIdProofListAll = new ArrayList<String>();
    private List<String> ChildrenBloodGrpListAll = new ArrayList<String>();
    private List<String> OtherClubNameListAll = new ArrayList<String>();
    private List<String> SD_listAll = new ArrayList<String>();
    private List<String> SD_ClubActivity_TempList = new ArrayList<String>();
    
    
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
     private ComboBoxValModel MemberBloodGroupBoxValModel;
    private ComboBoxValModel MotherBloodGroupBoxValModel;
    private ComboBoxValModel FatherIdProofBoxValModel;
    private ComboBoxValModel MotherIdProofBoxValModel;
    private ComboBoxValModel SD_List_ComboBoxValModel;
    private ComboBoxValModel SD_Ractivity_ComboBoxValModel;
    private ComboBoxValModel SD_FacilitySpouse_ComboBoxValModel;
    private ComboBoxValModel SD_FacilityName_ComboBoxValModel;
    private ComboBoxValModel SD_Talent_ComboBoxValModel;
    private ComboBoxValModel SD_ClubActSpouce_ComboBoxValModel;
    private ComboBoxValModel SD_ClubActName_ComboBoxValModel;
     private ComboBoxValModel Spouse_IdProofComboBoxModel;
     private ComboBoxValModel SpouseBloodGroupBoxValModel;

     
     
    private MemberFormTableModel MemberFormTable_Model;
    private MemberFormTableModel MemberFormTable_Model2;
    private MemberFormTableModel MemberFormTable_Model3;
    
    
   //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
    //added by pratima
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Pattern phnNoPattern = Pattern.compile("\\d{10}");
    Pattern namePattern = Pattern.compile("^\\pL+[\\pL\\pZ\\pP]{0,}$");
    //ended by pratima
    public MemberForm() {
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
        mname_text = new javax.swing.JTextField();
        FatherName_text = new javax.swing.JTextField();
        IDproofDoc_combo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idDocUniqueNo_text = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TypeofMember_combo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        SpouseName_text = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        DateOfMarriage_text = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        male_radio = new javax.swing.JRadioButton();
        female_radio = new javax.swing.JRadioButton();
        mno_text = new javax.swing.JTextField();
        memberSince_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        DOB_text = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        MemberName_text = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        memtype_label = new javax.swing.JLabel();
        SelectMember_Button = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        SpouseId_Combo = new javax.swing.JComboBox<>();
        IdProofMemberScan_Button = new javax.swing.JButton();
        IdProofSpouseScan_Button = new javax.swing.JButton();
        DocumentStatusMember_label = new javax.swing.JLabel();
        DocumentStatusSpouse_label = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        membcombo = new javax.swing.JComboBox<>();
        jLabel95 = new javax.swing.JLabel();
        spousebgcombo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        fatherDob_text = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        FatherBloodGroup_combo = new javax.swing.JComboBox();
        FatherPhoneNo_Text = new javax.swing.JTextField();
        FatherIdProof_combo = new javax.swing.JComboBox();
        FatherEmailId_Text = new javax.swing.JTextField();
        FatherUniqueNo_text = new javax.swing.JTextField();
        IdProofFatherScan_Button = new javax.swing.JButton();
        DocumentStatusFather_label = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        MotherName_text = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        MotherDOB_text = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        MotherBloodGroup_combo = new javax.swing.JComboBox();
        MotherIdProof_combo = new javax.swing.JComboBox();
        MotherPhoneNo_text = new javax.swing.JTextField();
        MotherEmailid_text = new javax.swing.JTextField();
        MotherUniqueNo_text = new javax.swing.JTextField();
        IdProofMotherScan_Button = new javax.swing.JButton();
        DocumentStatusMother_label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        ChildrenName_text = new javax.swing.JTextField();
        ChildrenDOB_text = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        ChildrenMale_radio = new javax.swing.JRadioButton();
        ChildrenFemale_radio = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ChildrenBlood_Combo = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        ChildrenPhone_text = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        ChildrenEmailId_text = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ChildrenUniqueNo_text = new javax.swing.JTextField();
        ChildrenIDProof_combo = new javax.swing.JComboBox();
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
        IdProofChildrenScan_Button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        Rline1Res_text = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        RLine2Res_text = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        ResPost_text = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        ResCity_combo = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        ResCountry_combo = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        ResState_Combo = new javax.swing.JComboBox();
        ResPinNo_text = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        offLine1_text = new javax.swing.JTextField();
        Off_line2_text = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        offCity_combo = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        Off_Country_combo = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        OffState_combo = new javax.swing.JComboBox();
        OffPinNo_text = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        OffPost_Text = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        MobileNo_text = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        Residence_no_text = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        OfficeNo_text = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        residence_Radio = new javax.swing.JRadioButton();
        Office_Radio = new javax.swing.JRadioButton();
        jLabel55 = new javax.swing.JLabel();
        Email_Text = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        Twitter_text = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        MobilePhoneUse_Combo = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        Proffesion_Combo = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        activities_combo = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        PlaySport_Text = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        SpecialTalent_text = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Activities_jList = new javax.swing.JList();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        OtherClubMemno_text = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        OtherClubMemberSince_text = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        OtherClubName_Combo = new javax.swing.JComboBox();
        OtherClubCity_combo = new javax.swing.JComboBox();
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
        jLabel96 = new javax.swing.JLabel();
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
        recreational_Sport_Text = new javax.swing.JTextField();
        recreational_level_text = new javax.swing.JTextField();
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
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sports_SD_Combo = new javax.swing.JComboBox();
        jLabel75 = new javax.swing.JLabel();
        SD_Sports_Textfeild = new javax.swing.JTextField();
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
        SD_Sports_RemoveButton = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        SD_RActivity_Combo = new javax.swing.JComboBox();
        jLabel77 = new javax.swing.JLabel();
        SD_RActivity_Textfeild = new javax.swing.JTextField();
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
        SD_RActivityLevel_Textfeild = new javax.swing.JTextField();
        SD_Ractivities_RemoveButton = new javax.swing.JButton();
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
        SD_Talent_RemoveButton = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        SD_ClubActSDName_Combo = new javax.swing.JComboBox();
        SD_Facility_FullAddButton1 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        SD_ClubActivity_Jtable = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        Save_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar(0,100);
        previousDetail_button = new javax.swing.JButton();
        nextDetail_button = new javax.swing.JButton();
        finalsubmit_button = new javax.swing.JButton();

        jLabel1.setText("Name of the Member : ");

        jLabel2.setText("Father's Name : ");

        jLabel3.setText("Identity Proof  : ");

        IDproofDoc_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel5.setText("Member No : ");

        jLabel6.setText("Id Document Unique No. : ");

        jLabel7.setText("Type Of Member : ");

        TypeofMember_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

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

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(204, 0, 0));
        jLabel85.setText("**");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(204, 0, 0));
        jLabel86.setText("**");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(204, 0, 0));
        jLabel87.setText("**");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(204, 0, 0));
        jLabel88.setText("**");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(204, 0, 0));
        jLabel89.setText("**");

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(204, 0, 0));
        jLabel90.setText("**");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(204, 0, 0));
        jLabel91.setText("**");

        jLabel92.setText("Data as per record");

        memtype_label.setText("jLabel93");

        SelectMember_Button.setText("Select Member ");
        SelectMember_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectMember_ButtonActionPerformed(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel93.setText("Spouse ID Proof ");

        SpouseId_Combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        IdProofMemberScan_Button.setText("Save Document");
        IdProofMemberScan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProofMemberScan_ButtonActionPerformed(evt);
            }
        });

        IdProofSpouseScan_Button.setText("Save Document");
        IdProofSpouseScan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProofSpouseScan_ButtonActionPerformed(evt);
            }
        });

        DocumentStatusMember_label.setText("document ");

        DocumentStatusSpouse_label.setText("document ");

        jLabel94.setText("Member Blood Group:");

        membcombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        membcombo.setSelectedItem(null);

        jLabel95.setText("Spouse Blood Group:");

        spousebgcombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        spousebgcombo.setSelectedItem(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel92)
                .addGap(249, 249, 249))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel90))
                                    .addComponent(jLabel93))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(memberSince_text, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12))
                                            .addComponent(SpouseName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SpouseId_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(DateOfMarriage_text, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TypeofMember_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(idDocUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel86))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel87))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel85))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel88))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel89)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(IDproofDoc_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(DOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel14))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(mno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(SelectMember_Button)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(mname_text)
                                            .addComponent(FatherName_text, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(membcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel95)
                                        .addGap(33, 33, 33)
                                        .addComponent(spousebgcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(IdProofMemberScan_Button)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(IdProofSpouseScan_Button)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(DocumentStatusSpouse_label, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                            .addComponent(DocumentStatusMember_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(200, 200, 200))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel91)))
                        .addGap(386, 386, 386)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(MemberName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(memtype_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(male_radio)
                                .addGap(45, 45, 45)
                                .addComponent(female_radio)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(mno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(MemberName_text)
                                .addComponent(jLabel85)
                                .addComponent(memtype_label)
                                .addComponent(SelectMember_Button))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(mname_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel94)
                            .addComponent(membcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(FatherName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(DOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel88))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(IDproofDoc_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(IdProofMemberScan_Button)
                    .addComponent(DocumentStatusMember_label))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(idDocUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TypeofMember_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(memberSince_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel91)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(male_radio)
                        .addComponent(female_radio)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(SpouseName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95)
                    .addComponent(spousebgcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpouseId_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93)
                    .addComponent(IdProofSpouseScan_Button)
                    .addComponent(DocumentStatusSpouse_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(DateOfMarriage_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(53, 53, 53))
        );

        mno_text.setEditable(false);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N

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

        FatherBloodGroup_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        FatherIdProof_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        IdProofFatherScan_Button.setText("Save Document");
        IdProofFatherScan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProofFatherScan_ButtonActionPerformed(evt);
            }
        });

        DocumentStatusFather_label.setText("No Document");

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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FatherPhoneNo_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(FatherIdProof_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, 225, Short.MAX_VALUE)
                            .addComponent(FatherBloodGroup_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DocumentStatusFather_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IdProofFatherScan_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(36, 36, 36)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel30)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(fatherDob_text, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17))
                    .addComponent(FatherEmailId_Text)
                    .addComponent(FatherUniqueNo_text, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(fatherDob_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(FatherBloodGroup_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(FatherPhoneNo_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FatherEmailId_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(FatherIdProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FatherUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdProofFatherScan_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(DocumentStatusFather_label))
        );

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

        MotherBloodGroup_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        MotherIdProof_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        IdProofMotherScan_Button.setText("Save Document");
        IdProofMotherScan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProofMotherScan_ButtonActionPerformed(evt);
            }
        });

        DocumentStatusMother_label.setText("No Document");

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
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MotherName_text)
                    .addComponent(MotherBloodGroup_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MotherIdProof_combo, 0, 244, Short.MAX_VALUE)
                    .addComponent(MotherPhoneNo_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DocumentStatusMother_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IdProofMotherScan_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71))
                .addGap(4, 4, 4)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(MotherDOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addComponent(MotherEmailid_text)
                    .addComponent(MotherUniqueNo_text, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MotherName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(MotherDOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(MotherBloodGroup_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel70)
                    .addComponent(MotherPhoneNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MotherEmailid_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel71)
                    .addComponent(MotherIdProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MotherUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdProofMotherScan_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(DocumentStatusMother_label))
        );

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
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Parents Info", jPanel2);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("Name : ");

        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("Eg. (dd/mm/yyyy)");

        ChildrenMale_radio.setText("Male");

        ChildrenFemale_radio.setText("Female");

        jLabel22.setText("Date of Birth");

        jLabel23.setText("Blood Group : ");

        ChildrenBlood_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel24.setText("Phone No: ");

        ChildrenPhone_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChildrenPhone_textActionPerformed(evt);
            }
        });

        jLabel25.setText("Email Id: ");

        jLabel26.setText("ID Proof :");

        jLabel27.setText("Unique No : ");

        ChildrenIDProof_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

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

        IdProofChildrenScan_Button.setText("Save Document");
        IdProofChildrenScan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProofChildrenScan_ButtonActionPerformed(evt);
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
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ChildrenName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(ChildrenMale_radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(ChildrenFemale_radio))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel24))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ChildrenPhone_text, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ChildrenEmailId_text, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ChildrenUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ChildrenIDProof_combo, 0, 227, Short.MAX_VALUE)
                                    .addComponent(ChildrenBlood_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ChildrenAdd_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ChildrenDOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(IdProofChildrenScan_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ChildRemove_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32)))
                        .addGap(0, 39, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(ChildrenName_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChildrenDOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChildrenMale_radio)
                    .addComponent(ChildrenFemale_radio)
                    .addComponent(ChildrenBlood_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(ChildrenPhone_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(ChildrenIDProof_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(ChildrenEmailId_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(ChildrenUniqueNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChildrenAdd_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChildRemove_Button)
                    .addComponent(IdProofChildrenScan_Button))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Children Info", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Residential Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel37.setText("Line 1 : ");

        jLabel38.setText("Line 2 : ");

        jLabel41.setText("Post : ");

        jLabel42.setText("City : ");

        ResCity_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel43.setText("Country : ");

        ResCountry_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel44.setText("State : ");

        jLabel45.setText("Pin : ");

        ResState_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Rline1Res_text, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResPost_text, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RLine2Res_text, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ResCity_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ResCountry_combo, 0, 231, Short.MAX_VALUE))
                                .addGap(124, 124, 124)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel44)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel45)
                                        .addGap(29, 29, 29)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ResPinNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ResState_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(Rline1Res_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResPost_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(RLine2Res_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(ResCity_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(ResState_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(ResCountry_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(ResPinNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Office Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel39.setText("Line 1 : ");

        jLabel40.setText("Line 2 : ");

        jLabel46.setText("City : ");

        offCity_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel47.setText("Country : ");

        Off_Country_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel48.setText("State : ");

        jLabel49.setText("Pin : ");

        OffState_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel50.setText("Post : ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(offCity_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Off_Country_combo, 0, 229, Short.MAX_VALUE))
                        .addGap(128, 128, 128)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OffPinNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OffState_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(offLine1_text, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Off_line2_text, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OffPost_Text, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(offLine1_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(OffPost_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(Off_line2_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(offCity_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(OffState_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(Off_Country_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(OffPinNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Email_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Twitter_text))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(residence_Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Office_Radio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MobileNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Residence_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel53)
                        .addGap(27, 27, 27)
                        .addComponent(OfficeNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(MobileNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(Residence_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(OfficeNo_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(residence_Radio)
                    .addComponent(Office_Radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(Email_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(Twitter_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jTabbedPane1.addTab("Address Detail", jPanel4);

        jLabel57.setText("Which Mobile Phone do you use ?  ");

        MobilePhoneUse_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabel58.setText("What is your profession ? ");

        Proffesion_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpecialTalent_text, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MobilePhoneUse_Combo, 0, 192, Short.MAX_VALUE)
                                    .addComponent(Proffesion_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel59))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(activities_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PlaySport_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(MobilePhoneUse_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(Proffesion_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(PlaySport_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(SpecialTalent_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jTabbedPane1.addTab("Other Info", jPanel8);

        jLabel64.setText("Membership of other clubs : ");

        jLabel65.setText("Name of club : ");

        jLabel66.setText("City : ");

        jLabel67.setText("Member No: ");

        jLabel68.setText("Type of member : ");

        jLabel69.setText("Member since : ");

        jButton6.setText("Add Club Details");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        OtherClubName_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        OtherClubCity_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

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

        jLabel96.setText("Eg. (dd/mm/yyyy)");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68)
                            .addComponent(jLabel66)
                            .addComponent(jLabel69))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OtherClubName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(OtherClubCity_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, 260, Short.MAX_VALUE)
                                        .addComponent(OtherClubMemno_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField34, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(OtherClubMemberSince_text, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel64)))
                .addGap(46, 46, 46))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator2)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(OtherClubName_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(OtherClubMemno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(OtherClubCity_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(OtherClubMemberSince_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jLabel96))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("other Clubs", jPanel9);

        jLabel72.setText("Which are the optional facilities that you have opted for ?  ");

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
                                .addComponent(jScrollPane3))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(recreational_Sport_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(recreational_level_text, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9)
                            .addComponent(Recractivity_remove_btn))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(FacilityOption_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(facilityRemove_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(facilityAdd_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(recreational_Sport_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recreational_level_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Recractivity_remove_btn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(34, Short.MAX_VALUE))
        );

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

        SD_Sports_RemoveButton.setText("Remove");
        SD_Sports_RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Sports_RemoveButtonActionPerformed(evt);
            }
        });

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
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sports_SD_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(SD_Sports_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(SD_Sports_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SD_Sports_RemoveButton)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SD_Sports_RemoveButton))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(sports_SD_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel75)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(SD_Sports_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SD_Sports_AddButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

        SD_Ractivities_RemoveButton.setText("Remove");
        SD_Ractivities_RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Ractivities_RemoveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SD_Ractivities_RemoveButton))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(SD_RActivityLevel_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(SD_Ractivity_AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SD_RActivity_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SD_RActivity_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SD_Ractivities_RemoveButton))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(SD_RActivity_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel77)
                            .addComponent(SD_RActivity_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78)
                            .addComponent(SD_RActivityLevel_Textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SD_Ractivity_AddButton))
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

        SD_Talent_RemoveButton.setText("Remove");
        SD_Talent_RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SD_Talent_RemoveButtonActionPerformed(evt);
            }
        });

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
                .addGap(18, 18, 18)
                .addComponent(SD_Talent_RemoveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SD_Talent_RemoveButton))
                    .addGroup(jPanel18Layout.createSequentialGroup()
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
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

        SD_ClubActivity_Jtable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(SD_ClubActivity_Jtable);

        jLabel84.setText("Select club activity :  ");

        SD_ClubAct_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        SD_ClubAct_Jlist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane13.setViewportView(SD_ClubAct_Jlist);

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
                                .addContainerGap(24, Short.MAX_VALUE))))))
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
                .addContainerGap(36, Short.MAX_VALUE))
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Spouse / Dependants", jPanel11);

        Save_button.setText("Save");
        Save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_buttonActionPerformed(evt);
            }
        });

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

        previousDetail_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousDetail_buttonActionPerformed(evt);
            }
        });

        finalsubmit_button.setText("Final Submit");
        finalsubmit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalsubmit_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(previousDetail_button, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(nextDetail_button, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)
                            .addGap(54, 54, 54)
                            .addComponent(Save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(finalsubmit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Save_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalsubmit_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(previousDetail_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nextDetail_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        previousDetail_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png")));
        nextDetail_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png")));
    }// </editor-fold>//GEN-END:initComponents

    String MemberNo;
    String MemberName;
    String MemberShipType;
    String MemberBg;
    
    String IDProofDoc;
    Date MemberDOB;
    Date MemberSince;
    String DocumentUniqueNo;
    String Gender;
    String SpouceName;
    String SpouceBg;

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
    
    String SD_Activity_FullStr ;
    Object SD_Activity_Object;
    String SpouseID;
    
    private void Save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_buttonActionPerformed
        if(mno_text.getText()!=null && mno_text.getText().trim().length()>0){
          if(mname_text.getText()!=null && mname_text.getText().trim().length()>0 && nameMatch(mname_text.getText().trim())){//
             if(FatherName_text.getText()!=null && FatherName_text.getText().trim().length()>0 && nameMatch(FatherName_text.getText().trim())){//
                if(DOB_text.getText()!=null && DOB_text.getText().trim().length()>0){
                    if(IDproofDoc_combo.getSelectedIndex()!=-1){
                        if(TypeofMember_combo.getSelectedIndex()!=-1){
                            if(memberSince_text.getText().trim().length()>0){
                                if(nameMatch(MotherName_text.getText())){
                                    if(nameMatch(SpouseName_text.getText().trim())){
                                if(phonNumberMatch(FatherPhoneNo_Text.getText().trim())){
                                 if(phonNumberMatch(MotherPhoneNo_text.getText())){
                                     if(phonNumberMatch(MobileNo_text.getText().trim())){
                             SD_Ractivity_Properties = new Properties();
                             MemberNo=mno_text.getText().trim();
                             if(spousebgcombo.getSelectedIndex()!=-1){
                             SpouceBg=spousebgcombo.getSelectedItem().toString();
                            }
                             else SpouceBg=null;
                             if(membcombo.getSelectedIndex()!=-1){
                             MemberBg=membcombo.getSelectedItem().toString();
                             }
                            else MemberBg=null;
                             MemberName=mname_text.getText().trim();
                            MemberShipType = TypeofMember_combo.getSelectedItem().toString();
                            IDProofDoc=IDproofDoc_combo.getSelectedItem().toString();
                        
                            try {
                            
                            // **************************** DATE FORMATS ******************
                            MemberDOB=(Date) formatter.parse(DOB_text.getText());
                           
                            MemberSince=(Date) formatter.parse(memberSince_text.getText());
                            if(DateOfMarriage_text!=null && DateOfMarriage_text.getText().trim().length()>0){
                                MarriageDate=(Date) formatter.parse(DateOfMarriage_text.getText());
                            }
                            if(fatherDob_text!=null && fatherDob_text.getText().trim().length()>0){
                                FatherDOB=(Date) formatter.parse(fatherDob_text.getText());
                                                              
                            }
                            if(MotherDOB_text!=null && MotherDOB_text.getText().trim().length()>0){
                                MotherDOB = (Date) formatter.parse(MotherDOB_text.getText());
                                 
                                 
                            }
                            // **************************** DATE FORMATS ENDS ******************
                            
                            
                            // **************************** PARENTS INFO ******************
                            
                            FatherName=FatherName_text.getText();
                            MotherName=MotherName_text.getText();
                            if(FatherBloodGroup_combo.getSelectedIndex()!=-1){
                                FatherBloodGroup=FatherBloodGroup_combo.getSelectedItem().toString();
                            }
                            if(MotherBloodGroup_combo.getSelectedIndex()!=-1){
                                MotherBloodGroup=MotherBloodGroup_combo.getSelectedItem().toString();
                            }
                            FatherPhoneNo=FatherPhoneNo_Text.getText().trim();
                            MotherPhoneNo=MotherPhoneNo_text.getText().trim();
                            FatherEmailID=FatherEmailId_Text.getText().trim();
                            MotherEmailid=MotherEmailid_text.getText().trim();
                            if(FatherIdProof_combo.getSelectedIndex()!=-1){
                                FatherIDProof=FatherIdProof_combo.getSelectedItem().toString();
                            }
                            if(MotherIdProof_combo.getSelectedIndex()!=-1){
                                MotherIdProof=MotherIdProof_combo.getSelectedItem().toString();
                            }
                            FatherUniqueNo=FatherUniqueNo_text.getText().trim();
                            MotherUniqueNo=MotherUniqueNo_text.getText().trim();
                          
                            
                            
                            // **************************** PARENTS INFO ENDS******************
                        
                            
                            
                            DocumentUniqueNo=idDocUniqueNo_text.getText().trim();
                            if(male_radio.isSelected()){
                                Gender="Male";
                            }
                            else{
                                Gender="Female";
                            }

                            SpouceName=SpouseName_text.getText().trim();
                            
                            
                            // ************** RECREATIONAL ACTIVITY LOOP *********************
                            RecreationalActivityStr="";
                            for(int i=0;i<RecreationalActivityList.size();i++){
                                String slno=RecreationalActivityList.get(i).GetSlNo().toString();
                                String ActivityName=RecreationalActivityList.get(i).GetActivityName().toString();
                                String Level=RecreationalActivityList.get(i).GetActivityLevel().toString();
                                String t = slno+"%"+ActivityName+"%"+Level;
                                RecreationalActivityStr=RecreationalActivityStr+t+"#";
                            }
                            // ************** RECREATIONAL ACTIVITY ENDS *********************
                            
                            
                            
                            // *************** FACILITY SELECTED LOOP ************************* 
                            FacilitiesOpted="";
                            for(int i=0;i<SelectedFacilityList.size();i++){
                                String fac = SelectedFacilityList.get(i).toString();
                                FacilitiesOpted=FacilitiesOpted+fac+"#";
                            }
                            // *************** FACILITY SELECTED LOOP ENDS  ************************* 
                            
                            
                            
                            // *************** OTHER INFO PANEL PROCESS ******************************* 
                            if(MobilePhoneUse_Combo.getSelectedIndex()!=-1){
                                MobilePhoneUse=MobilePhoneUse_Combo.getSelectedItem().toString();
                            }
                            if(Proffesion_Combo.getSelectedIndex()!=-1){
                               Proffesion=Proffesion_Combo.getSelectedItem().toString();
                            }
                            PlaySport=PlaySport_Text.getText().trim();
                            SpecialTalent=SpecialTalent_text.getText().trim();
                            ClubActivity="";
                            for(int i=0;i<SelectedActivityList.size();i++){
                                String act=SelectedActivityList.get(i).toString();
                                ClubActivity=ClubActivity+act+"#";
                            }
                            // *************** OTHER INFO PANEL PROCESS ENDS ******************************* 
                            
                            
                            
                            // *****************  ADDRESS DETIALS **************************************** 
                            String Rline1=""+Rline1Res_text.getText();
                            String RLine2=""+RLine2Res_text.getText();
                            String RPost=""+ResPost_text.getText();
                            String RCity="";
                            if(ResCity_combo.getSelectedIndex()!=-1)
                            {
                                RCity=ResCity_combo.getSelectedItem().toString();
                            }
                            String RState="";
                            if(ResState_Combo.getSelectedIndex()!=-1){
                                RState=ResState_Combo.getSelectedItem().toString();
                            }
                            String RCountry="";
                            if(ResCountry_combo.getSelectedIndex()!=-1){
                                RCountry=ResCountry_combo.getSelectedItem().toString();
                            }
                            String RPinNo=""+ResPinNo_text.getText();
                            if(RPinNo.equals("")){
                                RPinNo="0";
                            }
                            ResidentialAddress=Rline1+"#"+RLine2+"#"+RPost+"#"+RCity+"#"+RState+"#"+RCountry+"#"+RPinNo+"#";
                            
                            
                            String OFline1=""+offLine1_text.getText();
                            String Ofine2=""+Off_line2_text.getText();
                            String OfPost=""+OffPost_Text.getText();
                            String OfCity="";
                            if(offCity_combo.getSelectedIndex()!=-1)
                            {
                                OfCity=offCity_combo.getSelectedItem().toString();
                            }
                            String OfState="";
                            if(OffState_combo.getSelectedIndex()!=-1){
                                OfState=OffState_combo.getSelectedItem().toString();
                            }
                            String OfCountry="";
                            if(Off_Country_combo.getSelectedIndex()!=-1){
                                OfCountry=Off_Country_combo.getSelectedItem().toString();
                            }
                            String OfPinNo=""+OffPinNo_text.getText();
                            if(OfPinNo.equals("")){
                                OfPinNo="0";
                            }
                            OfficeAddress=OFline1+"#"+Ofine2+"#"+OfPost+"#"+OfCity+"#"+OfState+"#"+OfCountry+"#"+OfPinNo+"#";
                            
                            MobileNo=MobileNo_text.getText().trim();
                            ResidenceNo=Residence_no_text.getText().trim();
                            OfficeNo=OfficeNo_text.getText().toString();
                            if(residence_Radio.isSelected()){
                                CommunicationAddress="Residence";
                            }
                            else{
                                CommunicationAddress="Office";
                            }
                            EmailId=Email_Text.getText().toString();
                            Twitter=Twitter_text.getText().toString();
                            // **************************************** ADDRESS DETAILS ENDS *****************************************************
                            
                            
                            
                            // ******************************************** CHILDREN DETAILS *******************************************************
                            
                            ChildrenDetailedStr="";
                            for(int i=0;i<ChildrenClassList.size();i++){
                                String Cname=""+ChildrenClassList.get(i).GetName().toString();
                                String CGender=""+ChildrenClassList.get(i).GetGender().toString();
                                String CBloodGrp=""+ChildrenClassList.get(i).GetBloodGrp().toString();
                                String CIdProof=""+ChildrenClassList.get(i).GetIDProof().toString();
                                String CPhoneNo=""+ChildrenClassList.get(i).GetPhoneNo().toString();
                                String CEmailId=""+ChildrenClassList.get(i).GetEmailID().toString();
                                String CUniqueNo=""+ChildrenClassList.get(i).GetUniqueNo().toString();
                                String Cdob=""+ChildrenClassList.get(i).GetDateOfBirth().toString();
                                String Child=Cname+"%"+CGender+"%"+CBloodGrp+"%"+CIdProof+"%"+CPhoneNo+"%"+CEmailId+"%"+CUniqueNo+"%"+Cdob;
                                ChildrenDetailedStr=ChildrenDetailedStr+Child+"#";
                            }
                            // ******************************************** CHILDREN DETAILS ENDS  *******************************************************
                            
                            
                           // ******************************************** OTHER CLUB INFO  ******************************************************* 
                            OtherClubInfoStr="";
                            for(int i=0;i<OtherClubClassListAll_Class.size();i++){
                                String ClubName=OtherClubClassListAll_Class.get(i).GetClubName();
                                String otherMemno=OtherClubClassListAll_Class.get(i).GetMemberno();
                                String typeofmember=OtherClubClassListAll_Class.get(i).GetTypeofMember();
                                String City=OtherClubClassListAll_Class.get(i).GetCity();
                                String Membersince=OtherClubClassListAll_Class.get(i).GetMemberSince();
                                String Otherclubinfo=ClubName+"%"+otherMemno+"%"+typeofmember+"%"+City+"%"+Membersince;
                                OtherClubInfoStr=OtherClubInfoStr+Otherclubinfo+"#";
                            } 
                            
                            // ****************************************  SD SAPORTS DETIALS ************************************************
                            
                            SD_Sports_FullStr="";
                            for(int i=0;i<SD_Sports_ClassListAll.size();i++){
                                String SD_Name = SD_Sports_ClassListAll.get(i).GetSD_Name().toString();
                                String SD_Sports = SD_Sports_ClassListAll.get(i).GetSD_Sports().toString();
                                String tempstr=SD_Name+"%"+SD_Sports;
                                SD_Sports_FullStr=SD_Sports_FullStr+tempstr+"#";
                            }
                            SD_RActivity_FullStr="";
                            for(int i=0;i<SD_RActivity_ClassListAll.size();i++){
                                String SD_name=SD_RActivity_ClassListAll.get(i).GetSD_Name();
                                String SD_ActivityName = SD_RActivity_ClassListAll.get(i).GetSD_Sports();
                                String SD_level = SD_RActivity_ClassListAll.get(i).GetSD_level();
                                String t = SD_name+"%"+SD_ActivityName+"%"+SD_level;
                                SD_RActivity_FullStr=SD_RActivity_FullStr+t+"#";
                            }
                            oj = (Object) SD_RActivity_FullStr;
                            
                            SD_Facility_FullStr="";
                            for(int i=0;i<SD_Facility_ClassListAll.size();i++){
                                String Sd_Name = SD_Facility_ClassListAll.get(i).GetSD_Name();
                                String Sd_Facility = SD_Facility_ClassListAll.get(i).GetSD_Facility();
                                String x = Sd_Name+"%"+Sd_Facility;
                                SD_Facility_FullStr=SD_Facility_FullStr+x+"#";
                            }
                             SD_Facility_Object = (Object) SD_Facility_FullStr;
                            
                            SD_Talent_FullStr="";
                            for(int i=0;i<SD_Talent_ClassListAll.size();i++){
                                String sd_name = SD_Talent_ClassListAll.get(i).GetSD_Name();
                                String Sd_talent = SD_Talent_ClassListAll.get(i).GetSD_Talent();
                                String t = sd_name+"%"+Sd_talent;
                                SD_Talent_FullStr=SD_Talent_FullStr+t+"#";
                            }
                            SD_Talent_Object = (Object) SD_Talent_FullStr; 
                             
                            SD_Activity_FullStr = "";
                            for(int i=0;i<SD_ClubActivity_ClassListAll.size();i++){
                                String sd_name = SD_ClubActivity_ClassListAll.get(i).GetSD_Name().toString();
                                String ClubActivityName = SD_ClubActivity_ClassListAll.get(i).GetSD_Activity().toString();
                                String t = sd_name+"%"+ClubActivityName;
                                SD_Activity_FullStr=SD_Activity_FullStr+t+"#";
                            } 
                            SD_Activity_Object=(Object) SD_Activity_FullStr;
                             
                            if(SpouseId_Combo.getSelectedIndex()!=-1){
                              SpouseID = SpouseId_Combo.getSelectedItem().toString();
                            }
                            
                            
                            
                            Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                                 @Override      
                                 protected Object transact() throws BasicException {   

                                     
                                     Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM KYMMEMBER where MEMBERNO=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(MemberNo);
                                     if(obj!=null){
                                         DeactivatedRef=obj[0].toString();
                                         
                                     }
                                     

                                     int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO kymmember (ID ,memberno , membername , DATEOFBIRTH , IDPROOF , IDUNIQUENO , MEMBERTYPE , MEMBERSINCE , SPOUCENAME , DATEOFMARRIAGE , GENDER ,FATHERNAME ,FATHERDOB,MOTHERNAME,MOTHERDOB,RecreationalActivities,FACILITIES,MOBILETYPE,PROFFESION,PLAYSPORTS,SpecialTalent,CLUBACTIVITY,RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER,DEPENDENTDETAILS,OTHERCLUB,FATHERBG,FATHERPHONENO,FATHEREMAILID,FATHERIDPROOF,FATHERUNIQUENO,MOTHERBG,MOTHERPHONENO,MOTHEREMAILID,MOTHERIDPROOF,MOTHERUNIQUENO,CRBY,CRDATE,CRHOST,deacRef,SD_SPORTS_DETAIL,SD_RACTIVITY,SD_FACILITY,SD_TALENT,SD_CLUBACTIVITY , finalsubmit , spouseidproof, SPOUSE_BG ,MEMBER_BG ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.TIMESTAMP, Datas.STRING ,Datas.STRING  , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.TIMESTAMP,Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING,Datas.STRING ,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING ,Datas.STRING,Datas.SERIALIZABLE , Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE , Datas.INT , Datas.STRING ,Datas.STRING, Datas.STRING })                         
                                      ).exec(new Object[]{UUID.randomUUID().toString(), MemberNo ,MemberName , MemberDOB , IDProofDoc ,DocumentUniqueNo,MemberShipType, MemberSince , SpouceName , MarriageDate , Gender , FatherName,FatherDOB,MotherName,MotherDOB,RecreationalActivityStr,FacilitiesOpted,MobilePhoneUse,Proffesion,PlaySport,SpecialTalent,ClubActivity,ResidentialAddress ,OfficeAddress , MobileNo,ResidenceNo,OfficeNo,CommunicationAddress,EmailId,Twitter,ChildrenDetailedStr,OtherClubInfoStr,FatherBloodGroup,FatherPhoneNo,FatherEmailID,FatherIDProof,FatherUniqueNo,MotherBloodGroup,MotherPhoneNo,MotherEmailid,MotherIdProof,MotherUniqueNo ,m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() ,DeactivatedRef ,SD_Sports_FullStr,oj , SD_Facility_Object ,SD_Talent_Object,SD_Activity_Object , 0 , SpouseID ,SpouceBg, MemberBg });                                                                                                


                                     
                                      new PreparedSentence(m_App.getSession()
                                            , "INSERT INTO KYMMEMBER_ARV SELECT *,?,?,? FROM KYMMEMBER WHERE ID=? "
                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP , Datas.STRING })).exec(new Object[]{  m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost(),new Date() ,DeactivatedRef   });


                                      new PreparedSentence(m_App.getSession()
                                            , "DELETE FROM KYMMEMBER WHERE ID=? "
                                            , new SerializerWriteBasic(new Datas[]{Datas.STRING })).exec(new Object[]{ DeactivatedRef   });

                                      
                                      

                                     return null;                                      
                                         }                            
                                     };                 


                            try {                 

                                t.execute();          
                                JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                reset();
                                loaddata();
                                mno_text.setText(null);
                            }
                            catch (BasicException ex) {                    
                                Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                                ex.printStackTrace();
                                new MessageInf(ex).show(new JFrame());
                            } 
                           } catch (ParseException ex) {
                            Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                           }
                         } } } } }//PhoneNumber validation if breaket closed
                        } else{
                                JOptionPane.showMessageDialog(this, "Please Fill Date in Member Since.  ", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                    } else{
                         JOptionPane.showMessageDialog(this, "Please enter Membership Type.  ", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    //added by pratima
                }else{
                        JOptionPane.showMessageDialog(this, "Please select Identity Proof Type.  ", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
            }else{
                    JOptionPane.showMessageDialog(this, "Please enter Member's Date of birth .  ", "Warning", JOptionPane.WARNING_MESSAGE);
                }
        }else{
                JOptionPane.showMessageDialog(this, "Please enter Father's Name .  ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
    }else{
            JOptionPane.showMessageDialog(this, "Please enter Member Name.  ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
}else{ 
     JOptionPane.showMessageDialog(this, "Please enter Membership No.  ", "Warning", JOptionPane.WARNING_MESSAGE);
      }
    }//GEN-LAST:event_Save_buttonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
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
                Facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedFacilityList));
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
            Facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedFacilityList));
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
                Activities_jList.setModel(new MemberForm.ItemsListModel(SelectedActivityList));
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
            Activities_jList.setModel(new MemberForm.ItemsListModel(SelectedActivityList));
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void ChildrenAdd_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChildrenAdd_buttonActionPerformed
        if(ChildrenName_text.getText()!=null && ChildrenName_text.getText().trim().length()>0){
           if(ChildrenDOB_text.getText()!=null && ChildrenDOB_text.getText().trim().length()>0){ 
              if(ChildrenIDProof_combo.getSelectedIndex()!=-1){
                 if(phonNumberMatch(ChildrenPhone_text.getText())){
                     if(nameMatch(ChildrenName_text.getText().trim())){
               try{    
                  String Name = ChildrenName_text.getText().trim();
                 String Cgender="";
                 if(ChildrenMale_radio.isSelected()){
                     Cgender="Male";
                 }
                 else{
                     Cgender="Female";
                 }
                 
                 String BloodGrp="";
                 if(ChildrenBlood_Combo.getSelectedIndex()!=-1){
                     BloodGrp=ChildrenBlood_Combo.getSelectedItem().toString();
                 } 
                 String idProof="";
                 if(ChildrenIDProof_combo.getSelectedIndex()!=-1){
                     idProof=ChildrenIDProof_combo.getSelectedItem().toString();
                 }
                 String PhoneNo=ChildrenPhone_text.getText().trim();
                 String EmailId=ChildrenEmailId_text.getText().trim();
                 String Uniqueno=ChildrenUniqueNo_text.getText().trim();
                 Date ChildDate=new Date(); 
                 
                 
                 
                     ChildDate=(Date) formatter.parse(ChildrenDOB_text.getText());
                     
                     ChildrenClass cl = new ChildrenClass();
                     cl.setName(Name);
                     cl.setGender(Cgender);
                     cl.setBloodGrp(BloodGrp);
                     cl.setIDProof(idProof);
                     cl.setPhoneNo(PhoneNo);
                     cl.setEmailID(EmailId);
                     cl.setUniqueNo(Uniqueno);
                     cl.setDateOfBirth(ChildDate);
                     
                     ChildrenClassList.add(cl);
                     
                     MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                     children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                     
                     children_Jtable.setVisible(true);
                     
                     JOptionPane.showMessageDialog(this, "Children detail added successfully. ", "Success", JOptionPane.INFORMATION_MESSAGE);
                     
                     
                     ChildrenName_text.setText(null);
                     ChildrenDOB_text.setText(null);
                     ChildrenPhone_text.setText(null);
                     ChildrenEmailId_text.setText(null);     
                     ChildrenUniqueNo_text.setText(null);        
                     ChildrenBlood_Combo.setSelectedIndex(-1);
                     ChildrenIDProof_combo.setSelectedIndex(-1);
                     ChildrenMale_radio.setSelected(true);
                             
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
               } }//phonNumbervalidation if-ended
           }
              else{
                  JOptionPane.showMessageDialog(this, "Select ID Proof .", "Warning", JOptionPane.WARNING_MESSAGE);
              }
           }
           else{
               JOptionPane.showMessageDialog(this, "Date of Birth cannot be null", "Warning", JOptionPane.WARNING_MESSAGE);
           }
        }
        else{
            JOptionPane.showMessageDialog(this, "Name cannot be null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ChildrenAdd_buttonActionPerformed

    private void ChildrenPhone_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChildrenPhone_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChildrenPhone_textActionPerformed

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
              if(jTextField34.getText()!=null && jTextField34.getText().trim().length()>0){   
                if(OtherClubMemberSince_text.getText()!=null && OtherClubMemberSince_text.getText().trim().length()>0){
                
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
                     JOptionPane.showMessageDialog(this, "Enter Member since date ", "Warning", JOptionPane.WARNING_MESSAGE);
                }
              
              }
              else{
                  JOptionPane.showMessageDialog(this, "Enter Membership type", "Warning", JOptionPane.WARNING_MESSAGE);
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
    
    String DeactRefIDForHistory=null;
    
    private void mno_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mno_textKeyReleased
         if(mno_text.getText()!=null && mno_text.getText().trim().length()>0){
            String memno=mno_text.getText().trim();
            try{
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT c.NAME , m.name , c.visible  FROM CUSTOMERS c , memtype m  where c.SEARCHKEY=?  and c.memtype=m.id ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.INT })).find(memno);
                if(obj!=null){
                    String memberOrgname = obj[0].toString();
                    
                    MemberName_text.setText(memberOrgname);
                    MemberName_text.setVisible(true);
                    MemberName_text.setForeground(Color.BLUE);
                    Save_button.setEnabled(true);
                    
                    String MemtypeT = obj[1].toString();
                    memtype_label.setText("Mem. Type : "+MemtypeT);
                    
                    // ************** CHECK IN KNOW YOUR MEMBER LIST ******************************************************* 
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM KYMMEMBER where MEMBERNO=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                    if(obj1!=null){
                        getAlreadyLoadedMemberInfo(obj1[0].toString());
                        jProgressBar1.setForeground(Color.RED);
                        jProgressBar1.setValue(25);
                        
                        Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT deacref FROM KYMMEMBER where MEMBERNO=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                        if(obj2!=null){
                            if(obj2[0]!=null){
                                DeactRefIDForHistory = obj2[0].toString();
                            }
                        }
                        
                    }
                    else{
                        reset();
                    }
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, true);
                    jTabbedPane1.setEnabledAt(3, true);
                    jTabbedPane1.setEnabledAt(4, true);
                    jTabbedPane1.setEnabledAt(5, true);
                    jTabbedPane1.setEnabledAt(6, true);
                    jTabbedPane1.setEnabledAt(7, true);
                    previousDetail_button.setEnabled(true);
                    nextDetail_button.setEnabled(false);
                    memtype_label.setVisible(true);
                }
                else{
                    MemberName_text.setText("No match found.");
                    MemberName_text.setVisible(true);
                    MemberName_text.setForeground(Color.RED);
                    Save_button.setEnabled(false);
                    reset();
                    memtype_label.setVisible(false);
                    jTabbedPane1.setEnabledAt(1, false);
                    jTabbedPane1.setEnabledAt(2, false);
                    jTabbedPane1.setEnabledAt(3, false);
                    jTabbedPane1.setEnabledAt(4, false);
                    jTabbedPane1.setEnabledAt(5, false);
                    jTabbedPane1.setEnabledAt(6, false);
                    jTabbedPane1.setEnabledAt(7, false);
                    previousDetail_button.setEnabled(false);
                    nextDetail_button.setEnabled(false);
                    
                    SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
                }
                
            }
            catch(BasicException e){
                
            }
        }
        else{
            MemberName_text.setVisible(false);
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
                    SD_facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedSD_FacilityList));
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
                    SD_facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedSD_FacilityList));
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
           SD_facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedSD_FacilityList));
           
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
        if(SD_ClubActSDName_Combo.getSelectedIndex()!=-1){
          if(SD_ClubActivity_TempList.size()>0){  
            String SD_Name = SD_ClubActSDName_Combo.getSelectedItem().toString();
            String ActivityFullStr = "";
            for(int i=0;i<SD_ClubActivity_TempList.size();i++){
                String x = SD_ClubActivity_TempList.get(i).toString();
                ActivityFullStr=ActivityFullStr+x+",";
            }  
            SD_ClubActivity_Class sd = new SD_ClubActivity_Class();
            sd.setSD_Name(SD_Name);
            sd.setSD_Activity(ActivityFullStr);
            SD_ClubActivity_ClassListAll.add(sd);
            
            try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                    SD_ClubActivity_Jtable.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                    SD_ClubActivity_Jtable.setVisible(true);
                    SD_ClubAct_combo.setSelectedIndex(-1);
                    SD_ClubActSDName_Combo.setSelectedIndex(-1);
                    SD_ClubActivity_TempList = new ArrayList<String>();
                    SD_ClubAct_Jlist.setModel(new MemberForm.ItemsListModel(SD_ClubActivity_TempList));
                    
            }
            catch(BasicException e){
                e.printStackTrace();
            }  
            
            
          }
          else{
              
          }
        }
        else{
            JOptionPane.showMessageDialog(this, "Select Spouse/ dependant.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_SD_Facility_FullAddButton1ActionPerformed

    private void SD_facilityAdd_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_facilityAdd_button1ActionPerformed
        if(SD_ClubActSDName_Combo.getSelectedIndex()!=-1){
           if(SD_ClubAct_combo.getSelectedIndex()!=-1){
               
               String actname = SD_ClubAct_combo.getSelectedItem().toString();
               int flag=0;
               for(int i=0;i<SD_ClubActivity_TempList.size();i++){
                   String x = SD_ClubActivity_TempList.get(i).toString();
                   if(x.equals(actname)){
                       flag=1;
                       break;
                   }
               }
               if(flag==0){
                    SD_ClubActivity_TempList.add(actname);
                    SD_ClubAct_Jlist.setModel(new MemberForm.ItemsListModel(SD_ClubActivity_TempList));
               }
               else{
                     JOptionPane.showMessageDialog(this, "Activity already selected. please enter another.", "Warning", JOptionPane.WARNING_MESSAGE);
               }
               
               
           } 
            
           else{
               JOptionPane.showMessageDialog(this, "Please select club activity", "Warning", JOptionPane.WARNING_MESSAGE); 
           } 
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Select Spouse/ dependant.", "Warning", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_SD_facilityAdd_button1ActionPerformed

    private void SD_facility_remove_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_facility_remove_button1ActionPerformed
        if(SD_ClubAct_Jlist.getSelectedIndex()!=-1){
            int row = SD_ClubAct_Jlist.getSelectedIndex();
            SD_ClubAct_Jlist.remove(row);
            SD_ClubAct_Jlist.setModel(new MemberForm.ItemsListModel(SD_ClubActivity_TempList));
        }
    }//GEN-LAST:event_SD_facility_remove_button1ActionPerformed

    private void SD_FacilityTable_Remove_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_FacilityTable_Remove_button1ActionPerformed
        if(SD_ClubActivity_Jtable.getSelectedRow()!=-1){
            int row = SD_ClubActivity_Jtable.getSelectedRow();
            SD_ClubActivity_ClassListAll.remove(row);
            
            try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                    SD_ClubActivity_Jtable.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                    SD_ClubActivity_Jtable.setVisible(true);
                    
                    
            }
            catch(BasicException e){
                e.printStackTrace();
            }  
            
            
        }
    }//GEN-LAST:event_SD_FacilityTable_Remove_button1ActionPerformed

    private void previousDetail_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousDetail_buttonActionPerformed
       if(DeactRefIDForHistory!=null && DeactRefIDForHistory.length()>0){
           
           getPreviousDetails(DeactRefIDForHistory);
           
       }
    }//GEN-LAST:event_previousDetail_buttonActionPerformed

    private void SD_Talent_RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Talent_RemoveButtonActionPerformed
      if(SD_talent_Jtable.getSelectedRow()!=-1){
            int row = SD_talent_Jtable.getSelectedRow();
            SD_Talent_ClassListAll.remove(row);
            
            try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Talent_InfoAll(m_App,SD_Talent_ClassListAll);
                    SD_talent_Jtable.setModel(MemberFormTable_Model.getSD_Talent_TableModel(SD_Talent_ClassListAll));
                    SD_talent_Jtable.setVisible(true);
                    
                    
            }
            catch(BasicException e){
                e.printStackTrace();
            }  
            
            
        }
    }//GEN-LAST:event_SD_Talent_RemoveButtonActionPerformed

    private void SD_Ractivities_RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Ractivities_RemoveButtonActionPerformed
       if(SD_RActivity_Jtable.getSelectedRow()!=-1){
            int row = SD_RActivity_Jtable.getSelectedRow();
            SD_RActivity_ClassListAll.remove(row);
            
            try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Ractivity_InfoAll(m_App,SD_RActivity_ClassListAll);
                    SD_RActivity_Jtable.setModel(MemberFormTable_Model.getSD_RActivity_TableModel(SD_RActivity_ClassListAll));
                    SD_RActivity_Jtable.setVisible(true);
           }
           catch(BasicException e){
               e.printStackTrace();
           }
            
        }
    }//GEN-LAST:event_SD_Ractivities_RemoveButtonActionPerformed

    private void SD_Sports_RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SD_Sports_RemoveButtonActionPerformed
       if(SD_Sports_Jtable.getSelectedRow()!=-1){
            int row = SD_Sports_Jtable.getSelectedRow();
            SD_Sports_ClassListAll.remove(row);
            
            try{
                    MemberFormTable_Model=MemberFormTable_Model.loadSD_Sports_InfoAll(m_App,SD_Sports_ClassListAll);
                    SD_Sports_Jtable.setModel(MemberFormTable_Model.getSD_Sports_TableModel(SD_Sports_ClassListAll));
                    SD_Sports_Jtable.setVisible(true);
           }
           catch(BasicException e){
               e.printStackTrace();
           }
            
        }
    }//GEN-LAST:event_SD_Sports_RemoveButtonActionPerformed

    String SelectedMember;
    
    private void SelectMember_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectMember_ButtonActionPerformed
      DocumentReceiptListForm memList;
        try {
            memList = DocumentReceiptListForm.getDialog(this, m_App,true);
            
            SelectedMember = memList.showDialog();
             System.out.println("Memno : "+SelectedMember);
             DocumentStatusMember_label.setText(null);
                    DocumentStatusSpouse_label.setText(null);
             
                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT c.NAME , m.name , c.visible  FROM CUSTOMERS c , memtype m  where c.SEARCHKEY=?  and c.memtype=m.id ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.INT })).find(SelectedMember);
                 
                   
                    String memberOrgname = obj[0].toString();
                     mno_text.setText(SelectedMember);
                    MemberName_text.setText(memberOrgname);
                    MemberName_text.setVisible(true);
                    MemberName_text.setForeground(Color.BLUE);
                    Save_button.setEnabled(true);
                    
                    String MemtypeT = obj[1].toString();
                    memtype_label.setText("Mem. Type : "+MemtypeT);
                    
                    // ************** CHECK IN KNOW YOUR MEMBER LIST ******************************************************* 
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID , finalsubmit  FROM KYMMEMBER where MEMBERNO=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.INT })).find(SelectedMember);
                    if(obj1!=null){
                        
                        
                        
                        
                        getAlreadyLoadedMemberInfo(obj1[0].toString());
                        jProgressBar1.setForeground(Color.RED);
                        jProgressBar1.setValue(25);
                        
                        if(obj1[1]!=null){
                            int  Finalsubmitstatus = Integer.parseInt(obj1[1].toString());
                            if(Finalsubmitstatus==1){
                                 finalsubmit_button.setVisible(false);
                                 Save_button.setVisible(false);
                            }
                            else{
                                 finalsubmit_button.setVisible(true);
                                  Save_button.setVisible(true);
                            }
                            
                            
                            
                        }
                        else{
                             finalsubmit_button.setVisible(true);
                                  Save_button.setVisible(true);
                        }
                        
                        
                        Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT deacref FROM KYMMEMBER where MEMBERNO=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(SelectedMember);
                        if(obj2!=null){
                            if(obj2[0]!=null){
                                DeactRefIDForHistory = obj2[0].toString();
                            }
                        }
                        
                        
                        Object[] objForDocuments = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID , idproof, spouseid, fatherid , motherid  FROM kym_doc where memno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING  })).find(SelectedMember);
                        if(objForDocuments!=null){
                            if(objForDocuments[0]!=null){
                                
                            }
                            if(objForDocuments[1]!=null){
                                DocumentStatusMember_label.setText("Document Saved.");
                            }
                            else{
                                DocumentStatusMember_label.setText("No Document.");
                            }
                            if(objForDocuments[2]!=null){
                                DocumentStatusSpouse_label.setText("Document Saved.");
                            }
                            else{
                                DocumentStatusSpouse_label.setText("No Document.");
                            }
                            
                            if(objForDocuments[3]!=null){
                                DocumentStatusFather_label.setText("Document Saved.");
                            }
                            else{
                                DocumentStatusFather_label.setText("No Document.");
                            }
                            
                            if(objForDocuments[4]!=null){
                                DocumentStatusMother_label.setText("Document Saved.");
                            }
                            else{
                                DocumentStatusMother_label.setText("No Document.");
                            }
                            
                            
                            
                        }
                        else{
                           DocumentStatusMember_label.setText(null);
                            DocumentStatusSpouse_label.setText(null); 
                            DocumentStatusFather_label.setText(null);
                            DocumentStatusMother_label.setText(null);
                        }
                        
                    }
                    else{
                        reset();
                        finalsubmit_button.setVisible(true);
                                  Save_button.setVisible(true);
                    }
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, true);
                    jTabbedPane1.setEnabledAt(3, true);
                    jTabbedPane1.setEnabledAt(4, true);
                    jTabbedPane1.setEnabledAt(5, true);
                    jTabbedPane1.setEnabledAt(6, true);
                    jTabbedPane1.setEnabledAt(7, true);
                    previousDetail_button.setEnabled(true);
                    nextDetail_button.setEnabled(false);
                    memtype_label.setVisible(true);
                    IdProofMemberScan_Button.setVisible(true);
                    IdProofSpouseScan_Button.setVisible(true);
                    
                    DocumentStatusMember_label.setVisible(true);
                    DocumentStatusSpouse_label.setVisible(true);
             
             
             
        } catch (BasicException ex) {
            Logger.getLogger(DocumentReceiptListForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SelectMember_ButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // MemberName_text.setText("No match found.");
                    MemberName_text.setVisible(true);
                    MemberName_text.setForeground(Color.RED);
                    Save_button.setEnabled(false);
                    reset();
                    memtype_label.setVisible(false);
                    jTabbedPane1.setEnabledAt(1, false);
                    jTabbedPane1.setEnabledAt(2, false);
                    jTabbedPane1.setEnabledAt(3, false);
                    jTabbedPane1.setEnabledAt(4, false);
                    jTabbedPane1.setEnabledAt(5, false);
                    jTabbedPane1.setEnabledAt(6, false);
                    jTabbedPane1.setEnabledAt(7, false);
                    previousDetail_button.setEnabled(false);
                    nextDetail_button.setEnabled(false);
                    mno_text.setText(null);
                    SD_Facility_ClassListAll=new ArrayList<SD_Facility_Class>();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void finalsubmit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalsubmit_buttonActionPerformed
      int submit_hall = JOptionPane.showConfirmDialog(jPanel3, "Are you sure you want to make final submit for this form ? ", "Final Submit" , JOptionPane.YES_NO_OPTION);
        if(submit_hall == JOptionPane.YES_OPTION){
            
            
          int submit_hall1 = JOptionPane.showConfirmDialog(jPanel3, "Please ensure that all the details are filled correctly. \n  Note: Once submitted the form cannot be edited. ", "Final Submit" , JOptionPane.YES_NO_OPTION);
                if(submit_hall1 == JOptionPane.YES_OPTION){  
            
            
            
            
            
         if(mno_text.getText()!=null && mno_text.getText().trim().length()>0){
          if(mname_text.getText()!=null && mname_text.getText().trim().length()>0 && nameMatch(mname_text.getText().trim())){//
             if(FatherName_text.getText()!=null && FatherName_text.getText().trim().length()>0 && nameMatch(FatherName_text.getText().trim())){//
                if(DOB_text.getText()!=null && DOB_text.getText().trim().length()>0){
                    if(IDproofDoc_combo.getSelectedIndex()!=-1){
                        if(TypeofMember_combo.getSelectedIndex()!=-1){
                            if(memberSince_text.getText().trim().length()>0){
                                if(nameMatch(MotherName_text.getText())){
                                     if(nameMatch(SpouseName_text.getText().trim())){
                                if(phonNumberMatch(FatherPhoneNo_Text.getText().trim())){
                                 if(phonNumberMatch(MotherPhoneNo_text.getText())){
                                     if(phonNumberMatch(MobileNo_text.getText().trim())){
                                          SD_Ractivity_Properties = new Properties();
                                          MemberNo=mno_text.getText().trim();
                                          MemberName=mname_text.getText().trim();
                                          MemberShipType = TypeofMember_combo.getSelectedItem().toString();
                                          IDProofDoc=IDproofDoc_combo.getSelectedItem().toString();

                                          try {

                                              // **************************** DATE FORMATS ******************
                                              MemberDOB=(Date) formatter.parse(DOB_text.getText());
                                              MemberSince=(Date) formatter.parse(memberSince_text.getText());
                                              if(DateOfMarriage_text!=null && DateOfMarriage_text.getText().trim().length()>0){
                                                  MarriageDate=(Date) formatter.parse(DateOfMarriage_text.getText());
                                              }
                                              if(fatherDob_text!=null && fatherDob_text.getText().trim().length()>0){
                                                  FatherDOB=(Date) formatter.parse(fatherDob_text.getText());
                                              }
                                              if(MotherDOB_text!=null && MotherDOB_text.getText().trim().length()>0){
                                                  MotherDOB = (Date) formatter.parse(MotherDOB_text.getText());
                                              }
                                              // **************************** DATE FORMATS ENDS ******************


                                              // **************************** PARENTS INFO ******************

                                              FatherName=jTextField1.getText();
                                              MotherName=MotherName_text.getText();
                                              if(FatherBloodGroup_combo.getSelectedIndex()!=-1){
                                                  FatherBloodGroup=FatherBloodGroup_combo.getSelectedItem().toString();
                                              }
                                              if(MotherBloodGroup_combo.getSelectedIndex()!=-1){
                                                  MotherBloodGroup=MotherBloodGroup_combo.getSelectedItem().toString();
                                              }
                                              FatherPhoneNo=FatherPhoneNo_Text.getText().trim();
                                              MotherPhoneNo=MotherPhoneNo_text.getText().trim();
                                              FatherEmailID=FatherEmailId_Text.getText().trim();
                                              MotherEmailid=MotherEmailid_text.getText().trim();
                                              if(FatherIdProof_combo.getSelectedIndex()!=-1){
                                                  FatherIDProof=FatherIdProof_combo.getSelectedItem().toString();
                                              }
                                              if(MotherIdProof_combo.getSelectedIndex()!=-1){
                                                  MotherIdProof=MotherIdProof_combo.getSelectedItem().toString();
                                              }
                                              FatherUniqueNo=FatherUniqueNo_text.getText().trim();
                                              MotherUniqueNo=MotherUniqueNo_text.getText().trim();



                                              // **************************** PARENTS INFO ENDS******************



                                              DocumentUniqueNo=idDocUniqueNo_text.getText().trim();
                                              if(male_radio.isSelected()){
                                                  Gender="Male";
                                              }
                                              else{
                                                  Gender="Female";
                                              }

                                              SpouceName=SpouseName_text.getText().trim();


                                              // ************** RECREATIONAL ACTIVITY LOOP *********************
                                              RecreationalActivityStr="";
                                              for(int i=0;i<RecreationalActivityList.size();i++){
                                                  String slno=RecreationalActivityList.get(i).GetSlNo().toString();
                                                  String ActivityName=RecreationalActivityList.get(i).GetActivityName().toString();
                                                  String Level=RecreationalActivityList.get(i).GetActivityLevel().toString();
                                                  String t = slno+"%"+ActivityName+"%"+Level;
                                                  RecreationalActivityStr=RecreationalActivityStr+t+"#";
                                              }
                                              // ************** RECREATIONAL ACTIVITY ENDS *********************



                                              // *************** FACILITY SELECTED LOOP ************************* 
                                              FacilitiesOpted="";
                                              for(int i=0;i<SelectedFacilityList.size();i++){
                                                  String fac = SelectedFacilityList.get(i).toString();
                                                  FacilitiesOpted=FacilitiesOpted+fac+"#";
                                              }
                                              // *************** FACILITY SELECTED LOOP ENDS  ************************* 



                                              // *************** OTHER INFO PANEL PROCESS ******************************* 
                                              if(MobilePhoneUse_Combo.getSelectedIndex()!=-1){
                                                  MobilePhoneUse=MobilePhoneUse_Combo.getSelectedItem().toString();
                                              }
                                              if(Proffesion_Combo.getSelectedIndex()!=-1){
                                                 Proffesion=Proffesion_Combo.getSelectedItem().toString();
                                              }
                                              PlaySport=PlaySport_Text.getText().trim();
                                              SpecialTalent=SpecialTalent_text.getText().trim();
                                              ClubActivity="";
                                              for(int i=0;i<SelectedActivityList.size();i++){
                                                  String act=SelectedActivityList.get(i).toString();
                                                  ClubActivity=ClubActivity+act+"#";
                                              }
                                              // *************** OTHER INFO PANEL PROCESS ENDS ******************************* 



                                              // *****************  ADDRESS DETIALS **************************************** 
                                              String Rline1=""+Rline1Res_text.getText();
                                              String RLine2=""+RLine2Res_text.getText();
                                              String RPost=""+ResPost_text.getText();
                                              String RCity="";
                                              if(ResCity_combo.getSelectedIndex()!=-1)
                                              {
                                                  RCity=ResCity_combo.getSelectedItem().toString();
                                              }
                                              String RState="";
                                              if(ResState_Combo.getSelectedIndex()!=-1){
                                                  RState=ResState_Combo.getSelectedItem().toString();
                                              }
                                              String RCountry="";
                                              if(ResCountry_combo.getSelectedIndex()!=-1){
                                                  RCountry=ResCountry_combo.getSelectedItem().toString();
                                              }
                                              String RPinNo=""+ResPinNo_text.getText();
                                              if(RPinNo.equals("")){
                                                  RPinNo="0";
                                              }
                                              ResidentialAddress=Rline1+"#"+RLine2+"#"+RPost+"#"+RCity+"#"+RState+"#"+RCountry+"#"+RPinNo+"#";


                                              String OFline1=""+offLine1_text.getText();
                                              String Ofine2=""+Off_line2_text.getText();
                                              String OfPost=""+OffPost_Text.getText();
                                              String OfCity="";
                                              if(offCity_combo.getSelectedIndex()!=-1)
                                              {
                                                  OfCity=offCity_combo.getSelectedItem().toString();
                                              }
                                              String OfState="";
                                              if(OffState_combo.getSelectedIndex()!=-1){
                                                  OfState=OffState_combo.getSelectedItem().toString();
                                              }
                                              String OfCountry="";
                                              if(Off_Country_combo.getSelectedIndex()!=-1){
                                                  OfCountry=Off_Country_combo.getSelectedItem().toString();
                                              }
                                              String OfPinNo=""+OffPinNo_text.getText();
                                              if(OfPinNo.equals("")){
                                                  OfPinNo="0";
                                              }
                                              OfficeAddress=OFline1+"#"+Ofine2+"#"+OfPost+"#"+OfCity+"#"+OfState+"#"+OfCountry+"#"+OfPinNo+"#";

                                              MobileNo=MobileNo_text.getText().trim();
                                              ResidenceNo=Residence_no_text.getText().trim();
                                              OfficeNo=OfficeNo_text.getText().toString();
                                              if(residence_Radio.isSelected()){
                                                  CommunicationAddress="Residence";
                                              }
                                              else{
                                                  CommunicationAddress="Office";
                                              }
                                              EmailId=Email_Text.getText().toString();
                                              Twitter=Twitter_text.getText().toString();
                                              // **************************************** ADDRESS DETAILS ENDS *****************************************************



                                              // ******************************************** CHILDREN DETAILS *******************************************************

                                              ChildrenDetailedStr="";
                                              for(int i=0;i<ChildrenClassList.size();i++){
                                                  String Cname=""+ChildrenClassList.get(i).GetName().toString();
                                                  String CGender=""+ChildrenClassList.get(i).GetGender().toString();
                                                  String CBloodGrp=""+ChildrenClassList.get(i).GetBloodGrp().toString();
                                                  String CIdProof=""+ChildrenClassList.get(i).GetIDProof().toString();
                                                  String CPhoneNo=""+ChildrenClassList.get(i).GetPhoneNo().toString();
                                                  String CEmailId=""+ChildrenClassList.get(i).GetEmailID().toString();
                                                  String CUniqueNo=""+ChildrenClassList.get(i).GetUniqueNo().toString();
                                                  String Cdob=""+ChildrenClassList.get(i).GetDateOfBirth().toString();
                                                  String Child=Cname+"%"+CGender+"%"+CBloodGrp+"%"+CIdProof+"%"+CPhoneNo+"%"+CEmailId+"%"+CUniqueNo+"%"+Cdob;
                                                  ChildrenDetailedStr=ChildrenDetailedStr+Child+"#";
                                              }
                                              // ******************************************** CHILDREN DETAILS ENDS  *******************************************************


                                             // ******************************************** OTHER CLUB INFO  ******************************************************* 
                                              OtherClubInfoStr="";
                                              for(int i=0;i<OtherClubClassListAll_Class.size();i++){
                                                  String ClubName=OtherClubClassListAll_Class.get(i).GetClubName();
                                                  String otherMemno=OtherClubClassListAll_Class.get(i).GetMemberno();
                                                  String typeofmember=OtherClubClassListAll_Class.get(i).GetTypeofMember();
                                                  String City=OtherClubClassListAll_Class.get(i).GetCity();
                                                  String Membersince=OtherClubClassListAll_Class.get(i).GetMemberSince();
                                                  String Otherclubinfo=ClubName+"%"+otherMemno+"%"+typeofmember+"%"+City+"%"+Membersince;
                                                  OtherClubInfoStr=OtherClubInfoStr+Otherclubinfo+"#";
                                              } 

                                              // ****************************************  SD SAPORTS DETIALS ************************************************

                                              SD_Sports_FullStr="";
                                              for(int i=0;i<SD_Sports_ClassListAll.size();i++){
                                                  String SD_Name = SD_Sports_ClassListAll.get(i).GetSD_Name().toString();
                                                  String SD_Sports = SD_Sports_ClassListAll.get(i).GetSD_Sports().toString();
                                                  String tempstr=SD_Name+"%"+SD_Sports;
                                                  SD_Sports_FullStr=SD_Sports_FullStr+tempstr+"#";
                                              }
                                              SD_RActivity_FullStr="";
                                              for(int i=0;i<SD_RActivity_ClassListAll.size();i++){
                                                  String SD_name=SD_RActivity_ClassListAll.get(i).GetSD_Name();
                                                  String SD_ActivityName = SD_RActivity_ClassListAll.get(i).GetSD_Sports();
                                                  String SD_level = SD_RActivity_ClassListAll.get(i).GetSD_level();
                                                  String t = SD_name+"%"+SD_ActivityName+"%"+SD_level;
                                                  SD_RActivity_FullStr=SD_RActivity_FullStr+t+"#";
                                              }
                                              oj = (Object) SD_RActivity_FullStr;

                                              SD_Facility_FullStr="";
                                              for(int i=0;i<SD_Facility_ClassListAll.size();i++){
                                                  String Sd_Name = SD_Facility_ClassListAll.get(i).GetSD_Name();
                                                  String Sd_Facility = SD_Facility_ClassListAll.get(i).GetSD_Facility();
                                                  String x = Sd_Name+"%"+Sd_Facility;
                                                  SD_Facility_FullStr=SD_Facility_FullStr+x+"#";
                                              }
                                               SD_Facility_Object = (Object) SD_Facility_FullStr;

                                              SD_Talent_FullStr="";
                                              for(int i=0;i<SD_Talent_ClassListAll.size();i++){
                                                  String sd_name = SD_Talent_ClassListAll.get(i).GetSD_Name();
                                                  String Sd_talent = SD_Talent_ClassListAll.get(i).GetSD_Talent();
                                                  String t = sd_name+"%"+Sd_talent;
                                                  SD_Talent_FullStr=SD_Talent_FullStr+t+"#";
                                              }
                                              SD_Talent_Object = (Object) SD_Talent_FullStr; 

                                              SD_Activity_FullStr = "";
                                              for(int i=0;i<SD_ClubActivity_ClassListAll.size();i++){
                                                  String sd_name = SD_ClubActivity_ClassListAll.get(i).GetSD_Name().toString();
                                                  String ClubActivityName = SD_ClubActivity_ClassListAll.get(i).GetSD_Activity().toString();
                                                  String t = sd_name+"%"+ClubActivityName;
                                                  SD_Activity_FullStr=SD_Activity_FullStr+t+"#";
                                              } 
                                              SD_Activity_Object=(Object) SD_Activity_FullStr;


                                            if(SpouseId_Combo.getSelectedIndex()!=-1){
                                                SpouseID = SpouseId_Combo.getSelectedItem().toString();
                                            }
                                              
                                              
                                              Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                                                   @Override      
                                                   protected Object transact() throws BasicException {   


                                                       Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM KYMMEMBER where MEMBERNO=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(MemberNo);
                                                       if(obj!=null){
                                                           DeactivatedRef=obj[0].toString();

                                                       }


                                                       int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO kymmember (ID ,memberno , membername , DATEOFBIRTH , IDPROOF , IDUNIQUENO , MEMBERTYPE , MEMBERSINCE , SPOUCENAME , DATEOFMARRIAGE , GENDER ,FATHERNAME ,FATHERDOB,MOTHERNAME,MOTHERDOB,RecreationalActivities,FACILITIES,MOBILETYPE,PROFFESION,PLAYSPORTS,SpecialTalent,CLUBACTIVITY,RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER,DEPENDENTDETAILS,OTHERCLUB,FATHERBG,FATHERPHONENO,FATHEREMAILID,FATHERIDPROOF,FATHERUNIQUENO,MOTHERBG,MOTHERPHONENO,MOTHEREMAILID,MOTHERIDPROOF,MOTHERUNIQUENO,CRBY,CRDATE,CRHOST,deacRef,SD_SPORTS_DETAIL,SD_RACTIVITY,SD_FACILITY,SD_TALENT,SD_CLUBACTIVITY , finalsubmit , spouseidproof   ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.TIMESTAMP, Datas.STRING ,Datas.STRING  , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.TIMESTAMP,Datas.STRING ,Datas.STRING , Datas.STRING ,Datas.STRING,Datas.STRING ,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING ,Datas.STRING,Datas.SERIALIZABLE , Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE , Datas.INT , Datas.STRING})                         
                                                        ).exec(new Object[]{UUID.randomUUID().toString(), MemberNo ,MemberName , MemberDOB , IDProofDoc ,DocumentUniqueNo,MemberShipType, MemberSince , SpouceName , MarriageDate , Gender , FatherName,FatherDOB,MotherName,MotherDOB,RecreationalActivityStr,FacilitiesOpted,MobilePhoneUse,Proffesion,PlaySport,SpecialTalent,ClubActivity,ResidentialAddress ,OfficeAddress , MobileNo,ResidenceNo,OfficeNo,CommunicationAddress,EmailId,Twitter,ChildrenDetailedStr,OtherClubInfoStr,FatherBloodGroup,FatherPhoneNo,FatherEmailID,FatherIDProof,FatherUniqueNo,MotherBloodGroup,MotherPhoneNo,MotherEmailid,MotherIdProof,MotherUniqueNo ,m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() ,DeactivatedRef ,SD_Sports_FullStr,oj , SD_Facility_Object ,SD_Talent_Object,SD_Activity_Object , 1 , SpouseID });                                                                                                



                                                        new PreparedSentence(m_App.getSession()
                                                              , "INSERT INTO KYMMEMBER_ARV SELECT *,?,?,? FROM KYMMEMBER WHERE ID=? "
                                                              , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP , Datas.STRING })).exec(new Object[]{  m_App.getAppUserView().getUser().getName() , m_App.getProperties().getHost(),new Date() ,DeactivatedRef   });


                                                        new PreparedSentence(m_App.getSession()
                                                              , "DELETE FROM KYMMEMBER WHERE ID=? "
                                                              , new SerializerWriteBasic(new Datas[]{Datas.STRING })).exec(new Object[]{ DeactivatedRef   });




                                                       return null;                                      
                                                           }                            
                                                       };                 


                                              try {                 

                                                  t.execute();          
                                                  JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                  reset();
                                                  loaddata();
                                                  mno_text.setText(null);
                                              }
                                              catch (BasicException ex) {                    
                                                  Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                                                  ex.printStackTrace();
                                                  new MessageInf(ex).show(new JFrame());
                                              } 
                                          } catch (ParseException ex) {
                                              Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                                              ex.printStackTrace();
                                              new MessageInf(ex).show(new JFrame());
                                          }
                                     } } } } }//PhoneNumber validation if breaket closed
                        } else{
                                JOptionPane.showMessageDialog(this, "Please Fill Date in Member Since.  ", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                    } else{
                         JOptionPane.showMessageDialog(this, "Please enter Membership Type.  ", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    //added by pratima
                }else{
                        JOptionPane.showMessageDialog(this, "Please select Identity Proof Type.  ", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
            }else{
                    JOptionPane.showMessageDialog(this, "Please enter Member's Date of birth .  ", "Warning", JOptionPane.WARNING_MESSAGE);
                }
        }else{
                JOptionPane.showMessageDialog(this, "Please enter Father's Name .  ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
     }else{
            JOptionPane.showMessageDialog(this, "Please enter Member Name.  ", "Warning", JOptionPane.WARNING_MESSAGE);
            }
  }else{ 
     JOptionPane.showMessageDialog(this, "Please enter Membership No.  ", "Warning", JOptionPane.WARNING_MESSAGE);
      }
 }
}
    }//GEN-LAST:event_finalsubmit_buttonActionPerformed

    private void IdProofMemberScan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProofMemberScan_ButtonActionPerformed
        DocumentUploadFrame memList;
        try {
            memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-ID" , mno_text.getText() , 2);
            Boolean status =memList.showDialog();
            if(status){
                DocumentStatusMember_label.setText("Document Saved.");
            }
        } catch (BasicException ex) {
            Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IdProofMemberScan_ButtonActionPerformed

    private void IdProofSpouseScan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProofSpouseScan_ButtonActionPerformed
       DocumentUploadFrame memList;
        try {
            memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-SpouseID" , mno_text.getText() , 3);
            Boolean status =memList.showDialog();
             if(status){
                DocumentStatusSpouse_label.setText("Document Saved.");
            }
        } catch (BasicException ex) {
            Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IdProofSpouseScan_ButtonActionPerformed

    private void IdProofFatherScan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProofFatherScan_ButtonActionPerformed
       DocumentUploadFrame memList;
        try {
            memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-FatherID" , mno_text.getText() , 4);
            Boolean status =memList.showDialog();
            if(status){
                DocumentStatusFather_label.setText("Document Saved.");
            }
        } catch (BasicException ex) {
            Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IdProofFatherScan_ButtonActionPerformed

    private void IdProofMotherScan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProofMotherScan_ButtonActionPerformed
       DocumentUploadFrame memList;
        try {
            memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-MotherID" , mno_text.getText() , 5);
            Boolean status =memList.showDialog();
            if(status){
                DocumentStatusMother_label.setText("Document Saved.");
            }
        } catch (BasicException ex) {
            Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IdProofMotherScan_ButtonActionPerformed

    private void IdProofChildrenScan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProofChildrenScan_ButtonActionPerformed
       if(children_Jtable.getSelectedRow()!=-1){
            if(children_Jtable.getSelectedRow()< ChildrenClassList.size()){
                
              int row = children_Jtable.getSelectedRow();   
               if(row==0){
                   DocumentUploadFrame memList;
                    try {
                        memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-S1ID" , mno_text.getText() , 6);
                        Boolean status = memList.showDialog();
                    } catch (BasicException ex) {
                        Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               }  
                if(row==1){
                   DocumentUploadFrame memList;
                    try {
                        memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-S2ID" , mno_text.getText() , 7);
                        memList.showDialog();
                    } catch (BasicException ex) {
                        Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               } 
                
                if(row==2){
                   DocumentUploadFrame memList;
                    try {
                        memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-S3ID" , mno_text.getText() , 7);
                        memList.showDialog();
                    } catch (BasicException ex) {
                        Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               } 
                
               if(row==3){
                   DocumentUploadFrame memList;
                   try {
                        memList = DocumentUploadFrame.getDialog(this, m_App,true,mno_text.getText()+"-S4ID" , mno_text.getText() , 8);
                        memList.showDialog();
                    } catch (BasicException ex) {
                        Logger.getLogger(DocumentUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               }  
                
       
            }
            
       }
    }//GEN-LAST:event_IdProofChildrenScan_ButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Activities_jList;
    private javax.swing.JButton ChildRemove_Button;
    private javax.swing.JButton ChildrenAdd_button;
    private javax.swing.JComboBox ChildrenBlood_Combo;
    private javax.swing.JTextField ChildrenDOB_text;
    private javax.swing.JTextField ChildrenEmailId_text;
    private javax.swing.JRadioButton ChildrenFemale_radio;
    private javax.swing.JComboBox ChildrenIDProof_combo;
    private javax.swing.JRadioButton ChildrenMale_radio;
    private javax.swing.JTextField ChildrenName_text;
    private javax.swing.JTextField ChildrenPhone_text;
    private javax.swing.JTextField ChildrenUniqueNo_text;
    private javax.swing.JTextField DOB_text;
    private javax.swing.JTextField DateOfMarriage_text;
    private javax.swing.JLabel DocumentStatusFather_label;
    private javax.swing.JLabel DocumentStatusMember_label;
    private javax.swing.JLabel DocumentStatusMother_label;
    private javax.swing.JLabel DocumentStatusSpouse_label;
    private javax.swing.JTextField Email_Text;
    private javax.swing.JComboBox FacilityOption_Combo;
    private javax.swing.JList Facility_Jlist;
    private javax.swing.JComboBox FatherBloodGroup_combo;
    private javax.swing.JTextField FatherEmailId_Text;
    private javax.swing.JComboBox FatherIdProof_combo;
    private javax.swing.JTextField FatherName_text;
    private javax.swing.JTextField FatherPhoneNo_Text;
    private javax.swing.JTextField FatherUniqueNo_text;
    private javax.swing.JComboBox IDproofDoc_combo;
    private javax.swing.JButton IdProofChildrenScan_Button;
    private javax.swing.JButton IdProofFatherScan_Button;
    private javax.swing.JButton IdProofMemberScan_Button;
    private javax.swing.JButton IdProofMotherScan_Button;
    private javax.swing.JButton IdProofSpouseScan_Button;
    private javax.swing.JLabel MemberName_text;
    private javax.swing.JTextField MobileNo_text;
    private javax.swing.JComboBox MobilePhoneUse_Combo;
    private javax.swing.JComboBox MotherBloodGroup_combo;
    private javax.swing.JTextField MotherDOB_text;
    private javax.swing.JTextField MotherEmailid_text;
    private javax.swing.JComboBox MotherIdProof_combo;
    private javax.swing.JTextField MotherName_text;
    private javax.swing.JTextField MotherPhoneNo_text;
    private javax.swing.JTextField MotherUniqueNo_text;
    private javax.swing.JTextField OffPinNo_text;
    private javax.swing.JTextField OffPost_Text;
    private javax.swing.JComboBox OffState_combo;
    private javax.swing.JComboBox Off_Country_combo;
    private javax.swing.JTextField Off_line2_text;
    private javax.swing.JTextField OfficeNo_text;
    private javax.swing.JRadioButton Office_Radio;
    private javax.swing.JComboBox OtherClubCity_combo;
    private javax.swing.JTextField OtherClubMemberSince_text;
    private javax.swing.JTable OtherClubMember_JTable;
    private javax.swing.JTextField OtherClubMemno_text;
    private javax.swing.JComboBox OtherClubName_Combo;
    private javax.swing.JTextField PlaySport_Text;
    private javax.swing.JComboBox Proffesion_Combo;
    private javax.swing.JTextField RLine2Res_text;
    private javax.swing.JButton Recractivity_remove_btn;
    private javax.swing.JTable RecreationalActivity_JTable;
    private javax.swing.JComboBox ResCity_combo;
    private javax.swing.JComboBox ResCountry_combo;
    private javax.swing.JTextField ResPinNo_text;
    private javax.swing.JTextField ResPost_text;
    private javax.swing.JComboBox ResState_Combo;
    private javax.swing.JTextField Residence_no_text;
    private javax.swing.JTextField Rline1Res_text;
    private javax.swing.JComboBox SD_ClubActSDName_Combo;
    private javax.swing.JList SD_ClubAct_Jlist;
    private javax.swing.JComboBox SD_ClubAct_combo;
    private javax.swing.JTable SD_ClubActivity_Jtable;
    private javax.swing.JComboBox SD_FacilitySpouce_Combo;
    private javax.swing.JButton SD_FacilityTable_Remove_button;
    private javax.swing.JButton SD_FacilityTable_Remove_button1;
    private javax.swing.JButton SD_Facility_FullAddButton;
    private javax.swing.JButton SD_Facility_FullAddButton1;
    private javax.swing.JTable SD_Facility_Jtable;
    private javax.swing.JTextField SD_RActivityLevel_Textfeild;
    private javax.swing.JComboBox SD_RActivity_Combo;
    private javax.swing.JTable SD_RActivity_Jtable;
    private javax.swing.JTextField SD_RActivity_Textfeild;
    private javax.swing.JButton SD_Ractivities_RemoveButton;
    private javax.swing.JButton SD_Ractivity_AddButton;
    private javax.swing.JButton SD_Sports_AddButton;
    private javax.swing.JTable SD_Sports_Jtable;
    private javax.swing.JButton SD_Sports_RemoveButton;
    private javax.swing.JTextField SD_Sports_Textfeild;
    private javax.swing.JButton SD_Talent_RemoveButton;
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
    private javax.swing.JButton Save_button;
    private javax.swing.JButton SelectMember_Button;
    private javax.swing.JTextField SpecialTalent_text;
    private javax.swing.JComboBox<String> SpouseId_Combo;
    private javax.swing.JTextField SpouseName_text;
    private javax.swing.JTextField Twitter_text;
    private javax.swing.JComboBox TypeofMember_combo;
    private javax.swing.JComboBox activities_combo;
    private javax.swing.JTable children_Jtable;
    private javax.swing.JButton facilityAdd_button;
    private javax.swing.JButton facilityRemove_button;
    private javax.swing.JTextField fatherDob_text;
    private javax.swing.JRadioButton female_radio;
    private javax.swing.JButton finalsubmit_button;
    private javax.swing.JTextField idDocUniqueNo_text;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
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
    private javax.swing.JProgressBar jProgressBar1;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JRadioButton male_radio;
    private javax.swing.JComboBox<String> membcombo;
    private javax.swing.JTextField memberSince_text;
    private javax.swing.JLabel memtype_label;
    private javax.swing.JTextField mname_text;
    private javax.swing.JTextField mno_text;
    private javax.swing.JButton nextDetail_button;
    private javax.swing.JComboBox offCity_combo;
    private javax.swing.JTextField offLine1_text;
    private javax.swing.JButton previousDetail_button;
    private javax.swing.JTextField recreational_Sport_Text;
    private javax.swing.JTextField recreational_level_text;
    private javax.swing.JRadioButton residence_Radio;
    private javax.swing.JComboBox sports_SD_Combo;
    private javax.swing.JComboBox<String> spousebgcombo;
    // End of variables declaration//GEN-END:variables

    //added by pratima: method is created for phon numbers validation
public boolean phonNumberMatch(String phnNoStr){
    if(phnNoStr.length()>0){
       Matcher matcher = phnNoPattern.matcher(phnNoStr);
       if (matcher.matches()) {
         System.out.println("Phone Number Valid");
         return true;
       } else {
         JOptionPane.showMessageDialog(this, "Please enter 10 digit phone number  ", "Warning", JOptionPane.WARNING_MESSAGE);
         return false;
       }
    }else return true;  
}
public boolean nameMatch(String nameStr){
    if(nameStr.length()>0){
       Matcher matcher = namePattern.matcher(nameStr);
       if (matcher.matches()) {
         System.out.println("Name is Valid");
         return true;
       } else {
         JOptionPane.showMessageDialog(this, "Please enter only charecters in name ", "Warning", JOptionPane.WARNING_MESSAGE);
         return false;
       }
    }else return true;  
}

 //ended by pratima   
 public String getTitle() {
   return "Know Your Member Form";  
    }

    @Override
    public void activate() throws BasicException {
       reset();//added by pratima 
       mno_text.setText(null);//added by pratima 
      ButtonGrp();
      loaddata();
      LoadAllComboBox();
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
       
        memtype_label.setVisible(false);
        MemberShipTypeList=new ArrayList<String>();
        MemberShipTypeList=getMemberTypeListAll();
        MemTypeComboBoxValModel=new ComboBoxValModel(MemberShipTypeList);
        TypeofMember_combo.setModel(MemTypeComboBoxValModel);
        TypeofMember_combo.setSelectedIndex(-1);
        
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
        Save_button.setEnabled(false);
        male_radio.setSelected(true);
        residence_Radio.setSelected(true);
        SD_Sports_Jtable.setVisible(false);
        SD_Facility_Jtable.setVisible(false);
         finalsubmit_button.setVisible(false);
        previousDetail_button.setEnabled(false);
        nextDetail_button.setEnabled(false);
        SD_ClubActivity_Jtable.setVisible(false);
        SpouseId_Combo.setSelectedIndex(-1);
        System.out.println("Time zone : "+formatter.getTimeZone().getDisplayName());
        
        IdProofMemberScan_Button.setVisible(false);
     
        DocumentStatusMember_label.setText(null);
        DocumentStatusSpouse_label.setText(null);
        DocumentStatusMember_label.setVisible(false);
        DocumentStatusSpouse_label.setVisible(false);
        
        IdProofSpouseScan_Button.setVisible(false);
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
        //mno_text.setText(null);
        
        SpouseId_Combo.setSelectedIndex(-1);
        mname_text.setText(null);
        FatherName_text.setText(null);
        DOB_text.setText(null);
        IDproofDoc_combo.setSelectedIndex(-1);
        idDocUniqueNo_text.setText(null);
        TypeofMember_combo.setSelectedIndex(-1);
        memberSince_text.setText(null);
        SpouseName_text.setText(null);
        DateOfMarriage_text.setText(null);
        male_radio.setSelected(true);
        memtype_label.setVisible(false);
        jTextField1.setText(null);
        fatherDob_text.setText(null);
        MotherName_text.setText(null);
        MotherDOB_text.setText(null);
         finalsubmit_button.setVisible(false);
        Rline1Res_text.setText(null);
        RLine2Res_text.setText(null);
        ResPost_text.setText(null);
        ResCity_combo.setSelectedIndex(-1);
        ResCountry_combo.setSelectedIndex(-1);
        ResState_Combo.setSelectedIndex(-1);
        ResPinNo_text.setText(null);
        offLine1_text.setText(null);
        Off_line2_text.setText(null);
        OffPost_Text.setText(null);
        OffPinNo_text.setText(null);
        offCity_combo.setSelectedIndex(-1);
        Off_Country_combo.setSelectedIndex(-1);
        OffState_combo.setSelectedIndex(-1);
        
        MobileNo_text.setText(null);
        Residence_no_text.setText(null);
        OfficeNo_text.setText(null);
        Email_Text.setText(null);
        Twitter_text.setText(null);
        residence_Radio.setSelected(true);
        MobilePhoneUse_Combo.setSelectedIndex(-1);
        Proffesion_Combo.setSelectedIndex(-1);
        activities_combo.setSelectedIndex(-1);
        FacilityOption_Combo.setSelectedIndex(-1);
        PlaySport_Text.setText(null);
        SpecialTalent_text.setText(null);
        recreational_Sport_Text.setText(null);
        recreational_level_text.setText(null);
        SelectedActivityList=new ArrayList<String>();
        Activities_jList.setModel(new MemberForm.ItemsListModel(SelectedActivityList));
        SelectedFacilityList=new ArrayList<String>();
        Facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedFacilityList));
        
        RecreationalActivityList = new ArrayList<>();
        RecreationalActivity_JTable.setVisible(false);
        
        children_Jtable.setVisible(false);
        ChildrenClassList = new ArrayList<ChildrenClass>();//added by pratima
        FatherBloodGroup_combo.setSelectedIndex(-1);
        FatherPhoneNo_Text.setText(null);
        FatherIdProof_combo.setSelectedIndex(-1);
        FatherEmailId_Text.setText(null);
        FatherUniqueNo_text.setText(null);
        MotherBloodGroup_combo.setSelectedIndex(-1);
        MotherPhoneNo_text.setText(null);
        MotherIdProof_combo.setSelectedIndex(-1);
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
        SD_ClubActivity_Jtable.setVisible(false);
        SD_ClubActivity_ClassListAll=new ArrayList<SD_ClubActivity_Class>();
        
        previousDetail_button.setEnabled(false);
        nextDetail_button.setEnabled(false);
        IdProofMemberScan_Button.setVisible(false);
        
        IdProofSpouseScan_Button.setVisible(false);
        membcombo.setSelectedIndex(-1);
        spousebgcombo.setSelectedIndex(-1);
        
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
        ResCity_combo.setModel(CityComboBoxValModel);
        OffCityComboBoxValModel = new ComboBoxValModel(CityList);
        offCity_combo.setModel(OffCityComboBoxValModel);
        //city_combo.setSelectedIndex(-1);
        OtherClubCityListBoxValModel=new ComboBoxValModel(CityList);
        OtherClubCity_combo.setModel(OtherClubCityListBoxValModel);
        
        CountryList = new ArrayList<String>();
        CountryList=GetCountryList(m_App);
        CountryComboBoxValModel = new ComboBoxValModel(CountryList);
        ResCountry_combo.setModel(CountryComboBoxValModel);
        OffCountryComboBoxValModel = new ComboBoxValModel(CountryList);
        Off_Country_combo.setModel(OffCountryComboBoxValModel);
        //Country_Combo.setSelectedIndex(-1);
        
        StateList = new ArrayList<String>();
        StateList=GetStateList(m_App);
        StateComboBoxValModel = new ComboBoxValModel(StateList);
        ResState_Combo.setModel(StateComboBoxValModel);
        OffStateComboBoxValModel = new ComboBoxValModel(StateList);
        OffState_combo.setModel(OffStateComboBoxValModel);
        //State_Combo.setSelectedIndex(-1);
        
        MobileOsList = new ArrayList<String>();
        MobileOsList=GetMobileOSList(m_App);
        MobileOSComboBoxValModel = new ComboBoxValModel(MobileOsList);
        MobilePhoneUse_Combo.setModel(MobileOSComboBoxValModel);
        //MobileOS_Combo.setSelectedIndex(-1);
        
        ProfessionList = new ArrayList<String>();
        ProfessionList=GetProfessionList(m_App);
        professionComboBoxValModel = new ComboBoxValModel(ProfessionList);
        Proffesion_Combo.setModel(professionComboBoxValModel);
        //Profession_combo.setSelectedIndex(-1);
        
        ClubActivityList = new ArrayList<String>();
        ClubActivityList=GetClubActivityList(m_App);
        ClubActivityComboBoxValModel = new ComboBoxValModel(ClubActivityList);
        activities_combo.setModel(ClubActivityComboBoxValModel);
        SD_ClubActName_ComboBoxValModel  = new ComboBoxValModel(ClubActivityList);
        SD_ClubAct_combo.setModel(SD_ClubActName_ComboBoxValModel);
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
        IDproofDoc_combo.setModel(IdProofComboBoxValModel);
        
         
        IDProofTypeListSpouse = new ArrayList<String>();
        IDProofTypeListSpouse=GetIDProofListForSpouse(m_App);
        Spouse_IdProofComboBoxModel = new ComboBoxValModel(IDProofTypeListSpouse);
        SpouseId_Combo.setModel(Spouse_IdProofComboBoxModel);
        
        
        ChildrenIdProodListBoxValModel=new ComboBoxValModel(IDProofTypeList);
        ChildrenIDProof_combo.setModel(ChildrenIdProodListBoxValModel);
       // ChildrenIDProof_combo.setSelectedIndex(-1);
         FatherIdProofBoxValModel=new ComboBoxValModel(IDProofTypeList);
        FatherIdProof_combo.setModel(FatherIdProofBoxValModel);
        MotherIdProofBoxValModel=new ComboBoxValModel(IDProofTypeList);
        MotherIdProof_combo.setModel(MotherIdProofBoxValModel);
        
        
        //IDProof_combo.setSelectedIndex(-1);
        
        ChildrenBloodGrpListAll=new ArrayList<String>();
        ChildrenBloodGrpListAll=GetBloodGroupList(m_App);
        ChildrenBloodGrpListBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
        ChildrenBlood_Combo.setModel(ChildrenBloodGrpListBoxValModel);
        
        FatherBloodGroupBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
        FatherBloodGroup_combo.setModel(FatherBloodGroupBoxValModel);
        MotherBloodGroupBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
        MotherBloodGroup_combo.setModel(MotherBloodGroupBoxValModel);
        MemberBloodGroupBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
        membcombo.setModel(MemberBloodGroupBoxValModel);
        SpouseBloodGroupBoxValModel=new ComboBoxValModel(ChildrenBloodGrpListAll);
        spousebgcombo.setModel(SpouseBloodGroupBoxValModel);
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
            String x= formatter.format(DateOfBirth);
            return x;
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
            String x= formatter.format(MemberSince);
            return x;
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
    
    // *******************************************************************  GET ALREADY LOADED INFO *************************************************
    
    
    
    public void getAlreadyLoadedMemberInfo(String Id) {
      try{   
            reset();
            int unfilledMarks=0;
            
          
          SD_listAll = new ArrayList<String>();
          
          // ***********************  MAIN PAGE INFO **********************************************************
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MEMBERNAME,DATEOFBIRTH,IDPROOF,IDUNIQUENO,MEMBERTYPE,MEMBERSINCE,SPOUCENAME,DATEOFMARRIAGE,GENDER,FATHERNAME,MEMBERSINCE,MEMBER_BG,SPOUSE_BG,SPOUSEIDPROOF FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.TIMESTAMP , Datas.STRING,Datas.STRING,Datas.STRING , Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP , Datas.STRING, Datas.STRING,Datas.TIMESTAMP , Datas.STRING, Datas.STRING, Datas.STRING})).find(Id); 
          
          
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
                  String idproof=obj1[2].toString();
                  for(int i=0;i<IDProofTypeList.size();i++){
                      String x=IDProofTypeList.get(i).toString();
                      if(x.equals(idproof)){
                          IDproofDoc_combo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  IDproofDoc_combo.setSelectedIndex(-1);
                  unfilledMarks++;
              }
              if(obj1[3]!=null){
                  idDocUniqueNo_text.setText(obj1[3].toString());
              }
              
              
              if(obj1[4]!=null){
                  String memtype=obj1[4].toString();
                  for(int i=0;i<MemberShipTypeList.size();i++){
                      String x=MemberShipTypeList.get(i).toString();
                      if(x.equals(memtype)){
                          TypeofMember_combo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  TypeofMember_combo.setSelectedIndex(-1);
                  unfilledMarks++;
              }
              
              
              if(obj1[5]!=null){
                  Date memsince = (Date) obj1[5];
                  String X = formatter.format(memsince);
                  memberSince_text.setText(X);
              }
              
              if(obj1[6]!=null){
                  SpouseName_text.setText(obj1[6].toString());
                  SD_listAll.add(obj1[6].toString());
              }
             
              if(obj1[7]!=null){
                  Date dom = (Date) obj1[7];
                  String X = formatter.format(dom);
                  DateOfMarriage_text.setText(X);
              }
              
              if(obj1[8]!=null){
                 String g=obj1[8].toString().toLowerCase();
                 if(g.equals("male")){
                     male_radio.setSelected(true);
                 }
                 else{
                     female_radio.setSelected(true);
                 }
              }
              
              //added by pratima : below fiels were nt getting loaded when we were selecting a member
             
                if(obj1[9]!=null){
                  FatherName_text.setText(obj1[9].toString());
                    }
                if(obj1[10]!=null){
                  Date doMemSince = (Date) obj1[10];
                  String msX = formatter.format(doMemSince);
                  memberSince_text.setText(msX);
              }
               if(obj1[11]!=null){
                  String mbg=obj1[11].toString();
                  for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
                      String xmbg=ChildrenBloodGrpListAll.get(i).toString();
                      if(xmbg.equals(mbg)){
                          membcombo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  membcombo.setSelectedIndex(-1);
              }
               
               if(obj1[12]!=null){
                  String sbg=obj1[12].toString();
                  for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
                      String xsbg=ChildrenBloodGrpListAll.get(i).toString();
                      if(xsbg.equals(sbg)){
                          spousebgcombo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  spousebgcombo.setSelectedIndex(-1);
              }
               if(obj1[13]!=null){
                   
                  String sidp=obj1[13].toString();
                  for(int i=0;i<IDProofTypeListSpouse.size();i++){
                      String xsidp=IDProofTypeListSpouse.get(i).toString();
                      if(xsidp.equals(sidp)){
                          SpouseId_Combo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  SpouseId_Combo.setSelectedIndex(-1);
              }
              //ended by pratima
              
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
                          fatherDob_text.setText(X);
                    }
                    
                    if(obj2[2]!=null){
                        String fn = obj2[2].toString();
                        MotherName_text.setText(fn);
                    }
                    
                    if(obj2[3]!=null){
                         Date dom = (Date) obj2[3];
                          String X = formatter.format(dom);
                          MotherDOB_text.setText(X);
                    }
                    
                    if(obj2[4]!=null){
                        String Fatherbloodgrp= obj2[4].toString();
                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
                            String x= ChildrenBloodGrpListAll.get(i).toString();
                            if(x.equals(Fatherbloodgrp)){
                                FatherBloodGroup_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[5]!=null){
                        String fn = obj2[5].toString();
                        FatherPhoneNo_Text.setText(fn);
                    }
                    
                    if(obj2[6]!=null){
                        String fn = obj2[6].toString();
                        FatherEmailId_Text.setText(fn);
                    }
                   
                    if(obj2[7]!=null){
                        String FatherIdProof= obj2[7].toString();
                        for(int i=0;i<IDProofTypeList.size();i++){
                            String x= IDProofTypeList.get(i).toString();
                            if(x.equals(FatherIdProof)){
                                FatherIdProof_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[8]!=null){
                        String fn = obj2[8].toString();
                        FatherUniqueNo_text.setText(fn);
                    }
                    

                    if(obj2[9]!=null){
                        String Motherbloodgrp= obj2[9].toString();
                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
                            String x= ChildrenBloodGrpListAll.get(i).toString();
                            if(x.equals(Motherbloodgrp)){
                                MotherBloodGroup_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[10]!=null){
                        String fn = obj2[10].toString();
                        MotherPhoneNo_text.setText(fn);
                    }
                    
                    if(obj2[11]!=null){
                        String fn = obj2[11].toString();
                        MotherEmailid_text.setText(fn);
                    }
                    
                    if(obj2[12]!=null){
                        String MotherIdProof= obj2[12].toString();
                        for(int i=0;i<IDProofTypeList.size();i++){
                            String x= IDProofTypeList.get(i).toString();
                            if(x.equals(MotherIdProof)){
                                MotherIdProof_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[13]!=null){
                        String fn = obj2[13].toString();
                        MotherUniqueNo_text.setText(fn);
                    }
                    



                }
               // ***********************  PARENTS INFO  ENDS **********************************************************
                
                
                
                
              // ***********************  CHILDREN INFO    **********************************************************  // 
               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT DEPENDENTDETAILS FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id); 
                
               ChildrenClassList = new ArrayList<ChildrenClass>();
               
               if(obj3!=null){
                   if(obj3[0]!=null){
                     if(!obj3[0].equals("")){   
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
                                
                                Date Dob=formatter.parse(Cdob);
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
                        }
                        
                        
                        MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                   }
                   }
                   
                }
               
               // *********************************** CHILDREN DETAILS ENDS HERE ***************************************************
               
               
               
               // *********************************** ADDRESS DETAILS STARTS HERE ***************************************************
              
               
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               if(obj4!=null){
                   if(obj4[0]!=null){
                     if(!obj4[0].equals("")){  
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
                           
                           Rline1Res_text.setText(Rline1);
                           RLine2Res_text.setText(Rline2);
                           ResPost_text.setText(Rpost);
                           ResPinNo_text.setText(PinNo);
                           
                           for(int i=0;i<CityList.size();i++){
                               String c = CityList.get(i).toString();
                               if(c.equals(Rcity)){
                                   ResCity_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   ResCity_combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<StateList.size();i++){
                               String c = StateList.get(i).toString();
                               if(c.equals(RState)){
                                   ResState_Combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   ResState_Combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<CountryList.size();i++){
                               String c = CountryList.get(i).toString();
                               if(c.equals(RCountry)){
                                   ResCountry_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   ResCountry_combo.setSelectedIndex(-1);
                               }
                           }
                           
                           
                       }
                   }
                   }
                   
                   
                   
                   if(obj4[1]!=null){
                    if(!obj4[1].equals("")){   
                       String ResStrFull=obj4[1].toString();
                       String OffArr[] = ResStrFull.split("#");
                       if(OffArr.length>0){
                           String Oline1=OffArr[0].toString();
                           String Oline2=OffArr[1].toString();
                           String Opost=OffArr[2].toString();
                           String Ocity=OffArr[3].toString();
                           String OState=OffArr[4].toString();
                           String OCountry=OffArr[5].toString();
                           String OPinNo=OffArr[6].toString();
                           
                           offLine1_text.setText(Oline1);
                           Off_line2_text.setText(Oline2);
                           OffPost_Text.setText(Opost);
                           OffPinNo_text.setText(OPinNo);
                           
                           for(int i=0;i<CityList.size();i++){
                               String c = CityList.get(i).toString();
                               if(c.equals(Ocity)){
                                   offCity_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   offCity_combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<StateList.size();i++){
                               String c = StateList.get(i).toString();
                               if(c.equals(OState)){
                                   OffState_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   OffState_combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<CountryList.size();i++){
                               String c = CountryList.get(i).toString();
                               if(c.equals(OCountry)){
                                   Off_Country_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   Off_Country_combo.setSelectedIndex(-1);
                               }
                           }
                           
                           
                       }
                       
                   }
                   }
                   
                   
                   if(obj4[2]!=null){
                       String m = obj4[2].toString();
                       MobileNo_text.setText(m);
                   }
                   
                   if(obj4[3]!=null){
                       String m = obj4[3].toString();
                       Residence_no_text.setText(m);
                   }
                   
                   if(obj4[4]!=null){
                       String m = obj4[4].toString();
                       OfficeNo_text.setText(m);
                   }
                   
                   if(obj4[5]!=null){
                       String m = obj4[5].toString();
                       if(m.equals("Residence")){
                           residence_Radio.setSelected(true);
                       }
                       else{
                           Office_Radio.setSelected(true);
                       }
                   }
                   
                   if(obj4[6]!=null){
                       String m = obj4[6].toString();
                       Email_Text.setText(m);
                   }
                   
                   if(obj4[7]!=null){
                       String m = obj4[7].toString();
                       Twitter_text.setText(m);
                   }
                   
               }
               
             // ************************************OTHER INFO PANEL ***********************************************************************************************  
               Object[] obj5 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MOBILETYPE,PROFFESION,CLUBACTIVITY,PLAYSPORTS,SpecialTalent,FACILITIES,RecreationalActivities FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               
               if(obj5!=null){
                   if(obj5[0]!=null){
                       String Mobtype=obj5[0].toString();
                       for(int i=0;i<MobileOsList.size();i++){
                           String x=MobileOsList.get(i).toString();
                           if(x.equals(Mobtype)){
                               MobilePhoneUse_Combo.setSelectedIndex(i);
                               break;
                           }
                           else{
                               MobilePhoneUse_Combo.setSelectedIndex(-1);
                           }
                       }
                   }
                   else{
                       MobilePhoneUse_Combo.setSelectedIndex(-1);
                       
                   }
                   if(obj5[1]!=null){
                       String Mobtype=obj5[1].toString();
                       for(int i=0;i<ProfessionList.size();i++){
                           String x=ProfessionList.get(i).toString();
                           if(x.equals(Mobtype)){
                               Proffesion_Combo.setSelectedIndex(i);
                               break;
                           }
                           else{
                               Proffesion_Combo.setSelectedIndex(-1);
                           }
                       }
                   }
                   else{
                       Proffesion_Combo.setSelectedIndex(-1);
                       
                   }
                   if(obj5[2]!=null){
                       SelectedActivityList = new ArrayList<String>();
                       String Act = obj5[2].toString();
                       String ActArr[]=Act.split("#");
                       for(int i=0;i<ActArr.length;i++){
                           String t = ActArr[i].toString();
                           SelectedActivityList.add(t);
                       }
                       Activities_jList.setModel(new MemberForm.ItemsListModel(SelectedActivityList));
                   }
                   
                   if(obj5[3]!=null){
                       String t=obj5[3].toString();
                       PlaySport_Text.setText(t);
                   }
                   
                   if(obj5[4]!=null){
                       String t=obj5[4].toString();
                       SpecialTalent_text.setText(t);
                   }
                   
                   if(obj5[5]!=null){
                       SelectedFacilityList= new ArrayList<String>();
                       String Facility = obj5[5].toString();
                       String FacArr []=Facility.split("#");
                       for(int i=0;i<FacArr.length;i++){
                           String t=FacArr[i].toString();
                           SelectedFacilityList.add(t);
                       }
                       Facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedFacilityList));
                   }
                   
                   if(obj5[6]!=null){
                       if(!obj5[6].equals("")){
                            String RaStr =obj5[6].toString();
                            String Recrea_Arr[] = RaStr.split("#");
                            for(int i=0;i<Recrea_Arr.length;i++){
                                String x =Recrea_Arr[i].toString();
                                String ReacAct[] = x.split("%");
                                //added by pratima:to reset recreational activity list table when select a member
                                RecreationalActivitiesListClass rclass= new RecreationalActivitiesListClass();
                                MemberFormTable_Model=MemberFormTable_Model.loademailGroupNameList(m_App,RecreationalActivityList);
                                RecreationalActivity_JTable.setModel(MemberFormTable_Model.getTableModel(RecreationalActivityList));
                                 //added by pratima
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
                  
               }
              // ******************************** ENDD ******************************************************************************  
               
               
              // ************************************ OTHER CLUB INFO ******************************************************************* 
               
               
               Object[] obj6 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT OTHERCLUB FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING })).find(Id); 
               
               if(obj6!=null){
                   OtherClubClassListAll_Class=new ArrayList<OtherClubClass>();
                   
                   if(obj6[0]!=null){
                       if(!obj6[0].equals("")){
                       String otherclubfull = obj6[0].toString();
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
                   
                   
               }
               
               Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT SD_SPORTS_DETAIL   FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id);
               SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();
               
               if(obj7!=null){
                   if(obj7[0]!=null){
                       if(!obj7[0].equals("")){
                            String SD_Sportfull = obj7[0].toString();
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
                
               Object[] obj8 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT  SD_RACTIVITY,SD_FACILITY ,SD_TALENT , SD_CLUBACTIVITY FROM KYMMEMBER where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{ Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE , Datas.SERIALIZABLE})).find(Id);
               SD_RActivity_ClassListAll = new ArrayList<SD_RActivity_Class>();
                             
                if(obj8!=null){ 
                    if(obj8[0]!=null){
                     if(!obj8[0].equals("")){   
                            String x = obj8[0].toString();
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
                      if(!obj8[1].equals("")){ 
                          SD_Facility_ClassListAll = new ArrayList<SD_Facility_Class>();
                        
                            String x = obj8[1].toString();
                            String StrArr[] = x.split("#");
                            for(int i=0;i<StrArr.length;i++){
                                String x1=StrArr[i].toString();
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
                      if(!obj8[2].equals("")){  
                        String x = obj8[2].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            String x1=StrArr[i].toString();
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
                        if(!obj8[3].equals("")){
                          String x = obj8[3].toString();
                          String StrArr[] = x.split("#");
                          for(int i=0;i<StrArr.length;i++){
                              String x1=StrArr[i].toString();
                              String SD_ActivityArr[] = x1.split("%");
                              if(SD_ActivityArr.length>1){
                                String SD_Name = SD_ActivityArr[0].toString();
                                String SD_Activity = SD_ActivityArr[1].toString();
                                SD_ClubActivity_Class sd = new SD_ClubActivity_Class();
                                sd.setSD_Name(SD_Name);
                                sd.setSD_Activity(SD_Activity);
                                SD_ClubActivity_ClassListAll.add(sd);
                                MemberFormTable_Model=MemberFormTable_Model.loadSD_ClubActivity_InfoAll(m_App,SD_ClubActivity_ClassListAll);
                                SD_ClubActivity_Jtable.setModel(MemberFormTable_Model.getSD_ClubActivity_TableModel(SD_ClubActivity_ClassListAll));
                                SD_ClubActivity_Jtable.setVisible(true);
                              }
                          }  
                            
                        }
                    }
                    
                    
                  }
                   
                   
                   
               
               // ***************** RECREATIONL ACTIVITES FOR SPOUSE/DEPENDENTS *****************************************
               
               
               
                System.out.println("Total Unfilled Marks : "+unfilledMarks);
               
                SD_List_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                sports_SD_Combo.setModel(SD_List_ComboBoxValModel);
                SD_Ractivity_ComboBoxValModel = new ComboBoxValModel(SD_listAll);
                SD_RActivity_Combo.setModel(SD_Ractivity_ComboBoxValModel);
                SD_FacilitySpouse_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_FacilitySpouce_Combo.setModel(SD_FacilitySpouse_ComboBoxValModel);
                SD_Talent_ComboBoxValModel=new ComboBoxValModel(SD_listAll);
                SD_talent_Combo.setModel(SD_Talent_ComboBoxValModel);
                SD_ClubActSpouce_ComboBoxValModel = new ComboBoxValModel(SD_listAll);
                SD_ClubActSDName_Combo.setModel(SD_ClubActSpouce_ComboBoxValModel);
                
                
                
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
    
    
    public void getFormFilledPercDetails(){
     int full_length = 0;  
     try{   
        Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT memberno,membername,DATEOFBIRTH,idproof,iduniqueno,membertype,\n" +
                    "membersince , SPOUCENAME , DATEOFMARRIAGE , fathername , fatherdob,\n" +
                    "mothername , motherdob , DEPENDENTDETAILS , resaddress , OFFADDRESS , \n" +
                    "mobileno , resno,officeno,commaddres,emailid,twitter,mobiletype,PROFFESION,\n" +
                    "clubactivity, playsports , facilities , RecreationalActivities ,  specialtalent , otherclub,\n" +
                    "fatherbg,fatherphoneno,fatheremailid,fatheridproof , fatheruniqueno, motherbg, motherphoneno,\n" +
                    "motheremailid,MOTHERIDPROOF , MOTHERUNIQUENO,SD_SPORTS_DETAIL,SD_RACTIVITY,SD_FACILITY,\n" +
                    "SD_TALENT from kymmember\n" +
                    "", 
                    SerializerWriteString.INSTANCE, new SerializerReadBasic(
                    new Datas[]{ 
                    Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING  ,Datas.STRING ,
                    Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP   ,  
                    Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING   , 
                    Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING  ,Datas.STRING,Datas.STRING,Datas.STRING   , 
                    Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING  ,Datas.STRING,
                    Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING  ,Datas.STRING,Datas.STRING,
                    Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.SERIALIZABLE  ,Datas.SERIALIZABLE ,
                    Datas.SERIALIZABLE
                    
                    })).find();
        
          
            if(obj1!=null){
                
            }        
        
     }
     catch(BasicException ex ){
        Logger.getLogger(MemberForm.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
     }
    }
    
   
    
    
    // ********************************************* HISTORY LOADING METHOD *********************************************** //// 
    
        public void getPreviousDetails(String Id) {
      try{   
           
            int unfilledMarks=0;
            
          
          SD_listAll = new ArrayList<String>();
          
          // ***********************  MAIN PAGE INFO **********************************************************
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MEMBERNAME,DATEOFBIRTH,IDPROOF,IDUNIQUENO,MEMBERTYPE,MEMBERSINCE,SPOUCENAME,DATEOFMARRIAGE,GENDER FROM KYMMEMBER_ARV where ID=? ", 
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
                  String idproof=obj1[2].toString();
                  for(int i=0;i<IDProofTypeList.size();i++){
                      String x=IDProofTypeList.get(i).toString();
                      if(x.equals(idproof)){
                          IDproofDoc_combo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  IDproofDoc_combo.setSelectedIndex(-1);
                  unfilledMarks++;
              }
              if(obj1[3]!=null){
                  idDocUniqueNo_text.setText(obj1[3].toString());
              }
              
              
              if(obj1[4]!=null){
                  String memtype=obj1[4].toString();
                  for(int i=0;i<MemberShipTypeList.size();i++){
                      String x=MemberShipTypeList.get(i).toString();
                      if(x.equals(memtype)){
                          TypeofMember_combo.setSelectedIndex(i);
                          break;
                      }
                  }
              }
              else{
                  TypeofMember_combo.setSelectedIndex(-1);
                  unfilledMarks++;
              }
              
              
              if(obj1[5]!=null){
                  Date memsince = (Date) obj1[5];
                  String X = formatter.format(memsince);
                  memberSince_text.setText(X);
              }
              
              if(obj1[6]!=null){
                  SpouseName_text.setText(obj1[6].toString());
                  SD_listAll.add(obj1[6].toString());
              }
             
              if(obj1[7]!=null){
                  Date dom = (Date) obj1[7];
                  String X = formatter.format(dom);
                  DateOfMarriage_text.setText(X);
              }
              
              if(obj1[8]!=null){
                 String g=obj1[8].toString().toLowerCase();
                 if(g.equals("male")){
                     male_radio.setSelected(true);
                 }
                 else{
                     female_radio.setSelected(true);
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
                          fatherDob_text.setText(X);
                    }
                    
                    if(obj2[2]!=null){
                        String fn = obj2[2].toString();
                        MotherName_text.setText(fn);
                    }
                    
                    if(obj2[3]!=null){
                         Date dom = (Date) obj2[3];
                          String X = formatter.format(dom);
                          MotherDOB_text.setText(X);
                    }
                    
                    if(obj2[4]!=null){
                        String Fatherbloodgrp= obj2[4].toString();
                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
                            String x= ChildrenBloodGrpListAll.get(i).toString();
                            if(x.equals(Fatherbloodgrp)){
                                FatherBloodGroup_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[5]!=null){
                        String fn = obj2[5].toString();
                        FatherPhoneNo_Text.setText(fn);
                    }
                    
                    if(obj2[6]!=null){
                        String fn = obj2[6].toString();
                        FatherEmailId_Text.setText(fn);
                    }
                   
                    if(obj2[7]!=null){
                        String FatherIdProof= obj2[7].toString();
                        for(int i=0;i<IDProofTypeList.size();i++){
                            String x= IDProofTypeList.get(i).toString();
                            if(x.equals(FatherIdProof)){
                                FatherIdProof_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[8]!=null){
                        String fn = obj2[8].toString();
                        FatherUniqueNo_text.setText(fn);
                    }
                    

                    if(obj2[9]!=null){
                        String Motherbloodgrp= obj2[9].toString();
                        for(int i=0;i<ChildrenBloodGrpListAll.size();i++){
                            String x= ChildrenBloodGrpListAll.get(i).toString();
                            if(x.equals(Motherbloodgrp)){
                                MotherBloodGroup_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[10]!=null){
                        String fn = obj2[10].toString();
                        MotherPhoneNo_text.setText(fn);
                    }
                    
                    if(obj2[11]!=null){
                        String fn = obj2[11].toString();
                        MotherEmailid_text.setText(fn);
                    }
                    
                    if(obj2[12]!=null){
                        String MotherIdProof= obj2[12].toString();
                        for(int i=0;i<IDProofTypeList.size();i++){
                            String x= IDProofTypeList.get(i).toString();
                            if(x.equals(MotherIdProof)){
                                MotherIdProof_combo.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    

                    if(obj2[13]!=null){
                        String fn = obj2[13].toString();
                        MotherUniqueNo_text.setText(fn);
                    }
                    



                }
               // ***********************  PARENTS INFO  ENDS **********************************************************
                
                
                
                
              // ***********************  CHILDREN INFO    **********************************************************  // 
               Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT DEPENDENTDETAILS FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id); 
            
               ChildrenClassList = new ArrayList<ChildrenClass>();
               
               if(obj3!=null){
                   if(obj3[0]!=null){
                     if(!obj3[0].equals("")){   
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
                                
                                Date Dob=formatter.parse(Cdob);
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
                        }
                        
                        
                        MemberFormTable_Model2=MemberFormTable_Model2.LoadChildrenClassInfo(m_App,ChildrenClassList);
                        children_Jtable.setModel(MemberFormTable_Model2.getTableModelForChildren(ChildrenClassList));
                        children_Jtable.setVisible(true);
                   }
                   }
                   
                }
               
               // *********************************** CHILDREN DETAILS ENDS HERE ***************************************************
               
               
               
               // *********************************** ADDRESS DETAILS STARTS HERE ***************************************************
              
               
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT RESADDRESS,OFFADDRESS,MOBILENO,RESNO,OFFICENO,COMMADDRES,EMAILID,TWITTER FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               if(obj4!=null){
                   if(obj4[0]!=null){
                     if(!obj4[0].equals("")){  
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
                           
                           Rline1Res_text.setText(Rline1);
                           RLine2Res_text.setText(Rline2);
                           ResPost_text.setText(Rpost);
                           ResPinNo_text.setText(PinNo);
                           
                           for(int i=0;i<CityList.size();i++){
                               String c = CityList.get(i).toString();
                               if(c.equals(Rcity)){
                                   ResCity_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   ResCity_combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<StateList.size();i++){
                               String c = StateList.get(i).toString();
                               if(c.equals(RState)){
                                   ResState_Combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   ResState_Combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<CountryList.size();i++){
                               String c = CountryList.get(i).toString();
                               if(c.equals(RCountry)){
                                   ResCountry_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   ResCountry_combo.setSelectedIndex(-1);
                               }
                           }
                           
                           
                       }
                   }
                   }
                   
                   
                   
                   if(obj4[1]!=null){
                    if(!obj4[1].equals("")){   
                       String ResStrFull=obj4[1].toString();
                       String OffArr[] = ResStrFull.split("#");
                       if(OffArr.length>0){
                           String Oline1=OffArr[0].toString();
                           String Oline2=OffArr[1].toString();
                           String Opost=OffArr[2].toString();
                           String Ocity=OffArr[3].toString();
                           String OState=OffArr[4].toString();
                           String OCountry=OffArr[5].toString();
                           String OPinNo=OffArr[6].toString();
                           
                           offLine1_text.setText(Oline1);
                           Off_line2_text.setText(Oline2);
                           OffPost_Text.setText(Opost);
                           OffPinNo_text.setText(OPinNo);
                           
                           for(int i=0;i<CityList.size();i++){
                               String c = CityList.get(i).toString();
                               if(c.equals(Ocity)){
                                   offCity_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   offCity_combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<StateList.size();i++){
                               String c = StateList.get(i).toString();
                               if(c.equals(OState)){
                                   OffState_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   OffState_combo.setSelectedIndex(-1);
                               }
                           }
                           for(int i=0;i<CountryList.size();i++){
                               String c = CountryList.get(i).toString();
                               if(c.equals(OCountry)){
                                   Off_Country_combo.setSelectedIndex(i);
                                   break;
                               }
                               else{
                                   Off_Country_combo.setSelectedIndex(-1);
                               }
                           }
                           
                           
                       }
                       
                   }
                   }
                   
                   
                   if(obj4[2]!=null){
                       String m = obj4[2].toString();
                       MobileNo_text.setText(m);
                   }
                   
                   if(obj4[3]!=null){
                       String m = obj4[3].toString();
                       Residence_no_text.setText(m);
                   }
                   
                   if(obj4[4]!=null){
                       String m = obj4[4].toString();
                       OfficeNo_text.setText(m);
                   }
                   
                   if(obj4[5]!=null){
                       String m = obj4[5].toString();
                       if(m.equals("Residence")){
                           residence_Radio.setSelected(true);
                       }
                       else{
                           Office_Radio.setSelected(true);
                       }
                   }
                   
                   if(obj4[6]!=null){
                       String m = obj4[6].toString();
                       Email_Text.setText(m);
                   }
                   
                   if(obj4[7]!=null){
                       String m = obj4[7].toString();
                       Twitter_text.setText(m);
                   }
                   
               }
               
             // ************************************OTHER INFO PANEL ***********************************************************************************************  
               Object[] obj5 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT MOBILETYPE,PROFFESION,CLUBACTIVITY,PLAYSPORTS,SpecialTalent,FACILITIES,RecreationalActivities FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING ,Datas.STRING  })).find(Id); 
               
               
               if(obj5!=null){
                   if(obj5[0]!=null){
                       String Mobtype=obj5[0].toString();
                       for(int i=0;i<MobileOsList.size();i++){
                           String x=MobileOsList.get(i).toString();
                           if(x.equals(Mobtype)){
                               MobilePhoneUse_Combo.setSelectedIndex(i);
                               break;
                           }
                           else{
                               MobilePhoneUse_Combo.setSelectedIndex(-1);
                           }
                       }
                   }
                   else{
                       MobilePhoneUse_Combo.setSelectedIndex(-1);
                       
                   }
                   if(obj5[1]!=null){
                       String Mobtype=obj5[1].toString();
                       for(int i=0;i<ProfessionList.size();i++){
                           String x=ProfessionList.get(i).toString();
                           if(x.equals(Mobtype)){
                               Proffesion_Combo.setSelectedIndex(i);
                               break;
                           }
                           else{
                               Proffesion_Combo.setSelectedIndex(-1);
                           }
                       }
                   }
                   else{
                       Proffesion_Combo.setSelectedIndex(-1);
                       
                   }
                   if(obj5[2]!=null){
                       String Act = obj5[2].toString();
                       String ActArr[]=Act.split("#");
                       for(int i=0;i<ActArr.length;i++){
                           String t = ActArr[i].toString();
                           SelectedActivityList.add(t);
                       }
                       Activities_jList.setModel(new MemberForm.ItemsListModel(SelectedActivityList));
                   }
                   
                   if(obj5[3]!=null){
                       String t=obj5[3].toString();
                       PlaySport_Text.setText(t);
                   }
                   
                   if(obj5[4]!=null){
                       String t=obj5[4].toString();
                       SpecialTalent_text.setText(t);
                   }
                   
                   if(obj5[5]!=null){
                       String Facility = obj5[5].toString();
                       String FacArr []=Facility.split("#");
                       for(int i=0;i<FacArr.length;i++){
                           String t=FacArr[i].toString();
                           SelectedFacilityList.add(t);
                       }
                       Facility_Jlist.setModel(new MemberForm.ItemsListModel(SelectedFacilityList));
                   }
                   RecreationalActivityList = new ArrayList<RecreationalActivitiesListClass>();
                   if(obj5[6]!=null){
                       if(!obj5[6].equals("")){
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
                  
               }
              // ******************************** ENDD ******************************************************************************  
               
               
              // ************************************ OTHER CLUB INFO ******************************************************************* 
               
               
               Object[] obj6 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT OTHERCLUB FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING })).find(Id); 
               
               if(obj6!=null){
                   OtherClubClassListAll_Class=new ArrayList<OtherClubClass>();
                   
                   if(obj6[0]!=null){
                       if(!obj6[0].equals("")){
                       String otherclubfull = obj6[0].toString();
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
                   
                   
               }
               
               Object[] obj7 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT SD_SPORTS_DETAIL   FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(Id);
               SD_Sports_ClassListAll=new ArrayList<SD_Sports_Class>();
               if(obj7!=null){
                   if(obj7[0]!=null){
                       if(!obj7[0].equals("")){
                            String SD_Sportfull = obj7[0].toString();
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
                
               Object[] obj8 = (Object[]) new StaticSentence(m_App.getSession(),
                  "SELECT  SD_RACTIVITY,SD_FACILITY ,SD_TALENT FROM KYMMEMBER_ARV where ID=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{ Datas.SERIALIZABLE ,Datas.SERIALIZABLE,Datas.SERIALIZABLE})).find(Id);
               SD_RActivity_ClassListAll = new ArrayList<SD_RActivity_Class>();
                if(obj8!=null){ 
                    if(obj8[0]!=null){
                     if(!obj8[0].equals("")){   
                            String x = obj8[0].toString();
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
                    
                    SD_Facility_ClassListAll = new ArrayList<SD_Facility_Class>();
                    if(obj8[1]!=null){
                      if(!obj8[1].equals("")){  
                            String x = obj8[1].toString();
                            String StrArr[] = x.split("#");
                            for(int i=0;i<StrArr.length;i++){
                                String x1=StrArr[i].toString();
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
                    
                    SD_Talent_ClassListAll=new ArrayList<SD_Talent_Class>();
                    if(obj8[2]!=null){
                      if(!obj8[2].equals("")){  
                        String x = obj8[2].toString();
                        String StrArr[] = x.split("#");
                        for(int i=0;i<StrArr.length;i++){
                            String x1=StrArr[i].toString();
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
                    
                    
                    
                  }
                   
                  Object[] obj9 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT deacref FROM KYMMEMBER_ARV where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Id);
                    if(obj9!=null){
                        if(obj9[0]!=null){
                            DeactRefIDForHistory = obj9[0].toString();
                        }
                     } 
                   
               
               // ***************** RECREATIONL ACTIVITES FOR SPOUSE/DEPENDENTS *****************************************
               
               
               
                System.out.println("Total Unfilled Marks : "+unfilledMarks);
               
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
    
        
        
        
        
}
