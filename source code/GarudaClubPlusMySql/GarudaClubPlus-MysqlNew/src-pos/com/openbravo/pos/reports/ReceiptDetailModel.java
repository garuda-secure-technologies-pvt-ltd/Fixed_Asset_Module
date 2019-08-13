/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author swathi
 */
public class ReceiptDetailModel {

    private List<ReceiptDetailModelLine> rptlist;
    private List<ReceiptGuest> rptlist1;
    private ReceiptDetailModelLine rpline;
    private ReceiptMember rmlist;
    private DataLogicFacilities dlfac;
    private ReceiptGuest payment;
    private List<ReceiptGeneral> recgen;
    private List<ReceiptMember> recmem;
    private String narration;
    private String paymentmode;
    private Date crdate;
    private String customer;
    private String createdby;
    private String role;
    private ChequeDetailLine cdetail;
    private String chequeno;
    private Double total = 0.0;
    private Double amount;
   
    private String fname;
    private String bank;

    public ReceiptDetailModel() {
    }

    public ReceiptDetailModel loadInstance(AppView app, String id) throws BasicException {
        ReceiptDetailModel rm = new ReceiptDetailModel();
        Map<String, String> map = new HashMap<String, String>();
        List<ReceiptDetailModelLine> li = (List<ReceiptDetailModelLine>) new StaticSentence(app.getSession(), "SELECT RECEIPT,RDATE,CNAME,CKEY,BID,WNAME,BAMOUNT,TAX,TOTAL,PAYMENT,RUSER,ROLE  FROM " +
                "(SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,B.ID AS BID,W.NAME AS WNAME,B.AMOUNT AS BAMOUNT,B.TAXTOTAL AS TAX,(B.AMOUNT+B.TAXTOTAL) AS TOTAL,P.PAYMENT AS PAYMENT,R.RUSER AS RUSER,RO.NAME AS ROLE FROM RECEIPTS R,BILL B,CUSTOMERS C,WAITER W,PAYMENTS P,CHEQUE Ch,ROLES RO,PEOPLE PE WHERE R.ID=B.RECEIPT AND B.CUSTOMER=C.ID AND W.ID=B.WAITER AND R.ID=? AND P.RECEIPT=R.ID AND R.RUSER = PE.NAME AND PE.ROLE=RO.ID " +
                " UNION ALL" +
                " SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,B.ID AS BID,W.NAME AS WNAME,B.AMOUNT AS BAMOUNT,B.TAXTOTAL AS TAX,(B.AMOUNT+B.TAXTOTAL) AS TOTAL,P.PAYMENT AS PAYMENT,R.RUSER AS RUSER,RO.NAME AS ROLE  FROM RECEIPTS_ARV R,BILL_ARV B,CUSTOMERS C,WAITER W,PAYMENTS_ARV P,CHEQUE Ch,ROLES RO,PEOPLE PE WHERE R.ID=B.RECEIPT AND B.CUSTOMER=C.ID AND W.ID=B.WAITER AND R.ID=? AND P.RECEIPT=R.ID AND R.RUSER = PE.NAME AND PE.ROLE=RO.ID)" +
                "AS RECEIPT GROUP BY RECEIPT",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptDetailModelLine.class)).list(new Object[]{id, id});
        if (li == null) {
            rm.rptlist = new ArrayList<ReceiptDetailModelLine>();
        } else {
            rm.rptlist = li;
            ReceiptDetailModelLine r = (ReceiptDetailModelLine) new StaticSentence(app.getSession(), "SELECT NULL AS RECEIPT,NULL AS RDATE,NULL AS CNAME,NULL AS CKEY,NULL AS BID,NULL AS WNAME,0 AS BAMOUNT,0 AS TAX,0 AS TOTAL,P.PAYMENT AS PAYMENT,0 AS RUSER,0 AS ROLE FROM " +
                    "RECEIPTS R,BILL B,CUSTOMERS C,WAITER W,PAYMENTS P,CHEQUE Ch,ROLES RO,PEOPLE PE WHERE R.ID=B.RECEIPT AND B.CUSTOMER=C.ID AND W.ID=B.WAITER AND R.ID=? AND P.RECEIPT=R.ID AND R.RUSER = PE.NAME AND PE.ROLE=RO.ID ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptDetailModelLine.class)).find(new Object[]{id, id});
            if (r == null) {
                rm.rpline = new ReceiptDetailModelLine();
            } else {
                rm.rpline = r;
                rm.paymentmode = r.getPayment();
                if (rm.paymentmode.equals("cheque")) {
                    ChequeDetailLine chdetail = (ChequeDetailLine) new StaticSentence(app.getSession(), "SELECT CHEQUENO,BANK FROM (SELECT C.CHEQUENO,C.BANK,R.ID FROM CHEQUE C ,RECEIPTS R WHERE R.ID=? AND C.RNO =R.ID)CHEQUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ChequeDetailLine.class)).find(new Object[]{id});
                    if (chdetail == null) {
                        rm.cdetail = new ChequeDetailLine();
                    } else {
                        rm.cdetail = chdetail;
                        rm.chequeno = chdetail.getChequeno();
                        rm.bank = chdetail.getBank();
                    }
                }
            }

        }
        return rm;
    }

    public ReceiptDetailModel loadInstanceguest(AppView app, String id) throws BasicException {
        ReceiptDetailModel rm = new ReceiptDetailModel();
        List<ReceiptGuest> li = new StaticSentence(app.getSession(), "SELECT RECEIPT,RDATE,CNAME,CKEY,WNAME,BAMOUNT,TAX,TOTAL,PAYMENT,NUM,RUSER,ROLE,GUESTNAME,TAXAMOUNT FROM\n" +
                                                                    "(SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,G.NAME AS WNAME,\n" +
                                                                    "G.RATE  AS BAMOUNT,0 AS TAX,P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT ,GL.NUM AS NUM,R.RUSER AS RUSER,\n" +
                                                                    "RO.NAME AS ROLE,GL.NAMES AS GUESTNAME , IFNULL(GL.TAXAMOUNT,0.00) AS TAXAMOUNT FROM RECEIPTS R,GUESTCAT G,CUSTOMERS C,PAYMENTS P ,\n" +
                                                                    "GUESTLOG GL,BILL B,PEOPLE PE,ROLES RO WHERE  P.CUSTOMER=C.ID AND R.ID=? AND P.RECEIPT=R.ID \n" +
                                                                    "AND G.ID=R.DESC_ AND C.ID=GL.MEMNO AND G.ID=GL.GUESTCAT and GL.RECEIPTNO=r.id AND R.RUSER = PE.NAME \n" +
                                                                    "AND PE.ROLE=RO.ID \n" +
                                                                    "UNION ALL \n" +
                                                                    "SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,G.NAME AS WNAME,\n" +
                                                                    "G.RATE  AS BAMOUNT,0 AS TAX,P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT ,GL.NUM AS NUM,R.RUSER AS RUSER,\n" +
                                                                    "RO.NAME AS ROLE,GL.NAMES AS GUESTNAME, IFNULL(GL.TAXAMOUNT,0.00) AS TAXAMOUNT FROM RECEIPTS_ARV R,GUESTCAT G,CUSTOMERS C,PAYMENTS_ARV P ,\n" +
                                                                    "GUESTLOG GL,BILL B,PEOPLE PE,ROLES RO  WHERE  P.CUSTOMER=C.ID AND R.ID=? AND P.RECEIPT=R.ID AND \n" +
                                                                    "G.ID=R.DESC_ AND C.ID=GL.MEMNO AND G.ID=GL.GUESTCAT AND GL.RECEIPTNO=r.id AND R.RUSER = PE.NAME \n" +
                                                                    "AND PE.ROLE=RO.ID)\n" +
                                                                    "AS RECEIPT  GROUP BY RECEIPT",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptGuest.class)).list(new Object[]{id, id});
        if (li == null) {
            rm.rptlist1 = new ArrayList<ReceiptGuest>();
        } else {
            rm.rptlist1 = li;
            ReceiptGuest r = (ReceiptGuest) new StaticSentence(app.getSession(), "SELECT NULL AS RECEIPT,NULL AS RDATE,NULL AS CNAME,NULL AS CKEY,NULL AS WNAME,0 AS BAMOUNT,0 AS TAX,0 AS TOTAL,P.PAYMENT AS PAYMENT ,0 AS NUM,NULL AS RUSER,NULL AS ROLE,NULL AS GUESTNAME,0.00 AS TAXAMOUNT FROM " +
                    "RECEIPTS R,GUESTCAT G,CUSTOMERS C,PAYMENTS P ,GUESTLOG GL,BILL B,PEOPLE PE,ROLES RO WHERE P.CUSTOMER=C.ID AND R.ID=? AND P.RECEIPT=R.ID AND G.ID=R.DESC_ AND C.ID=GL.MEMNO AND G.ID=GL.GUESTCAT AND R.RUSER = PE.NAME AND PE.ROLE=RO.ID ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptGuest.class)).find(new Object[]{id, id, id});
            if (r == null) {
                rm.payment = new ReceiptGuest();
            } else {
                rm.payment = r;

                rm.paymentmode = r.getPayment();
                if (rm.paymentmode.equals("cheque")) {
                    ChequeDetailLine chdetail = (ChequeDetailLine) new StaticSentence(app.getSession(), "SELECT CHEQUENO,BANK FROM (SELECT C.CHEQUENO,C.BANK,R.ID FROM CHEQUE C ,RECEIPTS R WHERE R.ID=? AND C.RNO =R.ID)CHEQUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ChequeDetailLine.class)).find(new Object[]{id});
                    if (chdetail == null) {
                        rm.cdetail = new ChequeDetailLine();
                    } else {
                        rm.cdetail = chdetail;
                        rm.chequeno = chdetail.getChequeno();
                        rm.bank = chdetail.getBank();
                    }
                }
            }
        }
        return rm;
    }
    
    public ReceiptDetailModel loadInstanceguestMK(AppView app, String id) throws BasicException {
        ReceiptDetailModel rm = new ReceiptDetailModel();
        List<ReceiptGuest> li = new StaticSentence(app.getSession(), "SELECT RECEIPT,RDATE,CNAME,CKEY,WNAME,BAMOUNT,TAX,TOTAL,PAYMENT,NUM,RUSER,ROLE,GUESTNAME FROM (SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,G.NAME AS WNAME,G.RATE  AS BAMOUNT,0 AS TAX,P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT ,GL.NUM AS NUM, R.RUSER AS RUSER,'Member' as ROLE, GL.NAMES AS GUESTNAME  FROM RECEIPTS R,GUESTCAT G,CUSTOMERS C,PAYMENTS P ,GUESTLOG GL WHERE  P.CUSTOMER=C.ID AND R.ID=? AND P.RECEIPT=R.ID AND G.ID=R.DESC_ AND C.ID=GL.MEMNO AND G.ID=GL.GUESTCAT and GL.RECEIPTNO=r.id AND R.PAYMENTREF ='Guest Entry through Member Kiosk' UNION ALL  SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,G.NAME AS WNAME,G.RATE  AS BAMOUNT,0 AS TAX,P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT ,R.RUSER AS RUSER, 'Member' as ROLE, GL.NUM AS NUM,GL.NAMES AS GUESTNAME FROM RECEIPTS_ARV R,GUESTCAT G,CUSTOMERS C,PAYMENTS_ARV P ,GUESTLOG GL WHERE  P.CUSTOMER=C.ID AND R.ID=? AND P.RECEIPT=R.ID AND G.ID=R.DESC_ AND C.ID=GL.MEMNO AND G.ID=GL.GUESTCAT AND GL.RECEIPTNO=r.id AND R.PAYMENTREF ='Guest Entry through Member Kiosk')AS RECEIPT  GROUP BY RECEIPT",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptGuest.class)).list(new Object[]{id, id});
        if (li == null) {
            rm.rptlist1 = new ArrayList<ReceiptGuest>();
        } else {
            rm.rptlist1 = li;
            
            rm.payment = new ReceiptGuest();
         // ReceiptGuest r = (ReceiptGuest) new StaticSentence(app.getSession(), "SELECT NULL AS RECEIPT,NULL AS RDATE,NULL AS CNAME,NULL AS CKEY,NULL AS WNAME,0 AS BAMOUNT,0 AS TAX,0 AS TOTAL,P.PAYMENT AS PAYMENT ,0 AS NUM,NULL AS RUSER,NULL AS ROLE,NULL AS GUESTNAME  "
                 //   + "FROM RECEIPTS R,GUESTCAT G,CUSTOMERS C,PAYMENTS P ,GUESTLOG GL,BILL B,PEOPLE PE,ROLES RO WHERE P.CUSTOMER=C.ID AND R.ID=? AND P.RECEIPT=R.ID AND G.ID=R.DESC_ AND C.ID=GL.MEMNO AND G.ID=GL.GUESTCAT AND R.PAYMENTREF ='Guest Entry through Member Kiosk' ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptGuest.class)).find(new Object[]{id, id, id});
           // if (r == null) {
          //      rm.payment = new ReceiptGuest();
          //  } else {
           //     rm.payment = r;

              //  rm.paymentmode = r.getPayment();
           /*       if (rm.paymentmode.equals("cheque")) {
                    ChequeDetailLine chdetail = (ChequeDetailLine) new StaticSentence(app.getSession(), "SELECT CHEQUENO,BANK FROM (SELECT C.CHEQUENO,C.BANK,R.ID FROM CHEQUE C ,RECEIPTS R WHERE R.ID=? AND C.RNO =R.ID)CHEQUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ChequeDetailLine.class)).find(new Object[]{id});
                    if (chdetail == null) {
                        rm.cdetail = new ChequeDetailLine();
                    } else {
                        rm.cdetail = chdetail;
                        rm.chequeno = chdetail.getChequeno();
                        rm.bank = chdetail.getBank();
                    }
                }*/
            
        }
        return rm;
    }

    public List<ReceiptGuest> getRptlist1() {
        return rptlist1;
    }

    public void setRptlist1(List<ReceiptGuest> rptlist1) {
        this.rptlist1 = rptlist1;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

  
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    //public String getChequeno() {
//        return chequeno;
//    }
    public ReceiptDetailModel loadInstancemember(AppView app, String id) throws BasicException {
        ReceiptDetailModel rm = new ReceiptDetailModel();
    //    List<ReceiptMember> li = new StaticSentence(app.getSession(), "SELECT R.ID AS RECEIPTS ,R.DATENEW AS DATENEW,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,F.NAME AS FACILITY,A.AMOUNT AS AMOUNT ,P.TOTAL AS TOTAL,A.NARRATION AS NARRATION,P.PAYMENT AS PAYMENTMODE,PO.NAME AS CREATEDBY,A.COUNTER AS COUNTER,RO.NAME AS ROLE,A.PAYMENTREF AS PAYREF FROM " +
          //      "CUSTOMERS C,PEOPLE PO,ROLES RO,PAYMENTS P,RECEIPTS R,ACCOUNTJOURNAL A JOIN FACILITY F ON A.TRANSREF=F.ID  WHERE R.ID =? AND P.RECEIPT = R.ID AND  RO.ID = PO.ROLE AND PO.NAME = R.RUSER AND P.CUSTOMER=C.ID AND A.PAYMENTREF LIKE '%' ? ' # %'",
          //      new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptMember.class)).list(new Object[]{id, id});

        //Lokesh : Changed this query to get all type of recipts. Earlier only facility was coming.
         List<ReceiptMember> li = new StaticSentence(app.getSession(), "SELECT R.ID AS RECEIPTS ,R.DATENEW AS DATENEW,C.NAME AS CNAME,C.SEARCHKEY AS CKEY,IFNULL((SELECT NAME FROM FACILITY WHERE ID = A.TRANSREF), A.TRANSREF) AS FACILITY,A.AMOUNT AS AMOUNT ,P.TOTAL AS TOTAL,A.NARRATION AS NARRATION,P.PAYMENT AS PAYMENTMODE,PO.NAME AS CREATEDBY,A.COUNTER AS COUNTER,RO.NAME AS ROLE,A.PAYMENTREF AS PAYREF FROM " +
                "CUSTOMERS C,PEOPLE PO,ROLES RO,PAYMENTS P,RECEIPTS R,ACCOUNTJOURNAL A WHERE R.ID =? AND P.RECEIPT = R.ID AND  RO.ID = PO.ROLE AND PO.NAME = R.RUSER AND P.CUSTOMER=C.ID AND IFNULL(A.PAYMENTREF LIKE '%' ? ' # %', A.TRANSNO = ?)",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.STRING}), new SerializerReadClass(ReceiptMember.class)).list(new Object[]{id, id, id});

        
        
        if (li == null) {
            rm.recmem = new ArrayList<ReceiptMember>();
        } else {
            rm.recmem = li;

            ReceiptMember r = (ReceiptMember) new StaticSentence(app.getSession(), "SELECT NULL AS RECEIPTS ,NULL AS DATENEW,NULL  AS CNAME,NULL  AS CKEY,NULL  AS FACILITY,A.AMOUNT  AS AMOUNT,P.TOTAL AS TOTAL,NULL  AS NARRATION,P.PAYMENT AS PAYMENTMODE,NULL  AS CREATEDBY,NULL  AS COUNTER,NULL  AS ROLE,NULL  AS PAYREF FROM " +
                    "CUSTOMERS C,PEOPLE PO,ROLES RO,PAYMENTS P,RECEIPTS R,ACCOUNTJOURNAL A   WHERE R.ID =? AND P.RECEIPT = R.ID AND  RO.ID = PO.ROLE AND PO.NAME = A.CREATEDBY AND P.CUSTOMER=C.ID AND A.PAYMENTREF LIKE '%' ? ' # %'", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptMember.class)).find(new Object[]{id, id});
            if (r == null) {
                rm.rmlist = new ReceiptMember();
            } else {
                rm.rmlist = r;

                rm.paymentmode = r.getPaymentmode();

                rm.amount = r.getAmount();
                rm.total = r.getTotal();
                double totAmount = 0.0;
                for (Iterator<ReceiptMember> it = li.iterator(); it.hasNext();) {
                    ReceiptMember receiptMember = it.next();
                    totAmount = totAmount+receiptMember.getAmount();
                }
                totAmount = totAmount+0.009999;
                
                if (rm.total > totAmount) {
                    ReceiptMember rd1 = new ReceiptMember();
                    rd1.setFname("Account Pay");
                    rd1.setAmount((double)Math.round(rm.total - totAmount));
                    li.add(rd1);

                    rm.amount = rd1.getAmount();
                   
                    rm.fname = rd1.getFname();

                }
                if (rm.paymentmode.equals("cheque")) {
                    ChequeDetailLine chdetail = (ChequeDetailLine) new StaticSentence(app.getSession(), "SELECT CHEQUENO,BANK FROM (SELECT C.CHEQUENO,C.BANK,R.ID FROM CHEQUE C ,RECEIPTS R WHERE R.ID=? AND C.RNO =R.ID)CHEQUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ChequeDetailLine.class)).find(new Object[]{id});
                    if (chdetail == null) {
                        rm.cdetail = new ChequeDetailLine();
                    } else {
                        rm.cdetail = chdetail;
                        rm.chequeno = chdetail.getChequeno();
                        rm.bank = chdetail.getBank();

                    }
                }
            }
        }
        if(rm.amount!=null)
        {
        rm.amount = (double)Math.round(rm.amount);
        }
        return rm;

    }

