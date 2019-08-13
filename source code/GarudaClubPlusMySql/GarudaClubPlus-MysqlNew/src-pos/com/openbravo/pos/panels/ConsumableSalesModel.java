/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author user
 */
public class ConsumableSalesModel {

    private String m_sHost;
    private int m_iSeq;
    private Date m_dDateStart;
    private Date m_dDateEnd;
    private Integer m_iSales;
    private Double m_dSalesBase;
    private Double m_dSalesTaxes;
    private java.util.List<SalesLine> m_lsales;   
    private java.util.List<ProductsAccountLine> productsaccount;
    private java.util.List<ProductsCategoryLine> categoriesTotal;
    private java.util.List<ProductsAccountLine> productsWarerhouseTotalInternal;
    private java.util.List<ProductsAccountLine> productsWarerhouseTotalBilling;
    private java.util.List<ProductsAccountLine> productsaccountbilling;    
    private java.util.List<TaxLine> taxLines;
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
    private double TaxTotal;
    private List<SalesLine> m_billinfo;
     private List<SalesLine> m_billinfoInternal;
    private List<SalesLine> m_billinfoBilling;
    private List<ProductsLine> prdtWise;
    private List<DepartmentLines> DepartmentWise;
    private List<AccountLines> AccountLinesWise;
    
    private List<ProductsLine> prdtWiseInternal;
    private List<ProductsLine> prdtWiseBilling;
    private final static String[] SALESHEADERS = {"BillNO", "Customer", "BillAmount", "Tax", "Total"};
    private String pdtname;
    private int qty;
    private double rate;
    private double amount;
    private double amttotal;
    private double taxtotal;
    private double pdtamttotal;
    private double pendingamt;   
    private String customer;
    private String waiter;   
    private Timestamp created_date;    
    private final static String[] PRODUCTHEADERS = {"Product", "Qty", "Rate", "Amount"};

    private ConsumableSalesModel() {
    }

    public static ConsumableSalesModel emptyInstance() {

        ConsumableSalesModel p = new ConsumableSalesModel();
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
        p.customer = new String();
        p.waiter = new String();
        p.productsaccount = new ArrayList<ProductsAccountLine>();
        p.productsaccountbilling = new ArrayList<ProductsAccountLine>();
        p.categoriesTotal = new ArrayList<ProductsCategoryLine>();       
        p.created_date = new java.sql.Timestamp(00 - 00 - 00);
        p.m_billinfo = new ArrayList<SalesLine>();
        p.prdtWise = new ArrayList<ProductsLine>();    
        p.prdtWiseInternal = new ArrayList<ProductsLine>();    
        p.prdtWiseBilling = new ArrayList<ProductsLine>();    
        p.m_iPayments = new Integer(0);
        p.m_dPaymentsTotal = new Double(0.0); 
        p.m_iSales = null;
        p.m_dSalesBase = null;
        p.m_dSalesTaxes = null;
        p.m_lsales = new ArrayList<SalesLine>();
        p.m_billinfoInternal = new ArrayList<SalesLine>();
        p.m_billinfoBilling = new ArrayList<SalesLine>();
        p.pendingamt = 0.0;
        return p;
    }

