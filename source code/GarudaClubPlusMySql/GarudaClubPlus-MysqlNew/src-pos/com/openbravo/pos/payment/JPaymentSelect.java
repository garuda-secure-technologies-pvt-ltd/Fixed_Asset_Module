

package com.openbravo.pos.payment;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.JPaymentInterface;
import com.openbravo.pos.sales.BillInfo;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.sales.restaurant.BillList;
import java.awt.ComponentOrientation;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author adrianromero
 */
public abstract class JPaymentSelect extends javax.swing.JDialog 
                            implements JPaymentNotifier {
    
    private PaymentInfoList m_aPaymentInfo;
    private boolean printselected;
    
    private boolean accepted;
    
    private AppView app;
    private double m_dTotal; 
    private CustomerInfoExt customerext;
    private DataLogicSystem dlSystem;
    private DataLogicSales m_dlSales;
    private double intrest;
    double intrestPlusAmount;
    private Map<String, JPaymentInterface> payments = new HashMap<String, JPaymentInterface>();
  private DecimalFormat nf1=new DecimalFormat("###0.00");
  //Added by GG
  public static String intRefID=null;
  public static String  txRefID = null;
  public static double intterest;
  protected Session s;
  public static String gggg=null;
     int counter = 1;

    
    /** Creates new form JPaymentSelect */
    protected JPaymentSelect(java.awt.Frame parent, boolean modal, ComponentOrientation o) {
        super(parent, modal);
        initComponents();    
        
        this.applyComponentOrientation(o);
        
        getRootPane().setDefaultButton(m_jButtonOK); 
    }
    /** Creates new form JPaymentSelect */
    protected JPaymentSelect(java.awt.Dialog parent, boolean modal, ComponentOrientation o) {
        super(parent, modal);
        initComponents();       
        
        this.applyComponentOrientation(o);
        
        getRootPane().setDefaultButton(m_jButtonOK); 
    }    
    
    public void init(AppView app) {
        this.app = app;
        dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        printselected = true;
        m_dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
    }
    
    public void setPrintSelected(boolean value) {
        printselected = value;
    }
    
    public boolean isPrintSelected() {
        return printselected;
    }

    public List<PaymentInfo> getSelectedPayments() {
        return m_aPaymentInfo.getPayments();        
    }
            
    public boolean showDialog(double total, CustomerInfoExt customerext,String createdby,boolean flag) {
        intrestPlusAmount=total+intrest;
     
        jTextField1.setText(nf1.format(intrest)); //pratima: adding intrest amount
        boolean temp=false;
          String cname="";
        String cust[]=new String[5];
        if(flag==true){
         if(customerext!=null)
         {
         cname=customerext.getName();
         cust=cname.split(" : ");
         }
        AppView m_App= LookupUtilityImpl.getInstance(null).getAppView();
        this.customerext = customerext;     
    //  LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter");
  
      try{
          //warehouse changes -start
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,ROLE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING})).find(createdby);
                /*if(obj[5]==null)
                obj[5]=new Icon();*/

                String warehouse = null;
                if (obj2 != null && obj2[3] != null) {
                    String[] wArr = obj2[3].toString().split("#");
                    warehouse = wArr[0];
                }
                AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString(), warehouse);
                 //warehouse changes -end
         appuser.fillPermissions(dlSystem);
         temp= appuser.hasPermission("bar counter");
         if(cust.length>1 )
            temp=false;

      }
      catch(Exception e){
          e.printStackTrace();
         //  MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosave"), e);
        //    msg.show(this);
      }
        }else{
           this.customerext = customerext;
           temp=false;
        }
        if(customerext.getMemtype()==null || customerext.getMemtype().equals(""))
            temp=true;         // ********************************** EDITED BY AKASH
         m_aPaymentInfo = new PaymentInfoList();
        accepted = false;

        m_dTotal = total;
        m_jButtonPrint.setSelected(printselected);
        m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal)));
        
        addTabs(true);
        
        if (m_jTabPayment.getTabCount() == 0) {
            // No payment panels available            
            m_aPaymentInfo.add(getDefaultPayment(total));
            accepted = true;            
        } else {        
            printState();
            setVisible(true);
        }
        
        // gets the print button state
        printselected = m_jButtonPrint.isSelected();
        
        // remove all tabs        
        m_jTabPayment.removeAll();
        
        return accepted;
    }  
    
    
    
     public boolean showDialog2(double total, CustomerInfoExt customerext,String createdby,boolean flag) {
         boolean temp=false;
          String cname="";
       // String cust[]=new String[5];
        if(flag==true){
        // if(customerext!=null)
         //{
        // cname=customerext.getName();
       //  cust=cname.split(" : ");
        // }
        AppView m_App= LookupUtilityImpl.getInstance(null).getAppView();
        //this.customerext = customerext;     
    //  LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().hasPermission("bar counter");
  
      try{
          //warehouse changes -start
                Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID,NAME,ROLE,PRCATEGORIES FROM PEOPLE WHERE NAME=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING,Datas.STRING})).find(createdby);
                /*if(obj[5]==null)
                obj[5]=new Icon();*/

                String warehouse = null;
                if (obj2 != null && obj2[3] != null) {
                    String[] wArr = obj2[3].toString().split("#");
                    warehouse = wArr[0];
                }
               // AppUser appuser = new AppUser(obj2[0].toString(), obj2[1].toString(), obj2[2].toString(), warehouse);
                 //warehouse changes -end
        // appuser.fillPermissions(dlSystem);
        // temp= appuser.hasPermission("bar counter");
        //if(cust.length>1 )
           // temp=false;

      }
      catch(Exception e){
          e.printStackTrace();
         //  MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosave"), e);
        //    msg.show(this);
      }
        }else{
          // this.customerext = customerext;
           temp=false;
        }
        if(customerext.getMemtype()==null || customerext.getMemtype().equals(""))
           temp=true;  // ******************************************* CHANGED 
           m_aPaymentInfo = new PaymentInfoList();
           accepted = false;

        m_dTotal = total;
        m_jButtonPrint.setSelected(printselected);
        m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal)));
        
        addTabs(temp);
        
        if (m_jTabPayment.getTabCount() == 0) {
             //No payment panels available            
            m_aPaymentInfo.add(getDefaultPayment(total));
            accepted = true;            
        } else {        
            printState2();
            setVisible(true);
        }
        
        // gets the print button state
        printselected = m_jButtonPrint.isSelected();
        
        // remove all tabs        
        m_jTabPayment.removeAll();
        
        return accepted;
    }  
    
    
    
    protected abstract void addTabs(boolean temp);
    protected abstract void setStatusPanel(boolean isPositive, boolean isComplete);
    protected abstract PaymentInfo getDefaultPayment(double total);
    
    protected void setOKEnabled(boolean value) {
        m_jButtonOK.setEnabled(value);        
    }
    
    protected void setAddEnabled(boolean value) {
        m_jButtonAdd.setEnabled(value);
    }
        
    protected void addTabPayment(JPaymentCreator jpay) {
        if (app.getAppUserView().getUser().hasPermission(jpay.getKey())) {
            
            JPaymentInterface jpayinterface = payments.get(jpay.getKey());
            if (jpayinterface == null) {
                jpayinterface = jpay.createJPayment();
                payments.put(jpay.getKey(), jpayinterface);
            }
            
            jpayinterface.getComponent().applyComponentOrientation(getComponentOrientation());
            m_jTabPayment.addTab(
                    AppLocal.getIntString(jpay.getLabelKey()),
                    new javax.swing.ImageIcon(getClass().getResource(jpay.getIconKey())),
                    jpayinterface.getComponent());
        }
   }
    
    
    public interface JPaymentCreator {
        public JPaymentInterface createJPayment();
        public String getKey();
        public String getLabelKey();
        public String getIconKey();
    }

    public class JPaymentCashCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentCashPos(JPaymentSelect.this, dlSystem);
        }
        public String getKey() { return "payment.cash"; }
        public String getLabelKey() { return "tab.cash"; }
        public String getIconKey() { return "/com/openbravo/images/cash.png"; }
    }
        
    public class JPaymentChequeCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentCheque(JPaymentSelect.this);
        }
        public String getKey() { return "payment.cheque"; }
        public String getLabelKey() { return "tab.cheque"; }
        public String getIconKey() { return "/com/openbravo/images/desktop.png"; }
    }  
        
    public class JPaymentPaperCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentPaper(JPaymentSelect.this, "paperin");
        }
        public String getKey() { return "payment.paper"; }
        public String getLabelKey() { return "tab.paper"; }
        public String getIconKey() { return "/com/openbravo/images/knotes.png"; }
    }
   
    public class JPaymentMagcardCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentMagcard(app, JPaymentSelect.this);
        }
        public String getKey() { return "payment.magcard"; }
        public String getLabelKey() { return "tab.magcard"; }
        public String getIconKey() { return "/com/openbravo/images/vcard.png"; }
    }
        
    public class JPaymentFreeCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentFree(JPaymentSelect.this);
        }
        public String getKey() { return "payment.free"; }
        public String getLabelKey() { return "tab.free"; }
        public String getIconKey() { return "/com/openbravo/images/package_toys.png"; }
    }
        
    public class JPaymentDebtCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentDebt(JPaymentSelect.this);
        }
        public String getKey() { return "payment.debt"; }
        public String getLabelKey() { return "tab.debt"; }
        public String getIconKey() { return "/com/openbravo/images/kdmconfig32.png"; }
    }   
        
    public class JPaymentCashRefundCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentRefund(JPaymentSelect.this, "cashrefund");
        }
        public String getKey() { return "refund.cash"; }
        public String getLabelKey() { return "tab.cashrefund"; }
        public String getIconKey() { return "/com/openbravo/images/cash.png"; }
    }
        
    public class JPaymentChequeRefundCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentRefund(JPaymentSelect.this, "chequerefund");
        }
        public String getKey() { return "refund.cheque"; }
        public String getLabelKey() { return "tab.chequerefund"; }
        public String getIconKey() { return "/com/openbravo/images/desktop.png"; }
    }
       
    public class JPaymentPaperRefundCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentRefund(JPaymentSelect.this, "paperout");
        }
        public String getKey() { return "refund.paper"; }
        public String getLabelKey() { return "tab.paper"; }
        public String getIconKey() { return "/com/openbravo/images/knotes.png"; }
    }    
       
    public class JPaymentMagcardRefundCreator implements JPaymentCreator {
       public JPaymentInterface createJPayment() {     
            return new JPaymentMagcard(app, JPaymentSelect.this);
        }
        public String getKey() { return "refund.magcard"; }
        public String getLabelKey() { return "tab.magcard"; }
        public String getIconKey() { return "/com/openbravo/images/vcard.png"; }
    }    
    
    protected void setHeaderVisible(boolean value) {
        jPanel6.setVisible(value);
    }
    
    private void printState() {
        
        m_jRemaininglEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal - m_aPaymentInfo.getTotal())));
        m_jButtonRemove.setEnabled(!m_aPaymentInfo.isEmpty());
        m_jTabPayment.setSelectedIndex(0); // selecciono el primero
        ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).activate(customerext, m_dTotal - m_aPaymentInfo.getTotal(),app);
    }
    
    private void printState2() {
        
        m_jRemaininglEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal)));
        m_jButtonRemove.setEnabled(false);
       // m_jTabPayment.setSelectedIndex(0); // selecciono el primero
       // ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).activate(customerext, m_dTotal - m_aPaymentInfo.getTotal());
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
    
    public void setStatus(boolean isPositive, boolean isComplete) {
        
        setStatusPanel(isPositive, isComplete);
    }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        m_jLblTotalEuros1 = new javax.swing.JLabel();
        m_jTotalEuros = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        m_jLblRemainingEuros = new javax.swing.JLabel();
        m_jRemaininglEuros = new javax.swing.JLabel();
        m_jButtonAdd = new javax.swing.JButton();
        m_jButtonRemove = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        m_jTabPayment = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_jButtonPrint = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        m_jButtonOK = new javax.swing.JButton();
        m_jButtonCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("payment.title")); // NOI18N
        setResizable(false);

        jPanel4.setMinimumSize(new java.awt.Dimension(600, 130));

        m_jLblTotalEuros1.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
        jPanel4.add(m_jLblTotalEuros1);

        m_jTotalEuros.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        m_jTotalEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jTotalEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTotalEuros.setOpaque(true);
        m_jTotalEuros.setPreferredSize(new java.awt.Dimension(125, 25));
        m_jTotalEuros.setRequestFocusEnabled(false);
        jPanel4.add(m_jTotalEuros);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        m_jLblRemainingEuros.setText(AppLocal.getIntString("label.remainingcash")); // NOI18N
        jPanel6.add(m_jLblRemainingEuros);

        m_jRemaininglEuros.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        m_jRemaininglEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jRemaininglEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jRemaininglEuros.setOpaque(true);
        m_jRemaininglEuros.setPreferredSize(new java.awt.Dimension(125, 25));
        m_jRemaininglEuros.setRequestFocusEnabled(false);
        jPanel6.add(m_jRemaininglEuros);

        m_jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnplus.png"))); // NOI18N
        m_jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_jButtonAddMouseClicked(evt);
            }
        });
        m_jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jButtonAddActionPerformed(evt);
            }
        });
        jPanel6.add(m_jButtonAdd);

        m_jButtonRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnminus.png"))); // NOI18N
        m_jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jButtonRemoveActionPerformed(evt);
            }
        });
        jPanel6.add(m_jButtonRemove);

        jPanel4.add(jPanel6);

        getContentPane().add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        m_jTabPayment.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        m_jTabPayment.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        m_jTabPayment.setFocusable(false);
        m_jTabPayment.setRequestFocusEnabled(false);
        m_jTabPayment.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m_jTabPaymentStateChanged(evt);
            }
        });
        jPanel3.add(m_jTabPayment, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        m_jButtonPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileprint.png"))); // NOI18N
        m_jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jButtonPrintActionPerformed(evt);
            }
        });
        jPanel2.add(m_jButtonPrint);
        jPanel2.add(jPanel1);

        m_jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        m_jButtonOK.setText(AppLocal.getIntString("Button.OK")); // NOI18N
        m_jButtonOK.setFocusPainted(false);
        m_jButtonOK.setFocusable(false);
        m_jButtonOK.setMargin(new java.awt.Insets(8, 16, 8, 16));
        m_jButtonOK.setRequestFocusEnabled(false);
        m_jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jButtonOKActionPerformed(evt);
            }
        });
        jPanel2.add(m_jButtonOK);

        m_jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        m_jButtonCancel.setText(AppLocal.getIntString("Button.Cancel")); // NOI18N
        m_jButtonCancel.setFocusPainted(false);
        m_jButtonCancel.setFocusable(false);
        m_jButtonCancel.setMargin(new java.awt.Insets(8, 16, 8, 16));
        m_jButtonCancel.setRequestFocusEnabled(false);
        m_jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jButtonCancelActionPerformed(evt);
            }
        });
        jPanel2.add(m_jButtonCancel);

        jPanel5.add(jPanel2, java.awt.BorderLayout.LINE_END);

        jLabel1.setText("                   Interest ");
        jPanel5.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jTextField1.setEditable(false);
        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(698, 498));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonRemoveActionPerformed

        m_aPaymentInfo.removeLast();
        printState();        
        
    }//GEN-LAST:event_m_jButtonRemoveActionPerformed

    private void m_jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonAddActionPerformed
      PaymentInfo returnPayment = ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).executePayment();
        if (returnPayment != null) {
            m_aPaymentInfo.add(returnPayment);
            printState();
        }   
       
        
    }//GEN-LAST:event_m_jButtonAddActionPerformed

    private void m_jTabPaymentStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m_jTabPaymentStateChanged
        
        if (m_jTabPayment.getSelectedComponent() != null) {
           
            
           // ************************** ADDEDD BY AKASH ****************************
       
                ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).activate(customerext, m_dTotal - m_aPaymentInfo.getTotal(),app);
            
           
          
           //****************************************************************************
           
           
         }
        
    }//GEN-LAST:event_m_jTabPaymentStateChanged

    private void m_jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonOKActionPerformed

       try{ 
        Transaction t = new Transaction(app.getSession()) {
            //  boolean error=false;

            public Object transact() throws BasicException {
            
        
                    PaymentInfo returnPayment = ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).executePayment();
                   
                    if (returnPayment != null) {
                        
//                        BillInfo bi1 =  new BillInfo();
//                        if(!getNextReceiptID1(bi1.getCreatedBy()).equalsIgnoreCase(null)){
                    //    if(returnPayment.getTotal()>=intrestPlusAmount){
                      //  returnPayment.setTotal(returnPayment.getTotal()-intrest);
                        returnPayment.setTotal(returnPayment.getTotal());
                        m_aPaymentInfo.add(returnPayment);
                        accepted = true;
                        ////////////////////////Added by Ganesh
                        if(m_aPaymentInfo.getPayments().size()>1){
                          //  if(m_aPaymentInfo.getPayments().equals("magcard")){
                                
                            
                                if(m_aPaymentInfo.getPayments().get(0).getName().equals("magcard")){

                            if(returnPayment.getOtherCharges()>0.00){
                           try{
                               InsertIntoGeneralReceiptBankCharges1(m_aPaymentInfo.getPayments().get(0));
                           }
                           catch(BasicException e){
                               e.printStackTrace();
                           }
                        }
                                }
                            
                          //   if(m_aPaymentInfo.getPayments().equals("magcard")){
                          if(m_aPaymentInfo.getPayments().size()>1){
                                   if(m_aPaymentInfo.getPayments().get(1)!=null){
                                 if(m_aPaymentInfo.getPayments().get(1).getName().equals("magcard")){

                            if(returnPayment.getOtherCharges()>0.00){
                           try{
                               InsertIntoGeneralReceiptBankCharges1(m_aPaymentInfo.getPayments().get(1));
                           }
                           catch(BasicException e){
                               e.printStackTrace();
                           }
                        }
                               // }
                            }
                                   }
                          }
                                  //if(m_aPaymentInfo.getPayments().equals("magcard")){
                                   if(m_aPaymentInfo.getPayments().size()>2){
                                   if(m_aPaymentInfo.getPayments().get(2).getName().equals("magcard")){
                                       if(m_aPaymentInfo.getPayments().get(2)!=null){

                            if(returnPayment.getOtherCharges()>0.00){
                           try{
                               InsertIntoGeneralReceiptBankCharges1(m_aPaymentInfo.getPayments().get(2));
                           }
                           catch(BasicException e){
                               e.printStackTrace();
                           }
                        }
                                }
                                   }
                                }
                        /////////////////pratima :intrest amount addition
                        
                        if(intrest>0){
                            //if(DataLogicSales.gflag==1){
                            //added by ganesh
                            if(m_aPaymentInfo.getPayments().size()>1 && m_aPaymentInfo.getPayments().size()<3){
                                if(m_aPaymentInfo.getPayments().get(1)!=null){
                                     InsertIntrestCharges1(returnPayment);
                                }
                            }
                           else  if(m_aPaymentInfo.getPayments().size()>2 && m_aPaymentInfo.getPayments().size()<4){
                                if(m_aPaymentInfo.getPayments().get(2)!=null){
                                     InsertIntrestCharges1(returnPayment);
                                }
                            }
//                           else{
//                         try{
//                               InsertIntrestCharges1(returnPayment);
//                           }
//                           catch(BasicException e){
//                               e.printStackTrace();
//                           }
//                        }
                       // }
                     }/////////////////////////////end
                        
                        
                        
                        dispose();
                            
                            
                        }
                        /////////////ended by Ganesh
                        //if(getNextReceiptIDg()!=null){
                     
                        else
                        {
                            if(returnPayment.getOtherCharges()>0.00){
                           try{
                               InsertIntoGeneralReceiptBankCharges(returnPayment);
                           }
                           catch(BasicException e){
                               e.printStackTrace();
                           }
                        }
                        /////////////////pratima :intrest amount addition
                        
                        if(intrest>0){
                            
                            //if(DataLogicSales.gflag==1){
                         try{
                               InsertIntrestCharges(returnPayment);
                           }
                           catch(BasicException e){
                               e.printStackTrace();
                           }
                        }
                     //  }/////////////////////////////end
                        
                        
                        
                        dispose();
                //    }//intrestPlusAmountifended
                //        else{
                 //       JOptionPane.showMessageDialog( null,"Given Amount is less than total amount.", "Error", JOptionPane.OK_OPTION);
                    //}
                   //} 
             //return null;
                   }
                  // else
            }
         return null;
            }
            //  }
        };
        t.execute();
       }
       catch(BasicException e){
           e.printStackTrace();
       }
       //ended By Ganesh
        
    }//GEN-LAST:event_m_jButtonOKActionPerformed

    private void m_jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonCancelActionPerformed

        dispose();
        
    }//GEN-LAST:event_m_jButtonCancelActionPerformed

    private void m_jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jButtonPrintActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void m_jButtonAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_jButtonAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jButtonAddMouseClicked
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton m_jButtonAdd;
    private javax.swing.JButton m_jButtonCancel;
    private javax.swing.JButton m_jButtonOK;
    private javax.swing.JToggleButton m_jButtonPrint;
    private javax.swing.JButton m_jButtonRemove;
    private javax.swing.JLabel m_jLblRemainingEuros;
    private javax.swing.JLabel m_jLblTotalEuros1;
    private javax.swing.JLabel m_jRemaininglEuros;
    private javax.swing.JTabbedPane m_jTabPayment;
    private javax.swing.JLabel m_jTotalEuros;
    // End of variables declaration//GEN-END:variables
    
    
    public void InsertIntoGeneralReceiptBankCharges(PaymentInfo returnPayment) throws BasicException{
         BillInfo ticket=new BillInfo();
             ticket.setID(UUID.randomUUID().toString());
             ticket.setPayments(m_aPaymentInfo.getPayments());
             ticket.setCustomer(customerext);
             ticket.setCreatedBy(app.getAppUserView().getUser().getName());
             ticket.setCreatedDate(new Date());
             ticket.setActiveCash(app.getActiveCashIndex());
             String AccountID="";   
             
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Bank Charges Collected Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+m_aPaymentInfo.getPayments().get(0).getOtherCharges());
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("CC");
                 payaccountForCard(ticket, app.getInventoryLocation(),true);
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify bank charges account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
             
             
    }
    
    
    
    
     int flag1;
     String receiptno = null;
     public final String payaccountForCard(final BillInfo ticket, final String location, final boolean flag) throws BasicException {
       
         receiptno = null;
        Boolean perror = false;
        flag1=0;
        Transaction t = new Transaction(app.getSession()) {
            //  boolean error=false;

            public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                
                
                if (true) {
                    if (ticket.getReceiptRef() == null) {
                        final String rno = getNextReceiptID1(ticket.getCreatedBy());
                        receiptno = rno;
                        ticket.setReceiptRef(rno);
                        if (rno == null) {
                            flag1 = 1;
                            return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                    }
//                    else
//                    {
                    // new receipt
                    if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, "CC :"+ticket.getReceiptRef());//contains description for general receipts
                            }
                        });
                    } else {
                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                            }
                        });
                    }

                    SentenceExec paymentinsert = new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                    for (final PaymentInfo p : ticket.getPayments()) {
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                txRefID = ticket.getReceiptRef();
                                setString(3, p.getName());
                                 setDouble(4, BillInfo.totalcardOtherCharge);
                             //   setDouble(4, m_aPaymentInfo.getPayments().get(0).getOtherCharges());
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });

                       

                        
                    }
                //}
                }
                return null;
            }
            //  }
        };
        t.execute();
        //  if(flag1==0)
        //  return true;
        // else
        //    return false;
        if (perror == false ) {
            return receiptno;
        } else {
            return "false";
        }
    }
    ////////////////////////////////////////////by pratima intrest amount addition
          public void InsertIntrestCharges(PaymentInfo returnPayment) throws BasicException{
         BillInfo ticket=new BillInfo();
          
             ticket.setID(UUID.randomUUID().toString());
             ticket.setPayments(m_aPaymentInfo.getPayments());
             ticket.setCustomer(customerext);
             ticket.setCreatedBy(app.getAppUserView().getUser().getName());
             ticket.setCreatedDate(new Date());
             ticket.setActiveCash(app.getActiveCashIndex());
             String AccountID="";   
             
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Pending Bill Intrest Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+intrest);
                 gggg=ticket.getFloor();
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("IA");
                 payaccountForIntrest(ticket, app.getInventoryLocation());
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify Pending Bill Intrest Account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
           
             
    }
         public final String payaccountForIntrest(final BillInfo ticket, final String location) throws BasicException {
       final String rno = getNextReceiptID1(ticket.getCreatedBy());
        // receiptno = null;//p
        
       Boolean perror = false;
      //  flag1=0;//p
        Transaction t = new Transaction(app.getSession()) {
            //  boolean error=false;

            public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                
               
                if (true) {
                    if (ticket.getReceiptRef() == null) {
                     //   final String rno = getNextReceiptID1(ticket.getCreatedBy());
                      //  receiptno = rno;//p
                        ticket.setReceiptRef(rno);
                        if (rno == null) {
                         //   flag1 = 1;//p
                            return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                    }
//                    else {
                    // new receipt
                    //if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, "IA :"+ticket.getReceiptRef());//contains description for general receipts
                            }
                        });
//                    } else {
//                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {
//
//                            public void writeValues() throws BasicException {
//                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
//                                //  setString(2, ticket.getActiveCash());
//                                setTimestamp(2, date); //Receipt date could be different from bill date
//                                setString(3, ticket.getCreatedBy());
//                            }
//                        });
//                    }
                        BillInfo ticket1=new BillInfo();
                    SentenceExec paymentinsert = new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                    for (final PaymentInfo p : ticket.getPayments()) {
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                intRefID=ticket.getReceiptRef();
                                setString(3, p.getName());
                                setDouble(4, intrest);
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });

                       

                        
                    }
                
                }
                
                return null;
            }
             
        };
        t.execute();
        //  if(flag1==0)
        //  return true;
        // else
        //    return false;
        if (perror == false ) {
        return rno;    
        //return receiptno;
        } else {
            return "false";
        }
    }
     /////////////////////////////////////////////////////////////////////////////
    public final String getNextReceiptID1(String createdby) throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String receiptnum;
        //System.out.println("getNextReceiptId1:::line 951 JPaymentSelect");
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();

        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(app.getSession() , "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = (SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ID=? AND ROLES.ID=PEOPLE.ROLE)  AND CATEGORY=(SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.NAME=? AND ROLES.ID=PEOPLE.ROLE) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            new StaticSentence(app.getSession() , "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});

            return receiptnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
            return null;
        }
    }
    
    //Added by GG
    public final String getNextReceiptIDg() throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String receiptnum;
        //String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        String createdby=BillList.gg;
        //System.out.println("createdby::::"+createdby);
        //Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.ID AND R1.ID=P1.ROLE AND P1.ROLE=? AND SEQUENCEDETAIL.CATEGORY=R.ID AND R.ID=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});

        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();
            //System.out.println("receiptnum:::::::"+receiptnum);
            BillList.gg=null;
       
            return receiptnum;
        } else {
                return null;
        }
        
    }    
