
package com.openbravo.pos.payment;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

/**
 *
 * @author adrianromero
 */
public class JPaymentSelectRefundstd1 extends JPaymentSelectstd1 {
    
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectRefundstd1(java.awt.Frame parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    }
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectRefundstd1(java.awt.Dialog parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    } 
    
    public static JPaymentSelectstd1 getDialog(Component parent) {
         
        Window window = getWindow(parent);
        
        if (window instanceof Frame) { 
            return new JPaymentSelectRefundstd1((Frame) window, true, parent.getComponentOrientation());
        } else {
            return new JPaymentSelectRefundstd1((Dialog) window, true, parent.getComponentOrientation());
        }
    } 
    
    protected void addTabs(boolean temp) {
        
        addTabPayment(new JPaymentSelectstd1.JPaymentCashRefundCreator());
        addTabPayment(new JPaymentSelectstd1.JPaymentChequeRefundCreator());
        addTabPayment(new JPaymentSelectstd1.JPaymentPaperRefundCreator());
        addTabPayment(new JPaymentSelectstd1.JPaymentMagcardRefundCreator());
        setHeaderVisible(false);
    }
    
    protected void setStatusPanel(boolean isPositive, boolean isComplete) {
        
        setAddEnabled(isPositive && !isComplete);
        setOKEnabled(isComplete);
    }    
    
    protected PaymentInfo getDefaultPayment(double total) {
        return new PaymentInfoTicket(total, "cashrefund");
    }
     protected PaymentTicketInfo getDefaultPaymentTicket(double total) {
       return new TicketInfoCash(total, total);
    }
}
