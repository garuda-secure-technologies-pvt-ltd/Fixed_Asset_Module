
package com.openbravo.pos.Accounts;

import com.openbravo.pos.Accounts.AuditTrail.Detail;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class AuditTrialDataSource implements JRDataSource{
    private int m_nIdx;
    private List<Detail> v;

    public AuditTrialDataSource() {
        this(new ArrayList<Detail>());
    }



    AuditTrialDataSource(List<Detail> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        Detail curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }


        if (sName.equals("account")) {
            o = curr.getAccount();
        } else if (sName.equals("amount")) {
            o = curr.getAmount();
        } else if (sName.equals("transtype")) {
            o = curr.getTranstype();
        } 

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}