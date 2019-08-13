
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
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PJ_taxwiseReportTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<PJ_taxwiseReportTableModel.PurchaseInfo> data;
    private int size;  
    private final static String[] RoomHeader = {  "DATE" , "Invoice No" , "Vendor" , "Tin No" , "Amount" , "Tax", "Total" , "Tax Perc"}; 
    static DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
     
     @Override
    public void init(Session s) {
        this.s=s;
    }
    
    
     
      public static PJ_taxwiseReportTableModel LoadPurchaseDetailsAll(AppView app ) throws BasicException{
        PJ_taxwiseReportTableModel GuestInfo = new PJ_taxwiseReportTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<PJ_taxwiseReportTableModel.PurchaseInfo>();
          
            GuestInfo.data = new StaticSentence(app.getSession(), "select id, invoiceno , amt1  , tax1 , amt2,tax2,amt3,tax3,amt4,tax4,amt5,tax5,addchrg,total ,date,vendor,tinno,otheramt,othertax  FROM PJ_REPORT order by date ", new SerializerWriteBasic(new Datas[]{   }), new SerializerReadClass(PJ_taxwiseReportTableModel.PurchaseInfo.class)).list();
          
            
            
            
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(PJ_taxwiseReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
      public List<PJ_taxwiseReportTableModel.PurchaseInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<PJ_taxwiseReportTableModel.PurchaseInfo>();
      }
      
      
     public static class PurchaseInfo implements SerializableRead,IKeyed {

        private String ID;
       
        private String INVOICENO;
        private Double AMT1;
        private Double TAX1;
        private Double AMT2;
        private Double TAX2;
        private Double AMT3;
        private Double TAX3;
        private Double AMT4;
        private Double TAX4;
        private Double AMT5;
        private Double TAX5;
        private Double ADDCHRG;
        private Double TOTAL ;
        private String date;
        private String Vendor;
        private String tinno;
        private Double OTHERAMT;
        private Double OTHERTAX;
        
         public String getID(){
              return ID;
          }
          public void SETID(String ID){
              this.ID=ID;
          }
         
          public String getINVOICENO(){
              return INVOICENO;
          }
          public void SETINVOICENO(String INVOICENO){
              this.INVOICENO=INVOICENO;
          }
          
          public Double getAMT1(){
              return AMT1;
          }
          public void SETAMT1(Double AMT1){
              this.AMT1=AMT1;
          }
          public Double getAMT2(){
              return AMT2;
          }
          public void SETAMT2(Double AMT2){
              this.AMT2=AMT2;
          }
          
          
          public Double getAMT3(){
              return AMT3;
          }
          public void SETAMT3(Double AMT3){
              this.AMT3=AMT3;
          }
          
          public Double getAMT4(){
              return AMT4;
          }
          public void SETAMT4(Double AMT4){
              this.AMT4=AMT4;
          }
          
          public Double getAMT5(){
              return AMT5;
          }
          public void SETAMT5(Double AMT5){
              this.AMT5=AMT5;
          }
          
          public Double getTAX1(){
              return TAX1;
          }
          public void SETTAX1(Double TAX1){
              this.TAX1=TAX1;
          }
          
          public Double getTAX2(){
              return TAX2;
          }
          public void SETTAX2(Double TAX2){
              this.TAX2=TAX2;
          }
          
          public Double getTAX3(){
              return TAX3;
          }
          public void SETTAX3(Double TAX3){
              this.TAX3=TAX3;
          }
          
          public Double getTAX4(){
              return TAX4;
          }
          public void SETTAX4(Double TAX4){
              this.TAX4=TAX4;
          }
          public Double getTAX5(){
              return TAX5;
          }
          public void SETTAX5(Double TAX5){
              this.TAX5=TAX5;
          }
          
          public Double getADDCHRG(){
              return ADDCHRG;
          }
          public void SETADDCHRG(Double ADDCHRG){
              this.ADDCHRG=ADDCHRG;
          }
          public Double getTOTAL(){
              return TOTAL;
          }
          public void SETTOTAL(Double TOTAL){
              this.TOTAL=TOTAL;
          }
          public String getCrdate(){
              String x = date;
              return x;
          }
          public void setCrDate(String date){
              this.date=date;
          }
          public String getVendor(){
              return Vendor;
          }
          public void setVendor(String Vendor){
              this.Vendor=Vendor;
          }
          public String getTinno(){
              return tinno;
          }
          public void setTinno(String tinno){
              this.tinno=tinno;
          }
          
          public Double getOTHERAMT(){
              return OTHERAMT;
          }
          public void SETOTHERAMT(Double OTHERAMT){
              this.OTHERAMT=OTHERAMT;
          }
          public Double GETOTHERTAX(){
              return OTHERTAX;
          }
          public void SETOTHERTAX(Double OTHERTAX){
              this.OTHERTAX=OTHERTAX;
          }
          
          
          
          
          public void readValues(DataRead dr) throws BasicException {
           
             
              ID=dr.getString(1);
              INVOICENO = dr.getString(2);
              AMT1 = dr.getDouble(3);
              TAX1 = dr.getDouble(4);
              AMT2 = dr.getDouble(5);
              TAX2 = dr.getDouble(6);
              AMT3 = dr.getDouble(7);
              TAX3 = dr.getDouble(8);
              AMT4 = dr.getDouble(9);
              TAX4 = dr.getDouble(10);
              AMT5 = dr.getDouble(11);
              TAX5 = dr.getDouble(12);
              ADDCHRG = dr.getDouble(13);
              TOTAL = dr.getDouble(14);
              date = dr.getString(15);
              Vendor=dr.getString(16);
              tinno=dr.getString(17);
              OTHERAMT=dr.getDouble(18);
              OTHERTAX=dr.getDouble(19);
              
              
          }

        public Object getKey() {
           return this;
        }

   }    
      
     
    
    
    
}
