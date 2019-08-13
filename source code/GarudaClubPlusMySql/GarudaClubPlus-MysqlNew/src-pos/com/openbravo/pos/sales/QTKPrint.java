/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QTKPrint.java
 *
 * Created on Oct 25, 2012
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPrincipalApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author a
 */
public class QTKPrint extends javax.swing.JDialog {

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
    private QTKPrintTableModel qtkpmodel;
    private Session s;
    private String waiter;
    private String table;
    private String floor;
    private String qtid;

    public QTKPrint(Frame parent, AppView app, DataLogicSales dlSales, Qticket qticket) {
        super(parent, true);
        this.app = app;
        this.qticket = qticket;
        this.dlSales = dlSales;
        this.dlBill = LookupUtilityImpl.getInstance(app).getDataLogicBill();

    }

    public QTKPrint(Dialog parent, AppView app, DataLogicSales dlSales, Qticket qticket) {
        super(parent, true);
        this.app = app;
        this.qticket = qticket;
        this.dlSales = dlSales;
        this.dlBill = LookupUtilityImpl.getInstance(app).getDataLogicBill();
    }

    public boolean reloadPendingQTKPrint(boolean showAll) throws BasicException {
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

    /** Creates new form QTKPrint */
//    public boolean setCustomer(CustomerInfo customer) throws BasicException {
//        cust = customer;
//        boolean flag = true;
//        flag = reloadPendingQTKPrint(false);
//        if (flag == true) {
//            List<PlacesInfo> placesList = dlSales.getPlacesList().list();
//            placeMap.clear();
//            for (PlacesInfo pInfo : placesList) {
//                placeMap.put(pInfo.getID(), pInfo);
//            }
//
//            List<WaiterInfo> waitersList = dlSales.getWaiterList(app.getAppUserView().getUser().getRole());
//            waiterMap.clear();
//            for (WaiterInfo wInfo : waitersList) {
//                waiterMap.put(wInfo.getID(), wInfo);
//            }
//
//            initComponents();
//
//            jTable2.setModel(new QTItemTableModel(this, dlSales));
//
//            jTextCustomer.setText(customer.getName());
//            jTextPlace.setText(null);
//            jTextWaiter.setText(null);
//            customer1 = customer.getName();
//            if (!pendingQTList.isEmpty()) {
//                QticketInfo qt = pendingQTList.get(0);
//                jTextPlace.setText(placeMap.get(qt.getPlace()).getName());
//                jTextWaiter.setText(waiterMap.get(qt.getWaiter()).getName());
//            }
//        }
//        return flag;
//    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    public static QTKPrint getDialog(Component parent, AppView app, DataLogicSales dlSales, Qticket qticket) {

        Window window = getWindow(parent);

        QTKPrint myqtList;

        if (window instanceof Frame) {
            myqtList = new QTKPrint((Frame) window, app, dlSales, qticket);
        } else {
            myqtList = new QTKPrint((Dialog) window, app, dlSales, qticket);
        }

        return myqtList;
    }

    public void refreshModel() throws BasicException {

        for (QticketInfo qtInfo : pendingQTList) {
            if (qtInfo.getLines() == null) {
                qtInfo.setLines(qticket.getQTicketLineList(qtInfo.getId()));
            }
        }
    }

    private void refreshItemModel() {
int i=0;//temp
//        if (i >= 0 && i < pendingQTList.size()) {
            QticketInfo qtInfo = pendingQTList.get(i);
            ((QTItemTableModel) jTable2.getModel()).refresh(qtInfo);
//        }
    }

