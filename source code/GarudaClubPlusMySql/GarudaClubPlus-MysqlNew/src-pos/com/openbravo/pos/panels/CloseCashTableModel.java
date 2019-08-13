/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class CloseCashTableModel {

    private List<ClosedCashLine> closedCash;
    private final static String[] BILLSHEADERS = {"Sequence", "DateStart", "DateEnd", "User", "Host"};
    private List<CashList> receiptDetails;
    private final static String[] BILLDETAILHEADERS = {"ReceiptNo", "Date", "User", "Description", "Amount"};
    private Double cashTotal = 0.0;

    private CloseCashTableModel() {
    }

    public static CloseCashTableModel emptyinstance() {

        CloseCashTableModel c = new CloseCashTableModel();
        c.closedCash = new ArrayList<ClosedCashLine>();
        return c;


    }

    public static CloseCashTableModel loadInstantce(AppView app, Date fdate, Date todate, String user) throws BasicException {
        CloseCashTableModel c = new CloseCashTableModel();
        c.closedCash = new ArrayList<ClosedCashLine>();
        List<ClosedCashLine> receiptList = (List<ClosedCashLine>) new StaticSentence(app.getSession(), "SELECT C.HOSTSEQUENCE,C.DATESTART,C.DATEEND,P.NAME,C.HOST,C.USER_,C.MONEY FROM PEOPLE P,CLOSEDCASH C WHERE P.ID=C.USER_ AND P.NAME=? AND DATESTART BETWEEN ? AND ? ORDER BY HOSTSEQUENCE" //,null,new SerializerReadClass(CloseCashTableModel.ClosedCashLine.class)).list();
                , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(CloseCashTableModel.ClosedCashLine.class)).list(new Object[]{user, fdate, todate});
        if (receiptList == null) {
            c.closedCash = new ArrayList<ClosedCashLine>();
        } else {
            c.closedCash = receiptList;

        }
        return c;

    }

    public static CloseCashTableModel loadInstanceOfCash(AppView app, Date fdate, Date todate, String seq, String user) throws BasicException {
        CloseCashTableModel c1 = new CloseCashTableModel();
        c1.receiptDetails = new ArrayList<CashList>();
        List<CashList> saleDetailList = new StaticSentence(app.getSession(), "SELECT RID,RDATE,RUSER,descr,CSEQ,AMOUNT FROM (SELECT R.ID AS RID,R.DATENEW AS RDATE,R.RUSER AS RUSER,R.DESC_ AS DESCR,R.CLOSECASHSEQ AS CSEQ,P.TOTAL AS AMOUNT FROM RECEIPTS R,PAYMENTS P WHERE R.CLOSECASHSEQ=? AND P.RECEIPT=R.ID AND R.RUSER = ? AND R.DATENEW BETWEEN ? AND ?" +
                "UNION ALL" +
                " SELECT R.ID AS RID,R.DATENEW AS RDATE,R.RUSER AS RUSER,R.DESC_ AS DESCR,R.CLOSECASHSEQ AS CSEQ,P.TOTAL AS AMOUNT  FROM RECEIPTS_ARV R,PAYMENTS_ARV P WHERE  R.CLOSECASHSEQ=? AND P.RECEIPT=R.ID AND R.RUSER = ? AND R.DATENEW BETWEEN ? AND ? )AS CLOSECASH ORDER BY RID", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(CloseCashTableModel.CashList.class)).list(new Object[]{seq,user, fdate, todate, seq, user, fdate, todate});
        if (saleDetailList == null) {
            c1.receiptDetails = new ArrayList<CashList>();
        } else {
            c1.receiptDetails = saleDetailList;
        }
        return c1;
    }

    public Double getCashTotal() {
        CashList cl = null;
        cashTotal = 0.00;
        for (CashList c : getCashList()) {
            cashTotal = cashTotal + c.getAmount();
        }
        return cashTotal;

    }

    public List<ClosedCashLine> getClosedCashLine() {
        return closedCash;
    }

    public List<CashList> getCashList() {
        return receiptDetails;
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return closedCash.size();
            }

            public int getColumnCount() {
                return BILLSHEADERS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BILLSHEADERS[column]);
            }

            public Object getValueAt(int row, int col) {
                ClosedCashLine cl = closedCash.get(row);
                switch (col) {
                    case 0:
                        return cl.getHostsequence();
                    case 1:
                        return cl.getDateStart();

                    case 2:
                        return cl.getDateEnd();

                    case 3:
                        return cl.getUserName();

                    case 4:
                        return cl.getHost();

                    case 5:
                        return cl.getUser();

                    case 6:
                        return cl.getId();

                    case 7:
                        return cl.getMoney();

                }
                return cl;
            }
        };
    }

    public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return receiptDetails.size();

            }

            public int getColumnCount() {
                return BILLDETAILHEADERS.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BILLDETAILHEADERS[column]);

            }

            public Object getValueAt(int row, int col) {
                CashList rl = receiptDetails.get(row);
                switch (col) {
                    case 0:
                        return rl.getRid();
                    case 1:
                        return rl.getDatenew();
                    case 2:
                        return rl.getRuser();
                    case 3:
                        return rl.getDescription();
                    case 4:
                        return rl.getAmount();
                    

                }
                return rl;
            }
        };

    }

    public static class ClosedCashLine implements SerializableRead {

        private String id;
        private String hostsequence;
        private Date dateStart;
        private String money;
        private Date dateEnd;
        private String user;
        private String userName;
        private String host;

        public void readValues(DataRead dr) throws BasicException {
            //id=dr.getString(7);
            hostsequence = dr.getString(1);
            dateStart = dr.getTimestamp(2);
            dateEnd = dr.getTimestamp(3);
            userName = dr.getString(4);
            host = dr.getString(5);
            user = dr.getString(6);
            money = dr.getString(7);
        }

        public String getHost() {
            return host;
        }

        public Date getDateEnd() {
            return dateEnd;
        }

        public Date getDateStart() {
            return dateStart;
        }

        public String getHostsequence() {
            return hostsequence;
        }

        public String getId() {
            return id;
        }

        public String getMoney() {
            return money;
        }

        public String getUser() {
            return user;
        }

        public String getUserName() {
            return userName;
        }
    }

    public static class CashList implements SerializableRead {

        private String rid;
        private Date datenew;
        private String ruser;
        private String paymentref;
        private String description;
        private String closeCashSeq;
        private Double amount;       

        public void readValues(DataRead dr) throws BasicException {
            rid = dr.getString(1);
            datenew = dr.getTimestamp(2);
            ruser = dr.getString(3);
            description = dr.getString(4);
            closeCashSeq = dr.getString(5);
            amount = dr.getDouble(6);            
        // paymentref = dr.getString(7);

        }

        public String getCloseCashSeq() {
            return closeCashSeq;
        }

        public Date getDatenew() {
            return datenew;
        }

        public Double getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }

        public String getRid() {
            return rid;
        }

        public String getPaymentref() {
            return paymentref;
        }

        public String getRuser() {
            return ruser;
        }
    }

        
}
