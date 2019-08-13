

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
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class GuestRoomCheckInRequestModel extends BeanFactoryDataSingle{
    private Session s;
     private final static String[] TABLEHEADERS = {"Booking No.","Room Type","No. Of rooms Booked","No. of Days", "Booked By", "Bkd Check-In" , "Bkd Check_Out"  , "Check_In" , "Check_Out" };
     private List<GuestRoomCheckInRequestModel.Pending_StatusInfo> PendingRoom_List;
     private int RoomLength;
    
    
    
     public GuestRoomCheckInRequestModel(){
           
       }
        public GuestRoomCheckInRequestModel(List<GuestRoomCheckInRequestModel.Pending_StatusInfo> PendingRoom_List ){
           this.PendingRoom_List = PendingRoom_List;
       }

        @Override
        public void init(Session s) {
           this.s=s;
        }
    
     
        
    public static GuestRoomCheckInRequestModel load_PendingChkIn_Request(AppView app)throws BasicException{
         GuestRoomCheckInRequestModel Booked_Room_Status = new GuestRoomCheckInRequestModel(); 
         try{
            Booked_Room_Status.PendingRoom_List = new ArrayList<GuestRoomCheckInRequestModel.Pending_StatusInfo>();
            Booked_Room_Status.PendingRoom_List = new StaticSentence(app.getSession(), "SELECT C.ID , C.ROOMTYPE , C.MEMNO , C.MEMNAME ,\n" +
                                                                                        "C.ROOMS , C.DAYS , C.CHK_IN , C.CHK_OUT , E_CHK_IN , E_CHK_OUT , BILL_NAME ,\n" +
                                                                                        "(SELECT A.BOOKING_SEQ_NO FROM guestroom_advance_payment A WHERE A.ID=C.ADVNCE_RECV_ID  ) AS BOOKING_SEQ_NO , C.APPROVED , C.X_CHK_OUT \n" +
                                                                                        "FROM guestroom_checkin C WHERE C.APPROVED=0 OR C.APPROVED=2 ORDER BY BOOKING_SEQ_NO ", SerializerWriteString.INSTANCE ,new SerializerReadClass(GuestRoomCheckInRequestModel.Pending_StatusInfo.class)).list();

            Booked_Room_Status.RoomLength = Booked_Room_Status.PendingRoom_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(GuestRoomBookingRequstTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Room_Status;
         
     }
    
    
    
     
     public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return PendingRoom_List.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
              java.lang.String.class ,  java.lang.String.class , java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
               false , false, false, false, false, false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              GuestRoomCheckInRequestModel.Pending_StatusInfo r =PendingRoom_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBooking_Seq_No();
                   case 1: return r.getROOM_TYPE();
                   case 2: return r.getNO_OF_ROOMS_BOOKED();
                   case 3: return r.getNO_OF_DAYS();
                  
                   case 4: return (r.getMem_No()+" - "+r.getMemberName());
                   case 5: return r.getcheck_in();
                   case 6: return r.getcheck_out();
                   case 7: return r.getE_Check_In();
                   case 8: return r.getE_Check_Out();
                   
               }
                return null;
            }
          
          
        };
    } 
    
    
     public List<GuestRoomCheckInRequestModel.Pending_StatusInfo> getRoomList(){
           if(PendingRoom_List!=null)
        {
            return PendingRoom_List;
        }
        else
            return new ArrayList<GuestRoomCheckInRequestModel.Pending_StatusInfo>();
      }
    
     
     
    public static class Pending_StatusInfo implements SerializableRead,IKeyed {
          
    private String ID;      
    private String ROOM_TYPE;
    private String MEMBER_NO;
    private String MEMBER_NAME;
    private int NO_OF_ROOMS_BOOKED;
    private int NO_OF_DAYS;
    private Date check_in;
    private Date check_out;
    private Date E_Check_In ;
    private Date E_Check_Out;
    private String BillName;
    private String Booking_Seq_No;
    private int Approved;
    private Date X_Check_Out;
   
    
   
   
    
    
   
   
   
    
           public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          
           public String getMemberName(){
              return MEMBER_NAME;
          }
          public void setMemberName(String MEMBER_NAME){
              this.MEMBER_NAME=MEMBER_NAME;
          }
          
          public String getMem_No(){
              return MEMBER_NO;
          }
          public void setMem_No(String MEMBER_NO){
              this.MEMBER_NO=MEMBER_NO;
          }
         
           public String getROOM_TYPE(){
              return ROOM_TYPE;
          }
          public void setROOM_TYPE(String ROOM_TYPE){
              this.ROOM_TYPE=ROOM_TYPE;
          }
          
         
          
         
           public int getNO_OF_ROOMS_BOOKED(){
              return NO_OF_ROOMS_BOOKED;
          }
          public void setNO_OF_ROOMS_BOOKED(int NO_OF_ROOMS_BOOKED){
              this.NO_OF_ROOMS_BOOKED=NO_OF_ROOMS_BOOKED;
          }
          
           public int getNO_OF_DAYS(){
              return NO_OF_DAYS;
          }
          public void setNO_OF_DAYS(int NO_OF_DAYS){
              this.NO_OF_DAYS=NO_OF_DAYS;
          }
          
          public String getcheck_in(){
               String x = Formats.TIMESTAMP.formatValue(check_in);
               return x;
          }
          public void setcheck_in(Date check_in){
              this.check_in=check_in;
          }
          
          public String getcheck_out(){
               String x = Formats.TIMESTAMP.formatValue(check_out);
               return x;
          }
          public void setcheck_out(Date check_out){
              this.check_out=check_out;
          }
          
          
          
          
           public String getE_Check_In(){
               String x = Formats.TIMESTAMP.formatValue(E_Check_In);
               return x;
          }
          public void setE_Check_In(Date E_Check_In){
              this.E_Check_In=E_Check_In;
          }
           
          
          public String getE_Check_Out(){
              if(Approved==0){
               String x = Formats.TIMESTAMP.formatValue(E_Check_Out);
               return x;
              }
              else if(Approved==2){
               String x = Formats.TIMESTAMP.formatValue(X_Check_Out);
               return x;
                  
              }
              else{
                  return null;
              }
               
              
          }
          public void setE_Check_Out(Date E_Check_Out){
              this.E_Check_Out=E_Check_Out;
          }
          
          private String getBillName(){
              return BillName;
          }
          private void setBillName(String BillName){
              this.BillName=BillName;
          }
          
          private String getBooking_Seq_No(){
              return Booking_Seq_No;
          }
          private void setBooking_Seq_No(String Booking_Seq_No){
              this.Booking_Seq_No=Booking_Seq_No;
          }
          public int getApproved(){
              return Approved;
          }
          public void setApproved(int Approved){
              this.Approved = Approved;
            
          }
         
          public void setX_Check_Out(Date X_Check_Out){
              this.X_Check_Out=X_Check_Out;
          } 
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          ID = dr.getString(1);
          ROOM_TYPE = dr.getString(2);
          MEMBER_NO = dr.getString(3);
          MEMBER_NAME = dr.getString(4);
          NO_OF_ROOMS_BOOKED = dr.getInt(5);
          NO_OF_DAYS = dr.getInt(6);
          check_in = dr.getTimestamp(7);
          check_out = dr.getTimestamp(8);
          E_Check_In = dr.getTimestamp(9);
          E_Check_Out = dr.getTimestamp(10);
          BillName = dr.getString(11);
          Booking_Seq_No = dr.getString(12);
          Approved = dr.getInt(13);
          X_Check_Out=dr.getTimestamp(14);
          
        }

        public Object getKey() {
           return this;
        }
          
            
      }  
     
     // GET ROOM TYPE ID FROM NAME ......... 
    
   
        public String getRoomTypeId(AppView app , String x ){
         
         Object o = new Object();
         String RmID = null;
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_master WHERE ROOMTYPE=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(x);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             RmID = o.toString();
             
         }
         return RmID;
       }  
     
     
     
}
