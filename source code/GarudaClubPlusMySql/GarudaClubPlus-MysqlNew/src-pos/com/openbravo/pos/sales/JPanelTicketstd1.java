package com.openbravo.pos.sales;

import java.util.Locale;
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
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteBuilder;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.clubmang.CaluculateFacilityMatrix.customer;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.FacilityLimitCheck;
import com.openbravo.pos.clubmang.GeneralReceiptTableModel;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.CardSwipeNotifier;

import com.openbravo.pos.forms.JPrincipalApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.payment.BankInfo;
import com.openbravo.pos.payment.ChequeDetails;
import com.openbravo.pos.payment.JPaymentInterface;
import com.openbravo.pos.payment.JPaymentSelect.JPaymentCreator;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.JPaymentSelectReceiptstd1;
import com.openbravo.pos.payment.JPaymentSelectRefund;
import com.openbravo.pos.payment.JPaymentSelectRefundstd1;
import com.openbravo.pos.payment.JPaymentSelectstd1;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;

import com.openbravo.pos.sales.restaurant.DebtBillList;
import com.openbravo.pos.sales.restaurant.DebtBillListstd1;


import com.openbravo.pos.sales.shared.JTicketsBagSharedListstd1;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.util.JRPrinterAWT300;
import com.openbravo.pos.util.ReportUtils;
import com.openbravo.pos.util.StringUtils;
import cos.card.acs.Cosacs;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.print.PrintService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
  import java.util.List;

/**
 *
 * @author adrianromero
 */
public abstract class JPanelTicketstd1 extends JPanel implements JPanelView, BeanFactoryApp, TicketsEditor,CardSwipeNotifier {

    // Variable numerica
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
    private JTicketsBagstd1 m_ticketsbag;
    private SentenceList senttax;
    private ListKeyed taxcollection;
    // private ComboBoxValModel m_TaxModel;
    private SentenceList senttaxcategories;
    private ListKeyed taxcategoriescollection;
    private ComboBoxValModel taxcategoriesmodel;
    private TaxesLogic taxeslogic;
//    private ScriptObject scriptobjinst;
    protected JPanelButtonsstd1 m_jbtnconfig;
   
    protected DataLogicSystem dlSystem;
    protected DataLogicSales dlSales;
      
    protected DataLogicCustomers dlCustomers;
    protected DataLogicReceiptsstd1 dlReceipts;
    protected Qticket qTicket1;
    private JPaymentSelectstd1 paymentdialogreceipt;
    private JPaymentSelectstd1 paymentdialogrefund;
    private BillLogicstd1 dlBill;
    private AppView app;
    private BillLogicApply bla;
   
    private BillInfostd1 binfo;
    private ArrayList<TicketInfo> m_ticketList;
    private ArrayList<BillInfo> m_billList;
     public CustomerInfoExt customer;
   
     private DataLogicFacilities dlfac;
     private String prcategory;
     private CustomerInfo customertemp;
     private QticketInfo qTicket;
      private GeneralReceiptTableModel grmodel;
       protected AppView m_App;
       private AppView m_App1;
       private String m_sCurrentTicket = null;
        private CardReader cr;
         private boolean flag = true;
      private double totalamt=0;
            protected TicketsEditor m_panelticket;    
            private String card;
             private String initiator;
        private FacilityLimitCheck flcheck;
     
       private boolean resultok = false;
      private PaymentInfo pinfo12;
      private String cust;
    
       
        private double m_dTicket;
        private String m_sName;
           
    /** Creates new form JTicketView */
    public JPanelTicketstd1() {

        initComponents();
           this.customertemp = customer;
    }
    
    
  
    

