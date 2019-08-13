

package com.openbravo.pos.ticket;

import java.io.*;
import com.openbravo.pos.util.StringUtils;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.format.Formats;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppLocal;
import java.util.Properties;

/**
 *
 * @author adrianromero
 */
public class TicketLineInfo implements SerializableWrite, SerializableRead, Serializable {
    
    private static final long serialVersionUID = 10L;
    
    private String m_sTicket;
    private int m_iLine;
    
    private double multiply;    
    private double price;
    
    private TaxInfo tax;
    private Properties attributes;
    private boolean stockCheckRequired;
    private String productid;
    private String warehouse;
    
    private TaxInfo tax2;                                                                                  // edited by aakash
    private TaxInfo tax3;
    
    private Boolean BASIC2;
    private Boolean BASIC3;
    

    /** Creates new TicketLineInfo */   
//     public TicketLineInfo(String productid, double dMultiply, double dPrice, TaxInfo tax, Properties props) {
//        init(productid, dMultiply, dPrice, tax, props);
//    }
//
//    public TicketLineInfo(String productid, double dMultiply, double dPrice, TaxInfo tax) {
//        init(productid, dMultiply, dPrice, tax, new Properties());
//    }
       
//    public TicketLineInfo(String productname, String producttaxcategory, double dMultiply, double dPrice, TaxInfo tax) {
//
//        Properties props = new Properties();
//        props.setProperty("product.name", productname);
//        props.setProperty("product.taxcategoryid", producttaxcategory);
//        init(null, dMultiply, dPrice, tax, props);
//    }
     
     public TicketLineInfo() {
        init(null, 0.0, 0.0, null, new Properties(),false,null,null,null,true,true);
    }
     
    public TicketLineInfo(ProductInfoExt product, double dMultiply, double dPrice, TaxInfo tax, Properties attributes ,TaxInfo tax2 , TaxInfo tax3 , Boolean Basic2 , Boolean Basic3) {                                                     // edited by aakash
        
        String pid;
        
        if (product == null) {
            pid = null;
        } else {
            pid = product.getID();

            attributes.setProperty("product.name", product.getName());
            attributes.setProperty("product.com", product.isCom() ? "true" : "false");
            attributes.setProperty("product.taxcategoryid", product.getTaxCategoryID());
            
            if(product.getTaxCategoryID2()!=null){
                attributes.setProperty("product.taxcategoryid2", product.getTaxCategoryID2()); 
            }
            else{
                attributes.setProperty("product.taxcategoryid2", ""); 
            }
            if(product.getTaxCategoryID3()!=null){
                 attributes.setProperty("product.taxcategoryid3", product.getTaxCategoryID3());
            }
            else{
                 attributes.setProperty("product.taxcategoryid3","");
            }
                                                                                                                                                  // edited by aakash
            attributes.setProperty("product.BASIC2", product.getBASIC2()? "true" : "false");
            attributes.setProperty("product.BASIC3", product.getBASIC3()? "true" : "false");
            Basic2 = product.getBASIC2();
            Basic3 = product.getBASIC3();
                    
                    
            if (product.getCategoryID() != null) {
                attributes.setProperty("product.categoryid", product.getCategoryID());
            }
        }    
        init(pid, dMultiply, dPrice, tax, attributes,product.isIsStockMaintainRequired(),product.getWarehouse() , tax2 , tax3 , Basic2 , Basic3);
    }    
    public TicketLineInfo(ProductInfoExt oProduct, double dPrice, TaxInfo tax, Properties attributes , TaxInfo tax2 , TaxInfo tax3 , Boolean Basic2 , Boolean Basic3) {  
        this(oProduct, 1.0, dPrice, tax, attributes , tax2 , tax3 , Basic2 , Basic3);
        stockCheckRequired=oProduct.isIsStockMaintainRequired();
        warehouse=oProduct.getWarehouse();
    }
        
    public TicketLineInfo(TicketLineInfo line) {  
        init(line.productid, line.multiply, line.price, line.tax, (Properties) line.attributes.clone(),line.isStockCheckRequired(),line.getWarehouse() , line.tax2 , line.tax3 , line.BASIC2 , line.BASIC3);
    }
    
    private void init(String productid, double dMultiply, double dPrice, TaxInfo tax, Properties attributes,boolean stockCheckRequired,String warehouse ,TaxInfo Tax2 , TaxInfo Tax3 , Boolean Basic2 , Boolean Basic3 ) {
        
        this.productid = productid; 
        multiply = dMultiply;
        price = dPrice;
        this.tax = tax;
        this.attributes = attributes;
        this.stockCheckRequired=stockCheckRequired;
        m_sTicket = null;
        m_iLine = -1;
        this.warehouse=warehouse;
        
        
        this.tax2 = Tax2;
        this.tax3 = Tax3;
        this.BASIC2 = Basic2;
        this.BASIC3 = Basic3;
        
    }
     
