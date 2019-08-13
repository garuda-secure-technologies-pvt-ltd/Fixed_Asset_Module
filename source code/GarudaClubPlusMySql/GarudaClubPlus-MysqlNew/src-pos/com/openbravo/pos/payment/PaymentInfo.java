


package com.openbravo.pos.payment;

import com.openbravo.format.Formats;
import com.openbravo.pos.sales.Billpage;

public abstract class PaymentInfo {
    
    public abstract String getName();
    public abstract double getTotal();
    //public abstract double getTotal1();
    public abstract Double getOtherCharges();
    public abstract String getTrack3();
    public abstract String getTrack1();
    public abstract String getTrack2();
    public abstract PaymentInfo copyPayment();
    public abstract ChequeDetails getChequeDetails();
     public abstract void setTotal(Double amt);
     //public abstract void setTotal1(Double amt);
    public String printTotal() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
    //Added by gg
    public String printGrandTotal1() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
    
}
