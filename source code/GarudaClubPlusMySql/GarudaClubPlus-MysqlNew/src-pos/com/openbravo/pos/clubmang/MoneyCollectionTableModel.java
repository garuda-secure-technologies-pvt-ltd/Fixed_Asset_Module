/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MoneyCollectionTableModel {
    private final static String[] Header={"Type","Number","Amount"};
    private final static String[] CHeader={"Date","Cheque No","Amount","Bank","Customer","Transfered",""};
    private final static String[] CHeader1={"Date","Cheque No","Amount","Bank","Customer"};
    private final static String[] name={"1000","500","100","50","20","10","5","2","1","Change"};
    private final static float[] nos={0,0,0,0,0,0,0,0,0,0};
    private  final static Double[] amount={0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
    private java.util.List<ChequeDetailLine> cdetail;
    private java.util.List<ChequeDetailLine> cdetail1;
    private Double ctotal;

     public static MoneyCollectionTableModel emptyinstance(){
       MoneyCollectionTableModel mc=new MoneyCollectionTableModel();
       mc.cdetail=new ArrayList<ChequeDetailLine>();
       mc.ctotal=0.0;
       return mc;
     }
     public void setemptyInstance(){
        for(int i=0;i<10;i++){
          nos[i]=0;
          amount[i]=0.00;
        }
     }
     public static MoneyCollectionTableModel loadInstance(AppView app) throws BasicException{

         MoneyCollectionTableModel mc=new MoneyCollectionTableModel();
       //  mc.ctotal=0.0;
         String userid=app.getAppUserView().getUser().getId();
         Object[] obj=(Object[])new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS.TOTAL) FROM PAYMENTS,CHEQUE C "+
                "WHERE  PAYMENTS.RECEIPT=C.RNO AND PAYMENTS.PAYMENT ='cheque' AND C.HOLDER=? "

                , SerializerWriteString.INSTANCE
                , new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(userid);
          if(obj == null || obj[0] == null)
              mc.ctotal=0.0;
          else
              mc.ctotal=Double.parseDouble(obj[0].toString());
         Object[] obj1=(Object[])new StaticSentence(app.getSession(),
                " SELECT SUM(PAYMENTS_ARV.TOTAL) FROM PAYMENTS_ARV,CHEQUE C "+
                "WHERE  PAYMENTS_ARV.RECEIPT=C.RNO AND PAYMENTS_ARV.PAYMENT ='cheque' AND C.HOLDER=? "

                , SerializerWriteString.INSTANCE
                , new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(userid);
          if(obj1 != null && obj1[0] != null)
              mc.ctotal += Double.parseDouble(obj1[0].toString());
         //CUSTOMERS.NAME
         //WHERE CUSTOMERS.ID=PAYMENTS.CUSTOMER
         //,CUSTOMERS //ORDER BY PAYMENTS.PTIME
          List<ChequeDetailLine> chdetail = new StaticSentence(app.getSession(),
                "SELECT CNO,CBANK,TOTAL,SEARCHKEY,PTIME,ID FROM (SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,PAYMENTS.TOTAL AS TOTAL,CUSTOMERS.SEARCHKEY AS SEARCHKEY,PAYMENTS.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS ON  PAYMENTS.RECEIPT=C.RNO AND C.HOLDER=? JOIN CUSTOMERS  ON CUSTOMERS.ID=PAYMENTS.CUSTOMER   "+
                "   WHERE  PAYMENTS.PAYMENT ='cheque'   "+
                "UNION ALL SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,PAYMENTS.TOTAL AS TOTAL,PAYMENTS.CUSTOMER AS SEARCHKEY,PAYMENTS.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS "+
                " ON   PAYMENTS.RECEIPT=C.RNO AND C.HOLDER=? WHERE PAYMENTS.PAYMENT ='cheque' AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) "
                +" UNION ALL SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,PAYMENTS_ARV.TOTAL AS TOTAL,CUSTOMERS.SEARCHKEY AS SEARCHKEY,PAYMENTS_ARV.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS_ARV ON  PAYMENTS_ARV.RECEIPT=C.RNO AND C.HOLDER=? JOIN CUSTOMERS  ON CUSTOMERS.ID=PAYMENTS_ARV.CUSTOMER   "+
                "   WHERE  PAYMENTS_ARV.PAYMENT ='cheque'   "+
                "UNION ALL SELECT C.CHEQUENO AS CNO,C.BANK AS CBANK,PAYMENTS_ARV.TOTAL AS TOTAL,PAYMENTS_ARV.CUSTOMER AS SEARCHKEY,PAYMENTS_ARV.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS_ARV "+
                " ON   PAYMENTS_ARV.RECEIPT=C.RNO AND C.HOLDER=? WHERE PAYMENTS_ARV.PAYMENT ='cheque'  AND PAYMENTS_ARV.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) "
                +" ) as chdetails ORDER BY 5"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})
                , new SerializerReadClass(MoneyCollectionTableModel.ChequeDetailLine.class))
                .list(new Object[]{userid,userid,userid,userid});
          if(chdetail==null){
            mc.cdetail=new ArrayList<ChequeDetailLine>();
          }else{
              mc.cdetail=chdetail;
               List<String> chequesAlreadyTransfered=new StaticSentence(app.getSession()
                , "SELECT CHEQUEDETAIL FROM COLLECTIONTRANSFER WHERE RECEIVED=FALSE AND SENDER= ?"
                ,SerializerWriteString.INSTANCE
                ,SerializerReadString.INSTANCE).list(app.getAppUserView().getUser().getId());
               if(chequesAlreadyTransfered.size()>0){
                   List chequelist=new ArrayList();
                   for(int i=0;i<chequesAlreadyTransfered.size();i++){
                       if(chequesAlreadyTransfered.get(i)!=null){
                      String[] ch=chequesAlreadyTransfered.get(i).split("#");
                      for(int j=0;j<ch.length;j++)
                      chequelist.add(ch[j]);
                       }
                   }
                 for(int k=0;k<chdetail.size();k++){
                 MoneyCollectionTableModel.ChequeDetailLine c=chdetail.get(k);
                if(chequelist.contains(c.getid())){
                 mc.cdetail.get(k).setTransfered(true);
                mc.cdetail.get(k).setSelect(false);
                mc.ctotal=mc.ctotal-mc.cdetail.get(k).getAmount();
               }
               }
               }
          }
          
         return mc;
     }

      public static MoneyCollectionTableModel loadDetail(AppView app,String[] chequeid) throws BasicException{
         MoneyCollectionTableModel mc=new MoneyCollectionTableModel();
          List<ChequeDetailLine> cdlist=new ArrayList<ChequeDetailLine>();
         for(int i=0;i<chequeid.length;i++){
           ChequeDetailLine cd=(ChequeDetailLine)new StaticSentence(app.getSession()
                                  , "SELECT CNO,BANK,AMOUNT,CNAME,PTIME,ID from (SELECT C.CHEQUENO AS CNO,C.BANK AS BANK,C.AMOUNT AS AMOUNT,CUSTOMERS.NAME AS CNAME,PAYMENTS.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS ON  PAYMENTS.RECEIPT=C.RNO AND C.ID=? JOIN CUSTOMERS  ON CUSTOMERS.ID=PAYMENTS.CUSTOMER   "+
                                     "   WHERE  PAYMENTS.PAYMENT ='cheque'   "
                                    +" UNION ALL SELECT C.CHEQUENO AS CNO,C.BANK AS BANK,C.AMOUNT AS AMOUNT,CUSTOMERS.NAME AS CNAME,PAYMENTS_ARV.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS_ARV ON  PAYMENTS_ARV.RECEIPT=C.RNO AND C.ID=? JOIN CUSTOMERS  ON CUSTOMERS.ID=PAYMENTS_ARV.CUSTOMER   "+
                                      "   WHERE  PAYMENTS_ARV.PAYMENT ='cheque'   "
                                    +" UNION ALL SELECT C.CHEQUENO AS CNO,C.BANK AS BANK,C.AMOUNT AS AMOUNT,PAYMENTS.CUSTOMER AS CNAME,PAYMENTS.PTIME AS PTIME,C.ID AS ID  FROM CHEQUE C JOIN PAYMENTS "+
                                     " ON   PAYMENTS.RECEIPT=C.RNO AND C.ID=? WHERE PAYMENTS.PAYMENT ='cheque'  AND PAYMENTS.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS) "
                                    +" UNION ALL SELECT C.CHEQUENO AS CNO,C.BANK AS BANK,C.AMOUNT AS AMOUNT,PAYMENTS_ARV.CUSTOMER AS CNAME,PAYMENTS_ARV.PTIME AS PTIME,C.ID AS ID FROM CHEQUE C JOIN PAYMENTS_ARV "+
                                      " ON   PAYMENTS_ARV.RECEIPT=C.RNO AND C.ID=? WHERE PAYMENTS_ARV.PAYMENT ='cheque'  AND PAYMENTS_ARV.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS)) as detail "
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})
                                  ,new SerializerReadClass(MoneyCollectionTableModel.ChequeDetailLine.class)).find(new Object[]{chequeid[i],chequeid[i],chequeid[i],chequeid[i]});
           if(cd!=null)
           cdlist.add(cd);
         }
          mc.cdetail1=cdlist;
         return mc;
      }

     public java.util.List<ChequeDetailLine> getchequelist(){
         return cdetail;
     }
     public java.util.List<ChequeDetailLine> getchequelist1(){
         return cdetail1;
     }
     public Double getchequetotal(){
       return Formats.ApproxTo2Decimals(ctotal);
     }
     public String[] getnames(){
      return name;
     }
     public float[] getnos(){
      return nos;
     }
   public Double[] getAmount(){
      return amount;
     }
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(Header[column]);
                return (Header[column]);
            }
            public int getRowCount() {
                return 10;
            }
            public int getColumnCount() {
                return Header.length;
            }
             boolean[] canEdit = new boolean [] {
                false, false,false
            };


           @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            @Override
           public void setValueAt(Object aValue, int row, int column) {
              // Facilityline l = fac.get(row);
               switch(column){
                   case 1 : nos[row]=Float.parseFloat(aValue.toString());
                            break;
                   case 2 : amount[row]=Double.parseDouble(aValue.toString());
                            break;
               }
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
                //return (Header[column]);
            }
            public int getRowCount() {
                return cdetail.size();
            }
            public int getColumnCount() {
                return CHeader.length;
            }
             boolean[] canEdit = new boolean [] {
                false, false,false,false,false,false,true
            };
             Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

           @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            @Override
           public void setValueAt(Object aValue, int row, int column) {
             ChequeDetailLine cd=cdetail.get(row);
            // CollectionTransfer ct=new CollectionTransfer();
           //  ct=ct.getcollectionTransfer();

               if(column==6){
                 boolean flag=Boolean.parseBoolean(aValue.toString());
                 boolean prevstatus=cd.getselect();
                 if(flag!=prevstatus && cd.getTransfered()==false){
                   cd.setSelect(flag);
                   if(flag==true){
                    ctotal += cd.getAmount();
                   }else if(flag==false){
                      ctotal -= cd.getAmount();
                   }
                   CollectionTransfer.chequetotal.setText(Formats.ConvertDoubleToString(ctotal));
                   fireTableDataChanged();
                 }
               }
            }

            public Object getValueAt(int row, int column) {
                ChequeDetailLine cd=cdetail.get(row);
                switch (column) {

                case 0: return cd.getdate();
                case 1: return cd.getchid();
                case 2: return cd.getAmount();
                case 3: return cd.getBank();
                case 4: return cd.getcustomer();
                case 6: return cd.getselect();
                case 5:return cd.getTransfered();
                case 7: return cd.gettotal();
                 case 8:return cd.getid();
                 case 9:return cd.getTransfered();
                default: return null;
                }
            }
        };
    }
      public AbstractTableModel getchequeTableModel1() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(CHeader1[column]);
            }
            public int getRowCount() {
                return cdetail1.size();
            }
            public int getColumnCount() {
                return CHeader1.length;
            }

            public Object getValueAt(int row, int column) {
                ChequeDetailLine cd=cdetail1.get(row);
                if(cd!=null){
                switch (column) {

                case 0: return cd.getdate();
                case 1: return cd.getchid();
                case 2: return cd.getAmount();
                case 3: return cd.getBank();
                case 4: return cd.getcustomer();
                case 5: return cd.gettotal();
                case 6:return cd.getid();
                default: return null;
                }
                }else
                return null;
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
        private Boolean transferred;
        public void readValues(DataRead dr) throws BasicException {
            chid = dr.getString(1);
            bank = dr.getString(2);
            amount=dr.getDouble(3);
            customer=dr.getString(4);
            date=dr.getTimestamp(5);
            id=dr.getString(6);
            if(amount==null)
                total+=0;
            else
            total += amount;
            select=true;
            transferred=false;
        }
        public Double gettotal(){
           return total;
        }
         public Boolean getTransfered(){
          return transferred;
        }
        public void setTransfered(Boolean flag){
          transferred=flag;
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
