package com.openbravo.pos.sales;

import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Date;

import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.printer.*;

import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.panels.JProductFinder;
import com.openbravo.pos.scale.ScaleException;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ListKeyed;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPrincipalApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.panels.JProductFindernum1;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.JPaymentSelectRefund;
import com.openbravo.pos.sales.restaurant.JIntroPageRestnum1;
import com.openbravo.pos.sales.restaurant.QTList;
import com.openbravo.pos.sales.restaurant.QTListnum1;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.PrintCategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.util.JRPrinterAWT300;
import com.openbravo.pos.util.ReportUtils;
import com.openbravo.pos.util.StringUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.print.PrintService;
import javax.print.attribute.standard.PrinterName;
import javax.swing.event.EventListenerList;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author adrianromero
 */
public abstract class JPanelTicketnum1 extends JPanel implements JPanelView, BeanFactoryApp, TicketsEditor {

    // Variable numerica
  // KeyEventDispatcher myKeyEventDispatcher = new DefaultFocusManager();
   
      protected EventListenerList listeners = new EventListenerList();
    private final static int NUMBERZERO = 0;
    private final static int NUMBERVALID = 1;
    private final static int NUMBER_INPUTZERO = 0;
    private final static int NUMBER_INPUTZERODEC = 1;
    private final static int NUMBER_INPUTINT = 2;
    private final static int NUMBER_INPUTDEC = 3;
    private final static int NUMBER_PORZERO = 4;
    private final static int NUMBER_PORZERODEC = 5;
    private final static int NUMBER_PORINT = 6;
    private final static int NUMBER_PORDEC = 7;
    protected JTicketLines1 m_ticketlines;
    // private Template m_tempLine;
    private TicketParser m_TTP;
    protected TicketInfo m_oTicket;
    protected Object m_oTicketExt;
    // Estas tres variables forman el estado...
    private int m_iNumberStatus;
    private int m_iNumberStatusInput;
    private int m_iNumberStatusPor;
    private StringBuffer m_sBarcode;
    private JTicketsBagnum1 m_ticketsbag;
    private SentenceList senttax;
    private ListKeyed taxcollection;
    // private ComboBoxValModel m_TaxModel;
    private SentenceList senttaxcategories;
    private ListKeyed taxcategoriescollection;
    private ComboBoxValModel taxcategoriesmodel;
    private TaxesLogic taxeslogic;
//    private ScriptObject scriptobjinst;
    protected JPanelButtonsnum1 m_jbtnconfig;
    protected AppView m_App;
    protected DataLogicSystem dlSystem;
    protected DataLogicSales dlSales;
    protected DataLogicCustomers dlCustomers;
    protected DataLogicReceipts dlReceipts;
    protected Qticket qTicket;
    private JPaymentSelect paymentdialogreceipt;
    private JPaymentSelect paymentdialogrefund;
    public static boolean ct_t = true;
    public boolean t = true;
     protected String name;

    

    /** Creates new form JTicketView */
    public JPanelTicketnum1() {

        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {
       
        
        m_App = app;
        dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlCustomers = (DataLogicCustomers) m_App.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dlReceipts = (DataLogicReceipts) app.getBean("com.openbravo.pos.sales.DataLogicReceipts");

        qTicket = (Qticket) m_App.getBean("com.openbravo.pos.sales.Qticket");
        qTicket.setDataLogicSales(dlSales);
        qTicket.setAppView(m_App);

        // borramos el boton de bascula si no hay bascula conectada
        if (!m_App.getDeviceScale().existsScale()) {
            m_jbtnScale.setVisible(false);
        }

        m_ticketsbag = getJTicketsBag();
        m_jPanelBag.add(m_ticketsbag.getBagComponent(), BorderLayout.LINE_START);
        add(m_ticketsbag.getNullComponent(), "null");

        m_ticketlines = new JTicketLines1(dlSystem.getResourceAsXML("Ticket.Line"));
        m_jPanelCentral.add(m_ticketlines, java.awt.BorderLayout.CENTER);

        m_TTP = new TicketParser(m_App.getDeviceTicket(), dlSystem);

        // Los botones configurables...
        m_jbtnconfig = new JPanelButtonsnum1("Ticket.Buttons", this);
        m_jButtonsExt.add(m_jbtnconfig);

        // El panel de los productos o de las lineas...        
        catcontainer.add(getSouthComponentnum(), BorderLayout.CENTER);

        // El modelo de impuestos
        senttax = dlSales.getTaxList();
        senttaxcategories = dlSales.getTaxCategoriesList();

        taxcategoriesmodel = new ComboBoxValModel();

        // ponemos a cero el estado
        stateToZero();

        // inicializamos
        m_oTicket = null;
        m_oTicketExt = null;
     // JIntroPageRestnum1. ct_b = false;
        
        m_prTicket.addActionListener(new ActionListener(){  //code for t
      public void actionPerformed(ActionEvent ae){  
        doSomethings();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_T ) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
                if(t){
                    t=false;
                doSomethings();
                t=true;
                }
            }
          }  
          return false;}});  

    
   
    btnSplit.addActionListener(new ActionListener(){ //code for cut 
      public void actionPerformed(ActionEvent ae){  
              
        doSomething();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_X)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
               
             if(t){
              t=false;
                doSomething();  
              t=true;
                }
          }
          }  
          return false;}});  

    
    m_jDelete.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent ae){  
              
        doDelete();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  //code for delete
           
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_DELETE) )
               
                doDelete();  
          }  
          return false;}}); 
    
    m_jList.addActionListener(new ActionListener(){  //code for search
      public void actionPerformed(ActionEvent ae){  
        try{      
          doSearch();
        }catch(BasicException e){
            
        }
        
        
        }
    
    });  
    
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_SPACE) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               
                if(t){
                    t=false;
                
                    try{
                    doSearch();
                    }catch(BasicException E){
                        
                    }
                t=true;
                }  
          }  
          return false;}}); 
    
    jButton1.addActionListener(new ActionListener(){  //code for QT
      public void actionPerformed(ActionEvent ae){  
              
        doQT();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_Q)  && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
               
                doQT();  
          }  
          return false;}}); 
    
    m_jEditLine.addActionListener(new ActionListener(){  //code for Remark
      public void actionPerformed(ActionEvent ae){  
              
        doRemark();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if((e.getKeyCode() == KeyEvent.VK_W) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) 
               
                if(t){
                    t=false;
                    JIntroPageRestnum1.remark = false;
                doRemark();  
            t=true;
                }  
          }  
          return false;}}); 
    
   m_jUp.addActionListener(new ActionListener(){  //code for Up button
      public void actionPerformed(ActionEvent ae){  
              
        moveUP();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if(e.getKeyCode() == KeyEvent.VK_UP) 
               
             moveUP();  
          }  
          return false;}}); 
    
    
    m_jDown.addActionListener(new ActionListener(){  //code for Down button
      public void actionPerformed(ActionEvent ae){  
              
       moveDown();}});  
    KeyboardFocusManager.getCurrentKeyboardFocusManager()  
     .addKeyEventDispatcher(new KeyEventDispatcher(){  
        public boolean dispatchKeyEvent(KeyEvent e){  
          if(e.getID() == KeyEvent.KEY_PRESSED)  
          {  
            if(e.getKeyCode() == KeyEvent.VK_DOWN) 
               
             moveDown();  
          }  
          return false;}}); 
    
     btnSplit.setToolTipText("QT SPLIT(CTRL X)");
     jButton1.setToolTipText("QT(CTRL Q)");
     m_prTicket.setToolTipText("QT List(CTRL T)");
     m_jList.setToolTipText("Find Product(CTRL SPACEBAR)");
     m_jDelete.setToolTipText("Remove(DELETE)");
     m_jEditLine.setToolTipText("CTRL W");
     m_jUp.setToolTipText("Up");
     m_jDown.setToolTipText("Down");
     jTextField2.setFocusable(false);
     jTextField1.setFocusable(true);
     
    
     }
    
     public void  moveUP()//UP method
  {    m_ticketlines.selectionUp();
     }
    
    
    public void  moveDown()//Down method
  {     m_ticketlines.selectionDown();
        
    }
    
    
    
     public void doRemark()//Remark method
  {  
          int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            TicketLineInfo oLine = m_oTicket.getLine(i);
            if (JProductLineRemarkEditnum1.showMessage(this, m_App, m_oTicket.getLine(i))) {
                // se ha modificado la linea
                paintTicketLine(i, oLine);
            }
        }
     }
    
    
   
    
    
     public void doQT()//QT method
     {  if(JIntroPageRestnum1.ctlk){
       ct_t = false;
     	int x=0;
                Object[]stk=new Object[25];
        try {
            boolean berror = false;
            Date date = new Date();
            AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
            if (obj != null) {
                if (obj[0] != null) {
                    Date d = (Date) obj[0];
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.setTimeInMillis(date.getTime());
                    cal2.setTimeInMillis(d.getTime());
                    if (cal1.before(cal2)) {
                        if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                            berror = true;
                        } else {
                            new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                        }
                    }
                }
            }
            if (berror == false && m_oTicket.getLinesCount() > 0) {
                // temp=1;
                TicketInfo ticket1 = m_oTicket.copyTicket();
                TicketInfo ticket2 = new TicketInfo();
                ticket2.setCustomer(m_oTicket.getCustomer());
                ticket2.setUser(ticket1.getUser());
                ticket2.setPlace(ticket1.getPlace());
                ticket2.setWaiter(ticket1.getWaiter());
                ticket2.setFloor(ticket1.getFloor());

                int count = m_oTicket.getLinesCount();
                int j = 0;
                int size = ticket1.getLines().size();
                // Boolean temp=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("StockCheckNotRequired");

                // if(!temp)
                while (j < size) {
                    TicketLineInfo tl = ticket1.getLine(j);
//                    System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + tl.isStockCheckRequired());
//                    Object stockcheck = new PreparedSentence(m_App.getSession(), "SELECT INVENTRYMAINTAIN FROM PRODUCTS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(tl.getProductID().toString());
//                    System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + Boolean.valueOf(stockcheck.toString()));

                    if (tl.isStockCheckRequired()) {
                        Object o = dlSales.getStockVolume(tl.getProductID());
                        stk[j] = o;
                        Double sqty = 0.0;
                        if (o != null) {
                            sqty = Double.parseDouble(o.toString());
                        }
                        if (sqty >= tl.getMultiply()) {
                            dlSales.updateStockVolume(-tl.getMultiply(), tl.getProductID());
                            dlSales.updateStockVolume1(-tl.getMultiply(), tl.getProductID());
                            j++;
                        } else {
                            if (sqty == 0.0) {
		//						x=1;
                                JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "QT quantity Exceed the quantity in stock for \"" + tl.getProductName() + " \"", "Cannot Prepare QT", JOptionPane.WARNING_MESSAGE);
                            }
                            ticket1.deleteLine(j);
                            //j--;
                            for(int i=j;i<size;i++){
                                stk[i]=stk[i++];
                            }
                            
                            size--;
                        }
                    } else {
                        j++;
                    }
            }   
                
////////aaa..Start
                
  //           if(x==0){      
             Double amt=0.0;
             //////////////////////////////////////shiv
             System.out.println(ticket1.getTax());
             
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name);
                         if(obj4[0].equals("yes")){
                              ticket1.getTax();
                              Object f= new Float(Math.round(ticket1.getTax()));
                              String st= f.toString();
                              Double taxst= Double.parseDouble(st); 
                              amt=ticket1.getSubTotal()+taxst;
                              System.out.println(taxst);
                             
                         }else if(obj4[0].equals("yesnearest")){
                              ticket1.getTax();
                              System.out.println(ticket1.getTax());
                                Object f= new Float(Math.round(ticket1.getTax()));
                                 String st= f.toString();
                                  Double taxst= Double.parseDouble(st); 
                                  amt=ticket1.getSubTotal()+taxst;
                                  System.out.println(taxst);
                         }else if(obj4[0].equals("yesnext")){
                              ticket1.getTax();
                              System.out.println(ticket1.getTax());
                                  Object f= new Float(Math.round(ticket1.getTax())+1);
                                  String st= f.toString();
                                   Double taxst= Double.parseDouble(st); 
                                  amt=ticket1.getSubTotal()+taxst;
                                  System.out.println(taxst);
                          }else if(obj4[0].equals("yesprevious")){
                               ticket1.getTax();
                               System.out.println(ticket1.getTax());
                                   Object f= new Float(Math.round(ticket1.getTax())-1);
                                   String st= f.toString();
                                   Double taxst= Double.parseDouble(st); 
                                   amt=ticket1.getSubTotal()+taxst;
                                   System.out.println(taxst);
                          }else{
                              
                                amt=ticket1.getTotal();
                                 
                            }
               
        //        String a = m_oTicket.getCustomerId();
        //        String a1 = a.substring(0,36);
                 
                                  
                   //////////////////////////////////////////////////////shiv
        //        String a = m_oTicket.getCustomerId();
        //        String a1 = a.substring(0,36);
                
                String cust=m_oTicket.getCustomerId();
                String id=cust;
                
       Object[] objm = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MEMTYPE FROM CUSTOMERS where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id.toString());
    //    String memtype = objm[0].toString();
     
        if(objm==null){
             String a = m_oTicket.getCustomerId();
                String a1 = a.substring(0,36);
                String gname ="Credit check for Guests";
                Object[] gobjm = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MEMTYPE FROM CUSTOMERS where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(a1.toString());
                String memtype = gobjm[0].toString();
           
            Object[] gobj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id.toString());
                String gopb=gobj1[0].toString();
                   Double GOPB = new Double(gopb);
                   roundTwoDecimals(GOPB);
                    Double GSum = GOPB+amt;
                    GSum=roundTwoDecimals(GSum);
                    Object[] gobjqt = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM generaltable where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(gname);
                    Boolean GQTcheck = (Boolean)gobjqt[0];
                    
           if(GQTcheck==true){///Start of check
                        
          Object[] gobj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT DEBTMAX FROM memtype where id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memtype);
                     String dMax = gobj2[0].toString();
                  Double debtMax = new Double(dMax);
                  if((GSum>debtMax)){
                      //make a method for msg
              //to overcome the products reduction
                int s = 0;
                while (s < size) {
                    TicketLineInfo tl = ticket1.getLine(s);
                    if (tl.isStockCheckRequired()) {
                    //    Object o = dlSales.getStockVolume(tl.getProductID());
                        Double sqty = 0.0;
                        if (stk[s] != null) {
                            sqty = Double.parseDouble(stk[s].toString());
                        }
                        if (sqty >= tl.getMultiply()) {
                            dlSales.updateStockVolume(tl.getMultiply(), tl.getProductID());
                            dlSales.updateStockVolume1(tl.getMultiply(), tl.getProductID());
                            s++;
                        }
                    }else{
                        s++;
                    }
                }//end of while
             JOptionPane.showMessageDialog(this, "Please clear the Balance.Cannot prepare QT for it", "Amount limit Exceeded", JOptionPane.WARNING_MESSAGE);
         
                  }else{
                      amountUpdate(GSum,cust,ticket1,ticket2); 
                  }
           }else{
               amountUpdate(GSum,cust,ticket1,ticket2); 
           }
        }else{
        
        String memtype = objm[0].toString();
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id.toString());
                String opb=obj1[0].toString();
                   Double OPB = new Double(opb);
                   roundTwoDecimals(OPB);
                    Double Sum = OPB+amt;
                    Sum=roundTwoDecimals(Sum);
                   String name ="Credit check for QT";
          Object[] objqt = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM generaltable where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(name);
                    Boolean QTcheck = (Boolean)objqt[0];
                    
           if(QTcheck==true){///Start of check
                        
          Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT DEBTMAX FROM memtype where id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memtype);
                     String dMax = obj2[0].toString();
                  Double debtMax = new Double(dMax);
           if((Sum>debtMax)){///LimitCross>>>Start
                //to overcome the products reduction
                int s = 0;
                while (s < size) {
                    TicketLineInfo tl = ticket1.getLine(s);
                    if (tl.isStockCheckRequired()) {
                    //    Object o = dlSales.getStockVolume(tl.getProductID());
                        Double sqty = 0.0;
                        if (stk[s] != null) {
                            sqty = Double.parseDouble(stk[s].toString());
                        }
                        if (sqty >= tl.getMultiply()) {
                            dlSales.updateStockVolume(tl.getMultiply(), tl.getProductID());
                            dlSales.updateStockVolume1(tl.getMultiply(), tl.getProductID());
                            s++;
                        }
                    }else{
                        s++;
                    }
                }//end of while
             JOptionPane.showMessageDialog(this, "Please clear the Balance.Cannot prepare QT for it", "Amount limit Exceeded", JOptionPane.WARNING_MESSAGE);
         }else{
               amountUpdate(Sum,cust,ticket1,ticket2);
             }//End of else //if(Sum>5000)End
            }else{//if(gsa.checkQT()==true){////End of check
                        amountUpdate(Sum,cust,ticket1,ticket2);
                }
            }
