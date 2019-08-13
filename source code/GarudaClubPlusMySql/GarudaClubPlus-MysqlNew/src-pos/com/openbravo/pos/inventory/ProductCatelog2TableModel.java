

package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Booking.BilledReportsTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductCatelog2TableModel extends BeanFactoryDataSingle{
    
     private Session s;
     private List<ProductCatelog2TableModel.ProdCatelogInfo> ProdCatelog_list;  
     private int length;
     
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    
    
     public static ProductCatelog2TableModel LoadProdCatelogWithStock(AppView app , String CustId)throws BasicException{
         ProductCatelog2TableModel ProdCatelog = new ProductCatelog2TableModel(); 
         
       try{
            Object o = null;  
              
            o  =  new StaticSentence(app.getSession(), "SELECT ID from CONVERTER WHERE LOCATIONSCN=? ",SerializerWriteString.INSTANCE    , SerializerReadString.INSTANCE).find(CustId);
            
            if(o!=null){
            
                    ProdCatelog.ProdCatelog_list = new ArrayList<ProductCatelog2TableModel.ProdCatelogInfo>();
                    ProdCatelog.ProdCatelog_list = new StaticSentence(app.getSession(), "SELECT LOCATIONS.NAME AS WAREHOUSE,PRODUCTS.NAME AS PRODUCT,UNIT.NAME AS UNITTYPE,\n" +
                                                                                            "IFNULL(STOCKCURRENT.UNITS ,0.00) AS QUANTITY,CATEGORIES.NAME AS CATEGORY ,\n" +
                                                                                            "IFNULL(CAST((SELECT  rate FROM purchasejournal p1 , purchasejournalmain m1 \n" +
                                                                                            "where p1.item=C.PRODUCTFST  and p1.parent=m1.id \n" +
                                                                                            "order by m1.crdate desc limit 1)/C.NOSEC AS decimal(10,2)),0.00) \n" +
                                                                                            "AS PURCRATE , MAX(PM.CRDATE) as date ,\n" +
                                                                                            "C.NOSEC  ,  IFNULL(CAST(STOCKCURRENT.UNITS/C.NOSEC AS decimal(10,2)),0.00) AS FINALQTY , \n" +
                                                                                            "IFNULL(PRODUCTS.PRICESELL,0.00) AS SRATE\n" +
                                                                                            "FROM STOCKCURRENT,LOCATIONS,PRODUCTS,UNIT,CATEGORIES , purchasejournalmain PM , purchasejournal P , \n" +
                                                                                            "CONVERTER C\n" +
                                                                                            "WHERE STOCKCURRENT.LOCATION=LOCATIONS.ID AND STOCKCURRENT.PRODUCT=PRODUCTS.ID AND \n" +
                                                                                            "PRODUCTS.UNITTYPE=UNIT.ID AND CATEGORIES.ID=PRODUCTS.CATEGORY \n" +
                                                                                            "AND LOCATIONS.ID= ? \n" +
                                                                                            "AND P.item =C.PRODUCTFST and P.parent=PM.id \n" +
                                                                                            "AND C.PRODUCTSCN=PRODUCTS.ID  AND C.ACTIVE=1  \n" +
                                                                                            "GROUP BY PRODUCTS.NAME \n" +
                                                                                            "ORDER BY LOCATIONS.NAME,CATEGORY,PRODUCTS.NAME", new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(ProductCatelog2TableModel.ProdCatelogInfo.class)).list(new Object[]{  CustId  });

                    ProdCatelog.length = ProdCatelog.ProdCatelog_list.size();
            }
            else{
                
                ProdCatelog.ProdCatelog_list = new ArrayList<ProductCatelog2TableModel.ProdCatelogInfo>();
                    ProdCatelog.ProdCatelog_list = new StaticSentence(app.getSession(), "SELECT LOCATIONS.NAME AS WAREHOUSE,PRODUCTS.NAME AS PRODUCT,UNIT.NAME AS UNITTYPE,\n" +
                                                                                            "IFNULL(STOCKCURRENT.UNITS ,0.00) AS QUANTITY,CATEGORIES.NAME AS CATEGORY ,\n" +
                                                                                            "IFNULL(CAST((SELECT  rate FROM purchasejournal p1 , purchasejournalmain m1 \n" +
                                                                                            "where p1.item=C.PRODUCTFST  and p1.parent=m1.id \n" +
                                                                                            "order by m1.crdate desc limit 1)/C.NOFST AS decimal(10,2)),0.00) \n" +
                                                                                            "AS PURCRATE , MAX(PM.CRDATE) as date ,\n" +
                                                                                            "C.NOFST  ,  IFNULL(CAST(STOCKCURRENT.UNITS/C.NOFST AS decimal(10,2)),0.00) AS FINALQTY , \n" +
                                                                                            "IFNULL(PRODUCTS.PRICESELL,0.00) AS SRATE\n" +
                                                                                            "FROM STOCKCURRENT,LOCATIONS,PRODUCTS,UNIT,CATEGORIES , purchasejournalmain PM , purchasejournal P , \n" +
                                                                                            "CONVERTER C\n" +
                                                                                            "WHERE STOCKCURRENT.LOCATION=LOCATIONS.ID AND STOCKCURRENT.PRODUCT=PRODUCTS.ID AND \n" +
                                                                                            "PRODUCTS.UNITTYPE=UNIT.ID AND CATEGORIES.ID=PRODUCTS.CATEGORY \n" +
                                                                                            "AND LOCATIONS.ID= ? \n" +
                                                                                            "AND P.item =C.PRODUCTFST and P.parent=PM.id \n" +
                                                                                            "AND C.PRODUCTFST=PRODUCTS.ID AND C.ACTIVE=1\n" +
                                                                                            "GROUP BY PRODUCTS.NAME \n" +
                                                                                            "ORDER BY LOCATIONS.NAME,CATEGORY,PRODUCTS.NAME", new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(ProductCatelog2TableModel.ProdCatelogInfo.class)).list(new Object[]{  CustId  });

                    ProdCatelog.length = ProdCatelog.ProdCatelog_list.size();
                
                
            }
            
            
            
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(ProductCatelog2TableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return ProdCatelog;
         
     }
    
       public List<ProductCatelog2TableModel.ProdCatelogInfo> getProdCatelogList(){
           if(ProdCatelog_list!=null)
        {
            return ProdCatelog_list;
        }
        else
            return new ArrayList<ProductCatelog2TableModel.ProdCatelogInfo>();
      }
      
     
     
     public static class ProdCatelogInfo implements SerializableRead,IKeyed {
          
          private String WAREHOUSE;
          private String PRODUCT;
          private String UNITTYPE;
          private Double QUANTITY;
          private String CATEGORY;
          private Double PURCRATE;
          private Date CRDATE;
          private Double CONVERTING_RATIO;
          private Double FinalQTY;
          private Double SRATE;
          
          public String getWAREHOUSE(){
              return WAREHOUSE;
          }
          public void setWAREHOUSE(String WAREHOUSE){
              this.WAREHOUSE=WAREHOUSE;
          }
          
           public String getPRODUCT(){
              return PRODUCT;
          }
          public void setPRODUCT(String PRODUCT){
              this.PRODUCT=PRODUCT;
          }
          
          public String getUNITTYPE(){
              return UNITTYPE;
          }
          public void setUNITTYPE(String UNITTYPE){
              this.UNITTYPE=UNITTYPE;
          }
           public Double getQUANTITY(){
              return QUANTITY;
          }
          public void setQUANTITY(Double QUANTITY){
              this.QUANTITY=QUANTITY;
          }
           public String getCRDATE(){
               String x = Formats.TIMESTAMP.formatValue(CRDATE) ;
               return x;
          }
          public void setCRDATE(Date CRDATE){
              
              this.CRDATE=CRDATE;
          }
         
           public String getCATEGORY(){
              return CATEGORY;
          }
          public void setCATEGORY(String CATEGORY){
              this.CATEGORY=CATEGORY;
          }
          
           public Double getPURCRATE(){
              return PURCRATE;
          }
          public void setPURCRATE(Double PURCRATE){
              this.PURCRATE=PURCRATE;
          }
           public Double getCONVERTING_RATIO(){
              return CONVERTING_RATIO;
          }
          public void setCONVERTING_RATIO(Double CONVERTING_RATIO){
              this.CONVERTING_RATIO=CONVERTING_RATIO;
          }
           public Double getFinalQTY(){
              return FinalQTY;
          }
          public void setFinalQTY(Double FinalQTY){
              this.FinalQTY=FinalQTY;
          }
          
          public Double getSRATE(){
              return SRATE;
          }
          public void setSRATE(Double SRATE){
              this.SRATE=SRATE;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            WAREHOUSE = dr.getString(1);
            PRODUCT = dr.getString(2);
            UNITTYPE = dr.getString(3);
            QUANTITY = dr.getDouble(4);
            CATEGORY = dr.getString(5);
            PURCRATE = dr.getDouble(6);
            CRDATE = dr.getTimestamp(7);
            CONVERTING_RATIO = dr.getDouble(8);
            FinalQTY = dr.getDouble(9);
            SRATE = dr.getDouble(10);
            
            
            
            
        }

        public Object getKey() {
           return this;
        }
          
    }
     
     
     
    
    
    
}
