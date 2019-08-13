/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.ProductInfo;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
/**
 *
 * @author swathi
 */
public class BillLineInfostd1 implements SerializableWrite, SerializableRead, Serializable{

    private String m_id;
    private String m_parentid;
    private ProductInfo m_product;
    private int m_multiply;
    private double m_rate;
    private double m_amount;
    private int m_iLine;
    private Properties attributes = new Properties();
    private transient TaxInfo tax;
    private boolean disStatus=false;
    
    private transient TaxInfo tax2;                                                                                 // edited by aakash
    private transient TaxInfo tax3;
    private boolean Basic2=true;
    private boolean Basic3=true;
    
    private String gtax1id;         //Added by guru
    private double gtax1;
    private String gtax2id;
    private boolean gtax2cas;
    private double gtax2;
    private String gtax3id;
    private boolean gtax3cas;
    private double gtax3;
    static String gttax1id=null;
    static double gttax1=0.0; 
    static String gttax2id=null;
     static double gttax2=0.0; 
    static String gttax3id=null;
     static double gttax3=0.0; 
    
    
    public BillLineInfostd1(String id, String parentid, ProductInfo product, int multiply, double rate,double amount) {
        m_id=id;
        m_parentid = parentid;
        setProduct(product);
        m_multiply = multiply;
        m_rate = rate;
        m_iLine = -1;
        m_amount = amount;
        gtax1id=tax.getId();
        gtax1=getGtax1();
        gtax2id=getGtax2id();
        gtax2cas=true;
        gtax2=getGtax2();
        gtax3id=getGtax3id();
        gtax3cas=true;
        gtax3=getGtax3();
        
       }//or init???

    public BillLineInfostd1() {
        m_id = UUID.randomUUID().toString();
        disStatus=false;
    }

    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, m_id);
        dp.setInt(2, m_iLine);
        dp.setString(3, m_parentid);
        dp.setString(4, m_product.getID());
        dp.setInt(5, m_multiply);
        dp.setDouble(6, m_rate);
        try {
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            attributes.storeToXML(o, AppLocal.APP_NAME, "UTF-8");
            dp.setBytes(7, o.toByteArray());
        } catch (IOException e) {
            dp.setBytes(7, null);
        }
        dp.setDouble(8, m_amount);
        dp.setString(9,getGtax1id());
        dp.setDouble(10,getGtax1());
        dp.setString(11,getGtax2id());
        dp.setBoolean(12, getGtax2cas());
        dp.setDouble(13,getGtax2());
        dp.setString(14,getGtax3id());
        dp.setBoolean(15, getGtax3cas());
        dp.setDouble(16,getGtax3());
