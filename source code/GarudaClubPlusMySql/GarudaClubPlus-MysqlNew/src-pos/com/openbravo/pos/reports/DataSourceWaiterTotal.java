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
public class DataSourceWaiterTotal implements JRDataSource{

    private int m_nIdx;
    private List<WaiterWiseTotalModel.waiterTotalSales> v;

    public DataSourceWaiterTotal() {
        this(new ArrayList<WaiterWiseTotalModel.waiterTotalSales>());
    }

    public DataSourceWaiterTotal(List<WaiterWiseTotalModel.waiterTotalSales> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        WaiterWiseTotalModel.waiterTotalSales curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("NAME")) {
            o = curr.getName();
        } else if (sName.equals("DEBTAMOUNT")) {
            o = curr.getTotalCreditAmount();
        } else if (sName.equals("CASHAMOUNT")) {
            o = curr.getTotalCashAmount();
        } else if (sName.equals("AMOUNT")) {
            o = curr.getTotalAmount();
        } else if (sName.equals("LNAME")) {
            o = curr.getLocationName();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}
