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
public class MemType implements SerializableRead,IKeyed,SerializableWrite {
   private String id;
   private String name;
    MemType(){
        
    }

    public Object getKey() {
       return id;
    }
    @Override
    public String toString(){
      return name;
    }
    public String getid(){
      return id;
    }
    public String getname(){
      return name;
    }

    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
       name=dr.getString(2);
    }

    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, id);
        dp.setString(2, name);
    }

}

