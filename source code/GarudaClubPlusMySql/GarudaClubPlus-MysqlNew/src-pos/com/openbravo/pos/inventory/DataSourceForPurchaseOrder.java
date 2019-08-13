/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

/**
 *
 * @author swathi
 */
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForPurchaseOrder implements JRDataSource {

    private int m_nIdx;
    private List<PurchaseOrderModel.PurchaseOrderLinePrint> v;

    public DataSourceForPurchaseOrder() {
        this(new ArrayList<PurchaseOrderModel.PurchaseOrderLinePrint>());
    }

    public DataSourceForPurchaseOrder(List<PurchaseOrderModel.PurchaseOrderLinePrint> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        PurchaseOrderModel.PurchaseOrderLinePrint curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("product")) {
            o = curr.getPrdt();
        } else if (sName.equals("qty")) {
            o = curr.getQty();
        } else if (sName.equals("rate")) {
            o = curr.getRate();
        } else if (sName.equals("total")) {
            o = curr.getTotal();
        } else if (sName.equals("slno")) {
            o = curr.getSlNo();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }
}
