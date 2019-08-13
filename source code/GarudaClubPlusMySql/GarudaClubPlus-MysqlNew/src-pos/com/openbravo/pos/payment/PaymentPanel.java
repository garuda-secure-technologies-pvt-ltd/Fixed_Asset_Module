

package com.openbravo.pos.payment;

import javax.swing.JComponent;
import com.openbravo.pos.forms.AppView;


public interface PaymentPanel {
    
    public void activate(String sTransaction, double dTotal,AppView App);
    public JComponent getComponent();
    public PaymentInfoMagcard getPaymentInfoMagcard();
}
