/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales.restaurant;

//import com.openbravo.pos.sales.SharedTicketInfo;
import javax.swing.table.AbstractTableModel;
import com.openbravo.pos.ticket.TicketInfo;
//import java.awt.Component;
//import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;
import java.util.ArrayList;
//import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class IntroTableModel extends AbstractTableModel {

    private List<TicketInfo> m_rows = new ArrayList<TicketInfo>();
  //  private List<SharedTicketInfo> sharedTicketlist=new ArrayList<SharedTicketInfo>();
    private static final String[] columnHeaders = new String[] {"Customer", "Table", "Waiter","Floor","Last Qt Time"};

    public IntroTableModel (List<TicketInfo> rows) {
        this.m_rows = rows;
     //   this.sharedTicketlist=sharedTicketlist;
    }

    public void refresh(List<TicketInfo> rows) {
        this.m_rows = rows;
        fireTableDataChanged();
    }

    public String getColumnName(int column) {
        return columnHeaders[column];
    }

     



     public int getRowCount() {
        return m_rows.size();
    }
    public int getColumnCount() {
        return columnHeaders.length;
    }
    public Object getValueAt(int row, int column) {
       TicketInfo i = m_rows.get(row);
     //  SharedTicketInfo s=sharedTicketlist.get(row);
            switch (column) {
                case 0: return i.getCustomer().getSearchkey() + " - " +i.getName();
                case 1: return i.getPlace().getName();
                case 2: return i.getWaiter().getName();
                case 3: return i.getFloor().getName();
                case 4: return  i.getDate();
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
    
        public List<TicketInfo> getLines() {
            return m_rows;
        }
    
        public TicketInfo getRow(int index) {
            return m_rows.get(index);
        }
        
        public void setRow(int index, TicketInfo oLine){
            throw new UnsupportedOperationException("Existing Customer list is not modifiable");
        }        
        
        public void addRow(TicketInfo oLine) {
            
            insertRow(m_rows.size(), oLine);
        }
        
        public void insertRow(int index, TicketInfo oLine) {
            
            m_rows.add(index, oLine);
            fireTableRowsInserted(index, index);
        }
        
        public void removeRow(int row) {
            m_rows.remove(row);
            fireTableRowsDeleted(row, row);
        }        
}

