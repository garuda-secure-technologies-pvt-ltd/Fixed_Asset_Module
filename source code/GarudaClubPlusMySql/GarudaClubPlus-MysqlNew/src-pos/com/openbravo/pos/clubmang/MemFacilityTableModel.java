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
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MemFacilityTableModel {
     private List<Facilityline> fac;
    private final static String[] FACILITYHEADERS = {"No","Member name","Facility Type","Startdate","last bill date","Created by"};
  private MemFacilityTableModel()
   {
   }

  public static MemFacilityTableModel emptyinstance()
  {
      MemFacilityTableModel d=new MemFacilityTableModel();
      d.fac=new ArrayList<Facilityline>();
      return d;
  }
  public static MemFacilityTableModel loadInstance(AppView app,String type) throws BasicException{
      MemFacilityTableModel d = new MemFacilityTableModel();
    
         List dlist = new StaticSentence(app.getSession()               //praveen_changed query
                ,"SELECT M.ID,C.SEARCHKEY,CASE WHEN M.USERID IS NULL THEN C.NAME ELSE (SELECT DNAME FROM MEMDEPENDENT WHERE ID=USERID) END,F.NAME,M.SDATE,M.LBILLDATE,M.CREATEDBY "
                +"FROM MEMFACILITYUSAGE M,FACILITY F ,CUSTOMERS C WHERE M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE AND C.ID=M.MEMNO AND M.FACILITYTYPE=? ORDER BY C.SEARCHKEY"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( MemFacilityTableModel.Facilityline.class )).list(type);
         if(dlist==null)
         {
             d.fac=new ArrayList<Facilityline>();
         }
         else
        {
            d.fac=dlist;
        }
      
    

     return d;

  }

  public List<Facilityline> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(FACILITYHEADERS[column]);
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return FACILITYHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Facilityline l = fac.get(row);

                switch (column) {

                case 0: return l.getmno();
                case 1: return l.getmname();
                case 2: return l.getfname();
                case 3: return l.getsdate();
                case 4: return l.getlbilldate();
                case 5: return l.getcrby();
                case 6: return l.getid();

                default: return null;
                }
            }
        };
    }


public static class Facilityline implements SerializableRead{
    private String id;
    private String mno;
    private String mname;
    private String fname;
    private Timestamp sdate;
    private Timestamp lbdate;
    private String createdby;
   

    public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(1);
        mno=dr.getString(2);
        mname=dr.getString(3);
        fname=dr.getString(4);
        sdate=dr.getTimestamp(5);
        lbdate=dr.getTimestamp(6);
        createdby=dr.getString(7);
   }

    public String getid() {
     return id;
    }
   public String getmno(){
     return mno;
   }
    public String getcrby(){
        return createdby;
    }
    public Timestamp getsdate(){
      return sdate;
    }
     public Timestamp getlbilldate(){
      return lbdate;
    }
    public String getmname(){
       return mname;
    }
    public String getfname(){
       return fname;
    }
  
 }

}
