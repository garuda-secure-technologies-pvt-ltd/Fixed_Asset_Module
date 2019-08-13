/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.adminUtils;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class AJTableModel {
       private List<AccountjournalTable> ajlist;
       private static String[] HEADER=new String[]{"DATE","TNO","TRANSREF","NARRATION","CREDIT","DEBIT","DIFFERENCE"};
       public static AJTableModel emptyInstance(){
           AJTableModel p=new AJTableModel();
           p.ajlist=new ArrayList();
           return p;
       }

       public static AJTableModel loadData(AppView app,Date sdate,Date edate) throws BasicException{
           AJTableModel p=new AJTableModel();
           List<AccountjournalTable> l=null;
          /* if(status)
                l=new PreparedSentence(app.getSession()
                      , "select total,tno,ino,dchallan,docref,vname,crdate,crby,id,vid,warehouse,deactby,deactdate from (SELECT P.TOTAL as total,P.TNO as tno,P.INVOICENO as ino,P.DELIVERYCHALLAN as dchallan,DOCUMENTREF as docref,V.NAME as vname,P.CRDATE as crdate,P.CREATEDBY as crby, P.ID as id ,V.ID AS VID,P.WAREHOUSE AS WAREHOUSE,P.DEACTBY AS DEACTBY,P.DEACTDATE AS DEACTDATE  FROM PURCHASEJOURNALMAIN P JOIN VENDOR V ON P.VENDOR=V.ID WHERE P.CRDATE>? AND P.CRDATE<?"
                     // +" GROUP BY P.TNO,P.INVOICENO,P.DELIVERYCHALLAN,DOCUMENTREF,V.NAME,P.CRDATE,P.CREATEDBY  "
                      +" UNION ALL SELECT P.TOTAL as total,P.TNO as tno,P.INVOICENO as ino,P.DELIVERYCHALLAN as dchallan,DOCUMENTREF as docref,NULL as vname,P.CRDATE as crdate,P.CREATEDBY as crby, P.ID as id,NULL AS VID,P.WAREHOUSE AS WAREHOUSE,P.DEACTBY AS DEACTBY,P.DEACTDATE AS DEACTDATE FROM PURCHASEJOURNALMAIN P  WHERE P.VENDOR IS NULL AND P.CRDATE>? AND P.CRDATE<?"
                      +" )  order by crdate,tno"
                      , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                      , new SerializerReadClass(purchasejournalEditorTableModel.PurchasejournalTable.class)).list(new Object[]{sdate,edate,sdate,edate});
           else*/
                l=new PreparedSentence(app.getSession()
                      , "select Date,tid ,transref,narration,cr,dr,(cr-dr)as diff from (select Date ,transref,narration, tid,sum(cr) as cr,sum(dr) as dr from (select Date ,transref,narration,a.tid as tid,sum(a.amount) as cr,0.0 as dr from accountjournal a where a.transtype='C' group by a.tid "+
                        " union all select Date ,transref,narration,a.tid as tid,0.0 as cr,sum(a.amount)  as dr from accountjournal a where a.transtype='D' group by tid  )as aj1 group by tid)as aj2 where cr!=dr AND date>? AND date<?"
                      , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                      , new SerializerReadClass(AJTableModel.AccountjournalTable.class)).list(new Object[]{sdate,edate});


           if(l==null)
               p.ajlist=new ArrayList();
           else
               p.ajlist=l;
           return p;
       }
       public List<AccountjournalTable> getAccountJournalList(){
          return ajlist;
       }
       public AbstractTableModel getTableModel(){
          return new AbstractTableModel() {

            public int getRowCount() {
               return  ajlist.size();
            }
            @Override
             public String getColumnName(int column) {
                 return HEADER[column];
             }
            public int getColumnCount() {
               return HEADER.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                AccountjournalTable pj=(AccountjournalTable)ajlist.get(rowIndex);
                switch(columnIndex){
                    case 0: return Formats.DATE.formatValue(pj.getDate());
                    case 1: return pj.getTid();
                    case 2: return pj.getTransref();
                    case 3: return pj.getNarration();
                    case 4: return pj.getCredit();
                    case 5: return pj.getDebit();
                    case 6: return pj.getDiff();
                   
                    default: return null;
                }
            }
        };
       }
       public static class AccountjournalTable implements SerializableRead{
           private Date date;

        public double getCredit() {
            return credit;
        }

        public Date getDate() {
            return date;
        }

        public double getDebit() {
            return debit;
        }

        public double getDiff() {
            return diff;
        }

        public String getNarration() {
            return narration;
        }

        public String getTid() {
            return tid;
        }

        public String getTransref() {
            return transref;
        }
           private String tid;
           private String transref;
           private String narration;
           private double credit;
           private double debit;
           private double diff;


        public void readValues(DataRead dr) throws BasicException {
             date=dr.getTimestamp(1);
             tid=dr.getString(2);
             transref=dr.getString(3);
             narration=dr.getString(4);
             credit=dr.getDouble(5);
             debit=dr.getDouble(6);
             diff=dr.getDouble(7);
        }
        
       }
}