//        } /////End of If(x==0);//Just to overcome the again initialization of printTotal
////////aaa..End
            } else {
                if (berror == true) {
                    JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot login", JOptionPane.OK_OPTION);
                }
            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreateqt"), e);
            msg.show(this);
        }  finally{
        ct_t = true;
 //       JIntroPageRestnum1.ct_p = false;
//        if(!JIntroPageRestnum1.ct_p){
//     JIntroPageRestnum1.ct_p = true;
//        }
     }

     }   
     }
    
    public void doSearch()  throws BasicException//search method
  {  
          if(JIntroPageRestnum1.ctlk){
                ct_t = false;
     ProductInfoExt prod = JProductFindernum1.showMessage(JPanelTicketnum1.this, dlSales);
        if (prod != null) {
            buttonTransition(prod);
        }
          ct_t = true;
          }
  }

      public void doDelete()// delete method
  {  
       int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            removeTicketLine(i); // elimino la linea           
        }
  }
    
    
    public void doSomethings()//t method
  {  
        if(JIntroPageRestnum1.ctlk){
       ct_t = false;
  //     JIntroPageRestnum1.ctl_bill = true;
 //      JIntroPageRestnum1.ct_p = true;
       QTListnum1 qtList = QTListnum1.getDialog(this, m_App, dlSales, qTicket);
        try {
            boolean flag = qtList.showDialog(m_oTicket.getCustomer());
  //          JIntroPageRestnum1.ctl_bill = false;
        } catch (BasicException ex) {
            new MessageInf(ex).show(this);
        }
   // JOptionPane.showMessageDialog(this,"T button pressed something...");  
        finally{
        ct_t = true;
 //       JIntroPageRestnum1.ct_p = false;
//        if(!JIntroPageRestnum1.ct_p){
//     JIntroPageRestnum1.ct_p = true;
//        }
     }
        }
  }  
     
    public void doSomething()//cut method
  {  if(JIntroPageRestnum1.ctlk){
       ct_t = false;
   try {
            boolean berror = false;
            if (m_oTicket.getLinesCount() > 0) {
               ReceiptSplitnum1 splitdialog = ReceiptSplitnum1.getDialog(this, dlSystem.getResourceAsXML("Ticket.Line"), dlSales, dlCustomers, taxeslogic);

                TicketInfo ticket1 = m_oTicket.copyTicket();
                TicketInfo ticket2 = new TicketInfo();
                ticket2.setCustomer(m_oTicket.getCustomer());
                ticket2.setUser(ticket1.getUser());
                ticket2.setPlace(ticket1.getPlace());
                ticket2.setWaiter(ticket1.getWaiter());
                ticket2.setFloor(ticket1.getFloor());

                if (splitdialog.showDialog(ticket1, ticket2, m_oTicketExt)) {

                    Date date = new Date();
                    AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                                    berror = true;
                                } else {
                                    new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                                }
                            }
                        }
                    }
                    if (berror == false) {
                        int ticketsize = ticket2.getLines().size();
                        int j = 0;
                        // Boolean temp=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("StockCheckNotRequired");

                        // if(!temp)
                        while (j < ticket2.getLines().size()) {
                            TicketLineInfo tl = ticket2.getLine(j);

                            System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + tl.isStockCheckRequired());
                            Object stockcheck = new PreparedSentence(m_App.getSession(), "SELECT INVENTRYMAINTAIN FROM PRODUCTS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(tl.getProductID().toString());
                            System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + Boolean.valueOf(stockcheck.toString()));

                            if (tl.isStockCheckRequired()) {
                                // if(tl.getProductCategoryID()){
                                Object o = dlSales.getStockVolume(tl.getProductID());
                                Double sqty = 0.0;
                                if (o != null) {
                                    sqty = Double.parseDouble(o.toString());
                                }
                                if (sqty > 0) {
                                    dlSales.updateStockVolume(-tl.getMultiply(), tl.getProductID());
                                    dlSales.updateStockVolume1(-tl.getMultiply(), tl.getProductID());
                                    j++;
                                } else {
                                    JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);

                                    ticket2.deleteLine(j);
                                }
                            }
                            j++;
                        //   }

                        }
                        createQTicket(ticket2);
