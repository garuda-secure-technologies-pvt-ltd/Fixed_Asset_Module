

package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class UpdateDueAmtTableModel extends BeanFactoryDataSingle{
    
     private AppView m_App;
     private Session s;
     private int size;
     private List<UpdateDueAmtTableModel.ReceiptInfo> data;
    
     private final static String[] TABLEHEADERS = {"Particular" , "Receipt No."  , "Date" , "Amount" , "Cr. by." , "Narration" };
     
      @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
     public int getSize()
      {
        return size;
      }
     
     public List<UpdateDueAmtTableModel.ReceiptInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<UpdateDueAmtTableModel.ReceiptInfo>();
      }
     
 //////////////////////////////////////////////////////////////////////////////////
     
     
     
      public static UpdateDueAmtTableModel LoadDueAmtInfoFromAccounts(AppView app) throws BasicException{
        UpdateDueAmtTableModel GuestInfo = new UpdateDueAmtTableModel(); 

            try{
                   GuestInfo.data = new ArrayList<UpdateDueAmtTableModel.ReceiptInfo>();
                   GuestInfo.data = new StaticSentence(app.getSession(), "select TRANSREF , TRANSNO , DATE , AMOUNT , BALANCEAMOUNT , ADJUSTED , CREATEDBY  , NARRATION from accountjournal where \n" +
                                                                            "accountid='bb69774a-20a0-437e-82da-67aadb84d7b6' and date > '2014-09-25 00:00:00' and transtype='c' ", SerializerWriteString.INSTANCE, new SerializerReadClass(UpdateDueAmtTableModel.ReceiptInfo.class)).list();

                   GuestInfo.size = GuestInfo.data.size();

                   

               }
               catch(BasicException ex){
                   Logger.getLogger(UpdateDueAmtTableModel.class.getName()).log(Level.SEVERE, null, ex);
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
              UpdateDueAmtTableModel.ReceiptInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getTRANSREF();
                   case 1: return r.getTRANSNO();
                   case 2: return r.getCRDATE();
                   case 3: return r.getBalanceAmt();
                   case 4: return r.getCRBY();
                   case 5: return r.getNARRATION();
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
      
      
      
      
      
       public static class ReceiptInfo implements SerializableRead,IKeyed {

        private String TRANSREF;
        private String TRANSNO;
        private Double Amount;
        private Double BalanceAmt;
        private String MEMID;
        private int ADJUSTED;
        private String CRBY;
        private Date CRDATE;
       private String NARRATION;
         
        
        
         public String getTRANSREF(){
              return TRANSREF;
          }
          public void setTRANSREF(String TRANSREF){
              this.TRANSREF=TRANSREF;
          }
          public String getTRANSNO(){
              return TRANSNO;
          }
          public void setTRANSNO(String TRANSNO){
              this.TRANSNO = TRANSNO;
          }
          public Double getAmount(){
              return Amount;
          }
          public void setMEMNAME(Double Amount){
              this.Amount =Amount;
          }
        
           public Double getBalanceAmt(){
              return BalanceAmt;
          }
          public void setBalanceAmt(Double BalanceAmt){
              this.BalanceAmt =BalanceAmt;
          }
          
          public String getMEMID(){
              return MEMID;
          }
          public void setMEMID(String MEMID){
              this.MEMID = MEMID;
          }
          
         
          
          public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
        
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
          public int getADJUSTED(){
              return ADJUSTED;
          }
          public void setADJUSTED(int ADJUSTED){
              this.ADJUSTED=ADJUSTED;
          }
          
          public String getNARRATION(){
              return NARRATION;
          }
          public void setNARRATION(String NARRATION){
              this.NARRATION=NARRATION;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                TRANSREF = dr.getString(1);
                TRANSNO = dr.getString(2);
                CRDATE = dr.getTimestamp(3);
                Amount = dr.getDouble(4);
                BalanceAmt = dr.getDouble(5);
                ADJUSTED = dr.getInt(6);
                CRBY = dr.getString(7);
                NARRATION = dr.getString(8);
                
          }

        public Object getKey() {
           return this;
        }

       
     }   
       
      
      
      
      
      
      
      
     
    
}
