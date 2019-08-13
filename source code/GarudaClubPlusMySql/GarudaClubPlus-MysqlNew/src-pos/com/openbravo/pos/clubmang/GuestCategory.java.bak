/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class GuestCategory implements SerializableRead, IKeyed {
    private String id;
    private String name;
    private int days;
    private int maxguest;
    private Double rate;
    private Boolean echeck;
    private String receiptseq;
    private int maxreceiptseq;
    private String createdby;
    private Date crdate;
    private String deactby;
    private Date deactdate;
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        name=dr.getString(2);
        days=dr.getInt(3);
        maxguest=dr.getInt(4);
        rate=dr.getDouble(5);
        echeck=dr.getBoolean(6);
        receiptseq=dr.getString(7);
        maxreceiptseq=dr.getInt(8);
        createdby=dr.getString(9);
        crdate=dr.getTimestamp(10);
        deactby=dr.getString(11);
        deactdate=dr.getTimestamp(12);
     }
    public String getCreatedBy(){
        return createdby;
    }
    public String getDeactivatedBy(){
        return deactby;
    }
    public Date getCrdate(){
     return crdate;
    }
    public Date getDeactDate(){
     return deactdate;
    }
    public String getReceiptSeq(){
      return receiptseq;
     }
    public int getMaxReceiptSeq(){
      return maxreceiptseq;
    }

    public Boolean getecheck(){
         return echeck;
    }
     public String toString(){
        return name;
    }

     public String getname(){
        return name;
    }

    public Object getKey() {
        return id;
    }
    public int getdays(){
      return days;
    }
    public int getmaxguest(){
       return maxguest;
    }
    public Double getrate(){
        return rate;
    }
     public String getid() {
        return id;
    }
}
