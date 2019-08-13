
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;


public class BookGuestRoomTableModel extends BeanFactoryDataSingle{
        protected Session s;
        private List<BookGuestRoomTableModel.GuestRoomTableInfo> data;
        private int Guestlength;
    
    public BookGuestRoomTableModel() {
        
        
    }
    
    public BookGuestRoomTableModel(List<BookGuestRoomTableModel.GuestRoomTableInfo> data) {
        this.data = data;
    }
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static BookGuestRoomTableModel loadInstanceGuestInfo(AppView app , String s) throws BasicException{
        BookGuestRoomTableModel GuestInfo = new BookGuestRoomTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<BookGuestRoomTableModel.GuestRoomTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), " SELECT g.TOTAL_ROOMS , g.MEM_TARIFF , g.N_MEM_TARIFF , (SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.luxurytax) ,\n" +
                                                                    "(SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.tax2) , (SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.tax3) ,\n" +
                                                                    " g.FACILITY , g.BASIC1 , g.CASCADE1 , g.ID , g.ROOM_ICON , g.ROOMTYPE , g.ADVNCE_BOOK_DURA , g.AVAILIBILITY ,\n" +
                                                                    " g.ROOMS_AVAILABLE , (SELECT IMAGE1 FROM guestroom_images WHERE ID=g.ID) ,(SELECT IMAGE2 FROM guestroom_images WHERE ID=g.ID) ,\n" +
                                                                    " (SELECT IMAGE3 FROM guestroom_images WHERE ID=g.ID) , g.CHECK_IN , g.CHECK_OUT , g.MAX_DAYS , g.MAX_CAPACITY ,\n" +
                                                                    " g.BASIC2 , g.CASCADE2 , g.PAYMENT_DAYS , g.BLOCK_FLAG , g.BLOCK_ROOMS , g.ADVANCE_PERC , g.luxurytax , g.tax2 , g.tax3 ,(SELECT TC.RATE FROM TAXES TC WHERE TC.CATEGORY = g.luxurytax) , (SELECT TC.RATE FROM TAXES TC WHERE TC.CATEGORY = g.tax2) , (SELECT TC.RATE FROM TAXES TC WHERE TC.CATEGORY = g.tax3)   FROM guestroom_master g\n" +
                                                                    " WHERE  g.ROOMTYPE=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(BookGuestRoomTableModel.GuestRoomTableInfo.class)).list(s);
           
