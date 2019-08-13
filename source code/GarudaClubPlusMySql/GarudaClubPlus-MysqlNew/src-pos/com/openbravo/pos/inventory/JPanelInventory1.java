package com.openbravo.pos.inventory;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;

/**
 *
 * @author swathi
 */
public class JPanelInventory1 extends javax.swing.JPanel {


    private static String[] m_acolumns = new String[]{"Item","Price","Units","UnitType","Tax","Value"};
    private InventoryTableModel m_inventorylines;
    //private List<TicketLineInfo> m_aLines;
    private List<TicketTaxInfo> taxes;
    private List<PaymentInfo> payments;
    private List<InventoryLine> m_aLines;
    /** Creates new form JPanelInventory1 */
    public JPanelInventory1() {
        initComponents();
         DefaultTableColumnModel columns = new DefaultTableColumnModel();
        TableColumn c;
        
        c = new TableColumn(0, 120
                , new DataCellRenderer(javax.swing.SwingConstants.LEFT)
                , new DefaultCellEditor(new JTextField()));
        c.setHeaderValue(AppLocal.getIntString("label.Item"));
        columns.addColumn(c);
        c = new TableColumn(1, 50
                , new DataCellRenderer(javax.swing.SwingConstants.RIGHT)
                , new DefaultCellEditor(new JTextField()));
        c.setHeaderValue(AppLocal.getIntString("label.Price"));
        columns.addColumn(c);
        c = new TableColumn(2, 75
                , new DataCellRenderer(javax.swing.SwingConstants.RIGHT)
                , new DefaultCellEditor(new JTextField()));
        c.setHeaderValue(AppLocal.getIntString("label.Units"));
        columns.addColumn(c);
        c = new TableColumn(3, 75
                , new DataCellRenderer(javax.swing.SwingConstants.RIGHT)
                , new DefaultCellEditor(new JTextField()));
        c.setHeaderValue(AppLocal.getIntString("label.UnitType"));
        columns.addColumn(c);
        c = new TableColumn(4, 75
                , new DataCellRenderer(javax.swing.SwingConstants.RIGHT)
                , new DefaultCellEditor(new JTextField()));
        c.setHeaderValue(AppLocal.getIntString("label.Tax"));
        columns.addColumn(c);
          c = new TableColumn(5, 75
                , new DataCellRenderer(javax.swing.SwingConstants.RIGHT)
                , new DefaultCellEditor(new JTextField()));
        c.setHeaderValue(AppLocal.getIntString("label.Value"));
        columns.addColumn(c);

        m_tableinventory.setColumnModel(columns);

        m_tableinventory.getTableHeader().setReorderingAllowed(false);
        m_tableinventory.setRowHeight(40);
        m_tableinventory.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_tableinventory.setIntercellSpacing(new java.awt.Dimension(0, 1));

        m_inventorylines = new InventoryTableModel();
        m_tableinventory.setModel(m_inventorylines);
    }
 public void clear() {
        m_inventorylines.clear();
    }

    public void addLine(InventoryLine i) {
        m_inventorylines.addRow(i);
        setSelectedIndex(m_inventorylines.getRowCount() - 1);
    }

    public void deleteLine(int index) {
        m_inventorylines.removeRow(index);

        // Escojo una a seleccionar
        if (index >= m_inventorylines.getRowCount()) {
            index = m_inventorylines.getRowCount() - 1;
        }

        if ((index >= 0) && (index < m_inventorylines.getRowCount())) {
            // Solo seleccionamos si podemos.
            setSelectedIndex(index);
        }
    }

    public void setLine(int index, InventoryLine i) {
        m_inventorylines.setRow(index, i);
        setSelectedIndex(index);
    }

    public InventoryLine getLine(int index) {
        return m_inventorylines.getRow(index);
    }

    public List<InventoryLine> getLines() {
        return m_inventorylines.getLines();
    }

    public int getCount() {
        return m_inventorylines.getRowCount();
    }

    public int getSelectedRow() {
        return m_tableinventory.getSelectedRow();
    }

