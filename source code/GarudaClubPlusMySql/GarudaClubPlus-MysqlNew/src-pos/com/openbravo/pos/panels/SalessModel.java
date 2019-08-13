

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
public class SalessModel  {

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
    private java.util.List<discountlist> disQtlist;
    private java.util.List<discountlist> disBilllist;
    private java.util.List<discountlist> disBillliststockentry;
    private java.util.List<discountlist> disQtliststockentry;
    private java.util.List<ProductsAccountLine> productsWarerhouseTotal;
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
    private List<ProductsLine> m_blf;
    private List<QTLine> qt_info;
    
    private final static String[] SALESHEADERS = {"BillNO", "Customer", "BillAmount","Tax", "Total" };
    
    private String pdtname;
    private int qty;
    private double rate;
    private double amount;
    private double amttotal;
    private double taxtotal;
    private double pdtamttotal;
    private double pendingamt;


     private final static String[] QTHEADERS={"QT ID","CUSTOMER","WAITER","VALUE","CREATED DATE","REASON"};

      private String qtId;
      private String customer;
      private String waiter;
     
      private Double qtvalue;
      private Timestamp created_date;

      private final static String[] DISCOUNTHEADERS={"ID","PRODUCT","QTY","RATE","CREATED BY","REASON"};
    private final static String[] PRODUCTHEADERS = {"Product", "Qty", "Rate", "Amount"};


    private SalessModel() {
    }    
    
