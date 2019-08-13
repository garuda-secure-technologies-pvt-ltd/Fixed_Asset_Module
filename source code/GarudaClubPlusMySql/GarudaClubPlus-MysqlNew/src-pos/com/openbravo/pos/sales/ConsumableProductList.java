/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import java.io.Serializable;

/**
 *
 * @author user
 */
public class ConsumableProductList implements SerializableWrite, SerializableRead,Serializable{
     private String productId;
     private String productName;
     private int qty;

    public ConsumableProductList(String productId, String pName, int qty) {
        this.productId = productId;
        this.productName = pName;
        this.qty = qty;
    }

    public void readValues(DataRead dr) throws BasicException {
        productId=dr.getString(1);
        productName=dr.getString(2);
        qty=dr.getInt(3);

    }



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    


    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, productId);
        dp.setString(2, productName);
        dp.setInt(3, new Integer(qty));
    }

}