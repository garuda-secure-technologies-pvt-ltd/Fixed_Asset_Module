

package com.openbravo.pos.payment;

import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;

/**
 *
 * @author Adrian
 */
public interface JPaymentInterface {
    
    public void activate(CustomerInfoExt customerext, double dTotal,AppView App); 
    
    public PaymentInfo executePayment();
    public Component getComponent();
}
