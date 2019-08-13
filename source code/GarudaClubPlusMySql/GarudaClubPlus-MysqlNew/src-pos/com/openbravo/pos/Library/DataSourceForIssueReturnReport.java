/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import com.openbravo.pos.Library.IssueReturnReportTableModel;
/**
 *
 * @author dev3
 */
public class DataSourceForIssueReturnReport implements JRDataSource{
     private int m_nIdx;
    private List<IssueReturnReportTableModel.IssueReturnBook> v;
      DecimalFormat decimalformat=new DecimalFormat("#0.00##");
      
       public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
       
       public  DataSourceForIssueReturnReport(List<IssueReturnReportTableModel.IssueReturnBook> v){
                m_nIdx = -1;
		this.v = v;
       
       }
       
       
       public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        IssueReturnReportTableModel.IssueReturnBook curr = v.get(m_nIdx);

        if (curr == null) {
            return null;
        }
        if (sName.equals("slno")) {
               
            o = m_nIdx+1;

        } else if (sName.equals("cname")) {

            o = curr.getCname();

        } else if (sName.equals("memid")) {
                
            o = curr.getMemid();
                
            

        } else if (sName.equals("bname")) {
            
            o = curr.getBname();
            

        } else if (sName.equals("issue_date")) {

            o = curr.getIssueDate();

        } else if (sName.equals("to_be_retn_dt")) {
              
            o = curr.getTo_be_rtn_date();
                
            

        } else if (sName.equals("return_date")) {

            o = curr.getReturn_Date();

        } else if (sName.equals("due_charge")) {

            o = curr.getDueCharge();

        } else if (sName.equals("d_type")) {

            o = curr.getD_type();

        }  else if (sName.equals("nor_days")) {
            
            o = curr.getNor_days();
            

        }else if (sName.equals("nor_fine")) {
                
            o = curr.getNor_fine();
               
        }

        return o;

    }
}
