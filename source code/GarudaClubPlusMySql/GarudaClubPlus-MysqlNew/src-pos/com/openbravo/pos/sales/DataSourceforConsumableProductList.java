/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceforConsumableProductList implements JRDataSource{
    
    
    private int m_nIdx;
    private List<ConsumableBillReprintTableModel.ProductListInfo> v;
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
    DataSourceforConsumableProductList(List<ConsumableBillReprintTableModel.ProductListInfo> list) {
        m_nIdx = -1;
	v = list;
    }
    
    
    
    
    public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         ConsumableBillReprintTableModel.ProductListInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
          if(sName.equals("ProdName"))
                o = curr.getProdName();
          
           else if (sName.equals("SRNO"))  
              o = (m_nIdx+1)+"";
          
          
          else if (sName.equals("qty"))  
              o = curr.getQty()+"";
         
          else if (sName.equals("Rate"))  
              o = curr.getRate()+"";
          
          
          
           else if (sName.equals("Amount"))  {
             o = (curr.getQty()*curr.getRate()); 
           }
          
         
         
          
         
          
          // heading Part 
          
          
           else if (sName.equals("SeqNo"))  
              o = "Sr No";
           else if (sName.equals("Heading1"))  
              o = "Product Name";
          else if (sName.equals("Heading2"))  
              o = "Qty";
           else if (sName.equals("Heading3"))  
              o = "Rate";
          else if (sName.equals("AmountTitle"))  
              o = "Amount";
         
          
          
          
         return o;
        
    }
    
}
