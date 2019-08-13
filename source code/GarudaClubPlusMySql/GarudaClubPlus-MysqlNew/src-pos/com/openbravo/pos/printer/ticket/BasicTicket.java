
package com.openbravo.pos.printer.ticket;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BasicTicket implements PrintItem {
    
    private java.util.List<PrintItem> m_aCommands;  
    private PrintItemLine pil; 
    private int m_iBodyHeight;
    
    /** Creates a new instance of AbstractTicket */
    public BasicTicket() {
        m_aCommands = new ArrayList<PrintItem>();
        
        pil = null;
        m_iBodyHeight = 0;
    }
    
    public int getHeight() {
        return m_iBodyHeight;
    }
    
    public void draw(Graphics2D g2d, int x, int y, int width) {
        
        int currenty = y;
        for (PrintItem pi : m_aCommands) {
            pi.draw(g2d, x, currenty, width);
            currenty += pi.getHeight(); 
        }      
    }
    
    // INTERFAZ PRINTER 2
    public void printImage(BufferedImage image) {
        
        PrintItem pi = new PrintItemImage(image);
        m_aCommands.add(pi);
        m_iBodyHeight += pi.getHeight();
    }
    public void printBarCode(String type, String position, String code) {
        //type="CODE128";
       // position="bottom";
        //code="asdkahsdk";
        PrintItem pi = new PrintItemBarcode(type, position, code);
        m_aCommands.add(pi);
        m_iBodyHeight += pi.getHeight();
    }
    public void beginLine(int iTextSize,int width) {
        pil = new PrintItemLine(iTextSize,width);
    }
    public void printText(int iStyle, String sText) {
        if (pil != null) {
            pil.addText(iStyle, sText);
        }
    }    
    public void endLine() {
        if (pil != null) {
            m_aCommands.add(pil);
            m_iBodyHeight += pil.getHeight(); 
            pil = null;
        }
    }    
}
