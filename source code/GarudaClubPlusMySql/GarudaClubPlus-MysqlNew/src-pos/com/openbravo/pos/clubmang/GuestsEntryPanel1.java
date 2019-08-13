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
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
//import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
//import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
//import javax.swing.ListModel;
import javax.swing.table.TableCellRenderer;
//import sun.util.calendar.Gregorian;

/**
 *
 * @author swathi
 */
public class GuestsEntryPanel1 extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form GuestsEntryPanel */
    private DataLogicCustomers dlCustomers;
    private CustomerInfo customerInfo;
    private AppView m_App;
    private DataLogicFacilities dmang;
   // private ComboBoxValModel model;
     private ComboBoxValModel gcatmodel;
     private List gnames=new ArrayList();;
     private GuestListModel gmodel;
     private GuestlistTableModel gtablemodel;
     private DataLogicSales m_dlSales;
     private DataLogicSystem dlsystem;
     private TicketParser ttp;
    public GuestsEntryPanel1() {

        initComponents();
    }
     public void init(AppView app) throws BeanFactoryException {
         m_App=app;
         dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
         dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         dlsystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
         ttp = new TicketParser(app.getDeviceTicket(), dlsystem);
        


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
        loadData();
    }
    private void loadData() throws BasicException {
        
        try{
        memno.setText(null);
        mname.setText(null);
        gcatmodel=new ComboBoxValModel(dmang.getGuestCategory());
        jComboBox2.setModel(gcatmodel);
        }catch(Exception e){
            e.printStackTrace();
        }
       reset();
       java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               memno.requestFocus();
            }
        });
    }
   private void  reset(){
       // model=new ComboBoxValModel();
      
       // guestcat.setText(null);
        guestno.setText(null);
        jList1.setModel(new GuestListModel(new ArrayList()));
        gnames=new ArrayList();
         jComboBox2.setSelectedIndex(-1);
        maxguestlimit.setText(null);
        amount.setText(null);
   }
      public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
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
        public void remove(int i){
             guest.remove(i);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
       private Date getdate(){
          Date dnow=new Date();
          Calendar cal=GregorianCalendar.getInstance();
          cal.setTimeInMillis(dnow.getTime());
          cal.set(Calendar.HOUR_OF_DAY, 00);
          cal.set(Calendar.SECOND, 00);
          cal.set(Calendar.MINUTE, 00);
          cal.set(Calendar.MILLISECOND, 00);
          dnow.setTime(cal.getTimeInMillis());
          return dnow;
   }
       private Date getNextdate(Date d){
           Calendar cal=GregorianCalendar.getInstance();
           cal.setTimeInMillis(d.getTime());
           cal.add(Calendar.DATE, 1);
           d.setTime(cal.getTimeInMillis());
           return d;
       }
        private void printTicket(List names,String receiptno,String cname,List<PaymentInfo> pinfo,Double amount,GuestCategory gcat,int gno,String skey) throws ScriptException {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GuestReceipt");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
               // taxeslogic.calculateTaxes(ticket);
                //ticket.resetPayments();
               // pinfo.get(1).getChequeDetails().getChequeno()
               String gcatname=StringUtils.encodeXML(gcat.getname());
               int i=0;
               for(Object line:names){
                   names.set(i,StringUtils.encodeXML(line.toString()));
                   i++;
               }
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", amount);
                script.put("qty", String.valueOf(gno));
                script.put("rate", gcat.getrate());
                script.put("guestcat", gcatname);
                script.put("cname", cname);
                script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
                script.put("name", names);
                script.put("receipt", receiptno);
                script.put("ckey", skey);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                ttp.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            }
            catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jParamsDatesInterval1 = new com.openbravo.pos.reports.JParamsDatesInterval();
        jButton4 = new javax.swing.JButton();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setText("Mem No");

        memno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memnoKeyPressed(evt);
            }
        });

        jLabel2.setText("Mem Name");

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
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Number of Guest");

        guestno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                guestnoKeyReleased(evt);
            }
        });

        jLabel5.setText("Guest Name");

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

        jLabel8.setText("Max limit");

        maxguestlimit.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(709, 709, 709))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(memno, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox2, 0, 159, Short.MAX_VALUE)
                                    .addComponent(guestno, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gname, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))))))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(maxguestlimit, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(amount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(memno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(maxguestlimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(gname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(guestno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7)
                                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(183, 183, 183))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Creat New", jPanel1);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jParamsDatesInterval1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jParamsDatesInterval1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
               
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void memnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memnoKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){

            try{
               Object[] obj= dmang.getMamberbySkey(memno.getText().toUpperCase());
              

            if(obj==null)
            {
                JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                   reset();
                customerInfo=new CustomerInfo(obj[0].toString());
                customerInfo.setName(obj[1].toString());
                customerInfo.setSearchkey(memno.getText().toUpperCase());
              //  customerInfo.setMemType(obj[2]);
               // customerInfo.setId(obj[0].toString());
               mname.setText(obj[1].toString());
                
            }
            }
          catch(Exception e)
          {
              e.printStackTrace();
          }
        }
    }//GEN-LAST:event_memnoKeyPressed

    private void PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayActionPerformed
        // TODO add your handling code here:
        try{
            String name="";
            List<PaymentInfo> pinfo=new ArrayList<PaymentInfo>();
            List lnames=new ArrayList();
      if(jComboBox2.getSelectedIndex()!=-1 && customerInfo!= null && guestno.getText().length()>0){
       if(Integer.parseInt(guestno.getText())==gnames.size()){
        Date d=new Date();
        GuestCategory gcat=(GuestCategory)jComboBox2.getSelectedItem();
        
        int gno=Integer.parseInt(guestno.getText());
        //*/
        if( gno==gmodel.getSize()){
      //  Object
            CustomerInfoExt cinfo=new CustomerInfoExt(customerInfo.getId());
            cinfo.setSearchkey(customerInfo.getSearchkey());
            cinfo.setName(customerInfo.getName());
            JPaymentSelect paymentdialog =JPaymentSelectReceipt.getDialog(this);
            paymentdialog.init(m_App);
            boolean flag=paymentdialog.showDialog(Double.parseDouble(amount.getText()), cinfo, m_App.getAppUserView().getUser().getName(),true);
            if(flag==true){
             pinfo=paymentdialog.getSelectedPayments();
             BillInfo ticket=new BillInfo();
             ticket.setID(UUID.randomUUID().toString());
             ticket.setPayments(pinfo);
             ticket.setCustomer(cinfo);
             ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
             ticket.setCreatedDate(getdate());
             ticket.setActiveCash(m_App.getActiveCashIndex());
             ticket.setFloor("Guest Entry");
             ticket.setPlace("Guest Entry Fees");
             String rnum=m_dlSales.payaccount(ticket, m_App.getInventoryLocation(),true);
       //  }
             if(!(rnum==null || rnum.equals("false"))){
            String nametemp=null;
            int i=0;
           for( i=0;i < gmodel.getSize();i++){
               String tempname=gmodel.getElementAt(i).toString();
               if(name.equals(""))
                   name=tempname;
               else
                   name +=":" + tempname;
               if(nametemp==null)
                   nametemp=tempname;
               else{
                   nametemp=nametemp+" , "+tempname;
                 if((i+1)%3==0){
                  lnames.add(nametemp);
                  nametemp=null;
                 }
               }

           }
            if(i%3!=0){
                lnames.add(nametemp);
            }
        
          Double amt=gcat.getrate() * gno;
        // try{
             Object[] value=new Object[]{UUID.randomUUID().toString(),customerInfo.getId(),d,gcat.getid(),amt,gno,name,rnum,m_App.getAppUserView().getUser().getName()};
            new PreparedSentence(m_App.getSession()
                  , "INSERT INTO GUESTLOG(ID,MEMNO,DATE,GUESTCAT,AMOUNT,NUM,NAMES,RECEIPTNO,CREATEDBY) VALUES (?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.DOUBLE,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING})
                  ).exec(value);
            printTicket(lnames, rnum,cinfo.getName(), pinfo, amt,gcat,gno,cinfo.getSearchkey());
            loadData();
          //}
          }else{
             if(rnum.equals("false")){
                  JOptionPane.showMessageDialog(this, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
             }else
                 JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.OK_OPTION);
          }
            }
        }else{
             JOptionPane.showMessageDialog(this,"The Guest limit exceeds for the category","Guest Limit Exceeds" , JOptionPane.WARNING_MESSAGE);

        }
       }else{
           JOptionPane.showMessageDialog(this,"The Guest list does not match with guest number",null , JOptionPane.WARNING_MESSAGE);
       }
        }else{
           //exceeds
           JOptionPane.showMessageDialog(this,"Please Fill the form completely",null , JOptionPane.WARNING_MESSAGE);
        }
        }catch(Exception e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(this,"Error",null , JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_PayActionPerformed

    private void gnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gnameKeyPressed
        // TODO add your handling code here:
       if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
           if(gname.getText().length()>0 ){

            if(gnames.size()<Integer.parseInt(guestno.getText())){
              gnames.add(gname.getText());
              gmodel=new GuestListModel(gnames);
              jList1.setModel(gmodel);
              gname.setText(null);
            }else{
              JOptionPane.showMessageDialog(this, "Guest list exceeds the number of guest specified", "Exceeds the specified number", JOptionPane.OK_OPTION);
              gname.setText(null);
            }
           }

       }
    }//GEN-LAST:event_gnameKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row=jList1.getSelectedIndex();
        if(row>=0){
            gnames.remove(row);
       gmodel=new GuestListModel(gnames);

        jList1.setModel(gmodel);


        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
       javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
       int tabno=tabpane.getSelectedIndex();
       if(tabno==1){
          Date sdate=getdate();
          Date d=new Date();
          d.setTime(sdate.getTime());
          Date edate=getNextdate(d);
          jParamsDatesInterval1.setStartDate(sdate);
          jParamsDatesInterval1.setEndDate(edate);

       }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
        Date sdate=(Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getStartDate());
        Date edate=(Date) Formats.TIMESTAMP.parseValue(jParamsDatesInterval1.getEndDate());
        gtablemodel=GuestlistTableModel.loadInstance(m_App, sdate, edate);
        jTable1.setModel(gtablemodel.getTableModel());
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guestnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guestnoKeyReleased
        // TODO add your handling code here:
        try{
            if(guestno.getText().length()>0){

              int num=Integer.valueOf(guestno.getText());
              GuestCategory gcat=(GuestCategory)jComboBox2.getSelectedItem();
              if(num+guestnum >gcat.getmaxguest()){
                 JOptionPane.showMessageDialog(this, "The guest number exceeds the maximum", null, JOptionPane.OK_OPTION);
                   guestno.setText(null);
              }else
              
              amount.setText(String.valueOf(num * gcat.getrate()));
            }else{
               amount.setText("0.0");
            }
        }catch(NumberFormatException ex){
            guestno.setFocusable(false);
            JOptionPane.showMessageDialog(this, "Enter a valid number", "Wrong number Format", JOptionPane.OK_OPTION);
            guestno.setText(null);
            guestno.setFocusable(true);
        }
        catch(Exception e){
           e.printStackTrace();
        }
    }//GEN-LAST:event_guestnoKeyReleased
   private int guestnum=0;
    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        guestnum=0;
        if(jComboBox2.getSelectedIndex()!=-1){
             int pregno=0;

        GuestCategory gcat=(GuestCategory)jComboBox2.getSelectedItem();
        try{
        List obj=new StaticSentence(m_App.getSession()
                , "SELECT NUM FROM GUESTLOG WHERE MEMNO=? AND GUESTCAT=? AND DATE=?"
                , new  SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP})
                ,SerializerReadInteger.INSTANCE).list(new Object[]{customerInfo.getId(),gcat.getid(),getdate()});
          if(obj!=null){
              for(int i=0;i<obj.size();i++){
                guestnum+=Integer.valueOf(obj.get(i).toString());
              }
           // guestnum=Integer.parseInt(obj[0].toString());
          }
        }
        catch(Exception e){
          e.printStackTrace();
        }
        maxguestlimit.setText(String.valueOf(gcat.getmaxguest()));
        }else{
           maxguestlimit.setText("0");
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Pay;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField gname;
    private javax.swing.JTextField guestno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.openbravo.pos.reports.JParamsDatesInterval jParamsDatesInterval1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField maxguestlimit;
    private javax.swing.JTextField memno;
    private javax.swing.JTextField mname;
    // End of variables declaration//GEN-END:variables

}
