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
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author swathi
 */
public class WaiterWiseTotalModel {

    private List<waiterTotalSales> waiterlist;
    private waiterTotalSales wsales;
    private List<Creditbill> waitercreditlist;
    private List<StockTransferInfo> stocktransferdetails;
    private Creditbill cbill;
    
    public WaiterWiseTotalModel() {
    }

    public WaiterWiseTotalModel loadInstance(AppView app, Object[] val) throws BasicException {
        WaiterWiseTotalModel wm = new WaiterWiseTotalModel();
        List<waiterTotalSales> li = new StaticSentence(app.getSession(),
                "SELECT NAME,SUM(CASHAMOUNT+DEBTAMOUNT),ID,LNAME,SUM(DEBTAMOUNT),SUM(CASHAMOUNT) FROM "
                + "(SELECT WAITER.NAME, 0.0 AS AMOUNT,WAITER.ID,LOCATIONS.NAME AS LNAME,SUM(BILL.AMOUNT) AS DEBTAMOUNT,0.0 AS CASHAMOUNT "
                + "FROM BILL JOIN WAITER ON BILL.WAITER=WAITER.ID JOIN PEOPLE ON BILL.CREATEDBY=PEOPLE.NAME JOIN LOCATIONS ON PEOPLE.PRCATEGORIES=LOCATIONS.ID JOIN PAYMENTS P ON P.RECEIPT=BILL.RECEIPT "
                + "WHERE  P.PAYMENT='DEBT' AND BILL.CREATEDDATE>=? AND BILL.CREATEDDATE<=? AND BILL.WAREHOUSE=?  GROUP BY WAITER.ID,WAITER.NAME,LNAME "
                + "UNION ALL "
                + "SELECT WAITER.NAME, 0.0 AS AMOUNT,WAITER.ID,LOCATIONS.NAME AS LNAME,0.0 AS DEBTAMOUNT,SUM(BILL.AMOUNT) AS CASHAMOUNT "
                + "FROM BILL JOIN WAITER ON BILL.WAITER=WAITER.ID JOIN PEOPLE ON BILL.CREATEDBY=PEOPLE.NAME JOIN LOCATIONS ON PEOPLE.PRCATEGORIES=LOCATIONS.ID JOIN PAYMENTS P ON P.RECEIPT=BILL.RECEIPT "
                + "WHERE  P.PAYMENT!='DEBT' AND BILL.CREATEDDATE>=? AND BILL.CREATEDDATE<=? AND BILL.WAREHOUSE=?  GROUP BY WAITER.ID,WAITER.NAME,LNAME "
                + "UNION ALL "
                + "SELECT WAITER.NAME, 0.0 AS AMOUNT,WAITER.ID,LOCATIONS.NAME AS LNAME,SUM(BILL_ARV.AMOUNT) AS DEBTAMOUNT,0.0 AS CASHAMOUNT "
                + "FROM BILL_ARV JOIN WAITER ON BILL_ARV.WAITER=WAITER.ID JOIN PEOPLE ON BILL_ARV.CREATEDBY=PEOPLE.NAME JOIN LOCATIONS ON PEOPLE.PRCATEGORIES=LOCATIONS.ID JOIN PAYMENTS_ARV P ON P.RECEIPT=BILL_ARV.RECEIPT "
                + "WHERE  P.PAYMENT='DEBT' AND BILL_ARV.CREATEDDATE>=? AND BILL_ARV.CREATEDDATE<=? AND BILL_ARV.WAREHOUSE=?  GROUP BY WAITER.ID,WAITER.NAME,LNAME "
                + "UNION ALL "
                + "SELECT WAITER.NAME, 0.0 AS AMOUNT,WAITER.ID,LOCATIONS.NAME AS LNAME,0.0 AS DEBTAMOUNT,SUM(BILL_ARV.AMOUNT) AS CASHAMOUNT "
                + "FROM BILL_ARV JOIN WAITER ON BILL_ARV.WAITER=WAITER.ID JOIN PEOPLE ON BILL_ARV.CREATEDBY=PEOPLE.NAME JOIN LOCATIONS ON PEOPLE.PRCATEGORIES=LOCATIONS.ID JOIN PAYMENTS_ARV P ON P.RECEIPT=BILL_ARV.RECEIPT "
                + "WHERE  P.PAYMENT!='DEBT' AND BILL_ARV.CREATEDDATE>=? AND BILL_ARV.CREATEDDATE<=? AND BILL_ARV.WAREHOUSE=?  GROUP BY WAITER.ID,WAITER.NAME,LNAME ) "
                + "AS WAITERTOTAL GROUP BY ID,NAME,LNAME ORDER BY 4,1 ",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(waiterTotalSales.class)).list(val);
        if (li == null) {
            wm.waiterlist = new ArrayList<waiterTotalSales>();
        } else {
            wm.waiterlist = li;
        }
        return wm;
    }

