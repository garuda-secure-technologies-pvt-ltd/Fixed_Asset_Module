

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import java.text.DecimalFormat;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import com.openbravo.pos.Accounts.DaywiseSalesReportTableModel;
import net.sf.jasperreports.engine.JRField;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class DataSourceForDaywiseSaleReport implements JRDataSource  {
    
    
     private int m_nIdx;
     private List<DaywiseSalesReportTableModel.SalesInfo> v;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
     private String RPH;
     private AppView m_App;
     
     
     public boolean next() throws JRException {
        m_nIdx++;
       return (m_nIdx < v.size());
    }
    
     public DataSourceForDaywiseSaleReport(List<DaywiseSalesReportTableModel.SalesInfo> v , String Header , AppView m_App)   {
		m_nIdx = -1;
		this.v = v;
                this.RPH = Header;
                this.m_App=m_App;
     }
    
     
     
      public Double GrandTotTax=0.00;
      public Double GrandTotAmt = 0.00;
      public int TotalBills = 0;
      public Object getFieldValue(JRField jrf) throws JRException {
        Object o = null;
       
       String sName = jrf.getName();
       
       DaywiseSalesReportTableModel.SalesInfo curr = v.get(m_nIdx);
       
        if (curr== null)
	   return null;
        
          
               
               
        
                else if(sName.equals("CATEGORY"))
                     o = curr.getWAREHOUSE();

               
                else if (sName.equals("BILLCOUNT"))
                {   
                        try{  
                          String WarehouseName = curr.getWAREHOUSE();
                          String WareHouseID = getWareHouseID(m_App, WarehouseName);
                          String TaxName = curr.getTAXCATNAME();
                          String TaxID = getTaxID(m_App, TaxName);
                          
                          int Count = 0;
                          if(curr.getWAREHOUSE().equals("Grand Total")){
                            Count = GetTaxCountByTAXID(m_App,TaxID);
                          }
                          else{
                            Count = getWarehouseWiseCountForID(m_App, WareHouseID , TaxID);
                          }
                          o = Count;
                        }
                        catch(BasicException ex){
                                  Logger.getLogger(DataSourceForDaywiseSaleReport.class.getName()).log(Level.SEVERE, null, ex);             
                                  ex.printStackTrace();
                                  new MessageInf(ex).show(new JFrame());
                        }
                }
        
                else if(sName.equals("TAXCAT")) {
                     o = curr.getTAXCATNAME();
                }
                else if(sName.equals("PVALUE")) {
                     o = curr.getPRODSALEAMT();
                }                  
                else if(sName.equals("TAXAMT")) {
                     o = curr.getTAXTOTAL();
                }   
               
                
                else if(sName.equals("ActualBillAmt")) {
                   if(curr.getWAREHOUSE().equals("Grand Total")){
                       o=curr.getGrandBillTotal();
                   }
                   else{
                        o = curr.getActualBillAmt();
                   }
                   
                 }  
        
                else if(sName.equals("ActualTAXAMT")) {
                   if(curr.getWAREHOUSE().equals("Grand Total")){ 
                        o=curr.getGrandTaxTotal();
                   }
                   else{
                        o = curr.getActualTaxAmount();
                   }
                }  
        
                
                
                else if(sName.equals("otherBillAmt")) {
                     o = curr.getotherBillAmt();
                }  
        
                else if(sName.equals("otherTaxAmt")) {
                    
                    o = curr.getotherTaxAmt();
                    
                }  
        
                else if(sName.equals("HEADER")) {
                     o = RPH;
                }  
        
        
                else if(sName.equals("TOTALBILLS")) {
                     try{ 
                    
                        if(curr.getWAREHOUSE().equals("Grand Total")){
                            int TotalCount = getTotalBillsCount(m_App, sName);
                            o=TotalCount;
                        }   
                        else{
                            o = curr.getTotalInvoice();
                        }
                    
                   }
                  catch(BasicException ex){
                       Logger.getLogger(DataSourceForDaywiseSaleReport.class.getName()).log(Level.SEVERE, null, ex);             
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
                }  
        
        
       return o;
    }
   
      
      
      
      
      
      
// get warehouse id by name 
    public String getWareHouseID(AppView app , String warehouse) throws BasicException{
       
       Object o = null;
       String t = null;
        
       
           o  = new StaticSentence(app.getSession(), "select ID from locations where name=? " ,new SerializerWriteBasic(new Datas[]{Datas.STRING   }), SerializerReadString.INSTANCE).find(new Object[]{warehouse } );

         if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
   }
  
 // get TaxId from tax Name ..... 
    
    
   public String getTaxID(AppView app , String taxName) throws BasicException{
       
       Object o = null;
       String t = null;
        
       
           o  = new StaticSentence(app.getSession(), "select ID from taxes where name=? " ,new SerializerWriteBasic(new Datas[]{Datas.STRING   }), SerializerReadString.INSTANCE).find(new Object[]{taxName } );

         if(o!=null){
            t = o.toString();
            return t;
        }
        else{
            return t;
        }
   }
    
    
    
    
    
// get count for bills from taxsalereport by warehouse id  
    
    
   public int getWarehouseWiseCountForID(AppView app , String warehouseID , String TaxID) throws BasicException{
       
        Object o = null;
        int t = -1;
                o  = new StaticSentence(app.getSession(), " SELECT COUNT(*) FROM TAXSALEREPORT WHERE AMOUNT>=0 AND WAREHOUSE=? AND TAXID=?  GROUP BY WAREHOUSE " ,new SerializerWriteBasic(new Datas[]{ Datas.STRING  , Datas.STRING }), SerializerReadString.INSTANCE).find(new Object[]{ warehouseID , TaxID } );

         if(o!=null){
            t = Integer.parseInt(o.toString());
            return t;
          }
        else{
            return -1;
        }
   }
    
      
      
   
// get tax wise count from tax sale report    
   
       
   public int GetTaxCountByTAXID(AppView app , String TaxID) throws BasicException{
       
        Object o = null;
        int t = -1;
                o  = new StaticSentence(app.getSession(), " SELECT COUNT(*) FROM TAXSALEREPORT WHERE AMOUNT>=0 AND  TAXID=?  GROUP BY TAXID " ,new SerializerWriteBasic(new Datas[]{ Datas.STRING   }), SerializerReadString.INSTANCE).find(new Object[]{  TaxID } );

         if(o!=null){
            t = Integer.parseInt(o.toString());
            return t;
          }
        else{
            return -1;
        }
   }
    
      
           
   public int getTotalBillsCount(AppView app , String TaxID) throws BasicException{
       
        int Count=0;
        List<Object> Bill_ID_List = new ArrayList<Object>();
          
        Bill_ID_List  = new StaticSentence(app.getSession(), "  select totalbills from taxsalereport where amount>=0 group by warehouse " ,new SerializerWriteBasic(new Datas[]{ Datas.STRING }), SerializerReadString.INSTANCE).list(new Object[]{TaxID } );
        
        
        for(int i=0;i<Bill_ID_List.size();i++){
            String s = Bill_ID_List.get(i).toString();
            int t = Integer.parseInt(s);
            Count = Count+t;
        }
       return Count;
       
   }  
      
      
      
    
}