//                if (closeTicket(ticket2, m_oTicketExt)) { // already checked  that number of lines > 0
                        setActiveTicket(ticket1, m_oTicketExt);// set result ticket
                        dlReceipts.updateSharedTicket(ticket1.getCustomerId(), ticket1, m_App.getAppUserView().getUser().getRole());
                    //  dlReceipts.updateLastQtTimeOfSharedTicket(new Date(),ticket1.getCustomerId(), m_App.getAppUserView().getUser().getRole());
                    } else {
                        // if(berror==true){
                        JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot Create QT", JOptionPane.OK_OPTION);
                    // }
                    }
                }
                //praveen:exit for every transaction for kiosk mode---start
                if(m_App.getAppUserView().getUser().getTypeOfUser()==1)
                JPrincipalApp.m_approot.closeAppView();
                //praveen:exit for every transaction for kiosk mode---end

            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreateqt"), e);
            msg.show(this);
        } finally{
        ct_t = true;
 //       JIntroPageRestnum1.ct_p = false;
//        if(!JIntroPageRestnum1.ct_p){
//     JIntroPageRestnum1.ct_p = true;
//        }
     }
  // else
      //  btnSplit1.transferFocus();
   // JOptionPane.showMessageDialog(this,"Cut buton pressed.........");
     }
    }
    

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }
///aaa  
    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return new Double(twoDForm.format(d)).doubleValue();
    }
    public void activate() throws BasicException {
        //Action action = new AbstractAction("Do It") {

//            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//};
//        JPanel pnl = new JPanel();
//        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
//                  pnl.getActionMap().put("Do It", action);
//          pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "Do It");
//         JMenuItem item = new JMenuItem();
//              KeyStroke key = KeyStroke.getKeyStroke(
//                KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
//                item.setAccelerator(key);
////                  JPanel.add(item);
    
       
        paymentdialogreceipt = JPaymentSelectReceipt.getDialog(this);
        paymentdialogreceipt.init(m_App);
        paymentdialogrefund = JPaymentSelectRefund.getDialog(this);
        paymentdialogrefund.init(m_App);

        // impuestos incluidos seleccionado ?
        m_jaddtax.setSelected("true".equals(m_jbtnconfig.getProperty("taxesincluded")));

        // Inicializamos el combo de los impuestos.
        java.util.List<TaxInfo> taxlist = senttax.list();
        taxcollection = new ListKeyed<TaxInfo>(taxlist);
        java.util.List<TaxCategoryInfo> taxcategorieslist = senttaxcategories.list();
        taxcategoriescollection = new ListKeyed<TaxCategoryInfo>(taxcategorieslist);

        taxcategoriesmodel = new ComboBoxValModel(taxcategorieslist);
        m_jTax.setModel(taxcategoriesmodel);

        String taxesid = m_jbtnconfig.getProperty("taxcategoryid");
        if (taxesid == null) {
            if (m_jTax.getItemCount() > 0) {
                m_jTax.setSelectedIndex(0);
            }
        } else {
            taxcategoriesmodel.setSelectedKey(taxesid);
        }


        taxeslogic = new TaxesLogic(taxlist);

        // Show taxes options
        if (m_App.getAppUserView().getUser().hasPermission("sales.ChangeTaxOptions")) {
            m_jTax.setVisible(true);
            m_jaddtax.setVisible(true);
        } else {
            m_jTax.setVisible(false);
            m_jaddtax.setVisible(false);
        }

        // Authorization for buttons
        //btnSplit.setEnabled(m_App.getAppUserView().getUser().hasPermission("sales.Total"));
        btnSplit.setEnabled(true);
        m_jDelete.setEnabled(m_App.getAppUserView().getUser().hasPermission("sales.EditLines"));
    //  jPanel12.setMinusEnabled(m_App.getAppUserView().getUser().hasPermission("sales.EditLines"));
    //   jPanel12.setEqualsEnabled(m_App.getAppUserView().getUser().hasPermission("sales.Total"));
    //   jPanel12.setEqualsEnabled(true);
        m_jbtnconfig.setPermissions(m_App.getAppUserView().getUser());

        m_ticketsbag.activate();
    }

    public boolean deactivate() {

        return m_ticketsbag.deactivate();

    }

    protected abstract JTicketsBagnum1 getJTicketsBag();

    protected abstract Component getSouthComponentnum();

    public void setActiveTicket(TicketInfo oTicket, Object oTicketExt) {

        m_oTicket = oTicket;
        m_oTicketExt = oTicketExt;

        executeEvent(m_oTicket, m_oTicketExt, "ticket.show");

        refreshTicket();
    }

    public TicketInfo getActiveTicket() {
        return m_oTicket;
    }

    private void refreshTicket() {

     CardLayout cl = (CardLayout) (getLayout());

        if (m_oTicket == null) {
            m_jTicketId.setText(null);
            m_ticketlines.clearTicketLines();

            m_jSubtotalEuros.setText(null);
            m_jTaxesEuros.setText(null);
            m_jTotalEuros.setText(null);

            stateToZero();

            // Muestro el panel de nulos.
            cl.show(this, "null");

        } else {

            // Refresh ticket taxes
            for (TicketLineInfo line : m_oTicket.getLines()) {
                line.setTaxInfo(taxeslogic.getTaxInfo(line.getProductTaxCategoryID(), m_oTicket.getCustomer()));
            }

            // The ticket name
            m_jTicketId.setText(m_oTicket.getName(m_oTicketExt));

///aaa
  jTextField2.setRequestFocusEnabled(false);//*imp*
       
       jTextField2.setBackground(java.awt.Color.white);
        jTextField2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTextField2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        jTextField2.setOpaque(true);
        jTextField2.setPreferredSize(new java.awt.Dimension(150, 25));
        
        
        Font font = new Font("Times New Roman", Font.BOLD, 13);
            jTextField2.setFont(font);
       
    String cust=m_oTicket.getCustomerId();
    String id=cust;
     
    try{
       Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id.toString());
        String opb = obj1[0].toString();
                   Double OPB = new Double(opb);
                   roundTwoDecimals(OPB);
                    if(OPB>0){
                         jTextField2.setForeground(Color.red);
                        jTextField2.setText("Dr.  "+OPB);
                           }
                     else if(OPB<0){
                         jTextField2.setForeground(Color.green);
                         OPB = OPB*(-1);
                         jTextField2.setText("Cr.  "+OPB);
                             }
                      else{jTextField2.setForeground(Color.black);
                            jTextField2.setText(""+OPB);
                            }
///aaa                       
              }catch(Exception e){
                       e.printStackTrace();
                                  }
            // Limpiamos todas las filas y anadimos las del ticket actual
            m_ticketlines.clearTicketLines();

            for (int i = 0; i < m_oTicket.getLinesCount(); i++) {
                m_ticketlines.addTicketLine(m_oTicket.getLine(i));
            }
            printPartialTotals();
            stateToZero();

            // Muestro el panel de tickets.
            cl.show(this, "ticket");

            // activo el tecleador...
            m_jKeyFactory.setText(null);
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    m_jKeyFactory.requestFocus();
                }
            });
        }
    }

      
    
    private void printPartialTotals() {

        if (m_oTicket.getLinesCount() == 0) {
            m_jSubtotalEuros.setText(null);
            m_jTaxesEuros.setText(null);
            m_jTotalEuros.setText(null);
        } else {
            
              
              m_jSubtotalEuros.setText(m_oTicket.printSubTotal());
              //  m_jTotalEuros.setText(m_oTicket.printTotal());
                System.out.println(m_oTicket.getLines());
                m_oTicket.getLinesCount();
                System.out.println(m_oTicket.getLine(0).getTaxInfo().getId());
                /////i got struck here
         
                String gett=m_oTicket.getLine(0).getTaxInfo().getId();
            ArrayList<TicketLineInfo> arrayList = (ArrayList<TicketLineInfo>) m_oTicket.getLines();
                for (int i = 0; i <  arrayList.size(); i++) {
                      Object st2=arrayList.size();
                      int ib = (Integer)st2;
                      int ib1=ib-1;
                      String st1= m_oTicket.getLine(ib1).getTaxInfo().getId();
                      name=st1;
                      
                       
                try {
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(st1);
                         if(obj[0].equals("yes")){
                  System.out.println(obj[0]);
                  System.out.println("yes");
                      
                double str =m_oTicket.getTax();
                System.out.println(new Float( Math.round(str)));
                Object f= new Float(Math.round(str));
                String st= f.toString();
                m_jTaxesEuros.setText(Formats.CURRENCY.formatValue(new Double(st)));
                double str1=m_oTicket.getSubTotal();
                double str2=str1+Math.round(str);
                Object o1= new Double(str2);
                o1.toString();
                 m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(o1.toString())));
                 
                  
                
               }else if(obj[0].equals("yesnearest")){
                   double str =m_oTicket.getTax();
                System.out.println(new Float( Math.round(str)));
                Object f= new Float(Math.round(str));
                String st= f.toString();
                m_jTaxesEuros.setText(Formats.CURRENCY.formatValue(new Double(st)));
                double str1=m_oTicket.getSubTotal();
                double str2=str1+Math.round(str);
                Object o1= new Double(str2);
                o1.toString();
                 m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(o1.toString())));
             
                    
                }else if(obj[0].equals("yesnext")){
                    double str =m_oTicket.getTax();
                System.out.println(new Float( Math.round(str)+1));
                Object f= new Float(Math.round(str)+1);
                String st= f.toString();
                m_jTaxesEuros.setText(Formats.CURRENCY.formatValue(new Double(st)));
                double str1=m_oTicket.getSubTotal();
                double str2=str1+Math.round(str)+1;
                Object o1= new Double(str2);
                o1.toString();
                 m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(o1.toString())));
               
                    
                }else if(obj[0].equals("yesprevious")){
                    double str =m_oTicket.getTax();
                System.out.println(new Float( Math.round(str)));
                Object f= new Float(Math.round(str)-1);
                String st= f.toString();
                m_jTaxesEuros.setText(Formats.CURRENCY.formatValue(new Double(st)));
                double str1=m_oTicket.getSubTotal();
                double str2=str1+Math.round(str)-1;
                Object o1= new Double(str2);
                o1.toString();
                 m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(o1.toString())));
                  
                    
                  }
                         else{
                  System.out.println("no");
                  System.out.println(obj[0]);
                  m_jSubtotalEuros.setText(m_oTicket.printSubTotal());
                  m_jTaxesEuros.setText(m_oTicket.printTax());
                  m_jTotalEuros.setText(m_oTicket.printTotal());
               }
                } catch (BasicException ex) {
                    Logger.getLogger(JPanelTicket.class.getName()).log(Level.SEVERE, null, ex);
                }
                  } 
          
             
              
               
               }
    }

    private void paintTicketLine(int index, TicketLineInfo oLine) {

        if (executeEventAndRefresh("ticket.setline", new ScriptArg("index", index), new ScriptArg("line", oLine)) == null) {

            m_ticketlines.setTicketLine(index, oLine);
            m_ticketlines.setSelectedIndex(index);

            visorTicketLine(oLine); // Y al visor tambien...
            printPartialTotals();
            stateToZero();

            // event receipt
            executeEventAndRefresh("ticket.change");
            
            
            
        }
    }

    private void addTicketLine(ProductInfoExt oProduct, double dMul, double dPrice) throws BasicException{

        TaxInfo tax = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo(), m_oTicket.getCustomer());

        
        TaxInfo tax2 = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo2(), m_oTicket.getCustomer());                                                             // edited by aakash
        TaxInfo tax3 = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo3(), m_oTicket.getCustomer());
        
        Boolean Basic2 = oProduct.getBASIC2();
        Boolean Basic3 = oProduct.getBASIC3();
        // edited by aakash foe kitchen products ............................................................................
        
         String Productid=oProduct.getID();
         Object[] prodDeac = (Object[]) new StaticSentence(m_App.getSession(), "SELECT PRODUCTID FROM deactiveproduct where PRODUCTID=? AND ACTIVE=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Productid);
         if(prodDeac!=null){
               JOptionPane.showMessageDialog(null, "Product deactivated for the day... \n Sorry cannot create QT.", "Warning", JOptionPane.OK_OPTION);  
         }
         else{ 
        
        addTicketLine(new TicketLineInfo(oProduct, dMul, dPrice, tax, (java.util.Properties) (oProduct.getProperties().clone()) , tax2 , tax3 , Basic2 , Basic3  ));
    
       }
    }

    protected void addTicketLine(TicketLineInfo oLine) {

        if (executeEventAndRefresh("ticket.addline", new ScriptArg("line", oLine)) == null) {

            if (oLine.isProductCom()) {
                // Comentario entonces donde se pueda
                int i = m_ticketlines.getSelectedIndex();

                // me salto el primer producto normal...
                if (i >= 0 && !m_oTicket.getLine(i).isProductCom()) {
                    i++;
                }

                // me salto todos los productos auxiliares...
                while (i >= 0 && i < m_oTicket.getLinesCount() && m_oTicket.getLine(i).isProductCom()) {
                    i++;
                }

                if (i >= 0) {
                    m_oTicket.insertLine(i, oLine);
                    m_ticketlines.insertTicketLine(i, oLine); // Pintamos la linea en la vista...                 
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            } else {
                // Producto normal, entonces al final
                m_oTicket.addLine(oLine);
                m_ticketlines.addTicketLine(oLine); // Pintamos la linea en la vista... 
            }

            visorTicketLine(oLine);
            printPartialTotals();
            stateToZero();

            // event receipt
            executeEventAndRefresh("ticket.change");
        }
    }

    private void removeTicketLine(int i) {

        if (executeEventAndRefresh("ticket.removeline", new ScriptArg("index", i)) == null) {

            if (m_oTicket.getLine(i).isProductCom()) {
                // Es un producto auxiliar, lo borro y santas pascuas.
                m_oTicket.removeLine(i);
                m_ticketlines.removeTicketLine(i);
            } else {
                // Es un producto normal, lo borro.
                m_oTicket.removeLine(i);
                m_ticketlines.removeTicketLine(i);
                // Y todos lo auxiliaries que hubiera debajo.
                while (i < m_oTicket.getLinesCount() && m_oTicket.getLine(i).isProductCom()) {
                    m_oTicket.removeLine(i);
                    m_ticketlines.removeTicketLine(i);
                }
            }

            visorTicketLine(null); // borro el visor 
            printPartialTotals(); // pinto los totales parciales...                           
            stateToZero(); // Pongo a cero    

            // event receipt
            executeEventAndRefresh("ticket.change");
        }
    }

    private ProductInfoExt getInputProduct() {
        ProductInfoExt oProduct = new ProductInfoExt(); // Es un ticket
        oProduct.setReference(null);
        oProduct.setCode(null);
        oProduct.setName("");
        oProduct.setTaxCategoryInfo((TaxCategoryInfo) taxcategoriesmodel.getSelectedItem());

        oProduct.setPriceSell(includeTaxes(oProduct.getTaxCategoryInfo(), getInputValue()));

        return oProduct;
    }

    private double includeTaxes(TaxCategoryInfo tc, double dValue) {
        if (m_jaddtax.isSelected()) {
            TaxInfo tax = taxeslogic.getTaxInfo(tc, m_oTicket.getCustomer());
            double dTaxRate = tax == null ? 0.0 : tax.getRate();
            return dValue / (1.0 + dTaxRate);
        } else {
            return dValue;
        }
    }

    private double getInputValue() {
        try {
            return Double.parseDouble(m_jPrice.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private double getPorValue() {
        try {
            return Double.parseDouble(m_jPor.getText().substring(1));
        } catch (NumberFormatException e) {
            return 1.0;
        } catch (StringIndexOutOfBoundsException e) {
            return 1.0;
        }
    }

    private void stateToZero() {
        m_jPor.setText("");
        m_jPrice.setText("");
        m_sBarcode = new StringBuffer();

        m_iNumberStatus = NUMBER_INPUTZERO;
        m_iNumberStatusInput = NUMBERZERO;
        m_iNumberStatusPor = NUMBERZERO;
    }

    private void incProductByCode(String sCode) {
        // precondicion: sCode != null

        try {
            ProductInfoExt oProduct = dlSales.getProductInfoByCode(sCode);
            if (oProduct == null) {
                Toolkit.getDefaultToolkit().beep();
                new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noproduct")).show(this);
                stateToZero();
            } else {
                // Se anade directamente una unidad con el precio y todo
                incProduct(oProduct);
            }
        } catch (BasicException eData) {
            stateToZero();
            new MessageInf(eData).show(this);
        }
    }

    private void incProductByCodePrice(String sCode, double dPriceSell) {
        // precondicion: sCode != null

        try {
            ProductInfoExt oProduct = dlSales.getProductInfoByCode(sCode);
            if (oProduct == null) {
                Toolkit.getDefaultToolkit().beep();
                new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noproduct")).show(this);
                stateToZero();
            } else {
                // Se anade directamente una unidad con el precio y todo
                if (m_jaddtax.isSelected()) {
                    // debemos quitarle los impuestos ya que el precio es con iva incluido...
                    TaxInfo tax = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo(), m_oTicket.getCustomer());
                    addTicketLine(oProduct, 1.0, dPriceSell / (1.0 + tax.getRate()));
                } else {
                    addTicketLine(oProduct, 1.0, dPriceSell);
                }
            }
        } catch (BasicException eData) {
            stateToZero();
            new MessageInf(eData).show(this);
        }
    }

    private void incProduct(ProductInfoExt prod) throws BasicException{

        if (prod.isScale() && m_App.getDeviceScale().existsScale()) {
            try {
                Double value = m_App.getDeviceScale().readWeight();
                if (value != null) {
                    incProduct(value.doubleValue(), prod);
                }
            } catch (ScaleException e) {
                Toolkit.getDefaultToolkit().beep();
                new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noweight"), e).show(this);
                stateToZero();
            }
        } else {
            // No es un producto que se pese o no hay balanza
            incProduct(1.0, prod);
        }
    }

    private void incProduct(double dPor, ProductInfoExt prod) throws BasicException{
        // precondicion: prod != null
        addTicketLine(prod, dPor, prod.getPriceSell());
    }

    protected void buttonTransition(ProductInfoExt prod) throws BasicException{
        // precondicion: prod != null

        if (m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
            incProduct(prod);
        } else if (m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO) {
            incProduct(getInputValue(), prod);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private void addUnits1(double dUnits) {
        int i = m_ticketlines.getSelectedIndex();
        if (i >= 0) {
            TicketLineInfo oLine = m_oTicket.getLine(i);
            double qty=dUnits;
            if(qty>0){
                oLine.setMultiply(dUnits);
              
            }else{
                JOptionPane.showMessageDialog(null, "The Total Quantity Should Be Greater Then Zero");
            }
            paintTicketLine(i, oLine);
     
        }
    }
      private void addUnits(double dUnits) {
        int i = m_ticketlines.getSelectedIndex();
        if (i >= 0) {
            TicketLineInfo oLine = m_oTicket.getLine(i);
            double qty=oLine.getMultiply() + dUnits;
            if(qty>0){
                oLine.setMultiply(oLine.getMultiply() + dUnits);
               
            }else{
                JOptionPane.showMessageDialog(null, "The Total Quantity Should Be Greater Then Zero");
            }
            paintTicketLine(i, oLine);
}}
    
    
    private void stateTransition1(Object cTrans) {
        
        
         if (jTextField1.getText() == null || jTextField1.getText().equals("")) {
                // anadimos una unidad
                addUnits1(1.0);
//            
        }
        else  {
               addUnits1(Double.parseDouble(jTextField1.getText()));
                jTextField1.setText("");
            }
        }
    
     private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            m_jPor.setText("");
        } else if (cTrans == '+') {
            if (m_jPor.getText() == null || m_jPor.getText().equals("")) {
                // anadimos una unidad
                addUnits(1.0);
            } else {
                addUnits(Double.parseDouble(m_jPor.getText()));
               m_jPor.setText("");
            }
        } else if (cTrans == '-') {
            if (m_jPor.getText() == null || m_jPor.getText().equals("")) {
                // anadimos una unidad
                addUnits(-1.0);
            } else {
                addUnits(-Double.parseDouble(m_jPor.getText()));
                m_jPor.setText("");
            }
        }/*else  if (cTrans == '*' || cTrans == '=') {
        if ( m_jPor.getText() == null ||  m_jPor.getText().equals("")) {
        // anadimos una unidad
        addUnits(-1.0);
        } else {
        addUnits(-Double.parseDouble( m_jPor.getText()));
        m_jPor.setText("");
        }

        }*/ else if (cTrans == ' ' || cTrans == '=') {
            //  if ( m_jPor.getCount() == 0) {
            // No podemos grabar, no hay ningun registro.
            //      Toolkit.getDefaultToolkit().beep();
            //   } //else {
            //saveData();
            //}
        } else {
            // String del=m_jPor.getText();
            //   String del1= m_jPor.getText() + cTrans;
            m_jPor.setText(m_jPor.getText() + cTrans);
        }
    }
       //}
    /*else  if (cTrans == '*' || cTrans == '=') {
        if ( m_jPor.getText() == null ||  m_jPor.getText().equals("")) {
        // anadimos una unidad
        addUnits(-1.0);
        } else {
        addUnits(-Double.parseDouble( m_jPor.getText()));
        m_jPor.setText("");
        }

        }*/ 
     

    /*  private void stateTransition(char cTrans) {

    if (cTrans == '\n') {
    // Codigo de barras introducido
    if (m_sBarcode.length() > 0) {
    String sCode = m_sBarcode.toString();
    if (sCode.startsWith("c")) {
    // barcode of a customers card
    try {
    CustomerInfoExt newcustomer = dlSales.findCustomerExt(sCode);
    if (newcustomer == null) {
    Toolkit.getDefaultToolkit().beep();
    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.nocustomer")).show(this);
    } else {
    m_oTicket.setCustomer(newcustomer);
    m_jTicketId.setText(m_oTicket.getName(m_oTicketExt));
    }
    } catch (BasicException e) {
    Toolkit.getDefaultToolkit().beep();
    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.nocustomer"), e).show(this);
    }
    stateToZero();
    } else if (sCode.length() == 13 && sCode.startsWith("250")) {
    // barcode of the other machine
    ProductInfoExt oProduct = new ProductInfoExt(); // Es un ticket
    oProduct.setReference(null); // para que no se grabe
    oProduct.setCode(sCode);
    oProduct.setName("Ticket " + sCode.substring(3, 7));
    oProduct.setPriceSell(Double.parseDouble(sCode.substring(7, 12)) / 100);
    oProduct.setTaxCategoryInfo((TaxCategoryInfo) taxcategoriesmodel.getSelectedItem());
    // Se anade directamente una unidad con el precio y todo
    addTicketLine(oProduct, 1.0, includeTaxes(oProduct.getTaxCategoryInfo(), oProduct.getPriceSell()));
    } else if (sCode.length() == 13 && sCode.startsWith("210")) {
    // barcode of a weigth product
    incProductByCodePrice(sCode.substring(0, 7), Double.parseDouble(sCode.substring(7, 12)) / 100);
    } else {
    incProductByCode(sCode);
    }
    } else {
    Toolkit.getDefaultToolkit().beep();
    }
    } else {
    // otro caracter
    // Esto es para el codigo de barras...
    m_sBarcode.append(cTrans);

    // Esto es para el los productos normales...
    if (cTrans == '\u007f') {
    stateToZero();

    } else if ((cTrans == '0')
    && (m_iNumberStatus == NUMBER_INPUTZERO)) {
    m_jPor.setText("0");
    } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
    && (m_iNumberStatus == NUMBER_INPUTZERO)) {
    // Un numero entero
    m_jPor.setText(Character.toString(cTrans));
    m_iNumberStatus = NUMBER_INPUTINT;
    m_iNumberStatusInput = NUMBERVALID;
    } else if ((cTrans == '0' || cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
    && (m_iNumberStatus == NUMBER_INPUTINT)) {
    // Un numero entero
    m_jPrice.setText(m_jPrice.getText() + cTrans);

    } else if (cTrans == '.' && m_iNumberStatus == NUMBER_INPUTZERO) {
    m_jPrice.setText("0.");
    m_iNumberStatus = NUMBER_INPUTZERODEC;
    } else if (cTrans == '.' && m_iNumberStatus == NUMBER_INPUTINT) {
    m_jPrice.setText(m_jPrice.getText() + ".");
    m_iNumberStatus = NUMBER_INPUTDEC;

    } else if ((cTrans == '0')
    && (m_iNumberStatus == NUMBER_INPUTZERODEC || m_iNumberStatus == NUMBER_INPUTDEC)) {
    // Un numero decimal
    m_jPrice.setText(m_jPrice.getText() + cTrans);
    } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
    && (m_iNumberStatus == NUMBER_INPUTZERODEC || m_iNumberStatus == NUMBER_INPUTDEC)) {
    // Un numero decimal
    m_jPrice.setText(m_jPrice.getText() + cTrans);
    m_iNumberStatus = NUMBER_INPUTDEC;
    m_iNumberStatusInput = NUMBERVALID;

    } else if (cTrans == '*'
    && (m_iNumberStatus == NUMBER_INPUTINT || m_iNumberStatus == NUMBER_INPUTDEC)) {
    m_jPor.setText("x");
    m_iNumberStatus = NUMBER_PORZERO;
    } else if (cTrans == '*'
    && (m_iNumberStatus == NUMBER_INPUTZERO || m_iNumberStatus == NUMBER_INPUTZERODEC)) {
    m_jPrice.setText("0");
    m_jPor.setText("x");
    m_iNumberStatus = NUMBER_PORZERO;

    } else if ((cTrans == '0')
    && (m_iNumberStatus == NUMBER_PORZERO)) {
    m_jPor.setText("x0");
    } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
    && (m_iNumberStatus == NUMBER_PORZERO)) {
    // Un numero entero
    m_jPor.setText("x" + Character.toString(cTrans));
    m_iNumberStatus = NUMBER_PORINT;
    m_iNumberStatusPor = NUMBERVALID;
    } else if ((cTrans == '0' || cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
    && (m_iNumberStatus == NUMBER_PORINT)) {
    // Un numero entero
    m_jPor.setText(m_jPor.getText() + cTrans);

    } else if (cTrans == '.' && m_iNumberStatus == NUMBER_PORZERO) {
    m_jPor.setText("x0.");
    m_iNumberStatus = NUMBER_PORZERODEC;
    } else if (cTrans == '.' && m_iNumberStatus == NUMBER_PORINT) {
    m_jPor.setText(m_jPor.getText() + ".");
    m_iNumberStatus = NUMBER_PORDEC;

    } else if ((cTrans == '0')
    && (m_iNumberStatus == NUMBER_PORZERODEC || m_iNumberStatus == NUMBER_PORDEC)) {
    // Un numero decimal
    m_jPor.setText(m_jPor.getText() + cTrans);
    } else if ((cTrans == '1' || cTrans == '2' || cTrans == '3' || cTrans == '4' || cTrans == '5' || cTrans == '6' || cTrans == '7' || cTrans == '8' || cTrans == '9')
    && (m_iNumberStatus == NUMBER_PORZERODEC || m_iNumberStatus == NUMBER_PORDEC)) {
    // Un numero decimal
    m_jPor.setText(m_jPor.getText() + cTrans);
    m_iNumberStatus = NUMBER_PORDEC;
    m_iNumberStatusPor = NUMBERVALID;

    } else if (cTrans == '\u00a7'
    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO) {
    // Scale button pressed and a number typed as a price
    if (m_App.getDeviceScale().existsScale() && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {
    try {
    Double value = m_App.getDeviceScale().readWeight();
    if (value != null) {
    ProductInfoExt product = getInputProduct();
    addTicketLine(product, value.doubleValue(), product.getPriceSell());
    }
    } catch (ScaleException e) {
    Toolkit.getDefaultToolkit().beep();
    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noweight"), e).show(this);
    stateToZero();
    }
    } else {
    // No existe la balanza;
    Toolkit.getDefaultToolkit().beep();
    }
    } else if (cTrans == '\u00a7'
    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
    // Scale button pressed and no number typed.
    int i = m_ticketlines.getSelectedIndex();
    if (i < 0){
    Toolkit.getDefaultToolkit().beep();
    } else if (m_App.getDeviceScale().existsScale()) {
    try {
    Double value = m_App.getDeviceScale().readWeight();
    if (value != null) {
    TicketLineInfo oLine = m_oTicket.getLine(i);
    oLine.setMultiply(value.doubleValue());
    oLine.setPrice(Math.abs(oLine.getPrice()));
    paintTicketLine(i, oLine);
    }
    } catch (ScaleException e) {
    // Error de pesada.
    Toolkit.getDefaultToolkit().beep();
    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.noweight"), e).show(this);
    stateToZero();
    }
    } else {
    // No existe la balanza;
    Toolkit.getDefaultToolkit().beep();
    }

    // Anadimos un producto mas a la linea seleccionada
    } else if (cTrans == '+'
    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
    int i = m_ticketlines.getSelectedIndex();
    if (i < 0){
    Toolkit.getDefaultToolkit().beep();
    } else {
    // Sumamos uno a la seleccionada...
    TicketLineInfo oLine = m_oTicket.getLine(i);
    oLine.setMultiply(oLine.getMultiply() + 1.0);
    paintTicketLine(i, oLine);
    }

    // Eliminamos un producto mas a la linea seleccionada
    } else if (cTrans == '-'
    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO
    && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {

    int i = m_ticketlines.getSelectedIndex();
    if (i < 0){
    Toolkit.getDefaultToolkit().beep();
    } else {
    // Restamos uno a la seleccionada...
    TicketLineInfo oLine = m_oTicket.getLine(i);
    oLine.setMultiply(oLine.getMultiply() - 1.0);
    if (oLine.getMultiply() <= 0.0) {
    removeTicketLine(i); // elimino la linea
    } else {
    paintTicketLine(i, oLine);
    }
    }

    // Ponemos n productos a la linea seleccionada
    } else if (cTrans == '+'
    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERVALID) {
    int i = m_ticketlines.getSelectedIndex();
    if (i < 0){
    Toolkit.getDefaultToolkit().beep();
    } else {
    double dPor = getPorValue();
    TicketLineInfo oLine = m_oTicket.getLine(i);
    oLine.setMultiply(dPor);
    oLine.setPrice(Math.abs(oLine.getPrice()));
    paintTicketLine(i, oLine);
    }

    // Ponemos n productos negativos a la linea seleccionada
    } else if (cTrans == '-'
    && m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERVALID
    && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {

    int i = m_ticketlines.getSelectedIndex();
    if (i < 0){
    Toolkit.getDefaultToolkit().beep();
    } else {
    double dPor = getPorValue();
    TicketLineInfo oLine = m_oTicket.getLine(i);
    oLine.setMultiply(dPor);
    oLine.setPrice(-Math.abs(oLine.getPrice()));
    paintTicketLine(i, oLine);
    }

    // Anadimos 1 producto
    } else if (cTrans == '+'
    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO
    && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {
    ProductInfoExt product = getInputProduct();
    addTicketLine(product, 1.0, product.getPriceSell());

    // Anadimos 1 producto con precio negativo
    } else if (cTrans == '-'
    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO
    && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {
    ProductInfoExt product = getInputProduct();
    addTicketLine(product, 1.0, -product.getPriceSell());

    // Anadimos n productos
    } else if (cTrans == '+'
    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERVALID
    && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {
    ProductInfoExt product = getInputProduct();
    addTicketLine(product, getPorValue(), product.getPriceSell());

    // Anadimos n productos con precio negativo ?
    } else if (cTrans == '-'
    && m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERVALID
    && m_App.getAppUserView().getUser().hasPermission("sales.EditLines")) {
    ProductInfoExt product = getInputProduct();
    addTicketLine(product, getPorValue(), -product.getPriceSell());

    // Totals() Igual;
    } else if (cTrans == ' ' || cTrans == '=') {
    if (m_oTicket.getLinesCount() > 0) {

    if (closeTicket(m_oTicket, m_oTicketExt)) {
    // Ends edition of current receipt
    m_ticketsbag.deleteTicket();
    } else {
    // repaint current ticket
    refreshTicket();
    }
    } else {
    Toolkit.getDefaultToolkit().beep();
    }
    }
    }
    }*/

    private void createQTicket(TicketInfo ticket) throws Exception {
        QTLogic qtLogic = new QTLogic(ticket, dlSales, qTicket);
        qtLogic.dispatchQT();
        saveAndPrintQTs(qtLogic.getQTickets());
    }

    private void saveAndPrintQTs(final Collection<QticketInfo> qts) throws Exception {
        Transaction t = new Transaction(LookupUtilityImpl.getInstance(null).getAppView().getSession()) {

            public Object transact() throws BasicException {
                for (QticketInfo qtInfo : qts) {
                    boolean flag = qTicket.saveQTicket(qtInfo);
                    if (flag == true) {
                        printqt(qtInfo.getprCategory(), qtInfo);
                    } else {
                        break;
                    }
                }
                return null;
            }
        };
        t.execute();
    }

    public void printqt(String prcategory, QticketInfo qTicket) {
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.QT");
        String waitername;
        String table1 = qTicket.getPlace();
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
            boolean flag = m_App.getAppUserView().getUser().hasPermission("bar counter");
            String namedel = m_App.getAppUserView().getUser().getName();
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

            // script.put("flag", flag);
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("waiter", waitername);
            script.put("place", table1);
            script.put("flag", flag1);
            script.put("ticket", qTicket);
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

    private boolean closeTicket(TicketInfo ticket, Object ticketext) {

        boolean resultok = false;

        if (m_App.getAppUserView().getUser().hasPermission("sales.Total")) {

            // reset the payment info
            taxeslogic.calculateTaxes(ticket);
            ticket.resetPayments();

            if (executeEvent(ticket, ticketext, "ticket.total") == null) {

                // Muestro el total
                printTicket("Printer.TicketTotal", ticket, ticketext);


                // Select the Payments information
                JPaymentSelect paymentdialog = ticket.getTotal() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected("true".equals(m_jbtnconfig.getProperty("printselected", "true")));

                if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), m_App.getAppUserView().getUser().getName(), true)) {

                    // assign the payments selected and calculate taxes.                    
                    ticket.setPayments(paymentdialog.getSelectedPayments());

                    // Asigno los valores definitivos del ticket...
                    ticket.setUser(m_App.getAppUserView().getUser().getUserInfo()); // El usuario que lo cobra
                    ticket.setActiveCash(m_App.getActiveCashIndex());
                    ticket.setDate(new Date()); // Le pongo la fecha de cobro

                    if (executeEvent(ticket, ticketext, "ticket.save") == null) {
                        // Save the receipt and assign a receipt number
                        try {
                            dlSales.saveTicket(ticket, m_App.getInventoryLocation());
                        } catch (Exception eData) {
                            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosaveticket"), eData);
                            msg.show(this);
                        }

                        executeEvent(ticket, ticketext, "ticket.close");

                        // Print receipt.
                        printTicket(paymentdialog.isPrintSelected()
                                ? "Printer.Ticket"
                                : "Printer.Ticket2", ticket, ticketext);
                        resultok = true;
                    }
                }
            }

            // reset the payment info
            m_oTicket.resetTaxes();
            m_oTicket.resetPayments();
        }

        // cancelled the ticket.total script
        // or canceled the payment dialog
        // or canceled the ticket.close script
        return resultok;
    }

    private void printTicket(String sresourcename, TicketInfo ticket, Object ticketext) {

        String sresource = dlSystem.getResourceAsXML(sresourcename);
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(JPanelTicketnum1.this);
        } else {
            try {
                boolean flag = m_App.getAppUserView().getUser().hasPermission("Bar Counter");
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT prcategories from people  WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(m_App.getAppUserView().getUser().getId());
                String[] str = null;
                if (obj2[0] != null) {
                    str = obj2[0].toString().split("#");
                }
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(str[0]);
                String name = null;
                if (obj3 != null && obj3[0] != null) {
                    name = obj3[0].toString();
                }
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("taxes", taxcollection);
                script.put("taxeslogic", taxeslogic);
                script.put("flag", flag);
                script.put("ticket", ticket);
                script.put("place", ticketext);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("displayName", name);
                m_TTP.printTicket(script.eval(sresource).toString());
            } catch (BasicException ex) {
                Logger.getLogger(JPanelTicketnum1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(JPanelTicketnum1.this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(JPanelTicketnum1.this);
            }
        }
    }

    private void printReport(String resourcefile, TicketInfo ticket, Object ticketext) {

        try {

            JasperReport jr;

            InputStream in = getClass().getResourceAsStream(resourcefile + ".ser");
            if (in == null) {
                // read and compile the report
                JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream(resourcefile + ".jrxml"));
                jr = JasperCompileManager.compileReport(jd);
            } else {
                // read the compiled reporte
                ObjectInputStream oin = new ObjectInputStream(in);
                jr = (JasperReport) oin.readObject();
                oin.close();
            }

            // Construyo el mapa de los parametros.
            Map reportparams = new HashMap();
            // reportparams.put("ARG", params);
            try {
                reportparams.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle(resourcefile + ".properties"));
            } catch (MissingResourceException e) {
            }
            reportparams.put("TAXESLOGIC", taxeslogic);

            Map reportfields = new HashMap();
            reportfields.put("TICKET", ticket);
            reportfields.put("PLACE", ticketext);

            JasperPrint jp = JasperFillManager.fillReport(jr, reportparams, new JRMapArrayDataSource(new Object[]{reportfields}));

            PrintService service = ReportUtils.getPrintService(m_App.getProperties().getProperty("machine.printername"));

            JRPrinterAWT300.printPages(jp, 0, jp.getPages().size() - 1, service);

        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreport"), e);
            msg.show(this);
        }
    }

    private void visorTicketLine(TicketLineInfo oLine) {
        if (oLine == null) {
            m_App.getDeviceTicket().getDeviceDisplay().clearVisor();
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("ticketline", oLine);
                m_TTP.printTicket(script.eval(dlSystem.getResourceAsXML("Printer.TicketLine")).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintline"), e);
                msg.show(JPanelTicketnum1.this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintline"), e);
                msg.show(JPanelTicketnum1.this);
            }
        }
    }

    private Object evalScript(ScriptObject scr, String code, ScriptArg... args) {

        try {
            scr.setSelectedIndex(m_ticketlines.getSelectedIndex());
            return scr.evalScript(code, args);
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e);
            msg.show(this);
            return msg;
        }
    }

    public void evalScriptAndRefresh(String code, ScriptArg... args) {

        if (code == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"));
            msg.show(this);
        } else {
            ScriptObject scr = new ScriptObject(m_oTicket, m_oTicketExt);
            scr.setSelectedIndex(m_ticketlines.getSelectedIndex());
            evalScript(scr, code, args);
            refreshTicket();
            setSelectedIndex(scr.getSelectedIndex());
        }
    }

    private Object executeEventAndRefresh(String eventkey, ScriptArg... args) {

        String code = m_jbtnconfig.getEvent(eventkey);
        if (code == null) {
            return null;
        } 
        else {
            ScriptObject scr = new ScriptObject(m_oTicket, m_oTicketExt);
            scr.setSelectedIndex(m_ticketlines.getSelectedIndex());
            Object result = evalScript(scr, code, args);
            refreshTicket();
            setSelectedIndex(scr.getSelectedIndex());
            return result;
        }
        
        
   
    }

    private Object executeEvent(TicketInfo ticket, Object ticketext, String eventkey, ScriptArg... args) {

        String code = m_jbtnconfig.getEvent(eventkey);
        if (code == null) {
            return null;
        } else {
            ScriptObject scr = new ScriptObject(ticket, ticketext);
            return evalScript(scr, m_jbtnconfig.getEvent(eventkey), args);
        }
        
    }

    public String getResourceAsXML(String sresourcename) {
        return dlSystem.getResourceAsXML(sresourcename);
    }

    public BufferedImage getResourceAsImage(String sresourcename) {
        return dlSystem.getResourceAsImage(sresourcename);
    }

    private void setSelectedIndex(int i) {

        if (i >= 0 && i < m_oTicket.getLinesCount()) {
            m_ticketlines.setSelectedIndex(i);
        } else if (m_oTicket.getLinesCount() > 0) {
            m_ticketlines.setSelectedIndex(m_oTicket.getLinesCount() - 1);
        }
    }

   

    public static class ScriptArg {

        private String key;
        private Object value;

        public ScriptArg(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }

    public class ScriptObject {

        private TicketInfo ticket;
        private Object ticketext;
        private int selectedindex;

        private ScriptObject(TicketInfo ticket, Object ticketext) {
            this.ticket = ticket;
            this.ticketext = ticketext;
        }

        public double getInputValue() {
            if (m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO) {
                return JPanelTicketnum1.this.getInputValue();
            } else {
                return 0.0;
            }
        }

        public int getSelectedIndex() {
            return selectedindex;
        }

        public void setSelectedIndex(int i) {
            selectedindex = i;
        }

        public void printReport(String resourcefile) {
            JPanelTicketnum1.this.printReport(resourcefile, ticket, ticketext);
        }

        public void printTicket(String sresourcename) {
            JPanelTicketnum1.this.printTicket(sresourcename, ticket, ticketext);
        }

        public Object evalScript(String code, ScriptArg... args) throws ScriptException {

            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.BEANSHELL);
            script.put("ticket", ticket);
            script.put("place", ticketext);
            script.put("taxes", taxcollection);
            script.put("taxeslogic", taxeslogic);
            script.put("user", m_App.getAppUserView().getUser());
            script.put("sales", this);

            // more arguments
            for (ScriptArg arg : args) {
                script.put(arg.getKey(), arg.getValue());
            }

            return script.eval(code);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        m_jPanContainer = new javax.swing.JPanel();
        m_jOptions = new javax.swing.JPanel();
        m_jButtons = new javax.swing.JPanel();
        m_jTicketId = new javax.swing.JLabel();
        m_prTicket = new javax.swing.JButton();
        btnSplit = new javax.swing.JButton();
        m_jPanelScripts = new javax.swing.JPanel();
        m_jButtonsExt = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        m_jPanelBag = new javax.swing.JPanel();
        m_jbtnScale = new javax.swing.JButton();
        m_jPanTicket = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_jUp = new javax.swing.JButton();
        m_jDown = new javax.swing.JButton();
        m_jDelete = new javax.swing.JButton();
        m_jList = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        m_jEditLine = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        m_jPanelCentral = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        m_jPanTotals = new javax.swing.JPanel();
        m_jTotalEuros = new javax.swing.JLabel();
        m_jLblTotalEuros1 = new javax.swing.JLabel();
        m_jSubtotalEuros = new javax.swing.JLabel();
        m_jTaxesEuros = new javax.swing.JLabel();
        m_jLblTotalEuros3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        m_jLblTotalEuros2 = new javax.swing.JLabel();
        catcontainer = new javax.swing.JPanel();
        m_jContEntries = new javax.swing.JPanel();
        m_jPanEntries = new javax.swing.JPanel();
        m_jNumberKeys = new com.openbravo.beans.JNumberKeys();
        jPanel9 = new javax.swing.JPanel();
        m_jPrice = new javax.swing.JLabel();
        m_jPor = new javax.swing.JLabel();
        m_jEnter = new javax.swing.JButton();
        m_jTax = new javax.swing.JComboBox();
        m_jaddtax = new javax.swing.JToggleButton();
        m_jKeyFactory = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        m_jPanContainer1 = new javax.swing.JPanel();
        m_jOptions1 = new javax.swing.JPanel();
        m_jButtons1 = new javax.swing.JPanel();
        m_jTicketId1 = new javax.swing.JLabel();
        m_prTicket1 = new javax.swing.JButton();
        btnSplit1 = new javax.swing.JButton();
        m_jPanelScripts1 = new javax.swing.JPanel();
        m_jButtonsExt1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        m_jbtnScale1 = new javax.swing.JButton();
        m_jPanelBag1 = new javax.swing.JPanel();
        m_jPanTicket1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        m_jUp1 = new javax.swing.JButton();
        m_jDown1 = new javax.swing.JButton();
        m_jDelete1 = new javax.swing.JButton();
        m_jList1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        m_jEditLine1 = new javax.swing.JButton();
        m_jPanelCentral1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        m_jPanTotals1 = new javax.swing.JPanel();
        m_jTotalEuros1 = new javax.swing.JLabel();
        m_jLblTotalEuros4 = new javax.swing.JLabel();
        m_jSubtotalEuros1 = new javax.swing.JLabel();
        m_jTaxesEuros1 = new javax.swing.JLabel();
        m_jLblTotalEuros5 = new javax.swing.JLabel();
        m_jLblTotalEuros6 = new javax.swing.JLabel();
        m_jContEntries1 = new javax.swing.JPanel();
        m_jPanEntries1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        m_jPrice1 = new javax.swing.JLabel();
        m_jPor1 = new javax.swing.JLabel();
        m_jEnter1 = new javax.swing.JButton();
        m_jTax1 = new javax.swing.JComboBox();
        m_jaddtax1 = new javax.swing.JToggleButton();
        m_jKeyFactory1 = new javax.swing.JTextField();
        m_jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        catcontainer1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 204, 153));
        setLayout(new java.awt.CardLayout());

        m_jPanContainer.setLayout(new java.awt.BorderLayout());

        m_jTicketId.setBackground(java.awt.Color.white);
        m_jTicketId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_jTicketId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTicketId.setOpaque(true);
        m_jTicketId.setPreferredSize(new java.awt.Dimension(160, 25));
        m_jTicketId.setRequestFocusEnabled(false);

        m_prTicket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/tick1.PNG"))); // NOI18N
        m_prTicket.setToolTipText("QT list");
        m_prTicket.setFocusPainted(false);
        m_prTicket.setFocusable(false);
        m_prTicket.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_prTicket.setRequestFocusEnabled(false);
        m_prTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_prTicketActionPerformed(evt);
            }
        });

        btnSplit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editcut.png"))); // NOI18N
        btnSplit.setToolTipText("QT Split");
        btnSplit.setFocusPainted(false);
        btnSplit.setFocusable(false);
        btnSplit.setMargin(new java.awt.Insets(8, 14, 8, 14));
        btnSplit.setRequestFocusEnabled(false);
        btnSplit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSplitActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout m_jButtonsLayout = new org.jdesktop.layout.GroupLayout(m_jButtons);
        m_jButtons.setLayout(m_jButtonsLayout);
        m_jButtonsLayout.setHorizontalGroup(
            m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jButtonsLayout.createSequentialGroup()
                .add(5, 5, 5)
                .add(m_jTicketId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnSplit)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(m_prTicket))
        );
        m_jButtonsLayout.setVerticalGroup(
            m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jButtonsLayout.createSequentialGroup()
                .add(m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jButtonsLayout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(m_jTicketId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(m_jButtonsLayout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnSplit)
                            .add(m_prTicket))))
                .addContainerGap())
        );

        m_jPanelScripts.setLayout(new java.awt.BorderLayout());

        m_jButtonsExt.setLayout(new javax.swing.BoxLayout(m_jButtonsExt, javax.swing.BoxLayout.LINE_AXIS));
        m_jButtonsExt.add(jPanel1);

        m_jPanelScripts.add(m_jButtonsExt, java.awt.BorderLayout.LINE_END);

        m_jPanelBag.setLayout(new java.awt.BorderLayout());

        m_jbtnScale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/ark216.png"))); // NOI18N
        m_jbtnScale.setText(AppLocal.getIntString("button.scale")); // NOI18N
        m_jbtnScale.setFocusPainted(false);
        m_jbtnScale.setFocusable(false);
        m_jbtnScale.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jbtnScale.setRequestFocusEnabled(false);
        m_jbtnScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnScaleActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout m_jOptionsLayout = new org.jdesktop.layout.GroupLayout(m_jOptions);
        m_jOptions.setLayout(m_jOptionsLayout);
        m_jOptionsLayout.setHorizontalGroup(
            m_jOptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jOptionsLayout.createSequentialGroup()
                .add(m_jButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(m_jPanelBag, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 404, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(m_jbtnScale)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jPanelScripts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        m_jOptionsLayout.setVerticalGroup(
            m_jOptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jButtons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(m_jPanelBag, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(m_jOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .add(m_jOptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(m_jbtnScale)
                    .add(m_jPanelScripts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        m_jPanContainer.add(m_jOptions, java.awt.BorderLayout.NORTH);

        m_jPanTicket.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        m_jPanTicket.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));

        m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
        m_jUp.setFocusPainted(false);
        m_jUp.setFocusable(false);
        m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jUp.setRequestFocusEnabled(false);
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

        m_jDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/locationbar_erase.png"))); // NOI18N
        m_jDelete.setToolTipText("Remove");
        m_jDelete.setFocusPainted(false);
        m_jDelete.setFocusable(false);
        m_jDelete.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDelete.setRequestFocusEnabled(false);
        m_jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDeleteActionPerformed(evt);
            }
        });

        m_jList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/search22.png"))); // NOI18N
        m_jList.setToolTipText("Find Product");
        m_jList.setFocusPainted(false);
        m_jList.setFocusable(false);
        m_jList.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jList.setRequestFocusEnabled(false);
        m_jList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jListActionPerformed(evt);
            }
        });

        jButton1.setText("QT");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        m_jEditLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/color_line.png"))); // NOI18N
        m_jEditLine.setToolTipText("Remark");
        m_jEditLine.setFocusPainted(false);
        m_jEditLine.setFocusable(false);
        m_jEditLine.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jEditLine.setRequestFocusEnabled(false);
        m_jEditLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jEditLineActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText("QTY");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .add(m_jDown)
                    .add(m_jUp)
                    .add(m_jDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, m_jList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, m_jEditLine, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(m_jUp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jDown, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(m_jEditLine, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addContainerGap(93, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        m_jPanTicket.add(jPanel5, java.awt.BorderLayout.LINE_END);

        m_jPanelCentral.setLayout(new java.awt.BorderLayout());

        m_jTotalEuros.setBackground(java.awt.Color.white);
        m_jTotalEuros.setFont(new java.awt.Font("Dialog", 1, 14));
        m_jTotalEuros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        m_jTotalEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTotalEuros.setOpaque(true);
        m_jTotalEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jTotalEuros.setRequestFocusEnabled(false);

        m_jLblTotalEuros1.setText(AppLocal.getIntString("label.totalcash")); // NOI18N

        m_jSubtotalEuros.setBackground(java.awt.Color.white);
        m_jSubtotalEuros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        m_jSubtotalEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jSubtotalEuros.setOpaque(true);
        m_jSubtotalEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jSubtotalEuros.setRequestFocusEnabled(false);

        m_jTaxesEuros.setBackground(java.awt.Color.white);
        m_jTaxesEuros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        m_jTaxesEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTaxesEuros.setOpaque(true);
        m_jTaxesEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jTaxesEuros.setRequestFocusEnabled(false);

        m_jLblTotalEuros3.setText(AppLocal.getIntString("label.subtotalcash")); // NOI18N

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Curr.Op.Bal");

        m_jLblTotalEuros2.setText(AppLocal.getIntString("label.taxcash")); // NOI18N

        org.jdesktop.layout.GroupLayout m_jPanTotalsLayout = new org.jdesktop.layout.GroupLayout(m_jPanTotals);
        m_jPanTotals.setLayout(m_jPanTotalsLayout);
        m_jPanTotalsLayout.setHorizontalGroup(
            m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanTotalsLayout.createSequentialGroup()
                .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(m_jLblTotalEuros2))
                .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jPanTotalsLayout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(m_jTaxesEuros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(m_jPanTotalsLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jLblTotalEuros1)
                    .add(m_jLblTotalEuros3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jSubtotalEuros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(m_jTotalEuros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(80, 80, 80))
        );
        m_jPanTotalsLayout.setVerticalGroup(
            m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, m_jPanTotalsLayout.createSequentialGroup()
                .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(m_jLblTotalEuros3)
                    .add(m_jLblTotalEuros2)
                    .add(m_jTaxesEuros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(m_jSubtotalEuros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jPanTotalsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_jLblTotalEuros1))
                    .add(m_jTotalEuros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(m_jPanTotals, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(m_jPanTotals, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        m_jPanelCentral.add(jPanel4, java.awt.BorderLayout.SOUTH);

        m_jPanTicket.add(m_jPanelCentral, java.awt.BorderLayout.CENTER);

        m_jPanContainer.add(m_jPanTicket, java.awt.BorderLayout.CENTER);

        catcontainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        catcontainer.setLayout(new java.awt.BorderLayout());
        m_jPanContainer.add(catcontainer, java.awt.BorderLayout.SOUTH);

        m_jContEntries.setFocusable(false);
        m_jContEntries.setLayout(new java.awt.BorderLayout());

        m_jPanEntries.setFocusable(false);
        m_jPanEntries.setLayout(new javax.swing.BoxLayout(m_jPanEntries, javax.swing.BoxLayout.Y_AXIS));

        m_jNumberKeys.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                m_jNumberKeysKeyPerformed(evt);
            }
        });
        m_jPanEntries.add(m_jNumberKeys);
        /*m_jPlus.addActionListener(new ActionListener(){  //code for + SHIV CREATED
            public void actionPerformed(ActionEvent ae){
                m_jPlus.addActionListener(new MyKeyNumberListener('+')); }});
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
    .addKeyEventDispatcher(new KeyEventDispatcher(){
        public boolean dispatchKeyEvent(KeyEvent e){
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
                if((e.getKeyCode() == KeyEvent.VK_ADD ) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))  m_jPlus.addActionListener(new MyKeyNumberListener('+'));
            }
            return false;}});

m_jMinus.addActionListener(new ActionListener(){  //code for + SHIV CREATED
    public void actionPerformed(ActionEvent ae){
        m_jMinus.addActionListener(new MyKeyNumberListener('+')); }});
KeyboardFocusManager.getCurrentKeyboardFocusManager()
.addKeyEventDispatcher(new KeyEventDispatcher(){
public boolean dispatchKeyEvent(KeyEvent e){
    if(e.getID() == KeyEvent.KEY_PRESSED)
    {
        if((e.getKeyCode() == KeyEvent.VK_SUBTRACT ) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))   m_jMinus.addActionListener(new MyKeyNumberListener('+'));
    }
    return false;}});
    */

    jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    jPanel9.setFocusable(false);
    jPanel9.setLayout(new java.awt.GridBagLayout());

    m_jPrice.setBackground(java.awt.Color.white);
    m_jPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    m_jPrice.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jPrice.setOpaque(true);
    m_jPrice.setPreferredSize(new java.awt.Dimension(100, 22));
    m_jPrice.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel9.add(m_jPrice, gridBagConstraints);

    m_jPor.setBackground(java.awt.Color.white);
    m_jPor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    m_jPor.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jPor.setOpaque(true);
    m_jPor.setPreferredSize(new java.awt.Dimension(22, 22));
    m_jPor.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    jPanel9.add(m_jPor, gridBagConstraints);

    m_jEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/barcode.png"))); // NOI18N
    m_jEnter.setFocusPainted(false);
    m_jEnter.setFocusable(false);
    m_jEnter.setRequestFocusEnabled(false);
    m_jEnter.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jEnterActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    jPanel9.add(m_jEnter, gridBagConstraints);

    m_jTax.setFocusable(false);
    m_jTax.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    jPanel9.add(m_jTax, gridBagConstraints);

    m_jaddtax.setText("+");
    m_jaddtax.setFocusPainted(false);
    m_jaddtax.setFocusable(false);
    m_jaddtax.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    jPanel9.add(m_jaddtax, gridBagConstraints);

    m_jPanEntries.add(jPanel9);

    m_jKeyFactory.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
    m_jKeyFactory.setForeground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
    m_jKeyFactory.setBorder(null);
    m_jKeyFactory.setCaretColor(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
    m_jKeyFactory.setFocusable(false);
    m_jKeyFactory.setPreferredSize(new java.awt.Dimension(1, 1));
    m_jKeyFactory.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            m_jKeyFactoryKeyTyped(evt);
        }
    });
    m_jPanEntries.add(m_jKeyFactory);

    m_jContEntries.add(m_jPanEntries, java.awt.BorderLayout.NORTH);

    m_jPanContainer.add(m_jContEntries, java.awt.BorderLayout.LINE_END);

    add(m_jPanContainer, "ticket");

    jPanel3.setBackground(new java.awt.Color(255, 204, 153));
    jPanel3.setLayout(new java.awt.CardLayout());

    m_jPanContainer1.setLayout(new java.awt.BorderLayout());

    m_jTicketId1.setBackground(java.awt.Color.white);
    m_jTicketId1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    m_jTicketId1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jTicketId1.setOpaque(true);
    m_jTicketId1.setPreferredSize(new java.awt.Dimension(160, 25));
    m_jTicketId1.setRequestFocusEnabled(false);

    m_prTicket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/tick1.PNG"))); // NOI18N
    m_prTicket1.setToolTipText("QT list");
    m_prTicket1.setFocusPainted(false);
    m_prTicket1.setFocusable(false);
    m_prTicket1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_prTicket1.setRequestFocusEnabled(false);
    m_prTicket1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_prTicket1ActionPerformed(evt);
        }
    });

    btnSplit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editcut.png"))); // NOI18N
    btnSplit1.setToolTipText("QT Split");
    btnSplit1.setFocusPainted(false);
    btnSplit1.setFocusable(false);
    btnSplit1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    btnSplit1.setRequestFocusEnabled(false);
    btnSplit1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSplit1ActionPerformed(evt);
        }
    });

    org.jdesktop.layout.GroupLayout m_jButtons1Layout = new org.jdesktop.layout.GroupLayout(m_jButtons1);
    m_jButtons1.setLayout(m_jButtons1Layout);
    m_jButtons1Layout.setHorizontalGroup(
        m_jButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jButtons1Layout.createSequentialGroup()
            .add(5, 5, 5)
            .add(m_jTicketId1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(btnSplit1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(m_prTicket1))
    );
    m_jButtons1Layout.setVerticalGroup(
        m_jButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jButtons1Layout.createSequentialGroup()
            .add(m_jButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(m_jButtons1Layout.createSequentialGroup()
                    .add(10, 10, 10)
                    .add(m_jTicketId1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(m_jButtons1Layout.createSequentialGroup()
                    .add(5, 5, 5)
                    .add(m_jButtons1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(btnSplit1)
                        .add(m_prTicket1))))
            .addContainerGap())
    );

    m_jPanelScripts1.setLayout(new java.awt.BorderLayout());

    m_jButtonsExt1.setLayout(new javax.swing.BoxLayout(m_jButtonsExt1, javax.swing.BoxLayout.LINE_AXIS));

    m_jbtnScale1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/ark216.png"))); // NOI18N
    m_jbtnScale1.setText(AppLocal.getIntString("button.scale")); // NOI18N
    m_jbtnScale1.setFocusPainted(false);
    m_jbtnScale1.setFocusable(false);
    m_jbtnScale1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jbtnScale1.setRequestFocusEnabled(false);
    m_jbtnScale1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jbtnScale1ActionPerformed(evt);
        }
    });
    jPanel6.add(m_jbtnScale1);

    m_jButtonsExt1.add(jPanel6);

    m_jPanelScripts1.add(m_jButtonsExt1, java.awt.BorderLayout.LINE_END);

    m_jPanelBag1.setLayout(new java.awt.BorderLayout());

    org.jdesktop.layout.GroupLayout m_jOptions1Layout = new org.jdesktop.layout.GroupLayout(m_jOptions1);
    m_jOptions1.setLayout(m_jOptions1Layout);
    m_jOptions1Layout.setHorizontalGroup(
        m_jOptions1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jOptions1Layout.createSequentialGroup()
            .add(m_jButtons1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(m_jPanelBag1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 404, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(m_jPanelScripts1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    );
    m_jOptions1Layout.setVerticalGroup(
        m_jOptions1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jButtons1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(m_jPanelBag1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(m_jPanelScripts1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
    );

    m_jPanContainer1.add(m_jOptions1, java.awt.BorderLayout.NORTH);

    m_jPanTicket1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    m_jPanTicket1.setLayout(new java.awt.BorderLayout());

    jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));

    m_jUp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
    m_jUp1.setFocusPainted(false);
    m_jUp1.setFocusable(false);
    m_jUp1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jUp1.setRequestFocusEnabled(false);
    m_jUp1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jUp1ActionPerformed(evt);
        }
    });

    m_jDown1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
    m_jDown1.setFocusPainted(false);
    m_jDown1.setFocusable(false);
    m_jDown1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jDown1.setRequestFocusEnabled(false);
    m_jDown1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jDown1ActionPerformed(evt);
        }
    });

    m_jDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/locationbar_erase.png"))); // NOI18N
    m_jDelete1.setToolTipText("Remove");
    m_jDelete1.setFocusPainted(false);
    m_jDelete1.setFocusable(false);
    m_jDelete1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jDelete1.setRequestFocusEnabled(false);
    m_jDelete1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jDelete1ActionPerformed(evt);
        }
    });

    m_jList1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/search22.png"))); // NOI18N
    m_jList1.setToolTipText("Find Product");
    m_jList1.setFocusPainted(false);
    m_jList1.setFocusable(false);
    m_jList1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jList1.setRequestFocusEnabled(false);
    m_jList1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jList1ActionPerformed(evt);
        }
    });

    jButton2.setText("QT");
    jButton2.setFocusable(false);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    m_jEditLine1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/color_line.png"))); // NOI18N
    m_jEditLine1.setToolTipText("Remark");
    m_jEditLine1.setFocusPainted(false);
    m_jEditLine1.setFocusable(false);
    m_jEditLine1.setMargin(new java.awt.Insets(8, 14, 8, 14));
    m_jEditLine1.setRequestFocusEnabled(false);
    m_jEditLine1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jEditLine1ActionPerformed(evt);
        }
    });

    org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(jPanel8Layout.createSequentialGroup()
            .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(m_jUp1)
                .add(m_jDown1)
                .add(m_jDelete1)
                .add(m_jList1)
                .add(m_jEditLine1)
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(jPanel8Layout.createSequentialGroup()
            .add(m_jUp1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(m_jDown1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(m_jDelete1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(m_jList1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(1, 1, 1)
            .add(m_jEditLine1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
            .add(93, 93, 93))
    );

    org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
            .addContainerGap(54, Short.MAX_VALUE)
            .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(jPanel7Layout.createSequentialGroup()
            .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(140, Short.MAX_VALUE))
    );

    m_jPanTicket1.add(jPanel7, java.awt.BorderLayout.LINE_END);

    m_jPanelCentral1.setLayout(new java.awt.BorderLayout());

    jPanel10.setLayout(new java.awt.BorderLayout());

    m_jPanTotals1.setLayout(new java.awt.GridBagLayout());

    m_jTotalEuros1.setBackground(java.awt.Color.white);
    m_jTotalEuros1.setFont(new java.awt.Font("Dialog", 1, 14));
    m_jTotalEuros1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    m_jTotalEuros1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jTotalEuros1.setOpaque(true);
    m_jTotalEuros1.setPreferredSize(new java.awt.Dimension(150, 25));
    m_jTotalEuros1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    m_jPanTotals1.add(m_jTotalEuros1, gridBagConstraints);

    m_jLblTotalEuros4.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    m_jPanTotals1.add(m_jLblTotalEuros4, gridBagConstraints);

    m_jSubtotalEuros1.setBackground(java.awt.Color.white);
    m_jSubtotalEuros1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    m_jSubtotalEuros1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jSubtotalEuros1.setOpaque(true);
    m_jSubtotalEuros1.setPreferredSize(new java.awt.Dimension(150, 25));
    m_jSubtotalEuros1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    m_jPanTotals1.add(m_jSubtotalEuros1, gridBagConstraints);

    m_jTaxesEuros1.setBackground(java.awt.Color.white);
    m_jTaxesEuros1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    m_jTaxesEuros1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jTaxesEuros1.setOpaque(true);
    m_jTaxesEuros1.setPreferredSize(new java.awt.Dimension(150, 25));
    m_jTaxesEuros1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
    m_jPanTotals1.add(m_jTaxesEuros1, gridBagConstraints);

    m_jLblTotalEuros5.setText(AppLocal.getIntString("label.taxcash")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    m_jPanTotals1.add(m_jLblTotalEuros5, gridBagConstraints);

    m_jLblTotalEuros6.setText(AppLocal.getIntString("label.subtotalcash")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    m_jPanTotals1.add(m_jLblTotalEuros6, gridBagConstraints);

    jPanel10.add(m_jPanTotals1, java.awt.BorderLayout.LINE_END);

    m_jPanelCentral1.add(jPanel10, java.awt.BorderLayout.SOUTH);

    m_jPanTicket1.add(m_jPanelCentral1, java.awt.BorderLayout.CENTER);

    m_jPanContainer1.add(m_jPanTicket1, java.awt.BorderLayout.CENTER);

    m_jContEntries1.setFocusable(false);

    jPanel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    jPanel11.setLayout(new java.awt.GridBagLayout());

    m_jPrice1.setBackground(java.awt.Color.white);
    m_jPrice1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    m_jPrice1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jPrice1.setFocusable(false);
    m_jPrice1.setOpaque(true);
    m_jPrice1.setPreferredSize(new java.awt.Dimension(100, 22));
    m_jPrice1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel11.add(m_jPrice1, gridBagConstraints);

    m_jPor1.setBackground(java.awt.Color.white);
    m_jPor1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    m_jPor1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
    m_jPor1.setFocusable(false);
    m_jPor1.setOpaque(true);
    m_jPor1.setPreferredSize(new java.awt.Dimension(22, 22));
    m_jPor1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    jPanel11.add(m_jPor1, gridBagConstraints);

    m_jEnter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/barcode.png"))); // NOI18N
    m_jEnter1.setFocusPainted(false);
    m_jEnter1.setFocusable(false);
    m_jEnter1.setRequestFocusEnabled(false);
    m_jEnter1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            m_jEnter1ActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    jPanel11.add(m_jEnter1, gridBagConstraints);

    m_jTax1.setFocusable(false);
    m_jTax1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    jPanel11.add(m_jTax1, gridBagConstraints);

    m_jaddtax1.setText("+");
    m_jaddtax1.setFocusPainted(false);
    m_jaddtax1.setFocusable(false);
    m_jaddtax1.setRequestFocusEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
    jPanel11.add(m_jaddtax1, gridBagConstraints);

    m_jKeyFactory1.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
    m_jKeyFactory1.setForeground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
    m_jKeyFactory1.setBorder(null);
    m_jKeyFactory1.setCaretColor(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
    m_jKeyFactory1.setPreferredSize(new java.awt.Dimension(1, 1));
    m_jKeyFactory1.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            m_jKeyFactory1KeyTyped(evt);
        }
    });

    m_jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
        public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
            m_jNumberKeys1KeyPerformed(evt);
        }
    });

    org.jdesktop.layout.GroupLayout m_jPanEntries1Layout = new org.jdesktop.layout.GroupLayout(m_jPanEntries1);
    m_jPanEntries1.setLayout(m_jPanEntries1Layout);
    m_jPanEntries1Layout.setHorizontalGroup(
        m_jPanEntries1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jPanEntries1Layout.createSequentialGroup()
            .add(m_jKeyFactory1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(36, Short.MAX_VALUE))
        .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        .add(m_jPanEntries1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanEntries1Layout.createSequentialGroup()
                .addContainerGap()
                .add(m_jNumberKeys1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE)))
    );
    m_jPanEntries1Layout.setVerticalGroup(
        m_jPanEntries1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jPanEntries1Layout.createSequentialGroup()
            .add(75, 75, 75)
            .add(m_jKeyFactory1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 107, Short.MAX_VALUE)
            .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .add(m_jPanEntries1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanEntries1Layout.createSequentialGroup()
                .addContainerGap()
                .add(m_jNumberKeys1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE)))
    );

    org.jdesktop.layout.GroupLayout m_jContEntries1Layout = new org.jdesktop.layout.GroupLayout(m_jContEntries1);
    m_jContEntries1.setLayout(m_jContEntries1Layout);
    m_jContEntries1Layout.setHorizontalGroup(
        m_jContEntries1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jContEntries1Layout.createSequentialGroup()
            .add(21, 21, 21)
            .add(m_jPanEntries1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    m_jContEntries1Layout.setVerticalGroup(
        m_jContEntries1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
        .add(m_jContEntries1Layout.createSequentialGroup()
            .add(m_jPanEntries1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(217, Short.MAX_VALUE))
    );

    m_jPanContainer1.add(m_jContEntries1, java.awt.BorderLayout.LINE_END);

    catcontainer1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    catcontainer1.setLayout(new java.awt.BorderLayout());
    m_jPanContainer1.add(catcontainer1, java.awt.BorderLayout.SOUTH);

    jPanel3.add(m_jPanContainer1, "ticket");

    add(jPanel3, "card3");
    }// </editor-fold>//GEN-END:initComponents

private void m_jTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jTaxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jTaxActionPerformed

private void m_jPorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jPorKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_m_jPorKeyReleased

private void m_jDeleteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jDeleteKeyReleased
// TODO add your handling code here:

}//GEN-LAST:event_m_jDeleteKeyReleased

private void m_jDeleteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jDeleteKeyTyped
// TODO add your handling code here:
      char c = evt.getKeyChar();
            if (c == 'd') {
             m_jDelete.requestFocus();
     int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            removeTicketLine(i); // elimino la linea           
        }
        
      }
            else
     m_jDelete.transferFocus();  
}//GEN-LAST:event_m_jDeleteKeyTyped

