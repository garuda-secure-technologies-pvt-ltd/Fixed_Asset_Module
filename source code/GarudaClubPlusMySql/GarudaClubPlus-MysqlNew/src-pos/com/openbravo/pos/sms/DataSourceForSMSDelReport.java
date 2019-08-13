/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sms;

import com.openbravo.pos.sms.SmsConnection.DelRepBean;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author user
 */
public class DataSourceForSMSDelReport implements JRDataSource{

    
     private int m_nIdx;
	private List<SmsConnection.DelRepBean> v;

    DataSourceForSMSDelReport(List<DelRepBean> list) {
        m_nIdx = -1;
	v = list;
    }
        
    public boolean next() throws JRException {
        m_nIdx++;
	return (m_nIdx < v.size());
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;
        String sName = field.getName();
        SmsConnection.DelRepBean delrep;
        delrep=v.get(m_nIdx);
        if(delrep==null)
            return null;
        
        else if(sName.equals("memname"))
            o=delrep.getMemName();
        
        else if(sName.equals("memno"))
            o=delrep.getMemno();
        else if(sName.equals("phoneNo"))
            o=delrep.getPhoneNo();
        
        else if(sName.equals("DelReport"))
            o=delrep.getDeliveryReport();
        else if(sName.equals("schdID"))
            o=delrep.getMessage();
       
         else if(sName.equals("SENDDATE"))
            o=delrep.getSentDate();
       
         else if(sName.equals("SRNO"))
            o= (m_nIdx+1)+".";
        
        
        return o;
    }
    
}
