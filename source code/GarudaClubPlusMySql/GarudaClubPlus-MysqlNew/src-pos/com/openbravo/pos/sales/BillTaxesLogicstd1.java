

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author adrianromero
 */
public class BillTaxesLogicstd1 {
     protected BillLogicstd1 dlBillstd1;

    private List<TaxInfo> taxlist;
    
    private Map<String, TaxesLogicElement> taxtrees;
    
    public BillTaxesLogicstd1(List<TaxInfo> taxlist) {
        this.taxlist = taxlist;
      
        taxtrees = new HashMap<String, TaxesLogicElement>();
                
        // Order the taxlist by Application Order...
        List<TaxInfo> taxlistordered = new ArrayList<TaxInfo>();
        taxlistordered.addAll(taxlist);
        Collections.sort(taxlistordered, new Comparator<TaxInfo>() {
            public int compare(TaxInfo o1, TaxInfo o2) {
                if (o1.getApplicationOrder() < o2.getApplicationOrder()) {
                    return -1;
                } else if (o1.getApplicationOrder() == o2.getApplicationOrder()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        
        // Generate the taxtrees
        HashMap<String, TaxesLogicElement> taxorphans = new HashMap<String, TaxesLogicElement>();
        
        for (TaxInfo t : taxlistordered) {
                       
            TaxesLogicElement te = new TaxesLogicElement(t);
            
            // get the parent
            TaxesLogicElement teparent = taxtrees.get(t.getParentID());
            if (teparent == null) {
                // orphan node
                teparent = taxorphans.get(t.getParentID());
                if (teparent == null) {
                    teparent = new TaxesLogicElement(null);
                    taxorphans.put(t.getParentID(), teparent);
                } 
            } 
            
            teparent.getSons().add(te);

            // Does it have orphans ?
            teparent = taxorphans.get(t.getId());
            if (teparent != null) {
                // get all the sons
                te.getSons().addAll(teparent.getSons());
                // remove the orphans
                taxorphans.remove(t.getId());
            }          
            
            // Add it to the tree...
            taxtrees.put(t.getId(), te);
        }
    }
    
    public void calculateTaxes(BillInfostd1 ticket) throws BasicException {
            
        List<TicketTaxInfo> tickettaxes = new ArrayList<TicketTaxInfo>(); 
        if (ticket.getLines() == null) {
            BillLogicstd1 bl = getDataLogicBillstd();
            ticket.setLines(bl.getBillLineListstd(ticket.getID()));
            ticket.refreshLines();
        }
        for (BillLineInfostd1 line: ticket.getLines()) {
            tickettaxes = sumLineTaxes(tickettaxes, calculateTaxes(line));
        }
        ticket.setTaxes(tickettaxes);
    }
    
    public List<TicketTaxInfo> calculateTaxes(BillLineInfostd1 line) {
        
        TaxesLogicElement taxesapplied = getTaxesApplied(line.getTaxInfo());
        
        if(line.getTaxInfo2()!=null){
          TaxesLogicElement  taxesapplied2 = getTaxesApplied(line.getTaxInfo2());
       
        }
        if(line.getTaxInfo3()!=null){
            //taxesapplied = getTaxesApplied(line.getTaxInfo3());
        }
        
        
        
        return calculateLineTaxes(line.getSubValue(), taxesapplied , line);
    }
    
    private List<TicketTaxInfo> calculateLineTaxes(double base, TaxesLogicElement taxesapplied ,BillLineInfostd1 line ) {
 
        List<TicketTaxInfo> linetaxes = new ArrayList<TicketTaxInfo>();
     
       Double GTotal = 0.00;
       GTotal = base;
       Double Tax1 = 0.00;
       Double Tax2=0.00;
       Double Tax3=0.00;
       
       
       
        if (taxesapplied.getSons().isEmpty()) {           
            TicketTaxInfo tickettax = new TicketTaxInfo(taxesapplied.getTax());
            
            tickettax.add(base);
            linetaxes.add(tickettax);
            System.out.println("Base : "+base);
            
            Tax1 = tickettax.getTax();
            System.out.println("Tax1 :"+Tax1);
            
          // edited by aakashhhhh ------------------------------------------------  
            if(line.getTaxInfo2()!=null){
               
                if(line.getProductBasic2()){
                
                    
                    TaxesLogicElement  taxesapplied2 = getTaxesApplied(line.getTaxInfo2());
                    TicketTaxInfo tickettax2 = new TicketTaxInfo(taxesapplied2.getTax());
                    Tax2 = tickettax2.getTax();
                    tickettax2.add(base);
                    linetaxes.add(tickettax2);
                }
                else{
                    
                    GTotal = GTotal + (GTotal * tickettax.getTax()/100);
                    
                    TaxesLogicElement  taxesapplied2 = getTaxesApplied(line.getTaxInfo2());
                    TicketTaxInfo tickettax2 = new TicketTaxInfo(taxesapplied2.getTax());
                    Tax2 = tickettax2.getTax();
                     System.out.println("Tax2 :"+Tax2);
                    tickettax2.add(GTotal);
                    linetaxes.add(tickettax2);
                    System.out.println("Total 2 " +GTotal);
                    
                }
             }
            if(line.getTaxInfo3()!=null){
                
                if(line.getProductBasic3()){
                
                        TaxesLogicElement  taxesapplied3 = getTaxesApplied(line.getTaxInfo3());
                        TicketTaxInfo tickettax3 = new TicketTaxInfo(taxesapplied3.getTax());
                        tickettax3.add(base);
                        linetaxes.add(tickettax3);
                }
                else{
                    
                    
                        TaxesLogicElement  taxesapplied2 = getTaxesApplied(line.getTaxInfo2());
                        TicketTaxInfo tickettax2 = new TicketTaxInfo(taxesapplied2.getTax());
                        Tax2 = tickettax2.getTax();
                        tickettax2.add(base);
                        
                        
                         GTotal = GTotal + (GTotal * tickettax2.getTax()/100)  ;
                        
                        
                        TaxesLogicElement  taxesapplied3 = getTaxesApplied(line.getTaxInfo3());
                        TicketTaxInfo tickettax3 = new TicketTaxInfo(taxesapplied3.getTax());
                        Tax3 = tickettax3.getTax();
                         System.out.println("Tax3 :"+Tax3);
                        tickettax3.add(GTotal);
                        linetaxes.add(tickettax3);
                    
                }
            }
          // edited by aakashhhhh--------------------------------------------------------- 
            
            
        } else {
            double acum = base;
            
            for (TaxesLogicElement te : taxesapplied.getSons()) {
                
                
                
                List<TicketTaxInfo> sublinetaxes = calculateLineTaxes(
                        te.getTax().isCascade() ? acum : base, 
                        te , line);
               
               // TicketTaxInfo tickettax = new TicketTaxInfo(te.getTax());
               // tickettax.add(base);
               // linetaxes.add(tickettax);
                
                
                //linetaxes.addAll(sublinetaxes);
                acum += sumTaxes(sublinetaxes);
            }
        }
        
        return linetaxes;        
    }
    
    private TaxesLogicElement getTaxesApplied(TaxInfo t) {
        return taxtrees.get(t.getId());
    }
        
    private double sumTaxes(List<TicketTaxInfo> linetaxes) {
        
        double taxtotal = 0.0;
        
        for (TicketTaxInfo tickettax : linetaxes) {
            taxtotal += tickettax.getTax();
            
        }
        return  taxtotal;
    }
    
    private List<TicketTaxInfo> sumLineTaxes(List<TicketTaxInfo> list1, List<TicketTaxInfo> list2) {
     
        for (TicketTaxInfo tickettax : list2) {
            TicketTaxInfo i = searchTicketTax(list1, tickettax.getTaxInfo().getId());
            if (i == null){
                list1.add(tickettax);
            } else {
                i.add(tickettax.getSubTotal());
            }
        }
        return list1;
    }
    
    private TicketTaxInfo searchTicketTax(List<TicketTaxInfo> l, String id) {
        
        for (TicketTaxInfo tickettax : l) {
            if (id.equals(tickettax.getTaxInfo().getId())) {
                return tickettax;
            }
        }    
        return null;
    }
    
    public double getTaxRate(String tcid) {
        return getTaxRate(tcid, null);
    }
    
    public double getTaxRate(TaxCategoryInfo tc) {
        return getTaxRate(tc, null);
    }
    
    public double getTaxRate(TaxCategoryInfo tc, CustomerInfoExt customer) {
        
        if (tc == null) {
            return 0.0;
        } else {
            return getTaxRate(tc.getID(), customer);          
        }
    }
    
    public double getTaxRate(String tcid, CustomerInfoExt customer) {
        
        if (tcid == null) {
            return 0.0;
        } else {
            TaxInfo tax = getTaxInfo(tcid, customer);
            if (tax == null) {
                return 0.0;
            } else {
                return tax.getRate();
            }            
        }
    }
    
    public TaxInfo getTaxInfo(String tcid) {
        return getTaxInfo(tcid, null);
    }
    
    public TaxInfo getTaxInfo(TaxCategoryInfo tc) {
        return getTaxInfo(tc.getID(), null);
    }
    
    public TaxInfo getTaxInfo(TaxCategoryInfo tc, CustomerInfoExt customer) {  
        return getTaxInfo(tc.getID(), customer);
    }    
    
    public TaxInfo getTaxInfo(String tcid, CustomerInfoExt customer) {
        
        
        TaxInfo defaulttax = null;
        
        for (TaxInfo tax : taxlist) {
            if (tax.getParentID() == null && tax.getTaxCategoryID().equals(tcid)) {
                if ((customer == null || customer.getTaxCustCategoryID() == null) && tax.getTaxCustCategoryID() == null) {
                    return tax;
                } else if (customer != null && customer.getTaxCustCategoryID() != null && customer.getTaxCustCategoryID().equals(tax.getTaxCustCategoryID())) {
                    return tax;
                }
                
                if (tax.getTaxCustCategoryID() == null) {
                    defaulttax = tax;
                }
            }
        }
        
        // No tax found
        return defaulttax;
    }
    
    
    ///////////////shiv:////////////////////////////////////
    
     public BillLogicstd1 getDataLogicBillstd() {
        return dlBillstd1;
    }
    ////////////////////////////////////////////////////////
    
    
    
    
    
    
}
