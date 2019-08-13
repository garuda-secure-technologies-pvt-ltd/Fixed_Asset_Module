/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
//import javax.swing.tree.TreePath;
//import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author swathi
 */
public class EditorTableModel {

    
      private List<AJline> fac;
    private final static String[] HEADERS = {"Date","Type","Trans Ref","Amount","Created By"};
  public EditorTableModel()
  {
  }

  public static EditorTableModel emptyinstance()
  {
      EditorTableModel d=new EditorTableModel();
      d.fac=new ArrayList<AJline>();
      return d;
  }
  public static EditorTableModel loadInstance1(AppView app,String id,Date sdate,Date edate) throws BasicException{
      EditorTableModel d = new EditorTableModel();
      List dlist = new StaticSentence(app.getSession()
                ,"SELECT AJ.TID,AJ.DATE,AJ.TRANSREF,AJ.TRANSTYPE,AJ.AMOUNT,AJ.CREATEDBY  "
                +"FROM ACCOUNTJOURNAL AJ WHERE AJ.ACCOUNTID=? AND AJ.DATE>=? AND AJ.DATE<=?  AND AJ.ACTIVE=TRUE AND (AJ.TRANSREF='Journal' OR AJ.TRANSREF='Contra' OR AJ.TRANSREF='Receipt' OR AJ.TRANSREF='Payments')  ORDER BY AJ.DATE "
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( EditorTableModel.AJline.class )).list(new Object[]{id,sdate,edate});
         if(dlist==null)
         {
             d.fac=new ArrayList<AJline>();
         }
         else
        {
            d.fac=dlist;
        }
     return d;
  }
 public static EditorTableModel loadInstance(AppView app,String id) throws BasicException{
      EditorTableModel d = new EditorTableModel();
      List dlist = new StaticSentence(app.getSession()
                ,"SELECT AJ.TID,AJ.DATE,AJ.TRANSREF,AJ.TRANSTYPE,AJ.AMOUNT,AJ.CREATEDBY  "
                +"FROM ACCOUNTJOURNAL AJ WHERE AJ.ACCOUNTID=? AND AJ.ACTIVE=TRUE AND (AJ.TRANSREF='Journal' OR AJ.TRANSREF='Contra' OR AJ.TRANSREF='Receipt' OR AJ.TRANSREF='Payments')  ORDER BY AJ.DATE "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( EditorTableModel.AJline.class )).list(id);
         if(dlist==null)
         {
             d.fac=new ArrayList<AJline>();
         }
         else
        {
            d.fac=dlist;
        }
     return d;
  }

  public List<AJline> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return HEADERS[column];
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return HEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                AJline l = fac.get(row);

                switch (column) {
                case 0: return l.getDate();
                case 1: return l.getTranstype();
                case 2: return l.getTransref();
                case 3: return l.getAmt();
                case 4: return l.getCreatedBy();
                case 5: return l.getid();
                default: return null;
                }
            }

         
        };
    }


public static class AJline implements SerializableRead,IKeyed{
    private String id;
    private Timestamp date;
    private String transref;
    private String transtype;
    private Double amt;
    private String crby;


    public void readValues(DataRead dr) throws BasicException
    {
        id=dr.getString(1);
        date=dr.getTimestamp(2);
        transref=dr.getString(3);
        transtype=dr.getString(4);
        amt=dr.getDouble(5);
        crby=dr.getString(6);
   }
   public String getCreatedBy(){
      return crby;
   }

    public Double getAmt(){
       return amt;
    }

    public String getid() {
     return id;
    }
   public String getTranstype(){
     return transtype;
   }
     public String getTransref(){
      return transref;
    }
    public Timestamp getDate(){
      return date;
    }
    public Object getKey() {
         return id;
    }
    public String toString(){
      return transref;
   }
 }

}
