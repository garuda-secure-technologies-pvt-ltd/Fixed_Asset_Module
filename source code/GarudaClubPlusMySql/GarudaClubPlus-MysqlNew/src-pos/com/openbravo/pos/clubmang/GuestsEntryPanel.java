                              /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuestsEntryPanel.java
 *
 * Created on May 19, 2009, 3:51:07 PM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
//import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.customers.memberPhotoInfo;
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
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.sms.CardReader1;
import com.openbravo.pos.sms.SerialConnectionException;
import com.openbravo.pos.util.StringUtils;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.awt.Component;
import com.openbravo.pos.sales.restaurant.BillList;
import com.openbravo.pos.sales.restaurant.DebtBillList;

import com.openbravo.pos.sales.restaurant.JIntroPageRest;
import com.openbravo.pos.sms.SMSgeneralDBSettings;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
//import java.awt.event.KeyEvent;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
//import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
  
//import sun.util.calendar.Gregorian;
   
/**
 *
 * @author swathi
 */
          


     
public class GuestsEntryPanel extends javax.swing.JPanel implements JPanelView, BeanFactoryApp, CardSwipeNotifier {

  
   
    /** Creates new form GuestsEntryPanel */
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
   // private CustomerInfo customertemp;
    private AppView m_App;
      private DebtBillList.CreditConfirmList c;
    private DataLogicFacilities dmang;
    // private ComboBoxValModel model;
     //   private DataLogicFacilities dlfac;
    private ComboBoxValModel gcatmodel;
    private List gnames = new ArrayList();
    private GuestListModel gmodel;
    private GuestlistTableModel gtablemodel;
  //  private CreditConfirmList ccl;
    private DataLogicSales m_dlSales;
        private BillInfo binfo;
         // private AppView app;
    private DataLogicSystem dlsystem;
    private TicketParser ttp;
    private CardReader cr;
    private guestlisttable2 gtablemodel2;
    private ComboBoxValModel GuestCateBoxValModelNew;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private double TaxAmount1=0.00;//added by lipi
      private double TaxAmount2=0.00;//added by lipi
        private double TaxAmount3=0.00;//added by lipi
   private double TotalAmountwithTax = 0.00;
   protected SMSgeneralDBSettings smsDBSettings;
   
   
    public GuestsEntryPanel() {
        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException 
    {
        m_App = app;
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        ttp = new TicketParser(app.getDeviceTicket(), dlsystem);
        smsDBSettings = (SMSgeneralDBSettings) m_App.getBean("com.openbravo.pos.sms.SMSgeneralDBSettings");
   
    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        
        memno.setText(null);
        startCardReader();
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT INITCONTROL FROM FACILITY WHERE NAME='Guest Entry' AND ACTIVE=TRUE", null, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
//        Boolean bool = false;
//        if (obj != null) {
//            bool = Boolean.getBoolean(obj[0].toString());
//        }
        if ((Boolean) obj[0]) {
            memno.setEnabled(false);
            mname.setEnabled(false);
         jButton1.setVisible(false);
           
        } else {
            memno.setEnabled(true);
            mname.setEnabled(true);
            jButton1.setVisible(true);
            
        }
        
        loadData();
    }
    private void loadData() throws BasicException {
        detail_panel.setVisible(false);
        try {
            //jButton2.setVisible(false);
            ButtonGroup();
            mname.setText(null);
            cardno.setText(null); 
            //added by pratima
            memno.setText(null);
            MemText.setText(null);
            MemText.setEditable(false);
             MemText.setVisible(false);
            SearchKeyLabel.setText(null);
            jButton5.setVisible(false);
            memRadioButton.setSelected(false);
            memAllRadioButton.setSelected(true);
            //ended by pratima
            gcatmodel = new ComboBoxValModel(dmang.getGuestCategory());
            jComboBox2.setModel(gcatmodel);
            
            /// EDITED BY AAKASH ******************************
            GuestCateBoxValModelNew = new ComboBoxValModel(dmang.getGuestCategory());
            GuestType_Combo.setModel(GuestCateBoxValModelNew);
            GuestType_Combo.setSelectedIndex(-1);
            All_Radio.setSelected(true);
            
            taxAmount_text.setText(null);
            TotalAmount_text.setText(null);
           SearchKeyLabel.setVisible(false);//added by pratima
        } catch (Exception e) {
            e.printStackTrace();
        }
        reset();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
              //akshatha:to read a card from card reader without port num
                  String portNumber = m_App.getProperties().getProperty("card.portnumber");
                if (portNumber == null || portNumber.trim().length() == 0) {
                    cardno.requestFocus();
                     cardno.setEditable(true);
                    cardno.setVisible(true);
                    jButton10.setVisible(true);
                    jLabel9.setVisible(true);
                }else
                {
                   memno.requestFocus();
                   cardno.setEditable(false);
                    cardno.setVisible(false);
                    jButton10.setVisible(false);
                     jLabel9.setVisible(true);
                    
                }

            }
        });
    } 
//     public static class CreditConfirmList implements SerializableRead {
//
//        private String id;
//        private String billref;
//        private String customer;
//        private String ckey;
//        private String waiter;
//        private String ruser;
//        private Timestamp date;
//        private double amt;
//        private String custid;
//        private String facid;
//
//        public void readValues(DataRead dr) throws BasicException {
//            date = dr.getTimestamp(1);
//            customer = dr.getString(2);
//            ckey = dr.getString(3);
//            waiter = dr.getString(4);
//            billref = dr.getString(5);
//            id = dr.getString(6);
//            ruser = dr.getString(7);
//            amt = dr.getDouble(8);
//            custid = dr.getString(9);
//
//        // ckey=dr.getString(8);
//        }
//
//        public String getFacid() {
//            return facid;
//        }
//
//        public String getSearchkey() {
//            return ckey;
//        }
//
//        public String getCustomer() {
//            return customer;
//        }
//
//        public String getCSearchkey() {
//            return ckey;
//        }
//
//        public String getWaiter() {
//            return waiter;
//        }
//
//        public String getBillref() {
//            return billref;
//        }
//
//        public String getID() {
//            return id;
//        }
//
//        public String getRuser() {
//            return ruser;
//        }
//
//        public Timestamp getDate() {
//            return date;
//        }
//
//        public String printDate() {
//            return Formats.DATE.formatValue(date);
//        }
//
//        public double getAmount() {
//            return amt;
//        }
//
//        public String printAmount() {
//            return Formats.ConvertDoubleToString(amt);
//        }
//
//        public String getCustomerID() {
//            return custid;
//        }
//    }

    public void ButtonGroup(){
        ButtonGroup bg = new ButtonGroup();
        bg.add(All_Radio);
        bg.add(SelectGuest_Radio);
         ButtonGroup bg1 = new ButtonGroup();
         bg1.add(memAllRadioButton);
          bg1.add(memRadioButton);
    }
    
    private void reset() {
        guestno.setText(null);
       
        jList1.setModel(new GuestListModel(new ArrayList()));
        gnames = new ArrayList();
        jComboBox2.setSelectedIndex(-1);
        maxguestlimit.setText(null);
        amount.setText(null);
        availableguestlimit.setText(null);
        detail_panel.setVisible (false);   
        jTable2.setVisible(false);
        MemText.setText(null);
            SearchKeyLabel.setText(null);
     
         //  jPanel1.setVisible(false);
        
    }
   
        public boolean deactivate() {
        return true;
    }
      

    public void execute(String card) {
        card = String.valueOf(m_App.getReader().getVariance() + Double.valueOf(card));

    }

    public void cardswiped(WaiterInfo waiter) {
        //praveen: no need to implement this method
    }


    private class GuestListModel extends AbstractListModel {

        private java.util.List guest;

        public GuestListModel(java.util.List guest) {
            this.guest = guest;
        }

        public int getSize() {
            return guest.size();
        }

        public Object getElementAt(int i) {
            return guest.get(i);
        }

        public void remove(int i) {
            guest.remove(i);
        }
    }

    private Date getdate() {
        Date dnow = new Date();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(dnow.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.MILLISECOND, 00);
        dnow.setTime(cal.getTimeInMillis());
        return dnow;
    }

    private Date getNextdate(Date d) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.add(Calendar.DATE, 1);
        d.setTime(cal.getTimeInMillis());
        return d;
    }

