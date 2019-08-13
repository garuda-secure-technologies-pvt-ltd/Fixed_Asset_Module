/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class TokenElements implements SerializableRead{
  private String pdtName="";
  private String pdtID="";
  private double rate=0.0;
  private int qty=0;
  //private double amt=0.0;
  private int line=0;

    public TokenElements() {
    }

    public TokenElements(String pdtName, String pdtID, double rate, int qty) {
        this.pdtName = pdtName;
        this.pdtID = pdtID;
        this.rate = rate;
        this.qty = qty;
        //this.amt = qty*rate;
    }

    public void readValues(DataRead dr) throws BasicException {
        pdtName=dr.getString(1);
        pdtID=dr.getString(2);
        rate=dr.getDouble(3);
        qty=dr.getInt(4);
       // amt=dr.getDouble(5);
    }
    public int getLine(){
       return line;
    }
    public void setLine(int line){
        this.line=line;
    }
    public String getProductName(){
       return pdtName;
    }
    public void setProductName(String name){
       pdtName=name;
    }
    public String getProductID(){
       return pdtID;
    }
    public void setProductID(String id){
       pdtID=id;
    }
    public void setRate(double rate){
        this.rate=rate;
    }
    public double getRate(){
      return rate;
    }
    public void setQty(int qty){
        this.qty=qty;
    }
    public int getQty(){
      return qty;
    }
    //public void setTotalAmount(double amt){
   //     this.amt=amt;
   // }
    public double getTotalAmount(){
      return qty*rate;
    }

    @Override
    public String toString() {
        return pdtName;
    }

}
