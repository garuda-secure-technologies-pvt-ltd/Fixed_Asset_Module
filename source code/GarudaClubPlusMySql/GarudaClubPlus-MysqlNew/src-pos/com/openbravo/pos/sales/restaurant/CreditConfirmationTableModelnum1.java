/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales.restaurant;

import com.openbravo.pos.sales.restaurant.DebtBillList.CreditConfirmList;
import com.openbravo.pos.sales.restaurant.DebtBillListnum1.CreditConfirmListnum1;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class CreditConfirmationTableModelnum1 extends AbstractTableModel{
//  public class BillListTableModel extends AbstractTableModel{
    private static final String[] columnHeaders = new String[] {"Sl No","Ref No", "Date", "Member No", "Member Name","Amount","Bill No","Waiter","Created by"};


   // private List<BillInfo> m_rows;
    private List<CreditConfirmListnum1> list;
    public CreditConfirmationTableModelnum1 (List<CreditConfirmListnum1> list) {
        this.list=list;
    }

    public void refresh(List<CreditConfirmListnum1> list) {
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
        CreditConfirmListnum1 i = list.get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return i.getID();
            case 2: return i.getDate();
            case 3: return i.getCSearchkey();
            case 4: return i.getCustomer();
            case 5: return i.getAmount();
            case 6: return i.getBillref();
            case 7: return i.getWaiter();
            case 8: return i.getRuser();
            default: return null;
        }
    }
//}

}
