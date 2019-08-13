/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.admin;

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
public class PeopleInfo implements SerializableRead, IKeyed {

    private String m_sID;
    private String m_sName;
    private String role;

    /** Creates a new instance of RoleInfo */
    public PeopleInfo() {
        m_sID = null;
        m_sName = null;
        role=null;
    }

    public Object getKey() {
        return m_sID;
    }

    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        role=dr.getString(3);
    }

    public void setID(String sID) {
        m_sID = sID;
    }

    public String getID() {
        return m_sID;
    }
    public void setrole(String role1){
    role=role1;
    }
    public String getrole(){
    return role;
    }
    public String getName() {
        return m_sName;
    }

    public void setName(String sValue) {
        m_sName = sValue;
    }

    public String toString() {
        return m_sName;
    }
}