private void m_prTicketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_prTicketKeyPressed
// TODO add your handling code here:
  
}//GEN-LAST:event_m_prTicketKeyPressed

private void m_prTicketKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_prTicketKeyTyped
// TODO add your handling code here:

}//GEN-LAST:event_m_prTicketKeyTyped

private void m_prTicketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_prTicketKeyReleased
// TODO add your handling code here:
    
   
//       
}//GEN-LAST:event_m_prTicketKeyReleased

private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
// TODO add your handling code here:
// 
}//GEN-LAST:event_jButton1KeyReleased

private void m_jListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jListKeyReleased
// TODO add your handling code here:
//     if( evt.getKeyCode()==KeyEvent.VK_S){
//           m_jList.requestFocus();
//       ProductInfoExt prod = JProductFinder.showMessage(JPanelTicket.this, dlSales);
//        if (prod != null) {
//            buttonTransition(prod);
//        }
//        
//     }
//     else
//      m_jList.transferFocus();
}//GEN-LAST:event_m_jListKeyReleased

private void m_jEditLineKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jEditLineKeyTyped
// TODO add your handling code here:
    
//     if( evt.getKeyCode()==KeyEvent.VK_R){
//          m_jEditLine.requestFocus();
//           
//            int i = m_ticketlines.getSelectedIndex();
//        if (i < 0) {
//            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
//        } else {
//            TicketLineInfo oLine = m_oTicket.getLine(i);
//            if (JProductLineRemarkEdit.showMessage(this, m_App, m_oTicket.getLine(i))) {
//                // se ha modificado la linea
//                paintTicketLine(i, oLine);
//            }
//        }
//     }
//    m_jEditLine.transferFocus();
}//GEN-LAST:event_m_jEditLineKeyTyped

