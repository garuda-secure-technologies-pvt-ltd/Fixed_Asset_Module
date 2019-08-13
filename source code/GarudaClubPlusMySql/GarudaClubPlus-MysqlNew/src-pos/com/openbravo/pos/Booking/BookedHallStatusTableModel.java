
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
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataSourceforTrailBalance;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class BookedHallStatusTableModel  extends BeanFactoryDataSingle{
    protected Session s;
    private final static String[] TABLEHEADERS = {"Booking No.","Hall Name","Floor","Max Capacity ", "Booking Status", "Booked  By", "Booked  Date" , "Slot Booked" , "Request Status"};
    private final static String[] CANCELREQUEST = {"Booking No.","Hall Name","Floor","Max Capacity ", "Booking Status", "Booked  By", "Booked  Date" , "Slot Booked" , "Request Status"};
    private List<BookedHallStatusTableModel.HallStatusInfo> status_data;
    private List<BookedHallStatusTableModel.Cancel_requestInfo> Cancel_request_data;
    private int bookedHallLength;
     private int CancelHallLength;
    
     public BookedHallStatusTableModel() {
    }
     
     public BookedHallStatusTableModel(List<BookedHallStatusTableModel.HallStatusInfo> data1) {
        this.status_data = data1;
    } 
     
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static BookedHallStatusTableModel loadInstanceBooked_Hall_Status(AppView app , Date currDate)throws BasicException{
         BookedHallStatusTableModel Booked_Hall_Status = new BookedHallStatusTableModel(); 
         
          try{
            Booked_Hall_Status.status_data = new ArrayList<BookedHallStatusTableModel.HallStatusInfo>();
            Booked_Hall_Status.status_data = new StaticSentence(app.getSession(), "SELECT  ID, (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = MEMBERNAME) , MEM_NO, STATUS,\n" +
                                                                                        "(SELECT h.NAME FROM hall_master h WHERE HALL_NAME = h.ID) , FLOOR, MEMBER, NON_MEMBER, BOOKING_DATE,\n" +
                                                                                        " BOOKING_SLOT, CHARGES, LUXURYTAX, TAX2, TAX3, BASIC, CASCADE1, NON_MEM_NAME, NON_MEM_CONTCT, NON_MEM_ADDR  ,\n" +
                                                                                        " MAXCAPACITY , FLAG , HALL_NAME , BASIC2 , CASCADE2 , (SELECT h.PAYMENT_DAYS FROM hall_master h WHERE HALL_NAME = h.ID ) ,\n" +
                                                                                        "BOOKING_DATE_EX , CRDATE , LAST_PAYMENT_DATE , PAYMENT_FLAG , MEMBERNAME , SLOT_FLAG , ROLE ,\n" +
                                                                                        " (SELECT h.ADVANCE_PERC FROM hall_master h WHERE HALL_NAME = h.ID) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = LUXURYTAX),\n" +
                                                                                        "(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = TAX2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = TAX3) , BOOKING_SEQ_NO , CHK_IN_FLAG , (SELECT CONCAT(C.ADDRESS , '\\n' , C.ADDRESS2 , '\\n' , C.CITY  ) FROM CUSTOMERS c WHERE c.ID = MEMBERNAME)  , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = LUXURYTAX) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = TAX2) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = TAX3) \n" +
                                                                                        "from hall_booked_details\n" +
                                                                                        "Where  BOOKING_DATE_EX >= ? and  STATUS NOT IN (1) AND CHK_IN_FLAG!=2 ORDER BY BOOKING_SEQ_NO ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}) ,new SerializerReadClass(BookedHallStatusTableModel.HallStatusInfo.class)).list(new Object[]{currDate});

            Booked_Hall_Status.bookedHallLength = Booked_Hall_Status.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Hall_Status;
         
     }
     
     
     // load canceled requests...
     
       public static BookedHallStatusTableModel load_CancelRequest(AppView app )throws BasicException{
         BookedHallStatusTableModel Cancel_Requests = new BookedHallStatusTableModel(); 
         
          try{
            Cancel_Requests.Cancel_request_data = new ArrayList<BookedHallStatusTableModel.Cancel_requestInfo>();
            Cancel_Requests.Cancel_request_data = new StaticSentence(app.getSession(), "SELECT  ID, (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = MEMBERNAME) , MEM_NO, STATUS, (SELECT h.NAME FROM hall_master h WHERE HALL_NAME = h.ID) , FLOOR, MEMBER, NON_MEMBER, BOOKING_DATE, BOOKING_SLOT, CHARGES, (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=LUXURYTAX),(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=TAX3), BASIC, CASCADE1, NON_MEM_NAME, NON_MEM_CONTCT, NON_MEM_ADDR  , MAXCAPACITY , FLAG , HALL_NAME , BASIC2 , CASCADE2 , (SELECT h.PAYMENT_DAYS FROM hall_master h WHERE HALL_NAME = h.ID ) , BOOKING_DATE_EX , CRDATE , LAST_PAYMENT_DATE , PAYMENT_FLAG  , MEMBERNAME , SLOT_FLAG , ROLE , BOOKING_SEQ_NO , REQ_CANCEL_BY , REQ_CANCEL_HOST , REQ_CANCEL_DATE , CANCEL_REASON , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = LUXURYTAX) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = TAX2) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY = TAX3) from hall_cancelled_details Where FLAG=2 OR PAYMENT_FLAG=2 order by BOOKING_DATE  ", SerializerWriteString.INSTANCE, new SerializerReadClass(BookedHallStatusTableModel.Cancel_requestInfo.class)).list();
           
            Cancel_Requests.CancelHallLength = Cancel_Requests.Cancel_request_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Cancel_Requests;
         
     }
     
     
     
     
     public List CheckAvailibility(String date , AppView app){
        
        List<Object> temp = new ArrayList<Object>();
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "select (select b.NAME from hall_master b where HALL_NAME = b.ID) from hall_booked_details where BOOKING_DATE  = ? AND STATUS NOT IN (1) ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(date);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
        return temp; 
     }
     
     
     
     public int getTiming_Slot_Flag(String date ,String HallName ,  AppView app){
       int Slot_Flag; 
       Object obj = new Object();
         try {
            obj  =  new StaticSentence(app.getSession(), "SELECT SLOT_FLAG FROM hall_booked_details WHERE  HALL_NAME = (SELECT h.ID FROM hall_master h WHERE h.NAME= ?)  AND BOOKING_DATE = ? ",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{HallName , date } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(obj!=null){
            String temp = obj.toString();
            Slot_Flag = Integer.parseInt(temp);
            return Slot_Flag;
        }
        else{
           return 0; 
        }
         
         
     }
     
     
      public int getHall_Booked_Status(String date ,String HallName , String Slot_Booked ,  AppView app){
       int Slot_Flag; 
       Object obj = new Object();
         try {
            obj  =  new StaticSentence(app.getSession(), "SELECT STATUS FROM hall_booked_details WHERE  HALL_NAME = (SELECT h.ID FROM hall_master h WHERE h.NAME= ?)  AND BOOKING_DATE = ? AND BOOKING_SLOT=? ",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING , Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{HallName , date , Slot_Booked } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(obj!=null){
            String temp = obj.toString();
            Slot_Flag = Integer.parseInt(temp);
            return Slot_Flag;
        }
        else{
           return 0; 
        }
         
         
     }
     
     
     
     public List getBooking_Timings(String date ,String HallName ,  AppView app){
        
        List<Object> temp = new ArrayList<Object>();
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "SELECT BOOKING_SLOT FROM hall_booked_details WHERE HALL_NAME = (SELECT h.ID FROM hall_master h WHERE h.NAME= ?)  AND BOOKING_DATE = ? AND SLOT_FLAG = 1 AND STATUS NOT IN (1) AND CHK_IN_FLAG!=2",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{HallName , date } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
     }
     
     
     
      public int getPending_ReqList(AppView app){
        
        List<Object> temp = new ArrayList<Object>();
        int list_size=0;
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM hall_booked_details WHERE FLAG=0;",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
           
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
     
     
     
      
      public List getHalfDay_Booking_Timings(String date ,String HallName ,  AppView app){
        
        List<Object> temp = new ArrayList<Object>();
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "SELECT BOOKING_SLOT FROM hall_booked_details WHERE HALL_NAME = (SELECT h.ID FROM hall_master h WHERE h.NAME= ?)  AND BOOKING_DATE = ? AND SLOT_FLAG = 2 AND STATUS NOT IN (1)  AND CHK_IN_FLAG!=2",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{HallName , date } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
        return temp; 
     }
     
     
     //get member hourly  Timing Slot From hall_timing_slots Table ... 
     public List getMEM_Hourly_timing_slots( String HallName ,  AppView app){
        
        List<String> temp = new ArrayList<String>();
        Object o = null;
        
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT h.M_HOURLY_SLOTS FROM hall_master h WHERE   h.NAME= ?   ",new SerializerWriteBasic(new Datas[]{Datas.STRING }), SerializerReadString.INSTANCE).find(new Object[]{HallName  } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){  
            String x = o.toString();
            String[] items1 = x.split(";");
            Collection<String> collection1 = new ArrayList<String>();
            for(int i=0;i<items1.length;i++){
                      collection1.add(items1[i]);
                  }
           temp.addAll(collection1);
        
        }
         
        return temp; 
     }
     
     
     
     public int check_Status(String date , String HallName ,AppView app ){
         int checked_status = 0;
         Object temp1 = new Object();
         try {
            temp1  =  new StaticSentence(app.getSession(), "select status from hall_booked_details where (SELECT b.ID FROM hall_master b WHERE b.NAME = ? ) = HALL_NAME and BOOKING_DATE = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{HallName , date } );
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       checked_status = Integer.parseInt(temp1.toString());
          return checked_status;
     }
     
     // int get slot booking state --------------------------------------------------------------------------------------------------------------
      public int getSlotBookingState(String date , String HallName ,AppView app ){
         int checked_status = 0;
         Object temp1 = new Object();
         try {
            temp1  =  new StaticSentence(app.getSession(), "select slot_flag from hall_booked_details where (SELECT b.ID FROM hall_master b WHERE b.NAME = ? ) = HALL_NAME and BOOKING_DATE = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{HallName , date } );
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       checked_status = Integer.parseInt(temp1.toString());
          return checked_status;
     }
     
     
      
     public List BookedDates(AppView app){
         
         List<Object> bookDT = new ArrayList<Object>();
         
         try {
            bookDT  = (List<Object>) new StaticSentence(app.getSession(), "select BOOKING_DATE  from hall_booked_details", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         return bookDT;
     }
     
      public List hallNamesList (AppView app){
         
         List<Object> hallList = new ArrayList<Object>();
         
         try {
            hallList  = (List<Object>) new StaticSentence(app.getSession(), "SELECT DISTINCT NAME FROM hall_master WHERE ACTIVE=true", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         return hallList;
     }
      
       public List getBlocked_Hall_list(AppView app){
         
         List<Object> hallList = new ArrayList<Object>();
         
         try {
            hallList  = (List<Object>) new StaticSentence(app.getSession(), "SELECT  NAME FROM hall_master WHERE BLOCK_FLAG=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         return hallList;
     }
      
       
       
       public String getCancellation_Reason(AppView app , String ID){
         
          Object note = null;
         
         try {
            note  = new StaticSentence(app.getSession(), "SELECT  CANCEL_REASON FROM hall_booked_details WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(ID);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(note!=null){ 
         String x = note.toString();
         return x;
        }
        else{
            return null;
        }
        
     }
       
      // GET ADVANCE AMOUNT OF HALL BOOKED   
       public Double getAdvance_Paid(AppView app , String Booking_id){
         Object o = null;
         Double advance_amt = 0.00;
         try {
            o  = new StaticSentence(app.getSession(), "SELECT ADVANCE_RECV FROM hall_advance_payment WHERE  CHK_IN_FLAG=0 AND BOOKING_ID= ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_id);
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
       
     // GET STATUS OF CHECK IN FOR HALL BOOKED 
       
        public int getHallCheckInStatus(AppView app , String Booking_id){
         Object o = null;
         int advance_amt = 0;
         try {
            o  = new StaticSentence(app.getSession(), "SELECT CHK_IN_FLAG FROM hall_advance_payment WHERE   BOOKING_ID= ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_id);
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
       
       // GET LIST OF DAYS FROM HALL CANCELLATION OFFERS
        public List getHallCancellationOffer (AppView app , String d){
         
                List<Object> Days_list = new ArrayList<Object>();

                try {
                   Days_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT DAYS FROM hall_cancellation_offeres WHERE HALL_NAME=? AND ACTIVE=1 ORDER BY DAYS  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(d);


               } catch (BasicException ex) {
                   Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
             return Days_list;
        }
         
        
       // GET RATE N FIXED CHARGES FOR HALL CANCELLATION
        
         public Object[] getCan_ChargesFixed_Rates(AppView app , String Booking_id , int Days){
         Object[] o = null;
        
         try {
            o  = (Object[]) new StaticSentence(app.getSession(), "SELECT PERC_RATE , FIX_CHARGE FROM hall_cancellation_offeres h WHERE ACTIVE=1 AND HALL_NAME=? AND DAYS=? ORDER BY DAYS  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT}),  new SerializerReadBasic(new Datas[]{Datas.OBJECT, Datas.OBJECT})).find(new Object[]{Booking_id , Days });
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
            o  = new StaticSentence(app.getSession(), "SELECT TOTAL_AMOUNT FROM hall_advance_payment WHERE BOOKING_ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Booking_id);
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
          
        
          
       
       public List Payment_notRecieve(AppView app , Date curr_date){
         
         List<Object> payment_list = new ArrayList<Object>();
         
         try {
            payment_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM hall_booked_details WHERE LAST_PAYMENT_DATE < ? AND PAYMENT_FLAG = 0 ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), SerializerReadString.INSTANCE).list(new Object[]{curr_date } );
       
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         return payment_list;
     }
     
      
     
   public List DateList_by_HallName (AppView app , String d){
         
         List<Object> hallList = new ArrayList<Object>();
         
         try {
            hallList  = (List<Object>) new StaticSentence(app.getSession(), "select BOOKING_DATE  from hall_booked_details where (SELECT b.ID from hall_master b WHERE b.NAME= ? ) = hall_name  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(d);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         return hallList;
     }  
     
   public int getFlag(AppView app){
       int flag = 0;
        try {
            List  o  = new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable WHERE ID='1598' ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
       
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
   
   
     
     
     // For Loading Cancel Request Table.....
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
             java.lang.String.class ,   java.lang.String.class , java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class ,  java.lang.String.class ,  java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
               false , false, false, false, false, false, false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              BookedHallStatusTableModel.HallStatusInfo r =status_data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.gethall_name();
                   case 2: return r.getFloor();
                   case 3: return r.getMAX_CAPACITY();
                   case 4: if(r.getStatus()==1){
                                
                                return "Available";
                            }
                   else if(r.getStatus()==2){
                              return  "Fully Booked";
                            }
                   else if(r.getStatus()==3){
                              return "Blocked";
                   }
                   else{
                              return "Partially Booked";
                   }
                   case 5: return (r.getMem_No() + " - " +r.getMemberName());
                   case 6: return r.getBOOKING_DATE();
                   case 7:return r.getTIMING_SLOTS();
                   case 8: if(r.getFlag()==0){
                       return "Pending";
                   }
                   else if(r.getFlag()==1){
                       
                           return "Approved";
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
    
     
     public  AbstractTableModel getCancelRequestTable()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Cancel_request_data.size();
            }
          public int getColumnCount() {
                return CANCELREQUEST.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return CANCELREQUEST[column];
            }

          Class[] types = new Class[]{
              java.lang.String.class ,  java.lang.String.class , java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class ,  java.lang.String.class ,  java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
              false,  false, false, false, false, false, false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              BookedHallStatusTableModel.Cancel_requestInfo r =Cancel_request_data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.gethall_name();
                   case 2: return r.getFloor();
                   case 3: return r.getMAX_CAPACITY();
                   case 4: if(r.getStatus()==1){
                                
                                return "Available";
                            }
                   else if(r.getStatus()==2){
                              return  "Fully Booked";
                            }
                   else if(r.getStatus()==3){
                              return "Blocked";
                   }
                   else{
                              return "Partially Booked";
                   }
                   case 5: return r.getMemberName();
                   case 6: return r.getBOOKING_DATE();
                   case 7:return r.getTIMING_SLOTS();
                   case 8: if(r.getFlag()==0){
                       return " Pending ";
                   }
                   else if(r.getFlag()==1){
                       
                           return "Approved";
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
     
     
     public int getCancelRequestHallSize(){
         return CancelHallLength;
     }
     
    public int getHallSize()
    {
        return bookedHallLength;
    }
    
      
      public List<HallStatusInfo> getHallList(){
           if(status_data!=null)
        {
            return status_data;
        }
        else
            return new ArrayList<BookedHallStatusTableModel.HallStatusInfo>();
      }
      
      
      
      public List<Cancel_requestInfo> getHallCanceledList(){
           if(Cancel_request_data!=null)
        {
            return Cancel_request_data;
        }
        else
            return new ArrayList<BookedHallStatusTableModel.Cancel_requestInfo>();
      }
      
      
      public static class HallStatusInfo implements SerializableRead,IKeyed {
          
          private String ID;
          private String MEMBERNAME;
          private String MEM_NO;
          private int  STATUS ; 
          private String HALL_NAME;
          private String FLOOR ; 
          private int MEMBER_FLAG;
          private int NON_MEM_FLAG;
          private String BOOKING_DATE;
          private Date BOOKING_DATE_EX;
          private String TIMING_SLOTS;
          private Double CHARGES;
          private String LUXURYTAX_ID;
          private String TAX2_ID;
          private String TAX3_ID;
          private int BASIC;
          private int CASCADE;
          private String NON_MEM_NAME;
          private String NON_MEM_CONTCT;
          private String NON_MEM_ADDR;
          private int MAX_CAPACITY ; 
          private int FLAG;
          private String Hall_ID;
          private int BASIC2;
          private int CASCADE2;
          private int PAYMENT_DAYS;
          private Date BLOCKED_DATE;
          private Date LAST_PAYMENT_DATE;
          private int PAYMENT_FLAG;
          private String MEMBER_ID;
          private int SLOT_FLAG;
          private String ROLE;
          private CustomerInfoExt Customer;
          private String ADVANCE_PERC;
          private String LUXURYTAX_N;
          private String TAX2_N;
          private String TAX3_N;
          private String  BOOKING_SEQ_NO;
          private int CHK_IN_FLAG;
          private String CustAddress;
          private Double Tax1_Rate;
          private Double Tax2_Rate;
          private Double Tax3_Rate;
          
          
          
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
              return MEMBERNAME;
          }
          public void setMemberName(String MEMBERNAME){
              this.MEMBERNAME=MEMBERNAME;
          }
          
          public String getMem_No(){
              return MEM_NO;
          }
          public void setMem_No(String MEM_NO){
              this.MEM_NO=MEM_NO;
          }
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String gethall_name(){
              return HALL_NAME;
          }
          public void setHall_name(String HALL_NAME){
              this.HALL_NAME=HALL_NAME;
          }
          
           public String getFloor(){
              return FLOOR;
          }
          public void setFloor(String FLOOR){
              this.FLOOR=FLOOR;
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
          
           public String getTIMING_SLOTS(){
              return TIMING_SLOTS;
          }
          public void setTIMING_SLOTS(String TIMING_SLOTS){
              this.TIMING_SLOTS=TIMING_SLOTS;
          }
          
           public Double getCHARGES(){
              return CHARGES;
          }
          public void setCHARGES(Double CHARGES){
              this.CHARGES=CHARGES;
          }
          
           public String getLUXURYTAX_ID(){
              return LUXURYTAX_ID;
          }
          public void setLUXURYTAX_ID(String LUXURYTAX_ID){
              this.LUXURYTAX_ID=LUXURYTAX_ID;
          }
          
           public String getTAX2_ID(){
              return TAX2_ID;
          }
          public void setTAX2_ID(String TAX2_ID){
              this.TAX2_ID=TAX2_ID;
          }
          public String getTAX3_ID(){
              return TAX3_ID;
          }
          public void setTAX3_ID(String TAX3_ID){
              this.TAX3_ID=TAX3_ID;
          }
           public int getBASIC(){
              return BASIC;
          }
          public void setBASIC(int BASIC){
              this.BASIC=BASIC;
          }
          public int getCASCADE(){
              return CASCADE;
          }
          public void setCASCADE(int CASCADE){
              this.CASCADE=CASCADE;
          }
          
          public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC=BASIC;
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
              return NON_MEM_CONTCT;
          }
          public void setNON_MEM_CONTCT(String NON_MEM_CONTCT){
              this.NON_MEM_CONTCT=NON_MEM_CONTCT;
          }
          
          public String getNON_MEM_ADDR(){
              return NON_MEM_ADDR;
          }
          public void setNON_MEM_ADDR(String NON_MEM_ADDR){
              this.NON_MEM_ADDR=NON_MEM_ADDR;
          }
         public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
          }
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
          }
           public String getHall_ID(){
              return Hall_ID;
          }
          public void setHall_ID(String Hall_ID){
              this.Hall_ID=Hall_ID;
          }
          
          public int getPAYMENT_DAYS(){
              return PAYMENT_DAYS;
          }
          
          public void setPAYMENT_DAYS(int PAYMENT_DAYS){
              this.PAYMENT_DAYS = PAYMENT_DAYS;
          }
          
          public Date getBOOKING_DATE_EX(){
              return BOOKING_DATE_EX;
          }
          public void setBOOKING_DATE_EX(Date BOOKING_DATE_EX){
              this.BOOKING_DATE_EX = BOOKING_DATE_EX;
          }
          public Date getBLOCKED_DATE(){
              return BLOCKED_DATE;
          }
          public void setBLOCKED_DATE(Date BLOCKED_DATE){
              this.BLOCKED_DATE = BLOCKED_DATE;
          }
          
          public Date getLAST_PAYMENT_DATE(){
              return LAST_PAYMENT_DATE;
          }
          public void setLAST_PAYMENT_DATE(Date LAST_PAYMENT_DATE){
              this.LAST_PAYMENT_DATE = LAST_PAYMENT_DATE;
          }
          
          public int getPAYMENT_FLAG(){
              return PAYMENT_FLAG;
          }
          public void setPAYMENT_FLAG(int PAYMENT_FLAG){
              this.PAYMENT_FLAG = PAYMENT_FLAG;
          }
          public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          public int getSLOT_FLAG(){
              return SLOT_FLAG;
          }
          public void setSLOT_FLAG(int SLOT_FLAG){
              this.SLOT_FLAG=SLOT_FLAG;
          }
          public String getROLE(){
              return ROLE;
          }
          public void setROLE(String ROLE){
              this.ROLE = ROLE;
          }
          public CustomerInfoExt getCustomer() {
                return Customer;
         }

          public void setCustomer(CustomerInfoExt value) throws BasicException {
                Customer =  LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(getMEMBER_ID());
         }
          
         public String getADVANCE_PERC(){
             return ADVANCE_PERC;
         } 
         public void setADVANCE_PERC(String ADVANCE_PERC){
             this.ADVANCE_PERC = ADVANCE_PERC;
         }
         
         
            public String getLUXURYTAX_N(){
              return LUXURYTAX_N;
          }
          public void setLUXURYTAX_N(String LUXURYTAX_N){
              this.LUXURYTAX_N=LUXURYTAX_N;
          }
          
           public String getTAX2_N(){
              return TAX2_N;
          }
          public void setTAX2_N(String TAX2_N){
              this.TAX2_N=TAX2_N;
          }
          public String getTAX3_N(){
              return TAX3_N;
          }
          public void setTAX3_N(String TAX3_N){
              this.TAX3_N=TAX3_N;
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
          
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          ID = dr.getString(1);
          MEMBERNAME= dr.getString(2);
          MEM_NO= dr.getString(3);
          STATUS = dr.getInt(4);
          HALL_NAME = dr.getString(5);
          FLOOR  = dr.getString(6);
          MEMBER_FLAG = dr.getInt(7);
          NON_MEM_FLAG = dr.getInt(8);
          BOOKING_DATE = dr.getString(9);
          TIMING_SLOTS = dr.getString(10);
          CHARGES = dr.getDouble(11);
          LUXURYTAX_ID = dr.getString(12);
          TAX2_ID = dr.getString(13);
          TAX3_ID = dr.getString(14);
          BASIC = dr.getInt(15);
          CASCADE = dr.getInt(16);
          NON_MEM_NAME = dr.getString(17);
          NON_MEM_CONTCT = dr.getString(18);
          NON_MEM_ADDR = dr.getString(19);
          MAX_CAPACITY = dr.getInt(20);
          FLAG = dr.getInt(21);
          Hall_ID = dr.getString(22);
          BASIC2 = dr.getInt(23);
          CASCADE2 = dr.getInt(24);
          PAYMENT_DAYS = dr.getInt(25);
          BOOKING_DATE_EX = dr.getTimestamp(26);
          BLOCKED_DATE = dr.getTimestamp(27);
          LAST_PAYMENT_DATE = dr.getTimestamp(28);
          PAYMENT_FLAG = dr.getInt(29);
          MEMBER_ID = dr.getString(30);
          SLOT_FLAG = dr.getInt(31);
          ROLE = dr.getString(32);
          ADVANCE_PERC = dr.getString(33);
          LUXURYTAX_N = dr.getString(34);
          TAX2_N = dr.getString(35);
          TAX3_N = dr.getString(36);
          BOOKING_SEQ_NO = dr.getString(37);
          CHK_IN_FLAG = dr.getInt(38);
          CustAddress = dr.getString(39);
          Tax1_Rate = dr.getDouble(40);
          Tax2_Rate = dr.getDouble(41);
          Tax3_Rate = dr.getDouble(42);
        }

        public Object getKey() {
           return this;
        }
          
    }
      
      
       public static class Cancel_requestInfo implements SerializableRead,IKeyed {
          
          private String ID;
          private String MEMBERNAME;
          private String MEM_NO;
          private int  STATUS ; 
          private String HALL_NAME;
          private String FLOOR ; 
          private int MEMBER_FLAG;
          private int NON_MEM_FLAG;
          private String BOOKING_DATE;
          private Date BOOKING_DATE_EX;
          private String TIMING_SLOTS;
          private Double CHARGES;
          private String LUXURYTAX;
          private String TAX2;
          private String TAX3;
          private int BASIC;
          private int CASCADE;
          private String NON_MEM_NAME;
          private String NON_MEM_CONTCT;
          private String NON_MEM_ADDR;
          private int MAX_CAPACITY ; 
          private int FLAG;
          private String Hall_ID;
          private int BASIC2;
          private int CASCADE2;
          private int PAYMENT_DAYS;
          private Date BLOCKED_DATE;
          private Date LAST_PAYMENT_DATE;
          private int PAYMENT_FLAG;
          private String MEMBER_ID;
          private int SLOT_FLAG;
          private String ROLE;
          private String BOOKING_SEQ_NO;
          private String REQ_CANCEL_BY;
          private String REQ_CANCEL_HOST;
          private Date REQ_CANCEL_DATE;
          private String Cancel_Note;
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
              return MEMBERNAME;
          }
          public void setMemberName(String MEMBERNAME){
              this.MEMBERNAME=MEMBERNAME;
          }
          
          public String getMem_No(){
              return MEM_NO;
          }
          public void setMem_No(String MEM_NO){
              this.MEM_NO=MEM_NO;
          }
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String gethall_name(){
              return HALL_NAME;
          }
          public void setHall_name(String HALL_NAME){
              this.HALL_NAME=HALL_NAME;
          }
          
           public String getFloor(){
              return FLOOR;
          }
          public void setFloor(String FLOOR){
              this.FLOOR=FLOOR;
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
          
           public String getTIMING_SLOTS(){
              return TIMING_SLOTS;
          }
          public void setTIMING_SLOTS(String TIMING_SLOTS){
              this.TIMING_SLOTS=TIMING_SLOTS;
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
           public int getBASIC(){
              return BASIC;
          }
          public void setBASIC(int BASIC){
              this.BASIC=BASIC;
          }
          public int getCASCADE(){
              return CASCADE;
          }
          public void setCASCADE(int CASCADE){
              this.CASCADE=CASCADE;
          }
          
          public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC=BASIC;
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
              return NON_MEM_CONTCT;
          }
          public void setNON_MEM_CONTCT(String NON_MEM_CONTCT){
              this.NON_MEM_CONTCT=NON_MEM_CONTCT;
          }
          
          public String getNON_MEM_ADDR(){
              return NON_MEM_ADDR;
          }
          public void setNON_MEM_ADDR(String NON_MEM_ADDR){
              this.NON_MEM_ADDR=NON_MEM_ADDR;
          }
         public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
          }
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
          }
           public String getHall_ID(){
              return Hall_ID;
          }
          public void setHall_ID(String Hall_ID){
              this.Hall_ID=Hall_ID;
          }
          
          public int getPAYMENT_DAYS(){
              return PAYMENT_DAYS;
          }
          
          public void setPAYMENT_DAYS(int PAYMENT_DAYS){
              this.PAYMENT_DAYS = PAYMENT_DAYS;
          }
          
          public Date getBOOKING_DATE_EX(){
              return BOOKING_DATE_EX;
          }
          public void setBOOKING_DATE_EX(Date BOOKING_DATE_EX){
              this.BOOKING_DATE_EX = BOOKING_DATE_EX;
          }
          public Date getBLOCKED_DATE(){
              return BLOCKED_DATE;
          }
          public void setBLOCKED_DATE(Date BLOCKED_DATE){
              this.BLOCKED_DATE = BLOCKED_DATE;
          }
          
          public Date getLAST_PAYMENT_DATE(){
              return LAST_PAYMENT_DATE;
          }
          public void setLAST_PAYMENT_DATE(Date LAST_PAYMENT_DATE){
              this.LAST_PAYMENT_DATE = LAST_PAYMENT_DATE;
          }
          
          public int getPAYMENT_FLAG(){
              return PAYMENT_FLAG;
          }
          public void setPAYMENT_FLAG(int PAYMENT_FLAG){
              this.PAYMENT_FLAG = PAYMENT_FLAG;
          }
           public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          
           public int getSLOT_FLAG(){
              return SLOT_FLAG;
          }
          public void setSLOT_FLAG(int SLOT_FLAG){
              this.SLOT_FLAG=SLOT_FLAG;
          }
           public String getROLE(){
              return ROLE;
          }
          public void setROLE(String ROLE){
              this.ROLE = ROLE;
          }
          
           public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          
           public String getREQ_CANCEL_BY(){
              return REQ_CANCEL_BY;
          }
          public void setREQ_CANCEL_BY(String REQ_CANCEL_BY){
              this.REQ_CANCEL_BY=REQ_CANCEL_BY;
          }
           public String getREQ_CANCEL_HOST(){
              return REQ_CANCEL_HOST;
          }
          public void setREQ_CANCEL_HOST(String REQ_CANCEL_HOST){
              this.REQ_CANCEL_HOST=REQ_CANCEL_HOST;
          }
           public String getREQ_CANCEL_DATE(){
               String d = Formats.TIMESTAMP.formatValue(REQ_CANCEL_DATE);
               return d;
          }
          public void setREQ_CANCEL_DATE(Date REQ_CANCEL_DATE){
              this.REQ_CANCEL_DATE=REQ_CANCEL_DATE;
          }
           public String getCancel_Note(){
              return Cancel_Note;
          }
          public void setCancel_Note(String Cancel_Note){
              this.Cancel_Note=Cancel_Note;
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
          MEMBERNAME= dr.getString(2);
          MEM_NO= dr.getString(3);
          STATUS = dr.getInt(4);
          HALL_NAME = dr.getString(5);
          FLOOR  = dr.getString(6);
          MEMBER_FLAG = dr.getInt(7);
          NON_MEM_FLAG = dr.getInt(8);
          BOOKING_DATE = dr.getString(9);
          TIMING_SLOTS = dr.getString(10);
          CHARGES = dr.getDouble(11);
          LUXURYTAX = dr.getString(12);
          TAX2 = dr.getString(13);
          TAX3 = dr.getString(14);
          BASIC = dr.getInt(15);
          CASCADE = dr.getInt(16);
          NON_MEM_NAME = dr.getString(17);
          NON_MEM_CONTCT = dr.getString(18);
          NON_MEM_ADDR = dr.getString(19);
          MAX_CAPACITY = dr.getInt(20);
          FLAG = dr.getInt(21);
          Hall_ID = dr.getString(22);
          BASIC2 = dr.getInt(23);
          CASCADE2 = dr.getInt(24);
          PAYMENT_DAYS = dr.getInt(25);
          BOOKING_DATE_EX = dr.getTimestamp(26);
          BLOCKED_DATE = dr.getTimestamp(27);
          LAST_PAYMENT_DATE = dr.getTimestamp(28);
          PAYMENT_FLAG = dr.getInt(29);
          MEMBER_ID = dr.getString(30);
          SLOT_FLAG = dr.getInt(31);
          ROLE = dr.getString(32);
          BOOKING_SEQ_NO = dr.getString(33);
          REQ_CANCEL_BY = dr.getString(34);
          REQ_CANCEL_HOST = dr.getString(35);
          REQ_CANCEL_DATE = dr.getTimestamp(36);
          Cancel_Note = dr.getString(37);
          Tax1_Rate = dr.getDouble(38);
          Tax2_Rate = dr.getDouble(39);
          Tax3_Rate = dr.getDouble(40);
          
          
        }

        public Object getKey() {
           return this;
        }  
       }
       
       
       
       
       // CHECK STATUS FOR HALLS ...........................................................................................
       
     public int getBlockFlag( String HallName ,AppView app ){
         int BlockFlag = 0;
         Object temp1 = new Object();
         try {
            temp1  =  new StaticSentence(app.getSession(), "select BLOCK_FLAG from hall_master where NAME = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{HallName } );
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
              BlockFlag = Integer.parseInt(temp1.toString());
         }
         else{
             BlockFlag=0;
         }
      
          return BlockFlag;
     }
       
     
     
     
     
       
       
}
