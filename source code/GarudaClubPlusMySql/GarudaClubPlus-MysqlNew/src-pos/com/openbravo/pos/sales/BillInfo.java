/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;
import java.util.Date;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.sales.restaurant.BillList;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author swathi
 */
public class BillInfo implements SerializableRead{

    private String ID;
    private CustomerInfoExt Customer;
    private String waiter;
    private String place;
    private String floor;
    private String createdby;
    private double amount;
    private Date createddate;
    private boolean paid;
    private String receiptref;
    private String m_sActiveCash; //Stored in RECEIPTS
    //warehouse changes - start
    private String warehouse;
    //warehouse changes - end
    //praveen:initiator changes---start
    private String initiator;
    //praveen:initiator changes---end
    private Double tax;

    private List <BillLineInfo> lines;

    private List<PaymentInfo> payments;
    private PaymentInfo payments1;
    private List<TicketTaxInfo> taxes;
    
    public static double gt;
    public static double stottax=0.00;
    private AppView app1;
    private double taxamtg;
    public static double amttaxg;
    public static double totalcash;
    public static double totalcardamt;
    public static double totalchequeamt;
    public static double totalcardOtherCharge;

   
    //private double m_dIntrest=Billpage.interest1;
    public BillInfo() {

    }
    
    
    

    public void readValues(DataRead dr) throws BasicException {
        ID = dr.getString(1);
        Customer = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(dr.getString(2));
        waiter = dr.getString(3);
        place = dr.getString(4);
        floor = dr.getString(5);
        amount = dr.getDouble(6);
        createdby = dr.getString(7);
        createddate = dr.getTimestamp(8);
        paid = dr.getBoolean(9);
        receiptref = dr.getString(10);
        //warehouse changes - start
        warehouse = dr.getString(11);
        //warehouse changes - end
        //praveen:initiator changes---start
        initiator = dr.getString(12);
        //praveen:initiator changes---end
        tax = dr.getDouble(13);
    }

    public void writeValues (DataWrite dp) throws BasicException {
        dp.setString(1, ID);
        dp.setString(2, getCustomerId());
        dp.setString(3, waiter);
        dp.setString(4, place);
        dp.setString(5, floor);
        dp.setDouble(6, amount);
        dp.setString(7, createdby);
        dp.setTimestamp(8, createddate);
        dp.setBoolean(9, paid);
        dp.setString(10, receiptref);
        //warehouse changes - start
        dp.setString(11, warehouse);
        //warehouse changes - end
         //praveen:initiator changes---start
        dp.setString(12, initiator);
         //praveen:initiator changes---end

    }




    public String getID() {
        return ID;
    }

    public void setID(String value) {
        ID = value;
    }

    public CustomerInfoExt getCustomer() {
        return Customer;
    }

