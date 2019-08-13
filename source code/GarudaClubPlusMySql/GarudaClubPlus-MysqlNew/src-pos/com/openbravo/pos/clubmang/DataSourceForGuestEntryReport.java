
package com.openbravo.pos.clubmang;


import com.openbravo.format.Formats;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForGuestEntryReport implements JRDataSource{
    
    private int m_nIdx;
    private List<GuestlistTableModel.MembersGuestLine> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
    
    
     public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
      public DataSourceForGuestEntryReport(List<GuestlistTableModel.MembersGuestLine> v) {
		m_nIdx = -1;
		this.v = v;
     } 
      
      
      
      public Object getFieldValue(JRField jrf) throws JRException {
      
         Object o=null;
         String sName = jrf.getName();
         
          GuestlistTableModel.MembersGuestLine curr = v.get(m_nIdx);
       
          if (curr== null)
	      return null;
       
          
               if(sName.equals("SRNO"))
                                o = m_nIdx+1;

                else if (sName.equals("CRDATE")){
                   String x =  Formats.TIMESTAMP.formatValue(curr.getdate());
                     o = x;
                }
                               
               
                else if (sName.equals("MEMNAME")){
                    String Memno = curr.getMemberNo();
                    String Name = curr.getmname();
                    String x = Memno+"-"+Name;
                    o=x;
                }
                            
               
                 else if (sName.equals("GUESTCAT"))
                                o = curr.getguestcat();
               
                 else if (sName.equals("GUESTNO"))
                                o = curr.getNum();
               
                 else if (sName.equals("TOTAL"))
                                o = curr.getamount();
               
                 else if (sName.equals("GUESTNAME"))
                                o = curr.getnames();
               
                 else if (sName.equals("RNO"))
                                o = curr.getReceiptNo();
               
                 else if (sName.equals("CRBY"))
                                o = curr.getcrby();
               
                 else if (sName.equals("TaxAmt"))
                                o = curr.getTaxAmount();
               
                else if (sName.equals("TOTALFees")){
                     o = (curr.getTaxAmount()+curr.getamount());
                }
                                
               
       return o;
    } 
    
}
