/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;

/**
 *
 * @author swathi
 */
public class MemCat2 implements SerializableRead {
    private String name;

    private String a;
    public MemCat2()
    {

    }
    public MemCat2(String a){
        this.a=a;
    }

     public Object getMemberCategory() {
     return name;
    }
    @Override
    public String toString(){
      return name;
    }

   public void readValues(DataRead dr) throws BasicException {
       name=dr.getString(1);


    }

  

}
