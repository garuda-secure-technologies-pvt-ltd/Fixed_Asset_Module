
package com.openbravo.pos.panels;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class ClosedSaleDataSource implements JRDataSource{
    private int m_nIdx;
    private List<CloseSaleTableModel.ClosedSaleLine> v;

    public ClosedSaleDataSource() {
        this(new ArrayList<CloseSaleTableModel.ClosedSaleLine>());
    }

    public ClosedSaleDataSource(List<CloseSaleTableModel.ClosedSaleLine> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        CloseSaleTableModel.ClosedSaleLine curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }


        if (sName.equals("seq")) {
            o = curr.getSequence();
        } else if (sName.equals("sdate")) {
            o = curr.getDateStart();
        } else if (sName.equals("edate")) {
            o = curr.getDateEnd();
        } else if (sName.equals("amount")) {
            o = curr.getAmount();
        } else if (sName.equals("role")) {
            o = curr.getRoleName();
        } 

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}
