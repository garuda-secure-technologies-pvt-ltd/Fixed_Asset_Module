/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PurchaseJournal.java
 *
 * Created on Apr 7, 2009, 4:52:00 PM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.data.loader.DataRead;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AdditionalEntry;
import com.openbravo.pos.Accounts.PurchaseVoucherline;
import com.openbravo.pos.catalog.CatalogSelector;
import com.openbravo.pos.catalog.JCatalog;
import com.openbravo.pos.catalog.JCatalog1;
import com.openbravo.pos.clubmang.PurchaseJournalTable.PurchaseOrderedLine;
import com.openbravo.pos.clubmang.PurchaseJournalTable.purchase;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
//import com.openbravo.pos.sales.JPanelButtons;
import com.openbravo.pos.inventory.DisplayPurchaseOrderList;
import com.openbravo.pos.sales.TaxesLogic;

import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
//import java.awt.Container;
import java.awt.Dimension;
//import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
//import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.InputMethodEvent;
//import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
//import javax.swing.table.TableModel;
import com.openbravo.pos.clubmang.PurchaseLoadFrame;
/**
 *
 * @author swathi
 */
public class PurchaseJournal extends javax.swing.JPanel implements JPanelView, BeanFactoryApp, KeyListener {

    private CatalogSelector m_cat;
    private DataLogicSales m_dlSales;
    private ComboBoxValModel cModel;
    private ComboBoxValModel vModel;
    private ComboBoxValModel cashaccModel;
    private PurchaseJournalTable ptableModel;
    private int mode;//0=new creation mode,1=edit mode
    private List<PurchaseVoucherline> oldplist;
    private List<AdditionalEntry> oldalist;
    private TaxesLogic taxeslogic;
    private Component component;
    private DataLogicFacilities dlfac;
    private KeyboardFocusManager kfm;
    private String celldata = null;
    private AppView m_App;
    private String oldID;
    private Date oldDate;
    private String oldTransno;
    private Object[] oldValues;
    private JDialog dialog;
    private ComboBoxValModel pomodel;
    private List<purchase> ordererdline = new ArrayList<purchase>();
    private boolean addlEntryIndicator = false;
    
    private ComboBoxValModel taxCatModel;//added teju
    protected Session s; //added teju
    private List<String> taxCategories;
    private  JComboBox taxCatcombo;
    private String PurchaseIdSelected;
    
    List<String> purchase_date = new ArrayList<String>();
    
    
    public PurchaseJournal() {
        initComponents();
         
    }

    public void init(AppView app) throws BeanFactoryException {
        
        
        this.s=app.getSession();//added teju
        
        m_App = app;
        m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_cat = new JCatalog(m_dlSales);
        m_cat.getComponent().setPreferredSize(new Dimension(0, 245));
        m_cat.addActionListener(new CatalogListener());
        component = m_cat.getComponent();
        jPanel2.add(component, BorderLayout.CENTER);
        addKeyListener(this);
        mode = 0;
        oldID = null;
    //   this.addKeyListener(new KeyAdapter() {
    //     @Override
    //  public void keyReleased(java.awt.event.KeyEvent evt) {
    // formkeyReleased(evt);
    //    }});
       
      
        
        
    }

    public String getTitle() {
        return null;
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        // formkeyReleased(evt);
    }

    public void keyTyped(java.awt.event.KeyEvent evt) {
        // formkeyReleased(evt);
    }

