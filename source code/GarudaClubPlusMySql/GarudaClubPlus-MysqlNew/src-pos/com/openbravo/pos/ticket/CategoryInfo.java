

package com.openbravo.pos.ticket;

import java.awt.image.*;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;

/**
 *
 * @author  Adrian
 * @version 
 */
public class CategoryInfo implements SerializableRead, SerializableWrite, IKeyed {

    private String m_sID;
    private String m_sName;
    private BufferedImage m_Image;
    private String pId;
    
    /** Creates new CategoryInfo */
    public CategoryInfo() {
        m_sID = null;
        m_sName = null;
        m_Image = null;
        pId=null;
    }
    
    public Object getKey() {
        return m_sID;
    }
    public void readValues(DataRead dr) throws BasicException {
        m_sID = dr.getString(1);
        m_sName = dr.getString(2);
        
        pId=dr.getString(3);
        m_Image = ImageUtils.readImage(dr.getBytes(4));
    }
    
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, m_sID);
        dp.setString(2, m_sName);
        dp.setBytes(3, ImageUtils.writeImage(m_Image));
      //  dp.setString(4, pId);
    }
    
    public void setID(String sID) {
        m_sID = sID;
    }

   public String getpId()
    {
        return pId;
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
    
    public BufferedImage getImage() {
        return m_Image;
    }
    
    public void setImage(BufferedImage img) {
        m_Image = img;
    }    
    public String toString(){
        return m_sName;
    }
}
