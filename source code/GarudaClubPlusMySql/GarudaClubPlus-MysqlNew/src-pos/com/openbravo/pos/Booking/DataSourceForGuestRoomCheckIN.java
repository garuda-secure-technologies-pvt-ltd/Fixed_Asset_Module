
package com.openbravo.pos.Booking;

import com.openbravo.pos.Accounts.AccountReports;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForGuestRoomCheckIN implements JRDataSource{
     private int m_nIdx;   
     private List<GuestRoomBillModel.RoomAdvInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
     Double G_Amount = 0.00;
     Double TotalRoomCharge = 0.00;
     
     
     public DataSourceForGuestRoomCheckIN(List<GuestRoomBillModel.RoomAdvInfo> v) {
		m_nIdx = -1;
		this.v = v;
	}
     
    
    public boolean next() throws JRException {
       m_nIdx++;
       return (m_nIdx < v.size());
    }

    public Object getFieldValue(JRField field) throws JRException {
                Double Amount=0.00;
                Double Grand_Total = 0.00;
                
                
                Object o = null;

		String sName = field.getName();

		GuestRoomBillModel.RoomAdvInfo curr = v.get(m_nIdx);
                
		if (curr== null)
			return null;
                
                if(curr!=null){
                     int days = curr.getBOOKING_DAYS();
                     int rooms = curr.getNO_OF_ROOMS();
                     Double amt1;
                      //amt1 = curr.getTariff()*days;
                      amt1 = curr.getTariff();
                      o = amt1.toString();
                      Amount = Double.parseDouble(o.toString());
                     
                }
                
                

		if (sName.equals("MEMBER"))
			o = curr.getMEMBERNAME();
                
                else if(sName.equals("BILLNAME"))
                        o = curr.getBILL_NAME();
		else if (sName.equals("SKEY"))
			o = curr.getMEMNO();
                else if (sName.equals("TABLE"))
			o = curr.getROOMTYPE();
                else if (sName.equals("FDATE"))
			o = curr.getCHK_IN();
                else if (sName.equals("TDATE"))
			o = curr.getCHK_OUT();
                else if (sName.equals("RoomNo"))
			o = curr.getROOMNO();
                
                else if(sName.equals("RATE"))
                        o = curr.getTariff();
                  
            
                else if(sName.equals("PARENTID"))
                     o = curr.getBillNo();
                
                else if(sName.equals("AMT1"))
                {
                    int days = curr.getBOOKING_DAYS();
                    int rooms = curr.getNO_OF_ROOMS();
                    Double amt1;
                  //  amt1 = curr.getTariff()*days;
                    amt1 = curr.getTariff();
                    o = amt1;
                }  
                
                
                else if(sName.equals("DAYS"))
                        o = curr.getBOOKING_DAYS()+"";
               
                 else if(sName.equals("ROOMSERCHRG"))
                        o =TotalRoomCharge;
                
                
                 else if(sName.equals("ADVREC"))
                        o = curr.getADV_RECV();
                
                
                 else if(sName.equals("WAITER"))
                        o = curr.getUser();
                 
                 
                 else if(sName.equals("RTAX1")){
                     Double d = (curr.getTAX1()*100);
                        o = decimalFormat.format(d)+"%";
                 }
                 else if(sName.equals("RTAX2")){
                     Double d = (curr.getTAX2()*100);
                        o = decimalFormat.format(d)+"%";
                 }
                 else if(sName.equals("RTAX3")){
                     Double d = (curr.getTAX3()*100);
                        o = decimalFormat.format(d)+"%";
                 }
                
                 
                 
                 else if(sName.equals("TAX1")){
                     
                          Double t = curr.getTAX1_AMT();
                          o = t;
                     
                     
                 }
                       
                 else if(sName.equals("TAX2")){
                     
                   //  if(curr.getBASIC1()==1){
                    //    Double t = curr.getTAX2()*Amount;
                    //    o = t;
                   //  }
                   //  else{
                   //     Double x = Amount + Amount*curr.getTAX1();
                    //     Double t = curr.getTAX2()*x;
                   //      o = t;
                   //  }
                     
                    Double t = curr.getTAX2_AMT();
                    o = t;
                       
                     
                     
                     
                     
                 }
                       
                 else if(sName.equals("TAX3")){
                     
                   //  if(curr.getBASIC2()==1){
                  //      Double t = curr.getTAX3()*Amount;
                   //     o = t; 
                   //  }
                   //  else{
                         
                    //    Double x =  Amount + (Amount*curr.getTAX1()) ;
                    //    Double x1 = Amount + (x*curr.getTAX2());
                    //    Double t = curr.getTAX3()*x1;
                    //    o = t; 
                    // }
                     
                    Double t = curr.getTAX3_AMT();
                    o = t;
                       
                     
                      
                 }
                
                 else if(sName.equals("GTOTAL"))
                 {
                    
                    /* 
                     Double Temp=0.00;
                    Temp=(Amount)+(curr.getTAX1()*Amount);
                    
                    if(curr.getBASIC1()==1){
                        Temp = Temp +  (curr.getTAX2()*Amount);
                        
                    }
                    else{
                        
                       Temp = Temp +  (curr.getTAX2()*Temp);
                        
                    }
                    
                    if(curr.getBASIC2()==1){
                        Temp = Temp + ( curr.getTAX3()*Amount);
                        
                        
                    }
                    else{
                        
                         Temp = Temp + ( curr.getTAX3()*Temp);
                     
                    }
                    


                    // G_Amount = ((Amount)+(curr.getRM_SERV_CHRG())+(curr.getTAX1()*Amount)+(curr.getTAX2()*Amount)+( curr.getTAX3()*Amount));
                    // G_Amount = ((Amount)+(curr.getTAX1()*Amount)+(curr.getTAX2()*Amount)+( curr.getTAX3()*Amount));
                     
                    
                    G_Amount = Temp;
                    o = G_Amount;    */
                     
                    Double d = curr.getDiscount();
                    Double t = curr.getTAX_TOTAL();
                    G_Amount = ((Amount +t)-d);  
                     
                    o = G_Amount; 
                     
                 }
                  
                else if(sName.equals("BILL_NO")){
                    o = curr.getbill_id();
                }
               
                else if(sName.equals("PLACE")){
                    if(curr.getplace()=="0"){
                         o = "---";
                    }
                    else{
                        o = curr.getplace();
                    }
                    
                }
                else if(sName.equals("B_DATE")){
                    if(curr.getplace()=="0"){
                         o = "---";
                    }
                    else{
                      o = curr.getRM_SER_BL_DT(); 
                   }
                    
                    
                   
                }
                else if(sName.equals("BAMT")){
                    Double bamt = curr.getBAmount();
                    o = bamt;
                    TotalRoomCharge = TotalRoomCharge+bamt;
                }
                
                else if(sName.equals("N_TAX1")){
                    o = (curr.getN_TAX1()+"  (B)");
                }
                else if(sName.equals("N_TAX2")){
                    if(curr.getBASIC1()==1){
                        o = (curr.getN_TAX2()+"  (B)");
                    }
                    else{
                        o = (curr.getN_TAX2()+"  (C)");
                    }
                    
                }
                else if(sName.equals("N_TAX3")){
                    if(curr.getBASIC2()==1){
                         o = (curr.getN_TAX3()+"  (B)");
                    }
                    else{
                         o = (curr.getN_TAX3()+"  (C)");
                    }
                   
                }
                
                else if(sName.equals("AdvanceRef")){
                    o = curr.getAdvanceRef();
                }
                
                 else if(sName.equals("BALAMT"))
                 {
                     Double d  = curr.getBAL_AMT();
                     o = d;
                 }
                
                else if(sName.equals("STOTAL"))
                {
                     //Double d = ((curr.getADV_RECV())-(G_Amount)-(curr.getRM_SERV_CHRG()));
                     Double d = ((curr.getADV_RECV())-(curr.getBAL_AMT())- (G_Amount));
                     
                     if(d<0){
                        d=d*(-1); 
                     }
                     
                     o = d;
                    
                }
                    
               else if(sName.equals("DISCOUNT"))
                 {
                     Double d  = curr.getDiscount();
                     o = d;
                 }
                
               
               
               else if(sName.equals("DISCOUNT_TOT"))
                 {
                    
                    int days = curr.getBOOKING_DAYS();
                    int rooms = curr.getNO_OF_ROOMS();
                    Double amt1;
                   // amt1 = curr.getTariff()*days;
                    amt1 = curr.getTariff();
                     
                    Double d  = curr.getDiscount();
                    o = amt1-d;
                 }
               
               
               
                 
                  else if(sName.equals("BOOKING_SEQ_NO"))
                {
                    o=curr.getBOOKING_SEQ_NO();
                } 
                 else if(sName.equals("BILL_NO")){
                     
                 }
                 
               else if(sName.equals("Payable_Text")){
                    Double d = ((G_Amount)-(curr.getADV_RECV()));
                    if(d>=0){
                        o="Amount Payable :-";
                    } 
                    else{
                        o="Amount Refundable :-";
                    }
                 }
                  
                  
                 else if(sName.equals("RoomNo"))
                {   
                   /* String t="";
                    String Room = GuestRoom_Check_in.getRoomNo();
                    String []x = Room.split("#");
                    
                    
                    for(int i=0;i<x.length;i++){
                        t = t+ x[i];
                        if(i+1<x.length){
                            t=t+",";
                        }
                        else{
                            t=t+".";
                        }
                    }
                     */
                    
                    String t = curr.getROOMNO();
                    
                    o = t;
                }
                
               
                
                
                return o;
    }
    
}
