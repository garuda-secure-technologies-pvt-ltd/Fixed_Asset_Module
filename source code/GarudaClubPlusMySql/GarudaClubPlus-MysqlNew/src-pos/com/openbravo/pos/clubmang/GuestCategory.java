/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class GuestCategory implements SerializableRead, IKeyed {
    private String id;
    private String name;
    private int days;
    private int maxguest;
    private Double rate;
    private Boolean echeck;
    private String receiptseq;
    private int maxreceiptseq;
    private String createdby;
    private Date crdate;
    private String deactby;
    private Date deactdate;
    private String account;
    private Boolean kcheck;
    private String TaxCategory;
    private String TaxCategory2;
    private String TaxCategory3;
    private int AllowTabBilling;
    private String hsncode;
    private Double TotalAmount;
     private Boolean cascade1;
      private Boolean cascade2;
       private Boolean basic1;
        private Boolean basic2;
    
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        name=dr.getString(2);
        hsncode=dr.getString(3);
        days=dr.getInt(4);
        maxguest=dr.getInt(5);
        rate=dr.getDouble(6);
        echeck=dr.getBoolean(7);
        receiptseq=dr.getString(8);
        maxreceiptseq=dr.getInt(9);
        createdby=dr.getString(10);
        crdate=dr.getTimestamp(11);
        deactby=dr.getString(12);
        deactdate=dr.getTimestamp(13);
        account=dr.getString(14);
        
        
        
        
        try
        {
        if(dr.getBoolean(15)!=null)
        {
            kcheck = dr.getBoolean(15);
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        TaxCategory=dr.getString(16); 
        
        AllowTabBilling = dr.getInt(17);
         TaxCategory2=dr.getString(18);
          TaxCategory3=dr.getString(19);
        cascade1=dr.getBoolean(20);
         cascade2=dr.getBoolean(21);
         basic1=dr.getBoolean(22);
           basic2=dr.getBoolean(23);
         
        
     }

    public String getAccount() {
        return account;
    }

    public Boolean getKcheck() {
        return kcheck;
    }

    public void setKcheck(Boolean kcheck) {
        this.kcheck = kcheck;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getCreatedBy(){
        return createdby;
    }
    public String getDeactivatedBy(){
        return deactby;
    }
    public Date getCrdate(){
     return crdate;
    }
    public Date getDeactDate(){
     return deactdate;
    }
    public String getReceiptSeq(){
      return receiptseq;
     }
    public int getMaxReceiptSeq(){
      return maxreceiptseq;
    }

    public Boolean getecheck(){
         return echeck;
    }
     public String toString(){
        return name;
    }

     public String getname(){
        return name;
    }

    public Object getKey() {
        return id;
    }
    public int getdays(){
      return days;
    }
    public int getmaxguest(){
       return maxguest;
    }
    public Double getrate(){
        return rate;
    }  
    public Double getTotalAmount(){
        return TotalAmount;
    }
     public String getid() {
        return id;
    }
     
     
    public void setTaxCategory(String TaxCategory){
        this.TaxCategory=TaxCategory;
    }
    public String getTaxCategory(){
        return TaxCategory;
    }
    
     public void setTaxCategory2(String TaxCategory2){
        this.TaxCategory2=TaxCategory2;
    }
    public String getTaxCategory2(){
        return TaxCategory2;
    }
    
     public void setTaxCategory3(String TaxCategory3){
        this.TaxCategory3=TaxCategory3;
    }
    public String getTaxCategory3(){
        return TaxCategory3;
    }
    
    public int getAllowTabBilling(){
        return AllowTabBilling;
    }
    public void SetAllowTabBilling(int AllowTabBilling){
        this.AllowTabBilling=AllowTabBilling;
    }
     ////Added by guru

    public String getHsncode() {
        return hsncode;
    }   
     public Boolean getcascade1() {
//         System.out.println("cascade1::::::"+cascade1);
        return cascade1;
    }

    public void setcascade1(Boolean cascade1) {
        this.cascade1 = cascade1;
    }
  public Boolean getcascade2() {
//       System.out.println("cascade2::::::"+cascade2);
        return cascade2;
    }

    public void setcascade2(Boolean cascade2) {
        this.cascade2 = cascade2;
    } 
    
      public Boolean getbasic1() {
//           System.out.println("basic1::::::"+basic1);
        return basic1;
    }

    public void setbasic1(Boolean basic1) {
        this.basic1 = basic1;
    }
  public Boolean getbasic2() {
//       System.out.println("basic2::::::"+basic2);
        return basic2;
    }

    public void setbasic2(Boolean basic2) {
        this.basic2 = basic2;
    }

    
}
