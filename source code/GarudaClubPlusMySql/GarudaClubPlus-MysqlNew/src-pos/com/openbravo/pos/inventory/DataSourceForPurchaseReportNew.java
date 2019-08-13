
package com.openbravo.pos.inventory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForPurchaseReportNew implements JRDataSource{
    
    private int m_nIdx;
    private List<PurchaseJournalReportNewTableModel.PurchaseInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
     
     
    public DataSourceForPurchaseReportNew() {
        this(new ArrayList<PurchaseJournalReportNewTableModel.PurchaseInfo>());
    }

    public DataSourceForPurchaseReportNew(List<PurchaseJournalReportNewTableModel.PurchaseInfo> v) {
        m_nIdx = -1;
       this.v=v;
    }
    
    @Override
    public boolean next() throws JRException {
         m_nIdx++;
        return (m_nIdx < v.size());
    }

    
     @Override
    public Object getFieldValue(JRField field) throws JRException {
          Object o=null;

        String sName = field.getName();

        PurchaseJournalReportNewTableModel.PurchaseInfo curr = v.get(m_nIdx);


        if (curr==null) {
         return null;
        }
      

        //if (sName.equals("SRNO")) {
       //     o =m_nIdx+1;
       // } 
        
         
          if (sName.equals("INVOICENO")) {
          if(curr.getVENDORNAME().equals("Total")){
             o="";
          }
          else{
              o = curr.getINVOICENO();
          }
           
        } 
       
          if (sName.equals("VENDOR")) {
          
            
          
              o = curr.getVENDORNAME();
         
            
        } 
         if (sName.equals("TINNO")) {
         if(curr.getVENDORNAME().equals("Total")){
             o="";
          }
          else{
             o = curr.getTINNO();
          }
        } 
        else  if (sName.equals("AMOUNT")) {
          //  if(curr.getQTY()==0.00){
          //      o = "Amount";
          //  }
          //  else{
                o = curr.getTOTAL()+"";
          //  }
        } 
        else  if (sName.equals("TAXAMOUNT")) {
            //if(curr.getQTY()==0.00){
           //     o = "Tax Amount";
          //  }
           // else{
                o = curr.getTAXTOTAL()+"";
         //   }
        } 
        else  if (sName.equals("TAXPERC")) {
            if(curr.getTaxPerc()==98.00 || curr.getTaxPerc()==99.00){
                o = "";
           }
            else{
                o = curr.getTaxPerc()+" %";
           }
          
        } 
         else if (sName.equals("TOTALAMOUNT")) {
            // if(curr.getQTY()==0.00){
            //    o ="Total Amount";
           //  }
           //  else{
                  o = decimalFormat.format(curr.getTAXTOTAL()+curr.getTOTAL())+"";
           //  }
        } 
        else if (sName.equals("CRDATE")) {
          if(curr.getTaxPerc()==99.00){
              o = "";
          }
          else{
               o = curr.getCRDATE();
          }
          
          
        }
        
        
        return o;
    }
    
    
    
}
