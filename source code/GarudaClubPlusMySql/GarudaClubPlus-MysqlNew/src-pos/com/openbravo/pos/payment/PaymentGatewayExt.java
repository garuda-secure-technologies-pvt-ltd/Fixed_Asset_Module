

package com.openbravo.pos.payment;

public class PaymentGatewayExt implements PaymentGateway {
    
    /** Creates a new instance of PaymentGatewayExt */
    public PaymentGatewayExt() {
    }
  
    public void execute(PaymentInfoMagcard payinfo) {
        payinfo.paymentOK("OK"); // Este es el codigo de actualizacion...
    }
}
