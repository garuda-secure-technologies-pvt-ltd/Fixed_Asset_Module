

package com.openbravo.pos.reports;

import com.openbravo.pos.printer.ticket.*;
import com.openbravo.pos.reports.BillandQtReportsTableModel;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForBotReports implements JRDataSource {
    
    private int m_nIdx;
    private List<BillandQtReportsTableModel.BOTDetails> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
    
    
     public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
     public DataSourceForBotReports(List<BillandQtReportsTableModel.BOTDetails> v) {
		m_nIdx = -1;
		this.v = v;
     } 
    
     
      public Object getFieldValue(JRField jrf) throws JRException {
      
         Object o=null;
         String sName = jrf.getName();
         
          BillandQtReportsTableModel.BOTDetails curr = v.get(m_nIdx);
       
          if (curr== null)
	      return null;
       
          
               if(sName.equals("QTNO"))
                                o = curr.getQTID();

                else if (sName.equals("CRDATE"))
                                o = curr.getCRDATE();
        
                else if(sName.equals("ProdName"))
                                o = curr.getPRODUCT();

                else if (sName.equals("RATE"))
                               o = curr.getRATE();
                
             
                
                else if (sName.equals("qty"))
                               o = curr.getQTY();
                
                
                 else if (sName.equals("TOTAL"))
                               o = (curr.getQTY()*curr.getRATE());
                
                 else if (sName.equals("billref")){
                     if(curr.getBILLREF() != null){
                         o=curr.getBILLREF();
                     }
                     else{
                         o="Pending";
                     }
                 }
                              
         
                  else if (sName.equals("crby"))
                                o = curr.getCreatedBY();
               
               
       return o;
    }
     
     
     
     
}
