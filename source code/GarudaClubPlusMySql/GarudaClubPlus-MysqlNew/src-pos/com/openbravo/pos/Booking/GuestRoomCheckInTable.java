
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSales;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class GuestRoomCheckInTable extends BeanFactoryDataSingle{
    private Session s;
    private final static String[] TABLEHEADERS = {"Booking No.","Room Type","Name", "Billing Name" , "Room No" , "Check In" , "Check Out" , "Advnc Recv"  , "Cr. By"};
    private List<GuestRoomCheckInTable.GuestRoom_CheckIn_Info> data;
    private int GrLength;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
      private static DataLogicSales dlSales;
    
    public GuestRoomCheckInTable() {
    }
    public GuestRoomCheckInTable(List<GuestRoomCheckInTable.GuestRoom_CheckIn_Info> data) {
        this.data = data;
    }
    
    
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
     public static GuestRoomCheckInTable loadCheckIn_Data(AppView app) throws BasicException{
        GuestRoomCheckInTable GuestInfo = new GuestRoomCheckInTable(); 
    
     try{
            GuestInfo.data = new ArrayList<GuestRoomCheckInTable.GuestRoom_CheckIn_Info>();
            GuestInfo.data = new StaticSentence(app.getSession(), " SELECT C.ID , C.ROOMTYPE , C.ROOMNO , C.MEMNO , C.GUEST_N ,C.ROOMS , C.DAYS , C.CHK_IN , C.CHK_OUT ,\n" +
                                                                    "P.ADVANCE_RECV  , C.TOT_AMT ,  C.E_CHK_IN , C.E_CHK_OUT , C.RM_SERV_CHRG , C.RECIEPT_NO , C.BILL_NAME , C.ID_CARD , C.CRBY ,\n" +
                                                                    "C.CRDATE , C.CRHOST , C.MEMNAME , C.ADVNCE_RECV_ID , P.BAL_AMT , C.LINK_NAME , P.BOOKING_ID , P.ROOMTYPE , P.BOOKING_SEQ_NO , C.BILLED , C.BILLNO , C.DISCOUNT , C.DISC_FLAG\n" +
                                                                    "FROM guestroom_checkin C , guestroom_advance_payment P  WHERE ACTIVE=1 AND C.ADVNCE_RECV_ID = P.ID AND C.APPROVED!=0 ORDER BY  P.BOOKING_SEQ_NO ", SerializerWriteString.INSTANCE, new SerializerReadClass(GuestRoomCheckInTable.GuestRoom_CheckIn_Info.class)).list();

            GuestInfo.GrLength = GuestInfo.data.size();
            
        
       }
        catch(BasicException ex){
          Logger.getLogger(GuestRoomCheckInTable.class.getName()).log(Level.SEVERE, null, ex);
        }
       return GuestInfo;
     }
   
     // CHECK FOR NO OF ROOMS BOOKED FOR CUSTOMER
     public int getCountFor_Rooms_Booked(AppView app , String Advance_Recv_ID){
         Object o = new Object();
         int count =0;
         try {
            o  =  new StaticSentence(app.getSession(), "SELECT COUNT(*) FROM guestroom_checkin WHERE ADVNCE_RECV_ID = ? AND ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(Advance_Recv_ID);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             count = Integer.parseInt(o.toString());
         }
         
         return count;
     }
    //AAAKSH
     
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
     
     //GET GUESTROOM LINK NAME
       public String getRoom_Link_Name(AppView app , String roomNo ){
         
         Object o = new Object();
         
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT CUSTOMER_N FROM guestroom_link WHERE ROOMNO=?  AND ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(roomNo);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             return o.toString();
         }
         else{
             return null;
         }
       }  
      
     
     
     // GET TODAYS CHECK OUT LIST 
      public List getCheck_out_guestList(AppView app) throws BasicException{
         Date d = new Date();
         String s = Formats.DATE.formatValue(d);
         Date F_D =  (Date) Formats.TIMESTAMP.parseValue(s);
        Calendar c = Calendar.getInstance();
        c.setTime(F_D); // Now use today date.
        c.add(Calendar.DATE, 1); // Adding 5 days
        Date To_D = (c.getTime());
         
         
         
         List<String> Guest_list = new ArrayList<String>();
         
         try {
            Guest_list  =  new StaticSentence(app.getSession(), " SELECT BILL_NAME FROM guestroom_checkin WHERE E_CHK_OUT > ? AND E_CHK_OUT < ? AND  ACTIVE=1", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP }), SerializerReadString.INSTANCE).list(new Object[]{F_D , To_D} );
           
         System.out.println(Guest_list);
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return Guest_list;
       }  
     
      
      //GET ROOM SERVICE BOOKING ID
        public int getRoomService_Bill_ID(AppView app , String GR_Bill ){
         
         Object o = new Object();
         
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT RM_SERV_ID FROM GUESTROOM_BILL WHERE ID= ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(GR_Bill);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             return 1;
         }
         else{
             return 0;
         }
       }  
      
      
     
      public int getSize()
      {
        return GrLength;
      }
     public List<GuestRoom_CheckIn_Info> getGuestRmList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<GuestRoomCheckInTable.GuestRoom_CheckIn_Info>();
      }
     
     
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
               java.lang.String.class  , java.lang.String.class , java.lang.String.class , java.lang.String.class , java.lang.String.class, java.lang.Double.class ,   java.lang.String.class 
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false ,false 
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            
             }
          public Object getValueAt(int rowIndex, int columnIndex) {
              GuestRoomCheckInTable.GuestRoom_CheckIn_Info r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.getROOMTYPE();
                   case 2: return (r.getMEMNO() + " - " + r.getMemName());
                   case 3: return r.getBILL_NAME();
                   case 4: return r.getROOMNO();
                   case 5: return r.getE_CHK_IN();
                   case 6: return r.getE_CHK_OUT();
                   case 7: return decimalFormat.format(r.getADV_RECV());
                   
                   case 8: return r.getCRBY();
                   case 9: return r.getGst();
                     }
                return null;
            }
          
          
        };
    } 
     
     public static class GuestRoom_CheckIn_Info implements SerializableRead,IKeyed {
         
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
            String Gst;

            
            
         public String getId(){
              return ID;
          }
          public void setId(String ID){
              this.ID=ID;
          }
           public String getGst(){
         //      String id = null;
          //   String Gst=  dlSales.getPeopleListByName6(Gst);
              return Gst;
          }
          public void setGst(String Gst){
              this.Gst=Gst;
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
//             Gst = dr.getString(32);
             
         }
            public Object getKey() {
             return this;
        }
     }
     
     
     // GET TIME DIFFERENCE FOR CHECK IN ANF CHECK OUT ------------------------------------------------------------------------------------------------------------------------------------------------------
     
        public Double getCheck_In_TimeDiff(AppView app ){
         
         Object o = new Object();
         Double TimeDiff = 0.00;
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT CHECKIN_HRS FROM checkinout_timediff ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             TimeDiff = Double.parseDouble(o.toString());
             
         }
         return TimeDiff;
       }  
     
        
          public Double getCheck_Out_TimeDiff(AppView app ){
         
         Object o = new Object();
         Double TimeDiff = 0.00;
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT CHECKOUT_HRS FROM checkinout_timediff ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             TimeDiff = Double.parseDouble(o.toString());
             
         }
         return TimeDiff;
       }  
     
     
          
          
     // GET DETAILS FOR CHECKIN REQUEST SEND FOR CHECK OUT ........ 
          
      public int getCheckOutApproval(AppView app , String CheckInID ){
         
       Object o = new Object();
         int flag = 1;
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT APPROVED  FROM guestroom_checkin WHERE ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(CheckInID);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             flag = Integer.parseInt(o.toString());
             
         }
         return flag;
       }       
          
   /////////////////////////////////////////////////////////////////////////////////////
      //Added By Ganesh
      public String getCheckOutApproval1(AppView app , String CheckInID1){
         
       Object o = new Object();
        String flag = null;
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT value  FROM generaltable WHERE name=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find("Statutory Details");
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             flag = o.toString();
             
         }
         return flag;
       }      
     //Ended By Ganesh
     
     
}
