
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
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
import com.openbravo.data.loader.SerializerReadInteger;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;

public class BookHallTableModel extends BeanFactoryDataSingle{
    
    protected Session s;
    
    private List<BookHallTableModel.HallAvailibilityInfo> data1;
    
    private int Halllength;
  
   
    
    public BookHallTableModel() {
    }
    
     public BookHallTableModel(List<BookHallTableModel.HallAvailibilityInfo> data1) {
        this.data1 = data1;
    }
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static BookHallTableModel loadInstanceHallInfo(AppView app , String s) throws BasicException{
        BookHallTableModel HallInfo = new BookHallTableModel(); 
        
        try{
            HallInfo.data1 = new ArrayList<BookHallTableModel.HallAvailibilityInfo>();
            HallInfo.data1 = new StaticSentence(app.getSession(), "SELECT  b.FLOOR ,  b.NAME ,b.MAX_CAPACITY , (select name from taxcategories where id = b.luxurytax) ,\n" +
                                                                    "(select name from taxcategories where id = b.tax2) , (select name from taxcategories where id = b.tax3) ,\n" +
                                                                    "b.BASIC1 , b.CASCADE1 , b.FACILITLIES , b.ID , b.M_HOURS , b.M_HALF , b.M_FULL , b.N_HOURS , b.N_HALF ,\n" +
                                                                    "b.N_FULL , b.FLOOR_X , b.FLOOR_Y , b.ACTIVE , b.HALL_ICON , b.M_HOURLY_SLOTS , b.M_HALFDAY_SLOT  , b.M_FULLDAY_SLOT,\n" +
                                                                    "(select IMAGE1 from hall_images where ID=b.ID ), (select IMAGE2 from hall_images where ID=b.ID ),\n" +
                                                                    " (select IMAGE3 from hall_images where ID=b.ID ) , (select IMAGE from create_hall_floor where NAME=b.FLOOR) ,\n" +
                                                                    " b.BOOKING_DURA , b.BASIC2 , b.CASCADE2 , b.PAYMENT_DAYS , b.BLOCK_FLAG , b.ADVANCE_PERC , b.luxurytax  , b.tax2 , b.tax3 , (select T.RATE from TAXES T where T.CATEGORY = b.luxurytax) , (select T.RATE from TAXES T  where T.CATEGORY = b.tax2) , (select T.RATE from TAXES T where T.CATEGORY = b.tax3) from hall_master b\n" +
                                                                    "  where NAME=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(BookHallTableModel.HallAvailibilityInfo.class)).list(s);

            HallInfo.Halllength = HallInfo.data1.size();
            
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookHallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return HallInfo;
     }
    
     // For Booked Hall Table : 
    
     
     
     
     
     public List<BookHallTableModel.HallAvailibilityInfo> getData() {
        return data1;
    }

    public void setData(List<BookHallTableModel.HallAvailibilityInfo> data1) {
        this.data1 = data1;
    }
     
     
    public int getHallSize()
    {
        return Halllength;
    }
    
     
    public List<HallAvailibilityInfo> getHallPath()
    {
        if(data1!=null)
        {
            return data1;
        }
        else
            return new ArrayList<HallAvailibilityInfo>();
    } 
     
     
     public static class HallAvailibilityInfo implements SerializableRead,IKeyed {
         
         
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
        
         private BufferedImage IMAGE1;
         private BufferedImage IMAGE2;
         private BufferedImage IMAGE3;
         private BufferedImage Floor_image;
         private int advance_booking;
         private int Basic2;
         private int Cascade2;
         private int PAYMENT_DAYS;
         private int BLOCK_FLAG;
         private String ADVANCE_PERC;
         private String TAX1_ID;
         private String TAX2_ID;
         private String TAX3_ID;
         private Double Tax1_Rate;
         private Double Tax2_Rate;
         private Double Tax3_Rate;
         
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
        
          
          public BufferedImage getIMAGE1(){
              return IMAGE1;
          }
          public void setIMAGE1(BufferedImage image1){
              this.IMAGE1=image1;
          }
          public BufferedImage getIMAGE2(){
              return IMAGE2;
          }
          public void setIMAGE2(BufferedImage image2){
              this.IMAGE2=image2;
          }
          public BufferedImage getIMAGE3(){
              return IMAGE3;
          }
          public void setIMAGE3(BufferedImage image3){
              this.IMAGE3=image3;
          }
          public BufferedImage getFloorImage(){
              return Floor_image;
          }
          public void setFloorImage(BufferedImage Floor_image){
              this.Floor_image=Floor_image;
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
           public String getTAX1_ID(){
              return TAX1_ID;
          }
          public void setTAX1_ID(String TAX1_ID){
              this.TAX1_ID = TAX1_ID;
          }
           public String getTAX2_ID(){
              return TAX2_ID;
          }
          public void setTAX2_ID(String TAX2_ID){
              this.TAX2_ID = TAX2_ID;
          }
           public String getTAX3_ID(){
              return TAX3_ID;
          }
          public void setTAX3_ID(String TAX3_ID){
              this.TAX3_ID = TAX3_ID;
          }
          
          
          public Double getTax1_Rate(){
              return Tax1_Rate;
          }
          public void setTax1_Rate(Double Tax1_Rate){
              this.Tax1_Rate = Tax1_Rate;
          }
           public Double getTax2_Rate(){
              return Tax2_Rate;
          }
          public void setTax2_Rate(Double Tax2_Rate){
              this.Tax2_Rate = Tax2_Rate;
          }
           public Double getTax3_Rate(){
              return Tax3_Rate;
          }
          public void setTax3_Rate(Double Tax3_Rate){
              this.Tax3_Rate = Tax3_Rate;
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
         
          IMAGE1 = ImageUtils.readImage(dr.getBytes(24));
          IMAGE2 = ImageUtils.readImage(dr.getBytes(25));
          IMAGE3 = ImageUtils.readImage(dr.getBytes(26));
          Floor_image = ImageUtils.readImage(dr.getBytes(27));
           advance_booking = dr.getInt(28);
           Basic2 = dr.getInt(29);
           Cascade2 = dr.getInt(30);
           PAYMENT_DAYS = dr.getInt(31);
           BLOCK_FLAG = dr.getInt(32);
           ADVANCE_PERC = dr.getString(33);
           TAX1_ID = dr.getString(34);
           TAX2_ID = dr.getString(35);
           TAX3_ID = dr.getString(36);
           Tax1_Rate = dr.getDouble(37);
           Tax2_Rate = dr.getDouble(38);
           Tax3_Rate = dr.getDouble(39);
           
        }
          
          public Object getKey() {
            return this;
        }
          
     }
     
     
     
     
     
     
}
