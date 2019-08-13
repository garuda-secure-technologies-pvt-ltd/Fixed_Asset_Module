 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GeneralSettingsAccounting.java
 *
 * Created on May 12, 2009, 10:15:45 AM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sms.EmailMaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.ButtonGroup;
import com.openbravo.format.Formats;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.sms.EmailMasterTableModel;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.util.Calendar;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;



/**
 *
 * @author swathi
 */
public class GeneralSettingsAccounting extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /** Creates new form GeneralSettingsAccounting */
    private AppView m_App;
    private List arr;
    private ComboBoxValModel clmodel;
    private ComboBoxValModel stmodel;
    private ComboBoxValModel pcmodel;
    private ComboBoxValModel pcmodel1;
    private ComboBoxValModel pcmodel2;
    private ComboBoxValModel inventorycraccountmodel;
    private ComboBoxValModel inventorydraccountmodel;
    private ComboBoxValModel peoplemodel;
    private ComboBoxValModel kioskmodel;
    private ComboBoxValModel Account_andrd;
    private ComboBoxValModel Account_andrd2;
    private ComboBoxValModel peoplemodel8;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private ComboBoxValModel JRole_List_Model;
    private DataLogicFacilities dlfac;
    private String clevyp=null;
    private String clacc=null;
    private String stacc=null;
    private String pettycashacc=null;
    private String pettycashincharge=null;
    private String previousYearIncomeHead = null;
    private String nextYearIncomeHead = null;
    private String memberkioskincharge = null;
    private String inventoryAccountDebt = null;
    private String inventoryAccountCr = null;
    private String Demo = null;
    private String memstatPDF_folder = null;
    private String GenEmailDocPath = null;
    private String SendEmail = null;
    private ComboBoxValModel EmailModel;  
    private ComboBoxValModel FacilityModel; 
    private String urlPath = null;
    private String GenRecieptAccount = null;
    private ComboBoxValModel AndPeopleModel; 
   private ComboBoxValModel locationModel;//pratima
   private List<LocationInfo> locList;
    private ComboBoxValModel addedlocationModel;
   private List<LocationInfo> addedlocList;
   
    private JTextComponent editor;
    private JTextComponent servtaxeditor;
    private DataLogicSales m_dlSales;
    private Map<String, GeneralSettingInfo> GeneralSettingsMap = new HashMap<String, GeneralSettingInfo>();
    
    private List<String> MemPhotoEditList = new ArrayList<String>();
    
// for Billing Member changes
    
    private List<Object> SelectedFacilityList=new ArrayList<Object>();
    private List<String> SelectedPeopleForPurchaseAppList = new ArrayList<String>();
    //Added by guru
    List<AccountMaster> TempAccList1 =  new ArrayList<AccountMaster>();
    // BOOKING ----------- #AAKASH
    
    private ComboBoxValModel peoplemodel2;
    
    
    public GeneralSettingsAccounting() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {
       m_App=app;
       dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
		groupButton();
                groupButton1();
                groupButton2();
                groupButton3();
                groupButton4();
                groupButton5();
                groupButton15();
                groupButton16();
                groupButton17();
                
                 try {
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Credit check for QT'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
             Boolean v = (Boolean)obj[0];
                     if(v==true){
                         jRadioButton1.setSelected(true);
                         jLabel8.setVisible(true);
                         jRadioButton3.setVisible(true);
                         jRadioButton4.setVisible(true);
                         jRadioButton9.setVisible(true);
             Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Credit check for Guests'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
             Boolean v1 = (Boolean)obj1[0];
                     if(v1==true){
                         jRadioButton3.setSelected(true);
                     }else{
                         jRadioButton4.setSelected(true);
                     }
                     }else{
                         jRadioButton2.setSelected(true);
                         jRadioButton10.setSelected(true);
                         jLabel8.setVisible(false);
                         jRadioButton3.setVisible(false);
                         jRadioButton4.setVisible(false);
                     }
                     
                     Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Credit Check for Guest Entry Through Member Kiosk'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                      Boolean v2 = (Boolean)obj2[0];
                     if(v2==true){
                         jRadioButton5.setSelected(true);
                     }else{
                         jRadioButton6.setSelected(true);
                     }
                     
                     Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Reports To Use Manual Inventory Entry'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                     Boolean v1 = (Boolean)obj1[0];
                    jRadioButton7.setSelected(v1);
                    jRadioButton10.setSelected(v1);
                    
                    
                     Object[] obj15 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Guest Room Card Access Flag'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                     if(obj15!=null){
                         Boolean v15 = (Boolean)obj15[0];
                         if(v15){
                             yes_allowGuest_Radio.setSelected(true);
                         }
                         else{
                             No_allowGuest_Radio.setSelected(true);
                         }
                         
                        
                     }
                     else{
                         yes_allowGuest_Radio.setSelected(true);
                     }
                    
                     Object[] obj16 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Party Hall Card Access Flag'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                     if(obj16!=null){
                         Boolean v16 = (Boolean)obj16[0];
                         if(v16){
                             jRadioButton11.setSelected(true);
                         }
                         else{
                              jRadioButton12.setSelected(true);
                         }
                         
                        
                     }
                     else{
                          jRadioButton11.setSelected(true);
                     }
                     
                     Object[] obj17 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Card Access Type GuestRooms and PartyHall'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                     if(obj17!=null){
                         String  v17 = (String)obj17[0];
                         if(v17.equals("1")){
                             onlyMember_Radio.setSelected(true);
                         }
                         else if(v17.equals("2")){
                             memSpouse_radio.setSelected(true);
                         }
                         else{
                             Mem_Dependant_Radio.setSelected(true);
                         }
                         
                     }
                     else{
                         onlyMember_Radio.setSelected(true);
                     }
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                   
     // EDITED BY AKASH FOR VOUCHER ENTRY RESTRICTIONS 
                     
                      Object[] obj21 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Restricted From'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj21!=null){
                          From_VoucherEntry_Dt = obj21[0].toString();
                          fromdate_text.setText(From_VoucherEntry_Dt);
                          yes_VoucherRestrict_radio.setSelected(true);
                          Object[] obj22 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Restricted Upto'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                          if(obj22!=null){
                              To_VoucherEntry_Dt = obj22[0].toString();
                              toDate_text.setText(To_VoucherEntry_Dt);
                          }
                      }
                      else{
                          no_VoucherRestrict_radio.setSelected(true);
                          fromdate_text.setText(null);
                          toDate_text.setText(null);
                          
                      }
                     
                      
                      Object[] obj23 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Voucher Entry Approval Flag'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj23!=null){
                           int x= Integer.parseInt(obj23[0].toString());
                           if(x==1){
                                Yes_VoucherApproval_Radio.setSelected(true);
                           }else{
                               No_voucherApproval_Radio.setSelected(true);
                           }
                          
                      }
                      else{
                          No_voucherApproval_Radio.setSelected(true);
                      }
           // 1.            
                      Object[] obj24 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Receipt Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj24!=null){
                           receipt_check.setSelected(true);
                           receipt_text.setVisible(true);
                           int x = Integer.parseInt(obj24[0].toString());
                           receipt_text.setText(x+"");
                      }
                      else{
                          receipt_check.setSelected(false);
                          receipt_text.setVisible(false);
                          receipt_text.setText(null);
                      }
           // 2.            
                      Object[] obj25 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Payment Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj25!=null){
                          payment_check.setSelected(true);
                           payment_text.setVisible(true);
                           int x = Integer.parseInt(obj25[0].toString());
                           payment_text.setText(x+"");
                      }
                      else{
                          payment_check.setSelected(false);
                          payment_text.setVisible(false);
                          payment_text.setText(null);
                      }
                      
          // 3.             
                      Object[] obj26 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Journal Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj26!=null){
                           journal_check.setSelected(true);
                           journal_text.setVisible(true);
                           int x = Integer.parseInt(obj26[0].toString());
                           journal_text.setText(x+"");
                      }
                      else{
                          journal_check.setSelected(false);
                          journal_text.setVisible(false);
                          journal_text.setText(null);
                      }
                      
       // 4.                
                      Object[] obj27 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Max No. of Contra Voucher Entries'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj27!=null){
                          contra_check.setSelected(true);
                           contra_text.setVisible(true);
                           int x = Integer.parseInt(obj27[0].toString());
                           contra_text.setText(x+"");
                      }
                      else{
                          contra_check.setSelected(false);
                          contra_text.setVisible(false);
                          contra_text.setText(null);
                      }
                      
                    
                   Object[] obj20 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Crossing of products horizontally'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                    if(obj20!=null){ 
                    
                        Boolean v20 = (Boolean)obj20[0];
                        if(v20){
                             crossing_yes_radio.setSelected(true);
                        }
                        else{
                             crossing_no_radio.setSelected(false);
                        }
                       
                    }
                    else{
                        crossing_no_radio.setSelected(true);
                    }
                      
          // ***********************************LLOOPP    MINIMUM USAGE INCLUDING GUEST BILLING **************************************
                    
                    Object[] objMinimum = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Minimum Usage Guest Billing'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                    if(objMinimum!=null){ 
                        Boolean v21 = (Boolean)objMinimum[0];
                        if(v21){
                             Minimum_yes_radio.setSelected(true);
                        }
                        else{
                             minimum_no_radio.setSelected(true);
                             
                        }
                    }
                    else{
                        minimum_no_radio.setSelected(true);
                    }
                    
                    try{
                        InetAddress IP=InetAddress.getLocalHost();
                        String IPAddress = IP.getHostAddress();
                    
                    
                        Object[] objMemPhoto = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(IPAddress);
                            if(objMemPhoto!=null){
                                if(objMemPhoto[0]!=null){
                                    String x = objMemPhoto[0].toString();
                                    memberPhoto_destination_text.setText(x);
                                }
                                else{
                                     memberPhoto_destination_text.setText(null);
                                }
                            }
                            else{
                                memberPhoto_destination_text.setText(null);
                            }
                    
                    }
                    catch(UnknownHostException e){
                        e.printStackTrace();
                    }
                    
                    
                    // ****************************************************************************
                    
                   Object[] objMemStatNote = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='MemberStatement Report with only Note'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                    if(objMemStatNote!=null){ 
                        Boolean v21 = (Boolean)objMemStatNote[0];
                        if(v21){
                             memStatReport_YesRadio.setSelected(true);
                        }
                        else{
                             memStatReport_NoRadio.setSelected(true);
                             
                        }
                    }
                    else{
                        memStatReport_NoRadio.setSelected(true);
                    }
               
                    
                    
   /////// EDITED FOR EXPIRY DATE 
   
                    Object[] objEXP = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Validate'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                    if(objEXP!=null){  
                       if(objEXP!=null){ 
                        String vEXP = (String)objEXP[0].toString();
                        String []ArrDate = vEXP.split("#");
                        int year = Integer.parseInt( ArrDate[0].toString());
                        int Month = Integer.parseInt( ArrDate[1].toString()); 
                        int Day = Integer.parseInt( ArrDate[2].toString());
                        int y2 = year/13;
                        int m2 = Month/13;
                        int D2 = Day/13;
                        
                        Calendar c1 = GregorianCalendar.getInstance();
                        c1.set(y2, m2, D2);  //January 30th 2000
                        Date sDate = c1.getTime();
                        expriryDate_text.setText(Formats.TIMESTAMP.formatValue(sDate));       
                        
                       }
                       
                    
                    }
                    
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                  
                    
                  // Edited by aakash for email settings
                    
                //   Object[] EMailobj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Mem Stat Email Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
                 //   if(EMailobj1!=null ){
                    
                 //   String Email = EMailobj1[0].toString();
                 //   List<Object> EmailAccountList1=getEmailAccntList(m_App);
                 //   EmailModel=new ComboBoxValModel(EmailAccountList1);
                  //  EmailAccountList_Combo.setModel(EmailModel);
                    
                    
                 //   for(int i=0;i<EmailAccountList1.size();i++){
                  //      String x = EmailAccountList1.get(i).toString();
                 //       if(x.equals(Email)){
                 //           EmailAccountList_Combo.setSelectedIndex(i);
                 //       }
                        
                        
               //     }
                    
                   
                   
               //     }
// for Billing Member Changes 
                    
               Object[] obj5 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Credit Check for billing member'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
               if(obj5!=null){
                   
                  Boolean v5 = (Boolean)obj5[0];
                     if(v5){
                         all_Facility_Radio.setSelected(true);
                     }else{
                         other_fac_radio.setSelected(true);
                         jPanel5.setVisible(true);
                         SelectedFacilityList = new ArrayList();
                         Object[] obj6 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Facility for billing member'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                         if(obj6!=null){
                             String AllFac = obj6[0].toString();
                             String[] fac = AllFac.split("#");
                             for(int i=0;i<fac.length;i++){
                                 String f1 = fac[i].toString();
                                 String Facname = null;
                                 try{
                                     Facname = getFacilityNameByID(app, f1);
                                     SelectedFacilityList.add(Facname);
                                 }
                                 catch(BasicException ex){
                                     
                                 }
                                 
                             }
                            Facility_JList.setModel(new ItemsListModel( SelectedFacilityList));
                             
                         }
                         else{
                             SelectedFacilityList = new ArrayList();
                             Facility_JList.setModel(new ItemsListModel( SelectedFacilityList));
                         }
                    }       
               }
               else{
                  System.out.println("Credit Check for billing member is null");
                  all_Facility_Radio.setSelected(true); 
               }
               
               
               // CHANGED FOR PHOTO PANEL USER PERMISSION 
               
               Object[] obj81 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='User Permission list to edit member photo'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                    if(obj81!=null){
                        MemPhotoEditList = new ArrayList<String>();
                        String User_StrFull = obj81[0].toString();
                        String User_Arr[] = User_StrFull.split("#");
                        for(int i=0;i<User_Arr.length;i++){
                            String x = User_Arr[i].toString();
                            if(x.equals("ALL")){
                                //MemPhotoEditList.add("ALL");
                                //PhotoEditUser_JList.setModel(new ItemsListModel( MemPhotoEditList));
                                All_users_check.setSelected(true);
                                break;
                            }
                            else{
                                MemPhotoEditList.add(x);
                                PhotoEditUser_JList.setModel(new ItemsListModel( MemPhotoEditList));
                            }
                        }
                        
                        
                    }
                    else{
                        MemPhotoEditList = new ArrayList<String>();
                        PhotoEditUser_JList.setModel(new ItemsListModel( MemPhotoEditList));
                    }
               
                /////////////////////////////////////by pratima :fixed asset changes
                    Object[] obj82 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='AssetPhotos'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj82!=null){
                           AssetPhotoFolderText.setText(obj82[0].toString());
                      }
                      Object[] obj83 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Guest Room Checkout Strict'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                      if(obj83!=null){
                           jTextField3.setText(obj83[0].toString());
                      }
                  ///////////////////////////////////////////////////////////////////
               
