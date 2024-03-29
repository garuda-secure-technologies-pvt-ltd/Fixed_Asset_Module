/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.pos.FixedAssetRegistration.RevaluationTableModel.RevaluationInfo;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JComponent;

/**
 *
 * @author dev3
 */
public class RevaluationDialog extends javax.swing.JDialog {

    private AppView app;
    private boolean flag;
    private RevaluationTableModel Revaluation_table;
    String remarksp;
    private List<RevaluationTableModel.RevaluationInfo> data;
    String id;
    String link = null;

    public static String RID = null;
    String deactid;
    File file = null;
    String filename;
    private DirtyManager dirty = new DirtyManager();
    private static DataLogicFacilities dmang;
    File selectedFile;

    /**
     * Creates new form RevaluationDialog
     */
    public RevaluationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public RevaluationDialog(java.awt.Dialog parent, AppView app, boolean flag) {
        super(parent, true);

        this.app = app;
        this.flag = flag;
    }

    public RevaluationDialog(java.awt.Frame parent, AppView app, boolean flag) {
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

    public static RevaluationDialog getDialog(Component parent, AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);

        RevaluationDialog bill;

        if (window instanceof Frame) {
            bill = new RevaluationDialog((Frame) window, app, flag);
        } else {
            bill = new RevaluationDialog((Dialog) window, app, flag);
        }

        return bill;

    }

    public boolean showDialog() {
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
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        date_txt.setEditable(false);

        Revaluation_table = RevaluationTableModel.GetRevaluationTableModel(app);
        jTable1.setModel(Revaluation_table.getTableModel());
        jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        link_txt.setEditable(false);
    }

