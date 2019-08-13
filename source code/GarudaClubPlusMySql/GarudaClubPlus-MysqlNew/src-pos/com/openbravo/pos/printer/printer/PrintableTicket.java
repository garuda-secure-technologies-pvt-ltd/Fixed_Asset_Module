

package com.openbravo.pos.printer.printer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import com.openbravo.pos.printer.ticket.BasicTicket;
import javax.print.PrintService;

public class PrintableTicket implements Printable {
    
    private static final int H_GAP = 8;
    private static final int V_GAP = 8;
    private static final int COLUMNS = 50;
    private static final int LINEWIDTH = COLUMNS * 7;  
    
    private BasicTicket basict;
    
    /** Creates a new instance of PrintableTicket */
    public PrintableTicket(BasicTicket t) {
        basict = t;   
    }

   
    
    public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
        
        if (pi >= 1) {
            return Printable.NO_SUCH_PAGE;
        }        
        
        basict.draw((Graphics2D) g, (int) pf.getImageableX() + H_GAP, (int) pf.getImageableY() + V_GAP, LINEWIDTH);
        return Printable.PAGE_EXISTS;
    }    
}