// Changes Done for Billling Members               
               
                  
               
               
               
                    
              loaddata_for_rooms();
                     
                     
                     
                     
                    
                    
                 } catch (BasicException ex) {
                     ex.printStackTrace();
                     Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 
                
    }

     public String getTitle() {
       return "General Settings";
    }

    public void activate() throws BasicException {
        List<PeopleInfo> plist=m_dlSales.getPeopleList().list();
        peoplemodel=new ComboBoxValModel(plist);
        
        List<PeopleInfo> plist8=m_dlSales.getRoleList().list();
        peoplemodel8=new ComboBoxValModel(plist8);
        UserList_Combo.setModel(peoplemodel8);
        
        kioskmodel = new ComboBoxValModel(plist);
        kioskincharge.setModel(kioskmodel);
        kioskincharge.setSelectedIndex(-1);
        pcincharge.setModel(peoplemodel);
        pcincharge.setSelectedIndex(-1);
        List<AccountMasterExt> sacclist=dlfac.getaccounts();
        clmodel=new ComboBoxValModel(sacclist);
        claccount.setModel(clmodel);
        claccount.setSelectedIndex(-1);
        pcmodel=new ComboBoxValModel(sacclist);
        pcmodel1=new ComboBoxValModel(sacclist);
        pcmodel2=new ComboBoxValModel(sacclist);
        inventorycraccountmodel = new ComboBoxValModel(sacclist);
        inventorydraccountmodel = new ComboBoxValModel(sacclist);
        inventoryDebtAcc.setModel(inventorydraccountmodel);
        inventoryCrAcc.setModel(inventorycraccountmodel);
        inventoryDebtAcc.setSelectedIndex(-1);
        inventoryCrAcc.setSelectedIndex(-1);
        pcaccount.setModel(pcmodel);
        pcaccount.setSelectedIndex(-1);
        pYearIncomeHead.setModel(pcmodel1);
        nYearIncomeHead.setModel(pcmodel2);
        pYearIncomeHead.setSelectedIndex(-1);
        nYearIncomeHead.setSelectedIndex(-1);
        editor=(JTextComponent)claccount.getEditor().getEditorComponent();
        editor.setText(null);
        editor.addKeyListener(new Comboboxlistenner());
        stmodel=new ComboBoxValModel(sacclist);
        staxacc.setModel(stmodel);
        staxacc.setSelectedIndex(-1);
        servtaxeditor=(JTextComponent)staxacc.getEditor().getEditorComponent();
        servtaxeditor.setText(null);
        servtaxeditor.addKeyListener(new Comboboxlistenner1());
        arr=new ArrayList();
        jTextField1.setText(urlPath);
       
        action_combo.setSelectedIndex(-1);
        
        List<Object> EmailAccountList=  getEmailAccntList(m_App);
        EmailModel=new ComboBoxValModel(EmailAccountList);
        EmailAccountList_Combo.setModel(EmailModel);
        
// Changes for Billing Members
        
        List<Object> Facilitylist_All=  getFacilityListAll(m_App);
        FacilityModel=new ComboBoxValModel(Facilitylist_All);
        Facility_combo.setModel(FacilityModel);
        
        
        List<Object> AndPeople_All = getPeopleListForAndroidUser(m_App);
        AndPeopleModel = new ComboBoxValModel(AndPeople_All);
        AndroidPeople_combo.setModel(AndPeopleModel);
        
        
        
        
        
        
        SelectedPeopleForPurchaseAppList = new ArrayList<String>();
        SelectedPeopleForPurchaseAppList = getSelectedUserForAndroidApp(m_App);
        AndroidPeople_jList.setModel(new ItemsListModel( SelectedPeopleForPurchaseAppList));
        
        //pratima pending bill  intrest comboboxes
        jComboBoxIntrestAcc.setModel(clmodel);
         locList= m_dlSales.getLocationsList().list();
         
         locationModel= new ComboBoxValModel(locList);
         jComboBoxIntrestWareouse.setModel(locationModel);
         //jList2.setModel(locationModel);
         getPendingBillIntrestDetails();
   // edited by akash for android general receipts
        
        
       // Account_andrd=new ComboBoxValModel(sacclist);
       // Account_Combo.setModel(Account_andrd);
        
       // List<Object> GeneralReceiptAccList = new ArrayList<Object>();
      //  Account_andrd2=new ComboBoxValModel(GeneralReceiptAccList);
       // Account_combo2.setModel(Account_andrd2);
        
      //  List<RoleInfo> Rlist=m_dlSales.getRoleList().list();
       // JRole_List_Model = new ComboBoxValModel(Rlist);
       // JRole_List.setModel(JRole_List_Model);
        
        
        
        /*List<Object[]> data=new StaticSentence(m_App.getSession()
                , "SELECT NAME,VALUE FROM GENERALTABLE "
                ,null
                , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING})).list();*/
        LookupUtilityImpl.getInstance(null).refresh();
       GeneralSettingsMap=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
       // Object[] detail=(Object[])data.get(0);
       // for(int i=0;i<GeneralSettingsMap.size();i++){
         GeneralSettingInfo detail=GeneralSettingsMap.get("Canteen Levy");
         if(detail !=null && detail.getName().equals("Canteen Levy")){
           clevy.setText(detail.getValue());
           clevyp=detail.getValue();
         }
         detail=GeneralSettingsMap.get("Canteen Levy Account");
         if(detail !=null && detail.getName().equals("Canteen Levy Account")){
           clmodel.setSelectedKey(detail.getValue());
           clacc=detail.getValue();
         }
         detail=GeneralSettingsMap.get("Service Tax Account");
         if(detail !=null && detail.getName().equals("Service Tax Account")){
            stmodel.setSelectedKey(detail.getValue());
            stacc=detail.getValue();
         }
         detail=GeneralSettingsMap.get("Petty Cash Account");
         if(detail !=null && detail.getName().equals("Petty Cash Account")){
            pcmodel.setSelectedKey(detail.getValue());
            pettycashacc=detail.getValue();
         }
         detail=GeneralSettingsMap.get("Petty Cash Incharge");
         if(detail !=null && detail.getName().equals("Petty Cash Incharge")){
            peoplemodel.setSelectedKey(detail.getValue());
            pettycashincharge=detail.getValue();
         }
         detail=GeneralSettingsMap.get("prevFySubIncome");
         if(detail !=null && detail.getName().equals("prevFySubIncome")){
            pcmodel1.setSelectedKey(detail.getValue());
            previousYearIncomeHead=detail.getValue();
         }
         detail=GeneralSettingsMap.get("nextFySubIncome");
         if(detail !=null && detail.getName().equals("nextFySubIncome")){
            pcmodel2.setSelectedKey(detail.getValue());
            nextYearIncomeHead=detail.getValue();
         }
         
         detail=GeneralSettingsMap.get("Member Kiosk Incharge");
         if(detail !=null && detail.getName().equals("Member Kiosk Incharge")){
            kioskmodel.setSelectedKey(detail.getValue());
            memberkioskincharge=detail.getValue();
         }
         
        
         
         detail=GeneralSettingsMap.get("Accout Debit (Inventory)");
         if(detail !=null && detail.getName().equals("Accout Debit (Inventory)")){
            inventorydraccountmodel.setSelectedKey(detail.getValue());
            inventoryAccountDebt=detail.getValue();
         }
         
         detail=GeneralSettingsMap.get("Account Credit (Income and Expand)");
         if(detail !=null && detail.getName().equals("Account Credit (Income and Expand)")){
            inventorycraccountmodel.setSelectedKey(detail.getValue());
            inventoryAccountCr=detail.getValue();
         }
         
       try {     
        urlPath=(String) new StaticSentence(m_App.getSession()
                    ,"SELECT  VALUE FROM MEMPHOTOPATH WHERE NAME=?" ,SerializerWriteString.INSTANCE
                    ,SerializerReadString.INSTANCE).find("Member Photo Path");
        
       jTextField2.setText(urlPath);
        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }
         detail=GeneralSettingsMap.get("Member Photo Path");
         if(detail !=null && detail.getName().equals("Member Photo Path")){
            jTextField2.setText(detail.getValue());
            urlPath=detail.getValue();
         }
         
         
         // for email and sms -----------------------------------------------------------------------------------
         
         detail=GeneralSettingsMap.get("Member Stat PDF Folder Path");
         if(detail !=null && detail.getName().equals("Member Stat PDF Folder Path")){
            memstatPDF_folder_text.setText(detail.getValue().toString());
            memstatPDF_folder = detail.getValue();
            
         }
         
         detail=GeneralSettingsMap.get("General Email Documents");
         if(detail !=null && detail.getName().equals("General Email Documents")){
            gen_Email_Path_Text.setText(detail.getValue().toString());
            GenEmailDocPath = detail.getValue();
            
         }
         
         
         detail=GeneralSettingsMap.get("Email Mem Stat Yes or No");
         if(detail !=null && detail.getName().equals("Email Mem Stat Yes or No")){
            
            SendEmail =  detail.getValue().toString();
            if(SendEmail.equals("YES")){
                sendMail_check.setSelected(true);
            } 
            else{
                sendMail_check.setSelected(false);
            }
            
         }
         
         
         
         
         
         
         
       //  detail=GeneralSettingsMap.get("Mem Stat Email Account");
        // if(detail !=null && detail.getName().equals("Mem Stat Email Account")){
       //     EmailModel.setSelectedKey(detail.getValue());
          
        // }
         
         
         
         
        // CODE CHANGE FOR BOOKING....... 
         
       
         List<PeopleInfo> plist2=m_dlSales.getPeopleList().list();
        
         peoplemodel2=new ComboBoxValModel(plist2);
        
         peopleList_Combo.setModel(peoplemodel2);
         
         
         
         detail=GeneralSettingsMap.get("Booking Discount Permission");
         if(detail !=null && detail.getName().equals("Booking Discount Permission")){
            peoplemodel2.setSelectedKey(detail.getValue());
            for(int i=0;i<peoplemodel2.getSize();i++){
                String x = peoplemodel2.getElementAt(i).toString();
                if(x.equals(detail.getValue())){
                   peopleList_Combo.setSelectedIndex(i);  
                }
            }
            
           
            
         }
         
   // edited by aakash for android general receipt.
         
         detail=GeneralSettingsMap.get("General Receipt Accounts");
         if(detail !=null && detail.getName().equals("General Receipt Accounts")){
           
            GenRecieptAccount=detail.getValue();
            System.out.println("GenRecieptAccount  :  "  + GenRecieptAccount );
         }
         
     
         
        Object[] EMailobj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Mem Stat Email Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
         if(EMailobj1!=null ){
                    
                    String Email = EMailobj1[0].toString();
                    List<Object> EmailAccountList1=getEmailAccntList(m_App);
                    EmailModel=new ComboBoxValModel(EmailAccountList1);
                    EmailAccountList_Combo.setModel(EmailModel);
                    
                    
                    for(int i=0;i<EmailAccountList1.size();i++){
                        String x = EmailAccountList1.get(i).toString();
                        if(x.equals(Email)){
                            EmailAccountList_Combo.setSelectedIndex(i);
                        }
                        
                        
                    } 
         
          }
         
         
       // }
       
    }
     private boolean isAlpha(String s)
    {
       s = s.toUpperCase();
      for (int i = 0; i < s.length(); i ++)
      {
       int c = (int) s.charAt(i);
      if ((c < 65 || c > 90) && (c<47 || c>58) )
       return false;
      }
        return true;
     }

    private void memnoKeyPressed() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
     public class Comboboxlistenner extends  KeyAdapter{
         public void keyReleased(KeyEvent e) {
             try{
                 //!e.getKeyText(e.getKeyCode()).equals("Enter")

                 String text=editor.getText();
                 if(isAlpha(String.valueOf(e.getKeyChar())) || e.getKeyText(e.getKeyCode()).equals("Backspace")){//|| !e.getKeyText(e.getKeyCode()).equals("Backspace")){
                   clmodel=new ComboBoxValModel(dlfac.getsubAccounts(text.toUpperCase()));
                   claccount.setModel(clmodel);
                   editor.setText(text);
                   claccount.showPopup();

                 }else if(e.getKeyText(e.getKeyCode()).equals("Enter")){
                    if(clmodel.getSize()<=0){
                       editor.setText(null);
                    }
                 }
             }
             catch(Exception e1){
               e1.printStackTrace();
             }
         }
     }
     public class Comboboxlistenner1 extends  KeyAdapter{
         public void keyReleased(KeyEvent e) {
             try{
                 String text=servtaxeditor.getText();
                 if(isAlpha(String.valueOf(e.getKeyChar())) || e.getKeyText(e.getKeyCode()).equals("Backspace")){//|| !e.getKeyText(e.getKeyCode()).equals("Backspace")){
                   stmodel=new ComboBoxValModel(dlfac.getsubAccounts(text.toUpperCase()));
                   staxacc.setModel(stmodel);
                   servtaxeditor.setText(text);
                   staxacc.showPopup();

                 }else if(e.getKeyText(e.getKeyCode()).equals("Enter")){
                    if(stmodel.getSize()<=0){
                       servtaxeditor.setText(null);
                    }
                 }
             }
             catch(Exception e1){
               e1.printStackTrace();
             }
         }
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
    
   private void groupButton() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(jRadioButton1);
        bg1.add(jRadioButton2);
    }
    private void groupButton2() {
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(jRadioButton5);
        bg1.add(jRadioButton6);
    }
   private void groupButton1() {
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(jRadioButton3);
        bg2.add(jRadioButton4);
    }
   private void groupButton3() {
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(jRadioButton7);
        bg2.add(jRadioButton8);
    }
    private void groupButton4() {
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(jRadioButton9);
        bg2.add(jRadioButton10);
    }
    private void groupButton5() {
        ButtonGroup bg5 = new ButtonGroup();
        bg5.add(all_Facility_Radio);
        bg5.add(other_fac_radio);
    }
    
    
      private void groupButton15() {
        ButtonGroup bg15 = new ButtonGroup();
        bg15.add(yes_allowGuest_Radio);
        bg15.add(No_allowGuest_Radio);
        
        ButtonGroup bg16 = new ButtonGroup();
        bg16.add(jRadioButton11);
        bg16.add(jRadioButton12);
        
        
        ButtonGroup bg17 = new ButtonGroup();
        bg17.add(onlyMember_Radio);
        bg17.add(memSpouse_radio);
        bg17.add(Mem_Dependant_Radio);
        
    }
    
      private void groupButton16() {
        
        ButtonGroup bg15 = new ButtonGroup();
        bg15.add(no_VoucherRestrict_radio);
        bg15.add(yes_VoucherRestrict_radio);
        
        ButtonGroup bg16 = new ButtonGroup();
        bg16.add(No_voucherApproval_Radio);
        bg16.add(Yes_VoucherApproval_Radio);
        
     }
    
      
     private void groupButton17() {
        
        ButtonGroup bg17 = new ButtonGroup();
        bg17.add(crossing_no_radio);
        bg17.add(crossing_yes_radio);
        
        
        ButtonGroup bg18 = new ButtonGroup();
        bg18.add(minimum_no_radio);
        bg18.add(Minimum_yes_radio);
         
        ButtonGroup bg19 = new ButtonGroup();
        bg19.add(memStatReport_NoRadio);
        bg19.add(memStatReport_YesRadio);
        
     }  
   
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jMenu1 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        kioskincharge = new javax.swing.JComboBox();
        pcaccount = new javax.swing.JComboBox();
        nYearIncomeHead = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        pcincharge = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        claccount = new javax.swing.JComboBox();
        clevy = new javax.swing.JTextField();
        jRadioButton5 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        pYearIncomeHead = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        staxacc = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        inventoryDebtAcc = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        inventoryCrAcc = new javax.swing.JComboBox();
        labelUrl = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        peopleList_Combo = new javax.swing.JComboBox();
        save_btn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        hall_series_text = new javax.swing.JTextField();
        hall_series_no_text = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        room_series_text = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        rooml_series_no_text = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        refundl_series_text = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        refund_series_no_text = new javax.swing.JTextField();
        hall_Ok_Button = new javax.swing.JButton();
        room_Ok_Button = new javax.swing.JButton();
        refund_Ok_Button = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        yes_allowGuest_Radio = new javax.swing.JRadioButton();
        No_allowGuest_Radio = new javax.swing.JRadioButton();
        jLabel42 = new javax.swing.JLabel();
        onlyMember_Radio = new javax.swing.JRadioButton();
        memSpouse_radio = new javax.swing.JRadioButton();
        Mem_Dependant_Radio = new javax.swing.JRadioButton();
        jLabel43 = new javax.swing.JLabel();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jLabel78 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        memstatPDF_folder_text = new javax.swing.JTextField();
        save4 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        sendMail_check = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        EmailAccountList_Combo = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        gen_Email_Path_Text = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        billingMember_panel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        all_Facility_Radio = new javax.swing.JRadioButton();
        other_fac_radio = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        Facility_combo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Facility_JList = new javax.swing.JList();
        add_facility_btn = new javax.swing.JButton();
        remove_facility_btn = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        Facility_save_btn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        no_VoucherRestrict_radio = new javax.swing.JRadioButton();
        yes_VoucherRestrict_radio = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        fromdate_text = new javax.swing.JTextField();
        toDate_text = new javax.swing.JTextField();
        fromdate_buttn = new javax.swing.JButton();
        todate_buttn = new javax.swing.JButton();
        save6_button = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        No_voucherApproval_Radio = new javax.swing.JRadioButton();
        Yes_VoucherApproval_Radio = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        receipt_check = new javax.swing.JCheckBox();
        payment_check = new javax.swing.JCheckBox();
        journal_check = new javax.swing.JCheckBox();
        contra_check = new javax.swing.JCheckBox();
        jLabel49 = new javax.swing.JLabel();
        receipt_text = new javax.swing.JTextField();
        payment_text = new javax.swing.JTextField();
        journal_text = new javax.swing.JTextField();
        contra_text = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        save_6_2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        crossing_no_radio = new javax.swing.JRadioButton();
        crossing_yes_radio = new javax.swing.JRadioButton();
        jLabel53 = new javax.swing.JLabel();
        crossing_save_button = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        minimum_no_radio = new javax.swing.JRadioButton();
        Minimum_yes_radio = new javax.swing.JRadioButton();
        minimum_save_button = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        memberPhoto_destination_text = new javax.swing.JTextField();
        MemPhoto_SaveButton = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        memStatReport_NoRadio = new javax.swing.JRadioButton();
        memStatReport_YesRadio = new javax.swing.JRadioButton();
        memStatRadio_Button = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        UserList_Combo = new javax.swing.JComboBox();
        memPhotoEditUser_add = new javax.swing.JButton();
        memPhotoEditUser_RemoveBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        PhotoEditUser_JList = new javax.swing.JList();
        memPhotoEditUser_SaveBtn = new javax.swing.JButton();
        All_users_check = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        mobile_text = new javax.swing.JTextField();
        action_combo = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        savenoti_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        AndroidMobileUser_JList = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        AndroidPeople_combo = new javax.swing.JComboBox();
        AndroidPeople_AddButton = new javax.swing.JButton();
        AndroidPeople_RemoveButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        AndroidPeople_jList = new javax.swing.JList();
        AndroidPeople_Save = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        expriryDate_text = new javax.swing.JTextField();
        ExpiryDate_Button = new javax.swing.JButton();
        ExpiryDate_SaveButton = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        warehousePendingLimit_Combo = new javax.swing.JComboBox();
        AndroidPeople_AddButton1 = new javax.swing.JButton();
        AndroidPeople_RemoveButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        AndroidPeople_jList1 = new javax.swing.JList();
        SaveWarehousePending_Button = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        AssetPhotoFolderText = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jTextFieldFlag = new javax.swing.JTextField();
        jTextFieldDays = new javax.swing.JTextField();
        jTextFieldRate = new javax.swing.JTextField();
        jComboBoxIntrestWareouse = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jComboBoxIntrestAcc = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        Select = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jList1);

        jMenu1.setText("jMenu1");

        jLabel10.setText("Next Year Income Head");

        kioskincharge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pcaccount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pcaccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcaccountActionPerformed(evt);
            }
        });

        nYearIncomeHead.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Canteen Levy Account :");

        jLabel1.setText("Canteen Levy :");

        jRadioButton6.setText("No");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jLabel4.setText("Service tax account");

        jRadioButton1.setText("Yes");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        pcincharge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pcincharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcinchargeActionPerformed(evt);
            }
        });

        jLabel2.setText("%");

        jLabel5.setText("Petty Cash Account");

        jRadioButton3.setText("Yes");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Do Credit balance check while creating QT:");

        jRadioButton2.setText("No");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("Yes");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jLabel12.setText("Do Cr balance check for GE Through KIOSK ");

        pYearIncomeHead.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Member Kiosk Incharge");

        jLabel6.setText("Petty Cash Incharge");

        jLabel9.setText("Previous Year Income Head");

        jLabel8.setText("Do Credit balance check for Guests:");

        jRadioButton4.setText("No");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        staxacc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staxaccActionPerformed(evt);
            }
        });

        jLabel16.setText("Daily Overall Guest Limit");

        jRadioButton9.setText("Yes");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        jRadioButton10.setText("No");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });

        jLabel17.setText("Enter Daily Overall Limit");
        jLabel17.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jLabel17ComponentRemoved(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 48, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jRadioButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(316, 316, 316))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButton2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jRadioButton10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clevy, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(claccount, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(staxacc, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pcaccount, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pcincharge, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pYearIncomeHead, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kioskincharge, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nYearIncomeHead, javax.swing.GroupLayout.Alignment.TRAILING, 0, 286, Short.MAX_VALUE)))))
                .addContainerGap(177, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(clevy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(staxacc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pcaccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pcincharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(pYearIncomeHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(nYearIncomeHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kioskincharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton10)
                        .addComponent(jLabel17)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );

        //jTextField1.setEditable(false);

        jTabbedPane1.addTab("One", jPanel1);

        jLabel13.setText("Reports To Use Manual Inventory Entry");

        jRadioButton7.setText("Yes");
        jRadioButton7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton7ItemStateChanged(evt);
            }
        });
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        jRadioButton8.setText("No");
        jRadioButton8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton8ItemStateChanged(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Account Debt(Inventory)");

        inventoryDebtAcc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Account Credit(Income & Exp)");

        inventoryCrAcc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelUrl.setText("Member Photograph folder destination :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15))
                            .addGap(43, 43, 43)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jRadioButton7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jRadioButton8))
                                .addComponent(inventoryCrAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inventoryDebtAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelUrl))
                .addContainerGap(282, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inventoryCrAcc, jTextField2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(inventoryDebtAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(inventoryCrAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUrl)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jButton2)
                .addContainerGap(508, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Two", jPanel2);

        jLabel18.setText("Permission to give discount on bills");

        peopleList_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        save_btn.setText("Save");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setText("Guest Room & Hall Settings ");

        jLabel20.setText("Hall Booking Unique Id Series :  ");

        jLabel21.setText("Series : ");

        jLabel22.setText("Series No :");

        jLabel23.setText("Guest Room  Booking Unique Id Series :  ");

        jLabel24.setText("Series : ");

        jLabel25.setText("Series No :");

        jLabel26.setText("Refund Voucher Series No : ");

        jLabel27.setText("Series : ");

        jLabel28.setText("Series No :");

        hall_Ok_Button.setText("Ok");
        hall_Ok_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_Ok_ButtonActionPerformed(evt);
            }
        });

        room_Ok_Button.setText("Ok");
        room_Ok_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                room_Ok_ButtonActionPerformed(evt);
            }
        });

        refund_Ok_Button.setText("Ok");
        refund_Ok_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refund_Ok_ButtonActionPerformed(evt);
            }
        });

        jLabel41.setText("Allow Members with Card only (Guest Room ) : ");

        yes_allowGuest_Radio.setText("Yes");

        No_allowGuest_Radio.setText("No");

        jLabel42.setText("Card Access Limited to :   ");

        onlyMember_Radio.setText("Only Members");

        memSpouse_radio.setText("Member+Spouse");

        Mem_Dependant_Radio.setText("Member+Spouse+Dependants");

        jLabel43.setText("Allow Members With Card only  (Party Hall) : ");

        jRadioButton11.setText("Yes");

        jRadioButton12.setText("No");

        jLabel78.setText("Statutory Details");

        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(peopleList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hall_series_text, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(room_series_text, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refundl_series_text, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel25))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(hall_series_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rooml_series_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund_series_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(refund_Ok_Button)
                                    .addComponent(room_Ok_Button)
                                    .addComponent(hall_Ok_Button)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel43))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jRadioButton11)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton12))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(yes_allowGuest_Radio)
                                .addGap(18, 18, 18)
                                .addComponent(No_allowGuest_Radio))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(onlyMember_Radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(memSpouse_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Mem_Dependant_Radio))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9)))))
                .addContainerGap(377, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(peopleList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(hall_series_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hall_series_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(hall_Ok_Button))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(room_series_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(rooml_series_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_Ok_Button))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(refundl_series_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(refund_series_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refund_Ok_Button))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(yes_allowGuest_Radio)
                    .addComponent(No_allowGuest_Radio))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jRadioButton11)
                    .addComponent(jRadioButton12))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(onlyMember_Radio)
                    .addComponent(memSpouse_radio)
                    .addComponent(Mem_Dependant_Radio))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addComponent(save_btn)
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("Three", jPanel3);

        jLabel29.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(81, 72, 201));
        jLabel29.setText("Email and SMS Settings ");

        jLabel30.setText("2)   Path to save memberStatements in PDF format to send mail :  ");

        save4.setText("Save");
        save4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save4ActionPerformed(evt);
            }
        });

        jLabel31.setText("1)  Send Email for member Statements  : ");

        sendMail_check.setText("Yes");

        jLabel32.setText("3)  Select Email Account to send Member statement : ");

        EmailAccountList_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jLabel33.setText("4)  Path to save general documents sent by E-mail  : ");

        jLabel34.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(252, 16, 16));
        jLabel34.setText("Note : For (4) please enter path excluding drive name. For ex : \"D:\" or \"F:\" . ");

        jLabel35.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(252, 16, 16));
        jLabel35.setText("This can be varible from host to host. Ex \"Y:\" or \"Z:\" or \"X:\" .");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memstatPDF_folder_text, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(save4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(312, 312, 312)))))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sendMail_check))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addGap(18, 18, 18)
                                    .addComponent(EmailAccountList_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gen_Email_Path_Text)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(sendMail_check))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(memstatPDF_folder_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(EmailAccountList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(gen_Email_Path_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(save4)
                .addGap(110, 110, 110))
        );

        jTabbedPane1.addTab("Four", jPanel4);

        jLabel36.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(123, 5, 5));
        jLabel36.setText("Facility Restriction for Billing Member ");

        jLabel37.setText("Facility : ");

        all_Facility_Radio.setText("All ");
        all_Facility_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                all_Facility_RadioItemStateChanged(evt);
            }
        });

        other_fac_radio.setText("Other");

        jLabel38.setText("Select Facility : ");

        Facility_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        Facility_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Facility_JList);

        add_facility_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_facility_btnActionPerformed(evt);
            }
        });

        remove_facility_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_facility_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(remove_facility_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Facility_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add_facility_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_facility_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel38)
                                .addComponent(Facility_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remove_facility_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add_facility_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png"))); // NOI18N
        remove_facility_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png"))); // NOI18N

        jLabel39.setForeground(new java.awt.Color(247, 53, 53));
        jLabel39.setText("Note : -  Credit balance amount unadjusted ( if any ) will not be considered.");

        jLabel40.setForeground(new java.awt.Color(13, 25, 245));
        jLabel40.setText("This menu is used to check member  balance according to  Facility wise while creating QT/Bill . ");

        Facility_save_btn.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Facility_save_btn.setForeground(new java.awt.Color(110, 6, 6));
        Facility_save_btn.setText("Save");
        Facility_save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Facility_save_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout billingMember_panelLayout = new javax.swing.GroupLayout(billingMember_panel);
        billingMember_panel.setLayout(billingMember_panelLayout);
        billingMember_panelLayout.setHorizontalGroup(
            billingMember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billingMember_panelLayout.createSequentialGroup()
                .addGroup(billingMember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(billingMember_panelLayout.createSequentialGroup()
                        .addGroup(billingMember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(billingMember_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel37)
                                .addGap(26, 26, 26)
                                .addComponent(all_Facility_Radio)
                                .addGap(18, 18, 18)
                                .addComponent(other_fac_radio))
                            .addGroup(billingMember_panelLayout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(billingMember_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(billingMember_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, billingMember_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Facility_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        billingMember_panelLayout.setVerticalGroup(
            billingMember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billingMember_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(42, 42, 42)
                .addGroup(billingMember_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(all_Facility_Radio)
                    .addComponent(other_fac_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Facility_save_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel40)
                .addGap(7, 7, 7)
                .addComponent(jLabel39)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Five", billingMember_panel);

        jLabel44.setFont(new java.awt.Font("Ubuntu", 3, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(89, 6, 6));
        jLabel44.setText("Voucher Entry Restriction");

        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(138, 11, 11), 2, true));

        jLabel45.setForeground(new java.awt.Color(12, 13, 139));
        jLabel45.setText("1.    Do you want to restrict voucher entries for specific period ? ");

        no_VoucherRestrict_radio.setText("No");
        no_VoucherRestrict_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                no_VoucherRestrict_radioItemStateChanged(evt);
            }
        });

        yes_VoucherRestrict_radio.setText("yes");
        yes_VoucherRestrict_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yes_VoucherRestrict_radioItemStateChanged(evt);
            }
        });

        jLabel46.setText("From : ");

        jLabel47.setText("To : ");

        fromdate_buttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        fromdate_buttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromdate_buttnActionPerformed(evt);
            }
        });

        todate_buttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        todate_buttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todate_buttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(37, 37, 37)
                        .addComponent(toDate_text, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18)
                        .addComponent(fromdate_text)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromdate_buttn)
                    .addComponent(todate_buttn))
                .addGap(259, 259, 259))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(fromdate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromdate_buttn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(toDate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(todate_buttn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fromdate_text.setEditable(false);
        toDate_text.setEditable(false);

        save6_button.setText("Save");
        save6_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save6_buttonActionPerformed(evt);
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
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addComponent(no_VoucherRestrict_radio)
                        .addGap(18, 18, 18)
                        .addComponent(yes_VoucherRestrict_radio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(save6_button, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(save6_button)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(no_VoucherRestrict_radio)
                            .addComponent(yes_VoucherRestrict_radio))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(98, 1, 1), 2, true));
        jPanel10.setForeground(new java.awt.Color(137, 12, 12));

        jLabel48.setForeground(new java.awt.Color(30, 68, 145));
        jLabel48.setText("2.   Do you want approval for all voucher entries ?  ");

        No_voucherApproval_Radio.setText("No");
        No_voucherApproval_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                No_voucherApproval_RadioItemStateChanged(evt);
            }
        });

        Yes_VoucherApproval_Radio.setText("Yes");
        Yes_VoucherApproval_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Yes_VoucherApproval_RadioItemStateChanged(evt);
            }
        });

        receipt_check.setText("Receipt Voucher");
        receipt_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                receipt_checkItemStateChanged(evt);
            }
        });
        receipt_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receipt_checkActionPerformed(evt);
            }
        });

        payment_check.setText("Payment Voucher ");
        payment_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                payment_checkItemStateChanged(evt);
            }
        });

        journal_check.setText("Journal Voucher ");
        journal_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                journal_checkItemStateChanged(evt);
            }
        });

        contra_check.setText("Contra Voucher ");
        contra_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                contra_checkItemStateChanged(evt);
            }
        });

        jLabel49.setText("Max. No of Entries Allowed before Approval ");

        jLabel50.setForeground(new java.awt.Color(146, 7, 7));
        jLabel50.setText("* Note :  Leaving No. field blank will treat as not selected for approval. ");

        jLabel51.setForeground(new java.awt.Color(135, 10, 10));
        jLabel51.setText("Minimum no should be atleast 1. ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel49))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(receipt_check)
                                    .addGap(79, 79, 79)
                                    .addComponent(receipt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(payment_check)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(payment_text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(journal_check)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(journal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(contra_check)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(contra_text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel50))
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(4, 4, 4)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(receipt_check)
                    .addComponent(receipt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payment_check)
                    .addComponent(payment_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(journal_check)
                    .addComponent(journal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contra_check)
                    .addComponent(contra_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addContainerGap())
        );

        save_6_2.setText("Save");
        save_6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_6_2ActionPerformed(evt);
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
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(save_6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18)
                        .addComponent(No_voucherApproval_Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Yes_VoucherApproval_Radio)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(save_6_2)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(No_voucherApproval_Radio)
                            .addComponent(Yes_VoucherApproval_Radio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(95, 95, 95))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addGap(30, 30, 30)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Six", jPanel6);

        jLabel52.setText("Allow horizontal transfer(crossing) of products between warehouses level ?  ");

        crossing_no_radio.setText("No");

        crossing_yes_radio.setText("Yes");

        jLabel53.setForeground(new java.awt.Color(227, 3, 3));
        jLabel53.setText("Note :  If selected no,  only parent and child level warehouse will be visible.");

        crossing_save_button.setText("Save");
        crossing_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossing_save_buttonActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Minimum Usage Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 18), new java.awt.Color(146, 23, 23))); // NOI18N
        jPanel12.setForeground(new java.awt.Color(145, 12, 12));

        jLabel54.setText("Do you want to allow guest billing to be include in Minimum usage billing ? ");

        minimum_no_radio.setText("No");

        Minimum_yes_radio.setText("Yes");
        Minimum_yes_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Minimum_yes_radioActionPerformed(evt);
            }
        });

        minimum_save_button.setText("Save");
        minimum_save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimum_save_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minimum_no_radio)
                .addGap(18, 18, 18)
                .addComponent(Minimum_yes_radio)
                .addContainerGap(158, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimum_save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(minimum_no_radio)
                    .addComponent(Minimum_yes_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(minimum_save_button)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Member Photo & signature Destination Folder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 1, 18), new java.awt.Color(38, 20, 189))); // NOI18N
        jPanel13.setForeground(new java.awt.Color(91, 8, 8));

        jLabel55.setText("Enter Destination Path for saving member photo : ");

        MemPhoto_SaveButton.setText("Save");
        MemPhoto_SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemPhoto_SaveButtonActionPerformed(evt);
            }
        });

        jLabel56.setForeground(new java.awt.Color(227, 3, 3));
        jLabel56.setText("Note : Destination path is in Server==> GarudaclubPlus ==> MemberPhotos.");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MemPhoto_SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(memberPhoto_destination_text, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(memberPhoto_destination_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MemPhoto_SaveButton)
                    .addComponent(jLabel56))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Member Statement Report ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 18), new java.awt.Color(146, 23, 23))); // NOI18N
        jPanel14.setForeground(new java.awt.Color(145, 12, 12));

        jLabel57.setText("Allow Member Statement Report with Note Only in Footer");

        memStatReport_NoRadio.setText("No");

        memStatReport_YesRadio.setText("Yes");
        memStatReport_YesRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memStatReport_YesRadioActionPerformed(evt);
            }
        });

        memStatRadio_Button.setText("Save");
        memStatRadio_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memStatRadio_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(memStatReport_NoRadio)
                .addGap(18, 18, 18)
                .addComponent(memStatReport_YesRadio)
                .addContainerGap(252, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(memStatRadio_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(memStatReport_NoRadio)
                    .addComponent(memStatReport_YesRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(memStatRadio_Button)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(crossing_save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel52)
                        .addGap(18, 18, 18)
                        .addComponent(crossing_no_radio)
                        .addGap(18, 18, 18)
                        .addComponent(crossing_yes_radio))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel53))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(crossing_no_radio)
                    .addComponent(crossing_yes_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel53)
                .addGap(29, 29, 29)
                .addComponent(crossing_save_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Seven", jPanel11);

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel58.setForeground(new java.awt.Color(224, 7, 7));
        jLabel58.setText("Select User to give permission to save and edit Member Photo & signature in \"Member photo panel \"");

        jLabel59.setText("Role : ");

        UserList_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        UserList_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserList_ComboActionPerformed(evt);
            }
        });

        memPhotoEditUser_add.setText("Add");
        memPhotoEditUser_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memPhotoEditUser_addActionPerformed(evt);
            }
        });

        memPhotoEditUser_RemoveBtn.setText("Remove");
        memPhotoEditUser_RemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memPhotoEditUser_RemoveBtnActionPerformed(evt);
            }
        });

        PhotoEditUser_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(PhotoEditUser_JList);

        memPhotoEditUser_SaveBtn.setText("Save");
        memPhotoEditUser_SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memPhotoEditUser_SaveBtnActionPerformed(evt);
            }
        });

        All_users_check.setText("All Users");
        All_users_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                All_users_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(memPhotoEditUser_RemoveBtn))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(All_users_check)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(UserList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                                        .addComponent(memPhotoEditUser_add)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(memPhotoEditUser_SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(memPhotoEditUser_SaveBtn))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel59)
                                    .addComponent(UserList_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(memPhotoEditUser_add))
                                .addGap(18, 18, 18)
                                .addComponent(All_users_check)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memPhotoEditUser_RemoveBtn))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setForeground(new java.awt.Color(205, 13, 13));
        jLabel60.setText("Android notification :- mobile numbers for user");

        jLabel61.setText("Enter Mobile No :");

        action_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Close Sale" , "Close Cash" }));
        action_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                action_comboItemStateChanged(evt);
            }
        });

        jLabel62.setText("Action Performed :");

        savenoti_button.setText("Save");
        savenoti_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savenoti_buttonActionPerformed(evt);
            }
        });

        AndroidMobileUser_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(AndroidMobileUser_JList);

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(jLabel61))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(mobile_text, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(action_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(savenoti_button, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(savenoti_button))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(action_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mobile_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61)
                                    .addComponent(jButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eight", jPanel15);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Android App (Garuda Purchase Settings)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(199, 7, 7))); // NOI18N
        jPanel19.setForeground(new java.awt.Color(57, 22, 22));

        jLabel63.setText("Select users to be given permission for purchase of items through android app. ");

        AndroidPeople_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        AndroidPeople_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AndroidPeople_comboActionPerformed(evt);
            }
        });

        AndroidPeople_AddButton.setText("Add");
        AndroidPeople_AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AndroidPeople_AddButtonActionPerformed(evt);
            }
        });

        AndroidPeople_RemoveButton.setText("Remove");
        AndroidPeople_RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AndroidPeople_RemoveButtonActionPerformed(evt);
            }
        });

        AndroidPeople_jList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(AndroidPeople_jList);

        AndroidPeople_Save.setText("Save");
        AndroidPeople_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AndroidPeople_SaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(AndroidPeople_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AndroidPeople_RemoveButton)
                            .addComponent(AndroidPeople_AddButton))
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(AndroidPeople_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AndroidPeople_Save))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AndroidPeople_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AndroidPeople_AddButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AndroidPeople_RemoveButton))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Expiry Date", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), java.awt.Color.red)); // NOI18N

        jLabel64.setText("Select Date :  ");

        expriryDate_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expriryDate_textActionPerformed(evt);
            }
        });
        expriryDate_text.setEditable(false);

        ExpiryDate_Button.setText("Date");
        ExpiryDate_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpiryDate_ButtonActionPerformed(evt);
            }
        });

        ExpiryDate_SaveButton.setText("Save");
        ExpiryDate_SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpiryDate_SaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expriryDate_text, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ExpiryDate_Button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExpiryDate_SaveButton)
                .addGap(272, 272, 272))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(expriryDate_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExpiryDate_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(ExpiryDate_SaveButton)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nine", jPanel18);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pending Bill Limit Master", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(199, 7, 7))); // NOI18N
        jPanel22.setForeground(new java.awt.Color(57, 22, 22));

        jLabel65.setText("Select Warehouse to limit pending bills");

        warehousePendingLimit_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        AndroidPeople_AddButton1.setText("Add");
        AndroidPeople_AddButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AndroidPeople_AddButton1ActionPerformed(evt);
            }
        });

        AndroidPeople_RemoveButton1.setText("Remove");
        AndroidPeople_RemoveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AndroidPeople_RemoveButton1ActionPerformed(evt);
            }
        });

        AndroidPeople_jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(AndroidPeople_jList1);

        SaveWarehousePending_Button.setText("Save");
        SaveWarehousePending_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveWarehousePending_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel65))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(warehousePendingLimit_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(AndroidPeople_AddButton1)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(AndroidPeople_RemoveButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SaveWarehousePending_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(warehousePendingLimit_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AndroidPeople_AddButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AndroidPeople_RemoveButton1)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SaveWarehousePending_Button))
                        .addGap(25, 25, 25))))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Asset Photos Destination Folder", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(199, 7, 7))); // NOI18N

        jLabel66.setText("Note : Destination path is in Server for photos==> GarudaclubPlus ==> AssetPhotos");

        jLabel67.setText("Enter Destination Path for saving asset photo : ");

        jButton5.setText("save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel71.setText("  Destination path is in Server for documents==>  GarudaclubPlus ==> Asset Documents");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(335, 335, 335))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(AssetPhotoFolderText, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(AssetPhotoFolderText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Guset Room Check Out Strict", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(199, 7, 7))); // NOI18N

        jLabel68.setText("To give permission to perform the check Out of the date previous to the current date set field to 1 :");

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel69.setText("Note: After completion of check out Dont forget to reset this field to 0.");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel69)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ten", jPanel21);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pending Bill Inrest Master", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel70.setText("Applicable flag");

        jLabel72.setText("Days");

        jLabel73.setText("Rate per anum");

        jLabel74.setText("Account");

        jLabel75.setText("Warehouses");

        jComboBoxIntrestWareouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIntrestWareouseActionPerformed(evt);
            }
        });

        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBoxIntrestAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIntrestAccActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton8.setText("Remove");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel76.setText("Added Warehouse");

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel77.setText("List of Selected Warehouses(s).");

        jScrollPane7.setViewportView(jList2);

        Select.setText("Select");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldDays, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldFlag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxIntrestWareouse, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxIntrestAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Select, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING))))))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jLabel77)
                        .addGap(254, 254, 254))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(415, 415, 415)
                .addComponent(jButton7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jTextFieldFlag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(jTextFieldDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(jComboBoxIntrestAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Select))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(jComboBoxIntrestWareouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Add))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)
                            .addComponent(jLabel76)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(jButton7)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eleven", jPanel25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AndroidPeople_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndroidPeople_SaveActionPerformed

        try{

            new PreparedSentence(m_App.getSession()
                , "delete from  peoplepurchaseapp "
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec();

            for(int i=0;i<SelectedPeopleForPurchaseAppList.size();i++){
                String proplename = SelectedPeopleForPurchaseAppList.get(i).toString();
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT id from people where name =? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(proplename);
                if(obj!=null){
                    String peopleid = obj[0].toString();

                    new PreparedSentence(m_App.getSession()
                        , "INSERT INTO peoplepurchaseapp(ID,people) VALUES(?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), peopleid});

                }

            }

            JOptionPane.showMessageDialog(this, "Saved successfully..! ");

        }
        catch(BasicException e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_AndroidPeople_SaveActionPerformed

    private void AndroidPeople_RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndroidPeople_RemoveButtonActionPerformed
        int row = AndroidPeople_jList.getSelectedIndex();
        if (row >= 0) {

            String lst=AndroidPeople_jList.getSelectedValue().toString();
            SelectedPeopleForPurchaseAppList.remove(lst);
            AndroidPeople_jList.setModel(new ItemsListModel(SelectedPeopleForPurchaseAppList));

        }

    }//GEN-LAST:event_AndroidPeople_RemoveButtonActionPerformed

    private void AndroidPeople_AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndroidPeople_AddButtonActionPerformed
        if(AndroidPeople_combo.getSelectedIndex()!=-1){
            String people= AndroidPeople_combo.getSelectedItem().toString();
            int repeatflag=0;
            for(int i=0;i<SelectedPeopleForPurchaseAppList.size();i++){
                String x = SelectedPeopleForPurchaseAppList.get(i).toString();
                if(x.equals(people)){
                    repeatflag = 1;
                    break;
                }
            }

            if(repeatflag==1){
                JOptionPane.showMessageDialog(this, "Already Exsist . Please select another name.");
            }
            else{
                SelectedPeopleForPurchaseAppList.add(people);
                AndroidPeople_jList.setModel(new ItemsListModel( SelectedPeopleForPurchaseAppList));
            }
        }
    }//GEN-LAST:event_AndroidPeople_AddButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {

            if(action_combo.getSelectedIndex()==0){
                int row = AndroidMobileUser_JList.getSelectedIndex();
                if (row >= 0) {

                    String lst=AndroidMobileUser_JList.getSelectedValue().toString();
                    CloseSaleAndroidMobList.remove(lst);
                    AndroidMobileUser_JList.setModel(new ItemsListModel(CloseSaleAndroidMobList));

                } else {
                    JOptionPane.showMessageDialog(this, "Select any mobile no. to remove");

                }
            }
            else{
                int row = AndroidMobileUser_JList.getSelectedIndex();
                if (row >= 0) {

                    String lst=AndroidMobileUser_JList.getSelectedValue().toString();
                    CloseCashAndroidMobList.remove(lst);
                    AndroidMobileUser_JList.setModel(new ItemsListModel(CloseCashAndroidMobList));

                } else {
                    JOptionPane.showMessageDialog(this, "Select any mobile no. to remove");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(action_combo.getSelectedIndex()!=-1){
            if(action_combo.getSelectedIndex()==0){
                if(mobile_text.getText()!=null  && mobile_text.getText().trim().length()>0){
                    String Mobno=mobile_text.getText();

                    int flag=0;
                    for(int i=0;i<CloseSaleAndroidMobList.size();i++){
                        String x = CloseSaleAndroidMobList.get(i).toString();
                        if(x.equals(Mobno)){
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        CloseSaleAndroidMobList.add(Mobno);
                        AndroidMobileUser_JList.setModel(new ItemsListModel( CloseSaleAndroidMobList));
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Mobile No already exsist", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Please enter mobile number.");
                }
            }
            else{
                if(mobile_text.getText()!=null  && mobile_text.getText().trim().length()>0){
                    String Mobno=mobile_text.getText();

                    int flag=0;
                    for(int i=0;i<CloseCashAndroidMobList.size();i++){
                        String x = CloseCashAndroidMobList.get(i).toString();
                        if(x.equals(Mobno)){
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        CloseCashAndroidMobList.add(Mobno);
                        AndroidMobileUser_JList.setModel(new ItemsListModel( CloseCashAndroidMobList));
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Mobile No already exsist", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Please enter mobile number.");
                }

            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please select action to perform.");
        }

        mobile_text.setText(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void savenoti_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savenoti_buttonActionPerformed
        String mobFullstrCloseSale = "";
        String mobFullstrCloseCash = "";
        if(CloseSaleAndroidMobList.size()>0){

            for(int i=0;i<CloseSaleAndroidMobList.size();i++){
                String mob = CloseSaleAndroidMobList.get(i).toString();
                mobFullstrCloseSale = mobFullstrCloseSale+mob+"#";
            }
        }
        if(CloseCashAndroidMobList.size()>0){

            for(int i=0;i<CloseCashAndroidMobList.size();i++){
                String mob = CloseCashAndroidMobList.get(i).toString();
                mobFullstrCloseCash = mobFullstrCloseCash+mob+"#";
            }
        }

        try{

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE contact SET mobileno=? WHERE description=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{mobFullstrCloseSale,"close_sale"})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO contact(ID,mobileno,description) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),mobFullstrCloseCash, "close_sale"});

        }

        if( new PreparedSentence(m_App.getSession()
            , "UPDATE contact SET mobileno=? WHERE description=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{mobFullstrCloseSale,"close_cash"})<=0){

        new PreparedSentence(m_App.getSession()
            , "INSERT INTO contact(ID,mobileno,description) VALUES(?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),mobFullstrCloseCash, "close_cash"});

        }

        }
        catch(BasicException e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_savenoti_buttonActionPerformed

    private void action_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_action_comboItemStateChanged
        if(action_combo.getSelectedIndex()==0){
            CloseSaleAndroidMobList=new ArrayList<String>();
            try{
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT mobileno from contact where description ='close_sale'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj1!=null){
                    if(obj1[0]!=null){
                        String moball = obj1[0].toString();
                        String []mobarr = moball.split("#");
                        for(int i=0;i<mobarr.length;i++){
                            CloseSaleAndroidMobList.add(mobarr[i].toString());
                        }
                    }

                }

            }

            catch(BasicException e){
                e.printStackTrace();
            }
            AndroidMobileUser_JList.setModel(new ItemsListModel(CloseSaleAndroidMobList));

        }
        if(action_combo.getSelectedIndex()==1){
            CloseCashAndroidMobList=new ArrayList<String>();
            try{
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT mobileno from contact where description ='close_cash'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                if(obj1!=null){
                    if(obj1[0]!=null){
                        String moball = obj1[0].toString();
                        String []mobarr = moball.split("#");
                        for(int i=0;i<mobarr.length;i++){
                            CloseCashAndroidMobList.add(mobarr[i].toString());
                        }
                    }

                }

            }
            catch(BasicException e){
                e.printStackTrace();
            }
            AndroidMobileUser_JList.setModel(new ItemsListModel(CloseCashAndroidMobList));

        }
    }//GEN-LAST:event_action_comboItemStateChanged

    private void All_users_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_All_users_checkItemStateChanged
        if(All_users_check.isSelected()){
            MemPhotoEditList = new ArrayList<String>();
            MemPhotoEditList.add("ALL");
            PhotoEditUser_JList.setModel(new ItemsListModel(MemPhotoEditList));
            memPhotoEditUser_add.setEnabled(false);
            memPhotoEditUser_RemoveBtn.setEnabled(false);
        }
        else{
            MemPhotoEditList = new ArrayList<String>();
            PhotoEditUser_JList.setModel(new ItemsListModel(MemPhotoEditList));
            memPhotoEditUser_add.setEnabled(true);
            memPhotoEditUser_RemoveBtn.setEnabled(true);

        }
    }//GEN-LAST:event_All_users_checkItemStateChanged

    private void memPhotoEditUser_SaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memPhotoEditUser_SaveBtnActionPerformed

        String Member_Str = "";
        String Name = "User Permission list to edit member photo";
        if(MemPhotoEditList.size()>0){
            for(int i=0;i<MemPhotoEditList.size();i++){
                String x = MemPhotoEditList.get(i).toString();
                Member_Str=Member_Str+x+"#";
            }
            try{
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Member_Str,Name})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name, Member_Str});

            }

            JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(BasicException e){
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        }
        else{
            JOptionPane.showMessageDialog(this, "Select Atleast One user");
        }
    }//GEN-LAST:event_memPhotoEditUser_SaveBtnActionPerformed

    private void memPhotoEditUser_RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memPhotoEditUser_RemoveBtnActionPerformed
        try {
            int row = PhotoEditUser_JList.getSelectedIndex();
            if (row >= 0) {

                String lst=PhotoEditUser_JList.getSelectedValue().toString();
                MemPhotoEditList.remove(lst);
                PhotoEditUser_JList.setModel(new ItemsListModel(MemPhotoEditList));

            } else {
                JOptionPane.showMessageDialog(this, "Select any User");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_memPhotoEditUser_RemoveBtnActionPerformed

    private void memPhotoEditUser_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memPhotoEditUser_addActionPerformed
        if(UserList_Combo.getSelectedIndex()!=-1){
            String User = UserList_Combo.getSelectedItem().toString();
            int flag=0;
            for(int i=0;i<MemPhotoEditList.size();i++){
                String x = MemPhotoEditList.get(i).toString();
                if(x.equals(User)){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                MemPhotoEditList.add(User);
                PhotoEditUser_JList.setModel(new ItemsListModel( MemPhotoEditList));
            }
            else{
                JOptionPane.showMessageDialog(this, "User already exsist", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "Select any User . ", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_memPhotoEditUser_addActionPerformed

    private void memStatRadio_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memStatRadio_ButtonActionPerformed
        Boolean minimum_flag=false;
        if(memStatReport_YesRadio.isSelected()){
            minimum_flag=true;
        }

        String Name = "MemberStatement Report with only Note";
        try{

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING})).exec(new Object[]{minimum_flag,Name})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN})).exec(new Object[]{UUID.randomUUID().toString(),Name, minimum_flag});

        }

        JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(BasicException e){
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_memStatRadio_ButtonActionPerformed

    private void memStatReport_YesRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memStatReport_YesRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memStatReport_YesRadioActionPerformed

    private void MemPhoto_SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemPhoto_SaveButtonActionPerformed

        if(memberPhoto_destination_text.getText()!=null && memberPhoto_destination_text.getText().trim().length()>0){
            String Value=memberPhoto_destination_text.getText().trim();

            // String Name = "Member Photo Path";
            try{
                InetAddress IP=InetAddress.getLocalHost();
                String IPAddress = IP.getHostAddress();
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{Value,IPAddress})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),IPAddress, Value});

            }

            JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(BasicException e){
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        catch(UnknownHostException e){
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Enter Destination Path ", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_MemPhoto_SaveButtonActionPerformed

    private void minimum_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimum_save_buttonActionPerformed
        Boolean minimum_flag=false;
        if(Minimum_yes_radio.isSelected()){
            minimum_flag=true;
        }

        String Name = "Minimum Usage Guest Billing";
        try{

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING})).exec(new Object[]{minimum_flag,Name})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN})).exec(new Object[]{UUID.randomUUID().toString(),Name, minimum_flag});

        }

        JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(BasicException e){
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_minimum_save_buttonActionPerformed

    private void Minimum_yes_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Minimum_yes_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Minimum_yes_radioActionPerformed

    private void crossing_save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossing_save_buttonActionPerformed
        Boolean CrossingFlag;
        if(crossing_no_radio.isSelected()){
            CrossingFlag=false;
        }
        else{
            CrossingFlag=true;
        }

        String cross_str="Crossing of products horizontally";

        try{

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING})).exec(new Object[]{CrossingFlag,cross_str})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN})).exec(new Object[]{UUID.randomUUID().toString(),cross_str, CrossingFlag});

        }

        JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(BasicException e){
            JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_crossing_save_buttonActionPerformed

    private void save_6_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_6_2ActionPerformed
        String Name3 = "Voucher Entry Approval Flag";

        try{

            if(Yes_VoucherApproval_Radio.isSelected()){
                if(receipt_text.getText().trim().length()>0 || payment_text.getText().trim().length()>0 || journal_text.getText().trim().length()>0 || contra_text.getText().trim().length()>0){

                    if( new PreparedSentence(m_App.getSession()
                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{1,Name3})<=0){

                    new PreparedSentence(m_App.getSession()
                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name3, 1});

                }

                // 1.

                if(receipt_text.getText().trim().length()>0) {
                    Receipt_entries=Integer.parseInt(receipt_text.getText().trim());
                    String Name1="Max No. of Receipt Voucher Entries";

                    if( new PreparedSentence(m_App.getSession()
                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Receipt_entries,Name1})<=0){

                    new PreparedSentence(m_App.getSession()
                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name1, Receipt_entries});

                }

            }
            else{
                String Name1="Max No. of Receipt Voucher Entries";
                new PreparedSentence(m_App.getSession()
                    , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name1});
            }

            // 2.
            if(payment_text.getText().trim().length()>0) {
                payment_entries=Integer.parseInt(payment_text.getText().trim());
                String Name1="Max No. of Payment Voucher Entries";

                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{payment_entries,Name1})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name1, payment_entries});

            }

        }
        else{
            String Name1="Max No. of Payment Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name1});
        }

        // 3.

        if(journal_text.getText().trim().length()>0) {
            Journal_entries=Integer.parseInt(journal_text.getText().trim());
            String Name1="Max No. of Journal Voucher Entries";

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Journal_entries,Name1})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name1, Journal_entries});

        }

        }
        else{
            String Name1="Max No. of Journal Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name1});
        }
        // 4.

        if(contra_text.getText().trim().length()>0) {
            Contra_entries=Integer.parseInt(contra_text.getText().trim());
            String Name1="Max No. of Contra Voucher Entries";

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{Contra_entries,Name1})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name1, Contra_entries});

        }

        }
        else{
            String Name1="Max No. of Contra Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name1});
        }

        JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            JOptionPane.showMessageDialog(this, "Please select any of the vouchers. \n Else select 'No' for approval.","incomplte form", JOptionPane.WARNING_MESSAGE);
        }

        }

        if(No_voucherApproval_Radio.isSelected()){

            String Name1="Max No. of Receipt Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name1});

            String Name2="Max No. of Payment Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name2});

            String Name5="Max No. of Journal Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name5});

            String Name4="Max No. of Contra Voucher Entries";
            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name4});

            new PreparedSentence(m_App.getSession()
                , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name3});

            JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        }
        catch(Exception e){

        }

    }//GEN-LAST:event_save_6_2ActionPerformed

    private void contra_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_contra_checkItemStateChanged
        if(contra_check.isSelected()){
            contra_text.setVisible(true);
        }
        else{
            contra_text.setVisible(false);
            contra_text.setText(null);
        }
    }//GEN-LAST:event_contra_checkItemStateChanged

    private void journal_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_journal_checkItemStateChanged
        if(journal_check.isSelected()){
            journal_text.setVisible(true);
        }
        else{
            journal_text.setVisible(false);
            journal_text.setText(null);
        }
    }//GEN-LAST:event_journal_checkItemStateChanged

    private void payment_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_payment_checkItemStateChanged
        if(payment_check.isSelected()){
            payment_text.setVisible(true);
        }
        else{
            payment_text.setVisible(false);
            payment_text.setText(null);
        }
    }//GEN-LAST:event_payment_checkItemStateChanged

    private void receipt_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receipt_checkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receipt_checkActionPerformed

    private void receipt_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_receipt_checkItemStateChanged
        if(receipt_check.isSelected()){
            receipt_text.setVisible(true);
        }
        else{
            receipt_text.setVisible(false);
            receipt_text.setText(null);
        }
    }//GEN-LAST:event_receipt_checkItemStateChanged

    private void Yes_VoucherApproval_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Yes_VoucherApproval_RadioItemStateChanged
        if(Yes_VoucherApproval_Radio.isSelected()){

            jPanel8.setVisible(true);
            if(receipt_check.isSelected()){
                receipt_text.setVisible(true);
            }
            else{
                receipt_text.setVisible(false);
            }
            if(payment_check.isSelected()){
                payment_text.setVisible(true);
            }
            else{
                payment_text.setVisible(false);
            }
            if(journal_check.isSelected()){
                journal_text.setVisible(true);
            }
            else{
                journal_text.setVisible(false);
            }
            if(contra_check.isSelected()){
                contra_text.setVisible(true);
            }
            else{
                contra_text.setVisible(false);
            }
        }
        else{
            jPanel8.setVisible(false);
        }
    }//GEN-LAST:event_Yes_VoucherApproval_RadioItemStateChanged

    private void No_voucherApproval_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_No_voucherApproval_RadioItemStateChanged
        if(No_voucherApproval_Radio.isSelected()){
            jPanel8.setVisible(false);
        }
        else{
            jPanel8.setVisible(true);
        }
    }//GEN-LAST:event_No_voucherApproval_RadioItemStateChanged

    private void save6_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save6_buttonActionPerformed

        String Name1 = "Voucher Entry Restricted From";
        String Name2 = "Voucher Entry Restricted Upto";

        if(yes_VoucherRestrict_radio.isSelected()){
            if(fromdate_text.getText()!=null && fromdate_text.getText().trim().length()>0){
                if(toDate_text.getText()!=null && toDate_text.getText().trim().length()>0){

                    try{
                        From_VoucherEntry_Dt = (fromdate_text.getText());
                        To_VoucherEntry_Dt = (toDate_text.getText());

                        if( new PreparedSentence(m_App.getSession()
                            , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{From_VoucherEntry_Dt,Name1})<=0){

                        new PreparedSentence(m_App.getSession()
                            , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name1, From_VoucherEntry_Dt});

                    }

                    if( new PreparedSentence(m_App.getSession()
                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{To_VoucherEntry_Dt,Name2})<=0){

                    new PreparedSentence(m_App.getSession()
                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name2, To_VoucherEntry_Dt});

                }

                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "Select To date ","incomplte form", JOptionPane.WARNING_MESSAGE);
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Select From Date", "incomplte form", JOptionPane.WARNING_MESSAGE);
        }
        }

        if(no_VoucherRestrict_radio.isSelected()){

            try{

                new PreparedSentence(m_App.getSession()
                    , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name1});

                new PreparedSentence(m_App.getSession()
                    , "DELETE FROM  GENERALTABLE WHERE   NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Name2});

                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_save6_buttonActionPerformed

    private void todate_buttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todate_buttnActionPerformed
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(toDate_text.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            toDate_text.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_todate_buttnActionPerformed

    private void fromdate_buttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromdate_buttnActionPerformed
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(fromdate_text.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            fromdate_text.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_fromdate_buttnActionPerformed

    private void yes_VoucherRestrict_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yes_VoucherRestrict_radioItemStateChanged
        if(yes_VoucherRestrict_radio.isSelected()){
            jPanel7.setVisible(true);
        }
        else{
            jPanel7.setVisible(false);
        }
    }//GEN-LAST:event_yes_VoucherRestrict_radioItemStateChanged

    private void no_VoucherRestrict_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_no_VoucherRestrict_radioItemStateChanged
        if(no_VoucherRestrict_radio.isSelected()){
            jPanel7.setVisible(false);
        }
        else{
            jPanel7.setVisible(true);
        }
    }//GEN-LAST:event_no_VoucherRestrict_radioItemStateChanged

    private void Facility_save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Facility_save_btnActionPerformed
        if(all_Facility_Radio.isSelected()){

            String Name1 = "Credit Check for billing member";
            try {
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{1,Name1})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name1, 1});

            }

            JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
        else{
            if(SelectedFacilityList.size()>0){

                String AllFacIDStr = "";
                for(int y=0;y<SelectedFacilityList.size();y++){
                    String facname =  SelectedFacilityList.get(y).toString();
                    String Facid = null;
                    try{
                        Facid=getFacilityIdByName(m_App, facname);
                    }
                    catch(BasicException e){

                    }
                    if(Facid!=null){
                        AllFacIDStr = AllFacIDStr+Facid+"#";
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "No facility id found", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }

                // for inserting and updating facility IDs.

                String Name1 = "Credit Check for billing member";
                String Name2 = "Facility for billing member";

                try {
                    if( new PreparedSentence(m_App.getSession()
                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{0,Name1})<=0){

                    new PreparedSentence(m_App.getSession()
                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),Name1, 0});

                }

                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{AllFacIDStr,Name2})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name2, AllFacIDStr});

            }

            JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
        else{
            JOptionPane.showMessageDialog(this, "Select any facility . ", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        }
    }//GEN-LAST:event_Facility_save_btnActionPerformed

    private void remove_facility_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_facility_btnActionPerformed
        try {
            int row = Facility_JList.getSelectedIndex();
            if (row >= 0) {

                String lst=Facility_JList.getSelectedValue().toString();
                SelectedFacilityList.remove(lst);
                Facility_JList.setModel(new ItemsListModel(SelectedFacilityList));

            } else {
                JOptionPane.showMessageDialog(this, "Select any Facility");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_remove_facility_btnActionPerformed

    private void add_facility_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_facility_btnActionPerformed
        if(Facility_combo.getSelectedIndex()!=-1){

            String FacName = Facility_combo.getSelectedItem().toString();
            SelectedFacilityList.add(FacName);
            Facility_JList.setModel(new ItemsListModel( SelectedFacilityList));

        }
        else{
            JOptionPane.showMessageDialog(this, "Select any facility . ", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_add_facility_btnActionPerformed

    private void all_Facility_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_all_Facility_RadioItemStateChanged
        if(all_Facility_Radio.isSelected()){
            jPanel5.setVisible(false);
            Facility_combo.setSelectedIndex(-1);
        }
        else{
            jPanel5.setVisible(true);
        }
    }//GEN-LAST:event_all_Facility_RadioItemStateChanged

    private void save4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save4ActionPerformed

        if(sendMail_check.isSelected()){
            SendEmail ="YES";
        }
        else{
            SendEmail= "NO";

        }

        String FolderPath = memstatPDF_folder_text.getText();
        String Name = "Member Stat PDF Folder Path" ;

        String EmailAccount=null;

        if(EmailAccountList_Combo.getSelectedIndex()!=-1){
            EmailAccount = EmailAccountList_Combo.getSelectedItem().toString();
        }
        else{
            EmailAccount = "";
        }

        String EmailAccountName = "Mem Stat Email Account";

        try {
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{FolderPath,Name})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name , FolderPath});

        }
        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        String Name2 = "Email Mem Stat Yes or No" ;

        try {
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{SendEmail,Name2})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name2 , SendEmail});

        }
        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        // SET EMAIL ACCOUNT .............................................................................................
        try {
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{EmailAccount,EmailAccountName})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),EmailAccountName , EmailAccount});

        }
        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        // for general document path sent by email

        String GeneralDocPath = gen_Email_Path_Text.getText();
        String GenDocName = "General Email Documents";

        try {
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{GeneralDocPath,GenDocName})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),GenDocName , GeneralDocPath});

        }
        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_save4ActionPerformed

    private void refund_Ok_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refund_Ok_ButtonActionPerformed
        if(refundl_series_text.getText()!=null && refundl_series_text.getText().trim().length()>0){

            String Series = refundl_series_text.getText().trim();
            Double Series_no = 0.00;
            String Username  = "REFUND VOUCHER";
            try {
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO sequencedetail (ID,USERNAME,CATEGORY,RSERIES,RMAX,ACTIVE,CRDATE) VALUES(?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.INT , Datas.TIMESTAMP})).exec(new Object[]{UUID.randomUUID().toString(),Username,Username ,Series , Series_no , 1 , new Date()});

                loaddata_for_rooms();
                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (BasicException ex) {
                Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "Enter Series Name", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_refund_Ok_ButtonActionPerformed

    private void room_Ok_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_room_Ok_ButtonActionPerformed
        if(room_series_text.getText()!=null && room_series_text.getText().trim().length()>0){

            String Series = room_series_text.getText().trim();
            Double Series_no = 0.00;
            String Username  = "GUESTROOM_ID";
            try {
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO sequencedetail (ID,USERNAME,CATEGORY,RSERIES,RMAX , ACTIVE , CRDATE ) VALUES(?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.INT , Datas.TIMESTAMP})).exec(new Object[]{UUID.randomUUID().toString(),Username,Username ,Series , Series_no , 1 , new Date()});

                loaddata_for_rooms();
                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (BasicException ex) {
                Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "Enter Series Name", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_room_Ok_ButtonActionPerformed

    private void hall_Ok_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_Ok_ButtonActionPerformed
        if(hall_series_text.getText()!=null && hall_series_text.getText().trim().length()>0){

            String Series = hall_series_text.getText().trim();
            Double Series_no = 0.00;
            String Username  = "HALL_ID";
            try {
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO sequencedetail (ID,USERNAME,CATEGORY,RSERIES,RMAX,ACTIVE,CRDATE) VALUES(?,?,?,?,?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING , Datas.DOUBLE , Datas.INT , Datas.TIMESTAMP})).exec(new Object[]{UUID.randomUUID().toString(),Username,Username ,Series , Series_no , 1 , new Date()});

                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loaddata_for_rooms();
            } catch (BasicException ex) {
                Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            JOptionPane.showMessageDialog(this, "Enter Series Name", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_hall_Ok_ButtonActionPerformed

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
        if(peopleList_Combo.getSelectedIndex()!=-1){

            String User = peopleList_Combo.getSelectedItem().toString();
            String Name = "Booking Discount Permission" ;

            // CODE EDITED FOR CARD ACCESSSSS  FOR MEMBERS  .......................................................

            String GuestRoomCardAccess = "Guest Room Card Access Flag";
            String CardAccessFlagGuest = null;
            String PartyHallCardAccess = "Party Hall Card Access Flag";
            String CardAccessFlagPartyHall = null;

            String CardAccessTypeName = "Card Access Type GuestRooms and PartyHall";
            String  CardAccessTypeFlag=null;

            if(yes_allowGuest_Radio.isSelected()){
                CardAccessFlagGuest="1";
            }
            else{
                CardAccessFlagGuest="0";
            }

            //////////////////////////////////

            if(onlyMember_Radio.isSelected()){
                CardAccessTypeFlag="1";
            }
            else if(memSpouse_radio.isSelected()){
                CardAccessTypeFlag="2";
            }
            else{
                CardAccessTypeFlag="3";
            }

            //////////////////////////////////////////////////////////////////////

            if(jRadioButton11.isSelected()){
                CardAccessFlagPartyHall="1";
            }
            else{
                CardAccessFlagPartyHall="0";
            }
            //////////////////////////////////////////////////////////////////////////

            try {
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{User,Name})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name,User});
            }

            ////////////////////////////////////////////////////////////////////

            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{CardAccessFlagGuest,GuestRoomCardAccess})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),GuestRoomCardAccess,CardAccessFlagGuest});
        }

        if( new PreparedSentence(m_App.getSession()
            , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{CardAccessFlagPartyHall,PartyHallCardAccess})<=0){

        new PreparedSentence(m_App.getSession()
            , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),PartyHallCardAccess,CardAccessFlagPartyHall});
        }

        if( new PreparedSentence(m_App.getSession()
            , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{CardAccessTypeFlag,CardAccessTypeName})<=0){

        new PreparedSentence(m_App.getSession()
            , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),CardAccessTypeName,CardAccessTypeFlag});
        }

        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            JOptionPane.showMessageDialog(this, "Select User Name", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_save_btnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{

            if(!inventoryDebtAcc.getSelectedItem().equals(inventoryAccountDebt)){
                AccountMasterExt acc=(AccountMasterExt) inventorydraccountmodel.getSelectedItem();
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{acc.getid(),"Accout Debit (Inventory)"})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Accout Debit (Inventory)",acc.getid()});
            }
        }

        if(!inventoryCrAcc.getSelectedItem().equals(inventoryAccountCr)){
            AccountMasterExt acc=(AccountMasterExt) inventorycraccountmodel.getSelectedItem();
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{acc.getid(),"Account Credit (Income and Expand)"})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Account Credit (Income and Expand)",acc.getid()});
        }
        }

        if(jRadioButton7.isSelected())
        {

            if( new PreparedSentence(m_App.getSession()
                ,  "UPDATE GENERALTABLE SET VALUE =? where NAME='Reports To Use Manual Inventory Entry'"
                ,new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{1})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT})).exec(new Object[]{UUID.randomUUID().toString(),"Reports To Use Manual Inventory Entry",1});
        }
        }
        else
        {
            if( new PreparedSentence(m_App.getSession()
                ,  "UPDATE GENERALTABLE SET VALUE =? where NAME='Reports To Use Manual Inventory Entry'"
                ,new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{0})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Reports To Use Manual Inventory Entry",0});
        }
        }

        if(urlPath!=null){

            try {
                new PreparedSentence(m_App.getSession()
                    , "UPDATE MEMPHOTOPATH SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{jTextField2.getText(),"Member Photo Path"});
            } catch (BasicException ex) {
                Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
            }

            // JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);

        }else{
            // jTextField2.setText(urlPath);
            try {
                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO MEMPHOTOPATH(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Member Photo Path",jTextField2.getText()});
            } catch (BasicException ex) {
                Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
            }
            // JOptionPane.showMessageDialog(this, "Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton8ItemStateChanged

    }//GEN-LAST:event_jRadioButton8ItemStateChanged

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton7ItemStateChanged

    }//GEN-LAST:event_jRadioButton7ItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel17ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jLabel17ComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17ComponentRemoved

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        jTextField1.setVisible(false);
        jLabel17.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed

        JOptionPane.showMessageDialog(this, "Enter Daily Overall Limit", "GuestLimit", JOptionPane.INFORMATION_MESSAGE);

        jTextField1.requestFocusInWindow();
        jTextField1.setVisible (true);
        jLabel17.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{

            if(!pcaccount.getSelectedItem().equals(pettycashacc)){
                AccountMasterExt acc=(AccountMasterExt) pcmodel.getSelectedItem();
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{acc.getid(),"Petty Cash Account"})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Petty Cash Account",acc.getid()});
            }
        }
            //added by Ganesh
            if(!staxacc.getSelectedItem().equals(servtaxeditor)){
                AccountMasterExt acc=(AccountMasterExt) stmodel.getSelectedItem();
                if( new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{acc.getid(),"Service Tax Account"})<=0){

                new PreparedSentence(m_App.getSession()
                    , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Service Tax Account",acc.getid()});
            }
        }
            //Ended By Ganesh

        if(!nYearIncomeHead.getSelectedItem().equals(nextYearIncomeHead)){
            AccountMasterExt acc=(AccountMasterExt) pcmodel2.getSelectedItem();
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{acc.getid(),"nextFySubIncome"})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"nextFySubIncome",acc.getid()});
        }
        }

        if(!pYearIncomeHead.getSelectedItem().equals(previousYearIncomeHead)){
            AccountMasterExt acc=(AccountMasterExt) pcmodel1.getSelectedItem();
            if( new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{acc.getid(),"prevFySubIncome"})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"prevFySubIncome",acc.getid()});
        }
        }

        if( !pcincharge.getSelectedItem().equals(pettycashincharge)){
            PeopleInfo pinfo=(PeopleInfo) peoplemodel.getSelectedItem();
            if(new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{pinfo.getID(),"Petty Cash Incharge"})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Petty Cash Incharge",pinfo.getID()});
        }
        }

        if( !kioskincharge.getSelectedItem().equals(memberkioskincharge)){
            PeopleInfo pinfo=(PeopleInfo) kioskmodel.getSelectedItem();
            if(new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{pinfo.getID(),"Member Kiosk Incharge"})<=0){

            new PreparedSentence(m_App.getSession()
                , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),"Member Kiosk Incharge",pinfo.getID()});
        }
        }

        if(jRadioButton1.isSelected()){
            //        jRadioButton1.setSelected(true);
            //         new PreparedSentence(m_App.getSession()
                //                , "UPDATE GENERALTABLE SET  VALUE=? where NAME='Credit check for QT'"
                //                , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{1});

            if(jRadioButton3.isSelected()){
                jRadioButton3.setSelected(true);
                new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET  VALUE=? where NAME='Credit check for Guests'"
                    , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{1});
                ///
                jRadioButton1.setSelected(true);
                new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET  VALUE=? where NAME='Credit check for QT'"
                    , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{1});
                ///
            }else
            if(jRadioButton4.isSelected()){
                jRadioButton4.setSelected(true);
                new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET VALUE =? where NAME='Credit check for Guests'"
                    , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{0});
                ///
                jRadioButton1.setSelected(true);
                new PreparedSentence(m_App.getSession()
                    , "UPDATE GENERALTABLE SET  VALUE=? where NAME='Credit check for QT'"
                    , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{1});
                ///

            }else{
                JOptionPane.showMessageDialog(this, "Please select 'Yes' or 'NO'", "Fill Correctly", JOptionPane.WARNING_MESSAGE);
            }

        }else
        if(jRadioButton2.isSelected()){
            jRadioButton2.setSelected(true);
            new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Credit check for QT'"
                , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{0});

            jRadioButton4.setSelected(true);
            new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Credit check for Guests'"
                , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{0});

        }else{
            JOptionPane.showMessageDialog(this, "Please select QT checkbox,'Yes' or 'NO'", "Fill the Checkbox", JOptionPane.WARNING_MESSAGE);
        }

        if(jRadioButton5.isSelected())
        {
            new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Credit Check for Guest Entry Through Member Kiosk'"
                , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{1});
        }
        else
        {
            new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Credit Check for Guest Entry Through Member Kiosk'"
                , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{0});
        }
        if(jRadioButton9.isSelected())
        {
            String i=jTextField1.getText();
            int s=Integer.parseInt(i);
            /*displaying integer value agn to textfield or any other label */
            jTextField1.setText(i);

            new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Overall Guest Limit'"
                , new SerializerWriteBasic(new Datas[]{Datas.INT})).exec(new Object[]{s});
        }
        else
        {
            String s="no";
            new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Overall Guest Limit'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{"NO"});
        }
        ///////by pratima
        if(clevy.getText().length()>0){
        String clevyPerc=clevy.getText();
         new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Canteen Levy'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{clevyPerc});
        }
        
        /////////////////
        JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e1){
            JOptionPane.showMessageDialog(this, "Please enter a vaild number", null, JOptionPane.OK_OPTION);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jLabel8.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jLabel8.setVisible(true);
        jRadioButton3.setVisible(true);
        jRadioButton4.setVisible(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void ExpiryDate_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpiryDate_ButtonActionPerformed
       Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(expriryDate_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            expriryDate_text.setText(Formats.TIMESTAMP.formatValue(date));
            
        }

    }//GEN-LAST:event_ExpiryDate_ButtonActionPerformed

    private void expriryDate_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expriryDate_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expriryDate_textActionPerformed

    private void ExpiryDate_SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpiryDate_SaveButtonActionPerformed
       if(expriryDate_text.getText()!=null && expriryDate_text.getText().trim().length()>0){
           
           try {
               Date expDate = (Date) Formats.TIMESTAMP.parseValue(expriryDate_text.getText());
               
               if(expDate!=null)
               {
                   Calendar cal = Calendar.getInstance();
                    cal.setTime(expDate);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                   
                    int Modyear = year*13;
                    int ModMonth = month*13;
                    int ModDate = day*13;
                    
                    String encriptDate = Modyear+"#"+ModMonth+"#"+ModDate;
                   
                    String Name = "Validate";
                    if( new PreparedSentence(m_App.getSession()
                        , "UPDATE GENERALTABLE SET VALUE=? WHERE NAME=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{encriptDate,Name})<=0){

                    new PreparedSentence(m_App.getSession()
                        , "INSERT INTO GENERALTABLE(ID,NAME,VALUE) VALUES(?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(),Name, encriptDate});

                    }
                    
                    JOptionPane.showMessageDialog(this, "Saved successfully..! ");
                    
                    
               }
               
           } catch (BasicException ex) {
               Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(this, "Error , Please try again ! ");
           }
           
       }
       
    }//GEN-LAST:event_ExpiryDate_SaveButtonActionPerformed

    private void AndroidPeople_AddButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndroidPeople_AddButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AndroidPeople_AddButton1ActionPerformed

    private void AndroidPeople_RemoveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndroidPeople_RemoveButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AndroidPeople_RemoveButton1ActionPerformed

    private void SaveWarehousePending_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveWarehousePending_ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveWarehousePending_ButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String strAssetPhotoFolder= AssetPhotoFolderText.getText();
       try{
        new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='AssetPhotos'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{strAssetPhotoFolder});
        
       JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch(BasicException e1){
           
       }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
        // TODO add your handling code here:
        if(jTextFieldFlag.getText().trim().length()>0){
            if(jTextFieldDays.getText().trim().length()>0){
                if(jTextFieldRate.getText().trim().length()>0){
                    if(jComboBoxIntrestWareouse.getSelectedIndex()!=-1){
                        if(jComboBoxIntrestAcc.getSelectedIndex()!=-1){
                             
         try{
            
        new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Pending Bill intrest Flag'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{jTextFieldFlag.getText()});
        new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Pending Bill Intrest Days'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{jTextFieldDays.getText()});
        new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Pending Bill Intrest Rate'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{jTextFieldRate.getText()});
        
        
        List<AccountMasterExt> sacclist=dlfac.getaccounts();
         new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Pending Bill Intrest Account'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{sacclist.get(jComboBoxIntrestAcc.getSelectedIndex()).getId()});
       JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
       
         getPendingBillIntrestDetails();       
         }catch(BasicException e1){
        }
                     }else{JOptionPane.showMessageDialog(this, "Error , Account cannot be blank!!!!");}
                 }else{JOptionPane.showMessageDialog(this, "Error , Warehouse cannot be blank!!!!");}
               }else{JOptionPane.showMessageDialog(this, "Error , Rate cannot be blank!!!!");}
       }else{JOptionPane.showMessageDialog(this, "Error , Days cannot be blank!!!!");}
   }else{JOptionPane.showMessageDialog(this, "Error , Applicable Flag cannot be blank!!!!");}
                  
               
     
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try{
           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Pending Bill Intrest warehouses'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
         String oldLocation=  obj[0].toString();
           String newLocation=oldLocation.replace("#"+""+ addedlocList.get(jComboBox1.getSelectedIndex()).getID(),"");
       
                new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Pending Bill Intrest warehouses'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{newLocation});
       JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
         getPendingBillIntrestDetails();   
        }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton8ActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        try{
        String oldLocation="";
          Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Pending Bill Intrest warehouses'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
          oldLocation=  obj[0].toString();
           if(!oldLocation.contains(locList.get(jComboBoxIntrestWareouse.getSelectedIndex()).getID()))
        {
       String newLocation=oldLocation.concat("#"+""+ locList.get(jComboBoxIntrestWareouse.getSelectedIndex()).getID());
       
                new PreparedSentence(m_App.getSession()
                , "UPDATE GENERALTABLE SET VALUE =? where NAME='Pending Bill Intrest warehouses'"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{newLocation});
       JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
         getPendingBillIntrestDetails();   
        } }catch(Exception e){}
    }//GEN-LAST:event_AddActionPerformed

    private void jComboBoxIntrestAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIntrestAccActionPerformed
        // TODO add your handling code here:
//        if(jComboBoxIntrestAcc.getSelectedIndex()!=-1){
//            String acc = jComboBoxIntrestAcc.getSelectedItem().toString();
//
//            
//            List<AccountMasterExt> sacclist=new ArrayList<>();
//
//            try{
//                sacclist=dlfac.getaccounts();
//                
//            }
//            catch(BasicException e){
//
//            }
//
//            String AccID= null;
//
//            for(int i=0;i<sacclist.size();i++){
//                String AccName = sacclist.get(i).getName().toString();
//                if(AccName.equals(acc)){
//                    AccID = sacclist.get(jComboBoxIntrestAcc.getSelectedIndex()).getId();
//                    break;
//                }
//            }
//
//           // TempAccList = new ArrayList();//commented by pratima 
//          TempAccList1 =  new ArrayList<AccountMaster>();
//
//            try{
//                TempAccList1 = getAccountList(m_App, AccID);
//            }
//            catch(BasicException e){
//
//            }
//
//            Account_andrd2 = new ComboBoxValModel(TempAccList1);
//            jList2.setModel(Account_andrd2);
//            
//            //jComboBox1.setModel(Account_andrd2);
//        }
    }//GEN-LAST:event_jComboBoxIntrestAccActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBoxIntrestWareouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIntrestWareouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIntrestWareouseActionPerformed

    private void UserList_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserList_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserList_ComboActionPerformed

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        // TODO add your handling code here:

        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        int flag=0;

        try{
            List<AccountMasterExt> sacclist=dlfac.getaccounts();
            String  iAccount1=sacclist.get(jComboBoxIntrestAcc.getSelectedIndex()).getId();
            String  iAccount =  (String)new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Account'", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find();

            for(int i=0;i<sacclist.size();i++){
                if(sacclist.get(i).getId().equals(iAccount1)){
                    if(jComboBoxIntrestAcc.getSelectedIndex()!=-1){
                        Object[] obj3=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest warehouses'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.STRING})).find();
                        String[] locArr=obj3[0].toString().split("#");
                        addedlocList=new ArrayList<LocationInfo>();
                        for(int k=0;k<locArr.length;k++){
                            String temp=locArr[k];

                            for(int j=0;j<locList.size();j++){
                                if(locList.get(j).getID().equals(temp))
                                { // ArrayList<LocationInfo> locTemp= new ArrayList<LocationInfo>();
                                    //    locTemp = locList.get(j);
                                    addedlocList.add((LocationInfo)locList.get(j));
                                    break;
                                }
                            }
                        }
                        addedlocationModel=new  ComboBoxValModel( addedlocList);
                        jList2.setModel(addedlocationModel);
                        jComboBox1.setModel( addedlocationModel);

                        break;
                    }
                }

            }
            //               Object[] obj3=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest warehouses'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.STRING})).find();
            //                String[] locArr=obj3[0].toString().split("#");
            //                addedlocList=new ArrayList<LocationInfo>();
            //               for(int i=0;i<locArr.length;i++){
                //                   String temp=locArr[i];
                //
                //                   for(int j=0;j<locList.size();j++){
                    //                   if(locList.get(j).getID().equals(temp))
                    //                   { // ArrayList<LocationInfo> locTemp= new ArrayList<LocationInfo>();
                        //                      //    locTemp = locList.get(j);
                        //                        addedlocList.add((LocationInfo)locList.get(j));
                        //                        break;
                        //                   }
                    //                   }
                //               }
            //                addedlocationModel=new  ComboBoxValModel( addedlocList);
            //                jList2.setModel(addedlocationModel);
            //                jComboBox1.setModel( addedlocationModel);
            //

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_SelectActionPerformed

    private void pcaccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcaccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pcaccountActionPerformed

    private void pcinchargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcinchargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pcinchargeActionPerformed

    private void AndroidPeople_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndroidPeople_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AndroidPeople_comboActionPerformed

    private void staxaccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staxaccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staxaccActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            // TODO add your handling code here:
            String Gst =jTextField4.getText();
new PreparedSentence(m_App.getSession()
        , "UPDATE GENERALTABLE SET VALUE =? where NAME='Statutory Details'"
        , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{Gst});
JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);  
        } catch (BasicException ex) {
            Logger.getLogger(GeneralSettingsAccounting.class.getName()).log(Level.SEVERE, null, ex);
        }
       jTextField4.setText(null);
    }//GEN-LAST:event_jButton9ActionPerformed

    String From_VoucherEntry_Dt;
    String To_VoucherEntry_Dt;
    int Receipt_entries;
    int payment_entries;
    int Journal_entries;
    int Contra_entries;
    
    List<String> CloseSaleAndroidMobList = new ArrayList<String>();
     List<String> CloseCashAndroidMobList = new ArrayList<String>();
     
     
         public String BankDetail_id;
    private String AccountId = "";
    List<Object> TempAccList = new ArrayList<Object>();
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JCheckBox All_users_check;
    private javax.swing.JList AndroidMobileUser_JList;
    private javax.swing.JButton AndroidPeople_AddButton;
    private javax.swing.JButton AndroidPeople_AddButton1;
    private javax.swing.JButton AndroidPeople_RemoveButton;
    private javax.swing.JButton AndroidPeople_RemoveButton1;
    private javax.swing.JButton AndroidPeople_Save;
    private javax.swing.JComboBox AndroidPeople_combo;
    private javax.swing.JList AndroidPeople_jList;
    private javax.swing.JList AndroidPeople_jList1;
    private javax.swing.JTextField AssetPhotoFolderText;
    private javax.swing.JComboBox EmailAccountList_Combo;
    private javax.swing.JButton ExpiryDate_Button;
    private javax.swing.JButton ExpiryDate_SaveButton;
    private javax.swing.JList Facility_JList;
    private javax.swing.JComboBox Facility_combo;
    private javax.swing.JButton Facility_save_btn;
    private javax.swing.JButton MemPhoto_SaveButton;
    private javax.swing.JRadioButton Mem_Dependant_Radio;
    private javax.swing.JRadioButton Minimum_yes_radio;
    private javax.swing.JRadioButton No_allowGuest_Radio;
    private javax.swing.JRadioButton No_voucherApproval_Radio;
    private javax.swing.JList PhotoEditUser_JList;
    private javax.swing.JButton SaveWarehousePending_Button;
    private javax.swing.JButton Select;
    private javax.swing.JComboBox UserList_Combo;
    private javax.swing.JRadioButton Yes_VoucherApproval_Radio;
    private javax.swing.JComboBox action_combo;
    private javax.swing.JButton add_facility_btn;
    private javax.swing.JRadioButton all_Facility_Radio;
    private javax.swing.JPanel billingMember_panel;
    private javax.swing.JComboBox claccount;
    private javax.swing.JTextField clevy;
    private javax.swing.JCheckBox contra_check;
    private javax.swing.JTextField contra_text;
    private javax.swing.JRadioButton crossing_no_radio;
    private javax.swing.JButton crossing_save_button;
    private javax.swing.JRadioButton crossing_yes_radio;
    private javax.swing.JTextField expriryDate_text;
    private javax.swing.JButton fromdate_buttn;
    private javax.swing.JTextField fromdate_text;
    private javax.swing.JTextField gen_Email_Path_Text;
    private javax.swing.JButton hall_Ok_Button;
    private javax.swing.JTextField hall_series_no_text;
    private javax.swing.JTextField hall_series_text;
    private javax.swing.JComboBox inventoryCrAcc;
    private javax.swing.JComboBox inventoryDebtAcc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxIntrestAcc;
    private javax.swing.JComboBox<String> jComboBoxIntrestWareouse;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextFieldDays;
    private javax.swing.JTextField jTextFieldFlag;
    private javax.swing.JTextField jTextFieldRate;
    private javax.swing.JCheckBox journal_check;
    private javax.swing.JTextField journal_text;
    private javax.swing.JComboBox kioskincharge;
    private javax.swing.JLabel labelUrl;
    private javax.swing.JButton memPhotoEditUser_RemoveBtn;
    private javax.swing.JButton memPhotoEditUser_SaveBtn;
    private javax.swing.JButton memPhotoEditUser_add;
    private javax.swing.JRadioButton memSpouse_radio;
    private javax.swing.JButton memStatRadio_Button;
    private javax.swing.JRadioButton memStatReport_NoRadio;
    private javax.swing.JRadioButton memStatReport_YesRadio;
    private javax.swing.JTextField memberPhoto_destination_text;
    private javax.swing.JTextField memstatPDF_folder_text;
    private javax.swing.JRadioButton minimum_no_radio;
    private javax.swing.JButton minimum_save_button;
    private javax.swing.JTextField mobile_text;
    private javax.swing.JComboBox nYearIncomeHead;
    private javax.swing.JRadioButton no_VoucherRestrict_radio;
    private javax.swing.JRadioButton onlyMember_Radio;
    private javax.swing.JRadioButton other_fac_radio;
    private javax.swing.JComboBox pYearIncomeHead;
    private javax.swing.JCheckBox payment_check;
    private javax.swing.JTextField payment_text;
    private javax.swing.JComboBox pcaccount;
    private javax.swing.JComboBox pcincharge;
    private javax.swing.JComboBox peopleList_Combo;
    private javax.swing.JCheckBox receipt_check;
    private javax.swing.JTextField receipt_text;
    private javax.swing.JButton refund_Ok_Button;
    private javax.swing.JTextField refund_series_no_text;
    private javax.swing.JTextField refundl_series_text;
    private javax.swing.JButton remove_facility_btn;
    private javax.swing.JButton room_Ok_Button;
    private javax.swing.JTextField room_series_text;
    private javax.swing.JTextField rooml_series_no_text;
    private javax.swing.JButton save4;
    private javax.swing.JButton save6_button;
    private javax.swing.JButton save_6_2;
    private javax.swing.JButton save_btn;
    private javax.swing.JButton savenoti_button;
    private javax.swing.JCheckBox sendMail_check;
    private javax.swing.JComboBox staxacc;
    private javax.swing.JTextField toDate_text;
    private javax.swing.JButton todate_buttn;
    private javax.swing.JComboBox warehousePendingLimit_Combo;
    private javax.swing.JRadioButton yes_VoucherRestrict_radio;
    private javax.swing.JRadioButton yes_allowGuest_Radio;
    // End of variables declaration//GEN-END:variables

    
    
  public void loaddata_for_rooms() throws BasicException{
      
       Object[] obj10 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RSERIES , RMAX  FROM sequencedetail WHERE USERNAME = 'HALL_ID' AND CATEGORY='HALL_ID' AND ACTIVE=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.DOUBLE})).find();
                     if(obj10!=null){
                         hall_series_text.setText(obj10[0].toString());
                         hall_series_no_text.setText(obj10[1].toString());
                         hall_series_text.setEnabled(false);
                         hall_series_no_text.setEnabled(false);
                         hall_Ok_Button.setVisible(false);
                     }
                     else{
                         hall_series_text.setText(null);
                         hall_series_no_text.setText("0.00");
                         hall_series_text.setEnabled(true);
                         hall_series_no_text.setEnabled(false);
                         hall_Ok_Button.setVisible(true);
                     }
                     
                    
                     Object[] obj11 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RSERIES , RMAX  FROM sequencedetail WHERE USERNAME = 'GUESTROOM_ID' AND CATEGORY='GUESTROOM_ID' AND ACTIVE=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.DOUBLE})).find();
                     if(obj11!=null){
                         room_series_text.setText(obj11[0].toString());
                         rooml_series_no_text.setText(obj11[1].toString());
                         room_series_text.setEnabled(false);
                         rooml_series_no_text.setEnabled(false);
                         room_Ok_Button.setVisible(false);
                     }
                     else{
                         room_series_text.setText(null);
                         rooml_series_no_text.setText("0.00");
                         room_series_text.setEnabled(true);
                         rooml_series_no_text.setEnabled(false);
                         room_Ok_Button.setVisible(true);
                     }
                    
                    Object[] obj12 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RSERIES , RMAX  FROM sequencedetail WHERE USERNAME = 'REFUND VOUCHER' AND CATEGORY='REFUND VOUCHER' AND ACTIVE=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.DOUBLE})).find();
                     if(obj12!=null){
                         refundl_series_text.setText(obj12[0].toString());
                         refund_series_no_text.setText(obj12[1].toString());
                         refundl_series_text.setEnabled(false);
                         refund_series_no_text.setEnabled(false);
                         refund_Ok_Button.setVisible(false);
                     }
                     else{
                         refundl_series_text.setText(null);
                         refund_series_no_text.setText("0.00");
                         refundl_series_text.setEnabled(true);
                         refund_series_no_text.setEnabled(false);
                         refund_Ok_Button.setVisible(true);
                     }
                     
                         
      
      
  }  
    
    
     public List getEmailAccntList(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT USERNAME FROM email_master WHERE ACTIVE=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
    
// Changes for Billing Members'
     
     public List getFacilityListAll(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM FACILITY WHERE ACTIVE=1 ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
     
   // edited for general Receipts
     public String getAccountID(AppView app , String Name ) throws BasicException{
          Object  Accountid = null;
          Accountid  =  new StaticSentence(app.getSession(), "SELECT ID FROM ACCOUNTMASTER WHERE NAME=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(Accountid!=null){
              String x = Accountid.toString();  
              
              return x;  
          }
          else{
              return "";
          }
      }
     //Added by Guru
     
     public List getLocList1(AppView app , String RoleId) throws BasicException{
         Object o = null; 
         
         List<Object> Mem_list = new ArrayList<Object>();
          
         o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE WHERE NAME =? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(RoleId);
         if(o!=null){
            String o2 = o.toString();
            String []Str = o2.split("#");
            for(int i=0;i<locList.size();i++){
                String x = Str[i].toString();
                
                Object AccName = null;
                AccName  = new StaticSentence(app.getSession(), "SELECT  NAME FROM ACCOUNTMASTER WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(x);
                String AccStr = AccName.toString();
                
                Mem_list.add(AccStr);
            }
             
             
         }         
         else{
             Mem_list = new ArrayList<Object>();
         }
           
          return Mem_list;
      }
     
     
     
     
     public List getAccountList(AppView app , String RoleId) throws BasicException{
         Object o = null; 
         
         List<Object> Mem_list = new ArrayList<Object>();
          
         o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE WHERE NAME =? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(RoleId);
         if(o!=null){
            String o2 = o.toString();
            String []Str = o2.split("#");
            for(int i=0;i<Str.length;i++){
                String x = Str[i].toString();
                
                Object AccName = null;
                AccName  = new StaticSentence(app.getSession(), "SELECT  NAME FROM ACCOUNTMASTER WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(x);
                String AccStr = AccName.toString();
                
                Mem_list.add(AccStr);
            }
             
             
         }         
         else{
             Mem_list = new ArrayList<Object>();
         }
           
          return Mem_list;
      }
     
     
// Changes for Billing Members 
     
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
      
      
    
     public String getFacilityIdByName(AppView app , String Name ) throws BasicException{
          Object  Accountid = null;
          Accountid  =  new StaticSentence(app.getSession(), "SELECT ID FROM FACILITY WHERE NAME=?  AND ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(Accountid!=null){
              String x = Accountid.toString();  
              
              return x;  
          }
          else{
              return null;
          }
      }
     
      public String getFacilityNameByID(AppView app , String Name ) throws BasicException{
          Object  Accountid = null;
          Accountid  =  new StaticSentence(app.getSession(), "SELECT NAME FROM FACILITY WHERE ID=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Name);
          if(Accountid!=null){
              String x = Accountid.toString();  
              
              return x;  
          }
          else{
              return null;
          }
      }
     
      
      public List getPeopleListForAndroidUser(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM PEOPLE WHERE VISIBLE=1 ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
      public List getSelectedUserForAndroidApp(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM PEOPLE WHERE id in (select people from  peoplepurchaseapp ) order by name ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
      
      
     public void getPendingBillIntrestDetails() throws BasicException{
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        int flag=0;
        
    try{
         Object[] obj2=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill intrest Flag'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.INT})).find();
       jTextFieldFlag.setText(obj2[0].toString());
         
       
                Object[] obj1 =  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Days'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.INT})).find();
                if((obj1[0]!=null))
                jTextFieldDays.setText(obj1[0].toString());
                Object[] obj=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Rate'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.DOUBLE})).find();
                if( obj[0]!=null)
               jTextFieldRate.setText(obj[0].toString());
               String  iAccount =  (String)new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Account'", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find();
                List<AccountMasterExt> sacclist=dlfac.getaccounts();
              for(int i=0;i<sacclist.size();i++){
              if(sacclist.get(i).getId().equals(iAccount)){
              jComboBoxIntrestAcc.setSelectedIndex(i);
              break;
              }
              
              }
               Object[] obj3=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest warehouses'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.STRING})).find();
                String[] locArr=obj3[0].toString().split("#");
                addedlocList=new ArrayList<LocationInfo>();
               for(int i=0;i<locArr.length;i++){
                   String temp=locArr[i];
                  
                   for(int j=0;j<locList.size();j++){
                   if(locList.get(j).getID().equals(temp))
                   { // ArrayList<LocationInfo> locTemp= new ArrayList<LocationInfo>();
                      //    locTemp = locList.get(j);
                        addedlocList.add((LocationInfo)locList.get(j));
                        break;
                   }
                   }
               }
                addedlocationModel=new  ComboBoxValModel( addedlocList);
                jList2.setModel(addedlocationModel);
                jComboBox1.setModel( addedlocationModel);
        
        
      
        
        
    } catch (Exception ex) {
            ex.printStackTrace();
        }
    
   
}  
      
     
      
     
   
      
      
}
