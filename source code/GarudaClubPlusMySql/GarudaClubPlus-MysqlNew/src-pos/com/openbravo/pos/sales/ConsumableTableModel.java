/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class ConsumableTableModel extends AbstractTableModel {

    private List<Consumable> m_rows = new ArrayList<Consumable>();
    private int typeOfBilling;
    private int j = 0;
    private static final String[] columnHeaders = new String[]{"Slno", "DeptName", "CreatedBy", "CreatedDate"};
    private static final String[] columnHeaders1 = new String[]{"Slno", "MemNo", "CreatedBy", "CreatedDate"};

    public ConsumableTableModel(List<Consumable> rows, int type) {
        this.m_rows = rows;
        this.typeOfBilling = type;
    }

    public void refresh(List<Consumable> rows) {
        this.m_rows = rows;
        fireTableDataChanged();
    }

    public String getColumnName(int column) {
        if (typeOfBilling == 0) {
            return columnHeaders[column];
        } else {
            return columnHeaders1[column];
        }
    }

    public int getRowCount() {
        return m_rows.size();
    }

    public int getColumnCount() {
        return columnHeaders.length;
    }

    public Object getValueAt(int row, int column) {
        Consumable i = m_rows.get(row);        
        switch (column) {
            case 0:
                return j++;
            case 1:
                return i.getDeptName();
            case 2:
                return i.getCreatedBy();
            case 3:
                return i.getCreatedDate();
            case 4:
                return i.getSharedTicketId();
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void clear() {
        int old = getRowCount();
        if (old > 0) {
            m_rows.clear();
            fireTableRowsDeleted(0, old - 1);
        }
    }

    public List<Consumable> getLines() {
        return m_rows;
    }

    public Consumable getRow(int index) {
        return m_rows.get(index);
    }

    public void addRow(Consumable oLine) {
        insertRow(m_rows.size(), oLine);
    }

    public void insertRow(int index, Consumable oLine) {
        m_rows.add(index, oLine);
        fireTableRowsInserted(index, index);
    }

    public void removeRow(int row) {
        m_rows.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
