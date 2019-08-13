/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class AccountMaster implements SerializableRead, IKeyed{
    protected String Name;
    protected String Searchkey;
    protected String id;
    protected String parent;
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        Name=dr.getString(2);
        Searchkey=dr.getString(3);
        parent=dr.getString(4);
    }
      @Override
    public String toString(){
        return Name;
    }
    public String getid(){
     return id;
    }
     public String getSerachkey(){
     return Searchkey;
    }
       public Object getKey() {
       return id;
    }
    public String getName(){
      return Name;
    }
     public String getparent(){
     return parent;
    }

    public String getSearchkey() {
        return Searchkey;
    }

    public void setSearchkey(String Searchkey) {
        this.Searchkey = Searchkey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
     
     
}