    @Override
    public void init(AppView app) throws BeanFactoryException {
         
        m_App = app;
        dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlCustomers = (DataLogicCustomers) m_App.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dlReceipts = (DataLogicReceiptsstd1) app.getBean("com.openbravo.pos.sales.DataLogicReceiptsstd1");
            dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         this.dlBill = (BillLogicstd1)m_App.getBean("com.openbravo.pos.sales.BillLogicstd1");
         
         dlBill.setDataLogicSales(dlSales);
         dlBill.setAppView(m_App);

        qTicket1 = (Qticket) m_App.getBean("com.openbravo.pos.sales.Qticket");
        qTicket1.setDataLogicSales(dlSales);
        qTicket1.setAppView(m_App);
           this.customertemp = customer;

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
        m_jbtnconfig = new JPanelButtonsstd1("Ticket.Buttons", this);
        m_jButtonsExt.add(m_jbtnconfig);

        // El panel de los productos o de las lineas...        
        catcontainer.add(getSouthComponent(), BorderLayout.CENTER);

        // El modelo de impuestos
        senttax = dlSales.getTaxList();
        senttaxcategories = dlSales.getTaxCategoriesList();

        taxcategoriesmodel = new ComboBoxValModel();
          jPanel5.setLayout(new java.awt.CardLayout());
      //     m_jContEntries.setLayout(new java.awt.CardLayout());
         // m_jPanContainer.setLayout(new java.awt.CardLayout());
          // m_jPanEntries.setLayout(new java.awt.BoxLayout());
         //  m_jNumberKeys.setLayout(new java.awt.BorderLayout());
           
         // m_jPanelBag.setLayout(new java.awt.CardLayout());
         // jPanel3.setLayout(new java.awt.CardLayout()); 
       //    m_jOptions.setLayout(new java.awt.CardLayout()); 
        // ponemos a cero el estado
        stateToZero();
         startCardReader();
        // inicializamos
        m_oTicket = null;
        m_oTicketExt = null;
        customer=null;
         resultok = bla == null;
          ////////////////////SHIV:SEPT -22
        
         java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                //akshatha:to read a card from card reader without port num
               String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
        String CardRead = m_App.getProperties().getProperty("ACScard.port");
		if(cardReaderPortName.isEmpty() && CardRead.isEmpty()  ){
                    jTextField3.setEditable(true);
                    
                    jTextField3.requestFocus();
                  

                } else {


                    jTextField2.requestFocus();
                    jTextField3.setEditable(false);
                    jButton10.setVisible(true);
                     
                   
                   
                }
            }
        });
         /////////////////////////////////
         
         
         
       
    }

    public void customerdetail(CustomerInfoExt m_Customer,JTicketsBagstd1 m_ticketsbag,String card)
    {
         String custoid;
        //String card = cr.getData();


        if (card.length() > 0) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?   UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    m_Customer = dlSales.loadCustomerExt(custoid);
                    
                    setInitiator(obj[4].toString());
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
    
    }
     public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
       public String getInitiator() {
        return initiator;
    }
    
    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public void activate() throws BasicException {
        startCardReader();
        paymentdialogreceipt = JPaymentSelectReceiptstd1.getDialog(this);
        paymentdialogreceipt.init(m_App);
        paymentdialogrefund = JPaymentSelectRefundstd1.getDialog(this);
        paymentdialogrefund.init(m_App);
        jPanel3.setVisible(true);
        jButton9.setText("Load");
        
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
     //   btnSplit.setEnabled(false);
        m_jDelete.setEnabled(m_App.getAppUserView().getUser().hasPermission("sales.EditLines"));
        m_jNumberKeys.setMinusEnabled(m_App.getAppUserView().getUser().hasPermission("sales.EditLines"));
//        m_jNumberKeys.setEqualsEnabled(m_App.getAppUserView().getUser().hasPermission("sales.Total"));
        m_jNumberKeys.setEqualsEnabled(true);
        m_jbtnconfig.setPermissions(m_App.getAppUserView().getUser());

        m_ticketsbag.activate();
        String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
            jButton1.setText("QT");
            jPanel3.setVisible(false);
    
        } else if (ticketBag.equals("standard")) {
            jButton1.setText("Bill");
            
        
            m_jTicketId.setVisible(true); 
       //     m_jTicketId1.setVisible(false);
             String sresource1 = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.standardbill");
        
        
        }
        ////////////////////SHIV:SEPT -22
        
         java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                //akshatha:to read a card from card reader without port num
               String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
        String CardRead = m_App.getProperties().getProperty("ACScard.port");
		if(cardReaderPortName.isEmpty() && CardRead.isEmpty()  ){
                    jTextField3.setEditable(true);
                    
                    jTextField3.requestFocus();
                  

                } else {


                    jTextField2.requestFocus();
                    jTextField3.setEditable(false);
                
                    jButton10.setVisible(true);
                  ////  loadMemberDetails(card);
                    
                   
                }
            }
        });
         /////////////////////////////////
         // selectValidTicket();  
         
    }

    public boolean deactivate() {

        return m_ticketsbag.deactivate();

    }

    protected abstract JTicketsBagstd1 getJTicketsBag();

    protected abstract Component getSouthComponent();

    public void setActiveTicket(TicketInfo oTicket, Object oTicketExt) {

        m_oTicket = oTicket;
        m_oTicketExt = oTicketExt;

        executeEvent(m_oTicket, m_oTicketExt, "ticket.show");

        refreshTicket();
    }

    public TicketInfo getActiveTicket() {
        return m_oTicket;
    }
    public CustomerInfo getActiveCustomer() {
        return customer;
    }

    private void refreshTicket() {

        CardLayout cl = (CardLayout) (getLayout());

        if (m_oTicket == null) {
            m_jTicketId.setText(null);
     //        m_jTicketId1.setText(null);
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
             String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
            m_jTicketId.setText(m_oTicket.getName(m_oTicketExt));
        } else if (ticketBag.equals("standard")) {
       
            m_jTicketId.setVisible(true);
            m_jTicketId.setText(null);
        
          
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
            m_jTaxesEuros.setText(m_oTicket.printTax());
            m_jTotalEuros.setText(m_oTicket.printTotal());
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

    private void addTicketLine(ProductInfoExt oProduct, double dMul, double dPrice) {

        TaxInfo tax = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo(), m_oTicket.getCustomer());
        TaxInfo tax2 = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo2(), m_oTicket.getCustomer());                                                             // edited by aakash
        TaxInfo tax3 = taxeslogic.getTaxInfo(oProduct.getTaxCategoryInfo3(), m_oTicket.getCustomer());
        
        Boolean Basic2 = oProduct.getBASIC2();
        Boolean Basic3 = oProduct.getBASIC3();
        
        addTicketLine(new TicketLineInfo(oProduct, dMul, dPrice, tax, (java.util.Properties) (oProduct.getProperties().clone())  , tax2 , tax3 , Basic2 , Basic3  ));
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

    private void incProduct(ProductInfoExt prod) {

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

    private void incProduct(double dPor, ProductInfoExt prod) {
        // precondicion: prod != null
        addTicketLine(prod, dPor, prod.getPriceSell());
    }

    protected void buttonTransition(ProductInfoExt prod) {
        // precondicion: prod != null

        if (m_iNumberStatusInput == NUMBERZERO && m_iNumberStatusPor == NUMBERZERO) {
            incProduct(prod);
        } else if (m_iNumberStatusInput == NUMBERVALID && m_iNumberStatusPor == NUMBERZERO) {
            incProduct(getInputValue(), prod);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private void addUnits(double dUnits) {
        int i = m_ticketlines.getSelectedIndex();
        if (i >= 0) {
            TicketLineInfo oLine = m_oTicket.getLine(i);
            double qty = oLine.getMultiply() + dUnits;
            if (qty > 0) {
                oLine.setMultiply(oLine.getMultiply() + dUnits);
                // paintTicketLine(i, oLine);
            } else {
                JOptionPane.showMessageDialog(null, "The Total Quantity Should Be Greater Then Zero");
            }
            paintTicketLine(i, oLine);
            /*   InventoryLine inv = m_invlines.getLine(i);
            double dunits = inv.getMultiply() + dUnits;
            if (dunits == 0.0) {
            deleteLine(i);
            } else {
            inv.setMultiply(inv.getMultiply() + dUnits);
            m_invlines.setLine(i, inv);
            }*/
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

    
    
    public void startCardReader() {
        try {
            String portNumber = m_App.getProperties().getProperty("card.portnumber");
            boolean cardAccessOnlyFlag = false;
            if (m_App.getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf(m_App.getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
            cr.setCardSwipeNotifier(this);
            System.out.println(portNumber);
              cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
  
    
    
    //shivaprasad: created on 27aug for std Bill for inserting into Bill, Billitem,Receipts and payment table and for printing bills 
    
    private void printstdBill(final TicketInfo ticket,final TicketInfo customerT,final List<PaymentInfo> pinfo,final BillInfostd1 binf,PaymentInfo p1){
        String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("standard")) {
                      jButton1.setText("Bill");
              String sresource1 = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.standardbill");
            try {
                
                AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
              
                TicketLineInfo tl =new TicketLineInfo();
                PaymentInfo p4=pinfo.get(0);
                p4.getName();
              
  //    for (PaymentInfo p : pinfo) {
              binfo=new BillInfostd1();
        
              binfo.getReceiptRef();
           
                 taxeslogic.calculateTaxes(ticket);
         
                String type= p4.getName();
              
           //shiv:To save bill enters into bill and billitem table
             dlBill.saveBillstd(binfo,null,null,type,customerT,ticket);
             if("debt".equals(p4.getName())){
                 
             }else{
                 //dlSalesstd = (DataLogicSalesstd1) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesstd1");
              //   this.dlSalesstd = (DataLogicSalesstd1) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesstd1");
                payaccountstd(binfo, m_App.getInventoryLocation(),true,ticket,customerT);
             }
             
             /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
             
           //  shiv:added for displaying members accbal////////////////////////////////
               Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                        "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? GROUP BY A.ACCOUNTID " +
                        "UNION ALL " +
                        "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? GROUP BY A.ACCOUNTID) " +
                        "AS TOTAL GROUP BY ACC",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{customer.getId(), customer.getId()});
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
             /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          //  dlBill.saveBillstd(binfo,null,null,type,customer,ticket);
            
          
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("taxes", taxcollection);
            script.put("taxeslogic", taxeslogic);
            script.put("ticket",ticket);
            script.put("bt",  binfo);
            script.put("ticketline",tl);
            script.put("pinfo", pinfo);
            script.put("ticket1",customerT);
            script.put("binfo",binfo);
            script.put("balance", accountBalance);
          
          
            m_TTP.printTicket(script.eval(sresource1).toString());
             System.out.print(binfo.getID());
             
             
             
            
              ////////////////if it is for debt/////////////////
                         if("debt".equals(p4.getName())){
                                 
                    // put here credit confirmetion receipt // 
                           
            
                              binfo.setCreatedBy(m_App.getAppUserView().getUser().getName()); 
                             binfo.getCreatedBy();
                             printTicketstd("Printer.Creditconf_std", binfo, "cerditconf",ticket,taxeslogic,taxcollection,customerT);
                                                       
                              }
          ////////////if it is for debt////////////////////
             
        
        
   //  }
           
        
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
    
    //////////////////////////////////////////////////shiv:16oct////////////////////////////////////////////////////////////////////////////////
    
      public final String getNextReceiptID1(String createdby) throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String receiptnum;
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();

        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = (SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ID=? AND ROLES.ID=PEOPLE.ROLE)  AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});

            return receiptnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
            return null;
        }
    }
    
    
    
    //shiv:created for change to cash bill std///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      private int flag1 = 0;
      private boolean perror;
      private boolean berror;
      
         public BillLogicstd1 getDataLogicBillstd() {
                     return dlBill;
                                }
          public final SentenceList getBankList() {
        return new StaticSentence(m_App.getSession(), "SELECT ID, BANKNAME FROM BANK ORDER BY BANKNAME", null, new SerializerReadClass(BankInfo.class));
    }
          
          
      
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //shiv: created for std billing which will update the receipts generated during debt,CASH and cheque in payments
      public final String payaccountstd(final BillInfostd1 ticket, final String location, final boolean flag,TicketInfo ticket1,final TicketInfo ticket2) throws BasicException {
        flag1 = 0;
       // receiptno = null;
        perror = false;
   //     Transaction t = new Transaction(s) {
           
             
     //       public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENCASHTIME FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(userid);
                if (obj != null) {
                    if (obj[0] != null) {
                        Date d = (Date) obj[0];
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTimeInMillis(date.getTime());
                        cal2.setTimeInMillis(d.getTime());
                        if (cal1.before(cal2)) {
                            JOptionPane.showMessageDialog(null, "Bill Time is less than Open Cash Time.Previous Open Cash Time is " + d + " .Please reset your system time or consult your system admin", "Error-System Time was reset", JOptionPane.OK_OPTION);
                            perror = true;

                        }
                    }
                }
                if (perror == false) {
                //    if (ticket.getReceiptRef() == null) {
                        final String rno = ticket.getReceiptRef();
                       // receiptno = rno;
                       // ticket.setReceiptRef(rno);
                        if (rno == null) {
                            flag1 = 1;
                      //      return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                //    }
                    // new receipt
                    if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(m_App.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, ticket.getPlace());//contains description for general receipts
                            }
                        });
                    } else {
                        new PreparedSentence(m_App.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                            }
                        });
                    }

                    SentenceExec paymentinsert = new PreparedSentence(m_App.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                   
                    for (final PaymentInfo p :   ticket1.getPayments()) {
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                setString(3, p.getName());
                                setDouble(4, p.getTotal());
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket2.getCustomerId());
                            }
                        });

                        /*     if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {
                        getDebtUpdate().exec(new Object[]{
                        ticket.getCustomer().getId(),
                        new Double(p.getTotal()),
                        date
                        });
                        }*/

                        if ("cheque".equals(p.getName())) {
                            SentenceExec chequeInsert = new PreparedSentence(m_App.getSession(), "INSERT INTO CHEQUE(ID, CHEQUENO, BANK,RNO,HOLDER,AMOUNT) VALUES (?, ?, ?,?,?,?)", SerializerWriteParams.INSTANCE);
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
                                SentenceExec bankInsert = new PreparedSentence(m_App.getSession(), "INSERT INTO BANK (ID, BANKNAME) VALUES (?, ?)", SerializerWriteParams.INSTANCE);
                                bankInsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, cd.getBank());
                                    }
                                });
                            }
                        }
                    }
                 System.out.println(ticket.getID());
       new PreparedSentence(m_App.getSession(), "UPDATE BILL SET RECEIPT=(SELECT  ID FROM RECEIPTS WHERE ID=?) WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{ticket.getReceiptRef(),ticket.getID()});
    
                    
                }
                return null;
    //        }
           
     //   };
     //   t.execute();
        
    //    if (perror == false && flag1 == 0) {
       //     return receiptno;
      //  } else {
      //      return "false";
      //  }
    }
    
    //:end  of payaccountstd
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
       
       
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
     private void printTicketstd(String sresourcename, BillInfostd1 binfo, Object ticketext,TicketInfo ticket1,TaxesLogic taxeslogic,ListKeyed taxcollection,final TicketInfo customerT) {

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
                List<PaymentInfo> pi = ticket1.getPayments();
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

             ///   WaiterInfo w = LookupUtilityImpl.getInstance(null).getWaiterMap().get(ticket.getWaiter());
                ///waitername = w.getName();
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(binfo.getPlace());

                if (obj1 == null) {
                    table = "";
                } else {
                    table = obj1[0].toString();
                }
                // PlacesInfo p= LookupUtilityImpl.getInstance(null).getPlacesMap().get(ticket.getPlace());

                //  table=p.getName();
                //  try{


                //warehouse changes -start
              //  Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,ROLE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(ticket.getCreatedBy());
              /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                 Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT prcategories from people  WHERE NAME=?", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(binfo.getCreatedBy());
                String[] str = null;
                if (obj2[0] != null) {
                    str = obj2[0].toString().split("#");
                }
              //////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
                
                
                
                /*if(obj[5]==null)
                obj[5]=new Icon();*/

                String warehouse = null;
                if (obj2 != null && obj2[0] != null) {
                    String[] wArr = obj2[0].toString().split("#");
                    warehouse = wArr[0];
                }
             //   AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString(), warehouse);
                //warehouse changes -end
             //   appuser.fillPermissions(dlSystem);
             //   boolean flag1 = appuser.hasPermission("bar counter");

                //  ticket.getWaiter();
               
                boolean flag = m_App.getAppUserView().getUser().hasPermission("bar counter");
                boolean crconf = false;
                if (ticketext.equals("cerditconf")) {
                    crconf = true;
                }
                
      ////////////////////////////////////////////////////////////////////////////////////////////
              //     boolean flag = m_App.getAppUserView().getUser().hasPermission("Bar Counter");
               
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE,  new SerializerReadBasic(new Datas[]{Datas.STRING})).find(str[0].toString());
                String name = null;
                if (obj3 != null && obj3[0] != null) {
                    name = obj3[0].toString();
                }
     //////////////////////////////////////////////////////////////////////////////////////////////
                
                taxeslogic.calculateTaxes(ticket1);
              //  String[] str = null;
            //    if (obj2[3] != null) {
            //        str = obj2[3].toString().split("#");
            //    }
            //    Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(str[0].toString());
            ///    String name = null;
             //   if (obj3 != null && obj3[0] != null) {
             //       name = obj3[0].toString();
             //   }
                //shiv:added to display member's account balance
                Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                        "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? GROUP BY A.ACCOUNTID " +
                        "UNION ALL " +
                        "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? GROUP BY A.ACCOUNTID) " +
                        "AS TOTAL GROUP BY ACC",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{customer.getId(), customer.getId()});
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
                script.put("ticket1", ticket1);
                 script.put("ticket",binfo);
                script.put("place", table);
                script.put("flag", flag);
                 script.put("ticketcust",customerT);
            //    script.put("flag1", flag1);
         //      script.put("waiter", waitername);
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
    
    
    

    private boolean saveBillstd(String type,List<PaymentInfo> pinfo) throws Exception {
         for (PaymentInfo p : pinfo) {
          pinfo.add(p.copyPayment());
             p.getName();
             
       
 //   boolean flag = bla.saveBillstd(type);
            if(type.equals(p.getName())){
                 saveBillstd("Debt",null);
                
            }
         }       
      return flag;
     
    }
    

   
    
    private void saveAndPrintQTs(final Collection<QticketInfo> qts) throws Exception {
          
              Transaction t = new Transaction(LookupUtilityImpl.getInstance(null).getAppView().getSession()) {

            public Object transact() throws BasicException {
                for (QticketInfo qtInfo : qts) {
                    boolean flag = qTicket1.saveQTicket(qtInfo);
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
          String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
            jButton1.setText("QT");
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
         
          
        } else if (ticketBag.equals("standard")) {
            jButton1.setText("Bill");
             String sresource1 = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.standardbill");
                  String waitername;
        String table1 = qTicket.getPlace();
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();

            if (obj == null) {
                waitername = "";
            } else {
             //   waitername = obj[0].toString();
            }
            Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();

            if (obj1 == null) {
              //  table1 = "";
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
           // script.put("waiter", waitername);
            script.put("flag", flag1);
            //script.put("ticket", qTicket);
            //script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource1).toString());
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
                JPaymentSelectstd1 paymentdialog = ticket.getTotal() >= 0.0
                        ? paymentdialogreceipt
                        : paymentdialogrefund;
                paymentdialog.setPrintSelected("true".equals(m_jbtnconfig.getProperty("printselected", "true")));

                if (paymentdialog.showDialog(ticket.getTotal(), ticket.getCustomer(), m_App.getAppUserView().getUser().getName(), true,null,pinfo12)) {

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
        
        String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
            jButton1.setText("QT");
             String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.QT");
                 sresource = dlSystem.getResourceAsXML(sresourcename);
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(JPanelTicketstd1.this);
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
                Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(JPanelTicketstd1.this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(JPanelTicketstd1.this);
            }
        }
          
        } else if (ticketBag.equals("standard")) {
            jButton1.setText("Bill");
             String sresource1 = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.standardbill");
                 sresource1 = dlSystem.getResourceAsXML(sresourcename);
        if (sresource1 == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(JPanelTicketstd1.this);
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
                m_TTP.printTicket(script.eval(sresource1).toString());
            } catch (BasicException ex) {
                Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(JPanelTicketstd1.this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(JPanelTicketstd1.this);
            }
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
        String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
            jButton1.setText("QT");
           // m_jOptions.setVisible(true);
             //j_tktbgshrd.setVisible(false);
            String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.QT");
            if (oLine == null) {
            m_App.getDeviceTicket().getDeviceDisplay().clearVisor();
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("ticketline", oLine);
                m_TTP.printTicket(script.eval(dlSystem.getResourceAsXML("Printer.TicketLine")).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintline"), e);
                msg.show(JPanelTicketstd1.this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintline"), e);
                msg.show(JPanelTicketstd1.this);
            }
        }
        } else if (ticketBag.equals("standard")) {
            jButton1.setText("Bill");
            m_jTicketId.setVisible(true);
            //j_tktbgshrd.setVisible(true);
            //m_jOptions.setVisible(false);
            String sresource1 = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.standardbill");
            if (oLine == null) {
            m_App.getDeviceTicket().getDeviceDisplay().clearVisor();
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("ticketline", oLine);
                m_TTP.printTicket(script.eval(dlSystem.getResourceAsXML("Printer.TicketLine")).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintline"), e);
                msg.show(JPanelTicketstd1.this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintline"), e);
                msg.show(JPanelTicketstd1.this);
            }
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
        } else {
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
                return JPanelTicketstd1.this.getInputValue();
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
            JPanelTicketstd1.this.printReport(resourcefile, ticket, ticketext);
        }

        public void printTicket(String sresourcename) {
            JPanelTicketstd1.this.printTicket(sresourcename, ticket, ticketext);
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

        jPanel1 = new javax.swing.JPanel();
        m_jPanContainer = new javax.swing.JPanel();
        m_jOptions = new javax.swing.JPanel();
        m_jButtons = new javax.swing.JPanel();
        m_jTicketId = new javax.swing.JLabel();
        m_jListTickets = new javax.swing.JButton();
        m_jPanelScripts = new javax.swing.JPanel();
        m_jButtonsExt = new javax.swing.JPanel();
        m_jPanelBag = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        m_jDelTicket1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
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
        m_jPanelCentral = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        m_jPanTotals = new javax.swing.JPanel();
        m_jTotalEuros = new javax.swing.JLabel();
        m_jLblTotalEuros1 = new javax.swing.JLabel();
        m_jSubtotalEuros = new javax.swing.JLabel();
        m_jTaxesEuros = new javax.swing.JLabel();
        m_jLblTotalEuros2 = new javax.swing.JLabel();
        m_jLblTotalEuros3 = new javax.swing.JLabel();
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
        catcontainer = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 204, 153));
        setLayout(new java.awt.CardLayout());

        m_jPanContainer.setLayout(new java.awt.BorderLayout());

        m_jTicketId.setBackground(java.awt.Color.white);
        m_jTicketId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_jTicketId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTicketId.setOpaque(true);
        m_jTicketId.setPreferredSize(new java.awt.Dimension(160, 25));
        m_jTicketId.setRequestFocusEnabled(false);

        m_jListTickets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/unsortedList.png"))); // NOI18N
        m_jListTickets.setFocusPainted(false);
        m_jListTickets.setFocusable(false);
        m_jListTickets.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jListTickets.setRequestFocusEnabled(false);
        m_jListTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jListTicketsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout m_jButtonsLayout = new org.jdesktop.layout.GroupLayout(m_jButtons);
        m_jButtons.setLayout(m_jButtonsLayout);
        m_jButtonsLayout.setHorizontalGroup(
            m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, m_jButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .add(m_jTicketId, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jListTickets))
        );
        m_jButtonsLayout.setVerticalGroup(
            m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .add(m_jButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jTicketId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(m_jListTickets))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        m_jPanelScripts.setLayout(new java.awt.BorderLayout());

        m_jButtonsExt.setLayout(new javax.swing.BoxLayout(m_jButtonsExt, javax.swing.BoxLayout.LINE_AXIS));
        m_jPanelScripts.add(m_jButtonsExt, java.awt.BorderLayout.LINE_END);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel6.setText("Mem No:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton2.setToolTipText("Member list");
        jButton2.setMargin(new java.awt.Insets(8, 14, 8, 14));
        jButton2.setPreferredSize(new java.awt.Dimension(48, 36));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel1.setText("   card:");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        m_jDelTicket1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editdelete.png"))); // NOI18N
        m_jDelTicket1.setFocusPainted(false);
        m_jDelTicket1.setFocusable(false);
        m_jDelTicket1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDelTicket1.setRequestFocusEnabled(false);
        m_jDelTicket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDelTicket1ActionPerformed(evt);
            }
        });

        jButton7.setText("Cr.Co");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("Load");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(m_jDelTicket1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton9)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(m_jDelTicket1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton9)
                        .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout m_jPanelBagLayout = new org.jdesktop.layout.GroupLayout(m_jPanelBag);
        m_jPanelBag.setLayout(m_jPanelBagLayout);
        m_jPanelBagLayout.setHorizontalGroup(
            m_jPanelBagLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanelBagLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        m_jPanelBagLayout.setVerticalGroup(
            m_jPanelBagLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_jPanelBag, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(128, 128, 128)
                .add(m_jbtnScale)
                .add(222, 222, 222)
                .add(m_jPanelScripts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        m_jOptionsLayout.setVerticalGroup(
            m_jOptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanelScripts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(m_jOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .add(m_jbtnScale))
            .add(m_jOptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                .add(org.jdesktop.layout.GroupLayout.LEADING, m_jPanelBag, 0, 69, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, m_jButtons, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        m_jPanContainer.add(m_jOptions, java.awt.BorderLayout.NORTH);

        m_jPanTicket.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        m_jPanTicket.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jPanel2.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

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
        jPanel2.add(m_jUp);

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
        jPanel2.add(m_jDown);

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
        jPanel2.add(m_jDelete);

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
        jPanel2.add(m_jList);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

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
        jPanel2.add(m_jEditLine);

        jPanel5.add(jPanel2, java.awt.BorderLayout.NORTH);

        m_jPanTicket.add(jPanel5, java.awt.BorderLayout.LINE_END);

        m_jPanelCentral.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        m_jPanTotals.setLayout(new java.awt.GridBagLayout());

        m_jTotalEuros.setBackground(java.awt.Color.white);
        m_jTotalEuros.setFont(new java.awt.Font("Dialog", 1, 14));
        m_jTotalEuros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        m_jTotalEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTotalEuros.setOpaque(true);
        m_jTotalEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jTotalEuros.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        m_jPanTotals.add(m_jTotalEuros, gridBagConstraints);

        m_jLblTotalEuros1.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        m_jPanTotals.add(m_jLblTotalEuros1, gridBagConstraints);

        m_jSubtotalEuros.setBackground(java.awt.Color.white);
        m_jSubtotalEuros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        m_jSubtotalEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jSubtotalEuros.setOpaque(true);
        m_jSubtotalEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jSubtotalEuros.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        m_jPanTotals.add(m_jSubtotalEuros, gridBagConstraints);

        m_jTaxesEuros.setBackground(java.awt.Color.white);
        m_jTaxesEuros.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        m_jTaxesEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTaxesEuros.setOpaque(true);
        m_jTaxesEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jTaxesEuros.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        m_jPanTotals.add(m_jTaxesEuros, gridBagConstraints);

        m_jLblTotalEuros2.setText(AppLocal.getIntString("label.taxcash")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        m_jPanTotals.add(m_jLblTotalEuros2, gridBagConstraints);

        m_jLblTotalEuros3.setText(AppLocal.getIntString("label.subtotalcash")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        m_jPanTotals.add(m_jLblTotalEuros3, gridBagConstraints);

        jPanel4.add(m_jPanTotals, java.awt.BorderLayout.LINE_END);

        m_jPanelCentral.add(jPanel4, java.awt.BorderLayout.SOUTH);

        m_jPanTicket.add(m_jPanelCentral, java.awt.BorderLayout.CENTER);

        m_jPanContainer.add(m_jPanTicket, java.awt.BorderLayout.CENTER);

        m_jContEntries.setLayout(new java.awt.BorderLayout());

        m_jNumberKeys.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                m_jNumberKeysKeyPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
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

        m_jKeyFactory.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        m_jKeyFactory.setForeground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        m_jKeyFactory.setBorder(null);
        m_jKeyFactory.setCaretColor(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        m_jKeyFactory.setPreferredSize(new java.awt.Dimension(1, 1));
        m_jKeyFactory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_jKeyFactoryKeyTyped(evt);
            }
        });

        org.jdesktop.layout.GroupLayout m_jPanEntriesLayout = new org.jdesktop.layout.GroupLayout(m_jPanEntries);
        m_jPanEntries.setLayout(m_jPanEntriesLayout);
        m_jPanEntriesLayout.setHorizontalGroup(
            m_jPanEntriesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanEntriesLayout.createSequentialGroup()
                .add(m_jPanEntriesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(m_jKeyFactory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(m_jNumberKeys, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        m_jPanEntriesLayout.setVerticalGroup(
            m_jPanEntriesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_jPanEntriesLayout.createSequentialGroup()
                .add(m_jNumberKeys, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 204, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(53, 53, 53)
                .add(m_jKeyFactory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        m_jContEntries.add(m_jPanEntries, java.awt.BorderLayout.NORTH);

        m_jPanContainer.add(m_jContEntries, java.awt.BorderLayout.LINE_END);

        catcontainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        catcontainer.setLayout(new java.awt.BorderLayout());
        m_jPanContainer.add(catcontainer, java.awt.BorderLayout.SOUTH);

        add(m_jPanContainer, "ticket");
    }// </editor-fold>//GEN-END:initComponents

    private void m_jbtnScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnScaleActionPerformed

        stateTransition('\u00a7');

    }//GEN-LAST:event_m_jbtnScaleActionPerformed

    private void m_jEditLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEditLineActionPerformed

        int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            TicketLineInfo oLine = m_oTicket.getLine(i);
            if (JProductLineRemarkEdit.showMessage(this, m_App, m_oTicket.getLine(i))) {
                // se ha modificado la linea
                paintTicketLine(i, oLine);
            }
        }

    }//GEN-LAST:event_m_jEditLineActionPerformed

    private void m_jEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEnterActionPerformed

        stateTransition('\n');

    }//GEN-LAST:event_m_jEnterActionPerformed

    private void m_jNumberKeysKeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_m_jNumberKeysKeyPerformed

        stateTransition(evt.getKey());

    }//GEN-LAST:event_m_jNumberKeysKeyPerformed

    private void m_jKeyFactoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jKeyFactoryKeyTyped

        m_jKeyFactory.setText(null);
        stateTransition(evt.getKeyChar());

    }//GEN-LAST:event_m_jKeyFactoryKeyTyped

    private void m_jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDeleteActionPerformed

        int i = m_ticketlines.getSelectedIndex();
        if (i < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            removeTicketLine(i); // elimino la linea           
        }

    }//GEN-LAST:event_m_jDeleteActionPerformed

    private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed

        m_ticketlines.selectionUp();

    }//GEN-LAST:event_m_jUpActionPerformed

    private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed

        m_ticketlines.selectionDown();

    }//GEN-LAST:event_m_jDownActionPerformed

    private void m_jListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jListActionPerformed

        ProductInfoExt prod = JProductFinder.showMessage(JPanelTicketstd1.this, dlSales);
        if (prod != null) {
            buttonTransition(prod);
        }

    }//GEN-LAST:event_m_jListActionPerformed

    public boolean setTextnull(){
         jButton1.setText("Bill");   
         jTextField2.setText(null);
         jTextField3.setText(null);
         jTextField2.getText();
          return setTextnull();
    }
    
    
    public void getText(boolean b){
        if(b==true){
           setTextnull(); 
        }else{
            
        }
    }
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
        String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
       if (ticketBag.equals("standard")) {
              jButton1.setText("Bill");     
            //    printTicket("Printer.Ticket", binfo, binfo.getPlace());
               m_jTicketId.setVisible(true);
     
        String cust = jTextField2.getText();
        Object[] obj2;
        Object[] obj3;
            try {
                obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
               
                obj3 = (Object[])new StaticSentence(m_App.getSession(),"SELECT NAME FROM customers WHERE NAME=?",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj2[1].toString() );
                String custname=obj3.toString();
                      m_jTicketId.setText(obj3[0].toString());
                      customer=dlSales.loadCustomerExt(obj2[0].toString());
                         
                if (obj2== null && obj3== null ) {
                     JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                }else{
                    
               
              
              try{
                boolean berror = false;
                Date date = new Date();
                AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                
                
              Object[]  obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT OPENSALE FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(user.getId());
             // String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("printer.standardbill");
               // ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                //script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
                //m_TTP.printTicket(script.eval(sresource).toString());
                      
                
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
                            try {
                                new PreparedSentence(m_App.getSession(), "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                            } catch (BasicException ex) {
                                Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                        }
                    }
                }
         
             // createBill(m_oTicket);
              if (berror == false && m_oTicket.getLinesCount() > 0) {
                    // temp=1;
                    
                    TicketInfo ticket1 = m_oTicket.copyTicket();
                    TicketInfo ticket2 = new TicketInfo();
                    
                  //   WaiterInfo waiter=new WaiterInfo();
                    // waiter.getName().equals("self");
                     
                          // waiter.getCounter();
                       //       dlSales.getActiveWaiterList(m_App.getAppUserView().getUser().getRole());
            //warehouse changes -start
         //       Object obj4 = new StaticSentence(m_App.getSession(), "SELECT P.PRCATEGORIES FROM PEOPLE P WHERE P.ROLE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(waiter.getCounter());
             //   Object obj4 = new StaticSentence(m_App.getSession(), "SELECT NAME FROM WAITER WHERE COUNTER ='074b9f6c-558d-4c96-979c-aa622ce180a9'", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(waiter.getCounter());
               //     waiter.getName();
                ticket2.setUser(ticket1.getUser());
                      ticket2.setCustomer(m_oTicket.getCustomer());
                      ticket2.setPlace(ticket1.getPlace());
                      ticket2.setWaiter(ticket1.getWaiter());
                      ticket2.setFloor(ticket1.getFloor());
                     
                     // JTicketsBag jticbag=j_tktbgshrd.loadMemberDetails(prcategory);
                   // ticket2.printTotal();
                   // ticket2.setCustomer(m_oTicket.getCustomer());
                  
                   

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
                                    
                                    JOptionPane.showMessageDialog(this, "\"" + tl.getProductName() + " \" is Empty.Cannot prepare BILL for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Bill quantity Exceed the quantity in stock for \"" + tl.getProductName() + " \"", "Cannot Prepare Bill", JOptionPane.WARNING_MESSAGE);
                                  
                               
                                }
                                 ticket1.deleteLine(j);
                               
                                size--;
                               }
                           } else {
                              j++;
                            
                          }
                       }
             
                        
                               
                                
                                 boolean bill = false;
                                 Double amt;
                                amt=ticket1.getTotal(); 
                                if(amt>0.0)
                                {
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    String type=null;
//                                     boolean flag = saveBillstd("Debt");
//                                        if (flag == true) 
//                                        {
//                                             
//                                                       }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                               
                               
                                JPaymentSelectstd1 paymentdialog =JPaymentSelectReceiptstd1.getDialog(this);
                                  paymentdialog.init(m_App);
                                  List<PaymentInfo> pinfo=new ArrayList<PaymentInfo>();
                                bill= paymentdialog.showDialog(amt, customer, prcategory, true,type,pinfo12);
                                      pinfo=paymentdialog.getSelectedPayments();
                                   //    Boolean b=new Boolean(pinfo.listIterator(0));
                                          pinfo.listIterator(0);
                                        
                                       
                                           
                                 
                                       pinfo=(List<PaymentInfo>) paymentdialog.getSelectedPayments();
                                   
                                      if(bill==true){         
                                           
                                                                                       
                                          
                                             ticket1.setPayments(pinfo);
                                           
                                             ticket2.setCustomer(customer);
                                             
                                                 printstdBill(ticket1,ticket2,pinfo,binfo,null);
                                                  setActiveTicket(ticket2, m_oTicketExt);
                                           
                                                }
                                              
                                  }
                                else{
                                   
                                  //  JOptionPane.showMessageDialog(this,"Stock Empty");
                                      int i = m_ticketlines.getSelectedIndex();
                                     if (i < 0) {
                                          Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
                                             } else {
                                           removeTicketLine(i); // elimino la linea           
                                                   }
                                    
                                    }
                   
                                //setActiveTicket(ticket2, m_oTicketExt);
                               if(bill==true){
             try{
                  cust = jTextField2.getText();
                 Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj1== null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                          customer=dlSales.loadCustomerExt(obj1[0].toString());
                 dlReceipts.deleteSharedTicketstd(customer.getId(), m_App.getAppUserView().getUser().getRole());
                  jTextField2.setText(null);
                  jTextField3.setText(null);
                  
                                 
                }
             }catch(BasicException e){
                 for (int i = 0; i < m_oTicket.getLinesCount(); i++) {
                     //   m_ticketlines.removeTicketLine(0);
                        
                    }
             }
                    //exit for every transaction for kiosk mode---start
                    if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
                        JPrincipalApp.m_approot.closeAppView();
                    }  
             }
                  
                 
                  for (int i = 0; i < m_oTicket.getLinesCount(); i++) {
                      //  m_ticketlines.removeTicketLine(0);
                        
                    }
                    //exit for every transaction for kiosk mode---start
                    if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
                        JPrincipalApp.m_approot.closeAppView();
                    }
                    //exit for every transaction for kiosk mode---end
                } else {
                    if (berror == true) {
                        JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot login", JOptionPane.OK_OPTION);
                    }
                }

                 }  catch (Exception e) {
                     
              
                     
           MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotcreatebill"), e);
           
            }
        
         }
            } catch (BasicException ex) {
                Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }

      
    }//GEN-LAST:event_jButton1ActionPerformed

 
    private void saveCurrentTicket() {
        
//        // save current ticket, if exists,
        if (m_sCurrentTicket != null) {
           try {
               
               dlReceipts.insertSharedTicket(m_sCurrentTicket, m_panelticket.getActiveTicket(),m_App.getAppUserView().getUser().getRole(),null,DataConstants.SALES);
            } catch (BasicException e) {
                new MessageInf(e).show(this);
           }  
        }    
    }
    private void setActiveTicket(String id) throws BasicException{
          
        // BEGIN TRANSACTION
        TicketInfo ticket = dlReceipts.getSharedTicket(id,m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
        if (ticket == null)  {
            // Does not exists ???
            throw new BasicException(AppLocal.getIntString("message.noticket"));
        } else {
           dlReceipts.deleteSharedTicketstd(id,m_App.getAppUserView().getUser().getRole());
            m_sCurrentTicket = id;
            m_panelticket.setActiveTicket(ticket, null);
        } 
        // END TRANSACTION                 
    }

    
    
    private boolean selectValidTicket() {
        boolean resultok = false;
        try {
            List<SharedTicketInfo> l = dlReceipts.getSharedTicketList(m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
            if (l.size() == 0) {
                newTicket();
            } else {
                setActiveTicket(l.get(0).getId());
            }
            resultok = true;
        } catch (BasicException e) {
            new MessageInf(e).show(this);
            newTicket();
        }
        return resultok;
    }  
    
     public boolean deleteTicket() {
        m_sCurrentTicket = null;
        return selectValidTicket();
    }
    
    private void newTicket() {      
        
        saveCurrentTicket();

        TicketInfo ticket = new TicketInfo();    
        m_sCurrentTicket = UUID.randomUUID().toString(); // m_fmtid.format(ticket.getId());
        m_panelticket.setActiveTicket(ticket, null);      
     }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      private void saveCurrentTicketstd() {
        
//        // save current ticket, if exists,
        if (m_sCurrentTicket != null) {
           try {
              dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
               dlReceipts.insertSharedTicket(m_sCurrentTicket, m_panelticket.getActiveTicket(),m_App.getAppUserView().getUser().getRole(),null,DataConstants.SALES);
            } catch (BasicException e) {
                new MessageInf(e).show(this);
           }  
        }    
    }
    private void setActiveTicketstd(String id) throws BasicException{
          
        // BEGIN TRANSACTION
        TicketInfo ticket = dlReceipts.getSharedTicket(id,m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
        if (ticket == null)  {
            // Does not exists ???
            throw new BasicException(AppLocal.getIntString("message.noticket"));
        } else {
            dlReceipts.deleteSharedTicket(id,m_App.getAppUserView().getUser().getRole());
            m_sCurrentTicket = id;
            m_panelticket.setActiveTicket(ticket, null);
        } 
        // END TRANSACTION                 
    }

    
    
    private boolean selectValidTicketstd() {
        boolean resultok = false;
        try {
            List<SharedTicketInfo> l = dlReceipts.getSharedTicketList(m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
            if (l.size() == 0) {
                newTicket();
            } else {
                setActiveTicket(l.get(0).getId());
            }
            resultok = true;
        } catch (BasicException e) {
            new MessageInf(e).show(this);
            newTicket();
        }
        return resultok;
    }  
    
     public boolean deleteTicketstd() {
        m_sCurrentTicket = null;
        return selectValidTicket();
    }
    
    private void newTicketstd() {      
        
        saveCurrentTicket();

        TicketInfo ticket = new TicketInfo();    
        m_sCurrentTicket = UUID.randomUUID().toString(); // m_fmtid.format(ticket.getId());
        m_panelticket.setActiveTicket(ticket, null);      
     }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

private void m_jListTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jListTicketsActionPerformed

 String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
 if (ticketBag.equals("restaurant")) {
          
        SwingUtilities.invokeLater(new Runnable() {
                @Override
            public void run() {
                
                try {
           List<SharedTicketInfo> l = dlReceipts.getSharedTicketListstd(m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
                  
                   JTicketsBagSharedListstd1 listDialog = JTicketsBagSharedListstd1.newJDialog(JPanelTicketstd1.this);
                    String id = listDialog.showTicketsListstd(l);
                    id=listDialog.showTicketsListstd(l);

                    if (id != null) {
                        saveCurrentTicket();
                        setActiveTicket(id); 
                    }
                } catch (BasicException e) {
                    new MessageInf(e).show(JPanelTicketstd1.this);
                    newTicket();
                }                    
            }
        });
        } else if (ticketBag.equals("standard")) {
         
                  
        SwingUtilities.invokeLater(new Runnable() {
                @Override
            public void run() {
                
                try {
                   List<SharedTicketInfo> l = dlReceipts.getSharedTicketListstd(m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);

                   JTicketsBagSharedListstd1 listDialog = JTicketsBagSharedListstd1.newJDialog(JPanelTicketstd1.this);
                    String id = listDialog.showTicketsListstd(l);
                    
                        

                    if (id != null) {
                     
                         // dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                         dlReceipts.getSharedTicketstd(id,m_App.getAppUserView().getUser().getRole(),DataConstants.SALES);
                        jTextField2.setText(id);
                      
                          
                           
                      //      try {
          //     Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(id);
          //    jTextField2.setText(id);
           //  jTextField2.getAction();
                        
                        
         //    String custoid = obj[0].toString();
           //  String custname =obj[1].toString();
            
             //       customer = dlSales.loadCustomerExt(custoid);
               //      custname =obj[1].toString();
                 //    customer = dlSales.loadCustomerExt(custname);
              // StringBuffer name = new StringBuffer();
                //    if (custoid != null) {
                  //   name.append(obj[1].toString());
            //        name.append(" - ");
                //   //  m_jTicketId1.setText(obj[1].toString());
              //        m_jTicketId.setText(obj[1].toString());
                  //   }
      
             
                  
         //   } 
            //                catch (Exception e) {
            //    e.printStackTrace();
           // } 
                            String custname;
          String custoid;
     
            String cust = jTextField2.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj == null) {
             //       JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
             
   
                                    
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                     custname =obj[1].toString();
                     customer = dlSales.loadCustomerExt(custname);
                //    jTextField1.setText(obj[1].toString());
                       try{
                
                 Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj1== null) {
         //           JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                           customer=dlSales.loadCustomerExt(obj1[0].toString());
                  //        dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                           setInitiator("");
                    //       dlReceipts.updateSharedTicketstd(customer.getId(),customer,m_App.getAppUserView().getUser().getRole());
                           
                           
                  }
             }catch(BasicException e){
                
                    }
                    
                           
                     StringBuffer name = new StringBuffer();
                    if (custoid != null) {
                     name.append(obj[1].toString());
                    name.append(" - ");
                   //  m_jTicketId1.setText(obj[1].toString());
                      m_jTicketId.setText(obj[1].toString());
                     }
                    
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
                            
                   //  LookupUtilityImpl.getInstance(null).getDataLogicReceipts().deleteSharedTicketstd(id, m_App.getAppUserView().getUser().getRole());
                                
                                
            //       dlReceipts.updateSharedTicketstd(customer.getId(),customer,m_App.getAppUserView().getUser().getRole());
                      //     m_sCurrentTicket = id;
                       
                        //  setActiveTicket(l.get(0).getId());
                      
                         
                          
            
                    }
                } catch (BasicException e) {
                  //  new MessageInf(e).show(JPanelTicket.this);
                    
                }      
                
            }
        });
        
       // JOptionPane.showMessageDialog(this,"Hit Enter");
                       }
        
       
       
        
}//GEN-LAST:event_m_jListTicketsActionPerformed

private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
// TODO add your handling code here:  String custoid;
  
}//GEN-LAST:event_jTextField2ActionPerformed

private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
  
    String custoid;
    String custname;

        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            String cust = jTextField2.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
             
   
                                    
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                     custname =obj[1].toString();
                     customer = dlSales.loadCustomerExt(custname);
                     
                //    jTextField1.setText(obj[1].toString());
                       try{
                
                 Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj1== null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                         customer=dlSales.loadCustomerExt(obj1[0].toString());
                        dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                           setInitiator("");
                        //   dlReceipts.updateSharedTicketstd(customer.getId(),customer,m_App.getAppUserView().getUser().getRole());
                           
                           
                  }
             }catch(BasicException e){
                
                    }
                    
                           
                     StringBuffer name = new StringBuffer();
                    if (custoid != null) {
                     name.append(obj[1].toString());
                    name.append(" - ");
                   //  m_jTicketId1.setText(obj[1].toString());
                      m_jTicketId.setText(obj[1].toString());
                     }
                    
                   
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}//GEN-LAST:event_jTextField2KeyPressed


        public void loadmemberdetailstd(String id)
        {
            id=m_sCurrentTicket;
         //    jTextField1.setText(id);
            String custoid;
           String custname;
           
          String cust = jTextField2.getText();
            try {
               Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
             
               custname =obj[1].toString();
              customer = dlSales.loadCustomerExt(custname);
      
            
                  
            } catch (Exception e) {
                e.printStackTrace();
            }
        
           
    
         }

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
       // finder.search(m_oTicket.getCustomer());
        finder.setVisible(true);
        CustomerInfo customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                customer = dlSales.loadCustomerExt(customerInfo.getId());
              
      //          jTextField1.setText(customerInfo.toString());
          
                jTextField2.setText(customerInfo.getSearchkey());
                StringBuffer name = new StringBuffer();
            //    m_jTicketId1.setText(customerInfo.toString());
                 m_jTicketId.setText(customerInfo.toString());
                 dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                    
            } catch (BasicException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
        
        
         
       
      
         
                
           

            
           
        

//        try {
//            m_oTicket.setCustomer(finder.getSelectedCustomer() == null
//                  ? null
//                    : dlSales.loadCustomerExt(finder.getSelectedCustomer().getId()));
//        } catch (BasicException e) {
//            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
//            msg.show(this);
//        }
}//GEN-LAST:event_jButton2ActionPerformed

