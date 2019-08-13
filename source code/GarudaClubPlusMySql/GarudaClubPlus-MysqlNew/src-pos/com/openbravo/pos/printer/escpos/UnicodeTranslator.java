

package com.openbravo.pos.printer.escpos;

public abstract class UnicodeTranslator {
    
    public abstract byte[] getCodeTable();
    
    public final byte[] transString(String sCad) {

        if (sCad == null) {
            return null;
        } else {
            byte bAux[] = new byte[sCad.length()];
            for( int i = 0; i < sCad.length(); i++) {
                bAux[i] = transChar(sCad.charAt(i));
            }
            return bAux;
        }
    }
    
    public abstract byte transChar(char sChar);  
    
    public final byte[] transNumber(String sCad) {

        if (sCad == null) {
            return null;
        } else {
            byte bAux[] = new byte[sCad.length()];
            for( int i = 0; i < sCad.length(); i++) {
                bAux[i] = transNumberChar(sCad.charAt(i));
            }
            return bAux;
        }
    }
    
    public abstract byte transNumberChar(char sChar);
    
}
