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
import com.openbravo.data.loader.LocalRes;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.Library.BookMaster;
import com.openbravo.pos.clubmang.KIOSKSettings;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.knowYourMember.MemberForm;
import com.openbravo.pos.sms.EmailMaster;
import com.openbravo.pos.sms.MemberEmailList;
import com.openbravo.pos.util.AltEncrypter;
import com.openbravo.pos.util.DirectoryEvent;
import com.openbravo.pos.FixedAssetRegistration.MaintenanceTableModel.MaintenanceInfo;
import com.openbravo.pos.FixedAssetRegistration.FixedAssetTableModel.FixedAssetInfo;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.net.URL; 
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.awt.event.KeyEvent;
import com.openbravo.pos.Accounts.AccountTable;
import com.openbravo.pos.Library.CategoryMaster;
import com.openbravo.pos.Library.Lib_AuthorTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import java.awt.Desktop;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Component;
/**
 *
 * @author dev3
 */
public class FixedAsset extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {
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
    private static String level=null;
    private static DataLogicFacilities dmang;
    private static ComboBoxValModel elementsModel;
    private static ComboBoxValModel mainheadsModel;
    private static ComboBoxValModel breakdownsModel;
    private String deac_id;
   private FixedAssetTableModel fxd_table;
     private String docum;
    private static AccountTable acctablemodel;
     public List<MaintenanceInfo> MaintenanceInfoList = new ArrayList<MaintenanceInfo>();
     File documentfile;
   
    
    private List<String> majorclassList = new ArrayList<String>();
    private ComboBoxValModel majorclasslistModel;

    private List<String> subclassList = new ArrayList<String>();
    private ComboBoxValModel subclasslistModel;

    private List<String> subheadclassList = new ArrayList<String>();
    private ComboBoxValModel subheadclasslistModel;

    private List<String> accountheadList = new ArrayList<String>();
    private ComboBoxValModel accountheadlistModel;

    private List<String> vendorList = new ArrayList<String>();
    private ComboBoxValModel vendorListModel;
    private ComboBoxValModel assetListModel;
    String idf;
   
   public static  String fixedid=UUID.randomUUID().toString();
  
    JFileChooser chooser;
   String choosertitle;
   File srcLogo = null;
   File srcLogo1 = null;
   String id;
 File file=null;
      String filename;
          File selectedFile ;
 
