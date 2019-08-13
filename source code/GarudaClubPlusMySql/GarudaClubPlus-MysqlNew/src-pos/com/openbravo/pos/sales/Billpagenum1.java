/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Billpagenum1.java
 *shiv created
 * Created on Dec 11, 2012, 3:41:18 PM
 */
package com.openbravo.pos.sales;

import com.lowagie.text.Rectangle;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
//import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
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
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.restaurant.BillListnum1;
import com.openbravo.pos.sales.restaurant.JIntroPageRestnum1;
import com.openbravo.pos.sales.restaurant.JTicketsBagRestaurantnum1;
import com.openbravo.pos.sales.restaurant.QTListnum1;
import com.openbravo.pos.sales.restaurant.discountPagenum1;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
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

/**
 *
 * @author swathi
 */
public class Billpagenum1 extends javax.swing.JDialog {

    private static final String PERMISSION_PAYMENT = "payment";
    private BillItemTableModel billtablemodel;
    private List<BillLineInfo> blist;
    private BillLogic blogic;
    private BillLogicApply bla;
    private DataLogicSales dlSales;
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
    public static boolean t_error = false;
     public boolean bp = true;
      public static boolean cat_bp = false;
      public static boolean cat_rp = false;
        public static boolean  cd = false;
         public static boolean cat_ps2 = false;
    // private Frame frame;

    // private JIntroPageRest m_restaurant;
    private TicketParser m_TTP;

    /** Creates new form Billpage */
    public Billpagenum1(Frame parent, DataLogicSales dlSales, BillLogicApply bla, CustomerInfo customer) {

        super(parent, true);

        this.bla = bla;
        this.blogic = LookupUtilityImpl.getInstance(null).getDataLogicBill();
        this.customertemp = customer;
        this.dlSales = dlSales;
        resultok = bla == null;
    }

