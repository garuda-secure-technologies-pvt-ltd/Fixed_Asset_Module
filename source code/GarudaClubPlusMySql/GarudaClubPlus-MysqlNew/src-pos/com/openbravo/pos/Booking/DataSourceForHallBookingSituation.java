
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForHallBookingSituation implements JRDataSource{

    private int m_nIdx;
    private List<BookingSituationHallTableModel.HallStatusInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }

     public DataSourceForHallBookingSituation(List<BookingSituationHallTableModel.HallStatusInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
     
    
    
    public Object getFieldValue(JRField jrf) throws JRException {
        
       Object o=null;
       String sName = jrf.getName();
       
       BookingSituationHallTableModel.HallStatusInfo curr = v.get(m_nIdx);
       
        if (curr== null)
	   return null;
        
        
               
        
                if(sName.equals("BookingIdLabel"))
                                o = "Booking ID :";

                else if (sName.equals("BookingId"))
                                o = curr.getBOOKING_SEQ_NO();
        
                else if(sName.equals("MNOLabel"))
                                o = "Member No :";

                else if (sName.equals("Mno"))
                                o = curr.getMem_No();
                
                else if (sName.equals("Mname"))
                                o = curr.getMemberName();
                
                
                
                else if (sName.equals("GuestNameLabel")){
                    if(curr.getMem_flag()!=1){
                         o = "Guest Name :";
                    }
                    else{
                        o="";
                    }
                }
                               
                
                
                
                else if (sName.equals("Gname")){
                    if(curr.getMem_flag()!=1){
                         o = curr.getNON_MEM_NAME();
                    }
                    else{
                        o = "";
                    }
                }
                               
                
                
                else if (sName.equals("RoomTypeLabel"))
                                o = "Hall Name : ";
                
                
                else if (sName.equals("RoomType"))
                                o = curr.gethall_name();
       
                
                
                 else if (sName.equals("NoOfDaysLabel"))
                                o = "Slot Booked : ";
                
                
                 else if (sName.equals("NoofDays")){
                     if(curr.getSLOT_FLAG()==1){
                         o = "Hourly Booked";
                     }
                     else if(curr.getSLOT_FLAG()==2){
                         o = "Half Day";
                     }
                     else{
                         o = "Full Day";
                     }
                 }
                                
                
                 else if (sName.equals("NoOfRoomsLabel"))
                                o =  "Slot Timings : ";
       
                 else if (sName.equals("NoofRooms"))
                                o = curr.getTIMING_SLOTS();
                 
                
       
                 else if (sName.equals("BookingDateLabel"))
                                o = "Booked On : ";
       
                  else if (sName.equals("BookingDate"))
                                o = curr.getCRDATE();
                
                
                 else if (sName.equals("AdvnceRecvDateLabel"))
                                o = "Advance Payment:";
                
                
                    else if (sName.equals("AdvnceRecvDate")){
                        if(curr.getPAYMENT_FLAG()==1){
                            
                            try {
                                o = curr.getAdvanceDate();
                            } catch (BasicException ex) {
                                Logger.getLogger(DataSourceForHallBookingSituation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                    
                        }
                        else{
                            o = "Payment Pending";
                        }
                    }
                                
                
                 else if (sName.equals("CkeckInDateLabel"))
                                o = "Booking Date : ";
                
                
                 else if (sName.equals("ChkInDate")){
                      o = curr.getBOOKING_DATE();
                 }
                                
                
                
                
       return o;
    }
    
}
