
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author adrianromero
 * Created on February 13, 2007, 10:13 AM
 *
 */
public class LocationInfo implements SerializableRead, IKeyed {

    private String m_sID;
    private String m_sName;
    private String m_sAddress;
    private String rdname;
    private String parent;
    private boolean stockStatus;
    private String saleAccount;
    private String customercurrentAccount;
    private String levyAccount;
    private double levyValue;
    private String productPrefix;
    
    /** Creates a new instance of LocationInfo */
    public LocationInfo() {
        m_sID = null;
        m_sName = null;
        m_sAddress = null;
        rdname=null;
        parent=null;
        stockStatus=false;
    }
    
    public Object getKey() {
        return m_sID;
    }
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        m_sAddress = dr.getString(3);
        rdname=dr.getString(4);
        parent=dr.getString(5);
        stockStatus=dr.getBoolean(6);
    } 
    
    public void setID(String sID) {
        m_sID = sID;
    }
    
    public String getID() {
        return m_sID;
    }

    public boolean getStockStatus(){
      return stockStatus;
    }

    public String getName() {
        return m_sName;
    }
    
    public void setName(String sName) {
        m_sName = sName;
    }  

    public String getAddress() {
        return m_sAddress;
    }
    
    public void setDisplayName(String rdname) {
        this.rdname=rdname;
    }

    public String getDisplayName() {
        return rdname;
    }

    public void setAddress(String sAddress) {
        m_sAddress = sAddress;
    }
    public String getParentID(){
      return parent;
    }
    public String getProductPrefix() {
        return productPrefix;
    }

    public void setProductPrefix(String productPrefix) {
        this.productPrefix = productPrefix;
    }
    public String toString(){
        return m_sName;
    }    
}
