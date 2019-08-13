

package com.openbravo.pos.printer;

/**
 *
 * @author adrianromero
 */
public class ScrollAnimator extends BaseAnimator {
    
    public ScrollAnimator(String line1, String line2) {
        super(line1, line2);
    }

    public void setTiming(int i) {
        int j = (i / 2) % 40;
        if (j < 20) {
            currentLine1 = DeviceTicket.alignLeft(baseLine1.substring(j), 20);
            currentLine2 = DeviceTicket.alignLeft(baseLine2.substring(j), 20);
        } else {
            currentLine1 = DeviceTicket.alignRight(baseLine1.substring(0, j - 20), 20);
            currentLine2 = DeviceTicket.alignRight(baseLine2.substring(0, j - 20), 20);
        }
    }
}
