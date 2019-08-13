/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class consprodTable {

    

    private List<consProdline > pdtcon;
    private final static String[] PDTCONHEADERS = {"Department", "AccountType","Active"};

    public static consprodTable emptyInstance() {
        consprodTable p = new consprodTable();
        p.pdtcon = new ArrayList<consProdline >();
        return p;

    }
public static consprodTable loadInstance(Session session, String prodid) throws BasicException {
     consprodTable p = new consprodTable();

        List<consProdline> pdtconlist = new StaticSentence(session, " SELECT D.NAME,A.NAME,C.Active" +
                " FROM DEPARTMENT D,ACCOUNTMASTER A,CONSPRDACC C,PRODUCTS P WHERE A.ID=C.ACCID AND D.ID=C.DEPTID AND C.PROID=? and c.proid=p.id  and C.active=true", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(consProdline .class)).list(new Object[]{prodid});

        if (pdtconlist == null) {
            p.pdtcon = new ArrayList();
        } else {
            p.pdtcon = pdtconlist;
        }

        return p;
    }

    public void add(int i, Object object) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    

    //public AbstractTableModel getPdtConversionModel() {
    public AbstractTableModel getconsprodModel() {
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
                consProdline  l = pdtcon.get(row);
                switch (column) {
                    case 0:
                        return l.getDept();
                    case 1:
                        return l.getAccType();
                     case 2:
                        return l.isActive();
                    default:
                        return null;
                }
            }
        };
    }

    public static class consProdline implements SerializableRead {


        //private String proID;
        private String dept;
        private String accType;
        private boolean active;

        public void readValues(DataRead dr) throws BasicException {
           // proID = dr.getString(1);
            dept = dr.getString(1);
            accType = dr.getString(2);
             active = dr.getBoolean(3);
        }

        public String getAccType() {
            return accType;
        }

        public String getDept() {
            return dept;
        }

        public boolean isActive() {
            return active;
        }

       

    }
}
