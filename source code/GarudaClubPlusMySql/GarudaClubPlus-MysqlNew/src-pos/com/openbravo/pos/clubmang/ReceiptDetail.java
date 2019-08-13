/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.format.Formats;
import com.openbravo.pos.util.StringUtils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class ReceiptDetail implements SerializableRead{
    private String fname;
    private Double balamount;// date;
    private String payref;
    private Double oamt;
    private String rid;
    private Double amt;
    private String narration;
    
    public void readValues(DataRead dr) throws BasicException {
         balamount=dr.getDouble(1);
         fname=dr.getString(2);
         payref=dr.getString(3);
         oamt=dr.getDouble(4);
         rid=dr.getString(5);
          narration=dr.getString(6);
         if(payref!=null && rid !=null){
           String[] temparr=payref.split(" : ");
           for(int i=0;i<temparr.length;i++){
             String[] arr=temparr[i].toString().split(" # ");
             if(arr.length>1){
               if(arr[0].toString().equals(rid)){
                //if(arr.length>1)
                    amt=Double.valueOf(arr[1]);
                    break;
               }
             }else
                   amt=0.0;
           }
         }
        
    }
    public void setBalAmount(Double d){
      balamount=d;
    }
    public String getBalAmount(){
      return roundTwoDecimals(balamount);
    }
    public String getFname(){
      return fname;
    }
    public void setFname(String name){
      fname=name;
    }
    public String getPayref(){
      return payref;
    }
    public String getOrgAmount(){
      return roundTwoDecimals(oamt);
    }
    public void setOrgAmount(Double amt){
      oamt=amt;
    }
    public String getAmount(){
      return roundTwoDecimals(amt);
    }
    public Double getAmount1(){
      return amt;
    }
    public void setAmount(Double amount){
      amt=amount;
    }
    public String getNarration(){
      return StringUtils.encodeXML(narration);
    }
    public void setNarration(String nar){
      narration=nar;
    }
    public String roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#,##0.00");
		return twoDForm.format(d);
    }
}
