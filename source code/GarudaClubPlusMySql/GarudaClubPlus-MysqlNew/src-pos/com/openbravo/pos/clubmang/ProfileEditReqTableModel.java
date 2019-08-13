/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author swathi
 */
public class ProfileEditReqTableModel {
  private final static String[] HEADER = {"Date","Member Name","Member No","Initiator"};
  private List<RequestLine> preqlist;
  public static ProfileEditReqTableModel emptyInstance(){
     ProfileEditReqTableModel p=new ProfileEditReqTableModel();
     return p;
  }
  public static ProfileEditReqTableModel loadData(AppView app) throws BasicException{
     ProfileEditReqTableModel p=new ProfileEditReqTableModel();
     List list=new StaticSentence(app.getSession(),"SELECT ID,MNAME,SKEY,DATE,NAME,DATA,MID,DDATA FROM (SELECT P.ID AS ID,M.NAME AS MNAME,M.SEARCHKEY AS SKEY,P.DATE AS DATE,P1.NAME AS NAME,P.CHANGEDDATA AS DATA,M.ID AS MID,P.DEPDATA AS DDATA FROM MEMPROFILEEDIT P JOIN CUSTOMERS M ON P.CUSTOMER=M.ID JOIN PEOPLE P1 ON P.INITIATOR=P1.ID WHERE CONFIRMEDBY IS NULL "+
              " UNION ALL SELECT P.ID AS ID,M.NAME AS MNAME,M.SEARCHKEY AS SKEY,P.DATE AS DATE,C1.NAME AS NAME,P.CHANGEDDATA AS DATA,M.ID AS MID,P.DEPDATA AS DDATA FROM MEMPROFILEEDIT P JOIN CUSTOMERS M ON P.CUSTOMER=M.ID JOIN CUSTOMERS C1 ON P.INITIATOR=C1.ID WHERE CONFIRMEDBY IS NULL ) ORDER BY 4,3"
             ,null,new SerializerReadClass(ProfileEditReqTableModel.RequestLine.class)).list();
     if(list!=null)
         p.preqlist=list;
     else
         p.preqlist=new ArrayList<RequestLine>();
     return p;
  }
  public List<RequestLine> getProfileEditRequestList(){
     return preqlist;
  }
  public AbstractTableModel getTableModel(){
    return new AbstractTableModel(){
             @Override

            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADER[column]);
            }
            public int getRowCount() {
                return preqlist.size();
            }

            public int getColumnCount() {
                return HEADER.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                RequestLine l = preqlist.get(rowIndex);
                switch (columnIndex) {
                 case 0: return Formats.DATE.formatValue(l.getDate());
                 case 1: return l.getMemname();
                 case 2: return l.getMemno();
                 case 3: return l.getInitiator();
                 default: return null;
                }
            }

    };
  }
  public static class RequestLine implements SerializableRead{
     private String memname;
     private String memno;
     private String id;
     private Timestamp date;
     private String initiated;
     private String changeddata;
     private String memid;
     private String depdata;

        public void readValues(DataRead dr) throws BasicException {
            id=dr.getString(1);
            memname=dr.getString(2);
            memno=dr.getString(3);
            date=dr.getTimestamp(4);
            initiated=dr.getString(5);
            changeddata=dr.getString(6);
            memid=dr.getString(7);
            depdata=dr.getString(8);
        }
     public String getDepData(){
       return depdata;
     }
     public String getID(){
         /// returns profile edit request id
         return id;
     }
     public String getMemID(){
       return memid;
     }
     public String getChangedData(){
        return changeddata;
     }
     public String getMemname(){
        return memname;
     }
     public String getMemno(){
        return memno;
     }
     public Timestamp getDate(){
        return date;
     }
     public String getInitiator(){
       return initiated;
     }

  }
}
