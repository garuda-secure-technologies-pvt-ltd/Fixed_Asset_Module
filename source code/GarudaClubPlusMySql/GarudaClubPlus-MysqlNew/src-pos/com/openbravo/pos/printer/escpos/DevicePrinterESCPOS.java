

package com.openbravo.pos.printer.escpos;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;

import com.openbravo.pos.printer.*;
import com.openbravo.pos.forms.AppLocal;
import javax.swing.JOptionPane;

public class DevicePrinterESCPOS implements DevicePrinter  {
      
    private PrinterWritter m_CommOutputPrinter;   
    private Codes m_codes;
    private UnicodeTranslator m_trans;
    
//    private boolean m_bInline;
    private String m_sName;
    
    // Creates new TicketPrinter
    public DevicePrinterESCPOS(PrinterWritter CommOutputPrinter, Codes codes, UnicodeTranslator trans) throws TicketPrinterException {
        
        m_sName = AppLocal.getIntString("Printer.Serial");
        m_CommOutputPrinter = CommOutputPrinter;
        m_codes = codes;
        m_trans = trans;

        // Inicializamos la impresora
        m_CommOutputPrinter.write(ESCPOS.CHAR_FONT_1);
        m_CommOutputPrinter.init(ESCPOS.INIT);
        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER); // A la impresora
        m_CommOutputPrinter.write(m_trans.getCodeTable());        
        m_CommOutputPrinter.flush();  
    }
   
    public String getPrinterName() {
        return m_sName;
    }
    public String getPrinterDescription() {
        return null;
    }   
    public JComponent getPrinterComponent() {
        return null;
    }
    public void reset() {
    }
    
    public void beginReceipt() {
    }
    
    public void printImage(BufferedImage image) {
        
        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);        
        m_CommOutputPrinter.write(m_codes.transImage(image));
    }
    
    public void printBarCode(String type, String position, String code) {
        
        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);        

        if (DevicePrinter.BARCODE_EAN13.equals(type)) {
            m_CommOutputPrinter.write(m_codes.getNewLine());

            m_CommOutputPrinter.write(ESCPOS.BAR_HEIGHT);
            if (DevicePrinter.POSITION_NONE.equals(position)) {                
                m_CommOutputPrinter.write(ESCPOS.BAR_POSITIONNONE);
            } else {
                m_CommOutputPrinter.write(ESCPOS.BAR_POSITIONDOWN);
            }           
            m_CommOutputPrinter.write(ESCPOS.BAR_HRIFONT1);
            m_CommOutputPrinter.write(ESCPOS.BAR_CODE02);
            m_CommOutputPrinter.write(m_trans.transNumber(DeviceTicket.alignBarCode(code,13).substring(0,12)));
            m_CommOutputPrinter.write(new byte[] { 0x00 });

            m_CommOutputPrinter.write(m_codes.getNewLine());

        }
    }
    
    public void beginLine(int iTextSize) {

        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);        
        
        if (iTextSize == DevicePrinter.SIZE_0) {
            m_CommOutputPrinter.write(m_codes.getSize0());
        } else if (iTextSize == DevicePrinter.SIZE_1) {
            m_CommOutputPrinter.write(m_codes.getSize1());
        } else if (iTextSize == DevicePrinter.SIZE_2) {
            m_CommOutputPrinter.write(m_codes.getSize2());
        } else if (iTextSize == DevicePrinter.SIZE_3) {
            m_CommOutputPrinter.write(m_codes.getSize3());
        } else {
            m_CommOutputPrinter.write(m_codes.getSize0());
        }
    }
    
    public void printText(int iStyle, String sText) {

        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);   

        if ((iStyle & DevicePrinter.STYLE_BOLD) != 0) {
            m_CommOutputPrinter.write(ESCPOS.BOLD_SET);
        }
        if ((iStyle & DevicePrinter.STYLE_UNDERLINE) != 0) {
            m_CommOutputPrinter.write(ESCPOS.UNDERLINE_SET);
        }
        m_CommOutputPrinter.write(m_trans.transString(sText));
        if ((iStyle & DevicePrinter.STYLE_BOLD) != 0) {
            m_CommOutputPrinter.write(ESCPOS.BOLD_RESET);
        }
        if ((iStyle & DevicePrinter.STYLE_UNDERLINE) != 0) {
            m_CommOutputPrinter.write(ESCPOS.UNDERLINE_RESET);
        }        
    }
    public void endLine() {
        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);   
        m_CommOutputPrinter.write(m_codes.getNewLine());
    }
    
    public void endReceipt() {
        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);   
        
        m_CommOutputPrinter.write(m_codes.getNewLine());
        m_CommOutputPrinter.write(m_codes.getNewLine());
        m_CommOutputPrinter.write(m_codes.getNewLine());
        m_CommOutputPrinter.write(m_codes.getNewLine());
        m_CommOutputPrinter.write(m_codes.getNewLine());

        m_CommOutputPrinter.write(m_codes.getCutReceipt());
        m_CommOutputPrinter.flush();
    }
    
    public void openDrawer() {

        m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);   
        m_CommOutputPrinter.write(m_codes.getOpenDrawer());
        m_CommOutputPrinter.flush();
    }
     public void beginLine(int iTextSize, int width) {
       // JOptionPane.showMessageDialog(null, "Error");
         m_CommOutputPrinter.write(ESCPOS.CHAR_FONT_1);
         m_CommOutputPrinter.write(ESCPOS.SELECT_PRINTER);
         m_CommOutputPrinter.write(ESCPOS.CHAR_FONT_1);

        if (iTextSize == DevicePrinter.SIZE_0) {
            m_CommOutputPrinter.write(m_codes.getSize0());
        } else if (iTextSize == DevicePrinter.SIZE_1) {
            m_CommOutputPrinter.write(m_codes.getSize1());
        } else if (iTextSize == DevicePrinter.SIZE_2) {
            m_CommOutputPrinter.write(m_codes.getSize2());
        } else if (iTextSize == DevicePrinter.SIZE_3) {
            m_CommOutputPrinter.write(m_codes.getSize3());
        } else {
            m_CommOutputPrinter.write(m_codes.getSize0());
        }
    }
}

