/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author dev3
 */
public class DataSourceForListOfVerifiedFA implements JRDataSource {
    private int m_nIdx;
    private List<VerifiedListOfAssetsTable_Model.AssetInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
    public DataSourceForListOfVerifiedFA(List<VerifiedListOfAssetsTable_Model.AssetInfo> v) {
       m_nIdx = -1;
		this.v = v;
       
        
    }

   

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        VerifiedListOfAssetsTable_Model.AssetInfo curr = v.get(m_nIdx);

        if (curr == null) 
            return null;
        
         else if (sName.equals("Sno")) 
              o = m_nIdx+1;
         
        if (sName.equals("Name")) 
               
            o = curr.getName();
        
        else if (sName.equals("Barcode")) 

            o = curr.getBarcode();

        
         else if (sName.equals("SubHead")) 

            o = curr.getMajcls();

         else if (sName.equals("SubsidiaryHead")) 
                
            o = curr.getSubHead();
                
            

         else if (sName.equals("AccountHead")) 
            
            o = curr.getAccHead();
            

         else if (sName.equals("Vendor")) 

            o = curr.getVendor();

         else if (sName.equals("PurchaseDate")) 
              
            o = curr.getPurchaseDate();
                
            

         else if (sName.equals("Totalcost")) 

            o = decimalFormat.format(curr.getcost());

         else if (sName.equals("Agency")) 

            o = curr.getAgency();

         else if (sName.equals("InstalletionDate")) 

            o = curr.getInstDate();

          else if (sName.equals("CommisionDate")) 
            
            o = curr.getCommDate();
            

        else if (sName.equals("PutToUseDate")) 
                
            o = curr.getUseDate();
        else if (sName.equals("CapitalizationDate")) 
                
            o = curr.getCptDate();
         else if (sName.equals("DeprecationRate")) 
                
            o = decimalFormat.format(curr.getrod());
                    
        else if (sName.equals("WDV")) 
                
            o = decimalFormat.format(curr.getwdv());
         else if (sName.equals("ReplacementCost")) 
                
            o = decimalFormat.format(curr.getcor());
         else if (sName.equals("Standalone")) 
                
            o = curr.getstand_alone();
        else if (sName.equals("Asset")) 
                
            o = curr.getAsset();
          else if (sName.equals("ScannedDoc")) 
                
            o = curr.getScadoc();
                   

        return o;

    }
    
    
    
}
