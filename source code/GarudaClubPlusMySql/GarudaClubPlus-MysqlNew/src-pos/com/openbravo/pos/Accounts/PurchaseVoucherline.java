/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public  class PurchaseVoucherline implements SerializableRead{
    private String item;
    private int qty;
    private double rate=0;
    private double amount=0;
    private String taxcat;
    private String taxcatId;
    private double tax=0;
    private double totalAmount=0;
    private double pdttaxvalue=0;
    private String account;
    private String itemid;
    private String purchaseorderref;
    private int oqty;
    private boolean flag=true;
    ///////////////////////////////added by pratima to add tax2 tax3 in table
      private String taxcat2;
      private String taxcat3;
      private double tax2=0;
      private double tax3=0;
      private double pdttaxvalue2=0;
      private double pdttaxvalue3=0;
      private String taxcatId2;
      private String taxcatId3;
      private boolean basic2=true;
      private boolean basic3=true;
      private double totaltax=0;
    
          //////////////////////////////

    public void readValues(DataRead dr) throws BasicException
    {
       /* item=dr.getString(1);
        qty=dr.getInt(2);
        rate=dr.getDouble(3);
        amount=dr.getDouble(4);
        taxcat=dr.getString(5);
        tax=dr.getDouble(6);
        totalAmount=dr.getDouble(7);
        pdttaxvalue=dr.getDouble(8);
        account=dr.getString(9);
        itemid=dr.getString(10);*/
        item=dr.getString(1);
        qty=dr.getInt(2);
        rate=dr.getDouble(3);
        amount=dr.getDouble(4);
        taxcat=dr.getString(9);
        tax=dr.getDouble(5);
     //   totalAmount=amount+tax;
        pdttaxvalue=dr.getDouble(10);
        account=dr.getString(6);
        itemid=dr.getString(7);
        taxcatId=dr.getString(8);
        //////////////////by pratima to add tax2 and tax3 columns in table
        taxcat2=dr.getString(13);
        tax2=dr.getDouble(14);
      if(dr.getDouble(15)!=null)
        pdttaxvalue2=dr.getDouble(15);
        taxcatId2=dr.getString(16);
      
        taxcat3=dr.getString(17);
        tax3=dr.getDouble(18);
        if(dr.getDouble(19)!=null)
        pdttaxvalue3=dr.getDouble(19);
        taxcatId3=dr.getString(20);
         totalAmount=amount+tax+tax2+tax3;
        totaltax=tax+tax2+tax3;
        
        basic2=dr.getBoolean(21);
        basic3=dr.getBoolean(22);
       

        /////////////////////////////////////////////////////
   }
    public PurchaseVoucherline getCopy(){
        PurchaseVoucherline p=new PurchaseVoucherline();
        p.item=item;
        p.qty=qty;
        p.rate=rate;
        p.amount=amount;
        p.taxcat=taxcat;
        p.tax=tax;
        p.totalAmount=totalAmount;
        p.pdttaxvalue=pdttaxvalue;
        p.account=account;
        p.itemid=itemid;
        p.taxcatId=taxcatId;
        p.purchaseorderref = purchaseorderref;
        ////////////////////////////////////////
         p.taxcat2=taxcat2;
         p.taxcat3=taxcat3;
         p.tax2=tax2;
         p.tax3=tax3;
         p.pdttaxvalue2=pdttaxvalue2;
         p.pdttaxvalue3=pdttaxvalue3;
         p.taxcatId2=taxcatId2;
         p.taxcatId3=taxcatId3;
         p.basic2=basic2;
         p.basic3=basic3;   
         p.totaltax=totaltax;
         
        // p.flag=true;//foreditor
/////////////////////////////////////////
        return p;
    }

    public int getOqty() {
        return oqty;
    }

    public void setOqty(int oqty) {
        this.oqty = oqty;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getPurchaseorderref() {
        return purchaseorderref;
    }

    public void setPurchaseorderref(String purchaseorderref) {
        this.purchaseorderref = purchaseorderref;
    }

    public String getTaxcatId() {
        return taxcatId;
    }

    public void setTaxcatId(String taxcatId) {
        this.taxcatId = taxcatId;
    }


    public String getAccount(){
      return account;
    }
    public void setAccount(String acc){
     account=acc;
    }
    public Double getpdttaxvalue(){
      return pdttaxvalue;
    }
    public void setpdttaxvalue(Object tax){
      pdttaxvalue=Double.valueOf(tax.toString());
    }
    public String getitem(){
      return item;
    }
    public String getitemid(){
      return itemid;
    }

    public int getQty(){
       return qty;
    }

    public void setitem( Object name){
        item=name.toString();
    }
    public void setitemid( Object id){
        itemid=id.toString();
    }
     public void setQty( Object Qty){
        qty=Integer.valueOf(Qty.toString());
    }

    public double getRate(){
      return rate;
    }
    public void setRate(Object Rate){
       rate=Double.valueOf(Rate.toString());
    }

    public double getamount(){
      return amount;
    }
    public void setamount(Object amt){
     amount=Double.valueOf(amt.toString());
    }
   public String getTaxcat(){
     return taxcat;
   }
   public void setTaxcat(Object taxcatname){
    taxcat=taxcatname.toString();
   }
   public double getTax(){
    return tax;
   }
    public void setTax(Object taxamt){
      tax=Double.valueOf(taxamt.toString());
    }
    public double getTotalAmount(){
      return totalAmount;
    }
    public void setTotalAmount(Object amt){
     totalAmount=Double.valueOf(amt.toString());
    }
    ///////////////////////////////////////////////////added by pratima to add tax2 and tax3 in table
     public String getTaxcat2(){
     return taxcat2;
   }
   public void setTaxcat2(Object taxcatname){
    taxcat2=taxcatname.toString();
   }
    public String getTaxcat3(){
     return taxcat3;
   }
   public void setTaxcat3(Object taxcatname){
    taxcat3=taxcatname.toString();
   }
     public double getTax2(){
    return tax2;
   }
    public void setTax2(Object taxamt){
      tax2=Double.valueOf(taxamt.toString());
    }
     public double getTax3(){
    return tax3;
   }
    public void setTax3(Object taxamt){
      tax3=Double.valueOf(taxamt.toString());
    }
     public Double getpdttaxvalue2(){
      return pdttaxvalue2;
    }
    public void setpdttaxvalue2(Object tax){
     if(tax!=null)
        pdttaxvalue2=Double.valueOf(tax.toString());
     else pdttaxvalue2=0.00;
    }
     public Double getpdttaxvalue3(){
      return pdttaxvalue3;
    }
    public void setpdttaxvalue3(Object tax){
    if(tax!=null)
        pdttaxvalue3=Double.valueOf(tax.toString());
      else pdttaxvalue3=0.00;
    }
     public String getTaxcatId2() {
        return taxcatId2;
    }

    public void setTaxcatId2(String taxcatId) {
        this.taxcatId2 = taxcatId;
    }
     public String getTaxcatId3() {
        return taxcatId3;
    }

    public void setTaxcatId3(String taxcatId) {
        this.taxcatId3 = taxcatId;
    }
     public boolean isBasic2() {
        return basic2;
    }

    public void setBasic2(boolean basic2) {
        this.basic2 = basic2;
    }
public boolean isBasic3() {
        return basic3;
    }

    public void setBasic3(boolean basic3) {
        this.basic3 = basic3;
    }
    public void setTotalTax(Object  totaltax1){
      totaltax=Double.valueOf( totaltax1.toString());
    }
     public double getTaxTotal(){
    return  totaltax;
   }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
 }
