/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class AccountAudit implements SerializableRead, IKeyed,Comparable{
    private String Name;
    private String Searchkey;
    private String id;
    private String parent;
    private double debit;
    private double credit;
    private boolean active;
    private String sign;
    private boolean summary;
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        Name=dr.getString(2);
        Searchkey=dr.getString(3);
        parent=dr.getString(4);
        if(parent==null)
            parent="";
        /*double temp=dr.getDouble(5);
        if(temp>0){
          debit=temp;
          credit=0.0;
        }else{
          credit=temp * -1;
          debit=0.0;
        }*/
         debit=dr.getDouble(5);
         credit=dr.getDouble(6);
         active=dr.getBoolean(7);
         sign=dr.getString(8);
         summary=dr.getBoolean(9);
    }
      @Override
    public String toString(){
        return Name + "  "+Searchkey;
    }
    public String getSign(){
        return sign;
    }
    public boolean getStatus(){
      return active;
    }
    public boolean getSummary(){
      return summary;
    }
    public String getid(){
     return id;
    }
     public String getSerachkey(){
     return Searchkey;
    }
       public Object getKey() {
       return id;
    }
    public String getName(){
      return Name;
    }
     public String getparent(){
     return parent;
    }
     public double getDebit(){
        return debit;
     }
     public double getCredit(){
       return credit;
     }
     public void setName(String name){
         Name=name;
     }
     public void setID(String id){
         this.id=id;
     }
     public void setSearchKey(String skey){
         Searchkey=skey;
     }
     public void setDebitandCredit(double debit,double credit){
         this.debit=debit;
         this.credit=credit;
     }

    public int compareTo(Object o) {
        if(o instanceof AccountAudit){
            AccountAudit t=(AccountAudit) o;
            return this.getName().compareTo(t.getName());
        }else
            return 0;
    }
    
}