    public void setCustomer(CustomerInfoExt value) {
        Customer = value;

    }
    public String getCustomerId() {
        return Customer != null ? Customer.getId() : null;
    }
    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String value) {
        waiter = value;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String value) {
        place = value;
    }
    public String getFloor() {
        return floor;
    }

    public void setFloor(String value) {
        floor = value;
    }

    public String getCreatedBy() {
        return createdby;
    }

    public void setCreatedBy(String value) {
        createdby = value;
    }

    public double getAmount() {
        return amount;
    }

    public double getAmountPlusTax() {
        //stottax=amount+tax;
        return amount+tax;
    }

    public void setAmount(double value) {
        amount = value;
    }

    public Date getCreatedDate() {
        return createddate;
    }

    public void setCreatedDate(Date value) {
        createddate = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean value) {
        paid = value;
    }

    public String getReceiptRef() {
        return receiptref;
    }

    public void setReceiptRef(String value) {
        receiptref = value;
    }

    public List<BillLineInfo> getLines() {
        return lines;
    }

    public void setLines(List<BillLineInfo> l) {
        lines = l;
    }

    public BillLineInfo getLine(int index){
        return lines != null ? lines.get(index) : null;
    }


    public int getLinesCount() {
        return lines != null ? lines.size() : 0;
    }


    public void addLine(BillLineInfo oLine) {

       oLine.setTicket(this, lines.size());
       lines.add(oLine);
    }

    public void insertLine(int index, BillLineInfo oLine) {
        lines.add(index, oLine);
        refreshLines();
    }

    public void setLine(int index, BillLineInfo oLine) {
        oLine.setTicket(this, index);
         lines.set(index, oLine);
    }

    public void removeLine(int index) {
       lines.remove(index);
        refreshLines();
    }

    public void refreshLines() {
        for (int i = 0; i < lines.size(); i++) {
            getLine(i).setTicket(this, i);
        }
    }

    public List<PaymentInfo> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentInfo> l) {
        payments = l;
    }

    public void resetPayments() {
        payments = new ArrayList<PaymentInfo>();
    }

    public List<TicketTaxInfo> getTaxes() {
        
        return taxes;
        
    }

    public void setTaxes(List<TicketTaxInfo> l) {
        taxes = l;
               

    }

    public void resetTaxes() {
        taxes = null;
    }

    public TicketTaxInfo getTaxLine(TaxInfo tax) {
        
        for (TicketTaxInfo taxline : taxes) {
            if (tax.getId().equals(taxline.getTaxInfo().getId())) {
                return taxline;
            }
        }

        return new TicketTaxInfo(tax);
    }
    
    
    /////////////////////////////////////////////////////shiv
    public String getTaxId() {
       String s=null;
       
        if (hasTaxesCalculated()) {
            
            for (TicketTaxInfo tax : taxes) {
               s=tax.getTaxInfo().getId();
               
            }
            
        } else {
            
            if(lines!=null){
                
            for (BillLineInfo line : lines) {
               // sum += line.getTax(); 
            }
            }
        }
        return s;
    
    }
    ///////////////////////////////////////////////////////
    


    public boolean hasTaxesCalculated() {
        return taxes != null;
    }

    public double getTax() {
        
        double sum = 0.0;
        if (hasTaxesCalculated()) {
            for (TicketTaxInfo tax : taxes) {
                sum += tax.getTax();
                
                
                
            }
        } else {
            if(lines!=null){
            for (BillLineInfo line : lines) {
                sum += line.getTax();
            }
            }
        }
        return sum;
    }

    public double getSubTotal() {
        double sum = 0.0;
      /*  for (BillLineInfo line : lines) {
            sum += line.getSubValue();
        }*/
        return getAmount();
       // return sum;
    }

    public double getTotal() {
        stottax=getSubTotal() + getTax();
        return getSubTotal() + getTax();

    }
    //Added by GG
    public double getTotal1() {
//        stottax=getSubTotal() + getTax();
//        return getSubTotal() + getTax();
//System.out.println("taxxxxxx "+BillList.taxamt1);
     //   stottax=getSubTotal() + BillList.taxamt1;
        System.out.println("BillList.taxamt1:: "+BillList.taxamt1);
        return getSubTotal() + BillList.taxamt1;
    }
    public double getGrandTotal() {
        gt=getTotal()+Billpage.interest1;
        return getTotal()+Billpage.interest1;
    }
    public double getGrandTotal1() {
        //gt=getTotal1()+Billpage.interest1;
        return getTotal1()+Billpage.interest1;
    }
     public String printIntrest() {
        return Formats.CURRENCY.formatValue(new Double(Billpage.interest1));
    } 
     public double getInterestg(){

    return Billpage.interest1;
    }
    public void setActiveCash(String value) {
        m_sActiveCash = value;
    }
    public String getActiveCash() {
        return m_sActiveCash;
    }
    public double getArticlesCount() {
        double dArticles = 0.0;

        for (BillLineInfo oLine : lines) {
            dArticles += oLine.getMultiply();
        }

        return dArticles;
    }
    public double getTotalPaid() {

        double sum = 0.0;
        for (PaymentInfo p : payments) {
            if (!"debtpaid".equals(p.getName())) {
                sum += p.getTotal();
            }
        }
        return sum;
    }
      public String printHost()
    {

     return    LookupUtilityImpl.getInstance(null).getAppView().getProperties().getHost();
    }
    public String printRole()
    {
        String x = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        return LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString();
    }


    //For printing
    public String printId() {
        return getID();
    }

    public String printReceiptID () {
        return getReceiptRef();
    }
    
    public String printDate() {
        return Formats.TIMESTAMP.formatValue(createddate);
    }
    public String printUser() {
        return createdby;
    }

    public String printReceiptUser() {
        return LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
    }
    public String printCustomer() {
        return getCustomer().getName();
    }
    public String printArticlesCount() {
        return Formats.DOUBLE.formatValue(new Double(getArticlesCount()));
    }

    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }
    public String printTax() {
       return Formats.CURRENCY.formatValue(new Double(getTax()));
       //return Formats.CURRENCY.formatValue(new Double(BillList.taxamt1));
    }
   public String printTax1() {
//      // return Formats.CURRENCY.formatValue(new Double(getTax()));
     return Formats.CURRENCY.formatValue(new Double(BillList.taxamt1));
   }
    public String printTotal() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
    public String printTotal1() {
        return Formats.CURRENCY.formatValue(new Double(getTotal1()));
    }
    //Added by GG
    public String printGrandTotal() {
        return Formats.CURRENCY.formatValue(new Double(getGrandTotal1()));
    }
    public String printOtherChargesID() {
        String intRefID1=null;
        if(Billpage.interest1==0.0)
        {
            intRefID1="";
        }
        else{
            intRefID1=JPaymentSelect.intRefID;
        }
        JPaymentSelect.intRefID=null;
        return intRefID1;
    }
    public String printTotalPaid() {
        return Formats.CURRENCY.formatValue(new Double(getTotalPaid()));
    }
//warehouse changes - start
    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
//warehouse changes - end
 //praveen:initiator changes---start
    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
     //praveen:initiator changes---end
//     public double getTaxamtg() {
//        // System.out.println("billinfo taxamtttt:: "+taxamtg);
//        return taxamtg;
//    }
//
//    public void setTaxamtg(double taxamtg) {
//        //System.out.println("billinfo taxamtttt:11: "+taxamtg);
//        this.taxamtg = taxamtg;
//        //System.out.println("billinfo taxamtttt:11: "+this.taxamtg);
//    }
    public PaymentInfo getPayments1() {
        return payments1;
    }

    public void setPayments1(PaymentInfo l) {
        payments1 = l;
    }
//    public double getAmountt() {
//        return payments1.;
//    }
}
