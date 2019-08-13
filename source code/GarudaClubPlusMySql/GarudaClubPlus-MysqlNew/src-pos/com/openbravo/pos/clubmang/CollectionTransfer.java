/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CollectionTransfer.java
 *
 * Created on Oct 26, 2009, 3:33:47 PM
 */
package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.util.StringUtils;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class CollectionTransfer extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form CollectionTransfer */
    private MoneyCollectionTableModel mcmodel;
    private MoneyCollectionTableModel cheqmcmodel;
    private AppView m_App;
    private DataLogicSales dlsales;
    private ComboBoxValModel rmodel;
    private ComboBoxValModel bcmodel;
    private ComboBoxValModel bchmodel;
    private DataLogicFacilities dlfac;

    public CollectionTransfer() {
        initComponents();
    }
    //  public CollectionTransfer getcollectionTransfer(){
    //    return this;
    //  }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        mcmodel = new MoneyCollectionTableModel();
        dlsales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
    }

    public String getTitle() {
        return null;
    }

    public void activate() throws BasicException {
        loadData();
        jCheckBox1.setSelected(true);
        mcmodel.setemptyInstance();
        total.setText("0.00");
        AppUser appuser = m_App.getAppUserView().getUser();
        List<PeopleInfo> users = dlsales.getPeoplewithcashandcheque().list();
        String id = appuser.getId();
        int i = 0;
        for (PeopleInfo pinfo : users) {
            if (pinfo.getID().equals(id)) {
                users.remove(i);
                break;
            }
            i++;
        }
        rmodel = new ComboBoxValModel(users);
        jComboBox1.setModel(rmodel);
        cheqmcmodel = MoneyCollectionTableModel.loadInstance(m_App);
        jTable2.setModel(cheqmcmodel.getchequeTableModel());
        chequetotal.setText(Formats.ConvertDoubleToString(cheqmcmodel.getchequetotal()));
        if (!m_App.getAppUserView().getUser().hasPermission("Accountant")) {
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jComboBox2.setVisible(false);
            jComboBox3.setVisible(false);
        } else {
            List<AccountMaster> acc = dlfac.getBankAccount();
            List<String> cashieraccs = dlsales.getCashierAccounts();
            for (String line : cashieraccs) {
                for (AccountMaster accline : acc) {
                    if (line.equals(accline.getid())) {
                        acc.remove(accline);
                        break;
                    }
                }
            }
            bcmodel = new ComboBoxValModel(acc);
            bchmodel = new ComboBoxValModel(acc);
            jComboBox2.setModel(bcmodel);
            jComboBox3.setModel(bchmodel);
            jButton2.setText("Transfer Cash");
            jButton3.setText("Transfer Cheque");
        }
    }

    private void loadData() {
        nos.setText(null);
        jTable1.setModel(mcmodel.getTableModel());


        TableColumnModel jColumns = jTable1.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(100);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(40);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(80);
        jColumns.getColumn(2).setResizable(false);

    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            nos.setText(null);
        } else if (cTrans == '.') {
            nos.setText(nos.getText() + cTrans);
        } else {

            nos.setText(nos.getText() + cTrans);
        }
    }

    public Object getBean() {
        return this;
    }

    private Date getDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.MILLISECOND, 00);
        cal.set(Calendar.SECOND, 00);
        d.setTime(cal.getTimeInMillis());
        return d;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        nos = new javax.swing.JTextField();
        enter = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        chequetotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();

        setAutoscrolls(true);

        jButton1.setText("Transfer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Transfer To :");

        nos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        enter.setText("Enter");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });

        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(enter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jNumberKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enter))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Type", "Number", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        total.setEditable(false);

        jLabel1.setText("Total");

        jLabel3.setText("Cash");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jLabel4.setText("Total");

        chequetotal.setEditable(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Cheque No", "Amount", "Bank", "customer", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Cheque List:");

        jCheckBox1.setText("Select All");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(chequetotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                                .addComponent(jCheckBox1)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chequetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33))
        );

        jLabel6.setText("Transfer Cash To");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Transfer Cheque To");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, 0, 192, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, 192, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        try {
            float no = Float.parseFloat(nos.getText());
            String value = jTable1.getModel().getValueAt(row, 0).toString();
            Double amt = Double.parseDouble(nos.getText());
            if (!value.equals("Change")) {
                amt = amt * Double.parseDouble(value.toString());
                jTable1.getModel().setValueAt(no, row, 1);
            //jTable1.getModel().setValueAt(0, row, 1);
            } else {
            }


            jTable1.getModel().setValueAt(amt, row, 2);
            Double totalamount = 0.0;
            Double[] camount = mcmodel.getAmount();
            for (int i = 0; i < camount.length; i++) {
                totalamount += camount[i];
            }
            total.setText(Formats.ConvertDoubleToString(totalamount));
            loadData();
        } catch (NumberFormatException e) {
            //numberformatexception
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_enterActionPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        // TODO add your handling code here:
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String cashdetail = null, chequedetail = null;
            Double cashtotal = Formats.ConvertStringToDouble(total.getText());
            if (cashtotal != 0.0) {
                float[] cashnos = mcmodel.getnos();
                Double[] amt = mcmodel.getAmount();
                for (int i = 0; i < cashnos.length - 1; i++) {
                    if (i != 4) {
                        if (cashdetail == null) {
                            cashdetail = String.valueOf(cashnos[i]);
                        } else {
                            cashdetail += "#" + String.valueOf(cashnos[i]);
                        }
                    }
                }
                cashdetail += "#" + String.valueOf(amt[cashnos.length - 1]);
                cashdetail += "#" + String.valueOf(cashnos[4]);//
            // cashdetail= mcmodel.getnos().toString();
            }
            Double chequecash = (Formats.ConvertStringToDouble(chequetotal.getText()));
            String unsentsheques = null;
            if (chequecash > 0.0) {
                for (MoneyCollectionTableModel.ChequeDetailLine cd : cheqmcmodel.getchequelist()) {
                    // dlfac.getDebtType()updatechequeholder(id);
                    if (cd.getselect() == true) {
                        if (cd.getTransfered() == false) {
                            if (chequedetail == null) {
                                chequedetail = cd.getid();
                            } else {
                                chequedetail += "#" + cd.getid();
                            }
                        } else if (cd.getTransfered() == true) {
                            chequetotal.setText(String.valueOf(Formats.ConvertStringToDouble(chequetotal.getText()) - cd.getAmount()));
                            cd.setSelect(false);
                            if (unsentsheques == null) {
                                unsentsheques = cd.getchid();
                            } else {
                                unsentsheques = unsentsheques + " , " + cd.getchid();
                            }
                        }
                    }
                }
                if (unsentsheques != null) {
                    JOptionPane.showMessageDialog(this, "The following cheques are already sent " + unsentsheques, "Cannot Send these cheques", JOptionPane.OK_OPTION);
                }
            }
            chequecash = Formats.ConvertStringToDouble(chequetotal.getText());
            if (jComboBox1.getSelectedIndex() != -1 && (cashtotal != 0.0 || chequecash != 0.0)) {
                PeopleInfo pinfo = (PeopleInfo) jComboBox1.getSelectedItem();
                Date dnow = new Date();
                Object[] value = new Object[]{UUID.randomUUID().toString(), dnow, cashdetail, cashtotal, chequedetail, chequecash, m_App.getAppUserView().getUser().getId(), pinfo.getID(), false, dlsales.getNextTransNumberIndex()};
                new PreparedSentence(m_App.getSession(), "INSERT INTO COLLECTIONTRANSFER(ID,DATE,CASHDETAIL,CASHTOTAL,CHEQUEDETAIL,CHEQUETOTAL,SENDER,RECEIVER,RECEIVED,NO) VALUES (?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.INT})).exec(value);
                printSlip();
            
            
            } else {
                if (jComboBox1.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(this, "Please select the person to whom it has to be transfered", null, JOptionPane.OK_OPTION);
                } else {
                    JOptionPane.showMessageDialog(this, "Both cash amount and cheque amount is zero", "Not Transferred", JOptionPane.OK_OPTION);
                }
            }
            mcmodel.setemptyInstance();
            jTable1.setModel(mcmodel.getTableModel());
            total.setText("0.00");
            jComboBox1.setSelectedIndex(-1);
            cheqmcmodel = MoneyCollectionTableModel.loadInstance(m_App);
            jTable2.setModel(cheqmcmodel.getchequeTableModel());
            chequetotal.setText(cheqmcmodel.getchequetotal().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            Date d = new Date();
            d.setTime(getDate(d).getTime());
            AccountMaster account = (AccountMaster) jComboBox2.getSelectedItem();
            Double ctotal = Formats.ConvertStringToDouble(total.getText());
            String receiver = m_App.getAppUserView().getUser().getName();
            if (ctotal > 0) {
                if (JOptionPane.showConfirmDialog(this, "Do you want to transfer " + Formats.CURRENCY.formatValue(ctotal) + " to " + account.getName(), null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String tid = UUID.randomUUID().toString();
                    String transno = dlfac.getnextTranscationNum(d, "Contra");
                    //ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,PAYMENTREF,ACTIVE
                    Object[] chvalue = new Object[]{UUID.randomUUID().toString(), tid, null, d, "C", "Contra", transno, ctotal, d, true, receiver, m_App.getProperties().getHost(), "Cash transfered to " + account.getName(), m_App.getAppUserView().getUser().getcashaccount(), 0.0, d, "Cash handover", true};
                    dlfac.insertintoaccjoutnal2(chvalue);
                    Object[] chvalue1 = new Object[]{UUID.randomUUID().toString(), tid, null, d, "D", "Contra", transno, ctotal, d, true, receiver, m_App.getProperties().getHost(), "Cash transfered to " + account.getName(), account.getid(), 0.0, d, "Cash handover", true};
                    dlfac.insertintoaccjoutnal2(chvalue1);
                    mcmodel.setemptyInstance();
                    jTable1.setModel(mcmodel.getTableModel());
                    total.setText("0.00");
                    dlfac.updateTransNumber("Contra", d, Integer.parseInt(transno));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            Transaction t = new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                    String unsentsheques = null;
                    Double chtotal = 0.0;
                    Date d = new Date();
                    d.setTime(getDate(d).getTime());
                    String chequeList = null;
                    AccountMaster account = (AccountMaster) jComboBox3.getSelectedItem();
                    String tid = UUID.randomUUID().toString();
                    String id = UUID.randomUUID().toString();
                    if (JOptionPane.showConfirmDialog(null, "Do you want to transfer cheques to " + account.getName(), null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        for (MoneyCollectionTableModel.ChequeDetailLine cd : cheqmcmodel.getchequelist()) {
                            // dlfac.getDebtType()updatechequeholder(id);
                            if (cd.getselect() == true) {
                                if (cd.getTransfered() == false) {
                                    if (chequeList == null) {
                                        chequeList = cd.getchid();
                                    } else {
                                        chequeList += " , " + cd.getchid();
                                    }
                                    dlfac.updatechequeholder1(cd.getid(), account.getid(), tid);
                                } else if (cd.getTransfered() == true) {
                                    try {
                                        chequetotal.setText(String.valueOf(Formats.ConvertStringToDouble(chequetotal.getText()) - cd.getAmount()));
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                    cd.setSelect(false);
                                    if (unsentsheques == null) {
                                        unsentsheques = cd.getchid();
                                    } else {
                                        unsentsheques = unsentsheques + " , " + cd.getchid();
                                    }
                                }
                            }
                        }
                        // }
                        if (unsentsheques != null) {
                            JOptionPane.showMessageDialog(null, "The following cheques are already sent " + unsentsheques, "Cannot Send these cheques", JOptionPane.OK_OPTION);
                        }
                        try {
                            chtotal = Formats.ConvertStringToDouble(chequetotal.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        String receiver = m_App.getAppUserView().getUser().getName();
                        if (chtotal > 0) {
                            String transno = dlfac.getnextTranscationNum(d, "Contra");
                            //ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,CLEARDATE,PAYMENTREF,ACTIVE
                            Object[] chvalue = new Object[]{UUID.randomUUID().toString(), tid, null, d, "C", "Contra", transno, chtotal, d, true, receiver, m_App.getProperties().getHost(), "Cheque nos: " + chequeList + " transfered to " + account.getName(), m_App.getAppUserView().getUser().getchequeaccount(), 0.0, d, null, true};
                            dlfac.insertintoaccjoutnal2(chvalue);
                            Object[] chvalue1 = new Object[]{id, tid, null, d, "D", "Contra", transno, chtotal, d, true, receiver, m_App.getProperties().getHost(), "Cheque nos: " + chequeList + " transfered to " + account.getName(), account.getid(), 0.0, d, null, true};
                            dlfac.insertintoaccjoutnal2(chvalue1);
                            dlfac.updateTransNumber("Contra", d, Integer.parseInt(transno));
                        }
                        cheqmcmodel = MoneyCollectionTableModel.loadInstance(m_App);
                        jTable2.setModel(cheqmcmodel.getchequeTableModel());
                        chequetotal.setText(cheqmcmodel.getchequetotal().toString());
                        jComboBox3.setSelectedIndex(-1);

                    //  if(JOptionPane.showConfirmDialog(this, "Cheques were sucessfully transfered.Do u want a print out", null, JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION){

                    //  }
                    }
                    return null;
                }
            };
            t.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        int rcount = jTable2.getRowCount();
        if (jCheckBox1.isSelected() == true) {
            for (int i = 0; i < rcount; i++) {
                jTable2.getModel().setValueAt(true, i, 6);
            }

        } else if (jCheckBox1.isSelected() == false) {
            for (int i = 0; i < rcount; i++) {
                jTable2.getModel().setValueAt(false, i, 6);
            }
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField chequetotal;
    private javax.swing.JButton enter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField nos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables

private TicketParser m_TTP;

public void printSlip(){
         String sresource = LookupUtilityImpl.getInstance(null).getDataLogicSystem().getResourceAsXML("Printer.CollectionTransfer");
        
        
         String Cash1000 = (mcmodel.getTableModel().getValueAt(0, 1).toString()); 
         String Cash500 = (mcmodel.getTableModel().getValueAt(1, 1).toString()); 
         String Cash100 = (mcmodel.getTableModel().getValueAt(2, 1).toString()); 
         String Cash50 = (mcmodel.getTableModel().getValueAt(3, 1).toString()); 
         String Cash20 = (mcmodel.getTableModel().getValueAt(4, 1).toString()); 
         String Cash10 = (mcmodel.getTableModel().getValueAt(5, 1).toString()); 
         String Cash5 = (mcmodel.getTableModel().getValueAt(6, 1).toString()); 
         String Cash2 = (mcmodel.getTableModel().getValueAt(7, 1).toString()); 
         String Cash1 = (mcmodel.getTableModel().getValueAt(8, 1).toString()); 
         String CashChange = (mcmodel.getTableModel().getValueAt(9, 1).toString());
         
         String Amt1000 = (mcmodel.getTableModel().getValueAt(0, 2).toString()); 
         String Amt500 = (mcmodel.getTableModel().getValueAt(1, 2).toString()); 
         String Amt100 = (mcmodel.getTableModel().getValueAt(2, 2).toString()); 
         String Amt50 = (mcmodel.getTableModel().getValueAt(3, 2).toString()); 
         String Amt20 = (mcmodel.getTableModel().getValueAt(4, 2).toString()); 
         String Amt10 = (mcmodel.getTableModel().getValueAt(5, 2).toString()); 
         String Amt5 = (mcmodel.getTableModel().getValueAt(6, 2).toString()); 
         String Amt2 = (mcmodel.getTableModel().getValueAt(7, 2).toString()); 
         String Amt1 = (mcmodel.getTableModel().getValueAt(8, 2).toString()); 
         String AmtChange = (mcmodel.getTableModel().getValueAt(9, 2).toString());
         
         
         
         
        try {
            AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
            
            // qTicket.getCustomer().getSearchkey();
            m_TTP = new TicketParser(m_App.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());
            ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
            
            String x = m_App.getAppUserView().getUser().getRole();
            
            script.put("date", new Date());
           
             script.put("Amt1000", Amt1000);
             script.put("Amt500",Amt500);
             script.put("Amt100",Amt100);
             script.put("Amt50", Amt50);
             script.put("Amt20", Amt20);
             script.put("Amt10", Amt10);
             script.put("Amt5", Amt5);
             script.put("Amt2", Amt2);
             script.put("Amt1", Amt1);
             script.put("AmtChange",AmtChange);
           
             
             
             script.put("Cash1000", Cash1000);
             script.put("Cash500",Cash500);
             script.put("Cash100",Cash100);
             script.put("Cash50", Cash50);
             script.put("Cash20", Cash20);
             script.put("Cash10", Cash10);
             script.put("Cash5", Cash5);
             script.put("Cash2", Cash2);
             script.put("Cash1", Cash1);
             script.put("CashChange",CashChange);
            
           // script.put("type","CODE128");
          //  script.put("position","bottom");
           // script.put("code", "asdkahsdk");
           script.put("TotalAmt",total.getText());
              
         
            
           // m_TTP.TicketParserBarcode("CODE128", "bottom" ,barcodecods );
            
            script.put("eoe", StringUtils.encodeXML("E&OE"));
           // script.put("printer", LookupUtilityImpl.getInstance(null).getPRCategoriesMap().get(prcategory).getPrinter());
            m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (Exception e) {
            }
        }   





}
