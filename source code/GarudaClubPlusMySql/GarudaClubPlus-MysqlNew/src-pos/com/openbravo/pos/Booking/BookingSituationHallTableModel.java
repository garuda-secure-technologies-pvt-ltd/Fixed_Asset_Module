
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookingSituationHallTableModel extends BeanFactoryDataSingle{
    private Session s;
    private int bookedHallLength;
    private int bookedRoomLength;
    private List<BookingSituationHallTableModel.HallStatusInfo> status_data;
     private List<BookingSituationHallTableModel.RoomStatusInfo> Room_status_data;
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    
     public static BookingSituationHallTableModel loadInstanceBooked_Hall_Status(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BookingSituationHallTableModel Booked_Hall_Status = new BookingSituationHallTableModel(); 
         
          try{
            Booked_Hall_Status.status_data = new ArrayList<BookingSituationHallTableModel.HallStatusInfo>();
            Booked_Hall_Status.status_data = new StaticSentence(app.getSession(), "SELECT H.BOOKING_SEQ_NO , C.NAME , H.MEM_NO , H.STATUS ,  (SELECT M.NAME FROM HALL_MASTER M WHERE M.ID=H.HALL_NAME ), H.FLOOR , H.MEMBER , H.BOOKING_DATE ,\n" +
                                                                                    "H.BOOKING_SLOT , H.CHARGES , H.CRBY , H.CRDATE ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.LUXURYTAX),\n" +
                                                                                    "(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX2)  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX3) ,\n" +
                                                                                    " H.NON_MEM_NAME ,\n" +
                                                                                    "H.NON_MEM_CONTCT , H.NON_MEM_ADDR , H.REQ_APPROVE_BY , H.REQ_APPROV_DATE , H.BOOKING_DATE_EX  as bookeddate , H.FLAG ,\n" +
                                                                                    "H.PAYMENT_FLAG , H.SLOT_FLAG , H.CHK_IN_FLAG , '2008-01-01 00:00:00' as ADVNCE_DATE , '' as PAYMNT_REF\n" +
                                                                                    "FROM hall_booked_details H , CUSTOMERS C\n" +
                                                                                    "WHERE C.ID=H.MEMBERNAME AND H.PAYMENT_FLAG=0  AND H.BOOKING_DATE_EX >= ?  AND H.BOOKING_DATE_EX<= ?\n" +
                                                                                    "\n" +
                                                                                    "union\n" +
                                                                                    "SELECT H.BOOKING_SEQ_NO , C.NAME , H.MEM_NO , H.STATUS ,  (SELECT M.NAME FROM HALL_MASTER M WHERE M.ID=H.HALL_NAME ), H.FLOOR , H.MEMBER , H.BOOKING_DATE ,\n" +
                                                                                    "H.BOOKING_SLOT , H.CHARGES , H.CRBY , H.CRDATE ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.LUXURYTAX),\n" +
                                                                                    "(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX2)  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX3) ,\n" +
                                                                                    " H.NON_MEM_NAME ,\n" +
                                                                                    "H.NON_MEM_CONTCT , H.NON_MEM_ADDR , H.REQ_APPROVE_BY , H.REQ_APPROV_DATE , H.BOOKING_DATE_EX as bookeddate , H.FLAG ,\n" +
                                                                                    "H.PAYMENT_FLAG , H.SLOT_FLAG , H.CHK_IN_FLAG , A.CRDATE AS ADVNCE_DATE , A.ADVNCE_REF as PAYMNT_REF\n" +
                                                                                    "FROM hall_booked_details H , CUSTOMERS C cross join hall_advance_payment A\n" +
                                                                                    "\n" +
                                                                                    "WHERE C.ID=H.MEMBERNAME AND H.PAYMENT_FLAG=1 AND A.BOOKING_ID = H.ID  AND H.BOOKING_DATE_EX >= ? AND H.BOOKING_DATE_EX<= ?\n" +
                                                                                    "\n" +
                                                                                    "ORDER BY H.BOOKING_SEQ_NO DESC ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP}) ,new SerializerReadClass(BookingSituationHallTableModel.HallStatusInfo.class)).list(new Object[]{ FrmDate , ToDate , FrmDate , ToDate});

            Booked_Hall_Status.bookedHallLength = Booked_Hall_Status.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookingSituationHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Hall_Status;
         
     }
     
     
     // LOAD DATA BY MEMBER ID....... FOR HALL BOOKED DETAILS 
     
      public static BookingSituationHallTableModel loadInstanceBooked_Hall_StatusByHallID(AppView app , Date FrmDate , Date ToDate , String MemID)throws BasicException{
         BookingSituationHallTableModel Booked_Hall_Status = new BookingSituationHallTableModel(); 
         
          try{
            Booked_Hall_Status.status_data = new ArrayList<BookingSituationHallTableModel.HallStatusInfo>();
            Booked_Hall_Status.status_data = new StaticSentence(app.getSession(), "SELECT H.BOOKING_SEQ_NO , C.NAME , H.MEM_NO , H.STATUS ,  (SELECT M.NAME FROM HALL_MASTER M WHERE M.ID=H.HALL_NAME ), H.FLOOR , H.MEMBER , H.BOOKING_DATE ,\n" +
                                                                                    "H.BOOKING_SLOT , H.CHARGES , H.CRBY , H.CRDATE ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.LUXURYTAX),\n" +
                                                                                    "(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX2)  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX3) ,\n" +
                                                                                    " H.NON_MEM_NAME ,\n" +
                                                                                    "H.NON_MEM_CONTCT , H.NON_MEM_ADDR , H.REQ_APPROVE_BY , H.REQ_APPROV_DATE , H.BOOKING_DATE_EX  as bookeddate , H.FLAG ,\n" +
                                                                                    "H.PAYMENT_FLAG , H.SLOT_FLAG , H.CHK_IN_FLAG ,  '2008-01-01 00:00:00' as ADVNCE_DATE , '' as PAYMNT_REF\n" +
                                                                                    "FROM hall_booked_details H , CUSTOMERS C\n" +
                                                                                    "WHERE C.ID=H.MEMBERNAME AND H.PAYMENT_FLAG=0  AND H.BOOKING_DATE_EX >= ?  AND H.BOOKING_DATE_EX<= ?  AND H.MEMBERNAME=? \n" +
                                                                                    "\n" +
                                                                                    "union\n" +
                                                                                    "SELECT H.BOOKING_SEQ_NO , C.NAME , H.MEM_NO , H.STATUS ,  (SELECT M.NAME FROM HALL_MASTER M WHERE M.ID=H.HALL_NAME ), H.FLOOR , H.MEMBER , H.BOOKING_DATE ,\n" +
                                                                                    "H.BOOKING_SLOT , H.CHARGES , H.CRBY , H.CRDATE ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.LUXURYTAX),\n" +
                                                                                    "(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX2)  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = H.TAX3) ,\n" +
                                                                                    " H.NON_MEM_NAME ,\n" +
                                                                                    "H.NON_MEM_CONTCT , H.NON_MEM_ADDR , H.REQ_APPROVE_BY , H.REQ_APPROV_DATE , H.BOOKING_DATE_EX as bookeddate , H.FLAG ,\n" +
                                                                                    "H.PAYMENT_FLAG , H.SLOT_FLAG , H.CHK_IN_FLAG , A.CRDATE AS ADVNCE_DATE , A.ADVNCE_REF as PAYMNT_REF\n" +
                                                                                    "FROM hall_booked_details H , CUSTOMERS C cross join hall_advance_payment A\n" +
                                                                                    "\n" +
                                                                                    "WHERE C.ID=H.MEMBERNAME AND H.PAYMENT_FLAG=1 AND A.BOOKING_ID = H.ID  AND H.BOOKING_DATE_EX >= ?  AND H.BOOKING_DATE_EX<= ?   AND H.MEMBERNAME=? \n" +
                                                                                    "\n" +
                                                                                    "ORDER BY H.BOOKING_SEQ_NO DESC", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}) ,new SerializerReadClass(BookingSituationHallTableModel.HallStatusInfo.class)).list(new Object[]{ FrmDate , ToDate , MemID , FrmDate , ToDate , MemID});

            Booked_Hall_Status.bookedHallLength = Booked_Hall_Status.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookingSituationHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Hall_Status;
         
     }
     
     
     
     
      public List<BookingSituationHallTableModel.HallStatusInfo> getHallList(){
           if(status_data!=null)
        {
            return status_data;
        }
        else
            return new ArrayList<BookingSituationHallTableModel.HallStatusInfo>();
      }
      
     
     
     
      public static class HallStatusInfo implements SerializableRead,IKeyed {
          
          private String BOOKING_SEQ_NO;
          private String MEMBERNAME;
          private String MEM_NO;
          private int  STATUS ; 
          private String HALL_NAME;
          private String FLOOR ; 
          private int MEMBER_FLAG;
         
          private String BOOKING_DATE;
          private Date BOOKING_DATE_EX;
          private String TIMING_SLOTS;
          private Double CHARGES;
          private String CRBY;
          private Date CRDATE;
          
          private String REQ_APP_BY;
          private Date REQ_APP_DATE;
          
          private String LUXURYTAX_ID;
          private String TAX2_ID;
          private String TAX3_ID;
       
          private String NON_MEM_NAME;
          private String NON_MEM_CONTCT;
          private String NON_MEM_ADDR;
         
          private int FLAG;
          private int PAYMENT_FLAG;
          private int SLOT_FLAG;
          private int CHK_IN_FLAG;
          private Date AdvanceDate;
          private String PaymentRef;
          
          
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
          
           public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
          public String getCRDATE(){
              String d = Formats.TIMESTAMP.formatValue(CRDATE);
              return d;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE = CRDATE;
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
       
            public String getREQ_APP_BY(){
              return REQ_APP_BY;
          }
          public void setREQ_APP_BY(String REQ_APP_BY){
              this.REQ_APP_BY=REQ_APP_BY;
          }
          
          public String getREQ_APP_DATE(){
              String d = Formats.TIMESTAMP.formatValue(REQ_APP_DATE);
              return d;
          }
          public void setREQ_APP_DATE(Date REQ_APP_DATE){
              this.REQ_APP_DATE = REQ_APP_DATE;
          }
          
          
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
          }
          
          
     
          
          public Date getBOOKING_DATE_EX(){
              return BOOKING_DATE_EX;
          }
          public void setBOOKING_DATE_EX(Date BOOKING_DATE_EX){
              this.BOOKING_DATE_EX = BOOKING_DATE_EX;
          }
        
          public int getPAYMENT_FLAG(){
              return PAYMENT_FLAG;
          }
          public void setPAYMENT_FLAG(int PAYMENT_FLAG){
              this.PAYMENT_FLAG = PAYMENT_FLAG;
          }
        
          public int getSLOT_FLAG(){
              return SLOT_FLAG;
          }
          public void setSLOT_FLAG(int SLOT_FLAG){
              this.SLOT_FLAG=SLOT_FLAG;
          }
        
           public int getCHK_IN_FLAG(){
              return CHK_IN_FLAG;
          }
          public void setCHK_IN_FLAG(int CHK_IN_FLAG){
              this.CHK_IN_FLAG = CHK_IN_FLAG;
          }
          
          public String getAdvanceDate() throws BasicException{
             
              Date temp;
              temp = (Date) Formats.DATE.parseValue("10 Jan,2008 00:00:00");
              if(temp.after(AdvanceDate)){
                  return null;
              }
              else{
                   String d = Formats.TIMESTAMP.formatValue(AdvanceDate);
                   return d;
              }
             
             
          }
          public void setAdvanceDate(Date AdvanceDate){
              this.AdvanceDate=AdvanceDate;
          }
          
           public String getPaymentRef(){
              if(PaymentRef!=null && PaymentRef.length()>0){
                  return PaymentRef;
              }
              else{
                  return "";
              }
              
          }
          public void setPaymentRef(String PaymentRef){
              this.PaymentRef=PaymentRef;
          }
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          BOOKING_SEQ_NO = dr.getString(1);
          MEMBERNAME= dr.getString(2);
          MEM_NO= dr.getString(3);
          STATUS = dr.getInt(4);
          HALL_NAME = dr.getString(5);
          FLOOR  = dr.getString(6);
          MEMBER_FLAG = dr.getInt(7);
        
          BOOKING_DATE = dr.getString(8);
          TIMING_SLOTS = dr.getString(9);
          CHARGES = dr.getDouble(10);
          CRBY = dr.getString(11);
          CRDATE = dr.getTimestamp(12);
          
          LUXURYTAX_ID = dr.getString(13);
          TAX2_ID = dr.getString(14);
          TAX3_ID = dr.getString(15);
          NON_MEM_NAME = dr.getString(16);
          NON_MEM_CONTCT = dr.getString(17);
          NON_MEM_ADDR = dr.getString(18);
          REQ_APP_BY = dr.getString(19);
          REQ_APP_DATE = dr.getTimestamp(20);
          BOOKING_DATE_EX = dr.getTimestamp(21);
          FLAG = dr.getInt(22);
         
          PAYMENT_FLAG = dr.getInt(23);
          SLOT_FLAG = dr.getInt(24);
          CHK_IN_FLAG = dr.getInt(25);
          AdvanceDate = dr.getTimestamp(26);
          PaymentRef = dr.getString(27);
        }

        public Object getKey() {
           return this;
        }
          
    }
     
    
      
      
      // LOAD DATA FOR ROOMS...... 
      
      
            public static BookingSituationHallTableModel loadInstanceBooked_Rooms_Status(AppView app , Date FrmDate , Date ToDate)throws BasicException{
             BookingSituationHallTableModel Booked_Hall_Status = new BookingSituationHallTableModel(); 

              try{
                Booked_Hall_Status.Room_status_data = new ArrayList<BookingSituationHallTableModel.RoomStatusInfo>();
                Booked_Hall_Status.Room_status_data = new StaticSentence(app.getSession(), "SELECT R.BOOKING_SEQ_NO , (SELECT M.ROOMTYPE FROM GUESTROOM_MASTER M WHERE M.ID = R.ROOM_TYPE) , R.BOOKING_DATE_EX AS BOOKEDDATE ,\n" +
                                                                                            "C.NAME , C.SEARCHKEY , R.MEMBER_FLAG , R.STATUS , R.FLAG , R.ROOM_NOS , R.CRBY , R.CRDATE , R.BOOKING_DAYS ,\n" +
                                                                                            "R.NON_MEM_NAME , R.NON_MEM_CNTCT , R.NON_MEM_ADDR , R.CHARGES , R.LUXURYTAX , R.TAX2 , R.TAX3  ,\n" +
                                                                                            "R.REQ_APPROV_BY , R.REQ_APPROV_DATE , R.PAYMENT_FLAG , R.CHK_IN_FLAG , '2008-01-01 00:00:00' AS PAYMENTDATE , '' AS PAYMENTREF\n" +
                                                                                            " FROM guestroom_booked_details R , CUSTOMERS C\n" +
                                                                                            " WHERE C.ID = R.MEMBERNAME AND R.PAYMENT_FLAG=0 AND  R.BOOKING_DATE_EX >= ?  AND R.BOOKING_DATE_EX<= ?\n" +
                                                                                            "\n" +
                                                                                            "UNION\n" +
                                                                                            "\n" +
                                                                                            "SELECT R.BOOKING_SEQ_NO , (SELECT M.ROOMTYPE FROM GUESTROOM_MASTER M WHERE M.ID = R.ROOM_TYPE) , R.BOOKING_DATE_EX AS BOOKEDDATE ,\n" +
                                                                                            "C.NAME , C.SEARCHKEY , R.MEMBER_FLAG , R.STATUS , R.FLAG , R.ROOM_NOS , R.CRBY , R.CRDATE , R.BOOKING_DAYS ,\n" +
                                                                                            "R.NON_MEM_NAME , R.NON_MEM_CNTCT , R.NON_MEM_ADDR , R.CHARGES , R.LUXURYTAX , R.TAX2 , R.TAX3  ,\n" +
                                                                                            "R.REQ_APPROV_BY , R.REQ_APPROV_DATE , R.PAYMENT_FLAG , R.CHK_IN_FLAG , A.CRDATE AS PAYMENTDATE , A.ADVNCE_REF AS PAYMENTREF\n" +
                                                                                            " FROM guestroom_booked_details R , CUSTOMERS C , guestroom_advance_payment A\n" +
                                                                                            " WHERE C.ID = R.MEMBERNAME AND R.PAYMENT_FLAG=1  AND  R.BOOKING_DATE_EX >= ?  AND R.BOOKING_DATE_EX<= ?\n" +
                                                                                            "\n" +
                                                                                            "ORDER BY R.BOOKING_SEQ_NO DESC", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP}) ,new SerializerReadClass(BookingSituationHallTableModel.RoomStatusInfo.class)).list(new Object[]{ FrmDate , ToDate ,  FrmDate , ToDate});

                Booked_Hall_Status.bookedRoomLength = Booked_Hall_Status.Room_status_data.size();

            }
            catch(BasicException ex){

                Logger.getLogger(BookingSituationHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
            } 


             return Booked_Hall_Status;

         }
      
            
        // LOAD DATA WITH MEMBER NAME ....... FOR BOOKED ROOMS 
            
            public static BookingSituationHallTableModel loadInstanceBooked_Rooms_Status_MemID(AppView app , Date FrmDate , Date ToDate , String MemID)throws BasicException{
             BookingSituationHallTableModel Booked_Hall_Status = new BookingSituationHallTableModel(); 

              try{
                Booked_Hall_Status.Room_status_data = new ArrayList<BookingSituationHallTableModel.RoomStatusInfo>();
                Booked_Hall_Status.Room_status_data = new StaticSentence(app.getSession(), "SELECT R.BOOKING_SEQ_NO , (SELECT M.ROOMTYPE FROM GUESTROOM_MASTER M WHERE M.ID = R.ROOM_TYPE) , R.BOOKING_DATE_EX AS BOOKEDDATE ,\n" +
                                                                                                "C.NAME , C.SEARCHKEY , R.MEMBER_FLAG , R.STATUS , R.FLAG , R.ROOM_NOS , R.CRBY , R.CRDATE , R.BOOKING_DAYS ,\n" +
                                                                                                "R.NON_MEM_NAME , R.NON_MEM_CNTCT , R.NON_MEM_ADDR , R.CHARGES , R.LUXURYTAX , R.TAX2 , R.TAX3  ,\n" +
                                                                                                "R.REQ_APPROV_BY , R.REQ_APPROV_DATE , R.PAYMENT_FLAG , R.CHK_IN_FLAG , '2008-01-01 00:00:00' AS PAYMENTDATE , '' AS PAYMENTREF\n" +
                                                                                                " FROM guestroom_booked_details R , CUSTOMERS C\n" +
                                                                                                " WHERE C.ID = R.MEMBERNAME AND R.PAYMENT_FLAG=0 AND  R.BOOKING_DATE_EX >= ?  AND R.BOOKING_DATE_EX<= ?  AND R.MEMBERNAME=? \n" +
                                                                                                "\n" +
                                                                                                "UNION\n" +
                                                                                                "\n" +
                                                                                                "SELECT R.BOOKING_SEQ_NO , (SELECT M.ROOMTYPE FROM GUESTROOM_MASTER M WHERE M.ID = R.ROOM_TYPE) , R.BOOKING_DATE_EX AS BOOKEDDATE ,\n" +
                                                                                                "C.NAME , C.SEARCHKEY , R.MEMBER_FLAG , R.STATUS , R.FLAG , R.ROOM_NOS , R.CRBY , R.CRDATE , R.BOOKING_DAYS ,\n" +
                                                                                                "R.NON_MEM_NAME , R.NON_MEM_CNTCT , R.NON_MEM_ADDR , R.CHARGES , R.LUXURYTAX , R.TAX2 , R.TAX3  ,\n" +
                                                                                                "R.REQ_APPROV_BY , R.REQ_APPROV_DATE , R.PAYMENT_FLAG , R.CHK_IN_FLAG , A.CRDATE AS PAYMENTDATE , A.ADVNCE_REF AS PAYMENTREF\n" +
                                                                                                " FROM guestroom_booked_details R , CUSTOMERS C , guestroom_advance_payment A\n" +
                                                                                                " WHERE C.ID = R.MEMBERNAME AND R.PAYMENT_FLAG=1  AND  R.BOOKING_DATE_EX >= ?  AND R.BOOKING_DATE_EX<= ?  AND R.MEMBERNAME=?   \n" +
                                                                                                "\n" +
                                                                                                "ORDER BY R.BOOKING_SEQ_NO DESC", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}) ,new SerializerReadClass(BookingSituationHallTableModel.RoomStatusInfo.class)).list(new Object[]{ FrmDate , ToDate , MemID ,  FrmDate , ToDate , MemID});

                Booked_Hall_Status.bookedRoomLength = Booked_Hall_Status.Room_status_data.size();

            }
            catch(BasicException ex){
                     Logger.getLogger(BookingSituationHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
             } 
                     return Booked_Hall_Status;
         }
            
            
      public List<BookingSituationHallTableModel.RoomStatusInfo> getRoomList(){
           if(Room_status_data!=null)
        {
            return Room_status_data;
        }
        else
            return new ArrayList<BookingSituationHallTableModel.RoomStatusInfo>();
      } 
      
    
      
       public static class RoomStatusInfo implements SerializableRead,IKeyed {
          
          private String BOOKING_SEQ_NO;
          private String MEMBERNAME;
          private String MEM_NO;
          private int  STATUS ; 
          private String ROOMTYPE;
          private int MEMBER_FLAG;
          private Date BOOKING_DATE_EX;
          private Double CHARGES;
          private String CRBY;
          private Date CRDATE;
          private String REQ_APP_BY;
          private Date REQ_APP_DATE;
          private String LUXURYTAX_ID;
          private String TAX2_ID;
          private String TAX3_ID;
          private String NON_MEM_NAME;
          private String NON_MEM_CONTCT;
          private String NON_MEM_ADDR;
          private int FLAG;
          private int PAYMENT_FLAG;
          private int CHK_IN_FLAG;
          private int no_of_rooms;
          private int no_of_days;
          
           private Date AdvanceDate;
          private String PaymentRef;
          
          
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
          
          public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE = ROOMTYPE;
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
          
           public int getno_of_rooms(){
              return no_of_rooms;
          }
          public void setno_of_rooms(int no_of_rooms){
              this.no_of_rooms=no_of_rooms;
          }
          
           public int getno_of_days(){
              return no_of_days;
          }
          public void setno_of_days(int no_of_days){
              this.no_of_days=no_of_days;
          }
          
           public int getMem_flag(){
              return MEMBER_FLAG;
          }
          public void setMem_flag(int MEMBER_FLAG){
              this.MEMBER_FLAG=MEMBER_FLAG;
          }
          
         
          
          
           public Double getCHARGES(){
              return CHARGES;
          }
          public void setCHARGES(Double CHARGES){
              this.CHARGES=CHARGES;
          }
          
           public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
          public String getCRDATE(){
              String d = Formats.TIMESTAMP.formatValue(CRDATE);
              return d;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE = CRDATE;
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
       
            public String getREQ_APP_BY(){
              return REQ_APP_BY;
          }
          public void setREQ_APP_BY(String REQ_APP_BY){
              this.REQ_APP_BY=REQ_APP_BY;
          }
          
          public String getREQ_APP_DATE(){
              String d = Formats.TIMESTAMP.formatValue(REQ_APP_DATE);
              return d;
          }
          public void setREQ_APP_DATE(Date REQ_APP_DATE){
              this.REQ_APP_DATE = REQ_APP_DATE;
          }
          
          
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
          }
          
          
     
          
          public String getBOOKING_DATE_EX(){
              String d = Formats.DATE.formatValue(BOOKING_DATE_EX);
              return d;
          }
          public void setBOOKING_DATE_EX(Date BOOKING_DATE_EX){
              this.BOOKING_DATE_EX = BOOKING_DATE_EX;
          }
        
          public int getPAYMENT_FLAG(){
              return PAYMENT_FLAG;
          }
          public void setPAYMENT_FLAG(int PAYMENT_FLAG){
              this.PAYMENT_FLAG = PAYMENT_FLAG;
          }
        
       
        
           public int getCHK_IN_FLAG(){
              return CHK_IN_FLAG;
          }
          public void setCHK_IN_FLAG(int CHK_IN_FLAG){
              this.CHK_IN_FLAG = CHK_IN_FLAG;
          }
          
           public String getAdvanceDate() throws BasicException{
             
              Date temp;
              temp = (Date) Formats.DATE.parseValue("10 Jan,2008 00:00:00");
              if(temp.after(AdvanceDate)){
                  return null;
              }
              else{
                   String d = Formats.TIMESTAMP.formatValue(AdvanceDate);
                   return d;
              }
             
             
          }
          public void setAdvanceDate(Date AdvanceDate){
              this.AdvanceDate=AdvanceDate;
          }
          
           public String getPaymentRef(){
              if(PaymentRef!=null && PaymentRef.length()>0){
                  return PaymentRef;
              }
              else{
                  return "";
              }
              
          }
          public void setPaymentRef(String PaymentRef){
              this.PaymentRef=PaymentRef;
          }
          
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          BOOKING_SEQ_NO = dr.getString(1);
          ROOMTYPE = dr.getString(2);
          BOOKING_DATE_EX = dr.getTimestamp(3);
          MEMBERNAME= dr.getString(4);
          MEM_NO= dr.getString(5);
          MEMBER_FLAG = dr.getInt(6);
          STATUS = dr.getInt(7);
          FLAG = dr.getInt(8);
          no_of_rooms = dr.getInt(9);
          CRBY = dr.getString(10);
          CRDATE = dr.getTimestamp(11);
          no_of_days  = dr.getInt(12);
          NON_MEM_NAME = dr.getString(13);
          NON_MEM_CONTCT = dr.getString(14);
          NON_MEM_ADDR = dr.getString(15);
          CHARGES = dr.getDouble(16);
          
          LUXURYTAX_ID = dr.getString(17);
          TAX2_ID = dr.getString(18);
          TAX3_ID = dr.getString(19);
         
          REQ_APP_BY = dr.getString(20);
          REQ_APP_DATE = dr.getTimestamp(21);
         
          
          PAYMENT_FLAG = dr.getInt(22);
          CHK_IN_FLAG = dr.getInt(23);
           AdvanceDate = dr.getTimestamp(24);
          PaymentRef = dr.getString(25);
        }

        public Object getKey() {
           return this;
        }
          
    }
       
       
       
       
       
       
       
      
      
       
       
       
}
