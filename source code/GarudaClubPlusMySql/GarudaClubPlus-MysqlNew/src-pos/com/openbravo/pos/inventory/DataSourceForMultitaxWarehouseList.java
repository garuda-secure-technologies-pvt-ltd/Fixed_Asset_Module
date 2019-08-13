/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.inventory;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dev1
 */
public class DataSourceForMultitaxWarehouseList  implements JRDataSource{
    
    private List<MultitaxWarehousewiseList.WarehouseGetSetMethod> v;
   
     private int m_nIdx;

    DataSourceForMultitaxWarehouseList(List<MultitaxWarehousewiseList.WarehouseGetSetMethod> v) {
      
              m_nIdx = -1;
		this.v = v;
    
    }

   

    @Override
    public boolean next() throws JRException {
        
         
         m_nIdx++;
        return (m_nIdx < v.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
             Object o = null;
             String sName = jrf.getName();
           MultitaxWarehousewiseList. WarehouseGetSetMethod curr = v.get(m_nIdx);
            
            if (curr== null)
	       return null;
            
              /*else if (sName.equals("Slno"))  
              o = curr.getsno();*/
     
           else if (sName.equals("ProductName"))  
              o = curr.getProductName();
         
         if(sName.equals("Reference"))
                o = curr. getReference();
          
         else if (sName.equals("Tax1"))  
              o = curr.getTax1();
         
          else if (sName.equals("Tax2"))  
              o = curr. getTax2();
         
          else if (sName.equals("Tax3"))  
              o = curr.getTax3();
         else if (sName.equals("SellingPrice"))  
              o = curr.getSellingPrice();
         
          else if (sName.equals("Total"))  
              o = curr.getTotal();
         
          else if (sName.equals("CatName"))  
              o = curr.getCatName();
       
    return o;
    }
    
    
}
