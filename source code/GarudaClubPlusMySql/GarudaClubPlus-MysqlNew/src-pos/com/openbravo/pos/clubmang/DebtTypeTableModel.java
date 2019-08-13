/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */

public class DebtTypeTableModel {
     private List<DebtTypeline> fac;
    private final static String[] FACILITYHEADERS = {"name","Type","Num","Created By"};
  private DebtTypeTableModel()
  {
  }

  public static DebtTypeTableModel emptyinstance()
  {
      DebtTypeTableModel d=new DebtTypeTableModel();
      d.fac=new ArrayList<DebtTypeline>();
      return d;
  }
  public static DebtTypeTableModel loadInstance(AppView app) throws BasicException{
      DebtTypeTableModel d = new DebtTypeTableModel();
      List dlist = new StaticSentence(app.getSession()
                ,"SELECT D.ID,D.NAME,D.PERIODTYPE,D.NUMBER ,D.CREATEDBY "
                +"FROM DEBTTYPE D WHERE ACTIVE = TRUE ORDER BY NAME "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( DebtTypeTableModel.DebtTypeline.class )).list();
         if(dlist==null)
         {
             d.fac=new ArrayList<DebtTypeline>();
         }
         else
        {
            d.fac=dlist;
        }
     return d;
  }

  public List<DebtTypeline> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(FACILITYHEADERS[column]);
                //sanjeev:commented above line and added below line
                return FACILITYHEADERS[column];
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return FACILITYHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                DebtTypeline l = fac.get(row);

                switch (column) {
                case 0: return l.getname();
                case 1: return l.getperiod();
                case 2: return l.getNum();
                case 3: return l.getCreatedBy();
                default: return null;
                }
            }
        };
    }


public static class DebtTypeline implements SerializableRead,IKeyed{
    private String id;
    private String name;
    private String period;
    private int num;
    private String crby;
   

    public void readValues(DataRead dr) throws BasicException
    {
        id=dr.getString(1);
        name=dr.getString(2);
        period=dr.getString(3);
        num=dr.getInt(4);
        crby=dr.getString(5);
   }
   public String getCreatedBy(){
      return crby;
   }
  
    public int getNum(){
       return num;
    }

    public String getid() {
     return id;
    }
   public String getname(){
     return name;
   }
     public String getperiod(){
      return period;
    }

        public Object getKey() {
            return id;
        }
        public String toString(){
        return name;
        }
 }

}

