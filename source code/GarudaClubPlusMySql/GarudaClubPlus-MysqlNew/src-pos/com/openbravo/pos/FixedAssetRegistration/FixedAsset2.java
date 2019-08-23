/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.FixedAssetRegistration.MaintenanceTableModel.MaintenanceInfo;
import com.openbravo.pos.FixedAssetRegistration.FixedAssetTableModel.FixedAssetInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import com.openbravo.pos.Accounts.AccountTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import org.apache.commons.beanutils.BeanComparator;
import java.util.Iterator;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author dev3
 */
public class FixedAsset2 extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    private int stand_alone;
    private AppView m_App;
    private AppConfig config;
    private DirtyManager dirty = new DirtyManager();
    private String majorclass;
    private String subclass;
    private String subheadclass;
    private String accclass;
    private String vendor;
    private String asset;
    private String maintenancestr;
    private static boolean document;
    private static boolean summary;
    private static String level = null;
    private static DataLogicFacilities dmang;
    private static ComboBoxValModel elementsModel;
    private static ComboBoxValModel mainheadsModel;
    private static ComboBoxValModel breakdownsModel;
    // private static ComboBoxValModel acccomboemodel;
    private static ComboBoxValModel accountheadlistModel;
    private String deac_id;
    private FixedAssetTableModel fxd_table;
    private String docum;
    private int fsd;
    private static AccountTable acctablemodel;
    private List<String> namelist = new ArrayList<String>();
    private List<String> barcodelist = new ArrayList<String>();
    boolean updateFlag = false;

    private static DataLogicFacilities dlf;
    private static String a_SearchkeyWithoutDot;
    static Object[] fetch_data;
    static String ss;
    private List<FixedAssetTableModel.FixedAssetInfo> data2;
    private List<FixedAssetTableModel.FixedAssetInfo> data3;
    static String acc_id;
    static String barcode2;
    static String barcode4;
    static StringBuffer barcode1;
    static StringBuffer barcode3;
    Object[] fl;
    private List<String> searchkey_list1 = new ArrayList<String>();
    private List<String> barcode_listFaMaster = new ArrayList<String>();
    List<String> searchkey_listBarcode = new ArrayList<String>();
    static int max = 0;
    static String ffa_id;
    protected Session s;
    private static String a_id;
    private static int a_max;
    private static int max1;
    private static int max2;
    private static int mm;
    private static int max3;
    private static int max4;
    private static String barcode1_copy;
    private static String barcode3_copy;
    private static String test_searchkey1;
    private static String test_searchkey2;
    private static int flag = 1;
    private static String maj1;
    private static String subhead1;
    private static String sub1;
    private static String acchead1;
    private static String existed_barcode;
    private static String created_barcode;
    private static String existed_barcode1 = null;
    private static int savech_max;

    public List<MaintenanceInfo> MaintenanceInfoList = new ArrayList<MaintenanceInfo>();
    File documentfile;
    int k = 0;
    int y = 0;
    int z = 0;
    int p = 0;
    private List<String> majorclassList = new ArrayList<String>();
    private ComboBoxValModel majorclasslistModel;

    private List<String> subclassList = new ArrayList<String>();
    private ComboBoxValModel subclasslistModel;

    private List<String> subheadclassList = new ArrayList<String>();
    private ComboBoxValModel subheadclasslistModel;

    private List<String> accountheadList = new ArrayList<String>();

    private List<String> vendorList = new ArrayList<String>();
    private ComboBoxValModel vendorListModel;
    private ComboBoxValModel assetListModel;

    ////////////////////////////////
    List< AccountMasterExt> mainHeadList;
    List< AccountMasterExt> breakdownsList;
    List< AccountMaster> accList;
    /////////////////////////////////

    public static String idf;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    DecimalFormat df = new DecimalFormat("#0.00" + "%");

    public static String fixedid;
    String passingid;
    public String id;
    JFileChooser chooser;
    String choosertitle;
    File srcLogo = null;
    File srcLogo1 = null;
    String AID = null;
    String NAID = null;
    String MID = null;
    String WID = null;
    String RID = null;
    String PID = null;
    String PVID = null;
    File file = null;
    String filename;
    File selectedFile;

    /**
     * Creates new form FixedAsset2
     */
    public FixedAsset2() {
        initComponents();
        savebutt.setVisible(true);
        standaloneradio.setSelected(true);

    }

    public boolean hasChanged() {
        return dirty.isDirty();
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        barcode_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        name_txt = new javax.swing.JTextField();
        standaloneradio = new javax.swing.JRadioButton();
        anotherassetradio = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        assetcombo = new javax.swing.JComboBox();
        photo_butt = new javax.swing.JButton();
        amc_but = new javax.swing.JButton();
        PhysicalVerification_but = new javax.swing.JButton();
        purch_panel = new javax.swing.JPanel();
        vendorcombo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        purchasedate = new javax.swing.JButton();
        cost_txt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        linktxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        strtlineradio = new javax.swing.JRadioButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cstreplc_txt = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        calcu_txt = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        agency_txt = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        document_but = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        wdvDate = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        wdvradio = new javax.swing.JRadioButton();
        wdvdatebutton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        acccombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        elements = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        mainheads = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        breakdowns = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        instdate = new javax.swing.JTextField();
        commdate = new javax.swing.JTextField();
        putdate = new javax.swing.JTextField();
        captdate = new javax.swing.JTextField();
        installationdate = new javax.swing.JButton();
        commissiondate = new javax.swing.JButton();
        usedate = new javax.swing.JButton();
        capitalisationdate = new javax.swing.JButton();
        revaluation_but = new javax.swing.JButton();
        maintenance_but = new javax.swing.JButton();
        writeoff_but = new javax.swing.JButton();
        msgdlg = new javax.swing.JLabel();
        msgdlg1 = new javax.swing.JLabel();
        msgdlg2 = new javax.swing.JLabel();
        msgdlg4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldMake = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldModel = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        savebutt = new javax.swing.JButton();
        savecha_but = new javax.swing.JButton();
        reset_but = new javax.swing.JButton();
        submit_but = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        Name_rad = new javax.swing.JRadioButton();
        majcls_rad = new javax.swing.JRadioButton();
        createddate_rad = new javax.swing.JRadioButton();
        vendor_rad = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        deactivate_but = new javax.swing.JButton();
        edit_but = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
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

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setText("Barcode*");

        barcode_txt.setAlignmentX(0.0F);
        barcode_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcode_txtActionPerformed(evt);
            }
        });

        jLabel2.setText("Name*");

        standaloneradio.setText("Stand alone Asset");
        standaloneradio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                standaloneradioItemStateChanged(evt);
            }
        });

        anotherassetradio.setText("Part Of Another Asset");
        anotherassetradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anotherassetradioActionPerformed(evt);
            }
        });

        jLabel7.setText("Link to other asset");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assetcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assetcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        photo_butt.setText("Photograph");
        photo_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                photo_buttActionPerformed(evt);
            }
        });

        amc_but.setText("AMC and NON AMC");
        amc_but.setMaximumSize(new java.awt.Dimension(97, 29));
        amc_but.setMinimumSize(new java.awt.Dimension(97, 29));
        amc_but.setPreferredSize(new java.awt.Dimension(97, 29));
        amc_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amc_butActionPerformed(evt);
            }
        });

        PhysicalVerification_but.setText("Physical Verification ");
        PhysicalVerification_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhysicalVerification_butActionPerformed(evt);
            }
        });

        purch_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Purchase Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Andale Mono", 3, 18), java.awt.Color.blue)); // NOI18N
        purch_panel.setForeground(java.awt.Color.blue);
        purch_panel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        jLabel9.setText("Vendor");

        jLabel10.setText("Date Of Purchase");

        purchasedate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        purchasedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchasedateActionPerformed(evt);
            }
        });

        cost_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cost_txtActionPerformed(evt);
            }
        });
        cost_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cost_txtKeyTyped(evt);
            }
        });

        jLabel11.setText("total cost");

        jLabel12.setText("Scanned Document");

        strtlineradio.setText("Straight Line");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        jLabel17.setText("Rate Of Deprecation");

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });

        jLabel18.setText("WDV as on the date ");

        cstreplc_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cstreplc_txtActionPerformed(evt);
            }
        });
        cstreplc_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cstreplc_txtKeyTyped(evt);
            }
        });

        jLabel27.setText("Cost Of Replacement");

        calcu_txt.setColumns(20);
        calcu_txt.setRows(5);
        jScrollPane4.setViewportView(calcu_txt);

        jLabel28.setText("How Calculated");

        jLabel29.setText("Agency for Replacement");

        document_but.setText("View Document");
        document_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                document_butActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/mime2.png")));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel21.setText("Begining of Financial year for WDV");

        wdvradio.setText("WDV");

        wdvdatebutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        wdvdatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wdvdatebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout purch_panelLayout = new javax.swing.GroupLayout(purch_panel);
        purch_panel.setLayout(purch_panelLayout);
        purch_panelLayout.setHorizontalGroup(
            purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purch_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(strtlineradio, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wdvradio)))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(25, 25, 25)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField10)
                            .addComponent(cstreplc_txt)
                            .addComponent(agency_txt)
                            .addComponent(jScrollPane4)))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(7, 7, 7)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cost_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(purch_panelLayout.createSequentialGroup()
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(purch_panelLayout.createSequentialGroup()
                                .addComponent(linktxt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(document_but))
                            .addGroup(purch_panelLayout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(wdvDate, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wdvdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(vendorcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        purch_panelLayout.setVerticalGroup(
            purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purch_panelLayout.createSequentialGroup()
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(vendorcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(purchasedate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cost_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(linktxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(document_but)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strtlineradio)
                    .addComponent(wdvradio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(17, 17, 17)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(wdvDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(wdvdatebutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cstreplc_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agency_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel6.setText("Account Head *");
        jLabel6.setMaximumSize(new java.awt.Dimension(151, 17));
        jLabel6.setMinimumSize(new java.awt.Dimension(151, 17));
        jLabel6.setPreferredSize(new java.awt.Dimension(151, 17));

        acccombo.setAlignmentX(0.0F);

        jLabel3.setText("Major Classification *");

        elements.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elementsItemStateChanged(evt);
            }
        });

        jLabel4.setText("Sub Head Classification *");
        jLabel4.setMaximumSize(new java.awt.Dimension(151, 17));
        jLabel4.setMinimumSize(new java.awt.Dimension(151, 17));
        jLabel4.setPreferredSize(new java.awt.Dimension(151, 17));

        mainheads.setAlignmentX(0.0F);
        mainheads.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainheadsItemStateChanged(evt);
            }
        });

        jLabel5.setText("Sub Classification ");

        breakdowns.setAlignmentX(0.0F);
        breakdowns.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                breakdownsItemStateChanged(evt);
            }
        });
        breakdowns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breakdownsActionPerformed(evt);
            }
        });

        jLabel13.setText("Date Of Installation");

        jLabel14.setText("Date Of Commissioning");

        jLabel15.setText("Date On Which Put To Use");

        jLabel16.setText("Date Of Capitalisation");

        instdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instdateActionPerformed(evt);
            }
        });

        installationdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        installationdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                installationdateActionPerformed(evt);
            }
        });

        commissiondate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        commissiondate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commissiondateActionPerformed(evt);
            }
        });

        usedate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        usedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usedateActionPerformed(evt);
            }
        });

        capitalisationdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        capitalisationdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capitalisationdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(instdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(commdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(installationdate, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(commissiondate, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(captdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(putdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usedate, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(capitalisationdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(installationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(commissiondate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(putdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(usedate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(captdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(capitalisationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        revaluation_but.setText("Revaluation");
        revaluation_but.setMaximumSize(new java.awt.Dimension(97, 29));
        revaluation_but.setMinimumSize(new java.awt.Dimension(97, 29));
        revaluation_but.setPreferredSize(new java.awt.Dimension(97, 29));
        revaluation_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revaluation_butActionPerformed(evt);
            }
        });

        maintenance_but.setText("Maintenance");
        maintenance_but.setMaximumSize(new java.awt.Dimension(97, 29));
        maintenance_but.setMinimumSize(new java.awt.Dimension(97, 29));
        maintenance_but.setPreferredSize(new java.awt.Dimension(97, 29));
        maintenance_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintenance_butActionPerformed(evt);
            }
        });

        writeoff_but.setText("WriteOff");
        writeoff_but.setMaximumSize(new java.awt.Dimension(97, 28));
        writeoff_but.setMinimumSize(new java.awt.Dimension(97, 28));
        writeoff_but.setPreferredSize(new java.awt.Dimension(97, 28));
        writeoff_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeoff_butActionPerformed(evt);
            }
        });

        msgdlg.setForeground(java.awt.Color.red);
        msgdlg.setText("Put To Use Date Should be (Greater than) Installation Date and (Greater than or Equal to)  Commission date");

        msgdlg1.setForeground(java.awt.Color.red);
        msgdlg1.setText("Commission Date Should be Greater than or Equal to Installation Date");

        msgdlg2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        msgdlg2.setForeground(java.awt.Color.red);
        msgdlg2.setText("Capitalisation Date Should be (Greater than)  Installation Date,Commission date and  (Greater than or Equal to) Put To Use Date");

        msgdlg4.setForeground(java.awt.Color.red);
        msgdlg4.setText("Installation Date Should be Greater than or Equal to Purchase Date");

        jLabel19.setText("Make*");

        jLabel20.setText("Model* ");

        jButton3.setText("Create Barcode");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(97, 29));
        jButton3.setMinimumSize(new java.awt.Dimension(97, 29));
        jButton3.setPreferredSize(new java.awt.Dimension(97, 29));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(standaloneradio)
                        .addGap(28, 28, 28)
                        .addComponent(anotherassetradio))
                    .addComponent(purch_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msgdlg, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(msgdlg1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(msgdlg4, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(msgdlg2, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(breakdowns, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mainheads, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(elements, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldModel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldMake, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barcode_txt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name_txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(acccombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PhysicalVerification_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(photo_butt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amc_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(revaluation_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maintenance_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(writeoff_but, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barcode_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMake)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldModel)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(elements, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mainheads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(breakdowns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PhysicalVerification_but)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(photo_butt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amc_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revaluation_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maintenance_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(writeoff_but, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(acccombo)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(standaloneradio)
                    .addComponent(anotherassetradio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purch_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgdlg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msgdlg1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msgdlg2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgdlg4)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        savebutt.setText("Save");
        savebutt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttActionPerformed(evt);
            }
        });

        savecha_but.setText("Save Changes");
        savecha_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savecha_butActionPerformed(evt);
            }
        });

        reset_but.setText("reset");
        reset_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_butActionPerformed(evt);
            }
        });

        submit_but.setText("Submit");
        submit_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(reset_but, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(savebutt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(savecha_but)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submit_but, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit_but)
                    .addComponent(savecha_but)
                    .addComponent(savebutt)
                    .addComponent(reset_but))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Create New Asset", jPanel1);

        Name_rad.setText("Asset Name");
        Name_rad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Name_radItemStateChanged(evt);
            }
        });
        Name_rad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Name_radActionPerformed(evt);
            }
        });

        majcls_rad.setText("Subhead");
        majcls_rad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                majcls_radItemStateChanged(evt);
            }
        });

        createddate_rad.setText("Created Date");
        createddate_rad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                createddate_radItemStateChanged(evt);
            }
        });

        vendor_rad.setText("Vendor");
        vendor_rad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendor_radActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setText("Order By :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Name_rad)
                .addGap(29, 29, 29)
                .addComponent(majcls_rad)
                .addGap(35, 35, 35)
                .addComponent(createddate_rad)
                .addGap(36, 36, 36)
                .addComponent(vendor_rad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(359, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createddate_rad)
                    .addComponent(vendor_rad)
                    .addComponent(majcls_rad)
                    .addComponent(Name_rad)
                    .addComponent(jLabel8))
                .addContainerGap(477, Short.MAX_VALUE))
        );

        deactivate_but.setText("Deactivate");
        deactivate_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivate_butActionPerformed(evt);
            }
        });

        edit_but.setText("Edit");
        edit_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_butActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jScrollPane3.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deactivate_but)
                .addGap(41, 41, 41)
                .addComponent(edit_but, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(459, 459, 459))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 507, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit_but)
                    .addComponent(deactivate_but))
                .addGap(32, 32, 32))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 553, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("View List", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void barcode_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcode_txtActionPerformed

    private void standaloneradioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_standaloneradioItemStateChanged
        if (standaloneradio.isSelected()) {

            jPanel4.setVisible(false);
            purch_panel.setVisible(true);
            assetcombo.setSelectedIndex(-1);

        } else {

            jPanel4.setVisible(true);

        }
    }//GEN-LAST:event_standaloneradioItemStateChanged

    private void photo_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_photo_buttActionPerformed
        PhotogpDialog photo;
        try {

            photo = PhotogpDialog.getDialog(this, m_App, true);
            photo.showDialog();

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_photo_buttActionPerformed

    private void amc_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amc_butActionPerformed
        AmcnNonamcDialog amc;
        try {

            amc = AmcnNonamcDialog.getDialog(this, m_App, true);
            amc.showDialog();

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_amc_butActionPerformed

    private void PhysicalVerification_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhysicalVerification_butActionPerformed
        PhysicalVerification phyver;
        try {
            phyver = PhysicalVerification.getDialog(this, m_App, true);
            phyver.showDialog();

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PhysicalVerification_butActionPerformed

    private void purchasedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchasedateActionPerformed
        date.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(date.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {

            date.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_purchasedateActionPerformed

    private void cost_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cost_txtActionPerformed

    }//GEN-LAST:event_cost_txtActionPerformed

    private void cost_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cost_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {

            evt.consume();
        }
    }//GEN-LAST:event_cost_txtKeyTyped

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed

    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed

    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField10KeyTyped

    private void cstreplc_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cstreplc_txtActionPerformed

    }//GEN-LAST:event_cstreplc_txtActionPerformed

    private void cstreplc_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cstreplc_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) || (value == KeyEvent.VK_BACK_SPACE) || value == KeyEvent.VK_DELETE || value == '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_cstreplc_txtKeyTyped

    private void document_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_document_butActionPerformed

        try {

            File file1 = new File(linktxt.getText().replace("./", ""));;
            if (file1.exists() == true) {

                java.awt.Desktop.getDesktop().open(file1);
            } else {
                JOptionPane.showMessageDialog(this, "File not Found", null, JOptionPane.OK_OPTION);

            }
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

        }


    }//GEN-LAST:event_document_butActionPerformed

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

    private void mainheadsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainheadsItemStateChanged
        if (mainheads.getSelectedItem() == null) {
            breakdowns.setSelectedIndex(-1);
            breakdowns.setModel(new DefaultComboBoxModel());
            acccombo.setSelectedIndex(-1);
            acccombo.setModel(new DefaultComboBoxModel());
        }
        if (mainheads.getSelectedIndex() != -1) {
            try {

                AccountMasterExt mele = (AccountMasterExt) mainheads.getSelectedItem();
                //added by pratima
                breakdownsModel = new ComboBoxValModel(dmang.getaccountBreakpoints(mele.getSerachkey()));
                breakdownsList = dmang.getaccountBreakpoints(mele.getSerachkey());
                breakdowns.setModel(breakdownsModel);
                breakdowns.setSelectedIndex(-1);
                accountheadlistModel = new ComboBoxValModel(dmang.getSubaccounts1(mele.getSearchkey()));
                acccombo.setModel(accountheadlistModel);
                acccombo.setSelectedIndex(-1);

                //ended by pratima
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_mainheadsItemStateChanged

    private void breakdownsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_breakdownsItemStateChanged
        //by pratima
        if (breakdowns.getSelectedItem() == null) {
            acccombo.setSelectedIndex(-1);
            acccombo.setModel(new DefaultComboBoxModel());
        }
        if (breakdowns.getSelectedIndex() != -1) {
            try {
                AccountMasterExt mele = (AccountMasterExt) breakdowns.getSelectedItem();
                accountheadlistModel = new ComboBoxValModel(dmang.getSubaccounts1(mele.getSearchkey()));
                acccombo.setModel(accountheadlistModel);
                acccombo.setSelectedIndex(-1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }//ended by pratima
    }//GEN-LAST:event_breakdownsItemStateChanged

    private void installationdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_installationdateActionPerformed
        Date purc;
        try {
            purc = (Date) Formats.TIMESTAMP.parseValue(date.getText());
        } catch (BasicException e) {
            purc = null;
        }
        // d = JCalendarDialog.showCalendarTimeHours(this, purc);
        if (purc != null) {

            date.setText(Formats.TIMESTAMP.formatValue(purc));
        }
        instdate.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (purc == null) {
            JOptionPane.showMessageDialog(this, "First fill Purchase Date  ", null, JOptionPane.WARNING_MESSAGE);

        }
        if (d != null) {
            if ((d.compareTo(purc) >= 0)) {
                instdate.setText(Formats.TIMESTAMP.formatValue(d));

            } else {
                JOptionPane.showMessageDialog(this, " Installation Date  Should be Greater than or Equal to Purchase Date ", null, JOptionPane.WARNING_MESSAGE);
                instdate.setText("");
            }

        }
    }//GEN-LAST:event_installationdateActionPerformed

    private void commissiondateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commissiondateActionPerformed
        commdate.setEditable(false);
        Date inst;
        try {
            inst = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
        } catch (BasicException e) {
            inst = null;
        }
        //inst = JCalendarDialog.showCalendarTimeHours(this, inst);
        if (inst != null) {

            instdate.setText(Formats.TIMESTAMP.formatValue(inst));
        }

        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if ((inst == null)) {
            JOptionPane.showMessageDialog(this, "First fill Installation Date  ", null, JOptionPane.WARNING_MESSAGE);

        }
        if (d != null) {
            if ((d.compareTo(inst) >= 0)) {
                commdate.setText(Formats.TIMESTAMP.formatValue(d));

            } else {
                JOptionPane.showMessageDialog(this, " Commission Date Should be Greater than or Equal to Installation Date ", null, JOptionPane.WARNING_MESSAGE);
                commdate.setText("");
            }

        }
    }//GEN-LAST:event_commissiondateActionPerformed

    private void usedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usedateActionPerformed
        putdate.setEditable(false);
        Date dat;
        try {
            dat = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
        } catch (BasicException e) {
            dat = null;
        }

        if (dat != null) {

            commdate.setText(Formats.TIMESTAMP.formatValue(dat));
        }
        Date dd;
        try {
            dd = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
        } catch (BasicException e) {
            dd = null;
        }

        if (dd != null) {

            instdate.setText(Formats.TIMESTAMP.formatValue(dd));
        }
        Date ddw;
        try {
            ddw = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
        } catch (BasicException e) {
            ddw = null;
        }

        if (ddw != null) {

            captdate.setText(Formats.TIMESTAMP.formatValue(ddw));
        }
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        //  putdate.setText(Formats.TIMESTAMP.formatValue(d));
        if ((dd == null) || (dat == null)) {
            JOptionPane.showMessageDialog(this, "First fill Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);

        }
        if (d != null) {

            if ((d.compareTo(dd) >= 0) && (d.compareTo(dat) >= 0)) {
                putdate.setText(Formats.TIMESTAMP.formatValue(d));

            } else {
                JOptionPane.showMessageDialog(this, "Put To Use Date Should be (Greater than) Installation Date and (Greater than or Equal) to Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                putdate.setText("");
            }
        }
    }//GEN-LAST:event_usedateActionPerformed

    private void capitalisationdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capitalisationdateActionPerformed
        putdate.setEditable(false);
        Date dat;
        try {
            dat = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
        } catch (BasicException e) {
            dat = null;
        }

        if (dat != null) {

            commdate.setText(Formats.TIMESTAMP.formatValue(dat));
        }
        Date dd;
        try {
            dd = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
        } catch (BasicException e) {
            dd = null;
        }

        if (dd != null) {

            instdate.setText(Formats.TIMESTAMP.formatValue(dd));
        }
        Date used;
        try {
            used = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
        } catch (BasicException e) {
            used = null;
        }

        if (used != null) {

            putdate.setText(Formats.TIMESTAMP.formatValue(used));
        }

        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if ((dd == null) || (dat == null) || (used == null)) {
            JOptionPane.showMessageDialog(this, "First fill Installation Date,Commission date and Put To Use Date  ", null, JOptionPane.WARNING_MESSAGE);

        }
        if (d != null) {
            if ((d.compareTo(dd) >= 0) && (d.compareTo(dat) >= 0) && (d.compareTo(used) >= 0)) {
                captdate.setText(Formats.TIMESTAMP.formatValue(d));

            } else {
                JOptionPane.showMessageDialog(this, "Capitalisation Date Should be (Greater than) Installation Date,Commission date and (Greater than or Equal to) Put To Use Date ", null, JOptionPane.WARNING_MESSAGE);
                captdate.setText("");
            }

        }

    }//GEN-LAST:event_capitalisationdateActionPerformed

    private void revaluation_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revaluation_butActionPerformed
        RevaluationDialog reval;
        try {
            reval = RevaluationDialog.getDialog(this, m_App, true);
            reval.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_revaluation_butActionPerformed

    private void maintenance_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenance_butActionPerformed
        MaintenanceDailog mntnce;
        try {
            mntnce = MaintenanceDailog.getDialog(this, m_App, true);
            mntnce.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_maintenance_butActionPerformed

    private void writeoff_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeoff_butActionPerformed
        WriteOffDetailsDialog replmt;
        try {
            replmt = WriteOffDetailsDialog.getDialog(this, m_App, true);
            replmt.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_writeoff_butActionPerformed

    private void edit_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_butActionPerformed
        submit_but.setVisible(false);
        writeoff_but.setVisible(true);
        PhysicalVerification_but.setVisible(true);
        photo_butt.setVisible(true);
        amc_but.setVisible(true);
        revaluation_but.setVisible(true);
        maintenance_but.setVisible(true);
        jButton3.setVisible(false);

        if (jTable1.getSelectedRow() != -1) {
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? ", "Editing Menu", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {
                savecha_but.setVisible(true);
                savebutt.setVisible(false);
                if (jTable1.getSelectedRow() < fxd_table.getSize()) {
                    int row = jTable1.getSelectedRow();
                    FixedAssetInfo showdata = fxd_table.getList().get(row);
                    jPanel3.setVisible(true);
                    document_but.setVisible(false);
                    idf = showdata.getId();
                    fixedid = idf;

                    String name = showdata.getname();
                    String barcode = showdata.getbarcode();
                    existed_barcode = barcode;

                    String maj = null;
                    String subhead = null;
                    String sub = null;
                    // String acchead=null;
                    try {
                        maj = dmang.getaccountbyid(showdata.getmajor_cls()).getName();
                        maj1 = maj;

                        subhead = dmang.getaccountbyid(showdata.getsub_head()).getName();
                        subhead1 = subhead;

                        sub = dmang.getaccountbyid(showdata.getsub_cls()).getName();
                        sub1 = sub;

                        //   acchead = dmang.getaccountbyid(showdata.getac_head()).getName();
                    } catch (Exception e) {
                    }
//                    String subhead = showdata.getsub_head();
//                    String sub = showdata.getsub_cls();
                    String acchead = showdata.getac_head();
                    acchead1 = acchead;

                    String vendor = showdata.getvendor();
                    int standalne = showdata.getstand_alone();
                    String dop = showdata.getdop();
                    String docmm = showdata.getdocomm();
                    String doi = showdata.getdoi();
                    String docapt = showdata.getdocapt();
                    String dou = showdata.getdou();
                    docum = showdata.getdoc();
                    String agency = showdata.getagency();
                    String howcal = showdata.gethowcal();
                    int strt = showdata.getstrt();
                    Double cost = showdata.getcost();
                    Double cor = showdata.getcor();
                    Double rop = showdata.getrod();
                    Double wdv = showdata.getwdv();
                    String link = showdata.getlink();

                    String cos = Formats.DOUBLE.formatValue(cost);
                    if (showdata.getMake() != null) {
                        jTextFieldMake.setText(showdata.getMake());
                    }
                    if (showdata.getModel() != null) {
                        jTextFieldModel.setText(showdata.getModel());
                    }
                    if (showdata.getWdvDate() != null) {
                        wdvDate.setText(showdata.getWdvDate());
                    }

                    if (docum.equals("")) {
                        document_but.setVisible(false);

                    } else {
                        document_but.setVisible(true);
                    }

                    name_txt.setText(name + "");
                    barcode_txt.setText(barcode + "");
                    if (standalne == 1) {
                        standaloneradio.setSelected(true);
                    } else {
                        anotherassetradio.setSelected(true);
                    }
                    if (strt == 1) {
                        strtlineradio.setSelected(true);
                    } else {
                        wdvradio.setSelected(true);
                    }

                    date.setText(dop + "");
                    if (docum != null) {
                        linktxt.setText(docum + "");
                    } else {
                        linktxt.setText("");
                    }
                    instdate.setText(doi + "");
                    putdate.setText(dou + "");
                    commdate.setText(docmm + "");
                    captdate.setText(docapt + "");

                    if (cost != null) {
                        cost_txt.setText(decimalFormat.format(cost) + "");
                    } else {
                        cost_txt.setText("");
                    }
                    if (rop != null) {
                        String r = decimalFormat.format(rop);
                        jTextField9.setText(r + "%" + "");
                    } else {
                        jTextField9.setText("");
                    }
                    if (wdv != null) {
                        jTextField10.setText(decimalFormat.format(wdv) + "");
                    } else {
                        jTextField10.setText("");
                    }
                    if (cor != null) {
                        cstreplc_txt.setText(decimalFormat.format(cor) + "");
                    } else {
                        cstreplc_txt.setText("");
                    }
                    calcu_txt.setText(howcal + "");
                    agency_txt.setText(agency + "");

                    List<Object> p1_list = new ArrayList<Object>();
                    List<Object> p2_list = new ArrayList<Object>();

                    List<Object> p3_list = new ArrayList<Object>();

                    try {

                        p1_list = (List<Object>) new StaticSentence(m_App.getSession(), "select  a.name from accountmaster a where a.id=?  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(acchead);
                        p2_list = (List<Object>) new StaticSentence(m_App.getSession(), " select v.name from vendor v where v.id=?  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(vendor);
                        p3_list = (List<Object>) new StaticSentence(m_App.getSession(), " select  link from fa_master  where link=?  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(link);

                    } catch (BasicException ex) {
                        Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (p1_list.size() != 0) {
                        accountheadlistModel.setSelectedItem(p1_list.get(0));
                    } else {
                        acccombo.setSelectedIndex(-1);
                    }
                    if (p2_list.size() != 0) {
                        vendorListModel.setSelectedItem(p2_list.get(0));
                    } else {
                        vendorcombo.setSelectedIndex(-1);
                    }
                    if (p3_list.size() != 0) {
                        assetListModel.setSelectedItem(p3_list.get(0));
                    } else {
                        assetcombo.setSelectedIndex(-1);
                    }

                    for (int i = 0; i < elements.getItemCount(); i++) {
                        if (elements.getItemAt(i).toString().equals(maj)) {
                            elements.setSelectedIndex(i);
                            break;
                        }

                    }
                    for (int i = 0; i < mainheads.getItemCount(); i++) {
                        if (mainheads.getItemAt(i).toString().equals(subhead)) {
                            mainheads.setSelectedIndex(i);
                            break;
                        }

                    }
                    for (int i = 0; i < breakdowns.getItemCount(); i++) {
                        if (breakdowns.getItemAt(i).toString().equals(sub)) {
                            breakdowns.setSelectedIndex(i);
                            break;
                        }

                    }
                    for (int i = 0; i < acccombo.getItemCount(); i++) {
                        if (acccombo.getItemAt(i).toString().equals(acchead)) {
                            acccombo.setSelectedIndex(i);
                            break;
                        }

                    }
                    for (int i = 0; i < vendorcombo.getItemCount(); i++) {
                        if (vendorcombo.getItemAt(i).toString().equals(vendor)) {
                            vendorcombo.setSelectedIndex(i);
                            break;
                        }

                    }

                    for (int i = 0; i < assetcombo.getItemCount(); i++) {
                        if (assetcombo.getItemAt(i).toString().equals(link)) {
                            assetcombo.setSelectedIndex(i);
                            break;
                        } else {
                            assetcombo.setSelectedItem("");

                        }

                    }

                    jTabbedPane1.setSelectedIndex(0);

                }
            }
        }

    }//GEN-LAST:event_edit_butActionPerformed

    private void deactivate_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_butActionPerformed
        if (jTable1.getSelectedRow() != -1) {
            String assetlink = "";
            List<String> assetlist = new ArrayList<String>();
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? ", "Deactivation", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

                int row = jTable1.getSelectedRow();
                FixedAssetInfo showdata = fxd_table.getList().get(row);
                assetlink = showdata.getname();

                try {

                    assetlist = (List<String>) new StaticSentence(m_App.getSession(), "select link from fa_master where active=1 ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (assetlist.contains(assetlink)) {

                    JOptionPane.showMessageDialog(this, "This asset is linked to another asset,change that linked asset and Deactivate..", null, JOptionPane.OK_OPTION);

                } else {
                    deac_id = showdata.getId();
                    deactFixedast();
                }

                /*  try {

                 assetlist = (List<String>) new StaticSentence(m_App.getSession(), "SELECT NAME from fa_master WHERE ACTIVE=1 ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
                 } catch (Exception e) {
                 e.printStackTrace();
                 }
                 if (assetlist.contains(assetlink)) {
                    
                 } else {

                 JOptionPane.showMessageDialog(this, "This asset is linked to another asset,change that linked asset and Deactivate", null, JOptionPane.OK_OPTION);

                 }*/
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select row in the table ", null, JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_deactivate_butActionPerformed

    private void savecha_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savecha_butActionPerformed

        try {

            //if (jTextField10.getText() != null && jTextField10.getText().trim().length() > 0) {
            //  if (jTextField9.getText() != null && jTextField9.getText().trim().length() > 0) {
            //     if ((date.getText() != null && date.getText().trim().length() > 0) && (instdate.getText() != null && instdate.getText().trim().length() > 0) && (putdate.getText() != null && putdate.getText().trim().length() > 0) && (commdate.getText() != null && commdate.getText().trim().length() > 0) && (captdate.getText() != null && captdate.getText().trim().length() > 0)) {
            if (name_txt.getText() != null && name_txt.getText().trim().length() > 0) {
                if (barcode_txt.getText() != null && barcode_txt.getText().trim().length() > 0) {
                    if (jTextFieldMake.getText() != null && jTextFieldMake.getText().trim().length() > 0) {
                        if (jTextFieldModel.getText() != null && jTextFieldModel.getText().trim().length() > 0) {
                            //if ((elements.getSelectedIndex() != -1 && elements.getSelectedItem() != null) && (acccombo.getSelectedIndex() != -1 && acccombo.getSelectedItem() != null) && (vendorcombo.getSelectedIndex() != -1 && vendorcombo.getSelectedItem() != null)) {

                            //try {
                            //   namelist = (List<String>) new StaticSentence(m_App.getSession(), "select name from fa_master where active=true and id!= ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(id);
                            //  } catch (Exception e) {
                            //    e.printStackTrace();
                            // }
                            // if (namelist.contains(name_txt.getText().trim())) {
                            //   JOptionPane.showMessageDialog(null, "Fixed asset with the name " + name_txt.getText().trim() + " already exist", null, JOptionPane.OK_OPTION);
                            //} else {
                            //       if (anotherassetradio.isSelected()) {
                            //     if (assetcombo.getSelectedIndex() != -1 && assetcombo.getSelectedItem() != null) {
                            Transaction t = new Transaction(m_App.getSession()) {
                                @Override
                                protected Object transact() throws BasicException {
                                    int row = jTable1.getSelectedRow();
                                    FixedAssetTableModel.FixedAssetInfo data = fxd_table.getList().get(row);

                                    id = data.getId();
                                    Double costval = null;
                                    Double rod = null;
                                    Double wdv = null;
                                    Double cor = null;
                                    String brk = null;
                                    String brkdwn = null;
                                    String mainhcom = null;
                                    String asset1 = null;
                                    String fadifid = UUID.randomUUID().toString();
                                    String acchead = null;
                                    asset1 = assetcombo.getSelectedItem().toString();
                                    String make = jTextFieldMake.getText();
                                    String model = jTextFieldModel.getText();

                                    int stndal;
                                    int strt;
                                    //   String ele = elements.getSelectedItem().toString();
                                    String ele = dmang.getaccountElements().get(elements.getSelectedIndex()).getId();
                                    if (breakdowns.getSelectedIndex() != -1) {
                                        //   brkdwn = breakdowns.getSelectedItem().toString();
                                        brkdwn = breakdownsList.get(breakdowns.getSelectedIndex()).getId();
                                    } else {

                                        brkdwn = "";

                                    }

                                    if (assetcombo.getSelectedItem() != null) {
                                        asset = assetcombo.getSelectedItem().toString();

                                    } else {

                                        asset = " ";

                                    }
                                    if (mainheads.getSelectedIndex() != -1) {
                                        //  mainhcom = mainheads.getSelectedItem().toString();
                                        mainhcom = mainHeadList.get(mainheads.getSelectedIndex()).getId();
                                    } else {

                                        mainhcom = "";

                                    }

                                    if (standaloneradio.isSelected()) {
                                        stndal = 1;
                                    } else {
                                        stndal = 0;
                                    }
                                    if (strtlineradio.isSelected()) {
                                        strt = 1;
                                    } else {
                                        strt = 0;
                                    }

                                    costval = (Double) Formats.DOUBLE.parseValue(cost_txt.getText());
                                    rod = (Double) Formats.DOUBLE.parseValue(jTextField9.getText());
                                    cor = (Double) Formats.DOUBLE.parseValue(cstreplc_txt.getText());
                                    wdv = (Double) Formats.DOUBLE.parseValue(jTextField10.getText());
                                    Date purchasedate = new Date();
                                    Date commistiondate = new Date();
                                    Date installationdate = new Date();
                                    Date putdated = new Date();
                                    Date captdated = new Date();
                                    Date effectivedate = new Date();
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTimeInMillis(purchasedate.getTime());
                                    cal.setTimeInMillis(commistiondate.getTime());
                                    cal.setTimeInMillis(installationdate.getTime());
                                    cal.setTimeInMillis(putdated.getTime());
                                    cal.setTimeInMillis(captdated.getTime());

                                    cal.set(Calendar.HOUR_OF_DAY, 00);
                                    cal.set(Calendar.MINUTE, 00);
                                    cal.set(Calendar.SECOND, 00);
                                    cal.set(Calendar.MILLISECOND, 00);
                                    Date wdvDate1 = new Date();
                                    if (wdvDate.getText() != null) {

                                        wdvDate1.setTime(cal.getTimeInMillis());
                                        wdvDate1 = (Date) Formats.TIMESTAMP.parseValue(wdvDate.getText());
                                    } else {
                                        wdvDate1 = null;
                                    }
                                    if (date.getText() != null) {
                                        purchasedate.setTime(cal.getTimeInMillis());
                                        purchasedate = (Date) Formats.TIMESTAMP.parseValue(date.getText());
                                    } else {
                                        purchasedate = null;
                                    }

                                    if (commdate.getText() != null) {
                                        commistiondate.setTime(cal.getTimeInMillis());
                                        commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
                                    } else {
                                        commistiondate = null;
                                    }

                                    if (instdate.getText() != null) {
                                        installationdate.setTime(cal.getTimeInMillis());
                                        installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
                                    } else {
                                        installationdate = null;
                                    }
                                    if (putdate.getText() != null) {
                                        putdated.setTime(cal.getTimeInMillis());
                                        putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
                                    } else {
                                        putdated = null;
                                    }

                                    if (captdate.getText() != null) {
                                        captdated.setTime(cal.getTimeInMillis());
                                        captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
                                    } else {
                                        captdated = null;
                                    }
                                    //how to copy paste document files to particular folder
                                    String string = UUID.randomUUID().toString();
                                    String[] parts = string.split("-");
                                    String part1 = parts[0];
                                    String part2 = parts[1];

                                    String flnm = linktxt.getText();
                                    System.out.println("linktext svch" + linktxt.getText());
                                    String name = "";
                                    String x = "";
                                    if (flnm.equals("")) {
                                        name = "";
                                    } else {

                                        // String arr[] = flnm.split("/");
                                        //added by pratima
                                        char[] c = flnm.toCharArray();
                                        int j = 0;
                                        int l[] = new int[50];

                                        for (int i = 0; i < c.length - 1; i++) {
                                            if (c[i] == '\\') {
                                                l[j] = i;
                                                j++;
                                            }
                                        }
                                        if (j >= 1) {
                                            x = "asset" + part1 + "" + flnm.substring((l[j - 1]) + 1, (flnm.length()));
                                        } else {

                                            x = "asset" + part1 + "" + flnm.substring(flnm.lastIndexOf(".") + 1, (flnm.length()));
                                        }

                                        String filePath = "./Asset Documents/" + x;
                                        File fi = new File(filePath);
                                        //ended by pratima

                                        //  x = "asset" + part1 + arr[arr.length - 1];
                                        name = "./Asset Documents/" + x;
                                        System.out.println("name" + name);

                                    }
                                    if ((installationdate != null) && (purchasedate != null)) {
                                        if ((installationdate.compareTo(purchasedate) >= 0)) {
                                            installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
                                            p = 1;
                                            msgdlg4.setVisible(false);
                                        } else {
                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                            msgdlg4.setVisible(true);
                                            p = 0;
                                            instdate.setText("");
                                        }
                                    }
                                    if ((putdated != null) && (installationdate != null) && (commistiondate != null)) {
                                        if ((putdated.compareTo(installationdate) >= 0) && (putdated.compareTo(commistiondate) >= 0)) {
                                            putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
                                            k = 1;
                                            msgdlg.setVisible(false);
                                        } else {
                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                            msgdlg.setVisible(true);
                                            k = 0;
                                            putdate.setText("");
                                        }
                                    }
                                    if ((commistiondate != null) && (installationdate != null)) {
                                        if ((commistiondate.compareTo(installationdate) >= 0)) {
                                            commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
                                            y = 1;
                                            msgdlg1.setVisible(false);
                                        } else {
                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                            msgdlg1.setVisible(true);

                                            y = 0;
                                            commdate.setText("");
                                        }
                                    }

                                    if ((captdated != null) && (installationdate != null) && (commistiondate != null) && (putdated != null)) {
                                        if ((captdated.compareTo(installationdate) >= 0) && (captdated.compareTo(commistiondate) >= 0) && (captdated.compareTo(putdated) >= 0)) {
                                            captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
                                            z = 1;
                                            msgdlg2.setVisible(false);
                                        } else {
                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                            msgdlg2.setVisible(true);
                                            z = 0;
                                            captdate.setText("");
                                        }
                                    }
                                    // if ((k == 1) && (y == 1) && (z == 1) && (p == 1)) {

                                    Object[] param = new Object[]{fadifid, barcode_txt.getText().trim(), name_txt.getText().trim(), ele, mainhcom, brkdwn, getaccId(), stndal, asset1, getvendId(), purchasedate, costval, name, commistiondate, installationdate, putdated, captdated, strt, rod, wdv, cor, calcu_txt.getText().trim(), agency_txt.getText().trim(), m_App.getAppUserView().getUser().getName(), new Date(), true, id, make, model, wdvDate1};
                                    //    if(  compareObjectfa(param, data)){
                                    int updatefa_master = new PreparedSentence(m_App.getSession(), "insert into fa_master(id,barcode,name,maj_classification,sub_head_class,sub_classification,account_head,is_stand_alone_asset,link,vendor,date_of_purchase,total_cost,scanned_doc,date_of_commission,date_of_installetion,date_put_to_use,date_of_capitalization,straightline_or_wdv,rate_of_deprecation,wdv_date_of_far,cost_of_replacement,how_calculated,agency_for_replacement,created_by,created_date,active,daecreference,make,model,wdvdate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP})).exec(param);
                                    if (updatefa_master > 0) {
                                        int update_fixed_master = new PreparedSentence(m_App.getSession(), "update fa_master set active=false where id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{id});
                                        int update_AMC = new PreparedSentence(m_App.getSession(), "update fa_amc set fa_id=? where fa_id=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        int update_NonAMC = new PreparedSentence(m_App.getSession(), "update fa_nonamc set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        int update_Maintnc = new PreparedSentence(m_App.getSession(), "update fa_maintenance set fa_id=? where fa_id=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        int update_Photo = new PreparedSentence(m_App.getSession(), "update fa_photograph set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        int update_physcver = new PreparedSentence(m_App.getSession(), "update fa_physicalverification set fa_id=? where fa_id=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        int update_Reval = new PreparedSentence(m_App.getSession(), "update fa_revaluation set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        int update_writeoff = new PreparedSentence(m_App.getSession(), "update fa_write_off_details set faid=? where faid=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
                                        assetListModel = new ComboBoxValModel(getAssetList());
                                        assetcombo.setModel(assetListModel);
                                        //ADDED BY GURUGANI
                                        int bbarcode = JOptionPane.showConfirmDialog(jPanel1, " Do you want to CREATE BARCODE ", "Barcode", JOptionPane.YES_NO_OPTION);
                                        if (bbarcode == JOptionPane.YES_OPTION) {
                                            try {

                                                FixedAsset2 fa = new FixedAsset2();

                                                String id = FixedAsset2.acc_id;
                                                dlf = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

                                                fl = (Object[]) new StaticSentence(m_App.getSession(), "SELECT searchkey FROM accountmaster WHERE id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);

                                                for (Object string1 : fl) {
                                                    // System.out.println(string.toString());
                                                    ss = string1.toString();
                                                }
                                                String barsequence_id = UUID.randomUUID().toString();
                                                barcode2 = fa.create_Barcode();
                                                created_barcode = barcode2;
                                                StringBuffer sb = new StringBuffer(barcode2);
                                                System.out.println(barcode2);
                                                barcode_listFaMaster = (List<String>) new StaticSentence(m_App.getSession(), " select  barcode from fa_master", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

                                                searchkey_listBarcode = (List<String>) new StaticSentence(m_App.getSession(), " select  a_searchkey from barcode_sequence", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

                                                String[] strarray = (String[]) searchkey_listBarcode.toArray(new String[0]);
                                                int count = 0;

                                                if (searchkey_listBarcode.contains(ss)) {
                                                    Object[] fetchidmax_bysearchkey = (Object[]) new StaticSentence(m_App.getSession(), " select  id from barcode_sequence where a_searchkey=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ss);

                                                    for (Object idd : fetchidmax_bysearchkey) {
                                                        // System.out.println(string.toString());
                                                        a_id = (String) idd;
                                                    }
                                                    Object[] fetchidmax_bysearchkey1 = (Object[]) new StaticSentence(m_App.getSession(), " select  a_max from barcode_sequence where a_searchkey=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(ss);

                                                    for (Object max1 : fetchidmax_bysearchkey1) {
                                                        // System.out.println(string.toString());
                                                        a_max = (int) max1;
                                                    }
                                                    a_max = a_max + 1;

                                                    max = a_max;
                                                    if (max >= 0 && max <= 9) {
                                                        barcode1 = sb.append("000").append(max);
                                                    } else if (max >= 10 && max <= 99) {
                                                        barcode1 = sb.append("00").append(max);
                                                    } else if (max >= 100 && max <= 999) {
                                                        barcode1 = sb.append("0").append(max);
                                                    } else {
                                                        barcode1 = sb.append(max);
                                                    }
                                                    Object[] param1 = new Object[]{a_id, max};

                                                    savech_max = max;

                                                    //    if(  compareObjectfa(param, data)){
                                                    //fetch_data=param;
                                                    max = 0;
                                                } else {

                                                    max = max + 1;
                                                    barcode1 = sb.append("000" + "" + max);
                                                    Object[] param2 = new Object[]{barsequence_id, ss, max};
//                    if(  compareObjectfa(param, data)){
//                    fetch_data=param;
                                                    new PreparedSentence(m_App.getSession(), "INSERT INTO barcode_sequence(id,a_searchkey,a_max) VALUES(?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT})).exec(param2);

                                                }

                                                //Object[] param = new Object[]{barcode_txt.getText().trim(), };
                                                if (existed_barcode.length() == 10 || existed_barcode.length() == 14 || existed_barcode.length() == 18) {

                                                    existed_barcode1 = existed_barcode.substring(0, existed_barcode.length() - 4);
                                                }
                                                if (created_barcode.equals(existed_barcode1)) {
                                                    String barcode1_copy = existed_barcode;
                                                    if (new PreparedSentence(m_App.getSession(), "UPDATE fa_master SET BARCODE=?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{barcode1_copy, fadifid}) >= 0) {

                                                        JOptionPane.showMessageDialog(null, "Existing Barcode Correct", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                    }

                                                } else {
                                                    new PreparedSentence(m_App.getSession(), "UPDATE barcode_sequence SET a_max=? WHERE id=?", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING})).exec(new Object[]{savech_max, a_id});

                                                    String barcode1_copy = barcode1.toString();

                                                    if (barcode_listFaMaster.contains(barcode1_copy)) {

                                                        JOptionPane.showMessageDialog(null, "Barcode  already  existed..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                    } else {
                                                        //Object[] param = new Object[]{barcode_txt.getText().trim(), };
                                                        String fffa_id = fadifid;
                                                        System.out.println(fffa_id);
                                                        new PreparedSentence(m_App.getSession(), "UPDATE fa_master SET BARCODE=?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{barcode1_copy, fadifid});

                                                        fa.reset();
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        updateFlag = true;
                                    }

                                    //continuation of copy paste doc
                                    if (linktxt.getText() != null) {
                                        File srcDir = new File(linktxt.getText());
                                        FileInputStream fii;
                                        FileOutputStream fio;
                                        try {

                                            fii = new FileInputStream(srcDir);

                                            fio = new FileOutputStream("./Asset Documents/" + x + "");
                                            byte[] b = new byte[1024];
                                            //int i = 0;
                                            int length;
                                            try {
                                                // while ((fii.read(b)) > 0) {
                                                while ((length = fii.read(b)) > 0) {//added by pratima
                                                    fio.write(b);
                                                    //fio.write(b,0,length);//added by pratima
                                                }
                                                fii.close();
                                                fio.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();

                                                Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();

                                            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

                                        }
                                    }
                                    // }//compare end
                                    return null;
                                }
                            };
                            t.execute();
                            // if ((k == 1) && (y == 1) && (z == 1) && (p == 1) ) {
                            if (updateFlag == true) {
                                JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                reset();
                                updateFlag = false;
                            } else {
                                JOptionPane.showMessageDialog(this, "No changes are found.!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            }

//                                                } else {
//                                                    JOptionPane.showMessageDialog(this, "Please select asset which is the link to another asset", null, JOptionPane.OK_OPTION);
//
//                                                }
//                                            } else {
//
//                                                Transaction t = new Transaction(m_App.getSession()) {
//                                                    @Override
//                                                    protected Object transact() throws BasicException {
//
//                                                        Double costval = null;
//                                                        Double rod = null;
//                                                        Double wdv = null;
//                                                        Double cor = null;
//                                                        String brk = null;
//                                                        String brkdwn = null;
//                                                        String asset1 = null;
//                                                        String mainhcom = null;
//                                                        String fadifid = UUID.randomUUID().toString();
//                                                        String acchead = null;
//                                                        asset1 = "";
//                                                        int stndal;
//                                                        int strt;
//                                                        String ele = elements.getSelectedItem().toString();
//                                                        if (breakdowns.getSelectedIndex() != -1) {
//                                                            brkdwn = breakdowns.getSelectedItem().toString();
//                                                                System.out.println(brkdwn);//by pratima
//                                                        } else {
//
//                                                            brkdwn = "";
//
//                                                        }
//                                                        if (mainheads.getSelectedIndex() != -1) {
//                                                            mainhcom = mainheads.getSelectedItem().toString();
//                                                                System.out.println(mainhcom);//by pratima
//                                                        } else {
//
//                                                            mainhcom = "";
//
//                                                        }
//                                                        if (standaloneradio.isSelected()) {
//                                                            stndal = 1;
//                                                        } else {
//                                                            stndal = 0;
//                                                        }
//                                                        if (strtlineradio.isSelected()) {
//                                                            strt = 1;
//                                                        } else {
//                                                            strt = 0;
//                                                        }
//
//                                                        costval = (Double) Formats.DOUBLE.parseValue(cost_txt.getText());
//                                                        rod = (Double) Formats.DOUBLE.parseValue(jTextField9.getText());
//                                                        cor = (Double) Formats.DOUBLE.parseValue(cstreplc_txt.getText());
//                                                        wdv = (Double) Formats.DOUBLE.parseValue(jTextField10.getText());
//                                                        Date purchasedate = new Date();
//                                                        Date commistiondate = new Date();
//                                                        Date installationdate = new Date();
//                                                        Date putdated = new Date();
//                                                        Date captdated = new Date();
//                                                        Date effectivedate = new Date();
//                                                        Calendar cal = Calendar.getInstance();
//                                                        cal.setTimeInMillis(purchasedate.getTime());
//                                                        cal.setTimeInMillis(commistiondate.getTime());
//                                                        cal.setTimeInMillis(installationdate.getTime());
//                                                        cal.setTimeInMillis(putdated.getTime());
//                                                        cal.setTimeInMillis(captdated.getTime());
//
//                                                        cal.set(Calendar.HOUR_OF_DAY, 00);
//                                                        cal.set(Calendar.MINUTE, 00);
//                                                        cal.set(Calendar.SECOND, 00);
//                                                        cal.set(Calendar.MILLISECOND, 00);
//                                                        purchasedate.setTime(cal.getTimeInMillis());
//                                                        purchasedate = (Date) Formats.TIMESTAMP.parseValue(date.getText());
//
//                                                        commistiondate.setTime(cal.getTimeInMillis());
//                                                        commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
//
//                                                        installationdate.setTime(cal.getTimeInMillis());
//                                                        installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
//
//                                                        putdated.setTime(cal.getTimeInMillis());
//                                                        putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
//
//                                                        captdated.setTime(cal.getTimeInMillis());
//                                                        captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
//
//                                                        //how to copy paste document files to particular folder
//                                                        String string = UUID.randomUUID().toString();
//                                                        String[] parts = string.split("-");
//                                                        String part1 = parts[0];
//                                                        String part2 = parts[1];
//                                                        String flnm = linktxt.getText();
//                                                        String name = "";
//                                                        String x = "";
//                                                        if (flnm.equals("")) {
//                                                            name = "";
//                                                        } else {
//
//                                                            String arr[] = flnm.split("/");
//                                                            x = "asset" + part1 + arr[arr.length - 1];;
//                                                            name = "./Asset Documents/" + x;
//                                                        }
//                                                        if ((installationdate.compareTo(purchasedate) >= 0)) {
//                                                            installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
//                                                            p = 1;
//                                                            msgdlg4.setVisible(false);
//                                                        } else {
//                                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
//                                                            msgdlg4.setVisible(true);
//                                                            p = 0;
//                                                            instdate.setText("");
//                                                        }
//
//                                                        if ((putdated.compareTo(installationdate) >= 0) && (putdated.compareTo(commistiondate) >= 0)) {
//                                                            putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
//                                                            k = 1;
//                                                            msgdlg.setVisible(false);
//                                                        } else {
//                                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
//                                                            msgdlg.setVisible(true);
//                                                            k = 0;
//                                                            putdate.setText("");
//                                                        }
//                                                        if ((commistiondate.compareTo(installationdate) >= 0)) {
//                                                            commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
//                                                            y = 1;
//                                                            msgdlg1.setVisible(false);
//                                                        } else {
//                                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
//                                                            msgdlg1.setVisible(true);
//
//                                                            y = 0;
//                                                            commdate.setText("");
//                                                        }
//                                                        if ((captdated.compareTo(installationdate) >= 0) && (captdated.compareTo(commistiondate) >= 0) && (captdated.compareTo(putdated) >= 0)) {
//                                                            captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
//                                                            z = 1;
//                                                            msgdlg2.setVisible(false);
//                                                        } else {
//                                                            //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
//                                                            msgdlg2.setVisible(true);
//                                                            z = 0;
//                                                            captdate.setText("");
//                                                        }
//                                                        if ((k == 1) && (y == 1) && (z == 1) && (p == 1)) {
//                                                            Object[] param = new Object[]{fadifid, barcode_txt.getText().trim(), name_txt.getText().trim(), ele, mainhcom, brkdwn, getaccId(), stndal, asset1, getvendId(), purchasedate, costval, linktxt.getText().trim(), commistiondate, installationdate, putdated, captdated, strt, rod, wdv, cor, calcu_txt.getText().trim(), agency_txt.getText().trim(), m_App.getAppUserView().getUser().getName(), new Date(), true, RevaluationDialog.RID, WriteOffDetailsDialog.WID, id};
//                                                            new PreparedSentence(m_App.getSession(), "insert into fa_master(id,barcode,name,maj_classification,sub_head_class,sub_classification,account_head,is_stand_alone_asset,link,vendor,date_of_purchase,total_cost,scanned_doc,date_of_commission,date_of_installetion,date_put_to_use,date_of_capitalization,straightline_or_wdv,rate_of_deprecation,wdv_date_of_far,cost_of_replacement,how_calculated,agency_for_replacement,created_by,created_date,active,revaluation,wo,daecreference) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING})).exec(param);
//                                                            int update_fixed_master = new PreparedSentence(m_App.getSession(), "update fa_master set active=false where id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{id});
//
//                                                            int update_AMC = new PreparedSentence(m_App.getSession(), "update fa_amc set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                            int update_NonAMC = new PreparedSentence(m_App.getSession(), "update fa_nonamc set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                            int update_Maintnc = new PreparedSentence(m_App.getSession(), "update fa_maintenance set fa_id=? where fa_id=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                            int update_Photo = new PreparedSentence(m_App.getSession(), "update fa_photograph set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                            int update_physcver = new PreparedSentence(m_App.getSession(), "update fa_physicalverification set fa_id=? where fa_id=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                            int update_Reval = new PreparedSentence(m_App.getSession(), "update fa_revaluation set fa_id=? where fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                            int update_writeoff = new PreparedSentence(m_App.getSession(), "update fa_write_off_details set faid=? where faid=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{fadifid, idf});
//                                                           assetListModel = new ComboBoxValModel(getAssetList());
//                                                            assetcombo.setModel(assetListModel);
//                                                            
//                                                        }
//                                                        //continuation of copy paste doc
//                                                        if (linktxt.getText() != null) {
//                                                            File srcDir = new File(linktxt.getText());
//                                                            FileInputStream fii;
//                                                            FileOutputStream fio;
//                                                            try {
//
//                                                                fii = new FileInputStream(srcDir);
//
//                                                                fio = new FileOutputStream("./Asset Documents/" + x + "");
//                                                                byte[] b = new byte[1024];
//                                                                int i = 0;
//                                                                try {
//                                                                    while ((fii.read(b)) > 0) {
//
//                                                                        fio.write(b);
//                                                                    }
//                                                                    fii.close();
//                                                                    fio.close();
//                                                                } catch (Exception e) {
//                                                                    e.printStackTrace();
//
//                                                                    Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);
//
//                                                                }
//                                                            } catch (Exception e) {
//                                                                e.printStackTrace();
//
//                                                                Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);
//
//                                                            }
//                                                        }
//
//                                                        return null;
//                                                    }
//                                                };
//                                                t.execute();
//
//                                                if ((k == 1) && (y == 1) && (z == 1) && (p == 1) ) {
//                                                    JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                                                    reset();
//                                                }
//
//                                            }
                            // }
                            //}
                            //else {
                            //       JOptionPane.showMessageDialog(this, "Please ensure that account head and vendor should not be empty", null, JOptionPane.OK_OPTION);
                            //  }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that Model is not empty", null, JOptionPane.OK_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Make is not empty", null, JOptionPane.OK_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure that barcode is not empty", null, JOptionPane.OK_OPTION);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please ensure that Name is not empty", null, JOptionPane.OK_OPTION);
            }

//                    } else {
//                        JOptionPane.showMessageDialog(this, "Select  Date ", "incomplte form", JOptionPane.WARNING_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Please ensure that rate of depreciation is not empty", null, JOptionPane.OK_OPTION);
//
//                }
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Please ensure that WDV as on the date of entering into FAR is not empty", null, JOptionPane.OK_OPTION);
//
//            }
        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (BasicException nfe) {
            nfe.printStackTrace();
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, nfe);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please enter  the correct price  ", null, JOptionPane.OK_OPTION);

        } catch (Exception e) {
            e.printStackTrace();

            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

        }

    }//GEN-LAST:event_savecha_butActionPerformed

    private void savebuttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttActionPerformed

        try {

            if (name_txt.getText() != null && name_txt.getText().trim().length() > 0) {
                if (barcode_txt.getText() != null && barcode_txt.getText().trim().length() > 0) {
                    if (jTextFieldMake.getText() != null && jTextFieldMake.getText().trim().length() > 0) {
                        if (jTextFieldModel.getText() != null && jTextFieldModel.getText().trim().length() > 0) {
                            if (elements.getSelectedIndex() != -1 && elements.getSelectedItem() != null && acccombo.getSelectedIndex() != -1 && acccombo.getSelectedItem() != null) {
                                //&& vendorcombo.getSelectedIndex() != -1 && vendorcombo.getSelectedItem() != null) {
                                try {
                                    namelist = (List<String>) new StaticSentence(m_App.getSession(), "select name from fa_master where active=true ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
                                    barcodelist = (List<String>) new StaticSentence(m_App.getSession(), "select barcode from fa_master  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                if ((anotherassetradio.isSelected())) {
                                    if (assetcombo.getSelectedIndex() != -1 && assetcombo.getSelectedItem() != null) {

                                        if (namelist.contains(name_txt.getText().trim())) {
                                            JOptionPane.showMessageDialog(null, "Facility with the name " + name_txt.getText() + " already exist", null, JOptionPane.OK_OPTION);

                                        } else {
                                            if (barcodelist.contains(barcode_txt.getText().trim())) {

                                                JOptionPane.showMessageDialog(null, "Facility with the Barcode" + barcode_txt.getText() + " already exist", null, JOptionPane.OK_OPTION);

                                            } else {
                                                insertlib_asset();
                                            }
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(this, "Please select asset which is linked to another asset", null, JOptionPane.OK_OPTION);

                                    }
                                } else {
//                                    try {
//                                        namelist = (List<String>) new StaticSentence(m_App.getSession(), "select name from fa_master where active=true ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
//                                   barcodelist =(List<String>) new StaticSentence(m_App.getSession(), "select barcode from fa_master  ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
//                                       
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
                                    if (namelist.contains(name_txt.getText().trim())) {
                                        JOptionPane.showMessageDialog(null, "Facility with the name " + name_txt.getText() + " already exist", null, JOptionPane.OK_OPTION);

                                    } else {
                                        if (barcodelist.contains(barcode_txt.getText().trim())) {

                                            JOptionPane.showMessageDialog(null, "Facility with the Barcode" + barcode_txt.getText() + " already exist", null, JOptionPane.OK_OPTION);

                                        } else {

                                            insertlib_asset();
                                        }
                                    }

                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Please ensure that acccount head is not be empty", null, JOptionPane.OK_OPTION);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that model is not empty", null, JOptionPane.OK_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that make is not empty", null, JOptionPane.OK_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please ensure that barcode is not empty", null, JOptionPane.OK_OPTION);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please ensure that Name is not empty", null, JOptionPane.OK_OPTION);
            }

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            // JOptionPane.showMessageDialog(this, "Please enter the correct price  ", null, JOptionPane.OK_OPTION);

        } catch (Exception e) {
            e.printStackTrace();

            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

        }
    }//GEN-LAST:event_savebuttActionPerformed

    private void reset_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_butActionPerformed
        reset();
    }//GEN-LAST:event_reset_butActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();

        if (tabno == 1) {
            try {
                if (Name_rad.isSelected()) {
                    fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 0);
                    jTable1.setModel(fxd_table.getTableModel());
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                }
                if (majcls_rad.isSelected()) {
                    fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 1);
                    jTable1.setModel(fxd_table.getTableModel());
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                }
                if (createddate_rad.isSelected()) {
                    fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 2);
                    jTable1.setModel(fxd_table.getTableModel());
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                }
                if (vendor_rad.isSelected()) {
                    fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 3);
                    jTable1.setModel(fxd_table.getTableModel());
                    jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                }

                TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(30);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(100);
                cmodel.getColumn(6).setPreferredWidth(70);
                cmodel.getColumn(7).setPreferredWidth(80);
                cmodel.getColumn(8).setPreferredWidth(120);
            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void submit_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_butActionPerformed
        flag = 1;
        JOptionPane.showMessageDialog(this, "Fixed Asset Registered Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
        reset();
    }//GEN-LAST:event_submit_butActionPerformed

    private void Name_radActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Name_radActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Name_radActionPerformed

    private void Name_radItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Name_radItemStateChanged
        try {
            fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 0);
            jTable1.setModel(fxd_table.getTableModel());
            jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            TableColumnModel cmodel = jTable1.getColumnModel();
            cmodel.getColumn(0).setPreferredWidth(30);
            cmodel.getColumn(1).setPreferredWidth(100);
            cmodel.getColumn(2).setPreferredWidth(100);
            cmodel.getColumn(3).setPreferredWidth(100);
            cmodel.getColumn(4).setPreferredWidth(100);
            cmodel.getColumn(5).setPreferredWidth(100);
            cmodel.getColumn(6).setPreferredWidth(70);
            cmodel.getColumn(7).setPreferredWidth(80);
            cmodel.getColumn(8).setPreferredWidth(120);
        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

        }
    }//GEN-LAST:event_Name_radItemStateChanged

    private void majcls_radItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_majcls_radItemStateChanged
        try {
            fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 1);
            jTable1.setModel(fxd_table.getTableModel());
            jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            TableColumnModel cmodel = jTable1.getColumnModel();
            cmodel.getColumn(0).setPreferredWidth(30);
            cmodel.getColumn(1).setPreferredWidth(100);
            cmodel.getColumn(2).setPreferredWidth(100);
            cmodel.getColumn(3).setPreferredWidth(100);
            cmodel.getColumn(4).setPreferredWidth(100);
            cmodel.getColumn(5).setPreferredWidth(100);
            cmodel.getColumn(6).setPreferredWidth(70);
            cmodel.getColumn(7).setPreferredWidth(80);
            cmodel.getColumn(8).setPreferredWidth(120);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_majcls_radItemStateChanged

    private void createddate_radItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_createddate_radItemStateChanged
        try {
            fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 2);
            jTable1.setModel(fxd_table.getTableModel());
            jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            TableColumnModel cmodel = jTable1.getColumnModel();
            cmodel.getColumn(0).setPreferredWidth(30);
            cmodel.getColumn(1).setPreferredWidth(100);
            cmodel.getColumn(2).setPreferredWidth(100);
            cmodel.getColumn(3).setPreferredWidth(100);
            cmodel.getColumn(4).setPreferredWidth(100);
            cmodel.getColumn(5).setPreferredWidth(100);
            cmodel.getColumn(6).setPreferredWidth(70);
            cmodel.getColumn(7).setPreferredWidth(80);
            cmodel.getColumn(8).setPreferredWidth(120);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_createddate_radItemStateChanged

    private void vendor_radActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendor_radActionPerformed
        try {
            fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 3);
            jTable1.setModel(fxd_table.getTableModel());
            jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            TableColumnModel cmodel = jTable1.getColumnModel();
            cmodel.getColumn(0).setPreferredWidth(30);
            cmodel.getColumn(1).setPreferredWidth(100);
            cmodel.getColumn(2).setPreferredWidth(100);
            cmodel.getColumn(3).setPreferredWidth(100);
            cmodel.getColumn(4).setPreferredWidth(100);
            cmodel.getColumn(5).setPreferredWidth(100);
            cmodel.getColumn(6).setPreferredWidth(70);
            cmodel.getColumn(7).setPreferredWidth(80);
            cmodel.getColumn(8).setPreferredWidth(120);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_vendor_radActionPerformed

    private void elementsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elementsItemStateChanged
        if (elements.getSelectedItem() == null) {
            mainheads.setSelectedIndex(-1);
            mainheads.setModel(new DefaultComboBoxModel());
            breakdowns.setSelectedIndex(-1);
            breakdowns.setModel(new DefaultComboBoxModel());
            acccombo.setSelectedIndex(-1);
            acccombo.setModel(new DefaultComboBoxModel());
        }

        if (elements.getSelectedIndex() != -1) {
            try {

                AccountMasterExt mele = (AccountMasterExt) elements.getSelectedItem();
                mainheadsModel = new ComboBoxValModel(dmang.getaccountMainHeads1(mele.getSerachkey()));
                mainHeadList = dmang.getaccountMainHeads1(mele.getSerachkey());
                mainheads.setModel(mainheadsModel);
                mainheads.setSelectedIndex(-1);
                //added by pratima
                breakdownsModel = new ComboBoxValModel(dmang.getaccountBreakpoints(mele.getSerachkey()));
                breakdownsList = dmang.getaccountBreakpoints(mele.getSerachkey());
                breakdowns.setModel(breakdownsModel);
                breakdowns.setSelectedIndex(-1);
                accountheadlistModel = new ComboBoxValModel(dmang.getSubaccounts1(mele.getSearchkey()));
                accList = dmang.getSubaccounts1(mele.getSearchkey());
                acccombo.setModel(accountheadlistModel);
                acccombo.setSelectedIndex(-1);
                //ended by pratima
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_elementsItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            max = 0;
            FixedAsset2 fa = new FixedAsset2();

            String id = FixedAsset2.acc_id;
            dlf = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

            fl = (Object[]) new StaticSentence(m_App.getSession(), "SELECT searchkey FROM accountmaster WHERE id=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);

            for (Object string : fl) {
                // System.out.println(string.toString());
                ss = string.toString();
            }
            String barsequence_id = UUID.randomUUID().toString();
            barcode2 = fa.create_Barcode();
            barcode4 = fa.create_Barcode();
            StringBuffer sb = new StringBuffer(barcode2);
            StringBuffer sb1 = new StringBuffer(barcode4);

            searchkey_list1 = (List<String>) new StaticSentence(m_App.getSession(), " select  barcode from fa_master", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
//           Iterator it=searchkey_list.iterator();
//           while(it.hasNext())
//           {
//               System.out.println(it.next());
//           }
//          barcode2.substring(0, barcode2.length()-4);
//          FixedAssetTableModel.FixedAssetInfo
            searchkey_listBarcode = (List<String>) new StaticSentence(m_App.getSession(), " select  a_searchkey from barcode_sequence", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
            String[] strarray = (String[]) searchkey_listBarcode.toArray(new String[0]);
            int count = 0;
            if (searchkey_listBarcode.contains(ss)) {
                test_searchkey1 = ss;
//              for(int i=0;i<strarray.length;i++)
//              {
//                  if(ss.equalsIgnoreCase(strarray[i])){
//                   count = count+1;
//                   
//               }
//              }
                Object[] fetchidmax_bysearchkey = (Object[]) new StaticSentence(m_App.getSession(), " select  id from barcode_sequence where a_searchkey=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(ss);

                for (Object idd : fetchidmax_bysearchkey) {
//                  System.out.println(string.toString());
                    a_id = (String) idd;
                }
                Object[] fetchidmax_bysearchkey1 = (Object[]) new StaticSentence(m_App.getSession(), " select  a_max from barcode_sequence where a_searchkey=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(ss);

                for (Object max1 : fetchidmax_bysearchkey1) {
//                  System.out.println(string.toString());
                    a_max = (int) max1;
                    mm = a_max;
                }
                a_max = a_max + 1;

                max = a_max;
                max3 = mm;
                if (max >= 0 && max <= 9) {
                    barcode1 = sb.append("000").append(max);
                    barcode3 = sb1.append("000").append(max);
                } else if (max >= 10 && max <= 99) {
                    barcode1 = sb.append("00").append(max);
                    barcode3 = sb1.append("00").append(max);
                } else if (max >= 100 && max <= 999) {
                    barcode1 = sb.append("0").append(max);
                    barcode3 = sb1.append("0").append(max);
                } else {
                    barcode1 = sb.append(max);
                    barcode3 = sb1.append(max);
                }
                max1 = max;
                max = 0;
            } else {
                test_searchkey2 = ss;

                max = max + 1;
                max2 = max;
                barcode1 = sb.append("000").append(max);
                barcode3 = sb1.append("000").append(max2);
                max = 0;
            }
//          if(searchkey_list.contains(barcode2)){System.out.println("CONTAINS THE CURRENT BARCODE");
//               max=max;
//               barcode1=sb.append("000"+""+max);
//               System.out.println(barcode1);
//           }
//           else
//           {
//               System.out.println("CONTAINS THE CURRENT BARCODE NOT PRESENT");
//               max=++max;
//               barcode1=sb.append("000"+""+max);
//               System.out.println(barcode1);
//           }
            barcode1_copy = barcode1.toString();

            barcode3_copy = barcode3.toString();

            barcode_listFaMaster = (List<String>) new StaticSentence(m_App.getSession(), " select  barcode from fa_master", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
//           Iterator it=searchkey_list.iterator();
//           while(it.hasNext())
//           {
//               System.out.println(it.next());
//           }

//             if(barcode_listFaMaster.contains(barcode3_copy))
            if (flag == 1) {

//           if(test_searchkey2.equals(ss) && mm==max2)  
//           {
//                System.out.println("BARCODE Already exists!!!!!");
//                JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww111111" );
//           }
////           else if(test_searchkey1.equals(ss) && mm==max1)
////           {
////                 System.out.println("BARCODE Already exists!!!!!");
////                JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
////                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww2222" );
////           }
//           else if(!test_searchkey1.equals(null))
//           {
//                 System.out.println("BARCODE Already exists!!!!!");
//                JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
//           }
//           else if(!test_searchkey2.equals(null))
//           {
//                 System.out.println("BARCODE Already exists!!!!!");
//                JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
//                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwww3333333333333" );
//           }
//           else if(!barcode1_copy.equals(null))
//           {
//                 System.out.println("BARCODE Already exists!!!!!");
//                JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);  
//                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww44444" );
                if (searchkey_listBarcode.contains(ss)) {
                    Object[] param1 = new Object[]{barsequence_id, ss, max1};
//                  if(  compareObjectfa(param, data)){
//                  fetch_data=param;
                    new PreparedSentence(m_App.getSession(), "UPDATE barcode_sequence SET a_max=? WHERE id=?", new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING})).exec(new Object[]{max1, a_id});

                } else {
                    Object[] param2 = new Object[]{barsequence_id, ss, max2};
//                  if(  compareObjectfa(param, data)){
//                  fetch_data=param;
                    new PreparedSentence(m_App.getSession(), "INSERT INTO barcode_sequence(id,a_searchkey,a_max) VALUES(?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.INT})).exec(param2);

                }

                //Object[] param = new Object[]{barcode_txt.getText().trim(), };
                String fffa_id = FixedAsset2.ffa_id;

                barcode_listFaMaster = (List<String>) new StaticSentence(m_App.getSession(), " select  barcode from fa_master", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();
//              if(searchkey_list.contains(barcode1_copy)) 
//              {
//                  System.out.println("BARCODE Already exists!!!!!");
//                  JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);
//              }
//              else{
                if (new PreparedSentence(m_App.getSession(), "UPDATE fa_master SET BARCODE=?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{barcode1_copy, fffa_id}) >= 0) {

                    JOptionPane.showMessageDialog(this, "Barcode created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Barcode created Not Successfull..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                flag--;
                fa.reset();
                //}
            } else {

                JOptionPane.showMessageDialog(this, "Barcode already created ..!", "Success", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void anotherassetradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anotherassetradioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anotherassetradioActionPerformed

    private void instdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_instdateActionPerformed

    private void breakdownsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breakdownsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_breakdownsActionPerformed

    private void wdvdatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wdvdatebuttonActionPerformed
        // TODO add your handling code here:
        //Added by GuruGani
        wdvDate.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(wdvDate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {

            wdvDate.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_wdvdatebuttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Name_rad;
    private javax.swing.JButton PhysicalVerification_but;
    private javax.swing.JComboBox acccombo;
    private javax.swing.JTextField agency_txt;
    private javax.swing.JButton amc_but;
    private javax.swing.JRadioButton anotherassetradio;
    private javax.swing.JComboBox assetcombo;
    private javax.swing.JTextField barcode_txt;
    private javax.swing.JComboBox breakdowns;
    private javax.swing.JTextArea calcu_txt;
    private javax.swing.JButton capitalisationdate;
    private javax.swing.JTextField captdate;
    private javax.swing.JTextField commdate;
    private javax.swing.JButton commissiondate;
    private javax.swing.JTextField cost_txt;
    private javax.swing.JRadioButton createddate_rad;
    private javax.swing.JTextField cstreplc_txt;
    private javax.swing.JTextField date;
    private javax.swing.JButton deactivate_but;
    private javax.swing.JButton document_but;
    private javax.swing.JButton edit_but;
    private javax.swing.JComboBox elements;
    private javax.swing.JButton installationdate;
    private javax.swing.JTextField instdate;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldMake;
    private javax.swing.JTextField jTextFieldModel;
    private javax.swing.JTextField linktxt;
    private javax.swing.JComboBox mainheads;
    private javax.swing.JButton maintenance_but;
    private javax.swing.JRadioButton majcls_rad;
    private javax.swing.JLabel msgdlg;
    private javax.swing.JLabel msgdlg1;
    private javax.swing.JLabel msgdlg2;
    private javax.swing.JLabel msgdlg4;
    private javax.swing.JTextField name_txt;
    private javax.swing.JButton photo_butt;
    private javax.swing.JPanel purch_panel;
    private javax.swing.JButton purchasedate;
    private javax.swing.JTextField putdate;
    private javax.swing.JButton reset_but;
    private javax.swing.JButton revaluation_but;
    private javax.swing.JButton savebutt;
    private javax.swing.JButton savecha_but;
    private javax.swing.JRadioButton standaloneradio;
    private javax.swing.JRadioButton strtlineradio;
    private javax.swing.JButton submit_but;
    private javax.swing.JButton usedate;
    private javax.swing.JRadioButton vendor_rad;
    private javax.swing.JComboBox vendorcombo;
    private javax.swing.JTextField wdvDate;
    private javax.swing.JButton wdvdatebutton;
    private javax.swing.JRadioButton wdvradio;
    private javax.swing.JButton writeoff_but;
    // End of variables declaration//GEN-END:variables

    private void deactFixedast() {
        try {

            new PreparedSentence(m_App.getSession(), "update fa_master  set  active=0  , deacby=? , deacdate=?  where id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getName(), new Date(), deac_id});

            if (Name_rad.isSelected()) {
                fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 0);
                jTable1.setModel(fxd_table.getTableModel());
            }
            if (majcls_rad.isSelected()) {
                fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 1);
                jTable1.setModel(fxd_table.getTableModel());
            }
            if (createddate_rad.isSelected()) {
                fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 2);
                jTable1.setModel(fxd_table.getTableModel());
            }
            if (vendor_rad.isSelected()) {
                fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 3);
                jTable1.setModel(fxd_table.getTableModel());
            }

            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
            assetListModel = new ComboBoxValModel(getAssetList());
            assetcombo.setModel(assetListModel);

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle() {
        return "Fixed Asset Register";
    }

    @Override
    public void activate() throws BasicException {
        strtlineradio.setSelected(true);
        loaddata();
        ButtonGrp();
        linktxt.setEditable(false);

    }

    @Override
    public boolean deactivate() {
        return true;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        dmang = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

    }

    public void init() throws BasicException {
        initComponents();

    }

    @Override
    public Object getBean() {
        return this;
    }

    public void reset() {
        barcode_txt.setText(null);
        name_txt.setText(null);
        cost_txt.setText(null);
        elements.setSelectedIndex(-1);
        breakdowns.setSelectedIndex(-1);
        mainheads.setSelectedIndex(-1);
        acccombo.setSelectedIndex(-1);
        standaloneradio.setSelected(true);
        vendorcombo.setSelectedIndex(-1);
        date.setText(null);
        linktxt.setText(null);
        instdate.setText(null);
        putdate.setText(null);
        commdate.setText(null);
        captdate.setText(null);
        calcu_txt.setText(null);
        agency_txt.setText(null);
        cstreplc_txt.setText(null);
        savebutt.setVisible(true);
        savecha_but.setVisible(false);
        idf = null;
        fixedid = null;
        strtlineradio.setSelected(false);
        wdvradio.setSelected(false);
        jTextField9.setText(null);
        jTextField10.setText(null);
        name_txt.setEditable(true);
        barcode_txt.setEditable(true);
        elements.setEnabled(true);
        submit_but.setVisible(false);
        mainheads.setEnabled(true);
        breakdowns.setEnabled(true);
        acccombo.setEnabled(true);
        standaloneradio.setEnabled(true);
        anotherassetradio.setEnabled(true);
        assetcombo.setEnabled(true);
        vendorcombo.setEnabled(true);
        purchasedate.setEnabled(true);
        document_but.setVisible(false);
        cost_txt.setEditable(true);
        jButton2.setEnabled(true);
        strtlineradio.setEnabled(true);
        wdvradio.setEnabled(true);
        jTextField9.setEditable(true);
        jTextField10.setEditable(true);
        cstreplc_txt.setEditable(true);
        calcu_txt.setEditable(true);
        agency_txt.setEditable(true);
        installationdate.setEnabled(true);
        usedate.setEnabled(true);
        commissiondate.setEnabled(true);
        capitalisationdate.setEnabled(true);
        writeoff_but.setVisible(false);
        PhysicalVerification_but.setVisible(false);
        photo_butt.setVisible(false);
        amc_but.setVisible(false);
        revaluation_but.setVisible(false);
        maintenance_but.setVisible(false);
        savecha_but.setVisible(false);
        msgdlg.setVisible(false);
        msgdlg1.setVisible(false);
        msgdlg2.setVisible(false);
        msgdlg4.setVisible(false);
        strtlineradio.setSelected(true);
        jTextFieldMake.setText(null);
        jTextFieldModel.setText(null);
        wdvDate.setText(null);
        jButton3.setVisible(false);
    }

    public void loadProperties(AppConfig config) {

        linktxt.setText(config.getProperty("folder"));

        dirty.setDirty(false);
    }

    public void saveProperties(AppConfig config) {

        config.setProperty("FOLDER", linktxt.getText());

        dirty.setDirty(false);
    }

    public void loaddata() throws BasicException {

        fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App, 0);
        jTable1.setModel(fxd_table.getTableModel());
        reset();
        submit_but.setVisible(false);
        savebutt.setVisible(true);
        savecha_but.setVisible(false);
        date.setEditable(false);
        instdate.setEditable(false);
        putdate.setEditable(false);
        commdate.setEditable(false);
        captdate.setEditable(false);
        msgdlg.setVisible(false);
        msgdlg1.setVisible(false);
        msgdlg2.setVisible(false);
        msgdlg4.setVisible(false);
        document_but.setVisible(false);
        strtlineradio.setSelected(true);

        vendorListModel = new ComboBoxValModel(getVendorList());

        vendorcombo.setModel(vendorListModel);

        assetListModel = new ComboBoxValModel(getAssetList());
        assetcombo.setModel(assetListModel);
        //elementsModel = new ComboBoxValModel(dmang.getaccountParElements());
        elementsModel = new ComboBoxValModel(dmang.getaccountElements());//added by pratima
        elements.setModel(elementsModel);

        writeoff_but.setVisible(false);
        PhysicalVerification_but.setVisible(false);
        photo_butt.setVisible(false);
        amc_but.setVisible(false);
        revaluation_but.setVisible(false);
        maintenance_but.setVisible(false);
        savecha_but.setVisible(false);
        Name_rad.setSelected(true);
        jButton3.setVisible(false);
    }

    /* public List getaccountheadList() throws BasicException {
     List<Object> accountheadList = new ArrayList<Object>();
     accountheadList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM accountmaster where ACTIVE=1 ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

     return accountheadList;
     }*/
    public List getaccountnameList() throws BasicException {
        List<AccountTable.AccountLine> accountheadList = new ArrayList<AccountTable.AccountLine>();
        AccountMasterExt acc = (AccountMasterExt) elements.getSelectedItem();
        accountheadList = (List<AccountTable.AccountLine>) new StaticSentence(m_App.getSession(), "SELECT A.NAME FROM ACCOUNTMASTER A WHERE  A.LEVEL_='S' AND A.PARENT= ?  ORDER BY A.SEARCHKEY", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(acc.getSerachkey());

        return accountheadList;
    }

    public List getVendorList() throws BasicException {
        List<Object> vendorList = new ArrayList<Object>();
        vendorList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM VENDOR WHERE ACTIVE=1 ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return vendorList;
    }

    public List getAssetList() throws BasicException {
        List<Object> assetList = new ArrayList<Object>();
        assetList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM FA_MASTER WHERE ACTIVE=TRUE ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return assetList;
    }

    public String getaccId() {
        String acchead = null;
        List<Object> accountheadlist = new ArrayList<Object>();
        try {
            if (acccombo.getSelectedIndex() != -1) {
                if (acccombo.getSelectedItem() != null) {
                    acchead = acccombo.getSelectedItem().toString();

                } else {

                    acchead = " ";

                }
                accountheadlist = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT  A.ID FROM ACCOUNTMASTER A WHERE A.NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(acchead);
                accclass = (String) accountheadlist.get(0);
            } else {
                accclass = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }
        acc_id = accclass;
        return accclass;
    }

    public String getvendId() {

        List<Object> vendorlist = new ArrayList<Object>();
        try {
            if (vendorcombo.getSelectedIndex() != -1) {
                vendorlist = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT  ID FROM VENDOR  WHERE NAME=? AND ACTIVE=TRUE", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(vendorcombo.getSelectedItem());
                vendor = (String) vendorlist.get(0);
//
            } else {
                vendor = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendor;
    }

    public String getAssetId() {

        List<Object> assetList = new ArrayList<Object>();
        try {
            if (assetcombo.getSelectedIndex() != -1) {
                assetList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT  ID FROM FA_MASTER  WHERE NAME=? AND ACTIVE=TRUE ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(assetcombo.getSelectedItem());
                asset = (String) assetList.get(0);
            } else {
                asset = "No";
            }

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return asset;
    }

    private void insertlib_asset() {

        //    if (jTextField10.getText() != null && jTextField10.getText().trim().length() > 0) {
        //     if (jTextField9.getText() != null && jTextField9.getText().trim().length() > 0) {
        //      if ((date.getText() != null && date.getText().trim().length() > 0) && (instdate.getText() != null && instdate.getText().trim().length() > 0) && (putdate.getText() != null && putdate.getText().trim().length() > 0) && (commdate.getText() != null && commdate.getText().trim().length() > 0) && (captdate.getText() != null && captdate.getText().trim().length() > 0)) {
        try {
            if (name_txt.getText().trim().length() > 0) {
                if (anotherassetradio.isSelected()) {
                    if (assetcombo.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(this, "please select asset which is link to another asset ", "incomplte form", JOptionPane.WARNING_MESSAGE);

                    }

                }
                Transaction t = new Transaction(m_App.getSession()) {
                    @Override
                    protected Object transact() throws BasicException {

                        String ab = linktxt.getText();

                        int strline;
                        Double costval = null;
                        Double rod = null;
                        Double wdv = null;
                        Double cor = null;
                        String brk = null;
                        String make = jTextFieldMake.getText();
                        String model = jTextFieldModel.getText();
                        int stdln;
                        String maincom = null;
                        String acchead = null;
                        costval = (Double) Formats.DOUBLE.parseValue(cost_txt.getText());
                        if (jTextField9.getText() != null && jTextField9.getText().trim().length() > 0) {
                            rod = (Double) Formats.DOUBLE.parseValue(jTextField9.getText());
                        }
                        cor = (Double) Formats.DOUBLE.parseValue(cstreplc_txt.getText());
                        if (jTextField10.getText() != null && jTextField10.getText().trim().length() > 0) {
                            wdv = (Double) Formats.DOUBLE.parseValue(jTextField10.getText());
                        }
                        Date purchasedate = new Date();
                        Date commistiondate = new Date();
                        Date installationdate = new Date();
                        Date putdated = new Date();
                        Date captdated = new Date();
                        Date effectivedate = new Date();
                        Date wdvDate1 = new Date();

                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(purchasedate.getTime());
                        cal.setTimeInMillis(commistiondate.getTime());
                        cal.setTimeInMillis(installationdate.getTime());
                        cal.setTimeInMillis(putdated.getTime());
                        cal.setTimeInMillis(captdated.getTime());

                        wdvDate1 = (Date) Formats.TIMESTAMP.parseValue(wdvDate.getText());
                        if (date.getText() != null) {
                            purchasedate.setTime(cal.getTimeInMillis());
                            purchasedate = (Date) Formats.TIMESTAMP.parseValue(date.getText());
                        } else {
                            purchasedate = null;
                        }

                        if (commdate.getText() != null) {
                            commistiondate.setTime(cal.getTimeInMillis());
                            commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
                        } else {
                            commistiondate = null;
                        }

                        if (instdate.getText() != null) {
                            installationdate.setTime(cal.getTimeInMillis());
                            installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
                        } else {
                            installationdate = null;
                        }
                        if (putdate.getText() != null) {
                            putdated.setTime(cal.getTimeInMillis());
                            putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
                        } else {
                            putdated = null;
                        }

                        if (captdate.getText() != null) {
                            captdated.setTime(cal.getTimeInMillis());
                            captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
                        } else {
                            captdated = null;
                        }
                        if (standaloneradio.isSelected()) {
                            stdln = 1;

                        } else {
                            stdln = 0;

                        }

                        if (strtlineradio.isSelected()) {
                            strline = 1;
                        } else {
                            strline = 0;
                        }
                        if (breakdowns.getSelectedItem() != null) {
                            //   brk = breakdowns.getSelectedItem().toString();
                            brk = breakdownsList.get(breakdowns.getSelectedIndex()).getId();
                        } else {

                            brk = " ";

                        }

                        if (assetcombo.getSelectedItem() != null) {
                            asset = assetcombo.getSelectedItem().toString();

                        } else {

                            asset = " ";

                        }
                        if (mainheads.getSelectedItem() != null) {
                            //  maincom = mainheads.getSelectedItem().toString();
                            maincom = mainHeadList.get(mainheads.getSelectedIndex()).getId();
                        } else {

                            maincom = " ";

                        }
                        String elem = null;

                        // elem = elements.getSelectedItem().toString();
                        elem = dmang.getaccountElements().get(elements.getSelectedIndex()).getId();
                        fixedid = UUID.randomUUID().toString();

                        //how to copy paste document files to particular folder
                        String string = UUID.randomUUID().toString();
                        String[] parts = string.split("-");
                        String part1 = parts[0];
                        String part2 = parts[1];
                        String flnm = linktxt.getText();
                        String name = "";
                        String x = "";
                        if (flnm.equals("") || (linktxt.getText() == null)) {
                            name = "";
                        } else {

                            // String arr[] = flnm.split("/");
                            //added by pratima
                            char[] c = flnm.toCharArray();
                            int j = 0;
                            int l[] = new int[50];

                            for (int i = 0; i < c.length - 1; i++) {
                                if (c[i] == '\\') {
                                    l[j] = i;
                                    j++;
                                }
                            }
                            x = "asset" + part1 + "" + flnm.substring((l[j - 1]) + 1, (flnm.length()));
                            String filePath = "./Asset Documents/" + x;
                            File fi = new File(filePath);
                            //ended by pratima

                            //  x = "asset" + part1 + arr[arr.length - 1];
                            name = "./Asset Documents/" + x;
                            System.out.println("name" + name);
                        }

                        if ((installationdate != null) && (purchasedate != null)) {
                            if ((installationdate.compareTo(purchasedate) >= 0)) {
                                installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
                                p = 1;
                                msgdlg4.setVisible(false);
                            } else {
                                //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                msgdlg4.setVisible(true);
                                p = 0;
                                instdate.setText("");
                            }
                        }

                        if ((putdated != null) && (putdated != null)) {
                            if ((putdated.compareTo(installationdate) >= 0) && (putdated.compareTo(commistiondate) >= 0)) {
                                putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
                                k = 1;
                                msgdlg.setVisible(false);
                            } else {
                                //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                msgdlg.setVisible(true);
                                k = 0;
                                putdate.setText("");
                            }
                        }

                        if ((commistiondate != null) && (installationdate != null)) {
                            if ((commistiondate.compareTo(installationdate) >= 0)) {
                                commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
                                y = 1;
                                msgdlg1.setVisible(false);
                            } else {
                                //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                msgdlg1.setVisible(true);

                                y = 0;
                                commdate.setText("");
                            }
                        }
                        if ((commistiondate != null) && (captdated != null) && (putdated != null) && (installationdate != null)) {
                            if ((captdated.compareTo(installationdate) >= 0) && (captdated.compareTo(commistiondate) >= 0) && (captdated.compareTo(putdated) >= 0)) {
                                captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
                                z = 1;
                                msgdlg2.setVisible(false);
                            } else {
                                //JOptionPane.showMessageDialog(this, "Put To Use Date Should be Greater than Installation Date and  Commission date  ", null, JOptionPane.WARNING_MESSAGE);
                                msgdlg2.setVisible(true);
                                z = 0;
                                captdate.setText("");
                            }
                        }
                        //  if ((k == 1) && (y == 1) && (z == 1) && (p == 1)) {
                        Object[] param = new Object[]{fixedid, barcode_txt.getText().trim(), name_txt.getText().trim(), elem, maincom, brk, getaccId(), stdln, asset, getvendId(), purchasedate, costval, name, commistiondate, installationdate, putdated, captdated, strline, rod, wdv, cor, calcu_txt.getText().trim(), agency_txt.getText().trim(), m_App.getAppUserView().getUser().getName(), new Date(), true, RevaluationDialog.RID, WriteOffDetailsDialog.WID, make, model, wdvDate1};
                        int update_fa_master = new PreparedSentence(m_App.getSession(), "INSERT INTO FA_MASTER(ID,BARCODE,NAME,MAJ_CLASSIFICATION,SUB_HEAD_CLASS,SUB_CLASSIFICATION,ACCOUNT_HEAD,IS_STAND_ALONE_ASSET,LINK,VENDOR,DATE_OF_PURCHASE,TOTAL_COST,SCANNED_DOC,DATE_OF_COMMISSION,DATE_OF_INSTALLETION,DATE_PUT_TO_USE,DATE_OF_CAPITALIZATION,STRAIGHTLINE_OR_WDV,RATE_OF_DEPRECATION,WDV_DATE_OF_FAR,COST_OF_REPLACEMENT,HOW_CALCULATED,AGENCY_FOR_REPLACEMENT,CREATED_BY,CREATED_DATE,ACTIVE,REVALUATION,WO,MAKE,MODEL,WDVDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
                                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.INT, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP})).exec(param);
                        ffa_id = fixedid;

                        if (update_fa_master > 0) {
                            fsd = 1;
                        }
                        //  }
                        //continuation of copy paste doc
                        if ((linktxt.getText() != null) && (linktxt.getText().trim().length() > 0)) {
                            File srcDir = new File(linktxt.getText());
                            FileInputStream fii;
                            FileOutputStream fio;

                            try {

                                fii = new FileInputStream(srcDir);

                                fio = new FileOutputStream("./Asset Documents/" + x + "");
                                byte[] b = new byte[1024];
                                //int i = 0;
                                int length;
                                try {
                                    // while ((fii.read(b)) > 0) {
                                    while ((length = fii.read(b)) > 0) {//added by pratima
                                        fio.write(b);
                                        //fio.write(b,0,length);//added by pratima
                                    }
                                    fii.close();
                                    fio.close();
                                } catch (Exception e) {
                                    e.printStackTrace();

                                    Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                                Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

                            }
                        }

                        return null;
                    }
                };
                t.execute();
                //if ((k == 1) && (y == 1) && (z == 1) && (p == 1) && (fsd==1)) {
                if (fsd == 1) {
                    JOptionPane.showMessageDialog(this, "Asset details inserted  Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    submit_but.setVisible(true);
                    savebutt.setVisible(false);
                    writeoff_but.setVisible(true);
                    PhysicalVerification_but.setVisible(true);
                    photo_butt.setVisible(true);
                    amc_but.setVisible(true);
                    revaluation_but.setVisible(true);
                    maintenance_but.setVisible(true);
                    name_txt.setEditable(false);
                    barcode_txt.setEditable(false);
                    elements.setEnabled(false);

                    mainheads.setEnabled(false);
                    breakdowns.setEnabled(false);
                    acccombo.setEnabled(false);
                    standaloneradio.setEnabled(false);
                    anotherassetradio.setEnabled(false);
                    assetcombo.setEnabled(false);
                    vendorcombo.setEnabled(false);
                    purchasedate.setEnabled(false);

                    cost_txt.setEditable(false);
                    jButton2.setEnabled(false);
                    strtlineradio.setEnabled(false);
                    wdvradio.setEnabled(false);
                    jTextField9.setEditable(false);
                    jTextField10.setEditable(false);
                    cstreplc_txt.setEditable(false);
                    calcu_txt.setEditable(false);
                    agency_txt.setEditable(false);
                    installationdate.setEnabled(false);
                    usedate.setEnabled(false);
                    commissiondate.setEnabled(false);
                    capitalisationdate.setEnabled(false);
                    wdvDate.setEnabled(false);
                    jButton3.setVisible(true);
                }
            }

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Number Format Exception :   "+nfe.getMessage(), null, JOptionPane.OK_OPTION);

        } catch (BasicException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Basic Exception :  "+e.getMessage(), null, JOptionPane.OK_OPTION);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Basic Exception :  "+e.getMessage(), null, JOptionPane.OK_OPTION);
            Logger.getLogger(FixedAsset2.class.getName()).log(Level.SEVERE, null, e);

        }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Select date ", "incomplte form", JOptionPane.WARNING_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Please ensure that rate of depreciation is not empty", null, JOptionPane.OK_OPTION);
//
//            }
//        } else {
//
//            JOptionPane.showMessageDialog(this, "Please ensure that WDV as on the date of entering into FAR is not empty", null, JOptionPane.OK_OPTION);
//
//        }
    }

    public void ButtonGrp() {

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(standaloneradio);
        bg1.add(anotherassetradio);

        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(strtlineradio);
        bg3.add(wdvradio);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(Name_rad);
        bg2.add(majcls_rad);
        bg2.add(createddate_rad);
        bg2.add(vendor_rad);

    }

    public boolean compareObjectfa(Object[] param, FixedAssetInfo fai) {
        boolean result = false;
        try {

            if ((!param[1].toString().equals(fai.getbarcode())) || (!param[2].toString().equals(fai.getname()))
                    || (!param[3].toString().equals(fai.getmajor_cls())) || (!param[4].toString().equals(fai.getsub_head()))
                    || (!dmang.getaccountbyid(param[6].toString()).getName().equals(fai.getac_head())) || (!param[11].toString().equals(fai.getcost().toString()))
                    || (!param[12].toString().equals(fai.getdoc()))
                    || (!param[13].toString().equals(fai.getdocomm().toString())) || (!param[14].toString().equals(fai.getdoi().toString()))
                    || (!param[15].toString().equals(fai.getdop().toString())) || (!param[16].toString().equals(fai.getdocapt().toString()))
                    || (!param[17].equals(fai.getstrt())) || (!param[18].toString().equals(fai.getrod().toString()))
                    || (!param[19].toString().equals(fai.getwdv().toString())) || (!param[20].toString().equals(fai.getcor().toString()))
                    || (!param[21].toString().equals(fai.gethowcal())) || (!param[22].toString().equals(fai.getagency()))
                    || (!param[27].toString().equals(fai.getMake())) || (!param[28].toString().equals(fai.getModel()))
                    || (!param[7].toString().equals(fai.getstand_alone())) || (!param[8].toString().equals(fai.getlink()))
                    || (!dmang.getaccountbyid(param[5].toString()).getName().equals(fai.getsub_cls())) || (!param[10].toString().equals(fai.getdop().toString()))) {
                result = true;
            } else {
                String vendorName = null;
                for (int i = 0; i < dmang.getVendorList().size(); i++) {
                    if (dmang.getVendorList().get(i).getAccount().equals(param[9].toString())) {
                        {
                            vendorName = dmang.getVendorList().get(i).getName();
                            break;
                        }
                    }
                }
                if ((!vendorName.equals(fai.getvendor()))) {
                    result = true;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String create_Barcode() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String gg = FixedAsset2.ss;
        String gg_Replace = gg.replace(".", " ");
        String[] gg_Split = gg_Replace.split(" ");
        for (int i = 0; i < gg_Split.length; i++) {

            if (i == 0 || i == 1 || i == 2) {
                char ch[] = gg_Split[i].toCharArray();

                if (ch.length == 1) {
                    for (int j = 0; j < ch.length; j++) {
                        System.out.print(0 + "" + ch[j]);
                    }
                } else {
                    for (int j = 0; j < ch.length; j++) {

                        System.out.print(ch[j]);
                    }
                }
            } else {

                char ch[] = gg_Split[i].toCharArray();
                for (int k = 0; k < gg_Split[i].length(); k++) {

                    if (ch.length >= 1 && ch.length <= 3) {
                        for (int j = 0; j < 4 - ch.length; j++) {

                            System.out.print(0);
                        }
                        for (int j = 0; j < ch.length; j++) {

                            System.out.print(ch[j]);
                        }
                        break;
                    } else {
                        for (int j = 0; j < ch.length; j++) {

                            System.out.print(ch[j]);
                        }
                    }
                }
            }

        }
        System.out.flush();
        System.setOut(old);
        a_SearchkeyWithoutDot = baos.toString();
        return a_SearchkeyWithoutDot;
    }
}
