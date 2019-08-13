
package com.openbravo.pos.payment;

import com.openbravo.format.Formats;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;
import java.text.DecimalFormat;

public class PaymentInfoMagcard extends PaymentInfo {
     
    protected double m_dTotal;
    protected double OtherCharges;
    protected String m_sHolderName;
    protected String m_sCardNumber;
    protected String m_sExpirationDate;
    protected String track1;
    protected String track2;
    protected String track3;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    protected String m_sTransactionID;
    
    protected String m_sAuthorization;    
    protected String m_sMessage;
    
    /** Creates a new instance of PaymentInfoMagcard */
    public PaymentInfoMagcard(String sHolderName, String sCardNumber, String sExpirationDate, String track1, String track2, String track3, String sTransactionID, double dTotal,Double OtherCharge) {
        m_sHolderName = sHolderName;
        m_sCardNumber = sCardNumber;
        m_sExpirationDate = sExpirationDate;
        this.track1 = track1;
        this.track2 = track2;
        this.track3 = track3;
        
        m_sTransactionID = sTransactionID;
        m_dTotal = dTotal;
        BillInfo.totalcardamt=m_dTotal;
        OtherCharges=OtherCharge;
        BillInfo.totalcardOtherCharge=OtherCharges;
        m_sAuthorization = null;
        m_sMessage = null;
    }
    
    /** Creates a new instance of PaymentInfoMagcard */
    public PaymentInfoMagcard(String sHolderName, String sCardNumber, String sExpirationDate, String sTransactionID, double dTotal, Double OtherCharge) {
        this(sHolderName, sCardNumber, sExpirationDate, null, null, null, sTransactionID, dTotal , OtherCharge);
        BillInfo.totalcardamt=dTotal;
        BillInfo.totalcardOtherCharge=OtherCharges;
    }
    
    public PaymentInfo copyPayment(){
        PaymentInfoMagcard p = new PaymentInfoMagcard(m_sHolderName, m_sCardNumber, m_sExpirationDate, track1, track2, track3, m_sTransactionID, m_dTotal,OtherCharges);
        p.m_sAuthorization = m_sAuthorization;
        p.m_sMessage = m_sMessage;
        BillInfo.totalcardamt=m_dTotal;
        BillInfo.totalcardOtherCharge=OtherCharges;
        return p;
    }    
    
    public String getName() {
        return "magcard";
    }
    public double getTotal() {
        //System.out.println("getTotal:: PaymentInfoMagcard::::"+m_dTotal);
        return m_dTotal;
    }         
    public Double getOtherCharges(){
        return OtherCharges;
    }
    
    public String PrintOtherCharges(){
        //System.out.println("OtherCharges:: PaymentInfoMagcard::::"+decimalFormat.format(OtherCharges));
        return "Rs. "+decimalFormat.format(OtherCharges);
    } 
    // Added By GG
        public String printTxID() {
        String txRefID1=null;
        txRefID1=JPaymentSelect.txRefID;
       
       JPaymentSelect.txRefID=null;
        return txRefID1;
    }
    public boolean isPaymentOK() {
        return m_sAuthorization != null;
    }    
    public String getHolderName() {
        return m_sHolderName;
    }
    public String getCardNumber() {
        return m_sCardNumber;
    }
    public String getExpirationDate() {
        return m_sExpirationDate;
    }    
    public String getTransactionID() {
        return m_sTransactionID;
    }
    public String getTrack1() {
        return track1;
    }
    public String getTrack2() {
        return track2;
    }
    public String getTrack3() {
        return track3;
    }
    
    public String getAuthorization() {
        return m_sAuthorization;
    }

    public String getMessage() {
        return m_sMessage;
    }
    
    public void paymentError(String sMessage) {
        m_sAuthorization = null;
        m_sMessage = sMessage;
    }    
    
    public void paymentOK(String sAuthorization) {
        m_sAuthorization = sAuthorization;
        m_sMessage = null;
    }  

    public String printCardNumber() {
        // hide start numbers
        if (m_sCardNumber.length() > 4) {
            return "************" + m_sCardNumber.substring(m_sCardNumber.length() - 4);
        } else {
            return "****************";
        }
    }
    public String printExpirationDate() {
        return m_sExpirationDate;
    }
    public String printAuthorization() {
        return m_sAuthorization;
    }
    public String printTransactionID() {
        return m_sTransactionID;
    }

      public String printCardAmount() {
        return Formats.CURRENCY.formatValue(new Double( getTotal()));
    } 
    @Override
    public ChequeDetails getChequeDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
      public void setTotal(Double amt){
          //System.out.println("setTotal:: PaymentInfoMagcard::::"+amt);
//         m_dTotal=BillInfo.stottax+Billpage.interest1;
         //   m_dTotal=amt+Billpage.interest1;
            m_dTotal=amt;
    }

//    @Override
//    public double getTotal1() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setTotal1(Double amt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
