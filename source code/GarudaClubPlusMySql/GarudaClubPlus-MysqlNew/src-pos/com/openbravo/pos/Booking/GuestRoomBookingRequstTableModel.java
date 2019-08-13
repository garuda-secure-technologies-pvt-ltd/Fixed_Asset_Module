
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
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
import javax.swing.table.AbstractTableModel;


public class GuestRoomBookingRequstTableModel extends BeanFactoryDataSingle{
    private Session s;
     private final static String[] TABLEHEADERS = {"Booking No.","Room Type","No. Of rooms Booked","No. of Days", "Booked By", "Check-In Date" , "Check_Out_Dt" };
     private List<GuestRoomBookingRequstTableModel.Room_StatusInfo> Blocked_room_list;
     private int blockedRoomLength;
     private int bookedRoomLength;
    
     
       public GuestRoomBookingRequstTableModel(){
           
       }
        public GuestRoomBookingRequstTableModel(List<GuestRoomBookingRequstTableModel.Room_StatusInfo> Blocked_room_list ){
           this.Blocked_room_list = Blocked_room_list;
       }
     
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
     public static GuestRoomBookingRequstTableModel loadInstanceBooked_Room_Status(AppView app)throws BasicException{
         GuestRoomBookingRequstTableModel Booked_Room_Status = new GuestRoomBookingRequstTableModel(); 
         
         
         Date currDate = new Date();
         Calendar c =Calendar.getInstance();
         c.setTimeInMillis(new Date().getTime());
         c.add(Calendar.DATE, -1);
         currDate = c.getTime();
         
         
          try{
            Booked_Room_Status.Blocked_room_list = new ArrayList<GuestRoomBookingRequstTableModel.Room_StatusInfo>();
            Booked_Room_Status.Blocked_room_list = new StaticSentence(app.getSession(), "SELECT  b.ID, (SELECT m.ROOMTYPE FROM guestroom_master m WHERE m.ID = b.ROOM_TYPE) , b.BOOKING_DATE , (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = b.MEMBERNAME) , b.MEMBER_FLAG ,b.NON_MEMBER_FLAG, b.STATUS, b.FLAG, b.ROOM_NOS , b.BOOKING_DATE_EX , b.BOOKING_DAYS , b.MEMBER_NO, b.NON_MEM_NAME, b.NON_MEM_CNTCT, b.NON_MEM_ADDR, b.CHARGES , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.LUXURYTAX), (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID = b.TAX3) , b.BASIC1, b.CASCADE1,b.ROOM_TYPE,b.MEMBERNAME, b.MAX_CAPACITY , b.BASIC2 , b.CASCADE2 , b.ROLE , b.BOOKING_SEQ_NO , (SELECT CONCAT(C.ADDRESS , '\\n' , C.ADDRESS2 , '\\n' , C.CITY  ) FROM CUSTOMERS C WHERE C.ID = b.MEMBERNAME) , b.BDATECHANGEFLAG , b.ref  FROM guestroom_booked_details b WHERE b.FLAG=0  AND b.STATUS NOT IN (1) AND b.BOOKING_DATE_EX >= ? order by  b.BOOKING_SEQ_NO  ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}) ,new SerializerReadClass(GuestRoomBookingRequstTableModel.Room_StatusInfo.class)).list(new Object[]{currDate});
           
            Booked_Room_Status.bookedRoomLength = Booked_Room_Status.Blocked_room_list.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(GuestRoomBookingRequstTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Room_Status;
         
     }
    
    
     
     
     public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Blocked_room_list.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
              java.lang.String.class ,  java.lang.String.class , java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
               false , false, false, false, false, false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              GuestRoomBookingRequstTableModel.Room_StatusInfo r =Blocked_room_list.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.getROOM_TYPE();
                   case 2: return r.getNO_OF_ROOMS_BOOKED();
                   case 3: return r.getNO_OF_DAYS();
                  
                   case 4: return (r.getMem_No()+" - "+ r.getMemberName());
                   case 5: return r.getBOOKED_DATE_EX();
                   case 6: return r.getCheck_out_Date();
                   
                   
               }
                return null;
            }
          
          
        };
    } 
     
     
       public int getRoom_Booked(AppView app ,String RoomType , Date date){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT BOOKED_ROOMS FROM guestroom_availibility WHERE ROOM_TYPE= ?  AND BOOKED_DATES= ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), SerializerReadInteger.INSTANCE).find(new Object[]{RoomType , date } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
         int Room_booked = Integer.parseInt(x);
         return Room_booked;
         }
         else{
             return -1;
         }
         
     }
     
       
       
       public int getTotalRooms(AppView app ,String RoomType ){
         Object temp1 = new Object();;
         
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT ROOMS_AVAILABLE FROM guestroom_master WHERE ID = ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING }), SerializerReadInteger.INSTANCE).find(new Object[]{RoomType } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(temp1!=null){
         String x = temp1.toString();
         int Room_booked = Integer.parseInt(x);
         return Room_booked;
         }
         else{
             return -1;
         }
         
     }
     
       
       
       
     
     public List<GuestRoomBookingRequstTableModel.Room_StatusInfo> getRoomList(){
           if(Blocked_room_list!=null)
        {
            return Blocked_room_list;
        }
        else
            return new ArrayList<GuestRoomBookingRequstTableModel.Room_StatusInfo>();
      }
     
     
     
     
     
     
     
      public int getRoomSize()
    {
        return bookedRoomLength;
    }  
     
     
        public static class Room_StatusInfo implements SerializableRead,IKeyed {
          
    private String ID;      
    private String ROOM_TYPE;
    private String BOOKING_DATE;
    private String MEMBER_NAME;
    private String MEMBER_NO;
    private String NON_MEM_NAME;
    private String NON_MEM_CONTACT;
    private String NON_MEM_ADDR;
    private int MEMBER_FLAG = 0;
    private int NON_MEM_FLAG = 0;
    private int STATUS;
    private int NO_OF_ROOMS_BOOKED;
    private int NO_OF_DAYS;
    private int BASIC1;
    private int CASCADE1;
    private int BASIC2;
    private int CASCADE2;
    
    private String LUXURYTAX;
    private String TAX2;
    private String TAX3;
    
    private Double CHARGES;
    private int FLAG;
    private String MEMBER_ID;
    private Date BOOKED_DATE_EX;
    private int MAX_CAPACITY;
    private String Room_type_ID;
    private String ROLE;      
    private String BOOKING_SEQ_NO;
    private String CustAddress;
    private Date CheckOut_Date;
    private int BDateChangeFlag;
    private String OldReference;
    
    
           public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
          }
          
           public String getMemberName(){
              return MEMBER_NAME;
          }
          public void setMemberName(String MEMBER_NAME){
              this.MEMBER_NAME=MEMBER_NAME;
          }
          
          public String getMem_No(){
              return MEMBER_NO;
          }
          public void setMem_No(String MEMBER_NO){
              this.MEMBER_NO=MEMBER_NO;
          }
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String getROOM_TYPE(){
              return ROOM_TYPE;
          }
          public void setROOM_TYPE(String ROOM_TYPE){
              this.ROOM_TYPE=ROOM_TYPE;
          }
          
         
          
           public int getMem_flag(){
              return MEMBER_FLAG;
          }
          public void setMem_flag(int MEMBER_FLAG){
              this.MEMBER_FLAG=MEMBER_FLAG;
          }
          
          public int getNon_Mem_flag(){
              return NON_MEM_FLAG;
          }
          public void setNon_Mem_flag(int NON_MEM_FLAG){
              this.NON_MEM_FLAG=NON_MEM_FLAG;
          }
          
           public String getBOOKING_DATE(){
              return BOOKING_DATE;
          }
          public void setBOOKING_DATE(String BOOKING_DATE){
              this.BOOKING_DATE=BOOKING_DATE;
          }
          
           
          
           public Double getCHARGES(){
              return CHARGES;
          }
          public void setCHARGES(Double CHARGES){
              this.CHARGES=CHARGES;
          }
          
           public String getLUXURYTAX(){
              return LUXURYTAX;
          }
          public void setLUXURYTAX(String LUXURYTAX){
              this.LUXURYTAX=LUXURYTAX;
          }
          
           public String getTAX2(){
              return TAX2;
          }
          public void setTAX2(String TAX2){
              this.TAX2=TAX2;
          }
          public String getTAX3(){
              return TAX3;
          }
          public void setTAX3(String TAX3){
              this.TAX3=TAX3;
          }
           public int getBASIC1(){
              return BASIC1;
          }
          public void setBASIC1(int BASIC1){
              this.BASIC1=BASIC1;
          }
          public int getCASCADE1(){
              return CASCADE1;
          }
          public void setCASCADE1(int CASCADE1){
              this.CASCADE1=CASCADE1;
          }
          
           public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC2=BASIC2;
          }
          public int getCASCADE2(){
              return CASCADE2;
          }
          public void setCASCADE2(int CASCADE2){
              this.CASCADE2=CASCADE2;
          }
          
          public String getNON_MEM_NAME(){
              return NON_MEM_NAME;
          }
          public void setNON_MEM_NAME(String NON_MEM_NAME){
              this.NON_MEM_NAME=NON_MEM_NAME;
          }
           public String getNON_MEM_CONTCT(){
              return NON_MEM_CONTACT;
          }
          public void setNON_MEM_CONTCT(String NON_MEM_CONTCT){
              this.NON_MEM_CONTACT=NON_MEM_CONTCT;
          }
          
          public String getNON_MEM_ADDR(){
              return NON_MEM_ADDR;
          }
          public void setNON_MEM_ADDR(String NON_MEM_ADDR){
              this.NON_MEM_ADDR=NON_MEM_ADDR;
          }
        
        
          
           public int getFlag(){
              return FLAG;
          }
          public void setFlag(int FLAG){
              this.FLAG=FLAG;
          }
          
           public int getNO_OF_ROOMS_BOOKED(){
              return NO_OF_ROOMS_BOOKED;
          }
          public void setNO_OF_ROOMS_BOOKED(int NO_OF_ROOMS_BOOKED){
              this.NO_OF_ROOMS_BOOKED=NO_OF_ROOMS_BOOKED;
          }
          
           public int getNO_OF_DAYS(){
              return NO_OF_DAYS;
          }
          public void setNO_OF_DAYS(int NO_OF_DAYS){
              this.NO_OF_DAYS=NO_OF_DAYS;
          }
          
          public String getBOOKED_DATE_EX(){
               String x = Formats.TIMESTAMP.formatValue(BOOKED_DATE_EX);
               return x;
          }
          public void setBOOKED_DATE_EX(Date BOOKED_DATE_EX){
              this.BOOKED_DATE_EX=BOOKED_DATE_EX;
          }
          
          public String getROOMTYPE_ID(){
              return Room_type_ID;
          }
          public void setROOMTYPE_ID(String RoomType_id){
              this.Room_type_ID = RoomType_id;
          }
          
           public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          
           public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
          }
          public String getROLE(){
              return ROLE;
          }
          public void setROLE(String ROLE){
              this.ROLE = ROLE;
          }
          public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO = BOOKING_SEQ_NO;
          }
            public String getCustAddress(){
              return CustAddress;
          }
          public void setCustAddress(String CustAddress){
              this.CustAddress = CustAddress;
          }
          
          public String getCheck_out_Date(){
             
                Calendar cal = Calendar.getInstance();
                cal.setTime(BOOKED_DATE_EX);
                cal.add(Calendar.DATE, NO_OF_DAYS); //minus number would decrement the days
                CheckOut_Date =  cal.getTime();
                String x = Formats.TIMESTAMP.formatValue(CheckOut_Date);
              return x;
          }
         
          
          public void setBDateChangeFlag(int BDateChangeFlag){
              this.BDateChangeFlag=BDateChangeFlag;
          }
          public int getBDateChangeFlag(){
              return BDateChangeFlag;
          }
          public void setOldReference(String OldReference){
              this.OldReference=OldReference;
          }
          public String getOldReference(){
              return OldReference;
          }
          
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          ID = dr.getString(1);
          ROOM_TYPE = dr.getString(2);
          BOOKING_DATE = dr.getString(3);
          MEMBER_NAME = dr.getString(4);
          MEMBER_FLAG = dr.getInt(5);
          NON_MEM_FLAG  = dr.getInt(6);
          STATUS = dr.getInt(7);
          FLAG = dr.getInt(8);
          NO_OF_ROOMS_BOOKED = dr.getInt(9);
          BOOKED_DATE_EX = dr.getTimestamp(10);
          NO_OF_DAYS = dr.getInt(11);
          MEMBER_NO = dr.getString(12);
          NON_MEM_NAME = dr.getString(13);
          NON_MEM_CONTACT = dr.getString(14);
          NON_MEM_ADDR = dr.getString(15);
          CHARGES = dr.getDouble(16);
          LUXURYTAX = dr.getString(17);
          TAX2 = dr.getString(18);
          TAX3 = dr.getString(19);
          BASIC1 = dr.getInt(20);
          CASCADE1 = dr.getInt(21);
          Room_type_ID = dr.getString(22);
          MEMBER_ID = dr.getString(23);
          MAX_CAPACITY = dr.getInt(24);
          ROLE = dr.getString(27);
          BOOKING_SEQ_NO = dr.getString(28);
          CustAddress = dr.getString(29);
          BDateChangeFlag=dr.getInt(30);
          OldReference=dr.getString(31);
          
          
        }

        public Object getKey() {
           return this;
        }
          
            
      }
     
     
    
}
