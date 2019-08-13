/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfo;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class BillItemTableModel extends AbstractTableModel{

    private static final String[] columnHeaders = new String[] {"Sl No", "Product", "Qty", "Rate", "Amount"};

    
    private List<BillLineInfo> m_rows;

    public BillItemTableModel (List<BillLineInfo> blinfo) {
        m_rows = blinfo;
    }

     public String getColumnName(int column) {
        return columnHeaders[column];
    }

     public void clear() {
            int old = getRowCount();
            if (old > 0) {
                m_rows.clear();
                fireTableRowsDeleted(0, old - 1);
            }
        }


    public int getRowCount() {
        return m_rows.size();
    }

    public int getColumnCount() {
        return columnHeaders.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BillLineInfo i = m_rows.get(rowIndex);
        ProductInfo p = i.getProduct();
        double del;
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return p.getName();
            case 2: return i.getMultiply();
            case 3: return i.getRate();
            case 4: return i.getamount();
            case 5: return i.getGtax1id();
            case 6: return i.getGtax1();
            case 7: return i.getGtax2id();
            case 8: return i.getGtax2cas();
            case 9: return i.getGtax2();
            case 10: return i.getGtax3id();
            case 11: return i.getGtax3cas();
            case 12: return i.getGtax3();
            default: return null;
        }


    }

}
