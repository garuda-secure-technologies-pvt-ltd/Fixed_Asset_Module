/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.PurchaseJournalReport;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRReport;

/**
 *
 * @author swathi
 */
public class DataSourceForPurchaseDetail implements JRDataSource {

    public boolean next() throws JRException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getFieldValue(JRField arg0) throws JRException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*private int m_nIdx;
    // private List<PurchaseJournalReport.PurchaseJournalDetail> v;
    private String prevTid = "";
    private boolean flag;

    public DataSourceForPurchaseDetail() {
    //this(new ArrayList<PurchaseJournalReport.PurchaseJournalDetail>());
    prevTid = "";
    flag = false;

    }

    public DataSourceForPurchaseDetail(List<PurchaseJournalReport.PurchaseJournalDetail> v) {
    m_nIdx = -1;
    this.v = v;
    }

    public boolean next() throws JRException {
    m_nIdx++;
    return (m_nIdx < v.size());
    }

    public Object getFieldValue(JRField field) throws JRException {
    Object o = null;

    String sName = field.getName();

    PurchaseJournalReport.PurchaseJournalDetail curr = v.get(m_nIdx);

    if (curr == null) {
    return null;
    }
    if (sName.equals("transtype")) {
    o = curr.getTranstype();
    } else if (sName.equals("transid")) {
    o = curr.getTransid();

    } else if (sName.equals("accname")) {
    o = curr.getAccname();

    } else if (sName.equals("accamount")) {
    o = curr.getAccamount();

    }
    return o;
    }*/

}
