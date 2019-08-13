

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
public class PlacesInfo implements SerializableRead, Externalizable, IKeyed {
private static final long  serialVersionUID=3L; 
    private String m_sID;
    private String m_sName;
    private String m_sX;
    private String m_sY;
    private String m_sFloorsID;
    
    /** Creates a new instance of FloorsInfo */
    public PlacesInfo() {
        m_sID = null;
        m_sName = null;
        m_sX = null;
        m_sY = null;
        m_sFloorsID = null;
    }
   
    public Object getKey() {
        return m_sID;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        m_sX = dr.getString(3);
        m_sY = dr.getString(4);
        m_sFloorsID = dr.getString(5);
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
    
    public String getX() {
        return m_sX;
    }

    public void setX(String x) {
        m_sX = x;
    }

    public String getY() {
        return m_sY;
    }

    public void setY(String y) {
        m_sY = y;
    }

    public String getFloorsID() {
        return m_sFloorsID;
    }

    public void setFloorsID(String y) {
        m_sFloorsID = y;
    }

    public String toString(){
        return m_sName;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(m_sID);
        out.writeObject(m_sName);
        out.writeObject(m_sX);
        out.writeObject(m_sY);
        out.writeObject(m_sFloorsID);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        m_sID = (String) in.readObject();
        m_sName = (String) in.readObject();
        m_sX = (String) in.readObject();
        m_sY = (String) in.readObject();
        m_sFloorsID = (String) in.readObject();
    }
}
