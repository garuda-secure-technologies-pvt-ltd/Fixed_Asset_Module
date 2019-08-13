package com.openbravo.pos.panels;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import com.openbravo.basic.BasicException;
//import com.openbravo.data.gui.FindInfo;
import com.openbravo.data.loader.*;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.JPaymentSelect;
//import java.sql.Timestamp;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.sales.restaurant.DebtBillList.CreditConfirmList;
import com.openbravo.pos.util.StringUtils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author adrianromero
 */
public class PaymentsModel {

    private String m_sHost;
    private int m_iSeq;
    // private Date m_dDateStart;
    private Date m_dDateEnd;
    private double creditconftotal;
    private double cardsroomIncome;
    // private Integer m_iPayments;
    // private Double m_dPaymentsTotal;
    private java.util.List<OtherIncomeTotalLine> m_lotherincometotals;
    private java.util.List<OtherIncomeTotalLine> m_lotherincometotalswithGrouping;
    // private String paymentType;
    //  private Double paymentTotal;
    //  private String paymentType1;
    //  private Double paymentTotal1;
    private Double receiptTotal[];
    private String receiptcounter[];
    private Double receiptTotalsum;
    private Double receiptTotalsum1;
    private Double guestfee;
    private Double payTotal;
    private String billid;
    private final static String[] PAYMENTHEADERS = {"MODE OF PAYMENT", "TOTAL"};
    private final static String[] HEADERS = {"RECEIPT NO.", "DATE", "TOTAL"};
    private final static String[] HEADERS1 = {"Date", "Mem No", "Bill Ref.", "TOTAL"};
    private Integer m_iSales;
    //  private Double m_dSalesBase;
    //  private Double m_dSalesTaxes;
    private java.util.List<PaymentsLine1> m_lsales;
    private java.util.List<PaymentsLine2> m_lpayments;
    private java.util.List<UCReceiptLine> ucreceipts;
    private double ucreceipttotal;
    private Double dtotal;
    private Double cashinsum;
    private Double MgCardTotal;
    private List<Object> MagCardReceiptNos = new ArrayList<Object>();
    private Double balance;
    private final static String[] PAYMENTHEADERS2 = {"RECEIPT NO.", "MODE OF PAYMENT", "TOTAL"};
    private final static String[] PAYMENTHEADERS1 = {"MODE OF PAYMENT", "TOTAL"};
    private double paymentsout;
    private Double totaldebt;
    private Double debtcollection;
    private double debtraised;
    private double otherIncomeTotal;
    private double chequeamt;
    private java.sql.Timestamp m_rtime;
    private String m_rid;
    private String m_customer;
    private Double m_rtoatal;
    private double debtcollectedchequetotal;
    private double ochequetotal;
    //warehouse changes - start
    private java.util.List<WarehouseTotals> clist;
    private java.util.List<WarehouseTotals> clist1;
    //warehouse changes - end
    private java.util.List<RdetailsLine> m_lrdetails;
    private java.util.List<RdetailsLine2> m_lrdetails2;
    private java.util.List<RdetailsLine1> m_lrdetails1;
    private java.util.List<Debtcollected> drdetails;
    private java.util.List<OtherIncome> otherIncomedetails;
    private java.util.List<ChequeDetailLine> cdetail;
     private java.util.List<CardDetailLine> carddetail;
    private java.util.List<CreditConfirmList> crlist;
    private java.util.List<GuestReceiptdetailsLine> guestreceiptDetails;
    private java.util.List<GuestReceiptdetailsLine1> guestreceiptDetails1;
    private java.util.List<GuestReceiptdetailsLine1> guestreceiptDetails2;
    private final static String[] RECEIPTSHEADER = {"DATE", "R NO", "BILL ID", "CUSTOMER", "TOTAL", "COUNTER"};
    private final static String[] DEBTRAISEDHEADER = {"DATE", "R NO", "CUSTOMER", "TOTAL"};
    //private final static String[] DEBTRAISEDHEADER = {"DATE", "R NO","CUSTOMER","TOTAL","GAME","GAMETYPE"};
    private final static String[] CDETAILHEADERS = {"Cheque no", "Bank", "Amount", "member"};
     private final static String[] CARDDETAILHEADERS = {"Transaction no", "Bank", "Amount", "member"};
    private final static String[] COUNTERHEADERS = {"Name", "Amount"};
    private final static String[] COUNTERHEADERS1 = {"Name", "Amount"};
    private double issuedTokenAmount = 0.0;
    private double receivedTokenAmount = 0.0;
    private List<BillInfo> cashPend;

    private PaymentsModel() {
    }

    @SuppressWarnings("empty-statement")
    public static PaymentsModel emptyInstance() {

        PaymentsModel p = new PaymentsModel();

        //  p.m_iPayments = new Integer(0);
        //  p.m_dPaymentsTotal = new Double(0.0);
        p.paymentsout = 0;
        p.guestfee = new Double(0.0);
        p.creditconftotal = 0;
        //  p.paymentType=new String();
        //   p.paymentTotal=new Double(0.0);
        p.m_lotherincometotals = new ArrayList<OtherIncomeTotalLine>();
        p.crlist = new ArrayList<CreditConfirmList>();
        p.ucreceipts = new ArrayList<UCReceiptLine>();
        p.m_sHost = new String();
        p.receiptTotal = new Double[]{0.0};
        p.receiptcounter = new String[]{""};
        p.receiptTotalsum = new Double(0.0);
        p.receiptTotalsum = new Double(0.0);
        p.dtotal = new Double(0.0);
        p.m_iSales = null;
        p.ucreceipttotal = 0;
        //   p.m_dSalesBase = null;
        //    p.m_dSalesTaxes = null;
        p.billid = new String();
        //    p.paymentType1=new String();
        //     p.paymentTotal1=new Double(0.0);
        p.m_lsales = new ArrayList<PaymentsLine1>();
        p.m_lpayments = new ArrayList<PaymentsLine2>();
        p.cashinsum = new Double(0.0);
        p.balance = new Double(0.0);
        p.drdetails = new ArrayList<Debtcollected>();
        p.cdetail = new ArrayList<ChequeDetailLine>();
        p.carddetail = new ArrayList<CardDetailLine>();
        p.otherIncomedetails = new ArrayList<OtherIncome>();
        p.m_rid = new String();
        p.m_rtime = new java.sql.Timestamp(00 - 00 - 00);
        p.m_rtoatal = new Double(0.0);
        p.m_customer = new String();
        p.m_lrdetails = new ArrayList<RdetailsLine>();
        p.m_lrdetails1 = new ArrayList<RdetailsLine1>();
        p.m_lrdetails2 = new ArrayList<RdetailsLine2>();
        p.totaldebt = new Double(0.0);
        p.payTotal = new Double(0.0);
        p.debtcollection = new Double(0.0);
        p.debtraised = new Double(0.0);
        p.otherIncomeTotal = 0.0;
        //warehouse changes - start
        p.clist = new ArrayList<WarehouseTotals>();
        p.clist1 = new ArrayList<WarehouseTotals>();
        //warehouse changes - end
        p.guestreceiptDetails = new ArrayList<GuestReceiptdetailsLine>();
        p.guestreceiptDetails1 = new ArrayList<GuestReceiptdetailsLine1>();
        p.chequeamt = new Double(0.0);
        p.cashPend = new ArrayList<BillInfo>();
        return p;
    }

    public static PaymentsModel loadInstance(AppView app, Date date) throws BasicException {

        PaymentsModel p = new PaymentsModel();

        // Propiedades globales
        p.m_sHost = app.getProperties().getHost();
        // p.m_iSeq = app.getActiveCashSequence();
        //   p.m_dDateStart = app.getAppUserView().getUser().getOpenCashTime();
        p.m_dDateEnd = null;
        // p.m_iSeq=app.getActiveCashSequence();
        Object[] seq = (Object[]) new PreparedSentence(app.getSession(), "SELECT MAX(HOSTSEQUENCE) FROM CLOSEDCASH WHERE USER_ = ?"// chande: USER -> USER_
                , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(app.getAppUserView().getUser().getId());
        if (seq != null) {
            if (seq[0] != null) {
                int t = Integer.parseInt(seq[0].toString());
                if (t < 0) {
                    p.m_iSeq = 1;
                } else {
                    p.m_iSeq = t + 1;
                }
            } else {
                p.m_iSeq = 1;
            }
        } else {
            p.m_iSeq = 1;
        }
        Object[] debt = (Object[]) new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL) FROM PEOPLE,PAYMENTS,RECEIPTS " +
                " WHERE PAYMENTS.PAYMENT='debt' AND PEOPLE.NAME=PAYMENTS.PUSER AND PAYMENTS.PUSER= ? AND PAYMENTS.PTIME<=? AND PAYMENTS.RECEIPT=RECEIPTS.ID AND RECEIPTS.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (debt == null) {
            p.totaldebt = new Double(0.0);
        } else {
            p.totaldebt = (Double) debt[0];
        }

