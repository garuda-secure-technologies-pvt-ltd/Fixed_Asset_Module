 

package com.openbravo.pos.payment;

import com.openbravo.format.Formats;
import static com.openbravo.pos.payment.JPaymentSelect.txRefID;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;

public class PaymentInfoCash extends PaymentInfo {
    private double m_dPaid;
    private double m_dTotal;
    private double m_dIntrest=Billpage.interest1;
    private double m_dGmt=Billpage.Grandtotal;
    
    /** Creates a new instance of PaymentInfoCash */
    
    public PaymentInfoCash() {
    }

    
    public PaymentInfoCash(double dTotal, double dPaid) {
        m_dTotal = dTotal;
        m_dPaid = dPaid;
        //Added By Ganesh
        BillInfo.totalcash=m_dTotal;
//         m_dTotal = BillInfo.stottax;
//          m_dPaid = dPaid;
    }
    
    public PaymentInfo copyPayment(){
        return new PaymentInfoCash(m_dTotal, m_dPaid);
    }
    
    public String getName() {
        return "cash";
    }   
    public double getTotal() {
        return m_dTotal;
   // return BillInfo.stottax;
    }   
    public double getPaid() {
        return m_dPaid;
//return BillInfo.stottax;
    }
    public String printPaid() {
        return Formats.CURRENCY.formatValue(new Double(m_dPaid));
    }   
    public String printChange() {
        return Formats.CURRENCY.formatValue(new Double(((m_dPaid-Billpage.interest1) - (m_dTotal-Billpage.interest1))));
    }
    //Added By GG
     public String printIntrest() {
        return Formats.CURRENCY.formatValue(new Double(m_dIntrest));
    }  
       public String printGtotal() {
        return Formats.CURRENCY.formatValue(new Double(BillInfo.gt));
    }
    @Override
    public ChequeDetails getChequeDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Double getOtherCharges(){
        return 0.00;
    }
      public double getM_dIntrest() {
        return m_dIntrest;
    }

    public void setM_dIntrest(double m_dIntrest) {
        this.m_dIntrest = m_dIntrest;
    }
       public double getM_dAmt() {
        return m_dGmt;
    }

    public void setM_dAmt(double m_dGmt) {
        this.m_dGmt = m_dGmt;
    }
    @Override
    public String getTrack3() {
        return "No Trans";
    }

    @Override
    public String getTrack2() {
        return "No Trans";
    }

    @Override
    public String getTrack1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setTotal(Double amt){
      m_dTotal=amt;
   
       // m_dTotal =amt;
      
//Billpage.interest1 contains interest applied to the costumer balance.
            //m_dTotal=BillInfo.stottax+Billpage.interest1;
    }

//    @Override
//    public double getTotal1() {
//        return BillInfo.stottax; //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public void setTotal1(Double amt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