//    public boolean checkReceiptSeries() throws BasicException
//    {
//        String creatBy=BillList.g.toString();
//        System.out.println("creatBy"+creatBy);
//        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
//        
//            Object[] obj = (Object[]) new StaticSentence(app.getSession() , "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND  P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, creatBy});
//            if(obj!=null)
//                return true;
//            else
//                return false;
//        
//            
//        
//    }
    //by pratima for intrest addition
    public void setIntrestAmount(Double amount){
    intrest=amount;
    }
    
    ///////////Added by ganesh
    public void InsertIntoGeneralReceiptBankCharges1(PaymentInfo returnPayment) throws BasicException{
         BillInfo ticket=new BillInfo();
             ticket.setID(UUID.randomUUID().toString());
             ticket.setPayments(m_aPaymentInfo.getPayments());
             ticket.setCustomer(customerext);
             ticket.setCreatedBy(app.getAppUserView().getUser().getName());
             ticket.setCreatedDate(new Date());
             ticket.setActiveCash(app.getActiveCashIndex());
             String AccountID="";  
          //    if(m_aPaymentInfo.getPayments().equals("magcard")){
            if( ticket.getPayments().get(0).getName().equals("magcard")){
             
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Bank Charges Collected Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+m_aPaymentInfo.getPayments().get(0).getOtherCharges());
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("CC");
                 payaccountForCard1(ticket, app.getInventoryLocation(),true);
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify bank charges account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
             
            }
             // }
             //  if(m_aPaymentInfo.getPayments().equals("magcard")){
               if(ticket.getPayments().size()>1){
            if( ticket.getPayments().get(1).getName().equals("magcard")){
                  if(ticket.getPayments().get(1)!=null){
             
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Bank Charges Collected Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+m_aPaymentInfo.getPayments().get(0).getOtherCharges());
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("CC");
                 payaccountForCard1(ticket, app.getInventoryLocation(),true);
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify bank charges account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
             
            }
               }
             }
                //if(m_aPaymentInfo.getPayments().equals("magcard")){
           if(ticket.getPayments().size()>2){
             if( ticket.getPayments().get(2).getName().equals("magcard")){
             if(ticket.getPayments().get(2)!=null){
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Bank Charges Collected Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+m_aPaymentInfo.getPayments().get(0).getOtherCharges());
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("CC");
                 payaccountForCard1(ticket, app.getInventoryLocation(),true);
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify bank charges account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
             
            }
            }
                }
    }
    
   // int flag1;
    // String receiptno = null;
     public final String payaccountForCard1(final BillInfo ticket, final String location, final boolean flag) throws BasicException {
       
         receiptno = null;
        Boolean perror = false;
        flag1=0;
        
        Transaction t = new Transaction(app.getSession()) {
            //  boolean error=false;

            public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                
                
                if (true) {
                    if (ticket.getReceiptRef() == null) {
                        final String rno = getNextReceiptID1(ticket.getCreatedBy());
                        receiptno = rno;
                        ticket.setReceiptRef(rno);
                        if (rno == null) {
                            flag1 = 1;
                            return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                    }
//                    else
//                    {
                    // new receipt
                    if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, "CC :"+ticket.getReceiptRef());//contains description for general receipts
                            }
                        });
                    } else {
                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                            }
                        });
                    }
