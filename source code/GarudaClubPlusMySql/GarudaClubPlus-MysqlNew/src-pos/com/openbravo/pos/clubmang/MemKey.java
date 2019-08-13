/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
//import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class MemKey implements SerializableRead {
   private String key;
   private String a;
   
  public MemKey()
  {

  }
  public MemKey(String a)
  {
      this.a=a;
  }
    public Object getKey() {
     return key;
    }
     @Override
    public String toString(){
      return key;
    }
  public Object getName()
    {
       return a;
   }

    public void readValues(DataRead dr) throws BasicException {
        key=dr.getString(1);

    }

}
