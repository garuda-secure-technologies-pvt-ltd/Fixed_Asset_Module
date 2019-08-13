    

package com.openbravo.pos.ticket;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.util.RoundUtils;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TicketTaxInfo {
    
    private TaxInfo tax;
    
    private double subtotal;
    private double taxtotal;
       private AppView appView;
    /** Creates a new instance of TicketTaxInfo */
    public TicketTaxInfo(TaxInfo tax) {
        this.tax = tax;
        
        subtotal = 0.0;
        taxtotal = 0.0;
    }
    
   
    
    
    
    public TaxInfo getTaxInfo() {
        return tax;
    }
    
    public void add(double dValue) {
        subtotal += dValue;
        
        tax.getId();
        
        ////////////////////////////////shiv
        AppView app=LookupUtilityImpl.getInstance(null).getAppView();
        try {
            Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(tax.getId());
                   
                   taxtotal = RoundUtils.round(subtotal * tax.getRate());
                    double str=taxtotal;
                    if(obj[0].equals("yes")){
                        Object f= new Float(Math.round(str));
                        String st= f.toString();
                         taxtotal=new Double(st);
                   //     Double tax = new Double(st);
                    }else if(obj[0].equals("yesnearest")){
                        Object f= new Float(Math.round(str));
                        String st= f.toString();
                         taxtotal=new Double(st);
                     //   Double tax = new Double(st);
                    }else if(obj[0].equals("yesnext")){
                      Object f= new Float(Math.round(str)+1);
                        String st= f.toString();
                        taxtotal=new Double(st);
                     //   Double tax = new Double(st);
                    }else if(obj[0].equals("yesprevious")){
                        Object f= new Float(Math.round(str)-1);
                        String st= f.toString();
                         taxtotal=new Double(st);
                       
                     //   Double tax = new Double(st);
                    }else{
                        taxtotal = RoundUtils.round(subtotal * tax.getRate());
                    }
                    
                    
                    
                    
                    
                    
                    
                    
        } catch (BasicException ex) {
            Logger.getLogger(TicketTaxInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
       /////////////////////////////////////////////////////shiv     
    }
    
    public double getSubTotal() {    
        return subtotal;
    }
    
    public double getTax() {       
        return taxtotal;
    }
    
    public double getTotal() {         
        return subtotal + taxtotal;
    }
    
    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }
    public String printTax() {
        return Formats.CURRENCY.formatValue(new Double(getTax()));
    }    
    public String printTotal() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }    
}
