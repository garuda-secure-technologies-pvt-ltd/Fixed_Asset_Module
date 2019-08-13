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
import com.openbravo.pos.Library.BookListDetails;

/**
 *
 * @author dev3
 */
public class DataSourceForBookDetail implements JRDataSource {

    private int m_nIdx;
    private List<BookListDetails.BookListInfo> v;
    DecimalFormat decimalformat = new DecimalFormat("#0.00##");
   
    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
    public DataSourceForBookDetail(List<BookListDetails.BookListInfo> v) {
       m_nIdx = -1;
		this.v = v;
       
        
    }

   

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        BookListDetails.BookListInfo curr = v.get(m_nIdx);

        if (curr == null) 
            return null;
        
         else if (sName.equals("sno")) 
              o = m_nIdx+1;
         
        if (sName.equals("RefNo")) 
               
            o = curr.getRefNo();

         else if (sName.equals("Name")) 

            o = curr.getName();

         else if (sName.equals("Author")) 
                
            o = curr.getAuthor();
                
            

         else if (sName.equals("Publisher")) 
            
            o = curr.getPublisher();
            

         else if (sName.equals("Language")) 

            o = curr.getLanguage();

         else if (sName.equals("Category")) 
              
            o = curr.getCategory();
                
            

         else if (sName.equals("Copies")) 

            o = curr.getCopies();

         else if (sName.equals("Edition")) 

            o = curr.getEdition();

         else if (sName.equals("Avialability")) 

            o = curr.getAvailability();

          else if (sName.equals("Vendor")) 
            
            o = curr.getVendor();
            

        else if (sName.equals("Media")) 
                
            o = curr.getMedia();
               
        

        return o;

    }
}
