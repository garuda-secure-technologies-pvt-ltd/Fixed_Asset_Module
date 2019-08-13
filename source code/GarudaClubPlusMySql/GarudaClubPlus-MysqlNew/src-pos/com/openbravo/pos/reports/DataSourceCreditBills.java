/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.reports;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSourceCreditBills implements JRDataSource{

    private int m_nIdx;
    private List<WaiterWiseTotalModel.Creditbill> v;

    public DataSourceCreditBills() {
        this(new ArrayList<WaiterWiseTotalModel.Creditbill>());
    }

    public DataSourceCreditBills(List<WaiterWiseTotalModel.Creditbill> v) {
        m_nIdx = -1;
        this.v = v;
    }

   
    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        WaiterWiseTotalModel.Creditbill curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("Name")) {
            o = curr.getName();
        } else if (sName.equals("MemNo")) {
            o = curr.getMemno();
        } else if (sName.equals("Date")) {
            o = curr.getDate();
        } else if (sName.equals("Amount")) {
            o = curr.getBillno();
        } else if (sName.equals("BillId")) {
            o = curr.getBillid();
        } else if (sName.equals("lname")) {
            o = curr.getWarehouse();
        } else if (sName.equals("waiter")) {
            o = curr.getWaiter();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}
