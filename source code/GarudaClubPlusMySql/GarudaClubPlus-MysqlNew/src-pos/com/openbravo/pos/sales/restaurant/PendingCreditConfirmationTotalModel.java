

package com.openbravo.pos.sales.restaurant;

import javax.swing.table.AbstractTableModel;
import com.openbravo.pos.sales.restaurant.DebtBillList.PendingCreditConfirmListT;
import java.util.List;

public class PendingCreditConfirmationTotalModel extends AbstractTableModel{
    
    
     private static final String[] columnHeaders = new String[] {"Sl No","Name", "Amount"};

    private List<PendingCreditConfirmListT> list;
    
    
    public PendingCreditConfirmationTotalModel (List<PendingCreditConfirmListT> list) {
        this.list=list;
    }
    
    
    public void refresh(List<PendingCreditConfirmListT> list) {
        this.list=list;
        fireTableDataChanged();
    }

     public String getColumnName(int column) {
        return columnHeaders[column];
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return columnHeaders.length;
    }

    @SuppressWarnings("empty-statement")
    public Object getValueAt(int rowIndex, int columnIndex) {
        PendingCreditConfirmListT i = list.get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return i.getWName();
            case 2: return i.getWAmount();
           
            default: return null;
        }
    }
    
}
