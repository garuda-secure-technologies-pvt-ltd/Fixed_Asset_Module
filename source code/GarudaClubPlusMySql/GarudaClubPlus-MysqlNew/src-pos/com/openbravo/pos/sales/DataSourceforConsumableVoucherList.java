
package com.openbravo.pos.sales;

import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceforConsumableVoucherList implements JRDataSource{
    
    
    private int m_nIdx;
    private List<ConsumableBillReprintTableModel.VoucherListInfo> v;
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
    
    DataSourceforConsumableVoucherList(List<ConsumableBillReprintTableModel.VoucherListInfo> list) {
        m_nIdx = -1;
	v = list;
    }
    
    
      public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         ConsumableBillReprintTableModel.VoucherListInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
          if(sName.equals("ProdName"))
                o = curr.getBillID();
          
           else if (sName.equals("SRNO"))  
              o = (m_nIdx+1)+"";
          
          
          else if (sName.equals("qty"))  
              o = curr.getDepartMent();
         
          else if (sName.equals("Rate"))  
              o = curr.getCreatedDate();
          
          
          
           else if (sName.equals("Amount"))  {
             o = curr.getAmount(); 
           }
          
         
         
          
         
          
          // heading Part 
          
          
           else if (sName.equals("SeqNo"))  
              o = "Sr No";
           else if (sName.equals("Heading1"))  
              o = "Voucher No";
          else if (sName.equals("Heading2"))  
              o = "Department";
           else if (sName.equals("Heading3"))  
              o = "Created Date";
          else if (sName.equals("AmountTitle"))  
              o = "Amount";
         
          
          
          
         return o;
        
    }
    
    
    
    
}
