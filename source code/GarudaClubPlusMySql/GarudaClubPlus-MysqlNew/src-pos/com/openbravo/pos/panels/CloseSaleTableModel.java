/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.collections.ListUtils;

/**
 *
 * @author swathi
 */
public class CloseSaleTableModel {



    private List<ClosedSaleLine> closedSales;
    private final static String[] BILLSHEADERS = {"Sequence", "DateStart", "DateEnd", "Role", "Amount"};
    private List<saleList> billDetails;
    private final static String[] BILLDETAILHEADERS = {"BillNo", "MemberNO", "Amount", "Date"};
    private List<ProdList> ProdDetails;
    private List<saleProdList> saleProd;
    private final static String[] PRODDETAILHEADERS = {"ProdName", "Qty", "Rate", "Date"};
    private static Double saleListTotal=0.0;
    private static Double closeSaleTotal=0.0;

    private CloseSaleTableModel() {
    }

    public static CloseSaleTableModel emptyinstance() {

        CloseSaleTableModel c = new CloseSaleTableModel();
        c.closedSales = new ArrayList<ClosedSaleLine>();
        return c;

    }

    public static CloseSaleTableModel loadInstantce(AppView app, Date fdate, Date todate, String role) throws BasicException {
        CloseSaleTableModel c = new CloseSaleTableModel();
        c.closedSales = new ArrayList<ClosedSaleLine>();
        
        List<ClosedSaleLine> billList = (List<ClosedSaleLine>) new StaticSentence(app.getSession(), "SELECT C.ID,C.SEQUENCE,C.DATESTART,C.DATEEND,R.NAME,C.AMOUNT,C.ROLE FROM ROLES R,CLOSEDSALE C WHERE R.ID=C.ROLE AND R.NAME=? AND DATESTART BETWEEN ? AND ? ORDER BY SEQUENCE", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(CloseSaleTableModel.ClosedSaleLine.class)).list(new Object[]{role,fdate, todate});
        if (billList == null) {
            c.closedSales = new ArrayList<ClosedSaleLine>();
        } else {
            c.closedSales = billList;

        }
        return c;
    }

