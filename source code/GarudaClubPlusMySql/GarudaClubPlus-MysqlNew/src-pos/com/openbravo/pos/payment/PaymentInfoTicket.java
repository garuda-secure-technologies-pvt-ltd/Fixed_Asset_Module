

package com.openbravo.pos.payment;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.format.Formats;
import com.openbravo.pos.panels.PaymentsModel;
import static com.openbravo.pos.panels.PaymentsModel.roundTwoDecimals;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;

public class PaymentInfoTicket extends PaymentInfo implements SerializableRead  {
    
    private double m_dTicket;
     private double m_dTicket1;
    private String m_sName;
    private ChequeDetails cd;
    
    /** Creates a new instance of PaymentInfoCash */
    public PaymentInfoTicket(double dTicket, String sName) {
        m_sName = sName;
        m_dTicket =    dTicket;
        BillInfo.totalchequeamt=m_dTicket;
    }

    public PaymentInfoTicket(double dTicket, String sname, ChequeDetails cd) {
        m_sName = sname;
        m_dTicket = dTicket;
        this.cd = cd;
        BillInfo.totalchequeamt=m_dTicket;
    }
    
    public PaymentInfoTicket() {
        m_sName = null;
        m_dTicket = 0.0;
        cd = null;
     }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sName = dr.getString(1);
        m_dTicket = dr.getDouble(2).doubleValue();
    }
    
    public PaymentInfo copyPayment(){
        return new PaymentInfoTicket(m_dTicket, m_sName);
    }

    public ChequeDetails getChequeDetails() {
        return cd;
    }
    public String getName() {
        return m_sName;
    }   
    @SuppressWarnings("empty-statement")
    public double getTotal() {
        return m_dTicket;
    }    
    public Double getOtherCharges(){
        return 0.00;
    }
    
    public String printPaid() {
        return Formats.CURRENCY.formatValue(new Double(m_dTicket));
    }       
    
    // Especificas
    public String printPaperTotal() {
        // En una devolucion hay que cambiar el signo al total
        return Formats.CURRENCY.formatValue(new Double(-m_dTicket));
    }          

    @Override
    public String getTrack3() {
        return "no trans";
    }

    @Override
    public String getTrack2() {
         return "no trans";
    }

    @Override
    public String getTrack1() {
        return "no trans";
    }
  public void setTotal(Double amt){
         m_dTicket=amt;
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