    public Billpagenum1(Dialog parent, DataLogicSales dlSales, BillLogicApply bla, CustomerInfo customer) {
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

    public static Billpagenum1 getDialog(Component parent, DataLogicSales dlSales, BillLogicApply bla, CustomerInfo customer, int remainingBillCount) {

        Window window = getWindow(parent);

        Billpagenum1 mybilllogic;

        if (window instanceof Frame) {
            mybilllogic = new Billpagenum1((Frame) window, dlSales, bla, customer);
        } else {
            mybilllogic = new Billpagenum1((Dialog) window, dlSales, bla, customer);
        }
        mybilllogic.remainingBillCount = remainingBillCount;
        return mybilllogic;
    }

    public void init(BillInfo binfo) throws BasicException {
        //binfo.get
        this.binfo = binfo;
        blist = binfo.getLines();
        if (blist == null) {
            blist = blogic.getBillLineList(binfo.getID());
        }

        List<TaxInfo> taxlist = dlSales.getTaxList().list();
        taxeslogic = new BillTaxesLogic(taxlist);
        taxcollection = new ListKeyed<TaxInfo>(taxlist);

        AppView app = LookupUtilityImpl.getInstance(null).getAppView();
        dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());

        paymentdialogreceipt = JPaymentSelectReceipt.getDialog(this);
        paymentdialogreceipt.init(app);
        paymentdialogrefund = JPaymentSelectRefund.getDialog(this);
        paymentdialogrefund.init(app);
        

        initComponents();
        
        
//        jButton4.addActionListener(new ActionListener(){  //code for QT
//      public void actionPerformed(ActionEvent ae){  
//              
//       discount1();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if(e.getKeyCode() == KeyEvent.VK_F3)
//               if(!cat_bp){
//           cat_bp = true;
//           if(!BillListnum1.re_discount){
//               BillListnum1.re_discount = true;
//             discount1();
//           }
//            cat_bp = false;
//               }
//                
//          }  
//          return false;}});
//   jButton2.addActionListener(new ActionListener(){  //code for QT
//      public void actionPerformed(ActionEvent ae){  
//              
//     PRINT();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//           if((e.getKeyCode() == KeyEvent.VK_R)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
//                
//               if(!cat_rp){
//           cat_rp = true;
//           if(!BillListnum1.re_discount){
//               BillListnum1.re_discount = true;
//              PRINT();
//           }
//            cat_rp = false;
//               }
//                
//          }  
//          return false;}});
//    
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
        jButton4.setEnabled(JIntroPageRestnum1.dflag);
        jButton1.setEnabled(!JIntroPageRestnum1.dflag && !perm);
        jButton2.setEnabled(JIntroPageRestnum1.dflag);
        // boolean f=;
        if (LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter")) {
            jButton6.setEnabled(JIntroPageRestnum1.dflag && LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter"));
        } else {
            jButton6.setEnabled(JIntroPageRestnum1.dflag && LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("res counter"));
        }
        jBtnPay.setEnabled(perm);
        jButton5.setEnabled(!JIntroPageRestnum1.dflag && !perm);
        //Remove the below comment if you want to give debit permission only to bar counter
        //jButton5.setEnabled(!JIntroPageRest.dflag && LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter"));
        String[] arr = binfo.getCustomerId().split("#");
        if (arr.length > 1) {
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
        }
        //jButton1.setVisible(!perm);
        try {
            jButton1.setIcon(new ImageIcon(ImageIO.read(Billpagenum1.class.getResourceAsStream("/com/openbravo/images/cash.png"))));
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

        
       
    
   
    
    
    
  /*   jBtnPay.addActionListener(new ActionListener(){  //code for QT
     public void actionPerformed(ActionEvent ae){  
              
       Pay();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_F5) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               
                Pay();  
          }  
          return false;}}); */
   /* 
    jButton4.addActionListener(new ActionListener(){  //code for QT
   public void actionPerformed(ActionEvent ae){  
              
        Discount();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_F3) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               
                Discount();  
          }  
          return false;}}); 
    */
  /*  jButton2.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
        PRINT();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) )
               
                PRINT();  
          }  
          return false;}}); */
    
//    jButton6.addActionListener(new ActionListener(){  //code for QT
//      public void actionPerformed(ActionEvent ae){  
//              
//        CtoD();}});  
//    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
//     .addKeyEventDispatcher(new KeyEventDispatcher(){  
//        public boolean dispatchKeyEvent(KeyEvent e){  
//          if(e.getID() == KeyEvent.KEY_PRESSED)  
//          {  
//            if((e.getKeyCode() == KeyEvent.VK_F2) )
//                if(!cd){
//           cd = true;
//           if(!BillListnum1.re_discount){
//               BillListnum1.re_discount = true;
//                CtoD();
//           }
//               cd = true;}
//          }  
//          return false;}}); 
    
    jButton1.setToolTipText("CTRL C");
    jButton5.setToolTipText("CTRL D");
    jButton3.setToolTipText("ESC");
   
    
    
    }
    
    public void CtoD(){
        if(bp){
            BillListnum1.re_discount = false;
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
                                    
                                  
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
                                            new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                    //  new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{ d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getTotal(), binfo.getWaiter()});
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
                                     new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
                                            new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Integer.parseInt(getCreditConfID()),d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});
                                  
//                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
//                                            new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});
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
    }
    }
    public void PRINT(){
         if(bp){
             BillListnum1.re_discount = false;
       int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            printTicket("Printer.Ticket", binfo, binfo.getPlace());

        }
    }
    }
    
    public void  Discount(){
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
                        discountPagenum1 discount = discountPagenum1.getDialog(this, blist, bline, limit);
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
                            discountPagenum1 discount = discountPagenum1.getDialog(this, blist, new BillLineInfo(), 0);

                            String reason = discount.showDialogreason();
                            if (reason != null) {
                                for (BillLineInfo bli : blist) {
                                    //Double a=0;

                                    Integer temp = bli.getMultiply();
                                    if (temp > 0) {
                                        Double qty = Double.parseDouble(temp.toString());
                                        Double rate = bli.getRate();
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), bli.getParentid(), cname, bli.getProduct().getID(), (qty * -1), rate, reason, m_App.getAppUserView().getUser().getName(), dnow};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.TIMESTAMP})).exec(value);
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
    }

    
    
     public void UP(){
      //   int i = jTable1.getSelectedRow();
       // if(p){
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
       // }
    }
     public void setSelectedIndex(int i){
       
        // Seleccionamos
        jTable1.getSelectionModel().setSelectionInterval(i,i);

        // Hacemos visible la seleccion.
        java.awt.Rectangle oRect =jTable1.getCellRect(i, 0, true);
        jTable1.scrollRectToVisible(oRect);
    }
    
    public void DWN(){
        
//   
     // int i = jTable1.getSelectedRow();   
       // if(p){
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
    
    
    
    public void  Pay(){
         int flag = checkForBillDiscount(binfo.getID());
        if (flag == 0) {
            //  saveBill();
            resultok = true;
            // if (resultok) {
            try {
                //jBtnPay.setEnabled(false);
                closeTicket(binfo, binfo.getPlace());
                dispose();
            } catch (BasicException e) {
                e.printStackTrace();
                new MessageInf(e).show(this);
            }
        //  }
        }
    }
    public void Cancel(){
        if(bp){
        JIntroPageRestnum1.b = false;
        QTListnum1.b = false;
        dispose();
        BillListnum1.dispose_esc = true;
		}
    }
    public void Debt(){
        if(JIntroPageRestnum1.b||QTListnum1.b){
            JIntroPageRestnum1.ct_b = true;
        int flag1 = checkForBillDiscount(binfo.getID());
        if (flag1 == 0) {
            try {
                taxeslogic.calculateTaxes(bla.getBillInfo());
            } catch (Exception e) {
            }
            boolean flag = saveBill("Debt");
            if (flag == true) {
                printTicket("Printer.Ticket", binfo, binfo.getPlace());

                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                try {
                    // String name=customertemp.getName();
                    // String idt=customertemp.getId();
                    if (remainingBillCount == 0) {
                        new StaticSentence(m_App.getSession(), " DELETE FROM SHAREDTICKETS  WHERE CID = ? AND NAME = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{customertemp.getId(), customertemp.getSearchkey(), m_App.getAppUserView().getUser().getRole()});
                    }
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getAmount(), "debt");
                    l.add(p);
                    binfo.setPayments(l);
                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                    //dlSales.payDebtBill(binfo);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //if(remainingBillCount==0)
                JTicketsBagRestaurantnum1.m_rest.newTicket();
            }
        }
        }
    }
    
    public void Bill(){
        if(JIntroPageRestnum1.b||QTListnum1.b){
            JIntroPageRestnum1.ct_b = true;
            //QTListnum1.bq=true;
        int flag1 = checkForBillDiscount(binfo.getID());
        if (flag1 == 0) {
            try {
                taxeslogic.calculateTaxes(bla.getBillInfo());
            } catch (Exception e) {
            }
            boolean flag = saveBill("Cash");
            if (flag == true) {
                printTicket("Printer.Ticket", binfo, binfo.getPlace());
                dispose();
                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                try {
                    String name = customertemp.getName();
                    String idt = customertemp.getId();
                    if (remainingBillCount == 0) {
                        new StaticSentence(m_App.getSession(), " DELETE FROM SHAREDTICKETS  WHERE CID = ? AND NAME = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{customertemp.getId(), customertemp.getSearchkey(), m_App.getAppUserView().getUser().getRole()});
                    }

                } catch (Exception e) {
                }
                //  if(remainingBillCount==0)
                JTicketsBagRestaurantnum1.m_rest.newTicket();
                //dispose();
            }
           dispose();
        }
        }
    }
    public boolean showDialog() throws BasicException {
        // init(binfo);
        setVisible(true);
        JIntroPageRestnum1.b = false;
        QTListnum1.b = false;
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
                if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), ticket.getCreatedBy(), true)) {

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

                    // Print receipt.
                    if (flag == true) {
                        // String smsmsg="Dear Member,\rYour a/c with us has been debited by Rs."+Formats.ConvertDoubleToString(ticket.getTotal())+" bill no "+ticket.printId()+" for bar usage."+"Thank u for using our facility";
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket_1"
                                : "Printer.Ticket2", ticket, ticketext);
 ///aaa
 //      AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                String idt = customertemp.getId();
                    Double amt = binfo.getAmount();
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
                        resultok = true;
                    } else {
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
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(str[0].toString());
                String name = null;
                if (obj3 != null && obj3[0] != null) {
                    name = obj3[0].toString();
                }
                //praveen:added to display member's account balance
                Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                        "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? GROUP BY A.ACCOUNTID " +
                        "UNION ALL " +
                        "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? GROUP BY A.ACCOUNTID) " +
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
                String accountBalance = null;
                if (bal > 0) {
                    bal = dlfac.roundTwoDecimals(bal);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                } else {
                    bal = bal * -1;
                    bal = dlfac.roundTwoDecimals(bal);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                }
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
       
    public void discount1(){
        if(bp){
BillListnum1.re_discount = false;
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
                        discountPagenum1 discount = discountPagenum1.getDialog(this, blist, bline, limit);
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
                            discountPagenum1 discount = discountPagenum1.getDialog(this, blist, new BillLineInfo(), 0);

                            String reason = discount.showDialogreason();
                            if (reason != null) {
                                for (BillLineInfo bli : blist) {
                                    //Double a=0;

                                    Integer temp = bli.getMultiply();
                                    if (temp > 0) {
                                        Double qty = Double.parseDouble(temp.toString());
                                        Double rate = bli.getRate();
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), bli.getParentid(), cname, bli.getProduct().getID(), (qty * -1), rate, reason, m_App.getAppUserView().getUser().getName(), dnow};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.TIMESTAMP})).exec(value);
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

    }
    
    }
    private boolean saveBill(String type) {
        if (bla != null && !resultok) {
            try {
                boolean flag = false;
                if(t_error){
                flag = bla.saveBill(type);
                t_error = false;
                }
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
        m_jUp = new javax.swing.JButton();
        m_jDown = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BILL");

        jScrollPane1.setFocusable(false);

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
        jTable1.setFocusable(false);
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
        jButton2.setNextFocusableComponent(jButton6);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton4.setText("Discount");
        jButton4.setNextFocusableComponent(jButton2);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jButton6.setText("Convert to Debit");
        jButton6.setNextFocusableComponent(jButton4);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
        m_jUp.setFocusPainted(false);
        m_jUp.setFocusable(false);
        m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jUp.setVisible(false);

        m_jDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
        m_jDown.setFocusPainted(false);
        m_jDown.setFocusable(false);
        m_jDown.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDown.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
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
                        .addGap(31, 31, 31)
                        .addComponent(m_jUp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(m_jDown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnPay)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jDown)
                    .addComponent(m_jUp))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton5.addActionListener(new ActionListener(){  //code for QT
            public void actionPerformed(ActionEvent ae){

                Debt();}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_D)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))

                Debt();
            }
            return false;}});
