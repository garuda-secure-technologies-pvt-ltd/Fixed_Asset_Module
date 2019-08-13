
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RefundVoucherModel extends BeanFactoryDataSingle{
    private Session s;
    private List<RefundVoucherModel.Voucher_Info> data;
    private int VoucherList_size;
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public RefundVoucherModel() {
    }
    public RefundVoucherModel(List<RefundVoucherModel.Voucher_Info> data) {
        this.data = data;
    }
    
    
    
    public static RefundVoucherModel LoadVoucher(AppView app) throws BasicException{
        RefundVoucherModel voucherInfo = new RefundVoucherModel(); 
    
     try{
            voucherInfo.data = new ArrayList<RefundVoucherModel.Voucher_Info>();
            voucherInfo.data = new StaticSentence(app.getSession(), " SELECT R.ID , RV_NO , CUST_ID , MEM_NO , BOOKING_SEQ_NO , BILLED_AMT , ADVNCE_AMT ,\n" +
                                                                    "REFUND_AMT , CHK_IN_ID , REFUND_BY , REFUND_DATE , C.NAME\n" +
                                                                    "FROM room_hall_refund_voucher R , CUSTOMERS C\n" +
                                                                    "WHERE CUST_ID=C.ID ", SerializerWriteString.INSTANCE, new SerializerReadClass(RefundVoucherModel.Voucher_Info.class)).list();

            voucherInfo.VoucherList_size = voucherInfo.data.size();
            
        
       }
        catch(BasicException ex){
          Logger.getLogger(HallCheckInTable.class.getName()).log(Level.SEVERE, null, ex);
        }
       return voucherInfo;
     }
    
    
    
     public int getSize()
      {
        return VoucherList_size;
      }
    
      public List<RefundVoucherModel.Voucher_Info> getVoucherList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<RefundVoucherModel.Voucher_Info>();
      }
    
    
      
      
      
      
      
         
      public static class Voucher_Info implements SerializableRead,IKeyed {
         
            String ID;
            String RV_NO;
            String CUST_ID;
            String MEM_NO;
            String BOOKING_SEQ_NO;
            Double BILLED_AMT;
            Double ADVNCE_AMT;
            Double REFUND_AMT;
            String CHK_IN_ID;
            String REFUND_BY;
            Date REFUND_DATE;
            String Customer_name;
            
            
            
         public String getVoucherID(){
              return ID;
          }
          public void setVoucherID(String ID){
              this.ID=ID;
          }
          public String getRV_NO(){
              return RV_NO;
          }
          public void setRV_NO(String RV_NO){
              this.RV_NO=RV_NO;
          }
          public String getCUST_ID(){
              return CUST_ID;
          }
          public void setCUST_ID(String CUST_ID){
              this.CUST_ID=CUST_ID;
          }
          public String getMEMBERNO(){
              return MEM_NO;
          }
          public void setMEMBERNO(String MEM_NO){
              this.MEM_NO=MEM_NO;
          }
          public String getBOOKING_SEQ_NO(){
              return BOOKING_SEQ_NO;
          }
          public void setBOOKING_SEQ_NO(String BOOKING_SEQ_NO){
              this.BOOKING_SEQ_NO=BOOKING_SEQ_NO;
          }
          public Double getBILLED_AMT(){
              return BILLED_AMT;
          }
          public void setBILLED_AMT(Double BILLED_AMT){
              this.BILLED_AMT=BILLED_AMT;
          }
         
           public Double getADVNCE_AMT(){
              return ADVNCE_AMT;
          }
          public void setADVNCE_AMT(Double ADVNCE_AMT){
              this.ADVNCE_AMT=ADVNCE_AMT;
          }
           public Double getREFUND_AMT(){
              return REFUND_AMT;
          }
          public void setREFUND_AMT(Double REFUND_AMT){
              this.REFUND_AMT=REFUND_AMT;
          }
          
           public String getCHK_IN_ID(){
              return CHK_IN_ID;
          }
          public void setCHK_IN_ID(String CHK_IN_ID){
              this.CHK_IN_ID=CHK_IN_ID;
          }
           public String getREFUND_BY(){
              return REFUND_BY;
          }
          public void setREFUND_BY(String REFUND_BY){
              this.REFUND_BY=REFUND_BY;
          }
         
           public Date getREFUND_DATE(){
              return REFUND_DATE;
          }
          public void setREFUND_DATE(Date REFUND_DATE){
              this.REFUND_DATE=REFUND_DATE;
          }
          public String getCustomer_name(){
              return Customer_name;
          }
          public void setCustomer_name(String Customer_name){
              this.Customer_name = Customer_name;
          }
         
         
         public void readValues(DataRead dr) throws BasicException {
          
             ID = dr.getString(1);
             RV_NO = dr.getString(2);
             CUST_ID = dr.getString(3);
             MEM_NO = dr.getString(4);
             BOOKING_SEQ_NO = dr.getString(5);
             BILLED_AMT = dr.getDouble(6);
             ADVNCE_AMT = dr.getDouble(7);
             REFUND_AMT = dr.getDouble(8);
             CHK_IN_ID = dr.getString(9);
             REFUND_BY = dr.getString(10);
             REFUND_DATE = dr.getTimestamp(11);
             Customer_name = dr.getString(12);
            
             
             
         }
            public Object getKey() {
             return this;
        }
     }
      
}