    public static CloseSaleTableModel loadInstanceOfSale(AppView app, Date fdate, Date todate, String seq) throws BasicException {
        CloseSaleTableModel c1 = new CloseSaleTableModel();
        //saleList sale=null;
        
        c1.billDetails = new ArrayList<saleList>();
        //Lokesh
        //where class changed from c.id = b.customers to B.CUSTOMER like concat(C.ID , '%') to get the GuestEntry aslo
        
        List<saleList> saleDetailList = new StaticSentence(app.getSession(), "SELECT BID,SKEY,SEQ,AMOUNT,CRDATE,NAME FROM (SELECT B.ID AS BID,C.SEARCHKEY AS SKEY,B.CLOSESALESEQ AS SEQ, (B.AMOUNT+B.TAXTOTAL) AS AMOUNT,B.CREATEDDATE AS CRDATE,C.NAME AS NAME FROM BILL_ARV B,CUSTOMERS C WHERE B.CUSTOMER like concat(C.ID , '%')  AND B.CLOSESALESEQ=?" + "UNION ALL" +
        " SELECT B.ID AS BID,C.SEARCHKEY AS SKEY,B.CLOSESALESEQ AS SEQ, (B.AMOUNT+B.TAXTOTAL) AS AMOUNT,B.CREATEDDATE AS CRDATE,C.NAME AS NAME FROM BILL B,CUSTOMERS C WHERE B.CUSTOMER like concat(C.ID , '%')  AND B.CLOSESALESEQ=?) AS BILLLIST ORDER BY 2" //,"SELECT B.ID,C.SEARCHKEY,B.CLOSESALESEQ,B.AMOUNT,B.CREATEDDATE,C.NAME FROM BILL_ARV B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=? AND B.CREATEDDATE BETWEEN ? AND ? ORDER BY C.SEARCHKEY"
        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(CloseSaleTableModel.saleList.class)).list(new Object[]{seq, seq});
        ////List<saleList> saleDetailList = new StaticSentence(app.getSession(), "SELECT BID,SKEY,SEQ,AMOUNT,CRDATE,NAME FROM (SELECT B.ID AS BID,C.SEARCHKEY AS SKEY,B.CLOSESALESEQ AS SEQ, (B.AMOUNT+B.TAXTOTAL) AS AMOUNT,B.CREATEDDATE AS CRDATE,C.NAME AS NAME FROM BILL_ARV B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=?" + "UNION ALL" +
                ////" SELECT B.ID AS BID,C.SEARCHKEY AS SKEY,B.CLOSESALESEQ AS SEQ,B.AMOUNT AS AMOUNT,B.CREATEDDATE AS CRDATE,C.NAME AS NAME FROM BILL B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=?) AS BILLLIST ORDER BY 2" //,"SELECT B.ID,C.SEARCHKEY,B.CLOSESALESEQ,B.AMOUNT,B.CREATEDDATE,C.NAME FROM BILL_ARV B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=? AND B.CREATEDDATE BETWEEN ? AND ? ORDER BY C.SEARCHKEY"
                ////, new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(CloseSaleTableModel.saleList.class)).list(new Object[]{seq, seq});
        //, new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(CloseSaleTableModel.saleList.class)).list(new Object[]{seq, fdate, todate});
        if (saleDetailList == null) {
            c1.billDetails = new ArrayList<saleList>();
        } else {
            c1.billDetails = saleDetailList;
         }
        return c1;
    }
    public static CloseSaleTableModel loadInstanceOfProd(AppView app, Date fdate, Date todate, String seq,String role) throws BasicException {
         CloseSaleTableModel c1 = new CloseSaleTableModel();
        //saleList sale=null;

        List<ProdList> ProdDetailList = new StaticSentence(app.getSession(),"SELECT PRODUCT,QTY,RATE,CRDATE FROM(SELECT pr.name AS Product,p.qty AS Qty,p.rate AS Rate,p.csdate AS CRDATE FROM productwiseclosesale p,products pr,closedsale c WHERE pr.ID=p.product  AND p.CLOSESALEREF=c.id and c.role = ? and c.sequence=? )AS PRODLIST ORDER BY 1"
               //,"SELECT B.ID,C.SEARCHKEY,B.CLOSESALESEQ,B.AMOUNT,B.CREATEDDATE,C.NAME FROM BILL_ARV B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=? AND B.CREATEDDATE BETWEEN ? AND ? ORDER BY C.SEARCHKEY"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(CloseSaleTableModel.ProdList.class)).list(new Object[]{role,seq});
        //, new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(CloseSaleTableModel.saleList.class)).list(new Object[]{seq, fdate, todate});
        if (ProdDetailList == null) {
          c1.ProdDetails = new ArrayList<ProdList>();
        } else {
            c1.ProdDetails = ProdDetailList;
         }
        return c1;
    }
//    static CloseSaleTableModel loadInstanceOfSaleProd(AppView m_App, Date time, Date time0, String id, String sequence, String role) throws BasicException {
//     CloseSaleTableModel c1 = new CloseSaleTableModel();
//    
//         c1.saleProd = new ArrayList<saleProdList>();
//      List<saleList> saleDetailList = new StaticSentence(m_App.getSession(), "SELECT BID,SKEY,SEQ,AMOUNT,CRDATE,NAME FROM (SELECT B.ID AS BID,C.SEARCHKEY AS SKEY,B.CLOSESALESEQ AS SEQ,B.AMOUNT AS AMOUNT,B.CREATEDDATE AS CRDATE,C.NAME AS NAME FROM BILL_ARV B,CUSTOMERS C WHERE B.CUSTOMER like concat(C.ID , '%')  AND B.CLOSESALESEQ=?" + "UNION ALL" +
//                " SELECT B.ID AS BID,C.SEARCHKEY AS SKEY,B.CLOSESALESEQ AS SEQ,B.AMOUNT AS AMOUNT,B.CREATEDDATE AS CRDATE,C.NAME AS NAME FROM BILL B,CUSTOMERS C WHERE B.CUSTOMER like concat(C.ID , '%')  AND B.CLOSESALESEQ=?) AS BILLLIST ORDER BY 2" //,"SELECT B.ID,C.SEARCHKEY,B.CLOSESALESEQ,B.AMOUNT,B.CREATEDDATE,C.NAME FROM BILL_ARV B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=? AND B.CREATEDDATE BETWEEN ? AND ? ORDER BY C.SEARCHKEY"
//                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(CloseSaleTableModel.saleList.class)).list(new Object[]{id, id});
//        List<ProdList> ProdDetailList = new StaticSentence(m_App.getSession(),"SELECT PRODUCT,QTY,RATE,CRDATE FROM(SELECT pr.name AS Product,p.qty AS Qty,p.rate AS Rate,p.csdate AS CRDATE FROM productwiseclosesale p,products pr,closedsale c WHERE pr.ID=p.product  AND p.CLOSESALEREF=c.id and c.role = ? and c.sequence=? )AS PRODLIST ORDER BY 1"
//               //,"SELECT B.ID,C.SEARCHKEY,B.CLOSESALESEQ,B.AMOUNT,B.CREATEDDATE,C.NAME FROM BILL_ARV B,CUSTOMERS C WHERE C.ID=B.CUSTOMER  AND B.CLOSESALESEQ=? AND B.CREATEDDATE BETWEEN ? AND ? ORDER BY C.SEARCHKEY"
//                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(CloseSaleTableModel.ProdList.class)).list(new Object[]{role,sequence});
//
//
//        List<saleProdList> Prodsale = ListUtils.union( saleDetailList,ProdDetailList);
//         if (Prodsale == null) {
//          c1.saleProd = new ArrayList<saleProdList>();
//        } else {
//            c1.saleProd = Prodsale;
//         }
//        return c1;
//   }


    public  Double getTotalAmountOfCloseSaleLine(){
        ClosedSaleLine c=null;
        closeSaleTotal = 0.00;
        for(ClosedSaleLine sale:getCloseSaleLine()){
            closeSaleTotal=closeSaleTotal+sale.getAmount();
        }
         return closeSaleTotal;

    }
   public  Double getTotalAmountOfSaleList(){
        saleList s = null;
        saleListTotal = 0.0;
       for(saleList sale: getsaleList()){
           saleListTotal=saleListTotal+sale.getAmount();
       }
        return saleListTotal;

   }
     
    public List<ClosedSaleLine> getCloseSaleLine() {
        return closedSales;
    }

    public List<saleList> getsaleList() {
        return billDetails;
    }

    public List<ProdList> getProdDetails() {
        return ProdDetails;
    }

//    public List<saleProdList> getSaleProd() {
//        return saleProd;
//    }




    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return closedSales.size();
            }

