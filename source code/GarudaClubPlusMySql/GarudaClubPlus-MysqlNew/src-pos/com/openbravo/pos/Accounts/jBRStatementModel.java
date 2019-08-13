/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class jBRStatementModel {

    private List<jBRStatements> brecons;
     private List<jBRStatement> breconss;
    private DataLogicFacilities dlfac;
     private Date date;
     private double debttotal;
    private final static String[] BankHEADERS = {"LedgerDate","TID" ,"NARRATION" ,"","Deposit","BankDate"};
    private final static String[] BankHEADERSS = {"LedgerDate","TID" , "NARRATION" ,"","Withdrawal","BankDate"};
    
         public static jBRStatementModel emptyinstance() {

      jBRStatementModel bk = new jBRStatementModel();
         jBRStatementModel bk1 = new jBRStatementModel();
         bk.brecons = new ArrayList<jBRStatements>();
         bk1.breconss = new ArrayList<jBRStatement>();
        
          return  bk;
    }
         public static jBRStatementModel loadInstance(AppView app, String accid, Date fdate) throws BasicException {
      jBRStatementModel p1 = new jBRStatementModel();
      
          List<jBRStatements> breconlist = (List<jBRStatements>) new StaticSentence(app.getSession(),
                                 
//                  " SELECT  a.date as LedgerDate,NARRATION,TRANSTYPE, CASE  WHEN a.date<=? and b.bankdate>? or b.bankdate is null  THEN a.amount WHEN a.date<=? and b.bankdate<=? THEN a.amount-b.amount  ELSE  ''  END  as Ledgeramount, b.bankdate"
//                   +" from bankrecord b, accountjournal a join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'D' and (b.accid = a.id ) and a.date<=? and (b.bankdate<=? or b.bankdate>? or b.bankdate is null) order by a.date",
                    
                  //"SELECT  a.date as LedgerDate,NARRATION,TRANSTYPE,TID, CASE  WHEN a.date<=? and b.bankdate>? or b.bankdate is null  THEN a.amount WHEN a.date<=? and b.bankdate<=? THEN a.amount-b.amount  ELSE  ''  END  as Ledgeramount, b.bankdate from accountjournal a left join bankrecord b on a.id=b.accid join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'D' and a.date<=? and a.active=true   and (b.bankdate<=? or b.bankdate>? or b.bankdate is null )  order by a.date"
              
                   "SELECT a.date as LedgerDate,NARRATION,TRANSTYPE,TID, CASE WHEN a.date<=? and b.bankdate>? or b.bankdate is null THEN a.amount WHEN a.date<=? and b.bankdate<=?and (a.amount-b.amount)!=0.0 THEN a.amount-b.amount ELSE  ' '  END as Ledgeramount, b.bankdate from accountjournal a left join bankrecord b on a.id=b.accid join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'd'  and a.active=true and a.date<=? and (case WHEN a.date<=? and b.bankdate<=? and (a.amount-b.amount)!=0.0 THEN a.amount-b.amount WHEN a.date<=? and b.bankdate>? or b.bankdate is null THEN a.amount END) and (b.bankdate<=? or b.bankdate>? or b.bankdate is null ) order by a.date"
                  ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadClass(jBRStatementModel.jBRStatements.class)).list(new Object[]{fdate,fdate,fdate,fdate,accid, fdate, fdate,fdate,fdate,fdate,fdate,fdate});

//////                   
//               "SELECT  a.date as LedgerDate,NARRATION,TRANSTYPE ,(a.amount-b.amount) as amount , b.bankdate"
//                   + " from bankrecord b, accountjournal a join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'D' and (b.accid = a.id ) and a.date<=? and (b.bankdate<=? or b.bankdate is null) order by a.date" ,
//                  new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadClass(jBRStatementModel.jBRStatements.class)).list(new Object[]{accid, fdate,fdate});
////                   
//                   "SELECT b.bankdate,  (a.amount-b.amount) as amount,TRANSTYPE,NARRATION , a.date as LedgerDate, a.transtype as transtype, a.narration as narration "  
//                  + "from bankrecord b, accountjournal a join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'D' and b.accid = a.id and bankdate!= a.date and bankdate>a.date  and b.bankdate>=? and a.date<=?" ,new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadClass(jBRStatementModel.jBRStatements.class)).list(new Object[]{accid, fdate,fdate});
// 
           
            List<jBRStatement> breconlist1 = (List<jBRStatement>) new StaticSentence(app.getSession(),
//                     " SELECT  a.date as LedgerDate,NARRATION,TRANSTYPE, CASE  WHEN a.date<=? and b.bankdate>? or b.bankdate is null  THEN a.amount WHEN a.date<=? and b.bankdate<=? THEN a.amount-b.amount  ELSE  ''  END  as Ledgeramount, b.bankdate"
//                   +" from bankrecord b, accountjournal a join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'c' and (b.accid = a.id ) and a.date<=? and (b.bankdate<=? or b.bankdate>? or b.bankdate is null) order by a.date",
                   //"SELECT  a.date as LedgerDate,NARRATION,TRANSTYPE,TID, CASE  WHEN a.date<=? and b.bankdate>? or b.bankdate is null  THEN a.amount WHEN a.date<=? and b.bankdate<=? THEN a.amount-b.amount  ELSE  ''  END  as Ledgeramount, b.bankdate from accountjournal a left join bankrecord b on a.id=b.accid join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'c' and a.date<=?  and a.active=true  and (b.bankdate<=? or b.bankdate>? or b.bankdate is null ) order by a.date"
                  
//                     "SELECT  a.date as LedgerDate,NARRATION,TRANSTYPE ,(a.amount-b.amount) as amount , b.bankdate"
//                   + " from bankrecord b, accountjournal a join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'C' and (b.accid = a.id ) and a.date<=? and (b.bankdate>=? or b.bankdate is null) order by a.date" ,
                
                    "SELECT a.date as LedgerDate,NARRATION,TRANSTYPE,TID, CASE WHEN a.date<=? and b.bankdate>? or b.bankdate is null THEN a.amount WHEN a.date<=? and b.bankdate<=?and (a.amount-b.amount)!=0.0 THEN a.amount-b.amount ELSE  ' '  END as Ledgeramount, b.bankdate from accountjournal a left join bankrecord b on a.id=b.accid join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'c'  and a.active=true and a.date<=? and (case WHEN a.date<=? and b.bankdate<=? and (a.amount-b.amount)!=0.0 THEN a.amount-b.amount WHEN a.date<=? and b.bankdate>? or b.bankdate is null THEN a.amount END) and (b.bankdate<=? or b.bankdate>? or b.bankdate is null ) order by a.date"
                    , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(jBRStatementModel.jBRStatement.class)).list(new Object[]{fdate,fdate,fdate,fdate,accid, fdate, fdate,fdate,fdate,fdate,fdate,fdate});
            
                  
                    
//                , "SELECT b.bankdate, (a.amount-b.amount) as amount, TRANSTYPE, NARRATION ,a.date as LedgerDate, a.transtype as transtype, a.narration as narration "  
//                  + "from bankrecord b, accountjournal a join accountmaster am on am.id=a.accountid where a.accountid=? and a.transtype = 'C' and b.accid = a.id and bankdate!= a.date and bankdate>a.date and b.bankdate>=? and a.date<=?"  ,new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadClass(jBRStatementModel.jBRStatement.class)).list(new Object[]{accid, fdate, fdate});
    if (breconlist!= null ) {
         p1.brecons = breconlist;
        
        } else {
            
             p1.brecons = new ArrayList<jBRStatements>();
     }
      if (breconlist1!= null ) {
         p1.breconss = breconlist1;
        
        } else {
            
             p1.breconss = new ArrayList<jBRStatement>();
     }
    
    return p1;
  
         }
         
         
   
    public static String[] getBankHEADERS() {
        return BankHEADERS;
    }
    
      public List<jBRStatements> getBrecons() {
        return brecons;
    }
   
       public static String[] getBankHEADERSS() {
        return BankHEADERSS;
    }
       public List<jBRStatement> getBreconss() {
        return breconss;
    }
   
    public Date getDate() {
        return date;
    }
    

    
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
              
             Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

         
           public int getRowCount() {
                return brecons.size();
            }

            public int getColumnCount() {
                return BankHEADERS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BankHEADERS[column]);
            }

    public Object getValueAt(int row, int col) {
              jBRStatements bl = brecons.get(row);
             
                switch (col) {
                  case 5:
                   
                   return bl.getDates();//return bankdate
                   
                    case 4:
                       
                    case 3:
                        if (bl.getTransType1().equals("D")) {
                          //  return bl.getAmount();
                          String s2=new DecimalFormat("####0.00").format(bl.getAmount());
                            return s2;
                        } else {
                           
                            return " ";
                        }
                    case 2:
                         return bl.getNarrate();
                      case 0:
                   
                  return bl.getBankDates();//return ledger date
                    case 1:
                   
                   return bl.getTid();
                    
                }
                return bl;
            }
    
        };
        
        
        
        
                }    
    
    public static class jBRStatements implements SerializableRead {

       private String tid;
        private Date Dates;
        //private Date Date1;
        private double Amount;
        private String TransType;
        private String TransNo;
        private String TransRef;
        private String Narrate;
        private String TransType1;
        private String Id;
        private String Name;
        private String Pref;
        private String Cby;
        private double Amount1;
        private Date BankDates;
        private double Amount2;
        private boolean selected;
     
        public void readValues(DataRead dr) throws BasicException {
            Dates = dr.getTimestamp(6);  
          
            Amount = dr.getDouble(5);
              TransType1 = dr.getString(3);
            Narrate =dr.getString(2);
          BankDates =dr.getTimestamp(1);
              tid= dr.getString(4);
           }

        public String getId() {
            return Id;
        }

        public void setDates(Date Dates) {
            this.Dates = Dates;
        }

        public double getAmount2() {
            return Amount2;
        }

        public void setAmount2(double Amount2) {
            this.Amount2 = Amount2;
        }

     

        public Date getDates() {
            return Dates;
        }

        public void setNarrate(String Narrate) {
            this.Narrate = Narrate;
        }

        public void setTansType(String TansType) {
            this.TransType = TansType;
        }

        public String getNarrate() {
            return Narrate;
        }

        public String getTansType() {
            return TransType;
        }

        public double getAmount() {
            return Amount;
        }

        public double getAmount1() {
            return Amount1;
        }

        public String getCby() {
            return Cby;
        }

        public String getName() {
            return Name;
        }

        public String getPref() {
            return Pref;
        }

        public String getTransNo() {
            return TransNo;
        }

        public String getTransRef() {
            return TransRef;
        }

        public  Date getBankDates() {
            return BankDates;
        }

        public void setBankDates( Date BankDates) {
            this.BankDates = BankDates;
        }

        
        public String getTransType() {
            return TransType;
        }

        public String getTransType1() {
            return TransType1;
        }

        public String getTid() {
            return tid;
        }

        private Date setDates(Object value) {
            return (Date) value;
        } 
         public boolean isSelected() {
            return selected;
        }
        
         public void setStatus(boolean status) {
            selected = status;
        }
     
   
    }
    
     public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {
              
             Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

         
           public int getRowCount() {
                return breconss.size();
            }

            public int getColumnCount() {
                return BankHEADERSS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BankHEADERSS[column]);
            }

    public Object getValueAt(int row, int col) {
              jBRStatement b1 = breconss.get(row);
             
                switch (col) {
                  case 5:
                   
                   return b1.getDates();//return bankdate
                   
                    case 4:
                       
                    case 3:
                        if (b1.getTransType1().equals("C")) {
                           // return b1.getAmount();
                          String s3= new DecimalFormat("####0.00").format( b1.getAmount());
                          return s3;
                        } else {
                           
                            return " ";
                        }
                    case 2:
                         return b1.getNarrate();
                      case 0:
                   
                  return b1.getBankDates();
                    case 1:
                   
                   return b1.getTid();//return ledger date
                    
                }
                return b1;
            }
    
        };
        
        
        
                }    
    
    public static class jBRStatement implements SerializableRead {

       private String tid;
        private Date Dates;
         //private Date Date1;
        private double Amount;
        private String TransType;
        private String TransNo;
        private String TransRef;
        private String Narrate;
        private String TransType1;
        private String Id;
        private String Name;
        private String Pref;
        private String Cby;
        private double Amount1;
        private  Date BankDates;
        private double Amount2;
        private boolean selected;
     
        public void readValues(DataRead dr) throws BasicException {
          Dates = dr.getTimestamp(6);  
          
            Amount = dr.getDouble(5);
              TransType1 = dr.getString(3);
            Narrate =dr.getString(2);
          BankDates =dr.getTimestamp(1);
              tid= dr.getString(4);
           }

        public String getId() {
            return Id;
        }

       

        public void setDates(Date Dates) {
            this.Dates = Dates;
        }

        public double getAmount2() {
            return Amount2;
        }

        public void setAmount2(double Amount2) {
            this.Amount2 = Amount2;
        }

        public Date getDates() {
            return Dates;
        }

        public void setNarrate(String Narrate) {
            this.Narrate = Narrate;
        }

        public void setTansType(String TansType) {
            this.TransType = TansType;
        }

        public String getNarrate() {
            return Narrate;
        }

        public String getTansType() {
            return TransType;
        }

        public double getAmount() {
            return Amount;
        }

        public double getAmount1() {
            return Amount1;
        }

        public String getCby() {
            return Cby;
        }

        public String getName() {
            return Name;
        }

        public String getPref() {
            return Pref;
        }

        public String getTransNo() {
            return TransNo;
        }

        public String getTransRef() {
            return TransRef;
        }

        public Date getBankDates() {
            return BankDates;
        }

        public void setBankDates(Date BankDates) {
            this.BankDates = BankDates;
        }

        
        public String getTransType() {
            return TransType;
        }

        public String getTransType1() {
            return TransType1;
        }

        public String getTid() {
            return tid;
        }

        private Date setDates(Object value) {
            return (Date) value;
        } 
         public boolean isSelected() {
            return selected;
        }
        
         public void setStatus(boolean status) {
            selected = status;
        }
     
   
    
}
//     private String BankName;
//     private  Date Date;
//
//    public String getBankname() {
//        return BankName;
//    }
//
//    public void setBankname(String BankName) {
//        this.BankName=BankName;
//    }
//      public void setDate(Date Date) {
//            this.Date = Date;
//        }
}


  
    
 