
package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import java.awt.Component;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;

/**
 *
 * @author adrianromero
 */
public class JPanelConfigPayment extends javax.swing.JPanel implements PanelConfig {

    private DirtyManager dirty = new DirtyManager();
    
    /** Creates new form JPanelConfigPayment */
    public JPanelConfigPayment() {
        
        initComponents();
        
        // dirty manager
        jtxtCommerceID.getDocument().addDocumentListener(dirty);
        jtxtCommercePwd.getDocument().addDocumentListener(dirty);
        jcboCardReader.addActionListener(dirty);
        jcboPaymentGateway.addActionListener(dirty);
        jchkPaymentTest.addActionListener(dirty);
        
        // Payment Provider
        jcboPaymentGateway.addItem("Not defined");
        jcboPaymentGateway.addItem("external");
        jcboPaymentGateway.addItem("SECPay");
        jcboPaymentGateway.addItem("AuthorizeNet");    
        
        // Lector de tarjetas.
        jcboCardReader.addItem("Not defined");
        jcboCardReader.addItem("Generic");
        jcboCardReader.addItem("Intelligent");
        jcboCardReader.addItem("Keyboard");
        
    }
    
    public boolean hasChanged() {
        return dirty.isDirty();
    }
    
    public Component getConfigComponent() {
        return this;
    }
   
    public void loadProperties(AppConfig config) {
        
        jcboCardReader.setSelectedItem(config.getProperty("payment.magcardreader"));
        jcboPaymentGateway.setSelectedItem(config.getProperty("payment.gateway"));
        jchkPaymentTest.setSelected(Boolean.valueOf(config.getProperty("payment.testmode")).booleanValue());
        jtxtCommerceID.setText(config.getProperty("payment.commerceid"));        
        jtxtCommercePwd.setText(config.getProperty("payment.commercepassword"));        
        
        dirty.setDirty(false);
    }
   
    public void saveProperties(AppConfig config) {
        
        config.setProperty("payment.magcardreader", comboValue(jcboCardReader.getSelectedItem()));
        config.setProperty("payment.gateway", comboValue(jcboPaymentGateway.getSelectedItem()));
        config.setProperty("payment.testmode", Boolean.toString(jchkPaymentTest.isSelected()));
        config.setProperty("payment.commerceid", jtxtCommerceID.getText());
        config.setProperty("payment.commercepassword", new String(jtxtCommercePwd.getPassword()));
        
        dirty.setDirty(false);
    }    
     
    private String comboValue(Object value) {
        return value == null ? "" : value.toString();
    }   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcboCardReader = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jcboPaymentGateway = new javax.swing.JComboBox();
        jchkPaymentTest = new javax.swing.JCheckBox();
        jtxtCommercePwd = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtxtCommerceID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(680, 165));
        setLayout(null);
        add(jcboCardReader);
        jcboCardReader.setBounds(150, 130, 240, 20);

        jLabel11.setText(AppLocal.getIntString("label.magcardreader")); // NOI18N
        add(jLabel11);
        jLabel11.setBounds(20, 130, 130, 14);

        jLabel13.setText(AppLocal.getIntString("label.paymentgateway")); // NOI18N
        add(jLabel13);
        jLabel13.setBounds(20, 100, 130, 14);
        add(jcboPaymentGateway);
        jcboPaymentGateway.setBounds(150, 100, 240, 20);

        jchkPaymentTest.setText(AppLocal.getIntString("label.paymenttestmode")); // NOI18N
        add(jchkPaymentTest);
        jchkPaymentTest.setBounds(400, 100, 130, 20);
        add(jtxtCommercePwd);
        jtxtCommercePwd.setBounds(150, 70, 180, 20);

        jLabel14.setText(AppLocal.getIntString("label.commercepwd")); // NOI18N
        add(jLabel14);
        jLabel14.setBounds(20, 70, 130, 14);

        jLabel12.setText(AppLocal.getIntString("label.commerceid")); // NOI18N
        add(jLabel12);
        jLabel12.setBounds(20, 40, 130, 14);
        add(jtxtCommerceID);
        jtxtCommerceID.setBounds(150, 40, 180, 20);

        jLabel10.setText(AppLocal.getIntString("Label.Payment")); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        add(jLabel10);
        jLabel10.setBounds(20, 10, 660, 15);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JComboBox jcboCardReader;
    private javax.swing.JComboBox jcboPaymentGateway;
    private javax.swing.JCheckBox jchkPaymentTest;
    private javax.swing.JTextField jtxtCommerceID;
    private javax.swing.JPasswordField jtxtCommercePwd;
    // End of variables declaration//GEN-END:variables
    
}