    public WaiterWiseTotalModel loadInstanceCredit(AppView app, Object[] val) throws BasicException {
        WaiterWiseTotalModel wm = new WaiterWiseTotalModel();
        List<Creditbill> li = new StaticSentence(app.getSession(),
                "SELECT BDATE,BID,AMOUNT,CNAME,MEMNO,LNAME,WNAME FROM (SELECT B.CREATEDDATE AS BDATE,B.ID AS BID,B.AMOUNT AS AMOUNT,B.CREATEDBY AS CNAME,C.SEARCHKEY AS MEMNO,L.NAME AS LNAME,W.NAME AS WNAME "
                + " FROM BILL_ARV B JOIN PAYMENTS_ARV P ON P.RECEIPT=B.RECEIPT JOIN CUSTOMERS C ON C.ID=B.CUSTOMER JOIN LOCATIONS L ON L.ID=B.WAREHOUSE JOIN WAITER W ON W.ID=B.WAITER "
                + " WHERE  P.PAYMENT='DEBT' AND B.CREATEDDATE>=? AND B.CREATEDDATE<=? AND B.WAREHOUSE=? GROUP BY B.ID "
                + " UNION ALL "
                + " SELECT B.CREATEDDATE AS BDATE,B.ID AS BID,B.AMOUNT AS AMOUNT,B.CREATEDBY AS CNAME,C.SEARCHKEY AS MEMNO,L.NAME AS LNAME,W.NAME AS WNAME "
                + " FROM BILL B JOIN PAYMENTS P ON P.RECEIPT=B.RECEIPT JOIN CUSTOMERS C ON C.ID=B.CUSTOMER JOIN LOCATIONS L ON L.ID=B.WAREHOUSE JOIN WAITER W ON W.ID=B.WAITER "
                + " WHERE  P.PAYMENT='DEBT' AND B.CREATEDDATE>=? AND B.CREATEDDATE<=? AND B.WAREHOUSE=? GROUP BY B.ID )AS CREDITBILLS ORDER BY BDATE,MEMNO ",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(Creditbill.class)).list(val);
        if (li == null) {
            wm.waitercreditlist = new ArrayList<Creditbill>();
        } else {
            wm.waitercreditlist = li;
        }
        return wm;
    }

