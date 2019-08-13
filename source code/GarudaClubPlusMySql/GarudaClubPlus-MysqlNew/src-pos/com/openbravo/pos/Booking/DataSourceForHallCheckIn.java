/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Booking;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author user
 */
public class DataSourceForHallCheckIn implements JRDataSource{
    private int m_nIdx;
    private List<HallBillModel.HallBillInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    Double G_Amount = 0.00;
    
    
     public DataSourceForHallCheckIn(List<HallBillModel.HallBillInfo> v) {
		m_nIdx = -1;
		this.v = v;
     }
     
     
     
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }

    
    
    
    
    
    public Object getFieldValue(JRField jrf) throws JRException {
           Double Amount=0.00;
                
                Object o = null;

		String sName = jrf.getName();

		      HallBillModel.HallBillInfo curr = v.get(m_nIdx);
                
		if (curr== null)
			return null;
                
                if(curr!=null){
                   
                     Double amt1;
                      amt1 = curr.getRATE();
                      o = amt1.toString();
                      Amount = Double.parseDouble(o.toString());
                }
                
                

		if (sName.equals("BILLNAME"))
			o = curr.getBILL_NAME();
                
                else if(sName.equals("SKEY"))
                        o = curr.getMEMBERNO();
                
		else if (sName.equals("HALL_NAME"))
			o = curr.getHALL_NAME();
                
                else if (sName.equals("BOOKING_SEQ_NO"))
			o = curr.getBOOKING_SEQ_NO();
                
                else if (sName.equals("PARENTID"))
			o = curr.getBILL_ID();
                else if (sName.equals("MEMBER"))
			o = curr.getMEMBERNAME();
                else if (sName.equals("SLOT_TIME"))
			o = curr.getSLOT_TIME();
                
                else if(sName.equals("RATE"))
                        o = curr.getRATE();
                  
                else if(sName.equals("AMT1"))
                {
                   
                    Double amt1;
                    amt1 = curr.getRATE();
                    o = amt1;
                }  
                
                
              else if(sName.equals("ROOMSERCHRG"))
                        o =curr.getHALL_SERV_AMT();
                
                
                 else if(sName.equals("ADVREC"))
                        o = curr.getADVANCE_RECV();
                
                 else if(sName.equals("date"))
                        o = curr.getCurrDATE();
                 
                 else if(sName.equals("BOOKING_DATE"))
                        o = curr.getCHECKIN_DATE();
                 
                 
                 else if(sName.equals("WAITER"))
                        o = curr.getCRBY();
                 
                 
                 else if(sName.equals("RTAX1")){
                     Double d = (curr.getTAX1_RATE()*100);
                        o = decimalFormat.format(d)+"%";
                 }
                 else if(sName.equals("RTAX2")){
                     Double d = (curr.getTAX2_RATE()*100);
                        o = decimalFormat.format(d)+"%";
                 }
                 else if(sName.equals("RTAX3")){
                     Double d = (curr.getTAX3_RATE()*100);
                        o = decimalFormat.format(d)+"%";
                 }
                
                 else if(sName.equals("TAX1")){
                        Double t = curr.getTAX1_AMT();
                        o = t;
                 }
                  
                 
                 
                 
                 else if(sName.equals("TAX2")){
                    // if(curr.getBASIC1()==1){
                    //    Double t = curr.getTAX2_RATE()*Amount;
                     //   o = t;
                    // }
                    // else{
                     //   Double x = Amount + Amount*curr.getTAX1_RATE();
                      //   Double t = curr.getTAX2_RATE()*x;
                       //  o = t;
                    // }
                     
                     Double t = curr.getTAX2_AMT();
                     o = t; 
                     
                     
                 }
                   
                 
                 
                 else if(sName.equals("TAX3")){
                     //  if(curr.getBASIC2()==1){
                     //   Double t = curr.getTAX3_RATE()*Amount;
                      //  o = t; 
                     //}
                   //  else{
                         
                     //   Double x =  Amount + (Amount*curr.getTAX1_RATE()) ;
                     //   Double x1 = Amount + (x*curr.getTAX2_RATE());
                     //   Double t = curr.getTAX3_RATE()*x1;
                     //   o = t; 
                    // }
                     
                     
                     Double t = curr.getTAX3_AMT();
                     o = t; 
                     
                     
                 }
                 
                 
                 
                 
                 
                
                 else if(sName.equals("GTOTAL"))
                 {
                    
                    /* 
                    Double Temp=0.00;
                    Temp=(Amount)+(curr.getTAX1_RATE()*Amount);
                    
                    if(curr.getBASIC1()==1){
                        Temp = Temp +  (curr.getTAX2_RATE()*Amount);
                        
                    }
                    else{
                        
                       Temp = Temp +  (curr.getTAX2_RATE()*Temp);
                        
                    }
                    
                    if(curr.getBASIC2()==1){
                        Temp = Temp + ( curr.getTAX3_RATE()*Amount);
                        
                        
                    }
                    else{
                        
                         Temp = Temp + ( curr.getTAX3_RATE()*Temp);
                     
                    }
                    


                    // G_Amount = ((Amount)+(curr.getRM_SERV_CHRG())+(curr.getTAX1()*Amount)+(curr.getTAX2()*Amount)+( curr.getTAX3()*Amount));
                    // G_Amount = ((Amount)+(curr.getTAX1()*Amount)+(curr.getTAX2()*Amount)+( curr.getTAX3()*Amount));
                    */ 
                    
                     
                     Double t = curr.getTAX_TOTAL();
                     Double d = curr.getDiscount();
                     
                    G_Amount = ((Amount+t)-d);
                     o = G_Amount;
                 }
                  
                 
                 
                 
                 
                else if(sName.equals("BILL_NO")){
                    o = "---";
                }
              
     
                else if(sName.equals("PLACE")){
                    
                         o = "---";
                   
                   
                    
                }
                else if(sName.equals("B_DATE")){
                   
                         o = "---";
                   
                 
                }
                 else if(sName.equals("BAMT")){
                    Double bamt = curr.getHALL_SERV_AMT();
                    o = bamt;
                }
                
                
                
                else if(sName.equals("DISCOUNT")){
                    Double d = curr.getDiscount();
                    o = d;
                }
                
                else if(sName.equals("DISCOUNT_TOT")){
                    Double d = curr.getDiscount();
                    Double amt1;
                    amt1 = curr.getRATE();
                    o = amt1-d;
                    
                    
                    
                }
               
                
                
                
                else if(sName.equals("N_TAX1")){
                    if(curr.getTAX1_NAME()!=null){
                       o = (curr.getTAX1_NAME()+"  (B)");
                    }
                    else{
                        o="N/A";
                    }
                   
                }
                else if(sName.equals("N_TAX2")){
                    if(curr.getTAX2_NAME()!=null){
                        if(curr.getBASIC1()==1){
                            o = (curr.getTAX2_NAME()+"  (B)");
                        }
                        else{
                            o = (curr.getTAX2_NAME()+"  (C)");
                        }
                        
                       
                    }
                    else{
                        o="N/A";
                }
                    
                    
                }
                else if(sName.equals("N_TAX3")){
                    if(curr.getTAX3_NAME()!=null){
                        if(curr.getBASIC2()==1){
                             o = (curr.getTAX3_NAME()+"  (B)");
                        }
                        else{
                             o = (curr.getTAX3_NAME()+"  (C)");
                        }
                        
                       
                    }
                    else{
                        o="N/A";
                    }
                }
                
                
                else if(sName.equals("STOTAL"))
                {
                     //Double d = ((curr.getADV_RECV())-(G_Amount)-(curr.getRM_SERV_CHRG()));
                     Double d = ((G_Amount)-(curr.getADVANCE_RECV()));
                     if(d<0){
                         o = (d*(-1)); 
                     }
                     else{
                        o = d;  
                     }
                     
                     
                }
                
                else if(sName.equals("Amount_Title"))
                {
                    Double d = ((G_Amount)-(curr.getADVANCE_RECV()));
                    if(d>=0){
                        o="Amount Payable :";
                    } 
                    else{
                         o="Amount Refundable :";
                    }
                }
                
                    
               
                 
                 else if(sName.equals("BILL_NO")){
                     
                 }
                 
                
             
        
        return o;
    }
    
}
