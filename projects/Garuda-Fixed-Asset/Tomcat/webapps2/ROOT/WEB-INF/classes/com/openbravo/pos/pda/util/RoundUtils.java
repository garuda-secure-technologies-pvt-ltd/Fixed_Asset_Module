
package com.openbravo.pos.pda.util;

import java.text.NumberFormat;

public class RoundUtils {

    /** Creates a new instance of DoubleUtils */
    private RoundUtils() {
    }

    public static double round(double dValue) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        double fractionMultiplier = Math.pow(10.0, currencyFormat.getMaximumFractionDigits());
        return Math.rint(dValue * fractionMultiplier) / fractionMultiplier;
    }
}
