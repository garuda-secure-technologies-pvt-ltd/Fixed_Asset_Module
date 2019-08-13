/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.inventory;

/**
 *
 * @author swathi
 */
/*public class PdtUnitInfo {

}*/

import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.IKeyed;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


/**
 *
 * @author  Adrian
 * @version
 */
public class PdtUnitInfo implements SerializableRead, SerializableWrite,Externalizable, IKeyed {

    private String m_sID;
    private String m_sName;
   


    /** Creates new CategoryInfo */
    public PdtUnitInfo() {
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

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(m_sID);
        out.writeObject(m_sName);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        m_sID = (String) in.readObject();
        m_sName = (String) in.readObject();
    }

   
    public String toString(){
        return m_sName;
    }
}