jButton1.addActionListener(new ActionListener(){  //code for QT
    public void actionPerformed(ActionEvent ae){

        Bill();}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
public boolean dispatchKeyEvent(KeyEvent e){
    if(e.getID() == KeyEvent.KEY_PRESSED)
    {
        if((e.getKeyCode() == KeyEvent.VK_C)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))

        Bill();
    }
    return false;}});
    jButton3.addActionListener(new ActionListener(){  //code for QT
        public void actionPerformed(ActionEvent ae){

            Cancel();}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
    public boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            if((e.getKeyCode() == KeyEvent.VK_ESCAPE)  )

            Cancel();
        }
        return false;}});
jButton3.setToolTipText("ESC");
jButton2.setToolTipText("CTRL P");
jButton4.setToolTipText("F3");
java.awt.EventQueue.invokeLater(new Runnable() {

public void run() {

    jButton4.requestFocus();

    }
    });
    jButton6.setToolTipText("CTRL D");
    m_jUp.addActionListener(new ActionListener(){  //code for UP

        public void actionPerformed(ActionEvent ae){

            UP()
            ;}});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
    public boolean dispatchKeyEvent(KeyEvent e){
        if(e.getID() == KeyEvent.KEY_PRESSED)
        {
            if((e.getKeyCode() == KeyEvent.VK_PAGE_UP)  )

            UP();

        }
        return false;}});