        List<PaymentsModel.PaymentsLine1> l = new StaticSentence(app.getSession(), "SELECT PAYMENTS.PAYMENT, SUM(PAYMENTS.TOTAL) " +
                " FROM PAYMENTS,PEOPLE,RECEIPTS " +
                "WHERE  PAYMENTS.PAYMENT !='cashin' AND PAYMENTS.PUSER = ? AND PAYMENTS.PUSER=PEOPLE.NAME AND  PAYMENTS.PTIME <= ? AND PAYMENTS.RECEIPT=RECEIPTS.ID AND RECEIPTS.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL " +
                "GROUP BY PAYMENTS.PAYMENT", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.PaymentsLine1.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date}); //new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.DOUBLE}))
        // .list(app.getActiveCashIndex());
        System.out.println("gggg:::::::"+JPaymentSelect.gggg);
        p.chequeamt = 0.0;
        if (l == null) {
            p.m_lsales = new ArrayList();
        } else {
            p.m_lsales = l;
            for (PaymentsModel.PaymentsLine1 p1 : l) {
                if (p1.getpaymentType1().equals("cheque")) {
                    p.chequeamt = p1.getpaymentTotal1();
                    break;
                }
            }
        }
        p.ucreceipttotal = 0;
        List<UCReceiptLine> unclosedreceipt = new StaticSentence(app.getSession(),
                "SELECT DISTINCT R.ID,R.DATENEW , SUM(P.TOTAL) FROM RECEIPTS R,PAYMENTS P WHERE R.CLOSECASHSEQ IS NULL AND R.RUSER=? AND R.DATENEW >? AND P.RECEIPT=R.ID GROUP BY R.ID,R.DATENEW", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.UCReceiptLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (unclosedreceipt != null) {
            p.ucreceipts = unclosedreceipt;
            for (UCReceiptLine line : unclosedreceipt) {
                p.ucreceipttotal += line.getTotal().doubleValue();
            }
        } else {
            p.ucreceipts = new ArrayList<UCReceiptLine>();
        }
        /*
         *  "SELECT CNO,CBANK,TOTAL,SEARCHKEY,PTIME,ID FROM (SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,PAYMENTS.TOTAL AS TOTAL,CUSTOMERS.SEARCHKEY AS SEARCHKEY,PAYMENTS.PTIME AS PTIME,C.ID AS ID FROM PAYMENTS,CHEQUE C,CUSTOMERS  "+
        "WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND  PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque' AND C.HOLDER=?  "+
        "UNION  SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,PAYMENTS.TOTAL AS TOTAL,PAYMENTS.CUSTOMER AS SEARCHKEY,PAYMENTS.PTIME AS PTIME,C.ID AS ID FROM PAYMENTS,CHEQUE C,CUSTOMERS  "+
        "WHERE PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) AND  PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque' AND C.HOLDER=? ) ORDER BY 5"
         *
        " SELECT C.CHEQUENO,C.BANK,PAYMENTS.TOTAL,CUSTOMERS.NAME FROM PAYMENTS,PEOPLE,CHEQUE C,CUSTOMERS,RECEIPTS R "+
        "WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque' AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL "
         */
        List<ChequeDetailLine> chdetail = new StaticSentence(app.getSession(),
               // "SELECT CNO,CBANK,TOTAL,SEARCHKEY FROM (SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,C.AMOUNT AS TOTAL,CUSTOMERS.SEARCHKEY AS SEARCHKEY  FROM PAYMENTS,CHEQUE C,CUSTOMERS,PEOPLE,RECEIPTS R  " +
              //  "WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND  PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque'  AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL " +
              //  "UNION  SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,C.AMOUNT AS TOTAL,PAYMENTS.CUSTOMER AS SEARCHKEY FROM PAYMENTS,CHEQUE C,CUSTOMERS,PEOPLE,RECEIPTS R  " +
              //  "WHERE   PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque'  AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS)  AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL)as closecash ORDER BY 4", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.ChequeDetailLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date, app.getAppUserView().getUser().getName(), date});
       
                
                // query to make fast retrival     removed customers from 
                 "SELECT CNO,CBANK,TOTAL,SEARCHKEY FROM (SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,C.AMOUNT AS TOTAL,CUSTOMERS.SEARCHKEY AS SEARCHKEY  FROM PAYMENTS,CHEQUE C,CUSTOMERS,PEOPLE,RECEIPTS R  " +
                "WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND  PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque'  AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL " +
                "UNION all SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,C.AMOUNT AS TOTAL,PAYMENTS.CUSTOMER AS SEARCHKEY FROM PAYMENTS,CHEQUE C,PEOPLE,RECEIPTS R  " +
                "WHERE   PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque'  AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS)  AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL)as closecash ORDER BY 4", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.ChequeDetailLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date, app.getAppUserView().getUser().getName(), date});
        
        
        
        if (chdetail == null) {
            p.cdetail = new ArrayList<ChequeDetailLine>();
        } else {
            p.cdetail = chdetail;
        }
        
        
        
        
        
        
        //   p.receiptTotal=0.0;
        // PaymentsLine pl=mew PaymentsLine()
        int i = 0;
        Object[] obj5 = (Object[]) new StaticSentence(app.getSession(), "SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,PEOPLE,RECEIPTS R WHERE PAYMENTS.PAYMENT='cashin'AND PAYMENTS.PUSER = ? AND PAYMENTS.PUSER=PEOPLE.NAME AND  PTIME <=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (obj5 == null) {
            p.cashinsum = new Double(0.0);
        } else if (obj5[0] == null) {
            p.cashinsum = 0.0;
        } else {
            p.cashinsum = (Double) obj5[0];
        }

        
        // MODIFIED BY AAKASH
        p.MagCardReceiptNos = new ArrayList<Object>();
        p.MagCardReceiptNos  = (List<Object>) new StaticSentence(app.getSession(), "SELECT R.ID FROM PAYMENTS,PEOPLE,RECEIPTS R WHERE PAYMENTS.PAYMENT='magcard'AND PAYMENTS.PUSER = ? AND PAYMENTS.PUSER=PEOPLE.NAME AND  PTIME <=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL ",  new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP})  , SerializerReadString.INSTANCE).list(new Object[]{app.getAppUserView().getUser().getName(), date});        
                
        Object[] obj6 = (Object[]) new StaticSentence(app.getSession(), "SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,PEOPLE,RECEIPTS R WHERE PAYMENTS.PAYMENT='magcard'AND PAYMENTS.PUSER = ? AND PAYMENTS.PUSER=PEOPLE.NAME AND  PTIME <=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (obj6 == null) {
            p.MgCardTotal = new Double(0.0);
        } else if (obj6[0] == null) {
            p.MgCardTotal = 0.0;
        } else {
            Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "select sum(amount) from carddetails where receipt in (SELECT R.ID FROM PAYMENTS,PEOPLE,RECEIPTS R WHERE PAYMENTS.PAYMENT='magcard'AND PAYMENTS.PUSER = ? \n" +
                                        "AND PAYMENTS.PUSER=PEOPLE.NAME AND  PTIME <=?  AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL\n" +
                                        "AND PEOPLE.CLOSECASHTIME IS NULL) and  paymentflag=1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
            Double Paymentlass = 0.00;
            if(obj1!=null){
                if(obj1[0]!=null){
                   Paymentlass=Double.parseDouble(obj1[0].toString()); 
                }
            }
            
            p.MgCardTotal = (((Double) obj6[0]) - Paymentlass);
            
        }
        
        /////////////pratima :to add card details in close cash
         List<CardDetailLine> carddetail1 = new StaticSentence(app.getSession()," SELECT TNO,BANK,TOTAL,SEARCHKEY FROM (SELECT C.transactionid AS TNO,bd.name AS BANK,(C.AMOUNT+C.OTHERCHARGES) AS TOTAL,CUSTOMERS.SEARCHKEY AS SEARCHKEY  FROM PAYMENTS,carddetails C,CUSTOMERS,PEOPLE,RECEIPTS R,bank_details bd\n" +
"                WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND  PAYMENTS.RECEIPT=C.Receipt AND PAYMENTS.PAYMENT ='magcard' AND PAYMENTS.PUSER=?  AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL and c.bankid=bd.id\n" +
"                UNION all SELECT C.transactionid AS TNO,bd.name AS BANK,(C.AMOUNT+C.OTHERCHARGES) AS TOTAL,PAYMENTS.CUSTOMER AS SEARCHKEY FROM PAYMENTS,carddetails C,PEOPLE,RECEIPTS R,bank_details bd\n" +
"                WHERE   PAYMENTS.RECEIPT=C.Receipt AND PAYMENTS.PAYMENT ='magcard'  AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME<=? AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS)  AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL and c.bankid=bd.id)as closecash ORDER BY 4"
       
        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.CardDetailLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date, app.getAppUserView().getUser().getName(), date});
        
        
        
        if (chdetail == null) {
            p.carddetail = new ArrayList<CardDetailLine>();
        } else {
            p.carddetail = carddetail1;
        }
        //////////////////////////////////////////////////////
        
        
        p.receiptTotalsum = 0.0;
        //warehouse changes - start
        List<WarehouseTotals> ct = new StaticSentence(app.getSession(),
                "SELECT SUM(BILL.AMOUNT + BILL.TAXTOTAL),L.NAME,L.CUSTOMERCURRENTACCOUNT " +
                " FROM RECEIPTS,BILL,PEOPLE P,PEOPLE P1,LOCATIONS L " +
                " WHERE BILL.RECEIPT=RECEIPTS.ID AND BILL.CREATEDBY=P.NAME AND BILL.WAREHOUSE=L.ID   AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME AND  RECEIPTS.DATENEW <= ? AND RECEIPTS.CLOSECASHSEQ IS NULL AND P1.CLOSECASHTIME IS NULL  " +
                " GROUP BY BILL.WAREHOUSE",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.WarehouseTotals.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (ct == null) {
            p.clist = new ArrayList<WarehouseTotals>();
        //  p.receiptTotal=new Double[]{0.0};
        } else {

            p.clist = ct;
            for (int j = 0; j < p.clist.size(); j++) {
                p.receiptTotalsum += p.clist.get(j).getamount();
            }
        }
        p.receiptTotalsum1 = 0.0;
        List<WarehouseTotals> ct1 = new StaticSentence(app.getSession(),
                "SELECT SUM(BILL.AMOUNT + BILL.TAXTOTAL),L.NAME,L.CUSTOMERCURRENTACCOUNT " +
                " FROM RECEIPTS,BILL,PEOPLE P,PEOPLE P1,PAYMENTS,LOCATIONS L" +
                " WHERE BILL.RECEIPT=RECEIPTS.ID AND BILL.CREATEDBY=P.NAME AND BILL.WAREHOUSE=L.ID  AND PAYMENTS.RECEIPT=RECEIPTS.ID AND PAYMENTS.PAYMENT='debt'AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME AND  RECEIPTS.DATENEW <= ? AND RECEIPTS.CLOSECASHSEQ IS NULL AND P1.CLOSECASHTIME IS NULL  " +
                " GROUP BY BILL.WAREHOUSE",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.WarehouseTotals.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (ct1 == null) {
            p.clist1 = new ArrayList<WarehouseTotals>();
        //  p.receiptTotal=new Double[]{0.0};
        } else {

            p.clist1 = ct1;
            for (int j = 0; j < p.clist1.size(); j++) {
                p.receiptTotalsum1 += p.clist1.get(j).getamount();
            }
        }
        //warehouse changes - end
        //to refund entries
       /* List<CounterTotals> ct1= new StaticSentence(app.getSession(),
        "SELECT SUM(BILL.AMOUNT + BILL.TAXTOTAL),ROLES.NAME"+
        " FROM RECEIPTS,BILL,PEOPLE P,PEOPLE P1,ROLES "+
        " WHERE BILL.AMOUNT<0 AND BILL.RECEIPT=RECEIPTS.ID AND BILL.CREATEDBY=P.NAME AND P.ROLE=ROLES.ID  AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME AND  RECEIPTS.DATENEW <= ? AND RECEIPTS.CLOSECASHSEQ IS NULL AND P1.CLOSECASHTIME IS NULL  " +
        " GROUP BY ROLES.NAME",
        new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP})
        , new SerializerReadClass(PaymentsModel.CounterTotals.class)).list(new Object[]{app.getAppUserView().getUser().getName(),date});*/
        // Sales
     /*   Object[] recsales = (Object []) new StaticSentence(app.getSession(),
        "SELECT COUNT(DISTINCT RECEIPTS.ID), SUM(TICKETLINES.UNITS * TICKETLINES.PRICE) " +
        "FROM RECEIPTS, TICKETLINES WHERE RECEIPTS.ID = TICKETLINES.TICKET AND RECEIPTS.MONEY = ?",
        SerializerWriteString.INSTANCE,
        new SerializerReadBasic(new Datas[] {Datas.INT, Datas.DOUBLE}))
        .find(app.getActiveCashIndex());
        if (recsales == null) {
        p.m_iSales = null;
        p.m_dSalesBase = null;
        } else {
        p.m_iSales = (Integer) recsales[0];
        p.m_dSalesBase = (Double) recsales[1];
        }             
        
        // Taxes
        Object[] rectaxes = (Object []) new StaticSentence(app.getSession(),
        "SELECT SUM(TAXLINES.AMOUNT) " +
        "FROM RECEIPTS, TAXLINES WHERE RECEIPTS.ID = TAXLINES.RECEIPT AND RECEIPTS.MONEY = ?"
        , SerializerWriteString.INSTANCE
        , new SerializerReadBasic(new Datas[] {Datas.DOUBLE}))
        .find(app.getActiveCashIndex());
        if (rectaxes == null) {
        p.m_dSalesTaxes = null;
        } else {
        p.m_dSalesTaxes = (Double) rectaxes[0];
        } */

        List<PaymentsLine2> asales = new StaticSentence(app.getSession(),
                " SELECT PAYMENTS.RECEIPT,PAYMENTS.PAYMENT AS PYMT,(PAYMENTS.TOTAL * -1) AS PAYTOTAL FROM PAYMENTS,PEOPLE,RECEIPTS " +
                "WHERE PAYMENTS.TOTAL < 0 AND PAYMENTS.PAYMENT != 'debtpaid' AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND RECEIPTS.ID=PAYMENTS.RECEIPT  AND RECEIPTS.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL ", SerializerWriteString.INSTANCE, new SerializerReadClass(PaymentsModel.PaymentsLine2.class)).list(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
        if (asales == null) {
            p.m_lpayments = new ArrayList<PaymentsLine2>();
        } else {
            p.m_lpayments = asales;
        }

        p.payTotal = 0.0;
        Object[] obj2 = (Object[]) new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL * -1)    FROM PAYMENTS,PEOPLE,RECEIPTS R " +
                " WHERE PAYMENTS.TOTAL < 0 AND PAYMENTS.PAYMENT != 'debtpaid' AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND PTIME <= ? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL  AND PEOPLE.CLOSECASHTIME IS NULL",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (obj2 == null) {
            p.payTotal = new Double(0.0);

        } else if (obj2[0] == null) {
            p.payTotal = 0.0;
        } else {
            p.payTotal = (Double) obj2[0];
        }

        Object[] obj3 = (Object[]) new StaticSentence(app.getSession(),
                "  SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,CUSTOMERS,BILL,PEOPLE,RECEIPTS R" +
                "  WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER=? AND PAYMENTS.PAYMENT!='cashin' AND  PAYMENTS.RECEIPT = BILL.RECEIPT AND  PTIME<=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL" +
                "  GROUP BY PAYMENTS.PTIME,PAYMENTS.RECEIPT,CUSTOMERS.NAME   ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
//debt collected total
        Object[] obj4 = (Object[]) new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL * -1)    FROM PAYMENTS,PEOPLE,RECEIPTS R " +
                " WHERE PAYMENTS.TOTAL < 0 AND PAYMENTS.PAYMENT = 'debtpaid' AND PAYMENTS.PUSER=? AND PAYMENTS.PUSER = PEOPLE.NAME AND  PTIME<=? AND PAYMENTS.RECEIPT=R.ID AND R.CLOSECASHSEQ IS NULL AND PEOPLE.CLOSECASHTIME IS NULL ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (obj3 == null) {
            p.dtotal = new Double(0.0);
        } else {
            p.dtotal = (Double) obj3[0];
        }

        if (obj4 == null) {
            p.dtotal += new Double(0.0);
        } else {
            if (obj4[0] != null) {
                p.dtotal += (Double) obj4[0];
            }
        }

        List<RdetailsLine> rdetail = new StaticSentence(app.getSession(),
                " SELECT RECEIPTS.DATENEW,RECEIPTS.ID,BILL.ID,BILL.CUSTOMER,SUM(BILL.AMOUNT + BILL.TAXTOTAL),L.NAME " +
                " FROM RECEIPTS,BILL,PEOPLE P1,LOCATIONS L " +
                " WHERE BILL.RECEIPT=RECEIPTS.ID  AND BILL.WAREHOUSE=L.ID  AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME AND  RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL AND P1.CLOSECASHTIME IS NULL  " +
                " GROUP BY RECEIPTS.ID,RECEIPTS.DATENEW,BILL.CUSTOMER,L.NAME,BILL.ID ORDER BY L.NAME,RECEIPTS.ID", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.RdetailsLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (rdetail == null) {
            p.m_lrdetails = new ArrayList<RdetailsLine>();
        } else {
            p.m_lrdetails = rdetail;
        }

        List<RdetailsLine1> rdetail2 = new StaticSentence(app.getSession(),
                " SELECT RECEIPTS.DATENEW,RECEIPTS.ID,BILL.ID,CUSTOMERS.SEARCHKEY,SUM(PAYMENTS.TOTAL),L.NAME,CUSTOMERS.ACCOUNT,CUSTOMERS.ID,BILL.CREATEDDATE,L.FACILITY " +
                " FROM RECEIPTS,BILL,CUSTOMERS,PEOPLE P1,PAYMENTS,LOCATIONS L " +
                " WHERE BILL.RECEIPT=RECEIPTS.ID AND CUSTOMERS.ID=BILL.CUSTOMER AND PAYMENTS.RECEIPT=RECEIPTS.ID AND PAYMENTS.PAYMENT='debt' AND BILL.WAREHOUSE=L.ID   AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME AND  RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL AND P1.CLOSECASHTIME IS NULL AND BILL.WAREHOUSE=L.ID  " +
                " GROUP BY RECEIPTS.ID,RECEIPTS.DATENEW,BILL.CUSTOMER,L" +
                ".NAME,CUSTOMERS.ACCOUNT,CUSTOMERS.SEARCHKEY,BILL.CREATEDDATE,BILL.ID,CUSTOMERS.ID ORDER BY L.NAME,RECEIPTS.ID ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.RdetailsLine1.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (rdetail2 == null) {
            p.m_lrdetails1 = new ArrayList<RdetailsLine1>();
        } else {
            p.m_lrdetails1 = rdetail2;
        }

        List<RdetailsLine2> ddetail = new StaticSentence(app.getSession(),
                " SELECT RECEIPTS.DATENEW,RECEIPTS.ID,BILL.ID,CUSTOMERS.SEARCHKEY,SUM(BILL.AMOUNT + BILL.TAXTOTAL),L.NAME " +
                " FROM RECEIPTS,BILL,PEOPLE P1,LOCATIONS L,PAYMENTS,CUSTOMERS" +
                " WHERE BILL.RECEIPT=RECEIPTS.ID AND CUSTOMERS.ID=BILL.CUSTOMER  AND PAYMENTS.RECEIPT=RECEIPTS.ID AND PAYMENTS.PAYMENT='debt' AND BILL.WAREHOUSE=L.ID  AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME AND  RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL AND P1.CLOSECASHTIME IS NULL  " +
                " GROUP BY RECEIPTS.ID,RECEIPTS.DATENEW,CUSTOMERS.SEARCHKEY,L.NAME,BILL.ID ORDER BY L.NAME,RECEIPTS.ID", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.RdetailsLine2.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (ddetail == null) {
            p.m_lrdetails2 = new ArrayList<RdetailsLine2>();
        } else {
            p.m_lrdetails2 = ddetail;
        }
        /*List<OtherIncome> rdetail3=new StaticSentence(app.getSession(),
        " SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,CUSTOMERS.NAME,PAYMENTS.TOTAL,RECEIPTS.DESC_,(SELECT NAME FROM GAMES WHERE (SELECT PARENT  FROM GAMES,RECEIPTS WHERE GAMES.NAME=RECEIPTS.DESC_)=GAMES.ID)AS GAMETYPE,CUSTOMERS.ACCOUNT,CUSTOMERS.SEARCHKEY,RECEIPTS.PAYMENTREF FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE"+
        " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT AND PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL  "+
        " UNION ALL SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,PAYMENTS.CUSTOMER,PAYMENTS.TOTAL,RECEIPTS.DESC_,(SELECT NAME FROM GAMES WHERE (SELECT PARENT  FROM GAMES,RECEIPTS WHERE GAMES.NAME=RECEIPTS.DESC_)=GAMES.ID)AS GAMETYPE,RECEIPTS.PAYMENTREF,PAYMENTS.CUSTOMER,RECEIPTS.PAYMENTREF FROM PAYMENTS,RECEIPTS,PEOPLE"+
        " WHERE  PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT AND    PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL  AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL   ORDER BY PAYMENTS.RECEIPT"
        ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP})
        ,new SerializerReadClass(PaymentsModel.OtherIncome.class))
        .list(new Object[]{app.getAppUserView().getUser().getName(),date,app.getAppUserView().getUser().getName(),date});*/
        List<OtherIncome> rdetail3 = new StaticSentence(app.getSession(),
                " SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,CUSTOMERS.NAME,PAYMENTS.TOTAL,CUSTOMERS.ACCOUNT,CUSTOMERS.SEARCHKEY,RECEIPTS.PAYMENTREF FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE" +
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT AND    PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL AND RECEIPTS.DATENEW <=? AND RECEIPTS.PAYMENTREF != 'Guest Entry' AND RECEIPTS.CLOSECASHSEQ IS NULL  " +
                " UNION ALL SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,PAYMENTS.CUSTOMER,PAYMENTS.TOTAL,RECEIPTS.PAYMENTREF,PAYMENTS.CUSTOMER,RECEIPTS.PAYMENTREF FROM PAYMENTS,RECEIPTS,PEOPLE" +
                " WHERE  PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT AND    PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL  AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) AND RECEIPTS.DATENEW <=?  AND RECEIPTS.PAYMENTREF != 'Guest Entry' AND RECEIPTS.CLOSECASHSEQ IS NULL   ORDER BY 2" //praveen:Unknown column 'PAYMENTS.RECEIPT' in 'order clause':changed order by payments.receipt to order by 2
                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.OtherIncome.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date, app.getAppUserView().getUser().getName(), date});
        if (rdetail3 == null) {
            p.otherIncomedetails = new ArrayList<OtherIncome>();
        } else {
            p.otherIncomedetails = rdetail3;
        }
        
        
        
        List<OtherIncomeTotalLine> oitotal = new StaticSentence(app.getSession(),
//                " SELECT PAYMENTS.TOTAL,RECEIPTS.PAYMENTREF,RECEIPTS.DESC_ FROM PAYMENTS,RECEIPTS,PEOPLE " +
//                " WHERE  PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =?  AND RECEIPTS.ID=PAYMENTS.RECEIPT   AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL AND RECEIPTS.PAYMENTREF != 'Cards Room' AND RECEIPTS.PAYMENTREF != 'Guest Entry' AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL   ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(OtherIncomeTotalLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});
 //by pratima above query is modified to get receipt also
                " SELECT PAYMENTS.TOTAL,RECEIPTS.PAYMENTREF,RECEIPTS.DESC_,PAYMENTS.RECEIPT FROM PAYMENTS,RECEIPTS,PEOPLE " +
                " WHERE  PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =?  AND RECEIPTS.ID=PAYMENTS.RECEIPT   AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NOT NULL AND RECEIPTS.PAYMENTREF != 'Cards Room' AND RECEIPTS.PAYMENTREF != 'Guest Entry' AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL   ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(OtherIncomeTotalLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (oitotal == null) {
            p.m_lotherincometotals = new ArrayList<OtherIncomeTotalLine>();
        } else {
            p.m_lotherincometotals = oitotal;
        }
        p.otherIncomeTotal = 0.0;
        for (OtherIncomeTotalLine o : p.m_lotherincometotals) {
            p.otherIncomeTotal += o.getPtotal();
        }
        
        
        
        
//       Object[] guest = (Object[]) new StaticSentence(app.getSession(),
//                " SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE " +
//                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =?  AND RECEIPTS.ID=PAYMENTS.RECEIPT  AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF='Guest Entry' AND PAYMENTS.RECEIPT NOT IN (SELECT BILL.RECEIPT FROM BILL where bill.receipt is not null) AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL   ",
//                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
//
//        if (guest == null) {
//            p.guestfee = new Double(0.0);
//        } else if (guest[0] == null) {
//            p.guestfee = new Double(0.0);
//        } else {
//            p.guestfee = (Double) guest[0];
//        }
        List<GuestReceiptdetailsLine> guest = (ArrayList<GuestReceiptdetailsLine>) new StaticSentence(app.getSession(),
                    " SELECT CUSTOMERS.SEARCHKEY,GUESTCAT.NAME,GUESTCAT.ACCOUNT,SUM(PAYMENTS.TOTAL),RECEIPTS.ID,\n" +
                    "RECEIPTS.DATENEW , GUESTLOG.TAXCAT , IFNULL(SUM(TAXAMOUNT),0.00)\n" +
                    "FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE,GUESTCAT,GUESTLOG\n" +
                    "WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND\n" +
                    "PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =? AND RECEIPTS.DESC_= GUESTCAT.ID AND \n" +
                    "RECEIPTS.ID=PAYMENTS.RECEIPT  AND  PEOPLE.CLOSECASHTIME IS NULL AND \n" +
                    "RECEIPTS.PAYMENTREF='Guest Entry' AND PAYMENTS.RECEIPT NOT IN \n" +
                    "(SELECT BILL.RECEIPT FROM BILL WHERE BILL.RECEIPT IS NOT NULL) AND PAYMENTS.PAYMENT!='debt' \n" +
                    "AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL \n" +
                    "AND GUESTLOG.RECEIPTNO=RECEIPTS.ID\n" +
                    "GROUP BY GUESTLOG.GUESTCAT,GUESTCAT.NAME,GUESTCAT.ACCOUNT ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(GuestReceiptdetailsLine.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (guest == null || guest.size() <= 0) {
            p.guestreceiptDetails = new ArrayList<GuestReceiptdetailsLine>();
        } else {
            p.guestreceiptDetails = guest;
        }
        p.guestfee = 0.0;
        for (GuestReceiptdetailsLine line : p.guestreceiptDetails) {
            p.guestfee += line.getAmount();
        }

        List<GuestReceiptdetailsLine1> guest1 = (ArrayList<GuestReceiptdetailsLine1>) new StaticSentence(app.getSession(),
                " SELECT CUSTOMERS.SEARCHKEY,GUESTCAT.NAME,GUESTCAT.ACCOUNT,SUM(PAYMENTS.TOTAL),RECEIPTS.ID,RECEIPTS.DATENEW FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE,GUESTCAT " +
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =? AND RECEIPTS.DESC_= GUESTCAT.ID AND RECEIPTS.ID=PAYMENTS.RECEIPT  AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF='Guest Entry' AND PAYMENTS.RECEIPT NOT IN (SELECT BILL.RECEIPT FROM BILL WHERE BILL.RECEIPT IS NOT NULL) AND PAYMENTS.PAYMENT!='debt' AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL GROUP BY RECEIPTS.ID ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(GuestReceiptdetailsLine1.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (guest1 == null || guest1.size() <= 0) {
            p.guestreceiptDetails1 = new ArrayList<GuestReceiptdetailsLine1>();
        } else {
            p.guestreceiptDetails1 = guest1;
        }

        List<GuestReceiptdetailsLine1> guest2 = (ArrayList<GuestReceiptdetailsLine1>) new StaticSentence(app.getSession(),
                " SELECT CUSTOMERS.SEARCHKEY,GUESTCAT.NAME,GUESTCAT.ACCOUNT,SUM(PAYMENTS.TOTAL),RECEIPTS.ID,RECEIPTS.DATENEW FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE,GUESTCAT " +
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =? AND RECEIPTS.DESC_= GUESTCAT.ID AND RECEIPTS.ID=PAYMENTS.RECEIPT  AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF='Guest Entry' AND PAYMENTS.RECEIPT NOT IN (SELECT BILL.RECEIPT FROM BILL WHERE BILL.RECEIPT IS NOT NULL) AND PAYMENTS.PAYMENT!='debt' AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL GROUP BY RECEIPTS.ID ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(GuestReceiptdetailsLine1.class)).list(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (guest2 == null || guest2.size() <= 0) {
            p.guestreceiptDetails2 = new ArrayList<GuestReceiptdetailsLine1>();
        } else {
            p.guestreceiptDetails2 = guest2;
        }
        
        Object[] cardsroomIncome = (Object[]) new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE " +
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME =?  AND RECEIPTS.ID=PAYMENTS.RECEIPT  AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF='Cards Room' AND PAYMENTS.RECEIPT NOT IN (SELECT BILL.RECEIPT FROM BILL where bill.receipt is not null) AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL   ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (cardsroomIncome == null) {
            p.cardsroomIncome = new Double(0.0);
        } else if (cardsroomIncome[0] == null) {
            p.cardsroomIncome = new Double(0.0);
        } else {
            p.cardsroomIncome = (Double) cardsroomIncome[0];
        }
        p.otherIncomeTotal += p.guestfee + p.cardsroomIncome;
        List<Debtcollected> rdetail1 = new StaticSentence(app.getSession(),
                " SELECT PAYMENTS.PTIME,PAYMENTS.RECEIPT,CUSTOMERS.SEARCHKEY,PAYMENTS.TOTAL,CUSTOMERS.ACCOUNT,CUSTOMERS.ID FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE" +
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT   AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NULL AND PAYMENTS.RECEIPT NOT IN (SELECT BILL.RECEIPT FROM BILL where bill.receipt is not null) AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL ORDER BY RECEIPTS.ID  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(PaymentsModel.Debtcollected.class)).list(new Object[]{LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), date});
        if (rdetail1 == null) {
            p.drdetails = new ArrayList<Debtcollected>();
        } else {
            p.drdetails = rdetail1;
        }

        Object[] debtc = (Object[]) new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,CUSTOMERS,RECEIPTS,PEOPLE" +
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND RECEIPTS.ID=PAYMENTS.RECEIPT   AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NULL AND PAYMENTS.RECEIPT NOT IN (SELECT BILL.RECEIPT FROM BILL where bill.receipt is not null) AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL  ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (debtc == null || debtc[0] == null) {
            p.debtcollection = new Double(0.0);
        } else {
            p.debtcollection = (Double) debtc[0];
        }
        p.ochequetotal = p.chequeamt;
        Object[] debt_cheque = (Object[]) new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,RECEIPTS,PEOPLE" +
                " WHERE   RECEIPTS.CLOSECASHSEQ IS NULL AND RECEIPTS.ID=PAYMENTS.RECEIPT AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PAYMENTS.PAYMENT='cheque'  AND  PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NULL AND RECEIPTS.ID NOT IN (SELECT BILL.RECEIPT FROM BILL where bill.receipt is not null)  AND RECEIPTS.DATENEW <=?    ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});
        if (debt_cheque != null) {
            if (debt_cheque[0] != null) {
                p.debtcollectedchequetotal = Double.parseDouble(debt_cheque[0].toString());
                p.ochequetotal -= p.debtcollectedchequetotal;
            }
        }
        List<Object[]> tokenStatusWise = new StaticSentence(app.getSession(),
                " SELECT TYPE_,SUM(TOTALVALUE) FROM TOKENLOG WHERE CLOSECASHREF IS NULL GROUP BY TYPE_" //" WHERE   RECEIPTS.CLOSECASHSEQ IS NULL AND RECEIPTS.ID=PAYMENTS.RECEIPT AND PAYMENTS.TOTAL>0 AND PAYMENTS.PUSER = PEOPLE.NAME AND PAYMENTS.PAYMENT='cheque'  AND  PEOPLE.NAME=RECEIPTS.RUSER AND PEOPLE.NAME= ?  AND  PEOPLE.CLOSECASHTIME IS NULL AND RECEIPTS.PAYMENTREF IS NULL AND RECEIPTS.ID NOT IN (SELECT BILL.RECEIPT FROM BILL)  AND RECEIPTS.DATENEW <=?    "
                , null, new SerializerReadBasic(new Datas[]{Datas.INT, Datas.DOUBLE})).list();
        for (Object[] obj1 : tokenStatusWise) {
            if (String.valueOf(obj1[0]).equals("0")) {
                if (obj1[1] != null) {
                    double amt = Double.valueOf(String.valueOf(obj1[1]));
                    p.issuedTokenAmount += amt;
                }
            } else if (String.valueOf(obj1[0]).equals("1")) {
                if (obj1[1] != null) {
                    double amt = Double.valueOf(String.valueOf(obj1[1]));
                    p.receivedTokenAmount += amt;
                }
            }
        }
        Object[] debtr = (Object[]) new StaticSentence(app.getSession(),
                "SELECT SUM(PAYMENTS.TOTAL) " +
                " FROM RECEIPTS,CUSTOMERS,BILL,PEOPLE P,PEOPLE P1,ROLES,PAYMENTS " +
                " WHERE BILL.RECEIPT=RECEIPTS.ID AND PAYMENTS.RECEIPT=RECEIPTS.ID AND PAYMENTS.PAYMENT='debt' AND BILL.CREATEDBY=P.NAME AND P.ROLE=ROLES.ID AND BILL.CUSTOMER=CUSTOMERS.ID AND RECEIPTS.RUSER = ? AND RECEIPTS.RUSER=P1.NAME  AND P1.CLOSECASHTIME IS NULL AND RECEIPTS.DATENEW <=? AND RECEIPTS.CLOSECASHSEQ IS NULL ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getName(), date});

        if (debtr == null || debtr[0] == null) {
            p.debtraised = new Double(0.0);
        } else {
            p.debtraised = (Double) debtr[0];
        }

        String warehouse = null;
        String[] warehouses = null;

        Object obj = app.getAppUserView().getUser().getWarehouse();
        if (obj != null) {
            warehouses = obj.toString().split("#");
            warehouse = warehouses[0];
        }
        if (warehouses != null) {
            Object[] params = new Object[warehouses.length];
            Datas[] data = new Datas[warehouses.length];
            StringBuffer condition = new StringBuffer("");
            for (int j = 0; j < warehouses.length; j++) {
                data[j] = Datas.STRING;
                params[j] = warehouses[j].toString();
                condition.append(" ? , ");
            }
            if (condition.length() > 0) {
                condition.deleteCharAt(condition.lastIndexOf(","));
            }
            List<CreditConfirmList> crconf = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse in  (" + condition.toString() + ") group by d.id ORDER BY D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
            if (crconf == null) {
                p.crlist = new ArrayList<CreditConfirmList>();
            } else {
                p.crlist = crconf;
            }
            p.creditconftotal = 0;
            for (CreditConfirmList c : crconf) {
                p.creditconftotal += c.getAmount();
            }
            List<BillInfo> cash = new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME,BILL.AMOUNT , BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS,WAITER WHERE  BILL.FLOOR=FLOORS.ID AND BILL.PAID=FALSE AND bill.warehouse in  (" + condition.toString() + ") group by bill.id ORDER BY BILL.CREATEDDATE,BILL.ID,BILL.CREATEDBY", new SerializerWriteBasic(data), new SerializerReadClass(BillInfo.class)).list(params);
            if (cash != null) {
                p.cashPend = cash;
            } else {
                p.cashPend = new ArrayList<BillInfo>();
            }
        }


        p.balance = p.receiptTotalsum + p.cashinsum + p.debtcollection - p.debtraised + p.otherIncomeTotal;
        return p;
    }

    public String printUser() {
        return LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
    }

    public String printRole() {
        String x = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        return LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString();
    }

    public double getIssuedTokenamount() {
        return issuedTokenAmount;
    }

    public double getReceivedTokenAmount() {
        return receivedTokenAmount;
    }

    public List<OtherIncome> getOtherIncomeList() {
        return otherIncomedetails;
    }

    public List<UCReceiptLine> getUNClosedReceipts() {
        return ucreceipts;
    }

    public List<GuestReceiptdetailsLine> getGuestReceiptDetails() {
        return guestreceiptDetails;
    }

    public List<GuestReceiptdetailsLine1> getGuestreceiptDetails1() {
        return guestreceiptDetails1;
    }

    public List<GuestReceiptdetailsLine1> getGuestreceiptDetails2() {
        return guestreceiptDetails2;
    }

    public List<CreditConfirmList> getCreditConfList() {
        return crlist;
    }

    public List<BillInfo> getPendeingCashBills() {
        return cashPend;
    }

    public double getCreditConfTotal() {
        return creditconftotal;
    }

    public double getCardsRoomIncome() {
        return cardsroomIncome;
    }

    public String printCreditConfTotal() {
        return Formats.ConvertDoubleToString(creditconftotal);
    }

    public double getUNClosedReceiptsTotal() {
        return ucreceipttotal;
    }

    public String getotherIncomeTotal() {
        return roundTwoDecimals(otherIncomeTotal);
    }

    public Double getotherIncomeTotal1() {
        return otherIncomeTotal;
    }

    public double getOChequeAmount() {
        return ochequetotal;
    }
    
    public double getMagCardAmount() {
        return MgCardTotal;
    }

    public List<Object> getMagCardReceiptList(){
        return MagCardReceiptNos;
    }
    
    public Double getGuestFee() {
        return guestfee;
    }

    public String getGuestFee1() {
        return roundTwoDecimals(guestfee);
    }

    public Double getChequeAmount() {
        return chequeamt;
    }

    public String getChequeAmount1() {
        return roundTwoDecimals(chequeamt);
    }

    public Double getReceipttotalsum() {
        return receiptTotalsum.doubleValue();
    }

    public Double getReceipttotalsum1() {
        return receiptTotalsum1.doubleValue();
    }

    public String printReceiptTotal(int k) {
        return roundTwoDecimals(clist.get(k).getamount());
    }

    public Double getReceiptTotal(int k) {
        return clist.get(k).getamount();
    }

    public String printReceiptCounter(int k) {
        return clist.get(k).printName();
    }

    public int getcountercount() {
        return clist.size();
    }

    public String printReceiptTotal1(int k) {
        return roundTwoDecimals(clist1.get(k).getamount());
    }

    public Double getReceiptTotal1(int k) {
        return clist1.get(k).getamount();
    }

    public List<WarehouseTotals> getWareHouseTotals() {
        return clist;
    }

    public String printReceiptCounter1(int k) {
        return clist1.get(k).printName();
    }

    public int getcountercount1() {
        return clist1.size();
    }

    public Double getdtotal() {
       // double value = Double.parseDouble(dtotal);
     //  return dtotal;
        return Double.parseDouble(roundTwoDecimals(dtotal));
    }

    public String printdtotal() {
        return roundTwoDecimals(dtotal);
    }

    public String printHost() {
        return m_sHost;
    }

    public Double getpayTotal() {
        return payTotal.doubleValue();
    }

    public double getbalance() {
        return balance;
    }

    public String printbalance() {
        return roundTwoDecimals(balance);
    }

    public String printpayTotal() {
        return roundTwoDecimals(payTotal);
    }
    // public int getPayments() {
    //     return m_iPayments.intValue();
    //  }

    public double getTotal() {
        return totaldebt.doubleValue();
    }

    public Double getHost() {
        return totaldebt.doubleValue();
    }

    public int getSequence() {
        return m_iSeq;
    }
    //   public Date getDateStart() {
    //       return m_dDateStart;
    //   }

    public void setDateEnd(Date dValue) {
        m_dDateEnd = dValue;
    }

    public Date getDateEnd() {
        return m_dDateEnd;
    }

    public Double getDebtRaised() {
        return debtraised;
    }

    public String printdebtrTotal() {
        return roundTwoDecimals(debtraised);
    }

    public String printdebtcTotal() {
        return roundTwoDecimals(debtcollection);
    }

    public double getdebtcTotal() {
        return debtcollection;
    }

    public String printreceiptTotalsum() {
        return roundTwoDecimals(receiptTotalsum);
    }

    public String printreceiptTotalsum1() {
        return roundTwoDecimals(receiptTotalsum1);
    }
    /*  public String printHost() {
    return Formats.DOUBLE.formatValue(totaldebt);
    }*/

    public String printSequence() {
        return Formats.INT.formatValue(m_iSeq);
    }
    //  public String printDateStart() {
    //      return Formats.TIMESTAMP.formatValue(m_dDateStart);
    //   }

    public String printDateEnd() {
        m_dDateEnd = new Date();
        return Formats.TIMESTAMP.formatValue(m_dDateEnd);
    }

    //  public String printPayments() {
    //      return Formats.INT.formatValue(m_iPayments);
    //   }
    public String printPaymentsTotal() {
        return roundTwoDecimals(totaldebt);
    }

    public List<OtherIncomeTotalLine> getOtherIncomeTotalLines() {
        return m_lotherincometotals;
    }

    public List<ChequeDetailLine> getChequeDetailLines() {
        return cdetail;
    }
    public List<CardDetailLine> getCardDetailLines() {
        return carddetail;
    }
    public List<PaymentsLine1> getPaymentLines() {
        return m_lsales;
    }
    //warehouse changes - start

    public List<WarehouseTotals> getCounterlines() {
        return clist;
    }

    public List<WarehouseTotals> getCounterlines1() {
        return clist1;
    }
    //warehouse changes - end

    public List<RdetailsLine> getRdetalLines() {
        return m_lrdetails;
    }

    public List<RdetailsLine1> getRdetalLines1() {
        return m_lrdetails1;
    }

    public List<RdetailsLine2> getRdetalLines2() {
        return m_lrdetails2;
    }

    public List<Debtcollected> getDebtCollected() {
        return drdetails;
    }

    public int getSales() {
        return m_iSales == null ? 0 : m_iSales.intValue();
    }

    public double getincash() {
        return cashinsum;
    }

    public String printincash() {
        return roundTwoDecimals(cashinsum);
    }

    public String printSales() {
        return Formats.INT.formatValue(m_iSales);
    }
    /*   public String printSalesBase() {
    return Formats.CURRENCY.formatValue(m_dSalesBase);
    }     
    public String printSalesTaxes() {
    return Formats.CURRENCY.formatValue(m_dSalesTaxes);
    }     */
    /*   public String printSalesTotal() {
    return Formats.CURRENCY.formatValue((m_dSalesBase == null || m_dSalesTaxes == null)
    ? null
    : m_dSalesBase + m_dSalesTaxes);
    } */

    public List<PaymentsLine1> getPaymentsLines1() {
        return m_lsales;
    }

    public List<PaymentsLine2> getPaymentsLines2() {
        return m_lpayments;
    }

    public AbstractTableModel getPaymentsModel2() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(PAYMENTHEADERS2[column]);
            }

            public int getRowCount() {
                return m_lpayments.size();
            }

            public int getColumnCount() {
                return PAYMENTHEADERS2.length;
            }

            public Object getValueAt(int row, int column) {
                PaymentsLine2 l = m_lpayments.get(row);
                switch (column) {
                    case 0:
                        return l.getReceipt();
                    case 1:
                        return l.getpaymentType1();
                    case 2:
                        return l.getpaymentTotal1();
                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel UCReceiptTableModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS[column]);
            }

            public int getRowCount() {
                return ucreceipts.size();
            }

            public int getColumnCount() {
                return HEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                UCReceiptLine l = ucreceipts.get(row);
                switch (column) {
                    case 0:
                        return l.getReceipt();
                    case 1:
                        return l.getDate();
                    case 2:
                        return l.getTotal();
                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel PendingCrConfTableModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS1[column]);
            }

            public int getRowCount() {
                return crlist.size();
            }

            public int getColumnCount() {
                return HEADERS1.length;
            }

            public Object getValueAt(int row, int column) {
                CreditConfirmList l = crlist.get(row);
                switch (column) {
                    case 0:
                        return Formats.DATE.formatValue(l.getDate());
                    case 1:
                        return l.getSearchkey();
                    case 2:
                        return l.getBillref();
                    case 3:
                        return roundTwoDecimals(l.getAmount());
                    default:
                        return null;
                }
            }
        };
    }
    //warehouse changes - start

    public AbstractTableModel getCounterModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(COUNTERHEADERS[column]);
            }

            public int getRowCount() {
                return clist.size();
            }

            public int getColumnCount() {
                return COUNTERHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                WarehouseTotals l = clist.get(row);
                switch (column) {
                    case 0:
                        return l.printName();
                    case 1:
                        return l.getamount();
                    case 2:
                        return l.getCustomerCurrentAccount();
                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel getCounterModel1() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(COUNTERHEADERS1[column]);
            }

            public int getRowCount() {
                return clist1.size();
            }

            public int getColumnCount() {
                return COUNTERHEADERS1.length;
            }

            public Object getValueAt(int row, int column) {
                WarehouseTotals l = clist1.get(row);
                switch (column) {
                    case 0:
                        return l.printName();
                    case 1:
                        return l.getamount();
                    case 2:
                        return l.getCustomerCurrentAccount();
                    default:
                        return null;
                }
            }
        };
    }
    //warehouse changes - end

    public AbstractTableModel getRdetailsModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(RECEIPTSHEADER[column]);
            }

            public int getRowCount() {
                return m_lrdetails.size();
            }

            public int getColumnCount() {
                return RECEIPTSHEADER.length;
            }

            public Object getValueAt(int row, int column) {
                RdetailsLine l = m_lrdetails.get(row);
                switch (column) {
                    case 0:
                        return l.getrDate();
                    case 1:
                        return l.getrId();
                    case 2:
                        return l.getbillid();
                    case 3:
                        return l.getrCustomer();
                    case 4:
                        return l.getrTotal();
                    case 5:
                        return l.getCounter();
                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel getRdetails2Model() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(RECEIPTSHEADER[column]);
            }

            public int getRowCount() {
                return m_lrdetails2.size();
            }

            public int getColumnCount() {
                return RECEIPTSHEADER.length;
            }

            public Object getValueAt(int row, int column) {
                RdetailsLine2 l = m_lrdetails2.get(row);
                switch (column) {
                    case 0:
                        return l.getrDate();
                    case 1:
                        return l.getrId();
                    case 2:
                        return l.getbillid();
                    case 3:
                        return l.getrCustomer();
                    case 4:
                        return l.getrTotal();
                    case 5:
                        return l.getCounter();
                    default:
                        return null;
                }
            }
        };
    }

    public static class PaymentsLine1 implements SerializableRead {

        private String paymentType1;
        private Double paymentTotal1;

        public void readValues(DataRead dr) throws BasicException {
            paymentType1 = dr.getString(1);
            paymentTotal1 = dr.getDouble(2);
        }

        public String printpaymentType1() {
            return paymentType1;
        }

        public String printpaymentTotal1() {
            return roundTwoDecimals(paymentTotal1);
        }

        public String getpaymentType1() {
            return paymentType1;
        }

        public Double getpaymentTotal1() {
            System.out.println("PaymentsModel::line 1262::"+paymentTotal1);
            return paymentTotal1;
        }
    }

    public static class UCReceiptLine implements SerializableRead {

        private String receiptid;
        private Timestamp date;
        private double total;

        public void readValues(DataRead dr) throws BasicException {
            receiptid = dr.getString(1);
            date = dr.getTimestamp(2);
            total = dr.getDouble(3).doubleValue();
        }

        public String printTotal() {
            return roundTwoDecimals(total);
        }

        public String printDate() {
            return Formats.TIMESTAMP.formatValue(date);
        }

        public String getReceipt() {
            return receiptid;
        }

        public Timestamp getDate() {
            return date;
        }

        public Double getTotal() {
            return total;
        }
    }

    public static class PaymentsLine2 implements SerializableRead {

        private String receiptid;
        private String paymentType1;
        private Double paymentTotal1;

        public void readValues(DataRead dr) throws BasicException {
            receiptid = dr.getString(1);
            paymentType1 = dr.getString(2);
            paymentTotal1 = dr.getDouble(3);
        }

        public String printpaymentType1() {
            return paymentType1;
        }

        public String printpaymentTotal1() {
            return roundTwoDecimals(paymentTotal1);
        }

        public String getpaymentType1() {
            return paymentType1;
        }

        public Double getpaymentTotal1() {
            return paymentTotal1;
        }

        public String getReceipt() {
            return receiptid;
        }
    }
    /*  public static class PaymentsLine implements SerializableRead {

    private String paymentType1;
    private Double paymentTotal1;

    public void readValues(DataRead dr) throws BasicException {
    paymentType1 = dr.getString(1);
    paymentTotal1 = dr.getDouble(2);
    }
    public String printpaymentType() {
    return paymentType1;
    }
    public String printpaymentTotal() {
    return Formats.CURRENCY.formatValue(paymentTotal1);
    }
    public String getpaymentType() {
    return paymentType1;
    }
    public Double getpaymentTotal() {
    return paymentTotal1;
    }
    }*/

    public AbstractTableModel getDebtraisedModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(DEBTRAISEDHEADER[column]);
            }

            public int getRowCount() {
                return drdetails.size();
            }

            public int getColumnCount() {
                return DEBTRAISEDHEADER.length;
            }

            public Object getValueAt(int row, int column) {
                Debtcollected l = drdetails.get(row);
                switch (column) {
                    case 0:
                        return l.getrDate();
                    case 1:
                        return l.getrId();

                    case 2:
                        return l.getrCustomer();
                    case 3:
                        return l.getrTotal();
                    case 4:
                        return l.getaccount();
                    default:
                        return null;
                }
            }
        };
    }

    public static String roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#,##,##0.00");
        return twoDForm.format(d);
    }
    //warehouse changes - start

    public static class WarehouseTotals implements SerializableRead {

        private String name;
        private Double amount;
        private String customerCurrentAccount;

        public void readValues(DataRead dr) throws BasicException {
            //  bno = dr.getString(2);

            amount = dr.getDouble(1);
            name = dr.getString(2);
            customerCurrentAccount = dr.getString(3);
        }

        public String getCustomerCurrentAccount() {
            return customerCurrentAccount;
        }

        public void setCustomerCurrentAccount(String customerCurrentAccount) {
            this.customerCurrentAccount = customerCurrentAccount;
        }

        public String printAmount() {
            return roundTwoDecimals(amount);
        }

        public String printName() {
            return name;
        }

        public Double getamount() {
            System.out.println("2 PaymentModel :: line 1428"+amount);
            return amount;
        }

        /*  public int getlistcount()
        {
        return
        }*/
    }
