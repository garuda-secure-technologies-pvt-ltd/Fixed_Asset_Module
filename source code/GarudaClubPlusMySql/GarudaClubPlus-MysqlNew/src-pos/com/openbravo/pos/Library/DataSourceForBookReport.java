/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.Booking.BookingSituationHallTableModel;
import com.openbravo.pos.Booking.DataSourceForHallBookingSituation;
import com.openbravo.pos.Library.BookTableModel;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



/**
 *
 * @author dev3
 */
public class DataSourceForBookReport  implements JRDataSource{
    private int m_nIdx;
    private List<BookTableModel.BookInfo> v;
   
    DecimalFormat decimalformat=new DecimalFormat("#0.00##");
    
    int [] ArrInt = new int [10];
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    public DataSourceForBookReport(List<BookTableModel.BookInfo> v , int[] intArr){
                m_nIdx = -1;
		this.v = v;
                this.ArrInt=intArr;
                
    }
    
    
    
    public Object getFieldValue(JRField jrf) throws JRException {
        
       Object o=null;
       String sName = jrf.getName();
       
       BookTableModel.BookInfo curr = v.get(m_nIdx);
       
        if (curr== null)
	   return null;
        
         else if (sName.equals("sno")) 
              o = m_nIdx+1;
               
        
                if(sName.equals("RefNo")){
                    
                    
                    if(ArrInt[0]==1){
                        o = curr.getRefNo();
                    }
                    else{
                        o=null;
                    }
                                
                }

                else if (sName.equals("Name")){
                        if(ArrInt[1]==1){
                                o = curr.getName();
                        }
                        else{
                        }
                }
                 else if (sName.equals("Author")){
                        if(ArrInt[2]==1){
                                o = curr.getAuthor();
                        }else{
                            o=null;
                        }
                                
                 }
                 else if (sName.equals("Publisher")){
                          if(ArrInt[3]==1){
                               o = curr.getPublisher();
                          }else{
                              o=null;
                          } 
                               
                 }
                 else if (sName.equals("Language")){
                     if(ArrInt[4]==1){
                          o = curr.getLanguage();
                      }
                     else{
                         o=null;
                     }
                 
                 }
                               
                 else if (sName.equals("Category")){
                     if(ArrInt[5]==1){
                           o = curr.getCategory();
                     }
                     else{
                         o=null;
                     }
                 }
                 else if (sName.equals("Copies")){
                     if(ArrInt[6]==1){
                           o = curr.getCopies();      
                     }else{
                            o=null;
                     }
                 }
                                     
                 else if (sName.equals("Edition")){
                        if(ArrInt[7]==1){
                             o = curr.getEdition();
                        }else{
                            o=null;
                        }
                 
                 
                 }
                               
                 else if (sName.equals("Avialability")){
                        if(ArrInt[8]==1){
                              o = curr.getAvailability();
                        }else{
                                o=null;
                        }
                 }
                               
                 else if (sName.equals("Media")){
                        if(ArrInt[9]==1){
                             o = curr.getMedia();
                        }else{
                                o=null;
                        
                        }
                 
                 }
                               
                
                 
                 
                
                
        
                
                
                
                
       return o;
    }
    
    
    
    
}
 