//    private void printTicket(List names, String receiptno, String cname, List<PaymentInfo> pinfo, Double amount, GuestCategory gcat, int gno, String skey,String TaxCatName , Double TaxAmount,String TaxCatName2 ,String TaxCatName3 ,Double TaxAmount1,Double TaxAmount2,Double TaxAmount3) {    
     private void printTicket(List names, String receiptno, String cname, List<PaymentInfo> pinfo, Double amount, GuestCategory gcat, int gno, String skey,String TaxCatName , Double TaxAmount,String TaxCatName2 ,String TaxCatName3,BillInfo ticket) {
        
        if(TaxCatName!=null && TaxCatName.trim().length()>0){
            
        }
        else{
            TaxCatName="Tax Standard";
        }  
         if(TaxCatName2!=null && TaxCatName2.trim().length()>0){
            
        }
        else{
            TaxCatName2="Tax Standard";
        } 
          if(TaxCatName3!=null && TaxCatName3.trim().length()>0){
            
        }
        else{
            TaxCatName3="Tax Standard";
        }
        
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GuestReceipt");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                String gcatname = StringUtils.encodeXML(gcat.getname());
                int i = 0;
                for (Object line : names) {
                    names.set(i, StringUtils.encodeXML(line.toString()));
                    i++;
                }
                
//               String accountBalance = null;
//                String accountBalance1 = null;
 //               if(jButton2.isShowing()==true){
//               double T=0.00;
// T=             (double) jTable1.getModel().getValueAt(1,6);
//
//                System.out.println("dfdssds::::::"+T);
 
 
                                    //add by lipi
                                         Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                        "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID " +
                        "UNION ALL " +
                        "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID) " +
                        "AS TOTAL GROUP BY ACC",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{customerInfo.getId(), customerInfo.getId()});
                          Double d = 0.0;
                Double d1 = 0.0;
                Double bal1=0.0;
                 Double bal=0.0;
                if (obj4 != null) {
                    if (obj4[0] != null) {
                        d = Double.valueOf(obj4[0].toString());
                    }
                    if (obj4[1] != null) {
                        d1 = Double.valueOf(obj4[1].toString());
                    }
                }  
                
                                                      
                
                
                  bal = d - d1;
                  bal1 =bal+TotalAmountwithTax;
                String accountBalance = null;
                String accountBalance1 = null;
                if (bal > 0) {
                    bal = dmang.roundTwoDecimals(bal);
                    //bal1 = dmang.roundTwoDecimals(bal1);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                    //accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
                } else {
                    bal = bal * -1;
                     //bal1 = bal1 * -1;
                    bal = dmang.roundTwoDecimals(bal);
                    //bal1 = dlfac.roundTwoDecimals(bal1);
                    accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                    //accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
                }
                ///////Added by guru
                if (bal1 > 0) {
                    //bal = dlfac.roundTwoDecimals(bal);
                    bal1 = dmang.roundTwoDecimals(bal1);
                    //accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";
                    accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
                } else {
                    //bal = bal * -1;
                     bal1 = bal1 * -1;
                    //bal = dlfac.roundTwoDecimals(bal);
                    bal1 = dmang.roundTwoDecimals(bal1);
                    //accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
                    accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
                }
              //  }      
                
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", decimalFormat.format(amount));
                script.put("qty", String.valueOf(gno));
                script.put("rate", decimalFormat.format(gcat.getrate()));
                script.put("guestcat", gcatname);
                script.put("cname", cname);
                script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
                script.put("name", names);
                script.put("receipt", receiptno);
                script.put("TaxCatName", TaxCatName);
                script.put("TaxCatName2", TaxCatName2); 
                script.put("TaxCatName3", TaxCatName3);
                script.put("TaxAmount", decimalFormat.format(TaxAmount));
                script.put("totalwithTax", decimalFormat.format(TaxAmount+amount));
                script.put("TaxAmount1", decimalFormat.format(TaxAmount1));
                script.put("TaxAmount2", decimalFormat.format(TaxAmount2));
                script.put("TaxAmount3", decimalFormat.format(TaxAmount3));
                  
                script.put("ckey", skey);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("balance", accountBalance);
                script.put("balance1", accountBalance1);
                ttp.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
    }
     
     
     public String[] getCustomerBalDue()
     {
        String[] balanceArr = new String[2];
        Object[] obj4;
        try
        {
            obj4 = (Object[]) new StaticSentence(m_App.getSession(),
                    "SELECT SUM(DEBT),SUM(CREDIT),ACC FROM( " +
                            "SELECT SUM(A.BALANCEAMOUNT) AS DEBT,0.0 AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='D' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID " +
                            "UNION ALL " +
                            "SELECT 0.0 AS DEBT,SUM(A.BALANCEAMOUNT) AS CREDIT,ACCOUNTID AS ACC FROM ACCOUNTJOURNAL A,CUSTOMERS C WHERE A.TRANSTYPE='C' AND A.ACCOUNTID=C.ACCOUNT AND C.ID=? AND ACTIVE = TRUE GROUP BY A.ACCOUNTID) " +
                            "AS TOTAL GROUP BY ACC",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{customerInfo.getId(), customerInfo.getId()});
       
            Double d = 0.0;
            Double d1 = 0.0;
            Double bal1=0.0;
            Double bal=0.0;

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

            bal = d - d1;
            bal1 =bal+TotalAmountwithTax;
            String accountBalance = null;
            String accountBalance1 = null;
            if (bal > 0) 
            {
               bal = dmang.roundTwoDecimals(bal);
               accountBalance = Formats.CURRENCY.formatValue(bal) + " Dr.";

            }
            else 
            {
                bal = bal * -1;
                bal = dmang.roundTwoDecimals(bal);
                accountBalance = Formats.CURRENCY.formatValue(bal) + " Cr.";
            }

            if (bal1 > 0) 
            {
                bal1 = dmang.roundTwoDecimals(bal1);
                accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Dr.";
            } 
            else
            {
               bal1 = bal1 * -1;
               bal1 = dmang.roundTwoDecimals(bal1);
               accountBalance1=Formats.CURRENCY.formatValue(bal1) + " Cr.";
            }
            balanceArr[0] = accountBalance;
            balanceArr[1] = accountBalance1;
        
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(GuestsEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balanceArr;
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jButton4 = new javax.swing.JButton();
        jParamsDatesInterval1 = new com.openbravo.pos.reports.JParamsDatesInterval();
        All_Radio = new javax.swing.JRadioButton();
        SelectGuest_Radio = new javax.swing.JRadioButton();
        GuestType_Combo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        total_text = new javax.swing.JTextField();
        generate_report_button = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        taxTotal_text = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        GrandTotal_text = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        MemText = new javax.swing.JTextField();
        SearchKeyLabel = new javax.swing.JLabel();
        memRadioButton = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        memAllRadioButton = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        memno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        mname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        guestno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        gname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        Pay = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        maxguestlimit = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cardno = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        agt = new javax.swing.JLabel();
        Detail = new javax.swing.JButton();
        availableguestlimit = new javax.swing.JTextField();
        detail_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        taxAmount_text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TotalAmount_text = new javax.swing.JTextField();
        viewPhoto_button = new javax.swing.JButton();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jButton4.setText("Execute");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        All_Radio.setText("All");
        All_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                All_RadioItemStateChanged(evt);
            }
        });
        All_Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                All_RadioActionPerformed(evt);
            }
        });

        SelectGuest_Radio.setText("Select Guest Type ");
        SelectGuest_Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SelectGuest_RadioItemStateChanged(evt);
            }
        });
        SelectGuest_Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectGuest_RadioActionPerformed(evt);
            }
        });

        GuestType_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        GuestType_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuestType_ComboActionPerformed(evt);
            }
        });

        jLabel10.setText("Total Guest Charge :");

        total_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_textActionPerformed(evt);
            }
        });

        generate_report_button.setText("Generate Report ");
        generate_report_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_report_buttonActionPerformed(evt);
            }
        });

        jLabel13.setText("Tax Amount : ");

        jLabel14.setText("Grand Total : ");

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        SearchKeyLabel.setText("jLabel15");

        memRadioButton.setText("Select Member");
        memRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memRadioButtonItemStateChanged(evt);
            }
        });

        jLabel15.setText("Guest Type  :");

        jLabel16.setText("Members :");

        memAllRadioButton.setText("All");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total_text, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(taxTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jParamsDatesInterval1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(458, 458, 458)
                                .addComponent(generate_report_button, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(GrandTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(All_Radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(SelectGuest_Radio)
                                        .addGap(18, 18, 18)
                                        .addComponent(GuestType_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(memAllRadioButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(memRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(MemText, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SearchKeyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jParamsDatesInterval1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(All_Radio)
                    .addComponent(SelectGuest_Radio)
                    .addComponent(GuestType_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(memAllRadioButton)
                    .addComponent(memRadioButton)
                    .addComponent(MemText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(SearchKeyLabel))
                .addGap(16, 16, 16)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(total_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(taxTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(GrandTotal_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(generate_report_button))
        );

        total_text.setEditable(false);
        taxTotal_text.setEditable(false);
        GrandTotal_text.setEditable(false);
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N

        jTabbedPane1.addTab("List View", jPanel2);

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jLabel1.setText("Mem No");

        memno.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                memnoCaretPositionChanged(evt);
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

        mname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnameActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Guest Category ");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jLabel4.setText("Number of Guest");

        guestno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestnoActionPerformed(evt);
            }
        });
        guestno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                guestnoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guestnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                guestnoKeyReleased(evt);
            }
        });

        jLabel5.setText("Guest Name");

        gname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gnameActionPerformed(evt);
            }
        });
        gname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gnameKeyPressed(evt);
            }
        });

        jLabel6.setText("Guest List :");

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Pay.setText("Pay");
        Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayActionPerformed(evt);
            }
        });

        jLabel7.setText("Amount");

        amount.setEditable(false);
        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });

        jLabel8.setText("Max limit");

        maxguestlimit.setEditable(false);
        maxguestlimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxguestlimitActionPerformed(evt);
            }
        });

        jButton2.setText("Debit Bill");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Card No");

        cardno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardnoActionPerformed(evt);
            }
        });
        cardno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cardnoKeyPressed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        agt.setText("Available Guest Limit");

        Detail.setText("Detail");
        Detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetailActionPerformed(evt);
            }
        });
        Detail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetailKeyPressed(evt);
            }
        });

        availableguestlimit.setEditable(false);
        availableguestlimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availableguestlimitActionPerformed(evt);
            }
        });
        availableguestlimit.setEditable(false);

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Guestcat", "Amount", "ReceiptNo", "Num"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTable2ComponentHidden(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout detail_panelLayout = new javax.swing.GroupLayout(detail_panel);
        detail_panel.setLayout(detail_panelLayout);
        detail_panelLayout.setHorizontalGroup(
            detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        detail_panelLayout.setVerticalGroup(
            detail_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detail_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel11.setText("Tax Amount");

        taxAmount_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxAmount_textActionPerformed(evt);
            }
        });

        jLabel12.setText("Amount + Tax");

        TotalAmount_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmount_textActionPerformed(evt);
            }
        });

        viewPhoto_button.setText("View Photo");
        viewPhoto_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPhoto_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(cardno, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(guestno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(taxAmount_text)
                                            .addComponent(amount)
                                            .addComponent(TotalAmount_text, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(maxguestlimit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(agt)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addComponent(availableguestlimit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(viewPhoto_button, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(388, 388, 388))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(maxguestlimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(viewPhoto_button)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(agt)
                                        .addComponent(availableguestlimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(3, 3, 3)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(taxAmount_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(guestno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(gname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(TotalAmount_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Detail)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(detail_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Pay, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(cardno, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTextField1.setEditable(false);
        taxAmount_text.setEditable(false);
        TotalAmount_text.setEditable(false);

        jTabbedPane1.addTab("Creat New", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        //finder.search(m_oTicket.getCustomer());
        finder.setVisible(true);
        //  CustomerInfoExt customer;
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                reset();
                // customer = dlSales.loadCustomerExt(customerInfo.getId());
                mname.setText(customerInfo.toString());
                memno.setText(customerInfo.getSearchkey());
               // availableguestlimit.setText(null); 
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
//        else{
//            JOptionPane.showMessageDialog(this, "Select Member Number", "Wrong number Format", JOptionPane.OK_OPTION);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private int limit3=0;
     private int limit2=0;
      private int Billed_OverAllGuest= 0;
      private int AvailableOverAllGuest=0;
      public int OverAllGuestLimitFlag = 0;
      
      //private int available=0;
    private void memnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memnoKeyPressed
    if (evt.getKeyText(evt.getKeyCode()).equals("Enter")){
        
        try 
         {
           
             Object[] obj = dmang.getMamberbySkey(memno.getText().toUpperCase());
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                    mname.setText(null);
                } else {
                    reset();
                    customerInfo = new CustomerInfo(obj[0].toString());
                    customerInfo.setName(obj[1].toString());
                    customerInfo.setSearchkey(memno.getText().toUpperCase());
                    //  customerInfo.setMemType(obj[2]);
                    // customerInfo.setId(obj[0].toString());
                    mname.setText(obj[1].toString());
                
                int pregno = 0;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis((new Date()).getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                Date d = new Date();
                d.setTime(cal.getTimeInMillis());
                Date d1 = new Date();
                cal.add(Calendar.DATE, 1);
                d1.setTime(cal.getTimeInMillis());
                System.out.println(d);
                System.out.println(d1);
             //  GuestCategory gcat = (GuestCategory) jComboBox2.getSelectedItem();
             
                   try {
                   // Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SUM(NUM) FROM GUESTLOG WHERE MEMNO=?", null, new SerializerReadBasic(new Datas[]{Datas.INT})).find();
                   //Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SUM(NUM) FROM GUESTLOG WHERE MEMNO=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{customerInfo.getId(), gcat.getid(), d, d1});
                  reset();
                    System.out.println(customerInfo.getId());
                   // System.out.println(gcat.getid());
                  
                    Object obj1 =  new StaticSentence(m_App.getSession(), "SELECT SUM(NUM) FROM GUESTLOG WHERE MEMNO=?  AND DATE>? AND DATE<?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), SerializerReadString.INSTANCE).find(new Object[]{customerInfo.getId(), d, d1});
                    // for(Object[] obj:objlist){
                          if (obj1 != null) {
                              String x = obj1.toString();
                            Billed_OverAllGuest = Integer.parseInt(x);
                         
                          
                     }else{
                           Billed_OverAllGuest=0;   
                     }
                          
                         
                   Object[] obj10 = (Object[]) new StaticSentence(m_App.getSession(), "select VALUE from GENERALTABLE WHERE NAME='OVERALL GUEST LIMIT'",null, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                   String value=null;
                   
                   if (obj10 != null) {
                   
                       
                       value = obj10[0].toString();
                       if(value.equals("NO")){
                       
                          OverAllGuestLimitFlag = 0; 
                       
                        }
                       else{
                            OverAllGuestLimitFlag = 1; 
                            limit3=Integer.parseInt(obj10[0].toString());
                            AvailableOverAllGuest = limit3;
                        
                            detail_panel.setVisible(false);   
                            jTable2.setVisible(false);

                            int Curr_Limit = AvailableOverAllGuest - Billed_OverAllGuest;
                            availableguestlimit.setText(""+Curr_Limit); 
                       }
                       }
                       else{
                      JOptionPane.showMessageDialog(this, "No Value Set In General Table", null, JOptionPane.WARNING_MESSAGE); 
                       }    
                     }
                   catch (Exception e) {
                    e.printStackTrace();
                }
             
           } 
             
         }     
        
         catch (Exception e) {
                    e.printStackTrace();
                }
        }
   
    }//GEN-LAST:event_memnoKeyPressed
    
    private void PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayActionPerformed
        // TODO add your handling code here:
//          JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
//        customerInfo = finder.getSelectedCustomer();
////         finder.setVisible(true);
//        if (customerInfo == null) {
//        JOptionPane.showMessageDialog(this, "Please Select Member First", null, JOptionPane.OK_OPTION);
////        reset();
//        }
//        else  if (customerInfo != null) {

        try 
        {
            Transaction t;
            t = new Transaction(m_App.getSession()) 
            {

            @Override
            protected Object transact() throws BasicException 
            {
                String name = "";
                List<PaymentInfo> pinfo = new ArrayList<PaymentInfo>();
                List lnames = new ArrayList();
                if (jComboBox2.getSelectedIndex() != -1 && customerInfo != null && guestno.getText().length() > 0) 
                {
                    if (Integer.parseInt(guestno.getText()) == gnames.size()) 
                    {
                        Date d = new Date();
                        GuestCategory gcat = (GuestCategory) jComboBox2.getSelectedItem();

                        int gno = Integer.parseInt(guestno.getText());
                        
                        if (gno == gmodel.getSize()) 
                        {
                            CustomerInfoExt cinfo = new CustomerInfoExt(customerInfo.getId());
                            cinfo.setSearchkey(customerInfo.getSearchkey());
                            cinfo.setName(customerInfo.getName());
                            JPaymentSelect paymentdialog = JPaymentSelectReceipt.getDialog(new JFrame());
                            paymentdialog.init(m_App);

                            String Taxid = getTaxIDByTaxCatID(gcat.getTaxCategory());
                            String TaxCatName = getTaxCatNameIDByTaxCatID(gcat.getTaxCategory());

                            String Taxid2 = getTaxIDByTaxCatID1(gcat.getTaxCategory2());
                            String TaxCatName2 = getTaxCatNameIDByTaxCatID1(gcat.getTaxCategory2());

                            String Taxid3 = getTaxIDByTaxCatID2(gcat.getTaxCategory3());
                            String TaxCatName3 = getTaxCatNameIDByTaxCatID2(gcat.getTaxCategory3());


                            boolean flag = paymentdialog.showDialog(Double.parseDouble(TotalAmount_text.getText()), cinfo, m_App.getAppUserView().getUser().getName(), true);
                            if (flag == true) 
                            {
                                pinfo = paymentdialog.getSelectedPayments();
                                BillInfo ticket = new BillInfo();
                                ticket.setID(UUID.randomUUID().toString());
                                ticket.setPayments(pinfo);
                                ticket.setCustomer(cinfo);
                                ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
                                ticket.setCreatedDate(getdate());
                                ticket.setActiveCash(m_App.getActiveCashIndex());
                                ticket.setFloor("Guest Entry");
                                //Guest cat changes-start
                                ticket.setPlace(gcat.getid());
                                ticket.setTaxes(lnames);
                                 

                                //Guest cat changes-end
                                String rnum = m_dlSales.payaccount2(ticket, m_App.getInventoryLocation(), true, Taxid,Taxid2,Taxid3, Double.parseDouble(amount.getText()) , Double.parseDouble(taxAmount_text.getText()) );
                                //  }
                                if (!(rnum == null || rnum.equals("false"))) 
                                {
                                    String nametemp = null;

                                    int i = 0;
                                    for (i = 0; i < gmodel.getSize(); i++) 
                                    {
                                        String tempname = gmodel.getElementAt(i).toString();
                                        if (name.equals("")) 
                                        {
                                            name = tempname;
                                        } 
                                        else 
                                        {
                                            name += ":" + tempname;
                                        }
                                        if (nametemp == null) 
                                        {
                                            nametemp = tempname;
                                        } 
                                        else 
                                        {
                                            nametemp = nametemp + " , " + tempname;
                                            if ((i + 1) % 3 == 0) 
                                            {
                                                lnames.add(nametemp);
                                                nametemp = null;
                                            }
                                        }

                                    }
                                    if (i % 3 != 0) 
                                    {
                                        lnames.add(nametemp);
                                    }

                                    Double amt = gcat.getrate() * gno;
                                    Double TotalAmountWithTax = Double.parseDouble(TotalAmount_text.getText());
                                    // try{
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), customerInfo.getId(), d, gcat.getid(), amt, gno, name, rnum, m_App.getAppUserView().getUser().getName(),gcat.getTaxCategory(),Double.parseDouble(taxAmount_text.getText()) ,gcat.getTaxCategory2(),gcat.getTaxCategory3()};
                                    new PreparedSentence(m_App.getSession(), "INSERT INTO GUESTLOG(ID,MEMNO,DATE,GUESTCAT,AMOUNT,NUM,NAMES,RECEIPTNO,CREATEDBY,TAXCAT,TAXAMOUNT,TAXCAT1,TAXCAT2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.STRING})).exec(value);
                                    printTicket(lnames, rnum, cinfo.getName(), pinfo, amt, gcat, gno, cinfo.getSearchkey(),TaxCatName,Double.parseDouble(taxAmount_text.getText()),TaxCatName2,TaxCatName3,binfo);
                                    checkForCashSMS(ticket, amt+Double.parseDouble(taxAmount_text.getText()));
                                    
                                    loadData();
                                    memno.setText(null);
                                   
                                } 
                                else 
                                {
                                    if (rnum.equals("false"))
                                    {
                                        JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                                    } 
                                    else 
                                    {
                                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.OK_OPTION);
                                    }
                                }
                            }
                        } 
                        else
                        {
                            JOptionPane.showMessageDialog(null, "The Guest limit exceeds for the category", "Guest Limit Exceeds", JOptionPane.WARNING_MESSAGE);

                        }
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "The Guest list does not match with guest number", null, JOptionPane.WARNING_MESSAGE);
                    }
                } 
                else 
                {
                    //exceeds
                    JOptionPane.showMessageDialog(null, "Please Fill the form completely", null, JOptionPane.WARNING_MESSAGE);
                }
   
                return null;
            }
        };
                t.execute();

            } catch (Exception e) {
                 Logger.getLogger(GuestsEntryPanel.class.getName()).log(Level.SEVERE, null, e);
                 new MessageInf(e).show(new JFrame());
            }
//        }
        
}//GEN-LAST:event_PayActionPerformed

    
    public void checkForCashSMS(BillInfo ticket, Double totalAmount)
    {
        boolean sendSMSwhileGuestCharges =  smsDBSettings.getSMSvalue(SMSgeneralDBSettings.SMS_GUEST_CHRG_CASH_ID);
        if(sendSMSwhileGuestCharges)
        {
            createSMS(ticket, totalAmount, SMSgeneralDBSettings.SMS_GUEST_CHRG_CASH_ID);
        }
    }
    
    public void checkForDebitSMS(BillInfo ticket, Double totalAmount)
    {
        boolean sendSMSwhileGuestCharges =  smsDBSettings.getSMSvalue(SMSgeneralDBSettings.SMS_GUEST_CHRG_DEBIT_ID);
        if(sendSMSwhileGuestCharges)
        {
            createSMS(ticket, totalAmount, SMSgeneralDBSettings.SMS_GUEST_CHRG_DEBIT_ID);
        }
    }
    
    public void createSMS(BillInfo ticket, Double totalAmount, String smsMessage_Id)
    {
        String smsString = smsDBSettings.getMessage(smsMessage_Id);
        if(smsString != null)
        {
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_BILL_KEY, ticket.getReceiptRef());
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_DTM_KEY , Formats.TIMESTAMP.formatValue(new Date()));
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_TOT_AMOUNT_KEY , decimalFormat.format(totalAmount));
            String x = m_App.getAppUserView().getUser().getRole();
            smsString = smsString.replace(SMSgeneralDBSettings.SMS_ROLE_KEY ,  LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            
            if(ticket.getCustomer() != null)
            {
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NAME_KEY, ticket.getCustomer().getName()); 
                smsString = smsString.replace(SMSgeneralDBSettings.SMS_MEMBER_NO_KEY, ticket.getCustomer().getSearchkey()); 
            }
            if(smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE) || smsString.contains(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER))
            {
                String[] balArr = getCustomerBalDue();
                if(balArr != null && balArr.length > 0 )
                {
                    smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_BEFORE, balArr[0]);
                    smsString = smsString.replace(SMSgeneralDBSettings.SMS_CUST_BAL_AFTER, balArr[1]);
                }
            }
            
            if(customerInfo.getMobile() != null && customerInfo.getMobile().trim().length() > 0)
            {
                smsDBSettings.insertSMStoActiveMsgTable(smsString, customerInfo.getMobile(), customerInfo.getId());
            }
        }
    }
    
    
    private void gnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gnameKeyPressed
        // TODO add your handling code here:
         if (customerInfo != null) {
          if (guestno.getText().length() != 0) {
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            if (gname.getText().length() > 0) {

                if (gnames.size() < Integer.parseInt(guestno.getText())) {
                    gnames.add(gname.getText());
                    gmodel = new GuestListModel(gnames);
                    jList1.setModel(gmodel);
                    gname.setText(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Guest list exceeds the number of guest specified", "Exceeds the specified number", JOptionPane.OK_OPTION);
                    gname.setText(null);
                }
            }

        }
          }else if (guestno.getText().length() == 0) {
              
          JOptionPane.showMessageDialog(this, "Enter Number Of Guest", null, JOptionPane.OK_OPTION);
         // reset();
          }
         }else  if (customerInfo == null) {
             JOptionPane.showMessageDialog(this, "Please Select Member First", null, JOptionPane.OK_OPTION);
             reset();
         }
    }//GEN-LAST:event_gnameKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = jList1.getSelectedIndex();
        if (row >= 0) {
            gnames.remove(row);
            gmodel = new GuestListModel(gnames);

            jList1.setModel(gmodel);


        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();
        if (tabno == 1) {  
            Date sdate = getdate();
            Date d = new Date();
            d.setTime(sdate.getTime());
            Date edate = getNextdate(d);
            jParamsDatesInterval1.setStartDate(sdate);
            jParamsDatesInterval1.setEndDate(edate);
            
        }
           
        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
       if(jParamsDatesInterval1.getStartDate()!=null && jParamsDatesInterval1.getStartDate().length()>0){ 
        if(jParamsDatesInterval1.getEndDate()!=null && jParamsDatesInterval1.getEndDate().length()>0){
//          if(All_Radio.isSelected()){      
//            
//            try {
//                    Date sdate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getStartDate());
//                    Date edate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getEndDate());
//                    gtablemodel = GuestlistTableModel.loadInstance(m_App, sdate, edate);
//                    jTable1.setModel(gtablemodel.getTableModel());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                
//          }
//          else{
//             if(GuestType_Combo.getSelectedIndex()!=-1){
//                 String GuestCatName = GuestType_Combo.getSelectedItem().toString();
//                    try {
//                          Date sdate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getStartDate());
//                          Date edate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getEndDate());
//                          gtablemodel = GuestlistTableModel.loadInstanceForOneGuestCat(m_App, sdate, edate,GuestCatName);
//                          jTable1.setModel(gtablemodel.getTableModel());
//                      } catch (Exception e) {
//                          e.printStackTrace();
//                      }
//                    
//                    
//             }
//             else{
//                 JOptionPane.showMessageDialog(this, "Select Guest Category ","Warning", JOptionPane.OK_OPTION);
//             }
//              
//          }
          if(All_Radio.isSelected()){      
            
            try {
                    Date sdate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getStartDate());
                    Date edate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getEndDate());
                    if(memRadioButton.isSelected()){
                       if(MemText.getText().length()>0){
                       gtablemodel = GuestlistTableModel.loadInstanceForOneMem(m_App, sdate, edate,SearchKeyLabel.getText());
                       }else JOptionPane.showMessageDialog(this, "Select Member. ","Warning", JOptionPane.OK_OPTION);
                    }else gtablemodel = GuestlistTableModel.loadInstance(m_App, sdate, edate);
                    jTable1.setModel(gtablemodel.getTableModel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
          }
          else{
             if(GuestType_Combo.getSelectedIndex()!=-1){
                 String GuestCatName = GuestType_Combo.getSelectedItem().toString();
                    try {
                          Date sdate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getStartDate());
                          Date edate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getEndDate());
                           if(memRadioButton.isSelected()){
                              if(MemText.getText().length()>0){
                              gtablemodel = GuestlistTableModel.loadInstanceForOneGuestCatOneMem(m_App, sdate, edate,GuestCatName,SearchKeyLabel.getText());
                              }else JOptionPane.showMessageDialog(this, "Select Member. ","Warning", JOptionPane.OK_OPTION);
                          }else  gtablemodel = GuestlistTableModel.loadInstanceForOneGuestCat(m_App, sdate, edate,GuestCatName);
                          jTable1.setModel(gtablemodel.getTableModel());
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                    
                    
             }
             else{
                 JOptionPane.showMessageDialog(this, "Select Guest Category ","Warning", JOptionPane.OK_OPTION);
             }
              
          }
          
          Double Total = 0.00;
          Double TaxTotal = 0.00;
          
          
          if(gtablemodel.GetSize()>0){
              for(int i=0;i<gtablemodel.GetSize();i++){
                  Double x = gtablemodel.getGuestList().get(i).getamount();
                  Total=Total+x;
              }
          }
          
          if(gtablemodel.GetSize()>0){
              for(int i=0;i<gtablemodel.GetSize();i++){
                  Double x = gtablemodel.getGuestList().get(i).getTaxAmount();
                  TaxTotal=TaxTotal+x; 
                  
              }
          }
          
          total_text.setText(decimalFormat.format(Total));
          taxTotal_text.setText(decimalFormat.format(TaxTotal));
          GrandTotal_text.setText(decimalFormat.format(Total+TaxTotal));
          
        }
        else{
             JOptionPane.showMessageDialog(this, "Enter To Date ","Warning", JOptionPane.OK_OPTION);
        }
       }
       else{
           JOptionPane.showMessageDialog(this, "Enter From Date ","Warning", JOptionPane.OK_OPTION);
       }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guestnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guestnoKeyReleased
  
// TODO add your handling code here:
        if (customerInfo == null) {
        JOptionPane.showMessageDialog(this, "Please Select Member First", null, JOptionPane.OK_OPTION);
       
        }
else if(jComboBox2.getSelectedItem()==null){
            JOptionPane.showMessageDialog(this, "Please Select Guest Category", null, JOptionPane.OK_OPTION);
        }
        else 
try {
   
                if (guestno.getText().length() > 0) {
                    
                int num = Integer.valueOf(guestno.getText());
                //int num1= Integer.valueOf(guestno.getText());
                GuestCategory gcat = (GuestCategory) jComboBox2.getSelectedItem();
                int max_guest = Integer.parseInt(maxguestlimit.getText());
                int avail_guest_limit =Integer.parseInt( availableguestlimit.getText());
                if(availableguestlimit.getText()!=null && availableguestlimit.getText().trim().length()>0 ){
                    avail_guest_limit  = Integer.parseInt(availableguestlimit.getText());
                }
                
                
                
        
//                else{
//                    
//                  if ( num > avail_guest_limit) {
//              
//                    JOptionPane.showMessageDialog(this, "The guest number exceeds the avail guest limit", null, JOptionPane.OK_OPTION);
//                    guestno.setText(null);      
//                   
//                  } 
//                 else {
//                    amount.setText(decimalFormat.format(num * gcat.getrate()));      
//                  }  
//                    
//                }
                    
                
                
                
                // EDITED BY AAKASH
                 Double TaxAmount = 0.00; 
                // Double TotalAmountwithTax = 0.00;
                Double BasicRate = num * gcat.getrate();
                TotalAmountwithTax = TotalAmountwithTax+BasicRate;
              
                String TaxCatID1= gcat.getTaxCategory();
                
                if(TaxCatID1 !=null && TaxCatID1.trim().length()>0){
                    Double TaxRate1 = GetTaxRateByTaxCatID(TaxCatID1);
                    TaxAmount1=(BasicRate*TaxRate1);
                   TaxAmount=TaxAmount1;
                      TotalAmountwithTax =TotalAmountwithTax+TaxAmount1;
                      taxAmount_text.setText(decimalFormat.format(TaxAmount1));
                      TotalAmount_text.setText(decimalFormat.format(TotalAmountwithTax));
                   
                } else{TaxAmount1=0.00;
                }
                 
                 // EDITED BY LIPI
                String TaxCatID2= gcat.getTaxCategory2();
               
                  Double TaxRate2 = GetTaxRateByTaxCatID1(TaxCatID2);
                 
                  if(TaxCatID2!=null && TaxCatID2.trim().length()>0){
                if(gcat.getbasic1()==true){ 
                     TaxAmount2=(BasicRate*TaxRate2);
                     TaxAmount=TaxAmount2;
                      } 
//                if(gcat.getcascade1()==true){ 
                else{
                    TaxAmount2=((BasicRate+TaxAmount1)*TaxRate2);
                    TaxAmount=TaxAmount2;
                       }
                TaxAmount=TaxAmount2;
                      TotalAmountwithTax =TotalAmountwithTax+TaxAmount2;
                     taxAmount_text.setText(decimalFormat.format(TaxAmount2));
                      TotalAmount_text.setText(decimalFormat.format(TotalAmountwithTax));
                }else{TaxAmount2=0.00;
                }
                   
                   String TaxCatID3= gcat.getTaxCategory3();
               
                Double TaxRate3 = GetTaxRateByTaxCatID2(TaxCatID3);  
                  
               
                
                if(TaxCatID3 !=null && TaxCatID3.trim().length()>0){
                 
                  if(gcat.getbasic2()==true){
                  TaxAmount3=(BasicRate*TaxRate3);
                  TaxAmount=TaxAmount1+TaxAmount2+TaxAmount3;
                  TotalAmountwithTax =TotalAmountwithTax+ TaxAmount1+TaxAmount2+TaxAmount3;
                  taxAmount_text.setText(decimalFormat.format(TaxAmount3));
                  TotalAmount_text.setText(decimalFormat.format(TotalAmountwithTax));
               
                      }
                     else{
                       TaxAmount3=((BasicRate+TaxAmount1+TaxAmount2)*TaxRate3);
                      TaxAmount=TaxAmount1+TaxAmount2+TaxAmount3;
                      TotalAmountwithTax =TotalAmountwithTax+TaxAmount;
                      taxAmount_text.setText(decimalFormat.format(TaxAmount));
                      TotalAmount_text.setText(decimalFormat.format(TotalAmountwithTax));
                      }
                
                }else{TaxAmount3=0.00;
                }
                  
//                TaxAmount=TaxAmount1+TaxAmount2+TaxAmount3;
//                TotalAmountwithTax =TotalAmountwithTax+ TaxAmount1+TaxAmount2+TaxAmount3;
                  TaxAmount=TaxAmount2+TaxAmount1+TaxAmount3;
                      TotalAmountwithTax = BasicRate +TaxAmount;
               taxAmount_text.setText(decimalFormat.format(TaxAmount));
               TotalAmount_text.setText(decimalFormat.format(TotalAmountwithTax));
                
                       
  
                
                if(OverAllGuestLimitFlag==0){
                     //if (num>avail_guest_limit &&num > max_guest  ) {
//              if ( num = avail_guest_limit) {
//                  amount.setText(decimalFormat.format(num * gcat.getrate()));     
//              }else
                                if ( num > max_guest) {
              
                  //  JOptionPane.showMessageDialog(this, "The guest number exceeds the avail guest limit", null, JOptionPane.OK_OPTION);
                                      JOptionPane.showMessageDialog(this, "The guest number exceeds the maximum limit", null, JOptionPane.OK_OPTION);

                  guestno.setText(null);    
                    amount.setText(null);
                    taxAmount_text.setText(null);
                    TotalAmount_text.setText(null);
                  
                  
                      }
                 else if ( num >avail_guest_limit) {
                      JOptionPane.showMessageDialog(this, "The guest number exceeds the avail guest limit", null, JOptionPane.OK_OPTION);
                    guestno.setText(null);      
                    amount.setText(null);
                    taxAmount_text.setText(null);
                    TotalAmount_text.setText(null);
                       } 
                  else{   amount.setText(decimalFormat.format(num * gcat.getrate()));}
                      
                   

                }
              
                } 
                else {
                    amount.setText("0.00");
                    taxAmount_text.setText("0.00");
                    TotalAmount_text.setText("0.00");
                    
            }
  
                

}catch (NumberFormatException ex) {
           //guestno.setFocusable(false);
           JOptionPane.showMessageDialog(this, "Enter a valid number", "Wrong number Format", JOptionPane.OK_OPTION);
           // guestno.setText(null);
              //guestno.setFocusable(true);
              
        }
catch (Exception e) {
            e.printStackTrace();
        }

   
    if (evt.getKeyText(evt.getKeyCode()).equals("Enter")){
         gname.setFocusable(true);
         gname.requestFocusInWindow();
        
    }
    }//GEN-LAST:event_guestnoKeyReleased
    
   
   
   
//    private int  availableguestlimit1=0;
    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        
        try{
        
                 
   
               int pregno = 0;
               // int  availableguestlimit1=0;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis((new Date()).getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                Date d = new Date();
                d.setTime(cal.getTimeInMillis());
                Date d1 = new Date();
                cal.add(Calendar.DATE, 1);
                d1.setTime(cal.getTimeInMillis());
                System.out.println(d);
                amount.setText(null);
                guestno.setText(null);
                taxAmount_text.setText(null);
                TotalAmount_text.setText(null);
                
                System.out.println(d1);
                GuestCategory gcat = (GuestCategory) jComboBox2.getSelectedItem();
                 // int availableguestlimit1=0;
               // int x = Integer.parseInt(availableguestlimit.getText());
               // AvailableOverAllGuest = x;
                 
              
            //   maxguestlimit.setText(String.valueOf(gcat.getmaxguest()));
                   
                try{ 
              
                  
                   int value1 = gcat.getmaxguest();
                  int value = gcat.getmaxguest();
                  Object obj4 =  new StaticSentence(m_App.getSession(), "SELECT IFNULL(SUM(NUM),0) FROM GUESTLOG WHERE MEMNO=? AND GUESTCAT=? AND DATE>? AND DATE<?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING , Datas.TIMESTAMP, Datas.TIMESTAMP}), SerializerReadString.INSTANCE).find(new Object[]{customerInfo.getId(),gcat.getid(), d, d1});
                     if(obj4!=null){  
                        String x1 = obj4.toString();
                        value = gcat.getmaxguest() - Integer.parseInt(x1);
                        value1 = gcat.getmaxguest() ;
                    } 
                     else{
                         value = gcat.getmaxguest();
                     }
                    
               maxguestlimit.setText(""+value1);
                  availableguestlimit.setText(""+value);
                }
                catch (Exception e) {
                e.printStackTrace();
                             }
                      
              guestno.requestFocusInWindow();
              }
               catch (Exception e) {
                e.printStackTrace();
            }
 // TODO add your handling code here:}                              
    }//GEN-LAST:event_jComboBox2ItemStateChanged
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
//        if (customerInfo == null) {
//        JOptionPane.showMessageDialog(this, "Please Select Member First", null, JOptionPane.OK_OPTION);
//       
//        }
//else 
            try {
            if (JOptionPane.showConfirmDialog(this, "Do you need to create a debit bill", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        
                        String name = "";
                        List<PaymentInfo> pinfo = new ArrayList<PaymentInfo>();
                        List lnames = new ArrayList();
                        if (jComboBox2.getSelectedIndex() != -1 && customerInfo != null && guestno.getText().length() > 0) {
                            if (Integer.parseInt(guestno.getText()) == gnames.size()) {
                                Date d = new Date();
                                GuestCategory gcat = (GuestCategory) jComboBox2.getSelectedItem();

                                int gno = Integer.parseInt(guestno.getText());
                                //*/
                                if (gno == gmodel.getSize()) {
                                    CustomerInfoExt cinfo = new CustomerInfoExt(customerInfo.getId());
                                    cinfo.setSearchkey(customerInfo.getSearchkey());
                                    cinfo.setName(customerInfo.getName());
                                    JPaymentSelect paymentdialog = JPaymentSelectReceipt.getDialog(new JFrame());
                                    paymentdialog.init(m_App);
                                    // boolean flag=paymentdialog.showDialog(Double.parseDouble(amount.getText()), cinfo, m_App.getAppUserView().getUser().getName(),true);
                                    //  if(flag==true){
                                    PaymentInfoTicket payment = new PaymentInfoTicket(Double.parseDouble(TotalAmount_text.getText()), "debt" );

                                    pinfo.add(payment);
                                    BillInfo ticket = new BillInfo();
                                    ticket.setID(UUID.randomUUID().toString());
                                    ticket.setPayments(pinfo);
                                    ticket.setCustomer(cinfo);
                                    ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
                                    ticket.setCreatedDate(getdate());
                                    ticket.setActiveCash(m_App.getActiveCashIndex());
                                    ticket.setFloor("Guest Entry");
                                    //Guest cat changes-start
                                    ticket.setPlace(gcat.getid());
                                    //Guest cat changes-end
                                    
                                     String TaxID = getTaxIDByTaxCatID(gcat.getTaxCategory());
                                      String TaxID2 = getTaxIDByTaxCatID1(gcat.getTaxCategory2());
                                        String TaxID3 = getTaxIDByTaxCatID2(gcat.getTaxCategory3());
                                   
                                    
                                    String rnum = m_dlSales.payaccount2(ticket, m_App.getInventoryLocation(), true , TaxID,TaxID2,TaxID3 , Double.parseDouble(amount.getText()) , Double.parseDouble(taxAmount_text.getText()));
                                    //  }
                                    if (!(rnum == null || rnum.equals("false"))) {
                                        String nametemp = null;
                                        int i = 0;
                                        for (i = 0; i < gmodel.getSize(); i++) {
                                            String tempname = gmodel.getElementAt(i).toString();
                                            if (name.equals("")) {
                                                name = tempname;
                                            } else {
                                                name += ":" + tempname;
                                            }
                                            if (nametemp == null) {
                                                nametemp = tempname;
                                            } else {
                                                nametemp = nametemp + " , " + tempname;
                                                if ((i + 1) % 3 == 0) {
                                                    lnames.add(nametemp);
                                                    nametemp = null;
                                                }
                                            }

                                        }
                                        if (i % 3 != 0) {
                                            lnames.add(nametemp);
                                        }

                                        Double amt = gcat.getrate() * gno;
                                        String tid = UUID.randomUUID().toString();
                                        Double TaxAmount = Double.parseDouble(taxAmount_text.getText());
                                        Double TotalAmountWithTax = Double.parseDouble(TotalAmount_text.getText());
//                                        Double TaxAmount1 = Double.parseDouble(taxAmount_text.getText());
//                                         Double TaxAmount2 = Double.parseDouble(taxAmount_text.getText());
//                                          Double TaxAmount3 = Double.parseDouble(taxAmount_text.getText());
//                                         
                                        Date dnow = new Date();
                                        String custAcc = null;
                                        Object obj = new PreparedSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(customerInfo.getId());
                                        if (obj != null) {
                                            custAcc = obj.toString();
                                        }
                                        
                                        String TaxAccountID = getTaxAccountIDByTaxCatID(gcat.getTaxCategory());
                                        String TaxAccountID1 = getTaxAccountIDByTaxCatID1(gcat.getTaxCategory2());
                                        String TaxAccountID2 = getTaxAccountIDByTaxCatID2(gcat.getTaxCategory3());
                                        
                                        Object[] value = new Object[]{UUID.randomUUID().toString(), customerInfo.getId(), d, gcat.getid(), amt, gno, name, rnum, m_App.getAppUserView().getUser().getName(),gcat.getTaxCategory(),TaxAmount,gcat.getTaxCategory2(),gcat.getTaxCategory3()};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO GUESTLOG(ID,MEMNO,DATE,GUESTCAT,AMOUNT,NUM,NAMES,RECEIPTNO,CREATEDBY,TAXCAT,TAXAMOUNT,TAXCAT1,TAXCAT2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.STRING})).exec(value);
                                        
                                        
                                        
                                        
                                        // *********************************************************** CHANGES DONE BY AKASH ******************************************************** 
                                            String TaxCatName = getTaxCatNameIDByTaxCatID(gcat.getTaxCategory());
                                         String TaxCatName2 = getTaxCatNameIDByTaxCatID1(gcat.getTaxCategory2());
                                          String TaxCatName3 = getTaxCatNameIDByTaxCatID2(gcat.getTaxCategory3());
                                        
                                        
                                         printTicket(lnames, rnum, cinfo.getName(), pinfo, amt, gcat, gno, cinfo.getSearchkey(),TaxCatName,TaxAmount,TaxCatName2,TaxCatName3,binfo);
                                        
                                        checkForDebitSMS(ticket, (TotalAmountWithTax));
                                        
                                        
                                        if(TaxAmount1>0){
                                            
                                            
                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount1, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID, 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value1);
                                            
                                        } else{
                                            
                                             Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount1, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID, 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value1);
                                        }
                                      if(TaxAmount2>0){
//                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", gcat.getAccount(), 0.0, dnow, true};
//                                            dmang.insertintoaccjoutnal1(value1);
                                            
                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount2, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID1, 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value1);
                                            
                                        }else{Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount2, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID1, 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value1);}
                                        if(TaxAmount3>0){
//                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", gcat.getAccount(), 0.0, dnow, true};
//                                            dmang.insertintoaccjoutnal1(value1);
                                            
                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount3, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID2, 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value1);
                                            
//                                            Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount1, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID, 0.0, dnow, true};
//                                            dmang.insertintoaccjoutnal1(value2);
                                            
                                        }else{
                                             
                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TaxAmount3, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", TaxAccountID2, 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value1);
                                        }
//                                          
//                                        else{
//                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", TotalAmountWithTax, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", gcat.getAccount(), 0.0, dnow, true};
//                                            dmang.insertintoaccjoutnal1(value1); 
//                                        }
                                        
                                        Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Guest Entry", "GE", amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", gcat.getAccount(), 0.0, dnow, true};
                                            dmang.insertintoaccjoutnal1(value2);
                                        
                                        Object[] value5 = new Object[]{UUID.randomUUID().toString(), tid, customerInfo.getId(), dnow, "D", "Guest Entry", "GE", TotalAmountWithTax, dnow, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Guest Fee", custAcc, TotalAmountWithTax, true};
                                        dmang.insertintoaccjoutnal(value5);
                              //TODO add the accountjournal entry here
                                        
                                        

                                        // *********************************************************** CHANGES DONE BY AKASH ******************************************************** 
                                        
                                       
                                       
                                        
                                        
                                        // add by tejeswini-----------
                                        Object[] customer = dmang.getMamberbySkey(customerInfo.getSearchkey());
                                          customerInfo.setMobile(String.valueOf(customer[3]));
                                        // String id=String.valueOf(customer[0]);
//                                        String message="Dear Member,\rYour a/c "+customerInfo.getSearchkey() +"  with us has been debited by Rs."+dmang.ConvertDoubleToString(TotalAmountWithTax)+" for Guest Entry on "+Formats.DATE.formatValue(d)+", Receipt no "+rnum+"";
//                                        
//                                          if (customerInfo.getMobile() != null && customerInfo.getMobile().trim().length() == 10) {
//                                                  dmang.updatetosendMsg(message, customerInfo.getId(), customerInfo.getMobile(), 2);
//                                         }

                                         String id=null;
                                      
                                         
                                        id=String.valueOf(customerInfo.getId());
                                         
                                          System.out.println("customerid:::::"+id);
                                         
                                         
                                         
//                                          Object[] objBalance1=(Object[])new StaticSentence(app.getSession(), "SELECT ((OBDEBIT+CURDEBIT)-(OBCREDIT+CURCREDIT)) FROM trailbalance where ACCOUNTID=(SELECT ACCOUNT FROM CUSTOMERS WHERE ID=?)", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id);
//                                    Object[] objBalance2=(Object[])new StaticSentence(app.getSession(), "select sum(total) from payments where receipt in (select id from receipts where id in(select receipt from bill where id in(select billref from creditconflist_arv where customer=? and receipt is not null)) and closecashseq is null) " , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id);
//                                    
                                    Object[] objBalance1=(Object[])new StaticSentence(m_App.getSession(), "SELECT ((OBDEBIT+CURDEBIT)-(OBCREDIT+CURCREDIT)) FROM trailbalance where ACCOUNTID=(SELECT ACCOUNT FROM CUSTOMERS WHERE ID=?)", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id);
                                    Object[] objBalance2=(Object[])new StaticSentence(m_App.getSession(), "select sum(total) from payments where receipt in (select id from receipts where id in(select receipt from bill where id in(select billref from creditconflist_arv where customer=? and receipt is not null)) and closecashseq is null) " , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(id);
                                    
                             Double netBalance=0.00;
                            double b1=Double.parseDouble(objBalance1[0].toString());
                            // double b2=Double.parseDouble(objBalance2[0].toString());
                                     
//                                   netBalance=Double.parseDouble(objBalance1[0].toString())+Double.parseDouble(objBalance2[0].toString());
                                 netBalance=b1;
                                   System.out.println("unformated netBalance is"+netBalance);
                                  DecimalFormat df = new DecimalFormat("#.00"); 
                                   netBalance=Double.parseDouble(df.format(netBalance));
                                   String msg=null;
                                        //  msg="Dear Member,\rYour a/c "+customerInfo.getSearchkey() +"  with us has been debited by Rs."+dmang.ConvertDoubleToString(TotalAmountWithTax)+" for Guest Entry on "+Formats.DATE.formatValue(d)+", Receipt no "+rnum+".Net Balance is Dr. Rs."+netBalance;

//                                          if (customerInfo.getMobile() != null && customerInfo.getMobile().trim().length() == 10) {
//                                                  dmang.updatetosendMsg(msg, customerInfo.getId(), customerInfo.getMobile(), 2);
//                                         }

//                                   if (netBalance>0)
//                                   {
//                                       checkForDebitSMS(ticket, (TotalAmountWithTax), netBalance);
//                                       //msg="Dear Member,\rYour a/c "+customerInfo.getSearchkey() +"  with us has been debited by Rs."+dmang.ConvertDoubleToString(TotalAmountWithTax)+" for Guest Entry on "+Formats.DATE.formatValue(d)+", Receipt no "+rnum+".Net Balance is Dr. Rs."+netBalance;
//                                   
//                                   }
//                                   if (netBalance<0)
//                                   {    
//                                       netBalance=(netBalance*(-1));
//                                       checkForDebitSMS(ticket, (TotalAmountWithTax), netBalance);
//                                       //msg="Dear Member,\rYour a/c "+customerInfo.getSearchkey() +"  with us has been debited by Rs."+dmang.ConvertDoubleToString(TotalAmountWithTax)+" for Guest Entry on "+Formats.DATE.formatValue(d)+", Receipt no "+rnum+".Net Balance is Cr. Rs."+netBalance;
//                                   
//                                   } 
                                         
                                         
                                         // if (customerInfo.getMobile() != null && customerInfo.getMobile().trim().length() == 10) {
                                                // dmang.updatetosendMsg(msg, customerInfo.getId(), customerInfo.getMobile(), 2);
                                         //}
                                         
                                         
                                         
                                         
                                         
                                 
                                        loadData();
                                    } else {
                                        if (rnum.equals("false")) {
                                            JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.OK_OPTION);
                                        }
                                    }
                                // }
                                } else {
                                    JOptionPane.showMessageDialog(null, "The Guest limit exceeds for the category", "Guest Limit Exceeds", JOptionPane.WARNING_MESSAGE);

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "The Guest list does not match with guest number", null, JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            //exceeds
                            JOptionPane.showMessageDialog(null, "Please Fill the form completely", null, JOptionPane.WARNING_MESSAGE);
                        }
                        return null;
                    }
                };
                t.execute();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                cardno.requestFocus();
                cardno.setText(null);
                memno.setText(null);
                mname.setText(null);