    private static void calCategorywise(List<ProductsCategoryLine> list1, ConsumableSalesModel p) throws BasicException {
        String pid = null;
        Double amt = 0.0;
        ProductsCategoryLine line1 = new ProductsCategoryLine();
        List<ProductsCategoryLine> list2 = new ArrayList<ProductsCategoryLine>();
        for (ProductsCategoryLine line : list1) {
            Object[] obj = (Object[]) new StaticSentence(p.m_App.getSession(), "SELECT C.PARENTID,(SELECT C1.NAME FROM CATEGORIES C1 WHERE C1.ID=C.PARENTID),(SELECT C1.PARENTID FROM CATEGORIES C1 WHERE C1.ID=C.PARENTID) FROM CATEGORIES C WHERE C.ID=?",
                    SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(line.getCid());
            if (obj != null) {
                if (obj[2] == null) {
                    int flag = 0;
                    int i = 0;
                    for (ProductsCategoryLine line2 : p.categoriesTotal) {
                        if (line2.getCid().equals(line.getCid())) {
                            p.categoriesTotal.get(i).setAmount(line2.getAmount() + line.getAmount());
                            flag = 1;
                        }
                        i++;
                    }
                    if (flag == 0) {
                        p.categoriesTotal.add(line);
                    }
                } else {


                    if (pid != null && pid.equals(obj[0].toString())) {
                        amt += line.getAmount();
                    } else {
                        {
                            if (pid != null) {
                                line1.setAmount(amt);                                
                                list2.add(line1);
                                line1 = new ProductsCategoryLine();
                            }
                            line1.setCid(obj[0].toString());
                            line1.setCname(obj[1].toString());
                        }
                        pid = obj[0].toString();
                        amt = line.getAmount();
                    }
                }
            }

        }
        if (amt > 0) {
            line1.setAmount(amt);
            list2.add(line1);
        }
        if (list2.size() > 0) {
            calCategorywise(list2, p);
        }
    }

    private static void calculateCategoryWise(List<ProductsCategoryLine> list, ConsumableSalesModel p) throws BasicException {
        String pid = null;
        Double amt = 0.0;
        List<ProductsCategoryLine> list1 = new ArrayList<ProductsCategoryLine>();
        ProductsCategoryLine parent = new ProductsCategoryLine();
        for (ProductsCategoryLine pcline : list) {
            if (pcline.getParentid() != null) {
                if (pcline.getPParentid() != null) {
                    if (pid != null && pid.equals(pcline.getParentid())) {
                        amt += pcline.getAmount();
                    } else {                        
                        {
                            if (pid != null) {
                                parent.setAmount(amt);
                                list1.add(parent);
                                parent = new ProductsCategoryLine();
                            }
                            parent.setCid(pcline.getParentid());
                            parent.setCname(pcline.getParentName());
                        }
                        pid = pcline.getParentid();
                        amt = pcline.getAmount();
                    }
                } else {
                    p.categoriesTotal.add(pcline);
                }                
            } else {
                p.categoriesTotal.add(pcline);
            }

        }
        if (amt > 0) {
            parent.setAmount(amt);
            list1.add(parent);
        }
        if (list1.size() > 0) {
            calCategorywise(list1, p);
        }       
    }

    public List<TaxLine> getTaxLines() {
        return taxLines;
    }

    public void setTaxLines(List<TaxLine> taxLines) {
        this.taxLines = taxLines;
    }

    public double getTaxtotal() {
        return taxtotal;
    }

    public void setTaxtotal(double taxtotal) {
        this.taxtotal = taxtotal;
    }
    private AppView m_App;

    public static ConsumableSalesModel loadInstance(AppView app, Date d) throws BasicException {

        ConsumableSalesModel p = new ConsumableSalesModel();
        p.m_App = app;        
        Object[] seqobj = (Object[]) new StaticSentence(app.getSession(), "SELECT MAX(SEQUENCE) FROM CLOSEDSALECONSUME WHERE ROLE = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(app.getAppUserView().getUser().getRole());
        if (seqobj == null || seqobj[0] == null) {
            p.m_iSeq = 1;
        } else {
            p.m_iSeq = Integer.parseInt(seqobj[0].toString()) + 1;
        }
        p.m_sHost = app.getProperties().getHost();       
        p.m_dDateStart = app.getAppUserView().getUser().getOpenSaleTime();
        p.m_dDateEnd = null;
        p.pdtamttotal = 0.0;
        p.taxtotal = 0.0;
        p.amttotal = 0.0;
        p.totaltax = 0.0;    


        
        Object[] valtickets = (Object[]) new StaticSentence(app.getSession(), "SELECT SUM(AMOUNT), SUM(TAXAMOUNT) "
                + "FROM CPBILL "
                + "WHERE   CPBILL.CLOSESALESEQ IS NULL AND CPBILL.CREATEDDATE <= ? AND cpbill.createdby = ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (valtickets[0] == null && valtickets[1] != null) {
            p.billtotal = 0.0;
            p.totaltax = (Double) valtickets[1];
        } else if (valtickets[0] != null && valtickets[1] == null) {
            p.billtotal = (Double) valtickets[0];
            p.totaltax = 0.0;
        } else if (valtickets[0] == null && valtickets[1] == null) {
            p.billtotal = 0.0;
            p.totaltax = 0.0;
        } else {
            p.billtotal = (Double) valtickets[0];
            p.totaltax = (Double) valtickets[1];
        }
        
        List salebillwise = new StaticSentence(app.getSession(), 
                "SELECT BID,CORMID,AMOUNT,TAXAMOUNT,SEARCHKEYORDEPTNAME,BILLTYPE,PTYPE,CRDATE FROM "
                + " (SELECT DISTINCT CPBILL.ID AS BID, CPBILL.MEMID AS CORMID, CPBILL.AMOUNT AS AMOUNT, CPBILL.TAXAMOUNT AS TAXAMOUNT, CUSTOMERS.SEARCHKEY AS  SEARCHKEYORDEPTNAME,CPBILL.BILLTYPE AS BILLTYPE,CPBILL.PAYMENTTYPE AS PTYPE,CPBILL.CREATEDDATE AS CRDATE "
                + " FROM CPBILL, CUSTOMERS  "
                + " WHERE  CPBILL.CLOSESALESEQ IS NULL AND CPBILL.MEMID=CUSTOMERS.ID  AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY  = ? "
                + " UNION ALL "
                + " SELECT DISTINCT CPBILL.ID AS BID, CPBILL.DEPTID AS CORMID, CPBILL.AMOUNT AS AMOUNT, CPBILL.TAXAMOUNT AS TAXAMOUNT, DEPARTMENT.NAME AS  SEARCHKEYORDEPTNAME,CPBILL.BILLTYPE AS BILLTYPE,CPBILL.PAYMENTTYPE AS PTYPE,CPBILL.CREATEDDATE AS CRDATE   "
                + " FROM CPBILL, DEPARTMENT  "
                + " WHERE  CPBILL.CLOSESALESEQ IS NULL AND  CPBILL.DEPTID=DEPARTMENT.ID  AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY  = ? ) as cpbills  GROUP BY BID ORDER BY CRDATE,BID", 
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING,Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.SalesLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId(),d, app.getAppUserView().getUser().getId()});

        if (salebillwise == null) {
            p.m_billinfo = new ArrayList();
        } else {
            p.m_billinfo = salebillwise;
        }
        
        List salebillwiseBilling = new StaticSentence(app.getSession(), 
                "SELECT BID,CORMID,AMOUNT,TAXAMOUNT,SEARCHKEYORDEPTNAME,BILLTYPE,PTYPE,CRDATE FROM "
                + " (SELECT DISTINCT CPBILL.ID AS BID, DEPARTMENT.NAME AS CORMID, CPBILL.AMOUNT AS AMOUNT, CPBILL.TAXAMOUNT AS TAXAMOUNT, DEPARTMENT.NAME AS  SEARCHKEYORDEPTNAME,CPBILL.BILLTYPE AS BILLTYPE,CPBILL.PAYMENTTYPE AS PTYPE,CPBILL.CREATEDDATE AS CRDATE  "
                + " FROM CPBILL,  DEPARTMENT  "
                + " WHERE  CPBILL.CLOSESALESEQ IS NULL AND   CPBILL.DEPTID=DEPARTMENT.ID  AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY  = ? ) as bill", 
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.SalesLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (salebillwise == null) {
            p.m_billinfoBilling = new ArrayList();
        } else {
            p.m_billinfoBilling = salebillwiseBilling;
        }
        
        
        List taxCategorywise = new StaticSentence(app.getSession(), "SELECT TC.ID,TC.ACCOUNT,SUM(TL.AMOUNT),TC.NAME "
                + "FROM CPBILL,TAXLINES TL,TAXES T,taxcategories TC "
                + "WHERE  CPBILL.CLOSESALESEQ IS NULL AND CPBILL.CREATEDDATE <=? AND  CPBILL.CREATEDBY=?  AND CPBILL.ID=TL.RECEIPT AND TL.TAXID=T.ID AND T.CATEGORY=TC.ID group by TC.ID,TC.ACCOUNT,TC.NAME",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.TaxLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (taxCategorywise == null) {
            p.taxLines = new ArrayList<TaxLine>();
        } else {
            p.taxLines = taxCategorywise;
        }
        p.taxtotal = 0.0;
        for (TaxLine tax : p.taxLines) {
            p.taxtotal += tax.getAmount();
        }       

        List<ProductsCategoryLine> categorywise = new StaticSentence(app.getSession(), "SELECT  C.ID,C.NAME,SUM(CPBILLITEM.RATE * CPBILLITEM.QTY),C.PARENTID ,(SELECT C1.PARENTID FROM CATEGORIES C1  WHERE C1.ID=C.PARENTID),(SELECT C1.NAME FROM CATEGORIES C1  WHERE C1.ID=C.PARENTID) "
                + "FROM CPBILL, PEOPLE ,PRODUCTS,CPBILLITEM,CATEGORIES C "
                + "WHERE   CPBILL.CLOSESALESEQ IS NULL AND  CPBILL.ID=CPBILLITEM.BILLID AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <=?   AND CPBILL.CREATEDBY = ? AND PRODUCTS.CATEGORY=C.ID  GROUP BY PRODUCTS.CATEGORY,C.NAME,C.PARENTID,C.ID ORDER BY C.PARENTID", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsCategoryLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (categorywise == null) {
            p.categoriesTotal = new ArrayList<ProductsCategoryLine>();
        } else {
            p.categoriesTotal = new ArrayList<ProductsCategoryLine>();
            calculateCategoryWise(categorywise, p);
        }

      //  List productaccountwise = new StaticSentence(app.getSession(), "SELECT PRODUCTS.SACCOUNT,PRODUCTS.PACCOUNT,SUM(CPBILLITEM.RATE * CPBILLITEM.QTY),PRODUCTS.LOCATION "
       //         + "FROM CPBILL ,PRODUCTS,CPBILLITEM "
      //          + "WHERE   CPBILL.CLOSESALESEQ IS NULL AND CPBILL.ID=CPBILLITEM.BILLID AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <=?   AND CPBILL.CREATEDBY  = ? and cpbill.billtype=0 GROUP BY PRODUCTS.LOCATION,PRODUCTS.SACCOUNT", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsAccountLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
      //  if (productaccountwise == null) {
      //      p.productsaccount = new ArrayList<ProductsAccountLine>();
      //  } else {
      //      p.productsaccount = productaccountwise;
      //  }
// EDITED BY AKASH  
        
         List productaccountwise = new StaticSentence(app.getSession(), "SELECT C.ACCID,PRODUCTS.SACCOUNT,sum(CPBILLITEM.RATE * CPBILLITEM.QTY),PRODUCTS.LOCATION \n" +
                                                                        "FROM CPBILL ,PRODUCTS,CPBILLITEM , CONSPRDACC C  \n" +
                                                                        "WHERE   CPBILL.CLOSESALESEQ IS NULL AND CPBILL.ID=CPBILLITEM.BILLID \n" +
                                                                        "AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <= ?  AND C.ACTIVE=1  \n" +
                                                                        "AND CPBILL.CREATEDBY  = ?\n" +
                                                                        "and cpbill.billtype=0 and c.proid=CPBILLITEM.PRODUCTID AND C.DEPTID=CPBILL.DEPTID\n" +
                                                                        "GROUP BY PRODUCTS.LOCATION,C.ACCID , PRODUCTS.SACCOUNT", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsAccountLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (productaccountwise == null) {
            p.productsaccount = new ArrayList<ProductsAccountLine>();
        } else {
            p.productsaccount = productaccountwise;
        }
        
// FOR ACCOUNTING ENTRIES . ................................ 
        
 /////////////////////////////////////////////////////////////////////////////////////////////////////////
        
       List AccountwiseEntries = new StaticSentence(app.getSession(), "SELECT ACCOUNTMASTER.NAME ,A1.NAME,sum(CPBILLITEM.RATE * CPBILLITEM.QTY),PRODUCTS.LOCATION \n" +
                                                                        "FROM CPBILL ,PRODUCTS,CPBILLITEM , CONSPRDACC C , ACCOUNTMASTER , ACCOUNTMASTER A1\n" +
                                                                        "WHERE   CPBILL.CLOSESALESEQ IS NULL AND CPBILL.ID=CPBILLITEM.BILLID \n" +
                                                                        "AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <= ?   \n" +
                                                                        "AND CPBILL.CREATEDBY  = ?   \n" +
                                                                        "and cpbill.billtype=0 and c.proid=CPBILLITEM.PRODUCTID AND C.DEPTID=CPBILL.DEPTID\n" +
                                                                        "and ACCOUNTMASTER.id=C.ACCID AND A1.ID=PRODUCTS.SACCOUNT\n" +
                                                                        "GROUP BY PRODUCTS.LOCATION,C.ACCID , PRODUCTS.SACCOUNT", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsAccountLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (AccountwiseEntries == null) {
            p.AccountLinesWise = new ArrayList<AccountLines>();
        } else {
            p.AccountLinesWise = AccountwiseEntries;
        }  
        
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        
        
        
        List productaccountwisebill = new StaticSentence(app.getSession(), "SELECT PRODUCTS.SACCOUNT,PRODUCTS.PACCOUNT,SUM(CPBILLITEM.RATE * CPBILLITEM.QTY),PRODUCTS.LOCATION "
                + "FROM CPBILL ,PRODUCTS,CPBILLITEM "
                + "WHERE   CPBILL.CLOSESALESEQ IS NULL AND CPBILL.ID=CPBILLITEM.BILLID AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <=?   AND CPBILL.CREATEDBY  = ?  and cpbill.billtype=1  GROUP BY PRODUCTS.LOCATION,PRODUCTS.SACCOUNT", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsAccountLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (productaccountwisebill == null) {
            p.productsaccountbilling = new ArrayList<ProductsAccountLine>();
        } else {
            p.productsaccountbilling = productaccountwisebill;
        }

        List productwarehousewise = new StaticSentence(app.getSession(), "SELECT null,null,SUM(CPBILLITEM.RATE * CPBILLITEM.QTY),PRODUCTS.LOCATION "
                + "FROM CPBILL, PEOPLE ,PRODUCTS,CPBILLITEM "
                + "WHERE   CPBILL.CLOSESALESEQ IS NULL  AND CPBILL.ID=CPBILLITEM.BILLID AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <=?   AND CPBILL.CREATEDBY  = ?  and cpbill.billtype=1 GROUP BY PRODUCTS.LOCATION", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsAccountLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (productwarehousewise == null) {
            p.productsWarerhouseTotalInternal = new ArrayList<ProductsAccountLine>();
        } else {
            p.productsWarerhouseTotalInternal = productwarehousewise;
        }  
        
        List productwarehousewise1 = new StaticSentence(app.getSession(), "SELECT null,null,SUM(CPBILLITEM.RATE * CPBILLITEM.QTY),PRODUCTS.LOCATION "
                + "FROM CPBILL, PEOPLE ,PRODUCTS,CPBILLITEM "
                + "WHERE   CPBILL.CLOSESALESEQ IS NULL  AND CPBILL.ID=CPBILLITEM.BILLID AND CPBILLITEM.PRODUCTID=PRODUCTS.ID  AND CPBILL.CREATEDDATE <=?   AND CPBILL.CREATEDBY  = ?  and cpbill.billtype=1 GROUP BY PRODUCTS.LOCATION", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsAccountLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (productwarehousewise1 == null) {
            p.productsWarerhouseTotalBilling = new ArrayList<ProductsAccountLine>();
        } else {
            p.productsWarerhouseTotalBilling = productwarehousewise1;
        }  
        
        
        p.pendingamt = 0.0;
         Object[] x = (Object[]) new StaticSentence(app.getSession(), "SELECT SUM(CPBILLITEM.QTY * CPBILLITEM.RATE) "
                + "FROM CPBILL, CPBILLITEM,  PRODUCTS  "
                + "WHERE  CPBILL.CLOSESALESEQ IS NULL   AND PRODUCTS.ID = CPBILLITEM.PRODUCTID  AND CPBILL.ID = CPBILLITEM.BILLID AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY  = ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (x[0] == null) {
            p.amttotal = 0.0;
        } else {
            p.amttotal = (Double) x[0];
        }

        Object[] y = (Object[]) new StaticSentence(app.getSession(), "SELECT SUM(CPBILL.TAXAMOUNT) "
                + "FROM CPBILL  "
                + "WHERE   CPBILL.CLOSESALESEQ IS NULL  AND CPBILL.CREATEDDATE <=?   AND CPBILL.CREATEDBY = ?  ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{d, app.getAppUserView().getUser().getId()});
        if (y[0] == null) {
            p.taxtotal = 0.0;
        } else {
            p.taxtotal = (Double) y[0];
        }

        p.pdtamttotal = p.taxtotal + p.amttotal;

        List pdtwise = new StaticSentence(app.getSession(), "SELECT PRODUCTS.NAME,SUM(QTY),CPBILLITEM.RATE ,SUM(CPBILLITEM.QTY * CPBILLITEM.RATE),PRODUCTS.ID,PRODUCTS.CATEGORY,products.inventrymaintain  "
                + "FROM CPBILLITEM,PRODUCTS,CPBILL "
                + "WHERE CPBILLITEM.BILLID = CPBILL.ID AND PRODUCTS.ID = CPBILLITEM.PRODUCTID AND "
                + "CPBILL.CLOSESALESEQ IS NULL AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY = ? "
                + "GROUP BY PRODUCTS.ID ,PRODUCTS.NAME , CPBILLITEM.RATE,PRODUCTS.CATEGORY,PRODUCTS.ID "
                + "ORDER BY PRODUCTS.CATEGORY", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (pdtwise == null) {
            p.prdtWise = new ArrayList();
        } else {
            p.prdtWise = pdtwise;
        }       
        
//  GET DEPARTMENT WISE SALE REPORT
///////////////////////////////////////////////////////////////////////////////////////////        
         List DeptWise = new StaticSentence(app.getSession(), "SELECT PRODUCTS.NAME,SUM(QTY),CPBILLITEM.RATE ,SUM(CPBILLITEM.QTY * CPBILLITEM.RATE),\n" +
                                                                "PRODUCTS.ID,PRODUCTS.CATEGORY,products.inventrymaintain , DEPARTMENT.NAME \n" +
                                                                "FROM CPBILLITEM,PRODUCTS,CPBILL , DEPARTMENT\n" +
                                                                "WHERE CPBILLITEM.BILLID = CPBILL.ID AND PRODUCTS.ID = CPBILLITEM.PRODUCTID AND \n" +
                                                                "CPBILL.CLOSESALESEQ IS NULL AND CPBILL.CREATEDDATE <= ? AND \n" +
                                                                "CPBILL.CREATEDBY = ? \n" +
                                                                "AND CPBILLITEM.DEPTID=DEPARTMENT.ID\n" +
                                                                "GROUP BY   DEPARTMENT.ID , PRODUCTS.ID\n" +
                                                                "ORDER BY DEPARTMENT.NAME", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.DepartmentLines.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (DeptWise == null) {
            p.DepartmentWise = new ArrayList();
        } else {
            p.DepartmentWise = DeptWise;
        }       
        
 ///////////////////////////////////////////////////////////////////////////////////////////       
        
        
        
        
        List pdtwiseinternal = new StaticSentence(app.getSession(), "SELECT PRODUCTS.NAME,SUM(QTY),CPBILLITEM.RATE ,SUM(CPBILLITEM.QTY * CPBILLITEM.RATE),PRODUCTS.ID,PRODUCTS.CATEGORY,products.inventrymaintain  "
                + "FROM CPBILLITEM,PRODUCTS,CPBILL "
                + "WHERE CPBILLITEM.BILLID = CPBILL.ID AND PRODUCTS.ID = CPBILLITEM.PRODUCTID AND "
                + "CPBILL.CLOSESALESEQ IS NULL AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY = ? AND CPBILL.BILLTYPE=0 "
                + "GROUP BY PRODUCTS.ID ,PRODUCTS.NAME , CPBILLITEM.RATE,PRODUCTS.CATEGORY,PRODUCTS.ID "
                + "ORDER BY PRODUCTS.CATEGORY", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (pdtwiseinternal == null) {
            p.prdtWiseInternal = new ArrayList();
        } else {
            p.prdtWiseInternal = pdtwiseinternal;
        }       
        
        List pdtwiseBilling = new StaticSentence(app.getSession(), "SELECT PRODUCTS.NAME,SUM(QTY),CPBILLITEM.RATE ,SUM(CPBILLITEM.QTY * CPBILLITEM.RATE),PRODUCTS.ID,PRODUCTS.CATEGORY,products.inventrymaintain  "
                + "FROM CPBILLITEM,PRODUCTS,CPBILL "
                + "WHERE CPBILLITEM.BILLID = CPBILL.ID AND PRODUCTS.ID = CPBILLITEM.PRODUCTID AND "
                + "CPBILL.CLOSESALESEQ IS NULL AND CPBILL.CREATEDDATE <=?  AND CPBILL.CREATEDBY = ? AND CPBILL.BILLTYPE=1 "
                + "GROUP BY PRODUCTS.ID ,PRODUCTS.NAME , CPBILLITEM.RATE,PRODUCTS.CATEGORY,PRODUCTS.ID "
                + "ORDER BY PRODUCTS.CATEGORY", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ConsumableSalesModel.ProductsLine.class)).list(new Object[]{d, app.getAppUserView().getUser().getId()});

        if (pdtwiseBilling == null) {
            p.prdtWiseBilling = new ArrayList();
        } else {
            p.prdtWiseBilling = pdtwiseBilling;
        }       
        return p;

    }
    

    public String getbillno() {
        return m_billno;
    }

    public List<ProductsCategoryLine> getCategoryTotals() {
        return categoriesTotal;
    }

    public String getCustomer() {
        String x = new String();
        try {
            x = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(customername).getName().toString();
        } catch (BasicException e) {
            new MessageInf(e).notify();
        }
        return x;
    }    

    public List<ProductsAccountLine> getprodutsAccountwiseTotal() {
        return productsaccount;
    }

  
    
    public List<ProductsAccountLine> getProductsWarerhouseTotalInternal() {
        return productsWarerhouseTotalInternal;
    }

    public List<ProductsAccountLine> getProductsWarerhouseTotalBilling() {
        return productsWarerhouseTotalBilling;
    }
    
    

    public List<ProductsAccountLine> getProductsaccountbilling() {
        return productsaccountbilling;
    }
    
    

    public List<ProductsLine> getProductLine() {
        return prdtWise;
    }
     public List<DepartmentLines> getDepartmentLine() {
        return DepartmentWise;
    }

    public List<AccountLines> getAccountLines() {
        return AccountLinesWise;
    }
     
    public List<ProductsLine> getPrdtWiseBilling() {
        return prdtWiseBilling;
    }

    public List<ProductsLine> getPrdtWiseInternal() {
        return prdtWiseInternal;
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
        return billtotal;
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

    public String printPendingamt() {
        return Formats.CURRENCY.formatValue(pendingamt);
    }

    public String printsubbill() {
        return Formats.CURRENCY.formatValue(billtotal);
    }

    public String printsubtax() {
        //return Formats.CURRENCY.formatValue(totaltax);
        return Formats.CURRENCY.formatValue(0);
    }

    public double getTotaltax() {
        return totaltax;
    }

    public String printTotals() {
       // return Formats.CURRENCY.formatValue(billtotal + totaltax);
        return Formats.CURRENCY.formatValue(billtotal );
    }

    public Double getTotals() {
        return billtotal ;
    }

    public String printpdtamount() {
        return Formats.CURRENCY.formatValue(amttotal);
    }

    public String printtaxamt() {
       // return Formats.CURRENCY.formatValue(taxtotal);
        return Formats.CURRENCY.formatValue(0);
    }

    public String printtotalamt() {
      //  return Formats.CURRENCY.formatValue(pdtamttotal);
         return Formats.CURRENCY.formatValue(amttotal);
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

    public List<ProductsLine> getProductsLines() {
        return prdtWise;
    }
     public List<DepartmentLines> getDepartmentwiseLines() {
        return DepartmentWise;
    }

    public List<SalesLine> getSalesLines() {
        return m_billinfo;
    }
   
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

    public List<SalesLine> getSaleLines() {
        return m_lsales;
    }

    public List<SalesLine> getM_billinfoBilling() {
        return m_billinfoBilling;
    }
    
    

    public AbstractTableModel getProductsModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {               
                return PRODUCTHEADERS[column];
            }

            public int getRowCount() {
                return prdtWise.size();
            }

            public int getColumnCount() {
                return PRODUCTHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                ProductsLine l = prdtWise.get(row);
                switch (column) {
                    case 0:
                        return l.getproduct();
                    case 1:
                        return l.getqty();
                    case 2:
                        return l.getRate();
                    case 3:                      
                        return l.getAmount();
                    default:
                        return null;
                }
            }
        };
    }

    
    public static class ProductsAccountLine implements SerializableRead {

        private String salAccName;
        private String purAccName;
        private Double amount;
        private String location;

        public void readValues(DataRead dr) throws BasicException {
            salAccName = dr.getString(1);
            purAccName = dr.getString(2);
            amount = dr.getDouble(3);
            location = dr.getString(4);
        }

        public String getPurAccName() {
            return purAccName;
        }

        public String getAccount() {
            return salAccName;
        }

        public Double getAmount() {
            return amount;
        }

        public String getLocation() {
            return location;
        }
    }
    //C.ID,C.NAME,SUM(BILLITEM.RATE * BILLITEM.DMULTIPLY),C.PARENTID ,(SELECT C1.PARENTID FROM CATEGORIES C1  WHERE C1.ID=C.PARENTID),(SELECT C1.NAME FROM CATEGORIES C1  WHERE C1.ID=C.PARENTID)

    public static class ProductsCategoryLine implements SerializableRead {

        private String cid;
        private String cname;
        private Double amount;
        private String parentid;
        private String pparentid;
        private String parentname;

        public void readValues(DataRead dr) throws BasicException {
            cid = dr.getString(1);
            cname = dr.getString(2);
            amount = dr.getDouble(3);
            parentid = dr.getString(4);
            pparentid = dr.getString(5);
            parentname = dr.getString(6);
        }

        public String getCid() {
            return cid;
        }

        public String getCname() {
            return cname;
        }

        public String printCname() {
            return StringUtils.encodeXML(cname);
        }

        public Double getAmount() {
            return amount;
        }

        public String getParentid() {
            return parentid;
        }

        public String getPParentid() {
            return pparentid;
        }

        public String getParentName() {
            return parentname;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public void setAmount(Double amt) {
            amount = amt;
        }

        public void setParentid(String pid) {
            parentid = pid;
        }

        public void setParentName(String pname) {
            parentname = pname;
        }

        public void setPParentid(String pid) {
            pparentid = pid;
        }
    }

    public static class TaxLine implements SerializableRead {

        private String taxCategoryId;
        private String accountId;
        private double amount;
        private String name;

        public void readValues(DataRead dr) throws BasicException {
            taxCategoryId = dr.getString(1);
            accountId = dr.getString(2);
            amount = dr.getDouble(3);
            name = dr.getString(4);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getTaxCategoryId() {
            return taxCategoryId;
        }

        public void setTaxCategoryId(String taxCategoryId) {
            this.taxCategoryId = taxCategoryId;
        }

        public String printName() {
            return StringUtils.encodeXML(name);
        }

        public String printAmount() {
            return Formats.CURRENCY.formatValue(amount);
        }
    }

    public static class SalesLine implements SerializableRead {

        private String billno;
        private String customer;
        private String customerSKey;
        private double billamount;
        private double taxamount;
        private double total;
        private int billtype;   
        private String paymentType;
        private Date crDate; 

        public void readValues(DataRead dr) throws BasicException {
            billno = dr.getString(1);
            customer = dr.getString(2);
            billamount = dr.getDouble(3);
            taxamount = dr.getDouble(4);
            customerSKey = dr.getString(5);
            billtype = dr.getInt(6);
            paymentType = dr.getString(7);
            crDate = dr.getTimestamp(8);
            total = billamount + taxamount;
            
        }

        public Date getCrDate() {
            return crDate;
        }

        public void setCrDate(Date crDate) {
            this.crDate = crDate;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }
        
        

        public int getBilltype() {
            return billtype;
        }

        public void setBilltype(int billtype) {
            this.billtype = billtype;
        }

        public String getCustomerSKey() {
            return customerSKey;
        }

        public void setCustomerSKey(String customerSKey) {
            this.customerSKey = customerSKey;
        }

        public String printbillno() {
            return billno;
        }

        public String printTaxamt() {
            return Formats.CURRENCY.formatValue(taxamount);
        }

        public String printcustomer() {

            return StringUtils.encodeXML(customerSKey);
        }

        public String printbillamt() {
            return Formats.CURRENCY.formatValue(billamount);
        }

        public String printTotal() {
            return Formats.CURRENCY.formatValue(total);
        }

        public String getbillno() {
            return billno;
        }

        public String getcustomer() {           
            return customer;
        }

        public Double getbillamount() {
            return billamount;
        }

        public Double gettaxamount() {
            return taxamount;
        }

        public Double getTotal() {
            return total;
        }
       
    }
    
    public AbstractTableModel getSalesModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {                
                return SALESHEADERS[column];
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
                    case 0:
                        return l.getbillno();
                    case 1:
                        return l.getCustomerSKey();
                    case 2:
                        return l.getbillamount();
                    case 3:
                        return l.gettaxamount();
                    case 4:
                        return l.getTotal();
                    default:
                        return null;
                }
            }
        };
    }

    public static class ProductsLine implements SerializableRead {

        private String product;
        private int qty;
        private double rate;
        private double amt;
        private String pdtid;
        private String pdtcategory;
        private double tax = 0.0;
        private double totalamt = 0.0;
        private boolean maintainInventory;

        public void readValues(DataRead dr) throws BasicException {
            product = dr.getString(1);
            qty = dr.getInt(2);
            rate = dr.getDouble(3);
            //c  amt = qty * rate;
            amt = dr.getDouble(4);
            pdtid = dr.getString(5);
            pdtcategory = dr.getString(6);
            maintainInventory = dr.getBoolean(7);
        }

        public boolean isMaintainInventory() {
            return maintainInventory;
        }

        public String getpdtid() {
            return pdtid;
        }

        public String getpdtcategory() {
            return pdtcategory;
        }

        public String printproduct() {
            return StringUtils.encodeXML(product);
        }

        public String getproduct() {
            return product;
        }

        public int getqty() {
            return qty;
        }

        public String printQty() {
            return Formats.INT.formatValue(qty);
        }

        public Double getRate() {
            return rate;
        }

        public String printRate() {
            return Formats.CURRENCY.formatValue(rate);
        }

        public Double getAmount() {
            return amt;
        }

        public String printAmount() {
            return Formats.CURRENCY.formatValue(amt);
        }

        public Double getTax() {
            return tax;
        }

        public String printTax() {
            return Formats.CURRENCY.formatValue(tax);
        }

        public Double getTotal() {
            return totalamt;
        }

        public String printTotal() {
            return Formats.CURRENCY.formatValue(totalamt);
        }
    }
    
    
    
    public static class DepartmentLines implements SerializableRead {

        private String product;
        private int qty;
        private double rate;
        private double amt;
        private String pdtid;
        private String pdtcategory;
        private double tax = 0.0;
        private double totalamt = 0.0;
        private boolean maintainInventory;
        private String Department;
        
        public void readValues(DataRead dr) throws BasicException {
            product = dr.getString(1);
            qty = dr.getInt(2);
            rate = dr.getDouble(3);
            //c  amt = qty * rate;
            amt = dr.getDouble(4);
            pdtid = dr.getString(5);
            pdtcategory = dr.getString(6);
            maintainInventory = dr.getBoolean(7);
            Department = dr.getString(8);
        }

        public boolean isMaintainInventory() {
            return maintainInventory;
        }

        public String getpdtid() {
            return pdtid;
        }

        public String getpdtcategory() {
            return pdtcategory;
        }

        public String printproduct() {
            return StringUtils.encodeXML(product);
        }

        public String getproduct() {
            return product;
        }

        public int getqty() {
            return qty;
        }

        public String printQty() {
            return Formats.INT.formatValue(qty);
        }

        public Double getRate() {
            return rate;
        }

        public String printRate() {
            return Formats.CURRENCY.formatValue(rate);
        }

        public Double getAmount() {
            return amt;
        }

        public String printAmount() {
            return Formats.CURRENCY.formatValue(amt);
        }

        public Double getTax() {
            return tax;
        }

        public String printTax() {
            return Formats.CURRENCY.formatValue(tax);
        }

        public Double getTotal() {
            return totalamt;
        }

        public String printTotal() {
            return Formats.CURRENCY.formatValue(totalamt);
        }
        
         public String printDepartmentName() {
           return StringUtils.encodeXML(Department);
        }
    }
    
    
    
    
     public static class AccountLines implements SerializableRead {

        private String DebitAccName;
        private double Credit;
        private double Debit;
        private String CreditAccName;
        private String Location;
        public void readValues(DataRead dr) throws BasicException {
            DebitAccName = dr.getString(1);
            CreditAccName = dr.getString(2);
            Credit = dr.getDouble(3);
            Debit = dr.getDouble(3);
            Location = dr.getString(4);
           
        }

        public String getDebitAccName() {
            return DebitAccName;
        }
        
        
        public String getCreditAccName() {
            return CreditAccName;
        }
        
        
        public Double getCredit() {
            return Credit;
        }

       

        public Double getDebit() {
            return Debit;
        }

        public String printDebitname() {
             return StringUtils.encodeXML(DebitAccName);
        }
        public String printCreditName() {
             return StringUtils.encodeXML(CreditAccName);
        }
        
        public String PringCreditAmt() {
             return Formats.CURRENCY.formatValue(Credit);
        }
        public String printDebtAmt() {
            return Formats.CURRENCY.formatValue(Debit);
        }
     }
       
    
    
    
    
    
}
