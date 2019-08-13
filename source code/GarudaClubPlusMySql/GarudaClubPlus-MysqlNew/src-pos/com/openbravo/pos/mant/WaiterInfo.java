

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
public class WaiterInfo implements SerializableRead, Externalizable, IKeyed {
    private static final long  serialVersionUID=2L; 
    private String m_sID;
    private String m_sName;
     private String cardNo;
    private String counter;
  //  private Boolean m_sShow;
    
    /** Creates a new instance of FloorsInfo */
    public WaiterInfo() {
        m_sID = null;
        m_sName = null;
        cardNo = null;
     //   m_sShow=null;
    }
   
    public Object getKey() {
        return m_sID;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        cardNo = dr.getString(4);
        counter = dr.getString(3);

     //   m_sShow=dr.getBoolean(5);
    } 
    
    
    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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
    

    public String toString(){
        return m_sName;
    }

 /*   public void setShow(Boolean value)
    {
       m_sShow=value;
    }

    public Boolean getShow()
    {
        return m_sShow;
    }*/

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(m_sID);
        out.writeObject(m_sName);
       // out.writeObject(m_sShow);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        m_sID = (String) in.readObject();
        m_sName = (String) in.readObject();
     //   m_sShow=(Boolean) in.readObject();
    }
}
