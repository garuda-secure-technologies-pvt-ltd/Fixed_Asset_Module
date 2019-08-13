

package com.openbravo.pos.payment;

import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;

public class PaymentInfoFree extends PaymentInfo {
    
    private double m_dTotal;
   
    /** Creates a new instance of PaymentInfoFree */
    public PaymentInfoFree(double dTotal) {
        m_dTotal = dTotal;
        //Added By Ganesh
        BillInfo.totalcardamt=m_dTotal;
    }
    
    public PaymentInfo copyPayment(){
        return new PaymentInfoFree(m_dTotal);
    }    
    public String getName() {
        return "free";
    }   
    public double getTotal() {
        return m_dTotal-Billpage.interest1;
    }
    public Double getOtherCharges(){
        return 0.00;
    }
    @Override
    public ChequeDetails getChequeDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTrack3() {
        //System.out.println("PaymentInfoFree line 33:::");
       return "No trans";
    }

    @Override
    public String getTrack2() {
        //System.out.println("PaymentInfoFree line 39:::");
      return "No trans";
    }

    @Override
    public String getTrack1() {
        //System.out.println("PaymentInfoFree line 45:::");
        return "No trans";
    }
  public void setTotal(Double amt){
         
    }

//    @Override
//    public double getTotal1() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setTotal1(Double amt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
