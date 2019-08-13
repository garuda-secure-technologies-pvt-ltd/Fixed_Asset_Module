/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.inventory;

/**
 *
 * @author swathi
 */
import com.openbravo.format.Formats;
import com.openbravo.pos.inventory.CurrentStockValue.StockReport;
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForCurrentStockReport implements JRDataSource {
    private int m_nIdx;
    private List<StockReport> v;

    public DataSourceForCurrentStockReport() {
        this(new ArrayList<StockReport>());
    }

    public DataSourceForCurrentStockReport(List<StockReport> v) {
        m_nIdx = -1;
       this.v=v;
    }

    public Object getFieldValue(JRField field) throws JRException {
       Object o=null;

        String sName = field.getName();

        StockReport curr = v.get(m_nIdx);


        if (curr==null) {
         return null;
        }
      

        if (sName.equals("name")) {
            o = curr.getpName();
        } else if (sName.equals("total")) {
            o = Formats.DOUBLE.formatValue(curr.getQty());
        } else if (sName.equals("value")) {
            o = curr.getValue();
        } else {
            if(curr.getDependentCat().containsKey(String.valueOf(sName)))
                o = Formats.DOUBLE.formatValue(curr.getDependentCat().get(String.valueOf(sName)));
            else
                o = new String("0.0");
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}
