
package com.openbravo.pos.Booking;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForBilledReports_Rooms_Hori  implements JRDataSource  {
    private int m_nIdx;
    private List<BilledReportsTableModel.RoomBillInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }

    
     public DataSourceForBilledReports_Rooms_Hori(List<BilledReportsTableModel.RoomBillInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
     
    
    
    
    public Object getFieldValue(JRField jrf) throws JRException {
       
        
        Object o = null;
       
         String sName = jrf.getName();
       
         BilledReportsTableModel.RoomBillInfo curr = v.get(m_nIdx);
       
         if (curr== null)
	   return null;
            
            
         
          if(sName.equals("BillNo"))
                o = curr.getBillNo();
          
         else if (sName.equals("BookingSeqNo"))  
              o = curr.getBOOKING_SEQ_NO();
         
          else if (sName.equals("MemNo"))  
              o = curr.getMemNo();
         
          else if (sName.equals("MemName"))  
              o = curr.getCUSTOMER();
         
          else if (sName.equals("GuestName"))  
          {
              if(curr.getGUESTNAME()!=null){
                   o = curr.getGUESTNAME();
              }
              else{
                   o ="";
              }
          }
             
          
           else if (sName.equals("BookingDate"))  
              o = curr.getCHK_IN();
          
            else if (sName.equals("CHKOUTDATE"))  
              o = curr.getCHK_OUT();
           
           
           else if (sName.equals("RoomType"))  
              o = curr.getROOMTYPE();
          
           else if (sName.equals("RoomNo"))  
              o = curr.getROOM_NO();
          
           else if (sName.equals("NoOfRooms"))  
              o = curr.getNo_OF_ROOMS()+" No.";
          
           else if (sName.equals("NoOfDays"))  
              o = curr.getNO_OF_DAYS()+" Days.";
        
          else if (sName.equals("TotalBillAMT"))  
              o = decimalFormat.format(curr.getRATE()+curr.getTAX_TOTAL()+curr.getRM_SERV_AMT());
        
           else if (sName.equals("RATE"))  
              o = decimalFormat.format(curr.getRATE());
          
           else if (sName.equals("TAXAMT"))  
              o = decimalFormat.format(curr.getTAX_TOTAL());
           
           
            else if (sName.equals("ADVNCRERECV"))  
              o = decimalFormat.format(curr.getADVNCE_RECV());
           
           
           
           else if (sName.equals("CRBY"))  
              o = curr.getCRBY();
           
           
           else if (sName.equals("CrDATE"))  
              o = "Cr Date: "+curr.getCRDATE();
          
          
          
          else if (sName.equals("SRNO"))  
              o = (m_nIdx+1);
          
          
          
          
          
          
          
          
          
           return o;
        
    }
    
    
    
    
    
}
