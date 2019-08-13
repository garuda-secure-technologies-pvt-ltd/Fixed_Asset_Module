/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Billpage.java
 *
 * Created on Dec 11, 2008, 3:41:18 PM
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
//import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.JPaymentSelectRefund;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoCash;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.restaurant.JIntroPageRest;
import com.openbravo.pos.sales.restaurant.JTicketsBagRestaurant;
import com.openbravo.pos.sales.restaurant.discountPage;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

import com.openbravo.pos.payment.TicketInfoCash;
import com.openbravo.pos.sales.restaurant.BillList;
import com.openbravo.pos.sms.SMSgeneralDBSettings;

/**
 *
 * @author swathi
 */
public class Billpage extends javax.swing.JDialog {

    public static double interest1=0.0;
    private static final String PERMISSION_PAYMENT = "payment";
    private BillItemTableModel billtablemodel;
    private List<BillLineInfo> blist;
    private BillLogic blogic;
    private BillLogicApply bla;
    private DataLogicSales dlSales;
    private SMSgeneralDBSettings smsDBsettings;
    private DataLogicFacilities dlfac;
    private BillInfo binfo;
    private boolean resultok = false;
    private BillTaxesLogic taxeslogic;
    private ListKeyed taxcollection;
    private CustomerInfo customertemp;
    private JPaymentSelect paymentdialogreceipt;
    private JPaymentSelect paymentdialogrefund;
    private DataLogicSystem dlSystem;
    private String cname;
    private int remainingBillCount;
    public static Double  Grandtotal = 0.0;
     int i;
     private int iDays;
     private double iRate;
     private String iAccount;
     int iFlag;
     private ArrayList<String> iLocList=new ArrayList<String>();
     Boolean iDetailExistFlag1=false;
    // private Frame frame;

    // private JIntroPageRest m_restaurant;
    private TicketParser m_TTP;
    
    //Added by guru
   // public static double interest1=0.0;
    public static boolean gflag;
    protected Session s;

    
    String Taxcat1 =null;
    String Taxcat2 = null;
    String Taxcat3 = null;  
    Double TaxRate1=0.00;
    Double TaxRate2=0.00;
    Double TaxRate3=0.00;
    
    Double TaxAmt = 0.00;
    Double TaxAmt2 = 0.00;
    Double TaxAmt3 = 0.00;
    
    Double TaxTotal = 0.00;
    public static int partial=0;
    
    
    
    /** Creates new form Billpage */
    public Billpage(Frame parent, DataLogicSales dlSales, BillLogicApply bla, CustomerInfo customer) {

        super(parent, true);

        this.bla = bla;
        this.blogic = LookupUtilityImpl.getInstance(null).getDataLogicBill();
        this.customertemp = customer;
        this.dlSales = dlSales;
        resultok = bla == null;
    }

    public Billpage(Dialog parent, DataLogicSales dlSales, BillLogicApply bla, CustomerInfo customer) {
        super(parent, true);
        // frame=parent;
        this.bla = bla;
        this.blogic = LookupUtilityImpl.getInstance(null).getDataLogicBill();
        this.dlSales = dlSales;
        this.customertemp = customer;
        resultok = bla == null;
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

    public static Billpage getDialog(Component parent, DataLogicSales dlSales, BillLogicApply bla, CustomerInfo customer, int remainingBillCount) {

        Window window = getWindow(parent);

        Billpage mybilllogic;

        if (window instanceof Frame) {
            mybilllogic = new Billpage((Frame) window, dlSales, bla, customer);
        } else {
            mybilllogic = new Billpage((Dialog) window, dlSales, bla, customer);
        }
        mybilllogic.remainingBillCount = remainingBillCount;
        return mybilllogic;
    }

    
    
    
    public void init(BillInfo binfo) throws BasicException {
        //binfo.get
        this.binfo = binfo;
        blist = binfo.getLines();
//        BillLineInfo bb=new BillLineInfo();
//        for(int i=0;i<blist.size();i++){
//        bb.setGtax1id(blist.get(i).getTaxInfo().getId());
//        bb.setGtax2id(blist.get(i).getTaxInfo2().getId());
//        bb.setGtax3id(blist.get(i).getTaxInfo3().getId());
//        
//        }
        if (blist == null) {
            blist = blogic.getBillLineList(binfo.getID());
        }

        List<TaxInfo> taxlist = dlSales.getTaxList().list();
        taxeslogic = new BillTaxesLogic(taxlist);
        taxcollection = new ListKeyed<TaxInfo>(taxlist);

        AppView app = LookupUtilityImpl.getInstance(null).getAppView();
        dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        smsDBsettings = (SMSgeneralDBSettings) app.getBean("com.openbravo.pos.sms.SMSgeneralDBSettings");
        m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());

        paymentdialogreceipt = JPaymentSelectReceipt.getDialog(this);
        paymentdialogreceipt.init(app);
        paymentdialogrefund = JPaymentSelectRefund.getDialog(this);
        paymentdialogrefund.init(app);


        initComponents();
        jBtnPay.setEnabled(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission(PERMISSION_PAYMENT));
        billtablemodel = new BillItemTableModel(blist);
        jTable1.setModel(billtablemodel);

        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(2).setMaxWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);



//        jTextField1.setText(binfo.getID());
        jTextField2.setText(binfo.getCustomer().getSearchkey());
        cname = binfo.getCustomerId();
        Date date = new Date();
        jTextField3.setText("" + date);
        // jButton4.setVisible(false);
        boolean perm = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission(PERMISSION_PAYMENT);
        
        
       
