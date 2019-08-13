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
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class GuestlistTableModel {
    private List<MembersGuestLine> guestlist;
    private final static String[] TABLEHEADERS = {"Date","Mem Name","Guest Cat","Num","Guest Fees","Tax Amt" , "Grand Tot.","Guest name","Receipt No","Created By"};
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    
    private GuestlistTableModel()
   {
   }

  public static GuestlistTableModel emptyinstance()
  {
      GuestlistTableModel d=new GuestlistTableModel();
      d.guestlist=new ArrayList<MembersGuestLine>();
      return d;
  }
  public static GuestlistTableModel loadInstance(AppView app,Date sdate,Date edate) throws BasicException{
      GuestlistTableModel d = new GuestlistTableModel();

         List dlist = new StaticSentence(app.getSession()
                ,"SELECT G.DATE,C.NAME,GC.NAME,G.NUM,G.AMOUNT,G.NAMES,G.RECEIPTNO,G.CREATEDBY ,C.SEARCHKEY,IFNULL(G.TAXAMOUNT,0.00) "
                +"FROM CUSTOMERS C,GUESTLOG G,GUESTCAT GC WHERE G.MEMNO=C.ID AND G.GUESTCAT=GC.ID AND G.DATE >= ? AND G.DATE < ? ORDER BY G.DATE,C.NAME"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( GuestlistTableModel.MembersGuestLine.class )).list(new Object[]{sdate,edate});
         if(dlist==null)
         {
             d.guestlist=new ArrayList<MembersGuestLine>();
         }
         else
        {
            d.guestlist=dlist;
        }



     return d;

  }
  
  
  
  public static GuestlistTableModel loadInstanceForOneGuestCat(AppView app,Date sdate,Date edate ,String GuestCategory) throws BasicException{
      GuestlistTableModel d = new GuestlistTableModel();

         List dlist = new StaticSentence(app.getSession()
                ,"SELECT G.DATE,C.NAME,GC.NAME,G.NUM,G.AMOUNT,G.NAMES,G.RECEIPTNO,G.CREATEDBY ,C.SEARCHKEY, IFNULL(G.TAXAMOUNT,0.00)"
                +"FROM CUSTOMERS C,GUESTLOG G,GUESTCAT GC WHERE G.MEMNO=C.ID AND G.GUESTCAT=GC.ID AND G.DATE >= ? AND G.DATE < ?  AND GC.NAME=?  ORDER BY G.DATE,C.NAME"
              ,new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP , Datas.STRING  })
              ,new SerializerReadClass( GuestlistTableModel.MembersGuestLine.class )).list(new Object[]{sdate,edate,GuestCategory});
         if(dlist==null)
         {
             d.guestlist=new ArrayList<MembersGuestLine>();
         }
         else
        {
            d.guestlist=dlist;
        }



     return d;

  }
  //addded by pratima to get the guest details memberwise
   public static GuestlistTableModel loadInstanceForOneMem(AppView app,Date sdate,Date edate,String searchkey) throws BasicException{
      GuestlistTableModel d = new GuestlistTableModel();

         List dlist = new StaticSentence(app.getSession()
                ,"SELECT G.DATE,C.NAME,GC.NAME,G.NUM,G.AMOUNT,G.NAMES,G.RECEIPTNO,G.CREATEDBY ,C.SEARCHKEY,IFNULL(G.TAXAMOUNT,0.00) "
                +"FROM CUSTOMERS C,GUESTLOG G,GUESTCAT GC WHERE G.MEMNO=C.ID AND G.GUESTCAT=GC.ID AND C.SEARCHKEY=? AND G.DATE >= ? AND G.DATE < ? ORDER BY G.DATE,C.NAME"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP})
              ,new SerializerReadClass( GuestlistTableModel.MembersGuestLine.class )).list(new Object[]{searchkey,sdate,edate});
         if(dlist==null)
         {
             d.guestlist=new ArrayList<MembersGuestLine>();
         }
         else
        {
            d.guestlist=dlist;
        }



     return d;

  }
   public static GuestlistTableModel loadInstanceForOneGuestCatOneMem(AppView app,Date sdate,Date edate ,String GuestCategory,String searchkey) throws BasicException{
      GuestlistTableModel d = new GuestlistTableModel();

         List dlist = new StaticSentence(app.getSession()
                ,"SELECT G.DATE,C.NAME,GC.NAME,G.NUM,G.AMOUNT,G.NAMES,G.RECEIPTNO,G.CREATEDBY ,C.SEARCHKEY, IFNULL(G.TAXAMOUNT,0.00)"
                +"FROM CUSTOMERS C,GUESTLOG G,GUESTCAT GC WHERE G.MEMNO=C.ID AND G.GUESTCAT=GC.ID AND C.SEARCHKEY=? AND G.DATE >= ? AND G.DATE < ?  AND GC.NAME=?  ORDER BY G.DATE,C.NAME"
              ,new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP , Datas.STRING  })
              ,new SerializerReadClass( GuestlistTableModel.MembersGuestLine.class )).list(new Object[]{searchkey,sdate,edate,GuestCategory});
         if(dlist==null)
         {
             d.guestlist=new ArrayList<MembersGuestLine>();
         }
         else
        {
            d.guestlist=dlist;
        }
     return d;

  }
   //ended by pratima
  public int GetSize(){
      return guestlist.size();
  }

  public List<MembersGuestLine> getGuestList()
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
                MembersGuestLine l = guestlist.get(row);

                switch (column) {

                case 0: return Formats.TIMESTAMP.formatValue(l.getdate());
                case 1: return l.getmname();
                case 2: return l.getguestcat();
                case 3: return l.getNum();
                case 4: return decimalFormat.format(l.getamount());
                case 5: return decimalFormat.format(l.getTaxAmount());
                case 6: return decimalFormat.format(l.getamount()+l.getTaxAmount());
                case 7: return l.getnames();
                case 8: return l.getReceiptNo();
                case 9: return l.getcrby();
                    

                default: return null;
                }
            }
        };
    }


public static class MembersGuestLine implements SerializableRead{
    private String names;
    private String guestcat;
    private String mname;
    private String receiptno;
    private Timestamp date;
    private int num;
    private Double amount;
    private String createdby;
    private String MemberNo;
    private Double TaxAmount;
    
    public void readValues(DataRead dr) throws BasicException
    {
        date=dr.getTimestamp(1);
        mname=dr.getString(2);
        guestcat=dr.getString(3);
        num=dr.getInt(4);
        amount=dr.getDouble(5);
        names=dr.getString(6);
        receiptno=dr.getString(7);
        createdby=dr.getString(8);
        MemberNo=dr.getString(9);
        TaxAmount = dr.getDouble(10);
   }

    public Timestamp getdate() {
     return date;
    }
   public String getnames(){
     return names;
   }
    public String getcrby(){
        return createdby;
    }
    public int getNum(){
      return num;
    }
     public String getguestcat(){
      return guestcat;
    }
    public String getmname(){
       return mname;
    }
    public Double getamount(){
       return amount;
    }
    public String getReceiptNo(){
      return receiptno;
    }
    public String getMemberNo(){
      return MemberNo;
    }
    
    public Double getTaxAmount(){
        return TaxAmount;
    }

 }
}
