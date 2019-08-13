

package com.openbravo.pos.pda.util;

import java.text.NumberFormat;

/**
 *
 * @author jaroslawwozniak
 */
public class FormatUtils {

    public static String formatCurrency(Object value) {
        NumberFormat m_currencyformat = NumberFormat.getCurrencyInstance();
        return m_currencyformat.format(((Number) value).doubleValue());
    }

    public static String formatDouble(Object value) {
        NumberFormat doubleFormat = NumberFormat.getNumberInstance();

        return doubleFormat.format(((Number) value).doubleValue());
    }
}
