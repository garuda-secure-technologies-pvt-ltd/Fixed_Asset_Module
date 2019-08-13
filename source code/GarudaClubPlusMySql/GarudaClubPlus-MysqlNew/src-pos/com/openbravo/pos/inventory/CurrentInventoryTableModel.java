
package com.openbravo.pos.inventory;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;





public class CurrentInventoryTableModel  extends BeanFactoryDataSingle{
     private Session s;
     private List<CurrentInventoryTableModel.InventoryInfo> data;
     private int size;
     
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    
    
     public static CurrentInventoryTableModel LoadStockValues(AppView app) throws BasicException{
        CurrentInventoryTableModel GuestInfo = new CurrentInventoryTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select l.name , p.name , s.units , c.name , u.name\n" +
                                                                    "from stockcurrent s , products p , locations l , categories c , unit u\n" +
                                                                    "where p.id=s.product and l.id=s.location and c.id=p.category and p.unittype=u.id\n" +
                                                                    "order by l.name , c.name , p.name", SerializerWriteString.INSTANCE, new SerializerReadClass(CurrentInventoryTableModel.InventoryInfo.class)).list();
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CurrentInventoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
     
     
     
     public static CurrentInventoryTableModel LoadStockValuesByWarehouse(AppView app, String Warehouse) throws BasicException{
        CurrentInventoryTableModel GuestInfo = new CurrentInventoryTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select l.name , p.name , s.units , c.name , u.name\n" +
                                                                    "from stockcurrent s , products p , locations l , categories c , unit u\n" +
                                                                    "where p.id=s.product and l.id=s.location and c.id=p.category and p.unittype=u.id   and l.name=?  \n" +
                                                                    "order by l.name , c.name , p.name", SerializerWriteString.INSTANCE, new SerializerReadClass(CurrentInventoryTableModel.InventoryInfo.class)).list(Warehouse);
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CurrentInventoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
     
     public static CurrentInventoryTableModel LoadStockValuesByCategory(AppView app, String Category) throws BasicException{
        CurrentInventoryTableModel GuestInfo = new CurrentInventoryTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select l.name , p.name , s.units , c.name , u.name\n" +
                                                                    "from stockcurrent s , products p , locations l , categories c , unit u\n" +
                                                                    "where p.id=s.product and l.id=s.location and c.id=p.category and p.unittype=u.id   and c.name=?  \n" +
                                                                    "order by l.name , c.name , p.name", SerializerWriteString.INSTANCE, new SerializerReadClass(CurrentInventoryTableModel.InventoryInfo.class)).list(Category);
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CurrentInventoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
    
     public List<CurrentInventoryTableModel.InventoryInfo> getList(){
         if(data!=null){
             return data;
         }
         else{
             return new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
         }
     }
    
     
     
      public static class InventoryInfo implements SerializableRead,IKeyed {

        private String LOCATION;
        private String PRODUCT;
        private Double UNITS;
        
        private String CATEGORY;
         private String UNITTYPE;
         
         public String getLOCATION(){
              return LOCATION;
          }
          public void SETLOCATION(String LOCATION){
              this.LOCATION=LOCATION;
          }
          public String getPRODUCT(){
              return PRODUCT;
          }
          public void setPRODUCT(String PRODUCT){
              this.PRODUCT = PRODUCT;
          }
          public Double getUNITS(){
              return UNITS;
          }
          public void setUNITS(Double UNITS){
              this.UNITS =UNITS;
          }
          public String getCATEGORY(){
              return CATEGORY;
          }
          public void setCATEGORY(String CATEGORY){
              this.CATEGORY=CATEGORY;
          }
          
          public String getUNITTYPE(){
              return UNITTYPE;
          }
          public void setUNITTYPE(String UNITTYPE){
              this.UNITTYPE=UNITTYPE;
          }
       
          public void readValues(DataRead dr) throws BasicException {
           
             
                LOCATION = dr.getString(1);
                PRODUCT = dr.getString(2);
                UNITS = dr.getDouble(3);
                CATEGORY = dr.getString(4);
               UNITTYPE=dr.getString(5);
             
              
          }

        public Object getKey() {
           return this;
        }

   }   
    
    
      
      
   // zero values ignored
        
        public static CurrentInventoryTableModel LoadStockValuesZeroValueIgnored(AppView app) throws BasicException{
        CurrentInventoryTableModel GuestInfo = new CurrentInventoryTableModel(); 
    
        try{
               GuestInfo.data = new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
               GuestInfo.data = new StaticSentence(app.getSession(), "select l.name , p.name , s.units , c.name , u.name\n" +
                                                                       "from stockcurrent s , products p , locations l , categories c , unit u\n" +
                                                                       "where p.id=s.product and l.id=s.location and c.id=p.category and p.unittype=u.id   and s.units>0.00  \n" +
                                                                       "order by l.name , c.name , p.name", SerializerWriteString.INSTANCE, new SerializerReadClass(CurrentInventoryTableModel.InventoryInfo.class)).list();

               GuestInfo.size = GuestInfo.data.size();



           }
           catch(BasicException ex){
               Logger.getLogger(CurrentInventoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
           }
        return GuestInfo;
  
     }
           
       public static CurrentInventoryTableModel LoadStockValuesByCategoryZeroValuesIgnored(AppView app, String Category) throws BasicException{
        CurrentInventoryTableModel GuestInfo = new CurrentInventoryTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select l.name , p.name , s.units , c.name , u.name\n" +
                                                                    "from stockcurrent s , products p , locations l , categories c , unit u\n" +
                                                                    "where p.id=s.product and l.id=s.location and c.id=p.category and p.unittype=u.id   and c.name=?  and s.units>0  \n" +
                                                                    "order by l.name , c.name , p.name", SerializerWriteString.INSTANCE, new SerializerReadClass(CurrentInventoryTableModel.InventoryInfo.class)).list(Category);
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CurrentInventoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
       
       
        public static CurrentInventoryTableModel LoadStockValuesByWarehouseZeroValueIgnored(AppView app, String Warehouse) throws BasicException{
        CurrentInventoryTableModel GuestInfo = new CurrentInventoryTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CurrentInventoryTableModel.InventoryInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select l.name , p.name , s.units , c.name , u.name\n" +
                                                                    "from stockcurrent s , products p , locations l , categories c , unit u\n" +
                                                                    "where p.id=s.product and l.id=s.location and c.id=p.category and p.unittype=u.id   and l.name=?  and s.units>0  \n" +
                                                                    "order by l.name , c.name , p.name", SerializerWriteString.INSTANCE, new SerializerReadClass(CurrentInventoryTableModel.InventoryInfo.class)).list(Warehouse);
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CurrentInventoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
}
