
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CancellationReportTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<CancellationReportTableModel.HallAdvInfo> hall_data; 
    private List<CancellationReportTableModel.RoomAdvInfo> Room_Data; 
    int Hall_length;
    int RoomLength;
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static CancellationReportTableModel loadHallCancelledDetails_All(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         CancellationReportTableModel hallDetails = new CancellationReportTableModel(); 
         
          try{
            hallDetails.hall_data = new ArrayList<CancellationReportTableModel.HallAdvInfo>();
            hallDetails.hall_data = new StaticSentence(app.getSession(), "SELECT B.BOOKING_SEQ_NO , B.MEM_NO , C.NAME , B.BOOKING_DATE_EX , B.BOOKING_SLOT , B.REQ_CANCEL_BY , B.REQ_CANCEL_DATE ,\n" +
                                                                            "R.BILLED_AMT , R.ADVNCE_AMT , R.REFUND_AMT , M.NAME\n" +
                                                                            "FROM hall_cancelled_details  B , CUSTOMERS C , room_hall_refund_voucher R , HALL_MASTER M\n" +
                                                                            "WHERE C.ID = B.MEMBERNAME AND R.BOOKING_SEQ_NO = B.BOOKING_SEQ_NO AND M.ID = B.HALL_NAME AND B.REQ_CANCEL_DATE>= ? AND B.REQ_CANCEL_DATE<=?   "
                                                                                        ,   new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   })
                                                                                        ,new SerializerReadClass(CancellationReportTableModel.HallAdvInfo.class)).list(new Object[]{ FrmDate ,  ToDate });

            hallDetails.Hall_length = hallDetails.hall_data.size();
            
        }
        catch(BasicException ex){
            Logger.getLogger(CancellationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return hallDetails;
     }
    
    // LOAD HALL CANCELLATION DETAILS WITH SPECIFIC HALL TYPE -------------------------------------------------------------------------------------------------------------------------------- @AAKASH
     public static CancellationReportTableModel loadHallCancelledDetails_HallName(AppView app , Date FrmDate , Date ToDate , String HallNameID)throws BasicException{
         CancellationReportTableModel hallDetails = new CancellationReportTableModel(); 
         
          try{
            hallDetails.hall_data = new ArrayList<CancellationReportTableModel.HallAdvInfo>();
            hallDetails.hall_data = new StaticSentence(app.getSession(), "SELECT B.BOOKING_SEQ_NO , B.MEM_NO , C.NAME , B.BOOKING_DATE_EX , B.BOOKING_SLOT , B.REQ_CANCEL_BY , B.REQ_CANCEL_DATE ,\n" +
                                                                            "R.BILLED_AMT , R.ADVNCE_AMT , R.REFUND_AMT , M.NAME\n" +
                                                                            "FROM hall_cancelled_details  B , CUSTOMERS C , room_hall_refund_voucher R , HALL_MASTER M\n" +
                                                                            "WHERE C.ID = B.MEMBERNAME AND R.BOOKING_SEQ_NO = B.BOOKING_SEQ_NO AND M.ID = B.HALL_NAME AND B.REQ_CANCEL_DATE>= ? AND B.REQ_CANCEL_DATE<=?  AND M.NAME=?  "
                                                                                        ,   new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   , Datas.STRING })
                                                                                        ,new SerializerReadClass(CancellationReportTableModel.HallAdvInfo.class)).list(new Object[]{ FrmDate ,  ToDate  , HallNameID});

            hallDetails.Hall_length = hallDetails.hall_data.size();
            
        }
        catch(BasicException ex){
            Logger.getLogger(CancellationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return hallDetails;
     }
    
     
     
     
     
     
     
     
     
      public List<CancellationReportTableModel.HallAdvInfo> getHallList(){
           if(hall_data!=null)
        {
            return hall_data;
        }
        else
            return new ArrayList<CancellationReportTableModel.HallAdvInfo>();
      }  
     
     
     
     
     
     
      public static class HallAdvInfo implements SerializableRead,IKeyed {
      
          private String booking_seq_no;
          private Date CHECK_IN_DATE;
          
          private String MEMBERNAME;
          private String  MEMBER_NO ; 
          private Double Billed_amt;
          private Double ADVANCE_RECV ; 
          
         
         
         
         
          private String HALLNAME;
          private Date ADVANCE_PMT_DT;
          private String BOOKING_SLOT;
          private Double Refund_amt;
          private String RefundBy;
          private Date Refund_Date;
          
          
          
          public String getbooking_seq_no(){
              return booking_seq_no;
          }
          public void setbooking_seq_no(String booking_seq_no){
              this.booking_seq_no=booking_seq_no;
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
         public Double getBilled_amt(){
             return Billed_amt;
         }
         public void setBilled_amt(Double Billed_amt){
             this.Billed_amt = Billed_amt;
         }
         public Double getADVANCE_RECV(){
             return ADVANCE_RECV;
         }
         public void setADVANCE_RECV(Double ADVANCE_RECV){
             this.ADVANCE_RECV = ADVANCE_RECV;
         }
         
      
          
         
          public String getHALLNAME(){
              return HALLNAME;
          }
          public void setHALLNAME(String HALLNAME){
              this.HALLNAME = HALLNAME;
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
         
        
          public Double getRefund_amt(){
             return Refund_amt;
         }
         public void setRefund_amt(Double Refund_amt){
             this.Refund_amt = Refund_amt;
         }
          
         public String getRefundBy(){
             return RefundBy;
         }
         public void setRefundBy(String RefundBy){
             this.RefundBy=RefundBy;
         }
         public String getRefund_Date(){
             String x = Formats.DATE.formatValue(Refund_Date);
             return x;
         }
         public void setRefund_Date(Date Refund_Date){
             this.Refund_Date=Refund_Date;
         }
         
         
          
        public void readValues(DataRead dr) throws BasicException {
           
            booking_seq_no = dr.getString(1);
            MEMBER_NO = dr.getString(2);
            MEMBERNAME = dr.getString(3);
            CHECK_IN_DATE = dr.getTimestamp(4);
            BOOKING_SLOT = dr.getString(5);
            RefundBy = dr.getString(6);
            Refund_Date = dr.getTimestamp(7);
            Billed_amt = dr.getDouble(8);
            ADVANCE_RECV=dr.getDouble(9);
            Refund_amt = dr.getDouble(10);
            HALLNAME = dr.getString(11);
            
            
            
        }

        public Object getKey() {
           return this;
        }
        
      }
      
     // LOAD DETIALS FOR ROOMS ....................................................................................................................................................
      
      
       public static CancellationReportTableModel loadRoomCancelledDetaiLS_All(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         CancellationReportTableModel RoomDetails = new CancellationReportTableModel(); 
         
          try{
            RoomDetails.Room_Data = new ArrayList<CancellationReportTableModel.RoomAdvInfo>();
            RoomDetails.Room_Data = new StaticSentence(app.getSession(), "SELECT B.BOOKING_SEQ_NO , B.MEMBER_NO , C.NAME , B.BOOKING_DATE_EX , B.ROOM_NOS , B.REQ_CAN_BY , B.REQ_CAN_DATE ,\n" +
                                                                        "R.BILLED_AMT , R.ADVNCE_AMT , R.REFUND_AMT , M.ROOMTYPE , B.BOOKING_DAYS\n" +
                                                                        "FROM guestroom_cancelled_details  B , CUSTOMERS C , room_hall_refund_voucher R , guestroom_master M\n" +
                                                                        "WHERE C.ID = B.MEMBERNAME AND R.BOOKING_SEQ_NO = B.BOOKING_SEQ_NO AND M.ID = B.ROOM_TYPE AND B.REQ_CAN_DATE >= ? AND B.REQ_CAN_DATE<= ? "
                                                                          ,  new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   })
                                                                           ,new SerializerReadClass(CancellationReportTableModel.RoomAdvInfo.class)).list(new Object[]{ FrmDate ,  ToDate });

            RoomDetails.RoomLength = RoomDetails.Room_Data.size();
            
        }
        catch(BasicException ex){
            Logger.getLogger(CancellationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return RoomDetails;
     }
      
     // LOAD  ROOM CANCELLED DETAILS FOR SPECIFIC ROOMTYPE ---------------------------------------------------------------------------------------------------------------------
         
       
       public static CancellationReportTableModel loadRoomCancelledDetaiLS_RoomType(AppView app , Date FrmDate , Date ToDate , String RoomType)throws BasicException{
         CancellationReportTableModel RoomDetails = new CancellationReportTableModel(); 
         
          try{
            RoomDetails.Room_Data = new ArrayList<CancellationReportTableModel.RoomAdvInfo>();
            RoomDetails.Room_Data = new StaticSentence(app.getSession(), "SELECT B.BOOKING_SEQ_NO , B.MEMBER_NO , C.NAME , B.BOOKING_DATE_EX , B.ROOM_NOS , B.REQ_CAN_BY , B.REQ_CAN_DATE ,\n" +
                                                                        "R.BILLED_AMT , R.ADVNCE_AMT , R.REFUND_AMT , M.ROOMTYPE , B.BOOKING_DAYS\n" +
                                                                        "FROM guestroom_cancelled_details  B , CUSTOMERS C , room_hall_refund_voucher R , guestroom_master M\n" +
                                                                        "WHERE C.ID = B.MEMBERNAME AND R.BOOKING_SEQ_NO = B.BOOKING_SEQ_NO AND M.ID = B.ROOM_TYPE AND B.REQ_CAN_DATE >= ? AND B.REQ_CAN_DATE<= ? AND M.ROOMTYPE=?"
                                                                          ,  new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING  })
                                                                           ,new SerializerReadClass(CancellationReportTableModel.RoomAdvInfo.class)).list(new Object[]{ FrmDate ,  ToDate , RoomType});

            RoomDetails.RoomLength = RoomDetails.Room_Data.size();
            
        }
        catch(BasicException ex){
            Logger.getLogger(CancellationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return RoomDetails;
     }
       
       
       
        public List<CancellationReportTableModel.RoomAdvInfo> getRoomSize(){
           if(Room_Data!=null)
        {
            return Room_Data;
        }
        else
            return new ArrayList<CancellationReportTableModel.RoomAdvInfo>();
      }  
     
       
       
       
       
       
       
         public static class RoomAdvInfo implements SerializableRead,IKeyed {
      
          private String booking_seq_no;
          private Date CHECK_IN_DATE;
          
          private String MEMBERNAME;
          private String  MEMBER_NO ; 
          private Double Billed_amt;
          private Double ADVANCE_RECV ; 
          private String ROOMTYPE;
          private Date ADVANCE_PMT_DT;
          private int roomNos;
          private Double Refund_amt;
          private String RefundBy;
          private Date Refund_Date;
          private int No_OF_Days;
          
          
          public String getbooking_seq_no(){
              return booking_seq_no;
          }
          public void setbooking_seq_no(String booking_seq_no){
              this.booking_seq_no=booking_seq_no;
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
         public Double getBilled_amt(){
             return Billed_amt;
         }
         public void setBilled_amt(Double Billed_amt){
             this.Billed_amt = Billed_amt;
         }
         public Double getADVANCE_RECV(){
             return ADVANCE_RECV;
         }
         public void setADVANCE_RECV(Double ADVANCE_RECV){
             this.ADVANCE_RECV = ADVANCE_RECV;
         }
         
      
          
         
          public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE = ROOMTYPE;
          }
          
       
          public String getADVANCE_PMT_DT(){
              String x = Formats.DATE.formatValue(ADVANCE_PMT_DT);
              return x;
          }
          public void ADVANCE_PMT_DT(Date ADVANCE_PMT_DT){
              this.ADVANCE_PMT_DT = ADVANCE_PMT_DT;
          }
          public int getroomNos(){
              return roomNos;
          }
          public void setroomNos(int roomNos){
              this.roomNos=roomNos;
          }
          public int getroomNo_OF_Days(){
              return No_OF_Days;
          }
          public void setNo_OF_Days(int No_OF_Days){
              this.No_OF_Days=No_OF_Days;
          }
        
          public Double getRefund_amt(){
             return Refund_amt;
         }
         public void setRefund_amt(Double Refund_amt){
             this.Refund_amt = Refund_amt;
         }
          
         public String getRefundBy(){
             return RefundBy;
         }
         public void setRefundBy(String RefundBy){
             this.RefundBy=RefundBy;
         }
         public String getRefund_Date(){
             String x = Formats.DATE.formatValue(Refund_Date);
             return x;
         }
         public void setRefund_Date(Date Refund_Date){
             this.Refund_Date=Refund_Date;
         }
         
         
          
        public void readValues(DataRead dr) throws BasicException {
           
            booking_seq_no = dr.getString(1);
            MEMBER_NO = dr.getString(2);
            MEMBERNAME = dr.getString(3);
            CHECK_IN_DATE = dr.getTimestamp(4);
            roomNos = dr.getInt(5);
            RefundBy = dr.getString(6);
            Refund_Date = dr.getTimestamp(7);
            Billed_amt = dr.getDouble(8);
            ADVANCE_RECV=dr.getDouble(9);
            Refund_amt = dr.getDouble(10);
            ROOMTYPE = dr.getString(11);
            No_OF_Days = dr.getInt(12);
            
            
        }

        public Object getKey() {
           return this;
        }
        
      }
       
       
       
       
      
      
     
}