    public static SalessModel emptyInstance() {
        
        SalessModel p = new SalessModel();

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
        p.qtId=new String();
        p.customer=new String();
        p.waiter=new String();
        p.productsaccount=new ArrayList<ProductsAccountLine>();
        p.categoriesTotal=new ArrayList<ProductsCategoryLine>();
       // p.table=new String();
       // p.floor=new String();
          p.qtvalue=0.0;
         p.created_date = new java.sql.Timestamp(00-00-00);
        p.m_billinfo = new ArrayList<SalesLine>();
       p.m_blf = new ArrayList<ProductsLine>();
        p.qt_info=new ArrayList<QTLine>();
        p.disQtlist=new ArrayList<discountlist>();
        p.disBilllist=new ArrayList<discountlist>();
        p.m_iPayments = new Integer(0);
        p.m_dPaymentsTotal = new Double(0.0);
       // p.m_lpayments = new ArrayList<PaymentsLine>();
        
        p.m_iSales = null;
        p.m_dSalesBase = null;
        p.m_dSalesTaxes = null;
        p.m_lsales = new ArrayList<SalesLine>();
        p.pendingamt=0.0;
        
        return p;
    }
    private static void calCategorywise(List<ProductsCategoryLine> list1,SalessModel p) throws BasicException{
        String pid=null;
        Double amt=0.0;
        ProductsCategoryLine line1=new ProductsCategoryLine();
        List<ProductsCategoryLine> list2=new ArrayList<ProductsCategoryLine>();
        for(ProductsCategoryLine line:list1){
           Object[] obj=(Object[])new StaticSentence(p.m_App.getSession()
                        , "SELECT C.PARENTID,(SELECT C1.NAME FROM CATEGORIES C1 WHERE C1.ID=C.PARENTID),(SELECT C1.PARENTID FROM CATEGORIES C1 WHERE C1.ID=C.PARENTID) FROM CATEGORIES C WHERE C.ID=?",
                       SerializerWriteString.INSTANCE
                        ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).find(line.getCid());
          if(obj!=null){
           if(obj[2]==null){
               int flag=0;
               int i=0;
               for(ProductsCategoryLine line2:p.categoriesTotal){
                   if(line2.getCid().equals(line.getCid())){
                     p.categoriesTotal.get(i).setAmount(line2.getAmount()+line.getAmount());
                     flag=1;
                   }
                   i++;
               }
               if(flag==0)
                  p.categoriesTotal.add(line);
           }else{
              

              if(pid != null && pid.equals(obj[0].toString())){
                        amt+=line.getAmount();
                    }else{
                        {
                            if(pid!=null){
                          line1.setAmount(amt);
                         // for()
                          list2.add(line1);
                          line1=new ProductsCategoryLine();
                            }
                          line1.setCid(obj[0].toString());
                          line1.setCname(obj[1].toString());
                        }
                          pid=obj[0].toString();
                          amt=line.getAmount();
                    }
           }
        }
       
        }
         if(amt>0){
         line1.setAmount(amt);
         list2.add(line1);
        }
        if(list2.size()>0)
         calCategorywise(list2,p);
    }
    private static void calculateCategoryWise(List<ProductsCategoryLine> list,SalessModel p) throws BasicException{
              String pid=null;
              Double amt=0.0;
              List<ProductsCategoryLine> list1=new ArrayList<ProductsCategoryLine>();
              ProductsCategoryLine parent=new ProductsCategoryLine();
              for(ProductsCategoryLine pcline:list){
                if(pcline.getParentid()!=null){
                  if(pcline.getPParentid()!=null){
                    if(pid!=null && pid.equals(pcline.getParentid())){
                        amt+=pcline.getAmount();
                    }else{
                       // if(pid!=null){
                      //    parent.setCid(pid);
                     //     parent.setCname(pcline.getParentName());
                     //   }
                        {
                         if(pid!=null){
                          parent.setAmount(amt);
                          list1.add(parent);
                          parent=new ProductsCategoryLine();
                         }
                          parent.setCid(pcline.getParentid());
                          parent.setCname(pcline.getParentName());
                        }
                          pid=pcline.getParentid();
                          amt=pcline.getAmount();
                    }
                  }else{
                      p.categoriesTotal.add(pcline);
                  }
                    //list.remove(pcline);
                 }else{
                     p.categoriesTotal.add(pcline);
                 }

              }
              if(amt>0){
               parent.setAmount(amt);
                          list1.add(parent);
              }
              if(list1.size()>0)
               calCategorywise(list1,p);
              //return list;
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
    public static SalessModel loadInstance(AppView app,Date d) throws BasicException {
        
        SalessModel p = new SalessModel();
        p.m_App=app;
         p.disQtliststockentry=new ArrayList<discountlist>();
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
        p.m_dDateStart = app.getAppUserView().getUser().getOpenSaleTime1();
        p.m_dDateEnd = null;
        p.pdtamttotal=0.0;
        p.taxtotal=0.0;
        p.amttotal=0.0;
        p.totaltax=0.0;
       // p.billtotal=0.0;
    

        //sale bill wise
        String warehouse = null;
        String[] warehouses = null;
        Object[] params = null;
            Datas[] data = null;
            StringBuffer condition = new StringBuffer("");

        Object objwar = app.getAppUserView().getUser().getWarehouse();
        if (objwar != null) {
            warehouses = objwar.toString().split("#");
            warehouse = warehouses[0];
        }
        if (warehouses != null) {
             params = new Object[warehouses.length+1];
            data = new Datas[warehouses.length+1];
            data[0] = Datas.TIMESTAMP;
            params[0] = d;
           condition = new StringBuffer("");
            for (int j = 1; j < warehouses.length+1; j++) {
                data[j] = Datas.STRING;
                params[j] = warehouses[j-1].toString();
                condition.append(" ? , ");
            }
            if (condition.length() > 0) {
                condition.deleteCharAt(condition.lastIndexOf(","));
            }
        }

       Object[] valtickets = (Object []) new StaticSentence(app.getSession()
                , "SELECT SUM(BILL.AMOUNT), SUM(BILL.TAXTOTAL) " +
                "FROM BILL, PEOPLE,ROLES " +
                "WHERE  PEOPLE.NAME=BILL.CREATEDBY AND ROLES.ID=PEOPLE.ROLE AND  BILL.CLOSESALESEQ IS NULL AND BILL.CREATEDDATE <= ? AND bill.warehouse in ("+ condition +")" //ROLES.ID = ?  "
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                ,new SerializerReadBasic(new Datas[] { Datas.DOUBLE, Datas.DOUBLE})).find(params);//);
        if(valtickets[0] == null && valtickets[1]!= null ) {
            p.billtotal = 0.0;
            p.totaltax = (Double)valtickets[1];
        }
        else if(valtickets[0]!= null && valtickets[1] == null) {
            p.billtotal = (Double)valtickets[0];
            p.totaltax = 0.0;
        }
        else if(valtickets[0] == null && valtickets[1] == null) {
            p.billtotal = 0.0;
            p.totaltax = 0.0;
        }
        else {
            p.billtotal = (Double) valtickets[0];
            p.totaltax = (Double) valtickets[1];
        }
         Object[] objt=(Object[])new StaticSentence(app.getSession(), "SELECT MIN(OPENSALE) FROM PEOPLE WHERE ROLE=? AND CLOSESALE IS NULL"
                   ,SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(app.getAppUserView().getUser().getRole());
         if(objt!=null && objt[0]!=null){
         Date d1=(Date)objt[0];
         List disqt = new StaticSentence(app.getSession()
//                ,"SELECT D.QTITEMID,P.NAME,D.QTY,D.RATE,D.USER_ID,D.REASON,p.inventrymaintain "
//                +"FROM DISCOUNTLIST D,PRODUCTS P ,PEOPLE  P1,ROLES "
//                +"WHERE  ROLES.ID=P1.ROLE  AND P.ID=D.PRODUCT_ID AND D.AUTHORISED='true' AND  P1.NAME= D.USER_ID  AND D.CRDATE <= ? AND D.CRDATE >= ? and ROLES.ID = ?"
//                ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
               ,"SELECT D.QTITEMID,P.NAME,D.QTY,D.RATE,D.USER_ID,D.REASON,p.inventrymaintain "
                +"FROM DISCOUNTLIST D,PRODUCTS P  "
                +"WHERE  P.ID=D.PRODUCT_ID AND D.AUTHORISED='true' AND  D.CRDATE <= ? AND D.CRDATE >= ? and D.warehouse = ?"
                ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
                , new SerializerReadClass( SalessModel.discountlist.class )).list(new Object[]{d,d1,app.getAppUserView().getUser().getWarehouse()});


        if (disqt == null) {
            p.disQtlist = new ArrayList();
        }
        else {
            p.disQtlist = disqt;
        }
         }
          List disbill = new StaticSentence(app.getSession()
//                ,"SELECT R.BILLID,P.NAME,(R.QTY * -1),(R.RATE * -1),R.CREATEDBY,R.REASON,p.inventrymaintain  "
//                +"FROM REVERSEDBILL R,PRODUCTS P ,PEOPLE,ROLES "
//                +"WHERE   ROLES.ID=PEOPLE.ROLE AND P.ID=R.PRODUCT AND R.AUTHORISED=TRUE AND PEOPLE.NAME=R.CREATEDBY AND R.CRDATE <= ? AND ROLES.ID = ? AND R.CRDATE >= PEOPLE.OPENSALE "
                ,"SELECT R.BILLID,P.NAME,(R.QTY * -1),(R.RATE * -1),R.CREATEDBY,R.REASON,p.inventrymaintain  "
                +"FROM REVERSEDBILL R,PRODUCTS P ,BILL B "
                +"WHERE  P.ID=R.PRODUCT AND R.AUTHORISED=TRUE  AND R.CRDATE <= ? AND B.ID=R.BILLID AND B.WAREHOUSE = ? "
                ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                , new SerializerReadClass( SalessModel.discountlist.class )).list(new Object[]{d,app.getAppUserView().getUser().getWarehouse()});


        if (disbill == null) {
            p.disBilllist = new ArrayList();
        }
        else {
            p.disBilllist = disbill;
        }
           List disbillstk = new StaticSentence(app.getSession()
                ,"SELECT R.BILLID,P.ID,(R.QTY * -1),(R.RATE * -1),R.CREATEDBY,R.REASON,p.inventrymaintain  "
                +"FROM REVERSEDBILL R,PRODUCTS P ,PEOPLE,ROLES,BILL "
                +"WHERE   R.BILLID=BILL.ID AND BILL.CREATEDDATE <PEOPLE.OPENSALE AND  PEOPLE.NAME=R.CREATEDBY AND ROLES.ID=PEOPLE.ROLE AND P.ID=R.PRODUCT AND R.AUTHORISED=TRUE AND R.CRDATE <= ? and bill.warehouse in ("+ condition +") AND BILL.CLOSESALESEQ IS NULL  "
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                , new SerializerReadClass( SalessModel.discountlist.class )).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});


        if (disbillstk == null) {
            p.disBillliststockentry = new ArrayList();
        }
        else {
            p.disBillliststockentry = disbillstk;
        }
     /*    List disqtstk = new StaticSentence(app.getSession()
                ,"SELECT D.QTITEMID,P.NAME,D.QTY,D.RATE,D.USER_ID,D.REASON "
                +"FROM DISCOUNTLIST D,PRODUCTS P ,PEOPLE  P1,ROLES ,QTICKET "
                +"WHERE  D.CRDATE >= P1.OPENSALE AND P1.CLOSESALE IS NULL AND D.QTITEMID=QTICKET.ID AND QTICKET.CRDATE < P1.OPENSALE AND P1.NAME= D.USER_ID AND ROLES.ID = ? AND ROLES.ID=P1.ROLE  AND P.ID=D.PRODUCT_ID AND D.AUTHORISED='true'"
                ,SerializerWriteString.INSTANCE
                , new SerializerReadClass( SalessModel.discountlist.class )).list(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole());


        if (disqtstk == null) {
            p.disQtliststockentry = new ArrayList();
        }
        else {
            p.disQtliststockentry = disqtstk;
        }*/
        List salebillwise = new StaticSentence(app.getSession()
                ,"SELECT DISTINCT BILL.ID, BILL.CUSTOMER, BILL.AMOUNT, BILL.TAXTOTAL "
                +"FROM BILL, PEOPLE ,ROLES "
                +"WHERE  BILL.CLOSESALESEQ IS NULL AND  PEOPLE.NAME=BILL.CREATEDBY AND ROLES.ID=PEOPLE.ROLE  AND BILL.CREATEDDATE <=?  AND bill.warehouse in ("+ condition +")"//ROLES.ID = ? "
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                , new SerializerReadClass( SalessModel.SalesLine.class )).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});

        if (salebillwise == null) {
            p.m_billinfo = new ArrayList();
        }
        else {
            p.m_billinfo = salebillwise;
        }
         List taxCategorywise = new StaticSentence(app.getSession()
                ,"SELECT TC.ID,TC.ACCOUNT,SUM(TL.AMOUNT),TC.NAME "
                +"FROM BILL, PEOPLE ,ROLES,TAXLINES TL,TAXES T,taxcategories TC "
                +"WHERE  BILL.CLOSESALESEQ IS NULL AND  PEOPLE.NAME=BILL.CREATEDBY AND ROLES.ID=PEOPLE.ROLE  AND BILL.CREATEDDATE <=?  AND bill.warehouse in ("+ condition +") AND BILL.ID=TL.RECEIPT AND TL.TAXID=T.ID AND T.CATEGORY=TC.ID group by TC.ID,TC.ACCOUNT,TC.NAME"
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                , new SerializerReadClass( SalessModel.TaxLine.class )).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});

            if(taxCategorywise == null)
                p.taxLines=new ArrayList<TaxLine>();
            else
                p.taxLines=taxCategorywise;
         p.taxtotal=0.0;
         for(TaxLine tax :p.taxLines){
            p.taxtotal+=tax.getAmount();
         }

        /*
         * SELECT C.NAME,SUM(BILLITEM.RATE * BILLITEM.DMULTIPLY),C.PARENTID ,(select c1.parentid from categories c1  where c1.id=c.parentid),(select c1.NAME from categories c1  where c1.id=c.parentid)
                FROM BILL, PEOPLE ,ROLES,PRODUCTS,BILLITEM ,categories c
                WHERE   BILL.CLOSESALESEQ IS NULL AND PEOPLE.CLOSESALE IS NULL AND PEOPLE.NAME=BILL.CREATEDBY AND BILL.ID=BILLITEM.PARENTID AND BILLITEM.PRODUCT=PRODUCTS.ID AND ROLES.ID=PEOPLE.ROLE and c.id=products.category  GROUP BY c.name,c.parentid,products.category
         * */

         List<ProductsCategoryLine> categorywise = new StaticSentence(app.getSession()
                ,"SELECT  C.ID,C.NAME,SUM(BILLITEM.RATE * BILLITEM.DMULTIPLY),C.PARENTID ,(SELECT C1.PARENTID FROM CATEGORIES C1  WHERE C1.ID=C.PARENTID),(SELECT C1.NAME FROM CATEGORIES C1  WHERE C1.ID=C.PARENTID) "
                +"FROM BILL, PEOPLE ,ROLES,PRODUCTS,BILLITEM,CATEGORIES C "
                +"WHERE   BILL.CLOSESALESEQ IS NULL AND  PEOPLE.NAME=BILL.CREATEDBY AND BILL.ID=BILLITEM.PARENTID AND BILLITEM.PRODUCT=PRODUCTS.ID AND ROLES.ID=PEOPLE.ROLE AND BILL.CREATEDDATE <=?   AND bill.warehouse in ("+ condition +") AND PRODUCTS.CATEGORY=C.ID  GROUP BY PRODUCTS.CATEGORY,C.NAME,C.PARENTID,C.ID ORDER BY C.PARENTID"
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                , new SerializerReadClass( SalessModel.ProductsCategoryLine.class )).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});
         if(categorywise==null){
              p.categoriesTotal=new ArrayList<ProductsCategoryLine>();
         }else{
             p.categoriesTotal=new ArrayList<ProductsCategoryLine>();
              calculateCategoryWise(categorywise,p);
         }

         List productaccountwise = new StaticSentence(app.getSession()
                ,"SELECT PRODUCTS.SACCOUNT,SUM(BILLITEM.RATE * BILLITEM.DMULTIPLY),PRODUCTS.LOCATION "
                +"FROM BILL, PEOPLE ,ROLES,PRODUCTS,BILLITEM "
                +"WHERE   BILL.CLOSESALESEQ IS NULL  AND PEOPLE.NAME=BILL.CREATEDBY AND BILL.ID=BILLITEM.PARENTID AND BILLITEM.PRODUCT=PRODUCTS.ID AND ROLES.ID=PEOPLE.ROLE AND BILL.CREATEDDATE <=?  and  bill.warehouse in ("+ condition +")  GROUP BY PRODUCTS.LOCATION,PRODUCTS.SACCOUNT"
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                , new SerializerReadClass( SalessModel.ProductsAccountLine.class )).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});
         if(productaccountwise==null){
              p.productsaccount=new ArrayList<ProductsAccountLine>();
         }else{
              p.productsaccount=productaccountwise;
         }
         
         List productwarehousewise = new StaticSentence(app.getSession()
                ,"SELECT null,SUM(BILLITEM.RATE * BILLITEM.DMULTIPLY),PRODUCTS.LOCATION "
                +"FROM BILL, PEOPLE ,ROLES,PRODUCTS,BILLITEM "
                +"WHERE   BILL.CLOSESALESEQ IS NULL  AND PEOPLE.NAME=BILL.CREATEDBY AND BILL.ID=BILLITEM.PARENTID AND BILLITEM.PRODUCT=PRODUCTS.ID AND ROLES.ID=PEOPLE.ROLE AND BILL.CREATEDDATE <=?   AND bill.warehouse in ("+ condition +")  GROUP BY PRODUCTS.LOCATION"
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                , new SerializerReadClass( SalessModel.ProductsAccountLine.class )).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});
         if(productwarehousewise==null){
              p.productsWarerhouseTotal=new ArrayList<ProductsAccountLine>();
         }else{
              p.productsWarerhouseTotal=productwarehousewise;
         }


          p.qt_info=new ArrayList();
          p.pendingamt=0.0;
        List pendingqt = new StaticSentence(app.getSession()
                ," SELECT QTICKET.ID, QTICKET.CUSTOMER, WAITER.NAME,SUM(QTITEMS.DMULTIPLY * QTITEMS.RATE) AS AMOUNT, CRDATE,QTICKET.REASON FROM QTICKET,WAITER,QTITEMS,ROLES,PEOPLE "+
                     " WHERE QTITEMS.PARENTID=QTICKET.ID AND BILLED = FALSE AND WAITER.ID=QTICKET.WAITER AND ROLES.ID=PEOPLE.ROLE AND PEOPLE.NAME=QTICKET.CREATEDBY  AND qticket.warehouse=?"+
                     " GROUP BY QTICKET.ID,QTICKET.CUSTOMER, WAITER.NAME,CRDATE,QTICKET.REASON "+
                    " ORDER BY QTICKET.ID "
                ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
                   // ,new SerializerWriteBasic(data)
                ,new SerializerReadClass(SalessModel.QTLine.class)).list(new Object[]{app.getAppUserView().getUser().getWarehouse()});

        if(pendingqt==null)
        {
            p.qt_info=new ArrayList();
        }
        else
        {
            p.qt_info=pendingqt;
        }

        Object[] obj= (Object[])new StaticSentence(app.getSession()
                ," SELECT SUM(QTITEMS.DMULTIPLY * QTITEMS.RATE) AS AMOUNT FROM QTICKET,QTITEMS,PEOPLE,ROLES "+
                     " WHERE QTITEMS.PARENTID=QTICKET.ID AND BILLED = FALSE  AND ROLES.ID=PEOPLE.ROLE AND PEOPLE.NAME=QTICKET.CREATEDBY  AND qticket.warehouse = ?   "
                    ,new SerializerWriteBasic(new Datas[]{Datas.STRING})
                ,new SerializerReadBasic(new Datas [] { Datas.DOUBLE})).find(new Object[]{app.getAppUserView().getUser().getWarehouse()});
       if(obj==null || obj[0]==null )
       {
           p.pendingamt=0.0;
       }
       else
           p.pendingamt=(Double)obj[0];
        //sale product wise
     /*   List pdtwise = new StaticSentence(app.getSession()
                , "SELECT P.NAME,  SUM(BL.DMULTIPLY), P.PRICESELL "+
                "FROM BILLITEM AS BL, BILL AS B, PRODUCTS AS P, BILLITEM "+
                "WHERE BL.PARENTID = B.ID AND P.ID = BL.PRODUCT AND BL.PRODUCT = BILLITEM.PRODUCT AND "+
                "BILL.CREATEDDATE <= PEOPLE.CLOSECASHTIME AND BILL.CREATEDDATE >= PEOPLE.OPENCASHTIME AND BILL.CREATEDBY = ? "+
                "GROUP BY P.NAME, P.PRICESELL"
                ,SerializerWriteString.INSTANCE
                ,new SerializerReadClass(SalessModel.ProductsLine.class)).list(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
*/      Object[] x = (Object[]) new StaticSentence(app.getSession()
                ,"SELECT SUM(BILLITEM.TOTAL) " +
                "FROM BILL, BILLITEM, PEOPLE, PRODUCTS ,ROLES " +
                "WHERE  BILL.CLOSESALESEQ IS NULL   AND PRODUCTS.ID = BILLITEM.PRODUCT AND PEOPLE.NAME=BILL.CREATEDBY AND BILL.ID = BILLITEM.PARENTID AND BILL.CREATEDDATE <=?  AND bill.warehouse in ("+ condition +") AND ROLES.ID=PEOPLE.ROLE"
                //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                ,new SerializerReadBasic(new Datas [] { Datas.DOUBLE})).find(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});

                if(x[0] == null ) {
                    p.amttotal = 0.0;
                }
                else {
                    p.amttotal = (Double) x[0];
                }

          Object[] y = (Object[]) new StaticSentence(app.getSession()
                ,"SELECT SUM(BILL.TAXTOTAL) " +
                "FROM BILL, PEOPLE ,ROLES " +
                "WHERE   BILL.CLOSESALESEQ IS NULL AND PEOPLE.NAME=BILL.CREATEDBY AND BILL.CREATEDDATE <=?   AND bill.warehouse in ("+ condition +") AND ROLES.ID=PEOPLE.ROLE "
                 //,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                ,new SerializerReadBasic(new Datas [] { Datas.DOUBLE})).find(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});
          if(y[0] == null ) {
                    p.taxtotal = 0.0;
                }
                else {
                    p.taxtotal = (Double) y[0];
                }

          p.pdtamttotal = p.taxtotal + p.amttotal;

        List pdtwise = new StaticSentence(app.getSession()
                , "SELECT PRODUCTS.NAME,SUM(DMULTIPLY),BILLITEM.RATE ,SUM(BILLITEM.TOTAL),PRODUCTS.ID,PRODUCTS.CATEGORY,products.inventrymaintain  "+
                "FROM BILLITEM,PRODUCTS,BILL,PEOPLE ,ROLES "+
                "WHERE BILLITEM.PARENTID = BILL.ID AND PRODUCTS.ID = BILLITEM.PRODUCT AND "+
                "  BILL.CLOSESALESEQ IS NULL AND BILL.CREATEDDATE <=?  AND PEOPLE.NAME=BILL.CREATEDBY AND ROLES.ID=PEOPLE.ROLE AND bill.warehouse in ("+ condition +")"+
                "GROUP BY PRODUCTS.ID ,PRODUCTS.NAME , BILLITEM.RATE,PRODUCTS.CATEGORY,PRODUCTS.ID " +
                "ORDER BY PRODUCTS.NAME,PRODUCTS.CATEGORY"
               // ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                ,new SerializerWriteBasic(data)
                ,new SerializerReadClass(SalessModel.ProductsLine.class)).list(params);//new Object[]{d,app.getAppUserView().getUser().getRole()});
        
        if(pdtwise == null) {
            p.m_blf = new ArrayList();
        }
        else {
            p.m_blf = pdtwise;
        }
        // Sales

          
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

    public List<ProductsCategoryLine> getCategoryTotals(){
          return categoriesTotal;
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
    public List<QTLine> getQtLine()
    {
        return qt_info;
    }
    public List<discountlist> getdisQTLine(){
      return disQtlist;
    }
     public List<discountlist> getdisBillLine(){
      return disBilllist;
    }
     public List<discountlist> getdisQTLineStk(){
      return disQtliststockentry;
    }
     public List<discountlist> getdisBillLineStk(){
      return disBillliststockentry;
    }
    public List<SalesLine> getSalesLine() {
        return m_billinfo;
    }
   public List<ProductsAccountLine> getprodutsAccountwiseTotal(){
     return productsaccount;
   }

    public List<ProductsAccountLine> getProductsTotal() {
        return productsWarerhouseTotal;
    }
    public List<ProductsLine> getProductLine() {
        return m_blf;
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

    public double getTotaltax() {
        return totaltax;
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
    
    public List<ProductsLine> getProductsLines() {
        return m_blf;
    }
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
    //
   // public double pdtTotalAmount=0;
    //
    public AbstractTableModel getQtTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(QTHEADERS[column]);
                //Sanjeev:commented above line and added below line
                return QTHEADERS[column];
            }
            public int getRowCount() {
                return qt_info.size();
            }
            public int getColumnCount() {

                return QTHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                QTLine l = qt_info.get(row);
              
                switch (column) {
                case 0: return l.getqtid();
                case 1: return l.getcustomer();
                case 2: return l.getwaiter();
                case 3://
                        // pdtTotalAmount+=l.getAmount();
                       return l.getqtvalue();
                case 4 : return l.getcreated_date();
                    case 5 : return l.getreason();
                default: return null;
                }
            }  
        };
    }
   /* public int getSize()
    {
        return qtId.size();
    }*/
    // QT table
    public AbstractTableModel getProductsModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {

                //return AppLocal.getIntString(PRODUCTHEADERS[column]);
                //return AppLocal.getIntString(QTHEADERS[column]);
                return PRODUCTHEADERS[column];
            }
            public int getRowCount() {
                return m_blf.size();
            }
            public int getColumnCount() {
                return PRODUCTHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                ProductsLine l = m_blf.get(row);
                switch (column) {
                case 0: return l.getproduct();
                case 1: return l.getqty();
                case 2: return l.getRate();
                case 3://
                        // pdtTotalAmount+=l.getAmount();
                       return l.getAmount();
                    case 4:l.getTax();
                default: return null;
                }
            }
        };
    }
    
    public static  class discountlist implements SerializableRead{
        private String id;
        private String product;
        private Double qty;
        private Double rate;
        private String createdby;
        private String reason;
        private boolean  maintainInventory;

       public void readValues(DataRead dr) throws BasicException {
            id=dr.getString(1);
            product=dr.getString(2);
            qty=dr.getDouble(3);
            rate=dr.getDouble(4);
            createdby=dr.getString(5);
            reason=dr.getString(6);
            maintainInventory = dr.getBoolean(7);
       }

        public boolean isMaintainInventory() {
            return maintainInventory;
        }

       public String printid(){
             return id;
       }
       public String printproduct(){
             return StringUtils.encodeXML(product);
       }
       public String printqty(){
               return Formats.DOUBLE.formatValue(qty);
       }
       public String printrate(){
         return Formats.CURRENCY.formatValue(rate);
       }
       public String printcreatedby(){
           return createdby;
       }
       public String printreason(){
             return StringUtils.encodeXML(reason);
       }
       public Double getqty(){
          return (qty );
       }
    }
    public static class ProductsAccountLine implements SerializableRead {
        private String accname;
        private Double amount;
        private String location;
        public void readValues(DataRead dr) throws BasicException {
            accname = dr.getString(1);
            amount = dr.getDouble(2);
            location = dr.getString(3);
        }
        public String getAccount(){
          return accname;
        }
        public Double getAmount(){
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
            cname=dr.getString(2);
            amount=dr.getDouble(3);
            parentid=dr.getString(4);
            pparentid=dr.getString(5);
            parentname=dr.getString(6);
        }
        public String getCid(){
          return cid;
        }
         public String getCname(){
          return cname;
        }
         public String printCname(){
           return StringUtils.encodeXML(cname);
         }
        public Double getAmount(){
           return amount;
        }
        public String getParentid(){
          return parentid;
        }
        public String getPParentid(){
          return pparentid;
        }
        public String getParentName(){
          return parentname;
        }
        public void setCid(String cid){
          this.cid=cid;
        }
         public void setCname(String cname){
          this.cname=cname;
        }
        public void setAmount(Double amt){
            amount=amt;
        }
        public void setParentid(String pid){
            parentid=pid;
        }
        public void setParentName(String pname){
          parentname=pname;
        }
         public void setPParentid(String pid){
           pparentid=pid;
        }
    }

    public static class TaxLine implements SerializableRead {
        private String taxCategoryId;
        private String accountId;
        private double amount;
        private String name;
        public void readValues(DataRead dr) throws BasicException {
            taxCategoryId=dr.getString(1);
            accountId=dr.getString(2);
            amount=dr.getDouble(3);
            name=dr.getString(4);
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
        public String printName(){
             return StringUtils.encodeXML(name);
        }
         public String printAmount() {
            return Formats.CURRENCY.formatValue(amount);
        }


    }

     public static class SalesLine implements SerializableRead {

        private String billno;
        private String customer;
        private double billamount;
        private double taxamount;
        private double total;
        private double subbill = 0.0;
        private double subtax = 0.0;
        private double maintotal = 0.0;

        public void readValues(DataRead dr) throws BasicException {
            billno = dr.getString(1);
            customer = dr.getString(2);
            billamount = dr.getDouble(3);
            taxamount = dr.getDouble(4);
            total = billamount + taxamount;
            subbill+=billamount;
            subtax += taxamount;
            maintotal += total;
             String cust1="";
               String temp1[]=customer.split(" ");
              String temp[]=customer.split("#");
            try{
                // if(temp.length>1){
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
                customer= cust1+":"+"G"+temp1[1];
            else
            customer= cust1;
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
            return Formats.CURRENCY.formatValue(billamount);
        }

        public String printTotal() {
            return Formats.CURRENCY.formatValue(total);
        }
        public String getbillno() {
            return billno;
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
            return billamount;
        }

        public Double gettaxamount() {
            return taxamount;
        }

        public Double getTotal() {
            return total;
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

    
    public static class QTLine implements SerializableRead {
        
        private String qtId;
        private String customer;
        private String waiter;
        private Double qtvalue;
       // private String floor;
        private Timestamp created_date ;
        private String reason;
        //private double subtax = 0.0;
        //private double maintotal = 0.0;
        
        public void readValues(DataRead dr) throws BasicException {
            qtId = dr.getString(1);
            customer = dr.getString(2);
            waiter = dr.getString(3);
            qtvalue=dr.getDouble(4);
            created_date= dr.getTimestamp(5);
            reason=dr.getString(6);
              String cust1="";
               String temp1[]=customer.split(" ");
              String temp[]=customer.split("#");
            try{
                // if(temp.length>1){
             Object[] cust=(Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(),
                "SELECT SEARCHKEY FROM CUSTOMERS WHERE ID = ? ",
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
                customer= cust1+":"+"G"+temp1[1];
            else
            customer= cust1;
            
        }
        public String printqtid() {
            return qtId;
        }      
        public String printwaiter() {
            return StringUtils.encodeXML(waiter);
        }
        public String printreason()
        {
            return StringUtils.encodeXML(reason);
        }
        public String getreason()
        {
            return reason;
        }
        
        public String printcustomer() {
            
            return customer;
        }
        
        public String printqtvalue() {
            return Formats.CURRENCY.formatValue(qtvalue);
        }
        
      /*  public String printfloor() {
            return floor;
        }*/
        public String printcreateddate() {
            return Formats.DATE.formatValue(created_date);
        }
       
        public String getqtid() {
        return qtId;
    }
    public String getcustomer() {
      /*   String cust1="";
          String temp[]=customer.split("#");
            try{
                // if(temp.length>1){
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
    public String getwaiter() {
        return waiter;
    }
    public Double getqtvalue() {
        return qtvalue.doubleValue();
    }
  /*  public String getfloor() {
        return floor;
    }*/
    public Date getcreated_date() {
        return created_date;
    }
    }

    public AbstractTableModel getSalesModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(SALESHEADERS[column]);
                //Sanjeev:commented above line and added below line
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
                case 0: return l.getbillno();
                case 1: return l.getcustomer();
                case 2: return l.getbillamount();
                case 3: return l.gettaxamount();
                case 4: return l.getTotal();
                default: return null;
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
            amt=dr.getDouble(4);
            pdtid=dr.getString(5);
            pdtcategory=dr.getString(6);
            maintainInventory=dr.getBoolean(7);            
        }

        public boolean isMaintainInventory() {
            return maintainInventory;
        }
        
        public String getpdtid(){
           return pdtid;
        }
        public String getpdtcategory(){
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
}    