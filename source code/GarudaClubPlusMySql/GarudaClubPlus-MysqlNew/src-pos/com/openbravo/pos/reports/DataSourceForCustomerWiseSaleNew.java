

package com.openbravo.pos.reports;



import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import com.openbravo.pos.reports.CuetomerwiseSalesTableModel;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForCustomerWiseSaleNew implements JRDataSource{
    
    private int m_nIdx;
    private List<CuetomerwiseSalesTableModel.CustSalesInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
    
     public DataSourceForCustomerWiseSaleNew(List<CuetomerwiseSalesTableModel.CustSalesInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
     
     
     
     
      public Object getFieldValue(JRField jrf) throws JRException {
          
         Object o = null;
       
         String sName = jrf.getName();
       
         CuetomerwiseSalesTableModel.CustSalesInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
        
             if(sName.equals("MEMNO"))
                o = curr.getMEMNO();
          
            else if (sName.equals("MEMNAME"))  
              o = curr.getMEMNAME();
         
            else if (sName.equals("AMOUNT"))  
              o = curr.getAMOUNT();   
         
            else if (sName.equals("WHOUSE"))  
              o = curr.getWAREHOUSE();  
         
             
             else if (sName.equals("CATEGORY"))  
              o = curr.getWAREHOUSE();  
         
             else if (sName.equals("TAXAMOUNT"))  
              o = curr.getTAXTOTAL();  
            
             
        return o;
        
    }
     
    
    
}
