/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.BuildingTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class GreetingWishesTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<GreetingWishesTableModel.BirthdayWishesTableInfo> data;
    private int size;
    private final static String[] TABLEHEADERS = {"Sr No." , "Name" , "Member/Dependent" , "Auto/Manual" ,  "CrBy" , "CrDate" , "Prefix" , "Message" };
    private final static String[] TABLEHEADERS1 = {"Sr No." , "Name" , "Date" , "Auto/Manual" ,  "CrBy" , "CrDate" , "Prefix" , "Message" };
    private List<GreetingWishesTableModel.FestivalWishInfo> data1;
    private int size1; 
    
     
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
      public static GreetingWishesTableModel loadBirthdayWish(AppView app) throws BasicException{
        GreetingWishesTableModel GuestInfo = new GreetingWishesTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<GreetingWishesTableModel.BirthdayWishesTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT ID , NAME, MESSAGE , MEMFLAG,AUTOFLAG , BIRTHDAYFLAG , CRBY , CRDATE , ACTIVE , PREFIX , MEMNAME , MESSAGE1 FROM sms_greetings  WHERE ACTIVE=1 ", SerializerWriteString.INSTANCE, new SerializerReadClass(GreetingWishesTableModel.BirthdayWishesTableInfo.class)).list();
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(GreetingWishesTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
      public int getSize()
      {
        return size;
      }
      public List<GreetingWishesTableModel.BirthdayWishesTableInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<GreetingWishesTableModel.BirthdayWishesTableInfo>();
      }
      
      
      
    
      public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              GreetingWishesTableModel.BirthdayWishesTableInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getNAME();
                   case 2: if(r.getMEMFLAG()==1){
                            return "Member";
                             }
                            else{
                                return "Dependent";
                            }
                   case 3: if(r.getAUTOFLAG()==1){
                                return "Auto";
                            }
                            else{
                                return "Manual";
                            }
                  
                   case 4: return r.getCRBY();
                   case 5: return r.getCRDATE();
                   case 6: return r.getPREFIX();
                   case 7: return r.getMESSAGE1();
                 
                 }
                return null;
            }
          
          
          };
        } 
    
    
      
      
       public static class BirthdayWishesTableInfo implements SerializableRead,IKeyed {

        private String ID;
        private String NAME;
        private String MESSAGE;
        private int MEMFLAG;
        private int AUTOFLAG;
        
        private int BIRTHDAYFLAG;
        private String CRBY;
        private Date CRDATE;
        private int ACTIVE;
        private String PREFIX;
        private int MEMNAME;
        private String MESSAGE1;
       
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getNAME(){
              return NAME;
          }
          public void setNAME(String NAME){
              this.NAME = NAME;
          }
          public String getFullMESSAGE(){
              return MESSAGE;
          }
          public void setFullMESSAGE(String MESSAGE){
              this.MESSAGE =MESSAGE;
          }
          public int getMEMFLAG(){
              return MEMFLAG;
          }
          public void setMEMFLAG(int MEMFLAG){
              this.MEMFLAG = MEMFLAG;
          }
          public int getACTIVE(){
              return ACTIVE;
          }
          public void setACTIVE(int ACTIVE){
              this.ACTIVE = ACTIVE;
          }
          
           public int getAUTOFLAG(){
              return AUTOFLAG;
          }
          public void setAUTOFLAG(int AUTOFLAG){
              this.AUTOFLAG = AUTOFLAG;
          }
          
           public int getBIRTHDAYFLAG(){
              return BIRTHDAYFLAG;
          }
          public void setBIRTHDAYFLAG(int BIRTHDAYFLAG){
              this.BIRTHDAYFLAG = BIRTHDAYFLAG;
          }
          
          
          
          public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
        
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
           public String getPREFIX(){
              return PREFIX;
          }
          public void setPREFIX(String PREFIX){
              this.PREFIX=PREFIX;
          }
           public int getMEMNAME_FLAG(){
              return MEMNAME;
          }
          public void setMEMNAME_FALG(int MEMNAME){
              this.MEMNAME=MEMNAME;
          }
           public String getMESSAGE1(){
              return MESSAGE1;
          }
          public void setMESSAGE1(String MESSAGE1){
              this.MESSAGE1=MESSAGE1;
          }
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                NAME = dr.getString(2);
                MESSAGE = dr.getString(3);
                MEMFLAG = dr.getInt(4);
                AUTOFLAG=dr.getInt(5);

                BIRTHDAYFLAG = dr.getInt(6);
                CRBY = dr.getString(7);
                CRDATE = dr.getTimestamp(8);
                ACTIVE = dr.getInt(9);
                PREFIX = dr.getString(10);
                MEMNAME = dr.getInt(11);
                MESSAGE1 = dr.getString(12);
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
     // LOAD DATA FOR FESTIVALS WISHES--------------------------------------------------------------------------------------------------------
       
       
      public static GreetingWishesTableModel loadFestivalWishes(AppView app) throws BasicException{
        GreetingWishesTableModel GuestInfo1 = new GreetingWishesTableModel(); 
    
     try{
            GuestInfo1.data1 = new ArrayList<GreetingWishesTableModel.FestivalWishInfo>();
            GuestInfo1.data1 = new StaticSentence(app.getSession(), "SELECT ID , NAME, DATE , PRIFIX,MEMNAMEFLAG , MESSAGE , SENT , CRBY , CRDATE ,  ACTIVE , AUTOFLAG FROM sms_festival  WHERE ACTIVE=1 ", SerializerWriteString.INSTANCE, new SerializerReadClass(GreetingWishesTableModel.FestivalWishInfo.class)).list();
           
            GuestInfo1.size1 = GuestInfo1.data1.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(GreetingWishesTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo1;
  
     }  
       
       
       public int getSize1()
      {
        return size1;
      }
      public List<GreetingWishesTableModel.FestivalWishInfo> getList1(){
           if(data1!=null)
        {
            return data1;
        }
        else
            return new ArrayList<GreetingWishesTableModel.FestivalWishInfo>();
      } 
      
      
      
       
      public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data1.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS1.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS1[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              GreetingWishesTableModel.FestivalWishInfo r =data1.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getNAME();
                   case 2: return r.getDATE();
                   case 3: if(r.getAUTOFLAG()==1){
                                return "Auto";
                            }
                            else{
                                return "Manual";
                            }
                   case 4: return r.getCRBY();
                   case 5: return r.getCRDATE();
                   case 6: return r.getPREFIX();
                   case 7: return r.getMESSAGE1();
                 
                 }
                return null;
            }
          
          
          };
        }   
    
      
      
     public static class FestivalWishInfo implements SerializableRead,IKeyed {

        private String ID;
        private String NAME;
        private Date DATE;
        
        private int AUTOFLAG;
        private String CRBY;
        private Date CRDATE;
        private int ACTIVE;
        private String PREFIX;
        private int MEMNAMEFLAG;
        private String MESSAGE1;
        int SENT;
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getNAME(){
              return NAME;
          }
          public void setNAME(String NAME){
              this.NAME = NAME;
          }
          
         
          public int getACTIVE(){
              return ACTIVE;
          }
          public void setACTIVE(int ACTIVE){
              this.ACTIVE = ACTIVE;
          }
          
           public int getAUTOFLAG(){
              return AUTOFLAG;
          }
          public void setAUTOFLAG(int AUTOFLAG){
              this.AUTOFLAG = AUTOFLAG;
          }
          
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
          
          public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
        
           public String getDATE(){
              String x = Formats.TIMESTAMP.formatValue(DATE);
               return x;
          }
          public void setDATE(Date DATE){
              this.DATE=DATE;
          }
          
           public String getPREFIX(){
              return PREFIX;
          }
          public void setPREFIX(String PREFIX){
              this.PREFIX=PREFIX;
          }
           public int getMEMNAME_FLAG(){
              return MEMNAMEFLAG;
          }
          public void setMEMNAME_FALG(int MEMNAMEFLAG){
              this.MEMNAMEFLAG=MEMNAMEFLAG;
          }
           public String getMESSAGE1(){
              return MESSAGE1;
          }
          public void setMESSAGE1(String MESSAGE1){
              this.MESSAGE1=MESSAGE1;
          }
           
          public int getSENTFLAG(){
              return SENT;
          }
          public void setSENT_FLAG(int SENT){
              this.SENT=SENT;
          }
          
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                NAME = dr.getString(2);
                DATE = dr.getTimestamp(3);
                PREFIX = dr.getString(4);
                MEMNAMEFLAG = dr.getInt(5);        
                MESSAGE1 = dr.getString(6);
                SENT = dr.getInt(7);
                CRBY = dr.getString(8);
                CRDATE = dr.getTimestamp(9);
                ACTIVE = dr.getInt(10);
                AUTOFLAG=dr.getInt(11);
                
                
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }     
      
      
      
       
       
}