//      public static class CounterTotals1 implements SerializableRead {
//          private String name1;
//          private Double amount1;
//          public void readValues(DataRead dr) throws BasicException {
//         //  bno = dr.getString(2);
//
//            amount1 = dr.getDouble(1);
//             name1=dr.getString(2);
//          }
//          public String printAmount1()
//          {
//              return roundTwoDecimals(amount1);
//          }
//          public String printName1()
//          {
//              return name1;
//          }
//          public Double getamount1()
//          {
//              return amount1;
//          }
//
//        /*  public int getlistcount()
//          {
//              return
//          }*/
//      }
    //warehouse changes - end

    public static class Debtcollected implements SerializableRead {
        // private String bno;

        private java.sql.Timestamp date;
        private String customer;
        private String rno;
        private Double amount;
        private String account;
        private String cid;

        public void readValues(DataRead dr) throws BasicException {
            //  bno = dr.getString(2);
            amount = dr.getDouble(4);
            date = dr.getTimestamp(1);
            rno = dr.getString(2);
            customer = dr.getString(3);
            account = dr.getString(5);
            cid = dr.getString(6);
        //counter=dr.getString(6);
        }

        public String getcid() {
            return cid;
        }

        public String getaccount() {
            return account;
        }

        public String printrId() {
            return rno;
        }

        public String printrTotal() {
            return roundTwoDecimals(amount);
        }

        public String printrCustomer() {
            return StringUtils.encodeXML(customer);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(date);
        }

        public String getrId() {
            return rno;
        }

        public Double getrTotal() {
            return amount;
        }

        public String getrCustomer() {
            return customer;
        }

        public Date getrDate() {
            return date;
        }
    }

    public AbstractTableModel getOtherIncomeTableModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(DEBTRAISEDHEADER[column]);
            }

            public int getRowCount() {
                return otherIncomedetails.size();
            }

            public int getColumnCount() {
                return DEBTRAISEDHEADER.length;
            }

            public Object getValueAt(int row, int column) {
                OtherIncome l = otherIncomedetails.get(row);
                switch (column) {
                    case 0:
                        return l.getrDate();
                    case 1:
                        return l.getrId();

                    case 2:
                        return l.getrCustomer();
                    case 3:
                        return l.getrTotal();
                    //  case 4 : return l.getaccount();
                    //case 4 : return l.getrGame();
                    //case 5 : return l.getGametype();
                    default:
                        return null;
                }
            }
        };
    }

    public static class OtherIncome implements SerializableRead {
        // private String bno;

        private java.sql.Timestamp date;
        private String customer;
        private String rno;
        private Double amount;
        private String account;
        private String game;
        private String cid;
        private String incometype;
        private String gametype;

        public void readValues(DataRead dr) throws BasicException {
            //  bno = dr.getString(2);
            //gametype=dr.getString(6);
            //game=dr.getString(5);
            amount = dr.getDouble(4);
            date = dr.getTimestamp(1);
            rno = dr.getString(2);
            customer = dr.getString(3);
            account = dr.getString(5);
            cid = dr.getString(6);
            incometype = dr.getString(7);
        //counter=dr.getString(6);
        }

        public String getIncometype() {
            return incometype;
        }

        public String getGametype() {
            return gametype;
        }

        public String getcid() {
            return cid;
        }

        public String printcid() {
            return StringUtils.encodeXML(cid);
        }

        public String getaccount() {
            return account;
        }

        public String printrId() {
            return rno;
        }

        public String printrTotal() {
            return roundTwoDecimals(amount);
        }

        public String printrCustomer() {
            return StringUtils.encodeXML(customer);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(date);
        }

        public String printrGame() {
            return StringUtils.encodeXML(game);
        }

        public String printGametype() {
            return StringUtils.encodeXML(gametype);
        }

        public String getrId() {
            return rno;
        }

        public Double getrTotal() {
            return amount;
        }

        public String getrCustomer() {
            return customer;
        }

        public Date getrDate() {
            return date;
        }

        public String getrGame() {
            return game;
        }
    }

    public static class GuestReceiptdetailsLine implements SerializableRead {

        public String searchKey;
        public String guestCatName;
        public String account;
        public double amount;
        public String rid;
        public java.sql.Timestamp date;
        public String TaxCatID;
        public Double TaxAmount;
        
        public void readValues(DataRead dr) throws BasicException {
            searchKey = dr.getString(1);
            guestCatName = dr.getString(2);
            account = dr.getString(3);
            amount = dr.getDouble(4);
            rid = dr.getString(5);
            date = dr.getTimestamp(6);
            TaxCatID=dr.getString(7);
            TaxAmount=dr.getDouble(8);
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }


        public String getAccount() {
            return account;
        }

        public String printAccount(){
            return StringUtils.encodeXML(account);
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getId() {
            return searchKey;
        }

        public void setId(String id) {
            this.searchKey = id;
        }

        public String getName() {
            return guestCatName;
        }

        public void setName(String name) {
            this.guestCatName = name;
        }

        public String printName() {
            return StringUtils.encodeXML(guestCatName);
        }

        public String printcNo() {
            return StringUtils.encodeXML(searchKey);
        }

        public String printDate() {
            return StringUtils.encodeXML(guestCatName);
        }

        public String printRId() {
            return StringUtils.encodeXML(rid);
        }

        public String printAmount() {
            return roundTwoDecimals(amount);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(date);
        }
        
        
        public String getTaxCatID(){
            return TaxCatID;
        }
        public void setTaxCatID(String TaxCatID){
            this.TaxCatID=TaxCatID;
        }
        
        public Double getTaxAmount(){
            return TaxAmount;
        }
        public void setTaxAmount(Double TaxAmount){
            this.TaxAmount=TaxAmount;
        }
        


    }

    public static class GuestReceiptdetailsLine1 implements SerializableRead {

        public String searchKey;
        public String guestCatName;
        public String account;
        public double amount;
        public String rid;
        public java.sql.Timestamp date;

        public void readValues(DataRead dr) throws BasicException {
            searchKey = dr.getString(1);
            guestCatName = dr.getString(2);
            account = dr.getString(3);
            amount = dr.getDouble(4);
            rid = dr.getString(5);
            date = dr.getTimestamp(6);
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }


        public String getAccount() {
            return account;
        }

        public String printAccount(){
            return StringUtils.encodeXML(account);
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getId() {
            return searchKey;
        }

        public void setId(String id) {
            this.searchKey = id;
        }

        public String getName() {
            return guestCatName;
        }

        public void setName(String name) {
            this.guestCatName = name;
        }

        public String printName() {
            return StringUtils.encodeXML(guestCatName);
        }

        public String printcNo() {
            return StringUtils.encodeXML(searchKey);
        }

        public String printDate() {
            return StringUtils.encodeXML(guestCatName);
        }

        public String printRId() {
            return StringUtils.encodeXML(rid);
        }

        public String printAmount() {
            return roundTwoDecimals(amount);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(date);
        }


    }

    public static class RdetailsLine implements SerializableRead {

        private String m_rid;
        private Double m_rtotal;
        private java.sql.Timestamp m_rtime;
        private String m_rcustomer;
        private String billid;
        private String counter;
        private String custid;

        public void readValues(DataRead dr) throws BasicException {
            m_rid = dr.getString(2);
            m_rtotal = dr.getDouble(5);
            m_rtime = dr.getTimestamp(1);
            billid = dr.getString(3);
            m_rcustomer = dr.getString(4);
            counter = dr.getString(6);
            custid = "";
            String cust1 = "";
            String cust2 = "";
            String temp1[] = m_rcustomer.split(" ");
            String temp[] = m_rcustomer.split("#");
            try {
                Object[] cust = (Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(),
                        "SELECT NAME,SEARCHKEY FROM CUSTOMERS WHERE ID = ? ",
                        SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(temp[0]);

                if (cust != null && cust[0] != null) {
                    cust1 = cust[0].toString();
                    cust2 = cust[1].toString();
                }


            } catch (Exception e) {
            }
            if (temp1.length > 1) {
                this.custid = cust2 + ":" + "G" + temp1[1];
            } else {
                this.custid = cust2;
            }

            if (temp.length > 1) {
                m_rcustomer = cust1 + " : " + temp[1];
            } else {
                m_rcustomer = cust1;
            }
        }

        public String getCounter() {
            String[] arr = counter.split(" ");
            return arr[0];
        }

        public String printCounter() {
            return StringUtils.encodeXML(counter);
        }

        public String getbillid() {
            return billid;
        }

        public String printbillid() {
            return billid;
        }

        public String printrId() {
            return m_rid;
        }

        public String printrTotal() {
            return roundTwoDecimals(m_rtotal);
        }

        public String printrCustomer() {
            return StringUtils.encodeXML(m_rcustomer);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(m_rtime);
        }

        public String getrId() {
            return m_rid;
        }

        public Double getrTotal() {
            return m_rtotal;
        }

        public String getrCustomer() {
            return m_rcustomer;
        }

        public String getcustid() {
            return custid;
        }

        public Date getrDate() {
            return m_rtime;
        }
    }

    public static class RdetailsLine2 implements SerializableRead {

        private String m_rid;
        private Double m_rtotal;
        private java.sql.Timestamp m_rtime;
        private String m_rcustomer;
        private String billid;
        private String counter;
        private String custid;

        public void readValues(DataRead dr) throws BasicException {
            m_rid = dr.getString(2);
            m_rtotal = dr.getDouble(5);
            m_rtime = dr.getTimestamp(1);
            billid = dr.getString(3);
            m_rcustomer = dr.getString(4);
            counter = dr.getString(6);
        }

        public String getCounter() {
            String[] arr = counter.split(" ");
            return arr[0];
        }

        public String printCounter() {
            return StringUtils.encodeXML(counter);
        }

        public String getbillid() {
            return billid;
        }

        public String printbillid() {
            return billid;
        }

        public String printrId() {
            return m_rid;
        }

        public String printrTotal() {
            return roundTwoDecimals(m_rtotal);
        }

        public String printrCustomer() {
            return StringUtils.encodeXML(m_rcustomer);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(m_rtime);
        }

        public String getrId() {
            return m_rid;
        }

        public Double getrTotal() {
            return m_rtotal;
        }

        public String getrCustomer() {
            return m_rcustomer;
        }

        public String getcustid() {
            return custid;
        }

        public Date getrDate() {
            return m_rtime;
        }
    }

    public AbstractTableModel getRdetails1Model() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(RECEIPTSHEADER[column]);
            }

            public int getRowCount() {
                return m_lrdetails1.size();
            }

            public int getColumnCount() {
                return RECEIPTSHEADER.length;
            }

            public Object getValueAt(int row, int column) {
                RdetailsLine1 l = m_lrdetails1.get(row);
                switch (column) {
                    case 0:
                        return l.getrDate();
                    case 1:
                        return l.getrId();
                    case 2:
                        return l.getbillid();
                    case 3:
                        return l.getrCustomer();
                    case 4:
                        return l.getrTotal();
                    case 5:
                        return l.getCounter();
                    case 6:
                        return l.getAccount();
                    default:
                        return null;
                }
            }
        };
    }

    public static class RdetailsLine1 implements SerializableRead {

        private String m_rid;
        private Double m_rtotal;
        private java.sql.Timestamp m_rtime;
        private String m_rcustomer;
        private String billid;
        private String counter;
        private String account;
        private String cid;
        private java.sql.Timestamp bdate;
        //warehouse changes - start
        private String facilityId;
        //warehouse changes - end

        public void readValues(DataRead dr) throws BasicException {
            m_rid = dr.getString(2);
            m_rtotal = dr.getDouble(5);
            m_rtime = dr.getTimestamp(1);
            billid = dr.getString(3);
            m_rcustomer = dr.getString(4);
            counter = dr.getString(6);
            account = dr.getString(7);
            cid = dr.getString(8);
            bdate = dr.getTimestamp(9);
            //warehouse changes - start
            facilityId = dr.getString(10);
        //warehouse changes - end
        }
        //warehouse changes - start

        public String getFacilityId() {
            return facilityId;
        }

        public void setFacilityId(String facilityId) {
            this.facilityId = facilityId;
        }
        //warehouse changes - end

        public String getCounter() {
            String[] arr = counter.split(" ");
            return arr[0];
        }

        public String getAccount() {
            return account;
        }

        public String getCid() {
            return cid;
        }

        public String printCounter() {
            String[] arr = counter.split(" ");
            return StringUtils.encodeXML(arr[0]);
        }

        public String getbillid() {
            return billid;
        }

        public String printbillid() {
            return billid;
        }

        public String printrId() {
            return m_rid;
        }

        public String printrTotal() {
            return roundTwoDecimals(m_rtotal);
        }

        public String printrCustomer() {
            return StringUtils.encodeXML(m_rcustomer);
        }

        public String printrDate() {
            return Formats.DATE.formatValue(m_rtime);
        }

        public String getrId() {
            return m_rid;
        }

        public Double getrTotal() {
            return m_rtotal;
        }

        public String getrCustomer() {
            return m_rcustomer;
        }

        public Date getrDate() {
            return m_rtime;
        }

        public Date getbDate() {
            return bdate;
        }

        public String printBDate() {
            return Formats.DATE.formatValue(bdate);
        }
    }

    public AbstractTableModel getPaymentsModel1() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(PAYMENTHEADERS1[column]);
            }

            public int getRowCount() {
                return m_lsales.size();
            }

            public int getColumnCount() {
                return PAYMENTHEADERS1.length;
            }

            public Object getValueAt(int row, int column) {
                PaymentsLine1 l = m_lsales.get(row);
                switch (column) {
                    case 0:
                        return l.getpaymentType1();
                    case 1:
                        return l.getpaymentTotal1();
                    default:
                        return null;
                }
            }
        };
    }

    public static class OtherIncomeTotalLine implements SerializableRead {

        private String Type;
        private Double Total;
        private String desc;
        private String receipt;//added by pratima:to get receipt 

        
        public void readValues(DataRead dr) throws BasicException {

            Total = dr.getDouble(1);
            Type = dr.getString(2);
            desc = dr.getString(3);
            receipt=dr.getString(4);
        }

        public String printPtype() {
            return StringUtils.encodeXML(Type);
        }

        public String getPtype() {
            return Type;
        }

        public String getDesc() {
            return desc;
        }

        public String printPtotal() {
            return roundTwoDecimals(Total);
        }

        public Double getPtotal() {
            return Total;
        }

        public String printDesc() {
            return StringUtils.encodeXML(desc);
        }
       //added by pratima
        public String getReceipt() {
            return receipt;
        }

        public void setReceipt(String receipt) {
            this.receipt = receipt;
        }
        //ended by pratima
    }

    public AbstractTableModel getChequeDetailModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(CDETAILHEADERS[column]);
            }

            public int getRowCount() {
                return cdetail.size();
            }

            public int getColumnCount() {
                return CDETAILHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                ChequeDetailLine l = cdetail.get(row);
                switch (column) {
                    case 0:
                        return l.getchid();
                    case 1:
                        return l.getBank();
                    case 2:
                        return l.getAmount();
                    case 3:
                        return l.getcustomer();
                    default:
                        return null;
                }
            }
        };
    }

    public static class ChequeDetailLine implements SerializableRead {

        private String chid;
        private String bank;
        private Double amount;
        private String customer;

        public void readValues(DataRead dr) throws BasicException {
            chid = dr.getString(1);
            bank = dr.getString(2);
            amount = dr.getDouble(3);
            customer = dr.getString(4);
        }

        public String getchid() {
            return chid;
        }

        public String getBank() {
            return bank;
        }

        public String printBank() {
            return StringUtils.encodeXML(bank);
        }

        public String printchid() {
            return StringUtils.encodeXML(chid);
        }

        public String printAmount() {
            return roundTwoDecimals(amount);
        }

        public Double getAmount() {
            return amount;
        }

        public String getcustomer() {
            return customer;
        }

        public String printCustomer() {
            return StringUtils.encodeXML(customer);
        }
    }

    public static class cashPendingBills implements SerializableRead {

        int count;

        public void readValues(DataRead dr) throws BasicException {
            count = dr.getInt(1);
        }
    }
    ///////////pratima : to add card details  in close cash
     public AbstractTableModel getCardDetailModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(CARDDETAILHEADERS[column]);
            }

            public int getRowCount() {
                return carddetail.size();
            }

            public int getColumnCount() {
                return CARDDETAILHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                CardDetailLine l = carddetail.get(row);
                switch (column) {
                    case 0:
                        return l.gettransactionId();
                    case 1:
                        return l.getBank();
                    case 2:
                        return l.getAmount();
                    case 3:
                        return l.getcustomer();
                    default:
                        return null;
                }
            }
        };
    }

       public static class CardDetailLine implements SerializableRead {

        private String transactionId;
        private String bank;
        private Double amount;
        private String customer;

        public void readValues(DataRead dr) throws BasicException {
           transactionId = dr.getString(1);
            bank = dr.getString(2);
            amount = dr.getDouble(3);
            customer = dr.getString(4);
        }

        public String gettransactionId() {
            return transactionId;
        }

        public String getBank() {
            return bank;
        }

        public String printBank() {
            return StringUtils.encodeXML(bank);
        }

        public String printTransactionId() {
            return StringUtils.encodeXML(transactionId);
        }

        public String printAmount() {
            return roundTwoDecimals(amount);
        }

        public Double getAmount() {
            return amount;
        }

        public String getcustomer() {
            return customer;
        }

        public String printCustomer() {
            return StringUtils.encodeXML(customer);
        }
    }
}