    public List<ReceiptMember> getRecmem() {
        return recmem;
    }

    public void setRecmem(List<ReceiptMember> recmem) {
        this.recmem = recmem;
    }

    public ReceiptDetailModel loadInstancegeneral(AppView app, String id) throws BasicException {
        ReceiptDetailModel rm = new ReceiptDetailModel();

        Map<String, String> map = new HashMap<String, String>();
        ReceiptDetailModelLine li = (ReceiptDetailModelLine) new StaticSentence(app.getSession(), "SELECT RECEIPT,RDATE,CNAME,CKEY,BID,WNAME,BAMOUNT,TAX,TOTAL,PAYMENT,RUSER,ROLE FROM" +
                "(SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,P.CUSTOMER AS CNAME,NULL AS CKEY,R.PAYMENTREF AS BID,R.DESC_ AS WNAME,0 AS BAMOUNT,0 AS TAX,P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT,R.RUSER AS RUSER ,RO.NAME AS ROLE FROM RECEIPTS R,PAYMENTS P,BILL B ,PEOPLE PE,ROLES RO WHERE R.ID= ? AND P.RECEIPT=R.ID AND R.RUSER = PE.NAME AND PE.ROLE=RO.ID " +
                "UNION ALL" +
                " SELECT R.ID AS RECEIPT,R.DATENEW AS RDATE,P.CUSTOMER AS CNAME,NULL AS CKEY,R.PAYMENTREF AS BID,R.DESC_ AS WNAME,0 AS BAMOUNT,0 AS TAX,P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT,R.RUSER AS RUSER ,RO.NAME AS ROLE FROM RECEIPTS_ARV R,PAYMENTS_ARV P,PLACES PL,BILL B ,PEOPLE PE,ROLES RO WHERE R.ID=? AND P.RECEIPT=R.ID AND R.RUSER = PE.NAME AND PE.ROLE=RO.ID) " +
                "AS RECIEPT GROUP BY RECEIPT ",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptDetailModelLine.class)).find(new Object[]{id, id});
        if (li == null) {
            rm.rpline = new ReceiptDetailModelLine();
        } else {
            rm.rpline = li;
            rm.narration = li.getWname();
            rm.paymentmode = li.getPayment();

            rm.crdate = li.getRdate();
            rm.customer = li.getCname();
            rm.createdby = li.getCreatedby();
            rm.role = li.getRole();
            String[] str = li.getBid().split("#");
            int j = 1;
            List<ReceiptDetailModel.ReceiptGeneral> reclline = new ArrayList<ReceiptDetailModel.ReceiptGeneral>();
            ReceiptGeneral r = null;

            for (int i = 0; i < str.length; i++) {
                Object obj = new StaticSentence(app.getSession(), "SELECT NAME FROM ACCOUNTMASTER WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(str[i].toString());
                r = new ReceiptGeneral();
                if (obj != null) {
                    r.setPerticular(obj.toString());
                    i++;
                    r.setAmount(Double.valueOf(str[i].toString()));
                }
                reclline.add(r);
            }

            rm.recgen = reclline;
            if (rm.paymentmode.equals("cheque")) {
                ChequeDetailLine chdetail = (ChequeDetailLine) new StaticSentence(app.getSession(), "SELECT CHEQUENO,BANK FROM (SELECT C.CHEQUENO,C.BANK,R.ID FROM CHEQUE C ,RECEIPTS R WHERE R.ID=? AND C.RNO =R.ID)CHEQUE  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ChequeDetailLine.class)).find(new Object[]{id});
                if (chdetail == null) {
                    rm.cdetail = new ChequeDetailLine();
                } else {
                    rm.cdetail = chdetail;
                    rm.chequeno = chdetail.getChequeno();
                    rm.bank = chdetail.getBank();
                }
            }

        }
        return rm;
    }

    public List<ReceiptDetailModelLine> getReceiptlist() {
        return rptlist;
    }

    public void setReceiptlist(List<ReceiptDetailModelLine> waiterlist) {
        this.rptlist = waiterlist;
    }

    public List<ReceiptGeneral> getRecgen() {
        return recgen;
    }

    public void setRecgen(List<ReceiptGeneral> recgen) {
        this.recgen = recgen;
    }

    public Date getCrdate() {
        return crdate;
    }

    public void setCrdate(Date crdate) {
        this.crdate = crdate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public static class ChequeDetailLine implements SerializableRead {

        private String chequeno;
        private String bank;

        public void readValues(DataRead dr) throws BasicException {
            chequeno = dr.getString(1);
            bank = dr.getString(2);
        }

        public String getChequeno() {
            return chequeno;
        }

        public void setChequeno(String chequeno) {
            this.chequeno = chequeno;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
    }

    public static class ReceiptDetailModelLine implements SerializableRead {

        private String rid;
        private Date rdate;
        private String cname;
        private String ckey;
        private String bid;
        private String wname;
        private Double bamount;
        private Double tax;
        private Double total;
        private String payment;
        private String createdby;
        private String role;

        public void readValues(DataRead dr) throws BasicException {
            rid = dr.getString(1);
            rdate = dr.getTimestamp(2);
            cname = dr.getString(3);
            ckey = dr.getString(4);
            bid = dr.getString(5);
            wname = dr.getString(6);
            bamount = dr.getDouble(7);
            tax = dr.getDouble(8);
            total = dr.getDouble(9);
            payment = dr.getString(10);
            createdby = dr.getString(11);
            role = dr.getString(12);

        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public Double getBamount() {
            return bamount;
        }

        public void setBamount(Double bamount) {
            this.bamount = bamount;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getCkey() {
            return ckey;
        }

        public void setCkey(String ckey) {
            this.ckey = ckey;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public Date getRdate() {
            return rdate;
        }

        public void setRdate(Date rdate) {
            this.rdate = rdate;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public String getWname() {
            return wname;
        }

        public void setWname(String wname) {
            this.wname = wname;
        }
    }

    public static class ReceiptGuest implements SerializableRead {

        private String rid;
        private Date rdate;
        private String cname;
        private String ckey;
        private String wname;
        private Double bamount;
        private Double tax;
        private Double total;
        private String payment;
        private int qty;
        private String createdby;
        private String role;
        private String gname;
        private Double TaxAmount;
        
        
        //RECEIPT,RDATE,CNAME,CKEY,BID,WNAME,BAMOUNT,TAX,TOTAL,PAYMENT 
        public void readValues(DataRead dr) throws BasicException {
            rid = dr.getString(1);
            rdate = dr.getTimestamp(2);
            cname = dr.getString(3);
            ckey = dr.getString(4);
            wname = dr.getString(5);
            bamount = dr.getDouble(6);
            tax = dr.getDouble(7);
            total = dr.getDouble(8);
            payment = dr.getString(9);
            qty = dr.getInt(10);
            createdby = dr.getString(11);
            role = dr.getString(12);
            gname = dr.getString(13);
            TaxAmount=dr.getDouble(14);
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public Double getBamount() {
            return bamount;
        }

        public void setBamount(Double bamount) {
            this.bamount = bamount;
        }

        public String getCkey() {
            return ckey;
        }

        public void setCkey(String ckey) {
            this.ckey = ckey;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public Date getRdate() {
            return rdate;
        }

        public void setRdate(Date rdate) {
            this.rdate = rdate;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public String getWname() {
            return wname;
        }

        public void setWname(String wname) {
            this.wname = wname;
        }
        
        public Double getTaxAmount(){
            return TaxAmount;
        }
    }

    public static class ReceiptMember implements SerializableRead {

        private String rid;
        private Date rdate;
        private String cname;
        private String ckey;
        private String fname;
        private Double amount;
        private Double total;
        private String narration;
        private String paymentmode;
        private String createdby;
        private String counter;
        private String role;
        private String payref;

        public void readValues(DataRead dr) throws BasicException {
            rid = dr.getString(1);
            rdate = dr.getTimestamp(2);
            cname = dr.getString(3);
            ckey = dr.getString(4);
            fname = dr.getString(5);
            amount = dr.getDouble(6);
            total = dr.getDouble(7);
            narration = dr.getString(8);
            paymentmode = dr.getString(9);
            createdby = dr.getString(10);
            counter = dr.getString(11);
            role = dr.getString(12);
            payref = dr.getString(13);
            if (payref != null && rid != null) {
                String[] temparr = payref.split(" : ");
                for (int i = 0; i < temparr.length; i++) {
                    String[] arr = temparr[i].toString().split(" # ");
                    if (arr.length > 1) {
                        if (arr[0].toString().equals(rid)) {
                            //if(arr.length>1)
                            amount = Double.valueOf(arr[1]);
                            break;
                        }
                    } else {
                        amount = 0.0;
                    }
                }
            }
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        
        public String getCounter() {
            return counter;
        }

        public void setCounter(String counter) {
            this.counter = counter;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {

            this.fname = fname;
        }

        public String getNarration() {
            return narration;
        }

        public void setNarrat(String narration) {
            this.narration = narration;
        }

        public Date getRdate() {
            return rdate;
        }

        public void setRdate(Date rdate) {
            this.rdate = rdate;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getCkey() {
            return ckey;
        }

        public void setCkey(String ckey) {
            this.ckey = ckey;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getPaymentmode() {
            return paymentmode;
        }

        public void setPaymentmode(String paymentmode) {
            this.paymentmode = paymentmode;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        
        
    }

    public static class ReceiptGeneral implements SerializableRead {

        private String perticular;
        private Double amount;

        public void readValues(DataRead dr) throws BasicException {
            perticular = dr.getString(1);
            amount = dr.getDouble(2);

        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getPerticular() {
            return perticular;
        }

        public void setPerticular(String perticular) {
            this.perticular = perticular;
        }
    }
}

