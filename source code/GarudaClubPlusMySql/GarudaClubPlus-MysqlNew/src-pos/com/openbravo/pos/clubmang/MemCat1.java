/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class MemCat1 implements SerializableRead {
    private String memtype;

     public Object getMemberCategory1() {
     return null;
    }
    @Override
    public String toString(){
      return null;
    }
   public void readValues(DataRead dr) throws BasicException {
       memtype=dr.getString(1);
    }

}