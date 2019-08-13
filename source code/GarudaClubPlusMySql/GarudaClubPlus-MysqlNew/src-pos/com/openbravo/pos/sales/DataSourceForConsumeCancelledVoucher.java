

package com.openbravo.pos.sales;

import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForConsumeCancelledVoucher implements JRDataSource{
    
    private int m_nIdx;
    private List<ConsumeVoucherCancellationReportTableModel.BillInfo> v;
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
     DataSourceForConsumeCancelledVoucher(List<ConsumeVoucherCancellationReportTableModel.BillInfo> list) {
        m_nIdx = -1;
	v = list;
    }
     
     
     public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         ConsumeVoucherCancellationReportTableModel.BillInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
          if(sName.equals("BillNo"))
                o = curr.getBillID();
          
           else if (sName.equals("CreatedBy"))  
              o = curr.getCreatedBy();
          
           else if (sName.equals("Department"))  
              o = curr.getDepartment();
         
           else if (sName.equals("CreatedDate"))  
              o = curr.getCreatedDate();
           else if (sName.equals("CancelledBy"))  
              o = curr.getCancelledBy();
          
           else if (sName.equals("CancelledDate"))  
              o = curr.getCancelledDate();
          
           else if (sName.equals("Amount"))  
              o = curr.getAmount();
          
          
         return o;
        
    }
     
    
}
