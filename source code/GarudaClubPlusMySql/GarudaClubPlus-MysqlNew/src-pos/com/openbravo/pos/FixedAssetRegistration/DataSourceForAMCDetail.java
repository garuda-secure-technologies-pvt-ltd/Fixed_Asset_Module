/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.format.Formats;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dev3
 */
public class DataSourceForAMCDetail implements JRDataSource{
     private int m_nIdx;
    private List<ListOfAMCTable_model.AMCInfo> v;
   DecimalFormat decimalFormat = new DecimalFormat("#0.00");
 DecimalFormat df = new DecimalFormat("#.00%");
    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
    public DataSourceForAMCDetail(List<ListOfAMCTable_model.AMCInfo> v) {
       m_nIdx = -1;
		this.v = v;
       
        
    }

   

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        ListOfAMCTable_model.AMCInfo curr = v.get(m_nIdx);

        if (curr == null) 
            return null;
        
         else if (sName.equals("Sno")) 
              o = m_nIdx+1;
         
        if (sName.equals("CONTRACTOR")) 
               
            o = curr.getCONTRACTOR();
        
        else if (sName.equals("PARTICULAR")) 

            o = curr.getPARTICULAR();

        
         else if (sName.equals("PERIOD")) 

            o = curr.getPERIOD();

         else if (sName.equals("REMARK")) 
                
            o = curr.getREMARK();
                
            

         else if (sName.equals("APPROVED_BY")) 
            
            o = curr.getAPPROVED_BY();
            

         else if (sName.equals("START_DATE")) 

            o = Formats.TIMESTAMP.formatValue(curr.getSTART_DATE());

         else if (sName.equals("END_DATE")) 
              
            o = Formats.TIMESTAMP.formatValue(curr.getEND_DATE());
                
            

         else if (sName.equals("REMINDER_DATE")) 

            o = Formats.TIMESTAMP.formatValue(curr.getREMINDER_DATE());

         else if (sName.equals("APPROVED_DATE")) 

            o = Formats.TIMESTAMP.formatValue(curr.getAPPROVED_DATE());

         else if (sName.equals("RATE")) {
             String x=decimalFormat.format(curr.getRATE());
            o = x+"%";}

          else if (sName.equals("ACCOUNT")) 
            
            o = curr.getACCOUNT();
            

        else if (sName.equals("AMOUNT")) 
                
            o = decimalFormat.format(curr.getAMOUNT());
        else if (sName.equals("DOC_LINK")) 
                
            o = curr.getDOC_LINK();
         else if (sName.equals("ASSET")) 
                
            o = curr.getASSET();
         

        return o;

    }
    
    
}
