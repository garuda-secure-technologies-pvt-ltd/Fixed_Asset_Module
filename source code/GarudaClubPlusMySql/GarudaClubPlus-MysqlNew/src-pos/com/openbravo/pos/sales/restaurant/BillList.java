/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BillList.java
 *
 * Created on Dec 12, 2008, 11:47:36 AM
 */
package com.openbravo.pos.sales.restaurant;

import com.mysql.jdbc.StringUtils;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.BillListTableModel;
import com.openbravo.pos.sales.BillLogic;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class BillList extends javax.swing.JDialog {

    private BillListTableModel billtablemodel;
    private CounterTotalsTableModel counterTotalmodel;
    private List<BillInfo> blist;
    private BillLogic blogic;
    private DataLogicSales dlSales;
    private boolean resultok = false;
    private CustomerInfo customer;
    private TicketParser m_TTP;
    private DataLogicSystem dlSystem;
    private DataLogicFacilities dlfac;
//Added by ganesh
    public static String gg;
    private double taxamt;
    public static double taxamt1;
//    private List<BillInfo> glist;
//    public static BillInfo g;
    /** Creates new form BillList */
    public BillList(java.awt.Frame parent, DataLogicSales dlSales, BillLogic blogic, CustomerInfo customer) {
        super(parent, true);
        this.dlSales = dlSales;
        this.blogic = blogic;
        this.customer = customer;
    }

    public BillList(java.awt.Dialog parent, DataLogicSales dlSales, BillLogic blogic, CustomerInfo customer) {
        super(parent, true);
        this.dlSales = dlSales;
        this.blogic = blogic;
        this.customer = customer;

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

    public static BillList getDialog(Component parent, DataLogicSales dlSales, BillLogic blogic, CustomerInfo customer) {

        Window window = getWindow(parent);

        BillList mybilllogic;

        if (window instanceof Frame) {
            mybilllogic = new BillList((Frame) window, dlSales, blogic, customer);
        } else {
            mybilllogic = new BillList((Dialog) window, dlSales, blogic, customer);
        }

        return mybilllogic;
    }

    private void loadUnpaidBills(List<BillInfo> bl) {
        blist = new ArrayList<BillInfo>();
        double amount = 0.0;
        // for (BillInfo b : bl) {
        //    amount+=b.getAmount();
        // }
        //  ptotal.setText(String.valueOf(For))
        blist.addAll(bl);
    }

    private void refreshModel() throws BasicException {
        loadUnpaidBills(blogic.getBillList1());
        ((BillListTableModel) jTable1.getModel()).refresh(blist);
        List<CounterTotals> list = blogic.getPendingBillTotal();
        if (list == null || list.size() <= 0) {
            list = new ArrayList<CounterTotals>();
        }
        // counterTotalmodel=new CounterTotalsTableModel(list);
        ((CounterTotalsTableModel) jTable2.getModel()).refresh(list);
    }

    public void init() throws BasicException {

        loadUnpaidBills(blogic.getBillList1());

        initComponents();
        jButton3.setVisible(false);
        AppView app = LookupUtilityImpl.getInstance(null).getAppView();
        dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
        List<CounterTotals> list = blogic.getPendingBillTotal();
        if (list == null || list.size() <= 0) {
            list = new ArrayList<CounterTotals>();
        }
        counterTotalmodel = new CounterTotalsTableModel(list);
        jTable2.setModel(counterTotalmodel);
        billtablemodel = new BillListTableModel(blist);
        jTable1.setModel(billtablemodel);

        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(2).setPreferredWidth(60);
        columnModel.getColumn(2).setMaxWidth(60);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(4).setMaxWidth(100);
        columnModel.getColumn(5).setPreferredWidth(120);
        columnModel.getColumn(5).setMaxWidth(120);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(6).setMaxWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(6).setMaxWidth(80);


    }

    public boolean showDialog() {
        try {
            init();
            setVisible(true);
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return resultok;
    }

    public void displayBill() {
        try {
            int i = jTable1.getSelectedRow();
            if (i >= 0 && i < blist.size()) {
                BillInfo b = blist.get(i);
                gg=b.getCreatedBy();
                //System.out.println("gg::::::::"+gg);
                //g=glist.get(i);
                AppView app = LookupUtilityImpl.getInstance(null).getAppView();
                //praveen:checking bill is paid or not 
                Object obj1 = new StaticSentence(app.getSession(), "SELECT TAXTOTAL FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(b.getID());
                taxamt=Double.parseDouble(obj1.toString());
                taxamt1=taxamt;
//                BillInfo bbb=new BillInfo();
//                bbb.setTaxamtg(taxamt);
                //System.out.println("ttttttttttaxamt:::  "+taxamt);
                Object obj = new StaticSentence(app.getSession(), "SELECT RECEIPT FROM BILL WHERE ID=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(b.getID());
                
                if (obj != null) {
                    JOptionPane.showMessageDialog(this, "Already billed.Press OK to refresh the list", "warning", JOptionPane.OK_OPTION);
                    refreshModel();
                } else {
                    Billpage billpage = Billpage.getDialog(this, dlSales, null, b.getCustomer(),0);
                    billpage.init(b);
                    billpage.showDialog();
                    refreshModel();
                }
            }
        } catch (BasicException e) {
            e.printStackTrace();
            new MessageInf(e).show(this);
        }
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
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            try {
                WaiterInfo w = LookupUtilityImpl.getInstance(null).getWaiterMap().get(ticket.getWaiter());
                waitername = w.getName();
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM PLACES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ticket.getPlace());

                if (obj1 == null) {
                    table = "";
                } else {
                    table = obj1[0].toString();
                }

                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,ROLE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING})).find(ticket.getCreatedBy());

                String warehouse = null;
                if (obj2 != null && obj2[3] != null) {
                    String[] wArr = obj2[3].toString().split("#");
                    warehouse = wArr[0];
                }
                AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString(), warehouse);
                appuser.fillPermissions(dlSystem);
                boolean flag1 = appuser.hasPermission("bar counter");
                boolean flag = m_App.getAppUserView().getUser().hasPermission("bar counter");
                boolean crconf = false;
                if (ticketext.equals("cerditconf")) {
                    crconf = true;
                }
                String[] str = null;
                if (obj2[3] != null) {
                    str = obj2[3].toString().split("#");
                }
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT RDISPLAYNAME FROM LOCATIONS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(str[0].toString());
                String name = null;
                if (obj3 != null && obj3[0] != null) {
                    name = obj3[0].toString();
                }
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("ticket", ticket);
                script.put("place", table);
                script.put("flag", flag);
                script.put("flag1", flag1);
                script.put("waiter", waitername);
                script.put("date", receiptdate);
                script.put("sign", sign);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                script.put("crconf", crconf);
                script.put("displayName", name);
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent)c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                    if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                        jc.setBackground(Color.cyan);
                    }
                    else {
                        jc.setBackground(Color.white);
                    }
                    if(isCellSelected(rowIndex, vColIndex))
                    jc.setBackground(Color.lightGray);
                }
                return c;
            }};
            jButton1 = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();
            jScrollPane2 = new javax.swing.JScrollPane();
            jTable2 = new javax.swing.JTable(){
                @Override
                public Component prepareRenderer(TableCellRenderer renderer,
                    int rowIndex, int vColIndex) {
                    Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                    if (c instanceof JComponent) {
                        JComponent jc = (JComponent)c;
                        jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                        if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                            jc.setBackground(Color.cyan);
                        }
                        else {
                            jc.setBackground(Color.white);
                        }
                        if(isCellSelected(rowIndex, vColIndex))
                        jc.setBackground(Color.lightGray);
                    }
                    return c;
                }};
                jButton3 = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Pending Bill List");

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                    },
                    new String [] {
                        "Sl No", "Member Name", "Member ID", "Bill No", "Date"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jTable1MouseClicked(evt);
                    }
                });
                jScrollPane1.setViewportView(jTable1);

                jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/apply.png"))); // NOI18N
                jButton1.setText("Select");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });

                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
                jButton2.setText("Cancel");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                    }
                });

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {},
                        {},
                        {},
                        {}
                    },
                    new String [] {

                    }
                ));
                jTable2.getTableHeader().setReorderingAllowed(false);
                jScrollPane2.setViewportView(jTable2);

                jButton3.setText("Prt Pndg Bills");
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        displayBill();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() > 1) {
            displayBill();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
