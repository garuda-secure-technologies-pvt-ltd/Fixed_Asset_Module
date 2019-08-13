
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
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


public class AdvanceRecvReportModel extends BeanFactoryDataSingle{
    private Session s;
    private List<AdvanceRecvReportModel.HallAdvInfo> Hall_status_data; 
    private List<AdvanceRecvReportModel.RoomAdvInfo> Room_Status_Data;
    private int Hall_length;
    private int Room_length; 
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
      public static AdvanceRecvReportModel LoadHallAdvanceRecieve(AppView app , Date FromDate , Date ToDate)throws BasicException{
         AdvanceRecvReportModel hall_advance = new AdvanceRecvReportModel(); 
         
          try{
            hall_advance.Hall_status_data = new ArrayList<AdvanceRecvReportModel.HallAdvInfo>();
            hall_advance.Hall_status_data = new StaticSentence(app.getSession(), "SELECT A.ID, A.CHECK_IN_DATE AS CHKDATE , A.MEMBERNAME, A.MEMBER_NO, A.TOTAL_AMOUNT, A.ADVANCE_RECV, A.RECIEPT_NO, A.CASH_FLAG,\n" +
                                                                                    "A.CHEQUE_FLAG, A.CHEQUE_NO, A.BANK_NAME, (SELECT h.NAME FROM hall_master h WHERE h.ID=A.HALLNAME) , A.BOOKING_ID , A.CRDATE ,\n" +
                                                                                    "B.BOOKING_SLOT  , B.MEMBER , A.GUESTNAME , A.CONTACT , A.BAL_AMT , A.BOOKING_SEQ_NO  AS BK , A.ADVNCE_REF , 'BOOKED' AS BkSTATUS\n" +
                                                                                    "FROM hall_advance_payment A , hall_booked_details B\n" +
                                                                                    "WHERE  B.ID = A.BOOKING_ID  AND A.CRDATE>=? AND A.CRDATE<=? \n" +
                                                                                    "UNION\n" +
                                                                                    "SELECT A.ID, A.CHECK_IN_DATE AS CHKDATE , A.MEMBERNAME, A.MEMBER_NO, A.TOTAL_AMOUNT, A.ADVANCE_RECV, A.RECIEPT_NO, A.CASH_FLAG,\n" +
                                                                                    "A.CHEQUE_FLAG, A.CHEQUE_NO, A.BANK_NAME, (SELECT h.NAME FROM hall_master h WHERE h.ID=A.HALLNAME) , A.BOOKING_ID , A.CRDATE ,\n" +
                                                                                    "B.BOOKING_SLOT  , B.MEMBER , A.GUESTNAME , A.CONTACT , A.BAL_AMT , A.BOOKING_SEQ_NO AS BK , A.ADVNCE_REF , 'Cancelled' AS BkSTATUS\n" +
                                                                                    "FROM hall_advance_payment A , hall_cancelled_details B\n" +
                                                                                    "WHERE  B.ID = A.BOOKING_ID AND A.CRDATE>=? AND A.CRDATE<=?  \n" +
                                                                                    "ORDER BY CHKDATE ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  }) ,new SerializerReadClass(AdvanceRecvReportModel.HallAdvInfo.class)).list(new Object[]{ FromDate ,  ToDate , FromDate ,  ToDate  });

            hall_advance.Hall_length = hall_advance.Hall_status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return hall_advance;
     } 
    
    
    // LOAD OF GUEST ROOMS .... 
      
       public static AdvanceRecvReportModel LoadGuestRoomAdvanceRecieve(AppView app, Date FromDate , Date ToDate)throws BasicException{
         AdvanceRecvReportModel Room_advance = new AdvanceRecvReportModel(); 
         
          try{
            Room_advance.Room_Status_Data = new ArrayList<AdvanceRecvReportModel.RoomAdvInfo>();
            Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT A.BOOKING_SEQ_NO AS BK ,A.CHECK_IN_DATE AS CHKDATE , (SELECT r.ROOMTYPE FROM guestroom_master r  WHERE r.ID = A.ROOMTYPE) AS ROOMTYPE,\n" +
                                                                                    "A.MEMBERNAME, A.MEMBERNO, A.TOTAL_AMOUNT, A.ADVANCE_RECV, A.RECIEPT_NO , A.CASH_FLAG, A.CHEQUE_FLAG, A.CHEQUE_NO, A.BANK_NAME, A.BOOKING_ID ,\n" +
                                                                                    "R.ROOM_NOS , A.CRDATE AS CRDT ,\n" +
                                                                                    "R.BOOKING_DAYS  ,\n" +
                                                                                    "R.MEMBER_FLAG  , A.GUESTNAME , A.CONTACT , A.BAL_AMT , A.ADVNCE_REF , 'Booked' AS BkSTATUS\n" +
                                                                                    "FROM guestroom_advance_payment A , guestroom_booked_details R\n" +
                                                                                    "WHERE R.ID=A.BOOKING_ID  AND A.CRDATE>=? AND A.CRDATE<=?   \n" +
                                                                                    "UNION\n" +
                                                                                    "SELECT A.BOOKING_SEQ_NO AS BK ,A.CHECK_IN_DATE AS CHKDATE , (SELECT r.ROOMTYPE FROM guestroom_master r  WHERE r.ID = A.ROOMTYPE) AS ROOMTYPE,\n" +
                                                                                    "A.MEMBERNAME, A.MEMBERNO, A.TOTAL_AMOUNT, A.ADVANCE_RECV, A.RECIEPT_NO , A.CASH_FLAG, A.CHEQUE_FLAG, A.CHEQUE_NO, A.BANK_NAME, A.BOOKING_ID ,\n" +
                                                                                    "R.ROOM_NOS , A.CRDATE AS CRDT ,\n" +
                                                                                    "R.BOOKING_DAYS  ,\n" +
                                                                                    "R.MEMBER_FLAG  , A.GUESTNAME , A.CONTACT , A.BAL_AMT , A.ADVNCE_REF , 'Cancelled' AS BkSTATUS\n" +
                                                                                    "FROM guestroom_advance_payment A , guestroom_cancelled_details R\n" +
                                                                                    "WHERE R.ID=A.BOOKING_ID   AND A.CRDATE>=? AND A.CRDATE<=?   \n" +
                                                                                    "ORDER by  CRDT ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(AdvanceRecvReportModel.RoomAdvInfo.class)).list(new Object[]{ FromDate ,  ToDate , FromDate ,  ToDate  });

            Room_advance.Room_length = Room_advance.Room_Status_Data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(AdvanceRecvReportModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Room_advance;
     } 
    
      
      
      
      
       public List<AdvanceRecvReportModel.HallAdvInfo> getHallList(){
           if(Hall_status_data!=null)
        {
            return Hall_status_data;
        }
        else
            return new ArrayList<AdvanceRecvReportModel.HallAdvInfo>();
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
          private String BOOKING_SEQ_NO;
          private String ADVNCE_REF;
          private String BkStatus;
          
          public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          public String getCHECK_IN_DATE(){
              String x = Formats.DATE.formatValue(CHECK_IN_DATE);
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
          public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO = BOOKING_SEQ_NO;
          } 
          public String getADVNCE_REF(){
              return ADVNCE_REF;
          }
          public void setADVNCE_REF(String ADVNCE_REF){
              this.ADVNCE_REF = ADVNCE_REF;
          } 
          public String getBkStatus(){
              return BkStatus;
          }
          public void setBkStatus(String BkStatus){
              this.BkStatus = BkStatus;
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
            BOOKING_SEQ_NO = dr.getString(20);
            ADVNCE_REF = dr.getString(21);
            BkStatus = dr.getString(22);
            
        }

        public Object getKey() {
           return this;
        }
        
      }
      
       
       // LOAD FOR GUEST ROOMS 
       
       
        public List<AdvanceRecvReportModel.RoomAdvInfo> getRoomList(){
           if(Room_Status_Data!=null)
        {
            return Room_Status_Data;
        }
          else
            return new ArrayList<AdvanceRecvReportModel.RoomAdvInfo>();
         }  
        
          public int getRoomSize()
        {
            return Room_length;
        }
       
        public static class RoomAdvInfo implements SerializableRead,IKeyed {
      
          private String BOOKING_SEQ_NO;
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
          private String ADVNCE_REF;
          private String BkStatus;
          
          public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
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
         public String getADVNCE_REF(){
              return ADVNCE_REF;
          }
          public void setADVNCE_REF(String ADVNCE_REF){
              this.ADVNCE_REF = ADVNCE_REF;
          } 
         public String getBkStatus(){
              return BkStatus;
          }
          public void setBkStatus(String BkStatus){
              this.BkStatus = BkStatus;
          } 
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            BOOKING_SEQ_NO = dr.getString(1);
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
            ADVNCE_REF = dr.getString(21);
            BkStatus = dr.getString(22);
        }

        public Object getKey() {
           return this;
        }
        
      }
       
       
      
}
