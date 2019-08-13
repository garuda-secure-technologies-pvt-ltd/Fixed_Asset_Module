

package com.openbravo.pos.panels;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.*;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
//import com.openbravo.pos.sales.BillInfo;
//import com.openbravo.pos.sales.BillLineInfo;
//import com.openbravo.pos.sales.restaurant.QTList;
//import com.openbravo.pos.sales.QticketInfo;
import com.openbravo.pos.util.StringUtils;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author adrianromero
 */
public class CloseDayModel  {

    private String m_sHost;
    private int m_iSeq;
    private Date m_dDateStart;
    private Date m_dDateEnd;
    private Integer m_iSales;
    private Double m_dSalesBase;
    private Double m_dSalesTaxes;
    private java.util.List<SalesLine> m_lsales;
    private Integer m_iPayments;
    private Double m_dPaymentsTotal;

    private String m_billno;
    private String customername;
    private double billamt;
    private double taxamt;
    private double total;
    private double billtotal;
    private double totaltax;
    private double totals;
    private List<SalesLine> m_billinfo;
    private java.util.List<OtherIncome> otherIncomedetails;
    private java.util.List<ChequeDetailLine> cdetail;
   
  //private final static String[] SALESHEADERS = {"BillNO", "Customer", "BillAmount","Tax", "Total" };
    private final static String[] SALESHEADERS = {"Bill No","Customer","Game","Amount","Collection"};
    private final static String[] TOKENHEADER = {"Name","Rate","Quantity"};
    private final static String[] TOKENHEADER1 = {"Name","Rate","Quantity"};
    private String pdtname;
    private int qty;
    private double rate;
    private double amount;
    private double amttotal;
    private double taxtotal;
    private double pdtamttotal;
    private double pendingamt;


    private CloseDayModel() {
    }

