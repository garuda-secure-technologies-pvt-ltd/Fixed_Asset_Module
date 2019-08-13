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
public class MemberDependent implements SerializableRead,  IKeyed{
    private String id;
    private String name;
    private String searchkey;
    public void readValues(DataRead dr) throws BasicException {
       id=dr.getString(1);
       name=dr.getString(2);
       searchkey=dr.getString(3);
    }
    public String getID(){
      return id;
    }
    public String getName(){
      return name;
    }
    public String getSearchKey(){
      return searchkey;
    }
    public Object getKey() {
        return id;
    }
    @Override
    public String toString(){
        return name;
    }

}
