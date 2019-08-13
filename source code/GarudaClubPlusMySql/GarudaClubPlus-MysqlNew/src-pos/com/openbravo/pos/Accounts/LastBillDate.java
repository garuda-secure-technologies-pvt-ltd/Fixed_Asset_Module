/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.util.Date;

/**
 *
 * @author user
 */
public class LastBillDate implements SerializableRead{
        private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void readValues(DataRead dr) throws BasicException {
        date=dr.getTimestamp(1);
    }



}