    /**
     * Creates new form
     */
    public FixedAsset() {
        initComponents();
        savebutt.setVisible(true);
        standaloneradio.setSelected(true);
       // linktxt.getDocument().addDocumentListener(dirty);
       // foldbut.addActionListener(new DirectoryEvent(linktxt));
        standaloneradio.setSelected(true);
        strtlineradio.setSelected(true);
      // writeoff_but.setVisible(false);
       //  PhysicalVerification_but.setVisible(false);
      //  photo_butt.setVisible(false);
      //   amc_but.setVisible(false);
      //   revaluation_but.setVisible(false);
       //  maintenance_but.setVisible(false);
         savecha_but.setVisible(false);
       /* amcpanel.setVisible(false);
        nonamcepanel.setVisible(false);
        maintenance_panel.setVisible(true);*/
         
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

        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jLabel67 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
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
        wdvradio = new javax.swing.JRadioButton();
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
        jPanel5 = new javax.swing.JPanel();
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
        deactivate_but = new javax.swing.JButton();
        savebutt = new javax.swing.JButton();
        savecha_but = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jLabel67.setText("jLabel67");

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jButton1.setText("Save");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(1454, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1092, 1092, 1092)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(827, Short.MAX_VALUE))
        );

        jLabel1.setText("Barcode");

        barcode_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcode_txtActionPerformed(evt);
            }
        });

        jLabel2.setText("Name");

        standaloneradio.setText("Stand alone Asset");
        standaloneradio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                standaloneradioItemStateChanged(evt);
            }
        });

        anotherassetradio.setText("Part Of Another Asset");

        jLabel7.setText("Link to other asset");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assetcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assetcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
        );

        photo_butt.setText("Photograph");
        photo_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                photo_buttActionPerformed(evt);
            }
        });

        amc_but.setText("AMC and NON AMC");
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

        wdvradio.setText("WDV");

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

        javax.swing.GroupLayout purch_panelLayout = new javax.swing.GroupLayout(purch_panel);
        purch_panel.setLayout(purch_panelLayout);
        purch_panelLayout.setHorizontalGroup(
            purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purch_panelLayout.createSequentialGroup()
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purch_panelLayout.createSequentialGroup()
                                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cost_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(purch_panelLayout.createSequentialGroup()
                                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(vendorcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(purch_panelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(linktxt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(document_but))))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(strtlineradio, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(wdvradio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cstreplc_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agency_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        purch_panelLayout.setVerticalGroup(
            purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purch_panelLayout.createSequentialGroup()
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(strtlineradio)
                            .addComponent(wdvradio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cstreplc_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agency_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(purch_panelLayout.createSequentialGroup()
                        .addComponent(cost_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(purch_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(linktxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(document_but)))))
        );

        jLabel6.setText("Account Head");

        jLabel3.setText("Major Classification");

        elements.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elementsItemStateChanged(evt);
            }
        });

        jLabel4.setText("Sub Head Classification");

        mainheads.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mainheadsItemStateChanged(evt);
            }
        });

        jLabel5.setText("Sub Classification");

        breakdowns.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                breakdownsItemStateChanged(evt);
            }
        });

        jLabel13.setText("Date Of Installation");

        jLabel14.setText("Date Of Commissioning");

        jLabel15.setText("Date On Which Put To Use");

        jLabel16.setText("Date Of Capitalisation");

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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(instdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(installationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(commdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(commissiondate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(putdate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usedate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(captdate, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(capitalisationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(installationdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(putdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(usedate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(commdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commissiondate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(captdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(capitalisationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        revaluation_but.setText("Revaluation");
        revaluation_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revaluation_butActionPerformed(evt);
            }
        });

        maintenance_but.setText("Maintenance");
        maintenance_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintenance_butActionPerformed(evt);
            }
        });

        writeoff_but.setText("WriteOff");
        writeoff_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeoff_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(purch_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(standaloneradio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(anotherassetradio))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acccombo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(name_txt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(barcode_txt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(elements, javax.swing.GroupLayout.Alignment.LEADING, 0, 183, Short.MAX_VALUE)
                                        .addComponent(mainheads, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(breakdowns, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maintenance_but, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(writeoff_but, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(revaluation_but, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amc_but, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(photo_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PhysicalVerification_but, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barcode_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(elements, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mainheads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(breakdowns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acccombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(standaloneradio)
                            .addComponent(anotherassetradio)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(PhysicalVerification_but)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(photo_butt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amc_but)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revaluation_but)
                        .addGap(12, 12, 12)
                        .addComponent(maintenance_but)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(writeoff_but)))
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(purch_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1895, 1895, 1895)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1043, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Asset Details", jScrollPane1);

        edit_but.setText("Edit");
        edit_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_butActionPerformed(evt);
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

        jScrollPane3.setViewportView(jScrollPane2);

        deactivate_but.setText("Deactivate");
        deactivate_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deactivate_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deactivate_but, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(edit_but, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deactivate_but)
                    .addComponent(edit_but))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View List", jPanel5);

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

        jButton5.setText("reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 632, Short.MAX_VALUE)
                .addComponent(savebutt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(savecha_but)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savebutt)
                    .addComponent(savecha_but)
                    .addComponent(jButton5))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void savebuttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttActionPerformed
        //insertion of asset details start
      try {

            if (name_txt.getText()!=null && name_txt.getText().trim().length()>0) {
                if (barcode_txt.getText()!=null && barcode_txt.getText().trim().length()>0) {
                    if (cost_txt.getText()!=null && cost_txt.getText().trim().length()>0) {
                        
                    
                
                   if (mainheads.getSelectedIndex() != -1) {
                       // int count = Integer.valueOf(new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM fa_master WHERE NAME=? ", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find(name_txt.getText()).toString());
                           if (standaloneradio.isSelected()) {
                                    insertlib_asset(1);
                                } else {
                                    insertlib_asset(0);
                                }
                        
                        
                       
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that mainheads is not empty", null, JOptionPane.OK_OPTION);
                    }
                 

           
                    }           else {
                JOptionPane.showMessageDialog(this, "Please ensure that  cost_txt is not empty", null, JOptionPane.OK_OPTION);
            }}
                        else {
                JOptionPane.showMessageDialog(this, "Please ensure that  barcode_txt is not empty", null, JOptionPane.OK_OPTION);
            }}
                        else {
                JOptionPane.showMessageDialog(this, "Please ensure that  Name is not empty", null, JOptionPane.OK_OPTION);
            }
           
                          
                      
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, e);
            new MessageInf(e).show(new JFrame());

        }
      
       
    }//GEN-LAST:event_savebuttActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       reset();
    }//GEN-LAST:event_jButton5ActionPerformed
 
    private void edit_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_butActionPerformed
                        
                      
        if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? " , "Editing Menu" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
        savecha_but.setVisible(true);
                        savebutt.setVisible(false);
              if(jTable1.getSelectedRow()<fxd_table.getSize()){
               int row = jTable1.getSelectedRow();
           FixedAssetInfo showdata = fxd_table.getList().get(row);
                           jPanel3.setVisible(true);
                        idf=showdata.getId();
                        String name=showdata.getname();
                        String barcode=showdata.getbarcode();
                        String maj=showdata.getmajor_cls();
                        String subhead=showdata.getsub_head();
                        String sub=showdata.getsub_cls();
                        String acchead=showdata.getac_head();
                       
                        String vendor=showdata.getvendor();
                        int standalne=showdata.getstand_alone();
                        String dop=showdata.getdop();
                        String docmm=showdata.getdocomm();
                        String doi=showdata.getdoi();
                        String docapt=showdata.getdocapt();
                        String dou =showdata.getdou();
                      docum =showdata.getdoc();
                        String agency=showdata.getagency();
                        String howcal=showdata.gethowcal();
                        int strt=showdata.getstrt();
                        Double cost=showdata.getcost();
                        Double cor=showdata.getcor();
                        Double rop=showdata.getrod();
                        Double wdv=showdata.getwdv();
                         String link=showdata.getlink();
                      documentfile=new File(docum);
              
                        name_txt.setText(name+"");
                        barcode_txt.setText(barcode+"");
                        if(standalne==1){
                          standaloneradio.setSelected(true);
                      }
                      else{
                        anotherassetradio .setSelected(true);
                      }
                          if(strt==1){
                          strtlineradio.setSelected(true);
                      }
                      else{
                        wdvradio .setSelected(true);
                      }
                         
                         
                         
                         
                         
                         
                         date.setText(dop+"");
                        linktxt.setText(docum+"");
                        instdate.setText(doi+"");
                        putdate.setText(dou+"");
                        commdate.setText(docmm+"");
                        captdate.setText(docapt+"");
                        cost_txt.setText(cost+"");
                        jTextField9.setText(rop+"");
                        jTextField10.setText(wdv+"");
                        cstreplc_txt.setText(cor+"");
                        calcu_txt.setText(howcal+"");
                        agency_txt.setText(agency+"");
                        
                        
                        List<Object> p1_list = new ArrayList<Object>();
                     List<Object> p2_list = new ArrayList<Object>();
                     //List<Object> p3_list = new ArrayList<Object>();
                     //List<Object> p4_list = new ArrayList<Object>();
                     //List<Object> p5_list = new ArrayList<Object>();
                     List<Object> p3_list = new ArrayList<Object>();
                     
                     
                     
                      try {
            
            p1_list = (List<Object>)new StaticSentence(m_App.getSession(), "SELECT  a.NAME from accountmaster a WHERE a.ID=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(acchead);
            p2_list = (List<Object>)new StaticSentence(m_App.getSession(), " SELECT V.NAME FROM VENDOR V where V.ID=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(vendor);
            p3_list = (List<Object>)new StaticSentence(m_App.getSession(), " SELECT  LINK from fa_master  WHERE LINK=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(link);
           
                      } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }      
                     
                     if(p1_list.size()!=0){
                                accountheadlistModel.setSelectedItem(p1_list.get(0));
                            }else{
                                acccombo.setSelectedIndex(-1);
                                }
                     if(p2_list.size()!=0){
                                vendorListModel.setSelectedItem(p2_list.get(0));
                            }else{
                                vendorcombo.setSelectedIndex(-1);
                                }
                     if(p3_list.size()!=0){
                                assetListModel.setSelectedItem(p3_list.get(0));
                            }else{
                                assetcombo.setSelectedIndex(-1);
                                }
                     
                         
                         for(int i=0; i<elements.getItemCount(); i++)
                            {
                              if(elements.getItemAt(i).toString().equals(maj))
                                {
                                    elements.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<mainheads.getItemCount(); i++)
                            {
                              if(mainheads.getItemAt(i).toString().equals(subhead))
                                {
                                    mainheads.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<breakdowns.getItemCount(); i++)
                            {
                              if(breakdowns.getItemAt(i).toString().equals(sub))
                                {
                                    breakdowns.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<acccombo.getItemCount(); i++)
                            {
                              if(acccombo.getItemAt(i).toString().equals(acchead))
                                {
                                    acccombo.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      for(int i=0; i<vendorcombo.getItemCount(); i++)
                            {
                              if(vendorcombo.getItemAt(i).toString().equals(vendor))
                                {
                                    vendorcombo.setSelectedIndex(i);
                                    break;
                                }
                            
                            }
                      
                         
                      for(int i=0; i<assetcombo.getItemCount(); i++)
                            {
                              if(assetcombo.getItemAt(i).toString().equals(link))
                                {
                                    assetcombo.setSelectedIndex(i);
                                    break;
                                }
                              else{
                              assetcombo.setSelectedItem("no");
                              
                              }
                            
                            }
                      
                      
                      
                         
                        jTabbedPane1.setSelectedIndex(0);
                       
                 
               
                
                
                
              }
             }
         }      
    }//GEN-LAST:event_edit_butActionPerformed

    private void savecha_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savecha_butActionPerformed
                      
        
        Double costval = null;
        Double rod=null;
        Double wdv=null;
        Double cor=null;
         String brk=null;     
       String brkdwn=null;
       savebutt.setVisible(false);
        if(jTextField10.getText()!=null && jTextField10.getText().trim().length()>0){
        if(jTextField9.getText()!=null && jTextField9.getText().trim().length()>0){
        if ((date.getText() != null && date.getText().trim().length() > 0) && (instdate.getText() != null && instdate.getText().trim().length() > 0) && (putdate.getText() != null && putdate.getText().trim().length() > 0) && (commdate.getText() != null && commdate.getText().trim().length() > 0) && (captdate.getText() != null && captdate.getText().trim().length() > 0)) {
             if (name_txt.getText()!=null && name_txt.getText().trim().length()>0) {
                if (barcode_txt.getText()!=null && barcode_txt.getText().trim().length()>0) {
                    if (cost_txt.getText()!=null && cost_txt.getText().trim().length()>0) {
                      try{
                       int row = jTable1.getSelectedRow();
         FixedAssetTableModel.FixedAssetInfo data = fxd_table.getList().get(row);
                   
                    id=data.getId();
               
                          
                          
                           
                        int stndal;
                        int strt;
                        String ele=elements.getSelectedItem().toString();
                        String mainhd=mainheads.getSelectedItem().toString();
                     //  brkdwn=breakdowns.getSelectedItem().toString();
                        if(breakdowns.getSelectedIndex()!=-1){
                     brkdwn  =breakdowns.getSelectedItem().toString(); 
                     
                     
                     }else{
                     
                     brkdwn="no";
                     
                     }
                        
                          if(standaloneradio.isSelected()){
                          stndal=1;
                          }else{
                          stndal=0;
                          }
                          if(strtlineradio.isSelected()){
                          strt=1;
                          }else{
                          strt=0;
                          }
                     costval = (Double) Formats.DOUBLE.parseValue(cost_txt.getText());
                    rod=(Double) Formats.DOUBLE.parseValue(jTextField9.getText());
                    cor=(Double) Formats.DOUBLE.parseValue(jTextField10.getText());
                     wdv=(Double) Formats.DOUBLE.parseValue(cstreplc_txt.getText());
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
                    purchasedate.setTime(cal.getTimeInMillis());
                     purchasedate = (Date) Formats.TIMESTAMP.parseValue(date.getText());
                     
                      commistiondate.setTime(cal.getTimeInMillis());
                     commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
                     
                      installationdate.setTime(cal.getTimeInMillis());
                     installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
                     
                      putdated.setTime(cal.getTimeInMillis());
                     putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
                     
                      captdated.setTime(cal.getTimeInMillis());
                     captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
                     
                      
                      Object[] param = new Object[]{UUID.randomUUID().toString(), barcode_txt.getText(), name_txt.getText(), ele, mainhd, brkdwn, getaccId(), stndal, getAssetId(), getvendId(), purchasedate, costval, linktxt.getText(), commistiondate, installationdate, putdated, captdated,strt,rod,wdv,cor,calcu_txt.getText(),agency_txt.getText(),m_App.getAppUserView().getUser().getName(), new Date(),true,RevaluationDialog.RID,WriteOffDetailsDialog.WID,id};
                    new PreparedSentence(m_App.getSession(), "INSERT INTO fa_master(ID,BARCODE,NAME,MAJ_CLASSIFICATION,SUB_HEAD_CLASS,SUB_CLASSIFICATION,ACCOUNT_HEAD,IS_STAND_ALONE_ASSET,LINK,VENDOR,DATE_OF_PURCHASE,TOTAL_COST,SCANNED_DOC,DATE_OF_COMMISSION,DATE_OF_INSTALLETION,DATE_PUT_TO_USE,DATE_OF_CAPITALIZATION,STRAIGHTLINE_OR_WDV,RATE_OF_DEPRECATION,WDV_DATE_OF_FAR,COST_OF_REPLACEMENT,HOW_CALCULATED,AGENCY_FOR_REPLACEMENT,DEACBY,DEACDATE,ACTIVE,REVALUATION,WO,DAECREFERENCE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING})).exec(param);
                    
                     
                         int update_fixed_master =  new PreparedSentence(m_App.getSession(), "update fa_master set ACTIVE=FALSE where ID=? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING })).exec
                                                                            (new Object[]{id });
                  JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
               

                          
                          
                          
                      
                    
                  reset();;
                  savebutt.setVisible(true);
                  savecha_but.setVisible(false);
            } catch (NullPointerException e) {

                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
                      
                      
                      
                    
                
                   

           
                    }           else {
                JOptionPane.showMessageDialog(this, "Please ensure that  cost_txt is not empty", null, JOptionPane.OK_OPTION);
            }}
                        else {
                JOptionPane.showMessageDialog(this, "Please ensure that  barcode_txt is not empty", null, JOptionPane.OK_OPTION);
            }}
                        else {
                JOptionPane.showMessageDialog(this, "Please ensure that  Name is not empty", null, JOptionPane.OK_OPTION);
            }
           
            
        } else {
            JOptionPane.showMessageDialog(this, "Select  date ", "incomplte form", JOptionPane.WARNING_MESSAGE);
        }
        }else{
        JOptionPane.showMessageDialog(this, "Please ensure that  rate of depreciation is not empty", null, JOptionPane.OK_OPTION);

        }
        }else{
        
        JOptionPane.showMessageDialog(this, "Please ensure that  WDV as on the date of entering into FAR is not empty", null, JOptionPane.OK_OPTION);

        }
        
         reset();  
          savecha_but.setVisible(false);
                        savebutt.setVisible(true);
        /*  int row = jTable1.getSelectedRow();
         PhotoTableModel.PhotoInfo data = photo_table.getList().get(row);
                   
                    id=data.getId();
                    
          
           remarksp = remarks_txt.getText();
           
           Transaction t = new Transaction(app.getSession()) {                                                                                     

                    @Override      
                    protected Object transact() throws BasicException {   



                        int update_Email_master =  new PreparedSentence(app.getSession(), "update fa_photograph set REMARK=? where ID=? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING ,Datas.STRING})).exec
                                                                            (new Object[]{remarksp,id });
                  
               

                          return null;                                      
                            }                            
                        };                 

                        try {                 
                            t.execute();          

                            JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            

                        }
                     catch (BasicException ex) {                    
                                Logger.getLogger(PhotogpDialog.class.getName()).log(Level.SEVERE, null, ex);             
                                ex.printStackTrace();
                                new MessageInf(ex).show(new JFrame());

                     } 
                      
               
               
               
               
               
           
           }
           else{
                JOptionPane.showMessageDialog(this, "Enter Password.... !! ", "Warning", JOptionPane.WARNING_MESSAGE);
           }*/
           
    }//GEN-LAST:event_savecha_butActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        javax.swing.JTabbedPane tabpane=(javax.swing.JTabbedPane)evt.getSource();
        int tabno=tabpane.getSelectedIndex();
        
        if(tabno==1){
            try {
                fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App,0);

                jTable1.setModel(fxd_table.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
               
                TableColumnModel cmodel=jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(50);
                cmodel.getColumn(1).setPreferredWidth(100);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);
                cmodel.getColumn(5).setPreferredWidth(100);
                cmodel.getColumn(6).setPreferredWidth(60);
                cmodel.getColumn(7).setPreferredWidth(100);
                
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
            
          }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void capitalisationdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capitalisationdateActionPerformed
        captdate.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            // date=getDate(date);
            captdate.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_capitalisationdateActionPerformed

    private void usedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usedateActionPerformed
        putdate.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            // date=getDate(date);
            putdate.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_usedateActionPerformed

    private void commissiondateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commissiondateActionPerformed
        commdate.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            // date=getDate(date);
            commdate.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_commissiondateActionPerformed

    private void installationdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_installationdateActionPerformed

        instdate.setEditable(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {
            // date=getDate(date);
            instdate.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_installationdateActionPerformed

    private void breakdownsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_breakdownsItemStateChanged

    }//GEN-LAST:event_breakdownsItemStateChanged

    private void mainheadsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mainheadsItemStateChanged

    }//GEN-LAST:event_mainheadsItemStateChanged

    private void elementsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elementsItemStateChanged
        if(elements.getSelectedItem()==null){
            mainheads.setSelectedIndex(-1);
        }
        if(elements.getSelectedIndex()!=-1 ){
            try{

                AccountMasterExt mele=(AccountMasterExt)elements.getSelectedItem();
                mainheadsModel=new ComboBoxValModel(dmang.getaccountMainHeads1(mele.getSerachkey()));
                mainheads.setModel(mainheadsModel);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_elementsItemStateChanged

    private void revaluation_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revaluation_butActionPerformed
        RevaluationDialog reval;
        try {
            reval = RevaluationDialog.getDialog(this, m_App,true);
            reval.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_revaluation_butActionPerformed

    private void writeoff_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeoff_butActionPerformed
        WriteOffDetailsDialog replmt;
        try {
            replmt = WriteOffDetailsDialog.getDialog(this, m_App,true);
            replmt.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_writeoff_butActionPerformed

    private void maintenance_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenance_butActionPerformed
        MaintenanceDailog mntnce;
        try {
            mntnce = MaintenanceDailog.getDialog(this, m_App,true);
            mntnce.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_maintenance_butActionPerformed

    private void cstreplc_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cstreplc_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) || value==KeyEvent.VK_DELETE || value=='.')) {
            evt.consume();
        }
    }//GEN-LAST:event_cstreplc_txtKeyTyped

    private void cstreplc_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cstreplc_txtActionPerformed

    }//GEN-LAST:event_cstreplc_txtActionPerformed

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) || value==KeyEvent.VK_DELETE || value=='.')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed

    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) || value==KeyEvent.VK_DELETE || value=='.')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed

    }//GEN-LAST:event_jTextField9ActionPerformed

    private void cost_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cost_txtKeyTyped
        char value = evt.getKeyChar();
        if (!(Character.isDigit(value) ||(value==KeyEvent.VK_BACK_SPACE) || value==KeyEvent.VK_DELETE || value=='.')) {
            evt.consume();
        }
    }//GEN-LAST:event_cost_txtKeyTyped

    private void cost_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cost_txtActionPerformed

    }//GEN-LAST:event_cost_txtActionPerformed

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
            // date=getDate(date);
            date.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_purchasedateActionPerformed

    private void PhysicalVerification_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhysicalVerification_butActionPerformed
        PhysicalVerification phyver;
        try {
            phyver = PhysicalVerification.getDialog(this, m_App,true);
            phyver.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PhysicalVerification_butActionPerformed

    private void amc_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amc_butActionPerformed
        AmcnNonamcDialog amc;
        try {
            amc = AmcnNonamcDialog.getDialog(this, m_App,true);
            amc.showDialog();

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_amc_butActionPerformed

    private void photo_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_photo_buttActionPerformed
        PhotogpDialog photo;
        try {
            photo = PhotogpDialog.getDialog(this, m_App,true);
            photo.showDialog();
        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_photo_buttActionPerformed

    private void standaloneradioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_standaloneradioItemStateChanged
        if (standaloneradio.isSelected()) {

            jPanel4.setVisible(false);
            purch_panel.setVisible(true);

        } else {

            jPanel4.setVisible(true);

        }
    }//GEN-LAST:event_standaloneradioItemStateChanged

    private void barcode_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcode_txtActionPerformed

    private void deactivate_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deactivate_butActionPerformed
       if(jTable1.getSelectedRow()!=-1){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION){
                   if(jTable1.getSelectedRow()<fxd_table.getSize()){
               int row = jTable1.getSelectedRow();
           FixedAssetInfo showdata = fxd_table.getList().get(row);
                           
                deac_id=showdata.getId();
                     deactFixedast();
                     
                  }
             }
         }
    }//GEN-LAST:event_deactivate_butActionPerformed

    private void document_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_document_butActionPerformed
try{ File file1 = new File(linktxt.getText().replace("./", ""));
           
    java.awt.Desktop.getDesktop().open(file1);//<-- here
      } catch(IOException e){
      e.printStackTrace();}       // TODO add your handling code here:

/*try {
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN)) {
            desktop.open(new File("\"" + docum + "\""));
            System.out.println("\"" + docum + "\"");
        } else {
            System.out.println("Open is not supported");
        }
    } catch (IOException exp) {
        exp.printStackTrace();
    }*/
    }//GEN-LAST:event_document_butActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx", "xlsx", "pptx"));
         fileChooser.setAcceptAllFileFilterUsed(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
          selectedFile = fileChooser.getSelectedFile();
            filename=selectedFile.getAbsolutePath();
            linktxt.setText(filename);
            file = new File(filename);
         
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed
 private void deactFixedast() {
           try{
           
           
             new PreparedSentence(m_App.getSession(), "UPDATE fa_master  SET  ACTIVE=0  , DEACBY=? , DEACDATE=?  WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING,Datas.TIMESTAMP ,  Datas.STRING})).exec
                                                                            (new Object[]{   m_App.getAppUserView().getUser().getName() ,new Date(),  deac_id  });
             reset();
          JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
          
       }catch(NullPointerException e){
          
        e.printStackTrace();
      }
      catch(Exception e){
       e.printStackTrace();
      }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JTextField cstreplc_txt;
    private javax.swing.JTextField date;
    private javax.swing.JButton deactivate_but;
    private javax.swing.JButton document_but;
    private javax.swing.JButton edit_but;
    private javax.swing.JComboBox elements;
    private javax.swing.JButton installationdate;
    private javax.swing.JTextField instdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField linktxt;
    private javax.swing.JComboBox mainheads;
    private javax.swing.JButton maintenance_but;
    private javax.swing.JTextField name_txt;
    private javax.swing.JButton photo_butt;
    private javax.swing.JPanel purch_panel;
    private javax.swing.JButton purchasedate;
    private javax.swing.JTextField putdate;
    private javax.swing.JButton revaluation_but;
    private javax.swing.JButton savebutt;
    private javax.swing.JButton savecha_but;
    private javax.swing.JRadioButton standaloneradio;
    private javax.swing.JRadioButton strtlineradio;
    private javax.swing.JButton usedate;
    private javax.swing.JComboBox vendorcombo;
    private javax.swing.JRadioButton wdvradio;
    private javax.swing.JButton writeoff_but;
    // End of variables declaration//GEN-END:variables

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
         dmang=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
         
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
        
        
       
        
      
        
        strtlineradio.setSelected(false);
        wdvradio.setSelected(false);
        jTextField9.setText(null);
        jTextField10.setText(null);
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

       jPanel5.setVisible(true);
         fxd_table = FixedAssetTableModel.GetFixedAssetTableModel(m_App,0);
         jTable1.setModel(fxd_table.getTableModel()); 
 reset();
 savebutt.setVisible(true);
 savecha_but.setVisible(false);
         
       // subheadcombo.setModel(subheadclasslistModel);

        accountheadlistModel = new ComboBoxValModel(getaccountheadList());

        acccombo.setModel(accountheadlistModel);

        vendorListModel = new ComboBoxValModel(getVendorList());

        vendorcombo.setModel(vendorListModel);
        
        assetListModel = new ComboBoxValModel(getAssetList());
        assetcombo.setModel(assetListModel);
         elementsModel=new ComboBoxValModel(dmang.getaccountElements());
                elements.setModel(elementsModel);
           
        
         

    }

    

    public List getaccountheadList() throws BasicException {
        List<Object> accountheadList = new ArrayList<Object>();
        accountheadList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM accountmaster ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return accountheadList;
    }

    public List getVendorList() throws BasicException {
        List<Object> vendorList = new ArrayList<Object>();
        vendorList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM VENDOR ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return vendorList;
    }

    public List getAssetList() throws BasicException {
        List<Object> assetList = new ArrayList<Object>();
        assetList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM fa_master ORDER BY NAME ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list();

        return assetList;
    }
    
    
    

    public String getaccId() {

        List<Object> accountheadlist = new ArrayList<Object>();
        try {
            if (acccombo.getSelectedIndex() != -1) {
                accountheadlist = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT  a.ID from accountmaster a WHERE a.NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(acccombo.getSelectedItem());
                accclass = (String) accountheadlist.get(0);
            } else {
                accclass = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accclass;
    }

    public String getvendId() {

        List<Object> vendorlist = new ArrayList<Object>();
        try {
            if (vendorcombo.getSelectedIndex() != -1) {
                vendorlist = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT  ID from VENDOR  WHERE NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(vendorcombo.getSelectedItem());
                vendor = (String) vendorlist.get(0);
            } else {
                vendor = "null";
            }

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendor;
    }

    public String getAssetId() {

        List<Object> assetList = new ArrayList<Object>();
        try {
            if (assetcombo.getSelectedIndex() != -1) {
                assetList = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT  ID from fa_master  WHERE NAME=? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(assetcombo.getSelectedItem());
                asset = (String) assetList.get(0);
            } else {
                asset = "No";
            }

        } catch (BasicException ex) {
            Logger.getLogger(FixedAsset.class.getName()).log(Level.SEVERE, null, ex);
        }

        return asset;
    }
   


    private void insertlib_asset(int flag) {
        int strline;
        Double costval = null;
        Double rod=null;
        Double wdv=null;
        Double cor=null;
         String brk=null;
         String asset=null;
        if(jTextField10.getText()!=null && jTextField10.getText().trim().length()>0){
        if(jTextField9.getText()!=null && jTextField9.getText().trim().length()>0){
        if ((date.getText() != null && date.getText().trim().length() > 0) && (instdate.getText() != null && instdate.getText().trim().length() > 0) && (putdate.getText() != null && putdate.getText().trim().length() > 0) && (commdate.getText() != null && commdate.getText().trim().length() > 0) && (captdate.getText() != null && captdate.getText().trim().length() > 0)) {

            try {
                if (name_txt.getText().length() > 0) {
                    

                    String ab = linktxt.getText();
                   
                    costval = (Double) Formats.DOUBLE.parseValue(cost_txt.getText());
                    rod=(Double) Formats.DOUBLE.parseValue(jTextField9.getText());
                    cor=(Double) Formats.DOUBLE.parseValue(jTextField10.getText());
                     wdv=(Double) Formats.DOUBLE.parseValue(cstreplc_txt.getText());
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
                    purchasedate.setTime(cal.getTimeInMillis());
                     purchasedate = (Date) Formats.TIMESTAMP.parseValue(date.getText());
                     
                      commistiondate.setTime(cal.getTimeInMillis());
                     commistiondate = (Date) Formats.TIMESTAMP.parseValue(commdate.getText());
                     
                      installationdate.setTime(cal.getTimeInMillis());
                     installationdate = (Date) Formats.TIMESTAMP.parseValue(instdate.getText());
                     
                      putdated.setTime(cal.getTimeInMillis());
                     putdated = (Date) Formats.TIMESTAMP.parseValue(putdate.getText());
                     
                      captdated.setTime(cal.getTimeInMillis());
                     captdated = (Date) Formats.TIMESTAMP.parseValue(captdate.getText());
                     
                     
                     if(strtlineradio.isSelected()){
                         strline=1;
                     }else{
                         strline=0;
                     }
                     if(breakdowns.getSelectedItem()!=null){
                     brk  =breakdowns.getSelectedItem().toString(); 
                     
                     
                     }else{
                     
                     brk=" ";
                     
                     }
                     
                     
                     if(assetcombo.getSelectedItem()!=null){
                     asset  =assetcombo.getSelectedItem().toString(); 
                     
                     
                     }else{
                     
                     asset=" ";
                     
                     }
                         
                     
                     
                                     String elem=elements.getSelectedItem().toString();
                                    
                                    String main=mainheads.getSelectedItem().toString();
                                    
                                    
                              
                    Object[] param = new Object[]{fixedid, barcode_txt.getText(), name_txt.getText(), elem, main, brk, getaccId(), flag, asset, getvendId(), purchasedate, costval, filename, commistiondate, installationdate, putdated, captdated,strline,rod,wdv,cor,calcu_txt.getText(),agency_txt.getText(),m_App.getAppUserView().getUser().getName(), new Date(),true,RevaluationDialog.RID,WriteOffDetailsDialog.WID};
                    new PreparedSentence(m_App.getSession(), "INSERT INTO fa_master(ID,BARCODE,NAME,MAJ_CLASSIFICATION,SUB_HEAD_CLASS,SUB_CLASSIFICATION,ACCOUNT_HEAD,IS_STAND_ALONE_ASSET,LINK,VENDOR,DATE_OF_PURCHASE,TOTAL_COST,SCANNED_DOC,DATE_OF_COMMISSION,DATE_OF_INSTALLETION,DATE_PUT_TO_USE,DATE_OF_CAPITALIZATION,STRAIGHTLINE_OR_WDV,RATE_OF_DEPRECATION,WDV_DATE_OF_FAR,COST_OF_REPLACEMENT,HOW_CALCULATED,AGENCY_FOR_REPLACEMENT,CREATED_BY,CREATED_DATE,ACTIVE,REVALUATION,WO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.BOOLEAN,Datas.STRING,Datas.STRING})).exec(param);
                    JOptionPane.showMessageDialog(this, "Asset details inserted  Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                writeoff_but.setVisible(true);
         PhysicalVerification_but.setVisible(true);
         photo_butt.setVisible(true);
         amc_but.setVisible(true);
         revaluation_but.setVisible(true);
         maintenance_but.setVisible(true);
                reset();

            } catch (NullPointerException e) {

                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select  date ", "incomplte form", JOptionPane.WARNING_MESSAGE);
        }
        }else{
        JOptionPane.showMessageDialog(this, "Please ensure that  rate of depreciation is not empty", null, JOptionPane.OK_OPTION);

        }
        }else{
        
        JOptionPane.showMessageDialog(this, "Please ensure that  WDV as on the date of entering into FAR is not empty", null, JOptionPane.OK_OPTION);

        }
    }
     
    
     public void ButtonGrp() {

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(standaloneradio);
        bg1.add(anotherassetradio);
        
       
       
         ButtonGroup bg3 = new ButtonGroup();
        bg3.add(strtlineradio);
        bg3.add(wdvradio);
        
        
        
       
    }
     
}
