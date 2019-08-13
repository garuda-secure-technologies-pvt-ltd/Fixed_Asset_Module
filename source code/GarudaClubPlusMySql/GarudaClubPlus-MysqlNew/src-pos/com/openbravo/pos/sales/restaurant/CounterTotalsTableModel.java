/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales.restaurant;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class CounterTotalsTableModel extends AbstractTableModel{
//  public class BillListTableModel extends AbstractTableModel{
    private static final String[] columnHeaders = new String[] {"Sl No", "Name", "Amount"};


   // private List<BillInfo> m_rows;
    private List<CounterTotals> m_totals;
    public CounterTotalsTableModel (List<CounterTotals> totals) {
        this.m_totals=totals;
    }

    public void refresh(List<CounterTotals> totals) {
        this.m_totals=totals;
        fireTableDataChanged();
    }

     public String getColumnName(int column) {
        return columnHeaders[column];
    }

    public int getRowCount() {
        return m_totals.size();
    }

    public int getColumnCount() {
        return columnHeaders.length;
    }

    @SuppressWarnings("empty-statement")
    public Object getValueAt(int rowIndex, int columnIndex) {
        CounterTotals i = m_totals.get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return i.getName();
            case 2: return i.getAmount();
            default: return null;
        }
    }
//}

}
