/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsumableProd.java
 *
 * Created on 20-Jan-2012, 11:59:16
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteInteger;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.catalog.CatalogSelector;
import com.openbravo.pos.catalog.JCatalog;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GuestlistTableModel;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.CardSwipeNotifier;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.sales.ConsumableProductList;
import com.openbravo.pos.sales.DataConstants;
import com.openbravo.pos.sms.CardReader1;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.inventory.InventoryLine;
import com.openbravo.pos.inventory.JPanelInventory1;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ConsumableProd extends javax.swing.JPanel implements JPanelView, BeanFactoryApp,CardSwipeNotifier {

    private DataLogicReceipts dlReceipts;
    private DataLogicCustomers dlCustomers;
    private DataLogicSales m_dlSales = null;
    protected Qticket qTicket;
    private CustomerInfo customerInfo;
     private CustomerInfoExt customer;
    private AppView m_App;
    private javax.swing.JRadioButton mem;
    private DataLogicFacilities dmang;
    private CatalogSelector m_cat;
    private Component component;
    // private ComboBoxValModel model;
    private javax.swing.JLabel editIndicator;
    private ComboBoxValModel gcatmodel;
    private List gnames = new ArrayList();
    private GuestlistTableModel gtablemodel;
    
    private DataLogicSystem dlsystem;
    
    private CardReader cr;
    //private ComboBoxValModel facilitytable1;
    private ComboBoxValModel facilitytable;
    private ComboBoxValModel depMemModel;
    private javax.swing.JComboBox m_depnamelist;
    private Object facilitytype;
    public int lindex;
    private JPanelInventory1 m_invlines;
    private ComboBoxValModel typemodel;
    private String initiator;
    private Department1 d;
    private ArrayList<ConsumableProductList> c;
    private Consumable cp;
    private TaxesLogic taxeslogic;
    protected TicketInfo m_oTicket;
    public static boolean flag = true;

    private TicketParser m_TTP;
    private ComboBoxValModel ReceivercomboModel;  
    List<Object> ReceiverList = new ArrayList<Object>(); 
     
     
     
     
    public String getInitiator() {
        return initiator;
    }

    
    /** Creates new form ConsumableProd */
    public ConsumableProd() {
        initComponents();
    }

    public String getTitle() {
        return "Issue Of Consumable";
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
//            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void activate() throws BasicException {
         startCardReader();
         loaddata();
        MemPanel.setVisible(false);
        List li = new ArrayList();
        li.add("Internal");
        //li.add("Billing");
        typemodel = new ComboBoxValModel(li);
        typecmbox.setModel(typemodel);
        typecmbox.setSelectedIndex(0);
        List<CategoryInfo> categories = new ArrayList<CategoryInfo>();
        categories = m_dlSales.getRootCategories();
        m_cat.loadCatalog(categories);
        depMemModel = new ComboBoxValModel(m_dlSales.getActiveDepartments());
        DepartmentCmb.setModel(depMemModel);
        m_invlines = new JPanelInventory1();
        m_invlines.clear();
        jPanel5.add(m_invlines, BorderLayout.CENTER);
        taxeslogic = new TaxesLogic(m_dlSales.getTaxList().list());
          mname.setText(null);
          memno.setText(null);
          jTextField3.setText(null);
            m_jTaxesEuros.setText(null);
            m_jSubtotalEuros.setText(null);
                    m_jTotalEuros.setText(null);
          java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                 //akshatha:to read a card from card reader without port num
                String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
                if (cardReaderPortName == null || cardReaderPortName.trim().length() == 0) {
                    jTextField3.setEditable(true);
                    jButton10.setVisible(true);
                    jTextField3.requestFocus();

                    
                } else {


                   memno.requestFocus();
                    jTextField3.setEditable(false);
                    jButton10.setVisible(false);
                }
            }
        });
        try {
            int typeOfUser;
            String id;
            typeOfUser = m_App.getAppUserView().getUser().getTypeOfUser();
            id = m_App.getAppUserView().getUser().getId();
            if (typeOfUser == 1) {
                if (id != null) {
                //    m_waiter = new ComboBoxValModel(dlSales.getActiveCardWaiter(id));
                   // depMemModel= new ComboBoxValModel(dlSales.getActiveCardWaiter(id));
                  //  jComboBox2.setModel(m_waiter);
                     //  DepartmentCmb.setModel(depMemModel);   
                   // jComboBox2.setSelectedIndex(0);
                   // jComboBox2.setEnabled(false);
                   // jButton9.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "swipe a valid card");
                }

            } else {
               // m_waiter = new ComboBoxValModel(dlSales.getActiveWaiterList(m_App.getAppUserView().getUser().getRole()));
            //   depMemModel = new ComboBoxValModel(dlSales.getActiveWaiterList(m_App.getAppUserView().getUser().getRole()));
               // jComboBox2.setModel(m_waiter);
             //   DepartmentCmb.setModel(depMemModel);
              //  jComboBox2.setEnabled(true);
            //   DepartmentCmb.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dlReceipts = (DataLogicReceipts) app.getBean("com.openbravo.pos.sales.DataLogicReceipts");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_cat = new JCatalog(m_dlSales);
        m_cat.getComponent().setPreferredSize(new Dimension(350, 500));
        m_cat.addActionListener(new CatalogListener());
        component = m_cat.getComponent();
        jPanel2.add(component, BorderLayout.CENTER);
        typemodel = new ComboBoxValModel();
        depMemModel = new ComboBoxValModel();
    }
 



    private void loadMember1(String mem) {
        try {
            Object[] obj = dmang.getMamberbySkey(mem);
            if (obj == null) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            } else {
                //reset();
                customerInfo = new CustomerInfo(obj[0].toString());
                customerInfo.setName(obj[1].toString());
                customerInfo.setSearchkey(mem.toUpperCase());
                //  customerInfo.setMemType(obj[2]);
                // customerInfo.setId(obj[0].toString());
                mname.setText(obj[1].toString());
                memno.setText(mem.toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
public void loadMember(String mem) throws HeadlessException {
     
  String custoid;
      String card = cr.getData().toString();
        //String card = cr.getData();
        if (card.length() > 0) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND M.CARD=?   UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
//                    customer = dlSales.loadCustomerExt(custoid);
                     memno.setText(obj[1].toString());
                    mname.setText(obj[2].toString());
                    setInitiator(obj[4].toString());
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
            Object[] obj = dmang.getMamberbySkey(mem);
            if (obj == null) {
                JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            } else {
                //reset();
                customerInfo = new CustomerInfo(obj[0].toString());
                customerInfo.setName(obj[1].toString());
                customerInfo.setSearchkey(mem.toUpperCase());
                //  customerInfo.setMemType(obj[2]);
                // customerInfo.setId(obj[0].toString());
                mname.setText(obj[1].toString());
                memno.setText(mem.toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        
        
    }
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }


    public void cardswiped(String custCard) {
       
         loadMember(custCard);
    }

    public void cardswiped(WaiterInfo waiter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }








 private class CatalogListener implements ActionListener {
     
        public void actionPerformed(ActionEvent e) {
        
            try{
                incProduct(1.0, (ProductInfoExt) e.getSource(), taxeslogic);
            }
            catch(BasicException ex){
                
            }
        }
    }

    private void incProduct(double dPor, ProductInfoExt prod, TaxesLogic tax) throws BasicException{
        if (prod != null) {
            System.out.println("" + prod.getPriceSell());
            addLine(prod, dPor, prod.getPriceSell(), tax);
        }
    }

    private void addLine(ProductInfoExt oProduct, double dpor, double dprice, TaxesLogic tax) throws  BasicException{
       
        String CurrProdID = oProduct.getID();
        
        
        String DepartmentName = DepartmentCmb.getSelectedItem().toString();
        if(oProduct.getsAccount()!=null){
        
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID FROM consprdacc C where C.PROID=? AND C.ACTIVE=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CurrProdID);
                if(obj1!=null){

                    Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID FROM consprdacc C , department d  where C.PROID=? and d.id=C.deptid AND C.ACTIVE=1 and d.name='"+DepartmentName+"'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CurrProdID);
                    if(obj2!=null){

                       Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession()," SELECT UNITS FROM stockcurrent WHERE PRODUCT=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CurrProdID);
                       if(obj3!=null){
                         Double Currunits =  Double.parseDouble(obj3[0].toString());
                         if(Currunits>0){  

                             
                            m_invlines.addLine(new InventoryLine(lindex, oProduct, dpor, dprice, tax));
                           // Akshatha : to display the Subtotal and total value
                            double sum = 0.0;
                            String sumValue;
                            double tax1;
                            String taxvalue;
                            tax1 =tax.getTaxRate(oProduct.getTaxCategoryID());
                            lindex++;
                            calculatetotal(dpor,dprice,tax1);

                        
                        
                        
                         }
                         else{
                               JOptionPane.showMessageDialog(this, "Product out of stock..!");
                         }
                       }
                       else{
                            JOptionPane.showMessageDialog(this, "Product out of stock..!");
                       }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Selected Product not configure for this department . Please configure first.");
                    }
                }
                else{
                      JOptionPane.showMessageDialog(this, "Selected product is not found in the selected  department. ");
                }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter sale account of product. ");
        }
        
    }
    
    
    
    
    
     private void calculatetotal(double dpor, double dprice, double tax1) {
                String sumValue = m_jSubtotalEuros.getText();
		String taxvalue = m_jTaxesEuros.getText();
        // Akshatha : to display the Subtotal and total value

		double subTotal = 0.0;
		double taxTotal = 0.0;
                double Total =0.0;
                if(m_jSubtotalEuros.getText()== null){
                     subTotal += dpor * dprice ;
                    //taxTotal += tax1 ;
                    String subTotal1 = Formats.DOUBLE.formatValue(subTotal);
                    String taxTotal1 = Formats.DOUBLE.formatValue(taxTotal);
                     m_jTaxesEuros.setText(taxTotal1);
                     m_jSubtotalEuros.setText(subTotal1);
                    Total= Double.valueOf(subTotal1).doubleValue()+Double.valueOf(taxTotal1).doubleValue();
                    String GTotal = Formats.CURRENCY.formatValue(Total);
                     m_jTotalEuros.setText(GTotal);
                }else{
                        if(m_jSubtotalEuros.getText().length()>0){
                            subTotal = Double.valueOf(sumValue).doubleValue();
                        }
                        subTotal += dpor * dprice ;
                        String subTotal1 = Formats.DOUBLE.formatValue(subTotal);
                        if(m_jTaxesEuros.getText().length()>0 ){
                            taxTotal = Double.valueOf(taxvalue).doubleValue();
                        }
                            //taxTotal += tax1 ;
                        String taxTotal1 = Formats.DOUBLE.formatValue(taxTotal);
                         m_jTaxesEuros.setText(taxTotal1);
                        m_jSubtotalEuros.setText(subTotal1);
                        Total= Double.valueOf(subTotal1).doubleValue();
                        String GTotal = Formats.CURRENCY.formatValue(Total);
                        m_jTotalEuros.setText(GTotal);
                }
       }
     
     
    public Object getBean() {
        return this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        typecmbox = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        Savebttn = new javax.swing.JButton();
        Removebttn = new javax.swing.JButton();
        billBtn = new javax.swing.JButton();
        Listbttn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        DepartmentPnl = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        DepartmentCmb = new javax.swing.JComboBox();
        MemPanel = new javax.swing.JPanel();
        CashRbttn = new javax.swing.JRadioButton();
        CreditRbttn = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        mname = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        memno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        m_jPanTotals = new javax.swing.JPanel();
        m_jTotalEuros = new javax.swing.JLabel();
        m_jLblTotalEuros2 = new javax.swing.JLabel();
        m_jSubtotalEuros = new javax.swing.JLabel();
        m_jTaxesEuros = new javax.swing.JLabel();
        m_jLblTotalEuros4 = new javax.swing.JLabel();
        m_jLblTotalEuros5 = new javax.swing.JLabel();
        m_jContEntries = new javax.swing.JPanel();
        m_jPanEntries = new javax.swing.JPanel();
        m_jKeyFactory = new javax.swing.JTextField();
        m_jNumberKeys = new com.openbravo.beans.JNumberKeys();
        jPanel9 = new javax.swing.JPanel();
        m_jPrice = new javax.swing.JLabel();
        m_jPor = new javax.swing.JLabel();
        m_jEnter = new javax.swing.JButton();
        m_jTax = new javax.swing.JComboBox();
        m_jaddtax = new javax.swing.JToggleButton();
        Enterbttn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        receiver_Combo = new javax.swing.JComboBox();
        receiver_textfeild = new javax.swing.JTextField();

        typecmbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Internal" }));
        typecmbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typecmboxItemStateChanged(evt);
            }
        });
        typecmbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typecmboxActionPerformed(evt);
            }
        });

        Savebttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        Savebttn.setPreferredSize(new java.awt.Dimension(47, 23));
        Savebttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavebttnActionPerformed(evt);
            }
        });
        jPanel3.add(Savebttn);
        Savebttn.setEnabled(false);

        Removebttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileclose.png"))); // NOI18N
        Removebttn.setPreferredSize(new java.awt.Dimension(47, 23));
        Removebttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovebttnActionPerformed(evt);
            }
        });
        jPanel3.add(Removebttn);

        billBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/vcard.png"))); // NOI18N
        billBtn.setPreferredSize(new java.awt.Dimension(47, 23));
        billBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billBtnActionPerformed(evt);
            }
        });
        jPanel3.add(billBtn);

        Listbttn.setText("List");
        Listbttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListbttnActionPerformed(evt);
            }
        });
        jPanel3.add(Listbttn);

        jLabel4.setText("Type");

        jLabel3.setText("Department");

        DepartmentCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DepartmentCmbItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout DepartmentPnlLayout = new javax.swing.GroupLayout(DepartmentPnl);
        DepartmentPnl.setLayout(DepartmentPnlLayout);
        DepartmentPnlLayout.setHorizontalGroup(
            DepartmentPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepartmentPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addComponent(DepartmentCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DepartmentPnlLayout.setVerticalGroup(
            DepartmentPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepartmentPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(DepartmentCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CashRbttn.setText("Cash");
        CashRbttn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CashRbttnStateChanged(evt);
            }
        });
        CashRbttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CashRbttnActionPerformed(evt);
            }
        });

        CreditRbttn.setText("Credit");
        CreditRbttn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CreditRbttnStateChanged(evt);
            }
        });
        CreditRbttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditRbttnActionPerformed(evt);
            }
        });

        jLabel1.setText("Mem No");

        mname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnameActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        memno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memnoActionPerformed(evt);
            }
        });
        memno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memnoKeyPressed(evt);
            }
        });

        jLabel2.setText("Mem Name");

        jLabel8.setText("Card No:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MemPanelLayout = new javax.swing.GroupLayout(MemPanel);
        MemPanel.setLayout(MemPanelLayout);
        MemPanelLayout.setHorizontalGroup(
            MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MemPanelLayout.createSequentialGroup()
                        .addComponent(CashRbttn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CreditRbttn)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        MemPanelLayout.setVerticalGroup(
            MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MemPanelLayout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MemPanelLayout.createSequentialGroup()
                        .addGroup(MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(MemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CashRbttn)
                            .addComponent(CreditRbttn)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        m_jPanTotals.setLayout(new java.awt.GridBagLayout());

        m_jTotalEuros.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        m_jLblTotalEuros2.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        m_jPanTotals.add(m_jLblTotalEuros2, gridBagConstraints);

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

        m_jLblTotalEuros4.setText(AppLocal.getIntString("label.taxcash")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        m_jPanTotals.add(m_jLblTotalEuros4, gridBagConstraints);

        m_jLblTotalEuros5.setText(AppLocal.getIntString("label.subtotalcash")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        m_jPanTotals.add(m_jLblTotalEuros5, gridBagConstraints);

        jPanel5.add(m_jPanTotals, java.awt.BorderLayout.PAGE_END);

        m_jContEntries.setLayout(new java.awt.BorderLayout());

        m_jPanEntries.setLayout(new javax.swing.BoxLayout(m_jPanEntries, javax.swing.BoxLayout.Y_AXIS));

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
        m_jPanEntries.add(m_jKeyFactory);

        m_jContEntries.add(m_jPanEntries, java.awt.BorderLayout.NORTH);

        m_jNumberKeys.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                m_jNumberKeysKeyPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel9.setLayout(new java.awt.GridBagLayout());

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

        Enterbttn.setText("Enter");
        Enterbttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterbttnActionPerformed(evt);
            }
        });
        jPanel9.add(Enterbttn, new java.awt.GridBagConstraints());

        jLabel5.setText("Receiver's Name : ");

        receiver_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        receiver_textfeild.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                receiver_textfeildKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(126, 126, 126)
                        .addComponent(m_jContEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(typecmbox, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(DepartmentPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(MemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(receiver_textfeild)
                                    .addComponent(receiver_Combo, 0, 194, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(137, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(m_jNumberKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(typecmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DepartmentPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(receiver_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(receiver_textfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(m_jNumberKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addComponent(m_jContEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void memnoKeyPressed(java.awt.event.KeyEvent evt) {                                 
        // TODO add your handling code here:
        String custoid;
 if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
          
  loadMember(memno.getText().toUpperCase());

String cust =  memno.getText();
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME FROM CUSTOMERS WHERE SEARCHKEY = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(cust.toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customer = m_dlSales.loadCustomerExt(custoid);
                    mname.setText(obj[1].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
}                                      

//    private void reset() {
//    }
    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
}//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void typecmboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typecmboxActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_typecmboxActionPerformed

    private void typecmboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typecmboxItemStateChanged
        try {
            // TODO add your handling code here:
            if (typecmbox.getSelectedIndex() == 0) {
                MemPanel.setVisible(false);
                DepartmentPnl.setVisible(true);
            } else if (typecmbox.getSelectedIndex() == 1) {
                MemPanel.setVisible(true);
                DepartmentPnl.setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                 //akshatha:to read a card from card reader without port num
                String cardReaderPortName = m_App.getProperties().getProperty("card.portnumber");
                if (cardReaderPortName == null || cardReaderPortName.trim().length() == 0) {
                    jTextField3.setEditable(true);
                    jButton10.setVisible(true);
                    jTextField3.requestFocus();

                    
                } else {


                    memno.requestFocus();
                    jTextField3.setEditable(false);
                    jButton10.setVisible(false);
                }
            }
        });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_typecmboxItemStateChanged
    private void EnterbttnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EnterbttnActionPerformed
    {//GEN-HEADEREND:event_EnterbttnActionPerformed
        // TODO add your handling code here:
        try {
            DecimalFormat dFormat = new DecimalFormat("#.##");
            Double i = Double.parseDouble(m_jPor.getText());
            addUnits(i);
            m_jPor.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_EnterbttnActionPerformed
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                
            mname.setText(customerInfo.toString());
                memno.setText(customerInfo.getSearchkey());


            } 
            
            catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    private void CashRbttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CashRbttnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CashRbttnActionPerformed
    private void CreditRbttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditRbttnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CreditRbttnActionPerformed
    private void CreditRbttnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CreditRbttnStateChanged
        // TODO add your handling code here:
        if (CreditRbttn.isSelected() == true) {
            CashRbttn.setSelected(false);
        } else if (CreditRbttn.isSelected() == false) {
            CashRbttn.setSelected(true);
        }
}//GEN-LAST:event_CreditRbttnStateChanged
    private void CashRbttnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CashRbttnStateChanged
        // TODO add your handling code here:
        if (CashRbttn.isSelected() == true) {
            CreditRbttn.setSelected(false);
        } else if (CashRbttn.isSelected() == false) {
            CreditRbttn.setSelected(true);
        }

    }//GEN-LAST:event_CashRbttnStateChanged
    private void SavebttnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SavebttnActionPerformed
    {//GEN-HEADEREND:event_SavebttnActionPerformed
        // TODO add your handling code here:
        try {
            if (typecmbox.getSelectedIndex() == 1) {
                if (customerInfo != null) {
                    String sharedTicketId = UUID.randomUUID().toString();
                    cp = new Consumable();
                    cp.setSharedTicketId(sharedTicketId);
                    cp.setDeptName(customerInfo.getSearchkey());
                    cp.setCreatedBy(m_App.getAppUserView().getUser().getName());
                    cp.setCreatedDate(new Date());
                    cp.setBillType(1);
                    List<ConsumableProductList> cpList = new ArrayList<ConsumableProductList>();
                    List<InventoryLine> inventoryLines = m_invlines.getLines();
                    ConsumableProductList consumableProductList = null;
                    for (InventoryLine inventoryLine : inventoryLines) {
                        consumableProductList = new ConsumableProductList(inventoryLine.getProductID(), inventoryLine.getProductName(), 1);
                        cpList.add(consumableProductList);
                    }
                    cp.setCpList(cpList);
                    insertSharedTicket1(sharedTicketId, customerInfo.getId(), cp, m_App.getAppUserView().getUser().getRole(), customerInfo.getId(), customerInfo.getSearchkey(), DataConstants.CONSUMABLEPRODUCTS);
                    m_invlines.clear();
                    clear();
                    activate();
                } else {
                    JOptionPane.showMessageDialog(this, "Enter the member details");
                }
            } else if (typecmbox.getSelectedIndex() == 0) {
                if (DepartmentCmb.getSelectedIndex() != -1) {
                    String sharedTicketId = UUID.randomUUID().toString();
                    cp = new Consumable();
                    Department1 d1 = (Department1) depMemModel.getSelectedItem();
                    cp.setSharedTicketId(sharedTicketId);
                    cp.setDeptName(d1.getName());
                    cp.setCreatedBy(m_App.getAppUserView().getUser().getName());
                    cp.setCreatedDate(new Date());
                    cp.setBillType(0);
                    List<ConsumableProductList> cpList = new ArrayList<ConsumableProductList>();
                    List<InventoryLine> inventoryLines = m_invlines.getLines();
                    ConsumableProductList consumableProductList = null;
                    for (InventoryLine inventoryLine : inventoryLines) {
                        consumableProductList = new ConsumableProductList(inventoryLine.getProductID(), inventoryLine.getProductName(), 1);
                        cpList.add(consumableProductList);
                    }
                    cp.setCpList(cpList);
                    insertSharedTicket1(sharedTicketId, d1.getId(), cp, m_App.getAppUserView().getUser().getRole(), null, null, DataConstants.CONSUMABLEPRODUCTS);
                    m_invlines.clear();
                    clear();
                    activate();
                } else {
                    JOptionPane.showMessageDialog(this, "Enter the department");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_SavebttnActionPerformed

    public final void insertSharedTicket1(final String sharedTicketId, final String id, final Consumable ticket, final String counter, final String initiator, final String searchkey, final int t) throws BasicException {
        if (searchkey == null) {
            if (new PreparedSentence(m_App.getSession(), "DELETE FROM  SHAREDTICKETS WHERE CID=? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), null).exec(new Object[]{id, counter}) >= 0) {
                Object[] values = new Object[]{
                    sharedTicketId, id, ticket, counter, initiator, t
                };
                Datas[] datas = new Datas[]{
                    Datas.STRING, Datas.STRING, Datas.SERIALIZABLE, Datas.STRING, Datas.STRING, Datas.INT
                };

                new PreparedSentence(m_App.getSession(), "INSERT INTO SHAREDTICKETS (ID,CID,CONTENT,COUNTER,INITIATOR,TYPE) VALUES (?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{
                            0, 1, 2, 3, 4, 5
                        })).exec(values);
            }
        } else if (searchkey != null) {
            if (new PreparedSentence(m_App.getSession(), "DELETE FROM  SHAREDTICKETS WHERE CID=? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), null).exec(new Object[]{id, counter}) >= 0) {
                Object[] values = new Object[]{
                    sharedTicketId, id, searchkey, ticket, counter, initiator, t
                };
                Datas[] datas = new Datas[]{
                    Datas.STRING, Datas.STRING, Datas.STRING, Datas.SERIALIZABLE, Datas.STRING, Datas.STRING, Datas.INT
                };

                new PreparedSentence(m_App.getSession(), "INSERT INTO SHAREDTICKETS (ID,CID, NAME,CONTENT,COUNTER,INITIATOR,TYPE) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{
                            0, 1, 2, 3, 4, 5, 6
                        })).exec(values);
            }
        }
    }

    public void clear() {
        memno.setText(null);
        mname.setText(null);
        
        jPanel5.removeAll();
    }

    private void RemovebttnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_RemovebttnActionPerformed
    {//GEN-HEADEREND:event_RemovebttnActionPerformed
        // TODO add your handling code here:
        deleteLine(m_invlines.getSelectedRow());
        

        
}//GEN-LAST:event_RemovebttnActionPerformed

    private void deleteLine(int index) {
           String sumValue = m_jSubtotalEuros.getText();
            String taxValue = m_jTaxesEuros.getText();
           double subTotal=0.0;
           double taxTotal=0.0;
           double Total=0.0;
        if (index < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            double value= m_invlines.productrate(index);
            double tax = m_invlines.taxRate(index);
            if(m_jSubtotalEuros.getText().length()>0){
			subTotal = Double.valueOf(sumValue).doubleValue();
		}
            subTotal = subTotal-value;
            if(m_jTaxesEuros.getText().length()>0 ){
			taxTotal = Double.valueOf(taxValue).doubleValue();
		}
		taxTotal = taxTotal-tax ;
        String taxTotal1 = Formats.DOUBLE.formatValue(taxTotal);
        m_jTaxesEuros.setText(taxTotal1);
         String subTotal1 = Formats.DOUBLE.formatValue(subTotal);
		m_jSubtotalEuros.setText(subTotal1);
         Total= Double.valueOf(subTotal1).doubleValue()+Double.valueOf(taxTotal1).doubleValue();
         String GTotal = Formats.CURRENCY.formatValue(Total);
        m_jTotalEuros.setText(GTotal);
            
            m_invlines.deleteLine(index);


        }
    }

    private void billBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billBtnActionPerformed
        // TODO add your handling code here:
        
       if(receiver_Combo.getSelectedIndex()!=-1  ){
            try {
           
                if (typecmbox.getSelectedIndex() == 0 && DepartmentCmb.getSelectedIndex() != -1) {
                    bill(0);
                } else if (typecmbox.getSelectedIndex() == 1 && customerInfo != null) {
                    bill(1);
                } else {
                    JOptionPane.showMessageDialog(this, "Select all fields");
                }

            
            
            
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
       else{
            JOptionPane.showMessageDialog(this, "Please select Receiver's name.", "incomplete Form", JOptionPane.WARNING_MESSAGE);
           
       }
        
        
        
        
        

}//GEN-LAST:event_billBtnActionPerformed
    private void ListbttnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ListbttnActionPerformed
    {//GEN-HEADEREND:event_ListbttnActionPerformed
        // TODO add your handling code here:
        //typeOfBilling=0 for internal
        //typeOfBilling=1 for billing
       try {
            ConsumableDialog cd = ConsumableDialog.getDialog(this, m_App);
            if (typecmbox.getSelectedIndex() == 1) {
                cd.showDialog(m_dlSales, dlReceipts, 1);
            } else if (typecmbox.getSelectedIndex() == 0) {
                cd.showDialog(m_dlSales, dlReceipts, 0);
            }
            if (cd.getLoaded()) {
                Consumable c = cd.getConsumable();
                for (ConsumableProductList cplist : c.getCpList()) {
                   ProductInfoExt p = m_dlSales.getProductInfo(cplist.getProductId());
//                    incProduct(cplist.getQty(), p);
                }
                if (c.getBillType() == 1) {
                    loadMember(c.getDeptName());
                } else {
                    //depMemModel.setSelectedKey(c.getDeptName());
                    DepartmentCmb.setSelectedItem(c.getDeptName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_ListbttnActionPerformed

   /* private void printPartialTotals() {

        if (lindex == 0) {
           m_jSubtotalEuros.setText(null);
            m_jTaxesEuros.setText(null);
           m_jTotalEuros.setText(null);
       } else {
           m_jSubtotalEuros.setText(m_invlines.printSubTotal());
           m_jTaxesEuros.setText(m_invlines.printTax());
           m_jTotalEuros.setText(m_invlines.printTotal());
        }
    }*/

    public final List<Consumable> getSharedTicketList(String counter, int type) throws BasicException {

        return (List<Consumable>) new StaticSentence(m_App.getSession(), "SELECT CID, NAME FROM SHAREDTICKETS WHERE COUNTER=? AND TYPE=? ORDER BY NAME", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT}), new SerializerReadClass(Consumable.class)).list(new Object[]{counter, type});
    }

    public final Consumable getSharedTicket1(String Id, String counter) throws BasicException {
        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CONTENT FROM SHAREDTICKETS WHERE CID = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{
                Datas.STRING, Datas.STRING
            }), new SerializerReadBasic(new Datas[]{
                Datas.SERIALIZABLE
            })).find(new Object[]{
                Id, counter
            });
            return record == null ? null : (Consumable) record[0];
        }
    }

    public final Consumable getSharedTicket2(String DeptId, String counter) throws BasicException {
        if (DeptId == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(m_App.getSession(), "SELECT CONTENT FROM SHAREDTICKETS WHERE CID = ? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{
                Datas.STRING, Datas.STRING
            }), new SerializerReadBasic(new Datas[]{
                Datas.SERIALIZABLE
            })).find(new Object[]{
                DeptId, counter
            });
            return record == null ? null : (Consumable) record[0];
        }
    }

    private void memnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memnoActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_memnoActionPerformed

    private void m_jNumberKeysKeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_m_jNumberKeysKeyPerformed

        stateTransition(evt.getKey());
    }//GEN-LAST:event_m_jNumberKeysKeyPerformed

    private void m_jEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEnterActionPerformed

        stateTransition('\n');
    }//GEN-LAST:event_m_jEnterActionPerformed

    private void m_jKeyFactoryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jKeyFactoryKeyTyped

        m_jKeyFactory.setText(null);
        stateTransition(evt.getKeyChar());
    }//GEN-LAST:event_m_jKeyFactoryKeyTyped

                                      

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
                customer = m_dlSales.loadCustomerExt(custoid);
                mname.setText(obj[1].toString());
                memno.setText(obj[2].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } //akshatha:to read a card from card reader without port num
}//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                jTextField3.requestFocus();
                jTextField3.setText(null);

            }

        }); //akshatha:to read a card from card reader without port num
}//GEN-LAST:event_jButton10ActionPerformed

private void mnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_mnameActionPerformed

    private void DepartmentCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DepartmentCmbItemStateChanged
      
          m_invlines.clear();
     
    }//GEN-LAST:event_DepartmentCmbItemStateChanged

    private void receiver_textfeildKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_receiver_textfeildKeyPressed
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            if(receiver_textfeild.getText().trim().length()>0){
               String tempreceiver = receiver_textfeild.getText().trim();
               int flag=0;
               for(int i=0;i<ReceiverList.size();i++){
                   String x = ReceiverList.get(i).toString();
                   if(x.equals(tempreceiver)){
                       flag=1;
                       JOptionPane.showMessageDialog(this, "Receiver Name already present..! Please enter different name");
                       break;
                   }
               }  
               
               if(flag==0){
                   try{
                       
                        new PreparedSentence(m_App.getSession(), "insert into consumablereceiver (id,name,active) values(?,?,?)", 
                                             new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,  Datas.INT}))
                                             .exec(new Object[]{UUID.randomUUID().toString()  , tempreceiver , 1 });
 
                       loaddata();
                       receiver_Combo.setSelectedItem(tempreceiver);
                       receiver_textfeild.setText(null);
                   }
                   catch(BasicException ex){
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                       Logger.getLogger(ConsumableProd.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   
               }
               
            }
        }
    }//GEN-LAST:event_receiver_textfeildKeyPressed

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

    private void addUnits(double dUnits) {
        int i = m_invlines.getSelectedRow();
        if (i >= 0) {
            InventoryLine inv = m_invlines.getLine(i);
            dUnits = Math.round(dUnits);
            double d = inv.getMultiply();
            double dunits = inv.getMultiply() + dUnits - d;

            if (dunits == 0.0) {
                deleteLine(i);
            } else if (dunits < 0) {
                JOptionPane.showMessageDialog(null, "Total Quantity should be greater than 0");
            } else {
                inv.setMultiply(inv.getMultiply() + dUnits - d);
                m_invlines.setLine(i, inv);
            }
                
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

    private void bill(final int selectedIndex) throws BasicException {
        Transaction t = new Transaction(m_App.getSession()) {

            @Override
            protected Object transact() throws BasicException {
                Map<String, Double> taxMap = new HashMap<String, Double>();
                Map<String, Double> amountMap = new HashMap<String, Double>();
                List<String> li = new ArrayList<String>();
                double billAmt = 0;
                double taxValue = 0;
                double price = 0;
                double amount = 0;
                double taxamount = 0;
                String paymentType = null;
                String Receiver = receiver_Combo.getSelectedItem().toString();
                
                List<ConsumableTax> conTax = new ArrayList<ConsumableTax>();
                List<InventoryLine> inventoryLines = m_invlines.getLines();
                Object objId = new StaticSentence(m_App.getSession(), "SELECT VALUE FROM CPBILLID WHERE BILLNAME='CPBILLNO'", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find();
                String cpBillID = null;
                int i = 0;
                if (objId != null) {
                    i = Integer.valueOf(objId.toString());
                    cpBillID = "CP" + i;
                }
                int j = 0;
                int size = inventoryLines.size();
                while (j < size) {
                    InventoryLine inventoryLine = inventoryLines.get(j);
                    ConsumableTax c = new ConsumableTax();
                    double qty;
                    String s = inventoryLine.getProductID();
                    ProductInfoExt pin = m_dlSales.getProductInfo(s);
                    TaxInfo tax = taxeslogic.getTaxInfo(pin.getTaxCategoryInfo());
                    if (li == null) {
                        li.add(tax.getId());
                        taxMap.put(tax.getId(), 0.0);
                        amountMap.put(tax.getId(), 0.0);
                    } else if (!li.contains(tax.getId())) {
                        li.add(tax.getId());
                        taxMap.put(tax.getId(), 0.0);
                        amountMap.put(tax.getId(), 0.0);
                    }

                    Object[] obj1 = null;
                    if (pin.isIsStockMaintainRequired()) {
                        Object o = m_dlSales.getStockVolume(pin.getID());
                        Double sqty = 0.0;
                        if (o != null) {
                            sqty = Double.parseDouble(o.toString());
                        }
                        if (sqty >= inventoryLine.getMultiply()) {
                            m_dlSales.updateStockVolume1(-inventoryLine.getMultiply(), pin.getID());
                            price = pin.getPriceSell();
                            amount = inventoryLine.getMultiply() * price;
                            billAmt = billAmt + amount;
                            taxamount = amount * tax.getRate();
                            taxValue = taxamount + taxValue;
                            if (taxamount > 0) {
                                Double d = taxMap.get(tax.getId());
                                Double d1 = amountMap.get(tax.getId());
                                d = d + taxamount;
                                d1 = d1 + amount;
                                taxMap.remove(tax.getId());
                                amountMap.remove(tax.getId());
                                taxMap.put(tax.getId(), d);
                                amountMap.put(tax.getId(), d1);
                            }
                            j++;
                        } else {
                            if (sqty == 0.0) {
                                JOptionPane.showMessageDialog(null, "\"" + pin.getName() + " \" is Empty.Cannot prepare QT for it", "Stock Empty", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "QT quantity Exceed the quantity in stock for \"" + pin.getName() + " \"", "Cannot Prepare QT", JOptionPane.WARNING_MESSAGE);
                            }
                            inventoryLines.remove(j);
                            size--;
                            j++;
                        }
                    }
                }
                if (selectedIndex == 1 && billAmt > 0) {
                    if (CashRbttn.isSelected()) {
                        paymentType = "cash";
                    } else if (CreditRbttn.isSelected()) {
                        paymentType = "debt";
                    }

                    Object[] values = new Object[]{
                        //cpBillID should be used in 1 argument, random number is used for test purpose
                        cpBillID, customerInfo.getId(), billAmt, taxValue, paymentType, m_App.getAppUserView().getUser().getId(), m_App.getAppUserView().getUser().getRole(), new Date(), selectedIndex
                    };
                    Datas[] datas = new Datas[]{
                        Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.INT
                    };
                    new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILL (ID,MEMID,AMOUNT,TAXAMOUNT,PAYMENTTYPE,CREATEDBY,USERROLE,CREATEDDATE,BILLTYPE) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})).exec(values);
                    for (InventoryLine inventoryLine : inventoryLines) {
                        ProductInfoExt pin = m_dlSales.getProductInfo(inventoryLine.getProductID());
                        TaxInfo tax = taxeslogic.getTaxInfo(pin.getTaxCategoryInfo());
                        Object[] values1 = new Object[]{
                            //cpBillID should be used for 2nd argument
                            UUID.randomUUID().toString(), cpBillID, inventoryLine.getProductID(), inventoryLine.getMultiply(), pin.getPriceSell(), tax.getRate()
                        };
                        Datas[] datas1 = new Datas[]{
                            Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE
                        };
                        new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILLITEM (ID,BILLID,PRODUCTID,QTY,RATE,TAXVALUE) VALUES (?,?,?,?,?,?)", new SerializerWriteBasicExt(datas1, new int[]{0, 1, 2, 3, 4, 5})).exec(values1);
                    }
                    i = i + 1;
                    new StaticSentence(m_App.getSession(), "UPDATE CPBILLID SET VALUE=? WHERE BILLNAME='CPBILLNO'", SerializerWriteInteger.INSTANCE, null).exec(i);
                    new PreparedSentence(m_App.getSession(), "DELETE FROM  SHAREDTICKETS WHERE CID=? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), null).exec(new Object[]{customerInfo.getId(), m_App.getAppUserView().getUser().getRole()});
                } 
   ////////////////////////////////////////////////////////////////////////////////////////////////////////             
       //  FOR INTERNAL BILLING .. ....... .. 
                
                else if (selectedIndex == 0 && billAmt > 0) {
                    Department1 d2 = (Department1) depMemModel.getSelectedItem();
                    Object[] values = new Object[]{
                        cpBillID, d2.getId(), billAmt, taxValue, paymentType, m_App.getAppUserView().getUser().getId(), m_App.getAppUserView().getUser().getRole(), new Date(), selectedIndex , Receiver
                    };
                    Datas[] datas = new Datas[]{
                        Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING
                    };
                    new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILL (ID,DEPTID,AMOUNT,TAXAMOUNT,PAYMENTTYPE,CREATEDBY,USERROLE,CREATEDDATE,BILLTYPE,RECEIVER) VALUES (?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8,9})).exec(values);

                    for (InventoryLine inventoryLine : inventoryLines) {
                        ProductInfoExt pin = m_dlSales.getProductInfo(inventoryLine.getProductID());
                        TaxInfo tax = taxeslogic.getTaxInfo(pin.getTaxCategoryInfo());
                        Object[] values1 = new Object[]{
                                UUID.randomUUID().toString(), cpBillID, inventoryLine.getProductID(), inventoryLine.getMultiply(), pin.getPriceSell(), tax.getRate() , d2.getId()
                        };
                        Datas[] datas1 = new Datas[]{
                            Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE , Datas.STRING
                        };
                        new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILLITEM (ID,BILLID,PRODUCTID,QTY,RATE,TAXVALUE,DEPTID) VALUES (?,?,?,?,?,?,?)", new SerializerWriteBasicExt(datas1, new int[]{0, 1, 2, 3, 4, 5,6})).exec(values1);
                   
                    // UPDATE STOCK CURRENT ..........
                        
                  //  new PreparedSentence(m_App.getSession(), "UPDATE STOCKCURRENT SET UNITS = (UNITS - ?) WHERE  PRODUCT = ?", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING}), null).exec(new Object[]{inventoryLine.getMultiply() , inventoryLine.getProductID() });
                    
                    }
                    
                    
                    i = i + 1;
                    new StaticSentence(m_App.getSession(), "UPDATE CPBILLID SET VALUE=? WHERE BILLNAME='CPBILLNO'", SerializerWriteInteger.INSTANCE, null).exec(i);
                    new PreparedSentence(m_App.getSession(), "DELETE FROM  SHAREDTICKETS WHERE CID=? AND COUNTER=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), null).exec(new Object[]{d2.getId(), m_App.getAppUserView().getUser().getRole()});

                }

                
 ///////////////////////////////////////////////////////////////////////////////////////////////////          
                
                String RoleName = "";
                
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM ROLES WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(m_App.getAppUserView().getUser().getRole());
                if(obj1!=null){
                   RoleName =  obj1[0].toString();
                }
                
                printSales(cpBillID ,  inventoryLines , "Material Issue Voucher" , billAmt , Receiver , "Issuer's Copy" , RoleName);
                printSales(cpBillID ,  inventoryLines , "Material Issue Voucher" , billAmt , Receiver ,  "Receiver's Copy" , RoleName);
                
                for (String s : li) {
                    if (taxMap.get(s) > 0) {
                      //  Object[] val = new Object[]{UUID.randomUUID().toString(), UUID.randomUUID().toString(), s, amountMap.get(s), taxMap.get(s)};
                      //  Datas[] datas1 = new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE};
                      //  new PreparedSentence(m_App.getSession(), "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", new SerializerWriteBasicExt(datas1, new int[]{0, 1, 2, 3, 4})).exec(val);
                    }
                }
                return null;
            }
        };
        t.execute();
        m_invlines.clear();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CashRbttn;
    private javax.swing.JRadioButton CreditRbttn;
    private javax.swing.JComboBox DepartmentCmb;
    private javax.swing.JPanel DepartmentPnl;
    private javax.swing.JButton Enterbttn;
    private javax.swing.JButton Listbttn;
    private javax.swing.JPanel MemPanel;
    private javax.swing.JButton Removebttn;
    private javax.swing.JButton Savebttn;
    private javax.swing.JButton billBtn;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel m_jContEntries;
    private javax.swing.JButton m_jEnter;
    private javax.swing.JTextField m_jKeyFactory;
    private javax.swing.JLabel m_jLblTotalEuros2;
    private javax.swing.JLabel m_jLblTotalEuros4;
    private javax.swing.JLabel m_jLblTotalEuros5;
    private com.openbravo.beans.JNumberKeys m_jNumberKeys;
    private javax.swing.JPanel m_jPanEntries;
    private javax.swing.JPanel m_jPanTotals;
    private javax.swing.JLabel m_jPor;
    private javax.swing.JLabel m_jPrice;
    private javax.swing.JLabel m_jSubtotalEuros;
    private javax.swing.JComboBox m_jTax;
    private javax.swing.JLabel m_jTaxesEuros;
    private javax.swing.JLabel m_jTotalEuros;
    private javax.swing.JToggleButton m_jaddtax;
    private javax.swing.JTextField memno;
    private javax.swing.JTextField mname;
    private javax.swing.JComboBox receiver_Combo;
    private javax.swing.JTextField receiver_textfeild;
    private javax.swing.JComboBox typecmbox;
    // End of variables declaration//GEN-END:variables

    public static class ConsumableTax {

        private String taxCatId;
        private Double subTotal;
        private Double taxAmount;

        public Double getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(Double subTotal) {
            this.subTotal = subTotal;
        }

        public Double getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(Double taxAmount) {
            this.taxAmount = taxAmount;
        }

        public String getTaxCatId() {
            return taxCatId;
        }

        public void setTaxCatId(String taxCatId) {
            this.taxCatId = taxCatId;
        }
    }
    
    
    
    
      private void printSales(String cpBillID , List<InventoryLine> inventoryLines , String Copy , Double billAmt, String Receiver , String Subtitle , String RoleName)  {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.ConsumableTicket");

        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                
                
                m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
                
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                
                List<String> prodname = new ArrayList<String>();
                
                InventoryLine inventoryLine = null;
                 
                script.put("maintitle", Copy);
                script.put("receiver", Receiver);
                script.put("Reciept_title", "Voucher No :");
                script.put("subtitle", Subtitle);
                script.put("receipt", cpBillID);
                script.put("date", new Date());
                script.put("Dname", DepartmentCmb.getSelectedItem().toString());
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("role", RoleName);
                script.put("host",  m_App.getProperties().getHost());
                script.put("totalamt", billAmt );
              //  script.put("InventoryLine", inventoryLine);
               // script.put("inventoryLine", inventoryLine);
                script.put("m_invlines", m_invlines);
              //  script.put("inventoryLine", inventoryLine);
                
                m_TTP.printTicket(script.eval(sresource).toString());
                
            
                
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            }
        }
    }

    
    public void loaddata() throws BasicException{
        
        ReceiverList = new ArrayList<Object>();
        ReceiverList = GetReceiverList(m_App);
        ReceivercomboModel = new ComboBoxValModel(ReceiverList);
        receiver_Combo.setModel(ReceivercomboModel);
        
        
        
        
    }
    
     public List GetReceiverList(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM consumablereceiver WHERE ACTIVE=1 ORDER BY NAME  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
    
}
