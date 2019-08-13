

package com.openbravo.pos.payment;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import com.openbravo.pos.forms.AppLocal;
import javax.swing.JOptionPane;
import com.openbravo.pos.forms.AppView;
import java.text.DecimalFormat;
/**
 *
 * @author adrianromero
 */
public class PaymentPanelType extends javax.swing.JPanel implements PaymentPanel {
    
    private double m_dTotal;
    private String m_sTransactionID;
    private JPaymentNotifier m_notifier;
    private AppView m_App;
    private Double BankChargePerc=0.00;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private Double OtherCharges;
    
    /** Creates new form JPaymentCash */
    public PaymentPanelType(JPaymentNotifier notifier) {
        
        m_notifier = notifier;
        
        initComponents();  
        
        m_jHolderName.addPropertyChangeListener("Edition", new RecalculateName());
        m_jCardNumber.addPropertyChangeListener("Edition", new RecalculateName());
        m_jExpirationDate.addPropertyChangeListener("Edition", new RecalculateName());
        
        
        m_jHolderName.addEditorKeys(m_jKeys);
        m_jCardNumber.addEditorKeys(m_jKeys);
        m_jExpirationDate.addEditorKeys(m_jKeys);

    }
    
    public JComponent getComponent(){
        return this;
    }
    
    public void activate(String sTransaction, double dTotal,AppView App) {
        
        m_sTransactionID = sTransaction;
        m_dTotal = dTotal;
        m_App=App;
        resetState();
         
        try{
            BankChargePerc = getBankCharges();
        }
        catch(BasicException e){
          e.printStackTrace();
        }
        Double BankAmount = 0.00;
        OtherCharges=0.00;
        if(BankChargePerc>0.00){
           BankAmount=(m_dTotal*BankChargePerc)/100;
        }
        OtherCharges=BankAmount;
        BankCharge_Text.setText(decimalFormat.format(BankAmount));
        Double TotalAmount=BankAmount+m_dTotal;
        totalAmount_Text.setText(decimalFormat.format(TotalAmount));
        
        
        m_jHolderName.activate();
    }
    
    private void resetState() {
        
        m_notifier.setStatus(false, false);  
              
        m_jHolderName.setText(null);
        m_jCardNumber.setText(null);
        m_jExpirationDate.setText(null);
    }
    
    public PaymentInfoMagcard getPaymentInfoMagcard() {

     if(m_jHolderName.getText()!=null && m_jCardNumber.getText()!=null  && m_jExpirationDate.getText()!=null )   {
        if(m_jHolderName.getText().trim().length()>0  && m_jCardNumber.getText().trim().length()>0 && m_jExpirationDate.getText().trim().length()>0){
                
            
                if (m_dTotal > 0.0) {
                    return new PaymentInfoMagcard(
                            m_jHolderName.getText(),
                            m_jCardNumber.getText(), 
                            m_jExpirationDate.getText(),
                            null,
                            null,                    
                            null,                    
                            m_sTransactionID,
                            m_dTotal,OtherCharges);
                } else {
                    return new PaymentInfoMagcardRefund(
                            m_jHolderName.getText(),
                            m_jCardNumber.getText(), 
                            m_jExpirationDate.getText(),
                            null,
                            null,                    
                            null,                    
                            m_sTransactionID,
                            m_dTotal,OtherCharges);
                }
        
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter all details", "", JOptionPane.OK_OPTION);
        }
     }
     else{
         JOptionPane.showMessageDialog(this, "Please enter all details", "", JOptionPane.OK_OPTION);
     }
     return null;
    }    
    
    private class RecalculateName implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            boolean isvalid = isValidHolder() && isValidCardNumber() && isValidExpirationDate();
            m_notifier.setStatus(isvalid, isvalid);
        }
    }  
    
    private boolean isValidHolder() {
        return !(m_jHolderName.getText() == null || m_jHolderName.getText().equals(""));
    }
    private boolean isValidCardNumber() {
        return !(m_jCardNumber.getText() == null || m_jCardNumber.getText().length() != 16);
    }
    private boolean isValidExpirationDate() {
        return !(m_jExpirationDate.getText() == null || m_jExpirationDate.getText().length() != 4);
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        m_jKeys = new com.openbravo.editor.JEditorKeys();
        jPanel4 = new javax.swing.JPanel();
        m_jCardNumber = new com.openbravo.editor.JEditorStringNumber();
        m_jExpirationDate = new com.openbravo.editor.JEditorStringNumber();
        m_jHolderName = new com.openbravo.editor.JEditorString();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BankCharge_Text = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalAmount_Text = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jPanel1.add(m_jKeys);

        jPanel2.add(jPanel1, java.awt.BorderLayout.NORTH);

        add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel4.setLayout(null);
        jPanel4.add(m_jCardNumber);
        m_jCardNumber.setBounds(110, 50, 180, 25);
        jPanel4.add(m_jExpirationDate);
        m_jExpirationDate.setBounds(110, 80, 110, 25);
        jPanel4.add(m_jHolderName);
        m_jHolderName.setBounds(110, 20, 180, 25);

        jLabel8.setText(AppLocal.getIntString("label.cardholder")); // NOI18N
        jPanel4.add(jLabel8);
        jLabel8.setBounds(20, 20, 90, 14);

        jLabel6.setText(AppLocal.getIntString("label.cardnumber")); // NOI18N
        jPanel4.add(jLabel6);
        jLabel6.setBounds(20, 50, 90, 14);

        jLabel7.setText(AppLocal.getIntString("label.cardexpdate")); // NOI18N
        jPanel4.add(jLabel7);
        jLabel7.setBounds(20, 80, 120, 14);

        jLabel2.setText("Addition Bank Charges : ");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(20, 130, 180, 14);

        BankCharge_Text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BankCharge_Text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(BankCharge_Text);
        BankCharge_Text.setBounds(200, 130, 110, 26);

        jLabel3.setText("Addition Bank Charges : ");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(20, 130, 180, 14);

        jLabel4.setText("Total Payable Amount :  ");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(20, 180, 180, 20);

        totalAmount_Text.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        totalAmount_Text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel4.add(totalAmount_Text);
        totalAmount_Text.setBounds(200, 180, 110, 26);

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jLabel1.setText(AppLocal.getIntString("message.paymentgatewaytype")); // NOI18N
        jPanel5.add(jLabel1);

        add(jPanel5, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BankCharge_Text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private com.openbravo.editor.JEditorStringNumber m_jCardNumber;
    private com.openbravo.editor.JEditorStringNumber m_jExpirationDate;
    private com.openbravo.editor.JEditorString m_jHolderName;
    private com.openbravo.editor.JEditorKeys m_jKeys;
    private javax.swing.JTextField totalAmount_Text;
    // End of variables declaration//GEN-END:variables
    
  public Double getBankCharges() throws BasicException{
      Double BankChargePercx=0.00;
      Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Bank Charges for bar - percentage'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find();
      if(obj1!=null){
          if(obj1[0]!=null && obj1[0].toString().trim().length()>0){
              String x = obj1[0].toString();
              BankChargePercx= Double.parseDouble(x);
          }
      }
   
      return BankChargePercx;
  }
  
  
}
