

package com.openbravo.pos.mant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


/**
 *
 * @author adrianromero
 * Created on 26 de febrero de 2007, 23:49
 *
 */
public class FloorsInfo implements SerializableRead, Externalizable, IKeyed {
    private static final long  serialVersionUID=4L; 
    private String m_sID;
    private String m_sName;
    private String m_billslno;
    private String m_lastbillno;
    
    /** Creates a new instance of FloorsInfo */
    public FloorsInfo() {
        m_sID = null;
        m_sName = null;
        m_billslno = null;
        m_lastbillno = null;
    }
   
    public Object getKey() {
        return m_sID;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        m_billslno = dr.getString(3);
        m_lastbillno = dr.getString(4);
    } 
    
    public void setID(String sID) {
        m_sID = sID;
    }
    
    public String getID() {
        return m_sID;
    }

    public String getName() {
        return m_sName;
    }
    
    public void setName(String sName) {
        m_sName = sName;
    } 

     public void setBillSl(String billsl) {
        m_billslno = billsl;
    }

    public String getBillsl() {
        return m_billslno;
    }

    public void setLastBillSl(String lastbill) {
        m_lastbillno = lastbill;
    }

    public String getLastBillsl() {
        return m_lastbillno;
    }
    
    public String toString(){
        return m_sName;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(m_sID);
        out.writeObject(m_sName);
        out.writeObject(m_billslno);
        out.writeObject(m_lastbillno);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        m_sID = (String) in.readObject();
        m_sName = (String) in.readObject();
        m_billslno = (String) in.readObject();
        m_lastbillno = (String) in.readObject();

    }

  
}