    public void keyReleased(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            Date datenow;
            try {
                datenow = (Date) Formats.TIMESTAMP.parseValue(date.getText());
            } catch (BasicException e1) {
                datenow = null;
            }
            datenow = JCalendarDialog.showCalendar(this, datenow);
            if (datenow != null) {
                date.setText(Formats.TIMESTAMP.formatValue(datenow));
            }
        }
    }

    private void reset() throws BasicException {
        try{
        ptableModel = PurchaseJournalTable.loadInstance();
        // plist=ptableModel.getlist();
        PurchaseVoucherline pelement = new PurchaseVoucherline();
        ptableModel.getlist().add(pelement);
        jPanel1.setVisible(false);
        jTable1.setModel(ptableModel.getTableModel());
        TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        Calendar scal = Calendar.getInstance();
        scal.setTime(d);
        scal.set(Calendar.HOUR_OF_DAY, 00);
        scal.set(Calendar.MINUTE, 00);
        scal.set(Calendar.SECOND, 00);
        scal.set(Calendar.MILLISECOND, 00);
        Timestamp t = new Timestamp(d.getTime());
        String[] dstring = t.toString().split(" ");
        // String[] dstring= t.toString();
        date.setText(Formats.TIMESTAMP.formatValue(d));
        jLabel2.setText(dlfac.getPurchasetransactionnum(scal.getTime(), cal.getTime()));
       // warehouse.setSelectedIndex(-1);
       // warehouse.setSelectedItem("select");
       // List<CategoryInfo> cinfolist = new ArrayList();
        //cinfolist.add((CategoryInfo) warehouse.getSelectedItem());
        //m_cat.loadCatalog(cinfolist);
        amtTotal.setText("0.00");
        amountTotal.setText("0.00");
        grandtotal.setText("0.00");
        qtytotal.setText("0.00");
        jPanel2.setVisible(true);
        jLabel9.setVisible(true);
        amountTotal.setVisible(true);
        grandtotal.setVisible(true);
        jScrollPane3.setVisible(false);
        jPanel1.revalidate();
        invoiceno.setText(null);
        delivarychallan.setText(null);
        cashaccount.setSelectedIndex(-1);
        // cashaccount.showPopup();
        docref.setText(null);
        //warehouse.setSelectedIndex(-1);
        //alist=ptableModel.getadditionalEntrylist();
        AdditionalEntry aelement = new AdditionalEntry();
        ptableModel.getadditionalEntrylist().add(aelement);
        jTable3.setModel(ptableModel.getTableModelforAdditionalEntries());
        jLabel11.setVisible(true);
        jTable1.setRowSelectionInterval(0, 0);
        TableColumn col = jTable3.getColumnModel().getColumn(0);
        col.setPreferredWidth(300);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(80);
        col.setCellEditor(new MyTableCellEditor());
        pomodel = new ComboBoxValModel();
        if(mode!=1)
        purchaseorder.setEnabled(true);
        purchaseorder.setModel(pomodel);
        jButton3.setText("Addl. Charge");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void activate() throws BasicException {
      jCheckBox1.setSelected(false);
      
        addlEntryIndicator = false;
        //   jPanel1.setVisible(false);
        pomodel = new ComboBoxValModel();
        purchaseorder.setModel(pomodel);
        if (mode == 1)
        purchaseorder.setEnabled(false);
        else
            purchaseorder.setEnabled(true);
        docref.setEditable(false);
        qtytotal.setEditable(false);
        amtTotal.setEditable(false);
        amountTotal.setEditable(false);
        grandtotal.setEditable(false);
        cModel = new ComboBoxValModel(m_dlSales.getMainWarehouses());
        vModel = new ComboBoxValModel(dlfac.getVendorList());
        cashaccModel = new ComboBoxValModel(dlfac.getCashAndBanksubAccounts(""));
        cashaccount.setModel(cashaccModel);
        vendor.setModel(vModel);
        //warehouse.setSelectedItem("select warehouse");
        warehouse.setModel(cModel);
        // warehouse.setSelectedIndex(0);
        // List<CategoryInfo> cinfolist=new ArrayList();
        //  cinfolist.add((CategoryInfo)warehouse.getSelectedItem());
        //  m_cat.loadCatalog(cinfolist);
        taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
        vendor.getEditor().getEditorComponent().addKeyListener(new MyKeyListener());
        // if(flag)
        reset();
        // else
        //     ;
        jTable1.setBackground(Color.WHITE);
        TableColumnModel jColumns = jTable1.getColumnModel();
        displayColumns(jColumns);
//        jColumns.getColumn(0).setPreferredWidth(200);
//        jColumns.getColumn(0).setResizable(false);
//        jColumns.getColumn(1).setPreferredWidth(60);
//        jColumns.getColumn(1).setResizable(false);
//        jColumns.getColumn(2).setPreferredWidth(60);
//        jColumns.getColumn(2).setResizable(false);
//        jColumns.getColumn(3).setPreferredWidth(60);
//        jColumns.getColumn(3).setResizable(false);
//        jColumns.getColumn(4).setPreferredWidth(60);
//        jColumns.getColumn(4).setResizable(false);
//        jColumns.getColumn(5).setPreferredWidth(60);
//        jColumns.getColumn(5).setResizable(false);
//           jColumns.getColumn(6).setMinWidth(0);
//           jColumns.getColumn(6).setMaxWidth(0);
//           jColumns.getColumn(6).setResizable(false);
//           jColumns.getColumn(7).setMinWidth(0);
//           jColumns.getColumn(7).setMaxWidth(0);
//           jColumns.getColumn(7).setResizable(false);
//           jColumns.getColumn(8).setMinWidth(0);
//           jColumns.getColumn(8).setMaxWidth(0);
//           jColumns.getColumn(8).setResizable(false);
//           jColumns.getColumn(9).setMinWidth(0);
//           jColumns.getColumn(9).setMaxWidth(0);
//           jColumns.getColumn(9).setResizable(false);
//           jColumns.getColumn(10).setMinWidth(0);
//           jColumns.getColumn(10).setMaxWidth(0);
//           jColumns.getColumn(10).setResizable(false);
//           jColumns.getColumn(11).setMinWidth(0);
//           jColumns.getColumn(11).setMaxWidth(0);
//           jColumns.getColumn(11).setResizable(false);
//           jColumns.getColumn(12).setMinWidth(0);
//           jColumns.getColumn(12).setMaxWidth(0);
//           jColumns.getColumn(12).setResizable(false);
//        jColumns.getColumn(7).setPreferredWidth(60);
//        jColumns.getColumn(7).setResizable(false);
//        jColumns.getColumn(8).setPreferredWidth(60);
//        jColumns.getColumn(8).setResizable(false);
//           jColumns.getColumn(15).setMinWidth(0);
//           jColumns.getColumn(15).setMaxWidth(0);
//           jColumns.getColumn(15).setResizable(false);
//           jColumns.getColumn(16).setMinWidth(0);
//           jColumns.getColumn(16).setMaxWidth(0);
//           jColumns.getColumn(16).setResizable(false);
//        jColumns.getColumn(9).setPreferredWidth(60);
//        jColumns.getColumn(9).setResizable(false);
//        jColumns.getColumn(10).setPreferredWidth(60);
//        jColumns.getColumn(10).setResizable(false);
//           jColumns.getColumn(19).setMinWidth(0);
//           jColumns.getColumn(19).setMaxWidth(0);
//           jColumns.getColumn(19).setResizable(false);
//           jColumns.getColumn(20).setMinWidth(0);
//           jColumns.getColumn(20).setMaxWidth(0);
//           jColumns.getColumn(20).setResizable(false);
//           jColumns.getColumn(21).setMinWidth(0);
//           jColumns.getColumn(21).setMaxWidth(0);
//           jColumns.getColumn(21).setResizable(false);
//           jColumns.getColumn(22).setMinWidth(0);
//           jColumns.getColumn(22).setMaxWidth(0);
//           jColumns.getColumn(22).setResizable(false);
//        jColumns.getColumn(23).setPreferredWidth(60);
//        jColumns.getColumn(23).setResizable(false);
           

    //   TableColumn col = jTable3.getColumnModel().getColumn(0);
    //  col.setPreferredWidth(300);
    //  jTable3.getColumnModel().getColumn(1).setPreferredWidth(80);
    //  col.setCellEditor(new MyTableCellEditor());
    //     amtTotal.setText("0.0");
    //     qtytotal.setText("0.0");
    //     jPanel2.setVisible(true);
    //      jLabel9.setVisible(false);
    //      amountTotal.setVisible(false);
    //      grandtotal.setVisible(false);
    //       jList1.setVisible(false);
    //jPanel1.setVisible(false);
    //   TableColumn tcolumn=jTable1.getColumnModel().getColumn(2);
    //     tcolumn.setCellEditor(new javax.swing.JComboBox());
    //jTable1.getCellEditor().
    //  jTable1.getModel().addTableModelListener(this);

    //   jTable1.getColumnModel().getColumn(0).getc
    /*   KeyboardFocusManager kfm =
    KeyboardFocusManager.getCurrentKeyboardFocusManager();
    kfm.addKeyEventDispatcher(
    new KeyEventDispatcher() {
    public boolean dispatchKeyEvent(KeyEvent e) {
    String del=""+e.getKeyCode();
    if(e.getKeyCode() == KeyEvent.VK_DOWN) return true;
    return false;
    }});*/
    /* jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyPressed(java.awt.event.KeyEvent evt) {
    jTableKeyPressed(evt);
    }
    });*/
      


    }

    public void setValue(Object[] values, List<PurchaseVoucherline> purlist, List<AdditionalEntry> adlList, JDialog dialog) {
        this.dialog = dialog;
        mode = 1;
        oldValues = values;
        cModel.setSelectedKey(values[1]);
        vModel.setSelectedKey(values[0]);
        
        invoiceno.setText(values[2].toString());
        date.setText(Formats.TIMESTAMP.formatValue(values[3]));
        oldDate = (Date) values[3];
        oldTransno = values[6].toString();
        docref.setText(values[4].toString());
        delivarychallan.setText(values[5].toString());
        jLabel2.setText(values[6].toString());

        //cashaccount.set;
        boolean cashpayment = false;
        if (values[7] != null) {
            String cashacc = values[7].toString();
            cashaccModel.setSelectedKey(cashacc);
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
        } else {
            jRadioButton2.setSelected(true);
            jRadioButton1.setSelected(false);
        }
        // cashpayment=Boolean.parseBoolean(values[7].toString());
        oldID = values[8].toString();
        /*  if(cashpayment){
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
        }else{
        jRadioButton2.setSelected(true);
        jRadioButton1.setSelected(false);
        }*/

        //plist=ptableModel.getlist();
        ptableModel.getlist().clear();
        ptableModel.getlist().addAll(purlist);
        PurchaseVoucherline vline = new PurchaseVoucherline();
        ptableModel.getlist().add(vline);
        //alist=ptableModel.getadditionalEntrylist();
        ptableModel.getadditionalEntrylist().clear();
        ptableModel.getadditionalEntrylist().addAll(adlList);
        AdditionalEntry aline = new AdditionalEntry();
        ptableModel.getadditionalEntrylist().add(aline);
        oldplist = new ArrayList<PurchaseVoucherline>();
        for (PurchaseVoucherline line : ptableModel.getlist()) {
            oldplist.add(line.getCopy());
        }
        //oldplist.addAll(purlist);
        vline = new PurchaseVoucherline();
        oldplist.add(vline);
        oldalist = new ArrayList<AdditionalEntry>();
        for (AdditionalEntry line : ptableModel.getadditionalEntrylist()) {
            oldalist.add(line.getCopy());
        }
        //oldalist.addAll(adlList);
        aline = new AdditionalEntry();
        oldalist.add(aline);
        double qty = 0.0, amount = 0.0, addlamount = 0.0;
        for (PurchaseVoucherline line : ptableModel.getlist()) {
            amount += line.getTotalAmount();
            qty += line.getQty();
        // taxamount+=line.getTax();
        }
        for (AdditionalEntry line : ptableModel.getadditionalEntrylist()) {
            addlamount += line.getamount();
        }
        qtytotal.setText(String.valueOf(qty));
        amtTotal.setText(dlfac.ConvertDoubleToString(amount));
        amountTotal.setText(dlfac.ConvertDoubleToString(addlamount));
        grandtotal.setText(dlfac.ConvertDoubleToString(amount + addlamount));
        jTable1.setModel(ptableModel.getTableModel());
        TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
        jTable3.setModel(ptableModel.getTableModelforAdditionalEntries());
        jTable1.setRowSelectionInterval(0, 0);
        TableColumn col = jTable3.getColumnModel().getColumn(0);
        col.setPreferredWidth(300);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(80);
        col.setCellEditor(new MyTableCellEditor());
        purchaseorder.setEnabled(false);
    }

    private boolean checkforValidity(Date d) {
        try {
            Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
            GeneralSettingInfo sinfo = gs.get("Datestart");
            GeneralSettingInfo einfo = gs.get("Dateend");
            //String prevFySubIncome=gs.get("prevFySubIncome").getValue();
            // String nextFySubIncome=gs.get("nextFySubIncome").getValue();
            Date fsdate = (Date) Formats.DATE.parseValue(sinfo.getValue());
            Date fedate = (Date) Formats.DATE.parseValue(einfo.getValue());
            Calendar fscal = Calendar.getInstance();//financial year sdate
            fscal.setTime(fsdate);
            Calendar fecal = Calendar.getInstance();//financial year edate
            fecal.setTime(fedate);
            Calendar caltemp = Calendar.getInstance();
            caltemp.setTime(d);
            if (!caltemp.before(fscal) && !caltemp.after(fecal)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    class MyKeyListener extends KeyAdapter {

        public void keyReleased(KeyEvent evt) {
            
            vendor.showPopup();
        
        }
    }

    private void cellKeyPressed(java.awt.event.KeyEvent evt, Object text) throws BasicException {
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          
            int row = jTable3.getSelectedRow();
            jTable3.getModel().setValueAt(jList1.getSelectedValue().toString(), row, 0);
            AccountMaster acc = (AccountMaster) jList1.getSelectedValue();
            String id = acc.getid();
            addlEntryIndicator = false;
            jTable3.getModel().setValueAt(id, row, 2);
            // jList1.setVisible(false);
            jScrollPane3.setVisible(false);
            jPanel1.revalidate();
            //jScrollPane1.setVisible(false);
            jTable3.setModel(ptableModel.getTableModelforAdditionalEntries());
            TableColumn col = jTable3.getColumnModel().getColumn(0);
            col.setCellEditor(new MyTableCellEditor());
          
            jTable3.setRowSelectionInterval(row, row);
            
        } else {
            addlEntryIndicator = true;
            celldata = text.toString();
            List acclist = dlfac.getsubAccounts(text.toString().toUpperCase());
             jList1.removeAll();
            jList1.setModel(new ItemsListModel(acclist));
            jList1.setSelectedIndex(0);
            jList1.setVisible(true);
            jScrollPane3.setVisible(true);
            jPanel1.revalidate();
            jList1.requestFocus(true);
            
            // jScrollPane1.setVisible(true);
            //    jScrollPane3.setVisible(true);
            //    jPanel1.validate();
            // jPanel1.repaint();

            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        jList1.requestFocus();
                    }
                });
            }
        }
    }

    public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor, KeyListener {
        // This is the component that will handle the editing of the cell value

        JComponent component = new JTextField();

        // This method is called when a cell value is edited by the user.
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int rowIndex, int vColIndex) {
            // 'value' is value contained in the cell located at (rowIndex, vColIndex)
            component.addKeyListener(this);
            if (isSelected) {
                // cell (and perhaps other cells) are selected
                jTable1.clearSelection();
            }

            // Configure the component with the specified value
            ((JTextField) component).setText((String) value);

            // Return the configured component
            return component;
        }

        // This method is called when editing is completed.
        // It must return the new value to be stored in the cell.
        public Object getCellEditorValue() {
            return ((JTextField) component).getText();
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
            try {
                cellKeyPressed(e, getCellEditorValue());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public Object getBean() {
        return this;
    }

    private class ItemsListModel extends AbstractListModel {

        private java.util.List items;

        public ItemsListModel(java.util.List items) {
            this.items = items;
        }

        public int getSize() {
            return items.size();
        }

        public Object getElementAt(int i) {
            return items.get(i);
        }
    }
    //praveen:added to get quantity,rate and purchaseorderrf for the selected product

    private Object[] getDetails(String pid) {
        int qty = 0;
        double rate = 0.0;
        String s = "";
        try {
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT P.BALANCEQTY,P.RATE,PD.PURCHASEORDERREF FROM PURCHASEORDERDETAIL P,PURCHASEINDENTDETAILS PD  WHERE PD.PRODUCTID=? AND PD.PRODUCTID=P.PRODUCTID", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT, Datas.DOUBLE, Datas.STRING})).find(pid);
            if (obj != null) {
                if (obj[0] != null) {
                    qty = Integer.parseInt(obj[0].toString());
                }
                if (obj[1] != null) {
                    rate = Double.parseDouble(obj[1].toString());
                }
                if (obj[2] != null) {
                    s = obj[2].toString();
                }
            }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
        return new Object[]{qty, rate, s};

    }
//praveen:check the below logic...start

    private class CatalogListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                // incProduct(1.0, (ProductInfoExt) e.getSource());
                int x = jTable1.getRowCount();//edit
                jTable1.setRowSelectionInterval(x-1,x-1);//edit
                
                int row = jTable1.getSelectedRow();
                int column = jTable1.getSelectedColumn();
                Object[] obj = null;    
                ProductInfoExt pinfo = (ProductInfoExt) e.getSource();
                if (pinfo.getAccount() != null) {
                    String ven = vendor.getSelectedItem().toString();
                    if (mode != 1)
                            ordererdline = ptableModel.getPurchaseOrderedLine(m_App, pinfo.getID(), ven, docref.getText());
                    //ordererdline = ptableModel.getPdline1();
                    if (ordererdline.isEmpty()) {//praveen:here array index ut of bounds
                        if (JOptionPane.showConfirmDialog(null, "No pending purchase order.\r\n Do you want to still add the item", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            
                            ptableModel.getTableModel().setValueAt(pinfo.getName(), row, 0);
                            ptableModel.getTableModel().setValueAt(pinfo.getTaxCategoryName(), row, 4);
                            ptableModel.getTableModel().setValueAt(pinfo.getPriceBuy(), row, 2);
                            ptableModel.getTableModel().setValueAt(pinfo.getAccount(), row, 8);
                            TaxInfo tax = taxeslogic.getTaxInfo(pinfo.getTaxCategoryInfo());
                            //   pinfo.getPriceSellTax(tax);
                            ptableModel.getTableModel().setValueAt(tax.getRate(), row, 7);
                            ptableModel.getTableModel().setValueAt(pinfo.getID(), row, 9);//tax value for i qty
                            ptableModel.getTableModel().setValueAt(tax.getTaxCategoryID(), row, 10);
                            ptableModel.getTableModel().setValueAt(false, row, 12);
                           ////////////////////////////////////////////////////////////////////added by pratima to add tax2 and tax3 values in table
                          if(pinfo.getTaxCategoryName2()!=null){
                           ptableModel.getTableModel().setValueAt(pinfo.getTaxCategoryName2(), row, 13);
                            TaxInfo tax2 = taxeslogic.getTaxInfo(pinfo.getTaxCategoryInfo2());
                             ptableModel.getTableModel().setValueAt(tax2.getRate(), row, 15);
                             ptableModel.getTableModel().setValueAt(tax2.getTaxCategoryID(), row, 16);
                              ptableModel.getTableModel().setValueAt(pinfo.getBASIC2(), row, 21);
                              
                        }else{}
                          if(pinfo.getTaxCategoryName3()!=null){
                             ptableModel.getTableModel().setValueAt(pinfo.getTaxCategoryName3(), row, 17);
                            TaxInfo tax3 = taxeslogic.getTaxInfo(pinfo.getTaxCategoryInfo3());
                             ptableModel.getTableModel().setValueAt(tax3.getRate(), row, 19);
                             ptableModel.getTableModel().setValueAt(tax3.getTaxCategoryID(), row, 20);
                              ptableModel.getTableModel().setValueAt(pinfo.getBASIC3(), row, 22);
                               
                             }else{}
                           //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            List<PurchaseVoucherline> plist = ptableModel.getlist();
                            if ((jTable1.getRowCount()-1) == row) {
                                PurchaseVoucherline pelement = new PurchaseVoucherline();
                                plist.add(pelement);
                              
                               
                            }
                            jTable1.setModel(ptableModel.getTableModel());
                            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                           // jTable1.setRowSelectionAllowed(true);
                            
                       
                            
                            jTable1.setRowSelectionInterval(row, row);
                        
                          
                            
                        }
                       // jTable1.setColumnSelectionInterval(1,1);
                          
                         jTable1.setRowSelectionInterval(row, row);
                          //System.out.println(jTable1.getSelectedRow()+" "+jTable1.getSelectedColumn());
                    } else {
                        showAllPO(ordererdline);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Specify a Purchase Account to the product", null, JOptionPane.OK_OPTION);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        // jTable1.se
        //jTable1.getModel().addTableModelListener(this);
        }
    }

    private void showAllPO(List<purchase> pur) {
        try {
            DisplayPurchaseOrderList display = DisplayPurchaseOrderList.getDialog(this, m_App);
            display.showDialog(pur);
            int i = display.getResultok();
            if (i > 0) {
                for (PurchaseJournalTable.purchase pl : pur) {
                    if (pl.getPono() == i) {
                        int row = jTable1.getSelectedRow();
                        List<PurchaseVoucherline> pv = new ArrayList<PurchaseVoucherline>();
                        PurchaseVoucherline pvl;
                        ProductInfoExt pin = m_dlSales.getProductInfo(pl.getProduct());
                        if (pin.getAccount() != null) {
                            System.out.println(jTable1.getRowCount());
                            pvl = new PurchaseVoucherline();
                            pvl.setitem(pin.getName());
                            pvl.setQty(pl.getQty());
                            pvl.setRate(pl.getRate());
                            pvl.setPurchaseorderref(pl.getId());
                            pvl.setTaxcat(pin.getTaxCategoryName());
                            pvl.setAccount(pin.getAccount());
                            TaxInfo tax = taxeslogic.getTaxInfo(pin.getTaxCategoryInfo());
                            
                            //Lokesh Chaged this to set the tax as tax.getRate()*pvl.getamount()
                            pvl.setpdttaxvalue(tax.getRate());
                         
                          //  pvl.setTax(tax.getRate());
                            pvl.setitemid(pin.getID());
                            pvl.setTaxcatId(tax.getTaxCategoryID());
                            pvl.setamount(pl.getQty() * pl.getRate());
                            pvl.setTax(tax.getRate()*pvl.getamount());
                            pvl.setTotalAmount(pvl.getamount() + pvl.getTax());
                            pvl.setOqty(pl.getQty());
                            ptableModel.addPurchaseLine(pvl);
                            row++;
                            if(docref.getText().length()>0)
                            docref.setText(docref.getText() + "#" + String.valueOf(i));
                            else
                            docref.setText( String.valueOf(i));
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Specify a Purchase Account to the product " + pin.getName(), null, JOptionPane.OK_OPTION);
                        }
                        if(row==0){
                        pvl = new PurchaseVoucherline();
                        ptableModel.addPurchaseLine(pvl);
                        }
                        //pv.add(pvl);
                        // ptableModel.getlist().addAll(pv);//here its adding to new arraylist : ptableModel.getlist() method getting new arraylist
                        jTable1.setModel(ptableModel.getTableModel());
                        TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                        double d = 0.0;
                        int q = 0;
                        for (PurchaseVoucherline pvli : ptableModel.getlist()) {
                            d = d + pvli.getTotalAmount();
                            q = q + pvli.getQty();
                        }
                        qtytotal.setText(String.valueOf(q));
                        amtTotal.setText(String.valueOf(dlfac.roundTwoDecimals(d)));
                        if (grandtotal.isVisible() == true) {
                            grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(Double.parseDouble(amtTotal.getText()) + Double.parseDouble(amountTotal.getText()))));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //praveen:end
    private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            jTextField1.setText(null);
        } else if (cTrans == '+' || cTrans == '-') {
        } else if (cTrans == ' ' || cTrans == '=') {
        } else {

            jTextField1.setText(jTextField1.getText() + cTrans);
        }
    }

    private boolean isAlpha(String s) {
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            int c = (int) s.charAt(i);
            if ((c < 65 || c > 90) && (c < 47 || c > 58)) {
                return false;
            }
        }
        return true;
    }

    private void updatePurchaseJournalMainStockDiary(Date d, Vendor v, String id, double total) throws BasicException {
        String createdby = m_App.getAppUserView().getUser().getName();
        Object[] values = new Object[]{id, jLabel2.getText(), invoiceno.getText(), delivarychallan.getText(), docref.getText(), createdby, d, v.getKey(), total, cModel.getSelectedKey()};
        new PreparedSentence(m_App.getSession(), "INSERT INTO PURCHASEJOURNALMAIN (ID,TNO,INVOICENO,DELIVERYCHALLAN,DOCUMENTREF,CREATEDBY,CRDATE,VENDOR,TOTAL,WAREHOUSE) VALUES(?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(values);

    }

    private void updatePurchaseJournalnStockDiary(Date d, PurchaseVoucherline p, int qty, Vendor v, String id) throws BasicException {
        //for(PurchaseJournalTable.PurchaseVoucherline p:plist){

        String createdby = m_App.getAppUserView().getUser().getName();
        //Object[] values = new Object[]{UUID.randomUUID().toString(), p.getitemid(), qty, p.getRate(), p.getamount(), p.getTax(), id};
        // new PreparedSentence(m_App.getSession(), "INSERT INTO PURCHASEJOURNAL (ID,ITEM,QTY,RATE,TOTAL,TAXTOTAL,PARENT) VALUES(?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING})).exec(values);
        //praveen:inserting PURCHASEORDERREF to purchasejournal
      //  Object[] values = new Object[]{UUID.randomUUID().toString(), p.getitemid(), qty, p.getRate(), p.getamount(), p.getTax(), id, p.getPurchaseorderref(), p.isFlag()};
     //   new PreparedSentence(m_App.getSession(), "INSERT INTO PURCHASEJOURNAL (ID,ITEM,QTY,RATE,TOTAL,TAXTOTAL,PARENT,PURCHASEORDERREF,FLAG) VALUES(?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN})).exec(values);
       //pratima above query is replaced by below query to add tax2 and tax3
     Object[] values = new Object[]{UUID.randomUUID().toString(), p.getitemid(), qty, p.getRate(), p.getamount(), p.getTaxTotal(), id, p.getPurchaseorderref(), p.isFlag(),p.getTax(),p.getTax2(),p.getTax3()};
        new PreparedSentence(m_App.getSession(), "INSERT INTO PURCHASEJOURNAL (ID,ITEM,QTY,RATE,TOTAL,TAXTOTAL,PARENT,PURCHASEORDERREF,FLAG,TAX1,TAX2,TAX3) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN,Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).exec(values);
        
     if (p.getPurchaseorderref() != null) {
            new PreparedSentence(m_App.getSession(), "UPDATE PURCHASEORDERDETAIL SET BALANCEQTY=? WHERE PRODUCTID=? AND PURCHASEORDERID=?", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING, Datas.STRING}), null).exec(new Object[]{p.getOqty() - p.getQty(), p.getitemid(), p.getPurchaseorderref()});
        }

        /*
         *Object[] values=new Object[]{UUID.randomUUID().toString(),p.getitemid(),qty,p.getRate(),p.getTotalAmount(),jLabel2.getText(),invoiceno.getText(),delivarychallan.getText(),docref.getText(),createdby,d,v.getKey()};

        new PreparedSentence(m_App.getSession()
        , "INSERT INTO PURCHASEJOURNAL (ID,ITEM,QTY,RATE,TOTAL,TNO,INVOICENO,DELIVERYCHALLAN,DOCUMENTREF,CREATEDBY,CRDATE,VENDOR) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"
        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(values);

         * */
        CategoryInfo cinfo = (CategoryInfo) warehouse.getSelectedItem();
        // string qty=Integer.toString(p.getQty());
        Object[] param = new Object[]{UUID.randomUUID().toString(), d, 1, cinfo.getID(), p.getitemid(), Double.valueOf(Integer.toString(qty)), 0.0, createdby, jLabel2.getText(),id};
        new PreparedSentence(m_App.getSession(), "INSERT INTO STOCKDIARY (ID,DATENEW,REASON1,LOCATION1,PRODUCT1,UNITS1,PRICE1,CREATEDBY,RNO,PURCHASEID) VALUES(?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING,Datas.STRING})).exec(param);
        //  }(
        CategoryInfo catinfo = (CategoryInfo) warehouse.getSelectedItem();
        m_dlSales.updateStockVolume2(Double.parseDouble(String.valueOf(qty)), p.getitemid(), catinfo.getID());

    }

    private Date getDate(String date) throws BasicException {
        Date d = (Date) Formats.TIMESTAMP.parseValue(date);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return d;
    }

    private boolean isEdited() {
        boolean edited = false;
        for (PurchaseVoucherline line : ptableModel.getlist()) {
            boolean flag = true;
            if (line.getitem() != null) {
                for (PurchaseVoucherline oline : oldplist) {
                    if (line.getQty() == oline.getQty() && line.getTax() == oline.getTax() && line.getamount() == oline.getamount() && line.getitemid().equals(oline.getitemid())) {
                        //oldplist.remove(oline);
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }
            if (flag) {
                edited = true;
                return edited;
            }

        }
//        if(edited)
//            return edited;
        for (AdditionalEntry line : ptableModel.getadditionalEntrylist()) {
            boolean flag = true;
            if (line.getname() != null) {
                for (AdditionalEntry oline : oldalist) {
                    if (line.getAccount().equals(oline.getAccount()) && line.getamount() == oline.getamount()) {
                       // oldalist.remove(oline);
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }
            if (flag) {
                edited = true;
                return edited;
            }
        }
        //        if(edited)
//            return edited;
        if (vModel.getSelectedKey() != null && !vModel.getSelectedKey().equals(oldValues[0])) {
            edited = true;
        } else if (cModel.getSelectedKey() != null && !cModel.getSelectedKey().equals(oldValues[1])) {
            edited = true;
        } else if (!invoiceno.getText().equals(oldValues[2])) {
            edited = true;
        } else if (!Formats.TIMESTAMP.formatValue(oldValues[3]).equals(date.getText())) {
            edited = true;
        } else if (!docref.getText().equals(oldValues[4])) {
            edited = true;
        } else if (!delivarychallan.getText().equals(oldValues[5])) {
            edited = true;
        } else if (!jLabel2.getText().equals(oldValues[6])) {
            edited = true;
        } else if (oldValues[7] == null && jRadioButton1.isSelected()) {
            edited = true;
        } else if (oldValues[7] != null && jRadioButton2.isSelected()) {
            edited = true;
        } else if (oldValues[7] != null && !cashaccModel.getSelectedKey().equals(oldValues[7])) {
            edited = true;
        }


        return edited;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        qtytotal = new javax.swing.JTextField();
        amtTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        amountTotal = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vendor = new javax.swing.JComboBox();
        invoiceno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        docref = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        warehouse = new javax.swing.JComboBox();
        date = new javax.swing.JTextField();
        cashaccount = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        delivarychallan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        purchaseorder = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        load_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Barcode_text = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        grandtotal = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setAutoscrolls(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel8.setText("Total");

        qtytotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        amtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable3.setShowHorizontalLines(false);
        jTable3.setShowVerticalLines(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jTable3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable3FocusGained(evt);
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable3KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable3);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jLabel9.setText("Additional Charges");

        amountTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountTotal.setText("0.0");

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel3AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton2.setText("Enter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jNumberKeys1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))))
        );

        jLabel1.setText("Name");

        jLabel3.setText("Invoice No");

        vendor.setEditable(true);
        vendor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                vendorItemStateChanged(evt);
            }
        });
        vendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendorActionPerformed(evt);
            }
        });

        invoiceno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                invoicenoKeyReleased(evt);
            }
        });

        jLabel6.setText("Document Ref");

        jLabel7.setText("Warehouse");

        jLabel5.setText("Date");

        jLabel4.setText("Delivary Challan");

        warehouse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                warehouseItemStateChanged(evt);
            }
        });

        cashaccount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cashaccountItemStateChanged(evt);
            }
        });
        cashaccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashaccountActionPerformed(evt);
            }
        });

        jLabel10.setText("Cash Accounts");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Cash");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Credit");
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        delivarychallan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                delivarychallanKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Purchase NO :");

        jLabel13.setText("PO No");

        purchaseorder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                purchaseorderItemStateChanged(evt);
            }
        });

        jCheckBox1.setText("Barcode Mode");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        load_button.setText("<html>Load<br/> From<br/> App</html>");
        load_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        load_button.setMargin(new java.awt.Insets(2, 2, 2, 2));
        load_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_buttonActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("jLabel2");

        Barcode_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Barcode_textKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(purchaseorder, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(docref, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(invoiceno, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(vendor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cashaccount, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delivarychallan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(warehouse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(load_button, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Barcode_text, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(warehouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(invoiceno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1)
                    .addComponent(load_button, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(delivarychallan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cashaccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(purchaseorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel12)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Barcode_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jLabel11.setText("Grand Total");

        grandtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        grandtotal.setText("0.0");

        jButton3.setText("Addl. Charge");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel14.setText("Quantity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qtytotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(amountTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(amtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(qtytotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(amountTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel14))
                    .addComponent(amtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void warehouseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_warehouseItemStateChanged
        // TODO add your handling code here:
       
        
        
        try {
            if (warehouse.getSelectedIndex() != -1) {
                //  reset();
                List<CategoryInfo> cinfolist = new ArrayList();
                m_cat.loadCatalog(cinfolist);
                cinfolist.add((CategoryInfo) warehouse.getSelectedItem());
                m_cat.loadCatalog(cinfolist);
                if (ptableModel != null) {
                    ptableModel.getlist().clear();
                    ptableModel.getlist().add(new PurchaseVoucherline());
                    jTable1.setModel(ptableModel.getTableModel());
                    grandtotal.setText("0.00");
                    amountTotal.setText("0.00");
                    amtTotal.setText("0.00");
                    qtytotal.setText("0");
                   
                    vendor.requestFocus(true);
                    //vendor.setSelectedIndex(-1);
                     jTable1.setModel(ptableModel.getTableModel());
                            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_warehouseItemStateChanged

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DecimalFormat dFormat = new DecimalFormat("#.##");
        try {
            int row = jTable1.getSelectedRow();
            int row1 = jTable3.getSelectedRow();
            if (row >= 0 && row1 >= 0) {
                JOptionPane.showMessageDialog(this, "Please select only one table", "Cannot insert", JOptionPane.OK_OPTION);
            } else if (row >= 0) {

                int column = jTable1.getSelectedColumn();
                //  if(column==1){
                try {
                    if (jTextField1.getText().length() > 0) {
                        boolean bool = Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 12).toString());
                        if (!bool) {
                            if (column == 2) {   //Arun
                                Double qty = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                Double rate = dlfac.roundTwoDecimals(Double.valueOf(jTextField1.getText()).doubleValue());
                                Double Amount = dlfac.roundTwoDecimals(rate * qty);
                                
                                ptableModel.getTableModel().setValueAt(Amount, row, 3);
                                ptableModel.getTableModel().setValueAt(rate, row, 2);
                                Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                Double tax1 = dlfac.roundTwoDecimals(taxvalue1 * qty * rate);
                                ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                ////////////////////////////////////////////////////////////////pratima
                                Double total=Amount+tax1;
                               
                                Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());
                                 Double tax2=0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString())){
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 * Amount); 
                                }else{
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                total=total+tax2;
                                 Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                Double tax3 =0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString())){
                                 tax3 = dlfac.roundTwoDecimals(taxvalue3 * Amount);
                                }else{
                                     tax3 = dlfac.roundTwoDecimals(taxvalue3 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                total=total+tax3;
                                Double taxTotal=tax1+tax2+tax3;
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(total), row, 23);
                                 ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxTotal), row, 6);
                                 jTable1.setModel(ptableModel.getTableModel());
                                TableColumnModel jColumns = jTable1.getColumnModel();
                                displayColumns(jColumns);
                                ///////////////////////////////////////////////////////////////////////
                                
                               // ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);//commented by pratima
                               // jTable1.setModel(ptableModel.getTableModel());
                                if (jTable1.getRowCount() - 1 > row) {
                                    jTable1.setRowSelectionInterval(row, row + 1);
                                } else {
                                    jTable1.setRowSelectionInterval(row, row);
                                }
                                jTextField1.setText(null);
                         
                            } else if (column == 23) {  //Arun
                                double qty = Double.parseDouble(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                qty = Double.parseDouble(dFormat.format(qty));
                                double amt = 0.0, rate = 0.0,tax1=0.0,tax2=0.0,tax3=0.0,amttot=0.0;
                                if (qty > 0) {
//                                    amt = dlfac.roundTwoDecimals(Double.parseDouble(jTextField1.getText()));
//                                    amt = Double.parseDouble(dFormat.format(amt));
//                                    Double taxvalue = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
//                                    Double taxtotal = dlfac.roundTwoDecimals((taxvalue*100 * amt)/(100+(taxvalue*100)));
//                                    rate = dlfac.roundTwoDecimals((amt - taxtotal) / qty);
//                                    rate = Double.parseDouble(dFormat.format(rate));
//                                    ptableModel.getTableModel().setValueAt(rate, row, 2);
//                                    ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(amt - taxtotal), row, 3);
//                                    ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(amt), row, 6);
//                                    ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal), row, 5);
//                                    jTable1.setModel(ptableModel.getTableModel());
//                                    if (jTable1.getRowCount() - 1 > row) {
//                                        jTable1.setRowSelectionInterval(row + 1, row + 1);
//                                    } else {
//                                        jTable1.setRowSelectionInterval(row, row);
//                                    }
//                                    jTextField1.setText(null);
                                    amttot = dlfac.roundTwoDecimals(Double.parseDouble(jTextField1.getText()));
                                    amttot = Double.parseDouble(dFormat.format(amttot));
                                    Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                    Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());  
                                    Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                    boolean b2=Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString());
                                    boolean b3=Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString());
                                    if(b2 && b3){
                                        amt=amttot/(1+taxvalue1+taxvalue2+taxvalue3);
                                        tax1=amt*taxvalue1;
                                        tax2=amt*taxvalue2;
                                        tax3=amt*taxvalue3;
                                        rate=amt/qty;
                                         
                                    }else if(b2==true && b3==false){
                                        amt=amttot /(1+(taxvalue1+taxvalue2)+taxvalue3*(1+taxvalue1+taxvalue2));
                                        tax1=amt*taxvalue1;
                                        tax2=amt*taxvalue2;
                                        tax3=(amt+tax1)*taxvalue3;
                                        rate=amt/qty;
                                    }else if(b2==false && b3==true){
                                         amt=amttot /(1+taxvalue1+taxvalue2+taxvalue3+(taxvalue1*taxvalue2));
                                         tax1=amt*taxvalue1;
                                        tax2=(amt+tax1)*taxvalue2;
                                        tax3=amt*taxvalue3;
                                        rate=amt/qty;
                                    }
                                    ptableModel.getTableModel().setValueAt(rate, row, 2);
                                     ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(amt), row, 3);
                                         
                                           ptableModel.getTableModel().setValueAt(amttot , row, 23);
                                         ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                         ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                          ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                           ptableModel.getTableModel().setValueAt(tax1+tax2+tax3 , row, 6);
                                            jTable1.setModel(ptableModel.getTableModel());
                                        TableColumnModel jColumns = jTable1.getColumnModel();
                                          displayColumns(jColumns);
                                          jTextField1.setText(null);
                                }
                                //added by pratima
                            } else if(column == 3){ 
                                Double qty = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                Double Amount = dlfac.roundTwoDecimals(Double.valueOf(jTextField1.getText()).doubleValue());
                                Double rate = dlfac.roundTwoDecimals(Amount / qty);
                                ptableModel.getTableModel().setValueAt(Amount, row, 3);
                                ptableModel.getTableModel().setValueAt(rate, row, 2);
                                Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                Double tax1 = dlfac.roundTwoDecimals(taxvalue1 * qty * rate);
                                ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                ////////////////////////////////////////////////////////////////pratima
                                Double total=Amount+tax1;
                               
                                Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());
                                 Double tax2=0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString())){
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 * Amount); 
                                }else{
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                total=total+tax2;
                                 Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                Double tax3 =0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString())){
                                 tax3 = dlfac.roundTwoDecimals(taxvalue3 * Amount);
                                }else{
                                     tax3 = dlfac.roundTwoDecimals(taxvalue3 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                total=total+tax3;
                                Double taxTotal=tax1+tax2+tax3;
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(total), row, 23);
                                 ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxTotal), row, 6);
                                 jTable1.setModel(ptableModel.getTableModel());
                                TableColumnModel jColumns = jTable1.getColumnModel();
                                displayColumns(jColumns);
                                ///////////////////////////////////////////////////////////////////////
                                //ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);
                              //  jTable1.setModel(ptableModel.getTableModel());
                                if (jTable1.getRowCount() - 1 > row) {
                                    jTable1.setRowSelectionInterval(row + 1, row + 1);
                                } else {
                                    jTable1.setRowSelectionInterval(row, row);
                                }
                                jTextField1.setText(null);
                            }//ended by pratima
                            else {
                                Double qty = Double.valueOf(jTextField1.getText());
                                Double rate = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 2).toString());
                                Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                Double tax1 = dlfac.roundTwoDecimals(taxvalue1 * qty * rate);
                                Double Amount = dlfac.roundTwoDecimals(rate * qty);

                                ptableModel.getTableModel().setValueAt(Amount, row, 3);
                                ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                ////////////////////////////////////////////////////////////////pratima
                                Double total=Amount+tax1;
                               
                                Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());
                                 Double tax2=0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString())){
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 * Amount); 
                                }else{
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                total=total+tax2;
                                 Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                Double tax3 =0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString())){
                                 tax3 = dlfac.roundTwoDecimals(taxvalue3 * Amount);
                                }else{
                                     tax3 = dlfac.roundTwoDecimals(taxvalue3 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                total=total+tax3;
                                Double taxTotal=tax1+tax2+tax3;
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(total), row, 23);
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxTotal), row, 6);
                                ///////////////////////////////////////////////////////////////////////
                             //   ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);
                                ptableModel.getTableModel().setValueAt(Integer.valueOf(jTextField1.getText()), row, 1);
                                if (jTable1.getRowCount() - 1 == row) {
                                    PurchaseVoucherline pelement = new PurchaseVoucherline();
                                    ptableModel.getlist().add(pelement);
                                }
                               // jTable1.setModel(ptableModel.getTableModel());
                               ////////////////////////////////////////////////
                                jTable1.setModel(ptableModel.getTableModel());
                            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
