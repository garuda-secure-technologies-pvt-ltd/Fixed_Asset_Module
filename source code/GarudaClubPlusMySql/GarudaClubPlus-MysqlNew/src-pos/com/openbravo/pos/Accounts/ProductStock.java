/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class ProductStock implements SerializableRead,Comparable{
    private String pdtID;
    private String pdtName;
    private double qty;
    private double rate;
    private String pdtCat;
    public void readValues(DataRead dr) throws BasicException {
        pdtID=dr.getString(1);
        pdtName=dr.getString(2);
        qty=dr.getInt(5);
        rate=dr.getDouble(4);
        pdtCat=dr.getString(3);
    }

    public String getPtoductCategory(){
       return pdtCat;
    }

    public String getProductID(){
       return pdtID;
    }
    public String getProductName(){
       return pdtName;
    }
    public double getProductQty(){
       return qty;
    }
    public double getProductRate(){
       return rate;
    }

    public int compareTo(Object o) {
        if(o instanceof ProductStock){
            ProductStock t=(ProductStock) o;
            return this.getProductID().compareTo(t.getProductID());
        }else
            return 0;
    }
}
