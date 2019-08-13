

package com.openbravo.pos.sales;


import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import com.openbravo.pos.panels.ConsumableBillReprintTableModel;

public class DatasourceForConsumeBillReprint implements JRDataSource{
    
    private int m_nIdx;
    private List<ConsumableBillReprintTableModel.BillInfo> v;
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
    
    DatasourceForConsumeBillReprint(List<ConsumableBillReprintTableModel.BillInfo> list) {
        m_nIdx = -1;
	v = list;
    }
     
     
       public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         ConsumableBillReprintTableModel.BillInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
          if(sName.equals("ProdName"))
                o = curr.getProdName();
          
           else if (sName.equals("SRNO"))  
              o = m_nIdx+1;
          
          
          else if (sName.equals("qty"))  
              o = curr.getQty();
         
          else if (sName.equals("Rate"))  
              o = curr.getAmount();
          
          
          
           else if (sName.equals("Amount"))  {
             o = (curr.getQty()*curr.getAmount()); 
           }
          
          else if (sName.equals("DeptName"))  
              o = ("Department :  "+curr.getDepartmentName());
          
          
           else if (sName.equals("CreatedBy"))  
              o = ("Created By : "+curr.getCreatedBy());
          
          else if (sName.equals("CreatedDate"))  
              o = curr.getCreatedDate();
           else if (sName.equals("BillNo"))  
              o = curr.getBillID();
          
          
          
         return o;
        
    }
     
     
     
     
    
}
