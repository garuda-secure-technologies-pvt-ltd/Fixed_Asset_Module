

package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.sms.EmailMasterTableForCreateGroup;
;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class MemberFormTableModel {
    
    private List<MemberForm.RecreationalActivitiesListClass> list = new ArrayList<MemberForm.RecreationalActivitiesListClass>();
    private List<MemberForm.ChildrenClass> Childrenlist = new ArrayList<MemberForm.ChildrenClass>();
    private List<MemberForm.OtherClubClass> OtherClubList = new ArrayList<MemberForm.OtherClubClass>();
    private List<MemberForm.SD_Sports_Class> SD_Sports_List = new ArrayList<MemberForm.SD_Sports_Class>();
    private List<MemberForm.SD_RActivity_Class> SD_RActivity_List = new ArrayList<MemberForm.SD_RActivity_Class>();
    private List<MemberForm.SD_Facility_Class> SD_Facility_List = new ArrayList<MemberForm.SD_Facility_Class>();
    private List<MemberForm.SD_Talent_Class> SD_Talent_List = new ArrayList<MemberForm.SD_Talent_Class>();
    private List<MemberForm.SD_ClubActivity_Class> SD_ClubActivity_List = new ArrayList<MemberForm.SD_ClubActivity_Class>();
    
    private final static String[] TABLEHEADERS = {"Sr No. ", "Activity Name" ," Level"  };
    private final static String[] TABLEHEADERS2 = {"Sr No.", "Name" ," Gender" , "DOB","Blood Gr." , "Phone No","EmailId","Id Proof","Unique No" };
    private final static String[] TABLEHEADERS3 = {"Sr No. ", "Club Name","Member No." ,"Type of Member" ,"City" , "Member Since" };
    private final static String[] TABLEHEADERS4 = {"Sr No. ", "Spouse/ Dependant Name" ,"Sports"  };
    private final static String[] TABLEHEADERS5 = {"Sr No. ", "Spouse/ Dependant Name" ,"Sports" ,"Level" };
    private final static String[] TABLEHEADERS6 = {"Sr No. ", "Spouse/ Dependant Name" ,"Facility Selected" };
    private final static String[] TABLEHEADERS7 = {"Sr No. ", "Spouse/ Dependant Name" ,"Special Talent" };
    private final static String[] TABLEHEADERS8 = {"Sr No. ", "Spouse/ Dependant Name" ,"Club Activities" };
    
    private int size;
    private int ChildrenListsize;
    private int OtherClubListSize;
    private int SD_Sports_ListSize;
    private int SD_RActivity_ListSize;
    private int SD_Facility_ListSize;
    private int SD_Talent_ListSize;
    private int SD_ClubActivity_ListSize;
    
     private  MemberFormTableModel()
    {
    }
     
     public  static MemberFormTableModel loademailGroupNameList(AppView m_app ,List<MemberForm.RecreationalActivitiesListClass> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.list=new ArrayList<MemberForm.RecreationalActivitiesListClass>();
       
         d.list =  list;
         d.size = d.list.size();
         
     return d;

    }

     
    // ******************************** CHILDREN INFO  ********************************************************
     
     public  static MemberFormTableModel LoadChildrenClassInfo(AppView m_app ,List<MemberForm.ChildrenClass> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.Childrenlist=new ArrayList<MemberForm.ChildrenClass>();
       
         d.Childrenlist =  list;
         d.ChildrenListsize = d.Childrenlist.size();
         
     return d;

     }
     
     
     public  static MemberFormTableModel LoadOtherClubClassInfo(AppView m_app ,List<MemberForm.OtherClubClass> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.OtherClubList=new ArrayList<MemberForm.OtherClubClass>();
       
         d.OtherClubList =  list;
         d.OtherClubListSize = d.OtherClubList.size();
         
     return d;

     }
     
     //SPOUSE AND DEPENDENTS DETAILS
     
     public  static MemberFormTableModel loadSD_Sports_InfoAll(AppView m_app ,List<MemberForm.SD_Sports_Class> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.SD_Sports_List=new ArrayList<MemberForm.SD_Sports_Class>();
       
         d.SD_Sports_List =  list;
         d.SD_Sports_ListSize = d.SD_Sports_List.size();
         
     return d;

     }
     public  static MemberFormTableModel loadSD_Ractivity_InfoAll(AppView m_app ,List<MemberForm.SD_RActivity_Class> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.SD_RActivity_List=new ArrayList<MemberForm.SD_RActivity_Class>();
       
         d.SD_RActivity_List =  list;
         d.SD_RActivity_ListSize = d.SD_RActivity_List.size();
         
     return d;

     }
     public  static MemberFormTableModel loadSD_Facility_InfoAll(AppView m_app ,List<MemberForm.SD_Facility_Class> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.SD_Facility_List=new ArrayList<MemberForm.SD_Facility_Class>();
       
         d.SD_Facility_List =  list;
         d.SD_Facility_ListSize = d.SD_Facility_List.size();
         
     return d;

     }
     
    public  static MemberFormTableModel loadSD_Talent_InfoAll(AppView m_app ,List<MemberForm.SD_Talent_Class> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.SD_Talent_List=new ArrayList<MemberForm.SD_Talent_Class>();
       
         d.SD_Talent_List =  list;
         d.SD_Talent_ListSize = d.SD_Talent_List.size();
         
     return d;

     } 
    
    public  static MemberFormTableModel loadSD_ClubActivity_InfoAll(AppView m_app ,List<MemberForm.SD_ClubActivity_Class> list ) throws BasicException{
      MemberFormTableModel d = new  MemberFormTableModel();
       d.SD_ClubActivity_List=new ArrayList<MemberForm.SD_ClubActivity_Class>();
       
         d.SD_ClubActivity_List =  list;
         d.SD_ClubActivity_ListSize = d.SD_ClubActivity_List.size();
         
     return d;

     } 
     
    public  AbstractTableModel getTableModel(List<MemberForm.RecreationalActivitiesListClass> list1) {
        
        list=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS[column]);
            }
            public int getRowCount() {
                return list.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.RecreationalActivitiesListClass s = list.get(row);

                switch (column) {

                
                case 0: return s.GetSlNo();
                case 1: return s.GetActivityName();
                case 2: return s.GetActivityLevel();
                    
                default: return null;
                }
            }
        };
    }
    
    
    
     public  AbstractTableModel getTableModelForChildren(List<MemberForm.ChildrenClass> list1) {
        
        Childrenlist=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS2[column]);
            }
            public int getRowCount() {
                return Childrenlist.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS2.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.ChildrenClass s = Childrenlist.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetName();
                case 2: return s.GetGender();
                case 3: return s.GetDateOfBirth();
                case 4: return s.GetBloodGrp();        
                case 5: return s.GetPhoneNo();
                case 6: return s.GetEmailID();    
                case 7: return s.GetIDProof();    
                case 8: return s.GetUniqueNo();
                    
                    
                default: return null;
                }
            }
        };
    }
    
    
     
     
     
    public  AbstractTableModel getTableModelforOtherClubList(List<MemberForm.OtherClubClass> list1) {
        
        OtherClubList=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS3[column]);
            }
            public int getRowCount() {
                return OtherClubList.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS3.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.OtherClubClass s = OtherClubList.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetClubName();
                case 2: return s.GetMemberno();
                case 3: return s.GetTypeofMember();
                case 4: return s.GetCity();     
                case 5: return s.GetMemberSince();
                
                    
                default: return null;
                }
            }
        };
    } 
     
    
    public  AbstractTableModel getSD_Sports_TableModel(List<MemberForm.SD_Sports_Class> list1) {
        
        SD_Sports_List=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS4[column]);
            }
            public int getRowCount() {
                return SD_Sports_List.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS4.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.SD_Sports_Class s = SD_Sports_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Sports();
                    
                default: return null;
                }
            }
        };
    }
    
    public  AbstractTableModel getSD_RActivity_TableModel(List<MemberForm.SD_RActivity_Class> list1) {
        
        SD_RActivity_List=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS5[column]);
            }
            public int getRowCount() {
                return SD_RActivity_List.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS5.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.SD_RActivity_Class s = SD_RActivity_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Sports();
                case 3: return s.GetSD_level();
                    
                default: return null;
                }
            }
        };
    }
    
    
    
    
    public  AbstractTableModel getSD_Facility_TableModel(List<MemberForm.SD_Facility_Class> list1) {
        
        SD_Facility_List=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS6[column]);
            }
            public int getRowCount() {
                return SD_Facility_List.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS6.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.SD_Facility_Class s = SD_Facility_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Facility();
                default: return null;
                }
            }
        };
    }
    
     
    public  AbstractTableModel getSD_Talent_TableModel(List<MemberForm.SD_Talent_Class> list1) {
        
        SD_Talent_List=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS7[column]);
            }
            public int getRowCount() {
                return SD_Talent_List.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS7.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.SD_Talent_Class s = SD_Talent_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Talent();
                
                    
                default: return null;
                }
            }
        };
    }
    
      public  AbstractTableModel getSD_ClubActivity_TableModel(List<MemberForm.SD_ClubActivity_Class> list1) {
        
        SD_ClubActivity_List=list1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS8[column]);
            }
            public int getRowCount() {
                return SD_ClubActivity_List.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS8.length;
            }
            public Object getValueAt(int row, int column) {
               MemberForm.SD_ClubActivity_Class s = SD_ClubActivity_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Activity();
                
                    
                default: return null;
                }
            }
        };
    }
     
}
