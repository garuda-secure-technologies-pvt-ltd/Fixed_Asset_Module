/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;
import com.openbravo.format.Formats;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import com.openbravo.pos.Library.BookListDetails;

/**
 *
 * @author dev3
 */
public class DataSourceForListOfFADetail implements JRDataSource {
    private int m_nIdx;
    private List<ListOfAllAssetsTableModel.AssetInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");
      DecimalFormat df = new DecimalFormat("#.00%");
    @Override
    public boolean next() throws JRException {
        
        
         m_nIdx++;
        return (m_nIdx < v.size());
        
    }
    
    
    public DataSourceForListOfFADetail(List<ListOfAllAssetsTableModel.AssetInfo> v) {
       m_nIdx = -1;
		this.v = v;
       
        
    }

   

    public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
        String sName = jrf.getName();

        ListOfAllAssetsTableModel.AssetInfo curr = v.get(m_nIdx);

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
              if(curr.getPurchaseDate()!=null){
            o = Formats.TIMESTAMP.formatValue(curr.getPurchaseDate());
              }else{
              o = curr.getPurchaseDate();
              
              }
                
            

         else if (sName.equals("Totalcost")) 
             if(curr.getcost()!=null){
            o = decimalFormat.format(curr.getcost());}
             else{
              o = curr.getcost();
             }

         else if (sName.equals("Agency")) 

            o = curr.getAgency();

         else if (sName.equals("InstalletionDate")) 
             if(curr.getInstDate()!=null){
            o = Formats.TIMESTAMP.formatValue(curr.getInstDate());}
             else{
             o = curr.getInstDate();
           
             }

          else if (sName.equals("CommisionDate")) 
            if(curr.getCommDate()!=null){
            o = Formats.TIMESTAMP.formatValue(curr.getCommDate());}
            else{
            o = curr.getCommDate();
            }
            

        else if (sName.equals("PutToUseDate")) 
                if(curr.getUseDate()!=null){
            o = Formats.TIMESTAMP.formatValue(curr.getUseDate());}
                else{
                o = curr.getUseDate();
                
                }
                
        else if (sName.equals("CapitalizationDate")) 
                if(curr.getCptDate()!=null){
            o = Formats.TIMESTAMP.formatValue(curr.getCptDate());}
                else{
               o=curr.getCptDate(); 
                }
        
        
        
         else if (sName.equals("DeprecationRate")) 
               if(curr.getrod()!=null){ 
                   String x=decimalFormat.format(curr.getrod());
            o = x+"%";}
               else{
               o=curr.getrod();
               }
                    
        else if (sName.equals("WDV")) 
                if(curr.getwdv()!=null){
            o = decimalFormat.format(curr.getwdv());}
                else{
              o= curr.getwdv(); 
                }
         else if (sName.equals("ReplacementCost")) 
                if(curr.getcor()!=null){
            o = decimalFormat.format(curr.getcor());}
                else{
                o=curr.getcor();
                }
         else if (sName.equals("Standalone")) 
                
            o = curr.getstand_alone();
        else if (sName.equals("Asset")) 
                
            o = curr.getAsset();
          else if (sName.equals("ScannedDoc")) 
                
            o = curr.getScadoc();
                   

        return o;

    }
    
    
    
}
