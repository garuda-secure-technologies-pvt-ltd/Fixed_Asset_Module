/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;





import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CloseSaleProdSource implements JRDataSource{
 private int m_nIdx;
    private List<CloseSaleTableModel.saleList> v;
    //private List<CloseSaleTableModel.saleList> v1;

    public CloseSaleProdSource() {
        this(new ArrayList<CloseSaleTableModel.saleList>());
    }

    public CloseSaleProdSource(List<CloseSaleTableModel.saleList> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();
    CloseSaleTableModel.saleList curr = v.get(m_nIdx);
     //   ProdList curr = v.get(m_nIdx);



        if (curr == null) {
            return null;
        }
//
        if (sName.equals("billno")) {
            o = curr.getBillId();
        } else if (sName.equals("amt")) {
            o = curr.getAmount();
        } else if (sName.equals("date")) {
            o = curr.getCreateddate();
        } else if (sName.equals("memno")) {
            o = curr.getCustomer();
        }else if (sName.equals("memname")) {
            o = curr.getCname();
        }
//        else if (sName.equals("role")) {
//        o = curr.getRolename();
//        }
//    else if (sName.equals("ProductName")) {
//            o = curr.getProdid();
//        } else if (sName.equals("Qty")) {
//            o = curr.getQty();
//        } else if (sName.equals("Rate")) {
//            o = curr.getRate();
//        }

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}
