
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.JRootFrame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.apache.axis.encoding.Serializer;


public class BuildingTableModel extends BeanFactoryDataSingle{
    private Session s;
    private final static String[] TABLEHEADERS = {"Sr No." , "Building Name" , "No of Floors" , "Floor_Name" };
    private List<BuildingTableModel.BuildingTableInfo> data;
    private int Building_size;
    
    
    
    public BuildingTableModel() {
        
    }
    
    public BuildingTableModel(List<BuildingTableModel.BuildingTableInfo> data) {
        this.data = data;
    }
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    
       public static BuildingTableModel loadInstanceGuestInfo(AppView app) throws BasicException{
        BuildingTableModel GuestInfo = new BuildingTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<BuildingTableModel.BuildingTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT ID , B_NAME, FLOOR_NOS , FLOOR_NAME,ACTIVE FROM building  WHERE ACTIVE=1 ORDER BY B_NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(BuildingTableModel.BuildingTableInfo.class)).list();
           
            GuestInfo.Building_size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
      public int getSize()
      {
        return Building_size;
      }
      public List<BuildingTableModel.BuildingTableInfo> getGuestRmList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<BuildingTableModel.BuildingTableInfo>();
      }
    
    
      
     // GET COUNT OF NO OF BUILDINGS 
       public int getNo_of_Buldg(AppView app ){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT COUNT(*) FROM building  WHERE ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find();
         } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
         int Room_booked = Integer.parseInt(x);
         return Room_booked;
         }
         else{
             return 0;
         }
      }
       
       
         // GET BUILDING LIST CONNECTED TO FLOORS
       public List getBuildingNames_list(AppView app ){
         List<Object> Building_list = new ArrayList<Object>();
         
         try {
            Building_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT B_NAME FROM building WHERE ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
          }
           return Building_list;
        }    
       
      
     // GET COUNT OF FLOORS FOR PERTICULAR BULDG BY ROW INDEX.. #AAKASH  
       public int getNo_of_Floors(AppView app , String BuldgeName){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT FLOOR_NOS FROM building  WHERE ACTIVE=1 AND  B_NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{BuldgeName });
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
         int Room_booked = Integer.parseInt(x);
         return Room_booked;
         }
         else{
             return 0;
         }
         
     }
      
        // GET FLOORS NAMES  FOR PERTICULAR BULDG BY ROW INDEX.. #AAKASH  
       public String getFloorNames(AppView app , String  row){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT FLOOR_NAME FROM building  WHERE ACTIVE=1 AND  B_NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{row });
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
       
         return x;
         }
         else{
             return null;
         }
         
     }
       
       
       
       
       
       // GET COUNT OF ROOMS NO  FOR PERTICULAR FLOOR .. #AAKASH  
       public int getno_of_Rooms(AppView app , String  floor){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT COUNT(*)  FROM floor_link f WHERE ACTIVE=1 AND FLOOR=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{floor });
         } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
         int Room_booked = Integer.parseInt(x);
         return Room_booked;
         }
         else{
             return 0;
         }
         
     } 
       
        // GET ROOM NAME LIST CONNECTED TO FLOORS
       public List getRoomsNames_list(AppView app , String Floor){
         List<Object> RoomName_List = new ArrayList<Object>();
         
         try {
            RoomName_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ROOMNO FROM floor_link f WHERE ACTIVE=1 AND FLOOR=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(Floor);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomName_List;
       }    
       
       
      
      // GET BUILDING NAMES FROM BUILDING...
       public List getBuildingNames (AppView app){
         List<Object> Building_list = new ArrayList<Object>();
         
         try {
            Building_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT B_NAME FROM building WHERE ACTIVE=1 ORDER BY B_NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return Building_list;
       }   
      
       //TO GET FLOORS OF PERTICULAR BUILDING
        public String getFloors(AppView app , String Building){
         Object obj = new Object();
          try {
            obj  =  new StaticSentence(app.getSession(), " SELECT FLOOR_NAME  FROM building WHERE B_NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Building);
           
         } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return obj.toString();
       }  
       
      
      
           // GET STATUS OF ROOMS CHECKED IN BY CUSTOMER.. #AAKASH  
       public int getStatusRoom(AppView app , String  roomno){
         Object temp1 = new Object();;
         int Status=0;
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT ROOMNO FROM guestroom_checkin WHERE ACTIVE=1 AND  ROOMNO=?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{roomno });
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BuildingTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
            String x = temp1.toString();
            Status=1;
          
         }
         else{
            Status=0; 
         }
         return Status;
     }
        
       
      // GET DETAILS OF ROOMS BOOKED FROM GUESTROOM CHECK-IN TABLE... #AAKASH. 
       public List<Object[]> getRoomBookedDetails(AppView app , String RoomNo)
    {
        List<Object[]> obj=null;
        try {
          obj=new PreparedSentence(app.getSession(), "SELECT ROOMTYPE , MEMNO , GUEST_N , DAYS , E_CHK_IN , E_CHK_OUT , ADV_RECV , TOT_AMT ,RM_SERV_CHRG , BILL_NAME FROM guestroom_checkin WHERE ROOMNO=?",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.INT , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.DOUBLE , Datas.DOUBLE , Datas.DOUBLE , Datas.STRING})).list(RoomNo);
       
         
        } catch (BasicException ex) {
            Logger.getLogger(JRootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
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
              BuildingTableModel.BuildingTableInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getBuilding_Name();
                   case 2: return r.getFLOOR_NOS();
                   case 3: return r.getFLOOR_NAME();
                 
                 }
                return null;
            }
          
          
          };
        } 
     
     
    
    
    
    
    
    
    
    
       public static class BuildingTableInfo implements SerializableRead,IKeyed {

        private String ID;
        private String B_NAME;
        private int FLOOR_NOS;
        private String FLOOR_NAME;
        private int ACTIVE;
        
       
         
         
         public String getBuilding_ID(){
              return ID;
          }
          public void setBuilding_ID(String ID){
              this.ID=ID;
          }
          public String getBuilding_Name(){
              return B_NAME;
          }
          public void setBuilding_Name(String B_NAME){
              this.B_NAME = B_NAME;
          }
          public int getFLOOR_NOS(){
              return FLOOR_NOS;
          }
          public void setFLOOR_NOS(int FLOOR_NOS){
              this.FLOOR_NOS =FLOOR_NOS;
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
              FLOOR_NOS = dr.getInt(3);
              FLOOR_NAME = dr.getString(4);
              ACTIVE = dr.getInt(5);
             
              
          }

        public Object getKey() {
           return this;
        }
     }   
}
