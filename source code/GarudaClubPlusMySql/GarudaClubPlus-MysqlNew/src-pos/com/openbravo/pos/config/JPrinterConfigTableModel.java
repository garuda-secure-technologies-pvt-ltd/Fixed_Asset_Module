/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.config;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class JPrinterConfigTableModel extends AbstractTableModel {

    private static final String[] columnHeaders = new String[] {"Printer No", "Printer Configuration"};

    private Map<Integer, Map<String, String>> printersMap = new LinkedHashMap<Integer, Map<String, String>>();

    public int getRowCount() {
        return printersMap == null ? 0 : printersMap.size();
    }

    public int getColumnCount() {
        return columnHeaders.length;
    }

    public String getColumnName(int columnIndex) {
        return columnHeaders[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Map<String, String> printConfig = printersMap.get(rowIndex + 1);
        if (printConfig == null) {
             return null;
        }
        switch(columnIndex) {
            case 0 : return rowIndex + 1;
            default :
                StringBuffer config = new StringBuffer();
                for (String value : printConfig.values()) {
                    if (value != null && value.trim().length() > 0) {
                        config.append(value).append(':');
                    }
                }
                if (config.length() > 0) {
                    config.setLength(config.length() - 1); //Remove the trailing ':'
                }
                return config.toString();
        }
    }

    public void refresh(Map<Integer, Map<String, String>> printersMap) {
        this.printersMap = printersMap;
        fireTableDataChanged();
    }

}
