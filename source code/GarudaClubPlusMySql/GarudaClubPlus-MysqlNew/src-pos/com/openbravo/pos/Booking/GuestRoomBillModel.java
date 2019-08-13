
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
import static com.openbravo.pos.Booking.CheckInTableModel.user;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GuestRoomBillModel extends BeanFactoryDataSingle{
    private Session s;
    private GuestRoomCheckInTable GRCTM;
  
    private List<GuestRoomBillModel.RoomAdvInfo> Room_Status_Data;
     
    //private List<GuestRoomBillModel.RoomTariffInfo> GR_Tariff_Info;
    private int Room_length;  
    public int RoomServ;
     static String user;
    
    @Override
    public void init(Session s) {
        this.s=s;
    }
    
     
    
      public static GuestRoomBillModel LoadGuestRoomCheckInDetail(AppView app, String Name , String Bill_ID)throws BasicException{
         GuestRoomBillModel Room_advance = new GuestRoomBillModel(); 
             user = app.getAppUserView().getUser().getName();
          try{
      
              Object o = new Object();
              
                Room_advance.Room_Status_Data = new ArrayList<GuestRoomBillModel.RoomAdvInfo>();
               
                o  =  new StaticSentence(app.getSession(), " SELECT Z.RECEIPT FROM bill Z , GUESTROOM_BILL G , guestroom_advance_payment A , guestroom_checkin C\n" +
                                                            "WHERE Z.PAID=1 AND Z.RECEIPT in (SELECT P.RECIEPT_NO FROM advnce_agnst_guestroom P WHERE P.BOOKING_ID=A.BOOKING_ID)\n" +
                                                            "AND G.PARENTID = C.ID AND G.ID=? AND C.ADVNCE_RECV_ID = A.ID", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Bill_ID);
                if(o!=null && o!="" && o.toString().trim().length()>0){
                      Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT G.ROOMTYPE , G.ROOM_NO , C.MEMNO , C.GUEST_N , G.NO_OF_ROOMS , G.NO_OF_DAYS , G.CHK_IN , G.CHK_OUT , G.ADVNCE_RECV , C.TOT_AMT , C.RM_SERV_CHRG , C.RECIEPT_NO,C.BILL_NAME, C.ID_CARD,\n" +
                                                                                            "A.MEMBERNAME , B.MEMBER_FLAG , G.RATE , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.LUXURYTAX),\n" +
                                                                                            "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.TAX2) ,\n" +
                                                                                            "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.TAX3),\n" +
                                                                                            "B.BASIC1 , B.BASIC2 , A.BAL_AMT , G.RM_SERV_ID , Z.ID , Z.CREATEDDATE ,  (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE Z.WAREHOUSE= L.ID) , Z.AMOUNT+Z.TAXTOTAL ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.LUXURYTAX) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.TAX2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.TAX3) , G.BOOKING_SEQ_NO , A.ADVNCE_REF , G.DISCOUNT , G.TAX1_AMT , G.TAX2_AMT , G.TAX3_AMT , G.TAX_TOTAL , G.ID \n" +
                                                                                            "FROM  guestroom_checkin C , guestroom_advance_payment A , guestroom_booked_details B , GUESTROOM_BILL G , BILL Z\n" +
                                                                                            "WHERE C.ADVNCE_RECV_ID = A.ID AND A.BOOKING_ID=B.ID AND G.PARENTID = C.ID AND\n" +
                                                                                            "Z.PAID=1 AND Z.RECEIPT in (SELECT P.RECIEPT_NO FROM advnce_agnst_guestroom P WHERE P.BOOKING_ID=A.BOOKING_ID) AND G.ID=?\n" +
                                                                                            "UNION\n" +
                                                                                            "SELECT G.ROOMTYPE , G.ROOM_NO , C.MEMNO , C.GUEST_N , G.NO_OF_ROOMS , G.NO_OF_DAYS , G.CHK_IN , G.CHK_OUT , G.ADVNCE_RECV , C.TOT_AMT , C.RM_SERV_CHRG , C.RECIEPT_NO,C.BILL_NAME, C.ID_CARD,\n" +
                                                                                            "A.MEMBERNAME , B.MEMBER_FLAG , G.RATE , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.LUXURYTAX),\n" +
                                                                                            "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.TAX2) ,\n" +
                                                                                            "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.TAX3),\n" +
                                                                                            "B.BASIC1 , B.BASIC2 , A.BAL_AMT , G.RM_SERV_ID , Z.ID , Z.CREATEDDATE ,  (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE Z.WAREHOUSE= L.ID) , Z.AMOUNT+Z.TAXTOTAL , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.LUXURYTAX) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.TAX2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.TAX3) , G.BOOKING_SEQ_NO , A.ADVNCE_REF , G.DISCOUNT  , G.TAX1_AMT , G.TAX2_AMT , G.TAX3_AMT  , G.TAX_TOTAL , G.ID \n" +
                                                                                            "FROM  guestroom_checkin C , guestroom_advance_payment A , guestroom_booked_details B , GUESTROOM_BILL G , BILL_ARV Z\n" +
                                                                                            "WHERE C.ADVNCE_RECV_ID = A.ID AND A.BOOKING_ID=B.ID AND G.PARENTID = C.ID AND\n" +
                                                                                            "Z.PAID=1 AND Z.RECEIPT in (SELECT P.RECIEPT_NO FROM advnce_agnst_guestroom P WHERE P.BOOKING_ID=A.BOOKING_ID) AND G.ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}) ,new SerializerReadClass(GuestRoomBillModel.RoomAdvInfo.class)).list(new Object[]{Bill_ID , Bill_ID });

                Room_advance.Room_length = Room_advance.Room_Status_Data.size();
                }
                else{
                
                    
                    Room_advance.Room_Status_Data = new StaticSentence(app.getSession(), "SELECT G.ROOMTYPE , G.ROOM_NO , C.MEMNO , C.GUEST_N , G.NO_OF_ROOMS , G.NO_OF_DAYS , G.CHK_IN , G.CHK_OUT , G.ADVNCE_RECV , C.TOT_AMT , C.RM_SERV_CHRG , C.RECIEPT_NO,C.BILL_NAME, C.ID_CARD,\n" +
                                                                                            "A.MEMBERNAME , B.MEMBER_FLAG , G.RATE , (SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.LUXURYTAX),\n" +
                                                                                            "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.TAX2) ,\n" +
                                                                                            "(SELECT T.RATE FROM TAXES T WHERE T.CATEGORY=B.TAX3),\n" +
                                                                                            "B.BASIC1 , B.BASIC2 , A.BAL_AMT , G.RM_SERV_ID ,\n" +
                                                                                            "'0' ,'2012-01-01 00:00:00' ,'0' ,\n" +
                                                                                            "'0'  , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.LUXURYTAX) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.TAX2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=B.TAX3) , G.BOOKING_SEQ_NO , A.ADVNCE_REF , G.DISCOUNT , G.TAX1_AMT , G.TAX2_AMT , G.TAX3_AMT , G.TAX_TOTAL  , G.ID \n" +
                                                                                            "FROM  guestroom_checkin C , guestroom_advance_payment A , guestroom_booked_details B , GUESTROOM_BILL G\n" +
                                                                                            "WHERE C.ADVNCE_RECV_ID = A.ID AND A.BOOKING_ID=B.ID AND G.PARENTID = C.ID    AND G.ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(GuestRoomBillModel.RoomAdvInfo.class)).list(new Object[]{Bill_ID });

                   
                    
                 Room_advance.Room_length = Room_advance.Room_Status_Data.size();
                    
                }
           
        }
        catch(BasicException ex){
            
            Logger.getLogger(GuestRoomBillModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return Room_advance;
     } 
    
    
      public List<GuestRoomBillModel.RoomAdvInfo> getGuestRoomPath()
       {
           if(Room_Status_Data!=null)
           {
               return Room_Status_Data;
           }
           else
               return new ArrayList<GuestRoomBillModel.RoomAdvInfo>();
       } 
     
      
      
      
      
      public  static class RoomAdvInfo implements SerializableRead,IKeyed {
      
          private String ID;
          private String ROOMTYPE;
          private String ROOMNO;
          private String MEMNO;
          private String GUEST_N;
          private int  ROOMS ; 
          private int DAYS;
          private Date CHK_IN ; 
          private Date CHK_OUT;
          private Double ADV_RECV;
          private Double TOT_AMT;
          private Double RM_SERV_CHRG;
          private String RECIEPT_NO;
          
          private String BILL_NAME;
          private String ID_CARD;
          private String MEMBERNAME;
          private int MEMBER_FLAG;
          private Double CHARGES;
          private Double TAX1;
          private Double TAX2;
          private Double TAX3;
          private int BASIC1;
          private int BASIC2;
          private Double BAL_AMT;
          private String ROOM_SERV_ID;
          private String bill_id;
          private Date RM_SER_BL_DT;
          private String place;
          private Double BAmount;
          private String N_TAX1;
          private String N_TAX2;
          private String N_TAX3;
          private String BOOKING_SEQ_NO;
          private String AdvanceRef;
          private Double Discount;
          private Double TAX1_AMT;
          private Double TAX2_AMT;
          private Double TAX3_AMT;
          private Double TAX_TOTAL;
          
          
          public String getBillNo(){
              return ID;
          }
          public void setBillNo(String Id){
              this.ID=Id;
          }
          public String getROOMTYPE(){
              
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE=ROOMTYPE;
          }
           public String getROOMNO(){
              return ROOMNO;
          }
          public void setROOMNO(String ROOMNO){
              this.ROOMNO=ROOMNO;
          } 
          public String getMEMBERNAME(){
              return MEMBERNAME;
          }
          public void setMEMBERNAME(String MEMBERNAME){
              this.MEMBERNAME = MEMBERNAME;
          }
          
           public String getMEMNO(){
              return MEMNO;
          }
          public void setMEMNO(String MEMNO){
              this.MEMNO = MEMNO;
          }
         public Double getTOT_AMT(){
             return TOT_AMT;
         }
         public void setTOT_AMT(Double TOT_AMT){
             this.TOT_AMT = TOT_AMT;
         }
         public Double getADV_RECV(){
             return ADV_RECV;
         }
         public void setADV_RECV(Double ADV_RECV){
             this.ADV_RECV = ADV_RECV;
         }
         
        public String getRECIEPT_NO(){
              return RECIEPT_NO;
          }
          public void setRECIEPT_NO(String RECIEPT_NO){
              this.RECIEPT_NO = RECIEPT_NO;
          }
          
          public int getNO_OF_ROOMS(){
              return ROOMS;
          }
          public void setNO_OF_ROOMS(int ROOMS){
              this.ROOMS = ROOMS;
          }
          public int getBOOKING_DAYS(){
              return DAYS;
          }
          public void setBOOKING_DAYS(int DAYS){
              this.DAYS = DAYS;
          }
          
           public int getMEMBER_FLAG(){
              return MEMBER_FLAG;
          }
          public void setMEMBER_FLAG(int MEMBER_FLAG){
              this.MEMBER_FLAG = MEMBER_FLAG;
          }
           public String getGuestName(){
              return GUEST_N;
          }
          public void setGuestName(String GUEST_N){
              this.GUEST_N = GUEST_N;
          }
         
          public String getCurrDATE(){
              Date d = new Date();
              String x =d.toString();
              return x;
          }
          
          
          public Double getTariff(){
              return CHARGES;
          }
          public void setMEM_Tariff(Double CHARGES){
              this.CHARGES=CHARGES;
          }
          
        
           public Double getTAX1(){
              if(TAX1!=null){
                 return TAX1; 
              }
              else{
                  return 0.00;
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
                  return 0.00;
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
                  return 0.00;
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
         
          public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2 = BASIC2;
          }
         
          public String getUser(){
             return user;
          }
          
          public Double getRM_SERV_CHRG(){
              return RM_SERV_CHRG;
          }
          public void setRM_SERV_CHRG(Double RM_SERV_CHRG){
              this.RM_SERV_CHRG = RM_SERV_CHRG;
          }
          
           public String getBILL_NAME(){
              return BILL_NAME;
          }
          public void setBILL_NAME(String BILL_NAME){
              this.BILL_NAME = BILL_NAME;
          }
          
           public String getID_CARD(){
              return ID_CARD;
          }
          public void setID_CARD(String ID_CARD){
              this.ID_CARD = ID_CARD;
          }
          public String getCHK_IN(){
             String x1 = Formats.TIMESTAMP.formatValue(CHK_IN);
             return x1;
          }
          public void setCHK_IN(Date CHK_IN){
              this.CHK_IN = CHK_IN;
          }
          public String getCHK_OUT(){
             String x1 = Formats.TIMESTAMP.formatValue(CHK_OUT);
             return x1;
          }
          public void setCHK_OUT(Date CHK_OUT){
              this.CHK_OUT = CHK_OUT;
          }
          public Double getBAL_AMT(){
              return BAL_AMT;
          }
          public void setBAL_AMT(Double BAL_AMT){
              this.BAL_AMT = BAL_AMT;
          }
           public String getROOM_SERV_ID(){
            
             return ROOM_SERV_ID;
          }
          public void setROOM_SERV_ID(String ROOM_SERV_ID){
              this.ROOM_SERV_ID = ROOM_SERV_ID;
          }
          
          public String getbill_id(){
            
             return bill_id;
          }
          public void setbill_id(String bill_id){
              this.bill_id = bill_id;
          }
         public String getplace(){
            
             return place;
          }
          public void setplace(String place){
              this.place = place;
          }
          public Double getBAmount(){
              return BAmount;
          }
          public void setBAmount(Double BAmount){
              this.BAmount = BAmount;
          }
          
           public String getN_TAX1(){
              
              return N_TAX1;
          }
          public void setN_TAX1(String N_TAX1){
              this.N_TAX1=N_TAX1;
          }
           public String getN_TAX2(){
              
              return N_TAX2;
          }
          public void setN_TAX2(String N_TAX2){
              this.N_TAX2=N_TAX2;
          }
           public String getN_TAX3(){
              
              return N_TAX3;
          }
          public void setN_TAX3(String N_TAX3){
              this.N_TAX3=N_TAX3;
          }
         
          public String getRM_SER_BL_DT(){
             String x1 = Formats.DATE.formatValue(RM_SER_BL_DT);
             return x1;
          }
          public void setRM_SER_BL_DT(Date RM_SER_BL_DT){
              this.RM_SER_BL_DT = RM_SER_BL_DT;
          }
          
          public String getBOOKING_SEQ_NO(){
            return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO = BOOKING_SEQ_NO;
          }
          public String getAdvanceRef(){
              return AdvanceRef;
          }
          public void setAdvanceRef(String AdvanceRef){
              this.AdvanceRef=AdvanceRef;
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
           public Double getTAX_TOTAL(){
              return TAX_TOTAL;
          }
          public void setTAX_TOTAL(Double TAX_TOTAL){
              this.TAX_TOTAL=TAX_TOTAL;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            ROOMTYPE = dr.getString(1);
            ROOMNO = dr.getString(2);
            MEMNO = dr.getString(3);
            GUEST_N = dr.getString(4);
            ROOMS = dr.getInt(5);
            DAYS = dr.getInt(6);
            CHK_IN=dr.getTimestamp(7);
            CHK_OUT = dr.getTimestamp(8);
            ADV_RECV = dr.getDouble(9);
            TOT_AMT = dr.getDouble(10);
            RM_SERV_CHRG = dr.getDouble(11);
            RECIEPT_NO = dr.getString(12);
            BILL_NAME = dr.getString(13);
            ID_CARD = dr.getString(14);
            MEMBERNAME = dr.getString(15);
            MEMBER_FLAG = dr.getInt(16);
            
            CHARGES = dr.getDouble(17);
            TAX1 = dr.getDouble(18);
            TAX2 = dr.getDouble(19);
            TAX3= dr.getDouble(20);
            BASIC1 = dr.getInt(21);
            BASIC2 = dr.getInt(22);
            BAL_AMT = dr.getDouble(23);
            ROOM_SERV_ID = dr.getString(24);
            bill_id = dr.getString(25);
            RM_SER_BL_DT = dr.getTimestamp(26);
            place = dr.getString(27);
            BAmount = dr.getDouble(28);
            N_TAX1 = dr.getString(29);
            N_TAX2 = dr.getString(30);
            N_TAX3 = dr.getString(31);
            BOOKING_SEQ_NO = dr.getString(32);
            AdvanceRef = dr.getString(33);
            Discount = dr.getDouble(34);
            TAX1_AMT = dr.getDouble(35);
            TAX2_AMT = dr.getDouble(36);
            TAX3_AMT = dr.getDouble(37);
            TAX_TOTAL = dr.getDouble(38);
            ID = dr.getString(39);
            
            
        }

        public Object getKey() {
           return this;
        }
     }
      
      
      
     
}
