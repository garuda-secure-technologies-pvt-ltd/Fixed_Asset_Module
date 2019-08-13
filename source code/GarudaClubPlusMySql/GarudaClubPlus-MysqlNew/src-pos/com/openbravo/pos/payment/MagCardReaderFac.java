
package com.openbravo.pos.payment;

/**
 *
 * @author adrianromero
 */
public class MagCardReaderFac {
    
    /** Creates a new instance of MagCarReaderFac */
    private MagCardReaderFac() {
    }
    
    public static MagCardReader getMagCardReader(String sReader) {
        
        if ("Intelligent".equals(sReader)) {
            return new MagCardReaderIntelligent();
        } else if ("Generic".equals(sReader)) {
            return new MagCardReaderGeneric();
        } else {
            return null;
        }
    }    
}
