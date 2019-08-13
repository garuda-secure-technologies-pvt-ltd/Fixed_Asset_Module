
package com.openbravo.pos.Booking;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForAdvancePaymentReport implements JRDataSource{
 
     private int m_nIdx;
    private List<AdvanceRecvReportModel.HallAdvInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
   
     public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }
    
      public DataSourceForAdvancePaymentReport(List<AdvanceRecvReportModel.HallAdvInfo> v) {
		m_nIdx = -1;
		this.v = v;
     } 

    public Object getFieldValue(JRField jrf) throws JRException {
      
         Object o=null;
         String sName = jrf.getName();
         
          AdvanceRecvReportModel.HallAdvInfo curr = v.get(m_nIdx);
       
          if (curr== null)
	      return null;
       
          
               if(sName.equals("BookingSeqNo"))
                                o = curr.getBOOKING_SEQ_NO();

                else if (sName.equals("MemNo"))
                                o = curr.getMEMBER_NO();
        
                else if(sName.equals("MemName"))
                                o = curr.getMEMBERNAME();

                else if (sName.equals("BookingDate"))
                                o = curr.getCHECK_IN_DATE();
                
             
                
                else if (sName.equals("TotAmt"))
                                o = curr.getTOTAL_AMOUNT();
                
                
                 else if (sName.equals("AdvnceRecv"))
                                o = curr.getADVANCE_RECV();
                
                 else if (sName.equals("BalanceAmt"))
                                o = curr.getBAL_AMT();
         
                  else if (sName.equals("AdvncePaymntDate"))
                                o = curr.getADVANCE_PMT_DT();
               
               
                 else if (sName.equals("AdvnceRef"))
                 {
                     o = curr.getADVNCE_REF();
                    /*
                     String s = curr.getADVNCE_REF();
                     String Final="";
                     String []arr = s.split("#");
                     for(int i=0;i<arr.length;i=1+2){
                         Final = Final+","+ arr[0];
                     }
                     o = Final;
                     */
                 }
                               
                
                else if (sName.equals("GuestName"))
                {
                    if(curr.getGuestName()!=null && curr.getGuestName()!="" && curr.getGuestName().length()>0){
                        o="("+curr.getGuestName()+")";
                    }
                    else{
                        o="";
                    }
                }
               
       return o;
    }
    
}
