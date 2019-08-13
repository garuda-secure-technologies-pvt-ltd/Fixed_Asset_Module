
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSales;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class HallCheckInTable  extends BeanFactoryDataSingle{
    private Session s;
    private final static String[] TABLEHEADERS = {"Booking No","Hall Name","Name", "Billing Name" , "Date" , "Slot Booked" , "Advnc Recv"  , "By"};
    private List<HallCheckInTable.Hall_CheckIn_Info> data;
    private int Hall_Length;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     private static DataLogicSales dlSales;
   public static  String flag ;
    
    
    public HallCheckInTable() {
    }
    public HallCheckInTable(List<HallCheckInTable.Hall_CheckIn_Info> data) {
        this.data = data;
    }
    
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    public static HallCheckInTable loadCheckIn_Data(AppView app) throws BasicException{
        HallCheckInTable GuestInfo = new HallCheckInTable(); 
    
     try{
            GuestInfo.data = new ArrayList<HallCheckInTable.Hall_CheckIn_Info>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT C.ID , M.NAME , C.MEMBERNAME , C.MEMBERNO , C.GUESTNAME ,C.SLOTBOOKED , C.ID_CARD , C.BILL_NAME , A.ADVANCE_RECV ,\n" +
                                                                    "C.TOT_AMT  , C.RECIEPT_NO ,  C.ADVNCE_ID , C.TAX1 , C.TAX2 , C.TAX3 , M.BASIC1 , M.BASIC2 , M.FACILITLIES , C.CRBY ,\n" +
                                                                    "A.CHECK_IN_DATE , B.CHARGES ,\n" +
                                                                    "(SELECT T.RATE FROM TAXES T , TAXCATEGORIES TC   WHERE TC.ID = C.TAX1 AND TC.ID=T.CATEGORY) ,\n" +
                                                                    "(SELECT T.RATE FROM TAXES T , TAXCATEGORIES TC   WHERE TC.ID = C.TAX2 AND TC.ID=T.CATEGORY) ,\n" +
                                                                    "(SELECT T.RATE FROM TAXES T , TAXCATEGORIES TC   WHERE TC.ID = C.TAX3 AND TC.ID=T.CATEGORY) ,\n" +
                                                                    "B.SLOT_FLAG , B.BOOKING_SEQ_NO , M.ID , B.ID , C.BILLED , C.BILLNO , B.MEMBERNAME , A.BAL_AMT , C.DISCOUNT , C.DISC_FLAG\n" +
                                                                    "FROM hall_check_in C , hall_advance_payment A , hall_master M , hall_booked_details B\n" +
                                                                    "WHERE C.ACTIVE=1 AND C.ADVNCE_ID=A.ID AND M.ID = C.HALLNAME  AND B.ID = A.BOOKING_ID ORDER BY B.BOOKING_SEQ_NO ", SerializerWriteString.INSTANCE, new SerializerReadClass(HallCheckInTable.Hall_CheckIn_Info.class)).list();

            GuestInfo.Hall_Length = GuestInfo.data.size();
           
        
       }
        catch(BasicException ex){
          Logger.getLogger(HallCheckInTable.class.getName()).log(Level.SEVERE, null, ex);
        }
       return GuestInfo;
     }
    
    
    
    
     public int getSize()
      {
        return Hall_Length;
      }
    
      public List<HallCheckInTable.Hall_CheckIn_Info> getHallList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<HallCheckInTable.Hall_CheckIn_Info>();
      }
    
    
      
      
       // GET CUSTOMER ID
       public String getCustomerID(AppView app , String mem_no){
        Object o = new Object();
        
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT ID FROM CUSTOMERS WHERE SEARCHKEY=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(mem_no);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
         }
           return o.toString(); 
       }
      ////////////////////////////////////////////////////////////////////////
        public String getCustomerID1(AppView app , String mem_no){
        Object o = new Object();
        
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT Value FROM generaltable WHERE name=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find("Statutory Details");
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
         }
           return o.toString(); 
       }
       ///////////////////////////////////////////////
      
      
    
      public  AbstractTableModel getTableModel()
      {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
              java.lang.String.class  ,  java.lang.String.class  , java.lang.String.class , java.lang.String.class , java.lang.String.class , java.lang.String.class, java.lang.Double.class ,   java.lang.String.class 
            };
          boolean[] canEdit = new boolean[]{
               false , false, false, false, false, false, false ,false 
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            
             }
          public Object getValueAt(int rowIndex, int columnIndex) {
              HallCheckInTable.Hall_CheckIn_Info r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.getHALLNAME();
                   case 2: return (r.getMEMBERNO()+" - "+r.getMEMBERNAME());
                   case 3: return r.getBILL_NAME();
                   case 4: return Formats.DATE.formatValue(r.getCHECK_IN_DATE());
                   case 5: return r.getSLOT_BOOKED();
                   case 6: return decimalFormat.format(r.getADV_RECV());
                   case 7: return r.getCREATE_BY();
                   case 8: return r.getGst();
                 //  case 8: return r.getGst();
                }
                return null;
            }
          
          
        };
    } 
     
    
    
    
      public static class Hall_CheckIn_Info implements SerializableRead,IKeyed {
         
            String ID;
            String HALLNAME;
            String MEMBERNAME;
            String MEMBERNO;
            String GUEST_N;
            String SLOT_BOOKED;
            String ID_CARD;
            String BILL_NAME;
            Double ADVNCE_RECV;
            Double TOT_AMT;
            String RECIEPT_NO;
            String ADVANCE_ID;
            String TAX1;
            String TAX2;
            String TAX3;
            int BASIC1;
            int BASIC2;
            String FACILITIES;
            String CREATE_BY;
            Date CHECK_IN_DATE;
            Double CHARGES;
            Double TAX1_RATE;
            Double TAX2_RATE;
            Double TAX3_RATE;
            int SLOT_FLAG;
            String BOOKING_SEQ_NO;
            String Hall_ID;
            String Booking_ID;
            int BILLED;
            String BILLNO;
            String CUSTID;
            Double Bal_AMt;
            Double Discount;
            int DISC_FLAG;
            String Gst;
            
            
            
            
         public String getCHECK_IN_Id(){
              return ID;
          }
          public void setCHECK_IN_Id(String ID){
              this.ID=ID;
          }
          public String getGst(){
              return Gst;
          }
          public void setGst(String Gst){
              this.Gst=Gst;
          }
          public String getHALLNAME(){
              return HALLNAME;
          }
          public void setHALLNAME(String HALLNAME){
              this.HALLNAME=HALLNAME;
          }
          public String getMEMBERNAME(){
              return MEMBERNAME;
          }
          public void setMEMBERNAME(String MEMBERNAME){
              this.MEMBERNAME=MEMBERNAME;
          }
          public String getMEMBERNO(){
              return MEMBERNO;
          }
          public void setMEMBERNO(String MEMBERNO){
              this.MEMBERNO=MEMBERNO;
          }
          public String getGUEST_N(){
              return GUEST_N;
          }
          public void setGUEST_N(String GUEST_N){
              this.GUEST_N=GUEST_N;
          }
          public String getSLOT_BOOKED(){
              return SLOT_BOOKED;
          }
          public void setSLOT_BOOKED(String SLOT_BOOKED){
              this.SLOT_BOOKED=SLOT_BOOKED;
          }
         
           public Double getADV_RECV(){
              return ADVNCE_RECV;
          }
          public void setADV_RECV(Double ADVNCE_RECV){
              this.ADVNCE_RECV=ADVNCE_RECV;
          }
           public Double getTOT_AMT(){
              return TOT_AMT;
          }
          public void setTOT_AMT(Double TOT_AMT){
              this.TOT_AMT=TOT_AMT;
          }
          
           public String getID_CARD(){
              return ID_CARD;
          }
          public void setID_CARD(String ID_CARD){
              this.ID_CARD=ID_CARD;
          }
           public String getBILL_NAME(){
              return BILL_NAME;
          }
          public void setBILL_NAME(String BILL_NAME){
              this.BILL_NAME=BILL_NAME;
          }
         
           public String getRECIEPT_NO(){
              return RECIEPT_NO;
          }
          public void setRECIEPT_NO(String RECIEPT_NO){
              this.RECIEPT_NO=RECIEPT_NO;
          }
          
          
           public String getADVANCE_ID(){
              return ADVANCE_ID;
          }
          public void setADVANCE_ID(String ADVANCE_ID){
              this.ADVANCE_ID=ADVANCE_ID;
          }
           public String getTAX1_id(){
              return TAX1;
          }
          public void setTAX1(String TAX1){
              this.TAX1=TAX1;
          }
           public String getTAX2_id(){
              return TAX2;
          }
          public void setTAX2(String TAX2){
              this.TAX2=TAX2;
          }
        
         public String getTAX3_id(){
              return TAX3;
          }
          public void setTAX3(String TAX3){
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
          public String getCREATE_BY(){
              return CREATE_BY;
          }
         public void setCREATE_BY(String CREATE_BY){
             this.CREATE_BY=CREATE_BY;
         }
         public Date getCHECK_IN_DATE(){
             return CHECK_IN_DATE;
         }
         public void setCHECK_IN_DATE(Date CHECK_IN_DATE){
             this.CHECK_IN_DATE = CHECK_IN_DATE;
         }
         public Double getCHARGES(){
             return CHARGES;
         }
         public void setCHARGES(Double CHARGES){
             this.CHARGES = CHARGES;
         }
         public Double getTAX1_RATE(){
             return TAX1_RATE;
         }
         public void setTAX1_RATE(Double TAX1_RATE){
             this.TAX1_RATE = TAX1_RATE;
         }
          public Double getTAX2_RATE(){
             return TAX2_RATE;
         }
         
         public void setTAX2_RATE(Double TAX2_RATE){
             this.TAX2_RATE = TAX2_RATE;
         }
          public Double getTAX3_RATE(){
             return TAX3_RATE;
         }
         public void setTAX3_RATE(Double TAX3_RATE){
             this.TAX3_RATE = TAX3_RATE;
         }
         public int getSLOT_FLAG(){
             return SLOT_FLAG;
         }
         public void setSLOT_FLAG(int SLOT_FLAG){
             this.SLOT_FLAG=SLOT_FLAG;
         }
          public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          public String getHall_ID(){
              return Hall_ID;
          }
          public void setHall_ID(String Hall_ID){
              this.Hall_ID = Hall_ID;
          }
          public String getBooking_ID(){
              return Booking_ID;
          }
          public void setBooking_ID(String Booking_ID){
              this.Booking_ID = Booking_ID;
          }
          public int getBILLED(){
              return BILLED;
          }
          public void setBILLED(int BILLED){
              this.BILLED = BILLED;
          }
           public String getBILLNO(){
              return BILLNO;
          }
          public void setBILLNO(String BILLNO){
              this.BILLNO = BILLNO;
          }
           public String getCUSTOMERID(){
              return CUSTID;
          }
          public void setCUSTOMERID(String CUSTID){
              this.CUSTID = CUSTID;
          }
          public Double getBal_AMt(){
              return Bal_AMt;
          }
          public void setBal_AMt(Double Bal_AMt)
          {
              this.Bal_AMt=Bal_AMt;
          }
           public Double getDiscount(){
              return Discount;
          }
          public void setDiscount(Double Discount){
              this.Discount=Discount;
          }
          public int getDISC_FLAG(){
              return DISC_FLAG;
          }
          public void setDISC_FLAG(){
              this.DISC_FLAG = DISC_FLAG;
          }
          
          
         public void readValues(DataRead dr) throws BasicException {
          
             ID = dr.getString(1);
             HALLNAME = dr.getString(2);
             MEMBERNAME = dr.getString(3);
             MEMBERNO = dr.getString(4);
             GUEST_N = dr.getString(5);
             SLOT_BOOKED = dr.getString(6);
             ID_CARD = dr.getString(7);
             BILL_NAME = dr.getString(8);
             ADVNCE_RECV = dr.getDouble(9);
             TOT_AMT = dr.getDouble(10);
             RECIEPT_NO = dr.getString(11);
             ADVANCE_ID = dr.getString(12);
             TAX1 = dr.getString(13);
             TAX2 = dr.getString(14); 
             TAX3 = dr.getString(15);
             BASIC1 = dr.getInt(16);
             BASIC2 = dr.getInt(17);
             FACILITIES = dr.getString(18);
             CREATE_BY = dr.getString(19);
             CHECK_IN_DATE = dr.getTimestamp(20);
             CHARGES = dr.getDouble(21);
             TAX1_RATE = dr.getDouble(22);
             TAX2_RATE = dr.getDouble(23);
             TAX3_RATE = dr.getDouble(24);
             SLOT_FLAG = dr.getInt(25);
             BOOKING_SEQ_NO = dr.getString(26);
             Hall_ID = dr.getString(27);
             Booking_ID = dr.getString(28);
             BILLED = dr.getInt(29);
             BILLNO = dr.getString(30);
             CUSTID = dr.getString(31);
             Bal_AMt = dr.getDouble(32);
             Discount = dr.getDouble(33);
             DISC_FLAG = dr.getInt(34);
          //   Gst = dr.getString(35);
             
         }
            public Object getKey() {
             return this;
        }
     }
//    public String getCheckOutApproval1(AppView app , String CheckInID1 ){
//       Object o = new Object();
//        
//         try {
//            o  =  new StaticSentence(app.getSession(), " SELECT value  FROM generaltable WHERE name=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find("Statutory Details");
//           
//         } catch (BasicException ex) {
//            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         if(o!=null){
//             flag = o.toString();
//             
//         }
//         return flag;
//       }      
}
