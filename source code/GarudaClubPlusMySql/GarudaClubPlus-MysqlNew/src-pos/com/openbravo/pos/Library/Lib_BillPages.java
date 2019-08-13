/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.JPaymentSelect;
import com.openbravo.pos.payment.JPaymentSelectReceipt;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillLogicApply;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dev1
 */
public class Lib_BillPages extends javax.swing.JDialog {
    private CustomerInfo customertemp;
    private DataLogicSales dlSales;
    private int remainingBillCount;
    private CustomerInfo customerInfo;
   // private Lib_IssueBooksPanel libissue=new Lib_IssueBooksPanel();
    private TicketParser ttp;
    private AppView m_App;
    private DataLogicSystem dlsystem;
    private boolean resultok = false;
    private BillLogicApply bla;
    private List book_name;
    private DataLogicFacilities dmang;
    private double amt;
    private String retn_obj;
    
    /** Creates new form Lib_BillPages */
    public Lib_BillPages(Frame parent, DataLogicSales dlSales,BillLogicApply bla, CustomerInfo customer) {

        super(parent, true);
        super.setLocation(500,300);
        this.bla = bla;
        this.customertemp = customer;
        this.dlSales = dlSales;
        resultok = bla == null;
    }
    
    public Lib_BillPages(Dialog parent, DataLogicSales dlSales,BillLogicApply bla, CustomerInfo customer) {
        super(parent, true);
        super.setSize(400, 300);
        this.bla = bla;
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
    
    public static Lib_BillPages getDialog(Component parent, DataLogicSales dlSales,BillLogicApply bla, CustomerInfo customer, int remainingBillCount) {

        Window window = getWindow(parent);
        
        Lib_BillPages mybilllogic;

        if (window instanceof Frame) {
            mybilllogic = new Lib_BillPages((Frame) window, dlSales,bla, customer);
        } else {
            mybilllogic = new Lib_BillPages((Dialog) window, dlSales,bla, customer);
        }
        mybilllogic.remainingBillCount = remainingBillCount;
        return mybilllogic;
    }
    
    public void init(CustomerInfo customer) throws BasicException {
        
         m_App = LookupUtilityImpl.getInstance(null).getAppView();
         dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
         dlsystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
         dmang = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        ttp = new TicketParser(m_App.getDeviceTicket(), dlsystem);
        /*paymentdialogreceipt = JPaymentSelectReceipt.getDialog(this);
        paymentdialogreceipt.init(app);
        paymentdialogrefund = JPaymentSelectRefund.getDialog(this);
        paymentdialogrefund.init(app);*/
        customerInfo=customer;
        
        
        initComponents();
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
    
    public boolean showDialog(List book,double fine,String retnobj) throws BasicException {
        // init(binfo);
       jTextField1.setText(String.valueOf(fine));
        book_name=book;
        amt=fine;
        retn_obj=retnobj;
        setVisible(true);
        return resultok;
    }
    
    private void printTicket(String book_name,String receiptno, String cname, List<PaymentInfo> pinfo, Double amount, String skey) {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.LibraryReceipt");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", amount);
                script.put("name", book_name);
                script.put("cname", cname);
                script.put("date", Formats.TIMESTAMP.formatValue(new Date()));
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
            } catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnPay = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BILL");

        jLabel1.setText("Fine Amount is:");

        jTextField1.setEditable(false);

        jBtnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cash.png"))); // NOI18N
        jBtnPay.setText("Pay");
        jBtnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPayActionPerformed(evt);
            }
        });

        jButton5.setText("Debit Bill");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(40, 40, 40)
                        .add(jBtnPay, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(80, 80, 80)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 16, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jBtnPay))
                .add(134, 134, 134))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPayActionPerformed
        try {
            
            Transaction t = new Transaction(m_App.getSession()) {
                
                @Override
                protected Object transact() throws BasicException {
                    
                    List<PaymentInfo> pinfo = new ArrayList<PaymentInfo>();
                   //double amt=Double.parseDouble(jTextField1.getText());
                    if (amt>0.0)  {
                        Date d = new Date();
                        CustomerInfoExt cinfo = new CustomerInfoExt(customerInfo.getId());
                        cinfo.setSearchkey(customerInfo.getSearchkey());
                        cinfo.setName(customerInfo.getName());
                        JPaymentSelect paymentdialog = JPaymentSelectReceipt.getDialog(new JFrame());
                        paymentdialog.init(m_App);
                        boolean flag = paymentdialog.showDialog(amt, cinfo, m_App.getAppUserView().getUser().getName(), true);
                        if (flag == true) {
                            pinfo = paymentdialog.getSelectedPayments();
                            BillInfo ticket = new BillInfo();
                            ticket.setID(UUID.randomUUID().toString());
                            ticket.setPayments(pinfo);
                            ticket.setCustomer(cinfo);
                            ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
                            ticket.setCreatedDate(getdate());
                            ticket.setActiveCash(m_App.getActiveCashIndex());
                            ticket.setFloor("Library");
                            
                            String rnum = dlSales.payaccount(ticket, m_App.getInventoryLocation(), true);
                            if (!(rnum == null
                                    || rnum.equals("false"))) {
                                
                                //new Lib_IssueBooksPanel().updatelib_returnbook();
                                printTicket(book_name.get(0).toString(),rnum, cinfo.getName(), pinfo, amt, cinfo.getSearchkey());
                                Object[] param=new Object[]{new Date(),amt,false,retn_obj};
                            new PreparedSentence(m_App.getSession()
                            , "UPDATE lib_bookissue_retn SET return_date=?,due_charge=?,flag=? WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.DOUBLE,Datas.BOOLEAN,Datas.STRING})).exec(param);
                            JOptionPane.showMessageDialog(null, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }else {
                                if (rnum.equals("false")) {
                                    JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.OK_OPTION);
                                }
                            }
                        }
                        
                        
                    }
                    return null;
                }
            };
            t.execute();
            dispose();
        } catch (BasicException ex) {
            Logger.getLogger(Lib_BillPages.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jBtnPayActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        if (JOptionPane.showConfirmDialog(this, "do u need to create a debit bill", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        
                        List<PaymentInfo> pinfo = new ArrayList<PaymentInfo>();
                        
                        Date d = new Date();
                        
                        CustomerInfoExt cinfo = new CustomerInfoExt(customerInfo.getId());
                        cinfo.setSearchkey(customerInfo.getSearchkey());
                        cinfo.setName(customerInfo.getName());
                        JPaymentSelect paymentdialog = JPaymentSelectReceipt.getDialog(new JFrame());
                        paymentdialog.init(m_App);
                        // boolean flag=paymentdialog.showDialog(Double.parseDouble(amount.getText()), cinfo, m_App.getAppUserView().getUser().getName(),true);
                        //  if(flag==true){
                        PaymentInfoTicket payment = new PaymentInfoTicket(amt, "debt");
                        
                        pinfo.add(payment);
                        BillInfo ticket = new BillInfo();
                        ticket.setID(UUID.randomUUID().toString());
                        ticket.setPayments(pinfo);
                        ticket.setCustomer(cinfo);
                        ticket.setCreatedBy(m_App.getAppUserView().getUser().getName());
                        ticket.setCreatedDate(getdate());
                        ticket.setActiveCash(m_App.getActiveCashIndex());
                        ticket.setFloor("Library");
                        //Guest cat changes-start
                        
                        //Guest cat changes-end
                        String rnum = dlSales.payaccount(ticket, m_App.getInventoryLocation(), true);
                        //  }
                        if (!(rnum == null || rnum.equals("false"))) {
                            
                            String tid = UUID.randomUUID().toString();
                            Date dnow = new Date();
                            String custAcc = null;
                            Object obj = new PreparedSentence(m_App.getSession(), "SELECT ACCOUNT FROM CUSTOMERS WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(customerInfo.getId());
                            if (obj != null) {
                                custAcc = obj.toString();
                            }
                            
                            Object[] value1 = new Object[]{UUID.randomUUID().toString(), tid, dnow, "C", "Library Fine", "LF", amt, dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Library Fine Amount", custAcc, 0.0, dnow, true};
                            dmang.insertintoaccjoutnal1(value1);
                            Object[] value2 = new Object[]{UUID.randomUUID().toString(), tid, customerInfo.getId(), dnow, "D", "Library Fine", "LF", amt, dnow, false, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), "Library Fine Amount", custAcc, amt, true};
                            dmang.insertintoaccjoutnal(value2);
                            //TODO add the accountjournal entry here
                           
                                    printTicket(book_name.get(0).toString(), rnum, cinfo.getName(), pinfo,amt , cinfo.getSearchkey());
                            Object[] param=new Object[]{new Date(),amt,false,retn_obj};
                             new PreparedSentence(m_App.getSession()
                             , "UPDATE lib_bookissue_retn SET return_date=?,due_charge=?,flag=? WHERE ID=?"
                             , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.DOUBLE,Datas.BOOLEAN,Datas.STRING})).exec(param);
                             JOptionPane.showMessageDialog(null, "Returned Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (rnum.equals("false")) {
                                JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Receipt", JOptionPane.OK_OPTION);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.OK_OPTION);
                            }
                        }
                        
                        return null;
                    }
                };
                t.execute();
                dispose();
            } catch (BasicException ex) {
                Logger.getLogger(Lib_BillPages.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnPay;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
