

package com.openbravo.pos.sales;

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
import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class ConsumableVoucherCancellationTableModel extends BeanFactoryDataSingle{
    
     private Session s;
     private List<ConsumableVoucherCancellationTableModel.BillInfo> data;
     private List<ConsumableVoucherCancellationTableModel.ProductInfo> ProductData;
     private int size;
     private int size2;
     private final static String[] RoomHeader = {"Issue Voucher No." , "Amount" , "Department" , "Created Date" , "Created By" };    
     private final static String[] ProdHeader = {"Product" , "Qty" , "Rate"  };   
    
     @Override
     public void init(Session s) {
       this.s=s;
     }
    
     
      public static ConsumableVoucherCancellationTableModel LoadVoucherInfo(AppView app ) throws BasicException{
        ConsumableVoucherCancellationTableModel GuestInfo = new ConsumableVoucherCancellationTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<ConsumableVoucherCancellationTableModel.BillInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select b.id , b.amount , d.name , b.createddate , p.name\n" +
                                                                    "from cpbill b,  department d , people p\n" +
                                                                    "where d.id=b.deptid and b.createdby=p.id\n" +
                                                                    "order by createddate", 
                                                                    SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableVoucherCancellationTableModel.BillInfo.class)).list();
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(ConsumableVoucherCancellationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
 // PRODUCTS DETAILS 
      
      
       public static ConsumableVoucherCancellationTableModel LoadProductInfo(AppView app , String BillID) throws BasicException{
        ConsumableVoucherCancellationTableModel GuestInfo = new ConsumableVoucherCancellationTableModel(); 

            try{
                   GuestInfo.ProductData = new ArrayList<ConsumableVoucherCancellationTableModel.ProductInfo>();
                   GuestInfo.ProductData = new StaticSentence(app.getSession(), "select p.name , bi.qty , bi.rate   \n" +
                                                                                "from cpbillitem bi , cpbill b , products p \n" +
                                                                                "where bi.productid=p.id and b.id=bi.billid  and bi.billid=? \n" +
                                                                                "order by 1", 
                                                                                 SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableVoucherCancellationTableModel.ProductInfo.class)).list(BillID);

                   GuestInfo.size2 = GuestInfo.ProductData.size();



               }
               catch(BasicException ex){
                   Logger.getLogger(ConsumableVoucherCancellationTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
     return GuestInfo;
  
     }
      
      
      
     
     public List<ConsumableVoucherCancellationTableModel.BillInfo> getBillList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<ConsumableVoucherCancellationTableModel.BillInfo>();
      }
    
     public List<ConsumableVoucherCancellationTableModel.ProductInfo> GetProdList(){
           if(ProductData!=null)
        {
            return ProductData;
        }
        else
            return new ArrayList<ConsumableVoucherCancellationTableModel.ProductInfo>();
      }
     
     
     
       public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
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
              ConsumableVoucherCancellationTableModel.BillInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBillID();
                   case 1: return r.getAmount();
                   case 2: return r.getDepartmentName();
                   case 3: return r.getCreatedDate();
                   case 4: return r.getCreatedBy();
                 }
                return null;
            }
          
          
          };
        }  
     
     
     
     
      public static class BillInfo implements SerializableRead,IKeyed {

        private String BillID;
        private Double Amount;
        private String DepartmentName;
        private Date CreatedDate;
        private String CreatedBy;
        
         
         public String getBillID() {
            return BillID;
        }
        public void setBillID(String BillID){
            this.BillID=BillID;
        }
       
        
        
        public Double getAmount() {
            return Amount;
        }
        public void setAmount(Double Amount){
            this.Amount=Amount;
        }

       

        public String getDepartmentName() {
            return DepartmentName;
        }
        public void setDepartmentName(String DepartmentName){
            this.DepartmentName=DepartmentName;
        }

        public String  getCreatedDate() {
            String x = Formats.TIMESTAMP.formatValue(CreatedDate) ;
             return x;
        }
        public void setCreatedDate(Date CreatedDate){
            this.CreatedDate=CreatedDate;
        }
        public String getCreatedBy(){
             return CreatedBy;
        }
        public void setCreatedBy(String CreatedBy){
            this.CreatedBy=CreatedBy;
        }
        
       
        
          
          public void readValues(DataRead dr) throws BasicException {
           
             
            BillID = dr.getString(1);
            Amount = dr.getDouble(2);
            DepartmentName = dr.getString(3);
            CreatedDate = dr.getTimestamp(4);
            CreatedBy = dr.getString(5);
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
      
      
      
      
    public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return ProductData.size();
            }
          public int getColumnCount() {
                return ProdHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return ProdHeader[column];
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
              ConsumableVoucherCancellationTableModel.ProductInfo r =ProductData.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getProductName();
                   case 1: return r.getQty();
                   case 2: return r.getRate();
                   
                 }
                return null;
            }
          
          
          };
        }  
       
      
      
      
      
      
      
      
      
      
      
      
      
      
    public static class ProductInfo implements SerializableRead,IKeyed {

        private String ProductName;
        private int Qty;
        private Double Rate;
        
        
         
         public String getProductName() {
            return ProductName;
        }
        public void setProductName(String ProductName){
            this.ProductName=ProductName;
        }
       
        
        
        public Double getRate() {
            return Rate;
        }
        public void setAmount(Double Rate){
            this.Rate=Rate;
        }

       

        public int getQty() {
            return Qty;
        }
        public void setQty(int Qty){
            this.Qty=Qty;
        }

        
       
        
          
          public void readValues(DataRead dr) throws BasicException {
           
             
            ProductName = dr.getString(1);
            Qty = dr.getInt(2);
            Rate = dr.getDouble(3);
             
          }

        public Object getKey() {
           return this;
        }

       
     }     
      
      
      
      
     
     
     
     
    
}
