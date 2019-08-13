
package com.openbravo.pos.ticket;

import java.awt.image.BufferedImage;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.ImageUtils;

/**
 *
 * @author adrianromero
 * Created on 21 de marzo de 2007, 21:28
 *
 */
public class ProductInfoEdit /* implements SerializableRead , SerializableWrite */ {
    
    protected String m_ID;
    protected String m_sRef;
    protected String m_sCode;
    protected String m_sName;
    protected boolean m_bCom;
    protected boolean m_bScale;
    protected String m_sCategoryID;    
    protected String m_sTaxID;
    protected double m_dPriceBuy;
    protected double m_dPriceSell; 
    protected BufferedImage m_Image;
    
    protected String m_dStockCost;
    protected Double m_dStockVolume;
    protected Integer m_iCatalogOrder;
    protected String m_sunittype;
    
    /** Creates a new instance of ProductInfoEdit */
    public ProductInfoEdit() {
        m_ID = null;
        m_sRef = "0000";
        m_sCode = "0000";
        m_sName = null;
        m_bCom = false;
        m_bScale = false;
        m_sCategoryID = null;
        m_sTaxID = null;
        m_dPriceBuy = 0.0;
        m_dPriceSell = 0.0;
        m_Image = null;
        m_dStockCost = null;
        m_dStockVolume = null;
        m_iCatalogOrder = null;
        m_sunittype=null;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_ID = dr.getString(1);
        m_sRef = dr.getString(2);
        m_sCode = dr.getString(3);
        m_sName = dr.getString(4);
        m_bCom = dr.getBoolean(5).booleanValue();
        m_bScale = dr.getBoolean(6).booleanValue();
        m_dPriceBuy = dr.getDouble(7).doubleValue();
        m_dPriceSell = dr.getDouble(8).doubleValue();
        m_sCategoryID = dr.getString(9);
        m_sTaxID =  dr.getString(10);      
        m_Image = ImageUtils.readImage(dr.getBytes(11));
        m_dStockCost =  dr.getString(12);
        m_dStockVolume =  dr.getDouble(13);
        m_iCatalogOrder = dr.getInt(14);
        m_sunittype=dr.getString(15);
    }
   
    public final String getID() {
        return m_ID;
    }
    
    public final void setID(String id) {
        m_ID = id;
    }
    
    public final String getReference(){
        return m_sRef;
    }
    public final void setReference(String sRef){
        m_sRef = sRef;
    }    
    public final String getCode(){
        return m_sCode;
    }
    public final void setCode(String sCode){
        m_sCode = sCode;
    }
    public final String getName() {            
        return m_sName;
    }
    public final void setName(String sName){            
        m_sName = sName;
    }
    public final boolean isCom() {            
        return m_bCom;
    }
    public final void setCom(boolean bValue){            
        m_bCom = bValue;
    }
    public final boolean isScale() {            
        return m_bScale;
    }
    public final void setScale(boolean bValue){            
        m_bScale = bValue;
    }
    public final String getCategoryID() {
        return m_sCategoryID;
    }
    public final void setCategoryID(String sCategoryID) {
        m_sCategoryID = sCategoryID;
    }
   public final String getUnitType() {
        return m_sunittype;
    }
    public final void setUnitType(String unittype) {
        m_sunittype = unittype;
    }
    public final String getTaxID() {
        return m_sTaxID;
    }
    public final void setTaxID(String sTaxID) {
        m_sTaxID = sTaxID;
    }    
    public final double getPriceBuy(){
        return m_dPriceBuy;
    }    
    public final void setPriceBuy(double dPrice) {
        m_dPriceBuy = dPrice;
    }        
    public final double getPriceSell(){        
        return m_dPriceSell;
    }
    public final void setPriceSell(double dPrice) {        
        m_dPriceSell = dPrice;
    }      

    public BufferedImage getImage() {
        return m_Image;
    }
    public void setImage(BufferedImage img) {
        m_Image = img;
    }
    
    public final String toString() {
        return m_sRef + " - " + m_sName;
    }    
}
