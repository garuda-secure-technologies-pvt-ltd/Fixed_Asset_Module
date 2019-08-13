
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
import static com.openbravo.pos.Booking.GuestRoomBillModel.user;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HallBillModel extends BeanFactoryDataSingle{
    private Session s;
    private List<HallBillModel.HallBillInfo> Hall_Bill_Data;
   
    private int Hall_length;  
    public int HallServ;
    static String user;
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
      public static HallBillModel LoadHallCheckInDetail(AppView app, String Bill_ID)throws BasicException{
         HallBillModel hall_info = new HallBillModel(); 
             user = app.getAppUserView().getUser().getName();
          try{
      
              Object o = new Object();
              
                hall_info.Hall_Bill_Data = new ArrayList<HallBillModel.HallBillInfo>();
               
              
              
               hall_info.Hall_Bill_Data = new StaticSentence(app.getSession(), "SELECT B.ID , B.CUSTOMER , B.HALL_NAME , B.SLOT_TIME , B.CHECKIN_DATE ,B.RATE , B.TAX_TOTAL , B.HALL_SERV_ID ,\n" +
                                                                                    "B.HALL_SERV_AMT  , B.RECIEPT ,  B.ADVANCE_RECV , B.CHECK_IN_ID , B.TAX1 , B.TAX2 , B.TAX3 , B.BILLNAME ,\n" +
                                                                                    " B.ID_DETAIL , B.CRBY , B.CRDATE ,B.SLOT_BOOKED , C.MEMBERNAME , C.MEMBERNO , C.GUESTNAME,\n" +
                                                                                    "(SELECT NAME FROM TAXCATEGORIES TC WHERE TC.ID=C.TAX1) , (SELECT NAME FROM TAXCATEGORIES TC WHERE TC.ID=C.TAX2) ,\n" +
                                                                                    " (SELECT NAME FROM TAXCATEGORIES TC WHERE TC.ID=C.TAX3) , D.BASIC , D.BASIC2 , A.BOOKING_SEQ_NO , B.DISCOUNT , B.TAX1_AMT , B.TAX2_AMT , B.TAX3_AMT \n" +
                                                                                    "  FROM hall_bill B , hall_check_in C , hall_advance_payment A ,hall_booked_details D\n" +
                                                                                    "  WHERE B.CHECK_IN_ID=C.ID   AND C.ADVNCE_ID = A.ID AND A.BOOKING_ID=D.ID AND B.ID = ?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}) ,new SerializerReadClass(HallBillModel.HallBillInfo.class)).list(new Object[]{ Bill_ID });

                hall_info.Hall_length = hall_info.Hall_Bill_Data.size();
               
           
        }
        catch(BasicException ex){
            
            Logger.getLogger(GuestRoomBillModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return hall_info;
     } 
    
    
    
    
    
      
      
        public List<HallBillModel.HallBillInfo> getHallInfoList()
       {
           if(Hall_Bill_Data!=null)
           {
               return Hall_Bill_Data;
           }
           else
               return new ArrayList<HallBillModel.HallBillInfo>();
       } 
      
      
      
      
      
      
       public  static class HallBillInfo implements SerializableRead,IKeyed {
      
          private String CUSTOMER;
          private String HALL_NAME;
          private String MEMBERNO;
          private String MEMBERNAME;
          private String GUESTNAME;
          private String  SLOT_TIME ; 
          private Date CHECKIN_DATE;
          private Double RATE ; 
          private Double TAX_TOTAL;
          private String HALL_SERV_ID;
          private Double HALL_SERV_AMT;
          private String RECIEPT_NO;
          private Double ADVANCE_RECV;
          
          private String CHECK_IN_ID;
          private Double TAX1_RATE;
          private Double TAX2_RATE;
          private Double TAX3_RATE;
          private String BILLNAME;
          private String TAX1_NAME;
          private String TAX2_NAME;
          private String TAX3_NAME;
          private String ID_DETAIL;
          private String CRBY;
          private Date CRDATE;
          private String SLOT_BOOKED;
          private String BILL_ID;
          private int BASIC1;
          private int BASIC2;
          private String BOOKING_SEQ_NO;
          private Double Discount;
           private Double TAX1_AMT;
          private Double TAX2_AMT;
          private Double TAX3_AMT;
          
          
          
          
          
          public String getBILL_ID(){
              return BILL_ID;
          }
          public void setId(String BILL_ID){
              this.BILL_ID=BILL_ID;
          }
          public String getCUSTOMER(){
              
              return CUSTOMER;
          }
          public void setCUSTOMER(String CUSTOMER){
              this.CUSTOMER=CUSTOMER;
          }
          
           public String getHALL_NAME(){
              return HALL_NAME;
          }
          public void setHALL_NAME(String HALL_NAME){
              this.HALL_NAME=HALL_NAME;
          } 
          public String getMEMBERNAME(){
              return MEMBERNAME;
          }
          public void setMEMBERNAME(String MEMBERNAME){
              this.MEMBERNAME = MEMBERNAME;
          }
          
           public String getMEMBERNO(){
              return MEMBERNO;
          }
          public void setMEMBERNO(String MEMBERNO){
              this.MEMBERNO = MEMBERNO;
          }
         public String getGUESTNAME(){
             return GUESTNAME;
         }
         public void setGUESTNAME(String GUESTNAME){
             this.GUESTNAME = GUESTNAME;
         }
         
         public String getSLOT_TIME(){
             return SLOT_TIME;
         }
         public void setSLOT_TIME(String SLOT_TIME){
             this.SLOT_TIME = SLOT_TIME;
         }
         
        public String getRECIEPT_NO(){
              return RECIEPT_NO;
          }
          public void setRECIEPT_NO(String RECIEPT_NO){
              this.RECIEPT_NO = RECIEPT_NO;
          }
          
         public String getCHECKIN_DATE(){
             String x1 = Formats.DATE.formatValue(CHECKIN_DATE);
             return x1;
          }
          public void setCHECKIN_DATE(Date CHECKIN_DATE){
              this.CHECKIN_DATE = CHECKIN_DATE;
          }
          public Double getRATE(){
              return RATE;
          }
          public void setRATE(Double RATE){
              this.RATE = RATE;
          }
          
           public Double getTAX_TOTAL(){
              return TAX_TOTAL;
          }
          public void setTAX_TOTAL(Double TAX_TOTAL){
              this.TAX_TOTAL = TAX_TOTAL;
          }
           public String getHALL_SERV_ID(){
              return HALL_SERV_ID;
          }
          public void setHALL_SERV_ID(String HALL_SERV_ID){
              this.HALL_SERV_ID = HALL_SERV_ID;
          }
         
          public String getCurrDATE(){
              Date d = new Date();
              String x =Formats.TIMESTAMP.formatValue(d);
              return x;
          }
          
          
          public Double getHALL_SERV_AMT(){
              return HALL_SERV_AMT;
          }
          public void setHALL_SERV_AMT(Double HALL_SERV_AMT){
              this.HALL_SERV_AMT=HALL_SERV_AMT;
          }
          
        
           public Double getTAX1_RATE(){
              if(TAX1_RATE!=null){
                 return TAX1_RATE; 
              }
              else{
                  return 0.00;
              }
          }
          public void setTAX1_RATE(Double TAX1_RATE){
              this.TAX1_RATE=TAX1_RATE;
          }
          
          
           public Double getTAX2_RATE(){
             if(TAX2_RATE!=null){
                 return TAX2_RATE; 
              }
              else{
                  return 0.00;
              }
          }
          public void setTAX2_RATE(Double TAX2_RATE){
              this.TAX2_RATE=TAX2_RATE;
          }
          
          public Double getTAX3_RATE(){
             if(TAX3_RATE!=null){
                 return TAX3_RATE; 
              }
              else{
                  return 0.00;
              }
          }
          
          
          public void setTAX3_RATE(Double TAX3_RATE){
              this.TAX3_RATE=TAX3_RATE;
          }
          
          public int getBASIC1(){
              return BASIC1;
          }
          public void setBASIC1(int BASIC1){
              this.BASIC1 = BASIC1;
          }
         
          public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2 = BASIC2;
          }
         
          public String getUser(){
             return user;
          }
          
          public Double getADVANCE_RECV(){
              return ADVANCE_RECV;
          }
          public void setADVANCE_RECV(Double ADVANCE_RECV){
              this.ADVANCE_RECV = ADVANCE_RECV;
          }
          
           public String getBILL_NAME(){
              return BILLNAME;
          }
          public void setBILL_NAME(String BILLNAME){
              this.BILLNAME = BILLNAME;
          }
          
           public String getID_CARD(){
              return ID_DETAIL;
          }
          public void setID_CARD(String ID_DETAIL){
              this.ID_DETAIL = ID_DETAIL;
          }
         
         
          public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY = CRBY;
          }
          
           public Date getCRDATE(){
            
             return CRDATE;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE = CRDATE;
          }
          
          public String getSLOT_BOOKED(){
            
             return SLOT_BOOKED;
          }
          public void setSLOT_BOOKED(String SLOT_BOOKED){
              this.SLOT_BOOKED = SLOT_BOOKED;
          }
          
          
       
         
          public String getTAX1_NAME(){
              
              return TAX1_NAME;
          }
          public void setTAX1_NAME(String TAX1_NAME){
              this.TAX1_NAME=TAX1_NAME;
          }
           public String getTAX2_NAME(){
              
              return TAX2_NAME;
          }
          public void setTAX2_NAME(String TAX2_NAME){
              this.TAX2_NAME=TAX2_NAME;
          }
           public String getTAX3_NAME(){
              
              return TAX3_NAME;
          }
          public void setTAX3_NAME(String TAX3_NAME){
              this.TAX3_NAME=TAX3_NAME;
          }
         
          public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          
           public Double getDiscount(){
              return Discount;
          }
          public void setDiscount(Double Discount){
              this.Discount=Discount;
          }
         
           public Double getTAX1_AMT(){
              return TAX1_AMT;
          }
          public void setTAX1_AMT(Double TAX1_AMT){
              this.TAX1_AMT=TAX1_AMT;
          }
          public Double getTAX2_AMT(){
              return TAX2_AMT;
          }
          public void setTAX2_AMT(Double TAX2_AMT){
              this.TAX2_AMT=TAX2_AMT;
          }
          public Double getTAX3_AMT(){
              return TAX3_AMT;
          }
          public void setTAX3_AMT(Double TAX3_AMT){
              this.TAX3_AMT=TAX3_AMT;
          }
          
          
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            BILL_ID = dr.getString(1);
            CUSTOMER = dr.getString(2);
            HALL_NAME = dr.getString(3);
            SLOT_TIME = dr.getString(4);
            CHECKIN_DATE = dr.getTimestamp(5);
            RATE = dr.getDouble(6);
            TAX_TOTAL=dr.getDouble(7);
            HALL_SERV_ID = dr.getString(8);
            HALL_SERV_AMT = dr.getDouble(9);
            RECIEPT_NO = dr.getString(10);
            ADVANCE_RECV = dr.getDouble(11);
            CHECK_IN_ID = dr.getString(12);
            TAX1_RATE = dr.getDouble(13);
            TAX2_RATE = dr.getDouble(14);
            TAX3_RATE = dr.getDouble(15);
            BILLNAME = dr.getString(16);
            
            ID_DETAIL = dr.getString(17);
            CRBY = dr.getString(18);
            CRDATE = dr.getTimestamp(19);
            SLOT_BOOKED= dr.getString(20);
            MEMBERNAME = dr.getString(21);
            MEMBERNO = dr.getString(22);
            GUESTNAME = dr.getString(23);
            TAX1_NAME = dr.getString(24);
            TAX2_NAME = dr.getString(25);
            TAX3_NAME = dr.getString(26);
            BASIC1 = dr.getInt(27);
            BASIC2 = dr.getInt(28);
            BOOKING_SEQ_NO = dr.getString(29);
            Discount = dr.getDouble(30);
            TAX1_AMT = dr.getDouble(31);
            TAX2_AMT = dr.getDouble(32);
            TAX3_AMT = dr.getDouble(33);
            
            
            
        } 

        public Object getKey() {
           return this;
        }
     }
      
      
      
    
    
    
    
}