            public int getColumnCount() {
                return BILLSHEADERS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BILLSHEADERS[column]);
            }

            public Object getValueAt(int row, int col) {
                ClosedSaleLine bl = closedSales.get(row);
                switch (col) {
                    case 0:
                        return bl.getSequence();
                    case 1:
                        return bl.getDateStart();
                    case 2:
                        return bl.getDateEnd();
                    case 3:
                        return bl.getRoleName();
                    case 4:
                        return bl.getAmount();
                    case 5:
                        return bl.getRole();

                }
                return bl;
            }
        };
    }

    public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return billDetails.size();

            }

            public int getColumnCount() {
                return BILLDETAILHEADERS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BILLDETAILHEADERS[column]);

            }

            public Object getValueAt(int row, int col) {
                saleList sl = billDetails.get(row);
                switch (col) {
                    case 0:
                        return sl.getBillId();
                    case 1:
                        return sl.getCustomer();
                    case 2:
                        return sl.getAmount();
                    case 3:
                        return sl.getCreateddate();
                    case 4:
                        return sl.getCname();
                }
                return sl;
            }
        };

    }
      public AbstractTableModel getTableModel2() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return ProdDetails.size();

            }

            public int getColumnCount() {
                return PRODDETAILHEADERS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(PRODDETAILHEADERS[column]);

            }

            public Object getValueAt(int row, int col) {
                ProdList  pr= ProdDetails.get(row);
                switch (col) {
                    case 0:
                        return pr.getProdid();
                    case 1:
                        return pr.getQty();
                    case 2:
                        return pr.getRate();
                    case 3:
                        return pr.getCreateddate();
                   
                }
                return pr;
            }
        };

    }
       
    public static class ClosedSaleLine implements SerializableRead {

        private String id;
        private String sequence;
        private Date dateStart;
        private double amount;
        private Date dateEnd;
        private String role;
        private String roleName;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            sequence = dr.getString(2);
            dateStart = dr.getTimestamp(3);           
            dateEnd = dr.getTimestamp(4);
            roleName = dr.getString(5);
             amount = dr.getDouble(6);
            role = dr.getString(7);

        }

        public double getAmount() {
            return amount;
        }

        public String getRoleName() {
            return roleName;
        }

        public Date getDateEnd() {
            return dateEnd;
        }

        public Date getDateStart() {
            return dateStart;
        }

        public String getId() {
            return id;
        }

        public String getRole() {
            return role;
        }

        public String getSequence() {
            return sequence;
        }
    }

    public static class saleList implements SerializableRead {

        private String billId;
        private String customer;
        private String closedSaleSeq;
        private Double amount;
        private Date createddate;
        private String cname;
        private Double toatalAmt;
       

        //private String rolename;

        public void readValues(DataRead dr) throws BasicException {
            billId = dr.getString(1);
            customer = dr.getString(2);
            closedSaleSeq = dr.getString(3);
            amount = dr.getDouble(4);
            createddate = dr.getTimestamp(5);
            cname = dr.getString(6);
           // rolename  = dr.getString(7);
        }

        public Double getToatalAmt() {
            return toatalAmt;
        }

        public void setToatalAmt(Double toatalAmt) {
            this.toatalAmt = toatalAmt;
        }

        public Double getAmount() {
            return amount;
        }

        public String getBillId() {
            return billId;
        }

        public String getClosedSaleSeq() {
            return closedSaleSeq;
        }

        public Date getCreateddate() {
            return createddate;
        }

        public String getCustomer() {
            return customer;
        }

        public String getCname() {
            return cname;
        }

      

        


    }
    public static class ProdList implements SerializableRead {

        private String prodid;
         private String ProductName;
      
        private Double Qty;
        private Date createddate;
        private Double Rate;

           public void readValues(DataRead dr) throws BasicException {

            prodid = dr.getString(1);
            Qty = dr.getDouble(2);
             Rate = dr.getDouble(3);
            createddate = dr.getTimestamp(4);
        }

      

        public Date getCreateddate() {
            return createddate;
        }

        public void setCreateddate(Date createddate) {
            this.createddate = createddate;
        }

        public String getProdid() {
            return prodid;
        }

        public void setProdid(String prodid) {
            this.prodid = prodid;
        }

        public Double getQty() {
            return Qty;
        }

        public void setQty(Double qty) {
            this.Qty = Qty;
        }
      public Double getRate() {
            return Rate;
        }

        public void setRate(Double Rate) {
            this.Rate = Rate;

        }
        
    
    }
     public static class saleProdList implements SerializableRead {

         private String billId;
        private String customer;
        private String closedSaleSeq;
        private Double amount;
        private Date createddate;
        private String cname;
        private Double toatalAmt;
        private String rolename;
        private String prodid;
        private Double qty;
         private Double rate;

           public void readValues(DataRead dr) throws BasicException {
                billId = dr.getString(1);
            customer = dr.getString(2);
            closedSaleSeq = dr.getString(3);
            amount = dr.getDouble(4);
            createddate = dr.getTimestamp(5);
            cname = dr.getString(6);
            rolename  = dr.getString(7);
            prodid = dr.getString(8);
            qty = dr.getDouble(9);
             rate = dr.getDouble(10);
            createddate = dr.getTimestamp(11);
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getBillId() {
            return billId;
        }

        public void setBillId(String billId) {
            this.billId = billId;
        }

        public String getClosedSaleSeq() {
            return closedSaleSeq;
        }

        public void setClosedSaleSeq(String closedSaleSeq) {
            this.closedSaleSeq = closedSaleSeq;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public Double getToatalAmt() {
            return toatalAmt;
        }

        public void setToatalAmt(Double toatalAmt) {
            this.toatalAmt = toatalAmt;
        }


        public Date getCreateddate() {
            return createddate;
        }

        public void setCreateddate(Date createddate) {
            this.createddate = createddate;
        }

        public String getProdid() {
            return prodid;
        }

        public void setProdid(String prodid) {
            this.prodid = prodid;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }
      public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;

        }


    }
}
