/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
//import com.openbravo.pos.inventory.TaxCalculate.WTaxGetSetMethod;
import com.openbravo.pos.panels.MemberListReport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author safwan
 */
public class MultitaxWarehousewiseList extends BeanFactoryDataSingle {
    private Session s;
   private List<MultitaxWarehousewiseList. WarehouseGetSetMethod> Warehousewise_List; 
   
    @Override
    public void init(Session s) {
      this.s=s;
    }
    public static  MultitaxWarehousewiseList LoadAll_Perticular_Type(AppView app,String memty)throws BasicException{
        MultitaxWarehousewiseList All_list = new MultitaxWarehousewiseList(); 
          
          
          try{
            All_list.Warehousewise_List = new ArrayList<MultitaxWarehousewiseList. WarehouseGetSetMethod>();
            
            All_list.Warehousewise_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS CNAME,\n" +
"   P.NAME,\n" +
"   P.REFERENCE,COALESCE(T1.name, ' ') AS TAX1,\n" +
"CASE\n" +
"WHEN P.BASIC2=0 AND P.BASIC3=1 THEN    CONCAT(COALESCE(T2.name, ' '),'(C)')\n" +
"WHEN P.BASIC2=0 AND P.BASIC3=0 THEN    CONCAT(COALESCE(T2.name, ' '),'(C)') \n" +
" ELSE COALESCE(T2.name, ' ') END AS TAX2 , \n" +
"CASE\n" +
"WHEN P.BASIC2=1 AND P.BASIC3=0 THEN    CONCAT(COALESCE(T3.name, ' '),'(C)') \n" +
"WHEN P.BASIC2=0 AND P.BASIC3=0 THEN    CONCAT(COALESCE(T3.name, ' '),'(C)')\n" +
" ELSE COALESCE(T3.name, ' ') END AS TAX3 ,\n" +
"\n" +
"   COALESCE(T11.RATE, ' ') as rate1,\n" +
"   COALESCE(T22.RATE, ' ') as rate2,\n" +
"   COALESCE(T33.RATE, ' ') as rate3,\n" +
"   \n" +
"       TRUNCATE(PRICESELL,2) as BP,\n" +
"CASE\n" +
"WHEN P.BASIC2=0 AND P.BASIC3=0 THEN TRUNCATE( (COALESCE(T33.RATE, ' ') *(COALESCE(T22.RATE, ' ') *(COALESCE(T11.RATE, ' ') * PRICESELL+PRICESELL)+(COALESCE(T11.RATE, ' ') * PRICESELL+PRICESELL))+(COALESCE(T22.RATE, ' ') *(COALESCE(T11.RATE, ' ') * PRICESELL+PRICESELL)+(COALESCE(T11.RATE, ' ') * PRICESELL+PRICESELL))) ,2) \n" +
"WHEN P.BASIC2=1 AND P.BASIC3=0 THEN TRUNCATE( (COALESCE(T33.RATE, ' ') *((COALESCE(T11.RATE, ' ') * PRICESELL)+PRICESELL))+((COALESCE(T11.RATE, ' ') * PRICESELL)+PRICESELL)+(COALESCE(T22.RATE, ' ') * PRICESELL),2)\n" +
"WHEN P.BASIC2=0 AND P.BASIC3=1 THEN TRUNCATE( (COALESCE(T22.RATE, ' ') *((COALESCE(T11.RATE, ' ') * PRICESELL)+PRICESELL))+((COALESCE(T11.RATE, ' ') * PRICESELL)+PRICESELL)+(COALESCE(T33.RATE, ' ') * PRICESELL),2) \n" +
"ELSE TRUNCATE((COALESCE(T11.RATE, ' ') * PRICESELL + COALESCE(T22.RATE, ' ') * PRICESELL + COALESCE(T33.RATE, ' ') * PRICESELL) + PRICESELL,2)   \n" +
"END AS SP\n" +
"\n" +
"from\n" +
"    products P \n" +
"        LEFT JOIN\n" +
"    taxcategories T1 ON (T1.ID = P.TAXCAT)\n" +
"        LEFT JOIN\n" +
"    taxcategories T2 ON (T2.ID = P.TAXCAT2)\n" +
"        LEFT JOIN\n" +
"    taxcategories T3 ON (T3.ID = P.TAXCAT3)\n" +
"        LEFT JOIN\n" +
"    taxes T11 ON (T11.CATEGORY = P.TAXCAT)\n" +
"        LEFT JOIN\n" +
"    taxes T22 ON (T22.CATEGORY = P.TAXCAT2)\n" +
"        LEFT JOIN\n" +
"    taxes T33 ON (T33.CATEGORY = P.TAXCAT3)\n" +
"        LEFT JOIN\n" +
"    CATEGORIES C ON ( C.ID=P.CATEGORY ),LOCATIONS L,\n" +
"    (SELECT @rownum:=0) r\n" +
"WHERE\n" +
"   P.LOCATION = L.ID \n" +
"	AND L.NAME = ?\n" +
"ORDER BY C.name,P.NAME ", new SerializerWriteBasic(new Datas[]{  Datas.STRING }  )  ,new SerializerReadClass(MultitaxWarehousewiseList.WarehouseGetSetMethod.class)).list(new Object[]{  memty });
            //All_list.Warehousewise_List =   new StaticSentence(app.getSession(), " SELECT COALESCE(T1.RATE,' ') as rate1,COALESCE(T2.RATE,' ') as rate2,COALESCE(T3.RATE,' ')  as rate1 FROM products P LEFT JOIN taxes T1 ON(T1.CATEGORY =P.TAXCAT) LEFT JOIN taxes T2 ON(T2.CATEGORY =P.TAXCAT2) LEFT JOIN taxes T3 ON(T3.CATEGORY =P.TAXCAT3)  " , null  ,new SerializerReadClass(MultitaxWarehousewiseList. WarehouseGetSetMethod.class)).list();   
 /*  SELECT  P.NAME,P.REFERENCE, COALESCE(T1.name,' ') as tax1, COALESCE(T2.name,' ') as tax2,COALESCE(T3.name,' ') as tax3,COALESCE(T11.RATE,' ') as rate1,COALESCE(T22.RATE,' ') as rate2,COALESCE(T33.RATE,' ')  as rate3 ,PRICESELL,(COALESCE(T11.RATE,' ')*PRICESELL+ COALESCE(T22.RATE,' ')*PRICESELL+COALESCE(T33.RATE,' ')*PRICESELL)+PRICESELL AS TOTAL 
        from products P LEFT JOIN taxcategories T1 ON(T1.ID =P.TAXCAT) LEFT JOIN taxcategories T2 ON(T2.ID =P.TAXCAT2) LEFT JOIN taxcategories T3 ON(T3.ID =P.TAXCAT3) LEFT JOIN taxes T11 ON(T11.CATEGORY =P.TAXCAT) LEFT JOIN taxes T22 ON(T22.CATEGORY =P.TAXCAT2) LEFT JOIN taxes T33 ON(T33.CATEGORY =P.TAXCAT3);  */     
          
 /*--- SELECT  P.NAME,P.REFERENCE, COALESCE(T1.name,' ') as tax1, COALESCE(T2.name,' ') as tax2,COALESCE(T3.name,' ') as tax3,COALESCE(T11.RATE,' ') as rate1,COALESCE(T22.RATE,' ') as rate2,COALESCE(T33.RATE,' ')  as rate3 ,PRICESELL,(COALESCE(T11.RATE,' ')*PRICESELL+ COALESCE(T22.RATE,' ')*PRICESELL+COALESCE(T33.RATE,' ')*PRICESELL)+PRICESELL AS TOTAL 
    from products P LEFT JOIN taxcategories T1 ON(T1.ID =P.TAXCAT) LEFT JOIN taxcategories T2 ON(T2.ID =P.TAXCAT2) LEFT JOIN taxcategories T3 ON(T3.ID =P.TAXCAT3) LEFT JOIN taxes T11 ON(T11.CATEGORY =P.TAXCAT) LEFT JOIN taxes T22 ON(T22.CATEGORY =P.TAXCAT2) LEFT JOIN taxes T33 ON(T33.CATEGORY =P.TAXCAT3),LOCATIONS L WHERE P.LOCATION=L.ID AND L.NAME="W-TROLLEY-2" GROUP BY P.ID,P.NAME ORDER BY c.name,P.NAME; --TRUNCATE(PRICESELL,2)--CAST(PRICESELL AS DECIMAL(10,2)) AS PRICE--TRUNCATE((COALESCE(T11.RATE,' ')*PRICESELL+ COALESCE(T22.RATE,' ')*PRICESELL+COALESCE(T33.RATE,' ')*PRICESELL)+PRICESELL,2) AS TOTAL--CAST((COALESCE(T11.RATE, ' ') * PRICESELL + COALESCE(T22.RATE, ' ') * PRICESELL + COALESCE(T33.RATE, ' ') * PRICESELL) + PRICESELL
      AS DECIMAL(10,2)) AS TOTAL*/      
          }                                                                                                                                                                                                                                                                                                                                                                                                                
          
