/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QTList.java
 *
 * Created on Dec 9, 2008, 4:46:55 PM
 */
package com.openbravo.pos.sales.restaurant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPrincipalApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillLogicApply;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.sales.QTItemTableModel;
import com.openbravo.pos.sales.QTListTableModel;
import com.openbravo.pos.sales.QTicketLineInfo;
import com.openbravo.pos.sales.Qticket;
import com.openbravo.pos.sales.QticketInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class QTList extends javax.swing.JDialog {

    private static String PERMISSION_DISCOUNT = "sales.Discount";
    private AppView app;
    private DataLogicSales dlSales;
    private BillLogic dlBill;
    private QticketInfo qt;
    private Qticket qticket;
    private CustomerInfo cust;
    private TicketParser m_TTP;
    private List<QticketInfo> pendingQTList;
    private Map<String, PlacesInfo> placeMap = new HashMap<String, PlacesInfo>();
    private Map<String, WaiterInfo> waiterMap = new HashMap<String, WaiterInfo>();
    private BillLogicApply bla;
    private String customer1;
    private String waiter;
    private String table;
    private String floor;

    public QTList(Frame parent, AppView app, DataLogicSales dlSales, Qticket qticket) {
        super(parent, true);
        this.app = app;
        this.qticket = qticket;
        this.dlSales = dlSales;
        this.dlBill = LookupUtilityImpl.getInstance(app).getDataLogicBill();

    }

    public QTList(Dialog parent, AppView app, DataLogicSales dlSales, Qticket qticket) {
        super(parent, true);
        this.app = app;
        this.qticket = qticket;
        this.dlSales = dlSales;
        this.dlBill = LookupUtilityImpl.getInstance(app).getDataLogicBill();
    }

    public boolean reloadPendingQTList(boolean showAll) throws BasicException {
        Date d = new Date();
        pendingQTList = new ArrayList<QticketInfo>();
        List<QticketInfo> qtList = null;
        showAll = false;
        String role = app.getAppUserView().getUser().getRole();
        if (showAll) {
            qtList = qticket.getQTicketList(cust.getId());
        } else {
            qtList = qticket.getQTicketListForUser(cust.getId(), app.getAppUserView().getUser());
        }
        for (QticketInfo qticketInfo : qtList) {
            if (!qticketInfo.isBilled()) {
                pendingQTList.add(qticketInfo);
            }
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            cal.setTimeInMillis(d.getTime());
            cal1.setTimeInMillis(qticketInfo.getCreatedDate().getTime());
            if (cal1.after(cal)) {
                JOptionPane.showMessageDialog(this, "Present time is less than one of the QT time.System time might been reset", "Error - Cannot Create bill", JOptionPane.OK_OPTION);
                return false;
            }

        }

        refreshModel();
        refreshItemModel();
        return true;
    }

    /** Creates new form QTList */
    String CustIDDist = null;
    
    public boolean setCustomer(CustomerInfo customer) throws BasicException {
        cust = customer;
        boolean flag = true;
        flag = reloadPendingQTList(false);
        if (flag == true) {
            List<PlacesInfo> placesList = dlSales.getPlacesList().list();
            placeMap.clear();
            for (PlacesInfo pInfo : placesList) {
                placeMap.put(pInfo.getID(), pInfo);
            }

            List<WaiterInfo> waitersList = dlSales.getWaiterList(app.getAppUserView().getUser().getRole());
            waiterMap.clear();
            for (WaiterInfo wInfo : waitersList) {
                waiterMap.put(wInfo.getID(), wInfo);
            }

            initComponents();

            jBtnDiscount.setVisible(app.getAppUserView().getUser().hasPermission(PERMISSION_DISCOUNT));

            jTable1.setModel(new QTListTableModel(this, dlSales));
            jTable2.setModel(new QTItemTableModel(this, dlSales));

            jTextCustomer.setText(customer.getName());
            jTextPlace.setText(null);
            jTextWaiter.setText(null);
            customer1 = customer.getName();
            CustIDDist = customer.getId();
            if (!pendingQTList.isEmpty()) {
                QticketInfo qt = pendingQTList.get(0);
                jTextPlace.setText(placeMap.get(qt.getPlace()).getName());
                jTextWaiter.setText(waiterMap.get(qt.getWaiter()).getName());
            }
        }
        return flag;
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    public static QTList getDialog(Component parent, AppView app, DataLogicSales dlSales, Qticket qticket) {

        Window window = getWindow(parent);

        QTList myqtList;

        if (window instanceof Frame) {
            myqtList = new QTList((Frame) window, app, dlSales, qticket);
        } else {
            myqtList = new QTList((Dialog) window, app, dlSales, qticket);
        }

        return myqtList;
    }

    public void refreshModel() throws BasicException {
        if (jTable1 == null) {
            return;
        }
        ((QTListTableModel) jTable1.getModel()).refresh(pendingQTList);

        for (QticketInfo qtInfo : pendingQTList) {
            if (qtInfo.getLines() == null) {
                qtInfo.setLines(qticket.getQTicketLineList(qtInfo.getId()));
            }
        }
    }

    private void refreshItemModel() {
        if (jTable1 == null) {
            return;
        }
        int i = jTable1.getSelectedRow();
        if (i >= 0 && i < pendingQTList.size()) {
            QticketInfo qtInfo = pendingQTList.get(i);
            ((QTItemTableModel) jTable2.getModel()).refresh(qtInfo);
        } else {
            ((QTItemTableModel) jTable2.getModel()).refresh(null);
        }
    }

    public boolean showDialog(CustomerInfo customer) throws BasicException {
        boolean flag = setCustomer(customer);
        if (flag == true) {
            refreshModel();
            setVisible(true);
        }
        return flag;
    }

    private boolean validateDiscount(QticketInfo qt, QTicketLineInfo discountQTL) {
        int quantity = discountQTL.getMultiply();
        for (QTicketLineInfo qtlInfo : qt.getLines()) {
            
            if (qtlInfo.getProduct().equals(discountQTL.getProduct())) {
                quantity += qtlInfo.getMultiply();
            }
        }

        if (quantity < 0) {
            new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.toomuchdiscount")).show(this);
            return false;
        }

        return true;
    }

    public void requestDiscount() throws BasicException {
        int i = jTable1.getSelectedRow();
        int j = jTable2.getSelectedRow();
        if (i >= 0 && i < pendingQTList.size()) {
            QticketInfo qt = pendingQTList.get(i);
            // try{
            List<QTicketLineInfo> qtlines = qticket.getQTicketLineList(qt.getId());
            if (j >= 0 && j < qtlines.size()) {
                QTicketLineInfo qtl = new QTicketLineInfo();
                QTicketLineInfo temp = qtlines.get(j);

                if (temp.getMultiply() > 0) {
                    discountDetail ddetail = discountDetail.getDialog(this, dlSales, temp, app);

                    boolean resultok = ddetail.showDialog(customer1, qt.getId() , CustIDDist);
                }
            /* if (resultok) {
            try {
            qt.addLine(qtl);
            qticket.insertQTicketLine(qt, qtl);
            //  dlSales.updateStockVolume1(Volume, table);
            } catch(BasicException e) {
            new MessageInf(e);
            }
            refreshItemModel();
            }*/
            }

        }
    }


    /*   public void createDiscount() throws BasicException {
    int i = jTable1.getSelectedRow();
    int j = jTable2.getSelectedRow();

    if (i >= 0 && i < pendingQTList.size()) {
    //condition for checking if some thing is selected in qt
    QticketInfo qt = pendingQTList.get(i);
    List<QTicketLineInfo> qtlines = qticket.getQTicketLineList(qt.getId());
    if (j >= 0 && j < qtlines.size()) {
    //condition for checking if some thing is selected in qtline
    QTicketLineInfo qtl = new QTicketLineInfo();
    QTicketLineInfo temp = qtlines.get(j);
    qtl.setProduct(temp.getProduct());
    //TODO display a dialog to decide how much to subtract;
    discountPage discount = discountPage.getDialog(this, dlSales, qtl, temp.getMultiply());
    boolean resultok = false;
    try {
    resultok = discount.showDialog();
    } catch(BasicException e) {
    new MessageInf(e).show(this);
    }

    if (resultok && validateDiscount(qt, qtl)) {
    try {
    qt.addLine(qtl);
    qticket.insertQTicketLine(qt, qtl);
    } catch(BasicException e) {
    new MessageInf(e);
    }
    refreshItemModel();
    }
    }
    }
    }*/
    public void displayBill() {
        int flag = 0, tempint = 0;
        if (pendingQTList.isEmpty()) {
            new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nopendingqt")).show(this);
            return;
        }
        try {
            
            
///////////////aaa       
            List<QticketInfo> qtL = pendingQTList;
            for (QticketInfo qtInfo : qtL) {
            Object obj1[] = (Object[]) new StaticSentence(app.getSession(), "SELECT ID FROM QTKITCHEN WHERE ID = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qtInfo.getId());
            if(obj1!=null){
                JOptionPane.showMessageDialog(null, "QT is not Prepared or Delivered to be Billed", "MESSAGE", JOptionPane.WARNING_MESSAGE);
                return;
            }
            }
///////////////aaa
            
            
            List<QticketInfo> qtList = pendingQTList;
            String temp;
            for (QticketInfo qtInfo : qtList) {
                Object obj[] = (Object[]) new StaticSentence(app.getSession(), "SELECT COUNT(QTITEMID) FROM DISCOUNTLIST WHERE QTITEMID = ? AND AUTHORISED is null GROUP BY QTITEMID ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(qtInfo.getId());
                //temp
                if (obj != null) {
                    temp = obj[0].toString();
                    tempint = Integer.parseInt(temp);
                    if (tempint > 0) {
                        JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.billwarning"), AppLocal.getIntString("message.billwarningtitle"), JOptionPane.WARNING_MESSAGE);
                        flag = 1;
                        break;
                    }
                }
            }

        } catch (BasicException e) {
        }
        if (flag == 0) {
            try {
                Map<String,List<QticketInfo>> map=new HashMap<String, List<QticketInfo>>();
                List<QticketInfo> qtItems=null;
                for(QticketInfo qt1:pendingQTList){
                    
                    if(map.get(qt1.getWarehouse())==null)
                        qtItems=new ArrayList<QticketInfo>();
                    else
                       // qtItems=new ArrayList(map.get(qt1.getWarehouse()));
                    qtItems=map.get(qt1.getWarehouse());
                    qtItems.add(qt1);
                    map.put(qt1.getWarehouse(), qtItems);
                }
                int i=1;
                for(String key:map.keySet()){
                    bla = new BillLogicApply(dlSales, qticket, dlBill, map.get(key));
                    BillInfo b = bla.getBillInfo();
                    //Billpage billpage = Billpage.getDialog(this, dlSales, bla, cust,map.size()-1);
                    Billpage billpage = Billpage.getDialog(this, dlSales, bla, cust,map.size()-i);
                    billpage.init(b);
                    if (billpage.showDialog()) {
                        reloadPendingQTList(false);
                    }
                    i++;
                }
            } catch (BasicException e) {
                new MessageInf(e).show(this);
            } finally {
                bla = null;
            }
        }
    }

    private void showRemarks() {
        int i = jTable1.getSelectedRow();
        if (i >= 0 && i < jTable1.getRowCount()) {
            QticketInfo qt = pendingQTList.get(i);
            try {
                int j = jTable2.getSelectedRow();
                List<QTicketLineInfo> qtlines = qticket.getQTicketLineList(qt.getId());
                if (j >= 0 && j < qtlines.size()) {
                    jRemarks.setText(qtlines.get(j).getRemarks());
                }
            } catch (BasicException e) {
                new MessageInf(e).show(this);
            }
        }
    }

    private void printqt(String prcategory, QticketInfo qTicket) {
        //qTicket.getWaiter().getNmae;
        //  qTicket.getWaiter();
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.QT");
        String waitername;
        String table1;
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM WAITER WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qTicket.getWaiter());

            if (obj == null) {
                waitername = "";
            } else {
                waitername = obj[0].toString();
            }
            Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qTicket.getPlace());

            if (obj1 == null) {
                table1 = "";
            } else {
                table1 = obj1[0].toString();
            }
            boolean flag = m_App.getAppUserView().getUser().hasPermission("Bar Counter");
            int flag1 = 0;
            if (m_App.getAppUserView().getUser().hasPermission("bar counter")) {
                flag1 = 1;
            }
            if (m_App.getAppUserView().getUser().hasPermission("rest counter")) {
                flag1 = 2;
            }
            if (m_App.getAppUserView().getUser().hasPermission("chat counter")) {
                flag1 = 3;
            }

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("place", table1);
            script.put("waiter", waitername);
            script.put("ticket", qTicket);
            script.put("flag", flag1);
            script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
    }

    private void qtrePrint() {
        int i = jTable1.getSelectedRow();
        String waitername;
        String table1;
        if (i >= 0 && i < jTable1.getRowCount()) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprint"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                QticketInfo qTicket = pendingQTList.get(i);
                //  printqt(qt.getprCategory(), qt);
                String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.ReQt");

                try {
                    AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM WAITER WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qTicket.getWaiter());

                    if (obj == null) {
                        waitername = "";
                    } else {
                        waitername = obj[0].toString();
                    }
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qTicket.getPlace());

                    if (obj1 == null) {
                        table1 = "";
                    } else {
                        table1 = obj1[0].toString();
                    }
                    boolean flag = m_App.getAppUserView().getUser().hasPermission("Bar Counter");
                    m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
                    int flag1 = 0;
                    if (m_App.getAppUserView().getUser().hasPermission("bar counter")) {
                        flag1 = 1;
                    }
                    if (m_App.getAppUserView().getUser().hasPermission("rest counter")) {
                        flag1 = 2;
                    }
                    if (m_App.getAppUserView().getUser().hasPermission("chat counter")) {
                        flag1 = 3;
                    }
                    
                    String Displayname="";
                    Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(), "select rdisplayname from locations where id=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qTicket.getWarehouse());

                    if (obj4 == null) {
                        Displayname = "";
                    } else {
                        Displayname = obj4[0].toString();
                    }
                    
                    
                    ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                    script.put("place", table1);
                    script.put("waiter", waitername);
                    script.put("flag", 4);
                    script.put("Displayname", Displayname);
                    
                    script.put("ticket", qTicket);
                    script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(qTicket.getprCategory()).getPrinter());
                    m_TTP.printTicket(script.eval(sresource).toString());
                } catch (ScriptException e) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                    msg.show(this);
                } catch (TicketPrinterException e) {
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                    msg.show(this);
                } catch (Exception e) {
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jBillButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextCustomer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextPlace = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextWaiter = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jBtnDiscount = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jRemarks = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jShowAll = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pending QT"); // NOI18N
        setBackground(new java.awt.Color(236, 233, 216));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jButton1.setToolTipText("Cancel"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jBillButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/vcard.png"))); // NOI18N
        jBillButton.setToolTipText("Bill"); // NOI18N
        jBillButton.setMaximumSize(new java.awt.Dimension(48, 36));
        jBillButton.setMinimumSize(new java.awt.Dimension(48, 36));
        jBillButton.setPreferredSize(new java.awt.Dimension(48, 36));
        jBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBillButtonActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sl No", "Product", "Quantity", "Rate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setMinWidth(40);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(40);
        jTable2.getColumnModel().getColumn(2).setMinWidth(60);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(60);
        jTable2.getColumnModel().getColumn(3).setMinWidth(60);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(3).setMaxWidth(60);

        jLabel1.setText("Customer"); // NOI18N

        jTextCustomer.setEditable(false);
        jTextCustomer.setText("jTextField1"); // NOI18N

        jLabel2.setText("Table"); // NOI18N

        jTextPlace.setEditable(false);
        jTextPlace.setText("jTextField1"); // NOI18N

        jLabel3.setText("Waiter"); // NOI18N

        jTextWaiter.setEditable(false);
        jTextWaiter.setText("jTextField1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sl No", "QT Number", "Print Category", "QT Date", "Waiter"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Details"); // NOI18N

        jBtnDiscount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/package.png"))); // NOI18N
        jBtnDiscount.setToolTipText("Discount"); // NOI18N
        jBtnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDiscountActionPerformed(evt);
            }
        });

        jLabel6.setText("Remarks");

        jRemarks.setColumns(20);
        jRemarks.setRows(5);
        jScrollPane3.setViewportView(jRemarks);

        jShowAll.setText("Show All QTs");
        jShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jShowAllActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileprint.png"))); // NOI18N
        jButton2.setToolTipText("Reprint");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, 0, 0, Short.MAX_VALUE)
                            .addComponent(jButton1, 0, 0, Short.MAX_VALUE)
                            .addComponent(jBtnDiscount, 0, 0, Short.MAX_VALUE)
                            .addComponent(jBillButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(jShowAll))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextCustomer)
                                    .addComponent(jTextPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextWaiter, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jShowAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(jTextCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jTextWaiter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBillButtonActionPerformed
        displayBill();
        dispose();
        //praveen:exit for every transaction for kiosk mode---start
        if (app.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
    //praveen:exit for every transaction for kiosk mode---end

}//GEN-LAST:event_jBillButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBtnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDiscountActionPerformed
        // TODO add your handling code here:
        int i = jTable2.getSelectedRow();
        // int temp=pendingQTList.size();
        // if (i >= 0 && i <= pendingQTList.size()) {
        if (i >= 0) {
            try {
                //createDiscount();
                requestDiscount();
            } catch (BasicException e) {
                new MessageInf(e).show(this);
            }
        } else {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.itemnotselected"));
            msg.show(this);
        }
        if (app.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }

}//GEN-LAST:event_jBtnDiscountActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        refreshItemModel();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        showRemarks();
    }//GEN-LAST:event_jTable2MouseClicked

    private void jShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jShowAllActionPerformed
        try {
            reloadPendingQTList(jShowAll.isSelected());
        } catch (BasicException e) {
            new MessageInf(e).show(this);
        }
}//GEN-LAST:event_jShowAllActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        qtrePrint();
        if (app.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }

    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBillButton;
    private javax.swing.JButton jBtnDiscount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextArea jRemarks;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox jShowAll;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextCustomer;
    private javax.swing.JTextField jTextPlace;
    private javax.swing.JTextField jTextWaiter;
    // End of variables declaration//GEN-END:variables
}
