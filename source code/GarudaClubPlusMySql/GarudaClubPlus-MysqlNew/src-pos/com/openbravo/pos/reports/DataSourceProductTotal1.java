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
public class DataSourceProductTotal1 implements JRDataSource {
     private int m_nIdx;
    private List<ProductWiseTotalModel.productTotalSales1> v;

    public DataSourceProductTotal1() {
        this(new ArrayList<ProductWiseTotalModel.productTotalSales1>());
    }

    public DataSourceProductTotal1(List<ProductWiseTotalModel.productTotalSales1> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        ProductWiseTotalModel.productTotalSales1 curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }
 if (sName.equals("HSN_CODE")) {
            o = curr.getHsn_code();
        }
       else if (sName.equals("PRODUCT NAME")) {
            o = curr.getName1();
        } else if (sName.equals("QUANTITY")) {
            o = curr.getDmultiply1();
        } else if (sName.equals("RATE")) {
            o = curr.getRate1();
        } else if (sName.equals("AMOUNT")) {
            o = curr.getAmount1();
        } else if (sName.equals("TAX1")) {
            o = curr.getTax11();
        }
        else if (sName.equals("TAX2")) {
            o = curr.getTax21();
        }
        else if (sName.equals("TAX3")) {
            o = curr.getTax31();
        }
        else if (sName.equals("TOTAL")) {
            o = curr.getTotal1();
        }
         else if (sName.equals("LOCNAME")) {
            o = curr.getLocname1();
        }
        else if (sName.equals("WAREHOUSE")) {
            o = curr.getWarehouse1();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

    
}
