 
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class BankChargesCollectedReportsTableMOdel extends BeanFactoryDataSingle {
    
    private Session s;
    private final static String[] TABLEHEADERS = {  "Receipt No" , "Bank Name" , "Amount" , "OtherCharges" , "Account Name" , "Cr. Date"};
    private int size;
    private List<BankChargesCollectedReportsTableMOdel.BankCardInfo> data;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
     
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    
    public static BankChargesCollectedReportsTableMOdel loadBankCollectionDetails(AppView app , Date FmDate , Date ToDate , int OrderFlag ) throws BasicException{
        BankChargesCollectedReportsTableMOdel GuestInfo = new BankChargesCollectedReportsTableMOdel(); 
    
         
        
        try{
            GuestInfo.data = new ArrayList<BankChargesCollectedReportsTableMOdel.BankCardInfo>();
            
            if(OrderFlag==1){
                GuestInfo.data = new StaticSentence(app.getSession(), "SELECT c.id , b.name , c.receipt , c.amount , c.othercharges ,   c.transactionid , a.name , c.paymentflag , c.crdate\n" +
                                                                        " FROM carddetails c , accountmaster a, bank_details b\n" +
                                                                        "where b.id=c.bankid and c.accountid=a.id   and c.crdate >= ? and c.crdate <?       \n" +
                                                                        "order by c.crdate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP    }) , new SerializerReadClass(BankChargesCollectedReportsTableMOdel.BankCardInfo.class)).list(new Object[]{ FmDate ,  ToDate   });

            }
            if(OrderFlag==2){
                
                GuestInfo.data = new StaticSentence(app.getSession(), "SELECT c.id , b.name , c.receipt , c.amount , c.othercharges ,   c.transactionid , a.name , c.paymentflag , c.crdate\n" +
                                                                        " FROM carddetails c , accountmaster a, bank_details b\n" +
                                                                        "where b.id=c.bankid and c.accountid=a.id   and c.crdate >= ? and c.crdate <?       \n" +
                                                                        "order by c.receipt ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP    }) , new SerializerReadClass(BankChargesCollectedReportsTableMOdel.BankCardInfo.class)).list(new Object[]{ FmDate ,  ToDate   });

                
                
            }
            if(OrderFlag==3){
                
                GuestInfo.data = new StaticSentence(app.getSession(), "SELECT c.id , b.name , c.receipt , c.amount , c.othercharges ,   c.transactionid , a.name , c.paymentflag , c.crdate\n" +
                                                                        " FROM carddetails c , accountmaster a, bank_details b\n" +
                                                                        "where b.id=c.bankid and c.accountid=a.id   and c.crdate >= ? and c.crdate <?       \n" +
                                                                        "order by b.name , c.receipt ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP    }) , new SerializerReadClass(BankChargesCollectedReportsTableMOdel.BankCardInfo.class)).list(new Object[]{ FmDate ,  ToDate   });

                
                
            }
            if(OrderFlag==4){
                
                GuestInfo.data = new StaticSentence(app.getSession(), "SELECT c.id , b.name , c.receipt , c.amount , c.othercharges ,   c.transactionid , a.name , c.paymentflag , c.crdate\n" +
                                                                        " FROM carddetails c , accountmaster a, bank_details b\n" +
                                                                        "where b.id=c.bankid and c.accountid=a.id   and c.crdate >= ? and c.crdate <?       \n" +
                                                                        "order by a.name , c.crdate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP    }) , new SerializerReadClass(BankChargesCollectedReportsTableMOdel.BankCardInfo.class)).list(new Object[]{ FmDate ,  ToDate   });

                
                
            }
            
            
            
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            ex.printStackTrace();
            Logger.getLogger(BankChargesCollectedReportsTableMOdel.class.getName()).log(Level.SEVERE, null, ex);
        }
      return GuestInfo;
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
              BankChargesCollectedReportsTableMOdel.BankCardInfo r =data.get(rowIndex);
                  switch(columnIndex){
                   
                   case 0: return r.getReceiptNo();
                   case 1: return r.getBankName();
                   case 2: return decimalFormat.format(r.getAmount());
                   case 3: return decimalFormat.format(r.getOtherCharges());
                   case 4:return r.getAccountName();
                   case 5: return r.getCRDATE();
                   
                 }
                return null;
            }
          
          
          };
        } 
    
    
    public static class BankCardInfo implements SerializableRead,IKeyed {

        
        private String ID;
        private String BankName;
        private String ReceiptNo;
        private Double Amount;
        private Double OtherCharges;
        private String TransactionID;
        private String AccountName;
        private Date CRDATE;
        private int PAYMENTFLAG;
       
         
         public String getBankName(){
             return BankName;
         }
         public void setBankName(String BankName){
             this.BankName=BankName;
         }
         
         public String getID(){
             return ID;
         }
         public void setID(String ID){
             this.ID=ID;
         }
         public String getReceiptNo(){
             return ReceiptNo;
         }
         public void setReceiptNo(String ReceiptNo){
             this.ReceiptNo=ReceiptNo;
         }
         public Double getAmount(){
             return Amount;
         }
         public void setAmount(Double Amount){
             this.Amount=Amount;
         }
         
          public Double getOtherCharges(){
             return OtherCharges;
         }
         public void setOtherCharges(Double OtherCharges){
             this.OtherCharges=OtherCharges;
         }
         
         public String getTransactionID(){
             return TransactionID;
         }
         public void setTransactionID(String TransactionID){
             this.TransactionID = TransactionID;
         }
         
         
         public void setAccountName(String AccountName){
             this.AccountName=AccountName;
         }
         public String getAccountName(){
             return AccountName;
         }
         
        
         public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
              return x;
         }
         public void setCRDATE(Date CRDATE){
             this.CRDATE=CRDATE;
         }
          
         
         public void setPAYMENTFLAG(int PAYMENTFLAG){
             this.PAYMENTFLAG=PAYMENTFLAG;
         }
         public int  getPAYMENTFLAG(){
             return PAYMENTFLAG;
         }
         
         
           
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                BankName = dr.getString(2);
                ReceiptNo = dr.getString(3);
                Amount = dr.getDouble(4);
                OtherCharges = dr.getDouble(5);
                TransactionID = dr.getString(6);
                AccountName = dr.getString(7);
                PAYMENTFLAG = dr.getInt(8);
                CRDATE = dr.getTimestamp(9);
             
              
          }

        public Object getKey() {
           return this;
        }

       
     } 
    
    
}
