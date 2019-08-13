/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
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
public class PurchaseOrderModel {

    private final static String[] COLUMNHEADERS = {null, "Product", "Qty", "Rate", "Total"};
    private final static String[] COLUMNHEADERS1 = {"Product", "Qty", "Rate", "Total"};
    private List<PurchaseOrderModel.PendingVendor> pvendor;
    private List<PurchaseOrderModel.PurchaseOrderLine> poline;
    private List<PurchaseOrderModel.PurchaseOrderLine> poline1;
    private Double grandTotal = 0.0;
    private DataLogicFacilities dlfac;
    private List<PurchaseOrderModel.PurchaseOrderLinePrint> printpo;

    public PurchaseOrderModel() {
    }

    public static PurchaseOrderModel loademptyInstance() {
        PurchaseOrderModel p = new PurchaseOrderModel();
        p.pvendor = new ArrayList<PendingVendor>();
        return p;
    }

    public static PurchaseOrderModel loadVendors(AppView app) throws BasicException {
        PurchaseOrderModel p = new PurchaseOrderModel();
        List l = new StaticSentence(app.getSession(), "SELECT V.ID,V.NAME,V.ADDRESS,V.CONTACTPERSON FROM PURCHASEINDENTDETAILS P,VENDOR V WHERE P.APPROVEDBY IS NOT NULL  AND P.APPVENDOR=V.ID  GROUP BY P.APPVENDOR", SerializerWriteString.INSTANCE, new SerializerReadClass(PendingVendor.class)).list();
        if (l == null) {
            p.pvendor = new ArrayList<PendingVendor>();
        } else {
            p.pvendor = l;
        }
        return p;
    }