m_jUp.setVisible(false);
m_jDown.addActionListener(new ActionListener(){  //code for down button

public void actionPerformed(ActionEvent ae){

    DWN()
    ;}});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_PAGE_DOWN)  )

                DWN();
            }
            return false;}});
m_jDown.setVisible(false);

m_jDown.setVisible(false);

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
                dispose();
                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                try {
                    String name = customertemp.getName();
                    String idt = customertemp.getId();
                    if (remainingBillCount == 0) {
                        new StaticSentence(m_App.getSession(), " DELETE FROM SHAREDTICKETS  WHERE CID = ? AND NAME = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{customertemp.getId(), customertemp.getSearchkey(), m_App.getAppUserView().getUser().getRole()});
                    }

                } catch (Exception e) {
                }
                //  if(remainingBillCount==0)
                JTicketsBagRestaurantnum1.m_rest.newTicket();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBtnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPayActionPerformed
        int flag = checkForBillDiscount(binfo.getID());
        if (flag == 0) {
            //  saveBill();
            resultok = true;
            // if (resultok) {
            try {
                //jBtnPay.setEnabled(false);
                closeTicket(binfo, binfo.getPlace());
                dispose();
            } catch (BasicException e) {
                e.printStackTrace();
                new MessageInf(e).show(this);
            }
        //  }
        }
    }//GEN-LAST:event_jBtnPayActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
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
                        discountPagenum1 discount = discountPagenum1.getDialog(this, blist, bline, limit);
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
                            discountPagenum1 discount = discountPagenum1.getDialog(this, blist, new BillLineInfo(), 0);

                            String reason = discount.showDialogreason();
                            if (reason != null) {
                                for (BillLineInfo bli : blist) {
                                    //Double a=0;

                                    Integer temp = bli.getMultiply();
                                    if (temp > 0) {
                                        Double qty = Double.parseDouble(temp.toString());
                                        Double rate = bli.getRate();
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), bli.getParentid(), cname, bli.getProduct().getID(), (qty * -1), rate, reason, m_App.getAppUserView().getUser().getName(), dnow};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.TIMESTAMP})).exec(value);
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

        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
   /*     int flag1 = checkForBillDiscount(binfo.getID());
        if (flag1 == 0) {
            try {
                taxeslogic.calculateTaxes(bla.getBillInfo());
            } catch (Exception e) {
            }
            boolean flag = saveBill("Debt");
            if (flag == true) {
                printTicket("Printer.Ticket", binfo, binfo.getPlace());

                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
                try {
                    // String name=customertemp.getName();
                    // String idt=customertemp.getId();
                    if (remainingBillCount == 0) {
                        new StaticSentence(m_App.getSession(), " DELETE FROM SHAREDTICKETS  WHERE CID = ? AND NAME = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{customertemp.getId(), customertemp.getSearchkey(), m_App.getAppUserView().getUser().getRole()});
                    }
                    List<PaymentInfo> l = new ArrayList<PaymentInfo>();
                    PaymentInfo p = new PaymentInfoTicket(binfo.getAmount(), "debt");
                    l.add(p);
                    binfo.setPayments(l);
                    printTicket("Printer.Ticket_1", binfo, "cerditconf");
                    //dlSales.payDebtBill(binfo);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //if(remainingBillCount==0)
                JTicketsBagRestaurantnum1.m_rest.newTicket();
            }
        }*/
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
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES (?, ?, ?,?,?,?,?)",
                                            new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Integer.parseInt(getCreditConfID()),  d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                    
