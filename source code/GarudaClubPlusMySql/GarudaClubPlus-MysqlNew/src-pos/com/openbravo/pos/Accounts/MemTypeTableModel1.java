/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
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
public class MemTypeTableModel1 {
    private List<MemTypeline> fac;
    private int flag;
    private final static String[] TABLEHEADERS = {"Name","Period","Max Debt","Created by","Cr Date"};
    private final static String[] TABLEHEADERS1 = {"Name","Period","Max Debt","Cr Date","Created by","Deactivated Date","Deactivated By"};
  private MemTypeTableModel1()
  {
  }

  public static MemTypeTableModel1 emptyinstance()
  {
      MemTypeTableModel1 d=new MemTypeTableModel1();
      d.fac=new ArrayList<MemTypeline>();
      return d;
  }
  public static MemTypeTableModel1 loadInstance(AppView app,int flag) throws BasicException{
      MemTypeTableModel1 d = new MemTypeTableModel1();
         d.flag=flag;
         List dlist=new ArrayList();
         if(flag==1){
           dlist = new StaticSentence(app.getSession()
                ,"SELECT M.ID,M.NAME,P1.NAME,M.DATE,M.CREATEDBY,M.DEACTIVATEDDATE,M.DEACTIVATEDBY,M.DEBTMAX "
                +"FROM MEMTYPE M,PERIODICITY P1 WHERE ACTIVE = TRUE  AND P1.ID=M.PERIOD "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( MemTypeTableModel1.MemTypeline.class )).list();
         }else
         {
            dlist = new StaticSentence(app.getSession()
                ,"SELECT M.ID,M.NAME,P1.NAME,M.DATE,M.CREATEDBY,M.DEACTIVATEDDATE,M.DEACTIVATEDBY,M.DEBTMAX "
                +"FROM MEMTYPE M,PERIODICITY P1 WHERE  P1.ID=M.PERIOD "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( MemTypeTableModel1.MemTypeline.class )).list();
         }
         if(dlist==null)
         {
             d.fac=new ArrayList<MemTypeline>();
         }
         else
        {
            d.fac=dlist;
        }



     return d;

  }


  public List<MemTypeline> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                if(flag==1)
                  return AppLocal.getIntString(TABLEHEADERS[column]);
                else
                  return AppLocal.getIntString(TABLEHEADERS1[column]);
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {
                if(flag==1)
                 return TABLEHEADERS.length;
                else
                 return TABLEHEADERS1.length;
            }
            public Object getValueAt(int row, int column) {
                MemTypeline l = fac.get(row);

                switch (column) {

                case 0: return l.getname();
               // case 1: return l.getrperiod();
                case 1: return l.getperiod();
                case 2: return l.getDebtMax();
                case 3: return l.getcrby();
                case 4: return l.getdate();
                case 5: return l.getdeactivatedDate();
                case 6: return l.getdeactivatedby();
                case 7:return l.getid();
                default: return null;
                }
            }
        };
    }


public static class MemTypeline implements SerializableRead,IKeyed{
    private String id;
    private String name;
   // private String rperiod;
    private String period;
   // private String account;
    private Timestamp date;
    private Timestamp deactivateddate;
    private String createdby;
    private String deactivatedby;
    private Double debtmax;


    public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(1);
        name=dr.getString(2);
       // rperiod=dr.getString(3);
        period=dr.getString(3);
       // account=dr.getString(5);
        date=dr.getTimestamp(4);
        createdby=dr.getString(5);
        deactivateddate=dr.getTimestamp(6);
        deactivatedby=dr.getString(7);
        debtmax=dr.getDouble(8);
   }
    public Double getDebtMax(){
      return debtmax;
    }
    public String getdeactivatedby(){
      return deactivatedby;
    }
    public Timestamp getdeactivatedDate(){
      return deactivateddate;
    }
    public String getid() {
     return id;
    }
   public String getname(){
     return name;
   }
    public String getcrby(){
        return createdby;
    }
    public Timestamp getdate(){
      return date;
    }
    // public String getrperiod(){
   //   return rperiod;
   // }
    public String getperiod(){
       return period;
    }

        public Object getKey() {
            return id;
        }
    @Override
    public String toString(){
       return name;
    }
    public void setID(String id){
       this.id=id;
    }
    public void setName(String name){
       this.name=name;
    }

 }

}