private void m_jEditLineKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jEditLineKeyReleased
// TODO add your handling code here:
//    
//      if( evt.getKeyCode()==KeyEvent.VK_R){
//          m_jEditLine.requestFocus();
//           
//            int i = m_ticketlines.getSelectedIndex();
//        if (i < 0) {
//            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
//        } else {
//            TicketLineInfo oLine = m_oTicket.getLine(i);
//            if (JProductLineRemarkEdit.showMessage(this, m_App, m_oTicket.getLine(i))) {
//                // se ha modificado la linea
//                paintTicketLine(i, oLine);
//            }
//        }
//         m_jEditLine.transferFocus();
//     }
//   
}//GEN-LAST:event_m_jEditLineKeyReleased

private void m_jEditLineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jEditLineKeyPressed
// TODO add your handling code here:
//       if( evt.getKeyCode()==KeyEvent.VK_R){
//          m_jEditLine.requestFocus();
//           
//            int i = m_ticketlines.getSelectedIndex();
//        if (i < 0) {
//            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
//        } else {
//            TicketLineInfo oLine = m_oTicket.getLine(i);
//            if (JProductLineRemarkEditnum1.showMessage(this, m_App, m_oTicket.getLine(i))) {
//                // se ha modificado la linea
//                paintTicketLine(i, oLine);
//            }
//        }
//         //m_jEditLine.transferFocus();
//     }
//       else
//               m_jEditLine.transferFocus();
//     
//       
}//GEN-LAST:event_m_jEditLineKeyPressed

