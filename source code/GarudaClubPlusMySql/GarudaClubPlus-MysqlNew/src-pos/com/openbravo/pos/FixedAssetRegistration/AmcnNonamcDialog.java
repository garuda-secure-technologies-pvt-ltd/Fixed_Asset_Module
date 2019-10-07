/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.FacilityLogic;
import com.openbravo.pos.clubmang.Periodicity;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import javax.swing.JComponent;

/**
 *
 * @author dev3
 */
public class AmcnNonamcDialog extends javax.swing.JDialog {

    private AppView app;
    private boolean flag;
    String vendor;
    private List<String> amccontractorList = new ArrayList<String>();
    private ComboBoxValModel amccontractorListModel;
    private DirtyManager dirty = new DirtyManager();
    private List<String> amcparticularList = new ArrayList<String>();
    private ComboBoxValModel amcparticularListModel;
    private Periodicity p;
    private ComboBoxValModel periodmodel;
    private String periodtype;
    private ArrayList<Integer> periodicityno = new ArrayList<Integer>();
    private int period_no;
    private String contractor;
    private DataLogicFacilities dfac;
    private DataLogicFacilities dmang;
    private Date edate;
    private ComboBoxValModel periodtypemodel;
    private FacilityLogic flogic;
    String link = null;
    int perno = 0;
    String amcid;
    String nonamcid;
    private List<String> vendorList = new ArrayList<String>();
    private ComboBoxValModel vendorListModel;
    private AmcNNonamcTableModel nonamc_table;
    private AmcNNonamcTableModel amc_table;
    String amcdeatid;
    String nonamcdeactid;
    File documentfile;
    File file = null;
    String filename;
    File selectedFile;
    public static String AID = null;
    public static String NAID = null;
    String doc_link;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    DecimalFormat df = new DecimalFormat("#.00%");
    public String faid;
    private int flagkey;

    /**
     * Creates new form AmcnNonamcDialog
     */
    public AmcnNonamcDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public AmcnNonamcDialog(java.awt.Dialog parent, AppView app, boolean flag) {
        super(parent, true);

        this.app = app;
        this.flag = flag;
    }

    private List addNull(List list) {
        list.add(0, null);
        return list;
    }

    public AmcnNonamcDialog(java.awt.Frame parent, AppView app, boolean flag) {
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

    public static AmcnNonamcDialog getDialog(Component parent, AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);

        AmcnNonamcDialog bill;

        if (window instanceof Frame) {
            bill = new AmcnNonamcDialog((Frame) window, app, flag);
        } else {
            bill = new AmcnNonamcDialog((Dialog) window, app, flag);
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

    private Date calculateEndDate(Periodicity p, Date d, int no) {
        Date ed = new Date();
        ed.setTime(d.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ed.getTime());
        if (p.gettype().equals("Months")) {
            cal.add(Calendar.MONTH, p.getno() * no);
            if (p.getdoj() == false) {
                cal.set(Calendar.DATE, Integer.parseInt(p.getdate()));
            }
        } else if (p.gettype().equals("Days")) {
            cal.add(Calendar.DATE, p.getno() * no);
        } else if (p.gettype().equals("Years")) {
            cal.add(Calendar.YEAR, p.getno() * no);
            if (p.getdoj() == false) {
                cal.set(Calendar.DATE, Integer.parseInt(p.getdate()));
                cal.set(Calendar.MONTH, Integer.parseInt(p.getmonthname()));
            }
        } else if (p.gettype().equals("Quaterly")) {
            for (int i = 0; i < no; i++) {
                if (0 <= cal.get(Calendar.MONTH) && cal.get(Calendar.MONTH) <= 2) {
                    if (p.getdoj() == false) {
                        cal.set(Calendar.DATE, Integer.parseInt(p.getdate()));
                    }
                    cal.set(Calendar.MONTH, 3);
                } else if (3 <= cal.get(Calendar.MONTH) && cal.get(Calendar.MONTH) <= 5) {
                    if (p.getdoj() == false) {
                        cal.set(Calendar.DATE, Integer.parseInt(p.getdate()));
                    }
                    cal.set(Calendar.MONTH, 6);
                } else if (6 <= cal.get(Calendar.MONTH) && cal.get(Calendar.MONTH) <= 8) {
                    if (p.getdoj() == false) {
                        cal.set(Calendar.DATE, Integer.parseInt(p.getdate()));
                    }
                    cal.set(Calendar.MONTH, 9);
                } else if (9 <= cal.get(Calendar.MONTH) && cal.get(Calendar.MONTH) <= 11) {
                    if (p.getdoj() == false) {
                        cal.set(Calendar.DATE, Integer.parseInt(p.getdate()));
                    }
                    cal.set(Calendar.MONTH, 0);
                    cal.add(Calendar.YEAR, 1);
                }
            }
        }
        ed.setTime(cal.getTimeInMillis());
        amcenddate_txt.setText(Formats.DATE.formatValue(ed));
        return ed;
    }

    private Date getdate() {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        d.setTime(cal.getTimeInMillis());
        return d;
    }

    public void init() throws BasicException {
        initComponents();
        ButtonGrp();
        amcnnonam();
        dmang = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

       // amcpanel.setVisible(false);
        // nonamcepanel.setVisible(false);
        amcdoc_txt.setEditable(false);
        jButton2.setVisible(false);
        amccontractorListModel = new ComboBoxValModel(getcontractorList(app));

        amcconcombo.setModel(amccontractorListModel);
        periodmodel = new ComboBoxValModel(getPeriodList(app));
        period_combo.setModel(periodmodel);
        vendorListModel = new ComboBoxValModel(getVendorList());

        vend_combo.setModel(vendorListModel);

      //  amcrad_but.setSelected(true);
       // nonamc_table = AmcNNonamcTableModel.GetNonamcTableModel(app);
        // jTable1.setModel(nonamc_table.getTableModel1());
        Addchng_but.setVisible(false);
        amcstartdate_txt.setEditable(false);
        amcenddate_txt.setEditable(false);
        amcrenewaldate_txt.setEditable(false);
        appdate_txt.setEditable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        amcradio_but = new javax.swing.JRadioButton();
        nonamcradio_but = new javax.swing.JRadioButton();
        amcpanel = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        amcconcombo = new javax.swing.JComboBox();
        amcstartdate_txt = new javax.swing.JTextField();
        amcenddate_txt = new javax.swing.JTextField();
        amcsatrtdatebut = new javax.swing.JButton();
        amcrate_txt = new javax.swing.JTextField();
        amcacc_txt = new javax.swing.JTextField();
        amcamt_txt = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        amcremark_txt = new javax.swing.JTextArea();
        jLabel40 = new javax.swing.JLabel();
        amcrenewaldate_txt = new javax.swing.JTextField();
        remdate_but = new javax.swing.JButton();
        amcdoc_txt = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        paricular_txt = new javax.swing.JTextArea();
        period_combo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        appby_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        appdate_txt = new javax.swing.JTextField();
        appdate_but = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        nonamcepanel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        nonamccontper_txt = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        nonamccontdet_txt = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        nonamcremarks_txt = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        vend_combo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        edit_but = new javax.swing.JButton();
        amcrad_but = new javax.swing.JRadioButton();
        nonamcrad_but = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        add_but = new javax.swing.JButton();
        Addchng_but = new javax.swing.JButton();
        reset_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel12.setPreferredSize(new java.awt.Dimension(1000, 1000));

        amcradio_but.setText("AMC");
        amcradio_but.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                amcradio_butItemStateChanged(evt);
            }
        });

