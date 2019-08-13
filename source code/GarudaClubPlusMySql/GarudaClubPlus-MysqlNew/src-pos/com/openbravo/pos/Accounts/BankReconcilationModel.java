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
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
//import interfaces.*;



public class BankReconcilationModel {
     private int flag;
    private List<BankReconcilations> brecon;
    private DataLogicFacilities dlfac;
    private final static String[] BankHEADERS = {"Dates", "TID", "Narrate", "Deposit", "Withdraw", "RECL","Amount", "BankDates","","bankrecid"};
    private Date date;
    DateFormat formatter;
    
    String bid;
  
      public static BankReconcilationModel emptyinstance() {

        BankReconcilationModel b = new BankReconcilationModel();
        b.brecon = new ArrayList<BankReconcilations>();
        return b;
    }
    public static BankReconcilationModel loadInstance(AppView app,int flag, String accid, Date fdate, Date todate) throws BasicException {
        BankReconcilationModel p = new BankReconcilationModel();
          p.flag = flag;
          List<BankReconcilations> breconlist;
        //List dlist;
     if (flag == 1) {
        
            breconlist = new PreparedSentence(app.getSession(), "SELECT TID,DATE,AMOUNT,TRANSTYPE,TRANSNO,TRANSREF,NARRATION,ID,NAME,pref,cby,bankrecid,Amount1, bankdates FROM (" + "select  a.tid as tid, "
                + " a.date as date,a.amount as amount,b.id as bankrecid,b.amount as amount1,b.bankdate as bankdates ,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "
                + "from accountjournal a  left join bankrecord b on a.id=b.accid  join accountmaster am on am.id=a.accountid  where a.accountid=? and a.date>=? and a.date<=?  and a.active=true and a.amount!=0" + ") AS ACCRP ORDER BY 2,1", new SerializerWriteBasic(new Datas[]{Datas.STRING ,Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(BankReconcilationModel.BankReconcilations.class)).list(new Object[]{accid, fdate, todate});
           
   }
//            List<BankReconcilations> breconlist = new StaticSentence(app.getSession(), "SELECT TID,DATE,AMOUNT,TRANSTYPE,TRANSNO,TRANSREF,NARRATION,TRANSTYPE1,ID,NAME,pref,cby,amount1 FROM (" + "select  a.tid as tid, "
//                + " a.date as date,a.amount as amount,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a1.transtype as transtype1,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby,a1.amount as amount1 "
//               + "from accountjournal a join accountjournal a1 on  a.tid=a1.tid and a1.active=true  and a.id != a1.id  join accountmaster am on am.id=a1.accountid  where a.accountid=? and a.date>=? and a.date<=?  and a.active=true and a1.active=true  " + ") AS ACCRP ORDER BY 2,1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(BankReconcilations.class)).list(new Object[]{accid, fdate, todate});
////   
    //    }
     else{
                breconlist = new StaticSentence(app.getSession(), "SELECT TID,DATE,AMOUNT,TRANSTYPE,TRANSNO,TRANSREF,NARRATION,ID,NAME,pref,cby,bankrecid,Amount1, bankdates FROM (" + "select  a.tid as tid, "
                + " a.date as date,a.amount as amount,b.id as bankrecid,b.amount as amount1,b.bankdate as bankdates ,a.transtype as transtype,a.transno as transno,a.transref as transref,a.narration as narration,a.id as id,am.name as name,a.paymentref as pref,a.createdby as cby "
                + "from accountjournal a left join bankrecord b on a.id=b.accid or b.amount=null  join accountmaster am on am.id=a.accountid  where a.accountid=? and a.date>=? and a.date<=?  and a.active=true and (a.amount!=b.amount or b.amount is null) and a.amount!=0 " + ") AS ACCRP ORDER BY 2,1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(BankReconcilationModel.BankReconcilations.class)).list(new Object[]{accid, fdate, todate});
//
                  }
        if (breconlist == null) {

            p.brecon = new ArrayList();
        } else {
            p.brecon = breconlist;
        }
        return p;
    }
    
    public static String[] getBankHEADERS() {
        return BankHEADERS;
    }
    
    public List<BankReconcilations> getBrecon() {
        return brecon;
    }

    public Date getDate() {
        return date;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            
             Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,  java.lang.Boolean.class, java.lang.Double.class, java.lang.String.class,  java.lang.String.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true, true, true, false,false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
            public int getRowCount() {
                return brecon.size();
            }

            public int getColumnCount() {
                return BankHEADERS.length;
            }
            
              
           

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BankHEADERS[column]);
            }

            public Object getValueAt(int row, int col) {
                BankReconcilations bl = brecon.get(row);
                switch (col) {
                    case 0:
                         return bl.getDates();
                    case 1:
                        return bl.getTid();
                    case 2:
                        return bl.getNarrate();
                    case 4:
                        if (bl.getTransType().equals("C")) {
                            String s1=new DecimalFormat("####0.00").format(bl.getAmount());
                            return s1;
                         //  return (bl.getAmount());
                        } else {
                            return " ";
                        }
                    case 3:
                        if (bl.getTransType().equals("D")) {
                            String s2=new DecimalFormat("####0.00").format(bl.getAmount());
                            return s2;
                            // return (bl.getAmount());
                        } else {
                            return " ";
                        }
                   case 5:
                       if(bl.getAmount()==bl.getAmount1())
                       return true;
                       else return bl.isSelected();
                       // return bl.isSelected();                     
                       
                   case 6:
                     // return new DecimalFormat("####0.00").format(bl.getAmount1());
                      
                      return (bl.getAmount1());
                        
                    case 7: 
                         return bl.getBankDates();
                    case 8:
                        return bl.getId();
                        case 9:
                        return bl.getBankrecid();

                
               // return bl;
                default:
                        return null;
            }
            }

                   
            @Override
            public void setValueAt(Object value, int row, int col) {

                BankReconcilations bl = brecon.get(row);
               
                    if (col== 5) {
                    Double oamt = bl.getAmount1();
                    double namt = bl.getAmount();
                    boolean status = Boolean.parseBoolean(value.toString());
                   
                   if (status==true) {
                    
                    try{                                             
                       //bl.setAmount1(dlfac.roundTwoDecimals(bl.getAmount()));
                           bl.setAmount1(bl.getAmount());
                         
           
                        bl.setStatus(status);
                         }  catch(Exception e)
                    {
                    System.out.println("CheckBox EXception" + e);
                    
                  } 
                   
                   }
                    else {
                       namt= 0;
                       
                       bl.setAmount1(0);
                      bl.setBankDates(null);
                       bl.setStatus(status);
                  
                      
//////                
                    }
                    fireTableDataChanged();
                  }
                    else if (col == 2) {
                   
                    bl.setNarrate(value.toString());
                    }
                    else if (col == 6) {
//                if(value==null){
//                    bl.setAmount1(0.0);
//                }
//                else
                        bl.setAmount1(Double.parseDouble(value.toString()));
                        
                         fireTableDataChanged();
//                   
                    
             }
                
                   else 
                        if (col == 7) {
                     
                    try {
                          Date date1;
                     
                       if(value == null){
                           bl.setBankDates(null);
                       }else{
                          String s = value.toString();

                          
                            date1 = (Date) Formats.DATE.parseValue(s);
                           //added by pratima:                  
//                         java.sql.Timestamp sq = new java.sql.Timestamp(date1.getTime());
//                           bl.setBankDates(sq);
                           
                          bl.setBankDates(date1);
                       }
             
                    } catch (BasicException ex) {
                        Logger.getLogger(BankReconcilationModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   

                    fireTableDataChanged();
                }
            }   
    };
        
        }

    public static class BankReconcilations implements SerializableRead {

        private String tid;
        private Date Dates;
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
       // private String BankDates;
        private double Amount2;
        private boolean selected;
         private String bankrecid;
          private Date BankDates;
           private String Amounts;
        public void readValues(DataRead dr) throws BasicException {
            tid = dr.getString(1);
            Dates = dr.getTimestamp(2);
            Amount = dr.getDouble(3);
            TransType = dr.getString(4);
            TransNo = dr.getString(5);
            TransRef = dr.getString(6);
            Narrate = dr.getString(7);
            Id = dr.getString(8);
            Name = dr.getString(9);
            Pref = dr.getString(10);
            Cby = dr.getString(11);
            bankrecid= dr.getString(12);
            try{
            Amount1 = dr.getDouble(13);
            }
            catch(Exception e)
                    {
                    System.out.println(e);
                    }
            BankDates = dr.getTimestamp(14);
          
       selected = false;
           
             
               
//             tid = dr.getString(1);
//            Dates = dr.getTimestamp(2);
//            Amount = dr.getDouble(3);
//            TransType = dr.getString(4);
//            TransNo = dr.getString(5);
//            TransRef = dr.getString(6);
//            Narrate = dr.getString(7);
//            TransType1 = dr.getString(8);
//            Id = dr.getString(9);
//            Name = dr.getString(10);
//            Pref = dr.getString(11);
//            Cby = dr.getString(12);
////            
//        selected = false;
////            

        }

//        public String getAmounts() {
//            return Amounts;
//        }
//
//        public void setAmounts(double Amount1) throws ParseException {
//          //return  Formats.ConvertDoubleToString(Amount1);
//          //  return Formats.ConvertDoubleToString(f.getOverDueAmount());
//           Formats.ConvertStringToDouble(Amounts);
//            this.Amount1 = Amount1;
//        }

        public String getBankrecid() {
            return bankrecid;
        }

        public String getId() {
            return Id;
        }

//        public void setDates(Date Dates) {
//            this.Dates = Dates;
//        }   
//
//        public Date getDates() {
//            return Dates;
//        }
          public void setDates(Date Dates) {
            this.Dates = Dates;
        }   

        public String getDates() {
         String dates=new SimpleDateFormat("dd-MM-yyyy").format(Dates);
            return dates;
            
        }

        public void setNarrate(String Narrate) {
            this.Narrate = Narrate;
        }

        public void setAmount1(double Amount1)  {
           
            this.Amount1 = Amount1;
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
           
        // return Formats.CURRENCY.formatValue(new Double(getAmount1()));
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
//              private String setDates(Object value) {
//          Date date=(Date)value;
//          String sdate=new SimpleDateFormat("dd-MM-yyyy").format(date);
//          
//                  return  sdate;
//        } 
         public boolean isSelected() {
            return selected;
        }

         public void setStatus(boolean status) {
            selected = status;
        }

//          public String printAmount1() {
//            return Formats.CURRENCY.formatValue(Amounts);
//        }
          
//        public String printDate() {
//            return Formats.DATE.formatValue(Dates);
//        }
        }
    
    }
    
    
   