    public WaiterWiseTotalModel loadInstancestocktransfer(AppView app, Object[] val) throws BasicException {
        WaiterWiseTotalModel wm = new WaiterWiseTotalModel();
        List<StockTransferInfo> li = new StaticSentence(app.getSession(),
                "SELECT S.DATENEW,CAST(S.NUM AS SIGNED) AS TNO,P.NAME AS PRODUCT,L.NAME AS LOCATION,CAST(S.UNITS AS SIGNED) AS QTY,S.CREATEDBY AS SNAME,P1.NAME AS PRODUCT1,L1.NAME AS LOCATION1,CAST(S.UNITS1 AS SIGNED) AS QTY1,S.RECEIVEDBY AS RNAME FROM STOCKDIARY S,LOCATIONS L,LOCATIONS L1,PRODUCTS P,PRODUCTS P1 WHERE P1.ID=S.PRODUCT1 AND P.ID=S.PRODUCT AND L.ID=S.LOCATION AND L1.ID=S.LOCATION1 AND S.DATENEW>=? AND S.DATENEW<=? AND (S.LOCATION=? OR S.LOCATION1=?) ORDER BY S.DATENEW,TNO ",
                new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING}), new SerializerReadClass(StockTransferInfo.class)).list(val);
        if (li == null) {
            wm.stocktransferdetails = new ArrayList<StockTransferInfo>();
        } else {
            wm.stocktransferdetails = li;
        }
        return wm;
    }

    public List<waiterTotalSales> getWaiterlist() {
        return waiterlist;
    }

    public List<Creditbill> getWaitercreditlist() {
        return waitercreditlist;
    }

    public void setWaitercreditlist(List<Creditbill> waitercreditlist) {
        this.waitercreditlist = waitercreditlist;
    }

    public void setWaiterlist(List<waiterTotalSales> waiterlist) {
        this.waiterlist = waiterlist;
    }

    public List<StockTransferInfo> getStocktransferdetails() {
        return stocktransferdetails;
    }



    public static class waiterTotalSales implements SerializableRead {

        private String name;
        private Double totalAmount;
        private String id;
        private String locationName;
        private Double totalCreditAmount;
        private Double totalCashAmount;

        public void readValues(DataRead dr) throws BasicException {
            name = dr.getString(1);
            totalAmount = dr.getDouble(2);
            id = dr.getString(3);
            locationName = dr.getString(4);
            totalCreditAmount = dr.getDouble(5);
            totalCashAmount = dr.getDouble(6);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Double getTotalCashAmount() {
            return totalCashAmount;
        }

        public void setTotalCashAmount(Double totalCashAmount) {
            this.totalCashAmount = totalCashAmount;
        }

        public Double getTotalCreditAmount() {
            return totalCreditAmount;
        }

        public void setTotalCreditAmount(Double totalCreditAmount) {
            this.totalCreditAmount = totalCreditAmount;
        }
    }

    public static class Creditbill implements SerializableRead {

        private String name;
        private String memno;
        private Double amount;
        private String billid;
        private Date date;
        private String warehouse;
        private String waiter;

        public void readValues(DataRead dr) throws BasicException {
            date = dr.getTimestamp(1);
             billid = dr.getString(2); 
            amount = dr.getDouble(3);
            name = dr.getString(4);
            memno = dr.getString(5);
            warehouse = dr.getString(6);
            waiter = dr.getString(7);
        }

        public String getWarehouse() {
            return warehouse;
        }

        public String getWaiter() {
            return waiter;
        }
        
        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getBillid() {
            return billid;
        }

        public void setBillid(String billid) {
            this.billid = billid;
        }

        public Double getBillno() {
            return amount;
        }

        public void setBillno(Double amount) {
            this.amount = amount;
        }

       

        public String getMemno() {
            return memno;
        }

        public void setMemno(String memno) {
            this.memno = memno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class StockTransferInfo implements SerializableRead{
        private Date datenew;
        private String tno;
        private String product;
        private String location;
        private int qty;
        private String sname;
        private String product1;
        private String location1;
        private int qty1;
        private String rname;

        public void readValues(DataRead dr) throws BasicException {
            datenew = dr.getTimestamp(1);
            tno = dr.getString(2);
            product = dr.getString(3);
            location = dr.getString(4);
            qty = dr.getInt(5);
            sname = dr.getString(6);
            product1 = dr.getString(7);
            location1 = dr.getString(8);
            qty1 = dr.getInt(9);
            rname = dr.getString(10);

        }

        public Date getDatenew() {
            return datenew;
        }

        public void setDatenew(Date datenew) {
            this.datenew = datenew;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation1() {
            return location1;
        }

        public void setLocation1(String location1) {
            this.location1 = location1;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getProduct1() {
            return product1;
        }

        public void setProduct1(String product1) {
            this.product1 = product1;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getQty1() {
            return qty1;
        }

        public void setQty1(int qty1) {
            this.qty1 = qty1;
        }

        public String getRname() {
            return rname;
        }

        public void setRname(String rname) {
            this.rname = rname;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getTno() {
            return tno;
        }

        public void setTno(String tno) {
            this.tno = tno;
        }



    }
}
