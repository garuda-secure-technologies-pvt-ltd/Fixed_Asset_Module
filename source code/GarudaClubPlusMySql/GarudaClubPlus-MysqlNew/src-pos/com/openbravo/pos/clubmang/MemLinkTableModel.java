/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
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
public class MemLinkTableModel {
   private List<Linkline> fac;
    private final static String[] HEADERS = {"No","Member name","Type"};
  private  MemLinkTableModel()
   {
   }

  public static  MemLinkTableModel emptyinstance()
  {
       MemLinkTableModel d=new  MemLinkTableModel();
      d.fac=new ArrayList<Linkline>();
      return d;
  }
  public static  MemLinkTableModel loadInstance(AppView app,String ID) throws BasicException{
       MemLinkTableModel d = new  MemLinkTableModel();

         List dlist = new StaticSentence(app.getSession()
                ,"SELECT C.SEARCHKEY,C.NAME,M.LINKTYPE "
                +"FROM MEMLINK M,CUSTOMERS C WHERE M.MEMNO1=C.ID AND M.MEMNO2=? "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( MemLinkTableModel.Linkline.class )).list(ID);
         if(dlist==null)
         {
             d.fac=new ArrayList<Linkline>();
         }
         else
        {
            d.fac=dlist;
        }
     return d;

  }

  public List<Linkline> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS[column]);
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return HEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Linkline l = fac.get(row);

                switch (column) {
                case 0: return l.getlno();
                case 1: return l.getlname();
                case 2: return l.gettype();
                default: return null;
                }
            }
        };
    }


public static class Linkline implements SerializableRead{
    private String lno;
    private String lname;
    private String ltype;
   
    public void readValues(DataRead dr) throws BasicException
    {
        lno=dr.getString(1);
        lname=dr.getString(2);
        ltype=dr.getString(3);
   }

    public String gettype() {
     return ltype;
    }
   public String getlno(){
     return lno;
   }
    public String getlname(){
       return lname;
    }
 }
}
