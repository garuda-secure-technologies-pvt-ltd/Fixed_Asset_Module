

package com.openbravo.pos.panels;
import java.awt.Component;
import java.util.UUID;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.format.Formats;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.forms.AppView;
import java.util.Date;

/**
 *
 * @author adrianromero
 */
public class PaymentsEditor extends javax.swing.JPanel implements EditorRecord {
    
    private ComboBoxValModel m_ReasonModel;
    
    private String m_sId;
    private String m_sPaymentId;
    private Date datenew;
   
    private AppView m_App;
    
    /** Creates new form JPanelPayments */
    public PaymentsEditor(AppView oApp, DirtyManager dirty) {
        
        m_App = oApp;
        
        initComponents();
       
        m_ReasonModel = new ComboBoxValModel();
        m_ReasonModel.add(new PaymentReasonPositive("cashin", AppLocal.getIntString("transpayment.cashin")));
        m_ReasonModel.add(new PaymentReasonNegative("cashout", AppLocal.getIntString("transpayment.cashout")));              
        m_jreason.setModel(m_ReasonModel);

        m_jreason.addActionListener(dirty);
        m_jtotal.getDocument().addDocumentListener(dirty);

        writeValueEOF();
    }
    
    public void writeValueEOF() {
        m_sId = null;
        m_sPaymentId = null;
        datenew = null;
        setReasonTotal(null, null);
        m_jreason.setEnabled(false);
        m_jtotal.setEnabled(false);
    }  
    
    public void writeValueInsert() {
        m_sId = null;
        m_sPaymentId = null;
        datenew = null;
        setReasonTotal(null, null);
        m_jreason.setEnabled(true);
        m_jtotal.setEnabled(true);
    }
    
    public void writeValueDelete(Object value) {
        Object[] payment = (Object[]) value;
        m_sId = (String) payment[0];
        datenew = (Date) payment[2];
        m_sPaymentId = (String) payment[3];
        setReasonTotal(payment[4], payment[5]);
        m_jreason.setEnabled(false);
        m_jtotal.setEnabled(false);
    }
    
    public void writeValueEdit(Object value) {
        Object[] payment = (Object[]) value;
        m_sId = (String) payment[0];
        datenew = (Date) payment[2];
        m_sPaymentId = (String) payment[3];
        setReasonTotal(payment[4], payment[5]);
        m_jreason.setEnabled(false);
        m_jtotal.setEnabled(false);
    }
    
    public Object createValue() throws BasicException {
        Object[] payment = new Object[8];
        payment[0] = m_sId == null ? UUID.randomUUID().toString() : m_sId;
        payment[1] = m_App.getActiveCashIndex();
        payment[2] = datenew == null ? new Date() : datenew;
        payment[3] = m_sPaymentId == null ? UUID.randomUUID().toString() : m_sPaymentId;
        payment[4] = m_ReasonModel.getSelectedKey();

        PaymentReason reason = (PaymentReason) m_ReasonModel.getSelectedItem();
        Double dtotal = (Double) Formats.CURRENCY.parseValue(m_jtotal.getText());
        payment[5] = reason == null ? dtotal : reason.addSignum(dtotal);
        payment[6]="";
         payment[7]=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        return payment;
    }
    
    public Component getComponent() {
        return this;
    }
    
    private void setReasonTotal(Object reasonfield, Object totalfield) {
        
        m_ReasonModel.setSelectedKey(reasonfield);
        
        PaymentReason reason = (PaymentReason) m_ReasonModel.getSelectedItem();     
        
        Double dtotal;
        if (reason == null) {
            dtotal = (Double) totalfield;
        } else {
            dtotal = reason.positivize((Double) totalfield);
        }

        m_jtotal.setText(Formats.CURRENCY.formatValue(dtotal));  
    }
    
    private static abstract class PaymentReason implements IKeyed {
        private String m_sKey;
        private String m_sText;
        
        public PaymentReason(String key, String text) {
            m_sKey = key;
            m_sText = text;
        }
        public Object getKey() {
            return m_sKey;
        }
        public abstract Double positivize(Double d);
        public abstract Double addSignum(Double d);
        
        @Override
        public String toString() {
            return m_sText;
        }
    }
    private static class PaymentReasonPositive extends PaymentReason {
        public PaymentReasonPositive(String key, String text) {
            super(key, text);
        }
        public Double positivize(Double d) {
            return d;
        }
        public Double addSignum(Double d) {
            if (d == null) {
                return null;
            } else if (d.doubleValue() < 0.0) {
                return new Double(-d.doubleValue());
            } else {
                return d;
            }
        }
    }
    private static class PaymentReasonNegative extends PaymentReason {
        public PaymentReasonNegative(String key, String text) {
            super(key, text);
        }
        public Double positivize(Double d) {
            return d == null ? null : new Double(-d.doubleValue());
        }
        public Double addSignum(Double d) {
            if (d == null) {
                return null;
            } else if (d.doubleValue() > 0.0) {
                return new Double(-d.doubleValue());
            } else {
                return d;
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

        m_jreason = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        m_jtotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setLayout(null);

        m_jreason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jreasonActionPerformed(evt);
            }
        });
        add(m_jreason);
        m_jreason.setBounds(160, 30, 200, 20);

        jLabel2.setText(AppLocal.getIntString("label.paymentreason")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(10, 30, 150, 14);

        m_jtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(m_jtotal);
        m_jtotal.setBounds(160, 60, 70, 20);

        jLabel3.setText(AppLocal.getIntString("label.paymenttotal")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(10, 60, 150, 14);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jreasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jreasonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jreasonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox m_jreason;
    private javax.swing.JTextField m_jtotal;
    // End of variables declaration//GEN-END:variables
    
}
