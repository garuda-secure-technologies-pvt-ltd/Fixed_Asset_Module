
package com.openbravo.pos.reports;

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
import com.openbravo.pos.sms.EmailMasterTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class QTDetailsReportTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<QTDetailsReportTableModel.QTInfo> data;
    private int size;
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    public static QTDetailsReportTableModel LoadQTInfo(AppView app , String BillNo) throws BasicException{
        QTDetailsReportTableModel GuestInfo = new QTDetailsReportTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<QTDetailsReportTableModel.QTInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select q.id as QTID ,q.crdate as QTCrDate , p.name as product, qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, b.createddate as BillCrDate\n" +
                                                                   "from qticket q, qtitems qt , products p , bill b\n" +
                                                                   "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.id=?    \n" +
                                                                   "union all \n" +
                                                                   "select q.id as QTID ,q.crdate as QTCrDate , p.name as product, qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, b.createddate as BillCrDate\n" +
                                                                   "from qticket_arv q, qtitems_arv qt , products p , bill_arv b\n" +
                                                                   "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.id=?   order by 2 ,3 ", new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING }), new SerializerReadClass(QTDetailsReportTableModel.QTInfo.class)).list(new Object[]{ BillNo , BillNo});
           
            GuestInfo.size = GuestInfo.data.size();
         }
        catch(BasicException ex){
            Logger.getLogger(EmailMasterTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
    }
    
    
    public static QTDetailsReportTableModel LoadQTInfoForBillSeries(AppView app , Date FromDate , Date ToDate,String WarehouseId) throws BasicException{
        QTDetailsReportTableModel GuestInfo = new QTDetailsReportTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<QTDetailsReportTableModel.QTInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select q.id as QTID ,q.crdate as QTCrDate , p.name as product, qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, b.createddate as BillCrDate\n" +
                                                                   "from qticket q, qtitems qt , products p , bill b\n" +
                                                                   "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate >= ? and b.createddate <= ?  and b.warehouse=?  \n" +
                                                                   "union all \n" +
                                                                   "select q.id as QTID ,q.crdate as QTCrDate , p.name as product, qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, b.createddate as BillCrDate\n" +
                                                                   "from qticket_arv q, qtitems_arv qt , products p , bill_arv b\n" +
                                                                   "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and  b.createddate >= ? and b.createddate <= ?  and b.warehouse=?   order by 2 ,3 ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING, Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING }), new SerializerReadClass(QTDetailsReportTableModel.QTInfo.class)).list(new Object[]{ FromDate , ToDate ,WarehouseId , FromDate , ToDate , WarehouseId});
           
            GuestInfo.size = GuestInfo.data.size();
         }
        catch(BasicException ex){
            Logger.getLogger(EmailMasterTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
    }
    
    
    
    
 //////////////////////////////////////////////////////////////////////////////////////////////////
    
    
  public static QTDetailsReportTableModel LoadQTInfoForQtRange(AppView app , Date FromDate , Date ToDate,String WarehouseId) throws BasicException{
        QTDetailsReportTableModel GuestInfo = new QTDetailsReportTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<QTDetailsReportTableModel.QTInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select q.id as QTID ,q.crdate as QTCrDate , p.name as product, qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, b.createddate as BillCrDate\n" +
                                                                   "from qticket q, qtitems qt , products p , bill b\n" +
                                                                   "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and q.crdate >= ? and q.crdate <= ?  and b.warehouse=?  \n" +
                                                                   "union all \n" +
                                                                   "select q.id as QTID ,q.crdate as QTCrDate , p.name as product, qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, b.createddate as BillCrDate\n" +
                                                                   "from qticket_arv q, qtitems_arv qt , products p , bill_arv b\n" +
                                                                   "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and  q.crdate >= ? and q.crdate <= ?  and b.warehouse=?   order by 2 ,3 ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING, Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING }), new SerializerReadClass(QTDetailsReportTableModel.QTInfo.class)).list(new Object[]{ FromDate , ToDate ,WarehouseId , FromDate , ToDate , WarehouseId});
           
            GuestInfo.size = GuestInfo.data.size();
         }
        catch(BasicException ex){
            Logger.getLogger(EmailMasterTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
    }   
    
    
  
 
  
  
  
    
    
   public List<QTDetailsReportTableModel.QTInfo> getQtList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<QTDetailsReportTableModel.QTInfo>();
      }  
    
    
    
    public static class QTInfo implements SerializableRead,IKeyed {

        private String ID;
        private String QTNo;
        private Date QTDate;
        private String PRODUCT;
        private int QTY;
        private Double RATE;
        private Double AMOUNT;
        private String BILLNO;
        private Date BILLDATE;
        
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getQTNo(){
              return QTNo;
          }
          public void setQTNo(String QTNo){
              this.QTNo = QTNo;
          }
          public String getPRODUCT(){
              return PRODUCT;
          }
          public void setPRODUCT(String PRODUCT){
              this.PRODUCT =PRODUCT;
          }
        
          public int getQTY(){
              return QTY;
          }
          public void setQTY(int QTY){
              this.QTY = QTY;
          }
          
         
          
          public Double getRATE(){
              return RATE;
          }
          public void setRATE(Double RATE){
              this.RATE=RATE;
          }
          
        
           public String getQTDate(){
              String x = Formats.TIMESTAMP.formatValue(QTDate);
               return x;
          }
          public void setQTDate(Date QTDate){
              this.QTDate=QTDate;
          }
          
          public Double getAMOUNT(){
              return AMOUNT;
          }
          public void setAMOUNT(Double AMOUNT){
              this.AMOUNT=AMOUNT;
          }
          
           public String getBILLNO(){
              return BILLNO;
          }
          public void setBILLNO(String BILLNO){
              this.BILLNO=BILLNO;
          }
          
           public String getBILLDATE(){
              String x = Formats.TIMESTAMP.formatValue(BILLDATE);
             //  String x = (String)(BILLDATE.toString());
               return x;
          }
          public void setBILLDATE(Date BILLDATE){
              this.BILLDATE=BILLDATE;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                //ID = dr.getString(1);
                QTNo = dr.getString(1);
                QTDate = dr.getTimestamp(2);
                PRODUCT = dr.getString(3);
                RATE = dr.getDouble(4);
                QTY = dr.getInt(5);
                AMOUNT = dr.getDouble(6);
                BILLNO = dr.getString(7);
                BILLDATE = dr.getTimestamp(8);
                
             
              
          }

        public Object getKey() {
           return this;
        }

    
    
      }
    
    
}
