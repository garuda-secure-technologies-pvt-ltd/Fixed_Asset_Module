/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * waitereditor.java
 *
 * Created on Dec 1, 2008, 1:15:01 PM
 */
package com.openbravo.pos.mant;

import java.awt.Component;
import javax.swing.*;

import com.openbravo.pos.forms.AppLocal;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.admin.PeopleView;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class waitereditor extends JPanel implements EditorRecord {

    private String m_sid;
    private List m_sentcat;
    private SentenceExec m_sentadd;
    private SentenceExec m_sentdel;
    private ComboBoxValModel rolesmodel;
    AppView m_App;
    private String portNumber;
    private CardReader cr;

    /** Creates new form waitereditor */
    public waitereditor(AppView app, DirtyManager dirty) {
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_App = app;
        initComponents();
        jTextField1.getDocument().addDocumentListener(dirty);
        jTextField2.getDocument().addDocumentListener(dirty);
        roles.addActionListener(dirty);
        try {
            //  AppView app=LookupUtilityImpl.getInstance(null).getAppView();
            if (!m_App.getAppUserView().getUser().getRole().equals("0")) {
                m_sentcat = dlSales.getWaiterList(m_App.getAppUserView().getUser().getRole());
            } else {
                m_sentcat = dlSales.getWaiterList1();
            }
            m_sentadd = dlSales.getWaiterAdd();
            m_sentdel = dlSales.getWaiterDel();

            // try{
            rolesmodel = new ComboBoxValModel(dlSales.getRoleList().list());
        } catch (Exception e) {
            new MessageInf(e).show(this);
        }

        writeValueEOF();
    }

    public void activate() throws BasicException {
        startCardReader();
        List a = m_sentcat;
        a.add(0, null);
        roles.setModel(rolesmodel);
        if (m_App.getAppUserView().getUser().hasPermission("waiterRole")) {
            roles.setVisible(true);
            jLabel2.setVisible(true);
        } else {
            roles.setVisible(false);
            jLabel2.setVisible(false);
        }

    }

    public void writeValueEOF() {

        m_sid = null;
        jTextField1.setText(null);
        jTextField2.setText(null);
        // jTextField2.setText(null);


        rolesmodel.setSelectedKey(null);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        roles.setEnabled(false);
    //jTextField2.setEnabled(false);

    }
    //praveen:cardreader function

    public void startCardReader() {
        try {
            portNumber = m_App.getProperties().getProperty("card.portnumber");
            boolean cardAccessOnlyFlag = false;
            if (m_App.getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf(m_App.getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
            System.out.println(portNumber);
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void writeValueInsert() {
        jTextField1.setEditable(true);
        m_sid = null;
        jTextField1.setText(null);
        jTextField2.setText(null);
        rolesmodel.setSelectedKey(null);
        jTextField1.setEnabled(true);
        roles.setEnabled(true);
        jTextField2.setEnabled(true);
    }

    public void writeValueDelete(Object value) {
        jTextField1.setEditable(true);
        Object[] place = (Object[]) value;
        m_sid = Formats.STRING.formatValue(place[0]);
        jTextField1.setText(Formats.STRING.formatValue(place[1]));
        jTextField2.setText(Formats.STRING.formatValue(place[3]));
        rolesmodel.setSelectedKey(place[2]);
        roles.setEnabled(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
    }

    public void writeValueEdit(Object value) {

        Object[] place = (Object[]) value;
        m_sid = Formats.STRING.formatValue(place[0]);
        jTextField1.setText(Formats.STRING.formatValue(place[1]));
        jTextField1.setEditable(false);
        jTextField2.setText(Formats.STRING.formatValue(place[3]));
        rolesmodel.setSelectedKey(place[2]);
        jTextField1.setEnabled(true);
        roles.setEnabled(true);
        jTextField2.setEnabled(true);

    }

    public Object createValue() throws BasicException {
        Object[] place = new Object[5];
        place[0] = m_sid == null ? UUID.randomUUID().toString() : m_sid;
        place[1] = jTextField1.getText();
        place[3] = jTextField2.getText();
        if (m_App.getAppUserView().getUser().hasPermission("waiterRole")) {
            place[2] = rolesmodel.getSelectedKey();
        } else {
            JOptionPane.showMessageDialog(null, "This User Dont have Permission to Change Role");
        }
        return place;
    }

    public Component getComponent() {
        return this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        m_jCatalogAdd = new javax.swing.JButton();
        m_jCatalogDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        roles = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("NAME");

        m_jCatalogAdd.setText(AppLocal.getIntString("button.catalogadd")); // NOI18N
        m_jCatalogAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatalogAddActionPerformed(evt);
            }
        });

        m_jCatalogDelete.setText(AppLocal.getIntString("button.catalogdel")); // NOI18N
        m_jCatalogDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatalogDeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("Counter");

        jLabel3.setText("Card");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/contents.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(m_jCatalogAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(m_jCatalogDelete)
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCatalogDelete)
                    .addComponent(m_jCatalogAdd))
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void m_jCatalogAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatalogAddActionPerformed

        try {
            Object param = m_sid;
            //    m_sentdel.exec(param); // primero borramos
            m_sentadd.exec(param); // y luego insertamos lo que queda
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e));
        }
    }//GEN-LAST:event_m_jCatalogAddActionPerformed

    private void m_jCatalogDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatalogDeleteActionPerformed

        try {
            m_sentdel.exec(m_sid);
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e));
        }
    }//GEN-LAST:event_m_jCatalogDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardnew"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            String card = cr.getData();
            String s1 = checkCardForRegister(card);
            if (s1.equals(card)) {
                jTextField2.setText(card);
            } else {
                JOptionPane.showMessageDialog(this, "already registered for" + s1);
            }
            System.out.println();
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
       
        
         if (evt.getKeyChar() == '\n') {
            try {
                String receptionString = jTextField2.getText().trim();
                WaiterInfo waiterinfo = (WaiterInfo) new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "SELECT W.ID, W.NAME,W.COUNTER,P.NAME FROM WAITER W,PEOPLE P WHERE P.ROLE=W.COUNTER AND W.CARDNO = ? AND P.VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(receptionString);
                
                if(waiterinfo==null)
                {
                 jTextField2.setText(receptionString);
                
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "This card is already registered for " + waiterinfo.getName() +".", "Already Registered!!", JOptionPane.ERROR_MESSAGE);
                     jTextField2.setText(null);
                }
               // inputtext = new StringBuffer();
                } catch (BasicException ex) {
                Logger.getLogger(PeopleView.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        else
        {
            
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private String checkCardForRegister(String s) {
        Object[] obj = null;
        String card = s;
        try {
            obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT DNAME  FROM MEMDEPENDENT WHERE CARD=? UNION ALL SELECT NAME FROM WAITER WHERE CARDNO=? UNION ALL SELECT NAME FROM CUSTOMERS WHERE CARD=?",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{card, card, card});
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
        if (obj != null) {
            card = (String) obj[0];
        }
        return card;
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton m_jCatalogAdd;
    private javax.swing.JButton m_jCatalogDelete;
    private javax.swing.JComboBox roles;
    // End of variables declaration//GEN-END:variables
}