            GuestInfo.Guestlength = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      return GuestInfo;
  
     }
     
    
     
    
       
      
      public List<BookGuestRoomTableModel.GuestRoomTableInfo> getData() {
        return data;
    }

    public void setData(List<BookGuestRoomTableModel.GuestRoomTableInfo> data) {
        this.data = data;
    }
     
  
    
     public int getGuestRoomSize()
    {
        return Guestlength;
    }
     
     
     
     public List<BookGuestRoomTableModel.GuestRoomTableInfo> getGuestRoomPath()
    {
        if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<BookGuestRoomTableModel.GuestRoomTableInfo>();
    } 
   

     
      public static class GuestRoomTableInfo implements SerializableRead,IKeyed {

         private String Id;
       
       
        private int Room_no;
        private Double Mem_tariff;
        private Double Non_Mem_tariff;
        private String Luxury_tax;
        
        private String Tax_2;
        private String Tax_3;
        private Integer Basic1;
        private Integer Cascade1;
        private Integer Basic2;
        private Integer Cascade2;
        private Double Availibility_perc;
        private int Rooms_available;
        private String facilities;
         private BufferedImage Icon;
         private String RoomType ;
         private int advance_booking_dura;
         
         private BufferedImage IMAGE1;
         private BufferedImage IMAGE2;
         private BufferedImage IMAGE3;
        private Date CHECK_IN_TIME;
         private Date CHECK_OUT_TIME;
         private int MAX_DAYS;
         private int MAX_CAPACITY;
         private int PAYMENT_DAYS;
         private int BLOCK_FLAG;
         private int BLOCK_ROOMS;
         private String ADVANCE_PERC;
         private String Tax1_ID;
         private String Tax2_ID;
         private String Tax3_ID;
         private Double Tax1_Rate;
         private Double Tax2_Rate;
         private Double Tax3_Rate;
         
         
         
         public String getId(){
              return Id;
          }
          public void setId(String Id){
              this.Id=Id;
          }
          
         
        
          
          public int getRoom_no(){
              return Room_no;
          }
          public void setRoom_no(int Room_no){
              this.Room_no = Room_no;
          }
          public Double getMem_tariff(){
              return Mem_tariff;
          }
          public void setMem_tariff(Double Mem_tariff){
              this.Mem_tariff =Mem_tariff;
          }
          public Double getNon_mem_tariff(){
              return Non_Mem_tariff;
          }
          public void setNon_mem_tariff(Double Non_mem_tariff){
              this.Non_Mem_tariff=Non_mem_tariff;
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
          
          
          public int getBasic1(){
              return Basic1;
          }
          public void setBasic1(int Basic){
              this.Basic1=Basic1;
          }
          
          
          public int getCascade1(){
              return Cascade1;
          }
          public void setCascade1(int Cascade1){
              this.Cascade1=Cascade1;
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
          
          
          public Double getAvailibility_perc(){
              return Availibility_perc;
          }
          public void setAvailibility_perc(Double Availibility_perc){
              this.Availibility_perc=Availibility_perc;
          }
          
          public int getRooms_available(){
              return Rooms_available;
          }
          public void setRooms_available(int Rooms_available){
              this.Rooms_available=Rooms_available;
          }
          public String getFacilities(){
              return facilities;
          }
          public void setFacilities(String facilities){
              this.facilities=facilities;
          }
          
          public BufferedImage getIcon(){
              return Icon;
          }
          public void setIcon(BufferedImage Icon){
              this.Icon = Icon;
          }
          
          public String getRoomType(){
              return RoomType;
          }
          public void setRoomType(String roomType){
              this.RoomType = roomType;
          }
          
           public int getadvance_booking_dura(){
              return advance_booking_dura;
          }
          public void setadvance_booking_dura(int advance_booking_dura){
              this.advance_booking_dura=advance_booking_dura;
          }
          
           public BufferedImage getIMAGE1(){
              return IMAGE1;
          }
          public void setIMAGE1(BufferedImage IMAGE1){
              this.IMAGE1=IMAGE1;
          }
           public BufferedImage getIMAGE2(){
              return IMAGE2;
          }
          public void setIMAGE2(BufferedImage IMAGE2){
              this.IMAGE2=IMAGE2;
          }
           public BufferedImage getIMAGE3(){
              return IMAGE3;
          }
          public void setIMAGE3(BufferedImage IMAGE3){
              this.IMAGE3=IMAGE3;
          }
          
           public Date getCHECK_IN_TIME(){
              return CHECK_IN_TIME;
          }
          
          public void setCHECK_IN_TIME(Date CHECK_IN_TIME){
              this.CHECK_IN_TIME = CHECK_IN_TIME;
          }
          
          
           public Date getCHECK_OUT_TIME(){
              return CHECK_OUT_TIME;
          }
          
          public void setCHECK_OUT_TIME(Date CHECK_OUT_TIME){
              this.CHECK_OUT_TIME = CHECK_OUT_TIME;
          }
          
          public int getMAX_DAYS(){
              return MAX_DAYS;
          }
          public void setMAX_DAYS(int MAX_DAYS){
              this.MAX_DAYS = MAX_DAYS;
          }
           public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY = MAX_CAPACITY;
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
          
           public int getBLOCK_ROOMS(){
              return BLOCK_ROOMS;
          }
          public void setBLOCK_ROOMS(int BLOCK_ROOMS){
              this.BLOCK_ROOMS = BLOCK_ROOMS;
          }
          
          public String getADVANCE_PERC(){
              return ADVANCE_PERC;
          }
          public void setADVANCE_PERC(String ADVANCE_PERC){
              this.ADVANCE_PERC = ADVANCE_PERC;
          }
          
          public String getTax1_ID(){
              return Tax1_ID;
          }
          public void setTax1_ID(String Tax1_ID){
              this.Tax1_ID=Tax1_ID;
          }
           public String getTax2_ID(){
              return Tax2_ID;
          }
          public void setTax2_ID(String Tax2_ID){
              this.Tax2_ID=Tax2_ID;
          }
           public String getTax3_ID(){
              return Tax3_ID;
          }
          public void setTax3_ID(String Tax3_ID){
              this.Tax3_ID=Tax3_ID;
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
          
          
           @Override
           public String toString()
           {
               return RoomType;
           }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof GuestRoomTableModel.GuestRoomTableInfo)
                    {
                        GuestRoomTableModel.GuestRoomTableInfo g = (GuestRoomTableModel.GuestRoomTableInfo) obj;
                        if(g.getRoomType().equals(this.RoomType))
                        {
                            return true;
                        }
                        else
                            return false;
                    }
            return false;
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(RoomType.hashCode());
        }
           
        
           
           
          
          public void readValues(DataRead dr) throws BasicException {
           
              Room_no = dr.getInt(1);
              Mem_tariff = dr.getDouble(2);
              Non_Mem_tariff = dr.getDouble(3);
              Luxury_tax = dr.getString(4);
              Tax_2 = dr.getString(5);
              Tax_3 = dr.getString(6);
              facilities = dr.getString(7);
              Basic1 = dr.getInt(8);
              Cascade1 = dr.getInt(9);
              Id = dr.getString(10);
            
              Icon = ImageUtils.readImage(dr.getBytes(11));
              RoomType = dr.getString(12);
              advance_booking_dura = dr.getInt(13);
              Availibility_perc = dr.getDouble(14);
              Rooms_available = dr.getInt(15);
              IMAGE1 =  ImageUtils.readImage(dr.getBytes(16));
              IMAGE2 =  ImageUtils.readImage(dr.getBytes(17));
              IMAGE3 =  ImageUtils.readImage(dr.getBytes(18));
              CHECK_IN_TIME = dr.getTimestamp(19);
              CHECK_OUT_TIME = dr.getTimestamp(20);
              MAX_DAYS = dr.getInt(21);
              MAX_CAPACITY= dr.getInt(22);
              Basic2 = dr.getInt(23);
              Cascade2 = dr.getInt(24);
              PAYMENT_DAYS = dr.getInt(25);
              BLOCK_FLAG = dr.getInt(26);
              BLOCK_ROOMS = dr.getInt(27);
              ADVANCE_PERC = dr.getString(28);
              Tax1_ID = dr.getString(29);
              Tax2_ID = dr.getString(30);
              Tax3_ID = dr.getString(31);
              Tax1_Rate = dr.getDouble(32);
              Tax2_Rate = dr.getDouble(33);
              Tax3_Rate = dr.getDouble(34);
              
              
              
              
          }

        public Object getKey() {
           return this;
        }
          
          
          
          
          
      }
     
     
    
}
