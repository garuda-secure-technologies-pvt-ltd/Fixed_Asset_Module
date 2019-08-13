/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChequeDetail.java
 *
 * Created on Feb 18, 2009, 1:00:52 PM
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.ReceiptDetail;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
//import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
//import com.openbravo.possync.DataLogicIntegration;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class ChequeDetailstd1 extends javax.swing.JDialog {
   
    private DataLogicSystem m_dlSystem;
    private PaymentsModelstd1 m_PaymentsToClose = null;
    private DataLogicFacilities dlfac;
    private TicketParser m_TTP;
    private AppView m_App;
    /** Creates new form ChequeDetail */
    public ChequeDetailstd1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

     protected ChequeDetailstd1(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public ChequeDetailstd1(Frame parent,PaymentsModelstd1 m_Payments){
         super(parent,true);
         m_PaymentsToClose=m_Payments;
         List clines= m_PaymentsToClose.getChequeDetailLines();

         initComponents();
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         cdetail.getTableHeader().setReorderingAllowed(false);
         cdetail.setRowHeight(25);
         cdetail.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     }
     public ChequeDetailstd1(Dialog parent,PaymentsModelstd1 m_Payments){
         super(parent,true);
         m_PaymentsToClose=m_Payments;
         initComponents();
         cdetail.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[] {Formats.STRING, Formats.STRING, Formats.CURRENCY, Formats.STRING}));
         cdetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         cdetail.getTableHeader().setReorderingAllowed(false);
         cdetail.setRowHeight(25);
         cdetail.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

      public static ChequeDetailstd1 getDialog(Component parent,PaymentsModelstd1 m_Payments) {
        Window window = getWindow(parent);
        ChequeDetailstd1 cd;
        if (window instanceof Frame) {
            cd = new ChequeDetailstd1((Frame) window,m_Payments);
        } else {
            cd = new ChequeDetailstd1((Dialog) window,m_Payments);
        }
        return cd;
    }

       public  boolean showDialog(AbstractTableModel tablemodel,boolean status,String text) throws BasicException {
           m_App=LookupUtilityImpl.getInstance(null).getAppView();
           m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
           m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);
           dlfac=(DataLogicFacilities)m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
           cdetail.setModel(tablemodel);
           jLabel1.setText(text);
           if(status==true)
               jButton1.setVisible(true);
           else
               jButton1.setVisible(false);
          TableColumnModel jColumns = cdetail.getColumnModel();
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cdetail = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cdetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(cdetail);

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

  try{
        if(jLabel1.getText().equals("Details Of Cheque")){
        String sresource = m_dlSystem.getResourceAsXML("Printer.ChequeDetail");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("payments", m_PaymentsToClose);
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
        }
        }else if(jLabel1.getText().equals("Details Of Debt Collected")){
            int row=cdetail.getSelectedRow();
            if(row>=0){
                String rid=cdetail.getValueAt(row, 1).toString();
                Date date=(Date)cdetail.getValueAt(row, 0);
                String custacc=m_PaymentsToClose.getDebtraisedModel().getValueAt(row, 4).toString();
                Object[] cinfo=dlfac.getCustomerbyAccount(custacc);
                List<ReceiptDetail> list = new StaticSentence(m_App.getSession()
                         , "SELECT A.BALANCEAMOUNT,F.NAME,A.PAYMENTREF,A.AMOUNT,?,A.NARRATION FROM ACCOUNTJOURNAL A JOIN FACILITY F ON A.TRANSREF=F.ID  WHERE A.ACCOUNTID=? AND A.PAYMENTREF LIKE '%' ? ' # %'" //'%'+?+' # %'
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})
                         , new SerializerReadClass(ReceiptDetail.class)).list(new Object[]{rid,custacc,rid});
                Double amt1=0.0;
                for(ReceiptDetail rd:list){
                    amt1+=rd.getAmount1();
                }
                List<PaymentInfoTicket> pinfo=m_dlSystem.getTicketPaymentsNew(rid);
                Double amt=0.0;

                for(PaymentInfoTicket p:pinfo){
                    amt+=p.getTotal();
                }
                if(amt>amt1){
                   ReceiptDetail rd1=new ReceiptDetail();
                   rd1.setFname("Account Pay");
                   rd1.setOrgAmount(0.0);
                   rd1.setBalAmount(0.0);
                   rd1.setAmount(dlfac.roundTwoDecimals(amt-amt1));
                   list.add(rd1);
                }
                //printTicketReceipt(List list,String receiptno,String cname,List<PaymentInfo> pinfo,Double amount,String memno)
                printTicketReceipt( list,rid,cinfo[0].toString(),pinfo,amt,cinfo[1].toString(),date);

            }
        }else if(jLabel1.getText().equals("Details Of Other Income")){
            int row=cdetail.getSelectedRow();
            if(row>=0){
             String rid=cdetail.getValueAt(row, 1).toString();
             Date date=(Date)cdetail.getValueAt(row, 0);
             Object[] obj=(Object[])new StaticSentence(m_App.getSession()
                     ,"SELECT R.PAYMENTREF FROM  RECEIPTS R  WHERE  PAYMENTREF IS NOT NULL AND R.ID=? "
                     , SerializerWriteString.INSTANCE
                     , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(rid);
               if(obj!=null && obj[0]!=null ){
                   if(obj[0].toString().equals("Guest Entry")){
                       Object[] list = (Object[])new StaticSentence(m_App.getSession()
                         , "SELECT GL.AMOUNT,GL.NUM,GL.NAMES,GL.RECEIPTNO,C.SEARCHKEY,C.NAME,GC.NAME,GC.RATE FROM GUESTLOG GL JOIN CUSTOMERS C ON C.ID=GL.MEMNO JOIN GUESTCAT GC ON GC.ID=GL.GUESTCAT WHERE GL.RECEIPTNO=?"
                         , new SerializerWriteBasic(new Datas[]{Datas.STRING})
                         , new SerializerReadBasic(new Datas[]{Datas.DOUBLE,Datas.INT,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE})).find(new Object[]{rid});
                       List<PaymentInfoTicket> pinfo=m_dlSystem.getTicketPaymentsNew(rid);
                       if(list!=null){
                            //private void printTicketGuestReceipt(String names,String receiptno,String cname,List<PaymentInfoTicket> pinfo,Double amount,String gcat,int gno,String skey,double gactrate) throws ScriptException {
                              printTicketGuestReceipt( String.valueOf(list[2]),String.valueOf(list[3]),String.valueOf(list[5]), pinfo,Double.valueOf(String.valueOf(list[0])),String.valueOf(list[6]),Integer.parseInt(String.valueOf(list[1])),String.valueOf(list[4]),Double.parseDouble(String.valueOf(list[7])),date) ;
                       }

                   }else{
                       Object[] obj1=(Object[])new StaticSentence(m_App.getSession()
                        ,"SELECT R.PAYMENTREF,R.DESC_,P.CUSTOMER FROM  RECEIPTS R JOIN PAYMENTS P ON R.ID=P.RECEIPT  WHERE  R.PAYMENTREF IS NOT NULL AND R.ID=?  "
                        , SerializerWriteString.INSTANCE
                        , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).find(rid);
                       List<PaymentInfoTicket> pinfo=m_dlSystem.getTicketPaymentsNew(rid);
                       if(obj1!=null){
                          String temp=obj1[0].toString();
                          String[] temp2=temp.split("#");
                          List<ReceiptDetail> parlist=new ArrayList<ReceiptDetail>();
                          List amtlist=new ArrayList();
                          double amt=0.0;
                          if(temp2.length>1){
                            for(int i=0;i<temp2.length;i++){
                              Object[] obj2=(Object[])new StaticSentence(m_App.getSession()
                                  ,"SELECT NAME FROM ACCOUNTMASTER WHERE ID=?"
                                  , SerializerWriteString.INSTANCE
                                  , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(temp2[i]);
                              if(obj2!=null){
                                  ReceiptDetail r=new ReceiptDetail();
                                  r.setFname(StringUtils.encodeXML(String.valueOf(obj2[0])));
                                  r.setNarration(Formats.ConvertDoubleToString(Double.parseDouble(temp2[++i])));
                                  //Object[] ob1=new Object[]{StringUtils.encodeXML(String.valueOf(obj2[0])),Formats.ConvertDoubleToString(Double.parseDouble(temp2[++i]))};
                                 parlist.add(r);
                              }
                                // amtlist.add(temp2[++i]);
                                 amt+=Double.parseDouble(String.valueOf(temp2[i]));
                            }

                          }
                          printTicketGeneralReceipt(rid, String.valueOf(obj1[2]),pinfo,String.valueOf(obj1[1]),parlist,amtlist,amt,date);
                          // private void printTicketGeneralReceipt(String receiptno,String cname,List<PaymentInfoTicket> pinfo,String desc,List list,List list2,double totalamt) throws ScriptException {
                       }
                    }
               }

            }
        }
    }catch(Exception e){
         e.printStackTrace();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cdetail;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
