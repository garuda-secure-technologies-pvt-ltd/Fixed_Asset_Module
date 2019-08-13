/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.restaurant.*;
import com.openbravo.pos.ticket.CategoryInfo;
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
public class QTListTableModel extends AbstractTableModel {

    private static final String[] columnHeaders = new String[] {"Sl No", "QT Number", "Print Category","QT Date","Waiter"};

    private Component parent;
    private DataLogicSales dlSales;
    private List<QticketInfo> m_rows;

    public QTListTableModel (Component parent, DataLogicSales dlSales) {
        this.parent = parent;
        this.dlSales = dlSales;
    }

    public void refresh(List<QticketInfo> rows) {
        this.m_rows = rows;
        fireTableDataChanged();
    }

    public String getColumnName(int column) {
        return columnHeaders[column];
    }

    public int getRowCount() {
       // return m_rows.size();
                return m_rows != null ? m_rows.size() : 0;
    }
    public int getColumnCount() {
        return columnHeaders.length;
    }
    public Object getValueAt(int row, int column) {
        String waitername="";
       QticketInfo i = m_rows.get(row);
       
            switch (column) {
                case 0: return row + 1;
                case 1: return i.getId();
                case 2: return LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(i.getprCategory());
                case 3: return i.getCreatedDate().toString();
                case 4:  try {
                           AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                           Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                          , "SELECT NAME FROM WAITER WHERE ID=?"
                          ,SerializerWriteString.INSTANCE
                          ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(i.getWaiter());

                        if(obj== null)
                         waitername="";
                        else{
                         waitername=obj[0].toString();
                          }
                           return waitername;
                         }
                     catch(Exception e)
                      {return waitername;
                       }
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
    
        public List<QticketInfo> getLines() {
            return m_rows;
        }
    
        public QticketInfo getRow(int index) {
            return m_rows.get(index);
        }
        
        public void setRow(int index, TicketInfo oLine){
            throw new UnsupportedOperationException("QT list is not modifiable");
        }        
        
        public void addRow(QticketInfo oLine) {
            
            insertRow(m_rows.size(), oLine);
        }
        
        public void insertRow(int index, QticketInfo oLine) {
            
            m_rows.add(index, oLine);
            fireTableRowsInserted(index, index);
        }
        
        public void removeRow(int row) {
            m_rows.remove(row);
            fireTableRowsDeleted(row, row);
        }        
}