    public static PurchaseOrderModel loadIndentedProducts(AppView app, String vendor, DataLogicFacilities dlfac) throws BasicException {
        PurchaseOrderModel p = new PurchaseOrderModel();
        p.dlfac = dlfac;
        List<PurchaseOrderLine> l = new StaticSentence(app.getSession(), "SELECT PD.ID,P.NAME,P.ID,PD.BALANCEINDQTY,PD.APPRATE,PD.REMARKS,PD.PURCHASEORDERREF FROM PURCHASEINDENTDETAILS PD,PRODUCTS P WHERE P.ID=PD.PRODUCTID AND PD.APPROVEDBY IS NOT NULL AND PD.APPVENDOR=? and pd.BALANCEINDQTY>0", SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseOrderLine.class)).list(vendor);
        if (l == null) {
            p.poline = new ArrayList<PurchaseOrderLine>();
        } else {
            p.poline = l;
        }
        for (PurchaseOrderLine pl : l) {
            pl.setTotal(dlfac.roundTwoDecimals(pl.getOrderedqty() * pl.getRate()));
        }
        return p;
    }

    public static PurchaseOrderModel loadPurchaseOrderProducts(AppView app, int purchaseOrderSeq) throws BasicException {
        PurchaseOrderModel p = new PurchaseOrderModel();
        List<PurchaseOrderLinePrint> l = new StaticSentence(app.getSession(), "SELECT P.NAME,PD.BALANCEQTY,PD.RATE FROM PURCHASEORDER PO,PURCHASEORDERDETAIL PD,PRODUCTS P WHERE PO.PURCHASEORDERNO=? AND PO.ID=PD.PURCHASEORDERID AND PD.PRODUCTID=P.ID", SerializerWriteInteger.INSTANCE, new SerializerReadClass(PurchaseOrderLinePrint.class)).list(purchaseOrderSeq);
        if (l == null) {
            p.printpo = new ArrayList<PurchaseOrderLinePrint>();
        } else {
            p.printpo = l;
        }
        int i=1;
        for(PurchaseOrderLinePrint pl:l){
            pl.setTotal(pl.getQty()*pl.getRate());
            pl.setSlNo(i);
            i++;
        }
        return p;
    }

    
    public PurchaseOrderModel calculategrandtotal() {
        PurchaseOrderModel p = new PurchaseOrderModel();
        for (PurchaseOrderLine pl : getPurchaseLine()) {
            if (pl.isSelect()) {
                grandTotal = grandTotal + pl.getTotal();
            }
        }
        return p;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<PendingVendor> getVendorList() {
        return pvendor;
    }

    public List<PurchaseOrderLine> getPurchaseLine() {
        return poline;
    }

    public List<PurchaseOrderLine> getPurchaseLine1() {
        poline1 = new ArrayList<PurchaseOrderLine>();
        for(PurchaseOrderLine p1:poline){
            if(p1.isSelect()){
                poline1.add(p1);
            }
        }
        return poline1;
    }

    public List<PurchaseOrderLinePrint> getPrintpo() {
        return printpo;
    }

    public abstract class MyAbstractTableModel extends AbstractTableModel {

        protected JTextField grandtotal;

        public void settext(JTextField text) {
            grandtotal = text;
        }
    }

    public MyAbstractTableModel getPurchaseModel() {
        return new MyAbstractTableModel() {

            public int getRowCount() {
                return poline.size();
            }

            public int getColumnCount() {
                return COLUMNHEADERS.length;
            }

            public void settext(JTextField text) {
                grandtotal = text;
            }

            public Class[] getTypes() {
                return types;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, false, false, false
            };

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(COLUMNHEADERS[column]);
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
                PurchaseOrderLine p = poline.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        double namt = p.getTotal();
                        if (Boolean.parseBoolean(aValue.toString())) {
                            p.setSelect(Boolean.parseBoolean(aValue.toString()));
                            Double totalamt = Double.parseDouble(grandtotal.getText()) + namt;
                            grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(totalamt)));
                            fireTableDataChanged();
                        } else {
                            p.setSelect(Boolean.parseBoolean(aValue.toString()));
                            Double totalamt = Double.parseDouble(grandtotal.getText()) - namt;
                            grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(totalamt)));
                        }
                        break;
                    case 2:                        
                        if (Integer.parseInt(aValue.toString()) <= p.getQty()) {
                            p.setOrderedqty(Integer.parseInt(aValue.toString()));
                            p.setTotal(p.getOrderedqty() * p.getRate());
                            fireTableDataChanged();
                        } else {
                            JOptionPane.showMessageDialog(null, "Qty should be less than " + p.getQty());
                        }
                        fireTableDataChanged();
                        break;
                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseOrderLine p = poline.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return p.isSelect();
                    case 1:
                        return p.getProduct();
                    case 2:
                        return p.getOrderedqty();
                    case 3:
                        return p.getRate();
                    case 4:
                        return p.getTotal();
                }
                return p;
            }
        };
    }

    public AbstractTableModel getPurchaseModel1() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return getPurchaseLine1().size();
            }

            public int getColumnCount() {
                return COLUMNHEADERS1.length;
            }

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(COLUMNHEADERS1[column]);
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseOrderLine p = getPurchaseLine1().get(rowIndex);
                    switch (columnIndex) {
                        case 0:
                            return p.getProduct();
                        case 1:
                            return p.getOrderedqty();
                        case 2:
                            return p.getRate();
                        case 3:
                            return p.getTotal();
                    }
                    return p;
            }
        };
    }

    public static class PurchaseOrderLine implements SerializableRead {

        private String pid;
        private String product;
        private String prdtid;
        private int qty;
        private int orderedqty;
        private double rate;
        private double total;
        private boolean select;
        private String remark;
        private String poref;

        public void readValues(DataRead dr) throws BasicException {
            pid = dr.getString(1);
            product = dr.getString(2);
            prdtid = dr.getString(3);
            qty = dr.getInt(4);
            orderedqty = qty;
            rate = dr.getDouble(5);
            select = false;
            remark = dr.getString(6);
            poref = dr.getString(7);
        }

        public String getPoref() {
            return poref;
        }

        public void setPoref(String poref) {
            this.poref = poref;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getOrderedqty() {
            return orderedqty;
        }

        public void setOrderedqty(int orderedqty) {
            this.orderedqty = orderedqty;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPrdtid() {
            return prdtid;
        }

        public void setPrdtid(String prdtid) {
            this.prdtid = prdtid;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }

    public static class PendingVendor implements SerializableRead {

        private String id;
        private String vname;
        private String vaddress;
        private String contactPerson;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            vname = dr.getString(2);
            vaddress = dr.getString(3);
            contactPerson = dr.getString(4);
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public String getVaddress() {
            return vaddress;
        }

        public void setVaddress(String vaddress) {
            this.vaddress = vaddress;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        @Override
        public String toString() {
            return vname;
        }
    }

    public static class PurchaseOrderLinePrint implements SerializableRead{
        private String prdt;
        private int qty;
        private double rate;
        private double total;
        private int slNo;

        public void readValues(DataRead dr) throws BasicException {
            prdt = dr.getString(1);
            qty = dr.getInt(2);
            rate = dr.getDouble(3);
        }

        public String getPrdt() {
            return prdt;
        }

        public void setPrdt(String prdt) {
            this.prdt = prdt;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public int getSlNo() {
            return slNo;
        }

        public void setSlNo(int slNo) {
            this.slNo = slNo;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        

    }

    
}


