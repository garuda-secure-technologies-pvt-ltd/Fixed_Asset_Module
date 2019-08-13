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
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.DataLogicFacilities;
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
public class JPanelCloseDay extends JPanel implements JPanelView, BeanFactoryApp {

    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private DataLogicSales m_dlSales;
    private CloseDayModel m_salesmodel = null;
    private TicketParser m_TTP;
    private String String;
    private DataLogicFacilities dlfac;
    private Date date;

    /** Creates new form JPanelCloseMoney */
    public JPanelCloseDay() {

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
                new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.DOUBLE, Formats.DOUBLE}));
        m_jTicketTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        m_jScrollTableTicket.getVerticalScrollBar().setPreferredSize(new Dimension(25, 25));
        m_jTicketTable.getTableHeader().setReorderingAllowed(false);
        m_jTicketTable.setRowHeight(25);
        m_jTicketTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



    }

    public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.CloseDay");
    }

    public void activate() throws BasicException {
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
        m_jTicketTable.setModel(new DefaultTableModel());

        // LoadData
        m_salesmodel = CloseDayModel.loadInstance(m_App, date);

        // Populate Data
        m_jSequence.setText(m_salesmodel.printSequence());
        m_jMinDate.setText(m_salesmodel.printDateStart());
        m_jMaxDate.setText(m_salesmodel.printDateEnd());
        m_jCloseCash.setEnabled(true);

        m_jTicketTable.setModel(m_salesmodel.getSalesModel());

        TableColumnModel jColumns = m_jTicketTable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(80);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(200);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(80);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(80);
        jColumns.getColumn(4).setResizable(false);


        m_jCloseCash.setEnabled(false);
        if (m_salesmodel.getTotals() > 0) {
            m_jCloseCash.setEnabled(true);
        }
        String user = m_App.getAppUserView().getUser().getName();
        jTextField4.setText(dlfac.getOpeningBalanceToken().toString());//A
        Double A = dlfac.getOpeningBalanceToken();
        jTextField5.setText(dlfac.getOpeningBalance(user).toString());//B
        Double B = dlfac.getOpeningBalance(user);
        jTextField6.setText(dlfac.getTokenIssuedTotal().toString());//C
        Double C = dlfac.getTokenIssuedTotal();
        jTextField7.setText(dlfac.getTokenReturnedTotal().toString());//D
        Double D = dlfac.getTokenReturnedTotal();
        jTextField10.setText(dlfac.getClubCollectionTotal().toString());//E
        Double E = dlfac.getClubCollectionTotal();
        double F = A - C + D;
        jTextField8.setText(Double.toString(F));
        double G = B + C - D + E;
        jTextField9.setText(Double.toString(G));


    }

    private void printSales() {

        String sresource = m_dlSystem.getResourceAsXML("Printer.CloseSale");

        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("sales", m_salesmodel);
           
                int temp=0;//used to indicate whether bar counter or restaurant counter
                boolean flag=m_App.getAppUserView().getUser().hasPermission("Cards Room");
            
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_jCloseCash = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();

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
        m_jScrollTableTicket.setBounds(20, 20, 590, 160);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 130, 740, 190);
        jPanel5.getAccessibleContext().setAccessibleName("Game  Wise");

        jLabel6.setText("Details as per Cards Table Collection");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 330, 330, 14);

        jLabel7.setText("Cash + Token");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 350, 200, 14);

        jLabel8.setText("Opening Balance Token (A)");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, 370, 220, 14);

        jLabel9.setText("Opening Balance Cash(B)");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, 400, 210, 14);

        jLabel10.setText("Tokens Issued(C)");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(30, 430, 210, 14);

        jLabel12.setText("Tokens Returned(D)");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 460, 220, 14);

        jLabel13.setText("Closing Balance Token(A-C+D)");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(30, 520, 230, 14);

        jLabel14.setText("Closing Balance Cash(B+C-D+E)");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 550, 240, 14);
        jPanel1.add(jTextField4);
        jTextField4.setBounds(290, 370, 120, 20);
        jPanel1.add(jTextField5);
        jTextField5.setBounds(290, 400, 120, 20);
        jPanel1.add(jTextField6);
        jTextField6.setBounds(290, 430, 120, 20);
        jPanel1.add(jTextField7);
        jTextField7.setBounds(290, 460, 120, 20);
        jPanel1.add(jTextField8);
        jTextField8.setBounds(290, 520, 120, 20);
        jPanel1.add(jTextField9);
        jTextField9.setBounds(290, 550, 120, 20);

        jButton1.setText("Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(440, 370, 110, 23);

        jLabel15.setText("Club Collection(E)");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(30, 490, 220, 14);

        jButton2.setText("Details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(440, 520, 120, 23);

        jPanel3.setLayout(new java.awt.BorderLayout());

        m_jCloseCash.setText(AppLocal.getIntString("Button.CloseCash")); // NOI18N
        m_jCloseCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCloseCashActionPerformed(evt);
            }
        });
        jPanel2.add(m_jCloseCash);

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 580, 768, 33);
        jPanel1.add(jTextField10);
        jTextField10.setBounds(290, 490, 120, 20);

        add(jPanel1, java.awt.BorderLayout.CENTER);
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
                    AppView appv = LookupUtilityImpl.getInstance(null).getAppView();

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, AppLocal.getIntString("message.qtreasonwarning"), AppLocal.getIntString("message.qtreasontitle"), JOptionPane.WARNING_MESSAGE);
                    } else {
                        int res = JOptionPane.showConfirmDialog(null, AppLocal.getIntString("message.wannaclosesale"), AppLocal.getIntString("message.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
                                JOptionPane.showMessageDialog(null, "Please Close Other Terminals", "Cannot Close Sale", JOptionPane.OK_OPTION);

                            } else {
                                //   try {
                                new StaticSentence(m_App.getSession(), "UPDATE PEOPLE SET CLOSESALE = ? , LOGINTIME=?  WHERE ROLE = ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.NULL, Datas.STRING})).exec(new Object[]{dNow, null, m_App.getAppUserView().getUser().getRole()});


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
                                if (user.hasPermission("Cards Room")) {
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), tid, null, dnow, "D", "Close Day", m_salesmodel.getSequence() + "", m_salesmodel.getTotal(), dnow, true, m_App.getAppUserView().getUser().getName(), m_App.getProperties().getHost(), narration, "3", 0.0, dnow, null, true};
                                    dlfac.insertintoaccjoutnal2(value);


                                }

                                boolean flag = user.hasPermission("StockCheckNotRequired");
                                if (flag == false) {
                                    String location = null;

                                }
                                m_salesmodel.getSequence();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO CLOSEDSALE (ID,SEQUENCE,DATESTART,DATEEND,USER_,AMOUNT,ROLE) VALUES (?,?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), m_salesmodel.getSequence(), m_salesmodel.getDateStart(), dNow, m_App.getAppUserView().getUser().getId(), m_salesmodel.getTotal(), m_App.getAppUserView().getUser().getRole()});
                                //praveen:confirmer changes---start
                                new PreparedSentence(m_App.getSession(), "INSERT INTO RECEIPTS_ARV (ID,DATENEW,ATTRIBUTES,RUSER,PAYMENTREF,DESC_,CLOSECASHSEQ,CONFIRMER) SELECT R.ID,R.DATENEW,R.ATTRIBUTES,R.RUSER,R.PAYMENTREF,R.DESC_,R.CLOSECASHSEQ,R.CONFIRMER FROM RECEIPTS R,BILL B WHERE  R.CLOSECASHSEQ IS NOT NULL AND B.CLOSESALESEQ IS NOT NULL AND B.RECEIPT=R.ID ", null).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO RECEIPTS_ARV (ID,DATENEW,ATTRIBUTES,RUSER,PAYMENTREF,DESC_,CLOSECASHSEQ,CONFIRMER) SELECT R.ID,R.DATENEW,R.ATTRIBUTES,R.RUSER,R.PAYMENTREF,R.DESC_,R.CLOSECASHSEQ,R.CONFIRMER FROM RECEIPTS R  WHERE  R.CLOSECASHSEQ IS NOT NULL AND  R.ID NOT IN (SELECT RECEIPT FROM BILL) ", null).exec();
                                //praveen:confirmer changes---end
                                new PreparedSentence(m_App.getSession(), "INSERT INTO PAYMENTS_ARV (ID,RECEIPT,PAYMENT,TOTAL,PUSER,PTIME,CUSTOMER) SELECT P.ID,P.RECEIPT,P.PAYMENT,P.TOTAL,P.PUSER,P.PTIME,P.CUSTOMER FROM RECEIPTS R,BILL B,PAYMENTS P WHERE  R.CLOSECASHSEQ IS NOT NULL  AND R.ID=P.RECEIPT AND B.CLOSESALESEQ IS NOT NULL AND B.RECEIPT=R.ID ", null).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO PAYMENTS_ARV (ID,RECEIPT,PAYMENT,TOTAL,PUSER,PTIME,CUSTOMER) SELECT P.ID,P.RECEIPT,P.PAYMENT,P.TOTAL,P.PUSER,P.PTIME,P.CUSTOMER FROM RECEIPTS R,PAYMENTS P WHERE  R.CLOSECASHSEQ IS NOT NULL  AND R.ID=P.RECEIPT AND  R.ID NOT IN (SELECT RECEIPT FROM BILL) ", null).exec();

                                new PreparedSentence(m_App.getSession(), "DELETE FROM RECEIPTS R  WHERE R.CLOSECASHSEQ IS NOT NULL AND RUSER LIKE 'Cards Room%'", null).exec();


                                /*  new PreparedSentence(m_App.getSession()
                                , "UPDATE BILL B SET CLOSESALESEQ=? WHERE CLOSESALESEQ IS NULL AND CREATEDDATE < ? AND CREATEDBY IN (SELECT NAME FROM PEOPLE WHERE ROLE=? )"
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{user.getRole()+" : "+m_salesmodel.getSequence(),date,m_App.getAppUserView().getUser().getRole()});
                                 */
                                printSales();
                                //  printSales();
                                //   out.close();
                                // Mostramos el mensaje
                                JOptionPane.showMessageDialog(null, "Press Ok Once the printing is complete", AppLocal.getIntString("message.title"), JOptionPane.INFORMATION_MESSAGE);

                                new StaticSentence(m_App.getSession(), "UPDATE PEOPLE SET CLOSESALE = ? , LOGINTIME=?  WHERE ROLE = ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.NULL, Datas.STRING})).exec(new Object[]{dNow, null, m_App.getAppUserView().getUser().getRole()});


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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JPanelDetail cd = JPanelDetail.getDialog(this, m_salesmodel);
        try {
            boolean flag = cd.showDialog(m_salesmodel.getOtherIncomeTableModel(), true, "Details Of Tokens");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JPanelDetail cd = JPanelDetail.getDialog(this, m_salesmodel);
        try {
            boolean flag = cd.showDialog(m_salesmodel.getChequeDetailModel(), true, "Details Of Tokens");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton m_jCloseCash;
    private javax.swing.JTextField m_jMaxDate;
    private javax.swing.JTextField m_jMinDate;
    private javax.swing.JScrollPane m_jScrollTableTicket;
    private javax.swing.JTextField m_jSequence;
    private javax.swing.JTable m_jTicketTable;
    // End of variables declaration//GEN-END:variables
}
