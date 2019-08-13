/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author dev1
 */
class BookCat implements SerializableRead{
     private String cat;
   private String a;
   public BookCat()
  {

  }
  public BookCat(String a)
  {
      this.a=a;
  }
    public Object getCat() {
     return cat;
    }
     @Override
    public String toString(){
      return cat;
    }
  public Object getName()
    {
       return a;
   }

    

    @Override
    public void readValues(DataRead dr) throws BasicException {
        cat=dr.getString(1);//To change body of generated methods, choose Tools | Templates.
    }
    
}
