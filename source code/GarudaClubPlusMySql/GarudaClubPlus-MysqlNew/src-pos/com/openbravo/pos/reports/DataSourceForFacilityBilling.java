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
 * @author garuda
 */
public class DataSourceForFacilityBilling implements JRDataSource{
    private int m_nIdx;
     private List<OrderdFacilityBillReport.FacilityBillInfo> v;

    DataSourceForFacilityBilling(ArrayList<OrderdFacilityBillReport.FacilityBillInfo> facility_list) {
               m_nIdx = -1;
	       this.v = facility_list;
       
        
    }

    @Override
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object o=null;
        
        String sName = jrf.getName();
       
        OrderdFacilityBillReport.FacilityBillInfo curr = v.get(m_nIdx);
       
        if (curr== null)
	   return null;
        
        if (sName.equals("BillDate"))
             o = curr.getBill_Date();
        else if (sName.equals("MemNo"))
             o = curr. getSearchkey();
        else if (sName.equals("BillNo"))
             o = curr.getTrans_NO();
        else if (sName.equals("Amount"))
             o = curr.getAmount();
        else if (sName.equals("BalanceAmount"))
             o = curr.getBal_amount();
        else if (sName.equals("ClrDate"))
             o = curr.getClr_Date();
        else if (sName.equals("Narration"))
             o = curr.getNarration();
        else if (sName.equals("PaymentRef"))
             o = curr.getPay_ref();
        
        return o;
        
        
               
    
    }
    
}
