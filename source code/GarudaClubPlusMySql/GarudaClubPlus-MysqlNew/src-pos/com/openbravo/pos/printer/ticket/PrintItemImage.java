

package com.openbravo.pos.printer.ticket;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.openbravo.pos.printer.screen.*;

public class PrintItemImage implements PrintItem {
    
    private BufferedImage image;
    
    /** Creates a new instance of PrintItemImage */
    public PrintItemImage(BufferedImage image) {
        this.image = image;
    }
    
    public void draw(Graphics2D g, int x, int y, int width) {
        g.drawImage(image,  x + (width - image.getWidth()) / 2,  y, image.getWidth(), image.getHeight(), null);
    }
    
    public int getHeight() {
        return image.getHeight();
    }
    
}
