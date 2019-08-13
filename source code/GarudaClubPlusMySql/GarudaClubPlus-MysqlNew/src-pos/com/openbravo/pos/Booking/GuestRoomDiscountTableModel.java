

package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class GuestRoomDiscountTableModel extends BeanFactoryDataSingle {
    private Session s;
    private final static String[] HALL_TABLEHEADERS = {"Booking No.","RoomType" , "CheckIn Dt", "Check Out Dt", "Booked By" , "No. of Rooms", "Total Amt" , "Discount Amt" , "Requested By"};
    private List<GuestRoomDiscountTableModel.RoomDiscountInfo> Romm_Discount_List;
    private int Hall_discount_size;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    
    @Override
    public void init(Session s) {
        this.s=s;
    }
    
    
    
    
     public static GuestRoomDiscountTableModel loadInstanceHall_Discount(AppView app)throws BasicException{
         GuestRoomDiscountTableModel Discount_List = new GuestRoomDiscountTableModel(); 
         
          try{
            Discount_List.Romm_Discount_List = new ArrayList<GuestRoomDiscountTableModel.RoomDiscountInfo>();
            Discount_List.Romm_Discount_List = new StaticSentence(app.getSession(), "SELECT C.ID , C.ROOMTYPE , C.ROOMNO , C.MEMNO , C.GUEST_N ,C.ROOMS , C.DAYS , C.CHK_IN , C.CHK_OUT ,\n" +
                                                                                    "P.ADVANCE_RECV  , C.TOT_AMT ,  C.E_CHK_IN , C.E_CHK_OUT , C.RM_SERV_CHRG , C.RECIEPT_NO , C.BILL_NAME , C.ID_CARD , C.CRBY ,\n" +
                                                                                    "C.CRDATE , C.CRHOST , C.MEMNAME , C.ADVNCE_RECV_ID , P.BAL_AMT , C.LINK_NAME , P.BOOKING_ID , P.ROOMTYPE , P.BOOKING_SEQ_NO , C.BILLED , C.BILLNO , C.DISCOUNT , C.DISC_FLAG \n" +
                                                                                    "FROM guestroom_checkin C , guestroom_advance_payment P  WHERE ACTIVE=1 AND C.ADVNCE_RECV_ID = P.ID AND C.APPROVED!=0 AND C.DISC_FLAG=0 ORDER BY  P.BOOKING_SEQ_NO ", SerializerWriteString.INSTANCE,new SerializerReadClass(GuestRoomDiscountTableModel.RoomDiscountInfo.class)).list();
            Discount_List.Hall_discount_size = Discount_List.Romm_Discount_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(DiscountTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        return Discount_List;
     }
    
    
    
      
      
      
       public int getSize()
      {
        return Hall_discount_size;
      }
    
      public List<GuestRoomDiscountTableModel.RoomDiscountInfo> getHallList(){
           if(Romm_Discount_List!=null)
        {
            return Romm_Discount_List;
        }
        else
            return new ArrayList<GuestRoomDiscountTableModel.RoomDiscountInfo>();
      }
      
      
      
      
      
     public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Romm_Discount_List.size();
            }
          public int getColumnCount() {
                return HALL_TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return HALL_TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
              java.lang.String.class , java.lang.String.class , java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class 
            };
          boolean[] canEdit = new boolean[]{
               false, false, false, false, false, false, false 
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              GuestRoomDiscountTableModel.RoomDiscountInfo r =Romm_Discount_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.getROOMTYPE();
                   case 2: return r.getE_CHK_IN();
                   case 3: return r.getE_CHK_OUT();
                  
                   case 4: return r.getMemName();
                   case 5: return r.getROOMS();
                       
                   case 6:return decimalFormat.format(r.getTOT_AMT());
                   case 7:return decimalFormat.format(r.getDiscount());
                   case 8: return r.getCRBY();
                   
                   
               }
                return null;
            }
          
          
        };
    }   
      
      
      
      
      
      
      
      
      
      
      
       public static class RoomDiscountInfo implements SerializableRead,IKeyed {
         
           String ID;
            String ROOMTYPE;
            String ROOMNO;
            String MEMNO;
            String GUEST_N;
            int ROOMS;
            int DAYS;
            Date CHK_IN;
            Date CHK_OUT;
            Double ADV_RECV;
            Double TOT_AMT;
            Date E_CHK_IN;
            Date E_CHK_OUT;
            Double RM_SERV_CHRG;
            String RECIEPT_NO;
            String BILL_NAME;
            String ID_CARD;
            String CRBY;
            Date CRDATE;
            String MemName;
            String ADVNCE_RECV_ID;
            Double BAL_AMT;
            String LINK_NAME;
            String BOOKING_ID;
            String ROOMTYPE_ID;
            String BOOKING_SEQ_NO;
            int BILLED;
            String BILLNO;
            Double Discount;
            int DISC_FLAG;
            
            
            
            
         public String getId(){
              return ID;
          }
          public void setId(String ID){
              this.ID=ID;
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
          public String getMEMNO(){
              return MEMNO;
          }
          public void setMEMNO(String MEMNO){
              this.MEMNO=MEMNO;
          }
          public String getGUEST_N(){
              return GUEST_N;
          }
          public void setGUEST_N(String GUEST_N){
              this.GUEST_N=GUEST_N;
          }
          public int getROOMS(){
              return ROOMS;
          }
          public void setROOMS(int ROOMS){
              this.ROOMS=ROOMS;
          }
          public int getDAYS(){
              return DAYS;
          }
          public void setDAYS(int DAYS){
              this.DAYS=DAYS;
          }
          public String getCHK_IN(){
              String x = Formats.TIMESTAMP.formatValue(CHK_IN);
              return x;
          }
          public void setCHK_IN(Date CHK_IN){
              this.CHK_IN=CHK_IN;
          }
          public String getCHK_OUT(){
              String x = Formats.TIMESTAMP.formatValue(CHK_OUT);
              return x;
          }
          public void setCHK_OUT(Date CHK_OUT){
              this.CHK_OUT=CHK_OUT;
          }
          
           public Double getADV_RECV(){
              return ADV_RECV;
          }
          public void setADV_RECV(Double ADV_RECV){
              this.ADV_RECV=ADV_RECV;
          }
           public Double getTOT_AMT(){
              return TOT_AMT;
          }
          public void setTOT_AMT(Double TOT_AMT){
              this.TOT_AMT=TOT_AMT;
          }
          
           public String getE_CHK_IN(){
               String x = Formats.TIMESTAMP.formatValue(E_CHK_IN);
            
               return x;
          }
          public void setE_CHK_IN(Date E_CHK_IN){
              this.E_CHK_IN=E_CHK_IN;
          }
           public String getE_CHK_OUT(){
               String x = Formats.TIMESTAMP.formatValue(E_CHK_OUT);
               return x;
          }
          public void setE_CHK_OUT(Date E_CHK_OUT){
              this.E_CHK_OUT=E_CHK_OUT;
          }
           public Double getRM_SERV_CHRG(){
              return RM_SERV_CHRG;
          }
          public void setRM_SERV_CHRG(Double RM_SERV_CHRG){
              this.RM_SERV_CHRG=RM_SERV_CHRG;
          }
           public String getRECIEPT_NO(){
              return RECIEPT_NO;
          }
          public void setRECIEPT_NO(String RECIEPT_NO){
              this.RECIEPT_NO=RECIEPT_NO;
          }
          
          
           public String getBILL_NAME(){
              return BILL_NAME;
          }
          public void setBILL_NAME(String BILL_NAME){
              this.BILL_NAME=BILL_NAME;
          }
           public String getID_CARD(){
              return ID_CARD;
          }
          public void setID_CARD(String ID_CARD){
              this.ID_CARD=ID_CARD;
          }
           public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
        
         public Date getCRDATE(){
              return CRDATE;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          public String getMemName(){
              return MemName;
          }
          public void setMemName(String MemName){
              this.MemName = MemName;
          }
          public String getADVNCE_RECV_ID(){
              return ADVNCE_RECV_ID;
          }
          public void setADVNCE_RECV_ID(String ADVNCE_RECV_ID){
              this.ADVNCE_RECV_ID = ADVNCE_RECV_ID;
          }
          public Double getBAL_AMT(){
              return BAL_AMT;
          }
          public void setBAL_AMT(Double BAL_AMT){
              this.BAL_AMT=BAL_AMT;
          }
          
          public String getLINK_NAME(){
              return LINK_NAME;
          }
          public void setLINK_NAME(String LINK_NAME){
              this.LINK_NAME=LINK_NAME;
          }
           public String getBOOKING_ID(){
              return BOOKING_ID;
          }
          public void setBOOKING_ID(String BOOKING_ID){
              this.BOOKING_ID=BOOKING_ID;
          }
         
            public String getROOMTYPE_ID(){
              return ROOMTYPE_ID;
          }
          public void setROOMTYPE_ID(String ROOMTYPE_ID){
              this.ROOMTYPE_ID=ROOMTYPE_ID;
          }
           public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
           public int getBILLED(){
              return BILLED;
          }
          public void setBILLED(int BILLED){
              this.BILLED=BILLED;
          }
           public String getBILLNO(){
              return BILLNO;
          }
          public void setBILLNO(String BILLNO){
              this.BILLNO=BILLNO;
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
              this.DISC_FLAG=DISC_FLAG;
          }
          
          
         public void readValues(DataRead dr) throws BasicException {
          
               ID = dr.getString(1);
             ROOMTYPE = dr.getString(2);
             ROOMNO = dr.getString(3);
             MEMNO = dr.getString(4);
             GUEST_N = dr.getString(5);
             ROOMS = dr.getInt(6);
             DAYS = dr.getInt(7);
             CHK_IN = dr.getTimestamp(8);
             CHK_OUT = dr.getTimestamp(9);
             ADV_RECV = dr.getDouble(10);
             TOT_AMT = dr.getDouble(11);
             E_CHK_IN = dr.getTimestamp(12);
             E_CHK_OUT = dr.getTimestamp(13);
             RM_SERV_CHRG = dr.getDouble(14); 
             RECIEPT_NO = dr.getString(15);
             BILL_NAME = dr.getString(16);
             ID_CARD = dr.getString(17);
             CRBY = dr.getString(18);
             CRDATE = dr.getTimestamp(19);
             MemName = dr.getString(21);
             ADVNCE_RECV_ID = dr.getString(22);
             BAL_AMT = dr.getDouble(23);
             LINK_NAME = dr.getString(24);
             BOOKING_ID = dr.getString(25);
             ROOMTYPE_ID = dr.getString(26);
             BOOKING_SEQ_NO = dr.getString(27);
             BILLED = dr.getInt(28);
             BILLNO = dr.getString(29);
             Discount = dr.getDouble(30);
             DISC_FLAG = dr.getInt(31);
             
             
             
         }
            public Object getKey() {
             return this;
        }
     }
    
      
      
      
    
    
    
    
    
    
    
    
    
    
    
}
