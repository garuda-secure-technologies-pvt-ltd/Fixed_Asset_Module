
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class GuestRoomLinkTableModel extends BeanFactoryDataSingle{
    private Session s;
    private final static String[] TABLEHEADERS = {"Room Type" , "Room No" , "Member Name"};
    private List<GuestRoomLinkTableModel.GuestRoomLinkTableInfo> data;
    private int Guest_Link_Length;
    
    public GuestRoomLinkTableModel() {
        
        
    }
    
    public GuestRoomLinkTableModel(List<GuestRoomLinkTableInfo> data) {
        this.data = data;
    }
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    
    
    
     public static GuestRoomLinkTableModel loadInstanceGuestInfo(AppView app) throws BasicException{
        GuestRoomLinkTableModel GuestInfo = new GuestRoomLinkTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<GuestRoomLinkTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT L.ID , M.ROOMTYPE , L.ROOMNO , L.CUSTOMER_N , L.ACTIVE FROM guestroom_link L , guestroom_master M WHERE L.ACTIVE=1 AND L.ROOMTYPE = M.ID ORDER BY M.ROOMTYPE ,  L.ROOMNO", SerializerWriteString.INSTANCE, new SerializerReadClass(GuestRoomLinkTableModel.GuestRoomLinkTableInfo.class)).list();
           
            GuestInfo.Guest_Link_Length = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
      public int getSize()
      {
        return Guest_Link_Length;
      }
      
      public List<GuestRoomLinkTableModel.GuestRoomLinkTableInfo> getGuestRmList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<GuestRoomLinkTableModel.GuestRoomLinkTableInfo>();
      }
    
    // GET LIST OF ROOM_NO_ALREADY LINKED...AAKASH
     public List getLinked_RoomNos( AppView app ){
         
         List<Object> Room_Nos_list = new ArrayList<Object>();
         try {
            Room_Nos_list  =  new StaticSentence(app.getSession(), "SELECT ROOMNO FROM guestroom_link WHERE ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return Room_Nos_list;
      }  
       
     
     // GET CUSTOMER NAME LINKED WITH GUESTROOM NOS.
      public List getLinked_CustName( AppView app ){
         
         List<Object> Room_Nos_list = new ArrayList<Object>();
         try {
            Room_Nos_list  =  new StaticSentence(app.getSession(), "SELECT CUSTOMER_N FROM guestroom_link WHERE ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return Room_Nos_list;
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
              GuestRoomLinkTableModel.GuestRoomLinkTableInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getROOMTYPE();
                   case 1: return r.getROOMNO();
                   case 2: return r.getCUSTOMER_N();
                 
                 }
                return null;
            }
          
          
          };
        } 
     
     
     
     
     
      public static class GuestRoomLinkTableInfo implements SerializableRead,IKeyed {

        private String ID;
        private String ROOMNO;
        private String ROOMTYPE;
        private String CUSTOMER_N;
        private int ACTIVE;
        
       
         
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getROOMNO(){
              return ROOMNO;
          }
          public void setROOMNO(String ROOMNO){
              this.ROOMNO = ROOMNO;
          }
          public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE =ROOMTYPE;
          }
          public String getCUSTOMER_N(){
              return CUSTOMER_N;
          }
          public void setCUSTOMER_N(String CUSTOMER_N){
              this.CUSTOMER_N = CUSTOMER_N;
          }
          public int getACTIVE(){
              return ACTIVE;
          }
          public void setACTIVE(int ACTIVE){
              this.ACTIVE = ACTIVE;
          }
        
           
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
              ID = dr.getString(1);
             
              ROOMTYPE = dr.getString(2);
              ROOMNO = dr.getString(3);
              CUSTOMER_N = dr.getString(4);
              ACTIVE = dr.getInt(5);
             
              
          }

        public Object getKey() {
           return this;
        }
          
       }
      
      
      // LIST OF GUEST ROOMS  NOS  
      public List getRoomNo_List(AppView app ) throws BasicException{
          List<Object> RoomNo_List = new ArrayList<Object>();
           RoomNo_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ROOM_NOS FROM guestroom_master  WHERE ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          
          return RoomNo_List;
      } 
      
      
      
      
      
     
}
