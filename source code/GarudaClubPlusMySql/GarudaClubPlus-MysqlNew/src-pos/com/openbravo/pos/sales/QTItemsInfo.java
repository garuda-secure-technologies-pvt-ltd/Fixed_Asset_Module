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
import java.util.Date;
/**
 *
 * @author swathi
 */
public class QTItemsInfo {
    private String ID;
    private String ParentID;
    private String Product;
    private int multiply;
    private double rate;

    public void QTItemsInfo() {

    }

    public void readValues(DataRead dr) throws BasicException {
        ID = dr.getString(1);
        ParentID = dr.getString(2);
        Product = dr.getString(3);
        multiply = dr.getInt(4);
        rate = dr.getDouble(5);

    }

    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, ID);
        dp.setString(2, ParentID);
        dp.setString(3, Product);
        dp.setInt(4, multiply);
        dp.setDouble(5, rate);

    }

    public String getID() {
        return ID;
    }

    public void setID(String value) {
        ID = value;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String value) {
        ParentID = value;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String value) {
        Product = value;
    }

    public int getMultiply() {
        return multiply;
    }

    public void setMultiply(int value) {
        multiply = value;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double value) {
        rate = value;
    }

}
