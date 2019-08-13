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
//import com.openbravo.data.loader.SerializerWriteString;
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
public class CollTransferManagerTableModel {
      private List<CollectionTransferLine> ctransfer;
      private final static String[] Header={"Date","Cash Total","Cheque Total","Sender","Received","Receiver"};

      public static CollTransferManagerTableModel emptyinstance(){
         CollTransferManagerTableModel mc=new CollTransferManagerTableModel();
         mc.ctransfer=new ArrayList<CollectionTransferLine>();
       return mc;
      }
     public static CollTransferManagerTableModel loadInstance(AppView app) throws BasicException{
         CollTransferManagerTableModel mc=new CollTransferManagerTableModel();
         String userid=app.getAppUserView().getUser().getId();
          List<CollectionTransferLine> chdetail = new StaticSentence(app.getSession(),
                " SELECT C.DATE,C.CASHTOTAL,C.CHEQUETOTAL,P.NAME,C.RECEIVED,C.ID,P1.NAME,C.CHEQUEDETAIL,C.NO FROM COLLECTIONTRANSFER C,PEOPLE P,PEOPLE P1 WHERE (C.RECEIVER=? OR C.SENDER=?) AND P.ID=C.SENDER AND P1.ID=C.RECEIVER AND RECEIVED=FALSE ORDER BY C.DATE"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                , new SerializerReadClass(CollTransferManagerTableModel.CollectionTransferLine.class))
                .list(new Object[]{userid,userid});
          if(chdetail==null){
              mc.ctransfer=new ArrayList<CollectionTransferLine>();
          }else
              mc.ctransfer=chdetail;

         return mc;
     }
       public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
               // return AppLocal.getIntString(Header[column]);
                //sanjeev commented above line and added below line
                 return Header[column];
            }
            public int getRowCount() {
                return ctransfer.size();
            }
            public int getColumnCount() {
                return Header.length;
            }


            public Object getValueAt(int row, int column) {
                CollectionTransferLine cd=ctransfer.get(row);
                switch (column) {
                case 0: return Formats.DATE.formatValue(cd.getdate());
                case 1: return cd.getcashtotal();
                case 2: return cd.getchequetotal();
                case 3: return cd.getcreatedby();
                case 4: return cd.getreceived();
                case 5: return cd.getReceiver();
                case 6: return cd.getid();
                case 7: return cd.getChequeDetail();
                case 8: return cd.getno();
                default: return null;
                }
            }
        };
    }

     public static class CollectionTransferLine implements SerializableRead {


        private Timestamp date;
        private Double cashtotal;
        private Double chequetotal;
        private String createdby;
        private Boolean received;
        private String id;
        private String receiver;
        private String chequeids;
        private int no;

        public void readValues(DataRead dr) throws BasicException {

            date=dr.getTimestamp(1);
            cashtotal=dr.getDouble(2);
            chequetotal=dr.getDouble(3);
            createdby=dr.getString(4);
            received=dr.getBoolean(5);
            id=dr.getString(6);
            receiver=dr.getString(7);
            chequeids=dr.getString(8);
            no=dr.getInt(9);
        }
        public int getno(){
           return no;
        }
        public String getChequeDetail(){
           return chequeids;
        }
        public Boolean getreceived(){
          return received;
        }
        public String getReceiver(){
          return receiver;
        }

        public Double getcashtotal(){
           return cashtotal;
        }
        public Double getchequetotal(){
           return chequetotal;
        }
        public String getid(){
         return id;
        }
        public String getcreatedby(){
        return createdby;
        }
        public Timestamp getdate(){
          return date;
        }
    }

}