private void m_jDeleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jDeleteKeyPressed
// TODO add your handling code here:
 
}//GEN-LAST:event_m_jDeleteKeyPressed

private void m_jListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jListKeyPressed
// TODO add your handling code here:
    
}//GEN-LAST:event_m_jListKeyPressed

private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
// TODO add your handling code here:
//     if( evt.getKeyCode()==KeyEvent.VK_Q){
//           jButton1.requestFocus();
//        
//     try {
//            boolean berror = false;
//            Date date = new Date();
//            AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
//            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
//            if (obj != null) {
//                if (obj[0] != null) {
//                    Date d = (Date) obj[0];
//                    Calendar cal1 = Calendar.getInstance();
//                    Calendar cal2 = Calendar.getInstance();
//                    cal1.setTimeInMillis(date.getTime());
//                    cal2.setTimeInMillis(d.getTime());
//                    if (cal1.before(cal2)) {
//                        if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
//                            berror = true;
//                        } else {
//                            new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
//                        }
//                    }
//                }
//            }
//            if (berror == false && m_oTicket.getLinesCount() > 0) {
//                // temp=1;
//                TicketInfo ticket1 = m_oTicket.copyTicket();
//                TicketInfo ticket2 = new TicketInfo();
//                ticket2.setCustomer(m_oTicket.getCustomer());
//                ticket2.setUser(ticket1.getUser());
//                ticket2.setPlace(ticket1.getPlace());
//                ticket2.setWaiter(ticket1.getWaiter());
//                ticket2.setFloor(ticket1.getFloor());
//
//                int count = m_oTicket.getLinesCount();
//                int j = 0;
//                int size = ticket1.getLines().size();
//                // Boolean temp=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("StockCheckNotRequired");
//
//                // if(!temp)
//                while (j < size) {
//                    TicketLineInfo tl = ticket1.getLine(j);
////                    System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + tl.isStockCheckRequired());
////                    Object stockcheck = new PreparedSentence(m_App.getSession(), "SELECT INVENTRYMAINTAIN FROM PRODUCTS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(tl.getProductID().toString());
////                    System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + Boolean.valueOf(stockcheck.toString()));
//
//                    if (tl.isStockCheckRequired()) {
//                        Object o = dlSales.getStockVolume(tl.getProductID());
//                        Double sqty = 0.0;
//                        if (o != null) {
//                            sqty = Double.parseDouble(o.toString());
//                        }
//                        if (sqty >= tl.getMultiply()) {
//                            dlSales.updateStockVolume(-tl.getMultiply(), tl.getProductID());
//                            dlSales.updateStockVolume1(-tl.getMultiply(), tl.getProductID());
//                            j++;
//                        } else {
//                            if (sqty == 0.0) {
//                                JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);
//                            } else {
//                                JOptionPane.showMessageDialog(this, "QT quantity Exceed the quantity in stock for \"" + tl.getProductName() + " \"", "Cannot Prepare QT", JOptionPane.WARNING_MESSAGE);
//                            }
//                            ticket1.deleteLine(j);
//                            //j--;
//                            size--;
//                        }
//                    } else {
//                        j++;
//                    }
//                }
//                createQTicket(ticket1);
//                setActiveTicket(ticket2, m_oTicketExt);// set result ticket
//                dlReceipts.updateSharedTicket(ticket2.getCustomerId(), ticket2, m_App.getAppUserView().getUser().getRole());
//                //  dlReceipts.updateLastQtTimeOfSharedTicket(new Date(),ticket1.getCustomerId(), m_App.getAppUserView().getUser().getRole());
//                for (int i = 0; i < m_oTicket.getLinesCount(); i++) {
//                    m_ticketlines.removeTicketLine(0);
//                }
//                //praveen:exit for every transaction for kiosk mode---start
//                if(m_App.getAppUserView().getUser().getTypeOfUser()==1)
//                JPrincipalApp.m_approot.closeAppView();
//                //praveen:exit for every transaction for kiosk mode---end
//            } else {
//                if (berror == true) {
//                    JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot login", JOptionPane.OK_OPTION);
//                }
//            }
//        } catch (Exception e) {
//            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreateqt"), e);
//            msg.show(this);
//        }
//             
//      }
//      else
//      jButton1.transferFocus();
}//GEN-LAST:event_jButton1KeyPressed

