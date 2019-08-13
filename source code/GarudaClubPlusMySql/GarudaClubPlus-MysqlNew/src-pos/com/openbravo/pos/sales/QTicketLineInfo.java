/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

/**
 *
 * @author swathi
 */
public class QTicketLineInfo implements SerializableWrite, SerializableRead, Serializable {
    private static final long  serialVersionUID=7L; 
    private String m_id;
    private String m_parentid;
    private String m_product;
    private int m_multiply;
    private double m_rate;
    private int m_iLine;
    private Properties m_attributes = new Properties();
    
    public QTicketLineInfo(String id, String parentid, String product, int multiply, double rate) {
        m_id=id;
        m_parentid = parentid;
        m_product = product;
        m_multiply = multiply;
        m_rate = rate;
        m_iLine = -1;
    }//or init???

    
    public QTicketLineInfo () {
        m_id = UUID.randomUUID().toString();
    }
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, m_id);
        dp.setInt(2, m_iLine);
        dp.setString(3, m_parentid);
        dp.setString(4, m_product);
        dp.setInt(5, m_multiply);
        dp.setDouble(6, m_rate);
        try {
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            m_attributes.storeToXML(o, AppLocal.APP_NAME, "UTF-8");
            dp.setBytes(7, o.toByteArray());
        } catch (IOException e) {
            dp.setBytes(7, null);
        }
    }



    public void readValues(DataRead dr) throws BasicException {
         m_id = dr.getString(1);
         m_iLine = dr.getInt(2);
         m_parentid = dr.getString(3);
         m_product = dr.getString(4);
         m_multiply = dr.getInt(5);
         m_rate = dr.getDouble(6);
        m_attributes = new Properties();
        try {
            byte[] img = dr.getBytes(7);
            if (img != null) {
                m_attributes.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
            //TODO logging
        }
    }

    public QTicketLineInfo copyqticket() {
        QTicketLineInfo t = new QTicketLineInfo();
        t.m_id = m_id;
        t.m_iLine = m_iLine;
        t.m_parentid = m_parentid;
        t.m_product = m_product;
        t.m_multiply = m_multiply;
        t.m_rate = m_rate;
        t.m_attributes = m_attributes;
        return t;
    }

    public String getID() {
        return m_id;
    }

    public void setID(String value) {
        m_id = value;
    }

    public int getLine() {
        return m_iLine;
    }

    public void setLine(int value) {
        m_iLine = value;
    }

    public String getParentid() {
        return m_parentid;
    }

    public void setParentid(String value) {
        m_parentid = value;
    }

    public String getProduct() {
        return m_product;
    }

    public void setProduct(String value) {
        m_product = value;
    }

    public int getMultiply() {
        return m_multiply;
    }

    public void setMultiply(int value){
        m_multiply = value;
    }

    public double getRate() {
        return m_rate;
    }

    public void setRate(double value) {
        m_rate = value;
    }

     void setTicket(String parentid, int line) {
        m_parentid = parentid;
        m_iLine = line;
    }

    public String getProperty(String key) {
        return m_attributes.getProperty(key);
    }

    public String getProperty(String key, String defaultvalue) {
        return m_attributes.getProperty(key, defaultvalue);
    }

    public void setProperty(String key, String value) {
        m_attributes.setProperty(key, value);
    }

    public void setProperties(Properties attributes) {
        m_attributes = attributes;
    }

    public Properties getProperties() {
        return m_attributes;
    }

    public String getRemarks() {
        return m_attributes.getProperty("qt.remarks");
    }

    public void setRemarks(String value) {
        m_attributes.setProperty("qt.remarks", value);
    }

    public String printName() {
         //return StringUtils.encodeXML(m_attributes.getProperty("product.name"));
        return (m_attributes.getProperty("product.name"));
    }

    public String printMultiply() {
        return Formats.DOUBLE.formatValue(m_multiply);
    }

    public String printRemarks() {
         return getRemarks() == null ? "" : StringUtils.encodeXML(getRemarks());
    }
    public String printamount() {
          return Formats.DOUBLE.formatValue(m_rate);
    }


}
