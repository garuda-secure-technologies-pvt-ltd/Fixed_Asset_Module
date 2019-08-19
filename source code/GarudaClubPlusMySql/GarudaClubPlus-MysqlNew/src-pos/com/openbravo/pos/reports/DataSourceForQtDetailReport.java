/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForQtDetailReport implements JRDataSource{
    
    
    private int m_nIdx;
    private List<QTDetailsReportTableModel.QTInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
    public DataSourceForQtDetailReport(List<QTDetailsReportTableModel.QTInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
    
     public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         QTDetailsReportTableModel.QTInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
         
          if(sName.equals("SRNO"))
                o = m_nIdx+1;
          
         else if (sName.equals("QTNO"))  
              o = curr.getQTNo();
         
         else if (sName.equals("QTDATE"))  
              o = curr.getQTDate();
           
          else if (sName.equals("PRODUCT"))  
              o = curr.getPRODUCT();
          
          else if (sName.equals("QUANTITY"))  
              o = curr.getQTY();
          
          else if (sName.equals("PRate"))  
              o = curr.getRATE();
          
          
          else if (sName.equals("AMOUNT"))  
              o = curr.getAMOUNT();
          
          else if (sName.equals("BILLNO"))  
              o = curr.getBILLNO();
          
          else if (sName.equals("BILLDATE"))  
              o = curr.getBILLDATE();
          else if (sName.equals("REMARKS")){
              if(curr.getQTY()<0){
                  o="Bill/Qt Cancelled";;
              }
              else{
                  o=null;
              }
          }  
              
         
          
          
          
          
           return o;
        
    }
    
    
}
