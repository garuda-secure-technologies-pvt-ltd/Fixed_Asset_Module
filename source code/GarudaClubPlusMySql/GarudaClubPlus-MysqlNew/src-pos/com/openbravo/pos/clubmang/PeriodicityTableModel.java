/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */


public class PeriodicityTableModel {
     private List<Periodicity> fac;
    private final static String[] PERIODICITYHEADERS = {"Name","Type","Number","Date","Accurate","As per DOJ","Pre Payment","Month","Createdby"};
  private PeriodicityTableModel()
  {
  }

  public static PeriodicityTableModel emptyinstance()
  {
      PeriodicityTableModel d=new PeriodicityTableModel();
      d.fac=new ArrayList<Periodicity>();
      return d;
  }
  public static PeriodicityTableModel loadInstance(AppView app) throws BasicException{
      PeriodicityTableModel d = new PeriodicityTableModel();
      List<Periodicity> dlist = new StaticSentence(app.getSession()
                , "SELECT ID,NAME,TYPE_,NO,DATE,FMONTH,BILLTYPE,DOJ,ACCURATE,CREATEDBY FROM PERIODICITY WHERE ACTIVE =TRUE ORDER BY NAME"    //PRAVEEN:changed type to type_
                , null
                ,new SerializerReadClass(Periodicity.class)).list();
         if(dlist==null)
         {
             d.fac=new ArrayList<Periodicity>();
         }
         else
        {
            d.fac=dlist;
        }
     return d;
  }

  public List<Periodicity> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(PERIODICITYHEADERS[column]);
                //sanjeev:commented above line and added below line
                return PERIODICITYHEADERS[column];
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return PERIODICITYHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Periodicity l = fac.get(row);

                switch (column) {
                case 0: return l.getName();
                case 1: return l.gettype();
                case 2: return l.getno();
                case 3: return l.getdate();
                case 4: return l.getaccurate();
                case 5: return l.getdoj();
                case 6: return l.getqbtype();
                case 7: return l.getmonthname();
                case 8: return l.getcreatedby();
                default: return null;
                }
            }
        };
    }




}

