

package com.openbravo.pos.printer.escpos;

import java.awt.image.BufferedImage;

public class CodesTMU220 extends Codes {
    
    public static final byte[] CHAR_SIZE_0 = {0x1B, 0x21, 0x01}; // This sets 7x9 font 
    public static final byte[] CHAR_SIZE_1 = {0x1B, 0x21, 0x11}; // This sets double hight 7x9 font 
    public static final byte[] CHAR_SIZE_2 = {0x1B, 0x21, 0x21}; // This sets 7x9 double width font 
    public static final byte[] CHAR_SIZE_3 = {0x1B, 0x21, 0x31}; // This sets 7x9 double width/hight font 
    
    private static final byte[] OPEN_DRAWER = {0x1B, 0x70, 0x00, 0x32, -0x06};    
    private static final byte[] PARTIAL_CUT_1 = {0x1B, 0x69};
    private static final byte[] IMAGE_HEADER = {0x1D, 0x76, 0x30, 0x03};
    private static final byte[] NEW_LINE = {0x0D, 0x0A}; // Print and carriage return    

    /** Creates a new instance of CodesTMU220 */
    public CodesTMU220() {
    }
     
    public byte[] getSize0() { return CHAR_SIZE_0; }
    public byte[] getSize1() { return CHAR_SIZE_1; }
    public byte[] getSize2() { return CHAR_SIZE_2; }
    public byte[] getSize3() { return CHAR_SIZE_3; }
    
    public byte[] getOpenDrawer() { return OPEN_DRAWER; }    
    public byte[] getCutReceipt() { return PARTIAL_CUT_1; }   
    public byte[] getNewLine() { return NEW_LINE; } 
    public byte[] getImageHeader() { return IMAGE_HEADER; } // Not used
    
    @Override
    public byte[] transImage(BufferedImage oImage) {
        // Nothing to print
        return new byte[0];
    }
}
