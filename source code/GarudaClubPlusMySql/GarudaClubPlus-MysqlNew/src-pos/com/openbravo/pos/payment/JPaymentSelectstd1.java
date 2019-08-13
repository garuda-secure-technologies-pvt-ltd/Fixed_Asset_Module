

package com.openbravo.pos.payment;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
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
import com.openbravo.pos.payment.JPaymentCashPos;
import com.openbravo.pos.sales.JPanelTicket;
import com.openbravo.pos.sales.JTicketLines1;
import com.openbravo.pos.sales.JTicketsBag;
import com.openbravo.pos.sales.TicketsEditor;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author adrianromero
 */
public abstract class JPaymentSelectstd1 extends javax.swing.JDialog implements JPaymentNotifier {
    
     private PaymentInfoList m_aPaymentInfo;
     private boolean printselected;
    
     private boolean accepted;
    
     private AppView app;
     private double m_dTotal; 
     private double m_dTotal1; 
     private CustomerInfoExt customerext;
     private DataLogicSystem dlSystem;
     private DataLogicSales m_dlSales;
     public JPaymentCreator jpay;
      protected Object m_oTicketExt;
     private TicketInfo m_oTicket;
      protected JTicketLines1 m_ticketlines;
       private List<TicketLineInfo> m_aLines;
       private JTicketsBag m_ticketsbag;
     private boolean flag;


    
     
    
    private Map<String, JPaymentInterface> payments = new HashMap<String, JPaymentInterface>();

    
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectstd1(java.awt.Frame parent, boolean modal, ComponentOrientation o) {
        super(parent, modal);
        initComponents();    
        
        this.applyComponentOrientation(o);
        
        getRootPane().setDefaultButton(m_jButtonOK); 
    }
    /** Creates new form JPaymentSelect */
    protected JPaymentSelectstd1(java.awt.Dialog parent, boolean modal, ComponentOrientation o) {
        super(parent, modal);
        initComponents();       
        
        this.applyComponentOrientation(o);
        
        getRootPane().setDefaultButton(m_jButtonOK); 
    }    
    
