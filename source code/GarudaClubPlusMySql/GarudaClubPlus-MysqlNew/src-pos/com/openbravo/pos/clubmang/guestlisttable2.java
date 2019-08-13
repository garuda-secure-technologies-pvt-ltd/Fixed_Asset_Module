/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
/**
 *
 * @author swathi
 */
public class guestlisttable2 extends BeanFactoryDataSingle{
    private List< MembersGuestLine1> guestlist;
    private final static String[] TABLEHEADERS = {"Date","GuestCat","Amount","Receipt No","Num"};
    private Session s;
    public  guestlisttable2()
   {
   }
    
  public static guestlisttable2 emptyinstance()
  {
      guestlisttable2 d=new  guestlisttable2();
      d.guestlist=new ArrayList< MembersGuestLine1>();
      return d;
  } 
    public static guestlisttable2 loadInstance(AppView app,Date sdate,Date edate , String MemNo) throws BasicException{
     guestlisttable2 d = new guestlisttable2();

         List dlist = new StaticSentence(app.getSession()
                ,"SELECT G.DATE,C.NAME,GC.NAME,G.NUM,G.AMOUNT,G.NAMES,G.RECEIPTNO,G.CREATEDBY "
                +"FROM CUSTOMERS C,GUESTLOG G,GUESTCAT GC WHERE G.MEMNO=C.ID AND G.GUESTCAT=GC.ID AND G.DATE >= ? AND G.DATE < ? AND G.MEMNO=? ORDER BY G.DATE,C.NAME"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP , Datas.STRING})
              ,new SerializerReadClass( guestlisttable2.MembersGuestLine1.class )).list(new Object[]{sdate,edate , MemNo});
         if(dlist==null)
         {
             d.guestlist=new ArrayList<MembersGuestLine1>();
         }
         else
        {
            d.guestlist=dlist;
        }
     return d;

    }
    public List<MembersGuestLine1> getguestlist()
     {
         return guestlist;
     }
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS[column]);
            }
            public int getRowCount() {
                return guestlist.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                MembersGuestLine1 l = guestlist.get(row);

                switch (column) {

                case 0: return l.getdate();
                case 1: return l.getguestcat();
                case 2: return l.getamount();
                case 3: return l.getReceiptNo();
                case 4: return l.getNum();
                 default: return null;
                }
            }
        };
    }

    @Override
    public void init(Session s) {
       this.s = s;
    }


public static class MembersGuestLine1 implements SerializableRead{
    private Timestamp date;
    private String guestcat;
    private Double amount;
    private String receiptno;
    private int num;
 public void readValues(DataRead dr) throws BasicException
    {
        date=dr.getTimestamp(1);
        guestcat=dr.getString(3);
        num=dr.getInt(4);
        amount=dr.getDouble(5);
       receiptno=dr.getString(7);
        
   }

    public Timestamp getdate() {
     return date;
    }
  public String getguestcat(){
      return guestcat;
    }
    public Double getamount(){
       return amount;
    }
     public String getReceiptNo(){
      return receiptno;
    }
    public int getNum(){
      return num;
    }
 }
}
