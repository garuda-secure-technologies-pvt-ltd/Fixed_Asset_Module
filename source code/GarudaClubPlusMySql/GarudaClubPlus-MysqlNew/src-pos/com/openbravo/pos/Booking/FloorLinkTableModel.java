/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class FloorLinkTableModel extends BeanFactoryDataSingle{
    private Session s;
    private final static String[] TABLEHEADERS = {"Sr No." , "Building Name" , "Floor" , "Room No." , "Room Type"};
    private List<FloorLinkTableModel.FloorLinkTableInfo> data;
    private int Building_size;
    
    
     public FloorLinkTableModel() {
        
    }
    
    public FloorLinkTableModel(List<FloorLinkTableModel.FloorLinkTableInfo> data) {
        this.data = data;
    }
    
    
    @Override
    public void init(Session s) {
        this.s=s;
    }
    
    
    
     public static FloorLinkTableModel loadInstanceGuestInfo(AppView app) throws BasicException{
        FloorLinkTableModel GuestInfo = new FloorLinkTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<FloorLinkTableModel.FloorLinkTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT ID , BUILDING , FLOOR , ROOMTYPE ,ROOMNO , ACTIVE FROM floor_link f WHERE ACTIVE=1 ORDER BY BUILDING , FLOOR ", SerializerWriteString.INSTANCE, new SerializerReadClass(FloorLinkTableModel.FloorLinkTableInfo.class)).list();
            GuestInfo.Building_size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(FloorLinkTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
       public int getSize()
      {
        return Building_size;
      }
     
      public List<FloorLinkTableModel.FloorLinkTableInfo> getFloorLinkList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<FloorLinkTableModel.FloorLinkTableInfo>();
      }
       
       
    
     // GET LIST OF ACTIVE ROOMS NOS ALREADY LINKED WITH FLOOR
       public List getRoomNo_Linked(AppView app , String RoomType){
         List<Object> RoomType_List = new ArrayList<Object>();
         try {
            RoomType_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ROOMNO FROM floor_link f WHERE ACTIVE=1 AND ROOMTYPE=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(RoomType);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomType_List;
       }  
       
     
      // GET LIST OF ACTIVE FLOORS  ALREADY LINKED WITH ROOM NOS
       public List getFloorNames_Linked(AppView app , String BuldgeName){
         List<Object> FloorName_List = new ArrayList<Object>();
         try {
            FloorName_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT FLOOR FROM floor_link f WHERE ACTIVE=1 AND BUILDING=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(BuldgeName);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return FloorName_List;
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
              FloorLinkTableModel.FloorLinkTableInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getBuilding_Name();
                   case 2: return r.getFLOOR_NAME();
                   case 3: return r.getROOMNO();
                   case 4: return r.getROOMTYPE();
                 
                 }
                return null;
            }
          };
        } 
     
     
     
     
     
     
     
      public static class FloorLinkTableInfo implements SerializableRead,IKeyed {

        private String ID;
        private String B_NAME;
        private String FLOOR_NAME;
         private String ROOMTYPE;
        private String ROOMNO;
        private int ACTIVE;
        
       
         
         
         public String getID(){
              return ID;
          }
          public void SetID(String ID){
              this.ID=ID;
          }
          public String getBuilding_Name(){
              return B_NAME;
          }
          public void setBuilding_Name(String B_NAME){
              this.B_NAME = B_NAME;
          }
          public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE =ROOMTYPE;
          }
          public String getROOMNO(){
              return ROOMNO;
          }
          public void setROOMNO(String ROOMNO){
              this.ROOMNO =ROOMNO;
          }
          
          
          public String getFLOOR_NAME(){
              return FLOOR_NAME;
          }
          public void setFLOOR_NAME(String FLOOR_NAME){
              this.FLOOR_NAME = FLOOR_NAME;
          }
          public int getACTIVE(){
              return ACTIVE;
          }
          public void setACTIVE(int ACTIVE){
              this.ACTIVE = ACTIVE;
          }
        
           
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
              ID = dr.getString(1);
             
              B_NAME = dr.getString(2);
              FLOOR_NAME = dr.getString(3);
              ROOMTYPE = dr.getString(4);
              ROOMNO = dr.getString(5);
              ACTIVE = dr.getInt(6);
             
              
          }

        public Object getKey() {
           return this;
        }
     }   
    
}
