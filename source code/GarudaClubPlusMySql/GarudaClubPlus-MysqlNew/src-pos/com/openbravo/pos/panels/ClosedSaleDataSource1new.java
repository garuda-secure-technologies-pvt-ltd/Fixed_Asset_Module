
package com.openbravo.pos.panels;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class ClosedSaleDataSource1new implements JRDataSource{

    private int m_nIdx;
    private List<CloseSaleTableModel.ProdList> v;
    //private List<CloseSaleTableModel.ProdList> v1;

    public ClosedSaleDataSource1new() {
          this(new ArrayList<CloseSaleTableModel.ProdList>());
       // ArrayList<CloseSaleTableModel.ProdList> p1= new ArrayList<CloseSaleTableModel.ProdList>();
        
    }

    public ClosedSaleDataSource1new(List<CloseSaleTableModel.ProdList> v){
            //,List<CloseSaleTableModel.ProdList> v1) {
        m_nIdx = -1;
        this.v = v;
        //this.v1 = v1;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        CloseSaleTableModel.ProdList curr = v.get(m_nIdx);
       // CloseSaleTableModel.ProdList curr1 = v1.get(m_nIdx);

        if (curr == null) {
            return null;
        }


//        if (sName.equals("billno")) {
//            o = curr.getBillId();
//        } else if (sName.equals("amt")) {
//            o = curr.getAmount();
//        } else if (sName.equals("date")) {
//            o = curr.getCreateddate();
//        } else if (sName.equals("memno")) {
//            o = curr.getCustomer();
//        }
//        else if (sName.equals("memname")) {
//            o = curr.getCname();
//        }
//        else if (sName.equals("role")) {
//            o = curr.getRolename();
//        }
        else if (sName.equals("ProductName")){
            o = curr.getProdid();
        }else if (sName.equals("Qty")){
            o = curr.getQty();
        }else if (sName.equals("Rate")){
            o = curr.getRate();
        }

        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}