    void setTicket(String ticket, int line) {
        m_sTicket = ticket;
        m_iLine = line;
    }
    
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, m_sTicket);
        dp.setInt(2, new Integer(m_iLine));
        dp.setString(3, productid);
        
        dp.setDouble(4, new Double(multiply));
        dp.setDouble(5, new Double(price));
        
        dp.setString(6, tax.getId());
        try {
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            attributes.storeToXML(o, AppLocal.APP_NAME, "UTF-8");
            dp.setBytes(7, o.toByteArray()); 
        } catch (IOException e) {
            dp.setBytes(7, null);
        } 
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sTicket = dr.getString(1);
        m_iLine = dr.getInt(2).intValue();        
        productid = dr.getString(3);
        
        multiply = dr.getDouble(4);       
        price = dr.getDouble(5);
        
        tax = new TaxInfo(dr.getString(6), dr.getString(7), dr.getString(8), dr.getString(9), dr.getString(10), dr.getDouble(11), dr.getBoolean(12), dr.getInt(13));
        attributes = new Properties();
        try {
            byte[] img = dr.getBytes(14);
            if (img != null) {
                attributes.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
        }         
    }
    
    public TicketLineInfo copyTicketLine() {
        TicketLineInfo l = new TicketLineInfo();
        // l.m_sTicket = null;
        // l.m_iLine = -1;
        l.productid = productid;
        l.multiply = multiply;    
        l.price = price;
        l.tax = tax;   
        l.attributes = (Properties) attributes.clone();
        l.stockCheckRequired=stockCheckRequired;
        l.warehouse=warehouse;
        return l;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
    
    public int getTicketLine() {
        return m_iLine;
    }
    
    public String getProductID() {
        return productid;
    }
    
    public String getProductName() {
        return attributes.getProperty("product.name");
    }
    
    public boolean isProductCom() {
        return "true".equals(attributes.getProperty("product.com"));
    }
    
    public String getProductTaxCategoryID() {
        return (attributes.getProperty("product.taxcategoryid"));
    }
    
    
    public String getProductTaxCategoryID2() {                                                                           // edited by aakash
        return (attributes.getProperty("product.taxcategoryid2"));
    }
    
    public String getProductTaxCategoryID3() {                                                                          // edited by aakash
        return (attributes.getProperty("product.taxcategoryid3"));
    }
    
    public boolean getBasic2() {
        return "true".equals(attributes.getProperty("product.BASIC2"));
    }
    
    public boolean getBasic3() {
        return "true".equals(attributes.getProperty("product.BASIC3"));
    }
    
    
    
    public String getProductCategoryID() {
        return (attributes.getProperty("product.categoryid"));
    }    
    
    
    
    
    
    public double getMultiply() {
        return multiply;
    }
    
    public void setMultiply(double dValue) {
        multiply = dValue;  
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double dValue) {
        price = dValue;
    }    
    
    public double getPriceTax() {
        return price * (1.0 + getTaxRate());
    }
    
    public void setPriceTax(double dValue) {
        price = dValue / (1.0 + getTaxRate());
    }
    
    public TaxInfo getTaxInfo() {
        return tax;
    }    
    
    public void setTaxInfo(TaxInfo value) {
        tax = value;
    }
    
    public String getProperty(String key) {
        return attributes.getProperty(key);
    }
    
    public String getProperty(String key, String defaultvalue) {
        return attributes.getProperty(key, defaultvalue);
    }
    
    public void setProperty(String key, String value) {
        attributes.setProperty(key, value);
    }
    
    public Properties getProperties() {
        return attributes;
    }    
    
    public double getTaxRate() {                                                                      // edited by aakash
        Double Tax1Rate = 0.00;
        Double Tax2Rate = 0.00; 
        Double Tax3Rate = 0.00;
        Double TotalTaxRate = 0.00;
        
        
        Tax1Rate = tax.getRate();
        TotalTaxRate = TotalTaxRate+Tax1Rate;
        
        if(tax2!=null){
           if(BASIC2){
                Tax2Rate = tax2.getRate();
                TotalTaxRate = TotalTaxRate+Tax2Rate;
           } 
           else{
                Double d1 = tax.getRate()*100;
                Double d2 = tax2.getRate()*100;
                Double d = (d2/100)*(100+d1);
                
                Tax2Rate = d/100;
                TotalTaxRate = TotalTaxRate+Tax2Rate;
               
           }
            
          
            
        }
        
        if(tax3!=null){
            
            if(BASIC3){
              Tax3Rate = tax3.getRate();
              TotalTaxRate = TotalTaxRate+Tax3Rate;  
            }
            else{
               Double d1 = (tax.getRate()+tax2.getRate())*100;
               Double d2 = tax3.getRate()*100;
               Double d = (d2/100)*(100+d1);
                
               Tax2Rate = d/100;
               TotalTaxRate = TotalTaxRate+Tax2Rate;
                
            }
            
            
        }
        
        
        return tax == null ? 0.0 : TotalTaxRate;
    }
    
    
    
    public double getSubValue() {
        return price * multiply;
    }
    
    public double getTax() {
        return price * multiply * getTaxRate();
    }

    public double getValue() {
        return price;
    }
    
    public String printName() {
         return StringUtils.encodeXML(attributes.getProperty("product.name"));
    }
    
    public String printMultiply() {
        return Formats.DOUBLE.formatValue(multiply);
    }
    
    public String printPrice() {
        return Formats.CURRENCY.formatValue(getPrice());
    }  
       
    public String printPriceTax() {
        return Formats.CURRENCY.formatValue(getPriceTax());
    }   
    
    public String printTax() {
        return Formats.CURRENCY.formatValue(getTax());
    }
    
    public String printTaxRate() {
        return Formats.PERCENT.formatValue(getTaxRate());        
    }  
    
    public String printSubValue() {
        return Formats.CURRENCY.formatValue(getSubValue());
    }
    
    public String printValue() {
        return Formats.CURRENCY.formatValue(getValue());
    }

    public String printRemarks() {
        String temp=attributes.getProperty("qt.remarks");
        if(temp==null)
            return " ";
        else
          return temp;
    }
     public String getRemarks() {
        return attributes.getProperty("qt.remarks");
    }

    public void setRemarks(String value) {
        attributes.setProperty("qt.remarks", value);
    }

    public boolean isStockCheckRequired() {
        return stockCheckRequired;
    }

    public void setStockCheckRequired(boolean stockCheckRequired) {
        this.stockCheckRequired = stockCheckRequired;
    }


}
