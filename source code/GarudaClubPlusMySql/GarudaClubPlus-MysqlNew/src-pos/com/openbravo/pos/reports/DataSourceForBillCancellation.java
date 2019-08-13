/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.reports;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dev1
 */
public class DataSourceForBillCancellation implements JRDataSource{
    
    
      private List<BillQTCancellationModel.GetSetMethod2> v2;
      private int m_nIdx2;
          
    @Override
    public boolean next() throws JRException {
        
        m_nIdx2++;
        return (m_nIdx2 < v2.size());
    }

   
      public DataSourceForBillCancellation(List<BillQTCancellationModel.GetSetMethod2> v2) {
		m_nIdx2 = -1;
		this.v2 = v2;
     }  

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
       Object o = null;

        String sName = jrf.getName();

        BillQTCancellationModel.GetSetMethod2 curr = v2.get(m_nIdx2);


        if (curr == null) {
            return null;
        }

        if (sName.equals("billid")) {
            o = curr.getBillid();
        } else if (sName.equals("customername")) {
            o = curr.getCustomername();
        } else if (sName.equals("userid")) {
            o = curr.getUserid();
        } else if (sName.equals("product")) {
            o = curr.getProduct();
        } else if (sName.equals("qty")) {
            o = curr.getQty();
        } else if (sName.equals("rate")) {
            o = curr.getRate();
        } else if (sName.equals("reason")) {
            o = curr.getReason();
        } else if (sName.equals("authorised")) {
            o = curr.getAuthorised();
        } else if (sName.equals("crdate")) {
            o = curr.getCrdate();
        
        }
        
        return o;   
    
    
    }
      
    
}
