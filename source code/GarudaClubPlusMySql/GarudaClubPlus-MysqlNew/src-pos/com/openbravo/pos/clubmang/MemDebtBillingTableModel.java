/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.lowagie.text.Header;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
//import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.util.StringUtils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MemDebtBillingTableModel {

    private List<Facilityline> fac;
    private List<Creditline> creditlist;
    private DataLogicFacilities dlfac;
    private Double creditamount;
    private Double debttotal = 0.0;
    private String creditentry;
    private String credittransno;
    private final static String[] FACILITYHEADERS = {"Facility Name", "Billed Date", "Due Date", "Debt", "Narration", "Amount", null, "Org. Amt"};
    private final static String[] HEADERS = {"Particular", "No.", "Date", "Narration", "Amount", null, "Org. Amt"};
    private final static String[] FACILITYHEADERSMEM = {"Facility Name", "Billed Date", "Due Date", "Debt", "Narration", "Org. Amt"};
    private final static String[] HEADERSMEM = {"Particular", "No.", "Date", "Narration", "Org. Amt"};

    private MemDebtBillingTableModel() {
    }

    public static MemDebtBillingTableModel emptyinstance() {
        MemDebtBillingTableModel d = new MemDebtBillingTableModel();
        d.fac = new ArrayList<Facilityline>();
        d.creditlist = new ArrayList<Creditline>();
        d.creditamount = 0.0;
        d.debttotal = 0.0;
        d.creditentry = null;
        d.credittransno = null;
        return d;
    }

    public static MemDebtBillingTableModel loadInstance(AppView app, String ID, String accid, DataLogicFacilities dlfac) throws BasicException {
        MemDebtBillingTableModel d = new MemDebtBillingTableModel();
        d.dlfac = dlfac;
        //com.openbravo.format.Formats.CURRENCY.formatValue(app);
        /*List dlist = new StaticSentence(app.getSession()
        ,"SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT FROM( "
        +" SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO,A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT "
        +" FROM ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? AND A.CLEARDATE IS NULL AND A.ACTIVE=TRUE AND  A.ADJUSTED=FALSE AND A.TRANSTYPE='D'    "
        +" UNION ALL SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO,A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT "
        +" FROM ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID=? AND  A.CLEARDATE IS NULL AND A.ACTIVE=TRUE  AND A.ADJUSTED=FALSE AND A.TRANSTYPE='D'   AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY)  "
        +" ) ORDER BY 8"
        ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
        ,new SerializerReadClass( MemDebtBillingTableModel.Facilityline.class )).list(new Object[]{accid,accid});*/


        List dlist = new StaticSentence(app.getSession()//Added to replace above commented qry
                , "SELECT NAME,DUEDATE,AMOUNT,AID,TRANSNO,ACCOUNT,NARRATION,DATE,FID,AMT FROM(" + " SELECT F.NAME AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO,A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,F.ID AS FID,A.AMOUNT AS AMT" + " FROM ACCOUNTJOURNAL A JOIN FACILITY F ON  F.ID=A.TRANSREF WHERE  A.ACCOUNTID=? AND A.CLEARDATE IS NULL AND A.ACTIVE=TRUE AND  A.ADJUSTED=FALSE AND A.TRANSTYPE='D' " + " UNION ALL SELECT A.TRANSREF AS NAME,A.DUEDATE AS DUEDATE,A.BALANCEAMOUNT AS AMOUNT,A.ID AS AID,A.TRANSNO AS TRANSNO,A.ACCOUNTID AS ACCOUNT,A.NARRATION AS NARRATION,A.DATE AS DATE,A.TRANSREF AS FID,A.AMOUNT AS AMT " + " FROM ACCOUNTJOURNAL A  WHERE  A.ACCOUNTID= ? AND  A.CLEARDATE IS NULL AND A.ACTIVE=TRUE  AND A.ADJUSTED=FALSE AND A.TRANSTYPE='D' AND A.TRANSREF NOT IN (SELECT ID FROM FACILITY) " + " UNION ALL SELECT F.NAME AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO,AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,F.ID AS FID,AJ.AMOUNT AS AMT " + " FROM AJUNADJUSTED AJ JOIN FACILITY F ON  F.ID=AJ.TRANSREF WHERE  AJ.ACCOUNTID=? AND AJ.CLEARDATE IS NULL AND AJ.ACTIVE=TRUE AND  AJ.ADJUSTED=FALSE AND AJ.TRANSTYPE='D' " + " UNION ALL SELECT AJ.TRANSREF AS NAME,AJ.DUEDATE AS DUEDATE,AJ.BALANCEAMOUNT AS AMOUNT,AJ.ID AS AID,AJ.TRANSNO AS TRANSNO,AJ.ACCOUNTID AS ACCOUNT,AJ.NARRATION AS NARRATION,AJ.DATE AS DATE,AJ.TRANSREF AS FID,AJ.AMOUNT AS AMT " + " FROM AJUNADJUSTED AJ WHERE  AJ.ACCOUNTID=? AND  AJ.CLEARDATE IS NULL AND AJ.ACTIVE=TRUE  AND AJ.ADJUSTED=FALSE AND AJ.TRANSTYPE='D'   AND AJ.TRANSREF NOT IN (SELECT ID FROM FACILITY) " + " ) AS COLLTRANS ORDER BY 8 ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(MemDebtBillingTableModel.Facilityline.class)).list(new Object[]{accid, accid, accid, accid});
        if (dlist == null) {
            d.fac = new ArrayList<Facilityline>();
        } else {
            d.fac = dlist;
        }

        for (MemDebtBillingTableModel.Facilityline f : d.fac) {
            d.debttotal += f.getamount();
        }
        if (d.debttotal > 0) {
            d.debttotal = dlfac.roundTwoDecimals(d.debttotal);
        }
        List<Creditline> obj = new StaticSentence(app.getSession(), "SELECT ID,BALANCEAMOUNT,TRANSNO,NARRATION,DATE,TRANSREF,AMOUNT FROM ACCOUNTJOURNAL WHERE ADJUSTED=FALSE AND BALANCEAMOUNT>0 AND TRANSTYPE='C' AND ACCOUNTID=? AND ACTIVE=TRUE ORDER BY DATE", SerializerWriteString.INSTANCE, new SerializerReadClass(MemDebtBillingTableModel.Creditline.class)).list(accid);
        if (obj.size() > 0) {
            //  Object[] obj1=(Object[])obj.get(0);
            d.creditlist = obj;
        // d.creditamount=obj.get(0).getTotal();
        } else {
            //  d.creditamount=0.0;
            d.creditlist = new ArrayList();
        }
        d.creditamount = 0.0;
        for (MemDebtBillingTableModel.Creditline c : d.creditlist) {
            d.creditamount += c.getamount();
        }
        if (d.creditamount > 0) {
            d.creditamount = dlfac.roundTwoDecimals(d.creditamount);
        }
        d.creditentry = null;
        d.credittransno = null;
        return d;

    }

    public List<Facilityline> getfacilityline() {
        return fac;
    }

    public void setfacilityline(List<Facilityline> fac) {
        this.fac = fac;
    }

    public String getCreditEntry() {
        return creditentry;
    }

    public String getCreditTransno() {
        return credittransno;
    }

    public List<Creditline> getCreditList() {
        return creditlist;
    }

    public Double getcreditAmount() {
        return creditamount;
    }

    public Double getdebtAmount() {
        return debttotal;
    }

    public List<Facilityline> addfacility(Facilityline f) {
        fac.add(f);
        return fac;
    }

    public Facilityline newLine() {
        Facilityline f = new Facilityline();
        return f;
    }

    public abstract class MyAbstractTableModel extends AbstractTableModel {

        protected JTextField total;

        public void settext(JTextField text) {
            total = text;
        }
    }

    public MyAbstractTableModel getTableModel() {
        return new MyAbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(FACILITYHEADERS[column]);
                return (FACILITYHEADERS[column]);
            }

            public void settext(JTextField text) {
                total = text;
            }

            public int getRowCount() {
                return fac.size();
            }

            public int getColumnCount() {

                return FACILITYHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                Facilityline l = fac.get(row);
                if (column == 5) {
                    l.setAmountb(dlfac.roundTwoDecimals(Double.parseDouble(aValue.toString())));
                // fireTableDataChanged();
                } else if (column == 7) {
                    l.setStatus(Boolean.parseBoolean(aValue.toString()));
                // fireTableDataChanged();
                } else if (column == 6) {
                    double oamt = l.getamountb();
                    double namt = l.getamount();
                    boolean status = Boolean.parseBoolean(aValue.toString());
                    if (status) {
                        l.setAmountb(dlfac.roundTwoDecimals(l.getamount()));
                        l.setStatus(status);
                    } else {
                        namt = 0.0;
                        l.setAmountb(0.0);
                        l.setStatus(status);
                    }
                    Double totalamt = Double.parseDouble(total.getText()) - oamt + namt;
                    total.setText(String.valueOf(dlfac.roundTwoDecimals(totalamt)));
                    fireTableDataChanged();
                }
            }

            public Object getValueAt(int row, int column) {
                Facilityline l = fac.get(row);

                switch (column) {

                    case 0:
                        return l.getfname();
                    case 1:
                        return Formats.DATE.formatValue(l.getBilledDate());
                    case 2:
                        return Formats.DATE.formatValue(l.getduedate());
                    case 3:
                        return dlfac.roundTwoDecimals(l.getamount());
                    case 4:
                        return l.getNarration();
                    case 5:
                        return l.getamountb();
                    case 6:
                        return l.isSelected();
                    case 7:
                        return l.getamt();
                    case 8:
                        return l.getaccid();
                    case 9:
                        return l.gettransno();
                    case 10:
                        return l.getcustomeraccount();
                    case 11:
                        return l.getFacilityId();

                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel getMemDebitTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(FACILITYHEADERSMEM[column]);
            }

            public int getRowCount() {
                return fac.size();
            }

            public int getColumnCount() {

                return FACILITYHEADERSMEM.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            public Object getValueAt(int row, int column) {
                Facilityline l = fac.get(row);

                switch (column) {

                    case 0:
                        return l.getfname();
                    case 1:
                        return Formats.DATE.formatValue(l.getBilledDate());
                    case 2:
                        return Formats.DATE.formatValue(l.getduedate());
                    case 3:
                        return dlfac.roundTwoDecimals(l.getamount());
                    case 4:
                        return l.getNarration();
                    case 5:
                        return l.getamt();


                    default:
                        return null;
                }
            }
        };
    }

    public MyAbstractTableModel getCreditTableModel() {
        return new MyAbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(HEADERS[column]);
                return (HEADERS[column]);
            }

            @Override
            public void settext(JTextField text) {
                total = text;
            }

            public int getRowCount() {
                return creditlist.size();
            }

            public int getColumnCount() {

                return HEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                Creditline l = creditlist.get(row);
                if (column == 5) {
                    if (aValue != null) {
                        for (Creditline line : creditlist) {
                            line.setSelected(false);
                        }
                        boolean status = Boolean.parseBoolean(aValue.toString());
                        l.setSelected(status);
                        creditentry = l.getfname();
                        credittransno = l.getTransno();
                        if (status == true) {
                            total.setText(String.valueOf(dlfac.roundTwoDecimals(l.getamount())));
                        } else {
                            total.setText("0.0");
                        }
                        fireTableDataChanged();
                    }
                // fireTableDataChanged();
                }

            }

            public Object getValueAt(int row, int column) {
                Creditline l = creditlist.get(row);

                switch (column) {

                    case 0:
                        return l.getTransref();
                    case 1:
                        return l.getTransno();
                    case 2:
                        return Formats.DATE.formatValue(l.getDate());
                    case 3:
                        return l.getNarration();
                    case 4:
                        return l.getamount();
                    case 5:
                        return l.isSelected();
                    case 6:
                        return l.getAmt();
                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel getMemCreditTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERSMEM[column]);
            }

            public int getRowCount() {
                return creditlist.size();
            }

            public int getColumnCount() {

                return HEADERSMEM.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            public Object getValueAt(int row, int column) {
                Creditline l = creditlist.get(row);

                switch (column) {
                    case 0:
                        return l.getTransref();
                    case 1:
                        return l.getTransno();
                    case 2:
                        return Formats.DATE.formatValue(l.getDate());
                    case 3:
                        return l.getNarration();
                    case 4:
                        return l.getamount();
                    case 5:
                        return l.getAmt();
                    default:
                        return null;
                }
            }
        };
    }

    public static class Facilityline implements SerializableRead {

        private String fname;
        private Double amount;
        private Timestamp duedate;
        private String Narration;
        private Double amttobebilled;
        private String accountjournalid;
        private String transno;
        private String caccount;
        private Timestamp date;
        private String fid;
        private boolean selected;
        private Double amt;
        private Double bal;

        public void readValues(DataRead dr) throws BasicException {
            fname = dr.getString(1);
            duedate = dr.getTimestamp(2);
            amount = dr.getDouble(3);
            amttobebilled = 0.0;
            accountjournalid = dr.getString(4);
            transno = dr.getString(5);
            caccount = dr.getString(6);
            Narration = dr.getString(7);
            date = dr.getTimestamp(8);
            fid = dr.getString(9);
            amt = dr.getDouble(10);
            selected = false;
        }

        public Double getamt() {
            return amt;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setStatus(boolean status) {
            selected = status;
        }

        public String getFacilityId() {
            return fid;
        }

        public Timestamp getBilledDate() {
            return date;
        }

        public String printBilledDate() {
            return Formats.DATE.formatValue(date);
        }

        public String getNarration() {
            return Narration;
        }

        public String getcustomeraccount() {
            return caccount;
        }

        public String getaccid() {
            return accountjournalid;
        }
        // public Double getdebttotal(){
        //     return Debttotal;
        //  }

        public void setaccid(String accid) {
            accountjournalid = accid;
        }

        public String gettransno() {
            return transno;
        }

        public void settransno(String tno) {
            transno = tno;
        }

        public Timestamp getduedate() {
            return duedate;
        }

        public void setduedate(Timestamp duedate) {
            this.duedate = duedate;
        }

        public void setNarration(String Narration) {
            this.Narration = Narration;
        }

        public String printNarration() {
            return StringUtils.encodeXML(Narration);
        }
        /*
        public Timestamp getlbilldate(){
        return lbdate;
        }*/

        public String getBalance() {
            return String.valueOf(roundTwoDecimals(amount - amttobebilled));
        }

        public Double getamount() {
            return amount;
        }

        public void setamount(Double amt) {
            this.amount = amt;
        }

        public String printfname() {
            return StringUtils.encodeXML(fname);
        }

        public void setfname(String name) {
            this.fname = name;
        }

        public String getfname() {
            return fname;
        }

        public Double getamountb() {
            return amttobebilled;
        }

        public String printamountb() {
            return String.valueOf(roundTwoDecimals(amttobebilled));
        }

        public String printamount() {
            return String.valueOf(roundTwoDecimals(amount));
        }

        public String printOrgamount() {
            return String.valueOf(roundTwoDecimals(amt));
        }

        public String printbalance() {

            return String.valueOf(roundTwoDecimals(amount - amttobebilled));
        }

        public String roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#,###0.00");
            return twoDForm.format(d);
        }

        public void setAmountb(Double amt) {
            try {
                amttobebilled = amt;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Error", "Error", JOptionPane.OK_OPTION);
            }
        }
    }

    public static class Creditline implements SerializableRead {

        private String id;
        private Double amount;
        private Double total;
        private String transno;
        private String transref;
        private String Narration;
        private Timestamp date;
        private boolean selected;
        private Double amt;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            amount = dr.getDouble(2);
            transno = dr.getString(3);
            Narration = dr.getString(4);
            date = dr.getTimestamp(5);
            transref = dr.getString(6);
            selected = false;
            amt = dr.getDouble(7);
        }

        public Double getAmt() {
            return amt;
        }

        public Double getamount() {
            return amount;
        }

        public Double getTotal() {
            return total;
        }

        public String getTransno() {
            return transno;
        }

        public String getTransref() {
            return transref;
        }

        public String getNarration() {
            return Narration;
        }

        public Timestamp getDate() {
            return date;
        }

        public String getfname() {
            return id;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean status) {
            selected = status;
        }

        public String printDate() {
            return Formats.DATE.formatValue(date);
        }

        public String printamount() {
            return String.valueOf(roundTwoDecimals(amount));
        }

        public String printOrgamount() {
            return String.valueOf(roundTwoDecimals(amt));
        }

        public String printNarration() {
            return StringUtils.encodeXML(Narration);
        }

        public String roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#,###0.00");
            return twoDForm.format(d);
        }
    }
}

