

package com.openbravo.pos.reports;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForCustomerProductReport implements JRDataSource{
    
    private int m_nIdx;
    private List<CustomerProductwiseTableModel.DetailBillInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
     public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
    
     public DataSourceForCustomerProductReport(List<CustomerProductwiseTableModel.DetailBillInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
     
      public Object getFieldValue(JRField jrf) throws JRException {
          
         Object o = null;
       
         String sName = jrf.getName();
       
         CustomerProductwiseTableModel.DetailBillInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
        
             if(sName.equals("MEMNO"))
                o = curr.getMemno();
          
            else if (sName.equals("MEMNAME"))  
              o = curr.getMemName();
         
            
            else if (sName.equals("SRNO"))  
              o = m_nIdx+1;
             
             
             else if (sName.equals("BILLNO"))  
              o = curr.getBillNo();
             
            else if (sName.equals("BILLDATE"))  
              o = curr.getCRDATE(); 
             
            else if (sName.equals("PRODUCT"))  
              o = curr.getProductName();  
             
            else if (sName.equals("QUANTITY"))  
              o = curr.getDMultiply();    
             
            else if (sName.equals("RATE"))  
              o = curr.getRate();    
             
             else if (sName.equals("TOTAL"))  
              o = curr.getTotal();  
             
             
              else if (sName.equals("REMARKS")) {
                  o = curr.getRemarks();  
                  if(curr.getDMultiply()<0){
                      o = "QT Discounted";
                  }
              } 
              
             
             
             
             
            // System.out.println("Attributes : - "+curr.getRemarks());
            // System.out.println("QT DATE  : - "+curr.getQTCRDATE());
             
        return o;
        
    }
     
     
    
}