          catch(BasicException ex){
            
            Logger.getLogger(MultitaxWarehousewiseList.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_list;
     }
    
   public List<MultitaxWarehousewiseList. WarehouseGetSetMethod> getMemberList(){
     
           if(Warehousewise_List!=null)
        {
            return Warehousewise_List;
        }
        else
            return new ArrayList<MultitaxWarehousewiseList.WarehouseGetSetMethod>();
           
      }
    
   public static class WarehouseGetSetMethod implements SerializableRead,IKeyed 
    {
          
          private String Product_Name;
          private String Reference;
          private String Tax1;
          private String Tax2;
          private String Tax3;
          private Double Total;
          private Double SellingPrice;
          private Integer Slno;
          private String CatName;
          /* public Integer getsno(){
              return Slno;
          }
          public void setsno(Integer Slno){
              this.Slno=Slno;
          }
         */

          public String getProductName(){
              return Product_Name;
          }
          public void setProductName(String Product_Name){
              this.Product_Name=Product_Name;
          }
          
           public String getReference(){
              return Reference;
          }
          public void setReference(String Reference){
              this.Reference=Reference;
          }
          
           public String getTax1(){
              return Tax1;
          }
          public void setTax1(String Tax1){
              this.Tax1=Tax1;
          }
           public String getTax2(){
              return Tax2;
          }
           public void setTax2(String Tax2){
              this.Tax2=Tax2;
          }
            
            public String getTax3(){
              return Tax3;
          }
           public void setTax3(String Tax3){
              this.Tax3=Tax3;
          }
           
            public Double getSellingPrice(){
                return  SellingPrice;
          }
           public void setSellingPrice(Double SellingPrice){
             
            this.SellingPrice= SellingPrice;
          }
          
           
            public Double getTotal(){
                return  Total;
          }
           public void setTotal(Double Total){
              //Double Total1 =getRate1();
            this.Total= Total;
          }
          
           public String  getCatName(){
                return  CatName;
          }
           public void setCatName(String  CatName){
              //Double Total1 =getRate1();
            this.CatName= CatName;
          }
        
         
        @Override
        public void readValues(DataRead dr) throws BasicException {
            //Slno = dr.getInt(1);
            CatName= dr.getString(1);
            Product_Name= dr.getString(2);
            Reference= dr.getString(3);
            Tax1 = dr.getString(4);
            Tax2= dr.getString(5);
            Tax3= dr.getString(6);
            SellingPrice= dr.getDouble(10);
            Total= dr.getDouble(11);
            
           }

        @Override
        public Object getKey() {
            return this;
            
        }

        
    }
    
    
}
