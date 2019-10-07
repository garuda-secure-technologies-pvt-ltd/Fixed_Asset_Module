/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.AbstractListModel;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.Calendar;
import javax.swing.JOptionPane;
import com.openbravo.pos.FixedAssetRegistration.MaintenanceTableModel.MaintenanceInfo;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Transaction;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
/**
 *
 * @author dev3
 */
public class MaintenanceDailog extends javax.swing.JDialog {

    private AppView app;
    private boolean flag;
    private int flagkey;
    private MaintenanceTableModel mnt_table;
     File file = null;
    String filename;
    File selectedFile;
    private List<String> accountheadList = new ArrayList<String>();
    private ComboBoxValModel accountheadlistModel;
    String deacid;
    public static String MID = null;
    private String accclass;
    public List<MaintenanceTableModel.MaintenanceInfo> maintenanceList = new ArrayList<MaintenanceTableModel.MaintenanceInfo>();

    /**
     * Creates new form MaintenanceDailog
     */
    public MaintenanceDailog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public MaintenanceDailog(java.awt.Dialog parent, AppView app, boolean flag) {
        super(parent, true);

        this.app = app;
        this.flag = flag;
    }

    public MaintenanceDailog(java.awt.Frame parent, AppView app, boolean flag) {
        super(parent, true);

        this.app = app;
        this.flag = flag;
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    public static MaintenanceDailog getDialog(Component parent, AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);

        MaintenanceDailog bill;

        if (window instanceof Frame) {
            bill = new MaintenanceDailog((Frame) window, app, flag);
        } else {
            bill = new MaintenanceDailog((Dialog) window, app, flag);
        }

        return bill;

    }

