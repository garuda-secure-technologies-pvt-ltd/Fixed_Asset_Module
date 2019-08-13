/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class Vendor implements SerializableRead,IKeyed{
   private String id;
   private String name;
   private String account;
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        name=dr.getString(2);
        account=dr.getString(3);
    }

    public Object getKey() {
        return id;
    }
    @Override
    public String toString(){
      return name;
    }
    public String getAccount(){
      return account;
    }
    public void setName(String value){
     name=value;
    }
      public String getName(){
      return name;
    }
}
