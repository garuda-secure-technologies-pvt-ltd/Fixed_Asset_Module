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
public class DataSourceForBarcodeDataSheet implements JRDataSource{
    
    private int m_nIdx;
    private List<BarcodeTableModel.AssetInfo> v;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
    public DataSourceForBarcodeDataSheet(List<BarcodeTableModel.AssetInfo> v) {
       m_nIdx = -1;
		this.v = v;
       
        
    }

   

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        BarcodeTableModel.AssetInfo curr = v.get(m_nIdx);

       
         if (sName.equals("Barcode")) 
          o = curr.getBarcode();
            else if (sName.equals("name")) 
               o = curr.getName();
            else if(sName.equals("location"))
                    o=curr.getLocation();

         
        return o;

    }
    
    
    
}