//                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
//                                            new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                    //  new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{ d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getTotal(), binfo.getWaiter()});
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
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?,?, ?,?,?,?,?)",
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

private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
// TODO add your handling code here:
     if((evt.getKeyCode() == KeyEvent.VK_P)  && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
         int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.reprintbill"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            printTicket("Printer.Ticket", binfo, binfo.getPlace());

        }
     }
}//GEN-LAST:event_jButton2KeyPressed

private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
// TODO add your handling code here:
     if(evt.getKeyCode() == KeyEvent.VK_F3){
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
                        discountPagenum1 discount = discountPagenum1.getDialog(this, blist, bline, limit);
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
                            discountPagenum1 discount = discountPagenum1.getDialog(this, blist, new BillLineInfo(), 0);

                            String reason = discount.showDialogreason();
                            if (reason != null) {
                                for (BillLineInfo bli : blist) {
                                    //Double a=0;

                                    Integer temp = bli.getMultiply();
                                    if (temp > 0) {
                                        Double qty = Double.parseDouble(temp.toString());
                                        Double rate = bli.getRate();
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), bli.getParentid(), cname, bli.getProduct().getID(), (qty * -1), rate, reason, m_App.getAppUserView().getUser().getName(), dnow};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO REVERSEDBILL (ID, BILLID ,CUSTOMER, PRODUCT, QTY, RATE,REASON,CREATEDBY,CRDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.TIMESTAMP})).exec(value);
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

        
     }
}//GEN-LAST:event_jButton4KeyPressed

private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
// TODO add your handling code here:
   if((evt.getKeyCode() == KeyEvent.VK_D)  && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
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
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?,?, ?,?,?,?,?)",
                                            new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Integer.parseInt(getCreditConfID()),d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                   
//                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)",
//                                            new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getAmountPlusTax(), binfo.getWaiter()});//praveen
                                    //  new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{ d, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), binfo.getCustomerId(), binfo.getID(), binfo.getTotal(), binfo.getWaiter()});
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
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO CREDITCONFLIST ( ID, DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?,?, ?,?,?,?,?)",
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
}
}//GEN-LAST:event_jButton6KeyPressed
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
    private javax.swing.JButton m_jDown;
    private javax.swing.JButton m_jUp;
    // End of variables declaration//GEN-END:variables


 //added by pratima
    public String getCreditConfID() throws BasicException {
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
         Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL WHERE ID=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.DOUBLE})).find(new Object[]{"CREDITCONFLISTID"});
  if (obj != null) {
                String creditConfID =  obj[0].toString();
                 int creditConfID1=(int)Double.parseDouble(creditConfID);
                creditConfID =String.valueOf(creditConfID1);
            Double max = Double.parseDouble(obj[0].toString());
           
            max++;
            

            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{max,"CREDITCONFLISTID"});
            return creditConfID;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Credit Confirmation ID", "Cannot update Credit Confirmation table", JOptionPane.OK_OPTION);
            return null;
        }//ended by pratima
    }
}
