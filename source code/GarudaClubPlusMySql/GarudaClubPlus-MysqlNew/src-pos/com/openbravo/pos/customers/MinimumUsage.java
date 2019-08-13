/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import java.util.Date;

/**
 *
 * @author user
 */
public class MinimumUsage implements SerializableRead, SerializableWrite {

    String mid;
    String mname;
    Date lbilldate;
    Date edate;
    String newMinUsageRef;
    Date effectiveFrom;

    public Date getEffectiveFrom() {
        return effectiveFrom;
    }
   
    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getNewMinUsageRef() {
        return newMinUsageRef;
    }

    public void setNewMinUsageRef(String newMinUsageRef) {
        this.newMinUsageRef = newMinUsageRef;
    }



    public Date getLbilldate() {
        return lbilldate;
    }

    public void setLbilldate(Date lbilldate) {
        this.lbilldate = lbilldate;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void readValues(DataRead dr) throws BasicException {
        mid = dr.getString(1);
        mname = dr.getString(2);
        lbilldate=dr.getTimestamp(3);
        edate=dr.getTimestamp(4);
        newMinUsageRef=dr.getString(5);
        effectiveFrom = dr.getTimestamp(6);
    }

    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, mid);
        dp.setString(2, mname);
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof MinimumUsage)) {
            return false;
        }

        MinimumUsage minfo = (MinimumUsage) obj;
        return (this.mid).equals(minfo.mid) ? true : false;


    }

    @Override
    public int hashCode() {
        return mid.hashCode();
    }

}
