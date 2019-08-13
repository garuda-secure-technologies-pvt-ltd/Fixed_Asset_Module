/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.FloorsInfo;

/**
 *
 * @author swathi
 */
public class lastbillinfo implements SerializableRead, IKeyed {

    private String m_id;
    private FloorsInfo m_floor;
    private int m_lastbill;
    
    
    public lastbillinfo() {
        m_id = null;
        m_floor = null;
        m_lastbill = 0;
    }
    public void readValues(DataRead dr) throws BasicException {
        m_id = dr.getString(1);
        m_floor = LookupUtilityImpl.getInstance(null).getDataLogicSales().getFloorByID(dr.getString(2));
        m_lastbill = dr.getInt(3);
    }

    public Object getKey() {
        return m_id;
    }

    public void setfloor(FloorsInfo floor) {
        m_floor = floor;
    }

    public FloorsInfo getfloor() {
        return m_floor;
    }

    public String getfloorID() {
        return m_floor != null ? m_floor.getID() : null;
    }
    public void setID(String id) {
        m_id = id;
    }

    public String getID() {
        return m_id;
    }

    public void setlastbill(int lastbill) {
        m_lastbill = lastbill;
    }

    public int getlastbill() {
        return m_lastbill;
    }
}
