

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
public class JPaymentSelectReceiptstd1 extends JPaymentSelectstd1  implements JPaymentNotifier{
    
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectReceiptstd1(java.awt.Frame parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    }
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectReceiptstd1(java.awt.Dialog parent, boolean modal, ComponentOrientation o) {
        super(parent, modal, o);
    } 
    
    public static JPaymentSelectstd1 getDialog(Component parent) {
         
        Window window = getWindow(parent);
        
        if (window instanceof Frame) { 
            return new JPaymentSelectReceiptstd1((Frame) window, true, parent.getComponentOrientation());
        } else {
            return new JPaymentSelectReceiptstd1((Dialog) window, true, parent.getComponentOrientation());
        }
    } 
    
    protected void addTabs(boolean flag) {
        
        addTabPayment(new JPaymentSelectstd1.JPaymentCashCreator());
        addTabPayment(new JPaymentSelectstd1.JPaymentChequeCreator());
        addTabPayment(new JPaymentSelectstd1.JPaymentDebtCreator());
      //  boolean perm=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter");
        if(flag==true)
        {
        
     //   addTabPayment(new JPaymentSelectstd1.JPaymentPaperCreator());            
       // addTabPayment(new JPaymentSelectstd1.JPaymentMagcardCreator());                
      //  addTabPayment(new JPaymentSelectstd1.JPaymentFreeCreator());                
       // addTabPayment(new JPaymentSelectstd1.JPaymentDebtCreator());
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
   protected PaymentTicketInfo getDefaultPaymentTicket(double total) {
       return new TicketInfoCash(total, total);
    }
    
}
