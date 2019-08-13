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
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MemberDependentTableModel {
     private List<Dependentline> fac;
    private final static String[] HEADERS = {"No","Name","DOB","Type","DOJ","Status"};
  private  MemberDependentTableModel()
   {
   }

  public static  MemberDependentTableModel emptyinstance()
  {
       MemberDependentTableModel d=new  MemberDependentTableModel();
      d.fac=new ArrayList<Dependentline>();
      return d;
  }
  public static  MemberDependentTableModel loadInstance(AppView app,String ID) throws BasicException{
       MemberDependentTableModel d = new  MemberDependentTableModel();

         List<Dependentline> dlist = new StaticSentence(app.getSession()
                ,"SELECT DMEMNO,DNAME,DOB,DOJ,DTYPE,MEMTYPE,MEMREF,ID "
                +"FROM MEMDEPENDENT WHERE MEMNO=? AND ACTIVE = TRUE "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass(MemberDependentTableModel.Dependentline.class )).list(ID);
         if(dlist==null)
         {
             d.fac=new ArrayList<Dependentline>();
         }
         else
        {
            d.fac=dlist;
        }



     return d;

  }

  public List<Dependentline> getDependentList()
     {
         return fac;
     }
   public void setDependentList(List<Dependentline> obj){
        fac=obj;
     }
  public void addDependentLine(Dependentline dline){
      fac.add(dline);
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
                Dependentline l = fac.get(row);
                switch (column) {
                case 0: return l.getdno();
                case 1: return l.getdname();
                case 2: return l.getDob();
                case 3: return l.gettype();
                case 4: return l.getDoj();
                case 5: return l.getStatus();
                case 6: return l.getMemtype();
                case 7: return l.getMemref();
                default: return null;
                }
            }
        };
    }


public static class Dependentline implements SerializableRead {
    // DNAME,DMEMNO,DOB,DOJ,DTYPE,MEMTYPE,MEMREF
    private String memref;
    private String dno;
    private String dname;
    private String dtype;
    private Date dob;
    private Date doj;
    private String memtype;
    private String id;
    private String status;

 

    public void readValues(DataRead dr) throws BasicException
    {
        dno=dr.getString(1);
        dname=dr.getString(2);
        dob=(Date)Formats.DATE.parseValue(Formats.DATE.formatValue(dr.getTimestamp(3)));
        doj=(Date)Formats.DATE.parseValue(Formats.DATE.formatValue(dr.getTimestamp(4)));
        dtype=dr.getString(5);
        memtype=dr.getString(6);
        memref=dr.getString(7);
        id=dr.getString(8);
        status=null;
   }
     public static Dependentline CreateInstance(Object[] obj){
       Dependentline d=new Dependentline();
       d.dname=String.valueOf(obj[0]);
       d.dno=String.valueOf(obj[1]);
       try {
           d.dob = (Date)(Formats.DATE.parseValue(Formats.DATE.formatValue(obj[2])));
           d.doj = (Date)(Formats.DATE.parseValue(Formats.DATE.formatValue(obj[3])));

       } catch (BasicException ex) {
           ex.printStackTrace();
       }
       d.dtype=String.valueOf(obj[4]);
       d.memtype=null;
       d.memref=null;
       d.id=String.valueOf(obj[5]);
       d.status=String.valueOf(obj[6]);
       return d;
    }
   public String getStatus(){
       return status;
   }
   public void setStatus(String text){
       status=text;
   }
    public String gettype() {
     return dtype;
    }
   public String getdno(){
     return dno;
   }
    public String getMemtype(){
        return memtype;
    }
    public Date getDob(){
      return dob;
    }
     public Date getDoj(){
      return doj;
    }
    public String getdname(){
       return dname;
    }
    public String getMemref(){
       return memref;
    }
    public String getID(){
      return id;
    }
 }

}