private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
        String custoid;
        String cust = jTextField3.getText();
        try {
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,SEARCHKEY FROM CUSTOMERS WHERE CARD = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
            if (obj == null) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            } else {
                custoid = obj[0].toString();
                customer = dlSales.loadCustomerExt(custoid);
              //jTextField1.setText(obj[1].toString());
                jTextField2.setText(obj[2].toString());
                m_jTicketId.setText(obj[1].toString());
               dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } //akshatha:to read a card from card reader without port num
}//GEN-LAST:event_jTextField3ActionPerformed

private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                    jTextField2.setText(null);
                    
                     String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
        String CardRead = m_App.getProperties().getProperty("ACScard.port");
		if(cardReaderPortName.isEmpty() && CardRead.isEmpty()  ){
                    
                    jTextField3.requestFocus();
                    jTextField3.setText(null);
                     m_jTicketId.setText(null);
                       try {
                 //   dlReceipts.deleteSharedTicketstd(customer.getId(), m_App.getAppUserView().getUser().getRole());
                } catch (Exception ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
                       
                                           }else{
                    
                              
                    jTextField2.requestFocus();
                    jTextField2.setText(null);
                     m_jTicketId.setText(null);
                       try {
                   dlReceipts.deleteSharedTicketstd(customer.getId(), m_App.getAppUserView().getUser().getRole());
                } catch (BasicException ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
                              
                                                  }
                
                   
              

            }

        }); //shiv:to read a card from card reader without port num
}//GEN-LAST:event_jButton10ActionPerformed

