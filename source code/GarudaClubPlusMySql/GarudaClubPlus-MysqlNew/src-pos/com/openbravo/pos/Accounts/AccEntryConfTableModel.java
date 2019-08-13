/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.pos.clubmang.*;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.admin.PeopleInfo;
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
public class AccEntryConfTableModel {
     private List<EntryLine> entrylist;
      private List<RejectedLine> rejlist;
      private final static String[] Header={"Date","Debit","Credit","Created By"};
      private final static String[] Header1={"Date","Transref","Reason"};

      public static AccEntryConfTableModel emptyinstance(){
         AccEntryConfTableModel mc=new AccEntryConfTableModel();
         mc.entrylist=new ArrayList<EntryLine>();
         mc.rejlist=new ArrayList<RejectedLine>();
       return mc;
      }
     public static AccEntryConfTableModel loadInstance(AppView app) throws BasicException{
         AccEntryConfTableModel mc=new AccEntryConfTableModel();
         String usercashacc=app.getAppUserView().getUser().getcashaccount();
         String userchequeacc=app.getAppUserView().getUser().getchequeaccount();
         GeneralSettingInfo pinfo=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("Petty Cash Incharge");
         GeneralSettingInfo pcaccinfo=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("Petty Cash Account");
         List<AccEntryConfTableModel.EntryLine> chdetail=new ArrayList<EntryLine>();
         if(pinfo!=null && pinfo.getValue()!=null && pcaccinfo!=null && pcaccinfo.getValue()!=null && app.getAppUserView().getUser().getId().equals(pinfo.getValue())){
             chdetail = new StaticSentence(app.getSession(),
                " SELECT  A1.TID,A1.DATE,A1.TRANSREF,(SELECT SUM(A.AMOUNT) FROM ACCOUNTJOURNALDUP A WHERE A.TRANSTYPE='D' AND A.TID= A1.TID AND A.ACCOUNTID=A1.ACCOUNTID GROUP BY A.TID)" +
                " ,(SELECT SUM(A.AMOUNT) FROM ACCOUNTJOURNALDUP A WHERE A.TRANSTYPE='C' AND A.TID= A1.TID AND A.ACCOUNTID=A1.ACCOUNTID GROUP BY A.TID),A1.CREATEDBY,A1.PAYMENTREF FROM ACCOUNTJOURNALDUP A1 , ACCOUNTEDITDETAIL A WHERE A.TID=A1.TID AND A.CONFIRMEDBY IS NULL AND A1.ACTIVE=TRUE AND (A1.ACCOUNTID=? OR A1.ACCOUNTID=? OR A1.ACCOUNTID=?) AND A.CONFIRMER=A1.ACCOUNTID ORDER BY A1.DATE "
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})
                , new SerializerReadClass(AccEntryConfTableModel.EntryLine.class))
                .list(new Object[]{usercashacc,userchequeacc,pcaccinfo.getValue()});
         }else{
          chdetail = new StaticSentence(app.getSession(),
                " SELECT  A1.TID,A1.DATE,A1.TRANSREF,(SELECT SUM(A.AMOUNT) FROM ACCOUNTJOURNALDUP A WHERE A.TRANSTYPE='D' AND A.TID= A1.TID AND A.ACCOUNTID=A1.ACCOUNTID GROUP BY A.TID)" +
                " ,(SELECT SUM(A.AMOUNT) FROM ACCOUNTJOURNALDUP A WHERE A.TRANSTYPE='C' AND A.TID= A1.TID AND A.ACCOUNTID=A1.ACCOUNTID GROUP BY A.TID),A1.CREATEDBY,A1.PAYMENTREF FROM ACCOUNTJOURNALDUP A1,ACCOUNTEDITDETAIL A WHERE A.TID=A1.TID AND A.CONFIRMEDBY IS NULL AND A1.ACTIVE=TRUE AND (A1.ACCOUNTID=? OR A1.ACCOUNTID=?) AND A.CONFIRMER=A1.ACCOUNTID ORDER BY A1.DATE"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
               , new SerializerReadClass(AccEntryConfTableModel.EntryLine.class))
                .list(new Object[]{usercashacc,userchequeacc});
         }
         if(chdetail==null){
              mc.entrylist=new ArrayList<AccEntryConfTableModel.EntryLine>();
         }else
              mc.entrylist=chdetail;//praveen_altered below query---just swapped accountournaldup a1 and accounteditdetail a
         List<AccEntryConfTableModel.RejectedLine> rejectedList = new StaticSentence(app.getSession(),
                " SELECT A1.TID,A1.TRANSREF,A1.DATE, A2.MESSAGE,A.ID FROM  ACCOUNTEDITDETAIL A, ACCOUNTJOURNALDUP A1 join ACCOUNTEDITDETAIL A2 ON A2.TID=A1.TID AND A2.MESSAGE IS NOT NULL  WHERE A.TID=A1.TID AND A1.ACTIVE=FALSE AND A.CONFIRMER=A1.ACCOUNTID AND A.FLAG IS NULL AND (A1.ACCOUNTID=? OR A1.ACCOUNTID=?) " +
                "UNION SELECT A1.TID,A1.TRANSREF,A1.DATE,A2.MESSAGE  ,A.ID FROM ACCOUNTEDITDETAIL A,ACCOUNTJOURNALDUP A1 join ACCOUNTEDITDETAIL A2 ON A2.TID=A1.TID AND A2.MESSAGE IS NOT NULL  WHERE A.TID=A1.TID AND A1.ACTIVE=FALSE AND A.CONFIRMER=? AND A.FLAG IS NULL "
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})
                , new SerializerReadClass(AccEntryConfTableModel.RejectedLine.class))
                .list(new Object[]{usercashacc,userchequeacc,app.getAppUserView().getUser().getId()});
         if(rejectedList==null){
              mc.rejlist=new ArrayList<AccEntryConfTableModel.RejectedLine>();
         }else
              mc.rejlist=rejectedList;
         return mc;
     }
       public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
               // return AppLocal.getIntString(Header[column]);
                //sanjeev: commented above line and added below line
                return Header[column];
            }
            public int getRowCount() {
                return entrylist.size();
            }
            public int getColumnCount() {
                return Header.length;
            }


            public Object getValueAt(int row, int column) {
                AccEntryConfTableModel.EntryLine cd=entrylist.get(row);
                switch (column) {
                case 0: return cd.getdate();
                case 1: return cd.getDebit();
                case 2: return cd.getCredit();
                case 3: return cd.getcreatedby();
                case 4: return cd.getid();
                case 5: return cd.getTransref();
                case 6: return cd.getOldTransId();
                default: return null;
                }
            }
        };
    }

     public static class EntryLine implements SerializableRead {


        private Timestamp date;
        private Double debit;
        private Double credit;
        private String createdby;
        private String transref;
        private String tid;
        private String paymentref;//contains old transid i.e transaction id of the edited transaction

        public void readValues(DataRead dr) throws BasicException {
            tid=dr.getString(1);
            date=dr.getTimestamp(2);
            transref=dr.getString(3);
            debit=dr.getDouble(4);
            credit=dr.getDouble(5);
            createdby=dr.getString(6);
            paymentref=dr.getString(7);
        }
        public String getOldTransId(){
          return paymentref;
        }
        public String getTransref(){
         return transref;
        }
        public Double getCredit(){
           return credit;
        }
        public Double getDebit(){
           return debit;
        }
        public String getid(){
         return tid;
        }
        public String getcreatedby(){
        return createdby;
        }
        public Timestamp getdate(){
          return date;
        }
    }
      public AbstractTableModel getRejTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(Header1[column]);
                //sanjeev: commented above line and added below line
                return Header1[column];
            }
            public int getRowCount() {
                return rejlist.size();
            }
            public int getColumnCount() {
                return Header1.length;
            }


            public Object getValueAt(int row, int column) {
                AccEntryConfTableModel.RejectedLine cd=rejlist.get(row);
                switch (column) {
                case 0: return cd.getdate();
                case 1: return cd.getTransref();
                case 2: return cd.getmessage();
                case 3: return cd.getTid();
                case 4: return cd.getid();
                default: return null;
                }
            }
        };
    }
      public static class RejectedLine implements SerializableRead {


        private Timestamp date;
        private String tid;
        private String message;
        private String transref;
        private String id;
        public void readValues(DataRead dr) throws BasicException {
            tid=dr.getString(1);
            transref=dr.getString(2);
            date=dr.getTimestamp(3);
            message=dr.getString(4);
            id=dr.getString(5);
        }
        public String getid(){
         return id;
        }
        public String getTransref(){
         return transref;
        }
        public String getTid(){
         return tid;
        }
        public String getmessage(){
        return message;
        }
        public Timestamp getdate(){
          return date;
        }
    }

}
