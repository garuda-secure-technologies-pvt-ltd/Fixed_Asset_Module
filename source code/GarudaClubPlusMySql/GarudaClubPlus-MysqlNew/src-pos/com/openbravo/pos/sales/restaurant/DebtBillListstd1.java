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

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;

import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.BankInfo;
import com.openbravo.pos.payment.ChequeDetails;
import com.openbravo.pos.payment.JPaymentSelectstd1;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillInfostd1;

import com.openbravo.pos.sales.BillLineInfo;
import com.openbravo.pos.sales.BillLineInfostd1;
import com.openbravo.pos.sales.BillListTableModel;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.sales.BillLogicstd1;
import com.openbravo.pos.sales.BillTaxesLogic;
import com.openbravo.pos.sales.BillTaxesLogicstd1;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.sales.JPanelTicketstd1;
import com.openbravo.pos.sales.JTicketsBag;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class DebtBillListstd1 extends javax.swing.JDialog {

    private BillListTableModel billtablemodel;
    private CreditConfirmationTableModelstd1 cmodel;
    private List<BillInfo> blist;
   
    private BillInfostd1 binfostd;
    private TaxesLogic taxeslogic;
    private BillLogic blogic;
    private DataLogicSales dlSales;
    private boolean resultok = false;
   private CustomerInfo customer;
    private CustomerInfoExt customerext;
    private AppView app;
    private List<CreditConfirmListstd> list;
    private boolean flag;
    private BillTaxesLogicstd1 taxeslogicstd;

    private ListKeyed taxcollection;
    private TicketParser m_TTP;
    private DataLogicSystem dlSystem;
    private CardReader cr;
    private String portNumber;
    private List<RoleInfo> rolelist;
     //praveen:confirmer changes---start
    private String confirmer;
    private DataLogicFacilities dlfac;
       private JPaymentSelectstd1 paymentdialogreceipt;
    private JPaymentSelectstd1 paymentdialogrefund;
     private String prcategory;
         protected TicketInfo m_oTicket;
    public String getConfirmer() {
        return confirmer;
     }
   private static String cardno=""; 

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }
    //praveen:confirmer changes---end

    /** Creates new form BillList */
    public DebtBillListstd1(java.awt.Frame parent, DataLogicSales dlSales, AppView app, boolean flag) {
        super(parent, true);        
        this.dlSales = dlSales;
        this.app = app;
        this.flag = flag;
    }

    public DebtBillListstd1(java.awt.Dialog parent, DataLogicSales dlSales, AppView app, boolean flag) {
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

    public static DebtBillListstd1 getDialog(Component parent, DataLogicSales dlSales, AppView app, boolean flag) {

        Window window = getWindow(parent);

        DebtBillListstd1 bill;

        if (window instanceof Frame) {
            bill = new DebtBillListstd1((Frame) window, dlSales, app, flag);
        } else {
            bill = new DebtBillListstd1((Dialog) window, dlSales, app, flag);
        }

        return bill;
    }
    
    public static class CreditConfirmListstd implements SerializableRead {

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

  
    
    
     private void confirmstd(final CreditConfirmListstd c) throws BasicException {
        try {
          //  Transaction t = new Transaction(app.getSession()) {

         ///       @Override
       //         protected Object transact() throws BasicException {
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
                        //shiv:initiator changes---start
                          
                        this.dlBill = (BillLogicstd1) app.getBean("com.openbravo.pos.sales.BillLogicstd1");
                        
                        c.getBillref();
                        BillInfostd1 binfo = (BillInfostd1) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,BILL.TAXTOTAL FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfostd1.class)).find(c.getBillref());
                           BillLogicstd1 bl = getDataLogicBillstd();
                           ///////////////////////////////////
                            binfo = bl.getBillInfostd(c.getBillref());
                            ///////////////////////////////////
                           binfo.setLines(bl.getBillLineListstd(binfo.getID()));
                        //shiv:initiator changes---start
                        //warehouse changes - end
                        //shiv:checking bill is paid or not
                        Object obj1 = new StaticSentence(app.getSession(), "SELECT RECEIPT FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
                        if (obj1 != null) {
                            JOptionPane.showMessageDialog(null, "Already billed.Press OK to reresh the list", "warning", JOptionPane.OK_OPTION);
                            loadstdData();
                        } else {
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//praveen:changed from name to role
                            binfo.setReceiptRef(rno);
                            
                             bl = getDataLogicBillstd();
                            binfo.setLines(bl.getBillLineListstd(binfo.getID()));
                            String user = app.getAppUserView().getUser().getName();
                            Date d = new Date();
                          int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) {
                                //shiv:confirmer changes---start
                                new StaticSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,DATENEW,RUSER,DESC_,CONFIRMER) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{rno, d, user, c.getID(),getConfirmer()});
                                new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), rno, "debt", c.getAmount(), user, d, c.getCustomerID()});
                                setConfirmer("");
                                //shiv:confirmer changes---end
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                          TicketInfo ticket=new TicketInfo();
                         
                                
                                
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                                loadstdData();
                            }
                        }
                    }
        //            return null;
         //       }
        //    };
       //     t.execute();
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
            list = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse in  (" + condition.toString() + ") group by d.id ORDER BY B.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmListstd.class)).list(params);
