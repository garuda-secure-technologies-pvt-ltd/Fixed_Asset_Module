

package com.openbravo.pos.printer.escpos;

public class CodesIthaca extends Codes {
    
    private static final byte[] CHAR_SIZE_0 = {0x1D, 0x21, 0x00};
    private static final byte[] CHAR_SIZE_1 = {0x1D, 0x21, 0x01};
    private static final byte[] CHAR_SIZE_2 = {0x1D, 0x21, 0x30};
    private static final byte[] CHAR_SIZE_3 = {0x1D, 0x21, 0x31};
    
    private static final byte[] OPEN_DRAWER = {0x1B, 0x78, 0x01};    
    private static final byte[] PARTIAL_CUT = {0x1B, 0x50, 0x00};
    private static final byte[] IMAGE_HEADER = {0x1D, 0x76, 0x30, 0x03};
    private static final byte[] NEW_LINE = {0x0D, 0x0A}; // Print and carriage return
    
    /** Creates a new instance of CodesIthaca */
    public CodesIthaca() {
    }
     
    public byte[] getSize0() { return CHAR_SIZE_0; }
    public byte[] getSize1() { return CHAR_SIZE_1; }
    public byte[] getSize2() { return CHAR_SIZE_2; }
    public byte[] getSize3() { return CHAR_SIZE_3; }
    
    public byte[] getOpenDrawer() { return OPEN_DRAWER; }    
    public byte[] getCutReceipt() { return PARTIAL_CUT; }       
    public byte[] getNewLine() { return NEW_LINE; } 
    public byte[] getImageHeader() { return IMAGE_HEADER; } 
}
