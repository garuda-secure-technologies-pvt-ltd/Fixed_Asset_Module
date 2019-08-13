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
public class GeneralSettingInfo implements SerializableRead, IKeyed{
    private String id;
    private String name;
    private String value;
    public GeneralSettingInfo(){
     id=null;
     name=null;
     value=null;
    }
    public void readValues(DataRead dr) throws BasicException {
         id=dr.getString(1);
         name=dr.getString(2);
         value=dr.getString(3);
    }
    public String getID(){
      return id;
    }
    public String getName(){
       return name;
    }
    public String getValue(){
        return value;
    }

    public Object getKey() {
        return id;
    }
}