    public boolean showDialog(AppView app,String customer) throws BasicException {
        boolean flag=true;// = setCustomer(customer);
        qtid = customer;
        if (flag == true) {
            initComponents();
            displayModel(app,customer);
            refreshModel();
            setVisible(true);
        }
        return flag;
    }


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
                JOptionPane.showMessageDialog(null, "It is not Prepared or Delivered to be Billed", "MESSAGE", JOptionPane.WARNING_MESSAGE);
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
//        if (flag == 0) {
//            try {
//                Map<String,List<QticketInfo>> map=new HashMap<String, List<QticketInfo>>();
//                List<QticketInfo> qtItems=null;
//                for(QticketInfo qt1:pendingQTList){
//                    
//                    if(map.get(qt1.getWarehouse())==null)
//                        qtItems=new ArrayList<QticketInfo>();
//                    else
//                       // qtItems=new ArrayList(map.get(qt1.getWarehouse()));
//                    qtItems=map.get(qt1.getWarehouse());
//                    qtItems.add(qt1);
//                    map.put(qt1.getWarehouse(), qtItems);
//                }
//                int i=1;
//                for(String key:map.keySet()){
//                    bla = new BillLogicApply(dlSales, qticket, dlBill, map.get(key));
//                    BillInfo b = bla.getBillInfo();
//                    Billpage billpage = Billpage.getDialog(this, dlSales, bla, cust,map.size()-1);
//                    billpage.init(b);
//                    if (billpage.showDialog()) {
//                        reloadPendingQTKPrint(false);
//                    }
//                    i++;
//                }
//            } catch (BasicException e) {
//                new MessageInf(e).show(this);
//            } finally {
//                bla = null;
//            }
//        }
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
        int i = 0;
        String waitername;
        String table1;
   //     if (i >= 0 && i < jTable1.getRowCount()) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprint"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                QticketInfo qTicket = pendingQTList.get(i);
                //  printqt(qt.getprCategory(), qt);
                String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.ReQt");

                try {
                    AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                    
   Object[] obq = (Object[]) new StaticSentence(m_App.getSession(), "SELECT WAITER FROM qticket WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qtid);
   Object[] obq1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT PLACE FROM qticket WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(qtid);

                    
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM WAITER WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obq[0].toString());

                    if (obj == null) {
                        waitername = "";
                    } else {
                        waitername = obj[0].toString();
                    }
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obq1[0].toString());

                    if (obj1 == null) {
                        table1 = "";
                    } else {
                        table1 = obj1[0].toString();
                    }
                    boolean flag = m_App.getAppUserView().getUser().hasPermission("Bar Counter");
                    m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
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
                    ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                    script.put("place", table1);
                    script.put("waiter", waitername);
                    script.put("flag", flag1);
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
  //      }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pending QT"); // NOI18N
        setBackground(new java.awt.Color(236, 233, 216));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sl No", "QT Number", "Product", "Quantity", "Rate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Details"); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileprint.png"))); // NOI18N
        jButton2.setToolTipText("Reprint");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Reprint QT:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(341, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    private void displayModel(AppView app1, String customer) {
       List<QTicketLineInfo> qtList1 = null;
        try {
        
        AppView m_App= LookupUtilityImpl.getInstance(null).getAppView();
        s=m_App.getSession();
        qtkpmodel = QTKPrintTableModel.loadInstance(m_App,customer);
        jTable2.setModel(qtkpmodel.getqtdetailsTableModel());
        
        
        TableColumnModel columnModel = jTable2.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(0).setMaxWidth(80);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(1).setMaxWidth(140);
        columnModel.getColumn(2).setPreferredWidth(180);
        columnModel.getColumn(2).setMaxWidth(250);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(3).setMaxWidth(120);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(4).setMaxWidth(100);
        
        
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        qticket = (Qticket) m_App.getBean("com.openbravo.pos.sales.Qticket");
        qticket.setDataLogicSales(dlSales);
   //     jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        qticket.setAppView(m_App);
        
        Object[] obid = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CUSTOMER FROM QTKITCHEN WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(customer);
        Object[] obr = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CREATEDBY FROM QTKITCHEN WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(customer);
        Object[] obrole = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ROLE FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obr[0].toString());
                 pendingQTList = new ArrayList<QticketInfo>();
         List<QticketInfo>  qtList = qticket.getPendingQTicketList(obid[0].toString());
          for (QticketInfo qticketInfo : qtList) {
              String s1 = qticketInfo.getId();
              if(s1.equals(customer))
                pendingQTList.add(qticketInfo);
            }
        app=m_App;
        
        } catch (BasicException ex) {
            Logger.getLogger(QTKPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public final List<QticketInfo> getQTicketLineList(String customer) throws BasicException {
//
//         return (List<QticketInfo>) new PreparedSentence(s
//                , "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, PRCATEGORY, BILLED, BILLREF, CREATEDBY, CRDATE, WAREHOUSE,INITIATOR FROM QTICKET WHERE CUSTOMER = ? ORDER BY ID"
//                , SerializerWriteString.INSTANCE
//                , new SerializerReadClass(QticketInfo.class)).list(customer);
//    }
    
}
