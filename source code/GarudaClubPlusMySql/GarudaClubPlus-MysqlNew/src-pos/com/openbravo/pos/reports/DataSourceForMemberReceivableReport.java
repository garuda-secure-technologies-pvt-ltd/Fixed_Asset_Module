

package com.openbravo.pos.reports;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForMemberReceivableReport implements JRDataSource{
    
    private int m_nIdx;
    private List<MemberReceivableReportTableModel.MemberInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
    
    
     public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
     public DataSourceForMemberReceivableReport(List<MemberReceivableReportTableModel.MemberInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
     
      public Object getFieldValue(JRField jrf) throws JRException {
      
         Object o=null;
         String sName = jrf.getName();
         
          MemberReceivableReportTableModel.MemberInfo curr = v.get(m_nIdx);
       
          if (curr== null)
	      return null;
       
          
               if(sName.equals("MEMNO"))
                                o = curr.getMemno();

                else if (sName.equals("MEMNAME"))
                                o = curr.getName();
        
                else if(sName.equals("BILLDATE"))
                                o = curr.getDATE();

                else if (sName.equals("FACILITY"))
                               o = curr.getFacility();
                
             
                
                else if (sName.equals("TRANSNO"))
                               o = curr.getTransno();
                
                
                 else if (sName.equals("TOTAL"))
                               o = curr.getAmount();
                
                 else if (sName.equals("CLEARDATE"))
                     o=curr.getCleardate();
                     
                 else if (sName.equals("PAYMENTREF"))
                                o = curr.getPaymentref();
               
               
       return o;
    }
     
     
    
    
}