    public void init(AppView app) {
        this.app = app;
        dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        printselected = true;
         m_oTicket = null;
        m_oTicketExt = null;
      
       
  
       
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
                                
   
   
   
    
          
    public boolean showDialog(double total, CustomerInfoExt customerext,String createdby,boolean flag,String type,PaymentInfo pin) {
         
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
          /* SHIV: For generating paymentpanel in configuration standard for stdbilling in Restaurant and Bar Billing (sales) */
           temp=true;
           m_aPaymentInfo = new PaymentInfoList();              
           accepted=false;
           m_jTotalEuros.setText(Formats.CURRENCY.formatValue(total));
           addTabs(temp); 
           m_dTotal = total;
          if (m_jTabPayment.getSelectedComponent() != null) {
            ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).activate(customerext, total- m_aPaymentInfo.getTotal(),app);
              
                      }
          //////////////
        //  if (m_jTabPayment.getSelectedComponent() != null) {
        // ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).executePayment().copyPayment();
             
            //  addTabPayment(new JPaymentSelectstd1.JPaymentDebtCreator());
              
           
            
            
           
          //}
          ///////////////////////
             printState();
            
             if (m_jTabPayment.getTabCount() == 0) {
            // No payment panels available            
            m_aPaymentInfo.add(getDefaultPayment(total));
           
            accepted = true; 
              }
             else{
                 /*payment panel is visible*/
                 setVisible(true);
                 
                 
                }
              //     m_jTabPayment.removeAll();
                   return accepted;
              
         }
        }else{
           this.customerext = customerext;
           temp=false;
        }
        if(customerext.getMemtype()==null || customerext.getMemtype().equals(""))
            temp=false;
         m_aPaymentInfo = new PaymentInfoList();
        accepted = false;

        m_dTotal = total;
        m_jButtonPrint.setSelected(printselected);
        m_jTotalEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal)));
        
        addTabs(temp);
        
        if (m_jTabPayment.getTabCount() == 0) {
            // No payment panels available            
            m_aPaymentInfo.add(getDefaultPayment(total));
            accepted = true;     
       
        } else {  
                  
          //shiv commented on 12sept  printState();
            setVisible(true);
        }
        
        // gets the print button state
        printselected = m_jButtonPrint.isSelected();
        
        // remove all tabs        
        m_jTabPayment.removeAll();
         return accepted;
    }
 
    /////////////////////
    
    ////////////////////
    
    protected abstract void addTabs(boolean temp);
    protected abstract void setStatusPanel(boolean isPositive, boolean isComplete);
    protected abstract PaymentInfo getDefaultPayment(double total);
    protected abstract PaymentTicketInfo getDefaultPaymentTicket(double total);
   
     
    
   
    
    protected void setOKEnabled(boolean value) {
        m_jButtonOK.setEnabled(value);        
    }
    
    protected void setAddEnabled(boolean value) {
        m_jButtonAdd.setEnabled(value);
    }
        
  protected void addTabPayment(JPaymentCreator jpay) {
      try{
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
        }else{
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
      catch(Exception e){
    
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
            return new JPaymentCashPos(JPaymentSelectstd1.this, dlSystem);
        }
        public String getKey() { return "payment.cash"; }
        public String getLabelKey() { return "tab.cash"; }
        public String getIconKey() { return "/com/openbravo/images/cash.png"; }
    }
        
    public class JPaymentChequeCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentCheque(JPaymentSelectstd1.this);
        }
        public String getKey() { return "payment.cheque"; }
        public String getLabelKey() { return "tab.cheque"; }
        public String getIconKey() { return "/com/openbravo/images/desktop.png"; }
    }  
        
    public class JPaymentPaperCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentPaper(JPaymentSelectstd1.this, "paperin");
        }
        public String getKey() { return "payment.paper"; }
        public String getLabelKey() { return "tab.paper"; }
        public String getIconKey() { return "/com/openbravo/images/knotes.png"; }
    }
   
    public class JPaymentMagcardCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentMagcard(app, JPaymentSelectstd1.this);
        }
        public String getKey() { return "payment.magcard"; }
        public String getLabelKey() { return "tab.magcard"; }
        public String getIconKey() { return "/com/openbravo/images/vcard.png"; }
    }
        
    public class JPaymentFreeCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentFree(JPaymentSelectstd1.this);
        }
        public String getKey() { return "payment.free"; }
        public String getLabelKey() { return "tab.free"; }
        public String getIconKey() { return "/com/openbravo/images/package_toys.png"; }
    }
        
    public class JPaymentDebtCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentDebt(JPaymentSelectstd1.this);
        }
        public String getKey() { return "payment.debt"; }
        public String getLabelKey() { return "tab.debt"; }
        public String getIconKey() { return "/com/openbravo/images/kdmconfig32.png"; }
    }   
        
    public class JPaymentCashRefundCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentRefund(JPaymentSelectstd1.this, "cashrefund");
        }
        public String getKey() { return "refund.cash"; }
        public String getLabelKey() { return "tab.cashrefund"; }
        public String getIconKey() { return "/com/openbravo/images/cash.png"; }
    }
        
    public class JPaymentChequeRefundCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentRefund(JPaymentSelectstd1.this, "chequerefund");
        }
        public String getKey() { return "refund.cheque"; }
        public String getLabelKey() { return "tab.chequerefund"; }
        public String getIconKey() { return "/com/openbravo/images/desktop.png"; }
    }
       
    public class JPaymentPaperRefundCreator implements JPaymentCreator {
        public JPaymentInterface createJPayment() {
            return new JPaymentRefund(JPaymentSelectstd1.this, "paperout");
        }
        public String getKey() { return "refund.paper"; }
        public String getLabelKey() { return "tab.paper"; }
        public String getIconKey() { return "/com/openbravo/images/knotes.png"; }
    }    
       
    public class JPaymentMagcardRefundCreator implements JPaymentCreator {
       public JPaymentInterface createJPayment() {     
            return new JPaymentMagcard(app, JPaymentSelectstd1.this);
        }
        public String getKey() { return "refund.magcard"; }
        public String getLabelKey() { return "tab.magcard"; }
        public String getIconKey() { return "/com/openbravo/images/vcard.png"; }
    }    
    
    protected void setHeaderVisible(boolean value) {
        jPanel6.setVisible(value);
    }
    
    private void printState() {
   
     try{   
       m_jRemaininglEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal - m_aPaymentInfo.getTotal())));
       m_jButtonRemove.setEnabled(!m_aPaymentInfo.isEmpty());
        m_jTabPayment.setSelectedIndex(0); // selecciono el primero
        ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).activate(customerext, m_dTotal - m_aPaymentInfo.getTotal(),app);
     }
     catch(Exception e){
         
     }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("payment.title")); // NOI18N
        setResizable(false);

        m_jLblTotalEuros1.setText(AppLocal.getIntString("label.totalcash")); // NOI18N
        jPanel4.add(m_jLblTotalEuros1);

        m_jTotalEuros.setBackground(java.awt.Color.white);
        m_jTotalEuros.setFont(new java.awt.Font("Dialog", 1, 14));
        m_jTotalEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jTotalEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jTotalEuros.setOpaque(true);
        m_jTotalEuros.setPreferredSize(new java.awt.Dimension(125, 25));
        m_jTotalEuros.setRequestFocusEnabled(false);
        jPanel4.add(m_jTotalEuros);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        m_jLblRemainingEuros.setText(AppLocal.getIntString("label.remainingcash")); // NOI18N
        jPanel6.add(m_jLblRemainingEuros);

        m_jRemaininglEuros.setBackground(java.awt.Color.white);
        m_jRemaininglEuros.setFont(new java.awt.Font("Dialog", 1, 14));
        m_jRemaininglEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jRemaininglEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jRemaininglEuros.setOpaque(true);
        m_jRemaininglEuros.setPreferredSize(new java.awt.Dimension(125, 25));
        m_jRemaininglEuros.setRequestFocusEnabled(false);
        jPanel6.add(m_jRemaininglEuros);

        m_jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/btnplus.png"))); // NOI18N
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

        getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-672)/2, (screenSize.height-497)/2, 672, 497);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonRemoveActionPerformed
     
       
        m_aPaymentInfo.removeLast();
        printState();
       
    }//GEN-LAST:event_m_jButtonRemoveActionPerformed

    private void m_jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonAddActionPerformed
         m_jButtonAdd.setEnabled(false);
    /* PaymentInfo returnPayment = ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).executePayment();
        if (returnPayment != null) {
            m_aPaymentInfo.add(returnPayment);
           printState();
                                    }
       
     */
        
        
    }//GEN-LAST:event_m_jButtonAddActionPerformed

    private void m_jTabPaymentStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m_jTabPaymentStateChanged
        
        if (m_jTabPayment.getSelectedComponent() != null) {
            ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).activate(customerext, m_dTotal - m_aPaymentInfo.getTotal(),app);
        }
        
    }//GEN-LAST:event_m_jTabPaymentStateChanged

    private void m_jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonOKActionPerformed
            
         int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("Bill cannot be reversed.Do You wish to continue.?"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) { 
        PaymentInfo returnPayment = ((JPaymentInterface) m_jTabPayment.getSelectedComponent()).executePayment();
         if (returnPayment != null) {
            m_aPaymentInfo.add(returnPayment);
            accepted = true;
            dispose();
        }
        }else{
            
             }
      
        
    }//GEN-LAST:event_m_jButtonOKActionPerformed

    private void m_jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonCancelActionPerformed
       
       dispose();
         
      
        
    }//GEN-LAST:event_m_jButtonCancelActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
    
}
