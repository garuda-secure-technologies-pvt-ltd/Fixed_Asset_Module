

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
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
import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class VoucherEntryConfirmationTableModel extends BeanFactoryDataSingle{
    
    protected Session s;
     private final static String[] TABLEHEADERS = {"Sr No.", "Date" , "Amount" ,"V. Type","V. No.", "Cr.By" , "Cr. Date" };
    // private final static String[] TABLEHEADERS2 = {"Account", "Credit" , "Debit" };
      private final static String[] TABLEHEADERS2 = {"Account", "Debit" , "Credit" };
     private int size;
     private List<VoucherEntryConfirmationTableModel.VoucherInfo> data;
     private static List<VoucherEntryConfirmationTableModel.VoucherInfo> datag;
     private int size2;
     private List<VoucherEntryConfirmationTableModel.AccountInfo> data2;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
     
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    public static VoucherEntryConfirmationTableModel LoadPendingVouchers(AppView app ) throws BasicException{
     List<String> tidList=new ArrayList<String>();
        VoucherEntryConfirmationTableModel GuestInfo = new VoucherEntryConfirmationTableModel(); 
    try{
            GuestInfo.data = new ArrayList<VoucherEntryConfirmationTableModel.VoucherInfo>();
//            GuestInfo.data = new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype , a.narration  \n" +
//                                                                    "from accountjournal  a , voucherentry v   \n" +
//                                                                    "where a.active=1 and v.tid=a.tid and a.transtype='C'  and v.approved=0 \n" +
//                                                                    "group by a.tid    \n" +
//                                                                    "union all    \n" +
//                                                                    "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount)  , a.createdby , a.dateofentry , a.transtype  , a.narration  \n" +
//                                                                    "from accountjournaldup  a , voucherentry v  \n" +
//                                                                    "where a.active=1 and v.tid=a.tid and a.transtype='C'   and v.approved=0 \n" +
//                                                                    "group by a.tid   \n" +
//                                                                    "order by date , transref , transno ", new SerializerWriteBasic(new Datas[]{ Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.VoucherInfo.class)).list(new Object[]{ "Temp" });
//           
/////////////////////////////////////////////////////pratima
    tidList=new StaticSentence(app.getSession(), "select tid from voucherentry where approved=0 order by tid", new SerializerWriteBasic(new Datas[]{ Datas.STRING }) ,new SerializerReadBasic(new Datas[]{ Datas.STRING })).list(new Object[]{ "Temp" });
 
    for(int i=0;i<tidList.size();i++){
    
    VoucherEntryConfirmationTableModel.VoucherInfo vInfo= (VoucherInfo)(new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype , a.narration  \n" +
                                                                    "from accountjournal  a   \n" +
                                                                    "where a.active=1  and a.transtype='C' and a.tid=?  \n" +
                                                                    "group by a.tid    \n" +
                                                                   "order by date, transref , transno ", new SerializerWriteBasic(new Datas[]{ Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.VoucherInfo.class)).find(tidList.get(i)));
   if (vInfo!=null)
       GuestInfo.data.add(vInfo);
   VoucherEntryConfirmationTableModel.VoucherInfo vInfo1= (VoucherInfo)(new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount)  , a.createdby , a.dateofentry , a.transtype  , a.narration  \n" +
                                                                    "from accountjournaldup  a \n" +
                                                                    "where a.active=1 and a.tid=? and a.transtype='C'    \n" +
                                                                    "group by a.tid   \n" +
                                                                    "order by date, transref , transno ", new SerializerWriteBasic(new Datas[]{ Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.VoucherInfo.class)).find( tidList.get(i)));
      if (vInfo1!=null)
          GuestInfo.data.add(vInfo1);
    }
////////////////////////////////////////////////////////////
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(VoucherEntryConfirmationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    //Added by guru for sorting voucher Limit Release
    Collections.sort(GuestInfo.data, new Comparator<VoucherEntryConfirmationTableModel.VoucherInfo>() {
        public int compare(VoucherEntryConfirmationTableModel.VoucherInfo vi2, VoucherEntryConfirmationTableModel.VoucherInfo vi1)
        {
            return  vi2.DATE.compareTo(vi1.DATE);
        }
    });
     return GuestInfo;
  
     }
// LOAD ONLY RECEIPTS 
    
    public static VoucherEntryConfirmationTableModel LoadPendingVouchersOnlyType(AppView app , String Type) throws BasicException{
         List<String> tidList=new ArrayList<String>();
        VoucherEntryConfirmationTableModel GuestInfo = new VoucherEntryConfirmationTableModel(); 
    try{
            GuestInfo.data = new ArrayList<VoucherEntryConfirmationTableModel.VoucherInfo>();
//            GuestInfo.data = new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount)  , a.createdby , a.dateofentry , a.transtype , a.narration  \n" +
//                                                                    "from accountjournal  a , voucherentry v   \n" +
//                                                                    "where a.active=1 and v.tid=a.tid and a.transtype='C' and  a.transref=?   and v.approved=0 \n" +
//                                                                    "group by a.tid    \n" +
//                                                                    "union all    \n" +
//                                                                    "select a.tid  , a.date , a.transref ,'Pending'  ,SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , a.narration  \n" +
//                                                                    "from accountjournaldup  a , voucherentry v  \n" +
//                                                                    "where a.active=1 and v.tid=a.tid and a.transtype='C'  and  a.transref=?   and v.approved=0 \n" +
//                                                                    "group by a.tid   \n" +
//                                                                    "order by date , transref , transno ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.VoucherInfo.class)).list(new Object[]{ Type , Type});
//           
////////////////////////////////////////////////////pratima
 tidList=new StaticSentence(app.getSession(), "select tid from voucherentry where approved=0 order by tid", new SerializerWriteBasic(new Datas[]{ Datas.STRING }) ,new SerializerReadBasic(new Datas[]{ Datas.STRING })).list(new String[]{ "Temp" });
 
  for(int i=0;i< tidList.size();i++){       
  VoucherEntryConfirmationTableModel.VoucherInfo vInfo= (VoucherInfo)(new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype , a.narration  \n" +
                                                                    "from accountjournal  a   \n" +
                                                                    "where a.active=1  and a.transtype='C' and a.tid=?  \n" +
                                                                    "group by a.tid    \n" +
                                                                   "order by date , transref , transno ", new SerializerWriteBasic(new Datas[]{ Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.VoucherInfo.class)).find(tidList.get(i)));
   if ((vInfo!=null)&&(vInfo.getTRANSREF().equals(Type)))
       GuestInfo.data.add(vInfo);
   VoucherEntryConfirmationTableModel.VoucherInfo vInfo1= (VoucherInfo)(new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount)  , a.createdby , a.dateofentry , a.transtype  , a.narration  \n" +
                                                                    "from accountjournaldup  a \n" +
                                                                    "where a.active=1 and a.tid=? and a.transtype='C'    \n" +
                                                                    "group by a.tid   \n" +
                                                                    "order by date , transref , transno ", new SerializerWriteBasic(new Datas[]{ Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.VoucherInfo.class)).find( tidList.get(i)));
     if ((vInfo1!=null)&&(vInfo1.getTRANSREF().equals(Type)))
          GuestInfo.data.add(vInfo1);
    }
///////////////////////////////////////////////////////////
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(VoucherEntryConfirmationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    //Added by guru for sorting voucher Limit Release
    Collections.sort(GuestInfo.data, new Comparator<VoucherEntryConfirmationTableModel.VoucherInfo>() {
        public int compare(VoucherEntryConfirmationTableModel.VoucherInfo vi2, VoucherEntryConfirmationTableModel.VoucherInfo vi1)
        {
            return  vi2.DATE.compareTo(vi1.DATE);
        }
    });
     return GuestInfo;
  
     }
    

 
 
// LOAD ACCOUNT EDITED DETAILS
    
    public static VoucherEntryConfirmationTableModel LoadAccountDetailsByTid(AppView app , String TID) throws BasicException{
        VoucherEntryConfirmationTableModel GuestInfo2 = new VoucherEntryConfirmationTableModel(); 
    try{
            GuestInfo2.data2 = new ArrayList<VoucherEntryConfirmationTableModel.AccountInfo>();
            GuestInfo2.data2 = new StaticSentence(app.getSession(), "select am.name , cast(a.amount as decimal(64,2)) as credit , '' as debit , a.TRANSTYPE , a.narration \n" +
                                                                        "from accountjournal a , accountmaster am\n" +
                                                                        "where tid=? and am.id=a.accountid and transtype='C'\n" +
                                                                        "union all\n" +
                                                                        "select am.name , '' as credit , cast(a.amount as decimal(64,2)) as debit , a.TRANSTYPE , a.narration \n" +
                                                                        "from accountjournal a , accountmaster am\n" +
                                                                        "where tid=? and am.id=a.accountid and transtype='D'\n" +
                                                                        "union all\n" +
                                                                        "select am.name , cast(a.amount as decimal(64,2)) as credit , '' as debit , a.TRANSTYPE , a.narration \n" +
                                                                        "from accountjournaldup a , accountmaster am\n" +
                                                                        "where tid=? and am.id=a.accountid and transtype='C'\n" +
                                                                        "union all\n" +
                                                                        "select am.name , '' as credit , cast(a.amount as decimal(64,2)) as debit , a.TRANSTYPE  , a.narration  \n" +
                                                                        "from accountjournaldup a , accountmaster am\n" +
                                                                        "where tid=? and am.id=a.accountid and transtype='D'", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING , Datas.STRING    }) , new SerializerReadClass(VoucherEntryConfirmationTableModel.AccountInfo.class)).list(new Object[]{ TID , TID , TID , TID});
           
            GuestInfo2.size2 = GuestInfo2.data2.size();
        }
        catch(BasicException ex){
            Logger.getLogger(VoucherEntryConfirmationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo2;
  
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
              VoucherEntryConfirmationTableModel.VoucherInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getDATE();
                   case 2: return decimalFormat.format(r.getAmount()); 
                   case 3: return r.getTRANSREF(); 
                   case 4: return r.getTransNo();     
                   case 5: return r.getCRBY();    
                   case 6: return r.getCRDATE();  
                   case 7: return r.getTID();  
                   case 8: return r.getNARRATION();     
                 }
                return null;
            }
          
          
          };
        } 
     
    
    
    
    
    
    
    
    
     public static class VoucherInfo implements SerializableRead,IKeyed {

        private String TID;
        private Date DATE;
        private String TRANSREF;
        private String TransNo;
        private Double Amount;
        private String CRBY;
        private Date CRDATE;
        private String TRANSTYPE;
        private String NARRATION;
         
         public String getTID(){
             return TID;
         }
         public void setTID(String TID){
             this.TID=TID;
         }
         
         public String getTRANSREF(){
             return TRANSREF;
         }
         public void setTRANSREF(String TRANSREF){
             this.TRANSREF=TRANSREF;
         }
         public String getTransNo(){
             return TransNo;
         }
         public void setTransNo(String TransNo){
             this.TransNo=TransNo;
         }
         public Double getAmount(){
             return Amount;
         }
         public void setAmount(Double Amount){
             this.Amount=Amount;
         }
         public String getCRBY(){
             return CRBY;
         }
         public void setCRBY(String CRBY){
             this.CRBY=CRBY;
         }
         
          public String getTRANSTYPE(){
             return TRANSTYPE;
         }
         public void setTRANSTYPE(String TRANSTYPE){
             this.TRANSTYPE=TRANSTYPE;
         }
         public String getDATE(){
              String x = Formats.TIMESTAMP.formatValue(DATE);
               return x;
          }
          public void setDATE(Date DATE){
              this.DATE=DATE;
          }
          public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          public String getNARRATION(){
             
               return NARRATION;
          }
          public void setNARRATION(String NARRATION){
              this.NARRATION=NARRATION;
          }
         
          public void readValues(DataRead dr) throws BasicException {
           
             
                TID = dr.getString(1);
                DATE = dr.getTimestamp(2);
                TRANSREF = dr.getString(3);
                TransNo = dr.getString(4);
                Amount = dr.getDouble(5);
                CRBY = dr.getString(6);
                CRDATE = dr.getTimestamp(7);
                TRANSTYPE = dr.getString(8);
                NARRATION=dr.getString(9);
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
     
     
     
     
 // FOR ACCOUNT DETAILS
     
     
  public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data2.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS2.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS2[column];
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
              VoucherEntryConfirmationTableModel.AccountInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   
                   case 0: return r.getAccount();
//                   case 1: return (r.getCredit());
//                   case 2: return (r.getDebit()); 
                   
                   case 1: return (r.getDebit()); 
                   case 2: return (r.getCredit());
                 }
                return null;
            }
          
          
          };
        }     
     
     
     
     
     
     
     
     public static class AccountInfo implements SerializableRead,IKeyed {

        private String Account;
        private String Credit;
        private String Debit;
        private String Narration;
        
        
         public String getAccount(){
             return Account;
         }
         public void setAccount(String Account){
             this.Account=Account;
         }
         
         public String getCredit(){
             return Credit;
         }
         public void setCredit(String Credit){
             this.Credit=Credit;
         }
         public String getDebit(){
             return Debit;
         }
         public void setDebit(String Debit){
             this.Debit=Debit;
         }
        
         
          public void readValues(DataRead dr) throws BasicException {
           
             
                Account = dr.getString(1);
                Credit = dr.getString(2);
                Debit = dr.getString(3);
           }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
    
    
}
