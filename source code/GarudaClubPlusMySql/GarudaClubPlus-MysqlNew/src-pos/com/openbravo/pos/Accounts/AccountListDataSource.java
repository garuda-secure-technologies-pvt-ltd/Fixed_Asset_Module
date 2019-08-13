package com.openbravo.pos.Accounts;

import com.openbravo.pos.Accounts.AccountReports.AccountsList;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class AccountListDataSource implements JRDataSource {

    private int m_nIdx;
    private List<AccountsList> v;

    public AccountListDataSource() {
        this(new ArrayList<AccountsList>());
    }

    AccountListDataSource(List<AccountsList> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        AccountsList curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }


        if (sName.equals("account")) {
            o = curr.getAccountName();

        } else if (sName.equals("amount")) {
            o = curr.getAmt();

        } else if (sName.equals("transtype")) {
            o = curr.getTranstype();

        } else if (sName.equals("date")) {
            o = curr.getDate();

        } else if (sName.equals("createdby")) {
            o = curr.getCreatedBy();

        } else if (sName.equals("reference")) {
            o = curr.getTransref();

        }

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }
}
