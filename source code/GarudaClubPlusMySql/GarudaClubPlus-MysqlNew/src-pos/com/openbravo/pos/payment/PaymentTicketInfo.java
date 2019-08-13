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
public  abstract class PaymentTicketInfo {
     public abstract String getName();
    public abstract double getTotal();
    public abstract PaymentInfo copyPayment();
    public abstract ChequeDetails getChequeDetails();
    
     public String printTotal() {
         return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
    
}