        nonamcradio_but.setText("NON AMC");
        nonamcradio_but.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nonamcradio_butItemStateChanged(evt);
            }
        });

        jLabel30.setText("AMC Contractor");

        jLabel31.setText("AMC Particulars");

        jLabel32.setText("AMC Start Date");

        jLabel33.setText("AMC Period");

        jLabel34.setText("AMC End Date");

        jLabel35.setText("AMC Rate");

        jLabel36.setText("AMC Account");

        jLabel37.setText("AMC Amount");

        jLabel38.setText("AMC Remark");

        jLabel39.setText("AMC Renewal reminder Date");

        amcsatrtdatebut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        amcsatrtdatebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amcsatrtdatebutActionPerformed(evt);
            }
        });

        amcrate_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amcrate_txtKeyTyped(evt);
            }
        });

        amcamt_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amcamt_txtKeyTyped(evt);
            }
        });

        amcremark_txt.setColumns(20);
        amcremark_txt.setRows(5);
        jScrollPane9.setViewportView(amcremark_txt);

        jLabel40.setText("AMC Document Attached");

        remdate_but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        remdate_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remdate_butActionPerformed(evt);
            }
        });

        paricular_txt.setColumns(20);
        paricular_txt.setRows(5);
        jScrollPane3.setViewportView(paricular_txt);

        period_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        period_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                period_comboItemStateChanged(evt);
            }
        });

        jLabel1.setText("Approved By");

        jLabel2.setText("Approved Date");

        appdate_but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        appdate_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appdate_butActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/mime2.png")));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Document");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout amcpanelLayout = new javax.swing.GroupLayout(amcpanel);
        amcpanel.setLayout(amcpanelLayout);
        amcpanelLayout.setHorizontalGroup(
            amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amcpanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34))
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(period_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(amcconcombo, 0, 193, Short.MAX_VALUE)
                                .addComponent(amcamt_txt)
                                .addComponent(amcacc_txt)
                                .addComponent(amcrate_txt)
                                .addComponent(amcenddate_txt)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(amcpanelLayout.createSequentialGroup()
                                .addComponent(amcstartdate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amcsatrtdatebut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(amcpanelLayout.createSequentialGroup()
                                .addComponent(amcrenewaldate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(remdate_but, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(amcpanelLayout.createSequentialGroup()
                                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(appdate_txt)
                                    .addComponent(appby_txt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(amcdoc_txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(appdate_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        amcpanelLayout.setVerticalGroup(
            amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amcpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amcconcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amcsatrtdatebut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amcstartdate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(period_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amcenddate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amcrate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amcacc_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amcamt_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(amcrenewaldate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(remdate_but, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amcdoc_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amcpanelLayout.createSequentialGroup()
                        .addGroup(amcpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(appdate_but, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(amcpanelLayout.createSequentialGroup()
                                .addComponent(appby_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appdate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel42.setText("Contact Person");

        jLabel43.setText("Contact Details");

        jLabel44.setText("Remarks");

        nonamccontdet_txt.setColumns(20);
        nonamccontdet_txt.setRows(5);
        jScrollPane10.setViewportView(nonamccontdet_txt);

        nonamcremarks_txt.setColumns(20);
        nonamcremarks_txt.setRows(5);
        jScrollPane11.setViewportView(nonamcremarks_txt);

        jLabel41.setText("Name of Service Provider");

        javax.swing.GroupLayout nonamcepanelLayout = new javax.swing.GroupLayout(nonamcepanel);
        nonamcepanel.setLayout(nonamcepanelLayout);
        nonamcepanelLayout.setHorizontalGroup(
            nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nonamcepanelLayout.createSequentialGroup()
                .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nonamcepanelLayout.createSequentialGroup()
                        .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(nonamcepanelLayout.createSequentialGroup()
                        .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(vend_combo, 0, 206, Short.MAX_VALUE)
                            .addComponent(nonamccontper_txt))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        nonamcepanelLayout.setVerticalGroup(
            nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nonamcepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vend_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nonamccontper_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(nonamcepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(amcradio_but, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(nonamcradio_but, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(nonamcepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(amcpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amcradio_but)
                    .addComponent(nonamcradio_but))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amcpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(nonamcepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("AMC&NONAMC", jPanel12);

        edit_but.setText("Edit");
        edit_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_butActionPerformed(evt);
            }
        });

        amcrad_but.setText("Amc");
        amcrad_but.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                amcrad_butItemStateChanged(evt);
            }
        });

        nonamcrad_but.setText("NonAmc");
        nonamcrad_but.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nonamcrad_butItemStateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jScrollPane5.setViewportView(jScrollPane2);

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(amcrad_but)
                        .addGap(30, 30, 30)
                        .addComponent(nonamcrad_but))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(633, 633, 633)
                        .addComponent(deactivate_but, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_but, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amcrad_but)
                    .addComponent(nonamcrad_but))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deactivate_but)
                    .addComponent(edit_but))
                .addContainerGap(879, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ViewList", jPanel1);

        jScrollPane1.setViewportView(jTabbedPane1);

        add_but.setText("Add");
        add_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_butActionPerformed(evt);
            }
        });

        Addchng_but.setText("Add Changes");
        Addchng_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Addchng_butActionPerformed(evt);
            }
        });

        reset_but.setText("Reset");
        reset_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(reset_but, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                .addComponent(add_but, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Addchng_but, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reset_but)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(add_but)
                        .addComponent(Addchng_but)))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Addchng_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Addchng_butActionPerformed

        if (amcrad_but.isSelected()) {

            if ((amcstartdate_txt.getText() != null && amcstartdate_txt.getText().trim().length() > 0) && (amcenddate_txt.getText() != null && amcenddate_txt.getText().trim().length() > 0) && (amcrenewaldate_txt.getText() != null && amcrenewaldate_txt.getText().trim().length() > 0)) {
                if (amcconcombo.getSelectedIndex() != -1) {
                    if (amcrate_txt.getText() != null && amcrate_txt.getText().trim().length() > 0) {
                        if (amcacc_txt.getText() != null && amcacc_txt.getText().trim().length() > 0) {
                            if (amcamt_txt.getText() != null && amcamt_txt.getText().trim().length() > 0) {
                                if (amcremark_txt.getText() != null && amcremark_txt.getText().trim().length() > 0) {

                                    try {
                                        int row = jTable1.getSelectedRow();

                                        AmcNNonamcTableModel.AmcInfo amc = amc_table.getList().get(row);

                                        amcid = amc.getID();
                                        Transaction t = new Transaction(app.getSession()) {
                                            @Override
                                            protected Object transact() throws BasicException {

                                                Double rate = null;
                                                Double amount = null;
                                                Double account = null;
                                                String count = null;
                                                Date apprdate = new Date();

                                                Date startdate = new Date();
                                                Date amcrenewaldate = new Date();
                                                Date endtdate = new Date();

                                                Date effectivedate = new Date();
                                                Calendar cal = Calendar.getInstance();
                                                cal.setTimeInMillis(startdate.getTime());
                                                cal.setTimeInMillis(amcrenewaldate.getTime());
                                                cal.setTimeInMillis(endtdate.getTime());
                                                cal.setTimeInMillis(apprdate.getTime());

                                                cal.set(Calendar.HOUR_OF_DAY, 00);
                                                cal.set(Calendar.MINUTE, 00);
                                                cal.set(Calendar.SECOND, 00);
                                                cal.set(Calendar.MILLISECOND, 00);
                                                startdate.setTime(cal.getTimeInMillis());
                                                startdate = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());
                                                amcrenewaldate.setTime(cal.getTimeInMillis());
                                                amcrenewaldate = (Date) Formats.TIMESTAMP.parseValue(amcrenewaldate_txt.getText());
                                                endtdate.setTime(cal.getTimeInMillis());
                                                endtdate = (Date) Formats.TIMESTAMP.parseValue(amcenddate_txt.getText());
                                                apprdate.setTime(cal.getTimeInMillis());
                                                apprdate = (Date) Formats.TIMESTAMP.parseValue(appdate_txt.getText());

                                                rate = (Double) Formats.DOUBLE.parseValue(amcrate_txt.getText());
                                                amount = (Double) Formats.DOUBLE.parseValue(amcamt_txt.getText());
                                                //   account = (Double) Formats.DOUBLE.parseValue(amcacc_txt.getText());
                                                String periodname = period_combo.getSelectedItem().toString();
                                                AID = UUID.randomUUID().toString();

                                                //how to copy paste document files to particular folder
                                                String string = UUID.randomUUID().toString();
                                                String[] parts = string.split("-");
                                                String part1 = parts[0];
                                                String part2 = parts[1];
                                                String flnm = amcdoc_txt.getText();
                                                String name = "";
                                                String x = "";
                                                if (flnm != null) {
                                                  //  String arr[] = flnm.split("/");
                                                    //x = "amc" + part1 + arr[arr.length - 1];;
                                                    x="amc" + part1 +flnm.substring(flnm.lastIndexOf("."),flnm.length());
                                                    name = "./Asset Documents/" + x;
                                                } else {

                                                    name = "";
                                                }
                                                //save data
                                                Object[] param = new Object[]{AID, FixedAsset2.fixedid, getContractorId(), paricular_txt.getText().trim(), startdate, periodname, endtdate, rate, amcacc_txt.getText(), amount, amcremark_txt.getText().trim(), amcrenewaldate, name, app.getAppUserView().getUser().getName(), new Date(), true, apprdate, appby_txt.getText().trim(), amcid};
                                                new PreparedSentence(app.getSession(), "INSERT INTO FA_AMC (ID,FA_ID,CONTRACTOR,PARTICULAR,START_DATE,PERIOD,END_DATE,RATE,ACCOUNT,AMOUNT,REMARK,REMINDER_DATE,DOC_LINK ,INITIATOR,INITIATED_DATE,ACTIVE,APPROVED_DATE,APPROVED_BY,DEACTREFERENCE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(param);
                                                int update_amc_master = new PreparedSentence(app.getSession(), "UPDATE FA_AMC SET ACTIVE=FALSE WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{amcid});

                                                flagkey = 2;
                                                //continuation of copy paste doc
                                                if (filename != null) {
                                                    File srcDir = new File(filename);
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

                                                            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();

                                                        Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

                                                    }
                                                }
                                                return null;
                                            }
                                        };
                                        t.execute();
                                        if (flagkey == 2) {
                                            JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                            reset();
                                        }
                                    } catch (NullPointerException e) {

                                        e.printStackTrace();
                                    } catch (NumberFormatException nfe) {
                                        nfe.printStackTrace();
                                        JOptionPane.showMessageDialog(this, "Please enter the correct price  ", null, JOptionPane.OK_OPTION);

                                    } catch (Exception e) {
                                        e.printStackTrace();

                                        Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Please ensure that Remarks is not empty", null, JOptionPane.OK_OPTION);

                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Please ensure that Amount is not empty", null, JOptionPane.OK_OPTION);

                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that Account is not empty", null, JOptionPane.OK_OPTION);

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Rate is not empty", null, JOptionPane.OK_OPTION);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure that Contractor and Particular is not empty", null, JOptionPane.OK_OPTION);

                }

            } else {
                JOptionPane.showMessageDialog(this, "Please ensure that Date is not empty", null, JOptionPane.OK_OPTION);

            }

        } else {

            try {
                if (vend_combo.getSelectedIndex() != -1 && vend_combo.getSelectedItem().toString() != null) {
                    if (nonamccontper_txt.getText() != null && nonamccontper_txt.getText().trim().length() > 0) {
                        if (nonamccontdet_txt.getText() != null && nonamccontdet_txt.getText().trim().length() > 0) {
                            if (nonamcremarks_txt.getText() != null && nonamcremarks_txt.getText().trim().length() > 0) {
                                Transaction t = new Transaction(app.getSession()) {
                                    @Override
                                    protected Object transact() throws BasicException {

                                        String vendor = vend_combo.getSelectedItem().toString();
                                        int row = jTable1.getSelectedRow();

                                        AmcNNonamcTableModel.NonamcInfo nonamc = amc_table.getList1().get(row);

                                        nonamcid = nonamc.getID();
                                        NAID = UUID.randomUUID().toString();
                                        Object[] param = new Object[]{NAID, FixedAsset2.fixedid, getvendId(), nonamcremarks_txt.getText().trim(), nonamccontper_txt.getText().trim(), nonamccontdet_txt.getText().trim(), app.getAppUserView().getUser().getName(), new Date(), true, nonamcid};
                                        new PreparedSentence(app.getSession(), "INSERT INTO FA_NONAMC ( ID,FA_ID,VENDOR_NAME,REMARKS,CONTACT_PERSON,CONTACT_DETAILS,CREATED_BY,CREATED_DATE,ACTIVE,DAECREFERENCE) VALUES (?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING})).exec(param);

                                        int update_non_amc_master = new PreparedSentence(app.getSession(), "UPDATE FA_NONAMC SET ACTIVE=FALSE WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{nonamcid});
                                        reset();
                                        flagkey = 3;
                                        return null;
                                    }
                                };
                                t.execute();
                                if (flagkey == 3) {
                                    JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
                                nonamc_table = AmcNNonamcTableModel.GetNonamcTableModel(app);
                                jTable1.setModel(nonamc_table.getTableModel1());

                            } else {

                                JOptionPane.showMessageDialog(this, "Please ensure that Remarks is not empty", null, JOptionPane.OK_OPTION);

                            }

                        } else {

                            JOptionPane.showMessageDialog(this, "Please ensure that Contact Details is not empty", null, JOptionPane.OK_OPTION);

                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Contact Person is not empty", null, JOptionPane.OK_OPTION);

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Please ensure that Name of Service Provider is not empty", null, JOptionPane.OK_OPTION);

                }

            } catch (NullPointerException e) {

                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
                new MessageInf(e).show(new JFrame());

            }
        }

    }//GEN-LAST:event_Addchng_butActionPerformed
    public void amcnnonam() {

        String idforactamc = null;
        String idforactnonamc = null;
        int no = 0;
        int nona = 0;
        try {
            idforactamc = (String) new StaticSentence(app.getSession(), "SELECT COUNT(A.ID)  FROM FA_AMC A,FA_MASTER F WHERE A.ACTIVE=TRUE AND A.FA_ID= ? AND A.FA_ID=F.ID ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FixedAsset2.fixedid);
            idforactnonamc = (String) new StaticSentence(app.getSession(), "SELECT COUNT(A.ID)   FROM FA_NONAMC A,FA_MASTER F WHERE A.ACTIVE=TRUE AND A.FA_ID= ? AND A.FA_ID=F.ID ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FixedAsset2.fixedid);

            no = Integer.parseInt(idforactamc);
            nona = Integer.parseInt(idforactnonamc);
        } catch (Exception e) {
        }

        if ((no > nona)) {
            nonamcradio_but.setVisible(false);
            nonamcrad_but.setVisible(false);
            amcradio_but.setSelected(true);
            amcrad_but.setSelected(true);
            amcpanel.setVisible(true);
            nonamcrad_but.setSelected(false);
            nonamcradio_but.setSelected(false);
            nonamcepanel.setVisible(false);
        } else if (((nona > no))) {
            amcradio_but.setVisible(false);
            amcrad_but.setVisible(false);
            nonamcrad_but.setSelected(true);
            nonamcradio_but.setSelected(true);
            nonamcepanel.setVisible(true);
            amcradio_but.setSelected(false);
            amcrad_but.setSelected(false);
            amcpanel.setVisible(false);
        } else if (no == 0 && nona == 0) {
            amcradio_but.setVisible(true);
            nonamcradio_but.setVisible(true);
            amcrad_but.setVisible(true);
            nonamcrad_but.setVisible(true);
            amcpanel.setVisible(true);
            nonamcepanel.setVisible(false);
            amcradio_but.setSelected(true);
            nonamcradio_but.setSelected(false);
            amcrad_but.setSelected(true);
            nonamcrad_but.setSelected(false);
        }

    }
    private void add_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_butActionPerformed

        Addchng_but.setVisible(false);
        // String idforactamc = null;
        //String idforactnonamc = null;

        if (amcradio_but.isSelected()) {

            try {

                if ((amcstartdate_txt.getText() != null && amcstartdate_txt.getText().trim().length() > 0) && (amcrenewaldate_txt.getText() != null && amcrenewaldate_txt.getText().trim().length() > 0)) {
                    if ((amcenddate_txt.getText() != null && amcenddate_txt.getText().trim().length() > 0)) {
                        if (amcconcombo.getSelectedIndex() != -1 && amcconcombo.getSelectedItem() != null && paricular_txt.getText() != null && paricular_txt.getText().trim().length() > 0) {
                            if (amcrate_txt.getText() != null && amcrate_txt.getText().trim().length() > 0 && period_combo.getSelectedIndex() != -1 && period_combo.getSelectedItem() != null) {
                                if (amcacc_txt.getText() != null && amcacc_txt.getText().trim().length() > 0) {
                                    if (amcamt_txt.getText() != null && amcamt_txt.getText().trim().length() > 0) {
                                        if (amcremark_txt.getText() != null && amcremark_txt.getText().trim().length() > 0) {
                                            Transaction t = new Transaction(app.getSession()) {
                                                @Override
                                                protected Object transact() throws BasicException {
                                                    Date end = null;

                                                    Double rate = null;
                                                    Double amount = null;
                                                    Double account = null;

                                                    Date startdate = new Date();
                                                    Date amcrenewaldate = new Date();
                                                    Date endtdate = new Date();
                                                    Date apprdate = new Date();

                                                    Date effectivedate = new Date();
                                                    Calendar cal = Calendar.getInstance();
                                                    cal.setTimeInMillis(startdate.getTime());
                                                    cal.setTimeInMillis(amcrenewaldate.getTime());
                                                    cal.setTimeInMillis(endtdate.getTime());
                                                    cal.setTimeInMillis(apprdate.getTime());

                                                    startdate.setTime(cal.getTimeInMillis());
                                                    startdate = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());
                                                    amcrenewaldate.setTime(cal.getTimeInMillis());
                                                    amcrenewaldate = (Date) Formats.TIMESTAMP.parseValue(amcrenewaldate_txt.getText());
                                                    endtdate.setTime(cal.getTimeInMillis());
                                                    endtdate = (Date) Formats.TIMESTAMP.parseValue(amcenddate_txt.getText());
                                                    apprdate.setTime(cal.getTimeInMillis());
                                                    apprdate = (Date) Formats.TIMESTAMP.parseValue(appdate_txt.getText());

                                                    link = amcdoc_txt.getText();
                                                    rate = (Double) Formats.DOUBLE.parseValue(amcrate_txt.getText());
                                                    amount = (Double) Formats.DOUBLE.parseValue(amcamt_txt.getText());
                                                    String periodname = period_combo.getSelectedItem().toString();
                                                    AID = UUID.randomUUID().toString();

                                                    //how to copy paste document files to particular folder
                                                    String string = UUID.randomUUID().toString();
                                                    String[] parts = string.split("-");
                                                    String part1 = parts[0];
                                                    String part2 = parts[1];
                                                    String flnm = amcdoc_txt.getText();
                                                    String name = "";
                                                    String x = "";
                                                    if (flnm.equals("")) {
                                                        name = "";
                                                    } else {
                                                        String arr[] = flnm.split("/");
                                                        x = "amc" + part1 + arr[arr.length - 1];;
                                                        name = "./Asset Documents/" + x;
                                                    }
                                                    Object[] param = new Object[]{AID, FixedAsset2.fixedid, getContractorId(), paricular_txt.getText().trim(), startdate, periodname, endtdate, rate, amcacc_txt.getText(), amount, amcremark_txt.getText(), amcrenewaldate, name, app.getAppUserView().getUser().getName(), new Date(), true, apprdate, appby_txt.getText().trim()};
                                                    new PreparedSentence(app.getSession(), "INSERT INTO FA_AMC (ID,FA_ID,CONTRACTOR,PARTICULAR,START_DATE,PERIOD,END_DATE,RATE,ACCOUNT,AMOUNT,REMARK,REMINDER_DATE,DOC_LINK ,INITIATOR,INITIATED_DATE,ACTIVE,APPROVED_DATE,APPROVED_BY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING})).exec(param);

                                                    flagkey = 1;
                                                    amcnnonam();
                                                    //continuation of copy paste doc
                                                    if (amcdoc_txt.getText() != null) {
                                                        File srcDir = new File(amcdoc_txt.getText());
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

                                                                Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();

                                                            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

                                                        }
                                                    }

                                                    return null;
                                                }
                                            };
                                            t.execute();
                                            if (flagkey == 1) {
                                                JOptionPane.showMessageDialog(this, "AMC created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                reset();
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Please ensure that Remarks is not empty", null, JOptionPane.OK_OPTION);

                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Please ensure that Amount is not empty", null, JOptionPane.OK_OPTION);

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Please ensure that Account is not empty", null, JOptionPane.OK_OPTION);

                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Please ensure that Rate and Period is not empty", null, JOptionPane.OK_OPTION);

                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that Contractor and Particular is not empty", null, JOptionPane.OK_OPTION);

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "First fill start date then period,it will automatically take end date", null, JOptionPane.OK_OPTION);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure Dates should not be empty", null, JOptionPane.OK_OPTION);

                }

            } catch (NullPointerException e) {

                e.printStackTrace();
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please enter the correct price  ", null, JOptionPane.OK_OPTION);

            } catch (Exception e) {
                e.printStackTrace();

                Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

            }

        } else {

            try {
                if (vend_combo.getSelectedIndex() != -1 && vend_combo.getSelectedItem().toString() != null) {
                    if (nonamccontper_txt.getText() != null && nonamccontper_txt.getText().trim().length() > 0) {
                        if (nonamccontdet_txt.getText() != null && nonamccontdet_txt.getText().trim().length() > 0) {
                            if (nonamcremarks_txt.getText() != null && nonamcremarks_txt.getText().trim().length() > 0) {
                                Transaction t = new Transaction(app.getSession()) {
                                    @Override
                                    protected Object transact() throws BasicException {

                                        NAID = UUID.randomUUID().toString();
                                        Object[] param = new Object[]{NAID, FixedAsset2.fixedid, getvendId(), nonamcremarks_txt.getText().trim(), nonamccontper_txt.getText().trim(), nonamccontdet_txt.getText().trim(), app.getAppUserView().getUser().getName(), new Date(), true};
                                        new PreparedSentence(app.getSession(), "INSERT INTO FA_NONAMC ( ID,FA_ID,VENDOR_NAME,REMARKS,CONTACT_PERSON,CONTACT_DETAILS,CREATED_BY,CREATED_DATE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(param);
                                        // flagkey=5;
                                        nonamc_table = AmcNNonamcTableModel.GetNonamcTableModel(app);
                                        jTable1.setModel(nonamc_table.getTableModel1());
                                        reset();
                                        amcnnonam();
                                        return null;
                                    }
                                };
                                t.execute();
                                //if(flagkey==5){
                                JOptionPane.showMessageDialog(this, "nonamc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                                // }
                            } else {

                                JOptionPane.showMessageDialog(this, "Please ensure that remarks is not empty", null, JOptionPane.OK_OPTION);

                            }

                        } else {

                            JOptionPane.showMessageDialog(this, "Please ensure that contact details is not empty", null, JOptionPane.OK_OPTION);

                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that contact person is not empty", null, JOptionPane.OK_OPTION);

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Please ensure that Name of Service Provider is not empty", null, JOptionPane.OK_OPTION);

                }

            } catch (NullPointerException e) {

                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
                new MessageInf(e).show(new JFrame());

            }

        }

        /*      Addchng_but.setVisible(false);
         String idforactamc = null;
         String idforactnonamc = null;
         int no=0;
         int nona=0;
         try {
         idforactamc = (String) new StaticSentence(app.getSession(), "select count(a.ID)  from fa_amc a,fa_master f where a.ACTIVE=true and a.FA_ID= ? and a.FA_ID=f.ID ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FixedAsset2.fixedid);
         idforactnonamc = (String) new StaticSentence(app.getSession(), "select count(a.ID)   from fa_nonamc a,fa_master f where a.ACTIVE=true and a.FA_ID= ? and a.FA_ID=f.ID ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FixedAsset2.fixedid);

                
         no =Integer.parseInt(idforactamc);
         nona =Integer.parseInt(idforactnonamc);
         } catch (Exception e) {
         }
        
         if ((no>nona) ) {
         nonamcradio_but.setVisible(false);
         nonamcrad_but.setVisible(false);
         amcradio_but.setSelected(true);
         amcrad_but.setSelected(true);
                
         try {
                        
         if ((amcstartdate_txt.getText() != null && amcstartdate_txt.getText().trim().length() > 0) && (amcrenewaldate_txt.getText() != null && amcrenewaldate_txt.getText().trim().length() > 0)) {
         if ((amcenddate_txt.getText() != null && amcenddate_txt.getText().trim().length() > 0)) {
         if (amcconcombo.getSelectedIndex() != -1 && amcconcombo.getSelectedItem() != null && paricular_txt.getText() != null && paricular_txt.getText().trim().length() > 0) {
         if (amcrate_txt.getText() != null && amcrate_txt.getText().trim().length() > 0 && period_combo.getSelectedIndex() != -1 && period_combo.getSelectedItem() != null) {
         if (amcacc_txt.getText() != null && amcacc_txt.getText().trim().length() > 0) {
         if (amcamt_txt.getText() != null && amcamt_txt.getText().trim().length() > 0) {
         if (amcremark_txt.getText() != null && amcremark_txt.getText().trim().length() > 0) {
         Transaction t = new Transaction(app.getSession()) {
         @Override
         protected Object transact() throws BasicException {
         Date end = null;

         Double rate = null;
         Double amount = null;
         Double account = null;

         Date startdate = new Date();
         Date amcrenewaldate = new Date();
         Date endtdate = new Date();
         Date apprdate = new Date();

         Date effectivedate = new Date();
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(startdate.getTime());
         cal.setTimeInMillis(amcrenewaldate.getTime());
         cal.setTimeInMillis(endtdate.getTime());
         cal.setTimeInMillis(apprdate.getTime());

         startdate.setTime(cal.getTimeInMillis());
         startdate = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());
         amcrenewaldate.setTime(cal.getTimeInMillis());
         amcrenewaldate = (Date) Formats.TIMESTAMP.parseValue(amcrenewaldate_txt.getText());
         endtdate.setTime(cal.getTimeInMillis());
         endtdate = (Date) Formats.TIMESTAMP.parseValue(amcenddate_txt.getText());
         apprdate.setTime(cal.getTimeInMillis());
         apprdate = (Date) Formats.TIMESTAMP.parseValue(appdate_txt.getText());

         link = amcdoc_txt.getText();
         rate = (Double) Formats.DOUBLE.parseValue(amcrate_txt.getText());
         amount = (Double) Formats.DOUBLE.parseValue(amcamt_txt.getText());
         String periodname = period_combo.getSelectedItem().toString();
         AID = UUID.randomUUID().toString();

         //how to copy paste document files to particular folder
         String string = UUID.randomUUID().toString();
         String[] parts = string.split("-");
         String part1 = parts[0];
         String part2 = parts[1];
         String flnm = amcdoc_txt.getText();
         String name = "";
         String x = "";
         if (flnm.equals("")) {
         name = "";
         } else {
         String arr[] = flnm.split("/");
         x = "amc" + part1 + arr[arr.length - 1];;
         name = "./Asset Documents/" + x;
         }
         Object[] param = new Object[]{AID, FixedAsset2.fixedid, getContractorId(), paricular_txt.getText().trim(), startdate, periodname, endtdate, rate, amcacc_txt.getText(), amount, amcremark_txt.getText(), amcrenewaldate, name, app.getAppUserView().getUser().getName(), new Date(), true, apprdate, appby_txt.getText().trim()};
         new PreparedSentence(app.getSession(), "INSERT INTO fa_amc (ID,FA_ID,CONTRACTOR,PARTICULAR,START_DATE,PERIOD,END_DATE,RATE,ACCOUNT,AMOUNT,REMARK,REMINDER_DATE,DOC_LINK ,INITIATOR,INITIATED_DATE,ACTIVE,APPROVED_DATE,APPROVED_BY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING})).exec(param);

         //continuation of copy paste doc
         if (filename != null) {
         File srcDir = new File(filename);
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

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }
         } catch (Exception e) {
         e.printStackTrace();

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }
         }

         return null;
         }
         };
         t.execute();
                                                     
         JOptionPane.showMessageDialog(this, "amc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
         amcnnonam();
         reset();
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  remarks  is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  amount is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  account is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  rate and period is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  contractor and particular is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "First fill start date then period,it will automatically take end date", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  start date,end date and renewal date is not empty", null, JOptionPane.OK_OPTION);

         }


         } catch (NullPointerException e) {

         e.printStackTrace();
         } catch (NumberFormatException nfe) {
         nfe.printStackTrace();
         JOptionPane.showMessageDialog(this, "Please enter  the correct price  ", null, JOptionPane.OK_OPTION);

         } catch (Exception e) {
         e.printStackTrace();

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }

            
         } else if(((nona>no)  )) {
         amcradio_but.setVisible(false);
         amcrad_but.setVisible(false);
         nonamcrad_but.setSelected(true);
         nonamcradio_but.setSelected(true);
         try {
         if (vend_combo.getSelectedIndex() != -1 && vend_combo.getSelectedItem().toString() != null) {
         if (nonamccontper_txt.getText() != null && nonamccontper_txt.getText().trim().length() > 0) {
         if (nonamccontdet_txt.getText() != null && nonamccontdet_txt.getText().trim().length() > 0) {
         if (nonamcremarks_txt.getText() != null && nonamcremarks_txt.getText().trim().length() > 0) {
         Transaction t = new Transaction(app.getSession()) {
         @Override
         protected Object transact() throws BasicException {

         NAID = UUID.randomUUID().toString();
         Object[] param = new Object[]{NAID, FixedAsset2.fixedid, getvendId(), nonamcremarks_txt.getText().trim(), nonamccontper_txt.getText().trim(), nonamccontdet_txt.getText().trim(), app.getAppUserView().getUser().getName(), new Date(), true};
         new PreparedSentence(app.getSession(), "INSERT INTO fa_nonamc ( ID,FA_ID,VENDOR_NAME,REMARKS,CONTACT_PERSON,CONTACT_DETAILS,CREATED_BY,CREATED_DATE,ACTIVE\n) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(param);
         return null;
         }
         };
         t.execute();
         JOptionPane.showMessageDialog(this, "nonamc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    
         amcnnonam();
         reset();
         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  remarks is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  contact details is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  contact person is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  Name of Service Provider is not empty", null, JOptionPane.OK_OPTION);

         }

         } catch (NullPointerException e) {

         e.printStackTrace();
         } catch (Exception e) {
         e.printStackTrace();
         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
         new MessageInf(e).show(new JFrame());

         }
            
         } else if(no==0 && nona==0) {
         amcradio_but.setVisible(true);
         amcrad_but.setVisible(true);
         nonamcrad_but.setSelected(true);
         nonamcradio_but.setSelected(true);
       

         if (amcradio_but.isSelected()) {
            

         try {
         if (amcrad_but.isSelected()) {
         if ((amcstartdate_txt.getText() != null && amcstartdate_txt.getText().trim().length() > 0) && (amcrenewaldate_txt.getText() != null && amcrenewaldate_txt.getText().trim().length() > 0)) {
         if ((amcenddate_txt.getText() != null && amcenddate_txt.getText().trim().length() > 0)) {
         if (amcconcombo.getSelectedIndex() != -1 && amcconcombo.getSelectedItem() != null && paricular_txt.getText() != null && paricular_txt.getText().trim().length() > 0) {
         if (amcrate_txt.getText() != null && amcrate_txt.getText().trim().length() > 0 && period_combo.getSelectedIndex() != -1 && period_combo.getSelectedItem() != null) {
         if (amcacc_txt.getText() != null && amcacc_txt.getText().trim().length() > 0) {
         if (amcamt_txt.getText() != null && amcamt_txt.getText().trim().length() > 0) {
         if (amcremark_txt.getText() != null && amcremark_txt.getText().trim().length() > 0) {
         Transaction t = new Transaction(app.getSession()) {
         @Override
         protected Object transact() throws BasicException {
         Date end = null;

         Double rate = null;
         Double amount = null;
         Double account = null;

         Date startdate = new Date();
         Date amcrenewaldate = new Date();
         Date endtdate = new Date();
         Date apprdate = new Date();

         Date effectivedate = new Date();
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(startdate.getTime());
         cal.setTimeInMillis(amcrenewaldate.getTime());
         cal.setTimeInMillis(endtdate.getTime());
         cal.setTimeInMillis(apprdate.getTime());

         startdate.setTime(cal.getTimeInMillis());
         startdate = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());
         amcrenewaldate.setTime(cal.getTimeInMillis());
         amcrenewaldate = (Date) Formats.TIMESTAMP.parseValue(amcrenewaldate_txt.getText());
         endtdate.setTime(cal.getTimeInMillis());
         endtdate = (Date) Formats.TIMESTAMP.parseValue(amcenddate_txt.getText());
         apprdate.setTime(cal.getTimeInMillis());
         apprdate = (Date) Formats.TIMESTAMP.parseValue(appdate_txt.getText());

         link = amcdoc_txt.getText();
         rate = (Double) Formats.DOUBLE.parseValue(amcrate_txt.getText());
         amount = (Double) Formats.DOUBLE.parseValue(amcamt_txt.getText());
         String periodname = period_combo.getSelectedItem().toString();
         AID = UUID.randomUUID().toString();

         //how to copy paste document files to particular folder
         String string = UUID.randomUUID().toString();
         String[] parts = string.split("-");
         String part1 = parts[0];
         String part2 = parts[1];
         String flnm = amcdoc_txt.getText();
         String name = "";
         String x = "";
         if (flnm.equals("")) {
         name = "";
         } else {
         String arr[] = flnm.split("/");
         x = "amc" + part1 + arr[arr.length - 1];;
         name = "./Asset Documents/" + x;
         }
         Object[] param = new Object[]{AID, FixedAsset2.fixedid, getContractorId(), paricular_txt.getText().trim(), startdate, periodname, endtdate, rate, amcacc_txt.getText(), amount, amcremark_txt.getText(), amcrenewaldate, name, app.getAppUserView().getUser().getName(), new Date(), true, apprdate, appby_txt.getText().trim()};
         new PreparedSentence(app.getSession(), "INSERT INTO fa_amc (ID,FA_ID,CONTRACTOR,PARTICULAR,START_DATE,PERIOD,END_DATE,RATE,ACCOUNT,AMOUNT,REMARK,REMINDER_DATE,DOC_LINK ,INITIATOR,INITIATED_DATE,ACTIVE,APPROVED_DATE,APPROVED_BY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING})).exec(param);

         //continuation of copy paste doc
         if (filename != null) {
         File srcDir = new File(filename);
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

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }
         } catch (Exception e) {
         e.printStackTrace();

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }
         }

         return null;
         }
         };
         t.execute();
         JOptionPane.showMessageDialog(this, "amc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
         amcnnonam();
         reset();
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  remarks  is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  amount is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  account is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  rate and period is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  contractor and particular is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "First fill start date then period,it will automatically take end date", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  start date,end date and renewal date is not empty", null, JOptionPane.OK_OPTION);

         }

         }

         } catch (NullPointerException e) {

         e.printStackTrace();
         } catch (NumberFormatException nfe) {
         nfe.printStackTrace();
         JOptionPane.showMessageDialog(this, "Please enter  the correct price  ", null, JOptionPane.OK_OPTION);

         } catch (Exception e) {
         e.printStackTrace();

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }

            
         } else {
            

         try {
         if (vend_combo.getSelectedIndex() != -1 && vend_combo.getSelectedItem().toString() != null) {
         if (nonamccontper_txt.getText() != null && nonamccontper_txt.getText().trim().length() > 0) {
         if (nonamccontdet_txt.getText() != null && nonamccontdet_txt.getText().trim().length() > 0) {
         if (nonamcremarks_txt.getText() != null && nonamcremarks_txt.getText().trim().length() > 0) {
         Transaction t = new Transaction(app.getSession()) {
         @Override
         protected Object transact() throws BasicException {

         NAID = UUID.randomUUID().toString();
         Object[] param = new Object[]{NAID, FixedAsset2.fixedid, getvendId(), nonamcremarks_txt.getText().trim(), nonamccontper_txt.getText().trim(), nonamccontdet_txt.getText().trim(), app.getAppUserView().getUser().getName(), new Date(), true};
         new PreparedSentence(app.getSession(), "INSERT INTO fa_nonamc ( ID,FA_ID,VENDOR_NAME,REMARKS,CONTACT_PERSON,CONTACT_DETAILS,CREATED_BY,CREATED_DATE,ACTIVE\n) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(param);
         return null;
         }
         };
         t.execute();
         JOptionPane.showMessageDialog(this, "nonamc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    
         amcnnonam();
         reset();
         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  remarks is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  contact details is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  contact person is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  Name of Service Provider is not empty", null, JOptionPane.OK_OPTION);

         }

         } catch (NullPointerException e) {

         e.printStackTrace();
         } catch (Exception e) {
         e.printStackTrace();
         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
         new MessageInf(e).show(new JFrame());

         }
           
         }

         }
         */
        /*  Addchng_but.setVisible(false);
         String idforactamc = null;
         String idforactnonamc = null;

         if (amcradio_but.isSelected()) {
         try {
         idforactamc = (String) new StaticSentence(app.getSession(), "select a.FA_ID  from fa_amc a,fa_master f where a.ACTIVE=true and a.FA_ID= ? and a.FA_ID=f.ID ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FixedAsset2.fixedid);

         } catch (Exception e) {
         }
         if (idforactamc != null) {

         JOptionPane.showMessageDialog(this, "Amc alrady exist,Deactivate old one and Creat new amc!", null, JOptionPane.OK_OPTION);

         } else {

         try {
         if (amcrad_but.isSelected()) {
         if ((amcstartdate_txt.getText() != null && amcstartdate_txt.getText().trim().length() > 0) && (amcrenewaldate_txt.getText() != null && amcrenewaldate_txt.getText().trim().length() > 0)) {
         if ((amcenddate_txt.getText() != null && amcenddate_txt.getText().trim().length() > 0)) {
         if (amcconcombo.getSelectedIndex() != -1 && amcconcombo.getSelectedItem() != null && paricular_txt.getText() != null && paricular_txt.getText().trim().length() > 0) {
         if (amcrate_txt.getText() != null && amcrate_txt.getText().trim().length() > 0 && period_combo.getSelectedIndex() != -1 && period_combo.getSelectedItem() != null) {
         if (amcacc_txt.getText() != null && amcacc_txt.getText().trim().length() > 0) {
         if (amcamt_txt.getText() != null && amcamt_txt.getText().trim().length() > 0) {
         if (amcremark_txt.getText() != null && amcremark_txt.getText().trim().length() > 0) {
         Transaction t = new Transaction(app.getSession()) {
         @Override
         protected Object transact() throws BasicException {
         Date end = null;

         Double rate = null;
         Double amount = null;
         Double account = null;

         Date startdate = new Date();
         Date amcrenewaldate = new Date();
         Date endtdate = new Date();
         Date apprdate = new Date();

         Date effectivedate = new Date();
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(startdate.getTime());
         cal.setTimeInMillis(amcrenewaldate.getTime());
         cal.setTimeInMillis(endtdate.getTime());
         cal.setTimeInMillis(apprdate.getTime());

         startdate.setTime(cal.getTimeInMillis());
         startdate = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());
         amcrenewaldate.setTime(cal.getTimeInMillis());
         amcrenewaldate = (Date) Formats.TIMESTAMP.parseValue(amcrenewaldate_txt.getText());
         endtdate.setTime(cal.getTimeInMillis());
         endtdate = (Date) Formats.TIMESTAMP.parseValue(amcenddate_txt.getText());
         apprdate.setTime(cal.getTimeInMillis());
         apprdate = (Date) Formats.TIMESTAMP.parseValue(appdate_txt.getText());

         link = amcdoc_txt.getText();
         rate = (Double) Formats.DOUBLE.parseValue(amcrate_txt.getText());
         amount = (Double) Formats.DOUBLE.parseValue(amcamt_txt.getText());
         String periodname = period_combo.getSelectedItem().toString();
         AID = UUID.randomUUID().toString();

         //how to copy paste document files to particular folder
         String string = UUID.randomUUID().toString();
         String[] parts = string.split("-");
         String part1 = parts[0];
         String part2 = parts[1];
         String flnm = amcdoc_txt.getText();
         String name = "";
         String x = "";
         if (flnm.equals("")) {
         name = "";
         } else {
         String arr[] = flnm.split("/");
         x = "amc" + part1 + arr[arr.length - 1];;
         name = "./Asset Documents/" + x;
         }
         Object[] param = new Object[]{AID, FixedAsset2.fixedid, getContractorId(), paricular_txt.getText().trim(), startdate, periodname, endtdate, rate, amcacc_txt.getText(), amount, amcremark_txt.getText(), amcrenewaldate, name, app.getAppUserView().getUser().getName(), new Date(), true, apprdate, appby_txt.getText().trim()};
         new PreparedSentence(app.getSession(), "INSERT INTO fa_amc (ID,FA_ID,CONTRACTOR,PARTICULAR,START_DATE,PERIOD,END_DATE,RATE,ACCOUNT,AMOUNT,REMARK,REMINDER_DATE,DOC_LINK ,INITIATOR,INITIATED_DATE,ACTIVE,APPROVED_DATE,APPROVED_BY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.TIMESTAMP, Datas.STRING})).exec(param);

         //continuation of copy paste doc
         if (filename != null) {
         File srcDir = new File(filename);
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

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }
         } catch (Exception e) {
         e.printStackTrace();

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }
         }

         return null;
         }
         };
         t.execute();
         JOptionPane.showMessageDialog(this, "amc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

         reset();
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  remarks  is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  amount is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  account is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  rate and period is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  contractor and particular is not empty", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "First fill start date then period,it will automatically take end date", null, JOptionPane.OK_OPTION);

         }
         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  start date,end date and renewal date is not empty", null, JOptionPane.OK_OPTION);

         }

         }

         } catch (NullPointerException e) {

         e.printStackTrace();
         } catch (NumberFormatException nfe) {
         nfe.printStackTrace();
         JOptionPane.showMessageDialog(this, "Please enter  the correct price  ", null, JOptionPane.OK_OPTION);

         } catch (Exception e) {
         e.printStackTrace();

         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);

         }

         }
         } else {
         try {
         idforactnonamc = (String) new StaticSentence(app.getSession(), "select a.FA_ID  from fa_nonamc a,fa_master f where a.ACTIVE=true and a.FA_ID= ? and a.FA_ID=f.ID ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(FixedAsset2.fixedid);

         } catch (Exception e) {
         }
         if (idforactnonamc != null) {
         JOptionPane.showMessageDialog(this, "NonAmc alrady exist,Deactivate old one and Creat new nonamc!", null, JOptionPane.OK_OPTION);
         } else {

         try {
         if (vend_combo.getSelectedIndex() != -1 && vend_combo.getSelectedItem().toString() != null) {
         if (nonamccontper_txt.getText() != null && nonamccontper_txt.getText().trim().length() > 0) {
         if (nonamccontdet_txt.getText() != null && nonamccontdet_txt.getText().trim().length() > 0) {
         if (nonamcremarks_txt.getText() != null && nonamcremarks_txt.getText().trim().length() > 0) {
         Transaction t = new Transaction(app.getSession()) {
         @Override
         protected Object transact() throws BasicException {

         NAID = UUID.randomUUID().toString();
         Object[] param = new Object[]{NAID, FixedAsset2.fixedid, getvendId(), nonamcremarks_txt.getText().trim(), nonamccontper_txt.getText().trim(), nonamccontdet_txt.getText().trim(), app.getAppUserView().getUser().getName(), new Date(), true};
         new PreparedSentence(app.getSession(), "INSERT INTO fa_nonamc ( ID,FA_ID,VENDOR_NAME,REMARKS,CONTACT_PERSON,CONTACT_DETAILS,CREATED_BY,CREATED_DATE,ACTIVE\n) VALUES (?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN})).exec(param);
         return null;
         }
         };
         t.execute();
         JOptionPane.showMessageDialog(this, "nonamc created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
         System.out.println("non amc id in nonamc:" + NAID);

         reset();
         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  remarks is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  contact details is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {
         JOptionPane.showMessageDialog(this, "Please ensure that  contact person is not empty", null, JOptionPane.OK_OPTION);

         }

         } else {

         JOptionPane.showMessageDialog(this, "Please ensure that  Name of Service Provider is not empty", null, JOptionPane.OK_OPTION);

         }

         } catch (NullPointerException e) {

         e.printStackTrace();
         } catch (Exception e) {
         e.printStackTrace();
         Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
         new MessageInf(e).show(new JFrame());

         }
         }
         }
         */
    }//GEN-LAST:event_add_butActionPerformed

    private void reset_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_butActionPerformed
        reset();
    }//GEN-LAST:event_reset_butActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();

        if (tabno == 1) {
            try {
                if (amcrad_but.isSelected()) {
                    amc_table = AmcNNonamcTableModel.GetAmcTableModel(app);

                    jTable1.setModel(amc_table.getTableModel());
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                    TableColumnModel cmodel = jTable1.getColumnModel();
                    cmodel.getColumn(0).setPreferredWidth(25);
                    cmodel.getColumn(1).setPreferredWidth(100);
                    cmodel.getColumn(2).setPreferredWidth(100);
                    cmodel.getColumn(3).setPreferredWidth(80);
                    cmodel.getColumn(4).setPreferredWidth(100);
                    cmodel.getColumn(5).setPreferredWidth(80);
                    cmodel.getColumn(6).setPreferredWidth(130);
                }

            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        }

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void deactivate_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_butActionPerformed
        if (jTable1.getSelectedRow() != -1) {
            int bill = JOptionPane.showConfirmDialog(jTable1, " Do you want to deactivate ?? ", "Deactivation", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

                int row = jTable1.getSelectedRow();
                if (amcrad_but.isSelected()) {
                    AmcNNonamcTableModel.AmcInfo amc = amc_table.getList().get(row);

                    amcdeatid = amc.getID();
                    deactamc();

                } else {
                    AmcNNonamcTableModel.NonamcInfo nonamc = amc_table.getList1().get(row);
                    nonamcdeactid = nonamc.getID();
                    deactnonamc();

                }

            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select row in the table ", null, JOptionPane.WARNING_MESSAGE);

        }

        amcnnonam();
    }//GEN-LAST:event_deactivate_butActionPerformed

    private void nonamcrad_butItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nonamcrad_butItemStateChanged
        try {
            amc_table = AmcNNonamcTableModel.GetNonamcTableModel(app);
            jTable1.setModel(amc_table.getTableModel1());
        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
            new MessageInf(e).show(new JFrame());

        }
    }//GEN-LAST:event_nonamcrad_butItemStateChanged

    private void amcrad_butItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_amcrad_butItemStateChanged
        try {
            amc_table = AmcNNonamcTableModel.GetAmcTableModel(app);
            jTable1.setModel(amc_table.getTableModel());
        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
            new MessageInf(e).show(new JFrame());

        }
    }//GEN-LAST:event_amcrad_butItemStateChanged

    private void edit_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_butActionPerformed
        if (jTable1.getSelectedRow() != -1) {
            int bill = JOptionPane.showConfirmDialog(jTable1, " Do you want to Edit Data ?? ", "Editing Menu", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

                if (jTable1.getSelectedRow() < amc_table.getSize()) {
                    int row = jTable1.getSelectedRow();
                    if (amcrad_but.isSelected()) {
                        AmcNNonamcTableModel.AmcInfo amc = amc_table.getList().get(row);

                        amcradio_but.setSelected(true);
                        nonamcepanel.setVisible(false);
                        amcpanel.setVisible(true);
                        nonamcradio_but.setVisible(false);
                        amcid = amc.getID();

                        String contractor = amc.getContrator();
                        String particular = amc.getParticular();
                        String startdate = amc.getStartDate();
                        String enddate = amc.getEndDate();
                        String period = amc.getPeriod();
                        Double rate = amc.getRate();
                        Double amount = amc.getAmount();
                        String account = amc.getaccount();
                        String reminderdate = amc.getRenewalDate();
                        doc_link = amc.getDocLink();
                        String remarks = amc.getRemark();
                        String appby = amc.getApprovedBy();
                        String appdate = amc.getApprovedDate();
                        if (doc_link.equals("")) {
                            jButton2.setVisible(false);

                        } else {
                            jButton2.setVisible(true);
                        }
                        appby_txt.setText(appby + "");
                        appdate_txt.setText(appdate + "");
                        paricular_txt.setText(particular + "");
                        amcstartdate_txt.setText(startdate + "");
                        amcenddate_txt.setText(enddate + "");
                        if (rate != null) {
                            String r = decimalFormat.format(rate);
                            amcrate_txt.setText(r + "%" + "");
                        } else {
                            amcrate_txt.setText("");
                        }
                        if (amount != null) {
                            amcamt_txt.setText(decimalFormat.format(amount) + "");
                        } else {
                            amcamt_txt.setText("");
                        }
                        amcacc_txt.setText(account + "");
                        amcrenewaldate_txt.setText(reminderdate + "");
                        if (doc_link != null) {
                            amcdoc_txt.setText(doc_link + "");
                        } else {
                            amcdoc_txt.setText("");
                        }
                        amcremark_txt.setText(remarks + "");

                        List<Object> p1_list = new ArrayList<Object>();
                        List<Object> p2_list = new ArrayList<Object>();

                        try {

                            p1_list = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM VENDOR WHERE ID=? AND ACTIVE=TRUE ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(contractor);
                            p2_list = (List<Object>) new StaticSentence(app.getSession(), " SELECT NAME FROM PERIODICITY WHERE ID=? AND ACTIVE=TRUE  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(period);

                        } catch (BasicException ex) {
                            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (p1_list.size() != 0) {
                            amcconcombo.setSelectedItem(p1_list.get(0));
                        } else {
                            amcconcombo.setSelectedIndex(-1);
                        }
                        if (p2_list.size() != 0) {
                            period_combo.setSelectedItem(p2_list.get(0));
                        } else {
                            period_combo.setSelectedIndex(-1);
                        }

                        for (int i = 0; i < amcconcombo.getItemCount(); i++) {
                            if (amcconcombo.getItemAt(i).toString().equals(contractor)) {
                                amcconcombo.setSelectedIndex(i);
                                break;
                            }

                        }

                        for (int i = 0; i < period_combo.getItemCount(); i++) {
                            if (period_combo.getItemAt(i).toString().equals(period)) {
                                period_combo.setSelectedIndex(i);
                                break;
                            }

                        }

                    } else if (nonamcrad_but.isSelected()) {
                        AmcNNonamcTableModel.NonamcInfo nonamc = amc_table.getList1().get(row);

                        nonamcradio_but.setSelected(true);
                        nonamcepanel.setVisible(true);
                        amcpanel.setVisible(false);
                        amcradio_but.setVisible(false);
                        nonamcradio_but.setVisible(true);
                        nonamcid = nonamc.getID();

                        String vendor = nonamc.getvendor();
                        String contact_per = nonamc.getcontact_per();
                        String contact_det = nonamc.getcontact_det();
                        String remarks1 = nonamc.getRemark();

                        nonamccontper_txt.setText(contact_per + "");
                        nonamccontdet_txt.setText(contact_det + "");
                        nonamcremarks_txt.setText(remarks1 + "");

                        List<Object> List1 = new ArrayList<Object>();

                        try {

                            List1 = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM VENDOR WHERE ID=? AND ACTIVE=TRUE ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(vendor);

                        } catch (BasicException ex) {
                            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (List1.size() != 0) {
                            vend_combo.setSelectedItem(List1.get(0));
                        } else {
                            vend_combo.setSelectedIndex(-1);
                        }

                        for (int i = 0; i < vend_combo.getItemCount(); i++) {
                            if (vend_combo.getItemAt(i).toString().equals(vendor)) {
                                vend_combo.setSelectedIndex(i);
                                break;
                            }

                        }

                    }

                    jTabbedPane1.setSelectedIndex(0);
                    Addchng_but.setVisible(true);
                    add_but.setVisible(false);

                }
            }
        }
    }//GEN-LAST:event_edit_butActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            File file1 = new File(amcdoc_txt.getText().replace("./", ""));

            if (file1.exists() == true) {

                java.awt.Desktop.getDesktop().open(file1);
            } else {
                JOptionPane.showMessageDialog(this, "File not Found", null, JOptionPane.OK_OPTION);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf", "MS Office Documents", "docx", "xlsx", "pptx",
                "html", "wpd", "wp", "doc", "zip", "Library file");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filename = selectedFile.getAbsolutePath();
            amcdoc_txt.setText(filename);
            file = new File(filename);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void appdate_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appdate_butActionPerformed
        appdate_txt.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(appdate_txt.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {

            appdate_txt.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_appdate_butActionPerformed

    private void period_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_period_comboItemStateChanged
        try {

            if (period_combo.getSelectedIndex() != -1) {
                if (amcstartdate_txt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "First fill the Start date  ... !! ", "Error", JOptionPane.WARNING_MESSAGE);
                    period_combo.setSelectedIndex(-1);
                } else {

                    Date d = new Date();
                    String type = getPeriodType();

                    int periodno = getPeriodNo();

                    Date enddate = new Date();
                    Date startdate = new Date();
                    Date amcrenewaldate = new Date();

                    Date effectivedate = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(startdate.getTime());
                    cal.setTimeInMillis(enddate.getTime());
                    cal.setTimeInMillis(d.getTime());

                    cal.set(Calendar.HOUR_OF_DAY, 00);
                    cal.set(Calendar.MINUTE, 00);
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                    startdate.setTime(cal.getTimeInMillis());
                    enddate.setTime(cal.getTimeInMillis());
                    d.setTime(cal.getTimeInMillis());
                    startdate = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());

                    cal.setTime(startdate);

                    if (type.equals("Days")) {

                        cal.add(Calendar.DAY_OF_YEAR, periodno);
                        enddate = cal.getTime();
                    } else if (type.equals("Months")) {
                        cal.add(Calendar.MONTH, periodno);
                        enddate = cal.getTime();

                    } else if (type.equals("Quaterly")) {

                        cal.add(Calendar.MONTH, 3);
                        enddate = cal.getTime();

                    } else if (type.equals("Years")) {

                        cal.add(Calendar.YEAR, periodno);
                        enddate = cal.getTime();

                    }

                    amcenddate_txt.setText(Formats.TIMESTAMP.formatValue(enddate));

                }
            }
        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, e);
            new MessageInf(e).show(new JFrame());

        }
    }//GEN-LAST:event_period_comboItemStateChanged

    private void remdate_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remdate_butActionPerformed
        amcrenewaldate_txt.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(amcrenewaldate_txt.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            Date date1 = new Date();
            if ((d.compareTo(date1) < 0)) {
                amcrenewaldate_txt.setText("");
                JOptionPane.showMessageDialog(this, "Reminder date should be greater than System date  ... !! ", "Error", JOptionPane.WARNING_MESSAGE);

            } else {
                amcrenewaldate_txt.setText(Formats.TIMESTAMP.formatValue(d));

            }

        }


    }//GEN-LAST:event_remdate_butActionPerformed

    private void amcrate_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amcrate_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_amcrate_txtKeyTyped

    private void amcsatrtdatebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amcsatrtdatebutActionPerformed
        amcstartdate_txt.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(amcstartdate_txt.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {

            amcstartdate_txt.setText(Formats.TIMESTAMP.formatValue(d));
            period_combo.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_amcsatrtdatebutActionPerformed

    private void nonamcradio_butItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nonamcradio_butItemStateChanged
        if (nonamcradio_but.isSelected()) {
            nonamcepanel.setVisible(true);
            amcpanel.setVisible(false);
        } else {
            nonamcepanel.setVisible(false);
        }
    }//GEN-LAST:event_nonamcradio_butItemStateChanged

    private void amcradio_butItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_amcradio_butItemStateChanged
        if (amcradio_but.isSelected()) {
            amcpanel.setVisible(true);
            nonamcepanel.setVisible(false);
        } else {

            amcpanel.setVisible(false);

        }
    }//GEN-LAST:event_amcradio_butItemStateChanged

    private void amcamt_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amcamt_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_amcamt_txtKeyTyped
    public void reset() {
        jButton2.setVisible(false);
        amcconcombo.setSelectedIndex(-1);
        paricular_txt.setText(null);
        amcstartdate_txt.setText(null);
        period_combo.setSelectedIndex(-1);
        amcenddate_txt.setText(null);
        amcrate_txt.setText(null);
        amcacc_txt.setText(null);
        amcamt_txt.setText(null);
        amcremark_txt.setText(null);
        amcrenewaldate_txt.setText(null);
        amcdoc_txt.setText(null);
        vend_combo.setSelectedIndex(-1);
        nonamccontper_txt.setText(null);
        nonamccontdet_txt.setText(null);
        appby_txt.setText(null);
        appdate_txt.setText(null);
        nonamcremarks_txt.setText(null);
        add_but.setVisible(true);
        Addchng_but.setVisible(false);
        // nonamcradio_but.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "AMC & Non-AMC";
    }

    private void deactamc() {
        try {

            new PreparedSentence(app.getSession(), "UPDATE FA_AMC  SET  ACTIVE=0  , DEACBY=? , DEACDATE=?  WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), amcdeatid});

            amc_table = AmcNNonamcTableModel.GetAmcTableModel(app);
            jTable1.setModel(amc_table.getTableModel());
            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deactnonamc() {
        try {

            new PreparedSentence(app.getSession(), "UPDATE FA_NONAMC  SET  ACTIVE=0  , DEACBY=? , DEACDATE=?  WHERE ID = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), nonamcdeactid});

            nonamc_table = AmcNNonamcTableModel.GetNonamcTableModel(app);
            jTable1.setModel(nonamc_table.getTableModel1());
            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date addOnePeriod(Date d, Periodicity p1) {
        Date edate = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(d.getTime());
        String type = getPeriodType();
        int no = getPeriodNo();
        if (type.equals("Days")) {
            cal1.add(Calendar.DATE, no);
        } else if (type.equals("Months")) {
            cal1.set(Calendar.DATE, Integer.parseInt(p1.getdate()));
            cal1.add(Calendar.MONTH, no);
        } else if (type.equals("Quaterly")) {
            cal1.set(Calendar.DATE, Integer.parseInt(p1.getdate()));
            cal1.add(Calendar.MONTH, 3);
        } else if (type.equals("Years")) {
            cal1.set(Calendar.DATE, Integer.parseInt(p1.getdate()));
            cal1.set(Calendar.MONTH, p1.getmonth());
            cal1.add(Calendar.YEAR, no);
        }
        cal1.add(Calendar.DATE, -1);
        cal1.set(Calendar.HOUR_OF_DAY, 23);
        cal1.set(Calendar.MINUTE, 59);
        cal1.set(Calendar.SECOND, 59);
        edate.setTime(cal1.getTimeInMillis());
        return edate;
    }

    public void loaddata() throws BasicException {

    }

    public void loadProperties(AppConfig config) {

        amcdoc_txt.setText(config.getProperty("folder"));

        dirty.setDirty(false);
    }

    public void saveProperties(AppConfig config) {

        config.setProperty("FOLDER", amcdoc_txt.getText());

        dirty.setDirty(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          

    public boolean hasChanged() {
        return dirty.isDirty();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Addchng_but;
    private javax.swing.JButton add_but;
    private javax.swing.JTextField amcacc_txt;
    private javax.swing.JTextField amcamt_txt;
    private javax.swing.JComboBox amcconcombo;
    private javax.swing.JTextField amcdoc_txt;
    private javax.swing.JTextField amcenddate_txt;
    private javax.swing.JPanel amcpanel;
    private javax.swing.JRadioButton amcrad_but;
    private javax.swing.JRadioButton amcradio_but;
    private javax.swing.JTextField amcrate_txt;
    private javax.swing.JTextArea amcremark_txt;
    private javax.swing.JTextField amcrenewaldate_txt;
    private javax.swing.JButton amcsatrtdatebut;
    private javax.swing.JTextField amcstartdate_txt;
    private javax.swing.JTextField appby_txt;
    private javax.swing.JButton appdate_but;
    private javax.swing.JTextField appdate_txt;
    private javax.swing.JButton deactivate_but;
    private javax.swing.JButton edit_but;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea nonamccontdet_txt;
    private javax.swing.JTextField nonamccontper_txt;
    private javax.swing.JPanel nonamcepanel;
    private javax.swing.JRadioButton nonamcrad_but;
    private javax.swing.JRadioButton nonamcradio_but;
    private javax.swing.JTextArea nonamcremarks_txt;
    private javax.swing.JTextArea paricular_txt;
    private javax.swing.JComboBox period_combo;
    private javax.swing.JButton remdate_but;
    private javax.swing.JButton reset_but;
    private javax.swing.JComboBox vend_combo;
    // End of variables declaration//GEN-END:variables

    public List getcontractorList(AppView app) throws BasicException {
        List<Object> contractorList = new ArrayList<Object>();
        contractorList = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM VENDOR WHERE ACTIVE=1 ORDER BY NAME  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return contractorList;
    }

    public List getPeriodList(AppView app) throws BasicException {
        List<Object> periodList = new ArrayList<Object>();
        periodList = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM PERIODICITY WHERE ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return periodList;
    }

    public List getVendorList() throws BasicException {
        List<Object> vendorList = new ArrayList<Object>();
        vendorList = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM VENDOR WHERE ACTIVE=1 ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return vendorList;
    }

    public String getvendId() {

        List<Object> vendorlist = new ArrayList<Object>();
        try {
            if (vend_combo.getSelectedIndex() != -1) {
                vendorlist = (List<Object>) new StaticSentence(app.getSession(), "SELECT  ID FROM VENDOR  WHERE NAME=? AND ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(vend_combo.getSelectedItem());
                vendor = (String) vendorlist.get(0);
            } else {
                vendor = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendor;
    }

    public String getContractorId() {

        List<Object> contractorList = new ArrayList<Object>();
        try {
            if (amcconcombo.getSelectedIndex() != -1) {
                contractorList = (List<Object>) new StaticSentence(app.getSession(), "SELECT  ID FROM VENDOR  WHERE NAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(amcconcombo.getSelectedItem());
                contractor = (String) contractorList.get(0);
            } else {
                contractor = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contractor;
    }

    public String getPeriodType() {

        List<Object> PeriodTypeList = new ArrayList<Object>();
        try {
            if (period_combo.getSelectedIndex() != -1) {
                PeriodTypeList = (List<Object>) new StaticSentence(app.getSession(), "SELECT TYPE_ FROM PERIODICITY WHERE NAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(period_combo.getSelectedItem());
                periodtype = (String) PeriodTypeList.get(0);
            } else {
                periodtype = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return periodtype;
    }

    public int getPeriodNo() {
        String type = getPeriodType();
        String no = null;
        int nor = 0;
        List<Object> PeriodTypeList = new ArrayList<Object>();
        try {
            if (period_combo.getSelectedIndex() != -1) {
                PeriodTypeList = (List<Object>) new StaticSentence(app.getSession(), "SELECT NO FROM PERIODICITY WHERE NAME=? AND ACTIVE=1 ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(period_combo.getSelectedItem());
                no = (String) PeriodTypeList.get(0);
            } else {
                no = "null";
            }
            nor = Integer.parseInt(no);

        } catch (BasicException ex) {
            Logger.getLogger(AmcnNonamcDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nor;
    }

    public void ButtonGrp() {

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(amcradio_but);
        bg1.add(nonamcradio_but);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(amcrad_but);
        bg2.add(nonamcrad_but);

    }

}