    public Boolean showDialog() {
        try {

            init();
            setVisible(true);

        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }

    public void init() throws BasicException {
        initComponents();
        linktxt.setEditable(false);
        accountheadlistModel = new ComboBoxValModel(getaccountheadList());

        acccombo.setModel(accountheadlistModel);
        date_txt.setEditable(false);

        jPanel16.setVisible(true);
        mnt_table = MaintenanceTableModel.GetMaintenanceTableModel(app);
        jTable1.setModel(mnt_table.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }

    public void loaddata() throws BasicException {

        // subheadcombo.setModel(subheadclasslistModel);
    }

    public List getaccountheadList() throws BasicException {
        List<Object> accountheadList = new ArrayList<Object>();
        accountheadList = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM ACCOUNTMASTER ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return accountheadList;
    }

    public String getaccId() {

        List<Object> accountheadlist = new ArrayList<Object>();
        try {
            if (acccombo.getSelectedIndex() != -1) {
                accountheadlist = (List<Object>) new StaticSentence(app.getSession(), "SELECT  ID FROM ACCOUNTMASTER  WHERE NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(acccombo.getSelectedItem());
                accclass = (String) accountheadlist.get(0);
            } else {
                accclass = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(MaintenanceDailog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accclass;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        date_txt = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        amt_txtx = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        acccombo = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        vocher_det_txt = new javax.swing.JTextArea();
        jLabel66 = new javax.swing.JLabel();
        linktxt = new javax.swing.JTextField();
        add_but = new javax.swing.JButton();
        reset_but = new javax.swing.JButton();
        date_but = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
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
        deactivate_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel62.setText("Date");

        jLabel63.setText("Amount");

        amt_txtx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amt_txtxKeyTyped(evt);
            }
        });

        jLabel64.setText("Dr to HEad of Account");

        jLabel65.setText("Vocher Details");

        vocher_det_txt.setColumns(20);
        vocher_det_txt.setRows(5);
        jScrollPane18.setViewportView(vocher_det_txt);

        jLabel66.setText("Scanned copy details");

        add_but.setText("Add");
        add_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_butActionPerformed(evt);
            }
        });

        reset_but.setText("Reset");
        reset_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_butActionPerformed(evt);
            }
        });

        date_but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        date_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_butActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/mime2.png")));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(reset_but, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(add_but, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(amt_txtx)
                                    .addComponent(acccombo, 0, 179, Short.MAX_VALUE)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(linktxt))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(date_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(date_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amt_txtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acccombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(linktxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_but)
                    .addComponent(add_but))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Maintenance", jPanel16);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        deactivate_but.setText("Deactivate");
        deactivate_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivate_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deactivate_but, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deactivate_but)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View List", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_butActionPerformed

        try {

            if (date_txt.getText() != null && date_txt.getText().trim().length() > 0) {
                     if (acccombo.getSelectedIndex() != -1 && acccombo.getSelectedItem() != null) {
                        if (vocher_det_txt.getText() != null && vocher_det_txt.getText().trim().length() > 0) {
                            if (amt_txtx.getText() != null && amt_txtx.getText().trim().length() > 0) {

                                Transaction t = new Transaction(app.getSession()) {
                                    @Override
                                    protected Object transact() throws BasicException {
                                        Double amount = null;

                                        Date mntdate = new Date();
                                        
                                        Date effectivedate = new Date();
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTimeInMillis(mntdate.getTime());

                                        mntdate.setTime(cal.getTimeInMillis());
                                        mntdate = (Date) Formats.TIMESTAMP.parseValue(date_txt.getText());

                                        amount = (Double) Formats.DOUBLE.parseValue(amt_txtx.getText());

                                        MID = UUID.randomUUID().toString();
                                         String string = UUID.randomUUID().toString();
                                        String[] parts = string.split("-");
                                    String part1 = parts[0];
                                    String part2 = parts[1];
                                    String flnm = linktxt.getText();
                                    String name = "";
                                    String x = "";
                                    if (flnm.equals("")) {
                                        name = "";
                                    } else {

                                        String arr[] = flnm.split("/");
                                       // x = "maintenance" + part1 + arr[arr.length - 1];;
                                         x="amc" + part1 +flnm.substring(flnm.lastIndexOf("."),flnm.length());
                                        name = "./Asset Documents/" + x;
                                    }

                                        
                                        Object[] param = new Object[]{MID, FixedAsset2.fixedid, mntdate, amount, getaccId(), vocher_det_txt.getText().trim(), name, app.getAppUserView().getUser().getName(), new Date(), true};
                                        new PreparedSentence(app.getSession(), "INSERT INTO FA_MAINTENANCE ( ID,FA_ID,DATE,AMOUNT,ACCOUNT_HEAD,VOCHER_DETAILS,SCANNED_COPY,CREATED_BY,CREATED_DATE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(param);
                                        
                                        flagkey=1;
                                        //continuation of copy paste doc
                                    if (linktxt.getText() != null) {
                                        File srcDir = new File(linktxt.getText());
                                        FileInputStream fii;
                                        FileOutputStream fio;
                                        try {

                                            fii = new FileInputStream(srcDir);

                                            fio = new FileOutputStream("./Asset Documents/" + x + "");
                                            byte[] b = new byte[1024];
                                            int i = 0;
                                            try {
                                                while ((fii.read(b)) > 0) {

                                                    fio.write(b);
                                                }
                                                fii.close();
                                                fio.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();

                                                Logger.getLogger(MaintenanceDailog.class.getName()).log(Level.SEVERE, null, e);

                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                            Logger.getLogger(MaintenanceDailog.class.getName()).log(Level.SEVERE, null, e);

                                        }
                                    }
                                        return null;
                                    }
                                };
                                t.execute();
                                if(flagkey==1){
                                JOptionPane.showMessageDialog(this, "Maintenance created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                reset();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Please ensure that amount field is not empty", null, JOptionPane.OK_OPTION);

                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that vocher details field is not empty", null, JOptionPane.OK_OPTION);

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Dr to Head of Account field is not empty", null, JOptionPane.OK_OPTION);

                    }
                
            } else {
                JOptionPane.showMessageDialog(this, "Please ensure that Date is not empty", null, JOptionPane.OK_OPTION);

            }

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please enter the correct price  ", null, JOptionPane.OK_OPTION);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please enter the correct price  ", null, JOptionPane.OK_OPTION);

            Logger.getLogger(MaintenanceDailog.class.getName()).log(Level.SEVERE, null, e);
           
        }
    }//GEN-LAST:event_add_butActionPerformed

    private void date_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_butActionPerformed
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(date_txt.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            // date=getDate(date);
            date_txt.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_date_butActionPerformed

    private void reset_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_butActionPerformed
        reset();
    }//GEN-LAST:event_reset_butActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();

        if (tabno == 1) {
            try {
                mnt_table = MaintenanceTableModel.GetMaintenanceTableModel(app);

                jTable1.setModel(mnt_table.getTableModel());
                 jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(30);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(50);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(60);
                cmodel.getColumn(6).setPreferredWidth(120);

            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void amt_txtxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amt_txtxKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_amt_txtxKeyTyped

    private void deactivate_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_butActionPerformed
        if (jTable1.getSelectedRow() != -1) {
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? ", "Deactivation", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {
                
                    int row = jTable1.getSelectedRow();
                    MaintenanceInfo showdata = mnt_table.getList().get(row);

                    deacid = showdata.getId();
                    deactMaintnce();

               
            }
        }else{
        JOptionPane.showMessageDialog(this, "Please select row in the table ", null, JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_deactivate_butActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf", "MS Office Documents", "docx", "xlsx", "pptx",
            "html", "wpd", "wp", "doc", "zip", "Library file");

        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filename = selectedFile.getAbsolutePath();
            linktxt.setText(filename);
            file = new File(filename);

        }
    }//GEN-LAST:event_jButton2ActionPerformed
    private void deactMaintnce() {
        try {

            new PreparedSentence(app.getSession(), "UPDATE FA_MAINTENANCE  SET  ACTIVE=0  , DEACBY=? , DEACDATE=?  WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), deacid});

            mnt_table = MaintenanceTableModel.GetMaintenanceTableModel(app);
            jTable1.setModel(mnt_table.getTableModel());

            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        date_txt.setText(null);
        amt_txtx.setText(null);
        acccombo.setSelectedIndex(-1);
        vocher_det_txt.setText(null);
        linktxt.setText(null);

    }

    @Override
    public String getTitle() {
        return "Maintenance";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox acccombo;
    private javax.swing.JButton add_but;
    private javax.swing.JTextField amt_txtx;
    private javax.swing.JButton date_but;
    private javax.swing.JTextField date_txt;
    private javax.swing.JButton deactivate_but;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField linktxt;
    private javax.swing.JButton reset_but;
    private javax.swing.JTextArea vocher_det_txt;
    // End of variables declaration//GEN-END:variables

    private class ItemsListModel extends AbstractListModel {

        private java.util.List items;

        public ItemsListModel(java.util.List items) {
            this.items = items;
        }

        public int getSize() {
            return items.size();
        }

        public Object getElementAt(int i) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
            return items.get(i);
        }
    }

}
