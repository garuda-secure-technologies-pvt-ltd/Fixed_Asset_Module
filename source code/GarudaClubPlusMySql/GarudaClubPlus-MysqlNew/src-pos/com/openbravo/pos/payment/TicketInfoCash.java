/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.payment;

import com.openbravo.format.Formats;

/**
 *
 * @author Administrator
 */
public class TicketInfoCash extends PaymentTicketInfo {
    private double m_dPaid;
    private double m_dTotal;
 //  private double m_dTotal;
  public TicketInfoCash(double  dTotal, double dPaid) {
       m_dTotal = dTotal;
        m_dPaid = dPaid;
  }
   public PaymentInfo copyPayment(){
      return new PaymentInfoCash(m_dTotal, m_dPaid);
   }
        public String getName() {
        return "cash";
    }   
    public double getTotal() {
        return m_dTotal;
    }   
    public double getPaid() {
        return m_dPaid;
    }
    
    public String printPaid() {
        return Formats.CURRENCY.formatValue(new Double(m_dPaid));
    }   
    public String printChange() {
        return Formats.CURRENCY.formatValue(new Double(m_dPaid - m_dTotal));
    }

   

    @Override
    public ChequeDetails getChequeDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

                      
    

}
