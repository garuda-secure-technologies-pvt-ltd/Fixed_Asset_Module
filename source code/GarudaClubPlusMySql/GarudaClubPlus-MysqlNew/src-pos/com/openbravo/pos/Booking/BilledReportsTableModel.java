
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class BilledReportsTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<BilledReportsTableModel.RoomBillInfo> Bill_List;  
    private List<BilledReportsTableModel.HallBillInfo> Hall_List; 
    private int Bill_Room_Length;
    private int Bill_Hall_Length;
    
    private final static String[] RoomHeader = {"Bill No" , "Bkng No" , "Mem No" , "Guest Name" , "Chkng Date" , "Room Type" , "RoomNo", "Bkng Rooms" , "Bkng Days" , "Total Amt" , "CrBy" , "CrDate"};
    private final static String[] HallHeader = {"Bill No" , "Bkng No" , "Mem No" , "Guest Name" , "Chkng Date" , "Hall Name" , "Slot Timings", "Slot Booked"  , "Total Amt" , "CrBy" , "CrDate"};
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    // LOAD FOR ALL BILLS ..... 
     public static BilledReportsTableModel LoadGuestRoomBills_All(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BilledReportsTableModel Billed_Rooms = new BilledReportsTableModel(); 
         
          try{
            Billed_Rooms.Bill_List = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
            Billed_Rooms.Bill_List = new StaticSentence(app.getSession(), "SELECT B.ID , B.BOOKING_SEQ_NO ,  C.NAME , B.ROOM_NO , B.CHK_IN , B.CHK_OUT , B.RATE , B.TAX_TOTAL , B.RM_SERV_AMT ,\n" +
                                                                            "B.ADVNCE_RECV , B.CRBY , B.CRDATE , B.BILLNAME , B.No_OF_ROOMS , B.NO_OF_DAYS , B.ROOMTYPE , B.BAL_AMT , B.GUESTNAME , C.SEARCHKEY\n" +
                                                                            "FROM guestroom_bill B , CUSTOMERS C\n" +
                                                                            "WHERE B.CUSTOMER=C.ID AND B.CRDATE >= ? AND B.CRDATE <= ?  \n" +
                                                                            "ORDER BY B.CRDATE  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BilledReportsTableModel.RoomBillInfo.class)).list(new Object[]{ FrmDate ,  ToDate   });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.Bill_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BilledReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
         
     }
    
    
     
      // LOAD FOR PERTICULAR ROOM TYPE  BILLS ..  ....................................................................... #AAKASH
     
     
     public static BilledReportsTableModel LoadGuestRoomBilledReport_roomType(AppView app , Date FrmDate , Date ToDate , String RoomType)throws BasicException{
         BilledReportsTableModel Billed_Halls = new BilledReportsTableModel(); 
         
          try{
            Billed_Halls.Bill_List = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
            Billed_Halls.Bill_List = new StaticSentence(app.getSession(), "SELECT B.ID , B.BOOKING_SEQ_NO ,  C.NAME , B.ROOM_NO , B.CHK_IN , B.CHK_OUT , B.RATE , B.TAX_TOTAL , B.RM_SERV_AMT ,\n" +
                                                                            "B.ADVNCE_RECV , B.CRBY , B.CRDATE , B.BILLNAME , B.No_OF_ROOMS , B.NO_OF_DAYS , B.ROOMTYPE , B.BAL_AMT , B.GUESTNAME , C.SEARCHKEY\n" +
                                                                            "FROM guestroom_bill B , CUSTOMERS C\n" +
                                                                            "WHERE B.CUSTOMER=C.ID AND B.CRDATE >= ? AND B.CRDATE <= ? AND\n" +
                                                                            "B.ROOMTYPE=?  \n" +
                                                                            "ORDER BY B.CRDATE  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING  }) ,new SerializerReadClass(BilledReportsTableModel.RoomBillInfo.class)).list(new Object[]{ FrmDate ,  ToDate , RoomType  });

            Billed_Halls.Bill_Room_Length = Billed_Halls.Bill_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BilledReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Halls;
         
     }
     
     // SEARCH BY MEMBER ID .------------------------------------------------------------------------------------------------ # AAKASH
     
      public static BilledReportsTableModel LoadGuestRoomBilledReport_MemberId(AppView app , Date FrmDate , Date ToDate , String CustId)throws BasicException{
         BilledReportsTableModel Billed_Halls = new BilledReportsTableModel(); 
         
          try{
            Billed_Halls.Bill_List = new ArrayList<BilledReportsTableModel.RoomBillInfo>();
            Billed_Halls.Bill_List = new StaticSentence(app.getSession(), "SELECT B.ID , B.BOOKING_SEQ_NO ,  C.NAME , B.ROOM_NO , B.CHK_IN , B.CHK_OUT , B.RATE , B.TAX_TOTAL , B.RM_SERV_AMT ,\n" +
                                                                            "B.ADVNCE_RECV , B.CRBY , B.CRDATE , B.BILLNAME , B.No_OF_ROOMS , B.NO_OF_DAYS , B.ROOMTYPE , B.BAL_AMT , B.GUESTNAME , C.SEARCHKEY\n" +
                                                                            "FROM guestroom_bill B , CUSTOMERS C\n" +
                                                                            "WHERE B.CUSTOMER=C.ID AND B.CRDATE >= ? AND B.CRDATE <= ? AND\n" +
                                                                            "B.CUSTOMER=?  \n" +
                                                                            "ORDER BY B.CRDATE  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING  }) ,new SerializerReadClass(BilledReportsTableModel.RoomBillInfo.class)).list(new Object[]{ FrmDate ,  ToDate , CustId  });

            Billed_Halls.Bill_Room_Length = Billed_Halls.Bill_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BilledReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Halls;
         
     }
     
   // METHOD TO DISPLAY IN TABLE ------------------------------------------------------------------------------------------------------------------
       public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Bill_List.size();
            }
          public int getColumnCount() {
                return RoomHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return RoomHeader[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false , false ,  false, false , false ,  false, false , false ,  false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              BilledReportsTableModel.RoomBillInfo r =Bill_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBillNo();
                   case 1: return r.getBOOKING_SEQ_NO();
                   case 2: return r.getMemNo();
                   case 3: if(r.getGUESTNAME()!=null){
                            return r.getGUESTNAME();
                         }
                        else{
                            return "";
                         }
                   case 4: return r.getCHK_IN();
                   case 5: return r.getROOMTYPE();
                   case 6: return r.getROOM_NO(); 
                   case 7: return r.getNo_OF_ROOMS();
                   case 8: return r.getNO_OF_DAYS();
                   case 9: return decimalFormat.format(r.getRATE()+r.getTAX_TOTAL()+r.getRM_SERV_AMT());
                   case 10: return r.getCRBY();
                   case 11: return r.getCRDATE();
                 
                 }
                return null;
            }
          
          
          };
        } 
     
      
      
     public List<BilledReportsTableModel.RoomBillInfo> getRoomBillList(){
           if(Bill_List!=null)
        {
            return Bill_List;
        }
        else
            return new ArrayList<BilledReportsTableModel.RoomBillInfo>();
      }
      
     
     
    
      public static class RoomBillInfo implements SerializableRead,IKeyed {
          
          private String ID;
          private String BOOKING_SEQ_NO;
          private String CUSTOMER;
          private String ROOM_NO;
          private Date CHK_IN;
          private Date CHK_OUT;
          private Double RATE;
          private Double TAX_TOTAL;
          private Double RM_SERV_AMT;
          private Double ADVNCE_RECV;
          private String CRBY;
          private Date CRDATE;
          private String BILLNAME;
          private int No_OF_ROOMS;
          private int NO_OF_DAYS;
          private String ROOMTYPE;
          private Double BAL_AMT;
          private String GUESTNAME;
          private String MemNo;
          
          
          public String getBillNo(){
              return ID;
          }
          public void setBillNo(String ID){
              this.ID=ID;
          }
          
           public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          
          public String getCUSTOMER(){
              return CUSTOMER;
          }
          public void setCUSTOMER(String CUSTOMER){
              this.CUSTOMER=CUSTOMER;
          }
           public String getROOM_NO(){
              return ROOM_NO;
          }
          public void setROOM_NO(String ROOM_NO){
              this.ROOM_NO=ROOM_NO;
          }
           public String getCHK_IN(){
               String x = Formats.TIMESTAMP.formatValue(CHK_IN) ;
               return x;
          }
          public void setCHK_IN(Date CHK_IN){
              
              this.CHK_IN=CHK_IN;
          }
           public String getCHK_OUT(){
               String x = Formats.TIMESTAMP.formatValue(CHK_IN) ;
               return x;
          }
          public void setCHK_OUT(Date CHK_OUT){
              this.CHK_OUT=CHK_OUT;
          }
           public Double getRATE(){
              return RATE;
          }
          public void setRATE(Double RATE){
              this.RATE=RATE;
          }
           public Double getTAX_TOTAL(){
              return TAX_TOTAL;
          }
          public void setTAX_TOTAL(Double TAX_TOTAL){
              this.TAX_TOTAL=TAX_TOTAL;
          }
           public Double getRM_SERV_AMT(){
              return RM_SERV_AMT;
          }
          public void setRM_SERV_AMT(Double RM_SERV_AMT){
              this.RM_SERV_AMT=RM_SERV_AMT;
          }
           public Double getADVNCE_RECV(){
              return ADVNCE_RECV;
          }
          public void setADVNCE_RECV(Double ADVNCE_RECV){
              this.ADVNCE_RECV=ADVNCE_RECV;
          }
           public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
           public String getCRDATE(){
               String x = Formats.TIMESTAMP.formatValue(CRDATE) ;
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
           public String getBILLNAME(){
              return BILLNAME;
          }
          public void setBILLNAME(String BILLNAME){
              this.BILLNAME=BILLNAME;
          }
           public int getNo_OF_ROOMS(){
              return No_OF_ROOMS;
          }
          public void setNo_OF_ROOMS(int No_OF_ROOMS){
              this.No_OF_ROOMS=No_OF_ROOMS;
          }
           public int getNO_OF_DAYS(){
              return NO_OF_DAYS;
          }
          public void setNO_OF_DAYS(int NO_OF_DAYS){
              this.NO_OF_DAYS=NO_OF_DAYS;
          }
           public String getROOMTYPE(){
              return ROOMTYPE;
          }
          public void setROOMTYPE(String ROOMTYPE){
              this.ROOMTYPE=ROOMTYPE;
          }
           public Double getBAL_AMT(){
              return BAL_AMT;
          }
          public void setBAL_AMT(Double BAL_AMT){
              this.BAL_AMT=BAL_AMT;
          }
           public String getGUESTNAME(){
              return GUESTNAME;
          }
          public void setGUESTNAME(String GUESTNAME){
              this.GUESTNAME=GUESTNAME;
          }
          public String getMemNo(){
              return MemNo;
          }
          public void setMemNo(String MemNo){
              this.MemNo = MemNo;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            ID = dr.getString(1);
            BOOKING_SEQ_NO = dr.getString(2);
            CUSTOMER = dr.getString(3);
            ROOM_NO = dr.getString(4);
            CHK_IN = dr.getTimestamp(5);
            CHK_OUT = dr.getTimestamp(6);
            RATE = dr.getDouble(7);
            TAX_TOTAL = dr.getDouble(8);
            RM_SERV_AMT = dr.getDouble(9);
            ADVNCE_RECV = dr.getDouble(10);
            CRBY = dr.getString(11);
            CRDATE  = dr.getTimestamp(12);
            BILLNAME = dr.getString(13);
            No_OF_ROOMS = dr.getInt(14);
            NO_OF_DAYS = dr.getInt(15);
            ROOMTYPE = dr.getString(16);
            BAL_AMT = dr.getDouble(17);
            GUESTNAME = dr.getString(18);
            MemNo = dr.getString(19);
            
            
            
            
            
            
        }

        public Object getKey() {
           return this;
        }
          
    }
     
    
      
      
    // --------------------------------------------------------------------------------------------------------------------------  
      
      
      
      
      // LOAD REPORT FOR HALLLS .................................... # AAKASH
      
      
       public static BilledReportsTableModel LoadHallBills_All(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BilledReportsTableModel Billed_Halls = new BilledReportsTableModel(); 
         
          try{
            Billed_Halls.Hall_List = new ArrayList<BilledReportsTableModel.HallBillInfo>();
            Billed_Halls.Hall_List = new StaticSentence(app.getSession(), "SELECT B.ID , B.BOOKING_SEQ_NO , C.NAME , C.SEARCHKEY , B.HALL_NAME , B.SLOT_TIME , B.CHECKIN_DATE , B.RATE , B.TAX_TOTAL ,\n" +
                                                                            "B.HALL_SERV_AMT , B.ADVANCE_RECV , B.BILLNAME , B.CRBY , B.CRDATE , B.SLOT_BOOKED\n" +
                                                                            "FROM hall_bill B , CUSTOMERS C\n" +
                                                                            "WHERE C.ID=B.CUSTOMER AND B.CRDATE >= ? AND B.CRDATE <= ? \n" +
                                                                            "ORDER BY B.CRDATE  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BilledReportsTableModel.HallBillInfo.class)).list(new Object[]{ FrmDate ,  ToDate   });

            Billed_Halls.Bill_Hall_Length = Billed_Halls.Hall_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BilledReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Halls;
         
     }
      
      
      
       
       // LOAD FOR PERTICULAR HALLL NAME .....................................................................# AAKASH
       
        public static BilledReportsTableModel LoadHallBills_HallName(AppView app , Date FrmDate , Date ToDate , String Hall_Name)throws BasicException{
         BilledReportsTableModel Billed_Halls = new BilledReportsTableModel(); 
         
          try{
            Billed_Halls.Hall_List = new ArrayList<BilledReportsTableModel.HallBillInfo>();
            Billed_Halls.Hall_List = new StaticSentence(app.getSession(), "SELECT B.ID , B.BOOKING_SEQ_NO , C.NAME , C.SEARCHKEY , B.HALL_NAME , B.SLOT_TIME , B.CHECKIN_DATE , B.RATE , B.TAX_TOTAL ,\n" +
                                                                                "B.HALL_SERV_AMT , B.ADVANCE_RECV , B.BILLNAME , B.CRBY , B.CRDATE , B.SLOT_BOOKED\n" +
                                                                                "FROM hall_bill B , CUSTOMERS C\n" +
                                                                                "WHERE C.ID=B.CUSTOMER AND B.CRDATE >= ? AND B.CRDATE <= ? AND HALL_NAME = ?\n" +
                                                                                "ORDER BY B.CRDATE  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING  }) ,new SerializerReadClass(BilledReportsTableModel.HallBillInfo.class)).list(new Object[]{ FrmDate ,  ToDate , Hall_Name  });

            Billed_Halls.Bill_Hall_Length = Billed_Halls.Hall_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BilledReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Halls;
         
     }
      
     // LOAD FOR PERTICULAR MEMBER .......................................................................................#AAKASH
        
         public static BilledReportsTableModel LoadHallBills_MemberID(AppView app , Date FrmDate , Date ToDate , String memberId)throws BasicException{
         BilledReportsTableModel Billed_Halls = new BilledReportsTableModel(); 
         
          try{
            Billed_Halls.Hall_List = new ArrayList<BilledReportsTableModel.HallBillInfo>();
            Billed_Halls.Hall_List = new StaticSentence(app.getSession(), "SELECT B.ID , B.BOOKING_SEQ_NO , C.NAME , C.SEARCHKEY , B.HALL_NAME , B.SLOT_TIME , B.CHECKIN_DATE , B.RATE , B.TAX_TOTAL ,\n" +
                                                                                "B.HALL_SERV_AMT , B.ADVANCE_RECV , B.BILLNAME , B.CRBY , B.CRDATE , B.SLOT_BOOKED\n" +
                                                                                "FROM hall_bill B , CUSTOMERS C\n" +
                                                                                "WHERE C.ID=B.CUSTOMER AND B.CRDATE >= ? AND B.CRDATE <= ? AND B.CUSTOMER = ?\n" +
                                                                                "ORDER BY B.CRDATE  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING  }) ,new SerializerReadClass(BilledReportsTableModel.HallBillInfo.class)).list(new Object[]{ FrmDate ,  ToDate , memberId  });

            Billed_Halls.Bill_Hall_Length = Billed_Halls.Hall_List.size();
            
        }
          catch(BasicException ex){
            Logger.getLogger(BilledReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
         } 
          return Billed_Halls;
        }
       
       
         
         
         
      // METHOD TO DISPLAY IN TABLE ------------------------------------------------------------------------------------------------------------------
       public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Hall_List.size();
            }
          public int getColumnCount() {
                return HallHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return HallHeader[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false , false ,  false, false , false ,  false, false , false ,  false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              BilledReportsTableModel.HallBillInfo r =Hall_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBillNo();
                   case 1: return r.getBOOKING_SEQ_NO();
                   case 2: return r.getMemNo();
                   case 3: if(r.getGUESTNAME()!=null){
                            return r.getGUESTNAME();
                         }
                        else{
                            return "";
                         }
                   case 4: return r.getCHK_IN();
                   case 5: return r.getHallName();
                   case 6: return r.getSlotTimings(); 
                   case 7: return r.getSlotBooked();
                   case 8: return decimalFormat.format(r.getRATE()+r.getTAX_TOTAL()+r.getHALL_SRV_AMT());
                   case 9: return r.getCRBY();
                   case 10: return r.getCRDATE();
                 
                 }
                return null;
            }
          
          
          };
        } 
         
         
         
         
       // GET HALL BILLED LIST ..... # AAKASH
       
       public List<BilledReportsTableModel.HallBillInfo> getHallBilledList(){
           if(Hall_List!=null)
        {
            return Hall_List;
        }
        else
            return new ArrayList<BilledReportsTableModel.HallBillInfo>();
      }
       
       
       
       
     // LOAD HALL DETAILS VALUES.... 
         public static class HallBillInfo implements SerializableRead,IKeyed {
          
          private String BillNo;
          private String BOOKING_SEQ_NO;
          private String CUSTOMER;
          private String MemNo;
          private Date CHK_IN;
          private String SlotTimings;
          private String SlotBooked;
          private Double RATE;
          private Double TAX_TOTAL;
          private Double HALL_SRV_AMT;
          private Double ADVNCE_RECV;
          private String CRBY;
          private Date CRDATE;
          private String BILLNAME;
         
          private String HAllNAME;
        
          private String GUESTNAME;
          
          
          
          public String getBillNo(){
              return BillNo;
          }
          public void setBillNo(String BillNo){
              this.BillNo=BillNo;
          }
          
           public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          
          public String getCUSTOMER(){
              return CUSTOMER;
          }
          public void setCUSTOMER(String CUSTOMER){
              this.CUSTOMER=CUSTOMER;
          }
          
           public String getCHK_IN(){
               String x = Formats.DATE.formatValue(CHK_IN) ;
               return x;
          }
          public void setCHK_IN(Date CHK_IN){
              
              this.CHK_IN=CHK_IN;
          }
          
          public Double getRATE(){
              return RATE;
          }
          
          public void setRATE(Double RATE){
              this.RATE=RATE;
          }
           public Double getTAX_TOTAL(){
              return TAX_TOTAL;
          }
          public void setTAX_TOTAL(Double TAX_TOTAL){
              this.TAX_TOTAL=TAX_TOTAL;
          }
         
           public Double getADVNCE_RECV(){
              return ADVNCE_RECV;
          }
          public void setADVNCE_RECV(Double ADVNCE_RECV){
              this.ADVNCE_RECV=ADVNCE_RECV;
          }
           public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
           public String getCRDATE(){
               String x = Formats.TIMESTAMP.formatValue(CRDATE) ;
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
           public String getBILLNAME(){
              return BILLNAME;
          }
          public void setBILLNAME(String BILLNAME){
              this.BILLNAME=BILLNAME;
          }
          
        
       
          public String getGUESTNAME(){
              return GUESTNAME;
          }
          public void setGUESTNAME(String GUESTNAME){
              this.GUESTNAME=GUESTNAME;
          }
          public String getMemNo(){
              return MemNo;
          }
          public void setMemNo(String MemNo){
              this.MemNo = MemNo;
          }
          public String getSlotTimings(){
              return SlotTimings;
          }
          public void setSlotTimings(String SlotTimings){
              this.SlotTimings = SlotTimings;
          }
          public String getSlotBooked(){
              return SlotBooked;
          }
          public void setSlotBooked(String SlotBooked){
              this.SlotBooked = SlotBooked;
          }
          public Double getHALL_SRV_AMT(){
              return HALL_SRV_AMT;
          }
          public void setHALL_SRV_AMT(Double HALL_SRV_AMT){
              this.HALL_SRV_AMT = HALL_SRV_AMT;
          }
          
          public String getHallName(){
              return HAllNAME;
          }
          public void setHAllNAME(String HAllNAME){
              this.HAllNAME = HAllNAME;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            BillNo = dr.getString(1);
            BOOKING_SEQ_NO = dr.getString(2);
            CUSTOMER = dr.getString(3);
            MemNo = dr.getString(4);
            HAllNAME = dr.getString(5);
            SlotTimings = dr.getString(6);
            CHK_IN = dr.getTimestamp(7);
            RATE = dr.getDouble(8);
            TAX_TOTAL = dr.getDouble(9);
            HALL_SRV_AMT = dr.getDouble(10);
            ADVNCE_RECV = dr.getDouble(11);
            BILLNAME = dr.getString(12);
            CRBY = dr.getString(13);
            CRDATE = dr.getTimestamp(14);
            SlotBooked = dr.getString(15);
            
            
            
            
            
            
        }

        public Object getKey() {
           return this;
        }
          
    }
     
    
}
