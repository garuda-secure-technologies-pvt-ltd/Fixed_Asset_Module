
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class AdvanceRecieveTableModel extends BeanFactoryDataSingle{
    protected Session s;
    private final static String[] HALL_HERDERS = {"Booking ID","Hall Name","Booking Date", "Booked By" , "Advance Recv" , "Total Amt" , "Advance paymnt date" , "Bal Amt"};
    private final static String[]  ROOM_HEADERS = {"Booking ID","Room Type","Check-In Date", "Check-Out Date" ,"No of rooms booked" ,"Booked By" , "Advance Recv" , "Total Amt" , "Advance paymnt date" , "Bal Amt"};
    private List<AdvanceRecieveTableModel.HallAdvInfo> Hall_status_data; 
    private List<AdvanceRecieveTableModel.RoomAdvInfo> Room_Status_Data;
    private int Hall_length;
    private int Room_length; 
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    public AdvanceRecieveTableModel(){
   
    }
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
     public static AdvanceRecieveTableModel LoadHallAdvanceRecieve(AppView app)throws BasicException{
         AdvanceRecieveTableModel hall_advance = new AdvanceRecieveTableModel(); 
         
          try{
            hall_advance.Hall_status_data = new ArrayList<AdvanceRecieveTableModel.HallAdvInfo>();
            hall_advance.Hall_status_data = new StaticSentence(app.getSession(), "SELECT A.ID, CHECK_IN_DATE, A.MEMBERNAME, A.MEMBER_NO, TOTAL_AMOUNT, ADVANCE_RECV, RECIEPT_NO, CASH_FLAG,\n" +
                                                                                    "CHEQUE_FLAG, CHEQUE_NO, BANK_NAME, (SELECT h.NAME FROM hall_master h WHERE h.ID=HALLNAME) , BOOKING_ID , A.CRDATE ,\n" +
                                                                                    "(SELECT b.BOOKING_SLOT FROM hall_booked_details b WHERE b.ID = BOOKING_ID ) ,\n" +
                                                                                    " (SELECT b.MEMBER FROM hall_booked_details b WHERE b.ID = BOOKING_ID ), GUESTNAME , CONTACT , BAL_AMT ,  H.BOOKING_SEQ_NO , H.MEMBERNAME , H.HALL_NAME \n" +
                                                                                    " FROM hall_advance_payment A , hall_booked_details H   \n" +
                                                                                    "WHERE H.ID = A.BOOKING_ID ORDER BY H.BOOKING_SEQ_NO DESC ", SerializerWriteString.INSTANCE ,new SerializerReadClass(AdvanceRecieveTableModel.HallAdvInfo.class)).list();

            hall_advance.Hall_length = hall_advance.Hall_status_data.size();
            
        }
        catch(BasicException ex){
             ex.printStackTrace();
                    
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return hall_advance;
     } 
    
    
     
     
    public static AdvanceRecieveTableModel LoadGuestRoomAdvanceRecieve(AppView app)throws BasicException{
         AdvanceRecieveTableModel Room_advance = new AdvanceRecieveTableModel(); 
         
          try{
            Room_advance.Room_Status_Data = new ArrayList<AdvanceRecieveTableModel.RoomAdvInfo>();
            Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT P.ID, CHECK_IN_DATE, (SELECT r.ROOMTYPE FROM guestroom_master r  WHERE r.ID = P.ROOMTYPE),\n" +
                                                                                    "P.MEMBERNAME, P.MEMBERNO, P.TOTAL_AMOUNT, P.ADVANCE_RECV, RECIEPT_NO , CASH_FLAG, CHEQUE_FLAG, CHEQUE_NO, BANK_NAME, BOOKING_ID ,\n" +
                                                                                    "(SELECT r.ROOM_NOS FROM guestroom_booked_details r WHERE r.ID = P.BOOKING_ID) , P.CRDATE ,\n" +
                                                                                    "(SELECT r.BOOKING_DAYS FROM guestroom_booked_details r WHERE r.ID = P.BOOKING_ID) ,\n" +
                                                                                    "(SELECT r.MEMBER_FLAG FROM guestroom_booked_details r WHERE r.ID = p.BOOKING_ID) , GUESTNAME , CONTACT , BAL_AMT  ,  B.BOOKING_SEQ_NO , B.MEMBERNAME  , P.ROOMTYPE \n" +
                                                                                    "FROM guestroom_advance_payment P , guestroom_booked_details B\n" +
                                                                                    "WHERE B.ID=P.BOOKING_ID ORDER BY B.BOOKING_SEQ_NO  DESC ", SerializerWriteString.INSTANCE ,new SerializerReadClass(AdvanceRecieveTableModel.RoomAdvInfo.class)).list();

            Room_advance.Room_length = Room_advance.Room_Status_Data.size();
            
        }
        catch(BasicException ex){
             ex.printStackTrace();
                   
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Room_advance;
     } 
     
      public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Hall_status_data.size();
            }
          public int getColumnCount() {
                return HALL_HERDERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return HALL_HERDERS[column];
            }

          Class[] types = new Class[]{
               java.lang.Integer.class , java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,  java.lang.Double.class ,  java.lang.Double.class ,  java.lang.Double.class , java.lang.String.class , java.lang.Double.class
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false , false , false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
           public Object getValueAt(int rowIndex, int columnIndex) {
              AdvanceRecieveTableModel.HallAdvInfo r =Hall_status_data.get(rowIndex);
              
               switch(columnIndex){
                   
                   case 0: return r.getBooking_SEQ_no();
                   case 1: return r.getHALLNAME();
                   case 2: return r.getCHECK_IN_DATE();
                   case 3: return r.getMEMBERNAME();
                  
                   case 4: return decimalFormat.format(r.getADVANCE_RECV());
                   case 5: return decimalFormat.format(r.getTOTAL_AMOUNT());
                   
                   case 6: return r.getADVANCE_PMT_DT();
                   case 7: return decimalFormat.format(r.getBAL_AMT());
                  
                   
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
                return Room_Status_Data.size();
            }
          public int getColumnCount() {
                return ROOM_HEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return ROOM_HEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.Integer.class , java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.Integer.class , java.lang.String.class, java.lang.String.class,  java.lang.String.class , java.lang.Double.class ,  java.lang.Double.class , java.lang.String.class , java.lang.Double.class
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false , false , false , false , false , false , false 
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
           public Object getValueAt(int rowIndex, int columnIndex) {
              AdvanceRecieveTableModel.RoomAdvInfo r = Room_Status_Data.get(rowIndex);
              String Booking_ID = r.getBOOKING_ID();
              
               switch(columnIndex){
                   
                   case 0: return r.getBooking_SEQ_no();
                   case 1: return r.getROOMTYPE();
                   case 2: return r.getCHECK_IN_DATE();
                   case 3: return r.getCHECK_OUT_DATE();
                   case 4: return r.getNO_OF_ROOMS();
                   case 5: return r.getMEMBERNAME();
                  
                  
                   case 6: return decimalFormat.format(r.getADVANCE_RECV());
                   case 7: return decimalFormat.format(r.getTOTAL_AMOUNT());
                  
                   case 8: return r.getADVANCE_PMT_DT();
                   case 9: return decimalFormat.format(r.getBAL_AMT());
                  
                   
               }
                return null;
            }
          
          
        };
    }    
      
   
     
     // NO OF GUEST ROOMS BOOKED
     public int getNO_Of_Rooms(AppView app , String ID){
         Object note = null;
         int no_of_rooms = 0;
         try {
            note  = new StaticSentence(app.getSession(), "SELECT  CANCEL_REASON FROM hall_booked_details WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(ID);
          } catch (BasicException ex) {
             ex.printStackTrace();
                   
              Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         String x = note.toString();
         no_of_rooms = Integer.parseInt(x);
         return no_of_rooms;
      }
     
     // LIST OF GUEST FROM GUEST ROOM
      public List getGuetList_GL(AppView app ) throws BasicException{
          List<Object> Guest_list = new ArrayList<Object>();
           Guest_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NON_MEM_NAME FROM guestroom_booked_details WHERE  NON_MEM_NAME!='N/A' AND CHK_IN_FLAG=1 AND PAYMENT_FLAG=0",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          
          return Guest_list;
      }
      //LIST OF MEMBER FROM GUEST ROOM
       public List getMemList_GL(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
          // Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT C.NAME  FROM guestroom_booked_details B , CUSTOMERS C WHERE B.PAYMENT_FLAG=1 AND B.CHK_IN_FLAG=0 AND C.ID=B.MEMBERNAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();//commented by pratima 
          Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT C.NAME  FROM guestroom_booked_details B , CUSTOMERS C WHERE B.PAYMENT_FLAG=1 AND B.CHK_IN_FLAG=0 AND C.ID=B.MEMBERNAME AND B.BDATECHANGEFLAG=0 and req_can_by is null",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();//added by pratima : to remove the member names with old receiptno  whos booking date is changed from the memberlist of guestroom check-in 
          
          
          return Mem_list;
      }
      
       //LIST OF MEMBER FROM HALL BOOKED
        public List getMemListFromHall(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           //Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT A.membername FROM hall_advance_payment A , hall_booked_details B WHERE A.CHK_IN_FLAG=0 AND B.CHK_IN_FLAG=0 AND A.MEMBER_NO = B.MEM_NO  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();//commented by pratima
          Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT A.membername FROM hall_advance_payment A , hall_booked_details B WHERE A.CHK_IN_FLAG=0 AND B.CHK_IN_FLAG=0 AND A.MEMBER_NO = B.MEM_NO AND A.BOOKING_SEQ_NO=B.BOOKING_SEQ_NO  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();//added by pratima
          
          return Mem_list;
      }
        
        
        // LIST OF GUEST FROM HALL BOOKED
        public List getGuestListFromHall(AppView app ) throws BasicException{
          List<Object> Guest_list = new ArrayList<Object>();
            Guest_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT A.guestname FROM hall_advance_payment A , hall_booked_details B where A.guestname !='' AND A.CHK_IN_FLAG=0 AND B.CHK_IN_FLAG=0  AND A.MEMBER_NO = B.MEM_NO",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          
          return Guest_list;
        }
       
       
      
     // GUEST DETAILS FOR HALL BOOKING
      public List getHall_Guest_Details(AppView app , String Booking_ID){
         List<Object> Guest_list = new ArrayList<Object>();
         Object o1 = null;
         Object o2=null;
         Object o3=null;
         
         try {
            o1  = new StaticSentence(app.getSession(), "SELECT NON_MEM_NAME FROM hall_booked_details WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) , SerializerReadString.INSTANCE).find(new Object[]{Booking_ID});
            o2  = new StaticSentence(app.getSession(), "SELECT  NON_MEM_CONTCT FROM hall_booked_details WHERE ID = ? ",new SerializerWriteBasic(new Datas[]{Datas.STRING}),SerializerReadString.INSTANCE).find(new Object[]{Booking_ID} );
            o3 =  new StaticSentence(app.getSession(), "SELECT  NON_MEM_ADDR FROM hall_booked_details WHERE ID = ? ",new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{Booking_ID} );
         
         } catch (BasicException ex) {
            ex.printStackTrace();
                     
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         Guest_list.add(o1);
         Guest_list.add(o2);
         Guest_list.add(o3);
          
          return Guest_list;
      }
      
      // GUEST LIST FOR ROOM BOOKING..
      public List getGuestRoom_Guest_Details(AppView app , String Booking_ID){
         List<Object> Guest_list = new ArrayList<Object>();
         Object o1 = null;
         Object o2=null;
         Object o3=null;
         
         try {
            o1  = new StaticSentence(app.getSession(), "SELECT NON_MEM_NAME FROM guestroom_booked_details WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) , SerializerReadString.INSTANCE).find(new Object[]{Booking_ID});
            o2  = new StaticSentence(app.getSession(), "SELECT  NON_MEM_CNTCT FROM guestroom_booked_details WHERE ID = ? ",new SerializerWriteBasic(new Datas[]{Datas.STRING}),SerializerReadString.INSTANCE).find(new Object[]{Booking_ID} );
            o3 =  new StaticSentence(app.getSession(), "SELECT  NON_MEM_ADDR FROM guestroom_booked_details WHERE ID = ? ",new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{Booking_ID} );
         
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         Guest_list.add(o1);
         Guest_list.add(o2);
         Guest_list.add(o3);
          
          return Guest_list;
      }
      
      
    public List<AdvanceRecieveTableModel.HallAdvInfo> getHallList(){
           if(Hall_status_data!=null)
        {
            return Hall_status_data;
        }
        else
            return new ArrayList<AdvanceRecieveTableModel.HallAdvInfo>();
      }  
    
    
      public List<AdvanceRecieveTableModel.RoomAdvInfo> getRoomList(){
           if(Room_Status_Data!=null)
        {
            return Room_Status_Data;
        }
        else
            return new ArrayList<AdvanceRecieveTableModel.RoomAdvInfo>();
      }  
      
      
     public int getHallSize()
    {
        return Hall_length;
    }
     
      public static class HallAdvInfo implements SerializableRead,IKeyed {
      
          private String ID;
          private Date CHECK_IN_DATE;
          
          private String MEMBERNAME;
          private String  MEMBER_NO ; 
          private Double TOTAL_AMOUNT;
          private Double ADVANCE_RECV ; 
          private String RECIEPT_NO;
          private int CASH_FLAG;
          private int CHECK_FLAG;
          private String CHECK_NO;
          private String BANK_NAME;
          private String HALLNAME;
          private String BOOKING_ID;
          private Date ADVANCE_PMT_DT;
          private String BOOKING_SLOT;
          private int MEMBER_FLAG;
          private String GuestName;
          private String Contact;
          private Double BAL_AMT;
          private String Booking_SEQ_no;
          private String CustomerID;
          private String HallId;
          
          
          public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          public String getCHECK_IN_DATE(){
              String x = Formats.TIMESTAMP.formatValue(CHECK_IN_DATE);
              return x;
          }
          public void setCHECK_IN_DATE(Date CHECK_IN_DATE){
              this.CHECK_IN_DATE=CHECK_IN_DATE;
          }
          
          
          
          public String getMEMBERNAME(){
              return MEMBERNAME;
          }
          public void setMEMBERNAME(String MEMBERNAME){
              this.MEMBERNAME = MEMBERNAME;
          }
           public String getMEMBER_NO(){
              return MEMBER_NO;
          }
          public void setMEMBER_NO(String MEMBER_NO){
              this.MEMBER_NO = MEMBER_NO;
          }
         public Double getTOTAL_AMOUNT(){
             return TOTAL_AMOUNT;
         }
         public void setTOTAL_AMOUNT(Double TOTAL_AMOUNT){
             this.TOTAL_AMOUNT = TOTAL_AMOUNT;
         }
         public Double getADVANCE_RECV(){
             return ADVANCE_RECV;
         }
         public void setADVANCE_RECV(Double ADVANCE_RECV){
             this.ADVANCE_RECV = ADVANCE_RECV;
         }
         
        public String getRECIEPT_NO(){
              return RECIEPT_NO;
          }
          public void setRECIEPT_NO(String RECIEPT_NO){
              this.RECIEPT_NO = RECIEPT_NO;
          }
          public int getCASH_FLAG(){
              return CASH_FLAG;
          }
          public void setCASH_FLAG(int CASH_FLAG){
              this.CASH_FLAG = CASH_FLAG;
          }
         public int getCHECK_FLAG(){
              return CHECK_FLAG;
          }
          public void setCHECK_FLAG(int CHECK_FLAG){
              this.CHECK_FLAG = CHECK_FLAG;
          }
          public String getCHECK_NO(){
              return CHECK_NO;
          }
          public void setCHECK_NO(String CHECK_NO){
              this.CHECK_NO = CHECK_NO;
          }
          
          public String getBANK_NAME(){
              return BANK_NAME;
          }
          public void setBANK_NAME(String BANK_NAME){
              this.BANK_NAME = BANK_NAME;
          }
          public String getHALLNAME(){
              return HALLNAME;
          }
          public void setHALLNAME(String HALLNAME){
              this.HALLNAME = HALLNAME;
          }
          
          public String getBOOKING_ID(){
              return BOOKING_ID;
          }
          public void setBOOKING_ID(String BOOKING_ID){
              this.BOOKING_ID = BOOKING_ID;
          }
          
          public String getADVANCE_PMT_DT(){
              String x = Formats.DATE.formatValue(ADVANCE_PMT_DT);
              return x;
          }
          public void ADVANCE_PMT_DT(Date ADVANCE_PMT_DT){
              this.ADVANCE_PMT_DT = ADVANCE_PMT_DT;
          }
          public String getBOOKING_SLOT(){
              return BOOKING_SLOT;
          }
          public void setBOOKING_SLOT(String BOOKING_SLOT){
              this.BOOKING_SLOT=BOOKING_SLOT;
          }
          public int getMEMBER_FLAG(){
              return MEMBER_FLAG;
          }
          public void setMEMBER_FLAG(int MEMBER_FLAG){
              this.MEMBER_FLAG = MEMBER_FLAG;
          }
          public String getGuestName(){
              return GuestName;
          }
          public void setGuestName(String GuestName){
              this.GuestName = GuestName;
          }
          public String getContact(){
              return Contact;
          }
          public void setContact(String Contact){
              this.Contact = Contact;
          }
          public Double getBAL_AMT(){
             return BAL_AMT;
         }
         public void setBAL_AMT(Double BAL_AMT){
             this.BAL_AMT = BAL_AMT;
         }
          public String getBooking_SEQ_no(){
              return Booking_SEQ_no;
          }
          public void setBooking_SEQ_no(String Booking_SEQ_no){
              this.Booking_SEQ_no=Booking_SEQ_no;
          }
          
          public String getCustomerID(){
              return CustomerID;
          }
          public void setCustomerID(String CustomerID){
              this.CustomerID=CustomerID;
          }
          
          public String getHallId(){
              return HallId;
          }
          public void setHallId(String HallId){
              this.HallId = HallId;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            ID = dr.getString(1);
            CHECK_IN_DATE = dr.getTimestamp(2);
            MEMBERNAME = dr.getString(3);
            MEMBER_NO = dr.getString(4);
            TOTAL_AMOUNT = dr.getDouble(5);
            ADVANCE_RECV=dr.getDouble(6);
            RECIEPT_NO = dr.getString(7);
            CASH_FLAG = dr.getInt(8);
            CHECK_FLAG = dr.getInt(9);
            CHECK_NO = dr.getString(10);
            BANK_NAME = dr.getString(11);
            HALLNAME = dr.getString(12);
            BOOKING_ID = dr.getString(13);
            ADVANCE_PMT_DT = dr.getTimestamp(14);
            BOOKING_SLOT = dr.getString(15);
            MEMBER_FLAG = dr.getInt(16);
            GuestName = dr.getString(17);
            Contact = dr.getString(18);
            BAL_AMT = dr.getDouble(19);
            Booking_SEQ_no = dr.getString(20);
            CustomerID = dr.getString(21);
            HallId = dr.getString(22);
            
            
        }

        public Object getKey() {
           return this;
        }
        
      }
      
      
      
      
       public static class RoomAdvInfo implements SerializableRead,IKeyed {
      
          private String ID;
          private Date CHECK_IN_DATE;
          private Date CHECK_OUT_DATE;
          private String ROOMTYPE;
          private String MEMBERNAME;
          private String  MEMBER_NO ; 
          private Double TOTAL_AMOUNT;
          private Double ADVANCE_RECV ; 
          private String RECIEPT_NO;
          private int CASH_FLAG;
          private int CHECK_FLAG;
          private String CHECK_NO;
          private String BANK_NAME;
         
          private String BOOKING_ID;
          private Date ADVANCE_PMT_DT;
          private int NO_OF_ROOMS;
          private int BOOKING_DAYS;
          private int MEMBER_FLAG;
          private String GuestName;
          private String Contact;
          private Double BAL_AMT;
          private String Booking_SEQ_no;
          private String CustomerID;
          private String RoomTypeID;
          
          
          
          
          public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          public String getCHECK_IN_DATE(){
              String x1 = Formats.DATE.formatValue(CHECK_IN_DATE);
              return x1;
          }
          public void setCHECK_IN_DATE(Date CHECK_IN_DATE){
              this.CHECK_IN_DATE=CHECK_IN_DATE;
          }
           
          public String getCHECK_OUT_DATE(){
                Calendar call = Calendar.getInstance();
                call.setTimeInMillis(CHECK_IN_DATE.getTime());
                call.add(Calendar.DATE, BOOKING_DAYS);
                Date afterDate = call.getTime();
                String x = Formats.DATE.formatValue(afterDate);
               return x;
          }
          
          public void setCHECK_OUT_DATE(Date CHECK_OUT_DATE){
              this.CHECK_OUT_DATE=CHECK_OUT_DATE;
          }
          public String getMEMBERNAME(){
              return MEMBERNAME;
          }
          public void setMEMBERNAME(String MEMBERNAME){
              this.MEMBERNAME = MEMBERNAME;
          }
           public String getMEMBER_NO(){
              return MEMBER_NO;
          }
          public void setMEMBER_NO(String MEMBER_NO){
              this.MEMBER_NO = MEMBER_NO;
          }
         public Double getTOTAL_AMOUNT(){
             return TOTAL_AMOUNT;
         }
         public void setTOTAL_AMOUNT(Double TOTAL_AMOUNT){
             this.TOTAL_AMOUNT = TOTAL_AMOUNT;
         }
         public Double getADVANCE_RECV(){
             return ADVANCE_RECV;
         }
         public void setADVANCE_RECV(Double ADVANCE_RECV){
             this.ADVANCE_RECV = ADVANCE_RECV;
         }
         
        public String getRECIEPT_NO(){
              return RECIEPT_NO;
          }
          public void setRECIEPT_NO(String RECIEPT_NO){
              this.RECIEPT_NO = RECIEPT_NO;
          }
          public int getCASH_FLAG(){
              return CASH_FLAG;
          }
          public void setCASH_FLAG(int CASH_FLAG){
              this.CASH_FLAG = CASH_FLAG;
          }
         public int getCHECK_FLAG(){
              return CHECK_FLAG;
          }
          public void setCHECK_FLAG(int CHECK_FLAG){
              this.CHECK_FLAG = CHECK_FLAG;
          }
          public String getCHECK_NO(){
              return CHECK_NO;
          }
          public void setCHECK_NO(String CHECK_NO){
              this.CHECK_NO = CHECK_NO;
          }
          
          public String getBANK_NAME(){
              return BANK_NAME;
          }
          public void setBANK_NAME(String BANK_NAME){
              this.BANK_NAME = BANK_NAME;
          }
          public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE = ROOMTYPE;
          }
          
          public String getBOOKING_ID(){
              return BOOKING_ID;
          }
          public void setBOOKING_ID(String BOOKING_ID){
              this.BOOKING_ID = BOOKING_ID;
          }
          
          public String getADVANCE_PMT_DT(){
              String x = Formats.DATE.formatValue(ADVANCE_PMT_DT);
              return x;
          }
          public void ADVANCE_PMT_DT(Date ADVANCE_PMT_DT){
              this.ADVANCE_PMT_DT = ADVANCE_PMT_DT;
          }
          public int getNO_OF_ROOMS(){
              return NO_OF_ROOMS;
          }
          public void setNO_OF_ROOMS(int NO_OF_ROOMS){
              this.NO_OF_ROOMS = NO_OF_ROOMS;
          }
          public int getBOOKING_DAYS(){
              return BOOKING_DAYS;
          }
          public void setBOOKING_DAYS(int BOOKING_DAYS){
              this.BOOKING_DAYS = BOOKING_DAYS;
          }
          
           public int getMEMBER_FLAG(){
              return MEMBER_FLAG;
          }
          public void setMEMBER_FLAG(int MEMBER_FLAG){
              this.MEMBER_FLAG = MEMBER_FLAG;
          }
           public String getGuestName(){
              return GuestName;
          }
          public void setGuestName(String GuestName){
              this.GuestName = GuestName;
          }
          public String getContact(){
              return Contact;
          }
          public void setContact(String Contact){
              this.Contact = Contact;
          }
          
          public Double getBAL_AMT(){
             return BAL_AMT;
         }
         public void setBAL_AMT(Double BAL_AMT){
             this.BAL_AMT = BAL_AMT;
         }
           public String getBooking_SEQ_no(){
              return Booking_SEQ_no;
          }
          public void setBooking_SEQ_no(String Booking_SEQ_no){
              this.Booking_SEQ_no=Booking_SEQ_no;
          }
         
          public String getCustomerID(){
              return CustomerID;
          }
          public void setCustomerID(String CustomerID){
              this.CustomerID=CustomerID;
          }
          public String getRoomTypeID(){
              return RoomTypeID;
          }
          public void setRoomTypeID(String RoomTypeID){
              this.RoomTypeID = RoomTypeID;
          }
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            ID = dr.getString(1);
            CHECK_IN_DATE = dr.getTimestamp(2);
            ROOMTYPE = dr.getString(3);
            MEMBERNAME = dr.getString(4);
            MEMBER_NO = dr.getString(5);
            TOTAL_AMOUNT = dr.getDouble(6);
            ADVANCE_RECV=dr.getDouble(7);
            RECIEPT_NO = dr.getString(8);
            CASH_FLAG = dr.getInt(9);
            CHECK_FLAG = dr.getInt(10);
            CHECK_NO = dr.getString(11);
            BANK_NAME = dr.getString(12);
            BOOKING_ID = dr.getString(13);
            NO_OF_ROOMS = dr.getInt(14);
            ADVANCE_PMT_DT = dr.getTimestamp(15);
            BOOKING_DAYS = dr.getInt(16);
            MEMBER_FLAG = dr.getInt(17);
            GuestName = dr.getString(18);
            Contact = dr.getString(19);
            BAL_AMT = dr.getDouble(20);
            Booking_SEQ_no = dr.getString(21);
            CustomerID = dr.getString(22);
            RoomTypeID = dr.getString(23);
            
        }

        public Object getKey() {
           return this;
        }
        
      }
       
     
      
}
