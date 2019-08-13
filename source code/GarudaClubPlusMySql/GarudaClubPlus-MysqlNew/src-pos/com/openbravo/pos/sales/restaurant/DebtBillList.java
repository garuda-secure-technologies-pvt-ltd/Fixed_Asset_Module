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
import com.openbravo.pos.clubmang.DebtTypeTableModel;
import com.openbravo.pos.clubmang.FacilityLogic;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
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
import com.openbravo.pos.sales.CreditConfirm_UsbCard;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.sms.SMSgeneralDBSettings;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.text.DecimalFormat;
import java.util.Calendar;
/**
 *
 * @author swathi
 */
public class DebtBillList extends javax.swing.JDialog {

    private BillListTableModel billtablemodel;
    private CreditConfirmationTableModel cmodel;
     private PendingCreditConfirmationTotalModel PendingCmodel;
    
    private List<BillInfo> blist;
    private BillLogic blogic;
    private DataLogicSales dlSales;
    private boolean resultok = false;
    private FacilityLogic flogic;
    private CustomerInfo customer;
    private AppView app;
    private List<CreditConfirmList> list;
    private SMSgeneralDBSettings smsDBSettings;
    private DataLogicCustomers dlCustomers;
    private List<PendingCreditConfirmListT> Pendinglist;
    
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
    private String FacilityID;
    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }
    //praveen:confirmer changes---end

    /** Creates new form BillList */
    public DebtBillList(java.awt.Frame parent, DataLogicSales dlSales, AppView app, boolean flag) {
        super(parent, true);        
        this.dlSales = dlSales;
        this.app = app;
        this.flag = flag;
    }

    public DebtBillList(java.awt.Dialog parent, DataLogicSales dlSales, AppView app, boolean flag) {
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

    public static DebtBillList getDialog(Component parent, DataLogicSales dlSales, AppView app, boolean flag) {

        Window window = getWindow(parent);

        DebtBillList bill;

        if (window instanceof Frame) {
            bill = new DebtBillList((Frame) window, dlSales, app, flag);
        } else {
            bill = new DebtBillList((Dialog) window, dlSales, app, flag);
        }

        return bill;
    }
    
    public static class CreditConfirmList implements SerializableRead {

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

    private void confirm(final CreditConfirmList c) throws BasicException {
        try {
            Transaction t = new Transaction(app.getSession()) 
            {

                @Override
                protected Object transact() throws BasicException
                {
                    //CreditConfirmList c = list.get(row);
                    String s = c.getCustomerID();
                    String s1 = c.getID();
                    boolean flag = true;
                    Object[] obj = (Object[]) new PreparedSentence(app.getSession(), "SELECT C.MOBILE,M.NAME FROM CUSTOMERS C JOIN MEMTYPE M ON C.MEMTYPE=M.ID WHERE C.ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(s);
                    String str = null;
                    String memtype = null;
                    if (obj != null) 
                    {
                        if (obj[0] != null) 
                        {
                            str = String.valueOf(obj[0]);
                        }
                        if (obj[1] != null) 
                        {
                            memtype = String.valueOf(obj[0]);
                        } 
                        else 
                        {
                            flag = false;
                        }
                    } 
                    else
                    {
                        flag = false;
                    }
                    if (flag && memtype.equals("Affiliated Member"))
                    {
                        flag = false;
                    }
                    if (flag) 
                    {
                        //warehouse changes - start
                        //praveen:initiator changes---start
                        BillInfo binfo = (BillInfo) new StaticSentence(app.getSession(), "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME, BILL.AMOUNT, BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,BILL.TAXTOTAL FROM BILL,FLOORS WHERE  BILL.ID=? AND BILL.FLOOR=FLOORS.ID AND BILL.PAID=TRUE ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfo.class)).find(c.getBillref());
                        //praveen:initiator changes---start
                        //warehouse changes - end
                        //praveen:checking bill is paid or not
                        Object obj1 = new StaticSentence(app.getSession(), "SELECT RECEIPT FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
                        if (obj1 != null) 
                        {
                            JOptionPane.showMessageDialog(null, "Already billed.Press OK to reresh the list", "warning", JOptionPane.OK_OPTION);
                            loadData();
                        } 
                        else 
                        {
                            String rno = dlSales.getNextReceiptID(app.getAppUserView().getUser().getRole());//praveen:changed from name to role
                            binfo.setReceiptRef(rno);
                            BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                            binfo.setLines(bl.getBillLineList(binfo.getID()));
                            String user = app.getAppUserView().getUser().getName();
                            Date d = new Date();
                           
                            
                            int count = new StaticSentence(app.getSession(), "DELETE FROM CREDITCONFLIST WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{c.getID()});
                            if (count > 0) 
                            {
                                //praveen:confirmer changes---start
                                 //added by pratima: query to insert data into creditconflist_arv
                                 new StaticSentence(app.getSession(), "INSERT INTO CREDITCONFLIST_ARV (ID,datenew,ruser,customer,billref,waiter,amount) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,Datas.DOUBLE})).exec(new Object[]{c.getID(),c.getDate(),c.getRuser(),c.getCustomerID(),c.getBillref(),c.getWaiter(),c.getAmount()});
                                 //ended by pratima
                                new StaticSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,DATENEW,RUSER,DESC_,CONFIRMER) VALUES (?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{rno, d, user, c.getID(),getConfirmer()});
                                new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), rno, "debt", c.getAmount(), user, d, c.getCustomerID()});
                                setConfirmer("");
                                //praveen:confirmer changes---end
                                boolean status = markBillAsPaid(binfo);
                                if (status == false) 
                                {
                                    throw new BasicException();
                                }
                                try {
                                    // if("debt".equals(p.getName())){
                                                                      
                                    Object facility = new StaticSentence(app.getSession(), " SELECT SMSFORM FROM facility F WHERE F.ID=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FacilityID);
                                    String SMSFac = "";
                                    if(facility!=null)
                                    {
                                        SMSFac = facility.toString().trim();
                                    }
                                    //by pratima:query to get net balance to add in msg 
                                    Object[] objBalance1=(Object[])new StaticSentence(app.getSession(), "SELECT ((OBDEBIT+CURDEBIT)-(OBCREDIT+CURCREDIT)) FROM trailbalance where ACCOUNTID=(SELECT ACCOUNT FROM CUSTOMERS WHERE ID=?)", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(c.getCustomerID());
                                    Object[] objBalance2=(Object[])new StaticSentence(app.getSession(), "select sum(total) from payments where receipt in (select id from receipts where id in(select receipt from bill where id in(select billref from creditconflist_arv where customer=? and receipt is not null)) and closecashseq is null) " , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(c.getCustomerID());
                                    
                                     
                                    Double netBalance=Double.parseDouble(objBalance1[0].toString())+Double.parseDouble(objBalance2[0].toString());
                                 
                                    System.out.println("unformated netBalance is"+netBalance);
                                    DecimalFormat df = new DecimalFormat("#.00"); 
                                    netBalance=Double.parseDouble(df.format(netBalance));
                                    /*String msg=null;
                                    if (netBalance>0)
                                    {
                                        msg = "Dear Member,\rYour a/c "+c.getSearchkey() +" with us has been debited by " + Formats.CURRENCY.formatValue(c.getAmount()) + " for "+SMSFac+" usage.Bill no. " + c.getBillref() + " On " + Formats.DATE.formatValue(c.getDate())+".Net Balance is Dr. Rs."+netBalance;

                                    }
                                    if (netBalance<0)
                                    {    
                                        netBalance=(netBalance*(-1));
                                        msg = "Dear Member,\rYour a/c "+c.getSearchkey() +" with us has been debited by " + Formats.CURRENCY.formatValue(c.getAmount()) + " for "+SMSFac+" usage.Bill no. " + c.getBillref() + " On " + Formats.DATE.formatValue(c.getDate())+".Net Balance is Cr. Rs."+netBalance;

                                    }
                                    //ended by pratima 
                                   //  String msg = "Dear Member,\rYour a/c "+c.getSearchkey() +" with us has been debited by " + Formats.CURRENCY.formatValue(c.getAmount()) + " for "+SMSFac+" usage.Bill no. " + c.getBillref() + " On " + Formats.DATE.formatValue(c.getDate());
                                     System.out.println(msg.length());
                                     if (str != null && str.length() == 10) {
                                         dlSales.updatetosendMsg(msg, c.getCustomerID(), str, 2);
                                     }
                                      } */
                                   
                                    // check for SMS master
                                    checkForSMS(c, netBalance, SMSFac);
                                   
                                   
                                }
                                catch (Exception e) 
                                {
                                    e.printStackTrace();
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
    
    
    
    
    public void checkForSMS(CreditConfirmList c, Double netBalance, String facilitySMSName)
    {
        boolean sendSMSforActDebit =  smsDBSettings.getSMSvalue(SMSgeneralDBSettings.SMS_CREDIT_CONF_ID);
        if(sendSMSforActDebit)
        {
            createSMS(c, SMSgeneralDBSettings.SMS_CREDIT_CONF_ID, netBalance, facilitySMSName);
        }
    }
    
    
    public void createSMS(CreditConfirmList c, String smsMessage_Id, Double netBalance, String facilitySMSName)
    {
        String smsString = smsDBSettings.getMessage(smsMessage_Id);
        if(smsString != null)
        {
            CustomerInfoExt customerInfoExt = null;
            try
            {   // get customer details
                customerInfoExt = dlCustomers.getCustomerByID(c.getCustomerID());
            } 
            catch (BasicException ex) 
            {
                Logger.getLogger(DebtBillList.class.getName()).log(Level.SEVERE, null, ex);
            }
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_BILL_KEY, c.getBillref());
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_DTM_KEY , Formats.TIMESTAMP.formatValue(new Date()));
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_TOT_AMOUNT_KEY , Formats.CURRENCY.formatValue(c.getAmount()));
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_FACILITY_KEY , facilitySMSName);
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_WHAREHOUSE_NAME_KEY , facilitySMSName);
             
            String x = app.getAppUserView().getUser().getRole();
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_ROLE_KEY ,  LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            
            if(c.getCustomer() != null)
            {
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NAME_KEY, c.getCustomer()); 
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NO_KEY, c.getSearchkey()); 
            }
            if(smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE) || smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER))
            {
               smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE, getFormatedNetBalance(netBalance));
               smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER, getFormatedNetBalance(netBalance));
            }
            if(smsString.contains(SMSgeneralDBSettings.SMS_DUE_DATE_KEY))
            {
                DebtTypeTableModel.DebtTypeline dueperiod;
                try 
                {
                    Date duedate = new Date();
                    
                    Calendar caltemp = Calendar.getInstance();
                    caltemp.setTimeInMillis((new Date()).getTime());
                    
                    dueperiod = dlfac.getDebtTypebyid(smsDBSettings.getFacDueDate(FacilityID));
                    duedate.setTime(flogic.getDueDate(dueperiod, caltemp.getTime()).getTime());
                    smsString = smsString.replace(SMSgeneralDBSettings.SMS_DUE_DATE_KEY, Formats.DATE.formatValue(duedate));
                }
                catch (BasicException ex)
                {
                    Logger.getLogger(DebtBillList.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
                    
            }
            if(customerInfoExt != null && customerInfoExt.getmobile() != null && customerInfoExt.getmobile().trim().length() > 0)
            {
                smsDBSettings.insertSMStoActiveMsgTable(smsString, customerInfoExt.getmobile(), customerInfoExt.getId());
            }
        }
    }
    
    public String getFormatedNetBalance(Double netBalance)
    {
        if(netBalance >= 0)
        {
            return Formats.CURRENCY.formatValue(netBalance) + " Dr.";
        }
        else 
        {
            netBalance=(netBalance*(-1));  
            return Formats.CURRENCY.formatValue(netBalance) + " Cr.";
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
         list = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse in  (" + condition.toString() + ") group by d.id ORDER BY B.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//            List<CreditConfirmList> list1 = new StaticSentence(app.getSession(), "SELECT D.DATENEW,C.NAME,C.SEARCHKEY,W.NAME,D.BILLREF,D.ID,D.RUSER,D.amount,c.id FROM CREDITCONFLIST D JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref and b.warehouse not in  (" + condition.toString() + ") and W.COUNTER IN (select category from sequencedetail where username='"+role+"') group by d.id ORDER BY BILL.ID,D.DATENEW,W.NAME", new SerializerWriteBasic(data), new SerializerReadClass(CreditConfirmList.class)).list(params);
//            if(list1!=null || list1.size()>0){
//                list.addAll(list1);
//            }
        } else {
            list = null;
        }
        //praveen:end
        if (list == null || list.size() <= 0) {
            list = new ArrayList<CreditConfirmList>();
        }
        cmodel = new CreditConfirmationTableModel(list);
        jTable1.setModel(cmodel);
        
        
  // EDITED BY AAKASH FOR DISPLAYING PENDING
        
         if (warehouses != null) {
            Object[] params1 = new Object[warehouses.length];
            Datas[] data1 = new Datas[warehouses.length];
            StringBuffer condition1 = new StringBuffer("");
            for (int j = 0; j < warehouses.length; j++) {
                data1[j] = Datas.STRING;
                params1[j] = warehouses[j].toString();
                condition1.append(" ? , ");
            }
            if (condition1.length() > 0) {
                condition1.deleteCharAt(condition1.lastIndexOf(","));
            }
        Pendinglist = new StaticSentence(app.getSession(), "SELECT SUM(D.amount) , l.name FROM CREDITCONFLIST D\n" +
                                                "JOIN CUSTOMERS C ON D.CUSTOMER=C.ID JOIN WAITER W ON D.WAITER=W.ID join bill b on b.id=d.billref \n" +
                                                "and b.warehouse in  (" + condition1.toString() + ")\n" +
                                                "join Locations l on l.id=b.warehouse  group by l.name ", new SerializerWriteBasic(data1), new SerializerReadClass(PendingCreditConfirmListT.class)).list(params1);

        } else 
        {
            Pendinglist = null;
        }
        
        if (Pendinglist == null || Pendinglist.size() <= 0) {
            Pendinglist = new ArrayList<PendingCreditConfirmListT>();
        }
        PendingCmodel = new PendingCreditConfirmationTotalModel(Pendinglist);
        jTable2.setModel(PendingCmodel);
        
        
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
        smsDBSettings = (SMSgeneralDBSettings) app.getBean("com.openbravo.pos.sms.SMSgeneralDBSettings");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        jButton1.setText("Confirm");
        jButton2.setText("<html>Change to<br>Cash Bill</html>");
        jButton3.setText("Reprint");
        try {
            jButton2.setIcon(new ImageIcon(ImageIO.read(DebtBillList.class.getResourceAsStream("/com/openbravo/images/cash.png"))));
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
        flogic = new FacilityLogic(dlfac);
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
                
                Double bal =d - d1;
                Double bal1 =d - d1+ticket.getTotal1();
                String accountBalance = null;
                String accountBalance1 = null;
                if (bal > 0) {
                    bal = dlfac.roundTwoDecimals(bal);
                    //bal1 = dlfac.roundTwoDecimals(bal1);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                    //accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
                } else {
                    bal = bal * -1;
                    //bal1 = bal1 * -1;
                    bal = dlfac.roundTwoDecimals(bal);
                    //bal1 = dlfac.roundTwoDecimals(bal1);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                    //accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
                }
                /////Added by guru
                 if (bal1 > 0) {
                   // bal = dlfac.roundTwoDecimals(bal);
                    bal1 = dlfac.roundTwoDecimals(bal1);
                    //accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                    accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
                } else {
                   // bal = bal * -1;
                    bal1 = bal1 * -1;
                   // bal = dlfac.roundTwoDecimals(bal);
                    bal1 = dlfac.roundTwoDecimals(bal1);
                   // accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                    accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
                }
                /////
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
                script.put("balance1", accountBalance1);

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
            jScrollPane2 = new javax.swing.JScrollPane();
            jTable2 = new javax.swing.JTable(){
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
                jTable1.getTableHeader().setReorderingAllowed(false);
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

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                    },
                    new String [] {
                        "No", "Warehouse", "AMOUNT"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jScrollPane2.setViewportView(jTable2);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(25, Short.MAX_VALUE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        final int row = jTable1.getSelectedRow();
        if (row >= 0) {
            final CreditConfirmList c = list.get(row);
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
                    //Object obj1 = new StaticSentence(app.getSession(), "SELECT FACILITY FROM LOCATIONS WHERE ID IN (" + condition.toString() + ")", new SerializerWriteBasic(data), SerializerReadString.INSTANCE).find(params);
                    Object obj1 = new StaticSentence(app.getSession(), "SELECT P.ID FROM billitem B , PRODUCTS P where B.parentid= ?  AND B.PRODUCT = P.ID AND B.LINE=0", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(c.getBillref());
                    Object obj2 = null;
                    if(obj1!=null){
                    obj2 = new StaticSentence(app.getSession(), "select L.FACILITY  from  LOCATIONS L  ,  PRODUCTS P where P.id=?  AND P.LOCATION=L.ID", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(obj1.toString());   
                    }
                    if (obj2 != null) {
                        FacilityID = obj2.toString();
                        id = obj2.toString();
                        Object[] fac = (Object[]) new StaticSentence(app.getSession(), "SELECT F.CONFIRMCONTROL FROM FACILITY F WHERE  F.ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(id);
                        if ((Boolean) fac[0] == false) {
                            confirm(c);
                        } else {
                            
                            String card = cr.getData().toString();
                            
                            if (card == null || card.length() <= 0){
                               
                               
                                CreditConfirm_UsbCard dbillList = CreditConfirm_UsbCard.getDialog(this, app,true);
                                dbillList.showDialog();
                                
                                card  =  dbillList.getCardNo();
                                
                                
                                
                                
                                

                                    //   final JPanel jp = new JPanel();

                                        //   JPasswordField pf = new JPasswordField();
                                        //   pf.requestFocusInWindow();

                                        //   jp.add(pf);
                                         //  Object[] options = { "OK", "Cancel" };  
                                         //  int bool =  JOptionPane.showOptionDialog(null, new Object[]{ pf }, "Please Swipe Your Card ..!!", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE, null, null, null);


                                              //  if(bool==JOptionPane.OK_OPTION){
                                               //  card = new String(pf.getPassword());
                                               //  System.err.println("You entered: " + card);   

                                               //   }
                               
                            }
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
                            CreditConfirmList c = list.get(row);
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
                    CreditConfirmList c = list.get(row);
                    BillLogic bl = LookupUtilityImpl.getInstance(null).getDataLogicBill();
                    BillInfo binfo = bl.getBillInfo(c.getBillref());
                    ////////
                    Object objg = new StaticSentence(app.getSession(), "SELECT TAXTOTAL FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
                    double taxamt=Double.parseDouble(objg.toString());
                    BillList.taxamt1=taxamt;
                    ///////
                    printTicket("Printer.Ticket", binfo, binfo.getPlace());
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "debt");
                    l.add(p);
                    binfo.setPayments(l);
                    binfo.setReceiptRef(c.getID());
                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                } catch (BasicException ex) {
                    Logger.getLogger(DebtBillList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables



// METHOD EDITED BY AAKASH 
    
     public static class PendingCreditConfirmListT implements SerializableRead {

       
        private String WName;
        private Double WAmount;
        

        public void readValues(DataRead dr) throws BasicException {
           
            
            WAmount = dr.getDouble(1);
            WName = dr.getString(2);

        // ckey=dr.getString(8);
        }

       

        public String getWName() {
            return WName;
        }

        public Double getWAmount() {
            return WAmount;
        }

        
    }




}
