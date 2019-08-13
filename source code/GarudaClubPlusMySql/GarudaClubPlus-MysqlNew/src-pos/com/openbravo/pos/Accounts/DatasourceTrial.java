/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.pos.Booking.HallBillModel;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author User
 */
public class DatasourceTrial implements JRDataSource {

    private int m_nIdx;
    private List<TrialBalanceNew.MemberRecList> v;
    
    public DatasourceTrial(List<TrialBalanceNew.MemberRecList> v){
        m_nIdx = -1;
	this.v = v;
        
    }
    
    
    
    public boolean next() throws JRException {
        
        m_nIdx++;
       return (m_nIdx < v.size());
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        String sName = jrf.getName();
        Object o = null;
          
       // Object[] curr=(Object[]) v.get(m_nIdx);
        TrialBalanceNew.MemberRecList curr=v.get(m_nIdx);
        
        if (curr== null)
          return null;
        
        else if(sName.equals("Name"))
            o=curr.getName();
        
        else if(sName.equals("Amount"))
            o=(Double)curr.getAmount();
        
        
        return o;
    }
    
}
