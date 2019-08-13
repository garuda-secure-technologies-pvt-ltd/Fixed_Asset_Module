

package com.openbravo.pos.printer.ticket;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import com.openbravo.pos.printer.DevicePrinter;
import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

public class PrintItemBarcode implements PrintItem {
    
    private AbstractBarcodeBean m_barcode;
    private String m_sCode;
    
    private int m_iWidth;
    private int m_iHeight;
    
    /** Creates a new instance of PrinterItemBarcode */
    public PrintItemBarcode(String type, String position, String code) {
        
        
        
        m_sCode = code;
        
        if (DevicePrinter.BARCODE_CODE128.equals(type)) {
            m_barcode = new Code128Bean();
        } else {
            m_barcode = new EAN13Bean();
        }
        
        if (m_barcode != null) {
            m_barcode.setModuleWidth(1.0); 
            m_barcode.setBarHeight(40.0);
            m_barcode.setFontSize(10.0);
            m_barcode.setQuietZone(10.0);
            m_barcode.doQuietZone(true);  
            if (DevicePrinter.POSITION_NONE.equals(position)) {                
                m_barcode.setMsgPosition(HumanReadablePlacement.HRP_NONE);
            } else {
                m_barcode.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
            }
            BarcodeDimension dim = m_barcode.calcDimensions(m_sCode);
            m_iWidth = (int) dim.getWidth(0);
            m_iHeight = (int) dim.getHeight(0);
        }
    }
    
    public void draw(Graphics2D g, int x, int y, int width) {

        if (m_barcode != null) {
            Graphics2D g2d = (Graphics2D) g;

            AffineTransform oldt = g2d.getTransform();

            g2d.translate(x - 10 + (width - m_iWidth) / 2, y + 10);      
            
            try {
                m_barcode.generateBarcode(new Java2DCanvasProvider(g2d, 0), m_sCode);
            } catch (IllegalArgumentException e) {
                g2d.drawRect(0, 0, m_iWidth, m_iHeight);
                g2d.drawLine(0, 0, m_iWidth, m_iHeight);
                g2d.drawLine(m_iWidth, 0, 0, m_iHeight);
            }

            g2d.setTransform(oldt);
        }
    }
    
    public int getHeight() {
        return m_iHeight + 20;
    }    
}
