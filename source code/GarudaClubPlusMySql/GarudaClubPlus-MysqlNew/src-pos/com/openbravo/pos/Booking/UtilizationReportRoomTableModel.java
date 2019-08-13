
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtilizationReportRoomTableModel extends BeanFactoryDataSingle{
    private Session s;
   
    private int Room_List_Size;
    private List<UtilizationReportRoomTableModel.RoomInfoAll> RoomInfoAll_Data;
    private List<UtilizationReportRoomTableModel.RoomInfo_RoomId> RoomInfo_RoomId_Data;
    
    
    
    @Override
    public void init(Session s) {
        this.s=s;
    }
    
    
      public static UtilizationReportRoomTableModel loadInstanceBooked_Rooms_Status(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         UtilizationReportRoomTableModel Romm_Status_All = new UtilizationReportRoomTableModel(); 
         
          try{
            Romm_Status_All.RoomInfoAll_Data = new ArrayList<UtilizationReportRoomTableModel.RoomInfoAll>();
            Romm_Status_All.RoomInfoAll_Data = new StaticSentence(app.getSession(), "SELECT 'Booked' , COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) AS status , (SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master) AS TOTAL\n" +
                                                                                        "FROM guestroom_booked_details where status=2 and booking_date_ex >= ? and booking_date_ex <= ? \n" +
                                                                                        "union\n" +
                                                                                        "SELECT 'Blocked' ,COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) , (SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master) AS TOTAL\n" +
                                                                                        "FROM guestroom_booked_details where status=3 and booking_date_ex >= ? and booking_date_ex <= ? \n" +
                                                                                        "union\n" +
                                                                                        "SELECT 'Available' ,\n" +
                                                                                        "((SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master) -\n" +
                                                                                        "(SELECT COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) from guestroom_booked_details where status=2 and booking_date_ex >= ? and booking_date_ex <= ?) -\n" +
                                                                                        "(SELECT COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) from guestroom_booked_details where status=3 and booking_date_ex >= ? and booking_date_ex <= ?)\n" +
                                                                                        "\n" +
                                                                                        ") as status ,\n" +
                                                                                        "(SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master) AS TOTAL\n" +
                                                                                        "FROM guestroom_booked_details"
                                                                                        , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  })
                                                                                        ,new SerializerReadClass(UtilizationReportRoomTableModel.RoomInfoAll.class)).list(new Object[]{ ToDate ,  FrmDate , FrmDate ,  ToDate , ToDate ,  FrmDate , FrmDate ,  ToDate , ToDate ,  FrmDate , FrmDate ,  ToDate , FrmDate , ToDate , ToDate , FrmDate  });

            Romm_Status_All.Room_List_Size = Romm_Status_All.RoomInfoAll_Data.size();
            
        }
        catch(BasicException ex){
            Logger.getLogger(UtilizationReportRoomTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Romm_Status_All;
     }
    
    
    
    
    //  LOAD DATA WITH ROOM TYPE ...... 
     
     
     public static UtilizationReportRoomTableModel loadInstanceBooked_Room_Status_RoomID(AppView app , Date FrmDate , Date ToDate , String RoomId)throws BasicException{
         UtilizationReportRoomTableModel Romm_Status_All = new UtilizationReportRoomTableModel(); 
         
          try{
            Romm_Status_All.RoomInfoAll_Data = new ArrayList<UtilizationReportRoomTableModel.RoomInfoAll>();
            Romm_Status_All.RoomInfoAll_Data = new StaticSentence(app.getSession(), "SELECT 'Booked' , COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) AS status , (SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master WHERE ID=?) AS TOTAL\n" +
                                                                                        "FROM guestroom_booked_details where status=2 and booking_date_ex >= ? and booking_date_ex <= ?  and room_type=?  \n" +
                                                                                        "union\n" +
                                                                                        "SELECT 'Blocked' ,COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) , (SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master WHERE ID= ?) AS TOTAL\n" +
                                                                                        "FROM guestroom_booked_details where status=3 and booking_date_ex >= ? and booking_date_ex <= ?  and room_type=? \n" +
                                                                                        "union\n" +
                                                                                        "SELECT 'Available' ,\n" +
                                                                                        "((SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master WHERE ID=?) -\n" +
                                                                                        "(SELECT COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) from guestroom_booked_details where status=2 and booking_date_ex >= ? and booking_date_ex <= ? and room_type=?) - \n" +
                                                                                        "(SELECT COALESCE(SUM(ROOM_NOS*BOOKING_DAYS),0) from guestroom_booked_details where status=3 and booking_date_ex >= ? and booking_date_ex <= ? and room_type=?)\n" +
                                                                                        "\n" +
                                                                                        ") as status ,\n" +
                                                                                        "(SELECT SUM((ROOMS_AVAILABLE)*(DATEDIFF(?,?))) FROM guestroom_master WHERE ID=?) AS TOTAL\n" +
                                                                                        "FROM guestroom_booked_details"
                                                                                        , new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING   , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING  , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING  })
                                                                                        ,new SerializerReadClass(UtilizationReportRoomTableModel.RoomInfoAll.class)).list(new Object[]{ ToDate ,  FrmDate , RoomId , FrmDate ,  ToDate , RoomId , ToDate ,  FrmDate , RoomId , FrmDate ,  ToDate  , RoomId , ToDate ,  FrmDate, RoomId , FrmDate ,  ToDate , RoomId , FrmDate , ToDate , RoomId , ToDate , FrmDate , RoomId });

            Romm_Status_All.Room_List_Size = Romm_Status_All.RoomInfoAll_Data.size();
            
        }
        catch(BasicException ex){
            Logger.getLogger(UtilizationReportRoomTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Romm_Status_All;
     }
    
    
    
    
    
    
    
    
    
     public List<UtilizationReportRoomTableModel.RoomInfoAll> getHallList(){
           if(RoomInfoAll_Data!=null)
        {
            return RoomInfoAll_Data;
        }
        else
            return new ArrayList<UtilizationReportRoomTableModel.RoomInfoAll>();
      }
    
    
    
    
     public static class RoomInfoAll implements SerializableRead,IKeyed {
          
          private String Label;
          private String STATUS;
          private int total;
          
     
           public int getStatus(){
              int intStatus=0;
              if(STATUS!=null){
                  intStatus = Integer.parseInt(STATUS);
                 return intStatus;
              }
              else{
                  return intStatus;
              }
             
          }
          public void setStatus(String STATUS){
              this.STATUS=STATUS;
          }
           public String getLabel(){
              return Label;
          }
          public void setLabel(String Label){
              this.Label=Label;
          }
          
           public int gettotal(){
              return total;
          }
          public void settotal(int total){
              this.total=total;
          }
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            Label = dr.getString(1);
            STATUS = dr.getString(2);
            total = dr.getInt(3);
        }

        public Object getKey() {
           return this;
        }
          
    }
      
      
      
       public List<UtilizationReportRoomTableModel.RoomInfo_RoomId> getHallList_withID(){
           if(RoomInfo_RoomId_Data!=null)
        {
            return RoomInfo_RoomId_Data;
        }
        else
            return new ArrayList<UtilizationReportRoomTableModel.RoomInfo_RoomId>();
      }
      
      
      
      
      // FOR PERTICULAR ROOM TYPE .... #AAKASH
      
        public static class RoomInfo_RoomId implements SerializableRead,IKeyed {
          
          private String Label;
          private String STATUS;
          private int total;
          
     
          public int getStatus(){
              int intStatus=0;
              if(STATUS!=null){
                  intStatus = Integer.parseInt(STATUS);
                 return intStatus;
              }
              else{
                  return intStatus;
              }
             
          }
          public void setStatus(String STATUS){
              this.STATUS=STATUS;
          }
          
           public String getLabel(){
              return Label;
          }
          public void setLabel(String Label){
              this.Label=Label;
          }
          
          public int gettotal(){
              return total;
          }
          public void settotal(int total){
              this.total=total;
          }
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            Label = dr.getString(1);
            STATUS = dr.getString(2);
            total = dr.getInt(3);
            
        }

        public Object getKey() {
           return this;
        }
          
    }
        
        
        
        // GET ROOMTYPE ID FROM ROOM NAMES 
        public String getRoomTypeId(AppView app , String roomName){
            String RoomID = null;
            Object o = null;
         
                try {
                   o  = new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_master WHERE ROOMTYPE = ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(roomName);


               } catch (BasicException ex) {
                   Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
            
            if(o!=null){
               RoomID = o.toString();
            }
            
            return RoomID;
        }
        
        
    
}
