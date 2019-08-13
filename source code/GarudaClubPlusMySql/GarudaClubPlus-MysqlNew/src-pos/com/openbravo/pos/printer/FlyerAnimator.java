


package com.openbravo.pos.printer;

/**
 *
 * @author adrianromero
 */
public class FlyerAnimator extends BaseAnimator {
    
    public FlyerAnimator(String line1, String line2) {
        super(line1, line2);
    }
    
    public void setTiming(int i) {

        if (i < 20) {
            currentLine1 = DeviceTicket.alignRight(baseLine1.substring(0, i), 20);
            currentLine2 = DeviceTicket.alignRight(baseLine2.substring(0, i), 20);
        } else {
            currentLine1 = baseLine1;
            currentLine2 = baseLine2;
        }
    }
}
