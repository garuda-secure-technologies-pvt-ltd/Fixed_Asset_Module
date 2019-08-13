/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.MemDebtBillingTableModel.MyAbstractTableModel;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class RecieptDetailsTableModel {
    
    
   private final static String[] HEADERS1 = { "Receipt No", "Date", "Balance Amt.", "Original Amt."}; 
   private final static String[] HEADERS = {"Facility Name", "Billed Date","Org. Amt", "Adjusted Amt","Bill No"}; 
   private List<RecieptDetailaBean> rdbl;
     private DataLogicFacilities dlfac;
   private  List<RecieptDetailsTableModel.RecieptDetailaBean> rdblist = new ArrayList<RecieptDetailsTableModel.RecieptDetailaBean>();
   private  List<RecieptDetailsTableModel.RecieptAmountBean> rdblistAmt = new ArrayList<RecieptDetailsTableModel.RecieptAmountBean>();
   
   private double adjustedAmt;
   private double unadjustedAmt;
   private double totalAmt;
   private Timestamp receiptdate;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");
    private String nosuchrecp=null;

    public String getNosuchrecp() {
        return nosuchrecp;
    }
    
    
    public Timestamp getReceiptdate() {
        
        return receiptdate;
    }

    public void setReceiptdate(Timestamp receiptdate) {
        this.receiptdate = receiptdate;
    }
   
  
    public List<RecieptDetailaBean> getRdblist() {
        return rdblist;
    }

    public void setRdblist(List<RecieptDetailaBean> rdblist) {
        this.rdblist = rdblist;
    }

   
   
    public double getAdjustedAmt() {
        return adjustedAmt;
    }

    public void setAdjustedAmt(double adjustedAmt) {
        this.adjustedAmt = adjustedAmt;
    }

    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public double getUnadjustedAmt() {
        return unadjustedAmt;
    }

    public void setUnadjustedAmt(double unadjustedAmt) {
        this.unadjustedAmt = unadjustedAmt;
    }
   
   
   public static RecieptDetailsTableModel emptyInstance()
   {
       RecieptDetailsTableModel rd =new RecieptDetailsTableModel();
       rd.rdblist =new ArrayList<RecieptDetailsTableModel.RecieptDetailaBean>();
       return rd;
   }
   
   
   public static  RecieptDetailsTableModel loadInstance(String recieptno,AppView app,DataLogicFacilities dlfacility)
    {
       
          List<RecieptDetailsTableModel.RecieptDetailaBean> lst1 = new ArrayList<RecieptDetailsTableModel.RecieptDetailaBean>();
          RecieptDetailsTableModel rd =new RecieptDetailsTableModel();
          rd.dlfac=dlfacility;
          List<Object[]> obj;
          Object[] objarr=null;
          List<String[]> listOfBillRefAndAmt=new ArrayList<String[]>();
          HashSet<String> setOfBillRef=new HashSet<String>();
          rd.adjustedAmt=0.00;
          //List<RecieptDetailsTableModel.RecieptDetailaBean> rdblist = new ArrayList<RecieptDetailsTableModel.RecieptDetailaBean>();
          
        try {
             obj=(List<Object[]>)new StaticSentence(app.getSession(),"SELECT memid,paymentref,accountid,Amount,balanceamount,date FROM accountjournal where memid is not null and transno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.TIMESTAMP})).list(recieptno);
  
            for (Object[] object : obj) {
                objarr=object;
            }
            if(obj.size()!=0)
            {
             if(objarr[1]!=null)
             {
                 rd.totalAmt=Double.parseDouble(objarr[3].toString());    
                  rd.unadjustedAmt=Double.parseDouble(objarr[4].toString());
                  rd.receiptdate=(Timestamp)objarr[5];
                  String paymntref=objarr[1].toString();
                 String splitstr[]=paymntref.split(":");
                for(int i=0;i<splitstr.length;i++)
                {
                   String []billref=splitstr[i].split("#");
                
                  listOfBillRefAndAmt.add(billref);
                 //System.out.println();
                }
            
          //  for (String str[] : listOfBillRefAndAmt) {
           //    String billno=str[0];
          //  System.out.println(str[0]);
           // System.out.println(str[1]);
            
            //   List lst=new StaticSentence(app.getSession(),"select name ,date, Amount,Adjamt,transno from(select F.name,A.date,A.amount,null as adjamt,a.transno from FACILITY F join accountjournal a on f.id=a.transref where transno=?  and paymentref=? and memid=?"+
            //                                   "union all SELECT A.TRANSREF AS NAME,a.date,a.amount,null as adjamt,a.transno from accountjournal a where transno=? and paymentref=? and memid=?and A.TRANSREF NOT IN (" +
            //                                   "SELECT ID FROM FACILITY)) as recieptdetails",new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(RecieptDetailsTableModel.RecieptDetailaBean.class)).list(new Object[]{billno,recieptno, objarr[0].toString(),billno, recieptno, objarr[0].toString()});
            
            // System.out.println();
            //}
                List<RecieptDetailsTableModel.RecieptDetailaBean> lst = new ArrayList<RecieptDetailsTableModel.RecieptDetailaBean>();
                 for(int i=0;i<listOfBillRefAndAmt.size();i++){
                 String []s1 = listOfBillRefAndAmt.get(i);
                  String billno = s1[0].trim(); 
                  setOfBillRef.add(billno);
                
                }
             
               List<Object[]> tidlist=new ArrayList<Object[]>();
          
               for (String bno : setOfBillRef) {
                 String memid=objarr[0].toString().trim();  
                 List<Object[]> obj1= (List<Object[]>)new StaticSentence(app.getSession(),"SELECT transno,tid,amount FROM accountjournal where transno=? and paymentref=? and memid=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.DOUBLE})).list(new Object[]{bno,recieptno,memid});
                   
                  tidlist.addAll(obj1);
               }
          
          
          
          
          
             //for (Iterator<String> it = setOfBillRef.iterator(); it.hasNext();) {
              //  String billno = it.next();
                
            for(int i=0;i<listOfBillRefAndAmt.size();i++){
                double adjamt = 0;
               String []s1 = listOfBillRefAndAmt.get(i);
               String memid=objarr[0].toString().trim();
               String billno = s1[0].trim(); 
               if(s1.length>1)
               {
                adjamt=Double.parseDouble(s1[1]);
               }
               String accid=objarr[2].toString().trim();
               String recno='%'+recieptno+'%';
               rd.adjustedAmt+=adjamt;
               
                 lst1 =   new StaticSentence(app.getSession(),"select name ,date, Amount,Adjamt,transno from(select F.name,A.date,A.amount,? as adjamt,a.transno from FACILITY F join accountjournal a on f.id=a.transref where transno=?  and paymentref like ? and memid=?"+
                                               "union all SELECT A.TRANSREF AS NAME,a.date,a.amount,? as adjamt,a.transno from accountjournal a where transno=? and paymentref like ? and memid=?and A.TRANSREF NOT IN (" +
                                               "SELECT ID FROM FACILITY)"+ 
                         "union all SELECT A.TRANSREF AS NAME,a.date,a.amount,? as adjamt,a.transno from accountjournal a where transno=? and paymentref like ? and accountid=? and memid is null and A.TRANSREF NOT IN( SELECT ID FROM FACILITY)"+
                          ") as recieptdetails",new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING, Datas.STRING, Datas.STRING,Datas.DOUBLE, Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING, Datas.STRING, Datas.STRING}),new SerializerReadClass(RecieptDetailsTableModel.RecieptDetailaBean.class)).list(new Object[]{adjamt, billno,recno, memid,adjamt, billno, recno, memid,adjamt, billno,recno,accid});
                
                rd.rdblist.addAll(lst1);
                   
            }
           } 
             else
             {
                  rd.receiptdate=(Timestamp)objarr[5];
                 rd.unadjustedAmt=Double.parseDouble(objarr[4].toString());
                  rd.totalAmt=Double.parseDouble(objarr[3].toString()); 
             }
        } 
            else
            {
               rd.nosuchrecp="No such Receipt NO.Please enter proper Receipt NO";
            }
           
           // System.out.println();
      } catch (BasicException ex) {
            Logger.getLogger(RecieptDetailsTableModel.class.getName()).log(Level.SEVERE, null, ex);
     }
        
      
          return rd;
 }
   
   
   
   public abstract class MyAbstractTableModel extends AbstractTableModel {

        
    }
   
   
   public MyAbstractTableModel getTableModel() {
        return new MyAbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return (HEADERS[column]);
            }
             Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false,false
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
                return rdblist.size();
            }

            public int getColumnCount() {
                return HEADERS.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
               RecieptDetailaBean l= rdblist.get(rowIndex);
                  switch (columnIndex) {

                    case 0:
                        return l.getFacilityName();
                    case 1:
                        return Formats.DATE.formatValue(l.getBilledDate());
                    case 2:
                        return dlfac.roundTwoDecimals(l.getOriginalAmt());
                    case 3:
                        return dlfac.roundTwoDecimals(l.getAdjustedAmt());
                    case 4:
                     return l.getBillno();
                    
                    default:
                        return null;
                }
            }
        };
                
        }
   
   
   
   
   
   public static class RecieptDetailaBean implements SerializableRead
   {
        private String facilityName;
        //private Timestamp billedDate;
        private Double originalAmt;
        private Double adjustedAmt;
        private String billno;
        private Timestamp billedDate;

        public String getBillno() {
            return billno;
        }

        public void setBillno(String billno) {
            this.billno = billno;
        }
        

        public Double getAdjustedAmt() {
            return adjustedAmt;
        }

        public void setAdjustedAmt(Double adjustedAmt) {
            this.adjustedAmt = adjustedAmt;
        }

        public Timestamp getBilledDate() {
            return billedDate;
        }

        public void setBilledDate(Timestamp billedDate) {
            this.billedDate = billedDate;
        }

        public String getFacilityName() {
            return facilityName;
        }

        public void setFacilityName(String facilityName) {
            this.facilityName = facilityName;
        }

        public Double getOriginalAmt() {
            return originalAmt;
        }

        public void setOriginalAmt(Double originalAmt) {
            this.originalAmt = originalAmt;
        }
        
       
       
        public void readValues(DataRead dr) throws BasicException {
            facilityName=dr.getString(1);
            billedDate=dr.getTimestamp(2);
            originalAmt=dr.getDouble(3);
            adjustedAmt=dr.getDouble(4);
            billno=dr.getString(5);
            
        }
        
     }
   
   
        public static class RecieptAmountBean implements SerializableRead
        {
            private String receiptNo;
            private Timestamp date;
            private Double balAmt;
            private Double originalAmt;

            public Double getBalAmt() {
                return balAmt;
            }

            public void setBalAmt(Double balAmt) {
                this.balAmt = balAmt;
            }

            public Timestamp getDate() {
                return date;
            }

            public void setDate(Timestamp date) {
                this.date = date;
            }

            public Double getOriginalAmt() {
                return originalAmt;
            }

            public void setOriginalAmt(Double originalAmt) {
                this.originalAmt = originalAmt;
            }

            public String getReceiptNo() {
                return receiptNo;
            }

            public void setReceiptNo(String receiptNo) {
                this.receiptNo = receiptNo;
            }
            
  
            public void readValues(DataRead dr) throws BasicException {
                 receiptNo=dr.getString(1);
                 date=dr.getTimestamp(2);
                balAmt=dr.getDouble(3);
                 originalAmt=dr.getDouble(4);
           
            }
            
        }
        
   
}
