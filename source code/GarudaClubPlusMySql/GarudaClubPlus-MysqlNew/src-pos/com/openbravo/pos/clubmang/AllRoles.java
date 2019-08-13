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
public class AllRoles implements SerializableRead {
   private String name;
   private String id;
 

  public AllRoles()
  {

  }
  public AllRoles(String name){
      this.name=name;
  }

     @Override
    public String toString(){
      return name;
    }
  public String getName()
    {
       return name;
   }
  public String getId()
    {
       return id;
   }

    public void readValues(DataRead dr) throws BasicException {
        name=dr.getString(1);
        id=dr.getString(2);
        

    }


}