        jButton4.setEnabled(JIntroPageRest.dflag);
        jButton1.setEnabled(!JIntroPageRest.dflag && !perm);
        jButton2.setEnabled(JIntroPageRest.dflag);
        // boolean f=;
        if (LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter")) {
            jButton6.setEnabled(JIntroPageRest.dflag && LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter"));
        } else {
            jButton6.setEnabled(JIntroPageRest.dflag && LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("res counter"));
        }
        jBtnPay.setEnabled(perm);
        jButton5.setEnabled(!JIntroPageRest.dflag && !perm);
        //Remove the below comment if you want to give debit permission only to bar counter
        //jButton5.setEnabled(!JIntroPageRest.dflag && LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter"));
       
        
        String[] arr = binfo.getCustomerId().split("#");
        if (arr.length > 1) {
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
        }
        //jButton1.setVisible(!perm);
        try {
            jButton1.setIcon(new ImageIcon(ImageIO.read(Billpage.class.getResourceAsStream("/com/openbravo/images/cash.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //if(perm==true){

        //}
        if (jButton6.isEnabled()) {
            try {
                //warehouse changes -start
                Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING})).find(binfo.getCreatedBy());
                String warehouse = null;
                if (obj != null && obj[11] != null) {
                    String[] wArr = obj[11].toString().split("#");
                    warehouse = wArr[0];
                }
                AppUser appuser = new AppUser(obj[0].toString(), obj[1].toString(), obj[4].toString(), warehouse);
                //warehouse changes -end
                appuser.fillPermissions(dlSystem);
                if (LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter")) {
                    jButton6.setEnabled(appuser.hasPermission("bar counter"));
                } else {
                    jButton6.setEnabled(appuser.hasPermission("res counter"));
                }
            // jButton6.setEnabled(appuser.hasPermission("bar counter"));
            // jButton6.setEnabled(appuser.hasPermission("res counter"));


            } catch (Exception e) {
                e.printStackTrace();
            //  MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosave"), e);
            //    msg.show(this);
            }
        }
        try {
            loadBill();
        } catch (BasicException e) {
            new MessageInf(e);
        }

    }
    
    public void date(){
       
    Date date1=new Date();
    DateFormat dateFormat=new SimpleDateFormat("date");
    String formatedDate=dateFormat.format(date1);
    int l=formatedDate.length();
    
    for(int i=l;i>10;i++){
       double amt=i*1;
    }

    }
    

    public boolean showDialog() throws BasicException {
        // init(binfo);
        setVisible(true);
        return resultok;
    }

    private void loadBill() throws BasicException {
        if (binfo.getLines() == null) {
            List<BillLineInfo> billList = blogic.getBillLineList(binfo.getID());
            blist = new ArrayList<BillLineInfo>(billList.size());
            for (Iterator<BillLineInfo> it = billList.iterator(); it.hasNext();) {
                BillLineInfo billLineInfo = it.next();
                if (billLineInfo != null) {
                    blist.add(billLineInfo);
                }
            }
        }
    }

    private boolean closeTicket(BillInfo ticket, Object ticketext) throws BasicException {
    /////////////////////////////////////////////pratima: intrest check
    Double ticketandIntrestTotal=ticket.getTotal();
    Double intrest=0.00;
    Double amtplustax =0.00;
     
       if( getPendingBillIntrestDetails()==1){
           if(iLocList.contains(ticket.getWarehouse())){
        Date date=new Date();
         long diff = date.getTime() - ticket.getCreatedDate().getTime();
         int days1=(int)(diff / 1000 / 60 / 60 / 24);
        System.out.println ("Days: " + (diff / 1000 / 60 / 60 / 24));
        if( days1>iDays){
        intrest= ticket.getAmountPlusTax()*(iRate/36500)*(days1);
     
        
        
//        PaymentInfoCash pi=new PaymentInfoCash();
//        pi.setM_dIntrest(interest1);
        
      // new TicketInfoCash(interest1);
          //ticketandIntrestTotal=ticket.getTotal()+intrest;
        }
        interest1=intrest;
        amtplustax = ticket.getTax()+ ticket.getTotal();
        Grandtotal=amtplustax+interest1;
           }
        }
            
        ////////////////////////////////////////////////////////////////////
        jBtnPay.setEnabled(false);
        boolean resultok = false;
        if (!ticket.isPaid()) {

            //jBtnPay.setEnabled(false);
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();



            if (m_App.getAppUserView().getUser().hasPermission("sales.Total")) {

                // reset the payment info
                taxeslogic.calculateTaxes(ticket);
                ticket.resetPayments();

                // Muestro el total
                printTicket("Printer.TicketTotal", ticket, ticketext);


                // Select the Payments information
                JPaymentSelect paymentdialog = ticket.getTotal() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected(true); //Print always
                boolean flag = true;
                paymentdialog.setIntrestAmount(intrest);
                  ticketandIntrestTotal=ticket.getTotal()+intrest;
               
              if (paymentdialog.showDialog(ticketandIntrestTotal, ticket.getCustomer(), ticket.getCreatedBy(), true)) {
                
               // if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), ticket.getCreatedBy(), true)) {

                    // assign the payments selected and calculate taxes.
                    ticket.setPayments(paymentdialog.getSelectedPayments());

                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    try {
                        flag = dlSales.payBill(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }
                    if(ticket.getReceiptRef() != null){  //Added by ganesh
                        
                    // Print receipt.
                    if (flag == true) {
                        // String smsmsg="Dear Member,\rYour a/c with us has been debited by Rs."+Formats.ConvertDoubleToString(ticket.getTotal())+" bill no "+ticket.printId()+" for bar usage."+"Thank u for using our facility";
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket_1"
                                : "Printer.Ticket2", ticket, ticketext);
                    

                        
   /////////////////////////////////////////// ///////////////////////////////////////////                /////////////////////////////////////                     
  // Code added for billing member ..........                     
                        
                        
      String BMName="Credit Check for billing member";
      String BName2 = "Facility for billing member"; 
         
            Object[] FacObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(BMName);
            if(FacObj!=null){  
                
                Boolean v5 = (Boolean)FacObj[0];
                if(v5){

                    
                    
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+binfo.getTax();
                                     
                                       System.out.println("amount :" + amt);
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 
                    
                    
                }
                 else{
                    
                  
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                        // Code added if other facility is selected.........
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                    
                        Object[] Fac2Obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BName2);
                        String FacStrFull  = Fac2Obj[0].toString();
                        String []strarr = FacStrFull.split("#");
                        String FinFacId = strarr[0];
                        
                        Object[] QtFacIdObj = (Object[]) new StaticSentence(m_App.getSession(), "select f.id  from facility f , locations l , bill b  where l.facility=f.id and b.warehouse=l.id and b.id= ?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(binfo.getID());
                        String QtFacID  = QtFacIdObj[0].toString();
                        
                        if(FinFacId.equals(QtFacID)){
                                
                                  ///aaa
                                //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                               String idt = customertemp.getId();
                                               Double amt = binfo.getAmount()+binfo.getTax();
                                           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                           if(obj!=null){
                                               String popb=obj[0].toString();
                                               Double POPB = new Double(popb);
                                               dlfac.roundTwoDecimals(POPB);
                                               Double COPB = POPB-amt;
                                               COPB = dlfac.roundTwoDecimals(COPB);
                                               new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                            }
                               ///aaa 
 
                        }
                    
                     
                }
                
            }                  
                
            else{
                
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+binfo.getTax();
                                      
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 

             }
                        
             
            
            resultok = true;
                    } 
              }
                    else {
                        //new PreparedSentence(s, "DELETE FROM PAYMENTS WHERE RECEIPT=? ", SerializerWriteString.INSTANCE).exec(JPaymentSelect.intRefID);
                        resultok = false;
                    }
                }

                // reset the payment info
                ticket.resetTaxes();
                ticket.resetPayments();
            }

        // cancelled the ticket.total script
        // or canceled the payment dialog
        // or canceled the ticket.close script

        }
        return resultok;
 
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
            /*  String bank=   pi.get.get(0).getChequeDetails().getBank();
            for(PaymentInfo p:pi)
            {
            String bank=  p.getChequeDetails().getChequeno().getBank();
            }*/
            //ticket.resetPayments();
            try {
                Object objg = new StaticSentence(m_App.getSession(), "SELECT TAXTOTAL FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
                    double taxamt=Double.parseDouble(objg.toString());
                    BillList.taxamt1=taxamt;
                WaiterInfo w = LookupUtilityImpl.getInstance(null).getWaiterMap().get(ticket.getWaiter());
                waitername = w.getName();
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ticket.getPlace());

                if (obj1 == null) {
                    table = "";
                } else {
                    table = obj1[0].toString();
                }
                // PlacesInfo p= LookupUtilityImpl.getInstance(null).getPlacesMap().get(ticket.getPlace());

                //  table=p.getName();
                //  try{


                //warehouse changes -start
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,ROLE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(ticket.getCreatedBy());
                /*if(obj[5]==null)
                obj[5]=new Icon();*/

                String warehouse = null;
                if (obj2 != null && obj2[3] != null) {
                    String[] wArr = obj2[3].toString().split("#");
                    warehouse = wArr[0];
                }
                AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString(), warehouse);
                //warehouse changes -end
                appuser.fillPermissions(dlSystem);
                boolean flag1 = appuser.hasPermission("bar counter");

                //  ticket.getWaiter();
                boolean flag = m_App.getAppUserView().getUser().hasPermission("bar counter");
                boolean crconf = false;
                if (ticketext.equals("cerditconf")) {
                    crconf = true;
                }
                taxeslogic.calculateTaxes(ticket);
                String[] str = null;
                if (obj2[3] != null) {
                    str = obj2[3].toString().split("#");
                }
                
                String name = getRDisplayName(str[0].toString());
                
                //praveen:added to display member's account balance
                Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                        "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID " +
                        "UNION ALL " +
                        "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID) " +
                        "AS TOTAL GROUP BY ACC",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{customertemp.getId(), customertemp.getId()});
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
                Double bal1 =d - d1+ticket.getTotal();
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
                ///////Added by guru
                if (bal1 > 0) {
                    //bal = dlfac.roundTwoDecimals(bal);
                    bal1 = dlfac.roundTwoDecimals(bal1);
                    //accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                    accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
                } else {
                    //bal = bal * -1;
                     bal1 = bal1 * -1;
                    //bal = dlfac.roundTwoDecimals(bal);
                    bal1 = dlfac.roundTwoDecimals(bal1);
                    //accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                    accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
                }
                ///////
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
                //  script.put("bank", bank);
              /*  if(debt1==1)
                {
                m_TTP.printTicket(script.eval(sresource).toString());
                }*/
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
    
    
    public String getRDisplayName(String wharehouseID)
    {
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        Object[] displayObj;
        try 
        {
            displayObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(wharehouseID);
            if (displayObj != null && displayObj[0] != null) 
            {
                return displayObj[0].toString();
            }
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public String[] getCustomerBalanceDue(BillInfo ticket) throws BasicException
    {
        String[] balanceArr = new String[2];
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
         //praveen:added to display member's account balance
        Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID " +
                "UNION ALL " +
                "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID) " +
                "AS TOTAL GROUP BY ACC",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{customertemp.getId(), customertemp.getId()});
        Double d = 0.0;
        Double d1 = 0.0;
        if (obj4 != null) 
        {
            if (obj4[0] != null) 
            {
                d = Double.valueOf(obj4[0].toString());
            }
            if (obj4[1] != null) 
            {
                d1 = Double.valueOf(obj4[1].toString());
            }
        }
        Double bal = d - d1;
        Double bal1 = d - d1 + ticket.getTotal();
        String accountBalance = null;
        String accountBalance1 = null;
        if (bal > 0) 
        {
            bal = dlfac.roundTwoDecimals(bal);
            accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
        } 
        else 
        {
           bal = bal * -1;
           bal = dlfac.roundTwoDecimals(bal);
           accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
        }
       
        if (bal1 > 0) 
        {
            bal1 = dlfac.roundTwoDecimals(bal1);
            accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
        }
        else 
        {
           bal1 = bal1 * -1;
           bal1 = dlfac.roundTwoDecimals(bal1);
           accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
        }
        balanceArr[0] = accountBalance;
        balanceArr[1] = accountBalance1;
        return balanceArr;
    }
    
    
    public void checkForSMS( BillInfo ticket, Object ticketext)
    {
         boolean sendSMSwhileBill =  smsDBsettings.getSMSvalue(SMSgeneralDBSettings.SMS_BILL_ID);
         boolean isFacilityEnable = smsDBsettings.isFacilityEnable(SMSgeneralDBSettings.SMS_BILL_ID, smsDBsettings.getFacilityId(ticket.getWarehouse()) );
         if(sendSMSwhileBill && isFacilityEnable)
       {
           String smsString = smsDBsettings.getMessage(SMSgeneralDBSettings.SMS_BILL_ID);
           createSMS(smsString, ticket);
       }
    }

    public void createSMS(String smsString,  BillInfo ticket)
    {
        String sms = smsString;
        smsString = smsString.replace(SMSgeneralDBSettings.SMS_BILL_KEY, ticket.getID());
        smsString = smsString.replace(SMSgeneralDBSettings.SMS_DTM_KEY , ticket.printDate());
        smsString = smsString.replace(SMSgeneralDBSettings.SMS_FACILITY_KEY, getRDisplayName(ticket.getWarehouse()));
        smsString = smsString.replace(SMSgeneralDBSettings.SMS_WHAREHOUSE_NAME_KEY, getRDisplayName(ticket.getWarehouse()));
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        String x = m_App.getAppUserView().getUser().getRole();
        smsString = smsString.replace(SMSgeneralDBSettings.SMS_ROLE_KEY, LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString()); 
        smsString = smsString.replace(SMSgeneralDBSettings.SMS_TOT_AMOUNT_KEY, ticket.getTotal()+"");
        
        // check if string has balance due text 
        if(smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE) || smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER))
        {
            try 
            {
                String[] arr = getCustomerBalanceDue(ticket);
                if(arr != null && arr.length > 0)
                {
                    smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE, arr[0]);
                    smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER, arr[1]);
                }
            } 
            catch (BasicException ex) 
            {
                Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        // check if customer is guest
        if(ticket.getCustomer().getId().contains("Guest"))
        {
            String custID = smsDBsettings.getCustIdFromGuestID(ticket.getCustomer());
             if(custID != null)
             {
                try 
                {
                    CustomerInfoExt custInfo =  dlSales.loadCustomerExt(custID);
                    if(custInfo != null )
                    {
                        smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NAME_KEY, custInfo.getName());
                        smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NO_KEY, custInfo.getSearchkey()); 
                        if(custInfo.getmobile() != null && !custInfo.getmobile().isEmpty())
                        {
                           smsDBsettings.insertSMStoActiveMsgTable(smsString, custInfo.getmobile(), custInfo.getId()); 
                        }
                    }
                } 
                catch (BasicException ex)
                {
                    Logger.getLogger(JPanelTicket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            if(ticket.getCustomer().getmobile() != null && !ticket.getCustomer().getmobile().isEmpty())
            {
               smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NAME_KEY, ticket.getCustomer().getName());
               smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NO_KEY, ticket.getCustomer().getSearchkey());
               smsDBsettings.insertSMStoActiveMsgTable(smsString, ticket.getCustomer().getmobile(), ticket.getCustomer().getId());
               Logger.getLogger(JPanelTicket.class.getName()).log(Level.INFO,  "SMS sent successfully : "+smsString);
            }  
        }
        
        
    
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

    private boolean saveBill(String type) {
        if (bla != null && !resultok) {
            try {
                boolean flag = bla.saveBill(type);
                resultok = true;
                return flag;
            } catch (Exception e) {
                e.printStackTrace();
                new MessageInf(e).show(this);
            }
        }
        return false;
    }

    /*   private boolean validateDiscount(List<BillLineInfo> bill, BillLineInfo discountbline) {
    int quantity = discountbline.getMultiply();
    for (BillLineInfo blInfo : bill) {
    if (blInfo.getProduct().equals(discountbline.getProduct())) {
    quantity += blInfo.getMultiply();
    }
    }

    if (quantity < 0) {
    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.toomuchdiscount")).show(this);
    return false;
    }

    return true;
    }*/
    private void refreshItemModel() {
        if (jTable1 == null) {
            return;
        }
        int i = jTable1.getSelectedRow();

        billtablemodel = new BillItemTableModel(blist);
        jTable1.setModel(billtablemodel);

        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(2).setMaxWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);

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
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jBtnPay = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BILL");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sl No", "Product", "Qty", "Rate", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Member ID");

        jTextField2.setEditable(false);

        jLabel3.setText("Date");

        jTextField3.setEditable(false);

        jButton5.setText("Debit Bill");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
        jButton1.setText("Cash Bill");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jBtnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash.png"))); // NOI18N
        jBtnPay.setText("Pay");
        jBtnPay.setEnabled(false);
        jBtnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPayActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/ark216.png"))); // NOI18N
        jButton2.setText("Reprint");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Discount");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Convert to Debit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jBtnPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int flag1 = checkForBillDiscount(binfo.getID());
        if (flag1 == 0) {
            try {
                taxeslogic.calculateTaxes(bla.getBillInfo());
            } catch (Exception e) {
            }
            boolean flag = saveBill("Cash");
            if (flag == true) {
                printTicket("Printer.Ticket", binfo, binfo.getPlace());
                checkForSMS(binfo, binfo.getPlace());
                dispose();
                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                
                
       
                try {
                    String name = customertemp.getName();
                    String idt = customertemp.getId();
                    if (remainingBillCount == 0) {
                        new StaticSentence(m_App.getSession(), "DELETE FROM SHAREDTICKETS  WHERE CID = ? AND NAME = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{customertemp.getId(), customertemp.getSearchkey(), m_App.getAppUserView().getUser().getRole()});
                    }

                } catch (Exception e) {
                }
                //  if(remainingBillCount==0)
                JTicketsBagRestaurant.m_rest.newTicket();
                BillList.taxamt1=0.0;
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBtnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPayActionPerformed
        int flag = checkForBillDiscount(binfo.getID());
        try {
            if(getNextReceiptIDgg()!=null){
                if (flag == 0) {
                    
                    //  saveBill();
                    resultok = true;
                    // if (resultok) {
                    try {
                        int x= getPendingBillIntrestDetails();
                        if(iDetailExistFlag1==true){
                            
                            if(x==1){
                                //jBtnPay.setEnabled(false);
//                                closeTicket(binfo, binfo.getPlace());
                                  closeTicket2(binfo, binfo.getPlace());
                                  Billpage.interest1=0.0;
                                dispose();
                            }
                        }
                        else
                        {
//                            closeTicket1(binfo, binfo.getPlace());
                              closeTicket3(binfo, binfo.getPlace());
                              Billpage.interest1=0.0;
                                dispose();
                        }
                    } catch (BasicException e) {
                        e.printStackTrace();
                        new MessageInf(e).show(this);
                    }
                }
            }
            else {
                
                JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
                dispose();
        }
        } catch (BasicException ex) {
            Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnPayActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            ////////
//                    Object objg = new StaticSentence(m_App.getSession(), "SELECT TAXTOTAL FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(binfo.getID());
//                    double taxamt=Double.parseDouble(objg.toString());
//                    BillList.taxamt1=taxamt;
                    ///////
            printTicket("Printer.Ticket", binfo, binfo.getPlace());

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String crby = binfo.getCreatedBy();
        String auser = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        if (auser.equals(crby)) {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            if (jTable1.getSelectedRowCount() == 1) {
                try {
                    int row = jTable1.getSelectedRow();
                    BillLineInfo temp = blist.get(row);
                    BillLineInfo bline = new BillLineInfo();
                    bline.setProduct(temp.getProduct());
                    bline.setParentid(temp.getParentid());
                    int cnt = 0;
                    int limit = temp.getMultiply();
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SUM(QTY) FROM REVERSEDBILL WHERE BILLID = ? AND PRODUCT=? AND (AUTHORISED IS NULL OR AUTHORISED = TRUE) GROUP BY BILLID  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{binfo.getID(), temp.getProduct().getID()});
                    if (obj1 != null) {
                        cnt = Integer.parseInt(obj1[0].toString());
                    }
                    limit = limit + cnt;
                    // Frame frame=new JFra();
                    if (limit > 0) {
                        limit=limit* -1;   //Added by guru to to made negative in amt of single item
                        discountPage discount = discountPage.getDialog(this, blist, bline, limit);
                        boolean resultok = false;
                        try {
                            resultok = discount.showDialog(cname);
                        } catch (BasicException e) {
                            new MessageInf(e).show(this);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Discount Request cannot be processed(Limit reached)", "Discount Failed", JOptionPane.OK_OPTION);
                    }
                } catch (BasicException ex) {
                    ex.printStackTrace();
                // Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Do you want to Discount the entire bill", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int count = 0;

                    try {
                        Object obj[] = (Object[]) new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM REVERSEDBILL WHERE BILLID = ? AND (AUTHORISED IS NULL OR AUTHORISED = TRUE) GROUP BY BILLID  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(binfo.getID());
                        if (obj != null) {
                            count = Integer.parseInt(obj[0].toString());
                        }
                        if (count == 0) {
                            Date dnow = new Date();
                            discountPage discount = discountPage.getDialog(this, blist, new BillLineInfo(), 0);

                            String reason = discount.showDialogreason();
                            
                          
                            
                            if (reason != null) {
                                for (BillLineInfo bli : blist) {
                                    //Double a=0;

                                    Integer temp = bli.getMultiply();
                                    if (temp > 0) {
                                        
                                        
                                        Double qty = Double.parseDouble(temp.toString());
                                        Double rate = bli.getRate();
                                        
                                        Taxcat1  = bli.getProduct().getTaxCategoryID();
                                        if(bli.getProduct().getTaxCategoryID2()!=null){
                                            Taxcat2  = bli.getProduct().getTaxCategoryID2();
                                        }
                                        if(bli.getProduct().getTaxCategoryID3()!=null){
                                            Taxcat3  = bli.getProduct().getTaxCategoryID3();
                                        }
                                                 
                                        TaxRate1 = getTaxRate(Taxcat1);
                                        if(Taxcat2!=null){
                                            TaxRate2 = getTaxRate(Taxcat2);
                                        }
                                        if(Taxcat3!=null){
                                            TaxRate3 = getTaxRate(Taxcat3);
                                        }
                                        Double TotalTaxRate =  (TaxRate1+TaxRate2+TaxRate3);       
                                        Double TotalTaxAmount =rate* qty* TotalTaxRate; 
                                        System.out.println("Tax for reversed amount : ");
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), bli.getParentid(), cname, bli.getProduct().getID(), (qty * -1), rate, reason, m_App.getAppUserView().getUser().getName(), dnow ,TotalTaxAmount};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE,TAXTOTAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.TIMESTAMP,Datas.DOUBLE})).exec(value);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Cannot Disount", "", JOptionPane.OK_OPTION);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Cannot Request for Disount.Request has already been sent", "", JOptionPane.OK_OPTION);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select one product", "Error", JOptionPane.OK_OPTION);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Only the bill creator can discount it", "Error", JOptionPane.OK_OPTION);
        }
    //if(resultok)
       /*  if (resultok ) {
    try {
    //  int size=blist.size();
    blist.add(bline);
    blogic.insertBillItem(bline,blist.size());
    //qticket.insertQTicketLine(qt, qtl);
    } catch(BasicException e) {
    new MessageInf(e);
    }
    refreshItemModel();
    }*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int flag1 = checkForBillDiscount(binfo.getID());
        if (flag1 == 0) {
            try {
                taxeslogic.calculateTaxes(bla.getBillInfo());
            } catch (Exception e) {
            }
            boolean flag = saveBill("Debt");
            if (flag == true) {
                BillList.taxamt1=binfo.getTax();
                printTicket("Printer.Ticket", binfo, binfo.getPlace());
                checkForSMS(binfo, binfo.getPlace()); 
                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                try {
                    // String name=customertemp.getName();
                     String idt=customertemp.getId();
                    if (remainingBillCount == 0) {
                        new StaticSentence(m_App.getSession(), " DELETE FROM SHAREDTICKETS  WHERE CID = ? AND NAME = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{customertemp.getId(), customertemp.getSearchkey(), m_App.getAppUserView().getUser().getRole()});
                    }
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getAmount(), "debt");
                    l.add(p);
                    binfo.setPayments(l);
                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                    //dlSales.payDebtBill(binfo);
                    BillList.taxamt1=0.0;
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //if(remainingBillCount==0)
                JTicketsBagRestaurant.m_rest.newTicket();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     try {
            if (JOptionPane.showConfirmDialog(null, "Do you want to change the cash bill to debit bill ?", "Change bill type", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Transaction t = new Transaction(LookupUtilityImpl.getInstance(null).getAppView().getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                        String rno = blogic.getNextReceiptID(m_App.getAppUserView().getUser().getName());
                        boolean flag = false;
                        String role = null; 
                        String confoId = UUID.randomUUID().toString();//praveen:added to insert into creditconfolist instead of rno
                        Date d = new Date();
                        Object[] obj = (Object[]) new PreparedSentence(m_App.getSession(), "SELECT B.PAID,B.CREATEDDATE,P.ROLE FROM BILL B,PEOPLE P WHERE B.ID=? AND B.CREATEDBY=P.NAME", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING})).find(binfo.getID());
                        if (obj != null) {
                            flag = Boolean.valueOf(obj[0].toString());
                            d = (Date) obj[1];
                            role = String.valueOf(obj[2]);
                        }
                        if (m_App.getAppUserView().getUser().getRole().equals(role)) {
                            if (m_App.getAppUserView().getUser().hasPermission("bar counter") && !flag) {
                                int cnt = new PreparedSentence(m_App.getSession(), "UPDATE BILL SET PAID =TRUE WHERE ID=? ", SerializerWriteString.INSTANCE).exec(binfo.getID());
                                if (cnt > 0) {
//commented by pratima to add id in below query
//                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
//                                            new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                    //  new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{ d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getTotal(), binfo.getWaiter()});
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES (?, ?, ?,?,?,?,?)",
                                            new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Integer.parseInt(getCreditConfID()),d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                    
                                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                                    PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "debt");
                                    l.add(p);
                                    binfo.setPayments(l);
                                    binfo.setReceiptRef(rno);
                                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                                }
                            } else if (m_App.getAppUserView().getUser().hasPermission("res counter") && !flag) {
                                int cnt = new PreparedSentence(m_App.getSession(), "UPDATE BILL SET PAID =TRUE WHERE ID=? ", SerializerWriteString.INSTANCE).exec(binfo.getID());
                                if (cnt > 0) {
//commented by pratima
//                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
//                                            new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});
 new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES (?, ?, ?,?,?,?,?)",
                                            new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Integer.parseInt(getCreditConfID()),d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});
                                                                      
List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                                    PaymentInfo p = new PaymentInfoTicket(binfo.getTotal(), "debt");
                                    l.add(p);
                                    binfo.setPayments(l);
                                    binfo.setReceiptRef(rno);
                                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                                }
                            }
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry,the bill does not belong to you", "cannot covert to debit bill", JOptionPane.WARNING_MESSAGE);
                        }
                        return null;
                    }
                };
                t.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnPay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables



 // get tax rate ...... 
    
    public Double  getTaxRate(String Name) throws ParseException , BasicException{
       AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
       Double opass = 0.00;
       Object o = null;
      
       o =(Object) new PreparedSentence(m_App.getSession(), "SELECT RATE  FROM taxes where category=?", SerializerWriteString.INSTANCE ,SerializerReadString.INSTANCE).find(Name);
       if(o!=null){
            opass = Double.parseDouble(o.toString());
            return opass; 
       }
       else{
          return 0.00; 
       }
      }  

    
    public String getCreditConfID() throws BasicException
    {
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL WHERE ID=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.DOUBLE})).find(new Object[]{"CREDITCONFLISTID"});
        if (obj != null) 
        {
            String creditConfID =  obj[0].toString();
            int creditConfID1=(int)Double.parseDouble(creditConfID);
            creditConfID =String.valueOf(creditConfID1);
            Double max = Double.parseDouble(obj[0].toString());

            max++;
            System.out.println("max"+max);

            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{max,"CREDITCONFLISTID"});
            return creditConfID;
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Please Specify the Credit Confirmation ID", "Cannot update Credit Confirmation table", JOptionPane.OK_OPTION);
            return null;
        }
    }
 public int getPendingBillIntrestDetails() throws BasicException{
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        int flag=0;
        
    try{
         Object[] obj2=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill intrest Flag'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.INT})).find();
       
        System.out.println("iFlag"+iFlag);
      if(obj2[0]!=null){
           iFlag=Integer.parseInt(obj2[0].toString());
//          iDetailExistFlag1=true;
        if(iFlag==1){
            iDetailExistFlag1=true;
                Object[] obj1 =  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Days'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.INT})).find();
                if((obj1[0]!=null))
                iDays=Integer.parseInt(obj1[0].toString());
                Object[] obj=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Rate'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.DOUBLE})).find();
                if( obj[0]!=null)
                iRate=Double.parseDouble(obj[0].toString());
                iAccount =  (String)new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest Account'", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find();
    
                Object[] obj3=  (Object[])new StaticSentence(m_App.getSession(), "SELECT value from generaltable where name='Pending Bill Intrest warehouses'", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{ Datas.STRING})).find();
                String[] locArr=obj3[0].toString().split("#");
               for(int i=0;i<locArr.length;i++){
                   String temp=locArr[i];
             iLocList.add(temp);
               }
                if((obj[0]==null)||(obj1[0]==null)||(iAccount==null)){
                 JOptionPane.showMessageDialog(null, "Please Specify the intrest Details in GenralTable ","Error", JOptionPane.OK_OPTION);
                }else{
                    flag=1;
                }
        }
        }
//      if(iDetailExistFlag1.equals(false)){
//            JOptionPane.showMessageDialog(null, "Please Specify the Pending Bill intrest Flag in GenralTable ","Error", JOptionPane.OK_OPTION);
//        }
        
        
    } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    return flag;
}
 
 //Added by GG
    public final String getNextReceiptIDgg() throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String receiptnum;
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        String createdby=BillList.gg;
        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //BillList.gg=null;
            return receiptnum;
        } else {
                return null;
        }
        
    }    
    
        private boolean closeTicket1(BillInfo ticket, Object ticketext) throws BasicException {
    /////////////////////////////////////////////pratima: intrest check
    Double ticketandIntrestTotal=ticket.getTotal();
//    Double intrest=0.00;
//    Double amtplustax =0.00;
//     
//       if( getPendingBillIntrestDetails()==1){
//           if(iLocList.contains(ticket.getWarehouse())){
//        Date date=new Date();
//         long diff = date.getTime() - ticket.getCreatedDate().getTime();
//         int days1=(int)(diff / 1000 / 60 / 60 / 24);
//        System.out.println ("Days: " + (diff / 1000 / 60 / 60 / 24));
//        if( days1>iDays){
//        intrest= ticket.getAmountPlusTax()*(iRate/36500)*(days1);
//     
        
        
//        PaymentInfoCash pi=new PaymentInfoCash();
//        pi.setM_dIntrest(interest1);
        
      // new TicketInfoCash(interest1);
          //ticketandIntrestTotal=ticket.getTotal()+intrest;
//        }
//        interest1=intrest;
//        amtplustax = ticket.getTax()+ ticket.getTotal();
//        Grandtotal=amtplustax+interest1;
//           }
//        }
//            
        ////////////////////////////////////////////////////////////////////
        jBtnPay.setEnabled(false);
        boolean resultok = false;
        if (!ticket.isPaid()) {

            //jBtnPay.setEnabled(false);
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();



            if (m_App.getAppUserView().getUser().hasPermission("sales.Total")) {

                // reset the payment info
                taxeslogic.calculateTaxes(ticket);
                ticket.resetPayments();

                // Muestro el total
                printTicket("Printer.TicketTotal", ticket, ticketext);


                // Select the Payments information
                JPaymentSelect paymentdialog = ticket.getTotal() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected(true); //Print always
                boolean flag = true;
                //paymentdialog.setIntrestAmount(intrest);
                  ticketandIntrestTotal=ticket.getTotal();//+intrest;
               
              if (paymentdialog.showDialog(ticketandIntrestTotal, ticket.getCustomer(), ticket.getCreatedBy(), true)) {
                
               // if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), ticket.getCreatedBy(), true)) {

                    // assign the payments selected and calculate taxes.
                    ticket.setPayments(paymentdialog.getSelectedPayments());

                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    try {
                        flag = dlSales.payBill(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }
                    if(ticket.getReceiptRef() != null){  //Added by ganesh
                        
                    // Print receipt.
                    if (flag == true) {
                        // String smsmsg="Dear Member,\rYour a/c with us has been debited by Rs."+Formats.ConvertDoubleToString(ticket.getTotal())+" bill no "+ticket.printId()+" for bar usage."+"Thank u for using our facility";
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket_1"
                                : "Printer.Ticket2", ticket, ticketext);
                    

                        
   /////////////////////////////////////////// ///////////////////////////////////////////                /////////////////////////////////////                     
  // Code added for billing member ..........                     
                        
                        
      String BMName="Credit Check for billing member";
      String BName2 = "Facility for billing member"; 
         
            Object[] FacObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(BMName);
            if(FacObj!=null){  
                
                Boolean v5 = (Boolean)FacObj[0];
                if(v5){

                    
                    
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+binfo.getTax();
                                     
                                       System.out.println("amount :" + amt);
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 
                    
                    
                }
                 else{
                    
                  
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                        // Code added if other facility is selected.........
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                    
                        Object[] Fac2Obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BName2);
                        String FacStrFull  = Fac2Obj[0].toString();
                        String []strarr = FacStrFull.split("#");
                        String FinFacId = strarr[0];
                        
                        Object[] QtFacIdObj = (Object[]) new StaticSentence(m_App.getSession(), "select f.id  from facility f , locations l , bill b  where l.facility=f.id and b.warehouse=l.id and b.id= ?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(binfo.getID());
                        String QtFacID  = QtFacIdObj[0].toString();
                        
                        if(FinFacId.equals(QtFacID)){
                                
                                  ///aaa
                                //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                               String idt = customertemp.getId();
                                               Double amt = binfo.getAmount()+binfo.getTax();
                                           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                           if(obj!=null){
                                               String popb=obj[0].toString();
                                               Double POPB = new Double(popb);
                                               dlfac.roundTwoDecimals(POPB);
                                               Double COPB = POPB-amt;
                                               COPB = dlfac.roundTwoDecimals(COPB);
                                               new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                            }
                               ///aaa 
 
                        }
                    
                     
                }
                
            }                  
                
            else{
                
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+binfo.getTax();
                                      
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 

             }
                        
             
            
            resultok = true;
                    } 
              }
                    else {
                        //new PreparedSentence(s, "DELETE FROM PAYMENTS WHERE RECEIPT=? ", SerializerWriteString.INSTANCE).exec(JPaymentSelect.intRefID);
                        resultok = false;
                    }
                }

                // reset the payment info
                ticket.resetTaxes();
                ticket.resetPayments();
            }

        // cancelled the ticket.total script
        // or canceled the payment dialog
        // or canceled the ticket.close script

        }
        return resultok;
 
    }
        //Added methods closeTicket2 and closeTicket3 for reciept purpose
        
     private boolean closeTicket2(BillInfo ticket, Object ticketext) throws BasicException {
    /////////////////////////////////////////////pratima: intrest check
    Double ticketandIntrestTotal=ticket.getTotal();
    Double intrest=0.00;
    Double amtplustax =0.00;
     
       if( getPendingBillIntrestDetails()==1){
           if(iLocList.contains(ticket.getWarehouse())){
        Date date=new Date();
         long diff = date.getTime() - ticket.getCreatedDate().getTime();
         int days1=(int)(diff / 1000 / 60 / 60 / 24);
        System.out.println ("Days: " + (diff / 1000 / 60 / 60 / 24));
   //int days2 = days1+5;
        if( days1>iDays){
        intrest= ticket.getTotal1()*(iRate/36500)*(days1);
     
        
        
//        PaymentInfoCash pi=new PaymentInfoCash();
//        pi.setM_dIntrest(interest1);
        
      // new TicketInfoCash(interest1);
          //ticketandIntrestTotal=ticket.getTotal()+intrest;
        }
        interest1=intrest;
        amtplustax = ticket.getTotal1();
        Grandtotal=amtplustax+interest1;
           }
        }
            
        ////////////////////////////////////////////////////////////////////
        jBtnPay.setEnabled(false);
        boolean resultok = false;
        if (!ticket.isPaid()) {

            //jBtnPay.setEnabled(false);
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();



            if (m_App.getAppUserView().getUser().hasPermission("sales.Total")) {

                // reset the payment info
                taxeslogic.calculateTaxes(ticket);
                ticket.resetPayments();

                // Muestro el total
                printTicket("Printer.TicketTotal", ticket, ticketext);


                // Select the Payments information
                JPaymentSelect paymentdialog = ticket.getTotal1() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected(true); //Print always
                boolean flag = true;
                paymentdialog.setIntrestAmount(intrest);
                  ticketandIntrestTotal=ticket.getTotal1()+intrest;
               
              if (paymentdialog.showDialog(ticketandIntrestTotal, ticket.getCustomer(), ticket.getCreatedBy(), true)) {
                
               // if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), ticket.getCreatedBy(), true)) {

                    // assign the payments selected and calculate taxes.
                    
                    List<PaymentInfo> bp=paymentdialog.getSelectedPayments();
                    //Adde by Ganesh
                    if(bp.size()>1){
//                        partial=bp.size();
                        ticket.setPayments(paymentdialog.getSelectedPayments());
//                    for(PaymentInfo aa : bp){
//                        partial--;
//                    ticket.setPayments1(aa);
        
                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    
                    try {
                        flag = dlSales.payAccount4(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }
                    // ended by Ganesh
                    /////////////
                    
                    /////////
                    }
                    else{
                    
                              
                    ticket.setPayments(paymentdialog.getSelectedPayments());
        
                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    
                    try {
                        flag = dlSales.payAccount3(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    }
                    if(ticket.getReceiptRef() != null){  //Added by ganesh
                        
                    // Print receipt.
                    if (flag == true) {
                        // String smsmsg="Dear Member,\rYour a/c with us has been debited by Rs."+Formats.ConvertDoubleToString(ticket.getTotal())+" bill no "+ticket.printId()+" for bar usage."+"Thank u for using our facility";
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket_1"
                                : "Printer.Ticket2", ticket, ticketext);
                    

                        
   /////////////////////////////////////////// ///////////////////////////////////////////                /////////////////////////////////////                     
  // Code added for billing member ..........                     
                        
                        
      String BMName="Credit Check for billing member";
      String BName2 = "Facility for billing member"; 
         
            Object[] FacObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(BMName);
            if(FacObj!=null){  
                
                Boolean v5 = (Boolean)FacObj[0];
                if(v5){

                    
                    
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+BillList.taxamt1;
                                     
                                       System.out.println("amount :" + amt);
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 
                    
                    
                }
                 else{
                    
                  
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                        // Code added if other facility is selected.........
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                    
                        Object[] Fac2Obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BName2);
                        String FacStrFull  = Fac2Obj[0].toString();
                        String []strarr = FacStrFull.split("#");
                        String FinFacId = strarr[0];
                        
                        Object[] QtFacIdObj = (Object[]) new StaticSentence(m_App.getSession(), "select f.id  from facility f , locations l , bill b  where l.facility=f.id and b.warehouse=l.id and b.id= ?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(binfo.getID());
                        String QtFacID  = QtFacIdObj[0].toString();
                        
                        if(FinFacId.equals(QtFacID)){
                                
                                  ///aaa
                                //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                               String idt = customertemp.getId();
                                               Double amt = binfo.getAmount()+BillList.taxamt1;
                                           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                           if(obj!=null){
                                               String popb=obj[0].toString();
                                               Double POPB = new Double(popb);
                                               dlfac.roundTwoDecimals(POPB);
                                               Double COPB = POPB-amt;
                                               COPB = dlfac.roundTwoDecimals(COPB);
                                               new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                            }
                               ///aaa 
 
                        }
                    
                     
                }
                
            }                  
                
            else{
                
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+BillList.taxamt1;
                                      
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 

             }
                        
             
            
            resultok = true;
                    } 
              }
                    else {
                        //new PreparedSentence(s, "DELETE FROM PAYMENTS WHERE RECEIPT=? ", SerializerWriteString.INSTANCE).exec(JPaymentSelect.intRefID);
                        resultok = false;
                    }
                }

                // reset the payment info
                ticket.resetTaxes();
                ticket.resetPayments();
            }

        // cancelled the ticket.total script
        // or canceled the payment dialog
        // or canceled the ticket.close script

        }
        return resultok;
 
    }
     
      private boolean closeTicket3(BillInfo ticket, Object ticketext) throws BasicException {
    /////////////////////////////////////////////pratima: intrest check
    Double ticketandIntrestTotal=ticket.getTotal();
//    Double intrest=0.00;
//    Double amtplustax =0.00;
//     
//       if( getPendingBillIntrestDetails()==1){
//           if(iLocList.contains(ticket.getWarehouse())){
//        Date date=new Date();
//         long diff = date.getTime() - ticket.getCreatedDate().getTime();
//         int days1=(int)(diff / 1000 / 60 / 60 / 24);
//        System.out.println ("Days: " + (diff / 1000 / 60 / 60 / 24));
//        if( days1>iDays){
//        intrest= ticket.getAmountPlusTax()*(iRate/36500)*(days1);
//     
        
        
//        PaymentInfoCash pi=new PaymentInfoCash();
//        pi.setM_dIntrest(interest1);
        
      // new TicketInfoCash(interest1);
          //ticketandIntrestTotal=ticket.getTotal()+intrest;
//        }
//        interest1=intrest;
//        amtplustax = ticket.getTax()+ ticket.getTotal();
//        Grandtotal=amtplustax+interest1;
//           }
//        }
//            
        ////////////////////////////////////////////////////////////////////
        jBtnPay.setEnabled(false);
        boolean resultok = false;
        if (!ticket.isPaid()) {

            //jBtnPay.setEnabled(false);
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();



            if (m_App.getAppUserView().getUser().hasPermission("sales.Total")) {

                // reset the payment info
                taxeslogic.calculateTaxes(ticket);
                ticket.resetPayments();

                // Muestro el total
                printTicket("Printer.TicketTotal", ticket, ticketext);


                // Select the Payments information
                JPaymentSelect paymentdialog = ticket.getTotal1() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected(true); //Print always
                boolean flag = true;
                //paymentdialog.setIntrestAmount(intrest);
                  ticketandIntrestTotal=ticket.getTotal1();//+intrest;
               
              if (paymentdialog.showDialog(ticketandIntrestTotal, ticket.getCustomer(), ticket.getCreatedBy(), true)) {
                
               // if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), ticket.getCreatedBy(), true)) {

                    // assign the payments selected and calculate taxes.
                    List<PaymentInfo> bp=paymentdialog.getSelectedPayments();
                    //added By Ganesh
                    if(bp.size()>1){
//                        partial=bp.size();
                        ticket.setPayments(paymentdialog.getSelectedPayments());
//                    for(PaymentInfo aa : bp){
//                        partial--;
//                    ticket.setPayments1(aa);
        
                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    
                    try {
                        flag = dlSales.payAccount4(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }
                    /////////////
                    
                    /////////
                    }
                    else{
                    
                              
                    ticket.setPayments(paymentdialog.getSelectedPayments());
        
                    // Asigno los valores definitivos del ticket...
                    ticket.setActiveCash(m_App.getActiveCashIndex());

                    // Save the receipt and assign a receipt number
                    
                    try {
                        flag = dlSales.payAccount3(ticket, m_App.getInventoryLocation());
                    } catch (Exception eData) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                        msg.show(this);
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    }
                    
                    // ended by Ganesh
                    if(ticket.getReceiptRef() != null){  //Added by ganesh
                        
                    // Print receipt.
                    if (flag == true) {
                        // String smsmsg="Dear Member,\rYour a/c with us has been debited by Rs."+Formats.ConvertDoubleToString(ticket.getTotal())+" bill no "+ticket.printId()+" for bar usage."+"Thank u for using our facility";
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket_1"
                                : "Printer.Ticket2", ticket, ticketext);
                    

                        
   /////////////////////////////////////////// ///////////////////////////////////////////                /////////////////////////////////////                     
  // Code added for billing member ..........                     
                        
                        
      String BMName="Credit Check for billing member";
      String BName2 = "Facility for billing member"; 
         
            Object[] FacObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(BMName);
            if(FacObj!=null){  
                
                Boolean v5 = (Boolean)FacObj[0];
                if(v5){

                    
                    
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+BillList.taxamt1;
                                     
                                       System.out.println("amount :" + amt);
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 
                    
                    
                }
                 else{
                    
                  
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                        // Code added if other facility is selected.........
                        /////////////////////////////////////         ////////////////////////////////////////////////////////        /////////////////////////////////////////////
                    
                        Object[] Fac2Obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BName2);
                        String FacStrFull  = Fac2Obj[0].toString();
                        String []strarr = FacStrFull.split("#");
                        String FinFacId = strarr[0];
                        
                        Object[] QtFacIdObj = (Object[]) new StaticSentence(m_App.getSession(), "select f.id  from facility f , locations l , bill b  where l.facility=f.id and b.warehouse=l.id and b.id= ?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(binfo.getID());
                        String QtFacID  = QtFacIdObj[0].toString();
                        
                        if(FinFacId.equals(QtFacID)){
                                
                                  ///aaa
                                //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                               String idt = customertemp.getId();
                                               Double amt = binfo.getAmount()+BillList.taxamt1;
                                           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                           if(obj!=null){
                                               String popb=obj[0].toString();
                                               Double POPB = new Double(popb);
                                               dlfac.roundTwoDecimals(POPB);
                                               Double COPB = POPB-amt;
                                               COPB = dlfac.roundTwoDecimals(COPB);
                                               new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                            }
                               ///aaa 
 
                        }
                    
                     
                }
                
            }                  
                
            else{
                
                         ///aaa
                        //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                                       String idt = customertemp.getId();
                                       Double amt = binfo.getAmount()+BillList.taxamt1;
                                      
                                   Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(idt);
                                   if(obj!=null){
                                       String popb=obj[0].toString();
                                       Double POPB = new Double(popb);
                                       dlfac.roundTwoDecimals(POPB);
                                       Double COPB = POPB-amt;
                                       COPB = dlfac.roundTwoDecimals(COPB);
                                       new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{COPB,idt});
                                    }
                       ///aaa 

             }
                        
             
            
            resultok = true;
                    } 
              }
                    else {
                        //new PreparedSentence(s, "DELETE FROM PAYMENTS WHERE RECEIPT=? ", SerializerWriteString.INSTANCE).exec(JPaymentSelect.intRefID);
                        resultok = false;
                    }
                }

                // reset the payment info
                ticket.resetTaxes();
                ticket.resetPayments();
            }

        // cancelled the ticket.total script
        // or canceled the payment dialog
        // or canceled the ticket.close script

        }
        return resultok;
 
    }
}
