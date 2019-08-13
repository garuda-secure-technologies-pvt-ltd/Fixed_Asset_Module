
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


public class CheckInTableModel extends BeanFactoryDataSingle{
    private Session s;
  
    private List<CheckInTableModel.RoomAdvInfo> Room_Status_Data;
    private List<CheckInTableModel.Hall_AdvInfo> Hall_Status_Data;
    private List<CheckInTableModel.RoomTariffInfo> GR_Tariff_Info;
    private int Room_length; 
    private int Hall_length;
   
    @Override
    public void init(Session s) {
        this.s=s;
    }
   
    static String user;
    
    
    //LOAD DETAILS FOR GUESTROOM BOOKED..
    public static CheckInTableModel LoadGuestRoomCheckInDetail(AppView app, String Name , int MemFlag)throws BasicException{
         CheckInTableModel Room_advance = new CheckInTableModel(); 
             user = app.getAppUserView().getUser().getName();
          try{
            if(MemFlag==1){  
                Room_advance.Room_Status_Data = new ArrayList<CheckInTableModel.RoomAdvInfo>();
                Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT p.ID, CHECK_IN_DATE, (SELECT r.ROOMTYPE FROM guestroom_master r  WHERE r.ID = P.ROOMTYPE),\n" +
                                                                                        "P.MEMBERNAME, P.MEMBERNO, TOTAL_AMOUNT, ADVANCE_RECV, RECIEPT_NO , CASH_FLAG, CHEQUE_FLAG, CHEQUE_NO, BANK_NAME, BOOKING_ID ,\n" +
                                                                                        "B.ROOM_NOS  , p.CRDATE ,\n" +
                                                                                        "B.BOOKING_DAYS ,\n" +
                                                                                        "B.MEMBER_FLAG  ,\n" +
                                                                                        "GUESTNAME , CONTACT , m.MEM_TARIFF , M.N_MEM_TARIFF ,  (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=M.LUXURYTAX) ,\n" +
                                                                                        "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY= M.TAX2 ), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=M.TAX3 ) ,\n" +
                                                                                        "m.BASIC1 , m.CASCADE1, m.BASIC2 , m.CASCADE2 , P.BAL_AMT , P.BOOKING_SEQ_NO , M.LUXURYTAX , M.TAX2 , M.TAX3 , M.ADVANCE_PERC\n" +
                                                                                        "FROM guestroom_advance_payment P ,guestroom_master m , guestroom_booked_details B\n" +
                                                                                        "WHERE P.MEMBERNAME= ?    and p.roomtype=m.id and P.booking_id = B.id AND B.CHK_IN_FLAG=0 order by CHECK_IN_DATE ", SerializerWriteString.INSTANCE ,new SerializerReadClass(CheckInTableModel.RoomAdvInfo.class)).list(Name);
                  Room_advance.Room_length = Room_advance.Room_Status_Data.size();
            }
            else{
                Room_advance.Room_Status_Data = new ArrayList<CheckInTableModel.RoomAdvInfo>();
                Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT p.ID, CHECK_IN_DATE, (SELECT r.ROOMTYPE FROM guestroom_master r  WHERE r.ID = P.ROOMTYPE),\n" +
                                                                                        "P.MEMBERNAME, P.MEMBERNO, TOTAL_AMOUNT, ADVANCE_RECV, RECIEPT_NO , CASH_FLAG, CHEQUE_FLAG, CHEQUE_NO, BANK_NAME, BOOKING_ID ,\n" +
                                                                                        "B.ROOM_NOS  , p.CRDATE ,\n" +
                                                                                        "B.BOOKING_DAYS ,\n" +
                                                                                        "B.MEMBER_FLAG  ,\n" +
                                                                                        "GUESTNAME , CONTACT , m.MEM_TARIFF , M.N_MEM_TARIFF ,  (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=M.LUXURYTAX) ,\n" +
                                                                                        "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY= M.TAX2 ), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=M.TAX3 ) ,\n" +
                                                                                        "m.BASIC1 , m.CASCADE1, m.BASIC2 , m.CASCADE2 , P.BAL_AMT , P.BOOKING_SEQ_NO , M.LUXURYTAX , M.TAX2 , M.TAX3 , M.ADVANCE_PERC\n" +
                                                                                        "FROM guestroom_advance_payment P ,guestroom_master m , guestroom_booked_details B\n" +
                                                                                        "WHERE P.GUESTNAME= ?   and p.roomtype=m.id and P.booking_id = B.id   AND B.CHK_IN_FLAG=0  order by CHECK_IN_DATE ", SerializerWriteString.INSTANCE ,new SerializerReadClass(CheckInTableModel.RoomAdvInfo.class)).list(Name);
                 Room_advance.Room_length = Room_advance.Room_Status_Data.size();
            }
        }
        catch(BasicException ex){
            
            Logger.getLogger(CheckInTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Room_advance;
     } 
    
 // edited on 10th june 2014 ........................................
    
    
      //LOAD DETAILS FOR GUESTROOM BOOKED..
    public static CheckInTableModel LoadGuestRoomCheckInDetail2(AppView app, String BookingId )throws BasicException{
         CheckInTableModel Room_advance = new CheckInTableModel(); 
             user = app.getAppUserView().getUser().getName();
             
          try{
           
                Room_advance.Room_Status_Data = new ArrayList<CheckInTableModel.RoomAdvInfo>();
                Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT p.ID, CHECK_IN_DATE, (SELECT r.ROOMTYPE FROM guestroom_master r  WHERE r.ID = P.ROOMTYPE),\n" +
                                                                                        "P.MEMBERNAME, P.MEMBERNO, TOTAL_AMOUNT, ADVANCE_RECV, RECIEPT_NO , CASH_FLAG, CHEQUE_FLAG, CHEQUE_NO, BANK_NAME, BOOKING_ID ,\n" +
                                                                                        "B.ROOM_NOS  , p.CRDATE ,\n" +
                                                                                        "B.BOOKING_DAYS ,\n" +
                                                                                        "B.MEMBER_FLAG  ,\n" +
                                                                                        "GUESTNAME , CONTACT , m.MEM_TARIFF , M.N_MEM_TARIFF ,  (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=M.LUXURYTAX) ,\n" +
                                                                                        "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY= M.TAX2 ), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=M.TAX3 ) ,\n" +
                                                                                        "m.BASIC1 , m.CASCADE1, m.BASIC2 , m.CASCADE2 , P.BAL_AMT , P.BOOKING_SEQ_NO , M.LUXURYTAX , M.TAX2 , M.TAX3 , M.ADVANCE_PERC\n" +
                                                                                        "FROM guestroom_advance_payment P ,guestroom_master m , guestroom_booked_details B\n" +
                                                                                        "WHERE  p.roomtype=m.id and P.booking_id = B.id AND B.id=? order by CHECK_IN_DATE", SerializerWriteString.INSTANCE ,new SerializerReadClass(CheckInTableModel.RoomAdvInfo.class)).list(BookingId);
                  Room_advance.Room_length = Room_advance.Room_Status_Data.size();
          
           
        }
        catch(BasicException ex){
            
            Logger.getLogger(CheckInTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Room_advance;
     } 
    
    
    
    
    
    
    
    //LOAD DETAILS FOR HALL BOOKED
     public static CheckInTableModel LoadHallCheckInDetail(AppView app, String Name , int MemFlag)throws BasicException{
         CheckInTableModel Hall_advance = new CheckInTableModel(); 
             user = app.getAppUserView().getUser().getName();
             Date d = new Date();
             Calendar c = Calendar.getInstance();
             c.setTime(d);
             c.add(Calendar.DATE, -1);
             d = c.getTime();
             System.out.println(d);
             
             
          try{
            if(MemFlag==1){  
                Hall_advance.Hall_Status_Data = new ArrayList<CheckInTableModel.Hall_AdvInfo>();
                Hall_advance.Hall_Status_Data = new StaticSentence(app.getSession(), "SELECT P.ID, P.CHECK_IN_DATE, P.MEMBERNAME, P.MEMBER_NO, P.TOTAL_AMOUNT, P.ADVANCE_RECV, P.RECIEPT_NO ,\n" +
                                                                                        "(SELECT H.NAME FROM hall_master H WHERE H.ID=P.HALLNAME) , P.BOOKING_ID , P.GUESTNAME , P.CONTACT , P.BAL_AMT,\n" +
                                                                                        "B.FLOOR , B.MEMBER , B.BOOKING_SLOT , B.MEMBERNAME , B.LUXURYTAX , B.TAX2 , B.TAX3 , B.HALL_NAME , (SELECT H.ADVANCE_PERC FROM hall_master H WHERE H.ID=B.HALL_NAME) \n" +
                                                                                        "FROM hall_advance_payment P , hall_booked_details B  WHERE  P.BOOKING_ID = B.ID  AND P.MEMBERNAME=?  AND B.CHK_IN_FLAG=0", SerializerWriteString.INSTANCE ,new SerializerReadClass(CheckInTableModel.Hall_AdvInfo.class)).list(Name);
                Hall_advance.Hall_length = Hall_advance.Hall_Status_Data.size();
            }
            else{
                Hall_advance.Hall_Status_Data = new ArrayList<CheckInTableModel.Hall_AdvInfo>();
                Hall_advance.Hall_Status_Data = new StaticSentence(app.getSession(), "SELECT P.ID, P.CHECK_IN_DATE, P.MEMBERNAME, P.MEMBER_NO, P.TOTAL_AMOUNT, P.ADVANCE_RECV, P.RECIEPT_NO ,\n" +
                                                                                        "(SELECT H.NAME FROM hall_master H WHERE H.ID=P.HALLNAME) , P.BOOKING_ID , P.GUESTNAME , P.CONTACT , P.BAL_AMT,\n" +
                                                                                        "B.FLOOR , B.MEMBER , B.BOOKING_SLOT , B.MEMBERNAME , B.LUXURYTAX , B.TAX2 , B.TAX3 , B.HALL_NAME , (SELECT H.ADVANCE_PERC FROM hall_master H WHERE H.ID=B.HALL_NAME) \n" +
                                                                                        "FROM hall_advance_payment P , hall_booked_details B  WHERE  P.BOOKING_ID = B.ID  AND P.GUESTNAME=?  AND B.CHK_IN_FLAG=0", SerializerWriteString.INSTANCE ,new SerializerReadClass(CheckInTableModel.Hall_AdvInfo.class)).list(Name);
                Hall_advance.Hall_length = Hall_advance.Hall_Status_Data.size();
            }
        }
        catch(BasicException ex){
            
            Logger.getLogger(CheckInTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Hall_advance;
     } 
    
    
    
     
     
    
    
        public List<CheckInTableModel.RoomAdvInfo> getGuestRoomPath()
       {
           if(Room_Status_Data!=null)
           {
               return Room_Status_Data;
           }
           else
               return new ArrayList<CheckInTableModel.RoomAdvInfo>();
       } 
     
     
       public List<CheckInTableModel.Hall_AdvInfo> getHallPath()
       {
           if(Hall_Status_Data!=null)
           {
               return Hall_Status_Data;
           }
           else
               return new ArrayList<CheckInTableModel.Hall_AdvInfo>();
       } 
     
   
    
    
    
    
     public static CheckInTableModel LoadGuestRoomTariff(AppView app, String Name )throws BasicException{
         CheckInTableModel RoomTariff = new CheckInTableModel(); 
         
          try{
           
                RoomTariff.GR_Tariff_Info = new ArrayList<CheckInTableModel.RoomTariffInfo>();
                    RoomTariff.GR_Tariff_Info = new StaticSentence(app.getSession(), "SELECT MEM_TARIFF , N_MEM_TARIFF , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=LUXURYTAX) , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY= TAX2 ), (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=TAX3 ) , BASIC1 , CASCADE1, BASIC2 , CASCADE2 FROM guestroom_master WHERE ROOMTYPE=? ", SerializerWriteString.INSTANCE ,new SerializerReadClass(CheckInTableModel.RoomTariffInfo.class)).list(Name);
        }
        catch(BasicException ex){
            
            Logger.getLogger(CheckInTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return RoomTariff;
     } 
    
      public static class RoomTariffInfo implements SerializableRead,IKeyed {
      
          private Double MEM_Tariff;
          private Double N_MEM_Tariff;
          private Double TAX1;
          private Double TAX2;
          private Double TAX3;
          private int BASIC1;
          private int CASCADE1;
          private int BASIC2;
          private int CASCADE2;
          
          
          public Double getMEM_Tariff(){
              return MEM_Tariff;
          }
          public void setMEM_Tariff(Double MEM_Tariff){
              this.MEM_Tariff=MEM_Tariff;
          }
          
           public Double getN_MEM_Tariff(){
              return N_MEM_Tariff;
          }
          public void setN_MEM_Tariff(Double N_MEM_Tariff){
              this.N_MEM_Tariff=N_MEM_Tariff;
          }
          
          
           public Double getTAX1(){
              if(TAX1!=null){
                 return TAX1; 
              }
              else{
                  return 0.0;
              }
          }
          public void setTAX1(Double TAX1){
              this.TAX1=TAX1;
          }
          
          
           public Double getTAX2(){
             if(TAX2!=null){
                 return TAX2; 
              }
              else{
                  return 0.0;
              }
          }
          public void setTAX2(Double TAX2){
              this.TAX2=TAX2;
          }
          
          public Double getTAX3(){
             if(TAX3!=null){
                 return TAX3; 
              }
              else{
                  return 0.0;
              }
          }
          
          public void setTAX3(Double TAX3){
              this.TAX3=TAX3;
          }
          
          public int getBASIC1(){
              return BASIC1;
          }
          public void setBASIC1(int BASIC1){
              this.BASIC1 = BASIC1;
          }
          public int getCASCADE1(){
              return CASCADE1;
          }
          public void setCASCADE1(int CASCADE1){
              this.CASCADE1 = CASCADE1;
          }
          public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2 = BASIC2;
          }
          public int getCASCADE2(){
              return CASCADE2;
          }
          public void setCASCADE2(int CASCADE2){
              this.CASCADE2 = CASCADE2;
          }
          
         
        public void readValues(DataRead dr) throws BasicException {
           
            MEM_Tariff = dr.getDouble(1);
            N_MEM_Tariff = dr.getDouble(2);
            TAX1 = dr.getDouble(3);
            TAX2 = dr.getDouble(4);
            TAX3= dr.getDouble(5);
            BASIC1 = dr.getInt(6);
            CASCADE1 = dr.getInt(7);
            BASIC2 = dr.getInt(8);
            CASCADE2 = dr.getInt(9);
            
        }

        public Object getKey() {
           return this;
        }
     }
      
     public List<CheckInTableModel.RoomTariffInfo> getGuestRoomTariff()
       {
           if(GR_Tariff_Info!=null)
           {
               return GR_Tariff_Info;
           }
           else
               return new ArrayList<CheckInTableModel.RoomTariffInfo>();
       } 
      
     
     
      public  static class RoomAdvInfo implements SerializableRead,IKeyed {
      
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
          private Double MEM_Tariff;
          private Double N_MEM_Tariff;
          private Double TAX1;
          private Double TAX2;
          private Double TAX3;
          private int BASIC1;
          private int CASCADE1;
          private int BASIC2;
          private int CASCADE2;
          private Double BAL_AMT;
          private String BOOKING_SEQ_NO;
          private String TAX1_NAME;
          private String TAX2_NAME;
          private String TAX3_NAME;
          private String ADVANCE_PERC;
          
          
          public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          public String getCHECK_IN_DATE(){
              String x1 = Formats.TIMESTAMP.formatValue(CHECK_IN_DATE);
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
                String x = Formats.TIMESTAMP.formatValue(afterDate);
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
          
          public String getCurrDATE(){
              Date d = new Date();
              String x =d.toString();
              return x;
          }
          public Double getMEM_Tariff(){
              return MEM_Tariff;
          }
          public void setMEM_Tariff(Double MEM_Tariff){
              this.MEM_Tariff=MEM_Tariff;
          }
          
           public Double getN_MEM_Tariff(){
              return N_MEM_Tariff;
          }
          public void setN_MEM_Tariff(Double N_MEM_Tariff){
              this.N_MEM_Tariff=N_MEM_Tariff;
          }
          
          
           public Double getTAX1(){
              if(TAX1!=null){
                 return TAX1; 
              }
              else{
                  return 0.0;
              }
          }
          public void setTAX1(Double TAX1){
              this.TAX1=TAX1;
          }
          
          
           public Double getTAX2(){
             if(TAX2!=null){
                 return TAX2; 
              }
              else{
                  return 0.0;
              }
          }
          public void setTAX2(Double TAX2){
              this.TAX2=TAX2;
          }
          
          public Double getTAX3(){
             if(TAX3!=null){
                 return TAX3; 
              }
              else{
                  return 0.0;
              }
          }
          
          public void setTAX3(Double TAX3){
              this.TAX3=TAX3;
          }
          
          public int getBASIC1(){
              return BASIC1;
          }
          public void setBASIC1(int BASIC1){
              this.BASIC1 = BASIC1;
          }
          public int getCASCADE1(){
              return CASCADE1;
          }
          public void setCASCADE1(int CASCADE1){
              this.CASCADE1 = CASCADE1;
          }
          public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2 = BASIC2;
          }
          public int getCASCADE2(){
              return CASCADE2;
          }
          public void setCASCADE2(int CASCADE2){
              this.CASCADE2 = CASCADE2;
          }
          
          public String getUser(){
             return user;
          }
          
           public Double getBAL_AMT(){
              return BAL_AMT;
          }
          public void setBAL_AMT(Double BAL_AMT){
              this.BAL_AMT=BAL_AMT;
          }
           public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          
           public String getTAX1_NAME(){
              return TAX1_NAME;
          }
          public void setTAX1_NAME(String TAX1_NAME){
              this.TAX1_NAME = TAX1_NAME;
          }
            public String getTAX2_NAME(){
              return TAX2_NAME;
          }
          public void setTAX2_NAME(String TAX2_NAME){
              this.TAX2_NAME = TAX2_NAME;
          }
            public String getTAX3_NAME(){
              return TAX3_NAME;
          }
          public void setTAX3_NAME(String TAX3_NAME){
              this.TAX3_NAME = TAX3_NAME;
          }
          public String getADVANCE_PERC(){
              return ADVANCE_PERC;
          }
          public void setADVANCE_PERC(String ADVANCE_PERC){
              this.ADVANCE_PERC = ADVANCE_PERC;
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
            MEM_Tariff = dr.getDouble(20);
            N_MEM_Tariff = dr.getDouble(21);
            TAX1 = dr.getDouble(22);
            TAX2 = dr.getDouble(23);
            TAX3= dr.getDouble(24);
            BASIC1 = dr.getInt(25);
            CASCADE1 = dr.getInt(26);
            BASIC2 = dr.getInt(27);
            CASCADE2 = dr.getInt(28);
            BAL_AMT = dr.getDouble(29);
            BOOKING_SEQ_NO = dr.getString(30);
            TAX1_NAME = dr.getString(31);
            TAX2_NAME = dr.getString(32);
            TAX3_NAME = dr.getString(33);
            ADVANCE_PERC = dr.getString(34);
            
            
        }

        public Object getKey() {
           return this;
        }
     }
    
     
     
      public  static class Hall_AdvInfo implements SerializableRead,IKeyed {
      
          private String ADVANCE_ID;
          private Date CHECK_IN_DATE;
         
          private String HALLNAME;
          private String MEMBERNAME;
          private String  MEMBER_NO ; 
          private Double TOTAL_AMOUNT;
          private Double ADVANCE_RECV ; 
          private String RECIEPT_NO;
          private String BOOKING_ID;
          private Date ADVANCE_PMT_DT;
          private int MEMBER_FLAG;
          private String GuestName;
          private String Contact;
          private Double BAL_AMT;
          private String SLOT_TIMMINGS;
          private String FLOOR;
          private String MEMBER_ID;
          private String TAX1;
          private String TAX2;
          private String TAX3;
          private String HallName_ID;
          private String ADVANCE_PERC;
          
          public String getADVANCE_ID(){
              return ADVANCE_ID;
          }
          public void setADVANCE_ID(String ADVANCE_ID){
              this.ADVANCE_ID=ADVANCE_ID;
          }
          public String getCHECK_IN_DATE(){
              String x1 = Formats.DATE.formatValue(CHECK_IN_DATE);
              return x1;
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
          
          public String getCurrDATE(){
              Date d = new Date();
              String x =d.toString();
              return x;
          }
        
          
          public String getUser(){
             return user;
          }
          
           public Double getBAL_AMT(){
              return BAL_AMT;
          }
          public void setBAL_AMT(Double BAL_AMT){
              this.BAL_AMT=BAL_AMT;
          }
          
          public String getSLOT_TIMMINGS(){
              return SLOT_TIMMINGS;
          }
          public void setSLOT_TIMMINGS(String SLOT_TIMMINGS){
              this.SLOT_TIMMINGS = SLOT_TIMMINGS;
          }
          
          public String getFLOOR(){
              return FLOOR;
          }
          public void setFLOOR(String FLOOR){
              this.FLOOR = FLOOR;
          }
           public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          public String getTAX1(){
              return TAX1;
          }
          public void setTAX1(String TAX1){
              this.TAX1 = TAX1;
          }
            public String getTAX2(){
              return TAX2;
          }
          public void setTAX2(String TAX2){
              this.TAX2 = TAX2;
          }
            public String getTAX3(){
              return TAX3;
          }
          public void setTAX3(String TAX3){
              this.TAX3 = TAX3;
          }
          public String getHallName_ID(){
              return HallName_ID;
          }
          public void setHallName_ID(String HallName_ID){
              this.HallName_ID = HallName_ID;
          }
            public String getADVANCE_PERC(){
              return ADVANCE_PERC;
          }
          public void setADVANCE_PERC(String ADVANCE_PERC){
              this.ADVANCE_PERC = ADVANCE_PERC;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            ADVANCE_ID = dr.getString(1);
            CHECK_IN_DATE = dr.getTimestamp(2);
            MEMBERNAME = dr.getString(3);
            MEMBER_NO = dr.getString(4);
            TOTAL_AMOUNT = dr.getDouble(5);
            ADVANCE_RECV=dr.getDouble(6);
            RECIEPT_NO = dr.getString(7);
            HALLNAME = dr.getString(8);
            BOOKING_ID = dr.getString(9);
            GuestName = dr.getString(10);
            Contact = dr.getString(11);
            BAL_AMT = dr.getDouble(12);
            FLOOR = dr.getString(13);
            MEMBER_FLAG = dr.getInt(14);
            SLOT_TIMMINGS = dr.getString(15);
            MEMBER_ID = dr.getString(16);
            TAX1 = dr.getString(17);
            TAX2 = dr.getString(18);
            TAX3 = dr.getString(19);
            HallName_ID = dr.getString(20);
            ADVANCE_PERC = dr.getString(21);
            
            
        }

        public Object getKey() {
           return this;
        }
     }
     
      
      // GET CUSTOMER IS BY SEARCHKEY 
      
        public String getCustIDby_SearchKey(AppView app , String memNo){
         Object CustID = null;
        
         try {
            CustID  = new StaticSentence(app.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(memNo);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(CustID!=null){
             String x = CustID.toString();
             return x;
         }
         else{
             return "";
         }
        
      }  
     
      // GET TRANS rEF FOR ACCOUNT JOURNAL
       public String getTransRef(AppView app){
         Object Transref = null;
        
         try {
            Transref  = new StaticSentence(app.getSession(), "SELECT ID FROM FACILITY WHERE NAME='Room & Hall Bk'", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(Transref!=null){
             String x = Transref.toString();
             return x;
         }
         else{
             return "";
         }
        
      } 
       
        // GET REVENUE ACCOUNT ID FOR ROOM BOOKING TYPES
       public String getReve_Acct_Room(AppView app , String RoomType){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT REVENUE_ACCT FROM guestroom_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(RoomType);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }  
       
       
       
         // GET REVENUE ACCOUNT ID FOR ROOM BOOKING TYPES
       public String getReve_Acct_Hall(AppView app , String HallID){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT REVENUE_ACCT FROM hall_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(HallID);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }  
       
     
       
       
       // GET GUEST ROOM ID FROM GUEST ROOM TABLE
       
         public String getRoomBillID(AppView app , String BillID){
         Object billid = null;
        
         try {
            billid  = new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_bill WHERE ID=?" , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(BillID);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(billid!=null){
             String x = billid.toString();
             return x;
         }
         else{
             return "";
         }
        
      }  
      
         
       
       // GET Hall Bill ID FROM GUEST ROOM TABLE
       
         public String getHallBillID(AppView app , String BillID){
         Object billid = null;
        
         try {
            billid  = new StaticSentence(app.getSession(), "SELECT ID FROM hall_bill WHERE ID=?" , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(BillID);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(billid!=null){
             String x = billid.toString();
             return x;
         }
         else{
             return "";
         }
        
      }      
         
         
    
         
         
     // GET ADVANCE ACCOUNT FOR ROOM 
      // GET ACCOUNT ID FOR ROOM BOOKING TYPES
       public String getAdvance_Acct_Room(AppView app , String RoomType){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT ADVNCE_ACCT FROM guestroom_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(RoomType);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }  
       
     
       
       
        // GET ACCOUNT ID FOR ROOM HALL TYPES
       public String getAdvance_Acct_Hall(AppView app , String Hall_ID){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT ADVNCE_ACCT FROM hall_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Hall_ID);
          } 
         catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }       
         
      
       
       
       
      // GET REVENUE ACCOUNT ID FOR ROOM BOOKING TYPES
       public String getCancel_Accnt_Hall(AppView app , String HallID){
         Object ADvance_Account = null;
        
         try {
            ADvance_Account  = new StaticSentence(app.getSession(), "SELECT CANCEL_ACCT FROM hall_master WHERE ID=? " , SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(HallID);
          } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(ADvance_Account!=null){
             String x = ADvance_Account.toString();
             return x;
         }
         else{
             return "";
         }
        
      }     
     
       
      //  GET ADVANCE DURATION FOR PERTICULAR ROOM TYPE .......................................................................................
      
       public int getAdvancePayDura(AppView app , String Roomtype){
         
         Object o = new Object();
         int advanceDura = 0; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ADVNCE_BOOK_DURA FROM guestroom_master where ROOMTYPE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Roomtype);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            advanceDura = Integer.parseInt(o.toString());
            return advanceDura;
        }
        else{
            return advanceDura;
        }
     }    
       
     
       
     //  GET MAXIMUM DAYS  DURATION FOR PERTICULAR ROOM TYPE .......................................................................................
      
       public int getMaxDaysDuration(AppView app , String Roomtype){
         
         Object o = new Object();
         int advanceDura = 0; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT MAX_DAYS FROM guestroom_master where ROOMTYPE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Roomtype);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            advanceDura = Integer.parseInt(o.toString());
            return advanceDura;
        }
        else{
            return advanceDura;
        }
     }       
       
     
     //  GET ROOM TYPE ID     FOR PERTICULAR ROOM TYPE .......................................................................................
      
       public String getRoomID(AppView app , String Roomtype){
         
         Object o = new Object();
         String  RoomTypeID = null; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_master where ROOMTYPE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Roomtype);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            RoomTypeID = (o.toString());
            return RoomTypeID;
        }
        else{
            return RoomTypeID;
        }
     }       
          
       
       
      //  GET TOTAL ROOMS AVAILABLE FOR PERTICULAR ROOM TYPE .......................................................................................
      
       public int getTotalRoomsAvailable(AppView app , String Roomtype){
         
         Object o = new Object();
         int totRooms = 0; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ROOMS_AVAILABLE FROM guestroom_master where ROOMTYPE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Roomtype);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            totRooms = Integer.parseInt(o.toString());
            return totRooms;
        }
        else{
            return totRooms;
        }
     }       
       
       
       
       
       
       
       
       
       //  GET MAXIMUM DAYS  DURATION FOR PERTICULAR ROOM TYPE .......................................................................................
      
       public int getRowCountForCheckInTimeDiff(AppView app ){
         
         Object o = new Object();
         int RowCount = 0; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT COUNT(*) FROM checkinout_timediff  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            RowCount = Integer.parseInt(o.toString());
            return RowCount;
        }
        else{
            return RowCount;
        }
     }       
        
       
       
       
       
      //  GET ID FROM CHECKiN TIME DIFFERENCE FOR ROOMS  .......................................................................................
      
       public String getIdFromCheckInTimeDiffence(AppView app ){
         
         Object o = new Object();
         String  ID = null; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ID FROM checkinout_timediff  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            ID = (o.toString());
            return ID;
        }
        else{
            return ID;
        }
     }      
    
       
       
       
       
     // GET DETAILS OF DISCOUNT FOR ROOM BOOKING
       
         public int getDiscountFlag(AppView app ){
         
         Object o = new Object();
         int flag = 0; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT PERC_FLAG FROM general_discount  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            flag = Integer.parseInt(o.toString());
            return flag;
        }
        else{
            return 6;
        }
     }       
       
     // GET DETAILS OF DISCOUNT FOR ROOM BOOKING
       
         public Double getPerc_WiseDiscountForRooms(AppView app ){
         
         Object o = new Object();
         Double Perc = 0.00; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT PERC FROM general_discount  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            Perc = Double.parseDouble(o.toString());
            return Perc;
        }
        else{
            return 0.00;
        }
     }       
       
    
         
          // GET DETAILS OF DISCOUNT FOR ROOM BOOKING
       
         public Double getFixedAmt_WiseDiscountForRooms(AppView app ){
         
         Object o = new Object();
         Double Perc = 0.00; 
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT AMOUNT FROM general_discount  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            Perc = Double.parseDouble(o.toString());
            return Perc;
        }
        else{
            return 0.00;
        }
     }     
      
         
         
         
         
        // GET GUEST ROOM LINK ASSINGED WITH PERTICULAR HALL
       
         public String getGuestRoomLinkName(AppView app , String roomno ){
         
         Object o = new Object();
         String x = null;
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT CUSTOMER_N  FROM guestroom_link  where roomno=? AND active=1 ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(roomno);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            x = o.toString();
            return x;
        }
        else{
            return x;
        }
     }       
         
         
         
      // GET PENDING QTIES FOR LINKED GUESTROOM ACCOUNT...................................................................
       
         public int getPendingQTs(AppView app , String roomno , Date Check_In_Date ){
         
         Object o = new Object();
         int x = 0;
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ID   FROM qticket  where customer = (Select id from customers where name = ? ) and billed !=1 and CRDATE >= ? ",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), SerializerReadString.INSTANCE).find(new Object[]{roomno , Check_In_Date } );
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(o!=null){
            x = 1;
            return 1;
        }
        else{
            return 0;
        }
     }       
             
         
         
         
         
         
         
         
       
}
