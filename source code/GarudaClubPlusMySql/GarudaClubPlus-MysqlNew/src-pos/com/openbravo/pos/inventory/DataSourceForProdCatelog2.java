

package com.openbravo.pos.inventory;


import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import com.openbravo.pos.inventory.ProductCatelog2TableModel;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForProdCatelog2 implements JRDataSource{
    
     private int m_nIdx;
    private List<ProductCatelog2TableModel.ProdCatelogInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
     public DataSourceForProdCatelog2(List<ProductCatelog2TableModel.ProdCatelogInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
     
     
     
      public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         ProductCatelog2TableModel.ProdCatelogInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
         
          if(sName.equals("WAREHOUSE"))
                o = curr.getWAREHOUSE();
          
         else if (sName.equals("CATEGORY"))  
                o = curr.getCATEGORY();
         
          else if (sName.equals("UNITTYPE"))  
                o = curr.getUNITTYPE();
          
         else if (sName.equals("QUANTITY"))  
                o = curr.getQUANTITY();
          
        else if (sName.equals("PRODUCT"))  
                o = curr.getPRODUCT();
           
          
         else if (sName.equals("PRate"))  
                o = curr.getPURCRATE();  
          
           else if (sName.equals("PVALUE"))  {
               Double d = (curr.getQUANTITY()*curr.getPURCRATE());
                o = d;
           }
                
           else if (sName.equals("SRATE"))  
                o = curr.getSRATE();  
          
           else if (sName.equals("SVALUE"))  
           {
               Double d = (curr.getQUANTITY()*curr.getSRATE());
                o = d;
           }
          
           
          
          return o;
        
    }
     
    
    
}
