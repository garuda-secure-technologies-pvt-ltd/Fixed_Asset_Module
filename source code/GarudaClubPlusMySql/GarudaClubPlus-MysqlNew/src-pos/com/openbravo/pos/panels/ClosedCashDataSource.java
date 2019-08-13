
package com.openbravo.pos.panels;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class ClosedCashDataSource implements JRDataSource{
    private int m_nIdx;
    private List<CloseCashTableModel.ClosedCashLine> v;

    public ClosedCashDataSource() {
        this(new ArrayList<CloseCashTableModel.ClosedCashLine>());
    }

    public ClosedCashDataSource(List<CloseCashTableModel.ClosedCashLine> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        CloseCashTableModel.ClosedCashLine curr=v.get(m_nIdx);


        if (curr == null) {
            return null;
        }


        if (sName.equals("seq")) {
            o = curr.getHostsequence();
        } else if (sName.equals("sdate")) {
            o = curr.getDateStart();
        } else if (sName.equals("edate")) {
            o = curr.getDateEnd();
        } else if (sName.equals("user")) {
            o = curr.getUserName();
        } else if (sName.equals("host")) {
            o = curr.getHost();
        }

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}
