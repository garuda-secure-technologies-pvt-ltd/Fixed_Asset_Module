

package com.openbravo.pos.inventory;

import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.IKeyed;
import java.io.Serializable;

/**
 *
 * @author  adrianromero
 * @version 
 */
public class TaxCategoryInfo implements SerializableRead, SerializableWrite, Serializable, IKeyed {

    private String m_sID;
    private String m_sName;
    private String account;
    
    /** Creates new CategoryInfo */
    public TaxCategoryInfo() {
        m_sID = null;
        m_sName = null;
        account=null;
    }
    
    public TaxCategoryInfo(String sID, String sName) {
        m_sID = sID;
        m_sName = sName;      
    }
    
    public Object getKey() {
        return m_sID;
    }
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        account = dr.getString(3);
    }
    
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, m_sID);
        dp.setString(2, m_sName);     
    }
    
    public void setID(String sID) {
        m_sID = sID;
    }
    
    public String getID() {
        return m_sID;
    }

    public String getName() {
        return m_sName;
    }
    
    public void setName(String sName) {
        m_sName = sName;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }


   
    @Override
    public String toString(){
        return m_sName;
    }
}