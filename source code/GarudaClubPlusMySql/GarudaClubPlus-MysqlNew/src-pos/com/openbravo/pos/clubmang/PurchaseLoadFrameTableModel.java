
package com.openbravo.pos.clubmang;

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
import com.openbravo.pos.sms.EmailMasterTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class PurchaseLoadFrameTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private List<PurchaseLoadFrameTableModel.PurchaseDetails> data;
    private List<PurchaseLoadFrameTableModel.ItemDetails> data2;
    
    private int size;
    private int size2;
    private final static String[] TABLEHEADERS = {"Sr No." , "Vendor","Invoice No" , "Delv. Chall." , "Date" , "Doc Ref."};
    private final static String[] TABLEHEADERS1 = {"Sr No." , "Product" , "Qty"};
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
     public static PurchaseLoadFrameTableModel loadPurchasePendingInfo(String Warehouse , AppView app) throws BasicException{
        PurchaseLoadFrameTableModel PurchaseInfo = new PurchaseLoadFrameTableModel(); 
    
     try{
            PurchaseInfo.data = new ArrayList<PurchaseLoadFrameTableModel.PurchaseDetails>();
            PurchaseInfo.data = new StaticSentence(app.getSession(), "SELECT P.ID,P.INVOICENO,P.DELIVERYCHALLAN,P.DOCUMENTREF,P.crdate,P.WAREHOUSE,V.NAME\n" +
                                                                        "FROM purchasejournal_temp P , VENDOR V , LOCATIONS L\n" +
                                                                        "WHERE V.ID=P.VENDOR AND P.DEACTREF IS NULL  AND P.WAREHOUSE=L.ID AND L.NAME=? AND P.LOADED=0  ORDER BY P.CRDATE DESC ", SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseLoadFrameTableModel.PurchaseDetails.class)).list(Warehouse);
           
            PurchaseInfo.size = PurchaseInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(PurchaseLoadFrameTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return PurchaseInfo;
  
     }
    
    public static PurchaseLoadFrameTableModel loadPurchasePendingInfoQtyWise(String PurchaseID , AppView app) throws BasicException{
        PurchaseLoadFrameTableModel PurchaseInfo = new PurchaseLoadFrameTableModel(); 
    
        
        
     try{
            PurchaseInfo.data2 = new ArrayList<PurchaseLoadFrameTableModel.ItemDetails>();
            PurchaseInfo.data2 = new StaticSentence(app.getSession(), "SELECT I.ID,I.ITEM,I.QTY,P.NAME \n" +
                                                                        " FROM purchasejournalmain_temp I , purchasejournal_temp M , PRODUCTS P\n" +
                                                                        "WHERE P.ID=I.ITEM AND I.ACTIVE=1 AND I.PARENT=M.ID and I.PARENT=? \n" +
                                                                        "ORDER BY P.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseLoadFrameTableModel.ItemDetails.class)).list(PurchaseID);
           
           
            PurchaseInfo.size2 = PurchaseInfo.data2.size();
        }
        catch(BasicException ex){
            Logger.getLogger(PurchaseLoadFrameTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return PurchaseInfo;
  
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
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              PurchaseLoadFrameTableModel.PurchaseDetails r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getVendorName();
                   case 2: return r.getINVOICENO();
                   case 3: return r.getDELIVERYCHALLAN();
                   case 4: return r.getCRDATE();
                   case 5: return r.getDOCREF();
                       
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
      public int getSize()
      {
        return size;
      }
     
     public List<PurchaseLoadFrameTableModel.PurchaseDetails> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<PurchaseLoadFrameTableModel.PurchaseDetails>();
      }
      
     
     
     public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data2.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS1.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS1[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              PurchaseLoadFrameTableModel.ItemDetails r =data2.get(rowIndex);
              switch(columnIndex){
                   case 0: return (rowIndex+1);
                   case 1: return r.getProductName();
                   case 2: return r.getQTY();
                 }
                return null;
            }
          
          
          };
        } 
     
     
      public int getSize2()
      {
        return size2;
      }
     
     public List<PurchaseLoadFrameTableModel.ItemDetails> getList2(){
           if(data2!=null)
        {
            return data2;
        }
        else
            return new ArrayList<PurchaseLoadFrameTableModel.ItemDetails>();
      }
     
     
     
     
     
     public static class PurchaseDetails implements SerializableRead,IKeyed {

        private String ID;
        private String INVOICENO;
        private String DELIVERYCHALLAN;
        private String DOCREF;
        
        private Date CRDATE;
        private String WarehouseID;
        private String VendorName;
       
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getINVOICENO(){
              return INVOICENO;
          }
          public void setINVOICENO(String INVOICENO){
              this.INVOICENO = INVOICENO;
          }
          public String getDELIVERYCHALLAN(){
              return DELIVERYCHALLAN;
          }
          public void setDELIVERYCHALLAN(String DELIVERYCHALLAN){
              this.DELIVERYCHALLAN =DELIVERYCHALLAN;
          }
        
          public String getDOCREF(){
              return DOCREF;
          }
          public void setDOCREF(String DOCREF){
              this.DOCREF = DOCREF;
          }
          
         
          
          public String getWarehouseID(){
              return WarehouseID;
          }
          public void setWarehouseID(String WarehouseID){
              this.WarehouseID=WarehouseID;
          }
          
        
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
          public String getVendorName(){
              return VendorName;
          }
          public void setVendorName(String VendorName){
              this.VendorName=VendorName;
          }
          
         
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                INVOICENO = dr.getString(2);
                DELIVERYCHALLAN = dr.getString(3);
                DOCREF = dr.getString(4);
                CRDATE = dr.getTimestamp(5);
                WarehouseID = dr.getString(6);
                VendorName = dr.getString(7);
                
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
     
     
     public static class ItemDetails implements SerializableRead,IKeyed {

        private String ID;
        private String PRODUCTID;
        private Double QTY;
        private String ProductName;
       
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getPRODUCTID(){
              return PRODUCTID;
          }
          public void setPRODUCTID(String PRODUCTID){
              this.PRODUCTID = PRODUCTID;
          }
          public Double getQTY(){
              return QTY;
          }
          public void setQTY(Double QTY){
              this.QTY =QTY;
          }
        
          public String getProductName(){
              return ProductName;
          }
          public void setProductName(String ProductName){
              this.ProductName = ProductName;
          }
          
         public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                PRODUCTID = dr.getString(2);
                QTY = dr.getDouble(3);
                ProductName = dr.getString(4);
                
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
     
}
