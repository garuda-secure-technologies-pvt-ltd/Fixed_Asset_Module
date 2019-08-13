package com.openbravo.pos.panels;

import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppLocal;
import java.awt.*;
//import java.text.ParseException;
import javax.swing.*;
import java.util.Date;
import java.util.UUID;
import javax.swing.table.*;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializerReadBasic;
//import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPrincipalApp;
//import com.openbravo.pos.forms.JRootApp;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.forms.LookupUtilityImpl;
//import com.openbravo.pos.forms.StartPOS;
//import com.openbravo.pos.panels.Jpendingremarkedit;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.lang.String;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author adrianromero
 */
public class JPanelCloseSalestd1 extends JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private DataLogicSales m_dlSales;
    private SalessModelstd1 m_salesmodel = null;
    private TicketParser m_TTP;
    private String String;
    private DataLogicFacilities dlfac;
    private Date date;

    /** Creates new form JPanelCloseMoney */
    public JPanelCloseSalestd1() {

        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        // m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);

        m_jTicketTable.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[]{Formats.STRING, Formats.STRING, Formats.CURRENCY, Formats.CURRENCY, Formats.CURRENCY}));
        m_jTicketTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        m_jScrollTableTicket.getVerticalScrollBar().setPreferredSize(new Dimension(25, 25));
        m_jTicketTable.getTableHeader().setReorderingAllowed(false);
        m_jTicketTable.setRowHeight(25);
        m_jTicketTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        m_jsalestable.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[]{Formats.STRING, Formats.INT, Formats.CURRENCY, Formats.CURRENCY}));
        m_jsalestable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        m_jScrollSales.getVerticalScrollBar().setPreferredSize(new Dimension(25, 25));
        m_jsalestable.getTableHeader().setReorderingAllowed(false);
        m_jsalestable.setRowHeight(25);
        m_jsalestable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

     
    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.CloseSale");
    }

    public void activate() throws BasicException {
        if(m_App.getAppUserView().getUser().getTypeOfUser()==1){
            m_jCloseCash.setVisible(false);
        }
        loadData();
    }

    public boolean deactivate() {
        // se me debe permitir cancelar el deactivate   
        return true;
    }
    /*  public void loaddata2() throws BasicException{
    //  init( app);
    loadData();
    }*/

    private void loadData() throws BasicException {

        // Reset
        date = new Date();
        m_jSequence.setText(null);
        m_jMinDate.setText(null);
        m_jMaxDate.setText(null);
        m_jCloseCash.setEnabled(false);
        jTextField1.setText(null); // AppLocal.getIntString("label.noticketstoclose");
        jTextField2.setText(null);
      
//        jTextField3.setText(null);
        m_jSalesSubtotal.setText(null);
        m_jSalesTaxes.setText(null);
        m_jSalesTotal.setText(null);

        m_jTicketTable.setModel(new DefaultTableModel());
        m_jsalestable.setModel(new DefaultTableModel());
       

        // LoadData
        m_salesmodel = SalessModelstd1.loadInstance(m_App, date);

        // Populate Data
        m_jSequence.setText(m_salesmodel.printSequence());
        m_jMinDate.setText(m_salesmodel.printDateStart());
        m_jMaxDate.setText(m_salesmodel.printDateEnd());



        m_jCloseCash.setEnabled(true);

        jTextField1.setText(m_salesmodel.printsubbill());
        jTextField2.setText(m_salesmodel.printsubtax());
        jTextField3.setText(m_salesmodel.printTotals());
        m_jSalesSubtotal.setText(m_salesmodel.printsubbill());
        m_jSalesTaxes.setText(m_salesmodel.printtaxamt());
        m_jSalesTotal.setText(m_salesmodel.printpdtamount());
       
        //  m_jCash.setText(m_salesmodel.printPaymentsTotal());

        //    m_jSales.setText(m_salesmodel.printSales());
        //   m_jSalesSubtotal.setText(m_salesmodel.printSalesBase());
        //   m_jSalesTaxes.setText(m_salesmodel.printSalesTaxes());
        //    m_jSalesTotal.setText(m_salesmodel.printSalesTotal());


        m_jTicketTable.setModel(m_salesmodel.getSalesModel());

        TableColumnModel jColumns = m_jTicketTable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(60);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(200);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(80);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(80);
        jColumns.getColumn(4).setResizable(false);

        m_jsalestable.setModel(m_salesmodel.getProductsModel());

        jColumns = m_jsalestable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(200);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(60);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(80);
        jColumns.getColumn(3).setResizable(false);

      

     
        jColumns.getColumn(0).setPreferredWidth(200);
        jColumns.getColumn(0).setResizable(true);
        jColumns.getColumn(1).setPreferredWidth(60);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(80);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(80);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(80);
        jColumns.getColumn(5).setResizable(false);

        m_jCloseCash.setEnabled(false);
        if (m_salesmodel.getTotals() > 0) {
            m_jCloseCash.setEnabled(true);
        }

    }

    private void printSales() throws BasicException {

        String sresource = m_dlSystem.getResourceAsXML("Printer.CloseSalestd");

        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("sales", m_salesmodel);
                //   Object obj=script.eval(sresource).toString();
                int temp = 0;//used to indicate whether bar counter or restaurant counter
                boolean flag = m_App.getAppUserView().getUser().hasPermission("res counter");
                Object obj = new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(m_App.getAppUserView().getUser().getWarehouse());
                String displayName=null;
                if(obj!=null){
                    displayName = obj.toString();
                }
                if (flag == true) {
                    temp = 1;
                    script.put("name", "Restaurant");

                } else {
                    script.put("name", "Bar");
                }
                // flag=m_App.getAppUserView().getUser().hasPermission("bar counter");
                // if(flag==true){
                //
                //  }
                //      temp=2;
                script.put("displayname", displayName);
                script.put("flag", temp);
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

    private void updatestockdiarydis() throws BasicException {
        /*  java.util.List<SalessModel.discountlist> li=m_salesmodel.getdisQTLineStk();
        for(SalessModel.discountlist d1: li){
        Object[] obj1=m_dlSales.getlocationFromStkCurrent(d1.printproduct());
        if(obj1!=null){
        // m_dlSales.updateStockVolume1(qty1, product);

        SentenceExec sent=  m_dlSales.getStockDiaryInsertinpdt();
        Date d=new Date();
        //-8 indicates discount accepted
        Object[] values= new Object[]{UUID.randomUUID().toString(),d,-8,obj1[0].toString(),d1.printproduct(),d1.getqty(),0.0,"Bill Discount",d1.printcreatedby()};
        sent.exec(values);
        }
        }*/
        java.util.List<SalessModelstd1.discountlist> l = m_salesmodel.getdisBillLineStk();
        for (SalessModelstd1.discountlist d2 : l) {
            if (d2.isMaintainInventory()) {
                Object[] obj1 = m_dlSales.getlocationFromStkCurrent(d2.printproduct());
                if (obj1 != null) {
                    // m_dlSales.updateStockVolume1(qty1, product);

                    SentenceExec sent = m_dlSales.getStockDiaryInsertinpdt();
                    Date d = date;
                    //-8 indicates discount accepted
                    Object[] values = new Object[]{UUID.randomUUID().toString(), d, -8, obj1[0].toString(), d2.printproduct(), (d2.getqty()), 0.0, "Bill Discount", d2.printcreatedby()};
                    sent.exec(values);
                }
            }
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        m_jSequence = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        m_jMinDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        m_jMaxDate = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        m_jScrollTableTicket = new javax.swing.JScrollPane();
        m_jTicketTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        m_jSalesTotal = new javax.swing.JTextField();
        m_jScrollSales = new javax.swing.JScrollPane();
        m_jsalestable = new javax.swing.JTable();
        m_jSalesTaxes = new javax.swing.JTextField();
        m_jSalesSubtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_jCloseCash = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.datestitle"))); // NOI18N
        jPanel4.setLayout(null);

        jLabel11.setText(AppLocal.getIntString("label.sequence")); // NOI18N
        jPanel4.add(jLabel11);
        jLabel11.setBounds(20, 20, 140, 14);

        m_jSequence.setEditable(false);
        m_jSequence.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(m_jSequence);
        m_jSequence.setBounds(160, 20, 160, 20);

        jLabel2.setText(AppLocal.getIntString("Label.StartDate")); // NOI18N
        jPanel4.add(jLabel2);
        jLabel2.setBounds(20, 50, 140, 14);

        m_jMinDate.setEditable(false);
        m_jMinDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jMinDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jMinDateActionPerformed(evt);
            }
        });
        jPanel4.add(m_jMinDate);
        m_jMinDate.setBounds(160, 50, 160, 20);

        jLabel3.setText(AppLocal.getIntString("Label.EndDate")); // NOI18N
        jPanel4.add(jLabel3);
        jLabel3.setBounds(20, 80, 140, 14);

        m_jMaxDate.setEditable(false);
        m_jMaxDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(m_jMaxDate);
        m_jMaxDate.setBounds(160, 80, 160, 20);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 10, 620, 120);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.paymentstitle"))); // NOI18N
        jPanel5.setLayout(null);

        m_jTicketTable.setFocusable(false);
        m_jTicketTable.setIntercellSpacing(new java.awt.Dimension(0, 1));
        m_jTicketTable.setRequestFocusEnabled(false);
        m_jTicketTable.setShowVerticalLines(false);
        m_jScrollTableTicket.setViewportView(m_jTicketTable);

        jPanel5.add(m_jScrollTableTicket);
        m_jScrollTableTicket.setBounds(20, 20, 490, 130);

        jLabel1.setText("Amount Total");
        jPanel5.add(jLabel1);
        jLabel1.setBounds(520, 20, 70, 14);

        jTextField1.setEditable(false);
        jPanel5.add(jTextField1);
        jTextField1.setBounds(590, 20, 100, 20);

        jLabel4.setText("Tax Total");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(520, 50, 70, 14);

        jTextField2.setEditable(false);
        jPanel5.add(jTextField2);
        jTextField2.setBounds(590, 50, 100, 20);

        jLabel5.setText("Total");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(520, 80, 60, 14);

        jTextField3.setEditable(false);
        jPanel5.add(jTextField3);
        jTextField3.setBounds(590, 80, 100, 20);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 130, 740, 160);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.salestitle"))); // NOI18N
        jPanel6.setLayout(null);

        m_jSalesTotal.setEditable(false);
        m_jSalesTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jSalesTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jSalesTotalActionPerformed(evt);
            }
        });
        jPanel6.add(m_jSalesTotal);
        m_jSalesTotal.setBounds(590, 100, 100, 20);

        m_jsalestable.setFocusable(false);
        m_jsalestable.setIntercellSpacing(new java.awt.Dimension(0, 1));
        m_jsalestable.setRequestFocusEnabled(false);
        m_jsalestable.setShowVerticalLines(false);
        m_jScrollSales.setViewportView(m_jsalestable);

        jPanel6.add(m_jScrollSales);
        m_jScrollSales.setBounds(20, 30, 490, 130);

        m_jSalesTaxes.setEditable(false);
        m_jSalesTaxes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel6.add(m_jSalesTaxes);
        m_jSalesTaxes.setBounds(590, 70, 100, 20);

        m_jSalesSubtotal.setEditable(false);
        m_jSalesSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel6.add(m_jSalesSubtotal);
        m_jSalesSubtotal.setBounds(590, 40, 100, 20);

        jLabel6.setText(AppLocal.getIntString("label.subtotalcash")); // NOI18N
        jPanel6.add(jLabel6);
        jLabel6.setBounds(520, 40, 90, 14);

        jLabel12.setText(AppLocal.getIntString("label.taxcash")); // NOI18N
        jPanel6.add(jLabel12);
        jLabel12.setBounds(520, 70, 90, 14);

        jLabel7.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
        jPanel6.add(jLabel7);
        jLabel7.setBounds(520, 100, 90, 14);

        jPanel1.add(jPanel6);
        jPanel6.setBounds(10, 290, 740, 180);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        m_jCloseCash.setText(AppLocal.getIntString("Button.CloseCash")); // NOI18N
        m_jCloseCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCloseCashActionPerformed(evt);
            }
        });
        jPanel2.add(m_jCloseCash);

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_END);

        add(jPanel3, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jCloseCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCloseCashActionPerformed
        // TODO add your handling code here:
        try {
            Transaction t = new Transaction(m_App.getSession()) {

                public Object transact() throws BasicException {
                    String tempbool = "false";
                    int count = 0;
                    String tempstr;
                    Date dNow = new Date();
                    dNow.setTime(date.getTime());
                    String vatdebitaccount = null;
                    AppView appv = LookupUtilityImpl.getInstance(null).getAppView();
                    // try{
                    Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT COUNT(ID) FROM QTICKET WHERE  BILLED = FALSE AND CREATEDBY = ? AND REASON IS NULL ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName());

                    if (obj[0] == null || obj == null) {
                        count = 0;
                    } else {
                        tempstr = obj[0].toString();
                        count = Integer.parseInt(tempstr);
                    }
                    // }
                    // catch(BasicException be)
                    //  {
                    //   }
                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, AppLocal.getIntString("message.qtreasonwarning"), AppLocal.getIntString("message.qtreasontitle"), JOptionPane.WARNING_MESSAGE);
                    } else {
                        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("message.wannaclosesale"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (res == JOptionPane.YES_OPTION) {


                            String username = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
                            //    java.sql.Timestamp dNowtemp=(java.sql.Timestamp)System.nanoTime();
                            int count1 = 2;
                            // try {
                            // Cerramos la caja si esta pendiente de cerrar.

                            Object[] con = (Object[]) new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM PEOPLE WHERE LOGINTIME IS NOT NULL AND ROLE = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(m_App.getAppUserView().getUser().getRole());
                            if (con != null && con[0] != null) {
                                count1 = Integer.parseInt(con[0].toString());
                            } else {
                                count1 = 2;
                            }
                            //  } catch (BasicException e) {
                            //   MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotclosecash"), e);
                            //   msg.show(null);
                            //  }
                            if (count1 > 1) {
                                JOptionPane.showMessageDialog(null, "Please Close Other Terminals", "Cannot Close Sale", JOptionPane.OK_OPTION);

                            } else {
                                //   try {
                                new StaticSentence(m_App.getSession(), "UPDATE PEOPLE SET CLOSESALE = ? , LOGINTIME=?,OPENSALE=?  WHERE ROLE = ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.NULL,Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{dNow, null,new Date(), m_App.getAppUserView().getUser().getRole()});


                                m_salesmodel.setDateEnd(dNow);

                                AppUser user = m_App.getAppUserView().getUser();
                                user.setCloseSaleTime(dNow);
                                // m_dlSystem.updateUser(user);

                                //ID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,PAYMENTREF
                                // AppUser suser=dlfac.getnewuserdetail(dlsys, sender);
                                String narration = "close sale from " + m_salesmodel.getDateStart() + " to " + m_salesmodel.getDateEnd();
                                Date dnow = new Date();
                                dnow.setTime(date.getTime());
                                //bar
                                // AppUser appuser=m_App.getAppUserView().getUser();
                                String tid = UUID.randomUUID().toString();
                                Object warehouse = new StaticSentence(m_App.getSession(), "select prcategories from people where id=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(m_App.getAppUserView().getUser().getId());
                                String[] str = null;
                                if (warehouse != null) {
                                    str = String.valueOf(warehouse).split("#");
                                }
                                String salesaccount = null;
                                String currentaccount = null;
                                String levyaccount = null;
                                String levyvalue = null;
                                // for (int i = 0; i < str.length; i++) {
                                for (SalessModelstd1.ProductsAccountLine pdtAcc : m_salesmodel.getProductsTotal()) {

                                    // System.out.println(str[i]);
                                    Object[] details = (Object[]) new StaticSentence(m_App.getSession(), "select salesaccount,customercurrentaccount,levyaccount,levyvalue from locations where id=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE})).find(pdtAcc.getLocation());
                                    if (details != null && details[0] != null && details[1] != null) {
                                        salesaccount = details[0].toString();
                                        currentaccount = details[1].toString();
                                    }
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "", pdtAcc.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, currentaccount, 0.0, dnow, null, true};
                                    dlfac.insertintoaccjoutnal2(value);
                                    java.util.List<SalessModelstd1.ProductsAccountLine> list = m_salesmodel.getprodutsAccountwiseTotal();
                                    Double amt = 0.0;
                                    for (SalessModelstd1.ProductsAccountLine pacc : list) {
                                        if (pacc.getLocation().equals(pdtAcc.getLocation())) {
                                            if (pacc.getAccount() != null) {
                                                Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", pacc.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, pacc.getAccount(), 0.0, dnow, null, true};
                                                dlfac.insertintoaccjoutnal2(value1);
                                                amt += pacc.getAmount();
                                            }
                                        }
                                    }
                                    if (amt != pdtAcc.getAmount()) {
                                        Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", pdtAcc.getAmount() - amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, salesaccount, 0.0, dnow, null, true};
                                        dlfac.insertintoaccjoutnal2(value1);
                                    }

                                    Double lpercentage = 0.0;
                                    if (details[3] != null && details[2] != null) {
                                        levyaccount = details[2].toString();
                                        levyvalue = details[3].toString();
                                        lpercentage = Double.parseDouble(levyvalue);
                                    }
                                    if (lpercentage > 0) {
                                        String narration1 = "Canteen levy " + lpercentage + "% of the total sales";
                                        Double lamt = 0.0;
                                        lamt = Double.valueOf(String.valueOf(dlfac.roundTwoDecimals((lpercentage * pdtAcc.getAmount()) / 100)));
                                        if (lamt > 0) {
                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "", lamt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration1, "7", 0.0, dnow, null, true};
                                            dlfac.insertintoaccjoutnal2(value1);
                                            Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", lamt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration1, levyaccount, 0.0, dnow, null, true};
                                            dlfac.insertintoaccjoutnal2(value2);
                                        }
                                    }
                                    vatdebitaccount = currentaccount;
                                 }
                                //praveen:crediting to vatpayable and debited from customercurrentaccount
                                LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
                                Map<String, GeneralSettingInfo> gs = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
                                 //GeneralSettingInfo account = gs.get("vatpayable");
                                // String vataccount = account.getValue().toString();
                                Double tamt = m_salesmodel.getTotaltax();
                                if(tamt>0.0){
                                    for(SalessModelstd1.TaxLine tax :m_salesmodel.getTaxLines()){
                                     if(tax.getAmount()>0.0){
                                    Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "",tax.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, tax.getAccountId(), 0.0, dnow, null, true};
                                    dlfac.insertintoaccjoutnal2(cvalue);
                                     }
                                    }
                                Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "",tamt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, vatdebitaccount, 0.0, dnow, null, true};
                                dlfac.insertintoaccjoutnal2(dvalue);
                                }

//                                if (user.hasPermission("bar counter")) {
//                                    Object[] value = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "", m_salesmodel.getTotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, "3", 0.0, dnow, null, true};
//                                    dlfac.insertintoaccjoutnal2(value);
//                                    java.util.List<SalessModel.ProductsAccountLine> list = m_salesmodel.getprodutsAccountwiseTotal();
//                                    Double amt = 0.0;
//                                    for (SalessModel.ProductsAccountLine pacc : list) {
//                                        if (pacc.getAccount() != null) {
//                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", pacc.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, pacc.getAccount(), 0.0, dnow, null, true};
//                                            dlfac.insertintoaccjoutnal2(value1);
//                                            amt += pacc.getAmount();
//                                        }
//                                    }
//                                    if (amt != m_salesmodel.getTotal()) {
//                                        Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", m_salesmodel.getTotal() - amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, dlfac.getaccountidByName("Bar sales"), 0.0, dnow, null, true};
//                                        dlfac.insertintoaccjoutnal2(value1);
//                                    }
//                                } else if (user.hasPermission("res counter")) {
//                                    Object[] value = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "", m_salesmodel.getTotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, "4", 0.0, dnow, null, true};
//                                    dlfac.insertintoaccjoutnal2(value);
//                                    java.util.List<SalessModel.ProductsAccountLine> list = m_salesmodel.getprodutsAccountwiseTotal();
//                                    Double amt = 0.0;
//                                    for (SalessModel.ProductsAccountLine pacc : list) {
//                                        if (pacc.getAccount() != null) {
//                                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", pacc.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, pacc.getAccount(), 0.0, dnow, null, true};
//                                            dlfac.insertintoaccjoutnal2(value1);
//                                            amt += pacc.getAmount();
//                                        }
//                                    }
//                                    if (amt != m_salesmodel.getTotal()) {
//                                        Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", m_salesmodel.getTotal() - amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, "7", 0.0, dnow, null, true};
//                                        dlfac.insertintoaccjoutnal2(value1);
//                                    }
//
//                                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE,(SELECT VALUE FROM GENERALTABLE WHERE NAME = ?) FROM GENERALTABLE WHERE NAME = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(new Object[]{"Canteen Levy Account", "Canteen Levy"});
//                                    Double lpercentage = Double.parseDouble(obj1[0].toString());
//                                    String narration1 = "Canteen levy " + lpercentage + "% of the total sales";
//                                    Double lamt = Double.valueOf(String.valueOf(dlfac.roundTwoDecimals((lpercentage * m_salesmodel.getTotal()) / 100)));
//                                    if (lamt > 0) {
//                                        Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "", lamt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration1, "7", 0.0, dnow, null, true};
//                                        dlfac.insertintoaccjoutnal2(value1);
//                                        Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", lamt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration1, obj1[1], 0.0, dnow, null, true};
//                                        dlfac.insertintoaccjoutnal2(value2);
//                                    }
//                                }


                                updatestockdiarydis();
                                String closesaleId = UUID.randomUUID().toString();//praveen:closeedsale id and reference to productwiseclosesale
                                Date productwiseSaleRefDate = new Date();
                                // boolean flag = user.hasPermission("StockCheckNotRequired");
                                // if (flag == false) {
                                String location = null;
                                java.util.List<SalessModelstd1.ProductsLine> pdtlist = m_salesmodel.getProductLine();
                                Map<String, String> catwid = new HashMap<String, String>();
                                for (int i = 0; i < pdtlist.size(); i++) {
                                    SalessModelstd1.ProductsLine pdtinfo = pdtlist.get(i);
                                    if (pdtinfo.isMaintainInventory()) {
                                        location = catwid.get(pdtinfo.getpdtcategory());
                                        if (location == null) {
                                            Object parentid = pdtinfo.getpdtcategory();
                                            Object[] catdetail = new Object[3];
                                            while (parentid != null) {
                                                catdetail = m_dlSales.getParentCategories(parentid.toString());
                                                parentid = catdetail[0];
                                            }
                                            location = catdetail[2].toString();
                                            catwid.put(pdtinfo.getpdtcategory(), location);
                                        }
                                        String id = UUID.randomUUID().toString();
                                        Double tempv = 0.0;
                                        Integer temp1 = pdtinfo.getqty() * -1;
                                        Object[] values = new Object[]{id, user.getName(), dNow, -1, location, pdtinfo.getpdtid(), temp1.doubleValue(), tempv, "QT"};
                                        Datas[] datas = new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO STOCKDIARY (ID,CREATEDBY,DATENEW,REASON1,LOCATION1,PRODUCT1,UNITS1,PRICE1,RNO) VALUES (?,?,?,?,?,?,?,?,?) ", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})).exec(values);
                                        //praveen:added to insert into productwiseclosesale
                                        Object[] pdtwiseSale = new Object[]{UUID.randomUUID().toString(), pdtinfo.getpdtid(), pdtinfo.getqty(), pdtinfo.getAmount(), productwiseSaleRefDate, closesaleId};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTWISECLOSESALE (ID,PRODUCT,QTY,RATE,CSDATE,CLOSESALEREF) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING}), null).exec(pdtwiseSale);
                                    }
                                }
                                m_salesmodel.getSequence();

                                new PreparedSentence(m_App.getSession(), "INSERT INTO CLOSEDSALE (ID,SEQUENCE,DATESTART,DATEEND,USER_,AMOUNT,ROLE) VALUES (?,?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{closesaleId, m_salesmodel.getSequence(), m_salesmodel.getDateStart(), dNow, m_App.getAppUserView().getUser().getId(), m_salesmodel.getTotal(), m_App.getAppUserView().getUser().getRole()});
                                //new PreparedSentence(m_App.getSession(), "UPDATE BILL B SET CLOSESALESEQ=? WHERE CLOSESALESEQ IS NULL AND CREATEDDATE <= ? AND CREATEDBY IN (SELECT NAME FROM PEOPLE WHERE ROLE=? )", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{user.getRole() + " : " + m_salesmodel.getSequence(), date, m_App.getAppUserView().getUser().getWarehouse()});
                                new PreparedSentence(m_App.getSession(), "UPDATE BILL B SET CLOSESALESEQ=? WHERE CLOSESALESEQ IS NULL AND CREATEDDATE <= ? AND WAREHOUSE=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{user.getRole() + " : " + m_salesmodel.getSequence(), date, m_App.getAppUserView().getUser().getWarehouse()});

                                //praveen:confirmer changes---start
                                new PreparedSentence(m_App.getSession(), "INSERT INTO RECEIPTS_ARV (ID,DATENEW,ATTRIBUTES,RUSER,PAYMENTREF,DESC_,CLOSECASHSEQ,CONFIRMER) SELECT R.ID,R.DATENEW,R.ATTRIBUTES,R.RUSER,R.PAYMENTREF,R.DESC_,R.CLOSECASHSEQ,R.CONFIRMER FROM RECEIPTS R,BILL B WHERE  R.CLOSECASHSEQ IS NOT NULL AND B.CLOSESALESEQ IS NOT NULL AND B.RECEIPT=R.ID ", null).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO RECEIPTS_ARV (ID,DATENEW,ATTRIBUTES,RUSER,PAYMENTREF,DESC_,CLOSECASHSEQ,CONFIRMER) SELECT R.ID,R.DATENEW,R.ATTRIBUTES,R.RUSER,R.PAYMENTREF,R.DESC_,R.CLOSECASHSEQ,R.CONFIRMER FROM RECEIPTS R  WHERE  R.CLOSECASHSEQ IS NOT NULL AND  R.ID NOT IN (SELECT RECEIPT FROM BILL) ", null).exec();
                                 //praveen:confirmer changes---end
                                new PreparedSentence(m_App.getSession(), "INSERT INTO PAYMENTS_ARV (ID,RECEIPT,PAYMENT,TOTAL,PUSER,PTIME,CUSTOMER) SELECT P.ID,P.RECEIPT,P.PAYMENT,P.TOTAL,P.PUSER,P.PTIME,P.CUSTOMER FROM RECEIPTS R,BILL B,PAYMENTS P WHERE  R.CLOSECASHSEQ IS NOT NULL  AND R.ID=P.RECEIPT AND B.CLOSESALESEQ IS NOT NULL AND B.RECEIPT=R.ID ", null).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO PAYMENTS_ARV (ID,RECEIPT,PAYMENT,TOTAL,PUSER,PTIME,CUSTOMER) SELECT P.ID,P.RECEIPT,P.PAYMENT,P.TOTAL,P.PUSER,P.PTIME,P.CUSTOMER FROM RECEIPTS R,PAYMENTS P WHERE  R.CLOSECASHSEQ IS NOT NULL  AND R.ID=P.RECEIPT AND  R.ID NOT IN (SELECT RECEIPT FROM BILL) ", null).exec();
                                 //praveen:initiator changes---start
                                new PreparedSentence(m_App.getSession(), "INSERT INTO BILL_ARV(ID,CUSTOMER,PLACE,WAITER,FLOOR,AMOUNT,CREATEDBY,CREATEDDATE,PAID,RECEIPT,TAXTOTAL,CLOSESALESEQ,warehouse,INITIATOR) SELECT B.ID,B.CUSTOMER,B.PLACE,B.WAITER,B.FLOOR,B.AMOUNT,B.CREATEDBY,B.CREATEDDATE,B.PAID,B.RECEIPT,B.TAXTOTAL,B.CLOSESALESEQ,b.warehouse,B.INITIATOR FROM BILL B,RECEIPTS R WHERE  R.CLOSECASHSEQ IS NOT NULL AND B.CLOSESALESEQ IS NOT NULL AND R.ID=B.RECEIPT", null).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO BILLITEM_ARV(ID,LINE,PARENTID,PRODUCT,DMULTIPLY,ATTRIBUTES,RATE,TOTAL,Tax1ID,Tax1,Tax2ID,T2_Cas,Tax2,Tax3ID,T3_Cas,Tax3) SELECT BI.ID,BI.LINE,BI.PARENTID,BI.PRODUCT,BI.DMULTIPLY,BI.ATTRIBUTES,BI.RATE,BI.TOTAL,BI.Tax1ID,BI.Tax1,BI.Tax2ID,BI.T2_Cas,BI.Tax2,BI.Tax3ID,BI.T3_Cas,BI.Tax3 FROM BILLITEM BI,BILL B,RECEIPTS R WHERE  R.CLOSECASHSEQ IS NOT NULL AND B.CLOSESALESEQ IS NOT NULL AND R.ID=B.RECEIPT AND B.ID=BI.PARENTID", null).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO QTICKET_ARV(ID,CUSTOMER,PLACE,WAITER,FLOOR,PRCATEGORY,BILLED,BILLREF,CREATEDBY,CRDATE,REASON,warehouse,INITIATOR)  SELECT Q.ID,Q.CUSTOMER,Q.PLACE,Q.WAITER,Q.FLOOR,Q.PRCATEGORY,Q.BILLED,Q.BILLREF,Q.CREATEDBY,Q.CRDATE,Q.REASON,q.warehouse,Q.INITIATOR FROM QTICKET Q,BILL B,RECEIPTS R WHERE Q.BILLED=TRUE AND B.CLOSESALESEQ IS NOT NULL  AND B.ID=Q.BILLREF AND R.CLOSECASHSEQ IS NOT NULL AND R.ID=B.RECEIPT ", null).exec();
                                //praveen:initiator changes---end
                                new PreparedSentence(m_App.getSession(), "INSERT INTO QTITEMS_ARV (ID,LINE,PARENTID,PRODUCT,DMULTIPLY,RATE,ATTRIBUTES) SELECT Q.ID,Q.LINE,Q.PARENTID,Q.PRODUCT,Q.DMULTIPLY,Q.RATE,Q.ATTRIBUTES FROM QTITEMS Q,QTICKET QT,BILL B,RECEIPTS R WHERE QT.BILLED=TRUE  AND QT.ID=Q.PARENTID AND B.CLOSESALESEQ IS NOT NULL  AND B.ID=QT.BILLREF AND R.CLOSECASHSEQ IS NOT NULL AND R.ID=B.RECEIPT ", null).exec();
                                new PreparedSentence(m_App.getSession(), "DELETE FROM RECEIPTS  WHERE CLOSECASHSEQ IS NOT NULL AND ID IN (SELECT RECEIPT FROM BILL WHERE CLOSESALESEQ IS NOT NULL) ", null).exec();
                                new PreparedSentence(m_App.getSession(), "DELETE FROM RECEIPTS  WHERE CLOSECASHSEQ IS NOT NULL AND  ID NOT IN (SELECT RECEIPT FROM BILL)", null).exec();

                                /*  new PreparedSentence(m_App.getSession()
                                , "UPDATE BILL B SET CLOSESALESEQ=? WHERE CLOSESALESEQ IS NULL AND CREATEDDATE < ? AND CREATEDBY IN (SELECT NAME FROM PEOPLE WHERE ROLE=? )"
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{user.getRole()+" : "+m_salesmodel.getSequence(),date,m_App.getAppUserView().getUser().getRole()});
                                 */
                                printSales();
                                printSales();
                                //   out.close();
                                // Mostramos el mensaje
                               // JOptionPane.showMessageDialog(null, "Press Ok Once the printing is complete", AppLocal.getIntString("message.title"), JOptionPane.INFORMATION_MESSAGE);

                                new StaticSentence(m_App.getSession(), "UPDATE PEOPLE SET CLOSESALE = ? , LOGINTIME=? , OPENSALE=?  WHERE ROLE = ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.NULL,Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{dNow, null,new Date(), m_App.getAppUserView().getUser().getRole()});


                                //  } catch (Exception e) {
                                //      MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("label.noticketstoclose"), e);
                                //      msg.show(null);
                                //      e.printStackTrace();
                                //   }
                                // JPrincipalApp app= new JPrincipalApp();
                                JPrincipalApp.m_approot.closeAppView();
                            }
                        }
                    }
                    return null;
                }
            };
            t.execute();
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("label.noticketstoclose"), e);
            msg.show(this);
            e.printStackTrace();
        }
    }//GEN-LAST:event_m_jCloseCashActionPerformed

    private void m_jMinDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jMinDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jMinDateActionPerformed

private void m_jSalesTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jSalesTotalActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_m_jSalesTotalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton m_jCloseCash;
    private javax.swing.JTextField m_jMaxDate;
    private javax.swing.JTextField m_jMinDate;
    private javax.swing.JTextField m_jSalesSubtotal;
    private javax.swing.JTextField m_jSalesTaxes;
    private javax.swing.JTextField m_jSalesTotal;
    private javax.swing.JScrollPane m_jScrollSales;
    private javax.swing.JScrollPane m_jScrollTableTicket;
    private javax.swing.JTextField m_jSequence;
    private javax.swing.JTable m_jTicketTable;
    private javax.swing.JTable m_jsalestable;
    // End of variables declaration//GEN-END:variables
}
