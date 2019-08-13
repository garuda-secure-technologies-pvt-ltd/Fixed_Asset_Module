
package com.openbravo.pos.printer.escpos;

import java.awt.image.BufferedImage;

public abstract class Codes {
    
    /** Creates a new instance of Codes */
    public Codes() {
    }
    
    public abstract byte[] getSize0();
    public abstract byte[] getSize1();
    public abstract byte[] getSize2();
    public abstract byte[] getSize3();
    
    public abstract byte[] getOpenDrawer();    
    public abstract byte[] getCutReceipt();   
    public abstract byte[] getNewLine();    
    public abstract byte[] getImageHeader();
   
    public byte[] transImage(BufferedImage oImage) {
                        
        // Imprimo los par\u00e1metros en cu\u00e1druple
        int iWidth = (oImage.getWidth() + 7) / 8; // n\u00famero de bytes
        int iHeight = oImage.getHeight();
        
        // Array de datos
        byte[] bData = new byte[getImageHeader().length + 4 + iWidth * iHeight];
        
        // Comando de impresion de imagen
        System.arraycopy(getImageHeader(), 0, bData, 0, getImageHeader().length);
        
        int index = getImageHeader().length;
        
        // Dimension de la imagen
        bData[index ++] = (byte) (iWidth % 256);
        bData[index ++] = (byte) (iWidth / 256);
        bData[index ++] = (byte) (iHeight % 256);
        bData[index ++] = (byte) (iHeight / 256);       
        
        // Raw data
        int iRGB;
        int p;
        for (int i = 0; i < oImage.getHeight(); i++) {
            for (int j = 0; j < oImage.getWidth(); j = j + 8) {                
                p = 0x00;
                for (int d = 0; d < 8; d ++) {
                    p = p << 1;
                    if (j + d < oImage.getWidth()){
                        iRGB = oImage.getRGB(j + d, i);
                        // La condici\u00f3n es que no imprima lo claro 
                        // y que s\u00ed imprima lo oscuro.
                        
                        int gray = (int)(0.30 * ((iRGB >> 16) & 0xff) + 
                                         0.59 * ((iRGB >> 8) & 0xff) + 
                                         0.11 * (iRGB & 0xff));
                        
                        if (gray < 128) {
                            p = p | 0x01;
                        }
                    }
                }
                
                bData[index ++] = (byte) p;
            }
        }        
        return bData;
    }    
}
