
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class BookedRoomStatusTableModel extends BeanFactoryDataSingle {
    private Session s;
    private final static String[] TABLEHEADERS = {"Booking No.", "Room Type","No of Rooms Booked ", "Booked By", "Booking Date", "Booking Status" , "Booked No of Days" , "Request Status"};
     private final static String[] REJECTED_ENTRIES = {"Room Type","No of Rooms Booked ", "Booked By", "Booking Date", "Booking Status" , "Booked No of Days" , "Request Status"};
    private List<BookedRoomStatusTableModel.Room_StatusInfo> status_data;
    private List<BookedRoomStatusTableModel.Rejected_room_info> Rejected_rooms;
     private int booked_room_Length;
     private int rejected_room_length;
     public BookedRoomStatusTableModel() {
    }
     
     public BookedRoomStatusTableModel(List<BookedRoomStatusTableModel.Room_StatusInfo> data1) {
        this.status_data = data1;
    } 
     
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    
    }
    
    
    
     public static BookedRoomStatusTableModel loadInstanceBooked_Room_Status(AppView app)throws BasicException{
         BookedRoomStatusTableModel Booked_Room_Status = new BookedRoomStatusTableModel(); 
         Date currDate = new Date();
         Calendar c =Calendar.getInstance();
         c.setTimeInMillis(new Date().getTime());
         c.add(Calendar.DATE, -1);
         currDate = c.getTime();
         
         
         
          try{
            Booked_Room_Status.status_data = new ArrayList<BookedRoomStatusTableModel.Room_StatusInfo>();
          //  Booked_Room_Status.status_data = new StaticSentence(app.getSession(), "SELECT  b.ID, (SELECT m.ROOMTYPE FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_DATE , (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = b.MEMBERNAME) , b.MEMBER_FLAG ,b.NON_MEMBER_FLAG, b.STATUS, b.FLAG, b.ROOM_NOS , b.BOOKING_DATE_EX , b.BOOKING_DAYS , b.MEMBER_NO, b.NON_MEM_NAME, b.NON_MEM_CNTCT, b.NON_MEM_ADDR, b.CHARGES , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.LUXURYTAX), (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX3) , b.BASIC1, b.CASCADE1,b.ROOM_TYPE,b.MEMBERNAME, b.MAX_CAPACITY , b.BASIC2 , b.CASCADE2 , LAST_PAYMENT_DATE , PAYMENT_FLAG , ROLE , (SELECT m.ADVANCE_PERC FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_SEQ_NO , CHK_IN_FLAG , (SELECT CONCAT(C.ADDRESS , '\\n' , C.ADDRESS2 , '\\n' , C.CITY  ) FROM CUSTOMERS C WHERE C.ID = b.MEMBERNAME) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.LUXURYTAX), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX2) ,(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX3) FROM guestroom_booked_details b WHERE b.BOOKING_DATE_EX >= ?  AND b.STATUS NOT IN (1) AND b.CHK_IN_FLAG!=2 order by  b.BOOKING_SEQ_NO ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}) ,new SerializerReadClass(BookedRoomStatusTableModel.Room_StatusInfo.class)).list(new Object[]{currDate});
           
            //Booked_Room_Status.status_data = new StaticSentence(app.getSession(), "SELECT  b.ID, (SELECT m.ROOMTYPE FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_DATE , (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = b.MEMBERNAME) , b.MEMBER_FLAG ,b.NON_MEMBER_FLAG, b.STATUS, b.FLAG, b.ROOM_NOS , b.BOOKING_DATE_EX , b.BOOKING_DAYS , b.MEMBER_NO, b.NON_MEM_NAME, b.NON_MEM_CNTCT, b.NON_MEM_ADDR, b.CHARGES , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.LUXURYTAX), (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX3) , b.BASIC1, b.CASCADE1,b.ROOM_TYPE,b.MEMBERNAME, b.MAX_CAPACITY , b.BASIC2 , b.CASCADE2 , LAST_PAYMENT_DATE , PAYMENT_FLAG , ROLE , (SELECT m.ADVANCE_PERC FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_SEQ_NO , CHK_IN_FLAG , (SELECT CONCAT(C.ADDRESS , '\\n' , C.ADDRESS2 , '\\n' , C.CITY  ) FROM CUSTOMERS C WHERE C.ID = b.MEMBERNAME) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.LUXURYTAX), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX2) ,(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX3),b.ref,b.bdatechangeflag FROM guestroom_booked_details b WHERE  b.STATUS NOT IN (1) AND b.CHK_IN_FLAG!=2 order by  b.BOOKING_SEQ_NO ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}) ,new SerializerReadClass(BookedRoomStatusTableModel.Room_StatusInfo.class)).list(new Object[]{currDate});//commented by pratima
              Booked_Room_Status.status_data = new StaticSentence(app.getSession(), "SELECT  b.ID, (SELECT m.ROOMTYPE FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_DATE , (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = b.MEMBERNAME) , b.MEMBER_FLAG ,b.NON_MEMBER_FLAG, b.STATUS, b.FLAG, b.ROOM_NOS , b.BOOKING_DATE_EX , b.BOOKING_DAYS , b.MEMBER_NO, b.NON_MEM_NAME, b.NON_MEM_CNTCT, b.NON_MEM_ADDR, b.CHARGES , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.LUXURYTAX), (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX3) , b.BASIC1, b.CASCADE1,b.ROOM_TYPE,b.MEMBERNAME, b.MAX_CAPACITY , b.BASIC2 , b.CASCADE2 , LAST_PAYMENT_DATE , PAYMENT_FLAG , ROLE , (SELECT m.ADVANCE_PERC FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_SEQ_NO , CHK_IN_FLAG , (SELECT CONCAT(C.ADDRESS , '\\n' , C.ADDRESS2 , '\\n' , C.CITY  ) FROM CUSTOMERS C WHERE C.ID = b.MEMBERNAME) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.LUXURYTAX), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX2) ,(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX3),b.ref,b.bdatechangeflag ,b.cdatechangeflag FROM guestroom_booked_details b WHERE  b.STATUS NOT IN (1) AND b.CHK_IN_FLAG!=2 order by  b.BOOKING_SEQ_NO ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}) ,new SerializerReadClass(BookedRoomStatusTableModel.Room_StatusInfo.class)).list(new Object[]{currDate});//added by pratima
         
            
            
            Booked_Room_Status.booked_room_Length = Booked_Room_Status.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Room_Status;
         
     }
     
     
     
     
     // loading for cancelled request
     
      public static BookedRoomStatusTableModel loadInstance_Cancel_rooms(AppView app)throws BasicException{
         BookedRoomStatusTableModel Rejected_Room_status = new BookedRoomStatusTableModel(); 
       
          try{
            Rejected_Room_status.Rejected_rooms = new ArrayList<BookedRoomStatusTableModel.Rejected_room_info>();
            Rejected_Room_status.Rejected_rooms = new StaticSentence(app.getSession(), "SELECT  b.ID, (SELECT m.ROOMTYPE FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_DATE , (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = b.MEMBERNAME) , b.MEMBER_FLAG ,b.NON_MEMBER_FLAG, b.STATUS, b.FLAG, b.ROOM_NOS , b.BOOKING_DATE_EX , b.BOOKING_DAYS , b.MEMBER_NO, b.NON_MEM_NAME, b.NON_MEM_CNTCT, b.NON_MEM_ADDR, b.CHARGES , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.LUXURYTAX), (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX3), b.BASIC1, b.CASCADE1,b.ROOM_TYPE,b.MEMBERNAME, b.MAX_CAPACITY , b.BASIC2 , b.CASCADE2 , LAST_PAYMENT_DATE , PAYMENT_FLAG , CANCEL_REASON , REQ_APPROV_BY , REQ_APPROV_DATE , ROLE , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.LUXURYTAX), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX2) ,(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = b.TAX3)  FROM guestroom_cancelled_details b WHERE FLAG=2 OR PAYMENT_FLAG=2 order by BOOKING_DATE",  SerializerWriteString.INSTANCE, new SerializerReadClass(BookedRoomStatusTableModel.Rejected_room_info.class)).list();
           
            Rejected_Room_status.rejected_room_length = Rejected_Room_status.Rejected_rooms.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Rejected_Room_status;
      }
     
     
     
     public int getRoom_Booked(AppView app ,String RoomType , Date date){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT BOOKED_ROOMS FROM guestroom_availibility WHERE ROOM_TYPE= ?  AND BOOKED_DATES= ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), SerializerReadInteger.INSTANCE).find(new Object[]{RoomType , date } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
         int Room_booked = Integer.parseInt(x);
         return Room_booked;
         }
         else{
             return -1;
         }
         
     }
     
     
      public String getCancel_reason(AppView app ,String ID ){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT CANCEL_REASON FROM guestroom_booked_details WHERE FLAG= 2  AND ID= ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{ID } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String x = temp1.toString();
       
        return x;
         
     }
     
      
     public int getPending_ReqList(AppView app){
        
        List<Object> temp = new ArrayList<Object>();
        int list_size=0;
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_booked_details WHERE FLAG=0; ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
           
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(temp != null){
                list_size = temp.size();
        }
        else{
             list_size =0;
        }
        return list_size; 
     }
     
     
     
      public int getTotal_Rooms(AppView app ,String RoomType){
         Object temp1 = new Object();
         
         
         try {
             
            temp1  =  new StaticSentence(app.getSession(), "SELECT ROOMS_AVAILABLE FROM guestroom_master WHERE ROOMTYPE = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{RoomType  } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String x = temp1.toString();
         int Total_Rooms = Integer.parseInt(x);
         
         return Total_Rooms;
      }
     
     public String getRoom_id(AppView app ,String RoomType){
         Object temp1 = new Object();
         
         
         try {
             
            temp1  =  new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_master WHERE ROOMTYPE = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{RoomType  } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String x = temp1.toString();
        
         return x;
      }
     
     
     public int getAdvance_bookingDate(AppView app ,String RoomType){
         Object temp1 = new Object();
         
         
         try {
             
            temp1  =  new StaticSentence(app.getSession(), "SELECT ADVNCE_BOOK_DURA FROM guestroom_master WHERE ROOMTYPE=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{RoomType  } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1 !=null){
         String x = temp1.toString();
         int Advance_dura = Integer.parseInt(x);
         return Advance_dura;
         }
         else{
             return 3;
         }
      }
     
     
     
      public List Payment_notRecieve(AppView app , Date curr_date){
         
         List<Object> payment_list = new ArrayList<Object>();
         
         try {
            payment_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_booked_details WHERE LAST_PAYMENT_DATE < ? AND PAYMENT_FLAG = 0 ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), SerializerReadString.INSTANCE).list(new Object[]{curr_date } );
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          return payment_list;
      }
     
     
     public Date getBooking_date(AppView app , String Bk_ID) throws ParseException{
       Date d = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         
         Object[] o = null;
          try {
            o  =  (Object[]) new StaticSentence(app.getSession(), "SELECT BOOKING_DATE_EX FROM guestroom_booked_details WHERE ID=? ",  SerializerWriteString.INSTANCE , new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(Bk_ID);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
       d = sdf.parse(o[0].toString());
        return d; 
     } 
      
     
      // GET STATUS OF CHECK IN FOR ROOM BOOKED 
       public int getRoomCheckInStatus(AppView app , String Booking_id){
         Object o = null;
         int advance_amt = 0;
         try {
            o  = new StaticSentence(app.getSession(), "SELECT CHK_FLAG FROM guestroom_advance_payment WHERE BOOKING_ID= ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_id);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         if(o!=null){
           advance_amt = Integer.parseInt(o.toString());
           return advance_amt;
         }
         else{
            return advance_amt;
         }
       }
     
       
       // GET ADVANCE AMOUNT OF GUEST ROOM BOOKED   
       public Double getAdvance_Paid(AppView app , String Booking_id){
         Object o = null;
         Double advance_amt = 0.00;
         try {
            o  = new StaticSentence(app.getSession(), "SELECT ADVANCE_RECV FROM guestroom_advance_payment WHERE  CHK_FLAG=0 AND BOOKING_ID= ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_id);
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
           advance_amt = Double.parseDouble(o.toString());
           return advance_amt;
       }
        else{
            return advance_amt;
        }
       }
      
       
       // GET LIST OF DAYS FROM GUEST ROOM CANCELLATION OFFERS
        public List getRoomCancellationOffer (AppView app , String d){
         
                List<Object> Days_list = new ArrayList<Object>();

                try {
                    Days_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT DAYS FROM guestroom_cancel_offrs WHERE ROOMTYPE=? AND ACTIVE=1 ORDER BY DAYS  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(d);


               } catch (BasicException ex) {
                   Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
             return Days_list;
        } 
       
     
        // GET RATE N FIXED CHARGES FOR GUEST ROOM  CANCELLATION
        
         public Object[] getCan_ChargesFixed_Rates(AppView app , String Booking_id , int Days){
         Object[] o = null;
        
         try {
            o  = (Object[]) new StaticSentence(app.getSession(), "SELECT PERC_RATE , FIX_CHARGE FROM guestroom_cancel_offrs  WHERE ACTIVE=1 AND ROOMTYPE=? AND DAYS=? ORDER BY DAYS  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT}),  new SerializerReadBasic(new Datas[]{Datas.OBJECT, Datas.OBJECT})).find(new Object[]{Booking_id , Days });
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         
          return o;
       } 
        
        // GET TOTAL AMOUNT FOR HALL BOOKED BY MEMBER 
        public Double getTotalAmount(AppView app , String Booking_id){
         Object o = null;
         Double TotalAmt = 0.00;
         try {
            o  = new StaticSentence(app.getSession(), "SELECT TOTAL_AMOUNT FROM guestroom_advance_payment WHERE BOOKING_ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_id);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
         }
         if(o!=null){
             TotalAmt = Double.parseDouble(o.toString());
             return TotalAmt;
         }
         else{
            return TotalAmt;
         }
       } 
         
     
      public int get_Rooms_Booked(AppView app , String Bk_ID ){
      int Rooms_Booked;
       Object obj = null;
          try {
            obj  =   new StaticSentence(app.getSession(), "SELECT ROOM_NOS FROM guestroom_booked_details WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadInteger.INSTANCE).find(Bk_ID);
         } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        String x = obj.toString();
        Rooms_Booked = Integer.parseInt(x);
         
        return Rooms_Booked; 
     } 
      
     public int get_No_Of_Days(AppView app , String Bk_ID ){
      int No_Of_Days;
       Object obj = null;
          try {
            obj  =   new StaticSentence(app.getSession(), "SELECT BOOKING_DAYS FROM guestroom_booked_details WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadInteger.INSTANCE).find(Bk_ID);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        String x = obj.toString();
        No_Of_Days = Integer.parseInt(x);
         
        return No_Of_Days; 
     } 
     
     
      public String getRom_Type_ID(AppView app , String Bk_ID ){
       
       Object obj = null;
          try {
            obj  =   new StaticSentence(app.getSession(), "SELECT ROOM_TYPE FROM guestroom_booked_details WHERE ID=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(Bk_ID);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
        String x = obj.toString();
        return x; 
     } 
      
      
      public int getFlag(AppView app){
       int flag = 0;
        try {
            List  o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable WHERE ID='1599' ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
       
            String t = o.get(0).toString();
            flag = Integer.parseInt(t);
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(flag==0){
           flag=1;
       }
       else{
           flag=0;
       }
       return flag;
   }
     
     
      
     
    
     
      public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return status_data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class ,  java.lang.Integer.class , java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
            false, false, false, false, false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              BookedRoomStatusTableModel.Room_StatusInfo r =status_data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.getROOM_TYPE();
                   case 2: return r.getNO_OF_ROOMS_BOOKED();
                   case 3: return (r.getMem_No()+ " - "+r.getMemberName());
                   case 4: return Formats.TIMESTAMP.formatValue(r.getBOOKED_DATE_EX());
                   case 5: if(r.getStatus()==1){
                                
                                return "Available";
                            }
                            else if(r.getStatus()==2){
                                       return  "Fully Booked";
                                     }
                            else if(r.getStatus()==3){
                            
                                       return "Blocked";
                            }
                   case 6: return r.getNO_OF_DAYS();
                   case 7: if(r.getFlag()==0){
                       return " Pending ";
                   }
                   else if(r.getFlag()==1){
                       
                       if(r.getBDateChangeFlag()==2){
                       
                       return "Requested to change date";
                        }
                        else{
                            return "Approved"; 
                        }
                          
                       }
                   else{
                       if(r.getFlag()==2){
                           return "Rejected";
                       }
                       
                   }
                   
               }
                return null;
            }
          
          
        };
    } 
    
    
      //For Table 2
      
    public  AbstractTableModel getTableModel2()
        {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Rejected_rooms.size();
            }
          public int getColumnCount() {
                return REJECTED_ENTRIES.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return REJECTED_ENTRIES[column];
            }

          Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class ,  java.lang.Integer.class , java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
            false, false, false, false, false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              BookedRoomStatusTableModel.Rejected_room_info r =Rejected_rooms.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getROOM_TYPE();
                   case 1: return r.getNO_OF_ROOMS_BOOKED();
                   case 2: return r.getMemberName();
                   case 3: return Formats.TIMESTAMP.formatValue(r.getBOOKED_DATE_EX());
                   case 4: if(r.getStatus()==1){
                                
                                return "Available";
                            }
                            else if(r.getStatus()==2){
                                       return  "Fully Booked";
                                     }
                            else if(r.getStatus()==3){
                                       return "Blocked";
                            }
                   case 5: return r.getNO_OF_DAYS();
                   case 6: if(r.getFlag()==1){
                                return "Pending" ;
                            }
                            else if(r.getFlag()==2){
                                         return "Rejected";
                                     }
                            else{
                                return "Approved";
                            }
                   
               }
                return null;
            }
          
          
        };
    } 
      
      public int getRejectedRoomSize()
    {
        return rejected_room_length;
    }    
      
      
    public int getRoomSize()
    {
        return booked_room_Length;
    }  
    
    
     public List<BookedRoomStatusTableModel.Room_StatusInfo> getGuestRoomList(){
           if(status_data!=null)
        {
            return status_data;
        }
        else
            return new ArrayList<BookedRoomStatusTableModel.Room_StatusInfo>();
      }
    
     
    
     public List<BookedRoomStatusTableModel.Rejected_room_info> getRejectedGuestRoomList(){
           if(Rejected_rooms!=null)
        {
            return Rejected_rooms;
        }
        else
            return new ArrayList<BookedRoomStatusTableModel.Rejected_room_info>();
      }
    
    
    
    
    
    
   
    
    
      public static class Room_StatusInfo implements SerializableRead,IKeyed {
          
    private String ID;      
    private String ROOM_TYPE;
    private String BOOKING_DATE;
    private String MEMBER_NAME;
    private String MEMBER_NO;
    private String NON_MEM_NAME;
    private String NON_MEM_CONTACT;
    private String NON_MEM_ADDR;
    private int MEMBER_FLAG = 0;
    private int NON_MEM_FLAG = 0;
    private int STATUS;
    private int NO_OF_ROOMS_BOOKED;
    private int NO_OF_DAYS;
    private int BASIC1;
    private int CASCADE1;
    private int BASIC2;
    private int CASCADE2;
    private String LUXURYTAX;
    private String TAX2;
    private String TAX3;
    
    private Double CHARGES;
    private int FLAG;
    private String MEMBER_ID;
    private Date BOOKED_DATE_EX;
    private int MAX_CAPACITY;
    private String Room_type_ID;
    private Date LAST_PAYMENT_DATE;
    private int PAYMENT_FLAG;
    private String ROLE;
    private String ADVANCE_PERC;
    private String BOOKING_SEQ_NO;
    private int CHK_IN_FLAG;
    private String CustAddress;
    private Double Tax1_Rate;
    private Double Tax2_Rate;
    private Double Tax3_Rate;
    private int BDateChangeFlag;
    private String OldReference;
    private int CDateChangeFlag;
    
    
           public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          
           public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
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
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String getROOM_TYPE(){
              return ROOM_TYPE;
          }
          public void setROOM_TYPE(String ROOM_TYPE){
              this.ROOM_TYPE=ROOM_TYPE;
          }
          
         
          
           public int getMem_flag(){
              return MEMBER_FLAG;
          }
          public void setMem_flag(int MEMBER_FLAG){
              this.MEMBER_FLAG=MEMBER_FLAG;
          }
          
          public int getNon_Mem_flag(){
              return NON_MEM_FLAG;
          }
          public void setNon_Mem_flag(int NON_MEM_FLAG){
              this.NON_MEM_FLAG=NON_MEM_FLAG;
          }
          
           public String getBOOKING_DATE(){
              return BOOKING_DATE;
          }
          public void setBOOKING_DATE(String BOOKING_DATE){
              this.BOOKING_DATE=BOOKING_DATE;
          }
          
           
          
           public Double getCHARGES(){
              return CHARGES;
          }
          public void setCHARGES(Double CHARGES){
              this.CHARGES=CHARGES;
          }
          
           public String getLUXURYTAX(){
              return LUXURYTAX;
             
          }
          public void setLUXURYTAX(String LUXURYTAX){
              this.LUXURYTAX=LUXURYTAX;
          }
          
           public String getTAX2(){
              return TAX2;
          }
          public void setTAX2(String TAX2){
              this.TAX2=TAX2;
          }
          public String getTAX3(){
              return TAX3;
          }
          public void setTAX3(String TAX3){
              this.TAX3=TAX3;
          }
           public int getBASIC1(){
              return BASIC1;
          }
          public void setBASIC1(int BASIC1){
              this.BASIC1=BASIC1;
          }
          public int getCASCADE1(){
              return CASCADE1;
          }
          public void setCASCADE1(int CASCADE1){
              this.CASCADE1=CASCADE1;
          }
          
           public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2=BASIC2;
          }
          public int getCASCADE2(){
              return CASCADE2;
          }
          public void setCASCADE2(int CASCADE2){
              this.CASCADE2=CASCADE2;
          }
          
          
          public String getNON_MEM_NAME(){
              return NON_MEM_NAME;
          }
          public void setNON_MEM_NAME(String NON_MEM_NAME){
              this.NON_MEM_NAME=NON_MEM_NAME;
          }
           public String getNON_MEM_CONTCT(){
              return NON_MEM_CONTACT;
          }
          public void setNON_MEM_CONTCT(String NON_MEM_CONTCT){
              this.NON_MEM_CONTACT=NON_MEM_CONTCT;
          }
          
          public String getNON_MEM_ADDR(){
              return NON_MEM_ADDR;
          }
          public void setNON_MEM_ADDR(String NON_MEM_ADDR){
              this.NON_MEM_ADDR=NON_MEM_ADDR;
          }
        
        
          
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
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
          
           public Date getBOOKED_DATE_EX(){
              return BOOKED_DATE_EX;
          }
          public void setBOOKED_DATE_EX(Date BOOKED_DATE_EX){
              this.BOOKED_DATE_EX=BOOKED_DATE_EX;
          }
          
          public String getROOMTYPE_ID(){
              return Room_type_ID;
          }
          public void setROOMTYPE_ID(String RoomType_id){
              this.Room_type_ID = RoomType_id;
          }
          
           public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          
           public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
          }
          
            public Date getLAST_PAYMENT_DATE(){
              return LAST_PAYMENT_DATE;
          }
          public void setLAST_PAYMENT_DATE(Date LAST_PAYMENT_DATE){
              this.LAST_PAYMENT_DATE=LAST_PAYMENT_DATE;
          }
          
           public int getPAYMENT_FLAG(){
              return PAYMENT_FLAG;
          }
          public void setPAYMENT_FLAG(int PAYMENT_FLAG){
              this.PAYMENT_FLAG=PAYMENT_FLAG;
          }
          
          public String getROLE(){
              return ROLE;
          }
          public void setROLE(String ROLE){
              this.ROLE = ROLE;
          }
          
          
           public String getADVANCE_PERC(){
             return ADVANCE_PERC;
            } 
         public void setADVANCE_PERC(String ADVANCE_PERC){
             this.ADVANCE_PERC = ADVANCE_PERC;
         }
         
          public int getCHK_IN_FLAG(){
             return CHK_IN_FLAG;
            } 
         public void setCHK_IN_FLAG(int CHK_IN_FLAG){
             this.CHK_IN_FLAG = CHK_IN_FLAG;
         }
          public String getCustAddress(){
              return CustAddress;
          }
          public void setCustAddress(String CustAddress){
              this.CustAddress = CustAddress;
          }
          public Double getTax1_Rate(){
              return Tax1_Rate;
          }
          public void setTax1_Rate(Double Tax1_Rate){
              this.Tax1_Rate = Tax1_Rate;
          }
           public Double getTax2_Rate(){
              return Tax2_Rate;
          }
          public void setTax2_Rate(Double Tax2_Rate){
              this.Tax2_Rate = Tax2_Rate;
          }
           public Double getTax3_Rate(){
              return Tax3_Rate;
          }
          public void setTax3_Rate(Double Tax3_Rate){
              this.Tax3_Rate = Tax3_Rate;
          }
         public void setBDateChangeFlag(int BDateChangeFlag){
              this.BDateChangeFlag=BDateChangeFlag;
          }
          public int getBDateChangeFlag(){
              return BDateChangeFlag;
          }
          public void setOldReference(String OldReference){
              this.OldReference=OldReference;
          }
          public String getOldReference(){
              return OldReference;
          }
         //added by pratima
         public void setCDateChangeFlag(int CDateChangeFlag){
              this.CDateChangeFlag=CDateChangeFlag;
          }
          public int getCDateChangeFlag(){
              return CDateChangeFlag;
          }//ended by pratima
         
        public void readValues(DataRead dr) throws BasicException {
           
          ID = dr.getString(1);
          ROOM_TYPE = dr.getString(2);
          BOOKING_DATE = dr.getString(3);
          MEMBER_NAME = dr.getString(4);
          MEMBER_FLAG = dr.getInt(5);
          NON_MEM_FLAG  = dr.getInt(6);
          STATUS = dr.getInt(7);
          FLAG = dr.getInt(8);
          NO_OF_ROOMS_BOOKED = dr.getInt(9);
          BOOKED_DATE_EX = dr.getTimestamp(10);
          NO_OF_DAYS = dr.getInt(11);
          MEMBER_NO = dr.getString(12);
          NON_MEM_NAME = dr.getString(13);
          NON_MEM_CONTACT = dr.getString(14);
          NON_MEM_ADDR = dr.getString(15);
          CHARGES = dr.getDouble(16);
          LUXURYTAX = dr.getString(17);
          TAX2 = dr.getString(18);
          TAX3 = dr.getString(19);
          BASIC1 = dr.getInt(20);
          CASCADE1 = dr.getInt(21);
          Room_type_ID = dr.getString(22);
          MEMBER_ID = dr.getString(23);
          MAX_CAPACITY = dr.getInt(24);
          BASIC2 = dr.getInt(25);
          CASCADE2 = dr.getInt(26);
          LAST_PAYMENT_DATE = dr.getTimestamp(27);
          PAYMENT_FLAG = dr.getInt(28);
          ROLE = dr.getString(29);
          ADVANCE_PERC = dr.getString(30);
          BOOKING_SEQ_NO = dr.getString(31);
          CHK_IN_FLAG = dr.getInt(32);
          CustAddress = dr.getString(33);
          Tax1_Rate = dr.getDouble(34);
          Tax2_Rate = dr.getDouble(35);
          Tax3_Rate = dr.getDouble(36);
          BDateChangeFlag=dr.getInt(38);
          OldReference=dr.getString(37);
          CDateChangeFlag=dr.getInt(39);//added by pratima
        }

        public Object getKey() {
           return this;
        }
          
            
      }
    
    
      
      
      
      
      
  public static class Rejected_room_info implements SerializableRead,IKeyed {
          
    private String ID;      
    private String ROOM_TYPE;
    private String BOOKING_DATE;
    private String MEMBER_NAME;
    private String MEMBER_NO;
    private String NON_MEM_NAME;
    private String NON_MEM_CONTACT;
    private String NON_MEM_ADDR;
    private int MEMBER_FLAG = 0;
    private int NON_MEM_FLAG = 0;
    private int STATUS;
    private int NO_OF_ROOMS_BOOKED;
    private int NO_OF_DAYS;
    private int BASIC1;
    private int CASCADE1;
    private int BASIC2;
    private int CASCADE2;
    private String LUXURYTAX;
    private String TAX2;
    private String TAX3;
    
    private Double CHARGES;
    private int FLAG;
    private String MEMBER_ID;
    private Date BOOKED_DATE_EX;
    private int MAX_CAPACITY;
    private String Room_type_ID;
    private Date LAST_PAYMENT_DATE;
    private int PAYMENT_FLAG;
    private String CANCEL_REASON;
    private String REQ_APPROV_BY;
    private Date REQ_APPROV_DATE;
    private String ROLE;
    private Double Tax1_Rate;
    private Double Tax2_Rate;
    private Double Tax3_Rate;
    
    
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
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String getROOM_TYPE(){
              return ROOM_TYPE;
          }
          public void setROOM_TYPE(String ROOM_TYPE){
              this.ROOM_TYPE=ROOM_TYPE;
          }
          
         
          
           public int getMem_flag(){
              return MEMBER_FLAG;
          }
          public void setMem_flag(int MEMBER_FLAG){
              this.MEMBER_FLAG=MEMBER_FLAG;
          }
          
          public int getNon_Mem_flag(){
              return NON_MEM_FLAG;
          }
          public void setNon_Mem_flag(int NON_MEM_FLAG){
              this.NON_MEM_FLAG=NON_MEM_FLAG;
          }
          
           public String getBOOKING_DATE(){
              return BOOKING_DATE;
          }
          public void setBOOKING_DATE(String BOOKING_DATE){
              this.BOOKING_DATE=BOOKING_DATE;
          }
          
           
          
           public Double getCHARGES(){
              return CHARGES;
          }
          public void setCHARGES(Double CHARGES){
              this.CHARGES=CHARGES;
          }
          
           public String getLUXURYTAX(){
              return LUXURYTAX;
          }
          public void setLUXURYTAX(String LUXURYTAX){
              this.LUXURYTAX=LUXURYTAX;
          }
          
           public String getTAX2(){
              return TAX2;
          }
          public void setTAX2(String TAX2){
              this.TAX2=TAX2;
          }
          public String getTAX3(){
              return TAX3;
          }
          public void setTAX3(String TAX3){
              this.TAX3=TAX3;
          }
           public int getBASIC1(){
              return BASIC1;
          }
          public void setBASIC1(int BASIC1){
              this.BASIC1=BASIC1;
          }
          public int getCASCADE1(){
              return CASCADE1;
          }
          public void setCASCADE1(int CASCADE1){
              this.CASCADE1=CASCADE1;
          }
          
           public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2=BASIC2;
          }
          public int getCASCADE2(){
              return CASCADE2;
          }
          public void setCASCADE2(int CASCADE2){
              this.CASCADE2=CASCADE2;
          }
          
          
          public String getNON_MEM_NAME(){
              return NON_MEM_NAME;
          }
          public void setNON_MEM_NAME(String NON_MEM_NAME){
              this.NON_MEM_NAME=NON_MEM_NAME;
          }
           public String getNON_MEM_CONTCT(){
              return NON_MEM_CONTACT;
          }
          public void setNON_MEM_CONTCT(String NON_MEM_CONTCT){
              this.NON_MEM_CONTACT=NON_MEM_CONTCT;
          }
          
          public String getNON_MEM_ADDR(){
              return NON_MEM_ADDR;
          }
          public void setNON_MEM_ADDR(String NON_MEM_ADDR){
              this.NON_MEM_ADDR=NON_MEM_ADDR;
          }
        
        
          
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
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
          
           public Date getBOOKED_DATE_EX(){
              return BOOKED_DATE_EX;
          }
          public void setBOOKED_DATE_EX(Date BOOKED_DATE_EX){
              this.BOOKED_DATE_EX=BOOKED_DATE_EX;
          }
          
          public String getROOMTYPE_ID(){
              return Room_type_ID;
          }
          public void setROOMTYPE_ID(String RoomType_id){
              this.Room_type_ID = RoomType_id;
          }
          
           public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          
           public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
          }
          
            public Date getLAST_PAYMENT_DATE(){
              return LAST_PAYMENT_DATE;
          }
          public void setLAST_PAYMENT_DATE(Date LAST_PAYMENT_DATE){
              this.LAST_PAYMENT_DATE=LAST_PAYMENT_DATE;
          }
          
           public int getPAYMENT_FLAG(){
              return PAYMENT_FLAG;
          }
          public void setPAYMENT_FLAG(int PAYMENT_FLAG){
              this.PAYMENT_FLAG=PAYMENT_FLAG;
          }
          
          public String  getCANCEL_REASON(){
              return CANCEL_REASON;
          }
          
          public void setCANCEL_REASON(String CANCEL_REASON){
              this.CANCEL_REASON = CANCEL_REASON;
          }
          public String getREQ_APPROV_BY(){
              return REQ_APPROV_BY;
          }
          public void setREQ_APPROV_BY(String REQ_APPROV_BY){
              this.REQ_APPROV_BY = REQ_APPROV_BY;
          }
          
          public Date getREQ_APPROV_DATE(){
              return REQ_APPROV_DATE;
          }
          public void setREQ_APPROV_DATE(Date REQ_APPROV_DATE){
              this.REQ_APPROV_DATE = REQ_APPROV_DATE;
          }
          
            public String getROLE(){
              return ROLE;
          }
          public void setROLE(String ROLE){
              this.ROLE = ROLE;
          }
             public Double getTax1_Rate(){
              return Tax1_Rate;
          }
          public void setTax1_Rate(Double Tax1_Rate){
              this.Tax1_Rate = Tax1_Rate;
          }
           public Double getTax2_Rate(){
              return Tax2_Rate;
          }
          public void setTax2_Rate(Double Tax2_Rate){
              this.Tax2_Rate = Tax2_Rate;
          }
           public Double getTax3_Rate(){
              return Tax3_Rate;
          }
          public void setTax3_Rate(Double Tax3_Rate){
              this.Tax3_Rate = Tax3_Rate;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          ID = dr.getString(1);
          ROOM_TYPE = dr.getString(2);
          BOOKING_DATE = dr.getString(3);
          MEMBER_NAME = dr.getString(4);
          MEMBER_FLAG = dr.getInt(5);
          NON_MEM_FLAG  = dr.getInt(6);
          STATUS = dr.getInt(7);
          FLAG = dr.getInt(8);
          NO_OF_ROOMS_BOOKED = dr.getInt(9);
          BOOKED_DATE_EX = dr.getTimestamp(10);
          NO_OF_DAYS = dr.getInt(11);
          MEMBER_NO = dr.getString(12);
          NON_MEM_NAME = dr.getString(13);
          NON_MEM_CONTACT = dr.getString(14);
          NON_MEM_ADDR = dr.getString(15);
          CHARGES = dr.getDouble(16);
          LUXURYTAX = dr.getString(17);
          TAX2 = dr.getString(18);
          TAX3 = dr.getString(19);
          BASIC1 = dr.getInt(20);
          CASCADE1 = dr.getInt(21);
          Room_type_ID = dr.getString(22);
          MEMBER_ID = dr.getString(23);
          MAX_CAPACITY = dr.getInt(24);
          BASIC2 = dr.getInt(25);
          CASCADE2 = dr.getInt(26);
          LAST_PAYMENT_DATE = dr.getTimestamp(27);
          PAYMENT_FLAG = dr.getInt(28);
          CANCEL_REASON = dr.getString(29);
          REQ_APPROV_BY = dr.getString(30);
          REQ_APPROV_DATE = dr.getTimestamp(31);
          ROLE = dr.getString(32);
          Tax1_Rate = dr.getDouble(33);
          Tax2_Rate = dr.getDouble(34);
          Tax3_Rate = dr.getDouble(35);
          
        }

        public Object getKey() {
           return this;
        }
          
            
      }
      
}
