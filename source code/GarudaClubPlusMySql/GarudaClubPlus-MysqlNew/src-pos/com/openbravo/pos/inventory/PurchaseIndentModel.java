/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteInteger;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class PurchaseIndentModel {

    private List<PurchaseIndentLine> puIndLine;
    private List<PurchaseIndentLine1> purInd;
    private final static String[] INDENTHEADERS = {null, "Product", "Curr.Stk", "ROL", "Min.Stk", "Max.Stk", "Avg.Sale/m", "Ind.Qty.", "Ind.Rate", "Amount", "Vendor", "Remarks"};
    private final static String[] PURCHASEINDENTHEADERS = {"Product", "ROQ", "Ind.Qty.", "Prv.Rate", "Ind.Rate", "Vendor", "Remarks"};
    private List<PrintPurchaseIndent> printIndent;

    public PurchaseIndentModel() {
    }

    public static PurchaseIndentModel emptyInstance() {
        PurchaseIndentModel p = new PurchaseIndentModel();
        p.puIndLine = new ArrayList<PurchaseIndentLine>();
        return p;
    }

    public static PurchaseIndentModel loadInstance(AppView app, DataLogicFacilities dlfac, String loc) throws BasicException {
        PurchaseIndentModel p = new PurchaseIndentModel();
        Object obj = new StaticSentence(app.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(loc);
        String locId = null;
        if (obj != null) {
            locId = obj.toString();
        }
        List<PurchaseIndentLine> purLine = new StaticSentence(app.getSession(),
                //"SELECT P.NAME,S.UNITS,P.REORDERLEVEL,P.MINSTOCKLEVEL,P.MAXSTOCKLEVEL,P.REORDERQUANTITY,P1.RATE,P.ID  FROM PRODUCTS P,STOCKCURRENT S,PURCHASEJOURNAL P1 WHERE S.PRODUCT=P.ID AND S.UNITS<P.REORDERLEVEL AND S.LOCATION=? AND P1.ITEM=P.ID GROUP BY P.ID",
                "SELECT P.NAME,S.UNITS,P.REORDERLEVEL,P.MINSTOCKLEVEL,P.MAXSTOCKLEVEL,P.REORDERQUANTITY,P1.RATE,P.ID,V.NAME   FROM PRODUCTS P,STOCKCURRENT S,PURCHASEJOURNAL P1,VENDOR V,PURCHASEJOURNALMAIN PM WHERE S.PRODUCT=P.ID AND S.UNITS<P.REORDERLEVEL AND S.LOCATION=? AND P1.ITEM=P.ID AND P1.PARENT=PM.ID AND PM.VENDOR=V.ID GROUP BY P.ID ORDER BY P.NAME",
                SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseIndentModel.PurchaseIndentLine.class)).list(locId);
        if (purLine == null) {
            p.puIndLine = new ArrayList<PurchaseIndentLine>();
        } else {
            p.puIndLine = purLine;
        }

        for (PurchaseIndentLine pl : purLine) {
            int quantity = p.getAvearageQty(app, pl.getPid(), locId);
            if (quantity < 12) {
                quantity = 1;
            } else {
                quantity = quantity / 12;
            }
            pl.setAverageQty(quantity);

        }
        return p;
    }

    public static PurchaseIndentModel loadIndentsToPrint(AppView app, int seq) throws BasicException {
        PurchaseIndentModel p = new PurchaseIndentModel();
        List<PrintPurchaseIndent> l = new StaticSentence(app.getSession(), "SELECT PR.NAME,P.INTQTY,P.APPQTY,P.INTRATE,P.APPRATE,V.NAME,P.REMARKS FROM PURCHASEINDENTDETAILS P,PURCHASEINDENT PI,PRODUCTS PR,VENDOR V WHERE P.PRODUCTID=PR.ID AND V.ID=P.APPVENDOR AND P.PURCHASEINDENTID=PI.ID AND PI.SEQNO=? AND P.FORWARDEDBY IS NULL AND P.APPROVEDBY IS NULL",
                SerializerWriteInteger.INSTANCE, new SerializerReadClass(PrintPurchaseIndent.class)).list(seq);
        if (l == null) {
            p.printIndent = new ArrayList<PrintPurchaseIndent>();
        } else {
            int i=1;
            for(PrintPurchaseIndent pi:l){
                pi.setSlno(i);
                i++;
            }
            p.printIndent = l;

        }
        return p;
    }

    public int getAvearageQty(AppView app, String pid, String locid) throws BasicException {
        int qty = 0;
        Object obj = new StaticSentence(app.getSession(), "SELECT SUM(QTY) FROM PRODUCTWISECLOSESALE WHERE PRODUCT IN(SELECT PRODUCTSCN FROM CONVERTER WHERE PRODUCTFST=? AND LOCATIONFST=?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), SerializerReadString.INSTANCE).find(new Object[]{pid, locid});
        if (obj != null) {
            qty = Integer.parseInt(obj.toString());
        }
        return qty;
    }

    public void setPuIndLine(List<PurchaseIndentLine> puIndLine) {
        this.puIndLine = puIndLine;
    }

    public List<PurchaseIndentLine> getPurchseindentList() {
        return puIndLine;
    }

    public List<PurchaseIndentLine1> getPurInd() {
        return purInd;
    }

    public void setPurInd(List<PurchaseIndentLine1> purInd) {
        this.purInd = purInd;
    }

    public List<PrintPurchaseIndent> getPrintPurchaseIndents() {
        return printIndent;
    }

    public abstract class MyAbstractTableModel extends AbstractTableModel {

        protected JTextField total;

        public void settext(JTextField text) {
            total = text;
        }
    }

    public MyAbstractTableModel getPurchaseIndentTable() {
        return new MyAbstractTableModel() {

            public Class[] getTypes() {
                return types;
            }

            public int getRowCount() {
                return puIndLine.size();
            }

            public int getColumnCount() {
                return INDENTHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, javax.swing.JComboBox.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, false, false, false, false, false, false, false, false, true, false
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(INDENTHEADERS[column]);
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                PurchaseIndentLine p = puIndLine.get(rowIndex);
                if (columnIndex == 7) {
                    if (aValue != null) {
                        try {
                            p.setIndentQty(Integer.parseInt(aValue.toString()));
                            p.setTotal(p.getIndentQty() * p.getIndentRate());
                            fireTableDataChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Enter qty in numbers");
                        }
                    }

                } else if (columnIndex == 8) {
                    if (aValue != null) {
                        try {
                            p.setIndentRate(Double.parseDouble(aValue.toString()));
                            p.setTotal(p.getIndentQty() * p.getIndentRate());
                            fireTableDataChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Enter amount in numbers");
                        }
                    }

                } else if (columnIndex == 0) {
                    if (aValue != null) {
                        p.setIsSelected(Boolean.parseBoolean(aValue.toString()));
                        fireTableDataChanged();
                    }

                } else if (columnIndex == 10) {
                    if (aValue != null) {
                        p.setAppvendor(aValue.toString());
                        fireTableDataChanged();
                    }

                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseIndentLine p = puIndLine.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return p.isIsSelected();
                    case 1:
                        return p.getPname();
                    case 2:
                        return p.getUnits();
                    case 3:
                        return p.getRol();
                    case 4:
                        return p.getMinstocklevel();
                    case 5:
                        return p.getMaxstocklevel();
                    case 6:
                        return p.getAverageQty();
                    case 7:
                        return p.getIndentQty();
                    case 8:
                        return p.getIndentRate();
                    case 9:
                        return p.getTotal();
                    case 10:
                        return p.getAppvendor();
                    case 11:
                        return p.getRemark();
                }
                return p;
            }
        };

    }

    public AbstractTableModel getPurchaseIndentTableModel() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return purInd.size();
            }

            public int getColumnCount() {
                return PURCHASEINDENTHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true, false
            };

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(PURCHASEINDENTHEADERS[column]);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                //PurchaseIndentLine1 p1 = null;
                if (rowIndex >= 0) {
                    PurchaseIndentLine1 p = purInd.get(rowIndex);
                    switch (columnIndex) {
                        case 0:
                            if (aValue != null) {
                                p.setPrdtName(aValue.toString());
                                fireTableDataChanged();
                                break;
                            }
                        case 1:
                            if (aValue != null) {
                                p.setQty(Integer.parseInt(aValue.toString()));
                                fireTableDataChanged();
                                break;
                            }
                        case 2:
                            if (aValue != null) {
                                p.setOrderedqty(Integer.parseInt(aValue.toString()));
                                fireTableDataChanged();
                                break;
                            }
                        case 3:
                            if (aValue != null) {
                                p.setRate(Double.parseDouble(aValue.toString()));
                                fireTableDataChanged();
                                break;
                            }
                        case 4:
                            if (aValue != null) {
                                p.setOrderedrate(Double.parseDouble(aValue.toString()));
                                fireTableDataChanged();
//                                p1 = new PurchaseIndentLine1();
//                                purInd.add(p1);
                                break;
                            }
                        case 5:
                            if (aValue != null) {
                                if (p.getIntvendor() == null) {
                                    p.setIntvendor(aValue.toString());
                                }
                                p.setAppvendor(aValue.toString());
                                fireTableDataChanged();
                                break;
                            }
                        case 7:
                            if (aValue != null) {
                                p.setProductid(aValue.toString());
                                fireTableDataChanged();
                                break;
                            }
                        case 8:
                            if (aValue != null) {
                                p.setMinStockLevel(Integer.parseInt(aValue.toString()));
                                fireTableDataChanged();
                                break;
                            }
                        case 9:
                            if (aValue != null) {
                                p.setMaxStockLevel(Integer.parseInt(aValue.toString()));
                                fireTableDataChanged();
                                break;
                            }
                        case 10:
                            if (aValue != null) {
                                p.setStock(Double.parseDouble(aValue.toString()));
                                fireTableDataChanged();
                                break;
                            }
                        case 11:
                            if (aValue != null) {
                                p.setIntvendor(aValue.toString());
                                fireTableDataChanged();
                                break;
                            }

                    }
                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseIndentLine1 pline1 = purInd.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return pline1.getPrdtName();
                    case 1:
                        return pline1.getQty();
                    case 2:
                        return pline1.getOrderedqty();
                    case 3:
                        return pline1.getRate();
                    case 4:
                        return pline1.getOrderedrate();
                    case 5:
                        return pline1.getAppvendor();
                    case 6:
                        return pline1.getRemarks();
                    case 7:
                        return pline1.getProductid();

                }
                return pline1;
            }
        };
    }

    public static class PurchaseIndentLine implements SerializableRead {

        private String pname;
        private Double units;
        private int rol;
        private int minstocklevel;
        private int maxstocklevel;
        private int roq;
        private boolean isSelected;
        private Double rate;
        private int averageQty;
        private Double total;
        private String pid;
        private String remark;
        private String appvendor;
        private String appvendorId;
        private int indentQty;
        private Double indentRate;
        private String intvendor;
        private String intvendorid;

        public void readValues(DataRead dr) throws BasicException {
            pname = dr.getString(1);
            units = dr.getDouble(2);
            rol = dr.getInt(3);
            minstocklevel = dr.getInt(4);
            maxstocklevel = dr.getInt(5);
            roq = dr.getInt(6);
            rate = dr.getDouble(7);
            pid = dr.getString(8);
            indentQty = dr.getInt(6);
            indentRate = dr.getDouble(7);
            intvendor = dr.getString(9);
            appvendor = intvendor;
            isSelected = false;
        }

        public String getAppvendor() {
            return appvendor;
        }

        public void setAppvendor(String appvendor) {
            this.appvendor = appvendor;
        }

        public String getAppvendorId() {
            return appvendorId;
        }

        public void setAppvendorId(String appvendorId) {
            this.appvendorId = appvendorId;
        }

        public String getIntvendor() {
            return intvendor;
        }

        public void setIntvendor(String intvendor) {
            this.intvendor = intvendor;
        }

        public String getIntvendorid() {
            return intvendorid;
        }

        public void setIntvendorid(String intvendorid) {
            this.intvendorid = intvendorid;
        }

        public int getIndentQty() {
            return indentQty;
        }

        public void setIndentQty(int indentQty) {
            this.indentQty = indentQty;
        }

        public Double getIndentRate() {
            return indentRate;
        }

        public void setIndentRate(Double indentRate) {
            this.indentRate = indentRate;
        }

        public String getRemark() {
            if (getIndentQty() < getMinstocklevel()) {
                setRemark("Below Min.Stock");
            } else if (getIndentQty() > getMaxstocklevel()) {
                setRemark("Exceeds Max.Stock");
            } else {
                setRemark("Stock is " + String.valueOf(getUnits()));
            }
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getAverageQty() {
            return averageQty;
        }

        public void setAverageQty(int averageQty) {
            this.averageQty = averageQty;
        }

        public Double getTotal() {
            return getIndentQty() * getIndentRate();
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public String getPid() {
            return pid;
        }

        public Double getRate() {
            return rate;
        }

        public void setRoq(int roq) {
            this.roq = roq;
        }

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getPname() {
            return pname;
        }

        public int getRol() {
            return rol;
        }

        public int getMaxstocklevel() {
            return maxstocklevel;
        }

        public int getMinstocklevel() {
            return minstocklevel;
        }

        public Double getUnits() {
            return units;
        }

        public int getRoq() {
            return roq;
        }
    }

    public static class PurchaseIndentLine1 {

        private String productid;
        private int qty;
        private int appqty;
        private Double rate;
        private Double apprate;
        private String remarks;
        private String prdtName;
        private int minStockLevel;
        private int maxStockLevel;
        private Double stock;
        private String intvendor;
        private String intvendorid;
        private String appvendor;
        private String appvendorId;

        public String getAppvendor() {
            return appvendor;
        }

        public void setAppvendor(String appvendor) {
            this.appvendor = appvendor;
        }

        public String getAppvendorId() {
            return appvendorId;
        }

        public void setAppvendorId(String appvendorId) {
            this.appvendorId = appvendorId;
        }

        public String getIntvendor() {
            return intvendor;
        }

        public void setIntvendor(String intvendor) {
            this.intvendor = intvendor;
        }

        public String getIntvendorid() {
            return intvendorid;
        }

        public void setIntvendorid(String intvendorid) {
            this.intvendorid = intvendorid;
        }

        public Double getStock() {
            return stock;
        }

        public void setStock(Double stock) {
            this.stock = stock;
        }

        public int getMaxStockLevel() {
            return maxStockLevel;
        }

        public void setMaxStockLevel(int maxStockLevel) {
            this.maxStockLevel = maxStockLevel;
        }

        public int getMinStockLevel() {
            return minStockLevel;
        }

        public void setMinStockLevel(int minStockLevel) {
            this.minStockLevel = minStockLevel;
        }

        public int getOrderedqty() {
            return appqty;
        }

        public void setOrderedqty(int orderedqty) {
            this.appqty = orderedqty;
        }

        public Double getOrderedrate() {
            return apprate;
        }

        public void setOrderedrate(Double orderedrate) {
            this.apprate = orderedrate;
        }

        public String getPrdtName() {
            return prdtName;
        }

        public void setPrdtName(String prdtName) {
            this.prdtName = prdtName;
        }

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public String getRemarks() {
            if (remarks != null) {
                return remarks;
            } else {
                if (getPrdtName() != null) {
                    if (getOrderedqty() < getMinStockLevel()) {
                        setRemarks("Below Min.Stock");
                    } else if (getOrderedqty() > getMaxStockLevel()) {
                        setRemarks("Exceeds Max.Stock");
                    } else {
                        setRemarks("Stock is " + getStock().toString());
                    }
                    return remarks;
                } else {
                    return "";
                }
            }
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }

    public static class PrintPurchaseIndent implements SerializableRead {

        private String product;
        private int qty;
        private int appqty;
        private Double rate;
        private Double apprate;
        private String remarks;
        private String appvendor;
        private int slno;

        public void readValues(DataRead dr) throws BasicException {
            product = dr.getString(1);
            qty = dr.getInt(2);
            appqty = dr.getInt(3);
            rate = dr.getDouble(4);
            apprate = dr.getDouble(5);
            appvendor = dr.getString(6);
            remarks = dr.getString(7);
        }

        public int getSlno() {
            return slno;
        }

        public void setSlno(int slno) {
            this.slno = slno;
        }

        public int getAppqty() {
            return appqty;
        }

        public Double getApprate() {
            return apprate;
        }

        public String getAppvendor() {
            return appvendor;
        }

        public String getProduct() {
            return product;
        }

        public int getQty() {
            return qty;
        }

        public Double getRate() {
            return rate;
        }

        public String getRemarks() {
            return remarks;
        }
    }
}
