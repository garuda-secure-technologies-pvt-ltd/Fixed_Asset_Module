/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author User
 */
public class DataSourceProductTotal implements JRDataSource {
    private int m_nIdx;
    private List<ProductWiseTotalModel.productTotalSales> v;

    public DataSourceProductTotal() {
        this(new ArrayList<ProductWiseTotalModel.productTotalSales>());
    }

    public DataSourceProductTotal(List<ProductWiseTotalModel.productTotalSales> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        ProductWiseTotalModel.productTotalSales curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("PRODUCT NAME")) {
            o = curr.getName();
        } else if (sName.equals("QUANTITY")) {
            o = curr.getDmultiply();
        } else if (sName.equals("RATE")) {
            o = curr.getRate();
        } else if (sName.equals("AMOUNT")) {
            o = curr.getAmount();
        } else if (sName.equals("TAX1")) {
            o = curr.getTax1();
        }
        else if (sName.equals("TAX2")) {
            o = curr.getTax2();
        }
        else if (sName.equals("TAX3")) {
            o = curr.getTax3();
        }
        else if (sName.equals("TOTAL")) {
            o = curr.getTotal();
        }
         else if (sName.equals("LOCNAME")) {
            o = curr.getLocname();
        }
        else if (sName.equals("WAREHOUSE")) {
            o = curr.getWarehouse();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

}
