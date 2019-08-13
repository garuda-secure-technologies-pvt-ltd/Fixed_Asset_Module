/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.ProductInfo;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class BillReversalTableModel {
   private List<discountline> dis;
    private final static String[] DISCOUNTHEADERS = {"label.Discountid","label.Discustomer","label.Disname","label.Disproduct","label.disqty","label.disrate","label.disamt","label.disreason","label.disauthorised","label.Date"};
  private BillReversalTableModel()
   {
   }

  public static BillReversalTableModel emptyinstance()
  {
      BillReversalTableModel d=new BillReversalTableModel();
      d.dis=new ArrayList<discountline>();
      return d;
  }
  public static BillReversalTableModel loadInstance(AppView app) throws BasicException {
      BillReversalTableModel d = new BillReversalTableModel();
     List dlist = new StaticSentence(app.getSession()
                ,"SELECT R.BILLID,C.NAME,R.CREATEDBY,P.NAME,R.QTY,R.RATE,R.REASON,R.AUTHORISED,P.ID,C.ID,R.ID,R.CRDATE,R.TAXTOTAL "
                +"FROM REVERSEDBILL R,PRODUCTS P,CUSTOMERS C WHERE R.CUSTOMER LIKE CONCAT(C.ID,'%') AND P.ID=R.PRODUCT AND R.AUTHORISED IS NULL  ORDER BY R.BILLID "
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( BillReversalTableModel.discountline.class )).list();//modified--c.id+'%'
     if(dlist==null)
     {
         d.dis=new ArrayList<discountline>();
     }
     else
     {
         d.dis=dlist;
     }

     return d;

  }
  public List<discountline> getdiscountline()
     {
         return dis;
     }
  public AbstractTableModel getdiscountTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(DISCOUNTHEADERS[column]);
            }
            public int getRowCount() {
                return dis.size();
            }
            public int getColumnCount() {

                return DISCOUNTHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                discountline l = dis.get(row);

                switch (column) {

                 case 0 : return l.getid();
                case 1: return l.getcustomername();
                case 2: return l.getuserid();
                case 3: return l.getproductname();
                case 4: return l.getquantity();
                case 5 : return l.getrate();
                case 6 : return l.getquantity()*l.getrate();
                case 7: return l.getreason();
                case 8: return l.getauthorised();
                case 9: return l.getcrdate();
                case 10: return l.getproductid();
                case 11:return l.getrid();
                case 12:return l.getTaxTotal();
                default: return null;
                }
            }
        };
    }


public static class discountline implements SerializableRead{
    private String customername;
    private String userid;
    private String product;
    private int qty;
    private Double rate;
    private String pid;
    //private Double amount;
    private String reason;
    private String authorised;
    private String id;
    private String cid;
    private String rid;
    private Timestamp crdate;
    private Double TaxTotal;
    public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(1);
        customername=dr.getString(2);
        userid=dr.getString(3);
        product=dr.getString(4);
        qty=dr.getInt(5);
        rate=dr.getDouble(6);
        //amount = dr.getDouble(7);
        reason=dr.getString(7);
        authorised=dr.getString(8);
        pid=dr.getString(9);
        cid=dr.getString(10);
        rid=dr.getString(11);
        crdate=dr.getTimestamp(12);
        TaxTotal = dr.getDouble(13);
    }
    public Timestamp getcrdate(){
      return crdate;
    }
    public String getrid(){
        return rid;
    }
    public String getid() {
     return id;
    }
    public String getproductid(){
       return pid;
    }
    public String getcustomername()
    {
        return customername;
    }
    public String getuserid()
    {
        return userid;
    }
    public String getproductname()
    {
         /* ProductInfo pInfo = LookupUtilityImpl.getInstance(null).getProductsMap().get(productid);
        return pInfo.getName();*/
        return product;
    }
    public String getcid(){
      return cid;
    }
    public int getquantity()
    {
        return qty;
    }
    public Double getrate()
    {
        return rate;
    }
  /* public Double getamount()
    {
           
        return amount;
    }*/
    public String getreason()
    {
        return reason;
    }
    public String getauthorised()
    {
        return authorised;
    }
     public String printcustomername()
    {
        return customername;
    }
    public String printuserid()
    {
        return userid;
    }
    public String printproductid()
    {
        return product;
    }
    public String printquantity()
    {
        return Formats.INT.formatValue(qty);
    }
    public String printrate()
    {
        return Formats.CURRENCY.formatValue(rate);
    }
    /*public String printamount()
      {
     
        return Formats.CURRENCY.formatValue(amount);
    }*/
    public String printreason()
    {
        return reason;
    }
    public String printauthorised()
    {
        return authorised;
    }
    
    public Double getTaxTotal(){
        return TaxTotal;
    }
    
    
    

}
}
