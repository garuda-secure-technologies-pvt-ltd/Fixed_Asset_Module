/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class OfferTableDetail {
    private List<Offerline> ol;
     private final static String[] OFFERHEADERS = {"Ref Code","Product","Buy Qty","Buy Rate","Get Qty","Get Rate","From","To","Active"};
    private OfferTableDetail()
   {
   }

  public static OfferTableDetail emptyinstance()
  {
      OfferTableDetail st=new OfferTableDetail();
      st.ol=new ArrayList<Offerline>();
      return st;
  }

   public static OfferTableDetail loadInstance(AppView app,String Orderby) throws BasicException {
    //   String ra="";

      OfferTableDetail st = new OfferTableDetail();
      Date d=new Date();
     String d1=Formats.TIMESTAMP.formatValue(d);
   //   Timestamp date=;
Timestamp o1=new Timestamp(d.getTime());
//String[] arr=o1.toString().split(" ");
     List dlist = new ArrayList();   
     if(Orderby.equals("RefCode")){
            dlist = new StaticSentence(app.getSession()
                       ,"SELECT P.NAME,BUYQTY,BUYRATE,GETQTY,GETRATE,FROMDATE,TODATE,ACTIVE,OFFER.ID,P.REFERENCE "
                       +"FROM OFFER,PRODUCTS P WHERE P.ID=OFFER.PRODUCT AND OFFER.TODATE > ? AND ACTIVE=TRUE ORDER BY P.REFERENCE,TODATE "

                       , SerializerWriteString.INSTANCE
                     ,new SerializerReadClass( OfferTableDetail.Offerline.class )).list(o1.toString());
     }
     if(Orderby.equals("ProductWise")){
         dlist = new StaticSentence(app.getSession()
                ,"SELECT P.NAME,BUYQTY,BUYRATE,GETQTY,GETRATE,FROMDATE,TODATE,ACTIVE,OFFER.ID,P.REFERENCE "
                +"FROM OFFER,PRODUCTS P WHERE P.ID=OFFER.PRODUCT AND OFFER.TODATE > ? AND ACTIVE=TRUE ORDER BY P.NAME,P.REFERENCE "

                , SerializerWriteString.INSTANCE
              ,new SerializerReadClass( OfferTableDetail.Offerline.class )).list(o1.toString());
     }
     if(Orderby.equals("DateWise")){
         dlist = new StaticSentence(app.getSession()
                ,"SELECT P.NAME,BUYQTY,BUYRATE,GETQTY,GETRATE,FROMDATE,TODATE,ACTIVE,OFFER.ID,P.REFERENCE "
                +"FROM OFFER,PRODUCTS P WHERE P.ID=OFFER.PRODUCT AND OFFER.TODATE > ? AND ACTIVE=TRUE ORDER BY FROMDATE,P.REFERENCE "

                , SerializerWriteString.INSTANCE
              ,new SerializerReadClass( OfferTableDetail.Offerline.class )).list(o1.toString());
     }
     
     
     
     
     if(dlist==null)
     {
         st.ol=new ArrayList<Offerline>();
     }
     else
     {
         st.ol=dlist;
     }

     return st;

  }
    public List<Offerline> getofferline()
     {
         return ol;
     }

     public AbstractTableModel getdiscountTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(OFFERHEADERS[column]);
                
                return OFFERHEADERS[column];
            }
            public int getRowCount() {
                return ol.size();
            }
            public int getColumnCount() {

                return OFFERHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Offerline l = ol.get(row);

                switch (column) {
                    case 0 : return l.getRefCode();
                    case 1 : return l.getproduct();
                    case 2: return l.getbuyqty();
                     case 3: return l.getbuyrate();
                    case 4 : return l.getGetqty();
                    case 5: return l.getGetrate();
                    case 6: return l.getStartdate();
                    case 7: return l.getEnddate();
                    case 8:return l.getactive();
                    case 9:return l.getoid();

                default: return null;
                }
            }
        };
    }




    public static class Offerline implements SerializableRead{
    private String product;

    private Double buyqty;

    private Double getqty;
    private Timestamp startdate;
    private Timestamp enddate;
    private Double buyrate;
    private Double getrate;
    private Boolean active;
    private String oid;
    private String refCode;

    public void readValues(DataRead dr) throws BasicException
    {

        product=dr.getString(1);
        buyqty=dr.getDouble(2);
         buyrate=dr.getDouble(3);
       // num=dr.getDouble(3);
        getqty=dr.getDouble(4);
         getrate=dr.getDouble(5);

        startdate=dr.getTimestamp(6);
        enddate=dr.getTimestamp(7);
       active=dr.getBoolean(8);
       oid=dr.getString(9);
       try
       {
           refCode = dr.getString(10);
       }
       catch(Exception e)
       {
           
       }

    }

    public String getoid(){
        return oid;
    }
    public Double getbuyrate()
    {
        return buyrate;
    }

    public Boolean getactive()
    {
        return active;
    }

    public Double getGetrate()
    {
        return getrate;
    }

    public String getproduct() {
     return product;
    }

    public Double getbuyqty()
    {
        return buyqty;
    }

    public Double getGetqty()
    {
        return getqty;
    }


    public Timestamp getStartdate()
    {
        return startdate;
    }
    public Timestamp getEnddate()
    {
        return enddate;
    }

        public String getRefCode() {
            return refCode;
        }

        public void setRefCode(String refCode) {
            this.refCode = refCode;
        }

    }



}
