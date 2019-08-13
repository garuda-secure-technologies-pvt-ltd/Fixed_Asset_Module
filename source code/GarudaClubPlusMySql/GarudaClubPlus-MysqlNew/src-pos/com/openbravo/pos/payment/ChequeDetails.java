package com.openbravo.pos.payment;


import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.pos.util.StringUtils;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author swathi
 */
public class ChequeDetails implements SerializableRead, Externalizable, IKeyed {

    private String id;
    private String chequeno;
    private String bank;
    private String holder;
    private Double amount;
    public ChequeDetails() {
        id = null;
        chequeno = null;
        bank = null;
        holder=null;
        amount=0.0;
    }

    public void readValues(DataRead dr) throws BasicException {
       id = dr.getString(1);
       chequeno = dr.getString(2);
       bank = dr.getString(3);
       holder=dr.getString(4);
       amount=dr.getDouble(5);
    }
    public void setID(String Value) {
        id = Value;
    }
    public void setholder(String holder){
        this.holder=holder;
    }
    public String getholder(){
        return holder;
    }

    public String getID() {
        return id;
    }
    public void setChequeno(String value) {
        chequeno = value;
    }

    public String getChequeno() {
        if(chequeno==null)
            return "";
        else
            return chequeno;
    }

    public void setBank(String value) {
        bank = value;
    }

    public String getBank() {
        if(bank==null)
            return "";
        else
            return StringUtils.encodeXML(bank);
    }
   public Double getAmount(){
       if(amount==null)
           return 0.0;
       else
         return amount;
   }
   public void setAmount(Double amt){
       amount=amt;
   }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(chequeno);
        out.writeObject(bank);
        out.writeObject(holder);
        out.writeObject(amount);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
       id = (String) in.readObject();
        chequeno = (String) in.readObject();
        bank = (String) in.readObject();
        holder=(String) in.readObject();
        amount=(Double) in.readObject();
    }

    public Object getKey() {
        return id;
    }


}
