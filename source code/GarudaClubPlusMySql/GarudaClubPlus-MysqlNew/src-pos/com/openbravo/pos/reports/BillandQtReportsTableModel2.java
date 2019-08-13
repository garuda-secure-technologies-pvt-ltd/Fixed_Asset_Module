
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


public class BillandQtReportsTableModel2 extends BeanFactoryDataSingle{
    
    private Session s;
    private List<BillandQtReportsTableModel2.BillDetails> BOT_List;  
    private int Bill_Room_Length;
    private final static String[] RoomHeader = {"Bill no." , "CrDate" , "Amount" , "TaxAmt" , "Total Amount" , "Receipt Ref." ,"Warehouse", "Created by" };
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
     
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    // LOAD FOR ALL BILLS ..... 
     public static BillandQtReportsTableModel2 LoadBillQTs(AppView app , Date FrmDate , Date ToDate, String Orderby)throws BasicException{
         BillandQtReportsTableModel2 Billed_Rooms = new BillandQtReportsTableModel2(); 
         
         
          String orderedString = Orderby;  
         
          try{
            Billed_Rooms.BOT_List = new ArrayList<BillandQtReportsTableModel2.BillDetails>();
            Billed_Rooms.BOT_List = new StaticSentence(app.getSession(), "select b.id as billid  , w.name as waitername , b.createdby , b.paid , b.receipt , b.amount , b.taxtotal , b.createddate ,\n" +
                                                                        "l.name as warehousename \n" +
                                                                        "from bill b   , locations l , waiter w \n" +
                                                                        "where  l.id=b.warehouse and w.id=b.waiter and b.createddate>= ? and b.createddate<?\n" +
                                                                        "union\n" +
                                                                        "select b.id as billid  , w.name as waitername , b.createdby , b.paid , b.receipt , b.amount , b.taxtotal , b.createddate ,\n" +
                                                                        "l.name as warehousename \n" +
                                                                        "from bill b   , locations l , waiter w \n" +
                                                                        "where  l.id=b.warehouse and w.id=b.waiter  and b.createddate>= ? and b.createddate<?\n" +
                                                                        "order by ? ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING }) ,new SerializerReadClass(BillandQtReportsTableModel2.BillDetails.class)).list(new Object[]{ FrmDate ,  ToDate ,FrmDate ,  ToDate ,orderedString });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.BOT_List.size();
        }
        catch(BasicException ex){
            Logger.getLogger(BillandQtReportsTableModel2.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
     }
    
    
    
     public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return BOT_List.size();
            }
          public int getColumnCount() {
                return RoomHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return RoomHeader[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false , false ,  false, false , false ,  false, false , false ,  false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              BillandQtReportsTableModel2.BillDetails r =BOT_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBillId();
                   case 1: return r.getCRDATE();     
                   case 2: return decimalFormat.format(r.getAmount());
                   case 3: return decimalFormat.format(r.getTaxTotal());
                   case 4: return decimalFormat.format((r.getAmount()+r.getTaxTotal()));
                   
                   case 5: if(r.getPaid()==0){
                                return "Pending";
                            }
                   else{
                       if(r.getReceipt()!=null && r.getReceipt()!=""){
                            return  r.getReceipt();
                        }
                        else{
                            return  "Credit Confirmation";
                           }
                       
                   }
                   
                   case 6: return r.getWarehouseName();
                   case 7: return r.getCreatedBy();
                   
                   
                 
                 }
                return null;
            }
          
          
          };
        } 
     
      
      
     public List<BillandQtReportsTableModel2.BillDetails> getBOTList(){
           if(BOT_List!=null)
        {
            return BOT_List;
        }
        else
            return new ArrayList<BillandQtReportsTableModel2.BillDetails>();
      }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     public static class BillDetails implements SerializableRead,IKeyed {
          
          private String BillId;
          private String WaiterName;
          private String CreatedBy;
          private int Paid;
          private String Receipt;
          private Double Amount;
          private Double TaxTotal;
          private Date CRDATE;
          private String WarehouseName;
         
          
          
          public String getBillId(){
              return BillId;
          }
          public void setBillId(String BillId){
              this.BillId=BillId;
          }
          
           public String getWaiterName(){
              return WaiterName;
          }
          public void setWaiterName(String WaiterName){
              this.WaiterName=WaiterName;
          }
          
          public String getCreatedBy(){
              return CreatedBy;
          }
          public void setCreatedBy(String CreatedBy){
              this.CreatedBy=CreatedBy;
          }
           public int getPaid(){
              return Paid;
          }
          public void setPaid(int Paid){
              this.Paid=Paid;
          }
           public String getCRDATE(){
               String x = Formats.TIMESTAMP.formatValue(CRDATE) ;
               return x;
          }
          public void setCRDATE(Date CRDATE){
              
              this.CRDATE=CRDATE;
          }
          
           public String getReceipt(){
              return Receipt;
          }
          public void setReceipt(String Receipt){
              this.Receipt=Receipt;
          }
           public Double getAmount(){
              return Amount;
          }
          public void setAmount(Double Amount){
              this.Amount=Amount;
          }
           public Double getTaxTotal(){
              return TaxTotal;
          }
          public void setTaxTotal(Double TaxTotal){
              this.TaxTotal=TaxTotal;
          }
          
          public void setWarehouseName(String WarehouseName){
              this.WarehouseName=WarehouseName;
          }
          public String getWarehouseName(){
              return WarehouseName;
          }
          
        public void readValues(DataRead dr) throws BasicException {
           
            BillId = dr.getString(1);
            WaiterName = dr.getString(2);
            CreatedBy = dr.getString(3);
            Paid = dr.getInt(4);
            Receipt = dr.getString(5);
            Amount = dr.getDouble(6);
           
            TaxTotal = dr.getDouble(7);
            CRDATE = dr.getTimestamp(8);
            WarehouseName = dr.getString(9);
        }

        public Object getKey() {
           return this;
        }
          
    }
     
     
     
     
     
     
     
     
}
