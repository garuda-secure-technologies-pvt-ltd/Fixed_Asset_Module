/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dev1
 */
public class DataSourceForMemberList  implements JRDataSource{
    
    private List<MemberListReport.GetSetMethod> v;
     private int m_nIdx;

    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
     public DataSourceForMemberList(List<MemberListReport.GetSetMethod> v) {
		m_nIdx = -1;
		this.v = v;
     }
    
    

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
       
        Object o = null;
       
         String sName = jrf.getName();
       
         MemberListReport.GetSetMethod curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
          else if (sName.equals("sno"))  
              o = curr.getsno();
         
         if(sName.equals("Name"))
                o = curr.getCustomerName();
          
         else if (sName.equals("Address"))  
              o = curr.getAddress();
         
         else if (sName.equals("City"))  
              o = curr.getCITY();
         
         else if (sName.equals("Country"))  
              o = curr.getCOUNTRY();
         
          else if (sName.equals("PhoneNo"))  
              o = curr. getPHONE();
         
          else if (sName.equals("Member Type"))  
              o = curr.getMemberType();
          
         else if (sName.equals("MemNo"))  
              o = curr.getMemberNo();
         
          else if (sName.equals("PhoneNo1"))  
              o = curr.getPhone2();
          
         else if (sName.equals("PhoneNo2"))  
              o = curr.getMobile();
         
         return o;
        
    }
    
}
