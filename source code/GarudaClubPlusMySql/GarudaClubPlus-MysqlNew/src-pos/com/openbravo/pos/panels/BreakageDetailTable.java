/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;

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
public class BreakageDetailTable {

    private List<Breakageline> blist;
    private final static String[] BREAKAGEHEADERS = {"Date","Item","Quantity" , "Units","Initiator"};

   private BreakageDetailTable()
   {
   }

  public static BreakageDetailTable emptyinstance()
  {
      BreakageDetailTable b=new BreakageDetailTable();
      b.blist=new ArrayList<Breakageline>();
      return b;
  }
  public static BreakageDetailTable loadInstance(AppView app) throws BasicException {
      BreakageDetailTable d = new BreakageDetailTable();
      //List dlist = new StaticSentence(app.getSession()
           //     ,"SELECT S.DATENEW,P.NAME,S.UNITS1,S.CREATEDBY,S.ID,P.ID FROM STOCKDIARY S,PRODUCTS P WHERE P.ID=S.PRODUCT1 AND S.RECEIVEDBY IS NULL AND REASON1='-3' ORDER BY S.DATENEW ,P.NAME"

            //    ,SerializerWriteString.INSTANCE
            //  ,new SerializerReadClass( BreakageDetailTable.Breakageline.class )).list();
 

/////////////////////////    // EDITED BY AKASH FOR DISPLAYING UNITS
      
      List dlist = new StaticSentence(app.getSession()
                ,"SELECT S.DATENEW,P.NAME,S.UNITS1,S.CREATEDBY,S.ID,P.ID,U.NAME\n" +
                    "FROM STOCKDIARY_BREAKAGE S,PRODUCTS P , UNIT U\n" +
                    "WHERE  REASON1='-3' AND  P.ID=S.PRODUCT1 AND S.RECEIVEDBY IS NULL AND P.UNITTYPE=U.ID\n" +
                    " ORDER BY S.DATENEW ,P.NAME"
                    ,SerializerWriteString.INSTANCE
                    ,new SerializerReadClass( BreakageDetailTable.Breakageline.class )).list();
      
      
     if(dlist==null)
     {
         d.blist=new ArrayList<Breakageline>();
     }
     else
     {
         d.blist=dlist;
     }

     return d;

  }

  public List<Breakageline> getbreakageline()
     {
         return blist;
     }
     public AbstractTableModel getdiscountTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
               // return AppLocal.getIntString(BREAKAGEHEADERS[column]);
                //sanjeev commented above line and added below line
                 return BREAKAGEHEADERS[column];
            }
            public int getRowCount() {
                return blist.size();
            }
            public int getColumnCount() {

                return BREAKAGEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Breakageline l = blist.get(row);

                switch (column) {
               
                case 0: return l.getcdate();
                case 1: return l.getitem();
                case 2: return l.getqty();
                case 3: return  l.getUnit();    
                case 4: return l.getinitiator();
                case 5:  return l.getsid();
                case 6:return l.getpid();
                   
                default: return null;
                }
            }
        };
    }
    public static class Breakageline implements SerializableRead{
    private String Item;
    private Double Qty;
    private String initiator;
    private Timestamp cdate;
    private String sid;
    private String pid;
    private String Units;
    public void readValues(DataRead dr) throws BasicException
    {

        cdate=dr.getTimestamp(1);
        Item=dr.getString(2);
        Qty=dr.getDouble(3);
        initiator=dr.getString(4);
        sid=dr.getString(5);
        pid=dr.getString(6);
        Units=dr.getString(7);
    }

    private String getitem(){
        return Item;
    }
    private Double getqty(){
        return Qty;
    }
     private String getinitiator(){
        return initiator;
    }
      private String getsid(){
        return sid;
    }
      private Timestamp getcdate(){
        return cdate;
    }
      private String getpid(){
          return pid;
      }
      private String getUnit(){
          return Units;
      }
      
  }
}