//            reset();
            }

        }); //akshatha:to read a card from card reader without port num
        
        
}//GEN-LAST:event_jButton10ActionPerformed

    private void cardnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardnoActionPerformed
        // TODO add your handling code here:
        String custoid;
        String card = cardno.getText();
        if (card.length() > 0) {
            try {
                Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT C.ID,C.SEARCHKEY,C.NAME,M.CARD,M.ID  FROM CUSTOMERS C,MEMDEPENDENT M WHERE M.MEMNO=C.ID AND C.VISIBLE=TRUE AND M.CARD=?   UNION ALL  SELECT C.ID,C.SEARCHKEY,C.NAME,C.CARD,C.ID  FROM CUSTOMERS C WHERE C.VISIBLE=TRUE AND C.CARD=?",
                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(new Object[]{card, card});
                if (obj == null) {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
                } else {
                    custoid = obj[0].toString();
                    customerInfo = m_dlSales.loadCustomerExt(custoid);
                    memno.setText(obj[1].toString());
                    mname.setText(obj[2].toString());
                    
                    
                int pregno = 0;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis((new Date()).getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                Date d = new Date();
                d.setTime(cal.getTimeInMillis());
                Date d1 = new Date();
                cal.add(Calendar.DATE, 1);
                d1.setTime(cal.getTimeInMillis());
                System.out.println(d);
                System.out.println(d1);
                    
                    
                    
                    
                    
                   Object obj1 =  new StaticSentence(m_App.getSession(), "SELECT SUM(NUM) FROM GUESTLOG WHERE MEMNO=?  AND DATE>? AND DATE<?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), SerializerReadString.INSTANCE).find(new Object[]{customerInfo.getId(), d, d1});
                    // for(Object[] obj:objlist){
                          if (obj1 != null) {
                              String x = obj1.toString();
                            Billed_OverAllGuest = Integer.parseInt(x);
                         
                          
                     }else{
                           Billed_OverAllGuest=0;   
                     }
                    
                    
                    
                   Object[] obj10 = (Object[]) new StaticSentence(m_App.getSession(), "select VALUE from GENERALTABLE WHERE NAME='OVERALL GUEST LIMIT'",null, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                   String value=null;
                   
                   if (obj10 != null) {
                   
                       
                       value = obj10[0].toString();
                       if(value.equals("NO")){
                       
                          OverAllGuestLimitFlag = 0; 
                       
                        }
                       else{
                            OverAllGuestLimitFlag = 1; 
                            limit3=Integer.parseInt(obj10[0].toString());
                            AvailableOverAllGuest = limit3;
                        
                            detail_panel.setVisible(false);   
                            jTable2.setVisible(false);

                            int Curr_Limit = AvailableOverAllGuest - Billed_OverAllGuest;
                            availableguestlimit.setText(""+Curr_Limit); 
                       }
                       }
                       else{
                      JOptionPane.showMessageDialog(this, "No Value Set In General Table", null, JOptionPane.WARNING_MESSAGE); 
                       }      
                    
                    
                    
                    
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
 //akshatha:to read a card from card reader without port num
    }//GEN-LAST:event_cardnoActionPerformed

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jComboBox2ActionPerformed

private void guestnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestnoActionPerformed
                 //TODO add your handling code here:
}//GEN-LAST:event_guestnoActionPerformed

    private void maxguestlimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxguestlimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxguestlimitActionPerformed
      
    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed

    }//GEN-LAST:event_jComboBox2KeyPressed
            
    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
     
     // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void availableguestlimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availableguestlimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_availableguestlimitActionPerformed

    private void memnoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_memnoCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_memnoCaretPositionChanged

    private void guestnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guestnoKeyPressed

    }//GEN-LAST:event_guestnoKeyPressed

    private void jTable2ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable2ComponentHidden
                    // TODO add your handling code here:
    }//GEN-LAST:event_jTable2ComponentHidden

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
 //TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void memnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memnoActionPerformed

    private void guestnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guestnoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_guestnoKeyTyped

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
      
    }//GEN-LAST:event_jPanel1KeyPressed

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:
    // 
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void DetailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetailKeyPressed

    }//GEN-LAST:event_DetailKeyPressed

    private void DetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetailActionPerformed
 try {
            Date sdate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getStartDate());
            Date edate = (Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getEndDate());
            String MemNo = customerInfo.getId();
            
             gtablemodel2 = guestlisttable2.loadInstance(m_App, sdate, edate , MemNo);
             
             jTable2.setModel(gtablemodel2.getTableModel());
              detail_panel.setVisible(true); 
              jTable2.setVisible(true);
            
             
 
 
      } catch (Exception e) {
               e.printStackTrace();
        }         
    }//GEN-LAST:event_DetailActionPerformed

    private void All_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_All_RadioItemStateChanged
         if(All_Radio.isSelected()){
             GuestType_Combo.setVisible(false);
         }
         else{
             GuestType_Combo.setVisible(true);
         }
    }//GEN-LAST:event_All_RadioItemStateChanged

    private void SelectGuest_RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SelectGuest_RadioItemStateChanged
        if(SelectGuest_Radio.isSelected()){
             GuestType_Combo.setVisible(true);
         }
         else{
             GuestType_Combo.setVisible(false);
             GuestType_Combo.setSelectedIndex(-1);
         }
    }//GEN-LAST:event_SelectGuest_RadioItemStateChanged

    private void generate_report_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_report_buttonActionPerformed
        
        
        DataSourceProvider data1 = new DataSourceProvider(gtablemodel.getGuestList());
        DataSourceForGuestEntryReport dsfc = new DataSourceForGuestEntryReport(gtablemodel.getGuestList());
        data1.setDataSource(dsfc);
        Map reportparams = new HashMap();
        reportparams.put("CLUBNAME", m_App.getSession().getCompanyName());
        reportparams.put("ADDR", m_App.getSession().getCompanyAddress());
        
        String RPH = "From :  "+jParamsDatesInterval1.getStartDate()+"  To : "+jParamsDatesInterval1.getEndDate()+".";
        reportparams.put("HEADER",RPH);
        reportparams.put("CurrDate",Formats.TIMESTAMP.formatValue(new Date()));
      

       JasperPrint jp = JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/GuestEntryReport.jrxml", reportparams, false, data1, true, null); 
                         
                        
    }//GEN-LAST:event_generate_report_buttonActionPerformed

    public long startSec = 0;
    private void cardnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardnoKeyPressed
         char c = evt.getKeyChar();
        if(cardno.getText()!=null){
            int length = cardno.getText().trim().length();
            if(length==1){
                startSec = System.currentTimeMillis();
            }
            else if(length>1){
                long Currsec = System.currentTimeMillis();
                long diff = Currsec-startSec;
                if(diff>750){
                    JOptionPane.showMessageDialog(this, "Do not use keyboard. Please swipe card.");
                    cardno.setText(null);
                    System.out.println("Time Taken : "+diff);
                }
                if(c==KeyEvent.VK_ENTER){
                    System.out.println("Time Taken : "+diff);
                }
            }
        }
    }//GEN-LAST:event_cardnoKeyPressed

    private void viewPhoto_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPhoto_buttonActionPerformed
        if(customerInfo!=null){
           String MemberNo = customerInfo.getSearchkey();
           String MemberName = customerInfo.getName();
           String Cardno = cardno.getText();
           
            memberPhotoInfo memList;
            try {
                memList = memberPhotoInfo.getDialog(this, m_App,true,MemberNo,MemberName,Cardno);
                memList.showDialog();
            } catch (BasicException ex) {
                Logger.getLogger(GuestsEntryPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
           
       }
       
       else{
           JOptionPane.showMessageDialog(this, "Select Member first !");
       }
    }//GEN-LAST:event_viewPhoto_buttonActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
               MemText.setText(customerInfo.toString());
                SearchKeyLabel.setText(customerInfo.getSearchkey());
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void memRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memRadioButtonItemStateChanged
        // TODO add your handling code here:
        if(memRadioButton.isSelected()){
        MemText.setVisible(true);
        jButton5.setVisible(true);
        MemText.setText(null);
        SearchKeyLabel.setText(null);
        }else{
        MemText.setVisible(false);
        jButton5.setVisible(false);
        MemText.setText(null);
        SearchKeyLabel.setText(null);
        }
    }//GEN-LAST:event_memRadioButtonItemStateChanged

    private void total_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_textActionPerformed

    private void taxAmount_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxAmount_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taxAmount_textActionPerformed

    private void TotalAmount_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmount_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalAmount_textActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void gnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gnameActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void mnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnameActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_mnameActionPerformed

    private void SelectGuest_RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectGuest_RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelectGuest_RadioActionPerformed

    private void All_RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_All_RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_All_RadioActionPerformed

    private void GuestType_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuestType_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GuestType_ComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton All_Radio;
    private javax.swing.JButton Detail;
    private javax.swing.JTextField GrandTotal_text;
    private javax.swing.JComboBox GuestType_Combo;
    private javax.swing.JTextField MemText;
    private javax.swing.JButton Pay;
    private javax.swing.JLabel SearchKeyLabel;
    private javax.swing.JRadioButton SelectGuest_Radio;
    private javax.swing.JTextField TotalAmount_text;
    private javax.swing.JLabel agt;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField availableguestlimit;
    private javax.swing.JTextField cardno;
    private javax.swing.JPanel detail_panel;
    private javax.swing.JButton generate_report_button;
    private javax.swing.JTextField gname;
    private javax.swing.JTextField guestno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.openbravo.pos.reports.JParamsDatesInterval jParamsDatesInterval1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField maxguestlimit;
    private javax.swing.JRadioButton memAllRadioButton;
    private javax.swing.JRadioButton memRadioButton;
    private javax.swing.JTextField memno;
    private javax.swing.JTextField mname;
    private javax.swing.JTextField taxAmount_text;
    private javax.swing.JTextField taxTotal_text;
    private javax.swing.JTextField total_text;
    private javax.swing.JButton viewPhoto_button;
    // End of variables declaration//GEN-END:variables

    public void loadMemberDetails(String card) throws BasicException {
        // TODO add your handling code here:
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
                    customerInfo = m_dlSales.loadCustomerExt(custoid);
                    memno.setText(obj[1].toString());
                    mname.setText(obj[2].toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "swipe a card");
        }
    }

    public void cardswiped(String custCard) {
        try {
            loadMemberDetails(custCard);
        } catch (Exception e) {
            e.printStackTrace();
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
    
    // EDITED BY AAKASH FOR  INCLUDING TAX FOR GUEST CHARGES
    
    public Double GetTaxRateByTaxCatID(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
                }
                else{
                    TaxRate=0.00;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return TaxRate;
    } 
     public Double GetTaxRateByTaxCatID1(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
                }
                else{
                    TaxRate=0.00;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return TaxRate;
    }   
      public Double GetTaxRateByTaxCatID2(String id){
        Double TaxRate = 0.00;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    TaxRate = Double.parseDouble(obj.toString());
                }
                else{
                    TaxRate=0.00;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return TaxRate;
    } 
    
    public String getTaxIDByTaxCatID(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ID FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }
     public String getTaxIDByTaxCatID1(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ID FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }   
     
     public String getTaxIDByTaxCatID2(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ID FROM TAXES WHERE CATEGORY=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }
    
     public String getTaxAccountIDByTaxCatID(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }       
     
     
      public String getTaxAccountIDByTaxCatID1(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }   
       public String getTaxAccountIDByTaxCatID2(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT ACCOUNT FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }
      
      
      
     
     
    public String getTaxCatNameIDByTaxCatID(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    } 
    public String getTaxCatNameIDByTaxCatID1(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    }  
    public String getTaxCatNameIDByTaxCatID2(String id){
        String accName = null;
        try{
            if(id!=null){
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM TAXCATEGORIES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
                if(obj!=null){
                    accName = obj.toString();
                }
                else{
                    accName="";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return accName;
    } 
    
}