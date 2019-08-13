/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BillList.java
 *
 * Created on Dec 12, 2008, 11:47:36 AM
 */
package com.openbravo.pos.sales.restaurant;

import com.lowagie.text.Rectangle;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillLineInfo;
import com.openbravo.pos.sales.BillListTableModel;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.sales.BillTaxesLogic;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class DebtBillListnum1 extends javax.swing.JDialog {

    private BillListTableModel billtablemodel;
    private CreditConfirmationTableModelnum1 cmodel;
    private List<BillInfo> blist;
    private BillLogic blogic;
    private DataLogicSales dlSales;
    private boolean resultok = false;
    private CustomerInfo customer;
    private AppView app;
    private List<CreditConfirmListnum1> list;
    private boolean flag;
    private BillTaxesLogic taxeslogic;
    private ListKeyed taxcollection;
    private TicketParser m_TTP;
    private DataLogicSystem dlSystem;
    private CardReader cr;
    private String portNumber;
    private List<RoleInfo> rolelist;
     //praveen:confirmer changes---start
    private String confirmer;
    private DataLogicFacilities dlfac;
      public static boolean Rp = true;
       public static boolean R = true;
       
    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }
    //praveen:confirmer changes---end

    /** Creates new form BillList */
    public DebtBillListnum1(java.awt.Frame parent, DataLogicSales dlSales, AppView app, boolean flag) {
        super(parent, true);        
        this.dlSales = dlSales;
        this.app = app;
        this.flag = flag;
    }

    public DebtBillListnum1(java.awt.Dialog parent, DataLogicSales dlSales, AppView app, boolean flag) {
        super(parent, true);
        this.dlSales = dlSales;
        this.app = app;
        this.flag = flag;
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

    public static DebtBillListnum1 getDialog(Component parent, DataLogicSales dlSales, AppView app, boolean flag) {

        Window window = getWindow(parent);

        DebtBillListnum1 bill;

        if (window instanceof Frame) {
            bill = new DebtBillListnum1((Frame) window, dlSales, app, flag);
        } else {
            bill = new DebtBillListnum1((Dialog) window, dlSales, app, flag);
        }

        return bill;
    }
    
    public static class CreditConfirmListnum1 implements SerializableRead {

        private String id;
        private String billref;
        private String customer;
        private String ckey;
        private String waiter;
        private String ruser;
        private Timestamp date;
        private double amt;
        private String custid;
        
        private String facid;

        public void readValues(DataRead dr) throws BasicException {
            date = dr.getTimestamp(1);
            customer = dr.getString(2);
            ckey = dr.getString(3);
            waiter = dr.getString(4);
            billref = dr.getString(5);
            id = dr.getString(6);
            ruser = dr.getString(7);
            amt = dr.getDouble(8);
            custid = dr.getString(9);

        // ckey=dr.getString(8);
        }

        public String getFacid() {
            return facid;
        }

        public String getSearchkey() {
            return ckey;
        }

        public String getCustomer() {
            return customer;
        }

        public String getCSearchkey() {
            return ckey;
        }

        public String getWaiter() {
            return waiter;
        }

        public String getBillref() {
            return billref;
        }

        public String getID() {
            return id;
        }

        public String getRuser() {
            return ruser;
        }

        public Timestamp getDate() {
            return date;
        }

        public String printDate() {
            return Formats.DATE.formatValue(date);
        }

        public double getAmount() {
            return amt;
        }

        public String printAmount() {
            return Formats.ConvertDoubleToString(amt);
        }

        public String getCustomerID() {
            return custid;
        }
    }

    public void UPs(){
      //   int i = jTable1.getSelectedRow();
        //if(p){
       int i = jTable1.getSelectionModel().getMinSelectionIndex();
        if (i < 0){
            i = jTable1.getModel().getRowCount() - 1; // No hay ninguna seleccionada
        } else {
            i --;
            if (i < 0) {
                i = 0;
            }
        }

        if ((i >= 0) && (i < jTable1.getModel().getRowCount())) {
            // Solo seleccionamos si podemos.
         setSelectedIndex(i);
         
           // jTable1.getSelectionModel().setSelectionInterval(i, i);
        }        
//        
        
//////     
        //}
    }
     public void setSelectedIndex(int i){
       
        // Seleccionamos
        jTable1.getSelectionModel().setSelectionInterval(i,i);

        // Hacemos visible la seleccion.
        java.awt.Rectangle oRect =jTable1.getCellRect(i, 0, true);
        jTable1.scrollRectToVisible(oRect);
    }
    
    public void DWNs(){
        
//   
     // int i = jTable1.getSelectedRow();   
        //if(p){
      int i =jTable1.getSelectionModel().getMaxSelectionIndex();
        if (i < 0){
            i =  0; // No hay ninguna seleccionada
        } else {
            i ++;
            if (i >= jTable1.getModel().getRowCount() ) {
                i = jTable1.getModel().getRowCount() - 1;
            }
        }

        if ((i >= 0) && (i < jTable1.getModel().getRowCount())) {
            // Solo seleccionamos si podemos.
     setSelectedIndex(i);
           // jTable1.getSelectionModel().setSelectionInterval(i, i);
        }        
//
////       
//        
       // }
   }
    
    
    
    
    
    
    private void confirm(final CreditConfirmListnum1 c) throws BasicException {
        try {
            Transaction t = new Transaction(app.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                    //CreditConfirmList c = list.get(row);
                    String s = c.getCustomerID();
                    String s1 = c.getID();
                    boolean flag = true;
                    Object[] obj = (Object[]) new PreparedSentence(app.getSession(), "SELECT C.MOBILE,M.NAME FROM CUSTOMERS C JOIN MEMTYPE M ON C.MEMTYPE=M.ID WHERE C.ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(s);
                    String str = null;
                    String memtype = null;
                    if (obj != null) {
                        if (obj[0] != null) {
                            str = String.valueOf(obj[0]);
                        }
                        if (obj[1] != null) {
                            memtype = String.valueOf(obj[0]);
                        } else {
                            flag = false;
                        }
                    } else {
                        flag = false;
                    }
                    if (flag && memtype.equals("Affiliated Member")) {
                        flag = false;
                    }
                    if (flag) {
                        //warehouse changes - start
                        //praveen:initiator changes---start
                        BillInfo binfo = (BillInfo) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,BILL.TAXTOTAL FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfo.class)).find(c.getBillref());
                        //praveen:initiator changes---start
                        //warehouse changes - end
                        //praveen:checking bill is paid or not
                        Object obj1 = new StaticSentence(app.getSession(), "SELECT RECEIPT FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
                        if (obj1 != null) {
                            JOptionPane.showMessageDialog(null, "Already billed.Press OK to reresh the list", "warning", JOptionPane.OK_OPTION);
                            loadData();
                        } else {
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//praveen:changed from name to role
                            binfo.setReceiptRef(rno);
                            BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                            binfo.setLines(bl.getBillLineList(binfo.getID()));
                            String user = app.getAppUserView().getUser().getName();
                            Date d = new Date();
                            int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) {
                                //praveen:confirmer changes---start
                                new StaticSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,DATENEW,RUSER,DESC_,CONFIRMER) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{rno, d, user, c.getID(),getConfirmer()});
                                new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), rno, "debt", c.getAmount(), user, d, c.getCustomerID()});
                                setConfirmer("");
                                //praveen:confirmer changes---end
                                boolean status = markBillAsPaid(binfo);
                                if (status == false) {
                                    throw new BasicException();
                                }
                                try {
                                    // if("debt".equals(p.getName())){
                                    String msg = "Dear Member,\rYour a/c with us has been debited by " + Formats.CURRENCY.formatValue(c.getAmount()) + " for bar usage.Bill no. " + c.getBillref() + " On " + Formats.DATE.formatValue(c.getDate());
                                    if (str != null && str.length() == 10) {
                                        dlSales.updatetosendMsg(msg, c.getCustomerID(), str, 2);
                                    }
                                // }
                                } catch (Exception e) {
                                }
                                loadData();
                            }
                        }
                    }
                    return null;
                }
            };
            t.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadData() throws BasicException {
        //praveen:start-------displaying pending list as per role 
        String role = app.getAppUserView().getUser().getRole();
//        String warehouse = null;
//        Object obj = app.getAppUserView().getUser().getWarehouse();
//            if (obj != null) {
//                String[] warehouses = obj.toString().split("#");
//                warehouse = warehouses[0];
//            }
//        rolelist = dlSales.getCatalogListToRole(role);
//        StringBuffer condition = new StringBuffer("");
//        Object[] params = new Object[rolelist.size()];
//        Datas[] data = new Datas[rolelist.size()];
//        int i = 0;
//        for (RoleInfo r : rolelist) {
//            data[i] = Datas.STRING;
//            params[i] = r.getID();
//            condition.append(" ? ,");
//            i++;
//        }
//        if (condition.length() > 0) {
//            condition.deleteCharAt(condition.lastIndexOf(","));
//        }
//        if (rolelist.size() > 0) {
//            list = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID AND W.COUNTER IN (" + condition.toString() + ")  ORDER BY D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//        }
        String warehouse = null;
        String[] warehouses = null;

        Object obj = app.getAppUserView().getUser().getWarehouse();
        if (obj != null) {
            warehouses = obj.toString().split("#");
            warehouse = warehouses[0];
        }
        if (warehouses != null) {
            Object[] params = new Object[warehouses.length];
            Datas[] data = new Datas[warehouses.length];
            StringBuffer condition = new StringBuffer("");
            for (int j = 0; j < warehouses.length; j++) {
                data[j] = Datas.STRING;
                params[j] = warehouses[j].toString();
                condition.append(" ? , ");
            }
            if (condition.length() > 0) {
                condition.deleteCharAt(condition.lastIndexOf(","));
            }
            list = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse in  (" + condition.toString() + ") group by d.id ORDER BY B.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmListnum1.class)).list(params);
