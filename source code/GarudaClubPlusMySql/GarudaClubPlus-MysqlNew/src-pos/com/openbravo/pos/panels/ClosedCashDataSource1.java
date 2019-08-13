package com.openbravo.pos.panels;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class ClosedCashDataSource1 implements JRDataSource{
    private int m_nIdx;
    private List<CloseCashTableModel.CashList> v;

    public ClosedCashDataSource1() {
        this(new ArrayList<CloseCashTableModel.CashList>());
    }

    public ClosedCashDataSource1(List<CloseCashTableModel.CashList> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        CloseCashTableModel.CashList curr=v.get(m_nIdx);


        if (curr == null) {
            return "";
        }


        if (sName.equals("user1")) {
            o = curr.getRuser();
        } else if (sName.equals("date")) {
            o = curr.getDatenew();
        } else if (sName.equals("rno")) {
            o = curr.getRid();
        } else if (sName.equals("descr")) {
            o = curr.getDescription();
        } else if (sName.equals("amount")) {
            o = curr.getAmount();
        }

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}