

package com.openbravo.pos.inventory;

import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.IKeyed;

/**
 *
 * @author  adrianromero
 * @version 
 */
public class TaxCustCategoryInfo implements SerializableRead, SerializableWrite, IKeyed {

    private String m_sID;
    private String m_sName;
    
    /** Creates new CategoryInfo */
    public TaxCustCategoryInfo() {
        m_sID = null;
        m_sName = null;
    }
    
    public Object getKey() {
        return m_sID;
    }
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
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
   
    @Override
    public String toString(){
        return m_sName;
    }
}
