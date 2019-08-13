

package com.openbravo.pos.sales;

import java.awt.CardLayout;
import javax.swing.JOptionPane;
import com.openbravo.pos.forms.AppLocal;

public class JTicketsBagTicketBag extends javax.swing.JPanel {
    
    private JTicketsBagTicket m_ticketsbagticket;
    
    /** Creates new form JTicketsBagTicketBag */
    public JTicketsBagTicketBag(JTicketsBagTicket ticketsbagticket) {
        m_ticketsbagticket = ticketsbagticket;
        initComponents();
    }
    
    public void showEdit() {
        showView("edit");
    }
    
    public void showRefund() {
        showView("refund");
    }
    
    private void showView(String view) {
        CardLayout cl = (CardLayout)(getLayout());
        cl.show(this, view);  
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanEdit = new javax.swing.JPanel();
        m_jBtnDelete = new javax.swing.JButton();
        m_jBtnCancel = new javax.swing.JButton();
        jPanRefund = new javax.swing.JPanel();
        m_jBtnCancel1 = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        jPanEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        m_jBtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editdelete.png"))); // NOI18N
        m_jBtnDelete.setText(AppLocal.getIntString("Button.DeleteTicket")); // NOI18N
        m_jBtnDelete.setFocusPainted(false);
        m_jBtnDelete.setFocusable(false);
        m_jBtnDelete.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jBtnDelete.setRequestFocusEnabled(false);
        m_jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jBtnDeleteActionPerformed(evt);
            }
        });
        jPanEdit.add(m_jBtnDelete);

        m_jBtnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileclose.png"))); // NOI18N
        m_jBtnCancel.setText(AppLocal.getIntString("Button.Cancel")); // NOI18N
        m_jBtnCancel.setFocusPainted(false);
        m_jBtnCancel.setFocusable(false);
        m_jBtnCancel.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jBtnCancel.setRequestFocusEnabled(false);
        m_jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jBtnCancelActionPerformed(evt);
            }
        });
        jPanEdit.add(m_jBtnCancel);

        add(jPanEdit, "edit");

        jPanRefund.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        m_jBtnCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileclose.png"))); // NOI18N
        m_jBtnCancel1.setText(AppLocal.getIntString("Button.Cancel")); // NOI18N
        m_jBtnCancel1.setFocusPainted(false);
        m_jBtnCancel1.setFocusable(false);
        m_jBtnCancel1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jBtnCancel1.setRequestFocusEnabled(false);
        m_jBtnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jBtnCancel1ActionPerformed(evt);
            }
        });
        jPanRefund.add(m_jBtnCancel1);

        add(jPanRefund, "refund");
    }// </editor-fold>//GEN-END:initComponents

    private void m_jBtnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBtnCancel1ActionPerformed

        m_ticketsbagticket.canceleditionTicket();

    }//GEN-LAST:event_m_jBtnCancel1ActionPerformed

    private void m_jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBtnDeleteActionPerformed
        
        int res = JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.wannadelete"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            m_ticketsbagticket.deleteTicket();
        }
        
    }//GEN-LAST:event_m_jBtnDeleteActionPerformed

    private void m_jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBtnCancelActionPerformed

        m_ticketsbagticket.canceleditionTicket();
        
    }//GEN-LAST:event_m_jBtnCancelActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanEdit;
    private javax.swing.JPanel jPanRefund;
    private javax.swing.JButton m_jBtnCancel;
    private javax.swing.JButton m_jBtnCancel1;
    private javax.swing.JButton m_jBtnDelete;
    // End of variables declaration//GEN-END:variables
    
}
