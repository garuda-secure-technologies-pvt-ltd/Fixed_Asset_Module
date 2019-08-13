/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;

/**
 *
 * @author swathi
 */
public class CustomerInfo1 implements IKeyed,SerializableRead {

    protected String id;
    protected String searchkey;
    protected String taxid;
    protected String name;
    protected String memtype;
    /** Creates a new instance of UserInfoBasic */


    public void setMemType(Object type){
        if(type==null)
            memtype=null;
        else
            memtype=type.toString();
    }
    public String getMemType(){
      return memtype;
    }
    public String getId() {
        return id;
    }
     public void setId(String ID) {
         id=ID;
    }

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Object getKey() {
        return id;
    }

    public void readValues(DataRead dr) throws BasicException {
       id=dr.getString(1);
       searchkey=dr.getString(2);
       taxid=dr.getString(3);
       name=dr.getString(4);
       memtype=dr.getString(5);
    }
}

