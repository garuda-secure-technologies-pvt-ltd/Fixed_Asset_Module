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
public class DataSourceReceiptGeneral implements JRDataSource{

    
    private int m_nIdx;
    private List<ReceiptDetailModel.ReceiptGeneral> v;

    public DataSourceReceiptGeneral() {
        this(new ArrayList<ReceiptDetailModel.ReceiptGeneral>());
    }

    public DataSourceReceiptGeneral(List<ReceiptDetailModel.ReceiptGeneral> v) {
        m_nIdx = -1;
        this.v = v;
    }

   
    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String recpt = field.getName();

        ReceiptDetailModel.ReceiptGeneral curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (recpt.equals("amount")) {
            o = curr.getAmount();
        } else if (recpt.equals("perticular")){
            o = curr.getPerticular();
        } 
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}
