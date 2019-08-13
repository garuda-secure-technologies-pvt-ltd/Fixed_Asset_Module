

package com.openbravo.pos.ticket;

import java.io.*;
import java.awt.image.BufferedImage;
import com.openbravo.data.loader.DataRead;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import java.util.Properties;

public class ProductInfoExt extends ProductInfo {
    
    protected BufferedImage m_Image;
    protected Properties attributes;
   
    
    /** Creates new ProductInfo */
    public ProductInfoExt() {
        super();
        m_Image = null;
        attributes = new Properties();
    }


    
    @Override
    public void readValues(DataRead dr) throws BasicException {
        m_ID = dr.getString(1);
        m_sRef = dr.getString(2);
        m_sCode = dr.getString(3);
        m_sName = dr.getString(4);
        m_bCom = dr.getBoolean(5).booleanValue();
        m_bScale = dr.getBoolean(6).booleanValue();
        m_dPriceBuy = dr.getDouble(7).doubleValue();
        m_dPriceSell = dr.getDouble(8).doubleValue();
        taxcategory = new TaxCategoryInfo(dr.getString(9), dr.getString(10));      
        m_sCategoryID = dr.getString(11);
        m_Image = ImageUtils.readImage(dr.getBytes(12));
        attributes = new Properties();
        try {
            byte[] img = dr.getBytes(13);
            if (img != null) {
                attributes.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
        }
        m_sprcategory = dr.getString(14);
        m_sunittype=dr.getString(15);
          stockvolume=dr.getDouble(16);
          //Account
          account=dr.getString(17);
          saccount=dr.getString(18);
          isStockMaintainRequired=dr.getBoolean(19);
          warehouse=dr.getString(20);
          
          
         taxcategory2 = new TaxCategoryInfo(dr.getString(21), dr.getString(23));                                                                  // edited by aakash 
         taxcategory3 = new TaxCategoryInfo(dr.getString(22), dr.getString(24));     
         BASIC2 = dr.getBoolean(25);
         BASIC3 = dr.getBoolean(26);
    }
    
    public BufferedImage getImage() {
        return m_Image;
    }
    public void setImage(BufferedImage img) {
        m_Image = img;
    }
    
    public String getProperty(String key) {
        return attributes.getProperty(key);
    }
    public String getProperty(String key, String defaultvalue) {
        return attributes.getProperty(key, defaultvalue);
    }
    public void setProperty(String key, String value) {
        attributes.setProperty(key, value);
    }
    public Properties getProperties() {
        return attributes;
    }

    


}
