
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author adrianromero
 * Created on 27 de febrero de 2007, 23:27
 *
 */
public class PeoplesRoleInfo implements SerializableRead, IKeyed {
    
    protected String m_sName;
    
    /** Creates a new instance of RoleInfo */
    public PeoplesRoleInfo() {
        m_sName = null;
    }
   
    public Object getKey() {
        return m_sName;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sName = dr.getString(1);
    }     
    
    public String getName() {
        return m_sName;
    }
    
    public void setName(String sValue) {
        m_sName = sValue;
    }    
    
    @Override
    public String toString() {
        return m_sName;
    }
}