//            List<CreditConfirmList> list1 = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse not in  (" + condition.toString() + ") and W.COUNTER IN (select category from sequencedetail where username='"+role+"') group by d.id ORDER BY BILL.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//            if(list1!=null || list1.size()>0){
//                list.addAll(list1);
//            }
        } else {
            list = null;
        }
        //praveen:end
        if (list == null || list.size() <= 0) {
            list = new ArrayList<CreditConfirmListnum1>();
        }
        cmodel = new CreditConfirmationTableModelnum1(list);
        jTable1.setModel(cmodel);
    }

    public void startCardReader() {
        try {
            String portNumber = app.getProperties().getProperty("card.portnumber");
            boolean cardAccessOnlyFlag = false;
            if (app.getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf(app.getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
            System.out.println(portNumber);
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void init() throws BasicException {
        initComponents();
        
        
//         jButton3.addActionListener(new ActionListener(){  //code for Up button
//      public void actionPerformed(ActionEvent ae){  
//              
//       RRprnt();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_R) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//               if(R){
//                    R=false;
//                RRprnt();  
//                R=true;
//                }
//          }
//          }  
//          return false;}}); 
        
        
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jButton1.setText("Confirm");
        jButton2.setText("<html>Change to<br>Cash Bill</html>");
        jButton3.setText("Reprint");
        try {
            jButton2.setIcon(new ImageIcon(ImageIO.read(DebtBillListnum1.class.getResourceAsStream("/com/openbravo/images/cash.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        loadData();
        startCardReader();
        List<TaxInfo> taxlist = dlSales.getTaxList().list();
        taxeslogic = new BillTaxesLogic(taxlist);
        taxcollection = new ListKeyed<TaxInfo>(taxlist);
        jButton1.setEnabled(flag);
        jButton2.setEnabled(flag);
        dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());

        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(2).setPreferredWidth(60);
        columnModel.getColumn(2).setMaxWidth(60);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(4).setMaxWidth(100);
        columnModel.getColumn(5).setPreferredWidth(120);
        columnModel.getColumn(5).setMaxWidth(120);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(6).setMaxWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(6).setMaxWidth(80);


    }
    
      public void RRprnt(){
          if(Rp){
           int row = jTable1.getSelectedRow();
        if (row >= 0) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                try {
                    CreditConfirmListnum1 c = list.get(row);
                    BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                    BillInfo binfo = bl.getBillInfo(c.getBillref());
                    printTicket("Printer.Ticket", binfo, binfo.getPlace());
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "debt");
                    l.add(p);
                    binfo.setPayments(l);
                    binfo.setReceiptRef(c.getID());
                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                } catch (BasicException ex) {
                    Logger.getLogger(DebtBillListnum1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
      }
      }
    public boolean showDialog() {
        try {
            init();
            setVisible(true);
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return resultok;
    }

    public boolean markBillAsPaid(final BillInfo bill) throws BasicException {
        boolean status = true;
        Object[] values = new Object[]{bill.getID(), bill.getReceiptRef()};
        Datas[] datas = new Datas[]{Datas.STRING, Datas.STRING};
        int cnt = new PreparedSentence(app.getSession(), "UPDATE BILL SET RECEIPT = ? WHERE ID = ? AND PAID=TRUE", new SerializerWriteBasicExt(datas, new int[]{1, 0})).exec(values);
        if (cnt != 0) {
            PreparedSentence l = new PreparedSentence(app.getSession(), "UPDATE BILLITEM SET ATTRIBUTES = ? WHERE ID = ?", SerializerWriteParams.INSTANCE);
            for (final BillLineInfo line : bill.getLines()) {
                l.exec(new DataParams() {

                    @Override
                    public void writeValues() throws BasicException {
                        try {
                            ByteArrayOutputStream o = new ByteArrayOutputStream();
                            line.getProperties().storeToXML(o, AppLocal.APP_NAME, "UTF-8");
                            setBytes(1, o.toByteArray());
                        } catch (IOException e) {
                            setBytes(1, null);
                        }
                        setString(2, line.getID());
                    }
                });
            }
        } else {
            status = false;
            JOptionPane.showMessageDialog(null, "Error Occured...Try again...", null, JOptionPane.WARNING_MESSAGE);
        }
        return status;
    }

    private void printTicket(String sresourcename, BillInfo ticket, Object ticketext) {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML(sresourcename);
        String receiptdate;

        String waitername;
        String table;
        int debt1 = 0;
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            Date date = new Date();
            receiptdate = date.toString();

            String sign = "";
            String temp = "";
            try {
                List<PaymentInfo> pi = ticket.getPayments();
                for (int i = 0; i < pi.size(); i++) {
                    temp = pi.get(i).getName();
                    if (pi.get(i).getName().equals("debt")) {
                        debt1 = 1;
                        sign = "sign";
                    }
                }
            } catch (Exception e) {
            }
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();

            try {

                WaiterInfo w = LookupUtilityImpl.getInstance(null).getWaiterMap().get(ticket.getWaiter());
                waitername = w.getName();
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ticket.getPlace());

                if (obj1 == null) {
                    table = "";
                } else {
                    table = obj1[0].toString();
                }
                //warehouse changes -start
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,ROLE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(ticket.getCreatedBy());
                String warehouse = null;
                if (obj2 != null && obj2[3] != null) {
                    String[] wArr = obj2[3].toString().split("#");
                    warehouse = wArr[0];
                }
                AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString(), warehouse);
                //warehouse changes -end
                appuser.fillPermissions(dlSystem);
                boolean flag1 = appuser.hasPermission("bar counter");

//                AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString());
//                appuser.fillPermissions(dlSystem);
//                boolean flag2 = appuser.hasPermission("bar counter");
                boolean crconf = false;
                if (ticketext.equals("cerditconf")) {
                    crconf = true;
                }
                String[] str = null;
                if (obj2[3] != null) {
                    str = obj2[3].toString().split("#");
                }
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(str[0]);
                String name = null;
                if (obj3 != null && obj3[0] != null) {
                    name = obj3[0].toString();
                }
                int i = jTable1.getSelectedRow();
                String s = jTable1.getValueAt(i, 3).toString();
                Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                        "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.searchkey=? GROUP BY A.ACCOUNTID " +
                        "UNION ALL " +
                        "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.searchkey=? GROUP BY A.ACCOUNTID) " +
                        "AS TOTAL GROUP BY ACC",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{s, s});
                Double d = 0.0;
                Double d1 = 0.0;
                if (obj4 != null) {
                    if (obj4[0] != null) {
                        d = Double.valueOf(obj4[0].toString());
                    }
                    if (obj4[1] != null) {
                        d1 = Double.valueOf(obj4[1].toString());
                    }
                }
                Double bal = d - d1;
                String accountBalance = null;
                if (bal > 0) {
                    bal = dlfac.roundTwoDecimals(bal);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                } else {
                    bal = bal * -1;
                    bal = dlfac.roundTwoDecimals(bal);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                }
                taxeslogic.calculateTaxes(ticket);
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("taxes", taxcollection);
                script.put("taxeslogic", taxeslogic);
                script.put("ticket", ticket);
                script.put("place", table);
                script.put("flag", flag);
                script.put("flag1", flag1);
                script.put("waiter", waitername);
                script.put("date", receiptdate);
                script.put("sign", sign);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("crconf", crconf);
                script.put("displayName", name);
                script.put("balance", accountBalance);

                m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (BasicException e) {
                new MessageInf(e).show(this);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent)c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                    if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                        jc.setBackground(Color.cyan);
                    }
                    else {
                        jc.setBackground(Color.white);
                    }
                    if(isCellSelected(rowIndex, vColIndex))
                    jc.setBackground(Color.lightGray);
                }
                return c;
            }};
            jButton1 = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();
            jButton3 = new javax.swing.JButton();
            jButton4 = new javax.swing.JButton();
            m_jUp = new javax.swing.JButton();
            m_jDown = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Pending Bill List");

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String [] {
                    "Sl No", "Member Name", "Member ID", "Bill No", "Date"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable1.setFocusable(false);
            jScrollPane1.setViewportView(jTable1);

            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
            jButton1.setText("Select");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton2.setText("jButton2");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jButton3.setText("jButton3");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });
            jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jButton3KeyPressed(evt);
                }
            });

            jButton4.setText("esc");

            m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
            m_jUp.setFocusPainted(false);
            m_jUp.setFocusable(false);
            m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
            m_jUp.setVisible(false);
            m_jUp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    m_jUpActionPerformed(evt);
                }
            });

            m_jDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
            m_jDown.setFocusPainted(false);
            m_jDown.setFocusable(false);
            m_jDown.setMargin(new java.awt.Insets(8, 14, 8, 14));
            m_jDown.setRequestFocusEnabled(false);
            m_jDown.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    m_jDownActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(61, 61, 61)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(m_jDown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(m_jUp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(57, 57, 57))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(m_jUp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(m_jDown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(52, 52, 52))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(jButton4)
                            .addGap(39, 39, 39))))
            );

            jButton3.setToolTipText("CTRL P");
            jButton4.addActionListener(new ActionListener(){  //code for QT
                public void actionPerformed(ActionEvent ae){

                }});
                KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher(){
                    public boolean dispatchKeyEvent(KeyEvent e){
                        if(e.getID() == KeyEvent.KEY_PRESSED)
                        {
                            if((e.getKeyCode() == KeyEvent.VK_ESCAPE) )

                            dispose();
                        }
                        return false;}});

            jButton4.setVisible(false);
            m_jUp.addActionListener(new ActionListener(){  //code for UP

                public void actionPerformed(ActionEvent ae){

                    UPs()
                    ;}});
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new KeyEventDispatcher(){
            public boolean dispatchKeyEvent(KeyEvent e){
                if(e.getID() == KeyEvent.KEY_PRESSED)
                {
                    if((e.getKeyCode() == KeyEvent.VK_UP)  )

                    UPs();

                }
                return false;}});
    m_jUp.setVisible(false);
    m_jDown.addActionListener(new ActionListener(){  //code for down button

        public void actionPerformed(ActionEvent ae){

            DWNs()
            ;}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
    public boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            if((e.getKeyCode() == KeyEvent.VK_DOWN)  )

            DWNs();
        }
        return false;}});
