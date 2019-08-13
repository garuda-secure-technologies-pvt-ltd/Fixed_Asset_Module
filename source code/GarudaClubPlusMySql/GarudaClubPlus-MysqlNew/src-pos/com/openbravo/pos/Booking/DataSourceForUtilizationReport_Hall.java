
package com.openbravo.pos.Booking;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForUtilizationReport_Hall implements JRDataSource{

     private int m_nIdx;
    private List<UtilizationReportTableModel.HallStatusInfo> v;
    
    public boolean next() throws JRException {
        m_nIdx++;
       return (m_nIdx < v.size());
    }

       public DataSourceForUtilizationReport_Hall(List<UtilizationReportTableModel.HallStatusInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
    
    public Object getFieldValue(JRField jrf) throws JRException {
       Object o = null;
       
       String sName = jrf.getName();
       
       UtilizationReportTableModel.HallStatusInfo curr = v.get(m_nIdx);
       
        if (curr== null)
	   return null;
        
          
               
               
        
                else if(sName.equals("DATA3"))
                                o = curr.gethall_name() + " = "+curr.getStatus() ;
                                
               
                else if (sName.equals("DATA1"))
                                 o = curr.gethall_name();
        
                else if(sName.equals("DATA2"))
                {
                    Double d =  curr.getStatus()*1.00;
                    o = d;
                    
                }
                                 
       
       
       return o;
    }
    
}
