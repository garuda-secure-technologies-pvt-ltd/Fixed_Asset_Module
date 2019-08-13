/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.pos.reports.RecieptDetailsTableModel.RecieptDetailaBean;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author user
 */
public class DataSourceForRecieptDetails implements JRDataSource{

     private int m_nIdx;
	private List<RecieptDetailsTableModel.RecieptDetailaBean> v;
    
    
    
    DataSourceForRecieptDetails(List<RecieptDetailsTableModel.RecieptDetailaBean> rdblist) {
        m_nIdx = -1;
	v = rdblist;
    }

    public boolean next() throws JRException {
        m_nIdx++;
	return (m_nIdx < v.size());
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;
        String sName = field.getName();
        RecieptDetailsTableModel.RecieptDetailaBean  rdb;
        rdb=v.get(m_nIdx); 
         if(rdb==null)
            return null;       
         else if(sName.equals("facilityname"))
            o= rdb.getFacilityName();       
          else if(sName.equals("billedDate"))
            o= rdb.getBilledDate();  
          else if(sName.equals("billno"))
            o= rdb.getBillno();  
          else if(sName.equals("orgAmt"))
            o= rdb.getOriginalAmt();  
          else if(sName.equals("adjAmt"))
            o= rdb.getAdjustedAmt();  
                
        return o;
    }
    
    
    
    
}
