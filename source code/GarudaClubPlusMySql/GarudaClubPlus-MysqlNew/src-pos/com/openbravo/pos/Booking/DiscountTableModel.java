
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class DiscountTableModel extends BeanFactoryDataSingle{
    private Session s;
    private final static String[] HALL_TABLEHEADERS = {"Sr No.","Hall Name" , "No. of Days","Cancellation Chrg(%)" , "Fix Charge (Rs.)"};
    private final static String[] ROOM_TABLEHEADERS = {"Sr No.","Guest Room Type" , "No. of Days","Cancellation Chrg(%)" , "Fix Charge(Rs.)"};
    private List<DiscountTableModel.DiscountInfo> Offer_List;
    private List<DiscountTableModel.Room_DiscountInfo> Room_Offer_List;
    private int Hall_discountOffer_size;
    private int Room_discountOffer_size;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    public DiscountTableModel() {
    }
    
     public DiscountTableModel(List<DiscountTableModel.DiscountInfo> Offer_List ){
           this.Offer_List = Offer_List;
       }
   
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    // **  for hall
      public static DiscountTableModel loadInstanceHall_Discount_Offers(AppView app)throws BasicException{
         DiscountTableModel Discount_List = new DiscountTableModel(); 
         
          try{
            Discount_List.Offer_List = new ArrayList<DiscountTableModel.DiscountInfo>();
            Discount_List.Offer_List = new StaticSentence(app.getSession(), "SELECT  ID , DAYS , PERC_RATE , (SELECT h.NAME FROM hall_master h WHERE h.ID = HALL_NAME ) , FIX_CHARGE FROM hall_cancellation_offeres WHERE ACTIVE=1 ORDER BY HALL_NAME , DAYS  ", SerializerWriteString.INSTANCE,new SerializerReadClass(DiscountTableModel.DiscountInfo.class)).list();
            Discount_List.Hall_discountOffer_size = Discount_List.Offer_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(DiscountTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        return Discount_List;
     }
    
      
      // ** for guest room
       public static DiscountTableModel loadInstanceRoom_Discount_Offers(AppView app)throws BasicException{
         DiscountTableModel Discount_List = new DiscountTableModel(); 
         
          try{
            Discount_List.Room_Offer_List = new ArrayList<DiscountTableModel.Room_DiscountInfo>();
            Discount_List.Room_Offer_List = new StaticSentence(app.getSession(), "SELECT  d.ID , d.DAYS , d.PERC_RATE , (SELECT r.ROOMTYPE FROM guestroom_master r WHERE r.ID = d.ROOMTYPE ) , d.FIX_CHARGE FROM guestroom_cancel_offrs d WHERE d.ACTIVE=1 ORDER BY d.ROOMTYPE , d.DAYS", SerializerWriteString.INSTANCE,new SerializerReadClass(DiscountTableModel.Room_DiscountInfo.class)).list();
            Discount_List.Room_discountOffer_size = Discount_List.Room_Offer_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(DiscountTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        return Discount_List;
     }
      
      
       public List getHallNameList( AppView app){
        
        List<Object> temp = new ArrayList<Object>();
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM hall_master", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
     }
    
    
      public  AbstractTableModel getTableModel()
      {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Offer_List.size();
            }
          public int getColumnCount() {
                return HALL_TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return HALL_TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.Integer.class ,java.lang.String.class  ,  java.lang.Integer.class, java.lang.Double.class ,  java.lang.Double.class 
            };
          boolean[] canEdit = new boolean[]{
                false, false, false , false , false
            };
            
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            
          }
          public Object getValueAt(int rowIndex, int columnIndex) {
              DiscountTableModel.DiscountInfo r =Offer_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return rowIndex+1;
                   case 1: return r.getHallName();
                   case 2: return r.getDAYS();
                   case 3: return decimalFormat.format(r.getPERC_RATE());
                   case 4: return decimalFormat.format(r.getFIX_CHARGE());
               }
                return null;
            }
          
          
        };
    } 
      
    
      public  AbstractTableModel getTableModel2()
      {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Room_Offer_List.size();
            }
          public int getColumnCount() {
                return ROOM_TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return ROOM_TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.Integer.class ,java.lang.String.class  ,  java.lang.Integer.class, java.lang.Double.class ,  java.lang.Double.class 
            };
          boolean[] canEdit = new boolean[]{
                false, false, false , false , false
            };
            
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            
          }
          public Object getValueAt(int rowIndex, int columnIndex) {
              DiscountTableModel.Room_DiscountInfo r =Room_Offer_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return rowIndex+1;
                   case 1: return r.getROOMTYPE();
                   case 2: return r.getDAYS();
                   case 3: return decimalFormat.format(r.getPERC_RATE());
                   case 4: return decimalFormat.format(r.getFIX_CHARGE());
               }
                return null;
            }
          
          
        };
    } 
      
      
    
    public int getOfferSize()
    {
        return Hall_discountOffer_size;
    }  
    
    public int getRoom_OfferSize()
    {
        return Room_discountOffer_size;
    }
     public List<DiscountTableModel.DiscountInfo> getDiscountOfferList(){
           if(Offer_List!=null)
        {
            return Offer_List;
        }
        else
            return new ArrayList<DiscountTableModel.DiscountInfo>();
      }
    
     
     public List<DiscountTableModel.Room_DiscountInfo> getRoom_DiscountOfferList(){
           if(Room_Offer_List!=null)
        {
            return Room_Offer_List;
        }
        else
            return new ArrayList<DiscountTableModel.Room_DiscountInfo>();
      }
    
    
     public static class DiscountInfo implements SerializableRead,IKeyed {
          private String ID;
          private int DAYS;
          private Double PERC_RATE;
          private String HALL_NAME;
          private Double FIX_CHARGE;
           public String getId(){
              return ID;
           }
           public void setId(String Id){
              this.ID=Id;
           }
          
            public int getDAYS(){
              return DAYS;
          }
          public void setDAYS(int DAYS){
              this.DAYS = DAYS;
          }
          
          
           public Double getPERC_RATE(){
              return PERC_RATE;
          }
          public void setPERC_RATE(Double PERC_RATE){
              this.PERC_RATE = PERC_RATE;
          }
          
          public String getHallName(){
              return HALL_NAME;
          }
          public void setHallName(String HALL_NAME){
              this.HALL_NAME = HALL_NAME;
          }
         
          public Double getFIX_CHARGE(){
              return FIX_CHARGE;
          }
          public void setFIX_CHARGE(Double FIX_CHARGE){
              this.FIX_CHARGE = FIX_CHARGE;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
          ID = dr.getString(1);
          DAYS= dr.getInt(2);
          PERC_RATE= dr.getDouble(3);
          HALL_NAME = dr.getString(4);
          FIX_CHARGE = dr.getDouble(5);
          
        }

        public Object getKey() {
           return this;
        }
         
         
         
     }
     
     
    public static class Room_DiscountInfo implements SerializableRead,IKeyed {
          private String ID;
          private int DAYS;
          private Double PERC_RATE;
          private String ROOMTYPE;
          private Double FIX_CHARGE;
           public String getId(){
              return ID;
           }
           public void setId(String Id){
              this.ID=Id;
           }
          
            public int getDAYS(){
              return DAYS;
          }
          public void setDAYS(int DAYS){
              this.DAYS = DAYS;
          }
          
          
           public Double getPERC_RATE(){
              return PERC_RATE;
          }
          public void setPERC_RATE(Double PERC_RATE){
              this.PERC_RATE = PERC_RATE;
          }
          
          public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE = ROOMTYPE;
          }
         
          public Double getFIX_CHARGE(){
              return FIX_CHARGE;
          }
          public void setFIX_CHARGE(Double FIX_CHARGE){
              this.FIX_CHARGE = FIX_CHARGE;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
          ID = dr.getString(1);
          DAYS= dr.getInt(2);
          PERC_RATE= dr.getDouble(3);
          ROOMTYPE = dr.getString(4);
          FIX_CHARGE = dr.getDouble(5);
          
        }

        public Object getKey() {
           return this;
        }
         
         
         
     }
}
