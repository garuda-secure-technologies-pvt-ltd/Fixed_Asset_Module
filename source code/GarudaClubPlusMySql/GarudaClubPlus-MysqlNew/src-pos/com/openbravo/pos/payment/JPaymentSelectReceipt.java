

package com.openbravo.pos.payment;

//import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

/**
 *
 * @author adrianromero
 */
public class JPaymentSelectReceipt extends JPaymentSelect {
    
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectReceipt(java.awt.Frame parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    }
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectReceipt(java.awt.Dialog parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    } 
    
    public static JPaymentSelect getDialog(Component parent) {
         
        Window window = getWindow(parent);
        
        if (window instanceof Frame) { 
            return new JPaymentSelectReceipt((Frame) window, true, parent.getComponentOrientation());
        } else {
            return new JPaymentSelectReceipt((Dialog) window, true, parent.getComponentOrientation());
        }
    } 
    
    protected void addTabs(boolean flag) {
        
        addTabPayment(new JPaymentSelect.JPaymentCashCreator());
        addTabPayment(new JPaymentSelect.JPaymentChequeCreator());
      //  boolean perm=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter");
        if(flag==true)
        {
        
        addTabPayment(new JPaymentSelect.JPaymentPaperCreator());            
        addTabPayment(new JPaymentSelect.JPaymentMagcardCreator());                
        addTabPayment(new JPaymentSelect.JPaymentFreeCreator());                
        addTabPayment(new JPaymentSelect.JPaymentDebtCreator());
        }
        setHeaderVisible(true);
    }
    
    protected void setStatusPanel(boolean isPositive, boolean isComplete) {
        
        setAddEnabled(isPositive && !isComplete);
        setOKEnabled(isComplete);
    }
    
    protected PaymentInfo getDefaultPayment(double total) {
        return new PaymentInfoCash(total, total);
    }
}
