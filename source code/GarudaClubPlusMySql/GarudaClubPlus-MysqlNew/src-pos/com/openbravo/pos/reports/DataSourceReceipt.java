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
public class DataSourceReceipt implements JRDataSource{

    
    private int m_nIdx;
    private List<ReceiptDetailModel.ReceiptDetailModelLine> v;

    public DataSourceReceipt() {
        this(new ArrayList<ReceiptDetailModel.ReceiptDetailModelLine>());
    }

    public DataSourceReceipt(List<ReceiptDetailModel.ReceiptDetailModelLine> v) {
        m_nIdx = -1;
        this.v = v;
    }

   
    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String recpt = field.getName();

        ReceiptDetailModel.ReceiptDetailModelLine curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (recpt.equals("crdate")) {
            o = curr.getRdate();
        } else if (recpt.equals("membername")) {
            o = curr.getCname();
        } else if (recpt.equals("membernor")) {
            o = curr.getCkey();
        } else if (recpt.equals("billnor")) {
            o = curr.getBid();
        } else if (recpt.equals("servby")) {
            o = curr.getWname();
        } else if (recpt.equals("subtotal")){
            o = curr.getBamount();
        } else if (recpt.equals("taxes")){
            o = curr.getTax();
        } else if (recpt.equals("total")){
            o = curr.getTotal();
        } else if (recpt.equals("paymentmode")){
            o = curr.getPayment();
        
//        } else if (recpt.equals("qty")){
//            o = curr.getQty();
     } else if (recpt.equals("rate")){
            o = curr.getBamount();
        } else if (recpt.equals("perticular")){
            o = curr.getWname();
        } else if (recpt.equals("createdby")){
            o = curr.getCreatedby();
       } else if (recpt.equals("role")){
         o = curr.getRole();
       }
            
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}
