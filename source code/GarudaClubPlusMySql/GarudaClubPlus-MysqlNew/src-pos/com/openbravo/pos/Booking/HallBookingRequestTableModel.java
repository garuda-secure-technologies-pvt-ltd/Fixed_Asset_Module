
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class HallBookingRequestTableModel extends BeanFactoryDataSingle{
       protected Session s;
       private final static String[] TABLEHEADERS = {"Booking No.","Hall Name","Floor","Max. Capacity ", "Booked By", "Booking Date", "Slot booked" };
       private List<HallBookingRequestTableModel.HallStatusInfo> Blocked_hall_list;
        private int blockedHallLength;
        private int bookedHallLength;
        
        
       public HallBookingRequestTableModel(){
           
       }
        public HallBookingRequestTableModel(List<HallBookingRequestTableModel.HallStatusInfo> Blocked_hall_list ){
           this.Blocked_hall_list = Blocked_hall_list;
       }
            
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static HallBookingRequestTableModel loadInstanceBooked_Hall_Status(AppView app)throws BasicException{
         HallBookingRequestTableModel Booked_Hall_Status = new HallBookingRequestTableModel(); 
         
          try{
            Booked_Hall_Status.Blocked_hall_list = new ArrayList<HallBookingRequestTableModel.HallStatusInfo>();
            Booked_Hall_Status.Blocked_hall_list = new StaticSentence(app.getSession(), "SELECT  ID, (SELECT c.NAME FROM CUSTOMERS c WHERE c.ID = MEMBERNAME) , MEM_NO, STATUS,(SELECT h.NAME FROM hall_master h WHERE HALL_NAME = h.ID) , FLOOR, MEMBER, NON_MEMBER, BOOKING_DATE, BOOKING_SLOT, CHARGES, (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=LUXURYTAX),(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=TAX2) ,(SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=TAX3) , BASIC, CASCADE1, NON_MEM_NAME, NON_MEM_CONTCT, NON_MEM_ADDR  , MAXCAPACITY , BASIC2 , CASCADE2 , HALL_NAME , LAST_PAYMENT_DATE, MEMBERNAME , SLOT_FLAG , ROLE , BOOKING_SEQ_NO , (SELECT CONCAT(C.ADDRESS , '\\n' , C.ADDRESS2 , '\\n' , C.CITY  ) FROM CUSTOMERS C WHERE C.ID = MEMBERNAME)  from hall_booked_details where flag=0 AND STATUS NOT IN (1)  order by BOOKING_SEQ_NO   ", SerializerWriteString.INSTANCE,new SerializerReadClass(HallBookingRequestTableModel.HallStatusInfo.class)).list();
           
            Booked_Hall_Status.bookedHallLength = Booked_Hall_Status.Blocked_hall_list.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(HallBookingRequestTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Hall_Status;
         
     }
    
    
     
     
     
     
     public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return Blocked_hall_list.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
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
              HallBookingRequestTableModel.HallStatusInfo r =Blocked_hall_list.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBOOKING_SEQ_NO();
                   case 1: return r.gethall_name();
                   case 2: return r.getFloor();
                   case 3: return r.getMAX_CAPACITY();
                  
                   case 4: return (r.getMem_No()+" - "+r.getMemberName());
                   case 5: return r.getBOOKING_DATE();
                   case 6:return r.getTIMING_SLOTS();
                   
                   
               }
                return null;
            }
          
          
        };
    } 
     
     
     public int getHallSize()
    {
        return bookedHallLength;
    }  
     
     
     public List<HallBookingRequestTableModel.HallStatusInfo> getHallList(){
           if(Blocked_hall_list!=null)
        {
            return Blocked_hall_list;
        }
        else
            return new ArrayList<HallBookingRequestTableModel.HallStatusInfo>();
      }
     
     
     
    
    
    
    
    
    
    
      public static class HallStatusInfo implements SerializableRead,IKeyed {
          
          private String ID;
          private String MEMBERNAME;
          private String MEM_NO;
          private int  STATUS ; 
          private String HALL_NAME;
          private String FLOOR ; 
          private int MEMBER_FLAG;
          private int NON_MEM_FLAG;
          private String BOOKING_DATE;
          private String TIMING_SLOTS;
          private Double CHARGES;
          private String LUXURYTAX;
          private String TAX2;
          private String TAX3;
          private int BASIC;
          private int CASCADE;
          private String NON_MEM_NAME;
          private String NON_MEM_CONTCT;
          private String NON_MEM_ADDR;
          private int MAX_CAPACITY ; 
          private int BASIC2;
          private int CASCADE2;
          private String HALL_ID;
          private Date LAST_PAYMENT_DATE;
          private String MEMBER_ID;
          private int SLOT_FLAG;
          private String ROLE;
          private String BOOKING_SEQ_NO;
           private String CustAddress;
          
          
           public String getId(){
              return ID;
          }
          public void setId(String Id){
              this.ID=Id;
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
           public int getBASIC(){
              return BASIC;
          }
          public void setBASIC(int BASIC){
              this.BASIC=BASIC;
          }
          public int getCASCADE(){
              return CASCADE;
          }
          public void setCASCADE(int CASCADE){
              this.CASCADE=CASCADE;
          }
          
           public int getBASIC2(){
              return BASIC2;
          }
          public void setBASIC2(int BASIC2){
              this.BASIC=BASIC;
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
         public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
          }
          
           public String  getHALL_ID(){
              return HALL_ID;
          }
          public void setHALL_ID(String HALL_ID){
              this.HALL_ID=HALL_ID;
          }
          
          public Date getLAST_PAYMENT_DATE(){
              return LAST_PAYMENT_DATE;
          }
          public void setLAST_PAYMENT_DATE(Date LAST_PAYMENT_DATE){
              this.LAST_PAYMENT_DATE = LAST_PAYMENT_DATE;
          }
          
          public String getMEMBER_ID(){
              return MEMBER_ID;
          }
          public void setMEMBER_ID(String MEMBER_ID){
              this.MEMBER_ID = MEMBER_ID;
          }
          
           public int getSLOT_FLAG(){
              return SLOT_FLAG;
          }
          public void setSLOT_FLAG(int SLOT_FLAG){
              this.SLOT_FLAG=SLOT_FLAG;
          }
          
          public String getROLE(){
              return ROLE;
          }
          public void setROLE(String ROLE){
              this.ROLE=ROLE;
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
          
          
        public void readValues(DataRead dr) throws BasicException {
           
          ID = dr.getString(1);
          MEMBERNAME= dr.getString(2);
          MEM_NO= dr.getString(3);
          STATUS = dr.getInt(4);
          HALL_NAME = dr.getString(5);
          FLOOR  = dr.getString(6);
          MEMBER_FLAG = dr.getInt(7);
          NON_MEM_FLAG = dr.getInt(8);
          BOOKING_DATE = dr.getString(9);
          TIMING_SLOTS = dr.getString(10);
          CHARGES = dr.getDouble(11);
          LUXURYTAX = dr.getString(12);
          TAX2 = dr.getString(13);
          TAX3 = dr.getString(14);
          BASIC = dr.getInt(15);
          CASCADE = dr.getInt(16);
          NON_MEM_NAME = dr.getString(17);
          NON_MEM_CONTCT = dr.getString(18);
          NON_MEM_ADDR = dr.getString(19);
          MAX_CAPACITY = dr.getInt(20);
          BASIC2 = dr.getInt(21);
          CASCADE2 = dr.getInt(22);  
          HALL_ID = dr.getString(23);
          LAST_PAYMENT_DATE = dr.getTimestamp(24);
          MEMBER_ID = dr.getString(25);
          SLOT_FLAG = dr.getInt(26);
          ROLE = dr.getString(27);
          BOOKING_SEQ_NO = dr.getString(28);
           CustAddress = dr.getString(29);
        }

        public Object getKey() {
           return this;
        }
          
          
          
          
          
          
          
          
      }
    
    
    
}