    public static CloseDayModel emptyInstance() {

        CloseDayModel p = new CloseDayModel();

        p.m_billno = new String();
        p.customername = new String();
        p.billamt = 0.0;
        p.billtotal = 0.0;
        p.totaltax = 0.0;
        p.totals = 0.0;
        p.taxamt = 0.0;
        p.total = 0.0;

        p.pdtname = new String();
        p.qty = 0;
        p.rate = 0.0;
        p.amount = 0.0;
        p.amttotal = 0.0;
        p.taxtotal = 0.0;
        p.pdtamttotal = 0.0;
        p.m_billinfo = new ArrayList<SalesLine>();
        p.m_iPayments = new Integer(0);
        p.m_dPaymentsTotal = new Double(0.0);
       // p.m_lpayments = new ArrayList<PaymentsLine>();
        p.otherIncomedetails=new ArrayList<OtherIncome>();
        p.cdetail=new ArrayList<ChequeDetailLine>();
        p.m_iSales = null;
        p.m_dSalesBase = null;
        p.m_dSalesTaxes = null;
        p.m_lsales = new ArrayList<SalesLine>();
        p.pendingamt=0.0;

        return p;
    }
   
   
    private AppView m_App;
    public static CloseDayModel loadInstance(AppView app,Date d) throws BasicException {

        CloseDayModel p = new CloseDayModel();
        p.m_App=app;
      
         Object[] seqobj=(Object[]) new StaticSentence(app.getSession()
                 , "SELECT MAX(SEQUENCE) FROM CLOSEDSALE WHERE ROLE = ? "
                 , SerializerWriteString.INSTANCE
                 ,new SerializerReadBasic(new Datas[]{Datas.INT})).find(app.getAppUserView().getUser().getRole());
         if(seqobj==null || seqobj[0]==null){
             p.m_iSeq=1;
         }
         else
             p.m_iSeq=Integer.parseInt(seqobj[0].toString())+1;
        // Propiedades globales
        p.m_sHost = app.getProperties().getHost();
       // p.m_iSeq = app.getActiveCashSequence();
        p.m_dDateStart = app.getAppUserView().getUser().getOpenSaleTime();
        p.m_dDateEnd = null;
        p.pdtamttotal=0.0;
        p.taxtotal=0.0;
        p.amttotal=0.0;
        p.totaltax=0.0;

        List salebillwise = new StaticSentence(app.getSession(),
                "SELECT G.PAYMENTREF,C.NAME,R.DESC_,G1.GAMEAMOUNT,G.CLUBCOLLECTION FROM GAMELOG G,CUSTOMERS C,RECEIPTS R,GAMES G1 WHERE G.JACKID=C.ID AND G.PAYMENTREF=R.ID AND R.DESC_=G1.NAME AND R.CLOSECASHSEQ IS NULL AND G.COMPLETEDON<=?"
                /*" SELECT SUM(TOTAL) AS TOTAL,GAMETYPE FROM (SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,CUSTOMERS.NAME,PAYMENTS.TOTAL AS TOTAL,RECEIPTS.DESC_,(SELECT NAME FROM GAMES WHERE (SELECT PARENT  FROM GAMES,RECEIPTS WHERE GAMES.NAME=RECEIPTS.DESC_)=GAMES.ID)AS GAMETYPE,CUSTOMERS.ACCOUNT,CUSTOMERS.SEARCHKEY,RECEIPTS.PAYMENTREF FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE"+
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT AND PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL  "+
                 " UNION ALL SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,PAYMENTS.CUSTOMER,PAYMENTS.TOTAL,RECEIPTS.DESC_,(SELECT NAME FROM GAMES WHERE (SELECT PARENT  FROM GAMES,RECEIPTS WHERE GAMES.NAME=RECEIPTS.DESC_)=GAMES.ID)AS GAMETYPE,RECEIPTS.PAYMENTREF,PAYMENTS.CUSTOMER,RECEIPTS.PAYMENTREF FROM PAYMENTS,RECEIPTS,PEOPLE"+
                " WHERE  PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT AND    PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL  AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL   ORDER BY PAYMENTS.RECEIPT) GROUP BY GAMETYPE"*/
               ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
               ,new SerializerReadClass(CloseDayModel.SalesLine.class))
                .list(new Object[]{d});
                
                
                /*,"SELECT DISTINCT BILL.ID, BILL.CUSTOMER, BILL.AMOUNT, BILL.TAXTOTAL "
                +"FROM BILL, PEOPLE ,ROLES "
                +"WHERE  BILL.CLOSESALESEQ IS NULL AND  PEOPLE.NAME=BILL.CREATEDBY AND ROLES.ID=PEOPLE.ROLE  AND BILL.CREATEDDATE <=?  AND ROLES.ID = ? "
                ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                , new SerializerReadClass( SalessModel.SalesLine.class )).list(new Object[]{d,app.getAppUserView().getUser().getRole()});*/

        if (salebillwise == null) {
            p.m_billinfo = new ArrayList();
        }
        else {
            p.m_billinfo = salebillwise;
        }


         List<OtherIncome> rdetail3=new StaticSentence(app.getSession(),
                "select p.name,t.rate,sum(t.qty) from tokenstockdetail t,products p where p.id=t.pdid group by p.name,t.rate"
                ,null
                //,SerializerReadString.INSTANCE).list()
               ,new SerializerReadClass(CloseDayModel.OtherIncome.class))
                .list();
           if (rdetail3 == null) {
             p.otherIncomedetails = new ArrayList<OtherIncome>();
           } else {
             p.otherIncomedetails = rdetail3;
           }

         List<ChequeDetailLine> chdetail = new StaticSentence(app.getSession(),
                "select p.name,t.rate,sum(t.qty) from tokenstockdetail t,products p where p.id=t.pdid  group by p.name,t.rate"
                ,null
                , new SerializerReadClass(CloseDayModel.ChequeDetailLine.class))
                .list();
        if (chdetail == null) {
            p.cdetail = new ArrayList<ChequeDetailLine>();
        } else {
            p.cdetail = chdetail;
        }
        return p;
     }

 /*   public String getqtid(int row) {
        return qtId.get(row);
    }
    public String getcustomer(int row) {
        return customer.get(row);
    }
    public String getwaiter(int row) {
        return waiter.get(row);
    }
    public String gettable(int row) {
        return table.get(row);
    }
    public String getfloor(int row) {
        return floor.get(row);
    }
    public Date getcreated_date(int row) {
        return created_date.get(row);
    }*/
    public String getbillno() {
        return m_billno;
    }