//            List<CreditConfirmList> list1 = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse not in  (" + condition.toString() + ") and W.COUNTER IN (select category from sequencedetail where username='"+role+"') group by d.id ORDER BY BILL.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//            if(list1!=null || list1.size()>0){
//                list.addAll(list1);
//            }
        } else {
            list = null;
        }
        //praveen:end
        if (list == null || list.size() <= 0) {
            list = new ArrayList<CreditConfirmListstd>();
        }
        cmodel = new CreditConfirmationTableModelstd1(list);
        jTable1.setModel(cmodel);
    }
    
    //shiv:created load std data
    private void loadstdData() throws BasicException {
        //shiv:start-------displaying per role 
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
            
                    list = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,D.WAITER,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID   join bill b on b.id=d.billref and  b.warehouse in  (" + condition.toString() + ")  group by d.id ORDER BY B.ID,D.DATENEW,D.WAITER", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmListstd.class)).list(params);
           // list = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse in  (" + condition.toString() + ") group by d.id ORDER BY B.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//            List<CreditConfirmList> list1 = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse not in  (" + condition.toString() + ") and W.COUNTER IN (select category from sequencedetail where username='"+role+"') group by d.id ORDER BY BILL.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//            if(list1!=null || list1.size()>0){
//                list.addAll(list1);
//            }
        } else {
            list = null;
        }
        //shiv:end
        if (list == null || list.size() <= 0) {
            list = new ArrayList<CreditConfirmListstd>();
        }
        cmodel = new CreditConfirmationTableModelstd1(list);
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
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jButton1.setText("Confirm");
        jButton2.setText("<html>Change to<br>Cash Bill</html>");
        jButton3.setText("Reprint");
         jButton3.setEnabled(true);
         jButton1.setEnabled(false);
        try {
            jButton2.setIcon(new ImageIcon(ImageIO.read(DebtBillListstd1.class.getResourceAsStream("/com/openbravo/images/cash.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(flag==false){
        loadstdData();
        }else
        {
            loadstdData();
        }
       startCardReader();
        List<TaxInfo> taxlist = dlSales.getTaxList().list();
        taxeslogicstd = new BillTaxesLogicstd1(taxlist);
        taxcollection = new ListKeyed<TaxInfo>(taxlist);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
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
        ////////////////////////////////////////////////////////////////shiv
         String cardReaderPortName = app.getProperties().getProperty("card.portnumber");
          String CardRead = app.getProperties().getProperty("ACScard.port");
		if(cardReaderPortName.isEmpty() && CardRead.isEmpty()  ){
                    jTextField1.requestFocus();
                }else{
                       jTextField1.setVisible(false);
                   }
         /////////////////////////////////////////////////////////////////
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

    public boolean markBillAsPaid(final BillInfostd1 bill) throws BasicException {
        boolean status = true;
        Object[] values = new Object[]{bill.getID(), bill.getReceiptRef()};
        Datas[] datas = new Datas[]{Datas.STRING, Datas.STRING};
        int cnt = new PreparedSentence(app.getSession(), "UPDATE BILL SET RECEIPT = ? WHERE ID = ? AND PAID=TRUE", new SerializerWriteBasicExt(datas, new int[]{1, 0})).exec(values);
        if (cnt != 0) {
            PreparedSentence l = new PreparedSentence(app.getSession(), "UPDATE BILLITEM SET ATTRIBUTES = ? WHERE ID = ?", SerializerWriteParams.INSTANCE);
            for (final BillLineInfostd1 line : bill.getLines()) {
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

    private void printTicketstd(String sresourcename, BillInfostd1 ticket, Object ticketext) {

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
             //   taxeslogicstd.calculateTaxes(ticket);
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
             //   script.put("taxes", taxcollection);
             //   script.put("taxeslogic", taxeslogic);
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
    
    
     

       private void printstdBill(String sresourcename, BillInfostd1 ticket, Object ticketext,List<PaymentInfo> pi,TicketInfo ticket1, List<BillLineInfostd1> binfost1) {

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
                  pi = ticket1.getPayments();
                for (int i = 0; i < pi.size(); i++) {
                    temp = pi.get(i).getName();
                    if (pi.get(i).getName().equals("debt")) {
                        debt1 = 1;
                        sign = "sign";
                    }
                }
            } catch (Exception e) {
                System.out.println("error");
            }
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();

            try {

             //   WaiterInfo w = LookupUtilityImpl.getInstance(null).getWaiterMap().get(ticket.getWaiter());
           //     waitername = w.getName();
 //shiv commented
   /*              Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ticket.getPlace());

                if (obj1 == null) {
                    table = "";
                } else {
                    table = obj1[0].toString();
                }*/
                //shiv commented end
                
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
          //      if (ticketext.equals("cerditconf")) {
                    crconf = true;
          //      }
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
              //  taxeslogicstd.calculateTaxes(ticket);
                
                 ticket.getLines();
                  ticket.getLine(0).getRate();
                  ticket.getLine(0).getamount();
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("taxes", taxcollection);
                script.put("taxeslogic", taxeslogicstd);
               script.put("ticket1", ticket);
                script.put("ticket",ticket);
                script.put("ticket13",ticket.printSubTotal());
                script.put("ticket12",ticket.printSubTotal());
                script.put("ticket13a",ticket.printTotal());
                script.put("ticket12a",ticket.printTotal());
        //    script.put("ticket",binfost1);
 //:shivcom//   script.put("place", table);
                script.put("flag", flag);
                script.put("flag1", flag1);
              //  script.put("waiter", waitername);
                script.put("date", receiptdate);
                script.put("sign", sign);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("crconf", crconf);
                script.put("displayName", name);
                script.put("balance", accountBalance);

                
                
                
                
                
                
                
                        
          
            script.put("taxes",taxcollection);
            script.put("taxeslogic",taxeslogicstd);
            script.put("ticket",ticket);
           // script.put("ticket",binfor);
            script.put("bt",ticket);
            script.put("ticketline",binfost1);
            script.put("pinfo",pi);
           // script.put("ticket1",c.getCustomer());
            script.put("binfo",ticket);
           //   script.put("balance",);
            
           m_TTP.printTicket(script.eval(sresource).toString());
                
         
               /*   ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("taxes", taxcollection);
            script.put("taxeslogic", taxeslogic);
            script.put("ticket",ticket1);
            script.put("bt", ticket);
           // script.put("ticketline",tl);
            script.put("pinfo",pi);
            script.put("ticket1",ticket1);
            script.put("binfo",ticket);
            script.put("balance", accountBalance);
          
          
            m_TTP.printTicket(script.eval(sresource).toString());*/
            // System.out.print(binfo.getID());
                
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
            jTextField1 = new javax.swing.JTextField();

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

            jTextField1.setCaretColor(new java.awt.Color(204, 204, 204));
            jTextField1.setSelectedTextColor(new java.awt.Color(204, 204, 204));
            jTextField1.setSelectionColor(new java.awt.Color(204, 204, 204));
            jTextField1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(26, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(52, 52, 52))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final int row = jTable1.getSelectedRow();
           if (row >= 0) {
            final CreditConfirmListstd c = list.get(row);
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
                            confirmstd(c);
                        } else {
              
                     String card = cr.getData().toString();
                  

           
                //akshatha:to read a card from card reader without port num
               String cardReaderPortName =app.getProperties().getProperty("card.portnumber");
               String CardRead = app.getProperties().getProperty("ACScard.port");
		if(cardReaderPortName.isEmpty() && CardRead.isEmpty()  ){
                     
                          if (cardno == null || cardno.length() <= 0) {
                                //JOptionPane.showMessageDialog(this, "Please swipe a card");
                                //SHIV:added to request authorisation for credit confirm----start
                                if (JOptionPane.showConfirmDialog(this, "If ur card is not issued, click YES to 'Send for Authorisation' or if issued swipe a valid card", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                    Object[] object = (Object[]) new StaticSentence(app.getSession(), "SELECT MEMID,STATUS_ FROM USERAUTHORISATION WHERE MEMID=? AND BILLREF=? ORDER BY RDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN})).find(new Object[]{c.getCustomerID(), c.billref});
                                    if (object != null && object[0] != null) {
                                        if (object[1] != null) {
                                            boolean bool = Boolean.valueOf(object[1].toString());
                                            if (bool) {
                                                JOptionPane.showMessageDialog(this, "request is approved!!!!");
                                                setConfirmer(c.getCustomerID());
                                                confirmstd(c);
                                                
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
                            //SHIV:added to request authorisation for credit confirm------end

                           } else {
                                details = (Object[]) new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,C.ID,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?  UNION ALL  SELECT C.NAME,C.SEARCHKEY,C.ID,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                                        new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{cardno,cardno});
                                if (details != null) {
                                    if (details[2].toString().equals(c.getCustomerID())) {
                                        setConfirmer(details[4].toString());
                                        confirmstd(c);
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card does not match!!!", JOptionPane.ERROR_MESSAGE);
                                         jTextField1.setText(null);
                                         cardno=jTextField1.getText();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card Not Registered!!!", JOptionPane.ERROR_MESSAGE);
                                }
                           }        
                    
                    
                    
                    
                    
                        } else {
                                  jTextField1.setVisible(false);
                                   
                          if (card == null || card.length() <= 0) {
                                //JOptionPane.showMessageDialog(this, "Please swipe a card");
                                //SHIV:added to request authorisation for credit confirm----start
                                if (JOptionPane.showConfirmDialog(this, "If ur card is not issued, click YES to 'Send for Authorisation' or if issued swipe a valid card", "invalid card", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                    Object[] object = (Object[]) new StaticSentence(app.getSession(), "SELECT MEMID,STATUS_ FROM USERAUTHORISATION WHERE MEMID=? AND BILLREF=? ORDER BY RDATE DESC", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.BOOLEAN})).find(new Object[]{c.getCustomerID(), c.billref});
                                    if (object != null && object[0] != null) {
                                        if (object[1] != null) {
                                            boolean bool = Boolean.valueOf(object[1].toString());
                                            if (bool) {
                                                JOptionPane.showMessageDialog(this, "request is approved!!!!");
                                                setConfirmer(c.getCustomerID());
                                                confirmstd(c);
                                                
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
                                        confirmstd(c);
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card does not match!!!", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Please swipe a valid card", "Card Not Registered!!!", JOptionPane.ERROR_MESSAGE);
                                }
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
       String ticketBag = app.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
         
        
        final int row = jTable1.getSelectedRow();
        if (row >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to change to cash bill ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                   Transaction t = new Transaction(app.getSession()) {

                        @Override
                       protected Object transact() throws BasicException {
                            CreditConfirmListstd c = list.get(row);
                            //warehouse changes - start
                            //initiator changes - start
                        //    BillInfo binfo = (BillInfo) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfo.class)).find(c.getBillref());
                           ////////////////////////////////////////////////////////////////////////
                            BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                             //    binfo = bl.getBillInfo(c.getBillref());
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
                            //warehouse changes - end
                            //warehouse changes - end
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//sp:changed from name to role
                           
                        //    binfo.setReceiptRef(rno);
                            
       //////////////////////////////////////////////////////////////////////////////////////////////////////////                     
                            String user = app.getAppUserView().getUser().getName();
              //              BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                        //    binfo.setLines(bl.getBillLineList(binfo.getID()));
                            //String user=app.getAppUserView().getUser().getName();
                            Date d = new Date();

                            int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) {
                                new StaticSentence(app.getSession(), "UPDATE BILL SET PAID=FALSE WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getBillref()});

                             }
           
                          TicketInfo ticket=new TicketInfo();
              //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                         
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
        }
        else if (ticketBag.equals("standard")) {
           
            final int row = jTable1.getSelectedRow();
          if (row >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to change to cash bill ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
               //    Transaction t = new Transaction(app.getSession()) {

                     //  @Override
                //       protected Object transact() throws BasicException {
                            CreditConfirmListstd c = list.get(row);
                            //warehouse changes - start
                            //initiator changes - start
                            BillInfostd1 binfo = (BillInfostd1) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfostd1.class)).find(c.getBillref());
                           ////////////////////////////////////////////////////////////////////////
                            this.dlBill = (BillLogicstd1) app.getBean("com.openbravo.pos.sales.BillLogicstd1");
                            BillLogicstd1 bl = getDataLogicBillstd();
                            binfo = bl.getBillInfostd(c.getBillref());
                    ////////////////////////////////////////////////////////////////////////////////             
                            //warehouse changes - end
                            //warehouse changes - end
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//sp:changed from name to role
                           
                            binfo.setReceiptRef(rno);
                            
            //////////////////////////////////////////////////////////////////////////////////////////////////////////                     
                            String user = app.getAppUserView().getUser().getName();
              //              BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                            binfo.setLines(bl.getBillLineListstd(binfo.getID()));
                            //String user=app.getAppUserView().getUser().getName();
                            Date d = new Date();
                            
       //shiv: 07aug////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            int flag = checkForBillDiscount(binfo.getID());
      /*    if (flag == 0) {
            //  saveBill();
            resultok = true;
            // if (resultok) {
            try {
              
                closeTicket(binfo, binfo.getPlace());
                dispose();
            } catch (BasicException e) {
                e.printStackTrace();
                new MessageInf(e).show(this);
            }
        //  }
        }*/
       ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            
                       /*     

                            int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) {
                                new StaticSentence(app.getSession(), "UPDATE BILL SET PAID=FALSE WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getBillref()});

                            }
                            */
           
                            
      //shiv:07sep////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            
                             
                            binfo.setReceiptRef(rno);
                             bl =getDataLogicBillstd();
                            binfo.setLines(bl.getBillLineListstd(binfo.getID()));
                            //String user = app.getAppUserView().getUser().getName();
                           // Date d = new Date();
                            int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) {
                                //shiv:confirmer changes---start
                            
                                new StaticSentence(app.getSession(), "UPDATE BILL SET PAID=FALSE WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getBillref()});
                                new StaticSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,DATENEW,RUSER,DESC_,CONFIRMER) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{rno, d, user, c.getID(),getConfirmer()});
                                new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), rno, "cash", c.getAmount(), user, d, c.getCustomerID()});
                                new StaticSentence(app.getSession(), "UPDATE BILL SET RECEIPT=(SELECT  ID FROM RECEIPTS WHERE ID=?) WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{rno,c.getBillref()});
                                setConfirmer("");
                                
                            }
                                //shiv:confirmer changes---end
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            
                          loadstdData();
     //SHIV:11-sept2012///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                         
                        //   TicketLineInfo tl =new TicketLineInfo();
                          TicketInfo t=new TicketInfo();
                    // BillLineInfostd1 tl = (BillLineInfostd1) binfo.getLinesstd();
              ///             List<BillLineInfostd1> binfo1=new ArrayList<BillLineInfostd1>();
              /////             binfo.setLines(bl.getBillLineListstd(binfo.getID()));
                                  //  binfo.setLines(binfo1);
               ///                     binfo1=binfo.getLines();
                                   //  taxeslogicstd.calculateTaxes(binfo);
                                                              
                     //BillLineInfostd1 tl = (BillLineInfostd1) binfo.getLinesstd();
                     // t.getCustomerId();
                    // binfo.getCustomer();
                    
                                
                       //   TicketLineInfo t1=new TicketLineInfo();
                      //  List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                     //   PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "cash");
                      //  l.add(p);
                     //  binfo.setPayments(l);
                       
                        //  dlSales.loadTicket(count);
                    binfo = bl.getBillInfostd(c.getBillref());
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "cash");
                    l.add(p);
                    binfo.setPayments(l);
                    binfo.setReceiptRef(c.getID());
                    binfo.getPayments();
                     List<BillLineInfostd1> binfo1=new ArrayList<BillLineInfostd1>();
                           binfo.setLines(bl.getBillLineListstd(binfo.getID()));
                                  //  binfo.setLines(binfo1);
                                    binfo1=binfo.getLines();
                         String customername=  c.getCustomer();
                         String searchkey =  c.getSearchkey();
                    
        
                
            String sresource1 = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.standardCashbill");
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("taxes",taxcollection);
            script.put("taxeslogic",taxeslogicstd);
            script.put("ticket",binfo);
            script.put("c", customername);
            script.put("c1",searchkey);
            script.put("RECEIPT",rno);
           // script.put("ticket",binfor);
            script.put("bt",binfo);
            script.put("ticketline",binfo1);
            script.put("pinfo",p);
           // script.put("ticket1",c.getCustomer());
            script.put("binfo",binfo);
      //   script.put("balance",);
          
          
            m_TTP.printTicket(script.eval(sresource1).toString());
                 
                
           
          
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
                          //  return null;
                   //  }
                  //  };
                  //  t.execute();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private boolean closeTicket(BillInfostd1 ticket, Object ticketext) throws BasicException {

    
        boolean resultok = false;
        if (!ticket.isPaid()) {

            //jBtnPay.setEnabled(false);
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();



            if (m_App.getAppUserView().getUser().hasPermission("sales.Total")) {

                // reset the payment info
           //shiv :commented     taxeslogic.calculateTaxes(ticket);
                ticket.resetPayments();

                // Muestro el total
     //           printTicket("Printer.TicketTotal", ticket, ticketext);
                
                // Select the Payments information
                JPaymentSelectstd1 paymentdialog = ticket.getTotal() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected(true); //Print always
                boolean flag = true;
                  String type=null;
                  double amt = 0;
                   paymentdialog.showDialog(amt,customerext,prcategory,flag,type,null);
               //  paymentdialog.showDialog(amt,customer, prcategory, true,type); 

                    // assign the payments selected and calculate taxes.
                    ticket.setPayments(paymentdialog.getSelectedPayments());

                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    try {
                        flag = payBillstd(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }

                    // Print receipt.//shiv commented
                 /*   if (flag == true) {
                        
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket_1"
                                : "Printer.Ticket2", ticket, ticketext);
                        resultok = true;
                    } else {
                        resultok = false;
                    }
                }*/
                  resultok = true;
                // reset the payment info
                ticket.resetTaxes();
                ticket.resetPayments();
            

        // cancelled the ticket.total script
        // or canceled the payment dialog
        // or canceled the ticket.close script

        }
       

    }
         return resultok;
    }
    
     private int checkForBillDiscount(String billid) {
        int flag1 = 0, tempint = 0;
        String temp;
        try {

            String bid = billid;
            AppView app = LookupUtilityImpl.getInstance(null).getAppView();
            Object obj[] = (Object[]) new StaticSentence(app.getSession(), "SELECT COUNT(BILLID) FROM REVERSEDBILL WHERE BILLID = ? AND AUTHORISED IS NULL GROUP BY BILLID ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(bid);
            //temp
            if (obj != null) {
                temp = obj[0].toString();
                tempint = Integer.parseInt(temp);
                if (tempint > 0) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("Discount Requst under processing"), AppLocal.getIntString("Cannot Process"), JOptionPane.WARNING_MESSAGE);
                    flag1 = 1;
                // break;
                }
            }

        } catch (BasicException e) {
            flag1 = 1;
            e.printStackTrace();

        }
        return flag1;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //BillInfo binfo=dlSales.get
          String ticketBag = app.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                try {
                   CreditConfirmListstd c = list.get(row);
                   BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                   BillInfo binfo = bl.getBillInfo(c.getBillref());
          //         printTicket("Printer.Ticket", binfo, binfo.getPlace());
                  List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                  PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "debt");
                  l.add(p);
                   binfo.setPayments(l);
                   binfo.setReceiptRef(c.getID());
            //       printTicket("Printer.Ticket_1", binfo, "cerditconf");
                } catch (BasicException ex) {
                    Logger.getLogger(DebtBillListstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }    else if (ticketBag.equals("standard")) {
             int row = jTable1.getSelectedRow();
        if (row >= 0) {
            int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                try {
                    CreditConfirmListstd c = list.get(row);
                       this.dlBill = (BillLogicstd1) app.getBean("com.openbravo.pos.sales.BillLogicstd1");
                    BillLogicstd1 bl = getDataLogicBillstd();
                    BillInfostd1 binfo1 = bl.getBillInfostd(c.getBillref());
                   
  ////shiv :12thsept///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    
                    
                     binfo1 = bl.getBillInfostd(c.getBillref());
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo1.getTotal(), "debt");
                    l.add(p);
                    binfo1.setPayments(l);
                    binfo1.setReceiptRef(c.getID());
                    binfo1.getPayments();
                     List<BillLineInfostd1> binfost1=new ArrayList<BillLineInfostd1>();
                           binfo1.setLines(bl.getBillLineListstd(binfo1.getID()));
                                  //  binfo.setLines(binfo1);
                                    binfost1=binfo1.getLines();
        
           
             
         ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             //   List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                 //   PaymentInfo p = new PaymentInfoTicket(binfo1.getTotal(), "debt");
                   // l.add(p);
               //     binfo1.setPayments(l);
                 //   binfo1.setReceiptRef(c.getID());
                    TicketInfo t=new TicketInfo();
          printstdBill("Printer.Reprintstdbill", binfo1, "cerditconf",l,t,binfost1);        
          printstdBill("Printer.Reprintcrconf_std", binfo1, "cerditconf",l,t,binfost1);
           
                } catch (BasicException ex) {
                    Logger.getLogger(DebtBillListstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
     cardno=jTextField1.getText();         
   
}//GEN-LAST:event_jTextField1ActionPerformed
    
 
 /*public String getIncommingString() {
      
        byte[] bVal = cardno.getBytes();
         cardno  = "";
        return new String(bVal);
    }

    public void setIncommingString(String value) {
        cardno = value;

    }*/
    
 /*   public void stdBillPrint(){
         int row = jTable1.getSelectedRow();
          if (row >= 0) {
            CreditConfirmListstd c = list.get(row);
                            //warehouse changes - start
                            //initiator changes - start
       BillInfostd1 binfo = (BillInfostd1) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfostd1.class)).find(c.getBillref());
                           ////////////////////////////////////////////////////////////////////////
         BillLogicstd1 bl = LookupUtilityImpl.getInstance(null).getDataLogicBillstd();
                          binfo = bl.getBillInfostd(c.getBillref());
                    ////////////////////////////////////////////////////////////////////////////////             
                            //warehouse changes - end
                            //warehouse changes - end
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//sp:changed from name to role
                           
                            binfo.setReceiptRef(rno);
          }
    }*/
    
    
    
    /**
     * @param args the command line arguments
     */


////////////////shiv://////////////////////////////////////////////////////////////////////////////////
private BillLogicstd1 dlBill; 
 private int flag1 = 0;
      private boolean perror;
      private boolean berror;
             protected Session s;

public BillLogicstd1 getDataLogicBillstd() {
                     return dlBill;
                                }
  public final SentenceList getBankList() {
        return new StaticSentence(s, "SELECT ID, BANKNAME FROM BANK ORDER BY BANKNAME", null, new SerializerReadClass(BankInfo.class));
    }

   public final String getNextReceiptID1(String createdby) throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String receiptnum;
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();

        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = (SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ID=? AND ROLES.ID=PEOPLE.ROLE)  AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});

            return receiptnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
            return null;
        }
    }
    


 public final boolean payBillstd(final BillInfostd1 ticket, final String location) throws Exception {
        flag1 = 0;
        berror = false;
        Transaction t = new Transaction(s) {

            @Override
            public Object transact() throws BasicException {
                Date date1 = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(date1.getTime());
                cal.add(Calendar.MINUTE, 10);
                Calendar bcal = Calendar.getInstance();
                bcal.setTimeInMillis(ticket.getCreatedDate().getTime());
                if (bcal.after(cal)) {
                    JOptionPane.showMessageDialog(null, "Present Time is less than billed time", "Error-Cannot Create Receipt", JOptionPane.OK_OPTION);
                    berror = true;
                }
                final Date date = new Date();
                AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                if (berror == false) {
                    Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                JOptionPane.showMessageDialog(null, "Present Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                                berror = true;

                            }
                        }
                    }
                }
                if (berror == false) {
                    if (ticket.getReceiptRef() == null) {
                        String rno = getNextReceiptID1(ticket.getCreatedBy());
                        ticket.setReceiptRef(rno);
                        if (rno.equals("")) {
                            flag1 = 1;
                            return false;
                        }
                    }
                    // new receipt
                    new PreparedSentence(s, "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                        @Override
                        public void writeValues() throws BasicException {
                            setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                            // setString(2, ticket.getActiveCash());
                            setTimestamp(2, date); //Receipt date could be different from bill date
                            setString(3, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                        }
                    });

                    // new ticket
                    ticket.setPaid(true);
                    boolean berror1 = getDataLogicBillstd().markBillAsPaid(ticket);
                    if (berror1 == false) {
                        berror = true;
                        throw new BasicException();
                    } else {
                        SentenceExec paymentinsert = new PreparedSentence(s, "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        for (final PaymentInfo p : ticket.getPayments()) {
                            paymentinsert.exec(new DataParams() {

                                @Override
                                public void writeValues() throws BasicException {
                                    setString(1, UUID.randomUUID().toString());
                                    setString(2, ticket.getReceiptRef());
                                    setString(3, p.getName());
                                    setDouble(4, p.getTotal());
                                    setString(5, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());
                                    setTimestamp(6, date);
                                    setString(7, ticket.getCustomerId());
                                }
                            });

                            /*   if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                            getDebtUpdate().exec(new Object[]{
                            ticket.getCustomer().getId(),
                            new Double(p.getTotal()),
                            date
                            });
                            }*/
                            /*  try{
                            if("debt".equals(p.getName())){
                            String str=  String.valueOf(new PreparedSentence(s
                            , "SELECT MOBILE FROM CUSTOMERS WHERE ID=?"
                            , SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(ticket.getCustomerId()));
                            String msg="Dear Member,\rYour a/c with us has been debited by "+Formats.CURRENCY.formatValue(p.getTotal())+" for bar usage.Bill no "+ticket.getID();
                            if(str!=null && str.length()==10)
                            updatetosendMsg(msg, ticket.getCustomerId(), str, 2);
                            }
                            }catch(Exception e){
                            }*/
                            if ("cheque".equals(p.getName())) {
                                SentenceExec chequeInsert = new PreparedSentence(s, "INSERT INTO CHEQUE(ID, CHEQUENO, BANK, RNO,HOLDER,AMOUNT) VALUES (?, ?, ?, ?,?,?)", SerializerWriteParams.INSTANCE);
                                final ChequeDetails cd = p.getChequeDetails();
                                chequeInsert.exec(new DataParams() {

                                    @Override
                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getChequeno());
                                        setString(3, cd.getBank());
                                        setString(4, ticket.getReceiptRef());
                                        setString(5, cd.getholder());
                                        setDouble(6, cd.getAmount());
                                    }
                                });
                                List<BankInfo> temp = getBankList().list();
                                boolean result = false;
                                for (BankInfo b : temp) {
                                    if ((cd.getBank().equals(b.getName()))) {
                                        result = true;
                                        break;
                                    }
                                }
                                if (!result) {
                                    SentenceExec bankInsert = new PreparedSentence(s, "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                    bankInsert.exec(new DataParams() {

                                        public void writeValues() throws BasicException {
                                            setString(1, UUID.randomUUID().toString());
                                            setString(2, cd.getBank());
                                        }
                                    });
                                }
                            }
                        }
                        //removed and moved to bill
                    /*    SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                        if (ticket.getTaxes() != null) {
                            for (final TicketTaxInfo tickettax : ticket.getTaxes()) {
                                taxlinesinsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, ticket.getReceiptRef());
                                        setString(3, tickettax.getTaxInfo().getId());
                                        setDouble(4, tickettax.getSubTotal());
                                        setDouble(5, tickettax.getTax());
                                    }
                                });
                            }
                        }*/
                    }
                }
                return null;
            //}
            }
        };
        t.execute();
        if (flag1 == 0 && berror == false) {
            return true;
        } else {
            return false;
        }
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
