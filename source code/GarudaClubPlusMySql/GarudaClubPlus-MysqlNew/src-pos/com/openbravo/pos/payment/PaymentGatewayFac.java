

package com.openbravo.pos.payment;

import com.openbravo.pos.forms.*;

public class PaymentGatewayFac {
    
    /** Creates a new instance of PaymentGatewayFac */
    private PaymentGatewayFac() {
    }
    
    public static PaymentGateway getPaymentGateway(AppProperties props) {
        
        String sReader = props.getProperty("payment.gateway");
        
        if ("external".equals(sReader)) {
            return new PaymentGatewayExt();
        } else if ("SECPay".equals(sReader)) {
            return new PaymentGatewaySECPay(props);
        } else if ("AuthorizeNet".equals(sReader)) {
            return new PaymentGatewayAuthorizeNet(props);
        } else {
            return null;
        }
    }      
}
