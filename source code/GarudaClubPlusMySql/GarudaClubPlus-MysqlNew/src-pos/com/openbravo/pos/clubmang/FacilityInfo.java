/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class FacilityInfo implements SerializableRead, IKeyed {

    private String m_sID;
    private String m_sName;
    private String smsform;

    /** Creates a new instance of RoleInfo */
    public FacilityInfo() {
        m_sID = null;
        m_sName = null;
        smsform = null;
    }

    public Object getKey() {
        return m_sID;
    }

    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        smsform = dr.getString(3);
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

    public void setName(String sValue) {
        m_sName = sValue;
    }
    public String getSMSForm(){
       return smsform;
    }

    public String toString() {
        return m_sName;
    }
}

