/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class KioskGuestEntryReportTableModel extends BeanFactoryDataSingle{

    private Session s;
     private List<KioskBean> guestlist = new ArrayList<KioskBean>();
    private final static String[] TABLEHEADERS = {"Date","Mem No.","Mem Name","Guest Cat","Num","Amount","Guest name","Receipt No"};
    @Override
    public void init(Session s) {
        this.s = s;
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public static KioskGuestEntryReportTableModel getReport(Date fDate, Date tDate, AppView app)
    { KioskGuestEntryReportTableModel k = new KioskGuestEntryReportTableModel();
        
        try {
           
             List dlist = new StaticSentence(app.getSession()
                    ,"SELECT G.DATE,C.NAME,GC.NAME,G.NUM,G.AMOUNT,G.NAMES,G.RECEIPTNO, c.searchkey FROM CUSTOMERS C,GUESTLOG G,GUESTCAT GC, receipts R WHERE G.MEMNO=C.ID AND G.GUESTCAT=GC.ID AND G.DATE >= ? AND G.DATE < ? AND R.ID = G.RECEIPTNO AND R.PAYMENTREF = 'Guest Entry through Member Kiosk' ORDER BY G.DATE,C.NAME"
                  ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                  ,new SerializerReadClass( KioskGuestEntryReportTableModel.KioskBean.class )).list(new Object[]{fDate,tDate});
             if(dlist==null)
             {
                 k.guestlist=new ArrayList<KioskBean>();
             }
             else
            {
                k.guestlist=dlist;
            }



         
        } catch (BasicException ex) {
            Logger.getLogger(KioskGuestEntryReportTableModel.class.getName()).log(Level.SEVERE, null, ex);ex.printStackTrace();}
        
        return k;
        
    }
    
    
    public List<KioskBean> getList()
    {
        return guestlist;
    }
    
    public AbstractTableModel getTableModel(){
    
        return new AbstractTableModel() {

           public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS[column]);
            }
            public int getRowCount() {
                return guestlist.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                KioskBean l = guestlist.get(row);

                switch (column) {

                case 0: return l.getDate();
                case 1: return l.getMemNum();
                case 2: return l.getMemName();
                case 3: return l.getgCat();
                case 4: return l.getNum();
                case 5: return l.gettAmt();
                case 6: 
                    
                    String [] s = l.getgNames().split(":");
                    String names=s[0];
                    for (int i = 1; i < s.length; i++) {
                        names = names+", " +s[i];
                    }
                    
                    
                    return names;
                case 7: return l.getReceiptno();
                


                default: return null;
                }
            }
        };
        
    };
    public static class KioskBean implements SerializableRead, IKeyed
    
{

        private String memName;
        private String memNum;
        private String gCat;
        private Date date;
        private double tAmt;
        private String gNames;
        private int num;
        private String receiptno;
        
        
        
        public void readValues(DataRead dr) throws BasicException {
            
        date=dr.getTimestamp(1);
        memName=dr.getString(2);
        gCat=dr.getString(3);
        num=dr.getInt(4);
        tAmt=dr.getDouble(5);
        gNames=dr.getString(6);
        receiptno=dr.getString(7);
        memNum = dr.getString(8);
            
            //To change body of generated methods, choose Tools | Templates.
        }

        public Object getKey() {
            return this; //To change body of generated methods, choose Tools | Templates.
        }

        public String getMemName() {
            return memName;
        }

        public void setMemName(String memName) {
            this.memName = memName;
        }

        public String getMemNum() {
            return memNum;
        }

        public void setMemNum(String memNum) {
            this.memNum = memNum;
        }

        public String getgCat() {
            return gCat;
        }

        public void setgCat(String gCat) {
            this.gCat = gCat;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double gettAmt() {
            return tAmt;
        }

        public void settAmt(double tAmt) {
            this.tAmt = tAmt;
        }

        public String getgNames() {
            return gNames;
        }

        public void setgNames(String gNames) {
            this.gNames = gNames;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getReceiptno() {
            return receiptno;
        }

        public void setReceiptno(String receiptno) {
            this.receiptno = receiptno;
        }
    
}
}