    public void setSelectedIndex(int i){

        // Seleccionamos
        m_tableinventory.getSelectionModel().setSelectionInterval(i, i);

        // Hacemos visible la seleccion.
        Rectangle oRect = m_tableinventory.getCellRect(i, 0, true);
        m_tableinventory.scrollRectToVisible(oRect);
    }

    public void goDown() {

        int i = m_tableinventory.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= m_inventorylines.getRowCount()) {
                i = m_inventorylines.getRowCount() - 1;
            }
        }

        if ((i >= 0) && (i < m_inventorylines.getRowCount())) {
            // Solo seleccionamos si podemos.

            setSelectedIndex(i);
        }
    }

    public void goUp() {
        int i = m_tableinventory.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = m_inventorylines.getRowCount() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < m_inventorylines.getRowCount())) {
            // Solo seleccionamos si podemos.
            setSelectedIndex(i);
        }
    }
     public boolean hasTaxesCalculated() {
        return taxes != null;
      }
     
   
    private static class InventoryTableModel extends AbstractTableModel {

//        private AppView m_App;
//        private ColumnTicket[] m_acolumns;
        private ArrayList<InventoryLine> m_rows = new ArrayList<InventoryLine>();

//        public TicketTableModel(AppView app, ColumnTicket[] acolumns) {
//            m_App = app;
//            m_acolumns = acolumns;
//        }
        public int getRowCount() {
            return m_rows.size();
        }
        public int getColumnCount() {
            return 6;
        }
        public String getColumnName(int column) {
           // return AppLocal.getIntString(m_acolumns[column].name);
            return m_acolumns[column];
           // return "a";
        }
        public Object getValueAt(int row, int column) {

            InventoryLine i = m_rows.get(row);
            switch (column) {
                case 0: return i.getProductName();
                case 1: return i.getPrice();
                case 2: return "x" + Formats.DOUBLE.formatValue(i.getMultiply());
                case 3: return i.getUnittype();
                case 4: return i.getM_dtax();
                case 5: return i.getPrice() * i.getMultiply();
               
                
               
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

        public List<InventoryLine> getLines() {
            return m_rows;
        }

        public InventoryLine getRow(int index) {
            return m_rows.get(index);
        }

        public void setRow(int index, InventoryLine oLine){

            m_rows.set(index, oLine);
            fireTableRowsUpdated(index, index);
        }

        public void addRow(InventoryLine oLine) {

            insertRow(m_rows.size(), oLine);
        }

        public void insertRow(int index, InventoryLine oLine) {

            m_rows.add(index, oLine);
            fireTableRowsInserted(index, index);
        }

        public void removeRow(int row) {
            m_rows.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
    private static class DataCellRenderer extends DefaultTableCellRenderer {

        private int m_iAlignment;

        public DataCellRenderer(int align) {
            m_iAlignment = align;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

            JLabel aux = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            aux.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            aux.setHorizontalAlignment(m_iAlignment);
            if (!isSelected) {
                aux.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.disabledBackground"));
            }
            return aux;
        }
    }
    //praveen:added method  to check the existing product---start

    public Double containsproduct(String s) {
        Double flag = 0.0;
        if (m_inventorylines.getRowCount() > 0) {
            for (int i = 0; i < m_inventorylines.getRowCount(); i++) {
                String str = m_inventorylines.getValueAt(i, 3).toString();
                System.out.println(str);
                if (str.equals(s)) {
                    flag = flag + Double.valueOf(m_inventorylines.getValueAt(i, 4).toString());
                }
            }
        }
        return flag;
    }
    public Double productrate(int index){
         Double str = 0.0;
         if (m_inventorylines.getRowCount() > 0) {
            str = Double.valueOf( m_inventorylines.getValueAt(index, 5).toString());

         }
         return str;
    }

    public double taxRate(int index) {
        Double str = 0.0;
         if (m_inventorylines.getRowCount() > 0) {
            str = Double.valueOf( m_inventorylines.getValueAt(index, 4).toString());

         }
         return str;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        m_tableinventory = new javax.swing.JTable();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        m_tableinventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        m_tableinventory.setName("m_tableinventory"); // NOI18N
        m_tableinventory.setShowVerticalLines(false);
        jScrollPane1.setViewportView(m_tableinventory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable m_tableinventory;
    // End of variables declaration//GEN-END:variables

}



