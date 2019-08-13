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
public  class AdditionalEntry implements SerializableRead{
       private String name;
       private double amount=0.0;
       private String account;
       private String parentskey;
       private String transtype;
       private String narration;
     /*   public AdditionalEntry(){
          account=new javax.swing.JComboBox();
          amount=0.0;
        }*/
        public void readValues(DataRead dr) throws BasicException {
            name=dr.getString(1);
            amount=dr.getDouble(2);
            account=dr.getString(3);
            parentskey=dr.getString(4);
            transtype=dr.getString(5);
            narration=dr.getString(6);
        }
        public AdditionalEntry getCopy(){
            AdditionalEntry a=new AdditionalEntry();
            a.name=name;
            a.amount=amount;
            a.account=account;
            a.parentskey=parentskey;
            a.transtype=transtype;
            a.narration=narration;
            return a;
        }
        public String getNarration(){
          return narration;
        }
        public String getTransType(){
          return transtype;
        }
        public String getParentSearchKey(){
           return parentskey;
        }
        public String getname(){
          return name;
        }
        public void setname(Object name){
         this.name=name.toString();
        }
        public double getamount(){
          return amount;
        }
        public void setamount(Object amt){
          amount=Double.parseDouble(amt.toString());
        }
        public String getAccount(){
          return account;
        }
        public void setAccount(Object acc){
          account=acc.toString();
        }

}
