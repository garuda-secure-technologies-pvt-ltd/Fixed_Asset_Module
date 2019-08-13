

package com.openbravo.pos.Accounts;

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
import java.util.Calendar;

public class VoucherEntryStatusTablemodel extends BeanFactoryDataSingle{
    
    protected Session s;
    private final static String[] TABLEHEADERS = {"Sr No.", "Date" , "Amount" ,"V. Type","V. No.","Status", "Approved .By" };
    private List<VoucherEntryStatusTablemodel.VoucherInfo> data;
    private int size;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    public static VoucherEntryStatusTablemodel LoadPendingVouchers(AppView app , String User) throws BasicException{
        VoucherEntryStatusTablemodel GuestInfo = new VoucherEntryStatusTablemodel(); 
    try{
        
            Date d = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, -1);
            Date newDate = c.getTime();
            System.out.println(newDate);
            
            GuestInfo.data = new ArrayList<VoucherEntryStatusTablemodel.VoucherInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') ,  IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournal  a , voucherentry v   \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'  and a.createdby=? and approved!=1  and v.userconf=0  \n" +
                                                                    "group by a.tid    \n" +
                                                                    "union all    \n" +
                                                                    "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') , IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournaldup  a , voucherentry v  \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'   and a.createdby=? and approved!=1  and v.userconf=0 \n" +
                                                                    "group by a.tid   \n" +
                                                                    "union all\n" +
                                                                    "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') ,  IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournal  a , voucherentry v   \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'  and a.createdby=? and approved=1 and a.dateofentry>?  and v.userconf=0 \n" +
                                                                    "group by a.tid    \n" +
                                                                    "union all    \n" +
                                                                    "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') , IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournaldup  a , voucherentry v  \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'   and a.createdby=? and approved=1 and a.dateofentry>? and v.userconf=0  \n" +
                                                                    "group by a.tid \n" +
                                                                    "order by date , dateofentry ", new SerializerWriteBasic(new Datas[]{ Datas.STRING ,  Datas.STRING ,  Datas.STRING  , Datas.TIMESTAMP ,  Datas.STRING , Datas.TIMESTAMP  }) , new SerializerReadClass(VoucherEntryStatusTablemodel.VoucherInfo.class)).list(new Object[]{ User,User ,User,newDate ,User , newDate});
           
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(VoucherEntryStatusTablemodel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
   // ******************************* RECEIPT VOUCHERS ********************************************************************
    public static VoucherEntryStatusTablemodel LoadPendingReceiptVouchers(AppView app , String User , String Type) throws BasicException{
        VoucherEntryStatusTablemodel GuestInfo = new VoucherEntryStatusTablemodel(); 
    try{
            
            Date d = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, -1);
            Date newDate = c.getTime();
            System.out.println(newDate);
            
            GuestInfo.data = new ArrayList<VoucherEntryStatusTablemodel.VoucherInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') ,  IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournal  a , voucherentry v   \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'  and a.createdby=? and approved!=1  and v.userconf=0 and a.transref=? \n" +
                                                                    "group by a.tid    \n" +
                                                                    "union all    \n" +
                                                                    "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') , IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournaldup  a , voucherentry v  \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'   and a.createdby=? and approved!=1  and v.userconf=0 and a.transref=? \n" +
                                                                    "group by a.tid   \n" +
                                                                    "union all\n" +
                                                                    "select a.tid  , a.date , a.transref , a.transno , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') ,  IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournal  a , voucherentry v   \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'  and a.createdby=? and approved=1 and a.dateofentry>?  and v.userconf=0 and a.transref=? \n" +
                                                                    "group by a.tid    \n" +
                                                                    "union all    \n" +
                                                                    "select a.tid  , a.date , a.transref , 'Pending' , SUM(a.amount) , a.createdby , a.dateofentry , a.transtype  , v.approved, IFNULL(v.approvedby,'') , IFNULL(v.rejectby,'') , a.narration \n" +
                                                                    "from accountjournaldup  a , voucherentry v  \n" +
                                                                    "where  v.tid=a.tid and a.transtype='C'   and a.createdby=? and approved=1 and a.dateofentry>? and v.userconf=0  and a.transref=? \n" +
                                                                    "group by a.tid \n" +
                                                                    "order by date , dateofentry ", new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING,  Datas.STRING , Datas.STRING, Datas.STRING  , Datas.TIMESTAMP , Datas.STRING, Datas.STRING , Datas.TIMESTAMP ,Datas.STRING }) , new SerializerReadClass(VoucherEntryStatusTablemodel.VoucherInfo.class)).list(new Object[]{ User,Type,User ,Type ,User,newDate,Type ,User , newDate,Type  });
           
            GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(VoucherEntryStatusTablemodel.class.getName()).log(Level.SEVERE, null, ex);
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
              VoucherEntryStatusTablemodel.VoucherInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getDATE();
                   case 2: return decimalFormat.format(r.getAmount()); 
                   case 3: return r.getTRANSREF(); 
                   case 4: return r.getTransNo();     
                   case 5: if(r.getApproved()==0){
                                return "Pending";
                            }
                            else if(r.getApproved()==2){
                                return "Rejected";
                            }
                            else if(r.getApproved()==1){
                                return "Approved";
                            }
                   
                   case 6: if(r.getApproved()==0){
                                return "";
                            }
                            else if(r.getApproved()==2){
                                return r.getRejectBy();
                            }
                            else if(r.getApproved()==1){
                                return r.getApprovedBy();
                            }
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
        private int Approved;
        private String ApprovedBy;
        private String RejectBy;
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
          
          public int getApproved(){
              return Approved;
          }
          public void setApproved(int Approved){
              this.Approved=Approved;
          }
          public String getApprovedBy(){
              return ApprovedBy;
          }
          public void setApprovedBy(String ApprovedBy){
              this.ApprovedBy=ApprovedBy;
          }
           public String getRejectBy(){
              return RejectBy;
          }
          public void setRejectBy(String RejectBy){
              this.RejectBy=RejectBy;
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
                Approved=dr.getInt(9);
                ApprovedBy=dr.getString(10);
                RejectBy=dr.getString(11);
                NARRATION=dr.getString(12);
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
     
    
    
    
    
}
