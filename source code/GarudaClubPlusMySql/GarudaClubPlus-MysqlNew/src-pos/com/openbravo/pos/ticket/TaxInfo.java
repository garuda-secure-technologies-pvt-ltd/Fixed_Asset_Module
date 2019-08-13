

package com.openbravo.pos.ticket;

import java.io.Serializable;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.IKeyed;

/**
 *
 * @author adrianromero
 */
public class TaxInfo implements SerializableRead, Serializable, IKeyed {

    private String id;
    private String name;
    private String taxcategoryid;
    private String taxcustcategoryid;
    private String parentid;
    
    private double rate;
    private boolean cascade;
    private Integer order;
    
    /** Creates new TaxInfo */
    public TaxInfo() {
        id = null;
        name = null;
        taxcategoryid = null;
        taxcustcategoryid = null;
        parentid = null;
        
        rate = 0.0;         
        cascade = false;
        order = null;
    }
    
    /** Creates new TaxInfo */
    public TaxInfo(String id, String name, String taxcategoryid, String taxcustcategoryid, String parentid, double rate, boolean cascade, Integer order) {
        this.id = id;
        this.name = name;
        this.taxcategoryid = taxcategoryid;
        this.taxcustcategoryid = taxcustcategoryid;
        this.parentid = parentid;
        
        this.rate = rate;
        this.cascade = cascade;
        this.order = order;
    }

    public TaxInfo(Double rate) {
        this.rate = rate;
    }
    
    public Object getKey() {
        return id;
    }
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        name = dr.getString(2);
        taxcategoryid = dr.getString(3);
        taxcustcategoryid = dr.getString(4);
        parentid = dr.getString(5);
        
        rate = dr.getDouble(6).doubleValue();
        cascade = dr.getBoolean(7).booleanValue();
        order = dr.getInt(8);
    }   
    
    public void setID(String value) {
        id = value;
    }
    
    public String getId() {
        
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String value) {
        name = value;
    }

    public String getTaxCategoryID() {
        return taxcategoryid;
    }
    
    public void setTaxCategoryID(String value) {
        taxcategoryid = value;
    }

    public String getTaxCustCategoryID() {
        return taxcustcategoryid;
    }
    
    public void setTaxCustCategoryID(String value) {
        taxcustcategoryid = value;
    }    

    public String getParentID() {
        return parentid;
    }
    
    public void setParentID(String value) {
        parentid = value;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    
    public boolean isCascade() {
        return cascade;
    }
    
    public void setCascade(boolean value) {
        cascade = value;
    }
    
    public Integer getOrder() {
        return order;
    }
    
    public Integer getApplicationOrder() {
        return order == null ? Integer.MAX_VALUE : order.intValue();
    }    
    
    public void setOrder(Integer value) {
        order = value;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