    public String getCustomer() {
        String x = new String();
        try {
         x = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(customername).getName().toString();
        } catch(BasicException e) {
            new MessageInf(e).notify();
        }
        return x;
    }
   
   
    public List<SalesLine> getSalesLine() {
        return m_billinfo;
    }
 
    public double getbillamt() {
        return billamt;
    }

    public double gettaxamt() {
        return taxamt;
    }
    public int getPayments() {
        return m_iPayments.intValue();
    }
    public double getTotal() {
        return billtotal+totaltax;
    }
    public String getHost() {
        return m_sHost;
    }
    public int getSequence() {
        return m_iSeq;
    }
    public Date getDateStart() {
        return m_dDateStart;
    }
    public void setDateEnd(Date dValue) {
        m_dDateEnd = dValue;
    }
    public Date getDateEnd() {
        return m_dDateEnd;
    }
    public String printPendingamt()
    {
        return Formats.CURRENCY.formatValue(pendingamt);
    }
    public String printsubbill() {
        return Formats.CURRENCY.formatValue(billtotal);
    }
    public String printsubtax() {
        return Formats.CURRENCY.formatValue(totaltax);
    }

    public String printTotals() {
        return Formats.CURRENCY.formatValue(billtotal+totaltax);
    }
    public Double getTotals(){
       return billtotal+totaltax;
    }

    public String printpdtamount() {
        return Formats.CURRENCY.formatValue(amttotal);
    }
    public String printtaxamt() {
        return Formats.CURRENCY.formatValue(taxtotal);
    }
    public String printtotalamt() {
        return Formats.CURRENCY.formatValue(pdtamttotal);
    }
    public String printHost() {
        return m_sHost;
    }
    public String printUser() {
        return LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
    }
    public String printRole() {
        String x = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        return LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString();
    }
    public String printSequence() {
        return Formats.INT.formatValue(m_iSeq);
    }
    public String printDateStart() {
        return Formats.TIMESTAMP.formatValue(m_dDateStart);
    }
    public String printDateEnd() {
        return Formats.TIMESTAMP.formatValue(m_dDateEnd);
    }

    public String printPayments() {
        return Formats.INT.formatValue(m_iPayments);
    }

    public String printPaymentsTotal() {
        return Formats.CURRENCY.formatValue(m_dPaymentsTotal);
    }
  /*  public String printQtid(int row)
    {
        return
    }*/

    public List<SalesLine> getSalesLines() {
        return m_billinfo;
    }

    /////
    /////
    ////
    /////
    /////

    public int getSales() {
        return m_iSales == null ? 0 : m_iSales.intValue();
    }
    public String printSales() {
        return Formats.INT.formatValue(m_iSales);
    }
    public String printSalesBase() {
        return Formats.CURRENCY.formatValue(m_dSalesBase);
    }
    public String printSalesTaxes() {
        return Formats.CURRENCY.formatValue(m_dSalesTaxes);
    }
    public String printSalesTotal() {
        return Formats.CURRENCY.formatValue((m_dSalesBase == null || m_dSalesTaxes == null)
                ? null
                : m_dSalesBase + m_dSalesTaxes);
    }
    //////
    /////
    /////
    /////
    public List<SalesLine> getSaleLines() {
        return m_lsales;
    }

