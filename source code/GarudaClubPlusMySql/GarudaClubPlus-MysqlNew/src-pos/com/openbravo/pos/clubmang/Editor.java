/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Editor.java
 *
 * Created on May 12, 2009, 12:38:24 PM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.util.TreeAnimator;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author swathi
 */
public class Editor extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form Editor */
    private DataLogicFacilities dlfac;
    private EditorTableModel etModel;
    private AppView m_App;
    private AccountMaster accm;

    public Editor() {
    }

    public String getTitle() {
        return null;
    }

    public void init(AppView app) throws BeanFactoryException {
        initComponents();
        m_App = app;
        dlfac = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jLabel2.setText("From");
        jLabel3.setText("To");
        jButton1.setText("Date");
        jButton2.setText("Date");
        jLabel1.setVisible(false);
        jTextField1.setText(null);
        jTextField2.setText(null);


    }

    private void getSubAccounts(DefaultMutableTreeNode root, AccountMaster acc) throws BasicException {
        List<AccountMaster> sacclist = dlfac.getSubaccounts1(acc.getSerachkey());
        for (AccountMaster acc1 : sacclist) {
            DefaultMutableTreeNode rchild1 = new DefaultMutableTreeNode(acc1);
            root.add(rchild1);
        }
    }

    private void getAccounts(DefaultMutableTreeNode root, AccountMaster acc) throws BasicException {
        List<AccountMaster> macclist = dlfac.getaccountsMainhead1(acc.getSerachkey());
        for (AccountMaster acc1 : macclist) {
            DefaultMutableTreeNode rchild1 = new DefaultMutableTreeNode(acc1, true);
            root.add(rchild1);
            List<AccountMaster> bacclist = dlfac.getaccountsBreakdown1(acc1.getSerachkey());
            for (AccountMaster acc2 : bacclist) {
                DefaultMutableTreeNode rchild2 = new DefaultMutableTreeNode(acc2, true);
                rchild1.add(rchild2);
                getSubAccounts(rchild1, acc2);
            }
            getSubAccounts(rchild1, acc1);
        }
    }

    private void treestructure() throws BasicException {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Elements");
        List<AccountMaster> eacclist = dlfac.getaccountElementswithoutParent();
        for (AccountMaster acc : eacclist) {
            DefaultMutableTreeNode rchild = new DefaultMutableTreeNode(acc, true);
            root.add(rchild);
            List<AccountMaster> eacclist1 = dlfac.getaccountsElement(acc.getSerachkey());
            for (AccountMaster acc1 : eacclist1) {
                DefaultMutableTreeNode rchild1 = new DefaultMutableTreeNode(acc1, true);
                rchild.add(rchild1);
                getAccounts(rchild1, acc1);
                getSubAccounts(rchild1, acc1);
            }
        }
        DefaultTreeModel model = new DefaultTreeModel(root);
        jTree1.setModel(model);

        // ViewTooltips.register(jTree1);
        //jTree1.set;
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jTree1.requestFocus();
            }
        });

    }

    public void activate() throws BasicException {
        // etModel=new EditorTableModel();
        treestructure();
    // TreeAnimator animator = new TreeAnimator(jTree1);
    // Listener listener = new Listener(animator);
    //tree.addMouseListener(listener);
    // tree.addMouseMotionListener(listener);
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public Object getBean() {
        return this;
    }

    private boolean CheckForEditRequest(String tid) throws BasicException {
        boolean status = true;
        Object[] obj = (Object[]) new PreparedSentence(m_App.getSession(), "SELECT COUNT(A.ID) FROM ACCOUNTEDITDETAIL A JOIN ACCOUNTJOURNALDUP AD ON  AD.PAYMENTREF=? AND A.TID=AD.TID WHERE  A.CONFIRMEDBY IS  NULL", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(tid);
        if (obj != null && obj[0] != null) {
            if (Integer.parseInt(String.valueOf(obj[0])) > 0) {
                status = false;
            }
        }
        return status;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTree1.setAutoscrolls(true);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTree1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jLabel2.setText("jLabel1");

        jLabel3.setText("jLabel2");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName("jLabel2");
        jLabel3.getAccessibleContext().setAccessibleName("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        try {
            int row = jTable1.getSelectedRow();
            if (row >= 0) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    String tid = etModel.getTableModel().getValueAt(row, 5).toString();
                    boolean status = CheckForEditRequest(tid);
                    if (status == true) {
                        EditorDialog editor = EditorDialog.getDialog(this, m_App, dlfac);
                        editor.showDialog(tid);
                        etModel = EditorTableModel.loadInstance(m_App, accm.getid());
                        jTable1.setModel(etModel.getTableModel());
                        jLabel1.setText(accm.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "The voucher is already edited.The Edit request is in pending ", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        jTree1.requestFocus();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTree1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTree1KeyPressed
       
        DefaultMutableTreeNode temp = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

        if (temp.isLeaf()) {
           if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
                String sdate = jTextField1.getText();
                String edate = jTextField2.getText();
                if ("".equals(sdate) ||"".equals(edate)) {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to see the entire data ", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        try {
                            accm = (AccountMaster) temp.getUserObject();
                            jTable1.setVisible(true);
                            etModel = EditorTableModel.loadInstance(m_App, accm.getid());
                            jTable1.setModel(etModel.getTableModel());
                            jLabel1.setText(accm.getName());
                            jLabel1.setVisible(true);
                        } catch (BasicException e) {
                            //TODO :stacktrace and mesdsage
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a valid start and end date ", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    Date endDate = null, startDate = null;
                    try {
                        endDate = (Date) Formats.DATE.parseValue(jTextField2.getText());
                        startDate = (Date) Formats.DATE.parseValue(jTextField1.getText());
                    } catch (BasicException ex) {
                        JOptionPane.showMessageDialog(null, "Please select a valid start and end date (format:dd MMM,YYYY)", null, JOptionPane.INFORMATION_MESSAGE);
                        ex.printStackTrace();
                        //TODO :stacktrace and mesdsage
                    }
                    try {
                        if (startDate != null && endDate != null) {
                            accm = (AccountMaster) temp.getUserObject();
                            jTable1.setVisible(true);
                            etModel = EditorTableModel.loadInstance1(m_App, accm.getid(), startDate, endDate);
                            jTable1.setModel(etModel.getTableModel());
                            jLabel1.setText(accm.getName());
                            jLabel1.setVisible(true);
                        }
                    } catch (BasicException e) {
                        e.printStackTrace();
                        //TODO :stacktrace and mesdsage
                    }
                }
            }
        }

    }//GEN-LAST:event_jTree1KeyPressed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        // try {
        DefaultMutableTreeNode temp = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

        if (temp.isLeaf()) {
            if (evt.getClickCount() == 2) {
                String sdate = jTextField1.getText();
                String edate = jTextField2.getText();
                if ("".equals(sdate) ||"".equals(edate)) {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to see the entire data ", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        try {
                            accm = (AccountMaster) temp.getUserObject();
                            jTable1.setVisible(true);
                            etModel = EditorTableModel.loadInstance(m_App, accm.getid());
                            jTable1.setModel(etModel.getTableModel());
                            jLabel1.setText(accm.getName());
                            jLabel1.setVisible(true);
                        } catch (BasicException e) {
                            //TODO :stacktrace and mesdsage
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a valid start and end date ", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    Date endDate = null, startDate = null;
                    try {
                        endDate = (Date) Formats.DATE.parseValue(jTextField2.getText());
                        startDate = (Date) Formats.DATE.parseValue(jTextField1.getText());
                    } catch (BasicException ex) {
                        JOptionPane.showMessageDialog(null, "Please select a valid start and end date (format:dd MMM,YYYY)", null, JOptionPane.INFORMATION_MESSAGE);
                        ex.printStackTrace();
                        //TODO :stacktrace and mesdsage
                    }
                    try {
                        if (startDate != null && endDate != null) {
                            accm = (AccountMaster) temp.getUserObject();
                            jTable1.setVisible(true);
                            etModel = EditorTableModel.loadInstance1(m_App, accm.getid(), startDate, endDate);
                            jTable1.setModel(etModel.getTableModel());
                            jLabel1.setText(accm.getName());
                            jLabel1.setVisible(true);
                        }
                    } catch (BasicException e) {
                        e.printStackTrace();
                        //TODO :stacktrace and mesdsage
                    }
                }
            }
        }
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try {
            int row = jTable1.getSelectedRow();
            if (row >= 0) {
                if (evt.getClickCount() == 2) {
                    String tid = etModel.getTableModel().getValueAt(row, 5).toString();
                    boolean status = CheckForEditRequest(tid);
                    if (status == true) {
                        EditorDialog editor = EditorDialog.getDialog(this, m_App, dlfac);
                        editor.showDialog(tid);
                        etModel = EditorTableModel.loadInstance(m_App, accm.getid());
                        jTable1.setModel(etModel.getTableModel());
                        jLabel1.setText(accm.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "The voucher is already edited.The Request is in pending ", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField1.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {
            jTextField1.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);

        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 59);
            date.setTime(cal.getTimeInMillis());
            jTextField2.setText(Formats.DATE.formatValue(date));
        }
}//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
