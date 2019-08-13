

package com.openbravo.pos.printer.printer;

import com.openbravo.pos.forms.AppLocal;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import javax.swing.JComponent;
import com.openbravo.pos.printer.DevicePrinter;
import com.openbravo.pos.printer.ticket.BasicTicket;
import com.openbravo.pos.util.ReportUtils;
import java.awt.Event;
import javax.print.PrintService;

public class DevicePrinterPrinter implements DevicePrinter {
    
    private String m_sName;
    private BasicTicket m_ticketcurrent;
    private PrintService printservice;
    
    /** Creates a new instance of DevicePrinterPrinter */
    public DevicePrinterPrinter(String printername) {
        m_sName = "Printer"; // "AppLocal.getIntString("Printer.Screen");
        m_ticketcurrent = null;
        printservice = ReportUtils.getPrintService(printername);
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
        m_ticketcurrent = null;
    }
    
    // INTERFAZ PRINTER 2
    public void beginReceipt() {
        m_ticketcurrent = new BasicTicket();
    }
    public void printImage(BufferedImage image) {
        m_ticketcurrent.printImage(image);
    }
    public void printBarCode(String type, String position, String code) {
        m_ticketcurrent.printBarCode(type, position, code);
    }
    public void beginLine(int iTextSize,int width) {
        m_ticketcurrent.beginLine(iTextSize,width);
    }
    public void printText(int iStyle, String sText) {
        m_ticketcurrent.printText(iStyle, sText);
    }
    public void endLine() {
        m_ticketcurrent.endLine();
    }
    public void endReceipt() {

        try {
            // Print
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintable(new PrintableTicket(m_ticketcurrent));
            printJob.setJobName(AppLocal.APP_NAME + " - Document");                    
            printJob.setPrintService(printservice);
            printJob.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
        
        m_ticketcurrent = null;
    }
    
    public void openDrawer() {
        // Una simulacion
        Toolkit.getDefaultToolkit().beep();
    }   
}
