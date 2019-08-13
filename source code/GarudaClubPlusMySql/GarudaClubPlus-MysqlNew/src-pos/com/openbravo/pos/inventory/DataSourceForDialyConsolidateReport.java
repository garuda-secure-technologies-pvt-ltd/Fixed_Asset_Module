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


public class DataSourceForDialyConsolidateReport implements JRDataSource {
    private int m_nIdx;
    private List<MultipleWarehouseModel.consolidate> v;

    public DataSourceForDialyConsolidateReport() {
        this(new ArrayList<MultipleWarehouseModel.consolidate>());
    }

    public DataSourceForDialyConsolidateReport(List<MultipleWarehouseModel.consolidate> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        MultipleWarehouseModel.consolidate curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("product")) {
            o = curr.getProductName();
        } else if (sName.equals("qty")) {
            o = curr.getQty();
        } else if (sName.equals("category")) {
            o = curr.getCategoryName();
        }  
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}