//      
    }

    public void readValues(DataRead dr) throws BasicException {
        m_id = dr.getString(1);
         m_iLine = dr.getInt(2);
         m_parentid = dr.getString(3);
         m_product = LookupUtilityImpl.getInstance(null).getProductsMap().get(dr.getString(4));
         m_multiply = dr.getInt(5);
         m_rate = dr.getDouble(6);
        attributes = new Properties();
        try {
            byte[] img = dr.getBytes(7);
            if (img != null) {
                attributes.loadFromXML(new ByteArrayInputStream(img));
            }
            m_amount=dr.getDouble(8);
        } catch (IOException e) {
        }
        
    }

    public BillLineInfostd1 copybill() {
        BillLineInfostd1 t = new BillLineInfostd1();
        t.m_id = m_id;
        t.m_iLine = m_iLine;
        t.m_parentid = m_parentid;
        t.m_product = m_product;
        t.m_multiply = m_multiply;
        t.m_rate = m_rate;
        t.attributes = (Properties) attributes.clone();
        t.m_amount=m_amount;
          t.gtax1id=getGtax1id();
        t.gtax1=getGtax1();
        t.gtax2id=getGtax2id();
        t.gtax2cas=getGtax2cas();
        t.gtax2=getGtax2();
        t.gtax3id=getGtax3id();
        t.gtax3cas=getGtax3cas();
        t.gtax3=getGtax3();
        return t;
    }
   public boolean getDiscountStatus(){
     return disStatus;
   }
   public void setDiscountStatus(boolean status){
     disStatus=status;
   }
    public void setAmount(Double amt)
    {
        m_amount=amt;
    }
    public double getamount()
    {
       return m_amount;
    }
    public String getID() {
        return m_id;
    }

    public void setID(String value) {
        m_id = value;
    }
    public String getParentid() {
        return m_parentid;
    }

    public void setParentid(String value) {
        m_parentid = value;
    }

    private String getProductID() {
        return m_product.getID();
    }

    public ProductInfo getProduct() {
        return m_product;
    }

   //Added by guru for tax insertion to billitem table
    public String getGtax1id() {
        return gtax1id;
    }

    public void setGtax1id(String gtax1id) {
        this.gtax1id = gtax1id;
    }

    public double getGtax1() {
        return gtax1;
    }

    public void setGtax1(double gtax1) {
        this.gtax1 = gtax1;
    }

    public String getGtax2id() {
        return gtax2id;
    }

    public void setGtax2id(String gtax2id) {
        this.gtax2id = gtax2id;
    }

    public boolean getGtax2cas() {
        return gtax2cas;
    }

    public void setGtax2cas(boolean gtax2cas) {
        this.gtax2cas = gtax2cas;
    }

    public double getGtax2() {
        return gtax2;
    }

    public void setGtax2(double gtax2) {
        this.gtax2 = gtax2;
    }

    public String getGtax3id() {
        return gtax3id;
    }

    public void setGtax3id(String gtax3id) {
        this.gtax3id = gtax3id;
    }

    public boolean getGtax3cas() {
        return gtax3cas;
    }

    public void setGtax3cas(boolean gtax3cas) {
        this.gtax3cas = gtax3cas;
    }

    public double getGtax3() {
        return gtax3;
    }

    private void refreshAttributes() {
        attributes.setProperty("product.name", m_product.getName());
        attributes.setProperty("product.com", m_product.isCom() ? "true" : "false");
        attributes.setProperty("product.taxcategoryid", m_product.getTaxCategoryID());
        
        
        if(m_product.getTaxCategoryID2()!=null){                                                                                    // edited by aakash 
            attributes.setProperty("product.taxcategoryid2", m_product.getTaxCategoryID2());
        }
        else{
            attributes.setProperty("product.taxcategoryid2", "");
        }
        
        if(m_product.getTaxCategoryID3()!=null){
            attributes.setProperty("product.taxcategoryid3", m_product.getTaxCategoryID3());
        }
        else{
            attributes.setProperty("product.taxcategoryid3", "");
        }
        
         attributes.setProperty("product.BASIC2", m_product.getBASIC2()? "true" : "false");
         attributes.setProperty("product.BASIC3", m_product.getBASIC3()? "true" : "false");
        
        
        
        
        if (m_product.getCategoryID() != null) {
            attributes.setProperty("product.categoryid", m_product.getCategoryID());
        }
    }

    public void setProduct(ProductInfo value) {
        m_product = value;
        refreshAttributes();
    }

    public int getMultiply() {
        return m_multiply;
    }

    public void setMultiply(int value){
        m_multiply = value;
    }

    public double getRate() {
        return m_rate;
    }

    public void setRate(double value) {
        m_rate = value;
    }

    void setTicket(BillInfostd1 parentid, int line) {
        m_parentid = parentid.getID();
        m_iLine = line;
        if (tax == null) {
            tax = LookupUtilityImpl.getInstance(null).getTaxInfo(getProduct().getTaxCategoryInfo(), parentid.getCustomer());
        }
        
        
        if(tax2==null){
             tax2 = LookupUtilityImpl.getInstance(null).getTaxInfo(getProduct().getTaxCategoryInfo2(), parentid.getCustomer());
        }
        if(tax3==null){
             tax3 = LookupUtilityImpl.getInstance(null).getTaxInfo(getProduct().getTaxCategoryInfo3(), parentid.getCustomer());
        }
        
        Basic2 = getProduct().getBASIC2();
        Basic3 = getProduct().getBASIC3();
        
        
        
        
        
    }

    public TaxInfo getTaxInfo() {
        return tax;
    }

    public void setTaxInfo(TaxInfo tax) {
        this.tax=tax;
    }

    
    
     public TaxInfo getTaxInfo2() {
        return tax2;
    }

    public void setTaxInfo2(TaxInfo tax2) {
        this.tax2=tax2;
    }
    
    
    
    public TaxInfo getTaxInfo3() {
        return tax3;
    }

    public void setTaxInfo3(TaxInfo tax3) {
        this.tax3=tax3;
    }
    
    
    
    public double getSubValue() {
           return m_rate * m_multiply;
       //// return m_amount;
    }

    public double getTaxRate() {
       Double Tax1Rate = 0.00;
        Double Tax2Rate = 0.00;
        Double Tax3Rate = 0.00;
        Double TotalTaxRate = 0.00;
        
        
        Tax1Rate = tax.getRate();
        TotalTaxRate = TotalTaxRate+Tax1Rate;
        
        if(tax2!=null){
          if(Basic2){
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
            
            if(Basic3){
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

    public double getTax() {
        return m_amount * getTaxRate();
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

    public double getValue() {
        return m_rate * (1.0 + getTaxRate());
    }



    //For printing
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
    public String getProductTaxCategoryID3() {
        return (attributes.getProperty("product.taxcategoryid3"));
    }
    
    
    public Boolean getProductBasic2(){
         if(attributes.getProperty("product.BASIC2").equals("false") || attributes.getProperty("product.BASIC2").equals("true"))
         {
             return  "true".equals(attributes.getProperty("product.BASIC2"));
         }
         else{
             return true;
         }
        
    }
    
    public Boolean getProductBasic3(){
        if(attributes.getProperty("product.BASIC3").equals("false") || attributes.getProperty("product.BASIC3").equals("true"))
         {
             return  "true".equals(attributes.getProperty("product.BASIC3"));
         }
         else{
             return true;
         }
    }
    
    
    
    
    
    
    

    public String getProductCategoryID() {
        return (attributes.getProperty("product.categoryid"));
    }
    public String printName() {
        if(attributes.getProperty("product.name")==null)
            return StringUtils.encodeXML(m_product.getName());
        else
         return StringUtils.encodeXML(attributes.getProperty("product.name"));
    }

    public String printMultiply() {
        return Formats.DOUBLE.formatValue(getMultiply());
    }

    public String printPrice() {
        return Formats.CURRENCY.formatValue(getRate());
    }

    public String printPriceTax() {
        return Formats.CURRENCY.formatValue(getTaxRate());
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

    public String printAmount() {
        return Formats.CURRENCY.formatValue(getamount());
    }
    
     public String printamount() {
        return Formats.CURRENCY.formatValue(getamount());
    }
    
    

    public String printValue() {
        return Formats.CURRENCY.formatValue(getValue());
    }

 /*   public String printBilledQty(){

        double temp=getamount()/getRate();
        if(((int)temp)==getMultiply() || (getamount()%getRate())!=0.0)
            return "";
        else
        return " B("+Formats.DOUBLE.formatValue(temp)+")";
    }
    public Double getBilledQty(){
        double temp=getamount()/getRate();
        return temp;
    }

    public String printBilledPrice(){
        double temp=getamount()/getMultiply();
        if((temp)==getRate() || (getamount()%getMultiply())!=0.0)
            return "";
        else
        return " B("+Formats.DOUBLE.formatValue(temp)+")";
    }
    public Double getBilledPrice(){
        double temp=getamount()/getMultiply();
        return temp;
    }*/

}
