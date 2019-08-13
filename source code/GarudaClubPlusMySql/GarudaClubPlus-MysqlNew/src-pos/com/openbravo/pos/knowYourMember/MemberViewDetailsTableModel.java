

package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.knowYourMember.MemberViewDetails;
//import com.openbravo.pos.sms.EmailMasterTableForCreateGroup;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class MemberViewDetailsTableModel {
    
    private List<MemberViewDetails.RecreationalActivitiesListClass> list = new ArrayList<MemberViewDetails.RecreationalActivitiesListClass>();
    private List<MemberViewDetails.ChildrenClass> Childrenlist = new ArrayList<MemberViewDetails.ChildrenClass>();
    private List<MemberViewDetails.OtherClubClass> OtherClubList = new ArrayList<MemberViewDetails.OtherClubClass>();
    private List<MemberViewDetails.SD_Sports_Class> SD_Sports_List = new ArrayList<MemberViewDetails.SD_Sports_Class>();
    private List<MemberViewDetails.SD_RActivity_Class> SD_RActivity_List = new ArrayList<MemberViewDetails.SD_RActivity_Class>();
    private List<MemberViewDetails.SD_Facility_Class> SD_Facility_List = new ArrayList<MemberViewDetails.SD_Facility_Class>();
    private List<MemberViewDetails.SD_Talent_Class> SD_Talent_List = new ArrayList<MemberViewDetails.SD_Talent_Class>();
     private List<MemberViewDetails.SD_ClubActivity_Class> SD_ClubActivity_List = new ArrayList<MemberViewDetails.SD_ClubActivity_Class>();
    
    
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
    
    
     private  MemberViewDetailsTableModel()
    {
    }
     public  static MemberViewDetailsTableModel loadSD_ClubActivity_InfoAll(AppView m_app ,List<MemberViewDetails.SD_ClubActivity_Class> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.SD_ClubActivity_List=new ArrayList<MemberViewDetails.SD_ClubActivity_Class>();
       
         d.SD_ClubActivity_List =  list;
         d.SD_ClubActivity_ListSize = d.SD_ClubActivity_List.size();
         
     return d;

     }
     public  static MemberViewDetailsTableModel loademailGroupNameList(AppView m_app ,List<MemberViewDetails.RecreationalActivitiesListClass> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.list=new ArrayList<MemberViewDetails.RecreationalActivitiesListClass>();
       
         d.list =  list;
         d.size = d.list.size();
         
     return d;

    }

     
    // ******************************** CHILDREN INFO  ********************************************************
     
     public  static MemberViewDetailsTableModel LoadChildrenClassInfo(AppView m_app ,List<MemberViewDetails.ChildrenClass> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.Childrenlist=new ArrayList<MemberViewDetails.ChildrenClass>();
       
         d.Childrenlist =  list;
         d.ChildrenListsize = d.Childrenlist.size();
         
     return d;

     }
     
     
     public  static MemberViewDetailsTableModel LoadOtherClubClassInfo(AppView m_app ,List<MemberViewDetails.OtherClubClass> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.OtherClubList=new ArrayList<MemberViewDetails.OtherClubClass>();
       
         d.OtherClubList =  list;
         d.OtherClubListSize = d.OtherClubList.size();
         
     return d;

     }
     
     //SPOUSE AND DEPENDENTS DETAILS
     
     public  static MemberViewDetailsTableModel loadSD_Sports_InfoAll(AppView m_app ,List<MemberViewDetails.SD_Sports_Class> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.SD_Sports_List=new ArrayList<MemberViewDetails.SD_Sports_Class>();
       
         d.SD_Sports_List =  list;
         d.SD_Sports_ListSize = d.SD_Sports_List.size();
         
     return d;

     }
     public  static MemberViewDetailsTableModel loadSD_Ractivity_InfoAll(AppView m_app ,List<MemberViewDetails.SD_RActivity_Class> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.SD_RActivity_List=new ArrayList<MemberViewDetails.SD_RActivity_Class>();
       
         d.SD_RActivity_List =  list;
         d.SD_RActivity_ListSize = d.SD_RActivity_List.size();
         
     return d;

     }
     public  static MemberViewDetailsTableModel loadSD_Facility_InfoAll(AppView m_app ,List<MemberViewDetails.SD_Facility_Class> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.SD_Facility_List=new ArrayList<MemberViewDetails.SD_Facility_Class>();
       
         d.SD_Facility_List =  list;
         d.SD_Facility_ListSize = d.SD_Facility_List.size();
         
     return d;

     }
     
    public  static MemberViewDetailsTableModel loadSD_Talent_InfoAll(AppView m_app ,List<MemberViewDetails.SD_Talent_Class> list ) throws BasicException{
      MemberViewDetailsTableModel d = new  MemberViewDetailsTableModel();
       d.SD_Talent_List=new ArrayList<MemberViewDetails.SD_Talent_Class>();
       
         d.SD_Talent_List =  list;
         d.SD_Talent_ListSize = d.SD_Talent_List.size();
         
     return d;

     } 
    public  AbstractTableModel getTableModel(List<MemberViewDetails.RecreationalActivitiesListClass> list1) {
        
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
               MemberViewDetails.RecreationalActivitiesListClass s = list.get(row);

                switch (column) {

                
                case 0: return s.GetSlNo();
                case 1: return s.GetActivityName();
                case 2: return s.GetActivityLevel();
                    
                default: return null;
                }
            }
        };
    }
    
    
    
     public  AbstractTableModel getTableModelForChildren(List<MemberViewDetails.ChildrenClass> list1) {
        
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
               MemberViewDetails.ChildrenClass s = Childrenlist.get(row);

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
    
    
     
     
     
    public  AbstractTableModel getTableModelforOtherClubList(List<MemberViewDetails.OtherClubClass> list1) {
        
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
               MemberViewDetails.OtherClubClass s = OtherClubList.get(row);

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
     
    
    public  AbstractTableModel getSD_Sports_TableModel(List<MemberViewDetails.SD_Sports_Class> list1) {
        
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
               MemberViewDetails.SD_Sports_Class s = SD_Sports_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Sports();
                    
                default: return null;
                }
            }
        };
    }
    
    public  AbstractTableModel getSD_RActivity_TableModel(List<MemberViewDetails.SD_RActivity_Class> list1) {
        
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
               MemberViewDetails.SD_RActivity_Class s = SD_RActivity_List.get(row);

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
    
    
    
    
    public  AbstractTableModel getSD_Facility_TableModel(List<MemberViewDetails.SD_Facility_Class> list1) {
        
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
               MemberViewDetails.SD_Facility_Class s = SD_Facility_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Facility();
                default: return null;
                }
            }
        };
    }
    
     
    public  AbstractTableModel getSD_Talent_TableModel(List<MemberViewDetails.SD_Talent_Class> list1) {
        
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
               MemberViewDetails.SD_Talent_Class s = SD_Talent_List.get(row);

                switch (column) {

                
                case 0: return row+1;
                case 1: return s.GetSD_Name();
                case 2: return s.GetSD_Talent();
                
                    
                default: return null;
                }
            }
        };
    }
    
     public  AbstractTableModel getSD_ClubActivity_TableModel(List<MemberViewDetails.SD_ClubActivity_Class> list1) {
        
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
                
               MemberViewDetails.SD_ClubActivity_Class s = SD_ClubActivity_List.get(row);

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