private void btnSplit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSplit1KeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_btnSplit1KeyReleased

private void btnSplit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSplit1KeyPressed
// TODO add your handling code here:
 
}//GEN-LAST:event_btnSplit1KeyPressed

private void m_prTicketFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_m_prTicketFocusGained
        // TODO add your handling code here:
        // boolean focusTraversalKeysEnabled = m_prTicket.getFocusTraversalKeysEnabled();
//       FocusListener[] focusListeners = m_jButtons.getFocusListeners();
        m_jButtons.getFocusTraversalKeysEnabled();
   
}//GEN-LAST:event_m_prTicketFocusGained

private void m_prTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_prTicketActionPerformed
        //has to open to a small dialog that lists all current qts of that customer
//          component.getRootPane().getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW)
//          .put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "refresh");
//            component.getRootPane().getActionMap().put("refresh", new AbstractAction() {
    
    
         
//}
//         
}//GEN-LAST:event_m_prTicketActionPerformed

private void btnSplitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSplitActionPerformed
        try {
            boolean berror = false;
            if (m_oTicket.getLinesCount() > 0) {
               ReceiptSplitnum1 splitdialog = ReceiptSplitnum1.getDialog(this, dlSystem.getResourceAsXML("Ticket.Line"), dlSales, dlCustomers, taxeslogic);

                TicketInfo ticket1 = m_oTicket.copyTicket();
                TicketInfo ticket2 = new TicketInfo();
                ticket2.setCustomer(m_oTicket.getCustomer());
                ticket2.setUser(ticket1.getUser());
                ticket2.setPlace(ticket1.getPlace());
                ticket2.setWaiter(ticket1.getWaiter());
                ticket2.setFloor(ticket1.getFloor());

                if (splitdialog.showDialog(ticket1, ticket2, m_oTicketExt)) {

                    Date date = new Date();
                    AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                                    berror = true;
                                } else {
                                    new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                                }
                            }
                        }
                    }
                    if (berror == false) {
                        int ticketsize = ticket2.getLines().size();
                        int j = 0;
                        // Boolean temp=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("StockCheckNotRequired");

                        // if(!temp)
                        while (j < ticket2.getLines().size()) {
                            TicketLineInfo tl = ticket2.getLine(j);

                            System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + tl.isStockCheckRequired());
                            Object stockcheck = new PreparedSentence(m_App.getSession(), "SELECT INVENTRYMAINTAIN FROM PRODUCTS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(tl.getProductID().toString());
                            System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + Boolean.valueOf(stockcheck.toString()));

                            if (tl.isStockCheckRequired()) {
                                // if(tl.getProductCategoryID()){
                                Object o = dlSales.getStockVolume(tl.getProductID());
                                Double sqty = 0.0;
                                if (o != null) {
                                    sqty = Double.parseDouble(o.toString());
                                }
                                if (sqty > 0) {
                                    dlSales.updateStockVolume(-tl.getMultiply(), tl.getProductID());
                                    dlSales.updateStockVolume1(-tl.getMultiply(), tl.getProductID());
                                    j++;
                                } else {
                                    JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);

                                    ticket2.deleteLine(j);
                                }
                            }
                            j++;
                        //   }

                        }
                        createQTicket(ticket2);
//                if (closeTicket(ticket2, m_oTicketExt)) { // already checked  that number of lines > 0
                        setActiveTicket(ticket1, m_oTicketExt);// set result ticket
                        dlReceipts.updateSharedTicket(ticket1.getCustomerId(), ticket1, m_App.getAppUserView().getUser().getRole());
                    //  dlReceipts.updateLastQtTimeOfSharedTicket(new Date(),ticket1.getCustomerId(), m_App.getAppUserView().getUser().getRole());
                    } else {
                        // if(berror==true){
                        JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot Create QT", JOptionPane.OK_OPTION);
                    // }
                    }
                }
                //praveen:exit for every transaction for kiosk mode---start
                if(m_App.getAppUserView().getUser().getTypeOfUser()==1)
                JPrincipalApp.m_approot.closeAppView();
                //praveen:exit for every transaction for kiosk mode---end

            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreateqt"), e);
            msg.show(this);
        }
}//GEN-LAST:event_btnSplitActionPerformed

private void m_jbtnScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnScaleActionPerformed

        stateTransition('\u00a7');
//
}//GEN-LAST:event_m_jbtnScaleActionPerformed

private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed

        m_ticketlines.selectionUp();
}//GEN-LAST:event_m_jUpActionPerformed

private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed

        m_ticketlines.selectionDown();
}//GEN-LAST:event_m_jDownActionPerformed

private void m_jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDeleteActionPerformed

        int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            removeTicketLine(i); // elimino la linea           
        }
}//GEN-LAST:event_m_jDeleteActionPerformed

private void m_jListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jListActionPerformed
     try{
        ProductInfoExt prod = JProductFindernum1.showMessage(JPanelTicketnum1.this, dlSales);
        if (prod != null) {
            buttonTransition(prod);
        }
     }catch(BasicException e){
         
     }
}//GEN-LAST:event_m_jListActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      	int x=0;
                Object[]stk=new Object[25];
        try {
            boolean berror = false;
            Date date = new Date();
            AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
            if (obj != null) {
                if (obj[0] != null) {
                    Date d = (Date) obj[0];
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.setTimeInMillis(date.getTime());
                    cal2.setTimeInMillis(d.getTime());
                    if (cal1.before(cal2)) {
                        if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                            berror = true;
                        } else {
                            new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                        }
                    }
                }
            }
            if (berror == false && m_oTicket.getLinesCount() > 0) {
                // temp=1;
                TicketInfo ticket1 = m_oTicket.copyTicket();
                TicketInfo ticket2 = new TicketInfo();
                ticket2.setCustomer(m_oTicket.getCustomer());
                ticket2.setUser(ticket1.getUser());
                ticket2.setPlace(ticket1.getPlace());
                ticket2.setWaiter(ticket1.getWaiter());
                ticket2.setFloor(ticket1.getFloor());

                int count = m_oTicket.getLinesCount();
                int j = 0;
                int size = ticket1.getLines().size();
                // Boolean temp=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("StockCheckNotRequired");

                // if(!temp)
                while (j < size) {
                    TicketLineInfo tl = ticket1.getLine(j);
//                    System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + tl.isStockCheckRequired());
//                    Object stockcheck = new PreparedSentence(m_App.getSession(), "SELECT INVENTRYMAINTAIN FROM PRODUCTS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(tl.getProductID().toString());
//                    System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + Boolean.valueOf(stockcheck.toString()));

                    if (tl.isStockCheckRequired()) {
                        Object o = dlSales.getStockVolume(tl.getProductID());
                        stk[j] = o;
                        Double sqty = 0.0;
                        if (o != null) {
                            sqty = Double.parseDouble(o.toString());
                        }
                        if (sqty >= tl.getMultiply()) {
                            dlSales.updateStockVolume(-tl.getMultiply(), tl.getProductID());
                            dlSales.updateStockVolume1(-tl.getMultiply(), tl.getProductID());
                            j++;
                        } else {
                            if (sqty == 0.0) {
		//						x=1;
                                JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "QT quantity Exceed the quantity in stock for \"" + tl.getProductName() + " \"", "Cannot Prepare QT", JOptionPane.WARNING_MESSAGE);
                            }
                            ticket1.deleteLine(j);
                            //j--;
                            for(int i=j;i<size;i++){
                                stk[i]=stk[i++];
                            }
                            
                            size--;
                        }
                    } else {
                        j++;
                    }
            }   
                
////////aaa..Start
                
  //           if(x==0){      
             Double amt=0.0;
             
              //////////////////////////////////////shiv
             System.out.println(ticket1.getTax());
             
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(name);
                         if(obj4[0].equals("yes")){
                              ticket1.getTax();
                              Object f= new Float(Math.round(ticket1.getTax()));
                              String st= f.toString();
                              Double taxst= Double.parseDouble(st); 
                              amt=ticket1.getSubTotal()+taxst;
                              System.out.println(taxst);
                             
                         }else if(obj4[0].equals("yesnearest")){
                              ticket1.getTax();
                              System.out.println(ticket1.getTax());
                                Object f= new Float(Math.round(ticket1.getTax()));
                                 String st= f.toString();
                                  Double taxst= Double.parseDouble(st); 
                                  amt=ticket1.getSubTotal()+taxst;
                                  System.out.println(taxst);
                         }else if(obj4[0].equals("yesnext")){
                              ticket1.getTax();
                              System.out.println(ticket1.getTax());
                                  Object f= new Float(Math.round(ticket1.getTax())+1);
                                  String st= f.toString();
                                   Double taxst= Double.parseDouble(st); 
                                  amt=ticket1.getSubTotal()+taxst;
                                  System.out.println(taxst);
                          }else if(obj4[0].equals("yesprevious")){
                               ticket1.getTax();
                               System.out.println(ticket1.getTax());
                                   Object f= new Float(Math.round(ticket1.getTax())-1);
                                   String st= f.toString();
                                   Double taxst= Double.parseDouble(st); 
                                   amt=ticket1.getSubTotal()+taxst;
                                   System.out.println(taxst);
                          }else{
                              
                                amt=ticket1.getTotal();
                                 
                            }
               
        //        String a = m_oTicket.getCustomerId();
        //        String a1 = a.substring(0,36);
                 
                                  
                   //////////////////////////////////////////////////////shiv
             
             
             
             
             
               
        //        String a = m_oTicket.getCustomerId();
        //        String a1 = a.substring(0,36);
                
                String cust=m_oTicket.getCustomerId();
                String id=cust;
                
       Object[] objm = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MEMTYPE FROM CUSTOMERS where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id.toString());
    //    String memtype = objm[0].toString();
     
        if(objm==null){
             String a = m_oTicket.getCustomerId();
                String a1 = a.substring(0,36);
                String gname ="Credit check for Guests";
                Object[] gobjm = (Object[]) new StaticSentence(m_App.getSession(), "SELECT MEMTYPE FROM CUSTOMERS where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(a1.toString());
                String memtype = gobjm[0].toString();
           
            Object[] gobj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id.toString());
                String gopb=gobj1[0].toString();
                   Double GOPB = new Double(gopb);
                   roundTwoDecimals(GOPB);
                    Double GSum = GOPB+amt;
                    GSum=roundTwoDecimals(GSum);
                    Object[] gobjqt = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM generaltable where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(gname);
                    Boolean GQTcheck = (Boolean)gobjqt[0];
                    
           if(GQTcheck==true){///Start of check
                        
          Object[] gobj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT DEBTMAX FROM memtype where id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memtype);
                     String dMax = gobj2[0].toString();
                  Double debtMax = new Double(dMax);
                  if((GSum>debtMax)){
                      //make a method for msg
              //to overcome the products reduction
                int s = 0;
                while (s < size) {
                    TicketLineInfo tl = ticket1.getLine(s);
                    if (tl.isStockCheckRequired()) {
                    //    Object o = dlSales.getStockVolume(tl.getProductID());
                        Double sqty = 0.0;
                        if (stk[s] != null) {
                            sqty = Double.parseDouble(stk[s].toString());
                        }
                        if (sqty >= tl.getMultiply()) {
                            dlSales.updateStockVolume(tl.getMultiply(), tl.getProductID());
                            dlSales.updateStockVolume1(tl.getMultiply(), tl.getProductID());
                            s++;
                        }
                    }else{
                        s++;
                    }
                }//end of while
             JOptionPane.showMessageDialog(this, "Please clear the Balance.Cannot prepare QT for it", "Amount limit Exceeded", JOptionPane.WARNING_MESSAGE);
         
                  }else{
                      amountUpdate(GSum,cust,ticket1,ticket2); 
                  }
           }else{
               amountUpdate(GSum,cust,ticket1,ticket2); 
           }
        }else{
        
        String memtype = objm[0].toString();
          Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CAST((CURROPB)AS DECIMAL(10,2)) FROM billingmember where ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id.toString());
                String opb=obj1[0].toString();
                   Double OPB = new Double(opb);
                   roundTwoDecimals(OPB);
                    Double Sum = OPB+amt;
                    Sum=roundTwoDecimals(Sum);
                   String name ="Credit check for QT";
          Object[] objqt = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM generaltable where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(name);
                    Boolean QTcheck = (Boolean)objqt[0];
                    
           if(QTcheck==true){///Start of check
                        
          Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT DEBTMAX FROM memtype where id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memtype);
                     String dMax = obj2[0].toString();
                  Double debtMax = new Double(dMax);
           if((Sum>debtMax)){///LimitCross>>>Start
                //to overcome the products reduction
                int s = 0;
                while (s < size) {
                    TicketLineInfo tl = ticket1.getLine(s);
                    if (tl.isStockCheckRequired()) {
                    //    Object o = dlSales.getStockVolume(tl.getProductID());
                        Double sqty = 0.0;
                        if (stk[s] != null) {
                            sqty = Double.parseDouble(stk[s].toString());
                        }
                        if (sqty >= tl.getMultiply()) {
                            dlSales.updateStockVolume(tl.getMultiply(), tl.getProductID());
                            dlSales.updateStockVolume1(tl.getMultiply(), tl.getProductID());
                            s++;
                        }
                    }else{
                        s++;
                    }
                }//end of while
             JOptionPane.showMessageDialog(this, "Please clear the Balance.Cannot prepare QT for it", "Amount limit Exceeded", JOptionPane.WARNING_MESSAGE);
         }else{
               amountUpdate(Sum,cust,ticket1,ticket2);
             }//End of else //if(Sum>5000)End
            }else{//if(gsa.checkQT()==true){////End of check
                        amountUpdate(Sum,cust,ticket1,ticket2);
                }
            }
