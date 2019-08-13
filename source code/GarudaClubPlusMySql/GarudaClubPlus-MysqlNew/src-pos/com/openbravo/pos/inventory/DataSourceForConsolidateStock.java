

package com.openbravo.pos.inventory;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSourceForConsolidateStock implements JRDataSource{

    private int m_nIdx;
    private List<ConsolidateStockModel.ConsolidateStockReport> v;

    public DataSourceForConsolidateStock() {
        this(new ArrayList<ConsolidateStockModel.ConsolidateStockReport>());
    }

    public DataSourceForConsolidateStock(List<ConsolidateStockModel.ConsolidateStockReport> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        ConsolidateStockModel.ConsolidateStockReport curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("pname")) {
            o = curr.getPname();
        } else if (sName.equals("intotal")) {
            o = curr.getInn();
        } else if (sName.equals("outtotal")) {
            o = curr.getOutt();
        } else if (sName.equals("currentvalue")) {
            o = curr.getCurrent();
        } else if (sName.equals("catname")) {
            o = curr.getCatName();
        } else if (sName.equals("units")) {
            o = curr.getUnits();
        }
        else if (sName.equals("opbal")) {
            o = curr.getOpBal();
        }
        
        
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}

