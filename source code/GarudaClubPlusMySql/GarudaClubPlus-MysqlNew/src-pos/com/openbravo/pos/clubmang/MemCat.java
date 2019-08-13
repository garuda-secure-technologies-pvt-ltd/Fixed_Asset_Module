/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;

/**
 *
 * @author swathi
 */
public class MemCat implements SerializableRead,IKeyed,SerializableWrite {
    private String name;
    private String id;
    private String a;
    public MemCat()
    {
        
    }
    public MemCat(String a){
        this.a=a;
    }

     public Object getMemberCategory() {
     return name;
    }
    @Override
    public String toString(){
      return name;
    }
    public String getID(){
      return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

   public void readValues(DataRead dr) throws BasicException {
       name=dr.getString(1);
       id=dr.getString(2);
      
    }

    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1,name);
        dp.setString(2,id);
    }

    public Object getKey() {
       return id;
    }

}
