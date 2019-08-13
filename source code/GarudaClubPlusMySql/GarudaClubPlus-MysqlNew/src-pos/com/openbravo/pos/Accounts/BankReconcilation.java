/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author user
 */
public class BankReconcilation implements SerializableRead{
    private String ID;
    private String NAME;
     private String sign;
     private String type_;
    private String a;
   
    public BankReconcilation()
     {
         
    }

    public  BankReconcilation(String a)
  {
      this.a=a;
  }
    
    public Object getname() {
     return NAME;
    }
    
     @Override
    public String toString(){
      return NAME;
    }
     
  public Object getName()
    {
       return a;
   }

    public void readValues(DataRead dr) throws BasicException {
          NAME = dr.getString(1);
    }
}