//if( ticket.getPayments().get(2).getName().e"magcard")
// if(m_aPaymentInfo.getPayments().equals("magcard")){
                    SentenceExec paymentinsert = new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                    for (final PaymentInfo p : ticket.getPayments()) {
                        if(p.getName().equals("magcard")){
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                txRefID = ticket.getReceiptRef();
                                setString(3, p.getName());
                                setDouble(4, BillInfo.totalcardOtherCharge);
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });}

                       

                        
                    }
                }
                //}
                return null;
            }
            //  }
        };
        t.execute();
        BillInfo.totalcardOtherCharge=0.0;
        //  if(flag1==0)
        //  return true;
        // else
        //    return false;
        if (perror == false ) {
            return receiptno;
        } else {
            return "false";
        }
    }
     //Added By Ganesh
     public void InsertIntrestCharges1(PaymentInfo returnPayment) throws BasicException{
         BillInfo ticket=new BillInfo();
          
             ticket.setID(UUID.randomUUID().toString());
             ticket.setPayments(m_aPaymentInfo.getPayments());
             ticket.setCustomer(customerext);
             ticket.setCreatedBy(app.getAppUserView().getUser().getName());
             ticket.setCreatedDate(new Date());
             ticket.setActiveCash(app.getActiveCashIndex());
             String AccountID="";   
               if(ticket.getPayments().size()>1 && ticket.getPayments().size()<3){
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Pending Bill Intrest Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+intrest);
                 gggg=ticket.getFloor();
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("IA");
                 payaccountForIntrest1(ticket, app.getInventoryLocation());
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify Pending Bill Intrest Account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
               }
               
               
                if(ticket.getPayments().size()>2 && ticket.getPayments().size()<4){
             Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Pending Bill Intrest Account'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
             if(obj!=null){
                 AccountID=obj[0].toString();
                 ticket.setFloor(AccountID+"#"+intrest);
                 gggg=ticket.getFloor();
                 System.out.println(m_aPaymentInfo.getPayments().get(0).getTrack3());
                 ticket.setPlace("IA");
                 payaccountForIntrest1(ticket, app.getInventoryLocation());
             }
             else{
                  JOptionPane.showMessageDialog(null, "Please Specify Pending Bill Intrest Account in general table", "Cannot Create Receipt", JOptionPane.OK_OPTION);
             }
               }
           
             
    }
     
     
         public final String payaccountForIntrest1(final BillInfo ticket, final String location) throws BasicException {
       final String rno = getNextReceiptID1(ticket.getCreatedBy());
        // receiptno = null;//p
        
       Boolean perror = false;
      //  flag1=0;//p
        Transaction t = new Transaction(app.getSession()) {
            //  boolean error=false;

            public Object transact() throws BasicException {
                final Date date = new Date();
                String userid = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId();
                
               
                if (true) {
                    if (ticket.getReceiptRef() == null) {
                     //   final String rno = getNextReceiptID1(ticket.getCreatedBy());
                      //  receiptno = rno;//p
                        ticket.setReceiptRef(rno);
                        if (rno == null) {
                         //   flag1 = 1;//p
                            return false;

                        }
                    // ticket.setReceiptRef(getNextReceiptID());
                    }
//                    else {
                    // new receipt
                    //if (flag == true) {
                        //indication for guest entry and other direct income
                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER,PAYMENTREF,DESC_) VALUES (?,  ?, ?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
                                //  setString(2, ticket.getActiveCash());
                                setTimestamp(2, date); //Receipt date could be different from bill date
                                setString(3, ticket.getCreatedBy());
                                setString(4, ticket.getFloor());//contains type of income if its a guest entry or direct income
                                setString(5, "IA :"+ticket.getReceiptRef());//contains description for general receipts
                            }
                        });
//                    } else {
//                        new PreparedSentence(app.getSession(), "INSERT INTO RECEIPTS (ID,  DATENEW, RUSER) VALUES (?, ?, ?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {
//
//                            public void writeValues() throws BasicException {
//                                setString(1, ticket.getReceiptRef()); //Bill and receipt ID are the same
//                                //  setString(2, ticket.getActiveCash());
//                                setTimestamp(2, date); //Receipt date could be different from bill date
//                                setString(3, ticket.getCreatedBy());
//                            }
//                        });
//                    }
                        int fflag=1;
                        BillInfo ticket1=new BillInfo();
                    SentenceExec paymentinsert = new PreparedSentence(app.getSession(), "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, PUSER, PTIME, CUSTOMER) VALUES (?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);
                    for (final PaymentInfo p : ticket.getPayments()) {
                      //  if(ticket.getPayments().size()>1 && ticket.getPayments().size()<3){
                          //  if(ticket.getPayments().get(1)!=null){
                   if((ticket.getPayments().indexOf(p)>0) && (ticket.getPayments().indexOf(p)<2)){
                    //      if(ticket.getPayments().get(1)!=null){
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                intRefID=ticket.getReceiptRef();
                                
                                setString(3, p.getName());
                                
                                
//                                intRefID=ticket.getReceiptRef();
//                                setString(3, p.getName());
                                    setDouble(4, intrest);
                            
                                    
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });
                     }
                      //  }
                       // }
                       //  if(ticket.getPayments().size()>2){
                            //  if(ticket.getPayments().get(2)!=null){
                         if((ticket.getPayments().indexOf(p)>2) && (ticket.getPayments().indexOf(p)<4)){
                            //   if(ticket.getPayments().get(2)!=null){
                        paymentinsert.exec(new DataParams() {

                            public void writeValues() throws BasicException {
                                setString(1, UUID.randomUUID().toString());
                                setString(2, ticket.getReceiptRef());
                                intRefID=ticket.getReceiptRef();
                                setString(3, p.getName());
                                
//                                intRefID=ticket.getReceiptRef();
//                                setString(3, p.getName());
                                     
                                    setDouble(4, intrest);
                                     
                                setString(5, ticket.getCreatedBy());
                                setTimestamp(6, date);
                                setString(7, ticket.getCustomerId());
                            }
                        });
                     // }
                   }
                   // }
fflag--;
                        
                    }
                
                       
                        
                }
                
                return null;
            }
             
        };
        t.execute();
        //  if(flag1==0)
        //  return true;
        // else
        //    return false;
        if (perror == false ) {
        return rno;    
        //return receiptno;
        } else {
            return "false";
        }
        
        //Ended by Ganesh
        
    }
}
