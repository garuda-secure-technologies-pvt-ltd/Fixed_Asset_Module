/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Jpendingremarkedit.java
 *
 * Created on Jan 17, 2009, 3:12:25 PM
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;

/**
 *
 * @author swathi
 */
public class Jpendingremarkedit extends javax.swing.JDialog {
    private String qtidtemp;
    private  AppView m_app;
    private JPanelCloseSale obj=new JPanelCloseSale();

    /** Creates new form Jpendingremarkedit */
    private Jpendingremarkedit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    /** Creates new form JProductLineEdit */
    private Jpendingremarkedit(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
    }
     private boolean init(AppView app, String reason,String qtid) {
        // Inicializo los componentes
        initComponents();
        qtidtemp=qtid;
        m_app=app;


       // jRemarks.setEnabled(app.getAppUserView().getUser().hasPermission("com.openbravo.pos.sales.JPanelTicketEdits"));
       // jTextField1.setEnabled(true);
       // jRemarks.setText(m_oLineTicket.getRemarks());
        jTextArea1.setEnabled(true);
        jTextArea1.setEditable(true);
        if(reason != null)
            jTextArea1.setText(reason);
        else
           jTextArea1.setText(" ");
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);



       // getRootPane().setDefaultButton(m_jButtonOK);

       setVisible(true);
       return true;


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

    public static boolean showMessage(Component parent, AppView app, String reason,String qtid) {

        Window window = getWindow(parent);

        Jpendingremarkedit myMsg;
        if (window instanceof Frame) {
            myMsg = new Jpendingremarkedit((Frame) window, true);
        } else {
            myMsg = new Jpendingremarkedit((Dialog) window, true);
        }
        return myMsg.init(app, reason,qtid);
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Reason  :");

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\swathi\\Desktop\\temp\\src-beans\\com\\openbravo\\images\\button_ok.png")); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\swathi\\Desktop\\temp\\src-beans\\com\\openbravo\\images\\button_cancel.png")); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String reason=jTextArea1.getText();
        try{
 new StaticSentence(m_app.getSession()
                        , "UPDATE QTICKET SET REASON = ? WHERE ID = ?"
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.STRING }))
                        .exec(new Object[] {reason, qtidtemp});
        //  obj.init(m_app);
        //   obj.loaddata2();
          dispose();
        }
        catch(BasicException be)
        {

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
