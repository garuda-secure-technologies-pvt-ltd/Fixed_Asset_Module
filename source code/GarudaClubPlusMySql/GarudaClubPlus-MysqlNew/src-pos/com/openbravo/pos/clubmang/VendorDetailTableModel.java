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
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class VendorDetailTableModel {
   private List<Vendorline> fac;
    private final static String[] VENDORHEADERS = {"Name","Address","Cnt Person","Cnt No","PAN","TIN","CST","Account","Inst","Createdby","Deactivated by"};
  private VendorDetailTableModel()
  {
  }

  public static VendorDetailTableModel emptyinstance()
  {
      VendorDetailTableModel d=new VendorDetailTableModel();
      d.fac=new ArrayList<Vendorline>();
      return d;
  }
  public static VendorDetailTableModel loadInstance(AppView app) throws BasicException{
      VendorDetailTableModel d = new VendorDetailTableModel();
      List dlist = new StaticSentence(app.getSession()
                , "SELECT V.NAME,V.ADDRESS,V.CONTACTPERSON,V.CONTACTNUM,V.PANNO,V.TINNO,V.CST,A.NAME,V.INSTRUCTION,V.CREATEDBY,V.DEACTBY,V.ID FROM VENDOR V,ACCOUNTMASTER A WHERE V.ACTIVE =TRUE AND A.ID=V.ACCOUNT ORDER BY V.NAME"
                , null
                ,new SerializerReadClass(Vendorline.class)).list();
         if(dlist==null)
         {
             d.fac=new ArrayList<Vendorline>();
         }
         else
        {
            d.fac=dlist;
        }
     return d;
  }

  public List<Vendorline> getfacilityline()
     {
         return fac;
     }
  public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(VENDORHEADERS[column]);
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return VENDORHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Vendorline l = fac.get(row);

                switch (column) {
                case 0: return l.getname();
                case 1: return l.getaddress();
                case 2: return l.getcontactPerson();
                case 3: return l.getContactnumber();
                case 4: return l.getpanno();
                case 5: return l.gettinno();
                case 6: return l.getcstno();
                case 7: return l.getaccount();
                case 8: return l.getinstruction();
                case 9: return l.getcrby();
                case 10:return l.getdeactivatedby();
                case 11:return l.getid();
                default: return null;
                }
            }
        };
    }
public static class Vendorline implements SerializableRead,IKeyed{
    private String id;
    private String name;
   // private String rperiod;
    private String address;
    private String cperson;
    private String cnumber;
    private String panno;
    private String tinno;
    private String cstno;
   // private String account;

    private String createdby;
    private String deactivatedby;
    private String account;
    private String instruction;


    public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(12);
        name=dr.getString(1);
        address=dr.getString(2);
        cperson=dr.getString(3);
        cnumber=dr.getString(4);
        panno=dr.getString(5);
        tinno=dr.getString(6);
        cstno=dr.getString(7);
        account=dr.getString(8);
        instruction=dr.getString(9);
        createdby=dr.getString(10);
        deactivatedby=dr.getString(11);
   }
    public String getaddress(){
      return address;
    }
    public String getdeactivatedby(){
      return deactivatedby;
    }
    public String getcontactPerson(){
      return cperson;
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
    public String getContactnumber(){
      return cnumber;
    }
     public String gettinno(){
      return tinno;
    }
    public String getpanno(){
       return panno;
    }
     public String getcstno(){
      return cstno;
    }
    public String getaccount(){
       return account;
    }
    public String getinstruction(){
       return instruction;
    }
    public Object getKey() {
            return id;
        }
    @Override
    public String toString(){
       return name;
    }

 }

}
