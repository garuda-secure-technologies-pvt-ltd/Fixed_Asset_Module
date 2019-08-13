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
public class RPData implements SerializableRead{
    private String id;
    private String name;
    private double amount;
    private String type;
    public void readValues(DataRead dr) throws BasicException {
        name=dr.getString(1);
        id=dr.getString(2);
        amount=dr.getDouble(3);
        type=dr.getString(4);
    }

    public String getNAme(){
      return name;
    }
     public String getID(){
      return id;
    }
     public String getType(){
       return type;
     }
     public double getAmount(){
        return amount;
     }
}
