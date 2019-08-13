


package com.openbravo.pos.printer;

/**
 *
 * @author adrianromero
 */
public class CurtainAnimator extends BaseAnimator {
    
    public CurtainAnimator(String line1, String line2) {
        super(line1, line2);
    }
    
    public void setTiming(int i) {
        
        int j = i / 2;

        if (j < 10) {
            currentLine1 = DeviceTicket.alignCenter(baseLine1.substring(10 - j, 10 + j), 20);
            currentLine2 = DeviceTicket.alignCenter(baseLine2.substring(10 - j, 10 + j), 20);
        } else {
            currentLine1 = baseLine1;
            currentLine2 = baseLine2;
        }
    }
}