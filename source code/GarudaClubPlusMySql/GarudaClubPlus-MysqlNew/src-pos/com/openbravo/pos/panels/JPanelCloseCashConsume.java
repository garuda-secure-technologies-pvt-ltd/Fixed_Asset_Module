package com.openbravo.pos.panels;

import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppLocal;
import java.awt.*;
//import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import com.openbravo.data.loader.SerializerReadClass;
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
import com.openbravo.pos.panels.Cpbill1;
import com.openbravo.pos.sms.EmailMaster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author adrianromer
 */
public class JPanelCloseCashConsume extends JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private DataLogicSales m_dlSales;
    private ConsumableSalesModel m_salesmodel = null;
    private TicketParser m_TTP;
    private String String;
    private DataLogicFacilities dlfac;
    private Date date;
    private List cpBill;
    private List<ProductDetails> cp1;
    private Map<String, ProductDetails> cp;

    /** Creates new form JPanelCloseMoney */
    public JPanelCloseCashConsume() {

        initComponents();
    }

    public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
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
        return AppLocal.getIntString("Menu.CloseCashConsume");
    }

    public void activate() throws BasicException {
        if (m_App.getAppUserView().getUser().getTypeOfUser() == 1) {
            m_jCloseCash.setVisible(false);
        }
        loadData();
    }

    public boolean deactivate() {
        return true;
    }

    private void loadData() throws BasicException {


        date = new Date();
        m_jSequence.setText(null);
        m_jMinDate.setText(null);
        m_jMaxDate.setText(null);
        m_jCloseCash.setEnabled(true);
        jTextField1.setText(null); // AppLocal.getIntString("label.noticketstoclose");//AmountTotal
        jTextField2.setText(null);//Taxtotal
        m_jSalesSubtotal.setText(null);
        m_jSalesTaxes.setText(null);//productwiseTax
        m_jSalesTotal.setText(null);//subtotal

        m_jTicketTable.setModel(new DefaultTableModel());
        m_jsalestable.setModel(new DefaultTableModel());
        m_salesmodel = ConsumableSalesModel.loadInstance(m_App, date);
        m_jSequence.setText(m_salesmodel.printSequence());
        m_jMinDate.setText(m_salesmodel.printDateStart());
        m_jMaxDate.setText(m_salesmodel.printDateEnd());
        m_jCloseCash.setEnabled(true);
        jTextField1.setText(m_salesmodel.printsubbill());
        jTextField2.setText(m_salesmodel.printsubtax());
        jTextField3.setText(m_salesmodel.printTotals());
        m_jSalesSubtotal.setText(m_salesmodel.printpdtamount());
        m_jSalesTaxes.setText(m_salesmodel.printtaxamt());
        m_jSalesTotal.setText(m_salesmodel.printtotalamt());

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
        jColumns.getColumn(1).setPreferredWidth(50);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(80);
        jColumns.getColumn(3).setResizable(false);
        m_jCloseCash.setEnabled(true);
        if (m_salesmodel.getTotals() > 0) {
            m_jCloseCash.setEnabled(true);
        }

    }

    private void printSales(String closesaleseq) {

        String sresource = m_dlSystem.getResourceAsXML("Printer.CloseSaleConsume");
        
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("sales", m_salesmodel);
                int temp = 1;
                script.put("name", "Stores");
                script.put("flag", temp);
                script.put("displayname", "Material Issued ");  
                script.put("closesaleseq", closesaleseq); 
                
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
        java.util.List<ConsumableSalesModel.ProductsLine> l = m_salesmodel.getProductLine();
        for (ConsumableSalesModel.ProductsLine d2 : l) {
            if (d2.isMaintainInventory()) {
                Object[] obj1 = m_dlSales.getlocationFromStkCurrent(d2.printproduct());
                if (obj1 != null) {
                    SentenceExec sent = m_dlSales.getStockDiaryInsertinpdt();
                    Date d = date;
                    Object[] values = new Object[]{UUID.randomUUID().toString(), d, -8, obj1[0].toString(), d2.printproduct(), (d2.getqty()), 0.0, "CONSUMED", d2.getproduct()};
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
        m_jCloseCash = new javax.swing.JButton();

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.datestitle"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(null);

        jLabel11.setText(AppLocal.getIntString("label.sequence")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jPanel4.add(jLabel11);
        jLabel11.setBounds(20, 20, 140, 17);

        m_jSequence.setEditable(false);
        m_jSequence.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jSequence.setName("m_jSequence"); // NOI18N
        jPanel4.add(m_jSequence);
        m_jSequence.setBounds(160, 20, 160, 27);

        jLabel2.setText(AppLocal.getIntString("Label.StartDate")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel4.add(jLabel2);
        jLabel2.setBounds(20, 50, 140, 17);

        m_jMinDate.setEditable(false);
        m_jMinDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jMinDate.setName("m_jMinDate"); // NOI18N
        m_jMinDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jMinDateActionPerformed(evt);
            }
        });
        jPanel4.add(m_jMinDate);
        m_jMinDate.setBounds(160, 50, 160, 27);

        jLabel3.setText(AppLocal.getIntString("etbeans.modul")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel4.add(jLabel3);
        jLabel3.setBounds(20, 80, 140, 17);

        m_jMaxDate.setEditable(false);
        m_jMaxDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jMaxDate.setName("m_jMaxDate"); // NOI18N
        jPanel4.add(m_jMaxDate);
        m_jMaxDate.setBounds(160, 80, 160, 27);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 10, 620, 120);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.paymentstitle"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(null);

        m_jScrollTableTicket.setName("m_jScrollTableTicket"); // NOI18N

        m_jTicketTable.setFocusable(false);
        m_jTicketTable.setIntercellSpacing(new java.awt.Dimension(0, 1));
        m_jTicketTable.setName("m_jTicketTable"); // NOI18N
        m_jTicketTable.setRequestFocusEnabled(false);
        m_jTicketTable.setShowVerticalLines(false);
        m_jScrollTableTicket.setViewportView(m_jTicketTable);

        jPanel5.add(m_jScrollTableTicket);
        m_jScrollTableTicket.setBounds(20, 20, 490, 130);

        jLabel1.setText("Amount Total");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel5.add(jLabel1);
        jLabel1.setBounds(520, 20, 70, 17);

        jTextField1.setEditable(false);
        jTextField1.setName("jTextField1"); // NOI18N
        jPanel5.add(jTextField1);
        jTextField1.setBounds(590, 20, 100, 27);

        jLabel4.setText("Tax Total");
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel5.add(jLabel4);
        jLabel4.setBounds(520, 50, 70, 17);

        jTextField2.setEditable(false);
        jTextField2.setName("jTextField2"); // NOI18N
        jPanel5.add(jTextField2);
        jTextField2.setBounds(590, 50, 100, 27);

        jLabel5.setText("Total");
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel5.add(jLabel5);
        jLabel5.setBounds(520, 80, 60, 17);

        jTextField3.setEditable(false);
        jTextField3.setName("jTextField3"); // NOI18N
        jPanel5.add(jTextField3);
        jTextField3.setBounds(590, 80, 100, 27);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 130, 740, 160);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.salestitle"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(null);

        m_jSalesTotal.setEditable(false);
        m_jSalesTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jSalesTotal.setName("m_jSalesTotal"); // NOI18N
        jPanel6.add(m_jSalesTotal);
        m_jSalesTotal.setBounds(590, 100, 100, 27);

        m_jScrollSales.setName("m_jScrollSales"); // NOI18N

        m_jsalestable.setFocusable(false);
        m_jsalestable.setIntercellSpacing(new java.awt.Dimension(0, 1));
        m_jsalestable.setName("m_jsalestable"); // NOI18N
        m_jsalestable.setRequestFocusEnabled(false);
        m_jsalestable.setShowVerticalLines(false);
        m_jScrollSales.setViewportView(m_jsalestable);

        jPanel6.add(m_jScrollSales);
        m_jScrollSales.setBounds(20, 30, 490, 130);

        m_jSalesTaxes.setEditable(false);
        m_jSalesTaxes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jSalesTaxes.setName("m_jSalesTaxes"); // NOI18N
        jPanel6.add(m_jSalesTaxes);
        m_jSalesTaxes.setBounds(590, 70, 100, 27);

        m_jSalesSubtotal.setEditable(false);
        m_jSalesSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jSalesSubtotal.setName("m_jSalesSubtotal"); // NOI18N
        jPanel6.add(m_jSalesSubtotal);
        m_jSalesSubtotal.setBounds(590, 40, 100, 27);

        jLabel6.setText(AppLocal.getIntString("label.subtotalcash")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel6.add(jLabel6);
        jLabel6.setBounds(520, 40, 90, 17);

        jLabel12.setText(AppLocal.getIntString("label.taxcash")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jPanel6.add(jLabel12);
        jLabel12.setBounds(520, 70, 90, 17);

        jLabel7.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel6.add(jLabel7);
        jLabel7.setBounds(520, 100, 90, 17);

        jPanel1.add(jPanel6);
        jPanel6.setBounds(10, 290, 740, 180);

        m_jCloseCash.setText(AppLocal.getIntString("Close Day"));
        m_jCloseCash.setName("m_jCloseCash"); // NOI18N
        m_jCloseCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCloseCashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(m_jCloseCash)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_jCloseCash)
                .addContainerGap(137, Short.MAX_VALUE))
        );
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
                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, AppLocal.getIntString("message.qtreasonwarning"), AppLocal.getIntString("message.qtreasontitle"), JOptionPane.WARNING_MESSAGE);
                    } else {
                        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("Do you want Close Cash ? "), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (res == JOptionPane.YES_OPTION) {
                            String username = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
                            int count1 = 2;
                            Object[] con = (Object[]) new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM PEOPLE WHERE LOGINTIME IS NOT NULL AND ROLE = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(m_App.getAppUserView().getUser().getRole());
                            if (con != null && con[0] != null) {
                                count1 = Integer.parseInt(con[0].toString());
                            } else {
                                count1 = 2;
                            }
                            if (count1 > 1) {
                                JOptionPane.showMessageDialog(null, "Please Close Other Terminals", "Cannot Close Day", JOptionPane.OK_OPTION);

                            } else {
                                new StaticSentence(m_App.getSession(), "UPDATE PEOPLE SET CLOSESALE = ? , LOGINTIME=?  WHERE ROLE = ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.NULL, Datas.STRING})).exec(new Object[]{dNow, null, m_App.getAppUserView().getUser().getRole()});
                                m_salesmodel.setDateEnd(dNow);
                                AppUser user = m_App.getAppUserView().getUser();
                                user.setCloseSaleTime(dNow);
                                String narration = "close Day from " + m_salesmodel.getDateStart() + " to " + m_salesmodel.getDateEnd();
                                Date dnow = new Date();
                                dnow.setTime(date.getTime());
                                String tid = UUID.randomUUID().toString();
                                Object warehouse = new StaticSentence(m_App.getSession(), "select prcategories from people where id=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(m_App.getAppUserView().getUser().getId());
                                String[] str = null;
                                if (warehouse != null) {
                                    str = String.valueOf(warehouse).split("#");
                                }
                                //looping through internal bill products 
                                
                                java.util.List<ConsumableSalesModel.AccountLines> accline = m_salesmodel.getAccountLines();
                                
                                java.util.List<ConsumableSalesModel.ProductsAccountLine> pdtList = m_salesmodel.getprodutsAccountwiseTotal();
                                Double amt1 = 0.0;
                               // for (ConsumableSalesModel.ProductsAccountLine p : pdtList) {
 ///////// changed by akash     
                                  
                                    //Object[] value = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Day ", m_salesmodel.getSequence() + "", p.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, p.getAccount(), 0.0, dnow, null, true};
                                    //dlfac.insertintoaccjoutnal2(value);
                                   // Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Day ", m_salesmodel.getSequence() + "", p.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, p.getPurAccName(), 0.0, dnow, null, true};
                                   // dlfac.insertintoaccjoutnal2(value1);
                                 
                                    
                                    
                                    
                                //}
                                
                                
                                Boolean CreateCloseCashFlag = false;
                                List<Object[]> ProductForCheckList = new ArrayList<Object[]>();
                                ProductForCheckList = checkForProductAccount();
                                for(int i=0;i<ProductForCheckList.size() ; i++){
                                    Object [] o = ProductForCheckList.get(i);
                                    if(o!=null){
                                        String [] Details = getProductNotHavingAccount(o);
                                        if(Details!=null  && Details.length>1){
                                            CreateCloseCashFlag = false;
                                            String Productname=  Details[0].toString();
                                            String DepartmentName = Details[1].toString();
                                            JOptionPane.showMessageDialog(null, "Account for Product  "+Productname+" and department "+DepartmentName+" is not linked. Please link the account first", AppLocal.getIntString("message.title"), JOptionPane.INFORMATION_MESSAGE);
                                            
                                            break;
                                        }
                                        else{
                                            CreateCloseCashFlag = true;
                                        }
                                        
                                        
                                    }
                                    
                                }
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                if(CreateCloseCashFlag){
                                
                                Map<String,Double> DebitMap = new HashMap<String,Double>();
                                Map<String,Double> CreditMap = new HashMap<String,Double>();
                                for(int i=0;i<pdtList.size();i++){
                                    String Accountid=pdtList.get(i).getPurAccName();
                                    Double Amount = pdtList.get(i).getAmount();
                                    if(DebitMap.get(Accountid)!=null){
                                        Double tempa = DebitMap.get(Accountid);
                                        Amount = Amount+tempa;
                                        DebitMap.put(Accountid, Amount);
                                    }
                                    else{
                                        DebitMap.put(Accountid, Amount);
                                    }
                                }
                                
                                // for credit map
                                for(int i=0;i<pdtList.size();i++){
                                    String Accountid=pdtList.get(i).getAccount();
                                    Double Amount = pdtList.get(i).getAmount();
                                    if(CreditMap.get(Accountid)!=null){
                                        Double tempa = CreditMap.get(Accountid);
                                        Amount = Amount+tempa;
                                        CreditMap.put(Accountid, Amount);
                                    }
                                    else{
                                        CreditMap.put(Accountid, Amount);
                                    }
                                }
                                
                                
                                
                                Iterator Debitentries = DebitMap.entrySet().iterator();
                                while (Debitentries.hasNext()) {
                                  Map.Entry thisEntry = (Map.Entry) Debitentries.next();
                                  Object key = thisEntry.getKey();
                                  Object value = thisEntry.getValue();
                                  String Accountid = key.toString();
                                  Double Amount = Double.parseDouble(value.toString());
                                  
                                  //commented by pratima
                                 // Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Day ", m_salesmodel.getSequence() + "", Amount , dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, Accountid, 0.0, dnow, null, true};
                                  Object[] dvalue = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Day ", m_salesmodel.getSequence() + "", Amount , dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, Accountid, 0.0, dnow, null, true};
                                  dlfac.insertintoaccjoutnal2(dvalue);
                                 }
                                
                                
                                // iterator for credit entries 
                                
                                
                                
                                
                                
                                Iterator Creditentries = CreditMap.entrySet().iterator();
                                while (Creditentries.hasNext()) {
                                  Map.Entry thisEntry = (Map.Entry) Creditentries.next();
                                  Object key = thisEntry.getKey();
                                  Object value = thisEntry.getValue();
                                  String Accountid = key.toString();
                                  Double Amount = Double.parseDouble(value.toString());
                                  
                                  //commented by pratima
                                  //Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Day ", m_salesmodel.getSequence() + "", Amount, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, Accountid, 0.0, dnow, null, true};
                                   Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Day ", m_salesmodel.getSequence() + "", Amount, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, Accountid, 0.0, dnow, null, true};
                                  dlfac.insertintoaccjoutnal2(value1);
                                 }
                                


                                Double amountTotal = 0.0;
                                Double amountTax = 0.0;
                                
                                
                           //     for (ConsumableSalesModel.SalesLine pacc : m_salesmodel.getM_billinfoBilling()) {
                           //         if (pacc.getPaymentType().equals("cash")) {
                            //            amountTotal += pacc.getTotal();
                             //           amountTax += pacc.gettaxamount();
                             //       } else if (pacc.getPaymentType().equals("debt")) {
                              //          Object obj = new StaticSentence(m_App.getSession(), "select account from customers where id=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(pacc.getcustomer());
                              //          if (obj != null) {
                               //             String acc = obj.toString();
                                          //  Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, pacc.getcustomer(), dnow, "D", "Close Sale", pacc.getbillno(), pacc.getTotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, acc, pacc.getTotal(), null, null, true};
                                          //  dlfac.insertintoaccjoutnal2(value1);
                                //        }
                               //     }
                              //  }
                                
                                
                                
                                if (amountTotal > 0.0) {
                                   // Object[] value = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Sale", m_salesmodel.getSequence() + "", amountTotal, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, m_App.getAppUserView().getUser().getcashaccount(), 0.0, dnow, null, true};
                                  // dlfac.insertintoaccjoutnal2(value);
                                }

                            
                               
                                
                                java.util.List<ConsumableSalesModel.ProductsAccountLine> pdtList1 = m_salesmodel.getProductsaccountbilling();
                                for (ConsumableSalesModel.ProductsAccountLine p : pdtList1) {
                                  //  Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", p.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, p.getPurAccName(), 0.0, dnow, null, true};
                                  //  dlfac.insertintoaccjoutnal2(value1);
                                }

                                //todo tax line accountjournal entry
                            //    Double tamt = m_salesmodel.getTotaltax();
                             //   if (tamt > 0.0) {
                             //       for (ConsumableSalesModel.TaxLine tax : m_salesmodel.getTaxLines()) {
                            //            Object[] cvalue = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "C", "Close Sale", m_salesmodel.getSequence() + "", tax.getAmount(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, tax.getAccountId(), 0.0, dnow, null, true};
                            //            dlfac.insertintoaccjoutnal2(cvalue);
                            //        }
                           //    }


                                String closesaleId = UUID.randomUUID().toString();//praveen:closeedsale id and reference to productwiseclosesale
                                Date productwiseSaleRefDate = new Date();
                                String location = null;
                                java.util.List<ConsumableSalesModel.ProductsLine> pdtlist = m_salesmodel.getProductLine();
                                Map<String, String> catwid = new HashMap<String, String>();
                                for (ConsumableSalesModel.ProductsLine pdtinfo : pdtlist) {
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
                                        Object[] pdtwiseSale = new Object[]{UUID.randomUUID().toString(), pdtinfo.getpdtid(), pdtinfo.getqty(), pdtinfo.getAmount(), productwiseSaleRefDate, closesaleId};
                                        new PreparedSentence(m_App.getSession(), "INSERT INTO PRODUCTWISECLOSESALE (ID,PRODUCT,QTY,RATE,CSDATE,CLOSESALEREF) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT, Datas.DOUBLE, Datas.TIMESTAMP, Datas.STRING}), null).exec(pdtwiseSale);
                                    }
                                }
                                m_salesmodel.getSequence();
                               
                                 int i = new PreparedSentence(m_App.getSession(), "UPDATE CPBILL B SET CLOSESALESEQ=? WHERE CLOSESALESEQ IS NULL AND CREATEDDATE <= ? AND CREATEDBY IN (SELECT ID FROM PEOPLE WHERE ROLE=? )", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{user.getRole() + " : " + m_salesmodel.getSequence(), date, m_App.getAppUserView().getUser().getRole()});
                                System.out.println("Update query : "+i);
                                
                                
                                 new PreparedSentence(m_App.getSession(), "INSERT INTO CLOSEDSALECONSUME (ID,SEQUENCE,DATESTART,DATEEND,USER_,AMOUNT,ROLE) VALUES (?,?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{closesaleId, m_salesmodel.getSequence(), m_salesmodel.getDateStart(), dNow, m_App.getAppUserView().getUser().getId(), m_salesmodel.getTotal(), m_App.getAppUserView().getUser().getRole()});
                                
                                
                               
                                // new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILL_ARV(ID,MEMID,DEPTID,AMOUNT,TAXAMOUNT,PAYMENTTYPE,CREATEDBY,USERROLE,CREATEDDATE,BILLTYPE,CLOSESALESEQ) SELECT B.ID,B.MEMID,B.DEPTID,B.AMOUNT,B.TAXAMOUNT,B.PAYMENTTYPE,B.CREATEDBY,B.USERROLE,B.CREATEDDATE,B.BILLTYPE,B.CLOSESALESEQ FROM CPBILL B,CPBILLITEM R WHERE   B.CLOSESALESEQ IS NOT NULL AND R.BILLID=B.ID AND B.USERROLE=? ", null).exec(m_App.getAppUserView().getUser().getRole());
                               
                                 new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILL_ARV(ID,MEMID,DEPTID,AMOUNT,TAXAMOUNT,PAYMENTTYPE,CREATEDBY,USERROLE,CREATEDDATE,BILLTYPE,CLOSESALESEQ,RECEIVER) SELECT B.ID,B.MEMID,B.DEPTID,B.AMOUNT,B.TAXAMOUNT,B.PAYMENTTYPE,B.CREATEDBY,B.USERROLE,B.CREATEDDATE,B.BILLTYPE,B.CLOSESALESEQ ,B.RECEIVER  FROM CPBILL B WHERE   B.CLOSESALESEQ IS NOT NULL  ", null).exec();
                                
                              //  new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILLITEM_ARV(ID,BILLID,PRODUCTID,QTY,RATE,TAXVALUE) SELECT BI.ID,BI.BILLID,BI.PRODUCTID,BI.QTY,BI.RATE,BI.TAXVALUE FROM CPBILLITEM BI,CPBILL B WHERE  B.CLOSESALESEQ IS NOT NULL  AND B.ID=BI.BILLID AND B.USERROLE=? ", null).exec(m_App.getAppUserView().getUser().getRole());
                                new PreparedSentence(m_App.getSession(), "INSERT INTO CPBILLITEM_ARV(ID,BILLID,PRODUCTID,QTY,RATE,TAXVALUE,DEPTID) SELECT BI.ID,BI.BILLID,BI.PRODUCTID,BI.QTY,BI.RATE,BI.TAXVALUE,BI.DEPTID FROM CPBILLITEM BI,CPBILL B WHERE  B.CLOSESALESEQ IS NOT NULL  AND B.ID=BI.BILLID  ", null).exec();
                                new PreparedSentence(m_App.getSession(), " DELETE FROM CPBILLITEM  WHERE  BILLID in (SELECT ID FROM CPBILL B WHERE  B.CLOSESALESEQ IS NOT NULL  ) ", null).exec();
                               
                                new PreparedSentence(m_App.getSession(), " DELETE FROM CPBILL  WHERE CLOSESALESEQ IS NOT NULL ", null).exec();
                                new StaticSentence(m_App.getSession(), "UPDATE PEOPLE SET CLOSESALE = ? , OPENSALE=? , LOGINTIME=?  WHERE ROLE = ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.NULL, Datas.STRING})).exec(new Object[]{dNow,new Date() ,  null, m_App.getAppUserView().getUser().getRole()});
                                
                                
                                
                                String v = m_salesmodel.getSequence()+"";
                                printSales(v);
                                printSales(v);
                                JOptionPane.showMessageDialog(null, "Press Ok Once the Printing is Done", AppLocal.getIntString("message.title"), JOptionPane.INFORMATION_MESSAGE);
                               
                                JPrincipalApp.m_approot.closeAppView();
                                
                                }
                                
                                
                            }
                        }
                    }
                    return null;
                }
            };
            t.execute();
        } catch (Exception e) {
             e.printStackTrace();
                new MessageInf(e).show(new JFrame());
                  Logger.getLogger(JPanelCloseCashConsume.class.getName()).log(Level.SEVERE, null, e); 
        }

    }//GEN-LAST:event_m_jCloseCashActionPerformed

    private void m_jMinDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jMinDateActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_m_jMinDateActionPerformed
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





    

     public List checkForProductAccount(){
       List<Object[]> Temp = new ArrayList<Object[]>();
        
        try {
                      Temp =  new StaticSentence(m_App.getSession(),  " select productid,deptid from cpbillitem   ", 
                             SerializerWriteString.INSTANCE,
                              new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.STRING})  ).list();
                      
                    } catch (BasicException ex) {
                        Logger.getLogger(JPanelCloseCashConsume.class.getName()).log(Level.SEVERE, null, ex);
              }
        return Temp;              
          
    }
     
    public String [] getProductNotHavingAccount(Object []o){
        String [] Details  = new String[2];
        
        if(o!=null){
            String Proid = o[0].toString();
            String DeptID = o[1].toString();
            try{
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT id FROM consprdacc where proid = ? and deptid=? and active=1", 
                        new SerializerWriteBasic(new Datas[]{Datas.STRING , Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING }) ).find(new Object[]{Proid,DeptID});

                if(obj2!=null && obj2[0]!=null){
                    return null;
                }
                else{
                    Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT name FROM products  where id = ?  ", 
                        SerializerWriteString.INSTANCE,   new SerializerReadBasic(new Datas[]{Datas.STRING })).find( Proid );
                    Object[] obj4 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT name FROM department  where id = ?  ", 
                        SerializerWriteString.INSTANCE ,   new SerializerReadBasic(new Datas[]{Datas.STRING }) ).find(DeptID);
                        Details[0] = obj3[0].toString();
                        Details[1] = obj4[0].toString();
                    
                    
                    
                    return Details;
                }
                
            }
            catch(BasicException e){
                e.printStackTrace();
            }
        }
        return Details;
    } 
     


}