//                            //////////////////////////////////////////////////////
                                jTable1.setRowSelectionInterval(row,row);
                                jTable1.setColumnSelectionInterval(column, column);
                                //jTable1.setRowSelectionInterval(row+1, row+1);//edited from row+1
                               
                                
                                jTextField1.setText(null);
                                
                            }
                        } else {
                            if (column == 2) {   //Arun
                                Double qty = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                Double rate = dlfac.roundTwoDecimals(Double.valueOf(jTextField1.getText()).doubleValue());
                                Double Amount = dlfac.roundTwoDecimals(rate * qty);
                                ptableModel.getTableModel().setValueAt(Amount, row, 3);
                                ptableModel.getTableModel().setValueAt(rate, row, 2);
                                Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                Double tax1 = dlfac.roundTwoDecimals(taxvalue1 * qty * rate);
                                ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                  ////////////////////////////////////////////////////////////////pratima
                                Double total=Amount+tax1;
                                
                                Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());
                                 Double tax2=0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString())){
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 * Amount); 
                                }else{
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 *total);
                                }
                                total=total+tax2;
                                 Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                Double tax3 =0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString())){
                                 tax3 = dlfac.roundTwoDecimals(taxvalue3 * Amount);
                                }else{
                                     tax3 = dlfac.roundTwoDecimals(taxvalue3 *total);
                                }
                                total=total+tax3;
                                Double taxTotal=tax1+tax2+tax3;
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(total), row, 23);
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxTotal), row, 6);
                                 jTable1.setModel(ptableModel.getTableModel());
                            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                                ///////////////////////////////////////////////////////////////////////
                               // ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);
                                //jTable1.setModel(ptableModel.getTableModel());
                                if (jTable1.getRowCount() - 1 > row) {
                                    jTable1.setRowSelectionInterval(row + 1, row + 1);
                                } else {
                                    jTable1.setRowSelectionInterval(row, row);
                                }
                                jTextField1.setText(null);
                                
                            } else if (column == 23) {  //Arun
                                double qty = Double.parseDouble(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                qty = Double.parseDouble(dFormat.format(qty));
                                double amt = 0.0, rate = 0.0,tax1=0.0,tax2=0.0,tax3=0.0,amttot=0.0;
                                if (qty > 0) {
//                                    amt = dlfac.roundTwoDecimals(Double.parseDouble(jTextField1.getText()));
//                                    amt = Double.parseDouble(dFormat.format(amt));
//                                    Double taxvalue = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
//                                    Double taxtotal = dlfac.roundTwoDecimals((taxvalue*100 * amt)/(100+(taxvalue*100)));
//                                    rate = dlfac.roundTwoDecimals((amt - taxtotal) / qty);
//                                    rate = Double.parseDouble(dFormat.format(rate));
//                                    ptableModel.getTableModel().setValueAt(rate, row, 2);
//                                    ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(amt - taxtotal), row, 3);
//                                    ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(amt), row, 6);
//                                    ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal), row, 5);
//                                    jTable1.setModel(ptableModel.getTableModel());
//                                    if (jTable1.getRowCount() - 1 > row) {
//                                        jTable1.setRowSelectionInterval(row + 1, row + 1);
//                                    } else {
//                                        jTable1.setRowSelectionInterval(row, row);
//                                    }
//                                    jTextField1.setText(null);
                                     amttot = dlfac.roundTwoDecimals(Double.parseDouble(jTextField1.getText()));
                                    amttot = Double.parseDouble(dFormat.format(amttot));
                                    Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                    Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());  
                                    Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                    boolean b2=Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString());
                                    boolean b3=Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString());
                                    if(b2 && b3){
                                        amt=amttot/(1+taxvalue1+taxvalue2+taxvalue3);
                                        tax1=amt*taxvalue1;
                                        tax2=amt*taxvalue2;
                                        tax3=amt*taxvalue3;
                                        rate=amt/qty;
                                         
                                    }else if(b2==true && b3==false){
                                        amt=amttot /(1+(taxvalue1+taxvalue2)+taxvalue3*(1+taxvalue1+taxvalue2));
                                        tax1=amt*taxvalue1;
                                        tax2=amt*taxvalue2;
                                        tax3=(amt+tax1)*taxvalue3;
                                        rate=amt/qty;
                                    }else if(b2==false && b3==true){
                                         amt=amttot /(1+taxvalue1+taxvalue2+taxvalue3+(taxvalue1*taxvalue2));
                                         tax1=amt*taxvalue1;
                                        tax2=(amt+tax1)*taxvalue2;
                                        tax3=amt*taxvalue3;
                                        rate=amt/qty;
                                    }
                                    ptableModel.getTableModel().setValueAt(rate, row, 2);
                                     ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(amt), row, 3);
                                         
                                           ptableModel.getTableModel().setValueAt(amttot , row, 23);
                                         ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                         ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                          ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                          ptableModel.getTableModel().setValueAt(tax1+tax2+tax3 , row, 6);
                                           jTable1.setModel(ptableModel.getTableModel());
                                         TableColumnModel jColumns = jTable1.getColumnModel();
                                        displayColumns(jColumns);
                                jTextField1.setText(null);
                                }
                                
                            }
                            //added by pratima
                            else if(column == 3){ 
                                Double qty = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                Double Amount = dlfac.roundTwoDecimals(Double.valueOf(jTextField1.getText()).doubleValue());
                                Double rate = dlfac.roundTwoDecimals(Amount / qty);
                                ptableModel.getTableModel().setValueAt(Amount, row, 3);
                                ptableModel.getTableModel().setValueAt(rate, row, 2);
                                Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                Double tax1 = dlfac.roundTwoDecimals(taxvalue1 * qty * rate);
                                ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                ////////////////////////////////////////////////////////////////pratima
                                Double total=Amount+tax1;
                               
                                Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());
                                 Double tax2=0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString())){
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 * Amount); 
                                }else{
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                total=total+tax2;
                                 Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                Double tax3 =0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString())){
                                 tax3 = dlfac.roundTwoDecimals(taxvalue3 * Amount);
                                }else{
                                     tax3 = dlfac.roundTwoDecimals(taxvalue3 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                total=total+tax3;
                                Double taxTotal=tax1+tax2+tax3;
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(total), row, 23);
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxTotal), row, 6);
                                 jTable1.setModel(ptableModel.getTableModel());
                            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                                ///////////////////////////////////////////////////////////////////////
                                //ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);
                                //jTable1.setModel(ptableModel.getTableModel());
                                if (jTable1.getRowCount() - 1 > row) {
                                    jTable1.setRowSelectionInterval(row + 1, row + 1);
                                } else {
                                    jTable1.setRowSelectionInterval(row, row);
                                }
                                jTextField1.setText(null);
                            }//ended by pratima
                            else {
                                Double qty = Double.valueOf(jTextField1.getText());
                                Double oqty = Double.parseDouble(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                if (qty <= oqty || qty>oqty ) {
                                    Double rate = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 2).toString());
                                    Double taxvalue1 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                    Double tax1 = dlfac.roundTwoDecimals(taxvalue1 * qty * rate);
                                    Double Amount = dlfac.roundTwoDecimals(rate * qty);
                                    ptableModel.getTableModel().setValueAt(Amount, row, 3);
                                    ptableModel.getTableModel().setValueAt(tax1, row, 5);
                                    ////////////////////////////////////////////////////////////////pratima
                                Double total=Amount+tax1;
                               
                                Double taxvalue2 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 15).toString());
                                 Double tax2=0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 21).toString())){
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 * Amount); 
                                }else{
                                     tax2 = dlfac.roundTwoDecimals(taxvalue2 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax2, row, 14);
                                total=total+tax2;
                                 Double taxvalue3 = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 19).toString());
                                Double tax3 =0.00;
                                if(Boolean.valueOf(ptableModel.getTableModel().getValueAt(row, 22).toString())){
                                 tax3 = dlfac.roundTwoDecimals(taxvalue3 * Amount);
                                }else{
                                     tax3 = dlfac.roundTwoDecimals(taxvalue3 *total);
                                }
                                 ptableModel.getTableModel().setValueAt(tax3, row, 18);
                                total=total+tax3;
                                Double taxTotal=tax1+tax2+tax3;
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(total), row, 23);
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxTotal), row, 6);
                                ///////////////////////////////////////////////////////////////////////
                                 //   ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);
                                    ptableModel.getTableModel().setValueAt(Integer.valueOf(jTextField1.getText()), row, 1);
                                    if (jTable1.getRowCount() - 1 == row) {
                                        PurchaseVoucherline pelement = new PurchaseVoucherline();
                                        ptableModel.getlist().add(pelement);
                                    }
                                    //jTable1.setModel(ptableModel.getTableModel());
                                    ////////////////////////////////////////////////////////
                                     jTable1.setModel(ptableModel.getTableModel());
                            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                            /////////////////////////////////////////////////////////////
                                    jTable1.setRowSelectionInterval(row + 1, row + 1);
                                    jTextField1.setText(null);
                                    int qtytemp = 0;
                                    Double amttemp = 0.0;
                                    for (int i = 0; i < ptableModel.getlist().size(); i++) {
                                        qtytemp += ptableModel.getlist().get(i).getQty();
                                       amttemp += ptableModel.getlist().get(i).getamount() + ptableModel.getlist().get(i).getTax();
                                    
                                    }
                                    qtytotal.setText(String.valueOf(qtytemp));
                                    amtTotal.setText(String.valueOf(dlfac.roundTwoDecimals(amttemp)));
                                    if (grandtotal.isVisible() == true) {
                                        grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(Double.parseDouble(amtTotal.getText()) + Double.parseDouble(amountTotal.getText()))));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Qty should be less than " + oqty, "Error", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                        int qtytemp = 0;
                        Double amttemp = 0.0;
                        for (int i = 0; i < ptableModel.getlist().size(); i++) {
                            qtytemp += ptableModel.getlist().get(i).getQty();
                         //   amttemp += ptableModel.getlist().get(i).getamount() + ptableModel.getlist().get(i).getTax();
                             amttemp += ptableModel.getlist().get(i).getTotalAmount() ;
                        }
                                qtytotal.setText(String.valueOf(qtytemp));
                                amtTotal.setText(String.valueOf(dlfac.roundTwoDecimals(amttemp)));
                                if (grandtotal.isVisible() == true) {
                                    grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(Double.parseDouble(amtTotal.getText()) + Double.parseDouble(amountTotal.getText()))));
                                }
                                
                               
                                if(column==2)
                                {
                                    jTable1.requestFocus(true);
                                jTable1.setRowSelectionInterval(row,row);
                                jTable1.setColumnSelectionInterval(6,6);
                                }
                               // else if(column==6)
                               else if(column==23)
                                        {
                                             jTable1.requestFocus(true);
                                jTable1.setRowSelectionInterval(row+1,row+1);
                                jTable1.setColumnSelectionInterval(0,0); 
                                        }
                                else if(column<2){
                                  jTable1.requestFocus(true);
                                jTable1.setRowSelectionInterval(row,row);
                                jTable1.setColumnSelectionInterval(column+1, column+1);
                                }
                    
                    }
                    else{   
                              if(column<=2){
                                jTable1.requestFocus(true);
                                jTable1.setRowSelectionInterval(row,row);
                                jTable1.setColumnSelectionInterval(6,6);
                              }
                              //  if(column==6)
                               if(column==23)  {
                                   jTable1.requestFocus(true);
                                jTable1.setRowSelectionInterval(row+1,row+1);
                                jTable1.setColumnSelectionInterval(0,0); 
                                }
                    }
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid Number.Quantity Cannot be a decimal value", "Error", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Please Select a product", "Error", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }
            } else if (row1 >= 0) {
                if (jTextField1.getText().length() > 0) {
                    jTable3.getModel().setValueAt(Double.valueOf(jTextField1.getText()), row1, 1);
                    if ((jTable3.getRowCount() - 1) == row1) {
                        AdditionalEntry aelement = new AdditionalEntry();
                        ptableModel.getadditionalEntrylist().add(aelement);
                    }
                    jTable3.setModel(ptableModel.getTableModelforAdditionalEntries());
                    TableColumn col = jTable3.getColumnModel().getColumn(0);
                    col.setCellEditor(new MyTableCellEditor());

                    Double amt = 0.0;
                    for (int i = 0; i < ptableModel.getadditionalEntrylist().size(); i++) {
                        amt += ptableModel.getadditionalEntrylist().get(i).getamount();
                    }
                    amountTotal.setText(String.valueOf(dlfac.roundTwoDecimals(amt)));
                    grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(Double.parseDouble(amtTotal.getText()) + amt)));
                    jTextField1.setText(null);
                    jTable3.requestFocus(true);
                    
                    jTable3.setRowSelectionInterval(row1 + 1, row1 + 1);
                    jTable3.setColumnSelectionInterval(0,0);
                }
                
               
                jTable3.requestFocus(true);
                jTable3.setRowSelectionInterval(row1 + 1, row1 + 1);
                jTable3.setColumnSelectionInterval(0,0);
                
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this, "Invalid Number", "Error", JOptionPane.OK_OPTION);
            e1.printStackTrace();
        }
                      
        
     /*   int x = jTable1.getSelectedRow();
        int y = jTable1.getSelectedColumn();
        
        if(y!=6){
            jTable1.setRowSelectionInterval(x-1, x-1);
            jTable1.setColumnSelectionInterval(y+1, y+1);
            
            jTable1.changeSelection(x-1, y+1 ,true, true);
        }
        else{
            jTable1.setRowSelectionInterval(x+1, x+1);
            jTable1.setColumnSelectionInterval(0, 0);
        }*/
        
        
    }//GEN-LAST:event_jButton2ActionPerformed
    //   private javax.swing.JList list;
    //   javax.swing.JTable jTable2;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jPanel1.isVisible() == false) {
            jButton3.setText("Pdt. List");
            jLabel11.setVisible(true);
            jPanel2.setVisible(false);
            jPanel1.setVisible(true);
            jLabel9.setVisible(true);
            amountTotal.setVisible(true);
            grandtotal.setVisible(true);
            if (ptableModel.getadditionalEntrylist().size() > 0) {
                jTable3.setRowSelectionInterval(ptableModel.getadditionalEntrylist().size() - 1, ptableModel.getadditionalEntrylist().size() - 1);
            } else {
                jTable3.setRowSelectionInterval(ptableModel.getadditionalEntrylist().size(), ptableModel.getadditionalEntrylist().size());
            }
           
           
        } else {
            jPanel1.setVisible(false);
            jPanel2.setVisible(true);
            jButton3.setText("Addl. Charge");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        // TODO add your handling code here:
        int row = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             row = jTable3.getSelectedRow();
            jTable3.getModel().setValueAt(jList1.getSelectedValue().toString(), row, 0);
            AccountMaster acc = (AccountMaster) jList1.getSelectedValue();
            String id = acc.getid();
            jTable3.getModel().setValueAt(id, row, 2);
            // jList1.setVisible(false);
            jScrollPane3.setVisible(false);
            jPanel1.revalidate();
            /*   if((jTable3.getRowCount()-2)==row){
            PurchaseJournalTable.AdditionalEntry aelement=new PurchaseJournalTable.AdditionalEntry();
            alist.add(aelement);
            }*/
            AdditionalEntry aelement = new AdditionalEntry();
            ptableModel.getadditionalEntrylist().add(aelement);
            jTable3.setModel(ptableModel.getTableModelforAdditionalEntries());
            TableColumn col = jTable3.getColumnModel().getColumn(0);
            col.setCellEditor(new MyTableCellEditor());
            addlEntryIndicator = false;
             jTable3.requestFocus(true);
            jTable3.setRowSelectionInterval(row, row);
            jTable3.addColumnSelectionInterval(0,0);
        }
            //jTable3.requestFocus(true);
           // jTable3.setRowSelectionInterval(row, row);
           // jTable3.addColumnSelectionInterval(0,0);
    }//GEN-LAST:event_jList1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();
        //  int column=jTable3.getSelectedColumn();
        jTable3.getModel().setValueAt(jList1.getSelectedValue().toString(), row, 0);
        AccountMaster acc = (AccountMaster) jList1.getSelectedValue();
        addlEntryIndicator = false;
        String id = acc.getid();
        jTable3.getModel().setValueAt(id, row, 2);
        //  jList1.setVisible(false);
        //jScrollPane1.setVisible(false);
        jScrollPane3.setVisible(false);
        jPanel1.revalidate();
        /*  if((jTable3.getRowCount()-2)==row){
        PurchaseJournalTable.AdditionalEntry aelement=new PurchaseJournalTable.AdditionalEntry();
        alist.add(aelement);
        }*/
        AdditionalEntry aelement = new AdditionalEntry();
        ptableModel.getadditionalEntrylist().add(aelement);
        jTable3.setModel(ptableModel.getTableModelforAdditionalEntries());
        TableColumn col = jTable3.getColumnModel().getColumn(0);
        col.setCellEditor(new MyTableCellEditor());
         jTable3.requestFocus(true);
        jTable3.setRowSelectionInterval(row, row);
         jTable3.addColumnSelectionInterval(0,0);
    // celldata=null;
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(addlEntryIndicator){
            JOptionPane.showMessageDialog(null, "Please select the account in additionall charges");
             if (jPanel1.isVisible() == false) {
            jButton3.setText("Pdt. List");
            jLabel11.setVisible(true);
            jPanel2.setVisible(false);
            jPanel1.setVisible(true);
            jLabel9.setVisible(true);
            amountTotal.setVisible(true);
            grandtotal.setVisible(true);
            if (ptableModel.getadditionalEntrylist().size() > 0) {
                jTable3.setRowSelectionInterval(ptableModel.getadditionalEntrylist().size() - 1, ptableModel.getadditionalEntrylist().size() - 1);
            } else {
                jTable3.setRowSelectionInterval(ptableModel.getadditionalEntrylist().size(), ptableModel.getadditionalEntrylist().size());
            }
        }
            
        }else{
        if (vendor.getSelectedIndex() != -1 && invoiceno.getText().length() > 0  && date.getText().length() > 0 && delivarychallan.getText().length() > 0) {
            try {
                Date d = (Date) Formats.DATE.parseValue(date.getText());
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(d.getTime());
                Calendar fy_scal = Calendar.getInstance();
                Calendar fy_ecal = Calendar.getInstance();
                Map<String, GeneralSettingInfo> gsinfo = LookupUtilityImpl.getInstance(m_App).getGeneralSettingsMap();
                Date fy_sdate = (Date) Formats.DATE.parseValue(gsinfo.get("Datestart").getValue());
                Date fy_edate = (Date) Formats.DATE.parseValue(gsinfo.get("Dateend").getValue());
                fy_ecal.setTimeInMillis(fy_edate.getTime());
                fy_ecal.add(Calendar.DATE, 1);
                fy_scal.setTimeInMillis(fy_sdate.getTime());
                if (cal.before(fy_scal) || !cal.before(fy_ecal)) {
                    JOptionPane.showMessageDialog(this, "Please check the date.The Specified Date does not come under the present financial year", "Sorry Cannot Save", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean flag1 = false;
                    if (mode == 1) {
                        flag1 = isEdited();

                    } else {
                        flag1 = true;
                    }
                    if (flag1 == true) {
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                Date d = new Date();
                                String tid = UUID.randomUUID().toString();
                                Date pdate = (Date) Formats.DATE.parseValue(date.getText());
//                                purchase_date=(List<String>) new StaticSentence(m_App.getSession(), " select  pdate from accountjournal", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
//                                Object[] purchase_date=(Object[]) new StaticSentence(m_App.getSession(), " select date from accountjournal where date =? and active='1'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(pdate);
//                                int y = purchase_date.length;
//                                if(y>=1){
//                                    String x = String.valueOf(y+1);
//                                    jLabel2.setText(x);
//                                }
                                String transref = "Purchase Journal";
                                double amt = 0.0;
                                Vendor v = (Vendor) vendor.getSelectedItem();
                                if (grandtotal.isVisible() == true) {
                                    amt = dlfac.roundTwoDecimals(Double.valueOf(grandtotal.getText()));   //Arun
                                } else {
                                    amt = dlfac.roundTwoDecimals(Double.valueOf(amtTotal.getText()));
                                }
                                /* String cashiercashacc=m_App.getAppUserView().getUser().getcashaccount();
                                String cashierchequeacc=m_App.getAppUserView().getUser().getchequeaccount();
                                List cashieracc=dlfac.getUsersCashAndChequeAccount();
                                cashieracc.remove(cashiercashacc);
                                cashieracc.remove(cashierchequeacc);*/
                                
                                //********************************************************************* LOOP ********************** 
                                
                                if (mode == 1) {
                                    //Update Purchase journal
                                    new PreparedSentence(m_App.getSession(), "UPDATE PURCHASEJOURNALMAIN SET DEACTDATE=?,DEACTBY=?,DEACTREF=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{new Date(), m_App.getAppUserView().getUser().getId(), tid, oldID});
                                    //update accountjournal
                                    new PreparedSentence(m_App.getSession(), "UPDATE ACCOUNTJOURNAL SET ACTIVE=?,DEACTDATE=?,DEACTBY=? WHERE TID=? ", new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[]{false, new Date(), m_App.getAppUserView().getUser().getName(), oldID});
                                    //update ajperiodtotals
                                    List<Object[]> objList = new PreparedSentence(m_App.getSession(), "SELECT ACCOUNTID,AMOUNT,TRANSTYPE,DATE FROM ACCOUNTJOURNAL WHERE TID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP})).list(oldID);
                                    for (Object[] obj : objList) {
                                        if (obj != null) {
                                            Date d1 = (Date) obj[3];
                                            Calendar cal = Calendar.getInstance();
                                            cal.setTimeInMillis(d1.getTime());
                                            cal.set(Calendar.HOUR_OF_DAY, 00);
                                            cal.set(Calendar.MINUTE, 00);
                                            cal.set(Calendar.SECOND, 00);
                                            cal.set(Calendar.MILLISECOND, 00);
                                            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                                            d1.setTime(cal.getTimeInMillis());
                                            if (obj[2].toString().equals("C")) {
                                                new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURCREDIT=(CURCREDIT-?) where ACCOUNTID=? AND EDATE=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP})).exec(new Object[]{obj[1], obj[0], d1});
                                                new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURCREDIT=(CURCREDIT-?) where ACCOUNTID=?  ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{obj[1], obj[0]});
                                            } else {
                                                new PreparedSentence(m_App.getSession(), "UPDATE AJPERIODTOTALS SET CURDEBIT=(CURDEBIT-?) where ACCOUNTID=? AND EDATE=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP})).exec(new Object[]{obj[1], obj[0], d1});
                                                new PreparedSentence(m_App.getSession(), "UPDATE TRAILBALANCE SET CURDEBIT=(CURDEBIT-?) where ACCOUNTID=?  ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{obj[1], obj[0]});
                                            }
                                        }
                                    }
                                    
                                   
                                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM STOCKDIARY where PURCHASEID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(oldID);
                                    if(obj1!=null){
                                       new PreparedSentence(m_App.getSession(), "DELETE FROM STOCKDIARY WHERE DATENEW=? AND RNO=? AND PURCHASEID=? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING , Datas.STRING })).exec(new Object[]{oldDate, oldTransno,oldID});
                                    }
                                    else{
                                        new PreparedSentence(m_App.getSession(), "DELETE FROM STOCKDIARY WHERE DATENEW=? AND RNO=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{oldDate, oldTransno});
                                    }
                                    
                                    
                                    for (PurchaseVoucherline line : oldplist) {
                                            new PreparedSentence(m_App.getSession(), "UPDATE STOCKCURRENT SET UNITS=(UNITS-?) WHERE PRODUCT=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Double.valueOf(String.valueOf(line.getQty())), line.getitemid()});
                                    } 
                                    
                                }
                                
                                
                              //********************************************************************* LOOP ENDS **********************    
                                
                                if (jRadioButton1.isSelected() == true) {
                                    AccountMaster acc = (AccountMaster) cashaccount.getSelectedItem();
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), tid, pdate, "C", transref, jLabel2.getText(), amt, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Purchase", acc.getid(), amt, d, d, true, oldID};
                                    dlfac.insertintoaccjoutnal6(value);
                                /*  if( cashieracc.contains(acc.getid())){
                                new PreparedSentence(m_App.getSession()
                                , "INSERT INTO GENERALTABLE (ID,NAME,VALUE,CREATEDBY) VALUES (?,?,?,?)"
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})
                                ).exec(new Object[]{UUID.randomUUID().toString(),acc.getid(),tid,m_App.getAppUserView().getUser().getName()});
                                }*/
                                } else if (jRadioButton2.isSelected() == true) {
                                    //ID,TID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,DATEOFENTRY,ACTIVE
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), tid, pdate, "C", transref, jLabel2.getText(), amt, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Purchase", v.getAccount(), amt, d, d, true, oldID};
                                    dlfac.insertintoaccjoutnal6(value);
                                }
                                // updatePurchaseJournalnStockDiary(d);
                                // updateStockDiary(d);

                                List<PurchaseVoucherline> purchaselist = ptableModel.getlist();
                                int i = 0;
                                int size = purchaselist.size();
                                updatePurchaseJournalMainStockDiary(pdate, v, tid, Double.valueOf(amtTotal.getText()));
                                // while(i<size-1){
                                
                                
 // ********************************************************** LOOP ********************************************
                                
                                for (PurchaseVoucherline p : purchaselist) {
                                    //  PurchaseJournalTable.PurchaseVoucherline p=purchaselist.get(i);
                                    //  purchaselist.remove(p);
                                    size = size - 1;
                                    Double amount = p.getamount();
                                    int qty = p.getQty();
                                    // List<PurchaseJournalTable.PurchaseVoucherline> purchaselisttemp=new ArrayList<PurchaseJournalTable.PurchaseVoucherline>();
                                    // purchaselisttemp.addAll(purchaselist);
            /*for(PurchaseJournalTable.PurchaseVoucherline ptemp:purchaselisttemp){
                                    Boolean flag=p.getitem().equals(ptemp.getitem());
                                    if(flag){
                                    purchaselist.remove(ptemp);
                                    size=size-1;
                                    amount+=ptemp.getTotalAmount();
                                    qty+=ptemp.getQty();
                                    }
                                    }*/
                                    // purchaselist.
                                    if(amount>0 ||qty>0 )
                                    {
                                    double taxamount = p.getTax();
                                    double taxamount2 = p.getTax2();
                                    double taxamount3 = p.getTax3();
                                     String taxaccount = dlfac.getAccountForTaxID(p.getTaxcatId());
                                     String taxaccount2 = dlfac.getAccountForTaxID(p.getTaxcatId2());
                                     String taxaccount3 = dlfac.getAccountForTaxID(p.getTaxcatId3());
                                    String taxComment = "";
                                    if (taxamount > 0) {
                                       
                                      //  if (taxaccount != null) {
//                                            Object[] value = new Object[]{UUID.randomUUID().toString(), tid, pdate, "D", transref, jLabel2.getText(), taxamount, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Purchase of " + p.getitem(), taxaccount, taxamount, d, d, true, oldID};
//                                           dlfac.insertintoaccjoutnal6(value);
                                       // }
                                        if(taxaccount == null) {
                                            taxComment = " Inclusive Of Tax";
                                            JOptionPane.showMessageDialog(null, "Account is not specified to " + p.getTaxcat() + "Category. tax value will be added to the product account");
                                            amount += taxamount;
                                        }}
                                   if (taxamount2 > 0) {   
                                        if(taxaccount2 == null) {
                                            taxComment = taxComment+" Inclusive Of Tax2";
                                            JOptionPane.showMessageDialog(null, "Account is not specified to " + p.getTaxcat2() + "Category. tax value will be added to the product account");
                                            amount += taxamount2;
                                        }}
                                    if (taxamount3 > 0) {  
                                        if(taxaccount3 == null) {
                                            taxComment = taxComment+" Inclusive Of Tax3";
                                            JOptionPane.showMessageDialog(null, "Account is not specified to " + p.getTaxcat3() + "Category. tax value will be added to the product account");
                                            amount += taxamount3;
                                        }}
                                    


                                    if (p.getAccount() != null) {
                                        // try{
                                        updatePurchaseJournalnStockDiary(pdate, p, qty, v, tid);
                                        amount = dlfac.roundTwoDecimals(amount);
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), tid, pdate, "D", transref, jLabel2.getText(), amount, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Purchase of " + p.getitem() + taxComment, p.getAccount(), amount, d, d, true, oldID};
                                        dlfac.insertintoaccjoutnal6(value);
                                    //  }catch(Exception e){
                                    //   e.printStackTrace();
                                    //   }
                                    }
                                    
                                     else if (p.getitem() != null) {
                                        throw new BasicException();
                                    }
                                    
                                    ////////////////////////////// TO CHANGE PRODUCT AMOUNT //////////////////////////// **** ## EDITED BY AAKASH
                                    System.out.println(p.getitem()+ "  : "+p.getRate());

                                    if(p.getRate()>0){

                                        new PreparedSentence(m_App.getSession(), "UPDATE PRODUCTS SET PRICEBUY =? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING  }), null).exec(new Object[]{ p.getRate(),p.getitemid()  });

                                    }
                                    
                                    
                                    
                                    
                                }}
                                //added by pratima to insert single entry in accountjournal for each type of tax in all items in  purchaselist 
                                
                                ArrayList<String> taxesList= new ArrayList<String>();
                                for (PurchaseVoucherline p : purchaselist) {
                                 Double amount = p.getamount();
                                    int qty = p.getQty();
                                    if(amount>0 ||qty>0 )
                                    {
                                    double taxamount = p.getTax();
                                    double taxamount2 = p.getTax2();
                                    double taxamount3 = p.getTax3();
                                     String taxaccount = dlfac.getAccountForTaxID(p.getTaxcatId());
                                     String taxaccount2 = dlfac.getAccountForTaxID(p.getTaxcatId2());
                                     String taxaccount3 = dlfac.getAccountForTaxID(p.getTaxcatId3());
                                    String taxComment = "";
                                    if (taxamount > 0) {
                                        if(!taxesList.contains(taxaccount))
                                        taxesList.add(taxaccount);
                                    }
                                    if (taxamount2 > 0) {
                                        if(!taxesList.contains(taxaccount2))
                                        taxesList.add(taxaccount2);
                                    }
                                    if (taxamount3 > 0) {
                                        if(!taxesList.contains(taxaccount3))
                                        taxesList.add(taxaccount3);
                                   }
                                    }
                                
                                }  
                                // Added By GuruGani
                                for(int j=0;j<taxesList.size();j++){
                                   Double totalTaxAmount=0.00;
                                   System.out.println("purchaselist size"+purchaselist.size());
                                for (PurchaseVoucherline p : purchaselist) {
                                    double taxamount = p.getTax();
                                    double taxamount2 = p.getTax2();
                                    double taxamount3 = p.getTax3();
                                     String taxaccount = dlfac.getAccountForTaxID(p.getTaxcatId());
                                     String taxaccount2 = dlfac.getAccountForTaxID(p.getTaxcatId2());
                                     String taxaccount3 = dlfac.getAccountForTaxID(p.getTaxcatId3());
                                     if(taxesList.get(j).toString().equals(taxaccount ))
                                     { totalTaxAmount=totalTaxAmount+taxamount;
                                     }if(taxesList.get(j).toString().equals(taxaccount2 ))
                                     {   totalTaxAmount=totalTaxAmount+taxamount2;
                                     } if(taxesList.get(j).toString().equals(taxaccount3 ))
                                     {totalTaxAmount=totalTaxAmount+taxamount3;
                                    }
                                }
                                Object[] value = new Object[]{UUID.randomUUID().toString(), tid, pdate, "D", transref, jLabel2.getText(), totalTaxAmount, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Purchase of ", taxesList.get(j).toString(), totalTaxAmount, d, d, true, oldID};
                                            
                                            dlfac.insertintoaccjoutnal6(value);
                                       } 
     // **************************************** LOOP END ******************************************************************
                                
                                
                                
                                
                                double amt1 = 0;
                                for (int j = 0; j < ptableModel.getadditionalEntrylist().size() - 1; j++) {
                                    // try{
                                    if (ptableModel.getadditionalEntrylist().get(j).getAccount() != null && ptableModel.getadditionalEntrylist().get(j).getAccount().length() > 0 && ptableModel.getadditionalEntrylist().get(j).getamount() > 0) {
                                        double amount1 = dlfac.roundTwoDecimals(ptableModel.getadditionalEntrylist().get(j).getamount());
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), tid, pdate, "D", transref, jLabel2.getText(), amount1, d, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Additional charges", ptableModel.getadditionalEntrylist().get(j).getAccount(), amount1, d, d, true, oldID};
                                        dlfac.insertintoaccjoutnal6(value);
                                        amt1 += ptableModel.getadditionalEntrylist().get(j).getamount();
                                    }
                                
                                }
                                
                                if (mode != 1) {
                                    reset();
                                }

                                
                                // ***************************************** FOR ANDROID CHANGES *************************************** 
                                    if(PurchaseIdSelected!=null && PurchaseIdSelected!=""){
                                        new PreparedSentence(m_App.getSession(), "UPDATE purchasejournal_temp SET LOADED=1 WHERE ID=? ", new SerializerWriteBasic(new Datas[]{ Datas.STRING})).exec(new Object[]{ PurchaseIdSelected });
                                    }
                                    
                                
                                // ******************************************** ANDROID CHANGES ENDS ****************************************************** 
                                    
                                return null;
                            }
                        };
                        t.execute();
                    } else {
                        JOptionPane.showMessageDialog(null, "No Changes was found", "Not Saved", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                 Logger.getLogger(PurchaseJournal.class.getName()).log(Level.SEVERE, null, e);             
                     e.printStackTrace();
                     new MessageInf(e).show(new JFrame());
                
               // JOptionPane.showMessageDialog(this, "Please Enter Correct Values", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Fill The Form", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
        }
        if (mode == 1) {
            dialog.setVisible(false);
        }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            Date datenow;
            try {
                datenow = (Date) Formats.TIMESTAMP.parseValue(date.getText());
            } catch (BasicException e) {
                datenow = null;
            }
            datenow = JCalendarDialog.showCalendar(this, datenow);
            if (datenow != null) {
                // LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
                boolean status = checkforValidity(datenow);
                if (status == false) {
                    datenow = new Date();
                    JOptionPane.showMessageDialog(this, "Please select a date within this financial year", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("");
                Timestamp t = new Timestamp(datenow.getTime());
                String[] dstring = t.toString().split(" ");
                Calendar cal = Calendar.getInstance();
                cal.setTime(datenow);
                cal.set(Calendar.HOUR_OF_DAY, 23); // Added By GuruGani
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 59);
                Calendar scal = Calendar.getInstance();
                scal.setTime(datenow);
                scal.set(Calendar.HOUR_OF_DAY, 00);
                scal.set(Calendar.MINUTE, 00);
                scal.set(Calendar.SECOND, 00);
                scal.set(Calendar.MILLISECOND, 00);
                jLabel2.setText(dlfac.getPurchasetransactionnum(scal.getTime(), cal.getTime())); // Added By GuruGani
                date.setText(Formats.TIMESTAMP.formatValue(datenow));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        if (jRadioButton1.isSelected()) {
            jLabel10.setVisible(true);
            cashaccount.setVisible(true);
            cashaccount.requestFocus(true);
        } else {
            jLabel10.setVisible(false);
            cashaccount.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    
    //added by teju
     /*
	    private List<TaxInfo> loadTaxCategory() throws BasicException{

	       //taxCatModel;
	    //List<String> taxCategories =new StaticSentence(s, "SELECT NAME FROM TAXCATEGORIES", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

	         List<TaxInfo> taxCategories = new StaticSentence(s, "SELECT T.ID, TC.NAME, T.CATEGORY, TC.ACCOUNT, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER FROM TAXES T,TAXCATEGORIES TC WHERE TC.ID=T.CATEGORY", SerializerWriteString.INSTANCE, new SerializerReadClass(TaxInfo.class)).list();


	     return taxCategories;
         }
    
      ItemListener itemListener=new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                
                 TaxInfo taxInfo = (TaxInfo) e.getItem();
             int row=jTable1.getSelectedRow();
           int col=jTable1.getSelectedColumn();

           ptableModel.getTableModel().setValueAt(taxInfo.getRate(), row, 7);
           ptableModel.getTableModel().setValueAt(taxInfo.getTaxCategoryID(), row, 10);

           Double qty = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 1).toString());
                                Double rate = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 2).toString());
                                Double Amount = dlfac.roundTwoDecimals(rate * qty);
                                Double taxvalue = Double.valueOf(ptableModel.getTableModel().getValueAt(row, 7).toString());
                                Double taxtotal = dlfac.roundTwoDecimals(taxvalue * qty * rate);
                                ptableModel.getTableModel().setValueAt(taxtotal, row, 5);
                                ptableModel.getTableModel().setValueAt(dlfac.roundTwoDecimals(taxtotal + Amount), row, 6);
                                ptableModel.getTableModel().setValueAt(taxInfo.getName(), row, 4);
                                jTable1.setModel(ptableModel.getTableModel());
                                
                           jTable1.requestFocus(true);      
                           jTable1.setRowSelectionInterval(row,row);   
                           jTable1.setColumnSelectionInterval(col,col);
            
            }
              
        };
    */
    
  
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
/*
        jTable3.clearSelection();
     
       
        
        List<TaxInfo> lst=null;
        
      if(jTable1.getSelectedColumn()==4){ 
       TableColumn  taxCatCol=jTable1.getColumnModel().getColumn(4);
       
         taxCatcombo=new JComboBox();
         
        try {
           lst=loadTaxCategory();
        } catch (BasicException ex) {
            Logger.getLogger(PurchaseJournal.class.getName()).log(Level.SEVERE, null, ex);
        }
        taxCatModel= new ComboBoxValModel(lst);
        taxCatcombo.setModel(taxCatModel);
        taxCatCol.setCellEditor(new DefaultCellEditor(taxCatcombo));
        taxCatcombo.addItemListener(itemListener); 
      
         
     
         
      }
       */
    }//GEN-LAST:event_jTable1MouseClicked
   
  // ends--teju
    
    
    
    //praveen:start-----changes to link po's
    private void vendorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_vendorItemStateChanged
        try {
            // TODO add your handling code here:
            String ven = vendor.getSelectedItem().toString();
            docref.setText(null);
            loadPurchaseOrder(ven);
            if(mode!=1)
            purchaseorder.setEnabled(true);
            
                if(warehouse.getSelectedIndex()!=-1)
                {
                     if(vendor.getSelectedIndex()!=-1) //&& warehouse.getSelectedIndex()!=-1 )
                     {
                        invoiceno.requestFocus(true);
                     }
                }
               else{
                   
                    JOptionPane.showMessageDialog(this, "please select warehouse");
                    warehouse.requestFocus(true);
                    vendor.setSelectedIndex(-1);
                }
            
                
                if (ptableModel != null) {
                    ptableModel.getlist().clear();
                    ptableModel.getlist().add(new PurchaseVoucherline());
                    jTable1.setModel(ptableModel.getTableModel());
                    grandtotal.setText("0.00");
                    amountTotal.setText("0.00");
                    amtTotal.setText("0.00");
                    qtytotal.setText("0");
                    TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                 }
                
        } catch (BasicException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_vendorItemStateChanged

    private void purchaseorderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_purchaseorderItemStateChanged
        // TODO add your handling code here:
        if (docref.getText().length() > 0) {
            docref.setText(docref.getText() + "#" + purchaseorder.getSelectedItem().toString());
        } else {
            docref.setText(purchaseorder.getSelectedItem().toString());
        }
        purchaseorder.setEnabled(false);
        loadCorrespondingProducts(Integer.parseInt(purchaseorder.getSelectedItem().toString()));

}//GEN-LAST:event_purchaseorderItemStateChanged

    private void jPanel3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3AncestorAdded

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        jTable1.clearSelection();
        
        
        
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void invoicenoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoicenoKeyReleased
        
        char key=evt.getKeyChar();
        if(vendor.getSelectedIndex()!=-1){
        
        if( key==KeyEvent.VK_ENTER )
        {
            delivarychallan.requestFocus(true);
            
        }
       
        }
        else
        {
            JOptionPane.showMessageDialog(this, "please select vendor");
            invoiceno.setText("");
            vendor.requestFocus(true);
        }
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_invoicenoKeyReleased

    private void delivarychallanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_delivarychallanKeyReleased
       
           char key=evt.getKeyChar();
                
        if(invoiceno.getText().equals("")){   
               
            JOptionPane.showMessageDialog(this, "please enter invoiceno");
              
             delivarychallan.setText("");
            invoiceno.requestFocus(true);
            
            
        }
        else{
            
            if(delivarychallan.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "please enter deliverychallan");
            }
           else
            {if(key==KeyEvent.VK_ENTER)
             {
                if(jRadioButton1.isSelected()){
                 cashaccount.requestFocus(true); 
                }
                else{
                  jTable1.requestFocus();
                  jTable1.editCellAt(0,0);
                  
                }
             }
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_delivarychallanKeyReleased

    private void cashaccountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cashaccountItemStateChanged

        jTable1.requestFocus();
              jTable1.editCellAt(0,0);
        // TODO add your handling code here:
    }//GEN-LAST:event_cashaccountItemStateChanged

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased

        char key=evt.getKeyChar();
         int row=jTable1.getSelectedRow();
           int col=jTable1.getSelectedColumn();
           
        if(key==KeyEvent.VK_ENTER){  
             if(col==6)
           {
               jTable1.setRowSelectionInterval(row,row);
               jTable1.setColumnSelectionInterval(0,0);
            //jTable1.editCellAt(row+1,0);
           }else if(col<2){      
              jTable1.setRowSelectionInterval(row-1,row-1);
            
               jTable1.setColumnSelectionInterval(col+1,col+1);
             }
           else{
                jTable1.setRowSelectionInterval(row-1,row-1);
            
               jTable1.setColumnSelectionInterval(6,6);
           }
         
           //jTable1.setColumnSelectionInterval(0,0);
          
           
           // System.out.println(jTable1.getSelectedRow() +""+jTable1.getSelectedColumn());
           
            
          
       
       }
        /* 
         char key=evt.getKeyChar();
        if(key==KeyEvent.VK_ENTER){
          int c = jTable1.getSelectedColumn();
          int r = jTable1.getSelectedRow();
          if(c==-1){
              c=0;
          }
          if(r==-1){
              r=0;
          }
          
          if(c==0){
             jTable1.editCellAt(r, 1); 
          }
          else if(c==1){
              jTable1.editCellAt(r, 2);
          }
          else if(c==2){
              jTable1.editCellAt(r, 6);
          }
          else{
              if(c==6){
                  r++;
                  jTable1.editCellAt(r, 0);
                  jTable1.setRowSelectionInterval(r,0);
              }
          } 
        }
           */ 

    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
             
       
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable3FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable3FocusGained

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
       /* if(jButton2.isSelected())
        {
            jTable1.requestFocus(true);
            jTable1.setRowSelectionInterval(0, 0);
            jTable1.addColumnSelectionInterval(0,0);
        }*/
        
        jTable1.requestFocus(true);
            jTable1.setRowSelectionInterval(0, 0);
            jTable1.addColumnSelectionInterval(0,0);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
       
         if(jButton2.isSelected())
        {
            jTable1.requestFocus(true);
            jTable1.setRowSelectionInterval(0, 0);
            jTable1.addColumnSelectionInterval(0,0);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void jTable3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyReleased

       char key=evt.getKeyChar();
         int row=jTable3.getSelectedRow();
           int col=jTable3.getSelectedColumn();
           
        if(key==KeyEvent.VK_ENTER){  
                if(col==1)
               {
                    jTable3.setRowSelectionInterval(row,row);
               jTable3.setColumnSelectionInterval(0,0);
               }else{   
            
               jTable3.setRowSelectionInterval(row-1,row-1);
               jTable3.setColumnSelectionInterval(col+1,col+1);
                }
           }
            jTable1.editCellAt(row+1,0);
    }
        // TODO add your handling code here:          char key = evt.getKeyChar();         int row = jTable1.getSelectedRow();         int col = jTable1.getSelectedColumn();         if (key == KeyEvent.VK_ENTER) {             jTable3.setRowSelectionInterval(row - 1, row - 1);             jTable3.setColumnSelectionInterval(col + 1, col + 1);         }     }//GEN-LAST:event_jTable3KeyReleased

    private void Barcode_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Barcode_textKeyPressed
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
          if(warehouse.getSelectedIndex()!=-1){
            if(vendor.getSelectedIndex()!=-1){
              
                String Barcode = Barcode_text.getText().trim();
                String WarehouseName=warehouse.getSelectedItem().toString();
                String WarehouseID = getWarehouseIdByName(WarehouseName);
                String ProductID = getProductIdByBarcode(Barcode, WarehouseID);
                if(ProductID!=null && ProductID.trim().length()>0){
            
                    // ***********************************************************************************************************
                    
                    
                    try {
                            // incProduct(1.0, (ProductInfoExt) e.getSource());
                            int x = jTable1.getRowCount();//edit
                            jTable1.setRowSelectionInterval(x-1,x-1);//edit

                            int row = jTable1.getSelectedRow();
                            int column = jTable1.getSelectedColumn();
                            Object[] obj = null;    
                            ProductInfoExt pinfo = m_dlSales.getProductInfo(ProductID);
                            
                            
                            if (pinfo.getAccount() != null) {
                                String ven = vendor.getSelectedItem().toString();
                                if (mode != 1)
                                        ordererdline = ptableModel.getPurchaseOrderedLine(m_App, pinfo.getID(), ven, docref.getText());
                                //ordererdline = ptableModel.getPdline1();
                                if (ordererdline.isEmpty()) {//praveen:here array index ut of bounds
                                  //  if (JOptionPane.showConfirmDialog(null, "No pending purchase order.\r\n Do you want to still add the item", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                                        ptableModel.getTableModel().setValueAt(pinfo.getName(), row, 0);
                                        
                                        ptableModel.getTableModel().setValueAt(pinfo.getTaxCategoryName(), row, 4);
                                        ptableModel.getTableModel().setValueAt(pinfo.getPriceBuy(), row, 2);
                                        ptableModel.getTableModel().setValueAt(pinfo.getAccount(), row, 8);
                                        TaxInfo tax = taxeslogic.getTaxInfo(pinfo.getTaxCategoryInfo());
                                        //   pinfo.getPriceSellTax(tax);
                                        ptableModel.getTableModel().setValueAt(tax.getRate(), row, 7);
                                        ptableModel.getTableModel().setValueAt(pinfo.getID(), row, 9);//tax value for i qty
                                        ptableModel.getTableModel().setValueAt(tax.getTaxCategoryID(), row, 10);
                                        ptableModel.getTableModel().setValueAt(false, row, 12);

                                        List<PurchaseVoucherline> plist = ptableModel.getlist();
                                        if ((jTable1.getRowCount()-1) == row) {
                                            PurchaseVoucherline pelement = new PurchaseVoucherline();
                                            plist.add(pelement);


                                        }
                                        jTable1.setModel(ptableModel.getTableModel());
                                        TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                                       // jTable1.setRowSelectionAllowed(true);



                                        jTable1.setRowSelectionInterval(row, row);



                                    //}
                                    jTable1.setColumnSelectionInterval(1,1);
                                     jTable1.setRowSelectionInterval(row, row);
                                      //System.out.println(jTable1.getSelectedRow()+" "+jTable1.getSelectedColumn());
                                     Barcode_text.setText(null);
                                     
                                } else {
                                    showAllPO(ordererdline);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Please Specify a Purchase Account to the product", null, JOptionPane.OK_OPTION);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    
                    
                    
                    
                    
                    
            
            
                    // ************************************************************************************************************
                }
                else{
                    JOptionPane.showMessageDialog(this, "No product found . Please select from same warhouse");
                    Barcode_text.setText(null);
                }
            }
            else{
                 JOptionPane.showMessageDialog(this, "please select Vendor first");
                 Barcode_text.setText(null);
            }
          }
          else{
              JOptionPane.showMessageDialog(this, "please select Warehouse first");
              Barcode_text.setText(null);
          }
        }
    }//GEN-LAST:event_Barcode_textKeyPressed

    PurchaseLoadFrameTableModel.PurchaseDetails showPurchaseDetails = null;
    public List<PurchaseLoadFrameTableModel.ItemDetails> ProdFullList;
    
    
    private void load_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_buttonActionPerformed
      
     if (ptableModel != null) {
                    ptableModel.getlist().clear();
                    ptableModel.getlist().add(new PurchaseVoucherline());
                    jTable1.setModel(ptableModel.getTableModel());
                    grandtotal.setText("0.00");
                    amountTotal.setText("0.00");
                    amtTotal.setText("0.00");
                    qtytotal.setText("0");
                    TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                 }   
        
        
      if(warehouse.getSelectedIndex()!=-1){ 
            List<purchase> PurchaseOrderList = new ArrayList<purchase>();
            String WarehouseSelected = warehouse.getSelectedItem().toString();
            PurchaseLoadFrame purchaseListPendingbyApp;
            try {
                ProdFullList = new ArrayList<PurchaseLoadFrameTableModel.ItemDetails>();
                purchaseListPendingbyApp = PurchaseLoadFrame.getDialog(this, m_App,true,WarehouseSelected);
                purchaseListPendingbyApp.showDialog();
                PurchaseIdSelected=purchaseListPendingbyApp.getPurchaseIdSelected();
                System.out.println("Purchase ID selected : "+PurchaseIdSelected);
                showPurchaseDetails= purchaseListPendingbyApp.getPurchaseDetails();
                
                String VendorSelected = showPurchaseDetails.getVendorName();
                for(int i=0;i<dlfac.getVendorList().size();i++){
                    Vendor VClassBean = dlfac.getVendorList().get(i);
                    String VName = VClassBean.toString();
                    if(VendorSelected.equals(VName)){
                        vendor.setSelectedIndex(i);
                        break;
                    }
                }
                
                invoiceno.setText(showPurchaseDetails.getINVOICENO());
                delivarychallan.setText(showPurchaseDetails.getDELIVERYCHALLAN());
                ProdFullList = purchaseListPendingbyApp.getPurchaseDetailsItemList();
                System.out.println("Product list size is : "+ProdFullList.size());
                
                
                Double GrandTotal = 0.00;
                int totalQty = 0;
                
                // *********************************************************************** LOAD PRODUCT DETAILS TO TABLE **********************************
                for(int i=0;i<ProdFullList.size();i++){
                    Double amt = 0.00;
                    Double TaxAmt = 0.00;
                    Double TotalAmt=0.00;
                    int row=i;
                    String prodid = ProdFullList.get(i).getPRODUCTID().toString();
                    
                    ProductInfoExt prodIndoext = m_dlSales.getProductInfo(prodid);
                    PurchaseOrderList = checkPOforProduct(VendorSelected, prodid);
                    int Tempqty = ProdFullList.get(i).getQTY().intValue();
                    if (prodIndoext.getAccount() != null) {
                            
                        if(PurchaseOrderList.size()>0){
                            int PurchaseTempQty=Tempqty;
                             for(int j=0;j<PurchaseOrderList.size();j++){   
                                int OrderedQty = PurchaseOrderList.get(j).getQty();
                                String Porderno = PurchaseOrderList.get(j).toString();
                                 if (JOptionPane.showConfirmDialog(null, "Purchase Order no : "+Porderno+"     Ordered  Qty : "+OrderedQty + ".   Do you want to add ?   ", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                
                                        if(PurchaseTempQty>0){
                                                if(PurchaseTempQty>OrderedQty || PurchaseTempQty==OrderedQty){
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getName(), row, 0);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getTaxCategoryName(), row, 4);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getPriceBuy(), row, 2);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getAccount(), row, 8);
                                                        TaxInfo tax = taxeslogic.getTaxInfo(prodIndoext.getTaxCategoryInfo());
                                                        //   pinfo.getPriceSellTax(tax);
                                                        ptableModel.getTableModel().setValueAt(tax.getRate(), row, 7);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getID(), row, 9);//tax value for i qty
                                                        ptableModel.getTableModel().setValueAt(tax.getTaxCategoryID(), row, 10);
                                                        ptableModel.getTableModel().setValueAt(false, row, 12);
                                                        int qty = ProdFullList.get(i).getQTY().intValue();
                                                        totalQty=totalQty+OrderedQty;
                                                        ptableModel.getTableModel().setValueAt(OrderedQty , row, 1);
                                                        amt=dlfac.roundTwoDecimals(OrderedQty*prodIndoext.getPriceBuy());
                                                        ptableModel.getTableModel().setValueAt(amt , row, 3);     // AMOUNT
                                                        TaxAmt = dlfac.roundTwoDecimals(tax.getRate()*amt);
                                                        ptableModel.getTableModel().setValueAt(TaxAmt , row, 5);    // TAX AMOUNT
                                                        TotalAmt=dlfac.roundTwoDecimals(amt+TaxAmt);
                                                        ptableModel.getTableModel().setValueAt(TotalAmt , row, 6);    //TOTAL AMOUNT
                                                        GrandTotal = GrandTotal+TotalAmt;
                                                        List<PurchaseVoucherline> plist = ptableModel.getlist();
                                                        if ((jTable1.getRowCount()-1) == row) {
                                                            PurchaseVoucherline pelement = new PurchaseVoucherline();
                                                            plist.add(pelement);


                                                        }
                                                        PurchaseTempQty = PurchaseTempQty-OrderedQty;
                                                        
                                                }else{
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getName(), row, 0);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getTaxCategoryName(), row, 4);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getPriceBuy(), row, 2);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getAccount(), row, 8);
                                                        TaxInfo tax = taxeslogic.getTaxInfo(prodIndoext.getTaxCategoryInfo());
                                                        //   pinfo.getPriceSellTax(tax);
                                                        ptableModel.getTableModel().setValueAt(tax.getRate(), row, 7);
                                                        ptableModel.getTableModel().setValueAt(prodIndoext.getID(), row, 9);//tax value for i qty
                                                        ptableModel.getTableModel().setValueAt(tax.getTaxCategoryID(), row, 10);
                                                        ptableModel.getTableModel().setValueAt(false, row, 12);
                                                        int qty = ProdFullList.get(i).getQTY().intValue();
                                                        totalQty=totalQty+PurchaseTempQty;
                                                        ptableModel.getTableModel().setValueAt(PurchaseTempQty , row, 1);
                                                        amt=dlfac.roundTwoDecimals(PurchaseTempQty*prodIndoext.getPriceBuy());
                                                        ptableModel.getTableModel().setValueAt(amt , row, 3);     // AMOUNT
                                                        TaxAmt = dlfac.roundTwoDecimals(tax.getRate()*amt);
                                                        ptableModel.getTableModel().setValueAt(TaxAmt , row, 5);    // TAX AMOUNT
                                                        TotalAmt=dlfac.roundTwoDecimals(amt+TaxAmt);
                                                        ptableModel.getTableModel().setValueAt(TotalAmt , row, 6);    //TOTAL AMOUNT
                                                        GrandTotal = GrandTotal+TotalAmt;
                                                        List<PurchaseVoucherline> plist = ptableModel.getlist();
                                                        if ((jTable1.getRowCount()-1) == row) {
                                                            PurchaseVoucherline pelement = new PurchaseVoucherline();
                                                            plist.add(pelement);


                                                        }
                                                        break;
                                                }
                                        }
                                        else{
                                            break;
                                        }
                                        
                                        
                                 }
                                 else{
                                     
                                     
                                        ptableModel.getTableModel().setValueAt(prodIndoext.getName(), row, 0);
                                        ptableModel.getTableModel().setValueAt(prodIndoext.getTaxCategoryName(), row, 4);
                                        ptableModel.getTableModel().setValueAt(prodIndoext.getPriceBuy(), row, 2);
                                        ptableModel.getTableModel().setValueAt(prodIndoext.getAccount(), row, 8);
                                        TaxInfo tax = taxeslogic.getTaxInfo(prodIndoext.getTaxCategoryInfo());
                                        //   pinfo.getPriceSellTax(tax);
                                        ptableModel.getTableModel().setValueAt(tax.getRate(), row, 7);
                                        ptableModel.getTableModel().setValueAt(prodIndoext.getID(), row, 9);//tax value for i qty
                                        ptableModel.getTableModel().setValueAt(tax.getTaxCategoryID(), row, 10);
                                        ptableModel.getTableModel().setValueAt(false, row, 12);
                                        int qty = ProdFullList.get(i).getQTY().intValue();
                                        totalQty=totalQty+qty;
                                        ptableModel.getTableModel().setValueAt(qty , row, 1);
                                        amt=dlfac.roundTwoDecimals(qty*prodIndoext.getPriceBuy());
                                        ptableModel.getTableModel().setValueAt(amt , row, 3);     // AMOUNT
                                        TaxAmt = dlfac.roundTwoDecimals(tax.getRate()*amt);
                                        ptableModel.getTableModel().setValueAt(TaxAmt , row, 5);    // TAX AMOUNT
                                        TotalAmt=dlfac.roundTwoDecimals(amt+TaxAmt);
                                        ptableModel.getTableModel().setValueAt(TotalAmt , row, 6);    //TOTAL AMOUNT
                                        GrandTotal = GrandTotal+TotalAmt;
                                        List<PurchaseVoucherline> plist = ptableModel.getlist();
                                        if ((jTable1.getRowCount()-1) == row) {
                                            PurchaseVoucherline pelement = new PurchaseVoucherline();
                                            plist.add(pelement);


                                        }
                                 }
                                 
                                 
                                 
                             }
                                
                                
                                
                        }
                        else{
                            
                            ptableModel.getTableModel().setValueAt(prodIndoext.getName(), row, 0);
                            ptableModel.getTableModel().setValueAt(prodIndoext.getTaxCategoryName(), row, 4);
                            ptableModel.getTableModel().setValueAt(prodIndoext.getPriceBuy(), row, 2);
                            ptableModel.getTableModel().setValueAt(prodIndoext.getAccount(), row, 8);
                            TaxInfo tax = taxeslogic.getTaxInfo(prodIndoext.getTaxCategoryInfo());
                            //   pinfo.getPriceSellTax(tax);
                            ptableModel.getTableModel().setValueAt(tax.getRate(), row, 7);
                            ptableModel.getTableModel().setValueAt(prodIndoext.getID(), row, 9);//tax value for i qty
                            ptableModel.getTableModel().setValueAt(tax.getTaxCategoryID(), row, 10);
                            ptableModel.getTableModel().setValueAt(false, row, 12);
                            int qty = ProdFullList.get(i).getQTY().intValue();
                            totalQty=totalQty+qty;
                            ptableModel.getTableModel().setValueAt(qty , row, 1);
                            amt=dlfac.roundTwoDecimals(qty*prodIndoext.getPriceBuy());
                            ptableModel.getTableModel().setValueAt(amt , row, 3);     // AMOUNT
                            TaxAmt = dlfac.roundTwoDecimals(tax.getRate()*amt);
                            ptableModel.getTableModel().setValueAt(TaxAmt , row, 5);    // TAX AMOUNT
                            TotalAmt=dlfac.roundTwoDecimals(amt+TaxAmt);
                            ptableModel.getTableModel().setValueAt(TotalAmt , row, 6);    //TOTAL AMOUNT
                            GrandTotal = GrandTotal+TotalAmt;
                            
                            
                            
                            List<PurchaseVoucherline> plist = ptableModel.getlist();
                            if ((jTable1.getRowCount()-1) == row) {
                                PurchaseVoucherline pelement = new PurchaseVoucherline();
                                plist.add(pelement);
                                
                               
                            }
                            
                            
                        }
                        
                        
                        
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Please Specify a Purchase Account to the product", null, JOptionPane.OK_OPTION);
                    }
                }
               // ********************************************************************** FOR LOOP ENDS ******************************************* 
                
                jTable1.setModel(ptableModel.getTableModel());
                TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
                amtTotal.setText(dlfac.roundTwoDecimals(GrandTotal)+"");
                qtytotal.setText(totalQty+"");
                grandtotal.setText(dlfac.roundTwoDecimals(GrandTotal)+"");
                
                
            } catch (BasicException ex) {
                Logger.getLogger(PurchaseJournal.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      else{
          JOptionPane.showMessageDialog(this, "please select Warehouse first");
      }
    }//GEN-LAST:event_load_buttonActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
        Barcode_text.setVisible(true);
        }else {
         Barcode_text.setVisible(false);
         Barcode_text.setText(null);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void cashaccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashaccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashaccountActionPerformed

    private void vendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendorActionPerformed
    
    private List<purchase>  checkPOforProduct(String Vendor,String ProductId) throws BasicException{
        List<purchase> po3 = new StaticSentence(m_App.getSession(), " SELECT PO.ID,PO.PURCHASEORDERNO,PD.PRODUCTID,PD.ORDEREDQTY,PD.RATE \n" +
                                                                        "FROM PURCHASEORDER PO,PURCHASEORDERDETAIL PD, VENDOR V \n" +
                                                                        "WHERE PD.PURCHASEORDERID=PO.ID AND PO.VENDOR=V.ID AND V.NAME= ? \n" +
                                                                        "AND PD.BALANCEQTY>0 AND PD.PRODUCTID= '"+ProductId+"'  \n" +
                                                                        "ORDER BY PO.CRDATE", SerializerWriteString.INSTANCE, new SerializerReadClass(purchase.class)).list(Vendor);
    
        return po3;
    }
    
   
    
    
    private void loadPurchaseOrder(String vendorName) throws BasicException {
        List<purchase> po = new StaticSentence(m_App.getSession(), " SELECT PO.ID,PO.PURCHASEORDERNO,NULL,NULL,NULL FROM PURCHASEORDER PO,PURCHASEORDERDETAIL PD,VENDOR V WHERE PD.PURCHASEORDERID=PO.ID AND PO.VENDOR=V.ID AND V.NAME=? AND PD.BALANCEQTY>0 GROUP BY PO.ID ORDER BY PO.PURCHASEORDERNO", SerializerWriteString.INSTANCE, new SerializerReadClass(purchase.class)).list(vendorName);
        pomodel = new ComboBoxValModel(po);
        purchaseorder.setModel(pomodel);

    }

    private void loadCorrespondingProducts(int purcchaseorderno) {
        try {
            List<PurchaseJournalTable.PurchaseOrderedLine> oline = ptableModel.loadPurchaseOrderedLine(m_App, purcchaseorderno);
            //List<PurchaseJournalTable.PurchaseOrderedLine> oline = ptableModel.getPdline();
            int row = jTable1.getSelectedRow();
            List<PurchaseVoucherline> pv = new ArrayList<PurchaseVoucherline>();
            PurchaseVoucherline pvl;
            for (PurchaseOrderedLine p : oline) {
                ProductInfoExt pin = m_dlSales.getProductInfo(p.getProduct());
                pvl = new PurchaseVoucherline();
                if (pin.getAccount() != null) {
                    pvl.setitem(pin.getName());
                    pvl.setQty(p.getQty());
                    pvl.setRate(p.getRate());
                    pvl.setPurchaseorderref(p.getPoid());
                    pvl.setTaxcat(pin.getTaxCategoryName());
                    pvl.setAccount(pin.getAccount());
                    TaxInfo tax = taxeslogic.getTaxInfo(pin.getTaxCategoryInfo());
                    pvl.setpdttaxvalue(tax.getRate());
                    pvl.setitemid(pin.getID());
                    pvl.setTaxcatId(tax.getTaxCategoryID());
                    pvl.setamount(p.getPqty() * p.getRate());
                    pvl.setTax(tax.getRate()*pvl.getamount());
                    pvl.setTotalAmount(pvl.getamount() + pvl.getTax());
                    pvl.setOqty(p.getQty());
                    pv.add(pvl);
                    row++;
                } else {
                    JOptionPane.showMessageDialog(null, "Please Specify a Purchase Account to the product " + pin.getName(), null, JOptionPane.OK_OPTION);
                }
            }
            pvl = new PurchaseVoucherline();
            pv.add(pvl);
            //ptableModel.setlist(pv);
            ptableModel.getlist().remove(ptableModel.getlist().size()-1);
            ptableModel.getlist().addAll(pv);
            jTable1.setModel(ptableModel.getTableModel());
            TableColumnModel jColumns = jTable1.getColumnModel();
                            displayColumns(jColumns);
            double d = 0.0;
            int q = 0;
            for (PurchaseVoucherline p : ptableModel.getlist()) {
                d = d + p.getTotalAmount();
                q = q + p.getQty();
            }
            qtytotal.setText(String.valueOf(q));
            amtTotal.setText(String.valueOf(dlfac.roundTwoDecimals(d)));
            if (grandtotal.isVisible() == true) {
                grandtotal.setText(String.valueOf(dlfac.roundTwoDecimals(Double.parseDouble(amtTotal.getText()) + Double.parseDouble(amountTotal.getText()))));
            }
        //jTable1.setRowSelectionInterval(row, row);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //praveen:end-----
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Barcode_text;
    private javax.swing.JTextField amountTotal;
    private javax.swing.JTextField amtTotal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cashaccount;
    private javax.swing.JTextField date;
    private javax.swing.JTextField delivarychallan;
    private javax.swing.JTextField docref;
    private javax.swing.JTextField grandtotal;
    private javax.swing.JTextField invoiceno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton load_button;
    private javax.swing.JComboBox purchaseorder;
    private javax.swing.JTextField qtytotal;
    private javax.swing.JComboBox vendor;
    private javax.swing.JComboBox warehouse;
    // End of variables declaration//GEN-END:variables

    public String getWarehouseIdByName(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }

    public String getProductIdByBarcode(String Barcode ,String Warehouse ){
        String accName = null;
        try{
            if(Barcode!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS  WHERE CODE=? AND LOCATION='"+Warehouse+"'", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(Barcode);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }
    public void displayColumns(TableColumnModel jColumns){
   
        jColumns.getColumn(0).setPreferredWidth(200);
       // jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(30);
       // jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(60);
       // jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(60);
       // jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(60);
       // jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(60);
       // jColumns.getColumn(5).setResizable(false);
           jColumns.getColumn(6).setMinWidth(0);
           jColumns.getColumn(6).setMaxWidth(0);
           jColumns.getColumn(6).setResizable(false);
           jColumns.getColumn(7).setMinWidth(0);
           jColumns.getColumn(7).setMaxWidth(0);
           jColumns.getColumn(7).setResizable(false);
           jColumns.getColumn(8).setMinWidth(0);
           jColumns.getColumn(8).setMaxWidth(0);
           jColumns.getColumn(8).setResizable(false);
           jColumns.getColumn(9).setMinWidth(0);
           jColumns.getColumn(9).setMaxWidth(0);
           jColumns.getColumn(9).setResizable(false);
           jColumns.getColumn(10).setMinWidth(0);
           jColumns.getColumn(10).setMaxWidth(0);
           jColumns.getColumn(10).setResizable(false);
           jColumns.getColumn(11).setMinWidth(0);
           jColumns.getColumn(11).setMaxWidth(0);
           jColumns.getColumn(11).setResizable(false);
           jColumns.getColumn(12).setMinWidth(0);
           jColumns.getColumn(12).setMaxWidth(0);
           jColumns.getColumn(12).setResizable(false);
        jColumns.getColumn(7).setPreferredWidth(60);
      //  jColumns.getColumn(7).setResizable(false);
        jColumns.getColumn(8).setPreferredWidth(60);
      //  jColumns.getColumn(8).setResizable(false);
           jColumns.getColumn(15).setMinWidth(0);
           jColumns.getColumn(15).setMaxWidth(0);
           jColumns.getColumn(15).setResizable(false);
           jColumns.getColumn(16).setMinWidth(0);
           jColumns.getColumn(16).setMaxWidth(0);
           jColumns.getColumn(16).setResizable(false);
        jColumns.getColumn(9).setPreferredWidth(60);
      //  jColumns.getColumn(9).setResizable(false);
        jColumns.getColumn(10).setPreferredWidth(60);
      //  jColumns.getColumn(10).setResizable(false);
           jColumns.getColumn(19).setMinWidth(0);
           jColumns.getColumn(19).setMaxWidth(0);
           jColumns.getColumn(19).setResizable(false);
           jColumns.getColumn(20).setMinWidth(0);
           jColumns.getColumn(20).setMaxWidth(0);
           jColumns.getColumn(20).setResizable(false);
           jColumns.getColumn(21).setMinWidth(0);
           jColumns.getColumn(21).setMaxWidth(0);
           jColumns.getColumn(21).setResizable(false);
           jColumns.getColumn(22).setMinWidth(0);
           jColumns.getColumn(22).setMaxWidth(0);
           jColumns.getColumn(22).setResizable(false);
        jColumns.getColumn(23).setPreferredWidth(60);
        jColumns.getColumn(23).setResizable(false); 

    }

}
