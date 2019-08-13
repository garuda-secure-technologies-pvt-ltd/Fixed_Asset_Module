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
 * @author swathi
 */
public class DataSourceForStocktransfer implements JRDataSource{

    private int m_nIdx;
    private List<WaiterWiseTotalModel.StockTransferInfo> v;

    public DataSourceForStocktransfer() {
        this(new ArrayList<WaiterWiseTotalModel.StockTransferInfo>());
    }

    public DataSourceForStocktransfer(List<WaiterWiseTotalModel.StockTransferInfo> v) {
        m_nIdx = -1;
        this.v = v;
    }


    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        WaiterWiseTotalModel.StockTransferInfo curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("DATENEW")) {
            o = curr.getDatenew();
        } else if (sName.equals("TNO")) {
            o = curr.getTno();
        } else if (sName.equals("PRODUCT")) {
            o = curr.getProduct();
        } else if (sName.equals("LOCATION")) {
            o = curr.getLocation();
        } else if (sName.equals("QTY")) {
            o = curr.getQty();
        } else if (sName.equals("SNAME")) {
            o = curr.getSname();
        } else if (sName.equals("PRODUCT1")) {
            o = curr.getProduct1();
        } else if (sName.equals("LOCATION1")) {
            o = curr.getLocation1();
        } else if (sName.equals("QTY1")) {
            o = curr.getQty1();
        } else if (sName.equals("RNAME")) {
            o = curr.getRname();
        }
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }


}
