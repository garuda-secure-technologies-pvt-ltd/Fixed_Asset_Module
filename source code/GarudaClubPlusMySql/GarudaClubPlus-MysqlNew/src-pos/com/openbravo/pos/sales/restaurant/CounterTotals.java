/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales.restaurant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class CounterTotals implements SerializableRead  {
     private String name;
     private Double amount;
   //  private void

    public void readValues(DataRead dr) throws BasicException {
       amount=dr.getDouble(1);
       name=dr.getString(2);
    }
    public String getName(){
      return name;
    }
    public Double getAmount(){
      return amount;
    }
}
