
package com.openbravo.pos.Booking;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForCancellationReport_Room implements JRDataSource{
     
    
     private int m_nIdx;
     private List<CancellationReportTableModel.RoomAdvInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##");  
     
     
     
     
     
    public boolean next() throws JRException {
        m_nIdx++;
       return (m_nIdx < v.size());
    }
   public DataSourceForCancellationReport_Room(List<CancellationReportTableModel.RoomAdvInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
   
   
    public Object getFieldValue(JRField jrf) throws JRException {
      Object o = null;
       
         String sName = jrf.getName();
       
         CancellationReportTableModel.RoomAdvInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
        
         
         
         
                if(sName.equals("BookingSeqNo")){
                    o = curr.getbooking_seq_no();
                }
                    
                else if (sName.equals("MemNo"))
                {
                    o = curr.getMEMBER_NO();
                }
        
                else if(sName.equals("MemName")) {
                    o = curr.getMEMBERNAME();
                }
                 else if(sName.equals("BookingDate")) {
                    o = curr.getCHECK_IN_DATE();
                }                 
                 else if(sName.equals("RoomType")) {
                    o = curr.getROOMTYPE();
                }
                else if(sName.equals("TotAmt")) {
                    o = curr.getBilled_amt();
                }
                else if(sName.equals("AdvnceRecv")) {
                    o = curr.getADVANCE_RECV();
                }
                else if(sName.equals("REFUNDAMT")) {
                    o = curr.getRefund_amt();
                }
                else if(sName.equals("REFUNDID")) {
                    o = curr.getRefundBy();
                }
                else if(sName.equals("REFUNDDATE")) {
                    o = curr.getRefund_Date();
                }
               
       return o;
    }
    
}
