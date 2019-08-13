/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class PdtConversionTable {

    private List<PdtConversionLine> pdtcon;
    private final static String[] PDTCONHEADERS = {"Product1", "Qty", "Product2", "Qty", "Created Date", "User", "Active", ""};

    private PdtConversionTable() {
    }

    public static PdtConversionTable emptyInstance() {
        PdtConversionTable p = new PdtConversionTable();
        p.pdtcon = new ArrayList<PdtConversionLine>();
        return p;

    }

    public static PdtConversionTable loadInstance(AppView app, String loc1, String loc2) throws BasicException {
        PdtConversionTable p = new PdtConversionTable();

        List pdtconlist = new StaticSentence(app.getSession(), " SELECT P.NAME,NOFST,P1.NAME,NOSEC,CREATEDDATE,C.USER_,ACTIVE,C.ID " +
                " FROM CONVERTER C,PRODUCTS P,PRODUCTS P1 WHERE ACTIVE=TRUE AND PRODUCTFST=P.ID AND P1.ID=PRODUCTSCN AND C.LOCATIONFST=? AND C.LOCATIONSCN=? ORDER BY P.NAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(PdtConversionTable.PdtConversionLine.class)).list(new Object[]{loc1, loc2});

        if (pdtconlist == null) {
            p.pdtcon = new ArrayList();
        } else {
            p.pdtcon = pdtconlist;
        }

        return p;
    }
    
    public List<PdtConversionLine> getProdList()
    {
        if(this.pdtcon!=null)
        {
            return pdtcon;
        }
        else
        {
            return new ArrayList<PdtConversionLine>();
        }
    }

    public AbstractTableModel getPdtConversionModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(PDTCONHEADERS[column]);
                //sanjeev:commented above line and added below line for resourcebundle problem
                return PDTCONHEADERS[column];
            }

            public int getRowCount() {
                return pdtcon.size();
            }

            public int getColumnCount() {
                return PDTCONHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                PdtConversionLine l = pdtcon.get(row);
                switch (column) {
                    case 0:
                        return l.getpdt1();
                    case 1:
                        return l.getno1();
                    case 2:
                        return l.getpdt2();
                    case 3://
                        // pdtTotalAmount+=l.getAmount();
                        return l.getno2();
                    case 4:
                        return l.getcdate();
                    case 5:
                        return l.getuser();
                    case 6:
                        return l.getactive();
//                    case 7:
//                        return l.getLocationfst();
//                    case 8:
//                        return l.getLocationscn();
                    case 7:
                        return l.getCid();

                    default:
                        return null;
                }
            }
        };
    }

    public static class PdtConversionLine implements SerializableRead {

        private String product1;
        private double no1;
        private String product2;
        private double no2;
        private Timestamp cdate;
        private String user;
        private Boolean active;
        private String locationfst;
        private String locationscn;
        private String cid;

        public String getCid() {
            return cid;
        }

        public void readValues(DataRead dr) throws BasicException {
            product1 = dr.getString(1);
            no1 = dr.getDouble(2);
            product2 = dr.getString(3);
            no2 = dr.getDouble(4);
            cdate = dr.getTimestamp(5);
            user = dr.getString(6);
            active = dr.getBoolean(7);
            //locationfst = dr.getString(8);
            //locationscn = dr.getString(9);
            cid = dr.getString(8);

        }

        public String getpdt1() {
            /* try{
            Object[] obj1=(Object[])   new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
            , "SELECT NAME FROM PRODUCTS WHERE ID = ?"
            ,SerializerWriteString.INSTANCE
            ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(product1);
            return obj1[0].toString();
            }
            catch(Exception e)
            {
            return "";
            }*/
            return product1;
        }

        public String getpdt2() {
            /* try{
            Object[] obj1=(Object[])   new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession()
            , "SELECT NAME FROM PRODUCTS WHERE ID = ?"
            ,SerializerWriteString.INSTANCE
            ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(product2);
            return obj1[0].toString();
            }
            catch(Exception e)
            {
            return "";
            }*/
            return product2;
        }

        public Double getno1() {
            return no1;
        }

        public Double getno2() {
            return no2;
        }

        public Timestamp getcdate() {
            return cdate;
        }

        public String getuser() {
            return user;
        }

        public Boolean getactive() {
            return active;
        }

        public String getLocationfst() {
            return locationfst;
        }

        public String getLocationscn() {
            return locationscn;
        }
    }
}