    @Override
    public String getTitle() {
        return "Revaluation";
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
        jPanel14 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        date_txt = new javax.swing.JTextField();
        datere_but = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        reavamt_txt = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        reaforrev_txt = new javax.swing.JTextArea();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        amtdetails_txt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        link_txt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        Add_but = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        deact_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel51.setText("Date Of Revaluation");

        datere_but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        datere_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datere_butActionPerformed(evt);
            }
        });

        jLabel52.setText("Revalued Amount");

        reavamt_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reavamt_txtKeyTyped(evt);
            }
        });

        jLabel53.setText("Reason For Revaluation");

        reaforrev_txt.setColumns(20);
        reaforrev_txt.setRows(5);
        jScrollPane14.setViewportView(reaforrev_txt);

        jLabel54.setText("Revaluation Account Deatils");

        amtdetails_txt.setColumns(20);
        amtdetails_txt.setRows(5);
        jScrollPane15.setViewportView(amtdetails_txt);

        jLabel1.setText("Revaluation Document Link");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/mime2.png")));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        Add_but.setText("Add");
        Add_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(link_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(reavamt_txt))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datere_but, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(373, 373, 373)
                        .addComponent(Add_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datere_but, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reavamt_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(link_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Add_but)
                    .addComponent(reset))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Revaluation", jPanel14);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        deact_but.setText("Deactivate");
        deact_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deact_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deact_but, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deact_but)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View List", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();

        if (tabno == 1) {
            try {
                Revaluation_table = RevaluationTableModel.GetRevaluationTableModel(app);

                jTable1.setModel(Revaluation_table.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(30);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);

                cmodel.getColumn(4).setPreferredWidth(80);
                cmodel.getColumn(5).setPreferredWidth(120);

            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void Add_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_butActionPerformed

        try {
            if (amtdetails_txt.getText() != null && amtdetails_txt.getText().trim().length() > 0) {
                if (reaforrev_txt.getText() != null && reaforrev_txt.getText().trim().length() > 0) {
                    if (reavamt_txt.getText() != null && reavamt_txt.getText().trim().length() > 0) {
                        if (date_txt.getText() != null && date_txt.getText().trim().length() > 0) {
                            if (reavamt_txt.getText() != null && reavamt_txt.getText().trim().length() > 0) {
                                Transaction t = new Transaction(app.getSession()) {
                                    @Override
                                    protected Object transact() throws BasicException {

                                        Double amount = null;

                                        Date photo = new Date();

                                        Date effectivedate = new Date();
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTimeInMillis(photo.getTime());

                                        cal.set(Calendar.HOUR_OF_DAY, 00);
                                        cal.set(Calendar.MINUTE, 00);
                                        cal.set(Calendar.SECOND, 00);
                                        cal.set(Calendar.MILLISECOND, 00);
                                        photo.setTime(cal.getTimeInMillis());
                                        photo = (Date) Formats.TIMESTAMP.parseValue(date_txt.getText());

                                        link = link_txt.getText();
                                        amount = (Double) Formats.DOUBLE.parseValue(reavamt_txt.getText());

                                        RID = UUID.randomUUID().toString();
                                        String string = UUID.randomUUID().toString();
                                        String[] parts = string.split("-");
                                        String part1 = parts[0];
                                        String part2 = parts[1];
                                        String flnm = link_txt.getText();
                                        String x = "";
                                        String name = "";
                                        if (flnm.equals("")) {
                                            name = "";
                                        } else {
                                            // String arr[] = flnm.split("/");
                                            //x = "Revaluation"+part1 + arr[arr.length-1];
                                            System.out.println(part1 + "part1");
                                            System.out.println(flnm + "flnm");
                                            x = "Revaluation" + part1 + flnm.substring(flnm.lastIndexOf("."), flnm.length());
                                            name = "./Asset Documents/" + x;
                                        }
                                        Object[] param = new Object[]{RID, FixedAsset2.fixedid, photo, amount, reaforrev_txt.getText().trim(), amtdetails_txt.getText().trim(), name, app.getAppUserView().getUser().getName(), new Date(), true};
                                        new PreparedSentence(app.getSession(), "INSERT INTO FA_REVALUATION (ID,FA_ID,DATE_OF_REVALUATION,REVALUED_AMOUNT,REASON_REV,REV_ACC_DET,REV_DOC_LINK,CREATED_BY,CREATED_DATE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(param);
                                        int update_Reval = new PreparedSentence(app.getSession(), "UPDATE FA_MASTER SET REVALUATION=? WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{RID, FixedAsset2.fixedid});

                                        if (link_txt.getText() != null) {
                                            File srcDir = new File(link_txt.getText());
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

                                                    Logger.getLogger(RevaluationDialog.class.getName()).log(Level.SEVERE, null, e);

                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();

                                                Logger.getLogger(RevaluationDialog.class.getName()).log(Level.SEVERE, null, e);

                                            }
                                        }
                                        return null;
                                    }
                                };
                                t.execute();
                                JOptionPane.showMessageDialog(this, "Revaluation data inserted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                                reset();
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that Date field is not empty", null, JOptionPane.OK_OPTION);

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Amount is not empty", null, JOptionPane.OK_OPTION);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure that Reason For Revaluation is not empty", null, JOptionPane.OK_OPTION);

                }
            } else {
                JOptionPane.showMessageDialog(this, "Please ensure that Revaluation Account Deatils is not empty", null, JOptionPane.OK_OPTION);

            }

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Exception : " + nfe.getMessage(), null, JOptionPane.OK_OPTION);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Exception : " + e.getMessage(), null, JOptionPane.OK_OPTION);

            Logger.getLogger(RevaluationDialog.class.getName()).log(Level.SEVERE, null, e);

        }
    }//GEN-LAST:event_Add_butActionPerformed

    private void datere_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datere_butActionPerformed
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(date_txt.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            date_txt.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_datere_butActionPerformed

    private void reavamt_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reavamt_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_reavamt_txtKeyTyped

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        reset();
    }//GEN-LAST:event_resetActionPerformed

    private void deact_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deact_butActionPerformed
        if (jTable1.getSelectedRow() != -1) {
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? ", "Deactivation", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

                int row = jTable1.getSelectedRow();
                RevaluationInfo showdata = Revaluation_table.getList().get(row);

                deactid = showdata.getId();
                deactreval();

            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select row in the table ", null, JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_deact_butActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("./"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf", "MS Office Documents", "docx", "xlsx", "pptx",
                "html", "wpd", "wp", "doc", "zip", "Library file");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filename = selectedFile.getAbsolutePath();
            link_txt.setText(filename);
            file = new File(link_txt.getText().replace("./", ""));

        }

    }//GEN-LAST:event_jButton1ActionPerformed
    private void deactreval() {
        try {

            new PreparedSentence(app.getSession(), "UPDATE FA_REVALUATION  SET  ACTIVE=0  , DEACBY=? , DEACDATE=?  WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), deactid});

            Revaluation_table = RevaluationTableModel.GetRevaluationTableModel(app);
            jTable1.setModel(Revaluation_table.getTableModel());

            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_but;
    private javax.swing.JTextArea amtdetails_txt;
    private javax.swing.JTextField date_txt;
    private javax.swing.JButton datere_but;
    private javax.swing.JButton deact_but;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField link_txt;
    private javax.swing.JTextArea reaforrev_txt;
    private javax.swing.JTextField reavamt_txt;
    private javax.swing.JButton reset;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        date_txt.setText(null);
        reavamt_txt.setText(null);
        reaforrev_txt.setText(null);
        amtdetails_txt.setText(null);
        link_txt.setText(null);

    }

}