     public AbstractTableModel getSalesModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(SALESHEADERS[column]);
            }
            public int getRowCount() {
                return m_billinfo.size();
            }
            public int getColumnCount() {
                return SALESHEADERS.length;////////
            }
            public Object getValueAt(int row, int column) {
                SalesLine l = m_billinfo.get(row);
                switch (column) {
                case 0: return l.getbillno();
                case 1: return l.getcustomer();
                case 2: return l.getGameName();
                case 3: return l.getTotalamount();
                case 4: return l.getClubCollection();
                default: return null;
                }
            }
        };
    }


   

     public static class SalesLine implements SerializableRead {

        private String billno;
        private String customer;
        
        private double taxamount=0.0;
        private double total;
        private double subbill = 0.0;
        private double subtax = 0.0;
        private double maintotal = 0.0;
        private String gamename;
        private double totalamount;
        private double clubcollection;

        public void readValues(DataRead dr) throws BasicException {
            billno = dr.getString(1);
            customer = dr.getString(2);
            gamename = dr.getString(3);
           totalamount = dr.getDouble(4);
            clubcollection = dr.getDouble(5);
            total = clubcollection+ taxamount;
            subbill+=totalamount;
            subtax += taxamount;
            maintotal += total;
             
        }
        public String printbillno() {
            return billno;
        }
        public String printTaxamt() {
            return Formats.CURRENCY.formatValue(taxamount);
        }

        public String printcustomer() {

            return StringUtils.encodeXML(customer);
        }

        public String printbillamt() {
            return Formats.CURRENCY.formatValue(totalamount);
        }

        public String printTotal() {
            return Formats.CURRENCY.formatValue(total);
        }
        public String getbillno() {
            return billno;
        }
         public double getClubCollection() {
            return clubcollection;
        }

         public double getClubCollectionTotal() {
            return total;
        }
        public String getcustomer() {
         /*   String cust1="";

             String temp[]=customer.split("#");
            try{
               //  if(temp.length>1){
             Object[] cust=(Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(),
                "SELECT NAME FROM CUSTOMERS WHERE ID = ? ",
                SerializerWriteString.INSTANCE
                , new SerializerReadBasic(new Datas[] { Datas.STRING})).find(temp[0]);
             if(cust!=null && cust[0] != null)
           cust1=cust[0].toString();

               //  }
              }
            catch(Exception e)
            {
            }
            if(temp.length>1)
                return cust1+" : "+temp[1];
            else
             return cust1;*/
           return customer;
        }
        public Double getbillamount() {
            return totalamount;
        }

        public Double gettaxamount() {
            return taxamount;
        }

        public Double getTotal() {
            return total;
        }

        private String getGameName() {
            return gamename;
        }

        private double getTotalamount() {
           return totalamount;
        }

       

        private Double getsubbill() {
            return subbill;
        }
        private Double getsubtax() {
            return subtax;
        }
        private Double getmaintotal() {
            return maintotal;
        }
    }

public AbstractTableModel getOtherIncomeTableModel() {
        return new AbstractTableModel() {
            public String getColumnName(int column) {
                return AppLocal.getIntString(TOKENHEADER[column]);
            }
            public int getRowCount() {
                return otherIncomedetails.size();
            }
            public int getColumnCount() {
                return TOKENHEADER.length;
            }
            public Object getValueAt(int row, int column) {
                OtherIncome l = otherIncomedetails.get(row);
                switch (column) {
                case 0: return l.getName();
                case 1: return l.getRate();
                case 2: return l.getQuantity();
                default: return null;
                }
            }
        };
    }

   public static class OtherIncome implements SerializableRead {
       private String name;
       private double rate;
       private int qty;
       public void readValues(DataRead dr) throws BasicException {
         name = dr.getString(1);
         rate = dr.getDouble(2);
         qty = dr.getInt(3);
        }
        public String getName()
        {
            return name;
        }
      public double getRate()
        {
            return rate;
        }
      public Integer getQuantity()
        {
            return qty;
        }
   }

     public AbstractTableModel getChequeDetailModel() {
        return new AbstractTableModel() {
            public String getColumnName(int column) {
                return AppLocal.getIntString(TOKENHEADER1[column]);
            }
            public int getRowCount() {
                return cdetail.size();
            }
            public int getColumnCount() {
                return TOKENHEADER1.length;
            }
            public Object getValueAt(int row, int column) {
                ChequeDetailLine l = cdetail.get(row);
                switch (column) {
               case 0: return l.getName1();
                case 1: return l.getRate1();
                case 2: return l.getQuantity1();
                default: return null;
                }
            }
        };
    }
    public static class ChequeDetailLine implements SerializableRead {

       private String name1;
       private double rate1;
       private int qty1;

        public void readValues(DataRead dr) throws BasicException {
            name1 = dr.getString(1);
         rate1 = dr.getDouble(2);
         qty1 = dr.getInt(3);
        }

        public String getName1()
        {
            return name1;
        }
      public double getRate1()
        {
            return rate1;
        }
      public Integer getQuantity1()
        {
            return qty1;
        }

    }
}