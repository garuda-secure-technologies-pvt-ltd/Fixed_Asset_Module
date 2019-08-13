

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.sms.EmailSentTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CuetomerwiseSalesTableModel extends BeanFactoryDataSingle{
   
    
    
    private Session s;
    private int size;
    private List<CuetomerwiseSalesTableModel.CustSalesInfo> data;
     
     
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
  // load all customers
    
     public static CuetomerwiseSalesTableModel LoadCustWiseSaleAll(AppView app , Date FmDate , Date ToDate) throws BasicException{
        CuetomerwiseSalesTableModel GuestInfo = new CuetomerwiseSalesTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT CUSTID,CNAME,SUM(TOTAL), SUM(TAXTOTAL) , WAREHOUSE FROM( \n" +
                                                                        "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL.AMOUNT)AS TOTAL,  SUM(BILL.TAXTOTAL) AS TAXTOTAL ,\n" +
                                                                        "LOCATIONS.NAME AS WAREHOUSE FROM BILL JOIN LOCATIONS ON \n" +
                                                                        "BILL.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON BILL.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                        "WHERE BILL.CREATEDDATE>=?  AND BILL.CREATEDDATE<=? \n" +
                                                                        "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE\n" +
                                                                        "UNION ALL\n" +
                                                                        "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL_ARV.AMOUNT)AS TOTAL,  SUM(BILL_ARV.TAXTOTAL) AS TAXTOTAL, \n" +
                                                                        "LOCATIONS.NAME AS WAREHOUSE FROM BILL_ARV JOIN \n" +
                                                                        "LOCATIONS ON BILL_ARV.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON \n" +
                                                                        "BILL_ARV.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                        "WHERE  BILL_ARV.CREATEDDATE>=?  AND BILL_ARV.CREATEDDATE<=? \n" +
                                                                        "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE  )  \n" +
                                                                        "AS CUSTWISE GROUP BY CUSTID,CNAME,WAREHOUSE ORDER BY 5,1 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP }) , new SerializerReadClass(CuetomerwiseSalesTableModel.CustSalesInfo.class)).list(new Object[]{ FmDate ,  ToDate , FmDate ,  ToDate  });
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
//  load all customers with location 
     
      public static CuetomerwiseSalesTableModel LoadCustWiseSaleAllwithLocation(AppView app , Date FmDate , Date ToDate , String WID) throws BasicException{
        CuetomerwiseSalesTableModel GuestInfo = new CuetomerwiseSalesTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT CUSTID,CNAME,SUM(TOTAL),SUM(TAXTOTAL) , WAREHOUSE FROM( \n" +
                                                                        "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL.AMOUNT)AS TOTAL,   SUM(BILL.TAXTOTAL) AS TAXTOTAL, \n" +
                                                                        "LOCATIONS.NAME AS WAREHOUSE FROM BILL JOIN LOCATIONS ON \n" +
                                                                        "BILL.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON BILL.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                        "WHERE BILL.CREATEDDATE>= ?  AND BILL.CREATEDDATE<= ?  AND LOCATIONS.ID=? \n" +
                                                                        "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE\n" +
                                                                        "UNION ALL\n" +
                                                                        "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL_ARV.AMOUNT)AS TOTAL,   SUM(BILL_ARV.TAXTOTAL) AS TAXTOTAL, \n" +
                                                                        "LOCATIONS.NAME AS WAREHOUSE FROM BILL_ARV JOIN \n" +
                                                                        "LOCATIONS ON BILL_ARV.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON \n" +
                                                                        "BILL_ARV.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                        "WHERE  BILL_ARV.CREATEDDATE>=?  AND BILL_ARV.CREATEDDATE<=? AND LOCATIONS.ID=? \n" +
                                                                        "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE  )  \n" +
                                                                        "AS CUSTWISE GROUP BY CUSTID,CNAME,WAREHOUSE ORDER BY 5,1  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.STRING, Datas.TIMESTAMP , Datas.TIMESTAMP,Datas.STRING  }) , new SerializerReadClass(CuetomerwiseSalesTableModel.CustSalesInfo.class)).list(new Object[]{ FmDate ,  ToDate ,WID, FmDate ,  ToDate , WID });
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
// load individual customer with all warehouse.......
      
      
        public static CuetomerwiseSalesTableModel LoadCustWiseSaleOneCustwithAllLocation(AppView app , Date FmDate , Date ToDate , String WID) throws BasicException{
        CuetomerwiseSalesTableModel GuestInfo = new CuetomerwiseSalesTableModel(); 
    
            try{
                   GuestInfo.data = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
                   GuestInfo.data = new StaticSentence(app.getSession(), "SELECT CUSTID,CNAME,SUM(TOTAL),SUM(TAXTOTAL) , WAREHOUSE FROM( \n" +
                                                                               "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL.AMOUNT)AS TOTAL,  SUM(BILL.TAXTOTAL) AS TAXTOTAL , \n" +
                                                                               "LOCATIONS.NAME AS WAREHOUSE FROM BILL JOIN LOCATIONS ON \n" +
                                                                               "BILL.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON BILL.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                               "WHERE BILL.CREATEDDATE>= ?  AND BILL.CREATEDDATE<= ? AND CUSTOMERS.ID=?   \n" +
                                                                               "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE\n" +
                                                                               "UNION ALL\n" +
                                                                               "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL_ARV.AMOUNT)AS TOTAL,  SUM(BILL_ARV.TAXTOTAL) AS TAXTOTAL ,\n" +
                                                                               "LOCATIONS.NAME AS WAREHOUSE FROM BILL_ARV JOIN \n" +
                                                                               "LOCATIONS ON BILL_ARV.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON \n" +
                                                                               "BILL_ARV.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                               "WHERE  BILL_ARV.CREATEDDATE>=?   AND BILL_ARV.CREATEDDATE<= ? AND CUSTOMERS.ID=?  \n" +
                                                                               "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE  )  \n" +
                                                                               "AS CUSTWISE GROUP BY CUSTID,CNAME,WAREHOUSE ORDER BY 5,1  ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING  }) , new SerializerReadClass(CuetomerwiseSalesTableModel.CustSalesInfo.class)).list(new Object[]{ FmDate ,  ToDate ,WID ,  FmDate ,  ToDate , WID });
                   GuestInfo.size = GuestInfo.data.size();
               }
               catch(BasicException ex){
                   Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
     return GuestInfo;
  
     }
        
        
// load individual customer with one warehouse..        
        
    public static CuetomerwiseSalesTableModel LoadCustWiseSaleOneCustwithLocation(AppView app , Date FmDate , Date ToDate , String WID , String CustomerId) throws BasicException{
        CuetomerwiseSalesTableModel GuestInfo = new CuetomerwiseSalesTableModel(); 
    
            try{
                   GuestInfo.data = new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
                   GuestInfo.data = new StaticSentence(app.getSession(), "SELECT CUSTID,CNAME,SUM(TOTAL),SUM(TAXTOTAL) , WAREHOUSE FROM( \n" +
                                                                            "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL.AMOUNT)AS TOTAL,   SUM(BILL.TAXTOTAL) AS TAXTOTAL , \n" +
                                                                            "LOCATIONS.NAME AS WAREHOUSE FROM BILL JOIN LOCATIONS ON \n" +
                                                                            "BILL.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON BILL.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                            "WHERE BILL.CREATEDDATE>= ?  AND BILL.CREATEDDATE<= ?  AND LOCATIONS.ID= ? AND CUSTOMERS.ID= ? \n" +
                                                                            "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE\n" +
                                                                            "UNION ALL\n" +
                                                                            "SELECT CUSTOMERS.SEARCHKEY AS CUSTID,CUSTOMERS.NAME AS CNAME, SUM(BILL_ARV.AMOUNT)AS TOTAL  , SUM(BILL_ARV.TAXTOTAL) AS TAXTOTAL,  \n" +
                                                                            "LOCATIONS.NAME AS WAREHOUSE FROM BILL_ARV JOIN \n" +
                                                                            "LOCATIONS ON BILL_ARV.WAREHOUSE=LOCATIONS.ID JOIN CUSTOMERS ON \n" +
                                                                            "BILL_ARV.CUSTOMER LIKE CONCAT(CUSTOMERS.ID,'%')\n" +
                                                                            "WHERE  BILL_ARV.CREATEDDATE>= ?   AND BILL_ARV.CREATEDDATE<= ? AND LOCATIONS.ID= ?  AND CUSTOMERS.ID= ? \n" +
                                                                            "GROUP BY CUSTOMERS.SEARCHKEY,CUSTOMERS.NAME,WAREHOUSE  )  \n" +
                                                                            "AS CUSTWISE GROUP BY CUSTID,CNAME,WAREHOUSE ORDER BY 5,1 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING , Datas.STRING ,  Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.STRING }) , new SerializerReadClass(CuetomerwiseSalesTableModel.CustSalesInfo.class)).list(new Object[]{ FmDate ,  ToDate ,WID , CustomerId ,  FmDate ,  ToDate ,WID , CustomerId  });
                   GuestInfo.size = GuestInfo.data.size();
               }
               catch(BasicException ex){
                   Logger.getLogger(CuetomerwiseSalesTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
     return GuestInfo;
  
     } 
     
      public List<CuetomerwiseSalesTableModel.CustSalesInfo> getCustomerWiseList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<CuetomerwiseSalesTableModel.CustSalesInfo>();
      }
     
     
      public static class CustSalesInfo implements SerializableRead,IKeyed {

        private String MEMNO;
        private String MEMNAME;
        private Double AMOUNT;
        private String WAREHOUSE;
        private Double TAXTOTAL;
       
       
         
         public String getMEMNO(){
             return MEMNO;
         }
         public void setMEMNO(String MEMNO){
             this.MEMNO=MEMNO;
         }
         
         public String getMEMNAME(){
             return MEMNAME;
         }
         public void setMEMNAME(String MEMNAME){
             this.MEMNAME=MEMNAME;
         }
         public Double getAMOUNT(){
             return AMOUNT;
         }
         public void setAMOUNT(Double AMOUNT){
             this.AMOUNT=AMOUNT;
         }
         
         public Double getTAXTOTAL(){
             return TAXTOTAL;
         }
         public void setTAXTOTAL(Double TAXTOTAL){
             this.TAXTOTAL=TAXTOTAL;
         }
         
         public String getWAREHOUSE(){
             return WAREHOUSE;
         }
         public void setWAREHOUSE(String WAREHOUSE){
             this.WAREHOUSE=WAREHOUSE;
         }
         
        public void readValues(DataRead dr) throws BasicException {
           
             
                MEMNO = dr.getString(1);
                MEMNAME = dr.getString(2);
                AMOUNT = dr.getDouble(3);
                TAXTOTAL = dr.getDouble(4);
                WAREHOUSE = dr.getString(5);
               
               
               
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
}
