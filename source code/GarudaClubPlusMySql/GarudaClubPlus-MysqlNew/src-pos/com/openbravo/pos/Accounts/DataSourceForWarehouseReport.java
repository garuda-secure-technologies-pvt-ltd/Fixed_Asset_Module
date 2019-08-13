/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author user
 */
public class DataSourceForWarehouseReport implements JRDataSource{
    
    private int m_nIdx;
    private List<WarehouseWiseReportModel.StockDetailsline> v;
    private boolean flag;

    public DataSourceForWarehouseReport() {
        
        this (new ArrayList<WarehouseWiseReportModel.StockDetailsline>(),true);
    }

    public DataSourceForWarehouseReport(List<WarehouseWiseReportModel.StockDetailsline> v, boolean flag) {
        this.m_nIdx = -1;
        this.v = v;
        this.flag = flag;
    }

    public boolean next() throws JRException {
        
                m_nIdx++;
		return (m_nIdx < v.size());
           
    }

    public Object getFieldValue(JRField field) throws JRException {
      
        Object o = null;
        
        String sName=field.getName();
        WarehouseWiseReportModel.StockDetailsline curr=v.get(m_nIdx);
        curr.setRate(50.00);
        
        
        
        if(curr==null)
            return null;
        if (sName.equals("Total"))
			o = curr.getTotal();
        else if (sName.equals("Rate"))
			o = curr.getRate();
        else if (sName.equals("Amount"))
			o = curr.getAmount();
        
      
        return o;
        
    }
    
}
