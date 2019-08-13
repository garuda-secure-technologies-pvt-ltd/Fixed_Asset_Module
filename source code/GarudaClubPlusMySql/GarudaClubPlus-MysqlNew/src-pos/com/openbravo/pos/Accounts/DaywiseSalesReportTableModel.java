

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
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


public class DaywiseSalesReportTableModel extends BeanFactoryDataSingle{
    
    
     private AppView m_App;
     private Session s;
     private int size;
     private List<DaywiseSalesReportTableModel.SalesInfo> data;
    
       @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
     public int getSize()
      {
        return size;
      }
     
     public List<DaywiseSalesReportTableModel.SalesInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<DaywiseSalesReportTableModel.SalesInfo>();
      }
     
     
     
     
      public static DaywiseSalesReportTableModel LoadTaxSaleInfo(AppView app , Date FromDate , Date ToDate ) throws BasicException{
        DaywiseSalesReportTableModel GuestInfo = new DaywiseSalesReportTableModel(); 
            
            try{
                   GuestInfo.data = new ArrayList<DaywiseSalesReportTableModel.SalesInfo>();
                   GuestInfo.data = new StaticSentence(app.getSession(), "select l.name as warehouse , COUNT(*) as nos , sum(tl.amount) as taxamount,  t.name as taxname , \n" +
                                                                            "t.id as id , t.rate as taxrate  ,  sum(tl.base) as taxbase , TOTBILLAMT as TotalBill , TOTTAXAMT as totalTax  , totalbills , grandbilltotal ,  grandtaxtotal      \n" +
                                                                            "from  taxsalereport tl  , taxes t , locations l \n" +
                                                                            "where   tl.taxid=t.id and tl.warehouse = l.id\n" +
                                                                            "group by l.name , t.name\n" +
                                                                            "UNION \n" +
                                                                            "select 'Grand Total'  as warehouse , COUNT(*) as nos , sum(tl.amount) as taxamount,  t.name as taxname , \n" +
                                                                            "t.id as id , t.rate as taxrate  ,  sum(tl.base) as taxbase , \n" +
                                                                            "(select sum(base) from taxsalereport )  as TotalBill , (select sum(amount) from taxsalereport ) as totalTax , totalbills     , grandbilltotal ,  grandtaxtotal     \n" +
                                                                            "from  taxsalereport tl  , taxes t , locations l \n" +
                                                                            "where   tl.taxid=t.id and tl.warehouse = l.id\n" +
                                                                            "group by t.name", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.TIMESTAMP , Datas.TIMESTAMP    }), new SerializerReadClass(DaywiseSalesReportTableModel.SalesInfo.class)).list(new Object[]{ToDate, FromDate  , ToDate, FromDate });

                   GuestInfo.size = GuestInfo.data.size();

                   

               }
               catch(BasicException ex){
                   Logger.getLogger(DaywiseSalesReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
               }
     return GuestInfo;
  
     }
    
     
      
      
     public static class SalesInfo implements SerializableRead,IKeyed {

        private String WAREHOUSE;
        private int BILLCOUNT;
        private Double TAXTOTAL;
        private String TAXCATNAME;
        private String ID;
        private Double TAXRATE;
        private Double PRODSALEAMT;
        private Double BILLAMT;
        private Double ActualBillAmt ;
        private Double ActualTaxAmount; 
        private Double otherTaxAmt ;
        private Double otherBillAmt; 
        private int TotalInvoice ; 
        private Double GrandBillTotal;
        private Double GrandTaxTotal;
        
         public String getWAREHOUSE(){
              return WAREHOUSE;
          }
          public void setWAREHOUSE(String WAREHOUSE){
              this.WAREHOUSE=WAREHOUSE;
          }
          public int getBILLCOUNT(){
              return BILLCOUNT;
          }
          public void setBILLCOUNT(int BILLCOUNT){
              this.BILLCOUNT = BILLCOUNT;
          }
          public Double getTAXTOTAL(){
              return TAXTOTAL;
          }
          public void setTAXTOTAL(Double TAXTOTAL){
              this.TAXTOTAL =TAXTOTAL;
          }
        
           public String getTAXCATNAME(){
              return TAXCATNAME;
          }
          public void setBalanceAmt(String TAXCATNAME){
              this.TAXCATNAME =TAXCATNAME;
          }
          
          public String getID(){
              return ID;
          }
          public void setMEMID(String ID){
              this.ID = ID;
          }
          
         
          
          public Double getTAXRATE(){
              return TAXRATE;
          }
          public void setTAXRATE(Double TAXRATE){
              this.TAXRATE=TAXRATE;
          }
          
        
         
          
          public Double getPRODSALEAMT(){
              return PRODSALEAMT;
          }
          public void setPRODSALEAMT(Double PRODSALEAMT){
              this.PRODSALEAMT=PRODSALEAMT;
          }
          
          public Double getBILLAMT(){
              return BILLAMT;
          }
          public void setBILLAMT(Double BILLAMT){
              this.BILLAMT=BILLAMT;
          }
          
          
          public Double getActualBillAmt(){
              return ActualBillAmt;
          }
          public void setActualBillAmt(Double ActualBillAmt){
              this.ActualBillAmt=ActualBillAmt;
          }
          
          public Double getActualTaxAmount(){
              return ActualTaxAmount;
          }
          public void setActualTaxAmount(Double ActualTaxAmount){
              this.ActualTaxAmount=ActualTaxAmount;
          }
          
          
          
          public Double getotherBillAmt(){
              return otherBillAmt;
          }
          public void setotherBillAmt(Double otherBillAmt){
              this.otherBillAmt=otherBillAmt;
          }
          
           public Double getotherTaxAmt(){
              return otherTaxAmt;
          }
          public void setotherTaxAmt(Double otherTaxAmt){
              this.otherTaxAmt=otherTaxAmt;
          }
          
          public int getTotalInvoice(){
              return TotalInvoice;
          }
          public void setTotalInvoice(int TotalInvoice){
              this.TotalInvoice = TotalInvoice;
          }
          
          public Double getGrandBillTotal(){
              return GrandBillTotal;
          }
          public void setGrandBillTotal(Double GrandBillTotal){
              this.GrandBillTotal=GrandBillTotal;
          }
          
           public Double getGrandTaxTotal(){
              return GrandTaxTotal;
          }
          public void setGrandTaxTotal(Double GrandTaxTotal){
              this.GrandTaxTotal=GrandTaxTotal;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                WAREHOUSE = dr.getString(1);
                BILLCOUNT = dr.getInt(2);
                TAXTOTAL = dr.getDouble(3);
                TAXCATNAME = dr.getString(4);
                ID = dr.getString(5);
                TAXRATE = dr.getDouble(6);
                PRODSALEAMT = dr.getDouble(7);
              //  BILLAMT = dr.getDouble(8);
                ActualBillAmt = dr.getDouble(8);
                ActualTaxAmount = dr.getDouble(9);
              ///  otherBillAmt = dr.getDouble(11);
              //  otherTaxAmt = dr.getDouble(12);
                TotalInvoice = dr.getInt(10);
                GrandBillTotal = dr.getDouble(11);
                GrandTaxTotal = dr.getDouble(12);
          }

        public Object getKey() {
           return this;
        }

       
     }   
       
     
     
     
    
}
