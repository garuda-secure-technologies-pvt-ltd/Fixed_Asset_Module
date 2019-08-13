/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

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
public class purchasejournalEditorTableModel {
       private List<PurchasejournalTable> pjlist;
       private static String[] HEADER=new String[]{"Date","TNO","INVOICE_NO","DCHALLAN","DOCREF","VENDOR","TOTAL","CREATEDBY"};//,"DEACTIVATED BY","DEACTIVATED DATE"
       public static purchasejournalEditorTableModel emptyInstance(){
           purchasejournalEditorTableModel p=new purchasejournalEditorTableModel();
           p.pjlist=new ArrayList();
           return p;
       }

       public static purchasejournalEditorTableModel loadData(AppView app,Date sdate,Date edate) throws BasicException{
           purchasejournalEditorTableModel p=new purchasejournalEditorTableModel();
           List<PurchasejournalTable> l=null;
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
                      , "select total,tno,ino,dchallan,docref,vname,crdate,crby,id,vid,warehouse,deactby,deactdate from (SELECT P.TOTAL as total,P.TNO as tno,P.INVOICENO as ino,P.DELIVERYCHALLAN as dchallan,DOCUMENTREF as docref,V.NAME as vname,P.CRDATE as crdate,P.CREATEDBY as crby, P.ID as id ,V.ID AS VID,P.WAREHOUSE AS WAREHOUSE,P.DEACTBY AS DEACTBY,P.DEACTDATE AS DEACTDATE  FROM PURCHASEJOURNALMAIN P JOIN VENDOR V ON P.VENDOR=V.ID WHERE P.CRDATE>? AND P.CRDATE<? AND P.DEACTBY IS NULL "
                     // +" GROUP BY P.TNO,P.INVOICENO,P.DELIVERYCHALLAN,DOCUMENTREF,V.NAME,P.CRDATE,P.CREATEDBY  "
                      +" UNION ALL SELECT P.TOTAL as total,P.TNO as tno,P.INVOICENO as ino,P.DELIVERYCHALLAN as dchallan,DOCUMENTREF as docref,NULL as vname,P.CRDATE as crdate,P.CREATEDBY as crby, P.ID as id,NULL AS VID,P.WAREHOUSE AS WAREHOUSE,P.DEACTBY AS DEACTBY,P.DEACTDATE AS DEACTDATE FROM PURCHASEJOURNALMAIN P  WHERE P.VENDOR IS NULL AND P.CRDATE>? AND P.CRDATE<? AND P.DEACTBY IS NULL "
                      +" ) AS PURJOUR order by crdate,tno"
                      , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                      , new SerializerReadClass(purchasejournalEditorTableModel.PurchasejournalTable.class)).list(new Object[]{sdate,edate,sdate,edate});
           

           if(l==null)
               p.pjlist=new ArrayList();
           else
               p.pjlist=l;
           return p;
       }
       public List<PurchasejournalTable> getPurchaseList(){
          return pjlist;
       }
       public AbstractTableModel getTableModel(){
          return new AbstractTableModel() {

            public int getRowCount() {
               return  pjlist.size();
            }
            @Override
             public String getColumnName(int column) {
                 return HEADER[column];
             }
            public int getColumnCount() {
               return HEADER.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchasejournalTable pj=(PurchasejournalTable)pjlist.get(rowIndex);
                switch(columnIndex){
                    case 0: return Formats.DATE.formatValue(pj.getDate());
                    case 1: return pj.getTNO();
                    case 2: return pj.getInvoiceNo();
                    case 3: return pj.getDeliveryChallan();
                    case 4: return pj.getDocref();
                    case 5: return pj.getVendorName();
                    case 6: return Formats.ConvertDoubleToString(pj.getTotal());
                    case 7: return pj.getCreatedby();
                    //case 8: return pj.getDeactivatedby();
                   // case 9: return pj.getDeactDate();
                    default: return null;
                }
            }
        };
       }
       public static class PurchasejournalTable implements SerializableRead{
           private String tno;
           private String invoiceno;
           private String deliverychallan;
           private String docref;
           private String vname;
           private String crby;
           private Date crdate;
           private double total;
           private String id;
           private String vid;
           private String warehouse;
           private String deactby;
           private Date deactdate;

        public void readValues(DataRead dr) throws BasicException {
            total=dr.getDouble(1);
            tno=dr.getString(2);
            invoiceno=dr.getString(3);
            deliverychallan=dr.getString(4);
            docref=dr.getString(5);
            vname=dr.getString(6);
            crdate=dr.getTimestamp(7);
            crby=dr.getString(8);
            id=dr.getString(9);
            vid=dr.getString(10);
            warehouse=dr.getString(11);
            deactby=dr.getString(12);
            deactdate=dr.getTimestamp(13);
        }
        public String getDeactivatedby(){
           return deactby;
        }
        public Date getDeactDate(){
            return deactdate;
        }
        public String getWarehouse(){
          return warehouse;
        }
        public String getVendorID(){
           return vid;
        }
        public String getID(){
           return id;
        }
        public String getTNO(){
          return tno;
        }
        public String getInvoiceNo(){
          return invoiceno;
        }
        public String getDeliveryChallan(){
          return deliverychallan;
        }
        public String getDocref(){
         return docref;
        }
        public String getVendorName(){
          return vname;
        }
        public Date getDate(){
          return crdate;
        }
        public String getCreatedby(){
          return crby;
        }
        public double getTotal(){
         return total;
        }
       }
}
