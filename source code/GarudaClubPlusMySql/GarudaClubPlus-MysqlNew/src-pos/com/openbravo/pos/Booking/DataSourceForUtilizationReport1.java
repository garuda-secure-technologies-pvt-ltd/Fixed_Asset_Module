
package com.openbravo.pos.Booking;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForUtilizationReport1 implements JRDataSource {
    
    private int m_nIdx;
    private List<UtilizationReportTableModel.HallStatusInfo_MemID> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
     public boolean next() throws JRException {
        m_nIdx++;
       return (m_nIdx < v.size());
    }

       public DataSourceForUtilizationReport1(List<UtilizationReportTableModel.HallStatusInfo_MemID> v) {
		m_nIdx = -1;
		this.v = v;
     }

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
       
       String sName = jrf.getName();
       
       UtilizationReportTableModel.HallStatusInfo_MemID curr = v.get(m_nIdx);
       
        if (curr== null)
	   return null;
        
          
               
               
        
                else if(sName.equals("DATA1"))
                                o = curr.getLabel();

               
                else if (sName.equals("DATA2"))
                {
                    int booked = curr.getStatus();
                    int total = curr.gettotal();
                    Double hrd = 100.00;
                    String d = decimalFormat.format(booked*hrd/total);
                    Double d1 = Double.parseDouble(d);
                    o = d1;
                }
        
                else if(sName.equals("DATA3")) {
                    int booked = curr.getStatus();
                    int total = curr.gettotal();
                    Double hrd = 100.00;
                   String d = decimalFormat.format(booked*hrd/total);
                   Double d1 = Double.parseDouble(d);
                   
                    
                     o = curr.getLabel() + " "+d1+"%"  ;
                }
                                  
       
       
       return o;
    }
    
    
}
