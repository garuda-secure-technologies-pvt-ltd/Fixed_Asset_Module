


package com.openbravo.pos.scanpal2;

/**
 *
 * @author adrianromero
 */
public class DeviceScannerException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DeviceScannerException</code> without detail message.
     */
    public DeviceScannerException() {
    }
    
    
    /**
     * Constructs an instance of <code>DeviceScannerException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DeviceScannerException(String msg) {
        super(msg);
    }
    public DeviceScannerException(String msg, Throwable cause) {
        super(msg, cause);
    }    
    public DeviceScannerException(Throwable cause) {
        super(cause);
    }       
}
