/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev1
 */
public class BillQTCancellationModel extends BeanFactoryDataSingle {

      private Session s;
   private List<BillQTCancellationModel.GetSetMethod1> billqt_List;  
   private List<BillQTCancellationModel.GetSetMethod2> billqt_List2;  
    
    @Override
    public void init(Session s) {
  this.s=s;
    }
    
    
    //1----------------------for qt cancellation----------------------------------------------
     public static  BillQTCancellationModel LoadAllqtdisc(AppView app,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc.billqt_List = new ArrayList<BillQTCancellationModel.GetSetMethod1>();

            All_qtdisc.billqt_List = new StaticSentence(app.getSession(), " SELECT D.QTITEMID,concat(concat(c.SEARCHKEY,' - '),D.CUSTOMER_ID ) as customer,p.name product,D.QTY,D.RATE,D.AMOUNT,D.REASON,D.USER_ID as created_By,D.crdate,D.authorised ,l.name warehouse\n" +
"FROM DISCOUNTLIST D ,customers c,products p,locations l WHERE D.CUSTOMER_ID=c.NAME and D.PRODUCT_ID=p.ID and l.id=D.WAREHOUSE   and D.CRDATE>=? and D.CRDATE<=? order by  D.QTITEMID  " , new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod1.class)).list(new Object[]{  frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc;
     }
     
     //2
   
     public static  BillQTCancellationModel Loadqtdisc(AppView app,String memty,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc.billqt_List = new ArrayList<BillQTCancellationModel.GetSetMethod1>();

            All_qtdisc.billqt_List = new StaticSentence(app.getSession(), "SELECT D.QTITEMID,concat(concat(c.SEARCHKEY,' - '),D.CUSTOMER_ID ) as customer,p.name product,D.QTY,D.RATE,D.AMOUNT,D.REASON,D.USER_ID as created_By,D.crdate,D.authorised ,l.name warehouse\n" +
"FROM DISCOUNTLIST D ,customers c,products p,locations l WHERE D.CUSTOMER_ID=c.NAME and D.PRODUCT_ID=p.ID and l.id=D.WAREHOUSE   and l.name=? and D.CRDATE>=? and D.CRDATE<=?  order by  D.QTITEMID   " ,new SerializerWriteBasic(new Datas[]{  Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod1.class)).list(new Object[]{  memty,frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc;
     }
     
     //3 all-qtperiod
       public static  BillQTCancellationModel LoadAllqtdiscPeriod(AppView app,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc.billqt_List = new ArrayList<BillQTCancellationModel.GetSetMethod1>();

            All_qtdisc.billqt_List = new StaticSentence(app.getSession(), "  SELECT D.QTITEMID,concat(concat(c.SEARCHKEY,' - '),D.CUSTOMER_ID ) as customer,p.name product,D.QTY,D.RATE,D.AMOUNT,D.REASON,D.USER_ID as created_By,D.crdate,D.authorised ,l.name warehouse\n" +
"FROM DISCOUNTLIST D ,customers c,products p,locations l WHERE D.CUSTOMER_ID=c.NAME and D.PRODUCT_ID=p.ID and l.id=D.WAREHOUSE   and D.CRDATE>=? and D.CRDATE<=?  order by  D.QTITEMID " , new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod1.class)).list(new Object[]{  frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc;
     }
     
       //qtperiod
       
        //4
   
     public static  BillQTCancellationModel LoadqtdiscPeriod(AppView app,String memty,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc.billqt_List = new ArrayList<BillQTCancellationModel.GetSetMethod1>();

            All_qtdisc.billqt_List = new StaticSentence(app.getSession(), "SELECT D.QTITEMID,concat(concat(c.SEARCHKEY,' - '),D.CUSTOMER_ID ) as customer,p.name product,D.QTY,D.RATE,D.AMOUNT,D.REASON,D.USER_ID as created_By,D.crdate,D.authorised ,l.name warehouse\n" +
"FROM DISCOUNTLIST D ,customers c,products p,locations l WHERE D.CUSTOMER_ID=c.NAME and D.PRODUCT_ID=p.ID and l.id=D.WAREHOUSE   and l.name=? and D.CRDATE>=? and D.CRDATE<=?  order by  D.QTITEMID    " ,new SerializerWriteBasic(new Datas[]{  Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod1.class)).list(new Object[]{  memty,frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc;
     }
     
       
     //-------------------------------------------------------
     
      
      public List<BillQTCancellationModel.GetSetMethod1> getqtdiscList(){
           if(billqt_List!=null)
        {
            return billqt_List;
        }
        else
            return new ArrayList<BillQTCancellationModel.GetSetMethod1>();
      } 
      
     
      
    
      
          
  public static class GetSetMethod1 implements SerializableRead,IKeyed  {
      
    private String Qtid;  
    private String customername;
    private String userid;
    private String product;
    private Double qty;
    private Double rate;
   private Double Amount;
    private String reason;
    private Boolean authorised;
    private Timestamp crdate;

        public String getQtid() {
            return Qtid;
        }

        public void setQtid(String Qtid) {
            this.Qtid = Qtid;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }
       public Double getAmount() {
            return Amount;
        }

        public void setAmount(Double Amount) {
            this.Amount = Amount;
        }
        
        
        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Boolean getAuthorised() {
            return authorised;
        }

        public void setAuthorised(Boolean authorised) {
            this.authorised = authorised;
        }

        public Timestamp getCrdate() {
            return crdate;
        }

        public void setCrdate(Timestamp crdate) {
            this.crdate = crdate;
        }
           
        
          

        @Override
        public void readValues(DataRead dr) throws BasicException {
            
           Qtid=dr.getString(1); 
           customername=dr.getString(2);
           userid=dr.getString(8);
           product=dr.getString(3);
           qty=dr.getDouble(4);
           Amount=dr.getDouble(6);
           rate=dr.getDouble(5);
           reason=dr.getString(7);
           authorised=dr.getBoolean(10);
           crdate=dr.getTimestamp(9);  
        
        }

        @Override
        public Object getKey() { 
            return this;
        }

      
      
  } 
  
  
  //
   //1----------------------for bill cancellation----------------------------------------------
     public static  BillQTCancellationModel LoadAllbill_rev(AppView app,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc2 = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc2.billqt_List2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();

            All_qtdisc2.billqt_List2 = new StaticSentence(app.getSession(), "SELECT R.BILLID,concat(concat(C.searchkey,'-'),c.name) as customer,R.CREATEDBY,P.NAME product,l.name warehouse,R.QTY,R.RATE,R.REASON,R.AUTHORISED,R.CRDATE \n" +
" FROM REVERSEDBILL R,PRODUCTS P,CUSTOMERS C,locations l WHERE R.CUSTOMER =C.ID AND P.ID=R.PRODUCT and p.location=l.id and r.crdate>=? and r.crdate<=? ORDER BY R.BILLID" , new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod2.class)).list(new Object[]{  frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc2;
     }
     
      //2
   
     public static  BillQTCancellationModel Loadbill_rev(AppView app,String memty,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc2 = new BillQTCancellationModel(); 
          
          
          try{
             All_qtdisc2.billqt_List2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();

             All_qtdisc2.billqt_List2 = new StaticSentence(app.getSession(), "SELECT R.BILLID,concat(concat(C.searchkey,'-'),c.name) as customer,R.CREATEDBY,P.NAME product,l.name warehouse,R.QTY,R.RATE,R.REASON,R.AUTHORISED,R.CRDATE \n" +
" FROM REVERSEDBILL R,PRODUCTS P,CUSTOMERS C,locations l WHERE R.CUSTOMER =C.ID AND P.ID=R.PRODUCT and p.location=l.id and  l.name=? and r.crdate>=? and r.crdate<=?   ORDER BY R.BILLID" ,new SerializerWriteBasic(new Datas[]{  Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod2.class)).list(new Object[]{  memty,frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc2;
     }
  
      //3 all-qtperiod
       public static  BillQTCancellationModel LoadAllbill_discPeriod(AppView app,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc2 = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc2.billqt_List2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();

            All_qtdisc2.billqt_List2 = new StaticSentence(app.getSession(), "SELECT R.BILLID,concat(concat(C.searchkey,'-'),c.name) as customer,R.CREATEDBY,P.NAME product,l.name warehouse,R.QTY,R.RATE,R.REASON,R.AUTHORISED,R.CRDATE \n" +
" FROM REVERSEDBILL R,PRODUCTS P,CUSTOMERS C,locations l WHERE R.CUSTOMER =C.ID AND P.ID=R.PRODUCT and p.location=l.id  and r.crdate>=? and r.crdate<=?   ORDER BY R.BILLID " , new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod2.class)).list(new Object[]{  frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc2;
     }
     
       //qtperiod
       
        //4
   
     public static  BillQTCancellationModel Loadbill_revPeriod(AppView app,String memty,Date frm_date,Date to_date)throws BasicException{
          BillQTCancellationModel All_qtdisc2 = new BillQTCancellationModel(); 
          
          
          try{
            All_qtdisc2.billqt_List2 = new ArrayList<BillQTCancellationModel.GetSetMethod2>();

            All_qtdisc2.billqt_List2 = new StaticSentence(app.getSession(), " SELECT R.BILLID,concat(concat(C.searchkey,'-'),c.name) as customer,R.CREATEDBY,P.NAME product,l.name warehouse,R.QTY,R.RATE,R.REASON,R.AUTHORISED,R.CRDATE \n" +
" FROM REVERSEDBILL R,PRODUCTS P,CUSTOMERS C,locations l WHERE R.CUSTOMER =C.ID AND P.ID=R.PRODUCT and p.location=l.id and  l.name=? and r.crdate>=? and r.crdate<=?   ORDER BY R.BILLID" ,new SerializerWriteBasic(new Datas[]{  Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP }  ) ,new SerializerReadClass(BillQTCancellationModel.GetSetMethod2.class)).list(new Object[]{  memty,frm_date,to_date });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BillQTCancellationModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return  All_qtdisc2;
     }
     
     
  //-------------------------------for  bill cancellation-------------------------------
    public List<BillQTCancellationModel.GetSetMethod2> getbill_revList2(){
           if(billqt_List2!=null)
        {
            return billqt_List2;
        }
        else
            return new ArrayList<BillQTCancellationModel.GetSetMethod2>();
      }
  public static class GetSetMethod2 implements SerializableRead,IKeyed  {


        
    private String billid;  
    private String customername;
    private String userid;
    private String product;
    private Double qty;
    private Double rate;
    private String reason;
    private int authorised;
    private Timestamp crdate;
     //private String id;
   // private String cid;
   // private String rid;         
    // private String pid;
    //private Double amount;    
        public String getBillid() {
            return billid;
        }

        public void setBillid(String billid) {
            this.billid = billid;
        }
    
    
        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getAuthorised() {
            return authorised;
        }

        public void setAuthorised(int authorised) {
            this.authorised = authorised;
        }

       

        public Timestamp getCrdate() {
            return crdate;
        }

        public void setCrdate(Timestamp crdate) {
            this.crdate = crdate;
        }
          
          
          
          

        @Override
        public void readValues(DataRead dr) throws BasicException {
           billid=dr.getString(1); 
           customername=dr.getString(2);
           userid=dr.getString(3);
           product=dr.getString(4);
           qty=dr.getDouble(6);
           rate=dr.getDouble(7);
           reason=dr.getString(8);
           authorised=dr.getInt(9);
           crdate=dr.getTimestamp(10);  
        }

        @Override
        public Object getKey() { 
            return this;
        }

        

      
      
  }  
    
    
}
