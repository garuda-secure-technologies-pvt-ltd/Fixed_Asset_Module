/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.reports;
import java.util.List;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
/**
 *
 * @author user
 */
public class DataSourceReceiptMember implements JRDataSource{
     private int m_nIdx;
    private List<ReceiptDetailModel.ReceiptMember> v;
   
    public DataSourceReceiptMember() {
        this(new ArrayList<ReceiptDetailModel.ReceiptMember>());
    }

    public DataSourceReceiptMember(List<ReceiptDetailModel.ReceiptMember> v) {
        m_nIdx = -1;
        this.v = v;
    }
    public Object getFieldValue(JRField field) throws JRException {
         Object o = null;

        String recpt = field.getName();
        ReceiptDetailModel.ReceiptMember curr = v.get(m_nIdx);
        if (curr == null) {
            return null;
        }
        if (recpt.equals("crdate")) {
            o = curr.getRdate();
        } else if (recpt.equals("mname")) {
             o = curr.getCname();
        } else if (recpt.equals("mid")) {
             o = curr.getCkey();
        }else if (recpt.equals("fname")) {
             o = curr.getFname();
        } else if (recpt.equals("amount")) {
             o = curr.getAmount();
        }else if (recpt.equals("total")) {
             o = curr.getTotal();
        }else if (recpt.equals("narration")) {
             o = curr.getNarration();
        }else if (recpt.equals("paymentmode")) {
             o = curr.getPaymentmode();
        }else if (recpt.equals("createdby")){
            o = curr.getCreatedby();
        }else if (recpt.equals("counter")){
            o = curr.getCounter();
        }else if (recpt.equals("role")) {
            o = curr.getRole();
        } 
         return o;
     }
    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }
}
