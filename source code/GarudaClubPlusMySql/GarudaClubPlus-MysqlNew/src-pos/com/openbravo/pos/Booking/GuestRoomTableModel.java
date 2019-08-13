
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;


public class GuestRoomTableModel extends BeanFactoryDataSingle{
        protected Session s;
        private final static String[] TABLEHEADERS = {"Room Type","No. of Rooms","Mem Tariff","Non_mem Tariff", "Tax 1" , "Tax 2" , "Basic/Cascade" ,  "tax 3" ,"Basic/Cascade" , "Facilities"  , "Advc Bkng Dura" , "Availibility" , "Rooms Available" , "Check In Time" , "Check Out Time" , "Capacity" , "Max_Days"};
        private List<GuestRoomTableModel.GuestRoomTableInfo> data;
        private int Guestlength;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        
    public GuestRoomTableModel() {
        
        
    }
    
    public GuestRoomTableModel(List<GuestRoomTableInfo> data) {
        this.data = data;
    }
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static GuestRoomTableModel loadInstanceGuestInfo(AppView app) throws BasicException{
        GuestRoomTableModel GuestInfo = new GuestRoomTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<GuestRoomTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), " SELECT g.TOTAL_ROOMS , g.MEM_TARIFF , g.N_MEM_TARIFF , (SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.luxurytax) ,\n" +
                                                                    "(SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.tax2) , (SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.tax3) , g.FACILITY , g.BASIC1 ,\n" +
                                                                    "g.CASCADE1 , g.ID , g.ROOM_ICON , g.ROOMTYPE , g.ADVNCE_BOOK_DURA , g.AVAILIBILITY , g.ROOMS_AVAILABLE ,\n" +
                                                                    "(SELECT IMAGE1 FROM guestroom_images WHERE ID=g.ID) ,(SELECT IMAGE2 FROM guestroom_images WHERE ID=g.ID) ,\n" +
                                                                    "(SELECT IMAGE3 FROM guestroom_images WHERE ID=g.ID) , g.MAX_CAPACITY , g.CHECK_IN , g.CHECK_OUT ,g.MAX_DAYS , g.BASIC2 ,\n" +
                                                                    "g.CASCADE2 , g.PAYMENT_DAYS , g.BLOCK_FLAG , g.BLOCK_ROOMS , g.ADVANCE_PERC , g.ROOM_NOS ,\n" +
                                                                    "(SELECT M.NAME  FROM accountmaster M WHERE M.ID = g.ADVNCE_ACCT)  ,\n" +
                                                                    "(SELECT M.NAME  FROM accountmaster M WHERE M.ID =g.REVENUE_ACCT) ,\n" +
                                                                    "(SELECT M.NAME  FROM accountmaster M WHERE M.ID = g.CANCEL_ACCT) FROM guestroom_master g\n" +
                                                                    " WHERE g.ACTIVE=true ORDER BY g.ROOMTYPE ", SerializerWriteString.INSTANCE, new SerializerReadClass(GuestRoomTableModel.GuestRoomTableInfo.class)).list();
           
            GuestInfo.Guestlength = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      return GuestInfo;
  
     }
     
     public static GuestRoomTableModel loadInstanceGuestInfo_showAll(AppView app) throws BasicException{
        GuestRoomTableModel GuestInfo = new GuestRoomTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<GuestRoomTableInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT g.TOTAL_ROOMS , g.MEM_TARIFF , g.N_MEM_TARIFF , (SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.luxurytax) ,\n" +
                                                                    "(SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.tax2) , (SELECT TC.name FROM TAXCATEGORIES TC WHERE TC.ID = g.tax3) , g.FACILITY , g.BASIC1 ,\n" +
                                                                    "g.CASCADE1 , g.ID , g.ROOM_ICON , g.ROOMTYPE , g.ADVNCE_BOOK_DURA , g.AVAILIBILITY , g.ROOMS_AVAILABLE ,\n" +
                                                                    "(SELECT IMAGE1 FROM guestroom_images WHERE ID=g.ID) ,(SELECT IMAGE2 FROM guestroom_images WHERE ID=g.ID) ,\n" +
                                                                    "(SELECT IMAGE3 FROM guestroom_images WHERE ID=g.ID) , g.MAX_CAPACITY , g.CHECK_IN , g.CHECK_OUT ,g.MAX_DAYS , g.BASIC2 ,\n" +
                                                                    "g.CASCADE2 , g.PAYMENT_DAYS , g.BLOCK_FLAG , g.BLOCK_ROOMS , g.ADVANCE_PERC , g.ROOM_NOS ,\n" +
                                                                    "(SELECT M.NAME  FROM accountmaster M WHERE M.ID = g.ADVNCE_ACCT)  ,\n" +
                                                                    "(SELECT M.NAME  FROM accountmaster M WHERE M.ID =g.REVENUE_ACCT) ,\n" +
                                                                    "(SELECT M.NAME  FROM accountmaster M WHERE M.ID = g.CANCEL_ACCT) FROM guestroom_master g ORDER BY g.ROOMTYPE" 
                                                                    , SerializerWriteString.INSTANCE, new SerializerReadClass(GuestRoomTableModel.GuestRoomTableInfo.class)).list();
           
            GuestInfo.Guestlength = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(hallTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      return GuestInfo;
  
     }
     
     // For retriving imageIcon 
     
      public BufferedImage getCurrImage(String s,AppView app)
    {
        BufferedImage bi = null ;
        try {
            Object o = new StaticSentence(app.getSession(), "select ROOM_ICON from guestroom_master where ROOMTYPE = ? ", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(s);
           
            
           // Object o = new StaticSentence(session, "select  sum(room_no) from guestroom_master where roomtype=?",  new SerializerWriteBasic(new Datas[]{Datas.STRING  })).exec(new Object[]{s});
            if(o!=null)
            {
                bi = (BufferedImage) o;
            }
        } catch (BasicException ex) {
            Logger.getLogger(GuestRoomTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }
     
      
     
       public List RoomType_NamesList_Active (AppView app){
         List<Object> RoomType_List = new ArrayList<Object>();
         try {
            RoomType_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT DISTINCT roomtype FROM guestroom_master WHERE active=true", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomType_List;
       }  
       
       //TO GET ROOM NOS OF PERTICULAR ROOM TYPE
        public String getRoomNos(AppView app , String RoomType){
         Object obj = new Object();
          try {
            obj  =  new StaticSentence(app.getSession(), " SELECT ROOM_NOS FROM guestroom_master WHERE ROOMTYPE=?  ORDER BY ROOM_NOS", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(RoomType);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return obj.toString();
       }  
       
       //TO GET ROOM NOS OF PERTICULAR ROOM TYPE
        public List getAlreadyLinked_RoomList_ByRoomType(AppView app , String RoomType){
          List<Object> Linked_RoomNos_List = new ArrayList<Object>();
         try {
            Linked_RoomNos_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT G.ROOMNO FROM guestroom_link G WHERE G.ROOMTYPE=(SELECT M.ID FROM guestroom_master M WHERE M.ROOMTYPE=?) AND G.ACTIVE=1 ORDER BY  G.ROOMNO", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(RoomType);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return Linked_RoomNos_List;
       }  
        
        
        
        
        
       
        //TO GET ROOM NOS. FROM GUESTROOM LIST TABLE 
         public List getRoomNos_Cust(AppView app , String RmType_N){
         
         List<Object> RoomNos_List = new ArrayList<Object>();
         try {
            RoomNos_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT L.ROOMNO FROM GUESTROOM_LINK L WHERE L.ROOMTYPE = (SELECT M.ID FROM guestroom_master M WHERE M.ROOMTYPE = ? ) AND L.ACTIVE=1  AND L.ROOMNO NOT IN (SELECT C.ROOMNO FROM guestroom_checkin C WHERE C.ACTIVE=1 )", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(RmType_N);
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomNos_List;
       }  
         
         
         // TO GET ROOM NOS. FROM CUSTOMER TABLE FOR LINKING
         public List getRoomNos_Cust_LINKING(AppView app , String X){
         
         List<Object> RoomNos_List = new ArrayList<Object>();
         
         try {
            RoomNos_List  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM CUSTOMERS WHERE MEMTYPE = (SELECT ID FROM MEMTYPE WHERE NAME =?) AND NAME NOT IN (SELECT CUSTOMER_N FROM GUESTROOM_LINK WHERE ACTIVE =1) ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(X);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomNos_List;
       }   
        
       
         
       // TO GET ROOM NOS. FROM CUSTOMER TABLE FOR LINKING
         public List getMemTypeList(AppView app){
         
         List<Object> MemType = new ArrayList<Object>();
         
         try {
            MemType  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM MEMTYPE  WHERE ACTIVE=1 ORDER BY NAME", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return MemType;
       }      
         
       
      // GET CURRENT MEMBER TYPE SETTED ----------------------------------------------------------------
         
          
     // GET ROOM TYPE ID FROM GUESTROOM MASTER 
     public List getMemberTypeAlreadySet(AppView app ){
         List<Object> MemType = new ArrayList<Object>();
         try {
            MemType  =  new StaticSentence(app.getSession(), "SELECT M.NAME  FROM MEMTYPE M , CUSTOMERS C WHERE M.ID = C.MEMTYPE AND C.NAME = (SELECT CUSTOMER_N FROM guestroom_link WHERE ACTIVE=1 LIMIT 1)", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list( );
           
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return MemType;
       }      
         
         
         
       public int getRooms_Available(Date Booked_Date ,String Room_ID ,  AppView app){
       int Room_Avail; 
       Object obj = new Object();
         try {
            obj  =  new StaticSentence(app.getSession(), "SELECT BOOKED_ROOMS FROM guestroom_availibility WHERE BOOKED_DATES= ?  AND ROOM_TYPE= ?  ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), SerializerReadInteger.INSTANCE).find(new Object[]{Booked_Date , Room_ID } );
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(obj!=null){
            String temp = obj.toString();
            Room_Avail = Integer.parseInt(temp);
            return Room_Avail;
        }
        else{
           return 0; 
        }
         
     }
       
         
         
     public List getRoom_Booked_Date_List(String Room_ID , AppView app){
         
         List<Date> RoomType_Date_List = new ArrayList<Date>();
         
         try {
            RoomType_Date_List  =  new StaticSentence(app.getSession(), "SELECT BOOKED_DATES FROM guestroom_availibility WHERE ROOM_TYPE = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).list(Room_ID);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           return RoomType_Date_List;
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
     
     
     // GET ROOM TYPE ID FROM GUESTROOM MASTER 
     public String getRoomType_ID(AppView app ,String RoomType){
         Object temp1 = new Object();;
         try {
            temp1  =  new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_master WHERE ROOMTYPE= ?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{RoomType} );
           
        } catch (BasicException ex) {
            Logger.getLogger(BookedRoomStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(temp1!=null){
            return temp1.toString();
        } 
        else{
            return null;
        }
     }
     
     
     
     public Date getBlock_From_Date(String ID , AppView app){
     Date d = new Date();
     Object [] o = null;
      String s = null;
         try {
          o  = (Object[]) new StaticSentence(app.getSession(), "SELECT BLOCK_FROM FROM guestroom_master WHERE ID = ? AND BLOCK_FLAG=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(ID);
          
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
          o  = (Object[]) new StaticSentence(app.getSession(), "SELECT BLOCK_UPTO FROM guestroom_master WHERE ID = ? AND BLOCK_FLAG=1 ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(ID);
          
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
           
         d = (Date)o[0]; 
        return d; 
     } 
     
     
      
      public List<GuestRoomTableInfo> getData() {
        return data;
    }

    public void setData(List<GuestRoomTableInfo> data) {
        this.data = data;
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
               java.lang.String.class  , java.lang.Integer.class , java.lang.Double.class , java.lang.Double.class , java.lang.String.class, java.lang.String.class ,  java.lang.String.class, java.lang.String.class ,java.lang.String.class , java.lang.String.class , java.lang.Integer.class ,  java.lang.Double.class ,  java.lang.Integer.class ,   java.lang.String.class ,   java.lang.String.class ,java.lang.Integer.class , java.lang.Integer.class
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false ,false , false , false , false , false , false , false , false , false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
           
            
            
            
            
            public Object getValueAt(int rowIndex, int columnIndex) {
              GuestRoomTableInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getRoomType();
                   case 1: return r.getRoom_no();
                 
                   case 2: return decimalFormat.format(r.getMem_tariff());
                   case 3: return decimalFormat.format(r.getNon_mem_tariff());
                   case 4: return r.getLuxuryTax();
                   case 5: return r.getTax_2();
                    case 6:if (r.getBasic1()==1){

                      return "BASIC";
                      }
                       else {
                       return "CASCADE" ;
                      }
                   case 7: return r.getTax_3();
                   case 8:if (r.getBasic2()==1){
                       
                         return "BASIC";
                         }
                          else {
                          return "CASCADE" ;
                         }
                   case 9: return r.getFacilities();
                   
                   
                  
                   case 10: return r.getadvance_booking_dura();
                   case 11: return decimalFormat.format(r.getAvailibility_perc());
                   case 12: return r.getRooms_available();
                   case 13:  if(r.getCHECK_IN_TIME()!=null){
                                    String x = Formats.TIME.formatValue(r.getCHECK_IN_TIME());
                                    return x;
                                }
                   case 14: if(r.getCHECK_OUT_TIME()!=null){
                                    String x = Formats.TIME.formatValue(r.getCHECK_OUT_TIME());
                                    return x;
                                }
                       
                   case 15: return r.getMAX_CAPACITY();
                   case 16: return r.getMAX_DAYS();
                     }
                return null;
            }
          
          
        };
    } 
     
     public int getGuestRoomSize()
    {
        return Guestlength;
    }
     
     public List<GuestRoomTableInfo> getGuestRoomPath()
    {
        if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<GuestRoomTableInfo>();
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
         
         private int MAX_CAPACITY;
         private Date CHECK_IN_TIME;
         private Date CHECK_OUT_TIME;
         private int MAX_DAYS;
         private int PAYMENT_DAYS;
         private int BLOCK_FLAG;
         private int BLOCK_ROOMS;
         private String ADVANCE_PERC;
         private String Room_Nos;
         private String REVENUE_ACCT;
         private String ADVNCE_ACCT;
         private String CANCEL_ACCT;
         
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
          public void setBasic1(int Basic1){
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
          public void setIMAGE1(BufferedImage IAMGE1){
              this.IMAGE1=IMAGE1;
          }
           public BufferedImage getIMAGE2(){
              return IMAGE2;
          }
          public void setIMAGE2(BufferedImage IAMGE2){
              this.IMAGE2=IMAGE2;
          }
           public BufferedImage getIMAGE3(){
              return IMAGE3;
          }
          public void setIMAGE3(BufferedImage IAMGE3){
              this.IMAGE3=IMAGE3;
          }
           
           public int getMAX_CAPACITY(){
              return MAX_CAPACITY;
          }
          public void setMAX_CAPACITY(int MAX_CAPACITY){
              this.MAX_CAPACITY=MAX_CAPACITY;
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
          
          public  int getBLOCK_ROOMS(){
              return BLOCK_ROOMS;
          }
          public void setBLOCK_ROOMS(){
              this.BLOCK_ROOMS = BLOCK_ROOMS;
          }
          public String getADVANCE_PERC(){
              return ADVANCE_PERC;
          }
          public void setADVANCE_PERC(String ADVANCE_PERC){
              this.ADVANCE_PERC = ADVANCE_PERC;
          }
           public String getRoom_Nos(){
              return Room_Nos;
          }
          public void setRoom_Nos(String Room_Nos){
              this.Room_Nos=Room_Nos;
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
               return RoomType;
           }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof GuestRoomTableInfo)
                    {
                        GuestRoomTableInfo g = (GuestRoomTableInfo) obj;
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
              MAX_CAPACITY = dr.getInt(19);
              CHECK_IN_TIME = dr.getTimestamp(20);
              CHECK_OUT_TIME = dr.getTimestamp(21);
              MAX_DAYS = dr.getInt(22);
              Basic2 = dr.getInt(23);
              Cascade2 = dr.getInt(24);
              PAYMENT_DAYS = dr.getInt(25);
              BLOCK_FLAG = dr.getInt(26);
              BLOCK_ROOMS = dr.getInt(27);
              ADVANCE_PERC = dr.getString(28);
              Room_Nos = dr.getString(29);
              ADVNCE_ACCT = dr.getString(30);
              REVENUE_ACCT = dr.getString(31);
              CANCEL_ACCT = dr.getString(32);
              
              
          }

        public Object getKey() {
           return this;
        }
          
          
          
          
          
      }
     
     
    
}