//        } /////End of If(x==0);//Just to overcome the again initialization of printTotal
////////aaa..End
            } else {
                if (berror == true) {
                    JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot login", JOptionPane.OK_OPTION);
                }
            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreateqt"), e);
            msg.show(this);
        }
}//GEN-LAST:event_jButton1ActionPerformed




 public void amountUpdate(Double Sum,String cust,TicketInfo ticket1,TicketInfo ticket2){
     
        try{
            
            
            
            
            
         String BMName="Credit Check for billing member";
            String BName2 = "Facility for billing member";
            Object[] FacObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(BMName);
            
            if(FacObj!=null){
                 Boolean v5 = (Boolean)FacObj[0];
                  if(v5){
                      
                         ///////////       ///////////////////////     ////////////////////////////
                        new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Sum,cust});

                                  if(Sum>0){

                                       jTextField1.setForeground(Color.red);
                                      jTextField1.setText("Dr.  "+Sum);
                                         }
                                   else if(Sum<0){
                                       jTextField1.setForeground(Color.green);
                                       Sum = Sum*(-1);
                                       jTextField1.setText("Cr.  "+Sum);
                                           }
                                    else{jTextField1.setForeground(Color.black);
                                          jTextField1.setText(""+Sum);
                                          }       
                        ////////////////////////           ////////////////////////////////////      /////////////////////////
                      
                      
                      
                      
                      
                  }
                  else{
                      
                      
                      // code added by akash 
                       ////////////////////////           ////////////////////////////////////      /////////////////////////
                            String FacIDforBM = null;
                            Object s5 = m_App.getAppUserView().getUser().getWarehouse();
                            String warehouse5 = null;
                            if (s5 != null) {
                                String[] warehouses = s5.toString().split("#");
                                warehouse5 = warehouses[0];
                            }
                            Object obj5 = new StaticSentence(m_App.getSession(), "SELECT FACILITY FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(warehouse5);
                            if (obj5 != null) {
                                FacIDforBM = obj5.toString();
                            }
                     
                             Object[] Fac2Obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(BName2);
                             String FacStrFull  = Fac2Obj[0].toString();
                             String []strarr = FacStrFull.split("#");
                             String FinFacId = strarr[0];
                      
                      
                             
                             if(FinFacId.equals(FacIDforBM)){
                                 
                                  
                                    new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Sum,cust});

                                              if(Sum>0){

                                                   jTextField1.setForeground(Color.red);
                                                  jTextField1.setText("Dr.  "+Sum);
                                                     }
                                               else if(Sum<0){
                                                   jTextField1.setForeground(Color.green);
                                                   Sum = Sum*(-1);
                                                   jTextField1.setText("Cr.  "+Sum);
                                                       }
                                                else{jTextField1.setForeground(Color.black);
                                                      jTextField1.setText(""+Sum);
                                                      }       
                                  
                                 
                                 
                             }
                            ////////////////////////           ////////////////////////////////////      /////////////////////////
                      
                      
                      
                      
                      
                  }
                
               
            }
            else{
                
                        ///////////       ///////////////////////     ////////////////////////////
                        new PreparedSentence(m_App.getSession(), "UPDATE billingmember SET CURROPB=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING})).exec(new Object[]{Sum,cust});

                                  if(Sum>0){

                                       jTextField1.setForeground(Color.red);
                                      jTextField1.setText("Dr.  "+Sum);
                                         }
                                   else if(Sum<0){
                                       jTextField1.setForeground(Color.green);
                                       Sum = Sum*(-1);
                                       jTextField1.setText("Cr.  "+Sum);
                                           }
                                    else{jTextField1.setForeground(Color.black);
                                          jTextField1.setText(""+Sum);
                                          }       
                        ////////////////////////           ////////////////////////////////////      /////////////////////////
                
                
            }
            
            
                    
                    
                    
                    
                    
                    
                    
                    
                    
            
                createQTicket(ticket1);
                setActiveTicket(ticket2, m_oTicketExt);// set result ticket
                dlReceipts.updateSharedTicket(ticket2.getCustomerId(), ticket2, m_App.getAppUserView().getUser().getRole());
                //  dlReceipts.updateLastQtTimeOfSharedTicket(new Date(),ticket1.getCustomerId(), m_App.getAppUserView().getUser().getRole());
                for (int i = 0; i < m_oTicket.getLinesCount(); i++) {
                    m_ticketlines.removeTicketLine(0);
                }
                //praveen:exit for every transaction for kiosk mode---start
                if(m_App.getAppUserView().getUser().getTypeOfUser()==1)
                JPrincipalApp.m_approot.closeAppView();
                //praveen:exit for every transaction for kiosk mode---end
        }
        catch(Exception e){
            
        }
    }
    


private void m_jEditLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEditLineActionPerformed

        
        
        int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            TicketLineInfo oLine = m_oTicket.getLine(i);
            if (JProductLineRemarkEditnum1.showMessage(this, m_App, m_oTicket.getLine(i))) {
                // se ha modificado la linea
                paintTicketLine(i, oLine);
            }
        }
}//GEN-LAST:event_m_jEditLineActionPerformed

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
// TODO add your handling code here:
    // jTextField1.requestFocus();
    stateTransition1(evt.getSource());
          jTextField1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
           jTextField1.transferFocus();
        
    }
});
        
}//GEN-LAST:event_jTextField1ActionPerformed

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
// TODO add your handling code here:
    jTextField1.requestFocus();
      if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          
     stateTransition1(evt.getSource());
      jTextField1.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        jTextField1.transferFocus();
          
     
      }
    });
    
  }

      
}//GEN-LAST:event_jTextField1KeyReleased

private void m_jEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEnterActionPerformed

        stateTransition('\n');
}//GEN-LAST:event_m_jEnterActionPerformed

private void m_jKeyFactoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jKeyFactoryKeyTyped

        m_jKeyFactory.setText(null);
        stateTransition(evt.getKeyChar());
}//GEN-LAST:event_m_jKeyFactoryKeyTyped

private void m_prTicket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_prTicket1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_prTicket1ActionPerformed

private void btnSplit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSplit1ActionPerformed
          
    try {
            boolean berror = false;
            if (m_oTicket.getLinesCount() > 0) {
               ReceiptSplitnum1 splitdialog = ReceiptSplitnum1.getDialog(this, dlSystem.getResourceAsXML("Ticket.Line"), dlSales, dlCustomers, taxeslogic);

                TicketInfo ticket1 = m_oTicket.copyTicket();
                TicketInfo ticket2 = new TicketInfo();
                ticket2.setCustomer(m_oTicket.getCustomer());
                ticket2.setUser(ticket1.getUser());
                ticket2.setPlace(ticket1.getPlace());
                ticket2.setWaiter(ticket1.getWaiter());
                ticket2.setFloor(ticket1.getFloor());

                if (splitdialog.showDialog(ticket1, ticket2, m_oTicketExt)) {

                    Date date = new Date();
                    AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
                    if (obj != null) {
                        if (obj[0] != null) {
                            Date d = (Date) obj[0];
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(date.getTime());
                            cal2.setTimeInMillis(d.getTime());
                            if (cal1.before(cal2)) {
                                if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                                    berror = true;
                                } else {
                                    new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                                }
                            }
                        }
                    }
                    if (berror == false) {
                        int ticketsize = ticket2.getLines().size();
                        int j = 0;
                        // Boolean temp=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("StockCheckNotRequired");

                        // if(!temp)
                        while (j < ticket2.getLines().size()) {
                            TicketLineInfo tl = ticket2.getLine(j);

                            System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + tl.isStockCheckRequired());
                            Object stockcheck = new PreparedSentence(m_App.getSession(), "SELECT INVENTRYMAINTAIN FROM PRODUCTS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find(tl.getProductID().toString());
                            System.out.println(">>>>>>>>>>>>>>>>>tl.isStockCheckRequired() >>>>>>>>>>>>>>>>>>>>>>" + Boolean.valueOf(stockcheck.toString()));

                            if (tl.isStockCheckRequired()) {
                                // if(tl.getProductCategoryID()){
                                Object o = dlSales.getStockVolume(tl.getProductID());
                                Double sqty = 0.0;
                                if (o != null) {
                                    sqty = Double.parseDouble(o.toString());
                                }
                                if (sqty > 0) {
                                    dlSales.updateStockVolume(-tl.getMultiply(), tl.getProductID());
                                    dlSales.updateStockVolume1(-tl.getMultiply(), tl.getProductID());
                                    j++;
                                } else {
                                    JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);

                                    ticket2.deleteLine(j);
                                }
                            }
                            j++;
                        //   }

                        }
                        createQTicket(ticket2);
//                if (closeTicket(ticket2, m_oTicketExt)) { // already checked  that number of lines > 0
                        setActiveTicket(ticket1, m_oTicketExt);// set result ticket
                        dlReceipts.updateSharedTicket(ticket1.getCustomerId(), ticket1, m_App.getAppUserView().getUser().getRole());
                    //  dlReceipts.updateLastQtTimeOfSharedTicket(new Date(),ticket1.getCustomerId(), m_App.getAppUserView().getUser().getRole());
                    } else {
                        // if(berror==true){
                        JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot Create QT", JOptionPane.OK_OPTION);
                    // }
                    }
                }
                //praveen:exit for every transaction for kiosk mode---start
                if(m_App.getAppUserView().getUser().getTypeOfUser()==1)
                JPrincipalApp.m_approot.closeAppView();
                //praveen:exit for every transaction for kiosk mode---end

            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreateqt"), e);
            msg.show(this);
        }
    
}//GEN-LAST:event_btnSplit1ActionPerformed

private void m_jbtnScale1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnScale1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jbtnScale1ActionPerformed

private void m_jUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUp1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jUp1ActionPerformed

private void m_jDown1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDown1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jDown1ActionPerformed

private void m_jDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDelete1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jDelete1ActionPerformed

private void m_jList1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jList1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jList1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

private void m_jEditLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEditLine1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jEditLine1ActionPerformed

private void m_jEnter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEnter1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jEnter1ActionPerformed

private void m_jKeyFactory1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jKeyFactory1KeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_m_jKeyFactory1KeyTyped

private void m_jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_m_jNumberKeys1KeyPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jNumberKeys1KeyPerformed

private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField2ActionPerformed

private void m_jNumberKeysKeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_m_jNumberKeysKeyPerformed

        stateTransition(evt.getKey());
}//GEN-LAST:event_m_jNumberKeysKeyPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSplit;
    private javax.swing.JButton btnSplit1;
    private javax.swing.JPanel catcontainer;
    private javax.swing.JPanel catcontainer1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel m_jButtons;
    private javax.swing.JPanel m_jButtons1;
    private javax.swing.JPanel m_jButtonsExt;
    private javax.swing.JPanel m_jButtonsExt1;
    private javax.swing.JPanel m_jContEntries;
    private javax.swing.JPanel m_jContEntries1;
    private javax.swing.JButton m_jDelete;
    private javax.swing.JButton m_jDelete1;
    private javax.swing.JButton m_jDown;
    private javax.swing.JButton m_jDown1;
    private javax.swing.JButton m_jEditLine;
    private javax.swing.JButton m_jEditLine1;
    private javax.swing.JButton m_jEnter;
    private javax.swing.JButton m_jEnter1;
    private javax.swing.JTextField m_jKeyFactory;
    private javax.swing.JTextField m_jKeyFactory1;
    private javax.swing.JLabel m_jLblTotalEuros1;
    private javax.swing.JLabel m_jLblTotalEuros2;
    private javax.swing.JLabel m_jLblTotalEuros3;
    private javax.swing.JLabel m_jLblTotalEuros4;
    private javax.swing.JLabel m_jLblTotalEuros5;
    private javax.swing.JLabel m_jLblTotalEuros6;
    private javax.swing.JButton m_jList;
    private javax.swing.JButton m_jList1;
    private com.openbravo.beans.JNumberKeys m_jNumberKeys;
    private com.openbravo.beans.JNumberKeys m_jNumberKeys1;
    private javax.swing.JPanel m_jOptions;
    private javax.swing.JPanel m_jOptions1;
    private javax.swing.JPanel m_jPanContainer;
    private javax.swing.JPanel m_jPanContainer1;
    private javax.swing.JPanel m_jPanEntries;
    private javax.swing.JPanel m_jPanEntries1;
    private javax.swing.JPanel m_jPanTicket;
    private javax.swing.JPanel m_jPanTicket1;
    private javax.swing.JPanel m_jPanTotals;
    private javax.swing.JPanel m_jPanTotals1;
    private javax.swing.JPanel m_jPanelBag;
    private javax.swing.JPanel m_jPanelBag1;
    private javax.swing.JPanel m_jPanelCentral;
    private javax.swing.JPanel m_jPanelCentral1;
    private javax.swing.JPanel m_jPanelScripts;
    private javax.swing.JPanel m_jPanelScripts1;
    private javax.swing.JLabel m_jPor;
    private javax.swing.JLabel m_jPor1;
    private javax.swing.JLabel m_jPrice;
    private javax.swing.JLabel m_jPrice1;
    private javax.swing.JLabel m_jSubtotalEuros;
    private javax.swing.JLabel m_jSubtotalEuros1;
    private javax.swing.JComboBox m_jTax;
    private javax.swing.JComboBox m_jTax1;
    private javax.swing.JLabel m_jTaxesEuros;
    private javax.swing.JLabel m_jTaxesEuros1;
    private javax.swing.JLabel m_jTicketId;
    private javax.swing.JLabel m_jTicketId1;
    private javax.swing.JLabel m_jTotalEuros;
    private javax.swing.JLabel m_jTotalEuros1;
    private javax.swing.JButton m_jUp;
    private javax.swing.JButton m_jUp1;
    private javax.swing.JToggleButton m_jaddtax;
    private javax.swing.JToggleButton m_jaddtax1;
    private javax.swing.JButton m_jbtnScale;
    private javax.swing.JButton m_jbtnScale1;
    private javax.swing.JButton m_prTicket;
    private javax.swing.JButton m_prTicket1;
    // End of variables declaration//GEN-END:variables

   
    

}
