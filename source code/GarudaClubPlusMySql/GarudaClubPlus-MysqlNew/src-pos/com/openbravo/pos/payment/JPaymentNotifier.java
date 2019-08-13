

package com.openbravo.pos.payment;

/**
 *
 * @author adrian
 */
public interface JPaymentNotifier {
    
//    public void setOKEnabled(boolean bValue);
//    public void setAddEnabled(boolean bValue);    
    public void setStatus(boolean isPositive, boolean isComplete);
}
