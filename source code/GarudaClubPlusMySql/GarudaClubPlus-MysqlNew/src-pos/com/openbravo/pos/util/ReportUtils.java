


package com.openbravo.pos.util;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author adrianromero
 */
public class ReportUtils {
    
    private ReportUtils() {
    }
    
    public static PrintService getPrintService(String printername) {
        
        // Initalize print service
        
        if (printername != null) {
            PrintService[] pservices = 
                    PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE , null);
            for (PrintService s : pservices) {    
                if (printername.equals(s.getName())) {
                    return s;
                }
            }
        }
        
        return PrintServiceLookup.lookupDefaultPrintService();           
    }
    
    public static String[] getPrintNames() {
        PrintService[] pservices = 
                PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE , null);
        
        String printers[] = new String[pservices.length];
        for (int i = 0; i < pservices.length; i++) {    
            printers[i] = pservices[i].getName();
        }
        
        return printers;
    }

}
