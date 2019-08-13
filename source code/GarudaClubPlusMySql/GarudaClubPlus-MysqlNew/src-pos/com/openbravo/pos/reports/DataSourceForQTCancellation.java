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
public class DataSourceForQTCancellation  implements JRDataSource{

   
        
         private List<BillQTCancellationModel.GetSetMethod1> v1;
         private int m_nIdx1;
          
    @Override
    public boolean next() throws JRException {
        m_nIdx1++;
       return (m_nIdx1 < v1.size());
             
         
    }

   
      public DataSourceForQTCancellation(List<BillQTCancellationModel.GetSetMethod1> v1) {
		m_nIdx1 = -1;
		this.v1 = v1;
    }  
      
      
     @Override
    public Object getFieldValue(JRField jrf) throws JRException {
            Object o = null;

        String sName = jrf.getName();

        BillQTCancellationModel.GetSetMethod1 curr = v1.get(m_nIdx1);


        if (curr == null) {
            return null;
        }

        if (sName.equals("Qtid")) {
            o = curr.getQtid();
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
        else if (sName.equals("Amount")) {
            o = curr.getAmount();
        }
        return o;   

             
    }
    
    
    
}
