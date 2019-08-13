

package com.openbravo.pos.printer.escpos;

import java.io.*;

public class PrinterWritterFile extends PrinterWritter {
    
    private String m_sFilePrinter;
    private OutputStream m_out;
     private OutputStream m_out1;
    
    public PrinterWritterFile(String sFilePrinter) {
        m_sFilePrinter = sFilePrinter;
        m_out = null;
      //  m_out1=null;
        
        write(ESCPOS.INIT); // Arrancamos la impresora
        flush();
    }  
    
    protected void daemonWrite(byte[] data) {
        try {  
            if (m_out == null) {
                m_out = new FileOutputStream(m_sFilePrinter);  // No poner append = true.
              //  m_out1=new FileOutputStream("f:\\data.txt");
             //   m_out1.flush();
            }
            m_out.write(data);
          //  m_out1.write(data);
        } catch (IOException e) {
            System.err.println(e);
        }    
    }
    
    protected void daemonFlush() {
        try {  
            if (m_out != null) {
                m_out.flush();
                m_out.close();
             //   m_out1.close();
               //  m_out1 = null;
                m_out = null;
            }
        } catch (IOException e) {
            System.err.println(e);
        }    
    }
    
    protected void daemonClose() {
        try {  
            if (m_out != null) {
                m_out.flush();
                m_out.close();
                m_out = null;
               //   m_out1.close();
              //   m_out1 = null;
            }
        } catch (IOException e) {
            System.err.println(e);
        }    
    }    
}
