/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dev3
 */
public class DataSourceForNonAMCReport implements JRDataSource{
     private int m_nIdx;
    private List<ListOfAMCnNonAMCReportTableModel.NonamcInfo> v;
    
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
    public DataSourceForNonAMCReport(List<ListOfAMCnNonAMCReportTableModel.NonamcInfo> v) {
                 m_nIdx = -1;
		this.v = v;
       
        
    }
    

   

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        ListOfAMCnNonAMCReportTableModel.NonamcInfo curr = v.get(m_nIdx);

        if (curr == null) 
            return null;
        
         else if (sName.equals("Sno")) 
              o = m_nIdx+1;
         
        if (sName.equals("Vendor")) 
               
            o = curr.getvendor();
        
        else if (sName.equals("ContactPer")) 

            o = curr.getcontact_per();
        else if (sName.equals("Asset")) 

            o = curr.getasset();

        
         else if (sName.equals("ContactDet")) 

            o = curr.getcontact_det();

         else if (sName.equals("REMARK")) 
                
            o = curr.getRemark();
                
            

        
         

        return o;

    }

    
}
