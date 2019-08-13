/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.restaurant.*;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfo;
import javax.swing.table.AbstractTableModel;
import com.openbravo.pos.ticket.TicketInfo;
import java.awt.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author swathi
 */
public class QTItemTableModel extends AbstractTableModel {

    private static final String[] columnHeaders = new String[] {"Sl No", "Product", "Quantity", "Rate"};

    private Component parent;
    private DataLogicSales dlSales;
    private List<QTicketLineInfo> m_rows;

    public QTItemTableModel (Component parent, DataLogicSales dlSales) {
        this.parent = parent;
        this.dlSales = dlSales;
    }

    public void refresh(QticketInfo qticket) {
        this.m_rows = qticket == null || qticket.getLines() == null ? new ArrayList<QTicketLineInfo>() : qticket.getLines();
        fireTableDataChanged();
    }

    public String getColumnName(int column) {
        return columnHeaders[column];
    }

    public int getRowCount() {
        return m_rows != null ? m_rows.size() : 0;
    }
    public int getColumnCount() {
        return columnHeaders.length;
    }
    public Object getValueAt(int row, int column) {
       QTicketLineInfo i = m_rows.get(row);
            switch (column) {
                case 0: return i.getLine();
                case 1:
                    ProductInfo pInfo = LookupUtilityImpl.getInstance(null).getProductsMap().get(i.getProduct());
                    return pInfo != null ? pInfo.getName() : null;
                case 2: return i.getMultiply();
                case 3: return i.getRate();
                default: return null;
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
    
        public List<QTicketLineInfo> getLines() {
            return m_rows;
        }
    
        public QTicketLineInfo getRow(int index) {
            return m_rows.get(index);
        }
        
        public void setRow(int index, TicketInfo oLine){
            throw new UnsupportedOperationException("QT item list is not modifiable");
        }        
        
        public void addRow(QTicketLineInfo oLine) {
            
            insertRow(m_rows.size(), oLine);
        }
        
        public void insertRow(int index, QTicketLineInfo oLine) {
            
            m_rows.add(index, oLine);
            fireTableRowsInserted(index, index);
        }
        
        public void removeRow(int row) {
            m_rows.remove(row);
            fireTableRowsDeleted(row, row);
        }        
}