m_jDown.setVisible(false);

m_jDown.setVisible(false);

pack();
}// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final int row = jTable1.getSelectedRow();
        if (row >= 0) {
            final CreditConfirmListnum1 c = list.get(row);
            Object[] details;
            String id = null;
            try {
                Object obj = app.getAppUserView().getUser().getWarehouse(); //(Object[]) new StaticSentence(app.getSession(), "SELECT PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(c.getRuser());
                String warehouse = null;
                String[] warehouses = null;
                if (obj != null) {
                    warehouses = obj.toString().split("#");
                    warehouse = warehouses[0];
                }
                if (warehouses != null) {
                    Object[] params = new Object[warehouses.length];
                    Datas[] data = new Datas[warehouses.length];
                    StringBuffer condition = new StringBuffer("");
                    for (int j = 0; j < warehouses.length; j++) {
                        data[j] = Datas.STRING;
                        params[j] = warehouses[j].toString();
                        condition.append(" ? , ");
                    }
                    if (condition.length() > 0) {
                        condition.deleteCharAt(condition.lastIndexOf(","));
                    }
                    Object obj1 = new StaticSentence(app.getSession(), "SELECT FACILITY FROM LOCATIONS WHERE ID IN (" + condition.toString() + ")", new SerializerWriteBasic(data), SerializerReadString.INSTANCE).find(params);
                    if (obj1 != null) {
                        id = obj1.toString();
                        Object[] fac = (Object[]) new StaticSentence(app.getSession(), "SELECT F.CONFIRMCONTROL FROM FACILITY F WHERE  F.ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(id);
                        if ((Boolean) fac[0] == false) {
                            confirm(c);
                        } else {
                            String card = cr.getData().toString();
                            if (card == null || card.length() <= 0) {
                                //JOptionPane.showMessageDialog(this, "Please swipe a card");
                                //praveen:added to request authorisation for credit confirm----start
                                if (JOptionPane.showConfirmDialog(this, "If ur card is not issued, click YES to 'Send for Authorisation' or if issued swipe a valid card", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                    Object[] object = (Object[]) new StaticSentence(app.getSession(), "SELECT MEMID,STATUS_ FROM USERAUTHORISATION WHERE MEMID=? AND BILLREF=? ORDER BY RDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN})).find(new Object[]{c.getCustomerID(), c.billref});
                                    if (object != null && object[0] != null) {
                                        if (object[1] != null) {
                                            boolean bool = Boolean.valueOf(object[1].toString());
                                            if (bool) {
                                                JOptionPane.showMessageDialog(this, "request is approved!!!!");
                                                setConfirmer(c.getCustomerID());
                                                confirm(c);
                                            } else {
                                                if (JOptionPane.showConfirmDialog(this, "request is rejected, click YES to send it again or NO to exit", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                                    new StaticSentence(app.getSession(), "INSERT INTO USERAUTHORISATION(ID,MEMID,RDATE,REQUESTEDBY,COUNTER,BILLREF) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING}), null).exec(new Object[]{UUID.randomUUID().toString(), c.getCustomerID(), new Date(), app.getAppUserView().getUser().getName(), app.getAppUserView().getUser().getRole(), c.billref});
                                                    JOptionPane.showMessageDialog(this, "Request is sent for approval");

                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Request is under process");
                                        }
                                    } else {
                                        new StaticSentence(app.getSession(), "INSERT INTO USERAUTHORISATION(ID,MEMID,RDATE,REQUESTEDBY,COUNTER,BILLREF) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING}), null).exec(new Object[]{UUID.randomUUID().toString(), c.getCustomerID(), new Date(), app.getAppUserView().getUser().getName(), app.getAppUserView().getUser().getRole(), c.billref});
                                        JOptionPane.showMessageDialog(this, "Request is sent for approval");
                                    }
                                }
                            //praveen:added to request authorisation for credit confirm------end

                            } else {
                                details = (Object[]) new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,C.ID,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?  UNION ALL  SELECT C.NAME,C.SEARCHKEY,C.ID,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                                        new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card,card});
                                if (details != null) {
                                    if (details[2].toString().equals(c.getCustomerID())) {
                                        setConfirmer(details[4].toString());
                                        confirm(c);
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card does not match!!!", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card Not Registered!!!", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "select facility for warehouse that assigned to user");
                    }
                }
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
            if (cr.getSerialConnection() != null) {
                cr.getSerialConnection().setIncommingString("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select one row");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final int row = jTable1.getSelectedRow();
        if (row >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to change to cash bill ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    Transaction t = new Transaction(app.getSession()) {

                        @Override
                        protected Object transact() throws BasicException {
                            CreditConfirmListnum1 c = list.get(row);
                            //warehouse changes - start
                            //initiator changes - start
                            BillInfo binfo = (BillInfo) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfo.class)).find(c.getBillref());
                            //warehouse changes - end
                            //warehouse changes - end
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//praveen:changed from name to role
                            binfo.setReceiptRef(rno);
                            String user = app.getAppUserView().getUser().getName();
                            BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                            binfo.setLines(bl.getBillLineList(binfo.getID()));
                            //String user=app.getAppUserView().getUser().getName();
                            Date d = new Date();

                            int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) {
                                new StaticSentence(app.getSession(), "UPDATE BILL SET PAID=FALSE WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getBillref()});

                            }
                            loadData();
                            return null;
                        }
                    };
                    t.execute();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //BillInfo binfo=dlSales.get
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                try {
                    CreditConfirmListnum1 c = list.get(row);
                    BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                    BillInfo binfo = bl.getBillInfo(c.getBillref());
                    printTicket("Printer.Ticket", binfo, binfo.getPlace());
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "debt");
                    l.add(p);
                    binfo.setPayments(l);
                    binfo.setReceiptRef(c.getID());
                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                } catch (BasicException ex) {
                    Logger.getLogger(DebtBillListnum1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
// TODO add your handling code here:
    if((evt.getKeyCode() == KeyEvent.VK_P) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
               if(R){
                    R=false;
                RRprnt();  
                R=true;
                }
          }
}//GEN-LAST:event_jButton3KeyPressed

private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jUpActionPerformed

private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jDownActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton m_jDown;
    private javax.swing.JButton m_jUp;
    // End of variables declaration//GEN-END:variables
}
