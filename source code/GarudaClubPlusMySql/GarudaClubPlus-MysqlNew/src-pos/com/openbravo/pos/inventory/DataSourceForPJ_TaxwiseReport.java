
package com.openbravo.pos.inventory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForPJ_TaxwiseReport  implements JRDataSource{
    
    private int m_nIdx;
    private List<PJ_taxwiseReportTableModel.PurchaseInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
    
    
     public DataSourceForPJ_TaxwiseReport() {
        this(new ArrayList<PJ_taxwiseReportTableModel.PurchaseInfo>());
    }

    public DataSourceForPJ_TaxwiseReport(List<PJ_taxwiseReportTableModel.PurchaseInfo> v) {
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

        PJ_taxwiseReportTableModel.PurchaseInfo curr = v.get(m_nIdx);


        if (curr==null) {
         return null;
        }
      

        //if (sName.equals("SRNO")) {
       //     o =m_nIdx+1;
       // } 
        
         
          if (sName.equals("INVOICENO")) {
              o=curr.getINVOICENO();
          } 
       
          if (sName.equals("TAX1")) {
              
             o=(curr.getTAX1());
             
          } 
          if (sName.equals("TAX2")) {
             o=(curr.getTAX2());
          } 
          if (sName.equals("TAX3")) {
             o=(curr.getTAX3());
          } 
          if (sName.equals("TAX4")) {
             o=(curr.getTAX4());
          } 
          if (sName.equals("TAX5")) {
             o=(curr.getTAX5());
          } 
          if (sName.equals("AMT1")) {
             o=(curr.getAMT1());
          } 
          if (sName.equals("AMT2")) {
             o=(curr.getAMT2());
          } 
          if (sName.equals("AMT3")) {
             o=(curr.getAMT3());
          } 
          if (sName.equals("AMT4")) {
             o=(curr.getAMT4());
          } 
          if (sName.equals("AMT5")) {
             o=(curr.getAMT5());
          } 
          if (sName.equals("ADDCHRG")) {
             o=(curr.getADDCHRG());
          } 
         if (sName.equals("TOTAL")) {
             o=(curr.getTOTAL());
          } 
         if (sName.equals("DATE")) {
             o= (curr.getCrdate());
          } 
        if (sName.equals("VENDOR")) {
             o= (curr.getVendor());
          } 
        if (sName.equals("TINNO")) {
             o= (curr.getTinno());
          } 
        if (sName.equals("OTHERTAX")) {
             o= ((curr.GETOTHERTAX()));
          } 
         if (sName.equals("OTHERAMT")) {
             o= ((curr.getOTHERAMT()));
          } 
         
         
        return o;
    }
    
    
    
    
}
