/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteInteger;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.AdditionalEntry;
import com.openbravo.pos.Accounts.PurchaseVoucherline;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class PurchaseJournalTable {

    private static String[] HEADERS = {"Item", "Qty", "Rate", "Amount", "Taxcat", "Tax", "Total tax","taxrate","account","itemid","taxid","Purchaseorderref","Flag", "Taxcat2", "Tax2","taxrate2","taxid2", "Taxcat3", "Tax3","taxrate3","taxid3","B2","B3", "Total Amount"};
   // private static String[] HEADERS = {"Item", "Quantity", "Rate", "Amount", "Taxcat", "Tax", "Total Amount", "Taxcat2", "Tax2", "Taxcat3", "Tax3",};
  
    private static String[] HEADERS1 = {"Item", "Amount"};
    private List<PurchaseVoucherline> pvoucher = new ArrayList<PurchaseVoucherline>();
    private List<AdditionalEntry> addtional = new ArrayList<AdditionalEntry>();
    private List<PurchaseOrderedLine> pdline = new ArrayList<PurchaseOrderedLine>();
    private List<purchase> pdline1 = new ArrayList<purchase>();

    public List<PurchaseOrderedLine> getPdline() {
        return pdline;
    }

//    public List<purchase> getPdline1() {
//        return pdline1;
//    }

    public static PurchaseJournalTable loadInstance() {
        PurchaseJournalTable p = new PurchaseJournalTable();
        p.pvoucher = new ArrayList<PurchaseVoucherline>();
        p.addtional = new ArrayList<AdditionalEntry>();
        p.pdline = new ArrayList<PurchaseOrderedLine>();
        p.pdline1 = new ArrayList<purchase>();
        return p;
    }

    public  List<PurchaseOrderedLine> loadPurchaseOrderedLine(AppView app, int purchaseorderno) throws BasicException {
        PurchaseJournalTable p = new PurchaseJournalTable();
        List<PurchaseOrderedLine> pline = new StaticSentence(app.getSession(), "SELECT PD.PRODUCTID,PD.BALANCEQTY,PD.RATE,PO.ID,PO.PURCHASEORDERNO FROM PURCHASEORDER PO,PURCHASEORDERDETAIL PD WHERE PD.PURCHASEORDERID=PO.ID AND PO.PURCHASEORDERNO=? AND PD.BALANCEQTY>0", SerializerWriteInteger.INSTANCE, new SerializerReadClass(PurchaseOrderedLine.class)).list(purchaseorderno);
        if (pline == null) {
            pline = new ArrayList<PurchaseOrderedLine>();
        }
        return pline;
    }

    public List<purchase> getPurchaseOrderedLine(AppView app, String pid, String vendor, String ponor) throws BasicException {
        PurchaseJournalTable p = new PurchaseJournalTable();
        List<purchase> pline;
        if (ponor.length()<=0 || ponor == null) {
            pline = new StaticSentence(app.getSession(), "SELECT PO.ID,PO.PURCHASEORDERNO,PD.PRODUCTID,PD.BALANCEQTY,PD.RATE FROM PURCHASEORDER PO,PURCHASEORDERDETAIL PD,VENDOR V WHERE PD.PURCHASEORDERID=PO.ID AND PD.BALANCEQTY>0 AND PD.PRODUCTID=? AND PO.VENDOR=V.ID AND V.NAME=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(purchase.class)).list(new Object[]{pid, vendor});
        } else {
            String[] str = ponor.split("#");
            Object[] params = new Object[str.length+2];
            Datas[] data = new Datas[str.length+2];
            data[0] = Datas.STRING;
            data[1] = Datas.STRING;
            params[0] = pid;
            params[1] = vendor;
            StringBuffer condition = new StringBuffer("");
            for (int j = 2; j < str.length+2; j++) {
                data[j] = Datas.INT;
                params[j] = Integer.parseInt(str[j-2].toString());
                condition.append(" ? , ");
            }
            if (condition.length() > 0) {
                condition.deleteCharAt(condition.lastIndexOf(","));
            }
            pline = new StaticSentence(app.getSession(), "SELECT PO.ID,PO.PURCHASEORDERNO,PD.PRODUCTID,PD.BALANCEQTY,PD.RATE FROM PURCHASEORDER PO,PURCHASEORDERDETAIL PD,VENDOR V WHERE PD.PURCHASEORDERID=PO.ID AND PD.BALANCEQTY>0 AND PD.PRODUCTID=? AND PO.VENDOR=V.ID AND V.NAME=? AND PO.PURCHASEORDERNO not in (" + condition.toString() + ")", new SerializerWriteBasic(data), new SerializerReadClass(purchase.class)).list(params);
        } 
        if (pline == null) {
            pline= new ArrayList<purchase>();
        } 
        return pline;
    }

    public List<PurchaseVoucherline> getlist() {
        return pvoucher;
    }

    public void setlist(List<PurchaseVoucherline> list) {
        pvoucher.addAll(list);
    }

    public void setadditionalEntrylist(List<AdditionalEntry> list) {
        addtional.addAll(list);
    }

    public List<AdditionalEntry> getadditionalEntrylist() {
        return addtional;
    }

    public abstract class AbstractTableModelExt extends DefaultTableModel {

        public void setValueAt1(Object aValue, int row, int column) {
        }
    }

    public void addPurchaseLine(PurchaseVoucherline l) {
        if(pvoucher.size()>0)
        pvoucher.add(pvoucher.size()-1,l);
        getTableModel().fireTableDataChanged();
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            , java.lang.Double.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.Boolean.class//pratima
            ,java.lang.String.class,java.lang.Double.class,java.lang.Double.class,java.lang.String.class ,java.lang.String.class,java.lang.Double.class,java.lang.Double.class,java.lang.String.class,java.lang.Boolean.class,java.lang.Boolean.class,java.lang.Double.class
          //  ,java.lang.String.class,java.lang.Double.class,java.lang.String.class,java.lang.Double.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                return HEADERS[column];
            }

            @Override
            public int getRowCount() {
                return pvoucher.size();
            }

            @Override
            public int getColumnCount() {

                return HEADERS.length;
            }
            boolean[] canEdit = new boolean[]{
                true, false, false, true, false, false, false   
                    , false, false, false,false, false,false//pratima
                    , false, false, false,false, false,false, false,false,false,false,false
                    //, false,false,false,false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }


            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (row >= 0) {
                    PurchaseVoucherline l = pvoucher.get(row);
                   
                    switch (column) {

                        case 0:
                            l.setitem(aValue);
                            fireTableDataChanged();
                            break;
                        case 1:
                            l.setQty(aValue);
                            fireTableDataChanged();
                            break;
                        case 2:
                            l.setRate(aValue);
                            fireTableDataChanged();
                            break;
                        case 3:
                            l.setamount(aValue);
                            fireTableDataChanged();
                            break;
                        case 4:
                            l.setTaxcat(aValue);
                            fireTableDataChanged();
                            break;
                        case 5:
                            l.setTax(aValue);
                            fireTableDataChanged();
                            break;
                        case 6:
                            l.setTotalTax(aValue);
                            fireTableDataChanged();
                            break;
                        case 7:
                            l.setpdttaxvalue(aValue);
                            fireTableDataChanged();
                            break;
                        case 8:
                            l.setAccount(String.valueOf(aValue));
                            fireTableDataChanged();
                            break;
                        case 9:
                            l.setitemid(aValue);
                            fireTableDataChanged();
                            break;
                        case 10:
                            l.setTaxcatId(String.valueOf(aValue));
                            fireTableDataChanged();
                            break;
                        case 11:
                            l.setPurchaseorderref(String.valueOf(aValue));
                            fireTableDataChanged();
                            break;
                        case 12:
                            l.setFlag(Boolean.valueOf(aValue.toString()));
                            fireTableDataChanged();
                            break;
                        //////////////////////////////////////////////////by pratima
                        case 13:
                           // if(l.getTaxcat2()!=null){
                            l.setTaxcat2(aValue);
                         
                            fireTableDataChanged();  // }
                            break;
                        case 14:
                             
                            l.setTax2(aValue);
                            
                            fireTableDataChanged();
                            break;
                        case 15:
                            l.setpdttaxvalue2(aValue);
                            fireTableDataChanged();
                            break;
                        case 16:
                          //   if(l.getTaxcatId2()!=null){
                            l.setTaxcatId2(String.valueOf(aValue));
                             
                            fireTableDataChanged();//}
                            break;
                         case 17:
                        //    if(l.getTaxcat3()!=null){
                            l.setTaxcat3(aValue);
                            fireTableDataChanged();//}
                            break;
                        case 18:
                            l.setTax3(aValue);
                            fireTableDataChanged();
                            break;
                        case 19:
                            l.setpdttaxvalue3(aValue);
                            fireTableDataChanged();
                            break;
                        case 20:
                          //  if(l.getTaxcatId3()!=null){
                            l.setTaxcatId3(String.valueOf(aValue));
                            fireTableDataChanged();
                           // }
                            break;
                        case 21:
                            l.setBasic2(Boolean.valueOf(aValue.toString()));
                            fireTableDataChanged();
                            break;
                        case 22:
                            l.setBasic3(Boolean.valueOf(aValue.toString()));
                            fireTableDataChanged();
                            break;
                        case 23:
                            
                             l.setTotalAmount(aValue);
                            fireTableDataChanged();
                            break;
                       
                        ////////////////////////////////////////////////////////////
                        default:
                            break;
                    }
                }
            }

            @Override
            public Object getValueAt(int row, int column) {
                PurchaseVoucherline l = pvoucher.get(row);

                switch (column) {

                    case 0:
                        return l.getitem();
                    case 1:
                        return l.getQty();
                    case 2:
                        return l.getRate();
                    case 3:
                        return l.getamount();
                    case 4:
                        return l.getTaxcat();
                    case 5:
                        return l.getTax();
                    case 6:
                        return l.getTaxTotal();
                    case 7:
                        return l.getpdttaxvalue();
                    case 8:
                        return l.getAccount();
                    case 9:
                        return l.getitemid();
                    case 10:
                        return l.getPurchaseorderref();
                    case 11:
                        return l.getOqty();
                    case 12:
                        return l.isFlag();
                   /////////////////////////////////////pratima
                    case 13:
                        return l.getTaxcat2(); 
                    case 14:
                        return l.getTax2();  
                    case 15:
                        return l.getpdttaxvalue2();  
                    case 16:
                        return l.getTaxcatId2();  
                    case 17:
                        return l.getTaxcat3(); 
                    case 18:
                        return l.getTax3();  
                    case 19:
                        return l.getpdttaxvalue3();  
                    case 20:
                        return l.getTaxcatId3();  
                    case 21:
                        return l.isBasic2();
                    case 22:
                        return l.isBasic3();
                    case 23 :
                        return l.getTotalAmount();
                    
                    /////////////////////////////
                    default:
                        return null;
                }
            }
        };
    }

    public AbstractTableModel getTableModelforAdditionalEntries() {
        return new AbstractTableModel() {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                return HEADERS1[column];
            }

            @Override
            public int getRowCount() {
                return addtional.size();
            }

            @Override
            public int getColumnCount() {

                return HEADERS1.length;
            }
            boolean[] canEdit = new boolean[]{
                true, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                AdditionalEntry l = addtional.get(row);
                switch (column) {

                    case 0:
                        l.setname(aValue);
                        //  fireTableCellUpdated(row, 0);
                        break;
                    case 1:
                        l.setamount(aValue);
                        // fireTableCellUpdated(row, 1);
                        break;
                    case 2:
                        l.setAccount(aValue);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public Object getValueAt(int row, int column) {
                AdditionalEntry l = addtional.get(row);
                switch (column) {
                    // case 0: return l.getAccount();
                    case 0:
                        return l.getname();
                    case 1:
                        return l.getamount();
                    case 2:
                        return l.getAccount();
                    default:
                        return null;
                }
            }
        };
    }

    /*public static class AdditionalEntry implements SerializableRead{
    private String name;
    private double amount=0.0;
    private String account;
    private String parentskey;
    private String transtype;
    private String narration;
    /*   public AdditionalEntry(){
    account=new javax.swing.JComboBox();
    amount=0.0;
    }//
    public void readValues(DataRead dr) throws BasicException {
    name=dr.getString(1);
    amount=dr.getDouble(2);
    account=dr.getString(3);
    parentskey=dr.getString(4);
    transtype=dr.getString(5);
    narration=dr.getString(6);
    }
    public String getNarration(){
    return narration;
    }
    public String getTransType(){
    return transtype;
    }
    public String getParentSearchKey(){
    return parentskey;
    }
    public String getname(){
    return name;
    }
    public void setname(Object name){
    this.name=name.toString();
    }
    public double getamount(){
    return amount;
    }
    public void setamount(Object amt){
    amount=Double.parseDouble(amt.toString());
    }
    public String getAccount(){
    return account;
    }
    public void setAccount(Object acc){
    account=acc.toString();
    }

    }*/
    /*public static class PurchaseVoucherline implements SerializableRead{
    private String item;
    private int qty;
    private double rate=0;
    private double amount=0;
    private String taxcat;
    private double tax=0;
    private double totalAmount=0;
    private double pdttaxvalue=0;
    private String account;
    private String itemid;


    public void readValues(DataRead dr) throws BasicException
    {
    /* item=dr.getString(1);
    qty=dr.getInt(2);
    rate=dr.getDouble(3);
    amount=dr.getDouble(4);
    taxcat=dr.getString(5);
    tax=dr.getDouble(6);
    totalAmount=dr.getDouble(7);
    pdttaxvalue=dr.getDouble(8);
    account=dr.getString(9);
    itemid=dr.getString(10);//
    item=dr.getString(1);
    qty=dr.getInt(2);
    rate=dr.getDouble(3);
    amount=dr.getDouble(4);
    taxcat=null;
    tax=dr.getDouble(5);
    totalAmount=amount+tax;
    pdttaxvalue=tax/qty;
    account=dr.getString(6);
    itemid=dr.getString(7);
    }
    public String getAccount(){
    return account;
    }
    public void setAccount(String acc){
    account=acc;
    }
    public Double getpdttaxvalue(){
    return pdttaxvalue;
    }
    public void setpdttaxvalue(Object tax){
    pdttaxvalue=Double.valueOf(tax.toString());
    }
    public String getitem(){
    return item;
    }
    public String getitemid(){
    return itemid;
    }

    public int getQty(){
    return qty;
    }

    public void setitem( Object name){
    item=name.toString();
    }
    public void setitemid( Object id){
    itemid=id.toString();
    }
    public void setQty( Object Qty){
    qty=Integer.valueOf(Qty.toString());
    }

    public double getRate(){
    return rate;
    }
    public void setRate(Object Rate){
    rate=Double.valueOf(Rate.toString());
    }

    public double getamount(){
    return amount;
    }
    public void setamount(Object amt){
    amount=Double.valueOf(amt.toString());
    }
    public String getTaxcat(){
    return taxcat;
    }
    public void setTaxcat(Object taxcatname){
    taxcat=taxcatname.toString();
    }
    public double getTax(){
    return tax;
    }
    public void setTax(Object taxamt){
    tax=Double.valueOf(taxamt.toString());;
    }
    public double getTotalAmount(){
    return totalAmount;
    }
    public void setTotalAmount(Object amt){
    totalAmount=Double.valueOf(amt.toString());
    }
    }*/
    public static class PurchaseOrderedLine implements SerializableRead {

        private String product;
        private int qty;
        private double rate;
        private int pqty;
        private String poid;
        private int ponor;

        public void readValues(DataRead dr) throws BasicException {
            product = dr.getString(1);
            qty = dr.getInt(2);
            rate = dr.getDouble(3);
            poid = dr.getString(4);
            ponor = dr.getInt(5);
            pqty = qty;
        }

        public int getPonor() {
            return ponor;
        }

        public void setPonor(int ponor) {
            this.ponor = ponor;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getPoid() {
            return poid;
        }

        public void setPoid(String poid) {
            this.poid = poid;
        }

        public int getPqty() {
            return pqty;
        }

        public void setPqty(int pqty) {
            this.pqty = pqty;
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
    }

    public static class purchase implements SerializableRead {

        private String id;
        private int pono;
        private String product;
        private int qty;
        private double rate;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            pono = dr.getInt(2);
            product = dr.getString(3);
            if (dr.getInt(4) == null) {
                qty = 0;
            } else {
                qty = dr.getInt(4);
            }
            if (dr.getInt(5) == null) {
                rate = 0.0;
            } else {
                rate = dr.getDouble(5);
            }
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getPono() {
            return pono;
        }

        public void setPono(int pono) {
            this.pono = pono;
        }

        @Override
        public String toString() {
            return String.valueOf(pono);
        }
    }
}
