

package com.openbravo.pos.inventory;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ListModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.user.BrowsableEditableData;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.scanpal2.DeviceScanner;
import com.openbravo.pos.scanpal2.DeviceScannerException;

/**
 *
 * @author adrianromero
 */
public class JDlgUploadProducts extends javax.swing.JDialog {
    
    // private AppView m_App;
    private DeviceScanner m_scanner;
    private BrowsableEditableData m_bd;
    
    /** Creates new form JDlgUploadProducts */
    private JDlgUploadProducts(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    /** Creates new form JDlgUploadProducts */
    private JDlgUploadProducts(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
    }       

    private void init(DeviceScanner scanner, BrowsableEditableData bd) {
        
        initComponents();

        getRootPane().setDefaultButton(jcmdOK);   
   
        m_scanner = scanner;
        m_bd = bd;
        
        //show();
        setVisible(true);
        
        // return;
    }
    
    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }    
    
    public static void showMessage(Component parent, DeviceScanner scanner, BrowsableEditableData bd) {
         
        Window window = getWindow(parent);      
        
        JDlgUploadProducts myMsg;
        if (window instanceof Frame) { 
            myMsg = new JDlgUploadProducts((Frame) window, true);
        } else {
            myMsg = new JDlgUploadProducts((Dialog) window, true);
        }
        
        myMsg.init(scanner, bd);
    }         
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jcmdOK = new javax.swing.JButton();
        jcmdCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("caption.upload"));
        setResizable(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jcmdOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png")));
        jcmdOK.setText(AppLocal.getIntString("Button.OK"));
        jcmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOKActionPerformed(evt);
            }
        });

        jPanel2.add(jcmdOK);

        jcmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png")));
        jcmdCancel.setText(AppLocal.getIntString("Button.Cancel"));
        jcmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdCancelActionPerformed(evt);
            }
        });

        jPanel2.add(jcmdCancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(null);

        jLabel1.setText(AppLocal.getIntString("message.preparescanner"));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 30, 420, 15);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-474)/2, (screenSize.height-161)/2, 474, 161);
    }// </editor-fold>//GEN-END:initComponents

    private void jcmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdCancelActionPerformed

        dispose();
    }//GEN-LAST:event_jcmdCancelActionPerformed

    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed
       
        
        // Ponemos el estado de leyendo productos
        String stext = jLabel1.getText();
        jLabel1.setText("Subiendo productos. Espere un momento.");
        jcmdOK.setEnabled(false);
        jcmdCancel.setEnabled(false);          
                
        // Ejecutamos la descarga...
        try {
            m_scanner.connectDevice();
            m_scanner.startUploadProduct();
            
            ListModel l = m_bd.getListModel();
            for (int i = 0; i < l.getSize(); i++) {
                Object[] myprod = (Object[]) l.getElementAt(i);
                m_scanner.sendProduct(
                        (String) myprod[2], // nombre
                        (String) myprod[1], // codigo de barras
                        (Double) myprod[5]
                        ); // precio de compra                
            }
            m_scanner.stopUploadProduct();
            MessageInf msg = new MessageInf(MessageInf.SGN_SUCCESS, AppLocal.getIntString("message.scannerok"));
            msg.show(this);            
        } catch (DeviceScannerException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.scannerfail"), e);
            msg.show(this);            
        } finally {
            m_scanner.disconnectDevice();
        }
        
        // Deshacemos el estado de leyendo productos
        jLabel1.setText(stext);
        jcmdOK.setEnabled(true);
        jcmdCancel.setEnabled(true);
        
        dispose();

    }//GEN-LAST:event_jcmdOKActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jcmdCancel;
    private javax.swing.JButton jcmdOK;
    // End of variables declaration//GEN-END:variables
    
}