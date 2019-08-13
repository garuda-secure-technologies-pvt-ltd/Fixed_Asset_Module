package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.ReceiptDetail;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.util.Date;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;



public class JPanelDetail extends javax.swing.JDialog {

    private DataLogicSystem m_dlSystem;
    private CloseDayModel m_PaymentsToClose = null;
    private DataLogicFacilities dlfac;
    private TicketParser m_TTP;
    private AppView m_App;
    /** Creates new form ChequeDetail */
    public JPanelDetail(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

     protected JPanelDetail(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public JPanelDetail(Frame parent,CloseDayModel m_Payments){
         super(parent,true);
         m_PaymentsToClose=m_Payments;
         initComponents();
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         jTable1.getTableHeader().setReorderingAllowed(false);
         jTable1.setRowHeight(25);
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     }
     public JPanelDetail(Dialog parent,CloseDayModel m_Payments){
         super(parent,true);
         m_PaymentsToClose=m_Payments;
         initComponents();
         jTable1.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[] {Formats.STRING, Formats.STRING, Formats.CURRENCY, Formats.STRING}));
         jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         jTable1.getTableHeader().setReorderingAllowed(false);
         jTable1.setRowHeight(25);
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     }

      protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

      public static JPanelDetail getDialog(Component parent,CloseDayModel m_Payments) {
        Window window = getWindow(parent);
        JPanelDetail cd;
        if (window instanceof Frame) {
            cd = new JPanelDetail((Frame) window,m_Payments);
        } else {
            cd = new JPanelDetail((Dialog) window,m_Payments);
        }
        return cd;
    }

       public  boolean showDialog(AbstractTableModel tablemodel,boolean status,String text) throws BasicException {
           m_App=LookupUtilityImpl.getInstance(null).getAppView();
           m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
           m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);
           dlfac=(DataLogicFacilities)m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
           jTable1.setModel(tablemodel);
          
           
          TableColumnModel jColumns = jTable1.getColumnModel();
          jColumns.getColumn(0).setPreferredWidth(150);
          jColumns.getColumn(0).setResizable(false);
          this.setVisible(true);
          return true;
       }
       private void printTicketGeneralReceipt(String receiptno,String cname,List<PaymentInfoTicket> pinfo,String desc,List<ReceiptDetail> list,List list2,double totalamt,Date date) throws ScriptException {
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GeneralReceiptsRe");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                desc=StringUtils.encodeXML(desc);
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", totalamt);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                script.put("cname", StringUtils.encodeXML(cname));
                script.put("date", Formats.TIMESTAMP.formatValue(date));
                script.put("desc", StringUtils.encodeXML(desc));
                script.put("receipt", receiptno);
                script.put("detail", list);
                script.put("amt", list2);
                m_TTP.printTicket(script.eval(sresource).toString());
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
       private void printTicketGuestReceipt(String names,String receiptno,String cname,List<PaymentInfoTicket> pinfo,Double amount,String gcat,int gno,String skey,double gactrate,Date date) throws ScriptException {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.ReprintGuestReceipt");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                String gcatname=StringUtils.encodeXML(gcat);
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                script.put("pinfo", pinfo);
                script.put("total", amount);
                script.put("qty", String.valueOf(gno));
                script.put("rate",  Formats.ConvertDoubleToString(gactrate));
                script.put("guestcat", gcatname);
                script.put("cname", cname);
                script.put("date", Formats.TIMESTAMP.formatValue(date));
                script.put("name", names);
                script.put("receipt", receiptno);
                script.put("ckey", skey);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                m_TTP.printTicket(script.eval(sresource).toString());
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
       private void printTicketReceipt(List<ReceiptDetail> list,String receiptno,String cname,List<PaymentInfoTicket> pinfo,Double amount,String memno,Date date) {

        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.ReprintReceipt");
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
                script.put("total", Formats.ConvertDoubleToString(amount));
                script.put("cname", cname);
                script.put("cno", memno);
                script.put("date", Formats.TIMESTAMP.formatValue(date));
                script.put("ticket", list);
                script.put("receipt", receiptno);
                script.put("eoe", StringUtils.encodeXML("E&OE"));
                m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
                e.printStackTrace();
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
                e.printStackTrace();
            }
            catch (Exception e1) {
                new MessageInf(e1).show(this);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("Detail");
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
