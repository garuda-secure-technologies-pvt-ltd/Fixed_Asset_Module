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
import com.openbravo.data.loader.StaticSentence;
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
public class CardsRoomTableModel {

     private List<JackpotLine > jpPlayinglist;
      private List<StakeLine> rejlist;
      private final static String[] Header={"Sheet Num","Start time"};
      private final static String[] Header1={"Date","Transref","Reason"};

      public static CardsRoomTableModel emptyinstance(){
         CardsRoomTableModel mc=new CardsRoomTableModel();
         mc.jpPlayinglist=new ArrayList<JackpotLine >();
         mc.rejlist=new ArrayList<StakeLine>();
       return mc;
      }
     public static CardsRoomTableModel loadInstance(AppView app,String gtype) throws BasicException{
         CardsRoomTableModel mc=new CardsRoomTableModel();
      //   String usercashacc=app.getAppUserView().getUser().getcashaccount();
      //   String userchequeacc=app.getAppUserView().getUser().getchequeaccount();
         List<CardsRoomTableModel.JackpotLine > playinglist = new StaticSentence(app.getSession(),
                " SELECT REFERENCE,SDATE FROM CRPLAYINGLIST WHERE GTYPE=?"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING})
                , new SerializerReadClass(CardsRoomTableModel.JackpotLine.class))
                .list(new Object[]{gtype});

         if(playinglist==null){
              mc.jpPlayinglist=new ArrayList<CardsRoomTableModel.JackpotLine>();
         }else
              mc.jpPlayinglist=playinglist;
       /*  List<CardsRoomTableModel.StakeLine> rejectedList = new StaticSentence(app.getSession(),
                " SELECT A1.TID,A1.TRANSREF,A1.DATE,(SELECT A2.MESSAGE FROM ACCOUNTEDITDETAIL A2 WHERE A2.TID=A1.TID AND MESSAGE IS NOT NULL),A.ID FROM ACCOUNTJOURNALDUP A1,ACCOUNTEDITDETAIL A WHERE A.TID=A1.TID AND A1.ACTIVE=FALSE AND A.CONFIRMER=A1.ACCOUNTID AND A.FLAG IS NULL AND (A1.ACCOUNTID=? OR A1.ACCOUNTID=?) " +
                "UNION SELECT A1.TID,A1.TRANSREF,A1.DATE,(SELECT A2.MESSAGE FROM ACCOUNTEDITDETAIL A2 WHERE A2.TID=A1.TID AND MESSAGE IS NOT NULL),A.ID FROM ACCOUNTJOURNALDUP A1,ACCOUNTEDITDETAIL A WHERE A.TID=A1.TID AND A1.ACTIVE=FALSE AND A.CONFIRMER=? AND A.FLAG IS NULL "
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})
                , new SerializerReadClass(CardsRoomTableModel.StakeLine.class))
                .list(new Object[]{usercashacc,userchequeacc,app.getAppUserView().getUser().getId()});
         if(rejectedList==null){
              mc.rejlist=new ArrayList<CardsRoomTableModel.StakeLine>();
         }else
              mc.rejlist=rejectedList;*/
         return mc;
     }
       public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(Header[column]);
            }
            public int getRowCount() {
                return jpPlayinglist.size();
            }
            public int getColumnCount() {
                return Header.length;
            }


            public Object getValueAt(int row, int column) {
                CardsRoomTableModel.JackpotLine  cd=jpPlayinglist.get(row);
                switch (column) {
                case 0: return cd.getRef();
                case 1: return cd.getsdate();
                default: return null;
                }
            }
        };
    }

     public static class JackpotLine implements SerializableRead {
        private Timestamp sdate;
        private String reference;

        public void readValues(DataRead dr) throws BasicException {
            reference=dr.getString(1);
            sdate=dr.getTimestamp(2);
        }
        public String getRef(){
         return reference;
        }
        public Timestamp getsdate(){
          return sdate;
        }
    }
      public AbstractTableModel getRejTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(Header1[column]);
            }
            public int getRowCount() {
                return rejlist.size();
            }
            public int getColumnCount() {
                return Header1.length;
            }


            public Object getValueAt(int row, int column) {
                CardsRoomTableModel.StakeLine cd=rejlist.get(row);
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
      public static class StakeLine implements SerializableRead {


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
