/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author user
 */
public class DataSourceReceiptGuest implements JRDataSource {

    private int m_nIdx;
    private List<ReceiptDetailModel.ReceiptGuest> v1;

    public DataSourceReceiptGuest() {
        this(new ArrayList<ReceiptDetailModel.ReceiptGuest>());
    }

    public DataSourceReceiptGuest(List<ReceiptDetailModel.ReceiptGuest> v1) {
        m_nIdx = -1;
        this.v1 = v1;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String recpt = field.getName();

        ReceiptDetailModel.ReceiptGuest curr = v1.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (recpt.equals("crdate")) {
            o = curr.getRdate();
        } else if (recpt.equals("membername")) {
            o = curr.getCname();
        } else if (recpt.equals("membernor")) {
            o = curr.getCkey();
        } else if (recpt.equals("total")) {
            o = curr.getTotal();
        } else if (recpt.equals("paymentmode")) {
            o = curr.getPayment();
        } else if (recpt.equals("qty")) {
            o = curr.getQty();
        } else if (recpt.equals("rate")) {
            o = curr.getBamount();
        } else if (recpt.equals("perticular")) {
            o = curr.getWname();
        }else if (recpt.equals("createdby")) {
            o = curr.getCreatedby();
        }else if (recpt.equals("role")) {
            o = curr.getRole();
        }else if (recpt.equals("gname")) {
            o = curr.getGname();
        }else if (recpt.equals("TaxAmount")) {
            o = curr.getTaxAmount();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v1.size());
    }
}
