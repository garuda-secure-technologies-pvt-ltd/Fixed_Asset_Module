
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.format.Formats;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

public class hallTableModel extends BeanFactoryDataSingle{
    
    protected Session s;
    private final static String[] TABLEHEADERS = {"Floor","Hall Name","Max. Capacity ", "Mem Rs./Hour", "Mem Rs./HalfDay", "Mem Rs./FullDay" ,"Tax 1 ","Tax 2","Basic/Cascade" ,"Tax 3" ,"Basic/Cascade" ,"Facility" };
    private List<hallTableModel.HallTableInfo> data1;
    private int Halllength;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     
    public hallTableModel() {
    }
    
     public hallTableModel(List<HallTableInfo> data1) {
        this.data1 = data1;
    }
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static hallTableModel loadInstanceHallInfo(AppView app) throws BasicException{
        hallTableModel HallInfo = new hallTableModel(); 
        
        try{
            HallInfo.data1 = new ArrayList<HallTableInfo>();
            HallInfo.data1 = new StaticSentence(app.getSession(), " SELECT  b.FLOOR ,  b.NAME ,b.MAX_CAPACITY , (select name from taxcategories where id = b.luxurytax) ,(select name from taxcategories where id = b.tax2) , (select name from taxcategories where id = b.tax3) , b.BASIC1 , b.CASCADE1 , b.FACILITLIES , b.ID , b.M_HOURS , b.M_HALF , b.M_FULL , b.N_HOURS , b.N_HALF , b.N_FULL , b.FLOOR_X , b.FLOOR_Y , b.ACTIVE , b.HALL_ICON , b.M_HOURLY_SLOTS ,  b.M_HALFDAY_SLOT  , b.M_FULLDAY_SLOT  , b.BOOKING_DURA , b.BASIC2 , b.CASCADE2 , b.PAYMENT_DAYS , (SELECT h.IMAGE1 FROM hall_images h  WHERE  h.ID = b.ID ) ,(SELECT h.IMAGE2 FROM hall_images h  WHERE  h.ID = b.ID ) , (SELECT h.IMAGE3 FROM hall_images h  WHERE  h.ID = b.ID ) , b.BLOCK_FLAG , b.ADVANCE_PERC , (SELECT M.NAME  FROM accountmaster M WHERE M.ID = b.ADVNCE_ACCT)  , (SELECT M.NAME  FROM accountmaster M WHERE M.ID =b.REVENUE_ACCT) ,(SELECT M.NAME  FROM accountmaster M WHERE M.ID =b.CANCEL_ACCT) from hall_master b  where ACTIVE=true ORDER BY b.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(hallTableModel.HallTableInfo.class)).list();
           
            HallInfo.Halllength = HallInfo.data1.size();
            
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return HallInfo;
     }
    
   
     
     
     public static hallTableModel loadInstanceHallInfo_ShowAll(AppView app) throws BasicException{
        hallTableModel HallInfo = new hallTableModel(); 
        
        try{
            HallInfo.data1 = new ArrayList<HallTableInfo>();
            HallInfo.data1 = new StaticSentence(app.getSession(), " SELECT  b.FLOOR ,  b.NAME ,b.MAX_CAPACITY , (select name from taxcategories where id = b.luxurytax) ,(select name from taxcategories where id = b.tax2) , (select name from taxcategories where id = b.tax3) , b.BASIC1 , b.CASCADE1 , b.FACILITLIES , b.ID , b.M_HOURS , b.M_HALF , b.M_FULL , b.N_HOURS , b.N_HALF , b.N_FULL , b.FLOOR_X , b.FLOOR_Y , b.ACTIVE , b.HALL_ICON , b.M_HOURLY_SLOTS ,  b.M_HALFDAY_SLOT  , b.M_FULLDAY_SLOT  , b.BOOKING_DURA , b.BASIC2 , b.CASCADE2 , b.PAYMENT_DAYS , (SELECT h.IMAGE1 FROM hall_images h  WHERE  h.ID = b.ID ) ,(SELECT h.IMAGE2 FROM hall_images h  WHERE  h.ID = b.ID ) , (SELECT h.IMAGE3 FROM hall_images h  WHERE  h.ID = b.ID ) , b.BLOCK_FLAG , b.ADVANCE_PERC , (SELECT M.NAME  FROM accountmaster M WHERE M.ID = b.ADVNCE_ACCT)  , (SELECT M.NAME  FROM accountmaster M WHERE M.ID =b.REVENUE_ACCT) , (SELECT M.NAME  FROM accountmaster M WHERE M.ID =b.CANCEL_ACCT) from hall_master b  ORDER BY b.NAME ", SerializerWriteString.INSTANCE, new SerializerReadClass(hallTableModel.HallTableInfo.class)).list();
           
            HallInfo.Halllength = HallInfo.data1.size();
            
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return HallInfo;
     }
     
     
     
       public List CheckAvailibility(String ID , AppView app){
        
        List<Object> temp = new ArrayList<Object>();
         try {
            temp  = (List<Object>) new StaticSentence(app.getSession(), "SELECT BOOKING_DATE FROM hall_booked_details WHERE HALL_NAME = ? AND STATUS NOT IN (1)", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(ID);
           
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
     }
     
     
     
     public Date getBlock_From_Date(String ID , AppView app){
     Date d = new Date();
     Object [] o = null;
      String s = null;
         try {
          o  = (Object[]) new StaticSentence(app.getSession(), "SELECT BLOCK_FROM FROM hall_master WHERE NAME = ? AND BLOCK_FLAG=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(ID);
          
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            d = (Date)o[0]; 
        return d; 
} 
     
     
     public Date getBlock_To_Date(String ID , AppView app){
     Date d = new Date();
     Object [] o = null;
      String s = null;
         try {
          o  = (Object[]) new StaticSentence(app.getSession(), "SELECT BLOCK_UPTO FROM hall_master WHERE NAME = ? AND BLOCK_FLAG=1 ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(ID);
          
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           
         d = (Date)o[0]; 
        return d; 
} 
     
     
     public List<HallTableInfo> getData() {
        return data1;
    }

    public void setData(List<HallTableInfo> data1) {
        this.data1 = data1;
    }
     
     
    
    
    public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data1.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class , java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class,java.lang.String.class ,  java.lang.String.class , java.lang.String.class ,  java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false ,false , false, false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
           
            
            
            
            
            public Object getValueAt(int rowIndex, int columnIndex) {
              HallTableInfo r =data1.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getFloor();
                   case 1: return r.getName();
                   case 2: return r.getMax_Cap();
                   case 3: return decimalFormat.format(r.getMem_hourly_charge());
                   case 4: return decimalFormat.format(r.getMem_halfDay_charge());
                   case 5: return decimalFormat.format(r.getMem_fullDay_charge());
                   case 6: return r.getLuxuryTax();
                   case 7: return r.getTax_2();
                   case 8: if (r.getBasic()==1){
                       
                         return "BASIC";
                         }
                          else {
                          return "CASCADE";
                         }
                   case 9: return r.getTax_3();
                   case 10:if (r.getBasic2()==1){
                       
                         return "BASIC";
                         }
                          else {
                          return "CASCADE";
                         }
                   
                   case 11: return r.getFacilities();
                  
                   
                   
               }
                return null;
            }
          
          
        };
    } 
    
    
    public int getHallSize()
    {
        return Halllength;
    }
    
     
    public List<HallTableInfo> getHallPath()
    {
        if(data1!=null)
        {
            return data1;
        }
        else
            return new ArrayList<HallTableInfo>();
    } 
     
     
     public static class HallTableInfo implements SerializableRead,IKeyed {
         
         
        private String Id;
        private String Floor;
        private String Name;
        private int Max_Capacity;
        private Double Mem_hourly_chrg;
        private Double Mem_halfDay_chrg;
        private Double Mem_fullDay_chrg;
        private Double Non_mem_hourly_chrg;
        private Double Non_mem_halfDay_chrg;
        private Double Non_mem_fullDay_chrg;
        private String Luxury_tax;
        
         private String Tax_2;
         private String Tax_3;
         private Integer Basic;
         private Integer Cascade;
         private int flr_X;
         private int flr_Y;
         private String facilities;
         private int active; 
         private BufferedImage icon ;
         private  String M_HOURLY_SLOTS;
         private  String M_HALFDAY_SLOT;
         private  String M_FULLDAY_SLOT;
        
         private int advance_booking ; 
         private int Basic2;
         private int Cascade2;
         private int PAYMENT_DAYS;
         private BufferedImage IMAGE1;
         private BufferedImage IMAGE2;
         private BufferedImage IMAGE3;
         private String ADVANCE_PERC;
         private int BLOCK_FLAG;
         
         private String REVENUE_ACCT;
         private String ADVNCE_ACCT;
         private String CANCEL_ACCT;
         
          public String getId(){
              return Id;
          }
          public void setId(String Id){
              this.Id=Id;
          }
          public String getFloor(){
              return Floor;
          }
          public void setFloor(String Floor){
              
              this.Floor=Floor;   
          }
          
          public String getName(){
              return Name;
          }
          public void setName(String Name){
              this.Name=Name;
          }
          
          public int getMax_Cap(){
              return Max_Capacity;
          }
          public void setMax_Cap(int Max_Capacity){
              this.Max_Capacity=Max_Capacity;
          }
          
          public Double getMem_hourly_charge(){
              return Mem_hourly_chrg;
          }
          public void setMem_hourly_charge(Double Mem_hourly_chrg){
              this.Mem_hourly_chrg=Mem_hourly_chrg;
          }
          
          public Double getMem_halfDay_charge(){
              return Mem_halfDay_chrg;
          }
          public void setMem_halfDay_charge(Double Mem_halfDay_chrg){
              this.Mem_halfDay_chrg=Mem_halfDay_chrg;
          }
          
          public Double getMem_fullDay_charge(){
              return Mem_fullDay_chrg;
          }
          public void setMem_fullDay_charge(Double Mem_fullDay_chrg){
              this.Mem_fullDay_chrg=Mem_fullDay_chrg;
          }
          
         public Double getNon_Mem_hourly_charge(){
              return Non_mem_hourly_chrg;
          }
          public void setNon_Mem_hourly_charge(Double Non_mem_hourly_chrg){
              this.Non_mem_hourly_chrg=Non_mem_hourly_chrg;
          }
          
          public Double getNon_Mem_halfDay_charge(){
              return Non_mem_halfDay_chrg;
          }
          public void setNon_Mem_halfDay_charge(Double Non_mem_halfDay_chrg){
              this.Non_mem_halfDay_chrg=Non_mem_halfDay_chrg;
          }
          public Double getNon_Mem_fullDay_charge(){
              return Non_mem_fullDay_chrg;
          }
          public void setNon_Mem_fullDay_charge(Double Non_mem_fullDay_chrg){
              this.Non_mem_fullDay_chrg=Non_mem_fullDay_chrg;
          }
          
          public String getLuxuryTax(){
              return Luxury_tax;
          }
          public void setLuxuryTax(String Luxury_tax){
              this.Luxury_tax=Luxury_tax;
          }
          
          public String getTax_2(){
              return Tax_2;
          }
          public void setTax_2(String Tax_2){
              this.Tax_2=Tax_2;
          }
          public String getTax_3(){
              return Tax_3;
          }
          public void setTax_3(String Tax_3){
              this.Tax_3=Tax_3;
          }
          
          
          public int getBasic(){
              return Basic;
          }
          public void setBasic(int Basic){
              this.Basic=Basic;
          }
          
          
          public int getCascade(){
              return Cascade;
          }
          public void setCascade(int Cascade){
              this.Cascade=Cascade;
          }
          public int getBasic2(){
              return Basic2;
          }
          public void setBasic2(int Basic2){
              this.Basic2=Basic2;
          }
          
          
          public int getCascade2(){
              return Cascade2;
          }
          public void setCascade2(int Cascade2){
              this.Cascade2=Cascade2;
          }
          
          
          public int getFloor_X(){
              return flr_X;
          }
          public void setFloor_X(int floor_X){
              this.flr_X=floor_X;
          }
          
          public int getFloor_Y(){
              return flr_Y;
          }
          public void setFloor_Y(int floor_Y){
              this.flr_Y=floor_Y;
          }
          public String getFacilities(){
              return facilities;
          }
          public void setFacilities(String facilities){
              this.facilities=facilities;
          }
          
          public int getActive(){
              return active;
          }
          public void setActive(int active){
              this.active = active;
          }
          
          public BufferedImage getIcon(){
              return icon;
          }
          public void setIcon(BufferedImage icon){
              this.icon = icon;
          }
          
          public String getM_HOURLY_SLOTS(){
              return M_HOURLY_SLOTS;
          }
          public void setM_HOURLY_SLOTS(String M_HOURLY_SLOTS){
              this.M_HOURLY_SLOTS=M_HOURLY_SLOTS;
          }
          public String getM_HALFDAY_SLOT(){
              return M_HALFDAY_SLOT;
          }
          public void setM_HALFDAY_SLOT(String M_HALFDAY_SLOT){
              this.M_HALFDAY_SLOT=M_HALFDAY_SLOT;
          }
          public String getM_FULLDAY_SLOT(){
              return M_FULLDAY_SLOT;
          }
          public void setM_FULLDAY_SLOT(String M_FULLDAY_SLOT){
              this.M_FULLDAY_SLOT=M_FULLDAY_SLOT;
          }
        
          
          public int getAdvanceBookingDuration(){
              return advance_booking;
          }
          public void setAdvanceBookingDuration(int advanceBooking){
              this.advance_booking = advanceBooking;
          }
          
          public int getPAYMENT_DAYS(){
              return PAYMENT_DAYS;
          }
          public void setPAYMENT_DAYS(int PAYMENT_DAYS){
              this.PAYMENT_DAYS = PAYMENT_DAYS;
          }
          
          public BufferedImage getIMAGE1(){
              return IMAGE1;
          }
          public void setIMAGE1(BufferedImage IMAGE1){
              this.IMAGE1 = IMAGE1;
          }
          
           public BufferedImage getIMAGE2(){
              return IMAGE2;
          }
          public void setIMAGE2(BufferedImage IMAGE2){
              this.IMAGE2 = IMAGE2;
          }
           public BufferedImage getIMAGE3(){
              return IMAGE3;
          }
          public void setIMAGE3(BufferedImage IMAGE3){
              this.IMAGE3 = IMAGE3;
          }
          
          public int getBLOCK_FLAG(){
              return BLOCK_FLAG;
          }
          public void setBLOCK_FLAG(int BLOCK_FLAG){
              this.BLOCK_FLAG = BLOCK_FLAG;
          }
          
          public String getADVANCE_PERC(){
              return ADVANCE_PERC;
          }
          public void setADVANCE_PERC(String ADVANCE_PERC){
              this.ADVANCE_PERC = ADVANCE_PERC;
          }
          
           public String getADVNCE_ACCT(){
              return ADVNCE_ACCT;
          }
          public void setADVNCE_ACCT(String ADVNCE_ACCT){
              this.ADVNCE_ACCT = ADVNCE_ACCT;
          }
          
          
           public String getREVENUE_ACCT(){
              return REVENUE_ACCT;
          }
          public void setREVENUE_ACCT(String REVENUE_ACCT){
              this.REVENUE_ACCT = REVENUE_ACCT;
          }
          
          public String getCANCEL_ACCT(){
              return CANCEL_ACCT;
          }
          public void setCANCEL_ACCT(String CANCEL_ACCT){
              this.CANCEL_ACCT = CANCEL_ACCT;
          }
          
          
          @Override
           public String toString()
           {
               return Name;
           }
           public void readValues(DataRead dr) throws BasicException {
          
           Floor= dr.getString(1);
           Name = dr.getString(2);
           Max_Capacity = dr.getInt(3);
           
           Luxury_tax = dr.getString(4);
           Tax_2 = dr.getString(5);
           Tax_3 = dr.getString(6);
           Basic = dr.getInt(7);
           Cascade = dr.getInt(8);
           facilities = dr.getString(9);
           
           Id = dr.getString(10);
           Mem_hourly_chrg = dr.getDouble(11);
           Mem_halfDay_chrg = dr.getDouble(12);
           Mem_fullDay_chrg = dr.getDouble(13);
           Non_mem_hourly_chrg = dr.getDouble(14);
           Non_mem_halfDay_chrg = dr.getDouble(15);
           Non_mem_fullDay_chrg = dr.getDouble(16);
           flr_X = dr.getInt(17);
           flr_Y = dr.getInt(18);
           active = dr.getInt(19);
           icon = ImageUtils.readImage(dr.getBytes(20));
           M_HOURLY_SLOTS = dr.getString(21);
           M_HALFDAY_SLOT = dr.getString(22);
           M_FULLDAY_SLOT = dr.getString(23);
          
           advance_booking = dr.getInt(24);
           Basic2 = dr.getInt(25);
           Cascade2 = dr.getInt(26);
           PAYMENT_DAYS = dr.getInt(27);
           IMAGE1 = ImageUtils.readImage(dr.getBytes(28));      
           IMAGE2 = ImageUtils.readImage(dr.getBytes(29));
           IMAGE3 = ImageUtils.readImage(dr.getBytes(30));
           BLOCK_FLAG = dr.getInt(31);
           ADVANCE_PERC = dr.getString(32);
           ADVNCE_ACCT = dr.getString(33);
           REVENUE_ACCT = dr.getString(34);
           CANCEL_ACCT = dr.getString(35);
           
        }
          
          public Object getKey() {
            return this;
        }
          
     }
     
     
     
     
     
     
}