private void m_jDelTicket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDelTicket1ActionPerformed
      
    int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.wannadelete"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
           try{
                 String cust = jTextField2.getText();
                 Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj1== null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                          customer=dlSales.loadCustomerExt(obj1[0].toString());
                         dlReceipts.deleteSharedTicketstd(customer.getId(), m_App.getAppUserView().getUser().getRole());
               java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                    jTextField2.requestFocus();
                    jTextField2.setText(null);
            //        jTextField1.setText(null);
                    jTextField3.setText(null);
                    m_jTicketId.setText(null);
                    

            }

        }); 
                }
             }catch(BasicException e){
             }
        }
}//GEN-LAST:event_m_jDelTicket1ActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 String ticketBag = m_App.getProperties().getProperty("machine.ticketsbag");
        if (ticketBag.equals("restaurant")) {
    DebtBillList dbillList = DebtBillList.getDialog(this, dlSales, m_App,false);
        dbillList.showDialog();
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
        }
        else{
             DebtBillListstd1 dbillList = DebtBillListstd1.getDialog(this, dlSales, m_App,false);
        dbillList.showDialog();
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            JPrincipalApp.m_approot.closeAppView();
        }
        }

}//GEN-LAST:event_jButton7ActionPerformed

private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
     String portNumber = m_App.getProperties().getProperty("card.portnumber");
        String portValue = m_App.getProperties().getProperty("ACScard.port");
        if (!portNumber.isEmpty() && portValue.isEmpty()) {
            String card1 = cr.getData().toString();
               if (card1 != null) {
                try {
                    loadMemberDetails(card1);
                } catch (HeadlessException ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (portNumber.isEmpty() && !portValue.isEmpty()) {
                    final Cosacs cd = new Cosacs();

                    String card1 = cd.portOpen(portValue);
                    if(card1.equals("FALSE")){
                         JOptionPane.showMessageDialog(this, "The Port is Already Open", null, JOptionPane.OK_OPTION);
                    }
                    card1 = cd.getCardNumber();
            if (card1 != null) {
                try {
                    loadMemberDetails(card1);
                } catch (HeadlessException ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
             card1 = cd.portClose();
        }else if (portNumber.isEmpty() && portValue.isEmpty()) {
                    String card1 = jTextField3.getText();
       if (card1 != null) {
                try {
                    loadMemberDetails(card1);
                } catch (HeadlessException ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }   
}//GEN-LAST:event_jButton9ActionPerformed

  public String getNextBillID(BillInfo bill,TicketInfo ticket) throws BasicException {
        
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
         
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
       // Object[] obj = (Object[]) new StaticSentence(s, "SELECT BSERIES,BMAX FROM SEQUENCEDETAIL WHERE USERNAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(uname);
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SEQUENCEDETAIL.BSERIES,SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE  AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, bill.getCreatedBy()});
        if (obj == null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            billnum = obj[0].toString() + max.intValue();
            new StaticSentence(m_App.getSession(), "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, bill.getCreatedBy()});
            return billnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
            return "";
        }
    }


public void loadMemberDetails(String card) throws HeadlessException {
        // TODO add your handling code here:
        String custoid;
        /// card = cr.getData();


        if (card.length() > 0) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?   UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = dlSales.loadCustomerExt(custoid);
                      jTextField2.setText(obj[1].toString());
                  m_jTicketId.setText(obj[2].toString());
                 dlReceipts.insertSharedTicketstd(customer.getId(),customer, m_App.getAppUserView().getUser().getRole(), getInitiator(), DataConstants.SALES);
                    setInitiator(obj[4].toString());
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
    }
      
  public void setCustomer(CustomerInfoExt value) {
        customer = value;
    }
       
   public CustomerInfoExt getCustomer() {
        return customer;
        }
   
   public void setTicketInfo(TicketInfo value){
     m_oTicket=value;
   
   }
   
   public TicketInfo ticket() {
       return m_oTicket;  
   }
   
   
   public void setjpayment(JPaymentCreator jpay)
   {
       
       jpay.createJPayment();
   }
   public JPaymentCreator createJPayment() {
        return createJPayment();
        }
   
    public PaymentInfo copyPayment(){
        return new PaymentInfoTicket(m_dTicket, m_sName);
    }
    
    
      public void cardswiped(WaiterInfo waiter) {
        try {
            String id = waiter.getID();
            String role = waiter.getCounter();
            String name = waiter.getCardNo();
            //warehouse changes -start
            Object obj = new StaticSentence(m_App.getSession(), "SELECT P.PRCATEGORIES FROM PEOPLE P WHERE P.ROLE=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(waiter.getCounter());
            String warehouse = null;
            if (obj != null) {
                warehouse = obj.toString();
            }
            //praveen or sameer write a query to get the print categories from the waiter counter
            AppUser user = new AppUser(id, name, role, warehouse);
            //warehouse changes -end
            user.setTypeOfUser(1);
            cr.setData("");
            JPrincipalApp.m_approot.openAppView(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cardswiped(String custCard) {
        try {
            loadMemberDetails(custCard);
        } catch (HeadlessException ex) {
            Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JPanelTicketstd1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel catcontainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel m_jButtons;
    private javax.swing.JPanel m_jButtonsExt;
    private javax.swing.JPanel m_jContEntries;
    private javax.swing.JButton m_jDelTicket1;
    private javax.swing.JButton m_jDelete;
    private javax.swing.JButton m_jDown;
    private javax.swing.JButton m_jEditLine;
    private javax.swing.JButton m_jEnter;
    private javax.swing.JTextField m_jKeyFactory;
    private javax.swing.JLabel m_jLblTotalEuros1;
    private javax.swing.JLabel m_jLblTotalEuros2;
    private javax.swing.JLabel m_jLblTotalEuros3;
    private javax.swing.JButton m_jList;
    private javax.swing.JButton m_jListTickets;
    private com.openbravo.beans.JNumberKeys m_jNumberKeys;
    private javax.swing.JPanel m_jOptions;
    private javax.swing.JPanel m_jPanContainer;
    private javax.swing.JPanel m_jPanEntries;
    private javax.swing.JPanel m_jPanTicket;
    private javax.swing.JPanel m_jPanTotals;
    private javax.swing.JPanel m_jPanelBag;
    private javax.swing.JPanel m_jPanelCentral;
    private javax.swing.JPanel m_jPanelScripts;
    private javax.swing.JLabel m_jPor;
    private javax.swing.JLabel m_jPrice;
    private javax.swing.JLabel m_jSubtotalEuros;
    private javax.swing.JComboBox m_jTax;
    private javax.swing.JLabel m_jTaxesEuros;
    private javax.swing.JLabel m_jTicketId;
    private javax.swing.JLabel m_jTotalEuros;
    private javax.swing.JButton m_jUp;
    private javax.swing.JToggleButton m_jaddtax;
    private javax.swing.JButton m_jbtnScale;
    // End of variables declaration//GEN-END:variables
}
