

package com.openbravo.pos.customers;


import com.openbravo.data.loader.IKeyed;
import java.io.Serializable;

/**
 *
 * @author adrianromer
 */
public class CustomerInfo implements Serializable,IKeyed {
    private static final long  serialVersionUID=11L; 
    protected String id;
    protected String searchkey;
    protected String taxid;
    protected String name;
    protected String mobile;
    protected String accno;
    private String guestName=null;
     public String notes;
   // protected String memtype;
    /** Creates a new instance of UserInfoBasic */
    public CustomerInfo(){
    }
    public CustomerInfo(String id) {
        this.id = id;
        this.searchkey = null;
        this.taxid = null;
        this.name = null;
        this.mobile=null;
        this.accno=null;
      //  this.memtype=null;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }
 /*   public void setMemType(Object type){
       if(type==null)
            memtype=null;
        else
            memtype=type.toString();
    }
    public String getMemType(){
     return memtype;
    }*/
    public String getGuestName(){
        return guestName;
    }
    public void setGuestName(String name){
        guestName=name;
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
    public String getMobile(){
      return mobile;
    }

    public void setMobile(String value){
      mobile=value;
    }
    @Override
    public String toString() {
        return getSearchkey()+" - "+getName();
    }

    public Object getKey() {
        return id;
    }
 public String getNotes() {
        return notes;
    }  
      public void setNotes(String notes) {
        this.notes = notes;
    }
    
}

