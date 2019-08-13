/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
//import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
//import org.apache.velocity.runtime.directive.Foreach;

/**
 *
 * @author swathi
 */
public class CollectionDetailTableModel {

     private final static String[] Header={"Type","Number","Amount"};
    private final static String[] CHeader={"Date","Cheque No","Amount","Bank","Customer",""};
    private final static String[] name={"1000","500","100","50","10","5","2","1","Change"};
    private final static int[] nos={0,0,0,0,0,0,0,0,0};
    private final static Double[] amount={0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
    private java.util.List<ChequeDetailLine> cdetail;
    private Double ctotal;

     public static CollectionDetailTableModel  emptyinstance(){
       CollectionDetailTableModel  mc=new CollectionDetailTableModel();
       mc.cdetail=new ArrayList<ChequeDetailLine>();
       mc.ctotal=0.0;
       return mc;
     }
     public static CollectionDetailTableModel loadInstance(AppView app) throws BasicException{
         CollectionDetailTableModel  mc=new CollectionDetailTableModel();
         String userid=app.getAppUserView().getUser().getId();
          List<ChequeDetailLine> chdetail = new StaticSentence(app.getSession(),
                " SELECT C.CHEQUENO,C.BANK,PAYMENTS.TOTAL,CUSTOMERS.NAME,PAYMENTS.PTIME,C.ID FROM PAYMENTS,CHEQUE C,CUSTOMERS "+
                " WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER AND PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque' AND C.HOLDER=? "
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(CollectionDetailTableModel.ChequeDetailLine.class))
                .list(userid);
          if(chdetail==null){
            mc.cdetail=new ArrayList<ChequeDetailLine>();
          }else{
              mc.cdetail=chdetail;
       
          }
         return mc;
     }
     public java.util.List<ChequeDetailLine> getchequelist(){
         return cdetail;
     }
     public Double getchequetotal(){
       return ctotal;
     }
     public String[] getnames(){
      return name;
     }
     public int[] getnos(){
      return nos;
     }
     

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(Header[column]);
            }
            public int getRowCount() {
                return 9;
            }
            public int getColumnCount() {
                return Header.length;
            }

            public Object getValueAt(int row, int column) {
                switch (column) {

                case 0: return name[row];
                case 1: return nos[row];
                case 2: return amount[row];

                default: return null;
                }
            }
        };
    }
     public AbstractTableModel getchequeTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(CHeader[column]);
            }
            public int getRowCount() {
                return cdetail.size();
            }
            public int getColumnCount() {
                return CHeader.length;
            }
             boolean[] canEdit = new boolean [] {
                false, false,false,false,false,true
            };


            public Object getValueAt(int row, int column) {
                ChequeDetailLine cd=cdetail.get(row);
                switch (column) {

                case 0: return cd.getdate();
                case 1: return cd.getchid();
                case 2: return cd.getAmount();
                case 3: return cd.getBank();
                case 4: return cd.getcustomer();
                case 5: return cd.getselect();
                case 6: return cd.gettotal();
                 case 7:return cd.getid();
                default: return null;
                }
            }
        };
    }

     public static class ChequeDetailLine implements SerializableRead {

        private String chid;
        private String bank;
        private Double amount;
        private String customer;
        private Timestamp date;
        private Boolean select;
        private Double total=0.0;
        private String id;
       // private Boolean transferred;


        public void readValues(DataRead dr) throws BasicException {
            chid = dr.getString(1);
            bank = dr.getString(2);
            amount=dr.getDouble(3);
            customer=dr.getString(4);
            date=dr.getTimestamp(5);
            id=dr.getString(6);
            total += amount;
            select=true;
           // transferred=false;
        }
       
        public Double gettotal(){
           return total;
        }

        public Boolean getselect(){
         return select;
        }
        public void setSelect(boolean sel){
            select=sel;
        }

        public String getid(){
         return id;
        }
        public String getchid() {
            return  chid;
        }
        public String getBank() {
            return bank;
        }
        public String printAmount() {
            return Formats.CURRENCY.formatValue(amount);
        }
        public Double getAmount() {
            return amount;
        }
        public String getcustomer(){
        return customer;
        }
        public Timestamp getdate(){
          return date;
        }
    }

}
