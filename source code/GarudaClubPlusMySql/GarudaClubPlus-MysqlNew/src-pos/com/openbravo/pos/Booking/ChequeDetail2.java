

package com.openbravo.pos.Booking;

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
import com.openbravo.pos.panels.ChequeDetail;
import com.openbravo.pos.panels.PaymentsModel;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;


public class ChequeDetail2 extends javax.swing.JDialog {

    private DataLogicSystem m_dlSystem;
    private PaymentsModel2 m_PaymentsToClose = null;
    private DataLogicFacilities dlfac;
    private TicketParser m_TTP;
    private AppView m_App;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
     public ChequeDetail2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

     protected ChequeDetail2(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public ChequeDetail2(Frame parent,PaymentsModel2 m_Payments){
         super(parent,true);
         m_PaymentsToClose=m_Payments;
         List clines= m_PaymentsToClose.getChequeDetailLines();

         initComponents();
         jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
         cdetail.getTableHeader().setReorderingAllowed(false);
         cdetail.setRowHeight(25);
         cdetail.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     }
     public ChequeDetail2(Dialog parent,PaymentsModel2 m_Payments){
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

      public static ChequeDetail2 getDialog(Component parent,PaymentsModel2 m_Payments) {
        Window window = getWindow(parent);
        ChequeDetail2 cd;
        if (window instanceof Frame) {
            cd = new ChequeDetail2((Frame) window,m_Payments);
        } else {
            cd = new ChequeDetail2((Dialog) window,m_Payments);
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
     
     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cdetail = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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

        jLabel1.setText("jLabel1");

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    
                    
                    
                   /* 
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
                    */
                    
                     Object[] romm_Obj=(Object[])new StaticSentence(m_App.getSession()
                        ,"SELECT R.ID FROM  advnce_agnst_guestroom R  WHERE  RECIEPT_NO =? "
                        , SerializerWriteString.INSTANCE
                        , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(rid);
                    
                      Object[] Hall_Obj=(Object[])new StaticSentence(m_App.getSession()
                        ,"SELECT R.ID FROM  advnce_agnst_hall R  WHERE  RECIEPT_NO =? "
                        , SerializerWriteString.INSTANCE
                        , new SerializerReadBasic(new Datas[]{Datas.STRING})).find(rid);
                     
                      
                     Object[] list = null ;
                      
                     if(Hall_Obj!=null){
                         
                           list = (Object[])new StaticSentence(m_App.getSession(),
                                        "SELECT A.RECIEPT_NO , C.NAME , C.SEARCHKEY , M.NAME , A.ADVNCE_AMT , B.BOOKING_SEQ_NO , B.BOOKING_DATE , B.BOOKING_SLOT , B.CHARGES , A.CRDATE \n" +
                                        "FROM advnce_agnst_hall A , CUSTOMERS C , hall_master M , hall_booked_details B\n" +
                                        "WHERE C.ID=A.CUSTOMER AND A.HALLNAME = M.ID AND B.ID=A.BOOKING_ID AND A.RECIEPT_NO=? \n" +
                                        "UNION\n" +
                                        "SELECT A.RECIEPT_NO , C.NAME , C.SEARCHKEY , M.NAME , A.ADVNCE_AMT , B.BOOKING_SEQ_NO , B.BOOKING_DATE , B.BOOKING_SLOT, B.CHARGES , A.CRDATE  \n" +
                                        "FROM advnce_agnst_hall A , CUSTOMERS C , hall_master M , hall_cancelled_details B\n" +
                                        "WHERE C.ID=A.CUSTOMER AND A.HALLNAME = M.ID AND B.ID=A.BOOKING_ID AND A.RECIEPT_NO=? "
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING})
                                        , new SerializerReadBasic(new Datas[]{Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE  , Datas.TIMESTAMP})).find(new Object[]{rid});

                         
                     } 
                     
                     if(romm_Obj!=null){
                         
                           list = (Object[])new StaticSentence(m_App.getSession(),
                                        "SELECT  A.RECIEPT_NO , C.NAME , C.SEARCHKEY , M.ROOMTYPE , A.ADVNCE_AMT , B.BOOKING_SEQ_NO , B.BOOKING_DATE_EX , B.BOOKING_DAYS , B.CHARGES , A.CRDATE\n" +
                                        "FROM advnce_agnst_guestroom A , CUSTOMERS C , guestroom_master M , guestroom_booked_details B\n" +
                                        "WHERE C.ID=A.CUSTOMER AND A.ROOMTYPE = M.ID AND B.ID=A.BOOKING_ID AND A.RECIEPT_NO=?\n" +
                                        "UNION\n" +
                                        "SELECT A.RECIEPT_NO , C.NAME , C.SEARCHKEY , M.ROOMTYPE , A.ADVNCE_AMT , B.BOOKING_SEQ_NO , B.BOOKING_DATE_EX , B.BOOKING_DAYS, B.CHARGES , A.CRDATE\n" +
                                        "FROM advnce_agnst_guestroom A , CUSTOMERS C , guestroom_master M , guestroom_cancelled_details B\n" +
                                        "WHERE C.ID=A.CUSTOMER AND A.ROOMTYPE = M.ID AND B.ID=A.BOOKING_ID AND A.RECIEPT_NO=?"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING})
                                        , new SerializerReadBasic(new Datas[]{ Datas.STRING , Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP,Datas.INT,Datas.DOUBLE , Datas.TIMESTAMP})).find(new Object[]{rid});

                         
                         
                         
                     }
                    
                    
                     
                    if(Hall_Obj!=null){
                        
                        printHallAdvanceReciept(list);
                        
                    } 
                    if(romm_Obj!=null){
                        
                        PrintAdvanceRecieptForRooms(list);
                        
                        
                    } 
                     
                     
                     
                    
                    
                    
                    
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
            
                else if(jLabel1.getText().equals("Details Of Payment Made")){
                    int row=cdetail.getSelectedRow();
                    if(row>=0){
                        String rid=cdetail.getValueAt(row, 1).toString();
                       // Date date=(Date)cdetail.getValueAt(row, 0);
                        Double Amt = Double.parseDouble(cdetail.getValueAt(row, 3).toString());
                        
                        
                        
                        
                        Object[] list = (Object[])new StaticSentence(m_App.getSession(),
                                        "SELECT R.RV_NO , R.REFUND_DATE , C.NAME , R.MEM_NO , R.BOOKING_SEQ_NO , R.BILLED_AMT , R.ADVNCE_AMT , R.REFUND_AMT , R.REFUND_BY  , R.ROOMTYPE , R.CANCELLED \n" +
                                        "FROM room_hall_refund_voucher r , CUSTOMERS C\n" +
                                        "WHERE C.ID=R.CUST_ID AND R.RV_NO=?"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING})
                                        , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE , Datas.STRING , Datas.STRING , Datas.INT })).find(new Object[]{rid});

                        
                       
                        
                       printRefundVoucher(list); 
                     
                      

                    }
                }
                    
            
            
                }catch(Exception e){
                    e.printStackTrace();
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /**/ /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChequeDetail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChequeDetail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChequeDetail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChequeDetail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChequeDetail2 dialog = new ChequeDetail2(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cdetail;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


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
  
  
  
  public void printRefundVoucher(Object[] l){
      
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
      
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            
            

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", l[2]);
            script.put("cno", l[3]);
            script.put("date", new Date()); 
            
            
            
            script.put("Reciept_title", "Refund Voucher Id:");
            script.put("receipt", l[0]);
            
            script.put("maintitle", "Duplicate Refund Voucher");
            script.put("label_1", "Booking Id :");
            script.put("text1", l[4]);
            
            script.put("label_2", "Room/Hall :");
            script.put("text2", l[9]);
            
            
            script.put("label_4", "Refund Crby:");
            script.put("text4",  m_App.getAppUserView().getUser().getName() );
            
            script.put("label_3", "Refund Cr Date:");
            script.put("text3", l[1]); 
            
            Double TotAdvRecv = Double.parseDouble(l[6].toString());
            Double Refund_Amt = Double.parseDouble(l[7].toString());
            Double Cancel_Chrg = TotAdvRecv - Refund_Amt;
            
            int Status = Integer.parseInt(l[10].toString());
            if(Status==1){
                 script.put("label_5", "Tot Cancellation Chrg :");
                 script.put("text5", decimalFormat.format(Cancel_Chrg));
                
                
            }
            else{
                
                 script.put("label_5", "");
                 script.put("text5", "");
                
            }
           
            
            script.put("label_6", "Total Amount :");
            script.put("text6", decimalFormat.format(l[5]));
            
            script.put("label_7", "Advance Recv :");
            script.put("text7", decimalFormat.format(l[6]));
             
             
            script.put("label_8", "Refund Amount :");
            script.put("text8", decimalFormat.format(l[7]));
            
            script.put("label_9", "Booking Id:");
            script.put("text9", l[4]); 
            
            script.put("Gname", "");
            
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
      
  }
  
  
  // PRINT ADVANCE RECIEPT FOR HALLS 
  
   public void printHallAdvanceReciept(Object[] l){
      
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
      
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            
            

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", l[1]);
            script.put("cno", l[2]);
            script.put("date", new Date()); 
            
            
            
            script.put("Reciept_title", "Receipt NO :");
            script.put("receipt", l[0]);
            
            script.put("maintitle", "Duplicate Advance Receipt ");
            script.put("label_1", "Booking Id :");
            script.put("text1", l[5]);
            
            script.put("label_2", "Hall Name:");
            script.put("text2", l[3]);
            
            
            script.put("label_4", "Booked Date:");
            script.put("text4", l[6] );
            
            script.put("label_3", "Slot Booked : ");
            script.put("text3", l[7]); 
            
            script.put("label_5", "Advance Recv Date:");
            script.put("text5", l[9]);
                
            
           
            
           
            
            script.put("label_6", "Total Amount :");
            script.put("text6", decimalFormat.format(l[8]));
            
            script.put("label_7", "Advance Recv :");
            script.put("text7", decimalFormat.format(l[4]));
             
             
            script.put("label_8", "");
            script.put("text8", "");
            
            script.put("label_9", "");
            script.put("text9", ""); 
            
            
           
           
           
            script.put("Gname", "");
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
    }
  
  
   // PRINT ADVANCE RECIEPT FOR ROOMS 
   
    public void PrintAdvanceRecieptForRooms(Object[] l){
      
        String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.GRBill");
      
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            
            

            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            script.put("createdby", m_App.getAppUserView().getUser().getName());
            String x = m_App.getAppUserView().getUser().getRole();
            script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
            script.put("host",  m_App.getProperties().getHost());
            script.put("cname", l[1]);
            script.put("cno", l[2]);
            script.put("date", new Date()); 
            
            
            
             script.put("Reciept_title", "Receipt NO :");
            script.put("receipt", l[0]);
            
            script.put("maintitle", "Duplicate Advance Receipt ");
            script.put("label_1", "Booking Id :");
            script.put("text1", l[5]);
            
            script.put("label_2", "Room Type :");
            script.put("text2", l[3]);
            
            
            script.put("label_4", "Booked Date:");
            script.put("text4", l[6] );
            
            script.put("label_3", "No. od Days : ");
            script.put("text3", l[7]); 
            
            script.put("label_5", "Advance Recv Date:");
            script.put("text5", l[9]);
                
            
           
            
           
            
            script.put("label_6", "Total Amount :");
            script.put("text6", decimalFormat.format(l[8]));
            
            script.put("label_7", "Advance Recv :");
            script.put("text7", decimalFormat.format(l[4]));
             
             
            script.put("label_8", "");
            script.put("text8", "");
            
            script.put("label_9", "");
            script.put("text9", ""); 
            
            
           
           
           
            script.put("Gname", "");
            
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            
            
            
            
            
           
           
            
            
            
        } catch (ScriptException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (TicketPrinterException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            msg.show(this);
        } catch (Exception e) {
        }
      
      
      
      
      
  } 
   
  
  
}
