package com.openbravo.pos.clubmang;

import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;
//praveen:added to print credit list
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForCreditList implements JRDataSource {

    private int m_nIdx;
    private List<MemDebtBillingTableModel.Creditline> v;

    public DataSourceForCreditList() {
        this(new ArrayList<MemDebtBillingTableModel.Creditline>());
    }

    public DataSourceForCreditList(List<MemDebtBillingTableModel.Creditline> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        MemDebtBillingTableModel.Creditline curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }


        if (sName.equals("transno")) {
            o = curr.getTransno();
        } else if (sName.equals("transref")) {
            o = curr.getTransref();
        } else if (sName.equals("date")) {
            o = curr.getDate();
        } else if (sName.equals("amount")) {
            o = curr.getamount();
        } else if (sName.equals("narration")) {
            o = curr.getNarration();
        } else if (sName.equals("amt")) {
            o = curr.getAmt();
        }



        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }
}